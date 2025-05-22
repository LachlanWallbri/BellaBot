package io.minio.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ExistingObjectReplication")
/* loaded from: classes7.dex */
public class ExistingObjectReplication {

    @Element(name = "Status")
    private Status status;

    public ExistingObjectReplication(@Element(name = "Status") @Nonnull Status status) {
        this.status = (Status) Objects.requireNonNull(status, "Status must not be null");
    }

    public Status status() {
        return this.status;
    }
}
