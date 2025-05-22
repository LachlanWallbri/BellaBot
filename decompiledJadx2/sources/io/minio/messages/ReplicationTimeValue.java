package io.minio.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ReplicationTimeValue")
/* loaded from: classes7.dex */
public class ReplicationTimeValue {

    @Element(name = "Minutes", required = false)
    private Integer minutes;

    public ReplicationTimeValue(@Element(name = "Minutes", required = false) @Nullable Integer num) {
        this.minutes = 15;
        this.minutes = num;
    }

    public Integer minutes() {
        return this.minutes;
    }
}
