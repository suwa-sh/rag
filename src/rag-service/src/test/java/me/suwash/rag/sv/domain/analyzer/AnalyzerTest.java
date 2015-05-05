package me.suwash.rag.sv.domain.analyzer;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;

import me.suwash.ddd.classification.ProcessStatus;
import me.suwash.rag.classification.AnalyzeStatus;
import me.suwash.rag.classification.AnalyzeTargetType;
import me.suwash.rag.sv.domain.analyze.AnalyzeWork;
import me.suwash.rag.sv.domain.analyzer.AnalyzerInput;
import me.suwash.rag.sv.domain.analyzer.AnalyzerOutput;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-test.xml")
public class AnalyzerTest {

    @Inject
    Analyzer analyzer;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    @Test
    public final void testAnalyze_Validationエラー() {
        //---------------------------------------------
        // 準備
        //---------------------------------------------
        AnalyzerInput input = new AnalyzerInput();

        //---------------------------------------------
        // 実行
        //---------------------------------------------
        AnalyzerOutput output = analyzer.analyze(input);

        //---------------------------------------------
        // 確認
        //---------------------------------------------
        assertEquals("処理ステータスがエラー終了であること", AnalyzeStatus.Error, output.getStatus());
//        assertEquals(4, output.getViolations().size());
//        for (ConstraintViolation<AnalyzerInput> violation : output.getViolations()) {
//            assertEquals("JSR303 NotNullメッセージIDを返すこと", "{javax.validation.constraints.NotNull.message}", violation.getMessageTemplate());
//            assertNull("JSR303 NotNullへのメッセージ引数はnullであること", violation.getExecutableParameters());
//        }
    }

    @Test
    public final void testAnalyze_OperationMatchingエラー() {
        //---------------------------------------------
        // 準備
        //---------------------------------------------
        String analyzeTarget = "";
        AnalyzeTargetType analyzeTargetType = AnalyzeTargetType.Invoke;
        AnalyzeWork analyzeWork = new AnalyzeWork();

        AnalyzerInput input = new AnalyzerInput();
//        input.setAnalyzeTarget(analyzeTarget);
        input.setAnalyzeTargetType(analyzeTargetType);
        //TODO Analyzerで、analyzeTargetとanalyzeTargetTypeから、InvokeStateを生成。
        //     InvokeStateのコンストラクタで、呼び出し文法解析＆引数などのフィールドを設定。getterではそのまま取れば良い感じ？
        //     inputで、analyzeIdも指定してあげないと、analyzeType.Statementの場合が解析できないかな。
        input.setAnalyzeWork(analyzeWork);

        //---------------------------------------------
        // 実行
        //---------------------------------------------
        AnalyzerOutput output = analyzer.analyze(input);

        //---------------------------------------------
        // 確認
        //---------------------------------------------
//        assertEquals("処理ステータスがエラー終了であること", AnalyzeStatus.Error, output.getStatus());
//        assertEquals(1, output.getViolations().size());
        for (ConstraintViolation<AnalyzerInput> violation : output.getViolations()) {
            System.out.println(violation.getMessage());
        }
    }

}
