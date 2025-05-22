package com.amazonaws;

import org.apache.http.HttpHost;

/* loaded from: classes.dex */
public enum Protocol {
    HTTP(HttpHost.DEFAULT_SCHEME_NAME),
    HTTPS("https");

    private final String protocol;

    Protocol(String str) {
        this.protocol = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}
