package org.mozilla.javascript;

import androidx.exifinterface.media.ExifInterface;
import org.mozilla.javascript.typedarrays.Conversions;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class NativeMath extends IdScriptableObject {
    private static final int Id_E = 30;
    private static final int Id_LN10 = 32;
    private static final int Id_LN2 = 33;
    private static final int Id_LOG10E = 35;
    private static final int Id_LOG2E = 34;
    private static final int Id_PI = 31;
    private static final int Id_SQRT1_2 = 36;
    private static final int Id_SQRT2 = 37;
    private static final int Id_abs = 2;
    private static final int Id_acos = 3;
    private static final int Id_asin = 4;
    private static final int Id_atan = 5;
    private static final int Id_atan2 = 6;
    private static final int Id_cbrt = 20;
    private static final int Id_ceil = 7;
    private static final int Id_cos = 8;
    private static final int Id_cosh = 21;
    private static final int Id_exp = 9;
    private static final int Id_expm1 = 22;
    private static final int Id_floor = 10;
    private static final int Id_hypot = 23;
    private static final int Id_imul = 28;
    private static final int Id_log = 11;
    private static final int Id_log10 = 25;
    private static final int Id_log1p = 24;
    private static final int Id_max = 12;
    private static final int Id_min = 13;
    private static final int Id_pow = 14;
    private static final int Id_random = 15;
    private static final int Id_round = 16;
    private static final int Id_sin = 17;
    private static final int Id_sinh = 26;
    private static final int Id_sqrt = 18;
    private static final int Id_tan = 19;
    private static final int Id_tanh = 27;
    private static final int Id_toSource = 1;
    private static final int Id_trunc = 29;
    private static final int LAST_METHOD_ID = 29;
    private static final Object MATH_TAG = "Math";
    private static final int MAX_ID = 37;
    static final long serialVersionUID = -8838847185801131569L;

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Math";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Scriptable scriptable, boolean z) {
        NativeMath nativeMath = new NativeMath();
        nativeMath.activatePrototypeMap(37);
        nativeMath.setPrototype(getObjectPrototype(scriptable));
        nativeMath.setParentScope(scriptable);
        if (z) {
            nativeMath.sealObject();
        }
        ScriptableObject.defineProperty(scriptable, "Math", nativeMath, 2);
    }

    private NativeMath() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0007. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        double d;
        String str;
        String str2;
        String str3;
        if (i <= 29) {
            int i2 = 1;
            switch (i) {
                case 1:
                    str2 = "toSource";
                    i2 = 0;
                    str3 = str2;
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 2:
                    str3 = "abs";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 3:
                    str3 = "acos";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 4:
                    str3 = "asin";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 5:
                    str3 = "atan";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 6:
                    str3 = "atan2";
                    i2 = 2;
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 7:
                    str3 = "ceil";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 8:
                    str3 = "cos";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 9:
                    str3 = "exp";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 10:
                    str3 = "floor";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 11:
                    str3 = "log";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 12:
                    str3 = "max";
                    i2 = 2;
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 13:
                    str3 = "min";
                    i2 = 2;
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 14:
                    str3 = "pow";
                    i2 = 2;
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 15:
                    str2 = "random";
                    i2 = 0;
                    str3 = str2;
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 16:
                    str3 = "round";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 17:
                    str3 = "sin";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 18:
                    str3 = "sqrt";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 19:
                    str3 = "tan";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 20:
                    str3 = "cbrt";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 21:
                    str3 = "cosh";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 22:
                    str3 = "expm1";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 23:
                    str3 = "hypot";
                    i2 = 2;
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 24:
                    str3 = "log1p";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 25:
                    str3 = "log10";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 26:
                    str3 = "sinh";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 27:
                    str3 = "tanh";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 28:
                    str3 = "imul";
                    i2 = 2;
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                case 29:
                    str3 = "trunc";
                    initPrototypeMethod(MATH_TAG, i, str3, i2);
                    return;
                default:
                    throw new IllegalStateException(String.valueOf(i));
            }
        }
        switch (i) {
            case 30:
                d = 2.718281828459045d;
                str = ExifInterface.LONGITUDE_EAST;
                break;
            case 31:
                d = 3.141592653589793d;
                str = "PI";
                break;
            case 32:
                d = 2.302585092994046d;
                str = "LN10";
                break;
            case 33:
                d = 0.6931471805599453d;
                str = "LN2";
                break;
            case 34:
                d = 1.4426950408889634d;
                str = "LOG2E";
                break;
            case 35:
                d = 0.4342944819032518d;
                str = "LOG10E";
                break;
            case 36:
                d = 0.7071067811865476d;
                str = "SQRT1_2";
                break;
            case 37:
                d = 1.4142135623730951d;
                str = "SQRT2";
                break;
            default:
                throw new IllegalStateException(String.valueOf(i));
        }
        initPrototypeValue(i, str, ScriptRuntime.wrapNumber(d), 7);
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d1, code lost:
    
        if (r10 != 0.0d) goto L44;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x001b. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double number;
        double min;
        if (!idFunctionObject.hasTag(MATH_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        double d = Double.NaN;
        int i = 0;
        switch (methodId) {
            case 1:
                return "Math";
            case 2:
                number = ScriptRuntime.toNumber(objArr, 0);
                if (number != 0.0d) {
                    if (number < 0.0d) {
                        number = -number;
                    }
                    d = number;
                    return ScriptRuntime.wrapNumber(d);
                }
                d = 0.0d;
                return ScriptRuntime.wrapNumber(d);
            case 3:
            case 4:
                double number2 = ScriptRuntime.toNumber(objArr, 0);
                if (number2 == number2 && -1.0d <= number2 && number2 <= 1.0d) {
                    number = methodId == 3 ? Math.acos(number2) : Math.asin(number2);
                    d = number;
                }
                return ScriptRuntime.wrapNumber(d);
            case 5:
                d = Math.atan(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 6:
                d = Math.atan2(ScriptRuntime.toNumber(objArr, 0), ScriptRuntime.toNumber(objArr, 1));
                return ScriptRuntime.wrapNumber(d);
            case 7:
                d = Math.ceil(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 8:
                double number3 = ScriptRuntime.toNumber(objArr, 0);
                if (number3 != Double.POSITIVE_INFINITY && number3 != Double.NEGATIVE_INFINITY) {
                    number = Math.cos(number3);
                    d = number;
                }
                return ScriptRuntime.wrapNumber(d);
            case 9:
                number = ScriptRuntime.toNumber(objArr, 0);
                if (number != Double.POSITIVE_INFINITY) {
                    if (number != Double.NEGATIVE_INFINITY) {
                        number = Math.exp(number);
                    }
                    d = 0.0d;
                    return ScriptRuntime.wrapNumber(d);
                }
                d = number;
                return ScriptRuntime.wrapNumber(d);
            case 10:
                d = Math.floor(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 11:
                double number4 = ScriptRuntime.toNumber(objArr, 0);
                if (number4 >= 0.0d) {
                    number = Math.log(number4);
                    d = number;
                }
                return ScriptRuntime.wrapNumber(d);
            case 12:
            case 13:
                double d2 = methodId != 12 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
                while (true) {
                    if (i != objArr.length) {
                        d = ScriptRuntime.toNumber(objArr[i]);
                        if (d == d) {
                            if (methodId == 12) {
                                min = Math.max(d2, d);
                            } else {
                                min = Math.min(d2, d);
                            }
                            d2 = min;
                            i++;
                        }
                    } else {
                        d = d2;
                    }
                }
                return ScriptRuntime.wrapNumber(d);
            case 14:
                d = js_pow(ScriptRuntime.toNumber(objArr, 0), ScriptRuntime.toNumber(objArr, 1));
                return ScriptRuntime.wrapNumber(d);
            case 15:
                d = Math.random();
                return ScriptRuntime.wrapNumber(d);
            case 16:
                d = ScriptRuntime.toNumber(objArr, 0);
                if (d == d && d != Double.POSITIVE_INFINITY && d != Double.NEGATIVE_INFINITY) {
                    long round = Math.round(d);
                    if (round == 0) {
                        if (d >= 0.0d) {
                            break;
                        } else {
                            number = ScriptRuntime.negativeZero;
                        }
                    } else {
                        number = round;
                    }
                    d = number;
                }
                return ScriptRuntime.wrapNumber(d);
            case 17:
                double number5 = ScriptRuntime.toNumber(objArr, 0);
                if (number5 != Double.POSITIVE_INFINITY && number5 != Double.NEGATIVE_INFINITY) {
                    number = Math.sin(number5);
                    d = number;
                }
                return ScriptRuntime.wrapNumber(d);
            case 18:
                d = Math.sqrt(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 19:
                d = Math.tan(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 20:
                d = Math.cbrt(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 21:
                d = Math.cosh(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 22:
                d = Math.expm1(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 23:
                d = js_hypot(objArr);
                return ScriptRuntime.wrapNumber(d);
            case 24:
                d = Math.log1p(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 25:
                d = Math.log10(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 26:
                d = Math.sinh(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 27:
                d = Math.tanh(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            case 28:
                return js_imul(objArr);
            case 29:
                d = js_trunc(ScriptRuntime.toNumber(objArr, 0));
                return ScriptRuntime.wrapNumber(d);
            default:
                throw new IllegalStateException(String.valueOf(methodId));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0063, code lost:
    
        if (r23 < 1.0d) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x007c, code lost:
    
        if (r23 < 1.0d) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0086, code lost:
    
        if (r25 > 0.0d) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a1, code lost:
    
        if (r25 > 0.0d) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private double js_pow(double d, double d2) {
        double d3 = Double.POSITIVE_INFINITY;
        if (d2 != d2) {
            return d2;
        }
        if (d2 == 0.0d) {
            return 1.0d;
        }
        if (d != 0.0d) {
            double pow = Math.pow(d, d2);
            if (pow != pow) {
                if (d2 == Double.POSITIVE_INFINITY) {
                    if (d >= -1.0d && 1.0d >= d) {
                        if (-1.0d < d) {
                        }
                    }
                } else {
                    if (d2 == Double.NEGATIVE_INFINITY) {
                        if (d >= -1.0d && 1.0d >= d) {
                            if (-1.0d < d) {
                            }
                        }
                        return 0.0d;
                    }
                    if (d != Double.POSITIVE_INFINITY) {
                        if (d == Double.NEGATIVE_INFINITY) {
                            long j = (long) d2;
                            if (j == d2 && (j & 1) != 0) {
                                return d2 <= 0.0d ? -0.0d : Double.NEGATIVE_INFINITY;
                            }
                        }
                    }
                }
            }
            return pow;
        }
        if (1.0d / d <= 0.0d) {
            long j2 = (long) d2;
            if (j2 == d2 && (j2 & 1) != 0) {
                d3 = d2 <= 0.0d ? Double.NEGATIVE_INFINITY : -0.0d;
            } else if (d2 > 0.0d) {
                d3 = 0.0d;
            }
        } else if (d2 > 0.0d) {
            d3 = 0.0d;
        }
        return d3;
    }

    private double js_hypot(Object[] objArr) {
        double d = 0.0d;
        if (objArr == null) {
            return 0.0d;
        }
        for (Object obj : objArr) {
            double number = ScriptRuntime.toNumber(obj);
            if (number == ScriptRuntime.NaN) {
                return number;
            }
            if (number == Double.POSITIVE_INFINITY || number == Double.NEGATIVE_INFINITY) {
                return Double.POSITIVE_INFINITY;
            }
            d += number * number;
        }
        return Math.sqrt(d);
    }

    private double js_trunc(double d) {
        return d < 0.0d ? Math.ceil(d) : Math.floor(d);
    }

    private Object js_imul(Object[] objArr) {
        if (objArr == null || objArr.length < 2) {
            return ScriptRuntime.wrapNumber(ScriptRuntime.NaN);
        }
        long uint32 = (Conversions.toUint32(objArr[0]) * Conversions.toUint32(objArr[1])) % Conversions.THIRTYTWO_BIT;
        if (uint32 >= 2147483648L) {
            uint32 -= Conversions.THIRTYTWO_BIT;
        }
        return Double.valueOf(ScriptRuntime.toNumber(Long.valueOf(uint32)));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x001f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:5:0x01ef A[ADDED_TO_REGION] */
    @Override // org.mozilla.javascript.IdScriptableObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int findPrototypeId(String str) {
        String str2;
        int i = 4;
        switch (str.length()) {
            case 1:
                if (str.charAt(0) == 'E') {
                    return 30;
                }
                str2 = null;
                i = 0;
                if (str2 != null || str2 == str || str2.equals(str)) {
                    return i;
                }
                return 0;
            case 2:
                if (str.charAt(0) == 'P' && str.charAt(1) == 'I') {
                    return 31;
                }
                str2 = null;
                i = 0;
                if (str2 != null) {
                }
                return i;
            case 3:
                char charAt = str.charAt(0);
                if (charAt != 'L') {
                    if (charAt != 'a') {
                        if (charAt != 'c') {
                            if (charAt != 'e') {
                                if (charAt != 'p') {
                                    if (charAt != 'l') {
                                        if (charAt == 'm') {
                                            char charAt2 = str.charAt(2);
                                            if (charAt2 == 'n') {
                                                if (str.charAt(1) == 'i') {
                                                    return 13;
                                                }
                                            } else if (charAt2 == 'x' && str.charAt(1) == 'a') {
                                                return 12;
                                            }
                                        } else if (charAt == 's') {
                                            if (str.charAt(2) == 'n' && str.charAt(1) == 'i') {
                                                return 17;
                                            }
                                        } else if (charAt == 't' && str.charAt(2) == 'n' && str.charAt(1) == 'a') {
                                            return 19;
                                        }
                                    } else if (str.charAt(2) == 'g' && str.charAt(1) == 'o') {
                                        return 11;
                                    }
                                } else if (str.charAt(2) == 'w' && str.charAt(1) == 'o') {
                                    return 14;
                                }
                            } else if (str.charAt(2) == 'p' && str.charAt(1) == 'x') {
                                return 9;
                            }
                        } else if (str.charAt(2) == 's' && str.charAt(1) == 'o') {
                            return 8;
                        }
                    } else if (str.charAt(2) == 's' && str.charAt(1) == 'b') {
                        return 2;
                    }
                } else if (str.charAt(2) == '2' && str.charAt(1) == 'N') {
                    return 33;
                }
                str2 = null;
                i = 0;
                if (str2 != null) {
                }
                return i;
            case 4:
                char charAt3 = str.charAt(1);
                if (charAt3 == 'N') {
                    i = 32;
                    str2 = "LN10";
                } else if (charAt3 == 'e') {
                    i = 7;
                    str2 = "ceil";
                } else if (charAt3 == 'i') {
                    i = 26;
                    str2 = "sinh";
                } else if (charAt3 == 'm') {
                    i = 28;
                    str2 = "imul";
                } else if (charAt3 == 'o') {
                    i = 21;
                    str2 = "cosh";
                } else if (charAt3 == 'q') {
                    i = 18;
                    str2 = "sqrt";
                } else if (charAt3 == 's') {
                    str2 = "asin";
                } else if (charAt3 != 't') {
                    switch (charAt3) {
                        case 'a':
                            i = 27;
                            str2 = "tanh";
                            break;
                        case 'b':
                            i = 20;
                            str2 = "cbrt";
                            break;
                        case 'c':
                            i = 3;
                            str2 = "acos";
                            break;
                        default:
                            str2 = null;
                            i = 0;
                            break;
                    }
                } else {
                    i = 5;
                    str2 = "atan";
                }
                if (str2 != null) {
                }
                return i;
            case 5:
                char charAt4 = str.charAt(0);
                if (charAt4 == 'L') {
                    i = 34;
                    str2 = "LOG2E";
                } else if (charAt4 == 'S') {
                    i = 37;
                    str2 = "SQRT2";
                } else if (charAt4 == 'a') {
                    i = 6;
                    str2 = "atan2";
                } else if (charAt4 == 'h') {
                    i = 23;
                    str2 = "hypot";
                } else if (charAt4 == 'l') {
                    char charAt5 = str.charAt(4);
                    if (charAt5 == '0') {
                        i = 25;
                        str2 = "log10";
                    } else {
                        if (charAt5 == 'p') {
                            i = 24;
                            str2 = "log1p";
                        }
                        str2 = null;
                        i = 0;
                    }
                } else if (charAt4 == 'r') {
                    i = 16;
                    str2 = "round";
                } else if (charAt4 == 't') {
                    i = 29;
                    str2 = "trunc";
                } else if (charAt4 != 'e') {
                    if (charAt4 == 'f') {
                        i = 10;
                        str2 = "floor";
                    }
                    str2 = null;
                    i = 0;
                } else {
                    i = 22;
                    str2 = "expm1";
                }
                if (str2 != null) {
                }
                return i;
            case 6:
                char charAt6 = str.charAt(0);
                if (charAt6 == 'L') {
                    i = 35;
                    str2 = "LOG10E";
                } else {
                    if (charAt6 == 'r') {
                        i = 15;
                        str2 = "random";
                    }
                    str2 = null;
                    i = 0;
                }
                if (str2 != null) {
                }
                return i;
            case 7:
                i = 36;
                str2 = "SQRT1_2";
                if (str2 != null) {
                }
                return i;
            case 8:
                str2 = "toSource";
                i = 1;
                if (str2 != null) {
                }
                return i;
        }
    }
}
