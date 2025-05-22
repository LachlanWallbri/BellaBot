package com.iflytek.idata.extension;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.iflytek.cloud.thirdparty.C3722bg;
import com.iflytek.cloud.thirdparty.C3723bh;
import com.iflytek.idata.extension.C3752b;
import org.json.JSONObject;

/* renamed from: com.iflytek.idata.extension.c */
/* loaded from: classes3.dex */
public class RunnableC3753c implements Runnable {

    /* renamed from: a */
    private Context f3509a;

    /* renamed from: b */
    private C3752b.a f3510b = new C3752b.a() { // from class: com.iflytek.idata.extension.c.1
        @Override // com.iflytek.idata.extension.C3752b.a
        /* renamed from: a */
        public void mo2328a(C3752b c3752b, byte[] bArr) {
            if (bArr != null) {
                try {
                    String str = new String(bArr, "utf-8");
                    if (C3752b.m2332a(str)) {
                        return;
                    }
                    C3723bh.m2046a("Collector", "upload success");
                    RunnableC3753c.this.m2345a(str);
                } catch (Throwable th) {
                    mo2329a(new Exception(th));
                }
            }
        }

        @Override // com.iflytek.idata.extension.C3752b.a
        /* renamed from: a */
        public void mo2329a(Exception exc) {
            C3723bh.m2047b("Collector", "update error:" + exc);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3753c(Context context) {
        this.f3509a = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2345a(String str) {
        JSONObject optJSONObject;
        try {
            optJSONObject = new JSONObject(str).optJSONObject("rsp");
        } catch (Throwable th) {
            C3723bh.m2047b("Collector", "parse result error:" + th.toString());
            return;
        }
        if (optJSONObject != null) {
            String optString = optJSONObject.optString("des");
            if (!TextUtils.isEmpty(optString)) {
                SharedPreferences m2045b = C3722bg.m2045b(this.f3509a);
                String string = m2045b.getString("ifly_dynamic_business_conf", "");
                SharedPreferences.Editor edit = m2045b.edit();
                if ("no".equals(optString)) {
                    if (!TextUtils.isEmpty(string)) {
                        edit.putString("ifly_dynamic_business_conf", "");
                        edit.apply();
                    }
                } else if (!optString.equals(string)) {
                    edit.putString("ifly_dynamic_business_conf", optString);
                    edit.apply();
                }
                C3723bh.m2047b("Collector", "parse result error:" + th.toString());
                return;
            }
            String optString2 = optJSONObject.optString("ver");
            if (TextUtils.isEmpty(optString2) || optString2.equals(IFlyCollectorExt.m2309a(this.f3509a).m2314a())) {
                return;
            }
            new Thread(new RunnableC3751a(this.f3509a, true)).start();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        IFlyCollectorExt m2309a;
        byte[] m2321f;
        try {
            if (!C3722bg.m2043a(this.f3509a) || (m2321f = (m2309a = IFlyCollectorExt.m2309a(this.f3509a)).m2321f()) == null) {
                return;
            }
            C3752b c3752b = new C3752b();
            c3752b.m2342b(20000);
            c3752b.m2338a(1);
            StringBuilder sb = new StringBuilder();
            sb.append("product=freelog&appid=" + m2309a.m2319d());
            String m2320e = m2309a.m2320e();
            if (!TextUtils.isEmpty(m2320e)) {
                sb.append("&subid=" + m2320e);
            }
            sb.append("&size=" + m2321f.length + "&platform=android&source=daas&pv=1.1");
            c3752b.m2340a("https://log.iflytek.com/log", sb.toString(), m2321f);
            c3752b.m2339a(this.f3510b);
            c3752b.m2337a();
        } catch (Throwable th) {
            C3723bh.m2048c("Collector", "exception occur while upload:" + th.toString());
        }
    }
}
