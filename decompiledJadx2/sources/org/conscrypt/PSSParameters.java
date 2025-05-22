package org.conscrypt;

import java.io.IOException;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class PSSParameters extends AlgorithmParametersSpi {
    private PSSParameterSpec spec = PSSParameterSpec.DEFAULT;

    @Override // java.security.AlgorithmParametersSpi
    protected String engineToString() {
        return "Conscrypt PSS AlgorithmParameters";
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (algorithmParameterSpec instanceof PSSParameterSpec) {
            this.spec = (PSSParameterSpec) algorithmParameterSpec;
            return;
        }
        throw new InvalidParameterSpecException("Only PSSParameterSpec is supported");
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr) throws IOException {
        long j;
        long j2;
        int i;
        long asn1_read_tagged;
        long j3 = 0;
        try {
            j = NativeCrypto.asn1_read_init(bArr);
            try {
                j2 = NativeCrypto.asn1_read_sequence(j);
                i = 20;
            } catch (Throwable th) {
                th = th;
                j2 = 0;
            }
        } catch (Throwable th2) {
            th = th2;
            j = 0;
            j2 = 0;
        }
        try {
            String readHash = OAEPParameters.readHash(j2);
            String readMgfHash = OAEPParameters.readMgfHash(j2);
            if (NativeCrypto.asn1_read_next_tag_is(j2, 2)) {
                try {
                    asn1_read_tagged = NativeCrypto.asn1_read_tagged(j2);
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    i = (int) NativeCrypto.asn1_read_uint64(asn1_read_tagged);
                    NativeCrypto.asn1_read_free(asn1_read_tagged);
                } catch (Throwable th4) {
                    th = th4;
                    j3 = asn1_read_tagged;
                    throw th;
                }
            }
            int i2 = i;
            if (NativeCrypto.asn1_read_next_tag_is(j2, 3)) {
                try {
                    j3 = NativeCrypto.asn1_read_tagged(j2);
                    long asn1_read_uint64 = (int) NativeCrypto.asn1_read_uint64(j3);
                    NativeCrypto.asn1_read_free(j3);
                    if (asn1_read_uint64 != 1) {
                        throw new IOException("Error reading ASN.1 encoding");
                    }
                } finally {
                    NativeCrypto.asn1_read_free(j3);
                }
            }
            if (!NativeCrypto.asn1_read_is_empty(j2) || !NativeCrypto.asn1_read_is_empty(j)) {
                throw new IOException("Error reading ASN.1 encoding");
            }
            this.spec = new PSSParameterSpec(readHash, "MGF1", new MGF1ParameterSpec(readMgfHash), i2, 1);
            NativeCrypto.asn1_read_free(j2);
            NativeCrypto.asn1_read_free(j);
        } catch (Throwable th5) {
            th = th5;
            NativeCrypto.asn1_read_free(j2);
            NativeCrypto.asn1_read_free(j);
            throw th;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr, String str) throws IOException {
        if (str == null || str.equals("ASN.1") || str.equals("X.509")) {
            engineInit(bArr);
            return;
        }
        throw new IOException("Unsupported format: " + str);
    }

    @Override // java.security.AlgorithmParametersSpi
    protected <T extends AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> cls) throws InvalidParameterSpecException {
        if (cls != null && cls == PSSParameterSpec.class) {
            return this.spec;
        }
        throw new InvalidParameterSpecException("Unsupported class: " + cls);
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded() throws IOException {
        long j;
        Throwable th;
        long j2;
        IOException e;
        long asn1_write_sequence;
        long j3 = 0;
        try {
            try {
                j2 = NativeCrypto.asn1_write_init();
            } catch (IOException e2) {
                e = e2;
                j2 = 0;
            } catch (Throwable th2) {
                j = 0;
                th = th2;
                j2 = 0;
            }
            try {
                asn1_write_sequence = NativeCrypto.asn1_write_sequence(j2);
            } catch (IOException e3) {
                e = e3;
            } catch (Throwable th3) {
                th = th3;
                j = 0;
                NativeCrypto.asn1_write_free(j);
                NativeCrypto.asn1_write_free(j2);
                throw th;
            }
            try {
                OAEPParameters.writeHashAndMgfHash(asn1_write_sequence, this.spec.getDigestAlgorithm(), (MGF1ParameterSpec) this.spec.getMGFParameters());
                if (this.spec.getSaltLength() != 20) {
                    try {
                        j3 = NativeCrypto.asn1_write_tag(asn1_write_sequence, 2);
                        NativeCrypto.asn1_write_uint64(j3, this.spec.getSaltLength());
                        NativeCrypto.asn1_write_flush(asn1_write_sequence);
                        NativeCrypto.asn1_write_free(j3);
                    } catch (Throwable th4) {
                        NativeCrypto.asn1_write_flush(asn1_write_sequence);
                        NativeCrypto.asn1_write_free(j3);
                        throw th4;
                    }
                }
                byte[] asn1_write_finish = NativeCrypto.asn1_write_finish(j2);
                NativeCrypto.asn1_write_free(asn1_write_sequence);
                NativeCrypto.asn1_write_free(j2);
                return asn1_write_finish;
            } catch (IOException e4) {
                e = e4;
                NativeCrypto.asn1_write_cleanup(j2);
                throw e;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded(String str) throws IOException {
        if (str == null || str.equals("ASN.1") || str.equals("X.509")) {
            return engineGetEncoded();
        }
        throw new IOException("Unsupported format: " + str);
    }
}
