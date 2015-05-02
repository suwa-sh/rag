package me.suwash.rag.sv.domain.analyze;

import java.util.List;

public class Invoker {
    private String invokerPath;
    private String invokeSignature;
    private List<?> argList;

    public String getInvokerPath() {
        return invokerPath;
    }
    public void setInvokerPath(String invokerPath) {
        this.invokerPath = invokerPath;
    }
    public String getInvokeSignature() {
        return invokeSignature;
    }
    public void setInvokeSignature(String invokeSignature) {
        this.invokeSignature = invokeSignature;
    }
    public List<?> getArgList() {
        return argList;
    }
    public void setArgList(List<?> argList) {
        this.argList = argList;
    }
}
