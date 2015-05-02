package me.suwash.rag.ap.csv;

import me.suwash.rag.ap.infra.BaseOutDto;
import me.suwash.rag.sv.service.AnalyzeServiceOutBean;

public class AnalyzeFacade4CsvOutDto extends BaseOutDto<AnalyzeFacade4CsvInDto> {

    public AnalyzeFacade4CsvOutDto(AnalyzeFacade4CsvInDto input) {
        super(input);
    }

    public void setServiceOutput(AnalyzeServiceOutBean outBean) {
        // TODO 自動生成されたメソッド・スタブ
    }
}
