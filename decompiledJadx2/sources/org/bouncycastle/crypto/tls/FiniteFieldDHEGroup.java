package org.bouncycastle.crypto.tls;

/* loaded from: classes9.dex */
public class FiniteFieldDHEGroup {
    public static final short ffdhe2432 = 0;
    public static final short ffdhe3072 = 1;
    public static final short ffdhe4096 = 2;
    public static final short ffdhe6144 = 3;
    public static final short ffdhe8192 = 4;

    public static boolean isValid(short s) {
        return s >= 0 && s <= 4;
    }
}
