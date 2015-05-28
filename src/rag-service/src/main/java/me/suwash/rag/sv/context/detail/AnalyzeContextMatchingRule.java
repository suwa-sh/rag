package me.suwash.rag.sv.context.detail;

import java.io.File;

import me.suwash.rag.classification.AnalyzeContextMatchingMode;
import me.suwash.rag.classification.ArgumentType;

import org.apache.commons.lang.StringUtils;

/**
 * 分析コンテキストマッチングルール。
 */
public class AnalyzeContextMatchingRule extends BaseRule {

    /** 分析コンテキストマッチングモード。 */
    private AnalyzeContextMatchingMode matchingMode;

    /** 分析ID。 */
    private String analyzeId;

    // --------------------------------------------------
    // 拡張子指定
    // --------------------------------------------------
    /** 拡張子。 */
    private String extention;

    // --------------------------------------------------
    // 呼び出し元指定
    // --------------------------------------------------
    /** 呼び出し元指定キー。 */
    private String invokerKey;
    /** 引数タイプ。 */
    private ArgumentType argumentType;

    /** 対象ディレクトリ指定キー。 */
    private String targetDirArgKey;
    /** 対象ファイル指定キー。 */
    private String targetFileArgKey;

    /**
     * matchingModeを返します。
     *
     * @return matchingMode
     */
    public AnalyzeContextMatchingMode getMatchingMode() {
        return this.matchingMode;
    }

    /**
     * matchingModeを設定します。
     *
     * @param matchingMode matchingMode
     */
    public void setMatchingMode(AnalyzeContextMatchingMode matchingMode) {
        this.matchingMode = matchingMode;
    }

    /**
     * analyzeIdを返します。
     *
     * @return analyzeId
     */
    public String getAnalyzeId() {
        return this.analyzeId;
    }

    /**
     * analyzeIdを設定します。
     *
     * @param analyzeId analyzeId
     */
    public void setAnalyzeId(String analyzeId) {
        this.analyzeId = analyzeId;
    }

    /**
     * extentionを返します。
     *
     * @return extention
     */
    public String getExtention() {
        return this.extention;
    }

    /**
     * extentionを設定します。
     *
     * @param extention extention
     */
    public void setExtention(String extention) {
        this.extention = extention;
    }

    /**
     * invokerKeyを返します。
     *
     * @return invokerKey
     */
    public String getInvokerKey() {
        return this.invokerKey;
    }

    /**
     * invokerKeyを設定します。
     *
     * @param invokerKey invokerKey
     */
    public void setInvokerKey(String invokerKey) {
        this.invokerKey = invokerKey;
    }

    /**
     * argumentTypeを返します。
     *
     * @return argumentType
     */
    public ArgumentType getArgumentType() {
        return this.argumentType;
    }

    /**
     * argumentTypeを設定します。
     *
     * @param argumentType argumentType
     */
    public void setArgumentType(ArgumentType argumentType) {
        this.argumentType = argumentType;
    }

    /**
     * targetDirArgKeyを返します。
     *
     * @return targetDirArgKey
     */
    public String getTargetDirArgKey() {
        return this.targetDirArgKey;
    }

    /**
     * targetDirArgKeyを設定します。
     *
     * @param targetDirArgKey targetDirArgKey
     */
    public void setTargetDirArgKey(String targetDirArgKey) {
        this.targetDirArgKey = targetDirArgKey;
    }

    /**
     * targetFileArgKeyを返します。
     *
     * @return targetFileArgKey
     */
    public String getTargetFileArgKey() {
        return this.targetFileArgKey;
    }

    /**
     * targetFileArgKeyを設定します。
     *
     * @param targetFileArgKey targetFileArgKey
     */
    public void setTargetFileArgKey(String targetFileArgKey) {
        this.targetFileArgKey = targetFileArgKey;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @param target xxx
     * @return String
     */
    public String getMatchedAnalyzeId(String target) {
        // 引数チェック
        if (StringUtils.isEmpty(target)) {
            return StringUtils.EMPTY;
        }

        // 拡張子マッチング
        if (matchingMode.equals(AnalyzeContextMatchingMode.Extention)) {
            // 拡張子
            String targetExt = getExtention(target);

            // マッチング
            if (extention.equals(targetExt)) {
                return this.analyzeId;
            }
        }

        // 呼び出し元マッチング
        if (matchingMode.equals(AnalyzeContextMatchingMode.Invoker)) {
            // マッチング
            if (invokerKey.equals(target)) {
                return this.analyzeId;
            }
        }

        // マッチしなかった場合、空文字を返却
        return StringUtils.EMPTY;
    }

    /**
     * TODO メソッドのコメント。
     *
     * @param filePath xxx
     * @return String
     */
    private String getExtention(String filePath) {
        String ext = StringUtils.EMPTY;

        // 引数チェック
        if (StringUtils.isEmpty(filePath)) {
            return ext;
        }

        // ファイル名を取得
        String fileName = new File(filePath).getName();

        // ドットの位置を確認
        int pos = fileName.lastIndexOf(".");
        if (pos == -1) {
            // ドットなしの場合は、ファイル名を返却
            ext = fileName;
        } else {
            // ドットありの場合は、ドット以降の文字列を返却
            ext = filePath.substring(pos + 1);
        }

        // 戻り値の返却
        return ext;
    }
}
