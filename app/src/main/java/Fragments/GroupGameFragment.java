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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import Model.DBHandler;
import Model.GameTime;
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
    private int WeekID = 0;
    private DBHandler dbHandler;

    public GroupGameFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public GroupGameFragment(int weekID) {
        WeekID = weekID;
    }

    public static GroupGameFragment newInstance(String param1, String param2) {
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
        dbHandler = new DBHandler(getActivity());
        txtGroup = view.findViewById(R.id.txtGroup);
        btnGenerate = view.findViewById(R.id.btnGenerate);
        tvCode = view.findViewById(R.id.tvCode);
        txtGroup.setTypeface(tf);
        btnGenerate.setTypeface(tf);
        setGameTime();
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GenerateCode generateCode = new GenerateCode();
                tvCode.setText(generateCode.GenerateCode(57,txtGroup.getText().toString(),"20"));
            }
        });

        return view;
    }

    private void setGameTime(){
        if (!dbHandler.CheckGameTime(WeekID)){
            //Select
        }
        else {
            int GroupCount = dbHandler.GetGroupCount(WeekID), sGp = GroupCount-1;
            String[] groupName = dbHandler.GetGroupName(WeekID);
            int[] gAdded = new int[GroupCount];
            Random random;
//            try {

                for(int i=0;i<GroupCount;i++ ){
                    random = new Random();
                    int n = random.nextInt(sGp)+1;
                    for (int j = 0; j<=i;j++){
                        if (gAdded[j] != n){
                            gAdded[i] = n;
                        }
                    }
                }
//            }catch (Exception ex){
//                Toast.makeText(getActivity(), ex.getMessage() + GroupCount, Toast.LENGTH_LONG).show();
//            }
            if (GroupCount % 3 == 0){
                for (int i=0;i<GroupCount;i++){
                    if (i<3){
                        dbHandler.AddGameTime(new GameTime(WeekID, gAdded[i], "09"));
                    }
                    else if (i>=3 && i<6){
                        dbHandler.AddGameTime(new GameTime(WeekID, gAdded[i], "18"));
                    }
                    else if (i>=6 && i<9){
                        dbHandler.AddGameTime(new GameTime(WeekID, gAdded[i], "27"));
                    }
                    else if (i>=9 && i<12){
                        dbHandler.AddGameTime(new GameTime(WeekID, gAdded[i], "36"));
                    }
                    else if (i>=12 && i<15){
                        dbHandler.AddGameTime(new GameTime(WeekID, gAdded[i], "45"));
                    }
                    else if (i>=15 && i<18){
                        dbHandler.AddGameTime(new GameTime(WeekID, gAdded[i], "54"));
                    }
                    else if (i>=18 && i<21){
                        dbHandler.AddGameTime(new GameTime(WeekID, gAdded[i], "63"));
                    }
                    else if (i>=21 && i<24){
                        dbHandler.AddGameTime(new GameTime(WeekID, gAdded[i], "72"));
                    }
                }
            }
            else if (GroupCount % 3 == 1){
                if (GroupCount < 4){
                    for (int i=0;i<GroupCount;i++) {
                        if (i < 2) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"09"));
                        }
                        else if (i >= 2 && i < 4) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"18"));
                        }
                    }
                }
                else if (GroupCount < 7){
                    for (int i=0;i<GroupCount;i++) {
                        if (i < 3) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"09"));
                        }
                        else if (i >= 3 && i < 5) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"18"));
                        }
                        else {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"27"));
                        }
                    }
                }
                else if (GroupCount < 10){
                    for (int i=0;i<GroupCount;i++) {
                        if (i < 3) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"09"));
                        }
                        else if (i >= 3 && i < 6) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"18"));
                        }
                        else if (i >= 6 && i < 8){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"27"));
                        }
                        else {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"36"));
                        }
                    }
                }
                else if (GroupCount < 13){
                    for (int i=0;i<GroupCount;i++) {
                        if (i < 3) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"09"));
                        }
                        else if (i >= 3 && i < 6) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"18"));
                        }
                        else if (i >= 6 && i < 9){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"27"));
                        }
                        else if (i >= 9 && i < 11){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"36"));
                        }
                        else {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"45"));
                        }
                    }
                }
                else if (GroupCount < 16){
                    for (int i=0;i<GroupCount;i++) {
                        if (i < 3) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"09"));
                        }
                        else if (i >= 3 && i < 6) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"18"));
                        }
                        else if (i >= 6 && i < 9){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"27"));
                        }
                        else if (i >= 9 && i < 12){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"36"));
                        }
                        else if (i >= 12 && i < 14){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"45"));
                        }
                        else {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"54"));
                        }
                    }
                }
                else if (GroupCount < 19){
                    for (int i=0;i<GroupCount;i++) {
                        if (i < 3) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"09"));
                        }
                        else if (i >= 3 && i < 6) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                        }
                        else if (i >= 6 && i < 9){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"27"));
                        }
                        else if (i >= 9 && i < 12){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"36"));
                        }
                        else if (i >= 12 && i < 15){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"45"));
                        }
                        else if (i >= 15 && i < 17){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"54"));
                        }
                        else {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"63"));
                        }
                    }
                }
                else if (GroupCount < 22){
                    for (int i=0;i<GroupCount;i++) {
                        if (i < 3) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                        }
                        else if (i >= 3 && i < 6) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                        }
                        else if (i >= 6 && i < 9){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"27"));
                        }
                        else if (i >= 9 && i < 12){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"36"));
                        }
                        else if (i >= 12 && i < 15){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"45"));
                        }
                        else if (i >= 15 && i < 18){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"54"));
                        }
                        else if (i >= 18 && i < 20){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"63"));
                        }
                        else {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"72"));
                        }
                    }
                }
                else if (GroupCount < 25){
                    for (int i=0;i<GroupCount;i++) {
                        if (i < 3) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                        }
                        else if (i >= 3 && i < 6) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                        }
                        else if (i >= 6 && i < 9){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"27"));
                        }
                        else if (i >= 9 && i < 12){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"36"));
                        }
                        else if (i >= 12 && i < 15){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"45"));
                        }
                        else if (i >= 15 && i < 18){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"54"));
                        }
                        else if (i >= 18 && i < 21){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"63"));
                        }
                        else if (i >= 21 && i < 23){
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"72"));
                        }
                        else {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"81"));
                        }
                    }
                }
            }
            else if (GroupCount % 3 == 2){
                if (GroupCount < 5){
                    for (int i=0;i<GroupCount;i++) {
                        if (i < 3) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                        }
                        else if (i >= 3 && i < 5) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                        }
                    }
                }
                else if (GroupCount < 8){
                    for (int i=0;i<GroupCount;i++) {
                        if (i < 3) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                        }
                        else if (i >= 3 && i < 5) {
                            dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                        }
                    }
                }
            }
        }
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
