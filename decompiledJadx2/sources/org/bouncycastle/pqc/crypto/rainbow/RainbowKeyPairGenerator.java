package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* loaded from: classes9.dex */
public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {

    /* renamed from: A1 */
    private short[][] f9928A1;
    private short[][] A1inv;

    /* renamed from: A2 */
    private short[][] f9929A2;
    private short[][] A2inv;

    /* renamed from: b1 */
    private short[] f9930b1;

    /* renamed from: b2 */
    private short[] f9931b2;
    private boolean initialized = false;
    private Layer[] layers;
    private int numOfLayers;
    private short[][] pub_quadratic;
    private short[] pub_scalar;
    private short[][] pub_singular;
    private RainbowKeyGenerationParameters rainbowParams;

    /* renamed from: sr */
    private SecureRandom f9932sr;

    /* renamed from: vi */
    private int[] f9933vi;

    private void compactPublicKey(short[][][] sArr) {
        int length = sArr.length;
        int length2 = sArr[0].length;
        this.pub_quadratic = (short[][]) Array.newInstance((Class<?>) short.class, length, ((length2 + 1) * length2) / 2);
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < length2) {
                int i4 = i3;
                for (int i5 = i2; i5 < length2; i5++) {
                    short[][] sArr2 = this.pub_quadratic;
                    if (i5 == i2) {
                        sArr2[i][i4] = sArr[i][i2][i5];
                    } else {
                        sArr2[i][i4] = GF2Field.addElem(sArr[i][i2][i5], sArr[i][i5][i2]);
                    }
                    i4++;
                }
                i2++;
                i3 = i4;
            }
        }
    }

    private void computePublicKey() {
        Class<short> cls;
        Class<short> cls2 = short.class;
        ComputeInField computeInField = new ComputeInField();
        int[] iArr = this.f9933vi;
        int i = 0;
        int i2 = iArr[iArr.length - 1] - iArr[0];
        int i3 = iArr[iArr.length - 1];
        short[][][] sArr = (short[][][]) Array.newInstance((Class<?>) cls2, i2, i3, i3);
        this.pub_singular = (short[][]) Array.newInstance((Class<?>) cls2, i2, i3);
        this.pub_scalar = new short[i2];
        short[] sArr2 = new short[i3];
        int i4 = 0;
        int i5 = 0;
        while (true) {
            Layer[] layerArr = this.layers;
            if (i4 >= layerArr.length) {
                break;
            }
            short[][][] coeffAlpha = layerArr[i4].getCoeffAlpha();
            short[][][] coeffBeta = this.layers[i4].getCoeffBeta();
            short[][] coeffGamma = this.layers[i4].getCoeffGamma();
            short[] coeffEta = this.layers[i4].getCoeffEta();
            int length = coeffAlpha[i].length;
            int length2 = coeffBeta[i].length;
            int i6 = i;
            while (i6 < length) {
                while (true) {
                    cls = cls2;
                    if (i >= length) {
                        break;
                    }
                    int i7 = 0;
                    while (i7 < length2) {
                        int i8 = i3;
                        int i9 = i2;
                        int i10 = i + length2;
                        short[] multVect = computeInField.multVect(coeffAlpha[i6][i][i7], this.f9929A2[i10]);
                        int i11 = i5 + i6;
                        int i12 = i4;
                        sArr[i11] = computeInField.addSquareMatrix(sArr[i11], computeInField.multVects(multVect, this.f9929A2[i7]));
                        short[] multVect2 = computeInField.multVect(this.f9931b2[i7], multVect);
                        short[][] sArr3 = this.pub_singular;
                        sArr3[i11] = computeInField.addVect(multVect2, sArr3[i11]);
                        short[] multVect3 = computeInField.multVect(this.f9931b2[i10], computeInField.multVect(coeffAlpha[i6][i][i7], this.f9929A2[i7]));
                        short[][] sArr4 = this.pub_singular;
                        sArr4[i11] = computeInField.addVect(multVect3, sArr4[i11]);
                        short multElem = GF2Field.multElem(coeffAlpha[i6][i][i7], this.f9931b2[i10]);
                        short[] sArr5 = this.pub_scalar;
                        sArr5[i11] = GF2Field.addElem(sArr5[i11], GF2Field.multElem(multElem, this.f9931b2[i7]));
                        i7++;
                        i2 = i9;
                        i3 = i8;
                        coeffAlpha = coeffAlpha;
                        i4 = i12;
                        coeffEta = coeffEta;
                    }
                    i++;
                    cls2 = cls;
                }
                int i13 = i3;
                int i14 = i2;
                int i15 = i4;
                short[][][] sArr6 = coeffAlpha;
                short[] sArr7 = coeffEta;
                for (int i16 = 0; i16 < length2; i16++) {
                    for (int i17 = 0; i17 < length2; i17++) {
                        short[] multVect4 = computeInField.multVect(coeffBeta[i6][i16][i17], this.f9929A2[i16]);
                        int i18 = i5 + i6;
                        sArr[i18] = computeInField.addSquareMatrix(sArr[i18], computeInField.multVects(multVect4, this.f9929A2[i17]));
                        short[] multVect5 = computeInField.multVect(this.f9931b2[i17], multVect4);
                        short[][] sArr8 = this.pub_singular;
                        sArr8[i18] = computeInField.addVect(multVect5, sArr8[i18]);
                        short[] multVect6 = computeInField.multVect(this.f9931b2[i16], computeInField.multVect(coeffBeta[i6][i16][i17], this.f9929A2[i17]));
                        short[][] sArr9 = this.pub_singular;
                        sArr9[i18] = computeInField.addVect(multVect6, sArr9[i18]);
                        short multElem2 = GF2Field.multElem(coeffBeta[i6][i16][i17], this.f9931b2[i16]);
                        short[] sArr10 = this.pub_scalar;
                        sArr10[i18] = GF2Field.addElem(sArr10[i18], GF2Field.multElem(multElem2, this.f9931b2[i17]));
                    }
                }
                for (int i19 = 0; i19 < length2 + length; i19++) {
                    short[] multVect7 = computeInField.multVect(coeffGamma[i6][i19], this.f9929A2[i19]);
                    short[][] sArr11 = this.pub_singular;
                    int i20 = i5 + i6;
                    sArr11[i20] = computeInField.addVect(multVect7, sArr11[i20]);
                    short[] sArr12 = this.pub_scalar;
                    sArr12[i20] = GF2Field.addElem(sArr12[i20], GF2Field.multElem(coeffGamma[i6][i19], this.f9931b2[i19]));
                }
                short[] sArr13 = this.pub_scalar;
                int i21 = i5 + i6;
                sArr13[i21] = GF2Field.addElem(sArr13[i21], sArr7[i6]);
                i6++;
                cls2 = cls;
                i2 = i14;
                i3 = i13;
                coeffAlpha = sArr6;
                i4 = i15;
                coeffEta = sArr7;
                i = 0;
            }
            i5 += length;
            i4++;
            i = 0;
        }
        Class<short> cls3 = cls2;
        int i22 = i3;
        short[][][] sArr14 = (short[][][]) Array.newInstance((Class<?>) cls3, i2, i22, i22);
        short[][] sArr15 = (short[][]) Array.newInstance((Class<?>) cls3, i2, i22);
        short[] sArr16 = new short[i2];
        for (int i23 = 0; i23 < i2; i23++) {
            int i24 = 0;
            while (true) {
                short[][] sArr17 = this.f9928A1;
                if (i24 < sArr17.length) {
                    sArr14[i23] = computeInField.addSquareMatrix(sArr14[i23], computeInField.multMatrix(sArr17[i23][i24], sArr[i24]));
                    sArr15[i23] = computeInField.addVect(sArr15[i23], computeInField.multVect(this.f9928A1[i23][i24], this.pub_singular[i24]));
                    sArr16[i23] = GF2Field.addElem(sArr16[i23], GF2Field.multElem(this.f9928A1[i23][i24], this.pub_scalar[i24]));
                    i24++;
                }
            }
            sArr16[i23] = GF2Field.addElem(sArr16[i23], this.f9930b1[i23]);
        }
        this.pub_singular = sArr15;
        this.pub_scalar = sArr16;
        compactPublicKey(sArr14);
    }

    private void generateF() {
        this.layers = new Layer[this.numOfLayers];
        int i = 0;
        while (i < this.numOfLayers) {
            Layer[] layerArr = this.layers;
            int[] iArr = this.f9933vi;
            int i2 = i + 1;
            layerArr[i] = new Layer(iArr[i], iArr[i2], this.f9932sr);
            i = i2;
        }
    }

    private void generateL1() {
        int[] iArr = this.f9933vi;
        int i = iArr[iArr.length - 1] - iArr[0];
        this.f9928A1 = (short[][]) Array.newInstance((Class<?>) short.class, i, i);
        this.A1inv = (short[][]) null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A1inv == null) {
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    this.f9928A1[i2][i3] = (short) (this.f9932sr.nextInt() & 255);
                }
            }
            this.A1inv = computeInField.inverse(this.f9928A1);
        }
        this.f9930b1 = new short[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.f9930b1[i4] = (short) (this.f9932sr.nextInt() & 255);
        }
    }

    private void generateL2() {
        int i;
        int i2 = this.f9933vi[r0.length - 1];
        this.f9929A2 = (short[][]) Array.newInstance((Class<?>) short.class, i2, i2);
        this.A2inv = (short[][]) null;
        ComputeInField computeInField = new ComputeInField();
        while (true) {
            if (this.A2inv != null) {
                break;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    this.f9929A2[i3][i4] = (short) (this.f9932sr.nextInt() & 255);
                }
            }
            this.A2inv = computeInField.inverse(this.f9929A2);
        }
        this.f9931b2 = new short[i2];
        for (i = 0; i < i2; i++) {
            this.f9931b2[i] = (short) (this.f9932sr.nextInt() & 255);
        }
    }

    private void initializeDefault() {
        initialize(new RainbowKeyGenerationParameters(CryptoServicesRegistrar.getSecureRandom(), new RainbowParameters()));
    }

    private void keygen() {
        generateL1();
        generateL2();
        generateF();
        computePublicKey();
    }

    public AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        keygen();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = new RainbowPrivateKeyParameters(this.A1inv, this.f9930b1, this.A2inv, this.f9931b2, this.f9933vi, this.layers);
        int[] iArr = this.f9933vi;
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RainbowPublicKeyParameters(iArr[iArr.length - 1] - iArr[0], this.pub_quadratic, this.pub_singular, this.pub_scalar), (AsymmetricKeyParameter) rainbowPrivateKeyParameters);
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.rainbowParams = (RainbowKeyGenerationParameters) keyGenerationParameters;
        this.f9932sr = this.rainbowParams.getRandom();
        this.f9933vi = this.rainbowParams.getParameters().getVi();
        this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
        this.initialized = true;
    }
}
