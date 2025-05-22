package org.nanohttpd.protocols.webserver;

import java.io.File;
import java.util.Map;
import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.response.Response;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface WebServerPlugin {
    boolean canServeUri(String str, File file);

    void initialize(Map<String, String> map);

    Response serveFile(String str, Map<String, String> map, IHTTPSession iHTTPSession, File file, String str2);
}
