package org.bouncycastle.crypto.engines;

import androidx.core.view.InputDeviceCompat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.DigestDerivationFunction;
import org.bouncycastle.crypto.EphemeralKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyParser;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.IESParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.ISO18033KDFParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public class EthereumIESEngine {

    /* renamed from: IV */
    private byte[] f9383IV;

    /* renamed from: V */
    byte[] f9384V;
    BasicAgreement agree;
    BufferedBlockCipher cipher;
    byte[] commonMac;
    boolean forEncryption;
    DerivationFunction kdf;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;
    Mac mac;
    byte[] macBuf;
    IESParameters param;
    CipherParameters privParam;
    CipherParameters pubParam;

    /* loaded from: classes9.dex */
    public static class HandshakeKDFFunction implements DigestDerivationFunction {
        private int counterStart;
        private Digest digest;

        /* renamed from: iv */
        private byte[] f9385iv;
        private byte[] shared;

        public HandshakeKDFFunction(int i, Digest digest) {
            this.counterStart = i;
            this.digest = digest;
        }

        @Override // org.bouncycastle.crypto.DerivationFunction
        public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
            if (bArr.length - i2 < i) {
                throw new OutputLengthException("output buffer too small");
            }
            long j = i2;
            int digestSize = this.digest.getDigestSize();
            if (j > TarConstants.MAXSIZE) {
                throw new IllegalArgumentException("output length too large");
            }
            long j2 = digestSize;
            int i3 = (int) (((j + j2) - 1) / j2);
            byte[] bArr2 = new byte[this.digest.getDigestSize()];
            byte[] bArr3 = new byte[4];
            Pack.intToBigEndian(this.counterStart, bArr3, 0);
            int i4 = this.counterStart & InputDeviceCompat.SOURCE_ANY;
            int i5 = i;
            for (int i6 = 0; i6 < i3; i6++) {
                this.digest.update(bArr3, 0, bArr3.length);
                Digest digest = this.digest;
                byte[] bArr4 = this.shared;
                digest.update(bArr4, 0, bArr4.length);
                byte[] bArr5 = this.f9385iv;
                if (bArr5 != null) {
                    this.digest.update(bArr5, 0, bArr5.length);
                }
                this.digest.doFinal(bArr2, 0);
                if (i2 > digestSize) {
                    System.arraycopy(bArr2, 0, bArr, i5, digestSize);
                    i5 += digestSize;
                    i2 -= digestSize;
                } else {
                    System.arraycopy(bArr2, 0, bArr, i5, i2);
                }
                byte b = (byte) (bArr3[3] + 1);
                bArr3[3] = b;
                if (b == 0) {
                    i4 += 256;
                    Pack.intToBigEndian(i4, bArr3, 0);
                }
            }
            this.digest.reset();
            return (int) j;
        }

        @Override // org.bouncycastle.crypto.DigestDerivationFunction
        public Digest getDigest() {
            return this.digest;
        }

        @Override // org.bouncycastle.crypto.DerivationFunction
        public void init(DerivationParameters derivationParameters) {
            if (derivationParameters instanceof KDFParameters) {
                KDFParameters kDFParameters = (KDFParameters) derivationParameters;
                this.shared = kDFParameters.getSharedSecret();
                this.f9385iv = kDFParameters.getIV();
            } else {
                if (!(derivationParameters instanceof ISO18033KDFParameters)) {
                    throw new IllegalArgumentException("KDF parameters required for generator");
                }
                this.shared = ((ISO18033KDFParameters) derivationParameters).getSeed();
                this.f9385iv = null;
            }
        }
    }

    public EthereumIESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, byte[] bArr) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.commonMac = bArr;
        this.cipher = null;
    }

    public EthereumIESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, byte[] bArr, BufferedBlockCipher bufferedBlockCipher) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac;
        this.macBuf = new byte[mac.getMacSize()];
        this.commonMac = bArr;
        this.cipher = bufferedBlockCipher;
    }

    private byte[] decryptBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        int processBytes;
        if (i2 < this.f9384V.length + this.mac.getMacSize()) {
            throw new InvalidCipherTextException("length of input must be greater than the MAC and V combined");
        }
        if (this.cipher == null) {
            byte[] bArr4 = new byte[(i2 - this.f9384V.length) - this.mac.getMacSize()];
            bArr2 = new byte[this.param.getMacKeySize() / 8];
            byte[] bArr5 = new byte[bArr4.length + bArr2.length];
            this.kdf.generateBytes(bArr5, 0, bArr5.length);
            if (this.f9384V.length != 0) {
                System.arraycopy(bArr5, 0, bArr2, 0, bArr2.length);
                System.arraycopy(bArr5, bArr2.length, bArr4, 0, bArr4.length);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, bArr4.length);
                System.arraycopy(bArr5, bArr4.length, bArr2, 0, bArr2.length);
            }
            byte[] bArr6 = new byte[bArr4.length];
            for (int i3 = 0; i3 != bArr4.length; i3++) {
                bArr6[i3] = (byte) (bArr[(this.f9384V.length + i) + i3] ^ bArr4[i3]);
            }
            bArr3 = bArr6;
            processBytes = 0;
        } else {
            byte[] bArr7 = new byte[((IESWithCipherParameters) this.param).getCipherKeySize() / 8];
            bArr2 = new byte[this.param.getMacKeySize() / 8];
            byte[] bArr8 = new byte[bArr7.length + bArr2.length];
            this.kdf.generateBytes(bArr8, 0, bArr8.length);
            System.arraycopy(bArr8, 0, bArr7, 0, bArr7.length);
            System.arraycopy(bArr8, bArr7.length, bArr2, 0, bArr2.length);
            CipherParameters keyParameter = new KeyParameter(bArr7);
            byte[] bArr9 = this.f9383IV;
            if (bArr9 != null) {
                keyParameter = new ParametersWithIV(keyParameter, bArr9);
            }
            this.cipher.init(false, keyParameter);
            bArr3 = new byte[this.cipher.getOutputSize((i2 - this.f9384V.length) - this.mac.getMacSize())];
            BufferedBlockCipher bufferedBlockCipher = this.cipher;
            byte[] bArr10 = this.f9384V;
            processBytes = bufferedBlockCipher.processBytes(bArr, bArr10.length + i, (i2 - bArr10.length) - this.mac.getMacSize(), bArr3, 0);
        }
        byte[] encodingV = this.param.getEncodingV();
        byte[] lengthTag = this.f9384V.length != 0 ? getLengthTag(encodingV) : null;
        int i4 = i + i2;
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i4 - this.mac.getMacSize(), i4);
        byte[] bArr11 = new byte[copyOfRange.length];
        SHA256Digest sHA256Digest = new SHA256Digest();
        byte[] bArr12 = new byte[sHA256Digest.getDigestSize()];
        sHA256Digest.reset();
        sHA256Digest.update(bArr2, 0, bArr2.length);
        sHA256Digest.doFinal(bArr12, 0);
        this.mac.init(new KeyParameter(bArr12));
        Mac mac = this.mac;
        byte[] bArr13 = this.f9383IV;
        mac.update(bArr13, 0, bArr13.length);
        Mac mac2 = this.mac;
        byte[] bArr14 = this.f9384V;
        mac2.update(bArr, i + bArr14.length, (i2 - bArr14.length) - bArr11.length);
        if (encodingV != null) {
            this.mac.update(encodingV, 0, encodingV.length);
        }
        if (this.f9384V.length != 0) {
            this.mac.update(lengthTag, 0, lengthTag.length);
        }
        Mac mac3 = this.mac;
        byte[] bArr15 = this.commonMac;
        mac3.update(bArr15, 0, bArr15.length);
        this.mac.doFinal(bArr11, 0);
        if (!Arrays.constantTimeAreEqual(copyOfRange, bArr11)) {
            throw new InvalidCipherTextException("invalid MAC");
        }
        BufferedBlockCipher bufferedBlockCipher2 = this.cipher;
        return bufferedBlockCipher2 == null ? bArr3 : Arrays.copyOfRange(bArr3, 0, processBytes + bufferedBlockCipher2.doFinal(bArr3, processBytes));
    }

    private byte[] encryptBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2;
        BufferedBlockCipher bufferedBlockCipher;
        CipherParameters keyParameter;
        byte[] bArr3;
        if (this.cipher == null) {
            byte[] bArr4 = new byte[i2];
            bArr2 = new byte[this.param.getMacKeySize() / 8];
            byte[] bArr5 = new byte[bArr4.length + bArr2.length];
            this.kdf.generateBytes(bArr5, 0, bArr5.length);
            if (this.f9384V.length != 0) {
                System.arraycopy(bArr5, 0, bArr2, 0, bArr2.length);
                System.arraycopy(bArr5, bArr2.length, bArr4, 0, bArr4.length);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, bArr4.length);
                System.arraycopy(bArr5, i2, bArr2, 0, bArr2.length);
            }
            byte[] bArr6 = new byte[i2];
            for (int i3 = 0; i3 != i2; i3++) {
                bArr6[i3] = (byte) (bArr[i + i3] ^ bArr4[i3]);
            }
            bArr3 = bArr6;
        } else {
            byte[] bArr7 = new byte[((IESWithCipherParameters) this.param).getCipherKeySize() / 8];
            bArr2 = new byte[this.param.getMacKeySize() / 8];
            byte[] bArr8 = new byte[bArr7.length + bArr2.length];
            this.kdf.generateBytes(bArr8, 0, bArr8.length);
            System.arraycopy(bArr8, 0, bArr7, 0, bArr7.length);
            System.arraycopy(bArr8, bArr7.length, bArr2, 0, bArr2.length);
            if (this.f9383IV != null) {
                bufferedBlockCipher = this.cipher;
                keyParameter = new ParametersWithIV(new KeyParameter(bArr7), this.f9383IV);
            } else {
                bufferedBlockCipher = this.cipher;
                keyParameter = new KeyParameter(bArr7);
            }
            bufferedBlockCipher.init(true, keyParameter);
            bArr3 = new byte[this.cipher.getOutputSize(i2)];
            int processBytes = this.cipher.processBytes(bArr, i, i2, bArr3, 0);
            i2 = processBytes + this.cipher.doFinal(bArr3, processBytes);
        }
        byte[] encodingV = this.param.getEncodingV();
        byte[] lengthTag = this.f9384V.length != 0 ? getLengthTag(encodingV) : null;
        byte[] bArr9 = new byte[this.mac.getMacSize()];
        SHA256Digest sHA256Digest = new SHA256Digest();
        byte[] bArr10 = new byte[sHA256Digest.getDigestSize()];
        sHA256Digest.reset();
        sHA256Digest.update(bArr2, 0, bArr2.length);
        sHA256Digest.doFinal(bArr10, 0);
        this.mac.init(new KeyParameter(bArr10));
        Mac mac = this.mac;
        byte[] bArr11 = this.f9383IV;
        mac.update(bArr11, 0, bArr11.length);
        this.mac.update(bArr3, 0, bArr3.length);
        if (encodingV != null) {
            this.mac.update(encodingV, 0, encodingV.length);
        }
        if (this.f9384V.length != 0) {
            this.mac.update(lengthTag, 0, lengthTag.length);
        }
        Mac mac2 = this.mac;
        byte[] bArr12 = this.commonMac;
        mac2.update(bArr12, 0, bArr12.length);
        this.mac.doFinal(bArr9, 0);
        byte[] bArr13 = this.f9384V;
        byte[] bArr14 = new byte[bArr13.length + i2 + bArr9.length];
        System.arraycopy(bArr13, 0, bArr14, 0, bArr13.length);
        System.arraycopy(bArr3, 0, bArr14, this.f9384V.length, i2);
        System.arraycopy(bArr9, 0, bArr14, this.f9384V.length + i2, bArr9.length);
        return bArr14;
    }

    private void extractParams(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.f9383IV = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            this.f9383IV = null;
        }
        this.param = (IESParameters) cipherParameters;
    }

    public BufferedBlockCipher getCipher() {
        return this.cipher;
    }

    protected byte[] getLengthTag(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        if (bArr != null) {
            Pack.longToBigEndian(bArr.length * 8, bArr2, 0);
        }
        return bArr2;
    }

    public Mac getMac() {
        return this.mac;
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.forEncryption = false;
        this.privParam = asymmetricKeyParameter;
        this.keyParser = keyParser;
        extractParams(cipherParameters);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.forEncryption = true;
        this.pubParam = asymmetricKeyParameter;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        extractParams(cipherParameters);
    }

    public void init(boolean z, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.forEncryption = z;
        this.privParam = cipherParameters;
        this.pubParam = cipherParameters2;
        this.f9384V = new byte[0];
        extractParams(cipherParameters3);
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.forEncryption) {
            EphemeralKeyPairGenerator ephemeralKeyPairGenerator = this.keyPairGenerator;
            if (ephemeralKeyPairGenerator != null) {
                EphemeralKeyPair generate = ephemeralKeyPairGenerator.generate();
                this.privParam = generate.getKeyPair().getPrivate();
                this.f9384V = generate.getEncodedPublicKey();
            }
        } else if (this.keyParser != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
            try {
                this.pubParam = this.keyParser.readKey(byteArrayInputStream);
                this.f9384V = Arrays.copyOfRange(bArr, i, (i2 - byteArrayInputStream.available()) + i);
            } catch (IOException e) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e.getMessage(), e);
            } catch (IllegalArgumentException e2) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e2.getMessage(), e2);
            }
        }
        this.agree.init(this.privParam);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.agree.getFieldSize(), this.agree.calculateAgreement(this.pubParam));
        byte[] bArr2 = this.f9384V;
        if (bArr2.length != 0) {
            byte[] concatenate = Arrays.concatenate(bArr2, asUnsignedByteArray);
            Arrays.fill(asUnsignedByteArray, (byte) 0);
            asUnsignedByteArray = concatenate;
        }
        try {
            this.kdf.init(new KDFParameters(asUnsignedByteArray, this.param.getDerivationV()));
            return this.forEncryption ? encryptBlock(bArr, i, i2) : decryptBlock(bArr, i, i2);
        } finally {
            Arrays.fill(asUnsignedByteArray, (byte) 0);
        }
    }
}
