package org.nanohttpd.protocols.http.tempfiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.nanohttpd.protocols.http.NanoHTTPD;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class DefaultTempFile implements ITempFile {
    private final File file;
    private final OutputStream fstream;

    public DefaultTempFile(File file) throws IOException {
        this.file = File.createTempFile("NanoHTTPD-", "", file);
        this.fstream = new FileOutputStream(this.file);
    }

    @Override // org.nanohttpd.protocols.http.tempfiles.ITempFile
    public void delete() throws Exception {
        NanoHTTPD.safeClose(this.fstream);
        if (this.file.delete()) {
            return;
        }
        throw new Exception("could not delete temporary file: " + this.file.getAbsolutePath());
    }

    @Override // org.nanohttpd.protocols.http.tempfiles.ITempFile
    public String getName() {
        return this.file.getAbsolutePath();
    }

    @Override // org.nanohttpd.protocols.http.tempfiles.ITempFile
    public OutputStream open() throws Exception {
        return this.fstream;
    }
}
