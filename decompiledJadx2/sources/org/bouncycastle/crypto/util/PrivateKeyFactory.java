package org.bouncycastle.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.bouncycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.oiw.ElGamalParameter;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.p081x9.ECNamedCurveTable;
import org.bouncycastle.asn1.p081x9.X962Parameters;
import org.bouncycastle.asn1.p081x9.X9ECParameters;
import org.bouncycastle.asn1.p081x9.X9ObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.DHParameter;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.sec.ECPrivateKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECGOST3410Parameters;
import org.bouncycastle.crypto.params.ECNamedDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed448PrivateKeyParameters;
import org.bouncycastle.crypto.params.ElGamalParameters;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;
import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class PrivateKeyFactory {
    public static AsymmetricKeyParameter createKey(InputStream inputStream) throws IOException {
        return createKey(PrivateKeyInfo.getInstance(new ASN1InputStream(inputStream).readObject()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02e7  */
    /* JADX WARN: Type inference failed for: r2v10, types: [org.bouncycastle.crypto.params.ECDomainParameters] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r9v2, types: [org.bouncycastle.crypto.params.ECGOST3410Parameters] */
    /* JADX WARN: Type inference failed for: r9v4, types: [org.bouncycastle.crypto.params.ECGOST3410Parameters] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static AsymmetricKeyParameter createKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        Object eCGOST3410Parameters;
        ASN1Encodable parsePrivateKey;
        BigInteger key;
        DSAParameters dSAParameters;
        ?? r2;
        ECDomainParameters eCDomainParameters;
        AlgorithmIdentifier privateKeyAlgorithm = privateKeyInfo.getPrivateKeyAlgorithm();
        ASN1ObjectIdentifier algorithm = privateKeyAlgorithm.getAlgorithm();
        if (algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.rsaEncryption) || algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.id_RSASSA_PSS) || algorithm.equals((ASN1Primitive) X509ObjectIdentifiers.id_ea_rsa)) {
            RSAPrivateKey rSAPrivateKey = RSAPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
            return new RSAPrivateCrtKeyParameters(rSAPrivateKey.getModulus(), rSAPrivateKey.getPublicExponent(), rSAPrivateKey.getPrivateExponent(), rSAPrivateKey.getPrime1(), rSAPrivateKey.getPrime2(), rSAPrivateKey.getExponent1(), rSAPrivateKey.getExponent2(), rSAPrivateKey.getCoefficient());
        }
        DSAParameters dSAParameters2 = null;
        if (algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.dhKeyAgreement)) {
            DHParameter dHParameter = DHParameter.getInstance(privateKeyAlgorithm.getParameters());
            ASN1Integer aSN1Integer = (ASN1Integer) privateKeyInfo.parsePrivateKey();
            BigInteger l = dHParameter.getL();
            return new DHPrivateKeyParameters(aSN1Integer.getValue(), new DHParameters(dHParameter.getP(), dHParameter.getG(), null, l != null ? l.intValue() : 0));
        }
        if (algorithm.equals((ASN1Primitive) OIWObjectIdentifiers.elGamalAlgorithm)) {
            ElGamalParameter elGamalParameter = ElGamalParameter.getInstance(privateKeyAlgorithm.getParameters());
            return new ElGamalPrivateKeyParameters(((ASN1Integer) privateKeyInfo.parsePrivateKey()).getValue(), new ElGamalParameters(elGamalParameter.getP(), elGamalParameter.getG()));
        }
        if (algorithm.equals((ASN1Primitive) X9ObjectIdentifiers.id_dsa)) {
            ASN1Integer aSN1Integer2 = (ASN1Integer) privateKeyInfo.parsePrivateKey();
            ASN1Encodable parameters = privateKeyAlgorithm.getParameters();
            if (parameters != null) {
                DSAParameter dSAParameter = DSAParameter.getInstance(parameters.toASN1Primitive());
                dSAParameters2 = new DSAParameters(dSAParameter.getP(), dSAParameter.getQ(), dSAParameter.getG());
            }
            return new DSAPrivateKeyParameters(aSN1Integer2.getValue(), dSAParameters2);
        }
        if (algorithm.equals((ASN1Primitive) X9ObjectIdentifiers.id_ecPublicKey)) {
            X962Parameters x962Parameters = X962Parameters.getInstance(privateKeyAlgorithm.getParameters());
            boolean isNamedCurve = x962Parameters.isNamedCurve();
            ASN1Primitive parameters2 = x962Parameters.getParameters();
            if (isNamedCurve) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) parameters2;
                X9ECParameters byOID = CustomNamedCurves.getByOID(aSN1ObjectIdentifier);
                if (byOID == null) {
                    byOID = ECNamedCurveTable.getByOID(aSN1ObjectIdentifier);
                }
                eCDomainParameters = new ECNamedDomainParameters(aSN1ObjectIdentifier, byOID.getCurve(), byOID.getG(), byOID.getN(), byOID.getH(), byOID.getSeed());
            } else {
                X9ECParameters x9ECParameters = X9ECParameters.getInstance(parameters2);
                eCDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
            }
            return new ECPrivateKeyParameters(ECPrivateKey.getInstance(privateKeyInfo.parsePrivateKey()).getKey(), eCDomainParameters);
        }
        if (algorithm.equals((ASN1Primitive) EdECObjectIdentifiers.id_X25519)) {
            return new X25519PrivateKeyParameters(getRawKey(privateKeyInfo, 32), 0);
        }
        if (algorithm.equals((ASN1Primitive) EdECObjectIdentifiers.id_X448)) {
            return new X448PrivateKeyParameters(getRawKey(privateKeyInfo, 56), 0);
        }
        if (algorithm.equals((ASN1Primitive) EdECObjectIdentifiers.id_Ed25519)) {
            return new Ed25519PrivateKeyParameters(getRawKey(privateKeyInfo, 32), 0);
        }
        if (algorithm.equals((ASN1Primitive) EdECObjectIdentifiers.id_Ed448)) {
            return new Ed448PrivateKeyParameters(getRawKey(privateKeyInfo, 57), 0);
        }
        if (!algorithm.equals((ASN1Primitive) CryptoProObjectIdentifiers.gostR3410_2001) && !algorithm.equals((ASN1Primitive) RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512) && !algorithm.equals((ASN1Primitive) RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256)) {
            throw new RuntimeException("algorithm identifier in private key not recognised");
        }
        GOST3410PublicKeyAlgParameters gOST3410PublicKeyAlgParameters = GOST3410PublicKeyAlgParameters.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
        ASN1Primitive aSN1Primitive = privateKeyInfo.getPrivateKeyAlgorithm().getParameters().toASN1Primitive();
        if ((aSN1Primitive instanceof ASN1Sequence) && (ASN1Sequence.getInstance(aSN1Primitive).size() == 2 || ASN1Sequence.getInstance(aSN1Primitive).size() == 3)) {
            ECGOST3410Parameters eCGOST3410Parameters2 = new ECGOST3410Parameters(new ECNamedDomainParameters(gOST3410PublicKeyAlgParameters.getPublicKeyParamSet(), ECGOST3410NamedCurves.getByOID(gOST3410PublicKeyAlgParameters.getPublicKeyParamSet())), gOST3410PublicKeyAlgParameters.getPublicKeyParamSet(), gOST3410PublicKeyAlgParameters.getDigestParamSet(), gOST3410PublicKeyAlgParameters.getEncryptionParamSet());
            ASN1Encodable parsePrivateKey2 = privateKeyInfo.parsePrivateKey();
            if (parsePrivateKey2 instanceof ASN1Integer) {
                key = ASN1Integer.getInstance(parsePrivateKey2).getPositiveValue();
                r2 = eCGOST3410Parameters2;
            } else {
                key = new BigInteger(1, Arrays.reverse(ASN1OctetString.getInstance(parsePrivateKey2).getOctets()));
                r2 = eCGOST3410Parameters2;
            }
        } else {
            X962Parameters x962Parameters2 = X962Parameters.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
            if (x962Parameters2.isNamedCurve()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier2 = ASN1ObjectIdentifier.getInstance(x962Parameters2.getParameters());
                X9ECParameters byOID2 = ECNamedCurveTable.getByOID(aSN1ObjectIdentifier2);
                if (byOID2 == null) {
                    ECDomainParameters byOID3 = ECGOST3410NamedCurves.getByOID(aSN1ObjectIdentifier2);
                    dSAParameters = new ECGOST3410Parameters(new ECNamedDomainParameters(aSN1ObjectIdentifier2, byOID3.getCurve(), byOID3.getG(), byOID3.getN(), byOID3.getH(), byOID3.getSeed()), gOST3410PublicKeyAlgParameters.getPublicKeyParamSet(), gOST3410PublicKeyAlgParameters.getDigestParamSet(), gOST3410PublicKeyAlgParameters.getEncryptionParamSet());
                } else {
                    dSAParameters = new ECGOST3410Parameters(new ECNamedDomainParameters(aSN1ObjectIdentifier2, byOID2.getCurve(), byOID2.getG(), byOID2.getN(), byOID2.getH(), byOID2.getSeed()), gOST3410PublicKeyAlgParameters.getPublicKeyParamSet(), gOST3410PublicKeyAlgParameters.getDigestParamSet(), gOST3410PublicKeyAlgParameters.getEncryptionParamSet());
                }
                dSAParameters2 = dSAParameters;
            } else if (!x962Parameters2.isImplicitlyCA()) {
                X9ECParameters x9ECParameters2 = X9ECParameters.getInstance(x962Parameters2.getParameters());
                eCGOST3410Parameters = new ECGOST3410Parameters(new ECNamedDomainParameters(algorithm, x9ECParameters2.getCurve(), x9ECParameters2.getG(), x9ECParameters2.getN(), x9ECParameters2.getH(), x9ECParameters2.getSeed()), gOST3410PublicKeyAlgParameters.getPublicKeyParamSet(), gOST3410PublicKeyAlgParameters.getDigestParamSet(), gOST3410PublicKeyAlgParameters.getEncryptionParamSet());
                parsePrivateKey = privateKeyInfo.parsePrivateKey();
                if (parsePrivateKey instanceof ASN1Integer) {
                    key = ECPrivateKey.getInstance(parsePrivateKey).getKey();
                    r2 = eCGOST3410Parameters;
                } else {
                    key = ASN1Integer.getInstance(parsePrivateKey).getValue();
                    r2 = eCGOST3410Parameters;
                }
            }
            eCGOST3410Parameters = dSAParameters2;
            parsePrivateKey = privateKeyInfo.parsePrivateKey();
            if (parsePrivateKey instanceof ASN1Integer) {
            }
        }
        return new ECPrivateKeyParameters(key, new ECGOST3410Parameters(r2, gOST3410PublicKeyAlgParameters.getPublicKeyParamSet(), gOST3410PublicKeyAlgParameters.getDigestParamSet(), gOST3410PublicKeyAlgParameters.getEncryptionParamSet()));
    }

    public static AsymmetricKeyParameter createKey(byte[] bArr) throws IOException {
        return createKey(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(bArr)));
    }

    private static byte[] getRawKey(PrivateKeyInfo privateKeyInfo, int i) throws IOException {
        byte[] octets = ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets();
        if (i == octets.length) {
            return octets;
        }
        throw new RuntimeException("private key encoding has incorrect length");
    }
}
