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
 * 分析詳細
 */
public class AnalyzeDetail {

    /** 分析ステータス */
    private AnalyzeStatus status;
    /** 分析フェーズ */
    private AnalyzePhase phase;
    /** 分析エラー */
    private LayerException exception;

    /** 呼び出し文 */
    private String invokeStatement;
    /** 適用された分析ID */
    private String analyzeId;
    /** 置換後の分析対象ファイルパス */
    private String analyzeTargetPath;

    /** 適用された分析コンテキストマッチングルール */
    private AnalyzeContextMatchingRule analyzeContextMatchingRule;
    /** 適用されたパス置換ルール */
    private List<PathReplaceRule> pathReplaceRuleList;
    /** 適用されたデータストア操作マッチングルール */
    private OperationMatchingRule operationMatchingRule;
    /** 適用されたデータストア操作ルール */
    private ApplyOperationRule applyOperationRule;

    public AnalyzeDetail() {
        status = AnalyzeStatus.Processing;
    }

    /**
     * @return status
     */
    public AnalyzeStatus getStatus() {
        return status;
    }
    /**
     * @param status セットする status
     */
    public void setStatus(AnalyzeStatus status) {
        this.status = status;
    }
    /**
     * @return phase
     */
    public AnalyzePhase getPhase() {
        return phase;
    }
    /**
     * @param phase セットする phase
     */
    public void setPhase(AnalyzePhase phase) {
        this.phase = phase;
    }
    /**
     * @return exception
     */
    public LayerException getException() {
        return exception;
    }
    /**
     * @param exception セットする exception
     */
    public void setException(LayerException exception) {
        this.exception = exception;
    }
    /**
     * @return invokeStatement
     */
    public String getInvokeStatement() {
        return invokeStatement;
    }
    /**
     * @param invokeStatement セットする invokeStatement
     */
    public void setInvokeStatement(String invokeStatement) {
        this.invokeStatement = invokeStatement;
    }
    /**
     * @return analyzeTargetPath
     */
    public String getAnalyzeTargetPath() {
        return analyzeTargetPath;
    }
    /**
     * @param analyzeTargetPath セットする analyzeTargetPath
     */
    public void setAnalyzeTargetPath(String analyzeTargetPath) {
        this.analyzeTargetPath = analyzeTargetPath;
    }
    /**
     * @return analyzeId
     */
    public String getAnalyzeId() {
        return analyzeId;
    }
    /**
     * @param analyzeId セットする analyzeId
     */
    public void setAnalyzeId(String analyzeId) {
        this.analyzeId = analyzeId;
    }
    /**
     * @return analyzeContextMatchingRule
     */
    public AnalyzeContextMatchingRule getAnalyzeContextMatchingRule() {
        return analyzeContextMatchingRule;
    }
    /**
     * @param analyzeContextMatchingRule セットする analyzeContextMatchingRule
     */
    public void setAnalyzeContextMatchingRule(AnalyzeContextMatchingRule analyzeContextMatchingRule) {
        this.analyzeContextMatchingRule = analyzeContextMatchingRule;
    }
    /**
     * @return pathReplaceRuleList
     */
    public List<PathReplaceRule> getPathReplaceRuleList() {
        return pathReplaceRuleList;
    }
    /**
     * @param pathReplaceRuleList セットする pathReplaceRuleList
     */
    public void setPathReplaceRuleList(List<PathReplaceRule> pathReplaceRuleList) {
        this.pathReplaceRuleList = pathReplaceRuleList;
    }
    /**
     * @return operationMatchingRule
     */
    public OperationMatchingRule getOperationMatchingRule() {
        return operationMatchingRule;
    }
    /**
     * @param operationMatchingRule セットする operationMatchingRule
     */
    public void setOperationMatchingRule(OperationMatchingRule operationMatchingRule) {
        this.operationMatchingRule = operationMatchingRule;
    }
    /**
     * @return applyOperationRule
     */
    public ApplyOperationRule getApplyOperationRule() {
        return applyOperationRule;
    }
    /**
     * @param applyOperationRule セットする applyOperationRule
     */
    public void setApplyOperationRule(ApplyOperationRule applyOperationRule) {
        this.applyOperationRule = applyOperationRule;
    }

}
