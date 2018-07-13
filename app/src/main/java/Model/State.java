package Model;

public class State {
    Integer ID, State, GroupID, WeekID;

    public State() {
    }

    public State(Integer state,Integer groupID,Integer weekID) {
        State = state;
        GroupID = groupID;
        WeekID = weekID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }

    public Integer getGroupID() {
        return GroupID;
    }

    public void setGroupID(Integer groupID) {
        GroupID = groupID;
    }

    public Integer getWeekID() {
        return WeekID;
    }

    public void setWeekID(Integer weekID) {
        WeekID = weekID;
    }
}
