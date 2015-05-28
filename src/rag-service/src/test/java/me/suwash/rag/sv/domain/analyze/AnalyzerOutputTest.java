package me.suwash.rag.sv.domain.analyze;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import me.suwash.ddd.exception.SvLayerException;
import me.suwash.rag.classification.AnalyzePhase;
import me.suwash.rag.classification.AnalyzeStatus;
import me.suwash.rag.sv.domain.analyzer.AnalyzerOutput;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO クラスの説明。
 */
public class AnalyzerOutputTest {

    /**
     * TODO メソッドのコメント。
     *
     * @throws Exception xxx
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    /**
     * TODO メソッドのコメント。
     *
     * @throws Exception xxx
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /**
     * TODO メソッドのコメント。
     *
     * @throws Exception xxx
     */
    @Before
    public void setUp() throws Exception {}

    /**
     * TODO メソッドのコメント。
     *
     * @throws Exception xxx
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testAddDetail() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        AnalyzerOutput target = new AnalyzerOutput();
        target.addSuccess(AnalyzePhase.Finished);

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        AnalyzeStatus actual = target.getStatus();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals("登録した分析詳細が反映されていること", AnalyzeStatus.Success, actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetDetailList() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        AnalyzerOutput target = new AnalyzerOutput();
        target.addSuccess(AnalyzePhase.Finished);

        target.setCurrentDetail();
        target.addError(AnalyzePhase.ContextMatching, new SvLayerException("dummy"));

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        List<AnalyzeDetail> result = target.getDetailList();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals("登録した2件が取り出せること", 2, result.size());
        assertEquals("登録した順番に取り出せること", AnalyzeStatus.Success, result.get(0).getStatus());
        assertEquals("登録した順番に取り出せること", AnalyzeStatus.Error, result.get(1).getStatus());
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetAnalyzedCount() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        AnalyzerOutput target = new AnalyzerOutput();
        target.addSuccess(AnalyzePhase.Finished);

        target.setCurrentDetail();
        target.addError(AnalyzePhase.ContextMatching, new SvLayerException("dummy"));

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        int actual = target.getAnalyzedCount();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals("登録した件数が取り出せること", 2, actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetStatus_Success() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        AnalyzerOutput target = new AnalyzerOutput();
        target.addSuccess(AnalyzePhase.Finished);

        target.setCurrentDetail();
        target.addSuccess(AnalyzePhase.Finished);

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        AnalyzeStatus actual = target.getStatus();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals("よりエラーレベルの高い結果が取り出せること", AnalyzeStatus.Success, actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetStatus_Warning() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        AnalyzerOutput target = new AnalyzerOutput();
        target.addSuccess(AnalyzePhase.Finished);

        target.setCurrentDetail();
        target.addWarn(AnalyzePhase.ContextMatching, new SvLayerException("dummy"));

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        AnalyzeStatus actual = target.getStatus();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals("よりエラーレベルの高い結果が取り出せること", AnalyzeStatus.Warning, actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetStatus_Error() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        AnalyzerOutput target = new AnalyzerOutput();
        target.addSuccess(AnalyzePhase.Finished);

        target.setCurrentDetail();
        target.addError(AnalyzePhase.ContextMatching, new SvLayerException("dummy"));

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        AnalyzeStatus actual = target.getStatus();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals("よりエラーレベルの高い結果が取り出せること", AnalyzeStatus.Error, actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetStatus_Processing() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        AnalyzerOutput target = new AnalyzerOutput();
        target.addSuccess(AnalyzePhase.Finished);

        target.setCurrentDetail();

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        AnalyzeStatus actual = target.getStatus();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals("よりエラーレベルの高い結果が取り出せること", AnalyzeStatus.Processing, actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetStatus_Cancel() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        AnalyzerOutput target = new AnalyzerOutput();
        target.addSuccess(AnalyzePhase.Finished);

        target.setCurrentDetail();
        target.addCancel(AnalyzePhase.ContextMatching, new SvLayerException("dummy"));

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        AnalyzeStatus actual = target.getStatus();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals("よりエラーレベルの高い結果が取り出せること", AnalyzeStatus.Cancel, actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetStatusCountMap() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        AnalyzerOutput target = new AnalyzerOutput();
        target.addSuccess(AnalyzePhase.Finished);

        target.setCurrentDetail();
        target.addWarn(AnalyzePhase.ContextMatching, new SvLayerException("dummy"));

        target.setCurrentDetail();
        target.addError(AnalyzePhase.ContextMatching, new SvLayerException("dummy"));

        target.setCurrentDetail();
        target.addCancel(AnalyzePhase.ContextMatching, new SvLayerException("dummy"));

        target.setCurrentDetail();
        target.addSuccess(AnalyzePhase.Finished);

        target.setCurrentDetail();

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        Map<AnalyzeStatus, Integer> actual = target.getStatusCountMap();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals("登録したステータスごとの件数が取り出せること", new Integer(2), actual.get(AnalyzeStatus.Success));
        assertEquals("登録したステータスごとの件数が取り出せること", new Integer(1), actual.get(AnalyzeStatus.Warning));
        assertEquals("登録したステータスごとの件数が取り出せること", new Integer(1), actual.get(AnalyzeStatus.Error));
        assertEquals("登録したステータスごとの件数が取り出せること", new Integer(1), actual.get(AnalyzeStatus.Cancel));
        assertEquals("登録したステータスごとの件数が取り出せること", new Integer(1), actual.get(AnalyzeStatus.Processing));
    }

}
