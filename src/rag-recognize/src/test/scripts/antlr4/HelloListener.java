// Generated from Hello.g4 by ANTLR 4.2.2
package jp.co.presa.rag.recognize;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public class HelloListener extends ParseTreeListener {
        /**
         * Enter a parse tree produced by {@link HelloParser#r}.
         * @param ctx the parse tree
         */
        void enterR(@NotNull HelloParser.RContext ctx) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

            for (String value : list) {
                if (value.equals("case1")) {
                        System.out.println("1");
                } else if (value.equals("case2")) {
                        System.out.println("2");
                } else {
                        System.out.println("else");
                }
            }

            return;
        }
        /**
         * Exit a parse tree produced by {@link HelloParser#r}.
         * @param ctx the parse tree
         */
        void exitR(@NotNull HelloParser.RContext ctx) {
            return;
        }
}