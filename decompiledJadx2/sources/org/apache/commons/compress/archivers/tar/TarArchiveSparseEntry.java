package org.apache.commons.compress.archivers.tar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class TarArchiveSparseEntry implements TarConstants {
    private final boolean isExtended;
    private final List<TarArchiveStructSparse> sparseHeaders;

    public TarArchiveSparseEntry(byte[] bArr) throws IOException {
        this.sparseHeaders = new ArrayList(TarUtils.readSparseStructs(bArr, 0, 21));
        this.isExtended = TarUtils.parseBoolean(bArr, 504);
    }

    public boolean isExtended() {
        return this.isExtended;
    }

    public List<TarArchiveStructSparse> getSparseHeaders() {
        return this.sparseHeaders;
    }
}
