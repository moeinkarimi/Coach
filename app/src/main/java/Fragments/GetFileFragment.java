package Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import mytechcorp.ir.coach.R;
import mytechcorp.ir.coach.TextViewPlus;

public class GetFileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private int WeekID = 0;

    private OnFragmentInteractionListener mListener;
    private Button btnGetFile;
    private TextViewPlus tvpFile;

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
        btnGetFile = view.findViewById(R.id.btnGet);
        tvpFile = view.findViewById(R.id.tvpFile);
        btnGetFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), "sd", Toast.LENGTH_LONG).show();
                try{
                    ChooserDialog show = new ChooserDialog().with(getActivity())
                            .withStartFile(String.valueOf(Environment.getExternalStorageDirectory()))
                            .withChosenListener(new ChooserDialog.Result() {
                                @Override
                                public void onChoosePath(String path, File pathFile) {
                                    StringBuilder text = new StringBuilder();
                                    try {
                                        File file = new File(path);

                                        BufferedReader br = new BufferedReader(new FileReader(file));
                                        String line;
                                        while ((line = br.readLine()) != null) {
                                            text.append(line);
                                            text.append('\n');
                                        }
                                        br.close() ;
                                    }catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    tvpFile.setText(text.toString());

                                }
                            })
                            .build()
                            .show();

                }catch (Exception ex){
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();

                }

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
        void onFragmentInteraction(Uri uri);
    }
}
