package me.suwash.rag.sv.context.detail;


/**
 * 変数括り文字ルール。
 */
public class VarEncloseRule extends BaseRule {

    /** 括り開始文字 */
    private String prefix;
    /** 括り終了文字 */
    private String surfix;

    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public String getSurfix() {
        return surfix;
    }
    public void setSurfix(String surfix) {
        this.surfix = surfix;
    }

    /* (非 Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
        result = prime * result + ((surfix == null) ? 0 : surfix.hashCode());
        return result;
    }
    /* (非 Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        VarEncloseRule other = (VarEncloseRule) obj;
        if (prefix == null) {
            if (other.prefix != null) return false;
        } else if (!prefix.equals(other.prefix)) return false;
        if (surfix == null) {
            if (other.surfix != null) return false;
        } else if (!surfix.equals(other.surfix)) return false;
        return true;
    }

}
