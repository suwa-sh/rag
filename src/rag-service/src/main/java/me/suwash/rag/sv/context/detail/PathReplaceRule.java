package me.suwash.rag.sv.context.detail;

import java.lang.reflect.Field;

import me.suwash.ddd.exception.SvLayerException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.util.ReflectionUtils;

/**
 * Path置換ルール。
 *
 * ・正規表現
 * regexフィールドがtrueの場合、fromを正規表現として解釈します。
 *
 * ・暗黙変数
 * org.apache.commons.lang3.SystemUtilsの定数フィールドを${}で括った文字を、実態に置換して利用できます。
 * ${FILE_SEPARATOR}, ${OS_NAME}など
 *
 */
public class PathReplaceRule extends BaseRule {

    /** 置換元文字列 */
    private String from;
    /** 置換後文字列 */
    private String to;
    /** 正規表現を使用するか */
    private boolean regex;

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }

    public boolean isRegex() {
        return regex;
    }
    public void setRegex(boolean regex) {
        this.regex = regex;
    }

    /**
     * ルールに従い置換した結果を返します。
     *
     * @param target 置換対象
     * @return 置換結果
     */
    public String getReplacedPath(String target) {
        // --------------------------------------------------
        //  暗黙変数の置換
        // --------------------------------------------------
        for (Field field : SystemUtils.class.getFields()) {
            if (ReflectionUtils.isPublicStaticFinal(field)) {
                try {
                    to = to.replaceAll("\\Q" + "${" + field.getName() + "}" + "\\E", StringUtils.EMPTY + field.get(null));
                } catch (Exception e) {
                    throw new SvLayerException("Sv.E0000", e);
                }
            }
        }

        // --------------------------------------------------
        //  主処理
        // --------------------------------------------------
        if (regex) {
            // 正規表現が有効の場合
            return target.replaceAll(from, to);
        } else {
            // 正規表現が無効の場合
            return target.replaceAll("\\Q" + from + "\\E", to);
        }
    }

}
