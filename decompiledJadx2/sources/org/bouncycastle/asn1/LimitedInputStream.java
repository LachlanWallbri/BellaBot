package org.bouncycastle.asn1;

import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public abstract class LimitedInputStream extends InputStream {
    protected final InputStream _in;
    private int _length;
    private int _limit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LimitedInputStream(InputStream inputStream, int i, int i2) {
        this._in = inputStream;
        this._limit = i;
        this._length = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLimit() {
        return this._limit;
    }

    int getRemaining() {
        return this._length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setParentEofDetect(boolean z) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).setEofOn00(z);
        }
    }
}
