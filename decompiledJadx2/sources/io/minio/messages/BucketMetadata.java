package io.minio.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/* loaded from: classes7.dex */
public class BucketMetadata {

    @JsonProperty
    private String arn;

    @JsonProperty
    private String name;

    @JsonProperty
    private Identity ownerIdentity;

    public String name() {
        return this.name;
    }

    public String owner() {
        Identity identity = this.ownerIdentity;
        if (identity == null) {
            return null;
        }
        return identity.principalId();
    }

    public String arn() {
        return this.arn;
    }
}
