package mytechcorp.ir.coach;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import Fragments.GetFileFragment;
import Fragments.GroupGameFragment;
import Fragments.Maket;
import Fragments.MostanadFragment;
import Fragments.RecordFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    Typeface tf;
    private int WeekID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission_group.STORAGE}, 1);
        }
        try {

            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {
                WeekID = bundle.getInt("Week");
            }
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.groupgame);
        transaction.replace(R.id.content, new GroupGameFragment(WeekID)).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.groupgame:
                    transaction.replace(R.id.content, new GroupGameFragment(WeekID)).commit();
                    return true;
                case R.id.record:
                    transaction.replace(R.id.content, new RecordFragment(WeekID)).commit();
                    return true;
                case R.id.build:
                    transaction.replace(R.id.content, new Maket(WeekID)).commit();
                    return true;
                case R.id.mostanad:
                    transaction.replace(R.id.content, new MostanadFragment(WeekID)).commit();
                    return true;
                case R.id.getfile:
                    transaction.replace(R.id.content, new GetFileFragment(WeekID)).commit();
                    return true;
            }
            return false;
        }

    };

}
