package io.minio.messages;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "Years")
/* loaded from: classes7.dex */
public class RetentionDurationYears implements RetentionDuration {

    @Text(required = false)
    private Integer years;

    public RetentionDurationYears() {
    }

    public RetentionDurationYears(int i) {
        this.years = Integer.valueOf(i);
    }

    @Override // io.minio.messages.RetentionDuration
    public RetentionDurationUnit unit() {
        return RetentionDurationUnit.YEARS;
    }

    @Override // io.minio.messages.RetentionDuration
    public int duration() {
        return this.years.intValue();
    }

    public String toString() {
        if (this.years == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.years.toString());
        sb.append(this.years.intValue() == 1 ? " year" : " years");
        return sb.toString();
    }
}
