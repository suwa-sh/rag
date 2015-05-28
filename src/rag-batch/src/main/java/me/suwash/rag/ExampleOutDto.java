package me.suwash.rag;

/**
 * TODO クラスの説明。
 */
public class ExampleOutDto {
    private String boundary;
    private String controller1;
    private String dataStore;

    /**
     * TODO メソッドのコメント。
     *
     * @return xxx
     */
    public String getBoundary() {
        return boundary;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @param boundary xxx
     */
    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @return xxx
     */
    public String getController1() {
        return controller1;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @param controller1 xxx
     */
    public void setController1(String controller1) {
        this.controller1 = controller1;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @return xxx
     */
    public String getDataStore() {
        return dataStore;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @param dataStore xxx
     */
    public void setDataStore(String dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public String toString() {
        return "BOUNDARY:" + boundary + ", CTRL1: " + controller1 + ", DATASTORE:" + dataStore;
    }

}
