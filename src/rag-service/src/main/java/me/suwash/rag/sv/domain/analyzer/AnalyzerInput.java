package me.suwash.rag.sv.domain.analyzer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import me.suwash.rag.classification.AnalyzeTargetType;
import me.suwash.rag.sv.domain.analyze.AnalyzeWork;

/**
 * 分析処理の入力データモデル。
 */
public class AnalyzerInput {

    /** 分析対象タイプ */
    @NotNull
    private AnalyzeTargetType analyzeTargetType;
//    /** 呼び出し構文 */
//    @NotNull
//    private InvokeStatement analyzeTarget;
    /** 分析作業オブジェクト */
    @NotNull
    private AnalyzeWork analyzeWork;

    public AnalyzeTargetType getAnalyzeTargetType() {
        return analyzeTargetType;
    }
    public void setAnalyzeTargetType(AnalyzeTargetType analyzeTargetType) {
        this.analyzeTargetType = analyzeTargetType;
    }
    public AnalyzeWork getAnalyzeWork() {
        return analyzeWork;
    }
    public void setAnalyzeWork(AnalyzeWork analyzeWork) {
        this.analyzeWork = analyzeWork;
    }

}
