package me.suwash.rag.sv.domain.recognize;

import me.suwash.rag.recognize.java.JavaLexer;
import me.suwash.rag.recognize.java.JavaParser;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JavaListenerTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    private static final String trgValue =
            "public class Sample {" +
            "    private final String CONST_XXX = \"XXX\";" +
            "" +
            "    public static void main(String[] args) {" +
            "        int i = 0;" +
            "        SampleWalker walker = new SampleWalker();" +
            "        System.out.println(\"this is sample.\" + i);" +
            "        walker.walk();" +
            "    }" +
            "}";

    private static final String trgFilePath = "/Users/suwa_sh/Documents/sts-bundle/workspace/ddd/src/main/java/me/suwash/ddd/classification/ProcessStatus.java";

    @Test
    public final void test() {
        // 文字列を解析
        JavaParser parser = new JavaParser(new CommonTokenStream(new JavaLexer(new ANTLRInputStream(trgValue))));
        ParserRuleContext root = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        SampleJavaListener listener = new SampleJavaListener();
        walker.walk(listener, root);

        System.out.println(listener.getVarMap().toString());
    }

    @Test
    public final void test2() throws Exception {
        // 引数のパスを解析
        JavaParser parser = new JavaParser(new CommonTokenStream(new JavaLexer(new ANTLRFileStream(trgFilePath))));
        ParserRuleContext root = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        SampleJavaListener listener = new SampleJavaListener();
        walker.walk(listener, root);

        System.out.println(listener.getVarMap().toString());
    }

}
