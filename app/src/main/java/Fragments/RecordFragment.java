package Fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.DBHandler;
import Model.GenerateCode;
import Model.Record;
import mytechcorp.ir.coach.R;
import mytechcorp.ir.coach.TextViewPlus;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private DBHandler dbHandler;
    private Typeface tf;
    GridView gvRecord;
    Button btnGenerate;
    EditText txtRecord1, txtRecord2, txtRecord3;
    TextViewPlus txtCodeGenerated;
    private Spinner spGroupRecord;
    private SQLiteDatabase db;

    public RecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordFragment newInstance(String param1,String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2,param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record,container,false);
        dbHandler = new DBHandler(getActivity());
        tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/IRANSansMobile_Light.ttf");
        gvRecord = view.findViewById(R.id.gvRecord);
        btnGenerate = view.findViewById(R.id.btnSave);
        txtRecord1 = view.findViewById(R.id.txtRecord1);
        txtRecord2 = view.findViewById(R.id.txtRecord2);
        txtRecord3 = view.findViewById(R.id.txtRecord3);
        spGroupRecord = view.findViewById(R.id.spGroupMaket);
        spGroupRecord = view.findViewById(R.id.spGroupRecord);
        btnGenerate.setTypeface(tf);
        txtRecord1.setTypeface(tf);
        txtRecord2.setTypeface(tf);
        txtRecord3.setTypeface(tf);
        loadData();
        try{
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dbHandler.GetGroupName(1));
            spGroupRecord.setAdapter(adapter);
            spGroupRecord.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    txtRecord1.setText(dbHandler.GetSelectedRecord(1, spGroupRecord.getSelectedItemPosition()+1)[0]);
                    txtRecord2.setText(dbHandler.GetSelectedRecord(1, spGroupRecord.getSelectedItemPosition()+1)[1]);
                    txtRecord3.setText(dbHandler.GetSelectedRecord(1, spGroupRecord.getSelectedItemPosition()+1)[2]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
        }catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r1 = 0, r2 = 0, r3 = 0;
                if (!txtRecord1.getText().toString().equals("")) {
                    r1 = Integer.parseInt(txtRecord1.getText().toString());
                }
                if (!txtRecord2.getText().toString().equals("")) {
                    r2 = Integer.parseInt(txtRecord2.getText().toString());
                }
                if (!txtRecord3.getText().toString().equals("")) {
                    r3 = Integer.parseInt(txtRecord3.getText().toString());
                }

                dbHandler.UpdateRecord(
                        new Record(
                                spGroupRecord.getSelectedItemPosition()+1,
                                1,
                                r1,
                                r2,
                                r3
                        )
                );
            }
        });
        return view;
    }

    private void loadData(){
        db = dbHandler.getReadableDatabase();
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();

        List<Record> recordList = dbHandler.getAllRecord(1);
        for(Record record : recordList){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("gname", record.getGroupName());
            map.put("bestscore", String.valueOf(record.getBestRecord()));
            map.put("Code", record.getCode());
            Items.add(map);
        }

        if (Items.isEmpty()){

        }
        else {
            ListAdapter adapter = new SimpleAdapter(getActivity(), Items,
                    R.layout.record_sort,
                    new String[]{
                            "gname", "bestscore", "Code"
                    },
                    new int[]{
                            R.id.tvpGroupName, R.id.tvpRecord, R.id.tvpCode2
                    });

            gvRecord.setAdapter(adapter);
            gvRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView,View view,int i,long l) {
                    TextViewPlus tvpCode = view.findViewById(R.id.tvpCode);
                    TextViewPlus tvpCode2 = view.findViewById(R.id.tvpCode2);
                    TextViewPlus tvpRec = view.findViewById(R.id.tvpRecord);
                    GenerateCode generateCode = new GenerateCode();
                    tvpCode.setText(generateCode.GenerateCode(42,tvpCode2.getText().toString(),tvpRec.getText().toString()));
                    if(tvpCode.length()<7){
                        tvpCode.append("0");
                    }
                }
            });
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
