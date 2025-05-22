package com.loc;

/* compiled from: GeoHash.java */
/* renamed from: com.loc.by */
/* loaded from: classes4.dex */
public final class C3850by {

    /* renamed from: a */
    private static final char[] f3842a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /* renamed from: b */
    private static final int[] f3843b = {16, 8, 4, 2, 1};

    /* renamed from: a */
    public static final String m2732a(double d, double d2) {
        StringBuilder sb = new StringBuilder();
        double[] dArr = {-90.0d, 90.0d};
        double[] dArr2 = {-180.0d, 180.0d};
        boolean z = true;
        while (true) {
            int i = 0;
            int i2 = 0;
            while (sb.length() < 6) {
                if (z) {
                    double d3 = (dArr2[0] + dArr2[1]) / 2.0d;
                    if (d2 > d3) {
                        i |= f3843b[i2];
                        dArr2[0] = d3;
                    } else {
                        dArr2[1] = d3;
                    }
                } else {
                    double d4 = (dArr[0] + dArr[1]) / 2.0d;
                    if (d > d4) {
                        i |= f3843b[i2];
                        dArr[0] = d4;
                    } else {
                        dArr[1] = d4;
                    }
                }
                z = !z;
                if (i2 < 4) {
                    i2++;
                }
            }
            return sb.toString();
            sb.append(f3842a[i]);
        }
    }
}
