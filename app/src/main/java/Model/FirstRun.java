package Model;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

public class FirstRun {

    SQLiteDatabase db;
    DBHandler dbHandler;

    public void AddGroupNames(Activity a){
        dbHandler = new DBHandler(a);
        dbHandler.AddGroup(new Groups("گروه ۱"));
        dbHandler.AddGroup(new Groups("گروه ۲"));
        dbHandler.AddGroup(new Groups("گروه ۳"));
        dbHandler.AddGroup(new Groups("گروه ۴"));
        dbHandler.AddGroup(new Groups("گروه ۵"));
        dbHandler.AddGroup(new Groups("گروه ۶"));
        dbHandler.AddGroup(new Groups("گروه ۷"));
        dbHandler.AddGroup(new Groups("گروه ۸"));
        dbHandler.AddGroup(new Groups("گروه ۹"));
        dbHandler.AddGroup(new Groups("گروه ۱۰"));
        dbHandler.AddGroup(new Groups("گروه ۱۱"));
        dbHandler.AddGroup(new Groups("گروه ۱۲"));
        dbHandler.AddGroup(new Groups("گروه ۱۳"));
        dbHandler.AddGroup(new Groups("گروه ۱۴"));
        dbHandler.AddGroup(new Groups("گروه ۱۵"));
        dbHandler.AddGroup(new Groups("گروه ۱۶"));
        dbHandler.AddGroup(new Groups("گروه ۱۷"));
        dbHandler.AddGroup(new Groups("گروه ۱۸"));
        dbHandler.AddGroup(new Groups("گروه ۱۹"));
        dbHandler.AddGroup(new Groups("گروه ۲۰"));
        dbHandler.AddGroup(new Groups("گروه ۲۱"));
        dbHandler.AddGroup(new Groups("گروه ۲۲"));
        dbHandler.AddGroup(new Groups("گروه ۲۳"));
        dbHandler.AddGroup(new Groups("گروه ۲۴"));
        dbHandler.AddGroup(new Groups("گروه ۲۵"));
    }
}
