package Model;

public class Record {
    private int ID;
    private String Record1, Record2, Record3, GPName, GenCode;

    public Record(int ID, String record1, String record2, String record3, String GPName, String genCode) {
        this.ID = ID;
        Record1 = record1;
        Record2 = record2;
        Record3 = record3;
        this.GPName = GPName;
        GenCode = genCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRecord1() {
        return Record1;
    }

    public void setRecord1(String record1) {
        Record1 = record1;
    }

    public String getRecord2() {
        return Record2;
    }

    public void setRecord2(String record2) {
        Record2 = record2;
    }

    public String getRecord3() {
        return Record3;
    }

    public void setRecord3(String record3) {
        Record3 = record3;
    }

    public String getGPName() {
        return GPName;
    }

    public void setGPName(String GPName) {
        this.GPName = GPName;
    }

    public String getGenCode() {
        return GenCode;
    }

    public void setGenCode(String genCode) {
        GenCode = genCode;
    }
}
