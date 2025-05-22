package com.iflytek.aiui.pro;

import android.graphics.ImageFormat;
import android.os.Bundle;
import com.iflytek.aiui.jni.AIUI;
import com.iflytek.aiui.pro.C3570a1;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.iflytek.aiui.pro.x0 */
/* loaded from: classes4.dex */
public class C3649x0 {

    /* renamed from: o */
    private static int f2710o = 5;

    /* renamed from: a */
    private LinkedBlockingQueue<C3570a1> f2711a;

    /* renamed from: b */
    private Stack<C3570a1> f2712b;

    /* renamed from: c */
    private C3570a1 f2713c;

    /* renamed from: d */
    private long f2714d;

    /* renamed from: e */
    private b f2715e;

    /* renamed from: f */
    double f2716f = Double.parseDouble(C3633p0.m1477f("recorder", "cam_clip_left", "0"));

    /* renamed from: g */
    double f2717g = Double.parseDouble(C3633p0.m1477f("recorder", "cam_clip_right", "0"));

    /* renamed from: h */
    double f2718h = Double.parseDouble(C3633p0.m1477f("recorder", "cam_clip_top", "0"));

    /* renamed from: i */
    double f2719i = Double.parseDouble(C3633p0.m1477f("recorder", "cam_clip_bottom", "0"));

    /* renamed from: j */
    boolean f2720j = C3633p0.m1472a("recorder", "cam_mirror", false);

    /* renamed from: k */
    int f2721k = C3633p0.m1473b("recorder", "cam_rotate", 0);

    /* renamed from: l */
    int f2722l = C3633p0.m1473b("recorder", "cam_scale", 100);

    /* renamed from: m */
    private int f2723m;

    /* renamed from: n */
    private Thread f2724n;

    /* renamed from: com.iflytek.aiui.pro.x0$a */
    /* loaded from: classes4.dex */
    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x00d7 A[Catch: Exception -> 0x011e, TryCatch #0 {Exception -> 0x011e, blocks: (B:3:0x0004, B:57:0x000e, B:7:0x0019, B:9:0x002c, B:11:0x0039, B:33:0x00cf, B:35:0x00d7, B:36:0x00e9, B:38:0x00f5, B:55:0x0033), top: B:2:0x0004 }] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00f5 A[Catch: Exception -> 0x011e, TRY_LEAVE, TryCatch #0 {Exception -> 0x011e, blocks: (B:3:0x0004, B:57:0x000e, B:7:0x0019, B:9:0x002c, B:11:0x0039, B:33:0x00cf, B:35:0x00d7, B:36:0x00e9, B:38:0x00f5, B:55:0x0033), top: B:2:0x0004 }] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            int i;
            byte[] bArr;
            JSONObject jSONObject;
            a aVar = this;
            byte[] bArr2 = null;
            byte[] bArr3 = null;
            while (true) {
                try {
                    C3570a1 m1612c = C3649x0.this.m1612c();
                    if (m1612c == null) {
                        C3651y0.m1627i("ImageConvertHandler", "no frame provider   sleep 10ms");
                        Thread.sleep(10L);
                    } else {
                        long currentTimeMillis = System.currentTimeMillis();
                        Thread.sleep(0L);
                        int m906h = m1612c.m906h();
                        int m903e = m1612c.m903e();
                        if (bArr2 == null || bArr2.length < m906h * m903e * 3) {
                            bArr2 = new byte[m906h * m903e * 3];
                        }
                        byte[] bArr4 = bArr2;
                        byte[] m900b = m1612c.m900b();
                        int length = bArr4.length;
                        C3649x0 c3649x0 = C3649x0.this;
                        double d = c3649x0.f2716f;
                        double d2 = m906h;
                        Double.isNaN(d2);
                        byte[] bArr5 = bArr3;
                        int i2 = (int) (d * d2);
                        try {
                            double d3 = c3649x0.f2718h;
                            double d4 = m903e;
                            Double.isNaN(d4);
                            int i3 = (int) (d3 * d4);
                            double d5 = c3649x0.f2717g;
                            Double.isNaN(d2);
                            int i4 = (int) (((1.0d - d) - d5) * d2);
                            double d6 = c3649x0.f2719i;
                            Double.isNaN(d4);
                            try {
                                jSONObject = new JSONObject(AIUI.convertImage(m900b, m906h, m903e, bArr4, length, i2, i3, i4, (int) (((1.0d - d3) - d6) * d4), c3649x0.f2722l, c3649x0.f2721k, c3649x0.f2720j));
                                i = jSONObject.getInt("img_width");
                            } catch (JSONException e) {
                                e = e;
                                i = m906h;
                            }
                            try {
                                m903e = jSONObject.getInt("img_height");
                            } catch (JSONException e2) {
                                e = e2;
                                e.printStackTrace();
                                int i5 = i;
                                int i6 = m903e;
                                if (bArr5 != null) {
                                    bArr = bArr5;
                                    System.arraycopy(bArr4, 0, bArr, 0, bArr.length);
                                    aVar = this;
                                    if (C3649x0.this.f2715e != null) {
                                    }
                                    if (System.currentTimeMillis() - currentTimeMillis > 15) {
                                    }
                                    bArr3 = bArr;
                                    bArr2 = bArr4;
                                }
                                bArr = new byte[i6 * i5 * 3];
                                System.arraycopy(bArr4, 0, bArr, 0, bArr.length);
                                aVar = this;
                                if (C3649x0.this.f2715e != null) {
                                }
                                if (System.currentTimeMillis() - currentTimeMillis > 15) {
                                }
                                bArr3 = bArr;
                                bArr2 = bArr4;
                            }
                            int i52 = i;
                            int i62 = m903e;
                            if (bArr5 != null && bArr5.length == i52 * i62 * 3) {
                                bArr = bArr5;
                                System.arraycopy(bArr4, 0, bArr, 0, bArr.length);
                                aVar = this;
                                if (C3649x0.this.f2715e != null) {
                                    C3649x0.this.f2715e.m1616a(bArr, i52, i62, m1612c.m904f(), m1612c.m901c());
                                }
                                if (System.currentTimeMillis() - currentTimeMillis > 15) {
                                    C3651y0.m1625g("ImageConvertHandler", m1612c.f2246e + "process Frame time: " + (System.currentTimeMillis() - currentTimeMillis));
                                }
                                bArr3 = bArr;
                                bArr2 = bArr4;
                            }
                            bArr = new byte[i62 * i52 * 3];
                            System.arraycopy(bArr4, 0, bArr, 0, bArr.length);
                            aVar = this;
                            if (C3649x0.this.f2715e != null) {
                            }
                            if (System.currentTimeMillis() - currentTimeMillis > 15) {
                            }
                            bArr3 = bArr;
                            bArr2 = bArr4;
                        } catch (Exception e3) {
                            e = e3;
                            e.printStackTrace();
                            return;
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            }
        }
    }

    /* renamed from: com.iflytek.aiui.pro.x0$b */
    /* loaded from: classes4.dex */
    public interface b {
        /* renamed from: a */
        void m1616a(byte[] bArr, int i, int i2, long j, Bundle bundle);
    }

    public C3649x0(b bVar) {
        this.f2723m = 0;
        this.f2715e = bVar;
        this.f2723m = C3633p0.m1473b("recorder", "cam_skip_frame", 0);
        int m1473b = C3633p0.m1473b("recorder", "cam_max_px", 2073600);
        this.f2711a = new LinkedBlockingQueue<>(f2710o);
        this.f2712b = new Stack<>();
        for (int i = 0; i < f2710o; i++) {
            C3570a1 c3570a1 = new C3570a1((ImageFormat.getBitsPerPixel(17) * m1473b) / 8);
            c3570a1.m911m(C3570a1.a.NV21);
            this.f2712b.push(c3570a1);
        }
        C3570a1 c3570a12 = new C3570a1((m1473b * ImageFormat.getBitsPerPixel(17)) / 8);
        this.f2713c = c3570a12;
        c3570a12.m911m(C3570a1.a.NV21);
    }

    /* renamed from: b */
    private C3570a1 m1611b() {
        if (this.f2712b.empty()) {
            return null;
        }
        return this.f2712b.pop();
    }

    /* renamed from: c */
    public C3570a1 m1612c() {
        C3570a1 take = this.f2711a.take();
        if (take == null) {
            return null;
        }
        C3570a1.m898s(take, this.f2713c);
        this.f2712b.push(take);
        return this.f2713c;
    }

    /* renamed from: d */
    public void m1613d(byte[] bArr, Bundle bundle) {
        if (this.f2723m > 0) {
            long j = this.f2714d;
            if (j % (r0 + 1) != 0) {
                this.f2714d = j + 1;
                return;
            }
        }
        C3570a1 m1611b = m1611b();
        if (m1611b == null) {
            this.f2714d++;
            C3651y0.m1627i("ImageConvertHandler", "no avaiable buffer");
            return;
        }
        m1611b.m899a(bArr, this.f2721k, this.f2720j);
        m1611b.m916r(Integer.valueOf(bundle.getString("width")).intValue());
        m1611b.m912n(Integer.valueOf(bundle.getString("height")).intValue());
        m1611b.m909k(bundle);
        long j2 = this.f2714d;
        this.f2714d = 1 + j2;
        m1611b.m913o(j2);
        if (this.f2711a.offer(m1611b)) {
            return;
        }
        this.f2712b.push(m1611b);
    }

    /* renamed from: e */
    public void m1614e() {
        Thread thread = this.f2724n;
        if (thread != null && thread.isAlive()) {
            m1615f();
        }
        Thread thread2 = new Thread(new a());
        this.f2724n = thread2;
        thread2.start();
    }

    /* renamed from: f */
    public void m1615f() {
        Thread thread = this.f2724n;
        if (thread != null) {
            thread.interrupt();
        }
        try {
            Thread thread2 = this.f2724n;
            if (thread2 != null) {
                thread2.join();
                this.f2724n = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
