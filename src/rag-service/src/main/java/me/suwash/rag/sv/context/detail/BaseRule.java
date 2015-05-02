package me.suwash.rag.sv.context.detail;

public class BaseRule {
    protected String ruleName;

    public String getRuleName() {
        return ruleName;
    }

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
