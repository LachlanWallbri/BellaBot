package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "Retention", strict = false)
/* loaded from: classes7.dex */
public class Retention {

    @Element(name = "Mode", required = false)
    private RetentionMode mode;

    @Element(name = "RetainUntilDate", required = false)
    private ResponseDate retainUntilDate;

    public Retention() {
    }

    public Retention(RetentionMode retentionMode, ZonedDateTime zonedDateTime) {
        if (retentionMode == null) {
            throw new IllegalArgumentException("null mode is not allowed");
        }
        if (zonedDateTime == null) {
            throw new IllegalArgumentException("null retainUntilDate is not allowed");
        }
        this.mode = retentionMode;
        this.retainUntilDate = new ResponseDate(zonedDateTime);
    }

    public RetentionMode mode() {
        return this.mode;
    }

    public ZonedDateTime retainUntilDate() {
        ResponseDate responseDate = this.retainUntilDate;
        if (responseDate != null) {
            return responseDate.zonedDateTime();
        }
        return null;
    }
}
