package org.bouncycastle.math.ec;

import java.math.BigInteger;

/* loaded from: classes9.dex */
public abstract class WNafUtil {
    private static final int[] DEFAULT_WINDOW_SIZE_CUTOFFS = {13, 41, 121, 337, 897, 2305};
    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final int[] EMPTY_INTS = new int[0];
    private static final ECPoint[] EMPTY_POINTS = new ECPoint[0];
    private static final int MAX_WIDTH = 16;
    public static final String PRECOMP_NAME = "bc_wnaf";

    public static void configureBasepoint(ECPoint eCPoint) {
        ECCurve curve = eCPoint.getCurve();
        if (curve == null) {
            return;
        }
        BigInteger order = curve.getOrder();
        final int min = Math.min(16, getWindowSize(order == null ? curve.getFieldSize() + 1 : order.bitLength()) + 3);
        curve.precompute(eCPoint, PRECOMP_NAME, new PreCompCallback() { // from class: org.bouncycastle.math.ec.WNafUtil.1
            @Override // org.bouncycastle.math.ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo preCompInfo) {
                WNafPreCompInfo wNafPreCompInfo = preCompInfo instanceof WNafPreCompInfo ? (WNafPreCompInfo) preCompInfo : null;
                if (wNafPreCompInfo != null && wNafPreCompInfo.getConfWidth() == min) {
                    wNafPreCompInfo.setPromotionCountdown(0);
                    return wNafPreCompInfo;
                }
                WNafPreCompInfo wNafPreCompInfo2 = new WNafPreCompInfo();
                wNafPreCompInfo2.setPromotionCountdown(0);
                wNafPreCompInfo2.setConfWidth(min);
                if (wNafPreCompInfo != null) {
                    wNafPreCompInfo2.setPreComp(wNafPreCompInfo.getPreComp());
                    wNafPreCompInfo2.setPreCompNeg(wNafPreCompInfo.getPreCompNeg());
                    wNafPreCompInfo2.setTwice(wNafPreCompInfo.getTwice());
                    wNafPreCompInfo2.setWidth(wNafPreCompInfo.getWidth());
                }
                return wNafPreCompInfo2;
            }
        });
    }

    public static int[] generateCompactNaf(BigInteger bigInteger) {
        if ((bigInteger.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        }
        if (bigInteger.signum() == 0) {
            return EMPTY_INTS;
        }
        BigInteger add = bigInteger.shiftLeft(1).add(bigInteger);
        int bitLength = add.bitLength();
        int[] iArr = new int[bitLength >> 1];
        BigInteger xor = add.xor(bigInteger);
        int i = bitLength - 1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i4 < i) {
            if (xor.testBit(i4)) {
                iArr[i2] = i3 | ((bigInteger.testBit(i4) ? -1 : 1) << 16);
                i4++;
                i3 = 1;
                i2++;
            } else {
                i3++;
            }
            i4++;
        }
        int i5 = i2 + 1;
        iArr[i2] = 65536 | i3;
        return iArr.length > i5 ? trim(iArr, i5) : iArr;
    }

    public static int[] generateCompactWindowNaf(int i, BigInteger bigInteger) {
        if (i == 2) {
            return generateCompactNaf(bigInteger);
        }
        if (i < 2 || i > 16) {
            throw new IllegalArgumentException("'width' must be in the range [2, 16]");
        }
        if ((bigInteger.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        }
        if (bigInteger.signum() == 0) {
            return EMPTY_INTS;
        }
        int[] iArr = new int[(bigInteger.bitLength() / i) + 1];
        int i2 = 1 << i;
        int i3 = i2 - 1;
        int i4 = i2 >>> 1;
        BigInteger bigInteger2 = bigInteger;
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        while (i5 <= bigInteger2.bitLength()) {
            if (bigInteger2.testBit(i5) == z) {
                i5++;
            } else {
                bigInteger2 = bigInteger2.shiftRight(i5);
                int intValue = bigInteger2.intValue() & i3;
                if (z) {
                    intValue++;
                }
                z = (intValue & i4) != 0;
                if (z) {
                    intValue -= i2;
                }
                if (i6 > 0) {
                    i5--;
                }
                iArr[i6] = i5 | (intValue << 16);
                i5 = i;
                i6++;
            }
        }
        return iArr.length > i6 ? trim(iArr, i6) : iArr;
    }

    public static byte[] generateJSF(BigInteger bigInteger, BigInteger bigInteger2) {
        byte[] bArr = new byte[Math.max(bigInteger.bitLength(), bigInteger2.bitLength()) + 1];
        BigInteger bigInteger3 = bigInteger;
        BigInteger bigInteger4 = bigInteger2;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if ((i | i2) == 0 && bigInteger3.bitLength() <= i3 && bigInteger4.bitLength() <= i3) {
                break;
            }
            int intValue = ((bigInteger3.intValue() >>> i3) + i) & 7;
            int intValue2 = ((bigInteger4.intValue() >>> i3) + i2) & 7;
            int i5 = intValue & 1;
            if (i5 != 0) {
                i5 -= intValue & 2;
                if (intValue + i5 == 4 && (intValue2 & 3) == 2) {
                    i5 = -i5;
                }
            }
            int i6 = intValue2 & 1;
            if (i6 != 0) {
                i6 -= intValue2 & 2;
                if (intValue2 + i6 == 4 && (intValue & 3) == 2) {
                    i6 = -i6;
                }
            }
            if ((i << 1) == i5 + 1) {
                i ^= 1;
            }
            if ((i2 << 1) == i6 + 1) {
                i2 ^= 1;
            }
            i3++;
            if (i3 == 30) {
                bigInteger3 = bigInteger3.shiftRight(30);
                bigInteger4 = bigInteger4.shiftRight(30);
                i3 = 0;
            }
            bArr[i4] = (byte) ((i5 << 4) | (i6 & 15));
            i4++;
        }
        return bArr.length > i4 ? trim(bArr, i4) : bArr;
    }

    public static byte[] generateNaf(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return EMPTY_BYTES;
        }
        BigInteger add = bigInteger.shiftLeft(1).add(bigInteger);
        int bitLength = add.bitLength() - 1;
        byte[] bArr = new byte[bitLength];
        BigInteger xor = add.xor(bigInteger);
        int i = 1;
        while (i < bitLength) {
            if (xor.testBit(i)) {
                bArr[i - 1] = (byte) (bigInteger.testBit(i) ? -1 : 1);
                i++;
            }
            i++;
        }
        bArr[bitLength - 1] = 1;
        return bArr;
    }

    public static byte[] generateWindowNaf(int i, BigInteger bigInteger) {
        if (i == 2) {
            return generateNaf(bigInteger);
        }
        if (i < 2 || i > 8) {
            throw new IllegalArgumentException("'width' must be in the range [2, 8]");
        }
        if (bigInteger.signum() == 0) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[bigInteger.bitLength() + 1];
        int i2 = 1 << i;
        int i3 = i2 - 1;
        int i4 = i2 >>> 1;
        BigInteger bigInteger2 = bigInteger;
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        while (i5 <= bigInteger2.bitLength()) {
            if (bigInteger2.testBit(i5) == z) {
                i5++;
            } else {
                bigInteger2 = bigInteger2.shiftRight(i5);
                int intValue = bigInteger2.intValue() & i3;
                if (z) {
                    intValue++;
                }
                z = (intValue & i4) != 0;
                if (z) {
                    intValue -= i2;
                }
                if (i6 > 0) {
                    i5--;
                }
                int i7 = i6 + i5;
                bArr[i7] = (byte) intValue;
                i6 = i7 + 1;
                i5 = i;
            }
        }
        return bArr.length > i6 ? trim(bArr, i6) : bArr;
    }

    public static int getNafWeight(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return 0;
        }
        return bigInteger.shiftLeft(1).add(bigInteger).xor(bigInteger).bitCount();
    }

    public static WNafPreCompInfo getWNafPreCompInfo(ECPoint eCPoint) {
        return getWNafPreCompInfo(eCPoint.getCurve().getPreCompInfo(eCPoint, PRECOMP_NAME));
    }

    public static WNafPreCompInfo getWNafPreCompInfo(PreCompInfo preCompInfo) {
        if (preCompInfo instanceof WNafPreCompInfo) {
            return (WNafPreCompInfo) preCompInfo;
        }
        return null;
    }

    public static int getWindowSize(int i) {
        return getWindowSize(i, DEFAULT_WINDOW_SIZE_CUTOFFS, 16);
    }

    public static int getWindowSize(int i, int i2) {
        return getWindowSize(i, DEFAULT_WINDOW_SIZE_CUTOFFS, i2);
    }

    public static int getWindowSize(int i, int[] iArr) {
        return getWindowSize(i, iArr, 16);
    }

    public static int getWindowSize(int i, int[] iArr, int i2) {
        int i3 = 0;
        while (i3 < iArr.length && i >= iArr[i3]) {
            i3++;
        }
        return Math.max(2, Math.min(i2, i3 + 2));
    }

    public static ECPoint mapPointWithPrecomp(ECPoint eCPoint, int i, final boolean z, final ECPointMap eCPointMap) {
        ECCurve curve = eCPoint.getCurve();
        final WNafPreCompInfo precompute = precompute(eCPoint, i, z);
        ECPoint map = eCPointMap.map(eCPoint);
        curve.precompute(map, PRECOMP_NAME, new PreCompCallback() { // from class: org.bouncycastle.math.ec.WNafUtil.2
            @Override // org.bouncycastle.math.ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo preCompInfo) {
                WNafPreCompInfo wNafPreCompInfo = new WNafPreCompInfo();
                wNafPreCompInfo.setConfWidth(WNafPreCompInfo.this.getConfWidth());
                ECPoint twice = WNafPreCompInfo.this.getTwice();
                if (twice != null) {
                    wNafPreCompInfo.setTwice(eCPointMap.map(twice));
                }
                ECPoint[] preComp = WNafPreCompInfo.this.getPreComp();
                ECPoint[] eCPointArr = new ECPoint[preComp.length];
                for (int i2 = 0; i2 < preComp.length; i2++) {
                    eCPointArr[i2] = eCPointMap.map(preComp[i2]);
                }
                wNafPreCompInfo.setPreComp(eCPointArr);
                wNafPreCompInfo.setWidth(WNafPreCompInfo.this.getWidth());
                if (z) {
                    ECPoint[] eCPointArr2 = new ECPoint[eCPointArr.length];
                    for (int i3 = 0; i3 < eCPointArr2.length; i3++) {
                        eCPointArr2[i3] = eCPointArr[i3].negate();
                    }
                    wNafPreCompInfo.setPreCompNeg(eCPointArr2);
                }
                return wNafPreCompInfo;
            }
        });
        return map;
    }

    public static WNafPreCompInfo precompute(final ECPoint eCPoint, final int i, final boolean z) {
        final ECCurve curve = eCPoint.getCurve();
        return (WNafPreCompInfo) curve.precompute(eCPoint, PRECOMP_NAME, new PreCompCallback() { // from class: org.bouncycastle.math.ec.WNafUtil.3
            private boolean checkExisting(WNafPreCompInfo wNafPreCompInfo, int i2, int i3, boolean z2) {
                return wNafPreCompInfo != null && wNafPreCompInfo.getWidth() >= Math.max(wNafPreCompInfo.getConfWidth(), i2) && checkTable(wNafPreCompInfo.getPreComp(), i3) && (!z2 || checkTable(wNafPreCompInfo.getPreCompNeg(), i3));
            }

            private boolean checkTable(ECPoint[] eCPointArr, int i2) {
                return eCPointArr != null && eCPointArr.length >= i2;
            }

            /* JADX WARN: Removed duplicated region for block: B:43:0x00f2 A[LOOP:0: B:42:0x00f0->B:43:0x00f2, LOOP_END] */
            /* JADX WARN: Removed duplicated region for block: B:54:0x0117 A[LOOP:1: B:53:0x0115->B:54:0x0117, LOOP_END] */
            @Override // org.bouncycastle.math.ec.PreCompCallback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public PreCompInfo precompute(PreCompInfo preCompInfo) {
                ECPoint eCPoint2;
                ECPoint[] eCPointArr;
                ECPoint[] eCPointArr2;
                int length;
                ECPoint[] resizeTable;
                int i2;
                ECPoint eCPoint3;
                int coordinateSystem;
                ECFieldElement eCFieldElement = null;
                WNafPreCompInfo wNafPreCompInfo = preCompInfo instanceof WNafPreCompInfo ? (WNafPreCompInfo) preCompInfo : null;
                int max = Math.max(2, Math.min(16, i));
                if (checkExisting(wNafPreCompInfo, max, 1 << (max - 2), z)) {
                    wNafPreCompInfo.decrementPromotionCountdown();
                    return wNafPreCompInfo;
                }
                WNafPreCompInfo wNafPreCompInfo2 = new WNafPreCompInfo();
                if (wNafPreCompInfo != null) {
                    wNafPreCompInfo2.setPromotionCountdown(wNafPreCompInfo.decrementPromotionCountdown());
                    wNafPreCompInfo2.setConfWidth(wNafPreCompInfo.getConfWidth());
                    eCPointArr = wNafPreCompInfo.getPreComp();
                    eCPointArr2 = wNafPreCompInfo.getPreCompNeg();
                    eCPoint2 = wNafPreCompInfo.getTwice();
                } else {
                    eCPoint2 = null;
                    eCPointArr = null;
                    eCPointArr2 = null;
                }
                int min = Math.min(16, Math.max(wNafPreCompInfo2.getConfWidth(), max));
                int i3 = 1 << (min - 2);
                int i4 = 0;
                if (eCPointArr == null) {
                    eCPointArr = WNafUtil.EMPTY_POINTS;
                    length = 0;
                } else {
                    length = eCPointArr.length;
                }
                if (length < i3) {
                    eCPointArr = WNafUtil.resizeTable(eCPointArr, i3);
                    if (i3 == 1) {
                        eCPointArr[0] = eCPoint.normalize();
                    } else {
                        if (length == 0) {
                            eCPointArr[0] = eCPoint;
                            i2 = 1;
                        } else {
                            i2 = length;
                        }
                        if (i3 == 2) {
                            eCPointArr[1] = eCPoint.threeTimes();
                        } else {
                            ECPoint eCPoint4 = eCPointArr[i2 - 1];
                            if (eCPoint2 == null) {
                                eCPoint2 = eCPointArr[0].twice();
                                if (!eCPoint2.isInfinity() && ECAlgorithms.isFpCurve(curve) && curve.getFieldSize() >= 64 && ((coordinateSystem = curve.getCoordinateSystem()) == 2 || coordinateSystem == 3 || coordinateSystem == 4)) {
                                    eCFieldElement = eCPoint2.getZCoord(0);
                                    eCPoint3 = curve.createPoint(eCPoint2.getXCoord().toBigInteger(), eCPoint2.getYCoord().toBigInteger());
                                    ECFieldElement square = eCFieldElement.square();
                                    eCPoint4 = eCPoint4.scaleX(square).scaleY(square.multiply(eCFieldElement));
                                    if (length == 0) {
                                        eCPointArr[0] = eCPoint4;
                                    }
                                    while (i2 < i3) {
                                        eCPoint4 = eCPoint4.add(eCPoint3);
                                        eCPointArr[i2] = eCPoint4;
                                        i2++;
                                    }
                                }
                            }
                            eCPoint3 = eCPoint2;
                            while (i2 < i3) {
                            }
                        }
                        curve.normalizeAll(eCPointArr, length, i3 - length, eCFieldElement);
                    }
                }
                if (z) {
                    if (eCPointArr2 == null) {
                        resizeTable = new ECPoint[i3];
                    } else {
                        i4 = eCPointArr2.length;
                        if (i4 < i3) {
                            resizeTable = WNafUtil.resizeTable(eCPointArr2, i3);
                        }
                        while (i4 < i3) {
                            eCPointArr2[i4] = eCPointArr[i4].negate();
                            i4++;
                        }
                    }
                    eCPointArr2 = resizeTable;
                    while (i4 < i3) {
                    }
                }
                wNafPreCompInfo2.setPreComp(eCPointArr);
                wNafPreCompInfo2.setPreCompNeg(eCPointArr2);
                wNafPreCompInfo2.setTwice(eCPoint2);
                wNafPreCompInfo2.setWidth(min);
                return wNafPreCompInfo2;
            }
        });
    }

    public static WNafPreCompInfo precomputeWithPointMap(ECPoint eCPoint, final ECPointMap eCPointMap, final WNafPreCompInfo wNafPreCompInfo, final boolean z) {
        return (WNafPreCompInfo) eCPoint.getCurve().precompute(eCPoint, PRECOMP_NAME, new PreCompCallback() { // from class: org.bouncycastle.math.ec.WNafUtil.4
            private boolean checkExisting(WNafPreCompInfo wNafPreCompInfo2, int i, int i2, boolean z2) {
                return wNafPreCompInfo2 != null && wNafPreCompInfo2.getWidth() >= i && checkTable(wNafPreCompInfo2.getPreComp(), i2) && (!z2 || checkTable(wNafPreCompInfo2.getPreCompNeg(), i2));
            }

            private boolean checkTable(ECPoint[] eCPointArr, int i) {
                return eCPointArr != null && eCPointArr.length >= i;
            }

            @Override // org.bouncycastle.math.ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo preCompInfo) {
                WNafPreCompInfo wNafPreCompInfo2 = preCompInfo instanceof WNafPreCompInfo ? (WNafPreCompInfo) preCompInfo : null;
                int width = WNafPreCompInfo.this.getWidth();
                if (checkExisting(wNafPreCompInfo2, width, WNafPreCompInfo.this.getPreComp().length, z)) {
                    wNafPreCompInfo2.decrementPromotionCountdown();
                    return wNafPreCompInfo2;
                }
                WNafPreCompInfo wNafPreCompInfo3 = new WNafPreCompInfo();
                wNafPreCompInfo3.setPromotionCountdown(WNafPreCompInfo.this.getPromotionCountdown());
                ECPoint twice = WNafPreCompInfo.this.getTwice();
                if (twice != null) {
                    wNafPreCompInfo3.setTwice(eCPointMap.map(twice));
                }
                ECPoint[] preComp = WNafPreCompInfo.this.getPreComp();
                ECPoint[] eCPointArr = new ECPoint[preComp.length];
                for (int i = 0; i < preComp.length; i++) {
                    eCPointArr[i] = eCPointMap.map(preComp[i]);
                }
                wNafPreCompInfo3.setPreComp(eCPointArr);
                wNafPreCompInfo3.setWidth(width);
                if (z) {
                    ECPoint[] eCPointArr2 = new ECPoint[eCPointArr.length];
                    for (int i2 = 0; i2 < eCPointArr2.length; i2++) {
                        eCPointArr2[i2] = eCPointArr[i2].negate();
                    }
                    wNafPreCompInfo3.setPreCompNeg(eCPointArr2);
                }
                return wNafPreCompInfo3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ECPoint[] resizeTable(ECPoint[] eCPointArr, int i) {
        ECPoint[] eCPointArr2 = new ECPoint[i];
        System.arraycopy(eCPointArr, 0, eCPointArr2, 0, eCPointArr.length);
        return eCPointArr2;
    }

    private static byte[] trim(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    private static int[] trim(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        return iArr2;
    }
}
