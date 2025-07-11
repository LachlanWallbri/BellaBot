package org.conscrypt;

import java.io.IOException;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public final class GCMParameters extends AlgorithmParametersSpi {
    private static final int DEFAULT_TLEN = 96;

    /* renamed from: iv */
    private byte[] f9986iv;
    private int tLen;

    @Override // java.security.AlgorithmParametersSpi
    protected String engineToString() {
        return "Conscrypt GCM AlgorithmParameters";
    }

    public GCMParameters() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GCMParameters(int i, byte[] bArr) {
        this.tLen = i;
        this.f9986iv = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTLen() {
        return this.tLen;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getIV() {
        return this.f9986iv;
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        GCMParameters fromGCMParameterSpec = Platform.fromGCMParameterSpec(algorithmParameterSpec);
        if (fromGCMParameterSpec == null) {
            throw new InvalidParameterSpecException("Only GCMParameterSpec is supported");
        }
        this.tLen = fromGCMParameterSpec.tLen;
        this.f9986iv = fromGCMParameterSpec.f9986iv;
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr) throws IOException {
        long j;
        try {
            j = NativeCrypto.asn1_read_init(bArr);
        } catch (Throwable th) {
            th = th;
            j = 0;
        }
        try {
            long asn1_read_sequence = NativeCrypto.asn1_read_sequence(j);
            byte[] asn1_read_octetstring = NativeCrypto.asn1_read_octetstring(asn1_read_sequence);
            int asn1_read_uint64 = NativeCrypto.asn1_read_is_empty(asn1_read_sequence) ? 96 : ((int) NativeCrypto.asn1_read_uint64(asn1_read_sequence)) * 8;
            if (!NativeCrypto.asn1_read_is_empty(asn1_read_sequence) || !NativeCrypto.asn1_read_is_empty(j)) {
                throw new IOException("Error reading ASN.1 encoding");
            }
            this.f9986iv = asn1_read_octetstring;
            this.tLen = asn1_read_uint64;
            NativeCrypto.asn1_read_free(asn1_read_sequence);
            NativeCrypto.asn1_read_free(j);
        } catch (Throwable th2) {
            th = th2;
            NativeCrypto.asn1_read_free(0L);
            NativeCrypto.asn1_read_free(j);
            throw th;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr, String str) throws IOException {
        if (str == null || str.equals("ASN.1")) {
            engineInit(bArr);
            return;
        }
        throw new IOException("Unsupported format: " + str);
    }

    @Override // java.security.AlgorithmParametersSpi
    protected <T extends AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> cls) throws InvalidParameterSpecException {
        if (cls != null && cls.getName().equals("javax.crypto.spec.GCMParameterSpec")) {
            return cls.cast(Platform.toGCMParameterSpec(this.tLen, this.f9986iv));
        }
        throw new InvalidParameterSpecException("Unsupported class: " + cls);
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded() throws IOException {
        long j;
        long j2;
        long j3 = 0;
        try {
            j = NativeCrypto.asn1_write_init();
        } catch (IOException e) {
            e = e;
            j2 = 0;
        } catch (Throwable th) {
            th = th;
            j = 0;
        }
        try {
            j3 = NativeCrypto.asn1_write_sequence(j);
            NativeCrypto.asn1_write_octetstring(j3, this.f9986iv);
            if (this.tLen != 96) {
                NativeCrypto.asn1_write_uint64(j3, this.tLen / 8);
            }
            byte[] asn1_write_finish = NativeCrypto.asn1_write_finish(j);
            NativeCrypto.asn1_write_free(j3);
            NativeCrypto.asn1_write_free(j);
            return asn1_write_finish;
        } catch (IOException e2) {
            e = e2;
            long j4 = j3;
            j3 = j;
            j2 = j4;
            try {
                NativeCrypto.asn1_write_cleanup(j3);
                throw e;
            } catch (Throwable th2) {
                th = th2;
                long j5 = j3;
                j3 = j2;
                j = j5;
                NativeCrypto.asn1_write_free(j3);
                NativeCrypto.asn1_write_free(j);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            NativeCrypto.asn1_write_free(j3);
            NativeCrypto.asn1_write_free(j);
            throw th;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded(String str) throws IOException {
        if (str == null || str.equals("ASN.1")) {
            return engineGetEncoded();
        }
        throw new IOException("Unsupported format: " + str);
    }
}
