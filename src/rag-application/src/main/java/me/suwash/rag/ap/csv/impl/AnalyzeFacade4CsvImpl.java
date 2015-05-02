package me.suwash.rag.ap.csv.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;

import me.suwash.ddd.policy.layer.ap.GenericFacade;
import me.suwash.rag.ap.csv.AnalyzeFacade4Csv;
import me.suwash.rag.ap.csv.AnalyzeFacade4CsvInDto;
import me.suwash.rag.ap.csv.AnalyzeFacade4CsvOutDto;
import me.suwash.rag.infra.Loggable;
import me.suwash.rag.sv.service.AnalyzeService;
import me.suwash.rag.sv.service.AnalyzeServiceInBean;
import me.suwash.rag.sv.service.AnalyzeServiceOutBean;

/**
 * バウンダリ単位の分析、CSVファイル出力。
 */
@Named
public class AnalyzeFacade4CsvImpl extends GenericFacade<AnalyzeFacade4CsvInDto, AnalyzeFacade4CsvOutDto> implements AnalyzeFacade4Csv {

    @Loggable
    private Logger logger;

    @Inject
    private AnalyzeService analyzeService;

    @Inject
    private Validator validator;

    /* (非 Javadoc)
     * @see jp.co.presa.ddd.policy.LayerSuperType#validate(jp.co.presa.ddd.policy.Input)
     */
    @Override
    public Set<ConstraintViolation<AnalyzeFacade4CsvInDto>> validate(AnalyzeFacade4CsvInDto input) {
        //--------------------------------------------------------------------------------
        // 単項目チェック
        //--------------------------------------------------------------------------------
        Set<ConstraintViolation<AnalyzeFacade4CsvInDto>> violations = validator.validate(input);

        //--------------------------------------------------------------------------------
        // 関連チェック
        //--------------------------------------------------------------------------------
        // TODO 関連チェックをDtoにもたせたいが、どう実装すればJSR303に乗れるの？

        return violations;
    }

    /* (非 Javadoc)
     * @see jp.co.presa.ddd.policy.GenericLayerSuperType#getOutput()
     */
    @Override
    protected AnalyzeFacade4CsvOutDto getOutput(AnalyzeFacade4CsvInDto input) {
        return new AnalyzeFacade4CsvOutDto(input);
    }

//    /* (非 Javadoc)
//     * @see jp.co.presa.ddd.policy.GenericLayerSuperType#preExecute(jp.co.presa.ddd.policy.Input)
//     */
//    @Override
//    protected AnalyzeFacade4CsvOutDto preExecute(AnalyzeFacade4CsvInDto input) {
//        return super.preExecute(input);
//    }

    /* (非 Javadoc)
     * @see jp.co.presa.ddd.policy.GenericLayerSuperType#mainExecute(jp.co.presa.ddd.policy.Input, jp.co.presa.ddd.policy.Output)
     */
    @Override
    protected AnalyzeFacade4CsvOutDto mainExecute(AnalyzeFacade4CsvInDto input, AnalyzeFacade4CsvOutDto preExecuteOutput) {
        // TODO Facadeの入力データから、ServiceのInBeanを作成
        AnalyzeServiceInBean inBean = new AnalyzeServiceInBean();

        // Service実行
        AnalyzeServiceOutBean outBean = analyzeService.execute(inBean);

        // 結果の判定
        preExecuteOutput.setServiceOutput(outBean);
        return preExecuteOutput;
    }

    /* (非 Javadoc)
     * @see jp.co.presa.ddd.policy.GenericLayerSuperType#postExecute(jp.co.presa.ddd.policy.Input, jp.co.presa.ddd.policy.Output)
     */
    @Override
    protected AnalyzeFacade4CsvOutDto postExecute(AnalyzeFacade4CsvInDto input, AnalyzeFacade4CsvOutDto mainExecuteOutput) {
        // TODO 結果データからCSVファイル出力
        return super.postExecute(input, mainExecuteOutput);
    }

}
