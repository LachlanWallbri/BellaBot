package org.bouncycastle.crypto.ec;

import org.bouncycastle.math.ec.ECPoint;

/* loaded from: classes9.dex */
public class ECPair {

    /* renamed from: x */
    private final ECPoint f9312x;

    /* renamed from: y */
    private final ECPoint f9313y;

    public ECPair(ECPoint eCPoint, ECPoint eCPoint2) {
        this.f9312x = eCPoint;
        this.f9313y = eCPoint2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPair) {
            return equals((ECPair) obj);
        }
        return false;
    }

    public boolean equals(ECPair eCPair) {
        return eCPair.getX().equals(getX()) && eCPair.getY().equals(getY());
    }

    public ECPoint getX() {
        return this.f9312x;
    }

    public ECPoint getY() {
        return this.f9313y;
    }

    public int hashCode() {
        return this.f9312x.hashCode() + (this.f9313y.hashCode() * 37);
    }
}
