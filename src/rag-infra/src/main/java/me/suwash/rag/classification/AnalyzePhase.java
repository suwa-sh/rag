package me.suwash.rag.classification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.suwash.util.classification.Classification;

/**
 * 分析フェーズ。
 */
public enum AnalyzePhase implements Classification {
    /** コンテキストマッチング。 */
    ContextMatching("CTM"),
    /** 入力チェック。 */
    Validate("VLD"),
    /** 操作マッチング。 */
    OperationMatching("OPM"),
    /** パス置換。 */
    ReplacePath("REP"),
    /** パース。 */
    Parse("PRS"),
    /** 走査。 */
    Walk("WLK"),
    /** 完了。 */
    Finished("Fin");

    /** グループ名配列。 */
    private static final String[] groups;
    /** 区分値グループMap。 */
    private static final Map<String, AnalyzePhase[]> groupValuesMap;
    /** グループ内デフォルト区分値Map。 */
    private static final Map<String, AnalyzePhase> groupDefaultMap;

    /** グループ：デフォルト。 */
    public static final String GROUP_DEFAULT = "default";

    static {
        // グループMap
        groupValuesMap = new HashMap<String, AnalyzePhase[]>();
        groupValuesMap.put(GROUP_DEFAULT, new AnalyzePhase[] {
            ContextMatching,
            Validate,
            OperationMatching,
            ReplacePath,
            Parse,
            Walk,
            Finished
        });

        // グループ内デフォルト値Map
        groupDefaultMap = new HashMap<String, AnalyzePhase>();
        groupDefaultMap.put(GROUP_DEFAULT, ContextMatching);

        // グループ名配列
        groups = groupValuesMap.keySet().toArray(new String[0]);
    }

    /**
     * デフォルト区分値を返します。
     *
     * @return デフォルト区分値
     */
    public static AnalyzePhase defaultValue() {
        return groupDefaultMap.get(GROUP_DEFAULT);
    }

    /**
     * グループ内のデフォルト区分値を返します。
     *
     * @param group グループ名
     * @return デフォルトの区分値
     */
    public static AnalyzePhase defaultValue(String group) {
        return groupDefaultMap.get(group);
    }

    /**
     * 区分が持つグループ群を返します。
     *
     * @return グループ名配列
     */
    public static String[] groups() {
        return Arrays.copyOf(groups, groups.length);
    }

    /**
     * 指定したグループ名に属する区分値を返します。
     *
     * @param group グループ名
     * @return 区分値配列
     */
    public static AnalyzePhase[] values(String group) {
        return groupValuesMap.get(group);
    }

    /**
     * データディクショナリIDから区分値を返します。
     * 見つからない場合はnullを返します。
     *
     * @param ddId データディクショナリID
     * @return 区分値
     */
    public static AnalyzePhase valueOfByDdId(String ddId) {
        for (AnalyzePhase curEnum : AnalyzePhase.values()) {
            if (curEnum.ddId().equals(ddId)) {
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
    public static AnalyzePhase valueOfByStoreValue(String storeValue) {
        for (AnalyzePhase curEnum : AnalyzePhase.values()) {
            if (curEnum.storeValue().equals(storeValue)) {
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
        for (AnalyzePhase curEnum : AnalyzePhase.values()) {
            if (curEnum.name().equals(name)) {
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
        for (AnalyzePhase curEnum : AnalyzePhase.values(group)) {
            if (curEnum.name().equals(name)) {
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
        for (AnalyzePhase curEnum : AnalyzePhase.values()) {
            if (curEnum.ddId().equals(ddId)) {
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
        for (AnalyzePhase curEnum : AnalyzePhase.values(group)) {
            if (curEnum.ddId().equals(ddId)) {
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
        for (AnalyzePhase curEnum : AnalyzePhase.values()) {
            if (curEnum.storeValue().equals(storeValue)) {
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
        for (AnalyzePhase curEnum : AnalyzePhase.values(group)) {
            if (curEnum.storeValue().equals(storeValue)) {
                return true;
            }
        }
        return false;
    }

    /** データディクショナリID。 */
    private String ddId;
    /** 永続化値。 */
    private String storeValue;

    /**
     * コンストラクタ。
     *
     * @param storeValue 永続化値
     */
    private AnalyzePhase(String storeValue) {
        this.ddId = this.getClass().getSimpleName() + "." + name();
        this.storeValue = storeValue;
    }

    /*
     * (非 Javadoc)
     * @see me.suwash.util.classification.Classification#ddId()
     */
    @Override
    public String ddId() {
        return ddId;
    }

    /*
     * (非 Javadoc)
     * @see me.suwash.util.classification.Classification#storeValue()
     */
    @Override
    public String storeValue() {
        return storeValue;
    }

    /*
     * (非 Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return ddId() + "(" + storeValue() + ")";
    }
}
