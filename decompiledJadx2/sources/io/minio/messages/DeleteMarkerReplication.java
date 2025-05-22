package io.minio.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "DeleteMarkerReplication")
/* loaded from: classes7.dex */
public class DeleteMarkerReplication {

    @Element(name = "Status", required = false)
    private Status status;

    public DeleteMarkerReplication(@Element(name = "Status", required = false) @Nullable Status status) {
        this.status = status == null ? Status.DISABLED : status;
    }

    public Status status() {
        return this.status;
    }
}
