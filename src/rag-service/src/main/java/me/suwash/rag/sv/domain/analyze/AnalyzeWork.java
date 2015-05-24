package me.suwash.rag.sv.domain.analyze;

import java.util.HashMap;
import java.util.Map;

import me.suwash.rag.classification.VariableType;
import me.suwash.util.Stack;

/**
 * 分析作業オブジェクト。
 */
public class AnalyzeWork {

    /** カレントディレクトリStack。 */
    private Stack<String> curDirStack = new Stack<String>();
    /** 呼び出し元Stack。 */
    private Stack<Invoker> invokerStack = new Stack<Invoker>();
    /** スコープStack。 */
    private Stack<Scope> scopeStack = new Stack<Scope>();
    /** stasicフィールドMap。 */
    private Map<String, Variable> staticFieldMap = new HashMap<String, Variable>();

    /**
     * カレントディレクトリStackを返します。
     * @return カレントディレクトリStack
     */
    public Stack<String> getCurDirStack() {
        return curDirStack;
    }

    /**
     * 指定したディレクトリパスをカレントディレクトリStackに追加します。
     * @param dirPath ディレクトリパス
     */
    public void pushCurDir(String dirPath) {
        curDirStack.push(dirPath);
    }

    /**
     * カレントディレクトリStackから最後に追加した要素を返し、Stackから削除します。
     * @return 最後に追加した要素
     */
    public String popCurDir() {
        return curDirStack.pop();
    }

    /**
     * カレントディレクトリStackから最後に追加した要素を返します。
     * @return 最後に追加した要素
     */
    public String getCurDir() {
        return curDirStack.peek();
    }

    /**
     * スコープStackを取得します。
     * @return スコープStack
     */
    public Stack<Scope> getScopeStack() {
        return scopeStack;
    }

    /**
     * スコープStackにスコープを追加します。
     */
    public void pushScope() {
        scopeStack.push(new Scope());
    }

    /**
     * スコープStackから最後に追加した要素を返し、Stackから削除します。
     *
     * @return Scope
     */
    public Scope popScope() {
        return scopeStack.pop();
    }

    /**
     * スコープStackから最後に追加した要素を返します。
     *
     * @return Scope
     */
    public Scope getCurScope() {
        return scopeStack.peek();
    }

    /**
     * 現在のスコープからマッチする変数オブジェクトを返します。
     *
     * @param analyzeId 分析ID
     * @param name 変数名
     * @return 変数オブジェクト
     */
    public Variable getVariable(String analyzeId, String name) {
        for (Scope scope : scopeStack.elementList()) {
            if (scope != null) {
                return scope.getVariable(analyzeId, name);
            }
        }
        return null;
    }

    /**
     * 現在のスコープに変数オブジェクトを登録します。
     *
     * @param analyzeId 分析ID
     * @param name 変数名
     * @param type 変数の型
     * @param value 変数の設定値
     */
    public void addVariable(String analyzeId, String name, String type, String value) {
        Scope curScope = scopeStack.peek();

        Variable variable = new Variable(type, name, VariableType.Primitive);
        variable.setValue(value);

        curScope.addVariable(analyzeId, variable);
    }

    // TODO walkで、コンストラクタ呼び出ししたら addReferenceVariable
    //      → 呼び出されたコンストラクタ側のクラスで、static の評価済みチェック（AnaylzeWorkのstatic存在チェック）、評価されてなければ評価＆staticに登録
    //      → 呼び出されたコンストラクタ側のクラスで、インスタンスフィールドに設定していたら評価
    //        → walkで、refVar.setXxx("設定値") していたら、refVar#setXxx(String)をwalk、フィールドに設定していたらrefVarを取得して、フィールド更新
    //            ※同名の変数が定義されていても、もっとも近いスコープの設定値が更新されればOK
    //            ※walk中に、自分の変数名が必要。再帰呼び出しの引数に追加が必要か？
    // TODO walkで、プリミティブ型変数に代入していたら、addVariable

    /**
     * TODO メソッドのコメント。
     *
     * @param type xxx
     * @param name xxx
     * @return Variable
     */
    public Variable getStaticField(String type, String name) {
        // TODO 未実装
        return null;
    }

    /**
     * staticフィールドに変数オブジェクトを登録します。
     *
     * @param ownerType staticフィールドを保有する型
     * @param name staticフィールド名
     * @param type staticフィールドの型
     * @param value statiフィールドの設定値
     */
    public void addStaticField(String ownerType, String name, String type, String value) {
        if (staticFieldMap.containsKey(ownerType)) {
            // TODO
        }
    }


    /**
     * 呼び出し元Stackを返します。
     * @return 呼び出し元Stack
     */
    public Stack<Invoker> getInvokerStack() {
        return invokerStack;
    }

    /**
     * 呼び出し元Stackに呼び出し元を追加します。
     * @param invoker 呼び出し元
     */
    public void pushInvoker(Invoker invoker) {
        invokerStack.push(invoker);
    }

    /**
     * 呼び出し元Stackから最後に追加した要素を返し、Stackから削除します。
     *
     * @return Invoker
     */
    public Invoker popInvoker() {
        return invokerStack.pop();
    }

    /**
     * 呼び出し元Stackから最後に追加した要素を返します。
     *
     * @return Invoker
     */
    public Invoker getCurInvoker() {
        return invokerStack.peek();
    }
}
