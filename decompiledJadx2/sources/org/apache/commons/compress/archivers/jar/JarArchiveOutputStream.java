package org.apache.commons.compress.archivers.jar;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.JarMarker;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

/* loaded from: classes8.dex */
public class JarArchiveOutputStream extends ZipArchiveOutputStream {
    private boolean jarMarkerAdded;

    public JarArchiveOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public JarArchiveOutputStream(OutputStream outputStream, String str) {
        super(outputStream);
        setEncoding(str);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream, org.apache.commons.compress.archivers.ArchiveOutputStream
    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        if (!this.jarMarkerAdded) {
            ((ZipArchiveEntry) archiveEntry).addAsFirstExtraField(JarMarker.getInstance());
            this.jarMarkerAdded = true;
        }
        super.putArchiveEntry(archiveEntry);
    }
}
