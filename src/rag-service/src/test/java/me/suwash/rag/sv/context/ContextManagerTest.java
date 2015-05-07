package me.suwash.rag.sv.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import me.suwash.ddd.exception.SvLayerException;
import me.suwash.ddd.i18n.layer.sv.SvLayerDdSource;
import me.suwash.rag.classification.AnalyzePhase;
import me.suwash.rag.classification.OnErrorOperation;
import me.suwash.rag.sv.context.detail.PathReplaceRule;
import me.suwash.rag.sv.domain.analyze.AnalyzeDetail;
import me.suwash.rag.sv.domain.analyzer.AnalyzerOutput;
import me.suwash.util.i18n.DdSource;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ContextManagerTest {

    private static ContextManager contextManager;
    private static String projectContextFilePath = "src/test/scripts/rag/project_ut/context/project_ut_context.json";
//    private static String projectContextFilePath = "/Users/suwa_sh/Documents/sts-bundle/workspace/rag-service/src/test/scripts/rag/project_ut/context/project_ut_context_pattern2.json";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        contextManager = ContextManager.getInstance();
        contextManager.load(projectContextFilePath);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        contextManager = null;
    }

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    @Test
    public final void testGetInstance() {
        ContextManager expected = ContextManager.getInstance();
        ContextManager actual = ContextManager.getInstance();

        assertTrue("同一のインスタンスを返すこと", expected == actual);
    }

//    @Test
//    public final void testLoad_nullを指定した場合() {
//        ContextManager contextManager = ContextManager.getInstance();
//        String projectContextFilePath = null;
//
//        String expectMessageId = "check.notNull";
//
//        try {
//            contextManager.load(projectContextFilePath);
//            fail("例外をスローすること");
//        } catch (SvLayerException e) {
//            assertEquals("必須チェックエラー", expectMessageId, e.getMessageId());
//            System.out.println(e.getMessage());
//        }
//    }

//    @Test
//    public final void testLoad_空文字を指定した場合() {
//        ContextManager contextManager = ContextManager.getInstance();
//        String projectContextFilePath = StringUtils.EMPTY;
//
//        String expectMessageId = "check.notNull";
//
//        try {
//            contextManager.load(projectContextFilePath);
//            fail("例外をスローすること");
//        } catch (SvLayerException e) {
//            assertEquals("必須チェックエラー", expectMessageId, e.getMessageId());
//            System.out.println(e.getMessage());
//        }
//    }

//    @Test
//    public final void testLoad_存在しないパスを指定した場合() {
//        ContextManager contextManager = ContextManager.getInstance();
//        String projectContextFilePath = "NotExistPath";
//
//        String expectMessageId = "check.notExist";
//
//        try {
//            contextManager.load(projectContextFilePath);
//            fail("例外をスローすること");
//        } catch (SvLayerException e) {
//            assertEquals("存在チェックエラー", expectMessageId, e.getMessageId());
//            System.out.println(e.getMessage());
//        }
//    }

//    @Test
//    public final void testLoad_複数回実行した場合() {
//        String expectMessageId = "Sv.E0001";
//
//        // 1回目
//        contextManager.load(projectContextFilePath);
//
//        try {
//            // 2回目
//            contextManager.load(projectContextFilePath);
//            fail("例外をスローすること");
//        } catch (SvLayerException e) {
//            assertEquals("複数回読み込みエラー", expectMessageId, e.getMessageId());
//            System.out.println(e.getMessage());
//        }
//    }

//    @Test
//    public final void testGetReplacedPath_load実施前に呼び出した場合() {
//        String analyzeId = "analyzeId";
//        String target = "target";
//        AnalyzerOutput output = new AnalyzerOutput();
//
//        String expectMessageId = "Sv.E0002";
//
//        try {
//            contextManager.getReplacedPath(analyzeId, target, output);
//            fail("例外をスローすること");
//        } catch (SvLayerException e) {
//            assertEquals("未読み込みエラー", expectMessageId, e.getMessageId());
//            System.out.println(e.getMessage());
//        }
//    }

    @Test
    public final void testGetReplacedPath_分析コンテキストIDが未設定の場合() {
        String analyzeId = null;
        String target = "target";
        AnalyzerOutput output = new AnalyzerOutput();

        String expectMessageId = "check.notNull";

        try {
            contextManager.getReplacedPath(analyzeId, target, output);
            fail("例外をスローすること");
        } catch (SvLayerException e) {
            assertEquals("必須チェックエラー", expectMessageId, e.getMessageId());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public final void testGetReplacedPath_対象パスが未設定の場合() {
        String analyzeId = "analyzeId";
        String target = StringUtils.EMPTY;
        AnalyzerOutput output = new AnalyzerOutput();

        String expectMessageId = "check.notNull";

        try {
            contextManager.getReplacedPath(analyzeId, target, output);
            fail("例外をスローすること");
        } catch (SvLayerException e) {
            assertEquals("必須チェックエラー", expectMessageId, e.getMessageId());
            System.out.println(e.getMessage());
        }
    }

    @Ignore
    @Test
    public final void testGetReplacedPath_パス置換ルールにマッチしない場合() {
        String analyzeId = "java";
        String target = "/notExistPath/target.bat";
        AnalyzerOutput output = new AnalyzerOutput();

        AnalyzePhase expectPhase = AnalyzePhase.ReplacePath;
        String expectMessageId = "Sv.W0001";

        contextManager.getReplacedPath(analyzeId, target, output);

        for (AnalyzeDetail warning : output.getWarningList()) {
            assertEquals("パス置換フェーズでワーニングが発生していること", expectPhase, warning.getPhase());
            assertEquals("ワーニンングの内容が、置換ルールが未適用であること", expectMessageId, warning.getException().getMessageId());
            DdSource ddSource = SvLayerDdSource.getInstance();
            System.out.println(
                    "phase:" + ddSource.getName(warning.getPhase().ddId()) + ", error:" + warning.getException().getMessage());
        }
    }

    @Ignore
    @Test
    public final void testGetReplacedPath() {
        String analyzeId = "java";
        String target = "me.suwash.rag.test.Target";
        AnalyzerOutput output = new AnalyzerOutput();

        String expect = "/path/to/workspace/rag/src/main/jp/co/presa/rag/test/Target.java";
        int expectPathReplaceRuleCount = 3;

        String actual = contextManager.getReplacedPath(analyzeId, target, output);

        assertEquals("置換されていること", expect, actual);
        System.out.println("・getReplacedPath(\"" + analyzeId + "\", \"" + target + "\", analyzeProcessState)");
        System.out.println("  ・result:" + actual);
        System.out.println("  ・appliedRules:");

        List<PathReplaceRule> pathReplaceRuleList = output.getCurrentDetail().getPathReplaceRuleList();
        assertEquals(expectPathReplaceRuleCount, pathReplaceRuleList.size());
        for (PathReplaceRule rule : pathReplaceRuleList) {
            System.out.println("    ・" + rule.toString());
        }
    }

    @Test
    public final void testGetAnalyzeDepthLimit() {
        int expect = 100;

        int actual = contextManager.getAnalyzeDepthLimit();

        assertEquals("プロジェクトコンテキストの値が返却されること", expect, actual);
    }

//    @Test
//    /**
//     * 実行する場合は、project_ut_context_pattern2.json を利用すること
//     */
//    public final void testGetAnalyzeDepthLimit_プロジェクトコンテキストに設定が存在しない場合() {
//        int expect = 10;
//
//        int actual = contextManager.getAnalyzeDepthLimit();
//
//        assertEquals("システムコンテキストの値が返却されること", expect, actual);
//    }

    @Test
    public final void testGetOnError() {
        OnErrorOperation expect = OnErrorOperation.Abort;

        OnErrorOperation actual = contextManager.getOnError();

        assertEquals("プロジェクトコンテキストの値が返却されること", expect, actual);
    }

//    @Test
//    /**
//     * 実行する場合は、project_ut_context_pattern2.json を利用すること
//     */
//    public final void testGetOnError_プロジェクトコンテキストに設定が存在しない場合() {
//        OnErrorOperation expect = OnErrorOperation.Skip;
//
//        OnErrorOperation actual = contextManager.getOnError();
//
//        assertEquals("システムコンテキストの値が返却されること", expect, actual);
//    }

//    @Test
//    public final void test動作確認シグネチャ確認() {
//        // TODO 動作確認 シグネチャ取得
//        for (Method method : String.class.getDeclaredMethods()) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(method.getDeclaringClass().getName());
//            sb.append("#");
//            sb.append(method.getName());
//            sb.append("(");
//            Class<?>[] argClasses = method.getParameterTypes();
//            if (argClasses.length > 0) {
//                for (Class<?> argClass : argClasses) {
//                    sb.append(argClass.getSimpleName());
//                    sb.append(", ");
//                }
//                sb.delete(sb.length() - 2, sb.length());
//            }
//            sb.append(")");
//
//            System.out.println(sb);
//        }
//
//        ProcessBuilder pb = new ProcessBuilder(new String[] {"echo", "This is Test!"});
//        try {
//            Process process = pb.start();
//            process.waitFor();
//            System.out.println(process.exitValue());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
