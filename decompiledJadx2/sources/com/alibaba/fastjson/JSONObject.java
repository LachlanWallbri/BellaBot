package com.alibaba.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class JSONObject extends JSON implements Map<String, Object>, Cloneable, Serializable, InvocationHandler {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long serialVersionUID = 1;
    private final Map<String, Object> map;

    public JSONObject() {
        this(16, false);
    }

    public JSONObject(Map<String, Object> map) {
        this.map = map;
    }

    public JSONObject(boolean z) {
        this(16, z);
    }

    public JSONObject(int i) {
        this(i, false);
    }

    public JSONObject(int i, boolean z) {
        if (z) {
            this.map = new LinkedHashMap(i);
        } else {
            this.map = new HashMap(i);
        }
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        Object obj2 = this.map.get(obj);
        return (obj2 == null && (obj instanceof Number)) ? this.map.get(obj.toString()) : obj2;
    }

    public JSONObject getJSONObject(String str) {
        Object obj = this.map.get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        if (obj instanceof String) {
            return JSON.parseObject((String) obj);
        }
        return (JSONObject) toJSON(obj);
    }

    public JSONArray getJSONArray(String str) {
        Object obj = this.map.get(str);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        if (obj instanceof String) {
            return (JSONArray) JSON.parse((String) obj);
        }
        return (JSONArray) toJSON(obj);
    }

    public <T> T getObject(String str, Class<T> cls) {
        return (T) TypeUtils.castToJavaBean(this.map.get(str), cls);
    }

    public <T> T getObject(String str, Type type) {
        return (T) TypeUtils.cast(this.map.get(str), type, ParserConfig.getGlobalInstance());
    }

    public <T> T getObject(String str, TypeReference typeReference) {
        T t = (T) this.map.get(str);
        return typeReference == null ? t : (T) TypeUtils.cast(t, typeReference.getType(), ParserConfig.getGlobalInstance());
    }

    public Boolean getBoolean(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return TypeUtils.castToBoolean(obj);
    }

    public byte[] getBytes(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return TypeUtils.castToBytes(obj);
    }

    public boolean getBooleanValue(String str) {
        Boolean castToBoolean = TypeUtils.castToBoolean(get(str));
        if (castToBoolean == null) {
            return false;
        }
        return castToBoolean.booleanValue();
    }

    public Byte getByte(String str) {
        return TypeUtils.castToByte(get(str));
    }

    public byte getByteValue(String str) {
        Byte castToByte = TypeUtils.castToByte(get(str));
        if (castToByte == null) {
            return (byte) 0;
        }
        return castToByte.byteValue();
    }

    public Short getShort(String str) {
        return TypeUtils.castToShort(get(str));
    }

    public short getShortValue(String str) {
        Short castToShort = TypeUtils.castToShort(get(str));
        if (castToShort == null) {
            return (short) 0;
        }
        return castToShort.shortValue();
    }

    public Integer getInteger(String str) {
        return TypeUtils.castToInt(get(str));
    }

    public int getIntValue(String str) {
        Integer castToInt = TypeUtils.castToInt(get(str));
        if (castToInt == null) {
            return 0;
        }
        return castToInt.intValue();
    }

    public Long getLong(String str) {
        return TypeUtils.castToLong(get(str));
    }

    public long getLongValue(String str) {
        Long castToLong = TypeUtils.castToLong(get(str));
        if (castToLong == null) {
            return 0L;
        }
        return castToLong.longValue();
    }

    public Float getFloat(String str) {
        return TypeUtils.castToFloat(get(str));
    }

    public float getFloatValue(String str) {
        Float castToFloat = TypeUtils.castToFloat(get(str));
        if (castToFloat == null) {
            return 0.0f;
        }
        return castToFloat.floatValue();
    }

    public Double getDouble(String str) {
        return TypeUtils.castToDouble(get(str));
    }

    public double getDoubleValue(String str) {
        Double castToDouble = TypeUtils.castToDouble(get(str));
        if (castToDouble == null) {
            return 0.0d;
        }
        return castToDouble.doubleValue();
    }

    public BigDecimal getBigDecimal(String str) {
        return TypeUtils.castToBigDecimal(get(str));
    }

    public BigInteger getBigInteger(String str) {
        return TypeUtils.castToBigInteger(get(str));
    }

    public String getString(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public Date getDate(String str) {
        return TypeUtils.castToDate(get(str));
    }

    public java.sql.Date getSqlDate(String str) {
        return TypeUtils.castToSqlDate(get(str));
    }

    public Timestamp getTimestamp(String str) {
        return TypeUtils.castToTimestamp(get(str));
    }

    @Override // java.util.Map
    public Object put(String str, Object obj) {
        return this.map.put(str, obj);
    }

    public JSONObject fluentPut(String str, Object obj) {
        this.map.put(str, obj);
        return this;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        this.map.putAll(map);
    }

    public JSONObject fluentPutAll(Map<? extends String, ? extends Object> map) {
        this.map.putAll(map);
        return this;
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    public JSONObject fluentClear() {
        this.map.clear();
        return this;
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    public JSONObject fluentRemove(Object obj) {
        this.map.remove(obj);
        return this;
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return this.map.values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public Object clone() {
        return new JSONObject((Map<String, Object>) (this.map instanceof LinkedHashMap ? new LinkedHashMap(this.map) : new HashMap(this.map)));
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String str = null;
        if (parameterTypes.length == 1) {
            if (method.getName().equals("equals")) {
                return Boolean.valueOf(equals(objArr[0]));
            }
            if (method.getReturnType() != Void.TYPE) {
                throw new JSONException("illegal setter");
            }
            JSONField jSONField = (JSONField) method.getAnnotation(JSONField.class);
            String name = (jSONField == null || jSONField.name().length() == 0) ? null : jSONField.name();
            if (name == null) {
                String name2 = method.getName();
                if (!name2.startsWith(TmpConstant.PROPERTY_IDENTIFIER_SET)) {
                    throw new JSONException("illegal setter");
                }
                String substring = name2.substring(3);
                if (substring.length() == 0) {
                    throw new JSONException("illegal setter");
                }
                name = Character.toLowerCase(substring.charAt(0)) + substring.substring(1);
            }
            this.map.put(name, objArr[0]);
            return null;
        }
        if (parameterTypes.length == 0) {
            if (method.getReturnType() == Void.TYPE) {
                throw new JSONException("illegal getter");
            }
            JSONField jSONField2 = (JSONField) method.getAnnotation(JSONField.class);
            if (jSONField2 != null && jSONField2.name().length() != 0) {
                str = jSONField2.name();
            }
            if (str == null) {
                String name3 = method.getName();
                if (name3.startsWith(TmpConstant.PROPERTY_IDENTIFIER_GET)) {
                    String substring2 = name3.substring(3);
                    if (substring2.length() == 0) {
                        throw new JSONException("illegal getter");
                    }
                    str = Character.toLowerCase(substring2.charAt(0)) + substring2.substring(1);
                } else if (name3.startsWith("is")) {
                    String substring3 = name3.substring(2);
                    if (substring3.length() == 0) {
                        throw new JSONException("illegal getter");
                    }
                    str = Character.toLowerCase(substring3.charAt(0)) + substring3.substring(1);
                } else {
                    if (name3.startsWith("hashCode")) {
                        return Integer.valueOf(hashCode());
                    }
                    if (name3.startsWith("toString")) {
                        return toString();
                    }
                    throw new JSONException("illegal getter");
                }
            }
            return TypeUtils.cast(this.map.get(str), method.getGenericReturnType(), ParserConfig.getGlobalInstance());
        }
        throw new UnsupportedOperationException(method.toGenericString());
    }

    public Map<String, Object> getInnerMap() {
        return this.map;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class SecureObjectInputStream extends ObjectInputStream {
        static Field[] fields;
        static volatile boolean fields_error;

        @Override // java.io.ObjectInputStream
        protected void readStreamHeader() throws IOException, StreamCorruptedException {
        }

        static void ensureFields() {
            if (fields != null || fields_error) {
                return;
            }
            try {
                Field[] declaredFields = ObjectInputStream.class.getDeclaredFields();
                String[] strArr = {"bin", "passHandle", "handles", "curContext"};
                Field[] fieldArr = new Field[strArr.length];
                for (int i = 0; i < strArr.length; i++) {
                    Field field = TypeUtils.getField(ObjectInputStream.class, strArr[i], declaredFields);
                    field.setAccessible(true);
                    fieldArr[i] = field;
                }
                fields = fieldArr;
            } catch (Throwable unused) {
                fields_error = true;
            }
        }

        public SecureObjectInputStream(ObjectInputStream objectInputStream) throws IOException {
            super(objectInputStream);
            for (int i = 0; i < fields.length; i++) {
                try {
                    Field field = fields[i];
                    field.set(this, field.get(objectInputStream));
                } catch (IllegalAccessException unused) {
                    fields_error = true;
                    return;
                }
            }
        }

        @Override // java.io.ObjectInputStream
        protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
            String name = objectStreamClass.getName();
            if (name.length() > 2) {
                int lastIndexOf = name.lastIndexOf(91);
                if (lastIndexOf != -1) {
                    name = name.substring(lastIndexOf + 1);
                }
                if (name.length() > 2 && name.charAt(0) == 'L' && name.charAt(name.length() - 1) == ';') {
                    name = name.substring(1, name.length() - 1);
                }
                ParserConfig.global.checkAutoType(name, null, Feature.SupportAutoType.mask);
            }
            return super.resolveClass(objectStreamClass);
        }

        @Override // java.io.ObjectInputStream
        protected Class<?> resolveProxyClass(String[] strArr) throws IOException, ClassNotFoundException {
            for (String str : strArr) {
                ParserConfig.global.checkAutoType(str, null);
            }
            return super.resolveProxyClass(strArr);
        }
    }
}
