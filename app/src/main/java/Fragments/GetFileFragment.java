package Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.DBHandler;
import Model.GenerateCode;
import Model.Groups;
import mytechcorp.ir.coach.R;
import mytechcorp.ir.coach.TextViewPlus;

public class GetFileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private int WeekID = 0;

    private OnFragmentInteractionListener mListener;
    private Button btnGetFile, btnSave;
    private TextViewPlus tvpFile;
    EditText txtScore2;
    private DBHandler dbHandler;
    private Spinner spGroupFile;
    private Typeface tf;
    RadioButton rdbFile, rdbDasti;
    private RadioGroup rdGroup;

    public GetFileFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public GetFileFragment(int weekID) {
        WeekID = weekID;
    }

    public static GetFileFragment newInstance(String param1, String param2) {
        GetFileFragment fragment = new GetFileFragment();
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
        View view = inflater.inflate(R.layout.fragment_get_file,container,false);
        tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/IRANSansMobile_Light.ttf");
        btnGetFile = view.findViewById(R.id.btnGet);
        tvpFile = view.findViewById(R.id.tvpFile);
        spGroupFile = view.findViewById(R.id.spGroupFile);
        btnSave = view.findViewById(R.id.btnSave);
        txtScore2 = view.findViewById(R.id.txtScore2);
        rdbFile = view.findViewById(R.id.rdbFile);
        rdbDasti = view.findViewById(R.id.rdbDasti);
        rdGroup = view.findViewById(R.id.rdGroup);
        dbHandler = new DBHandler(getActivity());
        btnGetFile.setTypeface(tf);
        txtScore2.setTypeface(tf);
        btnSave.setTypeface(tf);
        rdbDasti.setTypeface(tf);
        rdbFile.setTypeface(tf);
        LoadData();

        try{
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dbHandler.GetGroupName(WeekID));
            spGroupFile.setAdapter(adapter);
        }catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        if(WeekID==1 || WeekID==2){

            rdGroup.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.VISIBLE);
            txtScore2.setVisibility(View.VISIBLE);
            btnGetFile.setVisibility(View.GONE);
        }
        else {
            rdGroup.setVisibility(View.GONE);
            btnSave.setVisibility(View.GONE);
            txtScore2.setVisibility(View.GONE);
            btnGetFile.setVisibility(View.VISIBLE);
        }
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int checkedId) {
                switch(checkedId){
                    case R.id.rdbDasti:
                        btnSave.setVisibility(View.VISIBLE);
                        txtScore2.setVisibility(View.VISIBLE);
                        btnGetFile.setVisibility(View.GONE);
                        break;
                    case R.id.rdbFile:
                        btnSave.setVisibility(View.GONE);
                        txtScore2.setVisibility(View.GONE);
                        btnGetFile.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
            }
            }});

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHandler.UpdateScore(
                        new Groups(
                                spGroupFile.getSelectedItemPosition()+1,
                                dbHandler.GetSumOfScore(spGroupFile.getSelectedItemPosition()+1,WeekID)+ Integer.parseInt(txtScore2.getText().toString()),
                                WeekID
                        )
                );
                LoadData();
            }
        });

        btnGetFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                try{
                    ChooserDialog show = new ChooserDialog().with(getActivity())
                            .withFilter(false, false, "mnk")
                            .withStartFile(String.valueOf(Environment.getExternalStorageDirectory()))
                            .withChosenListener(new ChooserDialog.Result() {
                                @Override
                                public void onChoosePath(String path, File pathFile) {
                                    StringBuilder text = new StringBuilder();
                                    try {
                                        File file = new File(path);

                                        BufferedReader br = new BufferedReader(new FileReader(file));
                                        String[] line = new String[150];
                                        for (int i=0;(line[i] = br.readLine()) != null; i++) {
                                            text.append(line);
                                            text.append('\n');
                                        }
                                        GenerateCode generateCode = new GenerateCode();
                                        dbHandler.UpdateScore(
                                                new Groups(
                                                        spGroupFile.getSelectedItemPosition()+1,
                                                        Integer.parseInt(generateCode.ConvertBinaryToDesimal(line[5]))
                                                        ,WeekID
                                                )
                                        );

                                        br.close() ;
                                    }catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            })
                            .build()
                            .show();

                    LoadData();
                }catch (Exception ex){
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });
        return view;
    }

    private void LoadData(){
        if(!isEmptyStringArray(dbHandler.GetGroupNameIfHasScore(WeekID))){
            tvpFile.setText("");
            for(int i=0; i<dbHandler.GetGroupNameIfHasScore(WeekID).length; i++){
                tvpFile.append(dbHandler.GetGroupNameIfHasScore(WeekID)[i] + " فایل خروجی را تحویل داد.\n");
            }
        }
        else {
            tvpFile.setText("هیچ گروهی فایل را تحویل نداده است");
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public boolean isEmptyStringArray(String [] array){
        for(int i=0; i<array.length; i++){
            if(array[i]!=null){
                return false;
            }
        }
        return true;
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
        void onFragmentInteraction(Uri uri);
    }
}
