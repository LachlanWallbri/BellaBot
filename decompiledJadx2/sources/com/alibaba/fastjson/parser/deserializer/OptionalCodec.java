package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class OptionalCodec implements ObjectSerializer, ObjectDeserializer {
    public static OptionalCodec instance = new OptionalCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (type == OptionalInt.class) {
            Integer castToInt = TypeUtils.castToInt(defaultJSONParser.parseObject((Class) Integer.class));
            if (castToInt == null) {
                return (T) OptionalInt.empty();
            }
            return (T) OptionalInt.of(castToInt.intValue());
        }
        if (type == OptionalLong.class) {
            Long castToLong = TypeUtils.castToLong(defaultJSONParser.parseObject((Class) Long.class));
            if (castToLong == null) {
                return (T) OptionalLong.empty();
            }
            return (T) OptionalLong.of(castToLong.longValue());
        }
        if (type == OptionalDouble.class) {
            Double castToDouble = TypeUtils.castToDouble(defaultJSONParser.parseObject((Class) Double.class));
            if (castToDouble == null) {
                return (T) OptionalDouble.empty();
            }
            return (T) OptionalDouble.of(castToDouble.doubleValue());
        }
        Object parseObject = defaultJSONParser.parseObject(TypeUtils.unwrapOptional(type));
        if (parseObject == null) {
            return (T) Optional.empty();
        }
        return (T) Optional.of(parseObject);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        if (obj == null) {
            jSONSerializer.writeNull();
            return;
        }
        if (obj instanceof Optional) {
            Optional optional = (Optional) obj;
            jSONSerializer.write(optional.isPresent() ? optional.get() : null);
            return;
        }
        if (obj instanceof OptionalDouble) {
            OptionalDouble optionalDouble = (OptionalDouble) obj;
            if (optionalDouble.isPresent()) {
                jSONSerializer.write(Double.valueOf(optionalDouble.getAsDouble()));
                return;
            } else {
                jSONSerializer.writeNull();
                return;
            }
        }
        if (obj instanceof OptionalInt) {
            OptionalInt optionalInt = (OptionalInt) obj;
            if (optionalInt.isPresent()) {
                jSONSerializer.out.writeInt(optionalInt.getAsInt());
                return;
            } else {
                jSONSerializer.writeNull();
                return;
            }
        }
        if (obj instanceof OptionalLong) {
            OptionalLong optionalLong = (OptionalLong) obj;
            if (optionalLong.isPresent()) {
                jSONSerializer.out.writeLong(optionalLong.getAsLong());
                return;
            } else {
                jSONSerializer.writeNull();
                return;
            }
        }
        throw new JSONException("not support optional : " + obj.getClass());
    }
}
