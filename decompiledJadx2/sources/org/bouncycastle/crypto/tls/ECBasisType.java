package org.bouncycastle.crypto.tls;

/* loaded from: classes9.dex */
public class ECBasisType {
    public static final short ec_basis_pentanomial = 2;
    public static final short ec_basis_trinomial = 1;

    public static boolean isValid(short s) {
        return s >= 1 && s <= 2;
    }
}
