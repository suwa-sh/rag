package me.suwash.rag.sv.context;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.suwash.ddd.exception.SvLayerException;
import me.suwash.rag.classification.AnalyzeContextMatchingMode;
import me.suwash.rag.classification.AnalyzePhase;
import me.suwash.rag.classification.OnErrorOperation;
import me.suwash.rag.sv.context.detail.AnalyzeContextMatchingRule;
import me.suwash.rag.sv.context.detail.PathReplaceRule;
import me.suwash.rag.sv.context.detail.VarEncloseRule;
import me.suwash.rag.sv.domain.analyze.Invoker;
import me.suwash.rag.sv.domain.analyzer.AnalyzerOutput;
import me.suwash.util.Stack;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * システム、プロジェクト、分析文法ごとのコンテキストを一括管理します。
 * コンテキストの利用は、全てこのクラスのメソッドから実施します。
 */
public class ContextManager {

    /** ファイル名：システムコンテキスト */
    private static final String FILENAME_SYSTEM_CONTEXT = "system_context.json";
    /** デフォルトコンテキストディレクトリ */
    private static final String DIR_DEFAULT_ANALYZE_CONTEXT = "/context/default";

    /** Singletoneパターン */
    private static ContextManager instance = new ContextManager();
    public static ContextManager getInstance() {
        return instance;
    }

    /** 読み込み済みフラグ */
    private boolean isLoaded;
    /** システムコンテキスト */
    private SystemContext systemContext;
    /** プロジェクトコンテキスト */
    private ProjectContext projectContext;
    /** デフォルト分析コンテキストMap */
    private Map<String, AnalyzeContext> defaultAnalyzeContextMap;
    /** カスタム分析コンテキストMap */
    private Map<String, AnalyzeContext> customAnalyzeContextMap;
    /** 包含コンテキストListMap */
    private Map<String, List<AnalyzeContext>> includeAnalyzeContextListMap;

    /**
     * コンストラクタ。
     */
    private ContextManager() {
        isLoaded = false;
        defaultAnalyzeContextMap = new HashMap<String, AnalyzeContext>();
        customAnalyzeContextMap = new HashMap<String, AnalyzeContext>();
        includeAnalyzeContextListMap = new HashMap<String, List<AnalyzeContext>>();
    }

    /**
     * システム、プロジェクト、分析文法ごとのコンテキスト定義を読み込みます。
     *
     * @param projectContextFilePath プロジェクトコンテキスト定義のファイルパス
     */
    public void load(String projectContextFilePath) {
        // ================================================================================
        //  事前処理
        // ================================================================================
        // --------------------------------------------------------------------------------
        // パース済みチェック
        // --------------------------------------------------------------------------------
        if (isLoaded) {
            throw new SvLayerException("Sv.E0001");
        }

        // --------------------------------------------------------------------------------
        // 必須チェック
        // --------------------------------------------------------------------------------
        if (StringUtils.isEmpty(projectContextFilePath)) {
            throw new SvLayerException("check.notNull", new Object[] {"projectContextPath"});
        }

        // --------------------------------------------------------------------------------
        // ファイルの存在確認
        // --------------------------------------------------------------------------------
        // システムコンテキスト
        String systemContextFilePath = this.getClass().getResource("/" + FILENAME_SYSTEM_CONTEXT).getPath();
        File systemContextFile = new File(systemContextFilePath);
        if (! systemContextFile.isFile()) {
            throw new SvLayerException("check.notExist", new Object[] {systemContextFile.getAbsolutePath()});
        }

        // プロジェクトコンテキスト
        File projectContextFile = new File(projectContextFilePath);
        if (! projectContextFile.isFile()) {
            throw new SvLayerException("check.notExist", new Object[] {projectContextFile.getAbsolutePath()});
        }

        // デフォルト分析コンテキストディレクトリ
        URL defaultAnalyzeContextUrl = this.getClass().getResource(DIR_DEFAULT_ANALYZE_CONTEXT);
        if (defaultAnalyzeContextUrl == null) {
            throw new SvLayerException("check.notExist", new Object[] {DIR_DEFAULT_ANALYZE_CONTEXT});
        }
        String defaultAnalyzeContextDirPath = defaultAnalyzeContextUrl.getPath();
        File defaultAnalyzeContextDir = new File(defaultAnalyzeContextDirPath);
        if (! defaultAnalyzeContextDir.isDirectory()) {
            throw new SvLayerException("check.notExist", new Object[] {defaultAnalyzeContextDir.getAbsolutePath()});
        }

        // --------------------------------------------------------------------------------
        //  依存オブジェクトの生成
        // --------------------------------------------------------------------------------
        // ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        // ================================================================================
        //  本処理
        // ================================================================================
        // --------------------------------------------------------------------------------
        //  システムコンテキストの作成
        // --------------------------------------------------------------------------------
        // クラスパス直下からシステムコンテキストをパース
        try {
            systemContext = mapper.readValue(systemContextFile, SystemContext.class);
        } catch (Exception e) {
            throw new SvLayerException("Sv.E0003", new Object[] {systemContextFile.getAbsolutePath()}, e);
        }

        // --------------------------------------------------------------------------------
        //  プロジェクトコンテキストの作成
        // --------------------------------------------------------------------------------
        // 引数のパスからプロジェクトコンテキストをパース
        try {
            projectContext = mapper.readValue(projectContextFile, ProjectContext.class);
        } catch (Exception e) {
            throw new SvLayerException("Sv.E0003", new Object[] {projectContextFile.getAbsolutePath()}, e);
        }


        // --------------------------------------------------------------------------------
        //  デフォルト分析コンテキストMapの作成
        // --------------------------------------------------------------------------------
        // デフォルト分析コンテキストファイルを全件ループ
        for (File curAnalyzeContextFile : defaultAnalyzeContextDir.listFiles()) {

            // ファイルのパース
            AnalyzeContext curAnalyzeContext = null;
            try {
                curAnalyzeContext = mapper.readValue(curAnalyzeContextFile, AnalyzeContext.class);
            } catch (Exception e) {
                throw new SvLayerException("Sv.E0003", new Object[] {curAnalyzeContextFile.getAbsolutePath()}, e);
            }

            // デフォルト分析コンテキストMapに追加
            if (StringUtils.isEmpty(curAnalyzeContext.getAnalyzeId())) {
                throw new SvLayerException("Sv.E0004", new Object[] {curAnalyzeContextFile.getAbsolutePath(), "analyzeId"});
            } else {
                defaultAnalyzeContextMap.put(curAnalyzeContext.getAnalyzeId(), curAnalyzeContext);
            }
        }


        // --------------------------------------------------------------------------------
        //  カスタム分析コンテキストMapの作成
        // --------------------------------------------------------------------------------
        // プロジェクトコンテキスト.分析コンテキストディレクトリ
        String customAnalyzeContextDirPath = projectContext.getAnalyzeContextDir();
        if (StringUtils.isEmpty(customAnalyzeContextDirPath)) {
            throw new SvLayerException("Sv.E0004", new Object[] {projectContextFile.getAbsolutePath(), "analyzeContextDir"});
        }
        File customAnalyzeContextDir = new File(customAnalyzeContextDirPath);
        if (! customAnalyzeContextDir.isDirectory()) {
            throw new SvLayerException("check.notExist", new Object[] {customAnalyzeContextDir.getAbsolutePath()});
        }

        // プロジェクトコンテキスト.分析コンテキストディレクトリのファイルを全件ループ
        for (File curAnalyzeContextFile : customAnalyzeContextDir.listFiles()) {

            // ファイルのパース
            AnalyzeContext curAnalyzeContext = null;
            try {
                curAnalyzeContext = mapper.readValue(curAnalyzeContextFile, AnalyzeContext.class);
            } catch (Exception e) {
                throw new SvLayerException("Sv.E0003", new Object[] {curAnalyzeContextFile.getAbsolutePath()}, e);
            }

            // カスタム分析コンテキストMapに追加
            if (StringUtils.isEmpty(curAnalyzeContext.getAnalyzeId())) {
                throw new SvLayerException("Sv.E0004", new Object[] {curAnalyzeContextFile.getAbsolutePath(), "analyzeId"});
            } else {
                customAnalyzeContextMap.put(curAnalyzeContext.getAnalyzeId(), curAnalyzeContext);
            }
        }

        // --------------------------------------------------------------------------------
        //  包含分析コンテキストリストMapの作成
        // --------------------------------------------------------------------------------
        // デフォルト分析コンテキストマップを全件ループ
        for (AnalyzeContext curAnalyzeContext : defaultAnalyzeContextMap.values()) {
            // 当該分析コンテキスト用のリスト
            List<AnalyzeContext> curIncludeList = new ArrayList<AnalyzeContext>();

            // 分析コンテキスト.包含分析コンテキスト名リストを全件ループ
            List<String> curIncludeAnalyzeIdList = curAnalyzeContext.getIncludeAnalyzeIdList();
            if (curIncludeAnalyzeIdList != null) {
                for (String curIncludeAnalyzeId : curIncludeAnalyzeIdList) {
                    // 包含分析コンテキスト名が、デフォルト分析コンテキストマップに存在するか確認
                    if (defaultAnalyzeContextMap.containsKey(curIncludeAnalyzeId)) {
                        // 存在する場合、リストに追加
                        curIncludeList.add(defaultAnalyzeContextMap.get(curIncludeAnalyzeId));
                    } else {
                        // 存在しない場合、エラー
                        throw new SvLayerException("Sv.E0005", new Object[] {
                                getIncludeListMapKey(AnalyzeContextType.Default, curAnalyzeContext.getAnalyzeId()),
                                "includeAnalyzeContextIdList",
                                curIncludeAnalyzeId}
                        );
                    }
                }
            }

            // 包含分析コンテキストリストマップに、作成したリストを追加
            includeAnalyzeContextListMap.put(
                    getIncludeListMapKey(AnalyzeContextType.Default, curAnalyzeContext.getAnalyzeId()),
                    curIncludeList
                    );
        }

        // カスタム分析コンテキストマップを全件ループ
        for (AnalyzeContext curAnalyzeContext : customAnalyzeContextMap.values()) {
            // 当該分析コンテキスト用のリスト
            List<AnalyzeContext> curIncludeList = new ArrayList<AnalyzeContext>();

            // 分析コンテキスト.包含分析コンテキスト名リストを全件ループ
            List<String> curIncludeAnalyzeIdList = curAnalyzeContext.getIncludeAnalyzeIdList();
            if (curIncludeAnalyzeIdList != null) {
                for (String curIncludeAnalyzeId : curIncludeAnalyzeIdList) {
                    // 包含分析コンテキスト名が、デフォルト分析コンテキストマップに存在するか確認
                    if (defaultAnalyzeContextMap.containsKey(curIncludeAnalyzeId)) {
                        // 存在する場合、リストに追加
                        curIncludeList.add(defaultAnalyzeContextMap.get(curIncludeAnalyzeId));
                    } else {
                        // 存在しない場合、エラー
                        throw new SvLayerException("Sv.E0005", new Object[] {
                                getIncludeListMapKey(AnalyzeContextType.Custom, curIncludeAnalyzeId),
                                "includeAnalyzeContextIdList",
                                curIncludeAnalyzeId}
                        );
                    }
                }
            }

            // 包含分析コンテキストリストマップに、作成したリストを追加
            includeAnalyzeContextListMap.put(
                    getIncludeListMapKey(AnalyzeContextType.Custom, curAnalyzeContext.getAnalyzeId()),
                    curIncludeList
                    );
        }


        // ================================================================================
        //  事後処理
        // ================================================================================
        // パース済みフラグを更新
        isLoaded = true;
    }

    /** ContextManager内部で利用する、分析コンテキスト分類 */
    private enum AnalyzeContextType { Default, Custom }

    /**
     * IncludeListMapのキー文字列を返します。
     *
     * @param contextType 分析コンテキスト分類
     * @param analyzeContextId 分析コンテキストID
     * @return IncluseListMapのキー文字列
     */
    private String getIncludeListMapKey(AnalyzeContextType contextType, String analyzeContextId) {
        return contextType + "." + analyzeContextId;
    }

    /**
     * 処理の実行可否をチェックします。
     * 実行できない場合、例外をスローします。
     */
    private void executeCheck() {
        if (! isLoaded) {
            throw new SvLayerException("Sv.E0002");
        }
    }

     /**
      * 分析深度の上限を取得します。
      *
      * @return 分析深度の上限
      */
    public int getAnalyzeDepthLimit() {
        // ================================================================================
        //  事前処理
        // ================================================================================
        // 実行可否チェック
        executeCheck();

        // ================================================================================
        //  本処理
        // ================================================================================
        // プロジェクトコンテキストの設定値を確認
        int returnValue = projectContext.getAnalyzeDepthLimit();
        if (SystemContext.UNDEFINED_ANALYZE_DEPTH_LIMIT != returnValue) {
            // 設定されている場合、プロジェクトコンテキストの値を返却
            return returnValue;
        }

        // システムコンテキストの設定値を確認
        returnValue = systemContext.getAnalyzeDepthLimit();
        if (SystemContext.UNDEFINED_ANALYZE_DEPTH_LIMIT != returnValue) {
            // 設定されている場合、システムコンテキストの値を返却
            return returnValue;
        }

        // ================================================================================
        //  事後処理
        // ================================================================================
        // 未設定の場合、エラー
        throw new SvLayerException("Sv.E0011", new Object[] {"analyzeDepthLimit"});
    }

    /**
     * エラー時の制御区分を返します。
     *
     * @return エラー時の制御区分
     */
    public OnErrorOperation getOnError() {
        // ================================================================================
        //  事前処理
        // ================================================================================
        // 実行可否チェック
        executeCheck();

        // ================================================================================
        //  本処理
        // ================================================================================
        // プロジェクトコンテキストの設定値を確認
        OnErrorOperation returnValue = projectContext.getOnError();
        if (returnValue != null) {
            // 設定されている場合、プロジェクトコンテキストの値を返却
            return returnValue;
        }

        // システムコンテキストの設定値を確認
        returnValue = systemContext.getOnError();
        if (returnValue != null) {
            // 設定されている場合、システムコンテキストの値を返却
            return returnValue;
        }

        // ================================================================================
        //  事後処理
        // ================================================================================
        // 未設定の場合、エラー
        throw new SvLayerException("Sv.E0011", new Object[] {"onErrorOperation"});
    }


    /** 呼び出し元マッチングルールリスト */
    private List<AnalyzeContextMatchingRule> invokerMatchingRuleList;
    /** 拡張子マッチングルールリスト */
    private List<AnalyzeContextMatchingRule> extMatchingRuleList;

    /**
     * 分析コンテキストマッチングフェーズ。
     * 下記のいずれかを指定して、対応する分析コンテキストIDを返します。
     * ・対象ファイルパス
     * ・対象呼び出しシグネチャ と 現在の呼び出しスタック
     *
     * @param targetPath 対象ファイルパス
     * @param targetSignature 対象呼び出しシグネチャ
     * @param invokerStack 呼び出しスタック
     * @return 分析コンテキストID
     */
    public String getAnalyzeId(String targetPath, String targetSignature, Stack<Invoker> invokerStack) {
        // ================================================================================
        //  事前処理
        // ================================================================================
        // 実行可否チェック
        executeCheck();

        // 引数チェック
        if (StringUtils.isEmpty(targetPath) && StringUtils.isEmpty(targetSignature)) {
            throw new SvLayerException("Sv.E0009", new Object[] {"targetPath", "targetSignature"});
        }
        if (! StringUtils.isEmpty(targetSignature)) {
            if (invokerStack == null || invokerStack.isEmpty()) {
                throw new SvLayerException("Sv.E0010", new Object[] {"targetSignature", "invokerStack"});
            }
        }

        // 呼び出し元マッチングルールリストの確認
        if (invokerMatchingRuleList == null) {
            invokerMatchingRuleList = new ArrayList<AnalyzeContextMatchingRule>();
            extMatchingRuleList = new ArrayList<AnalyzeContextMatchingRule>();

            // Projectコンテキストのマッチング設定を読み込み
            for (AnalyzeContextMatchingRule matchingRule : projectContext.getMatchingRuleList()) {
                if (AnalyzeContextMatchingMode.Invoker.equals(matchingRule.getMatchingMode())) {
                    // 呼び出し元マッチングルールリストに追加
                    invokerMatchingRuleList.add(matchingRule);
                } else {
                    // 拡張子マッチングルールリストに追加
                    extMatchingRuleList.add(matchingRule);
                }
            }

            // Systemコンテキストのマッチング設定を読み込み
            // ※マッチングは先勝ちさせるので、Projectコンテキストで上書きした状態になる
            for (AnalyzeContextMatchingRule matchingRule : systemContext.getMatchingRuleList()) {
                if (AnalyzeContextMatchingMode.Invoker.equals(matchingRule.getMatchingMode())) {
                    // 呼び出し元マッチングルールリストに追加
                    invokerMatchingRuleList.add(matchingRule);
                } else {
                    // 拡張子マッチングルールリストに追加
                    extMatchingRuleList.add(matchingRule);
                }
            }
        }

        // ================================================================================
        //  本処理
        // ================================================================================
        // 呼び出し元スタックの存在確認
        if (invokerStack != null && ! invokerStack.isEmpty()) {
            // 呼び出し元でのマッチング
            for (AnalyzeContextMatchingRule invokerMatcingRule : invokerMatchingRuleList) {
                return invokerMatcingRule.getMatchedAnalyzeId(targetSignature);
            }
        }

        // 拡張子でのマッチング
        for (AnalyzeContextMatchingRule extMatchingRule : extMatchingRuleList) {
            return extMatchingRule.getMatchedAnalyzeId(targetPath);
        }

        // ================================================================================
        //  事後処理
        // ================================================================================
        // マッチしない場合、エラー
        throw new SvLayerException("Sv.E0008");
    }

    /**
     * パス置換フェーズ。
     * 指定された分析IDのルールに従い、パスを置換します。
     * フェーズの処理詳細は、引数の分析状態オブジェクトに格納します。
     *
     * @param analyzeId 分析ID
     * @param target 置換対象
     * @param output 分析結果出力オブジェクト
     * @return 置換結果
     */
    public String getReplacedPath(String analyzeId, String target, AnalyzerOutput output) {
        // ================================================================================
        //  事前処理
        // ================================================================================
        // 実行可否チェック
        executeCheck();

        // 必須チェック
        if (StringUtils.isEmpty(analyzeId)) {
            throw new SvLayerException("check.notNull", new Object[] {"analyzeId"});
        }
        if (StringUtils.isEmpty(target)) {
            throw new SvLayerException("check.notNull", new Object[] {"target"});
        }


        // ================================================================================
        //  本処理
        // ================================================================================
        List<PathReplaceRule> appliedRuleList = new ArrayList<PathReplaceRule>();

        // カスタム分析コンテキストでの置換
        String replacedValue = getReplacedPath(AnalyzeContextType.Custom, analyzeId, target, appliedRuleList);

        // デフォルト分析コンテキストでの置換
        replacedValue = getReplacedPath(AnalyzeContextType.Default, analyzeId, replacedValue, appliedRuleList);


        // ================================================================================
        //  事後処理
        // ================================================================================
        // 置換結果の確認
        if (target.equals(replacedValue)) {
            // 置換がかからなかった場合、ワーニングを登録
            output.addWarn(AnalyzePhase.ReplacePath, new SvLayerException("Sv.W0001", new Object[]{target}));
        }

        // 適用したルールリストを分析状態に登録
        output.getCurrentDetail().setPathReplaceRuleList(appliedRuleList);

        // 置換後の文字列を返却
        return replacedValue;
    }

    /**
     * パス置換フェーズの本処理。
     *
     * @param analyzeContextType 分析コンテキスト分類
     * @param analyzeId 分析コンテキストID
     * @param target 置換元の文字列（ファイルパス）
     * @param appliedRuleList 編集用適用されたルールリスト
     * @return 置換後の文字列（置換後ファイルパス）
     */
    private String getReplacedPath(AnalyzeContextType analyzeContextType, String analyzeId, String target, List<PathReplaceRule> appliedRuleList) {
        String beforeValue = target;
        String afterValue = target;

        // 分析コンテキストタイプの確認
        Map<String, AnalyzeContext> contextMap;
        if (analyzeContextType.equals(AnalyzeContextType.Default)) {
            contextMap = defaultAnalyzeContextMap;
        } else {
            contextMap = customAnalyzeContextMap;
        }

        // 分析IDの存在確認
        if (contextMap.containsKey(analyzeId)) {
            AnalyzeContext analyzeContext = contextMap.get(analyzeId);

            // ------------------------------------------------------------
            //  対象コンテキストでの置換
            // ------------------------------------------------------------
            List<PathReplaceRule> pathReplaceRule = analyzeContext.getPathReplaceRuleList();
            if (pathReplaceRule != null) {
                for (PathReplaceRule rule : pathReplaceRule) {
                    afterValue = rule.getReplacedPath(beforeValue);
                    if (! beforeValue.equals(afterValue)) {
                        beforeValue = afterValue;
                        // 適用されたルールをリストに追加
                        appliedRuleList.add(rule);
                    }
                }
            }

            // ------------------------------------------------------------
            //  対象コンテキストの包含リストでの置換
            // ------------------------------------------------------------
            String includeListMapKey = getIncludeListMapKey(analyzeContextType, analyzeId);
            if (includeAnalyzeContextListMap.containsKey(includeListMapKey)) {
                List<AnalyzeContext> includeList = includeAnalyzeContextListMap.get(includeListMapKey);
                for (AnalyzeContext includeContext : includeList) {

                    pathReplaceRule = includeContext.getPathReplaceRuleList();
                    if (pathReplaceRule != null) {
                        for (PathReplaceRule rule : pathReplaceRule) {
                            afterValue = rule.getReplacedPath(beforeValue);
                            if (! beforeValue.equals(afterValue)) {
                                beforeValue = afterValue;
                                // 適用されたルールをリストに追加
                                appliedRuleList.add(rule);
                            }
                        }
                    }
                }
            }
        }
        return afterValue;
    }


    public List<VarEncloseRule> getVarEncloseRuleList(String analyzeId) {
        // ================================================================================
        //  事前処理
        // ================================================================================
        // 実行可否チェック
        executeCheck();

        // 必須チェック
        if (StringUtils.isEmpty(analyzeId)) {
            throw new SvLayerException("check.notNull", new Object[] {"analyzeId"});
        }


        // ================================================================================
        //  本処理
        // ================================================================================
        List<VarEncloseRule> returnList = new ArrayList<VarEncloseRule>();
        // 分析IDの存在確認
        if (defaultAnalyzeContextMap.containsKey(analyzeId)) {
            // --------------------------------------------------
            //  デフォルト分析コンテキストの括り文字ルールリストを取得
            // --------------------------------------------------
            AnalyzeContext analyzeContext = defaultAnalyzeContextMap.get(analyzeId);

            // 対象コンテキストの括り文字リスト
            List<VarEncloseRule> curVarEncloseRuleList = analyzeContext.getVarEncloseRuleList();
            if (curVarEncloseRuleList != null) {
                for (VarEncloseRule rule : curVarEncloseRuleList) {
                    if (returnList.contains(rule)) {
                        returnList.remove(rule);
                    }
                    returnList.add(rule);
                }
            }

            // 対象コンテキストの包含リストの括り文字リスト
            String includeListMapKey = getIncludeListMapKey(AnalyzeContextType.Default, analyzeId);
            if (includeAnalyzeContextListMap.containsKey(includeListMapKey)) {
                List<AnalyzeContext> includeList = includeAnalyzeContextListMap.get(includeListMapKey);
                for (AnalyzeContext includeContext : includeList) {

                    curVarEncloseRuleList = includeContext.getVarEncloseRuleList();
                    if (curVarEncloseRuleList != null) {
                        for (VarEncloseRule rule : curVarEncloseRuleList) {
                            if (returnList.contains(rule)) {
                                returnList.remove(rule);
                            }
                            returnList.add(rule);
                        }
                    }
                }
            }

            // --------------------------------------------------
            //  カスタム分析コンテキストの括り文字ルールリストを取得
            // --------------------------------------------------
            analyzeContext = customAnalyzeContextMap.get(analyzeId);

            // 対象コンテキストの括り文字リスト
            curVarEncloseRuleList = analyzeContext.getVarEncloseRuleList();
            if (curVarEncloseRuleList != null) {
                for (VarEncloseRule rule : curVarEncloseRuleList) {
                    if (returnList.contains(rule)) {
                        returnList.remove(rule);
                    }
                    returnList.add(rule);
                }
            }

            // 対象コンテキストの包含リストの括り文字リスト
            includeListMapKey = getIncludeListMapKey(AnalyzeContextType.Custom, analyzeId);
            if (includeAnalyzeContextListMap.containsKey(includeListMapKey)) {
                List<AnalyzeContext> includeList = includeAnalyzeContextListMap.get(includeListMapKey);
                for (AnalyzeContext includeContext : includeList) {

                    curVarEncloseRuleList = includeContext.getVarEncloseRuleList();
                    if (curVarEncloseRuleList != null) {
                        for (VarEncloseRule rule : curVarEncloseRuleList) {
                            if (returnList.contains(rule)) {
                                returnList.remove(rule);
                            }
                            returnList.add(rule);
                        }
                    }
                }
            }
        }


        // ================================================================================
        //  事後処理
        // ================================================================================
        // マージ後のリストを返却
        return returnList;
    }

}
