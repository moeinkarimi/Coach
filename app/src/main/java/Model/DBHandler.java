package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Coach.db";

    private static final String TABLE_Groups = "Groups";
    private static final String TABLE_Weeks = "Weeks";
    private static final String TABLE_Record = "Record";
    private static final String TABLE_GameTime = "GameTime";
    private static final String TABLE_State = "State";

    private static final String ID = "ID";
    private static final String GroupName = "GroupName";
    private static final String Week = "Week";
    private static final String GroupID = "GroupID";
    private static final String Code = "Code";
    private static final String Score = "Score";
    private static final String Record1 = "Record1";
    private static final String Record2 = "Record2";
    private static final String Record3 = "Record3";
    private static final String GameTime = "GameTime";
    private static final String State = "State";

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

        String dbexec2 = "CREATE TABLE IF NOT EXISTS "+ TABLE_Record +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + GroupID + " Integer," //1
                + Week + " Integer," //2
                + Record1 + " Integer," //3
                + Record2 + " Integer," //4
                + Record3 + " Integer);";//5
        db.execSQL(dbexec2);

        String dbexec3 = "CREATE TABLE IF NOT EXISTS "+ TABLE_GameTime +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + GroupID + " Integer," //1
                + Week + " Integer," //2
                + GameTime + " Text);";//5
        db.execSQL(dbexec3);

        String dbexec4 = "CREATE TABLE IF NOT EXISTS "+ TABLE_State +"("
                + ID + " Integer Primary Key Autoincrement," //0
                + GroupID + " Integer," //1
                + Week + " Integer,"
                + State + " Integer);";
        db.execSQL(dbexec4);
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

    public void UpdateScore(Groups groups){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Score, groups.getGroupsScores());

        db.update(TABLE_Weeks,values,"Week =? AND GroupID =?",new String[] {String.valueOf(groups.getGroupsWeek()), String.valueOf(groups.getGroupID())});
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
                    return false;
            }
        }
        return false;
    }

    public int GetGroupCount(int WeekID){
        String query = "SELECT * FROM " + TABLE_Weeks + " WHERE Week = " + WeekID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
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



    public String[] GetGroupNameIfHasScore(int WeekId){
        String query = "SELECT q.GroupName FROM " + TABLE_Groups +" q INNER JOIN " + TABLE_Weeks
                + " a ON q.ID = a.GroupID Where a.Week = "+WeekId + " And a.Score <> 0 ";
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

    public int GetSumOfScore(int groupID, int week){
        String query = "SELECT Sum(Score) FROM " + TABLE_Weeks + " Where GroupID = "+ groupID + " And Week = " + week;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                return Integer.parseInt(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        else return 0;
    }

    public int GetSumOfScore1(int groupID){
        String query = "SELECT Sum(Score) FROM " + TABLE_Weeks + " Where GroupID = "+ groupID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                return Integer.parseInt(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        else return 0;
    }

    //Record
    public void AddRecord(Record record){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroupID, record.getGroupID());
        values.put(Week, record.getWeek());
        values.put(Record1, record.getRecord1());
        values.put(Record2, record.getRecord2());
        values.put(Record3, record.getRecord3());
        db.insert(TABLE_Record, null, values);
        db.close();
    }

    public List<Record> getAllRecord(int weekId){
        List<Record> recordList = new ArrayList<Record>();
        String query = "SELECT G.GroupName, Case WHEN Record1>= Record2 AND Record1>=Record3 THEN Record1 "
                + "WHEN Record2>= Record1 AND Record2>=Record3 THEN Record2 "
                + "WHEN Record3>= Record1 AND Record3>=Record2 THEN Record3 "
                + "WHEN Record1>= Record3 AND Record3>=Record2 THEN Record1 "
                + "WHEN Record2>= Record3 AND Record2>=Record1 THEN Record2 "
                + "ELSE Record3 END, W.Code FROM "
                + TABLE_Record + " S Inner Join " + TABLE_Groups
                + " G on S.GroupID = G.ID Inner Join " + TABLE_Weeks
                + " W on S.GroupID = W.ID Where S.Week = " + weekId
                + " Order By (Case WHEN Record1>= Record2 AND Record1>=Record3 THEN Record1 "
                + "WHEN Record2>= Record1 AND Record2>=Record3 THEN Record2 "
                + "WHEN Record3>= Record1 AND Record3>=Record2 THEN Record3 "
                + "WHEN Record1>= Record3 AND Record3>=Record2 THEN Record1 "
                + "WHEN Record2>= Record3 AND Record2>=Record1 THEN Record2 "
                + "ELSE Record3 END) Desc";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setGroupName(cursor.getString(0));
                record.setBestRecord(Integer.parseInt(cursor.getString(1)));
                record.setCode(cursor.getString(2));
                recordList.add(record);
            }while (cursor.moveToNext());
        }
        return recordList;
    }

    public String[] GetSelectedRecord(int WeekId, int GroupId){
        String query = "SELECT Record1, Record2, Record3 FROM " + TABLE_Record +" Where Week = " + WeekId + " And GroupID = " + GroupId;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] result;
        Cursor cursor = db.rawQuery(query,null);
        Log.d("table", cursor.toString());
        result = new String[3];
        if (cursor.moveToFirst()) {
            result[0] = cursor.getString(0);
            result[1] = cursor.getString(1);
            result[2] = cursor.getString(2);
        }
        return result;
    }

    public void UpdateRecord(Record record){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Record1, record.getRecord1());
        values.put(Record2, record.getRecord2());
        values.put(Record3, record.getRecord3());

        db.update(TABLE_Record,values,"Week =? AND GroupID =?",new String[] {String.valueOf(record.getWeek()), String.valueOf(record.getGroupID())});
        db.close();
    }

    //Group Game
    public void AddGameTime(GameTime gameTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d("w", String.valueOf(gameTime.getWeek()));
        values.put(Week, gameTime.getWeek());
        values.put(GroupID, gameTime.getGroupID());
        values.put(GameTime, gameTime.GameTime);
        db.insert(TABLE_GameTime, null, values);
        db.close();
    }

    public boolean CheckGameTime(int WeekID){
        String countQuery = "SELECT  * FROM " + TABLE_GameTime + " WHERE Week = "+ WeekID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public void DeleteGrouping(int WeekId){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_GameTime, Week + "=" + WeekId, null);
    }

    public List<GameTime> GetListOfGames(int WeekId){
        List<GameTime> gameTimesList = new ArrayList<GameTime>();
        String query = "SELECT G.GroupName , GT.GameTime , W.Code FROM Groups"
                + " G Inner Join GameTime GT on G.ID = GT.GroupID Inner Join Weeks "
                + "W on GT.GroupID = W.ID Where GT.Week = " + WeekId +" Order by GameTime";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                GameTime gameTime = new GameTime();
                gameTime.setGroupName(cursor.getString(0));
                gameTime.setGameTime(cursor.getString(1));
                gameTime.setCode(cursor.getString(2));
                gameTimesList.add(gameTime);
            }while (cursor.moveToNext());
        }
        return gameTimesList;
    }

    // State
    public void AddMosState(State state){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroupID, state.getGroupID());
        values.put(Week, state.getWeekID());
        values.put(State, state.getState());
        db.insert(TABLE_State, null, values);
        db.close();
    }
    public boolean GetMostanadState(int WeekId, int groupId){
        String query = "SELECT * FROM " + TABLE_State;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            while (cursor.moveToFirst()) {
                if (cursor.getString(1).equals(String.valueOf(WeekId))&&cursor.getString(2).equals(String.valueOf(groupId))&&cursor.getString(3).equals("1")) {
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }


    public void UpdateState(State state){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(State, state.getState());

        db.update(TABLE_State,values,"Week =? AND GroupID =?",new String[] {String.valueOf(state.getWeekID()), String.valueOf(state.getGroupID())});
        db.close();
    }
}
