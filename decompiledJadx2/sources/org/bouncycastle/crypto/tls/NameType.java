package org.bouncycastle.crypto.tls;

/* loaded from: classes9.dex */
public class NameType {
    public static final short host_name = 0;

    public static boolean isValid(short s) {
        return s == 0;
    }
}
