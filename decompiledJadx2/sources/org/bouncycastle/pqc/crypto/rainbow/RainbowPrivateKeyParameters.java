package org.bouncycastle.pqc.crypto.rainbow;

/* loaded from: classes9.dex */
public class RainbowPrivateKeyParameters extends RainbowKeyParameters {
    private short[][] A1inv;
    private short[][] A2inv;

    /* renamed from: b1 */
    private short[] f9935b1;

    /* renamed from: b2 */
    private short[] f9936b2;
    private Layer[] layers;

    /* renamed from: vi */
    private int[] f9937vi;

    public RainbowPrivateKeyParameters(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        super(true, iArr[iArr.length - 1] - iArr[0]);
        this.A1inv = sArr;
        this.f9935b1 = sArr2;
        this.A2inv = sArr3;
        this.f9936b2 = sArr4;
        this.f9937vi = iArr;
        this.layers = layerArr;
    }

    public short[] getB1() {
        return this.f9935b1;
    }

    public short[] getB2() {
        return this.f9936b2;
    }

    public short[][] getInvA1() {
        return this.A1inv;
    }

    public short[][] getInvA2() {
        return this.A2inv;
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return this.f9937vi;
    }
}
