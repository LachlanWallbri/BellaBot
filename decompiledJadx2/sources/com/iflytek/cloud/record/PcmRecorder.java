package com.iflytek.cloud.record;

import android.media.AudioRecord;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;

/* loaded from: classes3.dex */
public class PcmRecorder extends Thread {
    public static final int RATE16K = 16000;
    public static final int READ_INTERVAL40MS = 40;

    /* renamed from: a */
    private final short f2841a;

    /* renamed from: b */
    private byte[] f2842b;

    /* renamed from: c */
    private AudioRecord f2843c;

    /* renamed from: d */
    private PcmRecordListener f2844d;

    /* renamed from: e */
    private PcmRecordListener f2845e;

    /* renamed from: f */
    private volatile boolean f2846f;

    /* renamed from: g */
    private double f2847g;

    /* renamed from: h */
    private double f2848h;

    /* renamed from: i */
    private int f2849i;

    /* renamed from: j */
    private int f2850j;

    /* renamed from: k */
    private int f2851k;

    /* renamed from: l */
    private int f2852l;

    /* loaded from: classes3.dex */
    public interface PcmRecordListener {
        void onError(SpeechError speechError);

        void onRecordBuffer(byte[] bArr, int i, int i2);

        void onRecordReleased();

        void onRecordStarted(boolean z);
    }

    public PcmRecorder(int i, int i2) {
        this(i, i2, 1);
    }

    public PcmRecorder(int i, int i2, int i3) {
        this.f2841a = (short) 16;
        this.f2842b = null;
        this.f2843c = null;
        this.f2844d = null;
        this.f2845e = null;
        this.f2846f = false;
        this.f2847g = 0.0d;
        this.f2848h = 0.0d;
        this.f2849i = 16000;
        this.f2850j = 40;
        this.f2851k = 40;
        this.f2852l = i3;
        this.f2849i = i;
        this.f2850j = i2;
        int i4 = this.f2850j;
        if (i4 < 40 || i4 > 100) {
            this.f2850j = 40;
        }
        this.f2851k = 10;
    }

    /* renamed from: a */
    protected void mo1680a(short s, int i, int i2) throws SpeechError {
        if (this.f2843c != null) {
            DebugLog.LogD("[initRecord] recoder release first");
            m1679b();
        }
        int i3 = (i2 * i) / 1000;
        int i4 = (((i3 * 4) * 16) * s) / 8;
        int i5 = s == 1 ? 2 : 3;
        int minBufferSize = AudioRecord.getMinBufferSize(i, i5, 2);
        if (i4 < minBufferSize) {
            i4 = minBufferSize;
        }
        this.f2843c = new AudioRecord(this.f2852l, i, i5, 2, i4);
        this.f2842b = new byte[((s * i3) * 16) / 8];
        DebugLog.LogD("\nSampleRate:" + i + "\nChannel:" + i5 + "\nFormat:2\nFramePeriod:" + i3 + "\nBufferSize:" + i4 + "\nMinBufferSize:" + minBufferSize + "\nActualBufferSize:" + this.f2842b.length + "\n");
        if (this.f2843c.getState() == 1) {
            return;
        }
        DebugLog.LogD("create AudioRecord error");
        throw new SpeechError(20006);
    }

    /* renamed from: a */
    private int m1678a() throws SpeechError {
        PcmRecordListener pcmRecordListener;
        AudioRecord audioRecord = this.f2843c;
        if (audioRecord == null || this.f2844d == null) {
            return 0;
        }
        byte[] bArr = this.f2842b;
        int read = audioRecord.read(bArr, 0, bArr.length);
        if (read > 0 && (pcmRecordListener = this.f2844d) != null) {
            pcmRecordListener.onRecordBuffer(this.f2842b, 0, read);
        } else if (read < 0) {
            DebugLog.LogE("Record read data error: " + read);
            throw new SpeechError(20006);
        }
        return read;
    }

    /* renamed from: a */
    private double m1677a(byte[] bArr, int i) {
        double d = 0.0d;
        if (bArr == null || i <= 0) {
            return 0.0d;
        }
        double d2 = 0.0d;
        for (byte b : bArr) {
            d2 += b;
        }
        double length = d2 / bArr.length;
        for (byte b2 : bArr) {
            d += Math.pow(b2 - length, 2.0d);
        }
        return Math.sqrt(d / (bArr.length - 1));
    }

    public void stopRecord(boolean z) {
        this.f2846f = true;
        if (this.f2845e == null) {
            this.f2845e = this.f2844d;
        }
        this.f2844d = null;
        if (z) {
            synchronized (this) {
                try {
                    DebugLog.LogD("stopRecord...release");
                    if (this.f2843c != null) {
                        if (3 == this.f2843c.getRecordingState() && 1 == this.f2843c.getState()) {
                            DebugLog.LogD("stopRecord releaseRecording ing...");
                            this.f2843c.release();
                            DebugLog.LogD("stopRecord releaseRecording end...");
                            this.f2843c = null;
                        }
                        if (this.f2845e != null) {
                            this.f2845e.onRecordReleased();
                            this.f2845e = null;
                        }
                    }
                } catch (Exception e) {
                    DebugLog.LogE(e.toString());
                }
            }
        }
        DebugLog.LogD("stop record");
    }

    public void startRecording(PcmRecordListener pcmRecordListener) throws SpeechError {
        this.f2844d = pcmRecordListener;
        setPriority(10);
        start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            boolean z = true;
            if (!this.f2846f) {
                try {
                    mo1680a((short) 1, this.f2849i, this.f2850j);
                } catch (Exception unused) {
                    sleep(40L);
                    throw new SpeechError(20006);
                }
            }
            int i = 0;
            while (!this.f2846f) {
                try {
                    this.f2843c.startRecording();
                    if (this.f2843c.getRecordingState() != 3) {
                        DebugLog.LogE("recorder state is not recoding");
                        throw new SpeechError(20006);
                        break;
                    }
                    break;
                } catch (Exception unused2) {
                    i++;
                    if (i < 10) {
                        sleep(40L);
                    } else {
                        DebugLog.LogE("recoder start failed");
                        throw new SpeechError(20006);
                    }
                }
            }
            if (this.f2844d != null) {
                this.f2844d.onRecordStarted(true);
            }
            long currentTimeMillis = System.currentTimeMillis();
            while (!this.f2846f) {
                int m1678a = m1678a();
                DebugLog.LogE("2019-08-05:readRecordData:count=" + m1678a);
                if (z) {
                    this.f2847g += m1678a;
                    this.f2848h += m1677a(this.f2842b, this.f2842b.length);
                    DebugLog.LogE("2019-08-05:checkAudio:checkStandDev=" + this.f2848h);
                    if (System.currentTimeMillis() - currentTimeMillis >= 1000) {
                        if (this.f2847g == 0.0d || this.f2848h == 0.0d) {
                            DebugLog.LogE("2019-08-05:checkDataSum=" + this.f2847g + ",checkStandDev=" + this.f2848h);
                            DebugLog.LogE("cannot get record permission, get invalid audio data.");
                            throw new SpeechError(20006);
                        }
                        z = false;
                    }
                }
                if (this.f2842b.length > m1678a) {
                    DebugLog.LogI("current record read size is less than buffer size: " + m1678a);
                    sleep((long) this.f2851k);
                }
            }
        } catch (Exception e) {
            DebugLog.LogE(e);
            PcmRecordListener pcmRecordListener = this.f2844d;
            if (pcmRecordListener != null) {
                pcmRecordListener.onError(new SpeechError(20006));
            }
        }
        m1679b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finalize() throws Throwable {
        DebugLog.LogD("[finalize] release recoder");
        m1679b();
        super.finalize();
    }

    /* renamed from: b */
    private void m1679b() {
        synchronized (this) {
            try {
                if (this.f2843c != null) {
                    DebugLog.LogD("release record begin");
                    this.f2843c.release();
                    this.f2843c = null;
                    if (this.f2845e != null) {
                        this.f2845e.onRecordReleased();
                        this.f2845e = null;
                    }
                    DebugLog.LogD("release record over");
                }
            } catch (Exception e) {
                DebugLog.LogE(e.toString());
            }
        }
    }
}
