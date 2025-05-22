package com.iflytek.aiui.pro;

import android.content.Context;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.d */
/* loaded from: classes.dex */
public class C3608d {

    /* renamed from: a */
    private Context f2471a;

    /* renamed from: b */
    private int f2472b;

    /* renamed from: e */
    private TelephonyManager f2475e;

    /* renamed from: f */
    private Object f2476f;

    /* renamed from: h */
    private volatile CellLocation f2478h;

    /* renamed from: c */
    private ArrayList<C3605c> f2473c = new ArrayList<>();

    /* renamed from: d */
    private int f2474d = -113;

    /* renamed from: g */
    private long f2477g = 0;

    public C3608d(Context context) {
        this.f2472b = 9;
        TelephonyManager telephonyManager = (TelephonyManager) C3614g.m1300a(context, "phone");
        this.f2475e = telephonyManager;
        this.f2471a = context;
        this.f2472b = C3614g.m1298a(telephonyManager.getCellLocation(), context);
        try {
            int m1246h = m1246h();
            if (m1246h == 0) {
                this.f2476f = C3614g.m1300a(this.f2471a, "phone2");
            } else if (m1246h == 1) {
                this.f2476f = C3614g.m1300a(this.f2471a, "phone_msim");
            } else {
                if (m1246h != 2) {
                    return;
                }
                this.f2476f = C3614g.m1300a(this.f2471a, "phone2");
            }
        } catch (Throwable th) {
            C3614g.m1308a(th, "CgiManager", "CgiManager");
            this.f2472b = 9;
        }
    }

    /* renamed from: a */
    private C3605c m1242a(NeighboringCellInfo neighboringCellInfo) {
        if (C3614g.m1334d() < 5) {
            return null;
        }
        try {
            C3605c c3605c = new C3605c();
            String[] m1318a = C3614g.m1318a(this.f2475e);
            c3605c.f2440a = m1318a[0];
            c3605c.f2441b = m1318a[1];
            c3605c.f2442c = C3614g.m1319b(neighboringCellInfo, "getLac", new Object[0]);
            c3605c.f2443d = neighboringCellInfo.getCid();
            c3605c.f2449j = C3614g.m1295a(neighboringCellInfo.getRssi());
            return c3605c;
        } catch (Throwable th) {
            C3614g.m1308a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    /* renamed from: b */
    private void m1243b(CellLocation cellLocation) {
        C3605c m1242a;
        if (cellLocation == null || this.f2475e == null) {
            return;
        }
        this.f2473c.clear();
        if (m1254a(cellLocation)) {
            this.f2472b = 1;
            this.f2473c.add(m1245d(cellLocation));
            List<NeighboringCellInfo> neighboringCellInfo = this.f2475e.getNeighboringCellInfo();
            if (neighboringCellInfo == null || neighboringCellInfo.isEmpty()) {
                return;
            }
            for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                if (neighboringCellInfo2 != null && m1253a(neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid()) && (m1242a = m1242a(neighboringCellInfo2)) != null && !this.f2473c.contains(m1242a)) {
                    this.f2473c.add(m1242a);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m1244c(CellLocation cellLocation) {
        boolean z;
        GsmCellLocation gsmCellLocation;
        if (cellLocation == null) {
            return;
        }
        this.f2473c.clear();
        if (C3614g.m1334d() >= 5) {
            try {
                if (this.f2476f != null) {
                    try {
                        Field declaredField = cellLocation.getClass().getDeclaredField("mGsmCellLoc");
                        if (!declaredField.isAccessible()) {
                            declaredField.setAccessible(true);
                        }
                        gsmCellLocation = (GsmCellLocation) declaredField.get(cellLocation);
                    } catch (Throwable th) {
                        C3614g.m1308a(th, "CgiManager", "hdlCdmaLocChange1");
                    }
                    if (gsmCellLocation != null && m1254a(gsmCellLocation)) {
                        m1243b(gsmCellLocation);
                        z = true;
                        if (z) {
                            return;
                        }
                    }
                    z = false;
                    if (z) {
                    }
                }
                if (m1254a(cellLocation)) {
                    this.f2472b = 2;
                    String[] m1318a = C3614g.m1318a(this.f2475e);
                    C3605c c3605c = new C3605c();
                    c3605c.f2440a = m1318a[0];
                    c3605c.f2441b = m1318a[1];
                    c3605c.f2446g = C3614g.m1319b(cellLocation, "getSystemId", new Object[0]);
                    c3605c.f2447h = C3614g.m1319b(cellLocation, "getNetworkId", new Object[0]);
                    c3605c.f2448i = C3614g.m1319b(cellLocation, "getBaseStationId", new Object[0]);
                    c3605c.f2449j = this.f2474d;
                    c3605c.f2444e = C3614g.m1319b(cellLocation, "getBaseStationLatitude", new Object[0]);
                    c3605c.f2445f = C3614g.m1319b(cellLocation, "getBaseStationLongitude", new Object[0]);
                    if (c3605c.f2444e < 0 || c3605c.f2445f < 0 || c3605c.f2444e == Integer.MAX_VALUE || c3605c.f2445f == Integer.MAX_VALUE || (c3605c.f2444e == c3605c.f2445f && c3605c.f2444e > 0)) {
                        c3605c.f2444e = 0;
                        c3605c.f2445f = 0;
                    }
                    if (this.f2473c.contains(c3605c)) {
                        return;
                    }
                    this.f2473c.add(c3605c);
                }
            } catch (Throwable th2) {
                C3614g.m1308a(th2, "CgiManager", "hdlCdmaLocChange");
            }
        }
    }

    /* renamed from: d */
    private C3605c m1245d(CellLocation cellLocation) {
        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
        C3605c c3605c = new C3605c();
        String[] m1318a = C3614g.m1318a(this.f2475e);
        c3605c.f2440a = m1318a[0];
        c3605c.f2441b = m1318a[1];
        c3605c.f2442c = gsmCellLocation.getLac();
        c3605c.f2443d = gsmCellLocation.getCid();
        c3605c.f2449j = this.f2474d;
        return c3605c;
    }

    /* renamed from: h */
    public static int m1246h() {
        int i;
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            i = 1;
        } catch (Throwable unused) {
            i = 0;
        }
        if (i != 0) {
            return i;
        }
        try {
            Class.forName("android.telephony.TelephonyManager2");
            return 2;
        } catch (Throwable unused2) {
            return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m1247i() {
        synchronized (this) {
            if (!C3614g.m1311a(this.f2471a) && this.f2475e != null) {
                CellLocation m1261g = m1261g();
                if (!m1254a(m1261g)) {
                    m1261g = m1248j();
                }
                if (m1254a(m1261g)) {
                    this.f2478h = m1261g;
                }
            }
            if (m1254a(this.f2478h)) {
                int m1298a = C3614g.m1298a(this.f2478h, this.f2471a);
                if (m1298a == 1) {
                    m1243b(this.f2478h);
                } else if (m1298a == 2) {
                    m1244c(this.f2478h);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0075 A[Catch: all -> 0x0079, TRY_LEAVE, TryCatch #7 {all -> 0x0079, blocks: (B:6:0x000a, B:8:0x0014, B:24:0x006d, B:17:0x0075, B:29:0x0067, B:35:0x0052, B:41:0x003b, B:46:0x0021, B:37:0x002a, B:11:0x0019, B:31:0x0042, B:23:0x005b), top: B:5:0x000a, inners: #9, #8, #7, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x002a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private CellLocation m1248j() {
        Object obj;
        List<?> list;
        Object obj2 = this.f2476f;
        if (obj2 == null) {
            return null;
        }
        try {
            Class<?> m1249k = m1249k();
            if (m1249k.isInstance(obj2)) {
                Object cast = m1249k.cast(obj2);
                try {
                    obj = C3614g.m1301a(cast, "getCellLocation", new Object[0]);
                } catch (NoSuchMethodException unused) {
                    obj = null;
                    if (obj == null) {
                    }
                    if (obj == null) {
                    }
                    if (obj == null) {
                    }
                    if (obj != null) {
                    }
                } catch (Throwable th) {
                    C3614g.m1308a(th, "CgiManager", "getSim2Cgi15");
                    obj = null;
                    if (obj == null) {
                    }
                    if (obj == null) {
                    }
                    if (obj == null) {
                    }
                    if (obj != null) {
                    }
                }
                if (obj == null) {
                    try {
                        obj = C3614g.m1301a(cast, "getCellLocation", 1);
                    } catch (NoSuchMethodException unused2) {
                    } catch (Throwable th2) {
                        C3614g.m1308a(th2, "CgiManager", "getSim2Cgi14");
                    }
                }
                if (obj == null) {
                    try {
                        obj = C3614g.m1301a(cast, "getCellLocationGemini", 1);
                    } catch (NoSuchMethodException unused3) {
                    } catch (Throwable th3) {
                        C3614g.m1308a(th3, "CgiManager", "getSim2Cgi13");
                    }
                }
                if (obj == null) {
                    try {
                        list = (List) C3614g.m1301a(cast, "getAllCellInfo", new Object[0]);
                    } catch (NoSuchMethodException unused4) {
                        list = null;
                        obj = m1251a(list);
                        if (obj != null) {
                        }
                    } catch (Throwable th4) {
                        C3614g.m1308a(th4, "CgiManager", "getSim2Cgi1");
                        list = null;
                        obj = m1251a(list);
                        if (obj != null) {
                        }
                    }
                    obj = m1251a(list);
                }
            } else {
                obj = null;
            }
            if (obj != null) {
                return (CellLocation) obj;
            }
            return null;
        } catch (Throwable th5) {
            C3614g.m1308a(th5, "CgiManager", "getSim2Cgi");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public Class<?> m1249k() {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        int m1246h = m1246h();
        try {
            return systemClassLoader.loadClass(m1246h != 0 ? m1246h != 1 ? m1246h != 2 ? null : "android.telephony.TelephonyManager2" : "android.telephony.MSimTelephonyManager" : "android.telephony.TelephonyManager");
        } catch (Throwable th) {
            C3614g.m1308a(th, "CgiManager", "getSim2TmClass");
            return null;
        }
    }

    /* renamed from: l */
    private void m1250l() {
        try {
            CellLocation.requestLocationUpdate();
        } catch (Throwable th) {
            C3614g.m1308a(th, "CgiManager", "updateCgi");
        }
        this.f2477g = C3614g.m1330c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* renamed from: a */
    public CellLocation m1251a(List<?> list) {
        Object cast;
        CdmaCellLocation cdmaCellLocation = null;
        if (list != null && !list.isEmpty()) {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            CdmaCellLocation cdmaCellLocation2 = null;
            CellLocation cellLocation = null;
            int i = 0;
            char c = 0;
            while (true) {
                if (i >= list.size()) {
                    cdmaCellLocation = cdmaCellLocation2;
                    break;
                }
                Object obj = list.get(i);
                if (obj != null) {
                    try {
                        Class<?> loadClass = systemClassLoader.loadClass("android.telephony.CellInfoGsm");
                        Class<?> loadClass2 = systemClassLoader.loadClass("android.telephony.CellInfoWcdma");
                        Class<?> loadClass3 = systemClassLoader.loadClass("android.telephony.CellInfoLte");
                        Class<?> loadClass4 = systemClassLoader.loadClass("android.telephony.CellInfoCdma");
                        if (loadClass.isInstance(obj)) {
                            try {
                                cast = loadClass.cast(obj);
                                c = 1;
                            } catch (Throwable th) {
                                th = th;
                                c = 1;
                                C3614g.m1308a(th, "CgiManager", "getCgi");
                                i++;
                            }
                        } else if (loadClass2.isInstance(obj)) {
                            c = 2;
                            cast = loadClass2.cast(obj);
                        } else if (loadClass3.isInstance(obj)) {
                            try {
                                cast = loadClass3.cast(obj);
                                c = 3;
                            } catch (Throwable th2) {
                                th = th2;
                                c = 3;
                                C3614g.m1308a(th, "CgiManager", "getCgi");
                                i++;
                            }
                        } else if (loadClass4.isInstance(obj)) {
                            try {
                                cast = loadClass4.cast(obj);
                                c = 4;
                            } catch (Throwable th3) {
                                th = th3;
                                c = 4;
                                C3614g.m1308a(th, "CgiManager", "getCgi");
                                i++;
                            }
                        } else {
                            cast = cdmaCellLocation;
                            c = 0;
                        }
                        if (c > 0 && cast != null) {
                            ?? r11 = new Object[0];
                            Object m1301a = C3614g.m1301a(cast, "getCellIdentity", (Object[]) r11);
                            if (c == 4) {
                                CdmaCellLocation cdmaCellLocation3 = new CdmaCellLocation();
                                try {
                                    cdmaCellLocation3.setCellLocationData(C3614g.m1319b(m1301a, "getBasestationId", new Object[0]), C3614g.m1319b(m1301a, "getLatitude", new Object[0]), C3614g.m1319b(m1301a, "getLongitude", new Object[0]), C3614g.m1319b(m1301a, "getSystemId", new Object[0]), C3614g.m1319b(m1301a, "getNetworkId", new Object[0]));
                                    cdmaCellLocation = cdmaCellLocation3;
                                    break;
                                } catch (Throwable th4) {
                                    th = th4;
                                    cdmaCellLocation2 = cdmaCellLocation3;
                                    C3614g.m1308a(th, "CgiManager", "getCgi");
                                    i++;
                                }
                            } else {
                                try {
                                    if (c == 3) {
                                        int m1319b = C3614g.m1319b(m1301a, "getTac", new Object[0]);
                                        int m1319b2 = C3614g.m1319b(m1301a, "getCi", new Object[0]);
                                        GsmCellLocation gsmCellLocation = new GsmCellLocation();
                                        gsmCellLocation.setLacAndCid(m1319b, m1319b2);
                                        r11 = gsmCellLocation;
                                    } else {
                                        int m1319b3 = C3614g.m1319b(m1301a, "getLac", new Object[0]);
                                        int m1319b4 = C3614g.m1319b(m1301a, "getCid", new Object[0]);
                                        GsmCellLocation gsmCellLocation2 = new GsmCellLocation();
                                        gsmCellLocation2.setLacAndCid(m1319b3, m1319b4);
                                        r11 = gsmCellLocation2;
                                    }
                                    cdmaCellLocation = cdmaCellLocation2;
                                    cellLocation = r11;
                                    break;
                                } catch (Throwable th5) {
                                    th = th5;
                                    cellLocation = r11;
                                    C3614g.m1308a(th, "CgiManager", "getCgi");
                                    i++;
                                }
                            }
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                }
                i++;
            }
            if (c != 4) {
                return cellLocation;
            }
        }
        return cdmaCellLocation;
    }

    /* renamed from: a */
    public ArrayList<C3605c> m1252a() {
        return this.f2473c;
    }

    /* renamed from: a */
    public boolean m1253a(int i, int i2) {
        return (i == 0 || i == -1 || i > 65535 || i2 == 0 || i2 == -1 || i2 == 65535 || i2 >= 268435455) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:
    
        if (com.iflytek.aiui.pro.C3614g.m1319b(r5, "getBaseStationId", new java.lang.Object[0]) < 0) goto L20;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m1254a(CellLocation cellLocation) {
        boolean z = false;
        if (cellLocation != null) {
            int m1298a = C3614g.m1298a(cellLocation, this.f2471a);
            if (m1298a != 1) {
                if (m1298a == 2) {
                    try {
                        if (C3614g.m1319b(cellLocation, "getSystemId", new Object[0]) > 0 && C3614g.m1319b(cellLocation, "getNetworkId", new Object[0]) >= 0) {
                        }
                    } catch (Throwable th) {
                        C3614g.m1308a(th, "CgiManager", "cgiUseful");
                    }
                }
                z = true;
            } else {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                z = m1253a(gsmCellLocation.getLac(), gsmCellLocation.getCid());
            }
            if (!z) {
                this.f2472b = 9;
            }
        }
        return z;
    }

    /* renamed from: a */
    public boolean m1255a(boolean z) {
        return (z || this.f2477g == 0 || C3614g.m1330c() - this.f2477g < 30000) ? false : true;
    }

    /* renamed from: b */
    public int m1256b() {
        return this.f2472b;
    }

    /* renamed from: c */
    public CellLocation m1257c() {
        TelephonyManager telephonyManager = this.f2475e;
        CellLocation cellLocation = null;
        if (telephonyManager != null) {
            try {
                cellLocation = telephonyManager.getCellLocation();
                if (m1254a(cellLocation)) {
                    this.f2478h = cellLocation;
                }
            } catch (Throwable th) {
                C3614g.m1308a(th, "CgiManager", "getCellLocation");
            }
        }
        return cellLocation;
    }

    /* renamed from: d */
    public TelephonyManager m1258d() {
        return this.f2475e;
    }

    /* renamed from: e */
    public void m1259e() {
        m1247i();
    }

    /* renamed from: f */
    public void m1260f() {
        m1250l();
    }

    /* renamed from: g */
    public CellLocation m1261g() {
        TelephonyManager telephonyManager = this.f2475e;
        CellLocation cellLocation = null;
        if (telephonyManager != null) {
            try {
                cellLocation = telephonyManager.getCellLocation();
            } catch (Throwable th) {
                C3614g.m1308a(th, "CgiManager", "getSim1Cgi4");
            }
            if (!m1254a(cellLocation)) {
                try {
                    cellLocation = m1251a((List<?>) C3614g.m1301a(telephonyManager, "getAllCellInfo", new Object[0]));
                } catch (NoSuchMethodException unused) {
                } catch (Throwable th2) {
                    C3614g.m1308a(th2, "CgiManager", "getSim1Cgi2");
                }
                if (!m1254a(cellLocation)) {
                    try {
                        Object m1301a = C3614g.m1301a(telephonyManager, "getCellLocationExt", 1);
                        if (m1301a != null) {
                            cellLocation = (CellLocation) m1301a;
                        }
                    } catch (NoSuchMethodException unused2) {
                    } catch (Throwable th3) {
                        C3614g.m1308a(th3, "CgiManager", "getSim1Cgi1");
                    }
                    if (!m1254a(cellLocation)) {
                        try {
                            Object m1301a2 = C3614g.m1301a(telephonyManager, "getCellLocationGemini", 1);
                            if (m1301a2 != null) {
                                cellLocation = (CellLocation) m1301a2;
                            }
                        } catch (NoSuchMethodException unused3) {
                        } catch (Throwable th4) {
                            C3614g.m1308a(th4, "CgiManager", "getSim1Cgi");
                        }
                        if (m1254a(cellLocation)) {
                        }
                    }
                }
            }
        }
        return cellLocation;
    }
}
