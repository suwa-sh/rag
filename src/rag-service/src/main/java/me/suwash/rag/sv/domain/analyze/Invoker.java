package me.suwash.rag.sv.domain.analyze;

import java.util.List;

/**
 * TODO クラスの説明。
 */
public class Invoker {
    private String invokerPath;
    private String invokeSignature;
    private List<?> argList;
    /**
     * invokerPathを返します。
     *
     * @return invokerPath
     */
    public String getInvokerPath() {
        return this.invokerPath;
    }
    /**
     * invokerPathを設定します。
     *
     * @param invokerPath invokerPath
     */
    public void setInvokerPath(String invokerPath) {
        this.invokerPath = invokerPath;
    }
    /**
     * invokeSignatureを返します。
     *
     * @return invokeSignature
     */
    public String getInvokeSignature() {
        return this.invokeSignature;
    }
    /**
     * invokeSignatureを設定します。
     *
     * @param invokeSignature invokeSignature
     */
    public void setInvokeSignature(String invokeSignature) {
        this.invokeSignature = invokeSignature;
    }
    /**
     * argListを返します。
     *
     * @return argList
     */
    public List<?> getArgList() {
        return this.argList;
    }
    /**
     * argListを設定します。
     *
     * @param argList argList
     */
    public void setArgList(List<?> argList) {
        this.argList = argList;
    }
}
