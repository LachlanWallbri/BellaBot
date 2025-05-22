package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class NumberDeserializer implements ObjectDeserializer {
    public static final NumberDeserializer instance = new NumberDeserializer();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v18, types: [java.math.BigDecimal, T, java.lang.Object] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 2) {
            if (type == Double.TYPE || type == Double.class) {
                String numberString = jSONLexer.numberString();
                jSONLexer.nextToken(16);
                return (T) Double.valueOf(Double.parseDouble(numberString));
            }
            long longValue = jSONLexer.longValue();
            jSONLexer.nextToken(16);
            if (type == Short.TYPE || type == Short.class) {
                if (longValue > 32767 || longValue < -32768) {
                    throw new JSONException("short overflow : " + longValue);
                }
                return (T) Short.valueOf((short) longValue);
            }
            if (type != Byte.TYPE && type != Byte.class) {
                if (longValue >= -2147483648L && longValue <= 2147483647L) {
                    return (T) Integer.valueOf((int) longValue);
                }
                return (T) Long.valueOf(longValue);
            }
            if (longValue > 127 || longValue < -128) {
                throw new JSONException("short overflow : " + longValue);
            }
            return (T) Byte.valueOf((byte) longValue);
        }
        if (jSONLexer.token() == 3) {
            if (type == Double.TYPE || type == Double.class) {
                String numberString2 = jSONLexer.numberString();
                jSONLexer.nextToken(16);
                return (T) Double.valueOf(Double.parseDouble(numberString2));
            }
            ?? r11 = (T) jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            if (type != Short.TYPE && type != Short.class) {
                return (type == Byte.TYPE || type == Byte.class) ? (T) Byte.valueOf(r11.byteValue()) : r11;
            }
            if (r11.compareTo(BigDecimal.valueOf(32767L)) > 0 || r11.compareTo(BigDecimal.valueOf(-32768L)) < 0) {
                throw new JSONException("short overflow : " + ((Object) r11));
            }
            return (T) Short.valueOf(r11.shortValue());
        }
        if (jSONLexer.token() == 18 && "NaN".equals(jSONLexer.stringVal())) {
            jSONLexer.nextToken();
            if (type == Double.class) {
                return (T) Double.valueOf(Double.NaN);
            }
            if (type == Float.class) {
                return (T) Float.valueOf(Float.NaN);
            }
            return null;
        }
        Object parse = defaultJSONParser.parse();
        if (parse == null) {
            return null;
        }
        if (type == Double.TYPE || type == Double.class) {
            try {
                return (T) TypeUtils.castToDouble(parse);
            } catch (Exception e) {
                throw new JSONException("parseDouble error, field : " + obj, e);
            }
        }
        if (type == Short.TYPE || type == Short.class) {
            try {
                return (T) TypeUtils.castToShort(parse);
            } catch (Exception e2) {
                throw new JSONException("parseShort error, field : " + obj, e2);
            }
        }
        if (type == Byte.TYPE || type == Byte.class) {
            try {
                return (T) TypeUtils.castToByte(parse);
            } catch (Exception e3) {
                throw new JSONException("parseByte error, field : " + obj, e3);
            }
        }
        return (T) TypeUtils.castToBigDecimal(parse);
    }
}
