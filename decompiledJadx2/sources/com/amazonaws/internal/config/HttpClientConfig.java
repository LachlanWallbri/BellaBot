package com.amazonaws.internal.config;

/* loaded from: classes.dex */
public class HttpClientConfig {
    private final String serviceName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpClientConfig(String str) {
        this.serviceName = str;
    }

    public String toString() {
        return "serviceName: " + this.serviceName;
    }

    public String getServiceName() {
        return this.serviceName;
    }
}
