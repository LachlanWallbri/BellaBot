package io.minio.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.minio.Time;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

@Root
@Convert(ResponseDateConverter.class)
/* loaded from: classes7.dex */
public class ResponseDate {
    public static final DateTimeFormatter MINIO_RESPONSE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':'mm':'ss'Z'", Locale.US).withZone(Time.UTC);
    private ZonedDateTime zonedDateTime;

    public ResponseDate() {
    }

    public ResponseDate(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public ZonedDateTime zonedDateTime() {
        return this.zonedDateTime;
    }

    public String toString() {
        return this.zonedDateTime.format(Time.RESPONSE_DATE_FORMAT);
    }

    @JsonCreator
    public static ResponseDate fromString(String str) {
        try {
            return new ResponseDate(ZonedDateTime.parse(str, Time.RESPONSE_DATE_FORMAT));
        } catch (DateTimeParseException unused) {
            return new ResponseDate(ZonedDateTime.parse(str, MINIO_RESPONSE_DATE_FORMAT));
        }
    }

    /* loaded from: classes7.dex */
    public static class ResponseDateConverter implements Converter<ResponseDate> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.simpleframework.xml.convert.Converter
        public ResponseDate read(InputNode inputNode) throws Exception {
            return ResponseDate.fromString(inputNode.getValue());
        }

        @Override // org.simpleframework.xml.convert.Converter
        public void write(OutputNode outputNode, ResponseDate responseDate) {
            outputNode.setValue(responseDate.toString());
        }
    }
}
