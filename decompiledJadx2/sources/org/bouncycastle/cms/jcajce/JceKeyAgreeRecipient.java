package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.ecc.ECCCMSSharedInfo;
import org.bouncycastle.asn1.cms.ecc.MQVuserKeyingMaterial;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.Gost2814789EncryptedKey;
import org.bouncycastle.asn1.cryptopro.Gost2814789KeyWrapParameters;
import org.bouncycastle.asn1.p081x9.X9ObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyAgreeRecipient;
import org.bouncycastle.jcajce.spec.GOST28147WrapParameterSpec;
import org.bouncycastle.jcajce.spec.MQVParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public abstract class JceKeyAgreeRecipient implements KeyAgreeRecipient {
    private static KeyMaterialGenerator ecc_cms_Generator;
    private static KeyMaterialGenerator old_ecc_cms_Generator;
    private static final Set possibleOldMessages = new HashSet();
    private PrivateKey recipientKey;
    protected EnvelopedDataHelper helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    protected EnvelopedDataHelper contentHelper = this.helper;
    private SecretKeySizeProvider keySizeProvider = new DefaultSecretKeySizeProvider();

    static {
        possibleOldMessages.add(X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme);
        possibleOldMessages.add(X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme);
        old_ecc_cms_Generator = new KeyMaterialGenerator() { // from class: org.bouncycastle.cms.jcajce.JceKeyAgreeRecipient.1
            @Override // org.bouncycastle.cms.jcajce.KeyMaterialGenerator
            public byte[] generateKDFMaterial(AlgorithmIdentifier algorithmIdentifier, int i, byte[] bArr) {
                try {
                    return new ECCCMSSharedInfo(new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE), bArr, Pack.intToBigEndian(i)).getEncoded(ASN1Encoding.DER);
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to create KDF material: " + e);
                }
            }
        };
        ecc_cms_Generator = new RFC5753KeyMaterialGenerator();
    }

    public JceKeyAgreeRecipient(PrivateKey privateKey) {
        this.recipientKey = CMSUtils.cleanPrivateKey(privateKey);
    }

    private SecretKey calculateAgreedWrapKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, PublicKey publicKey, ASN1OctetString aSN1OctetString, PrivateKey privateKey, KeyMaterialGenerator keyMaterialGenerator) throws CMSException, GeneralSecurityException, IOException {
        PrivateKey cleanPrivateKey = CMSUtils.cleanPrivateKey(privateKey);
        UserKeyingMaterialSpec userKeyingMaterialSpec = null;
        userKeyingMaterialSpec = null;
        if (CMSUtils.isMQV(algorithmIdentifier.getAlgorithm())) {
            MQVuserKeyingMaterial mQVuserKeyingMaterial = MQVuserKeyingMaterial.getInstance(aSN1OctetString.getOctets());
            PublicKey generatePublic = this.helper.createKeyFactory(algorithmIdentifier.getAlgorithm()).generatePublic(new X509EncodedKeySpec(new SubjectPublicKeyInfo(getPrivateKeyAlgorithmIdentifier(), mQVuserKeyingMaterial.getEphemeralPublicKey().getPublicKey().getBytes()).getEncoded()));
            KeyAgreement createKeyAgreement = this.helper.createKeyAgreement(algorithmIdentifier.getAlgorithm());
            byte[] octets = mQVuserKeyingMaterial.getAddedukm() != null ? mQVuserKeyingMaterial.getAddedukm().getOctets() : null;
            KeyMaterialGenerator keyMaterialGenerator2 = old_ecc_cms_Generator;
            if (keyMaterialGenerator == keyMaterialGenerator2) {
                octets = keyMaterialGenerator2.generateKDFMaterial(algorithmIdentifier2, this.keySizeProvider.getKeySize(algorithmIdentifier2), octets);
            }
            createKeyAgreement.init(cleanPrivateKey, new MQVParameterSpec(cleanPrivateKey, generatePublic, octets));
            createKeyAgreement.doPhase(publicKey, true);
            return createKeyAgreement.generateSecret(algorithmIdentifier2.getAlgorithm().getId());
        }
        KeyAgreement createKeyAgreement2 = this.helper.createKeyAgreement(algorithmIdentifier.getAlgorithm());
        if (CMSUtils.isEC(algorithmIdentifier.getAlgorithm())) {
            int keySize = this.keySizeProvider.getKeySize(algorithmIdentifier2);
            userKeyingMaterialSpec = aSN1OctetString != null ? new UserKeyingMaterialSpec(keyMaterialGenerator.generateKDFMaterial(algorithmIdentifier2, keySize, aSN1OctetString.getOctets())) : new UserKeyingMaterialSpec(keyMaterialGenerator.generateKDFMaterial(algorithmIdentifier2, keySize, null));
        } else if (CMSUtils.isRFC2631(algorithmIdentifier.getAlgorithm())) {
            if (aSN1OctetString != null) {
                userKeyingMaterialSpec = new UserKeyingMaterialSpec(aSN1OctetString.getOctets());
            }
        } else {
            if (!CMSUtils.isGOST(algorithmIdentifier.getAlgorithm())) {
                throw new CMSException("Unknown key agreement algorithm: " + algorithmIdentifier.getAlgorithm());
            }
            if (aSN1OctetString != null) {
                userKeyingMaterialSpec = new UserKeyingMaterialSpec(aSN1OctetString.getOctets());
            }
        }
        createKeyAgreement2.init(cleanPrivateKey, userKeyingMaterialSpec);
        createKeyAgreement2.doPhase(publicKey, true);
        return createKeyAgreement2.generateSecret(algorithmIdentifier2.getAlgorithm().getId());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1OctetString aSN1OctetString, byte[] bArr) throws CMSException {
        try {
            try {
                AlgorithmIdentifier algorithmIdentifier3 = AlgorithmIdentifier.getInstance(algorithmIdentifier.getParameters());
                PublicKey generatePublic = this.helper.createKeyFactory(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
                try {
                    SecretKey calculateAgreedWrapKey = calculateAgreedWrapKey(algorithmIdentifier, algorithmIdentifier3, generatePublic, aSN1OctetString, this.recipientKey, ecc_cms_Generator);
                    if (!algorithmIdentifier3.getAlgorithm().equals((ASN1Primitive) CryptoProObjectIdentifiers.id_Gost28147_89_None_KeyWrap) && !algorithmIdentifier3.getAlgorithm().equals((ASN1Primitive) CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap)) {
                        return unwrapSessionKey(algorithmIdentifier3.getAlgorithm(), calculateAgreedWrapKey, algorithmIdentifier2.getAlgorithm(), bArr);
                    }
                    Gost2814789EncryptedKey gost2814789EncryptedKey = Gost2814789EncryptedKey.getInstance(bArr);
                    Gost2814789KeyWrapParameters gost2814789KeyWrapParameters = Gost2814789KeyWrapParameters.getInstance(algorithmIdentifier3.getParameters());
                    Cipher createCipher = this.helper.createCipher(algorithmIdentifier3.getAlgorithm());
                    createCipher.init(4, calculateAgreedWrapKey, new GOST28147WrapParameterSpec(gost2814789KeyWrapParameters.getEncryptionParamSet(), aSN1OctetString.getOctets()));
                    return createCipher.unwrap(Arrays.concatenate(gost2814789EncryptedKey.getEncryptedKey(), gost2814789EncryptedKey.getMacKey()), this.helper.getBaseCipherName(algorithmIdentifier2.getAlgorithm()), 3);
                } catch (InvalidKeyException e) {
                    if (!possibleOldMessages.contains(algorithmIdentifier.getAlgorithm())) {
                        throw e;
                    }
                    return unwrapSessionKey(algorithmIdentifier3.getAlgorithm(), calculateAgreedWrapKey(algorithmIdentifier, algorithmIdentifier3, generatePublic, aSN1OctetString, this.recipientKey, old_ecc_cms_Generator), algorithmIdentifier2.getAlgorithm(), bArr);
                }
            } catch (NoSuchAlgorithmException e2) {
                throw new CMSException("can't find algorithm.", e2);
            } catch (InvalidKeySpecException e3) {
                throw new CMSException("originator key spec invalid.", e3);
            } catch (NoSuchPaddingException e4) {
                throw new CMSException("required padding not supported.", e4);
            } catch (Exception e5) {
                throw new CMSException("originator key invalid.", e5);
            }
        } catch (InvalidKeyException e6) {
            throw new CMSException("key invalid in message.", e6);
        }
    }

    @Override // org.bouncycastle.cms.KeyAgreeRecipient
    public AlgorithmIdentifier getPrivateKeyAlgorithmIdentifier() {
        return PrivateKeyInfo.getInstance(this.recipientKey.getEncoded()).getPrivateKeyAlgorithm();
    }

    public JceKeyAgreeRecipient setContentProvider(String str) {
        this.contentHelper = CMSUtils.createContentHelper(str);
        return this;
    }

    public JceKeyAgreeRecipient setContentProvider(Provider provider) {
        this.contentHelper = CMSUtils.createContentHelper(provider);
        return this;
    }

    public JceKeyAgreeRecipient setProvider(String str) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        this.contentHelper = this.helper;
        return this;
    }

    public JceKeyAgreeRecipient setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        this.contentHelper = this.helper;
        return this;
    }

    protected Key unwrapSessionKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, SecretKey secretKey, ASN1ObjectIdentifier aSN1ObjectIdentifier2, byte[] bArr) throws CMSException, InvalidKeyException, NoSuchAlgorithmException {
        Cipher createCipher = this.helper.createCipher(aSN1ObjectIdentifier);
        createCipher.init(4, secretKey);
        return createCipher.unwrap(bArr, this.helper.getBaseCipherName(aSN1ObjectIdentifier2), 3);
    }
}
