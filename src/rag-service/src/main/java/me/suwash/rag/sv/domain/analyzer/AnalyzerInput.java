package me.suwash.rag.sv.domain.analyzer;

import javax.validation.constraints.NotNull;

import me.suwash.rag.classification.AnalyzeTargetType;
import me.suwash.rag.sv.domain.analyze.AnalyzeWork;

/**
 * 分析処理の入力データモデル。
 */
public class AnalyzerInput {

    /** 分析対象タイプ。 */
    @NotNull
    private AnalyzeTargetType analyzeTargetType;
    // /** 呼び出し構文 */
    // @NotNull
    // private InvokeStatement analyzeTarget;
    /** 分析作業オブジェクト。 */
    @NotNull
    private AnalyzeWork analyzeWork;
    /**
     * analyzeTargetTypeを返します。
     *
     * @return analyzeTargetType
     */
    public AnalyzeTargetType getAnalyzeTargetType() {
        return this.analyzeTargetType;
    }
    /**
     * analyzeTargetTypeを設定します。
     *
     * @param analyzeTargetType analyzeTargetType
     */
    public void setAnalyzeTargetType(AnalyzeTargetType analyzeTargetType) {
        this.analyzeTargetType = analyzeTargetType;
    }
    /**
     * analyzeWorkを返します。
     *
     * @return analyzeWork
     */
    public AnalyzeWork getAnalyzeWork() {
        return this.analyzeWork;
    }
    /**
     * analyzeWorkを設定します。
     *
     * @param analyzeWork analyzeWork
     */
    public void setAnalyzeWork(AnalyzeWork analyzeWork) {
        this.analyzeWork = analyzeWork;
    }

}
