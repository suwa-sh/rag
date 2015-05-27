package me.suwash.rag.ap.infra;

import java.util.Set;

import javax.validation.ConstraintViolation;

import me.suwash.ddd.classification.ProcessStatus;
import me.suwash.ddd.policy.layer.ap.OutDto;

/**
 * TODO クラスの説明。
 *
 * @param <InT> xxx
 */
public class BaseOutDto<InT extends BaseInDto> implements OutDto<InT> {

    private InT input;
    private Set<ConstraintViolation<InT>> violationSet;
    private ProcessStatus processStatus;

    /**
     * コンストラクタ。
     *
     * @param input xxx
     */
    protected BaseOutDto(InT input) {
        this.input = input;
    }

    @Override
    public InT getInput() {
        return input;
    }

    @Override
    public void setViolationSet(Set<ConstraintViolation<InT>> violationSet) {
        this.violationSet = violationSet;
    }

    @Override
    public Set<ConstraintViolation<InT>> getViolationSet() {
        return violationSet;
    }

    @Override
    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

}
