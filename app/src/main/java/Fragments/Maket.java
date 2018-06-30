package Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import Model.DBHandler;
import Model.GenerateCode;
import mytechcorp.ir.coach.R;
import mytechcorp.ir.coach.TextViewPlus;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Maket.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Maket#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Maket extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Typeface tf;
    EditText txtGroup;
    Button btnGenerate;
    TextViewPlus tvCode;
    RadioButton rdbGood, rdbMiddling, rdbWeak;
    Spinner spGroupMaket;
    private DBHandler dbHandler;

    public Maket() {
        // Required empty public constructor
    }

    public static Maket newInstance(String param1,String param2) {
        Maket fragment = new Maket();
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
        View view = inflater.inflate(R.layout.fragment_maket,container,false);
        dbHandler = new DBHandler(getActivity());
        tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/IRANSansMobile_Light.ttf");
//        txtGroup = view.findViewById(R.id.txtGroup);
        btnGenerate = view.findViewById(R.id.btnGenerate);
        rdbGood = view.findViewById(R.id.rdbGood);
        rdbMiddling = view.findViewById(R.id.rdbMiddling);
        rdbWeak = view.findViewById(R.id.rdbWeak);
        tvCode = view.findViewById(R.id.tvCode);
        spGroupMaket = view.findViewById(R.id.spGroupMaket);
//        txtGroup.setTypeface(tf);
        btnGenerate.setTypeface(tf);
        rdbMiddling.setTypeface(tf);
        rdbWeak.setTypeface(tf);
        rdbGood.setTypeface(tf);

        try{
            String[] strings = new String[4];
            strings[0] = dbHandler.GetGroupName(1)[0];
            strings[1] = dbHandler.GetGroupName(1)[1];
            strings[2] = dbHandler.GetGroupName(1)[2];
            strings[3] = dbHandler.GetGroupName(1)[3];
            Log.d("0",strings[0] );
            Log.d("1",strings[1] );
            Log.d("2",strings[2] );
            Log.d("3",strings[3] );
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strings);
            spGroupMaket.setAdapter(adapter);
        }catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerateCode generateCode = new GenerateCode();
                if (rdbGood.isChecked()){
                    tvCode.setText(generateCode.GenerateCode(76,dbHandler.GetGroupCode(spGroupMaket.getSelectedItemPosition()+1,1),"20"));
                }
                else if (rdbMiddling.isChecked()){
                    tvCode.setText(generateCode.GenerateCode(76,dbHandler.GetGroupCode(spGroupMaket.getSelectedItemPosition()+1,1),"15"));
                }
                else if (rdbWeak.isChecked()){
                    tvCode.setText(generateCode.GenerateCode(76,dbHandler.GetGroupCode(spGroupMaket.getSelectedItemPosition()+1,1),"10"));
                }
                else
                    Toast.makeText(getActivity(), "لطفا یک گزینه را انتخاب نمایید.", Toast.LENGTH_LONG).show();

            }
        });

        return view;
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
