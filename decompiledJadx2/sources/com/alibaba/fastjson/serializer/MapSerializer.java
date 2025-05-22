package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MapSerializer extends SerializeFilterable implements ObjectSerializer {
    public static MapSerializer instance = new MapSerializer();
    private static final int NON_STRINGKEY_AS_STRING = SerializerFeature.m85of(new SerializerFeature[]{SerializerFeature.BrowserCompatible, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.BrowserSecure});

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01fc A[Catch: all -> 0x02fc, TryCatch #0 {all -> 0x02fc, blocks: (B:19:0x0052, B:23:0x0055, B:25:0x0061, B:33:0x0080, B:34:0x0091, B:35:0x00a1, B:37:0x00a7, B:39:0x00b9, B:42:0x00c1, B:45:0x00c6, B:47:0x00d0, B:49:0x00d4, B:57:0x00df, B:60:0x00ec, B:62:0x00f0, B:65:0x00f8, B:68:0x00fd, B:70:0x0107, B:72:0x010b, B:76:0x0116, B:80:0x0120, B:82:0x0124, B:85:0x012c, B:88:0x0131, B:90:0x013b, B:92:0x013f, B:96:0x014a, B:100:0x0154, B:102:0x0158, B:105:0x0160, B:108:0x0165, B:110:0x016f, B:112:0x0173, B:116:0x017f, B:120:0x018a, B:122:0x018e, B:125:0x0196, B:128:0x019b, B:130:0x01a5, B:132:0x01a9, B:133:0x01b2, B:134:0x01b8, B:136:0x01bc, B:139:0x01c4, B:142:0x01c9, B:144:0x01d3, B:146:0x01d7, B:147:0x01e0, B:150:0x01e9, B:153:0x01ee, B:155:0x01f2, B:160:0x01fc, B:163:0x0235, B:166:0x0242, B:168:0x0248, B:170:0x024d, B:171:0x0250, B:173:0x0258, B:174:0x025b, B:176:0x0282, B:179:0x028c, B:181:0x0294, B:182:0x029d, B:184:0x02a7, B:186:0x02ab, B:188:0x02af, B:190:0x02ba, B:191:0x02c0, B:194:0x02ce, B:197:0x0261, B:198:0x0264, B:200:0x026c, B:202:0x0270, B:203:0x027b, B:204:0x0278, B:209:0x021d, B:221:0x0075), top: B:18:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0235 A[Catch: all -> 0x02fc, TryCatch #0 {all -> 0x02fc, blocks: (B:19:0x0052, B:23:0x0055, B:25:0x0061, B:33:0x0080, B:34:0x0091, B:35:0x00a1, B:37:0x00a7, B:39:0x00b9, B:42:0x00c1, B:45:0x00c6, B:47:0x00d0, B:49:0x00d4, B:57:0x00df, B:60:0x00ec, B:62:0x00f0, B:65:0x00f8, B:68:0x00fd, B:70:0x0107, B:72:0x010b, B:76:0x0116, B:80:0x0120, B:82:0x0124, B:85:0x012c, B:88:0x0131, B:90:0x013b, B:92:0x013f, B:96:0x014a, B:100:0x0154, B:102:0x0158, B:105:0x0160, B:108:0x0165, B:110:0x016f, B:112:0x0173, B:116:0x017f, B:120:0x018a, B:122:0x018e, B:125:0x0196, B:128:0x019b, B:130:0x01a5, B:132:0x01a9, B:133:0x01b2, B:134:0x01b8, B:136:0x01bc, B:139:0x01c4, B:142:0x01c9, B:144:0x01d3, B:146:0x01d7, B:147:0x01e0, B:150:0x01e9, B:153:0x01ee, B:155:0x01f2, B:160:0x01fc, B:163:0x0235, B:166:0x0242, B:168:0x0248, B:170:0x024d, B:171:0x0250, B:173:0x0258, B:174:0x025b, B:176:0x0282, B:179:0x028c, B:181:0x0294, B:182:0x029d, B:184:0x02a7, B:186:0x02ab, B:188:0x02af, B:190:0x02ba, B:191:0x02c0, B:194:0x02ce, B:197:0x0261, B:198:0x0264, B:200:0x026c, B:202:0x0270, B:203:0x027b, B:204:0x0278, B:209:0x021d, B:221:0x0075), top: B:18:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0248 A[Catch: all -> 0x02fc, TryCatch #0 {all -> 0x02fc, blocks: (B:19:0x0052, B:23:0x0055, B:25:0x0061, B:33:0x0080, B:34:0x0091, B:35:0x00a1, B:37:0x00a7, B:39:0x00b9, B:42:0x00c1, B:45:0x00c6, B:47:0x00d0, B:49:0x00d4, B:57:0x00df, B:60:0x00ec, B:62:0x00f0, B:65:0x00f8, B:68:0x00fd, B:70:0x0107, B:72:0x010b, B:76:0x0116, B:80:0x0120, B:82:0x0124, B:85:0x012c, B:88:0x0131, B:90:0x013b, B:92:0x013f, B:96:0x014a, B:100:0x0154, B:102:0x0158, B:105:0x0160, B:108:0x0165, B:110:0x016f, B:112:0x0173, B:116:0x017f, B:120:0x018a, B:122:0x018e, B:125:0x0196, B:128:0x019b, B:130:0x01a5, B:132:0x01a9, B:133:0x01b2, B:134:0x01b8, B:136:0x01bc, B:139:0x01c4, B:142:0x01c9, B:144:0x01d3, B:146:0x01d7, B:147:0x01e0, B:150:0x01e9, B:153:0x01ee, B:155:0x01f2, B:160:0x01fc, B:163:0x0235, B:166:0x0242, B:168:0x0248, B:170:0x024d, B:171:0x0250, B:173:0x0258, B:174:0x025b, B:176:0x0282, B:179:0x028c, B:181:0x0294, B:182:0x029d, B:184:0x02a7, B:186:0x02ab, B:188:0x02af, B:190:0x02ba, B:191:0x02c0, B:194:0x02ce, B:197:0x0261, B:198:0x0264, B:200:0x026c, B:202:0x0270, B:203:0x027b, B:204:0x0278, B:209:0x021d, B:221:0x0075), top: B:18:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0282 A[Catch: all -> 0x02fc, TryCatch #0 {all -> 0x02fc, blocks: (B:19:0x0052, B:23:0x0055, B:25:0x0061, B:33:0x0080, B:34:0x0091, B:35:0x00a1, B:37:0x00a7, B:39:0x00b9, B:42:0x00c1, B:45:0x00c6, B:47:0x00d0, B:49:0x00d4, B:57:0x00df, B:60:0x00ec, B:62:0x00f0, B:65:0x00f8, B:68:0x00fd, B:70:0x0107, B:72:0x010b, B:76:0x0116, B:80:0x0120, B:82:0x0124, B:85:0x012c, B:88:0x0131, B:90:0x013b, B:92:0x013f, B:96:0x014a, B:100:0x0154, B:102:0x0158, B:105:0x0160, B:108:0x0165, B:110:0x016f, B:112:0x0173, B:116:0x017f, B:120:0x018a, B:122:0x018e, B:125:0x0196, B:128:0x019b, B:130:0x01a5, B:132:0x01a9, B:133:0x01b2, B:134:0x01b8, B:136:0x01bc, B:139:0x01c4, B:142:0x01c9, B:144:0x01d3, B:146:0x01d7, B:147:0x01e0, B:150:0x01e9, B:153:0x01ee, B:155:0x01f2, B:160:0x01fc, B:163:0x0235, B:166:0x0242, B:168:0x0248, B:170:0x024d, B:171:0x0250, B:173:0x0258, B:174:0x025b, B:176:0x0282, B:179:0x028c, B:181:0x0294, B:182:0x029d, B:184:0x02a7, B:186:0x02ab, B:188:0x02af, B:190:0x02ba, B:191:0x02c0, B:194:0x02ce, B:197:0x0261, B:198:0x0264, B:200:0x026c, B:202:0x0270, B:203:0x027b, B:204:0x0278, B:209:0x021d, B:221:0x0075), top: B:18:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x028c A[Catch: all -> 0x02fc, TryCatch #0 {all -> 0x02fc, blocks: (B:19:0x0052, B:23:0x0055, B:25:0x0061, B:33:0x0080, B:34:0x0091, B:35:0x00a1, B:37:0x00a7, B:39:0x00b9, B:42:0x00c1, B:45:0x00c6, B:47:0x00d0, B:49:0x00d4, B:57:0x00df, B:60:0x00ec, B:62:0x00f0, B:65:0x00f8, B:68:0x00fd, B:70:0x0107, B:72:0x010b, B:76:0x0116, B:80:0x0120, B:82:0x0124, B:85:0x012c, B:88:0x0131, B:90:0x013b, B:92:0x013f, B:96:0x014a, B:100:0x0154, B:102:0x0158, B:105:0x0160, B:108:0x0165, B:110:0x016f, B:112:0x0173, B:116:0x017f, B:120:0x018a, B:122:0x018e, B:125:0x0196, B:128:0x019b, B:130:0x01a5, B:132:0x01a9, B:133:0x01b2, B:134:0x01b8, B:136:0x01bc, B:139:0x01c4, B:142:0x01c9, B:144:0x01d3, B:146:0x01d7, B:147:0x01e0, B:150:0x01e9, B:153:0x01ee, B:155:0x01f2, B:160:0x01fc, B:163:0x0235, B:166:0x0242, B:168:0x0248, B:170:0x024d, B:171:0x0250, B:173:0x0258, B:174:0x025b, B:176:0x0282, B:179:0x028c, B:181:0x0294, B:182:0x029d, B:184:0x02a7, B:186:0x02ab, B:188:0x02af, B:190:0x02ba, B:191:0x02c0, B:194:0x02ce, B:197:0x0261, B:198:0x0264, B:200:0x026c, B:202:0x0270, B:203:0x027b, B:204:0x0278, B:209:0x021d, B:221:0x0075), top: B:18:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0242 A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:219:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a7 A[Catch: all -> 0x02fc, TryCatch #0 {all -> 0x02fc, blocks: (B:19:0x0052, B:23:0x0055, B:25:0x0061, B:33:0x0080, B:34:0x0091, B:35:0x00a1, B:37:0x00a7, B:39:0x00b9, B:42:0x00c1, B:45:0x00c6, B:47:0x00d0, B:49:0x00d4, B:57:0x00df, B:60:0x00ec, B:62:0x00f0, B:65:0x00f8, B:68:0x00fd, B:70:0x0107, B:72:0x010b, B:76:0x0116, B:80:0x0120, B:82:0x0124, B:85:0x012c, B:88:0x0131, B:90:0x013b, B:92:0x013f, B:96:0x014a, B:100:0x0154, B:102:0x0158, B:105:0x0160, B:108:0x0165, B:110:0x016f, B:112:0x0173, B:116:0x017f, B:120:0x018a, B:122:0x018e, B:125:0x0196, B:128:0x019b, B:130:0x01a5, B:132:0x01a9, B:133:0x01b2, B:134:0x01b8, B:136:0x01bc, B:139:0x01c4, B:142:0x01c9, B:144:0x01d3, B:146:0x01d7, B:147:0x01e0, B:150:0x01e9, B:153:0x01ee, B:155:0x01f2, B:160:0x01fc, B:163:0x0235, B:166:0x0242, B:168:0x0248, B:170:0x024d, B:171:0x0250, B:173:0x0258, B:174:0x025b, B:176:0x0282, B:179:0x028c, B:181:0x0294, B:182:0x029d, B:184:0x02a7, B:186:0x02ab, B:188:0x02af, B:190:0x02ba, B:191:0x02c0, B:194:0x02ce, B:197:0x0261, B:198:0x0264, B:200:0x026c, B:202:0x0270, B:203:0x027b, B:204:0x0278, B:209:0x021d, B:221:0x0075), top: B:18:0x0052 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i, boolean z) throws IOException {
        Map<String, Object> treeMap;
        boolean z2;
        String str;
        Class<?> cls;
        Object processValue;
        Object obj3;
        Class<?> cls2;
        Type type2;
        boolean z3;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        Map<String, Object> map = (Map) obj;
        int i2 = SerializerFeature.MapSortField.mask;
        if ((serializeWriter.features & i2) != 0 || (i2 & i) != 0) {
            if (map instanceof JSONObject) {
                map = ((JSONObject) map).getInnerMap();
            }
            if (!(map instanceof SortedMap) && !(map instanceof LinkedHashMap)) {
                try {
                    treeMap = new TreeMap(map);
                } catch (Exception unused) {
                }
                if (!jSONSerializer.containsReference(obj)) {
                    jSONSerializer.writeReference(obj);
                    return;
                }
                SerialContext serialContext = jSONSerializer.context;
                boolean z4 = false;
                jSONSerializer.setContext(serialContext, obj, obj2, 0);
                if (!z) {
                    try {
                        serializeWriter.write(123);
                    } catch (Throwable th) {
                        jSONSerializer.context = serialContext;
                        throw th;
                    }
                }
                jSONSerializer.incrementIndent();
                if (serializeWriter.isEnabled(SerializerFeature.WriteClassName)) {
                    String str2 = jSONSerializer.config.typeKey;
                    Class<?> cls3 = treeMap.getClass();
                    if (!((cls3 == JSONObject.class || cls3 == HashMap.class || cls3 == LinkedHashMap.class) && treeMap.containsKey(str2))) {
                        serializeWriter.writeFieldName(str2);
                        serializeWriter.writeString(obj.getClass().getName());
                        z2 = false;
                        boolean z5 = z2;
                        Class<?> cls4 = null;
                        ObjectSerializer objectSerializer = null;
                        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
                            Object value = entry.getValue();
                            String key = entry.getKey();
                            List<PropertyPreFilter> list = jSONSerializer.propertyPreFilters;
                            if (list != null && list.size() > 0) {
                                if (key != null && !(key instanceof String)) {
                                    if ((key.getClass().isPrimitive() || (key instanceof Number)) && !applyName(jSONSerializer, obj, JSON.toJSONString(key))) {
                                        cls = cls4;
                                        cls4 = cls;
                                        z4 = false;
                                    }
                                }
                                if (!applyName(jSONSerializer, obj, key)) {
                                    cls = cls4;
                                    cls4 = cls;
                                    z4 = false;
                                }
                            }
                            List<PropertyPreFilter> list2 = this.propertyPreFilters;
                            if (list2 != null && list2.size() > 0) {
                                if (key != null && !(key instanceof String)) {
                                    if ((key.getClass().isPrimitive() || (key instanceof Number)) && !applyName(jSONSerializer, obj, JSON.toJSONString(key))) {
                                        cls = cls4;
                                        cls4 = cls;
                                        z4 = false;
                                    }
                                }
                                if (!applyName(jSONSerializer, obj, key)) {
                                    cls = cls4;
                                    cls4 = cls;
                                    z4 = false;
                                }
                            }
                            List<PropertyFilter> list3 = jSONSerializer.propertyFilters;
                            if (list3 != null && list3.size() > 0) {
                                if (key != null && !(key instanceof String)) {
                                    if ((key.getClass().isPrimitive() || (key instanceof Number)) && !apply(jSONSerializer, obj, JSON.toJSONString(key), value)) {
                                        cls = cls4;
                                        cls4 = cls;
                                        z4 = false;
                                    }
                                }
                                if (!apply(jSONSerializer, obj, key, value)) {
                                    cls = cls4;
                                    cls4 = cls;
                                    z4 = false;
                                }
                            }
                            List<PropertyFilter> list4 = this.propertyFilters;
                            if (list4 != null && list4.size() > 0) {
                                if (key != null && !(key instanceof String)) {
                                    if ((key.getClass().isPrimitive() || (key instanceof Number)) && !apply(jSONSerializer, obj, JSON.toJSONString(key), value)) {
                                        cls = cls4;
                                        cls4 = cls;
                                        z4 = false;
                                    }
                                }
                                if (!apply(jSONSerializer, obj, key, value)) {
                                    cls = cls4;
                                    cls4 = cls;
                                    z4 = false;
                                }
                            }
                            List<NameFilter> list5 = jSONSerializer.nameFilters;
                            if (list5 != null && list5.size() > 0) {
                                if (key != null && !(key instanceof String)) {
                                    if (key.getClass().isPrimitive() || (key instanceof Number)) {
                                        key = processKey(jSONSerializer, obj, JSON.toJSONString(key), value);
                                    }
                                }
                                key = processKey(jSONSerializer, obj, key, value);
                            }
                            List<NameFilter> list6 = this.nameFilters;
                            if (list6 != null && list6.size() > 0) {
                                if (key != null && !(key instanceof String)) {
                                    if (key.getClass().isPrimitive() || (key instanceof Number)) {
                                        key = processKey(jSONSerializer, obj, JSON.toJSONString(key), value);
                                    }
                                }
                                key = processKey(jSONSerializer, obj, key, value);
                            }
                            String str3 = key;
                            if (str3 != null && !(str3 instanceof String)) {
                                if (!(str3 instanceof Map) && !(str3 instanceof Collection)) {
                                    z3 = z4;
                                    if (z3) {
                                        str = str3;
                                        cls = cls4;
                                        processValue = processValue(jSONSerializer, null, obj, JSON.toJSONString(str3), value);
                                        obj3 = processValue;
                                        if (obj3 != null || serializeWriter.isEnabled(SerializerFeature.WRITE_MAP_NULL_FEATURES)) {
                                            if (str instanceof String) {
                                                String str4 = str;
                                                if (!z5) {
                                                    serializeWriter.write(44);
                                                }
                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                    jSONSerializer.println();
                                                }
                                                serializeWriter.writeFieldName(str4, true);
                                            } else {
                                                if (!z5) {
                                                    serializeWriter.write(44);
                                                }
                                                if (serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING) && !(str instanceof Enum)) {
                                                    jSONSerializer.write(JSON.toJSONString(str));
                                                } else {
                                                    jSONSerializer.write((Object) str);
                                                }
                                                serializeWriter.write(58);
                                            }
                                            if (obj3 == null) {
                                                serializeWriter.writeNull();
                                                cls4 = cls;
                                            } else {
                                                Class<?> cls5 = obj3.getClass();
                                                Class<?> cls6 = cls;
                                                if (cls5 != cls6) {
                                                    objectSerializer = jSONSerializer.getObjectWriter(cls5);
                                                    cls2 = cls5;
                                                } else {
                                                    cls2 = cls6;
                                                }
                                                ObjectSerializer objectSerializer2 = objectSerializer;
                                                if (SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName) && (objectSerializer2 instanceof JavaBeanSerializer)) {
                                                    if (type instanceof ParameterizedType) {
                                                        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                                                        if (actualTypeArguments.length == 2) {
                                                            type2 = actualTypeArguments[1];
                                                            objectSerializer = objectSerializer2;
                                                            ((JavaBeanSerializer) objectSerializer2).writeNoneASM(jSONSerializer, obj3, str, type2, i);
                                                        }
                                                    }
                                                    type2 = null;
                                                    objectSerializer = objectSerializer2;
                                                    ((JavaBeanSerializer) objectSerializer2).writeNoneASM(jSONSerializer, obj3, str, type2, i);
                                                } else {
                                                    objectSerializer = objectSerializer2;
                                                    objectSerializer.write(jSONSerializer, obj3, str, null, i);
                                                }
                                                cls4 = cls2;
                                            }
                                            z4 = false;
                                            z5 = false;
                                        } else {
                                            cls4 = cls;
                                            z4 = false;
                                        }
                                    } else {
                                        str = str3;
                                        cls = cls4;
                                        obj3 = value;
                                        if (obj3 != null) {
                                        }
                                        if (str instanceof String) {
                                        }
                                        if (obj3 == null) {
                                        }
                                        z4 = false;
                                        z5 = false;
                                    }
                                }
                                z3 = true;
                                if (z3) {
                                }
                            }
                            str = str3;
                            cls = cls4;
                            processValue = processValue(jSONSerializer, null, obj, str, value);
                            obj3 = processValue;
                            if (obj3 != null) {
                            }
                            if (str instanceof String) {
                            }
                            if (obj3 == null) {
                            }
                            z4 = false;
                            z5 = false;
                        }
                        jSONSerializer.context = serialContext;
                        jSONSerializer.decrementIdent();
                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat) && treeMap.size() > 0) {
                            jSONSerializer.println();
                        }
                        if (z) {
                            serializeWriter.write(125);
                            return;
                        }
                        return;
                    }
                }
                z2 = true;
                boolean z52 = z2;
                Class<?> cls42 = null;
                ObjectSerializer objectSerializer3 = null;
                while (r16.hasNext()) {
                }
                jSONSerializer.context = serialContext;
                jSONSerializer.decrementIdent();
                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                    jSONSerializer.println();
                }
                if (z) {
                }
            }
        }
        treeMap = map;
        if (!jSONSerializer.containsReference(obj)) {
        }
    }
}
