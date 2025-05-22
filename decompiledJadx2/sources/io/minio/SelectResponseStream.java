package io.minio;

import com.google.common.io.ByteStreams;
import io.minio.errors.InternalException;
import io.minio.errors.MinioException;
import io.minio.messages.Progress;
import io.minio.messages.Stats;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.CRC32;

/* loaded from: classes7.dex */
public class SelectResponseStream extends InputStream {
    private InputStream inputStream;
    private byte[] prelude = new byte[8];
    private byte[] preludeCrc = new byte[4];
    private byte[] messageCrc = new byte[4];
    private byte[] headerValueLen = new byte[2];
    private Stats stats = null;
    private ByteArrayInputStream payloadStream = null;
    private CRC32 crcHasher = new CRC32();

    public SelectResponseStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private Map<String, String> decodeHeaderData(ByteArrayInputStream byteArrayInputStream) throws IOException {
        HashMap hashMap = new HashMap();
        while (true) {
            int read = byteArrayInputStream.read();
            if (read < 0) {
                return hashMap;
            }
            int i = read & 255;
            byte[] bArr = new byte[i];
            if (byteArrayInputStream.read(bArr, 0, i) < 0) {
                throw new IOException("insufficient data");
            }
            String str = new String(bArr, StandardCharsets.UTF_8);
            int read2 = byteArrayInputStream.read();
            if (read2 < 0) {
                throw new IOException("insufficient data");
            }
            if ((read2 & 255) != 7) {
                throw new IOException("header value type is not 7");
            }
            if (byteArrayInputStream.read(this.headerValueLen, 0, 2) < 0) {
                throw new IOException("insufficient data");
            }
            byte[] bArr2 = this.headerValueLen;
            int i2 = (bArr2[1] & 255) | ((bArr2[0] & 255) << 8);
            byte[] bArr3 = new byte[i2];
            if (byteArrayInputStream.read(bArr3, 0, i2) < 0) {
                throw new IOException("insufficient data");
            }
            hashMap.put(str, new String(bArr3, StandardCharsets.UTF_8));
        }
    }

    private boolean populate() throws EOFException, IOException, InternalException, MinioException {
        ByteStreams.readFully(this.inputStream, this.prelude);
        ByteStreams.readFully(this.inputStream, this.preludeCrc);
        this.crcHasher.reset();
        this.crcHasher.update(this.prelude);
        if (((int) this.crcHasher.getValue()) != ByteBuffer.wrap(this.preludeCrc).getInt()) {
            throw new IOException("prelude CRC mismatch; expected: " + ((int) this.crcHasher.getValue()) + ", got: " + ByteBuffer.wrap(this.preludeCrc).getInt());
        }
        int i = ByteBuffer.wrap(this.prelude, 0, 4).getInt();
        byte[] bArr = new byte[(i - 12) - 4];
        ByteStreams.readFully(this.inputStream, bArr);
        ByteStreams.readFully(this.inputStream, this.messageCrc);
        this.crcHasher.reset();
        this.crcHasher.update(this.prelude);
        this.crcHasher.update(this.preludeCrc);
        this.crcHasher.update(bArr);
        if (((int) this.crcHasher.getValue()) != ByteBuffer.wrap(this.messageCrc).getInt()) {
            throw new IOException("message CRC mismatch; expected: " + ((int) this.crcHasher.getValue()) + ", got: " + ByteBuffer.wrap(this.messageCrc).getInt());
        }
        int i2 = ByteBuffer.wrap(this.prelude, 4, 4).getInt();
        try {
            Map<String, String> decodeHeaderData = decodeHeaderData(new ByteArrayInputStream(bArr, 0, i2));
            if (decodeHeaderData.get(":message-type").equals("error")) {
                throw new MinioException(decodeHeaderData.get(":error-code") + ":" + decodeHeaderData.get(":error-message"));
            }
            if (decodeHeaderData.get(":event-type").equals("End")) {
                throw new EOFException();
            }
            int i3 = (i - i2) - 16;
            if (decodeHeaderData.get(":event-type").equals("Cont") || i3 < 1) {
                return false;
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i2, i3);
            if (decodeHeaderData.get(":event-type").equals("Progress")) {
                this.stats = (Stats) Xml.unmarshal(Progress.class, new InputStreamReader(byteArrayInputStream, StandardCharsets.UTF_8));
                return false;
            }
            if (decodeHeaderData.get(":event-type").equals("Stats")) {
                this.stats = (Stats) Xml.unmarshal(Stats.class, new InputStreamReader(byteArrayInputStream, StandardCharsets.UTF_8));
                return false;
            }
            if (decodeHeaderData.get(":event-type").equals("Records")) {
                this.payloadStream = byteArrayInputStream;
                return true;
            }
            throw new InternalException("unknown event-type '" + decodeHeaderData.get(":event-type") + "'", null);
        } catch (EOFException unused) {
            throw new IOException("invalid header read");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0008, code lost:
    
        if (populate() != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x000b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0011, code lost:
    
        throw new java.io.IOException(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0012, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0002, code lost:
    
        if (r2.payloadStream == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r0 = r2.payloadStream.read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x001a, code lost:
    
        if (r0 >= 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001c, code lost:
    
        r2.payloadStream = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0023, code lost:
    
        return read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:?, code lost:
    
        return r0;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int read() throws IOException {
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inputStream.close();
    }

    public Stats stats() {
        return this.stats;
    }
}
