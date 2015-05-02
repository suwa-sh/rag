package me.suwash.rag.sv.context.detail;

import java.io.File;

import me.suwash.rag.classification.AnalyzeContextMatchingMode;
import me.suwash.rag.classification.ArgumentType;

import org.apache.commons.lang.StringUtils;

/**
 * 分析コンテキストマッチングルール。
 */
public class AnalyzeContextMatchingRule extends BaseRule {

    /** 分析コンテキストマッチングモード */
    private AnalyzeContextMatchingMode matchingMode;

    /** 分析ID */
    private String analyzeId;

    // --------------------------------------------------
    //  拡張子指定
    // --------------------------------------------------
    /** 拡張子 */
    private String extention;

    // --------------------------------------------------
    //  呼び出し元指定
    // --------------------------------------------------
    /** 呼び出し元指定キー */
    private String invokerKey;
    /** 引数タイプ */
    private ArgumentType argumentType;

    /** 対象ディレクトリ指定キー */
    private String targetDirArgKey;
    /** 対象ファイル指定キー */
    private String targetFileArgKey;

    public AnalyzeContextMatchingMode getMatchingMode() {
        return matchingMode;
    }
    public void setMatchingMode(AnalyzeContextMatchingMode matchingMode) {
        this.matchingMode = matchingMode;
    }


    public String getAnalyzeId() {
        return analyzeId;
    }
    public void setAnalyzeId(String analyzeId) {
        this.analyzeId = analyzeId;
    }

    public String getExtention() {
        return extention;
    }
    public void setExtention(String extention) {
        this.extention = extention;
    }

    public String getInvokerKey() {
        return invokerKey;
    }
    public void setInvokerKey(String invokerKey) {
        this.invokerKey = invokerKey;
    }

    public ArgumentType getArgumentType() {
        return argumentType;
    }
    public void setArgumentType(ArgumentType argumentType) {
        this.argumentType = argumentType;
    }

    public String getTargetDirArgKey() {
        return targetDirArgKey;
    }
    public void setTargetDirArgKey(String targetDirArgKey) {
        this.targetDirArgKey = targetDirArgKey;
    }

    public String getTargetFileArgKey() {
        return targetFileArgKey;
    }
    public void setTargetFileArgKey(String targetFileArgKey) {
        this.targetFileArgKey = targetFileArgKey;
    }

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
