package me.suwash.rag.sv.domain.analyze;

import java.util.List;

import me.suwash.rag.classification.AnalyzePhase;
import me.suwash.rag.classification.AnalyzeStatus;
import me.suwash.rag.sv.context.detail.AnalyzeContextMatchingRule;
import me.suwash.rag.sv.context.detail.ApplyOperationRule;
import me.suwash.rag.sv.context.detail.OperationMatchingRule;
import me.suwash.rag.sv.context.detail.PathReplaceRule;
import me.suwash.util.exception.LayerException;

/**
 * 分析詳細。
 */
public class AnalyzeDetail {

    /** 分析ステータス。 */
    private AnalyzeStatus status;
    /** 分析フェーズ。 */
    private AnalyzePhase phase;
    /** 分析エラー。 */
    private LayerException exception;

    /** 呼び出し文。 */
    private String invokeStatement;
    /** 適用された分析ID。 */
    private String analyzeId;
    /** 置換後の分析対象ファイルパス。 */
    private String analyzeTargetPath;

    /** 適用された分析コンテキストマッチングルール。 */
    private AnalyzeContextMatchingRule analyzeContextMatchingRule;
    /** 適用されたパス置換ルール。 */
    private List<PathReplaceRule> pathReplaceRuleList;
    /** 適用されたデータストア操作マッチングルール。 */
    private OperationMatchingRule operationMatchingRule;
    /** 適用されたデータストア操作ルール。 */
    private ApplyOperationRule applyOperationRule;

    /**
     * コンストラクタ。
     */
    public AnalyzeDetail() {
        status = AnalyzeStatus.Processing;
    }

    /**
     * statusを返します。
     *
     * @return status
     */
    public AnalyzeStatus getStatus() {
        return this.status;
    }

    /**
     * statusを設定します。
     *
     * @param status status
     */
    public void setStatus(AnalyzeStatus status) {
        this.status = status;
    }

    /**
     * phaseを返します。
     *
     * @return phase
     */
    public AnalyzePhase getPhase() {
        return this.phase;
    }

    /**
     * phaseを設定します。
     *
     * @param phase phase
     */
    public void setPhase(AnalyzePhase phase) {
        this.phase = phase;
    }

    /**
     * exceptionを返します。
     *
     * @return exception
     */
    public LayerException getException() {
        return this.exception;
    }

    /**
     * exceptionを設定します。
     *
     * @param exception exception
     */
    public void setException(LayerException exception) {
        this.exception = exception;
    }

    /**
     * invokeStatementを返します。
     *
     * @return invokeStatement
     */
    public String getInvokeStatement() {
        return this.invokeStatement;
    }

    /**
     * invokeStatementを設定します。
     *
     * @param invokeStatement invokeStatement
     */
    public void setInvokeStatement(String invokeStatement) {
        this.invokeStatement = invokeStatement;
    }

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
     * analyzeTargetPathを返します。
     *
     * @return analyzeTargetPath
     */
    public String getAnalyzeTargetPath() {
        return this.analyzeTargetPath;
    }

    /**
     * analyzeTargetPathを設定します。
     *
     * @param analyzeTargetPath analyzeTargetPath
     */
    public void setAnalyzeTargetPath(String analyzeTargetPath) {
        this.analyzeTargetPath = analyzeTargetPath;
    }

    /**
     * analyzeContextMatchingRuleを返します。
     *
     * @return analyzeContextMatchingRule
     */
    public AnalyzeContextMatchingRule getAnalyzeContextMatchingRule() {
        return this.analyzeContextMatchingRule;
    }

    /**
     * analyzeContextMatchingRuleを設定します。
     *
     * @param analyzeContextMatchingRule analyzeContextMatchingRule
     */
    public void setAnalyzeContextMatchingRule(AnalyzeContextMatchingRule analyzeContextMatchingRule) {
        this.analyzeContextMatchingRule = analyzeContextMatchingRule;
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
     * operationMatchingRuleを返します。
     *
     * @return operationMatchingRule
     */
    public OperationMatchingRule getOperationMatchingRule() {
        return this.operationMatchingRule;
    }

    /**
     * operationMatchingRuleを設定します。
     *
     * @param operationMatchingRule operationMatchingRule
     */
    public void setOperationMatchingRule(OperationMatchingRule operationMatchingRule) {
        this.operationMatchingRule = operationMatchingRule;
    }

    /**
     * applyOperationRuleを返します。
     *
     * @return applyOperationRule
     */
    public ApplyOperationRule getApplyOperationRule() {
        return this.applyOperationRule;
    }

    /**
     * applyOperationRuleを設定します。
     *
     * @param applyOperationRule applyOperationRule
     */
    public void setApplyOperationRule(ApplyOperationRule applyOperationRule) {
        this.applyOperationRule = applyOperationRule;
    }

}
