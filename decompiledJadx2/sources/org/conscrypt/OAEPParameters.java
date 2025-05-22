package org.conscrypt;

import java.io.IOException;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class OAEPParameters extends AlgorithmParametersSpi {
    private static final String MGF1_OID = "1.2.840.113549.1.1.8";
    private static final String PSPECIFIED_OID = "1.2.840.113549.1.1.9";
    private OAEPParameterSpec spec = OAEPParameterSpec.DEFAULT;
    private static final Map<String, String> OID_TO_NAME = new HashMap();
    private static final Map<String, String> NAME_TO_OID = new HashMap();

    @Override // java.security.AlgorithmParametersSpi
    protected String engineToString() {
        return "Conscrypt OAEP AlgorithmParameters";
    }

    static {
        OID_TO_NAME.put("1.3.14.3.2.26", "SHA-1");
        OID_TO_NAME.put("2.16.840.1.101.3.4.2.4", "SHA-224");
        OID_TO_NAME.put("2.16.840.1.101.3.4.2.1", "SHA-256");
        OID_TO_NAME.put("2.16.840.1.101.3.4.2.2", "SHA-384");
        OID_TO_NAME.put("2.16.840.1.101.3.4.2.3", "SHA-512");
        for (Map.Entry<String, String> entry : OID_TO_NAME.entrySet()) {
            NAME_TO_OID.put(entry.getValue(), entry.getKey());
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (algorithmParameterSpec instanceof OAEPParameterSpec) {
            this.spec = (OAEPParameterSpec) algorithmParameterSpec;
            return;
        }
        throw new InvalidParameterSpecException("Only OAEPParameterSpec is supported");
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr) throws IOException {
        long j;
        long j2;
        long j3;
        try {
            j = NativeCrypto.asn1_read_init(bArr);
            try {
                j2 = NativeCrypto.asn1_read_sequence(j);
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
            PSource.PSpecified pSpecified = PSource.PSpecified.DEFAULT;
            String readHash = readHash(j2);
            String readMgfHash = readMgfHash(j2);
            if (NativeCrypto.asn1_read_next_tag_is(j2, 2)) {
                try {
                    j3 = NativeCrypto.asn1_read_tagged(j2);
                } catch (Throwable th3) {
                    th = th3;
                    j3 = 0;
                }
                try {
                    long asn1_read_sequence = NativeCrypto.asn1_read_sequence(j3);
                    if (!NativeCrypto.asn1_read_oid(asn1_read_sequence).equals(PSPECIFIED_OID)) {
                        throw new IOException("Error reading ASN.1 encoding");
                    }
                    pSpecified = new PSource.PSpecified(NativeCrypto.asn1_read_octetstring(asn1_read_sequence));
                    if (!NativeCrypto.asn1_read_is_empty(asn1_read_sequence)) {
                        throw new IOException("Error reading ASN.1 encoding");
                    }
                    NativeCrypto.asn1_read_free(asn1_read_sequence);
                    NativeCrypto.asn1_read_free(j3);
                } catch (Throwable th4) {
                    th = th4;
                    NativeCrypto.asn1_read_free(0L);
                    NativeCrypto.asn1_read_free(j3);
                    throw th;
                }
            }
            if (!NativeCrypto.asn1_read_is_empty(j2) || !NativeCrypto.asn1_read_is_empty(j)) {
                throw new IOException("Error reading ASN.1 encoding");
            }
            this.spec = new OAEPParameterSpec(readHash, "MGF1", new MGF1ParameterSpec(readMgfHash), pSpecified);
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
        if (str == null || str.equals("ASN.1")) {
            engineInit(bArr);
            return;
        }
        throw new IOException("Unsupported format: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String readHash(long j) throws IOException {
        if (!NativeCrypto.asn1_read_next_tag_is(j, 0)) {
            return "SHA-1";
        }
        long j2 = 0;
        try {
            j2 = NativeCrypto.asn1_read_tagged(j);
            return getHashName(j2);
        } finally {
            NativeCrypto.asn1_read_free(j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String readMgfHash(long j) throws IOException {
        long j2;
        if (!NativeCrypto.asn1_read_next_tag_is(j, 1)) {
            return "SHA-1";
        }
        try {
            j2 = NativeCrypto.asn1_read_tagged(j);
        } catch (Throwable th) {
            th = th;
            j2 = 0;
        }
        try {
            long asn1_read_sequence = NativeCrypto.asn1_read_sequence(j2);
            if (!NativeCrypto.asn1_read_oid(asn1_read_sequence).equals(MGF1_OID)) {
                throw new IOException("Error reading ASN.1 encoding");
            }
            String hashName = getHashName(asn1_read_sequence);
            if (!NativeCrypto.asn1_read_is_empty(asn1_read_sequence)) {
                throw new IOException("Error reading ASN.1 encoding");
            }
            NativeCrypto.asn1_read_free(asn1_read_sequence);
            NativeCrypto.asn1_read_free(j2);
            return hashName;
        } catch (Throwable th2) {
            th = th2;
            NativeCrypto.asn1_read_free(0L);
            NativeCrypto.asn1_read_free(j2);
            throw th;
        }
    }

    private static String getHashName(long j) throws IOException {
        long j2;
        try {
            j2 = NativeCrypto.asn1_read_sequence(j);
        } catch (Throwable th) {
            th = th;
            j2 = 0;
        }
        try {
            String asn1_read_oid = NativeCrypto.asn1_read_oid(j2);
            if (!NativeCrypto.asn1_read_is_empty(j2)) {
                NativeCrypto.asn1_read_null(j2);
            }
            if (!NativeCrypto.asn1_read_is_empty(j2) || !OID_TO_NAME.containsKey(asn1_read_oid)) {
                throw new IOException("Error reading ASN.1 encoding");
            }
            String str = OID_TO_NAME.get(asn1_read_oid);
            NativeCrypto.asn1_read_free(j2);
            return str;
        } catch (Throwable th2) {
            th = th2;
            NativeCrypto.asn1_read_free(j2);
            throw th;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected <T extends AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> cls) throws InvalidParameterSpecException {
        if (cls != null && cls == OAEPParameterSpec.class) {
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
        long j3;
        long j4 = 0;
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
                writeHashAndMgfHash(asn1_write_sequence, this.spec.getDigestAlgorithm(), (MGF1ParameterSpec) this.spec.getMGFParameters());
                PSource.PSpecified pSpecified = (PSource.PSpecified) this.spec.getPSource();
                if (pSpecified.getValue().length != 0) {
                    try {
                        j3 = NativeCrypto.asn1_write_tag(asn1_write_sequence, 2);
                    } catch (Throwable th4) {
                        th = th4;
                        j3 = 0;
                    }
                    try {
                        j4 = writeAlgorithmIdentifier(j3, PSPECIFIED_OID);
                        NativeCrypto.asn1_write_octetstring(j4, pSpecified.getValue());
                        NativeCrypto.asn1_write_flush(asn1_write_sequence);
                        NativeCrypto.asn1_write_free(j4);
                        NativeCrypto.asn1_write_free(j3);
                    } catch (Throwable th5) {
                        th = th5;
                        NativeCrypto.asn1_write_flush(asn1_write_sequence);
                        NativeCrypto.asn1_write_free(j4);
                        NativeCrypto.asn1_write_free(j3);
                        throw th;
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
        } catch (Throwable th6) {
            th = th6;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded(String str) throws IOException {
        if (str == null || str.equals("ASN.1")) {
            return engineGetEncoded();
        }
        throw new IOException("Unsupported format: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeHashAndMgfHash(long j, String str, MGF1ParameterSpec mGF1ParameterSpec) throws IOException {
        long j2;
        long j3;
        long j4;
        long writeAlgorithmIdentifier;
        long j5 = 0;
        if (!str.equals("SHA-1")) {
            try {
                j4 = NativeCrypto.asn1_write_tag(j, 0);
                try {
                    writeAlgorithmIdentifier = writeAlgorithmIdentifier(j4, NAME_TO_OID.get(str));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                j4 = 0;
            }
            try {
                NativeCrypto.asn1_write_null(writeAlgorithmIdentifier);
                NativeCrypto.asn1_write_flush(j);
                NativeCrypto.asn1_write_free(writeAlgorithmIdentifier);
                NativeCrypto.asn1_write_free(j4);
            } catch (Throwable th3) {
                th = th3;
                j5 = writeAlgorithmIdentifier;
                NativeCrypto.asn1_write_flush(j);
                NativeCrypto.asn1_write_free(j5);
                NativeCrypto.asn1_write_free(j4);
                throw th;
            }
        }
        if (mGF1ParameterSpec.getDigestAlgorithm().equals("SHA-1")) {
            return;
        }
        try {
            j2 = NativeCrypto.asn1_write_tag(j, 1);
            try {
                j3 = writeAlgorithmIdentifier(j2, MGF1_OID);
                try {
                    j5 = writeAlgorithmIdentifier(j3, NAME_TO_OID.get(mGF1ParameterSpec.getDigestAlgorithm()));
                    NativeCrypto.asn1_write_null(j5);
                    NativeCrypto.asn1_write_flush(j);
                    NativeCrypto.asn1_write_free(j5);
                    NativeCrypto.asn1_write_free(j3);
                    NativeCrypto.asn1_write_free(j2);
                } catch (Throwable th4) {
                    th = th4;
                    NativeCrypto.asn1_write_flush(j);
                    NativeCrypto.asn1_write_free(j5);
                    NativeCrypto.asn1_write_free(j3);
                    NativeCrypto.asn1_write_free(j2);
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                j3 = 0;
            }
        } catch (Throwable th6) {
            th = th6;
            j2 = 0;
            j3 = 0;
        }
    }

    private static long writeAlgorithmIdentifier(long j, String str) throws IOException {
        long j2;
        try {
            j2 = NativeCrypto.asn1_write_sequence(j);
            try {
                NativeCrypto.asn1_write_oid(j2, str);
                return j2;
            } catch (IOException e) {
                e = e;
                NativeCrypto.asn1_write_free(j2);
                throw e;
            }
        } catch (IOException e2) {
            e = e2;
            j2 = 0;
        }
    }
}
