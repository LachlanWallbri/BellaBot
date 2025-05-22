package org.mozilla.javascript;

import com.airbnb.lottie.utils.Utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.io.FilenameUtils;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class NativeDate extends IdScriptableObject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ConstructorId_UTC = -1;
    private static final int ConstructorId_now = -3;
    private static final int ConstructorId_parse = -2;
    private static final Object DATE_TAG = "Date";
    private static final double HalfTimeDomain = 8.64E15d;
    private static final double HoursPerDay = 24.0d;
    private static final int Id_constructor = 1;
    private static final int Id_getDate = 17;
    private static final int Id_getDay = 19;
    private static final int Id_getFullYear = 13;
    private static final int Id_getHours = 21;
    private static final int Id_getMilliseconds = 27;
    private static final int Id_getMinutes = 23;
    private static final int Id_getMonth = 15;
    private static final int Id_getSeconds = 25;
    private static final int Id_getTime = 11;
    private static final int Id_getTimezoneOffset = 29;
    private static final int Id_getUTCDate = 18;
    private static final int Id_getUTCDay = 20;
    private static final int Id_getUTCFullYear = 14;
    private static final int Id_getUTCHours = 22;
    private static final int Id_getUTCMilliseconds = 28;
    private static final int Id_getUTCMinutes = 24;
    private static final int Id_getUTCMonth = 16;
    private static final int Id_getUTCSeconds = 26;
    private static final int Id_getYear = 12;
    private static final int Id_setDate = 39;
    private static final int Id_setFullYear = 43;
    private static final int Id_setHours = 37;
    private static final int Id_setMilliseconds = 31;
    private static final int Id_setMinutes = 35;
    private static final int Id_setMonth = 41;
    private static final int Id_setSeconds = 33;
    private static final int Id_setTime = 30;
    private static final int Id_setUTCDate = 40;
    private static final int Id_setUTCFullYear = 44;
    private static final int Id_setUTCHours = 38;
    private static final int Id_setUTCMilliseconds = 32;
    private static final int Id_setUTCMinutes = 36;
    private static final int Id_setUTCMonth = 42;
    private static final int Id_setUTCSeconds = 34;
    private static final int Id_setYear = 45;
    private static final int Id_toDateString = 4;
    private static final int Id_toGMTString = 8;
    private static final int Id_toISOString = 46;
    private static final int Id_toJSON = 47;
    private static final int Id_toLocaleDateString = 7;
    private static final int Id_toLocaleString = 5;
    private static final int Id_toLocaleTimeString = 6;
    private static final int Id_toSource = 9;
    private static final int Id_toString = 2;
    private static final int Id_toTimeString = 3;
    private static final int Id_toUTCString = 8;
    private static final int Id_valueOf = 10;
    private static double LocalTZA = 0.0d;
    private static final int MAXARGS = 7;
    private static final int MAX_PROTOTYPE_ID = 47;
    private static final double MinutesPerDay = 1440.0d;
    private static final double MinutesPerHour = 60.0d;
    private static final double SecondsPerDay = 86400.0d;
    private static final double SecondsPerHour = 3600.0d;
    private static final double SecondsPerMinute = 60.0d;
    private static final String js_NaN_date_str = "Invalid Date";
    private static DateFormat localeDateFormatter = null;
    private static DateFormat localeDateTimeFormatter = null;
    private static DateFormat localeTimeFormatter = null;
    private static final double msPerDay = 8.64E7d;
    private static final double msPerHour = 3600000.0d;
    private static final double msPerMinute = 60000.0d;
    private static final double msPerSecond = 1000.0d;
    static final long serialVersionUID = -8307438915861678966L;
    private static TimeZone thisTimeZone;
    private static DateFormat timeZoneFormatter;
    private double date;

    private static double MakeDate(double d, double d2) {
        return (d * msPerDay) + d2;
    }

    private static double MakeTime(double d, double d2, double d3, double d4) {
        return (((((d * 60.0d) + d2) * 60.0d) + d3) * msPerSecond) + d4;
    }

    private static double TimeWithinDay(double d) {
        double d2 = d % msPerDay;
        return d2 < 0.0d ? d2 + msPerDay : d2;
    }

    private static int msFromTime(double d) {
        double d2 = d % msPerSecond;
        if (d2 < 0.0d) {
            d2 += msPerSecond;
        }
        return (int) d2;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Date";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Scriptable scriptable, boolean z) {
        NativeDate nativeDate = new NativeDate();
        nativeDate.date = ScriptRuntime.NaN;
        nativeDate.exportAsJSClass(47, scriptable, z);
    }

    private NativeDate() {
        if (thisTimeZone == null) {
            thisTimeZone = TimeZone.getDefault();
            LocalTZA = r0.getRawOffset();
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> cls) {
        if (cls == null) {
            cls = ScriptRuntime.StringClass;
        }
        return super.getDefaultValue(cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getJSTimeValue() {
        return this.date;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        addIdFunctionProperty(idFunctionObject, DATE_TAG, -3, "now", 0);
        addIdFunctionProperty(idFunctionObject, DATE_TAG, -2, "parse", 1);
        addIdFunctionProperty(idFunctionObject, DATE_TAG, -1, "UTC", 7);
        super.fillConstructorProperties(idFunctionObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0005. Please report as an issue. */
    @Override // org.mozilla.javascript.IdScriptableObject
    public void initPrototypeId(int i) {
        String str;
        String str2;
        int i2 = 4;
        int i3 = 0;
        switch (i) {
            case 1:
                i2 = 7;
                str = "constructor";
                i3 = i2;
                str2 = str;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 2:
                str2 = "toString";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 3:
                str2 = "toTimeString";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 4:
                str2 = "toDateString";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 5:
                str2 = "toLocaleString";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 6:
                str2 = "toLocaleTimeString";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 7:
                str2 = "toLocaleDateString";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 8:
                str2 = "toUTCString";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 9:
                str2 = "toSource";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 10:
                str2 = "valueOf";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 11:
                str2 = "getTime";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 12:
                str2 = "getYear";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 13:
                str2 = "getFullYear";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 14:
                str2 = "getUTCFullYear";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 15:
                str2 = "getMonth";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 16:
                str2 = "getUTCMonth";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 17:
                str2 = "getDate";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 18:
                str2 = "getUTCDate";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 19:
                str2 = "getDay";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 20:
                str2 = "getUTCDay";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 21:
                str2 = "getHours";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 22:
                str2 = "getUTCHours";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 23:
                str2 = "getMinutes";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 24:
                str2 = "getUTCMinutes";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 25:
                str2 = "getSeconds";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 26:
                str2 = "getUTCSeconds";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 27:
                str2 = "getMilliseconds";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 28:
                str2 = "getUTCMilliseconds";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 29:
                str2 = "getTimezoneOffset";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 30:
                str2 = "setTime";
                i3 = 1;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 31:
                str2 = "setMilliseconds";
                i3 = 1;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 32:
                str2 = "setUTCMilliseconds";
                i3 = 1;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 33:
                str2 = "setSeconds";
                i3 = 2;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 34:
                str2 = "setUTCSeconds";
                i3 = 2;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 35:
                str2 = "setMinutes";
                i3 = 3;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 36:
                str2 = "setUTCMinutes";
                i3 = 3;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 37:
                str = "setHours";
                i3 = i2;
                str2 = str;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 38:
                str = "setUTCHours";
                i3 = i2;
                str2 = str;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 39:
                str2 = "setDate";
                i3 = 1;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 40:
                str2 = "setUTCDate";
                i3 = 1;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 41:
                str2 = "setMonth";
                i3 = 2;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 42:
                str2 = "setUTCMonth";
                i3 = 2;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 43:
                str2 = "setFullYear";
                i3 = 3;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 44:
                str2 = "setUTCFullYear";
                i3 = 3;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 45:
                str2 = "setYear";
                i3 = 1;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 46:
                str2 = "toISOString";
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            case 47:
                str2 = "toJSON";
                i3 = 1;
                initPrototypeMethod(DATE_TAG, i, str2, i3);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double d;
        if (!idFunctionObject.hasTag(DATE_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == -3) {
            return ScriptRuntime.wrapNumber(now());
        }
        if (methodId == -2) {
            return ScriptRuntime.wrapNumber(date_parseString(ScriptRuntime.toString(objArr, 0)));
        }
        if (methodId == -1) {
            return ScriptRuntime.wrapNumber(jsStaticFunction_UTC(objArr));
        }
        if (methodId == 1) {
            if (scriptable2 != null) {
                return date_format(now(), 2);
            }
            return jsConstructor(objArr);
        }
        if (methodId == 47) {
            Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
            Object primitive = ScriptRuntime.toPrimitive(object, ScriptRuntime.NumberClass);
            if (primitive instanceof Number) {
                double doubleValue = ((Number) primitive).doubleValue();
                if (doubleValue != doubleValue || Double.isInfinite(doubleValue)) {
                    return null;
                }
            }
            Object property = ScriptableObject.getProperty(object, "toISOString");
            if (property == NOT_FOUND) {
                throw ScriptRuntime.typeError2("msg.function.not.found.in", "toISOString", ScriptRuntime.toString(object));
            }
            if (!(property instanceof Callable)) {
                throw ScriptRuntime.typeError3("msg.isnt.function.in", "toISOString", ScriptRuntime.toString(object), ScriptRuntime.toString(property));
            }
            Object call = ((Callable) property).call(context, scriptable, object, ScriptRuntime.emptyArgs);
            if (ScriptRuntime.isPrimitive(call)) {
                return call;
            }
            throw ScriptRuntime.typeError1("msg.toisostring.must.return.primitive", ScriptRuntime.toString(call));
        }
        if (!(scriptable2 instanceof NativeDate)) {
            throw incompatibleCallError(idFunctionObject);
        }
        NativeDate nativeDate = (NativeDate) scriptable2;
        double d2 = nativeDate.date;
        switch (methodId) {
            case 2:
            case 3:
            case 4:
                return d2 == d2 ? date_format(d2, methodId) : js_NaN_date_str;
            case 5:
            case 6:
            case 7:
                return d2 == d2 ? toLocale_helper(d2, methodId) : js_NaN_date_str;
            case 8:
                return d2 == d2 ? js_toUTCString(d2) : js_NaN_date_str;
            case 9:
                return "(new Date(" + ScriptRuntime.toString(d2) + "))";
            case 10:
            case 11:
                return ScriptRuntime.wrapNumber(d2);
            case 12:
            case 13:
            case 14:
                if (d2 == d2) {
                    if (methodId != 14) {
                        d2 = LocalTime(d2);
                    }
                    d2 = YearFromTime(d2);
                    if (methodId == 12 && (!context.hasFeature(1) || (1900.0d <= d2 && d2 < 2000.0d))) {
                        d2 -= 1900.0d;
                    }
                }
                return ScriptRuntime.wrapNumber(d2);
            case 15:
            case 16:
                if (d2 == d2) {
                    if (methodId == 15) {
                        d2 = LocalTime(d2);
                    }
                    d2 = MonthFromTime(d2);
                }
                return ScriptRuntime.wrapNumber(d2);
            case 17:
            case 18:
                if (d2 == d2) {
                    if (methodId == 17) {
                        d2 = LocalTime(d2);
                    }
                    d2 = DateFromTime(d2);
                }
                return ScriptRuntime.wrapNumber(d2);
            case 19:
            case 20:
                if (d2 == d2) {
                    if (methodId == 19) {
                        d2 = LocalTime(d2);
                    }
                    d2 = WeekDay(d2);
                }
                return ScriptRuntime.wrapNumber(d2);
            case 21:
            case 22:
                if (d2 == d2) {
                    if (methodId == 21) {
                        d2 = LocalTime(d2);
                    }
                    d2 = HourFromTime(d2);
                }
                return ScriptRuntime.wrapNumber(d2);
            case 23:
            case 24:
                if (d2 == d2) {
                    if (methodId == 23) {
                        d2 = LocalTime(d2);
                    }
                    d2 = MinFromTime(d2);
                }
                return ScriptRuntime.wrapNumber(d2);
            case 25:
            case 26:
                if (d2 == d2) {
                    if (methodId == 25) {
                        d2 = LocalTime(d2);
                    }
                    d2 = SecFromTime(d2);
                }
                return ScriptRuntime.wrapNumber(d2);
            case 27:
            case 28:
                if (d2 == d2) {
                    if (methodId == 27) {
                        d2 = LocalTime(d2);
                    }
                    d2 = msFromTime(d2);
                }
                return ScriptRuntime.wrapNumber(d2);
            case 29:
                if (d2 == d2) {
                    d2 = (d2 - LocalTime(d2)) / msPerMinute;
                }
                return ScriptRuntime.wrapNumber(d2);
            case 30:
                double TimeClip = TimeClip(ScriptRuntime.toNumber(objArr, 0));
                nativeDate.date = TimeClip;
                return ScriptRuntime.wrapNumber(TimeClip);
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
                double makeTime = makeTime(d2, objArr, methodId);
                nativeDate.date = makeTime;
                return ScriptRuntime.wrapNumber(makeTime);
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
                double makeDate = makeDate(d2, objArr, methodId);
                nativeDate.date = makeDate;
                return ScriptRuntime.wrapNumber(makeDate);
            case 45:
                double number = ScriptRuntime.toNumber(objArr, 0);
                if (number != number || Double.isInfinite(number)) {
                    d = ScriptRuntime.NaN;
                } else {
                    double LocalTime = d2 != d2 ? 0.0d : LocalTime(d2);
                    if (number >= 0.0d && number <= 99.0d) {
                        number += 1900.0d;
                    }
                    d = TimeClip(internalUTC(MakeDate(MakeDay(number, MonthFromTime(LocalTime), DateFromTime(LocalTime)), TimeWithinDay(LocalTime))));
                }
                nativeDate.date = d;
                return ScriptRuntime.wrapNumber(d);
            case 46:
                if (d2 == d2) {
                    return js_toISOString(d2);
                }
                throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage0("msg.invalid.date"));
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private static double Day(double d) {
        return Math.floor(d / msPerDay);
    }

    private static boolean IsLeapYear(int i) {
        return i % 4 == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    private static double DayFromYear(double d) {
        return ((((d - 1970.0d) * 365.0d) + Math.floor((d - 1969.0d) / 4.0d)) - Math.floor((d - 1901.0d) / 100.0d)) + Math.floor((d - 1601.0d) / 400.0d);
    }

    private static double TimeFromYear(double d) {
        return DayFromYear(d) * msPerDay;
    }

    private static int YearFromTime(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return 0;
        }
        double floor = Math.floor(d / 3.1556952E10d) + 1970.0d;
        double TimeFromYear = TimeFromYear(floor);
        if (TimeFromYear > d) {
            floor -= 1.0d;
        } else if (TimeFromYear + (DaysInYear(floor) * msPerDay) <= d) {
            floor += 1.0d;
        }
        return (int) floor;
    }

    private static double DayFromMonth(int i, int i2) {
        int i3;
        int i4;
        int i5 = i * 30;
        if (i >= 7) {
            i4 = i / 2;
        } else if (i >= 2) {
            i4 = (i - 1) / 2;
        } else {
            i3 = i5 + i;
            if (i >= 2 && IsLeapYear(i2)) {
                i3++;
            }
            return i3;
        }
        i3 = i5 + (i4 - 1);
        if (i >= 2) {
            i3++;
        }
        return i3;
    }

    private static double DaysInYear(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return ScriptRuntime.NaN;
        }
        return IsLeapYear((int) d) ? 366.0d : 365.0d;
    }

    private static int DaysInMonth(int i, int i2) {
        return i2 == 2 ? IsLeapYear(i) ? 29 : 28 : i2 >= 8 ? 31 - (i2 & 1) : (i2 & 1) + 30;
    }

    private static int MonthFromTime(double d) {
        int i;
        int YearFromTime = YearFromTime(d);
        int Day = ((int) (Day(d) - DayFromYear(YearFromTime))) - 59;
        if (Day < 0) {
            return Day < -28 ? 0 : 1;
        }
        if (IsLeapYear(YearFromTime)) {
            if (Day == 0) {
                return 1;
            }
            Day--;
        }
        int i2 = Day / 30;
        switch (i2) {
            case 0:
                return 2;
            case 1:
                i = 31;
                break;
            case 2:
                i = 61;
                break;
            case 3:
                i = 92;
                break;
            case 4:
                i = 122;
                break;
            case 5:
                i = 153;
                break;
            case 6:
                i = 184;
                break;
            case 7:
                i = 214;
                break;
            case 8:
                i = 245;
                break;
            case 9:
                i = 275;
                break;
            case 10:
                return 11;
            default:
                throw Kit.codeBug();
        }
        return Day >= i ? i2 + 2 : i2 + 1;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0037. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int DateFromTime(double d) {
        int i;
        int i2;
        int YearFromTime = YearFromTime(d);
        int Day = ((int) (Day(d) - DayFromYear(YearFromTime))) - 59;
        int i3 = 31;
        if (Day < 0) {
            if (Day < -28) {
                Day += 31;
            }
            return Day + 28 + 1;
        }
        if (IsLeapYear(YearFromTime)) {
            if (Day == 0) {
                return 29;
            }
            Day--;
        }
        int i4 = 30;
        switch (Math.round(Day / 30)) {
            case 0:
                return Day + 1;
            case 1:
                i4 = 31;
                i2 = Day - i3;
                if (i2 < 0) {
                    i2 += i4;
                }
                return i2 + 1;
            case 2:
                i3 = 61;
                i2 = Day - i3;
                if (i2 < 0) {
                }
                return i2 + 1;
            case 3:
                i = 92;
                i4 = 31;
                i3 = i;
                i2 = Day - i3;
                if (i2 < 0) {
                }
                return i2 + 1;
            case 4:
                i3 = 122;
                i2 = Day - i3;
                if (i2 < 0) {
                }
                return i2 + 1;
            case 5:
                i = 153;
                i4 = 31;
                i3 = i;
                i2 = Day - i3;
                if (i2 < 0) {
                }
                return i2 + 1;
            case 6:
                i = 184;
                i4 = 31;
                i3 = i;
                i2 = Day - i3;
                if (i2 < 0) {
                }
                return i2 + 1;
            case 7:
                i3 = 214;
                i2 = Day - i3;
                if (i2 < 0) {
                }
                return i2 + 1;
            case 8:
                i = 245;
                i4 = 31;
                i3 = i;
                i2 = Day - i3;
                if (i2 < 0) {
                }
                return i2 + 1;
            case 9:
                i3 = 275;
                i2 = Day - i3;
                if (i2 < 0) {
                }
                return i2 + 1;
            case 10:
                return (Day - 275) + 1;
            default:
                throw Kit.codeBug();
        }
    }

    private static int WeekDay(double d) {
        double Day = (Day(d) + 4.0d) % 7.0d;
        if (Day < 0.0d) {
            Day += 7.0d;
        }
        return (int) Day;
    }

    private static double now() {
        return System.currentTimeMillis();
    }

    private static double DaylightSavingTA(double d) {
        if (d < 0.0d) {
            d = MakeDate(MakeDay(EquivalentYear(YearFromTime(d)), MonthFromTime(d), DateFromTime(d)), TimeWithinDay(d));
        }
        if (thisTimeZone.inDaylightTime(new Date((long) d))) {
            return msPerHour;
        }
        return 0.0d;
    }

    private static int EquivalentYear(int i) {
        int DayFromYear = (((int) DayFromYear(i)) + 4) % 7;
        if (DayFromYear < 0) {
            DayFromYear += 7;
        }
        if (IsLeapYear(i)) {
            switch (DayFromYear) {
                case 0:
                    return 1984;
                case 1:
                    return 1996;
                case 2:
                    return 1980;
                case 3:
                    return 1992;
                case 4:
                    return 1976;
                case 5:
                    return 1988;
                case 6:
                    return 1972;
            }
        }
        switch (DayFromYear) {
            case 0:
                return 1978;
            case 1:
                return 1973;
            case 2:
                return 1985;
            case 3:
                return 1986;
            case 4:
                return 1981;
            case 5:
                return 1971;
            case 6:
                return 1977;
        }
        throw Kit.codeBug();
    }

    private static double LocalTime(double d) {
        return LocalTZA + d + DaylightSavingTA(d);
    }

    private static double internalUTC(double d) {
        double d2 = LocalTZA;
        return (d - d2) - DaylightSavingTA(d - d2);
    }

    private static int HourFromTime(double d) {
        double floor = Math.floor(d / msPerHour) % HoursPerDay;
        if (floor < 0.0d) {
            floor += HoursPerDay;
        }
        return (int) floor;
    }

    private static int MinFromTime(double d) {
        double floor = Math.floor(d / msPerMinute) % 60.0d;
        if (floor < 0.0d) {
            floor += 60.0d;
        }
        return (int) floor;
    }

    private static int SecFromTime(double d) {
        double floor = Math.floor(d / msPerSecond) % 60.0d;
        if (floor < 0.0d) {
            floor += 60.0d;
        }
        return (int) floor;
    }

    private static double MakeDay(double d, double d2, double d3) {
        double floor = d + Math.floor(d2 / 12.0d);
        double d4 = d2 % 12.0d;
        if (d4 < 0.0d) {
            d4 += 12.0d;
        }
        return ((Math.floor(TimeFromYear(floor) / msPerDay) + DayFromMonth((int) d4, (int) floor)) + d3) - 1.0d;
    }

    private static double TimeClip(double d) {
        if (d != d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY || Math.abs(d) > HalfTimeDomain) {
            return ScriptRuntime.NaN;
        }
        if (d > 0.0d) {
            return Math.floor(d + 0.0d);
        }
        return Math.ceil(d + 0.0d);
    }

    private static double date_msecFromDate(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        return MakeDate(MakeDay(d, d2, d3), MakeTime(d4, d5, d6, d7));
    }

    private static double date_msecFromArgs(Object[] objArr) {
        double[] dArr = new double[7];
        for (int i = 0; i < 7; i++) {
            if (i < objArr.length) {
                double number = ScriptRuntime.toNumber(objArr[i]);
                if (number != number || Double.isInfinite(number)) {
                    return ScriptRuntime.NaN;
                }
                dArr[i] = ScriptRuntime.toInteger(objArr[i]);
            } else if (i == 2) {
                dArr[i] = 1.0d;
            } else {
                dArr[i] = 0.0d;
            }
        }
        if (dArr[0] >= 0.0d && dArr[0] <= 99.0d) {
            dArr[0] = dArr[0] + 1900.0d;
        }
        return date_msecFromDate(dArr[0], dArr[1], dArr[2], dArr[3], dArr[4], dArr[5], dArr[6]);
    }

    private static double jsStaticFunction_UTC(Object[] objArr) {
        return TimeClip(date_msecFromArgs(objArr));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00b7, code lost:
    
        if (r8 != '-') goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00c9, code lost:
    
        r10 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00c6, code lost:
    
        if (r8 != '-') goto L68;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:65:0x0099. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0120 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0186  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static double parseISOString(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        double date_msecFromDate;
        char c;
        char c2;
        char c3;
        char c4;
        int i14;
        int[] iArr = {1970, 1, 1, 0, 0, 0, 0, -1, -1};
        int length = str.length();
        int i15 = -1;
        if (length != 0) {
            char charAt = str.charAt(0);
            if (charAt == '+' || charAt == '-') {
                i4 = charAt == '-' ? -1 : 1;
                i = 6;
                i2 = 0;
            } else if (charAt == 'T') {
                i = 4;
                i2 = 3;
                i4 = 1;
            }
            i3 = 1;
            int i16 = 1;
            while (i2 != i15) {
                int i17 = i3 + (i2 == 0 ? i : i2 == 6 ? 3 : 2);
                if (i17 <= length) {
                    int i18 = 0;
                    while (true) {
                        if (i3 < i17) {
                            char charAt2 = str.charAt(i3);
                            if (charAt2 >= '0' && charAt2 <= '9') {
                                i18 = (i18 * 10) + (charAt2 - '0');
                                i3++;
                            }
                        } else {
                            iArr[i2] = i18;
                            if (i3 != length) {
                                int i19 = i3 + 1;
                                char charAt3 = str.charAt(i3);
                                if (charAt3 == 'Z') {
                                    iArr[7] = 0;
                                    iArr[8] = 0;
                                    if (i2 == 4 || i2 == 5 || i2 == 6) {
                                        i3 = i19;
                                    } else {
                                        i3 = i19;
                                    }
                                } else {
                                    switch (i2) {
                                        case 0:
                                        case 1:
                                            c = Soundex.SILENT_MARKER;
                                            c2 = 'T';
                                            c3 = '+';
                                            if (charAt3 != '-') {
                                                if (charAt3 != 'T') {
                                                    i2 = -1;
                                                    break;
                                                } else {
                                                    i2 = 3;
                                                    break;
                                                }
                                            } else {
                                                i2++;
                                                break;
                                            }
                                        case 2:
                                            c2 = 'T';
                                            c3 = '+';
                                            i2 = charAt3 == 'T' ? 3 : -1;
                                            c = Soundex.SILENT_MARKER;
                                            break;
                                        case 3:
                                            c3 = '+';
                                            if (charAt3 == ':') {
                                                i2 = 4;
                                                c = Soundex.SILENT_MARKER;
                                                c2 = 'T';
                                                break;
                                            }
                                            i2 = -1;
                                            c = Soundex.SILENT_MARKER;
                                            c2 = 'T';
                                        case 4:
                                            c3 = '+';
                                            if (charAt3 == ':') {
                                                i2 = 5;
                                            } else {
                                                if (charAt3 == '+' || charAt3 == '-') {
                                                    i2 = 7;
                                                }
                                                i2 = -1;
                                            }
                                            c = Soundex.SILENT_MARKER;
                                            c2 = 'T';
                                            break;
                                        case 5:
                                            c4 = Soundex.SILENT_MARKER;
                                            c3 = '+';
                                            if (charAt3 == '.') {
                                                i14 = 6;
                                                c2 = 'T';
                                                char c5 = c4;
                                                i2 = i14;
                                                c = c5;
                                                break;
                                            } else {
                                                if (charAt3 != '+') {
                                                }
                                                i14 = 7;
                                                c2 = 'T';
                                                char c52 = c4;
                                                i2 = i14;
                                                c = c52;
                                            }
                                            break;
                                        case 6:
                                            c3 = '+';
                                            c4 = Soundex.SILENT_MARKER;
                                            if (charAt3 != '+') {
                                            }
                                            i14 = 7;
                                            c2 = 'T';
                                            char c522 = c4;
                                            i2 = i14;
                                            c = c522;
                                            break;
                                        case 7:
                                            if (charAt3 != ':') {
                                                i19--;
                                            }
                                            i2 = 8;
                                            c = Soundex.SILENT_MARKER;
                                            c2 = 'T';
                                            c3 = '+';
                                            break;
                                        case 8:
                                            c = Soundex.SILENT_MARKER;
                                            c2 = 'T';
                                            i2 = -1;
                                            c3 = '+';
                                            break;
                                        default:
                                            c = Soundex.SILENT_MARKER;
                                            c2 = 'T';
                                            c3 = '+';
                                            break;
                                    }
                                    if (i2 == 7) {
                                        i16 = charAt3 == c ? -1 : 1;
                                    }
                                    i15 = -1;
                                    i3 = i19;
                                }
                            } else if (i2 == 3 || i2 == 7) {
                                i2 = -1;
                            }
                            i5 = -1;
                        }
                    }
                }
                i5 = -1;
                i2 = -1;
                if (i2 != i5 && i3 == length) {
                    i6 = iArr[0];
                    i7 = iArr[1];
                    i8 = iArr[2];
                    i9 = iArr[3];
                    i10 = iArr[4];
                    i11 = iArr[5];
                    i12 = iArr[6];
                    i13 = iArr[7];
                    int i20 = iArr[8];
                    if (i6 <= 275943 && i7 >= 1 && i7 <= 12 && i8 >= 1 && i8 <= DaysInMonth(i6, i7) && i9 <= 24 && ((i9 != 24 || (i10 <= 0 && i11 <= 0 && i12 <= 0)) && i10 <= 59 && i11 <= 59 && i13 <= 23 && i20 <= 59)) {
                        date_msecFromDate = date_msecFromDate(i6 * i4, i7 - 1, i8, i9, i10, i11, i12);
                        if (i13 != -1) {
                            date_msecFromDate -= (((i13 * 60) + i20) * msPerMinute) * i16;
                        }
                        if (date_msecFromDate >= -8.64E15d && date_msecFromDate <= HalfTimeDomain) {
                            return date_msecFromDate;
                        }
                    }
                }
                return ScriptRuntime.NaN;
            }
            i5 = i15;
            if (i2 != i5) {
                i6 = iArr[0];
                i7 = iArr[1];
                i8 = iArr[2];
                i9 = iArr[3];
                i10 = iArr[4];
                i11 = iArr[5];
                i12 = iArr[6];
                i13 = iArr[7];
                int i202 = iArr[8];
                if (i6 <= 275943) {
                    date_msecFromDate = date_msecFromDate(i6 * i4, i7 - 1, i8, i9, i10, i11, i12);
                    if (i13 != -1) {
                    }
                    if (date_msecFromDate >= -8.64E15d) {
                        return date_msecFromDate;
                    }
                }
            }
            return ScriptRuntime.NaN;
        }
        i = 4;
        i2 = 0;
        i3 = 0;
        i4 = 1;
        int i162 = 1;
        while (i2 != i15) {
        }
        i5 = i15;
        if (i2 != i5) {
        }
        return ScriptRuntime.NaN;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:94:0x01b0. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:105:0x014e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0151  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static double date_parseString(String str) {
        int i;
        int i2;
        int i3;
        double parseISOString = parseISOString(str);
        if (parseISOString == parseISOString) {
            return parseISOString;
        }
        int length = str.length();
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        int i9 = -1;
        int i10 = 0;
        char c = 0;
        double d = -1.0d;
        boolean z = false;
        while (i10 < length) {
            char charAt = str.charAt(i10);
            i10++;
            if (charAt <= ' ' || charAt == ',' || charAt == '-') {
                char c2 = c;
                i = i4;
                if (i10 < length) {
                    char charAt2 = str.charAt(i10);
                    if (charAt == '-' && '0' <= charAt2 && charAt2 <= '9') {
                        c = charAt;
                        i4 = i;
                    }
                }
                i4 = i;
                c = c2;
            } else {
                int i11 = 1;
                if (charAt == '(') {
                    while (i10 < length) {
                        char charAt3 = str.charAt(i10);
                        i10++;
                        if (charAt3 != '(') {
                            if (charAt3 == ')' && i11 - 1 <= 0) {
                                break;
                            }
                        } else {
                            i11++;
                        }
                    }
                } else if ('0' <= charAt && charAt <= '9') {
                    int i12 = charAt - '0';
                    while (i10 < length) {
                        charAt = str.charAt(i10);
                        if ('0' <= charAt && charAt <= '9') {
                            i12 = ((i12 * 10) + charAt) - 48;
                            i10++;
                        }
                        if (c != '+' || c == '-') {
                            i3 = i12 >= 24 ? i12 * 60 : ((i12 / 100) * 60) + (i12 % 100);
                            if (c == '+') {
                                i3 = -i3;
                            }
                            if (d == 0.0d && d != -1.0d) {
                                return ScriptRuntime.NaN;
                            }
                            d = i3;
                            z = true;
                        } else if (i12 >= 70 || (c == '/' && i6 >= 0 && i7 >= 0 && i5 < 0)) {
                            if (i5 >= 0) {
                                return ScriptRuntime.NaN;
                            }
                            if (charAt > ' ' && charAt != ',' && charAt != '/' && i10 < length) {
                                return ScriptRuntime.NaN;
                            }
                            if (i12 < 100) {
                                i12 += 1900;
                            }
                            i5 = i12;
                        } else if (charAt == ':') {
                            if (i4 < 0) {
                                i4 = i12;
                            } else {
                                if (i9 >= 0) {
                                    return ScriptRuntime.NaN;
                                }
                                i9 = i12;
                            }
                        } else if (charAt == '/') {
                            if (i6 < 0) {
                                i6 = i12 - 1;
                            } else {
                                if (i7 >= 0) {
                                    return ScriptRuntime.NaN;
                                }
                                i7 = i12;
                            }
                        } else {
                            if (i10 < length && charAt != ',' && charAt > ' ' && charAt != '-') {
                                return ScriptRuntime.NaN;
                            }
                            if (!z || i12 >= 60) {
                                if (i4 < 0 || i9 >= 0) {
                                    if (i9 < 0 || i8 >= 0) {
                                        if (i7 >= 0) {
                                            return ScriptRuntime.NaN;
                                        }
                                        i7 = i12;
                                    } else {
                                        i8 = i12;
                                    }
                                }
                                i9 = i12;
                            } else {
                                d = d < 0.0d ? d - i12 : d + i12;
                            }
                        }
                        c = 0;
                    }
                    if (c != '+') {
                    }
                    if (i12 >= 24) {
                    }
                    if (c == '+') {
                    }
                    if (d == 0.0d) {
                    }
                    d = i3;
                    z = true;
                    c = 0;
                } else if (charAt == '/' || charAt == ':' || charAt == '+' || charAt == '-') {
                    i = i4;
                    c = charAt;
                    i4 = i;
                } else {
                    int i13 = i10 - 1;
                    int i14 = i10;
                    while (i14 < length) {
                        char charAt4 = str.charAt(i14);
                        if (('A' <= charAt4 && charAt4 <= 'Z') || ('a' <= charAt4 && charAt4 <= 'z')) {
                            i14++;
                        }
                        i2 = i14 - i13;
                        if (i2 >= 2) {
                            return ScriptRuntime.NaN;
                        }
                        String str2 = "am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;";
                        int i15 = 0;
                        int i16 = 0;
                        while (true) {
                            int indexOf = str2.indexOf(59, i16);
                            if (indexOf < 0) {
                                return ScriptRuntime.NaN;
                            }
                            int i17 = i14;
                            int i18 = i15;
                            String str3 = str2;
                            char c3 = c;
                            int i19 = i13;
                            int i20 = i13;
                            int i21 = i4;
                            if (str2.regionMatches(true, i16, str, i19, i2)) {
                                double d2 = 420.0d;
                                if (i18 >= 2) {
                                    int i22 = i18 - 2;
                                    if (i22 >= 7) {
                                        int i23 = i22 - 7;
                                        if (i23 >= 12) {
                                            switch (i23 - 12) {
                                                case 0:
                                                case 1:
                                                case 2:
                                                    i4 = i21;
                                                    d = 0.0d;
                                                    break;
                                                case 3:
                                                case 6:
                                                    d = 300.0d;
                                                    break;
                                                case 4:
                                                    d2 = 240.0d;
                                                    d = d2;
                                                    break;
                                                case 5:
                                                case 8:
                                                    i4 = i21;
                                                    d = 360.0d;
                                                    break;
                                                case 7:
                                                case 10:
                                                    d = d2;
                                                    break;
                                                case 9:
                                                    d2 = 480.0d;
                                                    d = d2;
                                                    break;
                                                default:
                                                    Kit.codeBug();
                                                    break;
                                            }
                                            c = c3;
                                            i10 = i17;
                                        } else {
                                            if (i6 >= 0) {
                                                return ScriptRuntime.NaN;
                                            }
                                            i6 = i23;
                                        }
                                    }
                                    i4 = i21;
                                    c = c3;
                                    i10 = i17;
                                } else {
                                    if (i21 > 12 || i21 < 0) {
                                        return ScriptRuntime.NaN;
                                    }
                                    if (i18 == 0) {
                                        if (i21 == 12) {
                                            i4 = 0;
                                            c = c3;
                                            i10 = i17;
                                        }
                                        i4 = i21;
                                        c = c3;
                                        i10 = i17;
                                    } else {
                                        if (i21 != 12) {
                                            i4 = i21 + 12;
                                            c = c3;
                                            i10 = i17;
                                        }
                                        i4 = i21;
                                        c = c3;
                                        i10 = i17;
                                    }
                                }
                            } else {
                                i16 = indexOf + 1;
                                i15 = i18 + 1;
                                i4 = i21;
                                c = c3;
                                i14 = i17;
                                i13 = i20;
                                str2 = str3;
                            }
                        }
                    }
                    i2 = i14 - i13;
                    if (i2 >= 2) {
                    }
                }
            }
        }
        int i24 = i4;
        if (i5 < 0 || i6 < 0 || i7 < 0) {
            return ScriptRuntime.NaN;
        }
        if (i8 < 0) {
            i8 = 0;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        double date_msecFromDate = date_msecFromDate(i5, i6, i7, i24 < 0 ? 0 : i24, i9, i8, 0.0d);
        return d == -1.0d ? internalUTC(date_msecFromDate) : date_msecFromDate + (d * msPerMinute);
    }

    private static String date_format(double d, int i) {
        StringBuilder sb = new StringBuilder(60);
        double LocalTime = LocalTime(d);
        if (i != 3) {
            appendWeekDayName(sb, WeekDay(LocalTime));
            sb.append(' ');
            appendMonthName(sb, MonthFromTime(LocalTime));
            sb.append(' ');
            append0PaddedUint(sb, DateFromTime(LocalTime), 2);
            sb.append(' ');
            int YearFromTime = YearFromTime(LocalTime);
            if (YearFromTime < 0) {
                sb.append(Soundex.SILENT_MARKER);
                YearFromTime = -YearFromTime;
            }
            append0PaddedUint(sb, YearFromTime, 4);
            if (i != 4) {
                sb.append(' ');
            }
        }
        if (i != 4) {
            append0PaddedUint(sb, HourFromTime(LocalTime), 2);
            sb.append(':');
            append0PaddedUint(sb, MinFromTime(LocalTime), 2);
            sb.append(':');
            append0PaddedUint(sb, SecFromTime(LocalTime), 2);
            int floor = (int) Math.floor((LocalTZA + DaylightSavingTA(d)) / msPerMinute);
            int i2 = ((floor / 60) * 100) + (floor % 60);
            if (i2 > 0) {
                sb.append(" GMT+");
            } else {
                sb.append(" GMT-");
                i2 = -i2;
            }
            append0PaddedUint(sb, i2, 4);
            if (timeZoneFormatter == null) {
                timeZoneFormatter = new SimpleDateFormat("zzz");
            }
            if (d < 0.0d) {
                d = MakeDate(MakeDay(EquivalentYear(YearFromTime(LocalTime)), MonthFromTime(d), DateFromTime(d)), TimeWithinDay(d));
            }
            sb.append(" (");
            Date date = new Date((long) d);
            synchronized (timeZoneFormatter) {
                sb.append(timeZoneFormatter.format(date));
            }
            sb.append(')');
        }
        return sb.toString();
    }

    private static Object jsConstructor(Object[] objArr) {
        double number;
        NativeDate nativeDate = new NativeDate();
        if (objArr.length == 0) {
            nativeDate.date = now();
            return nativeDate;
        }
        if (objArr.length == 1) {
            Object obj = objArr[0];
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(null);
            }
            if (obj instanceof CharSequence) {
                number = date_parseString(obj.toString());
            } else {
                number = ScriptRuntime.toNumber(obj);
            }
            nativeDate.date = TimeClip(number);
            return nativeDate;
        }
        double date_msecFromArgs = date_msecFromArgs(objArr);
        if (!Double.isNaN(date_msecFromArgs) && !Double.isInfinite(date_msecFromArgs)) {
            date_msecFromArgs = TimeClip(internalUTC(date_msecFromArgs));
        }
        nativeDate.date = date_msecFromArgs;
        return nativeDate;
    }

    private static String toLocale_helper(double d, int i) {
        DateFormat dateFormat;
        String format;
        if (i == 5) {
            if (localeDateTimeFormatter == null) {
                localeDateTimeFormatter = DateFormat.getDateTimeInstance(1, 1);
            }
            dateFormat = localeDateTimeFormatter;
        } else if (i == 6) {
            if (localeTimeFormatter == null) {
                localeTimeFormatter = DateFormat.getTimeInstance(1);
            }
            dateFormat = localeTimeFormatter;
        } else if (i == 7) {
            if (localeDateFormatter == null) {
                localeDateFormatter = DateFormat.getDateInstance(1);
            }
            dateFormat = localeDateFormatter;
        } else {
            throw new AssertionError();
        }
        synchronized (dateFormat) {
            format = dateFormat.format(new Date((long) d));
        }
        return format;
    }

    private static String js_toUTCString(double d) {
        StringBuilder sb = new StringBuilder(60);
        appendWeekDayName(sb, WeekDay(d));
        sb.append(", ");
        append0PaddedUint(sb, DateFromTime(d), 2);
        sb.append(' ');
        appendMonthName(sb, MonthFromTime(d));
        sb.append(' ');
        int YearFromTime = YearFromTime(d);
        if (YearFromTime < 0) {
            sb.append(Soundex.SILENT_MARKER);
            YearFromTime = -YearFromTime;
        }
        append0PaddedUint(sb, YearFromTime, 4);
        sb.append(' ');
        append0PaddedUint(sb, HourFromTime(d), 2);
        sb.append(':');
        append0PaddedUint(sb, MinFromTime(d), 2);
        sb.append(':');
        append0PaddedUint(sb, SecFromTime(d), 2);
        sb.append(" GMT");
        return sb.toString();
    }

    private static String js_toISOString(double d) {
        StringBuilder sb = new StringBuilder(27);
        int YearFromTime = YearFromTime(d);
        if (YearFromTime < 0) {
            sb.append(Soundex.SILENT_MARKER);
            append0PaddedUint(sb, -YearFromTime, 6);
        } else if (YearFromTime > 9999) {
            append0PaddedUint(sb, YearFromTime, 6);
        } else {
            append0PaddedUint(sb, YearFromTime, 4);
        }
        sb.append(Soundex.SILENT_MARKER);
        append0PaddedUint(sb, MonthFromTime(d) + 1, 2);
        sb.append(Soundex.SILENT_MARKER);
        append0PaddedUint(sb, DateFromTime(d), 2);
        sb.append('T');
        append0PaddedUint(sb, HourFromTime(d), 2);
        sb.append(':');
        append0PaddedUint(sb, MinFromTime(d), 2);
        sb.append(':');
        append0PaddedUint(sb, SecFromTime(d), 2);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        append0PaddedUint(sb, msFromTime(d), 3);
        sb.append(Matrix.MATRIX_TYPE_ZERO);
        return sb.toString();
    }

    private static void append0PaddedUint(StringBuilder sb, int i, int i2) {
        if (i < 0) {
            Kit.codeBug();
        }
        int i3 = i2 - 1;
        int i4 = Utils.SECOND_IN_NANOS;
        if (i < 10) {
            i4 = 1;
        } else if (i < 1000000000) {
            i4 = 1;
            while (true) {
                int i5 = i4 * 10;
                if (i < i5) {
                    break;
                }
                i3--;
                i4 = i5;
            }
        } else {
            i3 -= 9;
        }
        while (i3 > 0) {
            sb.append('0');
            i3--;
        }
        while (i4 != 1) {
            sb.append((char) ((i / i4) + 48));
            i %= i4;
            i4 /= 10;
        }
        sb.append((char) (i + 48));
    }

    private static void appendMonthName(StringBuilder sb, int i) {
        int i2 = i * 3;
        for (int i3 = 0; i3 != 3; i3++) {
            sb.append("JanFebMarAprMayJunJulAugSepOctNovDec".charAt(i2 + i3));
        }
    }

    private static void appendWeekDayName(StringBuilder sb, int i) {
        int i2 = i * 3;
        for (int i3 = 0; i3 != 3; i3++) {
            sb.append("SunMonTueWedThuFriSat".charAt(i2 + i3));
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static double makeTime(double d, Object[] objArr, int i) {
        boolean z;
        int i2;
        int length;
        int i3;
        boolean z2;
        double HourFromTime;
        double MinFromTime;
        double SecFromTime;
        double msFromTime;
        if (objArr.length == 0) {
            return ScriptRuntime.NaN;
        }
        int i4 = 0;
        switch (i) {
            case 31:
                z = true;
                i2 = 1;
                length = objArr.length >= i2 ? objArr.length : i2;
                double[] dArr = new double[4];
                z2 = false;
                for (i3 = 0; i3 < length; i3++) {
                    double number = ScriptRuntime.toNumber(objArr[i3]);
                    if (number != number || Double.isInfinite(number)) {
                        z2 = true;
                    } else {
                        dArr[i3] = ScriptRuntime.toInteger(number);
                    }
                }
                if (!z2 || d != d) {
                    return ScriptRuntime.NaN;
                }
                double LocalTime = z ? LocalTime(d) : d;
                if (i2 >= 4 && length > 0) {
                    HourFromTime = dArr[0];
                    i4 = 1;
                } else {
                    HourFromTime = HourFromTime(LocalTime);
                }
                if (i2 >= 3 && i4 < length) {
                    MinFromTime = dArr[i4];
                    i4++;
                } else {
                    MinFromTime = MinFromTime(LocalTime);
                }
                if (i2 >= 2 && i4 < length) {
                    double d2 = dArr[i4];
                    i4++;
                    SecFromTime = d2;
                } else {
                    SecFromTime = SecFromTime(LocalTime);
                }
                if (i2 >= 1 && i4 < length) {
                    msFromTime = dArr[i4];
                } else {
                    msFromTime = msFromTime(LocalTime);
                }
                double MakeDate = MakeDate(Day(LocalTime), MakeTime(HourFromTime, MinFromTime, SecFromTime, msFromTime));
                if (z) {
                    MakeDate = internalUTC(MakeDate);
                }
                return TimeClip(MakeDate);
            case 32:
                z = false;
                i2 = 1;
                if (objArr.length >= i2) {
                }
                double[] dArr2 = new double[4];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                    break;
                }
                return ScriptRuntime.NaN;
            case 33:
                z = true;
                i2 = 2;
                if (objArr.length >= i2) {
                }
                double[] dArr22 = new double[4];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                return ScriptRuntime.NaN;
            case 34:
                z = false;
                i2 = 2;
                if (objArr.length >= i2) {
                }
                double[] dArr222 = new double[4];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                return ScriptRuntime.NaN;
            case 35:
                z = true;
                i2 = 3;
                if (objArr.length >= i2) {
                }
                double[] dArr2222 = new double[4];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                return ScriptRuntime.NaN;
            case 36:
                z = false;
                i2 = 3;
                if (objArr.length >= i2) {
                }
                double[] dArr22222 = new double[4];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                return ScriptRuntime.NaN;
            case 37:
                z = true;
                i2 = 4;
                if (objArr.length >= i2) {
                }
                double[] dArr222222 = new double[4];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                return ScriptRuntime.NaN;
            case 38:
                z = false;
                i2 = 4;
                if (objArr.length >= i2) {
                }
                double[] dArr2222222 = new double[4];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                return ScriptRuntime.NaN;
            default:
                throw Kit.codeBug();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x000c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static double makeDate(double d, Object[] objArr, int i) {
        boolean z;
        int i2;
        int length;
        int i3;
        boolean z2;
        double LocalTime;
        double YearFromTime;
        double MonthFromTime;
        double DateFromTime;
        if (objArr.length == 0) {
            return ScriptRuntime.NaN;
        }
        int i4 = 0;
        switch (i) {
            case 39:
                z = true;
                i2 = 1;
                length = objArr.length >= i2 ? objArr.length : i2;
                double[] dArr = new double[3];
                z2 = false;
                for (i3 = 0; i3 < length; i3++) {
                    double number = ScriptRuntime.toNumber(objArr[i3]);
                    if (number != number || Double.isInfinite(number)) {
                        z2 = true;
                    } else {
                        dArr[i3] = ScriptRuntime.toInteger(number);
                    }
                }
                if (!z2) {
                    return ScriptRuntime.NaN;
                }
                if (d == d) {
                    LocalTime = z ? LocalTime(d) : d;
                } else {
                    if (i2 < 3) {
                        return ScriptRuntime.NaN;
                    }
                    LocalTime = 0.0d;
                }
                if (i2 >= 3 && length > 0) {
                    YearFromTime = dArr[0];
                    i4 = 1;
                } else {
                    YearFromTime = YearFromTime(LocalTime);
                }
                if (i2 >= 2 && i4 < length) {
                    double d2 = dArr[i4];
                    i4++;
                    MonthFromTime = d2;
                } else {
                    MonthFromTime = MonthFromTime(LocalTime);
                }
                if (i2 >= 1 && i4 < length) {
                    DateFromTime = dArr[i4];
                } else {
                    DateFromTime = DateFromTime(LocalTime);
                }
                double MakeDate = MakeDate(MakeDay(YearFromTime, MonthFromTime, DateFromTime), TimeWithinDay(LocalTime));
                if (z) {
                    MakeDate = internalUTC(MakeDate);
                }
                return TimeClip(MakeDate);
            case 40:
                z = false;
                i2 = 1;
                if (objArr.length >= i2) {
                }
                double[] dArr2 = new double[3];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                break;
            case 41:
                z = true;
                i2 = 2;
                if (objArr.length >= i2) {
                }
                double[] dArr22 = new double[3];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                break;
            case 42:
                z = false;
                i2 = 2;
                if (objArr.length >= i2) {
                }
                double[] dArr222 = new double[3];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                break;
            case 43:
                z = true;
                i2 = 3;
                if (objArr.length >= i2) {
                }
                double[] dArr2222 = new double[3];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                break;
            case 44:
                z = false;
                i2 = 3;
                if (objArr.length >= i2) {
                }
                double[] dArr22222 = new double[3];
                z2 = false;
                while (i3 < length) {
                }
                if (!z2) {
                }
                break;
            default:
                throw Kit.codeBug();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0217 A[FALL_THROUGH] */
    @Override // org.mozilla.javascript.IdScriptableObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int findPrototypeId(String str) {
        String str2;
        int i = 2;
        switch (str.length()) {
            case 6:
                char charAt = str.charAt(0);
                if (charAt != 'g') {
                    if (charAt == 't') {
                        i = 47;
                        str2 = "toJSON";
                        break;
                    }
                    str2 = null;
                    i = 0;
                    break;
                } else {
                    i = 19;
                    str2 = "getDay";
                    break;
                }
            case 7:
                char charAt2 = str.charAt(3);
                if (charAt2 == 'D') {
                    char charAt3 = str.charAt(0);
                    if (charAt3 == 'g') {
                        i = 17;
                        str2 = "getDate";
                        break;
                    } else {
                        if (charAt3 == 's') {
                            i = 39;
                            str2 = "setDate";
                            break;
                        }
                        str2 = null;
                        i = 0;
                    }
                } else if (charAt2 == 'T') {
                    char charAt4 = str.charAt(0);
                    if (charAt4 == 'g') {
                        i = 11;
                        str2 = "getTime";
                        break;
                    } else {
                        if (charAt4 == 's') {
                            i = 30;
                            str2 = "setTime";
                            break;
                        }
                        str2 = null;
                        i = 0;
                    }
                } else if (charAt2 != 'Y') {
                    if (charAt2 == 'u') {
                        i = 10;
                        str2 = "valueOf";
                        break;
                    }
                    str2 = null;
                    i = 0;
                    break;
                } else {
                    char charAt5 = str.charAt(0);
                    if (charAt5 == 'g') {
                        i = 12;
                        str2 = "getYear";
                        break;
                    } else {
                        if (charAt5 == 's') {
                            i = 45;
                            str2 = "setYear";
                            break;
                        }
                        str2 = null;
                        i = 0;
                    }
                }
            case 8:
                char charAt6 = str.charAt(3);
                if (charAt6 == 'H') {
                    char charAt7 = str.charAt(0);
                    if (charAt7 == 'g') {
                        i = 21;
                        str2 = "getHours";
                        break;
                    } else {
                        if (charAt7 == 's') {
                            i = 37;
                            str2 = "setHours";
                            break;
                        }
                        str2 = null;
                        i = 0;
                    }
                } else if (charAt6 == 'M') {
                    char charAt8 = str.charAt(0);
                    if (charAt8 == 'g') {
                        i = 15;
                        str2 = "getMonth";
                        break;
                    } else {
                        if (charAt8 == 's') {
                            i = 41;
                            str2 = "setMonth";
                            break;
                        }
                        str2 = null;
                        i = 0;
                    }
                } else if (charAt6 == 'o') {
                    str2 = "toSource";
                    i = 9;
                    break;
                } else {
                    if (charAt6 == 't') {
                        str2 = "toString";
                        break;
                    }
                    str2 = null;
                    i = 0;
                    break;
                }
            case 9:
                i = 20;
                str2 = "getUTCDay";
                break;
            case 10:
                char charAt9 = str.charAt(3);
                if (charAt9 == 'M') {
                    char charAt10 = str.charAt(0);
                    if (charAt10 != 'g') {
                        if (charAt10 == 's') {
                            i = 35;
                            str2 = "setMinutes";
                            break;
                        }
                        str2 = null;
                        i = 0;
                        break;
                    } else {
                        i = 23;
                        str2 = "getMinutes";
                        break;
                    }
                } else if (charAt9 == 'S') {
                    char charAt11 = str.charAt(0);
                    if (charAt11 == 'g') {
                        i = 25;
                        str2 = "getSeconds";
                        break;
                    } else {
                        if (charAt11 == 's') {
                            i = 33;
                            str2 = "setSeconds";
                            break;
                        }
                        str2 = null;
                        i = 0;
                    }
                } else {
                    if (charAt9 == 'U') {
                        char charAt12 = str.charAt(0);
                        if (charAt12 != 'g') {
                            if (charAt12 == 's') {
                                i = 40;
                                str2 = "setUTCDate";
                                break;
                            }
                        } else {
                            i = 18;
                            str2 = "getUTCDate";
                            break;
                        }
                    }
                    str2 = null;
                    i = 0;
                }
            case 11:
                char charAt13 = str.charAt(3);
                if (charAt13 != 'F') {
                    if (charAt13 == 'M') {
                        str2 = "toGMTString";
                    } else if (charAt13 == 's') {
                        i = 1;
                        str2 = "constructor";
                        break;
                    } else {
                        switch (charAt13) {
                            case 'S':
                                i = 46;
                                str2 = "toISOString";
                                break;
                            case 'T':
                                str2 = "toUTCString";
                                break;
                            case 'U':
                                char charAt14 = str.charAt(0);
                                if (charAt14 != 'g') {
                                    if (charAt14 == 's') {
                                        char charAt15 = str.charAt(9);
                                        if (charAt15 != 'r') {
                                            if (charAt15 == 't') {
                                                i = 42;
                                                str2 = "setUTCMonth";
                                                break;
                                            }
                                        } else {
                                            i = 38;
                                            str2 = "setUTCHours";
                                            break;
                                        }
                                    }
                                } else {
                                    char charAt16 = str.charAt(9);
                                    if (charAt16 != 'r') {
                                        if (charAt16 == 't') {
                                            i = 16;
                                            str2 = "getUTCMonth";
                                            break;
                                        }
                                    } else {
                                        i = 22;
                                        str2 = "getUTCHours";
                                        break;
                                    }
                                }
                            default:
                                str2 = null;
                                i = 0;
                                break;
                        }
                    }
                    i = 8;
                    break;
                } else {
                    char charAt17 = str.charAt(0);
                    if (charAt17 == 'g') {
                        i = 13;
                        str2 = "getFullYear";
                        break;
                    } else {
                        if (charAt17 == 's') {
                            i = 43;
                            str2 = "setFullYear";
                            break;
                        }
                        str2 = null;
                        i = 0;
                    }
                }
            case 12:
                char charAt18 = str.charAt(2);
                if (charAt18 != 'D') {
                    if (charAt18 == 'T') {
                        str2 = "toTimeString";
                        i = 3;
                        break;
                    }
                    str2 = null;
                    i = 0;
                    break;
                } else {
                    i = 4;
                    str2 = "toDateString";
                    break;
                }
            case 13:
                char charAt19 = str.charAt(0);
                if (charAt19 == 'g') {
                    char charAt20 = str.charAt(6);
                    if (charAt20 != 'M') {
                        if (charAt20 == 'S') {
                            i = 26;
                            str2 = "getUTCSeconds";
                            break;
                        }
                        str2 = null;
                        i = 0;
                        break;
                    } else {
                        i = 24;
                        str2 = "getUTCMinutes";
                        break;
                    }
                } else {
                    if (charAt19 == 's') {
                        char charAt21 = str.charAt(6);
                        if (charAt21 != 'M') {
                            if (charAt21 == 'S') {
                                i = 34;
                                str2 = "setUTCSeconds";
                                break;
                            }
                        } else {
                            i = 36;
                            str2 = "setUTCMinutes";
                            break;
                        }
                    }
                    str2 = null;
                    i = 0;
                }
            case 14:
                char charAt22 = str.charAt(0);
                if (charAt22 != 'g') {
                    if (charAt22 != 's') {
                        if (charAt22 == 't') {
                            i = 5;
                            str2 = "toLocaleString";
                            break;
                        }
                        str2 = null;
                        i = 0;
                        break;
                    } else {
                        i = 44;
                        str2 = "setUTCFullYear";
                        break;
                    }
                } else {
                    i = 14;
                    str2 = "getUTCFullYear";
                    break;
                }
            case 15:
                char charAt23 = str.charAt(0);
                if (charAt23 != 'g') {
                    if (charAt23 == 's') {
                        i = 31;
                        str2 = "setMilliseconds";
                        break;
                    }
                    str2 = null;
                    i = 0;
                    break;
                } else {
                    i = 27;
                    str2 = "getMilliseconds";
                    break;
                }
            case 17:
                i = 29;
                str2 = "getTimezoneOffset";
                break;
            case 18:
                char charAt24 = str.charAt(0);
                if (charAt24 != 'g') {
                    if (charAt24 != 's') {
                        if (charAt24 == 't') {
                            char charAt25 = str.charAt(8);
                            if (charAt25 != 'D') {
                                if (charAt25 == 'T') {
                                    str2 = "toLocaleTimeString";
                                    i = 6;
                                    break;
                                }
                            } else {
                                i = 7;
                                str2 = "toLocaleDateString";
                                break;
                            }
                        }
                        str2 = null;
                        i = 0;
                        break;
                    } else {
                        i = 32;
                        str2 = "setUTCMilliseconds";
                        break;
                    }
                } else {
                    i = 28;
                    str2 = "getUTCMilliseconds";
                    break;
                }
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
