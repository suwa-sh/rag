package me.suwash.rag.classification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.suwash.util.classification.Classification;

/**
 * 分析ステータス。
 */
public enum AnalyzeStatus implements Classification {
    /** 実行中 */
    Processing("PRC"),
    /** 成功 */
    Success("SUC"),
    /** 警告終了 */
    Warning("WRN"),
    /** エラー終了 */
    Error("ERR"),
    /** 中止 */
    Cancel("CNL");

    /** グループ名配列 */
    private static final String[] groups;
    /** 区分値グループMap */
    private static final Map<String, AnalyzeStatus[]> groupValuesMap;
    /** グループ内デフォルト区分値Map */
    private static final Map<String, AnalyzeStatus> groupDefaultMap;

    /** グループ：デフォルト */
    public static final String GROUP_DEFAULT = "default";

    static {
        // グループMap
        groupValuesMap = new HashMap<String, AnalyzeStatus[]>();
        groupValuesMap.put(GROUP_DEFAULT, new AnalyzeStatus[]{
            Processing,
            Success,
            Warning,
            Error,
            Cancel
            });

        // グループ内デフォルト値Map
        groupDefaultMap = new HashMap<String, AnalyzeStatus>();
        groupDefaultMap.put(GROUP_DEFAULT, Processing);

        // グループ名配列
        groups = groupValuesMap.keySet().toArray(new String[0]);
    }

    /**
     * デフォルト区分値を返します。
     *
     * @return デフォルト区分値
     */
    public static AnalyzeStatus defaultValue() {
        return groupDefaultMap.get(GROUP_DEFAULT);
    }

    /**
     * グループ内のデフォルト区分値を返します。
     *
     * @param group グループ名
     * @return デフォルトの区分値
     */
    public static AnalyzeStatus defaultValue(String group) {
        return groupDefaultMap.get(group);
    }

    /**
     * 区分が持つグループ群を返します。
     * @return グループ名配列
     */
    public static String[] groups() {
        return Arrays.copyOf(groups, groups.length);
    }

    /**
     * 指定したグループ名に属する区分値を返します。
     * @param group グループ名
     * @return 区分値配列
     */
    public static AnalyzeStatus[] values(String group) {
        return groupValuesMap.get(group);
    }

    /**
     * データディクショナリIDから区分値を返します。
     * 見つからない場合はnullを返します。
     *
     * @param ddId データディクショナリID
     * @return 区分値
     */
    public static AnalyzeStatus valueOfByDdId(String ddId) {
        for (AnalyzeStatus curEnum : AnalyzeStatus.values()) {
            if(curEnum.ddId().equals(ddId)) {
                return curEnum;
            }
        }
        return null;
    }

    /**
     * 永続化値から区分値を返します。
     * 見つからない場合はnullを返します。
     *
     * @param storeValue 永続化値
     * @return 区分値
     */
    public static AnalyzeStatus valueOfByStoreValue(String storeValue) {
        for (AnalyzeStatus curEnum : AnalyzeStatus.values()) {
            if(curEnum.storeValue().equals(storeValue)) {
                return curEnum;
            }
        }
        return null;
    }

    /**
     * 区分内に、指定した区分値名が存在するか確認します。
     *
     * @param name 区分値名
     * @return 存在する場合 true
     */
    public static boolean containsName(String name) {
        for (AnalyzeStatus curEnum : AnalyzeStatus.values()) {
            if(curEnum.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 区分内の、指定したグループに、指定した区分値名が存在するか確認します。
     *
     * @param group グループ名
     * @param name 区分値名
     * @return 存在する場合 true
     */
    public static boolean containsName(String group, String name) {
        for (AnalyzeStatus curEnum : AnalyzeStatus.values(group)) {
            if(curEnum.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 区分内に、指定したデータディクショナリIDが存在するか確認します。
     *
     * @param ddId データディクショナリID
     * @return 存在する場合 true
     */
    public static boolean containsDdId(String ddId) {
        for (AnalyzeStatus curEnum : AnalyzeStatus.values()) {
            if(curEnum.ddId().equals(ddId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 区分内の、指定したグループに、指定したデータディクショナリIDが存在するか確認します。
     *
     * @param group グループ名
     * @param ddId データディクショナリID
     * @return 存在する場合 true
     */
    public static boolean containsDdId(String group, String ddId) {
        for (AnalyzeStatus curEnum : AnalyzeStatus.values(group)) {
            if(curEnum.ddId().equals(ddId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 区分内に、指定した永続化値が存在するか確認します。
     *
     * @param storeValue 永続化値
     * @return 存在する場合 true
     */
    public static boolean containsStoreValue(String storeValue) {
        for (AnalyzeStatus curEnum : AnalyzeStatus.values()) {
            if(curEnum.storeValue().equals(storeValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 区分内の、指定したグループに、指定した永続化値が存在するか確認します。
     *
     * @param group グループ名
     * @param storeValue 永続化値
     * @return 存在する場合 true
     */
    public static boolean containsStoreValue(String group, String storeValue) {
        for (AnalyzeStatus curEnum : AnalyzeStatus.values(group)) {
            if(curEnum.storeValue().equals(storeValue)) {
                return true;
            }
        }
        return false;
    }


    /** データディクショナリID */
    private String ddId;
    /** 永続化値 */
    private String storeValue;

    /**
     * コンストラクタ。
     *
     * @param storeValue 永続化値
     */
    private AnalyzeStatus(String storeValue) {
        this.ddId = this.getClass().getSimpleName() + "." + name();
        this.storeValue = storeValue;
    }

    /* (非 Javadoc)
     * @see me.suwash.util.classification.Classification#ddId()
     */
    @Override
    public String ddId() {
        return ddId;
    }

    /* (非 Javadoc)
     * @see me.suwash.util.classification.Classification#storeValue()
     */
    @Override
    public String storeValue() {
        return storeValue;
    }

    /* (非 Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return ddId() + "(" + storeValue() + ")";
    }
}

