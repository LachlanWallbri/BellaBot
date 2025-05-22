package com.iflytek.cloud.record;

import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.record.PcmRecorder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: com.iflytek.cloud.record.a */
/* loaded from: classes3.dex */
public class C3681a extends PcmRecorder {

    /* renamed from: a */
    private int f2853a;

    /* renamed from: b */
    private int f2854b;

    /* renamed from: c */
    private int f2855c;

    /* renamed from: d */
    private boolean f2856d;

    /* renamed from: e */
    private int f2857e;

    /* renamed from: f */
    private final short f2858f;

    /* renamed from: g */
    private int f2859g;

    /* renamed from: h */
    private int f2860h;

    /* renamed from: i */
    private byte[] f2861i;

    /* renamed from: j */
    private RandomAccessFile f2862j;

    /* renamed from: k */
    private String f2863k;

    /* renamed from: l */
    private PcmRecorder.PcmRecordListener f2864l;

    public C3681a(int i, int i2, int i3, String str) {
        super(i, i2, i3);
        this.f2853a = 0;
        this.f2854b = 0;
        this.f2855c = 0;
        this.f2856d = false;
        this.f2857e = 16000;
        this.f2858f = (short) 16;
        this.f2859g = 40;
        this.f2860h = 40;
        this.f2861i = null;
        this.f2862j = null;
        this.f2863k = null;
        this.f2864l = null;
        this.f2857e = i;
        this.f2859g = i2;
        this.f2860h = i2;
        this.f2863k = str;
    }

    @Override // com.iflytek.cloud.record.PcmRecorder
    /* renamed from: a */
    protected void mo1680a(short s, int i, int i2) throws SpeechError {
        this.f2855c = ((((i * 40) / 1000) * s) * 16) / 8;
        this.f2861i = new byte[this.f2855c * 10];
        try {
            this.f2862j = new RandomAccessFile(this.f2863k, "r");
        } catch (FileNotFoundException unused) {
            throw new SpeechError(20006);
        }
    }

    /* renamed from: a */
    private int m1681a() throws SpeechError {
        RandomAccessFile randomAccessFile = this.f2862j;
        int i = 0;
        if (randomAccessFile != null && this.f2864l != null) {
            if (this.f2854b >= this.f2853a) {
                try {
                    this.f2854b = 0;
                    this.f2853a = randomAccessFile.read(this.f2861i, this.f2854b, this.f2861i.length);
                    if (this.f2853a < 0) {
                        return -1;
                    }
                } catch (IOException unused) {
                    throw new SpeechError(20006);
                }
            }
            int i2 = this.f2853a;
            if (i2 > 0 && this.f2864l != null) {
                int i3 = this.f2854b;
                int i4 = i2 - i3;
                int i5 = this.f2855c;
                i = i4 > i5 ? i5 : i2 - i3;
                this.f2864l.onRecordBuffer(this.f2861i, this.f2854b, i);
                this.f2854b += i;
            }
        }
        return i;
    }

    @Override // com.iflytek.cloud.record.PcmRecorder
    public void stopRecord(boolean z) {
        this.f2856d = true;
    }

    @Override // com.iflytek.cloud.record.PcmRecorder
    public void startRecording(PcmRecorder.PcmRecordListener pcmRecordListener) throws SpeechError {
        this.f2864l = pcmRecordListener;
        setPriority(10);
        start();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001b, code lost:
    
        r3.f2856d = true;
     */
    @Override // com.iflytek.cloud.record.PcmRecorder, java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        try {
            mo1680a((short) 1, this.f2857e, this.f2859g);
            if (this.f2864l != null) {
                this.f2864l.onRecordStarted(true);
            }
            while (true) {
                if (this.f2856d) {
                    break;
                } else if (m1681a() < 0) {
                    break;
                } else {
                    sleep(this.f2860h);
                }
            }
        } catch (Exception e) {
            DebugLog.LogE(e);
            PcmRecorder.PcmRecordListener pcmRecordListener = this.f2864l;
            if (pcmRecordListener != null) {
                pcmRecordListener.onError(new SpeechError(20006));
            }
        }
        m1682b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.record.PcmRecorder
    public void finalize() throws Throwable {
        m1682b();
        super.finalize();
    }

    /* renamed from: b */
    private void m1682b() {
        if (this.f2862j != null) {
            DebugLog.LogD("release record begin");
            try {
                this.f2862j.close();
            } catch (IOException e) {
                DebugLog.LogE(e);
            }
            this.f2862j = null;
            PcmRecorder.PcmRecordListener pcmRecordListener = this.f2864l;
            if (pcmRecordListener != null) {
                pcmRecordListener.onRecordReleased();
                this.f2864l = null;
            }
            DebugLog.LogD("release record over");
        }
        if (this.f2861i != null) {
            this.f2861i = null;
        }
    }
}
