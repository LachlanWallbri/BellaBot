package com.loc;

import android.content.Context;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CgiManager.java */
/* renamed from: com.loc.cc */
/* loaded from: classes4.dex */
public final class C3855cc {

    /* renamed from: b */
    private Context f3882b;

    /* renamed from: h */
    private TelephonyManager f3888h;

    /* renamed from: i */
    private C3853ca f3889i;

    /* renamed from: j */
    private Object f3890j;

    /* renamed from: l */
    private volatile CellLocation f3892l;

    /* renamed from: c */
    private int f3883c = 0;

    /* renamed from: d */
    private ArrayList<C3854cb> f3884d = new ArrayList<>();

    /* renamed from: e */
    private String f3885e = null;

    /* renamed from: f */
    private ArrayList<C3854cb> f3886f = new ArrayList<>();

    /* renamed from: g */
    private int f3887g = -113;

    /* renamed from: k */
    private long f3891k = 0;

    /* renamed from: m */
    private int f3893m = 0;

    /* renamed from: a */
    volatile boolean f3881a = false;

    /* renamed from: n */
    private String f3894n = null;

    public C3855cc(Context context) {
        this.f3888h = null;
        this.f3889i = null;
        this.f3882b = context;
        if (this.f3888h == null) {
            this.f3888h = (TelephonyManager) C3876cx.m2970a(this.f3882b, "phone");
        }
        this.f3889i = new C3853ca();
    }

    /* renamed from: a */
    private CellLocation m2756a(Object obj, String str, Object... objArr) {
        CellLocation cellLocation;
        if (obj == null) {
            return null;
        }
        try {
            Object m2917a = C3871cs.m2917a(obj, str, objArr);
            cellLocation = m2917a != null ? (CellLocation) m2917a : null;
        } catch (Throwable unused) {
        }
        if (m2764a(cellLocation)) {
            return cellLocation;
        }
        return null;
    }

    /* renamed from: a */
    private CellLocation m2757a(List<CellInfo> list) {
        C3854cb c3854cb;
        GsmCellLocation gsmCellLocation;
        long min;
        CdmaCellLocation cdmaCellLocation = null;
        if (list != null && !list.isEmpty()) {
            ArrayList<C3854cb> arrayList = this.f3886f;
            C3853ca c3853ca = this.f3889i;
            int size = list.size();
            if (size != 0) {
                if (arrayList != null) {
                    arrayList.clear();
                }
                c3854cb = null;
                for (int i = 0; i < size; i++) {
                    CellInfo cellInfo = list.get(i);
                    if (cellInfo != null) {
                        try {
                            boolean isRegistered = cellInfo.isRegistered();
                            if (cellInfo instanceof CellInfoCdma) {
                                CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                                CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
                                if (cellIdentity != null && cellIdentity.getSystemId() > 0 && cellIdentity.getNetworkId() >= 0 && cellIdentity.getBasestationId() >= 0) {
                                    c3854cb = m2759a(cellInfoCdma, isRegistered);
                                    c3854cb.f3877l = (short) Math.min(65535L, c3853ca.m2747a(c3854cb));
                                }
                            } else {
                                if (cellInfo instanceof CellInfoGsm) {
                                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                                    CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                                    if (cellIdentity2 != null && m2762a(cellIdentity2.getLac()) && m2766b(cellIdentity2.getCid())) {
                                        CellIdentityGsm cellIdentity3 = cellInfoGsm.getCellIdentity();
                                        c3854cb = m2758a(1, isRegistered, cellIdentity3.getMcc(), cellIdentity3.getMnc(), cellIdentity3.getLac(), cellIdentity3.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
                                        min = Math.min(65535L, c3853ca.m2747a(c3854cb));
                                    }
                                } else if (cellInfo instanceof CellInfoWcdma) {
                                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                                    CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                                    if (cellIdentity4 != null && m2762a(cellIdentity4.getLac()) && m2766b(cellIdentity4.getCid())) {
                                        CellIdentityWcdma cellIdentity5 = cellInfoWcdma.getCellIdentity();
                                        c3854cb = m2758a(4, isRegistered, cellIdentity5.getMcc(), cellIdentity5.getMnc(), cellIdentity5.getLac(), cellIdentity5.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
                                        min = Math.min(65535L, c3853ca.m2747a(c3854cb));
                                    }
                                } else if (cellInfo instanceof CellInfoLte) {
                                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                                    CellIdentityLte cellIdentity6 = cellInfoLte.getCellIdentity();
                                    if (cellIdentity6 != null && m2762a(cellIdentity6.getTac()) && m2766b(cellIdentity6.getCi())) {
                                        CellIdentityLte cellIdentity7 = cellInfoLte.getCellIdentity();
                                        c3854cb = m2758a(3, isRegistered, cellIdentity7.getMcc(), cellIdentity7.getMnc(), cellIdentity7.getTac(), cellIdentity7.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
                                        min = Math.min(65535L, c3853ca.m2747a(c3854cb));
                                    }
                                }
                                c3854cb.f3877l = (short) min;
                            }
                            arrayList.add(c3854cb);
                        } catch (Throwable unused) {
                        }
                    }
                }
            } else {
                c3854cb = null;
            }
            if (arrayList == null || arrayList.size() <= 0) {
                gsmCellLocation = null;
            } else {
                this.f3883c |= 4;
                c3853ca.m2749a(arrayList);
                C3854cb c3854cb2 = arrayList.get(arrayList.size() - 1);
                if (c3854cb2 == null || c3854cb2.f3876k != 2) {
                    gsmCellLocation = new GsmCellLocation();
                    gsmCellLocation.setLacAndCid(c3854cb.f3868c, c3854cb.f3869d);
                } else {
                    CdmaCellLocation cdmaCellLocation2 = new CdmaCellLocation();
                    cdmaCellLocation2.setCellLocationData(c3854cb2.f3874i, c3854cb2.f3870e, c3854cb2.f3871f, c3854cb2.f3872g, c3854cb2.f3873h);
                    gsmCellLocation = null;
                    cdmaCellLocation = cdmaCellLocation2;
                }
            }
            if (cdmaCellLocation == null) {
                return gsmCellLocation;
            }
        }
        return cdmaCellLocation;
    }

    /* renamed from: a */
    private static C3854cb m2758a(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        C3854cb c3854cb = new C3854cb(i, z);
        c3854cb.f3866a = i2;
        c3854cb.f3867b = i3;
        c3854cb.f3868c = i4;
        c3854cb.f3869d = i5;
        c3854cb.f3875j = i6;
        return c3854cb;
    }

    /* renamed from: a */
    private C3854cb m2759a(CellInfoCdma cellInfoCdma, boolean z) {
        int i;
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        String[] m2983a = C3876cx.m2983a(this.f3888h);
        int i2 = 0;
        try {
            i = Integer.parseInt(m2983a[0]);
            try {
                i2 = Integer.parseInt(m2983a[1]);
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            i = 0;
        }
        C3854cb m2758a = m2758a(2, z, i, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
        m2758a.f3872g = cellIdentity.getSystemId();
        m2758a.f3873h = cellIdentity.getNetworkId();
        m2758a.f3874i = cellIdentity.getBasestationId();
        m2758a.f3870e = cellIdentity.getLatitude();
        m2758a.f3871f = cellIdentity.getLongitude();
        return m2758a;
    }

    /* renamed from: a */
    private C3854cb m2760a(NeighboringCellInfo neighboringCellInfo) {
        try {
            C3854cb c3854cb = new C3854cb(1, false);
            String[] m2983a = C3876cx.m2983a(this.f3888h);
            c3854cb.f3866a = Integer.parseInt(m2983a[0]);
            c3854cb.f3867b = Integer.parseInt(m2983a[1]);
            c3854cb.f3868c = C3871cs.m2920b(neighboringCellInfo, "getLac", new Object[0]);
            c3854cb.f3869d = neighboringCellInfo.getCid();
            c3854cb.f3875j = C3876cx.m2965a(neighboringCellInfo.getRssi());
            return c3854cb;
        } catch (Throwable th) {
            C3880f.m3097a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    /* renamed from: a */
    private void m2761a(CellLocation cellLocation, boolean z) {
        List<NeighboringCellInfo> neighboringCellInfo;
        C3854cb m2760a;
        if (cellLocation == null || this.f3888h == null) {
            return;
        }
        this.f3884d.clear();
        if (m2764a(cellLocation)) {
            this.f3883c = 1;
            ArrayList<C3854cb> arrayList = this.f3884d;
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            C3854cb c3854cb = new C3854cb(1, true);
            String[] m2983a = C3876cx.m2983a(this.f3888h);
            c3854cb.f3866a = Integer.parseInt(m2983a[0]);
            c3854cb.f3867b = Integer.parseInt(m2983a[1]);
            c3854cb.f3868c = gsmCellLocation.getLac();
            c3854cb.f3869d = gsmCellLocation.getCid();
            c3854cb.f3875j = this.f3887g;
            arrayList.add(c3854cb);
            if (z || (neighboringCellInfo = this.f3888h.getNeighboringCellInfo()) == null || neighboringCellInfo.isEmpty()) {
                return;
            }
            for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                if (neighboringCellInfo2 != null && m2763a(neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid()) && (m2760a = m2760a(neighboringCellInfo2)) != null && !this.f3884d.contains(m2760a)) {
                    this.f3884d.add(m2760a);
                }
            }
        }
    }

    /* renamed from: a */
    private static boolean m2762a(int i) {
        return (i == -1 || i == 0 || i > 65535) ? false : true;
    }

    /* renamed from: a */
    private static boolean m2763a(int i, int i2) {
        return (i == -1 || i == 0 || i > 65535 || i2 == -1 || i2 == 0 || i2 == 65535 || i2 >= 268435455) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0031, code lost:
    
        if (com.loc.C3871cs.m2920b(r6, "getBaseStationId", new java.lang.Object[0]) < 0) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0050  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean m2764a(CellLocation cellLocation) {
        String str;
        if (cellLocation == null) {
            return false;
        }
        boolean z = this.f3881a;
        Context context = this.f3882b;
        int m2968a = C3876cx.m2968a(z, cellLocation);
        boolean z2 = true;
        if (m2968a == 1) {
            try {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                z2 = m2763a(gsmCellLocation.getLac(), gsmCellLocation.getCid());
            } catch (Throwable th) {
                th = th;
                str = "cgiUseful Cgi.iGsmT";
                C3880f.m3097a(th, "CgiManager", str);
                if (!z2) {
                }
                return z2;
            }
        } else if (m2968a == 2) {
            try {
                if (C3871cs.m2920b(cellLocation, "getSystemId", new Object[0]) > 0 && C3871cs.m2920b(cellLocation, "getNetworkId", new Object[0]) >= 0) {
                }
                z2 = false;
            } catch (Throwable th2) {
                th = th2;
                str = "cgiUseful Cgi.iCdmaT";
                C3880f.m3097a(th, "CgiManager", str);
                if (!z2) {
                }
                return z2;
            }
        }
        if (!z2) {
            this.f3883c = 0;
        }
        return z2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x007d, code lost:
    
        if (r8 == false) goto L40;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized void m2765b(boolean z, boolean z2) {
        CellLocation cellLocation;
        boolean z3;
        this.f3881a = z;
        if (!this.f3881a && this.f3888h != null) {
            CellLocation m2767n = m2767n();
            if (!m2764a(m2767n)) {
                m2767n = m2768o();
            }
            if (m2764a(m2767n)) {
                this.f3892l = m2767n;
            }
        }
        if (m2764a(this.f3892l)) {
            String[] m2983a = C3876cx.m2983a(this.f3888h);
            boolean z4 = this.f3881a;
            CellLocation cellLocation2 = this.f3892l;
            Context context = this.f3882b;
            int m2968a = C3876cx.m2968a(z4, cellLocation2);
            if (m2968a == 1) {
                m2761a(this.f3892l, z2);
            } else if (m2968a == 2 && (cellLocation = this.f3892l) != null) {
                this.f3884d.clear();
                if (C3876cx.m2996c() >= 5) {
                    try {
                        if (this.f3890j != null) {
                            try {
                                Field declaredField = cellLocation.getClass().getDeclaredField("mGsmCellLoc");
                                if (!declaredField.isAccessible()) {
                                    declaredField.setAccessible(true);
                                }
                                GsmCellLocation gsmCellLocation = (GsmCellLocation) declaredField.get(cellLocation);
                                if (gsmCellLocation != null && m2764a(gsmCellLocation)) {
                                    m2761a(gsmCellLocation, z2);
                                    z3 = true;
                                }
                            } catch (Throwable unused) {
                            }
                            z3 = false;
                        }
                        if (m2764a(cellLocation)) {
                            this.f3883c = 2;
                            C3854cb c3854cb = new C3854cb(2, true);
                            c3854cb.f3866a = Integer.parseInt(m2983a[0]);
                            c3854cb.f3867b = Integer.parseInt(m2983a[1]);
                            c3854cb.f3872g = C3871cs.m2920b(cellLocation, "getSystemId", new Object[0]);
                            c3854cb.f3873h = C3871cs.m2920b(cellLocation, "getNetworkId", new Object[0]);
                            c3854cb.f3874i = C3871cs.m2920b(cellLocation, "getBaseStationId", new Object[0]);
                            c3854cb.f3875j = this.f3887g;
                            c3854cb.f3870e = C3871cs.m2920b(cellLocation, "getBaseStationLatitude", new Object[0]);
                            c3854cb.f3871f = C3871cs.m2920b(cellLocation, "getBaseStationLongitude", new Object[0]);
                            if (c3854cb.f3870e < 0 || c3854cb.f3871f < 0 || c3854cb.f3870e == Integer.MAX_VALUE || c3854cb.f3871f == Integer.MAX_VALUE || (c3854cb.f3870e == c3854cb.f3871f && c3854cb.f3870e > 0)) {
                                c3854cb.f3870e = 0;
                                c3854cb.f3871f = 0;
                            }
                            if (!this.f3884d.contains(c3854cb)) {
                                this.f3884d.add(c3854cb);
                            }
                        }
                    } catch (Throwable th) {
                        C3880f.m3097a(th, "CgiManager", "hdlCdmaLocChange");
                    }
                }
            }
        }
        if (this.f3888h != null) {
            this.f3885e = this.f3888h.getNetworkOperator();
            if (!TextUtils.isEmpty(this.f3885e)) {
                this.f3883c |= 8;
            }
        }
    }

    /* renamed from: b */
    private static boolean m2766b(int i) {
        return (i == -1 || i == 0 || i == 65535 || i >= 268435455) ? false : true;
    }

    /* renamed from: n */
    private CellLocation m2767n() {
        TelephonyManager telephonyManager = this.f3888h;
        CellLocation cellLocation = null;
        if (telephonyManager == null) {
            return null;
        }
        CellLocation m2777f = m2777f();
        if (C3876cx.m2996c() >= 18) {
            try {
                cellLocation = m2757a(telephonyManager.getAllCellInfo());
            } catch (SecurityException e) {
                this.f3894n = e.getMessage();
            }
        }
        if (m2764a(m2777f)) {
            return m2777f;
        }
        if (cellLocation != null) {
            return cellLocation;
        }
        CellLocation m2756a = m2756a(telephonyManager, "getCellLocationExt", 1);
        return m2756a != null ? m2756a : m2756a(telephonyManager, "getCellLocationGemini", 1);
    }

    /* renamed from: o */
    private CellLocation m2768o() {
        Object obj = this.f3890j;
        CellLocation cellLocation = null;
        if (obj == null) {
            return null;
        }
        try {
            Class<?> m2769p = m2769p();
            if (m2769p.isInstance(obj)) {
                Object cast = m2769p.cast(obj);
                CellLocation m2756a = m2756a(cast, "getCellLocation", new Object[0]);
                if (m2756a != null) {
                    return m2756a;
                }
                CellLocation m2756a2 = m2756a(cast, "getCellLocation", 1);
                if (m2756a2 != null) {
                    return m2756a2;
                }
                CellLocation m2756a3 = m2756a(cast, "getCellLocationGemini", 1);
                if (m2756a3 != null) {
                    return m2756a3;
                }
                cellLocation = m2756a(cast, "getAllCellInfo", 1);
                if (cellLocation != null) {
                    return cellLocation;
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "CgiManager", "getSim2Cgi");
        }
        return cellLocation;
    }

    /* renamed from: p */
    private Class<?> m2769p() {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        int i = this.f3893m;
        try {
            return systemClassLoader.loadClass(i != 0 ? i != 1 ? i != 2 ? null : "android.telephony.TelephonyManager2" : "android.telephony.MSimTelephonyManager" : "android.telephony.TelephonyManager");
        } catch (Throwable th) {
            C3880f.m3097a(th, "CgiManager", "getSim2TmClass");
            return null;
        }
    }

    /* renamed from: q */
    private int m2770q() {
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            this.f3893m = 1;
        } catch (Throwable unused) {
        }
        if (this.f3893m == 0) {
            try {
                Class.forName("android.telephony.TelephonyManager2");
                this.f3893m = 2;
            } catch (Throwable unused2) {
            }
        }
        return this.f3893m;
    }

    /* renamed from: a */
    public final void m2771a() {
        if (this.f3888h == null) {
            return;
        }
        try {
            boolean z = this.f3881a;
            CellLocation cellLocation = this.f3888h.getCellLocation();
            Context context = this.f3882b;
            this.f3883c = C3876cx.m2968a(z, cellLocation);
        } catch (SecurityException e) {
            this.f3894n = e.getMessage();
        } catch (Throwable th) {
            this.f3894n = null;
            C3880f.m3097a(th, "CgiManager", "CgiManager");
            this.f3883c = 0;
        }
        try {
            this.f3893m = m2770q();
            int i = this.f3893m;
            if (i == 1) {
                this.f3890j = C3876cx.m2970a(this.f3882b, "phone_msim");
            } else if (i != 2) {
                this.f3890j = C3876cx.m2970a(this.f3882b, "phone2");
            } else {
                this.f3890j = C3876cx.m2970a(this.f3882b, "phone2");
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public final void m2772a(boolean z, boolean z2) {
        boolean z3 = false;
        if (!z) {
            try {
                if (C3876cx.m2985b() - this.f3891k >= 10000) {
                    z3 = true;
                }
            } catch (SecurityException e) {
                this.f3894n = e.getMessage();
                return;
            } catch (Throwable th) {
                C3880f.m3097a(th, "CgiManager", "refresh");
                return;
            }
        }
        if (z3) {
            m2765b(z, z2);
            this.f3891k = C3876cx.m2985b();
        }
    }

    /* renamed from: b */
    public final ArrayList<C3854cb> m2773b() {
        return this.f3884d;
    }

    /* renamed from: c */
    public final C3854cb m2774c() {
        ArrayList<C3854cb> arrayList = this.f3884d;
        if (arrayList.size() > 0) {
            return arrayList.get(0);
        }
        return null;
    }

    /* renamed from: d */
    public final int m2775d() {
        return this.f3883c;
    }

    /* renamed from: e */
    public final int m2776e() {
        return this.f3883c & 3;
    }

    /* renamed from: f */
    public final CellLocation m2777f() {
        TelephonyManager telephonyManager = this.f3888h;
        if (telephonyManager != null) {
            try {
                CellLocation cellLocation = telephonyManager.getCellLocation();
                this.f3894n = null;
                if (m2764a(cellLocation)) {
                    this.f3892l = cellLocation;
                    return cellLocation;
                }
            } catch (SecurityException e) {
                this.f3894n = e.getMessage();
            } catch (Throwable th) {
                this.f3894n = null;
                C3880f.m3097a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    /* renamed from: g */
    public final TelephonyManager m2778g() {
        return this.f3888h;
    }

    /* renamed from: h */
    public final void m2779h() {
        this.f3894n = null;
        this.f3892l = null;
        this.f3883c = 0;
        this.f3884d.clear();
    }

    /* renamed from: i */
    public final void m2780i() {
        this.f3889i.m2748a();
        this.f3884d.clear();
        this.f3887g = -113;
        this.f3891k = 0L;
        this.f3888h = null;
        this.f3890j = null;
    }

    /* renamed from: j */
    public final void m2781j() {
        int i = this.f3883c;
        if (i == 1) {
            if (this.f3884d.isEmpty()) {
                this.f3883c = 0;
            }
        } else if (i == 2 && this.f3884d.isEmpty()) {
            this.f3883c = 0;
        }
    }

    /* renamed from: k */
    public final String m2782k() {
        return this.f3894n;
    }

    /* renamed from: l */
    public final String m2783l() {
        return this.f3885e;
    }

    /* renamed from: m */
    public final ArrayList<C3854cb> m2784m() {
        return this.f3886f;
    }
}
