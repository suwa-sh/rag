package me.suwash.rag.sv.domain.analyzer.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import me.suwash.ddd.classification.ProcessStatus;
import me.suwash.ddd.exception.SvLayerException;
import me.suwash.rag.classification.AnalyzePhase;
import me.suwash.rag.infra.Loggable;
import me.suwash.rag.sv.domain.analyzer.Analyzer;
import me.suwash.rag.sv.domain.analyzer.AnalyzerInput;
import me.suwash.rag.sv.domain.analyzer.AnalyzerOutput;

import org.slf4j.Logger;

/**
 * 文法毎の分析処理基底クラス。
 * TODO AnalyzerにListener, AnalyzeIdを設定できれば、サブクラス分割はいらないかも！
 *      → メソッドをフェーズにする？validate, pathReplace, parse, walk(operationMatching, applyOperation)
 *      → walk済みのファイルなら、呼び出し先のメソッドまで一気に飛びたい。
 *        → AntlrのAPIをもう少し把握しないと無理
 */
@Named
public class AnalyzerImpl implements Analyzer {

    @Loggable
    protected Logger logger;
    @Inject
    protected Validator validator;

    /*
     * (非 Javadoc)
     * @see me.suwash.rag.sv.domain.analyzer.Analyzer#analyze(me.suwash.rag.sv.domain.analyze.AnalyzerInput)
     */
    @Override
    public AnalyzerOutput analyze(AnalyzerInput input) {
        // 出力データ
        AnalyzerOutput output = new AnalyzerOutput();
        output.setInput(input);

        // 入力チェック
        logger.debug("[START]validate(" + input + ")");
        output.getCurrentDetail().setPhase(AnalyzePhase.Validate);
        output = validate(input, output);
        logger.debug("[END  ]validate");
        if (
                ! ProcessStatus.Processing.equals(output.getStatus()) &&
                ! ProcessStatus.Warning.equals(output.getStatus())
            ) {
            return output;
        }

        // データストア操作マッチング
        logger.debug("[START]validate(" + input + ", " + output + ")");
        output.getCurrentDetail().setPhase(AnalyzePhase.OperationMatching);
        output = operationMatching(input, output);
        logger.debug("[END  ]validate");
        if (
                ! ProcessStatus.Processing.equals(output.getStatus()) &&
                ! ProcessStatus.Warning.equals(output.getStatus())
            ) {
            return output;
        }

        // パス置換
        logger.debug("[START]pathReplace(" + input + ", " + output + ")");
        output.getCurrentDetail().setPhase(AnalyzePhase.ReplacePath);
        output = pathReplace(input, output);
        logger.debug("[END  ]pathReplace");
        if (
                ! ProcessStatus.Processing.equals(output.getStatus()) &&
                ! ProcessStatus.Warning.equals(output.getStatus())
            ) {
            return output;
        }

        // 文法解析
        logger.debug("[START]parse(" + input + ", " + output + ")");
        output.getCurrentDetail().setPhase(AnalyzePhase.Parse);
        output = parse(input, output);
        logger.debug("[END  ]parse");
        if (
                ! ProcessStatus.Processing.equals(output.getStatus()) &&
                ! ProcessStatus.Warning.equals(output.getStatus())
            ) {
            return output;
        }

        // 走査
        logger.debug("[START]walk(" + input + ", " + output + ")");
        output.getCurrentDetail().setPhase(AnalyzePhase.Walk);
        output = walk(input, output);
        logger.debug("[END  ]walk");

        return output;
    }

    /**
     * バリデーション。
     *
     * @param input 入力データ
     * @param output 出力データ
     * @return AnalyzerOutput 出力データ
     */
    private AnalyzerOutput validate(AnalyzerInput input, AnalyzerOutput output) {
        // 入力オブジェクトの存在チェック
        if (input == null) {
            throw new SvLayerException("check.notNull", new Object[] { "input" });
        }

        // --------------------------------------------------
        // 単項目チェック
        // --------------------------------------------------
        Set<ConstraintViolation<AnalyzerInput>> violations = validator.validate(input);

        // --------------------------------------------------
        // 関連チェック
        // --------------------------------------------------

        // --------------------------------------------------
        // 結果の判定
        // --------------------------------------------------
        // 結果の格納
        output.setViolations(violations);
        if (violations.size() > 0) {
            output.addError(AnalyzePhase.ContextMatching, new SvLayerException("Sv.E0012"));
        }

        return output;
    }


    private AnalyzerOutput operationMatching(AnalyzerInput input, AnalyzerOutput output) {
        // TODO ContextManager#operationMathingを実行
        //      呼び出し文、引数からデータストア操作にマッチング
        //      マッチした場合、AnalyzeWorkにCSV出力の元データを追加して、analyzeをここで終了
        return null;
    }

    /**
     * パス置換。
     *
     * @param input 入力データ
     * @param output 出力データ
     * @return 出力データ
     */
    private AnalyzerOutput pathReplace(AnalyzerInput input, AnalyzerOutput output) {
        // TODO ContextManager#pathReplaceを実行
        //      ここまでanalyzeIdがinputで引きまわす必要がありそう。
        return output;
    }

    /**
     * ファイルの文法解析。
     *
     * @param input 入力データ
     * @param output 出力データ
     * @return 出力データ
     */
    private AnalyzerOutput parse(AnalyzerInput input, AnalyzerOutput output) {
        // TODO 対象ファイルパスをparseしてAntlr拡張のListener取得まで
        //      同一ファイルを複数箇所で利用している場合に、再度parseする必要がないようにparse結果をキャッシュしておきたい。
        //        →キャッシュに含まれる場合は、parse処理を行わず、即時で返却して終了。
        //          →キャッシュするためには、メソッド呼び出しだとしても、importやプロパティなどの変数Mapが必要＋後続のOperationも全て整理が必要
        //            →毎回parseすれば安全かな。
        return output;
    }

    /**
     * ファイル内容の走査。
     *
     * @param input 入力データ
     * @param output 出力データ
     * @return 出力データ
     */
    private AnalyzerOutput walk(AnalyzerInput input, AnalyzerOutput output) {
        // TODO parseで作成したListenerインスタンスを使って、構文ツリーを走査。操作時にAnalyzeWorkに結果を格納するように実装。
        return output;
    }
}
