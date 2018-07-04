package Model;

public class GameTime {
    int ID, Week, GroupID;
    String GameTime, GroupName, Code;

    public GameTime() {
    }

    public GameTime(int week,int groupID,String gameTime) {
        Week = week;
        GroupID = groupID;
        GameTime = gameTime;
    }

    public GameTime(String gameTime, String groupName, String code) {
        GameTime = gameTime;
        GroupName = groupName;
        Code = code;
    }

    public GameTime(int ID, int week, int groupID, String gameTime) {
        this.ID = ID;
        Week = week;
        GroupID = groupID;
        GameTime = gameTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getWeek() {
        return Week;
    }

    public void setWeek(int week) {
        Week = week;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
    }

    public String getGameTime() {
        return GameTime;
    }

    public void setGameTime(String gameTime) {
        GameTime = gameTime;
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
