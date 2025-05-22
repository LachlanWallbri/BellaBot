package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.util.AudioDetector;
import com.iflytek.msc.VAD;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.iflytek.cloud.thirdparty.aw */
/* loaded from: classes3.dex */
public class C3711aw extends AudioDetector {

    /* renamed from: c */
    private static final Map<String, Integer> f3093c = new HashMap();

    /* renamed from: d */
    private static final Map<String, Integer> f3094d = new HashMap();

    /* renamed from: e */
    private C3692ad f3095e;

    /* renamed from: f */
    private long f3096f;

    /* renamed from: g */
    private AudioDetector.DetectorResult f3097g;

    /* renamed from: h */
    private VAD.VadData f3098h;

    /* renamed from: i */
    private byte[] f3099i;

    /* renamed from: j */
    private byte[] f3100j;

    /* renamed from: k */
    private boolean f3101k;

    /* renamed from: l */
    private int f3102l;

    /* renamed from: m */
    private long f3103m;

    /* renamed from: n */
    private long f3104n;

    static {
        f3093c.put("vad_bos", 0);
        f3093c.put("vad_eos", 1);
        f3093c.put(AudioDetector.SUB_TIMEOUT, 3);
        f3093c.put(AudioDetector.EARLY_START, 4);
        f3094d.put("vad_bos", 2000);
        f3094d.put("vad_eos", 700);
        f3094d.put(AudioDetector.SUB_TIMEOUT, 20000);
        f3094d.put(AudioDetector.EARLY_START, 1);
    }

    public C3711aw(Context context, String str) {
        super(context, str);
        this.f3095e = new C3692ad();
        this.f3096f = 0L;
        this.f3097g = new AudioDetector.DetectorResult();
        this.f3098h = new VAD.VadData();
        this.f3099i = new byte[32768];
        this.f3100j = new byte[32784];
        this.f3101k = true;
        this.f3102l = 2;
        this.f3103m = -1L;
        this.f3104n = 0L;
        DebugLog.LogD("AudioDetector constructor enter, context: " + context + ", param: " + str);
        this.f3095e.m1821a(str);
        try {
            this.f3096f = VAD.Initialize(this.f3095e.m1816a("sample_rate", 16000));
            DebugLog.LogD("VAD Initialize ret: " + this.f3096f);
        } catch (Throwable th) {
            DebugLog.LogE("AudioDetector constructor exception");
            DebugLog.LogE(th);
        }
        this.f3098h.wavData = this.f3100j;
    }

    @Override // com.iflytek.cloud.util.AudioDetector
    public boolean destroy() {
        boolean z;
        DebugLog.LogD("destroy enter");
        synchronized (f3487b) {
            if (0 != this.f3096f) {
                try {
                    VAD.Uninitialize(this.f3096f);
                    DebugLog.LogD("VAD Uninitialize");
                    this.f3096f = 0L;
                } catch (Throwable th) {
                    DebugLog.LogE("destroy exception");
                    DebugLog.LogE(th);
                    z = false;
                }
            }
            z = true;
        }
        f3486a = null;
        DebugLog.LogD("destroy leave");
        return z;
    }

    @Override // com.iflytek.cloud.util.AudioDetector
    public void reset() {
        DebugLog.LogD("reset enter");
        synchronized (f3487b) {
            if (0 != this.f3096f) {
                try {
                    VAD.Reset(this.f3096f);
                    DebugLog.LogD("VAD Reset");
                    this.f3101k = true;
                    this.f3104n = 0L;
                } catch (Throwable th) {
                    DebugLog.LogE("reset exception");
                    DebugLog.LogE(th);
                }
            }
        }
        DebugLog.LogD("reset leave");
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x014e, code lost:
    
        r6 = com.iflytek.msc.VAD.EndAudioData(r5.f3096f);
        com.iflytek.cloud.msc.util.log.DebugLog.LogD("VAD EndAudioData ret: " + r6);
        m1987a(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x016f, code lost:
    
        if (r5.f3097g.error != 0) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0171, code lost:
    
        r5.f3097g.error = com.iflytek.msc.VAD.GetLastSpeechPos(r5.f3096f, r5.f3098h);
        com.iflytek.cloud.msc.util.log.DebugLog.LogD("VAD GetLastSpeechPos ret: " + r5.f3097g.error);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0199, code lost:
    
        if (r5.f3097g.error != 0) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x019b, code lost:
    
        m1988b();
     */
    @Override // com.iflytek.cloud.util.AudioDetector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public AudioDetector.DetectorResult detect(byte[] bArr, int i, int i2, boolean z) {
        synchronized (f3487b) {
            DebugLog.LogI("detect enter, buffer: " + bArr + ", offset: " + i + ", length: " + i2 + ", isLast: " + z);
            try {
                try {
                    m1986a();
                    if (0 == this.f3096f) {
                        DebugLog.LogE("detect error: handle is invalid!");
                        this.f3097g.error = 21003;
                    } else {
                        if (bArr != null && i2 > 0 && 32768 >= i2 && i >= 0 && bArr.length - i >= i2) {
                            System.arraycopy(bArr, i, this.f3099i, 0, i2);
                            DebugLog.LogI("buffer length: " + i2);
                            int CalcVolumLevel = VAD.CalcVolumLevel(this.f3096f, this.f3099i, i2, this.f3098h);
                            DebugLog.LogI("VAD CalcVolumLevel ret: " + CalcVolumLevel);
                            this.f3097g.error = CalcVolumLevel;
                            if (this.f3097g.error == 0) {
                                int AppendData = VAD.AppendData(this.f3096f, this.f3099i, i2);
                                DebugLog.LogI("VAD AppendData ret: " + AppendData);
                                if (!this.f3101k) {
                                    this.f3104n += i2;
                                }
                                m1987a(AppendData);
                                if (this.f3097g.error == 0) {
                                    int FetchData = VAD.FetchData(this.f3096f, this.f3098h);
                                    DebugLog.LogI("VAD FetchData ret: " + FetchData);
                                    m1987a(FetchData);
                                    if (this.f3097g.error == 0) {
                                        if (2 == this.f3097g.status || 3 == this.f3097g.status || z) {
                                            int EndAudioData = VAD.EndAudioData(this.f3096f);
                                            DebugLog.LogD("VAD EndAudioData ret: " + EndAudioData);
                                            m1987a(EndAudioData);
                                            if (this.f3097g.error == 0) {
                                                int GetLastSpeechPos = VAD.GetLastSpeechPos(this.f3096f, this.f3098h);
                                                DebugLog.LogD("VAD GetLastSpeechPos ret: " + GetLastSpeechPos);
                                                this.f3097g.error = GetLastSpeechPos;
                                            }
                                        }
                                        if (this.f3097g.error == 0) {
                                            m1988b();
                                        }
                                    }
                                }
                            }
                        }
                        this.f3097g.error = 20012;
                    }
                } catch (UnsatisfiedLinkError e) {
                    DebugLog.LogE("detect exception");
                    DebugLog.LogE(e);
                    m1986a();
                    this.f3097g.error = 20021;
                }
            } catch (Throwable th) {
                DebugLog.LogE("detect exception");
                DebugLog.LogE(th);
                m1986a();
                this.f3097g.error = 20999;
            }
        }
        DebugLog.LogD("detect leave");
        return this.f3097g;
    }

    @Override // com.iflytek.cloud.util.AudioDetector
    public void setParameter(String str, String str2) {
        long j;
        DebugLog.LogD("setParameter enter, key: " + str + ", value: " + str2);
        synchronized (f3487b) {
            try {
                if (!TextUtils.isEmpty(str) && f3093c.containsKey(str)) {
                    if (!TextUtils.isEmpty(str2)) {
                        this.f3095e.m1822a(str, str2);
                    } else {
                        this.f3095e.m1831d(str);
                    }
                    int m1816a = this.f3095e.m1816a(str, f3094d.get(str).intValue());
                    int intValue = f3093c.get(str).intValue();
                    DebugLog.LogD("VAD SetParameter key=" + intValue + ", value=" + m1816a + ", ret: " + VAD.SetParam(this.f3096f, intValue, m1816a));
                } else if (SpeechConstant.KEY_SPEECH_TIMEOUT.equalsIgnoreCase(str)) {
                    try {
                        j = Long.parseLong(str2);
                    } catch (NumberFormatException unused) {
                        j = -1;
                    }
                    DebugLog.LogD("SetParameter speech timeout value:" + j);
                    if (0 < j) {
                        this.f3103m = ((this.f3095e.m1816a("sample_rate", 16000) * this.f3102l) * j) / 1000;
                        DebugLog.LogD("SetParameter BytesOfSpeechTimeout: " + this.f3103m);
                    } else {
                        this.f3103m = -1L;
                    }
                } else {
                    int parseInt = Integer.parseInt(str);
                    int parseInt2 = Integer.parseInt(str2);
                    DebugLog.LogD("VAD SetParameter key=" + parseInt + ", value=" + parseInt2 + ", ret: " + VAD.SetParam(this.f3096f, parseInt, parseInt2));
                }
            } finally {
                DebugLog.LogD("setParameter leave.");
            }
        }
        DebugLog.LogD("setParameter leave.");
    }

    /* renamed from: a */
    private void m1986a() {
        AudioDetector.DetectorResult detectorResult = this.f3097g;
        detectorResult.buffer = null;
        detectorResult.end = 0;
        detectorResult.error = 0;
        detectorResult.length = 0;
        detectorResult.offset = 0;
        detectorResult.quality = 0;
        detectorResult.start = 0;
        detectorResult.status = 0;
        detectorResult.sub = 0;
        detectorResult.voice = false;
        detectorResult.volume = 0;
        VAD.VadData vadData = this.f3098h;
        vadData.audioQuality = 0;
        vadData.endByte = 0;
        vadData.endRemainFrameNum = 0;
        vadData.firstOutByte = 0;
        vadData.inSpeech = 0;
        vadData.startByte = 0;
        vadData.startRemainFrameNum = 0;
        vadData.status = 0;
        vadData.volumeLevel = 0;
        vadData.waitPauseOrEnd = 0;
        vadData.waitStart = 0;
        vadData.wavData = this.f3100j;
        vadData.wavDataSize = 0;
    }

    /* renamed from: b */
    private void m1988b() {
        this.f3097g.buffer = this.f3098h.wavData;
        this.f3097g.end = this.f3098h.endByte;
        this.f3097g.length = this.f3098h.wavDataSize;
        AudioDetector.DetectorResult detectorResult = this.f3097g;
        detectorResult.offset = 0;
        detectorResult.quality = this.f3098h.audioQuality;
        this.f3097g.start = this.f3098h.startByte;
        this.f3097g.voice = 1 == this.f3098h.inSpeech;
        this.f3097g.volume = this.f3098h.volumeLevel;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0044  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m1987a(int i) {
        if (i != 0) {
            switch (i) {
                case 5:
                    this.f3097g.sub = 1;
                    break;
                case 6:
                    this.f3097g.sub = 2;
                    break;
                case 7:
                    this.f3097g.sub = 3;
                    break;
                case 8:
                    this.f3097g.status = 2;
                    break;
                case 9:
                    AudioDetector.DetectorResult detectorResult = this.f3097g;
                    detectorResult.sub = 3;
                    detectorResult.status = 2;
                    break;
                case 10:
                    this.f3097g.status = 3;
                    break;
                case 11:
                    break;
                default:
                    this.f3097g.error = i;
                    break;
            }
            if (this.f3101k && this.f3097g.sub != 0) {
                this.f3101k = false;
                if (this.f3097g.status == 0) {
                    this.f3097g.status = 1;
                }
            }
            if (this.f3097g.status == 0 || !m1989c()) {
            }
            this.f3097g.status = 4;
            return;
        }
        this.f3097g.error = 0;
        if (this.f3101k) {
            this.f3101k = false;
            if (this.f3097g.status == 0) {
            }
        }
        if (this.f3097g.status == 0) {
        }
    }

    /* renamed from: c */
    private boolean m1989c() {
        long j = this.f3103m;
        return 0 < j && j <= this.f3104n;
    }
}
