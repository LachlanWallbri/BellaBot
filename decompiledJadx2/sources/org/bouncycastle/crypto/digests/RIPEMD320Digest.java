package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;

/* loaded from: classes9.dex */
public class RIPEMD320Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 40;

    /* renamed from: H0 */
    private int f9249H0;

    /* renamed from: H1 */
    private int f9250H1;

    /* renamed from: H2 */
    private int f9251H2;

    /* renamed from: H3 */
    private int f9252H3;

    /* renamed from: H4 */
    private int f9253H4;

    /* renamed from: H5 */
    private int f9254H5;

    /* renamed from: H6 */
    private int f9255H6;

    /* renamed from: H7 */
    private int f9256H7;

    /* renamed from: H8 */
    private int f9257H8;

    /* renamed from: H9 */
    private int f9258H9;

    /* renamed from: X */
    private int[] f9259X;
    private int xOff;

    public RIPEMD320Digest() {
        this.f9259X = new int[16];
        reset();
    }

    public RIPEMD320Digest(RIPEMD320Digest rIPEMD320Digest) {
        super(rIPEMD320Digest);
        this.f9259X = new int[16];
        doCopy(rIPEMD320Digest);
    }

    /* renamed from: RL */
    private int m4054RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void doCopy(RIPEMD320Digest rIPEMD320Digest) {
        super.copyIn(rIPEMD320Digest);
        this.f9249H0 = rIPEMD320Digest.f9249H0;
        this.f9250H1 = rIPEMD320Digest.f9250H1;
        this.f9251H2 = rIPEMD320Digest.f9251H2;
        this.f9252H3 = rIPEMD320Digest.f9252H3;
        this.f9253H4 = rIPEMD320Digest.f9253H4;
        this.f9254H5 = rIPEMD320Digest.f9254H5;
        this.f9255H6 = rIPEMD320Digest.f9255H6;
        this.f9256H7 = rIPEMD320Digest.f9256H7;
        this.f9257H8 = rIPEMD320Digest.f9257H8;
        this.f9258H9 = rIPEMD320Digest.f9258H9;
        int[] iArr = rIPEMD320Digest.f9259X;
        System.arraycopy(iArr, 0, this.f9259X, 0, iArr.length);
        this.xOff = rIPEMD320Digest.xOff;
    }

    /* renamed from: f1 */
    private int m4055f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m4056f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m4057f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m4058f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    /* renamed from: f5 */
    private int m4059f5(int i, int i2, int i3) {
        return i ^ (i2 | (~i3));
    }

    private void unpackWord(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD320Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        unpackWord(this.f9249H0, bArr, i);
        unpackWord(this.f9250H1, bArr, i + 4);
        unpackWord(this.f9251H2, bArr, i + 8);
        unpackWord(this.f9252H3, bArr, i + 12);
        unpackWord(this.f9253H4, bArr, i + 16);
        unpackWord(this.f9254H5, bArr, i + 20);
        unpackWord(this.f9255H6, bArr, i + 24);
        unpackWord(this.f9256H7, bArr, i + 28);
        unpackWord(this.f9257H8, bArr, i + 32);
        unpackWord(this.f9258H9, bArr, i + 36);
        reset();
        return 40;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD320";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 40;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f9249H0;
        int i2 = this.f9250H1;
        int i3 = this.f9251H2;
        int i4 = this.f9252H3;
        int i5 = this.f9253H4;
        int i6 = this.f9254H5;
        int i7 = this.f9255H6;
        int i8 = this.f9256H7;
        int i9 = this.f9257H8;
        int i10 = this.f9258H9;
        int m4054RL = m4054RL(i + m4055f1(i2, i3, i4) + this.f9259X[0], 11) + i5;
        int m4054RL2 = m4054RL(i3, 10);
        int m4054RL3 = m4054RL(i5 + m4055f1(m4054RL, i2, m4054RL2) + this.f9259X[1], 14) + i4;
        int m4054RL4 = m4054RL(i2, 10);
        int m4054RL5 = m4054RL(i4 + m4055f1(m4054RL3, m4054RL, m4054RL4) + this.f9259X[2], 15) + m4054RL2;
        int m4054RL6 = m4054RL(m4054RL, 10);
        int m4054RL7 = m4054RL(m4054RL2 + m4055f1(m4054RL5, m4054RL3, m4054RL6) + this.f9259X[3], 12) + m4054RL4;
        int m4054RL8 = m4054RL(m4054RL3, 10);
        int m4054RL9 = m4054RL(m4054RL4 + m4055f1(m4054RL7, m4054RL5, m4054RL8) + this.f9259X[4], 5) + m4054RL6;
        int m4054RL10 = m4054RL(m4054RL5, 10);
        int m4054RL11 = m4054RL(m4054RL6 + m4055f1(m4054RL9, m4054RL7, m4054RL10) + this.f9259X[5], 8) + m4054RL8;
        int m4054RL12 = m4054RL(m4054RL7, 10);
        int m4054RL13 = m4054RL(m4054RL8 + m4055f1(m4054RL11, m4054RL9, m4054RL12) + this.f9259X[6], 7) + m4054RL10;
        int m4054RL14 = m4054RL(m4054RL9, 10);
        int m4054RL15 = m4054RL(m4054RL10 + m4055f1(m4054RL13, m4054RL11, m4054RL14) + this.f9259X[7], 9) + m4054RL12;
        int m4054RL16 = m4054RL(m4054RL11, 10);
        int m4054RL17 = m4054RL(m4054RL12 + m4055f1(m4054RL15, m4054RL13, m4054RL16) + this.f9259X[8], 11) + m4054RL14;
        int m4054RL18 = m4054RL(m4054RL13, 10);
        int m4054RL19 = m4054RL(m4054RL14 + m4055f1(m4054RL17, m4054RL15, m4054RL18) + this.f9259X[9], 13) + m4054RL16;
        int m4054RL20 = m4054RL(m4054RL15, 10);
        int m4054RL21 = m4054RL(m4054RL16 + m4055f1(m4054RL19, m4054RL17, m4054RL20) + this.f9259X[10], 14) + m4054RL18;
        int m4054RL22 = m4054RL(m4054RL17, 10);
        int m4054RL23 = m4054RL(m4054RL18 + m4055f1(m4054RL21, m4054RL19, m4054RL22) + this.f9259X[11], 15) + m4054RL20;
        int m4054RL24 = m4054RL(m4054RL19, 10);
        int m4054RL25 = m4054RL(m4054RL20 + m4055f1(m4054RL23, m4054RL21, m4054RL24) + this.f9259X[12], 6) + m4054RL22;
        int m4054RL26 = m4054RL(m4054RL21, 10);
        int m4054RL27 = m4054RL(m4054RL22 + m4055f1(m4054RL25, m4054RL23, m4054RL26) + this.f9259X[13], 7) + m4054RL24;
        int m4054RL28 = m4054RL(m4054RL23, 10);
        int m4054RL29 = m4054RL(m4054RL24 + m4055f1(m4054RL27, m4054RL25, m4054RL28) + this.f9259X[14], 9) + m4054RL26;
        int m4054RL30 = m4054RL(m4054RL25, 10);
        int m4054RL31 = m4054RL(m4054RL26 + m4055f1(m4054RL29, m4054RL27, m4054RL30) + this.f9259X[15], 8) + m4054RL28;
        int m4054RL32 = m4054RL(m4054RL27, 10);
        int m4054RL33 = m4054RL(i6 + m4059f5(i7, i8, i9) + this.f9259X[5] + 1352829926, 8) + i10;
        int m4054RL34 = m4054RL(i8, 10);
        int m4054RL35 = m4054RL(i10 + m4059f5(m4054RL33, i7, m4054RL34) + this.f9259X[14] + 1352829926, 9) + i9;
        int m4054RL36 = m4054RL(i7, 10);
        int m4054RL37 = m4054RL(i9 + m4059f5(m4054RL35, m4054RL33, m4054RL36) + this.f9259X[7] + 1352829926, 9) + m4054RL34;
        int m4054RL38 = m4054RL(m4054RL33, 10);
        int m4054RL39 = m4054RL(m4054RL34 + m4059f5(m4054RL37, m4054RL35, m4054RL38) + this.f9259X[0] + 1352829926, 11) + m4054RL36;
        int m4054RL40 = m4054RL(m4054RL35, 10);
        int m4054RL41 = m4054RL(m4054RL36 + m4059f5(m4054RL39, m4054RL37, m4054RL40) + this.f9259X[9] + 1352829926, 13) + m4054RL38;
        int m4054RL42 = m4054RL(m4054RL37, 10);
        int m4054RL43 = m4054RL(m4054RL38 + m4059f5(m4054RL41, m4054RL39, m4054RL42) + this.f9259X[2] + 1352829926, 15) + m4054RL40;
        int m4054RL44 = m4054RL(m4054RL39, 10);
        int m4054RL45 = m4054RL(m4054RL40 + m4059f5(m4054RL43, m4054RL41, m4054RL44) + this.f9259X[11] + 1352829926, 15) + m4054RL42;
        int m4054RL46 = m4054RL(m4054RL41, 10);
        int m4054RL47 = m4054RL(m4054RL42 + m4059f5(m4054RL45, m4054RL43, m4054RL46) + this.f9259X[4] + 1352829926, 5) + m4054RL44;
        int m4054RL48 = m4054RL(m4054RL43, 10);
        int m4054RL49 = m4054RL(m4054RL44 + m4059f5(m4054RL47, m4054RL45, m4054RL48) + this.f9259X[13] + 1352829926, 7) + m4054RL46;
        int m4054RL50 = m4054RL(m4054RL45, 10);
        int m4054RL51 = m4054RL(m4054RL46 + m4059f5(m4054RL49, m4054RL47, m4054RL50) + this.f9259X[6] + 1352829926, 7) + m4054RL48;
        int m4054RL52 = m4054RL(m4054RL47, 10);
        int m4054RL53 = m4054RL(m4054RL48 + m4059f5(m4054RL51, m4054RL49, m4054RL52) + this.f9259X[15] + 1352829926, 8) + m4054RL50;
        int m4054RL54 = m4054RL(m4054RL49, 10);
        int m4054RL55 = m4054RL(m4054RL50 + m4059f5(m4054RL53, m4054RL51, m4054RL54) + this.f9259X[8] + 1352829926, 11) + m4054RL52;
        int m4054RL56 = m4054RL(m4054RL51, 10);
        int m4054RL57 = m4054RL(m4054RL52 + m4059f5(m4054RL55, m4054RL53, m4054RL56) + this.f9259X[1] + 1352829926, 14) + m4054RL54;
        int m4054RL58 = m4054RL(m4054RL53, 10);
        int m4054RL59 = m4054RL(m4054RL54 + m4059f5(m4054RL57, m4054RL55, m4054RL58) + this.f9259X[10] + 1352829926, 14) + m4054RL56;
        int m4054RL60 = m4054RL(m4054RL55, 10);
        int m4054RL61 = m4054RL(m4054RL56 + m4059f5(m4054RL59, m4054RL57, m4054RL60) + this.f9259X[3] + 1352829926, 12) + m4054RL58;
        int m4054RL62 = m4054RL(m4054RL57, 10);
        int m4054RL63 = m4054RL(m4054RL58 + m4059f5(m4054RL61, m4054RL59, m4054RL62) + this.f9259X[12] + 1352829926, 6) + m4054RL60;
        int m4054RL64 = m4054RL(m4054RL59, 10);
        int m4054RL65 = m4054RL(m4054RL28 + m4056f2(m4054RL63, m4054RL29, m4054RL32) + this.f9259X[7] + 1518500249, 7) + m4054RL30;
        int m4054RL66 = m4054RL(m4054RL29, 10);
        int m4054RL67 = m4054RL(m4054RL30 + m4056f2(m4054RL65, m4054RL63, m4054RL66) + this.f9259X[4] + 1518500249, 6) + m4054RL32;
        int m4054RL68 = m4054RL(m4054RL63, 10);
        int m4054RL69 = m4054RL(m4054RL32 + m4056f2(m4054RL67, m4054RL65, m4054RL68) + this.f9259X[13] + 1518500249, 8) + m4054RL66;
        int m4054RL70 = m4054RL(m4054RL65, 10);
        int m4054RL71 = m4054RL(m4054RL66 + m4056f2(m4054RL69, m4054RL67, m4054RL70) + this.f9259X[1] + 1518500249, 13) + m4054RL68;
        int m4054RL72 = m4054RL(m4054RL67, 10);
        int m4054RL73 = m4054RL(m4054RL68 + m4056f2(m4054RL71, m4054RL69, m4054RL72) + this.f9259X[10] + 1518500249, 11) + m4054RL70;
        int m4054RL74 = m4054RL(m4054RL69, 10);
        int m4054RL75 = m4054RL(m4054RL70 + m4056f2(m4054RL73, m4054RL71, m4054RL74) + this.f9259X[6] + 1518500249, 9) + m4054RL72;
        int m4054RL76 = m4054RL(m4054RL71, 10);
        int m4054RL77 = m4054RL(m4054RL72 + m4056f2(m4054RL75, m4054RL73, m4054RL76) + this.f9259X[15] + 1518500249, 7) + m4054RL74;
        int m4054RL78 = m4054RL(m4054RL73, 10);
        int m4054RL79 = m4054RL(m4054RL74 + m4056f2(m4054RL77, m4054RL75, m4054RL78) + this.f9259X[3] + 1518500249, 15) + m4054RL76;
        int m4054RL80 = m4054RL(m4054RL75, 10);
        int m4054RL81 = m4054RL(m4054RL76 + m4056f2(m4054RL79, m4054RL77, m4054RL80) + this.f9259X[12] + 1518500249, 7) + m4054RL78;
        int m4054RL82 = m4054RL(m4054RL77, 10);
        int m4054RL83 = m4054RL(m4054RL78 + m4056f2(m4054RL81, m4054RL79, m4054RL82) + this.f9259X[0] + 1518500249, 12) + m4054RL80;
        int m4054RL84 = m4054RL(m4054RL79, 10);
        int m4054RL85 = m4054RL(m4054RL80 + m4056f2(m4054RL83, m4054RL81, m4054RL84) + this.f9259X[9] + 1518500249, 15) + m4054RL82;
        int m4054RL86 = m4054RL(m4054RL81, 10);
        int m4054RL87 = m4054RL(m4054RL82 + m4056f2(m4054RL85, m4054RL83, m4054RL86) + this.f9259X[5] + 1518500249, 9) + m4054RL84;
        int m4054RL88 = m4054RL(m4054RL83, 10);
        int m4054RL89 = m4054RL(m4054RL84 + m4056f2(m4054RL87, m4054RL85, m4054RL88) + this.f9259X[2] + 1518500249, 11) + m4054RL86;
        int m4054RL90 = m4054RL(m4054RL85, 10);
        int m4054RL91 = m4054RL(m4054RL86 + m4056f2(m4054RL89, m4054RL87, m4054RL90) + this.f9259X[14] + 1518500249, 7) + m4054RL88;
        int m4054RL92 = m4054RL(m4054RL87, 10);
        int m4054RL93 = m4054RL(m4054RL88 + m4056f2(m4054RL91, m4054RL89, m4054RL92) + this.f9259X[11] + 1518500249, 13) + m4054RL90;
        int m4054RL94 = m4054RL(m4054RL89, 10);
        int m4054RL95 = m4054RL(m4054RL90 + m4056f2(m4054RL93, m4054RL91, m4054RL94) + this.f9259X[8] + 1518500249, 12) + m4054RL92;
        int m4054RL96 = m4054RL(m4054RL91, 10);
        int m4054RL97 = m4054RL(m4054RL60 + m4058f4(m4054RL31, m4054RL61, m4054RL64) + this.f9259X[6] + 1548603684, 9) + m4054RL62;
        int m4054RL98 = m4054RL(m4054RL61, 10);
        int m4054RL99 = m4054RL(m4054RL62 + m4058f4(m4054RL97, m4054RL31, m4054RL98) + this.f9259X[11] + 1548603684, 13) + m4054RL64;
        int m4054RL100 = m4054RL(m4054RL31, 10);
        int m4054RL101 = m4054RL(m4054RL64 + m4058f4(m4054RL99, m4054RL97, m4054RL100) + this.f9259X[3] + 1548603684, 15) + m4054RL98;
        int m4054RL102 = m4054RL(m4054RL97, 10);
        int m4054RL103 = m4054RL(m4054RL98 + m4058f4(m4054RL101, m4054RL99, m4054RL102) + this.f9259X[7] + 1548603684, 7) + m4054RL100;
        int m4054RL104 = m4054RL(m4054RL99, 10);
        int m4054RL105 = m4054RL(m4054RL100 + m4058f4(m4054RL103, m4054RL101, m4054RL104) + this.f9259X[0] + 1548603684, 12) + m4054RL102;
        int m4054RL106 = m4054RL(m4054RL101, 10);
        int m4054RL107 = m4054RL(m4054RL102 + m4058f4(m4054RL105, m4054RL103, m4054RL106) + this.f9259X[13] + 1548603684, 8) + m4054RL104;
        int m4054RL108 = m4054RL(m4054RL103, 10);
        int m4054RL109 = m4054RL(m4054RL104 + m4058f4(m4054RL107, m4054RL105, m4054RL108) + this.f9259X[5] + 1548603684, 9) + m4054RL106;
        int m4054RL110 = m4054RL(m4054RL105, 10);
        int m4054RL111 = m4054RL(m4054RL106 + m4058f4(m4054RL109, m4054RL107, m4054RL110) + this.f9259X[10] + 1548603684, 11) + m4054RL108;
        int m4054RL112 = m4054RL(m4054RL107, 10);
        int m4054RL113 = m4054RL(m4054RL108 + m4058f4(m4054RL111, m4054RL109, m4054RL112) + this.f9259X[14] + 1548603684, 7) + m4054RL110;
        int m4054RL114 = m4054RL(m4054RL109, 10);
        int m4054RL115 = m4054RL(m4054RL110 + m4058f4(m4054RL113, m4054RL111, m4054RL114) + this.f9259X[15] + 1548603684, 7) + m4054RL112;
        int m4054RL116 = m4054RL(m4054RL111, 10);
        int m4054RL117 = m4054RL(m4054RL112 + m4058f4(m4054RL115, m4054RL113, m4054RL116) + this.f9259X[8] + 1548603684, 12) + m4054RL114;
        int m4054RL118 = m4054RL(m4054RL113, 10);
        int m4054RL119 = m4054RL(m4054RL114 + m4058f4(m4054RL117, m4054RL115, m4054RL118) + this.f9259X[12] + 1548603684, 7) + m4054RL116;
        int m4054RL120 = m4054RL(m4054RL115, 10);
        int m4054RL121 = m4054RL(m4054RL116 + m4058f4(m4054RL119, m4054RL117, m4054RL120) + this.f9259X[4] + 1548603684, 6) + m4054RL118;
        int m4054RL122 = m4054RL(m4054RL117, 10);
        int m4054RL123 = m4054RL(m4054RL118 + m4058f4(m4054RL121, m4054RL119, m4054RL122) + this.f9259X[9] + 1548603684, 15) + m4054RL120;
        int m4054RL124 = m4054RL(m4054RL119, 10);
        int m4054RL125 = m4054RL(m4054RL120 + m4058f4(m4054RL123, m4054RL121, m4054RL124) + this.f9259X[1] + 1548603684, 13) + m4054RL122;
        int m4054RL126 = m4054RL(m4054RL121, 10);
        int m4054RL127 = m4054RL(m4054RL122 + m4058f4(m4054RL125, m4054RL123, m4054RL126) + this.f9259X[2] + 1548603684, 11) + m4054RL124;
        int m4054RL128 = m4054RL(m4054RL123, 10);
        int m4054RL129 = m4054RL(m4054RL92 + m4057f3(m4054RL95, m4054RL93, m4054RL128) + this.f9259X[3] + 1859775393, 11) + m4054RL94;
        int m4054RL130 = m4054RL(m4054RL93, 10);
        int m4054RL131 = m4054RL(m4054RL94 + m4057f3(m4054RL129, m4054RL95, m4054RL130) + this.f9259X[10] + 1859775393, 13) + m4054RL128;
        int m4054RL132 = m4054RL(m4054RL95, 10);
        int m4054RL133 = m4054RL(m4054RL128 + m4057f3(m4054RL131, m4054RL129, m4054RL132) + this.f9259X[14] + 1859775393, 6) + m4054RL130;
        int m4054RL134 = m4054RL(m4054RL129, 10);
        int m4054RL135 = m4054RL(m4054RL130 + m4057f3(m4054RL133, m4054RL131, m4054RL134) + this.f9259X[4] + 1859775393, 7) + m4054RL132;
        int m4054RL136 = m4054RL(m4054RL131, 10);
        int m4054RL137 = m4054RL(m4054RL132 + m4057f3(m4054RL135, m4054RL133, m4054RL136) + this.f9259X[9] + 1859775393, 14) + m4054RL134;
        int m4054RL138 = m4054RL(m4054RL133, 10);
        int m4054RL139 = m4054RL(m4054RL134 + m4057f3(m4054RL137, m4054RL135, m4054RL138) + this.f9259X[15] + 1859775393, 9) + m4054RL136;
        int m4054RL140 = m4054RL(m4054RL135, 10);
        int m4054RL141 = m4054RL(m4054RL136 + m4057f3(m4054RL139, m4054RL137, m4054RL140) + this.f9259X[8] + 1859775393, 13) + m4054RL138;
        int m4054RL142 = m4054RL(m4054RL137, 10);
        int m4054RL143 = m4054RL(m4054RL138 + m4057f3(m4054RL141, m4054RL139, m4054RL142) + this.f9259X[1] + 1859775393, 15) + m4054RL140;
        int m4054RL144 = m4054RL(m4054RL139, 10);
        int m4054RL145 = m4054RL(m4054RL140 + m4057f3(m4054RL143, m4054RL141, m4054RL144) + this.f9259X[2] + 1859775393, 14) + m4054RL142;
        int m4054RL146 = m4054RL(m4054RL141, 10);
        int m4054RL147 = m4054RL(m4054RL142 + m4057f3(m4054RL145, m4054RL143, m4054RL146) + this.f9259X[7] + 1859775393, 8) + m4054RL144;
        int m4054RL148 = m4054RL(m4054RL143, 10);
        int m4054RL149 = m4054RL(m4054RL144 + m4057f3(m4054RL147, m4054RL145, m4054RL148) + this.f9259X[0] + 1859775393, 13) + m4054RL146;
        int m4054RL150 = m4054RL(m4054RL145, 10);
        int m4054RL151 = m4054RL(m4054RL146 + m4057f3(m4054RL149, m4054RL147, m4054RL150) + this.f9259X[6] + 1859775393, 6) + m4054RL148;
        int m4054RL152 = m4054RL(m4054RL147, 10);
        int m4054RL153 = m4054RL(m4054RL148 + m4057f3(m4054RL151, m4054RL149, m4054RL152) + this.f9259X[13] + 1859775393, 5) + m4054RL150;
        int m4054RL154 = m4054RL(m4054RL149, 10);
        int m4054RL155 = m4054RL(m4054RL150 + m4057f3(m4054RL153, m4054RL151, m4054RL154) + this.f9259X[11] + 1859775393, 12) + m4054RL152;
        int m4054RL156 = m4054RL(m4054RL151, 10);
        int m4054RL157 = m4054RL(m4054RL152 + m4057f3(m4054RL155, m4054RL153, m4054RL156) + this.f9259X[5] + 1859775393, 7) + m4054RL154;
        int m4054RL158 = m4054RL(m4054RL153, 10);
        int m4054RL159 = m4054RL(m4054RL154 + m4057f3(m4054RL157, m4054RL155, m4054RL158) + this.f9259X[12] + 1859775393, 5) + m4054RL156;
        int m4054RL160 = m4054RL(m4054RL155, 10);
        int m4054RL161 = m4054RL(m4054RL124 + m4057f3(m4054RL127, m4054RL125, m4054RL96) + this.f9259X[15] + 1836072691, 9) + m4054RL126;
        int m4054RL162 = m4054RL(m4054RL125, 10);
        int m4054RL163 = m4054RL(m4054RL126 + m4057f3(m4054RL161, m4054RL127, m4054RL162) + this.f9259X[5] + 1836072691, 7) + m4054RL96;
        int m4054RL164 = m4054RL(m4054RL127, 10);
        int m4054RL165 = m4054RL(m4054RL96 + m4057f3(m4054RL163, m4054RL161, m4054RL164) + this.f9259X[1] + 1836072691, 15) + m4054RL162;
        int m4054RL166 = m4054RL(m4054RL161, 10);
        int m4054RL167 = m4054RL(m4054RL162 + m4057f3(m4054RL165, m4054RL163, m4054RL166) + this.f9259X[3] + 1836072691, 11) + m4054RL164;
        int m4054RL168 = m4054RL(m4054RL163, 10);
        int m4054RL169 = m4054RL(m4054RL164 + m4057f3(m4054RL167, m4054RL165, m4054RL168) + this.f9259X[7] + 1836072691, 8) + m4054RL166;
        int m4054RL170 = m4054RL(m4054RL165, 10);
        int m4054RL171 = m4054RL(m4054RL166 + m4057f3(m4054RL169, m4054RL167, m4054RL170) + this.f9259X[14] + 1836072691, 6) + m4054RL168;
        int m4054RL172 = m4054RL(m4054RL167, 10);
        int m4054RL173 = m4054RL(m4054RL168 + m4057f3(m4054RL171, m4054RL169, m4054RL172) + this.f9259X[6] + 1836072691, 6) + m4054RL170;
        int m4054RL174 = m4054RL(m4054RL169, 10);
        int m4054RL175 = m4054RL(m4054RL170 + m4057f3(m4054RL173, m4054RL171, m4054RL174) + this.f9259X[9] + 1836072691, 14) + m4054RL172;
        int m4054RL176 = m4054RL(m4054RL171, 10);
        int m4054RL177 = m4054RL(m4054RL172 + m4057f3(m4054RL175, m4054RL173, m4054RL176) + this.f9259X[11] + 1836072691, 12) + m4054RL174;
        int m4054RL178 = m4054RL(m4054RL173, 10);
        int m4054RL179 = m4054RL(m4054RL174 + m4057f3(m4054RL177, m4054RL175, m4054RL178) + this.f9259X[8] + 1836072691, 13) + m4054RL176;
        int m4054RL180 = m4054RL(m4054RL175, 10);
        int m4054RL181 = m4054RL(m4054RL176 + m4057f3(m4054RL179, m4054RL177, m4054RL180) + this.f9259X[12] + 1836072691, 5) + m4054RL178;
        int m4054RL182 = m4054RL(m4054RL177, 10);
        int m4054RL183 = m4054RL(m4054RL178 + m4057f3(m4054RL181, m4054RL179, m4054RL182) + this.f9259X[2] + 1836072691, 14) + m4054RL180;
        int m4054RL184 = m4054RL(m4054RL179, 10);
        int m4054RL185 = m4054RL(m4054RL180 + m4057f3(m4054RL183, m4054RL181, m4054RL184) + this.f9259X[10] + 1836072691, 13) + m4054RL182;
        int m4054RL186 = m4054RL(m4054RL181, 10);
        int m4054RL187 = m4054RL(m4054RL182 + m4057f3(m4054RL185, m4054RL183, m4054RL186) + this.f9259X[0] + 1836072691, 13) + m4054RL184;
        int m4054RL188 = m4054RL(m4054RL183, 10);
        int m4054RL189 = m4054RL(m4054RL184 + m4057f3(m4054RL187, m4054RL185, m4054RL188) + this.f9259X[4] + 1836072691, 7) + m4054RL186;
        int m4054RL190 = m4054RL(m4054RL185, 10);
        int m4054RL191 = m4054RL(m4054RL186 + m4057f3(m4054RL189, m4054RL187, m4054RL190) + this.f9259X[13] + 1836072691, 5) + m4054RL188;
        int m4054RL192 = m4054RL(m4054RL187, 10);
        int m4054RL193 = m4054RL(((m4054RL188 + m4058f4(m4054RL159, m4054RL157, m4054RL160)) + this.f9259X[1]) - 1894007588, 11) + m4054RL158;
        int m4054RL194 = m4054RL(m4054RL157, 10);
        int m4054RL195 = m4054RL(((m4054RL158 + m4058f4(m4054RL193, m4054RL159, m4054RL194)) + this.f9259X[9]) - 1894007588, 12) + m4054RL160;
        int m4054RL196 = m4054RL(m4054RL159, 10);
        int m4054RL197 = m4054RL(((m4054RL160 + m4058f4(m4054RL195, m4054RL193, m4054RL196)) + this.f9259X[11]) - 1894007588, 14) + m4054RL194;
        int m4054RL198 = m4054RL(m4054RL193, 10);
        int m4054RL199 = m4054RL(((m4054RL194 + m4058f4(m4054RL197, m4054RL195, m4054RL198)) + this.f9259X[10]) - 1894007588, 15) + m4054RL196;
        int m4054RL200 = m4054RL(m4054RL195, 10);
        int m4054RL201 = m4054RL(((m4054RL196 + m4058f4(m4054RL199, m4054RL197, m4054RL200)) + this.f9259X[0]) - 1894007588, 14) + m4054RL198;
        int m4054RL202 = m4054RL(m4054RL197, 10);
        int m4054RL203 = m4054RL(((m4054RL198 + m4058f4(m4054RL201, m4054RL199, m4054RL202)) + this.f9259X[8]) - 1894007588, 15) + m4054RL200;
        int m4054RL204 = m4054RL(m4054RL199, 10);
        int m4054RL205 = m4054RL(((m4054RL200 + m4058f4(m4054RL203, m4054RL201, m4054RL204)) + this.f9259X[12]) - 1894007588, 9) + m4054RL202;
        int m4054RL206 = m4054RL(m4054RL201, 10);
        int m4054RL207 = m4054RL(((m4054RL202 + m4058f4(m4054RL205, m4054RL203, m4054RL206)) + this.f9259X[4]) - 1894007588, 8) + m4054RL204;
        int m4054RL208 = m4054RL(m4054RL203, 10);
        int m4054RL209 = m4054RL(((m4054RL204 + m4058f4(m4054RL207, m4054RL205, m4054RL208)) + this.f9259X[13]) - 1894007588, 9) + m4054RL206;
        int m4054RL210 = m4054RL(m4054RL205, 10);
        int m4054RL211 = m4054RL(((m4054RL206 + m4058f4(m4054RL209, m4054RL207, m4054RL210)) + this.f9259X[3]) - 1894007588, 14) + m4054RL208;
        int m4054RL212 = m4054RL(m4054RL207, 10);
        int m4054RL213 = m4054RL(((m4054RL208 + m4058f4(m4054RL211, m4054RL209, m4054RL212)) + this.f9259X[7]) - 1894007588, 5) + m4054RL210;
        int m4054RL214 = m4054RL(m4054RL209, 10);
        int m4054RL215 = m4054RL(((m4054RL210 + m4058f4(m4054RL213, m4054RL211, m4054RL214)) + this.f9259X[15]) - 1894007588, 6) + m4054RL212;
        int m4054RL216 = m4054RL(m4054RL211, 10);
        int m4054RL217 = m4054RL(((m4054RL212 + m4058f4(m4054RL215, m4054RL213, m4054RL216)) + this.f9259X[14]) - 1894007588, 8) + m4054RL214;
        int m4054RL218 = m4054RL(m4054RL213, 10);
        int m4054RL219 = m4054RL(((m4054RL214 + m4058f4(m4054RL217, m4054RL215, m4054RL218)) + this.f9259X[5]) - 1894007588, 6) + m4054RL216;
        int m4054RL220 = m4054RL(m4054RL215, 10);
        int m4054RL221 = m4054RL(((m4054RL216 + m4058f4(m4054RL219, m4054RL217, m4054RL220)) + this.f9259X[6]) - 1894007588, 5) + m4054RL218;
        int m4054RL222 = m4054RL(m4054RL217, 10);
        int m4054RL223 = m4054RL(((m4054RL218 + m4058f4(m4054RL221, m4054RL219, m4054RL222)) + this.f9259X[2]) - 1894007588, 12) + m4054RL220;
        int m4054RL224 = m4054RL(m4054RL219, 10);
        int m4054RL225 = m4054RL(m4054RL156 + m4056f2(m4054RL191, m4054RL189, m4054RL192) + this.f9259X[8] + 2053994217, 15) + m4054RL190;
        int m4054RL226 = m4054RL(m4054RL189, 10);
        int m4054RL227 = m4054RL(m4054RL190 + m4056f2(m4054RL225, m4054RL191, m4054RL226) + this.f9259X[6] + 2053994217, 5) + m4054RL192;
        int m4054RL228 = m4054RL(m4054RL191, 10);
        int m4054RL229 = m4054RL(m4054RL192 + m4056f2(m4054RL227, m4054RL225, m4054RL228) + this.f9259X[4] + 2053994217, 8) + m4054RL226;
        int m4054RL230 = m4054RL(m4054RL225, 10);
        int m4054RL231 = m4054RL(m4054RL226 + m4056f2(m4054RL229, m4054RL227, m4054RL230) + this.f9259X[1] + 2053994217, 11) + m4054RL228;
        int m4054RL232 = m4054RL(m4054RL227, 10);
        int m4054RL233 = m4054RL(m4054RL228 + m4056f2(m4054RL231, m4054RL229, m4054RL232) + this.f9259X[3] + 2053994217, 14) + m4054RL230;
        int m4054RL234 = m4054RL(m4054RL229, 10);
        int m4054RL235 = m4054RL(m4054RL230 + m4056f2(m4054RL233, m4054RL231, m4054RL234) + this.f9259X[11] + 2053994217, 14) + m4054RL232;
        int m4054RL236 = m4054RL(m4054RL231, 10);
        int m4054RL237 = m4054RL(m4054RL232 + m4056f2(m4054RL235, m4054RL233, m4054RL236) + this.f9259X[15] + 2053994217, 6) + m4054RL234;
        int m4054RL238 = m4054RL(m4054RL233, 10);
        int m4054RL239 = m4054RL(m4054RL234 + m4056f2(m4054RL237, m4054RL235, m4054RL238) + this.f9259X[0] + 2053994217, 14) + m4054RL236;
        int m4054RL240 = m4054RL(m4054RL235, 10);
        int m4054RL241 = m4054RL(m4054RL236 + m4056f2(m4054RL239, m4054RL237, m4054RL240) + this.f9259X[5] + 2053994217, 6) + m4054RL238;
        int m4054RL242 = m4054RL(m4054RL237, 10);
        int m4054RL243 = m4054RL(m4054RL238 + m4056f2(m4054RL241, m4054RL239, m4054RL242) + this.f9259X[12] + 2053994217, 9) + m4054RL240;
        int m4054RL244 = m4054RL(m4054RL239, 10);
        int m4054RL245 = m4054RL(m4054RL240 + m4056f2(m4054RL243, m4054RL241, m4054RL244) + this.f9259X[2] + 2053994217, 12) + m4054RL242;
        int m4054RL246 = m4054RL(m4054RL241, 10);
        int m4054RL247 = m4054RL(m4054RL242 + m4056f2(m4054RL245, m4054RL243, m4054RL246) + this.f9259X[13] + 2053994217, 9) + m4054RL244;
        int m4054RL248 = m4054RL(m4054RL243, 10);
        int m4054RL249 = m4054RL(m4054RL244 + m4056f2(m4054RL247, m4054RL245, m4054RL248) + this.f9259X[9] + 2053994217, 12) + m4054RL246;
        int m4054RL250 = m4054RL(m4054RL245, 10);
        int m4054RL251 = m4054RL(m4054RL246 + m4056f2(m4054RL249, m4054RL247, m4054RL250) + this.f9259X[7] + 2053994217, 5) + m4054RL248;
        int m4054RL252 = m4054RL(m4054RL247, 10);
        int m4054RL253 = m4054RL(m4054RL248 + m4056f2(m4054RL251, m4054RL249, m4054RL252) + this.f9259X[10] + 2053994217, 15) + m4054RL250;
        int m4054RL254 = m4054RL(m4054RL249, 10);
        int m4054RL255 = m4054RL(m4054RL250 + m4056f2(m4054RL253, m4054RL251, m4054RL254) + this.f9259X[14] + 2053994217, 8) + m4054RL252;
        int m4054RL256 = m4054RL(m4054RL251, 10);
        int m4054RL257 = m4054RL(((m4054RL220 + m4059f5(m4054RL223, m4054RL253, m4054RL224)) + this.f9259X[4]) - 1454113458, 9) + m4054RL222;
        int m4054RL258 = m4054RL(m4054RL253, 10);
        int m4054RL259 = m4054RL(((m4054RL222 + m4059f5(m4054RL257, m4054RL223, m4054RL258)) + this.f9259X[0]) - 1454113458, 15) + m4054RL224;
        int m4054RL260 = m4054RL(m4054RL223, 10);
        int m4054RL261 = m4054RL(((m4054RL224 + m4059f5(m4054RL259, m4054RL257, m4054RL260)) + this.f9259X[5]) - 1454113458, 5) + m4054RL258;
        int m4054RL262 = m4054RL(m4054RL257, 10);
        int m4054RL263 = m4054RL(((m4054RL258 + m4059f5(m4054RL261, m4054RL259, m4054RL262)) + this.f9259X[9]) - 1454113458, 11) + m4054RL260;
        int m4054RL264 = m4054RL(m4054RL259, 10);
        int m4054RL265 = m4054RL(((m4054RL260 + m4059f5(m4054RL263, m4054RL261, m4054RL264)) + this.f9259X[7]) - 1454113458, 6) + m4054RL262;
        int m4054RL266 = m4054RL(m4054RL261, 10);
        int m4054RL267 = m4054RL(((m4054RL262 + m4059f5(m4054RL265, m4054RL263, m4054RL266)) + this.f9259X[12]) - 1454113458, 8) + m4054RL264;
        int m4054RL268 = m4054RL(m4054RL263, 10);
        int m4054RL269 = m4054RL(((m4054RL264 + m4059f5(m4054RL267, m4054RL265, m4054RL268)) + this.f9259X[2]) - 1454113458, 13) + m4054RL266;
        int m4054RL270 = m4054RL(m4054RL265, 10);
        int m4054RL271 = m4054RL(((m4054RL266 + m4059f5(m4054RL269, m4054RL267, m4054RL270)) + this.f9259X[10]) - 1454113458, 12) + m4054RL268;
        int m4054RL272 = m4054RL(m4054RL267, 10);
        int m4054RL273 = m4054RL(((m4054RL268 + m4059f5(m4054RL271, m4054RL269, m4054RL272)) + this.f9259X[14]) - 1454113458, 5) + m4054RL270;
        int m4054RL274 = m4054RL(m4054RL269, 10);
        int m4054RL275 = m4054RL(((m4054RL270 + m4059f5(m4054RL273, m4054RL271, m4054RL274)) + this.f9259X[1]) - 1454113458, 12) + m4054RL272;
        int m4054RL276 = m4054RL(m4054RL271, 10);
        int m4054RL277 = m4054RL(((m4054RL272 + m4059f5(m4054RL275, m4054RL273, m4054RL276)) + this.f9259X[3]) - 1454113458, 13) + m4054RL274;
        int m4054RL278 = m4054RL(m4054RL273, 10);
        int m4054RL279 = m4054RL(((m4054RL274 + m4059f5(m4054RL277, m4054RL275, m4054RL278)) + this.f9259X[8]) - 1454113458, 14) + m4054RL276;
        int m4054RL280 = m4054RL(m4054RL275, 10);
        int m4054RL281 = m4054RL(((m4054RL276 + m4059f5(m4054RL279, m4054RL277, m4054RL280)) + this.f9259X[11]) - 1454113458, 11) + m4054RL278;
        int m4054RL282 = m4054RL(m4054RL277, 10);
        int m4054RL283 = m4054RL(((m4054RL278 + m4059f5(m4054RL281, m4054RL279, m4054RL282)) + this.f9259X[6]) - 1454113458, 8) + m4054RL280;
        int m4054RL284 = m4054RL(m4054RL279, 10);
        int m4054RL285 = m4054RL(((m4054RL280 + m4059f5(m4054RL283, m4054RL281, m4054RL284)) + this.f9259X[15]) - 1454113458, 5) + m4054RL282;
        int m4054RL286 = m4054RL(m4054RL281, 10);
        int m4054RL287 = m4054RL(((m4054RL282 + m4059f5(m4054RL285, m4054RL283, m4054RL286)) + this.f9259X[13]) - 1454113458, 6) + m4054RL284;
        int m4054RL288 = m4054RL(m4054RL283, 10);
        int m4054RL289 = m4054RL(m4054RL252 + m4055f1(m4054RL255, m4054RL221, m4054RL256) + this.f9259X[12], 8) + m4054RL254;
        int m4054RL290 = m4054RL(m4054RL221, 10);
        int m4054RL291 = m4054RL(m4054RL254 + m4055f1(m4054RL289, m4054RL255, m4054RL290) + this.f9259X[15], 5) + m4054RL256;
        int m4054RL292 = m4054RL(m4054RL255, 10);
        int m4054RL293 = m4054RL(m4054RL256 + m4055f1(m4054RL291, m4054RL289, m4054RL292) + this.f9259X[10], 12) + m4054RL290;
        int m4054RL294 = m4054RL(m4054RL289, 10);
        int m4054RL295 = m4054RL(m4054RL290 + m4055f1(m4054RL293, m4054RL291, m4054RL294) + this.f9259X[4], 9) + m4054RL292;
        int m4054RL296 = m4054RL(m4054RL291, 10);
        int m4054RL297 = m4054RL(m4054RL292 + m4055f1(m4054RL295, m4054RL293, m4054RL296) + this.f9259X[1], 12) + m4054RL294;
        int m4054RL298 = m4054RL(m4054RL293, 10);
        int m4054RL299 = m4054RL(m4054RL294 + m4055f1(m4054RL297, m4054RL295, m4054RL298) + this.f9259X[5], 5) + m4054RL296;
        int m4054RL300 = m4054RL(m4054RL295, 10);
        int m4054RL301 = m4054RL(m4054RL296 + m4055f1(m4054RL299, m4054RL297, m4054RL300) + this.f9259X[8], 14) + m4054RL298;
        int m4054RL302 = m4054RL(m4054RL297, 10);
        int m4054RL303 = m4054RL(m4054RL298 + m4055f1(m4054RL301, m4054RL299, m4054RL302) + this.f9259X[7], 6) + m4054RL300;
        int m4054RL304 = m4054RL(m4054RL299, 10);
        int m4054RL305 = m4054RL(m4054RL300 + m4055f1(m4054RL303, m4054RL301, m4054RL304) + this.f9259X[6], 8) + m4054RL302;
        int m4054RL306 = m4054RL(m4054RL301, 10);
        int m4054RL307 = m4054RL(m4054RL302 + m4055f1(m4054RL305, m4054RL303, m4054RL306) + this.f9259X[2], 13) + m4054RL304;
        int m4054RL308 = m4054RL(m4054RL303, 10);
        int m4054RL309 = m4054RL(m4054RL304 + m4055f1(m4054RL307, m4054RL305, m4054RL308) + this.f9259X[13], 6) + m4054RL306;
        int m4054RL310 = m4054RL(m4054RL305, 10);
        int m4054RL311 = m4054RL(m4054RL306 + m4055f1(m4054RL309, m4054RL307, m4054RL310) + this.f9259X[14], 5) + m4054RL308;
        int m4054RL312 = m4054RL(m4054RL307, 10);
        int m4054RL313 = m4054RL(m4054RL308 + m4055f1(m4054RL311, m4054RL309, m4054RL312) + this.f9259X[0], 15) + m4054RL310;
        int m4054RL314 = m4054RL(m4054RL309, 10);
        int m4054RL315 = m4054RL(m4054RL310 + m4055f1(m4054RL313, m4054RL311, m4054RL314) + this.f9259X[3], 13) + m4054RL312;
        int m4054RL316 = m4054RL(m4054RL311, 10);
        int m4054RL317 = m4054RL(m4054RL312 + m4055f1(m4054RL315, m4054RL313, m4054RL316) + this.f9259X[9], 11) + m4054RL314;
        int m4054RL318 = m4054RL(m4054RL313, 10);
        int m4054RL319 = m4054RL(m4054RL314 + m4055f1(m4054RL317, m4054RL315, m4054RL318) + this.f9259X[11], 11) + m4054RL316;
        int m4054RL320 = m4054RL(m4054RL315, 10);
        this.f9249H0 += m4054RL284;
        this.f9250H1 += m4054RL287;
        this.f9251H2 += m4054RL285;
        this.f9252H3 += m4054RL288;
        this.f9253H4 += m4054RL318;
        this.f9254H5 += m4054RL316;
        this.f9255H6 += m4054RL319;
        this.f9256H7 += m4054RL317;
        this.f9257H8 += m4054RL320;
        this.f9258H9 += m4054RL286;
        int i11 = 0;
        this.xOff = 0;
        while (true) {
            int[] iArr = this.f9259X;
            if (i11 == iArr.length) {
                return;
            }
            iArr[i11] = 0;
            i11++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f9259X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f9259X;
        int i2 = this.xOff;
        this.xOff = i2 + 1;
        iArr[i2] = ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        if (this.xOff == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f9249H0 = 1732584193;
        this.f9250H1 = -271733879;
        this.f9251H2 = -1732584194;
        this.f9252H3 = 271733878;
        this.f9253H4 = -1009589776;
        this.f9254H5 = 1985229328;
        this.f9255H6 = -19088744;
        this.f9256H7 = -1985229329;
        this.f9257H8 = 19088743;
        this.f9258H9 = 1009589775;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f9259X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        doCopy((RIPEMD320Digest) memoable);
    }
}
