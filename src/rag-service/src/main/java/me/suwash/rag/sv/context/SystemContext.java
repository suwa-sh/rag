package me.suwash.rag.sv.context;

import java.util.List;

import me.suwash.rag.classification.OnErrorOperation;
import me.suwash.rag.sv.context.detail.AnalyzeContextMatchingRule;

/**
 * システムコンテキスト。
 */
public class SystemContext {

    /** 分析コンテキスト配置ディレクトリ。 */
    protected String analyzeContextDir;
    /** 分析コンテキストマッチングルールリスト。 */
    protected List<AnalyzeContextMatchingRule> matchingRuleList;
    /** バウンダリの分析コンテキストID。 */
    protected String boundaryAnalyzeId;

    /** 分析深度の上限の未設定値。 */
    public static final int UNDEFINED_ANALYZE_DEPTH_LIMIT = -1;
    /** 分析深度の上限。 */
    protected int analyzeDepthLimit = UNDEFINED_ANALYZE_DEPTH_LIMIT;
    /** エラー時の制御。 */
    protected OnErrorOperation onError;

    /**
     * analyzeContextDirを返します。
     *
     * @return analyzeContextDir
     */
    public String getAnalyzeContextDir() {
        return this.analyzeContextDir;
    }

    /**
     * analyzeContextDirを設定します。
     *
     * @param analyzeContextDir analyzeContextDir
     */
    public void setAnalyzeContextDir(String analyzeContextDir) {
        this.analyzeContextDir = analyzeContextDir;
    }

    /**
     * matchingRuleListを返します。
     *
     * @return matchingRuleList
     */
    public List<AnalyzeContextMatchingRule> getMatchingRuleList() {
        return this.matchingRuleList;
    }

    /**
     * matchingRuleListを設定します。
     *
     * @param matchingRuleList matchingRuleList
     */
    public void setMatchingRuleList(List<AnalyzeContextMatchingRule> matchingRuleList) {
        this.matchingRuleList = matchingRuleList;
    }

    /**
     * boundaryAnalyzeIdを返します。
     *
     * @return boundaryAnalyzeId
     */
    public String getBoundaryAnalyzeId() {
        return this.boundaryAnalyzeId;
    }

    /**
     * boundaryAnalyzeIdを設定します。
     *
     * @param boundaryAnalyzeId boundaryAnalyzeId
     */
    public void setBoundaryAnalyzeId(String boundaryAnalyzeId) {
        this.boundaryAnalyzeId = boundaryAnalyzeId;
    }

    /**
     * analyzeDepthLimitを返します。
     *
     * @return analyzeDepthLimit
     */
    public int getAnalyzeDepthLimit() {
        return this.analyzeDepthLimit;
    }

    /**
     * analyzeDepthLimitを設定します。
     *
     * @param analyzeDepthLimit analyzeDepthLimit
     */
    public void setAnalyzeDepthLimit(int analyzeDepthLimit) {
        this.analyzeDepthLimit = analyzeDepthLimit;
    }

    /**
     * onErrorを返します。
     *
     * @return onError
     */
    public OnErrorOperation getOnError() {
        return this.onError;
    }

    /**
     * onErrorを設定します。
     *
     * @param onError onError
     */
    public void setOnError(OnErrorOperation onError) {
        this.onError = onError;
    }

}
