package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.record.C3681a;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import com.iflytek.cloud.util.AudioDetector;
import java.io.IOException;

/* renamed from: com.iflytek.cloud.thirdparty.e */
/* loaded from: classes3.dex */
public class HandlerC3726e extends HandlerC3725d {

    /* renamed from: q */
    boolean f3213q;

    /* renamed from: r */
    private int f3214r;

    /* renamed from: s */
    private AudioDetector f3215s;

    /* renamed from: t */
    private byte[] f3216t;

    public HandlerC3726e(Context context, C3692ad c3692ad, HandlerThread handlerThread) {
        super(context, c3692ad, handlerThread);
        this.f3214r = 16000;
        this.f3215s = null;
        this.f3213q = false;
        this.f3216t = null;
        C3692ad param = getParam();
        this.f3214r = param != null ? param.m1816a("sample_rate", 16000) : 16000;
        param.m1823a("vad_enable", "0", true);
        this.f3215s = AudioDetector.getDetector();
        if (this.f3215s == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("sample_rate");
            stringBuffer.append("=");
            stringBuffer.append(this.f3214r);
            String m1827b = param.m1827b(AudioDetector.VAD_ENGINE, AudioDetector.TYPE_META);
            stringBuffer.append(",");
            stringBuffer.append(AudioDetector.VAD_ENGINE);
            stringBuffer.append("=");
            stringBuffer.append(m1827b);
            String m1833e = param != null ? param.m1833e(AudioDetector.RES_PATH) : null;
            if (!TextUtils.isEmpty(m1833e)) {
                stringBuffer.append(",");
                stringBuffer.append(AudioDetector.RES_PATH);
                stringBuffer.append("=");
                stringBuffer.append(m1833e);
            }
            this.f3215s = AudioDetector.createDetector(context, stringBuffer.toString());
        }
        this.f3215s.setParameter("vad_bos", Integer.toString(this.mSpeechTimeOut > 0 ? this.mSpeechTimeOut : Integer.MAX_VALUE));
        String m1827b2 = param.m1827b("vad_eos", Integer.toString(C3694af.m1843b(this)));
        DebugLog.LogD("meta vad eos on recog: " + m1827b2);
        this.f3215s.setParameter("vad_eos", m1827b2);
        this.f3215s.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT, Integer.toString(this.mSpeechTimeOut));
    }

    @Override // com.iflytek.cloud.thirdparty.HandlerC3725d
    /* renamed from: c */
    protected void mo2070c() throws Exception {
        DebugLog.LogD("start connecting");
        String m1833e = getParam().m1833e("engine_type");
        if (getParam().m1825a(SpeechConstant.NET_CHECK, true)) {
            if ("cloud".equals(m1833e)) {
                NetworkUtil.checkNetwork(this.mContext);
            } else if ("mixed".equals(m1833e) || "mixed".equals(m1833e)) {
                try {
                    NetworkUtil.checkNetwork(this.mContext);
                } catch (Exception unused) {
                    getParam().m1822a("engine_type", "local");
                }
            }
        }
        int m1816a = getParam().m1816a("record_read_rate", 40);
        setStatus(AbstractHandlerC3740s.b.recording);
        int i = ((this.f3214r * 300) * 2) / 1000;
        DebugLog.LogD("MscRecognizerMeta last buffer len: " + i);
        this.f3216t = new byte[i];
        if (this.f3200f != -1 && isRunning()) {
            DebugLog.LogD("start  record");
            if (this.f3200f == -2) {
                this.f3203i = new C3681a(getSampleRate(), m1816a, this.f3200f, getParam().m1833e(SpeechConstant.ASR_SOURCE_PATH));
            } else {
                this.f3203i = new PcmRecorder(getSampleRate(), m1816a, this.f3200f);
                if (hasMessages(3)) {
                    throw new SpeechError(10118);
                }
            }
            this.f3207o.m2256a("rec_open");
            this.f3203i.startRecording(this);
            this.mSpeechTimeOut = getParam().m1816a(SpeechConstant.KEY_SPEECH_TIMEOUT, -1);
            if (-1 != this.mSpeechTimeOut) {
                sendMsg(9, AbstractHandlerC3740s.a.normal, false, this.mSpeechTimeOut);
            }
        }
        if (this.f3195a != null && this.f3200f > -1) {
            this.f3195a.onBeginOfSpeech();
        }
        AudioDetector audioDetector = this.f3215s;
        if (audioDetector == null) {
            throw new SpeechError(21003);
        }
        audioDetector.reset();
    }

    @Override // com.iflytek.cloud.thirdparty.HandlerC3725d
    /* renamed from: e */
    protected void mo2072e() throws SpeechError, IOException, InterruptedException {
        if (this.f3213q) {
            this.f3207o.m2256a("app_lau");
            this.f3202h.m2053a();
            updateTimeoutMsg();
        } else {
            DebugLog.LogD("exit with no speech audio");
            exit(null);
        }
    }

    /* renamed from: j */
    private void m2078j() throws Exception {
        if (SpeechUtility.getUtility() != null) {
            this.f3207o.m2256a("app_ssb");
            DebugLog.LogD("begin session");
            m2071d();
        } else {
            DebugLog.LogE("not init while begin session");
            exit(new SpeechError(20015));
        }
    }

    @Override // com.iflytek.cloud.thirdparty.HandlerC3725d
    /* renamed from: a */
    protected void mo2066a(byte[] bArr, boolean z) throws SpeechError {
        if (!this.f3197c) {
            this.f3197c = true;
            this.f3207o.m2256a("app_fau");
            if (this.f3195a != null) {
                this.f3195a.onEvent(22002, 0, 0, null);
            }
        }
        this.f3202h.m2054a(bArr, bArr.length);
    }

    @Override // com.iflytek.cloud.thirdparty.HandlerC3725d
    /* renamed from: a */
    protected void mo2063a(Message message) throws Exception {
        byte[] m2077a;
        byte[] bArr = (byte[]) message.obj;
        if (bArr == null || bArr.length == 0 || (m2077a = m2077a(bArr)) == null) {
            return;
        }
        this.f3205m.add(m2077a);
        mo2066a(m2077a, true);
    }

    /* renamed from: a */
    private byte[] m2077a(byte[] bArr) throws Exception {
        if (this.f3215s != null) {
            int min = Math.min(32768, bArr.length);
            byte[] bArr2 = null;
            AudioDetector.DetectorResult detectorResult = null;
            int i = 0;
            int i2 = 0;
            boolean z = false;
            while (min > 0) {
                detectorResult = this.f3215s.detect(bArr, i, min, false);
                if (detectorResult.error != 0) {
                    throw new SpeechError(detectorResult.error);
                }
                if (3 == detectorResult.status) {
                    this.f3215s.reset();
                } else if (detectorResult.status == 0) {
                    continue;
                } else {
                    if (!this.f3213q) {
                        i2 = Math.max(i2, i - this.f3216t.length);
                        this.f3213q = true;
                        DebugLog.LogD("detectAudioData find start and begin session");
                        m2078j();
                        z = true;
                    }
                    if (1 != detectorResult.status) {
                        break;
                    }
                }
                i += min;
                min = Math.min(32768, bArr.length - i);
            }
            if (detectorResult != null) {
                m2065a(bArr, detectorResult.volume);
            }
            if (detectorResult != null && this.f3213q) {
                if (z) {
                    int length = bArr.length - i2;
                    byte[] bArr3 = this.f3216t;
                    byte[] bArr4 = new byte[length + bArr3.length];
                    if (bArr3.length <= i2) {
                        System.arraycopy(bArr, i2 - bArr3.length, bArr4, 0, bArr4.length);
                    } else {
                        System.arraycopy(bArr3, i2, bArr4, 0, bArr3.length - i2);
                        System.arraycopy(bArr, 0, bArr4, this.f3216t.length - i2, bArr.length);
                    }
                    bArr2 = bArr4;
                } else {
                    bArr2 = bArr;
                }
                if (2 == detectorResult.status || 4 == detectorResult.status) {
                    DebugLog.LogD("detectAudioData find eos or timeout");
                    m2074g();
                }
            } else {
                int min2 = Math.min(this.f3216t.length, bArr.length);
                byte[] bArr5 = this.f3216t;
                System.arraycopy(bArr5, min2, bArr5, 0, bArr5.length - min2);
                int length2 = bArr.length - min2;
                byte[] bArr6 = this.f3216t;
                System.arraycopy(bArr, length2, bArr6, bArr6.length - min2, min2);
            }
            return bArr2;
        }
        throw new SpeechError(22001);
    }
}
