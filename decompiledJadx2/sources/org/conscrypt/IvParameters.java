package org.conscrypt;

import java.io.IOException;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class IvParameters extends AlgorithmParametersSpi {

    /* renamed from: iv */
    private byte[] f9988iv;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class AES extends IvParameters {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class ChaCha20 extends IvParameters {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class DESEDE extends IvParameters {
    }

    @Override // java.security.AlgorithmParametersSpi
    protected String engineToString() {
        return "Conscrypt IV AlgorithmParameters";
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (!(algorithmParameterSpec instanceof IvParameterSpec)) {
            throw new InvalidParameterSpecException("Only IvParameterSpec is supported");
        }
        this.f9988iv = (byte[]) ((IvParameterSpec) algorithmParameterSpec).getIV().clone();
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
            byte[] asn1_read_octetstring = NativeCrypto.asn1_read_octetstring(j);
            if (!NativeCrypto.asn1_read_is_empty(j)) {
                throw new IOException("Error reading ASN.1 encoding");
            }
            this.f9988iv = asn1_read_octetstring;
            NativeCrypto.asn1_read_free(j);
        } catch (Throwable th2) {
            th = th2;
            NativeCrypto.asn1_read_free(j);
            throw th;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr, String str) throws IOException {
        if (str == null || str.equals("ASN.1")) {
            engineInit(bArr);
        } else {
            if (str.equals("RAW")) {
                this.f9988iv = (byte[]) bArr.clone();
                return;
            }
            throw new IOException("Unsupported format: " + str);
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected <T extends AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> cls) throws InvalidParameterSpecException {
        if (cls != IvParameterSpec.class) {
            throw new InvalidParameterSpecException("Incompatible AlgorithmParametersSpec class: " + cls);
        }
        return new IvParameterSpec(this.f9988iv);
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded() throws IOException {
        long j = 0;
        try {
            try {
                j = NativeCrypto.asn1_write_init();
                NativeCrypto.asn1_write_octetstring(j, this.f9988iv);
                return NativeCrypto.asn1_write_finish(j);
            } catch (IOException e) {
                NativeCrypto.asn1_write_cleanup(j);
                throw e;
            }
        } finally {
            NativeCrypto.asn1_write_free(j);
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded(String str) throws IOException {
        if (str == null || str.equals("ASN.1")) {
            return engineGetEncoded();
        }
        if (str.equals("RAW")) {
            return (byte[]) this.f9988iv.clone();
        }
        throw new IOException("Unsupported format: " + str);
    }
}
