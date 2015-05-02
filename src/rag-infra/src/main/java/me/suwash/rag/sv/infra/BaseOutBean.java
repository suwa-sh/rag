package me.suwash.rag.sv.infra;

import java.util.Set;

import javax.validation.ConstraintViolation;

import me.suwash.ddd.classification.ProcessStatus;
import me.suwash.ddd.policy.layer.sv.OutBean;

public class BaseOutBean<IN extends BaseInBean> implements OutBean<IN> {

    private IN input;
    private Set<ConstraintViolation<IN>> violationSet;
    private ProcessStatus processStatus;

    protected BaseOutBean(IN input) {
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
