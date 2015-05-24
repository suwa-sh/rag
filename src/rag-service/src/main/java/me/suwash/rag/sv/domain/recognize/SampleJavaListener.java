package me.suwash.rag.sv.domain.recognize;

import java.util.HashMap;
import java.util.Map;

import me.suwash.rag.recognize.java.JavaBaseListener;
import me.suwash.rag.recognize.java.JavaParser.ExpressionContext;
import me.suwash.rag.recognize.java.JavaParser.FieldDeclarationContext;
import me.suwash.rag.recognize.java.JavaParser.ImportDeclarationContext;
import me.suwash.rag.recognize.java.JavaParser.LocalVariableDeclarationContext;
import me.suwash.rag.recognize.java.JavaParser.QualifiedNameContext;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO クラスの説明。
 */
public class SampleJavaListener extends JavaBaseListener {

    private Map<String, Map<String, String>> varMap;

    /**
     * コンストラクタ。
     */
    public SampleJavaListener() {
        varMap = new HashMap<String, Map<String, String>>();
    }

    /**
     * TODO メソッドのコメント。
     *
     * @return xxx
     */
    public Map<String, Map<String, String>> getVarMap() {
        return this.varMap;
    }

    @Override
    public void enterImportDeclaration(ImportDeclarationContext ctx) {
        // TODO 自動生成されたメソッド・スタブ
        for (ParseTree child : ctx.children) {
            if (child instanceof QualifiedNameContext) {
                System.out.println("[import]:" + ((QualifiedNameContext) child).getText());
            }
        }
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        // System.out.println("[ENTER]" + ctx.getText());
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        // System.out.println("[EXIT ]" + ctx.getText());
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        // System.out.println("[TERMINAL]" + node.getText());
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        // System.out.println("[ERROR]" + node.getText());
    }

    /*
     * (非 Javadoc)
     * @see me.suwash.rag.recognize.java.JavaBaseListener#enterExpression(me.suwash.rag.recognize.java.JavaParser.ExpressionContext)
     */
    @Override
    public void enterExpression(ExpressionContext ctx) {
        if (ctx.getChildCount() >= 3 && "=".equals(ctx.getChild(1).getText())) {
            addVarMap(ctx);
        }
    }

    @Override
    public void enterFieldDeclaration(FieldDeclarationContext ctx) {
        addVarMap(ctx);
    }

    @Override
    public void enterLocalVariableDeclaration(
        LocalVariableDeclarationContext ctx) {
        addVarMap(ctx);
    }

    /**
     * TODO メソッドのコメント。
     *
     * @param ctx xxx
     */
    private void addVarMap(ParserRuleContext ctx) {
        System.out.println("・変数定義分解：" + ctx.getText());

        // =の位置を確認
        int equalIndex = -1;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if ("=".equals(ctx.getChild(i).getText())) {
                equalIndex = i;
            }
        }

        String type = StringUtils.EMPTY;
        ParseTree varDef;
        if (equalIndex == 1) {
            // 代入式の場合
            // 型：なし
            // 変数定義：値
            varDef = ctx.getChild(0).getChild(0);
        } else {
            // 変数宣言の場合
            // 変数定義：型
            type = ctx.getChild(0).getText();
            // 変数定義：値
            varDef = ctx.getChild(1).getChild(0);
        }

        // 変数定義：値.変数名
        String name = varDef.getChild(0).getText();

        // 変数定義：値.設定値
        String value = StringUtils.EMPTY;
        String valueDef = StringUtils.EMPTY;

        if (varDef.getChildCount() < 3) {
            System.out.println("　・skip:" + ctx.getText());

        } else {
            valueDef = varDef.getChild(2).getText();
            if (valueDef.length() > "new".length() && "new".equals(valueDef.substring(0, 3))) {
                // インスタンス生成式の場合
                value = valueDef.replaceFirst("new", StringUtils.EMPTY);

            } else if ("{".equals(valueDef.substring(0, 1))) {
                // 配列初期化子の場合
                StringBuilder valueBuilder = new StringBuilder();
                for (int i = 0; i < varDef.getChildCount(); i++) {
                    if (i != 0) {
                        // { をスキップ
                    } else if (i != varDef.getChildCount() - 1) {
                        // } をスキップし、最後のカンマを除去
                        value = valueBuilder.substring(0, valueBuilder.length() - 2);
                    } else {
                        valueBuilder.append(varDef.getChild(i).getText() + ",");
                    }
                }

            } else {
                // その他
                value = valueDef;
                // throw new RuntimeException("変数定義の分解で「" + varDef.getText() + "」は、想定外の値です。");
            }
        }

        // 変数の登録 TODO スコープを考慮する
        Map<String, String> var = new HashMap<String, String>();
        var.put(type, value);
        this.varMap.put(name, var);
    }
}
