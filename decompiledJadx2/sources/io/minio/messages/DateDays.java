package io.minio.messages;

import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;

/* loaded from: classes7.dex */
public abstract class DateDays {

    @Element(name = "Date", required = false)
    private ResponseDate date;

    @Element(name = "Days", required = false)
    private Integer days;

    public DateDays(ResponseDate responseDate, Integer num) {
        if ((num != null) ^ (responseDate != null)) {
            this.date = responseDate;
            this.days = num;
            return;
        }
        throw new IllegalArgumentException("Only one of date or days must be set");
    }

    public ZonedDateTime date() {
        ResponseDate responseDate = this.date;
        if (responseDate != null) {
            return responseDate.zonedDateTime();
        }
        return null;
    }

    public Integer days() {
        return this.days;
    }
}
