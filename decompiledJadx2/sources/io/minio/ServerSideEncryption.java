package io.minio;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public abstract class ServerSideEncryption {
    private static final Map<String, String> emptyHeaders = Collections.unmodifiableMap(new HashMap());

    public abstract Map<String, String> headers();

    public boolean tlsRequired() {
        return true;
    }

    public Map<String, String> copySourceHeaders() {
        return emptyHeaders;
    }
}
