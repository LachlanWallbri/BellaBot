package com.squareup.okhttp.internal.framed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSource;
import okio.InflaterSource;
import okio.Okio;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class NameValueBlockReader {
    private int compressedLimit;
    private final InflaterSource inflaterSource;
    private final BufferedSource source;

    static /* synthetic */ int access$022(NameValueBlockReader nameValueBlockReader, long j) {
        int i = (int) (nameValueBlockReader.compressedLimit - j);
        nameValueBlockReader.compressedLimit = i;
        return i;
    }

    public NameValueBlockReader(BufferedSource bufferedSource) {
        this.inflaterSource = new InflaterSource(new ForwardingSource(bufferedSource) { // from class: com.squareup.okhttp.internal.framed.NameValueBlockReader.1
            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                if (NameValueBlockReader.this.compressedLimit == 0) {
                    return -1L;
                }
                long read = super.read(buffer, Math.min(j, NameValueBlockReader.this.compressedLimit));
                if (read == -1) {
                    return -1L;
                }
                NameValueBlockReader.access$022(NameValueBlockReader.this, read);
                return read;
            }
        }, new Inflater() { // from class: com.squareup.okhttp.internal.framed.NameValueBlockReader.2
            @Override // java.util.zip.Inflater
            public int inflate(byte[] bArr, int i, int i2) throws DataFormatException {
                int inflate = super.inflate(bArr, i, i2);
                if (inflate != 0 || !needsDictionary()) {
                    return inflate;
                }
                setDictionary(Spdy3.DICTIONARY);
                return super.inflate(bArr, i, i2);
            }
        });
        this.source = Okio.buffer(this.inflaterSource);
    }

    public List<Header> readNameValueBlock(int i) throws IOException {
        this.compressedLimit += i;
        int readInt = this.source.readInt();
        if (readInt < 0) {
            throw new IOException("numberOfPairs < 0: " + readInt);
        }
        if (readInt > 1024) {
            throw new IOException("numberOfPairs > 1024: " + readInt);
        }
        ArrayList arrayList = new ArrayList(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            ByteString asciiLowercase = readByteString().toAsciiLowercase();
            ByteString readByteString = readByteString();
            if (asciiLowercase.size() == 0) {
                throw new IOException("name.size == 0");
            }
            arrayList.add(new Header(asciiLowercase, readByteString));
        }
        doneReading();
        return arrayList;
    }

    private ByteString readByteString() throws IOException {
        return this.source.readByteString(this.source.readInt());
    }

    private void doneReading() throws IOException {
        if (this.compressedLimit > 0) {
            this.inflaterSource.refill();
            if (this.compressedLimit == 0) {
                return;
            }
            throw new IOException("compressedLimit > 0: " + this.compressedLimit);
        }
    }

    public void close() throws IOException {
        this.source.close();
    }
}
