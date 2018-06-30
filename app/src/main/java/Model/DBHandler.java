package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Coach";

    private static final String TABLE_Groups = "Groups";
    private static final String TABLE_Weeks = "Weeks";
    private static final String TABLE_Score = "Score";
    private static final String TABLE_GCode = "GCode";
    private static final String TABLE_Questions = "Questions";
    private static final String TABLE_Answers = "Answers";

    private static final String ID = "ID";
    private static final String GroupName = "GroupName";
    private static final String Week = "Week";
    private static final String GroupID = "GroupID";
    private static final String GameID = "GameID";
    private static final String Code = "Code";
    private static final String Score = "Score";
    private static final String Question = "Question";
    private static final String QuestionID = "QuestionID";
    private static final String Answer = "Answer";
    private static final String Flag = "Flag";

    public DBHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String dbexec = "CREATE TABLE IF NOT EXISTS "+ TABLE_Groups +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + GroupName + " Text);"; //1
        db.execSQL(dbexec);

        String dbexec1 = "CREATE TABLE IF NOT EXISTS "+ TABLE_Weeks +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + GroupID + " Integer," //1
                + Week + " Integer," //2
                + Code + " Text," //3
                + Score + " Integer);";//4
        db.execSQL(dbexec1);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1) {

    }

    //Groups
    public void AddGroup(Groups groups){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroupName, groups.getGroupName());
        db.insert(TABLE_Groups, null, values);
        db.close();
    }

    public void AddWeeks(Groups groups){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroupID, groups.getGroupID());
        values.put(Week, groups.getGroupsWeek());
        values.put(Code, groups.getGroupsCode());
        values.put(Score, groups.getGroupsScores());
        db.insert(TABLE_Weeks, null, values);
        db.close();
    }

    public boolean GetGroupAddedState(int WeekId, int groupId){
        String query = "SELECT * FROM " + TABLE_Weeks + " WHERE Week = " + WeekId + " And GroupID = " + groupId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            while (cursor.moveToFirst()) {
                if (cursor.getString(1).equals(groupId) && cursor.getString(2).equals(WeekId)) {
                    return true;
                } else
                    return true;
            }
        }
        return false;
    }

    public String[] GetGroupName(int WeekId){
        String query = "SELECT q.GroupName FROM " + TABLE_Groups +" q INNER JOIN " + TABLE_Weeks
                + " a ON q.ID = a.GroupID Where a.Week = "+WeekId;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] result;
        Cursor cursor = db.rawQuery(query,null);
        result = new String[cursor.getCount()];
        if (cursor.moveToFirst()) {
            int i = 0;

            do {
                result[i] = cursor.getString(0);
                i++;
            }while (cursor.moveToNext());
        }
        return result;
    }

    public String GetGroupCode(int id,int WeekId){
        String query = "SELECT Code FROM " + TABLE_Weeks + " Where GroupID = "+ id + " And Week = " + WeekId;
        SQLiteDatabase db = this.getReadableDatabase();
        String result="";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getString(0);
            }while (cursor.moveToNext());
        }
        return result;
    }
}
