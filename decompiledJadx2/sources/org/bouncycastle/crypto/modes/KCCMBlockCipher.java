package org.bouncycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class KCCMBlockCipher implements AEADBlockCipher {
    private static final int BITS_IN_BYTE = 8;
    private static final int BYTES_IN_INT = 4;
    private static final int MAX_MAC_BIT_LENGTH = 512;
    private static final int MIN_MAC_BIT_LENGTH = 64;

    /* renamed from: G1 */
    private byte[] f9522G1;
    private int Nb_;
    private ExposedByteArrayOutputStream associatedText;
    private byte[] buffer;
    private byte[] counter;
    private ExposedByteArrayOutputStream data;
    private BlockCipher engine;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private byte[] mac;
    private byte[] macBlock;
    private int macSize;
    private byte[] nonce;

    /* renamed from: s */
    private byte[] f9523s;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream() {
        }

        public byte[] getBuffer() {
            return this.buf;
        }
    }

    public KCCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, 4);
    }

    public KCCMBlockCipher(BlockCipher blockCipher, int i) {
        this.associatedText = new ExposedByteArrayOutputStream();
        this.data = new ExposedByteArrayOutputStream();
        this.Nb_ = 4;
        this.engine = blockCipher;
        this.macSize = blockCipher.getBlockSize();
        this.nonce = new byte[blockCipher.getBlockSize()];
        this.initialAssociatedText = new byte[blockCipher.getBlockSize()];
        this.mac = new byte[blockCipher.getBlockSize()];
        this.macBlock = new byte[blockCipher.getBlockSize()];
        this.f9522G1 = new byte[blockCipher.getBlockSize()];
        this.buffer = new byte[blockCipher.getBlockSize()];
        this.f9523s = new byte[blockCipher.getBlockSize()];
        this.counter = new byte[blockCipher.getBlockSize()];
        setNb(i);
    }

    private void CalculateMac(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            for (int i3 = 0; i3 < this.engine.getBlockSize(); i3++) {
                byte[] bArr2 = this.macBlock;
                bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i + i3]);
            }
            BlockCipher blockCipher = this.engine;
            byte[] bArr3 = this.macBlock;
            blockCipher.processBlock(bArr3, 0, bArr3, 0);
            i2 -= this.engine.getBlockSize();
            i += this.engine.getBlockSize();
        }
    }

    private void ProcessBlock(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        while (true) {
            byte[] bArr3 = this.counter;
            if (i4 >= bArr3.length) {
                break;
            }
            byte[] bArr4 = this.f9523s;
            bArr4[i4] = (byte) (bArr4[i4] + bArr3[i4]);
            i4++;
        }
        this.engine.processBlock(this.f9523s, 0, this.buffer, 0);
        for (int i5 = 0; i5 < this.engine.getBlockSize(); i5++) {
            bArr2[i3 + i5] = (byte) (this.buffer[i5] ^ bArr[i + i5]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0047 A[LOOP:0: B:17:0x0040->B:19:0x0047, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private byte getFlag(boolean z, int i) {
        String str;
        String binaryString;
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append("1");
        } else {
            stringBuffer.append("0");
        }
        if (i == 8) {
            str = "010";
        } else if (i == 16) {
            str = "011";
        } else if (i == 32) {
            str = "100";
        } else {
            if (i != 48) {
                if (i == 64) {
                    str = "110";
                }
                binaryString = Integer.toBinaryString(this.Nb_ - 1);
                while (binaryString.length() < 4) {
                    binaryString = new StringBuffer(binaryString).insert(0, "0").toString();
                }
                stringBuffer.append(binaryString);
                return (byte) Integer.parseInt(stringBuffer.toString(), 2);
            }
            str = "101";
        }
        stringBuffer.append(str);
        binaryString = Integer.toBinaryString(this.Nb_ - 1);
        while (binaryString.length() < 4) {
        }
        stringBuffer.append(binaryString);
        return (byte) Integer.parseInt(stringBuffer.toString(), 2);
    }

    private void intToBytes(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >> 24);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2] = (byte) i;
    }

    private void processAAD(byte[] bArr, int i, int i2, int i3) {
        if (i2 - i < this.engine.getBlockSize()) {
            throw new IllegalArgumentException("authText buffer too short");
        }
        if (i2 % this.engine.getBlockSize() != 0) {
            throw new IllegalArgumentException("padding not supported");
        }
        byte[] bArr2 = this.nonce;
        System.arraycopy(bArr2, 0, this.f9522G1, 0, (bArr2.length - this.Nb_) - 1);
        intToBytes(i3, this.buffer, 0);
        System.arraycopy(this.buffer, 0, this.f9522G1, (this.nonce.length - this.Nb_) - 1, 4);
        byte[] bArr3 = this.f9522G1;
        bArr3[bArr3.length - 1] = getFlag(true, this.macSize);
        this.engine.processBlock(this.f9522G1, 0, this.macBlock, 0);
        intToBytes(i2, this.buffer, 0);
        if (i2 <= this.engine.getBlockSize() - this.Nb_) {
            for (int i4 = 0; i4 < i2; i4++) {
                byte[] bArr4 = this.buffer;
                int i5 = this.Nb_ + i4;
                bArr4[i5] = (byte) (bArr4[i5] ^ bArr[i + i4]);
            }
            for (int i6 = 0; i6 < this.engine.getBlockSize(); i6++) {
                byte[] bArr5 = this.macBlock;
                bArr5[i6] = (byte) (bArr5[i6] ^ this.buffer[i6]);
            }
            BlockCipher blockCipher = this.engine;
            byte[] bArr6 = this.macBlock;
            blockCipher.processBlock(bArr6, 0, bArr6, 0);
            return;
        }
        for (int i7 = 0; i7 < this.engine.getBlockSize(); i7++) {
            byte[] bArr7 = this.macBlock;
            bArr7[i7] = (byte) (bArr7[i7] ^ this.buffer[i7]);
        }
        BlockCipher blockCipher2 = this.engine;
        byte[] bArr8 = this.macBlock;
        blockCipher2.processBlock(bArr8, 0, bArr8, 0);
        while (i2 != 0) {
            for (int i8 = 0; i8 < this.engine.getBlockSize(); i8++) {
                byte[] bArr9 = this.macBlock;
                bArr9[i8] = (byte) (bArr9[i8] ^ bArr[i8 + i]);
            }
            BlockCipher blockCipher3 = this.engine;
            byte[] bArr10 = this.macBlock;
            blockCipher3.processBlock(bArr10, 0, bArr10, 0);
            i += this.engine.getBlockSize();
            i2 -= this.engine.getBlockSize();
        }
    }

    private void setNb(int i) {
        if (i != 4 && i != 6 && i != 8) {
            throw new IllegalArgumentException("Nb = 4 is recommended by DSTU7624 but can be changed to only 6 or 8 in this implementation");
        }
        this.Nb_ = i;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        int processPacket = processPacket(this.data.getBuffer(), 0, this.data.size(), bArr, i);
        reset();
        return processPacket;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.engine.getAlgorithmName() + "/KCCM";
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        return Arrays.clone(this.mac);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i) {
        return i + this.macSize;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        return i;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters parameters;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            if (aEADParameters.getMacSize() > 512 || aEADParameters.getMacSize() < 64 || aEADParameters.getMacSize() % 8 != 0) {
                throw new IllegalArgumentException("Invalid mac size specified");
            }
            this.nonce = aEADParameters.getNonce();
            this.macSize = aEADParameters.getMacSize() / 8;
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            parameters = aEADParameters.getKey();
        } else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("Invalid parameters specified");
            }
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.nonce = parametersWithIV.getIV();
            this.macSize = this.engine.getBlockSize();
            this.initialAssociatedText = null;
            parameters = parametersWithIV.getParameters();
        }
        this.mac = new byte[this.macSize];
        this.forEncryption = z;
        this.engine.init(true, parameters);
        this.counter[0] = 1;
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        this.associatedText.write(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        this.associatedText.write(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        this.data.write(b);
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException {
        if (bArr.length < i + i2) {
            throw new DataLengthException("input buffer too short");
        }
        this.data.write(bArr, i, i2);
        return 0;
    }

    public int processPacket(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalStateException, InvalidCipherTextException {
        int i4;
        if (bArr.length - i < i2) {
            throw new DataLengthException("input buffer too short");
        }
        if (bArr2.length - i3 < i2) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.associatedText.size() > 0) {
            if (this.forEncryption) {
                processAAD(this.associatedText.getBuffer(), 0, this.associatedText.size(), this.data.size());
            } else {
                processAAD(this.associatedText.getBuffer(), 0, this.associatedText.size(), this.data.size() - this.macSize);
            }
        }
        if (!this.forEncryption) {
            if ((i2 - this.macSize) % this.engine.getBlockSize() != 0) {
                throw new DataLengthException("partial blocks not supported");
            }
            this.engine.processBlock(this.nonce, 0, this.f9523s, 0);
            int blockSize = i2 / this.engine.getBlockSize();
            int i5 = i3;
            int i6 = i;
            for (int i7 = 0; i7 < blockSize; i7++) {
                ProcessBlock(bArr, i6, i2, bArr2, i5);
                i6 += this.engine.getBlockSize();
                i5 += this.engine.getBlockSize();
            }
            if (i2 > i6) {
                int i8 = 0;
                while (true) {
                    byte[] bArr3 = this.counter;
                    if (i8 >= bArr3.length) {
                        break;
                    }
                    byte[] bArr4 = this.f9523s;
                    bArr4[i8] = (byte) (bArr4[i8] + bArr3[i8]);
                    i8++;
                }
                this.engine.processBlock(this.f9523s, 0, this.buffer, 0);
                int i9 = 0;
                while (true) {
                    i4 = this.macSize;
                    if (i9 >= i4) {
                        break;
                    }
                    bArr2[i5 + i9] = (byte) (this.buffer[i9] ^ bArr[i6 + i9]);
                    i9++;
                }
                i5 += i4;
            }
            int i10 = 0;
            while (true) {
                byte[] bArr5 = this.counter;
                if (i10 >= bArr5.length) {
                    break;
                }
                byte[] bArr6 = this.f9523s;
                bArr6[i10] = (byte) (bArr6[i10] + bArr5[i10]);
                i10++;
            }
            this.engine.processBlock(this.f9523s, 0, this.buffer, 0);
            int i11 = this.macSize;
            System.arraycopy(bArr2, i5 - i11, this.buffer, 0, i11);
            CalculateMac(bArr2, 0, i5 - this.macSize);
            System.arraycopy(this.macBlock, 0, this.mac, 0, this.macSize);
            int i12 = this.macSize;
            byte[] bArr7 = new byte[i12];
            System.arraycopy(this.buffer, 0, bArr7, 0, i12);
            if (!Arrays.constantTimeAreEqual(this.mac, bArr7)) {
                throw new InvalidCipherTextException("mac check failed");
            }
            reset();
            return i2 - this.macSize;
        }
        if (i2 % this.engine.getBlockSize() != 0) {
            throw new DataLengthException("partial blocks not supported");
        }
        CalculateMac(bArr, i, i2);
        this.engine.processBlock(this.nonce, 0, this.f9523s, 0);
        int i13 = i3;
        int i14 = i;
        int i15 = i2;
        while (i15 > 0) {
            ProcessBlock(bArr, i14, i2, bArr2, i13);
            i15 -= this.engine.getBlockSize();
            i14 += this.engine.getBlockSize();
            i13 += this.engine.getBlockSize();
        }
        int i16 = 0;
        while (true) {
            byte[] bArr8 = this.counter;
            if (i16 >= bArr8.length) {
                break;
            }
            byte[] bArr9 = this.f9523s;
            bArr9[i16] = (byte) (bArr9[i16] + bArr8[i16]);
            i16++;
        }
        this.engine.processBlock(this.f9523s, 0, this.buffer, 0);
        int i17 = 0;
        while (true) {
            int i18 = this.macSize;
            if (i17 >= i18) {
                System.arraycopy(this.macBlock, 0, this.mac, 0, i18);
                reset();
                return i2 + this.macSize;
            }
            bArr2[i13 + i17] = (byte) (this.buffer[i17] ^ this.macBlock[i17]);
            i17++;
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        Arrays.fill(this.f9522G1, (byte) 0);
        Arrays.fill(this.buffer, (byte) 0);
        Arrays.fill(this.counter, (byte) 0);
        Arrays.fill(this.macBlock, (byte) 0);
        this.counter[0] = 1;
        this.data.reset();
        this.associatedText.reset();
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }
}
