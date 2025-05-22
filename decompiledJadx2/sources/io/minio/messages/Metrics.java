package io.minio.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Metrics")
/* loaded from: classes7.dex */
public class Metrics {

    @Element(name = "EventThreshold")
    private ReplicationTimeValue eventThreshold;

    @Element(name = "Status")
    private Status status;

    public Metrics(@Element(name = "EventThreshold") @Nonnull ReplicationTimeValue replicationTimeValue, @Element(name = "Status") @Nonnull Status status) {
        this.eventThreshold = (ReplicationTimeValue) Objects.requireNonNull(replicationTimeValue, "Event threshold must not be null");
        this.status = (Status) Objects.requireNonNull(status, "Status must not be null");
    }

    public ReplicationTimeValue eventThreshold() {
        return this.eventThreshold;
    }

    public Status status() {
        return this.status;
    }
}
