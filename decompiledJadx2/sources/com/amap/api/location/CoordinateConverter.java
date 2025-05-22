package com.amap.api.location;

import android.content.Context;
import com.loc.C3876cx;
import com.loc.C3880f;

/* loaded from: classes.dex */
public class CoordinateConverter {

    /* renamed from: b */
    private Context f1124b;

    /* renamed from: c */
    private CoordType f1125c = null;

    /* renamed from: d */
    private DPoint f1126d = null;

    /* renamed from: a */
    DPoint f1123a = null;

    /* loaded from: classes.dex */
    public enum CoordType {
        BAIDU,
        MAPBAR,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE,
        GPS
    }

    public CoordinateConverter(Context context) {
        this.f1124b = context;
    }

    public static float calculateLineDistance(DPoint dPoint, DPoint dPoint2) {
        try {
            return C3876cx.m2963a(dPoint, dPoint2);
        } catch (Throwable unused) {
            return 0.0f;
        }
    }

    public static boolean isAMapDataAvailable(double d, double d2) {
        return C3880f.m3098a(d, d2);
    }

    public synchronized DPoint convert() throws Exception {
        DPoint m496a;
        if (this.f1125c == null) {
            throw new IllegalArgumentException("转换坐标类型不能为空");
        }
        if (this.f1126d == null) {
            throw new IllegalArgumentException("转换坐标源不能为空");
        }
        if (this.f1126d.getLongitude() > 180.0d || this.f1126d.getLongitude() < -180.0d) {
            throw new IllegalArgumentException("请传入合理经度");
        }
        if (this.f1126d.getLatitude() > 90.0d || this.f1126d.getLatitude() < -90.0d) {
            throw new IllegalArgumentException("请传入合理纬度");
        }
        switch (this.f1125c) {
            case BAIDU:
                m496a = C1154a.m496a(this.f1126d);
                this.f1123a = m496a;
                break;
            case MAPBAR:
                m496a = C1154a.m500b(this.f1124b, this.f1126d);
                this.f1123a = m496a;
                break;
            case MAPABC:
            case SOSOMAP:
            case ALIYUN:
            case GOOGLE:
                m496a = this.f1126d;
                this.f1123a = m496a;
                break;
            case GPS:
                m496a = C1154a.m495a(this.f1124b, this.f1126d);
                this.f1123a = m496a;
                break;
        }
        return this.f1123a;
    }

    public synchronized CoordinateConverter coord(DPoint dPoint) throws Exception {
        try {
            if (dPoint == null) {
                throw new IllegalArgumentException("传入经纬度对象为空");
            }
            if (dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                throw new IllegalArgumentException("请传入合理经度");
            }
            if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d) {
                throw new IllegalArgumentException("请传入合理纬度");
            }
            this.f1126d = dPoint;
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    public synchronized CoordinateConverter from(CoordType coordType) {
        this.f1125c = coordType;
        return this;
    }
}
