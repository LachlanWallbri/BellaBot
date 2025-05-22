package org.nanohttpd.protocols.http.tempfiles;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface ITempFileManager {
    void clear();

    ITempFile createTempFile(String str) throws Exception;
}
