package me.suwash.rag.sv.domain.analyze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import me.suwash.util.Stack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO クラスの説明。
 */
public class AnalyzeWorkTest {

    private AnalyzeWork target = new AnalyzeWork();

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
    public final void testGetCurDirStack() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        Stack<String> actual = target.getCurDirStack();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertNotNull("Stackオブジェクトが取得できること", actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testPushCurDir() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        String dir1 = "/path/to/dir1";
        String dir2 = "/path/to/dir2";

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        target.pushCurDir(dir1);
        target.pushCurDir(dir2);

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        Stack<String> curDirStack = target.getCurDirStack();
        String actual = curDirStack.pop();
        assertEquals("Stackにpushされていること", dir2, actual);

        actual = curDirStack.pop();
        assertEquals("Stackにpushされていること", dir1, actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testPopCurDir() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        String dir1 = "/path/to/dir1";
        String dir2 = "/path/to/dir2";

        target.pushCurDir(dir1);
        target.pushCurDir(dir2);

        // --------------------------------------------------
        //  実行＆検証
        // --------------------------------------------------
        String actual = target.popCurDir();
        assertEquals("最後に追加した要素が取得できること", dir2, actual);

        actual = target.popCurDir();
        assertEquals("最後に追加した要素が削除され、その前に追加した要素が取得できること", dir1, actual);

        actual = target.popCurDir();
        assertNull("要素がない場合、nullが取得できること", actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetCurDir() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        String dir1 = "/path/to/dir1";
        String dir2 = "/path/to/dir2";

        target.pushCurDir(dir1);
        target.pushCurDir(dir2);

        // --------------------------------------------------
        //  実行＆検証
        // --------------------------------------------------
        String actual = target.getCurDir();
        assertEquals("最後に追加した要素が取得できること", dir2, actual);

        actual = target.getCurDir();
        assertEquals("複数回実行しても、最後に追加した要素が取得できること", dir2, actual);
        assertEquals("要素の数に変化がないこと", 2, target.getCurDirStack().elementList().size());
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetScopeStack() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        Stack<Scope> actual = target.getScopeStack();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertNotNull("Stackオブジェクトが取得できること", actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testPushScope() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        target.pushScope();
        target.pushScope();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        Stack<Scope> scopeStack = target.getScopeStack();
        assertEquals("追加した数のScopeが登録されていること", 2, scopeStack.elementList().size());
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testPopScope() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        target.pushScope();
        target.pushScope();

        // --------------------------------------------------
        //  実行＆検証
        // --------------------------------------------------
        Scope actual = target.popScope();
        assertNotNull("追加した数のScopeが登録されていること", actual);

        actual = target.popScope();
        assertNotNull("追加した数のScopeが登録されていること", actual);

        actual = target.popScope();
        assertNull("Scopeが登録されていない場合、nullが返されること", actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetCurScope() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
//        String scope1VarName = "scope1Var";
//        String scope1VarType = "Decimal";
//        String scope1VarValue = "1";
//
//        String scope2VarName = "scope2Var";
//        String scope2VarType = "String";
//        String scope2VarValue = "scope2VarValue";

        target.pushScope();
//        target.addVariable(scope1VarName, scope1VarType, scope1VarValue);

        target.pushScope();
//        target.addVariable(scope2VarName, scope2VarType, scope2VarValue);

        // --------------------------------------------------
        //  実行＆検証
        // --------------------------------------------------
        Scope actual = target.getCurScope();
        assertNotNull("追加した数のScopeが登録されていること", actual);
//        assertEquals("最後に追加したScopeが返されること", scope2VarValue, actual.getVariable(scope2VarName).getValue());

        actual = target.getCurScope();
//        assertEquals("最後に追加したScopeが返されること", scope2VarValue, actual.getVariable(scope2VarName).getValue());

        actual = target.getCurScope();
//        assertEquals("最後に追加したScopeが返されること", scope2VarValue, actual.getVariable(scope2VarName).getValue());
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetVariableString() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
//        String scope1VarName = "scope1Var";
//        String scope1VarType = "Decimal";
//        String scope1VarValue = "1";
//        String scope1VarValueOverrited = "100";
//
//        String scope2VarType = "String";
//        String scope2VarValue = "Hide!";

        target.pushScope();
//        target.addVariable(scope1VarName, scope1VarType, scope1VarValue);
        // 同一スコープ内の変数を上書き（分析時の代入時の処理）
//        target.addVariable(scope1VarName, scope1VarType, scope1VarValueOverrited);

        target.pushScope();
//        target.addVariable(scope1VarName, scope2VarType, scope2VarValue);

        // --------------------------------------------------
        //  実行＆検証
        // --------------------------------------------------
//        Variable actual = target.getVariable(scope1VarName);
//        assertEquals("より狭いスコープの値でHidingされている場合、狭いスコープの値が返されること", scope2VarValue, actual.getValue());


        target.popScope();
//        actual = target.getVariable(scope1VarName);
//        assertEquals("より狭いスコープの値でHidingされていない場合、元のスコープの値が返されること", scope1VarValueOverrited, actual.getValue());
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetVariableStringString() {
        // TODO AnalyzeID指定で変数を返却するAPIのテストを実装
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetInvokerStack() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        Stack<Invoker> actual = target.getInvokerStack();

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertNotNull("Stackオブジェクトが取得できること", actual);
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testPushInvoker() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        Invoker invoker1 = new Invoker();
        invoker1.setInvokerPath("/path/to/invoker1");
        invoker1.setInvokeSignature("me.suwash.rag.test.SampleClass1#sampleMethod1(java.lang.String, java.lang.Integer)");
        invoker1.setArgList(Arrays.asList("StringValue", "DecimalValue"));

        Invoker invoker2 = new Invoker();
        invoker2.setInvokerPath("/path/to/invoker2");
        invoker2.setInvokeSignature("me.suwash.rag.test.SampleClass2#sampleMethod1(java.lang.String)");
        invoker2.setArgList(Arrays.asList("StringValue"));

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        target.pushInvoker(invoker1);
        target.pushInvoker(invoker2);

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        Stack<Invoker> actual = target.getInvokerStack();
        assertEquals(invoker2, actual.pop());
        assertEquals(invoker1, actual.pop());
        assertNull(actual.pop());
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testPopInvoker() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        Invoker invoker1 = new Invoker();
        invoker1.setInvokerPath("/path/to/invoker1");
        invoker1.setInvokeSignature("me.suwash.rag.test.SampleClass1#sampleMethod1(java.lang.String, java.lang.Integer)");
        invoker1.setArgList(Arrays.asList("StringValue", "DecimalValue"));

        Invoker invoker2 = new Invoker();
        invoker2.setInvokerPath("/path/to/invoker2");
        invoker2.setInvokeSignature("me.suwash.rag.test.SampleClass2#sampleMethod1(java.lang.String)");
        invoker2.setArgList(Arrays.asList("StringValue"));

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        target.pushInvoker(invoker1);
        target.pushInvoker(invoker2);

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals(invoker2, target.popInvoker());
        assertEquals(invoker1, target.popInvoker());
        assertNull(target.popInvoker());
    }

    /**
     * TODO メソッドのコメント。
     */
    @Test
    public final void testGetCurInvoker() {
        // --------------------------------------------------
        //  準備
        // --------------------------------------------------
        Invoker invoker1 = new Invoker();
        invoker1.setInvokerPath("/path/to/invoker1");
        invoker1.setInvokeSignature("me.suwash.rag.test.SampleClass1#sampleMethod1(java.lang.String, java.lang.Integer)");
        invoker1.setArgList(Arrays.asList("StringValue", "DecimalValue"));

        Invoker invoker2 = new Invoker();
        invoker2.setInvokerPath("/path/to/invoker2");
        invoker2.setInvokeSignature("me.suwash.rag.test.SampleClass2#sampleMethod1(java.lang.String)");
        invoker2.setArgList(Arrays.asList("StringValue"));

        // --------------------------------------------------
        //  実行
        // --------------------------------------------------
        target.pushInvoker(invoker1);
        target.pushInvoker(invoker2);

        // --------------------------------------------------
        //  検証
        // --------------------------------------------------
        assertEquals(invoker2, target.getCurInvoker());
        assertEquals(invoker2, target.getCurInvoker());
    }

}
