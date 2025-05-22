package com.iflytek.cloud.record;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.FuncAdapter;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.record.C3682b;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.iflytek.cloud.record.c */
/* loaded from: classes3.dex */
public class C3683c {

    /* renamed from: A */
    private float f2894A;

    /* renamed from: B */
    private boolean f2895B;

    /* renamed from: C */
    private boolean f2896C;

    /* renamed from: D */
    private boolean f2897D;

    /* renamed from: E */
    private int f2898E;

    /* renamed from: F */
    private Handler f2899F;

    /* renamed from: a */
    ReentrantLock f2900a;

    /* renamed from: b */
    Condition f2901b;

    /* renamed from: c */
    AudioManager.OnAudioFocusChangeListener f2902c;

    /* renamed from: d */
    private AudioTrack f2903d;

    /* renamed from: e */
    private C3682b f2904e;

    /* renamed from: f */
    private Context f2905f;

    /* renamed from: g */
    private b f2906g;

    /* renamed from: h */
    private a f2907h;

    /* renamed from: i */
    private volatile int f2908i;

    /* renamed from: j */
    private boolean f2909j;

    /* renamed from: k */
    private int f2910k;

    /* renamed from: l */
    private int f2911l;

    /* renamed from: m */
    private boolean f2912m;

    /* renamed from: n */
    private boolean f2913n;

    /* renamed from: o */
    private Object f2914o;

    /* renamed from: p */
    private Object f2915p;

    /* renamed from: q */
    private final int f2916q;

    /* renamed from: r */
    private final int f2917r;

    /* renamed from: s */
    private final int f2918s;

    /* renamed from: t */
    private int f2919t;

    /* renamed from: u */
    private final float f2920u;

    /* renamed from: v */
    private final float f2921v;

    /* renamed from: w */
    private final float f2922w;

    /* renamed from: x */
    private int f2923x;

    /* renamed from: y */
    private float f2924y;

    /* renamed from: z */
    private float f2925z;

    /* renamed from: com.iflytek.cloud.record.c$a */
    /* loaded from: classes3.dex */
    public interface a {
        /* renamed from: a */
        void mo1744a();

        /* renamed from: a */
        void mo1745a(int i, int i2, int i3);

        /* renamed from: a */
        void mo1746a(SpeechError speechError);

        /* renamed from: b */
        void mo1747b();

        /* renamed from: c */
        void mo1748c();
    }

    public C3683c(Context context, int i, boolean z, boolean z2, boolean z3) {
        this.f2903d = null;
        this.f2904e = null;
        this.f2905f = null;
        this.f2906g = null;
        this.f2907h = null;
        this.f2908i = 0;
        this.f2909j = true;
        this.f2910k = 3;
        this.f2912m = false;
        this.f2913n = false;
        this.f2914o = new Object();
        this.f2915p = this;
        this.f2916q = 2;
        this.f2917r = 500;
        this.f2918s = 50;
        this.f2919t = 1600;
        this.f2920u = 1.0f;
        this.f2921v = 0.0f;
        this.f2922w = 0.1f;
        this.f2923x = this.f2919t * 10;
        this.f2924y = 0.0f;
        this.f2925z = 1.0f;
        this.f2894A = 0.1f;
        this.f2895B = false;
        this.f2896C = false;
        this.f2897D = false;
        this.f2900a = new ReentrantLock();
        this.f2901b = this.f2900a.newCondition();
        this.f2902c = new AudioManager.OnAudioFocusChangeListener() { // from class: com.iflytek.cloud.record.c.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i2) {
                if (i2 == -2 || i2 == -3 || i2 == -1) {
                    DebugLog.LogD("PcmPlayer", "pause start");
                    if (C3683c.this.m1737c()) {
                        DebugLog.LogD("PcmPlayer", "pause success");
                        C3683c.this.f2913n = true;
                        if (C3683c.this.f2907h != null) {
                            C3683c.this.f2907h.mo1744a();
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (i2 == 1) {
                    DebugLog.LogD("PcmPlayer", "resume start");
                    if (C3683c.this.f2913n) {
                        C3683c.this.f2913n = false;
                        if (C3683c.this.m1738d()) {
                            DebugLog.LogD("PcmPlayer", "resume success");
                            if (C3683c.this.f2907h != null) {
                                C3683c.this.f2907h.mo1747b();
                            }
                        }
                    }
                }
            }
        };
        this.f2898E = 0;
        this.f2899F = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.record.c.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 0) {
                    if (C3683c.this.f2907h != null) {
                        C3683c.this.f2907h.mo1746a((SpeechError) message.obj);
                        C3683c.this.f2907h = null;
                        return;
                    }
                    return;
                }
                if (i2 == 1) {
                    if (C3683c.this.f2907h != null) {
                        C3683c.this.f2907h.mo1744a();
                        return;
                    }
                    return;
                }
                if (i2 == 2) {
                    if (C3683c.this.f2907h != null) {
                        C3683c.this.f2907h.mo1747b();
                    }
                } else {
                    if (i2 != 3) {
                        if (i2 == 4 && C3683c.this.f2907h != null) {
                            C3683c.this.f2907h.mo1748c();
                            C3683c.this.f2907h = null;
                            return;
                        }
                        return;
                    }
                    if (C3683c.this.f2907h != null) {
                        C3683c.this.f2907h.mo1745a(message.arg1, message.arg2, C3683c.this.f2898E);
                    }
                }
            }
        };
        this.f2905f = context;
        this.f2910k = i;
        this.f2912m = z;
        this.f2897D = z2;
        this.f2896C = z3;
    }

    public C3683c(Context context) {
        this.f2903d = null;
        this.f2904e = null;
        this.f2905f = null;
        this.f2906g = null;
        this.f2907h = null;
        this.f2908i = 0;
        this.f2909j = true;
        this.f2910k = 3;
        this.f2912m = false;
        this.f2913n = false;
        this.f2914o = new Object();
        this.f2915p = this;
        this.f2916q = 2;
        this.f2917r = 500;
        this.f2918s = 50;
        this.f2919t = 1600;
        this.f2920u = 1.0f;
        this.f2921v = 0.0f;
        this.f2922w = 0.1f;
        this.f2923x = this.f2919t * 10;
        this.f2924y = 0.0f;
        this.f2925z = 1.0f;
        this.f2894A = 0.1f;
        this.f2895B = false;
        this.f2896C = false;
        this.f2897D = false;
        this.f2900a = new ReentrantLock();
        this.f2901b = this.f2900a.newCondition();
        this.f2902c = new AudioManager.OnAudioFocusChangeListener() { // from class: com.iflytek.cloud.record.c.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i2) {
                if (i2 == -2 || i2 == -3 || i2 == -1) {
                    DebugLog.LogD("PcmPlayer", "pause start");
                    if (C3683c.this.m1737c()) {
                        DebugLog.LogD("PcmPlayer", "pause success");
                        C3683c.this.f2913n = true;
                        if (C3683c.this.f2907h != null) {
                            C3683c.this.f2907h.mo1744a();
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (i2 == 1) {
                    DebugLog.LogD("PcmPlayer", "resume start");
                    if (C3683c.this.f2913n) {
                        C3683c.this.f2913n = false;
                        if (C3683c.this.m1738d()) {
                            DebugLog.LogD("PcmPlayer", "resume success");
                            if (C3683c.this.f2907h != null) {
                                C3683c.this.f2907h.mo1747b();
                            }
                        }
                    }
                }
            }
        };
        this.f2898E = 0;
        this.f2899F = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.record.c.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 0) {
                    if (C3683c.this.f2907h != null) {
                        C3683c.this.f2907h.mo1746a((SpeechError) message.obj);
                        C3683c.this.f2907h = null;
                        return;
                    }
                    return;
                }
                if (i2 == 1) {
                    if (C3683c.this.f2907h != null) {
                        C3683c.this.f2907h.mo1744a();
                        return;
                    }
                    return;
                }
                if (i2 == 2) {
                    if (C3683c.this.f2907h != null) {
                        C3683c.this.f2907h.mo1747b();
                    }
                } else {
                    if (i2 != 3) {
                        if (i2 == 4 && C3683c.this.f2907h != null) {
                            C3683c.this.f2907h.mo1748c();
                            C3683c.this.f2907h = null;
                            return;
                        }
                        return;
                    }
                    if (C3683c.this.f2907h != null) {
                        C3683c.this.f2907h.mo1745a(message.arg1, message.arg2, C3683c.this.f2898E);
                    }
                }
            }
        };
        this.f2905f = context;
    }

    /* renamed from: a */
    public int m1734a() {
        return this.f2908i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m1710a(int i, int i2) {
        boolean z;
        synchronized (this.f2915p) {
            if (i == this.f2908i) {
                this.f2908i = i2;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: j */
    private void m1723j() throws Exception {
        DebugLog.LogD("PcmPlayer", "createAudio start");
        int m1686a = this.f2904e.m1686a();
        this.f2911l = AudioTrack.getMinBufferSize(m1686a, 2, 2);
        this.f2919t = (m1686a / 1000) * 2 * 50;
        this.f2923x = this.f2919t * 10;
        if (this.f2903d != null) {
            m1736b();
        }
        DebugLog.LogD("PcmPlayer", "createAudio || mStreamType = " + this.f2910k + ", buffer size: " + this.f2911l);
        this.f2903d = new AudioTrack(this.f2910k, m1686a, 2, 2, this.f2911l * 2, 1);
        this.f2904e.m1687a(this.f2911l * 2);
        int i = this.f2911l;
        if (i == -2 || i == -1) {
            throw new Exception();
        }
        DebugLog.LogD("PcmPlayer", "createAudio end");
    }

    /* renamed from: b */
    public void m1736b() {
        synchronized (this.f2914o) {
            if (this.f2903d != null) {
                if (this.f2903d.getPlayState() == 3) {
                    this.f2903d.stop();
                }
                this.f2903d.release();
                this.f2903d = null;
            }
            DebugLog.LogD("PcmPlayer", "mAudioTrack released");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m1725k() throws Exception {
        b bVar = this.f2906g;
        if (this.f2903d == null || !(bVar == null || bVar.m1749a() == this.f2910k)) {
            DebugLog.LogD("PcmPlayer", "prepAudioPlayer || audiotrack is null or stream type is change.");
            m1723j();
            if (bVar != null) {
                bVar.m1750a(this.f2910k);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.record.c$b */
    /* loaded from: classes3.dex */
    public class b extends Thread {

        /* renamed from: b */
        private int f2929b;

        private b() {
            this.f2929b = C3683c.this.f2910k;
        }

        /* renamed from: a */
        public int m1749a() {
            return this.f2929b;
        }

        /* renamed from: a */
        public void m1750a(int i) {
            this.f2929b = i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:162:0x0381, code lost:
        
            if (r9.f2928a.f2909j == false) goto L136;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0302, code lost:
        
            if (r9.f2928a.f2909j != false) goto L135;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x039b, code lost:
        
            com.iflytek.cloud.msc.util.FuncAdapter.UnLock(r9.f2928a.f2905f, java.lang.Boolean.valueOf(r9.f2928a.f2912m), null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x03ae, code lost:
        
            r9.f2928a.f2906g = null;
            com.iflytek.cloud.msc.util.log.DebugLog.LogD("PcmPlayer", "player stopped");
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x03ba, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x0383, code lost:
        
            com.iflytek.cloud.msc.util.FuncAdapter.UnLock(r9.f2928a.f2905f, java.lang.Boolean.valueOf(r9.f2928a.f2912m), r9.f2928a.f2902c);
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            ReentrantLock reentrantLock;
            try {
                try {
                    DebugLog.LogD("PcmPlayer", "start player");
                    DebugLog.LogD("PcmPlayer", "mAudioFocus= " + C3683c.this.f2909j);
                    if (C3683c.this.f2909j) {
                        FuncAdapter.Lock(C3683c.this.f2905f, Boolean.valueOf(C3683c.this.f2912m), C3683c.this.f2902c);
                    } else {
                        FuncAdapter.Lock(C3683c.this.f2905f, Boolean.valueOf(C3683c.this.f2912m), null);
                    }
                    C3683c.this.f2904e.m1697d();
                    synchronized (C3683c.this.f2915p) {
                        if (C3683c.this.f2908i != 4 && C3683c.this.f2908i != 3) {
                            C3683c.this.f2908i = 2;
                        }
                    }
                    C3683c.this.m1740f();
                    while (true) {
                        C3683c.this.m1725k();
                        if (C3683c.this.f2908i != 2 && C3683c.this.f2908i != 1 && !C3683c.this.f2895B) {
                            if (C3683c.this.f2908i == 3) {
                                if (2 != C3683c.this.f2903d.getPlayState()) {
                                    C3683c.this.f2903d.pause();
                                    DebugLog.LogD("pause done");
                                    Message.obtain(C3683c.this.f2899F, 1).sendToTarget();
                                    if (C3683c.this.f2895B) {
                                        C3683c.this.m1743i();
                                    }
                                }
                                sleep(5L);
                            } else if (4 == C3683c.this.f2908i) {
                                C3683c.this.m1743i();
                                break;
                            }
                        }
                        if (C3683c.this.f2904e.m1701h()) {
                            if (C3683c.this.m1710a(1, 2)) {
                                Message.obtain(C3683c.this.f2899F, 2).sendToTarget();
                                DebugLog.LogD("BUFFERING to PLAYING  fading ");
                                C3683c.this.m1740f();
                            }
                            int m1698e = C3683c.this.f2904e.m1698e();
                            C3682b.a m1699f = C3683c.this.f2904e.m1699f();
                            if (m1699f != null) {
                                C3683c.this.f2898E = m1699f.f2892d;
                                Message.obtain(C3683c.this.f2899F, 3, m1698e, m1699f.f2891c).sendToTarget();
                            }
                            if (C3683c.this.f2903d.getPlayState() != 3) {
                                C3683c.this.f2903d.play();
                            }
                            if (C3683c.this.f2896C) {
                                if (!C3683c.this.f2904e.m1702i() && !C3683c.this.f2904e.m1696c(C3683c.this.f2923x) && Math.abs(C3683c.this.f2925z - 0.0f) >= 0.1f) {
                                    DebugLog.LogD("no more size  fading ");
                                    C3683c.this.m1741g();
                                } else if (2 == C3683c.this.f2908i && ((C3683c.this.f2904e.m1702i() || C3683c.this.f2904e.m1696c(C3683c.this.f2923x)) && Math.abs(C3683c.this.f2925z - 1.0f) >= 0.1f)) {
                                    DebugLog.LogD("has buffer  fading ");
                                    C3683c.this.m1740f();
                                }
                            }
                            if (C3683c.this.f2895B) {
                                C3683c.this.m1742h();
                            }
                            C3683c.this.f2904e.m1688a(C3683c.this.f2903d, C3683c.this.f2919t);
                        } else if (C3683c.this.f2904e.m1700g()) {
                            DebugLog.LogD("play stoped");
                            int playbackHeadPosition = C3683c.this.f2903d.getPlaybackHeadPosition();
                            int m1692b = (int) (C3683c.this.f2904e.m1692b() / 2);
                            if (m1692b > playbackHeadPosition && C3683c.this.f2900a.tryLock()) {
                                C3683c.this.f2903d.setNotificationMarkerPosition(m1692b);
                                DebugLog.LogI("PcmPlayer setNotificationMarkerPosition");
                                C3683c.this.f2903d.setPlaybackPositionUpdateListener(new AudioTrack.OnPlaybackPositionUpdateListener() { // from class: com.iflytek.cloud.record.c.b.1
                                    @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
                                    public void onPeriodicNotification(AudioTrack audioTrack) {
                                    }

                                    @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
                                    public void onMarkerReached(AudioTrack audioTrack) {
                                        DebugLog.LogI("PcmPlayer onMarkerReached");
                                        C3683c.this.f2900a.lock();
                                        try {
                                            C3683c.this.f2901b.signalAll();
                                        } catch (Exception unused) {
                                        } catch (Throwable th) {
                                            C3683c.this.f2900a.unlock();
                                            throw th;
                                        }
                                        C3683c.this.f2900a.unlock();
                                    }
                                });
                                try {
                                    try {
                                        C3683c.this.f2901b.await(1000L, TimeUnit.MILLISECONDS);
                                        reentrantLock = C3683c.this.f2900a;
                                    } catch (Throwable th) {
                                        C3683c.this.f2900a.unlock();
                                        throw th;
                                    }
                                } catch (InterruptedException e) {
                                    DebugLog.LogI("pcmplayer interrupted");
                                    e.printStackTrace();
                                    reentrantLock = C3683c.this.f2900a;
                                }
                                reentrantLock.unlock();
                            }
                            synchronized (C3683c.this.f2915p) {
                                DebugLog.LogI("pcmplayer isover stop:" + C3683c.this.f2908i);
                                if (C3683c.this.f2908i != 4) {
                                    C3683c.this.f2908i = 4;
                                    Message.obtain(C3683c.this.f2899F, 4).sendToTarget();
                                }
                                C3683c.this.f2895B = false;
                            }
                        } else if (C3683c.this.f2895B) {
                            C3683c.this.f2895B = false;
                        } else {
                            if (C3683c.this.m1710a(2, 1)) {
                                DebugLog.LogD("play onpaused!");
                                Message.obtain(C3683c.this.f2899F, 1).sendToTarget();
                            }
                            sleep(5L);
                        }
                    }
                    if (C3683c.this.f2903d != null) {
                        C3683c.this.f2903d.stop();
                    }
                    synchronized (C3683c.this.f2915p) {
                        C3683c.this.f2908i = 4;
                    }
                    if (C3683c.this.f2903d != null) {
                        C3683c.this.f2903d.release();
                        C3683c.this.f2903d = null;
                    }
                } catch (Exception e2) {
                    DebugLog.LogE(e2);
                    Message.obtain(C3683c.this.f2899F, 0, new SpeechError(20011)).sendToTarget();
                    synchronized (C3683c.this.f2915p) {
                        C3683c.this.f2908i = 4;
                        if (C3683c.this.f2903d != null) {
                            C3683c.this.f2903d.release();
                            C3683c.this.f2903d = null;
                        }
                    }
                }
            } catch (Throwable th2) {
                synchronized (C3683c.this.f2915p) {
                    C3683c.this.f2908i = 4;
                    if (C3683c.this.f2903d != null) {
                        C3683c.this.f2903d.release();
                        C3683c.this.f2903d = null;
                    }
                    if (C3683c.this.f2909j) {
                        FuncAdapter.UnLock(C3683c.this.f2905f, Boolean.valueOf(C3683c.this.f2912m), C3683c.this.f2902c);
                    } else {
                        FuncAdapter.UnLock(C3683c.this.f2905f, Boolean.valueOf(C3683c.this.f2912m), null);
                    }
                    C3683c.this.f2906g = null;
                    DebugLog.LogD("PcmPlayer", "player stopped");
                    throw th2;
                }
            }
        }
    }

    /* renamed from: c */
    public boolean m1737c() {
        if (this.f2908i == 4 || this.f2908i == 3) {
            return false;
        }
        DebugLog.LogD("pause start fade out");
        m1741g();
        this.f2908i = 3;
        return true;
    }

    /* renamed from: a */
    public boolean m1735a(C3682b c3682b, a aVar) {
        boolean z;
        DebugLog.LogD("PcmPlayer", "play mPlaytate= " + this.f2908i + ",mAudioFocus= " + this.f2909j);
        synchronized (this.f2915p) {
            if (this.f2908i == 4 || this.f2908i == 0 || this.f2908i == 3 || this.f2906g == null) {
                this.f2904e = c3682b;
                this.f2907h = aVar;
                this.f2906g = new b();
                this.f2906g.start();
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: d */
    public boolean m1738d() {
        boolean m1710a = m1710a(3, 2);
        FuncAdapter.Lock(this.f2905f, Boolean.valueOf(this.f2912m), this.f2902c);
        if (m1710a) {
            DebugLog.LogD("resume start fade in");
            Message.obtain(this.f2899F, 2).sendToTarget();
            m1740f();
        }
        return m1710a;
    }

    /* renamed from: e */
    public void m1739e() {
        if (4 != this.f2908i) {
            DebugLog.LogD("stop start fade out");
            m1741g();
        }
        synchronized (this.f2915p) {
            this.f2908i = 4;
        }
    }

    /* renamed from: f */
    public void m1740f() {
        if (this.f2897D) {
            synchronized (this.f2915p) {
                DebugLog.LogD("start fade in");
                this.f2895B = true;
                this.f2925z = 1.0f;
                this.f2894A = 0.1f;
            }
        }
    }

    /* renamed from: g */
    public void m1741g() {
        if (this.f2897D) {
            synchronized (this.f2915p) {
                DebugLog.LogD("start fade out");
                this.f2895B = true;
                this.f2925z = 0.0f;
                this.f2894A = -0.1f;
            }
        }
    }

    /* renamed from: h */
    public void m1742h() {
        if (this.f2897D) {
            synchronized (this.f2915p) {
                if (Math.abs(this.f2925z - this.f2924y) < 0.1f) {
                    this.f2924y = this.f2925z;
                    this.f2895B = false;
                    DebugLog.LogD("fading finish");
                } else {
                    this.f2924y += this.f2894A;
                }
            }
            AudioTrack audioTrack = this.f2903d;
            float f = this.f2924y;
            audioTrack.setStereoVolume(f, f);
            return;
        }
        this.f2895B = false;
    }

    /* renamed from: i */
    public void m1743i() {
        DebugLog.LogD("fading set silence");
        synchronized (this.f2915p) {
            if (Math.abs(0.0f - this.f2925z) < 0.1f) {
                this.f2924y = 0.0f;
                this.f2895B = false;
            }
        }
        AudioTrack audioTrack = this.f2903d;
        float f = this.f2924y;
        audioTrack.setStereoVolume(f, f);
    }
}
