package org.mozilla.javascript;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class NativeNumber extends IdScriptableObject {
    private static final int ConstructorId_isFinite = -1;
    private static final int ConstructorId_isInteger = -3;
    private static final int ConstructorId_isNaN = -2;
    private static final int ConstructorId_isSafeInteger = -4;
    private static final int ConstructorId_parseFloat = -5;
    private static final int ConstructorId_parseInt = -6;
    private static final int Id_constructor = 1;
    private static final int Id_toExponential = 7;
    private static final int Id_toFixed = 6;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toPrecision = 8;
    private static final int Id_toSource = 4;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 5;
    private static final int MAX_PRECISION = 100;
    private static final int MAX_PROTOTYPE_ID = 8;
    private static final double MAX_SAFE_INTEGER;
    private static final double MIN_SAFE_INTEGER;
    private static final Object NUMBER_TAG = "Number";
    static final long serialVersionUID = 3504516769741512101L;
    private double doubleValue;

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Number";
    }

    static {
        double pow = Math.pow(2.0d, 53.0d) - 1.0d;
        MAX_SAFE_INTEGER = pow;
        MIN_SAFE_INTEGER = -pow;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Scriptable scriptable, boolean z) {
        new NativeNumber(0.0d).exportAsJSClass(8, scriptable, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeNumber(double d) {
        this.doubleValue = d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        idFunctionObject.defineProperty("NaN", ScriptRuntime.NaNobj, 7);
        idFunctionObject.defineProperty("POSITIVE_INFINITY", ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY), 7);
        idFunctionObject.defineProperty("NEGATIVE_INFINITY", ScriptRuntime.wrapNumber(Double.NEGATIVE_INFINITY), 7);
        idFunctionObject.defineProperty("MAX_VALUE", ScriptRuntime.wrapNumber(Double.MAX_VALUE), 7);
        idFunctionObject.defineProperty("MIN_VALUE", ScriptRuntime.wrapNumber(Double.MIN_VALUE), 7);
        idFunctionObject.defineProperty("MAX_SAFE_INTEGER", ScriptRuntime.wrapNumber(MAX_SAFE_INTEGER), 7);
        idFunctionObject.defineProperty("MIN_SAFE_INTEGER", ScriptRuntime.wrapNumber(MIN_SAFE_INTEGER), 7);
        addIdFunctionProperty(idFunctionObject, NUMBER_TAG, -1, "isFinite", 1);
        addIdFunctionProperty(idFunctionObject, NUMBER_TAG, -2, "isNaN", 1);
        addIdFunctionProperty(idFunctionObject, NUMBER_TAG, -3, "isInteger", 1);
        addIdFunctionProperty(idFunctionObject, NUMBER_TAG, -4, "isSafeInteger", 1);
        addIdFunctionProperty(idFunctionObject, NUMBER_TAG, -5, "parseFloat", 1);
        addIdFunctionProperty(idFunctionObject, NUMBER_TAG, -6, "parseInt", 1);
        super.fillConstructorProperties(idFunctionObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0002. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        String str2;
        int i2 = 1;
        switch (i) {
            case 1:
                str = "constructor";
                initPrototypeMethod(NUMBER_TAG, i, str, i2);
                return;
            case 2:
                str = "toString";
                initPrototypeMethod(NUMBER_TAG, i, str, i2);
                return;
            case 3:
                str = "toLocaleString";
                initPrototypeMethod(NUMBER_TAG, i, str, i2);
                return;
            case 4:
                str2 = "toSource";
                String str3 = str2;
                i2 = 0;
                str = str3;
                initPrototypeMethod(NUMBER_TAG, i, str, i2);
                return;
            case 5:
                str2 = "valueOf";
                String str32 = str2;
                i2 = 0;
                str = str32;
                initPrototypeMethod(NUMBER_TAG, i, str, i2);
                return;
            case 6:
                str = "toFixed";
                initPrototypeMethod(NUMBER_TAG, i, str, i2);
                return;
            case 7:
                str = "toExponential";
                initPrototypeMethod(NUMBER_TAG, i, str, i2);
                return;
            case 8:
                str = "toPrecision";
                initPrototypeMethod(NUMBER_TAG, i, str, i2);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(NUMBER_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            double number = objArr.length >= 1 ? ScriptRuntime.toNumber(objArr[0]) : 0.0d;
            if (scriptable2 == null) {
                return new NativeNumber(number);
            }
            return ScriptRuntime.wrapNumber(number);
        }
        if (methodId < 1) {
            return execConstructorCall(methodId, objArr);
        }
        if (!(scriptable2 instanceof NativeNumber)) {
            throw incompatibleCallError(idFunctionObject);
        }
        double d = ((NativeNumber) scriptable2).doubleValue;
        int i = 10;
        switch (methodId) {
            case 2:
            case 3:
                if (objArr.length != 0 && objArr[0] != Undefined.instance) {
                    i = ScriptRuntime.toInt32(objArr[0]);
                }
                return ScriptRuntime.numberToString(d, i);
            case 4:
                return "(new Number(" + ScriptRuntime.toString(d) + "))";
            case 5:
                return ScriptRuntime.wrapNumber(d);
            case 6:
                return num_to(d, objArr, 2, 2, -20, 0);
            case 7:
                if (Double.isNaN(d)) {
                    return "NaN";
                }
                if (Double.isInfinite(d)) {
                    return d >= 0.0d ? "Infinity" : "-Infinity";
                }
                return num_to(d, objArr, 1, 3, 0, 1);
            case 8:
                if (objArr.length == 0 || objArr[0] == Undefined.instance) {
                    return ScriptRuntime.numberToString(d, 10);
                }
                if (Double.isNaN(d)) {
                    return "NaN";
                }
                if (Double.isInfinite(d)) {
                    return d >= 0.0d ? "Infinity" : "-Infinity";
                }
                return num_to(d, objArr, 0, 4, 1, 0);
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private Object execConstructorCall(int i, Object[] objArr) {
        switch (i) {
            case -6:
                return NativeGlobal.js_parseInt(objArr);
            case -5:
                return NativeGlobal.js_parseFloat(objArr);
            case -4:
                if (objArr.length == 0 || Undefined.instance == objArr[0] || !(objArr[0] instanceof Number)) {
                    return false;
                }
                return Boolean.valueOf(isSafeInteger((Number) objArr[0]));
            case -3:
                if (objArr.length != 0 && Undefined.instance != objArr[0] && (objArr[0] instanceof Number)) {
                    return Boolean.valueOf(isInteger((Number) objArr[0]));
                }
                return false;
            case -2:
                if (objArr.length != 0 && Undefined.instance != objArr[0] && (objArr[0] instanceof Number)) {
                    return isNaN((Number) objArr[0]);
                }
                return false;
            case -1:
                if (objArr.length != 0 && Undefined.instance != objArr[0] && (objArr[0] instanceof Number)) {
                    return isFinite(objArr[0]);
                }
                return false;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    public String toString() {
        return ScriptRuntime.numberToString(this.doubleValue, 10);
    }

    private static String num_to(double d, Object[] objArr, int i, int i2, int i3, int i4) {
        int i5 = 0;
        if (objArr.length != 0) {
            double integer = ScriptRuntime.toInteger(objArr[0]);
            if (integer < i3 || integer > 100.0d) {
                throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage1("msg.bad.precision", ScriptRuntime.toString(objArr[0])));
            }
            i5 = ScriptRuntime.toInt32(integer);
            i = i2;
        }
        StringBuilder sb = new StringBuilder();
        DToA.JS_dtostr(sb, i, i5 + i4, d);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object isFinite(Object obj) {
        Double valueOf = Double.valueOf(ScriptRuntime.toNumber(obj));
        return ScriptRuntime.wrapBoolean((valueOf.isInfinite() || valueOf.isNaN()) ? false : true);
    }

    private Object isNaN(Number number) {
        return Boolean.valueOf(ScriptRuntime.toBoolean(Boolean.valueOf(isDoubleNan(doubleVal(number)))));
    }

    private boolean isDoubleNan(Double d) {
        return d.isNaN();
    }

    private boolean isInteger(Number number) {
        return ScriptRuntime.toBoolean(Boolean.valueOf(isDoubleInteger(doubleVal(number))));
    }

    private boolean isDoubleInteger(Double d) {
        return (d.isInfinite() || d.isNaN() || Math.floor(d.doubleValue()) != d.doubleValue()) ? false : true;
    }

    private boolean isSafeInteger(Number number) {
        return ScriptRuntime.toBoolean(Boolean.valueOf(isDoubleSafeInteger(doubleVal(number))));
    }

    private boolean isDoubleSafeInteger(Double d) {
        return isDoubleInteger(d) && d.doubleValue() <= MAX_SAFE_INTEGER && d.doubleValue() >= MIN_SAFE_INTEGER;
    }

    private Double doubleVal(Number number) {
        if (number instanceof Double) {
            return (Double) number;
        }
        return Double.valueOf(number.doubleValue());
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        String str2;
        int length = str.length();
        int i = 3;
        if (length == 7) {
            char charAt = str.charAt(0);
            if (charAt == 't') {
                i = 6;
                str2 = "toFixed";
            } else {
                if (charAt == 'v') {
                    i = 5;
                    str2 = "valueOf";
                }
                str2 = null;
                i = 0;
            }
        } else if (length == 8) {
            char charAt2 = str.charAt(3);
            if (charAt2 == 'o') {
                i = 4;
                str2 = "toSource";
            } else {
                if (charAt2 == 't') {
                    i = 2;
                    str2 = "toString";
                }
                str2 = null;
                i = 0;
            }
        } else if (length == 11) {
            char charAt3 = str.charAt(0);
            if (charAt3 == 'c') {
                i = 1;
                str2 = "constructor";
            } else {
                if (charAt3 == 't') {
                    str2 = "toPrecision";
                    i = 8;
                }
                str2 = null;
                i = 0;
            }
        } else if (length != 13) {
            if (length == 14) {
                str2 = "toLocaleString";
            }
            str2 = null;
            i = 0;
        } else {
            str2 = "toExponential";
            i = 7;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
