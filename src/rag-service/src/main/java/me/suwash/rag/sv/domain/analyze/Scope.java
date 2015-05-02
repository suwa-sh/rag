package me.suwash.rag.sv.domain.analyze;

import java.util.HashMap;
import java.util.Map;

import me.suwash.ddd.exception.SvLayerException;
import me.suwash.rag.sv.context.ContextManager;
import me.suwash.rag.sv.context.detail.VarEncloseRule;

import org.apache.commons.lang3.StringUtils;

public class Scope {
    private Map<String, Variable> varMap = new HashMap<String, Variable>();

    public Variable getVariable(String name) {
        // 必須チェック
        if (StringUtils.isEmpty(name)) {
            throw new SvLayerException("check.notNull", new Object[]{"analyzeId"});
        }

        // 変数名をkeyにして変数Mapを確認
        if (varMap.containsKey(name)) {
            // 変数名で登録されている場合、変数オブジェクトを返却
            return varMap.get(name);
        }

        // 見つからない場合、nullを返却
        return null;
    }

    public Variable getVariable(String analyzeId, String name) {
        // 必須チェック
        if (StringUtils.isEmpty(name)) {
            throw new SvLayerException("check.notNull", new Object[]{"name"});
        }

        // analyzeIdが指定されていない場合、指定なしのメソッドに処理を移譲
        if (StringUtils.isEmpty(analyzeId)) {
            return getVariable(name);
        }

        // AnalyzeContext　の変数括り文字パターンをループ
        for (VarEncloseRule varEncloseRule : ContextManager.getInstance().getVarEncloseRuleList(analyzeId)) {
            String prefix = varEncloseRule.getPrefix();
            String surfix = varEncloseRule.getSurfix();

            // 括った変数名で登録されている場合、変数オブジェクトを返却
            String curVarMapKey = prefix + name + surfix;
            if (varMap.containsKey(curVarMapKey)) {
                return varMap.get(curVarMapKey);
            }
        }

        // 見つからない場合、nullを返却
        return null;
    }


    public void addVariable(Variable variable) {
        varMap.put(variable.getName(), variable);
    }


    public void addVariable(String analyzeId, Variable variable) {
        // analyzeIdが指定されていない場合、指定なしのメソッドに処理を移譲
        if (StringUtils.isEmpty(analyzeId)) {
            addVariable(variable);
            return;
        }

        // AnlyzeContext の変数括り文字パターンをループ
        for (VarEncloseRule varEncloseRule : ContextManager.getInstance().getVarEncloseRuleList(analyzeId)) {
            String prefix = varEncloseRule.getPrefix();
            String surfix = varEncloseRule.getSurfix();

            // 括った変数名でput
            varMap.put(prefix + variable.getName() + surfix, variable);
        }

        // analyzeId指定なしで、変数をput
        addVariable(variable);
    }
}
