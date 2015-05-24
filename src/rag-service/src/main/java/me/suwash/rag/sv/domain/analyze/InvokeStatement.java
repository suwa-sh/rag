package me.suwash.rag.sv.domain.analyze;

import java.util.ArrayList;
import java.util.List;

import me.suwash.ddd.exception.SvLayerException;

import org.apache.commons.lang.StringUtils;

/**
 *　呼び出し構文。
 * <pre>
 * ▪️想定する呼び出し構文のフォーマット
 * ・Java
 *   ・package.name.TypeName#MethodName()
 *   ・package.name.TypeName#MethodName(package.name.ArgTypeName)
 *   ・package.name.TypeName#MethodName(package.name.ArgTypeName1, package.name.ArgTypeName2)
 *
 * ・shell
 *   ・/path/to/script.sh#dummy()
 *   ・/path/to/script.sh#dummy(String)
 *   ・/path/to/script.sh#dummy(String, String)
 * </pre>
 */
public class InvokeStatement {
    /** 呼び出しロジックに処理名の指定が不要な場合のダミー値。 */
    public static final String METHOD_NAME_DUMMY = "dummy";

    /**
     * 呼び出し構文。
     * package.name.TypeName#MethodName(ArgType1, ArgType2)
     */
    private String analyzeTarget;

    /**
     * 呼び出し先の型。
     * package.name.TypeName#MethodName(ArgType1, ArgType2)
     *   → package.name.TypeName
     */
    private String invokeTargetType;

    /**
     * 呼び出し先処理。
     * package.name.TypeName#MethodName(ArgType1, ArgType2)
     *   → MethodName
     */
    private String invokeTargetMethod;

    /**
     * 呼び出し先処理引数の型リスト。
     * package.name.TypeName#MethodName(ArgType1, ArgType2)
     *   → [ArgType1, ArgType2]
     */
    private List<String> argumentTypeList;

    /**
     * コンストラクタ。
     * 呼び出し構文から、呼び出し対象の型、処理、引数に分解して保持します。
     *
     * @param analyzeTarget 呼び出し構文
     */
    public InvokeStatement(String analyzeTarget) {
        this.analyzeTarget = analyzeTarget;

        this.invokeTargetType = getInvokeTargetType(analyzeTarget);
        this.invokeTargetMethod = getInvokeTargetMethod(analyzeTarget);
        this.argumentTypeList = getArgumentTypeList(analyzeTarget);
    }

    /**
     * TODO メソッドのコメント。
     *
     * @return xxx
     */
    public String getAnalyzeTarget() {
        return analyzeTarget;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @return xxx
     */
    public String getInvokeTargetType() {
        return invokeTargetType;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @return xxx
     */
    public String getInvokeTargetMethod() {
        return invokeTargetMethod;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @return xxx
     */
    public List<String> getArgumentTypeList() {
        return argumentTypeList;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @param analyzeTarget xxx
     * @return xxx
     */
    private String getInvokeTargetType(String analyzeTarget) {
        // TODO batなどの処理名がない呼び出しの場合、呼び出しロジックから呼び出し構文への変換時に #dummy的な処理名を付与させる！
        if (analyzeTarget.contains("#")) {
            throw new SvLayerException("Sv.E0013", new Object[] {"analyzeTarget", "#"});
        }

        // package.name.TypeName#MethodName(ArgType1, ArgType2) → package.name.TypeName
        String invokeType = analyzeTarget.split("#")[0];
        return invokeType;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @param analyzeTarget2 xxx
     * @return xxx
     */
    private String getInvokeTargetMethod(String analyzeTarget2) {
        if (! analyzeTarget.contains("(")) {
            throw new SvLayerException("Sv.E0013", new Object[] {"analyzeTarget", "("});
        }

        // package.name.TypeName#MethodName(ArgType1, ArgType2) → MethodName(ArgType1, ArgType2)
        String method = analyzeTarget.split("#")[1];

        // MethodName(ArgType1, ArgType2) → MethodName
        method = method.split("\\(")[0];

        // ダミー値が設定されている場合、空文字を返却
        if (METHOD_NAME_DUMMY.equals(method)) {
            method = StringUtils.EMPTY;
        }

        return method;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @param analyzeTarget xxx
     * @return xxx
     */
    private List<String> getArgumentTypeList(String analyzeTarget) {
        if (! analyzeTarget.contains(")")) {
            throw new SvLayerException("Sv.E0013", new Object[] {"analyzeTarget", ")"});
        }

        // package.name.TypeName#MethodName(ArgType1, ArgType2) → MethodName(ArgType1, ArgType2)
        String argDef = analyzeTarget.split("#")[1];

        // MethodName(ArgType1, ArgType2) → ArgType1, ArgType2)
        argDef = argDef.split("\\(")[1];

        // ArgType1, ArgType2) → ArgType1, ArgType2
        argDef = argDef.replace(")", "");

        List<String> argList = new ArrayList<String>();
        if (argDef.contains(",")) {
            // ArgType1, ArgType2 → [ArgType1, ArgType2]
            String[] argStrs = argDef.split(",");
            for (String argStr : argStrs) {
                argList.add(StringUtils.trim(argStr));
            }
        }

        return argList;
    }
}
