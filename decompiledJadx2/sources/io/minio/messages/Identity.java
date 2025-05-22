package io.minio.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/* loaded from: classes7.dex */
public class Identity {

    @JsonProperty
    private String principalId;

    public String principalId() {
        return this.principalId;
    }
}
