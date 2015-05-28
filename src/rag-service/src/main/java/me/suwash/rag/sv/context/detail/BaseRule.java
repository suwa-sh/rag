package me.suwash.rag.sv.context.detail;

/**
 * TODO クラスの説明。
 */
public class BaseRule {
    /**
     * TODO フィールドの説明。
     */
    protected String ruleName;

    /**
     * TODO メソッドのコメント。
     *
     * @return xxx
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @param ruleName xxx
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /* (非 Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ruleName;
    }
}
