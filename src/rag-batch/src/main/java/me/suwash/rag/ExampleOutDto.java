package me.suwash.rag;

public class ExampleOutDto {
    private String boundary;
    private String controller1;
    private String dataStore;

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public String getController1() {
        return controller1;
    }

    public void setController1(String controller1) {
        this.controller1 = controller1;
    }

    public String getDataStore() {
        return dataStore;
    }

    public void setDataStore(String dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public String toString() {
        return "BOUNDARY:" + boundary + ", CTRL1: " + controller1 + ", DATASTORE:" + dataStore;
    }

}
