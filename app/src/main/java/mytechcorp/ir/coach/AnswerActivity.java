package mytechcorp.ir.coach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AnswerActivity extends AppCompatActivity {

    private Spinner spWeek;
    private TextViewPlus tvpAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        spWeek = findViewById(R.id.spWeek);
        tvpAnswer = findViewById(R.id.tvpAnswer);
        try{
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,new String[]{"هفته اول" ,"هفته دوم","هفته سوم","هفته چهارم","هفته پنجم","هفته ششم"});
            spWeek.setAdapter(adapter);
            spWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    switch (position){
                        case 0:
                            tvpAnswer.setText(R.string.w1);
                            break;
                        case 1:
                            tvpAnswer.setText(R.string.w2);
                            break;
                        case 2:
                            tvpAnswer.setText(R.string.w3);
                            break;
                        case 3:
                            tvpAnswer.setText(R.string.w4);
                            break;
                        case 4:
                            tvpAnswer.setText(R.string.w5);
                            break;
                        case 5:
                            tvpAnswer.setText(R.string.w6);
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    
                }

            });

        }catch (Exception ex){
            Log.d("err", ex.getMessage());
        }
    }
}
