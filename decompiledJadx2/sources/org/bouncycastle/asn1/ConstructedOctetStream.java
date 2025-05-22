package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class ConstructedOctetStream extends InputStream {
    private InputStream _currentStream;
    private boolean _first = true;
    private final ASN1StreamParser _parser;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstructedOctetStream(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        ASN1Encodable readObject;
        if (this._currentStream == null) {
            if (!this._first || (readObject = this._parser.readObject()) == null) {
                return -1;
            }
            if (!(readObject instanceof ASN1OctetStringParser)) {
                throw new IOException("unknown object encountered: " + readObject.getClass());
            }
            ASN1OctetStringParser aSN1OctetStringParser = (ASN1OctetStringParser) readObject;
            this._first = false;
            this._currentStream = aSN1OctetStringParser.getOctetStream();
        }
        while (true) {
            int read = this._currentStream.read();
            if (read >= 0) {
                return read;
            }
            ASN1Encodable readObject2 = this._parser.readObject();
            if (readObject2 == null) {
                this._currentStream = null;
                return -1;
            }
            if (!(readObject2 instanceof ASN1OctetStringParser)) {
                throw new IOException("unknown object encountered: " + readObject2.getClass());
            }
            this._currentStream = ((ASN1OctetStringParser) readObject2).getOctetStream();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        ASN1Encodable readObject;
        int i3 = 0;
        if (this._currentStream == null) {
            if (!this._first || (readObject = this._parser.readObject()) == null) {
                return -1;
            }
            if (!(readObject instanceof ASN1OctetStringParser)) {
                throw new IOException("unknown object encountered: " + readObject.getClass());
            }
            ASN1OctetStringParser aSN1OctetStringParser = (ASN1OctetStringParser) readObject;
            this._first = false;
            this._currentStream = aSN1OctetStringParser.getOctetStream();
        }
        while (true) {
            int read = this._currentStream.read(bArr, i + i3, i2 - i3);
            if (read >= 0) {
                i3 += read;
                if (i3 == i2) {
                    return i3;
                }
            } else {
                ASN1Encodable readObject2 = this._parser.readObject();
                if (readObject2 == null) {
                    this._currentStream = null;
                    if (i3 < 1) {
                        return -1;
                    }
                    return i3;
                }
                if (!(readObject2 instanceof ASN1OctetStringParser)) {
                    throw new IOException("unknown object encountered: " + readObject2.getClass());
                }
                this._currentStream = ((ASN1OctetStringParser) readObject2).getOctetStream();
            }
        }
    }
}
