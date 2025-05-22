package org.mozilla.javascript.regexp;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.mirsdk.compat.topo.MapElement;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NativeRegExp extends IdScriptableObject implements Function {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ANCHOR_BOL = -2;
    private static final int INDEX_LEN = 2;
    private static final int Id_compile = 1;
    private static final int Id_exec = 4;
    private static final int Id_global = 3;
    private static final int Id_ignoreCase = 4;
    private static final int Id_lastIndex = 1;
    private static final int Id_multiline = 5;
    private static final int Id_prefix = 6;
    private static final int Id_source = 2;
    private static final int Id_test = 5;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    public static final int JSREG_FOLD = 2;
    public static final int JSREG_GLOB = 1;
    public static final int JSREG_MULTILINE = 4;
    public static final int MATCH = 1;
    private static final int MAX_INSTANCE_ID = 5;
    private static final int MAX_PROTOTYPE_ID = 6;
    public static final int PREFIX = 2;
    private static final Object REGEXP_TAG = new Object();
    private static final byte REOP_ALNUM = 9;
    private static final byte REOP_ALT = 31;
    private static final byte REOP_ALTPREREQ = 53;
    private static final byte REOP_ALTPREREQ2 = 55;
    private static final byte REOP_ALTPREREQi = 54;
    private static final byte REOP_ASSERT = 41;
    private static final byte REOP_ASSERTNOTTEST = 44;
    private static final byte REOP_ASSERTTEST = 43;
    private static final byte REOP_ASSERT_NOT = 42;
    private static final byte REOP_BACKREF = 13;
    private static final byte REOP_BOL = 2;
    private static final byte REOP_CLASS = 22;
    private static final byte REOP_DIGIT = 7;
    private static final byte REOP_DOT = 6;
    private static final byte REOP_EMPTY = 1;
    private static final byte REOP_END = 57;
    private static final byte REOP_ENDCHILD = 49;
    private static final byte REOP_EOL = 3;
    private static final byte REOP_FLAT = 14;
    private static final byte REOP_FLAT1 = 15;
    private static final byte REOP_FLAT1i = 17;
    private static final byte REOP_FLATi = 16;
    private static final byte REOP_JUMP = 32;
    private static final byte REOP_LPAREN = 29;
    private static final byte REOP_MINIMALOPT = 47;
    private static final byte REOP_MINIMALPLUS = 46;
    private static final byte REOP_MINIMALQUANT = 48;
    private static final byte REOP_MINIMALREPEAT = 52;
    private static final byte REOP_MINIMALSTAR = 45;
    private static final byte REOP_NCLASS = 23;
    private static final byte REOP_NONALNUM = 10;
    private static final byte REOP_NONDIGIT = 8;
    private static final byte REOP_NONSPACE = 12;
    private static final byte REOP_OPT = 28;
    private static final byte REOP_PLUS = 27;
    private static final byte REOP_QUANT = 25;
    private static final byte REOP_REPEAT = 51;
    private static final byte REOP_RPAREN = 30;
    private static final byte REOP_SIMPLE_END = 23;
    private static final byte REOP_SIMPLE_START = 1;
    private static final byte REOP_SPACE = 11;
    private static final byte REOP_STAR = 26;
    private static final byte REOP_UCFLAT1 = 18;
    private static final byte REOP_UCFLAT1i = 19;
    private static final byte REOP_WBDRY = 4;
    private static final byte REOP_WNONBDRY = 5;
    public static final int TEST = 0;
    private static final boolean debug = false;
    static final long serialVersionUID = 4965263491464903264L;
    Object lastIndex;
    private int lastIndexAttr;

    /* renamed from: re */
    private RECompiled f10256re;

    private static boolean isControlLetter(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean reopIsSimple(int i) {
        return i >= 1 && i <= 23;
    }

    private static int toASCIIHexDigit(int i) {
        if (i < 48) {
            return -1;
        }
        if (i <= 57) {
            return i - 48;
        }
        int i2 = i | 32;
        if (97 > i2 || i2 > 102) {
            return -1;
        }
        return (i2 - 97) + 10;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "RegExp";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public int getMaxInstanceId() {
        return 5;
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public String getTypeOf() {
        return "object";
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        NativeRegExp nativeRegExp = new NativeRegExp();
        nativeRegExp.f10256re = compileRE(context, "", null, false);
        nativeRegExp.activatePrototypeMap(6);
        nativeRegExp.setParentScope(scriptable);
        nativeRegExp.setPrototype(getObjectPrototype(scriptable));
        NativeRegExpCtor nativeRegExpCtor = new NativeRegExpCtor();
        nativeRegExp.defineProperty("constructor", nativeRegExpCtor, 2);
        ScriptRuntime.setFunctionProtoAndParent(nativeRegExpCtor, scriptable);
        nativeRegExpCtor.setImmunePrototypeProperty(nativeRegExp);
        if (z) {
            nativeRegExp.sealObject();
            nativeRegExpCtor.sealObject();
        }
        defineProperty(scriptable, "RegExp", nativeRegExpCtor, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeRegExp(Scriptable scriptable, RECompiled rECompiled) {
        Double valueOf = Double.valueOf(0.0d);
        this.lastIndex = valueOf;
        this.lastIndexAttr = 6;
        this.f10256re = rECompiled;
        this.lastIndex = valueOf;
        ScriptRuntime.setBuiltinProtoAndParent(this, scriptable, TopLevel.Builtins.RegExp);
    }

    @Override // org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return execSub(context, scriptable, objArr, 1);
    }

    @Override // org.mozilla.javascript.Function
    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        return (Scriptable) execSub(context, scriptable, objArr, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Scriptable compile(Context context, Scriptable scriptable, Object[] objArr) {
        if (objArr.length > 0 && (objArr[0] instanceof NativeRegExp)) {
            if (objArr.length > 1 && objArr[1] != Undefined.instance) {
                throw ScriptRuntime.typeError0("msg.bad.regexp.compile");
            }
            NativeRegExp nativeRegExp = (NativeRegExp) objArr[0];
            this.f10256re = nativeRegExp.f10256re;
            this.lastIndex = nativeRegExp.lastIndex;
            return this;
        }
        this.f10256re = compileRE(context, (objArr.length == 0 || (objArr[0] instanceof Undefined)) ? "" : escapeRegExp(objArr[0]), (objArr.length <= 1 || objArr[1] == Undefined.instance) ? null : ScriptRuntime.toString(objArr[1]), false);
        this.lastIndex = Double.valueOf(0.0d);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('/');
        if (this.f10256re.source.length != 0) {
            sb.append(this.f10256re.source);
        } else {
            sb.append("(?:)");
        }
        sb.append('/');
        if ((this.f10256re.flags & 1) != 0) {
            sb.append('g');
        }
        if ((this.f10256re.flags & 2) != 0) {
            sb.append('i');
        }
        if ((this.f10256re.flags & 4) != 0) {
            sb.append('m');
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeRegExp() {
        this.lastIndex = Double.valueOf(0.0d);
        this.lastIndexAttr = 6;
    }

    private static RegExpImpl getImpl(Context context) {
        return (RegExpImpl) ScriptRuntime.getRegExpProxy(context);
    }

    private static String escapeRegExp(Object obj) {
        String scriptRuntime = ScriptRuntime.toString(obj);
        StringBuilder sb = null;
        int i = 0;
        for (int indexOf = scriptRuntime.indexOf(47); indexOf > -1; indexOf = scriptRuntime.indexOf(47, indexOf + 1)) {
            if (indexOf == i || scriptRuntime.charAt(indexOf - 1) != '\\') {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append((CharSequence) scriptRuntime, i, indexOf);
                sb.append("\\/");
                i = indexOf + 1;
            }
        }
        if (sb == null) {
            return scriptRuntime;
        }
        sb.append((CharSequence) scriptRuntime, i, scriptRuntime.length());
        return sb.toString();
    }

    private Object execSub(Context context, Scriptable scriptable, Object[] objArr, int i) {
        String scriptRuntime;
        RegExpImpl impl = getImpl(context);
        if (objArr.length == 0) {
            scriptRuntime = impl.input;
            if (scriptRuntime == null) {
                scriptRuntime = ScriptRuntime.toString(Undefined.instance);
            }
        } else {
            scriptRuntime = ScriptRuntime.toString(objArr[0]);
        }
        String str = scriptRuntime;
        double d = 0.0d;
        double integer = (this.f10256re.flags & 1) != 0 ? ScriptRuntime.toInteger(this.lastIndex) : 0.0d;
        if (integer < 0.0d || str.length() < integer) {
            this.lastIndex = Double.valueOf(0.0d);
            return null;
        }
        int[] iArr = {(int) integer};
        Object executeRegExp = executeRegExp(context, scriptable, impl, str, iArr, i);
        if ((this.f10256re.flags & 1) == 0) {
            return executeRegExp;
        }
        if (executeRegExp != null && executeRegExp != Undefined.instance) {
            d = iArr[0];
        }
        this.lastIndex = Double.valueOf(d);
        return executeRegExp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RECompiled compileRE(Context context, String str, String str2, boolean z) {
        int i;
        int i2;
        RECompiled rECompiled = new RECompiled(str);
        int length = str.length();
        if (str2 != null) {
            i = 0;
            for (int i3 = 0; i3 < str2.length(); i3++) {
                char charAt = str2.charAt(i3);
                if (charAt == 'g') {
                    i2 = 1;
                } else if (charAt == 'i') {
                    i2 = 2;
                } else if (charAt == 'm') {
                    i2 = 4;
                } else {
                    reportError("msg.invalid.re.flag", String.valueOf(charAt));
                    i2 = 0;
                }
                if ((i & i2) != 0) {
                    reportError("msg.invalid.re.flag", String.valueOf(charAt));
                }
                i |= i2;
            }
        } else {
            i = 0;
        }
        rECompiled.flags = i;
        CompilerState compilerState = new CompilerState(context, rECompiled.source, length, i);
        if (z && length > 0) {
            compilerState.result = new RENode((byte) 14);
            compilerState.result.chr = compilerState.cpbegin[0];
            compilerState.result.length = length;
            compilerState.result.flatIndex = 0;
            compilerState.progLength += 5;
        } else {
            if (!parseDisjunction(compilerState)) {
                return null;
            }
            if (compilerState.maxBackReference > compilerState.parenCount) {
                compilerState = new CompilerState(context, rECompiled.source, length, i);
                compilerState.backReferenceLimit = compilerState.parenCount;
                if (!parseDisjunction(compilerState)) {
                    return null;
                }
            }
        }
        rECompiled.program = new byte[compilerState.progLength + 1];
        if (compilerState.classCount != 0) {
            rECompiled.classList = new RECharSet[compilerState.classCount];
            rECompiled.classCount = compilerState.classCount;
        }
        rECompiled.program[emitREBytecode(compilerState, rECompiled, 0, compilerState.result)] = REOP_END;
        rECompiled.parenCount = compilerState.parenCount;
        byte b = rECompiled.program[0];
        if (b == 2) {
            rECompiled.anchorCh = -2;
        } else if (b != 31) {
            switch (b) {
                case 14:
                case 16:
                    rECompiled.anchorCh = rECompiled.source[getIndex(rECompiled.program, 1)];
                    break;
                case 15:
                case 17:
                    rECompiled.anchorCh = (char) (rECompiled.program[1] & 255);
                    break;
                case 18:
                case 19:
                    rECompiled.anchorCh = (char) getIndex(rECompiled.program, 1);
                    break;
            }
        } else {
            RENode rENode = compilerState.result;
            if (rENode.kid.f10261op == 2 && rENode.kid2.f10261op == 2) {
                rECompiled.anchorCh = -2;
            }
        }
        return rECompiled;
    }

    private static boolean isWord(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || isDigit(c) || c == '_';
    }

    private static boolean isLineTerm(char c) {
        return ScriptRuntime.isJSLineTerminator(c);
    }

    private static boolean isREWhiteSpace(int i) {
        return ScriptRuntime.isJSWhitespaceOrLineTerminator(i);
    }

    private static char upcase(char c) {
        if (c < 128) {
            return ('a' > c || c > 'z') ? c : (char) (c - ' ');
        }
        char upperCase = Character.toUpperCase(c);
        return upperCase < 128 ? c : upperCase;
    }

    private static char downcase(char c) {
        if (c < 128) {
            return ('A' > c || c > 'Z') ? c : (char) (c + ' ');
        }
        char lowerCase = Character.toLowerCase(c);
        return lowerCase < 128 ? c : lowerCase;
    }

    private static boolean parseDisjunction(CompilerState compilerState) {
        if (!parseAlternative(compilerState)) {
            return false;
        }
        char[] cArr = compilerState.cpbegin;
        int i = compilerState.f10254cp;
        if (i != cArr.length && cArr[i] == '|') {
            compilerState.f10254cp++;
            RENode rENode = new RENode((byte) 31);
            rENode.kid = compilerState.result;
            if (!parseDisjunction(compilerState)) {
                return false;
            }
            rENode.kid2 = compilerState.result;
            compilerState.result = rENode;
            if (rENode.kid.f10261op == 14 && rENode.kid2.f10261op == 14) {
                rENode.f10261op = (compilerState.flags & 2) == 0 ? (byte) 53 : (byte) 54;
                rENode.chr = rENode.kid.chr;
                rENode.index = rENode.kid2.chr;
                compilerState.progLength += 13;
            } else if (rENode.kid.f10261op == 22 && rENode.kid.index < 256 && rENode.kid2.f10261op == 14 && (compilerState.flags & 2) == 0) {
                rENode.f10261op = (byte) 55;
                rENode.chr = rENode.kid2.chr;
                rENode.index = rENode.kid.index;
                compilerState.progLength += 13;
            } else if (rENode.kid.f10261op == 14 && rENode.kid2.f10261op == 22 && rENode.kid2.index < 256 && (compilerState.flags & 2) == 0) {
                rENode.f10261op = (byte) 55;
                rENode.chr = rENode.kid.chr;
                rENode.index = rENode.kid2.index;
                compilerState.progLength += 13;
            } else {
                compilerState.progLength += 9;
            }
        }
        return true;
    }

    private static boolean parseAlternative(CompilerState compilerState) {
        char[] cArr = compilerState.cpbegin;
        RENode rENode = null;
        RENode rENode2 = null;
        while (compilerState.f10254cp != compilerState.cpend && cArr[compilerState.f10254cp] != '|' && (compilerState.parenNesting == 0 || cArr[compilerState.f10254cp] != ')')) {
            if (!parseTerm(compilerState)) {
                return false;
            }
            if (rENode == null) {
                rENode = compilerState.result;
                rENode2 = rENode;
            } else {
                rENode2.next = compilerState.result;
            }
            while (rENode2.next != null) {
                rENode2 = rENode2.next;
            }
        }
        if (rENode == null) {
            compilerState.result = new RENode((byte) 1);
        } else {
            compilerState.result = rENode;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0104, code lost:
    
        reportError("msg.bad.range", "");
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0107, code lost:
    
        return false;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:53:0x004a. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:54:0x004d. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:55:0x0050. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x001c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ce A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0070 A[LOOP:1: B:59:0x005d->B:63:0x0070, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x006b A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v7, types: [int] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [int] */
    /* JADX WARN: Type inference failed for: r14v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean calculateBitmapSize(CompilerState compilerState, RENode rENode, char[] cArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        ?? r14;
        CompilerState compilerState2;
        char c;
        int i6 = i;
        rENode.bmsize = 0;
        rENode.sense = true;
        if (i6 == i2) {
            return true;
        }
        if (cArr[i6] == '^') {
            i6++;
            rENode.sense = false;
        }
        char c2 = 0;
        boolean z = false;
        char c3 = 0;
        while (i6 != i2) {
            char c4 = '\\';
            char c5 = '\\';
            c4 = '\\';
            if (cArr[i6] == '\\') {
                int i7 = i6 + 1;
                i3 = i7 + 1;
                char c6 = cArr[i7];
                if (c6 != 'D' && c6 != 'S' && c6 != 'W') {
                    if (c6 != 'f') {
                        char c7 = c6;
                        if (c6 != 'n') {
                            switch (c6) {
                                case '0':
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                    ?? r1 = c6 - '0';
                                    char c8 = cArr[i3];
                                    c7 = r1;
                                    if ('0' <= c8) {
                                        c7 = r1;
                                        if (c8 <= '7') {
                                            i3++;
                                            ?? r12 = (r1 * 8) + (c8 - '0');
                                            char c9 = cArr[i3];
                                            c7 = r12;
                                            c7 = r12;
                                            if ('0' <= c9 && c9 <= '7') {
                                                i3++;
                                                ?? r13 = (r12 * 8) + (c9 - '0');
                                                if (r13 <= 255) {
                                                    c5 = r13;
                                                    break;
                                                } else {
                                                    i3--;
                                                    c7 = r12;
                                                }
                                            }
                                        }
                                    }
                                    c5 = c7;
                                    break;
                                default:
                                    switch (c6) {
                                        case 'b':
                                            c5 = '\b';
                                            break;
                                        case 'c':
                                            if (i3 >= i2 || !isControlLetter(cArr[i3])) {
                                                i6 = i3 - 1;
                                            } else {
                                                i6 = i3 + 1;
                                                char c10 = cArr[i3];
                                            }
                                            if (z) {
                                                if (i6 >= i2 - 1 || cArr[i6] != '-') {
                                                    compilerState2 = compilerState;
                                                    z = z;
                                                } else {
                                                    i6++;
                                                    c3 = c4 == true ? (char) 1 : (char) 0;
                                                    z = true;
                                                }
                                            } else {
                                                if (c3 > c4) {
                                                    reportError("msg.bad.range", "");
                                                    return false;
                                                }
                                                compilerState2 = compilerState;
                                                z = false;
                                            }
                                            c = c4;
                                            if ((2 & compilerState2.flags) != 0) {
                                                char c11 = c4 == true ? (char) 1 : (char) 0;
                                                char upcase = upcase(c11);
                                                char downcase = downcase(c11);
                                                c = upcase >= downcase ? upcase : downcase;
                                            }
                                            if (c <= c2) {
                                                c2 = c;
                                            }
                                            break;
                                        case 'd':
                                            if (!z) {
                                                c5 = '9';
                                                break;
                                            } else {
                                                reportError("msg.bad.range", "");
                                                return false;
                                            }
                                        default:
                                            switch (c6) {
                                                case 'r':
                                                    c5 = '\r';
                                                    break;
                                                case 's':
                                                case 'w':
                                                    break;
                                                case 't':
                                                    c5 = '\t';
                                                    break;
                                                case 'u':
                                                    i4 = 4;
                                                    i5 = 0;
                                                    r14 = 0;
                                                    while (i5 < i4 && i3 < i2) {
                                                        int i8 = i3 + 1;
                                                        r14 = Kit.xDigitToInt(cArr[i3], r14);
                                                        if (r14 >= 0) {
                                                            i3 = i8 - (i5 + 1);
                                                            break;
                                                        } else {
                                                            i5++;
                                                            i3 = i8;
                                                        }
                                                    }
                                                    c5 = r14;
                                                    break;
                                                case 'v':
                                                    c5 = 11;
                                                    break;
                                                case 'x':
                                                    i4 = 2;
                                                    i5 = 0;
                                                    r14 = 0;
                                                    while (i5 < i4) {
                                                        int i82 = i3 + 1;
                                                        r14 = Kit.xDigitToInt(cArr[i3], r14);
                                                        if (r14 >= 0) {
                                                        }
                                                    }
                                                    c5 = r14;
                                                    break;
                                                default:
                                                    c5 = c7;
                                                    break;
                                            }
                                    }
                            }
                        } else {
                            c5 = '\n';
                        }
                    } else {
                        c5 = '\f';
                    }
                }
                rENode.bmsize = 65536;
                return true;
            }
            i3 = i6 + 1;
            c5 = cArr[i6];
            i6 = i3;
            c4 = c5;
            if (z) {
            }
            c = c4;
            if ((2 & compilerState2.flags) != 0) {
            }
            if (c <= c2) {
            }
        }
        rENode.bmsize = c2 + 1;
        return true;
    }

    private static void doFlat(CompilerState compilerState, char c) {
        compilerState.result = new RENode((byte) 14);
        compilerState.result.chr = c;
        compilerState.result.length = 1;
        compilerState.result.flatIndex = -1;
        compilerState.progLength += 3;
    }

    private static int getDecimalValue(char c, CompilerState compilerState, int i, String str) {
        int i2 = compilerState.f10254cp;
        char[] cArr = compilerState.cpbegin;
        int i3 = c - 48;
        boolean z = false;
        while (compilerState.f10254cp != compilerState.cpend) {
            char c2 = cArr[compilerState.f10254cp];
            if (!isDigit(c2)) {
                break;
            }
            if (!z && (i3 = (i3 * 10) + (c2 - '0')) >= i) {
                i3 = i;
                z = true;
            }
            compilerState.f10254cp++;
        }
        if (z) {
            reportError(str, String.valueOf(cArr, i2, compilerState.f10254cp - i2));
        }
        return i3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:125:0x017c A[LOOP:0: B:120:0x0157->B:125:0x017c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x016d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0432 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x03e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean parseTerm(CompilerState compilerState) {
        boolean z;
        char c;
        char c2;
        int i;
        char c3;
        int xDigitToInt;
        char c4;
        char[] cArr = compilerState.cpbegin;
        int i2 = compilerState.f10254cp;
        compilerState.f10254cp = i2 + 1;
        char c5 = cArr[i2];
        int i3 = compilerState.parenCount;
        if (c5 == '$') {
            compilerState.result = new RENode((byte) 3);
            compilerState.progLength++;
            return true;
        }
        if (c5 == '.') {
            compilerState.result = new RENode((byte) 6);
            compilerState.progLength++;
        } else {
            if (c5 != '?') {
                if (c5 == '^') {
                    compilerState.result = new RENode((byte) 2);
                    compilerState.progLength++;
                    return true;
                }
                char c6 = '\\';
                if (c5 == '[') {
                    compilerState.result = new RENode((byte) 22);
                    int i4 = compilerState.f10254cp;
                    compilerState.result.startIndex = i4;
                    while (compilerState.f10254cp != compilerState.cpend) {
                        if (cArr[compilerState.f10254cp] == '\\') {
                            compilerState.f10254cp++;
                        } else if (cArr[compilerState.f10254cp] == ']') {
                            compilerState.result.kidlen = compilerState.f10254cp - i4;
                            RENode rENode = compilerState.result;
                            int i5 = compilerState.classCount;
                            compilerState.classCount = i5 + 1;
                            rENode.index = i5;
                            RENode rENode2 = compilerState.result;
                            int i6 = compilerState.f10254cp;
                            compilerState.f10254cp = i6 + 1;
                            if (!calculateBitmapSize(compilerState, rENode2, cArr, i4, i6)) {
                                return false;
                            }
                            compilerState.progLength += 3;
                        }
                        compilerState.f10254cp++;
                    }
                    reportError("msg.unterm.class", "");
                    return false;
                }
                int i7 = 4;
                if (c5 == '\\') {
                    if (compilerState.f10254cp < compilerState.cpend) {
                        int i8 = compilerState.f10254cp;
                        compilerState.f10254cp = i8 + 1;
                        char c7 = cArr[i8];
                        if (c7 == 'B') {
                            compilerState.result = new RENode((byte) 5);
                            compilerState.progLength++;
                            return true;
                        }
                        if (c7 == 'D') {
                            compilerState.result = new RENode((byte) 8);
                            compilerState.progLength++;
                        } else if (c7 == 'S') {
                            compilerState.result = new RENode((byte) 12);
                            compilerState.progLength++;
                        } else if (c7 == 'W') {
                            compilerState.result = new RENode((byte) 10);
                            compilerState.progLength++;
                        } else if (c7 == 'f') {
                            doFlat(compilerState, '\f');
                        } else if (c7 != 'n') {
                            switch (c7) {
                                case '0':
                                    reportWarning(compilerState.f10255cx, "msg.bad.backref", "");
                                    int i9 = 0;
                                    while (i9 < 32 && compilerState.f10254cp < compilerState.cpend && (c = cArr[compilerState.f10254cp]) >= '0' && c <= '7') {
                                        compilerState.f10254cp++;
                                        i9 = (i9 * 8) + (c - '0');
                                    }
                                    doFlat(compilerState, (char) i9);
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9':
                                    int i10 = compilerState.f10254cp - 1;
                                    int decimalValue = getDecimalValue(c7, compilerState, 65535, "msg.overlarge.backref");
                                    if (decimalValue > compilerState.backReferenceLimit) {
                                        reportWarning(compilerState.f10255cx, "msg.bad.backref", "");
                                    }
                                    if (decimalValue > compilerState.backReferenceLimit) {
                                        compilerState.f10254cp = i10;
                                        if (c7 >= '8') {
                                            doFlat(compilerState, '\\');
                                            break;
                                        } else {
                                            compilerState.f10254cp++;
                                            int i11 = c7 - '0';
                                            while (i11 < 32 && compilerState.f10254cp < compilerState.cpend && (c2 = cArr[compilerState.f10254cp]) >= '0' && c2 <= '7') {
                                                compilerState.f10254cp++;
                                                i11 = (i11 * 8) + (c2 - '0');
                                            }
                                            doFlat(compilerState, (char) i11);
                                        }
                                    } else {
                                        compilerState.result = new RENode((byte) 13);
                                        compilerState.result.parenIndex = decimalValue - 1;
                                        compilerState.progLength += 3;
                                        if (compilerState.maxBackReference < decimalValue) {
                                            compilerState.maxBackReference = decimalValue;
                                            break;
                                        }
                                    }
                                    break;
                                default:
                                    switch (c7) {
                                        case 'b':
                                            compilerState.result = new RENode((byte) 4);
                                            compilerState.progLength++;
                                            return true;
                                        case 'c':
                                            if (compilerState.f10254cp < compilerState.cpend && isControlLetter(cArr[compilerState.f10254cp])) {
                                                int i12 = compilerState.f10254cp;
                                                compilerState.f10254cp = i12 + 1;
                                                c6 = (char) (cArr[i12] & 31);
                                            } else {
                                                compilerState.f10254cp--;
                                            }
                                            doFlat(compilerState, c6);
                                            break;
                                        case 'd':
                                            compilerState.result = new RENode((byte) 7);
                                            compilerState.progLength++;
                                            break;
                                        default:
                                            switch (c7) {
                                                case 'r':
                                                    doFlat(compilerState, '\r');
                                                    break;
                                                case 's':
                                                    compilerState.result = new RENode((byte) 11);
                                                    compilerState.progLength++;
                                                    break;
                                                case 't':
                                                    doFlat(compilerState, '\t');
                                                    break;
                                                case 'u':
                                                    i = 0;
                                                    c3 = 0;
                                                    while (true) {
                                                        if (i < i7 && compilerState.f10254cp < compilerState.cpend) {
                                                            int i13 = compilerState.f10254cp;
                                                            compilerState.f10254cp = i13 + 1;
                                                            xDigitToInt = Kit.xDigitToInt(cArr[i13], c3);
                                                            if (xDigitToInt >= 0) {
                                                                compilerState.f10254cp -= i + 2;
                                                                int i14 = compilerState.f10254cp;
                                                                compilerState.f10254cp = i14 + 1;
                                                                c3 = cArr[i14];
                                                            } else {
                                                                i++;
                                                                c3 = xDigitToInt;
                                                            }
                                                        }
                                                    }
                                                    doFlat(compilerState, c3);
                                                    break;
                                                case 'v':
                                                    doFlat(compilerState, (char) 11);
                                                    break;
                                                case 'w':
                                                    compilerState.result = new RENode((byte) 9);
                                                    compilerState.progLength++;
                                                    break;
                                                case 'x':
                                                    i7 = 2;
                                                    i = 0;
                                                    c3 = 0;
                                                    while (true) {
                                                        if (i < i7) {
                                                            int i132 = compilerState.f10254cp;
                                                            compilerState.f10254cp = i132 + 1;
                                                            xDigitToInt = Kit.xDigitToInt(cArr[i132], c3);
                                                            if (xDigitToInt >= 0) {
                                                            }
                                                            break;
                                                        }
                                                        i++;
                                                        c3 = xDigitToInt;
                                                    }
                                                    doFlat(compilerState, c3);
                                                    break;
                                                default:
                                                    compilerState.result = new RENode((byte) 14);
                                                    compilerState.result.chr = c7;
                                                    compilerState.result.length = 1;
                                                    compilerState.result.flatIndex = compilerState.f10254cp - 1;
                                                    compilerState.progLength += 3;
                                                    break;
                                            }
                                    }
                            }
                        } else {
                            doFlat(compilerState, '\n');
                        }
                    } else {
                        reportError("msg.trail.backslash", "");
                        return false;
                    }
                } else {
                    switch (c5) {
                        case '(':
                            RENode rENode3 = null;
                            int i15 = compilerState.f10254cp;
                            if (compilerState.f10254cp + 1 < compilerState.cpend && cArr[compilerState.f10254cp] == '?' && ((c4 = cArr[compilerState.f10254cp + 1]) == '=' || c4 == '!' || c4 == ':')) {
                                compilerState.f10254cp += 2;
                                if (c4 == '=') {
                                    rENode3 = new RENode(REOP_ASSERT);
                                    compilerState.progLength += 4;
                                } else if (c4 == '!') {
                                    rENode3 = new RENode((byte) 42);
                                    compilerState.progLength += 4;
                                }
                            } else {
                                rENode3 = new RENode((byte) 29);
                                compilerState.progLength += 6;
                                int i16 = compilerState.parenCount;
                                compilerState.parenCount = i16 + 1;
                                rENode3.parenIndex = i16;
                            }
                            compilerState.parenNesting++;
                            if (!parseDisjunction(compilerState)) {
                                return false;
                            }
                            if (compilerState.f10254cp == compilerState.cpend || cArr[compilerState.f10254cp] != ')') {
                                reportError("msg.unterm.paren", "");
                                return false;
                            }
                            compilerState.f10254cp++;
                            compilerState.parenNesting--;
                            if (rENode3 != null) {
                                rENode3.kid = compilerState.result;
                                compilerState.result = rENode3;
                                break;
                            }
                            break;
                        case ')':
                            reportError("msg.re.unmatched.right.paren", "");
                            return false;
                        case '*':
                        case '+':
                            break;
                        default:
                            compilerState.result = new RENode((byte) 14);
                            compilerState.result.chr = c5;
                            compilerState.result.length = 1;
                            compilerState.result.flatIndex = compilerState.f10254cp - 1;
                            compilerState.progLength += 3;
                            break;
                    }
                }
            }
            reportError("msg.bad.quant", String.valueOf(cArr[compilerState.f10254cp - 1]));
            return false;
        }
        RENode rENode4 = compilerState.result;
        if (compilerState.f10254cp == compilerState.cpend) {
            return true;
        }
        char c8 = cArr[compilerState.f10254cp];
        int i17 = -1;
        if (c8 == '*') {
            compilerState.result = new RENode((byte) 25);
            compilerState.result.min = 0;
            compilerState.result.max = -1;
            compilerState.progLength += 8;
        } else if (c8 == '+') {
            compilerState.result = new RENode((byte) 25);
            compilerState.result.min = 1;
            compilerState.result.max = -1;
            compilerState.progLength += 8;
        } else {
            if (c8 != '?') {
                if (c8 != '{') {
                    z = false;
                } else {
                    int i18 = compilerState.f10254cp;
                    int i19 = compilerState.f10254cp + 1;
                    compilerState.f10254cp = i19;
                    if (i19 < cArr.length) {
                        char c9 = cArr[compilerState.f10254cp];
                        if (isDigit(c9)) {
                            compilerState.f10254cp++;
                            int decimalValue2 = getDecimalValue(c9, compilerState, 65535, "msg.overlarge.min");
                            if (compilerState.f10254cp < cArr.length) {
                                char c10 = cArr[compilerState.f10254cp];
                                if (c10 == ',') {
                                    int i20 = compilerState.f10254cp + 1;
                                    compilerState.f10254cp = i20;
                                    if (i20 < cArr.length) {
                                        c10 = cArr[compilerState.f10254cp];
                                        if (isDigit(c10)) {
                                            int i21 = compilerState.f10254cp + 1;
                                            compilerState.f10254cp = i21;
                                            if (i21 < cArr.length) {
                                                i17 = getDecimalValue(c10, compilerState, 65535, "msg.overlarge.max");
                                                c10 = cArr[compilerState.f10254cp];
                                                if (decimalValue2 > i17) {
                                                    reportError("msg.max.lt.min", String.valueOf(cArr[compilerState.f10254cp]));
                                                    return false;
                                                }
                                            }
                                        }
                                        if (c10 == '}') {
                                            compilerState.result = new RENode((byte) 25);
                                            compilerState.result.min = decimalValue2;
                                            compilerState.result.max = i17;
                                            compilerState.progLength += 12;
                                            z = true;
                                            if (!z) {
                                                compilerState.f10254cp = i18;
                                            }
                                        }
                                    }
                                }
                                i17 = decimalValue2;
                                if (c10 == '}') {
                                }
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                    }
                }
                if (z) {
                    return true;
                }
                compilerState.f10254cp++;
                compilerState.result.kid = rENode4;
                compilerState.result.parenIndex = i3;
                compilerState.result.parenCount = compilerState.parenCount - i3;
                if (compilerState.f10254cp < compilerState.cpend && cArr[compilerState.f10254cp] == '?') {
                    compilerState.f10254cp++;
                    compilerState.result.greedy = false;
                } else {
                    compilerState.result.greedy = true;
                }
                return true;
            }
            compilerState.result = new RENode((byte) 25);
            compilerState.result.min = 0;
            compilerState.result.max = 1;
            compilerState.progLength += 8;
        }
        z = true;
        if (z) {
        }
    }

    private static void resolveForwardJump(byte[] bArr, int i, int i2) {
        if (i > i2) {
            throw Kit.codeBug();
        }
        addIndex(bArr, i, i2 - i);
    }

    private static int getOffset(byte[] bArr, int i) {
        return getIndex(bArr, i);
    }

    private static int addIndex(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw Kit.codeBug();
        }
        if (i2 > 65535) {
            throw Context.reportRuntimeError("Too complex regexp");
        }
        bArr[i] = (byte) (i2 >> 8);
        bArr[i + 1] = (byte) i2;
        return i + 2;
    }

    private static int getIndex(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    private static int emitREBytecode(CompilerState compilerState, RECompiled rECompiled, int i, RENode rENode) {
        int i2;
        byte[] bArr = rECompiled.program;
        while (rENode != null) {
            int i3 = i + 1;
            bArr[i] = rENode.f10261op;
            byte b = rENode.f10261op;
            if (b != 1) {
                if (b != 22) {
                    if (b == 25) {
                        if (rENode.min == 0 && rENode.max == -1) {
                            bArr[i3 - 1] = rENode.greedy ? (byte) 26 : REOP_MINIMALSTAR;
                        } else if (rENode.min == 0 && rENode.max == 1) {
                            bArr[i3 - 1] = rENode.greedy ? (byte) 28 : REOP_MINIMALOPT;
                        } else if (rENode.min == 1 && rENode.max == -1) {
                            bArr[i3 - 1] = rENode.greedy ? (byte) 27 : REOP_MINIMALPLUS;
                        } else {
                            if (!rENode.greedy) {
                                bArr[i3 - 1] = 48;
                            }
                            i3 = addIndex(bArr, addIndex(bArr, i3, rENode.min), rENode.max + 1);
                        }
                        int addIndex = addIndex(bArr, addIndex(bArr, i3, rENode.parenCount), rENode.parenIndex);
                        int emitREBytecode = emitREBytecode(compilerState, rECompiled, addIndex + 2, rENode.kid);
                        i2 = emitREBytecode + 1;
                        bArr[emitREBytecode] = 49;
                        resolveForwardJump(bArr, addIndex, i2);
                    } else if (b != 29) {
                        if (b != 31) {
                            if (b == 13) {
                                i = addIndex(bArr, i3, rENode.parenIndex);
                            } else if (b == 14) {
                                if (rENode.flatIndex != -1) {
                                    while (rENode.next != null && rENode.next.f10261op == 14 && rENode.flatIndex + rENode.length == rENode.next.flatIndex) {
                                        rENode.length += rENode.next.length;
                                        rENode.next = rENode.next.next;
                                    }
                                }
                                if (rENode.flatIndex != -1 && rENode.length > 1) {
                                    if ((compilerState.flags & 2) != 0) {
                                        bArr[i3 - 1] = 16;
                                    } else {
                                        bArr[i3 - 1] = 14;
                                    }
                                    i = addIndex(bArr, addIndex(bArr, i3, rENode.flatIndex), rENode.length);
                                } else if (rENode.chr < 256) {
                                    if ((compilerState.flags & 2) != 0) {
                                        bArr[i3 - 1] = 17;
                                    } else {
                                        bArr[i3 - 1] = 15;
                                    }
                                    i = i3 + 1;
                                    bArr[i3] = (byte) rENode.chr;
                                } else {
                                    if ((compilerState.flags & 2) != 0) {
                                        bArr[i3 - 1] = 19;
                                    } else {
                                        bArr[i3 - 1] = 18;
                                    }
                                    i = addIndex(bArr, i3, rENode.chr);
                                }
                            } else if (b == 41) {
                                int emitREBytecode2 = emitREBytecode(compilerState, rECompiled, i3 + 2, rENode.kid);
                                i2 = emitREBytecode2 + 1;
                                bArr[emitREBytecode2] = REOP_ASSERTTEST;
                                resolveForwardJump(bArr, i3, i2);
                            } else if (b != 42) {
                                switch (b) {
                                    case 53:
                                    case 54:
                                    case 55:
                                        boolean z = rENode.f10261op == 54;
                                        char c = rENode.chr;
                                        if (z) {
                                            c = upcase(c);
                                        }
                                        addIndex(bArr, i3, c);
                                        int i4 = i3 + 2;
                                        int i5 = rENode.index;
                                        if (z) {
                                            i5 = upcase((char) i5);
                                        }
                                        addIndex(bArr, i4, i5);
                                        i3 = i4 + 2;
                                        break;
                                }
                            } else {
                                int emitREBytecode3 = emitREBytecode(compilerState, rECompiled, i3 + 2, rENode.kid);
                                i2 = emitREBytecode3 + 1;
                                bArr[emitREBytecode3] = 44;
                                resolveForwardJump(bArr, i3, i2);
                            }
                        }
                        RENode rENode2 = rENode.kid2;
                        int emitREBytecode4 = emitREBytecode(compilerState, rECompiled, i3 + 2, rENode.kid);
                        int i6 = emitREBytecode4 + 1;
                        bArr[emitREBytecode4] = 32;
                        int i7 = i6 + 2;
                        resolveForwardJump(bArr, i3, i7);
                        int emitREBytecode5 = emitREBytecode(compilerState, rECompiled, i7, rENode2);
                        int i8 = emitREBytecode5 + 1;
                        bArr[emitREBytecode5] = 32;
                        i = i8 + 2;
                        resolveForwardJump(bArr, i6, i);
                        resolveForwardJump(bArr, i8, i);
                    } else {
                        int emitREBytecode6 = emitREBytecode(compilerState, rECompiled, addIndex(bArr, i3, rENode.parenIndex), rENode.kid);
                        bArr[emitREBytecode6] = 30;
                        i = addIndex(bArr, emitREBytecode6 + 1, rENode.parenIndex);
                    }
                    i = i2;
                } else {
                    if (!rENode.sense) {
                        bArr[i3 - 1] = 23;
                    }
                    i = addIndex(bArr, i3, rENode.index);
                    rECompiled.classList[rENode.index] = new RECharSet(rENode.bmsize, rENode.startIndex, rENode.kidlen, rENode.sense);
                }
                rENode = rENode.next;
            } else {
                i3--;
            }
            i = i3;
            continue;
            rENode = rENode.next;
        }
        return i;
    }

    private static void pushProgState(REGlobalData rEGlobalData, int i, int i2, int i3, REBackTrackData rEBackTrackData, int i4, int i5) {
        rEGlobalData.stateStackTop = new REProgState(rEGlobalData.stateStackTop, i, i2, i3, rEBackTrackData, i4, i5);
    }

    private static REProgState popProgState(REGlobalData rEGlobalData) {
        REProgState rEProgState = rEGlobalData.stateStackTop;
        rEGlobalData.stateStackTop = rEProgState.previous;
        return rEProgState;
    }

    private static void pushBackTrackState(REGlobalData rEGlobalData, byte b, int i) {
        REProgState rEProgState = rEGlobalData.stateStackTop;
        rEGlobalData.backTrackStackTop = new REBackTrackData(rEGlobalData, b, i, rEGlobalData.f10260cp, rEProgState.continuationOp, rEProgState.continuationPc);
    }

    private static void pushBackTrackState(REGlobalData rEGlobalData, byte b, int i, int i2, int i3, int i4) {
        rEGlobalData.backTrackStackTop = new REBackTrackData(rEGlobalData, b, i, i2, i3, i4);
    }

    private static boolean flatNMatcher(REGlobalData rEGlobalData, int i, int i2, String str, int i3) {
        if (rEGlobalData.f10260cp + i2 > i3) {
            return false;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (rEGlobalData.regexp.source[i + i4] != str.charAt(rEGlobalData.f10260cp + i4)) {
                return false;
            }
        }
        rEGlobalData.f10260cp += i2;
        return true;
    }

    private static boolean flatNIMatcher(REGlobalData rEGlobalData, int i, int i2, String str, int i3) {
        if (rEGlobalData.f10260cp + i2 > i3) {
            return false;
        }
        char[] cArr = rEGlobalData.regexp.source;
        for (int i4 = 0; i4 < i2; i4++) {
            char c = cArr[i + i4];
            char charAt = str.charAt(rEGlobalData.f10260cp + i4);
            if (c != charAt && upcase(c) != upcase(charAt)) {
                return false;
            }
        }
        rEGlobalData.f10260cp += i2;
        return true;
    }

    private static boolean backrefMatcher(REGlobalData rEGlobalData, int i, String str, int i2) {
        if (rEGlobalData.parens == null || i >= rEGlobalData.parens.length) {
            return false;
        }
        int parensIndex = rEGlobalData.parensIndex(i);
        if (parensIndex == -1) {
            return true;
        }
        int parensLength = rEGlobalData.parensLength(i);
        if (rEGlobalData.f10260cp + parensLength > i2) {
            return false;
        }
        if ((rEGlobalData.regexp.flags & 2) != 0) {
            for (int i3 = 0; i3 < parensLength; i3++) {
                char charAt = str.charAt(parensIndex + i3);
                char charAt2 = str.charAt(rEGlobalData.f10260cp + i3);
                if (charAt != charAt2 && upcase(charAt) != upcase(charAt2)) {
                    return false;
                }
            }
        } else if (!str.regionMatches(parensIndex, str, rEGlobalData.f10260cp, parensLength)) {
            return false;
        }
        rEGlobalData.f10260cp += parensLength;
        return true;
    }

    private static void addCharacterToCharSet(RECharSet rECharSet, char c) {
        int i = c / '\b';
        if (c >= rECharSet.length) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        byte[] bArr = rECharSet.bits;
        bArr[i] = (byte) ((1 << (c & 7)) | bArr[i]);
    }

    private static void addCharacterRangeToCharSet(RECharSet rECharSet, char c, char c2) {
        int i = c / '\b';
        int i2 = c2 / '\b';
        if (c2 >= rECharSet.length || c > c2) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        char c3 = (char) (c & 7);
        char c4 = (char) (c2 & 7);
        if (i == i2) {
            byte[] bArr = rECharSet.bits;
            bArr[i] = (byte) (((255 >> (7 - (c4 - c3))) << c3) | bArr[i]);
            return;
        }
        byte[] bArr2 = rECharSet.bits;
        bArr2[i] = (byte) ((255 << c3) | bArr2[i]);
        while (true) {
            i++;
            if (i < i2) {
                rECharSet.bits[i] = -1;
            } else {
                byte[] bArr3 = rECharSet.bits;
                bArr3[i2] = (byte) (bArr3[i2] | (255 >> (7 - c4)));
                return;
            }
        }
    }

    private static void processCharSet(REGlobalData rEGlobalData, RECharSet rECharSet) {
        synchronized (rECharSet) {
            if (!rECharSet.converted) {
                processCharSetImpl(rEGlobalData, rECharSet);
                rECharSet.converted = true;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x0063. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x0066. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0069. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00a1 A[LOOP:4: B:89:0x008a->B:93:0x00a1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x009c A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void processCharSetImpl(REGlobalData rEGlobalData, RECharSet rECharSet) {
        int i;
        int i2;
        int aSCIIHexDigit;
        int i3 = rECharSet.startIndex;
        int i4 = rECharSet.strlength + i3;
        rECharSet.bits = new byte[(rECharSet.length + 7) / 8];
        if (i3 == i4) {
            return;
        }
        if (rEGlobalData.regexp.source[i3] == '^') {
            i3++;
        }
        boolean z = false;
        char c = 0;
        while (i3 != i4) {
            char c2 = '\\';
            if (rEGlobalData.regexp.source[i3] == '\\') {
                int i5 = i3 + 1;
                int i6 = i5 + 1;
                char c3 = rEGlobalData.regexp.source[i5];
                if (c3 == 'D') {
                    addCharacterRangeToCharSet(rECharSet, (char) 0, '/');
                    addCharacterRangeToCharSet(rECharSet, ':', (char) (rECharSet.length - 1));
                } else if (c3 == 'S') {
                    for (int i7 = rECharSet.length - 1; i7 >= 0; i7--) {
                        if (!isREWhiteSpace(i7)) {
                            addCharacterToCharSet(rECharSet, (char) i7);
                        }
                    }
                } else if (c3 != 'W') {
                    if (c3 == 'f') {
                        c2 = '\f';
                    } else if (c3 != 'n') {
                        switch (c3) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                                int i8 = c3 - '0';
                                char c4 = rEGlobalData.regexp.source[i6];
                                if ('0' <= c4 && c4 <= '7') {
                                    i6++;
                                    i8 = (i8 * 8) + (c4 - '0');
                                    char c5 = rEGlobalData.regexp.source[i6];
                                    if ('0' <= c5 && c5 <= '7') {
                                        i6++;
                                        int i9 = (i8 * 8) + (c5 - '0');
                                        if (i9 <= 255) {
                                            i8 = i9;
                                        } else {
                                            i6--;
                                        }
                                    }
                                }
                                c2 = (char) i8;
                                break;
                            default:
                                switch (c3) {
                                    case 'b':
                                        c2 = '\b';
                                        break;
                                    case 'c':
                                        if (i6 >= i4 || !isControlLetter(rEGlobalData.regexp.source[i6])) {
                                            i6--;
                                            break;
                                        } else {
                                            c2 = (char) (rEGlobalData.regexp.source[i6] & 31);
                                            i3 = i6 + 1;
                                            break;
                                        }
                                    case 'd':
                                        addCharacterRangeToCharSet(rECharSet, '0', '9');
                                        break;
                                    default:
                                        switch (c3) {
                                            case 'r':
                                                c2 = '\r';
                                                break;
                                            case 's':
                                                for (int i10 = rECharSet.length - 1; i10 >= 0; i10--) {
                                                    if (isREWhiteSpace(i10)) {
                                                        addCharacterToCharSet(rECharSet, (char) i10);
                                                    }
                                                }
                                                break;
                                            case 't':
                                                c2 = '\t';
                                                break;
                                            case 'u':
                                                i = 4;
                                                i2 = 0;
                                                char c6 = 0;
                                                while (i2 < i && i6 < i4) {
                                                    int i11 = i6 + 1;
                                                    aSCIIHexDigit = toASCIIHexDigit(rEGlobalData.regexp.source[i6]);
                                                    if (aSCIIHexDigit >= 0) {
                                                        i6 = i11 - (i2 + 1);
                                                        c2 = c2;
                                                        break;
                                                    } else {
                                                        ?? r13 = (c6 << 4) | aSCIIHexDigit;
                                                        i2++;
                                                        i6 = i11;
                                                        c6 = r13;
                                                    }
                                                }
                                                c2 = c6;
                                                c2 = c2;
                                            case 'v':
                                                c2 = 11;
                                                break;
                                            case 'w':
                                                for (int i12 = rECharSet.length - 1; i12 >= 0; i12--) {
                                                    char c7 = (char) i12;
                                                    if (isWord(c7)) {
                                                        addCharacterToCharSet(rECharSet, c7);
                                                    }
                                                }
                                                break;
                                            case 'x':
                                                i = 2;
                                                i2 = 0;
                                                char c62 = 0;
                                                while (i2 < i) {
                                                    int i112 = i6 + 1;
                                                    aSCIIHexDigit = toASCIIHexDigit(rEGlobalData.regexp.source[i6]);
                                                    if (aSCIIHexDigit >= 0) {
                                                    }
                                                }
                                                c2 = c62;
                                                c2 = c2;
                                                break;
                                            default:
                                                c2 = c3;
                                                break;
                                        }
                                }
                                break;
                        }
                    } else {
                        c2 = '\n';
                    }
                    i3 = i6;
                } else {
                    for (int i13 = rECharSet.length - 1; i13 >= 0; i13--) {
                        char c8 = (char) i13;
                        if (!isWord(c8)) {
                            addCharacterToCharSet(rECharSet, c8);
                        }
                    }
                }
                i3 = i6;
            } else {
                c2 = rEGlobalData.regexp.source[i3];
                i3++;
            }
            if (z) {
                if ((rEGlobalData.regexp.flags & 2) != 0) {
                    char c9 = c;
                    while (c9 <= c2) {
                        addCharacterToCharSet(rECharSet, c9);
                        char upcase = upcase(c9);
                        char downcase = downcase(c9);
                        if (c9 != upcase) {
                            addCharacterToCharSet(rECharSet, upcase);
                        }
                        if (c9 != downcase) {
                            addCharacterToCharSet(rECharSet, downcase);
                        }
                        c9 = (char) (c9 + 1);
                        if (c9 == 0) {
                        }
                    }
                } else {
                    addCharacterRangeToCharSet(rECharSet, c, c2);
                }
                z = false;
            } else {
                if ((rEGlobalData.regexp.flags & 2) != 0) {
                    addCharacterToCharSet(rECharSet, upcase(c2));
                    addCharacterToCharSet(rECharSet, downcase(c2));
                } else {
                    addCharacterToCharSet(rECharSet, c2);
                }
                if (i3 < i4 - 1 && rEGlobalData.regexp.source[i3] == '-') {
                    i3++;
                    z = true;
                    c = c2;
                }
            }
        }
    }

    private static boolean classMatcher(REGlobalData rEGlobalData, RECharSet rECharSet, char c) {
        if (!rECharSet.converted) {
            processCharSet(rEGlobalData, rECharSet);
        }
        int i = c >> 3;
        boolean z = true;
        if (rECharSet.length != 0 && c < rECharSet.length && (rECharSet.bits[i] & (1 << (c & 7))) != 0) {
            z = false;
        }
        return rECharSet.sense ^ z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x01c9, code lost:
    
        if (isWord(r4.charAt(r3.f10260cp)) != false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01e0, code lost:
    
        if (isLineTerm(r4.charAt(r3.f10260cp)) == false) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x01f6, code lost:
    
        if (isLineTerm(r4.charAt(r3.f10260cp - 1)) == false) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01a3, code lost:
    
        if (isWord(r4.charAt(r3.f10260cp)) != false) goto L97;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0201  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int simpleMatch(REGlobalData rEGlobalData, String str, int i, byte[] bArr, int i2, int i3, boolean z) {
        boolean z2;
        int i4;
        char charAt;
        char charAt2;
        int i5 = rEGlobalData.f10260cp;
        boolean z3 = false;
        switch (i) {
            case 1:
                z3 = true;
                if (!z3) {
                    rEGlobalData.f10260cp = i5;
                    return -1;
                }
                if (!z) {
                    rEGlobalData.f10260cp = i5;
                }
                return i2;
            case 2:
                if (rEGlobalData.f10260cp != 0) {
                    if (rEGlobalData.multiline) {
                        break;
                    }
                    if (!z3) {
                    }
                }
                z3 = true;
                if (!z3) {
                }
                break;
            case 3:
                if (rEGlobalData.f10260cp != i3) {
                    if (rEGlobalData.multiline) {
                        break;
                    }
                    if (!z3) {
                    }
                }
                z3 = true;
                if (!z3) {
                }
                break;
            case 4:
                z2 = rEGlobalData.f10260cp == 0 || !isWord(str.charAt(rEGlobalData.f10260cp - 1));
                if (rEGlobalData.f10260cp < i3) {
                    break;
                }
                z3 = true;
                z3 ^= z2;
                if (!z3) {
                }
                break;
            case 5:
                z2 = rEGlobalData.f10260cp == 0 || !isWord(str.charAt(rEGlobalData.f10260cp - 1));
                if (rEGlobalData.f10260cp < i3) {
                    break;
                }
                z3 ^= z2;
                if (!z3) {
                }
                break;
            case 6:
                if (rEGlobalData.f10260cp != i3 && !isLineTerm(str.charAt(rEGlobalData.f10260cp))) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                if (!z3) {
                }
                break;
            case 7:
                if (rEGlobalData.f10260cp != i3 && isDigit(str.charAt(rEGlobalData.f10260cp))) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                if (!z3) {
                }
                break;
            case 8:
                if (rEGlobalData.f10260cp != i3 && !isDigit(str.charAt(rEGlobalData.f10260cp))) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                if (!z3) {
                }
                break;
            case 9:
                if (rEGlobalData.f10260cp != i3 && isWord(str.charAt(rEGlobalData.f10260cp))) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                if (!z3) {
                }
                break;
            case 10:
                if (rEGlobalData.f10260cp != i3 && !isWord(str.charAt(rEGlobalData.f10260cp))) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                if (!z3) {
                }
                break;
            case 11:
                if (rEGlobalData.f10260cp != i3 && isREWhiteSpace(str.charAt(rEGlobalData.f10260cp))) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                if (!z3) {
                }
                break;
            case 12:
                if (rEGlobalData.f10260cp != i3 && !isREWhiteSpace(str.charAt(rEGlobalData.f10260cp))) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                if (!z3) {
                }
                break;
            case 13:
                int index = getIndex(bArr, i2);
                i2 += 2;
                z3 = backrefMatcher(rEGlobalData, index, str, i3);
                if (!z3) {
                }
                break;
            case 14:
                int index2 = getIndex(bArr, i2);
                int i6 = i2 + 2;
                int index3 = getIndex(bArr, i6);
                i2 = i6 + 2;
                z3 = flatNMatcher(rEGlobalData, index2, index3, str, i3);
                if (!z3) {
                }
                break;
            case 15:
                i4 = i2 + 1;
                char c = (char) (bArr[i2] & 255);
                if (rEGlobalData.f10260cp != i3 && str.charAt(rEGlobalData.f10260cp) == c) {
                    rEGlobalData.f10260cp++;
                    i2 = i4;
                    z3 = true;
                    if (!z3) {
                    }
                }
                i2 = i4;
                if (!z3) {
                }
                break;
            case 16:
                int index4 = getIndex(bArr, i2);
                int i7 = i2 + 2;
                int index5 = getIndex(bArr, i7);
                i2 = i7 + 2;
                z3 = flatNIMatcher(rEGlobalData, index4, index5, str, i3);
                if (!z3) {
                }
                break;
            case 17:
                i4 = i2 + 1;
                char c2 = (char) (bArr[i2] & 255);
                if (rEGlobalData.f10260cp != i3 && (c2 == (charAt = str.charAt(rEGlobalData.f10260cp)) || upcase(c2) == upcase(charAt))) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                i2 = i4;
                if (!z3) {
                }
                break;
            case 18:
                char index6 = (char) getIndex(bArr, i2);
                i2 += 2;
                if (rEGlobalData.f10260cp != i3 && str.charAt(rEGlobalData.f10260cp) == index6) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                if (!z3) {
                }
                break;
            case 19:
                char index7 = (char) getIndex(bArr, i2);
                i2 += 2;
                if (rEGlobalData.f10260cp != i3 && (index7 == (charAt2 = str.charAt(rEGlobalData.f10260cp)) || upcase(index7) == upcase(charAt2))) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                if (!z3) {
                }
                break;
            case 20:
            case 21:
            default:
                throw Kit.codeBug();
            case 22:
            case 23:
                int index8 = getIndex(bArr, i2);
                i2 += 2;
                if (rEGlobalData.f10260cp != i3 && classMatcher(rEGlobalData, rEGlobalData.regexp.classList[index8], str.charAt(rEGlobalData.f10260cp))) {
                    rEGlobalData.f10260cp++;
                    z3 = true;
                }
                if (!z3) {
                }
                break;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:102:0x007b. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:22:0x0073. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x0078. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:31:0x03c0. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x03c3. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x03e4  */
    /* JADX WARN: Type inference failed for: r0v8, types: [int] */
    /* JADX WARN: Type inference failed for: r0v96, types: [int] */
    /* JADX WARN: Type inference failed for: r1v24, types: [int] */
    /* JADX WARN: Type inference failed for: r1v27, types: [int] */
    /* JADX WARN: Type inference failed for: r1v38, types: [int] */
    /* JADX WARN: Type inference failed for: r1v46, types: [int] */
    /* JADX WARN: Type inference failed for: r1v53, types: [int] */
    /* JADX WARN: Type inference failed for: r1v56, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8, types: [int] */
    /* JADX WARN: Type inference failed for: r2v42, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean executeREBytecode(REGlobalData rEGlobalData, String str, int i) {
        int i2;
        byte b;
        byte b2;
        int i3;
        boolean z;
        boolean z2;
        int i4;
        int i5;
        boolean z3;
        int i6;
        byte b3;
        byte b4;
        int i7;
        int i8;
        boolean z4;
        int i9;
        int i10;
        int i11;
        byte b5;
        byte b6;
        byte b7;
        byte b8;
        boolean z5;
        byte[] bArr = rEGlobalData.regexp.program;
        byte b9 = bArr[0];
        int i12 = rEGlobalData.regexp.anchorCh;
        byte b10 = REOP_END;
        byte b11 = 52;
        int i13 = 1;
        if (i12 < 0 && reopIsSimple(b9)) {
            while (true) {
                if (rEGlobalData.f10260cp > i) {
                    z5 = false;
                    break;
                }
                int simpleMatch = simpleMatch(rEGlobalData, str, b9, bArr, 1, i, true);
                if (simpleMatch >= 0) {
                    i13 = simpleMatch + 1;
                    b9 = bArr[simpleMatch];
                    z5 = true;
                    break;
                }
                rEGlobalData.skipped++;
                rEGlobalData.f10260cp++;
            }
            if (!z5) {
                return false;
            }
        }
        boolean z6 = false;
        byte b12 = b9;
        byte b13 = 57;
        int i14 = i13;
        int i15 = 0;
        while (true) {
            if (reopIsSimple(b12)) {
                int simpleMatch2 = simpleMatch(rEGlobalData, str, b12, bArr, i14, i, true);
                boolean z7 = simpleMatch2 >= 0;
                if (z7) {
                    i14 = simpleMatch2;
                }
                z6 = z7;
            } else {
                if (b12 == b10) {
                    return true;
                }
                switch (b12) {
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                        switch (b12) {
                            case 25:
                                z = true;
                                int offset = getOffset(bArr, i14);
                                int i16 = i14 + 2;
                                i5 = getOffset(bArr, i16) - 1;
                                i6 = offset;
                                i4 = i16 + 2;
                                z3 = z;
                                pushProgState(rEGlobalData, i6, i5, rEGlobalData.f10260cp, null, b13, i15);
                                if (!z3) {
                                    pushBackTrackState(rEGlobalData, (byte) 51, i4);
                                    int i17 = i4 + 6;
                                    int i18 = i17 + 1;
                                    b4 = bArr[i17];
                                    b13 = 51;
                                    i14 = i18;
                                    i15 = i4;
                                } else if (i6 != 0) {
                                    int i19 = i4 + 6;
                                    int i20 = i19 + 1;
                                    b4 = bArr[i19];
                                    i14 = i20;
                                    i15 = i4;
                                    b13 = 52;
                                } else {
                                    b3 = 52;
                                    pushBackTrackState(rEGlobalData, (byte) 52, i4);
                                    popProgState(rEGlobalData);
                                    int i21 = i4 + 4;
                                    int offset2 = i21 + getOffset(bArr, i21);
                                    i14 = offset2 + 1;
                                    b12 = bArr[offset2];
                                    b11 = b3;
                                    b10 = REOP_END;
                                }
                                b12 = b4;
                                b3 = 52;
                                b11 = b3;
                                b10 = REOP_END;
                            case 26:
                                z2 = true;
                                i4 = i14;
                                i5 = -1;
                                z3 = z2;
                                i6 = 0;
                                pushProgState(rEGlobalData, i6, i5, rEGlobalData.f10260cp, null, b13, i15);
                                if (!z3) {
                                }
                                b12 = b4;
                                b3 = 52;
                                b11 = b3;
                                b10 = REOP_END;
                                break;
                            case 27:
                                z = true;
                                i4 = i14;
                                i5 = -1;
                                i6 = 1;
                                z3 = z;
                                pushProgState(rEGlobalData, i6, i5, rEGlobalData.f10260cp, null, b13, i15);
                                if (!z3) {
                                }
                                b12 = b4;
                                b3 = 52;
                                b11 = b3;
                                b10 = REOP_END;
                                break;
                            case 28:
                                z = true;
                                i6 = 0;
                                i4 = i14;
                                i5 = 1;
                                z3 = z;
                                pushProgState(rEGlobalData, i6, i5, rEGlobalData.f10260cp, null, b13, i15);
                                if (!z3) {
                                }
                                b12 = b4;
                                b3 = 52;
                                b11 = b3;
                                b10 = REOP_END;
                                break;
                            default:
                                switch (b12) {
                                    case 45:
                                        z2 = false;
                                        i4 = i14;
                                        i5 = -1;
                                        z3 = z2;
                                        i6 = 0;
                                        pushProgState(rEGlobalData, i6, i5, rEGlobalData.f10260cp, null, b13, i15);
                                        if (!z3) {
                                        }
                                        b12 = b4;
                                        b3 = 52;
                                        b11 = b3;
                                        b10 = REOP_END;
                                        break;
                                    case 46:
                                        z = false;
                                        i4 = i14;
                                        i5 = -1;
                                        i6 = 1;
                                        z3 = z;
                                        pushProgState(rEGlobalData, i6, i5, rEGlobalData.f10260cp, null, b13, i15);
                                        if (!z3) {
                                        }
                                        b12 = b4;
                                        b3 = 52;
                                        b11 = b3;
                                        b10 = REOP_END;
                                        break;
                                    case 47:
                                        z = false;
                                        i6 = 0;
                                        i4 = i14;
                                        i5 = 1;
                                        z3 = z;
                                        pushProgState(rEGlobalData, i6, i5, rEGlobalData.f10260cp, null, b13, i15);
                                        if (!z3) {
                                        }
                                        b12 = b4;
                                        b3 = 52;
                                        b11 = b3;
                                        b10 = REOP_END;
                                        break;
                                    case 48:
                                        z = false;
                                        int offset3 = getOffset(bArr, i14);
                                        int i162 = i14 + 2;
                                        i5 = getOffset(bArr, i162) - 1;
                                        i6 = offset3;
                                        i4 = i162 + 2;
                                        z3 = z;
                                        pushProgState(rEGlobalData, i6, i5, rEGlobalData.f10260cp, null, b13, i15);
                                        if (!z3) {
                                        }
                                        b12 = b4;
                                        b3 = 52;
                                        b11 = b3;
                                        b10 = REOP_END;
                                        break;
                                    default:
                                        throw Kit.codeBug();
                                }
                        }
                    case 29:
                        int index = getIndex(bArr, i14);
                        int i22 = i14 + 2;
                        rEGlobalData.setParens(index, rEGlobalData.f10260cp, 0);
                        i2 = i22 + 1;
                        b12 = bArr[i22];
                        i14 = i2;
                        b10 = REOP_END;
                        b11 = 52;
                    case 30:
                        int index2 = getIndex(bArr, i14);
                        int i23 = i14 + 2;
                        int parensIndex = rEGlobalData.parensIndex(index2);
                        rEGlobalData.setParens(index2, parensIndex, rEGlobalData.f10260cp - parensIndex);
                        i2 = i23 + 1;
                        b12 = bArr[i23];
                        i14 = i2;
                        b10 = REOP_END;
                        b11 = 52;
                    case 31:
                        int offset4 = i14 + getOffset(bArr, i14);
                        int i24 = i14 + 2;
                        int i25 = i24 + 1;
                        b = bArr[i24];
                        int i26 = rEGlobalData.f10260cp;
                        if (reopIsSimple(b)) {
                            int simpleMatch3 = simpleMatch(rEGlobalData, str, b, bArr, i25, i, true);
                            if (simpleMatch3 < 0) {
                                i14 = offset4 + 1;
                                b12 = bArr[offset4];
                                b10 = REOP_END;
                                b11 = 52;
                            } else {
                                b2 = bArr[simpleMatch3];
                                i3 = simpleMatch3 + 1;
                                z6 = true;
                            }
                        } else {
                            b2 = b;
                            i3 = i25;
                        }
                        pushBackTrackState(rEGlobalData, bArr[offset4], offset4 + 1, i26, b13, i15);
                        b12 = b2;
                        i14 = i3;
                        b10 = REOP_END;
                        b11 = 52;
                    case 32:
                        int offset5 = i14 + getOffset(bArr, i14);
                        i2 = offset5 + 1;
                        b12 = bArr[offset5];
                        i14 = i2;
                        b10 = REOP_END;
                        b11 = 52;
                    default:
                        switch (b12) {
                            case 41:
                                int index3 = i14 + getIndex(bArr, i14);
                                int i27 = i14 + 2;
                                i3 = i27 + 1;
                                b7 = bArr[i27];
                                if (!reopIsSimple(b7) || simpleMatch(rEGlobalData, str, b7, bArr, i3, i, false) >= 0) {
                                    pushProgState(rEGlobalData, 0, 0, rEGlobalData.f10260cp, rEGlobalData.backTrackStackTop, b13, i15);
                                    pushBackTrackState(rEGlobalData, REOP_ASSERTTEST, index3);
                                    b12 = b7;
                                    i14 = i3;
                                    b10 = REOP_END;
                                    b11 = 52;
                                }
                                z6 = false;
                                i14 = i3;
                                break;
                            case 42:
                                int index4 = i14 + getIndex(bArr, i14);
                                int i28 = i14 + 2;
                                i3 = i28 + 1;
                                b7 = bArr[i28];
                                if (reopIsSimple(b7)) {
                                    b8 = 44;
                                    int simpleMatch4 = simpleMatch(rEGlobalData, str, b7, bArr, i3, i, false);
                                    if (simpleMatch4 >= 0) {
                                        if (bArr[simpleMatch4] != 44) {
                                        }
                                        z6 = false;
                                        i14 = i3;
                                        break;
                                    }
                                } else {
                                    b8 = 44;
                                }
                                pushProgState(rEGlobalData, 0, 0, rEGlobalData.f10260cp, rEGlobalData.backTrackStackTop, b13, i15);
                                pushBackTrackState(rEGlobalData, b8, index4);
                                b12 = b7;
                                i14 = i3;
                                b10 = REOP_END;
                                b11 = 52;
                            case 43:
                            case 44:
                                REProgState popProgState = popProgState(rEGlobalData);
                                rEGlobalData.f10260cp = popProgState.index;
                                rEGlobalData.backTrackStackTop = popProgState.backTrack;
                                int i29 = popProgState.continuationPc;
                                ?? r0 = popProgState.continuationOp;
                                if (b12 == 44) {
                                    z6 = !z6;
                                }
                                b13 = r0;
                                i15 = i29;
                                break;
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                                switch (b12) {
                                }
                            case 49:
                                z6 = true;
                                i14 = i15;
                                b12 = b13;
                            default:
                                switch (b12) {
                                    case 51:
                                        while (true) {
                                            REProgState popProgState2 = popProgState(rEGlobalData);
                                            if (!z6) {
                                                if (popProgState2.min == 0) {
                                                    z6 = true;
                                                }
                                                i7 = popProgState2.continuationPc;
                                                ?? r1 = popProgState2.continuationOp;
                                                int i30 = i14 + 4;
                                                i14 = i30 + getOffset(bArr, i30);
                                                b5 = r1;
                                                break;
                                            } else if (popProgState2.min == 0 && rEGlobalData.f10260cp == popProgState2.index) {
                                                i11 = popProgState2.continuationPc;
                                                ?? r12 = popProgState2.continuationOp;
                                                int i31 = i14 + 4;
                                                i14 = i31 + getOffset(bArr, i31);
                                                b6 = r12;
                                            } else {
                                                int i32 = popProgState2.min;
                                                int i33 = popProgState2.max;
                                                if (i32 != 0) {
                                                    i32--;
                                                }
                                                int i34 = i32;
                                                if (i33 != -1) {
                                                    i33--;
                                                }
                                                int i35 = i33;
                                                if (i35 == 0) {
                                                    int i36 = popProgState2.continuationPc;
                                                    ?? r13 = popProgState2.continuationOp;
                                                    int i37 = i14 + 4;
                                                    i14 = i37 + getOffset(bArr, i37);
                                                    i15 = i36;
                                                    b13 = r13;
                                                    z6 = true;
                                                    break;
                                                } else {
                                                    int i38 = i14 + 6;
                                                    byte b14 = bArr[i38];
                                                    int i39 = rEGlobalData.f10260cp;
                                                    if (reopIsSimple(b14)) {
                                                        i8 = i39;
                                                        int simpleMatch5 = simpleMatch(rEGlobalData, str, b14, bArr, i38 + 1, i, true);
                                                        if (simpleMatch5 < 0) {
                                                            boolean z8 = i34 == 0;
                                                            int i40 = popProgState2.continuationPc;
                                                            ?? r2 = popProgState2.continuationOp;
                                                            int i41 = i14 + 4;
                                                            i14 = i41 + getOffset(bArr, i41);
                                                            z6 = z8;
                                                            i15 = i40;
                                                            b13 = r2;
                                                            break;
                                                        } else {
                                                            i9 = simpleMatch5;
                                                            z4 = true;
                                                        }
                                                    } else {
                                                        i8 = i39;
                                                        z4 = z6;
                                                        i9 = i38;
                                                    }
                                                    pushProgState(rEGlobalData, i34, i35, i8, null, popProgState2.continuationOp, popProgState2.continuationPc);
                                                    if (i34 == 0) {
                                                        pushBackTrackState(rEGlobalData, (byte) 51, i14, i8, popProgState2.continuationOp, popProgState2.continuationPc);
                                                        int index5 = getIndex(bArr, i14);
                                                        int index6 = getIndex(bArr, i14 + 2);
                                                        for (int i42 = 0; i42 < index5; i42++) {
                                                            rEGlobalData.setParens(index6 + i42, -1, 0);
                                                        }
                                                    }
                                                    if (bArr[i9] != 49) {
                                                        i10 = i9 + 1;
                                                        b12 = bArr[i9];
                                                        z6 = z4;
                                                        b10 = REOP_END;
                                                        b13 = 51;
                                                        break;
                                                    } else {
                                                        z6 = z4;
                                                    }
                                                }
                                            }
                                        }
                                        i15 = i11;
                                        b13 = b6;
                                        z6 = false;
                                        break;
                                    case 52:
                                        REProgState popProgState3 = popProgState(rEGlobalData);
                                        if (!z6) {
                                            if (popProgState3.max == -1 || popProgState3.max > 0) {
                                                pushProgState(rEGlobalData, popProgState3.min, popProgState3.max, rEGlobalData.f10260cp, null, popProgState3.continuationOp, popProgState3.continuationPc);
                                                int index7 = getIndex(bArr, i14);
                                                int i43 = i14 + 2;
                                                int index8 = getIndex(bArr, i43);
                                                int i44 = i43 + 4;
                                                for (int i45 = 0; i45 < index7; i45++) {
                                                    rEGlobalData.setParens(index8 + i45, -1, 0);
                                                }
                                                i10 = i44 + 1;
                                                b12 = bArr[i44];
                                            } else {
                                                i7 = popProgState3.continuationPc;
                                                b5 = popProgState3.continuationOp;
                                                i15 = i7;
                                                b13 = b5;
                                                break;
                                            }
                                        } else if (popProgState3.min == 0 && rEGlobalData.f10260cp == popProgState3.index) {
                                            i11 = popProgState3.continuationPc;
                                            b6 = popProgState3.continuationOp;
                                            i15 = i11;
                                            b13 = b6;
                                            z6 = false;
                                            break;
                                        } else {
                                            int i46 = popProgState3.min;
                                            int i47 = popProgState3.max;
                                            if (i46 != 0) {
                                                i46--;
                                            }
                                            int i48 = i46;
                                            if (i47 != -1) {
                                                i47--;
                                            }
                                            pushProgState(rEGlobalData, i48, i47, rEGlobalData.f10260cp, null, popProgState3.continuationOp, popProgState3.continuationPc);
                                            if (i48 != 0) {
                                                int index9 = getIndex(bArr, i14);
                                                int i49 = i14 + 2;
                                                int index10 = getIndex(bArr, i49);
                                                int i50 = i49 + 4;
                                                for (int i51 = 0; i51 < index9; i51++) {
                                                    rEGlobalData.setParens(index10 + i51, -1, 0);
                                                }
                                                i10 = i50 + 1;
                                                b12 = bArr[i50];
                                            } else {
                                                int i52 = popProgState3.continuationPc;
                                                ?? r14 = popProgState3.continuationOp;
                                                pushBackTrackState(rEGlobalData, b11, i14);
                                                popProgState(rEGlobalData);
                                                int i53 = i14 + 4;
                                                int offset6 = i53 + getOffset(bArr, i53);
                                                byte b15 = bArr[offset6];
                                                i15 = i52;
                                                b13 = r14;
                                                i14 = offset6 + 1;
                                                b12 = b15;
                                                b10 = REOP_END;
                                            }
                                        }
                                        b13 = b11;
                                        b10 = REOP_END;
                                        int i54 = i10;
                                        i15 = i14;
                                        i14 = i54;
                                        break;
                                    case 53:
                                    case 54:
                                    case 55:
                                        char index11 = (char) getIndex(bArr, i14);
                                        int i55 = i14 + 2;
                                        char index12 = (char) getIndex(bArr, i55);
                                        i14 = i55 + 2;
                                        if (rEGlobalData.f10260cp != i) {
                                            char charAt = str.charAt(rEGlobalData.f10260cp);
                                            if (b12 == 55) {
                                                if (charAt != index11) {
                                                    if (classMatcher(rEGlobalData, rEGlobalData.regexp.classList[index12], charAt)) {
                                                    }
                                                }
                                                int offset42 = i14 + getOffset(bArr, i14);
                                                int i242 = i14 + 2;
                                                int i252 = i242 + 1;
                                                b = bArr[i242];
                                                int i262 = rEGlobalData.f10260cp;
                                                if (reopIsSimple(b)) {
                                                }
                                                pushBackTrackState(rEGlobalData, bArr[offset42], offset42 + 1, i262, b13, i15);
                                                b12 = b2;
                                                i14 = i3;
                                                b10 = REOP_END;
                                                b11 = 52;
                                            } else {
                                                if (b12 == 54) {
                                                    charAt = upcase(charAt);
                                                }
                                                if (charAt != index11) {
                                                    if (charAt == index12) {
                                                    }
                                                }
                                                int offset422 = i14 + getOffset(bArr, i14);
                                                int i2422 = i14 + 2;
                                                int i2522 = i2422 + 1;
                                                b = bArr[i2422];
                                                int i2622 = rEGlobalData.f10260cp;
                                                if (reopIsSimple(b)) {
                                                }
                                                pushBackTrackState(rEGlobalData, bArr[offset422], offset422 + 1, i2622, b13, i15);
                                                b12 = b2;
                                                i14 = i3;
                                                b10 = REOP_END;
                                                b11 = 52;
                                            }
                                        }
                                        z6 = false;
                                        break;
                                    default:
                                        throw Kit.codeBug("invalid bytecode");
                                }
                        }
                        break;
                }
            }
            if (!z6) {
                REBackTrackData rEBackTrackData = rEGlobalData.backTrackStackTop;
                if (rEBackTrackData == null) {
                    return false;
                }
                rEGlobalData.backTrackStackTop = rEBackTrackData.previous;
                rEGlobalData.parens = rEBackTrackData.parens;
                rEGlobalData.f10260cp = rEBackTrackData.f10257cp;
                rEGlobalData.stateStackTop = rEBackTrackData.stateStackTop;
                ?? r15 = rEBackTrackData.continuationOp;
                int i56 = rEBackTrackData.continuationPc;
                i14 = rEBackTrackData.f10259pc;
                ?? r02 = rEBackTrackData.f10258op;
                b13 = r15;
                i15 = i56;
                b10 = REOP_END;
                b11 = 52;
                b12 = r02;
            } else {
                i2 = i14 + 1;
                b12 = bArr[i14];
                i14 = i2;
                b10 = REOP_END;
                b11 = 52;
            }
        }
    }

    private static boolean matchRegExp(REGlobalData rEGlobalData, RECompiled rECompiled, String str, int i, int i2, boolean z) {
        if (rECompiled.parenCount != 0) {
            rEGlobalData.parens = new long[rECompiled.parenCount];
        } else {
            rEGlobalData.parens = null;
        }
        rEGlobalData.backTrackStackTop = null;
        rEGlobalData.stateStackTop = null;
        rEGlobalData.multiline = z || (rECompiled.flags & 4) != 0;
        rEGlobalData.regexp = rECompiled;
        int i3 = rEGlobalData.regexp.anchorCh;
        int i4 = i;
        while (i4 <= i2) {
            if (i3 >= 0) {
                while (i4 != i2) {
                    char charAt = str.charAt(i4);
                    if (charAt != i3 && ((rEGlobalData.regexp.flags & 2) == 0 || upcase(charAt) != upcase((char) i3))) {
                        i4++;
                    }
                }
                return false;
            }
            rEGlobalData.f10260cp = i4;
            rEGlobalData.skipped = i4 - i;
            for (int i5 = 0; i5 < rECompiled.parenCount; i5++) {
                rEGlobalData.parens[i5] = -1;
            }
            boolean executeREBytecode = executeREBytecode(rEGlobalData, str, i2);
            rEGlobalData.backTrackStackTop = null;
            rEGlobalData.stateStackTop = null;
            if (executeREBytecode) {
                return true;
            }
            if (i3 == -2 && !rEGlobalData.multiline) {
                rEGlobalData.skipped = i2;
                return false;
            }
            i4 = rEGlobalData.skipped + i + 1;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object executeRegExp(Context context, Scriptable scriptable, RegExpImpl regExpImpl, String str, int[] iArr, int i) {
        Object newArray;
        Scriptable scriptable2;
        NativeRegExp nativeRegExp = this;
        REGlobalData rEGlobalData = new REGlobalData();
        int i2 = iArr[0];
        int length = str.length();
        int i3 = i2 > length ? length : i2;
        SubString subString = null;
        if (!matchRegExp(rEGlobalData, nativeRegExp.f10256re, str, i3, length, regExpImpl.multiline)) {
            if (i != 2) {
                return null;
            }
            return Undefined.instance;
        }
        int i4 = rEGlobalData.f10260cp;
        iArr[0] = i4;
        int i5 = i4 - (rEGlobalData.skipped + i3);
        int i6 = i4 - i5;
        if (i == 0) {
            scriptable2 = null;
            newArray = Boolean.TRUE;
        } else {
            newArray = context.newArray(scriptable, 0);
            scriptable2 = (Scriptable) newArray;
            scriptable2.put(0, scriptable2, str.substring(i6, i6 + i5));
        }
        if (nativeRegExp.f10256re.parenCount == 0) {
            regExpImpl.parens = null;
            regExpImpl.lastParen = new SubString();
        } else {
            regExpImpl.parens = new SubString[nativeRegExp.f10256re.parenCount];
            int i7 = 0;
            while (i7 < nativeRegExp.f10256re.parenCount) {
                int parensIndex = rEGlobalData.parensIndex(i7);
                if (parensIndex != -1) {
                    subString = new SubString(str, parensIndex, rEGlobalData.parensLength(i7));
                    regExpImpl.parens[i7] = subString;
                    if (i != 0) {
                        scriptable2.put(i7 + 1, scriptable2, subString.toString());
                    }
                } else if (i != 0) {
                    scriptable2.put(i7 + 1, scriptable2, Undefined.instance);
                }
                i7++;
                nativeRegExp = this;
            }
            regExpImpl.lastParen = subString;
        }
        if (i != 0) {
            scriptable2.put("index", scriptable2, Integer.valueOf(rEGlobalData.skipped + i3));
            scriptable2.put("input", scriptable2, str);
        }
        if (regExpImpl.lastMatch == null) {
            regExpImpl.lastMatch = new SubString();
            regExpImpl.leftContext = new SubString();
            regExpImpl.rightContext = new SubString();
        }
        regExpImpl.lastMatch.str = str;
        regExpImpl.lastMatch.index = i6;
        regExpImpl.lastMatch.length = i5;
        regExpImpl.leftContext.str = str;
        if (context.getLanguageVersion() == 120) {
            regExpImpl.leftContext.index = i3;
            regExpImpl.leftContext.length = rEGlobalData.skipped;
        } else {
            regExpImpl.leftContext.index = 0;
            regExpImpl.leftContext.length = i3 + rEGlobalData.skipped;
        }
        regExpImpl.rightContext.str = str;
        regExpImpl.rightContext.index = i4;
        regExpImpl.rightContext.length = length - i4;
        return newArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getFlags() {
        return this.f10256re.flags;
    }

    private static void reportWarning(Context context, String str, String str2) {
        if (context.hasFeature(11)) {
            Context.reportWarning(ScriptRuntime.getMessage1(str, str2));
        }
    }

    private static void reportError(String str, String str2) {
        throw ScriptRuntime.constructError("SyntaxError", ScriptRuntime.getMessage1(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public int findInstanceIdInfo(String str) {
        String str2;
        int i;
        int i2;
        int length = str.length();
        if (length == 6) {
            char charAt = str.charAt(0);
            if (charAt == 'g') {
                str2 = "global";
                i = 3;
            } else {
                if (charAt == 's') {
                    str2 = MapElement.Source.SOURCE;
                    i = 2;
                }
                str2 = null;
                i = 0;
            }
        } else if (length == 9) {
            char charAt2 = str.charAt(0);
            if (charAt2 == 'l') {
                str2 = "lastIndex";
                i = 1;
            } else {
                if (charAt2 == 'm') {
                    str2 = "multiline";
                    i = 5;
                }
                str2 = null;
                i = 0;
            }
        } else {
            if (length == 10) {
                str2 = "ignoreCase";
                i = 4;
            }
            str2 = null;
            i = 0;
        }
        int i3 = (str2 == null || str2 == str || str2.equals(str)) ? i : 0;
        if (i3 == 0) {
            return super.findInstanceIdInfo(str);
        }
        if (i3 == 1) {
            i2 = this.lastIndexAttr;
        } else {
            if (i3 != 2 && i3 != 3 && i3 != 4 && i3 != 5) {
                throw new IllegalStateException();
            }
            i2 = 7;
        }
        return instanceIdInfo(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public String getInstanceIdName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? super.getInstanceIdName(i) : "multiline" : "ignoreCase" : "global" : MapElement.Source.SOURCE : "lastIndex";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public Object getInstanceIdValue(int i) {
        if (i == 1) {
            return this.lastIndex;
        }
        if (i == 2) {
            return new String(this.f10256re.source);
        }
        if (i == 3) {
            return ScriptRuntime.wrapBoolean((this.f10256re.flags & 1) != 0);
        }
        if (i == 4) {
            return ScriptRuntime.wrapBoolean((this.f10256re.flags & 2) != 0);
        }
        if (i != 5) {
            return super.getInstanceIdValue(i);
        }
        return ScriptRuntime.wrapBoolean((this.f10256re.flags & 4) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void setInstanceIdValue(int i, Object obj) {
        if (i == 1) {
            this.lastIndex = obj;
        } else {
            if (i == 2 || i == 3 || i == 4 || i == 5) {
                return;
            }
            super.setInstanceIdValue(i, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void setInstanceIdAttributes(int i, int i2) {
        if (i == 1) {
            this.lastIndexAttr = i2;
        } else {
            super.setInstanceIdAttributes(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0002. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        String str2;
        int i2 = 0;
        int i3 = 1;
        switch (i) {
            case 1:
                i2 = 2;
                str = "compile";
                String str3 = str;
                i3 = i2;
                str2 = str3;
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            case 2:
                str = "toString";
                String str32 = str;
                i3 = i2;
                str2 = str32;
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            case 3:
                str = "toSource";
                String str322 = str;
                i3 = i2;
                str2 = str322;
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            case 4:
                str2 = "exec";
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            case 5:
                str2 = "test";
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            case 6:
                str2 = RequestParameters.PREFIX;
                initPrototypeMethod(REGEXP_TAG, i, str2, i3);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(REGEXP_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case 1:
                return realThis(scriptable2, idFunctionObject).compile(context, scriptable, objArr);
            case 2:
            case 3:
                return realThis(scriptable2, idFunctionObject).toString();
            case 4:
                return realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 1);
            case 5:
                return Boolean.TRUE.equals(realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 0)) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return realThis(scriptable2, idFunctionObject).execSub(context, scriptable, objArr, 2);
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private static NativeRegExp realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (!(scriptable instanceof NativeRegExp)) {
            throw incompatibleCallError(idFunctionObject);
        }
        return (NativeRegExp) scriptable;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        String str2;
        int length = str.length();
        int i = 3;
        if (length == 4) {
            char charAt = str.charAt(0);
            if (charAt == 'e') {
                str2 = "exec";
                i = 4;
            } else {
                if (charAt == 't') {
                    i = 5;
                    str2 = "test";
                }
                str2 = null;
                i = 0;
            }
        } else if (length == 6) {
            str2 = RequestParameters.PREFIX;
            i = 6;
        } else if (length != 7) {
            if (length == 8) {
                char charAt2 = str.charAt(3);
                if (charAt2 == 'o') {
                    str2 = "toSource";
                } else if (charAt2 == 't') {
                    i = 2;
                    str2 = "toString";
                }
            }
            str2 = null;
            i = 0;
        } else {
            i = 1;
            str2 = "compile";
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
