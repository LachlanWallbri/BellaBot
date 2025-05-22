package org.nanohttpd.protocols.http.tempfiles;

import org.nanohttpd.protocols.util.IFactory;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class DefaultTempFileManagerFactory implements IFactory<ITempFileManager> {
    @Override // org.nanohttpd.protocols.util.IFactory
    public ITempFileManager create() {
        return new DefaultTempFileManager();
    }
}
