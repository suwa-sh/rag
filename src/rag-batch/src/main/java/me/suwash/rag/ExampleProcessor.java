package me.suwash.rag;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
//import me.suwash.rag.rag.recognize.java.JavaExampleListener;
//import me.suwash.rag.rag.recognize.java.JavaLexer;
//import me.suwash.rag.rag.recognize.java.JavaParser;

@Component("processor")
@Scope("step")
public class ExampleProcessor implements ItemProcessor<ExampleInDto, ExampleOutDto> {

    private static final String trgValue =
            "public class Sample {" +
            "    private final String CONST_XXX = \"XXX\";" +
            "" +
            "    public static void main(String[] args) {" +
            "        String path = \"${item.value1}\";" +
            "        SampleWalker walker = new SampleWalker();" +
            "        System.out.println(\"this is sample.\" + i);" +
            "        walker.walk();" +
            "    }" +
            "}";

    public ExampleOutDto process(ExampleInDto item) {
//        // 文字列を解析
//        String replacedValue = trgValue.replaceAll("\\Q${item.value1}\\E", item.getValue1());
//        JavaParser parser = new JavaParser(new CommonTokenStream(new JavaLexer(new ANTLRInputStream(replacedValue))));
//        ParserRuleContext t = parser.compilationUnit();
//
//        ParseTreeWalker walker = new ParseTreeWalker();
//        JavaExampleListener listener = new JavaExampleListener();
//        listener.setVarMap(new HashMap<String, Map<String, String>>());
//        walker.walk(listener, t);
//
        ExampleOutDto out = new ExampleOutDto();
//        out.setBoundary(item.getValue1());
//        out.setController1(item.getValue1() + "-ctrl");
//        out.setDataStore(listener.getVarMap().toString());
        return out;
    }

}
