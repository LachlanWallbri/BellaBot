package org.mozilla.javascript;

import com.fasterxml.jackson.core.base.GeneratorBase;
import java.io.Serializable;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.xml.XMLLib;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class NativeGlobal implements Serializable, IdFunctionCall {
    private static final Object FTAG = "Global";
    private static final int INVALID_UTF8 = Integer.MAX_VALUE;
    private static final int Id_decodeURI = 1;
    private static final int Id_decodeURIComponent = 2;
    private static final int Id_encodeURI = 3;
    private static final int Id_encodeURIComponent = 4;
    private static final int Id_escape = 5;
    private static final int Id_eval = 6;
    private static final int Id_isFinite = 7;
    private static final int Id_isNaN = 8;
    private static final int Id_isXMLName = 9;
    private static final int Id_new_CommonError = 14;
    private static final int Id_parseFloat = 10;
    private static final int Id_parseInt = 11;
    private static final int Id_unescape = 12;
    private static final int Id_uneval = 13;
    private static final int LAST_SCOPE_FUNCTION_ID = 13;
    private static final String URI_DECODE_RESERVED = ";/?:@&=+$,#";
    static final long serialVersionUID = 6080442165748707530L;

    private static int unHex(char c) {
        char c2 = 'A';
        if ('A' > c || c > 'F') {
            c2 = 'a';
            if ('a' > c || c > 'f') {
                if ('0' > c || c > '9') {
                    return -1;
                }
                return c - '0';
            }
        }
        return (c - c2) + 10;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0051 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void init(Context context, Scriptable scriptable, boolean z) {
        String str;
        String str2;
        int i;
        NativeGlobal nativeGlobal = new NativeGlobal();
        for (int i2 = 1; i2 <= 13; i2++) {
            switch (i2) {
                case 1:
                    str = "decodeURI";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                        idFunctionObject.sealObject();
                    }
                    idFunctionObject.exportAsScopeProperty();
                case 2:
                    str = "decodeURIComponent";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject2 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject2.exportAsScopeProperty();
                    break;
                case 3:
                    str = "encodeURI";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject22 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject22.exportAsScopeProperty();
                    break;
                case 4:
                    str = "encodeURIComponent";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject222 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject222.exportAsScopeProperty();
                    break;
                case 5:
                    str = "escape";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject2222 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject2222.exportAsScopeProperty();
                    break;
                case 6:
                    str = "eval";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject22222 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject22222.exportAsScopeProperty();
                    break;
                case 7:
                    str = "isFinite";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject222222 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject222222.exportAsScopeProperty();
                    break;
                case 8:
                    str = "isNaN";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject2222222 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject2222222.exportAsScopeProperty();
                    break;
                case 9:
                    str = "isXMLName";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject22222222 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject22222222.exportAsScopeProperty();
                    break;
                case 10:
                    str = "parseFloat";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject222222222 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject222222222.exportAsScopeProperty();
                    break;
                case 11:
                    str2 = "parseInt";
                    i = 2;
                    IdFunctionObject idFunctionObject2222222222 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject2222222222.exportAsScopeProperty();
                    break;
                case 12:
                    str = "unescape";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject22222222222 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject22222222222.exportAsScopeProperty();
                    break;
                case 13:
                    str = "uneval";
                    str2 = str;
                    i = 1;
                    IdFunctionObject idFunctionObject222222222222 = new IdFunctionObject(nativeGlobal, FTAG, i2, str2, i, scriptable);
                    if (z) {
                    }
                    idFunctionObject222222222222.exportAsScopeProperty();
                    break;
                default:
                    throw Kit.codeBug();
            }
        }
        ScriptableObject.defineProperty(scriptable, "NaN", ScriptRuntime.NaNobj, 7);
        ScriptableObject.defineProperty(scriptable, "Infinity", ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY), 7);
        ScriptableObject.defineProperty(scriptable, "undefined", Undefined.instance, 7);
        for (TopLevel.NativeErrors nativeErrors : TopLevel.NativeErrors.values()) {
            if (nativeErrors != TopLevel.NativeErrors.Error) {
                String name = nativeErrors.name();
                ScriptableObject scriptableObject = (ScriptableObject) ScriptRuntime.newBuiltinObject(context, scriptable, TopLevel.Builtins.Error, ScriptRuntime.emptyArgs);
                scriptableObject.put("name", scriptableObject, name);
                scriptableObject.put("message", scriptableObject, "");
                IdFunctionObject idFunctionObject3 = new IdFunctionObject(nativeGlobal, FTAG, 14, name, 1, scriptable);
                idFunctionObject3.markAsConstructor(scriptableObject);
                scriptableObject.put("constructor", scriptableObject, idFunctionObject3);
                scriptableObject.setAttributes("constructor", 2);
                if (z) {
                    scriptableObject.sealObject();
                    idFunctionObject3.sealObject();
                }
                idFunctionObject3.exportAsScopeProperty();
            }
        }
    }

    @Override // org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (idFunctionObject.hasTag(FTAG)) {
            int methodId = idFunctionObject.methodId();
            switch (methodId) {
                case 1:
                case 2:
                    return decode(ScriptRuntime.toString(objArr, 0), methodId == 1);
                case 3:
                case 4:
                    return encode(ScriptRuntime.toString(objArr, 0), methodId == 3);
                case 5:
                    return js_escape(objArr);
                case 6:
                    return js_eval(context, scriptable, objArr);
                case 7:
                    if (objArr.length < 1) {
                        return Boolean.FALSE;
                    }
                    return NativeNumber.isFinite(objArr[0]);
                case 8:
                    if (objArr.length >= 1) {
                        double number = ScriptRuntime.toNumber(objArr[0]);
                        if (number == number) {
                            r0 = false;
                        }
                    }
                    return ScriptRuntime.wrapBoolean(r0);
                case 9:
                    return ScriptRuntime.wrapBoolean(XMLLib.extractFromScope(scriptable).isXMLName(context, objArr.length == 0 ? Undefined.instance : objArr[0]));
                case 10:
                    return js_parseFloat(objArr);
                case 11:
                    return js_parseInt(objArr);
                case 12:
                    return js_unescape(objArr);
                case 13:
                    return ScriptRuntime.uneval(context, scriptable, objArr.length != 0 ? objArr[0] : Undefined.instance);
                case 14:
                    return NativeError.make(context, scriptable, idFunctionObject, objArr);
            }
        }
        throw idFunctionObject.unknown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002c, code lost:
    
        if (r0 != false) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Object js_parseInt(Object[] objArr) {
        char charAt;
        char charAt2;
        String scriptRuntime = ScriptRuntime.toString(objArr, 0);
        int int32 = ScriptRuntime.toInt32(objArr, 1);
        int length = scriptRuntime.length();
        if (length == 0) {
            return ScriptRuntime.NaNobj;
        }
        int i = 0;
        do {
            charAt = scriptRuntime.charAt(i);
            if (!ScriptRuntime.isStrWhiteSpaceChar(charAt)) {
                break;
            }
            i++;
        } while (i < length);
        if (charAt != '+') {
            r0 = charAt == '-';
        }
        i++;
        int i2 = 16;
        if (int32 == 0) {
            int32 = -1;
        } else {
            if (int32 < 2 || int32 > 36) {
                return ScriptRuntime.NaNobj;
            }
            if (int32 == 16 && length - i > 1 && scriptRuntime.charAt(i) == '0' && ((charAt2 = scriptRuntime.charAt(i + 1)) == 'x' || charAt2 == 'X')) {
                i += 2;
            }
        }
        if (int32 == -1) {
            int32 = 10;
            if (length - i > 1 && scriptRuntime.charAt(i) == '0') {
                int i3 = i + 1;
                char charAt3 = scriptRuntime.charAt(i3);
                if (charAt3 == 'x' || charAt3 == 'X') {
                    i += 2;
                } else if ('0' <= charAt3 && charAt3 <= '9') {
                    i2 = 8;
                    i = i3;
                }
                double stringPrefixToNumber = ScriptRuntime.stringPrefixToNumber(scriptRuntime, i, i2);
                if (r0) {
                    stringPrefixToNumber = -stringPrefixToNumber;
                }
                return ScriptRuntime.wrapNumber(stringPrefixToNumber);
            }
        }
        i2 = int32;
        double stringPrefixToNumber2 = ScriptRuntime.stringPrefixToNumber(scriptRuntime, i, i2);
        if (r0) {
        }
        return ScriptRuntime.wrapNumber(stringPrefixToNumber2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object js_parseFloat(Object[] objArr) {
        int i;
        if (objArr.length < 1) {
            return ScriptRuntime.NaNobj;
        }
        boolean z = false;
        String scriptRuntime = ScriptRuntime.toString(objArr[0]);
        int length = scriptRuntime.length();
        for (int i2 = 0; i2 != length; i2++) {
            char charAt = scriptRuntime.charAt(i2);
            if (!ScriptRuntime.isStrWhiteSpaceChar(charAt)) {
                if (charAt == '+' || charAt == '-') {
                    int i3 = i2 + 1;
                    if (i3 == length) {
                        return ScriptRuntime.NaNobj;
                    }
                    i = i3;
                    charAt = scriptRuntime.charAt(i3);
                } else {
                    i = i2;
                }
                if (charAt == 'I') {
                    if (i + 8 <= length && scriptRuntime.regionMatches(i, "Infinity", 0, 8)) {
                        return ScriptRuntime.wrapNumber(scriptRuntime.charAt(i2) == '-' ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                    }
                    return ScriptRuntime.NaNobj;
                }
                int i4 = -1;
                int i5 = -1;
                while (true) {
                    if (i < length) {
                        char charAt2 = scriptRuntime.charAt(i);
                        if (charAt2 != '+') {
                            if (charAt2 == 'E' || charAt2 == 'e') {
                                if (i4 == -1 && i != length - 1) {
                                    i4 = i;
                                    i++;
                                }
                            } else if (charAt2 != '-') {
                                if (charAt2 != '.') {
                                    switch (charAt2) {
                                        case '0':
                                        case '1':
                                        case '2':
                                        case '3':
                                        case '4':
                                        case '5':
                                        case '6':
                                        case '7':
                                        case '8':
                                        case '9':
                                            if (i4 != -1) {
                                                z = true;
                                                break;
                                            } else {
                                                break;
                                            }
                                    }
                                    i++;
                                } else if (i5 == -1) {
                                    i5 = i;
                                    i++;
                                }
                            }
                        }
                        if (i4 == i - 1) {
                            if (i == length - 1) {
                                i--;
                            } else {
                                i++;
                            }
                        }
                    }
                }
                if (i4 == -1 || z) {
                    i4 = i;
                }
                try {
                    return Double.valueOf(scriptRuntime.substring(i2, i4));
                } catch (NumberFormatException unused) {
                    return ScriptRuntime.NaNobj;
                }
            }
        }
        return ScriptRuntime.NaNobj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
    
        if ((r12 & (-8)) == 0) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Object js_escape(Object[] objArr) {
        int i;
        String scriptRuntime = ScriptRuntime.toString(objArr, 0);
        if (objArr.length > 1) {
            double number = ScriptRuntime.toNumber(objArr[1]);
            if (number == number) {
                i = (int) number;
                if (i == number) {
                }
            }
            throw Context.reportRuntimeError0("msg.bad.esc.mask");
        }
        i = 7;
        StringBuilder sb = null;
        int length = scriptRuntime.length();
        for (int i2 = 0; i2 != length; i2++) {
            char charAt = scriptRuntime.charAt(i2);
            if (i == 0 || ((charAt < '0' || charAt > '9') && ((charAt < 'A' || charAt > 'Z') && !((charAt >= 'a' && charAt <= 'z') || charAt == '@' || charAt == '*' || charAt == '_' || charAt == '-' || charAt == '.' || ((i & 4) != 0 && (charAt == '/' || charAt == '+')))))) {
                if (sb == null) {
                    sb = new StringBuilder(length + 3);
                    sb.append(scriptRuntime);
                    sb.setLength(i2);
                }
                int i3 = 2;
                if (charAt >= 256) {
                    sb.append('%');
                    sb.append('u');
                    i3 = 4;
                } else if (charAt == ' ' && i == 2) {
                    sb.append('+');
                } else {
                    sb.append('%');
                }
                for (int i4 = (i3 - 1) * 4; i4 >= 0; i4 -= 4) {
                    int i5 = (charAt >> i4) & 15;
                    sb.append((char) (i5 < 10 ? i5 + 48 : i5 + 55));
                }
            } else if (sb != null) {
                sb.append(charAt);
            }
        }
        return sb == null ? scriptRuntime : sb.toString();
    }

    private Object js_unescape(Object[] objArr) {
        int i;
        int i2;
        String scriptRuntime = ScriptRuntime.toString(objArr, 0);
        int indexOf = scriptRuntime.indexOf(37);
        if (indexOf < 0) {
            return scriptRuntime;
        }
        int length = scriptRuntime.length();
        char[] charArray = scriptRuntime.toCharArray();
        int i3 = indexOf;
        while (indexOf != length) {
            char c = charArray[indexOf];
            indexOf++;
            if (c == '%' && indexOf != length) {
                if (charArray[indexOf] == 'u') {
                    i2 = indexOf + 1;
                    i = indexOf + 5;
                } else {
                    i = indexOf + 2;
                    i2 = indexOf;
                }
                if (i <= length) {
                    int i4 = 0;
                    while (i2 != i) {
                        i4 = Kit.xDigitToInt(charArray[i2], i4);
                        i2++;
                    }
                    if (i4 >= 0) {
                        c = (char) i4;
                        indexOf = i;
                    }
                }
            }
            charArray[i3] = c;
            i3++;
        }
        return new String(charArray, 0, i3);
    }

    private Object js_eval(Context context, Scriptable scriptable, Object[] objArr) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        return ScriptRuntime.evalSpecial(context, topLevelScope, topLevelScope, objArr, "eval code", 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEvalFunction(Object obj) {
        if (!(obj instanceof IdFunctionObject)) {
            return false;
        }
        IdFunctionObject idFunctionObject = (IdFunctionObject) obj;
        return idFunctionObject.hasTag(FTAG) && idFunctionObject.methodId() == 6;
    }

    @Deprecated
    public static EcmaError constructError(Context context, String str, String str2, Scriptable scriptable) {
        return ScriptRuntime.constructError(str, str2);
    }

    @Deprecated
    public static EcmaError constructError(Context context, String str, String str2, Scriptable scriptable, String str3, int i, int i2, String str4) {
        return ScriptRuntime.constructError(str, str2, str3, i, str4, i2);
    }

    private static String encode(String str, boolean z) {
        int length = str.length();
        StringBuilder sb = null;
        int i = 0;
        byte[] bArr = null;
        while (i != length) {
            char charAt = str.charAt(i);
            sb = sb;
            if (!encodeUnescaped(charAt, z)) {
                if (sb == null) {
                    StringBuilder sb2 = new StringBuilder(length + 3);
                    sb2.append(str);
                    sb2.setLength(i);
                    bArr = new byte[6];
                    sb = sb2;
                }
                if (56320 <= charAt && charAt <= 57343) {
                    throw uriError();
                }
                int i2 = charAt;
                if (charAt >= 55296) {
                    i2 = charAt;
                    if (56319 >= charAt) {
                        i++;
                        if (i == length) {
                            throw uriError();
                        }
                        int charAt2 = str.charAt(i);
                        if (56320 > charAt2 || charAt2 > 57343) {
                            throw uriError();
                        }
                        i2 = ((charAt - GeneratorBase.SURR1_FIRST) << 10) + (charAt2 - GeneratorBase.SURR2_FIRST) + 65536;
                    }
                }
                int oneUcs4ToUtf8Char = oneUcs4ToUtf8Char(bArr, i2);
                for (int i3 = 0; i3 < oneUcs4ToUtf8Char; i3++) {
                    int i4 = bArr[i3] & 255;
                    sb.append('%');
                    sb.append(toHexChar(i4 >>> 4));
                    sb.append(toHexChar(i4 & 15));
                }
            } else if (sb != null) {
                sb.append(charAt);
            }
            i++;
            sb = sb;
        }
        return sb == null ? str : sb.toString();
    }

    private static char toHexChar(int i) {
        if ((i >> 4) != 0) {
            Kit.codeBug();
        }
        return (char) (i < 10 ? i + 48 : (i - 10) + 65);
    }

    private static int unHex(char c, char c2) {
        int unHex = unHex(c);
        int unHex2 = unHex(c2);
        if (unHex < 0 || unHex2 < 0) {
            return -1;
        }
        return (unHex << 4) | unHex2;
    }

    private static String decode(String str, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        char c;
        int length = str.length();
        char[] cArr = null;
        int i5 = 0;
        int i6 = 0;
        while (i5 != length) {
            char charAt = str.charAt(i5);
            if (charAt != '%') {
                if (cArr != null) {
                    cArr[i6] = charAt;
                    i6++;
                }
                i5++;
            } else {
                if (cArr == null) {
                    cArr = new char[length];
                    str.getChars(0, i5, cArr, 0);
                    i6 = i5;
                }
                int i7 = i5 + 3;
                if (i7 > length) {
                    throw uriError();
                }
                int unHex = unHex(str.charAt(i5 + 1), str.charAt(i5 + 2));
                if (unHex < 0) {
                    throw uriError();
                }
                if ((unHex & 128) != 0) {
                    if ((unHex & 192) == 128) {
                        throw uriError();
                    }
                    if ((unHex & 32) == 0) {
                        unHex &= 31;
                        i = 1;
                        i2 = 128;
                    } else {
                        if ((unHex & 16) == 0) {
                            unHex &= 15;
                            i3 = 2048;
                            i4 = 2;
                        } else if ((unHex & 8) == 0) {
                            unHex &= 7;
                            i = 3;
                            i2 = 65536;
                        } else if ((unHex & 4) == 0) {
                            unHex &= 3;
                            i3 = 2097152;
                            i4 = 4;
                        } else {
                            if ((unHex & 2) != 0) {
                                throw uriError();
                            }
                            i = 5;
                            unHex &= 1;
                            i2 = 67108864;
                        }
                        int i8 = i4;
                        i2 = i3;
                        i = i8;
                    }
                    if ((i * 3) + i7 > length) {
                        throw uriError();
                    }
                    for (int i9 = 0; i9 != i; i9++) {
                        if (str.charAt(i7) != '%') {
                            throw uriError();
                        }
                        int unHex2 = unHex(str.charAt(i7 + 1), str.charAt(i7 + 2));
                        if (unHex2 < 0 || (unHex2 & 192) != 128) {
                            throw uriError();
                        }
                        unHex = (unHex << 6) | (unHex2 & 63);
                        i7 += 3;
                    }
                    if (unHex < i2 || (unHex >= 55296 && unHex <= 57343)) {
                        unHex = Integer.MAX_VALUE;
                    } else if (unHex == 65534 || unHex == 65535) {
                        unHex = 65533;
                    }
                    if (unHex >= 65536) {
                        int i10 = unHex - 65536;
                        if (i10 > 1048575) {
                            throw uriError();
                        }
                        char c2 = (char) ((i10 >>> 10) + GeneratorBase.SURR1_FIRST);
                        char c3 = (char) ((i10 & 1023) + GeneratorBase.SURR2_FIRST);
                        cArr[i6] = c2;
                        c = c3;
                        i6++;
                        if (z || URI_DECODE_RESERVED.indexOf(c) < 0) {
                            cArr[i6] = c;
                            i6++;
                        } else {
                            while (i5 != i7) {
                                cArr[i6] = str.charAt(i5);
                                i5++;
                                i6++;
                            }
                        }
                        i5 = i7;
                    }
                }
                c = (char) unHex;
                if (z) {
                }
                cArr[i6] = c;
                i6++;
                i5 = i7;
            }
        }
        return cArr == null ? str : new String(cArr, 0, i6);
    }

    private static boolean encodeUnescaped(char c, boolean z) {
        if (('A' > c || c > 'Z') && (('a' > c || c > 'z') && (('0' > c || c > '9') && "-_.!~*'()".indexOf(c) < 0))) {
            return z && URI_DECODE_RESERVED.indexOf(c) >= 0;
        }
        return true;
    }

    private static EcmaError uriError() {
        return ScriptRuntime.constructError("URIError", ScriptRuntime.getMessage0("msg.bad.uri"));
    }

    private static int oneUcs4ToUtf8Char(byte[] bArr, int i) {
        if ((i & (-128)) == 0) {
            bArr[0] = (byte) i;
            return 1;
        }
        int i2 = i >>> 11;
        int i3 = 2;
        while (i2 != 0) {
            i2 >>>= 5;
            i3++;
        }
        int i4 = i3;
        while (true) {
            i4--;
            if (i4 > 0) {
                bArr[i4] = (byte) ((i & 63) | 128);
                i >>>= 6;
            } else {
                bArr[0] = (byte) ((256 - (1 << (8 - i3))) + i);
                return i3;
            }
        }
    }
}
