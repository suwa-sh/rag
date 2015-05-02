package me.suwash.rag.sv.service.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;

import me.suwash.ddd.policy.layer.sv.GenericService;
import me.suwash.rag.infra.Loggable;
import me.suwash.rag.sv.domain.analyzer.Analyzer;
import me.suwash.rag.sv.domain.analyzer.AnalyzerInput;
import me.suwash.rag.sv.service.AnalyzeService;
import me.suwash.rag.sv.service.AnalyzeServiceInBean;
import me.suwash.rag.sv.service.AnalyzeServiceOutBean;

/**
 * 対象から分析IDの判断、ファイル単位の分析。
 */
@Named
public class AnalyzeServiceImpl extends GenericService<AnalyzeServiceInBean, AnalyzeServiceOutBean> implements AnalyzeService {

    @Loggable
    private Logger logger;

    @Inject
    private Validator validator;

    @Inject
    private Analyzer analyzer;

    /* (非 Javadoc)
     * @see jp.co.presa.ddd.policy.LayerSuperType#validate(jp.co.presa.ddd.policy.Input)
     * TODO 全体的にjp.co.presa.dddをme.suwashに置換
     */
    @Override
    public Set<ConstraintViolation<AnalyzeServiceInBean>> validate(AnalyzeServiceInBean input) {
        //--------------------------------------------------------------------------------
        // 単項目チェック
        //--------------------------------------------------------------------------------
        Set<ConstraintViolation<AnalyzeServiceInBean>> violations = validator.validate(input);

        //--------------------------------------------------------------------------------
        // 関連チェック
        //--------------------------------------------------------------------------------
        // TODO 関連チェックをBeanにもたせたいが、どう実装すればJSR303に乗れるの？

        return violations;
    }

    /* (非 Javadoc)
     * @see jp.co.presa.ddd.policy.GenericLayerSuperType#getOutput()
     */
    @Override
    protected AnalyzeServiceOutBean getOutput(AnalyzeServiceInBean input) {
        return new AnalyzeServiceOutBean(input);
    }

    /* (非 Javadoc)
     * @see jp.co.presa.ddd.policy.GenericLayerSuperType#preExecute(jp.co.presa.ddd.policy.Input)
     */
    @Override
    protected AnalyzeServiceOutBean preExecute(AnalyzeServiceInBean input) {
        //--------------------------------------------------
        // AnalyzeInputの作成
        //--------------------------------------------------

        // TODO 自動生成されたメソッド・スタブ
        return super.preExecute(input);
    }

    /* (非 Javadoc)
     * @see jp.co.presa.ddd.policy.GenericLayerSuperType#mainExecute(jp.co.presa.ddd.policy.Input, jp.co.presa.ddd.policy.Output)
     */
    @Override
    protected AnalyzeServiceOutBean mainExecute(AnalyzeServiceInBean input, AnalyzeServiceOutBean preExecuteOutput) {
        //--------------------------------------------------
        // AnalyzeIdの判定
        //--------------------------------------------------
        AnalyzerInput analyzerInput = new AnalyzerInput();

        //--------------------------------------------------
        // Analyzerの呼び出し
        //--------------------------------------------------
        analyzer.analyze(analyzerInput);
        return null;
    }

    /* (非 Javadoc)
     * @see jp.co.presa.ddd.policy.GenericLayerSuperType#postExecute(jp.co.presa.ddd.policy.Input, jp.co.presa.ddd.policy.Output)
     */
    @Override
    protected AnalyzeServiceOutBean postExecute(AnalyzeServiceInBean input, AnalyzeServiceOutBean mainExecuteOutput) {
        //--------------------------------------------------
        // AnalyzerOutputの判定
        //--------------------------------------------------

        // TODO 自動生成されたメソッド・スタブ
        return super.postExecute(input, mainExecuteOutput);
    }

}
