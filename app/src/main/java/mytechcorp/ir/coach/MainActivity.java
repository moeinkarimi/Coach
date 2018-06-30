package mytechcorp.ir.coach;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import Fragments.GetFileFragment;
import Fragments.GroupGameFragment;
import Fragments.Maket;
import Fragments.MostanadFragment;
import Fragments.RecordFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        tf = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile_Light.ttf");
        navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.groupgame);
        transaction.replace(R.id.content, new GroupGameFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.groupgame:
                    transaction.replace(R.id.content, new GroupGameFragment()).commit();
                    return true;
                case R.id.record:
                    transaction.replace(R.id.content, new RecordFragment()).commit();
                    return true;
                case R.id.build:
                    transaction.replace(R.id.content, new Maket()).commit();
                    return true;
                case R.id.mostanad:
                    transaction.replace(R.id.content, new MostanadFragment()).commit();
                    return true;
                case R.id.getfile:
                    transaction.replace(R.id.content, new GetFileFragment()).commit();
                    return true;
            }
            return false;
        }

    };

}
