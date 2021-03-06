package Fragments;

import android.annotation.SuppressLint;
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
    private int WeekID = 0;

    public Maket() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public Maket(int weekID) {
        WeekID = weekID;
    }

    public static Maket newInstance(String param1, String param2) {
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
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dbHandler.GetGroupName(WeekID));
            spGroupMaket.setAdapter(adapter);
        }catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerateCode generateCode = new GenerateCode();
                Log.d(" a ", dbHandler.GetGroupCode(spGroupMaket.getSelectedItemPosition()+1,WeekID));
                if (rdbGood.isChecked()){
                    tvCode.setText(generateCode.GenerateCode(76,dbHandler.GetGroupCode(spGroupMaket.getSelectedItemPosition()+1,WeekID),"20"));
                }
                else if (rdbMiddling.isChecked()){
                    tvCode.setText(generateCode.GenerateCode(76,dbHandler.GetGroupCode(spGroupMaket.getSelectedItemPosition()+1,WeekID),"15"));
                }
                else if (rdbWeak.isChecked()){
                    tvCode.setText(generateCode.GenerateCode(76,dbHandler.GetGroupCode(spGroupMaket.getSelectedItemPosition()+1,WeekID),"10"));
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
