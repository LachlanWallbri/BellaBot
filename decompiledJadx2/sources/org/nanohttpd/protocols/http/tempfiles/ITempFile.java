package org.nanohttpd.protocols.http.tempfiles;

import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface ITempFile {
    void delete() throws Exception;

    String getName();

    OutputStream open() throws Exception;
}
