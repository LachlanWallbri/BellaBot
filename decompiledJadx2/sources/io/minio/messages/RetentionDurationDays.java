package io.minio.messages;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "Days")
/* loaded from: classes7.dex */
public class RetentionDurationDays implements RetentionDuration {

    @Text(required = false)
    private Integer days;

    public RetentionDurationDays() {
    }

    public RetentionDurationDays(int i) {
        this.days = Integer.valueOf(i);
    }

    @Override // io.minio.messages.RetentionDuration
    public RetentionDurationUnit unit() {
        return RetentionDurationUnit.DAYS;
    }

    @Override // io.minio.messages.RetentionDuration
    public int duration() {
        return this.days.intValue();
    }

    public String toString() {
        if (this.days == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.days.toString());
        sb.append(this.days.intValue() == 1 ? " day" : " days");
        return sb.toString();
    }
}
