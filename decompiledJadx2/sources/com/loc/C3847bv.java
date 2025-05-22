package com.loc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.aliyun.alink.p022h2.api.Constraint;
import com.amap.api.location.C1154a;
import com.amap.api.location.DPoint;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.iflytek.cloud.SpeechUtility;
import com.loc.InterfaceC3883i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: ConnectionServiceManager.java */
/* renamed from: com.loc.bv */
/* loaded from: classes4.dex */
public class C3847bv {

    /* renamed from: b */
    private String f3810b;

    /* renamed from: c */
    private Context f3811c;

    /* renamed from: d */
    private boolean f3812d = true;

    /* renamed from: a */
    public boolean f3809a = false;

    /* renamed from: e */
    private InterfaceC3883i f3813e = null;

    /* renamed from: f */
    private ServiceConnection f3814f = null;

    /* renamed from: g */
    private ServiceConnection f3815g = null;

    /* renamed from: h */
    private ServiceConnection f3816h = null;

    /* renamed from: i */
    private Intent f3817i = new Intent();

    /* renamed from: j */
    private String f3818j = "com.autonavi.minimap";

    /* renamed from: k */
    private String f3819k = "com.amap.api.service.AMapService";

    /* renamed from: l */
    private String f3820l = "com.autonavi.minimap.LBSConnectionService";

    /* renamed from: m */
    private boolean f3821m = false;

    /* renamed from: n */
    private boolean f3822n = false;

    /* renamed from: o */
    private boolean f3823o = false;

    /* renamed from: p */
    private String f3824p = "invaid type";

    /* renamed from: q */
    private String f3825q = "empty appkey";

    /* renamed from: r */
    private String f3826r = "refused";

    /* renamed from: s */
    private String f3827s = "failed";

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3847bv(Context context) {
        this.f3810b = null;
        this.f3811c = null;
        this.f3811c = context;
        try {
            this.f3810b = C3889o.m3178a(C3857ce.m2798a(C3885k.m3127f(context).getBytes("UTF-8"), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n"));
        } catch (Throwable th) {
            C3880f.m3097a(th, "ConnectionServiceManager", "ConnectionServiceManager");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private AMapLocationServer m2719a(Bundle bundle) {
        byte[] bArr;
        if (bundle == null) {
            return null;
        }
        if (bundle.containsKey(TransferTable.COLUMN_KEY)) {
            try {
                bArr = C3857ce.m2802b(C3889o.m3182b(bundle.getString(TransferTable.COLUMN_KEY)), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n");
            } catch (Throwable th) {
                C3880f.m3097a(th, "ConnectionServiceManager", "parseData part");
            }
            if (bundle.containsKey(SpeechUtility.TAG_RESOURCE_RESULT)) {
                try {
                    JSONObject jSONObject = new JSONObject(new String(C3857ce.m2800a(bArr, C3889o.m3182b(bundle.getString(SpeechUtility.TAG_RESOURCE_RESULT))), "utf-8"));
                    if (jSONObject.has("error")) {
                        String string = jSONObject.getString("error");
                        if (this.f3824p.equals(string)) {
                            this.f3812d = false;
                        }
                        if (this.f3825q.equals(string)) {
                            this.f3812d = false;
                        }
                        if (this.f3826r.equals(string)) {
                            this.f3812d = false;
                        }
                        this.f3827s.equals(string);
                        return null;
                    }
                    AMapLocationServer aMapLocationServer = new AMapLocationServer("");
                    aMapLocationServer.m548b(jSONObject);
                    aMapLocationServer.setProvider("lbs");
                    aMapLocationServer.setLocationType(7);
                    if ("WGS84".equals(aMapLocationServer.m551d()) && C3880f.m3098a(aMapLocationServer.getLatitude(), aMapLocationServer.getLongitude())) {
                        DPoint m494a = C1154a.m494a(this.f3811c, aMapLocationServer.getLongitude(), aMapLocationServer.getLatitude());
                        aMapLocationServer.setLatitude(m494a.getLatitude());
                        aMapLocationServer.setLongitude(m494a.getLongitude());
                    }
                    return aMapLocationServer;
                } catch (Throwable th2) {
                    C3880f.m3097a(th2, C3847bv.class.getName(), "parseData");
                }
            }
            return null;
        }
        bArr = null;
        if (bundle.containsKey(SpeechUtility.TAG_RESOURCE_RESULT)) {
        }
        return null;
    }

    /* renamed from: a */
    public final void m2721a() {
        try {
            if (this.f3821m) {
                this.f3811c.unbindService(this.f3814f);
            }
            if (this.f3822n) {
                this.f3811c.unbindService(this.f3815g);
            }
            if (this.f3823o) {
                this.f3811c.unbindService(this.f3816h);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "ConnectionServiceManager", "unbindService");
        }
        this.f3813e = null;
        this.f3811c = null;
        this.f3813e = null;
        this.f3814f = null;
        this.f3815g = null;
        this.f3816h = null;
        this.f3812d = true;
        this.f3809a = false;
        this.f3821m = false;
        this.f3822n = false;
        this.f3823o = false;
    }

    /* renamed from: b */
    public final void m2722b() {
        try {
            if (this.f3814f == null) {
                this.f3814f = new ServiceConnection() { // from class: com.loc.bv.1
                    @Override // android.content.ServiceConnection
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        C3847bv c3847bv = C3847bv.this;
                        c3847bv.f3809a = true;
                        c3847bv.f3813e = InterfaceC3883i.a.m3117a(iBinder);
                    }

                    @Override // android.content.ServiceConnection
                    public final void onServiceDisconnected(ComponentName componentName) {
                        C3847bv c3847bv = C3847bv.this;
                        c3847bv.f3809a = false;
                        c3847bv.f3813e = null;
                    }
                };
            }
            if (this.f3815g == null) {
                this.f3815g = new ServiceConnection() { // from class: com.loc.bv.2
                    @Override // android.content.ServiceConnection
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    }

                    @Override // android.content.ServiceConnection
                    public final void onServiceDisconnected(ComponentName componentName) {
                    }
                };
            }
            if (this.f3816h == null) {
                this.f3816h = new ServiceConnection() { // from class: com.loc.bv.3
                    @Override // android.content.ServiceConnection
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    }

                    @Override // android.content.ServiceConnection
                    public final void onServiceDisconnected(ComponentName componentName) {
                    }
                };
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "ConnectionServiceManager", "init");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final boolean m2723c() {
        ArrayList<String> m2897n;
        ArrayList<String> m2896m;
        try {
            if (C3869cq.m2878b(this.f3811c)) {
                this.f3817i.putExtra(Constraint.AUTH_TYPE_APP_KEY, this.f3810b);
                this.f3817i.setComponent(new ComponentName(this.f3818j, this.f3819k));
                try {
                    this.f3821m = this.f3811c.bindService(this.f3817i, this.f3814f, 1);
                } catch (Throwable unused) {
                }
                if (!this.f3821m && (m2896m = C3869cq.m2896m()) != null) {
                    Iterator<String> it = m2896m.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        if (!next.equals(this.f3819k)) {
                            this.f3817i.setComponent(new ComponentName(this.f3818j, next));
                            try {
                                this.f3821m = this.f3811c.bindService(this.f3817i, this.f3814f, 1);
                            } catch (Throwable unused2) {
                            }
                            if (this.f3821m) {
                                break;
                            }
                        }
                    }
                }
            }
            if (C3869cq.m2880c(this.f3811c)) {
                Intent intent = new Intent();
                intent.putExtra(Constraint.AUTH_TYPE_APP_KEY, this.f3810b);
                intent.setComponent(new ComponentName(this.f3818j, this.f3820l));
                try {
                    this.f3822n = this.f3811c.bindService(intent, this.f3815g, 1);
                } catch (Throwable unused3) {
                }
                if (!this.f3822n && (m2897n = C3869cq.m2897n()) != null) {
                    Iterator<String> it2 = m2897n.iterator();
                    while (it2.hasNext()) {
                        String next2 = it2.next();
                        if (!next2.equals(this.f3820l)) {
                            intent.setComponent(new ComponentName(this.f3818j, next2));
                            try {
                                this.f3822n = this.f3811c.bindService(intent, this.f3815g, 1);
                            } catch (Throwable unused4) {
                            }
                            if (this.f3821m) {
                                break;
                            }
                        }
                    }
                }
            }
            m2724d();
            if (this.f3821m) {
                if (this.f3822n) {
                    return true;
                }
            }
        } catch (Throwable unused5) {
        }
        return false;
    }

    /* renamed from: d */
    public final void m2724d() {
        List<C3870cr> m2907x;
        try {
            if (C3869cq.m2888g(this.f3811c) && (m2907x = C3869cq.m2907x()) != null && m2907x.size() > 0) {
                for (C3870cr c3870cr : m2907x) {
                    if (c3870cr != null) {
                        try {
                            if (c3870cr.m2910a()) {
                                Intent intent = new Intent();
                                intent.setComponent(new ComponentName(c3870cr.m2911b(), c3870cr.m2912c()));
                                if (!TextUtils.isEmpty(c3870cr.m2914e())) {
                                    intent.setAction(c3870cr.m2914e());
                                }
                                List<Map<String, String>> m2913d = c3870cr.m2913d();
                                if (m2913d != null && m2913d.size() > 0) {
                                    for (int i = 0; i < m2913d.size(); i++) {
                                        Iterator<Map.Entry<String, String>> it = m2913d.get(i).entrySet().iterator();
                                        if (it.hasNext()) {
                                            Map.Entry<String, String> next = it.next();
                                            intent.putExtra(next.getKey().toString(), next.getValue().toString());
                                        }
                                    }
                                }
                                if (c3870cr.m2915f()) {
                                    this.f3811c.startService(intent);
                                }
                                intent.putExtra("c", C3880f.m3102c(this.f3811c));
                                boolean bindService = this.f3811c.bindService(intent, this.f3816h, 1);
                                if (bindService) {
                                    this.f3823o = bindService;
                                }
                            }
                        } catch (Throwable th) {
                            C3880f.m3097a(th, "ConnectionServiceManager", "bindOtherService 1");
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "ConnectionServiceManager", "bindOtherService");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final AMapLocationServer m2725e() {
        try {
        } catch (Throwable th) {
            C3880f.m3097a(th, "ConnectionServiceManager", "sendCommand");
        }
        if (this.f3812d && this.f3821m) {
            Bundle bundle = new Bundle();
            bundle.putString("type", "corse");
            bundle.putString(Constraint.AUTH_TYPE_APP_KEY, this.f3810b);
            if (this.f3813e != null) {
                this.f3813e.mo3116a(bundle);
                if (bundle.size() > 0) {
                    return m2719a(bundle);
                }
            }
            return null;
        }
        return null;
    }
}
