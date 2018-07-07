package Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private int WeekID = 0;

    public RecordFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public RecordFragment(int weekID) {
        WeekID = weekID;
    }

    public static RecordFragment newInstance(String param1, String param2) {
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
        Log.d("WeekId", String.valueOf(WeekID));
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
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dbHandler.GetGroupName(WeekID));
            spGroupRecord.setAdapter(adapter);
            spGroupRecord.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    int r1 = Integer.parseInt(dbHandler.GetSelectedRecord(WeekID, spGroupRecord.getSelectedItemPosition()+1)[0]),
                        r2 = Integer.parseInt(dbHandler.GetSelectedRecord(WeekID, spGroupRecord.getSelectedItemPosition()+1)[1]),
                        r3 = Integer.parseInt(dbHandler.GetSelectedRecord(WeekID, spGroupRecord.getSelectedItemPosition()+1)[2]);

                    if (r1 == 0)
                        txtRecord1.setText("");
                    else txtRecord1.setText(String.valueOf(r1));

                    if (r2 == 0)
                        txtRecord2.setText("");
                    else txtRecord2.setText(String.valueOf(r2));

                    if (r3 == 0)
                        txtRecord3.setText("");
                    else txtRecord3.setText(String.valueOf(r3));

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
                                WeekID,
                                r1,
                                r2,
                                r3
                        )
                );

                loadData();
            }
        });
        return view;
    }

    private void loadData(){
        db = dbHandler.getReadableDatabase();
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();

        List<Record> recordList = dbHandler.getAllRecord(WeekID);
        for(Record record : recordList){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("gname", record.getGroupName());
            map.put("bestscore", String.valueOf(record.getBestRecord()));
            map.put("Code", record.getCode());
            Items.add(map);
            Log.d("Item", Items.toString());
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
                    if (gvRecord.getCount()<4){
                        switch (i) {
                            case 0:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "20"));
                                break;
                            case 1:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "18"));
                                break;
                            case 2:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "16"));
                                break;
                        }
                    }
                    else if (gvRecord.getCount()>3 && gvRecord.getCount()<8){
                        switch (i) {
                            case 0:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "20"));
                                break;
                            case 1:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "20"));
                                break;
                            case 2:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "18"));
                                break;
                            case 3:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "18"));
                                break;
                            case 4:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "16"));
                                break;
                            case 5:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "16"));
                                break;
                            case 6:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "16"));
                                break;
                        }
                    }
                    else if (gvRecord.getCount()>7){
                        switch (i) {
                            case 0:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "20"));
                                break;
                            case 1:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "20"));
                                break;
                            case 2:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "20"));
                                break;
                            case 3:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "18"));
                                break;
                            case 4:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "18"));
                                break;
                            case 5:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "18"));
                                break;
                            case 6:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "16"));
                                break;
                            case 7:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "16"));
                                break;
                            case 8:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "16"));
                                break;
                            case 9:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "14"));
                                break;
                            case 10:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "14"));
                                break;
                            case 11:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "14"));
                                break;
                            case 12:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "12"));
                                break;
                            case 13:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "12"));
                                break;
                            case 14:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "12"));
                                break;
                            case 15:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "10"));
                                break;
                            case 16:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "10"));
                                break;
                            case 17:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "10"));
                                break;
                            case 18:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "08"));
                                break;
                            case 19:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "08"));
                                break;
                            case 20:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "08"));
                                break;
                            case 21:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "06"));
                                break;
                            case 22:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "06"));
                                break;
                            case 23:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "06"));
                                break;
                            case 24:
                                tvpCode.setText(generateCode.GenerateCode(42, tvpCode2.getText().toString(), "04"));
                                break;
                        }
                    }

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
