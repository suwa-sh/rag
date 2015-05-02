package me.suwash.rag.sv.context;

import java.util.List;

import me.suwash.rag.classification.OnErrorOperation;
import me.suwash.rag.sv.context.detail.AnalyzeContextMatchingRule;

/**
 * システムコンテキスト。
 */
public class SystemContext {

    /** 分析コンテキスト配置ディレクトリ */
    protected String analyzeContextDir;
    /** 分析コンテキストマッチングルールリスト */
    protected List<AnalyzeContextMatchingRule> matchingRuleList;
    /** バウンダリの分析コンテキストID */
    protected String boundaryAnalyzeId;

    /** 分析深度の上限の未設定値 */
    public static final int UNDEFINED_ANALYZE_DEPTH_LIMIT = -1;
    /** 分析深度の上限 */
    protected int analyzeDepthLimit = UNDEFINED_ANALYZE_DEPTH_LIMIT;
    /** エラー時の制御 */
    protected OnErrorOperation onError;

    public String getAnalyzeContextDir() {
        return analyzeContextDir;
    }
    public void setAnalyzeContextDir(String analyzeContextDir) {
        this.analyzeContextDir = analyzeContextDir;
    }

    public List<AnalyzeContextMatchingRule> getMatchingRuleList() {
        return matchingRuleList;
    }
    public void setMatchingRuleList(List<AnalyzeContextMatchingRule> matchingRuleList) {
        this.matchingRuleList = matchingRuleList;
    }

    public String getBoundaryAnalyzeId() {
        return boundaryAnalyzeId;
    }
    public void setBoundaryAnalyzeId(String boundaryAnalyzeId) {
        this.boundaryAnalyzeId = boundaryAnalyzeId;
    }

    public int getAnalyzeDepthLimit() {
        return analyzeDepthLimit;
    }
    public void setAnalyzeDepthLimit(int analyzeDepthLimit) {
        this.analyzeDepthLimit = analyzeDepthLimit;
    }

    public OnErrorOperation getOnError() {
        return onError;
    }
    public void setOnError(OnErrorOperation onError) {
        this.onError = onError;
    }

}
