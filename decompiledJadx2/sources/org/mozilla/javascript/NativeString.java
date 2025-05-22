package org.mozilla.javascript;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import java.text.Collator;
import java.text.Normalizer;
import kotlin.text.Typography;
import org.mozilla.javascript.regexp.NativeRegExp;
import org.simpleframework.xml.strategy.Name;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class NativeString extends IdScriptableObject {
    private static final int ConstructorId_charAt = -5;
    private static final int ConstructorId_charCodeAt = -6;
    private static final int ConstructorId_concat = -14;
    private static final int ConstructorId_equalsIgnoreCase = -30;
    private static final int ConstructorId_fromCharCode = -1;
    private static final int ConstructorId_indexOf = -7;
    private static final int ConstructorId_lastIndexOf = -8;
    private static final int ConstructorId_localeCompare = -34;
    private static final int ConstructorId_match = -31;
    private static final int ConstructorId_replace = -33;
    private static final int ConstructorId_search = -32;
    private static final int ConstructorId_slice = -15;
    private static final int ConstructorId_split = -9;
    private static final int ConstructorId_substr = -13;
    private static final int ConstructorId_substring = -10;
    private static final int ConstructorId_toLocaleLowerCase = -35;
    private static final int ConstructorId_toLowerCase = -11;
    private static final int ConstructorId_toUpperCase = -12;
    private static final int Id_anchor = 28;
    private static final int Id_big = 21;
    private static final int Id_blink = 22;
    private static final int Id_bold = 16;
    private static final int Id_charAt = 5;
    private static final int Id_charCodeAt = 6;
    private static final int Id_codePointAt = 45;
    private static final int Id_concat = 14;
    private static final int Id_constructor = 1;
    private static final int Id_endsWith = 42;
    private static final int Id_equals = 29;
    private static final int Id_equalsIgnoreCase = 30;
    private static final int Id_fixed = 18;
    private static final int Id_fontcolor = 26;
    private static final int Id_fontsize = 25;
    private static final int Id_includes = 40;
    private static final int Id_indexOf = 7;
    private static final int Id_italics = 17;
    private static final int Id_lastIndexOf = 8;
    private static final int Id_length = 1;
    private static final int Id_link = 27;
    private static final int Id_localeCompare = 34;
    private static final int Id_match = 31;
    private static final int Id_normalize = 43;
    private static final int Id_repeat = 44;
    private static final int Id_replace = 33;
    private static final int Id_search = 32;
    private static final int Id_slice = 15;
    private static final int Id_small = 20;
    private static final int Id_split = 9;
    private static final int Id_startsWith = 41;
    private static final int Id_strike = 19;
    private static final int Id_sub = 24;
    private static final int Id_substr = 13;
    private static final int Id_substring = 10;
    private static final int Id_sup = 23;
    private static final int Id_toLocaleLowerCase = 35;
    private static final int Id_toLocaleUpperCase = 36;
    private static final int Id_toLowerCase = 11;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_toUpperCase = 12;
    private static final int Id_trim = 37;
    private static final int Id_trimLeft = 38;
    private static final int Id_trimRight = 39;
    private static final int Id_valueOf = 4;
    private static final int MAX_INSTANCE_ID = 1;
    private static final int MAX_PROTOTYPE_ID = 46;
    private static final Object STRING_TAG = "String";
    private static final int SymbolId_iterator = 46;
    static final long serialVersionUID = 920268368584188687L;
    private CharSequence string;

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "String";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public int getMaxInstanceId() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Scriptable scriptable, boolean z) {
        new NativeString("").exportAsJSClass(46, scriptable, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeString(CharSequence charSequence) {
        this.string = charSequence;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public int findInstanceIdInfo(String str) {
        if (str.equals(Name.LENGTH)) {
            return instanceIdInfo(7, 1);
        }
        return super.findInstanceIdInfo(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public String getInstanceIdName(int i) {
        return i == 1 ? Name.LENGTH : super.getInstanceIdName(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public Object getInstanceIdValue(int i) {
        if (i == 1) {
            return ScriptRuntime.wrapInt(this.string.length());
        }
        return super.getInstanceIdValue(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -1, "fromCharCode", 1);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -5, "charAt", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -6, "charCodeAt", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -7, "indexOf", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -8, "lastIndexOf", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -9, "split", 3);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -10, "substring", 3);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -11, "toLowerCase", 1);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -12, "toUpperCase", 1);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -13, "substr", 3);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -14, "concat", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -15, "slice", 3);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_equalsIgnoreCase, "equalsIgnoreCase", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_match, "match", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_search, "search", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, -33, "replace", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_localeCompare, "localeCompare", 2);
        addIdFunctionProperty(idFunctionObject, STRING_TAG, ConstructorId_toLocaleLowerCase, "toLocaleLowerCase", 1);
        super.fillConstructorProperties(idFunctionObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0015. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        String str2;
        String str3;
        int i2;
        String str4;
        if (i == 46) {
            initPrototypeMethod(STRING_TAG, i, SymbolKey.ITERATOR, "[Symbol.iterator]", 0);
            return;
        }
        switch (i) {
            case 1:
                str = "constructor";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 2:
                str2 = "toString";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 3:
                str2 = "toSource";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 4:
                str2 = "valueOf";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 5:
                str = "charAt";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 6:
                str = "charCodeAt";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 7:
                str = "indexOf";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 8:
                str = "lastIndexOf";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 9:
                str3 = "split";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 10:
                str3 = "substring";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 11:
                str2 = "toLowerCase";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 12:
                str2 = "toUpperCase";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 13:
                str3 = "substr";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 14:
                str = "concat";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 15:
                str3 = "slice";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 16:
                str2 = "bold";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 17:
                str2 = "italics";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 18:
                str2 = "fixed";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 19:
                str2 = "strike";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 20:
                str2 = "small";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 21:
                str2 = "big";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 22:
                str2 = "blink";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 23:
                str2 = "sup";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 24:
                str2 = "sub";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 25:
                str2 = "fontsize";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 26:
                str2 = "fontcolor";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 27:
                str2 = "link";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 28:
                str2 = "anchor";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 29:
                str = "equals";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 30:
                str = "equalsIgnoreCase";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 31:
                str = "match";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 32:
                str = "search";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 33:
                str3 = "replace";
                i2 = 2;
                str4 = str3;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 34:
                str = "localeCompare";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 35:
                str2 = "toLocaleLowerCase";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 36:
                str2 = "toLocaleUpperCase";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 37:
                str2 = "trim";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 38:
                str2 = "trimLeft";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 39:
                str2 = "trimRight";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 40:
                str = "includes";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 41:
                str = "startsWith";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 42:
                str = "endsWith";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 43:
                str2 = "normalize";
                str4 = str2;
                i2 = 0;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 44:
                str = "repeat";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            case 45:
                str = "codePointAt";
                str4 = str;
                i2 = 1;
                initPrototypeMethod(STRING_TAG, i, str4, (String) null, i2);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0022. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        boolean equalsIgnoreCase;
        Normalizer.Form form;
        if (!idFunctionObject.hasTag(STRING_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        Scriptable scriptable3 = scriptable2;
        Object[] objArr2 = objArr;
        while (true) {
            CharSequence charSequence = "";
            int i = 0;
            if (methodId == -1) {
                int length = objArr2.length;
                if (length < 1) {
                    return "";
                }
                StringBuilder sb = new StringBuilder(length);
                while (i != length) {
                    sb.append(ScriptRuntime.toUint16(objArr2[i]));
                    i++;
                }
                return sb.toString();
            }
            switch (methodId) {
                default:
                    switch (methodId) {
                        case -15:
                        case -14:
                        case -13:
                        case -12:
                        case -11:
                        case -10:
                        case -9:
                        case -8:
                        case -7:
                        case -6:
                        case -5:
                            break;
                        default:
                            int i2 = 3;
                            switch (methodId) {
                                case 1:
                                    if (objArr2.length != 0) {
                                        if (ScriptRuntime.isSymbol(objArr2[0]) && scriptable3 != null) {
                                            charSequence = objArr2[0].toString();
                                        } else {
                                            charSequence = ScriptRuntime.toCharSequence(objArr2[0]);
                                        }
                                    }
                                    if (scriptable3 == null) {
                                        return new NativeString(charSequence);
                                    }
                                    return charSequence instanceof String ? charSequence : charSequence.toString();
                                case 2:
                                case 4:
                                    CharSequence charSequence2 = realThis(scriptable3, idFunctionObject).string;
                                    return charSequence2 instanceof String ? charSequence2 : charSequence2.toString();
                                case 3:
                                    return "(new String(\"" + ScriptRuntime.escapeString(realThis(scriptable3, idFunctionObject).string.toString()) + "\"))";
                                case 5:
                                case 6:
                                    CharSequence charSequence3 = ScriptRuntime.toCharSequence(scriptable3);
                                    double integer = ScriptRuntime.toInteger(objArr2, 0);
                                    if (integer < 0.0d || integer >= charSequence3.length()) {
                                        return methodId == 5 ? "" : ScriptRuntime.NaNobj;
                                    }
                                    char charAt = charSequence3.charAt((int) integer);
                                    if (methodId == 5) {
                                        return String.valueOf(charAt);
                                    }
                                    return ScriptRuntime.wrapInt(charAt);
                                case 7:
                                    return ScriptRuntime.wrapInt(js_indexOf(7, ScriptRuntime.toString(scriptable3), objArr2));
                                case 8:
                                    break;
                                case 9:
                                    return ScriptRuntime.checkRegExpProxy(context).js_split(context, scriptable, ScriptRuntime.toString(scriptable3), objArr2);
                                case 10:
                                    return js_substring(context, ScriptRuntime.toCharSequence(scriptable3), objArr2);
                                case 11:
                                    return ScriptRuntime.toString(scriptable3).toLowerCase(ScriptRuntime.ROOT_LOCALE);
                                case 12:
                                    return ScriptRuntime.toString(scriptable3).toUpperCase(ScriptRuntime.ROOT_LOCALE);
                                case 13:
                                    return js_substr(ScriptRuntime.toCharSequence(scriptable3), objArr2);
                                case 14:
                                    return js_concat(ScriptRuntime.toString(scriptable3), objArr2);
                                case 15:
                                    return js_slice(ScriptRuntime.toCharSequence(scriptable3), objArr2);
                                case 16:
                                    return tagify(scriptable3, "b", null, null);
                                case 17:
                                    return tagify(scriptable3, "i", null, null);
                                case 18:
                                    return tagify(scriptable3, "tt", null, null);
                                case 19:
                                    return tagify(scriptable3, "strike", null, null);
                                case 20:
                                    return tagify(scriptable3, "small", null, null);
                                case 21:
                                    return tagify(scriptable3, "big", null, null);
                                case 22:
                                    return tagify(scriptable3, "blink", null, null);
                                case 23:
                                    return tagify(scriptable3, "sup", null, null);
                                case 24:
                                    return tagify(scriptable3, "sub", null, null);
                                case 25:
                                    return tagify(scriptable3, "font", "size", objArr2);
                                case 26:
                                    return tagify(scriptable3, "font", TypedValues.Custom.S_COLOR, objArr2);
                                case 27:
                                    return tagify(scriptable3, "a", LinkFormat.LINK, objArr2);
                                case 28:
                                    return tagify(scriptable3, "a", "name", objArr2);
                                case 29:
                                case 30:
                                    String scriptRuntime = ScriptRuntime.toString(scriptable3);
                                    String scriptRuntime2 = ScriptRuntime.toString(objArr2, 0);
                                    if (methodId == 29) {
                                        equalsIgnoreCase = scriptRuntime.equals(scriptRuntime2);
                                    } else {
                                        equalsIgnoreCase = scriptRuntime.equalsIgnoreCase(scriptRuntime2);
                                    }
                                    return ScriptRuntime.wrapBoolean(equalsIgnoreCase);
                                case 31:
                                case 32:
                                case 33:
                                    if (methodId == 31) {
                                        i2 = 1;
                                    } else if (methodId != 32) {
                                        i2 = 2;
                                    }
                                    return ScriptRuntime.checkRegExpProxy(context).action(context, scriptable, scriptable3, objArr2, i2);
                                case 34:
                                    Collator collator = Collator.getInstance(context.getLocale());
                                    collator.setStrength(3);
                                    collator.setDecomposition(1);
                                    return ScriptRuntime.wrapNumber(collator.compare(ScriptRuntime.toString(scriptable3), ScriptRuntime.toString(objArr2, 0)));
                                case 35:
                                    return ScriptRuntime.toString(scriptable3).toLowerCase(context.getLocale());
                                case 36:
                                    return ScriptRuntime.toString(scriptable3).toUpperCase(context.getLocale());
                                case 37:
                                    String scriptRuntime3 = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(context, scriptable3, idFunctionObject));
                                    char[] charArray = scriptRuntime3.toCharArray();
                                    while (i < charArray.length && ScriptRuntime.isJSWhitespaceOrLineTerminator(charArray[i])) {
                                        i++;
                                    }
                                    int length2 = charArray.length;
                                    while (length2 > i && ScriptRuntime.isJSWhitespaceOrLineTerminator(charArray[length2 - 1])) {
                                        length2--;
                                    }
                                    return scriptRuntime3.substring(i, length2);
                                case 38:
                                    String scriptRuntime4 = ScriptRuntime.toString(scriptable3);
                                    char[] charArray2 = scriptRuntime4.toCharArray();
                                    while (i < charArray2.length && ScriptRuntime.isJSWhitespaceOrLineTerminator(charArray2[i])) {
                                        i++;
                                    }
                                    return scriptRuntime4.substring(i, charArray2.length);
                                case 39:
                                    String scriptRuntime5 = ScriptRuntime.toString(scriptable3);
                                    char[] charArray3 = scriptRuntime5.toCharArray();
                                    int length3 = charArray3.length;
                                    while (length3 > 0 && ScriptRuntime.isJSWhitespaceOrLineTerminator(charArray3[length3 - 1])) {
                                        length3--;
                                    }
                                    return scriptRuntime5.substring(0, length3);
                                case 40:
                                case 41:
                                case 42:
                                    String scriptRuntime6 = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(context, scriptable3, idFunctionObject));
                                    if (objArr2.length > 0 && (objArr2[0] instanceof NativeRegExp)) {
                                        throw ScriptRuntime.typeError2("msg.first.arg.not.regexp", String.class.getSimpleName(), idFunctionObject.getFunctionName());
                                    }
                                    int js_indexOf = js_indexOf(methodId, scriptRuntime6, objArr2);
                                    if (methodId == 40) {
                                        return Boolean.valueOf(js_indexOf != -1);
                                    }
                                    if (methodId == 41) {
                                        return Boolean.valueOf(js_indexOf == 0);
                                    }
                                    if (methodId == 42) {
                                        return Boolean.valueOf(js_indexOf != -1);
                                    }
                                    break;
                                case 43:
                                    String scriptRuntime7 = ScriptRuntime.toString(objArr2, 0);
                                    if (Normalizer.Form.NFD.name().equals(scriptRuntime7)) {
                                        form = Normalizer.Form.NFD;
                                    } else if (Normalizer.Form.NFKC.name().equals(scriptRuntime7)) {
                                        form = Normalizer.Form.NFKC;
                                    } else if (Normalizer.Form.NFKD.name().equals(scriptRuntime7)) {
                                        form = Normalizer.Form.NFKD;
                                    } else if (Normalizer.Form.NFC.name().equals(scriptRuntime7) || objArr2.length == 0) {
                                        form = Normalizer.Form.NFC;
                                    } else {
                                        throw ScriptRuntime.rangeError("The normalization form should be one of NFC, NFD, NFKC, NFKD");
                                    }
                                    return Normalizer.normalize(ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(context, scriptable3, idFunctionObject)), form);
                                case 44:
                                    return js_repeat(context, scriptable3, idFunctionObject, objArr2);
                                case 45:
                                    String scriptRuntime8 = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(context, scriptable3, idFunctionObject));
                                    double integer2 = ScriptRuntime.toInteger(objArr2, 0);
                                    if (integer2 < 0.0d || integer2 >= scriptRuntime8.length()) {
                                        return Undefined.instance;
                                    }
                                    return Integer.valueOf(scriptRuntime8.codePointAt((int) integer2));
                                case 46:
                                    return new NativeStringIterator(scriptable, scriptable3);
                                default:
                                    throw new IllegalArgumentException("String.prototype has no method: " + idFunctionObject.getFunctionName());
                            }
                            return ScriptRuntime.wrapInt(js_lastIndexOf(ScriptRuntime.toString(scriptable3), objArr2));
                    }
                case ConstructorId_toLocaleLowerCase /* -35 */:
                case ConstructorId_localeCompare /* -34 */:
                case -33:
                case ConstructorId_search /* -32 */:
                case ConstructorId_match /* -31 */:
                case ConstructorId_equalsIgnoreCase /* -30 */:
                    if (objArr2.length > 0) {
                        scriptable3 = ScriptRuntime.toObject(context, scriptable, ScriptRuntime.toCharSequence(objArr2[0]));
                        int length4 = objArr2.length - 1;
                        Object[] objArr3 = new Object[length4];
                        while (i < length4) {
                            int i3 = i + 1;
                            objArr3[i] = objArr2[i3];
                            i = i3;
                        }
                        objArr2 = objArr3;
                    } else {
                        scriptable3 = ScriptRuntime.toObject(context, scriptable, ScriptRuntime.toCharSequence(scriptable3));
                    }
                    methodId = -methodId;
            }
        }
    }

    private static NativeString realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (!(scriptable instanceof NativeString)) {
            throw incompatibleCallError(idFunctionObject);
        }
        return (NativeString) scriptable;
    }

    private static String tagify(Object obj, String str, String str2, Object[] objArr) {
        String scriptRuntime = ScriptRuntime.toString(obj);
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.less);
        sb.append(str);
        if (str2 != null) {
            sb.append(' ');
            sb.append(str2);
            sb.append("=\"");
            sb.append(ScriptRuntime.toString(objArr, 0));
            sb.append('\"');
        }
        sb.append(Typography.greater);
        sb.append(scriptRuntime);
        sb.append("</");
        sb.append(str);
        sb.append(Typography.greater);
        return sb.toString();
    }

    public CharSequence toCharSequence() {
        return this.string;
    }

    public String toString() {
        CharSequence charSequence = this.string;
        return charSequence instanceof String ? (String) charSequence : charSequence.toString();
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int i, Scriptable scriptable) {
        if (i >= 0 && i < this.string.length()) {
            return String.valueOf(this.string.charAt(i));
        }
        return super.get(i, scriptable);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int i, Scriptable scriptable, Object obj) {
        if (i < 0 || i >= this.string.length()) {
            super.put(i, scriptable, obj);
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int i, Scriptable scriptable) {
        if (i < 0 || i >= this.string.length()) {
            return super.has(i, scriptable);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    public Object[] getIds(boolean z, boolean z2) {
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null && currentContext.getLanguageVersion() >= 200) {
            Object[] ids = super.getIds(z, z2);
            Object[] objArr = new Object[ids.length + this.string.length()];
            int i = 0;
            while (i < this.string.length()) {
                objArr[i] = Integer.valueOf(i);
                i++;
            }
            System.arraycopy(ids, 0, objArr, i, ids.length);
            return objArr;
        }
        return super.getIds(z, z2);
    }

    private static int js_indexOf(int i, String str, Object[] objArr) {
        int length;
        String scriptRuntime = ScriptRuntime.toString(objArr, 0);
        double integer = ScriptRuntime.toInteger(objArr, 1);
        if (integer > str.length() && i != 41 && i != 42) {
            return -1;
        }
        if (integer < 0.0d) {
            integer = 0.0d;
        } else {
            if (integer > str.length()) {
                length = str.length();
            } else if (i == 42 && (integer != integer || integer > str.length())) {
                length = str.length();
            }
            integer = length;
        }
        if (42 != i) {
            if (i == 41) {
                return str.startsWith(scriptRuntime, (int) integer) ? 0 : -1;
            }
            return str.indexOf(scriptRuntime, (int) integer);
        }
        if (objArr.length == 0 || objArr.length == 1 || (objArr.length == 2 && objArr[1] == Undefined.instance)) {
            integer = str.length();
        }
        return str.substring(0, (int) integer).endsWith(scriptRuntime) ? 0 : -1;
    }

    private static int js_lastIndexOf(String str, Object[] objArr) {
        String scriptRuntime = ScriptRuntime.toString(objArr, 0);
        double number = ScriptRuntime.toNumber(objArr, 1);
        if (number != number || number > str.length()) {
            number = str.length();
        } else if (number < 0.0d) {
            number = 0.0d;
        }
        return str.lastIndexOf(scriptRuntime, (int) number);
    }

    private static CharSequence js_substring(Context context, CharSequence charSequence, Object[] objArr) {
        int length = charSequence.length();
        double integer = ScriptRuntime.toInteger(objArr, 0);
        double d = 0.0d;
        if (integer < 0.0d) {
            integer = 0.0d;
        } else {
            double d2 = length;
            if (integer > d2) {
                integer = d2;
            }
        }
        if (objArr.length <= 1 || objArr[1] == Undefined.instance) {
            d = length;
        } else {
            double integer2 = ScriptRuntime.toInteger(objArr[1]);
            if (integer2 >= 0.0d) {
                d = length;
                if (integer2 <= d) {
                    d = integer2;
                }
            }
            if (d < integer) {
                if (context.getLanguageVersion() != 120) {
                    double d3 = integer;
                    integer = d;
                    d = d3;
                } else {
                    d = integer;
                }
            }
        }
        return charSequence.subSequence((int) integer, (int) d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLength() {
        return this.string.length();
    }

    private static CharSequence js_substr(CharSequence charSequence, Object[] objArr) {
        double d;
        if (objArr.length < 1) {
            return charSequence;
        }
        double integer = ScriptRuntime.toInteger(objArr[0]);
        int length = charSequence.length();
        if (integer < 0.0d) {
            integer += length;
            if (integer < 0.0d) {
                integer = 0.0d;
            }
        } else {
            double d2 = length;
            if (integer > d2) {
                integer = d2;
            }
        }
        if (objArr.length == 1) {
            d = length;
        } else {
            double integer2 = ScriptRuntime.toInteger(objArr[1]);
            double d3 = (integer2 >= 0.0d ? integer2 : 0.0d) + integer;
            d = length;
            if (d3 <= d) {
                d = d3;
            }
        }
        return charSequence.subSequence((int) integer, (int) d);
    }

    private static String js_concat(String str, Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return str;
        }
        if (length == 1) {
            return str.concat(ScriptRuntime.toString(objArr[0]));
        }
        int length2 = str.length();
        String[] strArr = new String[length];
        for (int i = 0; i != length; i++) {
            String scriptRuntime = ScriptRuntime.toString(objArr[i]);
            strArr[i] = scriptRuntime;
            length2 += scriptRuntime.length();
        }
        StringBuilder sb = new StringBuilder(length2);
        sb.append(str);
        for (int i2 = 0; i2 != length; i2++) {
            sb.append(strArr[i2]);
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003e, code lost:
    
        if (r6 < 0.0d) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
    
        r2 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004a, code lost:
    
        if (r2 >= r4) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004c, code lost:
    
        r2 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0044, code lost:
    
        if (r6 > r2) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static CharSequence js_slice(CharSequence charSequence, Object[] objArr) {
        double d = 0.0d;
        double integer = objArr.length < 1 ? 0.0d : ScriptRuntime.toInteger(objArr[0]);
        int length = charSequence.length();
        if (integer < 0.0d) {
            integer += length;
            if (integer < 0.0d) {
                integer = 0.0d;
            }
        } else {
            double d2 = length;
            if (integer > d2) {
                integer = d2;
            }
        }
        if (objArr.length < 2 || objArr[1] == Undefined.instance) {
            d = length;
        } else {
            double integer2 = ScriptRuntime.toInteger(objArr[1]);
            if (integer2 < 0.0d) {
                integer2 += length;
            } else {
                d = length;
            }
        }
        return charSequence.subSequence((int) integer, (int) d);
    }

    private static String js_repeat(Context context, Scriptable scriptable, IdFunctionObject idFunctionObject, Object[] objArr) {
        String scriptRuntime = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(context, scriptable, idFunctionObject));
        double integer = ScriptRuntime.toInteger(objArr, 0);
        if (integer < 0.0d || integer == Double.POSITIVE_INFINITY) {
            throw ScriptRuntime.rangeError("Invalid count value");
        }
        if (integer == 0.0d || scriptRuntime.length() == 0) {
            return "";
        }
        long length = scriptRuntime.length() * ((long) integer);
        if (integer > 2.147483647E9d || length > 2147483647L) {
            throw ScriptRuntime.rangeError("Invalid size or count value");
        }
        StringBuilder sb = new StringBuilder((int) length);
        sb.append(scriptRuntime);
        int i = 1;
        int i2 = (int) integer;
        while (i <= i2 / 2) {
            sb.append((CharSequence) sb);
            i *= 2;
        }
        if (i < i2) {
            sb.append(sb.substring(0, scriptRuntime.length() * (i2 - i)));
        }
        return sb.toString();
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        return SymbolKey.ITERATOR.equals(symbol) ? 46 : 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x001e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:7:0x01fb A[ADDED_TO_REGION] */
    @Override // org.mozilla.javascript.IdScriptableObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int findPrototypeId(String str) {
        String str2;
        int i = 8;
        switch (str.length()) {
            case 3:
                char charAt = str.charAt(2);
                if (charAt == 'b') {
                    if (str.charAt(0) == 's' && str.charAt(1) == 'u') {
                        return 24;
                    }
                } else if (charAt == 'g') {
                    if (str.charAt(0) == 'b' && str.charAt(1) == 'i') {
                        return 21;
                    }
                } else if (charAt == 'p' && str.charAt(0) == 's' && str.charAt(1) == 'u') {
                    return 23;
                }
                str2 = null;
                i = 0;
                if (str2 != null || str2 == str || str2.equals(str)) {
                    return i;
                }
                return 0;
            case 4:
                char charAt2 = str.charAt(0);
                if (charAt2 == 'b') {
                    i = 16;
                    str2 = "bold";
                } else if (charAt2 == 'l') {
                    i = 27;
                    str2 = "link";
                } else {
                    if (charAt2 == 't') {
                        i = 37;
                        str2 = "trim";
                    }
                    str2 = null;
                    i = 0;
                }
                if (str2 != null) {
                }
                return i;
            case 5:
                char charAt3 = str.charAt(4);
                if (charAt3 == 'd') {
                    i = 18;
                    str2 = "fixed";
                } else if (charAt3 == 'e') {
                    i = 15;
                    str2 = "slice";
                } else if (charAt3 == 'h') {
                    i = 31;
                    str2 = "match";
                } else if (charAt3 == 't') {
                    i = 9;
                    str2 = "split";
                } else if (charAt3 != 'k') {
                    if (charAt3 == 'l') {
                        i = 20;
                        str2 = "small";
                    }
                    str2 = null;
                    i = 0;
                } else {
                    i = 22;
                    str2 = "blink";
                }
                if (str2 != null) {
                }
                return i;
            case 6:
                char charAt4 = str.charAt(1);
                if (charAt4 == 'e') {
                    char charAt5 = str.charAt(0);
                    if (charAt5 == 'r') {
                        i = 44;
                        str2 = "repeat";
                    } else {
                        if (charAt5 == 's') {
                            i = 32;
                            str2 = "search";
                        }
                        str2 = null;
                        i = 0;
                    }
                } else if (charAt4 == 'h') {
                    i = 5;
                    str2 = "charAt";
                } else if (charAt4 == 'q') {
                    i = 29;
                    str2 = "equals";
                } else if (charAt4 == 'n') {
                    i = 28;
                    str2 = "anchor";
                } else if (charAt4 == 'o') {
                    i = 14;
                    str2 = "concat";
                } else if (charAt4 != 't') {
                    if (charAt4 == 'u') {
                        i = 13;
                        str2 = "substr";
                    }
                    str2 = null;
                    i = 0;
                } else {
                    i = 19;
                    str2 = "strike";
                }
                if (str2 != null) {
                }
                return i;
            case 7:
                char charAt6 = str.charAt(1);
                if (charAt6 == 'a') {
                    str2 = "valueOf";
                    i = 4;
                } else if (charAt6 == 'e') {
                    i = 33;
                    str2 = "replace";
                } else if (charAt6 != 'n') {
                    if (charAt6 == 't') {
                        i = 17;
                        str2 = "italics";
                    }
                    str2 = null;
                    i = 0;
                } else {
                    i = 7;
                    str2 = "indexOf";
                }
                if (str2 != null) {
                }
                return i;
            case 8:
                char charAt7 = str.charAt(6);
                if (charAt7 == 'c') {
                    i = 3;
                    str2 = "toSource";
                } else if (charAt7 == 'n') {
                    str2 = "toString";
                    i = 2;
                } else if (charAt7 == 't') {
                    i = 42;
                    str2 = "endsWith";
                } else if (charAt7 == 'z') {
                    i = 25;
                    str2 = "fontsize";
                } else if (charAt7 != 'e') {
                    if (charAt7 == 'f') {
                        i = 38;
                        str2 = "trimLeft";
                    }
                    str2 = null;
                    i = 0;
                } else {
                    i = 40;
                    str2 = "includes";
                }
                if (str2 != null) {
                }
                return i;
            case 9:
                char charAt8 = str.charAt(0);
                if (charAt8 == 'f') {
                    i = 26;
                    str2 = "fontcolor";
                } else if (charAt8 == 'n') {
                    i = 43;
                    str2 = "normalize";
                } else if (charAt8 != 's') {
                    if (charAt8 == 't') {
                        i = 39;
                        str2 = "trimRight";
                    }
                    str2 = null;
                    i = 0;
                } else {
                    i = 10;
                    str2 = "substring";
                }
                if (str2 != null) {
                }
                return i;
            case 10:
                char charAt9 = str.charAt(0);
                if (charAt9 == 'c') {
                    str2 = "charCodeAt";
                    i = 6;
                } else {
                    if (charAt9 == 's') {
                        i = 41;
                        str2 = "startsWith";
                    }
                    str2 = null;
                    i = 0;
                }
                if (str2 != null) {
                }
                return i;
            case 11:
                char charAt10 = str.charAt(2);
                if (charAt10 == 'L') {
                    i = 11;
                    str2 = "toLowerCase";
                } else if (charAt10 == 'U') {
                    i = 12;
                    str2 = "toUpperCase";
                } else if (charAt10 == 'd') {
                    i = 45;
                    str2 = "codePointAt";
                } else if (charAt10 != 'n') {
                    if (charAt10 == 's') {
                        str2 = "lastIndexOf";
                    }
                    str2 = null;
                    i = 0;
                } else {
                    str2 = "constructor";
                    i = 1;
                }
                if (str2 != null) {
                }
                return i;
            case 12:
            case 14:
            case 15:
            default:
                str2 = null;
                i = 0;
                if (str2 != null) {
                }
                return i;
            case 13:
                i = 34;
                str2 = "localeCompare";
                if (str2 != null) {
                }
                return i;
            case 16:
                i = 30;
                str2 = "equalsIgnoreCase";
                if (str2 != null) {
                }
                return i;
            case 17:
                char charAt11 = str.charAt(8);
                if (charAt11 == 'L') {
                    i = 35;
                    str2 = "toLocaleLowerCase";
                } else {
                    if (charAt11 == 'U') {
                        i = 36;
                        str2 = "toLocaleUpperCase";
                    }
                    str2 = null;
                    i = 0;
                }
                if (str2 != null) {
                }
                return i;
        }
    }
}
