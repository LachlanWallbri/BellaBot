package io.minio.messages;

import java.time.ZonedDateTime;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Expiration")
/* loaded from: classes7.dex */
public class Expiration extends DateDays {

    @Element(name = "ExpiredObjectDeleteMarker", required = false)
    private Boolean expiredObjectDeleteMarker;

    public Expiration(@Element(name = "Date", required = false) @Nullable ResponseDate responseDate, @Element(name = "Days", required = false) @Nullable Integer num, @Element(name = "ExpiredObjectDeleteMarker", required = false) @Nullable Boolean bool) {
        super(responseDate, num);
        this.expiredObjectDeleteMarker = bool;
    }

    public Expiration(ZonedDateTime zonedDateTime, Integer num, Boolean bool) {
        this(zonedDateTime == null ? null : new ResponseDate(zonedDateTime), num, bool);
    }

    public Boolean expiredObjectDeleteMarker() {
        return this.expiredObjectDeleteMarker;
    }
}
