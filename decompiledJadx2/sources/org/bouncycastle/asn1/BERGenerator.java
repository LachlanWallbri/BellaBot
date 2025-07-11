package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes9.dex */
public class BERGenerator extends ASN1Generator {
    private boolean _isExplicit;
    private int _tagNo;
    private boolean _tagged;

    /* JADX INFO: Access modifiers changed from: protected */
    public BERGenerator(OutputStream outputStream) {
        super(outputStream);
        this._tagged = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BERGenerator(OutputStream outputStream, int i, boolean z) {
        super(outputStream);
        this._tagged = false;
        this._tagged = true;
        this._isExplicit = z;
        this._tagNo = i;
    }

    private void writeHdr(int i) throws IOException {
        this._out.write(i);
        this._out.write(128);
    }

    @Override // org.bouncycastle.asn1.ASN1Generator
    public OutputStream getRawOutputStream() {
        return this._out;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeBEREnd() throws IOException {
        this._out.write(0);
        this._out.write(0);
        if (this._tagged && this._isExplicit) {
            this._out.write(0);
            this._out.write(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeBERHeader(int i) throws IOException {
        if (this._tagged) {
            int i2 = this._tagNo | 128;
            if (this._isExplicit) {
                writeHdr(i2 | 32);
            } else {
                if ((i & 32) == 0) {
                    writeHdr(i2);
                    return;
                }
                i = i2 | 32;
            }
        }
        writeHdr(i);
    }
}
