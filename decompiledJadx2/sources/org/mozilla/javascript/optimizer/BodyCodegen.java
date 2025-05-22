package org.mozilla.javascript.optimizer;

import androidx.core.app.NotificationCompat;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.ScriptNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: Codegen.java */
/* loaded from: classes2.dex */
public class BodyCodegen {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ECMAERROR_EXCEPTION = 2;
    private static final int EVALUATOR_EXCEPTION = 1;
    private static final int EXCEPTION_MAX = 5;
    private static final int FINALLY_EXCEPTION = 4;
    static final int GENERATOR_START = 0;
    static final int GENERATOR_TERMINATE = -1;
    static final int GENERATOR_YIELD_START = 1;
    private static final int JAVASCRIPT_EXCEPTION = 0;
    private static final int MAX_LOCALS = 1024;
    private static final int THROWABLE_EXCEPTION = 3;
    private short argsLocal;
    ClassFileWriter cfw;
    Codegen codegen;
    CompilerEnvirons compilerEnv;
    private short contextLocal;
    private int enterAreaStartLabel;
    private int epilogueLabel;
    private Map<Node, FinallyReturnPoint> finallys;
    private short firstFreeLocal;
    private OptFunctionNode fnCurrent;
    private short funObjLocal;
    private short generatorStateLocal;
    private int generatorSwitch;
    private boolean hasVarsInRegs;
    private boolean inDirectCallFunction;
    private boolean inLocalBlock;
    private boolean isGenerator;
    private boolean itsForcedObjectParameters;
    private int itsLineNumber;
    private short itsOneArgArray;
    private short itsZeroArgArray;
    private List<Node> literals;
    private int[] locals;
    private short localsMax;
    private short operationLocal;
    private short popvLocal;
    private int savedCodeOffset;
    ScriptNode scriptOrFn;
    public int scriptOrFnIndex;
    private short thisObjLocal;
    private short[] varRegisters;
    private short variableObjectLocal;
    private ExceptionManager exceptionManager = new ExceptionManager();
    private int maxLocals = 0;
    private int maxStack = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void generateBodyCode() {
        Node node;
        this.isGenerator = Codegen.isGenerator(this.scriptOrFn);
        initBodyGeneration();
        if (this.isGenerator) {
            String str = "(" + this.codegen.mainClassSignature + "Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Ljava/lang/Object;I)Ljava/lang/Object;";
            this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn) + "_gen", str, (short) 10);
        } else {
            this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn), this.codegen.getBodyMethodSignature(this.scriptOrFn), (short) 10);
        }
        generatePrologue();
        if (this.fnCurrent != null) {
            node = this.scriptOrFn.getLastChild();
        } else {
            node = this.scriptOrFn;
        }
        generateStatement(node);
        generateEpilogue();
        this.cfw.stopMethod((short) (this.localsMax + 1));
        if (this.isGenerator) {
            generateGenerator();
        }
        if (this.literals != null) {
            for (int i = 0; i < this.literals.size(); i++) {
                Node node2 = this.literals.get(i);
                int type = node2.getType();
                if (type == 66) {
                    generateArrayLiteralFactory(node2, i + 1);
                } else if (type == 67) {
                    generateObjectLiteralFactory(node2, i + 1);
                } else {
                    Kit.codeBug(Token.typeToName(type));
                }
            }
        }
    }

    private void generateGenerator() {
        this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn), this.codegen.getBodyMethodSignature(this.scriptOrFn), (short) 10);
        initBodyGeneration();
        short s = this.firstFreeLocal;
        short s2 = (short) (s + 1);
        this.firstFreeLocal = s2;
        this.argsLocal = s;
        this.localsMax = s2;
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getParentScope", "()Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
        }
        this.cfw.addALoad(this.funObjLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.addPush(this.scriptOrFn.isInStrictMode());
        addScriptRuntimeInvoke("createFunctionActivation", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Z)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(187, this.codegen.mainClassName);
        this.cfw.add(89);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(this.scriptOrFnIndex);
        this.cfw.addInvoke(183, this.codegen.mainClassName, "<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V");
        generateNestedFunctionInits();
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.thisObjLocal);
        this.cfw.addLoadConstant(this.maxLocals);
        this.cfw.addLoadConstant(this.maxStack);
        addOptRuntimeInvoke("createNativeGenerator", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;II)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void generateNestedFunctionInits() {
        int functionCount = this.scriptOrFn.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn, i);
            if (optFunctionNode.fnode.getFunctionType() == 1) {
                visitFunction(optFunctionNode, 1);
            }
        }
    }

    private void initBodyGeneration() {
        int paramAndVarCount;
        this.varRegisters = null;
        if (this.scriptOrFn.getType() == 110) {
            OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn);
            this.fnCurrent = optFunctionNode;
            boolean z = !optFunctionNode.fnode.requiresActivation();
            this.hasVarsInRegs = z;
            if (z && (paramAndVarCount = this.fnCurrent.fnode.getParamAndVarCount()) != 0) {
                this.varRegisters = new short[paramAndVarCount];
            }
            boolean isTargetOfDirectCall = this.fnCurrent.isTargetOfDirectCall();
            this.inDirectCallFunction = isTargetOfDirectCall;
            if (isTargetOfDirectCall && !this.hasVarsInRegs) {
                Codegen.badTree();
            }
        } else {
            this.fnCurrent = null;
            this.hasVarsInRegs = false;
            this.inDirectCallFunction = false;
        }
        this.locals = new int[1024];
        this.funObjLocal = (short) 0;
        this.contextLocal = (short) 1;
        this.variableObjectLocal = (short) 2;
        this.thisObjLocal = (short) 3;
        this.localsMax = (short) 4;
        this.firstFreeLocal = (short) 4;
        this.popvLocal = (short) -1;
        this.argsLocal = (short) -1;
        this.itsZeroArgArray = (short) -1;
        this.itsOneArgArray = (short) -1;
        this.epilogueLabel = -1;
        this.enterAreaStartLabel = -1;
        this.generatorStateLocal = (short) -1;
    }

    private void generatePrologue() {
        String str;
        short newWordLocal;
        if (this.inDirectCallFunction) {
            int paramCount = this.scriptOrFn.getParamCount();
            if (this.firstFreeLocal != 4) {
                Kit.codeBug();
            }
            for (int i = 0; i != paramCount; i++) {
                short[] sArr = this.varRegisters;
                short s = this.firstFreeLocal;
                sArr[i] = s;
                this.firstFreeLocal = (short) (s + 3);
            }
            if (!this.fnCurrent.getParameterNumberContext()) {
                this.itsForcedObjectParameters = true;
                for (int i2 = 0; i2 != paramCount; i2++) {
                    short s2 = this.varRegisters[i2];
                    this.cfw.addALoad(s2);
                    this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                    int acquireLabel = this.cfw.acquireLabel();
                    this.cfw.add(166, acquireLabel);
                    this.cfw.addDLoad(s2 + 1);
                    addDoubleWrap();
                    this.cfw.addAStore(s2);
                    this.cfw.markLabel(acquireLabel);
                }
            }
        }
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getParentScope", "()Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
        }
        short s3 = this.firstFreeLocal;
        short s4 = (short) (s3 + 1);
        this.firstFreeLocal = s4;
        this.argsLocal = s3;
        this.localsMax = s4;
        if (this.isGenerator) {
            short s5 = (short) (s4 + 1);
            this.firstFreeLocal = s5;
            this.operationLocal = s4;
            this.localsMax = s5;
            this.cfw.addALoad(this.thisObjLocal);
            short s6 = this.firstFreeLocal;
            short s7 = (short) (s6 + 1);
            this.firstFreeLocal = s7;
            this.generatorStateLocal = s6;
            this.localsMax = s7;
            this.cfw.add(192, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState");
            this.cfw.add(89);
            this.cfw.addAStore(this.generatorStateLocal);
            this.cfw.add(180, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "thisObj", "Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.thisObjLocal);
            if (this.epilogueLabel == -1) {
                this.epilogueLabel = this.cfw.acquireLabel();
            }
            List<Node> resumptionPoints = ((FunctionNode) this.scriptOrFn).getResumptionPoints();
            if (resumptionPoints != null) {
                generateGetGeneratorResumptionPoint();
                this.generatorSwitch = this.cfw.addTableSwitch(0, resumptionPoints.size() + 0);
                generateCheckForThrowOrClose(-1, false, 0);
            }
        }
        if (this.fnCurrent == null && this.scriptOrFn.getRegexpCount() != 0) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addInvoke(184, this.codegen.mainClassName, "_reInit", "(Lorg/mozilla/javascript/Context;)V");
        }
        if (this.compilerEnv.isGenerateObserverCount()) {
            saveCurrentCodeOffset();
        }
        if (this.hasVarsInRegs) {
            int paramCount2 = this.scriptOrFn.getParamCount();
            if (paramCount2 > 0 && !this.inDirectCallFunction) {
                this.cfw.addALoad(this.argsLocal);
                this.cfw.add(190);
                this.cfw.addPush(paramCount2);
                int acquireLabel2 = this.cfw.acquireLabel();
                this.cfw.add(162, acquireLabel2);
                this.cfw.addALoad(this.argsLocal);
                this.cfw.addPush(paramCount2);
                addScriptRuntimeInvoke("padArguments", "([Ljava/lang/Object;I)[Ljava/lang/Object;");
                this.cfw.addAStore(this.argsLocal);
                this.cfw.markLabel(acquireLabel2);
            }
            int paramCount3 = this.fnCurrent.fnode.getParamCount();
            int paramAndVarCount = this.fnCurrent.fnode.getParamAndVarCount();
            boolean[] paramAndVarConst = this.fnCurrent.fnode.getParamAndVarConst();
            short s8 = -1;
            for (int i3 = 0; i3 != paramAndVarCount; i3++) {
                if (i3 < paramCount3) {
                    if (this.inDirectCallFunction) {
                        newWordLocal = -1;
                    } else {
                        newWordLocal = getNewWordLocal();
                        this.cfw.addALoad(this.argsLocal);
                        this.cfw.addPush(i3);
                        this.cfw.add(50);
                        this.cfw.addAStore(newWordLocal);
                    }
                } else if (this.fnCurrent.isNumberVar(i3)) {
                    newWordLocal = getNewWordPairLocal(paramAndVarConst[i3]);
                    this.cfw.addPush(0.0d);
                    this.cfw.addDStore(newWordLocal);
                } else {
                    newWordLocal = getNewWordLocal(paramAndVarConst[i3]);
                    if (s8 == -1) {
                        Codegen.pushUndefined(this.cfw);
                        s8 = newWordLocal;
                    } else {
                        this.cfw.addALoad(s8);
                    }
                    this.cfw.addAStore(newWordLocal);
                }
                if (newWordLocal >= 0) {
                    if (paramAndVarConst[i3]) {
                        this.cfw.addPush(0);
                        this.cfw.addIStore((this.fnCurrent.isNumberVar(i3) ? (short) 2 : (short) 1) + newWordLocal);
                    }
                    this.varRegisters[i3] = newWordLocal;
                }
                if (this.compilerEnv.isGenerateDebugInfo()) {
                    String paramOrVarName = this.fnCurrent.fnode.getParamOrVarName(i3);
                    String str2 = this.fnCurrent.isNumberVar(i3) ? "D" : "Ljava/lang/Object;";
                    int currentCodeOffset = this.cfw.getCurrentCodeOffset();
                    if (newWordLocal < 0) {
                        newWordLocal = this.varRegisters[i3];
                    }
                    this.cfw.addVariableDescriptor(paramOrVarName, str2, currentCodeOffset, newWordLocal);
                }
            }
            return;
        }
        if (this.isGenerator) {
            return;
        }
        ScriptNode scriptNode = this.scriptOrFn;
        boolean z = (scriptNode instanceof FunctionNode) && ((FunctionNode) scriptNode).getFunctionType() == 4;
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.argsLocal);
            String str3 = z ? "createArrowFunctionActivation" : "createFunctionActivation";
            this.cfw.addPush(this.scriptOrFn.isInStrictMode());
            addScriptRuntimeInvoke(str3, "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Z)Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("enterActivationFunction", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V");
            str = "activation";
        } else {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(0);
            addScriptRuntimeInvoke("initScript", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Z)V");
            str = "global";
        }
        this.enterAreaStartLabel = this.cfw.acquireLabel();
        this.epilogueLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(this.enterAreaStartLabel);
        generateNestedFunctionInits();
        if (this.compilerEnv.isGenerateDebugInfo()) {
            ClassFileWriter classFileWriter = this.cfw;
            classFileWriter.addVariableDescriptor(str, "Lorg/mozilla/javascript/Scriptable;", classFileWriter.getCurrentCodeOffset(), this.variableObjectLocal);
        }
        OptFunctionNode optFunctionNode = this.fnCurrent;
        if (optFunctionNode == null) {
            this.popvLocal = getNewWordLocal();
            Codegen.pushUndefined(this.cfw);
            this.cfw.addAStore(this.popvLocal);
            int endLineno = this.scriptOrFn.getEndLineno();
            if (endLineno != -1) {
                this.cfw.addLineNumberEntry((short) endLineno);
                return;
            }
            return;
        }
        if (optFunctionNode.itsContainsCalls0) {
            this.itsZeroArgArray = getNewWordLocal();
            this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
            this.cfw.addAStore(this.itsZeroArgArray);
        }
        if (this.fnCurrent.itsContainsCalls1) {
            this.itsOneArgArray = getNewWordLocal();
            this.cfw.addPush(1);
            this.cfw.add(189, "java/lang/Object");
            this.cfw.addAStore(this.itsOneArgArray);
        }
    }

    private void generateGetGeneratorResumptionPoint() {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.add(180, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "resumptionPoint", "I");
    }

    private void generateSetGeneratorResumptionPoint(int i) {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.addLoadConstant(i);
        this.cfw.add(181, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "resumptionPoint", "I");
    }

    private void generateGetGeneratorStackState() {
        this.cfw.addALoad(this.generatorStateLocal);
        addOptRuntimeInvoke("getGeneratorStackState", "(Ljava/lang/Object;)[Ljava/lang/Object;");
    }

    private void generateEpilogue() {
        if (this.compilerEnv.isGenerateObserverCount()) {
            addInstructionCount();
        }
        if (this.isGenerator) {
            Map<Node, int[]> liveLocals = ((FunctionNode) this.scriptOrFn).getLiveLocals();
            if (liveLocals != null) {
                List<Node> resumptionPoints = ((FunctionNode) this.scriptOrFn).getResumptionPoints();
                for (int i = 0; i < resumptionPoints.size(); i++) {
                    Node node = resumptionPoints.get(i);
                    int[] iArr = liveLocals.get(node);
                    if (iArr != null) {
                        this.cfw.markTableSwitchCase(this.generatorSwitch, getNextGeneratorState(node));
                        generateGetGeneratorLocalsState();
                        for (int i2 = 0; i2 < iArr.length; i2++) {
                            this.cfw.add(89);
                            this.cfw.addLoadConstant(i2);
                            this.cfw.add(50);
                            this.cfw.addAStore(iArr[i2]);
                        }
                        this.cfw.add(87);
                        this.cfw.add(167, getTargetLabel(node));
                    }
                }
            }
            Map<Node, FinallyReturnPoint> map = this.finallys;
            if (map != null) {
                for (Node node2 : map.keySet()) {
                    if (node2.getType() == 126) {
                        FinallyReturnPoint finallyReturnPoint = this.finallys.get(node2);
                        this.cfw.markLabel(finallyReturnPoint.tableLabel, (short) 1);
                        int addTableSwitch = this.cfw.addTableSwitch(0, finallyReturnPoint.jsrPoints.size() - 1);
                        this.cfw.markTableSwitchDefault(addTableSwitch);
                        int i3 = 0;
                        for (int i4 = 0; i4 < finallyReturnPoint.jsrPoints.size(); i4++) {
                            this.cfw.markTableSwitchCase(addTableSwitch, i3);
                            this.cfw.add(167, finallyReturnPoint.jsrPoints.get(i4).intValue());
                            i3++;
                        }
                    }
                }
            }
        }
        int i5 = this.epilogueLabel;
        if (i5 != -1) {
            this.cfw.markLabel(i5);
        }
        if (this.hasVarsInRegs) {
            this.cfw.add(176);
            return;
        }
        if (this.isGenerator) {
            if (((FunctionNode) this.scriptOrFn).getResumptionPoints() != null) {
                this.cfw.markTableSwitchDefault(this.generatorSwitch);
            }
            generateSetGeneratorResumptionPoint(-1);
            this.cfw.addALoad(this.variableObjectLocal);
            addOptRuntimeInvoke("throwStopIteration", "(Ljava/lang/Object;)V");
            Codegen.pushUndefined(this.cfw);
            this.cfw.add(176);
            return;
        }
        if (this.fnCurrent == null) {
            this.cfw.addALoad(this.popvLocal);
            this.cfw.add(176);
            return;
        }
        generateActivationExit();
        this.cfw.add(176);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.markHandler(acquireLabel);
        short newWordLocal = getNewWordLocal();
        this.cfw.addAStore(newWordLocal);
        generateActivationExit();
        this.cfw.addALoad(newWordLocal);
        releaseWordLocal(newWordLocal);
        this.cfw.add(191);
        this.cfw.addExceptionHandler(this.enterAreaStartLabel, this.epilogueLabel, acquireLabel, null);
    }

    private void generateGetGeneratorLocalsState() {
        this.cfw.addALoad(this.generatorStateLocal);
        addOptRuntimeInvoke("getGeneratorLocalsState", "(Ljava/lang/Object;)[Ljava/lang/Object;");
    }

    private void generateActivationExit() {
        if (this.fnCurrent == null || this.hasVarsInRegs) {
            throw Kit.codeBug();
        }
        this.cfw.addALoad(this.contextLocal);
        addScriptRuntimeInvoke("exitActivationFunction", "(Lorg/mozilla/javascript/Context;)V");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0038. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x003c. Please report as an issue. */
    private void generateStatement(Node node) {
        updateLineNumber(node);
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        if (type == 50) {
            generateExpression(firstChild, node);
            if (this.compilerEnv.isGenerateObserverCount()) {
                addInstructionCount();
            }
            generateThrowJavaScriptException();
            return;
        }
        if (type == 51) {
            if (this.compilerEnv.isGenerateObserverCount()) {
                addInstructionCount();
            }
            this.cfw.addALoad(getLocalBlockRegister(node));
            this.cfw.add(191);
            return;
        }
        if (type != 65) {
            if (type == 82) {
                visitTryCatchFinally((Jump) node, firstChild);
                return;
            }
            int i = 1;
            if (type == 110) {
                OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn, node.getExistingIntProp(1));
                int functionType = optFunctionNode.fnode.getFunctionType();
                if (functionType == 3) {
                    visitFunction(optFunctionNode, functionType);
                    return;
                } else {
                    if (functionType != 1) {
                        throw Codegen.badTree();
                    }
                    return;
                }
            }
            if (type != 115) {
                if (type != 124) {
                    if (type == 126) {
                        if (this.isGenerator) {
                            if (this.compilerEnv.isGenerateObserverCount()) {
                                saveCurrentCodeOffset();
                            }
                            this.cfw.setStackTop((short) 1);
                            short newWordLocal = getNewWordLocal();
                            int acquireLabel = this.cfw.acquireLabel();
                            int acquireLabel2 = this.cfw.acquireLabel();
                            this.cfw.markLabel(acquireLabel);
                            generateIntegerWrap();
                            this.cfw.addAStore(newWordLocal);
                            while (firstChild != null) {
                                generateStatement(firstChild);
                                firstChild = firstChild.getNext();
                            }
                            this.cfw.addALoad(newWordLocal);
                            this.cfw.add(192, "java/lang/Integer");
                            generateIntegerUnwrap();
                            FinallyReturnPoint finallyReturnPoint = this.finallys.get(node);
                            finallyReturnPoint.tableLabel = this.cfw.acquireLabel();
                            this.cfw.add(167, finallyReturnPoint.tableLabel);
                            releaseWordLocal(newWordLocal);
                            this.cfw.markLabel(acquireLabel2);
                            return;
                        }
                        return;
                    }
                    if (type == 142) {
                        boolean z = this.inLocalBlock;
                        this.inLocalBlock = true;
                        short newWordLocal2 = getNewWordLocal();
                        if (this.isGenerator) {
                            this.cfw.add(1);
                            this.cfw.addAStore(newWordLocal2);
                        }
                        node.putIntProp(2, newWordLocal2);
                        while (firstChild != null) {
                            generateStatement(firstChild);
                            firstChild = firstChild.getNext();
                        }
                        releaseWordLocal(newWordLocal2);
                        node.removeProp(2);
                        this.inLocalBlock = z;
                        return;
                    }
                    if (type != 161) {
                        switch (type) {
                            case 2:
                                generateExpression(firstChild, node);
                                this.cfw.addALoad(this.contextLocal);
                                this.cfw.addALoad(this.variableObjectLocal);
                                addScriptRuntimeInvoke("enterWith", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                                this.cfw.addAStore(this.variableObjectLocal);
                                incReferenceWordLocal(this.variableObjectLocal);
                                return;
                            case 3:
                                this.cfw.addALoad(this.variableObjectLocal);
                                addScriptRuntimeInvoke("leaveWith", "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                                this.cfw.addAStore(this.variableObjectLocal);
                                decReferenceWordLocal(this.variableObjectLocal);
                                return;
                            case 4:
                                break;
                            default:
                                switch (type) {
                                    case 57:
                                        this.cfw.setStackTop((short) 0);
                                        int localBlockRegister = getLocalBlockRegister(node);
                                        int existingIntProp = node.getExistingIntProp(14);
                                        String string = firstChild.getString();
                                        generateExpression(firstChild.getNext(), node);
                                        if (existingIntProp == 0) {
                                            this.cfw.add(1);
                                        } else {
                                            this.cfw.addALoad(localBlockRegister);
                                        }
                                        this.cfw.addPush(string);
                                        this.cfw.addALoad(this.contextLocal);
                                        this.cfw.addALoad(this.variableObjectLocal);
                                        addScriptRuntimeInvoke("newCatchScope", "(Ljava/lang/Throwable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                                        this.cfw.addAStore(localBlockRegister);
                                        return;
                                    case 58:
                                    case 59:
                                    case 60:
                                    case 61:
                                        generateExpression(firstChild, node);
                                        this.cfw.addALoad(this.contextLocal);
                                        this.cfw.addALoad(this.variableObjectLocal);
                                        if (type == 58) {
                                            i = 0;
                                        } else if (type != 59) {
                                            i = type == 61 ? 6 : 2;
                                        }
                                        this.cfw.addPush(i);
                                        addScriptRuntimeInvoke("enumInit", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                                        this.cfw.addAStore(getLocalBlockRegister(node));
                                        return;
                                    default:
                                        switch (type) {
                                            case 129:
                                            case 130:
                                            case 131:
                                            case 133:
                                            case 137:
                                                break;
                                            case 132:
                                                if (this.compilerEnv.isGenerateObserverCount()) {
                                                    addInstructionCount();
                                                }
                                                this.cfw.markLabel(getTargetLabel(node));
                                                if (this.compilerEnv.isGenerateObserverCount()) {
                                                    saveCurrentCodeOffset();
                                                    return;
                                                }
                                                return;
                                            case 134:
                                                if (firstChild.getType() == 56) {
                                                    visitSetVar(firstChild, firstChild.getFirstChild(), false);
                                                    return;
                                                }
                                                if (firstChild.getType() == 157) {
                                                    visitSetConstVar(firstChild, firstChild.getFirstChild(), false);
                                                    return;
                                                }
                                                if (firstChild.getType() == 73) {
                                                    generateYieldPoint(firstChild, false);
                                                    return;
                                                }
                                                generateExpression(firstChild, node);
                                                if (node.getIntProp(8, -1) != -1) {
                                                    this.cfw.add(88);
                                                    return;
                                                } else {
                                                    this.cfw.add(87);
                                                    return;
                                                }
                                            case 135:
                                                generateExpression(firstChild, node);
                                                if (this.popvLocal < 0) {
                                                    this.popvLocal = getNewWordLocal();
                                                }
                                                this.cfw.addAStore(this.popvLocal);
                                                return;
                                            case 136:
                                                break;
                                            default:
                                                throw Codegen.badTree();
                                        }
                                }
                            case 5:
                            case 6:
                            case 7:
                                if (this.compilerEnv.isGenerateObserverCount()) {
                                    addInstructionCount();
                                }
                                visitGoto((Jump) node, type, firstChild);
                                return;
                        }
                    } else {
                        return;
                    }
                }
                if (this.compilerEnv.isGenerateObserverCount()) {
                    addInstructionCount(1);
                }
                while (firstChild != null) {
                    generateStatement(firstChild);
                    firstChild = firstChild.getNext();
                }
                return;
            }
            if (this.compilerEnv.isGenerateObserverCount()) {
                addInstructionCount();
            }
            visitSwitch((Jump) node, firstChild);
            return;
        }
        if (!this.isGenerator) {
            if (firstChild != null) {
                generateExpression(firstChild, node);
            } else if (type == 4) {
                Codegen.pushUndefined(this.cfw);
            } else {
                short s = this.popvLocal;
                if (s < 0) {
                    throw Codegen.badTree();
                }
                this.cfw.addALoad(s);
            }
        }
        if (this.compilerEnv.isGenerateObserverCount()) {
            addInstructionCount();
        }
        if (this.epilogueLabel == -1) {
            if (!this.hasVarsInRegs) {
                throw Codegen.badTree();
            }
            this.epilogueLabel = this.cfw.acquireLabel();
        }
        this.cfw.add(167, this.epilogueLabel);
    }

    private void generateIntegerWrap() {
        this.cfw.addInvoke(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
    }

    private void generateIntegerUnwrap() {
        this.cfw.addInvoke(182, "java/lang/Integer", "intValue", "()I");
    }

    private void generateThrowJavaScriptException() {
        this.cfw.add(187, "org/mozilla/javascript/JavaScriptException");
        this.cfw.add(90);
        this.cfw.add(95);
        this.cfw.addPush(this.scriptOrFn.getSourceName());
        this.cfw.addPush(this.itsLineNumber);
        this.cfw.addInvoke(183, "org/mozilla/javascript/JavaScriptException", "<init>", "(Ljava/lang/Object;Ljava/lang/String;I)V");
        this.cfw.add(191);
    }

    private int getNextGeneratorState(Node node) {
        return ((FunctionNode) this.scriptOrFn).getResumptionPoints().indexOf(node) + 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x0046. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0049. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x004e. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0053. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:26:0x0056. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0463  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void generateExpression(Node node, Node node2) {
        int i;
        int i2;
        int i3;
        String str;
        String str2;
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        if (type != 90) {
            if (type == 103) {
                Node next = firstChild.getNext();
                Node next2 = next.getNext();
                generateExpression(firstChild, node);
                addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
                int acquireLabel = this.cfw.acquireLabel();
                this.cfw.add(153, acquireLabel);
                short stackTop = this.cfw.getStackTop();
                generateExpression(next, node);
                int acquireLabel2 = this.cfw.acquireLabel();
                this.cfw.add(167, acquireLabel2);
                this.cfw.markLabel(acquireLabel, stackTop);
                generateExpression(next2, node);
                this.cfw.markLabel(acquireLabel2);
                return;
            }
            if (type == 110) {
                if (this.fnCurrent == null && node2.getType() == 137) {
                    return;
                }
                OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn, node.getExistingIntProp(1));
                int functionType = optFunctionNode.fnode.getFunctionType();
                if (functionType != 2 && functionType != 4) {
                    throw Codegen.badTree();
                }
                visitFunction(optFunctionNode, functionType);
                return;
            }
            if (type == 127) {
                generateExpression(firstChild, node);
                this.cfw.add(87);
                Codegen.pushUndefined(this.cfw);
                return;
            }
            if (type != 143) {
                if (type == 147) {
                    visitDotQuery(node, firstChild);
                    return;
                }
                if (type == 160) {
                    Node next3 = firstChild.getNext();
                    Node next4 = next3.getNext();
                    generateStatement(firstChild);
                    generateExpression(next3.getFirstChild(), next3);
                    generateStatement(next4);
                    return;
                }
                if (type == 150) {
                    if (firstChild.getType() == 40) {
                        i = 8;
                        i2 = -1;
                        i3 = firstChild.getIntProp(8, -1);
                    } else {
                        i = 8;
                        i2 = -1;
                        i3 = -1;
                    }
                    if (i3 != i2) {
                        firstChild.removeProp(i);
                        generateExpression(firstChild, node);
                        firstChild.putIntProp(i, i3);
                        return;
                    } else {
                        generateExpression(firstChild, node);
                        addDoubleWrap();
                        return;
                    }
                }
                if (type == 151) {
                    generateExpression(firstChild, node);
                    addObjectToDouble();
                    return;
                }
                switch (type) {
                    case 8:
                        visitSetName(node, firstChild);
                        return;
                    case 9:
                    case 10:
                    case 11:
                    case 18:
                    case 19:
                    case 20:
                        visitBitOp(node, type, firstChild);
                        return;
                    case 12:
                    case 13:
                    case 46:
                    case 47:
                        int acquireLabel3 = this.cfw.acquireLabel();
                        int acquireLabel4 = this.cfw.acquireLabel();
                        visitIfJumpEqOp(node, firstChild, acquireLabel3, acquireLabel4);
                        addJumpedBooleanWrap(acquireLabel3, acquireLabel4);
                        return;
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        int acquireLabel5 = this.cfw.acquireLabel();
                        int acquireLabel6 = this.cfw.acquireLabel();
                        visitIfJumpRelOp(node, firstChild, acquireLabel5, acquireLabel6);
                        addJumpedBooleanWrap(acquireLabel5, acquireLabel6);
                        return;
                    case 21:
                        generateExpression(firstChild, node);
                        generateExpression(firstChild.getNext(), node);
                        int intProp = node.getIntProp(8, -1);
                        if (intProp == 0) {
                            this.cfw.add(99);
                            return;
                        }
                        if (intProp == 1) {
                            addOptRuntimeInvoke(TmpConstant.GROUP_OP_ADD, "(DLjava/lang/Object;)Ljava/lang/Object;");
                            return;
                        }
                        if (intProp == 2) {
                            addOptRuntimeInvoke(TmpConstant.GROUP_OP_ADD, "(Ljava/lang/Object;D)Ljava/lang/Object;");
                            return;
                        }
                        if (firstChild.getType() == 41) {
                            addScriptRuntimeInvoke(TmpConstant.GROUP_OP_ADD, "(Ljava/lang/CharSequence;Ljava/lang/Object;)Ljava/lang/CharSequence;");
                            return;
                        } else if (firstChild.getNext().getType() == 41) {
                            addScriptRuntimeInvoke(TmpConstant.GROUP_OP_ADD, "(Ljava/lang/Object;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;");
                            return;
                        } else {
                            this.cfw.addALoad(this.contextLocal);
                            addScriptRuntimeInvoke(TmpConstant.GROUP_OP_ADD, "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                            return;
                        }
                    case 22:
                        visitArithmetic(node, 103, firstChild, node2);
                        return;
                    case 23:
                        visitArithmetic(node, 107, firstChild, node2);
                        return;
                    case 24:
                    case 25:
                        visitArithmetic(node, type == 24 ? 111 : 115, firstChild, node2);
                        return;
                    case 26:
                        int acquireLabel7 = this.cfw.acquireLabel();
                        int acquireLabel8 = this.cfw.acquireLabel();
                        int acquireLabel9 = this.cfw.acquireLabel();
                        generateIfJump(firstChild, node, acquireLabel7, acquireLabel8);
                        this.cfw.markLabel(acquireLabel7);
                        this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
                        this.cfw.add(167, acquireLabel9);
                        this.cfw.markLabel(acquireLabel8);
                        this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
                        this.cfw.markLabel(acquireLabel9);
                        this.cfw.adjustStackTop(-1);
                        return;
                    case 27:
                        generateExpression(firstChild, node);
                        addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
                        this.cfw.addPush(-1);
                        this.cfw.add(130);
                        this.cfw.add(135);
                        addDoubleWrap();
                        return;
                    case 28:
                    case 29:
                        generateExpression(firstChild, node);
                        addObjectToDouble();
                        if (type == 29) {
                            this.cfw.add(119);
                        }
                        addDoubleWrap();
                        return;
                    case 30:
                    case 38:
                        int intProp2 = node.getIntProp(10, 0);
                        if (intProp2 == 0) {
                            OptFunctionNode optFunctionNode2 = (OptFunctionNode) node.getProp(9);
                            if (optFunctionNode2 != null) {
                                visitOptimizedCall(node, optFunctionNode2, type, firstChild);
                                return;
                            } else if (type == 38) {
                                visitStandardCall(node, firstChild);
                                return;
                            } else {
                                visitStandardNew(node, firstChild);
                                return;
                            }
                        }
                        visitSpecialCall(node, type, intProp2, firstChild);
                        return;
                    case 31:
                        boolean z = firstChild.getType() == 49;
                        generateExpression(firstChild, node);
                        generateExpression(firstChild.getNext(), node);
                        this.cfw.addALoad(this.contextLocal);
                        this.cfw.addPush(z);
                        addScriptRuntimeInvoke(RequestParameters.SUBRESOURCE_DELETE, "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Z)Ljava/lang/Object;");
                        return;
                    case 32:
                        generateExpression(firstChild, node);
                        addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
                        return;
                    case 33:
                    case 34:
                        visitGetProp(node, firstChild);
                        return;
                    case 35:
                        visitSetProp(type, node, firstChild);
                        return;
                    case 36:
                        generateExpression(firstChild, node);
                        generateExpression(firstChild.getNext(), node);
                        this.cfw.addALoad(this.contextLocal);
                        if (node.getIntProp(8, -1) != -1) {
                            addScriptRuntimeInvoke("getObjectIndex", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                            return;
                        } else {
                            this.cfw.addALoad(this.variableObjectLocal);
                            addScriptRuntimeInvoke("getObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
                            return;
                        }
                    case 37:
                        visitSetElem(type, node, firstChild);
                        return;
                    case 39:
                        this.cfw.addALoad(this.contextLocal);
                        this.cfw.addALoad(this.variableObjectLocal);
                        this.cfw.addPush(node.getString());
                        addScriptRuntimeInvoke("name", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
                        return;
                    case 40:
                        double d = node.getDouble();
                        if (node.getIntProp(8, -1) != -1) {
                            this.cfw.addPush(d);
                            return;
                        } else {
                            this.codegen.pushNumberAsObject(this.cfw, d);
                            return;
                        }
                    case 41:
                        this.cfw.addPush(node.getString());
                        return;
                    case 42:
                        this.cfw.add(1);
                        return;
                    case 43:
                        this.cfw.addALoad(this.thisObjLocal);
                        return;
                    case 44:
                        this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
                        return;
                    case 45:
                        this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
                        return;
                    case 48:
                        this.cfw.addALoad(this.contextLocal);
                        this.cfw.addALoad(this.variableObjectLocal);
                        this.cfw.add(178, this.codegen.mainClassName, this.codegen.getCompiledRegexpName(this.scriptOrFn, node.getExistingIntProp(4)), "Ljava/lang/Object;");
                        this.cfw.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "wrapRegExp", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
                        return;
                    case 49:
                        while (firstChild != null) {
                            generateExpression(firstChild, node);
                            firstChild = firstChild.getNext();
                        }
                        this.cfw.addALoad(this.contextLocal);
                        this.cfw.addALoad(this.variableObjectLocal);
                        this.cfw.addPush(node.getString());
                        addScriptRuntimeInvoke("bind", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Lorg/mozilla/javascript/Scriptable;");
                        return;
                    default:
                        switch (type) {
                            case 52:
                            case 53:
                                break;
                            case 54:
                                this.cfw.addALoad(getLocalBlockRegister(node));
                                return;
                            case 55:
                                visitGetVar(node);
                                return;
                            case 56:
                                visitSetVar(node, firstChild, true);
                                return;
                            default:
                                switch (type) {
                                    case 62:
                                    case 63:
                                        this.cfw.addALoad(getLocalBlockRegister(node));
                                        if (type == 62) {
                                            addScriptRuntimeInvoke("enumNext", "(Ljava/lang/Object;)Ljava/lang/Boolean;");
                                            return;
                                        } else {
                                            this.cfw.addALoad(this.contextLocal);
                                            addScriptRuntimeInvoke("enumId", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                                            return;
                                        }
                                    case 64:
                                        this.cfw.add(42);
                                        return;
                                    default:
                                        switch (type) {
                                            case 66:
                                                visitArrayLiteral(node, firstChild, false);
                                                return;
                                            case 67:
                                                visitObjectLiteral(node, firstChild, false);
                                                return;
                                            case 68:
                                                generateExpression(firstChild, node);
                                                this.cfw.addALoad(this.contextLocal);
                                                addScriptRuntimeInvoke("refGet", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                                                return;
                                            case 69:
                                                break;
                                            case 70:
                                                generateExpression(firstChild, node);
                                                this.cfw.addALoad(this.contextLocal);
                                                addScriptRuntimeInvoke("refDel", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                                                return;
                                            case 71:
                                                generateFunctionAndThisObj(firstChild, node);
                                                generateCallArgArray(node, firstChild.getNext(), false);
                                                this.cfw.addALoad(this.contextLocal);
                                                addScriptRuntimeInvoke("callRef", "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Ref;");
                                                return;
                                            case 72:
                                                String str3 = (String) node.getProp(17);
                                                generateExpression(firstChild, node);
                                                this.cfw.addPush(str3);
                                                this.cfw.addALoad(this.contextLocal);
                                                this.cfw.addALoad(this.variableObjectLocal);
                                                addScriptRuntimeInvoke("specialRef", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Ref;");
                                                return;
                                            case 73:
                                                generateYieldPoint(node, true);
                                                return;
                                            case 74:
                                                visitStrictSetName(node, firstChild);
                                                return;
                                            case 75:
                                                generateExpression(firstChild, node);
                                                this.cfw.addALoad(this.contextLocal);
                                                addScriptRuntimeInvoke("setDefaultNamespace", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                                                return;
                                            case 76:
                                                generateExpression(firstChild, node);
                                                this.cfw.addALoad(this.contextLocal);
                                                addScriptRuntimeInvoke("escapeAttributeValue", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;");
                                                return;
                                            case 77:
                                                generateExpression(firstChild, node);
                                                this.cfw.addALoad(this.contextLocal);
                                                addScriptRuntimeInvoke("escapeTextValue", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;");
                                                return;
                                            case 78:
                                            case 79:
                                            case 80:
                                            case 81:
                                                int intProp3 = node.getIntProp(16, 0);
                                                do {
                                                    generateExpression(firstChild, node);
                                                    firstChild = firstChild.getNext();
                                                } while (firstChild != null);
                                                this.cfw.addALoad(this.contextLocal);
                                                switch (type) {
                                                    case 78:
                                                        str = "memberRef";
                                                        str2 = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Lorg/mozilla/javascript/Ref;";
                                                        break;
                                                    case 79:
                                                        str = "memberRef";
                                                        str2 = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Lorg/mozilla/javascript/Ref;";
                                                        break;
                                                    case 80:
                                                        this.cfw.addALoad(this.variableObjectLocal);
                                                        str = "nameRef";
                                                        str2 = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;";
                                                        break;
                                                    case 81:
                                                        this.cfw.addALoad(this.variableObjectLocal);
                                                        str = "nameRef";
                                                        str2 = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;";
                                                        break;
                                                    default:
                                                        throw Kit.codeBug();
                                                }
                                                this.cfw.addPush(intProp3);
                                                addScriptRuntimeInvoke(str, str2);
                                                return;
                                            default:
                                                switch (type) {
                                                    case 105:
                                                    case 106:
                                                        generateExpression(firstChild, node);
                                                        this.cfw.add(89);
                                                        addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
                                                        int acquireLabel10 = this.cfw.acquireLabel();
                                                        if (type == 106) {
                                                            this.cfw.add(153, acquireLabel10);
                                                        } else {
                                                            this.cfw.add(154, acquireLabel10);
                                                        }
                                                        this.cfw.add(87);
                                                        generateExpression(firstChild.getNext(), node);
                                                        this.cfw.markLabel(acquireLabel10);
                                                        return;
                                                    case 107:
                                                    case 108:
                                                        visitIncDec(node);
                                                        return;
                                                    default:
                                                        switch (type) {
                                                            case 138:
                                                                visitTypeofname(node);
                                                                return;
                                                            case 139:
                                                                return;
                                                            case 140:
                                                                break;
                                                            case 141:
                                                                break;
                                                            default:
                                                                switch (type) {
                                                                    case 156:
                                                                        visitSetConst(node, firstChild);
                                                                        return;
                                                                    case 157:
                                                                        visitSetConstVar(node, firstChild, true);
                                                                        return;
                                                                    case 158:
                                                                        Node next5 = firstChild.getNext();
                                                                        generateStatement(firstChild);
                                                                        generateExpression(next5, node);
                                                                        return;
                                                                    default:
                                                                        throw new RuntimeException("Unexpected node type " + type);
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
            }
            generateExpression(firstChild, node);
            Node next6 = firstChild.getNext();
            if (type == 143) {
                this.cfw.add(89);
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("refGet", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
            }
            generateExpression(next6, node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("refSet", "(Lorg/mozilla/javascript/Ref;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            return;
        }
        Node next7 = firstChild.getNext();
        while (true) {
            Node node3 = firstChild;
            firstChild = next7;
            if (firstChild != null) {
                generateExpression(node3, node);
                this.cfw.add(87);
                next7 = firstChild.getNext();
            } else {
                generateExpression(node3, node);
                return;
            }
        }
    }

    private void generateYieldPoint(Node node, boolean z) {
        short stackTop = this.cfw.getStackTop();
        int i = this.maxStack;
        if (i <= stackTop) {
            i = stackTop;
        }
        this.maxStack = i;
        if (this.cfw.getStackTop() != 0) {
            generateGetGeneratorStackState();
            for (int i2 = 0; i2 < stackTop; i2++) {
                this.cfw.add(90);
                this.cfw.add(95);
                this.cfw.addLoadConstant(i2);
                this.cfw.add(95);
                this.cfw.add(83);
            }
            this.cfw.add(87);
        }
        Node firstChild = node.getFirstChild();
        if (firstChild != null) {
            generateExpression(firstChild, node);
        } else {
            Codegen.pushUndefined(this.cfw);
        }
        int nextGeneratorState = getNextGeneratorState(node);
        generateSetGeneratorResumptionPoint(nextGeneratorState);
        boolean generateSaveLocals = generateSaveLocals(node);
        this.cfw.add(176);
        generateCheckForThrowOrClose(getTargetLabel(node), generateSaveLocals, nextGeneratorState);
        if (stackTop != 0) {
            generateGetGeneratorStackState();
            for (int i3 = 0; i3 < stackTop; i3++) {
                this.cfw.add(89);
                this.cfw.addLoadConstant((stackTop - i3) - 1);
                this.cfw.add(50);
                this.cfw.add(95);
            }
            this.cfw.add(87);
        }
        if (z) {
            this.cfw.addALoad(this.argsLocal);
        }
    }

    private void generateCheckForThrowOrClose(int i, boolean z, int i2) {
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        this.cfw.addALoad(this.argsLocal);
        generateThrowJavaScriptException();
        this.cfw.markLabel(acquireLabel2);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.add(192, "java/lang/Throwable");
        this.cfw.add(191);
        if (i != -1) {
            this.cfw.markLabel(i);
        }
        if (!z) {
            this.cfw.markTableSwitchCase(this.generatorSwitch, i2);
        }
        this.cfw.addILoad(this.operationLocal);
        this.cfw.addLoadConstant(2);
        this.cfw.add(159, acquireLabel2);
        this.cfw.addILoad(this.operationLocal);
        this.cfw.addLoadConstant(1);
        this.cfw.add(159, acquireLabel);
    }

    private void generateIfJump(Node node, Node node2, int i, int i2) {
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        if (type == 26) {
            generateIfJump(firstChild, node, i2, i);
            return;
        }
        if (type != 46 && type != 47) {
            if (type != 52 && type != 53) {
                if (type == 105 || type == 106) {
                    int acquireLabel = this.cfw.acquireLabel();
                    if (type == 106) {
                        generateIfJump(firstChild, node, acquireLabel, i2);
                    } else {
                        generateIfJump(firstChild, node, i, acquireLabel);
                    }
                    this.cfw.markLabel(acquireLabel);
                    generateIfJump(firstChild.getNext(), node, i, i2);
                    return;
                }
                switch (type) {
                    case 12:
                    case 13:
                        break;
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        break;
                    default:
                        generateExpression(node, node2);
                        addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
                        this.cfw.add(154, i);
                        this.cfw.add(167, i2);
                        return;
                }
            }
            visitIfJumpRelOp(node, firstChild, i, i2);
            return;
        }
        visitIfJumpEqOp(node, firstChild, i, i2);
    }

    private void visitFunction(OptFunctionNode optFunctionNode, int i) {
        int index = this.codegen.getIndex(optFunctionNode.fnode);
        this.cfw.add(187, this.codegen.mainClassName);
        this.cfw.add(89);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(index);
        this.cfw.addInvoke(183, this.codegen.mainClassName, "<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V");
        if (i == 4) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            addOptRuntimeInvoke("bindThis", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Function;");
        }
        if (i == 2 || i == 4) {
            return;
        }
        this.cfw.addPush(i);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        addOptRuntimeInvoke("initFunction", "(Lorg/mozilla/javascript/NativeFunction;ILorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;)V");
    }

    private int getTargetLabel(Node node) {
        int labelId = node.labelId();
        if (labelId != -1) {
            return labelId;
        }
        int acquireLabel = this.cfw.acquireLabel();
        node.labelId(acquireLabel);
        return acquireLabel;
    }

    private void visitGoto(Jump jump, int i, Node node) {
        Node node2 = jump.target;
        if (i != 6 && i != 7) {
            if (i == 136) {
                if (this.isGenerator) {
                    addGotoWithReturn(node2);
                    return;
                } else {
                    inlineFinally(node2);
                    return;
                }
            }
            addGoto(node2, 167);
            return;
        }
        if (node == null) {
            throw Codegen.badTree();
        }
        int targetLabel = getTargetLabel(node2);
        int acquireLabel = this.cfw.acquireLabel();
        if (i == 6) {
            generateIfJump(node, jump, targetLabel, acquireLabel);
        } else {
            generateIfJump(node, jump, acquireLabel, targetLabel);
        }
        this.cfw.markLabel(acquireLabel);
    }

    private void addGotoWithReturn(Node node) {
        FinallyReturnPoint finallyReturnPoint = this.finallys.get(node);
        this.cfw.addLoadConstant(finallyReturnPoint.jsrPoints.size());
        addGoto(node, 167);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        finallyReturnPoint.jsrPoints.add(Integer.valueOf(acquireLabel));
    }

    private void generateArrayLiteralFactory(Node node, int i) {
        String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + i;
        initBodyGeneration();
        short s = this.firstFreeLocal;
        short s2 = (short) (s + 1);
        this.firstFreeLocal = s2;
        this.argsLocal = s;
        this.localsMax = s2;
        this.cfw.startMethod(str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;", (short) 2);
        visitArrayLiteral(node, node.getFirstChild(), true);
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void generateObjectLiteralFactory(Node node, int i) {
        String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + i;
        initBodyGeneration();
        short s = this.firstFreeLocal;
        short s2 = (short) (s + 1);
        this.firstFreeLocal = s2;
        this.argsLocal = s;
        this.localsMax = s2;
        this.cfw.startMethod(str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;", (short) 2);
        visitObjectLiteral(node, node.getFirstChild(), true);
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void visitArrayLiteral(Node node, Node node2, boolean z) {
        int i = 0;
        int i2 = 0;
        for (Node node3 = node2; node3 != null; node3 = node3.getNext()) {
            i2++;
        }
        if (!z && ((i2 > 10 || this.cfw.getCurrentCodeOffset() > 30000) && !this.hasVarsInRegs && !this.isGenerator && !this.inLocalBlock)) {
            if (this.literals == null) {
                this.literals = new LinkedList();
            }
            this.literals.add(node);
            String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + this.literals.size();
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addALoad(this.argsLocal);
            this.cfw.addInvoke(182, this.codegen.mainClassName, str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        if (this.isGenerator) {
            for (int i3 = 0; i3 != i2; i3++) {
                generateExpression(node2, node);
                node2 = node2.getNext();
            }
            addNewObjectArray(i2);
            while (i != i2) {
                this.cfw.add(90);
                this.cfw.add(95);
                this.cfw.addPush((i2 - i) - 1);
                this.cfw.add(95);
                this.cfw.add(83);
                i++;
            }
        } else {
            addNewObjectArray(i2);
            while (i != i2) {
                this.cfw.add(89);
                this.cfw.addPush(i);
                generateExpression(node2, node);
                this.cfw.add(83);
                node2 = node2.getNext();
                i++;
            }
        }
        int[] iArr = (int[]) node.getProp(11);
        if (iArr == null) {
            this.cfw.add(1);
            this.cfw.add(3);
        } else {
            this.cfw.addPush(OptRuntime.encodeIntArray(iArr));
            this.cfw.addPush(iArr.length);
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addOptRuntimeInvoke("newArrayLiteral", "([Ljava/lang/Object;Ljava/lang/String;ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void addLoadPropertyIds(Object[] objArr, int i) {
        addNewObjectArray(i);
        for (int i2 = 0; i2 != i; i2++) {
            this.cfw.add(89);
            this.cfw.addPush(i2);
            Object obj = objArr[i2];
            if (obj instanceof String) {
                this.cfw.addPush((String) obj);
            } else {
                this.cfw.addPush(((Integer) obj).intValue());
                addScriptRuntimeInvoke("wrapInt", "(I)Ljava/lang/Integer;");
            }
            this.cfw.add(83);
        }
    }

    private void addLoadPropertyValues(Node node, Node node2, int i) {
        int i2 = 0;
        if (this.isGenerator) {
            for (int i3 = 0; i3 != i; i3++) {
                int type = node2.getType();
                if (type == 152 || type == 153 || type == 164) {
                    generateExpression(node2.getFirstChild(), node);
                } else {
                    generateExpression(node2, node);
                }
                node2 = node2.getNext();
            }
            addNewObjectArray(i);
            while (i2 != i) {
                this.cfw.add(90);
                this.cfw.add(95);
                this.cfw.addPush((i - i2) - 1);
                this.cfw.add(95);
                this.cfw.add(83);
                i2++;
            }
            return;
        }
        addNewObjectArray(i);
        while (i2 != i) {
            this.cfw.add(89);
            this.cfw.addPush(i2);
            int type2 = node2.getType();
            if (type2 == 152 || type2 == 153 || type2 == 164) {
                generateExpression(node2.getFirstChild(), node);
            } else {
                generateExpression(node2, node);
            }
            this.cfw.add(83);
            node2 = node2.getNext();
            i2++;
        }
    }

    private void visitObjectLiteral(Node node, Node node2, boolean z) {
        boolean z2;
        Object[] objArr = (Object[]) node.getProp(12);
        int length = objArr.length;
        if (!z && ((length > 10 || this.cfw.getCurrentCodeOffset() > 30000) && !this.hasVarsInRegs && !this.isGenerator && !this.inLocalBlock)) {
            if (this.literals == null) {
                this.literals = new LinkedList();
            }
            this.literals.add(node);
            String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + this.literals.size();
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addALoad(this.argsLocal);
            this.cfw.addInvoke(182, this.codegen.mainClassName, str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        if (this.isGenerator) {
            addLoadPropertyValues(node, node2, length);
            addLoadPropertyIds(objArr, length);
            this.cfw.add(95);
        } else {
            addLoadPropertyIds(objArr, length);
            addLoadPropertyValues(node, node2, length);
        }
        Node node3 = node2;
        for (int i = 0; i != length; i++) {
            int type = node3.getType();
            if (type == 152 || type == 153) {
                z2 = true;
                break;
            }
            node3 = node3.getNext();
        }
        z2 = false;
        if (z2) {
            this.cfw.addPush(length);
            this.cfw.add(188, 10);
            for (int i2 = 0; i2 != length; i2++) {
                this.cfw.add(89);
                this.cfw.addPush(i2);
                int type2 = node2.getType();
                if (type2 == 152) {
                    this.cfw.add(2);
                } else if (type2 == 153) {
                    this.cfw.add(4);
                } else {
                    this.cfw.add(3);
                }
                this.cfw.add(79);
                node2 = node2.getNext();
            }
        } else {
            this.cfw.add(1);
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("newObjectLiteral", "([Ljava/lang/Object;[Ljava/lang/Object;[ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void visitSpecialCall(Node node, int i, int i2, Node node2) {
        String str;
        String str2;
        this.cfw.addALoad(this.contextLocal);
        if (i == 30) {
            generateExpression(node2, node);
        } else {
            generateFunctionAndThisObj(node2, node);
        }
        generateCallArgArray(node, node2.getNext(), false);
        if (i == 30) {
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addPush(i2);
            str = "newObjectSpecial";
            str2 = "(Lorg/mozilla/javascript/Context;Ljava/lang/Object;[Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;";
        } else {
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addPush(i2);
            String sourceName = this.scriptOrFn.getSourceName();
            ClassFileWriter classFileWriter = this.cfw;
            if (sourceName == null) {
                sourceName = "";
            }
            classFileWriter.addPush(sourceName);
            this.cfw.addPush(this.itsLineNumber);
            str = "callSpecial";
            str2 = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;ILjava/lang/String;I)Ljava/lang/Object;";
        }
        addOptRuntimeInvoke(str, str2);
    }

    private void visitStandardCall(Node node, Node node2) {
        String str;
        String str2;
        if (node.getType() != 38) {
            throw Codegen.badTree();
        }
        Node next = node2.getNext();
        int type = node2.getType();
        if (next == null) {
            if (type == 39) {
                this.cfw.addPush(node2.getString());
                str = "callName0";
                str2 = "(Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else if (type == 33) {
                Node firstChild = node2.getFirstChild();
                generateExpression(firstChild, node);
                this.cfw.addPush(firstChild.getNext().getString());
                str = "callProp0";
                str2 = "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else {
                if (type == 34) {
                    throw Kit.codeBug();
                }
                generateFunctionAndThisObj(node2, node);
                str = "call0";
                str2 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            }
        } else if (type == 39) {
            String string = node2.getString();
            generateCallArgArray(node, next, false);
            this.cfw.addPush(string);
            str = "callName";
            str2 = "([Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
        } else {
            int i = 0;
            for (Node node3 = next; node3 != null; node3 = node3.getNext()) {
                i++;
            }
            generateFunctionAndThisObj(node2, node);
            if (i == 1) {
                generateExpression(next, node);
                str = "call1";
                str2 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else if (i == 2) {
                generateExpression(next, node);
                generateExpression(next.getNext(), node);
                str = "call2";
                str2 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else {
                generateCallArgArray(node, next, false);
                str = "callN";
                str2 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            }
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addOptRuntimeInvoke(str, str2);
    }

    private void visitStandardNew(Node node, Node node2) {
        if (node.getType() != 30) {
            throw Codegen.badTree();
        }
        Node next = node2.getNext();
        generateExpression(node2, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        generateCallArgArray(node, next, false);
        addScriptRuntimeInvoke("newObject", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void visitOptimizedCall(Node node, OptFunctionNode optFunctionNode, int i, Node node2) {
        short newWordLocal;
        String bodyMethodName;
        Node next = node2.getNext();
        String str = this.codegen.mainClassName;
        if (i == 30) {
            generateExpression(node2, node);
            newWordLocal = 0;
        } else {
            generateFunctionAndThisObj(node2, node);
            newWordLocal = getNewWordLocal();
            this.cfw.addAStore(newWordLocal);
        }
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(89);
        this.cfw.add(193, str);
        this.cfw.add(153, acquireLabel2);
        this.cfw.add(192, str);
        this.cfw.add(89);
        this.cfw.add(180, str, TransferTable.COLUMN_ID, "I");
        this.cfw.addPush(this.codegen.getIndex(optFunctionNode.fnode));
        this.cfw.add(160, acquireLabel2);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (i == 30) {
            this.cfw.add(1);
        } else {
            this.cfw.addALoad(newWordLocal);
        }
        for (Node node3 = next; node3 != null; node3 = node3.getNext()) {
            int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node3);
            if (nodeIsDirectCallParameter >= 0) {
                this.cfw.addALoad(nodeIsDirectCallParameter);
                this.cfw.addDLoad(nodeIsDirectCallParameter + 1);
            } else if (node3.getIntProp(8, -1) == 0) {
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                generateExpression(node3, node);
            } else {
                generateExpression(node3, node);
                this.cfw.addPush(0.0d);
            }
        }
        this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
        ClassFileWriter classFileWriter = this.cfw;
        String str2 = this.codegen.mainClassName;
        if (i == 30) {
            bodyMethodName = this.codegen.getDirectCtorName(optFunctionNode.fnode);
        } else {
            bodyMethodName = this.codegen.getBodyMethodName(optFunctionNode.fnode);
        }
        classFileWriter.addInvoke(184, str2, bodyMethodName, this.codegen.getBodyMethodSignature(optFunctionNode.fnode));
        this.cfw.add(167, acquireLabel);
        this.cfw.markLabel(acquireLabel2);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (i != 30) {
            this.cfw.addALoad(newWordLocal);
            releaseWordLocal(newWordLocal);
        }
        generateCallArgArray(node, next, true);
        if (i == 30) {
            addScriptRuntimeInvoke("newObject", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        } else {
            this.cfw.addInvoke(185, "org/mozilla/javascript/Callable", NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        }
        this.cfw.markLabel(acquireLabel);
    }

    private void generateCallArgArray(Node node, Node node2, boolean z) {
        short s;
        int i = 0;
        for (Node node3 = node2; node3 != null; node3 = node3.getNext()) {
            i++;
        }
        if (i == 1 && (s = this.itsOneArgArray) >= 0) {
            this.cfw.addALoad(s);
        } else {
            addNewObjectArray(i);
        }
        for (int i2 = 0; i2 != i; i2++) {
            if (!this.isGenerator) {
                this.cfw.add(89);
                this.cfw.addPush(i2);
            }
            if (!z) {
                generateExpression(node2, node);
            } else {
                int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node2);
                if (nodeIsDirectCallParameter >= 0) {
                    dcpLoadAsObject(nodeIsDirectCallParameter);
                } else {
                    generateExpression(node2, node);
                    if (node2.getIntProp(8, -1) == 0) {
                        addDoubleWrap();
                    }
                }
            }
            if (this.isGenerator) {
                short newWordLocal = getNewWordLocal();
                this.cfw.addAStore(newWordLocal);
                this.cfw.add(192, "[Ljava/lang/Object;");
                this.cfw.add(89);
                this.cfw.addPush(i2);
                this.cfw.addALoad(newWordLocal);
                releaseWordLocal(newWordLocal);
            }
            this.cfw.add(83);
            node2 = node2.getNext();
        }
    }

    private void generateFunctionAndThisObj(Node node, Node node2) {
        int type = node.getType();
        int type2 = node.getType();
        if (type2 != 33) {
            if (type2 == 34) {
                throw Kit.codeBug();
            }
            if (type2 != 36) {
                if (type2 == 39) {
                    this.cfw.addPush(node.getString());
                    this.cfw.addALoad(this.contextLocal);
                    this.cfw.addALoad(this.variableObjectLocal);
                    addScriptRuntimeInvoke("getNameFunctionAndThis", "(Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
                } else {
                    generateExpression(node, node2);
                    this.cfw.addALoad(this.contextLocal);
                    addScriptRuntimeInvoke("getValueFunctionAndThis", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Callable;");
                }
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("lastStoredScriptable", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;");
            }
        }
        Node firstChild = node.getFirstChild();
        generateExpression(firstChild, node);
        Node next = firstChild.getNext();
        if (type == 33) {
            this.cfw.addPush(next.getString());
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("getPropFunctionAndThis", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
        } else {
            generateExpression(next, node);
            if (node.getIntProp(8, -1) != -1) {
                addDoubleWrap();
            }
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("getElemFunctionAndThis", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
        }
        this.cfw.addALoad(this.contextLocal);
        addScriptRuntimeInvoke("lastStoredScriptable", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void updateLineNumber(Node node) {
        int lineno = node.getLineno();
        this.itsLineNumber = lineno;
        if (lineno == -1) {
            return;
        }
        this.cfw.addLineNumberEntry((short) lineno);
    }

    private void visitTryCatchFinally(Jump jump, Node node) {
        int i;
        int i2;
        short newWordLocal = getNewWordLocal();
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addAStore(newWordLocal);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel, (short) 0);
        Node node2 = jump.target;
        Node node3 = jump.getFinally();
        int[] iArr = new int[5];
        this.exceptionManager.pushExceptionInfo(jump);
        if (node2 != null) {
            iArr[0] = this.cfw.acquireLabel();
            iArr[1] = this.cfw.acquireLabel();
            iArr[2] = this.cfw.acquireLabel();
            Context currentContext = Context.getCurrentContext();
            if (currentContext != null && currentContext.hasFeature(13)) {
                iArr[3] = this.cfw.acquireLabel();
            }
        }
        if (node3 != null) {
            iArr[4] = this.cfw.acquireLabel();
        }
        this.exceptionManager.setHandlers(iArr, acquireLabel);
        if (this.isGenerator && node3 != null) {
            FinallyReturnPoint finallyReturnPoint = new FinallyReturnPoint();
            if (this.finallys == null) {
                this.finallys = new HashMap();
            }
            this.finallys.put(node3, finallyReturnPoint);
            this.finallys.put(node3.getNext(), finallyReturnPoint);
        }
        for (Node node4 = node; node4 != null; node4 = node4.getNext()) {
            if (node4 == node2) {
                int targetLabel = getTargetLabel(node2);
                this.exceptionManager.removeHandler(0, targetLabel);
                this.exceptionManager.removeHandler(1, targetLabel);
                this.exceptionManager.removeHandler(2, targetLabel);
                this.exceptionManager.removeHandler(3, targetLabel);
            }
            generateStatement(node4);
        }
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, acquireLabel2);
        int localBlockRegister = getLocalBlockRegister(jump);
        if (node2 != null) {
            int labelId = node2.labelId();
            i = localBlockRegister;
            i2 = acquireLabel2;
            generateCatchBlock(0, newWordLocal, labelId, localBlockRegister, iArr[0]);
            generateCatchBlock(1, newWordLocal, labelId, localBlockRegister, iArr[1]);
            generateCatchBlock(2, newWordLocal, labelId, localBlockRegister, iArr[2]);
            Context currentContext2 = Context.getCurrentContext();
            if (currentContext2 != null && currentContext2.hasFeature(13)) {
                generateCatchBlock(3, newWordLocal, labelId, i, iArr[3]);
            }
        } else {
            i = localBlockRegister;
            i2 = acquireLabel2;
        }
        if (node3 != null) {
            int acquireLabel3 = this.cfw.acquireLabel();
            int acquireLabel4 = this.cfw.acquireLabel();
            this.cfw.markHandler(acquireLabel3);
            if (!this.isGenerator) {
                this.cfw.markLabel(iArr[4]);
            }
            int i3 = i;
            this.cfw.addAStore(i3);
            this.cfw.addALoad(newWordLocal);
            this.cfw.addAStore(this.variableObjectLocal);
            int labelId2 = node3.labelId();
            if (this.isGenerator) {
                addGotoWithReturn(node3);
            } else {
                inlineFinally(node3, iArr[4], acquireLabel4);
            }
            this.cfw.addALoad(i3);
            if (this.isGenerator) {
                this.cfw.add(192, "java/lang/Throwable");
            }
            this.cfw.add(191);
            this.cfw.markLabel(acquireLabel4);
            if (this.isGenerator) {
                this.cfw.addExceptionHandler(acquireLabel, labelId2, acquireLabel3, null);
            }
        }
        releaseWordLocal(newWordLocal);
        this.cfw.markLabel(i2);
        if (this.isGenerator) {
            return;
        }
        this.exceptionManager.popExceptionInfo();
    }

    private void generateCatchBlock(int i, short s, int i2, int i3, int i4) {
        if (i4 == 0) {
            i4 = this.cfw.acquireLabel();
        }
        this.cfw.markHandler(i4);
        this.cfw.addAStore(i3);
        this.cfw.addALoad(s);
        this.cfw.addAStore(this.variableObjectLocal);
        exceptionTypeToName(i);
        this.cfw.add(167, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String exceptionTypeToName(int i) {
        if (i == 0) {
            return "org/mozilla/javascript/JavaScriptException";
        }
        if (i == 1) {
            return "org/mozilla/javascript/EvaluatorException";
        }
        if (i == 2) {
            return "org/mozilla/javascript/EcmaError";
        }
        if (i == 3) {
            return "java/lang/Throwable";
        }
        if (i == 4) {
            return null;
        }
        throw Kit.codeBug();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* compiled from: Codegen.java */
    /* loaded from: classes2.dex */
    public class ExceptionManager {
        private LinkedList<ExceptionInfo> exceptionInfo = new LinkedList<>();

        ExceptionManager() {
        }

        void pushExceptionInfo(Jump jump) {
            this.exceptionInfo.add(new ExceptionInfo(jump, BodyCodegen.this.getFinallyAtTarget(jump.getFinally())));
        }

        void addHandler(int i, int i2, int i3) {
            ExceptionInfo top = getTop();
            top.handlerLabels[i] = i2;
            top.exceptionStarts[i] = i3;
        }

        void setHandlers(int[] iArr, int i) {
            getTop();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] != 0) {
                    addHandler(i2, iArr[i2], i);
                }
            }
        }

        int removeHandler(int i, int i2) {
            ExceptionInfo top = getTop();
            if (top.handlerLabels[i] == 0) {
                return 0;
            }
            int i3 = top.handlerLabels[i];
            endCatch(top, i, i2);
            top.handlerLabels[i] = 0;
            return i3;
        }

        void popExceptionInfo() {
            this.exceptionInfo.removeLast();
        }

        void markInlineFinallyStart(Node node, int i) {
            LinkedList<ExceptionInfo> linkedList = this.exceptionInfo;
            ListIterator<ExceptionInfo> listIterator = linkedList.listIterator(linkedList.size());
            while (listIterator.hasPrevious()) {
                ExceptionInfo previous = listIterator.previous();
                for (int i2 = 0; i2 < 5; i2++) {
                    if (previous.handlerLabels[i2] != 0 && previous.currentFinally == null) {
                        endCatch(previous, i2, i);
                        previous.exceptionStarts[i2] = 0;
                        previous.currentFinally = node;
                    }
                }
                if (previous.finallyBlock == node) {
                    return;
                }
            }
        }

        void markInlineFinallyEnd(Node node, int i) {
            LinkedList<ExceptionInfo> linkedList = this.exceptionInfo;
            ListIterator<ExceptionInfo> listIterator = linkedList.listIterator(linkedList.size());
            while (listIterator.hasPrevious()) {
                ExceptionInfo previous = listIterator.previous();
                for (int i2 = 0; i2 < 5; i2++) {
                    if (previous.handlerLabels[i2] != 0 && previous.currentFinally == node) {
                        previous.exceptionStarts[i2] = i;
                        previous.currentFinally = null;
                    }
                }
                if (previous.finallyBlock == node) {
                    return;
                }
            }
        }

        private void endCatch(ExceptionInfo exceptionInfo, int i, int i2) {
            if (exceptionInfo.exceptionStarts[i] == 0) {
                throw new IllegalStateException("bad exception start");
            }
            if (BodyCodegen.this.cfw.getLabelPC(exceptionInfo.exceptionStarts[i]) != BodyCodegen.this.cfw.getLabelPC(i2)) {
                BodyCodegen.this.cfw.addExceptionHandler(exceptionInfo.exceptionStarts[i], i2, exceptionInfo.handlerLabels[i], BodyCodegen.this.exceptionTypeToName(i));
            }
        }

        private ExceptionInfo getTop() {
            return this.exceptionInfo.getLast();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes8.dex
         */
        /* compiled from: Codegen.java */
        /* loaded from: classes2.dex */
        public class ExceptionInfo {
            Node finallyBlock;
            Jump node;
            int[] handlerLabels = new int[5];
            int[] exceptionStarts = new int[5];
            Node currentFinally = null;

            ExceptionInfo(Jump jump, Node node) {
                this.node = jump;
                this.finallyBlock = node;
            }
        }
    }

    private void inlineFinally(Node node, int i, int i2) {
        Node finallyAtTarget = getFinallyAtTarget(node);
        finallyAtTarget.resetTargets();
        this.exceptionManager.markInlineFinallyStart(finallyAtTarget, i);
        for (Node firstChild = finallyAtTarget.getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
            generateStatement(firstChild);
        }
        this.exceptionManager.markInlineFinallyEnd(finallyAtTarget, i2);
    }

    private void inlineFinally(Node node) {
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        inlineFinally(node, acquireLabel, acquireLabel2);
        this.cfw.markLabel(acquireLabel2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Node getFinallyAtTarget(Node node) {
        Node next;
        if (node == null) {
            return null;
        }
        if (node.getType() == 126) {
            return node;
        }
        if (node == null || node.getType() != 132 || (next = node.getNext()) == null || next.getType() != 126) {
            throw Kit.codeBug("bad finally target");
        }
        return next;
    }

    private boolean generateSaveLocals(Node node) {
        int i = 0;
        for (int i2 = 0; i2 < this.firstFreeLocal; i2++) {
            if (this.locals[i2] != 0) {
                i++;
            }
        }
        if (i == 0) {
            ((FunctionNode) this.scriptOrFn).addLiveLocals(node, null);
            return false;
        }
        int i3 = this.maxLocals;
        if (i3 <= i) {
            i3 = i;
        }
        this.maxLocals = i3;
        int[] iArr = new int[i];
        int i4 = 0;
        for (int i5 = 0; i5 < this.firstFreeLocal; i5++) {
            if (this.locals[i5] != 0) {
                iArr[i4] = i5;
                i4++;
            }
        }
        ((FunctionNode) this.scriptOrFn).addLiveLocals(node, iArr);
        generateGetGeneratorLocalsState();
        for (int i6 = 0; i6 < i; i6++) {
            this.cfw.add(89);
            this.cfw.addLoadConstant(i6);
            this.cfw.addALoad(iArr[i6]);
            this.cfw.add(83);
        }
        this.cfw.add(87);
        return true;
    }

    private void visitSwitch(Jump jump, Node node) {
        generateExpression(node, jump);
        short newWordLocal = getNewWordLocal();
        this.cfw.addAStore(newWordLocal);
        for (Jump jump2 = (Jump) node.getNext(); jump2 != null; jump2 = (Jump) jump2.getNext()) {
            if (jump2.getType() != 116) {
                throw Codegen.badTree();
            }
            generateExpression(jump2.getFirstChild(), jump2);
            this.cfw.addALoad(newWordLocal);
            addScriptRuntimeInvoke("shallowEq", "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            addGoto(jump2.target, 154);
        }
        releaseWordLocal(newWordLocal);
    }

    private void visitTypeofname(Node node) {
        int indexForNameNode;
        if (this.hasVarsInRegs && (indexForNameNode = this.fnCurrent.fnode.getIndexForNameNode(node)) >= 0) {
            if (this.fnCurrent.isNumberVar(indexForNameNode)) {
                this.cfw.addPush("number");
                return;
            }
            if (varIsDirectCallParameter(indexForNameNode)) {
                short s = this.varRegisters[indexForNameNode];
                this.cfw.addALoad(s);
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                int acquireLabel = this.cfw.acquireLabel();
                this.cfw.add(165, acquireLabel);
                short stackTop = this.cfw.getStackTop();
                this.cfw.addALoad(s);
                addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
                int acquireLabel2 = this.cfw.acquireLabel();
                this.cfw.add(167, acquireLabel2);
                this.cfw.markLabel(acquireLabel, stackTop);
                this.cfw.addPush("number");
                this.cfw.markLabel(acquireLabel2);
                return;
            }
            this.cfw.addALoad(this.varRegisters[indexForNameNode]);
            addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
            return;
        }
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(node.getString());
        addScriptRuntimeInvoke("typeofName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/String;");
    }

    private void saveCurrentCodeOffset() {
        this.savedCodeOffset = this.cfw.getCurrentCodeOffset();
    }

    private void addInstructionCount() {
        addInstructionCount(Math.max(this.cfw.getCurrentCodeOffset() - this.savedCodeOffset, 1));
    }

    private void addInstructionCount(int i) {
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(i);
        addScriptRuntimeInvoke("addInstructionCount", "(Lorg/mozilla/javascript/Context;I)V");
    }

    private void visitIncDec(Node node) {
        int existingIntProp = node.getExistingIntProp(13);
        Node firstChild = node.getFirstChild();
        int type = firstChild.getType();
        if (type == 33) {
            Node firstChild2 = firstChild.getFirstChild();
            generateExpression(firstChild2, node);
            generateExpression(firstChild2.getNext(), node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(existingIntProp);
            addScriptRuntimeInvoke("propIncrDecr", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
            return;
        }
        if (type == 34) {
            throw Kit.codeBug();
        }
        if (type == 36) {
            Node firstChild3 = firstChild.getFirstChild();
            generateExpression(firstChild3, node);
            generateExpression(firstChild3.getNext(), node);
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(existingIntProp);
            if (firstChild3.getNext().getIntProp(8, -1) != -1) {
                addOptRuntimeInvoke("elemIncrDecr", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                return;
            } else {
                addScriptRuntimeInvoke("elemIncrDecr", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                return;
            }
        }
        if (type == 39) {
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addPush(firstChild.getString());
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addPush(existingIntProp);
            addScriptRuntimeInvoke("nameIncrDecr", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;I)Ljava/lang/Object;");
            return;
        }
        if (type != 55) {
            if (type == 68) {
                generateExpression(firstChild.getFirstChild(), node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(existingIntProp);
                addScriptRuntimeInvoke("refIncrDecr", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                return;
            }
            Codegen.badTree();
            return;
        }
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        boolean z = (existingIntProp & 2) != 0;
        int varIndex = this.fnCurrent.getVarIndex(firstChild);
        short s = this.varRegisters[varIndex];
        if (this.fnCurrent.fnode.getParamAndVarConst()[varIndex]) {
            if (node.getIntProp(8, -1) != -1) {
                this.cfw.addDLoad(s + (varIsDirectCallParameter(varIndex) ? 1 : 0));
                if (z) {
                    return;
                }
                this.cfw.addPush(1.0d);
                if ((existingIntProp & 1) == 0) {
                    this.cfw.add(99);
                    return;
                } else {
                    this.cfw.add(103);
                    return;
                }
            }
            if (varIsDirectCallParameter(varIndex)) {
                dcpLoadAsObject(s);
            } else {
                this.cfw.addALoad(s);
            }
            if (z) {
                this.cfw.add(89);
                addObjectToDouble();
                this.cfw.add(88);
                return;
            } else {
                addObjectToDouble();
                this.cfw.addPush(1.0d);
                if ((existingIntProp & 1) == 0) {
                    this.cfw.add(99);
                } else {
                    this.cfw.add(103);
                }
                addDoubleWrap();
                return;
            }
        }
        if (node.getIntProp(8, -1) != -1) {
            boolean varIsDirectCallParameter = varIsDirectCallParameter(varIndex);
            ClassFileWriter classFileWriter = this.cfw;
            int i = s + (varIsDirectCallParameter ? 1 : 0);
            classFileWriter.addDLoad(i);
            if (z) {
                this.cfw.add(92);
            }
            this.cfw.addPush(1.0d);
            if ((existingIntProp & 1) == 0) {
                this.cfw.add(99);
            } else {
                this.cfw.add(103);
            }
            if (!z) {
                this.cfw.add(92);
            }
            this.cfw.addDStore(i);
            return;
        }
        if (varIsDirectCallParameter(varIndex)) {
            dcpLoadAsObject(s);
        } else {
            this.cfw.addALoad(s);
        }
        addObjectToDouble();
        if (z) {
            this.cfw.add(92);
        }
        this.cfw.addPush(1.0d);
        if ((existingIntProp & 1) == 0) {
            this.cfw.add(99);
        } else {
            this.cfw.add(103);
        }
        addDoubleWrap();
        if (!z) {
            this.cfw.add(89);
        }
        this.cfw.addAStore(s);
        if (z) {
            addDoubleWrap();
        }
    }

    private static boolean isArithmeticNode(Node node) {
        int type = node.getType();
        return type == 22 || type == 25 || type == 24 || type == 23;
    }

    private void visitArithmetic(Node node, int i, Node node2, Node node3) {
        if (node.getIntProp(8, -1) != -1) {
            generateExpression(node2, node);
            generateExpression(node2.getNext(), node);
            this.cfw.add(i);
            return;
        }
        boolean isArithmeticNode = isArithmeticNode(node3);
        generateExpression(node2, node);
        if (!isArithmeticNode(node2)) {
            addObjectToDouble();
        }
        generateExpression(node2.getNext(), node);
        if (!isArithmeticNode(node2.getNext())) {
            addObjectToDouble();
        }
        this.cfw.add(i);
        if (isArithmeticNode) {
            return;
        }
        addDoubleWrap();
    }

    private void visitBitOp(Node node, int i, Node node2) {
        int intProp = node.getIntProp(8, -1);
        generateExpression(node2, node);
        if (i == 20) {
            addScriptRuntimeInvoke("toUint32", "(Ljava/lang/Object;)J");
            generateExpression(node2.getNext(), node);
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
            this.cfw.addPush(31);
            this.cfw.add(126);
            this.cfw.add(125);
            this.cfw.add(138);
            addDoubleWrap();
            return;
        }
        if (intProp == -1) {
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
            generateExpression(node2.getNext(), node);
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
        } else {
            addScriptRuntimeInvoke("toInt32", "(D)I");
            generateExpression(node2.getNext(), node);
            addScriptRuntimeInvoke("toInt32", "(D)I");
        }
        if (i == 18) {
            this.cfw.add(120);
        } else if (i != 19) {
            switch (i) {
                case 9:
                    this.cfw.add(128);
                    break;
                case 10:
                    this.cfw.add(130);
                    break;
                case 11:
                    this.cfw.add(126);
                    break;
                default:
                    throw Codegen.badTree();
            }
        } else {
            this.cfw.add(122);
        }
        this.cfw.add(135);
        if (intProp == -1) {
            addDoubleWrap();
        }
    }

    private int nodeIsDirectCallParameter(Node node) {
        if (node.getType() != 55 || !this.inDirectCallFunction || this.itsForcedObjectParameters) {
            return -1;
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        if (this.fnCurrent.isParameter(varIndex)) {
            return this.varRegisters[varIndex];
        }
        return -1;
    }

    private boolean varIsDirectCallParameter(int i) {
        return this.fnCurrent.isParameter(i) && this.inDirectCallFunction && !this.itsForcedObjectParameters;
    }

    private void genSimpleCompare(int i, int i2, int i3) {
        if (i2 == -1) {
            throw Codegen.badTree();
        }
        switch (i) {
            case 14:
                this.cfw.add(152);
                this.cfw.add(155, i2);
                break;
            case 15:
                this.cfw.add(152);
                this.cfw.add(158, i2);
                break;
            case 16:
                this.cfw.add(151);
                this.cfw.add(157, i2);
                break;
            case 17:
                this.cfw.add(151);
                this.cfw.add(156, i2);
                break;
            default:
                throw Codegen.badTree();
        }
        if (i3 != -1) {
            this.cfw.add(167, i3);
        }
    }

    private void visitIfJumpRelOp(Node node, Node node2, int i, int i2) {
        if (i == -1 || i2 == -1) {
            throw Codegen.badTree();
        }
        int type = node.getType();
        Node next = node2.getNext();
        if (type == 53 || type == 52) {
            generateExpression(node2, node);
            generateExpression(next, node);
            this.cfw.addALoad(this.contextLocal);
            addScriptRuntimeInvoke(type == 53 ? "instanceOf" : "in", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Z");
            this.cfw.add(154, i);
            this.cfw.add(167, i2);
            return;
        }
        int intProp = node.getIntProp(8, -1);
        int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node2);
        int nodeIsDirectCallParameter2 = nodeIsDirectCallParameter(next);
        if (intProp != -1) {
            if (intProp != 2) {
                generateExpression(node2, node);
            } else if (nodeIsDirectCallParameter != -1) {
                dcpLoadAsNumber(nodeIsDirectCallParameter);
            } else {
                generateExpression(node2, node);
                addObjectToDouble();
            }
            if (intProp != 1) {
                generateExpression(next, node);
            } else if (nodeIsDirectCallParameter2 != -1) {
                dcpLoadAsNumber(nodeIsDirectCallParameter2);
            } else {
                generateExpression(next, node);
                addObjectToDouble();
            }
            genSimpleCompare(type, i, i2);
            return;
        }
        if (nodeIsDirectCallParameter != -1 && nodeIsDirectCallParameter2 != -1) {
            short stackTop = this.cfw.getStackTop();
            int acquireLabel = this.cfw.acquireLabel();
            this.cfw.addALoad(nodeIsDirectCallParameter);
            this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
            this.cfw.add(166, acquireLabel);
            this.cfw.addDLoad(nodeIsDirectCallParameter + 1);
            dcpLoadAsNumber(nodeIsDirectCallParameter2);
            genSimpleCompare(type, i, i2);
            if (stackTop != this.cfw.getStackTop()) {
                throw Codegen.badTree();
            }
            this.cfw.markLabel(acquireLabel);
            int acquireLabel2 = this.cfw.acquireLabel();
            this.cfw.addALoad(nodeIsDirectCallParameter2);
            this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
            this.cfw.add(166, acquireLabel2);
            this.cfw.addALoad(nodeIsDirectCallParameter);
            addObjectToDouble();
            this.cfw.addDLoad(nodeIsDirectCallParameter2 + 1);
            genSimpleCompare(type, i, i2);
            if (stackTop != this.cfw.getStackTop()) {
                throw Codegen.badTree();
            }
            this.cfw.markLabel(acquireLabel2);
            this.cfw.addALoad(nodeIsDirectCallParameter);
            this.cfw.addALoad(nodeIsDirectCallParameter2);
        } else {
            generateExpression(node2, node);
            generateExpression(next, node);
        }
        if (type == 17 || type == 16) {
            this.cfw.add(95);
        }
        addScriptRuntimeInvoke((type == 14 || type == 16) ? "cmp_LT" : "cmp_LE", "(Ljava/lang/Object;Ljava/lang/Object;)Z");
        this.cfw.add(154, i);
        this.cfw.add(167, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void visitIfJumpEqOp(Node node, Node node2, int i, int i2) {
        int i3;
        int i4;
        int i5;
        Node node3 = node2;
        int i6 = i;
        int i7 = i2;
        if (i6 == -1 || i7 == -1) {
            throw Codegen.badTree();
        }
        short stackTop = this.cfw.getStackTop();
        int type = node.getType();
        Node next = node2.getNext();
        if (node2.getType() == 42 || next.getType() == 42) {
            if (node2.getType() == 42) {
                node3 = next;
            }
            generateExpression(node3, node);
            int i8 = 46;
            if (type != 46) {
                if (type == 47) {
                    i8 = 46;
                } else {
                    if (type != 12) {
                        if (type != 13) {
                            throw Codegen.badTree();
                        }
                        i7 = i6;
                        i6 = i7;
                    }
                    this.cfw.add(89);
                    int acquireLabel = this.cfw.acquireLabel();
                    this.cfw.add(199, acquireLabel);
                    short stackTop2 = this.cfw.getStackTop();
                    this.cfw.add(87);
                    this.cfw.add(167, i6);
                    this.cfw.markLabel(acquireLabel, stackTop2);
                    Codegen.pushUndefined(this.cfw);
                    this.cfw.add(165, i6);
                    this.cfw.add(167, i7);
                }
            }
            this.cfw.add(type == i8 ? 198 : 199, i6);
            this.cfw.add(167, i7);
        } else {
            int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node3);
            if (nodeIsDirectCallParameter != -1 && next.getType() == 150) {
                Node firstChild = next.getFirstChild();
                if (firstChild.getType() == 40) {
                    this.cfw.addALoad(nodeIsDirectCallParameter);
                    this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                    int acquireLabel2 = this.cfw.acquireLabel();
                    this.cfw.add(166, acquireLabel2);
                    this.cfw.addDLoad(nodeIsDirectCallParameter + 1);
                    this.cfw.addPush(firstChild.getDouble());
                    this.cfw.add(151);
                    if (type == 12) {
                        i3 = 153;
                        this.cfw.add(153, i6);
                        i4 = 154;
                    } else {
                        i3 = 153;
                        i4 = 154;
                        this.cfw.add(154, i6);
                    }
                    this.cfw.add(167, i7);
                    this.cfw.markLabel(acquireLabel2);
                    generateExpression(node3, node);
                    generateExpression(next, node);
                    String str = "shallowEq";
                    if (type == 12) {
                        if (type == 13) {
                            str = "eq";
                        } else if (type != 46) {
                            if (type != 47) {
                                throw Codegen.badTree();
                            }
                        }
                        i5 = i3;
                        addScriptRuntimeInvoke(str, "(Ljava/lang/Object;Ljava/lang/Object;)Z");
                        this.cfw.add(i5, i6);
                        this.cfw.add(167, i7);
                    } else {
                        str = "eq";
                    }
                    i5 = i4;
                    addScriptRuntimeInvoke(str, "(Ljava/lang/Object;Ljava/lang/Object;)Z");
                    this.cfw.add(i5, i6);
                    this.cfw.add(167, i7);
                }
            }
            i3 = 153;
            i4 = 154;
            generateExpression(node3, node);
            generateExpression(next, node);
            String str2 = "shallowEq";
            if (type == 12) {
            }
            i5 = i4;
            addScriptRuntimeInvoke(str2, "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            this.cfw.add(i5, i6);
            this.cfw.add(167, i7);
        }
        if (stackTop != this.cfw.getStackTop()) {
            throw Codegen.badTree();
        }
    }

    private void visitSetName(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(string);
        addScriptRuntimeInvoke("setName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitStrictSetName(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(string);
        addScriptRuntimeInvoke("strictSetName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitSetConst(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(string);
        addScriptRuntimeInvoke("setConst", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitGetVar(Node node) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        short s = this.varRegisters[varIndex];
        if (varIsDirectCallParameter(varIndex)) {
            if (node.getIntProp(8, -1) != -1) {
                dcpLoadAsNumber(s);
                return;
            } else {
                dcpLoadAsObject(s);
                return;
            }
        }
        if (this.fnCurrent.isNumberVar(varIndex)) {
            this.cfw.addDLoad(s);
        } else {
            this.cfw.addALoad(s);
        }
    }

    private void visitSetVar(Node node, Node node2, boolean z) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        generateExpression(node2.getNext(), node);
        boolean z2 = node.getIntProp(8, -1) != -1;
        short s = this.varRegisters[varIndex];
        if (this.fnCurrent.fnode.getParamAndVarConst()[varIndex]) {
            if (z) {
                return;
            }
            if (z2) {
                this.cfw.add(88);
                return;
            } else {
                this.cfw.add(87);
                return;
            }
        }
        if (varIsDirectCallParameter(varIndex)) {
            if (z2) {
                if (z) {
                    this.cfw.add(92);
                }
                this.cfw.addALoad(s);
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                int acquireLabel = this.cfw.acquireLabel();
                int acquireLabel2 = this.cfw.acquireLabel();
                this.cfw.add(165, acquireLabel);
                short stackTop = this.cfw.getStackTop();
                addDoubleWrap();
                this.cfw.addAStore(s);
                this.cfw.add(167, acquireLabel2);
                this.cfw.markLabel(acquireLabel, stackTop);
                this.cfw.addDStore(s + 1);
                this.cfw.markLabel(acquireLabel2);
                return;
            }
            if (z) {
                this.cfw.add(89);
            }
            this.cfw.addAStore(s);
            return;
        }
        boolean isNumberVar = this.fnCurrent.isNumberVar(varIndex);
        if (!z2) {
            if (isNumberVar) {
                Kit.codeBug();
            }
            this.cfw.addAStore(s);
            if (z) {
                this.cfw.addALoad(s);
                return;
            }
            return;
        }
        if (isNumberVar) {
            this.cfw.addDStore(s);
            if (z) {
                this.cfw.addDLoad(s);
                return;
            }
            return;
        }
        if (z) {
            this.cfw.add(92);
        }
        addDoubleWrap();
        this.cfw.addAStore(s);
    }

    private void visitSetConstVar(Node node, Node node2, boolean z) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        generateExpression(node2.getNext(), node);
        boolean z2 = node.getIntProp(8, -1) != -1;
        short s = this.varRegisters[varIndex];
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        if (z2) {
            int i = s + 2;
            this.cfw.addILoad(i);
            this.cfw.add(154, acquireLabel2);
            short stackTop = this.cfw.getStackTop();
            this.cfw.addPush(1);
            this.cfw.addIStore(i);
            this.cfw.addDStore(s);
            if (z) {
                this.cfw.addDLoad(s);
                this.cfw.markLabel(acquireLabel2, stackTop);
            } else {
                this.cfw.add(167, acquireLabel);
                this.cfw.markLabel(acquireLabel2, stackTop);
                this.cfw.add(88);
            }
        } else {
            int i2 = s + 1;
            this.cfw.addILoad(i2);
            this.cfw.add(154, acquireLabel2);
            short stackTop2 = this.cfw.getStackTop();
            this.cfw.addPush(1);
            this.cfw.addIStore(i2);
            this.cfw.addAStore(s);
            if (z) {
                this.cfw.addALoad(s);
                this.cfw.markLabel(acquireLabel2, stackTop2);
            } else {
                this.cfw.add(167, acquireLabel);
                this.cfw.markLabel(acquireLabel2, stackTop2);
                this.cfw.add(87);
            }
        }
        this.cfw.markLabel(acquireLabel);
    }

    private void visitGetProp(Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        generateExpression(next, node);
        if (node.getType() == 34) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("getObjectPropNoWarn", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        } else if (node2.getType() == 43 && next.getType() == 41) {
            this.cfw.addALoad(this.contextLocal);
            addScriptRuntimeInvoke("getObjectProp", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
        } else {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("getObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        }
    }

    private void visitSetProp(int i, Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        if (i == 140) {
            this.cfw.add(89);
        }
        generateExpression(next, node);
        Node next2 = next.getNext();
        if (i == 140) {
            this.cfw.add(90);
            if (node2.getType() == 43 && next.getType() == 41) {
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("getObjectProp", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
            } else {
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("getObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            }
        }
        generateExpression(next2, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("setObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
    }

    private void visitSetElem(int i, Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        if (i == 141) {
            this.cfw.add(89);
        }
        generateExpression(next, node);
        Node next2 = next.getNext();
        boolean z = node.getIntProp(8, -1) != -1;
        if (i == 141) {
            if (z) {
                this.cfw.add(93);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("getObjectIndex", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            } else {
                this.cfw.add(90);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("getObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            }
        }
        generateExpression(next2, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (z) {
            addScriptRuntimeInvoke("setObjectIndex", "(Ljava/lang/Object;DLjava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        } else {
            addScriptRuntimeInvoke("setObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        }
    }

    private void visitDotQuery(Node node, Node node2) {
        updateLineNumber(node);
        generateExpression(node2, node);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("enterDotQuery", "(Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(1);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        this.cfw.add(87);
        generateExpression(node2.getNext(), node);
        addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("updateDotQuery", "(ZLorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        this.cfw.add(89);
        this.cfw.add(198, acquireLabel);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("leaveDotQuery", "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
    }

    private int getLocalBlockRegister(Node node) {
        return ((Node) node.getProp(3)).getExistingIntProp(2);
    }

    private void dcpLoadAsNumber(int i) {
        this.cfw.addALoad(i);
        this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.add(165, acquireLabel);
        short stackTop = this.cfw.getStackTop();
        this.cfw.addALoad(i);
        addObjectToDouble();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, acquireLabel2);
        this.cfw.markLabel(acquireLabel, stackTop);
        this.cfw.addDLoad(i + 1);
        this.cfw.markLabel(acquireLabel2);
    }

    private void dcpLoadAsObject(int i) {
        this.cfw.addALoad(i);
        this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.add(165, acquireLabel);
        short stackTop = this.cfw.getStackTop();
        this.cfw.addALoad(i);
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, acquireLabel2);
        this.cfw.markLabel(acquireLabel, stackTop);
        this.cfw.addDLoad(i + 1);
        addDoubleWrap();
        this.cfw.markLabel(acquireLabel2);
    }

    private void addGoto(Node node, int i) {
        this.cfw.add(i, getTargetLabel(node));
    }

    private void addObjectToDouble() {
        addScriptRuntimeInvoke("toNumber", "(Ljava/lang/Object;)D");
    }

    private void addNewObjectArray(int i) {
        if (i == 0) {
            short s = this.itsZeroArgArray;
            if (s >= 0) {
                this.cfw.addALoad(s);
                return;
            } else {
                this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
                return;
            }
        }
        this.cfw.addPush(i);
        this.cfw.add(189, "java/lang/Object");
    }

    private void addScriptRuntimeInvoke(String str, String str2) {
        this.cfw.addInvoke(184, "org.mozilla.javascript.ScriptRuntime", str, str2);
    }

    private void addOptRuntimeInvoke(String str, String str2) {
        this.cfw.addInvoke(184, "org/mozilla/javascript/optimizer/OptRuntime", str, str2);
    }

    private void addJumpedBooleanWrap(int i, int i2) {
        this.cfw.markLabel(i2);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
        this.cfw.add(167, acquireLabel);
        this.cfw.markLabel(i);
        this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
        this.cfw.markLabel(acquireLabel);
        this.cfw.adjustStackTop(-1);
    }

    private void addDoubleWrap() {
        addOptRuntimeInvoke("wrapDouble", "(D)Ljava/lang/Double;");
    }

    private short getNewWordPairLocal(boolean z) {
        return getNewWordIntern(z ? 3 : 2);
    }

    private short getNewWordLocal(boolean z) {
        return getNewWordIntern(z ? 2 : 1);
    }

    private short getNewWordLocal() {
        return getNewWordIntern(1);
    }

    private short getNewWordIntern(int i) {
        int i2;
        int i3;
        int[] iArr = this.locals;
        if (i > 1) {
            i2 = this.firstFreeLocal;
            loop0: while (true) {
                if (i2 + i > 1024) {
                    i2 = -1;
                    break;
                }
                i3 = 0;
                while (i3 < i) {
                    if (iArr[i2 + i3] != 0) {
                        break;
                    }
                    i3++;
                }
                break loop0;
                i2 += i3 + 1;
            }
        } else {
            i2 = this.firstFreeLocal;
        }
        if (i2 != -1) {
            iArr[i2] = 1;
            if (i > 1) {
                iArr[i2 + 1] = 1;
            }
            if (i > 2) {
                iArr[i2 + 2] = 1;
            }
            if (i2 != this.firstFreeLocal) {
                return (short) i2;
            }
            for (int i4 = i + i2; i4 < 1024; i4++) {
                if (iArr[i4] == 0) {
                    short s = (short) i4;
                    this.firstFreeLocal = s;
                    if (this.localsMax < s) {
                        this.localsMax = s;
                    }
                    return (short) i2;
                }
            }
        }
        throw Context.reportRuntimeError("Program too complex (out of locals)");
    }

    private void incReferenceWordLocal(short s) {
        int[] iArr = this.locals;
        iArr[s] = iArr[s] + 1;
    }

    private void decReferenceWordLocal(short s) {
        this.locals[s] = r0[s] - 1;
    }

    private void releaseWordLocal(short s) {
        if (s < this.firstFreeLocal) {
            this.firstFreeLocal = s;
        }
        this.locals[s] = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* compiled from: Codegen.java */
    /* loaded from: classes2.dex */
    public static class FinallyReturnPoint {
        public List<Integer> jsrPoints = new ArrayList();
        public int tableLabel = 0;

        FinallyReturnPoint() {
        }
    }
}
