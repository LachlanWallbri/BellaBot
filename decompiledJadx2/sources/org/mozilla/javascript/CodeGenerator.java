package org.mozilla.javascript;

import androidx.core.view.InputDeviceCompat;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.ScriptNode;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
class CodeGenerator extends Icode {
    private static final int ECF_TAIL = 1;
    private static final int MIN_FIXUP_TABLE_SIZE = 40;
    private static final int MIN_LABEL_TABLE_SIZE = 32;
    private CompilerEnvirons compilerEnv;
    private int doubleTableTop;
    private int exceptionTableTop;
    private long[] fixupTable;
    private int fixupTableTop;
    private int iCodeTop;
    private InterpreterData itsData;
    private boolean itsInFunctionFlag;
    private boolean itsInTryFlag;
    private int[] labelTable;
    private int labelTableTop;
    private int lineNumber;
    private int localTop;
    private ScriptNode scriptOrFn;
    private int stackDepth;
    private ObjToIntMap strings = new ObjToIntMap(20);
    private ObjArray literalIds = new ObjArray();

    public InterpreterData compile(CompilerEnvirons compilerEnvirons, ScriptNode scriptNode, String str, boolean z) {
        this.compilerEnv = compilerEnvirons;
        new NodeTransformer().transform(scriptNode, compilerEnvirons);
        if (z) {
            this.scriptOrFn = scriptNode.getFunctionNode(0);
        } else {
            this.scriptOrFn = scriptNode;
        }
        InterpreterData interpreterData = new InterpreterData(compilerEnvirons.getLanguageVersion(), this.scriptOrFn.getSourceName(), str, this.scriptOrFn.isInStrictMode());
        this.itsData = interpreterData;
        interpreterData.topLevel = true;
        if (z) {
            generateFunctionICode();
        } else {
            generateICodeFromTree(this.scriptOrFn);
        }
        return this.itsData;
    }

    private void generateFunctionICode() {
        this.itsInFunctionFlag = true;
        FunctionNode functionNode = (FunctionNode) this.scriptOrFn;
        this.itsData.itsFunctionType = functionNode.getFunctionType();
        this.itsData.itsNeedsActivation = functionNode.requiresActivation();
        if (functionNode.getFunctionName() != null) {
            this.itsData.itsName = functionNode.getName();
        }
        if (functionNode.isGenerator()) {
            addIcode(-62);
            addUint16(functionNode.getBaseLineno() & 65535);
        }
        if (functionNode.isInStrictMode()) {
            this.itsData.isStrict = true;
        }
        generateICodeFromTree(functionNode.getLastChild());
    }

    private void generateICodeFromTree(Node node) {
        generateNestedFunctions();
        generateRegExpLiterals();
        visitStatement(node, 0);
        fixLabelGotos();
        if (this.itsData.itsFunctionType == 0) {
            addToken(65);
        }
        int length = this.itsData.itsICode.length;
        int i = this.iCodeTop;
        if (length != i) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.itsData.itsICode, 0, bArr, 0, this.iCodeTop);
            this.itsData.itsICode = bArr;
        }
        if (this.strings.size() == 0) {
            this.itsData.itsStringTable = null;
        } else {
            this.itsData.itsStringTable = new String[this.strings.size()];
            ObjToIntMap.Iterator newIterator = this.strings.newIterator();
            newIterator.start();
            while (!newIterator.done()) {
                String str = (String) newIterator.getKey();
                int value = newIterator.getValue();
                if (this.itsData.itsStringTable[value] != null) {
                    Kit.codeBug();
                }
                this.itsData.itsStringTable[value] = str;
                newIterator.next();
            }
        }
        if (this.doubleTableTop == 0) {
            this.itsData.itsDoubleTable = null;
        } else {
            int length2 = this.itsData.itsDoubleTable.length;
            int i2 = this.doubleTableTop;
            if (length2 != i2) {
                double[] dArr = new double[i2];
                System.arraycopy(this.itsData.itsDoubleTable, 0, dArr, 0, this.doubleTableTop);
                this.itsData.itsDoubleTable = dArr;
            }
        }
        if (this.exceptionTableTop != 0) {
            int length3 = this.itsData.itsExceptionTable.length;
            int i3 = this.exceptionTableTop;
            if (length3 != i3) {
                int[] iArr = new int[i3];
                System.arraycopy(this.itsData.itsExceptionTable, 0, iArr, 0, this.exceptionTableTop);
                this.itsData.itsExceptionTable = iArr;
            }
        }
        this.itsData.itsMaxVars = this.scriptOrFn.getParamAndVarCount();
        InterpreterData interpreterData = this.itsData;
        interpreterData.itsMaxFrameArray = interpreterData.itsMaxVars + this.itsData.itsMaxLocals + this.itsData.itsMaxStack;
        this.itsData.argNames = this.scriptOrFn.getParamAndVarNames();
        this.itsData.argIsConst = this.scriptOrFn.getParamAndVarConst();
        this.itsData.argCount = this.scriptOrFn.getParamCount();
        this.itsData.encodedSourceStart = this.scriptOrFn.getEncodedSourceStart();
        this.itsData.encodedSourceEnd = this.scriptOrFn.getEncodedSourceEnd();
        if (this.literalIds.size() != 0) {
            this.itsData.literalIds = this.literalIds.toArray();
        }
    }

    private void generateNestedFunctions() {
        int functionCount = this.scriptOrFn.getFunctionCount();
        if (functionCount == 0) {
            return;
        }
        InterpreterData[] interpreterDataArr = new InterpreterData[functionCount];
        for (int i = 0; i != functionCount; i++) {
            FunctionNode functionNode = this.scriptOrFn.getFunctionNode(i);
            CodeGenerator codeGenerator = new CodeGenerator();
            codeGenerator.compilerEnv = this.compilerEnv;
            codeGenerator.scriptOrFn = functionNode;
            codeGenerator.itsData = new InterpreterData(this.itsData);
            codeGenerator.generateFunctionICode();
            interpreterDataArr[i] = codeGenerator.itsData;
        }
        this.itsData.itsNestedFunctions = interpreterDataArr;
    }

    private void generateRegExpLiterals() {
        int regexpCount = this.scriptOrFn.getRegexpCount();
        if (regexpCount == 0) {
            return;
        }
        Context context = Context.getContext();
        RegExpProxy checkRegExpProxy = ScriptRuntime.checkRegExpProxy(context);
        Object[] objArr = new Object[regexpCount];
        for (int i = 0; i != regexpCount; i++) {
            objArr[i] = checkRegExpProxy.compileRegExp(context, this.scriptOrFn.getRegexpString(i), this.scriptOrFn.getRegexpFlags(i));
        }
        this.itsData.itsRegExpLiterals = objArr;
    }

    private void updateLineNumber(Node node) {
        int lineno = node.getLineno();
        if (lineno == this.lineNumber || lineno < 0) {
            return;
        }
        if (this.itsData.firstLinePC < 0) {
            this.itsData.firstLinePC = lineno;
        }
        this.lineNumber = lineno;
        addIcode(-26);
        addUint16(lineno & 65535);
    }

    private RuntimeException badTree(Node node) {
        throw new RuntimeException(node.toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x0040. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x0043. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0046. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0169 A[LOOP:0: B:36:0x0167->B:37:0x0169, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void visitStatement(Node node, int i) {
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        if (type != -62) {
            if (type == 65) {
                updateLineNumber(node);
                addToken(65);
            } else if (type == 82) {
                Jump jump = (Jump) node;
                int localBlockRef = getLocalBlockRef(jump);
                int allocLocal = allocLocal();
                addIndexOp(-13, allocLocal);
                int i2 = this.iCodeTop;
                boolean z = this.itsInTryFlag;
                this.itsInTryFlag = true;
                while (firstChild != null) {
                    visitStatement(firstChild, i);
                    firstChild = firstChild.getNext();
                }
                this.itsInTryFlag = z;
                Node node2 = jump.target;
                if (node2 != null) {
                    int i3 = this.labelTable[getTargetLabel(node2)];
                    addExceptionHandler(i2, i3, i3, false, localBlockRef, allocLocal);
                }
                Node node3 = jump.getFinally();
                if (node3 != null) {
                    int i4 = this.labelTable[getTargetLabel(node3)];
                    addExceptionHandler(i2, i4, i4, true, localBlockRef, allocLocal);
                }
                addIndexOp(-56, allocLocal);
                releaseLocal(allocLocal);
            } else if (type == 110) {
                int existingIntProp = node.getExistingIntProp(1);
                int functionType = this.scriptOrFn.getFunctionNode(existingIntProp).getFunctionType();
                if (functionType == 3) {
                    addIndexOp(-20, existingIntProp);
                } else if (functionType != 1) {
                    throw Kit.codeBug();
                }
                if (!this.itsInFunctionFlag) {
                    addIndexOp(-19, existingIntProp);
                    stackChange(1);
                    addIcode(-5);
                    stackChange(-1);
                }
            } else if (type != 115) {
                if (type != 124) {
                    if (type == 126) {
                        stackChange(1);
                        int localBlockRef2 = getLocalBlockRef(node);
                        addIndexOp(-24, localBlockRef2);
                        stackChange(-1);
                        while (firstChild != null) {
                            visitStatement(firstChild, i);
                            firstChild = firstChild.getNext();
                        }
                        addIndexOp(-25, localBlockRef2);
                    } else if (type == 142) {
                        int allocLocal2 = allocLocal();
                        node.putIntProp(2, allocLocal2);
                        updateLineNumber(node);
                        while (firstChild != null) {
                            visitStatement(firstChild, i);
                            firstChild = firstChild.getNext();
                        }
                        addIndexOp(-56, allocLocal2);
                        releaseLocal(allocLocal2);
                    } else if (type == 161) {
                        addIcode(-64);
                    } else if (type == 50) {
                        updateLineNumber(node);
                        visitExpression(firstChild, 0);
                        addToken(50);
                        addUint16(this.lineNumber & 65535);
                        stackChange(-1);
                    } else if (type != 51) {
                        switch (type) {
                            case 2:
                                visitExpression(firstChild, 0);
                                addToken(2);
                                stackChange(-1);
                                break;
                            case 3:
                                addToken(3);
                                break;
                            case 4:
                                updateLineNumber(node);
                                if (node.getIntProp(20, 0) != 0) {
                                    addIcode(-63);
                                    addUint16(this.lineNumber & 65535);
                                    break;
                                } else if (firstChild != null) {
                                    visitExpression(firstChild, 1);
                                    addToken(4);
                                    stackChange(-1);
                                    break;
                                } else {
                                    addIcode(-22);
                                    break;
                                }
                            case 5:
                                addGoto(((Jump) node).target, type);
                                break;
                            case 6:
                            case 7:
                                Node node4 = ((Jump) node).target;
                                visitExpression(firstChild, 0);
                                addGoto(node4, type);
                                stackChange(-1);
                                break;
                            default:
                                switch (type) {
                                    case 57:
                                        int localBlockRef3 = getLocalBlockRef(node);
                                        int existingIntProp2 = node.getExistingIntProp(14);
                                        String string = firstChild.getString();
                                        visitExpression(firstChild.getNext(), 0);
                                        addStringPrefix(string);
                                        addIndexPrefix(localBlockRef3);
                                        addToken(57);
                                        addUint8(existingIntProp2 == 0 ? 0 : 1);
                                        stackChange(-1);
                                        break;
                                    case 58:
                                    case 59:
                                    case 60:
                                    case 61:
                                        visitExpression(firstChild, 0);
                                        addIndexOp(type, getLocalBlockRef(node));
                                        stackChange(-1);
                                        break;
                                    default:
                                        switch (type) {
                                            case 129:
                                            case 130:
                                            case 131:
                                            case 133:
                                                break;
                                            case 132:
                                                markTargetLabel(node);
                                                break;
                                            case 134:
                                            case 135:
                                                updateLineNumber(node);
                                                visitExpression(firstChild, 0);
                                                addIcode(type == 134 ? -4 : -5);
                                                stackChange(-1);
                                                break;
                                            case 136:
                                                addGoto(((Jump) node).target, -23);
                                                break;
                                            case 137:
                                                while (firstChild != null) {
                                                    visitStatement(firstChild, i);
                                                    firstChild = firstChild.getNext();
                                                }
                                                break;
                                            default:
                                                throw badTree(node);
                                        }
                                }
                        }
                    } else {
                        updateLineNumber(node);
                        addIndexOp(51, getLocalBlockRef(node));
                    }
                }
                updateLineNumber(node);
                while (firstChild != null) {
                }
            } else {
                updateLineNumber(node);
                visitExpression(firstChild, 0);
                for (Jump jump2 = (Jump) firstChild.getNext(); jump2 != null; jump2 = (Jump) jump2.getNext()) {
                    if (jump2.getType() != 116) {
                        throw badTree(jump2);
                    }
                    Node firstChild2 = jump2.getFirstChild();
                    addIcode(-1);
                    stackChange(1);
                    visitExpression(firstChild2, 0);
                    addToken(46);
                    stackChange(-1);
                    addGoto(jump2.target, -6);
                    stackChange(-1);
                }
                addIcode(-4);
                stackChange(-1);
            }
        }
        if (this.stackDepth != i) {
            throw Kit.codeBug();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x0036. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:18:0x003b. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:19:0x003e. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x0041. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:112:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x023c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void visitExpression(Node node, int i) {
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        int i2 = this.stackDepth;
        if (type != 90) {
            if (type == 103) {
                Node next = firstChild.getNext();
                Node next2 = next.getNext();
                visitExpression(firstChild, 0);
                int i3 = this.iCodeTop;
                addGotoOp(7);
                stackChange(-1);
                int i4 = i & 1;
                visitExpression(next, i4);
                int i5 = this.iCodeTop;
                addGotoOp(5);
                resolveForwardGoto(i3);
                this.stackDepth = i2;
                visitExpression(next2, i4);
                resolveForwardGoto(i5);
            } else if (type == 110) {
                int existingIntProp = node.getExistingIntProp(1);
                FunctionNode functionNode = this.scriptOrFn.getFunctionNode(existingIntProp);
                if (functionNode.getFunctionType() != 2 && functionNode.getFunctionType() != 4) {
                    throw Kit.codeBug();
                }
                addIndexOp(-19, existingIntProp);
                stackChange(1);
            } else {
                if (type != 127) {
                    if (type != 143) {
                        if (type == 147) {
                            updateLineNumber(node);
                            visitExpression(firstChild, 0);
                            addIcode(-53);
                            stackChange(-1);
                            int i6 = this.iCodeTop;
                            visitExpression(firstChild.getNext(), 0);
                            addBackwardGoto(-54, i6);
                        } else if (type != 160) {
                            switch (type) {
                                case 8:
                                    String string = firstChild.getString();
                                    visitExpression(firstChild, 0);
                                    visitExpression(firstChild.getNext(), 0);
                                    addStringOp(type, string);
                                    stackChange(-1);
                                    break;
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                case 18:
                                case 19:
                                case 20:
                                case 21:
                                case 22:
                                case 23:
                                case 24:
                                case 25:
                                case 36:
                                case 46:
                                case 47:
                                    visitExpression(firstChild, 0);
                                    visitExpression(firstChild.getNext(), 0);
                                    addToken(type);
                                    stackChange(-1);
                                    break;
                                case 26:
                                case 27:
                                case 28:
                                case 29:
                                case 32:
                                    break;
                                case 30:
                                case 38:
                                    if (type == 30) {
                                        visitExpression(firstChild, 0);
                                    } else {
                                        generateCallFunAndThis(firstChild);
                                    }
                                    int i7 = 0;
                                    while (true) {
                                        firstChild = firstChild.getNext();
                                        if (firstChild != null) {
                                            visitExpression(firstChild, 0);
                                            i7++;
                                        } else {
                                            int intProp = node.getIntProp(10, 0);
                                            if (type != 71 && intProp != 0) {
                                                addIndexOp(-21, i7);
                                                addUint8(intProp);
                                                addUint8(type == 30 ? 1 : 0);
                                                addUint16(this.lineNumber & 65535);
                                            } else {
                                                if (type == 38 && (i & 1) != 0 && !this.compilerEnv.isGenerateDebugInfo() && !this.itsInTryFlag) {
                                                    type = -55;
                                                }
                                                addIndexOp(type, i7);
                                            }
                                            if (type == 30) {
                                                stackChange(-i7);
                                            } else {
                                                stackChange((-1) - i7);
                                            }
                                            if (i7 > this.itsData.itsMaxCalleeArgs) {
                                                this.itsData.itsMaxCalleeArgs = i7;
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                case 31:
                                    boolean z = firstChild.getType() == 49;
                                    visitExpression(firstChild, 0);
                                    visitExpression(firstChild.getNext(), 0);
                                    if (z) {
                                        addIcode(0);
                                    } else {
                                        addToken(31);
                                    }
                                    stackChange(-1);
                                    break;
                                case 33:
                                case 34:
                                    visitExpression(firstChild, 0);
                                    addStringOp(type, firstChild.getNext().getString());
                                    break;
                                case 35:
                                    visitExpression(firstChild, 0);
                                    Node next3 = firstChild.getNext();
                                    String string2 = next3.getString();
                                    Node next4 = next3.getNext();
                                    if (type == 140) {
                                        addIcode(-1);
                                        stackChange(1);
                                        addStringOp(33, string2);
                                        stackChange(-1);
                                    }
                                    visitExpression(next4, 0);
                                    addStringOp(35, string2);
                                    stackChange(-1);
                                    break;
                                case 37:
                                    visitExpression(firstChild, 0);
                                    Node next5 = firstChild.getNext();
                                    visitExpression(next5, 0);
                                    Node next6 = next5.getNext();
                                    if (type == 141) {
                                        addIcode(-2);
                                        stackChange(2);
                                        addToken(36);
                                        stackChange(-1);
                                        stackChange(-1);
                                    }
                                    visitExpression(next6, 0);
                                    addToken(37);
                                    stackChange(-2);
                                    break;
                                case 39:
                                case 41:
                                case 49:
                                    addStringOp(type, node.getString());
                                    stackChange(1);
                                    break;
                                case 40:
                                    double d = node.getDouble();
                                    int i8 = (int) d;
                                    if (i8 != d) {
                                        addIndexOp(40, getDoubleIndex(d));
                                    } else if (i8 == 0) {
                                        addIcode(-51);
                                        if (1.0d / d < 0.0d) {
                                            addToken(29);
                                        }
                                    } else if (i8 == 1) {
                                        addIcode(-52);
                                    } else if (((short) i8) == i8) {
                                        addIcode(-27);
                                        addUint16(i8 & 65535);
                                    } else {
                                        addIcode(-28);
                                        addInt(i8);
                                    }
                                    stackChange(1);
                                    break;
                                case 42:
                                case 43:
                                case 44:
                                case 45:
                                    addToken(type);
                                    stackChange(1);
                                    break;
                                case 48:
                                    addIndexOp(48, node.getExistingIntProp(4));
                                    stackChange(1);
                                    break;
                                default:
                                    switch (type) {
                                        case 52:
                                        case 53:
                                            break;
                                        case 54:
                                            addIndexOp(54, getLocalBlockRef(node));
                                            stackChange(1);
                                            break;
                                        case 55:
                                            if (this.itsData.itsNeedsActivation) {
                                                Kit.codeBug();
                                            }
                                            addVarOp(55, this.scriptOrFn.getIndexForNameNode(node));
                                            stackChange(1);
                                            break;
                                        case 56:
                                            if (this.itsData.itsNeedsActivation) {
                                                Kit.codeBug();
                                            }
                                            int indexForNameNode = this.scriptOrFn.getIndexForNameNode(firstChild);
                                            visitExpression(firstChild.getNext(), 0);
                                            addVarOp(56, indexForNameNode);
                                            break;
                                        default:
                                            switch (type) {
                                                case 62:
                                                case 63:
                                                    addIndexOp(type, getLocalBlockRef(node));
                                                    stackChange(1);
                                                    break;
                                                case 64:
                                                    break;
                                                default:
                                                    switch (type) {
                                                        case 66:
                                                        case 67:
                                                            visitLiteral(node, firstChild);
                                                            break;
                                                        case 68:
                                                        case 70:
                                                            visitExpression(firstChild, 0);
                                                            addToken(type);
                                                            break;
                                                        case 69:
                                                            break;
                                                        case 71:
                                                            break;
                                                        case 72:
                                                            visitExpression(firstChild, 0);
                                                            addStringOp(type, (String) node.getProp(17));
                                                            break;
                                                        case 73:
                                                            if (firstChild != null) {
                                                                visitExpression(firstChild, 0);
                                                            } else {
                                                                addIcode(-50);
                                                                stackChange(1);
                                                            }
                                                            addToken(73);
                                                            addUint16(node.getLineno() & 65535);
                                                            break;
                                                        case 74:
                                                            break;
                                                        case 75:
                                                        case 76:
                                                        case 77:
                                                            visitExpression(firstChild, 0);
                                                            addToken(type);
                                                            break;
                                                        case 78:
                                                        case 79:
                                                        case 80:
                                                        case 81:
                                                            int intProp2 = node.getIntProp(16, 0);
                                                            int i9 = 0;
                                                            do {
                                                                visitExpression(firstChild, 0);
                                                                i9++;
                                                                firstChild = firstChild.getNext();
                                                            } while (firstChild != null);
                                                            addIndexOp(type, intProp2);
                                                            stackChange(1 - i9);
                                                            break;
                                                        default:
                                                            switch (type) {
                                                                case 105:
                                                                case 106:
                                                                    visitExpression(firstChild, 0);
                                                                    addIcode(-1);
                                                                    stackChange(1);
                                                                    int i10 = this.iCodeTop;
                                                                    addGotoOp(type != 106 ? 6 : 7);
                                                                    stackChange(-1);
                                                                    addIcode(-4);
                                                                    stackChange(-1);
                                                                    visitExpression(firstChild.getNext(), i & 1);
                                                                    resolveForwardGoto(i10);
                                                                    break;
                                                                case 107:
                                                                case 108:
                                                                    visitIncDec(node, firstChild);
                                                                    break;
                                                                default:
                                                                    switch (type) {
                                                                        case 138:
                                                                            int indexForNameNode2 = (!this.itsInFunctionFlag || this.itsData.itsNeedsActivation) ? -1 : this.scriptOrFn.getIndexForNameNode(node);
                                                                            if (indexForNameNode2 == -1) {
                                                                                addStringOp(-14, node.getString());
                                                                                stackChange(1);
                                                                                break;
                                                                            } else {
                                                                                addVarOp(55, indexForNameNode2);
                                                                                stackChange(1);
                                                                                addToken(32);
                                                                                break;
                                                                            }
                                                                            break;
                                                                        case 139:
                                                                            stackChange(1);
                                                                            break;
                                                                        case 140:
                                                                            break;
                                                                        case 141:
                                                                            break;
                                                                        default:
                                                                            switch (type) {
                                                                                case 156:
                                                                                    String string3 = firstChild.getString();
                                                                                    visitExpression(firstChild, 0);
                                                                                    visitExpression(firstChild.getNext(), 0);
                                                                                    addStringOp(-59, string3);
                                                                                    stackChange(-1);
                                                                                    break;
                                                                                case 157:
                                                                                    if (this.itsData.itsNeedsActivation) {
                                                                                        Kit.codeBug();
                                                                                    }
                                                                                    int indexForNameNode3 = this.scriptOrFn.getIndexForNameNode(firstChild);
                                                                                    visitExpression(firstChild.getNext(), 0);
                                                                                    addVarOp(157, indexForNameNode3);
                                                                                    break;
                                                                                case 158:
                                                                                    visitArrayComprehension(node, firstChild, firstChild.getNext());
                                                                                    break;
                                                                                default:
                                                                                    throw badTree(node);
                                                                            }
                                                                    }
                                                            }
                                                    }
                                            }
                                    }
                            }
                        } else {
                            Node firstChild2 = node.getFirstChild();
                            Node next7 = firstChild2.getNext();
                            visitExpression(firstChild2.getFirstChild(), 0);
                            addToken(2);
                            stackChange(-1);
                            visitExpression(next7.getFirstChild(), 0);
                            addToken(3);
                        }
                    }
                    visitExpression(firstChild, 0);
                    Node next8 = firstChild.getNext();
                    if (type == 143) {
                        addIcode(-1);
                        stackChange(1);
                        addToken(68);
                        stackChange(-1);
                    }
                    visitExpression(next8, 0);
                    addToken(69);
                    stackChange(-1);
                }
                visitExpression(firstChild, 0);
                if (type == 127) {
                    addIcode(-4);
                    addIcode(-50);
                } else {
                    addToken(type);
                }
            }
        } else {
            Node lastChild = node.getLastChild();
            while (firstChild != lastChild) {
                visitExpression(firstChild, 0);
                addIcode(-4);
                stackChange(-1);
                firstChild = firstChild.getNext();
            }
            visitExpression(firstChild, i & 1);
        }
        if (i2 + 1 != this.stackDepth) {
            Kit.codeBug();
        }
    }

    private void generateCallFunAndThis(Node node) {
        int type = node.getType();
        if (type != 33 && type != 36) {
            if (type == 39) {
                addStringOp(-15, node.getString());
                stackChange(2);
                return;
            } else {
                visitExpression(node, 0);
                addIcode(-18);
                stackChange(1);
                return;
            }
        }
        Node firstChild = node.getFirstChild();
        visitExpression(firstChild, 0);
        Node next = firstChild.getNext();
        if (type == 33) {
            addStringOp(-16, next.getString());
            stackChange(1);
        } else {
            visitExpression(next, 0);
            addIcode(-17);
        }
    }

    private void visitIncDec(Node node, Node node2) {
        int existingIntProp = node.getExistingIntProp(13);
        int type = node2.getType();
        if (type == 33) {
            Node firstChild = node2.getFirstChild();
            visitExpression(firstChild, 0);
            addStringOp(-9, firstChild.getNext().getString());
            addUint8(existingIntProp);
            return;
        }
        if (type == 36) {
            Node firstChild2 = node2.getFirstChild();
            visitExpression(firstChild2, 0);
            visitExpression(firstChild2.getNext(), 0);
            addIcode(-10);
            addUint8(existingIntProp);
            stackChange(-1);
            return;
        }
        if (type == 39) {
            addStringOp(-8, node2.getString());
            addUint8(existingIntProp);
            stackChange(1);
        } else {
            if (type != 55) {
                if (type == 68) {
                    visitExpression(node2.getFirstChild(), 0);
                    addIcode(-11);
                    addUint8(existingIntProp);
                    return;
                }
                throw badTree(node);
            }
            if (this.itsData.itsNeedsActivation) {
                Kit.codeBug();
            }
            addVarOp(-7, this.scriptOrFn.getIndexForNameNode(node2));
            addUint8(existingIntProp);
            stackChange(1);
        }
    }

    private void visitLiteral(Node node, Node node2) {
        Object[] objArr;
        int length;
        int type = node.getType();
        if (type == 66) {
            length = 0;
            for (Node node3 = node2; node3 != null; node3 = node3.getNext()) {
                length++;
            }
            objArr = null;
        } else if (type == 67) {
            objArr = (Object[]) node.getProp(12);
            length = objArr.length;
        } else {
            throw badTree(node);
        }
        addIndexOp(-29, length);
        stackChange(2);
        while (node2 != null) {
            int type2 = node2.getType();
            if (type2 == 152) {
                visitExpression(node2.getFirstChild(), 0);
                addIcode(-57);
            } else if (type2 == 153) {
                visitExpression(node2.getFirstChild(), 0);
                addIcode(-58);
            } else if (type2 == 164) {
                visitExpression(node2.getFirstChild(), 0);
                addIcode(-30);
            } else {
                visitExpression(node2, 0);
                addIcode(-30);
            }
            stackChange(-1);
            node2 = node2.getNext();
        }
        if (type == 66) {
            int[] iArr = (int[]) node.getProp(11);
            if (iArr == null) {
                addToken(66);
            } else {
                int size = this.literalIds.size();
                this.literalIds.add(iArr);
                addIndexOp(-31, size);
            }
        } else {
            int size2 = this.literalIds.size();
            this.literalIds.add(objArr);
            addIndexOp(67, size2);
        }
        stackChange(-1);
    }

    private void visitArrayComprehension(Node node, Node node2, Node node3) {
        visitStatement(node2, this.stackDepth);
        visitExpression(node3, 0);
    }

    private int getLocalBlockRef(Node node) {
        return ((Node) node.getProp(3)).getExistingIntProp(2);
    }

    private int getTargetLabel(Node node) {
        int labelId = node.labelId();
        if (labelId != -1) {
            return labelId;
        }
        int i = this.labelTableTop;
        int[] iArr = this.labelTable;
        if (iArr == null || i == iArr.length) {
            int[] iArr2 = this.labelTable;
            if (iArr2 == null) {
                this.labelTable = new int[32];
            } else {
                int[] iArr3 = new int[iArr2.length * 2];
                System.arraycopy(iArr2, 0, iArr3, 0, i);
                this.labelTable = iArr3;
            }
        }
        this.labelTableTop = i + 1;
        this.labelTable[i] = -1;
        node.labelId(i);
        return i;
    }

    private void markTargetLabel(Node node) {
        int targetLabel = getTargetLabel(node);
        if (this.labelTable[targetLabel] != -1) {
            Kit.codeBug();
        }
        this.labelTable[targetLabel] = this.iCodeTop;
    }

    private void addGoto(Node node, int i) {
        int targetLabel = getTargetLabel(node);
        if (targetLabel >= this.labelTableTop) {
            Kit.codeBug();
        }
        int i2 = this.labelTable[targetLabel];
        if (i2 != -1) {
            addBackwardGoto(i, i2);
            return;
        }
        int i3 = this.iCodeTop;
        addGotoOp(i);
        int i4 = this.fixupTableTop;
        long[] jArr = this.fixupTable;
        if (jArr == null || i4 == jArr.length) {
            long[] jArr2 = this.fixupTable;
            if (jArr2 == null) {
                this.fixupTable = new long[40];
            } else {
                long[] jArr3 = new long[jArr2.length * 2];
                System.arraycopy(jArr2, 0, jArr3, 0, i4);
                this.fixupTable = jArr3;
            }
        }
        this.fixupTableTop = i4 + 1;
        this.fixupTable[i4] = (targetLabel << 32) | i3;
    }

    private void fixLabelGotos() {
        for (int i = 0; i < this.fixupTableTop; i++) {
            long j = this.fixupTable[i];
            int i2 = (int) (j >> 32);
            int i3 = (int) j;
            int i4 = this.labelTable[i2];
            if (i4 == -1) {
                throw Kit.codeBug();
            }
            resolveGoto(i3, i4);
        }
        this.fixupTableTop = 0;
    }

    private void addBackwardGoto(int i, int i2) {
        int i3 = this.iCodeTop;
        if (i3 <= i2) {
            throw Kit.codeBug();
        }
        addGotoOp(i);
        resolveGoto(i3, i2);
    }

    private void resolveForwardGoto(int i) {
        int i2 = this.iCodeTop;
        if (i2 < i + 3) {
            throw Kit.codeBug();
        }
        resolveGoto(i, i2);
    }

    private void resolveGoto(int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 0 && i3 <= 2) {
            throw Kit.codeBug();
        }
        int i4 = i + 1;
        if (i3 != ((short) i3)) {
            if (this.itsData.longJumps == null) {
                this.itsData.longJumps = new UintMap();
            }
            this.itsData.longJumps.put(i4, i2);
            i3 = 0;
        }
        byte[] bArr = this.itsData.itsICode;
        bArr[i4] = (byte) (i3 >> 8);
        bArr[i4 + 1] = (byte) i3;
    }

    private void addToken(int i) {
        if (!Icode.validTokenCode(i)) {
            throw Kit.codeBug();
        }
        addUint8(i);
    }

    private void addIcode(int i) {
        if (!Icode.validIcode(i)) {
            throw Kit.codeBug();
        }
        addUint8(i & 255);
    }

    private void addUint8(int i) {
        if ((i & InputDeviceCompat.SOURCE_ANY) != 0) {
            throw Kit.codeBug();
        }
        byte[] bArr = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        if (i2 == bArr.length) {
            bArr = increaseICodeCapacity(1);
        }
        bArr[i2] = (byte) i;
        this.iCodeTop = i2 + 1;
    }

    private void addUint16(int i) {
        if (((-65536) & i) != 0) {
            throw Kit.codeBug();
        }
        byte[] bArr = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        int i3 = i2 + 2;
        if (i3 > bArr.length) {
            bArr = increaseICodeCapacity(2);
        }
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
        this.iCodeTop = i3;
    }

    private void addInt(int i) {
        byte[] bArr = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        int i3 = i2 + 4;
        if (i3 > bArr.length) {
            bArr = increaseICodeCapacity(4);
        }
        bArr[i2] = (byte) (i >>> 24);
        bArr[i2 + 1] = (byte) (i >>> 16);
        bArr[i2 + 2] = (byte) (i >>> 8);
        bArr[i2 + 3] = (byte) i;
        this.iCodeTop = i3;
    }

    private int getDoubleIndex(double d) {
        int i = this.doubleTableTop;
        if (i == 0) {
            this.itsData.itsDoubleTable = new double[64];
        } else if (this.itsData.itsDoubleTable.length == i) {
            double[] dArr = new double[i * 2];
            System.arraycopy(this.itsData.itsDoubleTable, 0, dArr, 0, i);
            this.itsData.itsDoubleTable = dArr;
        }
        this.itsData.itsDoubleTable[i] = d;
        this.doubleTableTop = i + 1;
        return i;
    }

    private void addGotoOp(int i) {
        byte[] bArr = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        if (i2 + 3 > bArr.length) {
            bArr = increaseICodeCapacity(3);
        }
        bArr[i2] = (byte) i;
        this.iCodeTop = i2 + 1 + 2;
    }

    private void addVarOp(int i, int i2) {
        if (i != -7) {
            if (i == 157) {
                if (i2 < 128) {
                    addIcode(-61);
                    addUint8(i2);
                    return;
                } else {
                    addIndexOp(-60, i2);
                    return;
                }
            }
            if (i != 55 && i != 56) {
                throw Kit.codeBug();
            }
            if (i2 < 128) {
                addIcode(i == 55 ? -48 : -49);
                addUint8(i2);
                return;
            }
        }
        addIndexOp(i, i2);
    }

    private void addStringOp(int i, String str) {
        addStringPrefix(str);
        if (Icode.validIcode(i)) {
            addIcode(i);
        } else {
            addToken(i);
        }
    }

    private void addIndexOp(int i, int i2) {
        addIndexPrefix(i2);
        if (Icode.validIcode(i)) {
            addIcode(i);
        } else {
            addToken(i);
        }
    }

    private void addStringPrefix(String str) {
        int i = this.strings.get(str, -1);
        if (i == -1) {
            i = this.strings.size();
            this.strings.put(str, i);
        }
        if (i < 4) {
            addIcode((-41) - i);
            return;
        }
        if (i <= 255) {
            addIcode(-45);
            addUint8(i);
        } else if (i <= 65535) {
            addIcode(-46);
            addUint16(i);
        } else {
            addIcode(-47);
            addInt(i);
        }
    }

    private void addIndexPrefix(int i) {
        if (i < 0) {
            Kit.codeBug();
        }
        if (i < 6) {
            addIcode((-32) - i);
            return;
        }
        if (i <= 255) {
            addIcode(-38);
            addUint8(i);
        } else if (i <= 65535) {
            addIcode(-39);
            addUint16(i);
        } else {
            addIcode(-40);
            addInt(i);
        }
    }

    private void addExceptionHandler(int i, int i2, int i3, boolean z, int i4, int i5) {
        int i6 = this.exceptionTableTop;
        int[] iArr = this.itsData.itsExceptionTable;
        if (iArr == null) {
            if (i6 != 0) {
                Kit.codeBug();
            }
            iArr = new int[12];
            this.itsData.itsExceptionTable = iArr;
        } else if (iArr.length == i6) {
            iArr = new int[iArr.length * 2];
            System.arraycopy(this.itsData.itsExceptionTable, 0, iArr, 0, i6);
            this.itsData.itsExceptionTable = iArr;
        }
        iArr[i6 + 0] = i;
        iArr[i6 + 1] = i2;
        iArr[i6 + 2] = i3;
        iArr[i6 + 3] = z ? 1 : 0;
        iArr[i6 + 4] = i4;
        iArr[i6 + 5] = i5;
        this.exceptionTableTop = i6 + 6;
    }

    private byte[] increaseICodeCapacity(int i) {
        int length = this.itsData.itsICode.length;
        int i2 = this.iCodeTop;
        int i3 = i + i2;
        if (i3 <= length) {
            throw Kit.codeBug();
        }
        int i4 = length * 2;
        if (i3 <= i4) {
            i3 = i4;
        }
        byte[] bArr = new byte[i3];
        System.arraycopy(this.itsData.itsICode, 0, bArr, 0, i2);
        this.itsData.itsICode = bArr;
        return bArr;
    }

    private void stackChange(int i) {
        if (i <= 0) {
            this.stackDepth += i;
            return;
        }
        int i2 = this.stackDepth + i;
        if (i2 > this.itsData.itsMaxStack) {
            this.itsData.itsMaxStack = i2;
        }
        this.stackDepth = i2;
    }

    private int allocLocal() {
        int i = this.localTop;
        int i2 = i + 1;
        this.localTop = i2;
        if (i2 > this.itsData.itsMaxLocals) {
            this.itsData.itsMaxLocals = this.localTop;
        }
        return i;
    }

    private void releaseLocal(int i) {
        int i2 = this.localTop - 1;
        this.localTop = i2;
        if (i != i2) {
            Kit.codeBug();
        }
    }
}
