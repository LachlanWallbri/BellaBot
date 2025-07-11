package org.bouncycastle.crypto.util;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.p081x9.ECNamedCurveTable;
import org.bouncycastle.asn1.p081x9.X9ECParameters;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.sec.ECPrivateKey;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECNamedDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Strings;

/* loaded from: classes9.dex */
public class OpenSSHPrivateKeyUtil {
    static final byte[] AUTH_MAGIC = Strings.toByteArray("openssh-key-v1\u0000");

    private OpenSSHPrivateKeyUtil() {
    }

    private static boolean allIntegers(ASN1Sequence aSN1Sequence) {
        for (int i = 0; i < aSN1Sequence.size(); i++) {
            if (!(aSN1Sequence.getObjectAt(i) instanceof ASN1Integer)) {
                return false;
            }
        }
        return true;
    }

    public static byte[] encodePrivateKey(AsymmetricKeyParameter asymmetricKeyParameter) throws IOException {
        if (asymmetricKeyParameter == null) {
            throw new IllegalArgumentException("param is null");
        }
        if (!(asymmetricKeyParameter instanceof RSAPrivateCrtKeyParameters) && !(asymmetricKeyParameter instanceof ECPrivateKeyParameters)) {
            if (asymmetricKeyParameter instanceof DSAPrivateKeyParameters) {
                DSAPrivateKeyParameters dSAPrivateKeyParameters = (DSAPrivateKeyParameters) asymmetricKeyParameter;
                DSAParameters parameters = dSAPrivateKeyParameters.getParameters();
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                aSN1EncodableVector.add(new ASN1Integer(0L));
                aSN1EncodableVector.add(new ASN1Integer(parameters.getP()));
                aSN1EncodableVector.add(new ASN1Integer(parameters.getQ()));
                aSN1EncodableVector.add(new ASN1Integer(parameters.getG()));
                aSN1EncodableVector.add(new ASN1Integer(parameters.getG().modPow(dSAPrivateKeyParameters.getX(), parameters.getP())));
                aSN1EncodableVector.add(new ASN1Integer(dSAPrivateKeyParameters.getX()));
                try {
                    return new DERSequence(aSN1EncodableVector).getEncoded();
                } catch (Exception e) {
                    throw new IllegalStateException("unable to encode DSAPrivateKeyParameters " + e.getMessage());
                }
            }
            if (!(asymmetricKeyParameter instanceof Ed25519PrivateKeyParameters)) {
                throw new IllegalArgumentException("unable to convert " + asymmetricKeyParameter.getClass().getName() + " to openssh private key");
            }
            Ed25519PrivateKeyParameters ed25519PrivateKeyParameters = (Ed25519PrivateKeyParameters) asymmetricKeyParameter;
            Ed25519PublicKeyParameters generatePublicKey = ed25519PrivateKeyParameters.generatePublicKey();
            SSHBuilder sSHBuilder = new SSHBuilder();
            sSHBuilder.writeBytes(AUTH_MAGIC);
            sSHBuilder.writeString("none");
            sSHBuilder.writeString("none");
            sSHBuilder.writeString("");
            sSHBuilder.u32(1);
            sSHBuilder.writeBlock(OpenSSHPublicKeyUtil.encodePublicKey(generatePublicKey));
            SSHBuilder sSHBuilder2 = new SSHBuilder();
            int nextInt = CryptoServicesRegistrar.getSecureRandom().nextInt();
            sSHBuilder2.u32(nextInt);
            sSHBuilder2.u32(nextInt);
            sSHBuilder2.writeString("ssh-ed25519");
            byte[] encoded = generatePublicKey.getEncoded();
            sSHBuilder2.writeBlock(encoded);
            sSHBuilder2.writeBlock(Arrays.concatenate(ed25519PrivateKeyParameters.getEncoded(), encoded));
            sSHBuilder2.writeString("");
            sSHBuilder.writeBlock(sSHBuilder2.getPaddedBytes());
            return sSHBuilder.getBytes();
        }
        return PrivateKeyInfoFactory.createPrivateKeyInfo(asymmetricKeyParameter).parsePrivateKey().toASN1Primitive().getEncoded();
    }

    public static AsymmetricKeyParameter parsePrivateKeyBlob(byte[] bArr) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (bArr[0] == 48) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(bArr);
            if (aSN1Sequence.size() == 6) {
                if (allIntegers(aSN1Sequence) && ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getPositiveValue().equals(BigIntegers.ZERO)) {
                    asymmetricKeyParameter = new DSAPrivateKeyParameters(((ASN1Integer) aSN1Sequence.getObjectAt(5)).getPositiveValue(), new DSAParameters(((ASN1Integer) aSN1Sequence.getObjectAt(1)).getPositiveValue(), ((ASN1Integer) aSN1Sequence.getObjectAt(2)).getPositiveValue(), ((ASN1Integer) aSN1Sequence.getObjectAt(3)).getPositiveValue()));
                }
                asymmetricKeyParameter = null;
            } else if (aSN1Sequence.size() == 9) {
                if (allIntegers(aSN1Sequence) && ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getPositiveValue().equals(BigIntegers.ZERO)) {
                    RSAPrivateKey rSAPrivateKey = RSAPrivateKey.getInstance(aSN1Sequence);
                    asymmetricKeyParameter = new RSAPrivateCrtKeyParameters(rSAPrivateKey.getModulus(), rSAPrivateKey.getPublicExponent(), rSAPrivateKey.getPrivateExponent(), rSAPrivateKey.getPrime1(), rSAPrivateKey.getPrime2(), rSAPrivateKey.getExponent1(), rSAPrivateKey.getExponent2(), rSAPrivateKey.getCoefficient());
                }
                asymmetricKeyParameter = null;
            } else {
                if (aSN1Sequence.size() == 4 && (aSN1Sequence.getObjectAt(3) instanceof ASN1TaggedObject) && (aSN1Sequence.getObjectAt(2) instanceof ASN1TaggedObject)) {
                    ECPrivateKey eCPrivateKey = ECPrivateKey.getInstance(aSN1Sequence);
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) eCPrivateKey.getParameters();
                    X9ECParameters byOID = ECNamedCurveTable.getByOID(aSN1ObjectIdentifier);
                    asymmetricKeyParameter = new ECPrivateKeyParameters(eCPrivateKey.getKey(), new ECNamedDomainParameters(aSN1ObjectIdentifier, byOID.getCurve(), byOID.getG(), byOID.getN(), byOID.getH(), byOID.getSeed()));
                }
                asymmetricKeyParameter = null;
            }
        } else {
            SSHBuffer sSHBuffer = new SSHBuffer(AUTH_MAGIC, bArr);
            if (!"none".equals(sSHBuffer.readString())) {
                throw new IllegalStateException("encrypted keys not supported");
            }
            sSHBuffer.skipBlock();
            sSHBuffer.skipBlock();
            if (sSHBuffer.readU32() != 1) {
                throw new IllegalStateException("multiple keys not supported");
            }
            OpenSSHPublicKeyUtil.parsePublicKey(sSHBuffer.readBlock());
            byte[] readPaddedBlock = sSHBuffer.readPaddedBlock();
            if (sSHBuffer.hasRemaining()) {
                throw new IllegalArgumentException("decoded key has trailing data");
            }
            SSHBuffer sSHBuffer2 = new SSHBuffer(readPaddedBlock);
            if (sSHBuffer2.readU32() != sSHBuffer2.readU32()) {
                throw new IllegalStateException("private key check values are not the same");
            }
            String readString = sSHBuffer2.readString();
            if (!"ssh-ed25519".equals(readString)) {
                throw new IllegalStateException("can not parse private key of type " + readString);
            }
            sSHBuffer2.skipBlock();
            byte[] readBlock = sSHBuffer2.readBlock();
            if (readBlock.length != 64) {
                throw new IllegalStateException("private key value of wrong length");
            }
            Ed25519PrivateKeyParameters ed25519PrivateKeyParameters = new Ed25519PrivateKeyParameters(readBlock, 0);
            sSHBuffer2.skipBlock();
            if (sSHBuffer2.hasRemaining()) {
                throw new IllegalArgumentException("private key block has trailing data");
            }
            asymmetricKeyParameter = ed25519PrivateKeyParameters;
        }
        if (asymmetricKeyParameter != null) {
            return asymmetricKeyParameter;
        }
        throw new IllegalArgumentException("unable to parse key");
    }
}
