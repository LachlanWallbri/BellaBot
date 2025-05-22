package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] hashArray;
    private transient short[] hashArrayMapping;
    private transient long[] smartMatchHashArray;
    private transient short[] smartMatchHashArrayMapping;
    protected final FieldDeserializer[] sortedFieldDeserializers;

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this(parserConfig, cls, cls);
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, JavaBeanInfo.build(cls, type, parserConfig.propertyNamingStrategy, parserConfig.fieldBased, parserConfig.compatibleWithJavaBean));
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo) {
        this.clazz = javaBeanInfo.clazz;
        this.beanInfo = javaBeanInfo;
        this.sortedFieldDeserializers = new FieldDeserializer[javaBeanInfo.sortedFields.length];
        int length = javaBeanInfo.sortedFields.length;
        HashMap hashMap = null;
        for (int i = 0; i < length; i++) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i];
            FieldDeserializer createFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, javaBeanInfo, fieldInfo);
            this.sortedFieldDeserializers[i] = createFieldDeserializer;
            for (String str : fieldInfo.alternateNames) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(str, createFieldDeserializer);
            }
        }
        this.alterNameFieldDeserializers = hashMap;
        this.fieldDeserializers = new FieldDeserializer[javaBeanInfo.fields.length];
        int length2 = javaBeanInfo.fields.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(javaBeanInfo.fields[i2].name);
        }
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        return getFieldDeserializer(str, null);
    }

    public FieldDeserializer getFieldDeserializer(String str, int[] iArr) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedFieldDeserializers.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedFieldDeserializers[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else {
                if (compareTo <= 0) {
                    if (isSetFlag(i2, iArr)) {
                        return null;
                    }
                    return this.sortedFieldDeserializers[i2];
                }
                length = i2 - 1;
            }
        }
        Map<String, FieldDeserializer> map = this.alterNameFieldDeserializers;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public FieldDeserializer getFieldDeserializer(long j) {
        int i = 0;
        if (this.hashArray == null) {
            long[] jArr = new long[this.sortedFieldDeserializers.length];
            int i2 = 0;
            while (true) {
                FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                if (i2 >= fieldDeserializerArr.length) {
                    break;
                }
                jArr[i2] = TypeUtils.fnv1a_64(fieldDeserializerArr[i2].fieldInfo.name);
                i2++;
            }
            Arrays.sort(jArr);
            this.hashArray = jArr;
        }
        int binarySearch = Arrays.binarySearch(this.hashArray, j);
        if (binarySearch < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            while (true) {
                FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                if (i >= fieldDeserializerArr2.length) {
                    break;
                }
                int binarySearch2 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(fieldDeserializerArr2[i].fieldInfo.name));
                if (binarySearch2 >= 0) {
                    sArr[binarySearch2] = (short) i;
                }
                i++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[binarySearch];
        if (s != -1) {
            return this.sortedFieldDeserializers[s];
        }
        return null;
    }

    static boolean isSetFlag(int i, int[] iArr) {
        if (iArr == null) {
            return false;
        }
        int i2 = i / 32;
        int i3 = i % 32;
        if (i2 < iArr.length) {
            if (((1 << i3) & iArr[i2]) != 0) {
                return true;
            }
        }
        return false;
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        Object newInstance;
        if ((type instanceof Class) && this.clazz.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        }
        Object obj = null;
        if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
            return null;
        }
        if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
            return null;
        }
        try {
            Constructor<?> constructor = this.beanInfo.defaultConstructor;
            if (this.beanInfo.defaultConstructorParameterSize != 0) {
                ParseContext context = defaultJSONParser.getContext();
                if (context == null || context.object == null) {
                    throw new JSONException("can't create non-static inner class instance.");
                }
                if (type instanceof Class) {
                    String name = ((Class) type).getName();
                    String substring = name.substring(0, name.lastIndexOf(36));
                    Object obj2 = context.object;
                    String name2 = obj2.getClass().getName();
                    if (!name2.equals(substring)) {
                        ParseContext parseContext = context.parent;
                        if (parseContext != null && parseContext.object != null && (("java.util.ArrayList".equals(name2) || "java.util.List".equals(name2) || "java.util.Collection".equals(name2) || "java.util.Map".equals(name2) || "java.util.HashMap".equals(name2)) && parseContext.object.getClass().getName().equals(substring))) {
                            obj = parseContext.object;
                        }
                        obj2 = obj;
                    }
                    if (obj2 == null) {
                        throw new JSONException("can't create non-static inner class instance.");
                    }
                    newInstance = constructor.newInstance(obj2);
                } else {
                    throw new JSONException("can't create non-static inner class instance.");
                }
            } else if (constructor != null) {
                newInstance = constructor.newInstance(new Object[0]);
            } else {
                newInstance = this.beanInfo.factoryMethod.invoke(null, new Object[0]);
            }
            if (defaultJSONParser != null && defaultJSONParser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
                for (FieldInfo fieldInfo : this.beanInfo.fields) {
                    if (fieldInfo.fieldClass == String.class) {
                        try {
                            fieldInfo.set(newInstance, "");
                        } catch (Exception e) {
                            throw new JSONException("create instance error, class " + this.clazz.getName(), e);
                        }
                    }
                }
            }
            return newInstance;
        } catch (JSONException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new JSONException("create instance error, class " + this.clazz.getName(), e3);
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, 0);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, int i) {
        return (T) deserialze(defaultJSONParser, type, obj, null, i, null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        Enum<?> scanEnum;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() != 14) {
            throw new JSONException("error");
        }
        T t = (T) createInstance(defaultJSONParser, type);
        int i = 0;
        int length = this.sortedFieldDeserializers.length;
        while (true) {
            if (i >= length) {
                break;
            }
            char c = i == length + (-1) ? ']' : ',';
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
            Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
            if (cls == Integer.TYPE) {
                fieldDeserializer.setValue((Object) t, jSONLexer.scanInt(c));
            } else if (cls == String.class) {
                fieldDeserializer.setValue((Object) t, jSONLexer.scanString(c));
            } else if (cls == Long.TYPE) {
                fieldDeserializer.setValue(t, jSONLexer.scanLong(c));
            } else if (cls.isEnum()) {
                char current = jSONLexer.getCurrent();
                if (current == '\"' || current == 'n') {
                    scanEnum = jSONLexer.scanEnum(cls, defaultJSONParser.getSymbolTable(), c);
                } else if (current >= '0' && current <= '9') {
                    scanEnum = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.getConfig())).valueOf(jSONLexer.scanInt(c));
                } else {
                    scanEnum = scanEnum(jSONLexer, c);
                }
                fieldDeserializer.setValue(t, scanEnum);
            } else if (cls == Boolean.TYPE) {
                fieldDeserializer.setValue(t, jSONLexer.scanBoolean(c));
            } else if (cls == Float.TYPE) {
                fieldDeserializer.setValue(t, Float.valueOf(jSONLexer.scanFloat(c)));
            } else if (cls == Double.TYPE) {
                fieldDeserializer.setValue(t, Double.valueOf(jSONLexer.scanDouble(c)));
            } else if (cls == Date.class && jSONLexer.getCurrent() == '1') {
                fieldDeserializer.setValue(t, new Date(jSONLexer.scanLong(c)));
            } else if (cls == BigDecimal.class) {
                fieldDeserializer.setValue(t, jSONLexer.scanDecimal(c));
            } else {
                jSONLexer.nextToken(14);
                fieldDeserializer.setValue(t, defaultJSONParser.parseObject(fieldDeserializer.fieldInfo.fieldType, fieldDeserializer.fieldInfo.name));
                if (jSONLexer.token() == 15) {
                    break;
                }
                check(jSONLexer, c == ']' ? 15 : 16);
            }
            i++;
        }
        jSONLexer.nextToken(16);
        return t;
    }

    protected void check(JSONLexer jSONLexer, int i) {
        if (jSONLexer.token() != i) {
            throw new JSONException("syntax error");
        }
    }

    protected Enum<?> scanEnum(JSONLexer jSONLexer, char c) {
        throw new JSONException("illegal enum. " + jSONLexer.info());
    }

    /* JADX WARN: Code restructure failed: missing block: B:199:0x040d, code lost:
    
        r2 = r17;
        r30 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x0518, code lost:
    
        if (r1 != null) goto L466;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x051a, code lost:
    
        if (r2 != null) goto L376;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x051c, code lost:
    
        r1 = (T) createInstance(r25, r26);
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x0520, code lost:
    
        if (r6 != null) goto L372;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x0522, code lost:
    
        r6 = r25.setContext(r15, r1, r27);
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x0526, code lost:
    
        if (r6 == null) goto L374;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x0528, code lost:
    
        r6.object = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x052a, code lost:
    
        r25.setContext(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x052d, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x052e, code lost:
    
        r3 = r24.beanInfo.creatorConstructorParameters;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0532, code lost:
    
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x0536, code lost:
    
        if (r3 == null) goto L411;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x0538, code lost:
    
        r12 = new java.lang.Object[r3.length];
        r13 = r30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x053e, code lost:
    
        if (r13 >= r3.length) goto L536;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x0540, code lost:
    
        r14 = r2.remove(r3[r13]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x0546, code lost:
    
        if (r14 != null) goto L538;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x0548, code lost:
    
        r4 = r24.beanInfo.creatorConstructorParameterTypes[r13];
        r5 = r24.beanInfo.fields[r13];
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x0556, code lost:
    
        if (r4 != java.lang.Byte.TYPE) goto L387;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x0558, code lost:
    
        r14 = java.lang.Byte.valueOf(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x05a2, code lost:
    
        r12[r13] = r14;
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x055f, code lost:
    
        if (r4 != java.lang.Short.TYPE) goto L390;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x0561, code lost:
    
        r14 = java.lang.Short.valueOf(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x0568, code lost:
    
        if (r4 != java.lang.Integer.TYPE) goto L393;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x056a, code lost:
    
        r14 = java.lang.Integer.valueOf(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0571, code lost:
    
        if (r4 != java.lang.Long.TYPE) goto L396;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0573, code lost:
    
        r14 = 0L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x057a, code lost:
    
        if (r4 != java.lang.Float.TYPE) goto L399;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x057c, code lost:
    
        r14 = java.lang.Float.valueOf(0.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x0584, code lost:
    
        if (r4 != java.lang.Double.TYPE) goto L402;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x0586, code lost:
    
        r14 = java.lang.Double.valueOf(0.0d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x058f, code lost:
    
        if (r4 != java.lang.Boolean.TYPE) goto L405;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x0591, code lost:
    
        r14 = java.lang.Boolean.FALSE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x0596, code lost:
    
        if (r4 != java.lang.String.class) goto L546;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x059f, code lost:
    
        if ((r5.parserFeatures & com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty.mask) == 0) goto L547;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x05a1, code lost:
    
        r14 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x0625, code lost:
    
        if (r24.beanInfo.creatorConstructor == null) goto L458;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x0685, code lost:
    
        if (r24.beanInfo.factoryMethod == null) goto L465;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x06b2, code lost:
    
        r6.object = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x0690, code lost:
    
        r1 = (T) r24.beanInfo.factoryMethod.invoke(null, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x0692, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x06b1, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create factory method error, " + r24.beanInfo.factoryMethod.toString(), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x0627, code lost:
    
        r1 = (T) r24.beanInfo.creatorConstructor.newInstance(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x062f, code lost:
    
        if (r3 == null) goto L465;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x0631, code lost:
    
        r0 = r2.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x063d, code lost:
    
        if (r0.hasNext() == false) goto L548;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x063f, code lost:
    
        r2 = r0.next();
        r3 = getFieldDeserializer(r2.getKey());
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x064f, code lost:
    
        if (r3 == null) goto L551;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x0651, code lost:
    
        r3.setValue(r1, r2.getValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:283:0x0659, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x0680, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error, " + r3 + ", " + r24.beanInfo.creatorConstructor.toGenericString(), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x05a7, code lost:
    
        r4 = r24.beanInfo.fields;
        r5 = r4.length;
        r12 = new java.lang.Object[r5];
        r7 = r30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x05b0, code lost:
    
        if (r7 >= r5) goto L553;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x05b2, code lost:
    
        r13 = r4[r7];
        r14 = r2.get(r13.name);
     */
    /* JADX WARN: Code restructure failed: missing block: B:289:0x05ba, code lost:
    
        if (r14 != null) goto L443;
     */
    /* JADX WARN: Code restructure failed: missing block: B:290:0x05bc, code lost:
    
        r10 = r13.fieldType;
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x05c0, code lost:
    
        if (r10 != java.lang.Byte.TYPE) goto L420;
     */
    /* JADX WARN: Code restructure failed: missing block: B:292:0x05c2, code lost:
    
        r14 = java.lang.Byte.valueOf(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:293:0x05c6, code lost:
    
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x061a, code lost:
    
        r12[r7] = r14;
        r7 = r7 + 1;
        r10 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x05cd, code lost:
    
        if (r10 != java.lang.Short.TYPE) goto L423;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x05cf, code lost:
    
        r14 = java.lang.Short.valueOf(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x05d6, code lost:
    
        if (r10 != java.lang.Integer.TYPE) goto L426;
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x05d8, code lost:
    
        r14 = java.lang.Integer.valueOf(r30);
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x05df, code lost:
    
        if (r10 != java.lang.Long.TYPE) goto L429;
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x05e1, code lost:
    
        r16 = 0;
        r14 = 0L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x05e8, code lost:
    
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:307:0x05ec, code lost:
    
        if (r10 != java.lang.Float.TYPE) goto L432;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x05ee, code lost:
    
        r14 = java.lang.Float.valueOf(0.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:310:0x05f6, code lost:
    
        if (r10 != java.lang.Double.TYPE) goto L435;
     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x05f8, code lost:
    
        r14 = java.lang.Double.valueOf(0.0d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:314:0x0603, code lost:
    
        if (r10 != java.lang.Boolean.TYPE) goto L438;
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x0605, code lost:
    
        r14 = java.lang.Boolean.FALSE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x060a, code lost:
    
        if (r10 != java.lang.String.class) goto L557;
     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x0613, code lost:
    
        if ((r13.parserFeatures & com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty.mask) == 0) goto L558;
     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x0615, code lost:
    
        r14 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x0617, code lost:
    
        r16 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:327:0x06b4, code lost:
    
        r0 = r24.beanInfo.buildMethod;
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x06b8, code lost:
    
        if (r0 != null) goto L472;
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x06ba, code lost:
    
        if (r6 == null) goto L470;
     */
    /* JADX WARN: Code restructure failed: missing block: B:330:0x06bc, code lost:
    
        r6.object = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:331:0x06be, code lost:
    
        r25.setContext(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:332:0x06c1, code lost:
    
        return (T) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x06c4, code lost:
    
        r0 = (T) r0.invoke(r1, new java.lang.Object[r30]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:336:0x06ca, code lost:
    
        if (r6 == null) goto L476;
     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x06cc, code lost:
    
        r6.object = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:338:0x06ce, code lost:
    
        r25.setContext(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:339:0x06d1, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:341:0x06d2, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x06da, code lost:
    
        throw new com.alibaba.fastjson.JSONException("build object error", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:379:0x071c, code lost:
    
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r11.token()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:383:0x0512, code lost:
    
        r2 = r16;
        r1 = (T) r18;
        r6 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:546:0x02ad, code lost:
    
        if (r11.matchStat == (-2)) goto L219;
     */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02bc A[Catch: all -> 0x072b, TryCatch #5 {all -> 0x072b, blocks: (B:89:0x012d, B:91:0x0132, B:93:0x0146, B:98:0x0155, B:105:0x0163, B:110:0x02bc, B:112:0x02c6, B:426:0x02d2, B:202:0x051c, B:204:0x0522, B:209:0x052e, B:212:0x0538, B:213:0x053d, B:215:0x0540, B:217:0x0548, B:219:0x0558, B:222:0x055d, B:224:0x0561, B:226:0x0566, B:228:0x056a, B:230:0x056f, B:232:0x0573, B:234:0x0578, B:236:0x057c, B:238:0x0582, B:240:0x0586, B:242:0x058d, B:244:0x0591, B:248:0x0598, B:221:0x05a2, B:256:0x0621, B:269:0x0627, B:271:0x0631, B:272:0x0639, B:274:0x063f, B:277:0x0651, B:260:0x06b2, B:258:0x0681, B:262:0x0687, B:266:0x0693, B:267:0x06b1, B:284:0x065a, B:285:0x0680, B:286:0x05a7, B:288:0x05b2, B:290:0x05bc, B:292:0x05c2, B:296:0x061a, B:297:0x05cb, B:299:0x05cf, B:300:0x05d4, B:302:0x05d8, B:303:0x05dd, B:305:0x05e1, B:306:0x05e8, B:308:0x05ee, B:309:0x05f4, B:311:0x05f8, B:313:0x05ff, B:315:0x0605, B:319:0x060c, B:327:0x06b4, B:335:0x06c4, B:342:0x06d3, B:343:0x06da, B:116:0x02dd, B:134:0x02f0, B:136:0x02fa, B:138:0x0306, B:139:0x035d, B:141:0x0368, B:146:0x0378, B:147:0x037f, B:148:0x030a, B:150:0x0312, B:152:0x0318, B:153:0x031b, B:154:0x0327, B:157:0x0330, B:159:0x0334, B:161:0x0337, B:163:0x033b, B:164:0x033e, B:165:0x034a, B:168:0x0352, B:169:0x0380, B:170:0x039a, B:172:0x039d, B:174:0x03a7, B:176:0x03b1, B:178:0x03c4, B:182:0x03cd, B:184:0x03d5, B:185:0x03e7, B:187:0x03ef, B:189:0x03f3, B:195:0x0402, B:198:0x040a, B:345:0x0424, B:346:0x042b, B:347:0x03a3, B:352:0x043c, B:354:0x0442, B:355:0x044c, B:357:0x0452, B:429:0x016d, B:436:0x0177, B:438:0x017b, B:441:0x0185, B:446:0x018f, B:449:0x0199, B:454:0x01a3, B:457:0x01ad, B:460:0x01b3, B:465:0x01bd, B:470:0x01c7, B:475:0x01d1, B:477:0x01d7, B:480:0x01e5, B:482:0x01ed, B:484:0x01f1, B:487:0x0200, B:494:0x020b, B:497:0x0215, B:502:0x0220, B:505:0x022a, B:510:0x0235, B:513:0x023f, B:516:0x0246, B:519:0x024f, B:522:0x025c, B:525:0x0262, B:528:0x026f, B:531:0x0275, B:534:0x0282, B:537:0x0288, B:540:0x0295, B:543:0x029b, B:545:0x02aa), top: B:88:0x012d, inners: #3, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0734  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0070 A[Catch: all -> 0x0049, TRY_LEAVE, TryCatch #4 {all -> 0x0049, blocks: (B:17:0x0039, B:19:0x003e, B:25:0x0053, B:27:0x005e, B:29:0x0066, B:34:0x0070, B:41:0x007f, B:46:0x008b, B:48:0x0095, B:51:0x009c, B:53:0x00a2, B:55:0x00ad, B:58:0x00b7, B:68:0x00ca, B:70:0x00d2, B:73:0x00dc, B:75:0x00fd, B:76:0x0105, B:77:0x0118, B:80:0x00c5, B:85:0x011e), top: B:15:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:351:0x043a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0442 A[Catch: all -> 0x072b, TryCatch #5 {all -> 0x072b, blocks: (B:89:0x012d, B:91:0x0132, B:93:0x0146, B:98:0x0155, B:105:0x0163, B:110:0x02bc, B:112:0x02c6, B:426:0x02d2, B:202:0x051c, B:204:0x0522, B:209:0x052e, B:212:0x0538, B:213:0x053d, B:215:0x0540, B:217:0x0548, B:219:0x0558, B:222:0x055d, B:224:0x0561, B:226:0x0566, B:228:0x056a, B:230:0x056f, B:232:0x0573, B:234:0x0578, B:236:0x057c, B:238:0x0582, B:240:0x0586, B:242:0x058d, B:244:0x0591, B:248:0x0598, B:221:0x05a2, B:256:0x0621, B:269:0x0627, B:271:0x0631, B:272:0x0639, B:274:0x063f, B:277:0x0651, B:260:0x06b2, B:258:0x0681, B:262:0x0687, B:266:0x0693, B:267:0x06b1, B:284:0x065a, B:285:0x0680, B:286:0x05a7, B:288:0x05b2, B:290:0x05bc, B:292:0x05c2, B:296:0x061a, B:297:0x05cb, B:299:0x05cf, B:300:0x05d4, B:302:0x05d8, B:303:0x05dd, B:305:0x05e1, B:306:0x05e8, B:308:0x05ee, B:309:0x05f4, B:311:0x05f8, B:313:0x05ff, B:315:0x0605, B:319:0x060c, B:327:0x06b4, B:335:0x06c4, B:342:0x06d3, B:343:0x06da, B:116:0x02dd, B:134:0x02f0, B:136:0x02fa, B:138:0x0306, B:139:0x035d, B:141:0x0368, B:146:0x0378, B:147:0x037f, B:148:0x030a, B:150:0x0312, B:152:0x0318, B:153:0x031b, B:154:0x0327, B:157:0x0330, B:159:0x0334, B:161:0x0337, B:163:0x033b, B:164:0x033e, B:165:0x034a, B:168:0x0352, B:169:0x0380, B:170:0x039a, B:172:0x039d, B:174:0x03a7, B:176:0x03b1, B:178:0x03c4, B:182:0x03cd, B:184:0x03d5, B:185:0x03e7, B:187:0x03ef, B:189:0x03f3, B:195:0x0402, B:198:0x040a, B:345:0x0424, B:346:0x042b, B:347:0x03a3, B:352:0x043c, B:354:0x0442, B:355:0x044c, B:357:0x0452, B:429:0x016d, B:436:0x0177, B:438:0x017b, B:441:0x0185, B:446:0x018f, B:449:0x0199, B:454:0x01a3, B:457:0x01ad, B:460:0x01b3, B:465:0x01bd, B:470:0x01c7, B:475:0x01d1, B:477:0x01d7, B:480:0x01e5, B:482:0x01ed, B:484:0x01f1, B:487:0x0200, B:494:0x020b, B:497:0x0215, B:502:0x0220, B:505:0x022a, B:510:0x0235, B:513:0x023f, B:516:0x0246, B:519:0x024f, B:522:0x025c, B:525:0x0262, B:528:0x026f, B:531:0x0275, B:534:0x0282, B:537:0x0288, B:540:0x0295, B:543:0x029b, B:545:0x02aa), top: B:88:0x012d, inners: #3, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0452 A[Catch: all -> 0x072b, TRY_LEAVE, TryCatch #5 {all -> 0x072b, blocks: (B:89:0x012d, B:91:0x0132, B:93:0x0146, B:98:0x0155, B:105:0x0163, B:110:0x02bc, B:112:0x02c6, B:426:0x02d2, B:202:0x051c, B:204:0x0522, B:209:0x052e, B:212:0x0538, B:213:0x053d, B:215:0x0540, B:217:0x0548, B:219:0x0558, B:222:0x055d, B:224:0x0561, B:226:0x0566, B:228:0x056a, B:230:0x056f, B:232:0x0573, B:234:0x0578, B:236:0x057c, B:238:0x0582, B:240:0x0586, B:242:0x058d, B:244:0x0591, B:248:0x0598, B:221:0x05a2, B:256:0x0621, B:269:0x0627, B:271:0x0631, B:272:0x0639, B:274:0x063f, B:277:0x0651, B:260:0x06b2, B:258:0x0681, B:262:0x0687, B:266:0x0693, B:267:0x06b1, B:284:0x065a, B:285:0x0680, B:286:0x05a7, B:288:0x05b2, B:290:0x05bc, B:292:0x05c2, B:296:0x061a, B:297:0x05cb, B:299:0x05cf, B:300:0x05d4, B:302:0x05d8, B:303:0x05dd, B:305:0x05e1, B:306:0x05e8, B:308:0x05ee, B:309:0x05f4, B:311:0x05f8, B:313:0x05ff, B:315:0x0605, B:319:0x060c, B:327:0x06b4, B:335:0x06c4, B:342:0x06d3, B:343:0x06da, B:116:0x02dd, B:134:0x02f0, B:136:0x02fa, B:138:0x0306, B:139:0x035d, B:141:0x0368, B:146:0x0378, B:147:0x037f, B:148:0x030a, B:150:0x0312, B:152:0x0318, B:153:0x031b, B:154:0x0327, B:157:0x0330, B:159:0x0334, B:161:0x0337, B:163:0x033b, B:164:0x033e, B:165:0x034a, B:168:0x0352, B:169:0x0380, B:170:0x039a, B:172:0x039d, B:174:0x03a7, B:176:0x03b1, B:178:0x03c4, B:182:0x03cd, B:184:0x03d5, B:185:0x03e7, B:187:0x03ef, B:189:0x03f3, B:195:0x0402, B:198:0x040a, B:345:0x0424, B:346:0x042b, B:347:0x03a3, B:352:0x043c, B:354:0x0442, B:355:0x044c, B:357:0x0452, B:429:0x016d, B:436:0x0177, B:438:0x017b, B:441:0x0185, B:446:0x018f, B:449:0x0199, B:454:0x01a3, B:457:0x01ad, B:460:0x01b3, B:465:0x01bd, B:470:0x01c7, B:475:0x01d1, B:477:0x01d7, B:480:0x01e5, B:482:0x01ed, B:484:0x01f1, B:487:0x0200, B:494:0x020b, B:497:0x0215, B:502:0x0220, B:505:0x022a, B:510:0x0235, B:513:0x023f, B:516:0x0246, B:519:0x024f, B:522:0x025c, B:525:0x0262, B:528:0x026f, B:531:0x0275, B:534:0x0282, B:537:0x0288, B:540:0x0295, B:543:0x029b, B:545:0x02aa), top: B:88:0x012d, inners: #3, #6, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0509 A[Catch: all -> 0x0725, TryCatch #0 {all -> 0x0725, blocks: (B:122:0x06f1, B:365:0x04fe, B:370:0x0509, B:382:0x050f, B:373:0x06de, B:375:0x06e6, B:378:0x06fe, B:379:0x071c, B:413:0x04de, B:415:0x04e4, B:419:0x04ea, B:420:0x04f6, B:423:0x071d, B:424:0x0724), top: B:121:0x06f1 }] */
    /* JADX WARN: Removed duplicated region for block: B:412:0x04c5  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x042f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i, int[] iArr) {
        Object obj3;
        FieldInfo fieldInfo;
        FieldDeserializer fieldDeserializer;
        Class<?> cls;
        JSONField jSONField;
        int i2;
        boolean z;
        Object obj4;
        boolean z2;
        ParseContext parseContext;
        Object obj5;
        int[] iArr2;
        HashMap hashMap;
        String str;
        int i3;
        char c;
        int i4;
        FieldInfo fieldInfo2;
        Object obj6;
        Class<?> cls2;
        String str2;
        byte b;
        Class<?> cls3;
        boolean z3;
        if (type == JSON.class || type == JSONObject.class) {
            return (T) defaultJSONParser.parse();
        }
        JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
        ParserConfig config = defaultJSONParser.getConfig();
        int i5 = jSONLexerBase.token();
        ParseContext parseContext2 = null;
        if (i5 == 8) {
            jSONLexerBase.nextToken(16);
            return null;
        }
        ParseContext context = defaultJSONParser.getContext();
        if (obj2 != null && context != null) {
            context = context.parent;
        }
        ParseContext parseContext3 = context;
        try {
            if (i5 == 13) {
                jSONLexerBase.nextToken(16);
                T t = obj2 == null ? (T) createInstance(defaultJSONParser, type) : (T) obj2;
                defaultJSONParser.setContext(parseContext3);
                return t;
            }
            if (i5 == 14) {
                int i6 = Feature.SupportArrayToBean.mask;
                if ((this.beanInfo.parserFeatures & i6) == 0 && !jSONLexerBase.isEnabled(Feature.SupportArrayToBean) && (i & i6) == 0) {
                    z3 = false;
                    if (z3) {
                        T t2 = (T) deserialzeArrayMapping(defaultJSONParser, type, obj, obj2);
                        defaultJSONParser.setContext(parseContext3);
                        return t2;
                    }
                }
                z3 = true;
                if (z3) {
                }
            }
            if (i5 != 12 && i5 != 16) {
                if (jSONLexerBase.isBlankInput()) {
                    defaultJSONParser.setContext(parseContext3);
                    return null;
                }
                if (i5 == 4) {
                    String stringVal = jSONLexerBase.stringVal();
                    if (stringVal.length() == 0) {
                        jSONLexerBase.nextToken();
                        defaultJSONParser.setContext(parseContext3);
                        return null;
                    }
                    if (this.beanInfo.jsonType != null) {
                        for (Class<?> cls4 : this.beanInfo.jsonType.seeAlso()) {
                            if (Enum.class.isAssignableFrom(cls4)) {
                                try {
                                    T t3 = (T) Enum.valueOf(cls4, stringVal);
                                    defaultJSONParser.setContext(parseContext3);
                                    return t3;
                                } catch (IllegalArgumentException unused) {
                                    continue;
                                }
                            }
                        }
                    }
                } else if (i5 == 5) {
                    jSONLexerBase.getCalendar();
                }
                if (i5 == 14 && jSONLexerBase.getCurrent() == ']') {
                    jSONLexerBase.next();
                    jSONLexerBase.nextToken();
                    defaultJSONParser.setContext(parseContext3);
                    return null;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("syntax error, expect {, actual ");
                stringBuffer.append(jSONLexerBase.tokenName());
                stringBuffer.append(", pos ");
                stringBuffer.append(jSONLexerBase.pos());
                if (obj instanceof String) {
                    stringBuffer.append(", fieldName ");
                    stringBuffer.append(obj);
                }
                stringBuffer.append(", fastjson-version ");
                stringBuffer.append(JSON.VERSION);
                throw new JSONException(stringBuffer.toString());
            }
            try {
                if (defaultJSONParser.resolveStatus == 2) {
                    defaultJSONParser.resolveStatus = 0;
                }
                String str3 = this.beanInfo.typeKey;
                obj3 = obj2;
                int[] iArr3 = iArr;
                HashMap hashMap2 = null;
                int i7 = 0;
                ParseContext parseContext4 = null;
                while (true) {
                    try {
                        if (i7 < this.sortedFieldDeserializers.length) {
                            fieldDeserializer = this.sortedFieldDeserializers[i7];
                            fieldInfo = fieldDeserializer.fieldInfo;
                            cls = fieldInfo.fieldClass;
                            jSONField = fieldInfo.getAnnotation();
                        } else {
                            fieldInfo = null;
                            fieldDeserializer = null;
                            cls = null;
                            jSONField = null;
                        }
                        if (fieldDeserializer != null) {
                            char[] cArr = fieldInfo.name_chars;
                            i2 = i7;
                            if (cls != Integer.TYPE && cls != Integer.class) {
                                if (cls != Long.TYPE && cls != Long.class) {
                                    try {
                                        if (cls == String.class) {
                                            obj4 = jSONLexerBase.scanFieldString(cArr);
                                            if (jSONLexerBase.matchStat > 0) {
                                                z = true;
                                                z2 = true;
                                                if (z) {
                                                    fieldInfo2 = fieldInfo;
                                                    obj6 = obj4;
                                                    cls2 = cls;
                                                    str2 = null;
                                                } else {
                                                    cls2 = cls;
                                                    str2 = jSONLexerBase.scanSymbol(defaultJSONParser.symbolTable);
                                                    if (str2 == null) {
                                                        obj6 = obj4;
                                                        int i8 = jSONLexerBase.token();
                                                        fieldInfo2 = fieldInfo;
                                                        if (i8 == 13) {
                                                            jSONLexerBase.nextToken(16);
                                                            break;
                                                        }
                                                        i4 = (i8 == 16 && jSONLexerBase.isEnabled(Feature.AllowArbitraryCommas)) ? 13 : 13;
                                                    } else {
                                                        fieldInfo2 = fieldInfo;
                                                        obj6 = obj4;
                                                    }
                                                    if ("$ref" == str2 && parseContext3 != null) {
                                                        jSONLexerBase.nextTokenWithColon(4);
                                                        int i9 = jSONLexerBase.token();
                                                        if (i9 != 4) {
                                                            throw new JSONException("illegal ref, " + JSONToken.name(i9));
                                                        }
                                                        String stringVal2 = jSONLexerBase.stringVal();
                                                        if ("@".equals(stringVal2)) {
                                                            obj3 = (T) parseContext3.object;
                                                        } else if ("..".equals(stringVal2)) {
                                                            ParseContext parseContext5 = parseContext3.parent;
                                                            if (parseContext5.object != null) {
                                                                obj3 = (T) parseContext5.object;
                                                            } else {
                                                                defaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(parseContext5, stringVal2));
                                                                defaultJSONParser.resolveStatus = 1;
                                                            }
                                                        } else if ("$".equals(stringVal2)) {
                                                            ParseContext parseContext6 = parseContext3;
                                                            while (parseContext6.parent != null) {
                                                                parseContext6 = parseContext6.parent;
                                                            }
                                                            if (parseContext6.object != null) {
                                                                obj3 = (T) parseContext6.object;
                                                            } else {
                                                                defaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(parseContext6, stringVal2));
                                                                defaultJSONParser.resolveStatus = 1;
                                                            }
                                                        } else {
                                                            Object resolveReference = defaultJSONParser.resolveReference(stringVal2);
                                                            if (resolveReference != null) {
                                                                obj3 = (T) resolveReference;
                                                            } else {
                                                                defaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(parseContext3, stringVal2));
                                                                defaultJSONParser.resolveStatus = 1;
                                                            }
                                                        }
                                                        jSONLexerBase.nextToken(13);
                                                        if (jSONLexerBase.token() != 13) {
                                                            throw new JSONException("illegal ref");
                                                        }
                                                        jSONLexerBase.nextToken(16);
                                                        defaultJSONParser.setContext(parseContext3, obj3, obj);
                                                        if (parseContext4 != null) {
                                                            parseContext4.object = obj3;
                                                        }
                                                        defaultJSONParser.setContext(parseContext3);
                                                        return (T) obj3;
                                                    }
                                                    if ((str3 != null && str3.equals(str2)) || JSON.DEFAULT_TYPE_KEY == str2) {
                                                        jSONLexerBase.nextTokenWithColon(4);
                                                        if (jSONLexerBase.token() != 4) {
                                                            throw new JSONException("syntax error");
                                                        }
                                                        String stringVal3 = jSONLexerBase.stringVal();
                                                        jSONLexerBase.nextToken(16);
                                                        if (!stringVal3.equals(this.beanInfo.typeName) && !defaultJSONParser.isEnabled(Feature.IgnoreAutoType)) {
                                                            ObjectDeserializer seeAlso = getSeeAlso(config, this.beanInfo, stringVal3);
                                                            if (seeAlso == null) {
                                                                cls3 = config.checkAutoType(stringVal3, TypeUtils.getClass(type));
                                                                seeAlso = defaultJSONParser.getConfig().getDeserializer(cls3);
                                                            } else {
                                                                cls3 = null;
                                                            }
                                                            T t4 = (T) seeAlso.deserialze(defaultJSONParser, cls3, obj);
                                                            if (seeAlso instanceof JavaBeanDeserializer) {
                                                                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) seeAlso;
                                                                if (str3 != null) {
                                                                    javaBeanDeserializer.getFieldDeserializer(str3).setValue((Object) t4, stringVal3);
                                                                }
                                                            }
                                                            if (parseContext4 != null) {
                                                                parseContext4.object = obj3;
                                                            }
                                                            defaultJSONParser.setContext(parseContext3);
                                                            return t4;
                                                        }
                                                        if (jSONLexerBase.token() == 13) {
                                                            jSONLexerBase.nextToken();
                                                            break;
                                                        }
                                                    }
                                                }
                                                if (obj3 == null && hashMap2 == null) {
                                                    obj3 = (T) createInstance(defaultJSONParser, type);
                                                    if (obj3 == null) {
                                                        hashMap2 = new HashMap(this.fieldDeserializers.length);
                                                    }
                                                    parseContext4 = defaultJSONParser.setContext(parseContext3, obj3, obj);
                                                    if (iArr3 == null) {
                                                        iArr3 = new int[(this.fieldDeserializers.length / 32) + 1];
                                                    }
                                                }
                                                Object obj7 = obj3;
                                                parseContext = parseContext4;
                                                HashMap hashMap3 = hashMap2;
                                                iArr2 = iArr3;
                                                if (z) {
                                                    if (z2) {
                                                        if (obj7 == null) {
                                                            hashMap3.put(fieldInfo2.name, obj6);
                                                        } else {
                                                            Object obj8 = obj6;
                                                            if (obj8 == null) {
                                                                Class<?> cls5 = cls2;
                                                                if (cls5 != Integer.TYPE && cls5 != Long.TYPE && cls5 != Float.TYPE && cls5 != Double.TYPE && cls5 != Boolean.TYPE) {
                                                                    fieldDeserializer.setValue(obj7, obj8);
                                                                }
                                                            } else {
                                                                fieldDeserializer.setValue(obj7, obj8);
                                                            }
                                                        }
                                                        if (iArr2 != null) {
                                                            int i10 = i2 / 32;
                                                            iArr2[i10] = (1 >> (i2 % 32)) | iArr2[i10];
                                                        }
                                                        if (jSONLexerBase.matchStat == 4) {
                                                            hashMap = hashMap3;
                                                            obj5 = obj7;
                                                            b = 0;
                                                            break;
                                                        }
                                                    } else {
                                                        try {
                                                            fieldDeserializer.parseField(defaultJSONParser, obj7, type, hashMap3);
                                                        } catch (Throwable th) {
                                                            th = th;
                                                            obj3 = obj7;
                                                            parseContext2 = parseContext;
                                                            if (parseContext2 != null) {
                                                            }
                                                            defaultJSONParser.setContext(parseContext3);
                                                            throw th;
                                                        }
                                                    }
                                                    str = str3;
                                                    hashMap = hashMap3;
                                                    obj5 = obj7;
                                                    b = 0;
                                                    i3 = 13;
                                                    c = 16;
                                                    if (jSONLexerBase.token() != 16) {
                                                        obj3 = (T) obj5;
                                                        parseContext4 = parseContext;
                                                        str3 = str;
                                                        i7 = i2 + 1;
                                                        iArr3 = iArr2;
                                                        hashMap2 = hashMap;
                                                    } else {
                                                        if (jSONLexerBase.token() == i3) {
                                                            jSONLexerBase.nextToken(16);
                                                            break;
                                                        }
                                                        if (jSONLexerBase.token() == 18 || jSONLexerBase.token() == 1) {
                                                            break;
                                                        }
                                                        obj3 = (T) obj5;
                                                        parseContext4 = parseContext;
                                                        str3 = str;
                                                        i7 = i2 + 1;
                                                        iArr3 = iArr2;
                                                        hashMap2 = hashMap;
                                                    }
                                                } else {
                                                    str = str3;
                                                    String str4 = str2;
                                                    hashMap = hashMap3;
                                                    b = 0;
                                                    obj5 = obj7;
                                                    i3 = 13;
                                                    if (parseField(defaultJSONParser, str4, obj7, type, hashMap3, iArr2)) {
                                                        if (jSONLexerBase.token() == 17) {
                                                            throw new JSONException("syntax error, unexpect token ':'");
                                                        }
                                                        c = 16;
                                                        if (jSONLexerBase.token() != 16) {
                                                        }
                                                    } else {
                                                        if (jSONLexerBase.token() == 13) {
                                                            jSONLexerBase.nextToken();
                                                            break;
                                                        }
                                                        c = 16;
                                                        obj3 = (T) obj5;
                                                        parseContext4 = parseContext;
                                                        str3 = str;
                                                        i7 = i2 + 1;
                                                        iArr3 = iArr2;
                                                        hashMap2 = hashMap;
                                                    }
                                                }
                                            } else {
                                                if (jSONLexerBase.matchStat == -2) {
                                                }
                                                z = false;
                                                z2 = false;
                                                if (z) {
                                                }
                                                if (obj3 == null) {
                                                    obj3 = (T) createInstance(defaultJSONParser, type);
                                                    if (obj3 == null) {
                                                    }
                                                    parseContext4 = defaultJSONParser.setContext(parseContext3, obj3, obj);
                                                    if (iArr3 == null) {
                                                    }
                                                }
                                                Object obj72 = obj3;
                                                parseContext = parseContext4;
                                                HashMap hashMap32 = hashMap2;
                                                iArr2 = iArr3;
                                                if (z) {
                                                }
                                            }
                                        } else if (cls == Date.class && fieldInfo.format == null) {
                                            obj4 = jSONLexerBase.scanFieldDate(cArr);
                                            if (jSONLexerBase.matchStat > 0) {
                                                z = true;
                                                z2 = true;
                                                if (z) {
                                                }
                                                if (obj3 == null) {
                                                }
                                                Object obj722 = obj3;
                                                parseContext = parseContext4;
                                                HashMap hashMap322 = hashMap2;
                                                iArr2 = iArr3;
                                                if (z) {
                                                }
                                            } else {
                                                if (jSONLexerBase.matchStat == -2) {
                                                }
                                                z = false;
                                                z2 = false;
                                                if (z) {
                                                }
                                                if (obj3 == null) {
                                                }
                                                Object obj7222 = obj3;
                                                parseContext = parseContext4;
                                                HashMap hashMap3222 = hashMap2;
                                                iArr2 = iArr3;
                                                if (z) {
                                                }
                                            }
                                        } else if (cls == BigDecimal.class) {
                                            obj4 = jSONLexerBase.scanFieldDecimal(cArr);
                                            if (jSONLexerBase.matchStat > 0) {
                                                z = true;
                                                z2 = true;
                                                if (z) {
                                                }
                                                if (obj3 == null) {
                                                }
                                                Object obj72222 = obj3;
                                                parseContext = parseContext4;
                                                HashMap hashMap32222 = hashMap2;
                                                iArr2 = iArr3;
                                                if (z) {
                                                }
                                            } else {
                                                if (jSONLexerBase.matchStat == -2) {
                                                }
                                                z = false;
                                                z2 = false;
                                                if (z) {
                                                }
                                                if (obj3 == null) {
                                                }
                                                Object obj722222 = obj3;
                                                parseContext = parseContext4;
                                                HashMap hashMap322222 = hashMap2;
                                                iArr2 = iArr3;
                                                if (z) {
                                                }
                                            }
                                        } else if (cls == BigInteger.class) {
                                            obj4 = jSONLexerBase.scanFieldBigInteger(cArr);
                                            if (jSONLexerBase.matchStat > 0) {
                                                z = true;
                                                z2 = true;
                                                if (z) {
                                                }
                                                if (obj3 == null) {
                                                }
                                                Object obj7222222 = obj3;
                                                parseContext = parseContext4;
                                                HashMap hashMap3222222 = hashMap2;
                                                iArr2 = iArr3;
                                                if (z) {
                                                }
                                            } else {
                                                if (jSONLexerBase.matchStat == -2) {
                                                }
                                                z = false;
                                                z2 = false;
                                                if (z) {
                                                }
                                                if (obj3 == null) {
                                                }
                                                Object obj72222222 = obj3;
                                                parseContext = parseContext4;
                                                HashMap hashMap32222222 = hashMap2;
                                                iArr2 = iArr3;
                                                if (z) {
                                                }
                                            }
                                        } else {
                                            if (cls != Boolean.TYPE && cls != Boolean.class) {
                                                if (cls != Float.TYPE && cls != Float.class) {
                                                    if (cls != Double.TYPE && cls != Double.class) {
                                                        if (cls.isEnum() && (defaultJSONParser.getConfig().getDeserializer(cls) instanceof EnumDeserializer) && (jSONField == null || jSONField.deserializeUsing() == Void.class)) {
                                                            if (fieldDeserializer instanceof DefaultFieldDeserializer) {
                                                                obj4 = scanEnum(jSONLexerBase, cArr, ((DefaultFieldDeserializer) fieldDeserializer).fieldValueDeserilizer);
                                                                if (jSONLexerBase.matchStat > 0) {
                                                                    z = true;
                                                                    z2 = true;
                                                                    if (z) {
                                                                    }
                                                                    if (obj3 == null) {
                                                                    }
                                                                    Object obj722222222 = obj3;
                                                                    parseContext = parseContext4;
                                                                    HashMap hashMap322222222 = hashMap2;
                                                                    iArr2 = iArr3;
                                                                    if (z) {
                                                                    }
                                                                } else {
                                                                    if (jSONLexerBase.matchStat == -2) {
                                                                    }
                                                                    z = false;
                                                                    z2 = false;
                                                                    if (z) {
                                                                    }
                                                                    if (obj3 == null) {
                                                                    }
                                                                    Object obj7222222222 = obj3;
                                                                    parseContext = parseContext4;
                                                                    HashMap hashMap3222222222 = hashMap2;
                                                                    iArr2 = iArr3;
                                                                    if (z) {
                                                                    }
                                                                }
                                                            }
                                                        } else if (cls == int[].class) {
                                                            obj4 = jSONLexerBase.scanFieldIntArray(cArr);
                                                            if (jSONLexerBase.matchStat > 0) {
                                                                z = true;
                                                                z2 = true;
                                                                if (z) {
                                                                }
                                                                if (obj3 == null) {
                                                                }
                                                                Object obj72222222222 = obj3;
                                                                parseContext = parseContext4;
                                                                HashMap hashMap32222222222 = hashMap2;
                                                                iArr2 = iArr3;
                                                                if (z) {
                                                                }
                                                            } else {
                                                                if (jSONLexerBase.matchStat == -2) {
                                                                }
                                                                z = false;
                                                                z2 = false;
                                                                if (z) {
                                                                }
                                                                if (obj3 == null) {
                                                                }
                                                                Object obj722222222222 = obj3;
                                                                parseContext = parseContext4;
                                                                HashMap hashMap322222222222 = hashMap2;
                                                                iArr2 = iArr3;
                                                                if (z) {
                                                                }
                                                            }
                                                        } else if (cls == float[].class) {
                                                            obj4 = jSONLexerBase.scanFieldFloatArray(cArr);
                                                            if (jSONLexerBase.matchStat > 0) {
                                                                z = true;
                                                                z2 = true;
                                                                if (z) {
                                                                }
                                                                if (obj3 == null) {
                                                                }
                                                                Object obj7222222222222 = obj3;
                                                                parseContext = parseContext4;
                                                                HashMap hashMap3222222222222 = hashMap2;
                                                                iArr2 = iArr3;
                                                                if (z) {
                                                                }
                                                            } else {
                                                                if (jSONLexerBase.matchStat == -2) {
                                                                }
                                                                z = false;
                                                                z2 = false;
                                                                if (z) {
                                                                }
                                                                if (obj3 == null) {
                                                                }
                                                                Object obj72222222222222 = obj3;
                                                                parseContext = parseContext4;
                                                                HashMap hashMap32222222222222 = hashMap2;
                                                                iArr2 = iArr3;
                                                                if (z) {
                                                                }
                                                            }
                                                        } else if (cls == float[][].class) {
                                                            obj4 = jSONLexerBase.scanFieldFloatArray2(cArr);
                                                            if (jSONLexerBase.matchStat > 0) {
                                                                z = true;
                                                                z2 = true;
                                                                if (z) {
                                                                }
                                                                if (obj3 == null) {
                                                                }
                                                                Object obj722222222222222 = obj3;
                                                                parseContext = parseContext4;
                                                                HashMap hashMap322222222222222 = hashMap2;
                                                                iArr2 = iArr3;
                                                                if (z) {
                                                                }
                                                            } else {
                                                                if (jSONLexerBase.matchStat == -2) {
                                                                }
                                                                z = false;
                                                                z2 = false;
                                                                if (z) {
                                                                }
                                                                if (obj3 == null) {
                                                                }
                                                                Object obj7222222222222222 = obj3;
                                                                parseContext = parseContext4;
                                                                HashMap hashMap3222222222222222 = hashMap2;
                                                                iArr2 = iArr3;
                                                                if (z) {
                                                                }
                                                            }
                                                        } else if (jSONLexerBase.matchField(cArr)) {
                                                            z = true;
                                                            obj4 = null;
                                                            z2 = false;
                                                            if (z) {
                                                            }
                                                            if (obj3 == null) {
                                                            }
                                                            Object obj72222222222222222 = obj3;
                                                            parseContext = parseContext4;
                                                            HashMap hashMap32222222222222222 = hashMap2;
                                                            iArr2 = iArr3;
                                                            if (z) {
                                                            }
                                                        }
                                                    }
                                                    obj4 = Double.valueOf(jSONLexerBase.scanFieldDouble(cArr));
                                                    if (jSONLexerBase.matchStat > 0) {
                                                        z = true;
                                                        z2 = true;
                                                        if (z) {
                                                        }
                                                        if (obj3 == null) {
                                                        }
                                                        Object obj722222222222222222 = obj3;
                                                        parseContext = parseContext4;
                                                        HashMap hashMap322222222222222222 = hashMap2;
                                                        iArr2 = iArr3;
                                                        if (z) {
                                                        }
                                                    } else {
                                                        if (jSONLexerBase.matchStat == -2) {
                                                        }
                                                        z = false;
                                                        z2 = false;
                                                        if (z) {
                                                        }
                                                        if (obj3 == null) {
                                                        }
                                                        Object obj7222222222222222222 = obj3;
                                                        parseContext = parseContext4;
                                                        HashMap hashMap3222222222222222222 = hashMap2;
                                                        iArr2 = iArr3;
                                                        if (z) {
                                                        }
                                                    }
                                                }
                                                obj4 = Float.valueOf(jSONLexerBase.scanFieldFloat(cArr));
                                                if (jSONLexerBase.matchStat > 0) {
                                                    z = true;
                                                    z2 = true;
                                                    if (z) {
                                                    }
                                                    if (obj3 == null) {
                                                    }
                                                    Object obj72222222222222222222 = obj3;
                                                    parseContext = parseContext4;
                                                    HashMap hashMap32222222222222222222 = hashMap2;
                                                    iArr2 = iArr3;
                                                    if (z) {
                                                    }
                                                } else {
                                                    if (jSONLexerBase.matchStat == -2) {
                                                    }
                                                    z = false;
                                                    z2 = false;
                                                    if (z) {
                                                    }
                                                    if (obj3 == null) {
                                                    }
                                                    Object obj722222222222222222222 = obj3;
                                                    parseContext = parseContext4;
                                                    HashMap hashMap322222222222222222222 = hashMap2;
                                                    iArr2 = iArr3;
                                                    if (z) {
                                                    }
                                                }
                                            }
                                            obj4 = Boolean.valueOf(jSONLexerBase.scanFieldBoolean(cArr));
                                            if (jSONLexerBase.matchStat > 0) {
                                                z = true;
                                                z2 = true;
                                                if (z) {
                                                }
                                                if (obj3 == null) {
                                                }
                                                Object obj7222222222222222222222 = obj3;
                                                parseContext = parseContext4;
                                                HashMap hashMap3222222222222222222222 = hashMap2;
                                                iArr2 = iArr3;
                                                if (z) {
                                                }
                                            } else {
                                                if (jSONLexerBase.matchStat == -2) {
                                                }
                                                z = false;
                                                z2 = false;
                                                if (z) {
                                                }
                                                if (obj3 == null) {
                                                }
                                                Object obj72222222222222222222222 = obj3;
                                                parseContext = parseContext4;
                                                HashMap hashMap32222222222222222222222 = hashMap2;
                                                iArr2 = iArr3;
                                                if (z) {
                                                }
                                            }
                                        }
                                        str3 = str;
                                        i7 = i2 + 1;
                                        iArr3 = iArr2;
                                        hashMap2 = hashMap;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        obj3 = obj5;
                                        parseContext2 = parseContext;
                                        if (parseContext2 != null) {
                                            parseContext2.object = obj3;
                                        }
                                        defaultJSONParser.setContext(parseContext3);
                                        throw th;
                                    }
                                    str = str3;
                                    i3 = i4;
                                    hashMap = hashMap2;
                                    iArr2 = iArr3;
                                    c = 16;
                                }
                                obj4 = Long.valueOf(jSONLexerBase.scanFieldLong(cArr));
                                if (jSONLexerBase.matchStat > 0) {
                                    z = true;
                                    z2 = true;
                                    if (z) {
                                    }
                                    if (obj3 == null) {
                                    }
                                    Object obj722222222222222222222222 = obj3;
                                    parseContext = parseContext4;
                                    HashMap hashMap322222222222222222222222 = hashMap2;
                                    iArr2 = iArr3;
                                    if (z) {
                                    }
                                } else {
                                    if (jSONLexerBase.matchStat == -2) {
                                        str = str3;
                                        i3 = i4;
                                        hashMap = hashMap2;
                                        iArr2 = iArr3;
                                        c = 16;
                                        str3 = str;
                                        i7 = i2 + 1;
                                        iArr3 = iArr2;
                                        hashMap2 = hashMap;
                                    }
                                    z = false;
                                    z2 = false;
                                    if (z) {
                                    }
                                    if (obj3 == null) {
                                    }
                                    Object obj7222222222222222222222222 = obj3;
                                    parseContext = parseContext4;
                                    HashMap hashMap3222222222222222222222222 = hashMap2;
                                    iArr2 = iArr3;
                                    if (z) {
                                    }
                                }
                            }
                            obj4 = Integer.valueOf(jSONLexerBase.scanFieldInt(cArr));
                            if (jSONLexerBase.matchStat <= 0) {
                            }
                            z = true;
                            z2 = true;
                            if (z) {
                            }
                            if (obj3 == null) {
                            }
                            Object obj72222222222222222222222222 = obj3;
                            parseContext = parseContext4;
                            HashMap hashMap32222222222222222222222222 = hashMap2;
                            iArr2 = iArr3;
                            if (z) {
                            }
                        } else {
                            i2 = i7;
                        }
                        z = false;
                        obj4 = null;
                        z2 = false;
                        if (z) {
                        }
                        if (obj3 == null) {
                        }
                        Object obj722222222222222222222222222 = obj3;
                        parseContext = parseContext4;
                        HashMap hashMap322222222222222222222222222 = hashMap2;
                        iArr2 = iArr3;
                        if (z) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        parseContext2 = parseContext4;
                        if (parseContext2 != null) {
                        }
                        defaultJSONParser.setContext(parseContext3);
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                obj3 = obj2;
                if (parseContext2 != null) {
                }
                defaultJSONParser.setContext(parseContext3);
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    protected Enum scanEnum(JSONLexerBase jSONLexerBase, char[] cArr, ObjectDeserializer objectDeserializer) {
        EnumDeserializer enumDeserializer = objectDeserializer instanceof EnumDeserializer ? (EnumDeserializer) objectDeserializer : null;
        if (enumDeserializer == null) {
            jSONLexerBase.matchStat = -1;
            return null;
        }
        long scanFieldSymbol = jSONLexerBase.scanFieldSymbol(cArr);
        if (jSONLexerBase.matchStat > 0) {
            return enumDeserializer.getEnumByHashCode(scanFieldSymbol);
        }
        return null;
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        return parseField(defaultJSONParser, str, obj, type, map, null);
    }

    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v4 */
    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map, int[] iArr) {
        FieldDeserializer fieldDeserializer;
        ?? r17;
        JSONLexer jSONLexer;
        JSONLexer jSONLexer2;
        JSONLexer jSONLexer3 = defaultJSONParser.lexer;
        int i = Feature.DisableFieldSmartMatch.mask;
        if (jSONLexer3.isEnabled(i) || (i & this.beanInfo.parserFeatures) != 0) {
            fieldDeserializer = getFieldDeserializer(str);
        } else {
            fieldDeserializer = smartMatch(str, iArr);
        }
        int i2 = Feature.SupportNonPublicField.mask;
        if (fieldDeserializer != null || (!jSONLexer3.isEnabled(i2) && (i2 & this.beanInfo.parserFeatures) == 0)) {
            r17 = 1;
            jSONLexer = jSONLexer3;
        } else {
            if (this.extraFieldDeserializers == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(1, 0.75f, 1);
                for (Class<?> cls = this.clazz; cls != null && cls != Object.class; cls = cls.getSuperclass()) {
                    for (Field field : cls.getDeclaredFields()) {
                        String name = field.getName();
                        if (getFieldDeserializer(name) == null) {
                            int modifiers = field.getModifiers();
                            if ((modifiers & 16) == 0 && (modifiers & 8) == 0) {
                                concurrentHashMap.put(name, field);
                            }
                        }
                    }
                }
                this.extraFieldDeserializers = concurrentHashMap;
            }
            Object obj2 = this.extraFieldDeserializers.get(str);
            if (obj2 != null) {
                if (obj2 instanceof FieldDeserializer) {
                    fieldDeserializer = (FieldDeserializer) obj2;
                } else {
                    Field field2 = (Field) obj2;
                    field2.setAccessible(true);
                    r17 = 1;
                    jSONLexer = jSONLexer3;
                    fieldDeserializer = new DefaultFieldDeserializer(defaultJSONParser.getConfig(), this.clazz, new FieldInfo(str, field2.getDeclaringClass(), field2.getType(), field2.getGenericType(), field2, 0, 0, 0));
                    this.extraFieldDeserializers.put(str, fieldDeserializer);
                }
            }
            jSONLexer = jSONLexer3;
            r17 = 1;
        }
        if (fieldDeserializer == null) {
            if (!jSONLexer.isEnabled(Feature.IgnoreNotMatch)) {
                throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + str);
            }
            for (FieldDeserializer fieldDeserializer2 : this.sortedFieldDeserializers) {
                FieldInfo fieldInfo = fieldDeserializer2.fieldInfo;
                if (fieldInfo.unwrapped && (fieldDeserializer2 instanceof DefaultFieldDeserializer)) {
                    if (fieldInfo.field != null) {
                        DefaultFieldDeserializer defaultFieldDeserializer = (DefaultFieldDeserializer) fieldDeserializer2;
                        ObjectDeserializer fieldValueDeserilizer = defaultFieldDeserializer.getFieldValueDeserilizer(defaultJSONParser.getConfig());
                        if (fieldValueDeserilizer instanceof JavaBeanDeserializer) {
                            FieldDeserializer fieldDeserializer3 = ((JavaBeanDeserializer) fieldValueDeserilizer).getFieldDeserializer(str);
                            if (fieldDeserializer3 != null) {
                                try {
                                    Object obj3 = fieldInfo.field.get(obj);
                                    if (obj3 == null) {
                                        obj3 = ((JavaBeanDeserializer) fieldValueDeserilizer).createInstance(defaultJSONParser, fieldInfo.fieldType);
                                        fieldDeserializer2.setValue(obj, obj3);
                                    }
                                    jSONLexer.nextTokenWithColon(defaultFieldDeserializer.getFastMatchToken());
                                    fieldDeserializer3.parseField(defaultJSONParser, obj3, type, map);
                                    return r17;
                                } catch (Exception e) {
                                    throw new JSONException("parse unwrapped field error.", e);
                                }
                            }
                        } else if (fieldValueDeserilizer instanceof MapDeserializer) {
                            MapDeserializer mapDeserializer = (MapDeserializer) fieldValueDeserilizer;
                            try {
                                Map<Object, Object> map2 = (Map) fieldInfo.field.get(obj);
                                if (map2 == null) {
                                    map2 = mapDeserializer.createMap(fieldInfo.fieldType);
                                    fieldDeserializer2.setValue(obj, map2);
                                }
                                jSONLexer.nextTokenWithColon();
                                map2.put(str, defaultJSONParser.parse(str));
                                return r17;
                            } catch (Exception e2) {
                                throw new JSONException("parse unwrapped field error.", e2);
                            }
                        }
                    } else if (fieldInfo.method.getParameterTypes().length == 2) {
                        jSONLexer.nextTokenWithColon();
                        Object parse = defaultJSONParser.parse(str);
                        try {
                            Method method = fieldInfo.method;
                            Object[] objArr = new Object[2];
                            objArr[0] = str;
                            objArr[r17] = parse;
                            method.invoke(obj, objArr);
                            return r17;
                        } catch (Exception e3) {
                            throw new JSONException("parse unwrapped field error.", e3);
                        }
                    }
                }
            }
            defaultJSONParser.parseExtra(obj, str);
            return false;
        }
        int i3 = 0;
        while (true) {
            FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
            if (i3 >= fieldDeserializerArr.length) {
                i3 = -1;
                break;
            }
            if (fieldDeserializerArr[i3] == fieldDeserializer) {
                break;
            }
            i3++;
        }
        if (i3 != -1) {
            jSONLexer2 = jSONLexer;
            if (iArr != null && str.startsWith("_") && isSetFlag(i3, iArr)) {
                defaultJSONParser.parseExtra(obj, str);
                return false;
            }
        } else {
            jSONLexer2 = jSONLexer;
        }
        jSONLexer2.nextTokenWithColon(fieldDeserializer.getFastMatchToken());
        fieldDeserializer.parseField(defaultJSONParser, obj, type, map);
        return r17;
    }

    public FieldDeserializer smartMatch(String str) {
        return smartMatch(str, null);
    }

    public FieldDeserializer smartMatch(String str, int[] iArr) {
        boolean z;
        if (str == null) {
            return null;
        }
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str, iArr);
        if (fieldDeserializer == null) {
            long fnv1a_64_lower = TypeUtils.fnv1a_64_lower(str);
            int i = 0;
            if (this.smartMatchHashArray == null) {
                long[] jArr = new long[this.sortedFieldDeserializers.length];
                int i2 = 0;
                while (true) {
                    FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                    if (i2 >= fieldDeserializerArr.length) {
                        break;
                    }
                    jArr[i2] = TypeUtils.fnv1a_64_lower(fieldDeserializerArr[i2].fieldInfo.name);
                    i2++;
                }
                Arrays.sort(jArr);
                this.smartMatchHashArray = jArr;
            }
            int binarySearch = Arrays.binarySearch(this.smartMatchHashArray, fnv1a_64_lower);
            if (binarySearch < 0) {
                z = str.startsWith("is");
                if (z) {
                    binarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(str.substring(2)));
                }
            } else {
                z = false;
            }
            if (binarySearch >= 0) {
                if (this.smartMatchHashArrayMapping == null) {
                    short[] sArr = new short[this.smartMatchHashArray.length];
                    Arrays.fill(sArr, (short) -1);
                    while (true) {
                        FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                        if (i >= fieldDeserializerArr2.length) {
                            break;
                        }
                        int binarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(fieldDeserializerArr2[i].fieldInfo.name));
                        if (binarySearch2 >= 0) {
                            sArr[binarySearch2] = (short) i;
                        }
                        i++;
                    }
                    this.smartMatchHashArrayMapping = sArr;
                }
                short s = this.smartMatchHashArrayMapping[binarySearch];
                if (s != -1 && !isSetFlag(s, iArr)) {
                    fieldDeserializer = this.sortedFieldDeserializers[s];
                }
            }
            if (fieldDeserializer != null) {
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                if ((fieldInfo.parserFeatures & Feature.DisableFieldSmartMatch.mask) != 0) {
                    return null;
                }
                Class<?> cls = fieldInfo.fieldClass;
                if (z && cls != Boolean.TYPE && cls != Boolean.class) {
                    return null;
                }
            }
        }
        return fieldDeserializer;
    }

    public Object createInstance(Map<String, Object> map, ParserConfig parserConfig) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (this.beanInfo.creatorConstructor == null && this.beanInfo.factoryMethod == null) {
            Object createInstance = createInstance((DefaultJSONParser) null, this.clazz);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                FieldDeserializer smartMatch = smartMatch(key);
                if (smartMatch != null) {
                    smartMatch.setValue(createInstance, TypeUtils.cast(value, smartMatch.fieldInfo.fieldType, parserConfig));
                }
            }
            if (this.beanInfo.buildMethod == null) {
                return createInstance;
            }
            try {
                return this.beanInfo.buildMethod.invoke(createInstance, new Object[0]);
            } catch (Exception e) {
                throw new JSONException("build object error", e);
            }
        }
        FieldInfo[] fieldInfoArr = this.beanInfo.fields;
        int length = fieldInfoArr.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            FieldInfo fieldInfo = fieldInfoArr[i];
            Object obj = map.get(fieldInfo.name);
            if (obj == null) {
                Class<?> cls = fieldInfo.fieldClass;
                if (cls == Integer.TYPE) {
                    obj = 0;
                } else if (cls == Long.TYPE) {
                    obj = 0L;
                } else if (cls == Short.TYPE) {
                    obj = (short) 0;
                } else if (cls == Byte.TYPE) {
                    obj = (byte) 0;
                } else if (cls == Float.TYPE) {
                    obj = Float.valueOf(0.0f);
                } else if (cls == Double.TYPE) {
                    obj = Double.valueOf(0.0d);
                } else if (cls == Character.TYPE) {
                    obj = '0';
                } else if (cls == Boolean.TYPE) {
                    obj = false;
                }
            }
            objArr[i] = obj;
        }
        if (this.beanInfo.creatorConstructor != null) {
            try {
                return this.beanInfo.creatorConstructor.newInstance(objArr);
            } catch (Exception e2) {
                throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e2);
            }
        }
        if (this.beanInfo.factoryMethod == null) {
            return null;
        }
        try {
            return this.beanInfo.factoryMethod.invoke(null, objArr);
        } catch (Exception e3) {
            throw new JSONException("create factory method error, " + this.beanInfo.factoryMethod.toString(), e3);
        }
    }

    public Type getFieldType(int i) {
        return this.sortedFieldDeserializers[i].fieldInfo.fieldType;
    }

    protected Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i) {
        return parseRest(defaultJSONParser, type, obj, obj2, i, new int[0]);
    }

    protected Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i, int[] iArr) {
        return deserialze(defaultJSONParser, type, obj, obj2, i, iArr);
    }

    protected JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        if (javaBeanInfo.jsonType == null) {
            return null;
        }
        for (Class<?> cls : javaBeanInfo.jsonType.seeAlso()) {
            ObjectDeserializer deserializer = parserConfig.getDeserializer(cls);
            if (deserializer instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                JavaBeanDeserializer seeAlso = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (seeAlso != null) {
                    return seeAlso;
                }
            }
        }
        return null;
    }

    protected static void parseArray(Collection collection, ObjectDeserializer objectDeserializer, DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
        int i = jSONLexerBase.token();
        if (i == 8) {
            jSONLexerBase.nextToken(16);
            jSONLexerBase.token();
            return;
        }
        if (i != 14) {
            defaultJSONParser.throwException(i);
        }
        if (jSONLexerBase.getCurrent() == '[') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(14);
        } else {
            jSONLexerBase.nextToken(14);
        }
        if (jSONLexerBase.token() == 15) {
            jSONLexerBase.nextToken();
            return;
        }
        int i2 = 0;
        while (true) {
            collection.add(objectDeserializer.deserialze(defaultJSONParser, type, Integer.valueOf(i2)));
            i2++;
            if (jSONLexerBase.token() != 16) {
                break;
            }
            if (jSONLexerBase.getCurrent() == '[') {
                jSONLexerBase.next();
                jSONLexerBase.setToken(14);
            } else {
                jSONLexerBase.nextToken(14);
            }
        }
        int i3 = jSONLexerBase.token();
        if (i3 != 15) {
            defaultJSONParser.throwException(i3);
        }
        if (jSONLexerBase.getCurrent() == ',') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(16);
        } else {
            jSONLexerBase.nextToken(16);
        }
    }
}
