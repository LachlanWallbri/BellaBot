package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import com.alibaba.fastjson.parser.deserializer.OptionalCodec;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.ServiceLoader;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SerializeConfig {
    private boolean asm;
    private ASMSerializerFactory asmFactory;
    private final boolean fieldBased;
    public PropertyNamingStrategy propertyNamingStrategy;
    private final IdentityHashMap<Type, ObjectSerializer> serializers;
    protected String typeKey;
    public static final SerializeConfig globalInstance = new SerializeConfig();
    private static boolean awtError = false;
    private static boolean jdk8Error = false;
    private static boolean oracleJdbcError = false;
    private static boolean springfoxError = false;
    private static boolean guavaError = false;
    private static boolean jsonnullError = false;

    public String getTypeKey() {
        return this.typeKey;
    }

    public void setTypeKey(String str) {
        this.typeKey = str;
    }

    private final JavaBeanSerializer createASMSerializer(SerializeBeanInfo serializeBeanInfo) throws Exception {
        JavaBeanSerializer createJavaBeanSerializer = this.asmFactory.createJavaBeanSerializer(serializeBeanInfo);
        for (int i = 0; i < createJavaBeanSerializer.sortedGetters.length; i++) {
            Class<?> cls = createJavaBeanSerializer.sortedGetters[i].fieldInfo.fieldClass;
            if (cls.isEnum() && !(getObjectWriter(cls) instanceof EnumSerializer)) {
                createJavaBeanSerializer.writeDirect = false;
            }
        }
        return createJavaBeanSerializer;
    }

    public final ObjectSerializer createJavaBeanSerializer(Class<?> cls) {
        SerializeBeanInfo buildBeanInfo = TypeUtils.buildBeanInfo(cls, null, this.propertyNamingStrategy, this.fieldBased);
        if (buildBeanInfo.fields.length == 0 && Iterable.class.isAssignableFrom(cls)) {
            return MiscCodec.instance;
        }
        return createJavaBeanSerializer(buildBeanInfo);
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x012b, code lost:
    
        r0 = createASMSerializer(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x012f, code lost:
    
        if (r0 == null) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0131, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0132, code lost:
    
        r14 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0149, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create asm serializer error, class " + r0, r14);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ObjectSerializer createJavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        Method method;
        JSONType jSONType = serializeBeanInfo.jsonType;
        boolean z = false;
        if (jSONType != null) {
            Class<?> serializer = jSONType.serializer();
            if (serializer != Void.class) {
                try {
                    Object newInstance = serializer.newInstance();
                    if (newInstance instanceof ObjectSerializer) {
                        return (ObjectSerializer) newInstance;
                    }
                } catch (Throwable unused) {
                }
            }
            if (!jSONType.asm()) {
                this.asm = false;
            }
            for (SerializerFeature serializerFeature : jSONType.serialzeFeatures()) {
                if (SerializerFeature.WriteNonStringValueAsString == serializerFeature || SerializerFeature.WriteEnumUsingToString == serializerFeature || SerializerFeature.NotWriteDefaultValue == serializerFeature) {
                    this.asm = false;
                    break;
                }
            }
        }
        Class<?> cls = serializeBeanInfo.beanType;
        if (!Modifier.isPublic(serializeBeanInfo.beanType.getModifiers())) {
            return new JavaBeanSerializer(serializeBeanInfo);
        }
        boolean z2 = this.asm && !this.fieldBased;
        if ((z2 && this.asmFactory.classLoader.isExternalClass(cls)) || cls == Serializable.class || cls == Object.class) {
            z2 = false;
        }
        if (z2 && !ASMUtils.checkName(cls.getSimpleName())) {
            z2 = false;
        }
        if (z2 && serializeBeanInfo.beanType.isInterface()) {
            z2 = false;
        }
        if (z2) {
            for (FieldInfo fieldInfo : serializeBeanInfo.fields) {
                Field field = fieldInfo.field;
                if ((field != null && !field.getType().equals(fieldInfo.fieldClass)) || ((method = fieldInfo.method) != null && !method.getReturnType().equals(fieldInfo.fieldClass))) {
                    break;
                }
                JSONField annotation = fieldInfo.getAnnotation();
                if (annotation != null) {
                    String format = annotation.format();
                    if ((format.length() != 0 && (fieldInfo.fieldClass != String.class || !"trim".equals(format))) || !ASMUtils.checkName(annotation.name()) || annotation.jsonDirect() || annotation.serializeUsing() != Void.class || annotation.unwrapped()) {
                        break;
                    }
                    for (SerializerFeature serializerFeature2 : annotation.serialzeFeatures()) {
                        if (SerializerFeature.WriteNonStringValueAsString == serializerFeature2 || SerializerFeature.WriteEnumUsingToString == serializerFeature2 || SerializerFeature.NotWriteDefaultValue == serializerFeature2 || SerializerFeature.WriteClassName == serializerFeature2) {
                            z2 = false;
                            break;
                        }
                    }
                    if (TypeUtils.isAnnotationPresentOneToMany(method) || TypeUtils.isAnnotationPresentManyToMany(method)) {
                        z = true;
                        break;
                    }
                }
            }
        }
        z = z2;
        return new JavaBeanSerializer(serializeBeanInfo);
    }

    public boolean isAsmEnable() {
        return this.asm;
    }

    public void setAsmEnable(boolean z) {
        if (ASMUtils.IS_ANDROID) {
            return;
        }
        this.asm = z;
    }

    public static SerializeConfig getGlobalInstance() {
        return globalInstance;
    }

    public SerializeConfig() {
        this(8192);
    }

    public SerializeConfig(boolean z) {
        this(8192, z);
    }

    public SerializeConfig(int i) {
        this(i, false);
    }

    public SerializeConfig(int i, boolean z) {
        this.asm = !ASMUtils.IS_ANDROID;
        this.typeKey = JSON.DEFAULT_TYPE_KEY;
        this.fieldBased = z;
        this.serializers = new IdentityHashMap<>(i);
        try {
            if (this.asm) {
                this.asmFactory = new ASMSerializerFactory();
            }
        } catch (Throwable unused) {
            this.asm = false;
        }
        put(Boolean.class, (ObjectSerializer) BooleanCodec.instance);
        put(Character.class, (ObjectSerializer) CharacterCodec.instance);
        put(Byte.class, (ObjectSerializer) IntegerCodec.instance);
        put(Short.class, (ObjectSerializer) IntegerCodec.instance);
        put(Integer.class, (ObjectSerializer) IntegerCodec.instance);
        put(Long.class, (ObjectSerializer) LongCodec.instance);
        put(Float.class, (ObjectSerializer) FloatCodec.instance);
        put(Double.class, (ObjectSerializer) DoubleSerializer.instance);
        put(BigDecimal.class, (ObjectSerializer) BigDecimalCodec.instance);
        put(BigInteger.class, (ObjectSerializer) BigIntegerCodec.instance);
        put(String.class, (ObjectSerializer) StringCodec.instance);
        put(byte[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(short[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(int[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(long[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(float[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(double[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(boolean[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(char[].class, (ObjectSerializer) PrimitiveArraySerializer.instance);
        put(Object[].class, (ObjectSerializer) ObjectArrayCodec.instance);
        put(Class.class, (ObjectSerializer) MiscCodec.instance);
        put(SimpleDateFormat.class, (ObjectSerializer) MiscCodec.instance);
        put(Currency.class, (ObjectSerializer) new MiscCodec());
        put(TimeZone.class, (ObjectSerializer) MiscCodec.instance);
        put(InetAddress.class, (ObjectSerializer) MiscCodec.instance);
        put(Inet4Address.class, (ObjectSerializer) MiscCodec.instance);
        put(Inet6Address.class, (ObjectSerializer) MiscCodec.instance);
        put(InetSocketAddress.class, (ObjectSerializer) MiscCodec.instance);
        put(File.class, (ObjectSerializer) MiscCodec.instance);
        put(Appendable.class, (ObjectSerializer) AppendableSerializer.instance);
        put(StringBuffer.class, (ObjectSerializer) AppendableSerializer.instance);
        put(StringBuilder.class, (ObjectSerializer) AppendableSerializer.instance);
        put(Charset.class, (ObjectSerializer) ToStringSerializer.instance);
        put(Pattern.class, (ObjectSerializer) ToStringSerializer.instance);
        put(Locale.class, (ObjectSerializer) ToStringSerializer.instance);
        put(URI.class, (ObjectSerializer) ToStringSerializer.instance);
        put(URL.class, (ObjectSerializer) ToStringSerializer.instance);
        put(UUID.class, (ObjectSerializer) ToStringSerializer.instance);
        put(AtomicBoolean.class, (ObjectSerializer) AtomicCodec.instance);
        put(AtomicInteger.class, (ObjectSerializer) AtomicCodec.instance);
        put(AtomicLong.class, (ObjectSerializer) AtomicCodec.instance);
        put(AtomicReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put(AtomicIntegerArray.class, (ObjectSerializer) AtomicCodec.instance);
        put(AtomicLongArray.class, (ObjectSerializer) AtomicCodec.instance);
        put(WeakReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put(SoftReference.class, (ObjectSerializer) ReferenceCodec.instance);
        put(LinkedList.class, (ObjectSerializer) CollectionCodec.instance);
    }

    public void addFilter(Class<?> cls, SerializeFilter serializeFilter) {
        Object objectWriter = getObjectWriter(cls);
        if (objectWriter instanceof SerializeFilterable) {
            SerializeFilterable serializeFilterable = (SerializeFilterable) objectWriter;
            if (this != globalInstance && serializeFilterable == MapSerializer.instance) {
                MapSerializer mapSerializer = new MapSerializer();
                put((Type) cls, (ObjectSerializer) mapSerializer);
                mapSerializer.addFilter(serializeFilter);
                return;
            }
            serializeFilterable.addFilter(serializeFilter);
        }
    }

    public void config(Class<?> cls, SerializerFeature serializerFeature, boolean z) {
        ObjectSerializer objectWriter = getObjectWriter(cls, false);
        if (objectWriter == null) {
            SerializeBeanInfo buildBeanInfo = TypeUtils.buildBeanInfo(cls, null, this.propertyNamingStrategy);
            if (z) {
                buildBeanInfo.features = serializerFeature.mask | buildBeanInfo.features;
            } else {
                buildBeanInfo.features = (~serializerFeature.mask) & buildBeanInfo.features;
            }
            put((Type) cls, createJavaBeanSerializer(buildBeanInfo));
            return;
        }
        if (objectWriter instanceof JavaBeanSerializer) {
            SerializeBeanInfo serializeBeanInfo = ((JavaBeanSerializer) objectWriter).beanInfo;
            int i = serializeBeanInfo.features;
            if (z) {
                serializeBeanInfo.features = serializerFeature.mask | serializeBeanInfo.features;
            } else {
                serializeBeanInfo.features = (~serializerFeature.mask) & serializeBeanInfo.features;
            }
            if (i == serializeBeanInfo.features || objectWriter.getClass() == JavaBeanSerializer.class) {
                return;
            }
            put((Type) cls, createJavaBeanSerializer(serializeBeanInfo));
        }
    }

    public ObjectSerializer getObjectWriter(Class<?> cls) {
        return getObjectWriter(cls, true);
    }

    private ObjectSerializer getObjectWriter(Class<?> cls, boolean z) {
        ClassLoader classLoader;
        ObjectSerializer objectSerializer = this.serializers.get(cls);
        if (objectSerializer == null) {
            try {
                for (Object obj : ServiceLoader.load(AutowiredObjectSerializer.class, Thread.currentThread().getContextClassLoader())) {
                    if (obj instanceof AutowiredObjectSerializer) {
                        AutowiredObjectSerializer autowiredObjectSerializer = (AutowiredObjectSerializer) obj;
                        Iterator<Type> it = autowiredObjectSerializer.getAutowiredFor().iterator();
                        while (it.hasNext()) {
                            put(it.next(), (ObjectSerializer) autowiredObjectSerializer);
                        }
                    }
                }
            } catch (ClassCastException unused) {
            }
            objectSerializer = this.serializers.get(cls);
        }
        if (objectSerializer == null && (classLoader = JSON.class.getClassLoader()) != Thread.currentThread().getContextClassLoader()) {
            try {
                for (Object obj2 : ServiceLoader.load(AutowiredObjectSerializer.class, classLoader)) {
                    if (obj2 instanceof AutowiredObjectSerializer) {
                        AutowiredObjectSerializer autowiredObjectSerializer2 = (AutowiredObjectSerializer) obj2;
                        Iterator<Type> it2 = autowiredObjectSerializer2.getAutowiredFor().iterator();
                        while (it2.hasNext()) {
                            put(it2.next(), (ObjectSerializer) autowiredObjectSerializer2);
                        }
                    }
                }
            } catch (ClassCastException unused2) {
            }
            objectSerializer = this.serializers.get(cls);
        }
        if (objectSerializer != null) {
            return objectSerializer;
        }
        String name = cls.getName();
        if (Map.class.isAssignableFrom(cls)) {
            objectSerializer = MapSerializer.instance;
            put((Type) cls, objectSerializer);
        } else if (List.class.isAssignableFrom(cls)) {
            objectSerializer = ListSerializer.instance;
            put((Type) cls, objectSerializer);
        } else if (Collection.class.isAssignableFrom(cls)) {
            objectSerializer = CollectionCodec.instance;
            put((Type) cls, objectSerializer);
        } else if (Date.class.isAssignableFrom(cls)) {
            objectSerializer = DateCodec.instance;
            put((Type) cls, objectSerializer);
        } else if (JSONAware.class.isAssignableFrom(cls)) {
            objectSerializer = JSONAwareSerializer.instance;
            put((Type) cls, objectSerializer);
        } else if (JSONSerializable.class.isAssignableFrom(cls)) {
            objectSerializer = JSONSerializableSerializer.instance;
            put((Type) cls, objectSerializer);
        } else if (JSONStreamAware.class.isAssignableFrom(cls)) {
            objectSerializer = MiscCodec.instance;
            put((Type) cls, objectSerializer);
        } else if (cls.isEnum()) {
            JSONType jSONType = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
            if (jSONType != null && jSONType.serializeEnumAsJavaBean()) {
                objectSerializer = createJavaBeanSerializer(cls);
                put((Type) cls, objectSerializer);
            } else {
                objectSerializer = EnumSerializer.instance;
                put((Type) cls, objectSerializer);
            }
        } else {
            Class<? super Object> superclass = cls.getSuperclass();
            if (superclass != null && superclass.isEnum()) {
                JSONType jSONType2 = (JSONType) TypeUtils.getAnnotation(superclass, JSONType.class);
                if (jSONType2 != null && jSONType2.serializeEnumAsJavaBean()) {
                    objectSerializer = createJavaBeanSerializer(cls);
                    put((Type) cls, objectSerializer);
                } else {
                    objectSerializer = EnumSerializer.instance;
                    put((Type) cls, objectSerializer);
                }
            } else if (cls.isArray()) {
                Class<?> componentType = cls.getComponentType();
                ObjectSerializer arraySerializer = new ArraySerializer(componentType, getObjectWriter(componentType));
                put((Type) cls, arraySerializer);
                objectSerializer = arraySerializer;
            } else {
                Class<?> cls2 = null;
                if (Throwable.class.isAssignableFrom(cls)) {
                    SerializeBeanInfo buildBeanInfo = TypeUtils.buildBeanInfo(cls, null, this.propertyNamingStrategy);
                    buildBeanInfo.features |= SerializerFeature.WriteClassName.mask;
                    ObjectSerializer javaBeanSerializer = new JavaBeanSerializer(buildBeanInfo);
                    put((Type) cls, javaBeanSerializer);
                    objectSerializer = javaBeanSerializer;
                } else if (TimeZone.class.isAssignableFrom(cls) || Map.Entry.class.isAssignableFrom(cls)) {
                    objectSerializer = MiscCodec.instance;
                    put((Type) cls, objectSerializer);
                } else if (Appendable.class.isAssignableFrom(cls)) {
                    objectSerializer = AppendableSerializer.instance;
                    put((Type) cls, objectSerializer);
                } else if (Charset.class.isAssignableFrom(cls)) {
                    objectSerializer = ToStringSerializer.instance;
                    put((Type) cls, objectSerializer);
                } else if (Enumeration.class.isAssignableFrom(cls)) {
                    objectSerializer = EnumerationSerializer.instance;
                    put((Type) cls, objectSerializer);
                } else if (Calendar.class.isAssignableFrom(cls) || XMLGregorianCalendar.class.isAssignableFrom(cls)) {
                    objectSerializer = CalendarCodec.instance;
                    put((Type) cls, objectSerializer);
                } else if (Clob.class.isAssignableFrom(cls)) {
                    objectSerializer = ClobSeriliazer.instance;
                    put((Type) cls, objectSerializer);
                } else if (TypeUtils.isPath(cls)) {
                    objectSerializer = ToStringSerializer.instance;
                    put((Type) cls, objectSerializer);
                } else if (Iterator.class.isAssignableFrom(cls)) {
                    objectSerializer = MiscCodec.instance;
                    put((Type) cls, objectSerializer);
                } else {
                    int i = 0;
                    if (name.startsWith("java.awt.") && AwtCodec.support(cls) && !awtError) {
                        try {
                            String[] strArr = {"java.awt.Color", "java.awt.Font", "java.awt.Point", "java.awt.Rectangle"};
                            for (int i2 = 0; i2 < 4; i2++) {
                                String str = strArr[i2];
                                if (str.equals(name)) {
                                    Type cls3 = Class.forName(str);
                                    objectSerializer = AwtCodec.instance;
                                    put(cls3, objectSerializer);
                                    return objectSerializer;
                                }
                            }
                        } catch (Throwable unused3) {
                            awtError = true;
                        }
                    }
                    if (!jdk8Error && (name.startsWith("java.time.") || name.startsWith("java.util.Optional") || name.equals("java.util.concurrent.atomic.LongAdder") || name.equals("java.util.concurrent.atomic.DoubleAdder"))) {
                        try {
                            String[] strArr2 = {"java.time.LocalDateTime", "java.time.LocalDate", "java.time.LocalTime", "java.time.ZonedDateTime", "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZoneOffset", "java.time.ZoneRegion", "java.time.Period", "java.time.Duration", "java.time.Instant"};
                            for (int i3 = 0; i3 < 11; i3++) {
                                String str2 = strArr2[i3];
                                if (str2.equals(name)) {
                                    Type cls4 = Class.forName(str2);
                                    ObjectSerializer objectSerializer2 = Jdk8DateCodec.instance;
                                    put(cls4, objectSerializer2);
                                    return objectSerializer2;
                                }
                            }
                            String[] strArr3 = {"java.util.Optional", "java.util.OptionalDouble", "java.util.OptionalInt", "java.util.OptionalLong"};
                            for (int i4 = 0; i4 < 4; i4++) {
                                String str3 = strArr3[i4];
                                if (str3.equals(name)) {
                                    Type cls5 = Class.forName(str3);
                                    ObjectSerializer objectSerializer3 = OptionalCodec.instance;
                                    put(cls5, objectSerializer3);
                                    return objectSerializer3;
                                }
                            }
                            String[] strArr4 = {"java.util.concurrent.atomic.LongAdder", "java.util.concurrent.atomic.DoubleAdder"};
                            for (int i5 = 0; i5 < 2; i5++) {
                                String str4 = strArr4[i5];
                                if (str4.equals(name)) {
                                    Type cls6 = Class.forName(str4);
                                    ObjectSerializer objectSerializer4 = AdderSerializer.instance;
                                    put(cls6, objectSerializer4);
                                    return objectSerializer4;
                                }
                            }
                        } catch (Throwable unused4) {
                            jdk8Error = true;
                        }
                    }
                    if (!oracleJdbcError && name.startsWith("oracle.sql.")) {
                        try {
                            String[] strArr5 = {"oracle.sql.DATE", "oracle.sql.TIMESTAMP"};
                            for (int i6 = 0; i6 < 2; i6++) {
                                String str5 = strArr5[i6];
                                if (str5.equals(name)) {
                                    Type cls7 = Class.forName(str5);
                                    objectSerializer = DateCodec.instance;
                                    put(cls7, objectSerializer);
                                    return objectSerializer;
                                }
                            }
                        } catch (Throwable unused5) {
                            oracleJdbcError = true;
                        }
                    }
                    if (!springfoxError && name.equals("springfox.documentation.spring.web.json.Json")) {
                        try {
                            Type cls8 = Class.forName("springfox.documentation.spring.web.json.Json");
                            objectSerializer = SwaggerJsonSerializer.instance;
                            put(cls8, objectSerializer);
                            return objectSerializer;
                        } catch (ClassNotFoundException unused6) {
                            springfoxError = true;
                        }
                    }
                    if (!guavaError && name.startsWith("com.google.common.collect.")) {
                        try {
                            String[] strArr6 = {"com.google.common.collect.HashMultimap", "com.google.common.collect.LinkedListMultimap", "com.google.common.collect.ArrayListMultimap", "com.google.common.collect.TreeMultimap"};
                            for (int i7 = 0; i7 < 4; i7++) {
                                String str6 = strArr6[i7];
                                if (str6.equals(name)) {
                                    Type cls9 = Class.forName(str6);
                                    objectSerializer = GuavaCodec.instance;
                                    put(cls9, objectSerializer);
                                    return objectSerializer;
                                }
                            }
                        } catch (ClassNotFoundException unused7) {
                            guavaError = true;
                        }
                    }
                    if (!jsonnullError && name.equals("net.sf.json.JSONNull")) {
                        try {
                            Type cls10 = Class.forName("net.sf.json.JSONNull");
                            objectSerializer = MiscCodec.instance;
                            put(cls10, objectSerializer);
                            return objectSerializer;
                        } catch (ClassNotFoundException unused8) {
                            jsonnullError = true;
                        }
                    }
                    Class<?>[] interfaces = cls.getInterfaces();
                    if (interfaces.length == 1 && interfaces[0].isAnnotation()) {
                        return AnnotationSerializer.instance;
                    }
                    if (TypeUtils.isProxy(cls)) {
                        ObjectSerializer objectWriter = getObjectWriter(cls.getSuperclass());
                        put((Type) cls, objectWriter);
                        return objectWriter;
                    }
                    if (Proxy.isProxyClass(cls)) {
                        if (interfaces.length == 2) {
                            cls2 = interfaces[1];
                        } else {
                            int length = interfaces.length;
                            Class<?> cls11 = null;
                            while (true) {
                                if (i >= length) {
                                    cls2 = cls11;
                                    break;
                                }
                                Class<?> cls12 = interfaces[i];
                                if (!cls12.getName().startsWith("org.springframework.aop.")) {
                                    if (cls11 != null) {
                                        break;
                                    }
                                    cls11 = cls12;
                                }
                                i++;
                            }
                        }
                        if (cls2 != null) {
                            ObjectSerializer objectWriter2 = getObjectWriter(cls2);
                            put((Type) cls, objectWriter2);
                            return objectWriter2;
                        }
                    }
                    if (z) {
                        objectSerializer = createJavaBeanSerializer(cls);
                        put((Type) cls, objectSerializer);
                    }
                }
            }
        }
        return objectSerializer == null ? this.serializers.get(cls) : objectSerializer;
    }

    public final ObjectSerializer get(Type type) {
        return this.serializers.get(type);
    }

    public boolean put(Object obj, Object obj2) {
        return put((Type) obj, (ObjectSerializer) obj2);
    }

    public boolean put(Type type, ObjectSerializer objectSerializer) {
        return this.serializers.put(type, objectSerializer);
    }

    public void configEnumAsJavaBean(Class<? extends Enum>... clsArr) {
        for (Class<? extends Enum> cls : clsArr) {
            put((Type) cls, createJavaBeanSerializer(cls));
        }
    }

    public void setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this.propertyNamingStrategy = propertyNamingStrategy;
    }
}
