package Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import Model.DBHandler;
import Model.GameTime;
import Model.GenerateCode;
import Model.Record;
import mytechcorp.ir.coach.R;
import mytechcorp.ir.coach.TextViewPlus;


public class GroupGameFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Typeface tf;
    Button btnGenerate, btnRegroup;
    TextViewPlus tvCode;
    private int WeekID = 0;
    private DBHandler dbHandler;
    private SQLiteDatabase db;
    private Spinner spGroupGame;
    private GridView gvGameTime;

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
        btnGenerate = view.findViewById(R.id.btnGenerate);
        spGroupGame = view.findViewById(R.id.spGroupGame);
        btnRegroup = view.findViewById(R.id.btnRegroup);
        gvGameTime = view.findViewById(R.id.gvGameTime);
        tvCode = view.findViewById(R.id.tvCode);
        btnGenerate.setTypeface(tf);
        btnRegroup.setTypeface(tf);
        setGameTime();
        try{
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dbHandler.GetGroupName(WeekID));
            spGroupGame.setAdapter(adapter);

        }catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GenerateCode generateCode = new GenerateCode();
                tvCode.setText(generateCode.GenerateCode(57,dbHandler.GetGroupCode(spGroupGame.getSelectedItemPosition()+1,WeekID), "20"));
            }
        });
        btnRegroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.DeleteGrouping(WeekID);
                setGameTime();
            }
        });

        return view;
    }

    private void setGameTime(){
        Log.d("bool", String.valueOf(WeekID));
        if (dbHandler.CheckGameTime(WeekID)){
            db = dbHandler.getReadableDatabase();
            final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();

            List<GameTime> gameTimeList = dbHandler.GetListOfGames(WeekID);
            for(GameTime gameTime : gameTimeList){
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("gname", gameTime.getGroupName());
                if (gameTime.getGameTime().equals("09")){
                    map.put("gametime", "15 دقیقه اول");
                }
                else if (gameTime.getGameTime().equals("18")){
                    map.put("gametime", "15 دقیقه دوم");
                }
                else if (gameTime.getGameTime().equals("27")){
                    map.put("gametime", "15 دقیقه سوم");
                }
                else if (gameTime.getGameTime().equals("36")){
                    map.put("gametime", "15 دقیقه چهارم");
                }
                else if (gameTime.getGameTime().equals("45")){
                    map.put("gametime", "15 دقیقه پنجم");
                }
                else if (gameTime.getGameTime().equals("54")){
                    map.put("gametime", "15 دقیقه ششم");
                }
                else if (gameTime.getGameTime().equals("63")){
                    map.put("gametime", "15 دقیقه هفتم");
                }
                else if (gameTime.getGameTime().equals("72")){
                    map.put("gametime", "15 دقیقه هشتم");
                }
                else if (gameTime.getGameTime().equals("81")){
                    map.put("gametime", "15 دقیقه نهم");
                }
                map.put("Code", gameTime.getCode());
                map.put("gameTimeCode", gameTime.getGameTime());
                Items.add(map);
            }
            if (Items.isEmpty()){

            }
            else {
                ListAdapter adapter = new SimpleAdapter(getActivity(), Items,
                        R.layout.record_sort,
                        new String[]{
                                "gname", "gametime", "Code", "gameTimeCode"
                        },
                        new int[]{
                                R.id.tvpGroupName, R.id.tvpRecord, R.id.tvpCode2, R.id.tvpCode3
                        });

                gvGameTime.setAdapter(adapter);
                gvGameTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextViewPlus tvpCode = view.findViewById(R.id.tvpCode);
                        TextViewPlus tvpCode2 = view.findViewById(R.id.tvpCode2);
                        TextViewPlus tvpCode3 = view.findViewById(R.id.tvpCode3);
                        GenerateCode generateCode = new GenerateCode();
                        tvpCode.setText(generateCode.GenerateGameTime(57, tvpCode2.getText().toString(), tvpCode3.getText().toString()));
                    }
                });
            }
        }
        else {
            Grouping();
            setGameTime();
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

    private void Grouping(){

        int GroupCount = dbHandler.GetGroupCount(WeekID);
        String[] groupName = dbHandler.GetGroupName(WeekID);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<=GroupCount; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        Log.d("WeekID ", String.valueOf(WeekID));
        int[] gAdded = new int[GroupCount];
        for (int i = 0 ; i<GroupCount; i++){
            gAdded[i] = list.get(i);
        }
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
            if (GroupCount <= 4){
                for (int i=0;i<GroupCount;i++) {
                    if (i < 2) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"09"));
                    }
                    else {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"18"));
                    }
                }
            }
            else if (GroupCount <= 7){
                Log.d("s",String.valueOf(GroupCount));
                for (int i=0;i<GroupCount;i++) {
                    if (i < 3) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"09"));
                    }
                    else if (i >= 3 && i <5) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"18"));
                    }
                    else {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i],"27"));
                    }
                }
            }
            else if (GroupCount <= 10){
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
            else if (GroupCount <= 13){
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
            else if (GroupCount <= 16){
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
            else if (GroupCount <= 19){
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
            else if (GroupCount <= 22){
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
            else if (GroupCount <= 25){
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
            if (GroupCount <= 5){
                for (int i=0;i<GroupCount;i++) {
                    if (i < 3) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                    }
                    else {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                    }
                }
            }
            else if (GroupCount <= 8){
                for (int i=0;i<GroupCount;i++) {
                    if (i < 3) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                    }
                    else if (i >= 3 && i < 6) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                    }
                    else {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"27"));
                    }
                }
            }
            else if (GroupCount <= 11){
                for (int i=0;i<GroupCount;i++) {
                    if (i < 3) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                    }
                    else if (i >= 3 && i < 6) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                    }
                    else if (i >= 6 && i < 9) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"27"));
                    }
                    else {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"36"));
                    }
                }
            }
            else if (GroupCount <= 14){
                for (int i=0;i<GroupCount;i++) {
                    if (i < 3) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                    }
                    else if (i >= 3 && i < 6) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                    }
                    else if (i >= 6 && i < 9) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"27"));
                    }
                    else if (i >= 9 && i < 12) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"36"));
                    }
                    else {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"45"));
                    }
                }
            }
            else if (GroupCount <= 17){
                for (int i=0;i<GroupCount;i++) {
                    if (i < 3) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                    }
                    else if (i >= 3 && i < 6) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                    }
                    else if (i >= 6 && i < 9) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"27"));
                    }
                    else if (i >= 9 && i < 12) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"36"));
                    }
                    else if (i >= 12 && i < 15) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"45"));
                    }
                    else {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"54"));
                    }
                }
            }
            else if (GroupCount <= 20){
                for (int i=0;i<GroupCount;i++) {
                    if (i < 3) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                    }
                    else if (i >= 3 && i < 6) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                    }
                    else if (i >= 6 && i < 9) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"27"));
                    }
                    else if (i >= 9 && i < 12) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"36"));
                    }
                    else if (i >= 12 && i < 15) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"45"));
                    }
                    else if (i >= 15 && i < 18) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"54"));
                    }
                    else {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"63"));
                    }
                }
            }
            else if (GroupCount <= 23){
                for (int i=0;i<GroupCount;i++) {
                    if (i < 3) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"09"));
                    }
                    else if (i >= 3 && i < 6) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"18"));
                    }
                    else if (i >= 6 && i < 9) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"27"));
                    }
                    else if (i >= 9 && i < 12) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"36"));
                    }
                    else if (i >= 12 && i < 15) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"45"));
                    }
                    else if (i >= 15 && i < 18) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"54"));
                    }
                    else if (i >= 18 && i < 21) {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"63"));
                    }
                    else {
                        dbHandler.AddGameTime(new GameTime(WeekID,gAdded[i] ,"72"));
                    }
                }
            }
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
