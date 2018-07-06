package Model;

public class Groups {
    private int ID, GroupID, Scores, Week;
    private String Code, GroupName;

    public Groups() {
    }

    public Groups(String groupName) {
        GroupName = groupName;
    }

    public Groups(int groupID,int scores,int week) {
        GroupID = groupID;
        Scores = scores;
        Week = week;
    }

    public Groups(int groupID,int scores,int week,String code) {
        GroupID = groupID;
        Scores = scores;
        Week = week;
        Code = code;
    }

    public Groups(int ID,int groupID,int scores,int week,String code) {
        this.ID = ID;
        GroupID = groupID;
        Scores = scores;
        Week = week;
        Code = code;
    }

    public int getGroupsID() {
        return ID;
    }

    public void setGroupsID(int ID) {
        this.ID = ID;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
    }

    public int getGroupsScores() {
        return Scores;
    }

    public void setGroupsScores(int scores) {
        Scores = scores;
    }

    public int getGroupsWeek() {
        return Week;
    }

    public void setGroupsWeek(int week) {
        Week = week;
    }

    public String getGroupsCode() {
        return Code;
    }

    public void setGroupsCode(String code) {
        Code = code;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }
}
