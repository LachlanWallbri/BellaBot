package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.mirsdk.SolicitService;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.C5865b;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.AbstractC5927k;
import com.tencent.bugly.proguard.C5896a;
import com.tencent.bugly.proguard.C5907ak;
import com.tencent.bugly.proguard.C5909am;
import com.tencent.bugly.proguard.C5910an;
import com.tencent.bugly.proguard.C5911ao;
import com.tencent.bugly.proguard.C5912ap;
import com.tencent.bugly.proguard.C5932p;
import com.tencent.bugly.proguard.C5934r;
import com.tencent.bugly.proguard.C5937u;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import com.tencent.bugly.proguard.InterfaceC5931o;
import com.tencent.bugly.proguard.InterfaceC5936t;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.b */
/* loaded from: classes7.dex */
public final class C5886b {

    /* renamed from: a */
    private static int f7908a;

    /* renamed from: b */
    private Context f7909b;

    /* renamed from: c */
    private C5937u f7910c;

    /* renamed from: d */
    private C5932p f7911d;

    /* renamed from: e */
    private C5876a f7912e;

    /* renamed from: f */
    private InterfaceC5931o f7913f;

    /* renamed from: g */
    private BuglyStrategy.C5863a f7914g;

    public C5886b(int i, Context context, C5937u c5937u, C5932p c5932p, C5876a c5876a, BuglyStrategy.C5863a c5863a, InterfaceC5931o interfaceC5931o) {
        f7908a = i;
        this.f7909b = context;
        this.f7910c = c5937u;
        this.f7911d = c5932p;
        this.f7912e = c5876a;
        this.f7914g = c5863a;
        this.f7913f = interfaceC5931o;
    }

    /* renamed from: a */
    private static List<C5879a> m3530a(List<C5879a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (C5879a c5879a : list) {
            if (c5879a.f7877d && c5879a.f7875b <= currentTimeMillis - 86400000) {
                arrayList.add(c5879a);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private CrashDetailBean m3527a(List<C5879a> list, CrashDetailBean crashDetailBean) {
        List<CrashDetailBean> m3535b;
        String[] split;
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2 = null;
        ArrayList arrayList = new ArrayList(10);
        for (C5879a c5879a : list) {
            if (c5879a.f7878e) {
                arrayList.add(c5879a);
            }
        }
        if (arrayList.size() > 0 && (m3535b = m3535b(arrayList)) != null && m3535b.size() > 0) {
            Collections.sort(m3535b);
            CrashDetailBean crashDetailBean3 = null;
            for (int i = 0; i < m3535b.size(); i++) {
                CrashDetailBean crashDetailBean4 = m3535b.get(i);
                if (i == 0) {
                    crashDetailBean3 = crashDetailBean4;
                } else if (crashDetailBean4.f7866s != null && (split = crashDetailBean4.f7866s.split("\n")) != null) {
                    for (String str : split) {
                        if (!crashDetailBean3.f7866s.contains(str)) {
                            crashDetailBean3.f7867t++;
                            crashDetailBean3.f7866s += str + "\n";
                        }
                    }
                }
            }
            crashDetailBean2 = crashDetailBean3;
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.f7857j = true;
            crashDetailBean.f7867t = 0;
            crashDetailBean.f7866s = "";
            crashDetailBean2 = crashDetailBean;
        }
        for (C5879a c5879a2 : list) {
            if (!c5879a2.f7878e && !c5879a2.f7877d) {
                String str2 = crashDetailBean2.f7866s;
                StringBuilder sb = new StringBuilder();
                sb.append(c5879a2.f7875b);
                if (!str2.contains(sb.toString())) {
                    crashDetailBean2.f7867t++;
                    crashDetailBean2.f7866s += c5879a2.f7875b + "\n";
                }
            }
        }
        if (crashDetailBean2.f7865r != crashDetailBean.f7865r) {
            String str3 = crashDetailBean2.f7866s;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.f7865r);
            if (!str3.contains(sb2.toString())) {
                crashDetailBean2.f7867t++;
                crashDetailBean2.f7866s += crashDetailBean.f7865r + "\n";
            }
        }
        return crashDetailBean2;
    }

    /* renamed from: a */
    public final boolean m3542a(CrashDetailBean crashDetailBean) {
        return m3543a(crashDetailBean, -123456789);
    }

    /* renamed from: a */
    public final boolean m3543a(CrashDetailBean crashDetailBean, int i) {
        if (crashDetailBean == null) {
            return true;
        }
        if (C5887c.f7930n != null && !C5887c.f7930n.isEmpty()) {
            C5940x.m3823c("Crash filter for crash stack is: %s", C5887c.f7930n);
            if (crashDetailBean.f7864q.contains(C5887c.f7930n)) {
                C5940x.m3824d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (C5887c.f7931o != null && !C5887c.f7931o.isEmpty()) {
            C5940x.m3823c("Crash regular filter for crash stack is: %s", C5887c.f7931o);
            if (Pattern.compile(C5887c.f7931o).matcher(crashDetailBean.f7864q).find()) {
                C5940x.m3824d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        int i2 = crashDetailBean.f7849b;
        String str = crashDetailBean.f7861n;
        String str2 = crashDetailBean.f7862o;
        String str3 = crashDetailBean.f7863p;
        String str4 = crashDetailBean.f7864q;
        long j = crashDetailBean.f7865r;
        String str5 = crashDetailBean.f7860m;
        String str6 = crashDetailBean.f7852e;
        String str7 = crashDetailBean.f7850c;
        String str8 = crashDetailBean.f7825A;
        String str9 = crashDetailBean.f7826B;
        if (this.f7913f != null) {
            C5940x.m3823c("Calling 'onCrashSaving' of RQD crash listener.", new Object[0]);
            if (!this.f7913f.m3733c()) {
                C5940x.m3824d("Crash listener 'onCrashSaving' return 'false' thus will not handle this crash.", new Object[0]);
                return true;
            }
        }
        if (crashDetailBean.f7849b != 2) {
            C5934r c5934r = new C5934r();
            c5934r.f8211b = 1;
            c5934r.f8212c = crashDetailBean.f7825A;
            c5934r.f8213d = crashDetailBean.f7826B;
            c5934r.f8214e = crashDetailBean.f7865r;
            this.f7911d.m3763b(1);
            this.f7911d.m3762a(c5934r);
            C5940x.m3821b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            C5940x.m3821b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<C5879a> m3534b = m3534b();
        ArrayList arrayList = null;
        if (m3534b != null && m3534b.size() > 0) {
            arrayList = new ArrayList(10);
            ArrayList arrayList2 = new ArrayList(10);
            arrayList.addAll(m3530a(m3534b));
            m3534b.removeAll(arrayList);
            if (!C5865b.f7644c && C5887c.f7920d) {
                boolean z = false;
                for (C5879a c5879a : m3534b) {
                    if (crashDetailBean.f7868u.equals(c5879a.f7876c)) {
                        if (c5879a.f7878e) {
                            z = true;
                        }
                        arrayList2.add(c5879a);
                    }
                }
                if (z || arrayList2.size() >= C5887c.f7919c) {
                    C5940x.m3818a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean m3527a = m3527a(arrayList2, crashDetailBean);
                    for (C5879a c5879a2 : arrayList2) {
                        if (c5879a2.f7874a != m3527a.f7848a) {
                            arrayList.add(c5879a2);
                        }
                    }
                    m3546d(m3527a);
                    m3536c(arrayList);
                    C5940x.m3821b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                    return true;
                }
            }
        }
        m3546d(crashDetailBean);
        if (arrayList != null && !arrayList.isEmpty()) {
            m3536c(arrayList);
        }
        C5940x.m3821b("[crash] save crash success", new Object[0]);
        return false;
    }

    /* renamed from: a */
    public final List<CrashDetailBean> m3539a() {
        StrategyBean m3497c = C5876a.m3487a().m3497c();
        if (m3497c == null) {
            C5940x.m3824d("have not synced remote!", new Object[0]);
            return null;
        }
        if (!m3497c.f7790g) {
            C5940x.m3824d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            C5940x.m3821b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long m3876b = C5942z.m3876b();
        List<C5879a> m3534b = m3534b();
        C5940x.m3823c("Size of crash list loaded from DB: %s", Integer.valueOf(m3534b.size()));
        if (m3534b == null || m3534b.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(m3530a(m3534b));
        m3534b.removeAll(arrayList);
        Iterator<C5879a> it = m3534b.iterator();
        while (it.hasNext()) {
            C5879a next = it.next();
            if (next.f7875b < m3876b - C5887c.f7923g) {
                it.remove();
                arrayList.add(next);
            } else if (next.f7877d) {
                if (next.f7875b >= currentTimeMillis - 86400000) {
                    it.remove();
                } else if (!next.f7878e) {
                    it.remove();
                    arrayList.add(next);
                }
            } else if (next.f7879f >= 3 && next.f7875b < currentTimeMillis - 86400000) {
                it.remove();
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0) {
            m3536c(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        List<CrashDetailBean> m3535b = m3535b(m3534b);
        if (m3535b != null && m3535b.size() > 0) {
            String str = C5873a.m3390b().f7762j;
            Iterator<CrashDetailBean> it2 = m3535b.iterator();
            while (it2.hasNext()) {
                CrashDetailBean next2 = it2.next();
                if (!str.equals(next2.f7853f)) {
                    it2.remove();
                    arrayList2.add(next2);
                }
            }
        }
        if (arrayList2.size() > 0) {
            m3537d(arrayList2);
        }
        return m3535b;
    }

    /* renamed from: b */
    public final void m3544b(CrashDetailBean crashDetailBean) {
        if (this.f7913f != null) {
            C5940x.m3823c("Calling 'onCrashHandleEnd' of RQD crash listener.", new Object[0]);
            InterfaceC5931o interfaceC5931o = this.f7913f;
            int i = crashDetailBean.f7849b;
        }
    }

    /* renamed from: a */
    public final void m3540a(CrashDetailBean crashDetailBean, long j, boolean z) {
        if (C5887c.f7928l) {
            C5940x.m3818a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            m3541a(arrayList, SolicitService.CAMERA_OPEN_TIME_OUT, z, crashDetailBean.f7849b == 7, z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00a7 A[Catch: all -> 0x00e9, TryCatch #0 {all -> 0x00e9, blocks: (B:20:0x0041, B:22:0x0047, B:23:0x004c, B:25:0x0053, B:26:0x0058, B:29:0x0064, B:32:0x006e, B:36:0x0077, B:37:0x0087, B:39:0x008d, B:42:0x00a7, B:44:0x00af, B:46:0x00b5, B:48:0x00bd, B:50:0x00c5, B:52:0x00cd, B:54:0x00d4, B:56:0x00e0, B:58:0x009d, B:60:0x0056, B:61:0x004a), top: B:19:0x0041 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00af A[Catch: all -> 0x00e9, TryCatch #0 {all -> 0x00e9, blocks: (B:20:0x0041, B:22:0x0047, B:23:0x004c, B:25:0x0053, B:26:0x0058, B:29:0x0064, B:32:0x006e, B:36:0x0077, B:37:0x0087, B:39:0x008d, B:42:0x00a7, B:44:0x00af, B:46:0x00b5, B:48:0x00bd, B:50:0x00c5, B:52:0x00cd, B:54:0x00d4, B:56:0x00e0, B:58:0x009d, B:60:0x0056, B:61:0x004a), top: B:19:0x0041 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m3541a(final List<CrashDetailBean> list, long j, boolean z, boolean z2, boolean z3) {
        C5937u c5937u;
        C5911ao c5911ao;
        if (!C5873a.m3389a(this.f7909b).f7757e || (c5937u = this.f7910c) == null) {
            return;
        }
        if (!z3 && !c5937u.m3803b(C5887c.f7917a)) {
            return;
        }
        StrategyBean m3497c = this.f7912e.m3497c();
        if (!m3497c.f7790g) {
            C5940x.m3824d("remote report is disable!", new Object[0]);
            C5940x.m3821b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
            return;
        }
        if (list == null || list.size() == 0) {
            return;
        }
        try {
            String str = this.f7910c.f8221a ? m3497c.f7802s : m3497c.f7803t;
            String str2 = this.f7910c.f8221a ? StrategyBean.f7786c : StrategyBean.f7784a;
            int i = this.f7910c.f8221a ? 830 : 630;
            Context context = this.f7909b;
            C5873a m3390b = C5873a.m3390b();
            if (context != null && list != null && list.size() != 0 && m3390b != null) {
                c5911ao = new C5911ao();
                c5911ao.f8069a = new ArrayList<>();
                Iterator<CrashDetailBean> it = list.iterator();
                while (it.hasNext()) {
                    c5911ao.f8069a.add(m3529a(context, it.next(), m3390b));
                }
                if (c5911ao != null) {
                    C5940x.m3824d("create eupPkg fail!", new Object[0]);
                    return;
                }
                byte[] m3620a = C5896a.m3620a((AbstractC5927k) c5911ao);
                if (m3620a == null) {
                    C5940x.m3824d("send encode fail!", new Object[0]);
                    return;
                }
                C5912ap m3611a = C5896a.m3611a(this.f7909b, i, m3620a);
                if (m3611a == null) {
                    C5940x.m3824d("request package is null.", new Object[0]);
                    return;
                }
                InterfaceC5936t interfaceC5936t = new InterfaceC5936t() { // from class: com.tencent.bugly.crashreport.crash.b.1
                    @Override // com.tencent.bugly.proguard.InterfaceC5936t
                    /* renamed from: a */
                    public final void mo3353a(boolean z4) {
                        C5886b c5886b = C5886b.this;
                        C5886b.m3532a(z4, (List<CrashDetailBean>) list);
                    }
                };
                if (z) {
                    this.f7910c.m3795a(f7908a, m3611a, str, str2, interfaceC5936t, j, z2);
                    return;
                } else {
                    this.f7910c.m3796a(f7908a, m3611a, str, str2, interfaceC5936t, false);
                    return;
                }
            }
            C5940x.m3824d("enEXPPkg args == null!", new Object[0]);
            c5911ao = null;
            if (c5911ao != null) {
            }
        } catch (Throwable th) {
            C5940x.m3825e("req cr error %s", th.toString());
            if (C5940x.m3822b(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m3532a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            C5940x.m3823c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                C5940x.m3823c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.f7850c, Integer.valueOf(crashDetailBean.f7859l), Boolean.valueOf(crashDetailBean.f7851d), Boolean.valueOf(crashDetailBean.f7857j));
                crashDetailBean.f7859l++;
                crashDetailBean.f7851d = z;
                C5940x.m3823c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.f7850c, Integer.valueOf(crashDetailBean.f7859l), Boolean.valueOf(crashDetailBean.f7851d), Boolean.valueOf(crashDetailBean.f7857j));
            }
            Iterator<CrashDetailBean> it = list.iterator();
            while (it.hasNext()) {
                C5887c.m3547a().m3553a(it.next());
            }
            C5940x.m3823c("update state size %d", Integer.valueOf(list.size()));
        }
        if (z) {
            return;
        }
        C5940x.m3821b("[crash] upload fail.", new Object[0]);
    }

    /* renamed from: c */
    public final void m3545c(CrashDetailBean crashDetailBean) {
        int i;
        Map<String, String> map;
        String str;
        HashMap hashMap;
        if (crashDetailBean == null) {
            return;
        }
        if (this.f7914g == null && this.f7913f == null) {
            return;
        }
        try {
            C5940x.m3818a("[crash callback] start user's callback:onCrashHandleStart()", new Object[0]);
            switch (crashDetailBean.f7849b) {
                case 0:
                    i = 0;
                    break;
                case 1:
                    i = 2;
                    break;
                case 2:
                    i = 1;
                    break;
                case 3:
                    i = 4;
                    break;
                case 4:
                    i = 3;
                    break;
                case 5:
                    i = 5;
                    break;
                case 6:
                    i = 6;
                    break;
                case 7:
                    i = 7;
                    break;
                default:
                    return;
            }
            int i2 = crashDetailBean.f7849b;
            String str2 = crashDetailBean.f7861n;
            String str3 = crashDetailBean.f7863p;
            String str4 = crashDetailBean.f7864q;
            long j = crashDetailBean.f7865r;
            byte[] bArr = null;
            if (this.f7913f != null) {
                C5940x.m3823c("Calling 'onCrashHandleStart' of RQD crash listener.", new Object[0]);
                InterfaceC5931o interfaceC5931o = this.f7913f;
                C5940x.m3823c("Calling 'getCrashExtraMessage' of RQD crash listener.", new Object[0]);
                String m3732b = this.f7913f.m3732b();
                if (m3732b != null) {
                    hashMap = new HashMap(1);
                    hashMap.put("userData", m3732b);
                } else {
                    hashMap = null;
                }
                map = hashMap;
            } else if (this.f7914g != null) {
                C5940x.m3823c("Calling 'onCrashHandleStart' of Bugly crash listener.", new Object[0]);
                map = this.f7914g.onCrashHandleStart(i, crashDetailBean.f7861n, crashDetailBean.f7862o, crashDetailBean.f7864q);
            } else {
                map = null;
            }
            if (map != null && map.size() > 0) {
                crashDetailBean.f7839O = new LinkedHashMap(map.size());
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (!C5942z.m3868a(entry.getKey())) {
                        String key = entry.getKey();
                        if (key.length() > 100) {
                            key = key.substring(0, 100);
                            C5940x.m3824d("setted key length is over limit %d substring to %s", 100, key);
                        }
                        if (!C5942z.m3868a(entry.getValue()) && entry.getValue().length() > 30000) {
                            str = entry.getValue().substring(entry.getValue().length() - BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH);
                            C5940x.m3824d("setted %s value length is over limit %d substring", key, Integer.valueOf(BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH));
                        } else {
                            str = entry.getValue();
                        }
                        crashDetailBean.f7839O.put(key, str);
                        C5940x.m3818a("add setted key %s value size:%d", key, Integer.valueOf(str.length()));
                    }
                }
            }
            C5940x.m3818a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
            if (this.f7913f != null) {
                C5940x.m3823c("Calling 'getCrashExtraData' of RQD crash listener.", new Object[0]);
                bArr = this.f7913f.m3731a();
            } else if (this.f7914g != null) {
                C5940x.m3823c("Calling 'onCrashHandleStart2GetExtraDatas' of Bugly crash listener.", new Object[0]);
                bArr = this.f7914g.onCrashHandleStart2GetExtraDatas(i, crashDetailBean.f7861n, crashDetailBean.f7862o, crashDetailBean.f7864q);
            }
            crashDetailBean.f7844T = bArr;
            if (bArr != null) {
                if (bArr.length > 30000) {
                    C5940x.m3824d("extra bytes size %d is over limit %d will drop over part", Integer.valueOf(bArr.length), Integer.valueOf(BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH));
                    crashDetailBean.f7844T = Arrays.copyOf(bArr, BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH);
                }
                C5940x.m3818a("add extra bytes %d ", Integer.valueOf(bArr.length));
            }
        } catch (Throwable th) {
            C5940x.m3824d("crash handle callback something wrong! %s", th.getClass().getName());
            if (C5940x.m3819a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    private static ContentValues m3538e(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.f7848a > 0) {
                contentValues.put(TransferTable.COLUMN_ID, Long.valueOf(crashDetailBean.f7848a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.f7865r));
            contentValues.put("_s1", crashDetailBean.f7868u);
            int i = 1;
            contentValues.put("_up", Integer.valueOf(crashDetailBean.f7851d ? 1 : 0));
            if (!crashDetailBean.f7857j) {
                i = 0;
            }
            contentValues.put("_me", Integer.valueOf(i));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.f7859l));
            contentValues.put("_dt", C5942z.m3871a(crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static CrashDetailBean m3526a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(TransferTable.COLUMN_ID));
            CrashDetailBean crashDetailBean = (CrashDetailBean) C5942z.m3851a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.f7848a = j;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: d */
    public final void m3546d(CrashDetailBean crashDetailBean) {
        ContentValues m3538e;
        if (crashDetailBean == null || (m3538e = m3538e(crashDetailBean)) == null) {
            return;
        }
        long m3755a = C5932p.m3740a().m3755a("t_cr", m3538e, (InterfaceC5931o) null, true);
        if (m3755a >= 0) {
            C5940x.m3823c("insert %s success!", "t_cr");
            crashDetailBean.f7848a = m3755a;
        }
    }

    /* renamed from: b */
    private List<CrashDetailBean> m3535b(List<C5879a> list) {
        Cursor cursor;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in ");
        sb.append("(");
        Iterator<C5879a> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().f7874a);
            sb.append(",");
        }
        StringBuilder sb2 = sb.toString().contains(",") ? new StringBuilder(sb.substring(0, sb.lastIndexOf(","))) : sb;
        sb2.append(")");
        String sb3 = sb2.toString();
        sb2.setLength(0);
        try {
            cursor = C5932p.m3740a().m3756a("t_cr", null, sb3, null, null, true);
            if (cursor == null) {
                return null;
            }
            try {
                ArrayList arrayList = new ArrayList();
                sb2.append("_id in ");
                sb2.append("(");
                int i = 0;
                while (cursor.moveToNext()) {
                    CrashDetailBean m3526a = m3526a(cursor);
                    if (m3526a != null) {
                        arrayList.add(m3526a);
                    } else {
                        try {
                            sb2.append(cursor.getLong(cursor.getColumnIndex(TransferTable.COLUMN_ID)));
                            sb2.append(",");
                            i++;
                        } catch (Throwable unused) {
                            C5940x.m3824d("unknown id!", new Object[0]);
                        }
                    }
                }
                if (sb2.toString().contains(",")) {
                    sb2 = new StringBuilder(sb2.substring(0, sb2.lastIndexOf(",")));
                }
                sb2.append(")");
                String sb4 = sb2.toString();
                if (i > 0) {
                    C5940x.m3824d("deleted %s illegal data %d", "t_cr", Integer.valueOf(C5932p.m3740a().m3754a("t_cr", sb4, (String[]) null, (InterfaceC5931o) null, true)));
                }
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!C5940x.m3819a(th)) {
                        th.printStackTrace();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    /* renamed from: b */
    private static C5879a m3533b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C5879a c5879a = new C5879a();
            c5879a.f7874a = cursor.getLong(cursor.getColumnIndex(TransferTable.COLUMN_ID));
            c5879a.f7875b = cursor.getLong(cursor.getColumnIndex("_tm"));
            c5879a.f7876c = cursor.getString(cursor.getColumnIndex("_s1"));
            c5879a.f7877d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            c5879a.f7878e = cursor.getInt(cursor.getColumnIndex("_me")) == 1;
            c5879a.f7879f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return c5879a;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    private List<C5879a> m3534b() {
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        try {
            cursor = C5932p.m3740a().m3756a("t_cr", new String[]{TransferTable.COLUMN_ID, "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, true);
            if (cursor == null) {
                return null;
            }
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("_id in ");
                sb.append("(");
                int i = 0;
                while (cursor.moveToNext()) {
                    C5879a m3533b = m3533b(cursor);
                    if (m3533b != null) {
                        arrayList.add(m3533b);
                    } else {
                        try {
                            sb.append(cursor.getLong(cursor.getColumnIndex(TransferTable.COLUMN_ID)));
                            sb.append(",");
                            i++;
                        } catch (Throwable unused) {
                            C5940x.m3824d("unknown id!", new Object[0]);
                        }
                    }
                }
                if (sb.toString().contains(",")) {
                    sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                }
                sb.append(")");
                String sb2 = sb.toString();
                sb.setLength(0);
                if (i > 0) {
                    C5940x.m3824d("deleted %s illegal data %d", "t_cr", Integer.valueOf(C5932p.m3740a().m3754a("t_cr", sb2, (String[]) null, (InterfaceC5931o) null, true)));
                }
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!C5940x.m3819a(th)) {
                        th.printStackTrace();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    /* renamed from: c */
    private static void m3536c(List<C5879a> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in ");
        sb.append("(");
        Iterator<C5879a> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().f7874a);
            sb.append(",");
        }
        StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
        sb2.append(")");
        String sb3 = sb2.toString();
        sb2.setLength(0);
        try {
            C5940x.m3823c("deleted %s data %d", "t_cr", Integer.valueOf(C5932p.m3740a().m3754a("t_cr", sb3, (String[]) null, (InterfaceC5931o) null, true)));
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: d */
    private static void m3537d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() == 0) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (CrashDetailBean crashDetailBean : list) {
                    sb.append(" or _id");
                    sb.append(" = ");
                    sb.append(crashDetailBean.f7848a);
                }
                String sb2 = sb.toString();
                if (sb2.length() > 0) {
                    sb2 = sb2.substring(4);
                }
                sb.setLength(0);
                C5940x.m3823c("deleted %s data %d", "t_cr", Integer.valueOf(C5932p.m3740a().m3754a("t_cr", sb2, (String[]) null, (InterfaceC5931o) null, true)));
            } catch (Throwable th) {
                if (C5940x.m3819a(th)) {
                    return;
                }
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static C5910an m3529a(Context context, CrashDetailBean crashDetailBean, C5873a c5873a) {
        C5909am m3528a;
        C5909am m3528a2;
        C5909am c5909am;
        if (context == null || crashDetailBean == null || c5873a == null) {
            C5940x.m3824d("enExp args == null", new Object[0]);
            return null;
        }
        C5910an c5910an = new C5910an();
        switch (crashDetailBean.f7849b) {
            case 0:
                c5910an.f8047a = crashDetailBean.f7857j ? ErrorCode.UNKNOWN_SUCCESS_CODE : "100";
                break;
            case 1:
                c5910an.f8047a = crashDetailBean.f7857j ? "201" : "101";
                break;
            case 2:
                c5910an.f8047a = crashDetailBean.f7857j ? "202" : "102";
                break;
            case 3:
                c5910an.f8047a = crashDetailBean.f7857j ? "203" : "103";
                break;
            case 4:
                c5910an.f8047a = crashDetailBean.f7857j ? "204" : "104";
                break;
            case 5:
                c5910an.f8047a = crashDetailBean.f7857j ? "207" : "107";
                break;
            case 6:
                c5910an.f8047a = crashDetailBean.f7857j ? "206" : "106";
                break;
            case 7:
                c5910an.f8047a = crashDetailBean.f7857j ? "208" : "108";
                break;
            default:
                C5940x.m3825e("crash type error! %d", Integer.valueOf(crashDetailBean.f7849b));
                break;
        }
        c5910an.f8048b = crashDetailBean.f7865r;
        c5910an.f8049c = crashDetailBean.f7861n;
        c5910an.f8050d = crashDetailBean.f7862o;
        c5910an.f8051e = crashDetailBean.f7863p;
        c5910an.f8053g = crashDetailBean.f7864q;
        c5910an.f8054h = crashDetailBean.f7873z;
        c5910an.f8055i = crashDetailBean.f7850c;
        c5910an.f8056j = null;
        c5910an.f8058l = crashDetailBean.f7860m;
        c5910an.f8059m = crashDetailBean.f7852e;
        c5910an.f8052f = crashDetailBean.f7826B;
        c5910an.f8066t = C5873a.m3390b().m3431i();
        c5910an.f8060n = null;
        if (crashDetailBean.f7856i != null && crashDetailBean.f7856i.size() > 0) {
            c5910an.f8061o = new ArrayList<>();
            for (Map.Entry<String, PlugInBean> entry : crashDetailBean.f7856i.entrySet()) {
                C5907ak c5907ak = new C5907ak();
                c5907ak.f8027a = entry.getValue().f7698a;
                c5907ak.f8029c = entry.getValue().f7700c;
                c5907ak.f8030d = entry.getValue().f7699b;
                c5907ak.f8028b = c5873a.m3440r();
                c5910an.f8061o.add(c5907ak);
            }
        }
        if (crashDetailBean.f7855h != null && crashDetailBean.f7855h.size() > 0) {
            c5910an.f8062p = new ArrayList<>();
            for (Map.Entry<String, PlugInBean> entry2 : crashDetailBean.f7855h.entrySet()) {
                C5907ak c5907ak2 = new C5907ak();
                c5907ak2.f8027a = entry2.getValue().f7698a;
                c5907ak2.f8029c = entry2.getValue().f7700c;
                c5907ak2.f8030d = entry2.getValue().f7699b;
                c5910an.f8062p.add(c5907ak2);
            }
        }
        if (crashDetailBean.f7857j) {
            c5910an.f8057k = crashDetailBean.f7867t;
            if (crashDetailBean.f7866s != null && crashDetailBean.f7866s.length() > 0) {
                if (c5910an.f8063q == null) {
                    c5910an.f8063q = new ArrayList<>();
                }
                try {
                    c5910an.f8063q.add(new C5909am((byte) 1, "alltimes.txt", crashDetailBean.f7866s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    c5910an.f8063q = null;
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(c5910an.f8057k);
            objArr[1] = Integer.valueOf(c5910an.f8063q != null ? c5910an.f8063q.size() : 0);
            C5940x.m3823c("crashcount:%d sz:%d", objArr);
        }
        if (crashDetailBean.f7870w != null) {
            if (c5910an.f8063q == null) {
                c5910an.f8063q = new ArrayList<>();
            }
            try {
                c5910an.f8063q.add(new C5909am((byte) 1, "log.txt", crashDetailBean.f7870w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                c5910an.f8063q = null;
            }
        }
        if (crashDetailBean.f7871x != null) {
            if (c5910an.f8063q == null) {
                c5910an.f8063q = new ArrayList<>();
            }
            try {
                c5910an.f8063q.add(new C5909am((byte) 1, "jniLog.txt", crashDetailBean.f7871x.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                c5910an.f8063q = null;
            }
        }
        if (!C5942z.m3868a(crashDetailBean.f7845U)) {
            if (c5910an.f8063q == null) {
                c5910an.f8063q = new ArrayList<>();
            }
            try {
                c5909am = new C5909am((byte) 1, "crashInfos.txt", crashDetailBean.f7845U.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e4) {
                e4.printStackTrace();
                c5909am = null;
            }
            if (c5909am != null) {
                C5940x.m3823c("attach crash infos", new Object[0]);
                c5910an.f8063q.add(c5909am);
            }
        }
        if (crashDetailBean.f7846V != null) {
            if (c5910an.f8063q == null) {
                c5910an.f8063q = new ArrayList<>();
            }
            C5909am m3528a3 = m3528a("backupRecord.zip", context, crashDetailBean.f7846V);
            if (m3528a3 != null) {
                C5940x.m3823c("attach backup record", new Object[0]);
                c5910an.f8063q.add(m3528a3);
            }
        }
        if (crashDetailBean.f7872y != null && crashDetailBean.f7872y.length > 0) {
            C5909am c5909am2 = new C5909am((byte) 2, "buglylog.zip", crashDetailBean.f7872y);
            C5940x.m3823c("attach user log", new Object[0]);
            if (c5910an.f8063q == null) {
                c5910an.f8063q = new ArrayList<>();
            }
            c5910an.f8063q.add(c5909am2);
        }
        if (crashDetailBean.f7849b == 3) {
            if (c5910an.f8063q == null) {
                c5910an.f8063q = new ArrayList<>();
            }
            C5940x.m3823c("crashBean.userDatas:%s", crashDetailBean.f7839O);
            if (crashDetailBean.f7839O != null && crashDetailBean.f7839O.containsKey("BUGLY_CR_01")) {
                try {
                    if (!TextUtils.isEmpty(crashDetailBean.f7839O.get("BUGLY_CR_01"))) {
                        c5910an.f8063q.add(new C5909am((byte) 1, "anrMessage.txt", crashDetailBean.f7839O.get("BUGLY_CR_01").getBytes("utf-8")));
                        C5940x.m3823c("attach anr message", new Object[0]);
                    }
                } catch (UnsupportedEncodingException e5) {
                    e5.printStackTrace();
                    c5910an.f8063q = null;
                }
                crashDetailBean.f7839O.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.f7869v != null && (m3528a2 = m3528a("trace.zip", context, crashDetailBean.f7869v)) != null) {
                C5940x.m3823c("attach traces", new Object[0]);
                c5910an.f8063q.add(m3528a2);
            }
        }
        if (crashDetailBean.f7849b == 1) {
            if (c5910an.f8063q == null) {
                c5910an.f8063q = new ArrayList<>();
            }
            if (crashDetailBean.f7869v != null && (m3528a = m3528a("tomb.zip", context, crashDetailBean.f7869v)) != null) {
                C5940x.m3823c("attach tombs", new Object[0]);
                c5910an.f8063q.add(m3528a);
            }
        }
        if (c5873a.f7704C != null && !c5873a.f7704C.isEmpty()) {
            if (c5910an.f8063q == null) {
                c5910an.f8063q = new ArrayList<>();
            }
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = c5873a.f7704C.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
            try {
                c5910an.f8063q.add(new C5909am((byte) 1, "martianlog.txt", sb.toString().getBytes("utf-8")));
                C5940x.m3823c("attach pageTracingList", new Object[0]);
            } catch (UnsupportedEncodingException e6) {
                e6.printStackTrace();
            }
        }
        if (crashDetailBean.f7844T != null && crashDetailBean.f7844T.length > 0) {
            if (c5910an.f8063q == null) {
                c5910an.f8063q = new ArrayList<>();
            }
            c5910an.f8063q.add(new C5909am((byte) 1, "userExtraByteData", crashDetailBean.f7844T));
            C5940x.m3823c("attach extraData", new Object[0]);
        }
        c5910an.f8064r = new HashMap();
        Map<String, String> map = c5910an.f8064r;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(crashDetailBean.f7827C);
        map.put("A9", sb2.toString());
        Map<String, String> map2 = c5910an.f8064r;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(crashDetailBean.f7828D);
        map2.put("A11", sb3.toString());
        Map<String, String> map3 = c5910an.f8064r;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(crashDetailBean.f7829E);
        map3.put("A10", sb4.toString());
        c5910an.f8064r.put("A23", crashDetailBean.f7853f);
        c5910an.f8064r.put("A7", c5873a.f7758f);
        c5910an.f8064r.put("A6", c5873a.m3441s());
        c5910an.f8064r.put("A5", c5873a.m3440r());
        c5910an.f8064r.put("A22", c5873a.m3430h());
        Map<String, String> map4 = c5910an.f8064r;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(crashDetailBean.f7831G);
        map4.put("A2", sb5.toString());
        Map<String, String> map5 = c5910an.f8064r;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(crashDetailBean.f7830F);
        map5.put("A1", sb6.toString());
        c5910an.f8064r.put("A24", c5873a.f7760h);
        Map<String, String> map6 = c5910an.f8064r;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(crashDetailBean.f7832H);
        map6.put("A17", sb7.toString());
        c5910an.f8064r.put("A3", c5873a.m3433k());
        c5910an.f8064r.put("A16", c5873a.m3435m());
        c5910an.f8064r.put("A25", c5873a.m3436n());
        c5910an.f8064r.put("A14", c5873a.m3434l());
        c5910an.f8064r.put("A15", c5873a.m3445w());
        Map<String, String> map7 = c5910an.f8064r;
        StringBuilder sb8 = new StringBuilder();
        sb8.append(c5873a.m3446x());
        map7.put("A13", sb8.toString());
        c5910an.f8064r.put("A34", crashDetailBean.f7825A);
        if (c5873a.f7776x != null) {
            c5910an.f8064r.put("productIdentify", c5873a.f7776x);
        }
        try {
            c5910an.f8064r.put("A26", URLEncoder.encode(crashDetailBean.f7833I, "utf-8"));
        } catch (UnsupportedEncodingException e7) {
            e7.printStackTrace();
        }
        if (crashDetailBean.f7849b == 1) {
            c5910an.f8064r.put("A27", crashDetailBean.f7835K);
            c5910an.f8064r.put("A28", crashDetailBean.f7834J);
            Map<String, String> map8 = c5910an.f8064r;
            StringBuilder sb9 = new StringBuilder();
            sb9.append(crashDetailBean.f7858k);
            map8.put("A29", sb9.toString());
        }
        c5910an.f8064r.put("A30", crashDetailBean.f7836L);
        Map<String, String> map9 = c5910an.f8064r;
        StringBuilder sb10 = new StringBuilder();
        sb10.append(crashDetailBean.f7837M);
        map9.put("A18", sb10.toString());
        Map<String, String> map10 = c5910an.f8064r;
        StringBuilder sb11 = new StringBuilder();
        sb11.append(!crashDetailBean.f7838N);
        map10.put("A36", sb11.toString());
        Map<String, String> map11 = c5910an.f8064r;
        StringBuilder sb12 = new StringBuilder();
        sb12.append(c5873a.f7769q);
        map11.put("F02", sb12.toString());
        Map<String, String> map12 = c5910an.f8064r;
        StringBuilder sb13 = new StringBuilder();
        sb13.append(c5873a.f7770r);
        map12.put("F03", sb13.toString());
        c5910an.f8064r.put("F04", c5873a.m3424e());
        Map<String, String> map13 = c5910an.f8064r;
        StringBuilder sb14 = new StringBuilder();
        sb14.append(c5873a.f7771s);
        map13.put("F05", sb14.toString());
        c5910an.f8064r.put("F06", c5873a.f7768p);
        c5910an.f8064r.put("F08", c5873a.f7774v);
        c5910an.f8064r.put("F09", c5873a.f7775w);
        Map<String, String> map14 = c5910an.f8064r;
        StringBuilder sb15 = new StringBuilder();
        sb15.append(c5873a.f7772t);
        map14.put("F10", sb15.toString());
        if (crashDetailBean.f7840P >= 0) {
            Map<String, String> map15 = c5910an.f8064r;
            StringBuilder sb16 = new StringBuilder();
            sb16.append(crashDetailBean.f7840P);
            map15.put("C01", sb16.toString());
        }
        if (crashDetailBean.f7841Q >= 0) {
            Map<String, String> map16 = c5910an.f8064r;
            StringBuilder sb17 = new StringBuilder();
            sb17.append(crashDetailBean.f7841Q);
            map16.put("C02", sb17.toString());
        }
        if (crashDetailBean.f7842R != null && crashDetailBean.f7842R.size() > 0) {
            for (Map.Entry<String, String> entry3 : crashDetailBean.f7842R.entrySet()) {
                c5910an.f8064r.put("C03_" + entry3.getKey(), entry3.getValue());
            }
        }
        if (crashDetailBean.f7843S != null && crashDetailBean.f7843S.size() > 0) {
            for (Map.Entry<String, String> entry4 : crashDetailBean.f7843S.entrySet()) {
                c5910an.f8064r.put("C04_" + entry4.getKey(), entry4.getValue());
            }
        }
        c5910an.f8065s = null;
        if (crashDetailBean.f7839O != null && crashDetailBean.f7839O.size() > 0) {
            c5910an.f8065s = crashDetailBean.f7839O;
            C5940x.m3818a("setted message size %d", Integer.valueOf(c5910an.f8065s.size()));
        }
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.f7861n;
        objArr2[1] = crashDetailBean.f7850c;
        objArr2[2] = c5873a.m3424e();
        objArr2[3] = Long.valueOf((crashDetailBean.f7865r - crashDetailBean.f7837M) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.f7858k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.f7838N);
        objArr2[6] = Boolean.valueOf(crashDetailBean.f7857j);
        objArr2[7] = Boolean.valueOf(crashDetailBean.f7849b == 1);
        objArr2[8] = Integer.valueOf(crashDetailBean.f7867t);
        objArr2[9] = crashDetailBean.f7866s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.f7851d);
        objArr2[11] = Integer.valueOf(c5910an.f8064r.size());
        C5940x.m3823c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr2);
        return c5910an;
    }

    /* renamed from: a */
    private static C5909am m3528a(String str, Context context, String str2) {
        FileInputStream fileInputStream;
        if (str2 == null || context == null) {
            C5940x.m3824d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        C5940x.m3823c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (!C5942z.m3866a(file, file2, 5000)) {
            C5940x.m3824d("zip fail!", new Object[0]);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            fileInputStream = new FileInputStream(file2);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
                byteArrayOutputStream.flush();
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            C5940x.m3823c("read bytes :%d", Integer.valueOf(byteArray.length));
            C5909am c5909am = new C5909am((byte) 2, file2.getName(), byteArray);
            try {
                fileInputStream.close();
            } catch (IOException e) {
                if (!C5940x.m3819a(e)) {
                    e.printStackTrace();
                }
            }
            if (file2.exists()) {
                C5940x.m3823c("del tmp", new Object[0]);
                file2.delete();
            }
            return c5909am;
        } catch (Throwable th2) {
            th = th2;
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        if (!C5940x.m3819a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (file2.exists()) {
                    C5940x.m3823c("del tmp", new Object[0]);
                    file2.delete();
                }
                return null;
            } catch (Throwable th3) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        if (!C5940x.m3819a(e3)) {
                            e3.printStackTrace();
                        }
                    }
                }
                if (file2.exists()) {
                    C5940x.m3823c("del tmp", new Object[0]);
                    file2.delete();
                }
                throw th3;
            }
        }
    }

    /* renamed from: a */
    public static void m3531a(String str, String str2, String str3, String str4, String str5, CrashDetailBean crashDetailBean) {
        String str6;
        C5873a m3390b = C5873a.m3390b();
        if (m3390b == null) {
            return;
        }
        C5940x.m3825e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
        C5940x.m3825e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
        C5940x.m3825e("# PKG NAME: %s", m3390b.f7755c);
        C5940x.m3825e("# APP VER: %s", m3390b.f7762j);
        C5940x.m3825e("# LAUNCH TIME: %s", C5942z.m3858a(new Date(C5873a.m3390b().f7728a)));
        C5940x.m3825e("# CRASH TYPE: %s", str);
        C5940x.m3825e("# CRASH TIME: %s", str2);
        C5940x.m3825e("# CRASH PROCESS: %s", str3);
        C5940x.m3825e("# CRASH THREAD: %s", str4);
        if (crashDetailBean != null) {
            C5940x.m3825e("# REPORT ID: %s", crashDetailBean.f7850c);
            Object[] objArr = new Object[2];
            objArr[0] = m3390b.f7759g;
            objArr[1] = m3390b.m3446x().booleanValue() ? "ROOTED" : "UNROOT";
            C5940x.m3825e("# CRASH DEVICE: %s %s", objArr);
            C5940x.m3825e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f7827C), Long.valueOf(crashDetailBean.f7828D), Long.valueOf(crashDetailBean.f7829E));
            C5940x.m3825e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f7830F), Long.valueOf(crashDetailBean.f7831G), Long.valueOf(crashDetailBean.f7832H));
            if (!C5942z.m3868a(crashDetailBean.f7835K)) {
                C5940x.m3825e("# EXCEPTION FIRED BY %s %s", crashDetailBean.f7835K, crashDetailBean.f7834J);
            } else if (crashDetailBean.f7849b == 3) {
                Object[] objArr2 = new Object[1];
                if (crashDetailBean.f7839O == null) {
                    str6 = "null";
                } else {
                    str6 = crashDetailBean.f7839O.get("BUGLY_CR_01");
                }
                objArr2[0] = str6;
                C5940x.m3825e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
            }
        }
        if (!C5942z.m3868a(str5)) {
            C5940x.m3825e("# CRASH STACK: ", new Object[0]);
            C5940x.m3825e(str5, new Object[0]);
        }
        C5940x.m3825e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
    }
}
