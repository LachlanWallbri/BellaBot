package com.iflytek.cloud.record;

import android.content.Context;
import android.media.AudioTrack;
import android.os.MemoryFile;
import com.iflytek.cloud.msc.util.FileUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.iflytek.cloud.record.b */
/* loaded from: classes3.dex */
public class C3682b {

    /* renamed from: a */
    public volatile long f2865a;

    /* renamed from: h */
    private int f2872h;

    /* renamed from: i */
    private ArrayList<a> f2873i;

    /* renamed from: j */
    private Context f2874j;

    /* renamed from: k */
    private int f2875k;

    /* renamed from: l */
    private volatile long f2876l;

    /* renamed from: q */
    private String f2881q;

    /* renamed from: u */
    private int f2885u;

    /* renamed from: b */
    private final int f2866b = 2;

    /* renamed from: c */
    private final int f2867c = 1;

    /* renamed from: d */
    private final int f2868d = 16000;

    /* renamed from: e */
    private final int f2869e = 60;

    /* renamed from: f */
    private final int f2870f = 500;

    /* renamed from: g */
    private final int f2871g = 1920000;

    /* renamed from: m */
    private MemoryFile f2877m = null;

    /* renamed from: n */
    private volatile int f2878n = 0;

    /* renamed from: o */
    private a f2879o = null;

    /* renamed from: p */
    private String f2880p = "";

    /* renamed from: r */
    private byte[] f2882r = null;

    /* renamed from: s */
    private int f2883s = 0;

    /* renamed from: t */
    private int f2884t = 0;

    /* renamed from: v */
    private final float f2886v = 0.95f;

    /* renamed from: w */
    private boolean f2887w = true;

    /* renamed from: x */
    private int f2888x = 0;

    public C3682b(Context context, int i, int i2, String str, int i3) {
        this.f2872h = 1920000;
        this.f2873i = null;
        this.f2874j = null;
        this.f2875k = 16000;
        this.f2876l = 0L;
        this.f2865a = 0L;
        this.f2881q = null;
        this.f2885u = 100;
        this.f2874j = context;
        this.f2876l = 0L;
        this.f2873i = new ArrayList<>();
        this.f2865a = 0L;
        this.f2875k = i;
        this.f2881q = str;
        this.f2885u = i3;
        this.f2872h = (this.f2875k * 2 * 1 * i2) + 1920000;
        DebugLog.LogD("min audio seconds: " + i2 + ", max audio buf size: " + this.f2872h);
    }

    /* renamed from: a */
    public void m1687a(int i) {
        this.f2888x = i;
    }

    /* renamed from: a */
    public int m1686a() {
        return this.f2875k;
    }

    /* renamed from: b */
    public long m1692b() {
        return this.f2865a;
    }

    /* renamed from: a */
    public void m1689a(ArrayList<byte[]> arrayList, int i, int i2, int i3) throws IOException {
        DebugLog.LogI("buffer percent = " + i + ", beg=" + i2 + ", end=" + i3);
        a aVar = new a(this.f2865a, this.f2865a, i2, i3);
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            m1683a(arrayList.get(i4));
        }
        aVar.f2890b = this.f2865a;
        this.f2876l = i;
        synchronized (this.f2873i) {
            this.f2873i.add(aVar);
        }
        DebugLog.LogI("allSize = " + this.f2865a + " maxSize=" + this.f2872h);
    }

    /* renamed from: a */
    public boolean m1691a(String str) {
        DebugLog.LogD("save to local: format = " + str + " totalSize = " + this.f2865a + " maxSize=" + this.f2872h);
        if (FileUtil.saveFile(this.f2877m, this.f2865a, this.f2881q)) {
            return FileUtil.formatPcm(str, this.f2881q, m1686a());
        }
        return false;
    }

    /* renamed from: b */
    public boolean m1694b(int i) {
        if (((float) this.f2876l) > this.f2885u * 0.95f) {
            return true;
        }
        return this.f2865a / 32 >= ((long) i) && 0 < this.f2865a;
    }

    /* renamed from: a */
    private void m1683a(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        if (this.f2877m == null) {
            this.f2880p = m1685l();
            this.f2877m = new MemoryFile(this.f2880p, this.f2872h);
            this.f2877m.allowPurging(false);
        }
        this.f2877m.writeBytes(bArr, 0, (int) this.f2865a, bArr.length);
        this.f2865a += bArr.length;
    }

    /* renamed from: l */
    private String m1685l() {
        return FileUtil.getUserPath(this.f2874j) + System.currentTimeMillis() + "tts.pcm";
    }

    /* renamed from: c */
    public int m1695c() {
        MemoryFile memoryFile = this.f2877m;
        if (memoryFile != null) {
            return memoryFile.length();
        }
        return 0;
    }

    /* renamed from: d */
    public void m1697d() throws IOException {
        this.f2878n = 0;
        this.f2879o = null;
        if (this.f2873i.size() > 0) {
            this.f2879o = this.f2873i.get(0);
        }
    }

    /* renamed from: e */
    public int m1698e() {
        if (this.f2865a <= 0) {
            return 0;
        }
        return (int) (((this.f2878n - (this.f2884t - this.f2883s)) * this.f2876l) / this.f2865a);
    }

    /* renamed from: f */
    public a m1699f() {
        if (this.f2879o == null) {
            return null;
        }
        long j = this.f2878n - (this.f2884t - this.f2883s);
        if (j >= this.f2879o.f2889a && j <= this.f2879o.f2890b) {
            return this.f2879o;
        }
        synchronized (this.f2873i) {
            Iterator<a> it = this.f2873i.iterator();
            while (it.hasNext()) {
                this.f2879o = it.next();
                if (j >= this.f2879o.f2889a && j <= this.f2879o.f2890b) {
                    return this.f2879o;
                }
            }
            return null;
        }
    }

    /* renamed from: g */
    public boolean m1700g() {
        return ((long) this.f2885u) == this.f2876l && ((long) this.f2878n) >= this.f2865a && this.f2883s >= this.f2884t;
    }

    /* renamed from: h */
    public boolean m1701h() {
        return ((long) this.f2878n) < this.f2865a || this.f2883s < this.f2884t;
    }

    /* renamed from: c */
    public boolean m1696c(int i) {
        return ((long) i) <= ((this.f2865a - ((long) this.f2878n)) + ((long) this.f2884t)) - ((long) this.f2883s);
    }

    /* renamed from: i */
    public boolean m1702i() {
        return ((long) this.f2885u) == this.f2876l;
    }

    /* renamed from: a */
    public void m1690a(boolean z) {
        this.f2887w = z;
    }

    /* renamed from: j */
    public boolean m1703j() {
        return this.f2887w;
    }

    /* renamed from: a */
    public void m1688a(AudioTrack audioTrack, int i) throws IOException {
        if (this.f2883s >= this.f2884t) {
            m1684d(i);
        }
        int i2 = i * 2;
        int i3 = this.f2884t;
        int i4 = this.f2883s;
        int i5 = i2 > i3 - i4 ? i3 - i4 : i;
        audioTrack.write(this.f2882r, this.f2883s, i5);
        this.f2883s += i5;
        if (m1700g() && m1703j()) {
            m1693b(audioTrack, i);
        }
    }

    /* renamed from: b */
    public void m1693b(AudioTrack audioTrack, int i) {
        long j = this.f2865a;
        int i2 = this.f2888x;
        if (j < i2) {
            int i3 = (int) (i2 - this.f2865a);
            DebugLog.LogI("mBuffer.writeTrack writeTrackBlankBlock size: " + i3);
            audioTrack.write(new byte[i3], 0, i3);
        }
    }

    /* renamed from: k */
    public void m1704k() {
        DebugLog.LogD("deleteFile");
        try {
            if (this.f2877m != null) {
                this.f2877m.close();
                this.f2877m = null;
            }
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
    }

    protected void finalize() throws Throwable {
        m1704k();
        super.finalize();
    }

    /* renamed from: com.iflytek.cloud.record.b$a */
    /* loaded from: classes3.dex */
    public class a {

        /* renamed from: a */
        long f2889a;

        /* renamed from: b */
        long f2890b;

        /* renamed from: c */
        int f2891c;

        /* renamed from: d */
        int f2892d;

        public a(long j, long j2, int i, int i2) {
            this.f2889a = j;
            this.f2890b = j2;
            this.f2891c = i;
            this.f2892d = i2;
        }
    }

    /* renamed from: d */
    private void m1684d(int i) throws IOException {
        if (this.f2882r == null) {
            this.f2882r = new byte[i * 10];
        }
        int length = this.f2882r.length;
        int i2 = (int) (this.f2865a - this.f2878n);
        if (i2 < length) {
            length = i2;
        }
        this.f2877m.readBytes(this.f2882r, this.f2878n, 0, length);
        this.f2878n += length;
        this.f2883s = 0;
        this.f2884t = length;
        DebugLog.LogD("readAudio leave, dataSize=" + length + ", bufLen=" + i2);
    }
}
