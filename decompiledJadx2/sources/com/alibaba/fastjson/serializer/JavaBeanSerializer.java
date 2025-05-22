package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class JavaBeanSerializer extends SerializeFilterable implements ObjectSerializer {
    protected SerializeBeanInfo beanInfo;
    protected final FieldSerializer[] getters;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;
    protected final FieldSerializer[] sortedGetters;

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, createAliasMap(strArr));
    }

    static Map<String, String> createAliasMap(String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            hashMap.put(str, str);
        }
        return hashMap;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(TypeUtils.buildBeanInfo(cls, map, null));
    }

    public JavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        this.beanInfo = serializeBeanInfo;
        this.sortedGetters = new FieldSerializer[serializeBeanInfo.sortedFields.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            FieldSerializer[] fieldSerializerArr = this.sortedGetters;
            if (i2 >= fieldSerializerArr.length) {
                break;
            }
            fieldSerializerArr[i2] = new FieldSerializer(serializeBeanInfo.beanType, serializeBeanInfo.sortedFields[i2]);
            i2++;
        }
        if (serializeBeanInfo.fields == serializeBeanInfo.sortedFields) {
            this.getters = this.sortedGetters;
            return;
        }
        this.getters = new FieldSerializer[serializeBeanInfo.fields.length];
        while (true) {
            FieldSerializer[] fieldSerializerArr2 = this.getters;
            if (i >= fieldSerializerArr2.length) {
                return;
            }
            fieldSerializerArr2[i] = getFieldSerializer(serializeBeanInfo.fields[i].name);
            i++;
        }
    }

    public void writeDirectNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArray(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArrayNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    public void writeNoneASM(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00e4, code lost:
    
        if (r10.fieldTransient != false) goto L247;
     */
    /* JADX WARN: Removed duplicated region for block: B:195:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0317 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02e6 A[Catch: Exception -> 0x0329, all -> 0x0360, TryCatch #0 {all -> 0x0360, blocks: (B:52:0x00d8, B:56:0x00e2, B:83:0x00f2, B:85:0x00f8, B:88:0x0102, B:90:0x0108, B:92:0x0112, B:95:0x011a, B:257:0x0121, B:261:0x030e, B:96:0x012a, B:101:0x0136, B:104:0x0142, B:105:0x0148, B:108:0x0167, B:110:0x016b, B:115:0x0179, B:117:0x017d, B:119:0x0186, B:121:0x0191, B:123:0x0197, B:125:0x019b, B:129:0x01a6, B:131:0x01aa, B:133:0x01ae, B:136:0x01b8, B:138:0x01bc, B:140:0x01c0, B:143:0x01cb, B:145:0x01cf, B:147:0x01d3, B:150:0x01e1, B:152:0x01e5, B:154:0x01e9, B:157:0x01f6, B:159:0x01fa, B:161:0x01fe, B:164:0x020c, B:166:0x0210, B:168:0x0214, B:172:0x0220, B:174:0x0224, B:176:0x0228, B:178:0x0233, B:180:0x023e, B:184:0x0247, B:185:0x024d, B:187:0x02d4, B:189:0x02d8, B:191:0x02dc, B:198:0x02e6, B:200:0x02ee, B:201:0x02f6, B:203:0x02fc, B:217:0x0258, B:218:0x025b, B:220:0x0261, B:223:0x0267, B:225:0x0279, B:228:0x0283, B:231:0x028d, B:233:0x0296, B:236:0x02a0, B:237:0x02a4, B:238:0x02aa, B:240:0x02b1, B:241:0x02b5, B:242:0x02b9, B:244:0x02bd, B:246:0x02c1, B:250:0x02cd, B:251:0x02d1, B:252:0x0271, B:276:0x033e, B:278:0x0346, B:280:0x034e, B:282:0x0356), top: B:51:0x00d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0356 A[Catch: all -> 0x0360, Exception -> 0x0364, TRY_LEAVE, TryCatch #0 {all -> 0x0360, blocks: (B:52:0x00d8, B:56:0x00e2, B:83:0x00f2, B:85:0x00f8, B:88:0x0102, B:90:0x0108, B:92:0x0112, B:95:0x011a, B:257:0x0121, B:261:0x030e, B:96:0x012a, B:101:0x0136, B:104:0x0142, B:105:0x0148, B:108:0x0167, B:110:0x016b, B:115:0x0179, B:117:0x017d, B:119:0x0186, B:121:0x0191, B:123:0x0197, B:125:0x019b, B:129:0x01a6, B:131:0x01aa, B:133:0x01ae, B:136:0x01b8, B:138:0x01bc, B:140:0x01c0, B:143:0x01cb, B:145:0x01cf, B:147:0x01d3, B:150:0x01e1, B:152:0x01e5, B:154:0x01e9, B:157:0x01f6, B:159:0x01fa, B:161:0x01fe, B:164:0x020c, B:166:0x0210, B:168:0x0214, B:172:0x0220, B:174:0x0224, B:176:0x0228, B:178:0x0233, B:180:0x023e, B:184:0x0247, B:185:0x024d, B:187:0x02d4, B:189:0x02d8, B:191:0x02dc, B:198:0x02e6, B:200:0x02ee, B:201:0x02f6, B:203:0x02fc, B:217:0x0258, B:218:0x025b, B:220:0x0261, B:223:0x0267, B:225:0x0279, B:228:0x0283, B:231:0x028d, B:233:0x0296, B:236:0x02a0, B:237:0x02a4, B:238:0x02aa, B:240:0x02b1, B:241:0x02b5, B:242:0x02b9, B:244:0x02bd, B:246:0x02c1, B:250:0x02cd, B:251:0x02d1, B:252:0x0271, B:276:0x033e, B:278:0x0346, B:280:0x034e, B:282:0x0356), top: B:51:0x00d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:292:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ce A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0392 A[Catch: all -> 0x03ca, TryCatch #7 {all -> 0x03ca, blocks: (B:80:0x0372, B:69:0x0392, B:70:0x03a6, B:72:0x03ac, B:73:0x03c4, B:74:0x03c9), top: B:79:0x0372 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x03ac A[Catch: all -> 0x03ca, TryCatch #7 {all -> 0x03ca, blocks: (B:80:0x0372, B:69:0x0392, B:70:0x03a6, B:72:0x03ac, B:73:0x03c4, B:74:0x03c9), top: B:79:0x0372 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0372 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i, boolean z) throws IOException {
        FieldSerializer[] fieldSerializerArr;
        SerialContext serialContext;
        Object obj3;
        boolean z2;
        int i2;
        FieldSerializer[] fieldSerializerArr2;
        SerialContext serialContext2;
        Object obj4;
        int i3;
        char c;
        char c2;
        char c3;
        char c4;
        char c5;
        Map map;
        char c6;
        Object obj5 = obj;
        Type type2 = type;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj5 == null) {
            serializeWriter.writeNull();
            return;
        }
        if (writeReference(jSONSerializer, obj5, i)) {
            return;
        }
        if (serializeWriter.sortField) {
            fieldSerializerArr = this.sortedGetters;
        } else {
            fieldSerializerArr = this.getters;
        }
        FieldSerializer[] fieldSerializerArr3 = fieldSerializerArr;
        SerialContext serialContext3 = jSONSerializer.context;
        if (!this.beanInfo.beanType.isEnum()) {
            jSONSerializer.setContext(serialContext3, obj, obj2, this.beanInfo.features, i);
        }
        boolean isWriteAsArray = isWriteAsArray(jSONSerializer, i);
        char c7 = isWriteAsArray ? '[' : '{';
        char c8 = isWriteAsArray ? ']' : '}';
        if (!z) {
            try {
                try {
                    serializeWriter.append(c7);
                } catch (Throwable th) {
                    th = th;
                    serialContext = serialContext3;
                    jSONSerializer.context = serialContext;
                    throw th;
                }
            } catch (Exception e) {
                e = e;
                obj3 = obj5;
                serialContext = serialContext3;
                String str = "write javaBean error, fastjson version 1.2.40";
                if (obj3 != null) {
                }
                if (obj2 != null) {
                }
                if (e.getMessage() != null) {
                }
                throw new JSONException(str, e);
            }
        }
        if (fieldSerializerArr3.length > 0 && serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
            jSONSerializer.incrementIndent();
            jSONSerializer.println();
        }
        char c9 = 1;
        try {
            if ((this.beanInfo.features & SerializerFeature.WriteClassName.mask) != 0 || (i & SerializerFeature.WriteClassName.mask) != 0 || jSONSerializer.isWriteClassName(type2, obj5)) {
                Class<?> cls = obj.getClass();
                if (cls != ((cls == type2 || !(type2 instanceof WildcardType)) ? type2 : TypeUtils.getClass(type))) {
                    writeClassName(jSONSerializer, this.beanInfo.typeKey, obj5);
                    z2 = true;
                    char c10 = ',';
                    char c11 = !z2 ? ',' : (char) 0;
                    boolean z3 = (serializeWriter.quoteFieldNames || serializeWriter.useSingleQuotes) ? false : true;
                    char c12 = writeBefore(jSONSerializer, obj5, c11) != ',' ? (char) 1 : (char) 0;
                    boolean isEnabled = serializeWriter.isEnabled(SerializerFeature.SkipTransientField);
                    boolean isEnabled2 = serializeWriter.isEnabled(SerializerFeature.IgnoreNonFieldGetter);
                    char c13 = c12;
                    i2 = 0;
                    while (i2 < fieldSerializerArr3.length) {
                        try {
                            FieldSerializer fieldSerializer = fieldSerializerArr3[i2];
                            Field field = fieldSerializer.fieldInfo.field;
                            FieldInfo fieldInfo = fieldSerializer.fieldInfo;
                            serialContext2 = serialContext3;
                            try {
                                try {
                                    String str2 = fieldInfo.name;
                                    FieldSerializer[] fieldSerializerArr4 = fieldSerializerArr3;
                                    Class<?> cls2 = fieldInfo.fieldClass;
                                    if (isEnabled && field != null) {
                                        try {
                                        } catch (Exception e2) {
                                            e = e2;
                                            obj3 = obj5;
                                            serialContext = serialContext2;
                                            String str3 = "write javaBean error, fastjson version 1.2.40";
                                            if (obj3 != null) {
                                            }
                                            if (obj2 != null) {
                                            }
                                            if (e.getMessage() != null) {
                                            }
                                            throw new JSONException(str3, e);
                                        }
                                    }
                                    if ((!isEnabled2 || field != null) && applyName(jSONSerializer, obj5, str2) && applyLabel(jSONSerializer, fieldInfo.label) && (this.beanInfo.typeKey == null || !str2.equals(this.beanInfo.typeKey) || !jSONSerializer.isWriteClassName(type2, obj5))) {
                                        try {
                                            obj4 = fieldSerializer.getPropertyValueDirect(obj5);
                                        } catch (InvocationTargetException e3) {
                                            if (!serializeWriter.isEnabled(SerializerFeature.IgnoreErrorGetter)) {
                                                throw e3;
                                            }
                                            obj4 = null;
                                        }
                                        if (apply(jSONSerializer, obj5, str2, obj4)) {
                                            if (cls2 == String.class && "trim".equals(fieldInfo.format) && obj4 != null) {
                                                obj4 = ((String) obj4).trim();
                                            }
                                            String processKey = processKey(jSONSerializer, obj5, str2, obj4);
                                            i3 = i2;
                                            c = ',';
                                            c2 = c8;
                                            Object processValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str2, obj4);
                                            if (processValue != null || isWriteAsArray || fieldSerializer.writeNull || serializeWriter.isEnabled(SerializerFeature.WRITE_MAP_NULL_FEATURES)) {
                                                if (processValue != null && (serializeWriter.notWriteDefaultValue || (fieldInfo.serialzeFeatures & SerializerFeature.NotWriteDefaultValue.mask) != 0 || (this.beanInfo.features & SerializerFeature.NotWriteDefaultValue.mask) != 0)) {
                                                    Class<?> cls3 = fieldInfo.fieldClass;
                                                    if (cls3 != Byte.TYPE || !(processValue instanceof Byte) || ((Byte) processValue).byteValue() != 0) {
                                                        if (cls3 != Short.TYPE || !(processValue instanceof Short) || ((Short) processValue).shortValue() != 0) {
                                                            if (cls3 != Integer.TYPE || !(processValue instanceof Integer) || ((Integer) processValue).intValue() != 0) {
                                                                if (cls3 != Long.TYPE || !(processValue instanceof Long) || ((Long) processValue).longValue() != 0) {
                                                                    if (cls3 != Float.TYPE || !(processValue instanceof Float) || ((Float) processValue).floatValue() != 0.0f) {
                                                                        if (cls3 != Double.TYPE || !(processValue instanceof Double) || ((Double) processValue).doubleValue() != 0.0d) {
                                                                            if (cls3 == Boolean.TYPE && (processValue instanceof Boolean) && !((Boolean) processValue).booleanValue()) {
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                if (c13 != 0) {
                                                    if (!fieldInfo.unwrapped || !(processValue instanceof Map) || ((Map) processValue).size() != 0) {
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                }
                                                if (processKey != str2) {
                                                    if (isWriteAsArray) {
                                                        c3 = 1;
                                                    } else {
                                                        c3 = 1;
                                                        serializeWriter.writeFieldName(processKey, true);
                                                    }
                                                    jSONSerializer.write(processValue);
                                                } else {
                                                    c3 = 1;
                                                    if (obj4 != processValue) {
                                                        if (!isWriteAsArray) {
                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                        }
                                                        jSONSerializer.write(processValue);
                                                    } else {
                                                        if (isWriteAsArray || fieldInfo.unwrapped) {
                                                            c4 = 0;
                                                        } else if (z3) {
                                                            c4 = 0;
                                                            serializeWriter.write(fieldInfo.name_chars, 0, fieldInfo.name_chars.length);
                                                        } else {
                                                            c4 = 0;
                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                        }
                                                        if (!isWriteAsArray) {
                                                            JSONField annotation = fieldInfo.getAnnotation();
                                                            if (cls2 == String.class && (annotation == null || annotation.serializeUsing() == Void.class)) {
                                                                if (processValue == null) {
                                                                    if ((serializeWriter.features & SerializerFeature.WriteNullStringAsEmpty.mask) == 0 && (fieldSerializer.features & SerializerFeature.WriteNullStringAsEmpty.mask) == 0) {
                                                                        serializeWriter.writeNull();
                                                                    }
                                                                    serializeWriter.writeString("");
                                                                } else {
                                                                    String str4 = (String) processValue;
                                                                    if (serializeWriter.useSingleQuotes) {
                                                                        serializeWriter.writeStringWithSingleQuote(str4);
                                                                    } else {
                                                                        serializeWriter.writeStringWithDoubleQuote(str4, c4);
                                                                    }
                                                                }
                                                            } else if (fieldInfo.unwrapped && (processValue instanceof Map) && ((Map) processValue).size() == 0) {
                                                                c13 = c4;
                                                                i2 = i3 + 1;
                                                                type2 = type;
                                                                c9 = c3;
                                                                c10 = c;
                                                                serialContext3 = serialContext2;
                                                                fieldSerializerArr3 = fieldSerializerArr4;
                                                                c8 = c2;
                                                                obj5 = obj;
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer, processValue);
                                                            }
                                                        } else {
                                                            fieldSerializer.writeValue(jSONSerializer, processValue);
                                                        }
                                                        if (fieldInfo.unwrapped && (processValue instanceof Map)) {
                                                            map = (Map) processValue;
                                                            if (map.size() != 0) {
                                                                if (!jSONSerializer.isEnabled(SerializerFeature.WriteMapNullValue)) {
                                                                    Iterator it = map.values().iterator();
                                                                    while (true) {
                                                                        if (!it.hasNext()) {
                                                                            c6 = c4;
                                                                            break;
                                                                        } else if (it.next() != null) {
                                                                            c6 = c3;
                                                                            break;
                                                                        }
                                                                    }
                                                                    if (c6 == 0) {
                                                                    }
                                                                }
                                                            }
                                                            c5 = c3;
                                                            if (c5 != 0) {
                                                                c13 = c3;
                                                            }
                                                            i2 = i3 + 1;
                                                            type2 = type;
                                                            c9 = c3;
                                                            c10 = c;
                                                            serialContext3 = serialContext2;
                                                            fieldSerializerArr3 = fieldSerializerArr4;
                                                            c8 = c2;
                                                            obj5 = obj;
                                                        }
                                                        c5 = c4;
                                                        if (c5 != 0) {
                                                        }
                                                        i2 = i3 + 1;
                                                        type2 = type;
                                                        c9 = c3;
                                                        c10 = c;
                                                        serialContext3 = serialContext2;
                                                        fieldSerializerArr3 = fieldSerializerArr4;
                                                        c8 = c2;
                                                        obj5 = obj;
                                                    }
                                                }
                                                c4 = 0;
                                                if (fieldInfo.unwrapped) {
                                                    map = (Map) processValue;
                                                    if (map.size() != 0) {
                                                    }
                                                    c5 = c3;
                                                    if (c5 != 0) {
                                                    }
                                                    i2 = i3 + 1;
                                                    type2 = type;
                                                    c9 = c3;
                                                    c10 = c;
                                                    serialContext3 = serialContext2;
                                                    fieldSerializerArr3 = fieldSerializerArr4;
                                                    c8 = c2;
                                                    obj5 = obj;
                                                }
                                                c5 = c4;
                                                if (c5 != 0) {
                                                }
                                                i2 = i3 + 1;
                                                type2 = type;
                                                c9 = c3;
                                                c10 = c;
                                                serialContext3 = serialContext2;
                                                fieldSerializerArr3 = fieldSerializerArr4;
                                                c8 = c2;
                                                obj5 = obj;
                                            }
                                            c3 = 1;
                                            i2 = i3 + 1;
                                            type2 = type;
                                            c9 = c3;
                                            c10 = c;
                                            serialContext3 = serialContext2;
                                            fieldSerializerArr3 = fieldSerializerArr4;
                                            c8 = c2;
                                            obj5 = obj;
                                        }
                                    }
                                    i3 = i2;
                                    c3 = c9;
                                    c2 = c8;
                                    c = ',';
                                    i2 = i3 + 1;
                                    type2 = type;
                                    c9 = c3;
                                    c10 = c;
                                    serialContext3 = serialContext2;
                                    fieldSerializerArr3 = fieldSerializerArr4;
                                    c8 = c2;
                                    obj5 = obj;
                                } catch (Exception e4) {
                                    e = e4;
                                    obj3 = obj;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                serialContext = serialContext2;
                                jSONSerializer.context = serialContext;
                                throw th;
                            }
                        } catch (Exception e5) {
                            e = e5;
                            obj3 = obj;
                            serialContext = serialContext3;
                            String str32 = "write javaBean error, fastjson version 1.2.40";
                            if (obj3 != null) {
                                try {
                                    str32 = "write javaBean error, fastjson version 1.2.40, class " + obj.getClass().getName();
                                } catch (Throwable th3) {
                                    th = th3;
                                    jSONSerializer.context = serialContext;
                                    throw th;
                                }
                            }
                            if (obj2 != null) {
                                str32 = str32 + ", fieldName : " + obj2;
                            }
                            if (e.getMessage() != null) {
                                str32 = str32 + ", " + e.getMessage();
                            }
                            throw new JSONException(str32, e);
                        }
                    }
                    char c14 = c8;
                    fieldSerializerArr2 = fieldSerializerArr3;
                    serialContext2 = serialContext3;
                    obj3 = obj;
                    writeAfter(jSONSerializer, obj3, c13 != 0 ? c10 : (char) 0);
                    if (fieldSerializerArr2.length > 0 && serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                        jSONSerializer.decrementIdent();
                        jSONSerializer.println();
                    }
                    if (!z) {
                        serializeWriter.append(c14);
                    }
                    jSONSerializer.context = serialContext2;
                    return;
                }
            }
            writeAfter(jSONSerializer, obj3, c13 != 0 ? c10 : (char) 0);
            if (fieldSerializerArr2.length > 0) {
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
            }
            if (!z) {
            }
            jSONSerializer.context = serialContext2;
            return;
        } catch (Exception e6) {
            e = e6;
            serialContext = serialContext2;
            String str322 = "write javaBean error, fastjson version 1.2.40";
            if (obj3 != null) {
            }
            if (obj2 != null) {
            }
            if (e.getMessage() != null) {
            }
            throw new JSONException(str322, e);
        }
        z2 = false;
        char c102 = ',';
        if (!z2) {
        }
        if (serializeWriter.quoteFieldNames) {
        }
        if (writeBefore(jSONSerializer, obj5, c11) != ',') {
        }
        boolean isEnabled3 = serializeWriter.isEnabled(SerializerFeature.SkipTransientField);
        boolean isEnabled22 = serializeWriter.isEnabled(SerializerFeature.IgnoreNonFieldGetter);
        char c132 = c12;
        i2 = 0;
        while (i2 < fieldSerializerArr3.length) {
        }
        char c142 = c8;
        fieldSerializerArr2 = fieldSerializerArr3;
        serialContext2 = serialContext3;
        obj3 = obj;
    }

    protected void writeClassName(JSONSerializer jSONSerializer, String str, Object obj) {
        if (str == null) {
            str = jSONSerializer.config.typeKey;
        }
        jSONSerializer.out.writeFieldName(str, false);
        String str2 = this.beanInfo.typeName;
        if (str2 == null) {
            Class<?> cls = obj.getClass();
            if (TypeUtils.isProxy(cls)) {
                cls = cls.getSuperclass();
            }
            str2 = cls.getName();
        }
        jSONSerializer.write(str2);
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i) {
        SerialContext serialContext = jSONSerializer.context;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (serialContext == null || (serialContext.features & i2) != 0 || (i & i2) != 0 || jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return isWriteAsArray(jSONSerializer, 0);
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer, int i) {
        int i2 = SerializerFeature.BeanToArray.mask;
        return ((this.beanInfo.features & i2) == 0 && !jSONSerializer.out.beanToArray && (i & i2) == 0) ? false : true;
    }

    public Object getFieldValue(Object obj, String str) {
        FieldSerializer fieldSerializer = getFieldSerializer(str);
        if (fieldSerializer == null) {
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public Object getFieldValue(Object obj, String str, long j, boolean z) {
        FieldSerializer fieldSerializer = getFieldSerializer(j);
        if (fieldSerializer == null) {
            if (!z) {
                return null;
            }
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedGetters.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedGetters[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else {
                if (compareTo <= 0) {
                    return this.sortedGetters[i2];
                }
                length = i2 - 1;
            }
        }
        return null;
    }

    public FieldSerializer getFieldSerializer(long j) {
        PropertyNamingStrategy[] propertyNamingStrategyArr;
        int binarySearch;
        if (this.hashArray == null) {
            propertyNamingStrategyArr = PropertyNamingStrategy.values();
            long[] jArr = new long[this.sortedGetters.length * propertyNamingStrategyArr.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr = this.sortedGetters;
                if (i >= fieldSerializerArr.length) {
                    break;
                }
                String str = fieldSerializerArr[i].fieldInfo.name;
                jArr[i2] = TypeUtils.fnv1a_64(str);
                i2++;
                for (PropertyNamingStrategy propertyNamingStrategy : propertyNamingStrategyArr) {
                    String translate = propertyNamingStrategy.translate(str);
                    if (!str.equals(translate)) {
                        jArr[i2] = TypeUtils.fnv1a_64(translate);
                        i2++;
                    }
                }
                i++;
            }
            Arrays.sort(jArr, 0, i2);
            this.hashArray = new long[i2];
            System.arraycopy(jArr, 0, this.hashArray, 0, i2);
        } else {
            propertyNamingStrategyArr = null;
        }
        int binarySearch2 = Arrays.binarySearch(this.hashArray, j);
        if (binarySearch2 < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (propertyNamingStrategyArr == null) {
                propertyNamingStrategyArr = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            int i3 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                if (i3 >= fieldSerializerArr2.length) {
                    break;
                }
                String str2 = fieldSerializerArr2[i3].fieldInfo.name;
                int binarySearch3 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(str2));
                if (binarySearch3 >= 0) {
                    sArr[binarySearch3] = (short) i3;
                }
                for (PropertyNamingStrategy propertyNamingStrategy2 : propertyNamingStrategyArr) {
                    String translate2 = propertyNamingStrategy2.translate(str2);
                    if (!str2.equals(translate2) && (binarySearch = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(translate2))) >= 0) {
                        sArr[binarySearch] = (short) i3;
                    }
                }
                i3++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[binarySearch2];
        if (s != -1) {
            return this.sortedGetters[s];
        }
        return null;
    }

    public List<Object> getFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            arrayList.add(fieldSerializer.getPropertyValue(obj));
        }
        return arrayList;
    }

    public List<Object> getObjectFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (!cls.isPrimitive() && !cls.getName().startsWith("java.lang.")) {
                arrayList.add(fieldSerializer.getPropertyValue(obj));
            }
        }
        return arrayList;
    }

    public int getSize(Object obj) throws Exception {
        int i = 0;
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                i++;
            }
        }
        return i;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
        }
        return linkedHashMap;
    }

    protected BeanContext getBeanContext(int i) {
        return this.sortedGetters[i].fieldContext;
    }

    protected Type getFieldType(int i) {
        return this.sortedGetters[i].fieldInfo.fieldType;
    }

    protected char writeBefore(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.beforeFilters != null) {
            Iterator<BeforeFilter> it = jSONSerializer.beforeFilters.iterator();
            while (it.hasNext()) {
                c = it.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        if (this.beforeFilters != null) {
            Iterator<BeforeFilter> it2 = this.beforeFilters.iterator();
            while (it2.hasNext()) {
                c = it2.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    protected char writeAfter(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.afterFilters != null) {
            Iterator<AfterFilter> it = jSONSerializer.afterFilters.iterator();
            while (it.hasNext()) {
                c = it.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        if (this.afterFilters != null) {
            Iterator<AfterFilter> it2 = this.afterFilters.iterator();
            while (it2.hasNext()) {
                c = it2.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    protected boolean applyLabel(JSONSerializer jSONSerializer, String str) {
        if (jSONSerializer.labelFilters != null) {
            Iterator<LabelFilter> it = jSONSerializer.labelFilters.iterator();
            while (it.hasNext()) {
                if (!it.next().apply(str)) {
                    return false;
                }
            }
        }
        if (this.labelFilters == null) {
            return true;
        }
        Iterator<LabelFilter> it2 = this.labelFilters.iterator();
        while (it2.hasNext()) {
            if (!it2.next().apply(str)) {
                return false;
            }
        }
        return true;
    }
}
