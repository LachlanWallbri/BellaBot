package org.bouncycastle.jcajce.provider.asymmetric.p085dh;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyEncoder;
import org.bouncycastle.crypto.agreement.DHBasicAgreement;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.engines.IESEngine;
import org.bouncycastle.crypto.generators.DHKeyPairGenerator;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHKeyGenerationParameters;
import org.bouncycastle.crypto.params.DHKeyParameters;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.parsers.DHIESPublicKeyParser;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.util.DHUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.IESUtil;
import org.bouncycastle.jcajce.provider.util.BadBlockException;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.IESKey;
import org.bouncycastle.jce.spec.IESParameterSpec;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Strings;

/* loaded from: classes9.dex */
public class IESCipher extends CipherSpi {
    private ByteArrayOutputStream buffer;
    private boolean dhaesMode;
    private IESEngine engine;
    private AlgorithmParameters engineParam;
    private IESParameterSpec engineSpec;
    private final JcaJceHelper helper;
    private final int ivLength;
    private AsymmetricKeyParameter key;
    private AsymmetricKeyParameter otherKeyParameter;
    private SecureRandom random;
    private int state;

    /* loaded from: classes9.dex */
    public static class IES extends IESCipher {
        public IES() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()), new HMac(DigestFactory.createSHA1())));
        }
    }

    /* loaded from: classes9.dex */
    public static class IESwithAESCBC extends IESCipher {
        public IESwithAESCBC() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()), new HMac(DigestFactory.createSHA1()), new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()))), 16);
        }
    }

    /* loaded from: classes9.dex */
    public static class IESwithDESedeCBC extends IESCipher {
        public IESwithDESedeCBC() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()), new HMac(DigestFactory.createSHA1()), new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()))), 8);
        }
    }

    public IESCipher(IESEngine iESEngine) {
        this.helper = new BCJcaJceHelper();
        this.state = -1;
        this.buffer = new ByteArrayOutputStream();
        this.engineParam = null;
        this.engineSpec = null;
        this.dhaesMode = false;
        this.otherKeyParameter = null;
        this.engine = iESEngine;
        this.ivLength = 0;
    }

    public IESCipher(IESEngine iESEngine, int i) {
        this.helper = new BCJcaJceHelper();
        this.state = -1;
        this.buffer = new ByteArrayOutputStream();
        this.engineParam = null;
        this.engineSpec = null;
        this.dhaesMode = false;
        this.otherKeyParameter = null;
        this.engine = iESEngine;
        this.ivLength = i;
    }

    @Override // javax.crypto.CipherSpi
    public int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        byte[] engineDoFinal = engineDoFinal(bArr, i, i2);
        System.arraycopy(engineDoFinal, 0, bArr2, i3, engineDoFinal.length);
        return engineDoFinal.length;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineDoFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        if (i2 != 0) {
            this.buffer.write(bArr, i, i2);
        }
        byte[] byteArray = this.buffer.toByteArray();
        this.buffer.reset();
        CipherParameters iESWithCipherParameters = new IESWithCipherParameters(this.engineSpec.getDerivationV(), this.engineSpec.getEncodingV(), this.engineSpec.getMacKeySize(), this.engineSpec.getCipherKeySize());
        if (this.engineSpec.getNonce() != null) {
            iESWithCipherParameters = new ParametersWithIV(iESWithCipherParameters, this.engineSpec.getNonce());
        }
        DHParameters parameters = ((DHKeyParameters) this.key).getParameters();
        AsymmetricKeyParameter asymmetricKeyParameter = this.otherKeyParameter;
        if (asymmetricKeyParameter != null) {
            try {
                if (this.state != 1 && this.state != 3) {
                    this.engine.init(false, this.key, asymmetricKeyParameter, iESWithCipherParameters);
                    return this.engine.processBlock(byteArray, 0, byteArray.length);
                }
                this.engine.init(true, this.otherKeyParameter, this.key, iESWithCipherParameters);
                return this.engine.processBlock(byteArray, 0, byteArray.length);
            } catch (Exception e) {
                throw new BadBlockException("unable to process block", e);
            }
        }
        int i3 = this.state;
        if (i3 == 1 || i3 == 3) {
            DHKeyPairGenerator dHKeyPairGenerator = new DHKeyPairGenerator();
            dHKeyPairGenerator.init(new DHKeyGenerationParameters(this.random, parameters));
            try {
                this.engine.init(this.key, iESWithCipherParameters, new EphemeralKeyPairGenerator(dHKeyPairGenerator, new KeyEncoder() { // from class: org.bouncycastle.jcajce.provider.asymmetric.dh.IESCipher.1
                    @Override // org.bouncycastle.crypto.KeyEncoder
                    public byte[] getEncoded(AsymmetricKeyParameter asymmetricKeyParameter2) {
                        byte[] bArr2 = new byte[(((DHKeyParameters) asymmetricKeyParameter2).getParameters().getP().bitLength() + 7) / 8];
                        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(((DHPublicKeyParameters) asymmetricKeyParameter2).getY());
                        if (asUnsignedByteArray.length > bArr2.length) {
                            throw new IllegalArgumentException("Senders's public key longer than expected.");
                        }
                        System.arraycopy(asUnsignedByteArray, 0, bArr2, bArr2.length - asUnsignedByteArray.length, asUnsignedByteArray.length);
                        return bArr2;
                    }
                }));
                return this.engine.processBlock(byteArray, 0, byteArray.length);
            } catch (Exception e2) {
                throw new BadBlockException("unable to process block", e2);
            }
        }
        if (i3 != 2 && i3 != 4) {
            throw new IllegalStateException("IESCipher not initialised");
        }
        try {
            this.engine.init(this.key, iESWithCipherParameters, new DHIESPublicKeyParser(((DHKeyParameters) this.key).getParameters()));
            return this.engine.processBlock(byteArray, 0, byteArray.length);
        } catch (InvalidCipherTextException e3) {
            throw new BadBlockException("unable to process block", e3);
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetBlockSize() {
        if (this.engine.getCipher() != null) {
            return this.engine.getCipher().getBlockSize();
        }
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineGetIV() {
        IESParameterSpec iESParameterSpec = this.engineSpec;
        if (iESParameterSpec != null) {
            return iESParameterSpec.getNonce();
        }
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        if (key instanceof DHKey) {
            return ((DHKey) key).getParams().getP().bitLength();
        }
        throw new IllegalArgumentException("not a DH key");
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetOutputSize(int i) {
        int size;
        BufferedBlockCipher cipher;
        if (this.key == null) {
            throw new IllegalStateException("cipher not initialised");
        }
        int macSize = this.engine.getMac().getMacSize();
        int bitLength = this.otherKeyParameter == null ? (((((DHKeyParameters) this.key).getParameters().getP().bitLength() + 7) * 2) / 8) + 1 : 0;
        if (this.engine.getCipher() != null) {
            int i2 = this.state;
            if (i2 == 1 || i2 == 3) {
                cipher = this.engine.getCipher();
            } else {
                if (i2 != 2 && i2 != 4) {
                    throw new IllegalStateException("cipher not initialised");
                }
                cipher = this.engine.getCipher();
                i = (i - macSize) - bitLength;
            }
            i = cipher.getOutputSize(i);
        }
        int i3 = this.state;
        if (i3 == 1 || i3 == 3) {
            size = this.buffer.size() + macSize + bitLength;
        } else {
            if (i3 != 2 && i3 != 4) {
                throw new IllegalStateException("IESCipher not initialised");
            }
            size = (this.buffer.size() - macSize) - bitLength;
        }
        return size + i;
    }

    @Override // javax.crypto.CipherSpi
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParam == null && this.engineSpec != null) {
            try {
                this.engineParam = this.helper.createAlgorithmParameters("IES");
                this.engineParam.init(this.engineSpec);
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.engineParam;
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec parameterSpec;
        if (algorithmParameters != null) {
            try {
                parameterSpec = algorithmParameters.getParameterSpec(IESParameterSpec.class);
            } catch (Exception e) {
                throw new InvalidAlgorithmParameterException("cannot recognise parameters: " + e.toString());
            }
        } else {
            parameterSpec = null;
        }
        this.engineParam = algorithmParameters;
        engineInit(i, key, parameterSpec, secureRandom);
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException("cannot handle supplied parameter spec: " + e.getMessage());
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException, InvalidKeyException {
        IESParameterSpec iESParameterSpec;
        AsymmetricKeyParameter generatePublicKeyParameter;
        PrivateKey privateKey;
        if (algorithmParameterSpec == null) {
            byte[] bArr = null;
            int i2 = this.ivLength;
            if (i2 != 0 && i == 1) {
                bArr = new byte[i2];
                secureRandom.nextBytes(bArr);
            }
            iESParameterSpec = IESUtil.guessParameterSpec(this.engine.getCipher(), bArr);
        } else {
            if (!(algorithmParameterSpec instanceof IESParameterSpec)) {
                throw new InvalidAlgorithmParameterException("must be passed IES parameters");
            }
            iESParameterSpec = (IESParameterSpec) algorithmParameterSpec;
        }
        this.engineSpec = iESParameterSpec;
        byte[] nonce = this.engineSpec.getNonce();
        int i3 = this.ivLength;
        if (i3 != 0 && (nonce == null || nonce.length != i3)) {
            throw new InvalidAlgorithmParameterException("NONCE in IES Parameters needs to be " + this.ivLength + " bytes long");
        }
        if (i == 1 || i == 3) {
            if (!(key instanceof DHPublicKey)) {
                if (!(key instanceof IESKey)) {
                    throw new InvalidKeyException("must be passed recipient's public DH key for encryption");
                }
                IESKey iESKey = (IESKey) key;
                this.key = DHUtil.generatePublicKeyParameter(iESKey.getPublic());
                this.otherKeyParameter = DHUtil.generatePrivateKeyParameter(iESKey.getPrivate());
                this.random = secureRandom;
                this.state = i;
                this.buffer.reset();
            }
            generatePublicKeyParameter = DHUtil.generatePublicKeyParameter((PublicKey) key);
        } else {
            if (i != 2 && i != 4) {
                throw new InvalidKeyException("must be passed EC key");
            }
            if (key instanceof DHPrivateKey) {
                privateKey = (PrivateKey) key;
            } else {
                if (!(key instanceof IESKey)) {
                    throw new InvalidKeyException("must be passed recipient's private DH key for decryption");
                }
                IESKey iESKey2 = (IESKey) key;
                this.otherKeyParameter = DHUtil.generatePublicKeyParameter(iESKey2.getPublic());
                privateKey = iESKey2.getPrivate();
            }
            generatePublicKeyParameter = DHUtil.generatePrivateKeyParameter(privateKey);
        }
        this.key = generatePublicKeyParameter;
        this.random = secureRandom;
        this.state = i;
        this.buffer.reset();
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        boolean z;
        String upperCase = Strings.toUpperCase(str);
        if (upperCase.equals("NONE")) {
            z = false;
        } else {
            if (!upperCase.equals("DHAES")) {
                throw new IllegalArgumentException("can't support mode " + str);
            }
            z = true;
        }
        this.dhaesMode = z;
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        String upperCase = Strings.toUpperCase(str);
        if (!upperCase.equals("NOPADDING") && !upperCase.equals("PKCS5PADDING") && !upperCase.equals("PKCS7PADDING")) {
            throw new NoSuchPaddingException("padding not available with IESCipher");
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.buffer.write(bArr, i, i2);
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineUpdate(byte[] bArr, int i, int i2) {
        this.buffer.write(bArr, i, i2);
        return null;
    }
}
