package mytechcorp.ir.coach;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

import Model.DBHandler;
import Model.FirstRun;
import Model.Groups;
import Model.Record;

public class EnterActivity extends AppCompatActivity {

    TextViewPlus tvpGp1,tvpGp2,tvpGp3,tvpGp4,tvpGp5,tvpGp6,tvpGp7,tvpGp8,tvpGp9,tvpGp10,tvpGp11,
            tvpGp12,tvpGp13,tvpGp14,tvpGp15,tvpGp16,tvpGp17,tvpGp18,tvpGp19,tvpGp20,tvpGp21,tvpGp22,
            tvpGp23,tvpGp24,tvpGp25;
    CheckBox cbxGp1,cbxGp2,cbxGp3,cbxGp4,cbxGp5,cbxGp6,cbxGp7,cbxGp8,cbxGp9,cbxGp10,cbxGp11,cbxGp12,
            cbxGp13,cbxGp14,cbxGp15,cbxGp16,cbxGp17,cbxGp18,cbxGp19,cbxGp20,cbxGp21,cbxGp22,cbxGp23,
            cbxGp24,cbxGp25;
    Spinner spWeek;
    Button btnEnter, btnSaveEnter;
    private static final String TABLE_Groups = "Groups";
    private Typeface tf;
    DBHandler dbHandler;
    SQLiteDatabase db;
    Activity ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ta= this;
        dbHandler = new DBHandler(this);
        tf = Typeface.createFromAsset(this.getAssets(),"fonts/IRANSansMobile_Light.ttf");
        spWeek = findViewById(R.id.spWeek);
        btnEnter = findViewById(R.id.btnEnter);
        btnSaveEnter = findViewById(R.id.btnSaveEnter);
        tvpGp1 = findViewById(R.id.tvpGp1);
        tvpGp2 = findViewById(R.id.tvpGp2);
        tvpGp3 = findViewById(R.id.tvpGp3);
        tvpGp4 = findViewById(R.id.tvpGp4);
        tvpGp5 = findViewById(R.id.tvpGp5);
        tvpGp6 = findViewById(R.id.tvpGp6);
        tvpGp7 = findViewById(R.id.tvpGp7);
        tvpGp8 = findViewById(R.id.tvpGp8);
        tvpGp9 = findViewById(R.id.tvpGp9);
        tvpGp10 = findViewById(R.id.tvpGp10);
        tvpGp11 = findViewById(R.id.tvpGp11);
        tvpGp12 = findViewById(R.id.tvpGp12);
        tvpGp13 = findViewById(R.id.tvpGp13);
        tvpGp14 = findViewById(R.id.tvpGp14);
        tvpGp15 = findViewById(R.id.tvpGp15);
        tvpGp16 = findViewById(R.id.tvpGp16);
        tvpGp17 = findViewById(R.id.tvpGp17);
        tvpGp18 = findViewById(R.id.tvpGp18);
        tvpGp19 = findViewById(R.id.tvpGp19);
        tvpGp20 = findViewById(R.id.tvpGp20);
        tvpGp21 = findViewById(R.id.tvpGp21);
        tvpGp22 = findViewById(R.id.tvpGp22);
        tvpGp23 = findViewById(R.id.tvpGp23);
        tvpGp24 = findViewById(R.id.tvpGp24);
        tvpGp25 = findViewById(R.id.tvpGp25);
        cbxGp1  = findViewById(R.id.cbxGp1);
        cbxGp2  = findViewById(R.id.cbxGp2);
        cbxGp3  = findViewById(R.id.cbxGp3);
        cbxGp4  = findViewById(R.id.cbxGp4);
        cbxGp5  = findViewById(R.id.cbxGp5);
        cbxGp6  = findViewById(R.id.cbxGp6);
        cbxGp7  = findViewById(R.id.cbxGp7);
        cbxGp8  = findViewById(R.id.cbxGp8);
        cbxGp9  = findViewById(R.id.cbxGp9);
        cbxGp10 = findViewById(R.id.cbxGp10);
        cbxGp11 = findViewById(R.id.cbxGp11);
        cbxGp12 = findViewById(R.id.cbxGp12);
        cbxGp13 = findViewById(R.id.cbxGp13);
        cbxGp14 = findViewById(R.id.cbxGp14);
        cbxGp15 = findViewById(R.id.cbxGp15);
        cbxGp16 = findViewById(R.id.cbxGp16);
        cbxGp17 = findViewById(R.id.cbxGp17);
        cbxGp18 = findViewById(R.id.cbxGp18);
        cbxGp19 = findViewById(R.id.cbxGp19);
        cbxGp20 = findViewById(R.id.cbxGp20);
        cbxGp21 = findViewById(R.id.cbxGp21);
        cbxGp22 = findViewById(R.id.cbxGp22);
        cbxGp23 = findViewById(R.id.cbxGp23);
        cbxGp24 = findViewById(R.id.cbxGp24);
        cbxGp25 = findViewById(R.id.cbxGp25);

        btnEnter.setTypeface(tf);
        btnSaveEnter.setTypeface(tf);
        cbxGp1.setTypeface(tf);
        cbxGp2.setTypeface(tf);
        cbxGp3.setTypeface(tf);
        cbxGp4.setTypeface(tf);
        cbxGp5.setTypeface(tf);
        cbxGp6.setTypeface(tf);
        cbxGp7.setTypeface(tf);
        cbxGp8.setTypeface(tf);
        cbxGp9.setTypeface(tf);
        cbxGp10.setTypeface(tf);
        cbxGp11.setTypeface(tf);
        cbxGp12.setTypeface(tf);
        cbxGp13.setTypeface(tf);
        cbxGp14.setTypeface(tf);
        cbxGp15.setTypeface(tf);
        cbxGp16.setTypeface(tf);
        cbxGp17.setTypeface(tf);
        cbxGp18.setTypeface(tf);
        cbxGp19.setTypeface(tf);
        cbxGp20.setTypeface(tf);
        cbxGp21.setTypeface(tf);
        cbxGp22.setTypeface(tf);
        cbxGp23.setTypeface(tf);
        cbxGp24.setTypeface(tf);
        cbxGp25.setTypeface(tf);
        cbxGPsEnables();
        firstRun();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,new String[]{"هفته اول" ,"هفته دوم","هفته سوم","هفته چهارم","هفته پنجم","هفته ششم"});
        spWeek.setAdapter(adapter);

        btnSaveEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    cbxsGp();
                Intent intent = new Intent(ta , MainActivity.class);
                intent.putExtra("Week" , spWeek.getSelectedItemPosition()+1);
                startActivity(intent);
            }
        });
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ta , MainActivity.class);
                intent.putExtra("Week" , spWeek.getSelectedItemPosition()+1);
                startActivity(intent);
            }
        });
    }

    public void cbxGPsEnables(){

        // 1
        cbxGp1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if (cbxGp1.isChecked()){
                    cbxGp2.setEnabled(true);
                    tvpGp1.setText("76");
                }
                else {
                    cbxGp2.setEnabled(false);
                    tvpGp1.setText("");
                }
            }
        });
        // 2
        cbxGp2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if (cbxGp2.isChecked()) {
                    cbxGp3.setEnabled(true);
                    tvpGp2.setText("11");
                } else {
                    cbxGp3.setEnabled(false);
                    tvpGp2.setText("");
                }
            }
        });
        // 3
        cbxGp3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
        if (cbxGp3.isChecked()){
            cbxGp4.setEnabled(true);
            tvpGp3.setText("84");
        }
        else {
            cbxGp4.setEnabled(false);
            tvpGp3.setText("");
        }
            }
        });
        // 4
        cbxGp4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if (cbxGp4.isChecked()){
                    cbxGp5.setEnabled(true);
                    tvpGp4.setText("41");
                }
                else {
                    cbxGp5.setEnabled(false);
                    tvpGp4.setText("");
                }
            }
        });
        // 5
        cbxGp5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if (cbxGp5.isChecked()){
                    cbxGp6.setEnabled(true);
                    tvpGp5.setText("36");
                }
                else {
                    cbxGp6.setEnabled(false);
                    tvpGp5.setText("");
                }
            }
        });
        // 6
        cbxGp6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if (cbxGp6.isChecked()){
                    cbxGp7.setEnabled(true);
                    tvpGp6.setText("57");
                }
                else {
                    cbxGp7.setEnabled(false);
                    tvpGp6.setText("");
                }
            }
        });
        // 7
        cbxGp7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if (cbxGp7.isChecked()){
                    cbxGp8.setEnabled(true);
                    tvpGp7.setText("21");
                }
                else {
                    cbxGp8.setEnabled(false);
                    tvpGp7.setText("");
                }
            }
        });
        // 8
        cbxGp8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if (cbxGp8.isChecked()){
                    cbxGp9.setEnabled(true);
                    tvpGp8.setText("78");
                }
                else {
                    cbxGp9.setEnabled(false);
                    tvpGp8.setText("");
                }

            }
        });
        // 9
        cbxGp9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp9.isChecked()){
                    cbxGp10.setEnabled(true);
                    tvpGp9.setText("38");
                }
                else {
                    cbxGp10.setEnabled(false);
                    tvpGp9.setText("");
                }
            }
        });
        // 10
        cbxGp10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp10.isChecked()){
                    cbxGp11.setEnabled(true);
                    tvpGp10.setText("55");
                }
                else {
                    cbxGp11.setEnabled(false);
                    tvpGp10.setText("");
                }
            }
        });
        // 11
        cbxGp11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp11.isChecked()){
                    cbxGp12.setEnabled(true);
                    tvpGp11.setText("22");
                }
                else {
                    cbxGp12.setEnabled(false);
                    tvpGp11.setText("");
                }
            }
        });
        // 12
        cbxGp12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp12.isChecked()){
                    cbxGp13.setEnabled(true);
                    tvpGp12.setText("42");
                }
                else {
                    cbxGp13.setEnabled(false);
                    tvpGp12.setText("");
                }
            }
        });
        // 13
        cbxGp13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp13.isChecked()){
                    cbxGp14.setEnabled(true);
                    tvpGp13.setText("68");
                }
                else {
                    cbxGp14.setEnabled(false);
                    tvpGp13.setText("");
                }
            }
        });
        // 14
        cbxGp14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp14.isChecked()){
                    cbxGp15.setEnabled(true);
                    tvpGp14.setText("18");
                }
                else {
                    cbxGp15.setEnabled(false);
                    tvpGp14.setText("");
                }
            }
        });
        // 15
        cbxGp15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp15.isChecked()){
                    cbxGp16.setEnabled(true);
                    tvpGp15.setText("67");
                }
                else {
                    cbxGp16.setEnabled(false);
                    tvpGp15.setText("");
                }
            }
        });
        // 16
        cbxGp16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp16.isChecked()){
                    cbxGp17.setEnabled(true);
                    tvpGp16.setText("54");
                }
                else {
                    cbxGp17.setEnabled(false);
                    tvpGp16.setText("");
                }
            }
        });
        // 17
        cbxGp17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp17.isChecked()){
                    cbxGp18.setEnabled(true);
                    tvpGp17.setText("97");
                }
                else {
                    cbxGp18.setEnabled(false);
                    tvpGp17.setText("");
                }
            }
        });
        // 18
        cbxGp18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp18.isChecked()){
                    cbxGp19.setEnabled(true);
                    tvpGp18.setText("30");
                }
                else {
                    cbxGp19.setEnabled(false);
                    tvpGp18.setText("");
                }
            }
        });
        // 19
        cbxGp19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp19.isChecked()){
                    cbxGp20.setEnabled(true);
                    tvpGp19.setText("28");
                }
                else {
                    cbxGp20.setEnabled(false);
                    tvpGp19.setText("");
                }
            }
        });
        // 20
        cbxGp20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp20.isChecked()){
                    cbxGp21.setEnabled(true);
                    tvpGp20.setText("15");
                }
                else {
                    cbxGp21.setEnabled(false);
                    tvpGp20.setText("");
                }
            }
        });
        // 21
        cbxGp21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp21.isChecked()){
                    cbxGp22.setEnabled(true);
                    tvpGp21.setText("52");
                }
                else {
                    cbxGp22.setEnabled(false);
                    tvpGp21.setText("");
                }
            }
        });
        // 22
        cbxGp22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {

                if (cbxGp22.isChecked()){
                    cbxGp23.setEnabled(true);
                    tvpGp22.setText("46");
                }
                else {
                    cbxGp23.setEnabled(false);
                    tvpGp22.setText("");
                }
            }
        });
        // 23
        cbxGp23.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if (cbxGp23.isChecked()){
                    cbxGp24.setEnabled(true);
                    tvpGp23.setText("25");
                }
                else {
                    cbxGp24.setEnabled(false);
                    tvpGp23.setText("");
                }
            }
        });
        // 24
        cbxGp24.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if (cbxGp24.isChecked()){
                    cbxGp25.setEnabled(true);
                    tvpGp24.setText("33");
                }
                else {
                    cbxGp25.setEnabled(false);
                    tvpGp24.setText("");
                }
            }
        });
        // 25
        cbxGp25.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if (cbxGp25.isChecked()){
                    tvpGp25.setText("14");
                }
                else {
                    tvpGp25.setText("");
                }
            }
        });

    }

    public void cbxsGp(){
        // 1
        if (cbxGp1.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 1)) {
                dbHandler.AddWeeks(
                        new Groups(
                                1,
                                0,
                                spWeek.getSelectedItemPosition() + 1,
                                tvpGp1.getText().toString()
                        )
                );
                dbHandler.AddRecord(
                        new Record(
                                1,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 1)){
                Toast.makeText(this, "گروه 1 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 2
        if (cbxGp2.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 2)){
            dbHandler.AddWeeks(
                    new Groups(
                            2,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp2.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                2,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 2)){
                Toast.makeText(this, "گروه 2 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 3
        if (cbxGp3.isChecked()){
            if (!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 3)){
            dbHandler.AddWeeks(
                    new Groups(
                            3,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp3.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                3,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 2)){
                Toast.makeText(this, "گروه 3 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 4
        if (cbxGp4.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 4)){
            dbHandler.AddWeeks(
                    new Groups(
                            4,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp4.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                4,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 4)){
                Toast.makeText(this, "گروه 4 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 5
        if (cbxGp5.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 5)){
            dbHandler.AddWeeks(
                    new Groups(
                            5,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp5.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                5,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 5)){
                Toast.makeText(this, "گروه 5 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 6
        if (cbxGp6.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 6)){
            dbHandler.AddWeeks(
                    new Groups(
                            6,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp6.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                6,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 6)){
                Toast.makeText(this, "گروه 6 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 7
        if (cbxGp7.isChecked()){
            if (!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 7)){
            dbHandler.AddWeeks(
                    new Groups(
                            7,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp7.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                7,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 7)){
                Toast.makeText(this, "گروه 7 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 8
        if (cbxGp8.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 8)){
            dbHandler.AddWeeks(
                    new Groups(
                            8,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp8.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                8,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 8)){
                Toast.makeText(this, "گروه 8 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 9
        if (cbxGp9.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 9)){
            dbHandler.AddWeeks(
                    new Groups(
                            9,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp9.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                9,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 9)){
                Toast.makeText(this, "گروه 9 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 10
        if (cbxGp10.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 10)){
            dbHandler.AddWeeks(
                    new Groups(
                            10,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp10.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                10,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 10)){
                Toast.makeText(this, "گروه 10 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 11
        if (cbxGp11.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 11)){
            dbHandler.AddWeeks(
                    new Groups(
                            11,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp11.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                11,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 11)){
                Toast.makeText(this, "گروه 11 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 12
        if (cbxGp12.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 12)){
            dbHandler.AddWeeks(
                    new Groups(
                            12,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp12.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                12,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 12)){
                Toast.makeText(this, "گروه 12 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 13
        if (cbxGp13.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 13)){
            dbHandler.AddWeeks(
                    new Groups(
                            13,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp13.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                13,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 13)){
                Toast.makeText(this, "گروه 13 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 14
        if (cbxGp14.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 14)){
            dbHandler.AddWeeks(
                    new Groups(
                            14,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp14.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                14,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 14)){
                Toast.makeText(this, "گروه 14 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 15
        if (cbxGp15.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 15)){
            dbHandler.AddWeeks(
                    new Groups(
                            15,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp15.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                15,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 15)){
                Toast.makeText(this, "گروه 15 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 16
        if (cbxGp16.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 16)){
            dbHandler.AddWeeks(
                    new Groups(
                            16,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp16.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                16,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 16)){
                Toast.makeText(this, "گروه 16 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 17
        if (cbxGp17.isChecked()){
            if (!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 17)){
            dbHandler.AddWeeks(
                    new Groups(
                            17,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp17.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                17,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 17)){
                Toast.makeText(this, "گروه 17 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 18
        if (cbxGp18.isChecked() ){
            if (!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 18)){
            dbHandler.AddWeeks(
                    new Groups(
                            18,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp18.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                18,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 18)){
                Toast.makeText(this, "گروه 18 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 19
        if (cbxGp19.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 19)){
            dbHandler.AddWeeks(
                    new Groups(
                            19,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp19.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                19,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 19)){
                Toast.makeText(this, "گروه 19 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 20
        if (cbxGp20.isChecked()){
            if (!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 20)){
            dbHandler.AddWeeks(
                    new Groups(
                            20,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp20.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                20,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 20)){
                Toast.makeText(this, "گروه 20 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 21
        if (cbxGp21.isChecked()){
            if (!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 21)){
            dbHandler.AddWeeks(
                    new Groups(
                            21,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp21.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                21,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 21)){
                Toast.makeText(this, "گروه 21 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 22
        if (cbxGp22.isChecked() ){
            if (!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 22)){
            dbHandler.AddWeeks(
                    new Groups(
                            22,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp22.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                22,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 22)){
                Toast.makeText(this, "گروه 22 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 23
        if (cbxGp23.isChecked()){
            if (!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 23)){
            dbHandler.AddWeeks(
                    new Groups(
                            23,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp23.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                23,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 23)){
                Toast.makeText(this, "گروه 23 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 24
        if (cbxGp24.isChecked()){
            if (!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 24)){
            dbHandler.AddWeeks(
                    new Groups(
                            24,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp24.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                24,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 24)){
                Toast.makeText(this, "گروه 24 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
        // 25
        if (cbxGp25.isChecked()){
            if(!dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 25)){
            dbHandler.AddWeeks(
                    new Groups(
                            25,
                            0,
                            spWeek.getSelectedItemPosition()+1,
                            tvpGp25.getText().toString()
                    )
            );
                dbHandler.AddRecord(
                        new Record(
                                25,
                                spWeek.getSelectedItemPosition() + 1,
                                0,
                                0,
                                0
                        )
                );
            }
            else if (dbHandler.GetGroupAddedState(spWeek.getSelectedItemPosition()+1, 25)){
                Toast.makeText(this, "گروه 25 قبلا اضافه شده", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void firstRun(){
        String query = "SELECT * FROM " + TABLE_Groups;
        db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        if (cursor.getCount() <= 0) {
            FirstRun firstRun = new FirstRun();
            firstRun.AddGroupNames(this);
        }
    }
}
