package io.minio.messages;

import java.time.ZonedDateTime;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Transition")
/* loaded from: classes7.dex */
public class Transition extends DateDays {

    @Element(name = "StorageClass")
    private String storageClass;

    public Transition(@Element(name = "Date", required = false) @Nullable ResponseDate responseDate, @Element(name = "Days", required = false) @Nullable Integer num, @Element(name = "StorageClass", required = false) @Nonnull String str) {
        super(responseDate, num);
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("StorageClass must be provided");
        }
        this.storageClass = str;
    }

    public Transition(ZonedDateTime zonedDateTime, Integer num, String str) {
        this(zonedDateTime == null ? null : new ResponseDate(zonedDateTime), num, str);
    }

    public String storageClass() {
        return this.storageClass;
    }
}
