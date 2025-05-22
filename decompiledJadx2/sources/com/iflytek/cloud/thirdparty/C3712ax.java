package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.msc.util.DataUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.util.AudioDetector;
import com.iflytek.msc.MetaVAD;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.iflytek.cloud.thirdparty.ax */
/* loaded from: classes3.dex */
public class C3712ax extends AudioDetector {

    /* renamed from: c */
    private static final Map<String, String> f3105c = new HashMap();

    /* renamed from: d */
    private static final Map<String, String> f3106d = new HashMap();

    /* renamed from: e */
    private C3692ad f3107e;

    /* renamed from: f */
    private final AudioDetector.DetectorResult f3108f;

    /* renamed from: g */
    private MetaVAD.Instance f3109g;

    /* renamed from: h */
    private String f3110h;

    /* renamed from: i */
    private boolean f3111i;

    /* renamed from: j */
    private boolean f3112j;

    /* renamed from: k */
    private int f3113k;

    /* renamed from: l */
    private int f3114l;

    /* renamed from: m */
    private int f3115m;

    /* renamed from: n */
    private long f3116n;

    /* renamed from: o */
    private long f3117o;

    static {
        f3105c.put("vad_bos", "vad_starttimeout");
        f3105c.put("vad_eos", "vad_endtimeout");
        f3105c.put("threshold", "vad_threshold");
        f3106d.put("vad_bos", String.valueOf(2000));
        f3106d.put("vad_eos", String.valueOf(700));
        f3106d.put("threshold", String.valueOf(0.6f));
    }

    public C3712ax(Context context, String str) {
        super(context, str);
        this.f3107e = new C3692ad();
        this.f3108f = new AudioDetector.DetectorResult();
        this.f3109g = new MetaVAD.Instance();
        this.f3110h = "gb2312";
        this.f3111i = false;
        this.f3112j = true;
        this.f3113k = 0;
        this.f3114l = 0;
        this.f3115m = 2;
        this.f3116n = -1L;
        this.f3117o = 0L;
        DebugLog.LogD("Meta VAD AudioDetector constructor enter, context: " + context + ", param: " + str);
        this.f3107e.m1821a(str);
        try {
            this.f3110h = this.f3107e.m1827b(SpeechConstant.TEXT_ENCODING, this.f3110h);
            String m1833e = this.f3107e.m1833e("extra");
            byte[] nativeByteArray = m1833e != null ? DataUtil.getNativeByteArray(m1833e, this.f3110h) : null;
            DebugLog.LogD("MetaVAD.VADInitialize begin.");
            int VADInitialize = MetaVAD.VADInitialize(nativeByteArray);
            if (VADInitialize == 0) {
                this.f3109g.rate = this.f3107e.m1816a("sample_rate", 16000);
                String m1833e2 = this.f3107e.m1833e(AudioDetector.RES_PATH);
                byte[] nativeByteArray2 = m1833e2 != null ? DataUtil.getNativeByteArray(m1833e2, this.f3110h) : null;
                DebugLog.LogD("MetaVAD.VADLoadResource begin.");
                VADInitialize = MetaVAD.VADLoadResource(this.f3109g.rate, nativeByteArray2);
                if (VADInitialize == 0) {
                    DebugLog.LogD("MetaVAD.VADCreateSession begin.");
                    VADInitialize = MetaVAD.VADCreateSession(this.f3109g);
                }
            }
            if (VADInitialize != 0) {
                DebugLog.LogE("MetaVAD Native error " + VADInitialize);
            }
        } catch (Throwable th) {
            DebugLog.LogE("Meta VAD AudioDetector constructor exception:");
            DebugLog.LogE(th);
        }
        DebugLog.LogD("Meta VAD AudioDetector constructor leave");
    }

    @Override // com.iflytek.cloud.util.AudioDetector
    public boolean destroy() {
        boolean z;
        int i;
        DebugLog.LogD("destroy enter");
        synchronized (f3487b) {
            z = false;
            try {
                boolean z2 = true;
                if (this.f3109g != null) {
                    if (0 != this.f3109g.handle) {
                        DebugLog.LogD("destroy MetaVAD.VADDestroySession begin");
                        i = MetaVAD.VADDestroySession(this.f3109g);
                        DebugLog.LogD("destroy MetaVAD.VADDestroySession ret=" + i);
                    } else {
                        i = 0;
                    }
                    if (i == 0) {
                        this.f3109g.handle = 0L;
                        DebugLog.LogD("destroy MetaVAD.VADDelResource begin");
                        DebugLog.LogD("destroy MetaVAD.VADDelResource ret=" + MetaVAD.VADDelResource(this.f3109g.rate));
                        DebugLog.LogD("destroy MetaVAD.VADUninitialize begin");
                        i = MetaVAD.VADUninitialize();
                        DebugLog.LogD("destroy MetaVAD.VADUninitialize ret=" + i);
                    }
                    if (i != 0) {
                        z2 = false;
                    }
                    if (z2) {
                        this.f3109g = null;
                        f3486a = null;
                    }
                }
                z = z2;
            } finally {
                DebugLog.LogD("destroy leave: " + z);
                return z;
            }
        }
        DebugLog.LogD("destroy leave: " + z);
        return z;
    }

    @Override // com.iflytek.cloud.util.AudioDetector
    public void reset() {
        DebugLog.LogD("reset enter");
        synchronized (f3487b) {
            if (this.f3109g != null && 0 != this.f3109g.handle) {
                try {
                    DebugLog.LogD("reset MetaVAD.VADResetSession begin");
                    DebugLog.LogD("reset MetaVAD.VADResetSession return " + MetaVAD.VADResetSession(this.f3109g));
                    this.f3109g.m2346a();
                    this.f3112j = true;
                    this.f3111i = false;
                    this.f3117o = 0L;
                    this.f3114l = 0;
                } catch (Throwable th) {
                    DebugLog.LogE("reset exception:");
                    DebugLog.LogE(th);
                }
            } else {
                DebugLog.LogE("setParameter error: vad instance is null, or invalid handle.");
            }
        }
        DebugLog.LogD("reset leave");
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0068, code lost:
    
        r5.f3108f.error = 20012;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0080  */
    @Override // com.iflytek.cloud.util.AudioDetector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public AudioDetector.DetectorResult detect(byte[] bArr, int i, int i2, boolean z) {
        DebugLog.LogI("detect enter, buffer: " + bArr + ", offset: " + i + ", length: " + i2 + ", isLast: " + z);
        synchronized (f3487b) {
            try {
                m1990a();
            } catch (UnsatisfiedLinkError e) {
                DebugLog.LogE("detect exception");
                DebugLog.LogE(e);
                m1990a();
                this.f3108f.error = 20021;
            } catch (Throwable th) {
                DebugLog.LogE("detect exception");
                DebugLog.LogE(th);
                m1990a();
                this.f3108f.error = 20999;
            }
            if (this.f3109g != null && 0 != this.f3109g.handle) {
                if (bArr != null && i2 > 0 && 32768 >= i2 && i >= 0 && bArr.length - i >= i2) {
                    DebugLog.LogI("detect buffer length: " + i2);
                    if (this.f3108f.error == 0) {
                        int VADAppendPCM = MetaVAD.VADAppendPCM(this.f3109g, bArr, i, i2, z ? 1 : 0);
                        DebugLog.LogI("MetaVAD VADAppendPCM ret: " + VADAppendPCM);
                        if (this.f3111i) {
                            this.f3117o += i2;
                        }
                        m1991a(VADAppendPCM);
                        if (this.f3108f.error == 0) {
                            int i3 = 5;
                            while (5 == i3) {
                                i3 = MetaVAD.VADGetSeg(this.f3109g);
                                DebugLog.LogI("MetaVAD VADGetSeg ret: " + i3 + ", seg status: " + this.f3109g.seg + ", seg begin: " + this.f3109g.begin + ", seg end: " + this.f3109g.end);
                                m1991a(i3);
                                if (this.f3108f.error == 0) {
                                    m1992b();
                                    this.f3108f.buffer = bArr;
                                    this.f3108f.length = i2;
                                    this.f3108f.offset = i;
                                }
                                if (3 == this.f3109g.seg || this.f3108f.error != 0) {
                                    DebugLog.LogD("detect get last seg or error.");
                                    break;
                                }
                            }
                        }
                    }
                }
                if (this.f3108f.error == 0) {
                }
            }
            DebugLog.LogE("detect error: vad instance null, or handle is invalid!");
            this.f3108f.error = 21003;
            if (this.f3108f.error == 0) {
            }
        }
        DebugLog.LogI("detect leave");
        return this.f3108f;
    }

    @Override // com.iflytek.cloud.util.AudioDetector
    public void setParameter(String str, String str2) {
        long j;
        DebugLog.LogD("setParameter enter, key: " + str + ", value: " + str2);
        synchronized (f3487b) {
            if (this.f3109g != null && 0 != this.f3109g.handle) {
                try {
                    if (AudioDetector.RESET_SENTENCE.equalsIgnoreCase(str)) {
                        DebugLog.LogD("VAD VADResetSentence ret: " + MetaVAD.VADResetSentence(this.f3109g));
                    } else if (!TextUtils.isEmpty(str) && f3105c.containsKey(str)) {
                        if (!TextUtils.isEmpty(str2)) {
                            this.f3107e.m1822a(str, str2);
                        } else {
                            this.f3107e.m1831d(str);
                        }
                        String m1827b = this.f3107e.m1827b(str, f3106d.get(str));
                        String str3 = f3105c.get(str);
                        DebugLog.LogD("VAD SetParameter key=" + str3 + ", value=" + m1827b + ", ret: " + MetaVAD.VADSetParam(this.f3109g, DataUtil.getNativeByteArray(str3, this.f3110h), DataUtil.getNativeByteArray(m1827b, this.f3110h)));
                    } else if (!TextUtils.isEmpty(str)) {
                        if (SpeechConstant.KEY_SPEECH_TIMEOUT.equalsIgnoreCase(str)) {
                            try {
                                j = Long.parseLong(str2);
                            } catch (NumberFormatException e) {
                                DebugLog.LogE(e);
                                j = -1;
                            }
                            DebugLog.LogD("SetParameter speech timeout value:" + j);
                            if (0 < j) {
                                this.f3116n = ((this.f3109g.rate * this.f3115m) * j) / 1000;
                                DebugLog.LogD("SetParameter BytesOfSpeechTimeout: " + this.f3116n);
                            } else {
                                this.f3116n = -1L;
                            }
                        } else {
                            DebugLog.LogD("VAD SetParameter name=" + str + ", value=" + str2 + ", ret: " + MetaVAD.VADSetParam(this.f3109g, DataUtil.getNativeByteArray(str, this.f3110h), DataUtil.getNativeByteArray(str2, this.f3110h)));
                        }
                    }
                } catch (Throwable th) {
                    DebugLog.LogE("setParameter exception");
                    DebugLog.LogE(th);
                }
            } else {
                DebugLog.LogE("setParameter error: vad instance is null, or invalid handle.");
            }
        }
        DebugLog.LogD("setParameter leave.");
    }

    /* renamed from: a */
    private void m1990a() {
        AudioDetector.DetectorResult detectorResult = this.f3108f;
        detectorResult.buffer = null;
        detectorResult.end = 0;
        detectorResult.error = 0;
        detectorResult.length = 0;
        detectorResult.offset = 0;
        detectorResult.quality = 0;
        detectorResult.start = 0;
        detectorResult.status = 0;
        detectorResult.sub = 0;
        detectorResult.subs.clear();
        AudioDetector.DetectorResult detectorResult2 = this.f3108f;
        detectorResult2.voice = false;
        detectorResult2.volume = 0;
        detectorResult2.confidence = 1.0f;
        MetaVAD.Instance instance = this.f3109g;
        if (instance != null) {
            instance.m2346a();
        }
        this.f3113k = 0;
    }

    /* renamed from: b */
    private void m1992b() {
        if (this.f3109g.seg != 0) {
            Integer put = this.f3108f.subs.put(Integer.valueOf(this.f3109g.begin), Integer.valueOf(this.f3109g.end));
            if (put != null) {
                DebugLog.LogE("update result error: repeat sub begin: " + put);
                int i = this.f3113k + 1;
                this.f3113k = i;
                if (10 <= i) {
                    this.f3108f.error = 10100;
                    DebugLog.LogE("update result error: repeat sub reach max count.");
                }
            }
            this.f3108f.sub = 3;
            if (1 == this.f3109g.seg || (this.f3112j && 3 == this.f3109g.seg)) {
                this.f3108f.start = this.f3109g.begin;
                this.f3114l = this.f3108f.start;
            }
            if (3 == this.f3109g.seg) {
                this.f3108f.end = this.f3109g.end;
                AudioDetector.DetectorResult detectorResult = this.f3108f;
                detectorResult.start = this.f3114l;
                detectorResult.confidence = MetaVAD.VADGetSentConfidence(this.f3109g);
            }
            this.f3112j = false;
        }
        AudioDetector.DetectorResult detectorResult2 = this.f3108f;
        detectorResult2.quality = 0;
        detectorResult2.voice = false;
        detectorResult2.volume = this.f3109g.volume * 4;
    }

    /* renamed from: a */
    private void m1991a(int i) {
        switch (i) {
            case 0:
            case 6:
                this.f3108f.error = 0;
                this.f3109g.seg = 0;
                break;
            case 1:
            case 2:
                this.f3108f.sub = 1;
                break;
            case 3:
                this.f3108f.sub = 2;
                break;
            case 4:
                this.f3108f.status = this.f3111i ? 2 : 3;
                break;
            case 5:
                this.f3108f.sub = 3;
                break;
            default:
                this.f3108f.error = i;
                break;
        }
        if (!this.f3111i && this.f3108f.sub != 0) {
            this.f3111i = true;
            if (this.f3108f.status == 0) {
                this.f3108f.status = 1;
            }
        }
        if (this.f3108f.status == 0 && m1993c()) {
            this.f3108f.status = 4;
        }
    }

    /* renamed from: c */
    private boolean m1993c() {
        long j = this.f3116n;
        return 0 < j && j <= this.f3117o;
    }
}
