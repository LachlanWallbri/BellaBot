package org.nanohttpd.protocols.webserver;

import java.io.ByteArrayInputStream;
import java.util.Map;
import org.nanohttpd.protocols.http.response.Response;
import org.nanohttpd.protocols.http.response.Status;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class InternalRewrite extends Response {
    private final Map<String, String> headers;
    private final String uri;

    public InternalRewrite(Map<String, String> map, String str) {
        super(Status.OK, "text/html", new ByteArrayInputStream(new byte[0]), 0L);
        this.headers = map;
        this.uri = str;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getUri() {
        return this.uri;
    }
}
