package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ObjectArrayCodec implements ObjectSerializer, ObjectDeserializer {
    public static final ObjectArrayCodec instance = new ObjectArrayCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 14;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        Object[] objArr = (Object[]) obj;
        if (obj == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        int length = objArr.length;
        int i2 = length - 1;
        if (i2 == -1) {
            serializeWriter.append((CharSequence) "[]");
            return;
        }
        SerialContext serialContext = jSONSerializer.context;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        try {
            serializeWriter.append('[');
            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                jSONSerializer.incrementIndent();
                jSONSerializer.println();
                for (int i3 = 0; i3 < length; i3++) {
                    if (i3 != 0) {
                        serializeWriter.write(44);
                        jSONSerializer.println();
                    }
                    jSONSerializer.write(objArr[i3]);
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter.write(93);
                return;
            }
            Class<?> cls = null;
            ObjectSerializer objectSerializer = null;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj3 = objArr[i4];
                if (obj3 == null) {
                    serializeWriter.append((CharSequence) "null,");
                } else {
                    if (jSONSerializer.containsReference(obj3)) {
                        jSONSerializer.writeReference(obj3);
                    } else {
                        Class<?> cls2 = obj3.getClass();
                        if (cls2 == cls) {
                            objectSerializer.write(jSONSerializer, obj3, null, null, 0);
                        } else {
                            objectSerializer = jSONSerializer.getObjectWriter(cls2);
                            objectSerializer.write(jSONSerializer, obj3, null, null, 0);
                            cls = cls2;
                        }
                    }
                    serializeWriter.append(',');
                }
            }
            Object obj4 = objArr[i2];
            if (obj4 == null) {
                serializeWriter.append((CharSequence) "null]");
            } else {
                if (jSONSerializer.containsReference(obj4)) {
                    jSONSerializer.writeReference(obj4);
                } else {
                    jSONSerializer.writeWithFieldName(obj4, Integer.valueOf(i2));
                }
                serializeWriter.append(']');
            }
        } finally {
            jSONSerializer.context = serialContext;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1, types: [byte[], T] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Class<?> cls;
        Type type2;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = jSONLexer.token();
        Type type3 = null;
        if (i == 8) {
            jSONLexer.nextToken(16);
            return null;
        }
        if (i == 4 || i == 26) {
            ?? r8 = (T) jSONLexer.bytesValue();
            jSONLexer.nextToken(16);
            if (r8.length != 0 || type == byte[].class) {
                return r8;
            }
            return null;
        }
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (genericComponentType instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) genericComponentType;
                Type type4 = defaultJSONParser.getContext().type;
                if (type4 instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type4;
                    Type rawType = parameterizedType.getRawType();
                    if (rawType instanceof Class) {
                        TypeVariable<Class<T>>[] typeParameters = ((Class) rawType).getTypeParameters();
                        for (int i2 = 0; i2 < typeParameters.length; i2++) {
                            if (typeParameters[i2].getName().equals(typeVariable.getName())) {
                                type3 = parameterizedType.getActualTypeArguments()[i2];
                            }
                        }
                    }
                    if (type3 instanceof Class) {
                        cls = (Class) type3;
                        type2 = genericComponentType;
                    } else {
                        cls = Object.class;
                        type2 = genericComponentType;
                    }
                } else {
                    cls = TypeUtils.getClass(typeVariable.getBounds()[0]);
                    type2 = genericComponentType;
                }
            } else {
                cls = TypeUtils.getClass(genericComponentType);
                type2 = genericComponentType;
            }
        } else {
            Class<?> componentType = ((Class) type).getComponentType();
            cls = componentType;
            type2 = componentType;
        }
        JSONArray jSONArray = new JSONArray();
        defaultJSONParser.parseArray(type2, jSONArray, obj);
        return (T) toObjectArray(defaultJSONParser, cls, jSONArray);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private <T> T toObjectArray(DefaultJSONParser defaultJSONParser, Class<?> cls, JSONArray jSONArray) {
        Object obj;
        if (jSONArray == null) {
            return null;
        }
        int size = jSONArray.size();
        T t = (T) Array.newInstance(cls, size);
        for (int i = 0; i < size; i++) {
            Object obj2 = jSONArray.get(i);
            if (obj2 == jSONArray) {
                Array.set(t, i, t);
            } else if (cls.isArray()) {
                if (!cls.isInstance(obj2)) {
                    obj2 = toObjectArray(defaultJSONParser, cls, (JSONArray) obj2);
                }
                Array.set(t, i, obj2);
            } else {
                if (obj2 instanceof JSONArray) {
                    JSONArray jSONArray2 = (JSONArray) obj2;
                    int size2 = jSONArray2.size();
                    boolean z = false;
                    for (int i2 = 0; i2 < size2; i2++) {
                        if (jSONArray2.get(i2) == jSONArray) {
                            jSONArray2.set(i, t);
                            z = true;
                        }
                    }
                    if (z) {
                        obj = jSONArray2.toArray();
                        if (obj == null) {
                            obj = TypeUtils.cast(obj2, (Class<Object>) cls, defaultJSONParser.getConfig());
                        }
                        Array.set(t, i, obj);
                    }
                }
                obj = null;
                if (obj == null) {
                }
                Array.set(t, i, obj);
            }
        }
        jSONArray.setRelatedArray(t);
        jSONArray.setComponentType(cls);
        return t;
    }
}
