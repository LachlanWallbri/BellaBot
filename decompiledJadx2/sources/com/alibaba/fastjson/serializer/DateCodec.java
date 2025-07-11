package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.codec.language.Soundex;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DateCodec extends AbstractDateDeserializer implements ObjectSerializer, ObjectDeserializer {
    public static final DateCodec instance = new DateCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        Date castToDate;
        char[] charArray;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        if (obj instanceof Date) {
            castToDate = (Date) obj;
        } else {
            castToDate = TypeUtils.castToDate(obj);
        }
        if (serializeWriter.isEnabled(SerializerFeature.WriteDateUseDateFormat)) {
            DateFormat dateFormat = jSONSerializer.getDateFormat();
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT, jSONSerializer.locale);
                dateFormat.setTimeZone(jSONSerializer.timeZone);
            }
            serializeWriter.writeString(dateFormat.format(castToDate));
            return;
        }
        if (serializeWriter.isEnabled(SerializerFeature.WriteClassName) && obj.getClass() != type) {
            if (obj.getClass() == Date.class) {
                serializeWriter.write("new Date(");
                serializeWriter.writeLong(((Date) obj).getTime());
                serializeWriter.write(41);
                return;
            } else {
                serializeWriter.write(123);
                serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY);
                jSONSerializer.write(obj.getClass().getName());
                serializeWriter.writeFieldValue(',', "val", ((Date) obj).getTime());
                serializeWriter.write(125);
                return;
            }
        }
        long time = castToDate.getTime();
        if (serializeWriter.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
            int i2 = serializeWriter.isEnabled(SerializerFeature.UseSingleQuotes) ? 39 : 34;
            serializeWriter.write(i2);
            Calendar calendar = Calendar.getInstance(jSONSerializer.timeZone, jSONSerializer.locale);
            calendar.setTimeInMillis(time);
            int i3 = calendar.get(1);
            int i4 = calendar.get(2) + 1;
            int i5 = calendar.get(5);
            int i6 = calendar.get(11);
            int i7 = calendar.get(12);
            int i8 = calendar.get(13);
            int i9 = calendar.get(14);
            if (i9 != 0) {
                charArray = "0000-00-00T00:00:00.000".toCharArray();
                IOUtils.getChars(i9, 23, charArray);
                IOUtils.getChars(i8, 19, charArray);
                IOUtils.getChars(i7, 16, charArray);
                IOUtils.getChars(i6, 13, charArray);
                IOUtils.getChars(i5, 10, charArray);
                IOUtils.getChars(i4, 7, charArray);
                IOUtils.getChars(i3, 4, charArray);
            } else if (i8 == 0 && i7 == 0 && i6 == 0) {
                charArray = "0000-00-00".toCharArray();
                IOUtils.getChars(i5, 10, charArray);
                IOUtils.getChars(i4, 7, charArray);
                IOUtils.getChars(i3, 4, charArray);
            } else {
                charArray = "0000-00-00T00:00:00".toCharArray();
                IOUtils.getChars(i8, 19, charArray);
                IOUtils.getChars(i7, 16, charArray);
                IOUtils.getChars(i6, 13, charArray);
                IOUtils.getChars(i5, 10, charArray);
                IOUtils.getChars(i4, 7, charArray);
                IOUtils.getChars(i3, 4, charArray);
            }
            serializeWriter.write(charArray);
            int rawOffset = calendar.getTimeZone().getRawOffset() / 3600000;
            if (rawOffset == 0) {
                serializeWriter.write(90);
            } else {
                if (rawOffset > 0) {
                    serializeWriter.append('+').append((CharSequence) String.format("%02d", Integer.valueOf(rawOffset)));
                } else {
                    serializeWriter.append(Soundex.SILENT_MARKER).append((CharSequence) String.format("%02d", Integer.valueOf(-rawOffset)));
                }
                serializeWriter.append((CharSequence) ":00");
            }
            serializeWriter.write(i2);
            return;
        }
        serializeWriter.writeLong(time);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v19, types: [java.util.Calendar, T] */
    /* JADX WARN: Type inference failed for: r4v24, types: [java.util.Calendar, T] */
    @Override // com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer
    public <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        if (obj2 == 0) {
            return null;
        }
        if (obj2 instanceof Date) {
            return obj2;
        }
        if (obj2 instanceof Number) {
            return (T) new Date(((Number) obj2).longValue());
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.length() == 0) {
                return null;
            }
            JSONScanner jSONScanner = new JSONScanner(str);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    ?? r4 = (T) jSONScanner.getCalendar();
                    return type == Calendar.class ? r4 : (T) r4.getTime();
                }
                jSONScanner.close();
                if (str.length() == defaultJSONParser.getDateFomartPattern().length() || (str.length() == 22 && defaultJSONParser.getDateFomartPattern().equals("yyyyMMddHHmmssSSSZ"))) {
                    try {
                        return (T) defaultJSONParser.getDateFormat().parse(str);
                    } catch (ParseException unused) {
                    }
                }
                if (str.startsWith("/Date(") && str.endsWith(")/")) {
                    str = str.substring(6, str.length() - 2);
                }
                if ("0000-00-00".equals(str) || "0000-00-00T00:00:00".equalsIgnoreCase(str) || "0001-01-01T00:00:00+08:00".equalsIgnoreCase(str)) {
                    return null;
                }
                int lastIndexOf = str.lastIndexOf(124);
                if (lastIndexOf > 20) {
                    TimeZone timeZone = TimeZone.getTimeZone(str.substring(lastIndexOf + 1));
                    if (!"GMT".equals(timeZone.getID())) {
                        JSONScanner jSONScanner2 = new JSONScanner(str.substring(0, lastIndexOf));
                        try {
                            if (jSONScanner2.scanISO8601DateIfMatch(false)) {
                                ?? r42 = (T) jSONScanner2.getCalendar();
                                r42.setTimeZone(timeZone);
                                return type == Calendar.class ? r42 : (T) r42.getTime();
                            }
                        } finally {
                        }
                    }
                }
                return (T) new Date(Long.parseLong(str));
            } finally {
            }
        }
        throw new JSONException("parse error");
    }
}
