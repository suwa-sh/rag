package me.suwash.rag.sv.context;

import java.util.List;

import me.suwash.rag.sv.context.detail.PathReplaceRule;
import me.suwash.rag.sv.context.detail.VarEncloseRule;

/**
 * 分析コンテキスト。
 */
public class AnalyzeContext {
    /** 分析ID。 */
    private String analyzeId;
    /** 包含分析IDリスト。 */
    private List<String> includeAnalyzeIdList;

    /** Path置換ルールリスト。 */
    private List<PathReplaceRule> pathReplaceRuleList;
    /** 変数括り文字ルールリスト。 */
    private List<VarEncloseRule> varEncloseRuleList;

    /**
     * analyzeIdを返します。
     *
     * @return analyzeId
     */
    public String getAnalyzeId() {
        return this.analyzeId;
    }

    /**
     * analyzeIdを設定します。
     *
     * @param analyzeId analyzeId
     */
    public void setAnalyzeId(String analyzeId) {
        this.analyzeId = analyzeId;
    }

    /**
     * includeAnalyzeIdListを返します。
     *
     * @return includeAnalyzeIdList
     */
    public List<String> getIncludeAnalyzeIdList() {
        return this.includeAnalyzeIdList;
    }

    /**
     * includeAnalyzeIdListを設定します。
     *
     * @param includeAnalyzeIdList includeAnalyzeIdList
     */
    public void setIncludeAnalyzeIdList(List<String> includeAnalyzeIdList) {
        this.includeAnalyzeIdList = includeAnalyzeIdList;
    }

    /**
     * pathReplaceRuleListを返します。
     *
     * @return pathReplaceRuleList
     */
    public List<PathReplaceRule> getPathReplaceRuleList() {
        return this.pathReplaceRuleList;
    }

    /**
     * pathReplaceRuleListを設定します。
     *
     * @param pathReplaceRuleList pathReplaceRuleList
     */
    public void setPathReplaceRuleList(List<PathReplaceRule> pathReplaceRuleList) {
        this.pathReplaceRuleList = pathReplaceRuleList;
    }

    /**
     * varEncloseRuleListを返します。
     *
     * @return varEncloseRuleList
     */
    public List<VarEncloseRule> getVarEncloseRuleList() {
        return this.varEncloseRuleList;
    }

    /**
     * varEncloseRuleListを設定します。
     *
     * @param varEncloseRuleList varEncloseRuleList
     */
    public void setVarEncloseRuleList(List<VarEncloseRule> varEncloseRuleList) {
        this.varEncloseRuleList = varEncloseRuleList;
    }

    /*
     * (非 Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return analyzeId;
    }

}
