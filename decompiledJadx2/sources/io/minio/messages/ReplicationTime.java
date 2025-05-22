package io.minio.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ReplicationTime")
/* loaded from: classes7.dex */
public class ReplicationTime {

    @Element(name = "Status")
    private Status status;

    @Element(name = "Time")
    private ReplicationTimeValue time;

    public ReplicationTime(@Element(name = "Time") @Nonnull ReplicationTimeValue replicationTimeValue, @Element(name = "Status") @Nonnull Status status) {
        this.time = (ReplicationTimeValue) Objects.requireNonNull(replicationTimeValue, "Time must not be null");
        this.status = (Status) Objects.requireNonNull(status, "Status must not be null");
    }

    public ReplicationTimeValue time() {
        return this.time;
    }

    public Status status() {
        return this.status;
    }
}
