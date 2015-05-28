package me.suwash.rag.sv.infra;

import java.util.Set;

import javax.validation.ConstraintViolation;

import me.suwash.ddd.classification.ProcessStatus;
import me.suwash.ddd.policy.layer.sv.OutBean;

/**
 * TODO クラスの説明。
 *
 * @param <InT> xxx
 */
public class BaseOutBean<InT extends BaseInBean> implements OutBean<InT> {

    private InT input;
    private Set<ConstraintViolation<InT>> violationSet;
    private ProcessStatus processStatus;

    /**
     * コンストラクタ。
     *
     * @param input xxx
     */
    protected BaseOutBean(InT input) {
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
