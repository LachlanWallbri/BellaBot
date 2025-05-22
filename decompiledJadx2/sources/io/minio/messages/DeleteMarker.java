package io.minio.messages;

import org.simpleframework.xml.Root;

@Root(name = "DeleteMarker", strict = false)
/* loaded from: classes7.dex */
public class DeleteMarker extends Item {
    public DeleteMarker() {
    }

    public DeleteMarker(String str) {
        super(str);
    }
}
