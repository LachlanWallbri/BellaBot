package com.iflytek.aiui.data.audio.player;

import android.media.AudioTrack;
import com.iflytek.aiui.pro.C3570a1;
import com.iflytek.aiui.pro.C3633p0;
import com.iflytek.aiui.pro.C3651y0;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.iflytek.aiui.data.audio.player.a */
/* loaded from: classes4.dex */
public class C3539a {

    /* renamed from: a */
    private AudioTrack f2154a;

    /* renamed from: b */
    private Thread f2155b;

    /* renamed from: c */
    private int f2156c;

    /* renamed from: d */
    private boolean f2157d;

    /* renamed from: e */
    private boolean f2158e = C3633p0.m1472a("ivw", "play_aec", false);

    /* renamed from: f */
    private LinkedBlockingQueue<C3570a1> f2159f;

    /* renamed from: g */
    private Stack<C3570a1> f2160g;

    /* renamed from: h */
    private C3570a1 f2161h;

    /* renamed from: i */
    private int f2162i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.aiui.data.audio.player.a$a */
    /* loaded from: classes4.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            do {
                try {
                    C3570a1 m808e = C3539a.this.m808e();
                    if (m808e == null) {
                        Thread.sleep(5L);
                    } else {
                        if (C3539a.this.f2154a.getPlayState() != 3) {
                            C3539a.this.f2154a.play();
                        }
                        C3539a.this.f2154a.write(m808e.m900b(), 0, m808e.m902d());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            } while (C3539a.this.f2157d);
        }
    }

    /* renamed from: c */
    private void m804c() {
        C3651y0.m1620b("AECPlayer", "createAudio start, tid=" + Thread.currentThread().getId());
        this.f2156c = AudioTrack.getMinBufferSize(16000, 2, 2);
        if (this.f2154a != null) {
            m806g();
        }
        C3651y0.m1620b("AECPlayer", "createAudio || mStreamType = 3, buffer size: " + this.f2156c);
        this.f2154a = new AudioTrack(3, 16000, 2, 2, this.f2156c * 4, 1);
        int i = this.f2156c;
        if (i == -2 || i == -1) {
            throw new RuntimeException("create audio track fail");
        }
        this.f2159f = new LinkedBlockingQueue<>(40);
        this.f2160g = new Stack<>();
        for (int i2 = 0; i2 < 40; i2++) {
            this.f2160g.push(new C3570a1(1066));
        }
        C3570a1 c3570a1 = new C3570a1(1066);
        this.f2161h = c3570a1;
        c3570a1.m911m(C3570a1.a.NV21);
        C3651y0.m1620b("AECPlayer", "createAudio end");
    }

    /* renamed from: d */
    private C3570a1 m805d() {
        if (this.f2160g.empty()) {
            return null;
        }
        return this.f2160g.pop();
    }

    /* renamed from: g */
    private void m806g() {
        synchronized (this) {
            AudioTrack audioTrack = this.f2154a;
            if (audioTrack != null) {
                if (audioTrack.getPlayState() == 3) {
                    this.f2154a.stop();
                }
                this.f2154a.release();
                this.f2154a = null;
            }
            C3651y0.m1620b("AECPlayer", "mAudioTrack released");
        }
    }

    /* renamed from: i */
    private void m807i() {
        Thread thread = new Thread(new a());
        this.f2155b = thread;
        thread.start();
    }

    /* renamed from: e */
    public C3570a1 m808e() {
        C3570a1 take = this.f2159f.take();
        if (take == null) {
            return null;
        }
        C3570a1.m898s(take, this.f2161h);
        this.f2160g.push(take);
        return this.f2161h;
    }

    /* renamed from: f */
    public void m809f(byte[] bArr) {
        if (this.f2158e) {
            C3570a1 m805d = m805d();
            if (m805d == null) {
                this.f2162i++;
                C3651y0.m1627i("AECPlayer", "no avaiable buffer");
                return;
            }
            m805d.m899a(bArr, 0, false);
            int i = this.f2162i;
            this.f2162i = i + 1;
            m805d.m913o(i);
            if (this.f2159f.offer(m805d)) {
                return;
            }
            this.f2160g.push(m805d);
        }
    }

    /* renamed from: h */
    public void m810h() {
        if (this.f2158e) {
            m811j();
            this.f2157d = true;
            m804c();
            m807i();
        }
    }

    /* renamed from: j */
    public void m811j() {
        this.f2157d = false;
        Thread thread = this.f2155b;
        if (thread != null) {
            thread.interrupt();
            try {
                this.f2155b.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.f2155b = null;
        }
        if (this.f2154a != null) {
            m806g();
        }
    }
}
