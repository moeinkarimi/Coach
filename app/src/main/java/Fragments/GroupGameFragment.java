package Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.GenerateCode;
import mytechcorp.ir.coach.R;
import mytechcorp.ir.coach.TextViewPlus;


public class GroupGameFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Typeface tf;
    EditText txtGroup;
    Button btnGenerate;
    TextViewPlus tvCode;


    public GroupGameFragment() {
        // Required empty public constructor
    }

    public static GroupGameFragment newInstance(String param1,String param2) {
        GroupGameFragment fragment = new GroupGameFragment();
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
        View view = inflater.inflate(R.layout.fragment_group_game,container,false);
        tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/IRANSansMobile_Light.ttf");
        txtGroup = view.findViewById(R.id.txtGroup);
        btnGenerate = view.findViewById(R.id.btnGenerate);
        tvCode = view.findViewById(R.id.tvCode);
        txtGroup.setTypeface(tf);
        btnGenerate.setTypeface(tf);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GenerateCode generateCode = new GenerateCode();
                tvCode.setText(generateCode.GenerateCode(57,txtGroup.getText().toString(),"20"));
            }
        });

        return view;
    }



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
