package me.suwash.rag.sv.domain.analyze;

import java.util.HashMap;
import java.util.Map;

import me.suwash.ddd.exception.SvLayerException;
import me.suwash.rag.classification.VariableType;

/**
 * TODO クラスの説明。
 */
public class Variable {

    /** この変数の型。 */
    private String type;
    /** この変数名。 */
    private String name;
    /** この変数タイプ。 */
    private VariableType variableType;
    /** この変数がプリミティブ型の場合の設定値。 */
    private String primitiveValue;
    /** この変数が参照型の場合のフィールド設定値。 */
    private Map<String, Variable> varMap = new HashMap<String, Variable>();

    /**
     * コンストラクタ。
     *
     * @param type xxx
     * @param name xxx
     * @param variableType xxx
     */
    public Variable(String type, String name, VariableType variableType) {
        this.type = type;
        this.name = name;
        this.variableType = variableType;
    }

    /**
     * typeを返します。
     *
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * typeを設定します。
     *
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * この変数がプリミティブ型の場合、設定値を返します。
     *
     * @return 設定値
     * @throws SvLayerException 参照型の場合
     */
    public String getValue() {
        if (VariableType.Primitive.equals(variableType)) {
            return primitiveValue;
        } else {
            // 参照型の場合、フィールド名が指定されていないのでエラー
            throw new SvLayerException("Sv.E0007", new Object[] {
                this.type + " " + this.name, VariableType.Reference.ddId()
            });
        }
    }

    /**
     * nameを返します。
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * nameを設定します。
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * この変数がプリミティブ型の場合、値を設定します。
     *
     * @param value 設定値
     * @throws SvLayerException 参照型の場合
     */
    public void setValue(String value) {
        if (VariableType.Primitive.equals(variableType)) {
            this.primitiveValue = value;
        } else {
            // 参照型の場合、フィールド名が指定されていないのでエラー
            throw new SvLayerException("Sv.E0007", new Object[] {
                this.type + " " + this.name, VariableType.Reference.ddId()
            });
        }
    }

    /**
     * この変数が参照型の場合、指定されたフィールドの変数を返します。
     *
     * @param name フィールド名
     * @return フィールドに設定された変数
     */
    public Variable getReferenceValue(String name) {
        if (VariableType.Reference.equals(variableType)) {
            return varMap.get(name);
        } else {
            // プリミティブ型の場合、エラー
            throw new SvLayerException("Sv.E0007", new Object[] {
                this.type + " " + this.name, VariableType.Primitive.ddId()
            });
        }
    }

    /**
     * この変数が参照型の場合、変数を設定します。
     *
     * @param variable 設定する変数
     * @throws SvLayerException プリミティブ型の場合
     */
    public void setReferenceValue(Variable variable) {
        if (VariableType.Reference.equals(variableType)) {
            varMap.put(variable.getName(), variable);
        } else {
            // プリミティブ型の場合、エラー
            throw new SvLayerException("Sv.E0007", new Object[] {
                this.type + " " + this.name, VariableType.Primitive.ddId()
            });
        }
    }
}
