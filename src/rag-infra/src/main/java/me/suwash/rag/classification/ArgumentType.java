package me.suwash.rag.classification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.suwash.util.classification.Classification;

/**
 * 引数タイプ。
 */
public enum ArgumentType implements Classification {
    /** 番号指定 */
    Number("NUM"),
    /** 名前指定 */
    Name("NAM"),
    /** 引数なし */
    None("NON");

    /** グループ名配列 */
    private static final String[] groups;
    /** 区分値グループMap */
    private static final Map<String, ArgumentType[]> groupValuesMap;
    /** グループ内デフォルト区分値Map */
    private static final Map<String, ArgumentType> groupDefaultMap;

    /** グループ：デフォルト */
    public static final String GROUP_DEFAULT = "default";

    static {
        // グループMap
        groupValuesMap = new HashMap<String, ArgumentType[]>();
        groupValuesMap.put(GROUP_DEFAULT, new ArgumentType[]{
            Number,
            Name,
            None
            });

        // グループ内デフォルト値Map
        groupDefaultMap = new HashMap<String, ArgumentType>();
        groupDefaultMap.put(GROUP_DEFAULT, Number);

        // グループ名配列
        groups = groupValuesMap.keySet().toArray(new String[0]);
    }

    /**
     * デフォルト区分値を返します。
     *
     * @return デフォルト区分値
     */
    public static ArgumentType defaultValue() {
        return groupDefaultMap.get(GROUP_DEFAULT);
    }

    /**
     * グループ内のデフォルト区分値を返します。
     *
     * @param group グループ名
     * @return デフォルトの区分値
     */
    public static ArgumentType defaultValue(String group) {
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
    public static ArgumentType[] values(String group) {
        return groupValuesMap.get(group);
    }

    /**
     * データディクショナリIDから区分値を返します。
     * 見つからない場合はnullを返します。
     *
     * @param ddId データディクショナリID
     * @return 区分値
     */
    public static ArgumentType valueOfByDdId(String ddId) {
        for (ArgumentType curEnum : ArgumentType.values()) {
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
    public static ArgumentType valueOfByStoreValue(String storeValue) {
        for (ArgumentType curEnum : ArgumentType.values()) {
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
        for (ArgumentType curEnum : ArgumentType.values()) {
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
        for (ArgumentType curEnum : ArgumentType.values(group)) {
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
        for (ArgumentType curEnum : ArgumentType.values()) {
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
        for (ArgumentType curEnum : ArgumentType.values(group)) {
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
        for (ArgumentType curEnum : ArgumentType.values()) {
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
        for (ArgumentType curEnum : ArgumentType.values(group)) {
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
    private ArgumentType(String storeValue) {
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

