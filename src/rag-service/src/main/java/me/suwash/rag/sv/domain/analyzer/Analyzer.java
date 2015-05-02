package me.suwash.rag.sv.domain.analyzer;


/**
 * 分析処理。
 */
public interface Analyzer {

    /**
     * 分析処理。
     *
     * @param input 入力データ
     * @return 出力データ
     */
    abstract public AnalyzerOutput analyze(AnalyzerInput input);
}
