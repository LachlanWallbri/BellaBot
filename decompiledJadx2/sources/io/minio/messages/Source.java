package io.minio.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/* loaded from: classes7.dex */
public class Source {

    @JsonProperty
    private String host;

    @JsonProperty
    private String port;

    @JsonProperty
    private String userAgent;

    public String host() {
        return this.host;
    }

    public String port() {
        return this.port;
    }

    public String userAgent() {
        return this.userAgent;
    }
}
