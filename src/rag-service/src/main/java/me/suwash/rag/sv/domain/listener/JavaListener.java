package me.suwash.rag.sv.domain.listener;

import me.suwash.rag.recognize.java.JavaBaseListener;
import me.suwash.rag.sv.domain.analyze.AnalyzeWork;

public class JavaListener extends JavaBaseListener {
    private AnalyzeWork work;

    public JavaListener(AnalyzeWork work) {
        this.work = work;
    }

    // TODO 変数保持
    // TODO Scope push & pop
    // TODO 再帰呼び出し
    // TODO 呼び出し文法 → 文字列として保持、InvokeState作成、引数を変数Mapに追加（Scope push）
}
