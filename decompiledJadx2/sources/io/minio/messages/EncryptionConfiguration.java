package io.minio.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "EncryptionConfiguration")
/* loaded from: classes7.dex */
public class EncryptionConfiguration {

    @Element(name = "ReplicaKmsKeyID", required = false)
    private String replicaKmsKeyID;

    public EncryptionConfiguration(@Element(name = "ReplicaKmsKeyID", required = false) @Nullable String str) {
        this.replicaKmsKeyID = str;
    }

    public String replicaKmsKeyID() {
        return this.replicaKmsKeyID;
    }
}
