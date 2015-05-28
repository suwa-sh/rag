package me.suwash.rag.recognize.gen.java;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class TestJava2 {
    public static void main(String[] args) throws Exception {
        // 引数のパスを解析
        JavaParser parser = new JavaParser(new CommonTokenStream(new JavaLexer(new ANTLRFileStream(args[0]))));

        ParserRuleContext t = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new JavaBaseListener(), t);

        t.inspect(parser);
    }
}
