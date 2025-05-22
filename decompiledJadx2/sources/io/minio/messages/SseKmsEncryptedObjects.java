package io.minio.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "SseKmsEncryptedObjects")
/* loaded from: classes7.dex */
public class SseKmsEncryptedObjects {

    @Element(name = "Status")
    private Status status;

    public SseKmsEncryptedObjects(@Element(name = "Status") @Nonnull Status status) {
        this.status = (Status) Objects.requireNonNull(status, "Status must not be null");
    }

    public Status status() {
        return this.status;
    }
}
