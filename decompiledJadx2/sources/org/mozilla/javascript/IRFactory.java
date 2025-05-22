package org.mozilla.javascript;

import com.pudutech.remotemaintenance.config.IoTConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.ArrayComprehension;
import org.mozilla.javascript.ast.ArrayComprehensionLoop;
import org.mozilla.javascript.ast.ArrayLiteral;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.BreakStatement;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.ConditionalExpression;
import org.mozilla.javascript.ast.ContinueStatement;
import org.mozilla.javascript.ast.DestructuringForm;
import org.mozilla.javascript.ast.DoLoop;
import org.mozilla.javascript.ast.ElementGet;
import org.mozilla.javascript.ast.EmptyExpression;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.ForLoop;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.GeneratorExpression;
import org.mozilla.javascript.ast.GeneratorExpressionLoop;
import org.mozilla.javascript.ast.IfStatement;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Label;
import org.mozilla.javascript.ast.LabeledStatement;
import org.mozilla.javascript.ast.LetNode;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NewExpression;
import org.mozilla.javascript.ast.NumberLiteral;
import org.mozilla.javascript.ast.ObjectLiteral;
import org.mozilla.javascript.ast.ObjectProperty;
import org.mozilla.javascript.ast.ParenthesizedExpression;
import org.mozilla.javascript.ast.PropertyGet;
import org.mozilla.javascript.ast.RegExpLiteral;
import org.mozilla.javascript.ast.ReturnStatement;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ast.StringLiteral;
import org.mozilla.javascript.ast.SwitchCase;
import org.mozilla.javascript.ast.SwitchStatement;
import org.mozilla.javascript.ast.ThrowStatement;
import org.mozilla.javascript.ast.TryStatement;
import org.mozilla.javascript.ast.UnaryExpression;
import org.mozilla.javascript.ast.VariableDeclaration;
import org.mozilla.javascript.ast.VariableInitializer;
import org.mozilla.javascript.ast.WhileLoop;
import org.mozilla.javascript.ast.WithStatement;
import org.mozilla.javascript.ast.XmlDotQuery;
import org.mozilla.javascript.ast.XmlElemRef;
import org.mozilla.javascript.ast.XmlExpression;
import org.mozilla.javascript.ast.XmlFragment;
import org.mozilla.javascript.ast.XmlLiteral;
import org.mozilla.javascript.ast.XmlMemberGet;
import org.mozilla.javascript.ast.XmlPropRef;
import org.mozilla.javascript.ast.XmlRef;
import org.mozilla.javascript.ast.XmlString;
import org.mozilla.javascript.ast.Yield;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class IRFactory extends Parser {
    private static final int ALWAYS_FALSE_BOOLEAN = -1;
    private static final int ALWAYS_TRUE_BOOLEAN = 1;
    private static final int LOOP_DO_WHILE = 0;
    private static final int LOOP_FOR = 2;
    private static final int LOOP_WHILE = 1;
    private Decompiler decompiler;

    public IRFactory() {
        this.decompiler = new Decompiler();
    }

    public IRFactory(CompilerEnvirons compilerEnvirons) {
        this(compilerEnvirons, compilerEnvirons.getErrorReporter());
    }

    public IRFactory(CompilerEnvirons compilerEnvirons, ErrorReporter errorReporter) {
        super(compilerEnvirons, errorReporter);
        this.decompiler = new Decompiler();
    }

    public ScriptNode transformTree(AstRoot astRoot) {
        this.currentScriptOrFn = astRoot;
        this.inUseStrictDirective = astRoot.isInStrictMode();
        int currentOffset = this.decompiler.getCurrentOffset();
        ScriptNode scriptNode = (ScriptNode) transform(astRoot);
        scriptNode.setEncodedSourceBounds(currentOffset, this.decompiler.getCurrentOffset());
        if (this.compilerEnv.isGeneratingSource()) {
            scriptNode.setEncodedSource(this.decompiler.getEncodedSource());
        }
        this.decompiler = null;
        return scriptNode;
    }

    public Node transform(AstNode astNode) {
        int type = astNode.getType();
        if (type == 66) {
            return transformArrayLiteral((ArrayLiteral) astNode);
        }
        if (type == 67) {
            return transformObjectLiteral((ObjectLiteral) astNode);
        }
        if (type == 129) {
            return astNode;
        }
        if (type == 130) {
            return transformBlock(astNode);
        }
        switch (type) {
            case 4:
                return transformReturn((ReturnStatement) astNode);
            case 30:
                return transformNewExpr((NewExpression) astNode);
            case 33:
                return transformPropertyGet((PropertyGet) astNode);
            case 36:
                return transformElementGet((ElementGet) astNode);
            case 48:
                return transformRegExp((RegExpLiteral) astNode);
            case 50:
                return transformThrow((ThrowStatement) astNode);
            case 73:
                return transformYield((Yield) astNode);
            case 82:
                return transformTry((TryStatement) astNode);
            case 103:
                return transformCondExpr((ConditionalExpression) astNode);
            case 110:
                return transformFunction((FunctionNode) astNode);
            case 113:
                return transformIf((IfStatement) astNode);
            case 115:
                return transformSwitch((SwitchStatement) astNode);
            case 124:
                return transformWith((WithStatement) astNode);
            case 137:
                return transformScript((ScriptNode) astNode);
            case 158:
                return transformArrayComp((ArrayComprehension) astNode);
            case 161:
                break;
            case 163:
                return transformGenExpr((GeneratorExpression) astNode);
            default:
                switch (type) {
                    case 38:
                        return transformFunctionCall((FunctionCall) astNode);
                    case 39:
                        return transformName((Name) astNode);
                    case 40:
                        return transformNumber((NumberLiteral) astNode);
                    case 41:
                        return transformString((StringLiteral) astNode);
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                        break;
                    default:
                        switch (type) {
                            case 118:
                                return transformWhileLoop((WhileLoop) astNode);
                            case 119:
                                return transformDoLoop((DoLoop) astNode);
                            case 120:
                                if (astNode instanceof ForInLoop) {
                                    return transformForInLoop((ForInLoop) astNode);
                                }
                                return transformForLoop((ForLoop) astNode);
                            case 121:
                                return transformBreak((BreakStatement) astNode);
                            case 122:
                                return transformContinue((ContinueStatement) astNode);
                            default:
                                if (astNode instanceof ExpressionStatement) {
                                    return transformExprStmt((ExpressionStatement) astNode);
                                }
                                if (astNode instanceof Assignment) {
                                    return transformAssignment((Assignment) astNode);
                                }
                                if (astNode instanceof UnaryExpression) {
                                    return transformUnary((UnaryExpression) astNode);
                                }
                                if (astNode instanceof XmlMemberGet) {
                                    return transformXmlMemberGet((XmlMemberGet) astNode);
                                }
                                if (astNode instanceof InfixExpression) {
                                    return transformInfix((InfixExpression) astNode);
                                }
                                if (astNode instanceof VariableDeclaration) {
                                    return transformVariables((VariableDeclaration) astNode);
                                }
                                if (astNode instanceof ParenthesizedExpression) {
                                    return transformParenExpr((ParenthesizedExpression) astNode);
                                }
                                if (astNode instanceof LabeledStatement) {
                                    return transformLabeledStatement((LabeledStatement) astNode);
                                }
                                if (astNode instanceof LetNode) {
                                    return transformLetNode((LetNode) astNode);
                                }
                                if (astNode instanceof XmlRef) {
                                    return transformXmlRef((XmlRef) astNode);
                                }
                                if (astNode instanceof XmlLiteral) {
                                    return transformXmlLiteral((XmlLiteral) astNode);
                                }
                                throw new IllegalArgumentException("Can't transform: " + astNode);
                        }
                }
        }
        return transformLiteral(astNode);
    }

    private Node transformArrayComp(ArrayComprehension arrayComprehension) {
        int lineno = arrayComprehension.getLineno();
        Scope createScopeNode = createScopeNode(158, lineno);
        String nextTempName = this.currentScriptOrFn.getNextTempName();
        pushScope(createScopeNode);
        try {
            defineSymbol(154, nextTempName, false);
            Node node = new Node(130, lineno);
            node.addChildToBack(new Node(134, createAssignment(91, createName(nextTempName), createCallOrNew(30, createName("Array"))), lineno));
            node.addChildToBack(arrayCompTransformHelper(arrayComprehension, nextTempName));
            createScopeNode.addChildToBack(node);
            createScopeNode.addChildToBack(createName(nextTempName));
            return createScopeNode;
        } finally {
            popScope();
        }
    }

    private Node arrayCompTransformHelper(ArrayComprehension arrayComprehension, String str) {
        ArrayComprehensionLoop arrayComprehensionLoop;
        Scope createLoopNode;
        int i;
        String str2;
        this.decompiler.addToken(84);
        int lineno = arrayComprehension.getLineno();
        Node transform = transform(arrayComprehension.getResult());
        List<ArrayComprehensionLoop> loops = arrayComprehension.getLoops();
        int size = loops.size();
        Node[] nodeArr = new Node[size];
        Node[] nodeArr2 = new Node[size];
        Node node = transform;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayComprehensionLoop arrayComprehensionLoop2 = loops.get(i2);
            this.decompiler.addName(" ");
            this.decompiler.addToken(120);
            if (arrayComprehensionLoop2.isForEach()) {
                this.decompiler.addName("each ");
            }
            this.decompiler.addToken(88);
            AstNode iterator = arrayComprehensionLoop2.getIterator();
            if (iterator.getType() == 39) {
                str2 = iterator.getString();
                this.decompiler.addName(str2);
            } else {
                decompile(iterator);
                String nextTempName = this.currentScriptOrFn.getNextTempName();
                defineSymbol(88, nextTempName, false);
                node = createBinary(90, createAssignment(91, iterator, createName(nextTempName)), node);
                str2 = nextTempName;
            }
            Node createName = createName(str2);
            defineSymbol(154, str2, false);
            nodeArr[i2] = createName;
            if (arrayComprehensionLoop2.isForOf()) {
                this.decompiler.addName("of ");
            } else {
                this.decompiler.addToken(52);
            }
            nodeArr2[i2] = transform(arrayComprehensionLoop2.getIteratedObject());
            this.decompiler.addToken(89);
        }
        Node createCallOrNew = createCallOrNew(38, createPropertyGet(createName(str), null, IoTConfig.PARAM_PUSH_FILE, 0));
        Node node2 = new Node(134, createCallOrNew, lineno);
        if (arrayComprehension.getFilter() != null) {
            this.decompiler.addName(" ");
            this.decompiler.addToken(113);
            this.decompiler.addToken(88);
            node2 = createIf(transform(arrayComprehension.getFilter()), node2, null, lineno);
            this.decompiler.addToken(89);
        }
        Node node3 = node2;
        int i3 = size - 1;
        int i4 = 0;
        while (i3 >= 0) {
            try {
                arrayComprehensionLoop = loops.get(i3);
                createLoopNode = createLoopNode(null, arrayComprehensionLoop.getLineno());
                pushScope(createLoopNode);
                i = i4 + 1;
            } catch (Throwable th) {
                th = th;
            }
            try {
                Node node4 = createCallOrNew;
                node3 = createForIn(154, createLoopNode, nodeArr[i3], nodeArr2[i3], node3, arrayComprehensionLoop.isForEach(), arrayComprehensionLoop.isForOf());
                i3--;
                createCallOrNew = node4;
                i4 = i;
            } catch (Throwable th2) {
                th = th2;
                i4 = i;
                for (int i5 = 0; i5 < i4; i5++) {
                    popScope();
                }
                throw th;
            }
        }
        Node node5 = createCallOrNew;
        for (int i6 = 0; i6 < i4; i6++) {
            popScope();
        }
        this.decompiler.addToken(85);
        node5.addChildToBack(node);
        return node3;
    }

    private Node transformArrayLiteral(ArrayLiteral arrayLiteral) {
        if (arrayLiteral.isDestructuring()) {
            return arrayLiteral;
        }
        this.decompiler.addToken(84);
        List<AstNode> elements = arrayLiteral.getElements();
        Node node = new Node(66);
        ArrayList arrayList = null;
        for (int i = 0; i < elements.size(); i++) {
            AstNode astNode = elements.get(i);
            if (astNode.getType() != 129) {
                node.addChildToBack(transform(astNode));
            } else {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(Integer.valueOf(i));
            }
            if (i < elements.size() - 1) {
                this.decompiler.addToken(90);
            }
        }
        this.decompiler.addToken(85);
        node.putIntProp(21, arrayLiteral.getDestructuringLength());
        if (arrayList != null) {
            int[] iArr = new int[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
            }
            node.putProp(11, iArr);
        }
        return node;
    }

    private Node transformAssignment(Assignment assignment) {
        AstNode astNode;
        AstNode removeParens = removeParens(assignment.getLeft());
        if (isDestructuring(removeParens)) {
            decompile(removeParens);
            astNode = removeParens;
        } else {
            astNode = transform(removeParens);
        }
        this.decompiler.addToken(assignment.getType());
        return createAssignment(assignment.getType(), astNode, transform(assignment.getRight()));
    }

    private Node transformBlock(AstNode astNode) {
        boolean z = astNode instanceof Scope;
        if (z) {
            pushScope((Scope) astNode);
        }
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<Node> it = astNode.iterator();
            while (it.hasNext()) {
                arrayList.add(transform((AstNode) it.next()));
            }
            astNode.removeChildren();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                astNode.addChildToBack((Node) it2.next());
            }
            return astNode;
        } finally {
            if (z) {
                popScope();
            }
        }
    }

    private Node transformBreak(BreakStatement breakStatement) {
        this.decompiler.addToken(121);
        if (breakStatement.getBreakLabel() != null) {
            this.decompiler.addName(breakStatement.getBreakLabel().getIdentifier());
        }
        this.decompiler.addEOL(83);
        return breakStatement;
    }

    private Node transformCondExpr(ConditionalExpression conditionalExpression) {
        Node transform = transform(conditionalExpression.getTestExpression());
        this.decompiler.addToken(103);
        Node transform2 = transform(conditionalExpression.getTrueExpression());
        this.decompiler.addToken(104);
        return createCondExpr(transform, transform2, transform(conditionalExpression.getFalseExpression()));
    }

    private Node transformContinue(ContinueStatement continueStatement) {
        this.decompiler.addToken(122);
        if (continueStatement.getLabel() != null) {
            this.decompiler.addName(continueStatement.getLabel().getIdentifier());
        }
        this.decompiler.addEOL(83);
        return continueStatement;
    }

    private Node transformDoLoop(DoLoop doLoop) {
        doLoop.setType(133);
        pushScope(doLoop);
        try {
            this.decompiler.addToken(119);
            this.decompiler.addEOL(86);
            Node transform = transform(doLoop.getBody());
            this.decompiler.addToken(87);
            this.decompiler.addToken(118);
            this.decompiler.addToken(88);
            Node transform2 = transform(doLoop.getCondition());
            this.decompiler.addToken(89);
            this.decompiler.addEOL(83);
            return createLoop(doLoop, 0, transform, transform2, null, null);
        } finally {
            popScope();
        }
    }

    private Node transformElementGet(ElementGet elementGet) {
        Node transform = transform(elementGet.getTarget());
        this.decompiler.addToken(84);
        Node transform2 = transform(elementGet.getElement());
        this.decompiler.addToken(85);
        return new Node(36, transform, transform2);
    }

    private Node transformExprStmt(ExpressionStatement expressionStatement) {
        Node transform = transform(expressionStatement.getExpression());
        this.decompiler.addEOL(83);
        return new Node(expressionStatement.getType(), transform, expressionStatement.getLineno());
    }

    private Node transformForInLoop(ForInLoop forInLoop) {
        this.decompiler.addToken(120);
        if (forInLoop.isForEach()) {
            this.decompiler.addName("each ");
        }
        this.decompiler.addToken(88);
        forInLoop.setType(133);
        pushScope(forInLoop);
        try {
            AstNode iterator = forInLoop.getIterator();
            int type = iterator instanceof VariableDeclaration ? ((VariableDeclaration) iterator).getType() : -1;
            Node transform = transform(iterator);
            if (forInLoop.isForOf()) {
                this.decompiler.addName("of ");
            } else {
                this.decompiler.addToken(52);
            }
            Node transform2 = transform(forInLoop.getIteratedObject());
            this.decompiler.addToken(89);
            this.decompiler.addEOL(86);
            Node transform3 = transform(forInLoop.getBody());
            this.decompiler.addEOL(87);
            return createForIn(type, forInLoop, transform, transform2, transform3, forInLoop.isForEach(), forInLoop.isForOf());
        } finally {
            popScope();
        }
    }

    private Node transformForLoop(ForLoop forLoop) {
        this.decompiler.addToken(120);
        this.decompiler.addToken(88);
        forLoop.setType(133);
        Scope scope = this.currentScope;
        this.currentScope = forLoop;
        try {
            Node transform = transform(forLoop.getInitializer());
            this.decompiler.addToken(83);
            Node transform2 = transform(forLoop.getCondition());
            this.decompiler.addToken(83);
            Node transform3 = transform(forLoop.getIncrement());
            this.decompiler.addToken(89);
            this.decompiler.addEOL(86);
            Node transform4 = transform(forLoop.getBody());
            this.decompiler.addEOL(87);
            return createFor(forLoop, transform, transform2, transform3, transform4);
        } finally {
            this.currentScope = scope;
        }
    }

    private Node transformFunction(FunctionNode functionNode) {
        int functionType = functionNode.getFunctionType();
        int markFunctionStart = this.decompiler.markFunctionStart(functionType);
        Node decompileFunctionHeader = decompileFunctionHeader(functionNode);
        int addFunction = this.currentScriptOrFn.addFunction(functionNode);
        Parser.PerFunctionVariables perFunctionVariables = new Parser.PerFunctionVariables(functionNode);
        try {
            Node node = (Node) functionNode.getProp(23);
            functionNode.removeProp(23);
            int lineno = functionNode.getBody().getLineno();
            this.nestingOfFunction++;
            Node transform = transform(functionNode.getBody());
            if (!functionNode.isExpressionClosure()) {
                this.decompiler.addToken(87);
            }
            functionNode.setEncodedSourceBounds(markFunctionStart, this.decompiler.markFunctionEnd(markFunctionStart));
            if (functionType != 2 && !functionNode.isExpressionClosure()) {
                this.decompiler.addToken(1);
            }
            if (node != null) {
                transform.addChildToFront(new Node(134, node, lineno));
            }
            int functionType2 = functionNode.getFunctionType();
            Node initFunction = initFunction(functionNode, addFunction, transform, functionType2);
            if (decompileFunctionHeader != null) {
                initFunction = createAssignment(91, decompileFunctionHeader, initFunction);
                if (functionType2 != 2) {
                    initFunction = createExprStatementNoReturn(initFunction, functionNode.getLineno());
                }
            }
            return initFunction;
        } finally {
            this.nestingOfFunction--;
            perFunctionVariables.restore();
        }
    }

    private Node transformFunctionCall(FunctionCall functionCall) {
        Node createCallOrNew = createCallOrNew(38, transform(functionCall.getTarget()));
        createCallOrNew.setLineno(functionCall.getLineno());
        this.decompiler.addToken(88);
        List<AstNode> arguments = functionCall.getArguments();
        for (int i = 0; i < arguments.size(); i++) {
            createCallOrNew.addChildToBack(transform(arguments.get(i)));
            if (i < arguments.size() - 1) {
                this.decompiler.addToken(90);
            }
        }
        this.decompiler.addToken(89);
        return createCallOrNew;
    }

    private Node transformGenExpr(GeneratorExpression generatorExpression) {
        FunctionNode functionNode = new FunctionNode();
        functionNode.setSourceName(this.currentScriptOrFn.getNextTempName());
        functionNode.setIsGenerator();
        functionNode.setFunctionType(2);
        functionNode.setRequiresActivation();
        int functionType = functionNode.getFunctionType();
        int markFunctionStart = this.decompiler.markFunctionStart(functionType);
        Node decompileFunctionHeader = decompileFunctionHeader(functionNode);
        int addFunction = this.currentScriptOrFn.addFunction(functionNode);
        Parser.PerFunctionVariables perFunctionVariables = new Parser.PerFunctionVariables(functionNode);
        try {
            Node node = (Node) functionNode.getProp(23);
            functionNode.removeProp(23);
            int i = generatorExpression.lineno;
            this.nestingOfFunction++;
            Node genExprTransformHelper = genExprTransformHelper(generatorExpression);
            if (!functionNode.isExpressionClosure()) {
                this.decompiler.addToken(87);
            }
            functionNode.setEncodedSourceBounds(markFunctionStart, this.decompiler.markFunctionEnd(markFunctionStart));
            if (functionType != 2 && !functionNode.isExpressionClosure()) {
                this.decompiler.addToken(1);
            }
            if (node != null) {
                genExprTransformHelper.addChildToFront(new Node(134, node, i));
            }
            int functionType2 = functionNode.getFunctionType();
            Node initFunction = initFunction(functionNode, addFunction, genExprTransformHelper, functionType2);
            if (decompileFunctionHeader != null) {
                initFunction = createAssignment(91, decompileFunctionHeader, initFunction);
                if (functionType2 != 2) {
                    initFunction = createExprStatementNoReturn(initFunction, functionNode.getLineno());
                }
            }
            this.nestingOfFunction--;
            perFunctionVariables.restore();
            Node createCallOrNew = createCallOrNew(38, initFunction);
            createCallOrNew.setLineno(generatorExpression.getLineno());
            this.decompiler.addToken(88);
            this.decompiler.addToken(89);
            return createCallOrNew;
        } catch (Throwable th) {
            this.nestingOfFunction--;
            perFunctionVariables.restore();
            throw th;
        }
    }

    private Node genExprTransformHelper(GeneratorExpression generatorExpression) {
        String str;
        int i = 88;
        this.decompiler.addToken(88);
        int lineno = generatorExpression.getLineno();
        Node transform = transform(generatorExpression.getResult());
        List<GeneratorExpressionLoop> loops = generatorExpression.getLoops();
        int size = loops.size();
        Node[] nodeArr = new Node[size];
        Node[] nodeArr2 = new Node[size];
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            GeneratorExpressionLoop generatorExpressionLoop = loops.get(i3);
            this.decompiler.addName(" ");
            this.decompiler.addToken(120);
            this.decompiler.addToken(i);
            AstNode iterator = generatorExpressionLoop.getIterator();
            if (iterator.getType() == 39) {
                str = iterator.getString();
                this.decompiler.addName(str);
            } else {
                decompile(iterator);
                String nextTempName = this.currentScriptOrFn.getNextTempName();
                defineSymbol(i, nextTempName, false);
                transform = createBinary(90, createAssignment(91, iterator, createName(nextTempName)), transform);
                str = nextTempName;
            }
            Node createName = createName(str);
            defineSymbol(154, str, false);
            nodeArr[i3] = createName;
            if (generatorExpressionLoop.isForOf()) {
                this.decompiler.addName("of ");
            } else {
                this.decompiler.addToken(52);
            }
            nodeArr2[i3] = transform(generatorExpressionLoop.getIteratedObject());
            this.decompiler.addToken(89);
            i3++;
            i = 88;
        }
        Node node = new Node(134, new Node(73, transform, generatorExpression.getLineno()), lineno);
        if (generatorExpression.getFilter() != null) {
            this.decompiler.addName(" ");
            this.decompiler.addToken(113);
            this.decompiler.addToken(88);
            node = createIf(transform(generatorExpression.getFilter()), node, null, lineno);
            this.decompiler.addToken(89);
        }
        Node node2 = node;
        int i4 = size - 1;
        int i5 = 0;
        while (i4 >= 0) {
            try {
                GeneratorExpressionLoop generatorExpressionLoop2 = loops.get(i4);
                Scope createLoopNode = createLoopNode(null, generatorExpressionLoop2.getLineno());
                pushScope(createLoopNode);
                int i6 = i5 + 1;
                try {
                    node2 = createForIn(154, createLoopNode, nodeArr[i4], nodeArr2[i4], node2, generatorExpressionLoop2.isForEach(), generatorExpressionLoop2.isForOf());
                    i4--;
                    i5 = i6;
                } catch (Throwable th) {
                    th = th;
                    i5 = i6;
                    while (i2 < i5) {
                        popScope();
                        i2++;
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        while (i2 < i5) {
            popScope();
            i2++;
        }
        this.decompiler.addToken(89);
        return node2;
    }

    private Node transformIf(IfStatement ifStatement) {
        Node node;
        this.decompiler.addToken(113);
        this.decompiler.addToken(88);
        Node transform = transform(ifStatement.getCondition());
        this.decompiler.addToken(89);
        this.decompiler.addEOL(86);
        Node transform2 = transform(ifStatement.getThenPart());
        if (ifStatement.getElsePart() != null) {
            this.decompiler.addToken(87);
            this.decompiler.addToken(114);
            this.decompiler.addEOL(86);
            node = transform(ifStatement.getElsePart());
        } else {
            node = null;
        }
        this.decompiler.addEOL(87);
        return createIf(transform, transform2, node, ifStatement.getLineno());
    }

    private Node transformInfix(InfixExpression infixExpression) {
        Node transform = transform(infixExpression.getLeft());
        this.decompiler.addToken(infixExpression.getType());
        Node transform2 = transform(infixExpression.getRight());
        if (infixExpression instanceof XmlDotQuery) {
            this.decompiler.addToken(89);
        }
        return createBinary(infixExpression.getType(), transform, transform2);
    }

    private Node transformLabeledStatement(LabeledStatement labeledStatement) {
        Label firstLabel = labeledStatement.getFirstLabel();
        List<Label> labels = labeledStatement.getLabels();
        this.decompiler.addName(firstLabel.getName());
        if (labels.size() > 1) {
            for (Label label : labels.subList(1, labels.size())) {
                this.decompiler.addEOL(104);
                this.decompiler.addName(label.getName());
            }
        }
        if (labeledStatement.getStatement().getType() == 130) {
            this.decompiler.addToken(67);
            this.decompiler.addEOL(86);
        } else {
            this.decompiler.addEOL(104);
        }
        Node transform = transform(labeledStatement.getStatement());
        if (labeledStatement.getStatement().getType() == 130) {
            this.decompiler.addEOL(87);
        }
        Node newTarget = Node.newTarget();
        Node node = new Node(130, firstLabel, transform, newTarget);
        firstLabel.target = newTarget;
        return node;
    }

    private Node transformLetNode(LetNode letNode) {
        pushScope(letNode);
        try {
            this.decompiler.addToken(154);
            this.decompiler.addToken(88);
            Node transformVariableInitializers = transformVariableInitializers(letNode.getVariables());
            this.decompiler.addToken(89);
            letNode.addChildToBack(transformVariableInitializers);
            boolean z = letNode.getType() == 159;
            if (letNode.getBody() != null) {
                if (z) {
                    this.decompiler.addName(" ");
                } else {
                    this.decompiler.addEOL(86);
                }
                letNode.addChildToBack(transform(letNode.getBody()));
                if (!z) {
                    this.decompiler.addEOL(87);
                }
            }
            return letNode;
        } finally {
            popScope();
        }
    }

    private Node transformLiteral(AstNode astNode) {
        this.decompiler.addToken(astNode.getType());
        return astNode;
    }

    private Node transformName(Name name) {
        this.decompiler.addName(name.getIdentifier());
        return name;
    }

    private Node transformNewExpr(NewExpression newExpression) {
        this.decompiler.addToken(30);
        Node createCallOrNew = createCallOrNew(30, transform(newExpression.getTarget()));
        createCallOrNew.setLineno(newExpression.getLineno());
        List<AstNode> arguments = newExpression.getArguments();
        this.decompiler.addToken(88);
        for (int i = 0; i < arguments.size(); i++) {
            createCallOrNew.addChildToBack(transform(arguments.get(i)));
            if (i < arguments.size() - 1) {
                this.decompiler.addToken(90);
            }
        }
        this.decompiler.addToken(89);
        if (newExpression.getInitializer() != null) {
            createCallOrNew.addChildToBack(transformObjectLiteral(newExpression.getInitializer()));
        }
        return createCallOrNew;
    }

    private Node transformNumber(NumberLiteral numberLiteral) {
        this.decompiler.addNumber(numberLiteral.getNumber());
        return numberLiteral;
    }

    private Node transformObjectLiteral(ObjectLiteral objectLiteral) {
        Object[] objArr;
        if (objectLiteral.isDestructuring()) {
            return objectLiteral;
        }
        this.decompiler.addToken(86);
        List<ObjectProperty> elements = objectLiteral.getElements();
        Node node = new Node(67);
        if (elements.isEmpty()) {
            objArr = ScriptRuntime.emptyArgs;
        } else {
            int size = elements.size();
            int i = 0;
            Object[] objArr2 = new Object[size];
            for (ObjectProperty objectProperty : elements) {
                if (objectProperty.isGetterMethod()) {
                    this.decompiler.addToken(152);
                } else if (objectProperty.isSetterMethod()) {
                    this.decompiler.addToken(153);
                } else if (objectProperty.isNormalMethod()) {
                    this.decompiler.addToken(164);
                }
                int i2 = i + 1;
                objArr2[i] = getPropKey(objectProperty.getLeft());
                if (!objectProperty.isMethod()) {
                    this.decompiler.addToken(67);
                }
                Node transform = transform(objectProperty.getRight());
                if (objectProperty.isGetterMethod()) {
                    transform = createUnary(152, transform);
                } else if (objectProperty.isSetterMethod()) {
                    transform = createUnary(153, transform);
                } else if (objectProperty.isNormalMethod()) {
                    transform = createUnary(164, transform);
                }
                node.addChildToBack(transform);
                if (i2 < size) {
                    this.decompiler.addToken(90);
                }
                i = i2;
            }
            objArr = objArr2;
        }
        this.decompiler.addToken(87);
        node.putProp(12, objArr);
        return node;
    }

    private Object getPropKey(Node node) {
        if (node instanceof Name) {
            String identifier = ((Name) node).getIdentifier();
            this.decompiler.addName(identifier);
            return ScriptRuntime.getIndexObject(identifier);
        }
        if (node instanceof StringLiteral) {
            String value = ((StringLiteral) node).getValue();
            this.decompiler.addString(value);
            return ScriptRuntime.getIndexObject(value);
        }
        if (node instanceof NumberLiteral) {
            double number = ((NumberLiteral) node).getNumber();
            this.decompiler.addNumber(number);
            return ScriptRuntime.getIndexObject(number);
        }
        throw Kit.codeBug();
    }

    private Node transformParenExpr(ParenthesizedExpression parenthesizedExpression) {
        AstNode expression = parenthesizedExpression.getExpression();
        this.decompiler.addToken(88);
        int i = 1;
        while (expression instanceof ParenthesizedExpression) {
            this.decompiler.addToken(88);
            i++;
            expression = ((ParenthesizedExpression) expression).getExpression();
        }
        Node transform = transform(expression);
        for (int i2 = 0; i2 < i; i2++) {
            this.decompiler.addToken(89);
        }
        transform.putProp(19, Boolean.TRUE);
        return transform;
    }

    private Node transformPropertyGet(PropertyGet propertyGet) {
        Node transform = transform(propertyGet.getTarget());
        String identifier = propertyGet.getProperty().getIdentifier();
        this.decompiler.addToken(109);
        this.decompiler.addName(identifier);
        return createPropertyGet(transform, null, identifier, 0);
    }

    private Node transformRegExp(RegExpLiteral regExpLiteral) {
        this.decompiler.addRegexp(regExpLiteral.getValue(), regExpLiteral.getFlags());
        this.currentScriptOrFn.addRegExp(regExpLiteral);
        return regExpLiteral;
    }

    private Node transformReturn(ReturnStatement returnStatement) {
        boolean equals = Boolean.TRUE.equals(returnStatement.getProp(25));
        boolean equals2 = Boolean.TRUE.equals(returnStatement.getProp(27));
        if (!equals) {
            this.decompiler.addToken(4);
        } else if (!equals2) {
            this.decompiler.addName(" ");
        }
        AstNode returnValue = returnStatement.getReturnValue();
        Node transform = returnValue == null ? null : transform(returnValue);
        if (!equals) {
            this.decompiler.addEOL(83);
        }
        if (returnValue == null) {
            return new Node(4, returnStatement.getLineno());
        }
        return new Node(4, transform, returnStatement.getLineno());
    }

    private Node transformScript(ScriptNode scriptNode) {
        this.decompiler.addToken(137);
        if (this.currentScope != null) {
            Kit.codeBug();
        }
        this.currentScope = scriptNode;
        Node node = new Node(130);
        Iterator<Node> it = scriptNode.iterator();
        while (it.hasNext()) {
            node.addChildToBack(transform((AstNode) it.next()));
        }
        scriptNode.removeChildren();
        Node firstChild = node.getFirstChild();
        if (firstChild != null) {
            scriptNode.addChildrenToBack(firstChild);
        }
        return scriptNode;
    }

    private Node transformString(StringLiteral stringLiteral) {
        this.decompiler.addString(stringLiteral.getValue());
        return Node.newString(stringLiteral.getValue());
    }

    private Node transformSwitch(SwitchStatement switchStatement) {
        this.decompiler.addToken(115);
        this.decompiler.addToken(88);
        Node transform = transform(switchStatement.getExpression());
        this.decompiler.addToken(89);
        switchStatement.addChildToBack(transform);
        Node node = new Node(130, switchStatement, switchStatement.getLineno());
        this.decompiler.addEOL(86);
        for (SwitchCase switchCase : switchStatement.getCases()) {
            AstNode expression = switchCase.getExpression();
            Node node2 = null;
            if (expression != null) {
                this.decompiler.addToken(116);
                node2 = transform(expression);
            } else {
                this.decompiler.addToken(117);
            }
            this.decompiler.addEOL(104);
            List<AstNode> statements = switchCase.getStatements();
            Block block = new Block();
            if (statements != null) {
                Iterator<AstNode> it = statements.iterator();
                while (it.hasNext()) {
                    block.addChildToBack(transform(it.next()));
                }
            }
            addSwitchCase(node, node2, block);
        }
        this.decompiler.addEOL(87);
        closeSwitch(node);
        return node;
    }

    private Node transformThrow(ThrowStatement throwStatement) {
        this.decompiler.addToken(50);
        Node transform = transform(throwStatement.getExpression());
        this.decompiler.addEOL(83);
        return new Node(50, transform, throwStatement.getLineno());
    }

    private Node transformTry(TryStatement tryStatement) {
        Node emptyExpression;
        this.decompiler.addToken(82);
        this.decompiler.addEOL(86);
        Node transform = transform(tryStatement.getTryBlock());
        this.decompiler.addEOL(87);
        Block block = new Block();
        for (CatchClause catchClause : tryStatement.getCatchClauses()) {
            this.decompiler.addToken(125);
            this.decompiler.addToken(88);
            String identifier = catchClause.getVarName().getIdentifier();
            this.decompiler.addName(identifier);
            AstNode catchCondition = catchClause.getCatchCondition();
            if (catchCondition != null) {
                this.decompiler.addName(" ");
                this.decompiler.addToken(113);
                emptyExpression = transform(catchCondition);
            } else {
                emptyExpression = new EmptyExpression();
            }
            this.decompiler.addToken(89);
            this.decompiler.addEOL(86);
            Node transform2 = transform(catchClause.getBody());
            this.decompiler.addEOL(87);
            block.addChildToBack(createCatch(identifier, emptyExpression, transform2, catchClause.getLineno()));
        }
        Node node = null;
        if (tryStatement.getFinallyBlock() != null) {
            this.decompiler.addToken(126);
            this.decompiler.addEOL(86);
            node = transform(tryStatement.getFinallyBlock());
            this.decompiler.addEOL(87);
        }
        return createTryCatchFinally(transform, block, node, tryStatement.getLineno());
    }

    private Node transformUnary(UnaryExpression unaryExpression) {
        int type = unaryExpression.getType();
        if (type == 75) {
            return transformDefaultXmlNamepace(unaryExpression);
        }
        if (unaryExpression.isPrefix()) {
            this.decompiler.addToken(type);
        }
        Node transform = transform(unaryExpression.getOperand());
        if (unaryExpression.isPostfix()) {
            this.decompiler.addToken(type);
        }
        if (type == 107 || type == 108) {
            return createIncDec(type, unaryExpression.isPostfix(), transform);
        }
        return createUnary(type, transform);
    }

    private Node transformVariables(VariableDeclaration variableDeclaration) {
        this.decompiler.addToken(variableDeclaration.getType());
        transformVariableInitializers(variableDeclaration);
        AstNode parent = variableDeclaration.getParent();
        if (!(parent instanceof Loop) && !(parent instanceof LetNode)) {
            this.decompiler.addEOL(83);
        }
        return variableDeclaration;
    }

    private Node transformVariableInitializers(VariableDeclaration variableDeclaration) {
        Node node;
        List<VariableInitializer> variables = variableDeclaration.getVariables();
        int size = variables.size();
        int i = 0;
        for (VariableInitializer variableInitializer : variables) {
            AstNode target = variableInitializer.getTarget();
            AstNode initializer = variableInitializer.getInitializer();
            if (variableInitializer.isDestructuring()) {
                decompile(target);
                node = target;
            } else {
                node = transform(target);
            }
            Node node2 = null;
            if (initializer != null) {
                this.decompiler.addToken(91);
                node2 = transform(initializer);
            }
            if (!variableInitializer.isDestructuring()) {
                if (node2 != null) {
                    node.addChildToBack(node2);
                }
                variableDeclaration.addChildToBack(node);
            } else if (node2 == null) {
                variableDeclaration.addChildToBack(node);
            } else {
                variableDeclaration.addChildToBack(createDestructuringAssignment(variableDeclaration.getType(), node, node2));
            }
            int i2 = i + 1;
            if (i < size - 1) {
                this.decompiler.addToken(90);
            }
            i = i2;
        }
        return variableDeclaration;
    }

    private Node transformWhileLoop(WhileLoop whileLoop) {
        this.decompiler.addToken(118);
        whileLoop.setType(133);
        pushScope(whileLoop);
        try {
            this.decompiler.addToken(88);
            Node transform = transform(whileLoop.getCondition());
            this.decompiler.addToken(89);
            this.decompiler.addEOL(86);
            Node transform2 = transform(whileLoop.getBody());
            this.decompiler.addEOL(87);
            return createLoop(whileLoop, 1, transform2, transform, null, null);
        } finally {
            popScope();
        }
    }

    private Node transformWith(WithStatement withStatement) {
        this.decompiler.addToken(124);
        this.decompiler.addToken(88);
        Node transform = transform(withStatement.getExpression());
        this.decompiler.addToken(89);
        this.decompiler.addEOL(86);
        Node transform2 = transform(withStatement.getStatement());
        this.decompiler.addEOL(87);
        return createWith(transform, transform2, withStatement.getLineno());
    }

    private Node transformYield(Yield yield) {
        this.decompiler.addToken(73);
        Node transform = yield.getValue() == null ? null : transform(yield.getValue());
        if (transform != null) {
            return new Node(73, transform, yield.getLineno());
        }
        return new Node(73, yield.getLineno());
    }

    private Node transformXmlLiteral(XmlLiteral xmlLiteral) {
        Node transform;
        Node createUnary;
        Node node = new Node(30, xmlLiteral.getLineno());
        List<XmlFragment> fragments = xmlLiteral.getFragments();
        node.addChildToBack(createName(((XmlString) fragments.get(0)).getXml().trim().startsWith("<>") ? "XMLList" : "XML"));
        Node node2 = null;
        for (XmlFragment xmlFragment : fragments) {
            if (xmlFragment instanceof XmlString) {
                String xml = ((XmlString) xmlFragment).getXml();
                this.decompiler.addName(xml);
                if (node2 == null) {
                    node2 = createString(xml);
                } else {
                    node2 = createBinary(21, node2, createString(xml));
                }
            } else {
                XmlExpression xmlExpression = (XmlExpression) xmlFragment;
                boolean isXmlAttribute = xmlExpression.isXmlAttribute();
                this.decompiler.addToken(86);
                if (xmlExpression.getExpression() instanceof EmptyExpression) {
                    transform = createString("");
                } else {
                    transform = transform(xmlExpression.getExpression());
                }
                this.decompiler.addToken(87);
                if (isXmlAttribute) {
                    createUnary = createBinary(21, createBinary(21, createString("\""), createUnary(76, transform)), createString("\""));
                } else {
                    createUnary = createUnary(77, transform);
                }
                node2 = createBinary(21, node2, createUnary);
            }
        }
        node.addChildToBack(node2);
        return node;
    }

    private Node transformXmlMemberGet(XmlMemberGet xmlMemberGet) {
        XmlRef memberRef = xmlMemberGet.getMemberRef();
        Node transform = transform(xmlMemberGet.getLeft());
        int i = memberRef.isAttributeAccess() ? 2 : 0;
        if (xmlMemberGet.getType() == 144) {
            i |= 4;
            this.decompiler.addToken(144);
        } else {
            this.decompiler.addToken(109);
        }
        return transformXmlRef(transform, memberRef, i);
    }

    private Node transformXmlRef(XmlRef xmlRef) {
        return transformXmlRef(null, xmlRef, xmlRef.isAttributeAccess() ? 2 : 0);
    }

    private Node transformXmlRef(Node node, XmlRef xmlRef, int i) {
        if ((i & 2) != 0) {
            this.decompiler.addToken(148);
        }
        Name namespace = xmlRef.getNamespace();
        String identifier = namespace != null ? namespace.getIdentifier() : null;
        if (identifier != null) {
            this.decompiler.addName(identifier);
            this.decompiler.addToken(145);
        }
        if (xmlRef instanceof XmlPropRef) {
            String identifier2 = ((XmlPropRef) xmlRef).getPropName().getIdentifier();
            this.decompiler.addName(identifier2);
            return createPropertyGet(node, identifier, identifier2, i);
        }
        this.decompiler.addToken(84);
        Node transform = transform(((XmlElemRef) xmlRef).getExpression());
        this.decompiler.addToken(85);
        return createElementGet(node, identifier, transform, i);
    }

    private Node transformDefaultXmlNamepace(UnaryExpression unaryExpression) {
        this.decompiler.addToken(117);
        this.decompiler.addName(" xml");
        this.decompiler.addName(" namespace");
        this.decompiler.addToken(91);
        return createUnary(75, transform(unaryExpression.getOperand()));
    }

    private void addSwitchCase(Node node, Node node2, Node node3) {
        if (node.getType() != 130) {
            throw Kit.codeBug();
        }
        Jump jump = (Jump) node.getFirstChild();
        if (jump.getType() != 115) {
            throw Kit.codeBug();
        }
        Node newTarget = Node.newTarget();
        if (node2 != null) {
            Jump jump2 = new Jump(116, node2);
            jump2.target = newTarget;
            jump.addChildToBack(jump2);
        } else {
            jump.setDefault(newTarget);
        }
        node.addChildToBack(newTarget);
        node.addChildToBack(node3);
    }

    private void closeSwitch(Node node) {
        if (node.getType() != 130) {
            throw Kit.codeBug();
        }
        Jump jump = (Jump) node.getFirstChild();
        if (jump.getType() != 115) {
            throw Kit.codeBug();
        }
        Node newTarget = Node.newTarget();
        jump.target = newTarget;
        Node node2 = jump.getDefault();
        if (node2 == null) {
            node2 = newTarget;
        }
        node.addChildAfter(makeJump(5, node2), jump);
        node.addChildToBack(newTarget);
    }

    private Node createExprStatementNoReturn(Node node, int i) {
        return new Node(134, node, i);
    }

    private Node createString(String str) {
        return Node.newString(str);
    }

    private Node createCatch(String str, Node node, Node node2, int i) {
        if (node == null) {
            node = new Node(129);
        }
        return new Node(125, createName(str), node, node2, i);
    }

    private Node initFunction(FunctionNode functionNode, int i, Node node, int i2) {
        Name functionName;
        functionNode.setFunctionType(i2);
        functionNode.addChildToBack(node);
        if (functionNode.getFunctionCount() != 0) {
            functionNode.setRequiresActivation();
        }
        if (i2 == 2 && (functionName = functionNode.getFunctionName()) != null && functionName.length() != 0 && functionNode.getSymbol(functionName.getIdentifier()) == null) {
            functionNode.putSymbol(new org.mozilla.javascript.ast.Symbol(110, functionName.getIdentifier()));
            node.addChildrenToFront(new Node(134, new Node(8, Node.newString(49, functionName.getIdentifier()), new Node(64))));
        }
        Node lastChild = node.getLastChild();
        if (lastChild == null || lastChild.getType() != 4) {
            node.addChildToBack(new Node(4));
        }
        Node newString = Node.newString(110, functionNode.getName());
        newString.putIntProp(1, i);
        return newString;
    }

    private Scope createLoopNode(Node node, int i) {
        Scope createScopeNode = createScopeNode(133, i);
        if (node != null) {
            ((Jump) node).setLoop(createScopeNode);
        }
        return createScopeNode;
    }

    private Node createFor(Scope scope, Node node, Node node2, Node node3, Node node4) {
        if (node.getType() == 154) {
            Scope splitScope = Scope.splitScope(scope);
            splitScope.setType(154);
            splitScope.addChildrenToBack(node);
            splitScope.addChildToBack(createLoop(scope, 2, node4, node2, new Node(129), node3));
            return splitScope;
        }
        return createLoop(scope, 2, node4, node2, node, node3);
    }

    private Node createLoop(Jump jump, int i, Node node, Node node2, Node node3, Node node4) {
        Node newTarget = Node.newTarget();
        Node newTarget2 = Node.newTarget();
        if (i == 2 && node2.getType() == 129) {
            node2 = new Node(45);
        }
        Jump jump2 = new Jump(6, node2);
        jump2.target = newTarget;
        Node newTarget3 = Node.newTarget();
        jump.addChildToBack(newTarget);
        jump.addChildrenToBack(node);
        if (i == 1 || i == 2) {
            jump.addChildrenToBack(new Node(129, jump.getLineno()));
        }
        jump.addChildToBack(newTarget2);
        jump.addChildToBack(jump2);
        jump.addChildToBack(newTarget3);
        jump.target = newTarget3;
        if (i == 1 || i == 2) {
            jump.addChildToFront(makeJump(5, newTarget2));
            if (i == 2) {
                int type = node3.getType();
                if (type != 129) {
                    if (type != 123 && type != 154) {
                        node3 = new Node(134, node3);
                    }
                    jump.addChildToFront(node3);
                }
                newTarget2 = Node.newTarget();
                jump.addChildAfter(newTarget2, node);
                if (node4.getType() != 129) {
                    jump.addChildAfter(new Node(134, node4), newTarget2);
                }
            }
        }
        jump.setContinue(newTarget2);
        return jump;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Node createForIn(int i, Node node, Node node2, Node node3, Node node4, boolean z, boolean z2) {
        int type;
        Node node5;
        Node simpleAssignment;
        int type2 = node2.getType();
        if (type2 != 123 && type2 != 154) {
            if (type2 == 66 || type2 == 67) {
                r5 = node2 instanceof ArrayLiteral ? ((ArrayLiteral) node2).getDestructuringLength() : 0;
                type = type2;
                node5 = node2;
                Node node6 = new Node(142);
                Node node7 = new Node(!z ? 59 : z2 ? 61 : type2 != -1 ? 60 : 58, node3);
                node7.putProp(3, node6);
                Node node8 = new Node(62);
                node8.putProp(3, node6);
                Node node9 = new Node(63);
                node9.putProp(3, node6);
                Node node10 = new Node(130);
                if (type2 == -1) {
                }
                node10.addChildToBack(new Node(134, simpleAssignment));
                node10.addChildToBack(node4);
                Node createLoop = createLoop((Jump) node, 1, node10, node8, null, null);
                createLoop.addChildToFront(node7);
                if (type != 123) {
                }
                createLoop.addChildToFront(node2);
                node6.addChildToBack(createLoop);
                return node6;
            }
            node5 = makeReference(node2);
            if (node5 == null) {
                reportError("msg.bad.for.in.lhs");
                return null;
            }
            type = type2;
            type2 = -1;
            Node node62 = new Node(142);
            Node node72 = new Node(!z ? 59 : z2 ? 61 : type2 != -1 ? 60 : 58, node3);
            node72.putProp(3, node62);
            Node node82 = new Node(62);
            node82.putProp(3, node62);
            Node node92 = new Node(63);
            node92.putProp(3, node62);
            Node node102 = new Node(130);
            if (type2 == -1) {
            }
            node102.addChildToBack(new Node(134, simpleAssignment));
            node102.addChildToBack(node4);
            Node createLoop2 = createLoop((Jump) node, 1, node102, node82, null, null);
            createLoop2.addChildToFront(node72);
            if (type != 123) {
            }
            createLoop2.addChildToFront(node2);
            node62.addChildToBack(createLoop2);
            return node62;
        }
        Node lastChild = node2.getLastChild();
        type = lastChild.getType();
        if (type != 66 && type != 67) {
            if (type == 39) {
                type = type2;
                node5 = Node.newString(39, lastChild.getString());
                type2 = -1;
                Node node622 = new Node(142);
                Node node722 = new Node(!z ? 59 : z2 ? 61 : type2 != -1 ? 60 : 58, node3);
                node722.putProp(3, node622);
                Node node822 = new Node(62);
                node822.putProp(3, node622);
                Node node922 = new Node(63);
                node922.putProp(3, node622);
                Node node1022 = new Node(130);
                if (type2 == -1) {
                }
                node1022.addChildToBack(new Node(134, simpleAssignment));
                node1022.addChildToBack(node4);
                Node createLoop22 = createLoop((Jump) node, 1, node1022, node822, null, null);
                createLoop22.addChildToFront(node722);
                if (type != 123) {
                }
                createLoop22.addChildToFront(node2);
                node622.addChildToBack(createLoop22);
                return node622;
            }
            reportError("msg.bad.for.in.lhs");
            return null;
        }
        r5 = lastChild instanceof ArrayLiteral ? ((ArrayLiteral) lastChild).getDestructuringLength() : 0;
        node5 = lastChild;
        type2 = type;
        Node node6222 = new Node(142);
        Node node7222 = new Node(!z ? 59 : z2 ? 61 : type2 != -1 ? 60 : 58, node3);
        node7222.putProp(3, node6222);
        Node node8222 = new Node(62);
        node8222.putProp(3, node6222);
        Node node9222 = new Node(63);
        node9222.putProp(3, node6222);
        Node node10222 = new Node(130);
        if (type2 == -1) {
            simpleAssignment = createDestructuringAssignment(i, node5, node9222);
            if (!z && !z2 && (type2 == 67 || r5 != 2)) {
                reportError("msg.bad.for.in.destruct");
            }
        } else {
            simpleAssignment = simpleAssignment(node5, node9222);
        }
        node10222.addChildToBack(new Node(134, simpleAssignment));
        node10222.addChildToBack(node4);
        Node createLoop222 = createLoop((Jump) node, 1, node10222, node8222, null, null);
        createLoop222.addChildToFront(node7222);
        if (type != 123 || type == 154) {
            createLoop222.addChildToFront(node2);
        }
        node6222.addChildToBack(createLoop222);
        return node6222;
    }

    private Node createTryCatchFinally(Node node, Node node2, Node node3, int i) {
        boolean z = false;
        boolean z2 = node3 != null && (node3.getType() != 130 || node3.hasChildren());
        if (node.getType() == 130 && !node.hasChildren() && !z2) {
            return node;
        }
        boolean hasChildren = node2.hasChildren();
        if (!z2 && !hasChildren) {
            return node;
        }
        Node node4 = new Node(142);
        Jump jump = new Jump(82, node, i);
        int i2 = 3;
        jump.putProp(3, node4);
        if (hasChildren) {
            Node newTarget = Node.newTarget();
            jump.addChildToBack(makeJump(5, newTarget));
            Node newTarget2 = Node.newTarget();
            jump.target = newTarget2;
            jump.addChildToBack(newTarget2);
            Node node5 = new Node(142);
            Node firstChild = node2.getFirstChild();
            int i3 = 0;
            while (firstChild != null) {
                int lineno = firstChild.getLineno();
                Node firstChild2 = firstChild.getFirstChild();
                Node next = firstChild2.getNext();
                Node next2 = next.getNext();
                firstChild.removeChild(firstChild2);
                firstChild.removeChild(next);
                firstChild.removeChild(next2);
                next2.addChildToBack(new Node(i2));
                next2.addChildToBack(makeJump(5, newTarget));
                if (next.getType() == 129) {
                    z = true;
                } else {
                    next2 = createIf(next, next2, null, lineno);
                }
                Node node6 = new Node(57, firstChild2, createUseLocal(node4));
                node6.putProp(3, node5);
                node6.putIntProp(14, i3);
                node5.addChildToBack(node6);
                node5.addChildToBack(createWith(createUseLocal(node5), next2, lineno));
                firstChild = firstChild.getNext();
                i3++;
                i2 = 3;
            }
            jump.addChildToBack(node5);
            if (!z) {
                Node node7 = new Node(51);
                node7.putProp(3, node4);
                jump.addChildToBack(node7);
            }
            jump.addChildToBack(newTarget);
        }
        if (z2) {
            Node newTarget3 = Node.newTarget();
            jump.setFinally(newTarget3);
            jump.addChildToBack(makeJump(136, newTarget3));
            Node newTarget4 = Node.newTarget();
            jump.addChildToBack(makeJump(5, newTarget4));
            jump.addChildToBack(newTarget3);
            Node node8 = new Node(126, node3);
            node8.putProp(3, node4);
            jump.addChildToBack(node8);
            jump.addChildToBack(newTarget4);
        }
        node4.addChildToBack(jump);
        return node4;
    }

    private Node createWith(Node node, Node node2, int i) {
        setRequiresActivation();
        Node node3 = new Node(130, i);
        node3.addChildToBack(new Node(2, node));
        node3.addChildrenToBack(new Node(124, node2, i));
        node3.addChildToBack(new Node(3));
        return node3;
    }

    private Node createIf(Node node, Node node2, Node node3, int i) {
        int isAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
        if (isAlwaysDefinedBoolean == 1) {
            return node2;
        }
        if (isAlwaysDefinedBoolean == -1) {
            return node3 != null ? node3 : new Node(130, i);
        }
        Node node4 = new Node(130, i);
        Node newTarget = Node.newTarget();
        Jump jump = new Jump(7, node);
        jump.target = newTarget;
        node4.addChildToBack(jump);
        node4.addChildrenToBack(node2);
        if (node3 != null) {
            Node newTarget2 = Node.newTarget();
            node4.addChildToBack(makeJump(5, newTarget2));
            node4.addChildToBack(newTarget);
            node4.addChildrenToBack(node3);
            node4.addChildToBack(newTarget2);
        } else {
            node4.addChildToBack(newTarget);
        }
        return node4;
    }

    private Node createCondExpr(Node node, Node node2, Node node3) {
        int isAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
        return isAlwaysDefinedBoolean == 1 ? node2 : isAlwaysDefinedBoolean == -1 ? node3 : new Node(103, node, node2, node3);
    }

    private Node createUnary(int i, Node node) {
        int type = node.getType();
        switch (i) {
            case 26:
                int isAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
                if (isAlwaysDefinedBoolean != 0) {
                    int i2 = isAlwaysDefinedBoolean == 1 ? 44 : 45;
                    if (type == 45 || type == 44) {
                        node.setType(i2);
                        return node;
                    }
                    return new Node(i2);
                }
                break;
            case 27:
                if (type == 40) {
                    node.setDouble(~ScriptRuntime.toInt32(node.getDouble()));
                    return node;
                }
                break;
            case 29:
                if (type == 40) {
                    node.setDouble(-node.getDouble());
                    return node;
                }
                break;
            case 31:
                if (type == 39) {
                    node.setType(49);
                    return new Node(i, node, Node.newString(node.getString()));
                }
                if (type == 33 || type == 36) {
                    Node firstChild = node.getFirstChild();
                    Node lastChild = node.getLastChild();
                    node.removeChild(firstChild);
                    node.removeChild(lastChild);
                    return new Node(i, firstChild, lastChild);
                }
                if (type == 68) {
                    Node firstChild2 = node.getFirstChild();
                    node.removeChild(firstChild2);
                    return new Node(70, firstChild2);
                }
                return new Node(i, new Node(45), node);
            case 32:
                if (type == 39) {
                    node.setType(138);
                    return node;
                }
                break;
        }
        return new Node(i, node);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
    
        if (r7.getLastChild().getString().equals("eval") != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Node createCallOrNew(int i, Node node) {
        int i2 = 1;
        if (node.getType() == 39) {
            String string = node.getString();
            if (!string.equals("eval")) {
                if (string.equals("With")) {
                    i2 = 2;
                }
                i2 = 0;
            }
        } else {
            if (node.getType() == 33) {
            }
            i2 = 0;
        }
        Node node2 = new Node(i, node);
        if (i2 != 0) {
            setRequiresActivation();
            node2.putIntProp(10, i2);
        }
        return node2;
    }

    private Node createIncDec(int i, boolean z, Node node) {
        Node makeReference = makeReference(node);
        int type = makeReference.getType();
        if (type == 33 || type == 36 || type == 39 || type == 68) {
            Node node2 = new Node(i, makeReference);
            int i2 = i == 108 ? 1 : 0;
            if (z) {
                i2 |= 2;
            }
            node2.putIntProp(13, i2);
            return node2;
        }
        throw Kit.codeBug();
    }

    private Node createPropertyGet(Node node, String str, String str2, int i) {
        if (str != null || i != 0) {
            return createMemberRefGet(node, str, Node.newString(str2), i | 1);
        }
        if (node == null) {
            return createName(str2);
        }
        checkActivationName(str2, 33);
        if (ScriptRuntime.isSpecialProperty(str2)) {
            Node node2 = new Node(72, node);
            node2.putProp(17, str2);
            return new Node(68, node2);
        }
        return new Node(33, node, Node.newString(str2));
    }

    private Node createElementGet(Node node, String str, Node node2, int i) {
        if (str != null || i != 0) {
            return createMemberRefGet(node, str, node2, i);
        }
        if (node == null) {
            throw Kit.codeBug();
        }
        return new Node(36, node, node2);
    }

    private Node createMemberRefGet(Node node, String str, Node node2, int i) {
        Node node3;
        Node node4;
        Node node5;
        if (str == null) {
            node3 = null;
        } else if (str.equals("*")) {
            node3 = new Node(42);
        } else {
            node3 = createName(str);
        }
        if (node != null) {
            if (str == null) {
                node4 = new Node(78, node, node2);
            } else {
                node4 = new Node(79, node, node3, node2);
            }
            node5 = node4;
        } else if (str == null) {
            node5 = new Node(80, node2);
        } else {
            node5 = new Node(81, node3, node2);
        }
        if (i != 0) {
            node5.putIntProp(16, i);
        }
        return new Node(68, node5);
    }

    private Node createBinary(int i, Node node, Node node2) {
        String numberToString;
        if (i == 105) {
            int isAlwaysDefinedBoolean = isAlwaysDefinedBoolean(node);
            if (isAlwaysDefinedBoolean == 1) {
                return node;
            }
            if (isAlwaysDefinedBoolean == -1) {
                return node2;
            }
        } else if (i != 106) {
            switch (i) {
                case 21:
                    if (node.type == 41) {
                        if (node2.type == 41) {
                            numberToString = node2.getString();
                        } else if (node2.type == 40) {
                            numberToString = ScriptRuntime.numberToString(node2.getDouble(), 10);
                        }
                        node.setString(node.getString().concat(numberToString));
                        return node;
                    }
                    if (node.type == 40) {
                        if (node2.type == 40) {
                            node.setDouble(node.getDouble() + node2.getDouble());
                            return node;
                        }
                        if (node2.type == 41) {
                            node2.setString(ScriptRuntime.numberToString(node.getDouble(), 10).concat(node2.getString()));
                            return node2;
                        }
                    }
                    break;
                case 22:
                    if (node.type == 40) {
                        double d = node.getDouble();
                        if (node2.type == 40) {
                            node.setDouble(d - node2.getDouble());
                            return node;
                        }
                        if (d == 0.0d) {
                            return new Node(29, node2);
                        }
                    } else if (node2.type == 40 && node2.getDouble() == 0.0d) {
                        return new Node(28, node);
                    }
                    break;
                case 23:
                    if (node.type == 40) {
                        double d2 = node.getDouble();
                        if (node2.type == 40) {
                            node.setDouble(d2 * node2.getDouble());
                            return node;
                        }
                        if (d2 == 1.0d) {
                            return new Node(28, node2);
                        }
                    } else if (node2.type == 40 && node2.getDouble() == 1.0d) {
                        return new Node(28, node);
                    }
                    break;
                case 24:
                    if (node2.type == 40) {
                        double d3 = node2.getDouble();
                        if (node.type == 40) {
                            node.setDouble(node.getDouble() / d3);
                            return node;
                        }
                        if (d3 == 1.0d) {
                            return new Node(28, node);
                        }
                    }
                    break;
            }
        } else {
            int isAlwaysDefinedBoolean2 = isAlwaysDefinedBoolean(node);
            if (isAlwaysDefinedBoolean2 == -1) {
                return node;
            }
            if (isAlwaysDefinedBoolean2 == 1) {
                return node2;
            }
        }
        return new Node(i, node, node2);
    }

    private Node createAssignment(int i, Node node, Node node2) {
        int i2;
        Node makeReference = makeReference(node);
        if (makeReference == null) {
            if (node.getType() != 66 && node.getType() != 67) {
                reportError("msg.bad.assign.left");
                return node2;
            }
            if (i != 91) {
                reportError("msg.bad.destruct.op");
                return node2;
            }
            return createDestructuringAssignment(-1, node, node2);
        }
        switch (i) {
            case 91:
                return simpleAssignment(makeReference, node2);
            case 92:
                i2 = 9;
                break;
            case 93:
                i2 = 10;
                break;
            case 94:
                i2 = 11;
                break;
            case 95:
                i2 = 18;
                break;
            case 96:
                i2 = 19;
                break;
            case 97:
                i2 = 20;
                break;
            case 98:
                i2 = 21;
                break;
            case 99:
                i2 = 22;
                break;
            case 100:
                i2 = 23;
                break;
            case 101:
                i2 = 24;
                break;
            case 102:
                i2 = 25;
                break;
            default:
                throw Kit.codeBug();
        }
        int type = makeReference.getType();
        if (type == 33 || type == 36) {
            return new Node(type == 33 ? 140 : 141, makeReference.getFirstChild(), makeReference.getLastChild(), new Node(i2, new Node(139), node2));
        }
        if (type == 39) {
            return new Node(8, Node.newString(49, makeReference.getString()), new Node(i2, makeReference, node2));
        }
        if (type == 68) {
            Node firstChild = makeReference.getFirstChild();
            checkMutableReference(firstChild);
            return new Node(143, firstChild, new Node(i2, new Node(139), node2));
        }
        throw Kit.codeBug();
    }

    private Node createUseLocal(Node node) {
        if (142 != node.getType()) {
            throw Kit.codeBug();
        }
        Node node2 = new Node(54);
        node2.putProp(3, node);
        return node2;
    }

    private Jump makeJump(int i, Node node) {
        Jump jump = new Jump(i);
        jump.target = node;
        return jump;
    }

    private Node makeReference(Node node) {
        int type = node.getType();
        if (type != 33 && type != 36 && type != 68) {
            if (type == 38) {
                node.setType(71);
                return new Node(68, node);
            }
            if (type != 39) {
                return null;
            }
        }
        return node;
    }

    private static int isAlwaysDefinedBoolean(Node node) {
        int type = node.getType();
        if (type == 40) {
            double d = node.getDouble();
            return (d != d || d == 0.0d) ? -1 : 1;
        }
        if (type == 42 || type == 44) {
            return -1;
        }
        return type != 45 ? 0 : 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean isDestructuring(Node node) {
        return (node instanceof DestructuringForm) && ((DestructuringForm) node).isDestructuring();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    Node decompileFunctionHeader(FunctionNode functionNode) {
        Node transform;
        int i;
        boolean z;
        boolean z2;
        List<AstNode> params;
        if (functionNode.getFunctionName() != null) {
            this.decompiler.addName(functionNode.getName());
        } else if (functionNode.getMemberExprNode() != null) {
            transform = transform(functionNode.getMemberExprNode());
            z = functionNode.getFunctionType() != 4;
            z2 = !z && functionNode.getLp() == -1;
            if (!z2) {
                this.decompiler.addToken(88);
            }
            params = functionNode.getParams();
            for (i = 0; i < params.size(); i++) {
                decompile(params.get(i));
                if (i < params.size() - 1) {
                    this.decompiler.addToken(90);
                }
            }
            if (!z2) {
                this.decompiler.addToken(89);
            }
            if (z) {
                this.decompiler.addToken(165);
            }
            if (!functionNode.isExpressionClosure()) {
                this.decompiler.addEOL(86);
            }
            return transform;
        }
        transform = null;
        if (functionNode.getFunctionType() != 4) {
        }
        if (z) {
        }
        if (!z2) {
        }
        params = functionNode.getParams();
        while (i < params.size()) {
        }
        if (!z2) {
        }
        if (z) {
        }
        if (!functionNode.isExpressionClosure()) {
        }
        return transform;
    }

    void decompile(AstNode astNode) {
        int type = astNode.getType();
        if (type == 33) {
            decompilePropertyGet((PropertyGet) astNode);
            return;
        }
        if (type == 36) {
            decompileElementGet((ElementGet) astNode);
            return;
        }
        if (type == 43) {
            this.decompiler.addToken(astNode.getType());
            return;
        }
        if (type != 129) {
            if (type == 66) {
                decompileArrayLiteral((ArrayLiteral) astNode);
                return;
            }
            if (type == 67) {
                decompileObjectLiteral((ObjectLiteral) astNode);
                return;
            }
            switch (type) {
                case 39:
                    this.decompiler.addName(((Name) astNode).getIdentifier());
                    return;
                case 40:
                    this.decompiler.addNumber(((NumberLiteral) astNode).getNumber());
                    return;
                case 41:
                    this.decompiler.addString(((StringLiteral) astNode).getValue());
                    return;
                default:
                    Kit.codeBug("unexpected token: " + Token.typeToName(astNode.getType()));
                    return;
            }
        }
    }

    void decompileArrayLiteral(ArrayLiteral arrayLiteral) {
        this.decompiler.addToken(84);
        List<AstNode> elements = arrayLiteral.getElements();
        int size = elements.size();
        for (int i = 0; i < size; i++) {
            decompile(elements.get(i));
            if (i < size - 1) {
                this.decompiler.addToken(90);
            }
        }
        this.decompiler.addToken(85);
    }

    void decompileObjectLiteral(ObjectLiteral objectLiteral) {
        this.decompiler.addToken(86);
        List<ObjectProperty> elements = objectLiteral.getElements();
        int size = elements.size();
        for (int i = 0; i < size; i++) {
            ObjectProperty objectProperty = elements.get(i);
            boolean equals = Boolean.TRUE.equals(objectProperty.getProp(26));
            decompile(objectProperty.getLeft());
            if (!equals) {
                this.decompiler.addToken(104);
                decompile(objectProperty.getRight());
            }
            if (i < size - 1) {
                this.decompiler.addToken(90);
            }
        }
        this.decompiler.addToken(87);
    }

    void decompilePropertyGet(PropertyGet propertyGet) {
        decompile(propertyGet.getTarget());
        this.decompiler.addToken(109);
        decompile(propertyGet.getProperty());
    }

    void decompileElementGet(ElementGet elementGet) {
        decompile(elementGet.getTarget());
        this.decompiler.addToken(84);
        decompile(elementGet.getElement());
        this.decompiler.addToken(85);
    }
}
