package Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import Model.DBHandler;
import Model.GenerateCode;
import Model.Groups;
import mytechcorp.ir.coach.R;
import mytechcorp.ir.coach.TextViewPlus;


public class MostanadFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Typeface tf;
    EditText txtGroup,txtGroup2,txtScore;
    Button btnGenerate,btnGenerate2;
    TextViewPlus tvCode,tvCode2;
    private Spinner spGroupMostanad;
    private DBHandler dbHandler;
    private int WeekID = 0;
    private Spinner spGroupFile;


    public MostanadFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public MostanadFragment(int weekID) {
        WeekID = weekID;
    }

    public static MostanadFragment newInstance(String param1, String param2) {
        MostanadFragment fragment = new MostanadFragment();
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
        View view = inflater.inflate(R.layout.fragment_mostanad,container,false);
        dbHandler = new DBHandler(getActivity());
        tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/IRANSansMobile_Light.ttf");
//        txtGroup = view.findViewById(R.id.txtGroup);
        btnGenerate = view.findViewById(R.id.btnGenerate);
        tvCode = view.findViewById(R.id.tvCode);
//        txtGroup2 = view.findViewById(R.id.txtGroup2);
        txtScore = view.findViewById(R.id.txtScore);
        btnGenerate2 = view.findViewById(R.id.btnGenerate2);
        spGroupMostanad = view.findViewById(R.id.spGroupMostanad);
        spGroupFile = view.findViewById(R.id.spGroupFile);
        tvCode2 = view.findViewById(R.id.tvCode2);
//        txtGroup.setTypeface(tf);
        btnGenerate.setTypeface(tf);
//        txtGroup2.setTypeface(tf);
        txtScore.setTypeface(tf);
        btnGenerate2.setTypeface(tf);


        try{
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dbHandler.GetGroupName(WeekID));
            spGroupMostanad.setAdapter(adapter);
            spGroupFile.setAdapter(adapter);
        }catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        btnGenerate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                GenerateCode generateCode = new GenerateCode();
                tvCode.setText(generateCode.GenerateCode(18,dbHandler.GetGroupCode(spGroupMostanad.getSelectedItemPosition()+1,WeekID),"10"));
            }
        });

        btnGenerate2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dbHandler.UpdateScore(
                        new Groups(
                                spGroupFile.getSelectedItemPosition()+1,
                                Integer.parseInt(txtScore.getText().toString()) + dbHandler.GetSumOfScore(spGroupFile.getSelectedItemPosition()+1)
                                ,WeekID
                        )
                );
                tvCode2.setText("امتیاز با موفقیت اضافه شد.");
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
