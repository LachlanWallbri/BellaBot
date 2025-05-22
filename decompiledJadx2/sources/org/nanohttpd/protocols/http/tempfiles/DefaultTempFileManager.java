package org.nanohttpd.protocols.http.tempfiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.nanohttpd.protocols.http.NanoHTTPD;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class DefaultTempFileManager implements ITempFileManager {
    private final List<ITempFile> tempFiles;
    private final File tmpdir = new File(System.getProperty("java.io.tmpdir"));

    public DefaultTempFileManager() {
        if (!this.tmpdir.exists()) {
            this.tmpdir.mkdirs();
        }
        this.tempFiles = new ArrayList();
    }

    @Override // org.nanohttpd.protocols.http.tempfiles.ITempFileManager
    public void clear() {
        Iterator<ITempFile> it = this.tempFiles.iterator();
        while (it.hasNext()) {
            try {
                it.next().delete();
            } catch (Exception e) {
                NanoHTTPD.LOG.log(Level.WARNING, "could not delete file ", (Throwable) e);
            }
        }
        this.tempFiles.clear();
    }

    @Override // org.nanohttpd.protocols.http.tempfiles.ITempFileManager
    public ITempFile createTempFile(String str) throws Exception {
        DefaultTempFile defaultTempFile = new DefaultTempFile(this.tmpdir);
        this.tempFiles.add(defaultTempFile);
        return defaultTempFile;
    }
}
