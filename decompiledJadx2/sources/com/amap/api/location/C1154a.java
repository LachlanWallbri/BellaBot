package com.amap.api.location;

import android.content.Context;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.loc.C3877cy;
import com.loc.C3880f;
import com.loc.C3892r;
import java.io.File;
import java.math.BigDecimal;

/* compiled from: OffsetUtil.java */
/* renamed from: com.amap.api.location.a */
/* loaded from: classes.dex */
public final class C1154a {

    /* renamed from: a */
    static double f1131a = 3.141592653589793d;

    /* renamed from: b */
    private static boolean f1132b = false;

    /* renamed from: a */
    private static double m492a(double d) {
        return Math.sin(d * 3000.0d * (f1131a / 180.0d)) * 2.0E-5d;
    }

    /* renamed from: a */
    private static double m493a(double d, double d2) {
        return (Math.cos(d2 / 100000.0d) * (d / 18000.0d)) + (Math.sin(d / 100000.0d) * (d2 / 9000.0d));
    }

    /* renamed from: a */
    public static DPoint m494a(Context context, double d, double d2) {
        if (context == null) {
            return null;
        }
        return m495a(context, new DPoint(d2, d));
    }

    /* renamed from: a */
    public static DPoint m495a(Context context, DPoint dPoint) {
        if (context == null) {
            return null;
        }
        String m3199a = C3892r.m3199a(context, "libwgs2gcj.so");
        if (!TextUtils.isEmpty(m3199a) && new File(m3199a).exists() && !f1132b) {
            try {
                System.load(m3199a);
                f1132b = true;
            } catch (UnsatisfiedLinkError unused) {
            } catch (Throwable th) {
                C3880f.m3097a(th, "OffsetUtil", TypedValues.Cycle.S_WAVE_OFFSET);
            }
        }
        return m497a(dPoint, f1132b);
    }

    /* renamed from: a */
    public static DPoint m496a(DPoint dPoint) {
        if (dPoint != null) {
            double d = 0.006401062d;
            double d2 = 0.0060424805d;
            DPoint dPoint2 = null;
            for (int i = 0; i < 2; i++) {
                try {
                    double longitude = dPoint.getLongitude();
                    double latitude = dPoint.getLatitude();
                    dPoint2 = new DPoint();
                    double d3 = longitude - d;
                    double d4 = latitude - d2;
                    DPoint dPoint3 = new DPoint();
                    double d5 = (d3 * d3) + (d4 * d4);
                    double cos = (Math.cos(m498b(d3) + Math.atan2(d4, d3)) * (m492a(d4) + Math.sqrt(d5))) + 0.0065d;
                    double sin = (Math.sin(m498b(d3) + Math.atan2(d4, d3)) * (m492a(d4) + Math.sqrt(d5))) + 0.006d;
                    dPoint3.setLongitude(m501c(cos));
                    dPoint3.setLatitude(m501c(sin));
                    dPoint2.setLongitude(m501c((longitude + d3) - dPoint3.getLongitude()));
                    dPoint2.setLatitude(m501c((latitude + d4) - dPoint3.getLatitude()));
                    d = dPoint.getLongitude() - dPoint2.getLongitude();
                    d2 = dPoint.getLatitude() - dPoint2.getLatitude();
                } catch (Throwable th) {
                    C3880f.m3097a(th, "OffsetUtil", "B2G");
                }
            }
            return dPoint2;
        }
        return dPoint;
    }

    /* renamed from: a */
    private static DPoint m497a(DPoint dPoint, boolean z) {
        double longitude;
        double latitude;
        try {
            if (!C3880f.m3098a(dPoint.getLatitude(), dPoint.getLongitude())) {
                return dPoint;
            }
            double[] dArr = new double[2];
            if (z) {
                try {
                    if (CoordUtil.convertToGcj(new double[]{dPoint.getLongitude(), dPoint.getLatitude()}, dArr) != 0) {
                        longitude = dPoint.getLongitude();
                        latitude = dPoint.getLatitude();
                    }
                } catch (Throwable th) {
                    try {
                        C3880f.m3097a(th, "OffsetUtil", "cover part1");
                        longitude = dPoint.getLongitude();
                        latitude = dPoint.getLatitude();
                    } catch (Throwable th2) {
                        C3877cy.m3021a(dPoint.getLongitude(), dPoint.getLatitude());
                        throw th2;
                    }
                }
                return new DPoint(dArr[1], dArr[0]);
            }
            longitude = dPoint.getLongitude();
            latitude = dPoint.getLatitude();
            dArr = C3877cy.m3021a(longitude, latitude);
            return new DPoint(dArr[1], dArr[0]);
        } catch (Throwable th3) {
            C3880f.m3097a(th3, "OffsetUtil", "cover part2");
            return dPoint;
        }
    }

    /* renamed from: b */
    private static double m498b(double d) {
        return Math.cos(d * 3000.0d * (f1131a / 180.0d)) * 3.0E-6d;
    }

    /* renamed from: b */
    private static double m499b(double d, double d2) {
        return (Math.sin(d2 / 100000.0d) * (d / 18000.0d)) + (Math.cos(d / 100000.0d) * (d2 / 9000.0d));
    }

    /* renamed from: b */
    public static DPoint m500b(Context context, DPoint dPoint) {
        try {
            double longitude = ((long) (dPoint.getLongitude() * 100000.0d)) % 36000000;
            double latitude = ((long) (dPoint.getLatitude() * 100000.0d)) % 36000000;
            double d = (int) ((-m493a(longitude, latitude)) + longitude);
            double d2 = (int) ((-m499b(longitude, latitude)) + latitude);
            int i = 1;
            double d3 = (int) ((-m493a(d, d2)) + longitude + (longitude > 0.0d ? 1 : -1));
            double d4 = (-m499b(d3, d2)) + latitude;
            if (latitude <= 0.0d) {
                i = -1;
            }
            return m495a(context, new DPoint(((int) (d4 + i)) / 100000.0d, d3 / 100000.0d));
        } catch (Throwable th) {
            C3880f.m3097a(th, "OffsetUtil", "marbar2G");
            return dPoint;
        }
    }

    /* renamed from: c */
    private static double m501c(double d) {
        return new BigDecimal(d).setScale(8, 4).doubleValue();
    }
}
