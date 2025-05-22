package com.iflytek.cloud.thirdparty;

import com.iflytek.cloud.msc.util.log.DebugLog;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* renamed from: com.iflytek.cloud.thirdparty.o */
/* loaded from: classes3.dex */
public class C3736o {

    /* renamed from: a */
    private static final Class<?>[] f3355a = null;

    /* renamed from: b */
    private static final Object[] f3356b = null;

    /* renamed from: s */
    private static final Object f3357s = new Object();

    /* renamed from: t */
    private static C3736o f3358t = null;

    /* renamed from: c */
    private Class<?> f3359c;

    /* renamed from: d */
    private Method f3360d;

    /* renamed from: e */
    private Method f3361e;

    /* renamed from: f */
    private Method f3362f;

    /* renamed from: g */
    private Method f3363g;

    /* renamed from: h */
    private Method f3364h;

    /* renamed from: i */
    private Method f3365i;

    /* renamed from: j */
    private Method f3366j;

    /* renamed from: k */
    private Method f3367k;

    /* renamed from: l */
    private Class<?> f3368l;

    /* renamed from: m */
    private Method f3369m;

    /* renamed from: n */
    private Class<?> f3370n;

    /* renamed from: o */
    private Method f3371o;

    /* renamed from: p */
    private Object f3372p;

    /* renamed from: r */
    private Object f3374r;

    /* renamed from: q */
    private final a f3373q = new a();

    /* renamed from: u */
    private b f3375u = null;

    /* renamed from: com.iflytek.cloud.thirdparty.o$b */
    /* loaded from: classes3.dex */
    public interface b {
        /* renamed from: a */
        void mo2195a(byte[] bArr, int i);
    }

    /* renamed from: a */
    public static C3736o m2202a(int i, int i2, int i3) {
        C3736o c3736o;
        synchronized (f3357s) {
            if (f3358t == null) {
                try {
                    f3358t = new C3736o(i, i2, i3);
                } catch (Throwable th) {
                    DebugLog.LogE(th);
                }
            }
            c3736o = f3358t;
        }
        return c3736o;
    }

    /* renamed from: a */
    public static C3736o m2201a() {
        C3736o c3736o;
        synchronized (f3357s) {
            c3736o = f3358t;
        }
        return c3736o;
    }

    /* renamed from: a */
    public int m2205a(b bVar) {
        this.f3375u = bVar;
        try {
            return ((Integer) this.f3360d.invoke(this.f3372p, this.f3368l.cast(this.f3374r))).intValue();
        } catch (Throwable th) {
            DebugLog.LogE(th);
            return 20999;
        }
    }

    /* renamed from: b */
    public boolean m2207b() {
        try {
            return ((Boolean) this.f3362f.invoke(this.f3372p, f3356b)).booleanValue();
        } catch (Throwable th) {
            DebugLog.LogE(th);
            return false;
        }
    }

    /* renamed from: c */
    public void m2208c() {
        try {
            this.f3361e.invoke(this.f3372p, f3356b);
        } catch (Throwable th) {
            DebugLog.LogE(th);
        }
    }

    /* renamed from: d */
    public int m2209d() {
        try {
            return ((Integer) this.f3363g.invoke(this.f3372p, f3356b)).intValue();
        } catch (Throwable th) {
            DebugLog.LogE(th);
            return -1;
        }
    }

    /* renamed from: e */
    public int m2210e() {
        try {
            return ((Integer) this.f3364h.invoke(this.f3372p, f3356b)).intValue();
        } catch (Throwable th) {
            DebugLog.LogE(th);
            return -1;
        }
    }

    /* renamed from: f */
    public int m2211f() {
        try {
            return ((Integer) this.f3365i.invoke(this.f3372p, f3356b)).intValue();
        } catch (Throwable th) {
            DebugLog.LogE(th);
            return -1;
        }
    }

    /* renamed from: g */
    public b m2212g() {
        b bVar = this.f3375u;
        try {
            Object invoke = this.f3366j.invoke(this.f3372p, f3356b);
            if (this.f3374r.equals(invoke)) {
                return bVar;
            }
            DebugLog.LogD("Recorder getListener alsa listener unequal to current object: " + invoke);
            return null;
        } catch (Throwable th) {
            DebugLog.LogE(th);
            return null;
        }
    }

    /* renamed from: h */
    public void m2213h() {
        try {
            this.f3367k.invoke(this.f3372p, f3356b);
        } catch (Throwable th) {
            DebugLog.LogE(th);
        }
        synchronized (f3357s) {
            f3358t = null;
        }
    }

    /* renamed from: a */
    public void m2206a(boolean z) {
        try {
            this.f3371o.invoke(null, Boolean.valueOf(z));
        } catch (Throwable th) {
            DebugLog.LogE(th);
        }
    }

    private C3736o(int i, int i2, int i3) throws NullPointerException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.f3359c = null;
        this.f3360d = null;
        this.f3361e = null;
        this.f3362f = null;
        this.f3363g = null;
        this.f3364h = null;
        this.f3365i = null;
        this.f3366j = null;
        this.f3367k = null;
        this.f3368l = null;
        this.f3369m = null;
        this.f3370n = null;
        this.f3371o = null;
        this.f3372p = null;
        this.f3374r = null;
        this.f3368l = Class.forName("com.iflytek.alsa.AlsaRecorder$PcmListener");
        this.f3369m = this.f3368l.getMethod("onPcmData", byte[].class, Integer.TYPE);
        this.f3374r = Proxy.newProxyInstance(this.f3368l.getClassLoader(), new Class[]{this.f3368l}, this.f3373q);
        this.f3359c = Class.forName("com.iflytek.alsa.AlsaRecorder");
        this.f3372p = this.f3359c.getMethod("createInstance", Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(null, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        if (this.f3372p == null) {
            DebugLog.LogE("Recorder create alsa failed!");
            throw new NullPointerException("Recorder create alsa failed!");
        }
        this.f3360d = this.f3359c.getMethod("startRecording", this.f3368l);
        this.f3361e = this.f3359c.getMethod("stopRecording", f3355a);
        this.f3367k = this.f3359c.getMethod("destroy", f3355a);
        this.f3363g = this.f3359c.getMethod("getCardDevId", f3355a);
        this.f3366j = this.f3359c.getMethod("getListener", f3355a);
        this.f3365i = this.f3359c.getMethod("getPeriodSize", f3355a);
        this.f3364h = this.f3359c.getMethod("getSampleRate", f3355a);
        this.f3362f = this.f3359c.getMethod("isRecording", f3355a);
        this.f3370n = Class.forName("com.iflytek.alsa.jni.AlsaJni");
        this.f3371o = this.f3370n.getMethod("showJniLog", Boolean.TYPE);
    }

    /* renamed from: com.iflytek.cloud.thirdparty.o$a */
    /* loaded from: classes3.dex */
    private class a implements InvocationHandler {
        private a() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (method.equals(C3736o.this.f3369m) && C3736o.this.f3375u != null) {
                C3736o.this.f3375u.mo2195a((byte[]) objArr[0], ((Integer) objArr[1]).intValue());
            }
            return null;
        }
    }
}
