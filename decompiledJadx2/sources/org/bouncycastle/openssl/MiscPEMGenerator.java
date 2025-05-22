package org.bouncycastle.openssl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.p081x9.X9ObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.pem.PemGenerationException;
import org.bouncycastle.util.io.pem.PemHeader;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemObjectGenerator;

/* loaded from: classes9.dex */
public class MiscPEMGenerator implements PemObjectGenerator {
    private static final ASN1ObjectIdentifier[] dsaOids = {X9ObjectIdentifiers.id_dsa, OIWObjectIdentifiers.dsaWithSHA1};
    private static final byte[] hexEncodingTable = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 65, 66, 67, 68, 69, 70};
    private final PEMEncryptor encryptor;
    private final Object obj;

    public MiscPEMGenerator(Object obj) {
        this.obj = obj;
        this.encryptor = null;
    }

    public MiscPEMGenerator(Object obj, PEMEncryptor pEMEncryptor) {
        this.obj = obj;
        this.encryptor = pEMEncryptor;
    }

    private PemObject createPemObject(Object obj) throws IOException {
        byte[] encoded;
        String str;
        if (obj instanceof PemObject) {
            return (PemObject) obj;
        }
        if (obj instanceof PemObjectGenerator) {
            return ((PemObjectGenerator) obj).generate();
        }
        if (obj instanceof X509CertificateHolder) {
            encoded = ((X509CertificateHolder) obj).getEncoded();
            str = "CERTIFICATE";
        } else if (obj instanceof X509CRLHolder) {
            encoded = ((X509CRLHolder) obj).getEncoded();
            str = "X509 CRL";
        } else if (obj instanceof X509TrustedCertificateBlock) {
            encoded = ((X509TrustedCertificateBlock) obj).getEncoded();
            str = "TRUSTED CERTIFICATE";
        } else if (obj instanceof PrivateKeyInfo) {
            PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) obj;
            ASN1ObjectIdentifier algorithm = privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm();
            if (algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.rsaEncryption)) {
                encoded = privateKeyInfo.parsePrivateKey().toASN1Primitive().getEncoded();
                str = "RSA PRIVATE KEY";
            } else if (algorithm.equals((ASN1Primitive) dsaOids[0]) || algorithm.equals((ASN1Primitive) dsaOids[1])) {
                DSAParameter dSAParameter = DSAParameter.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                aSN1EncodableVector.add(new ASN1Integer(0L));
                aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getP()));
                aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getQ()));
                aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getG()));
                BigInteger value = ASN1Integer.getInstance(privateKeyInfo.parsePrivateKey()).getValue();
                aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getG().modPow(value, dSAParameter.getP())));
                aSN1EncodableVector.add(new ASN1Integer(value));
                encoded = new DERSequence(aSN1EncodableVector).getEncoded();
                str = "DSA PRIVATE KEY";
            } else {
                if (!algorithm.equals((ASN1Primitive) X9ObjectIdentifiers.id_ecPublicKey)) {
                    throw new IOException("Cannot identify private key");
                }
                encoded = privateKeyInfo.parsePrivateKey().toASN1Primitive().getEncoded();
                str = "EC PRIVATE KEY";
            }
        } else if (obj instanceof SubjectPublicKeyInfo) {
            encoded = ((SubjectPublicKeyInfo) obj).getEncoded();
            str = "PUBLIC KEY";
        } else if (obj instanceof X509AttributeCertificateHolder) {
            encoded = ((X509AttributeCertificateHolder) obj).getEncoded();
            str = "ATTRIBUTE CERTIFICATE";
        } else if (obj instanceof PKCS10CertificationRequest) {
            encoded = ((PKCS10CertificationRequest) obj).getEncoded();
            str = "CERTIFICATE REQUEST";
        } else if (obj instanceof PKCS8EncryptedPrivateKeyInfo) {
            encoded = ((PKCS8EncryptedPrivateKeyInfo) obj).getEncoded();
            str = "ENCRYPTED PRIVATE KEY";
        } else {
            if (!(obj instanceof ContentInfo)) {
                throw new PemGenerationException("unknown object passed - can't encode.");
            }
            encoded = ((ContentInfo) obj).getEncoded();
            str = "PKCS7";
        }
        PEMEncryptor pEMEncryptor = this.encryptor;
        if (pEMEncryptor == null) {
            return new PemObject(str, encoded);
        }
        String upperCase = Strings.toUpperCase(pEMEncryptor.getAlgorithm());
        if (upperCase.equals("DESEDE")) {
            upperCase = "DES-EDE3-CBC";
        }
        byte[] iv = this.encryptor.getIV();
        byte[] encrypt = this.encryptor.encrypt(encoded);
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new PemHeader("Proc-Type", "4,ENCRYPTED"));
        arrayList.add(new PemHeader("DEK-Info", upperCase + "," + getHexEncoded(iv)));
        return new PemObject(str, arrayList, encrypt);
    }

    private String getHexEncoded(byte[] bArr) throws IOException {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i != bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            byte[] bArr2 = hexEncodingTable;
            cArr[i3] = (char) bArr2[i2 >>> 4];
            cArr[i3 + 1] = (char) bArr2[i2 & 15];
        }
        return new String(cArr);
    }

    @Override // org.bouncycastle.util.io.pem.PemObjectGenerator
    public PemObject generate() throws PemGenerationException {
        try {
            return createPemObject(this.obj);
        } catch (IOException e) {
            throw new PemGenerationException("encoding exception: " + e.getMessage(), e);
        }
    }
}
