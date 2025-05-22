package com.alibaba.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.FieldSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class JSONPath implements JSONAware {
    static final long SIZE = 5614464919154503228L;
    private static ConcurrentMap<String, JSONPath> pathCache = new ConcurrentHashMap(128, 0.75f, 1);
    private ParserConfig parserConfig;
    private final String path;
    private Segement[] segments;
    private SerializeConfig serializeConfig;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface Filter {
        boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum Operator {
        EQ,
        NE,
        GT,
        GE,
        LT,
        LE,
        LIKE,
        NOT_LIKE,
        RLIKE,
        NOT_RLIKE,
        IN,
        NOT_IN,
        BETWEEN,
        NOT_BETWEEN
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Segement {
        Object eval(JSONPath jSONPath, Object obj, Object obj2);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    interface Segment {
        Object eval(JSONPath jSONPath, Object obj, Object obj2);

        void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context);
    }

    public JSONPath(String str) {
        this(str, SerializeConfig.getGlobalInstance(), ParserConfig.getGlobalInstance());
    }

    public JSONPath(String str, SerializeConfig serializeConfig, ParserConfig parserConfig) {
        if (str == null || str.length() == 0) {
            throw new JSONPathException("json-path can not be null or empty");
        }
        this.path = str;
        this.serializeConfig = serializeConfig;
        this.parserConfig = parserConfig;
    }

    protected void init() {
        if (this.segments != null) {
            return;
        }
        if ("*".equals(this.path)) {
            this.segments = new Segement[]{WildCardSegement.instance};
        } else {
            this.segments = new JSONPathParser(this.path).explain();
        }
    }

    public Object eval(Object obj) {
        if (obj == null) {
            return null;
        }
        init();
        int i = 0;
        Object obj2 = obj;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i >= segementArr.length) {
                return obj2;
            }
            obj2 = segementArr[i].eval(this, obj, obj2);
            i++;
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        init();
        Object obj2 = obj;
        int i = 0;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i >= segementArr.length) {
                return true;
            }
            obj2 = segementArr[i].eval(this, obj, obj2);
            if (obj2 == null) {
                return false;
            }
            i++;
        }
    }

    public boolean containsValue(Object obj, Object obj2) {
        Object eval = eval(obj);
        if (eval == obj2) {
            return true;
        }
        if (eval == null) {
            return false;
        }
        if (eval instanceof Iterable) {
            Iterator it = ((Iterable) eval).iterator();
            while (it.hasNext()) {
                if (m83eq(it.next(), obj2)) {
                    return true;
                }
            }
            return false;
        }
        return m83eq(eval, obj2);
    }

    public int size(Object obj) {
        if (obj == null) {
            return -1;
        }
        init();
        int i = 0;
        Object obj2 = obj;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i < segementArr.length) {
                obj2 = segementArr[i].eval(this, obj, obj2);
                i++;
            } else {
                return evalSize(obj2);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private static class Context {
        final boolean eval;
        Object object;
        final Context parent;

        public Context(Context context, boolean z) {
            this.parent = context;
            this.eval = z;
        }
    }

    public void arrayAdd(Object obj, Object... objArr) {
        if (objArr == null || objArr.length == 0 || obj == null) {
            return;
        }
        init();
        Object obj2 = null;
        int i = 0;
        Object obj3 = obj;
        int i2 = 0;
        while (true) {
            if (i2 >= this.segments.length) {
                break;
            }
            if (i2 == r4.length - 1) {
                obj2 = obj3;
            }
            obj3 = this.segments[i2].eval(this, obj, obj3);
            i2++;
        }
        if (obj3 == null) {
            throw new JSONPathException("value not found in path " + this.path);
        }
        if (obj3 instanceof Collection) {
            Collection collection = (Collection) obj3;
            int length = objArr.length;
            while (i < length) {
                collection.add(objArr[i]);
                i++;
            }
            return;
        }
        Class<?> cls = obj3.getClass();
        if (cls.isArray()) {
            int length2 = Array.getLength(obj3);
            Object newInstance = Array.newInstance(cls.getComponentType(), objArr.length + length2);
            System.arraycopy(obj3, 0, newInstance, 0, length2);
            while (i < objArr.length) {
                Array.set(newInstance, length2 + i, objArr[i]);
                i++;
            }
            Segement segement = this.segments[r8.length - 1];
            if (segement instanceof PropertySegement) {
                ((PropertySegement) segement).setValue(this, obj2, newInstance);
                return;
            } else {
                if (segement instanceof ArrayAccessSegement) {
                    ((ArrayAccessSegement) segement).setValue(this, obj2, newInstance);
                    return;
                }
                throw new UnsupportedOperationException();
            }
        }
        throw new JSONException("unsupported array put operation. " + cls);
    }

    public boolean remove(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        init();
        Object obj2 = null;
        Object obj3 = obj;
        int i = 0;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i >= segementArr.length) {
                break;
            }
            if (i == segementArr.length - 1) {
                obj2 = obj3;
                break;
            }
            obj3 = segementArr[i].eval(this, obj, obj3);
            if (obj3 == null) {
                break;
            }
            i++;
        }
        if (obj2 == null) {
            return false;
        }
        Segement[] segementArr2 = this.segments;
        Segement segement = segementArr2[segementArr2.length - 1];
        if (segement instanceof PropertySegement) {
            PropertySegement propertySegement = (PropertySegement) segement;
            if ((obj2 instanceof Collection) && segementArr2.length > 1) {
                Segement segement2 = segementArr2[segementArr2.length - 2];
                if ((segement2 instanceof RangeSegement) || (segement2 instanceof MultiIndexSegement)) {
                    Iterator it = ((Collection) obj2).iterator();
                    while (it.hasNext()) {
                        if (propertySegement.remove(this, it.next())) {
                            z = true;
                        }
                    }
                    return z;
                }
            }
            return propertySegement.remove(this, obj2);
        }
        if (segement instanceof ArrayAccessSegement) {
            return ((ArrayAccessSegement) segement).remove(this, obj2);
        }
        throw new UnsupportedOperationException();
    }

    public boolean set(Object obj, Object obj2) {
        return set(obj, obj2, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean set(Object obj, Object obj2, boolean z) {
        Class<?> cls;
        JavaBeanDeserializer javaBeanDeserializer;
        if (obj == null) {
            return false;
        }
        init();
        Object obj3 = obj;
        int i = 0;
        Object obj4 = null;
        while (true) {
            Segement[] segementArr = this.segments;
            if (i >= segementArr.length) {
                obj3 = obj4;
                break;
            }
            Segement segement = segementArr[i];
            Object eval = segement.eval(this, obj, obj3);
            if (eval == null) {
                Segement[] segementArr2 = this.segments;
                Segement segement2 = i < segementArr2.length - 1 ? segementArr2[i + 1] : null;
                if (segement2 instanceof PropertySegement) {
                    if (segement instanceof PropertySegement) {
                        String str = ((PropertySegement) segement).propertyName;
                        JavaBeanDeserializer javaBeanDeserializer2 = getJavaBeanDeserializer(obj3.getClass());
                        if (javaBeanDeserializer2 != null) {
                            cls = javaBeanDeserializer2.getFieldDeserializer(str).fieldInfo.fieldClass;
                            javaBeanDeserializer = getJavaBeanDeserializer(cls);
                            if (javaBeanDeserializer == null) {
                                if (javaBeanDeserializer.beanInfo.defaultConstructor == null) {
                                    return false;
                                }
                                eval = javaBeanDeserializer.createInstance((DefaultJSONParser) null, cls);
                            } else {
                                eval = new JSONObject();
                            }
                        }
                    }
                    cls = null;
                    javaBeanDeserializer = null;
                    if (javaBeanDeserializer == null) {
                    }
                } else {
                    eval = segement2 instanceof ArrayAccessSegement ? new JSONArray() : null;
                }
                if (eval != null) {
                    if (segement instanceof PropertySegement) {
                        ((PropertySegement) segement).setValue(this, obj3, eval);
                    } else {
                        if (!(segement instanceof ArrayAccessSegement)) {
                            break;
                        }
                        ((ArrayAccessSegement) segement).setValue(this, obj3, eval);
                    }
                } else {
                    break;
                }
            }
            i++;
            obj4 = obj3;
            obj3 = eval;
        }
        if (obj3 == null) {
            return false;
        }
        Segement[] segementArr3 = this.segments;
        Segement segement3 = segementArr3[segementArr3.length - 1];
        if (segement3 instanceof PropertySegement) {
            ((PropertySegement) segement3).setValue(this, obj3, obj2);
            return true;
        }
        if (segement3 instanceof ArrayAccessSegement) {
            return ((ArrayAccessSegement) segement3).setValue(this, obj3, obj2);
        }
        throw new UnsupportedOperationException();
    }

    public static Object eval(Object obj, String str) {
        return compile(str).eval(obj);
    }

    public static int size(Object obj, String str) {
        JSONPath compile = compile(str);
        return compile.evalSize(compile.eval(obj));
    }

    public static boolean contains(Object obj, String str) {
        if (obj == null) {
            return false;
        }
        return compile(str).contains(obj);
    }

    public static boolean containsValue(Object obj, String str, Object obj2) {
        return compile(str).containsValue(obj, obj2);
    }

    public static void arrayAdd(Object obj, String str, Object... objArr) {
        compile(str).arrayAdd(obj, objArr);
    }

    public static boolean set(Object obj, String str, Object obj2) {
        return compile(str).set(obj, obj2);
    }

    public static boolean remove(Object obj, String str) {
        return compile(str).remove(obj);
    }

    public static JSONPath compile(String str) {
        if (str == null) {
            throw new JSONPathException("jsonpath can not be null");
        }
        JSONPath jSONPath = pathCache.get(str);
        if (jSONPath != null) {
            return jSONPath;
        }
        JSONPath jSONPath2 = new JSONPath(str);
        if (pathCache.size() >= 1024) {
            return jSONPath2;
        }
        pathCache.putIfAbsent(str, jSONPath2);
        return pathCache.get(str);
    }

    public static Object read(String str, String str2) {
        return compile(str2).eval(JSON.parse(str));
    }

    public static Map<String, Object> paths(Object obj) {
        return paths(obj, SerializeConfig.globalInstance);
    }

    public static Map<String, Object> paths(Object obj, SerializeConfig serializeConfig) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        paths(identityHashMap, "/", obj, serializeConfig);
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : identityHashMap.entrySet()) {
            hashMap.put(entry.getValue(), entry.getKey());
        }
        return hashMap;
    }

    private static void paths(Map<Object, String> map, String str, Object obj, SerializeConfig serializeConfig) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        if (obj == null || map.containsKey(obj)) {
            return;
        }
        map.put(obj, str);
        if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                if (key instanceof String) {
                    if (str.equals("/")) {
                        sb4 = new StringBuilder();
                    } else {
                        sb4 = new StringBuilder();
                        sb4.append(str);
                    }
                    sb4.append("/");
                    sb4.append(key);
                    paths(map, sb4.toString(), entry.getValue(), serializeConfig);
                }
            }
            return;
        }
        int i = 0;
        if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                if (str.equals("/")) {
                    sb3 = new StringBuilder();
                } else {
                    sb3 = new StringBuilder();
                    sb3.append(str);
                }
                sb3.append("/");
                sb3.append(i);
                paths(map, sb3.toString(), obj2, serializeConfig);
                i++;
            }
            return;
        }
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            int length = Array.getLength(obj);
            while (i < length) {
                Object obj3 = Array.get(obj, i);
                if (str.equals("/")) {
                    sb2 = new StringBuilder();
                } else {
                    sb2 = new StringBuilder();
                    sb2.append(str);
                }
                sb2.append("/");
                sb2.append(i);
                paths(map, sb2.toString(), obj3, serializeConfig);
                i++;
            }
            return;
        }
        if (ParserConfig.isPrimitive2(cls) || cls.isEnum()) {
            return;
        }
        ObjectSerializer objectWriter = serializeConfig.getObjectWriter(cls);
        if (objectWriter instanceof JavaBeanSerializer) {
            try {
                for (Map.Entry<String, Object> entry2 : ((JavaBeanSerializer) objectWriter).getFieldValuesMap(obj).entrySet()) {
                    String key2 = entry2.getKey();
                    if (key2 instanceof String) {
                        if (str.equals("/")) {
                            sb = new StringBuilder();
                            sb.append("/");
                            sb.append(key2);
                        } else {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("/");
                            sb.append(key2);
                        }
                        paths(map, sb.toString(), entry2.getValue(), serializeConfig);
                    }
                }
            } catch (Exception e) {
                throw new JSONException("toJSON error", e);
            }
        }
    }

    public String getPath() {
        return this.path;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class JSONPathParser {

        /* renamed from: ch */
        private char f297ch;
        private int level;
        private final String path;
        private int pos;

        static boolean isDigitFirst(char c) {
            return c == '-' || c == '+' || (c >= '0' && c <= '9');
        }

        public JSONPathParser(String str) {
            this.path = str;
            next();
        }

        void next() {
            String str = this.path;
            int i = this.pos;
            this.pos = i + 1;
            this.f297ch = str.charAt(i);
        }

        boolean isEOF() {
            return this.pos >= this.path.length();
        }

        Segement readSegement() {
            char c;
            boolean z = true;
            if (this.level == 0 && this.path.length() == 1) {
                if (isDigitFirst(this.f297ch)) {
                    return new ArrayAccessSegement(this.f297ch - '0');
                }
                char c2 = this.f297ch;
                if ((c2 >= 'a' && c2 <= 'z') || ((c = this.f297ch) >= 'A' && c <= 'Z')) {
                    return new PropertySegement(Character.toString(this.f297ch), false);
                }
            }
            while (!isEOF()) {
                skipWhitespace();
                char c3 = this.f297ch;
                if (c3 != '$') {
                    if (c3 != '.' && c3 != '/') {
                        if (c3 == '[') {
                            return parseArrayAccess(true);
                        }
                        if (this.level == 0) {
                            return new PropertySegement(readName(), false);
                        }
                        throw new JSONPathException("not support jsonpath : " + this.path);
                    }
                    char c4 = this.f297ch;
                    next();
                    if (c4 == '.' && this.f297ch == '.') {
                        next();
                        int length = this.path.length();
                        int i = this.pos;
                        if (length > i + 3 && this.f297ch == '[' && this.path.charAt(i) == '*' && this.path.charAt(this.pos + 1) == ']' && this.path.charAt(this.pos + 2) == '.') {
                            next();
                            next();
                            next();
                            next();
                        }
                    } else {
                        z = false;
                    }
                    char c5 = this.f297ch;
                    if (c5 == '*') {
                        if (!isEOF()) {
                            next();
                        }
                        return WildCardSegement.instance;
                    }
                    if (isDigitFirst(c5)) {
                        return parseArrayAccess(false);
                    }
                    String readName = readName();
                    if (this.f297ch == '(') {
                        next();
                        if (this.f297ch == ')') {
                            if (!isEOF()) {
                                next();
                            }
                            if ("size".equals(readName)) {
                                return SizeSegement.instance;
                            }
                            throw new JSONPathException("not support jsonpath : " + this.path);
                        }
                        throw new JSONPathException("not support jsonpath : " + this.path);
                    }
                    return new PropertySegement(readName, z);
                }
                next();
            }
            return null;
        }

        public final void skipWhitespace() {
            while (true) {
                char c = this.f297ch;
                if (c > ' ') {
                    return;
                }
                if (c != ' ' && c != '\r' && c != '\n' && c != '\t' && c != '\f' && c != '\b') {
                    return;
                } else {
                    next();
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x0060, code lost:
        
            r0 = r14.pos;
         */
        /* JADX WARN: Removed duplicated region for block: B:29:0x007f  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x008b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        Segement parseArrayAccess(boolean z) {
            boolean z2;
            String str;
            String str2;
            String str3;
            String str4;
            String[] strArr;
            int i;
            String substring;
            if (z) {
                accept('[');
            }
            int i2 = 0;
            if (this.f297ch == '?') {
                next();
                accept('(');
                if (this.f297ch == '@') {
                    next();
                    accept(FilenameUtils.EXTENSION_SEPARATOR);
                }
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 || IOUtils.firstIdentifier(this.f297ch)) {
                String readName = readName();
                skipWhitespace();
                if (z2 && this.f297ch == ')') {
                    next();
                    if (z) {
                        accept(']');
                    }
                    return new FilterSegement(new NotNullSegement(readName));
                }
                if (z && this.f297ch == ']') {
                    next();
                    return new FilterSegement(new NotNullSegement(readName));
                }
                Operator readOp = readOp();
                skipWhitespace();
                if (readOp == Operator.BETWEEN || readOp == Operator.NOT_BETWEEN) {
                    boolean z3 = readOp == Operator.NOT_BETWEEN;
                    Object readValue = readValue();
                    if (!"and".equalsIgnoreCase(readName())) {
                        throw new JSONPathException(this.path);
                    }
                    Object readValue2 = readValue();
                    if (readValue == null || readValue2 == null) {
                        throw new JSONPathException(this.path);
                    }
                    if (JSONPath.isInt(readValue.getClass()) && JSONPath.isInt(readValue2.getClass())) {
                        return new FilterSegement(new IntBetweenSegement(readName, ((Number) readValue).longValue(), ((Number) readValue2).longValue(), z3));
                    }
                    throw new JSONPathException(this.path);
                }
                if (readOp == Operator.IN || readOp == Operator.NOT_IN) {
                    boolean z4 = readOp == Operator.NOT_IN;
                    accept('(');
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.add(readValue());
                    while (true) {
                        skipWhitespace();
                        if (this.f297ch != ',') {
                            break;
                        }
                        next();
                        jSONArray.add(readValue());
                    }
                    accept(')');
                    if (z2) {
                        accept(')');
                    }
                    if (z) {
                        accept(']');
                    }
                    boolean z5 = true;
                    boolean z6 = true;
                    boolean z7 = true;
                    for (Object obj : jSONArray) {
                        if (obj != null) {
                            Class<?> cls = obj.getClass();
                            if (z5 && cls != Byte.class && cls != Short.class && cls != Integer.class && cls != Long.class) {
                                z5 = false;
                                z7 = false;
                            }
                            if (z6 && cls != String.class) {
                                z6 = false;
                            }
                        } else if (z5) {
                            z5 = false;
                        }
                    }
                    if (jSONArray.size() == 1 && jSONArray.get(0) == null) {
                        if (z4) {
                            return new FilterSegement(new NotNullSegement(readName));
                        }
                        return new FilterSegement(new NullSegement(readName));
                    }
                    if (z5) {
                        if (jSONArray.size() == 1) {
                            return new FilterSegement(new IntOpSegement(readName, ((Number) jSONArray.get(0)).longValue(), z4 ? Operator.NE : Operator.EQ));
                        }
                        int size = jSONArray.size();
                        long[] jArr = new long[size];
                        while (i2 < size) {
                            jArr[i2] = ((Number) jSONArray.get(i2)).longValue();
                            i2++;
                        }
                        return new FilterSegement(new IntInSegement(readName, jArr, z4));
                    }
                    if (z6) {
                        if (jSONArray.size() == 1) {
                            return new FilterSegement(new StringOpSegement(readName, (String) jSONArray.get(0), z4 ? Operator.NE : Operator.EQ));
                        }
                        String[] strArr2 = new String[jSONArray.size()];
                        jSONArray.toArray(strArr2);
                        return new FilterSegement(new StringInSegement(readName, strArr2, z4));
                    }
                    if (z7) {
                        int size2 = jSONArray.size();
                        Long[] lArr = new Long[size2];
                        while (i2 < size2) {
                            Number number = (Number) jSONArray.get(i2);
                            if (number != null) {
                                lArr[i2] = Long.valueOf(number.longValue());
                            }
                            i2++;
                        }
                        return new FilterSegement(new IntObjInSegement(readName, lArr, z4));
                    }
                    throw new UnsupportedOperationException();
                }
                char c = this.f297ch;
                if (c == '\'' || c == '\"') {
                    String readString = readString();
                    if (z2) {
                        accept(')');
                    }
                    if (z) {
                        accept(']');
                    }
                    if (readOp == Operator.RLIKE) {
                        return new FilterSegement(new RlikeSegement(readName, readString, false));
                    }
                    if (readOp == Operator.NOT_RLIKE) {
                        return new FilterSegement(new RlikeSegement(readName, readString, true));
                    }
                    if (readOp == Operator.LIKE || readOp == Operator.NOT_LIKE) {
                        while (readString.indexOf("%%") != -1) {
                            readString = readString.replaceAll("%%", "%");
                        }
                        boolean z8 = readOp == Operator.NOT_LIKE;
                        int indexOf = readString.indexOf(37);
                        if (indexOf == -1) {
                            if (readOp == Operator.LIKE) {
                                readOp = Operator.EQ;
                            } else {
                                readOp = Operator.NE;
                            }
                        } else {
                            String[] split = readString.split("%");
                            String[] strArr3 = null;
                            if (indexOf == 0) {
                                if (readString.charAt(readString.length() - 1) == '%') {
                                    int length = split.length - 1;
                                    String[] strArr4 = new String[length];
                                    System.arraycopy(split, 1, strArr4, 0, length);
                                    strArr = strArr4;
                                    str3 = null;
                                    str4 = null;
                                    return new FilterSegement(new MatchSegement(readName, str3, str4, strArr, z8));
                                }
                                String str5 = split[split.length - 1];
                                if (split.length > 2) {
                                    int length2 = split.length - 2;
                                    String[] strArr5 = new String[length2];
                                    System.arraycopy(split, 1, strArr5, 0, length2);
                                    str4 = str5;
                                    strArr = strArr5;
                                    str3 = null;
                                } else {
                                    str4 = str5;
                                    str3 = null;
                                    strArr = null;
                                }
                                return new FilterSegement(new MatchSegement(readName, str3, str4, strArr, z8));
                            }
                            if (readString.charAt(readString.length() - 1) != '%') {
                                if (split.length == 1) {
                                    str3 = split[0];
                                    str4 = null;
                                    strArr = null;
                                } else {
                                    if (split.length == 2) {
                                        str = split[0];
                                        str2 = split[1];
                                    } else {
                                        str = split[0];
                                        str2 = split[split.length - 1];
                                        int length3 = split.length - 2;
                                        strArr3 = new String[length3];
                                        System.arraycopy(split, 1, strArr3, 0, length3);
                                    }
                                    str3 = str;
                                    str4 = str2;
                                    strArr = strArr3;
                                }
                                return new FilterSegement(new MatchSegement(readName, str3, str4, strArr, z8));
                            }
                            strArr = split;
                            str3 = null;
                            str4 = null;
                            return new FilterSegement(new MatchSegement(readName, str3, str4, strArr, z8));
                        }
                    }
                    return new FilterSegement(new StringOpSegement(readName, readString, readOp));
                }
                if (isDigitFirst(c)) {
                    long readLongValue = readLongValue();
                    double readDoubleValue = this.f297ch == '.' ? readDoubleValue(readLongValue) : 0.0d;
                    if (z2) {
                        accept(')');
                    }
                    if (z) {
                        accept(']');
                    }
                    if (readDoubleValue == 0.0d) {
                        return new FilterSegement(new IntOpSegement(readName, readLongValue, readOp));
                    }
                    return new FilterSegement(new DoubleOpSegement(readName, readDoubleValue, readOp));
                }
                char c2 = this.f297ch;
                if (c2 == 'n') {
                    if ("null".equals(readName())) {
                        if (z2) {
                            accept(')');
                        }
                        accept(']');
                        if (readOp == Operator.EQ) {
                            return new FilterSegement(new NullSegement(readName));
                        }
                        if (readOp == Operator.NE) {
                            return new FilterSegement(new NotNullSegement(readName));
                        }
                        throw new UnsupportedOperationException();
                    }
                } else if (c2 == 't') {
                    if ("true".equals(readName())) {
                        if (z2) {
                            accept(')');
                        }
                        accept(']');
                        if (readOp == Operator.EQ) {
                            return new FilterSegement(new ValueSegment(readName, Boolean.TRUE, true));
                        }
                        if (readOp == Operator.NE) {
                            return new FilterSegement(new ValueSegment(readName, Boolean.TRUE, false));
                        }
                        throw new UnsupportedOperationException();
                    }
                } else if (c2 == 'f' && "false".equals(readName())) {
                    if (z2) {
                        accept(')');
                    }
                    accept(']');
                    if (readOp == Operator.EQ) {
                        return new FilterSegement(new ValueSegment(readName, Boolean.FALSE, true));
                    }
                    if (readOp == Operator.NE) {
                        return new FilterSegement(new ValueSegment(readName, Boolean.FALSE, false));
                    }
                    throw new UnsupportedOperationException();
                }
                throw new UnsupportedOperationException();
            }
            int i3 = this.pos - 1;
            while (true) {
                char c3 = this.f297ch;
                if (c3 == ']' || c3 == '/' || isEOF() || !(this.f297ch != '.' || z2 || z2)) {
                    break;
                }
                if (this.f297ch == '\\') {
                    next();
                }
                next();
            }
            char c4 = this.f297ch;
            if (c4 == '/' || c4 == '.') {
                int i4 = this.pos;
                i = i4 - 1;
                substring = this.path.substring(i3, i);
                if (substring.indexOf("\\.") == -1) {
                    return new PropertySegement(substring.replaceAll("\\\\\\.", "\\."), false);
                }
                Segement buildArraySegement = buildArraySegement(substring);
                if (z && !isEOF()) {
                    accept(']');
                }
                return buildArraySegement;
            }
            i = this.pos;
            substring = this.path.substring(i3, i);
            if (substring.indexOf("\\.") == -1) {
            }
        }

        protected long readLongValue() {
            int i = this.pos - 1;
            char c = this.f297ch;
            if (c == '+' || c == '-') {
                next();
            }
            while (true) {
                char c2 = this.f297ch;
                if (c2 < '0' || c2 > '9') {
                    break;
                }
                next();
            }
            return Long.parseLong(this.path.substring(i, this.pos - 1));
        }

        protected double readDoubleValue(long j) {
            int i = this.pos - 1;
            next();
            while (true) {
                char c = this.f297ch;
                if (c < '0' || c > '9') {
                    break;
                }
                next();
            }
            return Double.parseDouble(this.path.substring(i, this.pos - 1)) + j;
        }

        protected Object readValue() {
            skipWhitespace();
            if (isDigitFirst(this.f297ch)) {
                return Long.valueOf(readLongValue());
            }
            char c = this.f297ch;
            if (c == '\"' || c == '\'') {
                return readString();
            }
            if (c == 'n') {
                if ("null".equals(readName())) {
                    return null;
                }
                throw new JSONPathException(this.path);
            }
            throw new UnsupportedOperationException();
        }

        protected Operator readOp() {
            Operator operator;
            char c = this.f297ch;
            if (c == '=') {
                next();
                operator = Operator.EQ;
            } else if (c == '!') {
                next();
                accept('=');
                operator = Operator.NE;
            } else if (c == '<') {
                next();
                if (this.f297ch == '=') {
                    next();
                    operator = Operator.LE;
                } else {
                    operator = Operator.LT;
                }
            } else if (c == '>') {
                next();
                if (this.f297ch == '=') {
                    next();
                    operator = Operator.GE;
                } else {
                    operator = Operator.GT;
                }
            } else {
                operator = null;
            }
            if (operator != null) {
                return operator;
            }
            String readName = readName();
            if ("not".equalsIgnoreCase(readName)) {
                skipWhitespace();
                String readName2 = readName();
                if ("like".equalsIgnoreCase(readName2)) {
                    return Operator.NOT_LIKE;
                }
                if ("rlike".equalsIgnoreCase(readName2)) {
                    return Operator.NOT_RLIKE;
                }
                if ("in".equalsIgnoreCase(readName2)) {
                    return Operator.NOT_IN;
                }
                if ("between".equalsIgnoreCase(readName2)) {
                    return Operator.NOT_BETWEEN;
                }
                throw new UnsupportedOperationException();
            }
            if ("like".equalsIgnoreCase(readName)) {
                return Operator.LIKE;
            }
            if ("rlike".equalsIgnoreCase(readName)) {
                return Operator.RLIKE;
            }
            if ("in".equalsIgnoreCase(readName)) {
                return Operator.IN;
            }
            if ("between".equalsIgnoreCase(readName)) {
                return Operator.BETWEEN;
            }
            throw new UnsupportedOperationException();
        }

        String readName() {
            skipWhitespace();
            char c = this.f297ch;
            if (c != '\\' && !IOUtils.firstIdentifier(c)) {
                throw new JSONPathException("illeal jsonpath syntax. " + this.path);
            }
            StringBuilder sb = new StringBuilder();
            while (!isEOF()) {
                char c2 = this.f297ch;
                if (c2 == '\\') {
                    next();
                    sb.append(this.f297ch);
                    if (isEOF()) {
                        break;
                    }
                    next();
                } else {
                    if (!IOUtils.isIdent(c2)) {
                        break;
                    }
                    sb.append(this.f297ch);
                    next();
                }
            }
            if (isEOF() && IOUtils.isIdent(this.f297ch)) {
                sb.append(this.f297ch);
            }
            return sb.toString();
        }

        String readString() {
            char c = this.f297ch;
            next();
            int i = this.pos - 1;
            while (this.f297ch != c && !isEOF()) {
                next();
            }
            String substring = this.path.substring(i, isEOF() ? this.pos : this.pos - 1);
            accept(c);
            return substring;
        }

        void accept(char c) {
            if (this.f297ch != c) {
                throw new JSONPathException("expect '" + c + ", but '" + this.f297ch + "'");
            }
            if (isEOF()) {
                return;
            }
            next();
        }

        public Segement[] explain() {
            String str = this.path;
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException();
            }
            Segement[] segementArr = new Segement[8];
            while (true) {
                Segement readSegement = readSegement();
                if (readSegement == null) {
                    break;
                }
                int i = this.level;
                if (i == segementArr.length) {
                    Segement[] segementArr2 = new Segement[(i * 3) / 2];
                    System.arraycopy(segementArr, 0, segementArr2, 0, i);
                    segementArr = segementArr2;
                }
                int i2 = this.level;
                this.level = i2 + 1;
                segementArr[i2] = readSegement;
            }
            int i3 = this.level;
            if (i3 == segementArr.length) {
                return segementArr;
            }
            Segement[] segementArr3 = new Segement[i3];
            System.arraycopy(segementArr, 0, segementArr3, 0, i3);
            return segementArr3;
        }

        Segement buildArraySegement(String str) {
            int length = str.length();
            int i = 0;
            char charAt = str.charAt(0);
            int i2 = length - 1;
            char charAt2 = str.charAt(i2);
            int indexOf = str.indexOf(44);
            if (str.length() > 2 && charAt == '\'' && charAt2 == '\'') {
                if (indexOf == -1) {
                    return new PropertySegement(str.substring(1, i2), false);
                }
                String[] split = str.split(",");
                String[] strArr = new String[split.length];
                while (i < split.length) {
                    String str2 = split[i];
                    strArr[i] = str2.substring(1, str2.length() - 1);
                    i++;
                }
                return new MultiPropertySegement(strArr);
            }
            int indexOf2 = str.indexOf(58);
            if (indexOf == -1 && indexOf2 == -1) {
                if (TypeUtils.isNumber(str)) {
                    try {
                        return new ArrayAccessSegement(Integer.parseInt(str));
                    } catch (NumberFormatException unused) {
                        return new PropertySegement(str, false);
                    }
                }
                return new PropertySegement(str, false);
            }
            if (indexOf != -1) {
                String[] split2 = str.split(",");
                int[] iArr = new int[split2.length];
                while (i < split2.length) {
                    iArr[i] = Integer.parseInt(split2[i]);
                    i++;
                }
                return new MultiIndexSegement(iArr);
            }
            if (indexOf2 != -1) {
                String[] split3 = str.split(":");
                int length2 = split3.length;
                int[] iArr2 = new int[length2];
                for (int i3 = 0; i3 < split3.length; i3++) {
                    String str3 = split3[i3];
                    if (str3.length() != 0) {
                        iArr2[i3] = Integer.parseInt(str3);
                    } else if (i3 == 0) {
                        iArr2[i3] = 0;
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
                int i4 = iArr2[0];
                int i5 = length2 > 1 ? iArr2[1] : -1;
                int i6 = length2 == 3 ? iArr2[2] : 1;
                if (i5 < 0 || i5 >= i4) {
                    if (i6 <= 0) {
                        throw new UnsupportedOperationException("step must greater than zero : " + i6);
                    }
                    return new RangeSegement(i4, i5, i6);
                }
                throw new UnsupportedOperationException("end must greater than or equals start. start " + i4 + ",  end " + i5);
            }
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SizeSegement implements Segement {
        public static final SizeSegement instance = new SizeSegement();

        SizeSegement() {
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Integer eval(JSONPath jSONPath, Object obj, Object obj2) {
            return Integer.valueOf(jSONPath.evalSize(obj2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class PropertySegement implements Segement {
        private final boolean deep;
        private final String propertyName;
        private final long propertyNameHash;

        public PropertySegement(String str, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.deep = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (this.deep) {
                ArrayList arrayList = new ArrayList();
                jSONPath.deepScan(obj2, this.propertyName, arrayList);
                return arrayList;
            }
            return jSONPath.getPropertyValue(obj2, this.propertyName, this.propertyNameHash);
        }

        public void setValue(JSONPath jSONPath, Object obj, Object obj2) {
            if (this.deep) {
                jSONPath.deepSet(obj, this.propertyName, this.propertyNameHash, obj2);
            } else {
                jSONPath.setPropertyValue(obj, this.propertyName, this.propertyNameHash, obj2);
            }
        }

        public boolean remove(JSONPath jSONPath, Object obj) {
            return jSONPath.removePropertyValue(obj, this.propertyName);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class MultiPropertySegement implements Segement {
        private final String[] propertyNames;
        private final long[] propertyNamesHash;

        public MultiPropertySegement(String[] strArr) {
            this.propertyNames = strArr;
            this.propertyNamesHash = new long[strArr.length];
            int i = 0;
            while (true) {
                long[] jArr = this.propertyNamesHash;
                if (i >= jArr.length) {
                    return;
                }
                jArr[i] = TypeUtils.fnv1a_64(strArr[i]);
                i++;
            }
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            ArrayList arrayList = new ArrayList(this.propertyNames.length);
            int i = 0;
            while (true) {
                String[] strArr = this.propertyNames;
                if (i >= strArr.length) {
                    return arrayList;
                }
                arrayList.add(jSONPath.getPropertyValue(obj2, strArr[i], this.propertyNamesHash[i]));
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class WildCardSegement implements Segement {
        public static WildCardSegement instance = new WildCardSegement();

        WildCardSegement() {
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getPropertyValues(obj2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ArrayAccessSegement implements Segement {
        private final int index;

        public ArrayAccessSegement(int i) {
            this.index = i;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getArrayItem(obj2, this.index);
        }

        public boolean setValue(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.setArrayItem(jSONPath, obj, this.index, obj2);
        }

        public boolean remove(JSONPath jSONPath, Object obj) {
            return jSONPath.removeArrayItem(jSONPath, obj, this.index);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class MultiIndexSegement implements Segement {
        private final int[] indexes;

        public MultiIndexSegement(int[] iArr) {
            this.indexes = iArr;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            ArrayList arrayList = new ArrayList(this.indexes.length);
            int i = 0;
            while (true) {
                int[] iArr = this.indexes;
                if (i >= iArr.length) {
                    return arrayList;
                }
                arrayList.add(jSONPath.getArrayItem(obj2, iArr[i]));
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class RangeSegement implements Segement {
        private final int end;
        private final int start;
        private final int step;

        public RangeSegement(int i, int i2, int i3) {
            this.start = i;
            this.end = i2;
            this.step = i3;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            int intValue = SizeSegement.instance.eval(jSONPath, obj, obj2).intValue();
            int i = this.start;
            if (i < 0) {
                i += intValue;
            }
            int i2 = this.end;
            if (i2 < 0) {
                i2 += intValue;
            }
            int i3 = ((i2 - i) / this.step) + 1;
            if (i3 == -1) {
                return null;
            }
            ArrayList arrayList = new ArrayList(i3);
            while (i <= i2 && i < intValue) {
                arrayList.add(jSONPath.getArrayItem(obj2, i));
                i += this.step;
            }
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class NotNullSegement implements Filter {
        private final String propertyName;
        private final long propertyNameHash;

        public NotNullSegement(String str) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash) != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class NullSegement implements Filter {
        private final String propertyName;
        private final long propertyNameHash;

        public NullSegement(String str) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            return jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash) == null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ValueSegment implements Filter {

        /* renamed from: eq */
        private boolean f308eq;
        private final String propertyName;
        private final long propertyNameHash;
        private final Object value;

        public ValueSegment(String str, Object obj, boolean z) {
            this.f308eq = true;
            if (obj == null) {
                throw new IllegalArgumentException("value is null");
            }
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = obj;
            this.f308eq = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            boolean equals = this.value.equals(jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash));
            return !this.f308eq ? !equals : equals;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class IntInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final long[] values;

        public IntInSegement(String str, long[] jArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = jArr;
            this.not = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long longValue = ((Number) propertyValue).longValue();
                for (long j : this.values) {
                    if (j == longValue) {
                        return !this.not;
                    }
                }
            }
            return this.not;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class IntBetweenSegement implements Filter {
        private final long endValue;
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final long startValue;

        public IntBetweenSegement(String str, long j, long j2, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.startValue = j;
            this.endValue = j2;
            this.not = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long longValue = ((Number) propertyValue).longValue();
                if (longValue >= this.startValue && longValue <= this.endValue) {
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class IntObjInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final Long[] values;

        public IntObjInSegement(String str, Long[] lArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = lArr;
            this.not = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            int i = 0;
            if (propertyValue == null) {
                Long[] lArr = this.values;
                int length = lArr.length;
                while (i < length) {
                    if (lArr[i] == null) {
                        return !this.not;
                    }
                    i++;
                }
                return this.not;
            }
            if (propertyValue instanceof Number) {
                long longValue = ((Number) propertyValue).longValue();
                Long[] lArr2 = this.values;
                int length2 = lArr2.length;
                while (i < length2) {
                    Long l = lArr2[i];
                    if (l != null && l.longValue() == longValue) {
                        return !this.not;
                    }
                    i++;
                }
            }
            return this.not;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class StringInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final String[] values;

        public StringInSegement(String str, String[] strArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.values = strArr;
            this.not = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            for (String str : this.values) {
                if (str == propertyValue) {
                    return !this.not;
                }
                if (str != null && str.equals(propertyValue)) {
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class IntOpSegement implements Filter {

        /* renamed from: op */
        private final Operator f296op;
        private final String propertyName;
        private final long propertyNameHash;
        private final long value;

        public IntOpSegement(String str, long j, Operator operator) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = j;
            this.f296op = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null || !(propertyValue instanceof Number)) {
                return false;
            }
            long longValue = ((Number) propertyValue).longValue();
            return this.f296op == Operator.EQ ? longValue == this.value : this.f296op == Operator.NE ? longValue != this.value : this.f296op == Operator.GE ? longValue >= this.value : this.f296op == Operator.GT ? longValue > this.value : this.f296op == Operator.LE ? longValue <= this.value : this.f296op == Operator.LT && longValue < this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class DoubleOpSegement implements Filter {

        /* renamed from: op */
        private final Operator f295op;
        private final String propertyName;
        private final long propertyNameHash;
        private final double value;

        public DoubleOpSegement(String str, double d, Operator operator) {
            this.propertyName = str;
            this.value = d;
            this.f295op = operator;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null || !(propertyValue instanceof Number)) {
                return false;
            }
            double doubleValue = ((Number) propertyValue).doubleValue();
            return this.f295op == Operator.EQ ? doubleValue == this.value : this.f295op == Operator.NE ? doubleValue != this.value : this.f295op == Operator.GE ? doubleValue >= this.value : this.f295op == Operator.GT ? doubleValue > this.value : this.f295op == Operator.LE ? doubleValue <= this.value : this.f295op == Operator.LT && doubleValue < this.value;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class SizeSegment implements Segment {
        public static final SizeSegment instance = new SizeSegment();

        SizeSegment() {
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Integer eval(JSONPath jSONPath, Object obj, Object obj2) {
            return Integer.valueOf(jSONPath.evalSize(obj2));
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class MatchSegement implements Filter {
        private final String[] containsValues;
        private final String endsWithValue;
        private final int minLength;
        private final boolean not;
        private final String propertyName;
        private final long propertyNameHash;
        private final String startsWithValue;

        public MatchSegement(String str, String str2, String str3, String[] strArr, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.startsWithValue = str2;
            this.endsWithValue = str3;
            this.containsValues = strArr;
            this.not = z;
            int length = str2 != null ? str2.length() + 0 : 0;
            length = str3 != null ? length + str3.length() : length;
            if (strArr != null) {
                for (String str4 : strArr) {
                    length += str4.length();
                }
            }
            this.minLength = length;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            int i;
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            String obj4 = propertyValue.toString();
            if (obj4.length() < this.minLength) {
                return this.not;
            }
            String str = this.startsWithValue;
            if (str == null) {
                i = 0;
            } else {
                if (!obj4.startsWith(str)) {
                    return this.not;
                }
                i = this.startsWithValue.length() + 0;
            }
            String[] strArr = this.containsValues;
            if (strArr != null) {
                for (String str2 : strArr) {
                    int indexOf = obj4.indexOf(str2, i);
                    if (indexOf == -1) {
                        return this.not;
                    }
                    i = indexOf + str2.length();
                }
            }
            String str3 = this.endsWithValue;
            if (str3 != null && !obj4.endsWith(str3)) {
                return this.not;
            }
            return !this.not;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class MaxSegment implements Segment {
        public static final MaxSegment instance = new MaxSegment();

        MaxSegment() {
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj instanceof Collection) {
                Object obj3 = null;
                for (Object obj4 : (Collection) obj) {
                    if (obj4 != null && (obj3 == null || JSONPath.compare(obj3, obj4) < 0)) {
                        obj3 = obj4;
                    }
                }
                return obj3;
            }
            throw new UnsupportedOperationException();
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class MinSegment implements Segment {
        public static final MinSegment instance = new MinSegment();

        MinSegment() {
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj instanceof Collection) {
                Object obj3 = null;
                for (Object obj4 : (Collection) obj) {
                    if (obj4 != null && (obj3 == null || JSONPath.compare(obj3, obj4) > 0)) {
                        obj3 = obj4;
                    }
                }
                return obj3;
            }
            throw new UnsupportedOperationException();
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class RlikeSegement implements Filter {
        private final boolean not;
        private final Pattern pattern;
        private final String propertyName;
        private final long propertyNameHash;

        public RlikeSegement(String str, String str2, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.pattern = Pattern.compile(str2);
            this.not = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            boolean matches = this.pattern.matcher(propertyValue.toString()).matches();
            return this.not ? !matches : matches;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class StringOpSegement implements Filter {

        /* renamed from: op */
        private final Operator f307op;
        private final String propertyName;
        private final long propertyNameHash;
        private final String value;

        public StringOpSegement(String str, String str2, Operator operator) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.value = str2;
            this.f307op = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (this.f307op == Operator.EQ) {
                return this.value.equals(propertyValue);
            }
            if (this.f307op == Operator.NE) {
                return !this.value.equals(propertyValue);
            }
            if (propertyValue == null) {
                return false;
            }
            int compareTo = this.value.compareTo(propertyValue.toString());
            return this.f307op == Operator.GE ? compareTo <= 0 : this.f307op == Operator.GT ? compareTo < 0 : this.f307op == Operator.LE ? compareTo >= 0 : this.f307op == Operator.LT && compareTo > 0;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class KeySetSegment implements Segment {
        public static final KeySetSegment instance = new KeySetSegment();

        KeySetSegment() {
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.evalKeySet(obj2);
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class PropertySegment implements Segment {
        private final boolean deep;
        private final String propertyName;
        private final long propertyNameHash;

        public PropertySegment(String str, boolean z) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.deep = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (this.deep) {
                ArrayList arrayList = new ArrayList();
                jSONPath.deepScan(obj2, this.propertyName, arrayList);
                return arrayList;
            }
            return jSONPath.getPropertyValue(obj2, this.propertyName, this.propertyNameHash);
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00b3 A[SYNTHETIC] */
        @Override // com.alibaba.fastjson.JSONPath.Segment
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            Object integerValue;
            Object integerValue2;
            JSONArray jSONArray;
            Object integerValue3;
            JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
            if (this.deep && context.object == null) {
                context.object = new JSONArray();
            }
            if (jSONLexerBase.token() == 14) {
                if ("*".equals(this.propertyName)) {
                    return;
                }
                jSONLexerBase.nextToken();
                if (this.deep) {
                    jSONArray = (JSONArray) context.object;
                } else {
                    jSONArray = new JSONArray();
                }
                while (true) {
                    int i = jSONLexerBase.token();
                    if (i == 12) {
                        boolean z = this.deep;
                        if (z) {
                            extract(jSONPath, defaultJSONParser, context);
                        } else {
                            int seekObjectToField = jSONLexerBase.seekObjectToField(this.propertyNameHash, z);
                            if (seekObjectToField == 3) {
                                int i2 = jSONLexerBase.token();
                                if (i2 == 2) {
                                    integerValue3 = jSONLexerBase.integerValue();
                                    jSONLexerBase.nextToken();
                                } else if (i2 == 4) {
                                    integerValue3 = jSONLexerBase.stringVal();
                                    jSONLexerBase.nextToken();
                                } else {
                                    integerValue3 = defaultJSONParser.parse();
                                }
                                jSONArray.add(integerValue3);
                                if (jSONLexerBase.token() == 13) {
                                    jSONLexerBase.nextToken();
                                } else {
                                    jSONLexerBase.skipObject(false);
                                }
                            } else if (seekObjectToField == -1) {
                                continue;
                            } else {
                                if (this.deep) {
                                    throw new UnsupportedOperationException(jSONLexerBase.info());
                                }
                                jSONLexerBase.skipObject(false);
                            }
                        }
                    } else if (i == 14) {
                        if (this.deep) {
                            extract(jSONPath, defaultJSONParser, context);
                        } else {
                            jSONLexerBase.skipObject(false);
                        }
                    } else {
                        switch (i) {
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                jSONLexerBase.nextToken();
                            default:
                                if (jSONLexerBase.token() != 15) {
                                    jSONLexerBase.nextToken();
                                    if (this.deep || jSONArray.size() <= 0) {
                                        return;
                                    }
                                    context.object = jSONArray;
                                    return;
                                }
                                if (jSONLexerBase.token() == 16) {
                                    jSONLexerBase.nextToken();
                                    break;
                                } else {
                                    throw new JSONException("illegal json : " + jSONLexerBase.info());
                                }
                        }
                    }
                    if (jSONLexerBase.token() != 15) {
                    }
                }
            } else {
                boolean z2 = this.deep;
                if (!z2) {
                    if (jSONLexerBase.seekObjectToField(this.propertyNameHash, z2) == 3 && context.eval) {
                        int i3 = jSONLexerBase.token();
                        if (i3 == 2) {
                            integerValue2 = jSONLexerBase.integerValue();
                            jSONLexerBase.nextToken(16);
                        } else if (i3 == 3) {
                            integerValue2 = jSONLexerBase.decimalValue();
                            jSONLexerBase.nextToken(16);
                        } else if (i3 == 4) {
                            integerValue2 = jSONLexerBase.stringVal();
                            jSONLexerBase.nextToken(16);
                        } else {
                            integerValue2 = defaultJSONParser.parse();
                        }
                        if (context.eval) {
                            context.object = integerValue2;
                            return;
                        }
                        return;
                    }
                    return;
                }
                while (true) {
                    int seekObjectToField2 = jSONLexerBase.seekObjectToField(this.propertyNameHash, this.deep);
                    if (seekObjectToField2 == -1) {
                        return;
                    }
                    if (seekObjectToField2 == 3) {
                        if (context.eval) {
                            int i4 = jSONLexerBase.token();
                            if (i4 == 2) {
                                integerValue = jSONLexerBase.integerValue();
                                jSONLexerBase.nextToken(16);
                            } else if (i4 == 3) {
                                integerValue = jSONLexerBase.decimalValue();
                                jSONLexerBase.nextToken(16);
                            } else if (i4 == 4) {
                                integerValue = jSONLexerBase.stringVal();
                                jSONLexerBase.nextToken(16);
                            } else {
                                integerValue = defaultJSONParser.parse();
                            }
                            if (context.eval) {
                                if (context.object instanceof List) {
                                    List list = (List) context.object;
                                    if (list.size() == 0 && (integerValue instanceof List)) {
                                        context.object = integerValue;
                                    } else {
                                        list.add(integerValue);
                                    }
                                } else {
                                    context.object = integerValue;
                                }
                            }
                        }
                    } else if (seekObjectToField2 == 1 || seekObjectToField2 == 2) {
                        extract(jSONPath, defaultJSONParser, context);
                    }
                }
            }
        }

        public void setValue(JSONPath jSONPath, Object obj, Object obj2) {
            if (this.deep) {
                jSONPath.deepSet(obj, this.propertyName, this.propertyNameHash, obj2);
            } else {
                jSONPath.setPropertyValue(obj, this.propertyName, this.propertyNameHash, obj2);
            }
        }

        public boolean remove(JSONPath jSONPath, Object obj) {
            return jSONPath.removePropertyValue(obj, this.propertyName);
        }
    }

    /* loaded from: classes.dex */
    public static class FilterSegement implements Segement {
        private final Filter filter;

        public FilterSegement(Filter filter) {
            this.filter = filter;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segement
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj2 == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            if (obj2 instanceof Iterable) {
                for (Object obj3 : (Iterable) obj2) {
                    if (this.filter.apply(jSONPath, obj, obj2, obj3)) {
                        jSONArray.add(obj3);
                    }
                }
                return jSONArray;
            }
            if (this.filter.apply(jSONPath, obj, obj2, obj2)) {
                return obj2;
            }
            return null;
        }
    }

    protected Object getArrayItem(Object obj, int i) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (i >= 0) {
                if (i < list.size()) {
                    return list.get(i);
                }
                return null;
            }
            if (Math.abs(i) <= list.size()) {
                return list.get(list.size() + i);
            }
            return null;
        }
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            if (i >= 0) {
                if (i < length) {
                    return Array.get(obj, i);
                }
                return null;
            }
            if (Math.abs(i) <= length) {
                return Array.get(obj, length + i);
            }
            return null;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(Integer.valueOf(i));
            return obj2 == null ? map.get(Integer.toString(i)) : obj2;
        }
        if (obj instanceof Collection) {
            int i2 = 0;
            for (Object obj3 : (Collection) obj) {
                if (i2 == i) {
                    return obj3;
                }
                i2++;
            }
            return null;
        }
        throw new UnsupportedOperationException();
    }

    public boolean setArrayItem(JSONPath jSONPath, Object obj, int i, Object obj2) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (i >= 0) {
                list.set(i, obj2);
            } else {
                list.set(list.size() + i, obj2);
            }
            return true;
        }
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            int length = Array.getLength(obj);
            if (i >= 0) {
                if (i < length) {
                    Array.set(obj, i, obj2);
                }
            } else if (Math.abs(i) <= length) {
                Array.set(obj, length + i, obj2);
            }
            return true;
        }
        throw new JSONPathException("unsupported set operation." + cls);
    }

    public boolean removeArrayItem(JSONPath jSONPath, Object obj, int i) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (i >= 0) {
                if (i >= list.size()) {
                    return false;
                }
                list.remove(i);
                return true;
            }
            int size = list.size() + i;
            if (size < 0) {
                return false;
            }
            list.remove(size);
            return true;
        }
        throw new JSONPathException("unsupported set operation." + obj.getClass());
    }

    protected Collection<Object> getPropertyValues(Object obj) {
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                return javaBeanSerializer.getFieldValues(obj);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path, e);
            }
        }
        if (obj instanceof Map) {
            return ((Map) obj).values();
        }
        throw new UnsupportedOperationException();
    }

    /* renamed from: eq */
    static boolean m83eq(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if (obj.getClass() == obj2.getClass()) {
            return obj.equals(obj2);
        }
        if (obj instanceof Number) {
            if (obj2 instanceof Number) {
                return eqNotNull((Number) obj, (Number) obj2);
            }
            return false;
        }
        return obj.equals(obj2);
    }

    static boolean eqNotNull(Number number, Number number2) {
        Class<?> cls = number.getClass();
        boolean isInt = isInt(cls);
        Class<?> cls2 = number2.getClass();
        boolean isInt2 = isInt(cls2);
        if (number instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) number;
            if (isInt2) {
                return bigDecimal.equals(BigDecimal.valueOf(number2.longValue()));
            }
        }
        if (isInt) {
            if (isInt2) {
                return number.longValue() == number2.longValue();
            }
            if (number2 instanceof BigInteger) {
                return BigInteger.valueOf(number.longValue()).equals((BigInteger) number);
            }
        }
        if (isInt2 && (number instanceof BigInteger)) {
            return ((BigInteger) number).equals(BigInteger.valueOf(number2.longValue()));
        }
        boolean isDouble = isDouble(cls);
        boolean isDouble2 = isDouble(cls2);
        return ((isDouble && isDouble2) || ((isDouble && isInt2) || (isDouble2 && isInt))) && number.doubleValue() == number2.doubleValue();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class MultiPropertySegment implements Segment {
        private final String[] propertyNames;
        private final long[] propertyNamesHash;

        public MultiPropertySegment(String[] strArr) {
            this.propertyNames = strArr;
            this.propertyNamesHash = new long[strArr.length];
            int i = 0;
            while (true) {
                long[] jArr = this.propertyNamesHash;
                if (i >= jArr.length) {
                    return;
                }
                jArr[i] = TypeUtils.fnv1a_64(strArr[i]);
                i++;
            }
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            ArrayList arrayList = new ArrayList(this.propertyNames.length);
            int i = 0;
            while (true) {
                String[] strArr = this.propertyNames;
                if (i >= strArr.length) {
                    return arrayList;
                }
                arrayList.add(jSONPath.getPropertyValue(obj2, strArr[i], this.propertyNamesHash[i]));
                i++;
            }
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            JSONArray jSONArray;
            Object integerValue;
            JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
            if (context.object == null) {
                jSONArray = new JSONArray();
                context.object = jSONArray;
            } else {
                jSONArray = (JSONArray) context.object;
            }
            for (int size = jSONArray.size(); size < this.propertyNamesHash.length; size++) {
                jSONArray.add(null);
            }
            do {
                int seekObjectToField = jSONLexerBase.seekObjectToField(this.propertyNamesHash);
                if (jSONLexerBase.matchStat != 3) {
                    return;
                }
                int i = jSONLexerBase.token();
                if (i == 2) {
                    integerValue = jSONLexerBase.integerValue();
                    jSONLexerBase.nextToken(16);
                } else if (i == 3) {
                    integerValue = jSONLexerBase.decimalValue();
                    jSONLexerBase.nextToken(16);
                } else if (i == 4) {
                    integerValue = jSONLexerBase.stringVal();
                    jSONLexerBase.nextToken(16);
                } else {
                    integerValue = defaultJSONParser.parse();
                }
                jSONArray.set(seekObjectToField, integerValue);
            } while (jSONLexerBase.token() == 16);
        }
    }

    protected static boolean isDouble(Class<?> cls) {
        return cls == Float.class || cls == Double.class;
    }

    protected static boolean isInt(Class<?> cls) {
        return cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class;
    }

    protected Object getPropertyValue(Object obj, String str, long j) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            Object obj2 = map.get(str);
            return (obj2 == null && SIZE == j) ? Integer.valueOf(map.size()) : obj2;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                return javaBeanSerializer.getFieldValue(obj, str, j, false);
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str, e);
            }
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (SIZE == j) {
                return Integer.valueOf(list.size());
            }
            JSONArray jSONArray = new JSONArray(list.size());
            for (int i = 0; i < list.size(); i++) {
                Object propertyValue = getPropertyValue(list.get(i), str, j);
                if (propertyValue instanceof Collection) {
                    jSONArray.addAll((Collection) propertyValue);
                } else if (propertyValue != null) {
                    jSONArray.add(propertyValue);
                }
            }
            return jSONArray;
        }
        if (obj instanceof Enum) {
            Enum r12 = (Enum) obj;
            if (-4270347329889690746L == j) {
                return r12.name();
            }
            if (-1014497654951707614L == j) {
                return Integer.valueOf(r12.ordinal());
            }
        }
        if (obj instanceof Calendar) {
            Calendar calendar = (Calendar) obj;
            if (8963398325558730460L == j) {
                return Integer.valueOf(calendar.get(1));
            }
            if (-811277319855450459L == j) {
                return Integer.valueOf(calendar.get(2));
            }
            if (-3851359326990528739L == j) {
                return Integer.valueOf(calendar.get(5));
            }
            if (4647432019745535567L == j) {
                return Integer.valueOf(calendar.get(11));
            }
            if (6607618197526598121L == j) {
                return Integer.valueOf(calendar.get(12));
            }
            if (-6586085717218287427L == j) {
                return Integer.valueOf(calendar.get(13));
            }
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class WildCardSegment implements Segment {
        public static final WildCardSegment instance = new WildCardSegment(false);
        public static final WildCardSegment instance_deep = new WildCardSegment(true);
        private boolean deep;

        private WildCardSegment(boolean z) {
            this.deep = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (!this.deep) {
                return jSONPath.getPropertyValues(obj2);
            }
            ArrayList arrayList = new ArrayList();
            jSONPath.deepGetPropertyValues(obj2, arrayList);
            return arrayList;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            if (context.eval) {
                Object parse = defaultJSONParser.parse();
                if (this.deep) {
                    ArrayList arrayList = new ArrayList();
                    jSONPath.deepGetPropertyValues(parse, arrayList);
                    context.object = arrayList;
                    return;
                } else {
                    if (parse instanceof JSONObject) {
                        Collection<Object> values = ((JSONObject) parse).values();
                        JSONArray jSONArray = new JSONArray(values.size());
                        Iterator<Object> it = values.iterator();
                        while (it.hasNext()) {
                            jSONArray.add(it.next());
                        }
                        context.object = jSONArray;
                        return;
                    }
                    if (parse instanceof JSONArray) {
                        context.object = parse;
                        return;
                    }
                }
            }
            throw new JSONException("TODO");
        }
    }

    protected void deepScan(Object obj, String str, List<Object> list) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.containsKey(str)) {
                list.add(map.get(str));
                return;
            }
            Iterator it = map.values().iterator();
            while (it.hasNext()) {
                deepScan(it.next(), str, list);
            }
            return;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                FieldSerializer fieldSerializer = javaBeanSerializer.getFieldSerializer(str);
                if (fieldSerializer != null) {
                    try {
                        try {
                            list.add(fieldSerializer.getPropertyValueDirect(obj));
                            return;
                        } catch (IllegalAccessException e) {
                            throw new JSONException("getFieldValue error." + str, e);
                        }
                    } catch (InvocationTargetException e2) {
                        throw new JSONException("getFieldValue error." + str, e2);
                    }
                }
                Iterator<Object> it2 = javaBeanSerializer.getFieldValues(obj).iterator();
                while (it2.hasNext()) {
                    deepScan(it2.next(), str, list);
                }
                return;
            } catch (Exception e3) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str, e3);
            }
        }
        if (obj instanceof List) {
            List list2 = (List) obj;
            for (int i = 0; i < list2.size(); i++) {
                deepScan(list2.get(i), str, list);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class ArrayAccessSegment implements Segment {
        private final int index;

        public ArrayAccessSegment(int i) {
            this.index = i;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getArrayItem(obj2, this.index);
        }

        public boolean setValue(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.setArrayItem(jSONPath, obj, this.index, obj2);
        }

        public boolean remove(JSONPath jSONPath, Object obj) {
            return jSONPath.removeArrayItem(jSONPath, obj, this.index);
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            if (((JSONLexerBase) defaultJSONParser.lexer).seekArrayToItem(this.index) && context.eval) {
                context.object = defaultJSONParser.parse();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class MultiIndexSegment implements Segment {
        private final int[] indexes;

        public MultiIndexSegment(int[] iArr) {
            this.indexes = iArr;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            JSONArray jSONArray = new JSONArray(this.indexes.length);
            int i = 0;
            while (true) {
                int[] iArr = this.indexes;
                if (i >= iArr.length) {
                    return jSONArray;
                }
                jSONArray.add(jSONPath.getArrayItem(obj2, iArr[i]));
                i++;
            }
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            if (context.eval) {
                Object parse = defaultJSONParser.parse();
                if (parse instanceof List) {
                    int[] iArr = this.indexes;
                    int[] iArr2 = new int[iArr.length];
                    System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
                    List list = (List) parse;
                    if (iArr2[0] >= 0) {
                        for (int size = list.size() - 1; size >= 0; size--) {
                            if (Arrays.binarySearch(iArr2, size) < 0) {
                                list.remove(size);
                            }
                        }
                        context.object = list;
                        return;
                    }
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    protected void deepSet(Object obj, String str, long j, Object obj2) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.containsKey(str)) {
                map.get(str);
                map.put(str, obj2);
                return;
            } else {
                Iterator it = map.values().iterator();
                while (it.hasNext()) {
                    deepSet(it.next(), str, j, obj2);
                }
                return;
            }
        }
        Class<?> cls = obj.getClass();
        JavaBeanDeserializer javaBeanDeserializer = getJavaBeanDeserializer(cls);
        if (javaBeanDeserializer != null) {
            try {
                FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(str);
                if (fieldDeserializer != null) {
                    fieldDeserializer.setValue(obj, obj2);
                    return;
                }
                Iterator<Object> it2 = getJavaBeanSerializer(cls).getObjectFieldValues(obj).iterator();
                while (it2.hasNext()) {
                    deepSet(it2.next(), str, j, obj2);
                }
                return;
            } catch (Exception e) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str, e);
            }
        }
        if (obj instanceof List) {
            List list = (List) obj;
            for (int i = 0; i < list.size(); i++) {
                deepSet(list.get(i), str, j, obj2);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class RangeSegment implements Segment {
        private final int end;
        private final int start;
        private final int step;

        public RangeSegment(int i, int i2, int i3) {
            this.start = i;
            this.end = i2;
            this.step = i3;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            int intValue = SizeSegment.instance.eval(jSONPath, obj, obj2).intValue();
            int i = this.start;
            if (i < 0) {
                i += intValue;
            }
            int i2 = this.end;
            if (i2 < 0) {
                i2 += intValue;
            }
            int i3 = ((i2 - i) / this.step) + 1;
            if (i3 == -1) {
                return null;
            }
            ArrayList arrayList = new ArrayList(i3);
            while (i <= i2 && i < intValue) {
                arrayList.add(jSONPath.getArrayItem(obj2, i));
                i += this.step;
            }
            return arrayList;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    protected boolean setPropertyValue(Object obj, String str, long j, Object obj2) {
        if (obj instanceof Map) {
            ((Map) obj).put(str, obj2);
            return true;
        }
        if (obj instanceof List) {
            for (Object obj3 : (List) obj) {
                if (obj3 != null) {
                    setPropertyValue(obj3, str, j, obj2);
                }
            }
            return true;
        }
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(obj.getClass());
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (javaBeanDeserializer != null) {
            FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(j);
            if (fieldDeserializer == null) {
                return false;
            }
            fieldDeserializer.setValue(obj, obj2);
            return true;
        }
        throw new UnsupportedOperationException();
    }

    protected boolean removePropertyValue(Object obj, String str) {
        if (obj instanceof Map) {
            return ((Map) obj).remove(str) != null;
        }
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(obj.getClass());
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (javaBeanDeserializer != null) {
            FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(str);
            if (fieldDeserializer == null) {
                return false;
            }
            fieldDeserializer.setValue(obj, (String) null);
            return true;
        }
        throw new UnsupportedOperationException();
    }

    protected JavaBeanSerializer getJavaBeanSerializer(Class<?> cls) {
        ObjectSerializer objectWriter = this.serializeConfig.getObjectWriter(cls);
        if (objectWriter instanceof JavaBeanSerializer) {
            return (JavaBeanSerializer) objectWriter;
        }
        return null;
    }

    protected JavaBeanDeserializer getJavaBeanDeserializer(Class<?> cls) {
        ObjectDeserializer deserializer = this.parserConfig.getDeserializer(cls);
        if (deserializer instanceof JavaBeanDeserializer) {
            return (JavaBeanDeserializer) deserializer;
        }
        return null;
    }

    int evalSize(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        }
        if (obj instanceof Map) {
            int i = 0;
            Iterator it = ((Map) obj).values().iterator();
            while (it.hasNext()) {
                if (it.next() != null) {
                    i++;
                }
            }
            return i;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer == null) {
            return -1;
        }
        try {
            return javaBeanSerializer.getSize(obj);
        } catch (Exception e) {
            throw new JSONPathException("evalSize error : " + this.path, e);
        }
    }

    @Override // com.alibaba.fastjson.JSONAware
    public String toJSONString() {
        return JSON.toJSONString(this.path);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class RefOpSegement implements Filter {

        /* renamed from: op */
        private final Operator f305op;
        private final String propertyName;
        private final long propertyNameHash;
        private final Segment refSgement;

        public RefOpSegement(String str, Segment segment, Operator operator) {
            this.propertyName = str;
            this.refSgement = segment;
            this.f305op = operator;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null || !(propertyValue instanceof Number)) {
                return false;
            }
            Object eval = this.refSgement.eval(jSONPath, obj, obj);
            if ((eval instanceof Integer) || (eval instanceof Long) || (eval instanceof Short) || (eval instanceof Byte)) {
                long longExtractValue = TypeUtils.longExtractValue((Number) eval);
                if ((propertyValue instanceof Integer) || (propertyValue instanceof Long) || (propertyValue instanceof Short) || (propertyValue instanceof Byte)) {
                    long longExtractValue2 = TypeUtils.longExtractValue((Number) propertyValue);
                    switch (this.f305op) {
                        case EQ:
                            return longExtractValue2 == longExtractValue;
                        case NE:
                            return longExtractValue2 != longExtractValue;
                        case GE:
                            return longExtractValue2 >= longExtractValue;
                        case GT:
                            return longExtractValue2 > longExtractValue;
                        case LE:
                            return longExtractValue2 <= longExtractValue;
                        case LT:
                            return longExtractValue2 < longExtractValue;
                    }
                }
                if (propertyValue instanceof BigDecimal) {
                    int compareTo = BigDecimal.valueOf(longExtractValue).compareTo((BigDecimal) propertyValue);
                    switch (this.f305op) {
                        case EQ:
                            return compareTo == 0;
                        case NE:
                            return compareTo != 0;
                        case GE:
                            return compareTo <= 0;
                        case GT:
                            return compareTo < 0;
                        case LE:
                            return compareTo >= 0;
                        case LT:
                            return compareTo > 0;
                        default:
                            return false;
                    }
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class RegMatchSegement implements Filter {

        /* renamed from: op */
        private final Operator f306op;
        private final Pattern pattern;
        private final String propertyName;
        private final long propertyNameHash;

        public RegMatchSegement(String str, Pattern pattern, Operator operator) {
            this.propertyName = str;
            this.propertyNameHash = TypeUtils.fnv1a_64(str);
            this.pattern = pattern;
            this.f306op = operator;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, this.propertyNameHash);
            if (propertyValue == null) {
                return false;
            }
            return this.pattern.matcher(propertyValue.toString()).matches();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class FilterSegment implements Segment {
        private final Filter filter;

        public FilterSegment(Filter filter) {
            this.filter = filter;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj2 == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            if (obj2 instanceof Iterable) {
                for (Object obj3 : (Iterable) obj2) {
                    if (this.filter.apply(jSONPath, obj, obj2, obj3)) {
                        jSONArray.add(obj3);
                    }
                }
                return jSONArray;
            }
            if (this.filter.apply(jSONPath, obj, obj2, obj2)) {
                return obj2;
            }
            return null;
        }

        @Override // com.alibaba.fastjson.JSONPath.Segment
        public void extract(JSONPath jSONPath, DefaultJSONParser defaultJSONParser, Context context) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class FilterGroup implements Filter {
        private boolean and;
        private List<Filter> fitlers = new ArrayList(2);

        public FilterGroup(Filter filter, Filter filter2, boolean z) {
            this.fitlers.add(filter);
            this.fitlers.add(filter2);
            this.and = z;
        }

        @Override // com.alibaba.fastjson.JSONPath.Filter
        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            if (this.and) {
                Iterator<Filter> it = this.fitlers.iterator();
                while (it.hasNext()) {
                    if (!it.next().apply(jSONPath, obj, obj2, obj3)) {
                        return false;
                    }
                }
                return true;
            }
            Iterator<Filter> it2 = this.fitlers.iterator();
            while (it2.hasNext()) {
                if (it2.next().apply(jSONPath, obj, obj2, obj3)) {
                    return true;
                }
            }
            return false;
        }
    }
}
