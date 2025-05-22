package org.nanohttpd.protocols.webserver;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface WebServerPluginInfo {
    String[] getIndexFilesForMimeType(String str);

    String[] getMimeTypes();

    WebServerPlugin getWebServerPlugin(String str);
}
