package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.RegExpProxy;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class RegExpImpl implements RegExpProxy {
    protected String input;
    protected SubString lastMatch;
    protected SubString lastParen;
    protected SubString leftContext;
    protected boolean multiline;
    protected SubString[] parens;
    protected SubString rightContext;

    @Override // org.mozilla.javascript.RegExpProxy
    public boolean isRegExp(Scriptable scriptable) {
        return scriptable instanceof NativeRegExp;
    }

    @Override // org.mozilla.javascript.RegExpProxy
    public Object compileRegExp(Context context, String str, String str2) {
        return NativeRegExp.compileRE(context, str, str2, false);
    }

    @Override // org.mozilla.javascript.RegExpProxy
    public Scriptable wrapRegExp(Context context, Scriptable scriptable, Object obj) {
        return new NativeRegExp(scriptable, (RECompiled) obj);
    }

    @Override // org.mozilla.javascript.RegExpProxy
    public Object action(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, int i) {
        String scriptRuntime;
        NativeRegExp nativeRegExp;
        String scriptRuntime2;
        Function function;
        Object obj;
        GlobData globData = new GlobData();
        globData.mode = i;
        globData.str = ScriptRuntime.toString(scriptable2);
        if (i == 1) {
            return globData.arrayobj == null ? matchOrReplace(context, scriptable, scriptable2, objArr, this, globData, createRegExp(context, scriptable, objArr, 1, false)) : globData.arrayobj;
        }
        if (i != 2) {
            if (i == 3) {
                return matchOrReplace(context, scriptable, scriptable2, objArr, this, globData, createRegExp(context, scriptable, objArr, 1, false));
            }
            throw Kit.codeBug();
        }
        boolean z = (objArr.length > 0 && (objArr[0] instanceof NativeRegExp)) || objArr.length > 2;
        if (z) {
            nativeRegExp = createRegExp(context, scriptable, objArr, 2, true);
            scriptRuntime = null;
        } else {
            scriptRuntime = ScriptRuntime.toString(objArr.length < 1 ? Undefined.instance : objArr[0]);
            nativeRegExp = null;
        }
        Object obj2 = objArr.length < 2 ? Undefined.instance : objArr[1];
        if (obj2 instanceof Function) {
            function = (Function) obj2;
            scriptRuntime2 = null;
        } else {
            scriptRuntime2 = ScriptRuntime.toString(obj2);
            function = null;
        }
        globData.lambda = function;
        globData.repstr = scriptRuntime2;
        globData.dollar = scriptRuntime2 == null ? -1 : scriptRuntime2.indexOf(36);
        globData.charBuf = null;
        globData.leftIndex = 0;
        if (z) {
            obj = matchOrReplace(context, scriptable, scriptable2, objArr, this, globData, nativeRegExp);
        } else {
            String str = globData.str;
            int indexOf = str.indexOf(scriptRuntime);
            if (indexOf >= 0) {
                int length = scriptRuntime.length();
                this.lastParen = null;
                this.leftContext = new SubString(str, 0, indexOf);
                this.lastMatch = new SubString(str, indexOf, length);
                this.rightContext = new SubString(str, indexOf + length, (str.length() - indexOf) - length);
                obj = Boolean.TRUE;
            } else {
                obj = Boolean.FALSE;
            }
        }
        if (globData.charBuf == null) {
            if (globData.global || obj == null || !obj.equals(Boolean.TRUE)) {
                return globData.str;
            }
            SubString subString = this.leftContext;
            replace_glob(globData, context, scriptable, this, subString.index, subString.length);
        }
        SubString subString2 = this.rightContext;
        globData.charBuf.append((CharSequence) subString2.str, subString2.index, subString2.index + subString2.length);
        return globData.charBuf.toString();
    }

    private static NativeRegExp createRegExp(Context context, Scriptable scriptable, Object[] objArr, int i, boolean z) {
        NativeRegExp nativeRegExp;
        String str;
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        if (objArr.length == 0 || objArr[0] == Undefined.instance) {
            nativeRegExp = new NativeRegExp(topLevelScope, NativeRegExp.compileRE(context, "", "", false));
        } else {
            if (objArr[0] instanceof NativeRegExp) {
                return (NativeRegExp) objArr[0];
            }
            String scriptRuntime = ScriptRuntime.toString(objArr[0]);
            if (i < objArr.length) {
                objArr[0] = scriptRuntime;
                str = ScriptRuntime.toString(objArr[i]);
            } else {
                str = null;
            }
            nativeRegExp = new NativeRegExp(topLevelScope, NativeRegExp.compileRE(context, scriptRuntime, str, z));
        }
        return nativeRegExp;
    }

    private static Object matchOrReplace(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, RegExpImpl regExpImpl, GlobData globData, NativeRegExp nativeRegExp) {
        String str = globData.str;
        globData.global = (nativeRegExp.getFlags() & 1) != 0;
        int[] iArr = {0};
        Object obj = null;
        if (globData.mode == 3) {
            Object executeRegExp = nativeRegExp.executeRegExp(context, scriptable, regExpImpl, str, iArr, 0);
            if (executeRegExp != null && executeRegExp.equals(Boolean.TRUE)) {
                return Integer.valueOf(regExpImpl.leftContext.length);
            }
            return -1;
        }
        if (!globData.global) {
            return nativeRegExp.executeRegExp(context, scriptable, regExpImpl, str, iArr, globData.mode == 2 ? 0 : 1);
        }
        nativeRegExp.lastIndex = Double.valueOf(0.0d);
        int i = 0;
        while (iArr[0] <= str.length()) {
            Object executeRegExp2 = nativeRegExp.executeRegExp(context, scriptable, regExpImpl, str, iArr, 0);
            if (executeRegExp2 != null && executeRegExp2.equals(Boolean.TRUE)) {
                if (globData.mode == 1) {
                    match_glob(globData, context, scriptable, i, regExpImpl);
                } else {
                    if (globData.mode != 2) {
                        Kit.codeBug();
                    }
                    SubString subString = regExpImpl.lastMatch;
                    int i2 = globData.leftIndex;
                    int i3 = subString.index - i2;
                    globData.leftIndex = subString.index + subString.length;
                    replace_glob(globData, context, scriptable, regExpImpl, i2, i3);
                }
                if (regExpImpl.lastMatch.length == 0) {
                    if (iArr[0] != str.length()) {
                        iArr[0] = iArr[0] + 1;
                    }
                }
                i++;
                obj = executeRegExp2;
            }
            return executeRegExp2;
        }
        return obj;
    }

    @Override // org.mozilla.javascript.RegExpProxy
    public int find_split(Context context, Scriptable scriptable, String str, String str2, Scriptable scriptable2, int[] iArr, int[] iArr2, boolean[] zArr, String[][] strArr) {
        int i;
        int i2 = iArr[0];
        int length = str.length();
        int languageVersion = context.getLanguageVersion();
        NativeRegExp nativeRegExp = (NativeRegExp) scriptable2;
        while (true) {
            int i3 = iArr[0];
            iArr[0] = i2;
            if (nativeRegExp.executeRegExp(context, scriptable, this, str, iArr, 0) != Boolean.TRUE) {
                iArr[0] = i3;
                iArr2[0] = 1;
                zArr[0] = false;
                return length;
            }
            i = iArr[0];
            iArr[0] = i3;
            zArr[0] = true;
            iArr2[0] = this.lastMatch.length;
            if (iArr2[0] != 0 || i != iArr[0]) {
                break;
            }
            if (i != length) {
                i2 = i + 1;
            } else if (languageVersion == 120) {
                iArr2[0] = 1;
            } else {
                i = -1;
            }
        }
        i -= iArr2[0];
        SubString[] subStringArr = this.parens;
        int length2 = subStringArr == null ? 0 : subStringArr.length;
        strArr[0] = new String[length2];
        for (int i4 = 0; i4 < length2; i4++) {
            strArr[0][i4] = getParenSubString(i4).toString();
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubString getParenSubString(int i) {
        SubString subString;
        SubString[] subStringArr = this.parens;
        return (subStringArr == null || i >= subStringArr.length || (subString = subStringArr[i]) == null) ? new SubString() : subString;
    }

    private static void match_glob(GlobData globData, Context context, Scriptable scriptable, int i, RegExpImpl regExpImpl) {
        if (globData.arrayobj == null) {
            globData.arrayobj = context.newArray(scriptable, 0);
        }
        globData.arrayobj.put(i, globData.arrayobj, regExpImpl.lastMatch.toString());
    }

    private static void replace_glob(GlobData globData, Context context, Scriptable scriptable, RegExpImpl regExpImpl, int i, int i2) {
        String str;
        int length;
        int i3;
        if (globData.lambda != null) {
            SubString[] subStringArr = regExpImpl.parens;
            int length2 = subStringArr == null ? 0 : subStringArr.length;
            Object[] objArr = new Object[length2 + 3];
            objArr[0] = regExpImpl.lastMatch.toString();
            for (int i4 = 0; i4 < length2; i4++) {
                SubString subString = subStringArr[i4];
                if (subString != null) {
                    objArr[i4 + 1] = subString.toString();
                } else {
                    objArr[i4 + 1] = Undefined.instance;
                }
            }
            objArr[length2 + 1] = Integer.valueOf(regExpImpl.leftContext.length);
            objArr[length2 + 2] = globData.str;
            if (regExpImpl != ScriptRuntime.getRegExpProxy(context)) {
                Kit.codeBug();
            }
            RegExpImpl regExpImpl2 = new RegExpImpl();
            regExpImpl2.multiline = regExpImpl.multiline;
            regExpImpl2.input = regExpImpl.input;
            ScriptRuntime.setRegExpProxy(context, regExpImpl2);
            try {
                Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
                str = ScriptRuntime.toString(globData.lambda.call(context, topLevelScope, topLevelScope, objArr));
                ScriptRuntime.setRegExpProxy(context, regExpImpl);
                length = str.length();
            } catch (Throwable th) {
                ScriptRuntime.setRegExpProxy(context, regExpImpl);
                throw th;
            }
        } else {
            str = null;
            length = globData.repstr.length();
            if (globData.dollar >= 0) {
                int[] iArr = new int[1];
                int i5 = globData.dollar;
                do {
                    SubString interpretDollar = interpretDollar(context, regExpImpl, globData.repstr, i5, iArr);
                    if (interpretDollar != null) {
                        length += interpretDollar.length - iArr[0];
                        i3 = i5 + iArr[0];
                    } else {
                        i3 = i5 + 1;
                    }
                    i5 = globData.repstr.indexOf(36, i3);
                } while (i5 >= 0);
            }
        }
        int i6 = length + i2 + regExpImpl.rightContext.length;
        StringBuilder sb = globData.charBuf;
        if (sb == null) {
            sb = new StringBuilder(i6);
            globData.charBuf = sb;
        } else {
            sb.ensureCapacity(globData.charBuf.length() + i6);
        }
        sb.append((CharSequence) regExpImpl.leftContext.str, i, i2 + i);
        if (globData.lambda != null) {
            sb.append(str);
        } else {
            do_replace(globData, context, regExpImpl);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0082 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static SubString interpretDollar(Context context, RegExpImpl regExpImpl, String str, int i, int[] iArr) {
        int length;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (str.charAt(i) != '$') {
            Kit.codeBug();
        }
        int languageVersion = context.getLanguageVersion();
        if ((languageVersion != 0 && languageVersion <= 140 && i > 0 && str.charAt(i - 1) == '\\') || (i2 = i + 1) >= (length = str.length())) {
            return null;
        }
        char charAt = str.charAt(i2);
        if (NativeRegExp.isDigit(charAt)) {
            if (languageVersion != 0 && languageVersion <= 140) {
                if (charAt != '0') {
                    i3 = i;
                    i4 = 0;
                    while (true) {
                        i3++;
                        if (i3 >= length) {
                            break;
                        }
                        char charAt2 = str.charAt(i3);
                        if (!NativeRegExp.isDigit(charAt2) || (i6 = (i4 * 10) + (charAt2 - '0')) < i4) {
                            break;
                        }
                        i4 = i6;
                    }
                } else {
                    return null;
                }
            } else {
                SubString[] subStringArr = regExpImpl.parens;
                int length2 = subStringArr == null ? 0 : subStringArr.length;
                int i7 = charAt - '0';
                if (i7 > length2) {
                    return null;
                }
                int i8 = i + 2;
                if (i8 < length) {
                    char charAt3 = str.charAt(i8);
                    if (NativeRegExp.isDigit(charAt3) && (i5 = (i7 * 10) + (charAt3 - '0')) <= length2) {
                        i3 = i8 + 1;
                        i4 = i5;
                        if (i4 == 0) {
                            return null;
                        }
                    }
                }
                i3 = i8;
                i4 = i7;
                if (i4 == 0) {
                }
            }
            iArr[0] = i3 - i;
            return regExpImpl.getParenSubString(i4 - 1);
        }
        iArr[0] = 2;
        if (charAt == '$') {
            return new SubString("$");
        }
        if (charAt == '+') {
            return regExpImpl.lastParen;
        }
        if (charAt == '`') {
            if (languageVersion == 120) {
                regExpImpl.leftContext.index = 0;
                regExpImpl.leftContext.length = regExpImpl.lastMatch.index;
            }
            return regExpImpl.leftContext;
        }
        if (charAt == '&') {
            return regExpImpl.lastMatch;
        }
        if (charAt != '\'') {
            return null;
        }
        return regExpImpl.rightContext;
    }

    private static void do_replace(GlobData globData, Context context, RegExpImpl regExpImpl) {
        int i;
        StringBuilder sb = globData.charBuf;
        String str = globData.repstr;
        int i2 = globData.dollar;
        int i3 = 0;
        if (i2 != -1) {
            int[] iArr = new int[1];
            int i4 = 0;
            do {
                sb.append(str.substring(i4, i2));
                SubString interpretDollar = interpretDollar(context, regExpImpl, str, i2, iArr);
                if (interpretDollar != null) {
                    int i5 = interpretDollar.length;
                    if (i5 > 0) {
                        sb.append((CharSequence) interpretDollar.str, interpretDollar.index, interpretDollar.index + i5);
                    }
                    i4 = iArr[0] + i2;
                    i = i2 + iArr[0];
                } else {
                    i4 = i2;
                    i = i2 + 1;
                }
                i2 = str.indexOf(36, i);
            } while (i2 >= 0);
            i3 = i4;
        }
        int length = str.length();
        if (length > i3) {
            sb.append(str.substring(i3, length));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.mozilla.javascript.RegExpProxy
    public Object js_split(Context context, Scriptable scriptable, String str, Object[] objArr) {
        RegExpProxy regExpProxy;
        Scriptable scriptable2;
        String str2;
        boolean z;
        Object substring;
        Scriptable newArray = context.newArray(scriptable, 0);
        byte b = objArr.length > 1 && objArr[1] != Undefined.instance;
        long j = 0;
        if (b != false) {
            j = ScriptRuntime.toUint32(objArr[1]);
            if (j > str.length()) {
                j = str.length() + 1;
            }
        }
        long j2 = j;
        if (objArr.length < 1 || objArr[0] == Undefined.instance) {
            newArray.put(0, newArray, str);
            return newArray;
        }
        int[] iArr = new int[1];
        if (objArr[0] instanceof Scriptable) {
            RegExpProxy regExpProxy2 = ScriptRuntime.getRegExpProxy(context);
            if (regExpProxy2 != null) {
                Scriptable scriptable3 = (Scriptable) objArr[0];
                if (regExpProxy2.isRegExp(scriptable3)) {
                    regExpProxy = regExpProxy2;
                    scriptable2 = scriptable3;
                }
            }
            regExpProxy = regExpProxy2;
            scriptable2 = null;
        } else {
            regExpProxy = null;
            scriptable2 = null;
        }
        if (scriptable2 == null) {
            String scriptRuntime = ScriptRuntime.toString(objArr[0]);
            iArr[0] = scriptRuntime.length();
            str2 = scriptRuntime;
        } else {
            str2 = null;
        }
        int[] iArr2 = {0};
        boolean[] zArr = {false};
        String[][] strArr = {null};
        int languageVersion = context.getLanguageVersion();
        int i = 0;
        while (true) {
            int i2 = i;
            int i3 = languageVersion;
            String[][] strArr2 = strArr;
            boolean[] zArr2 = zArr;
            int[] iArr3 = iArr2;
            int[] iArr4 = iArr;
            int find_split = find_split(context, scriptable, str, str2, languageVersion, regExpProxy, scriptable2, iArr2, iArr, zArr2, strArr2);
            if (find_split < 0 || ((b == true && i2 >= j2) || find_split > str.length())) {
                break;
            }
            if (str.length() == 0) {
                substring = str;
                z = false;
            } else {
                z = false;
                substring = str.substring(iArr3[0], find_split);
            }
            newArray.put(i2, newArray, substring);
            i = i2 + 1;
            if (scriptable2 != null && zArr2[z ? 1 : 0]) {
                int length = strArr2[z ? 1 : 0].length;
                for (int i4 = z ? 1 : 0; i4 < length && (b == false || i < j2); i4++) {
                    newArray.put(i, newArray, strArr2[z ? 1 : 0][i4]);
                    i++;
                }
                zArr2[z ? 1 : 0] = z;
            }
            iArr3[z ? 1 : 0] = find_split + iArr4[z ? 1 : 0];
            if (i3 < 130 && i3 != 0 && b != true && iArr3[z ? 1 : 0] == str.length()) {
                break;
            }
            languageVersion = i3;
            strArr = strArr2;
            zArr = zArr2;
            iArr2 = iArr3;
            iArr = iArr4;
        }
        return newArray;
    }

    private static int find_split(Context context, Scriptable scriptable, String str, String str2, int i, RegExpProxy regExpProxy, Scriptable scriptable2, int[] iArr, int[] iArr2, boolean[] zArr, String[][] strArr) {
        int indexOf;
        int i2 = iArr[0];
        int length = str.length();
        if (i == 120 && scriptable2 == null && str2.length() == 1 && str2.charAt(0) == ' ') {
            if (i2 == 0) {
                while (i2 < length && Character.isWhitespace(str.charAt(i2))) {
                    i2++;
                }
                iArr[0] = i2;
            }
            if (i2 == length) {
                return -1;
            }
            while (i2 < length && !Character.isWhitespace(str.charAt(i2))) {
                i2++;
            }
            int i3 = i2;
            while (i3 < length && Character.isWhitespace(str.charAt(i3))) {
                i3++;
            }
            iArr2[0] = i3 - i2;
            return i2;
        }
        if (i2 > length) {
            return -1;
        }
        if (scriptable2 != null) {
            return regExpProxy.find_split(context, scriptable, str, str2, scriptable2, iArr, iArr2, zArr, strArr);
        }
        if (i != 0 && i < 130 && length == 0) {
            return -1;
        }
        if (str2.length() != 0) {
            return (iArr[0] < length && (indexOf = str.indexOf(str2, iArr[0])) != -1) ? indexOf : length;
        }
        if (i != 120) {
            if (i2 == length) {
                return -1;
            }
            return i2 + 1;
        }
        if (i2 != length) {
            return i2 + 1;
        }
        iArr2[0] = 1;
        return i2;
    }
}
