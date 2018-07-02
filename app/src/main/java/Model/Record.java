package Model;

public class Record {
    private int ID, GroupID, Week, Record1, Record2, Record3, BestRecord;
    private String GroupName, Code;

    public Record() {
    }

    public Record(int bestRecord, String groupName, String code) {
        BestRecord = bestRecord;
        GroupName = groupName;
        Code = code;
    }

    public Record(int groupID,int week,int record1,int record2,int record3) {
        GroupID = groupID;
        Week = week;
        Record1 = record1;
        Record2 = record2;
        Record3 = record3;
    }

    public Record(int ID,int groupID,int week,int record1,int record2,int record3) {
        this.ID = ID;
        GroupID = groupID;
        Week = week;
        Record1 = record1;
        Record2 = record2;
        Record3 = record3;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
    }

    public int getWeek() {
        return Week;
    }

    public void setWeek(int week) {
        Week = week;
    }

    public int getRecord1() {
        return Record1;
    }

    public void setRecord1(int record1) {
        Record1 = record1;
    }

    public int getRecord2() {
        return Record2;
    }

    public void setRecord2(int record2) {
        Record2 = record2;
    }

    public int getRecord3() {
        return Record3;
    }

    public void setRecord3(int record3) {
        Record3 = record3;
    }

    public int getBestRecord() {
        return BestRecord;
    }

    public void setBestRecord(int bestRecord) {
        BestRecord = bestRecord;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
