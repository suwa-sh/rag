package me.suwash.rag.ap.infra;

import java.util.Set;

import javax.validation.ConstraintViolation;

import me.suwash.ddd.classification.ProcessStatus;
import me.suwash.ddd.policy.layer.ap.OutDto;

public class BaseOutDto<IN extends BaseInDto> implements OutDto<IN> {

    private IN input;
    private Set<ConstraintViolation<IN>> violationSet;
    private ProcessStatus processStatus;

    protected BaseOutDto(IN input) {
        this.input = input;
    }

    @Override
    public IN getInput() {
        return input;
    }

    @Override
    public void setViolationSet(Set<ConstraintViolation<IN>> violationSet) {
        this.violationSet = violationSet;
    }

    @Override
    public Set<ConstraintViolation<IN>> getViolationSet() {
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
