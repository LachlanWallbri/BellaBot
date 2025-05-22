package org.bouncycastle.crypto.tls;

import androidx.core.view.InputDeviceCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.util.io.SimpleOutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class RecordStream {
    private static int DEFAULT_PLAINTEXT_LIMIT = 16384;
    static final int TLS_HEADER_LENGTH_OFFSET = 3;
    static final int TLS_HEADER_SIZE = 5;
    static final int TLS_HEADER_TYPE_OFFSET = 0;
    static final int TLS_HEADER_VERSION_OFFSET = 1;
    private int ciphertextLimit;
    private int compressedLimit;
    private TlsProtocol handler;
    private InputStream input;
    private OutputStream output;
    private int plaintextLimit;
    private TlsCompression readCompression;
    private SequenceNumber readSeqNo;
    private TlsCompression writeCompression;
    private SequenceNumber writeSeqNo;
    private TlsCompression pendingCompression = null;
    private TlsCipher pendingCipher = null;
    private TlsCipher readCipher = null;
    private TlsCipher writeCipher = null;
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private TlsHandshakeHash handshakeHash = null;
    private SimpleOutputStream handshakeHashUpdater = new SimpleOutputStream() { // from class: org.bouncycastle.crypto.tls.RecordStream.1
        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            RecordStream.this.handshakeHash.update(bArr, i, i2);
        }
    };
    private ProtocolVersion readVersion = null;
    private ProtocolVersion writeVersion = null;
    private boolean restrictReadVersion = true;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class SequenceNumber {
        private boolean exhausted;
        private long value;

        private SequenceNumber() {
            this.value = 0L;
            this.exhausted = false;
        }

        synchronized long nextValue(short s) throws TlsFatalAlert {
            long j;
            if (this.exhausted) {
                throw new TlsFatalAlert(s);
            }
            j = this.value;
            long j2 = this.value + 1;
            this.value = j2;
            if (j2 == 0) {
                this.exhausted = true;
            }
            return j;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecordStream(TlsProtocol tlsProtocol, InputStream inputStream, OutputStream outputStream) {
        this.readCompression = null;
        this.writeCompression = null;
        this.readSeqNo = new SequenceNumber();
        this.writeSeqNo = new SequenceNumber();
        this.handler = tlsProtocol;
        this.input = inputStream;
        this.output = outputStream;
        this.readCompression = new TlsNullCompression();
        this.writeCompression = this.readCompression;
    }

    private static void checkLength(int i, int i2, short s) throws IOException {
        if (i > i2) {
            throw new TlsFatalAlert(s);
        }
    }

    private static void checkType(short s, short s2) throws IOException {
        switch (s) {
            case 20:
            case 21:
            case 22:
            case 23:
                return;
            default:
                throw new TlsFatalAlert(s2);
        }
    }

    private byte[] getBufferContents() {
        byte[] byteArray = this.buffer.toByteArray();
        this.buffer.reset();
        return byteArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkRecordHeader(byte[] bArr) throws IOException {
        checkType(TlsUtils.readUint8(bArr, 0), (short) 10);
        if (this.restrictReadVersion) {
            ProtocolVersion readVersion = TlsUtils.readVersion(bArr, 1);
            ProtocolVersion protocolVersion = this.readVersion;
            if (protocolVersion != null && !readVersion.equals(protocolVersion)) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if ((TlsUtils.readVersionRaw(bArr, 1) & InputDeviceCompat.SOURCE_ANY) != 768) {
            throw new TlsFatalAlert((short) 47);
        }
        checkLength(TlsUtils.readUint16(bArr, 3), this.ciphertextLimit, (short) 22);
    }

    byte[] decodeAndVerify(short s, InputStream inputStream, int i) throws IOException {
        byte[] readFully = TlsUtils.readFully(i, inputStream);
        byte[] decodeCiphertext = this.readCipher.decodeCiphertext(this.readSeqNo.nextValue((short) 10), s, readFully, 0, readFully.length);
        checkLength(decodeCiphertext.length, this.compressedLimit, (short) 22);
        OutputStream decompress = this.readCompression.decompress(this.buffer);
        if (decompress != this.buffer) {
            decompress.write(decodeCiphertext, 0, decodeCiphertext.length);
            decompress.flush();
            decodeCiphertext = getBufferContents();
        }
        checkLength(decodeCiphertext.length, this.plaintextLimit, (short) 30);
        if (decodeCiphertext.length >= 1 || s == 23) {
            return decodeCiphertext;
        }
        throw new TlsFatalAlert((short) 47);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void finaliseHandshake() throws IOException {
        TlsCompression tlsCompression = this.readCompression;
        TlsCompression tlsCompression2 = this.pendingCompression;
        if (tlsCompression == tlsCompression2 && this.writeCompression == tlsCompression2) {
            TlsCipher tlsCipher = this.readCipher;
            TlsCipher tlsCipher2 = this.pendingCipher;
            if (tlsCipher == tlsCipher2 && this.writeCipher == tlsCipher2) {
                this.pendingCompression = null;
                this.pendingCipher = null;
                return;
            }
        }
        throw new TlsFatalAlert((short) 40);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flush() throws IOException {
        this.output.flush();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsHandshakeHash getHandshakeHash() {
        return this.handshakeHash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputStream getHandshakeHashUpdater() {
        return this.handshakeHashUpdater;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPlaintextLimit() {
        return this.plaintextLimit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProtocolVersion getReadVersion() {
        return this.readVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(TlsContext tlsContext) {
        this.readCipher = new TlsNullCipher(tlsContext);
        this.writeCipher = this.readCipher;
        this.handshakeHash = new DeferredHash();
        this.handshakeHash.init(tlsContext);
        setPlaintextLimit(DEFAULT_PLAINTEXT_LIMIT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyHelloComplete() {
        this.handshakeHash = this.handshakeHash.notifyPRFDetermined();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsHandshakeHash prepareToFinish() {
        TlsHandshakeHash tlsHandshakeHash = this.handshakeHash;
        this.handshakeHash = tlsHandshakeHash.stopTracking();
        return tlsHandshakeHash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean readRecord() throws IOException {
        byte[] readAllOrNothing = TlsUtils.readAllOrNothing(5, this.input);
        if (readAllOrNothing == null) {
            return false;
        }
        short readUint8 = TlsUtils.readUint8(readAllOrNothing, 0);
        checkType(readUint8, (short) 10);
        if (this.restrictReadVersion) {
            ProtocolVersion readVersion = TlsUtils.readVersion(readAllOrNothing, 1);
            ProtocolVersion protocolVersion = this.readVersion;
            if (protocolVersion == null) {
                this.readVersion = readVersion;
            } else if (!readVersion.equals(protocolVersion)) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if ((TlsUtils.readVersionRaw(readAllOrNothing, 1) & InputDeviceCompat.SOURCE_ANY) != 768) {
            throw new TlsFatalAlert((short) 47);
        }
        int readUint16 = TlsUtils.readUint16(readAllOrNothing, 3);
        checkLength(readUint16, this.ciphertextLimit, (short) 22);
        byte[] decodeAndVerify = decodeAndVerify(readUint8, this.input, readUint16);
        this.handler.processRecord(readUint8, decodeAndVerify, 0, decodeAndVerify.length);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void receivedReadCipherSpec() throws IOException {
        TlsCipher tlsCipher;
        TlsCompression tlsCompression = this.pendingCompression;
        if (tlsCompression == null || (tlsCipher = this.pendingCipher) == null) {
            throw new TlsFatalAlert((short) 40);
        }
        this.readCompression = tlsCompression;
        this.readCipher = tlsCipher;
        this.readSeqNo = new SequenceNumber();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void safeClose() {
        try {
            this.input.close();
        } catch (IOException unused) {
        }
        try {
            this.output.close();
        } catch (IOException unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sentWriteCipherSpec() throws IOException {
        TlsCipher tlsCipher;
        TlsCompression tlsCompression = this.pendingCompression;
        if (tlsCompression == null || (tlsCipher = this.pendingCipher) == null) {
            throw new TlsFatalAlert((short) 40);
        }
        this.writeCompression = tlsCompression;
        this.writeCipher = tlsCipher;
        this.writeSeqNo = new SequenceNumber();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPendingConnectionState(TlsCompression tlsCompression, TlsCipher tlsCipher) {
        this.pendingCompression = tlsCompression;
        this.pendingCipher = tlsCipher;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPlaintextLimit(int i) {
        this.plaintextLimit = i;
        this.compressedLimit = this.plaintextLimit + 1024;
        this.ciphertextLimit = this.compressedLimit + 1024;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setReadVersion(ProtocolVersion protocolVersion) {
        this.readVersion = protocolVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRestrictReadVersion(boolean z) {
        this.restrictReadVersion = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setWriteVersion(ProtocolVersion protocolVersion) {
        this.writeVersion = protocolVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeRecord(short s, byte[] bArr, int i, int i2) throws IOException {
        byte[] encodePlaintext;
        if (this.writeVersion == null) {
            return;
        }
        checkType(s, (short) 80);
        checkLength(i2, this.plaintextLimit, (short) 80);
        if (i2 < 1 && s != 23) {
            throw new TlsFatalAlert((short) 80);
        }
        OutputStream compress = this.writeCompression.compress(this.buffer);
        long nextValue = this.writeSeqNo.nextValue((short) 80);
        if (compress == this.buffer) {
            encodePlaintext = this.writeCipher.encodePlaintext(nextValue, s, bArr, i, i2);
        } else {
            compress.write(bArr, i, i2);
            compress.flush();
            byte[] bufferContents = getBufferContents();
            checkLength(bufferContents.length, i2 + 1024, (short) 80);
            encodePlaintext = this.writeCipher.encodePlaintext(nextValue, s, bufferContents, 0, bufferContents.length);
        }
        checkLength(encodePlaintext.length, this.ciphertextLimit, (short) 80);
        byte[] bArr2 = new byte[encodePlaintext.length + 5];
        TlsUtils.writeUint8(s, bArr2, 0);
        TlsUtils.writeVersion(this.writeVersion, bArr2, 1);
        TlsUtils.writeUint16(encodePlaintext.length, bArr2, 3);
        System.arraycopy(encodePlaintext, 0, bArr2, 5, encodePlaintext.length);
        this.output.write(bArr2);
        this.output.flush();
    }
}
