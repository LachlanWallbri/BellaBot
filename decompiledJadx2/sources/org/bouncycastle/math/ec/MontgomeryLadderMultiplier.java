package org.bouncycastle.math.ec;

import java.math.BigInteger;

/* loaded from: classes9.dex */
public class MontgomeryLadderMultiplier extends AbstractECMultiplier {
    @Override // org.bouncycastle.math.ec.AbstractECMultiplier
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint[] eCPointArr = new ECPoint[2];
        eCPointArr[0] = eCPoint.getCurve().getInfinity();
        eCPointArr[1] = eCPoint;
        int bitLength = bigInteger.bitLength();
        while (true) {
            bitLength--;
            if (bitLength < 0) {
                return eCPointArr[0];
            }
            boolean testBit = bigInteger.testBit(bitLength);
            int i = 1 - (testBit ? 1 : 0);
            eCPointArr[i] = eCPointArr[i].add(eCPointArr[testBit ? 1 : 0]);
            eCPointArr[testBit ? 1 : 0] = eCPointArr[testBit ? 1 : 0].twice();
        }
    }
}
