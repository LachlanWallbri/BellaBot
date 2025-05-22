package com.amazonaws.internal;

import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import java.io.FileInputStream;
import java.io.InputStream;

/* loaded from: classes.dex */
public class ReleasableInputStream extends SdkFilterInputStream implements Releasable {
    private static final Log log = LogFactory.getLog((Class<?>) ReleasableInputStream.class);
    private boolean closeDisabled;

    /* JADX INFO: Access modifiers changed from: protected */
    public ReleasableInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.closeDisabled) {
            return;
        }
        doRelease();
    }

    @Override // com.amazonaws.internal.Releasable
    public final void release() {
        doRelease();
    }

    private void doRelease() {
        try {
            this.in.close();
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("FYI", e);
            }
        }
        if (this.in instanceof Releasable) {
            ((Releasable) this.in).release();
        }
        abortIfNeeded();
    }

    public final boolean isCloseDisabled() {
        return this.closeDisabled;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends ReleasableInputStream> T disableClose() {
        this.closeDisabled = true;
        return this;
    }

    public static ReleasableInputStream wrap(InputStream inputStream) {
        if (inputStream instanceof ReleasableInputStream) {
            return (ReleasableInputStream) inputStream;
        }
        if (inputStream instanceof FileInputStream) {
            return ResettableInputStream.newResettableInputStream((FileInputStream) inputStream);
        }
        return new ReleasableInputStream(inputStream);
    }
}
