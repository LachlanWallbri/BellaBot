package io.minio.messages;

import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "NoncurrentVersionTransition")
/* loaded from: classes7.dex */
public class NoncurrentVersionTransition extends NoncurrentVersionExpiration {

    @Element(name = "StorageClass")
    private String storageClass;

    public NoncurrentVersionTransition(@Element(name = "NoncurrentDays", required = false) int i, @Element(name = "StorageClass", required = false) @Nonnull String str) {
        super(i);
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("StorageClass must be provided");
        }
        this.storageClass = str;
    }

    public String storageClass() {
        return this.storageClass;
    }
}
