package com.iflytek.aiui.pro;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.aliyun.alink.p022h2.api.Constraint;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.b */
/* loaded from: classes.dex */
public class C3597b {

    /* renamed from: a */
    private String f2421a;

    /* renamed from: b */
    private Context f2422b;

    /* renamed from: c */
    private ServiceConnection f2423c = null;

    /* renamed from: d */
    private ServiceConnection f2424d = null;

    /* renamed from: e */
    private String f2425e = "com.autonavi.minimap";

    /* renamed from: f */
    private String f2426f = "com.amap.api.service.AMapService";

    /* renamed from: g */
    private String f2427g = "com.autonavi.minimap.LBSConnectionService";

    /* renamed from: h */
    private boolean f2428h = false;

    /* renamed from: i */
    private boolean f2429i = false;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.pro.b$a */
    /* loaded from: classes.dex */
    private static class a implements ServiceConnection {

        /* renamed from: a */
        private String f2430a;

        public a(String str) {
            this.f2430a = str;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3597b(Context context) {
        this.f2421a = null;
        this.f2422b = null;
        this.f2422b = context;
        try {
            this.f2421a = C3624l.m1397b(C3614g.m1317a(C3618i.m1361e(context).getBytes("UTF-8"), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n"));
        } catch (Throwable th) {
            C3614g.m1308a(th, "ConnectionServiceManager", "ConnectionServiceManager");
        }
    }

    /* renamed from: a */
    public void m1112a() {
        synchronized (this) {
            m1116d();
            this.f2422b = null;
            this.f2423c = null;
            this.f2424d = null;
            this.f2428h = false;
            this.f2429i = false;
        }
    }

    /* renamed from: a */
    boolean m1113a(ServiceConnection serviceConnection, String str) {
        Intent intent = new Intent();
        intent.putExtra(Constraint.AUTH_TYPE_APP_KEY, this.f2421a);
        intent.setComponent(new ComponentName(this.f2425e, str));
        return this.f2422b.bindService(intent, serviceConnection, 1);
    }

    /* renamed from: b */
    public void m1114b() {
        try {
            if (this.f2423c == null) {
                this.f2423c = new a("mapService");
            }
            if (this.f2424d == null) {
                this.f2424d = new a("pushService");
            }
        } catch (Throwable th) {
            C3614g.m1308a(th, "ConnectionServiceManager", "init");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean m1115c() {
        boolean z;
        synchronized (this) {
            try {
                if (C3614g.m1335d(this.f2422b)) {
                    this.f2428h = m1113a(this.f2423c, this.f2426f);
                }
                if (C3614g.m1337e(this.f2422b)) {
                    this.f2429i = m1113a(this.f2424d, this.f2427g);
                }
            } finally {
                return z;
            }
            if (this.f2428h) {
                z = this.f2429i;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public void m1116d() {
        synchronized (this) {
            try {
                if (this.f2428h) {
                    this.f2422b.unbindService(this.f2423c);
                }
                if (this.f2429i) {
                    this.f2422b.unbindService(this.f2424d);
                }
            } finally {
            }
        }
    }
}
