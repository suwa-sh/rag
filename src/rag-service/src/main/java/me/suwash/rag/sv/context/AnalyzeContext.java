package me.suwash.rag.sv.context;

import java.util.List;

import me.suwash.rag.sv.context.detail.PathReplaceRule;
import me.suwash.rag.sv.context.detail.VarEncloseRule;

/**
 * 分析コンテキスト。
 */
public class AnalyzeContext {

    /** 分析ID */
    private String analyzeId;
    /** 包含分析IDリスト */
    private List<String> includeAnalyzeIdList;

    /** Path置換ルールリスト */
    private List<PathReplaceRule> pathReplaceRuleList;
    /** 変数括り文字ルールリスト */
    private List<VarEncloseRule> varEncloseRuleList;

    public String getAnalyzeId() {
        return analyzeId;
    }
    public void setAnalyzeId(String analyzeId) {
        this.analyzeId = analyzeId;
    }

    public List<String> getIncludeAnalyzeIdList() {
        return includeAnalyzeIdList;
    }
    public void setIncludeAnalyzeIdList(List<String> includeAnalyzeIdList) {
        this.includeAnalyzeIdList = includeAnalyzeIdList;
    }

    public List<PathReplaceRule> getPathReplaceRuleList() {
        return pathReplaceRuleList;
    }
    public void setPathReplaceRuleList(List<PathReplaceRule> pathReplaceRuleList) {
        this.pathReplaceRuleList = pathReplaceRuleList;
    }

    public List<VarEncloseRule> getVarEncloseRuleList() {
        return varEncloseRuleList;
    }
    public void setVarEncloseRuleList(List<VarEncloseRule> varEncloseRuleList) {
        this.varEncloseRuleList = varEncloseRuleList;
    }

    /* (非 Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return analyzeId;
    }
}
