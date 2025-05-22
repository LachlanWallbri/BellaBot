package com.iflytek.cloud.thirdparty;

import android.os.Environment;
import android.os.MemoryFile;
import android.text.TextUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.DataUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3734m;
import com.iflytek.cloud.thirdparty.C3736o;
import com.iflytek.cloud.thirdparty.C3737p;
import com.iflytek.msc.AIMIC;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.Thread;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.commons.compress.archivers.cpio.CpioConstants;

/* renamed from: com.iflytek.cloud.thirdparty.n */
/* loaded from: classes3.dex */
public class C3735n extends AbstractC3734m {

    /* renamed from: d */
    private static C3735n f3296d;

    /* renamed from: e */
    private static final Object f3297e = new Object();

    /* renamed from: g */
    private boolean f3302g;

    /* renamed from: l */
    private final a f3307l;

    /* renamed from: r */
    private final b f3313r;

    /* renamed from: a */
    private final int f3298a = CpioConstants.C_ISSOCK;

    /* renamed from: b */
    private final int f3299b = 491520;

    /* renamed from: c */
    private final int f3300c = 983040;

    /* renamed from: f */
    private final Object f3301f = new Object();

    /* renamed from: h */
    private final byte[] f3303h = new byte[256];

    /* renamed from: i */
    private final C3737p f3304i = new C3737p(491520, CpioConstants.C_ISSOCK, 245760, false, false);

    /* renamed from: j */
    private final c f3305j = new c("AIMicAudioWritingThread");

    /* renamed from: k */
    private int f3306k = 2;

    /* renamed from: m */
    private int f3308m = 4000;

    /* renamed from: n */
    private final int f3309n = 16000;

    /* renamed from: o */
    private final int f3310o = 2;

    /* renamed from: p */
    private final int f3311p = 1536;

    /* renamed from: q */
    private C3736o f3312q = null;

    /* renamed from: s */
    private int f3314s = 16000;

    /* renamed from: t */
    private int f3315t = 2;

    /* renamed from: u */
    private int f3316u = 1536;

    /* renamed from: v */
    private int f3317v = -3;

    /* renamed from: w */
    private boolean f3318w = false;

    private C3735n(String str) throws UnsatisfiedLinkError, SpeechError, Throwable {
        String str2;
        int indexOf;
        int i = 0;
        this.f3302g = false;
        this.f3307l = new a();
        this.f3313r = new b();
        DebugLog.LogD("aimic constructor enter: " + str);
        StringBuffer stringBuffer = new StringBuffer();
        if (!TextUtils.isEmpty(str)) {
            stringBuffer.append(str);
        }
        if (!AIMIC.isLoaded()) {
            if (str == null || (indexOf = str.indexOf(SpeechConstant.LIB_NAME)) < 0 || indexOf >= str.length()) {
                str2 = null;
            } else {
                int indexOf2 = str.indexOf(44, indexOf);
                indexOf2 = indexOf2 < 0 ? str.length() : indexOf2;
                str2 = str.substring(str.indexOf(61, indexOf) + 1, indexOf2);
                stringBuffer.delete(indexOf, indexOf2);
            }
            AIMIC.loadLibrary(str2);
        }
        if (!AIMIC.isValid() && (i = AIMIC.AIMICNew(DataUtil.getNativeByteArray(stringBuffer.toString()), this.f3307l)) != 0) {
            DebugLog.LogE("AIMICNew return error: " + i);
            AIMIC.AIMICDestroy(AIMIC.getHandler());
        }
        if (i != 0) {
            throw new SpeechError(i);
        }
        this.f3302g = true;
        this.f3305j.start();
        int i2 = this.f3306k;
        if (i2 == 0 || 2 == i2) {
            this.f3304i.m2219a(491520L);
        }
        DebugLog.LogD("aimic constructor leave: " + i);
    }

    /* renamed from: h */
    public static C3735n m2169h() {
        C3735n c3735n;
        DebugLog.LogD("aimic getAIMic enter");
        synchronized (f3297e) {
            c3735n = f3296d;
        }
        DebugLog.LogD("aimic getAIMic leave: " + c3735n);
        return c3735n;
    }

    /* renamed from: c */
    public static C3735n m2166c(String str) {
        C3735n c3735n;
        DebugLog.LogD("aimic createAIMic enter");
        synchronized (f3297e) {
            if (f3296d == null) {
                try {
                    f3296d = new C3735n(str);
                } catch (SpeechError e) {
                    DebugLog.LogE(e);
                } catch (UnsatisfiedLinkError e2) {
                    DebugLog.LogE(e2);
                } catch (Throwable th) {
                    DebugLog.LogE(th);
                }
            }
            c3735n = f3296d;
        }
        DebugLog.LogD("aimic createAIMic leave: " + c3735n);
        return c3735n;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: b */
    public void mo2156b() {
        DebugLog.LogD("aimic destroy enter");
        synchronized (this.f3301f) {
            this.f3302g = false;
            if (this.f3307l != null) {
                this.f3307l.m2180b();
            }
            if (this.f3312q != null) {
                this.f3312q.m2208c();
                this.f3312q.m2213h();
                this.f3312q = null;
            }
        }
        synchronized (this.f3305j) {
            if (this.f3305j.isAlive()) {
                this.f3305j.notify();
            }
        }
        synchronized (f3297e) {
            if (f3296d != null) {
                try {
                    AIMIC.AIMICDestroy(AIMIC.getHandler());
                } catch (Throwable th) {
                    DebugLog.LogE(th);
                }
                f3296d = null;
            }
        }
        System.gc();
        DebugLog.LogD("aimic destroy leave");
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: a */
    public void mo2153a(AbstractC3734m.b bVar) {
        DebugLog.LogD("aimic  registerListener enter: " + bVar);
        synchronized (this.f3301f) {
            this.f3307l.m2178a(bVar);
        }
        DebugLog.LogD("aimic  setParameter leave");
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: a */
    public void mo2152a(AbstractC3734m.a aVar) {
        DebugLog.LogD("aimic  registerListener enter: " + aVar);
        synchronized (this.f3301f) {
            this.f3307l.m2177a(aVar);
        }
        DebugLog.LogD("aimic  registerListener leave");
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: b */
    public void mo2158b(AbstractC3734m.b bVar) {
        DebugLog.LogD("aimic  unregisterListener enter: " + bVar);
        synchronized (this.f3301f) {
            this.f3307l.m2182b(bVar);
            if (this.f3307l.m2179a()) {
                mo2161e();
            }
        }
        DebugLog.LogD("aimic  unregisterListener leave");
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: b */
    public void mo2157b(AbstractC3734m.a aVar) {
        DebugLog.LogD("aimic  unregisterListener enter: " + aVar);
        synchronized (this.f3301f) {
            this.f3307l.m2181b(aVar);
            if (this.f3307l.m2179a()) {
                mo2161e();
            }
        }
        DebugLog.LogD("aimic  unregisterListener leave");
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: a */
    public int mo2150a(String str, String str2) {
        int i;
        DebugLog.LogD("aimic  setParameter enter key=" + str + ", value=" + str2);
        try {
            synchronized (this.f3301f) {
                DebugLog.LogD("aimic  setParameter sync");
                boolean equalsIgnoreCase = "aimic_asr_buffer_time".equalsIgnoreCase(str);
                i = ErrorCode.ERROR_AIMIC_NOT_INIT;
                if (equalsIgnoreCase) {
                    this.f3308m = Integer.parseInt(str2);
                    this.f3307l.m2175a(this.f3308m);
                } else if ("alsa_rate".equalsIgnoreCase(str)) {
                    this.f3314s = Integer.parseInt(str2);
                } else if ("alsa_card".equalsIgnoreCase(str)) {
                    this.f3315t = Integer.parseInt(str2);
                } else if ("alsa_per_size".equalsIgnoreCase(str)) {
                    this.f3316u = Integer.parseInt(str2);
                } else if ("alsa_save".equalsIgnoreCase(str)) {
                    this.f3313r.m2194a(m2168d(str2));
                } else if (SpeechConstant.AUDIO_SOURCE.equalsIgnoreCase(str)) {
                    this.f3317v = Integer.parseInt(str2);
                } else if ("buf_mode".equalsIgnoreCase(str)) {
                    this.f3306k = Integer.parseInt(str2);
                    if (this.f3306k == 1) {
                        this.f3304i.m2219a(491520 - this.f3304i.m2218a());
                    } else {
                        this.f3304i.m2219a(983040 - this.f3304i.m2218a());
                    }
                    if (m2172k()) {
                        DebugLog.LogD("aimic  setParameter Native");
                        i = AIMIC.AIMICSetParam(AIMIC.getHandler(), DataUtil.getNativeByteArray(str), DataUtil.getNativeByteArray(str2));
                    }
                } else if ("thread_priority".equalsIgnoreCase(str)) {
                    if (m2172k()) {
                        DebugLog.LogD("aimic  setParameter Native");
                        i = AIMIC.AIMICSetParam(AIMIC.getHandler(), DataUtil.getNativeByteArray(str), DataUtil.getNativeByteArray(str2));
                        this.f3305j.m2199a(Integer.parseInt(str2));
                    }
                } else if (m2172k()) {
                    DebugLog.LogD("aimic  setParameter Native");
                    i = AIMIC.AIMICSetParam(AIMIC.getHandler(), DataUtil.getNativeByteArray(str), DataUtil.getNativeByteArray(str2));
                }
                i = 0;
            }
        } catch (UnsatisfiedLinkError e) {
            DebugLog.LogE(e);
            i = 20021;
        } catch (Throwable th) {
            DebugLog.LogE(th);
            i = 20999;
        }
        DebugLog.LogD("aimic  setParameter leave: " + i);
        return i;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: b */
    public String mo2155b(String str) {
        String str2;
        DebugLog.LogD("aimic  getParameter enter");
        byte[] bArr = this.f3303h;
        synchronized (this.f3301f) {
            str2 = null;
            if (m2172k()) {
                try {
                    int AIMICGetParam = AIMIC.AIMICGetParam(AIMIC.getHandler(), DataUtil.getNativeByteArray(str), bArr);
                    if (AIMICGetParam == 0) {
                        str2 = DataUtil.getStringFromNativeByteArray(bArr);
                    } else {
                        DebugLog.LogE("get parameter error: " + AIMICGetParam);
                    }
                } catch (Throwable th) {
                    DebugLog.LogE(th);
                }
            }
        }
        DebugLog.LogD("aimic  getParameter leave: " + str2);
        return str2;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: c */
    public int mo2159c() {
        int i;
        DebugLog.LogD("aimic  reset enter");
        synchronized (this.f3301f) {
            try {
                try {
                    i = m2172k() ? AIMIC.AIMICResetEng(AIMIC.getHandler()) : ErrorCode.ERROR_AIMIC_NOT_INIT;
                } catch (SpeechError e) {
                    DebugLog.LogE(e);
                    i = e.getErrorCode();
                } catch (UnsatisfiedLinkError e2) {
                    DebugLog.LogE(e2);
                    i = 20021;
                }
            } finally {
                DebugLog.LogD("aimic  reset leave: " + i);
                return i;
            }
        }
        DebugLog.LogD("aimic  reset leave: " + i);
        return i;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: d */
    public int mo2160d() {
        int i;
        DebugLog.LogD("aimic  startListening enter");
        synchronized (this.f3301f) {
            if (!this.f3318w) {
                DebugLog.LogD("aimic  startListening clear old datas.");
                this.f3304i.m2224d();
            }
            if (!m2172k()) {
                i = ErrorCode.ERROR_AIMIC_NOT_INIT;
            } else if (-3 == this.f3317v) {
                i = m2173l();
            } else if (-1 == this.f3317v) {
                if (this.f3312q != null) {
                    i = ErrorCode.ERROR_AIMIC_BUSY;
                    DebugLog.LogE("startListening failed, current internal recorder is not stoped!");
                } else {
                    i = 0;
                }
            } else {
                i = ErrorCode.ERROR_AIMIC_INVALID_PARA_VALUE;
                DebugLog.LogE("startListening failed, invalid audio source: " + this.f3317v);
            }
            this.f3318w = i == 0;
        }
        DebugLog.LogD("aimic  startListening leave: " + i);
        return i;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: e */
    public void mo2161e() {
        DebugLog.LogD("aimic  stopListening enter");
        synchronized (this.f3301f) {
            if (this.f3307l == null || this.f3307l.m2179a()) {
                DebugLog.LogD("AIMic Listener is empty, audio recorder will stop recording.");
                m2174m();
                this.f3318w = false;
            }
        }
        DebugLog.LogD("aimic  stopListening leave");
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: a */
    public int mo2151a(byte[] bArr, int i, int i2) {
        int i3;
        synchronized (this.f3301f) {
            if (m2172k()) {
                i3 = m2163b(bArr, i, i2);
            } else {
                i3 = ErrorCode.ERROR_AIMIC_NOT_INIT;
                DebugLog.LogE("write audio while not init!");
            }
        }
        return i3;
    }

    /* renamed from: i */
    public static String m2170i() {
        String str;
        synchronized (f3297e) {
            try {
                str = DataUtil.getStringFromNativeByteArray(AIMIC.AIMICGetVersion());
            } finally {
                return str;
            }
        }
        return str;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m
    /* renamed from: a */
    public void mo2154a(boolean z, int i) {
        synchronized (this.f3301f) {
            try {
                DebugLog.setShowLog(z);
                DebugLog.setLogLevel(DebugLog.LOG_LEVEL.values()[i]);
                AIMIC.AIMICDebugLog(z, i);
                if (this.f3312q != null) {
                    this.f3312q.m2206a(z && DebugLog.LOG_LEVEL.detail.ordinal() >= i);
                }
            } finally {
            }
        }
    }

    /* renamed from: j */
    public static int m2171j() {
        try {
            return AIMIC.AIMICGetChannel();
        } catch (Throwable th) {
            DebugLog.LogE(th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public boolean m2172k() {
        return this.f3302g;
    }

    /* renamed from: b */
    private int m2163b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        try {
            this.f3304i.m2221a(bArr, i, i2);
            synchronized (this.f3305j) {
                if (Thread.State.WAITING == this.f3305j.getState()) {
                    this.f3305j.notify();
                }
            }
            SpeechError m2198a = this.f3305j.m2198a();
            if (m2198a != null) {
                int errorCode = m2198a.getErrorCode();
                this.f3305j.m2200b();
                return errorCode;
            }
        } catch (IllegalArgumentException e) {
            DebugLog.LogE(e);
            return ErrorCode.ERROR_AIMIC_INVALID_PARA;
        } catch (NullPointerException e2) {
            DebugLog.LogE(e2);
            return ErrorCode.ERROR_AIMIC_NULL_HANDLE;
        } catch (OutOfMemoryError e3) {
            DebugLog.LogE(e3);
            if (2 == this.f3306k) {
                DebugLog.LogE("write audio too soon, current audios  in buffer will be ignored!");
                this.f3304i.m2224d();
            } else {
                DebugLog.LogE("write audio too soon, please wait for a second, and try again!");
                i3 = ErrorCode.ERROR_AIMIC_BUSY;
            }
        } catch (Throwable th) {
            DebugLog.LogE("write audio too soon, please wait for a second, and try again!");
            DebugLog.LogE(th);
            return 20999;
        }
        return i3;
    }

    /* renamed from: d */
    private boolean m2168d(String str) {
        return str != null && (str.equals("true") || str.equals("1"));
    }

    /* renamed from: l */
    private int m2173l() {
        if (!m2172k()) {
            return ErrorCode.ERROR_AIMIC_NOT_INIT;
        }
        this.f3312q = C3736o.m2201a();
        C3736o c3736o = this.f3312q;
        if (c3736o == null || c3736o.m2209d() != this.f3315t || this.f3312q.m2210e() != this.f3314s || this.f3312q.m2211f() != this.f3316u) {
            C3736o c3736o2 = this.f3312q;
            if (c3736o2 != null) {
                c3736o2.m2208c();
                this.f3312q.m2213h();
                this.f3312q = null;
            }
            DebugLog.LogD("create new audio recorder");
            this.f3312q = C3736o.m2202a(this.f3315t, this.f3314s, this.f3316u);
        }
        C3736o c3736o3 = this.f3312q;
        if (c3736o3 == null) {
            return ErrorCode.ERROR_AIMIC_CREATE_HANDLE;
        }
        if (c3736o3.m2207b() && !this.f3313r.equals(this.f3312q.m2212g())) {
            DebugLog.LogD("Current record listener is not this, recorder will be stoped.");
            this.f3312q.m2208c();
        }
        this.f3312q.m2206a(DebugLog.getShowLog() && DebugLog.LOG_LEVEL.detail.ordinal() >= DebugLog.getLogLevel().ordinal());
        if (!this.f3312q.m2207b()) {
            DebugLog.LogD("start audio recording.");
            return this.f3312q.m2205a(this.f3313r);
        }
        DebugLog.LogD("audio recorder is recording.");
        return 0;
    }

    /* renamed from: m */
    private void m2174m() {
        if (this.f3312q != null) {
            DebugLog.LogD("stop audio record");
            this.f3312q.m2208c();
            this.f3313r.m2193a();
            this.f3312q = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.n$c */
    /* loaded from: classes3.dex */
    public class c extends Thread {

        /* renamed from: b */
        private final int f3346b;

        /* renamed from: c */
        private final int f3347c;

        /* renamed from: d */
        private final int f3348d;

        /* renamed from: e */
        private final C3737p f3349e;

        /* renamed from: f */
        private int f3350f;

        /* renamed from: g */
        private final HashMap<Integer, Integer> f3351g;

        /* renamed from: h */
        private SpeechError f3352h;

        /* renamed from: i */
        private long f3353i;

        /* renamed from: j */
        private boolean f3354j;

        public c(String str) {
            super(str);
            this.f3346b = 1;
            this.f3347c = 16;
            this.f3348d = 1000;
            this.f3349e = C3735n.this.f3304i;
            this.f3350f = 10;
            this.f3351g = new HashMap<>();
            this.f3352h = null;
            this.f3353i = System.currentTimeMillis();
            this.f3354j = true;
            this.f3351g.put(0, 1);
            this.f3351g.put(1, 5);
            this.f3351g.put(2, 10);
        }

        /* renamed from: a */
        public SpeechError m2198a() {
            return this.f3352h;
        }

        /* renamed from: b */
        public void m2200b() {
            this.f3352h = null;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i;
            Throwable th;
            UnsatisfiedLinkError e;
            C3737p.a m2226f;
            DebugLog.LogD("aimic audio writing thread enter");
            m2196b(this.f3350f);
            int i2 = 0;
            int i3 = 0;
            loop0: while (true) {
                int i4 = i3;
                while (C3735n.this.m2172k()) {
                    try {
                        m2226f = this.f3349e.m2226f();
                    } catch (UnsatisfiedLinkError e2) {
                        i = i3;
                        e = e2;
                    } catch (Throwable th2) {
                        i = i3;
                        th = th2;
                    }
                    if (m2226f != null) {
                        i2++;
                        if (30 <= i2) {
                            try {
                                DebugLog.LogD("current buf container size in aimic thread is " + this.f3349e.m2223c());
                                i2 = 0;
                            } catch (UnsatisfiedLinkError e3) {
                                i = i3;
                                e = e3;
                                i2 = 0;
                                DebugLog.LogE(e);
                                this.f3352h = new SpeechError(20021);
                                i3 = i;
                            } catch (Throwable th3) {
                                i = i3;
                                th = th3;
                                i2 = 0;
                                DebugLog.LogE(th);
                                this.f3352h = new SpeechError(20999);
                                i3 = i;
                            }
                        }
                        try {
                            int AIMICAudioWrite = AIMIC.AIMICAudioWrite(AIMIC.getHandler(), m2226f.getKey(), 0, m2226f.getValue().intValue());
                            this.f3349e.m2220a(m2226f);
                            if (AIMICAudioWrite != 0) {
                                DebugLog.LogE("AIMICAudioWrite error: " + AIMICAudioWrite);
                                C3735n.this.f3307l.m2176a(new SpeechError(AIMICAudioWrite));
                            }
                            i3 = 0;
                        } catch (UnsatisfiedLinkError e4) {
                            e = e4;
                            i4 = 0;
                            i = 0;
                            DebugLog.LogE(e);
                            this.f3352h = new SpeechError(20021);
                            i3 = i;
                        } catch (Throwable th4) {
                            th = th4;
                            i4 = 0;
                            i = 0;
                            DebugLog.LogE(th);
                            this.f3352h = new SpeechError(20999);
                            i3 = i;
                        }
                    } else {
                        m2197c();
                        if (1000 <= i3) {
                            try {
                                synchronized (this) {
                                    if (this.f3349e.m2225e()) {
                                        DebugLog.LogD("aimic audio writing will suspend");
                                        wait();
                                        DebugLog.LogD("aimic audio writing is waked");
                                    }
                                }
                                i3 = 0;
                            } catch (UnsatisfiedLinkError e5) {
                                e = e5;
                                i = 0;
                                DebugLog.LogE(e);
                                this.f3352h = new SpeechError(20021);
                                i3 = i;
                            } catch (Throwable th5) {
                                th = th5;
                                i = 0;
                                DebugLog.LogE(th);
                                this.f3352h = new SpeechError(20999);
                                i3 = i;
                            }
                        } else {
                            i3++;
                            i4 = Math.min(i4 + 1, 16);
                            sleep(i4);
                        }
                    }
                }
                this.f3349e.m2224d();
                DebugLog.LogD("aimic audio writing thread exited");
                return;
            }
        }

        /* renamed from: a */
        public void m2199a(int i) {
            Integer num = this.f3351g.get(Integer.valueOf(i));
            this.f3350f = num != null ? num.intValue() : this.f3350f;
            DebugLog.LogD("set priority, target aimic priority: " + i + ", real target priority: " + this.f3350f);
            m2196b(this.f3350f);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x004f, code lost:
        
            com.iflytek.cloud.msc.util.log.DebugLog.LogD("get a thread group has target priority");
         */
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void m2196b(int i) {
            DebugLog.LogD("update priority enter, target priority: " + i + ", current priority: " + getPriority());
            if (i != getPriority()) {
                try {
                    LinkedList linkedList = new LinkedList();
                    ThreadGroup threadGroup = getThreadGroup();
                    while (true) {
                        if (threadGroup == null) {
                            break;
                        }
                        DebugLog.LogD("thread group name: " + threadGroup.getName());
                        try {
                            if (i <= threadGroup.getMaxPriority()) {
                                break;
                            }
                            threadGroup.checkAccess();
                            linkedList.addFirst(threadGroup);
                            threadGroup = threadGroup.getParent();
                        } catch (Throwable th) {
                            DebugLog.LogE("exception while improve thread group priority");
                            DebugLog.LogE(th);
                        }
                    }
                    Iterator it = linkedList.iterator();
                    while (it.hasNext()) {
                        ThreadGroup threadGroup2 = (ThreadGroup) it.next();
                        if (i > threadGroup2.getMaxPriority()) {
                            threadGroup2.setMaxPriority(i);
                        }
                    }
                    setPriority(i);
                    DebugLog.LogD("set thread priority to target, after that is: " + getPriority());
                } catch (Throwable th2) {
                    DebugLog.LogE("exception while set thread priority");
                    DebugLog.LogE(th2);
                }
            }
            DebugLog.LogD("update priority leave");
        }

        /* renamed from: c */
        private void m2197c() {
            if (1 == C3735n.this.f3306k || 120000 < System.currentTimeMillis() - this.f3353i) {
                return;
            }
            long m2227g = this.f3349e.m2227g();
            if (49152 < m2227g && m2227g + this.f3349e.m2222b() > 491520) {
                this.f3354j = !this.f3354j;
                if (!this.f3354j) {
                    this.f3349e.m2228h();
                }
            }
            this.f3353i = System.currentTimeMillis();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.n$a */
    /* loaded from: classes3.dex */
    public static class a implements AIMIC.Listener {

        /* renamed from: a */
        private final HashSet<AbstractC3734m.b> f3319a;

        /* renamed from: b */
        private final HashSet<AbstractC3734m.a> f3320b;

        /* renamed from: c */
        private final Object f3321c;

        /* renamed from: d */
        private final Object f3322d;

        /* renamed from: e */
        private final int f3323e;

        /* renamed from: f */
        private final int f3324f;

        /* renamed from: g */
        private final int f3325g;

        /* renamed from: h */
        private final int f3326h;

        /* renamed from: i */
        private final C3737p f3327i;

        /* renamed from: j */
        private final int f3328j;

        /* renamed from: k */
        private boolean f3329k;

        /* renamed from: l */
        private int f3330l;

        private a() {
            this.f3319a = new HashSet<>();
            this.f3320b = new HashSet<>();
            this.f3321c = new Object();
            this.f3322d = new Object();
            this.f3323e = 32;
            this.f3324f = 4000;
            this.f3325g = 128000;
            this.f3326h = 512;
            this.f3327i = new C3737p(128000L, 512, 0L, false, true);
            this.f3328j = 1;
            this.f3329k = true;
            this.f3330l = 128000;
        }

        /* renamed from: a */
        public boolean m2179a() {
            HashSet<AbstractC3734m.a> hashSet;
            HashSet<AbstractC3734m.b> hashSet2 = this.f3319a;
            return (hashSet2 == null || hashSet2.isEmpty()) && ((hashSet = this.f3320b) == null || hashSet.isEmpty());
        }

        /* renamed from: b */
        public void m2180b() {
            HashSet<AbstractC3734m.b> hashSet = this.f3319a;
            if (hashSet != null) {
                hashSet.clear();
            }
            HashSet<AbstractC3734m.a> hashSet2 = this.f3320b;
            if (hashSet2 != null) {
                hashSet2.clear();
            }
        }

        /* renamed from: a */
        public void m2175a(int i) {
            this.f3330l = i * 32;
        }

        /* renamed from: a */
        public void m2178a(AbstractC3734m.b bVar) {
            if (bVar != null) {
                synchronized (this.f3321c) {
                    this.f3319a.add(bVar);
                }
            }
        }

        /* renamed from: a */
        public void m2177a(AbstractC3734m.a aVar) {
            if (aVar != null) {
                synchronized (this.f3322d) {
                    this.f3320b.add(aVar);
                }
            }
        }

        /* renamed from: b */
        public void m2182b(AbstractC3734m.b bVar) {
            if (bVar != null) {
                synchronized (this.f3321c) {
                    this.f3319a.remove(bVar);
                }
            }
        }

        /* renamed from: b */
        public void m2181b(AbstractC3734m.a aVar) {
            if (aVar != null) {
                synchronized (this.f3322d) {
                    this.f3320b.remove(aVar);
                }
            }
        }

        @Override // com.iflytek.msc.AIMIC.Listener
        public void onWakeupAudio(byte[] bArr, int i, int i2, Object obj) {
            try {
                synchronized (this.f3321c) {
                    Iterator<AbstractC3734m.b> it = this.f3319a.iterator();
                    while (it.hasNext()) {
                        it.next().mo2118a(bArr, i, i2, obj);
                    }
                }
            } catch (Throwable th) {
                m2176a(new SpeechError(th, 20999));
            }
        }

        @Override // com.iflytek.msc.AIMIC.Listener
        public void onWakeupMsg(int i, int i2, int i3, byte[] bArr, int i4, byte[] bArr2, int i5, byte[] bArr3, int i6) {
            try {
                DebugLog.LogS("onWakeupMsg enter");
                boolean z = true;
                if (1 == i) {
                    synchronized (this.f3322d) {
                        if (this.f3330l <= 0) {
                            z = false;
                        }
                        this.f3329k = z;
                    }
                }
                synchronized (this.f3321c) {
                    Iterator<AbstractC3734m.b> it = this.f3319a.iterator();
                    while (it.hasNext()) {
                        it.next().mo2116a(i, i2, i3, bArr, i4, bArr2, i5, bArr3, i6);
                    }
                }
                DebugLog.LogS("onWakeupMsg leave");
            } catch (Throwable th) {
                DebugLog.LogE(th);
                m2176a(new SpeechError(th, 20999));
            }
        }

        @Override // com.iflytek.msc.AIMIC.Listener
        public void onRecogAudio(byte[] bArr, int i, int i2, Object obj) {
            try {
                synchronized (this.f3322d) {
                    this.f3329k = this.f3329k && this.f3320b.isEmpty();
                    if (this.f3329k) {
                        long m2222b = this.f3327i.m2222b();
                        if (i + m2222b > this.f3330l) {
                            DebugLog.LogD(m2222b + " matched max buffering len: " + this.f3330l + ", will be clean");
                            this.f3329k = false;
                            this.f3327i.m2224d();
                        } else {
                            this.f3327i.m2221a(bArr, 0, i);
                        }
                    } else {
                        Iterator<AbstractC3734m.a> it = this.f3320b.iterator();
                        while (it.hasNext()) {
                            AbstractC3734m.a next = it.next();
                            if (!this.f3327i.m2225e()) {
                                C3737p c3737p = this.f3327i;
                                while (true) {
                                    C3737p.a m2226f = c3737p.m2226f();
                                    if (m2226f == null) {
                                        break;
                                    }
                                    next.mo2120b(m2226f.getKey(), m2226f.getValue().intValue(), 0, null);
                                    this.f3327i.m2220a(m2226f);
                                    c3737p = this.f3327i;
                                }
                                this.f3327i.m2224d();
                            }
                            next.mo2120b(bArr, i, i2, obj);
                        }
                    }
                }
            } catch (Throwable th) {
                DebugLog.LogE(th);
                m2176a(new SpeechError(th, 20999));
            }
        }

        /* renamed from: a */
        public void m2176a(SpeechError speechError) {
            DebugLog.LogE(speechError);
            synchronized (this.f3321c) {
                Iterator<AbstractC3734m.b> it = this.f3319a.iterator();
                while (it.hasNext()) {
                    it.next().mo2115a(speechError.getErrorCode());
                }
            }
            synchronized (this.f3322d) {
                Iterator<AbstractC3734m.a> it2 = this.f3320b.iterator();
                while (it2.hasNext()) {
                    it2.next().mo2115a(speechError.getErrorCode());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.n$b */
    /* loaded from: classes3.dex */
    public class b implements C3736o.b {

        /* renamed from: b */
        private int f3332b;

        /* renamed from: c */
        private final int f3333c;

        /* renamed from: d */
        private final int f3334d;

        /* renamed from: e */
        private final int f3335e;

        /* renamed from: f */
        private final C3737p f3336f;

        /* renamed from: g */
        private final C3737p f3337g;

        /* renamed from: h */
        private volatile C3737p f3338h;

        /* renamed from: i */
        private volatile C3737p f3339i;

        /* renamed from: j */
        private final String f3340j;

        /* renamed from: k */
        private Thread f3341k;

        /* renamed from: l */
        private boolean f3342l;

        /* renamed from: m */
        private boolean f3343m;

        private b() {
            this.f3332b = 0;
            this.f3333c = CpioConstants.C_ISSOCK;
            this.f3334d = 512000;
            this.f3335e = 61440000;
            this.f3336f = new C3737p(512000L, CpioConstants.C_ISSOCK, 0L, true, false);
            this.f3337g = new C3737p(512000L, CpioConstants.C_ISSOCK, 0L, true, false);
            this.f3338h = this.f3336f;
            this.f3339i = this.f3337g;
            this.f3340j = Environment.getExternalStorageDirectory().getPath() + "/aimic_alsa.pcm";
            this.f3341k = null;
            this.f3342l = false;
            this.f3343m = false;
        }

        @Override // com.iflytek.cloud.thirdparty.C3736o.b
        /* renamed from: a */
        public void mo2195a(byte[] bArr, int i) {
            if (m2190b()) {
                m2189b(bArr, i);
            }
            this.f3332b = C3735n.this.mo2151a(bArr, 0, i);
            if (this.f3332b != 0) {
                C3735n.this.f3307l.m2176a(new SpeechError(this.f3332b));
            }
        }

        /* renamed from: a */
        public void m2193a() {
            this.f3343m = false;
            if (m2190b()) {
                m2184a(0, true);
            }
        }

        /* renamed from: a */
        public void m2194a(boolean z) {
            this.f3342l = z;
        }

        /* renamed from: b */
        private boolean m2190b() {
            return this.f3342l;
        }

        /* renamed from: b */
        private void m2189b(byte[] bArr, int i) {
            if (this.f3341k == null) {
                File file = new File(this.f3340j);
                if (file.exists()) {
                    file.delete();
                }
                this.f3336f.m2224d();
                this.f3337g.m2224d();
                this.f3338h = this.f3336f;
                this.f3339i = this.f3337g;
                this.f3343m = true;
                m2192c();
            }
            try {
                m2184a(i, false);
                synchronized (this.f3338h) {
                    this.f3338h.m2221a(bArr, 0, i);
                }
            } catch (Throwable th) {
                DebugLog.LogE(th);
                C3735n.this.f3307l.m2176a(new SpeechError(th, 20999));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public boolean m2187a(RandomAccessFile randomAccessFile, MemoryFile memoryFile, int i, int i2) {
            try {
                if (randomAccessFile == null || memoryFile == null || i < 0 || i2 < 0) {
                    DebugLog.LogE("saveFileFromMemory error: arguments error");
                    return false;
                }
                int i3 = i2 + i;
                byte[] bArr = new byte[65536];
                while (i3 > i) {
                    int min = Math.min(bArr.length, i3 - i);
                    if (memoryFile.readBytes(bArr, i, 0, min) != min) {
                        DebugLog.LogE("saveFileFromMemory error: read bytes length error!");
                        return false;
                    }
                    i += min;
                    randomAccessFile.write(bArr, 0, min);
                }
                return true;
            } catch (Throwable th) {
                DebugLog.LogE(th);
                return false;
            }
        }

        /* renamed from: c */
        private void m2192c() {
            this.f3341k = new Thread("AlsaAudioSavingThread") { // from class: com.iflytek.cloud.thirdparty.n.b.1
                /* JADX WARN: Removed duplicated region for block: B:61:0x0140 A[Catch: all -> 0x013c, TRY_LEAVE, TryCatch #6 {all -> 0x013c, blocks: (B:72:0x0138, B:61:0x0140), top: B:71:0x0138 }] */
                /* JADX WARN: Removed duplicated region for block: B:68:0x0153  */
                /* JADX WARN: Removed duplicated region for block: B:71:0x0138 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                @Override // java.lang.Thread, java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public void run() {
                    MemoryFile memoryFile;
                    RandomAccessFile randomAccessFile;
                    SpeechError speechError;
                    int i;
                    int i2;
                    DebugLog.LogD("alsa data save thread enter");
                    try {
                        memoryFile = new MemoryFile(null, 61952000);
                        try {
                            memoryFile.allowPurging(false);
                            i = 0;
                            boolean z = false;
                            while (b.this.f3343m) {
                                synchronized (b.this.f3339i) {
                                    while (!b.this.f3339i.m2225e()) {
                                        C3737p.a m2226f = b.this.f3339i.m2226f();
                                        memoryFile.writeBytes(m2226f.getKey(), 0, i, m2226f.getValue().intValue());
                                        i += m2226f.getValue().intValue();
                                        b.this.f3339i.m2220a(m2226f);
                                    }
                                    b.this.f3339i.m2224d();
                                }
                                if (61440000 <= i) {
                                    z = true;
                                    i = 0;
                                }
                                synchronized (this) {
                                    if (b.this.f3339i.m2225e()) {
                                        wait();
                                    }
                                }
                            }
                            synchronized (b.this.f3339i) {
                                while (!b.this.f3339i.m2225e()) {
                                    C3737p.a m2226f2 = b.this.f3339i.m2226f();
                                    memoryFile.writeBytes(m2226f2.getKey(), 0, i, m2226f2.getValue().intValue());
                                    i += m2226f2.getValue().intValue();
                                    b.this.f3339i.m2220a(m2226f2);
                                }
                                b.this.f3339i.m2224d();
                            }
                            randomAccessFile = new RandomAccessFile(b.this.f3340j, "rw");
                            i2 = z ? 61440000 - i : 0;
                        } catch (Throwable th) {
                            th = th;
                            randomAccessFile = null;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        memoryFile = null;
                        randomAccessFile = null;
                    }
                    if (i2 > 0) {
                        try {
                        } catch (Throwable th3) {
                            th = th3;
                            DebugLog.LogE(th);
                            DebugLog.LogE("保存文件失败！");
                            speechError = new SpeechError(th, 20999);
                            if (memoryFile != null) {
                            }
                            if (randomAccessFile != null) {
                            }
                            b.this.f3341k = null;
                            if (speechError != null) {
                            }
                            DebugLog.LogD("alsa data save thread exit");
                        }
                        if (!b.this.m2187a(randomAccessFile, memoryFile, i, i2)) {
                            speechError = new SpeechError(20999);
                            DebugLog.LogE("save last memory file data failed!");
                            if (speechError == null && !b.this.m2187a(randomAccessFile, memoryFile, 0, Math.min(61440000, i))) {
                                speechError = new SpeechError(20999);
                                DebugLog.LogE("save current memory file data failed!");
                            }
                            if (memoryFile != null) {
                                try {
                                    memoryFile.close();
                                } catch (Throwable th4) {
                                    DebugLog.LogE(th4);
                                    DebugLog.LogE("关闭文件异常！");
                                }
                            }
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                            b.this.f3341k = null;
                            if (speechError != null) {
                                C3735n.this.f3307l.m2176a(speechError);
                            }
                            DebugLog.LogD("alsa data save thread exit");
                        }
                    }
                    speechError = null;
                    if (speechError == null) {
                        speechError = new SpeechError(20999);
                        DebugLog.LogE("save current memory file data failed!");
                    }
                    if (memoryFile != null) {
                    }
                    if (randomAccessFile != null) {
                    }
                    b.this.f3341k = null;
                    if (speechError != null) {
                    }
                    DebugLog.LogD("alsa data save thread exit");
                }
            };
            this.f3341k.start();
        }

        /* renamed from: a */
        private void m2184a(int i, boolean z) {
            C3737p c3737p;
            synchronized (this.f3338h) {
                if (!z) {
                    if (this.f3338h.m2222b() + i < 512000) {
                        c3737p = null;
                    }
                }
                c3737p = this.f3338h;
                this.f3338h = c3737p.equals(this.f3337g) ? this.f3336f : this.f3337g;
            }
            if (c3737p != null) {
                synchronized (this.f3339i) {
                    this.f3339i = c3737p;
                }
                Thread thread = this.f3341k;
                if (thread != null) {
                    synchronized (thread) {
                        if (Thread.State.WAITING == this.f3341k.getState()) {
                            this.f3341k.notify();
                        }
                    }
                }
                synchronized (this.f3338h) {
                    if (0 != this.f3338h.m2222b()) {
                        DebugLog.LogE("Error: buffer is not null when exchanged!");
                        C3735n.this.f3307l.m2176a(new SpeechError(20999));
                    }
                }
            }
        }
    }
}
