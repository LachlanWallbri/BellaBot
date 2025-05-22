package org.mozilla.javascript;

import com.pudutech.lib_update.util.PackageUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.debug.DebugFrame;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class Interpreter extends Icode implements Evaluator {
    static final int EXCEPTION_HANDLER_SLOT = 2;
    static final int EXCEPTION_LOCAL_SLOT = 4;
    static final int EXCEPTION_SCOPE_SLOT = 5;
    static final int EXCEPTION_SLOT_SIZE = 6;
    static final int EXCEPTION_TRY_END_SLOT = 1;
    static final int EXCEPTION_TRY_START_SLOT = 0;
    static final int EXCEPTION_TYPE_SLOT = 3;
    InterpreterData itsData;

    static void dumpICode(InterpreterData interpreterData) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class CallFrame implements Cloneable, Serializable {
        static final long serialVersionUID = -2843792508994958978L;
        DebugFrame debuggerFrame;
        int emptyStackTop;
        InterpretedFunction fnOrScript;
        int frameIndex;
        boolean frozen;
        InterpreterData idata;
        boolean isContinuationsTopFrame;
        int localShift;
        CallFrame parentFrame;

        /* renamed from: pc */
        int f10198pc;
        int pcPrevBranch;
        int pcSourceLineStart;
        Object result;
        double resultDbl;
        double[] sDbl;
        int savedCallOp;
        int savedStackTop;
        Scriptable scope;
        Object[] stack;
        int[] stackAttributes;
        Scriptable thisObj;
        Object throwable;
        boolean useActivation;
        CallFrame varSource;

        private CallFrame() {
        }

        CallFrame cloneFrozen() {
            if (!this.frozen) {
                Kit.codeBug();
            }
            try {
                CallFrame callFrame = (CallFrame) clone();
                callFrame.stack = (Object[]) this.stack.clone();
                callFrame.stackAttributes = (int[]) this.stackAttributes.clone();
                callFrame.sDbl = (double[]) this.sDbl.clone();
                callFrame.frozen = false;
                return callFrame;
            } catch (CloneNotSupportedException unused) {
                throw new IllegalStateException();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static final class ContinuationJump implements Serializable {
        static final long serialVersionUID = 7687739156004308247L;
        CallFrame branchFrame;
        CallFrame capturedFrame;
        Object result;
        double resultDbl;

        ContinuationJump(NativeContinuation nativeContinuation, CallFrame callFrame) {
            CallFrame callFrame2;
            CallFrame callFrame3 = (CallFrame) nativeContinuation.getImplementation();
            this.capturedFrame = callFrame3;
            if (callFrame3 == null || callFrame == null) {
                this.branchFrame = null;
                return;
            }
            int i = callFrame3.frameIndex - callFrame.frameIndex;
            if (i != 0) {
                if (i < 0) {
                    callFrame2 = this.capturedFrame;
                    i = -i;
                } else {
                    callFrame = callFrame3;
                    callFrame2 = callFrame;
                }
                do {
                    callFrame = callFrame.parentFrame;
                    i--;
                } while (i != 0);
                if (callFrame.frameIndex != callFrame2.frameIndex) {
                    Kit.codeBug();
                }
                callFrame = callFrame2;
                callFrame3 = callFrame;
            }
            while (callFrame3 != callFrame && callFrame3 != null) {
                callFrame3 = callFrame3.parentFrame;
                callFrame = callFrame.parentFrame;
            }
            this.branchFrame = callFrame3;
            if (callFrame3 == null || callFrame3.frozen) {
                return;
            }
            Kit.codeBug();
        }
    }

    private static CallFrame captureFrameForGenerator(CallFrame callFrame) {
        callFrame.frozen = true;
        CallFrame cloneFrozen = callFrame.cloneFrozen();
        callFrame.frozen = false;
        cloneFrozen.parentFrame = null;
        cloneFrozen.frameIndex = 0;
        return cloneFrozen;
    }

    @Override // org.mozilla.javascript.Evaluator
    public Object compile(CompilerEnvirons compilerEnvirons, ScriptNode scriptNode, String str, boolean z) {
        InterpreterData compile = new CodeGenerator().compile(compilerEnvirons, scriptNode, str, z);
        this.itsData = compile;
        return compile;
    }

    @Override // org.mozilla.javascript.Evaluator
    public Script createScriptObject(Object obj, Object obj2) {
        if (obj != this.itsData) {
            Kit.codeBug();
        }
        return InterpretedFunction.createScript(this.itsData, obj2);
    }

    @Override // org.mozilla.javascript.Evaluator
    public void setEvalScriptFlag(Script script) {
        ((InterpretedFunction) script).idata.evalScriptFlag = true;
    }

    @Override // org.mozilla.javascript.Evaluator
    public Function createFunctionObject(Context context, Scriptable scriptable, Object obj, Object obj2) {
        if (obj != this.itsData) {
            Kit.codeBug();
        }
        return InterpretedFunction.createFunction(context, scriptable, this.itsData, obj2);
    }

    private static int getShort(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | (bArr[i] << 8);
    }

    private static int getIndex(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    private static int getInt(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | (bArr[i] << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    private static int getExceptionHandler(CallFrame callFrame, boolean z) {
        int[] iArr = callFrame.idata.itsExceptionTable;
        int i = -1;
        if (iArr == null) {
            return -1;
        }
        int i2 = callFrame.f10198pc - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 != iArr.length; i5 += 6) {
            int i6 = iArr[i5 + 0];
            int i7 = iArr[i5 + 1];
            if (i6 <= i2 && i2 < i7 && (!z || iArr[i5 + 3] == 1)) {
                if (i >= 0) {
                    if (i3 >= i7) {
                        if (i4 > i6) {
                            Kit.codeBug();
                        }
                        if (i3 == i7) {
                            Kit.codeBug();
                        }
                    }
                }
                i = i5;
                i4 = i6;
                i3 = i7;
            }
        }
        return i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:18:0x0023. Please report as an issue. */
    private static int bytecodeSpan(int i) {
        if (i != -54 && i != -23) {
            if (i == -21) {
                return 5;
            }
            if (i != 50) {
                if (i != 57) {
                    if (i != 73 && i != 5 && i != 6 && i != 7) {
                        switch (i) {
                            case -63:
                            case -62:
                                break;
                            default:
                                switch (i) {
                                    case -49:
                                    case -48:
                                        break;
                                    case -47:
                                        return 5;
                                    case -46:
                                        return 3;
                                    case -45:
                                        return 2;
                                    default:
                                        switch (i) {
                                            case -40:
                                                return 5;
                                            case -39:
                                                return 3;
                                            case -38:
                                                return 2;
                                            default:
                                                switch (i) {
                                                    case -28:
                                                        return 5;
                                                    case -27:
                                                    case -26:
                                                        return 3;
                                                    default:
                                                        switch (i) {
                                                            case PackageUtils.INSTALL_FAILED_DEXOPT /* -11 */:
                                                            case PackageUtils.INSTALL_FAILED_REPLACE_COULDNT_DELETE /* -10 */:
                                                            case PackageUtils.INSTALL_FAILED_MISSING_SHARED_LIBRARY /* -9 */:
                                                            case PackageUtils.INSTALL_FAILED_SHARED_USER_INCOMPATIBLE /* -8 */:
                                                            case PackageUtils.INSTALL_FAILED_UPDATE_INCOMPATIBLE /* -7 */:
                                                                return 2;
                                                            case -6:
                                                                break;
                                                            default:
                                                                if (validBytecode(i)) {
                                                                    return 1;
                                                                }
                                                                throw Kit.codeBug();
                                                        }
                                                }
                                        }
                                }
                            case -61:
                                return 2;
                        }
                    }
                }
                return 2;
            }
            return 3;
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] getLineNumbers(InterpreterData interpreterData) {
        UintMap uintMap = new UintMap();
        byte[] bArr = interpreterData.itsICode;
        int length = bArr.length;
        int i = 0;
        while (i != length) {
            byte b = bArr[i];
            int bytecodeSpan = bytecodeSpan(b);
            if (b == -26) {
                if (bytecodeSpan != 3) {
                    Kit.codeBug();
                }
                uintMap.put(getIndex(bArr, i + 1), 0);
            }
            i += bytecodeSpan;
        }
        return uintMap.getKeys();
    }

    @Override // org.mozilla.javascript.Evaluator
    public void captureStackInfo(RhinoException rhinoException) {
        CallFrame[] callFrameArr;
        Context currentContext = Context.getCurrentContext();
        if (currentContext == null || currentContext.lastInterpreterFrame == null) {
            rhinoException.interpreterStackInfo = null;
            rhinoException.interpreterLineData = null;
            return;
        }
        if (currentContext.previousInterpreterInvocations == null || currentContext.previousInterpreterInvocations.size() == 0) {
            callFrameArr = new CallFrame[1];
        } else {
            int size = currentContext.previousInterpreterInvocations.size();
            if (currentContext.previousInterpreterInvocations.peek() == currentContext.lastInterpreterFrame) {
                size--;
            }
            callFrameArr = new CallFrame[size + 1];
            currentContext.previousInterpreterInvocations.toArray(callFrameArr);
        }
        callFrameArr[callFrameArr.length - 1] = (CallFrame) currentContext.lastInterpreterFrame;
        int i = 0;
        for (int i2 = 0; i2 != callFrameArr.length; i2++) {
            i += callFrameArr[i2].frameIndex + 1;
        }
        int[] iArr = new int[i];
        int length = callFrameArr.length;
        while (length != 0) {
            length--;
            for (CallFrame callFrame = callFrameArr[length]; callFrame != null; callFrame = callFrame.parentFrame) {
                i--;
                iArr[i] = callFrame.pcSourceLineStart;
            }
        }
        if (i != 0) {
            Kit.codeBug();
        }
        rhinoException.interpreterStackInfo = callFrameArr;
        rhinoException.interpreterLineData = iArr;
    }

    @Override // org.mozilla.javascript.Evaluator
    public String getSourcePositionFromStack(Context context, int[] iArr) {
        CallFrame callFrame = (CallFrame) context.lastInterpreterFrame;
        InterpreterData interpreterData = callFrame.idata;
        if (callFrame.pcSourceLineStart >= 0) {
            iArr[0] = getIndex(interpreterData.itsICode, callFrame.pcSourceLineStart);
        } else {
            iArr[0] = 0;
        }
        return interpreterData.itsSourceFile;
    }

    @Override // org.mozilla.javascript.Evaluator
    public String getPatchedStack(RhinoException rhinoException, String str) {
        char charAt;
        StringBuilder sb = new StringBuilder(str.length() + 1000);
        String systemProperty = SecurityUtilities.getSystemProperty("line.separator");
        CallFrame[] callFrameArr = (CallFrame[]) rhinoException.interpreterStackInfo;
        int[] iArr = rhinoException.interpreterLineData;
        int length = callFrameArr.length;
        int length2 = iArr.length;
        int i = 0;
        while (length != 0) {
            length--;
            int indexOf = str.indexOf("org.mozilla.javascript.Interpreter.interpretLoop", i);
            if (indexOf < 0) {
                break;
            }
            int i2 = indexOf + 48;
            while (i2 != str.length() && (charAt = str.charAt(i2)) != '\n' && charAt != '\r') {
                i2++;
            }
            sb.append(str.substring(i, i2));
            for (CallFrame callFrame = callFrameArr[length]; callFrame != null; callFrame = callFrame.parentFrame) {
                if (length2 == 0) {
                    Kit.codeBug();
                }
                length2--;
                InterpreterData interpreterData = callFrame.idata;
                sb.append(systemProperty);
                sb.append("\tat script");
                if (interpreterData.itsName != null && interpreterData.itsName.length() != 0) {
                    sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                    sb.append(interpreterData.itsName);
                }
                sb.append('(');
                sb.append(interpreterData.itsSourceFile);
                int i3 = iArr[length2];
                if (i3 >= 0) {
                    sb.append(':');
                    sb.append(getIndex(interpreterData.itsICode, i3));
                }
                sb.append(')');
            }
            i = i2;
        }
        sb.append(str.substring(i));
        return sb.toString();
    }

    @Override // org.mozilla.javascript.Evaluator
    public List<String> getScriptStack(RhinoException rhinoException) {
        ScriptStackElement[][] scriptStackElements = getScriptStackElements(rhinoException);
        ArrayList arrayList = new ArrayList(scriptStackElements.length);
        String systemProperty = SecurityUtilities.getSystemProperty("line.separator");
        for (ScriptStackElement[] scriptStackElementArr : scriptStackElements) {
            StringBuilder sb = new StringBuilder();
            for (ScriptStackElement scriptStackElement : scriptStackElementArr) {
                scriptStackElement.renderJavaStyle(sb);
                sb.append(systemProperty);
            }
            arrayList.add(sb.toString());
        }
        return arrayList;
    }

    public ScriptStackElement[][] getScriptStackElements(RhinoException rhinoException) {
        if (rhinoException.interpreterStackInfo == null) {
            return (ScriptStackElement[][]) null;
        }
        ArrayList arrayList = new ArrayList();
        CallFrame[] callFrameArr = (CallFrame[]) rhinoException.interpreterStackInfo;
        int[] iArr = rhinoException.interpreterLineData;
        int length = callFrameArr.length;
        int length2 = iArr.length;
        while (length != 0) {
            length--;
            CallFrame callFrame = callFrameArr[length];
            ArrayList arrayList2 = new ArrayList();
            while (callFrame != null) {
                if (length2 == 0) {
                    Kit.codeBug();
                }
                length2--;
                InterpreterData interpreterData = callFrame.idata;
                String str = interpreterData.itsSourceFile;
                int i = iArr[length2];
                int index = i >= 0 ? getIndex(interpreterData.itsICode, i) : -1;
                String str2 = (interpreterData.itsName == null || interpreterData.itsName.length() == 0) ? null : interpreterData.itsName;
                callFrame = callFrame.parentFrame;
                arrayList2.add(new ScriptStackElement(str, str2, index));
            }
            arrayList.add(arrayList2.toArray(new ScriptStackElement[arrayList2.size()]));
        }
        return (ScriptStackElement[][]) arrayList.toArray(new ScriptStackElement[arrayList.size()]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getEncodedSource(InterpreterData interpreterData) {
        if (interpreterData.encodedSource == null) {
            return null;
        }
        return interpreterData.encodedSource.substring(interpreterData.encodedSourceStart, interpreterData.encodedSourceEnd);
    }

    private static void initFunction(Context context, Scriptable scriptable, InterpretedFunction interpretedFunction, int i) {
        InterpretedFunction createFunction = InterpretedFunction.createFunction(context, scriptable, interpretedFunction, i);
        ScriptRuntime.initFunction(context, scriptable, createFunction, createFunction.idata.itsFunctionType, interpretedFunction.idata.evalScriptFlag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object interpret(InterpretedFunction interpretedFunction, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!ScriptRuntime.hasTopCall(context)) {
            Kit.codeBug();
        }
        if (context.interpreterSecurityDomain != interpretedFunction.securityDomain) {
            Object obj = context.interpreterSecurityDomain;
            context.interpreterSecurityDomain = interpretedFunction.securityDomain;
            try {
                return interpretedFunction.securityController.callWithDomain(interpretedFunction.securityDomain, context, interpretedFunction, scriptable, scriptable2, objArr);
            } finally {
                context.interpreterSecurityDomain = obj;
            }
        }
        CallFrame callFrame = new CallFrame();
        initFrame(context, scriptable, scriptable2, objArr, null, 0, objArr.length, interpretedFunction, null, callFrame);
        callFrame.isContinuationsTopFrame = context.isContinuationsTopCall;
        context.isContinuationsTopCall = false;
        return interpretLoop(context, callFrame, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class GeneratorState {
        int operation;
        RuntimeException returnedException;
        Object value;

        GeneratorState(int i, Object obj) {
            this.operation = i;
            this.value = obj;
        }
    }

    public static Object resumeGenerator(Context context, Scriptable scriptable, int i, Object obj, Object obj2) {
        CallFrame callFrame = (CallFrame) obj;
        GeneratorState generatorState = new GeneratorState(i, obj2);
        if (i == 2) {
            try {
                return interpretLoop(context, callFrame, generatorState);
            } catch (RuntimeException e) {
                if (e != obj2) {
                    throw e;
                }
                return Undefined.instance;
            }
        }
        Object interpretLoop = interpretLoop(context, callFrame, generatorState);
        if (generatorState.returnedException == null) {
            return interpretLoop;
        }
        throw generatorState.returnedException;
    }

    public static Object restartContinuation(NativeContinuation nativeContinuation, Context context, Scriptable scriptable, Object[] objArr) {
        Object obj;
        if (!ScriptRuntime.hasTopCall(context)) {
            return ScriptRuntime.doTopCall(nativeContinuation, context, scriptable, null, objArr, context.isTopLevelStrict);
        }
        if (objArr.length == 0) {
            obj = Undefined.instance;
        } else {
            obj = objArr[0];
        }
        if (((CallFrame) nativeContinuation.getImplementation()) == null) {
            return obj;
        }
        ContinuationJump continuationJump = new ContinuationJump(nativeContinuation, null);
        continuationJump.result = obj;
        return interpretLoop(context, null, continuationJump);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    private static java.lang.Object interpretLoop(org.mozilla.javascript.Context r48, org.mozilla.javascript.Interpreter.CallFrame r49, java.lang.Object r50) {
        /*
            Method dump skipped, instructions count: 7024
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Interpreter.interpretLoop(org.mozilla.javascript.Context, org.mozilla.javascript.Interpreter$CallFrame, java.lang.Object):java.lang.Object");
    }

    private static int doInOrInstanceof(Context context, int i, Object[] objArr, double[] dArr, int i2) {
        boolean instanceOf;
        Object obj = objArr[i2];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        int i3 = i2 - 1;
        Object obj2 = objArr[i3];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        if (i == 52) {
            instanceOf = ScriptRuntime.m4182in(obj2, obj, context);
        } else {
            instanceOf = ScriptRuntime.instanceOf(obj2, obj, context);
        }
        objArr[i3] = ScriptRuntime.wrapBoolean(instanceOf);
        return i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0029, code lost:
    
        if (r2 >= r0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
    
        if (r2 > r0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0034, code lost:
    
        if (r2 <= r0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0039, code lost:
    
        if (r2 < r0) goto L14;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x001f. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int doCompare(CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        boolean cmp_LT;
        double number;
        double d;
        int i3 = i2 - 1;
        int i4 = i3 + 1;
        Object obj = objArr[i4];
        Object obj2 = objArr[i3];
        if (obj == UniqueTag.DOUBLE_MARK) {
            number = dArr[i4];
            d = stack_double(callFrame, i3);
        } else if (obj2 == UniqueTag.DOUBLE_MARK) {
            number = ScriptRuntime.toNumber(obj);
            d = dArr[i3];
        } else {
            switch (i) {
                case 14:
                    cmp_LT = ScriptRuntime.cmp_LT(obj2, obj);
                    break;
                case 15:
                    cmp_LT = ScriptRuntime.cmp_LE(obj2, obj);
                    break;
                case 16:
                    cmp_LT = ScriptRuntime.cmp_LT(obj, obj2);
                    break;
                case 17:
                    cmp_LT = ScriptRuntime.cmp_LE(obj, obj2);
                    break;
                default:
                    throw Kit.codeBug();
            }
            objArr[i3] = ScriptRuntime.wrapBoolean(cmp_LT);
            return i3;
        }
        cmp_LT = false;
        switch (i) {
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
            case 17:
                break;
            default:
                throw Kit.codeBug();
        }
    }

    private static int doBitOp(CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        int stack_int32 = stack_int32(callFrame, i2 - 1);
        int stack_int322 = stack_int32(callFrame, i2);
        int i3 = i2 - 1;
        objArr[i3] = UniqueTag.DOUBLE_MARK;
        if (i == 18) {
            stack_int32 <<= stack_int322;
        } else if (i != 19) {
            switch (i) {
                case 9:
                    stack_int32 |= stack_int322;
                    break;
                case 10:
                    stack_int32 ^= stack_int322;
                    break;
                case 11:
                    stack_int32 &= stack_int322;
                    break;
            }
        } else {
            stack_int32 >>= stack_int322;
        }
        dArr[i3] = stack_int32;
        return i3;
    }

    private static int doDelName(Context context, CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        Object obj = objArr[i2];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        int i3 = i2 - 1;
        Object obj2 = objArr[i3];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        objArr[i3] = ScriptRuntime.delete(obj2, obj, context, callFrame.scope, i == 0);
        return i3;
    }

    private static int doGetElem(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i) {
        Object objectIndex;
        int i2 = i - 1;
        Object obj = objArr[i2];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        int i3 = i2 + 1;
        Object obj2 = objArr[i3];
        if (obj2 != UniqueTag.DOUBLE_MARK) {
            objectIndex = ScriptRuntime.getObjectElem(obj, obj2, context, callFrame.scope);
        } else {
            objectIndex = ScriptRuntime.getObjectIndex(obj, dArr[i3], context, callFrame.scope);
        }
        objArr[i2] = objectIndex;
        return i2;
    }

    private static int doSetElem(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i) {
        Object objectIndex;
        int i2 = i - 2;
        int i3 = i2 + 2;
        Object obj = objArr[i3];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        Object obj2 = obj;
        Object obj3 = objArr[i2];
        if (obj3 == UniqueTag.DOUBLE_MARK) {
            obj3 = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        Object obj4 = obj3;
        int i4 = i2 + 1;
        Object obj5 = objArr[i4];
        if (obj5 != UniqueTag.DOUBLE_MARK) {
            objectIndex = ScriptRuntime.setObjectElem(obj4, obj5, obj2, context, callFrame.scope);
        } else {
            objectIndex = ScriptRuntime.setObjectIndex(obj4, dArr[i4], obj2, context, callFrame.scope);
        }
        objArr[i2] = objectIndex;
        return i2;
    }

    private static int doElemIncDec(Context context, CallFrame callFrame, byte[] bArr, Object[] objArr, double[] dArr, int i) {
        Object obj = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i2 = i - 1;
        Object obj2 = objArr[i2];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        objArr[i2] = ScriptRuntime.elemIncrDecr(obj2, obj, context, callFrame.scope, bArr[callFrame.f10198pc]);
        callFrame.f10198pc++;
        return i2;
    }

    private static int doCallSpecial(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i, byte[] bArr, int i2) {
        int i3;
        int i4 = bArr[callFrame.f10198pc] & 255;
        boolean z = bArr[callFrame.f10198pc + 1] != 0;
        int index = getIndex(bArr, callFrame.f10198pc + 2);
        if (z) {
            i3 = i - i2;
            Object obj = objArr[i3];
            if (obj == UniqueTag.DOUBLE_MARK) {
                obj = ScriptRuntime.wrapNumber(dArr[i3]);
            }
            objArr[i3] = ScriptRuntime.newSpecial(context, obj, getArgsArray(objArr, dArr, i3 + 1, i2), callFrame.scope, i4);
        } else {
            i3 = i - (i2 + 1);
            objArr[i3] = ScriptRuntime.callSpecial(context, (Callable) objArr[i3], (Scriptable) objArr[i3 + 1], getArgsArray(objArr, dArr, i3 + 2, i2), callFrame.scope, callFrame.thisObj, i4, callFrame.idata.itsSourceFile, index);
        }
        callFrame.f10198pc += 4;
        return i3;
    }

    private static int doSetConstVar(CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int[] iArr, int i2) {
        if (!callFrame.useActivation) {
            if ((iArr[i2] & 1) == 0) {
                throw Context.reportRuntimeError1("msg.var.redecl", callFrame.idata.argNames[i2]);
            }
            if ((iArr[i2] & 8) != 0) {
                objArr2[i2] = objArr[i];
                iArr[i2] = iArr[i2] & (-9);
                dArr2[i2] = dArr[i];
            }
        } else {
            Object obj = objArr[i];
            if (obj == UniqueTag.DOUBLE_MARK) {
                obj = ScriptRuntime.wrapNumber(dArr[i]);
            }
            String str = callFrame.idata.argNames[i2];
            if (callFrame.scope instanceof ConstProperties) {
                ((ConstProperties) callFrame.scope).putConst(str, callFrame.scope, obj);
            } else {
                throw Kit.codeBug();
            }
        }
        return i;
    }

    private static int doSetVar(CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int[] iArr, int i2) {
        if (!callFrame.useActivation) {
            if ((iArr[i2] & 1) == 0) {
                objArr2[i2] = objArr[i];
                dArr2[i2] = dArr[i];
            }
        } else {
            Object obj = objArr[i];
            if (obj == UniqueTag.DOUBLE_MARK) {
                obj = ScriptRuntime.wrapNumber(dArr[i]);
            }
            callFrame.scope.put(callFrame.idata.argNames[i2], callFrame.scope, obj);
        }
        return i;
    }

    private static int doGetVar(CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int i2) {
        int i3 = i + 1;
        if (!callFrame.useActivation) {
            objArr[i3] = objArr2[i2];
            dArr[i3] = dArr2[i2];
        } else {
            objArr[i3] = callFrame.scope.get(callFrame.idata.argNames[i2], callFrame.scope);
        }
        return i3;
    }

    private static int doVarIncDec(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int[] iArr, int i2) {
        double number;
        int i3 = i + 1;
        byte b = callFrame.idata.itsICode[callFrame.f10198pc];
        if (!callFrame.useActivation) {
            Object obj = objArr2[i2];
            if (obj == UniqueTag.DOUBLE_MARK) {
                number = dArr2[i2];
            } else {
                number = ScriptRuntime.toNumber(obj);
            }
            double d = (b & 1) == 0 ? 1.0d + number : number - 1.0d;
            boolean z = (b & 2) != 0;
            if ((iArr[i2] & 1) == 0) {
                if (obj != UniqueTag.DOUBLE_MARK) {
                    objArr2[i2] = UniqueTag.DOUBLE_MARK;
                }
                dArr2[i2] = d;
                objArr[i3] = UniqueTag.DOUBLE_MARK;
                if (!z) {
                    number = d;
                }
                dArr[i3] = number;
            } else if (z && obj != UniqueTag.DOUBLE_MARK) {
                objArr[i3] = obj;
            } else {
                objArr[i3] = UniqueTag.DOUBLE_MARK;
                if (!z) {
                    number = d;
                }
                dArr[i3] = number;
            }
        } else {
            objArr[i3] = ScriptRuntime.nameIncrDecr(callFrame.scope, callFrame.idata.argNames[i2], context, b);
        }
        callFrame.f10198pc++;
        return i3;
    }

    private static int doRefMember(Context context, Object[] objArr, double[] dArr, int i, int i2) {
        Object obj = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i3 = i - 1;
        Object obj2 = objArr[i3];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        objArr[i3] = ScriptRuntime.memberRef(obj2, obj, context, i2);
        return i3;
    }

    private static int doRefNsMember(Context context, Object[] objArr, double[] dArr, int i, int i2) {
        Object obj = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i3 = i - 1;
        Object obj2 = objArr[i3];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        int i4 = i3 - 1;
        Object obj3 = objArr[i4];
        if (obj3 == UniqueTag.DOUBLE_MARK) {
            obj3 = ScriptRuntime.wrapNumber(dArr[i4]);
        }
        objArr[i4] = ScriptRuntime.memberRef(obj3, obj2, obj, context, i2);
        return i4;
    }

    private static int doRefNsName(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i, int i2) {
        Object obj = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i3 = i - 1;
        Object obj2 = objArr[i3];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        objArr[i3] = ScriptRuntime.nameRef(obj2, obj, context, callFrame.scope, i2);
        return i3;
    }

    private static CallFrame initFrameForNoSuchMethod(Context context, CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2, int i3, Scriptable scriptable, Scriptable scriptable2, ScriptRuntime.NoSuchMethodShim noSuchMethodShim, InterpretedFunction interpretedFunction) {
        CallFrame callFrame2;
        int i4 = i2 + 2;
        Object[] objArr2 = new Object[i];
        int i5 = 0;
        while (i5 < i) {
            Object obj = objArr[i4];
            if (obj == UniqueTag.DOUBLE_MARK) {
                obj = ScriptRuntime.wrapNumber(dArr[i4]);
            }
            objArr2[i5] = obj;
            i5++;
            i4++;
        }
        Object[] objArr3 = {noSuchMethodShim.methodName, context.newArray(scriptable2, objArr2)};
        CallFrame callFrame3 = new CallFrame();
        if (i3 == -55) {
            CallFrame callFrame4 = callFrame.parentFrame;
            exitFrame(context, callFrame, null);
            callFrame2 = callFrame4;
        } else {
            callFrame2 = callFrame;
        }
        initFrame(context, scriptable2, scriptable, objArr3, null, 0, 2, interpretedFunction, callFrame2, callFrame3);
        if (i3 != -55) {
            callFrame.savedStackTop = i2;
            callFrame.savedCallOp = i3;
        }
        return callFrame3;
    }

    private static boolean doEquals(Object[] objArr, double[] dArr, int i) {
        int i2 = i + 1;
        Object obj = objArr[i2];
        Object obj2 = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            if (obj2 == UniqueTag.DOUBLE_MARK) {
                return dArr[i] == dArr[i2];
            }
            return ScriptRuntime.eqNumber(dArr[i2], obj2);
        }
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.eqNumber(dArr[i], obj);
        }
        return ScriptRuntime.m4181eq(obj2, obj);
    }

    private static boolean doShallowEquals(Object[] objArr, double[] dArr, int i) {
        double d;
        double doubleValue;
        int i2 = i + 1;
        Object obj = objArr[i2];
        Object obj2 = objArr[i];
        UniqueTag uniqueTag = UniqueTag.DOUBLE_MARK;
        if (obj == uniqueTag) {
            doubleValue = dArr[i2];
            if (obj2 == uniqueTag) {
                d = dArr[i];
            } else {
                if (!(obj2 instanceof Number)) {
                    return false;
                }
                d = ((Number) obj2).doubleValue();
            }
        } else if (obj2 == uniqueTag) {
            d = dArr[i];
            if (!(obj instanceof Number)) {
                return false;
            }
            doubleValue = ((Number) obj).doubleValue();
        } else {
            return ScriptRuntime.shallowEq(obj2, obj);
        }
        return d == doubleValue;
    }

    private static CallFrame processThrowable(Context context, Object obj, CallFrame callFrame, int i, boolean z) {
        if (i >= 0) {
            if (callFrame.frozen) {
                callFrame = callFrame.cloneFrozen();
            }
            int[] iArr = callFrame.idata.itsExceptionTable;
            callFrame.f10198pc = iArr[i + 2];
            if (z) {
                callFrame.pcPrevBranch = callFrame.f10198pc;
            }
            callFrame.savedStackTop = callFrame.emptyStackTop;
            int i2 = callFrame.localShift + iArr[i + 5];
            int i3 = callFrame.localShift + iArr[i + 4];
            callFrame.scope = (Scriptable) callFrame.stack[i2];
            callFrame.stack[i3] = obj;
        } else {
            ContinuationJump continuationJump = (ContinuationJump) obj;
            if (continuationJump.branchFrame != callFrame) {
                Kit.codeBug();
            }
            if (continuationJump.capturedFrame == null) {
                Kit.codeBug();
            }
            int i4 = continuationJump.capturedFrame.frameIndex + 1;
            if (continuationJump.branchFrame != null) {
                i4 -= continuationJump.branchFrame.frameIndex;
            }
            CallFrame callFrame2 = continuationJump.capturedFrame;
            CallFrame[] callFrameArr = null;
            int i5 = 0;
            for (int i6 = 0; i6 != i4; i6++) {
                if (!callFrame2.frozen) {
                    Kit.codeBug();
                }
                if (isFrameEnterExitRequired(callFrame2)) {
                    if (callFrameArr == null) {
                        callFrameArr = new CallFrame[i4 - i6];
                    }
                    callFrameArr[i5] = callFrame2;
                    i5++;
                }
                callFrame2 = callFrame2.parentFrame;
            }
            while (i5 != 0) {
                i5--;
                enterFrame(context, callFrameArr[i5], ScriptRuntime.emptyArgs, true);
            }
            callFrame = continuationJump.capturedFrame.cloneFrozen();
            setCallResult(callFrame, continuationJump.result, continuationJump.resultDbl);
        }
        callFrame.throwable = null;
        return callFrame;
    }

    private static Object freezeGenerator(Context context, CallFrame callFrame, int i, GeneratorState generatorState) {
        if (generatorState.operation == 2) {
            throw ScriptRuntime.typeError0("msg.yield.closing");
        }
        callFrame.frozen = true;
        callFrame.result = callFrame.stack[i];
        callFrame.resultDbl = callFrame.sDbl[i];
        callFrame.savedStackTop = i;
        callFrame.f10198pc--;
        ScriptRuntime.exitActivationFunction(context);
        return callFrame.result != UniqueTag.DOUBLE_MARK ? callFrame.result : ScriptRuntime.wrapNumber(callFrame.resultDbl);
    }

    private static Object thawGenerator(CallFrame callFrame, int i, GeneratorState generatorState, int i2) {
        callFrame.frozen = false;
        int index = getIndex(callFrame.idata.itsICode, callFrame.f10198pc);
        callFrame.f10198pc += 2;
        if (generatorState.operation == 1) {
            return new JavaScriptException(generatorState.value, callFrame.idata.itsSourceFile, index);
        }
        if (generatorState.operation == 2) {
            return generatorState.value;
        }
        if (generatorState.operation != 0) {
            throw Kit.codeBug();
        }
        if (i2 == 73) {
            callFrame.stack[i] = generatorState.value;
        }
        return Scriptable.NOT_FOUND;
    }

    private static CallFrame initFrameForApplyOrCall(Context context, CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2, int i3, Scriptable scriptable, IdFunctionObject idFunctionObject, InterpretedFunction interpretedFunction) {
        Scriptable scriptable2;
        Object[] applyArguments;
        CallFrame callFrame2 = callFrame;
        if (i != 0) {
            int i4 = i2 + 2;
            Object obj = objArr[i4];
            if (obj == UniqueTag.DOUBLE_MARK) {
                obj = ScriptRuntime.wrapNumber(dArr[i4]);
            }
            scriptable2 = ScriptRuntime.toObjectOrNull(context, obj, callFrame2.scope);
        } else {
            scriptable2 = null;
        }
        if (scriptable2 == null) {
            scriptable2 = ScriptRuntime.getTopCallScope(context);
        }
        if (i3 == -55) {
            exitFrame(context, callFrame, null);
            callFrame2 = callFrame2.parentFrame;
        } else {
            callFrame2.savedStackTop = i2;
            callFrame2.savedCallOp = i3;
        }
        CallFrame callFrame3 = callFrame2;
        CallFrame callFrame4 = new CallFrame();
        if (BaseFunction.isApply(idFunctionObject)) {
            if (i < 2) {
                applyArguments = ScriptRuntime.emptyArgs;
            } else {
                applyArguments = ScriptRuntime.getApplyArguments(context, objArr[i2 + 3]);
            }
            Object[] objArr2 = applyArguments;
            initFrame(context, scriptable, scriptable2, objArr2, null, 0, objArr2.length, interpretedFunction, callFrame3, callFrame4);
        } else {
            for (int i5 = 1; i5 < i; i5++) {
                int i6 = i2 + 1 + i5;
                int i7 = i2 + 2 + i5;
                objArr[i6] = objArr[i7];
                dArr[i6] = dArr[i7];
            }
            initFrame(context, scriptable, scriptable2, objArr, dArr, i2 + 2, i < 2 ? 0 : i - 1, interpretedFunction, callFrame3, callFrame4);
        }
        return callFrame4;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void initFrame(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, double[] dArr, int i, int i2, InterpretedFunction interpretedFunction, CallFrame callFrame, CallFrame callFrame2) {
        DebugFrame debugFrame;
        Object[] objArr2;
        double[] dArr2;
        int i3;
        Scriptable scriptable3;
        Scriptable scriptable4;
        int i4;
        int i5;
        Object[] objArr3;
        boolean z;
        int[] iArr;
        double[] dArr3;
        int paramAndVarCount;
        int i6;
        int i7;
        int i8;
        InterpreterData interpreterData = interpretedFunction.idata;
        boolean z2 = interpreterData.itsNeedsActivation;
        if (context.debugger != null) {
            debugFrame = context.debugger.getFrame(context, interpreterData);
            if (debugFrame != null) {
                z2 = true;
            }
        } else {
            debugFrame = null;
        }
        if (z2) {
            objArr2 = dArr != null ? getArgsArray(objArr, dArr, i, i2) : objArr;
            dArr2 = null;
            i3 = 0;
        } else {
            objArr2 = objArr;
            dArr2 = dArr;
            i3 = i;
        }
        if (interpreterData.itsFunctionType != 0) {
            scriptable3 = interpretedFunction.getParentScope();
            if (z2) {
                if (interpreterData.itsFunctionType == 4) {
                    scriptable4 = ScriptRuntime.createArrowFunctionActivation(interpretedFunction, scriptable3, objArr2, interpreterData.isStrict);
                } else {
                    scriptable4 = ScriptRuntime.createFunctionActivation(interpretedFunction, scriptable3, objArr2, interpreterData.isStrict);
                }
                if (interpreterData.itsNestedFunctions != null) {
                    if (interpreterData.itsFunctionType != 0 && !interpreterData.itsNeedsActivation) {
                        Kit.codeBug();
                    }
                    for (int i9 = 0; i9 < interpreterData.itsNestedFunctions.length; i9++) {
                        if (interpreterData.itsNestedFunctions[i9].itsFunctionType == 1) {
                            initFunction(context, scriptable4, interpretedFunction, i9);
                        }
                    }
                }
                i4 = (interpreterData.itsMaxVars + interpreterData.itsMaxLocals) - 1;
                i5 = interpreterData.itsMaxFrameArray;
                if (i5 != interpreterData.itsMaxStack + i4 + 1) {
                    Kit.codeBug();
                }
                if (callFrame2.stack == null && i5 <= callFrame2.stack.length) {
                    objArr3 = callFrame2.stack;
                    iArr = callFrame2.stackAttributes;
                    dArr3 = callFrame2.sDbl;
                    z = true;
                } else {
                    objArr3 = new Object[i5];
                    int[] iArr2 = new int[i5];
                    double[] dArr4 = new double[i5];
                    z = false;
                    iArr = iArr2;
                    dArr3 = dArr4;
                }
                paramAndVarCount = interpreterData.getParamAndVarCount();
                double[] dArr5 = dArr2;
                for (i6 = 0; i6 < paramAndVarCount; i6++) {
                    if (interpreterData.getParamOrVarConst(i6)) {
                        iArr[i6] = 13;
                    }
                }
                i7 = interpreterData.argCount;
                i8 = i2;
                if (i7 <= i8) {
                    i8 = i7;
                }
                callFrame2.parentFrame = callFrame;
                callFrame2.frameIndex = callFrame != null ? 0 : callFrame.frameIndex + 1;
                if (callFrame2.frameIndex <= context.getMaximumInterpreterStackDepth()) {
                    throw Context.reportRuntimeError("Exceeded maximum stack depth");
                }
                callFrame2.frozen = false;
                callFrame2.fnOrScript = interpretedFunction;
                callFrame2.idata = interpreterData;
                callFrame2.stack = objArr3;
                callFrame2.stackAttributes = iArr;
                callFrame2.sDbl = dArr3;
                callFrame2.varSource = callFrame2;
                callFrame2.localShift = interpreterData.itsMaxVars;
                callFrame2.emptyStackTop = i4;
                callFrame2.debuggerFrame = debugFrame;
                callFrame2.useActivation = z2;
                callFrame2.thisObj = scriptable2;
                callFrame2.result = Undefined.instance;
                callFrame2.f10198pc = 0;
                callFrame2.pcPrevBranch = 0;
                callFrame2.pcSourceLineStart = interpreterData.firstLinePC;
                callFrame2.scope = scriptable4;
                callFrame2.savedStackTop = i4;
                callFrame2.savedCallOp = 0;
                System.arraycopy(objArr2, i3, objArr3, 0, i8);
                if (dArr5 != null) {
                    System.arraycopy(dArr5, i3, dArr3, 0, i8);
                }
                while (i8 != interpreterData.itsMaxVars) {
                    objArr3[i8] = Undefined.instance;
                    i8++;
                }
                if (z) {
                    for (int i10 = i4 + 1; i10 != objArr3.length; i10++) {
                        objArr3[i10] = null;
                    }
                }
                enterFrame(context, callFrame2, objArr2, false);
                return;
            }
        } else {
            scriptable3 = scriptable;
            ScriptRuntime.initScript(interpretedFunction, scriptable2, context, scriptable3, interpretedFunction.idata.evalScriptFlag);
        }
        scriptable4 = scriptable3;
        if (interpreterData.itsNestedFunctions != null) {
        }
        i4 = (interpreterData.itsMaxVars + interpreterData.itsMaxLocals) - 1;
        i5 = interpreterData.itsMaxFrameArray;
        if (i5 != interpreterData.itsMaxStack + i4 + 1) {
        }
        if (callFrame2.stack == null) {
        }
        objArr3 = new Object[i5];
        int[] iArr22 = new int[i5];
        double[] dArr42 = new double[i5];
        z = false;
        iArr = iArr22;
        dArr3 = dArr42;
        paramAndVarCount = interpreterData.getParamAndVarCount();
        double[] dArr52 = dArr2;
        while (i6 < paramAndVarCount) {
        }
        i7 = interpreterData.argCount;
        i8 = i2;
        if (i7 <= i8) {
        }
        callFrame2.parentFrame = callFrame;
        callFrame2.frameIndex = callFrame != null ? 0 : callFrame.frameIndex + 1;
        if (callFrame2.frameIndex <= context.getMaximumInterpreterStackDepth()) {
        }
    }

    private static boolean isFrameEnterExitRequired(CallFrame callFrame) {
        return callFrame.debuggerFrame != null || callFrame.idata.itsNeedsActivation;
    }

    private static void enterFrame(Context context, CallFrame callFrame, Object[] objArr, boolean z) {
        boolean z2 = callFrame.idata.itsNeedsActivation;
        boolean z3 = callFrame.debuggerFrame != null;
        if (z2 || z3) {
            Scriptable scriptable = callFrame.scope;
            if (scriptable == null) {
                Kit.codeBug();
            } else if (z) {
                while (scriptable instanceof NativeWith) {
                    scriptable = scriptable.getParentScope();
                    if (scriptable == null || (callFrame.parentFrame != null && callFrame.parentFrame.scope == scriptable)) {
                        Kit.codeBug();
                        break;
                    }
                }
            }
            if (z3) {
                callFrame.debuggerFrame.onEnter(context, scriptable, callFrame.thisObj, objArr);
            }
            if (z2) {
                ScriptRuntime.enterActivationFunction(context, scriptable);
            }
        }
    }

    private static void exitFrame(Context context, CallFrame callFrame, Object obj) {
        Object obj2;
        double d;
        if (callFrame.idata.itsNeedsActivation) {
            ScriptRuntime.exitActivationFunction(context);
        }
        if (callFrame.debuggerFrame != null) {
            try {
                if (obj instanceof Throwable) {
                    callFrame.debuggerFrame.onExit(context, true, obj);
                    return;
                }
                ContinuationJump continuationJump = (ContinuationJump) obj;
                if (continuationJump == null) {
                    obj2 = callFrame.result;
                } else {
                    obj2 = continuationJump.result;
                }
                if (obj2 == UniqueTag.DOUBLE_MARK) {
                    if (continuationJump == null) {
                        d = callFrame.resultDbl;
                    } else {
                        d = continuationJump.resultDbl;
                    }
                    obj2 = ScriptRuntime.wrapNumber(d);
                }
                callFrame.debuggerFrame.onExit(context, false, obj2);
            } catch (Throwable th) {
                System.err.println("RHINO USAGE WARNING: onExit terminated with exception");
                th.printStackTrace(System.err);
            }
        }
    }

    private static void setCallResult(CallFrame callFrame, Object obj, double d) {
        if (callFrame.savedCallOp == 38) {
            callFrame.stack[callFrame.savedStackTop] = obj;
            callFrame.sDbl[callFrame.savedStackTop] = d;
        } else if (callFrame.savedCallOp == 30) {
            if (obj instanceof Scriptable) {
                callFrame.stack[callFrame.savedStackTop] = obj;
            }
        } else {
            Kit.codeBug();
        }
        callFrame.savedCallOp = 0;
    }

    public static NativeContinuation captureContinuation(Context context) {
        if (context.lastInterpreterFrame == null || !(context.lastInterpreterFrame instanceof CallFrame)) {
            throw new IllegalStateException("Interpreter frames not found");
        }
        return captureContinuation(context, (CallFrame) context.lastInterpreterFrame, true);
    }

    private static NativeContinuation captureContinuation(Context context, CallFrame callFrame, boolean z) {
        NativeContinuation nativeContinuation = new NativeContinuation();
        ScriptRuntime.setObjectProtoAndParent(nativeContinuation, ScriptRuntime.getTopCallScope(context));
        CallFrame callFrame2 = callFrame;
        CallFrame callFrame3 = callFrame2;
        while (callFrame2 != null && !callFrame2.frozen) {
            callFrame2.frozen = true;
            for (int i = callFrame2.savedStackTop + 1; i != callFrame2.stack.length; i++) {
                callFrame2.stack[i] = null;
                callFrame2.stackAttributes[i] = 0;
            }
            if (callFrame2.savedCallOp == 38) {
                callFrame2.stack[callFrame2.savedStackTop] = null;
            } else if (callFrame2.savedCallOp != 30) {
                Kit.codeBug();
            }
            callFrame3 = callFrame2;
            callFrame2 = callFrame2.parentFrame;
        }
        if (z) {
            while (callFrame3.parentFrame != null) {
                callFrame3 = callFrame3.parentFrame;
            }
            if (!callFrame3.isContinuationsTopFrame) {
                throw new IllegalStateException("Cannot capture continuation from JavaScript code not called directly by executeScriptWithContinuations or callFunctionWithContinuations");
            }
        }
        nativeContinuation.initImplementation(callFrame);
        return nativeContinuation;
    }

    private static int stack_int32(CallFrame callFrame, int i) {
        Object obj = callFrame.stack[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.toInt32(callFrame.sDbl[i]);
        }
        return ScriptRuntime.toInt32(obj);
    }

    private static double stack_double(CallFrame callFrame, int i) {
        Object obj = callFrame.stack[i];
        if (obj != UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.toNumber(obj);
        }
        return callFrame.sDbl[i];
    }

    private static boolean stack_boolean(CallFrame callFrame, int i) {
        Object obj = callFrame.stack[i];
        if (obj == Boolean.TRUE) {
            return true;
        }
        if (obj == Boolean.FALSE) {
            return false;
        }
        if (obj == UniqueTag.DOUBLE_MARK) {
            double d = callFrame.sDbl[i];
            return d == d && d != 0.0d;
        }
        if (obj == null || obj == Undefined.instance) {
            return false;
        }
        if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            return doubleValue == doubleValue && doubleValue != 0.0d;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return ScriptRuntime.toBoolean(obj);
    }

    private static void doAdd(Object[] objArr, double[] dArr, int i, Context context) {
        boolean z;
        double d;
        int i2 = i + 1;
        Object obj = objArr[i2];
        Object obj2 = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            d = dArr[i2];
            if (obj2 == UniqueTag.DOUBLE_MARK) {
                dArr[i] = dArr[i] + d;
                return;
            }
            z = true;
        } else if (obj2 == UniqueTag.DOUBLE_MARK) {
            z = false;
            obj2 = obj;
            d = dArr[i];
        } else {
            if ((obj2 instanceof Scriptable) || (obj instanceof Scriptable)) {
                objArr[i] = ScriptRuntime.add(obj2, obj, context);
                return;
            }
            if ((obj2 instanceof CharSequence) || (obj instanceof CharSequence)) {
                objArr[i] = new ConsString(ScriptRuntime.toCharSequence(obj2), ScriptRuntime.toCharSequence(obj));
                return;
            }
            double doubleValue = obj2 instanceof Number ? ((Number) obj2).doubleValue() : ScriptRuntime.toNumber(obj2);
            double doubleValue2 = obj instanceof Number ? ((Number) obj).doubleValue() : ScriptRuntime.toNumber(obj);
            objArr[i] = UniqueTag.DOUBLE_MARK;
            dArr[i] = doubleValue + doubleValue2;
            return;
        }
        if (obj2 instanceof Scriptable) {
            Object wrapNumber = ScriptRuntime.wrapNumber(d);
            if (!z) {
                Object obj3 = obj2;
                obj2 = wrapNumber;
                wrapNumber = obj3;
            }
            objArr[i] = ScriptRuntime.add(obj2, wrapNumber, context);
            return;
        }
        if (obj2 instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj2;
            CharSequence charSequence2 = ScriptRuntime.toCharSequence(Double.valueOf(d));
            if (z) {
                objArr[i] = new ConsString(charSequence, charSequence2);
                return;
            } else {
                objArr[i] = new ConsString(charSequence2, charSequence);
                return;
            }
        }
        double doubleValue3 = obj2 instanceof Number ? ((Number) obj2).doubleValue() : ScriptRuntime.toNumber(obj2);
        objArr[i] = UniqueTag.DOUBLE_MARK;
        dArr[i] = doubleValue3 + d;
    }

    private static int doArithmetic(CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        double stack_double = stack_double(callFrame, i2);
        int i3 = i2 - 1;
        double stack_double2 = stack_double(callFrame, i3);
        objArr[i3] = UniqueTag.DOUBLE_MARK;
        switch (i) {
            case 22:
                stack_double2 -= stack_double;
                break;
            case 23:
                stack_double2 *= stack_double;
                break;
            case 24:
                stack_double2 /= stack_double;
                break;
            case 25:
                stack_double2 %= stack_double;
                break;
        }
        dArr[i3] = stack_double2;
        return i3;
    }

    private static Object[] getArgsArray(Object[] objArr, double[] dArr, int i, int i2) {
        if (i2 == 0) {
            return ScriptRuntime.emptyArgs;
        }
        Object[] objArr2 = new Object[i2];
        int i3 = 0;
        while (i3 != i2) {
            Object obj = objArr[i];
            if (obj == UniqueTag.DOUBLE_MARK) {
                obj = ScriptRuntime.wrapNumber(dArr[i]);
            }
            objArr2[i3] = obj;
            i3++;
            i++;
        }
        return objArr2;
    }

    private static void addInstructionCount(Context context, CallFrame callFrame, int i) {
        context.instructionCount += (callFrame.f10198pc - callFrame.pcPrevBranch) + i;
        if (context.instructionCount > context.instructionThreshold) {
            context.observeInstructionCount(context.instructionCount);
            context.instructionCount = 0;
        }
    }
}
