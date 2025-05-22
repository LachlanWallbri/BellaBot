package com.alibaba.fastjson.util;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.CalendarCodec;
import com.alibaba.fastjson.serializer.SerializeBeanInfo;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amitshekhar.utils.DataType;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.pudutech.shared.lib_syntime.SystemTimeSetting;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TypeUtils {
    private static Class<? extends Annotation> class_ManyToMany = null;
    private static boolean class_ManyToMany_error = false;
    private static Class<? extends Annotation> class_OneToMany = null;
    private static boolean class_OneToMany_error = false;
    public static boolean compatibleWithFieldName = false;
    public static boolean compatibleWithJavaBean = false;
    private static volatile Map<Class, String[]> kotlinIgnores = null;
    private static volatile boolean kotlinIgnores_error = false;
    private static volatile boolean kotlin_class_klass_error = false;
    private static volatile boolean kotlin_error = false;
    private static volatile Constructor kotlin_kclass_constructor = null;
    private static volatile Method kotlin_kclass_getConstructors = null;
    private static volatile Method kotlin_kfunction_getParameters = null;
    private static volatile Method kotlin_kparameter_getName = null;
    private static volatile Class kotlin_metadata = null;
    private static volatile boolean kotlin_metadata_error = false;
    private static Method method_HibernateIsInitialized = null;
    private static boolean method_HibernateIsInitialized_error = false;
    private static Class<?> optionalClass = null;
    private static boolean optionalClassInited = false;
    private static Method oracleDateMethod = null;
    private static boolean oracleDateMethodInited = false;
    private static Method oracleTimestampMethod = null;
    private static boolean oracleTimestampMethodInited = false;
    private static Class<?> pathClass = null;
    private static boolean setAccessibleEnable = true;
    private static Class<? extends Annotation> transientClass = null;
    private static boolean transientClassInited = false;
    private static ConcurrentMap<String, Class<?>> mappings = new ConcurrentHashMap(16, 0.75f, 1);
    private static boolean pathClass_error = false;

    static {
        try {
            compatibleWithJavaBean = "true".equals(IOUtils.getStringProperty(IOUtils.FASTJSON_COMPATIBLEWITHJAVABEAN));
            compatibleWithFieldName = "true".equals(IOUtils.getStringProperty(IOUtils.FASTJSON_COMPATIBLEWITHFIELDNAME));
        } catch (Throwable unused) {
        }
        addBaseClassMappings();
    }

    public static String castToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Byte castToByte(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(str));
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static Character castToChar(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return (Character) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            if (str.length() != 1) {
                throw new JSONException("can not cast to char, value : " + obj);
            }
            return Character.valueOf(str.charAt(0));
        }
        throw new JSONException("can not cast to char, value : " + obj);
    }

    public static Short castToShort(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            return Short.valueOf(Short.parseShort(str));
        }
        throw new JSONException("can not cast to short, value : " + obj);
    }

    public static BigDecimal castToBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0) {
            return null;
        }
        if ((obj instanceof Map) && ((Map) obj).size() == 0) {
            return null;
        }
        return new BigDecimal(obj2);
    }

    public static BigInteger castToBigInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return BigInteger.valueOf(((Number) obj).longValue());
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
            return null;
        }
        return new BigInteger(obj2);
    }

    public static Float castToFloat(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
                return null;
            }
            if (obj2.indexOf(44) != 0) {
                obj2 = obj2.replaceAll(",", "");
            }
            return Float.valueOf(Float.parseFloat(obj2));
        }
        throw new JSONException("can not cast to float, value : " + obj);
    }

    public static Double castToDouble(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || "null".equals(obj2) || "NULL".equals(obj2)) {
                return null;
            }
            if (obj2.indexOf(44) != 0) {
                obj2 = obj2.replaceAll(",", "");
            }
            return Double.valueOf(Double.parseDouble(obj2));
        }
        throw new JSONException("can not cast to double, value : " + obj);
    }

    public static Date castToDate(Object obj) {
        String str;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime();
        }
        long j = -1;
        if (obj instanceof Number) {
            return new Date(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            String str2 = (String) obj;
            JSONScanner jSONScanner = new JSONScanner(str2);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    return jSONScanner.getCalendar().getTime();
                }
                jSONScanner.close();
                if (str2.startsWith("/Date(") && str2.endsWith(")/")) {
                    str2 = str2.substring(6, str2.length() - 2);
                }
                if (str2.indexOf(45) != -1) {
                    if (str2.length() == JSON.DEFFAULT_DATE_FORMAT.length() || (str2.length() == 22 && JSON.DEFFAULT_DATE_FORMAT.equals("yyyyMMddHHmmssSSSZ"))) {
                        str = JSON.DEFFAULT_DATE_FORMAT;
                    } else if (str2.length() == 10) {
                        str = "yyyy-MM-dd";
                    } else if (str2.length() == 19) {
                        str = SystemTimeSetting.TIME_FORMAT;
                    } else {
                        str = (str2.length() == 29 && str2.charAt(26) == ':' && str2.charAt(28) == '0') ? "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" : "yyyy-MM-dd HH:mm:ss.SSS";
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, JSON.defaultLocale);
                    simpleDateFormat.setTimeZone(JSON.defaultTimeZone);
                    try {
                        return simpleDateFormat.parse(str2);
                    } catch (ParseException unused) {
                        throw new JSONException("can not cast to Date, value : " + str2);
                    }
                }
                if (str2.length() == 0) {
                    return null;
                }
                j = Long.parseLong(str2);
            } finally {
                jSONScanner.close();
            }
        }
        if (j < 0) {
            Class<?> cls = obj.getClass();
            if ("oracle.sql.TIMESTAMP".equals(cls.getName())) {
                if (oracleTimestampMethod == null && !oracleTimestampMethodInited) {
                    try {
                        oracleTimestampMethod = cls.getMethod("toJdbc", new Class[0]);
                    } catch (NoSuchMethodException unused2) {
                    } catch (Throwable th) {
                        oracleTimestampMethodInited = true;
                        throw th;
                    }
                    oracleTimestampMethodInited = true;
                }
                try {
                    return (Date) oracleTimestampMethod.invoke(obj, new Object[0]);
                } catch (Exception e) {
                    throw new JSONException("can not cast oracle.sql.TIMESTAMP to Date", e);
                }
            }
            if ("oracle.sql.DATE".equals(cls.getName())) {
                if (oracleDateMethod == null && !oracleDateMethodInited) {
                    try {
                        oracleDateMethod = cls.getMethod("toJdbc", new Class[0]);
                    } catch (NoSuchMethodException unused3) {
                    } catch (Throwable th2) {
                        oracleDateMethodInited = true;
                        throw th2;
                    }
                    oracleDateMethodInited = true;
                }
                try {
                    return (Date) oracleDateMethod.invoke(obj, new Object[0]);
                } catch (Exception e2) {
                    throw new JSONException("can not cast oracle.sql.DATE to Date", e2);
                }
            }
            throw new JSONException("can not cast to Date, value : " + obj);
        }
        return new Date(j);
    }

    public static java.sql.Date castToSqlDate(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof java.sql.Date) {
            return (java.sql.Date) obj;
        }
        if (obj instanceof Date) {
            return new java.sql.Date(((Date) obj).getTime());
        }
        if (obj instanceof Calendar) {
            return new java.sql.Date(((Calendar) obj).getTimeInMillis());
        }
        long longValue = obj instanceof Number ? ((Number) obj).longValue() : 0L;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (isNumber(str)) {
                longValue = Long.parseLong(str);
            } else {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    longValue = jSONScanner.getCalendar().getTime().getTime();
                } else {
                    throw new JSONException("can not cast to Timestamp, value : " + str);
                }
            }
        }
        if (longValue <= 0) {
            throw new JSONException("can not cast to Date, value : " + obj);
        }
        return new java.sql.Date(longValue);
    }

    public static Timestamp castToTimestamp(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return new Timestamp(((Calendar) obj).getTimeInMillis());
        }
        if (obj instanceof Timestamp) {
            return (Timestamp) obj;
        }
        if (obj instanceof Date) {
            return new Timestamp(((Date) obj).getTime());
        }
        long longValue = obj instanceof Number ? ((Number) obj).longValue() : 0L;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (str.endsWith(".000000000")) {
                str = str.substring(0, str.length() - 10);
            } else if (str.endsWith(".000000")) {
                str = str.substring(0, str.length() - 7);
            }
            if (isNumber(str)) {
                longValue = Long.parseLong(str);
            } else {
                JSONScanner jSONScanner = new JSONScanner(str);
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    longValue = jSONScanner.getCalendar().getTime().getTime();
                } else {
                    throw new JSONException("can not cast to Timestamp, value : " + str);
                }
            }
        }
        if (longValue <= 0) {
            throw new JSONException("can not cast to Timestamp, value : " + obj);
        }
        return new Timestamp(longValue);
    }

    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '+' || charAt == '-') {
                if (i != 0) {
                    return false;
                }
            } else if (charAt < '0' || charAt > '9') {
                return false;
            }
        }
        return true;
    }

    public static Long castToLong(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (str.indexOf(44) != 0) {
                str = str.replaceAll(",", "");
            }
            try {
                return Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                JSONScanner jSONScanner = new JSONScanner(str);
                Calendar calendar = jSONScanner.scanISO8601DateIfMatch(false) ? jSONScanner.getCalendar() : null;
                jSONScanner.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
                Iterator it = map.values().iterator();
                it.next();
                return castToLong(it.next());
            }
        }
        throw new JSONException("can not cast to long, value : " + obj);
    }

    public static Integer castToInt(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (str.indexOf(44) != 0) {
                str = str.replaceAll(",", "");
            }
            return Integer.valueOf(Integer.parseInt(str));
        }
        if (obj instanceof Boolean) {
            return Integer.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
                Iterator it = map.values().iterator();
                it.next();
                return castToInt(it.next());
            }
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static byte[] castToBytes(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            return IOUtils.decodeBase64((String) obj);
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static Boolean castToBoolean(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Number) {
            return Boolean.valueOf(((Number) obj).intValue() == 1);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if ("true".equalsIgnoreCase(str) || "1".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(str) || "0".equals(str)) {
                return Boolean.FALSE;
            }
            if ("Y".equalsIgnoreCase(str) || ExifInterface.GPS_DIRECTION_TRUE.equals(str)) {
                return Boolean.TRUE;
            }
            if ("F".equalsIgnoreCase(str) || "N".equals(str)) {
                return Boolean.FALSE;
            }
        }
        throw new JSONException("can not cast to boolean, value : " + obj);
    }

    public static <T> T castToJavaBean(Object obj, Class<T> cls) {
        return (T) cast(obj, (Class) cls, ParserConfig.getGlobalInstance());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T cast(Object obj, Class<T> cls, ParserConfig parserConfig) {
        Object obj2;
        int i = 0;
        if (obj == 0) {
            if (cls == Integer.TYPE) {
                return (T) 0;
            }
            if (cls == Long.TYPE) {
                return (T) 0L;
            }
            if (cls == Short.TYPE) {
                return (T) (short) 0;
            }
            if (cls == Byte.TYPE) {
                return (T) (byte) 0;
            }
            if (cls == Float.TYPE) {
                return (T) Float.valueOf(0.0f);
            }
            if (cls == Double.TYPE) {
                return (T) Double.valueOf(0.0d);
            }
            if (cls == Boolean.TYPE) {
                return (T) Boolean.FALSE;
            }
            return null;
        }
        if (cls == null) {
            throw new IllegalArgumentException("clazz is null");
        }
        if (cls == obj.getClass()) {
            return obj;
        }
        if (obj instanceof Map) {
            if (cls == Map.class) {
                return obj;
            }
            Map map = (Map) obj;
            return (cls != Object.class || map.containsKey(JSON.DEFAULT_TYPE_KEY)) ? (T) castToJavaBean(map, cls, parserConfig) : obj;
        }
        if (cls.isArray()) {
            if (obj instanceof Collection) {
                Collection collection = (Collection) obj;
                T t = (T) Array.newInstance(cls.getComponentType(), collection.size());
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Array.set(t, i, cast(it.next(), (Class) cls.getComponentType(), parserConfig));
                    i++;
                }
                return t;
            }
            if (cls == byte[].class) {
                return (T) castToBytes(obj);
            }
        }
        if (cls.isAssignableFrom(obj.getClass())) {
            return obj;
        }
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return (T) castToBoolean(obj);
        }
        if (cls == Byte.TYPE || cls == Byte.class) {
            return (T) castToByte(obj);
        }
        if (cls == Character.TYPE || cls == Character.class) {
            return (T) castToChar(obj);
        }
        if (cls == Short.TYPE || cls == Short.class) {
            return (T) castToShort(obj);
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            return (T) castToInt(obj);
        }
        if (cls == Long.TYPE || cls == Long.class) {
            return (T) castToLong(obj);
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return (T) castToFloat(obj);
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return (T) castToDouble(obj);
        }
        if (cls == String.class) {
            return (T) castToString(obj);
        }
        if (cls == BigDecimal.class) {
            return (T) castToBigDecimal(obj);
        }
        if (cls == BigInteger.class) {
            return (T) castToBigInteger(obj);
        }
        if (cls == Date.class) {
            return (T) castToDate(obj);
        }
        if (cls == java.sql.Date.class) {
            return (T) castToSqlDate(obj);
        }
        if (cls == Timestamp.class) {
            return (T) castToTimestamp(obj);
        }
        if (cls.isEnum()) {
            return (T) castToEnum(obj, cls, parserConfig);
        }
        if (Calendar.class.isAssignableFrom(cls)) {
            Date castToDate = castToDate(obj);
            if (cls == Calendar.class) {
                obj2 = (T) Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
            } else {
                try {
                    obj2 = (T) ((Calendar) cls.newInstance());
                } catch (Exception e) {
                    throw new JSONException("can not cast to : " + cls.getName(), e);
                }
            }
            ((Calendar) obj2).setTime(castToDate);
            return (T) obj2;
        }
        if (cls.getName().equals("javax.xml.datatype.XMLGregorianCalendar")) {
            Date castToDate2 = castToDate(obj);
            Calendar calendar = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
            calendar.setTime(castToDate2);
            return (T) CalendarCodec.instance.createXMLGregorianCalendar(calendar);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (cls == Currency.class) {
                return (T) Currency.getInstance(str);
            }
            if (cls == Locale.class) {
                return (T) toLocale(str);
            }
        }
        throw new JSONException("can not cast to : " + cls.getName());
    }

    public static Locale toLocale(String str) {
        String[] split = str.split("_");
        if (split.length == 1) {
            return new Locale(split[0]);
        }
        if (split.length == 2) {
            return new Locale(split[0], split[1]);
        }
        return new Locale(split[0], split[1], split[2]);
    }

    public static <T> T castToEnum(Object obj, Class<T> cls, ParserConfig parserConfig) {
        try {
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0) {
                    return null;
                }
                return (T) Enum.valueOf(cls, str);
            }
            if (obj instanceof Number) {
                int intValue = ((Number) obj).intValue();
                T[] enumConstants = cls.getEnumConstants();
                if (intValue < enumConstants.length) {
                    return enumConstants[intValue];
                }
            }
            throw new JSONException("can not cast to : " + cls.getName());
        } catch (Exception e) {
            throw new JSONException("can not cast to : " + cls.getName(), e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T cast(Object obj, Type type, ParserConfig parserConfig) {
        if (obj == 0) {
            return null;
        }
        if (type instanceof Class) {
            return (T) cast(obj, (Class) type, parserConfig);
        }
        if (type instanceof ParameterizedType) {
            return (T) cast(obj, (ParameterizedType) type, parserConfig);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.util.Map, java.util.HashMap] */
    public static <T> T cast(Object obj, ParameterizedType parameterizedType, ParserConfig parserConfig) {
        T t;
        Type rawType = parameterizedType.getRawType();
        if (rawType == Set.class || rawType == HashSet.class || rawType == TreeSet.class || rawType == List.class || rawType == ArrayList.class) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                if (rawType == Set.class || rawType == HashSet.class) {
                    t = (T) new HashSet();
                } else if (rawType == TreeSet.class) {
                    t = (T) new TreeSet();
                } else {
                    t = (T) new ArrayList();
                }
                Iterator<T> it = ((Iterable) obj).iterator();
                while (it.hasNext()) {
                    ((Collection) t).add(cast(it.next(), type, parserConfig));
                }
                return t;
            }
        }
        if (rawType == Map.class || rawType == HashMap.class) {
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            Type type3 = parameterizedType.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                ?? r7 = (T) new HashMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    r7.put(cast(entry.getKey(), type2, parserConfig), cast(entry.getValue(), type3, parserConfig));
                }
                return r7;
            }
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        if (parameterizedType.getActualTypeArguments().length == 1 && (parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
            return (T) cast(obj, rawType, parserConfig);
        }
        throw new JSONException("can not cast to : " + parameterizedType);
    }

    public static <T> T castToJavaBean(Map<String, Object> map, Class<T> cls, ParserConfig parserConfig) {
        JSONObject jSONObject;
        int i = 0;
        try {
            if (cls == StackTraceElement.class) {
                String str = (String) map.get("className");
                String str2 = (String) map.get("methodName");
                String str3 = (String) map.get("fileName");
                Number number = (Number) map.get("lineNumber");
                if (number != null) {
                    i = number.intValue();
                }
                return (T) new StackTraceElement(str, str2, str3, i);
            }
            Object obj = map.get(JSON.DEFAULT_TYPE_KEY);
            if (obj instanceof String) {
                String str4 = (String) obj;
                if (parserConfig == null) {
                    parserConfig = ParserConfig.global;
                }
                Class<?> checkAutoType = parserConfig.checkAutoType(str4, null);
                if (checkAutoType == null) {
                    throw new ClassNotFoundException(str4 + " not found");
                }
                if (!checkAutoType.equals(cls)) {
                    return (T) castToJavaBean(map, checkAutoType, parserConfig);
                }
            }
            if (cls.isInterface()) {
                if (map instanceof JSONObject) {
                    jSONObject = (JSONObject) map;
                } else {
                    jSONObject = new JSONObject(map);
                }
                if (parserConfig == null) {
                    parserConfig = ParserConfig.getGlobalInstance();
                }
                return parserConfig.getDeserializers().get(cls) != null ? (T) JSON.parseObject(JSON.toJSONString(jSONObject), cls) : (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, jSONObject);
            }
            if (cls == Locale.class) {
                Object obj2 = map.get("language");
                Object obj3 = map.get("country");
                if (obj2 instanceof String) {
                    String str5 = (String) obj2;
                    if (obj3 instanceof String) {
                        return (T) new Locale(str5, (String) obj3);
                    }
                    if (obj3 == null) {
                        return (T) new Locale(str5);
                    }
                }
            }
            if (cls == String.class && (map instanceof JSONObject)) {
                return (T) map.toString();
            }
            if (cls == LinkedHashMap.class && (map instanceof JSONObject)) {
                T t = (T) ((JSONObject) map).getInnerMap();
                if (t instanceof LinkedHashMap) {
                    return t;
                }
                new LinkedHashMap().putAll(t);
            }
            if (parserConfig == null) {
                parserConfig = ParserConfig.getGlobalInstance();
            }
            ObjectDeserializer deserializer = parserConfig.getDeserializer(cls);
            JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
            if (javaBeanDeserializer == null) {
                throw new JSONException("can not get javaBeanDeserializer. " + cls.getName());
            }
            return (T) javaBeanDeserializer.createInstance(map, parserConfig);
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    private static void addBaseClassMappings() {
        mappings.put("byte", Byte.TYPE);
        mappings.put("short", Short.TYPE);
        mappings.put("int", Integer.TYPE);
        mappings.put(DataType.LONG, Long.TYPE);
        mappings.put("float", Float.TYPE);
        mappings.put(TmpConstant.TYPE_VALUE_DOUBLE, Double.TYPE);
        mappings.put("boolean", Boolean.TYPE);
        mappings.put("char", Character.TYPE);
        mappings.put("[byte", byte[].class);
        mappings.put("[short", short[].class);
        mappings.put("[int", int[].class);
        mappings.put("[long", long[].class);
        mappings.put("[float", float[].class);
        mappings.put("[double", double[].class);
        mappings.put("[boolean", boolean[].class);
        mappings.put("[char", char[].class);
        mappings.put("[B", byte[].class);
        mappings.put("[S", short[].class);
        mappings.put("[I", int[].class);
        mappings.put("[J", long[].class);
        mappings.put("[F", float[].class);
        mappings.put("[D", double[].class);
        mappings.put("[C", char[].class);
        mappings.put("[Z", boolean[].class);
        Class<?>[] clsArr = {Object.class, Cloneable.class, loadClass("java.lang.AutoCloseable"), Exception.class, RuntimeException.class, IllegalAccessError.class, IllegalAccessException.class, IllegalArgumentException.class, IllegalMonitorStateException.class, IllegalStateException.class, IllegalThreadStateException.class, IndexOutOfBoundsException.class, InstantiationError.class, InstantiationException.class, InternalError.class, InterruptedException.class, LinkageError.class, NegativeArraySizeException.class, NoClassDefFoundError.class, NoSuchFieldError.class, NoSuchFieldException.class, NoSuchMethodError.class, NoSuchMethodException.class, NullPointerException.class, NumberFormatException.class, OutOfMemoryError.class, SecurityException.class, StackOverflowError.class, StringIndexOutOfBoundsException.class, TypeNotPresentException.class, VerifyError.class, StackTraceElement.class, HashMap.class, Hashtable.class, TreeMap.class, java.util.IdentityHashMap.class, WeakHashMap.class, LinkedHashMap.class, HashSet.class, LinkedHashSet.class, TreeSet.class, TimeUnit.class, ConcurrentHashMap.class, loadClass("java.util.concurrent.ConcurrentSkipListMap"), loadClass("java.util.concurrent.ConcurrentSkipListSet"), AtomicInteger.class, AtomicLong.class, Collections.EMPTY_MAP.getClass(), BitSet.class, Calendar.class, Date.class, Locale.class, UUID.class, Time.class, java.sql.Date.class, Timestamp.class, SimpleDateFormat.class, JSONObject.class};
        for (int i = 0; i < 58; i++) {
            Class<?> cls = clsArr[i];
            if (cls != null) {
                mappings.put(cls.getName(), cls);
            }
        }
        String[] strArr = {"java.awt.Rectangle", "java.awt.Point", "java.awt.Font", "java.awt.Color"};
        for (int i2 = 0; i2 < 4; i2++) {
            Class<?> loadClass = loadClass(strArr[i2]);
            if (loadClass == null) {
                break;
            }
            mappings.put(loadClass.getName(), loadClass);
        }
        String[] strArr2 = {"org.springframework.util.LinkedMultiValueMap", "org.springframework.util.LinkedCaseInsensitiveMap", "org.springframework.remoting.support.RemoteInvocation", "org.springframework.remoting.support.RemoteInvocationResult", "org.springframework.security.web.savedrequest.DefaultSavedRequest", "org.springframework.security.web.savedrequest.SavedCookie", "org.springframework.security.web.csrf.DefaultCsrfToken", "org.springframework.security.web.authentication.WebAuthenticationDetails", "org.springframework.security.core.context.SecurityContextImpl", "org.springframework.security.authentication.UsernamePasswordAuthenticationToken", "org.springframework.security.core.authority.SimpleGrantedAuthority", "org.springframework.security.core.userdetails.User"};
        for (int i3 = 0; i3 < 12; i3++) {
            Class<?> loadClass2 = loadClass(strArr2[i3]);
            if (loadClass2 == null) {
                return;
            }
            mappings.put(loadClass2.getName(), loadClass2);
        }
    }

    public static void clearClassMapping() {
        mappings.clear();
        addBaseClassMappings();
    }

    public static Class<?> loadClass(String str) {
        return loadClass(str, null);
    }

    public static boolean isPath(Class<?> cls) {
        if (pathClass == null && !pathClass_error) {
            try {
                pathClass = Class.forName("java.nio.file.Path");
            } catch (Throwable unused) {
                pathClass_error = true;
            }
        }
        Class<?> cls2 = pathClass;
        if (cls2 != null) {
            return cls2.isAssignableFrom(cls);
        }
        return false;
    }

    public static Class<?> getClassFromMapping(String str) {
        return mappings.get(str);
    }

    public static Class<?> loadClass(String str, ClassLoader classLoader) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Class<?> cls = mappings.get(str);
        if (cls != null) {
            return cls;
        }
        if (str.charAt(0) == '[') {
            return Array.newInstance(loadClass(str.substring(1), classLoader), 0).getClass();
        }
        if (str.startsWith("L") && str.endsWith(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
            return loadClass(str.substring(1, str.length() - 1), classLoader);
        }
        if (classLoader != null) {
            try {
                cls = classLoader.loadClass(str);
                mappings.put(str, cls);
                return cls;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null && contextClassLoader != classLoader) {
                Class<?> loadClass = contextClassLoader.loadClass(str);
                try {
                    mappings.put(str, loadClass);
                    return loadClass;
                } catch (Throwable unused) {
                    cls = loadClass;
                }
            }
        } catch (Throwable unused2) {
        }
        try {
            cls = Class.forName(str);
            mappings.put(str, cls);
            return cls;
        } catch (Throwable unused3) {
            return cls;
        }
    }

    public static SerializeBeanInfo buildBeanInfo(Class<?> cls, Map<String, String> map, PropertyNamingStrategy propertyNamingStrategy) {
        return buildBeanInfo(cls, map, propertyNamingStrategy, false);
    }

    public static SerializeBeanInfo buildBeanInfo(Class<?> cls, Map<String, String> map, PropertyNamingStrategy propertyNamingStrategy, boolean z) {
        PropertyNamingStrategy propertyNamingStrategy2;
        int i;
        String[] strArr;
        String str;
        String str2;
        List<FieldInfo> computeGetters;
        List<FieldInfo> list;
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        if (jSONType != null) {
            String[] orders = jSONType.orders();
            String typeName = jSONType.typeName();
            if (typeName.length() == 0) {
                typeName = null;
            }
            PropertyNamingStrategy naming = jSONType.naming();
            if (naming == null || naming == PropertyNamingStrategy.CamelCase) {
                naming = propertyNamingStrategy;
            }
            int m85of = SerializerFeature.m85of(jSONType.serialzeFeatures());
            String str3 = null;
            for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
                JSONType jSONType2 = (JSONType) getAnnotation(superclass, JSONType.class);
                if (jSONType2 == null) {
                    break;
                }
                str3 = jSONType2.typeKey();
                if (str3.length() != 0) {
                    break;
                }
            }
            for (Class<?> cls2 : cls.getInterfaces()) {
                JSONType jSONType3 = (JSONType) getAnnotation(cls2, JSONType.class);
                if (jSONType3 != null) {
                    str3 = jSONType3.typeKey();
                    if (str3.length() != 0) {
                        break;
                    }
                }
            }
            str2 = (str3 == null || str3.length() != 0) ? str3 : null;
            strArr = orders;
            str = typeName;
            propertyNamingStrategy2 = naming;
            i = m85of;
        } else {
            propertyNamingStrategy2 = propertyNamingStrategy;
            i = 0;
            strArr = null;
            str = null;
            str2 = null;
        }
        HashMap hashMap = new HashMap();
        ParserConfig.parserAllFieldToCache(cls, hashMap);
        if (z) {
            computeGetters = computeGettersWithFieldBase(cls, map, false, propertyNamingStrategy2);
        } else {
            computeGetters = computeGetters(cls, jSONType, map, hashMap, false, propertyNamingStrategy2);
        }
        FieldInfo[] fieldInfoArr = new FieldInfo[computeGetters.size()];
        computeGetters.toArray(fieldInfoArr);
        if (strArr == null || strArr.length == 0) {
            ArrayList arrayList = new ArrayList(computeGetters);
            Collections.sort(arrayList);
            list = arrayList;
        } else if (z) {
            list = computeGettersWithFieldBase(cls, map, true, propertyNamingStrategy2);
        } else {
            list = computeGetters(cls, jSONType, map, hashMap, true, propertyNamingStrategy2);
        }
        FieldInfo[] fieldInfoArr2 = new FieldInfo[list.size()];
        list.toArray(fieldInfoArr2);
        return new SerializeBeanInfo(cls, jSONType, str, str2, i, fieldInfoArr, Arrays.equals(fieldInfoArr2, fieldInfoArr) ? fieldInfoArr : fieldInfoArr2);
    }

    public static List<FieldInfo> computeGettersWithFieldBase(Class<?> cls, Map<String, String> map, boolean z, PropertyNamingStrategy propertyNamingStrategy) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            computeFields(cls2, map, propertyNamingStrategy, linkedHashMap, cls2.getDeclaredFields());
        }
        return getFieldInfos(cls, z, linkedHashMap);
    }

    public static List<FieldInfo> computeGetters(Class<?> cls, Map<String, String> map) {
        return computeGetters(cls, map, true);
    }

    public static List<FieldInfo> computeGetters(Class<?> cls, Map<String, String> map, boolean z) {
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        HashMap hashMap = new HashMap();
        ParserConfig.parserAllFieldToCache(cls, hashMap);
        return computeGetters(cls, jSONType, map, hashMap, z, PropertyNamingStrategy.CamelCase);
    }

    /* JADX WARN: Code restructure failed: missing block: B:168:0x045d, code lost:
    
        if (r0 == null) goto L237;
     */
    /* JADX WARN: Removed duplicated region for block: B:136:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0210  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<FieldInfo> computeGetters(Class<?> cls, JSONType jSONType, Map<String, String> map, Map<String, Field> map2, boolean z, PropertyNamingStrategy propertyNamingStrategy) {
        Annotation[][] annotationArr;
        int i;
        int i2;
        Method[] methodArr;
        LinkedHashMap linkedHashMap;
        Map<String, String> map3;
        JSONField jSONField;
        Constructor<?>[] constructorArr;
        short[] sArr;
        JSONField jSONField2;
        String[] strArr;
        LinkedHashMap linkedHashMap2;
        int i3;
        int i4;
        int i5;
        String str;
        Method method;
        LinkedHashMap linkedHashMap3;
        int i6;
        char charAt;
        Map<String, Field> map4;
        String substring;
        int i7;
        int i8;
        JSONField jSONField3;
        int i9;
        String propertyNameByCompatibleFieldName;
        int i10;
        Map<String, String> map5;
        int i11;
        JSONField jSONField4;
        String str2;
        int i12;
        Map<String, String> map6;
        char charAt2;
        JSONField jSONField5;
        Field fieldFromCache;
        Constructor koltinConstructor;
        Class<?> cls2 = cls;
        Map<String, String> map7 = map;
        Map<String, Field> map8 = map2;
        PropertyNamingStrategy propertyNamingStrategy2 = propertyNamingStrategy;
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        boolean isKotlin = isKotlin(cls);
        Annotation[][] annotationArr2 = (Annotation[][]) null;
        Method[] methods = cls.getMethods();
        int length = methods.length;
        Constructor<?>[] constructorArr2 = null;
        String[] strArr2 = null;
        short[] sArr2 = null;
        int i13 = 0;
        while (i13 < length) {
            Method method2 = methods[i13];
            String name = method2.getName();
            String str3 = null;
            if (Modifier.isStatic(method2.getModifiers()) || method2.getReturnType().equals(Void.TYPE) || method2.getParameterTypes().length != 0 || method2.getReturnType() == ClassLoader.class || ((name.equals("getMetaClass") && method2.getReturnType().getName().equals("groovy.lang.MetaClass")) || ((name.equals("getSuppressed") && method2.getDeclaringClass() == Throwable.class) || (isKotlin && isKotlinIgnore(cls2, name))))) {
                annotationArr = annotationArr2;
                i = i13;
                i2 = length;
                methodArr = methods;
                linkedHashMap = linkedHashMap4;
                map3 = map7;
            } else {
                JSONField jSONField6 = (JSONField) method2.getAnnotation(JSONField.class);
                if (jSONField6 == null) {
                    jSONField6 = getSuperMethodAnnotation(cls2, method2);
                }
                LinkedHashMap linkedHashMap5 = linkedHashMap4;
                if (jSONField6 == null && isKotlin) {
                    if (constructorArr2 == null && (koltinConstructor = getKoltinConstructor((constructorArr2 = cls.getDeclaredConstructors()))) != null) {
                        annotationArr2 = koltinConstructor.getParameterAnnotations();
                        strArr2 = getKoltinConstructorParameters(cls);
                        if (strArr2 != null) {
                            String[] strArr3 = new String[strArr2.length];
                            i2 = length;
                            System.arraycopy(strArr2, 0, strArr3, 0, strArr2.length);
                            Arrays.sort(strArr3);
                            short[] sArr3 = new short[strArr2.length];
                            for (short s = 0; s < strArr2.length; s = (short) (s + 1)) {
                                sArr3[Arrays.binarySearch(strArr3, strArr2[s])] = s;
                            }
                            strArr2 = strArr3;
                            sArr2 = sArr3;
                            annotationArr2 = annotationArr2;
                            if (strArr2 == null && sArr2 != null && name.startsWith(TmpConstant.PROPERTY_IDENTIFIER_GET)) {
                                String decapitalize = decapitalize(name.substring(3));
                                int binarySearch = Arrays.binarySearch(strArr2, decapitalize);
                                constructorArr = constructorArr2;
                                jSONField = jSONField6;
                                if (binarySearch < 0) {
                                    int i14 = 0;
                                    while (true) {
                                        if (i14 >= strArr2.length) {
                                            break;
                                        }
                                        if (decapitalize.equalsIgnoreCase(strArr2[i14])) {
                                            binarySearch = i14;
                                            break;
                                        }
                                        i14++;
                                    }
                                }
                                if (binarySearch >= 0) {
                                    Annotation[] annotationArr3 = annotationArr2[sArr2[binarySearch]];
                                    if (annotationArr3 != null) {
                                        int length2 = annotationArr3.length;
                                        int i15 = 0;
                                        while (i15 < length2) {
                                            annotationArr = annotationArr2;
                                            Annotation annotation = annotationArr3[i15];
                                            Annotation[] annotationArr4 = annotationArr3;
                                            if (annotation instanceof JSONField) {
                                                jSONField5 = (JSONField) annotation;
                                                break;
                                            }
                                            i15++;
                                            annotationArr2 = annotationArr;
                                            annotationArr3 = annotationArr4;
                                        }
                                    }
                                    annotationArr = annotationArr2;
                                    jSONField5 = jSONField;
                                    if (jSONField5 != null || (fieldFromCache = ParserConfig.getFieldFromCache(decapitalize, map8)) == null) {
                                        strArr = strArr2;
                                        sArr = sArr2;
                                        jSONField2 = jSONField5;
                                    } else {
                                        jSONField2 = (JSONField) fieldFromCache.getAnnotation(JSONField.class);
                                        strArr = strArr2;
                                        sArr = sArr2;
                                    }
                                    if (jSONField2 == null) {
                                        if (jSONField2.serialize()) {
                                            i3 = jSONField2.ordinal();
                                            i4 = SerializerFeature.m85of(jSONField2.serialzeFeatures());
                                            i5 = Feature.m84of(jSONField2.parseFeatures());
                                            if (jSONField2.name().length() != 0) {
                                                String name2 = jSONField2.name();
                                                if (map7 == null || (name2 = map7.get(name2)) != null) {
                                                    String str4 = name2;
                                                    i = i13;
                                                    methodArr = methods;
                                                    linkedHashMap2 = linkedHashMap5;
                                                    linkedHashMap2.put(str4, new FieldInfo(str4, method2, null, cls, null, i3, i4, i5, jSONField2, null, null));
                                                    map3 = map;
                                                    linkedHashMap = linkedHashMap2;
                                                    propertyNamingStrategy2 = propertyNamingStrategy;
                                                    constructorArr2 = constructorArr;
                                                    strArr2 = strArr;
                                                    sArr2 = sArr;
                                                }
                                            } else {
                                                i = i13;
                                                methodArr = methods;
                                                linkedHashMap2 = linkedHashMap5;
                                                if (jSONField2.label().length() != 0) {
                                                    str3 = jSONField2.label();
                                                }
                                            }
                                        }
                                        i = i13;
                                        methodArr = methods;
                                        map3 = map7;
                                        linkedHashMap = linkedHashMap5;
                                        constructorArr2 = constructorArr;
                                        strArr2 = strArr;
                                        sArr2 = sArr;
                                    } else {
                                        i = i13;
                                        methodArr = methods;
                                        linkedHashMap2 = linkedHashMap5;
                                        i3 = 0;
                                        i4 = 0;
                                        i5 = 0;
                                    }
                                    if (name.startsWith(TmpConstant.PROPERTY_IDENTIFIER_GET)) {
                                        str = name;
                                        method = method2;
                                        linkedHashMap3 = linkedHashMap2;
                                        i6 = 3;
                                    } else {
                                        if (name.length() >= 4 && !name.equals("getClass") && (!name.equals("getDeclaringClass") || !cls.isEnum())) {
                                            char charAt3 = name.charAt(3);
                                            if (Character.isUpperCase(charAt3) || charAt3 > 512) {
                                                propertyNameByCompatibleFieldName = getPropertyNameByCompatibleFieldName(map8, name, compatibleWithJavaBean ? decapitalize(name.substring(3)) : Character.toLowerCase(name.charAt(3)) + name.substring(4), 3);
                                            } else if (charAt3 == '_') {
                                                propertyNameByCompatibleFieldName = name.substring(4);
                                            } else if (charAt3 == 'f') {
                                                propertyNameByCompatibleFieldName = name.substring(3);
                                            } else if (name.length() >= 5 && Character.isUpperCase(name.charAt(4))) {
                                                propertyNameByCompatibleFieldName = decapitalize(name.substring(3));
                                            }
                                            if (!isJSONTypeIgnore(cls2, propertyNameByCompatibleFieldName)) {
                                                Field fieldFromCache2 = ParserConfig.getFieldFromCache(propertyNameByCompatibleFieldName, map8);
                                                if (fieldFromCache2 != null || propertyNameByCompatibleFieldName.length() <= 1 || (charAt2 = propertyNameByCompatibleFieldName.charAt(1)) < 'A' || charAt2 > 'Z') {
                                                    i10 = 3;
                                                } else {
                                                    i10 = 3;
                                                    fieldFromCache2 = ParserConfig.getFieldFromCache(decapitalize(name.substring(3)), map8);
                                                }
                                                Field field = fieldFromCache2;
                                                if (field != null) {
                                                    JSONField jSONField7 = (JSONField) field.getAnnotation(JSONField.class);
                                                    if (jSONField7 == null) {
                                                        map5 = map;
                                                        i11 = i4;
                                                        str2 = str3;
                                                        jSONField4 = jSONField7;
                                                    } else if (jSONField7.serialize()) {
                                                        int ordinal = jSONField7.ordinal();
                                                        int m85of = SerializerFeature.m85of(jSONField7.serialzeFeatures());
                                                        int m84of = Feature.m84of(jSONField7.parseFeatures());
                                                        if (jSONField7.name().length() != 0) {
                                                            propertyNameByCompatibleFieldName = jSONField7.name();
                                                            map6 = map;
                                                            if (map6 != null && (propertyNameByCompatibleFieldName = map6.get(propertyNameByCompatibleFieldName)) == null) {
                                                                map3 = map6;
                                                                linkedHashMap = linkedHashMap2;
                                                                propertyNamingStrategy2 = propertyNamingStrategy;
                                                                constructorArr2 = constructorArr;
                                                                strArr2 = strArr;
                                                                sArr2 = sArr;
                                                            }
                                                        } else {
                                                            map6 = map;
                                                        }
                                                        if (jSONField7.label().length() != 0) {
                                                            i12 = ordinal;
                                                            i11 = m85of;
                                                            i5 = m84of;
                                                            map5 = map6;
                                                            str2 = jSONField7.label();
                                                            jSONField4 = jSONField7;
                                                        } else {
                                                            jSONField4 = jSONField7;
                                                            i11 = m85of;
                                                            i5 = m84of;
                                                            map5 = map6;
                                                            str2 = str3;
                                                            i12 = ordinal;
                                                        }
                                                        if (map5 == null && (propertyNameByCompatibleFieldName = map5.get(propertyNameByCompatibleFieldName)) == null) {
                                                            map3 = map5;
                                                            linkedHashMap = linkedHashMap2;
                                                            propertyNamingStrategy2 = propertyNamingStrategy;
                                                            constructorArr2 = constructorArr;
                                                            strArr2 = strArr;
                                                            sArr2 = sArr;
                                                        } else {
                                                            LinkedHashMap linkedHashMap6 = linkedHashMap2;
                                                            if (propertyNamingStrategy != null) {
                                                                propertyNameByCompatibleFieldName = propertyNamingStrategy.translate(propertyNameByCompatibleFieldName);
                                                            }
                                                            String str5 = propertyNameByCompatibleFieldName;
                                                            str = name;
                                                            method = method2;
                                                            i6 = i10;
                                                            linkedHashMap3 = linkedHashMap6;
                                                            linkedHashMap3.put(str5, new FieldInfo(str5, method2, field, cls, null, i12, i11, i5, jSONField2, jSONField4, str2));
                                                            i3 = i12;
                                                            i4 = i11;
                                                            str3 = str2;
                                                        }
                                                    }
                                                } else {
                                                    map5 = map;
                                                    i11 = i4;
                                                    jSONField4 = null;
                                                    str2 = str3;
                                                }
                                                i12 = i3;
                                                if (map5 == null) {
                                                }
                                                LinkedHashMap linkedHashMap62 = linkedHashMap2;
                                                if (propertyNamingStrategy != null) {
                                                }
                                                String str52 = propertyNameByCompatibleFieldName;
                                                str = name;
                                                method = method2;
                                                i6 = i10;
                                                linkedHashMap3 = linkedHashMap62;
                                                linkedHashMap3.put(str52, new FieldInfo(str52, method2, field, cls, null, i12, i11, i5, jSONField2, jSONField4, str2));
                                                i3 = i12;
                                                i4 = i11;
                                                str3 = str2;
                                            }
                                        }
                                        map3 = map;
                                        linkedHashMap = linkedHashMap2;
                                        propertyNamingStrategy2 = propertyNamingStrategy;
                                        constructorArr2 = constructorArr;
                                        strArr2 = strArr;
                                        sArr2 = sArr;
                                    }
                                    if (str.startsWith("is") && str.length() >= i6 && (method.getReturnType() == Boolean.TYPE || method.getReturnType() == Boolean.class)) {
                                        charAt = str.charAt(2);
                                        if (Character.isUpperCase(charAt)) {
                                            map4 = map2;
                                            int i16 = i6;
                                            if (charAt == '_') {
                                                substring = str.substring(i16);
                                            } else if (charAt == 'f') {
                                                substring = str.substring(2);
                                            }
                                        } else {
                                            map4 = map2;
                                            substring = getPropertyNameByCompatibleFieldName(map4, str, compatibleWithJavaBean ? decapitalize(str.substring(2)) : Character.toLowerCase(str.charAt(2)) + str.substring(i6), 2);
                                        }
                                        String str6 = str;
                                        cls2 = cls;
                                        if (!isJSONTypeIgnore(cls2, substring)) {
                                            Field fieldFromCache3 = ParserConfig.getFieldFromCache(substring, map4);
                                            Field fieldFromCache4 = fieldFromCache3 == null ? ParserConfig.getFieldFromCache(str6, map4) : fieldFromCache3;
                                            if (fieldFromCache4 != null) {
                                                JSONField jSONField8 = (JSONField) fieldFromCache4.getAnnotation(JSONField.class);
                                                if (jSONField8 == null) {
                                                    map3 = map;
                                                    i7 = i3;
                                                    i8 = i4;
                                                    i9 = i5;
                                                    jSONField3 = jSONField8;
                                                } else if (jSONField8.serialize()) {
                                                    int ordinal2 = jSONField8.ordinal();
                                                    int m85of2 = SerializerFeature.m85of(jSONField8.serialzeFeatures());
                                                    int m84of2 = Feature.m84of(jSONField8.parseFeatures());
                                                    if (jSONField8.name().length() != 0) {
                                                        substring = jSONField8.name();
                                                        map3 = map;
                                                        if (map3 != null) {
                                                            substring = map3.get(substring);
                                                        }
                                                    } else {
                                                        map3 = map;
                                                    }
                                                    if (jSONField8.label().length() != 0) {
                                                        jSONField3 = jSONField8;
                                                        i8 = m85of2;
                                                        i9 = m84of2;
                                                        str3 = jSONField8.label();
                                                        i7 = ordinal2;
                                                    } else {
                                                        jSONField3 = jSONField8;
                                                        i7 = ordinal2;
                                                        i8 = m85of2;
                                                        i9 = m84of2;
                                                    }
                                                }
                                            } else {
                                                map3 = map;
                                                i7 = i3;
                                                i8 = i4;
                                                jSONField3 = null;
                                                i9 = i5;
                                            }
                                            if (map3 == null || (substring = map3.get(substring)) != null) {
                                                propertyNamingStrategy2 = propertyNamingStrategy;
                                                if (propertyNamingStrategy2 != null) {
                                                    substring = propertyNamingStrategy2.translate(substring);
                                                }
                                                String str7 = substring;
                                                if (!linkedHashMap3.containsKey(str7)) {
                                                    linkedHashMap = linkedHashMap3;
                                                    linkedHashMap.put(str7, new FieldInfo(str7, method, fieldFromCache4, cls, null, i7, i8, i9, jSONField2, jSONField3, str3));
                                                    constructorArr2 = constructorArr;
                                                    strArr2 = strArr;
                                                    sArr2 = sArr;
                                                }
                                                linkedHashMap = linkedHashMap3;
                                                constructorArr2 = constructorArr;
                                                strArr2 = strArr;
                                                sArr2 = sArr;
                                            }
                                            propertyNamingStrategy2 = propertyNamingStrategy;
                                            linkedHashMap = linkedHashMap3;
                                            constructorArr2 = constructorArr;
                                            strArr2 = strArr;
                                            sArr2 = sArr;
                                        }
                                        map3 = map;
                                        propertyNamingStrategy2 = propertyNamingStrategy;
                                        linkedHashMap = linkedHashMap3;
                                        constructorArr2 = constructorArr;
                                        strArr2 = strArr;
                                        sArr2 = sArr;
                                    }
                                    cls2 = cls;
                                    map3 = map;
                                    propertyNamingStrategy2 = propertyNamingStrategy;
                                    linkedHashMap = linkedHashMap3;
                                    constructorArr2 = constructorArr;
                                    strArr2 = strArr;
                                    sArr2 = sArr;
                                } else {
                                    annotationArr = annotationArr2;
                                }
                            } else {
                                annotationArr = annotationArr2;
                                constructorArr = constructorArr2;
                                jSONField = jSONField6;
                            }
                        }
                    }
                    i2 = length;
                    if (strArr2 == null) {
                    }
                    annotationArr = annotationArr2;
                    constructorArr = constructorArr2;
                    jSONField = jSONField6;
                } else {
                    jSONField = jSONField6;
                    i2 = length;
                    annotationArr = annotationArr2;
                    constructorArr = constructorArr2;
                }
                sArr = sArr2;
                jSONField2 = jSONField;
                strArr = strArr2;
                if (jSONField2 == null) {
                }
                if (name.startsWith(TmpConstant.PROPERTY_IDENTIFIER_GET)) {
                }
                if (str.startsWith("is")) {
                    charAt = str.charAt(2);
                    if (Character.isUpperCase(charAt)) {
                    }
                    String str62 = str;
                    cls2 = cls;
                    if (!isJSONTypeIgnore(cls2, substring)) {
                    }
                    map3 = map;
                    propertyNamingStrategy2 = propertyNamingStrategy;
                    linkedHashMap = linkedHashMap3;
                    constructorArr2 = constructorArr;
                    strArr2 = strArr;
                    sArr2 = sArr;
                }
                cls2 = cls;
                map3 = map;
                propertyNamingStrategy2 = propertyNamingStrategy;
                linkedHashMap = linkedHashMap3;
                constructorArr2 = constructorArr;
                strArr2 = strArr;
                sArr2 = sArr;
            }
            i13 = i + 1;
            linkedHashMap4 = linkedHashMap;
            map7 = map3;
            length = i2;
            annotationArr2 = annotationArr;
            methods = methodArr;
            map8 = map2;
        }
        LinkedHashMap linkedHashMap7 = linkedHashMap4;
        computeFields(cls2, map7, propertyNamingStrategy2, linkedHashMap7, cls.getFields());
        return getFieldInfos(cls2, z, linkedHashMap7);
    }

    private static List<FieldInfo> getFieldInfos(Class<?> cls, boolean z, Map<String, FieldInfo> map) {
        ArrayList arrayList = new ArrayList();
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        String[] orders = jSONType != null ? jSONType.orders() : null;
        if (orders != null && orders.length > 0) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(arrayList.size());
            for (FieldInfo fieldInfo : map.values()) {
                linkedHashMap.put(fieldInfo.name, fieldInfo);
            }
            for (String str : orders) {
                FieldInfo fieldInfo2 = (FieldInfo) linkedHashMap.get(str);
                if (fieldInfo2 != null) {
                    arrayList.add(fieldInfo2);
                    linkedHashMap.remove(str);
                }
            }
            Iterator it = linkedHashMap.values().iterator();
            while (it.hasNext()) {
                arrayList.add((FieldInfo) it.next());
            }
        } else {
            Iterator<FieldInfo> it2 = map.values().iterator();
            while (it2.hasNext()) {
                arrayList.add(it2.next());
            }
            if (z) {
                Collections.sort(arrayList);
            }
        }
        return arrayList;
    }

    private static void computeFields(Class<?> cls, Map<String, String> map, PropertyNamingStrategy propertyNamingStrategy, Map<String, FieldInfo> map2, Field[] fieldArr) {
        String str;
        int i;
        int i2;
        int i3;
        for (Field field : fieldArr) {
            if (!Modifier.isStatic(field.getModifiers())) {
                JSONField jSONField = (JSONField) field.getAnnotation(JSONField.class);
                String name = field.getName();
                if (jSONField == null) {
                    str = null;
                    i = 0;
                    i2 = 0;
                    i3 = 0;
                } else if (jSONField.serialize()) {
                    int ordinal = jSONField.ordinal();
                    int m85of = SerializerFeature.m85of(jSONField.serialzeFeatures());
                    int m84of = Feature.m84of(jSONField.parseFeatures());
                    if (jSONField.name().length() != 0) {
                        name = jSONField.name();
                    }
                    str = jSONField.label().length() != 0 ? jSONField.label() : null;
                    i = ordinal;
                    i2 = m85of;
                    i3 = m84of;
                }
                if (map == null || (name = map.get(name)) != null) {
                    if (propertyNamingStrategy != null) {
                        name = propertyNamingStrategy.translate(name);
                    }
                    String str2 = name;
                    if (!map2.containsKey(str2)) {
                        map2.put(str2, new FieldInfo(str2, null, field, cls, null, i, i2, i3, null, jSONField, str));
                    }
                }
            }
        }
    }

    private static String getPropertyNameByCompatibleFieldName(Map<String, Field> map, String str, String str2, int i) {
        if (!compatibleWithFieldName || map.containsKey(str2)) {
            return str2;
        }
        String substring = str.substring(i);
        return map.containsKey(substring) ? substring : str2;
    }

    public static JSONField getSuperMethodAnnotation(Class<?> cls, Method method) {
        boolean z;
        JSONField jSONField;
        boolean z2;
        JSONField jSONField2;
        Class<?>[] interfaces = cls.getInterfaces();
        if (interfaces.length > 0) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> cls2 : interfaces) {
                for (Method method2 : cls2.getMethods()) {
                    Class<?>[] parameterTypes2 = method2.getParameterTypes();
                    if (parameterTypes2.length == parameterTypes.length && method2.getName().equals(method.getName())) {
                        int i = 0;
                        while (true) {
                            if (i >= parameterTypes.length) {
                                z2 = true;
                                break;
                            }
                            if (!parameterTypes2[i].equals(parameterTypes[i])) {
                                z2 = false;
                                break;
                            }
                            i++;
                        }
                        if (z2 && (jSONField2 = (JSONField) method2.getAnnotation(JSONField.class)) != null) {
                            return jSONField2;
                        }
                    }
                }
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && Modifier.isAbstract(superclass.getModifiers())) {
            Class<?>[] parameterTypes3 = method.getParameterTypes();
            for (Method method3 : superclass.getMethods()) {
                Class<?>[] parameterTypes4 = method3.getParameterTypes();
                if (parameterTypes4.length == parameterTypes3.length && method3.getName().equals(method.getName())) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= parameterTypes3.length) {
                            z = true;
                            break;
                        }
                        if (!parameterTypes4[i2].equals(parameterTypes3[i2])) {
                            z = false;
                            break;
                        }
                        i2++;
                    }
                    if (z && (jSONField = (JSONField) method3.getAnnotation(JSONField.class)) != null) {
                        return jSONField;
                    }
                }
            }
        }
        return null;
    }

    private static boolean isJSONTypeIgnore(Class<?> cls, String str) {
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        if (jSONType != null) {
            String[] includes = jSONType.includes();
            if (includes.length > 0) {
                for (String str2 : includes) {
                    if (str.equals(str2)) {
                        return false;
                    }
                }
                return true;
            }
            for (String str3 : jSONType.ignores()) {
                if (str.equals(str3)) {
                    return true;
                }
            }
        }
        return (cls.getSuperclass() == Object.class || cls.getSuperclass() == null || !isJSONTypeIgnore(cls.getSuperclass(), str)) ? false : true;
    }

    public static boolean isGenericParamType(Type type) {
        Type genericSuperclass;
        if (type instanceof ParameterizedType) {
            return true;
        }
        if (!(type instanceof Class) || (genericSuperclass = ((Class) type).getGenericSuperclass()) == Object.class) {
            return false;
        }
        return isGenericParamType(genericSuperclass);
    }

    public static Type getGenericParamType(Type type) {
        return (!(type instanceof ParameterizedType) && (type instanceof Class)) ? getGenericParamType(((Class) type).getGenericSuperclass()) : type;
    }

    public static Type unwrapOptional(Type type) {
        if (!optionalClassInited) {
            try {
                optionalClass = Class.forName("java.util.Optional");
            } catch (Exception unused) {
            } catch (Throwable th) {
                optionalClassInited = true;
                throw th;
            }
            optionalClassInited = true;
        }
        if (!(type instanceof ParameterizedType)) {
            return type;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return parameterizedType.getRawType() == optionalClass ? parameterizedType.getActualTypeArguments()[0] : type;
    }

    public static Class<?> getClass(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        }
        if (type instanceof TypeVariable) {
            return (Class) ((TypeVariable) type).getBounds()[0];
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getClass(upperBounds[0]);
            }
            return Object.class;
        }
        return Object.class;
    }

    public static Field getField(Class<?> cls, String str, Field[] fieldArr) {
        char charAt;
        char charAt2;
        for (Field field : fieldArr) {
            String name = field.getName();
            if (str.equals(name)) {
                return field;
            }
            if (str.length() > 2 && (charAt = str.charAt(0)) >= 'a' && charAt <= 'z' && (charAt2 = str.charAt(1)) >= 'A' && charAt2 <= 'Z' && str.equalsIgnoreCase(name)) {
                return field;
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass == null || superclass == Object.class) {
            return null;
        }
        return getField(superclass, str, superclass.getDeclaredFields());
    }

    public static int getSerializeFeatures(Class<?> cls) {
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        if (jSONType == null) {
            return 0;
        }
        return SerializerFeature.m85of(jSONType.serialzeFeatures());
    }

    public static int getParserFeatures(Class<?> cls) {
        JSONType jSONType = (JSONType) getAnnotation(cls, JSONType.class);
        if (jSONType == null) {
            return 0;
        }
        return Feature.m84of(jSONType.parseFeatures());
    }

    public static String decapitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (str.length() > 1 && Character.isUpperCase(str.charAt(1)) && Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        return new String(charArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setAccessible(AccessibleObject accessibleObject) {
        if (setAccessibleEnable && !accessibleObject.isAccessible()) {
            try {
                accessibleObject.setAccessible(true);
            } catch (AccessControlException unused) {
                setAccessibleEnable = false;
            }
        }
    }

    public static Type getCollectionItemType(Type type) {
        Type type2;
        if (type instanceof ParameterizedType) {
            type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type2).getUpperBounds();
                if (upperBounds.length == 1) {
                    type2 = upperBounds[0];
                }
            }
        } else {
            if (type instanceof Class) {
                Class cls = (Class) type;
                if (!cls.getName().startsWith("java.")) {
                    type2 = getCollectionItemType(cls.getGenericSuperclass());
                }
            }
            type2 = null;
        }
        return type2 == null ? Object.class : type2;
    }

    public static Class<?> getCollectionItemClass(Type type) {
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type2).getUpperBounds();
                if (upperBounds.length == 1) {
                    type2 = upperBounds[0];
                }
            }
            if (type2 instanceof Class) {
                Class<?> cls = (Class) type2;
                if (Modifier.isPublic(cls.getModifiers())) {
                    return cls;
                }
                throw new JSONException("can not create ASMParser");
            }
            throw new JSONException("can not create ASMParser");
        }
        return Object.class;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.lang.Class] */
    public static Type checkPrimitiveArray(GenericArrayType genericArrayType) {
        Type genericComponentType = genericArrayType.getGenericComponentType();
        String str = "[";
        while (genericComponentType instanceof GenericArrayType) {
            genericComponentType = ((GenericArrayType) genericComponentType).getGenericComponentType();
            str = str + str;
        }
        if (genericComponentType instanceof Class) {
            Class cls = (Class) genericComponentType;
            if (cls.isPrimitive()) {
                try {
                    if (cls == Boolean.TYPE) {
                        genericArrayType = Class.forName(str + "Z");
                    } else if (cls == Character.TYPE) {
                        genericArrayType = Class.forName(str + "C");
                    } else if (cls == Byte.TYPE) {
                        genericArrayType = Class.forName(str + "B");
                    } else if (cls == Short.TYPE) {
                        genericArrayType = Class.forName(str + ExifInterface.LATITUDE_SOUTH);
                    } else if (cls == Integer.TYPE) {
                        genericArrayType = Class.forName(str + "I");
                    } else if (cls == Long.TYPE) {
                        genericArrayType = Class.forName(str + "J");
                    } else if (cls == Float.TYPE) {
                        genericArrayType = Class.forName(str + "F");
                    } else {
                        genericArrayType = genericArrayType;
                        if (cls == Double.TYPE) {
                            genericArrayType = Class.forName(str + "D");
                        }
                    }
                } catch (ClassNotFoundException unused) {
                }
            }
        }
        return genericArrayType;
    }

    public static Collection createCollection(Type type) {
        Type type2;
        Class<?> rawClass = getRawClass(type);
        if (rawClass == AbstractCollection.class || rawClass == Collection.class) {
            return new ArrayList();
        }
        if (rawClass.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (rawClass.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (rawClass.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (rawClass.isAssignableFrom(EnumSet.class)) {
            if (type instanceof ParameterizedType) {
                type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                type2 = Object.class;
            }
            return EnumSet.noneOf((Class) type2);
        }
        try {
            return (Collection) rawClass.newInstance();
        } catch (Exception unused) {
            throw new JSONException("create instance error, class " + rawClass.getName());
        }
    }

    public static Class<?> getRawClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getRawClass(((ParameterizedType) type).getRawType());
        }
        throw new JSONException("TODO");
    }

    public static boolean isProxy(Class<?> cls) {
        for (Class<?> cls2 : cls.getInterfaces()) {
            String name = cls2.getName();
            if (name.equals("net.sf.cglib.proxy.Factory") || name.equals("org.springframework.cglib.proxy.Factory") || name.equals("javassist.util.proxy.ProxyObject") || name.equals("org.apache.ibatis.javassist.util.proxy.ProxyObject")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTransient(Method method) {
        if (method == null) {
            return false;
        }
        if (!transientClassInited) {
            try {
                transientClass = Class.forName("java.beans.Transient");
            } catch (Exception unused) {
            } catch (Throwable th) {
                transientClassInited = true;
                throw th;
            }
            transientClassInited = true;
        }
        Class<? extends Annotation> cls = transientClass;
        return (cls == null || method.getAnnotation(cls) == null) ? false : true;
    }

    public static boolean isAnnotationPresentOneToMany(Method method) {
        if (method == null) {
            return false;
        }
        if (class_OneToMany == null && !class_OneToMany_error) {
            try {
                class_OneToMany = Class.forName("javax.persistence.OneToMany");
            } catch (Throwable unused) {
                class_OneToMany_error = true;
            }
        }
        Class<? extends Annotation> cls = class_OneToMany;
        if (cls == null) {
            return false;
        }
        return method.isAnnotationPresent(cls);
    }

    public static boolean isAnnotationPresentManyToMany(Method method) {
        if (method == null) {
            return false;
        }
        if (class_ManyToMany == null && !class_ManyToMany_error) {
            try {
                class_ManyToMany = Class.forName("javax.persistence.ManyToMany");
            } catch (Throwable unused) {
                class_ManyToMany_error = true;
            }
        }
        if (class_ManyToMany == null) {
            return false;
        }
        return method.isAnnotationPresent(class_OneToMany) || method.isAnnotationPresent(class_ManyToMany);
    }

    public static boolean isHibernateInitialized(Object obj) {
        if (obj == null) {
            return false;
        }
        if (method_HibernateIsInitialized == null && !method_HibernateIsInitialized_error) {
            try {
                method_HibernateIsInitialized = Class.forName("org.hibernate.Hibernate").getMethod("isInitialized", Object.class);
            } catch (Throwable unused) {
                method_HibernateIsInitialized_error = true;
            }
        }
        Method method = method_HibernateIsInitialized;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(null, obj)).booleanValue();
            } catch (Throwable unused2) {
            }
        }
        return true;
    }

    public static long fnv1a_64_lower(String str) {
        long j = -3750763034362895579L;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != '_' && charAt != '-') {
                if (charAt >= 'A' && charAt <= 'Z') {
                    charAt = (char) (charAt + ' ');
                }
                j = (j ^ charAt) * 1099511628211L;
            }
        }
        return j;
    }

    public static long fnv1a_64(String str) {
        long j = -3750763034362895579L;
        for (int i = 0; i < str.length(); i++) {
            j = (j ^ str.charAt(i)) * 1099511628211L;
        }
        return j;
    }

    public static boolean isKotlin(Class cls) {
        if (kotlin_metadata == null && !kotlin_metadata_error) {
            try {
                kotlin_metadata = Class.forName("kotlin.Metadata");
            } catch (Throwable unused) {
                kotlin_metadata_error = true;
            }
        }
        if (kotlin_metadata == null) {
            return false;
        }
        return cls.isAnnotationPresent(kotlin_metadata);
    }

    public static Constructor getKoltinConstructor(Constructor[] constructorArr) {
        Constructor constructor = null;
        for (Constructor constructor2 : constructorArr) {
            Class<?>[] parameterTypes = constructor2.getParameterTypes();
            if ((parameterTypes.length <= 0 || !parameterTypes[parameterTypes.length - 1].getName().equals("kotlin.jvm.internal.DefaultConstructorMarker")) && (constructor == null || constructor.getParameterTypes().length < parameterTypes.length)) {
                constructor = constructor2;
            }
        }
        return constructor;
    }

    public static String[] getKoltinConstructorParameters(Class cls) {
        if (kotlin_kclass_constructor == null && !kotlin_class_klass_error) {
            try {
                kotlin_kclass_constructor = Class.forName("kotlin.reflect.jvm.internal.KClassImpl").getConstructor(Class.class);
            } catch (Throwable unused) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kclass_constructor == null) {
            return null;
        }
        if (kotlin_kclass_getConstructors == null && !kotlin_class_klass_error) {
            try {
                kotlin_kclass_getConstructors = Class.forName("kotlin.reflect.jvm.internal.KClassImpl").getMethod("getConstructors", new Class[0]);
            } catch (Throwable unused2) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kfunction_getParameters == null && !kotlin_class_klass_error) {
            try {
                kotlin_kfunction_getParameters = Class.forName("kotlin.reflect.KFunction").getMethod("getParameters", new Class[0]);
            } catch (Throwable unused3) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kparameter_getName == null && !kotlin_class_klass_error) {
            try {
                kotlin_kparameter_getName = Class.forName("kotlin.reflect.KParameter").getMethod("getName", new Class[0]);
            } catch (Throwable unused4) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_error) {
            return null;
        }
        try {
            Iterator it = ((Iterable) kotlin_kclass_getConstructors.invoke(kotlin_kclass_constructor.newInstance(cls), new Object[0])).iterator();
            Object obj = null;
            while (it.hasNext()) {
                obj = it.next();
                it.hasNext();
            }
            List list = (List) kotlin_kfunction_getParameters.invoke(obj, new Object[0]);
            String[] strArr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                strArr[i] = (String) kotlin_kparameter_getName.invoke(list.get(i), new Object[0]);
            }
            return strArr;
        } catch (Throwable th) {
            th.printStackTrace();
            kotlin_error = true;
            return null;
        }
    }

    private static boolean isKotlinIgnore(Class cls, String str) {
        String[] strArr;
        if (kotlinIgnores == null && !kotlinIgnores_error) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put(Class.forName("kotlin.ranges.CharRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.IntRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.LongRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.ClosedFloatRange"), new String[]{"getEndInclusive", "isEmpty"});
                hashMap.put(Class.forName("kotlin.ranges.ClosedDoubleRange"), new String[]{"getEndInclusive", "isEmpty"});
                kotlinIgnores = hashMap;
            } catch (Throwable unused) {
                kotlinIgnores_error = true;
            }
        }
        return (kotlinIgnores == null || (strArr = kotlinIgnores.get(cls)) == null || Arrays.binarySearch(strArr, str) < 0) ? false : true;
    }

    public static <A extends Annotation> A getAnnotation(Class<?> cls, Class<A> cls2) {
        A a = (A) cls.getAnnotation(cls2);
        if (a != null) {
            return a;
        }
        if (cls.getAnnotations().length <= 0) {
            return null;
        }
        for (Annotation annotation : cls.getAnnotations()) {
            A a2 = (A) annotation.annotationType().getAnnotation(cls2);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }
}
