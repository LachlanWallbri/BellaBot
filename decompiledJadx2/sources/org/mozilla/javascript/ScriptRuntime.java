package org.mozilla.javascript;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.mozilla.javascript.NativeIterator;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.typedarrays.NativeArrayBuffer;
import org.mozilla.javascript.typedarrays.NativeDataView;
import org.mozilla.javascript.v8dtoa.DoubleConversion;
import org.mozilla.javascript.v8dtoa.FastDtoa;
import org.mozilla.javascript.xml.XMLLib;
import org.mozilla.javascript.xml.XMLObject;
import org.objenesis.strategy.PlatformDescription;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class ScriptRuntime {
    private static final String DEFAULT_NS_TAG = "__default_namespace__";
    public static final int ENUMERATE_ARRAY = 2;
    public static final int ENUMERATE_ARRAY_NO_ITERATOR = 5;
    public static final int ENUMERATE_KEYS = 0;
    public static final int ENUMERATE_KEYS_NO_ITERATOR = 3;
    public static final int ENUMERATE_VALUES = 1;
    public static final int ENUMERATE_VALUES_IN_ORDER = 6;
    public static final int ENUMERATE_VALUES_NO_ITERATOR = 4;
    public static final Class<?> BooleanClass = Kit.classOrNull("java.lang.Boolean");
    public static final Class<?> ByteClass = Kit.classOrNull("java.lang.Byte");
    public static final Class<?> CharacterClass = Kit.classOrNull("java.lang.Character");
    public static final Class<?> ClassClass = Kit.classOrNull("java.lang.Class");
    public static final Class<?> DoubleClass = Kit.classOrNull("java.lang.Double");
    public static final Class<?> FloatClass = Kit.classOrNull("java.lang.Float");
    public static final Class<?> IntegerClass = Kit.classOrNull("java.lang.Integer");
    public static final Class<?> LongClass = Kit.classOrNull("java.lang.Long");
    public static final Class<?> NumberClass = Kit.classOrNull("java.lang.Number");
    public static final Class<?> ObjectClass = Kit.classOrNull("java.lang.Object");
    public static final Class<?> ShortClass = Kit.classOrNull("java.lang.Short");
    public static final Class<?> StringClass = Kit.classOrNull("java.lang.String");
    public static final Class<?> DateClass = Kit.classOrNull("java.util.Date");
    public static final Class<?> ContextClass = Kit.classOrNull("org.mozilla.javascript.Context");
    public static final Class<?> ContextFactoryClass = Kit.classOrNull("org.mozilla.javascript.ContextFactory");
    public static final Class<?> FunctionClass = Kit.classOrNull("org.mozilla.javascript.Function");
    public static final Class<?> ScriptableObjectClass = Kit.classOrNull("org.mozilla.javascript.ScriptableObject");
    public static final Class<Scriptable> ScriptableClass = Scriptable.class;
    public static Locale ROOT_LOCALE = new Locale("");
    private static final Object LIBRARY_SCOPE_KEY = "LIBRARY_SCOPE";
    public static final double NaN = Double.longBitsToDouble(9221120237041090560L);
    public static final double negativeZero = Double.longBitsToDouble(Long.MIN_VALUE);
    public static final Double NaNobj = new Double(NaN);
    public static MessageProvider messageProvider = new DefaultMessageProvider();
    public static final Object[] emptyArgs = new Object[0];
    public static final String[] emptyStrings = new String[0];

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public interface MessageProvider {
        String getMessage(String str, Object[] objArr);
    }

    public static boolean isJSLineTerminator(int i) {
        if ((57296 & i) != 0) {
            return false;
        }
        return i == 10 || i == 13 || i == 8232 || i == 8233;
    }

    @Deprecated
    public static BaseFunction typeErrorThrower() {
        return typeErrorThrower(Context.getCurrentContext());
    }

    public static BaseFunction typeErrorThrower(Context context) {
        if (context.typeErrorThrower == null) {
            BaseFunction baseFunction = new BaseFunction() { // from class: org.mozilla.javascript.ScriptRuntime.1
                static final long serialVersionUID = -5891740962154902286L;

                @Override // org.mozilla.javascript.BaseFunction
                public int getLength() {
                    return 0;
                }

                @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
                public Object call(Context context2, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
                    throw ScriptRuntime.typeError0("msg.op.not.allowed");
                }
            };
            setFunctionProtoAndParent(baseFunction, context.topCallScope);
            baseFunction.preventExtensions();
            context.typeErrorThrower = baseFunction;
        }
        return context.typeErrorThrower;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class NoSuchMethodShim implements Callable {
        String methodName;
        Callable noSuchMethodMethod;

        NoSuchMethodShim(Callable callable, String str) {
            this.noSuchMethodMethod = callable;
            this.methodName = str;
        }

        @Override // org.mozilla.javascript.Callable
        public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            return this.noSuchMethodMethod.call(context, scriptable, scriptable2, new Object[]{this.methodName, ScriptRuntime.newArrayLiteral(objArr, null, context, scriptable)});
        }
    }

    public static boolean isRhinoRuntimeType(Class<?> cls) {
        return cls.isPrimitive() ? cls != Character.TYPE : cls == StringClass || cls == BooleanClass || NumberClass.isAssignableFrom(cls) || ScriptableClass.isAssignableFrom(cls);
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
    
        r8 = r8;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [org.mozilla.javascript.ClassCache] */
    /* JADX WARN: Type inference failed for: r8v1, types: [org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ScriptableObject initSafeStandardObjects(Context context, ScriptableObject scriptableObject, boolean z) {
        TopLevel topLevel;
        if (scriptableObject == null) {
            topLevel = new NativeObject();
        }
        topLevel.associateValue(LIBRARY_SCOPE_KEY, topLevel);
        new ClassCache().associate(topLevel);
        BaseFunction.init(topLevel, z);
        NativeObject.init(topLevel, z);
        Scriptable objectPrototype = ScriptableObject.getObjectPrototype(topLevel);
        ScriptableObject.getClassPrototype(topLevel, "Function").setPrototype(objectPrototype);
        if (topLevel.getPrototype() == null) {
            topLevel.setPrototype(objectPrototype);
        }
        NativeError.init(topLevel, z);
        NativeGlobal.init(context, topLevel, z);
        NativeArray.init(topLevel, z);
        if (context.getOptimizationLevel() > 0) {
            NativeArray.setMaximumInitialCapacity(200000);
        }
        NativeString.init(topLevel, z);
        NativeBoolean.init(topLevel, z);
        NativeNumber.init(topLevel, z);
        NativeDate.init(topLevel, z);
        NativeMath.init(topLevel, z);
        NativeJSON.init(topLevel, z);
        NativeWith.init(topLevel, z);
        NativeCall.init(topLevel, z);
        NativeScript.init(topLevel, z);
        NativeIterator.init(topLevel, z);
        NativeArrayIterator.init(topLevel, z);
        NativeStringIterator.init(topLevel, z);
        boolean z2 = context.hasFeature(6) && context.getE4xImplementationFactory() != null;
        ScriptableObject scriptableObject2 = topLevel;
        new LazilyLoadedCtor(scriptableObject2, "RegExp", "org.mozilla.javascript.regexp.NativeRegExp", z, true);
        new LazilyLoadedCtor(scriptableObject2, "Continuation", "org.mozilla.javascript.NativeContinuation", z, true);
        if (z2) {
            String implementationClassName = context.getE4xImplementationFactory().getImplementationClassName();
            ScriptableObject scriptableObject3 = topLevel;
            new LazilyLoadedCtor(scriptableObject3, "XML", implementationClassName, z, true);
            new LazilyLoadedCtor(scriptableObject3, "XMLList", implementationClassName, z, true);
            new LazilyLoadedCtor(scriptableObject3, "Namespace", implementationClassName, z, true);
            new LazilyLoadedCtor(scriptableObject3, "QName", implementationClassName, z, true);
        }
        if ((context.getLanguageVersion() >= 180 && context.hasFeature(14)) || context.getLanguageVersion() >= 200) {
            ScriptableObject scriptableObject4 = topLevel;
            new LazilyLoadedCtor(scriptableObject4, NativeArrayBuffer.CLASS_NAME, "org.mozilla.javascript.typedarrays.NativeArrayBuffer", z, true);
            new LazilyLoadedCtor(scriptableObject4, "Int8Array", "org.mozilla.javascript.typedarrays.NativeInt8Array", z, true);
            new LazilyLoadedCtor(scriptableObject4, "Uint8Array", "org.mozilla.javascript.typedarrays.NativeUint8Array", z, true);
            new LazilyLoadedCtor(scriptableObject4, "Uint8ClampedArray", "org.mozilla.javascript.typedarrays.NativeUint8ClampedArray", z, true);
            new LazilyLoadedCtor(scriptableObject4, "Int16Array", "org.mozilla.javascript.typedarrays.NativeInt16Array", z, true);
            new LazilyLoadedCtor(scriptableObject4, "Uint16Array", "org.mozilla.javascript.typedarrays.NativeUint16Array", z, true);
            new LazilyLoadedCtor(scriptableObject4, "Int32Array", "org.mozilla.javascript.typedarrays.NativeInt32Array", z, true);
            new LazilyLoadedCtor(scriptableObject4, "Uint32Array", "org.mozilla.javascript.typedarrays.NativeUint32Array", z, true);
            new LazilyLoadedCtor(scriptableObject4, "Float32Array", "org.mozilla.javascript.typedarrays.NativeFloat32Array", z, true);
            new LazilyLoadedCtor(scriptableObject4, "Float64Array", "org.mozilla.javascript.typedarrays.NativeFloat64Array", z, true);
            new LazilyLoadedCtor(scriptableObject4, NativeDataView.CLASS_NAME, "org.mozilla.javascript.typedarrays.NativeDataView", z, true);
        }
        if (context.getLanguageVersion() >= 200) {
            NativeSymbol.init(context, topLevel, z);
        }
        if (topLevel instanceof TopLevel) {
            topLevel.cacheBuiltins();
        }
        return topLevel;
    }

    public static ScriptableObject initStandardObjects(Context context, ScriptableObject scriptableObject, boolean z) {
        ScriptableObject initSafeStandardObjects = initSafeStandardObjects(context, scriptableObject, z);
        new LazilyLoadedCtor(initSafeStandardObjects, "Packages", "org.mozilla.javascript.NativeJavaTopPackage", z, true);
        new LazilyLoadedCtor(initSafeStandardObjects, "getClass", "org.mozilla.javascript.NativeJavaTopPackage", z, true);
        new LazilyLoadedCtor(initSafeStandardObjects, "JavaAdapter", "org.mozilla.javascript.JavaAdapter", z, true);
        new LazilyLoadedCtor(initSafeStandardObjects, "JavaImporter", "org.mozilla.javascript.ImporterTopLevel", z, true);
        for (String str : getTopPackageNames()) {
            new LazilyLoadedCtor(initSafeStandardObjects, str, "org.mozilla.javascript.NativeJavaTopPackage", z, true);
        }
        return initSafeStandardObjects;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] getTopPackageNames() {
        return PlatformDescription.DALVIK.equals(System.getProperty("java.vm.name")) ? new String[]{"java", "javax", "org", "com", "edu", "net", "android"} : new String[]{"java", "javax", "org", "com", "edu", "net"};
    }

    public static ScriptableObject getLibraryScopeOrNull(Scriptable scriptable) {
        return (ScriptableObject) ScriptableObject.getTopScopeValue(scriptable, LIBRARY_SCOPE_KEY);
    }

    public static boolean isJSWhitespaceOrLineTerminator(int i) {
        return isStrWhiteSpaceChar(i) || isJSLineTerminator(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isStrWhiteSpaceChar(int i) {
        if (i == 32 || i == 160 || i == 65279 || i == 8232 || i == 8233) {
            return true;
        }
        switch (i) {
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                return true;
            default:
                return Character.getType(i) == 12;
        }
    }

    public static Boolean wrapBoolean(boolean z) {
        return z ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Integer wrapInt(int i) {
        return Integer.valueOf(i);
    }

    public static Number wrapNumber(double d) {
        if (d != d) {
            return NaNobj;
        }
        return new Double(d);
    }

    public static boolean toBoolean(Object obj) {
        while (!(obj instanceof Boolean)) {
            if (obj == null || obj == Undefined.instance) {
                return false;
            }
            if (obj instanceof CharSequence) {
                return ((CharSequence) obj).length() != 0;
            }
            if (obj instanceof Number) {
                double doubleValue = ((Number) obj).doubleValue();
                return doubleValue == doubleValue && doubleValue != 0.0d;
            }
            if (obj instanceof Scriptable) {
                if ((obj instanceof ScriptableObject) && ((ScriptableObject) obj).avoidObjectDetection()) {
                    return false;
                }
                if (Context.getContext().isVersionECMA1()) {
                    return true;
                }
                obj = ((Scriptable) obj).getDefaultValue(BooleanClass);
                if ((obj instanceof Scriptable) && !isSymbol(obj)) {
                    throw errorWithClassName("msg.primitive.expected", obj);
                }
            } else {
                warnAboutNonJSObject(obj);
                return true;
            }
        }
        return ((Boolean) obj).booleanValue();
    }

    public static double toNumber(Object obj) {
        while (!(obj instanceof Number)) {
            if (obj == null) {
                return 0.0d;
            }
            if (obj == Undefined.instance) {
                return NaN;
            }
            if (obj instanceof String) {
                return toNumber((String) obj);
            }
            if (obj instanceof CharSequence) {
                return toNumber(obj.toString());
            }
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue() ? 1.0d : 0.0d;
            }
            if (obj instanceof Symbol) {
                throw typeError0("msg.not.a.number");
            }
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(NumberClass);
                if ((obj instanceof Scriptable) && !isSymbol(obj)) {
                    throw errorWithClassName("msg.primitive.expected", obj);
                }
            } else {
                warnAboutNonJSObject(obj);
                return NaN;
            }
        }
        return ((Number) obj).doubleValue();
    }

    public static double toNumber(Object[] objArr, int i) {
        return i < objArr.length ? toNumber(objArr[i]) : NaN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double stringPrefixToNumber(String str, int i, int i2) {
        return stringToNumber(str, i, str.length() - 1, i2, true);
    }

    static double stringToNumber(String str, int i, int i2, int i3) {
        return stringToNumber(str, i, i2, i3, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x009a, code lost:
    
        if (r10 != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00a1, code lost:
    
        r14 = r14 + 1.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x009f, code lost:
    
        if ((r10 & r12) != false) goto L59;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static double stringToNumber(String str, int i, int i2, int i3, boolean z) {
        char c;
        char c2;
        char c3;
        int i4;
        int i5;
        int i6 = i;
        char c4 = i3 < 10 ? (char) ((i3 + 48) - 1) : '9';
        char c5 = 'A';
        char c6 = 'a';
        if (i3 > 10) {
            c2 = (char) ((i3 + 97) - 10);
            c = (char) ((i3 + 65) - 10);
        } else {
            c = 'A';
            c2 = 'a';
        }
        int i7 = i6;
        double d = 0.0d;
        while (true) {
            c3 = '0';
            if (i7 > i2) {
                break;
            }
            char charAt = str.charAt(i7);
            if ('0' > charAt || charAt > c4) {
                if (c6 <= charAt && charAt < c2) {
                    i4 = charAt - 'a';
                } else {
                    if (c5 > charAt || charAt >= c) {
                        break;
                    }
                    i4 = charAt - 'A';
                }
                i5 = i4 + 10;
            } else {
                i5 = charAt - '0';
            }
            d = (d * i3) + i5;
            i7++;
            c5 = 'A';
            c6 = 'a';
        }
        if (!z) {
            return NaN;
        }
        if (i6 == i7) {
            return NaN;
        }
        if (d >= 9.007199254740992E15d) {
            if (i3 == 10) {
                try {
                    return Double.parseDouble(str.substring(i6, i7));
                } catch (NumberFormatException unused) {
                    return NaN;
                }
            }
            if (i3 == 2 || i3 == 4 || i3 == 8 || i3 == 16 || i3 == 32) {
                int i8 = 53;
                int i9 = 1;
                char c7 = 0;
                boolean z2 = false;
                boolean z3 = false;
                int i10 = 0;
                double d2 = 0.0d;
                while (true) {
                    if (i9 == 1) {
                        if (i6 == i7) {
                            break;
                        }
                        int i11 = i6 + 1;
                        char charAt2 = str.charAt(i6);
                        i10 = (c3 > charAt2 || charAt2 > '9') ? ('a' > charAt2 || charAt2 > 'z') ? charAt2 - '7' : charAt2 - 'W' : charAt2 - '0';
                        i6 = i11;
                        i9 = i3;
                    }
                    i9 >>= 1;
                    boolean z4 = (i10 & i9) != 0;
                    if (c7 != 0) {
                        if (c7 == 1) {
                            d *= 2.0d;
                            if (z4) {
                                d += 1.0d;
                            }
                            i8--;
                            if (i8 == 0) {
                                c7 = 2;
                                z3 = z4;
                            }
                        } else if (c7 != 2) {
                            if (c7 != 3) {
                                if (c7 != 4) {
                                }
                            } else if (z4) {
                                c7 = 4;
                            }
                            d2 *= 2.0d;
                        } else {
                            c7 = 3;
                            z2 = z4;
                            d2 = 2.0d;
                        }
                    } else if (z4) {
                        i8--;
                        c7 = 1;
                        d = 1.0d;
                    }
                    c3 = '0';
                }
                if (c7 == 0) {
                    return 0.0d;
                }
                if (c7 != 3) {
                    if (c7 == 4) {
                    }
                }
                return d * d2;
            }
        }
        return d;
    }

    public static double toNumber(String str) {
        char charAt;
        int i;
        char charAt2;
        int length = str.length();
        int i2 = 0;
        while (i2 != length) {
            char charAt3 = str.charAt(i2);
            if (!isStrWhiteSpaceChar(charAt3)) {
                int i3 = length - 1;
                while (true) {
                    charAt = str.charAt(i3);
                    if (!isStrWhiteSpaceChar(charAt)) {
                        break;
                    }
                    i3--;
                }
                Context currentContext = Context.getCurrentContext();
                boolean z = currentContext == null || currentContext.getLanguageVersion() < 200;
                int i4 = 16;
                if (charAt3 == '0') {
                    int i5 = i2 + 2;
                    if (i5 <= i3) {
                        char charAt4 = str.charAt(i2 + 1);
                        if (charAt4 != 'x' && charAt4 != 'X') {
                            i4 = (z || !(charAt4 == 'o' || charAt4 == 'O')) ? (z || !(charAt4 == 'b' || charAt4 == 'B')) ? -1 : 2 : 8;
                        }
                        if (i4 != -1) {
                            if (z) {
                                return stringPrefixToNumber(str, i5, i4);
                            }
                            return stringToNumber(str, i5, i3, i4);
                        }
                    }
                } else if (z && ((charAt3 == '+' || charAt3 == '-') && (i = i2 + 3) <= i3 && str.charAt(i2 + 1) == '0' && ((charAt2 = str.charAt(i2 + 2)) == 'x' || charAt2 == 'X'))) {
                    double stringPrefixToNumber = stringPrefixToNumber(str, i, 16);
                    return charAt3 == '-' ? -stringPrefixToNumber : stringPrefixToNumber;
                }
                if (charAt == 'y') {
                    if (charAt3 == '+' || charAt3 == '-') {
                        i2++;
                    }
                    if (i2 + 7 == i3 && str.regionMatches(i2, "Infinity", 0, 8)) {
                        return charAt3 == '-' ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                    }
                    return NaN;
                }
                String substring = str.substring(i2, i3 + 1);
                for (int length2 = substring.length() - 1; length2 >= 0; length2--) {
                    char charAt5 = substring.charAt(length2);
                    if (('0' > charAt5 || charAt5 > '9') && charAt5 != '.' && charAt5 != 'e' && charAt5 != 'E' && charAt5 != '+' && charAt5 != '-') {
                        return NaN;
                    }
                }
                try {
                    return Double.parseDouble(substring);
                } catch (NumberFormatException unused) {
                    return NaN;
                }
            }
            i2++;
        }
        return 0.0d;
    }

    public static Object[] padArguments(Object[] objArr, int i) {
        if (i < objArr.length) {
            return objArr;
        }
        Object[] objArr2 = new Object[i];
        int i2 = 0;
        while (i2 < objArr.length) {
            objArr2[i2] = objArr[i2];
            i2++;
        }
        while (i2 < i) {
            objArr2[i2] = Undefined.instance;
            i2++;
        }
        return objArr2;
    }

    public static String escapeString(String str) {
        return escapeString(str, '\"');
    }

    public static String escapeString(String str, char c) {
        int i;
        if (c != '\"' && c != '\'') {
            Kit.codeBug();
        }
        StringBuilder sb = null;
        int length = str.length();
        for (int i2 = 0; i2 != length; i2++) {
            char charAt = str.charAt(i2);
            int i3 = 32;
            if (' ' > charAt || charAt > '~' || charAt == c || charAt == '\\') {
                if (sb == null) {
                    sb = new StringBuilder(length + 3);
                    sb.append(str);
                    sb.setLength(i2);
                }
                if (charAt != ' ') {
                    if (charAt != '\\') {
                        switch (charAt) {
                            case '\b':
                                i3 = 98;
                                break;
                            case '\t':
                                i3 = 116;
                                break;
                            case '\n':
                                i3 = 110;
                                break;
                            case 11:
                                i3 = 118;
                                break;
                            case '\f':
                                i3 = 102;
                                break;
                            case '\r':
                                i3 = 114;
                                break;
                            default:
                                i3 = -1;
                                break;
                        }
                    } else {
                        i3 = 92;
                    }
                }
                if (i3 >= 0) {
                    sb.append('\\');
                    sb.append((char) i3);
                } else if (charAt == c) {
                    sb.append('\\');
                    sb.append(c);
                } else {
                    if (charAt < 256) {
                        sb.append("\\x");
                        i = 2;
                    } else {
                        sb.append("\\u");
                        i = 4;
                    }
                    for (int i4 = (i - 1) * 4; i4 >= 0; i4 -= 4) {
                        int i5 = (charAt >> i4) & 15;
                        sb.append((char) (i5 < 10 ? i5 + 48 : i5 + 87));
                    }
                }
            } else if (sb != null) {
                sb.append(charAt);
            }
        }
        return sb == null ? str : sb.toString();
    }

    static boolean isValidIdentifierName(String str, Context context, boolean z) {
        int length = str.length();
        if (length == 0 || !Character.isJavaIdentifierStart(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i != length; i++) {
            if (!Character.isJavaIdentifierPart(str.charAt(i))) {
                return false;
            }
        }
        return !TokenStream.isKeyword(str, context.getLanguageVersion(), z);
    }

    public static CharSequence toCharSequence(Object obj) {
        if (obj instanceof NativeString) {
            return ((NativeString) obj).toCharSequence();
        }
        return obj instanceof CharSequence ? (CharSequence) obj : toString(obj);
    }

    public static String toString(Object obj) {
        while (obj != null) {
            if (obj == Undefined.instance || obj == Undefined.SCRIPTABLE_UNDEFINED) {
                return "undefined";
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            if (obj instanceof CharSequence) {
                return obj.toString();
            }
            if (obj instanceof Number) {
                return numberToString(((Number) obj).doubleValue(), 10);
            }
            if (obj instanceof Symbol) {
                throw typeError0("msg.not.a.string");
            }
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(StringClass);
                if ((obj instanceof Scriptable) && !isSymbol(obj)) {
                    throw errorWithClassName("msg.primitive.expected", obj);
                }
            } else {
                return obj.toString();
            }
        }
        return "null";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String defaultObjectToString(Scriptable scriptable) {
        if (scriptable == null) {
            return "[object Null]";
        }
        if (Undefined.isUndefined(scriptable)) {
            return "[object Undefined]";
        }
        return "[object " + scriptable.getClassName() + ']';
    }

    public static String toString(Object[] objArr, int i) {
        return i < objArr.length ? toString(objArr[i]) : "undefined";
    }

    public static String toString(double d) {
        return numberToString(d, 10);
    }

    public static String numberToString(double d, int i) {
        if (i < 2 || i > 36) {
            throw Context.reportRuntimeError1("msg.bad.radix", Integer.toString(i));
        }
        if (d != d) {
            return "NaN";
        }
        if (d == Double.POSITIVE_INFINITY) {
            return "Infinity";
        }
        if (d == Double.NEGATIVE_INFINITY) {
            return "-Infinity";
        }
        if (d == 0.0d) {
            return "0";
        }
        if (i != 10) {
            return DToA.JS_dtobasestr(i, d);
        }
        String numberToString = FastDtoa.numberToString(d);
        if (numberToString != null) {
            return numberToString;
        }
        StringBuilder sb = new StringBuilder();
        DToA.JS_dtostr(sb, 0, 0, d);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String uneval(Context context, Scriptable scriptable, Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj == Undefined.instance) {
            return "undefined";
        }
        if (obj instanceof CharSequence) {
            String escapeString = escapeString(obj.toString());
            StringBuilder sb = new StringBuilder(escapeString.length() + 2);
            sb.append('\"');
            sb.append(escapeString);
            sb.append('\"');
            return sb.toString();
        }
        if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            return (doubleValue != 0.0d || 1.0d / doubleValue >= 0.0d) ? toString(doubleValue) : "-0";
        }
        if (obj instanceof Boolean) {
            return toString(obj);
        }
        if (obj instanceof Scriptable) {
            Scriptable scriptable2 = (Scriptable) obj;
            if (ScriptableObject.hasProperty(scriptable2, "toSource")) {
                Object property = ScriptableObject.getProperty(scriptable2, "toSource");
                if (property instanceof Function) {
                    return toString(((Function) property).call(context, scriptable, scriptable2, emptyArgs));
                }
            }
            return toString(obj);
        }
        warnAboutNonJSObject(obj);
        return obj.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String defaultObjectToSource(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        boolean has;
        boolean z;
        Object obj;
        if (context.iterating == null) {
            context.iterating = new ObjToIntMap(31);
            z = true;
            has = false;
        } else {
            has = context.iterating.has(scriptable2);
            z = false;
        }
        StringBuilder sb = new StringBuilder(128);
        if (z) {
            sb.append("(");
        }
        sb.append('{');
        if (!has) {
            try {
                context.iterating.intern(scriptable2);
                Object[] ids = scriptable2.getIds();
                for (int i = 0; i < ids.length; i++) {
                    Object obj2 = ids[i];
                    if (obj2 instanceof Integer) {
                        int intValue = ((Integer) obj2).intValue();
                        obj = scriptable2.get(intValue, scriptable2);
                        if (obj != Scriptable.NOT_FOUND) {
                            if (i > 0) {
                                sb.append(", ");
                            }
                            sb.append(intValue);
                            sb.append(':');
                            sb.append(uneval(context, scriptable, obj));
                        }
                    } else {
                        String str = (String) obj2;
                        obj = scriptable2.get(str, scriptable2);
                        if (obj != Scriptable.NOT_FOUND) {
                            if (i > 0) {
                                sb.append(", ");
                            }
                            if (isValidIdentifierName(str, context, context.isStrictMode())) {
                                sb.append(str);
                            } else {
                                sb.append('\'');
                                sb.append(escapeString(str, '\''));
                                sb.append('\'');
                            }
                            sb.append(':');
                            sb.append(uneval(context, scriptable, obj));
                        }
                    }
                }
            } finally {
                if (z) {
                    context.iterating = null;
                }
            }
        }
        sb.append('}');
        if (z) {
            sb.append(')');
        }
        return sb.toString();
    }

    public static Scriptable toObject(Scriptable scriptable, Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        return toObject(Context.getContext(), scriptable, obj);
    }

    @Deprecated
    public static Scriptable toObjectOrNull(Context context, Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        if (obj == null || obj == Undefined.instance) {
            return null;
        }
        return toObject(context, getTopCallScope(context), obj);
    }

    public static Scriptable toObjectOrNull(Context context, Object obj, Scriptable scriptable) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        if (obj == null || obj == Undefined.instance) {
            return null;
        }
        return toObject(context, scriptable, obj);
    }

    @Deprecated
    public static Scriptable toObject(Scriptable scriptable, Object obj, Class<?> cls) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        return toObject(Context.getContext(), scriptable, obj);
    }

    public static Scriptable toObject(Context context, Scriptable scriptable, Object obj) {
        if (isSymbol(obj)) {
            NativeSymbol nativeSymbol = new NativeSymbol((NativeSymbol) obj);
            setBuiltinProtoAndParent(nativeSymbol, scriptable, TopLevel.Builtins.Symbol);
            return nativeSymbol;
        }
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        if (obj instanceof CharSequence) {
            NativeString nativeString = new NativeString((CharSequence) obj);
            setBuiltinProtoAndParent(nativeString, scriptable, TopLevel.Builtins.String);
            return nativeString;
        }
        if (obj instanceof Number) {
            NativeNumber nativeNumber = new NativeNumber(((Number) obj).doubleValue());
            setBuiltinProtoAndParent(nativeNumber, scriptable, TopLevel.Builtins.Number);
            return nativeNumber;
        }
        if (obj instanceof Boolean) {
            NativeBoolean nativeBoolean = new NativeBoolean(((Boolean) obj).booleanValue());
            setBuiltinProtoAndParent(nativeBoolean, scriptable, TopLevel.Builtins.Boolean);
            return nativeBoolean;
        }
        if (obj == null) {
            throw typeError0("msg.null.to.object");
        }
        if (obj == Undefined.instance) {
            throw typeError0("msg.undef.to.object");
        }
        Object wrap = context.getWrapFactory().wrap(context, scriptable, obj, null);
        if (wrap instanceof Scriptable) {
            return (Scriptable) wrap;
        }
        throw errorWithClassName("msg.invalid.type", obj);
    }

    @Deprecated
    public static Scriptable toObject(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        return toObject(context, scriptable, obj);
    }

    @Deprecated
    public static Object call(Context context, Object obj, Object obj2, Object[] objArr, Scriptable scriptable) {
        if (!(obj instanceof Function)) {
            throw notFunctionError(toString(obj));
        }
        Function function = (Function) obj;
        Scriptable objectOrNull = toObjectOrNull(context, obj2, scriptable);
        if (objectOrNull == null) {
            throw undefCallError(objectOrNull, "function");
        }
        return function.call(context, scriptable, objectOrNull, objArr);
    }

    public static Scriptable newObject(Context context, Scriptable scriptable, String str, Object[] objArr) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        Function existingCtor = getExistingCtor(context, topLevelScope, str);
        if (objArr == null) {
            objArr = emptyArgs;
        }
        return existingCtor.construct(context, topLevelScope, objArr);
    }

    public static Scriptable newBuiltinObject(Context context, Scriptable scriptable, TopLevel.Builtins builtins, Object[] objArr) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        Function builtinCtor = TopLevel.getBuiltinCtor(context, topLevelScope, builtins);
        if (objArr == null) {
            objArr = emptyArgs;
        }
        return builtinCtor.construct(context, topLevelScope, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Scriptable newNativeError(Context context, Scriptable scriptable, TopLevel.NativeErrors nativeErrors, Object[] objArr) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        Function nativeErrorCtor = TopLevel.getNativeErrorCtor(context, topLevelScope, nativeErrors);
        if (objArr == null) {
            objArr = emptyArgs;
        }
        return nativeErrorCtor.construct(context, topLevelScope, objArr);
    }

    public static double toInteger(Object obj) {
        return toInteger(toNumber(obj));
    }

    public static double toInteger(double d) {
        if (d != d) {
            return 0.0d;
        }
        if (d == 0.0d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY) {
            return d;
        }
        if (d > 0.0d) {
            return Math.floor(d);
        }
        return Math.ceil(d);
    }

    public static double toInteger(Object[] objArr, int i) {
        if (i < objArr.length) {
            return toInteger(objArr[i]);
        }
        return 0.0d;
    }

    public static int toInt32(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return toInt32(toNumber(obj));
    }

    public static int toInt32(Object[] objArr, int i) {
        if (i < objArr.length) {
            return toInt32(objArr[i]);
        }
        return 0;
    }

    public static int toInt32(double d) {
        return DoubleConversion.doubleToInt32(d);
    }

    public static long toUint32(double d) {
        return DoubleConversion.doubleToInt32(d) & 4294967295L;
    }

    public static long toUint32(Object obj) {
        return toUint32(toNumber(obj));
    }

    public static char toUint16(Object obj) {
        return (char) DoubleConversion.doubleToInt32(toNumber(obj));
    }

    public static Object setDefaultNamespace(Object obj, Context context) {
        Scriptable scriptable = context.currentActivationCall;
        if (scriptable == null) {
            scriptable = getTopCallScope(context);
        }
        Object defaultXmlNamespace = currentXMLLib(context).toDefaultXmlNamespace(context, obj);
        if (!scriptable.has(DEFAULT_NS_TAG, scriptable)) {
            ScriptableObject.defineProperty(scriptable, DEFAULT_NS_TAG, defaultXmlNamespace, 6);
        } else {
            scriptable.put(DEFAULT_NS_TAG, scriptable, defaultXmlNamespace);
        }
        return Undefined.instance;
    }

    public static Object searchDefaultNamespace(Context context) {
        Scriptable scriptable = context.currentActivationCall;
        if (scriptable == null) {
            scriptable = getTopCallScope(context);
        }
        while (true) {
            Scriptable parentScope = scriptable.getParentScope();
            if (parentScope == null) {
                Object property = ScriptableObject.getProperty(scriptable, DEFAULT_NS_TAG);
                if (property == Scriptable.NOT_FOUND) {
                    return null;
                }
                return property;
            }
            Object obj = scriptable.get(DEFAULT_NS_TAG, scriptable);
            if (obj != Scriptable.NOT_FOUND) {
                return obj;
            }
            scriptable = parentScope;
        }
    }

    public static Object getTopLevelProp(Scriptable scriptable, String str) {
        return ScriptableObject.getProperty(ScriptableObject.getTopLevelScope(scriptable), str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Function getExistingCtor(Context context, Scriptable scriptable, String str) {
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (property instanceof Function) {
            return (Function) property;
        }
        if (property == Scriptable.NOT_FOUND) {
            throw Context.reportRuntimeError1("msg.ctor.not.found", str);
        }
        throw Context.reportRuntimeError1("msg.not.ctor", str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
    
        if (r4 <= (r5 != 0 ? 8 : 7)) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static long indexFromString(String str) {
        int i;
        int length = str.length();
        if (length > 0) {
            int i2 = 0;
            char charAt = str.charAt(0);
            if (charAt != '-' || length <= 1) {
                i = 0;
            } else {
                charAt = str.charAt(1);
                if (charAt == '0') {
                    return -1L;
                }
                i = 1;
            }
            int i3 = i;
            int i4 = charAt - '0';
            if (i4 >= 0 && i4 <= 9) {
                if (length <= (i != 0 ? 11 : 10)) {
                    int i5 = -i4;
                    int i6 = i3 + 1;
                    if (i5 != 0) {
                        while (i6 != length) {
                            i4 = str.charAt(i6) - '0';
                            if (i4 < 0 || i4 > 9) {
                                break;
                            }
                            i6++;
                            int i7 = i5;
                            i5 = (i5 * 10) - i4;
                            i2 = i7;
                        }
                    }
                    if (i6 == length) {
                        if (i2 <= -214748364) {
                            if (i2 == -214748364) {
                            }
                        }
                        if (i == 0) {
                            i5 = -i5;
                        }
                        return 4294967295L & i5;
                    }
                }
            }
        }
        return -1L;
    }

    public static long testUint32String(String str) {
        int length = str.length();
        if (1 <= length && length <= 10) {
            int charAt = str.charAt(0) - '0';
            if (charAt == 0) {
                return length == 1 ? 0L : -1L;
            }
            if (1 <= charAt && charAt <= 9) {
                long j = charAt;
                for (int i = 1; i != length; i++) {
                    int charAt2 = str.charAt(i) - '0';
                    if (charAt2 < 0 || charAt2 > 9) {
                        return -1L;
                    }
                    j = (j * 10) + charAt2;
                }
                if ((j >>> 32) == 0) {
                    return j;
                }
            }
        }
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object getIndexObject(String str) {
        long indexFromString = indexFromString(str);
        return indexFromString >= 0 ? Integer.valueOf((int) indexFromString) : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object getIndexObject(double d) {
        int i = (int) d;
        if (i == d) {
            return Integer.valueOf(i);
        }
        return toString(d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toStringIdOrIndex(Context context, Object obj) {
        String scriptRuntime;
        if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            int i = (int) doubleValue;
            if (i == doubleValue) {
                storeIndexResult(context, i);
                return null;
            }
            return toString(obj);
        }
        if (obj instanceof String) {
            scriptRuntime = (String) obj;
        } else {
            scriptRuntime = toString(obj);
        }
        long indexFromString = indexFromString(scriptRuntime);
        if (indexFromString < 0) {
            return scriptRuntime;
        }
        storeIndexResult(context, (int) indexFromString);
        return null;
    }

    @Deprecated
    public static Object getObjectElem(Object obj, Object obj2, Context context) {
        return getObjectElem(obj, obj2, context, getTopCallScope(context));
    }

    public static Object getObjectElem(Object obj, Object obj2, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull == null) {
            throw undefReadError(obj, obj2);
        }
        return getObjectElem(objectOrNull, obj2, context);
    }

    public static Object getObjectElem(Scriptable scriptable, Object obj, Context context) {
        Object property;
        if (scriptable instanceof XMLObject) {
            property = ((XMLObject) scriptable).get(context, obj);
        } else if (isSymbol(obj)) {
            property = ScriptableObject.getProperty(scriptable, (Symbol) obj);
        } else {
            String stringIdOrIndex = toStringIdOrIndex(context, obj);
            if (stringIdOrIndex == null) {
                property = ScriptableObject.getProperty(scriptable, lastIndexResult(context));
            } else {
                property = ScriptableObject.getProperty(scriptable, stringIdOrIndex);
            }
        }
        return property == Scriptable.NOT_FOUND ? Undefined.instance : property;
    }

    @Deprecated
    public static Object getObjectProp(Object obj, String str, Context context) {
        return getObjectProp(obj, str, context, getTopCallScope(context));
    }

    public static Object getObjectProp(Object obj, String str, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull == null) {
            throw undefReadError(obj, str);
        }
        return getObjectProp(objectOrNull, str, context);
    }

    public static Object getObjectProp(Scriptable scriptable, String str, Context context) {
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (property != Scriptable.NOT_FOUND) {
            return property;
        }
        if (context.hasFeature(11)) {
            Context.reportWarning(getMessage1("msg.ref.undefined.prop", str));
        }
        return Undefined.instance;
    }

    @Deprecated
    public static Object getObjectPropNoWarn(Object obj, String str, Context context) {
        return getObjectPropNoWarn(obj, str, context, getTopCallScope(context));
    }

    public static Object getObjectPropNoWarn(Object obj, String str, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull == null) {
            throw undefReadError(obj, str);
        }
        Object property = ScriptableObject.getProperty(objectOrNull, str);
        return property == Scriptable.NOT_FOUND ? Undefined.instance : property;
    }

    @Deprecated
    public static Object getObjectIndex(Object obj, double d, Context context) {
        return getObjectIndex(obj, d, context, getTopCallScope(context));
    }

    public static Object getObjectIndex(Object obj, double d, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull == null) {
            throw undefReadError(obj, toString(d));
        }
        int i = (int) d;
        if (i == d) {
            return getObjectIndex(objectOrNull, i, context);
        }
        return getObjectProp(objectOrNull, toString(d), context);
    }

    public static Object getObjectIndex(Scriptable scriptable, int i, Context context) {
        Object property = ScriptableObject.getProperty(scriptable, i);
        return property == Scriptable.NOT_FOUND ? Undefined.instance : property;
    }

    @Deprecated
    public static Object setObjectElem(Object obj, Object obj2, Object obj3, Context context) {
        return setObjectElem(obj, obj2, obj3, context, getTopCallScope(context));
    }

    public static Object setObjectElem(Object obj, Object obj2, Object obj3, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull == null) {
            throw undefWriteError(obj, obj2, obj3);
        }
        return setObjectElem(objectOrNull, obj2, obj3, context);
    }

    public static Object setObjectElem(Scriptable scriptable, Object obj, Object obj2, Context context) {
        if (scriptable instanceof XMLObject) {
            ((XMLObject) scriptable).put(context, obj, obj2);
        } else if (isSymbol(obj)) {
            ScriptableObject.putProperty(scriptable, (Symbol) obj, obj2);
        } else {
            String stringIdOrIndex = toStringIdOrIndex(context, obj);
            if (stringIdOrIndex == null) {
                ScriptableObject.putProperty(scriptable, lastIndexResult(context), obj2);
            } else {
                ScriptableObject.putProperty(scriptable, stringIdOrIndex, obj2);
            }
        }
        return obj2;
    }

    @Deprecated
    public static Object setObjectProp(Object obj, String str, Object obj2, Context context) {
        return setObjectProp(obj, str, obj2, context, getTopCallScope(context));
    }

    public static Object setObjectProp(Object obj, String str, Object obj2, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull == null) {
            throw undefWriteError(obj, str, obj2);
        }
        return setObjectProp(objectOrNull, str, obj2, context);
    }

    public static Object setObjectProp(Scriptable scriptable, String str, Object obj, Context context) {
        ScriptableObject.putProperty(scriptable, str, obj);
        return obj;
    }

    @Deprecated
    public static Object setObjectIndex(Object obj, double d, Object obj2, Context context) {
        return setObjectIndex(obj, d, obj2, context, getTopCallScope(context));
    }

    public static Object setObjectIndex(Object obj, double d, Object obj2, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull == null) {
            throw undefWriteError(obj, String.valueOf(d), obj2);
        }
        int i = (int) d;
        if (i == d) {
            return setObjectIndex(objectOrNull, i, obj2, context);
        }
        return setObjectProp(objectOrNull, toString(d), obj2, context);
    }

    public static Object setObjectIndex(Scriptable scriptable, int i, Object obj, Context context) {
        ScriptableObject.putProperty(scriptable, i, obj);
        return obj;
    }

    public static boolean deleteObjectElem(Scriptable scriptable, Object obj, Context context) {
        if (isSymbol(obj)) {
            ScriptableObject.ensureSymbolScriptable(scriptable).delete((Symbol) obj);
            return !r3.has(r2, scriptable);
        }
        String stringIdOrIndex = toStringIdOrIndex(context, obj);
        if (stringIdOrIndex == null) {
            scriptable.delete(lastIndexResult(context));
            return !scriptable.has(r2, scriptable);
        }
        scriptable.delete(stringIdOrIndex);
        return !scriptable.has(stringIdOrIndex, scriptable);
    }

    public static boolean hasObjectElem(Scriptable scriptable, Object obj, Context context) {
        if (isSymbol(obj)) {
            return ScriptableObject.hasProperty(scriptable, (Symbol) obj);
        }
        String stringIdOrIndex = toStringIdOrIndex(context, obj);
        if (stringIdOrIndex == null) {
            return ScriptableObject.hasProperty(scriptable, lastIndexResult(context));
        }
        return ScriptableObject.hasProperty(scriptable, stringIdOrIndex);
    }

    public static Object refGet(Ref ref, Context context) {
        return ref.get(context);
    }

    @Deprecated
    public static Object refSet(Ref ref, Object obj, Context context) {
        return refSet(ref, obj, context, getTopCallScope(context));
    }

    public static Object refSet(Ref ref, Object obj, Context context, Scriptable scriptable) {
        return ref.set(context, scriptable, obj);
    }

    public static Object refDel(Ref ref, Context context) {
        return wrapBoolean(ref.delete(context));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSpecialProperty(String str) {
        return str.equals("__proto__") || str.equals("__parent__");
    }

    @Deprecated
    public static Ref specialRef(Object obj, String str, Context context) {
        return specialRef(obj, str, context, getTopCallScope(context));
    }

    public static Ref specialRef(Object obj, String str, Context context, Scriptable scriptable) {
        return SpecialRef.createSpecial(context, scriptable, obj, str);
    }

    @Deprecated
    public static Object delete(Object obj, Object obj2, Context context) {
        return delete(obj, obj2, context, false);
    }

    @Deprecated
    public static Object delete(Object obj, Object obj2, Context context, boolean z) {
        return delete(obj, obj2, context, getTopCallScope(context), z);
    }

    public static Object delete(Object obj, Object obj2, Context context, Scriptable scriptable, boolean z) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull != null) {
            return wrapBoolean(deleteObjectElem(objectOrNull, obj2, context));
        }
        if (z) {
            return Boolean.TRUE;
        }
        throw undefDeleteError(obj, obj2);
    }

    public static Object name(Context context, Scriptable scriptable, String str) {
        Scriptable parentScope = scriptable.getParentScope();
        if (parentScope == null) {
            Object obj = topScopeName(context, scriptable, str);
            if (obj != Scriptable.NOT_FOUND) {
                return obj;
            }
            throw notFoundError(scriptable, str);
        }
        return nameOrFunction(context, scriptable, parentScope, str, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0046, code lost:
    
        r6 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0075 A[LOOP:0: B:2:0x0002->B:12:0x0075, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Object nameOrFunction(Context context, Scriptable scriptable, Scriptable scriptable2, String str, boolean z) {
        Object property;
        Object obj;
        Scriptable parentScope;
        Scriptable scriptable3 = null;
        Scriptable scriptable4 = scriptable;
        while (true) {
            if (scriptable4 instanceof NativeWith) {
                scriptable4 = scriptable4.getPrototype();
                if (scriptable4 instanceof XMLObject) {
                    scriptable4 = (XMLObject) scriptable4;
                    if (scriptable4.has(str, scriptable4)) {
                        obj = scriptable4.get(str, scriptable4);
                        break;
                    }
                    if (scriptable3 == null) {
                        scriptable3 = scriptable4;
                    }
                } else {
                    property = ScriptableObject.getProperty(scriptable4, str);
                    if (property != Scriptable.NOT_FOUND) {
                        break;
                    }
                }
                parentScope = scriptable2.getParentScope();
                if (parentScope != null) {
                    obj = topScopeName(context, scriptable2, str);
                    if (obj == Scriptable.NOT_FOUND) {
                        if (scriptable3 == null || z) {
                            throw notFoundError(scriptable2, str);
                        }
                        obj = scriptable3.get(str, scriptable3);
                    }
                    scriptable4 = scriptable2;
                } else {
                    scriptable4 = scriptable2;
                    scriptable2 = parentScope;
                }
            } else {
                if (scriptable4 instanceof NativeCall) {
                    Object obj2 = scriptable4.get(str, scriptable4);
                    if (obj2 != Scriptable.NOT_FOUND) {
                        if (z) {
                            scriptable = ScriptableObject.getTopLevelScope(scriptable2);
                        }
                        scriptable4 = scriptable;
                        obj = obj2;
                    }
                } else {
                    property = ScriptableObject.getProperty(scriptable4, str);
                    if (property != Scriptable.NOT_FOUND) {
                        break;
                    }
                }
                parentScope = scriptable2.getParentScope();
                if (parentScope != null) {
                }
            }
        }
        if (z) {
            if (!(obj instanceof Callable)) {
                throw notFunctionError(obj, str);
            }
            storeScriptable(context, scriptable4);
        }
        return obj;
    }

    private static Object topScopeName(Context context, Scriptable scriptable, String str) {
        if (context.useDynamicScope) {
            scriptable = checkDynamicScope(context.topCallScope, scriptable);
        }
        return ScriptableObject.getProperty(scriptable, str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
    
        r5 = r0;
        r1 = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Scriptable bind(Context context, Scriptable scriptable, String str) {
        Scriptable parentScope = scriptable.getParentScope();
        XMLObject xMLObject = null;
        if (parentScope != null) {
            XMLObject xMLObject2 = null;
            while (true) {
                if (scriptable instanceof NativeWith) {
                    Scriptable prototype = scriptable.getPrototype();
                    if (prototype instanceof XMLObject) {
                        XMLObject xMLObject3 = (XMLObject) prototype;
                        if (xMLObject3.has(context, str)) {
                            return xMLObject3;
                        }
                        if (xMLObject2 == null) {
                            xMLObject2 = xMLObject3;
                        }
                    } else if (ScriptableObject.hasProperty(prototype, str)) {
                        return prototype;
                    }
                    Scriptable parentScope2 = parentScope.getParentScope();
                    if (parentScope2 == null) {
                        break;
                    }
                    Scriptable scriptable2 = parentScope;
                    parentScope = parentScope2;
                    scriptable = scriptable2;
                } else {
                    while (!ScriptableObject.hasProperty(scriptable, str)) {
                        Scriptable parentScope3 = parentScope.getParentScope();
                        if (parentScope3 != null) {
                            Scriptable scriptable3 = parentScope;
                            parentScope = parentScope3;
                            scriptable = scriptable3;
                        }
                    }
                    return scriptable;
                }
            }
        }
        if (context.useDynamicScope) {
            scriptable = checkDynamicScope(context.topCallScope, scriptable);
        }
        return ScriptableObject.hasProperty(scriptable, str) ? scriptable : xMLObject;
    }

    public static Object setName(Scriptable scriptable, Object obj, Context context, Scriptable scriptable2, String str) {
        if (scriptable != null) {
            ScriptableObject.putProperty(scriptable, str, obj);
        } else {
            if (context.hasFeature(11) || context.hasFeature(8)) {
                Context.reportWarning(getMessage1("msg.assn.create.strict", str));
            }
            Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable2);
            if (context.useDynamicScope) {
                topLevelScope = checkDynamicScope(context.topCallScope, topLevelScope);
            }
            topLevelScope.put(str, topLevelScope, obj);
        }
        return obj;
    }

    public static Object strictSetName(Scriptable scriptable, Object obj, Context context, Scriptable scriptable2, String str) {
        if (scriptable != null) {
            ScriptableObject.putProperty(scriptable, str, obj);
            return obj;
        }
        throw constructError("ReferenceError", "Assignment to undefined \"" + str + "\" in strict mode");
    }

    public static Object setConst(Scriptable scriptable, Object obj, Context context, String str) {
        if (scriptable instanceof XMLObject) {
            scriptable.put(str, scriptable, obj);
        } else {
            ScriptableObject.putConstProperty(scriptable, str, obj);
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class IdEnumeration implements Serializable {
        private static final long serialVersionUID = 1;
        Object currentId;
        boolean enumNumbers;
        int enumType;
        Object[] ids;
        int index;
        Scriptable iterator;
        Scriptable obj;
        ObjToIntMap used;

        private IdEnumeration() {
        }
    }

    public static Scriptable toIterator(Context context, Scriptable scriptable, Scriptable scriptable2, boolean z) {
        if (!ScriptableObject.hasProperty(scriptable2, NativeIterator.ITERATOR_PROPERTY_NAME)) {
            return null;
        }
        Object property = ScriptableObject.getProperty(scriptable2, NativeIterator.ITERATOR_PROPERTY_NAME);
        if (!(property instanceof Callable)) {
            throw typeError0("msg.invalid.iterator");
        }
        Callable callable = (Callable) property;
        Object[] objArr = new Object[1];
        objArr[0] = z ? Boolean.TRUE : Boolean.FALSE;
        Object call = callable.call(context, scriptable, scriptable2, objArr);
        if (!(call instanceof Scriptable)) {
            throw typeError0("msg.iterator.primitive");
        }
        return (Scriptable) call;
    }

    @Deprecated
    public static Object enumInit(Object obj, Context context, boolean z) {
        return enumInit(obj, context, z ? 1 : 0);
    }

    @Deprecated
    public static Object enumInit(Object obj, Context context, int i) {
        return enumInit(obj, context, getTopCallScope(context), i);
    }

    public static Object enumInit(Object obj, Context context, Scriptable scriptable, int i) {
        IdEnumeration idEnumeration = new IdEnumeration();
        idEnumeration.obj = toObjectOrNull(context, obj, scriptable);
        if (i == 6) {
            idEnumeration.enumType = i;
            idEnumeration.iterator = null;
            return enumInitInOrder(context, idEnumeration);
        }
        if (idEnumeration.obj == null) {
            return idEnumeration;
        }
        idEnumeration.enumType = i;
        idEnumeration.iterator = null;
        if (i != 3 && i != 4 && i != 5) {
            idEnumeration.iterator = toIterator(context, idEnumeration.obj.getParentScope(), idEnumeration.obj, i == 0);
        }
        if (idEnumeration.iterator == null) {
            enumChangeObject(idEnumeration);
        }
        return idEnumeration;
    }

    private static Object enumInitInOrder(Context context, IdEnumeration idEnumeration) {
        if (!(idEnumeration.obj instanceof ScriptableObject)) {
            throw typeError1("msg.not.iterable", toString(idEnumeration.obj));
        }
        ScriptableObject scriptableObject = (ScriptableObject) idEnumeration.obj;
        if (!ScriptableObject.hasProperty(scriptableObject, SymbolKey.ITERATOR)) {
            throw typeError1("msg.not.iterable", toString(idEnumeration.obj));
        }
        Object property = ScriptableObject.getProperty(scriptableObject, SymbolKey.ITERATOR);
        if (!(property instanceof Callable)) {
            throw typeError1("msg.not.iterable", toString(idEnumeration.obj));
        }
        Object call = ((Callable) property).call(context, idEnumeration.obj.getParentScope(), idEnumeration.obj, new Object[0]);
        if (!(call instanceof Scriptable)) {
            throw typeError1("msg.not.iterable", toString(idEnumeration.obj));
        }
        idEnumeration.iterator = (Scriptable) call;
        return idEnumeration;
    }

    public static void setEnumNumbers(Object obj, boolean z) {
        ((IdEnumeration) obj).enumNumbers = z;
    }

    public static Boolean enumNext(Object obj) {
        IdEnumeration idEnumeration = (IdEnumeration) obj;
        if (idEnumeration.iterator != null) {
            if (idEnumeration.enumType == 6) {
                return enumNextInOrder(idEnumeration);
            }
            Object property = ScriptableObject.getProperty(idEnumeration.iterator, ES6Iterator.NEXT_METHOD);
            if (!(property instanceof Callable)) {
                return Boolean.FALSE;
            }
            try {
                idEnumeration.currentId = ((Callable) property).call(Context.getContext(), idEnumeration.iterator.getParentScope(), idEnumeration.iterator, emptyArgs);
                return Boolean.TRUE;
            } catch (JavaScriptException e) {
                if (e.getValue() instanceof NativeIterator.StopIteration) {
                    return Boolean.FALSE;
                }
                throw e;
            }
        }
        while (idEnumeration.obj != null) {
            if (idEnumeration.index == idEnumeration.ids.length) {
                idEnumeration.obj = idEnumeration.obj.getPrototype();
                enumChangeObject(idEnumeration);
            } else {
                Object[] objArr = idEnumeration.ids;
                int i = idEnumeration.index;
                idEnumeration.index = i + 1;
                Object obj2 = objArr[i];
                if (idEnumeration.used == null || !idEnumeration.used.has(obj2)) {
                    if (obj2 instanceof Symbol) {
                        continue;
                    } else if (obj2 instanceof String) {
                        String str = (String) obj2;
                        if (idEnumeration.obj.has(str, idEnumeration.obj)) {
                            idEnumeration.currentId = str;
                            return Boolean.TRUE;
                        }
                    } else {
                        int intValue = ((Number) obj2).intValue();
                        if (idEnumeration.obj.has(intValue, idEnumeration.obj)) {
                            idEnumeration.currentId = idEnumeration.enumNumbers ? Integer.valueOf(intValue) : String.valueOf(intValue);
                            return Boolean.TRUE;
                        }
                    }
                }
            }
        }
        return Boolean.FALSE;
    }

    private static Boolean enumNextInOrder(IdEnumeration idEnumeration) {
        Object property = ScriptableObject.getProperty(idEnumeration.iterator, ES6Iterator.NEXT_METHOD);
        if (!(property instanceof Callable)) {
            throw notFunctionError(idEnumeration.iterator, ES6Iterator.NEXT_METHOD);
        }
        Context context = Context.getContext();
        Scriptable parentScope = idEnumeration.iterator.getParentScope();
        Scriptable object = toObject(context, parentScope, ((Callable) property).call(context, parentScope, idEnumeration.iterator, emptyArgs));
        Object property2 = ScriptableObject.getProperty(object, ES6Iterator.DONE_PROPERTY);
        if (property2 != ScriptableObject.NOT_FOUND && toBoolean(property2)) {
            return Boolean.FALSE;
        }
        idEnumeration.currentId = ScriptableObject.getProperty(object, ES6Iterator.VALUE_PROPERTY);
        return Boolean.TRUE;
    }

    public static Object enumId(Object obj, Context context) {
        IdEnumeration idEnumeration = (IdEnumeration) obj;
        if (idEnumeration.iterator != null) {
            return idEnumeration.currentId;
        }
        int i = idEnumeration.enumType;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                throw Kit.codeBug();
                            }
                        }
                    }
                }
                return context.newArray(ScriptableObject.getTopLevelScope(idEnumeration.obj), new Object[]{idEnumeration.currentId, enumValue(obj, context)});
            }
            return enumValue(obj, context);
        }
        return idEnumeration.currentId;
    }

    public static Object enumValue(Object obj, Context context) {
        IdEnumeration idEnumeration = (IdEnumeration) obj;
        if (isSymbol(idEnumeration.currentId)) {
            return ScriptableObject.ensureSymbolScriptable(idEnumeration.obj).get((Symbol) idEnumeration.currentId, idEnumeration.obj);
        }
        String stringIdOrIndex = toStringIdOrIndex(context, idEnumeration.currentId);
        if (stringIdOrIndex == null) {
            return idEnumeration.obj.get(lastIndexResult(context), idEnumeration.obj);
        }
        return idEnumeration.obj.get(stringIdOrIndex, idEnumeration.obj);
    }

    private static void enumChangeObject(IdEnumeration idEnumeration) {
        Object[] objArr = null;
        while (idEnumeration.obj != null) {
            objArr = idEnumeration.obj.getIds();
            if (objArr.length != 0) {
                break;
            } else {
                idEnumeration.obj = idEnumeration.obj.getPrototype();
            }
        }
        if (idEnumeration.obj != null && idEnumeration.ids != null) {
            Object[] objArr2 = idEnumeration.ids;
            int length = objArr2.length;
            if (idEnumeration.used == null) {
                idEnumeration.used = new ObjToIntMap(length);
            }
            for (int i = 0; i != length; i++) {
                idEnumeration.used.intern(objArr2[i]);
            }
        }
        idEnumeration.ids = objArr;
        idEnumeration.index = 0;
    }

    public static Callable getNameFunctionAndThis(String str, Context context, Scriptable scriptable) {
        Scriptable parentScope = scriptable.getParentScope();
        if (parentScope == null) {
            Object obj = topScopeName(context, scriptable, str);
            if (!(obj instanceof Callable)) {
                if (obj == Scriptable.NOT_FOUND) {
                    throw notFoundError(scriptable, str);
                }
                throw notFunctionError(obj, str);
            }
            storeScriptable(context, scriptable);
            return (Callable) obj;
        }
        return (Callable) nameOrFunction(context, scriptable, parentScope, str, true);
    }

    @Deprecated
    public static Callable getElemFunctionAndThis(Object obj, Object obj2, Context context) {
        return getElemFunctionAndThis(obj, obj2, context, getTopCallScope(context));
    }

    public static Callable getElemFunctionAndThis(Object obj, Object obj2, Context context, Scriptable scriptable) {
        Scriptable objectOrNull;
        Object property;
        if (isSymbol(obj2)) {
            objectOrNull = toObjectOrNull(context, obj, scriptable);
            if (objectOrNull == null) {
                throw undefCallError(obj, String.valueOf(obj2));
            }
            property = ScriptableObject.getProperty(objectOrNull, (Symbol) obj2);
        } else {
            String stringIdOrIndex = toStringIdOrIndex(context, obj2);
            if (stringIdOrIndex != null) {
                return getPropFunctionAndThis(obj, stringIdOrIndex, context, scriptable);
            }
            int lastIndexResult = lastIndexResult(context);
            objectOrNull = toObjectOrNull(context, obj, scriptable);
            if (objectOrNull == null) {
                throw undefCallError(obj, String.valueOf(obj2));
            }
            property = ScriptableObject.getProperty(objectOrNull, lastIndexResult);
        }
        if (!(property instanceof Callable)) {
            throw notFunctionError(property, obj2);
        }
        storeScriptable(context, objectOrNull);
        return (Callable) property;
    }

    @Deprecated
    public static Callable getPropFunctionAndThis(Object obj, String str, Context context) {
        return getPropFunctionAndThis(obj, str, context, getTopCallScope(context));
    }

    public static Callable getPropFunctionAndThis(Object obj, String str, Context context, Scriptable scriptable) {
        return getPropFunctionAndThisHelper(obj, str, context, toObjectOrNull(context, obj, scriptable));
    }

    private static Callable getPropFunctionAndThisHelper(Object obj, String str, Context context, Scriptable scriptable) {
        if (scriptable == null) {
            throw undefCallError(obj, str);
        }
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (!(property instanceof Callable)) {
            Object property2 = ScriptableObject.getProperty(scriptable, "__noSuchMethod__");
            if (property2 instanceof Callable) {
                property = new NoSuchMethodShim((Callable) property2, str);
            }
        }
        if (!(property instanceof Callable)) {
            throw notFunctionError(scriptable, property, str);
        }
        storeScriptable(context, scriptable);
        return (Callable) property;
    }

    public static Callable getValueFunctionAndThis(Object obj, Context context) {
        if (!(obj instanceof Callable)) {
            throw notFunctionError(obj);
        }
        Callable callable = (Callable) obj;
        Scriptable parentScope = callable instanceof Scriptable ? ((Scriptable) callable).getParentScope() : null;
        if (parentScope == null) {
            if (context.topCallScope == null) {
                throw new IllegalStateException();
            }
            parentScope = context.topCallScope;
        }
        if (parentScope.getParentScope() != null && !(parentScope instanceof NativeWith) && (parentScope instanceof NativeCall)) {
            parentScope = ScriptableObject.getTopLevelScope(parentScope);
        }
        storeScriptable(context, parentScope);
        return callable;
    }

    public static Ref callRef(Callable callable, Scriptable scriptable, Object[] objArr, Context context) {
        if (callable instanceof RefCallable) {
            RefCallable refCallable = (RefCallable) callable;
            Ref refCall = refCallable.refCall(context, scriptable, objArr);
            if (refCall != null) {
                return refCall;
            }
            throw new IllegalStateException(refCallable.getClass().getName() + ".refCall() returned null");
        }
        throw constructError("ReferenceError", getMessage1("msg.no.ref.from.function", toString(callable)));
    }

    public static Scriptable newObject(Object obj, Context context, Scriptable scriptable, Object[] objArr) {
        if (!(obj instanceof Function)) {
            throw notFunctionError(obj);
        }
        return ((Function) obj).construct(context, scriptable, objArr);
    }

    public static Object callSpecial(Context context, Callable callable, Scriptable scriptable, Object[] objArr, Scriptable scriptable2, Scriptable scriptable3, int i, String str, int i2) {
        if (i == 1) {
            if (scriptable.getParentScope() == null && NativeGlobal.isEvalFunction(callable)) {
                return evalSpecial(context, scriptable2, scriptable3, objArr, str, i2);
            }
        } else if (i == 2) {
            if (NativeWith.isWithFunction(callable)) {
                throw Context.reportRuntimeError1("msg.only.from.new", "With");
            }
        } else {
            throw Kit.codeBug();
        }
        return callable.call(context, scriptable2, scriptable, objArr);
    }

    public static Object newSpecial(Context context, Object obj, Object[] objArr, Scriptable scriptable, int i) {
        if (i == 1) {
            if (NativeGlobal.isEvalFunction(obj)) {
                throw typeError1("msg.not.ctor", "eval");
            }
        } else if (i == 2) {
            if (NativeWith.isWithFunction(obj)) {
                return NativeWith.newWithSpecial(context, scriptable, objArr);
            }
        } else {
            throw Kit.codeBug();
        }
        return newObject(obj, context, scriptable, objArr);
    }

    public static Object applyOrCall(boolean z, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable scriptable3;
        Object[] objArr2;
        int length = objArr.length;
        Callable callable = getCallable(scriptable2);
        if (length == 0) {
            scriptable3 = null;
        } else if (context.hasFeature(15)) {
            scriptable3 = toObjectOrNull(context, objArr[0], scriptable);
        } else {
            scriptable3 = objArr[0] == Undefined.instance ? Undefined.SCRIPTABLE_UNDEFINED : toObjectOrNull(context, objArr[0], scriptable);
        }
        if (scriptable3 == null && context.hasFeature(15)) {
            scriptable3 = getTopCallScope(context);
        }
        if (z) {
            if (length <= 1) {
                objArr2 = emptyArgs;
            } else {
                objArr2 = getApplyArguments(context, objArr[1]);
            }
        } else if (length <= 1) {
            objArr2 = emptyArgs;
        } else {
            int i = length - 1;
            objArr2 = new Object[i];
            System.arraycopy(objArr, 1, objArr2, 0, i);
        }
        return callable.call(context, scriptable, scriptable3, objArr2);
    }

    private static boolean isArrayLike(Scriptable scriptable) {
        return scriptable != null && ((scriptable instanceof NativeArray) || (scriptable instanceof Arguments) || ScriptableObject.hasProperty(scriptable, Name.LENGTH));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] getApplyArguments(Context context, Object obj) {
        if (obj == null || obj == Undefined.instance) {
            return emptyArgs;
        }
        if (obj instanceof Scriptable) {
            Scriptable scriptable = (Scriptable) obj;
            if (isArrayLike(scriptable)) {
                return context.getElements(scriptable);
            }
        }
        if (obj instanceof ScriptableObject) {
            return emptyArgs;
        }
        throw typeError0("msg.arg.isnt.array");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Callable getCallable(Scriptable scriptable) {
        if (scriptable instanceof Callable) {
            return (Callable) scriptable;
        }
        Object defaultValue = scriptable.getDefaultValue(FunctionClass);
        if (!(defaultValue instanceof Callable)) {
            throw notFunctionError(defaultValue, scriptable);
        }
        return (Callable) defaultValue;
    }

    public static Object evalSpecial(Context context, Scriptable scriptable, Object obj, Object[] objArr, String str, int i) {
        if (objArr.length < 1) {
            return Undefined.instance;
        }
        Object obj2 = objArr[0];
        if (!(obj2 instanceof CharSequence)) {
            if (context.hasFeature(11) || context.hasFeature(9)) {
                throw Context.reportRuntimeError0("msg.eval.nonstring.strict");
            }
            Context.reportWarning(getMessage0("msg.eval.nonstring"));
            return obj2;
        }
        if (str == null) {
            int[] iArr = new int[1];
            String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
            if (sourcePositionFromStack != null) {
                i = iArr[0];
                str = sourcePositionFromStack;
            } else {
                str = "";
            }
        }
        String makeUrlForGeneratedScript = makeUrlForGeneratedScript(true, str, i);
        ErrorReporter forEval = DefaultErrorReporter.forEval(context.getErrorReporter());
        Evaluator createInterpreter = Context.createInterpreter();
        if (createInterpreter == null) {
            throw new JavaScriptException("Interpreter not present", str, i);
        }
        Script compileString = context.compileString(obj2.toString(), createInterpreter, forEval, makeUrlForGeneratedScript, 1, null);
        createInterpreter.setEvalScriptFlag(compileString);
        return ((Callable) compileString).call(context, scriptable, (Scriptable) obj, emptyArgs);
    }

    public static String typeof(Object obj) {
        if (obj == null) {
            return "object";
        }
        if (obj == Undefined.instance) {
            return "undefined";
        }
        if (obj instanceof ScriptableObject) {
            return ((ScriptableObject) obj).getTypeOf();
        }
        if (obj instanceof Scriptable) {
            return obj instanceof Callable ? "function" : "object";
        }
        if (obj instanceof CharSequence) {
            return "string";
        }
        if (obj instanceof Number) {
            return "number";
        }
        if (obj instanceof Boolean) {
            return "boolean";
        }
        throw errorWithClassName("msg.invalid.type", obj);
    }

    public static String typeofName(Scriptable scriptable, String str) {
        Context context = Context.getContext();
        Scriptable bind = bind(context, scriptable, str);
        return bind == null ? "undefined" : typeof(getObjectProp(bind, str, context));
    }

    public static Object add(Object obj, Object obj2, Context context) {
        Object addValues;
        Object addValues2;
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            return wrapNumber(((Number) obj).doubleValue() + ((Number) obj2).doubleValue());
        }
        if ((obj instanceof XMLObject) && (addValues2 = ((XMLObject) obj).addValues(context, true, obj2)) != Scriptable.NOT_FOUND) {
            return addValues2;
        }
        if ((obj2 instanceof XMLObject) && (addValues = ((XMLObject) obj2).addValues(context, false, obj)) != Scriptable.NOT_FOUND) {
            return addValues;
        }
        if ((obj instanceof Symbol) || (obj2 instanceof Symbol)) {
            throw typeError0("msg.not.a.number");
        }
        if (obj instanceof Scriptable) {
            obj = ((Scriptable) obj).getDefaultValue(null);
        }
        if (obj2 instanceof Scriptable) {
            obj2 = ((Scriptable) obj2).getDefaultValue(null);
        }
        if (!(obj instanceof CharSequence) && !(obj2 instanceof CharSequence)) {
            if ((obj instanceof Number) && (obj2 instanceof Number)) {
                return wrapNumber(((Number) obj).doubleValue() + ((Number) obj2).doubleValue());
            }
            return wrapNumber(toNumber(obj) + toNumber(obj2));
        }
        return new ConsString(toCharSequence(obj), toCharSequence(obj2));
    }

    public static CharSequence add(CharSequence charSequence, Object obj) {
        return new ConsString(charSequence, toCharSequence(obj));
    }

    public static CharSequence add(Object obj, CharSequence charSequence) {
        return new ConsString(toCharSequence(obj), charSequence);
    }

    @Deprecated
    public static Object nameIncrDecr(Scriptable scriptable, String str, int i) {
        return nameIncrDecr(scriptable, str, Context.getContext(), i);
    }

    public static Object nameIncrDecr(Scriptable scriptable, String str, Context context, int i) {
        do {
            if (context.useDynamicScope && scriptable.getParentScope() == null) {
                scriptable = checkDynamicScope(context.topCallScope, scriptable);
            }
            Scriptable scriptable2 = scriptable;
            do {
                if ((scriptable2 instanceof NativeWith) && (scriptable2.getPrototype() instanceof XMLObject)) {
                    break;
                }
                Object obj = scriptable2.get(str, scriptable);
                if (obj == Scriptable.NOT_FOUND) {
                    scriptable2 = scriptable2.getPrototype();
                } else {
                    return doScriptableIncrDecr(scriptable2, str, scriptable, obj, i);
                }
            } while (scriptable2 != null);
            scriptable = scriptable.getParentScope();
        } while (scriptable != null);
        throw notFoundError(scriptable, str);
    }

    @Deprecated
    public static Object propIncrDecr(Object obj, String str, Context context, int i) {
        return propIncrDecr(obj, str, context, getTopCallScope(context), i);
    }

    public static Object propIncrDecr(Object obj, String str, Context context, Scriptable scriptable, int i) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull == null) {
            throw undefReadError(obj, str);
        }
        Scriptable scriptable2 = objectOrNull;
        do {
            Object obj2 = scriptable2.get(str, objectOrNull);
            if (obj2 == Scriptable.NOT_FOUND) {
                scriptable2 = scriptable2.getPrototype();
            } else {
                return doScriptableIncrDecr(scriptable2, str, objectOrNull, obj2, i);
            }
        } while (scriptable2 != null);
        objectOrNull.put(str, objectOrNull, NaNobj);
        return NaNobj;
    }

    private static Object doScriptableIncrDecr(Scriptable scriptable, String str, Scriptable scriptable2, Object obj, int i) {
        double number;
        boolean z = (i & 2) != 0;
        if (obj instanceof Number) {
            number = ((Number) obj).doubleValue();
        } else {
            number = toNumber(obj);
            if (z) {
                obj = wrapNumber(number);
            }
        }
        Number wrapNumber = wrapNumber((i & 1) == 0 ? number + 1.0d : number - 1.0d);
        scriptable.put(str, scriptable2, wrapNumber);
        return z ? obj : wrapNumber;
    }

    @Deprecated
    public static Object elemIncrDecr(Object obj, Object obj2, Context context, int i) {
        return elemIncrDecr(obj, obj2, context, getTopCallScope(context), i);
    }

    public static Object elemIncrDecr(Object obj, Object obj2, Context context, Scriptable scriptable, int i) {
        double number;
        Object objectElem = getObjectElem(obj, obj2, context, scriptable);
        boolean z = (i & 2) != 0;
        if (objectElem instanceof Number) {
            number = ((Number) objectElem).doubleValue();
        } else {
            number = toNumber(objectElem);
            if (z) {
                objectElem = wrapNumber(number);
            }
        }
        Number wrapNumber = wrapNumber((i & 1) == 0 ? number + 1.0d : number - 1.0d);
        setObjectElem(obj, obj2, wrapNumber, context, scriptable);
        return z ? objectElem : wrapNumber;
    }

    @Deprecated
    public static Object refIncrDecr(Ref ref, Context context, int i) {
        return refIncrDecr(ref, context, getTopCallScope(context), i);
    }

    public static Object refIncrDecr(Ref ref, Context context, Scriptable scriptable, int i) {
        double number;
        Object obj = ref.get(context);
        boolean z = (i & 2) != 0;
        if (obj instanceof Number) {
            number = ((Number) obj).doubleValue();
        } else {
            number = toNumber(obj);
            if (z) {
                obj = wrapNumber(number);
            }
        }
        Number wrapNumber = wrapNumber((i & 1) == 0 ? number + 1.0d : number - 1.0d);
        ref.set(context, scriptable, wrapNumber);
        return z ? obj : wrapNumber;
    }

    public static Object toPrimitive(Object obj) {
        return toPrimitive(obj, null);
    }

    public static Object toPrimitive(Object obj, Class<?> cls) {
        if (!(obj instanceof Scriptable)) {
            return obj;
        }
        Object defaultValue = ((Scriptable) obj).getDefaultValue(cls);
        if (!(defaultValue instanceof Scriptable) || isSymbol(defaultValue)) {
            return defaultValue;
        }
        throw typeError0("msg.bad.default.value");
    }

    /* renamed from: eq */
    public static boolean m4181eq(Object obj, Object obj2) {
        Object equivalentValues;
        Object equivalentValues2;
        Object equivalentValues3;
        Object equivalentValues4;
        Object equivalentValues5;
        if (obj == null || obj == Undefined.instance) {
            if (obj2 == null || obj2 == Undefined.instance) {
                return true;
            }
            if (!(obj2 instanceof ScriptableObject) || (equivalentValues = ((ScriptableObject) obj2).equivalentValues(obj)) == Scriptable.NOT_FOUND) {
                return false;
            }
            return ((Boolean) equivalentValues).booleanValue();
        }
        if (obj instanceof Number) {
            return eqNumber(((Number) obj).doubleValue(), obj2);
        }
        if (obj == obj2) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return eqString((CharSequence) obj, obj2);
        }
        if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            if (obj2 instanceof Boolean) {
                return booleanValue == ((Boolean) obj2).booleanValue();
            }
            if ((obj2 instanceof ScriptableObject) && (equivalentValues5 = ((ScriptableObject) obj2).equivalentValues(obj)) != Scriptable.NOT_FOUND) {
                return ((Boolean) equivalentValues5).booleanValue();
            }
            return eqNumber(booleanValue ? 1.0d : 0.0d, obj2);
        }
        if (obj instanceof Scriptable) {
            if (obj2 instanceof Scriptable) {
                if ((obj instanceof ScriptableObject) && (equivalentValues4 = ((ScriptableObject) obj).equivalentValues(obj2)) != Scriptable.NOT_FOUND) {
                    return ((Boolean) equivalentValues4).booleanValue();
                }
                if ((obj2 instanceof ScriptableObject) && (equivalentValues3 = ((ScriptableObject) obj2).equivalentValues(obj)) != Scriptable.NOT_FOUND) {
                    return ((Boolean) equivalentValues3).booleanValue();
                }
                if (!(obj instanceof Wrapper) || !(obj2 instanceof Wrapper)) {
                    return false;
                }
                Object unwrap = ((Wrapper) obj).unwrap();
                Object unwrap2 = ((Wrapper) obj2).unwrap();
                if (unwrap != unwrap2) {
                    return isPrimitive(unwrap) && isPrimitive(unwrap2) && m4181eq(unwrap, unwrap2);
                }
                return true;
            }
            if (obj2 instanceof Boolean) {
                if ((obj instanceof ScriptableObject) && (equivalentValues2 = ((ScriptableObject) obj).equivalentValues(obj2)) != Scriptable.NOT_FOUND) {
                    return ((Boolean) equivalentValues2).booleanValue();
                }
                return eqNumber(((Boolean) obj2).booleanValue() ? 1.0d : 0.0d, obj);
            }
            if (obj2 instanceof Number) {
                return eqNumber(((Number) obj2).doubleValue(), obj);
            }
            if (obj2 instanceof CharSequence) {
                return eqString((CharSequence) obj2, obj);
            }
            return false;
        }
        warnAboutNonJSObject(obj);
        return obj == obj2;
    }

    public static boolean same(Object obj, Object obj2) {
        if (!typeof(obj).equals(typeof(obj2))) {
            return false;
        }
        if (obj instanceof Number) {
            if (isNaN(obj) && isNaN(obj2)) {
                return true;
            }
            return obj.equals(obj2);
        }
        return m4181eq(obj, obj2);
    }

    public static boolean isNaN(Object obj) {
        if (obj == NaNobj) {
            return true;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            return d.doubleValue() == NaN || Double.isNaN(d.doubleValue());
        }
        if (!(obj instanceof Float)) {
            return false;
        }
        Float f = (Float) obj;
        return ((double) f.floatValue()) == NaN || Float.isNaN(f.floatValue());
    }

    public static boolean isPrimitive(Object obj) {
        return obj == null || obj == Undefined.instance || (obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006b, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean eqNumber(double d, Object obj) {
        while (true) {
            if (obj != null && obj != Undefined.instance) {
                if (!(obj instanceof Number)) {
                    if (!(obj instanceof CharSequence)) {
                        if (!(obj instanceof Boolean)) {
                            if (!isSymbol(obj)) {
                                if (obj instanceof Scriptable) {
                                    if (obj instanceof ScriptableObject) {
                                        Object equivalentValues = ((ScriptableObject) obj).equivalentValues(wrapNumber(d));
                                        if (equivalentValues != Scriptable.NOT_FOUND) {
                                            return ((Boolean) equivalentValues).booleanValue();
                                        }
                                    }
                                    obj = toPrimitive(obj);
                                } else {
                                    warnAboutNonJSObject(obj);
                                    break;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return d == (((Boolean) obj).booleanValue() ? 1.0d : 0.0d);
                        }
                    } else {
                        return d == toNumber(obj);
                    }
                } else {
                    return d == ((Number) obj).doubleValue();
                }
            } else {
                break;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x008f, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean eqString(CharSequence charSequence, Object obj) {
        Object equivalentValues;
        while (true) {
            if (obj != null && obj != Undefined.instance) {
                if (obj instanceof CharSequence) {
                    CharSequence charSequence2 = (CharSequence) obj;
                    return charSequence.length() == charSequence2.length() && charSequence.toString().equals(charSequence2.toString());
                }
                if (!(obj instanceof Number)) {
                    if (!(obj instanceof Boolean)) {
                        if (!isSymbol(obj)) {
                            if (obj instanceof Scriptable) {
                                if ((obj instanceof ScriptableObject) && (equivalentValues = ((ScriptableObject) obj).equivalentValues(charSequence.toString())) != Scriptable.NOT_FOUND) {
                                    return ((Boolean) equivalentValues).booleanValue();
                                }
                                obj = toPrimitive(obj);
                            } else {
                                warnAboutNonJSObject(obj);
                                break;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return toNumber(charSequence.toString()) == (((Boolean) obj).booleanValue() ? 1.0d : 0.0d);
                    }
                } else {
                    return toNumber(charSequence.toString()) == ((Number) obj).doubleValue();
                }
            } else {
                break;
            }
        }
    }

    public static boolean shallowEq(Object obj, Object obj2) {
        if (obj == obj2) {
            if (!(obj instanceof Number)) {
                return true;
            }
            double doubleValue = ((Number) obj).doubleValue();
            return doubleValue == doubleValue;
        }
        if (obj == null || obj == Undefined.instance || obj == Undefined.SCRIPTABLE_UNDEFINED) {
            return (obj == Undefined.instance && obj2 == Undefined.SCRIPTABLE_UNDEFINED) || (obj == Undefined.SCRIPTABLE_UNDEFINED && obj2 == Undefined.instance);
        }
        if (obj instanceof Number) {
            return (obj2 instanceof Number) && ((Number) obj).doubleValue() == ((Number) obj2).doubleValue();
        }
        if (obj instanceof CharSequence) {
            if (obj2 instanceof CharSequence) {
                return obj.toString().equals(obj2.toString());
            }
            return false;
        }
        if (obj instanceof Boolean) {
            if (obj2 instanceof Boolean) {
                return obj.equals(obj2);
            }
            return false;
        }
        if (obj instanceof Scriptable) {
            return (obj instanceof Wrapper) && (obj2 instanceof Wrapper) && ((Wrapper) obj).unwrap() == ((Wrapper) obj2).unwrap();
        }
        warnAboutNonJSObject(obj);
        return obj == obj2;
    }

    public static boolean instanceOf(Object obj, Object obj2, Context context) {
        if (!(obj2 instanceof Scriptable)) {
            throw typeError0("msg.instanceof.not.object");
        }
        if (obj instanceof Scriptable) {
            return ((Scriptable) obj2).hasInstance((Scriptable) obj);
        }
        return false;
    }

    public static boolean jsDelegatesTo(Scriptable scriptable, Scriptable scriptable2) {
        for (Scriptable prototype = scriptable.getPrototype(); prototype != null; prototype = prototype.getPrototype()) {
            if (prototype.equals(scriptable2)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: in */
    public static boolean m4182in(Object obj, Object obj2, Context context) {
        if (!(obj2 instanceof Scriptable)) {
            throw typeError0("msg.in.not.object");
        }
        return hasObjectElem((Scriptable) obj2, obj, context);
    }

    public static boolean cmp_LT(Object obj, Object obj2) {
        double number;
        double number2;
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            number = ((Number) obj).doubleValue();
            number2 = ((Number) obj2).doubleValue();
        } else {
            if ((obj instanceof Symbol) || (obj2 instanceof Symbol)) {
                throw typeError0("msg.compare.symbol");
            }
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(NumberClass);
            }
            if (obj2 instanceof Scriptable) {
                obj2 = ((Scriptable) obj2).getDefaultValue(NumberClass);
            }
            if ((obj instanceof CharSequence) && (obj2 instanceof CharSequence)) {
                return obj.toString().compareTo(obj2.toString()) < 0;
            }
            number = toNumber(obj);
            number2 = toNumber(obj2);
        }
        return number < number2;
    }

    public static boolean cmp_LE(Object obj, Object obj2) {
        double number;
        double number2;
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            number = ((Number) obj).doubleValue();
            number2 = ((Number) obj2).doubleValue();
        } else {
            if ((obj instanceof Symbol) || (obj2 instanceof Symbol)) {
                throw typeError0("msg.compare.symbol");
            }
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(NumberClass);
            }
            if (obj2 instanceof Scriptable) {
                obj2 = ((Scriptable) obj2).getDefaultValue(NumberClass);
            }
            if ((obj instanceof CharSequence) && (obj2 instanceof CharSequence)) {
                return obj.toString().compareTo(obj2.toString()) <= 0;
            }
            number = toNumber(obj);
            number2 = toNumber(obj2);
        }
        return number <= number2;
    }

    public static ScriptableObject getGlobal(Context context) {
        Class<?> classOrNull = Kit.classOrNull("org.mozilla.javascript.tools.shell.Global");
        if (classOrNull != null) {
            try {
                return (ScriptableObject) classOrNull.getConstructor(ContextClass).newInstance(context);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
        return new ImporterTopLevel(context);
    }

    public static boolean hasTopCall(Context context) {
        return context.topCallScope != null;
    }

    public static Scriptable getTopCallScope(Context context) {
        Scriptable scriptable = context.topCallScope;
        if (scriptable != null) {
            return scriptable;
        }
        throw new IllegalStateException();
    }

    public static Object doTopCall(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return doTopCall(callable, context, scriptable, scriptable2, objArr, context.isTopLevelStrict);
    }

    public static Object doTopCall(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, boolean z) {
        if (scriptable == null) {
            throw new IllegalArgumentException();
        }
        if (context.topCallScope != null) {
            throw new IllegalStateException();
        }
        context.topCallScope = ScriptableObject.getTopLevelScope(scriptable);
        context.useDynamicScope = context.hasFeature(7);
        boolean z2 = context.isTopLevelStrict;
        context.isTopLevelStrict = z;
        try {
            Object doTopCall = context.getFactory().doTopCall(callable, context, scriptable, scriptable2, objArr);
            context.topCallScope = null;
            context.cachedXMLLib = null;
            context.isTopLevelStrict = z2;
            if (context.currentActivationCall == null) {
                return doTopCall;
            }
            throw new IllegalStateException();
        } catch (Throwable th) {
            context.topCallScope = null;
            context.cachedXMLLib = null;
            context.isTopLevelStrict = z2;
            if (context.currentActivationCall != null) {
                throw new IllegalStateException();
            }
            throw th;
        }
    }

    static Scriptable checkDynamicScope(Scriptable scriptable, Scriptable scriptable2) {
        if (scriptable == scriptable2) {
            return scriptable;
        }
        Scriptable scriptable3 = scriptable;
        do {
            scriptable3 = scriptable3.getPrototype();
            if (scriptable3 == scriptable2) {
                return scriptable;
            }
        } while (scriptable3 != null);
        return scriptable2;
    }

    public static void addInstructionCount(Context context, int i) {
        context.instructionCount += i;
        if (context.instructionCount > context.instructionThreshold) {
            context.observeInstructionCount(context.instructionCount);
            context.instructionCount = 0;
        }
    }

    public static void initScript(NativeFunction nativeFunction, Scriptable scriptable, Context context, Scriptable scriptable2, boolean z) {
        if (context.topCallScope == null) {
            throw new IllegalStateException();
        }
        int paramAndVarCount = nativeFunction.getParamAndVarCount();
        if (paramAndVarCount == 0) {
            return;
        }
        Scriptable scriptable3 = scriptable2;
        while (scriptable3 instanceof NativeWith) {
            scriptable3 = scriptable3.getParentScope();
        }
        while (true) {
            int i = paramAndVarCount - 1;
            if (paramAndVarCount == 0) {
                return;
            }
            String paramOrVarName = nativeFunction.getParamOrVarName(i);
            boolean paramOrVarConst = nativeFunction.getParamOrVarConst(i);
            if (ScriptableObject.hasProperty(scriptable2, paramOrVarName)) {
                ScriptableObject.redefineProperty(scriptable2, paramOrVarName, paramOrVarConst);
            } else if (paramOrVarConst) {
                ScriptableObject.defineConstProperty(scriptable3, paramOrVarName);
            } else if (!z) {
                ScriptableObject.defineProperty(scriptable3, paramOrVarName, Undefined.instance, 4);
            } else {
                scriptable3.put(paramOrVarName, scriptable3, Undefined.instance);
            }
            paramAndVarCount = i;
        }
    }

    @Deprecated
    public static Scriptable createFunctionActivation(NativeFunction nativeFunction, Scriptable scriptable, Object[] objArr) {
        return createFunctionActivation(nativeFunction, scriptable, objArr, false);
    }

    public static Scriptable createFunctionActivation(NativeFunction nativeFunction, Scriptable scriptable, Object[] objArr, boolean z) {
        return new NativeCall(nativeFunction, scriptable, objArr, false, z);
    }

    public static Scriptable createArrowFunctionActivation(NativeFunction nativeFunction, Scriptable scriptable, Object[] objArr, boolean z) {
        return new NativeCall(nativeFunction, scriptable, objArr, true, z);
    }

    public static void enterActivationFunction(Context context, Scriptable scriptable) {
        if (context.topCallScope == null) {
            throw new IllegalStateException();
        }
        NativeCall nativeCall = (NativeCall) scriptable;
        nativeCall.parentActivationCall = context.currentActivationCall;
        context.currentActivationCall = nativeCall;
        nativeCall.defineAttributesForArguments();
    }

    public static void exitActivationFunction(Context context) {
        NativeCall nativeCall = context.currentActivationCall;
        context.currentActivationCall = nativeCall.parentActivationCall;
        nativeCall.parentActivationCall = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NativeCall findFunctionActivation(Context context, Function function) {
        for (NativeCall nativeCall = context.currentActivationCall; nativeCall != null; nativeCall = nativeCall.parentActivationCall) {
            if (nativeCall.function == function) {
                return nativeCall;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Scriptable newCatchScope(Throwable th, Scriptable scriptable, String str, Context context, Scriptable scriptable2) {
        TopLevel.NativeErrors nativeErrors;
        String th2;
        String str2;
        Throwable th3;
        RhinoException rhinoException;
        String sourceName;
        Scriptable newNativeError;
        Object obj;
        RhinoException rhinoException2;
        boolean z = false;
        if (th instanceof JavaScriptException) {
            obj = ((JavaScriptException) th).getValue();
        } else {
            if (scriptable != null) {
                obj = ((NativeObject) scriptable).getAssociatedValue(th);
                if (obj == null) {
                    Kit.codeBug();
                }
            } else {
                if (th instanceof EcmaError) {
                    EcmaError ecmaError = (EcmaError) th;
                    nativeErrors = TopLevel.NativeErrors.valueOf(ecmaError.getName());
                    th2 = ecmaError.getErrorMessage();
                    rhinoException2 = ecmaError;
                } else if (th instanceof WrappedException) {
                    WrappedException wrappedException = (WrappedException) th;
                    Throwable wrappedException2 = wrappedException.getWrappedException();
                    TopLevel.NativeErrors nativeErrors2 = TopLevel.NativeErrors.JavaException;
                    str2 = wrappedException2.getClass().getName() + ": " + wrappedException2.getMessage();
                    th3 = wrappedException2;
                    nativeErrors = nativeErrors2;
                    rhinoException = wrappedException;
                    sourceName = rhinoException.sourceName();
                    if (sourceName == null) {
                        sourceName = "";
                    }
                    int lineNumber = rhinoException.lineNumber();
                    newNativeError = newNativeError(context, scriptable2, nativeErrors, lineNumber <= 0 ? new Object[]{str2, sourceName, Integer.valueOf(lineNumber)} : new Object[]{str2, sourceName});
                    if (newNativeError instanceof NativeError) {
                        ((NativeError) newNativeError).setStackProvider(rhinoException);
                    }
                    if (th3 != null && isVisible(context, th3)) {
                        ScriptableObject.defineProperty(newNativeError, "javaException", context.getWrapFactory().wrap(context, scriptable2, th3, null), 7);
                    }
                    if (isVisible(context, rhinoException)) {
                        ScriptableObject.defineProperty(newNativeError, "rhinoException", context.getWrapFactory().wrap(context, scriptable2, rhinoException, null), 7);
                    }
                    obj = newNativeError;
                } else if (th instanceof EvaluatorException) {
                    RhinoException rhinoException3 = (EvaluatorException) th;
                    nativeErrors = TopLevel.NativeErrors.InternalError;
                    th2 = rhinoException3.getMessage();
                    rhinoException2 = rhinoException3;
                } else if (context.hasFeature(13)) {
                    RhinoException wrappedException3 = new WrappedException(th);
                    nativeErrors = TopLevel.NativeErrors.JavaException;
                    th2 = th.toString();
                    rhinoException2 = wrappedException3;
                } else {
                    throw Kit.codeBug();
                }
                str2 = th2;
                th3 = null;
                rhinoException = rhinoException2;
                sourceName = rhinoException.sourceName();
                if (sourceName == null) {
                }
                int lineNumber2 = rhinoException.lineNumber();
                newNativeError = newNativeError(context, scriptable2, nativeErrors, lineNumber2 <= 0 ? new Object[]{str2, sourceName, Integer.valueOf(lineNumber2)} : new Object[]{str2, sourceName});
                if (newNativeError instanceof NativeError) {
                }
                if (th3 != null) {
                    ScriptableObject.defineProperty(newNativeError, "javaException", context.getWrapFactory().wrap(context, scriptable2, th3, null), 7);
                }
                if (isVisible(context, rhinoException)) {
                }
                obj = newNativeError;
            }
            z = true;
        }
        NativeObject nativeObject = new NativeObject();
        nativeObject.defineProperty(str, obj, 4);
        if (isVisible(context, th)) {
            nativeObject.defineProperty("__exception__", Context.javaToJS(th, scriptable2), 6);
        }
        if (z) {
            nativeObject.associateValue(th, obj);
        }
        return nativeObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Scriptable wrapException(Throwable th, Scriptable scriptable, Context context) {
        String str;
        Throwable th2;
        String th3;
        RhinoException rhinoException;
        String str2;
        String sourceName;
        Scriptable newObject;
        RhinoException rhinoException2;
        if (th instanceof EcmaError) {
            EcmaError ecmaError = (EcmaError) th;
            str2 = ecmaError.getName();
            th3 = ecmaError.getErrorMessage();
            rhinoException2 = ecmaError;
        } else {
            if (th instanceof WrappedException) {
                WrappedException wrappedException = (WrappedException) th;
                Throwable wrappedException2 = wrappedException.getWrappedException();
                th2 = wrappedException2;
                th3 = wrappedException2.getClass().getName() + ": " + wrappedException2.getMessage();
                str = "JavaException";
                rhinoException = wrappedException;
            } else if (th instanceof EvaluatorException) {
                RhinoException rhinoException3 = (EvaluatorException) th;
                th3 = rhinoException3.getMessage();
                str2 = "InternalError";
                rhinoException2 = rhinoException3;
            } else if (context.hasFeature(13)) {
                RhinoException wrappedException3 = new WrappedException(th);
                str = "JavaException";
                th2 = null;
                th3 = th.toString();
                rhinoException = wrappedException3;
            } else {
                throw Kit.codeBug();
            }
            sourceName = rhinoException.sourceName();
            if (sourceName == null) {
                sourceName = "";
            }
            int lineNumber = rhinoException.lineNumber();
            newObject = context.newObject(scriptable, str, lineNumber <= 0 ? new Object[]{th3, sourceName, Integer.valueOf(lineNumber)} : new Object[]{th3, sourceName});
            ScriptableObject.putProperty(newObject, "name", str);
            if (newObject instanceof NativeError) {
                ((NativeError) newObject).setStackProvider(rhinoException);
            }
            if (th2 != null && isVisible(context, th2)) {
                ScriptableObject.defineProperty(newObject, "javaException", context.getWrapFactory().wrap(context, scriptable, th2, null), 7);
            }
            if (isVisible(context, rhinoException)) {
                ScriptableObject.defineProperty(newObject, "rhinoException", context.getWrapFactory().wrap(context, scriptable, rhinoException, null), 7);
            }
            return newObject;
        }
        str = str2;
        th2 = null;
        rhinoException = rhinoException2;
        sourceName = rhinoException.sourceName();
        if (sourceName == null) {
        }
        int lineNumber2 = rhinoException.lineNumber();
        newObject = context.newObject(scriptable, str, lineNumber2 <= 0 ? new Object[]{th3, sourceName, Integer.valueOf(lineNumber2)} : new Object[]{th3, sourceName});
        ScriptableObject.putProperty(newObject, "name", str);
        if (newObject instanceof NativeError) {
        }
        if (th2 != null) {
            ScriptableObject.defineProperty(newObject, "javaException", context.getWrapFactory().wrap(context, scriptable, th2, null), 7);
        }
        if (isVisible(context, rhinoException)) {
        }
        return newObject;
    }

    private static boolean isVisible(Context context, Object obj) {
        ClassShutter classShutter = context.getClassShutter();
        return classShutter == null || classShutter.visibleToScripts(obj.getClass().getName());
    }

    public static Scriptable enterWith(Object obj, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull == null) {
            throw typeError1("msg.undef.with", toString(obj));
        }
        if (objectOrNull instanceof XMLObject) {
            return ((XMLObject) objectOrNull).enterWith(scriptable);
        }
        return new NativeWith(scriptable, objectOrNull);
    }

    public static Scriptable leaveWith(Scriptable scriptable) {
        return ((NativeWith) scriptable).getParentScope();
    }

    public static Scriptable enterDotQuery(Object obj, Scriptable scriptable) {
        if (!(obj instanceof XMLObject)) {
            throw notXmlError(obj);
        }
        return ((XMLObject) obj).enterDotQuery(scriptable);
    }

    public static Object updateDotQuery(boolean z, Scriptable scriptable) {
        return ((NativeWith) scriptable).updateDotQuery(z);
    }

    public static Scriptable leaveDotQuery(Scriptable scriptable) {
        return ((NativeWith) scriptable).getParentScope();
    }

    public static void setFunctionProtoAndParent(BaseFunction baseFunction, Scriptable scriptable) {
        baseFunction.setParentScope(scriptable);
        baseFunction.setPrototype(ScriptableObject.getFunctionPrototype(scriptable));
    }

    public static void setObjectProtoAndParent(ScriptableObject scriptableObject, Scriptable scriptable) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        scriptableObject.setParentScope(topLevelScope);
        scriptableObject.setPrototype(ScriptableObject.getClassPrototype(topLevelScope, scriptableObject.getClassName()));
    }

    public static void setBuiltinProtoAndParent(ScriptableObject scriptableObject, Scriptable scriptable, TopLevel.Builtins builtins) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        scriptableObject.setParentScope(topLevelScope);
        scriptableObject.setPrototype(TopLevel.getBuiltinPrototype(topLevelScope, builtins));
    }

    public static void initFunction(Context context, Scriptable scriptable, NativeFunction nativeFunction, int i, boolean z) {
        if (i == 1) {
            String functionName = nativeFunction.getFunctionName();
            if (functionName == null || functionName.length() == 0) {
                return;
            }
            if (!z) {
                ScriptableObject.defineProperty(scriptable, functionName, nativeFunction, 4);
                return;
            } else {
                scriptable.put(functionName, scriptable, nativeFunction);
                return;
            }
        }
        if (i == 3) {
            String functionName2 = nativeFunction.getFunctionName();
            if (functionName2 == null || functionName2.length() == 0) {
                return;
            }
            while (scriptable instanceof NativeWith) {
                scriptable = scriptable.getParentScope();
            }
            scriptable.put(functionName2, scriptable, nativeFunction);
            return;
        }
        throw Kit.codeBug();
    }

    public static Scriptable newArrayLiteral(Object[] objArr, int[] iArr, Context context, Scriptable scriptable) {
        int length = objArr.length;
        int i = 0;
        int length2 = iArr != null ? iArr.length : 0;
        int i2 = length + length2;
        if (i2 > 1 && length2 * 2 < i2) {
            if (length2 != 0) {
                Object[] objArr2 = new Object[i2];
                int i3 = 0;
                int i4 = 0;
                while (i != i2) {
                    if (i3 != length2 && iArr[i3] == i) {
                        objArr2[i] = Scriptable.NOT_FOUND;
                        i3++;
                    } else {
                        objArr2[i] = objArr[i4];
                        i4++;
                    }
                    i++;
                }
                objArr = objArr2;
            }
            return context.newArray(scriptable, objArr);
        }
        Scriptable newArray = context.newArray(scriptable, i2);
        int i5 = 0;
        int i6 = 0;
        while (i != i2) {
            if (i5 == length2 || iArr[i5] != i) {
                newArray.put(i, newArray, objArr[i6]);
                i6++;
            } else {
                i5++;
            }
            i++;
        }
        return newArray;
    }

    @Deprecated
    public static Scriptable newObjectLiteral(Object[] objArr, Object[] objArr2, Context context, Scriptable scriptable) {
        return newObjectLiteral(objArr, objArr2, null, context, scriptable);
    }

    public static Scriptable newObjectLiteral(Object[] objArr, Object[] objArr2, int[] iArr, Context context, Scriptable scriptable) {
        Scriptable newObject = context.newObject(scriptable);
        int length = objArr.length;
        for (int i = 0; i != length; i++) {
            Object obj = objArr[i];
            int i2 = iArr == null ? 0 : iArr[i];
            Object obj2 = objArr2[i];
            if (!(obj instanceof String)) {
                newObject.put(((Integer) obj).intValue(), newObject, obj2);
            } else if (i2 == 0) {
                String str = (String) obj;
                if (isSpecialProperty(str)) {
                    specialRef(newObject, str, context, scriptable).set(context, scriptable, obj2);
                } else {
                    newObject.put(str, newObject, obj2);
                }
            } else {
                ((ScriptableObject) newObject).setGetterOrSetter((String) obj, 0, (Callable) obj2, i2 == 1);
            }
        }
        return newObject;
    }

    public static boolean isArrayObject(Object obj) {
        return (obj instanceof NativeArray) || (obj instanceof Arguments);
    }

    public static Object[] getArrayElements(Scriptable scriptable) {
        long lengthProperty = NativeArray.getLengthProperty(Context.getContext(), scriptable);
        if (lengthProperty > 2147483647L) {
            throw new IllegalArgumentException();
        }
        int i = (int) lengthProperty;
        if (i == 0) {
            return emptyArgs;
        }
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            Object property = ScriptableObject.getProperty(scriptable, i2);
            if (property == Scriptable.NOT_FOUND) {
                property = Undefined.instance;
            }
            objArr[i2] = property;
        }
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkDeprecated(Context context, String str) {
        int languageVersion = context.getLanguageVersion();
        if (languageVersion >= 140 || languageVersion == 0) {
            String message1 = getMessage1("msg.deprec.ctor", str);
            if (languageVersion == 0) {
                Context.reportWarning(message1);
                return;
            }
            throw Context.reportRuntimeError(message1);
        }
    }

    public static String getMessage0(String str) {
        return getMessage(str, null);
    }

    public static String getMessage1(String str, Object obj) {
        return getMessage(str, new Object[]{obj});
    }

    public static String getMessage2(String str, Object obj, Object obj2) {
        return getMessage(str, new Object[]{obj, obj2});
    }

    public static String getMessage3(String str, Object obj, Object obj2, Object obj3) {
        return getMessage(str, new Object[]{obj, obj2, obj3});
    }

    public static String getMessage4(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return getMessage(str, new Object[]{obj, obj2, obj3, obj4});
    }

    public static String getMessage(String str, Object[] objArr) {
        return messageProvider.getMessage(str, objArr);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    private static class DefaultMessageProvider implements MessageProvider {
        private DefaultMessageProvider() {
        }

        @Override // org.mozilla.javascript.ScriptRuntime.MessageProvider
        public String getMessage(String str, Object[] objArr) {
            Context currentContext = Context.getCurrentContext();
            try {
                return new MessageFormat(ResourceBundle.getBundle("org.mozilla.javascript.resources.Messages", currentContext != null ? currentContext.getLocale() : Locale.getDefault()).getString(str)).format(objArr);
            } catch (MissingResourceException unused) {
                throw new RuntimeException("no message resource found for message property " + str);
            }
        }
    }

    public static EcmaError constructError(String str, String str2) {
        int[] iArr = new int[1];
        return constructError(str, str2, Context.getSourcePositionFromStack(iArr), iArr[0], null, 0);
    }

    public static EcmaError constructError(String str, String str2, int i) {
        int[] iArr = new int[1];
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        if (iArr[0] != 0) {
            iArr[0] = iArr[0] + i;
        }
        return constructError(str, str2, sourcePositionFromStack, iArr[0], null, 0);
    }

    public static EcmaError constructError(String str, String str2, String str3, int i, String str4, int i2) {
        return new EcmaError(str, str2, str3, i, str4, i2);
    }

    public static EcmaError rangeError(String str) {
        return constructError("RangeError", str);
    }

    public static EcmaError typeError(String str) {
        return constructError("TypeError", str);
    }

    public static EcmaError typeError0(String str) {
        return typeError(getMessage0(str));
    }

    public static EcmaError typeError1(String str, Object obj) {
        return typeError(getMessage1(str, obj));
    }

    public static EcmaError typeError2(String str, Object obj, Object obj2) {
        return typeError(getMessage2(str, obj, obj2));
    }

    public static EcmaError typeError3(String str, String str2, String str3, String str4) {
        return typeError(getMessage3(str, str2, str3, str4));
    }

    public static RuntimeException undefReadError(Object obj, Object obj2) {
        return typeError2("msg.undef.prop.read", toString(obj), toString(obj2));
    }

    public static RuntimeException undefCallError(Object obj, Object obj2) {
        return typeError2("msg.undef.method.call", toString(obj), toString(obj2));
    }

    public static RuntimeException undefWriteError(Object obj, Object obj2, Object obj3) {
        return typeError3("msg.undef.prop.write", toString(obj), toString(obj2), toString(obj3));
    }

    private static RuntimeException undefDeleteError(Object obj, Object obj2) {
        throw typeError2("msg.undef.prop.delete", toString(obj), toString(obj2));
    }

    public static RuntimeException notFoundError(Scriptable scriptable, String str) {
        throw constructError("ReferenceError", getMessage1("msg.is.not.defined", str));
    }

    public static RuntimeException notFunctionError(Object obj) {
        return notFunctionError(obj, obj);
    }

    public static RuntimeException notFunctionError(Object obj, Object obj2) {
        String obj3 = obj2 == null ? "null" : obj2.toString();
        if (obj == Scriptable.NOT_FOUND) {
            return typeError1("msg.function.not.found", obj3);
        }
        return typeError2("msg.isnt.function", obj3, typeof(obj));
    }

    public static RuntimeException notFunctionError(Object obj, Object obj2, String str) {
        int indexOf;
        String scriptRuntime = toString(obj);
        if ((obj instanceof NativeFunction) && (indexOf = scriptRuntime.indexOf(123, scriptRuntime.indexOf(41))) > -1) {
            scriptRuntime = scriptRuntime.substring(0, indexOf + 1) + "...}";
        }
        if (obj2 == Scriptable.NOT_FOUND) {
            return typeError2("msg.function.not.found.in", str, scriptRuntime);
        }
        return typeError3("msg.isnt.function.in", str, scriptRuntime, typeof(obj2));
    }

    private static RuntimeException notXmlError(Object obj) {
        throw typeError1("msg.isnt.xml.object", toString(obj));
    }

    private static void warnAboutNonJSObject(Object obj) {
        if ("true".equals(getMessage0("params.omit.non.js.object.warning"))) {
            return;
        }
        String message2 = getMessage2("msg.non.js.object.warning", obj, obj.getClass().getName());
        Context.reportWarning(message2);
        System.err.println(message2);
    }

    public static RegExpProxy getRegExpProxy(Context context) {
        return context.getRegExpProxy();
    }

    public static void setRegExpProxy(Context context, RegExpProxy regExpProxy) {
        if (regExpProxy == null) {
            throw new IllegalArgumentException();
        }
        context.regExpProxy = regExpProxy;
    }

    public static RegExpProxy checkRegExpProxy(Context context) {
        RegExpProxy regExpProxy = getRegExpProxy(context);
        if (regExpProxy != null) {
            return regExpProxy;
        }
        throw Context.reportRuntimeError0("msg.no.regexp");
    }

    public static Scriptable wrapRegExp(Context context, Scriptable scriptable, Object obj) {
        return context.getRegExpProxy().wrapRegExp(context, scriptable, obj);
    }

    private static XMLLib currentXMLLib(Context context) {
        if (context.topCallScope == null) {
            throw new IllegalStateException();
        }
        XMLLib xMLLib = context.cachedXMLLib;
        if (xMLLib == null) {
            xMLLib = XMLLib.extractFromScope(context.topCallScope);
            if (xMLLib == null) {
                throw new IllegalStateException();
            }
            context.cachedXMLLib = xMLLib;
        }
        return xMLLib;
    }

    public static String escapeAttributeValue(Object obj, Context context) {
        return currentXMLLib(context).escapeAttributeValue(obj);
    }

    public static String escapeTextValue(Object obj, Context context) {
        return currentXMLLib(context).escapeTextValue(obj);
    }

    public static Ref memberRef(Object obj, Object obj2, Context context, int i) {
        if (!(obj instanceof XMLObject)) {
            throw notXmlError(obj);
        }
        return ((XMLObject) obj).memberRef(context, obj2, i);
    }

    public static Ref memberRef(Object obj, Object obj2, Object obj3, Context context, int i) {
        if (!(obj instanceof XMLObject)) {
            throw notXmlError(obj);
        }
        return ((XMLObject) obj).memberRef(context, obj2, obj3, i);
    }

    public static Ref nameRef(Object obj, Context context, Scriptable scriptable, int i) {
        return currentXMLLib(context).nameRef(context, obj, scriptable, i);
    }

    public static Ref nameRef(Object obj, Object obj2, Context context, Scriptable scriptable, int i) {
        return currentXMLLib(context).nameRef(context, obj, obj2, scriptable, i);
    }

    private static void storeIndexResult(Context context, int i) {
        context.scratchIndex = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int lastIndexResult(Context context) {
        return context.scratchIndex;
    }

    public static void storeUint32Result(Context context, long j) {
        if ((j >>> 32) != 0) {
            throw new IllegalArgumentException();
        }
        context.scratchUint32 = j;
    }

    public static long lastUint32Result(Context context) {
        long j = context.scratchUint32;
        if ((j >>> 32) == 0) {
            return j;
        }
        throw new IllegalStateException();
    }

    private static void storeScriptable(Context context, Scriptable scriptable) {
        if (context.scratchScriptable != null) {
            throw new IllegalStateException();
        }
        context.scratchScriptable = scriptable;
    }

    public static Scriptable lastStoredScriptable(Context context) {
        Scriptable scriptable = context.scratchScriptable;
        context.scratchScriptable = null;
        return scriptable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String makeUrlForGeneratedScript(boolean z, String str, int i) {
        if (z) {
            return str + '#' + i + "(eval)";
        }
        return str + '#' + i + "(Function)";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isGeneratedScript(String str) {
        return str.indexOf("(eval)") >= 0 || str.indexOf("(Function)") >= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSymbol(Object obj) {
        return ((obj instanceof NativeSymbol) && ((NativeSymbol) obj).isSymbol()) || (obj instanceof SymbolKey);
    }

    private static RuntimeException errorWithClassName(String str, Object obj) {
        return Context.reportRuntimeError1(str, obj.getClass().getName());
    }

    public static JavaScriptException throwError(Context context, Scriptable scriptable, String str) {
        int[] iArr = {0};
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        return new JavaScriptException(newBuiltinObject(context, scriptable, TopLevel.Builtins.Error, new Object[]{str, sourcePositionFromStack, Integer.valueOf(iArr[0])}), sourcePositionFromStack, iArr[0]);
    }

    public static JavaScriptException throwCustomError(Context context, Scriptable scriptable, String str, String str2) {
        int[] iArr = {0};
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        return new JavaScriptException(context.newObject(scriptable, str, new Object[]{str2, sourcePositionFromStack, Integer.valueOf(iArr[0])}), sourcePositionFromStack, iArr[0]);
    }
}
