package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.record.C3681a;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import com.iflytek.msc.MSC;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.iflytek.cloud.thirdparty.d */
/* loaded from: classes3.dex */
public class HandlerC3725d extends AbstractHandlerC3740s implements PcmRecorder.PcmRecordListener {

    /* renamed from: j */
    public static int f3193j;

    /* renamed from: k */
    public static int f3194k;

    /* renamed from: a */
    protected volatile RecognizerListener f3195a;

    /* renamed from: b */
    protected boolean f3196b;

    /* renamed from: c */
    protected boolean f3197c;

    /* renamed from: d */
    protected boolean f3198d;

    /* renamed from: e */
    protected boolean f3199e;

    /* renamed from: f */
    protected int f3200f;

    /* renamed from: g */
    protected boolean f3201g;

    /* renamed from: h */
    protected C3724c f3202h;

    /* renamed from: i */
    protected PcmRecorder f3203i;

    /* renamed from: l */
    protected String f3204l;

    /* renamed from: m */
    protected ConcurrentLinkedQueue<byte[]> f3205m;

    /* renamed from: n */
    protected ArrayList<String> f3206n;

    /* renamed from: o */
    protected C3742u f3207o;

    /* renamed from: p */
    protected int f3208p;

    /* renamed from: q */
    private boolean f3209q;

    /* renamed from: r */
    private String f3210r;

    /* renamed from: s */
    private boolean f3211s;

    /* renamed from: t */
    private int f3212t;

    public HandlerC3725d(Context context, C3692ad c3692ad, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.f3195a = null;
        this.f3196b = false;
        this.f3197c = false;
        this.f3198d = false;
        this.f3199e = false;
        this.f3200f = 1;
        this.f3201g = true;
        this.f3202h = new C3724c();
        this.f3203i = null;
        this.f3204l = null;
        this.f3205m = null;
        this.f3206n = null;
        this.f3207o = new C3742u();
        this.f3208p = 0;
        this.f3209q = false;
        this.f3210r = null;
        this.f3211s = false;
        this.f3212t = 0;
        this.f3205m = new ConcurrentLinkedQueue<>();
        this.f3206n = new ArrayList<>();
        this.f3199e = false;
        setParams(c3692ad);
    }

    /* renamed from: a */
    public int m2061a() {
        return this.f3200f;
    }

    /* renamed from: b */
    public ConcurrentLinkedQueue<byte[]> m2068b() {
        return this.f3205m;
    }

    /* renamed from: a */
    public void m2065a(byte[] bArr, int i) {
        if (this.f3195a == null || !isRunning()) {
            return;
        }
        this.f3195a.onVolumeChanged(i, bArr);
        if (this.f3209q) {
            Bundle bundle = new Bundle();
            bundle.putByteArray("data", bArr);
            this.f3195a.onEvent(21003, i, 0, bundle);
        }
    }

    /* renamed from: a */
    public synchronized void m2064a(RecognizerListener recognizerListener) {
        this.f3195a = recognizerListener;
        DebugLog.LogD("[isr]startListening called");
        start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void start() {
        this.f3207o.m2255a(getParam());
        super.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
        super.onMsgProcess(message);
        int i = message.what;
        if (i == 0) {
            mo2070c();
            return;
        }
        if (i == 1) {
            m2071d();
            return;
        }
        if (i == 2) {
            mo2063a(message);
            return;
        }
        if (i == 3) {
            mo2072e();
            return;
        }
        if (i == 4) {
            m2069b(message);
        } else if (i == 7) {
            m2073f();
        } else {
            if (i != 9) {
                return;
            }
            m2074g();
        }
    }

    /* renamed from: c */
    protected void mo2070c() throws Exception {
        DebugLog.LogD("[isr]start connecting");
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
        if (this.f3200f != -1 && isRunning()) {
            DebugLog.LogD("[isr]start  record");
            if (this.f3200f == -2) {
                this.f3203i = new C3681a(getSampleRate(), m1816a, this.f3200f, getParam().m1833e(SpeechConstant.ASR_SOURCE_PATH));
            } else {
                this.f3211s = getParam().m1825a(SpeechConstant.BLUETOOTH, this.f3211s);
                if (this.f3211s) {
                    startBluetooth();
                }
                this.f3203i = new PcmRecorder(getSampleRate(), m1816a, this.f3200f);
                if (hasMessages(3)) {
                    throw new SpeechError(10118);
                }
            }
            this.f3207o.m2256a("rec_open");
            this.f3203i.startRecording(this);
            if (-1 != this.mSpeechTimeOut) {
                sendMsg(9, AbstractHandlerC3740s.a.normal, false, this.mSpeechTimeOut);
            }
        }
        if (this.f3195a != null && this.f3200f > -1) {
            this.f3195a.onBeginOfSpeech();
        }
        this.f3207o.m2256a("app_ssb");
        sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public void m2071d() throws Exception {
        PerfLogger.appendInfo(PerfLogger.SDK_SESSION_BIGNE, null);
        int sessionBegin = this.f3202h.sessionBegin(this.mContext, this.f3204l, this);
        if (sessionBegin == 0 && this.f3202h.mClientID != null) {
            if (isRunning()) {
                MSC.QISRRegisterNotify(this.f3202h.mClientID, "rsltCb", "stusCb", "errCb", this);
                setStatus(AbstractHandlerC3740s.b.recording);
                if (getParam().m1825a(SpeechConstant.ASR_NET_PERF, false)) {
                    sendMsg(7, AbstractHandlerC3740s.a.max, false, 0);
                    return;
                }
                return;
            }
            return;
        }
        this.f3212t++;
        if (this.f3212t > 40) {
            throw new SpeechError(sessionBegin);
        }
        if (isRunning()) {
            Thread.sleep(15L);
            sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
        }
    }

    /* renamed from: a */
    protected void mo2063a(Message message) throws Exception {
        byte[] bArr = (byte[]) message.obj;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        this.f3205m.add(bArr);
        mo2066a(bArr, true);
    }

    /* renamed from: a */
    public synchronized boolean m2067a(boolean z) {
        DebugLog.LogD("stopRecognize, current status is :" + getStatus() + " usercancel : " + z);
        this.f3207o.m2256a("app_stop");
        m2075h();
        this.f3199e = z;
        sendMsg(3);
        return true;
    }

    /* renamed from: e */
    protected void mo2072e() throws SpeechError, IOException, InterruptedException {
        DebugLog.LogD("recording stop");
        m2075h();
        this.f3207o.m2256a("app_lau");
        this.f3202h.m2053a();
        updateTimeoutMsg();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void cancel(boolean z) {
        if (z && isRunning() && this.f3195a != null) {
            this.f3195a.onError(new SpeechError(20017));
        }
        m2075h();
        if (getStatus() == AbstractHandlerC3740s.b.recording) {
            this.f3199e = true;
        }
        super.cancel(z);
    }

    /* renamed from: f */
    public void m2073f() {
        if (isRunning()) {
            int m2050a = this.f3202h.m2050a(AbstractHandlerC3740s.TAG_NETPERF);
            if (this.f3195a != null) {
                this.f3195a.onEvent(10001, m2050a, 0, null);
            }
            sendMsg(7, AbstractHandlerC3740s.a.normal, false, 100);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onEnd(SpeechError speechError) {
        DebugLog.LogD("onSessionEnd");
        m2075h();
        f3193j = this.f3202h.m2050a(AbstractHandlerC3740s.TAG_UPFLOW);
        f3194k = this.f3202h.m2050a(AbstractHandlerC3740s.TAG_DOWNFLOW);
        getSessionID();
        if (this.f3206n.size() <= 0 && speechError == null && getParam().m1825a(SpeechConstant.ASR_NOMATCH_ERROR, true)) {
            speechError = new SpeechError(10118);
        }
        if (speechError != null) {
            this.f3207o.m2257a("app_ret", speechError.getErrorCode(), false);
        } else {
            this.f3207o.m2257a("app_ret", 0L, false);
        }
        this.f3207o.m2258a("rec_ustop", this.f3199e ? "1" : "0", false);
        this.f3202h.m2055a("sessinfo", this.f3207o.m2254a());
        PerfLogger.appendInfo(PerfLogger.SESSION_END_BEGIN, null);
        if (this.mUserCancel) {
            this.f3202h.sessionEnd("user abort");
        } else if (speechError != null) {
            this.f3202h.sessionEnd("error" + speechError.getErrorCode());
        } else {
            this.f3202h.sessionEnd("success");
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_END_END, null);
        super.onEnd(speechError);
        if (this.f3195a != null) {
            if (this.mUserCancel) {
                DebugLog.LogD("RecognizerListener#onCancel");
            } else {
                DebugLog.LogD("RecognizerListener#onEnd");
                if (speechError != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, getSessionID());
                    this.f3195a.onEvent(20001, 0, 0, bundle);
                    this.f3195a.onError(speechError);
                }
            }
        }
        this.f3195a = null;
    }

    /* renamed from: g */
    public void m2074g() {
        if (AbstractHandlerC3740s.b.recording == getStatus()) {
            DebugLog.LogD("isr recognize vadEndCall");
            if (this.f3195a != null) {
                this.f3195a.onEndOfSpeech();
            }
            m2067a(false);
        }
    }

    /* renamed from: a */
    private void m2060a(boolean z, byte[] bArr) throws SpeechError, UnsupportedEncodingException {
        this.mStatusBegin = SystemClock.elapsedRealtime();
        String str = "";
        if (bArr != null && bArr.length > 0) {
            str = new String(bArr, "utf-8");
        } else if (this.f3206n.size() <= 0) {
            String m1833e = getParam().m1833e(SpeechConstant.LOCAL_GRAMMAR);
            if (!TextUtils.isEmpty(m1833e) && !"sms.irf".equals(m1833e)) {
                throw new SpeechError(20005);
            }
            if (getParam().m1825a(SpeechConstant.ASR_NOMATCH_ERROR, true)) {
                throw new SpeechError(10118);
            }
        }
        this.f3206n.add(str);
        if (this.f3195a != null && isRunning()) {
            Bundle bundle = new Bundle();
            bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, getSessionID());
            this.f3195a.onEvent(20001, 0, 0, bundle);
            if (z && getParam().m1825a("request_audio_url", false)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("audio_url", this.f3202h.m2059d());
                this.f3195a.onEvent(23001, 0, 0, bundle2);
            }
            RecognizerResult recognizerResult = new RecognizerResult(str);
            PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
            this.f3195a.onResult(recognizerResult, z);
        }
        DebugLog.LogD("msc result time:" + System.currentTimeMillis());
        if (z) {
            exit(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onParseParam() {
        this.f3204l = getParam().m1833e(SpeechConstant.CLOUD_GRAMMAR);
        this.f3200f = getParam().m1816a(SpeechConstant.AUDIO_SOURCE, 1);
        this.f3201g = C3694af.m1842a(getParam().m1833e("domain"));
        this.f3208p = (((getParam().m1816a("sample_rate", this.mSampleRate) / 1000) * 16) / 8) * getParam().m1816a(SpeechConstant.FILTER_AUDIO_TIME, 0);
        this.mSpeechTimeOut = getParam().m1816a(SpeechConstant.KEY_SPEECH_TIMEOUT, this.mSpeechTimeOut);
        this.f3209q = getParam().m1825a(SpeechConstant.NOTIFY_RECORD_DATA, false);
        DebugLog.LogD("mSpeechTimeOut=" + this.mSpeechTimeOut);
        super.onParseParam();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public boolean isLongInput() {
        return this.f3201g;
    }

    void stusCb(char[] cArr, int i, int i2, int i3, byte[] bArr) {
        if (i == 0 && i2 == 3) {
            DebugLog.LogD("MscRecognizer", "stusCb:" + i2 + ",type:" + i);
            m2074g();
        }
    }

    void rsltCb(char[] cArr, byte[] bArr, int i, int i2) {
        if (bArr != null) {
            DebugLog.LogD("MscRecognizer", "rsltCb:" + i2 + "result:" + new String(bArr));
        } else {
            DebugLog.LogI("MscRecognizer", "rsltCb:" + i2 + "result:null");
        }
        Message obtainMessage = obtainMessage(4, i2, 0, bArr);
        if (hasMessages(4)) {
            sendMsg(obtainMessage, AbstractHandlerC3740s.a.normal, false, 0);
        } else {
            sendMsg(obtainMessage, AbstractHandlerC3740s.a.max, false, 0);
        }
    }

    void errCb(char[] cArr, int i, byte[] bArr) {
        onError(new SpeechError(i));
    }

    /* renamed from: h */
    protected void m2075h() {
        PcmRecorder pcmRecorder = this.f3203i;
        if (pcmRecorder != null) {
            pcmRecorder.stopRecord(getParam().m1825a("record_force_stop", false));
            this.f3203i = null;
            this.f3207o.m2256a("rec_close");
            if (this.f3195a != null) {
                this.f3195a.onEvent(22003, 0, 0, null);
            }
            if (this.f3211s) {
                stopBluetooth();
            }
        }
    }

    /* renamed from: b */
    void m2069b(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        int i = message.arg1;
        byte[] bArr = (byte[]) message.obj;
        if (i == 0) {
            if (!this.f3198d) {
                this.f3198d = true;
                this.f3207o.m2256a("app_frs");
            }
            m2060a(false, bArr);
            return;
        }
        if (i == 2 || i != 5) {
            return;
        }
        if (!this.f3198d) {
            this.f3198d = true;
            this.f3207o.m2256a("app_frs");
        }
        this.f3207o.m2256a("app_lrs");
        m2060a(true, bArr);
    }

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
        if (z) {
            int m2056b = this.f3202h.m2056b();
            DebugLog.LogI("QISRAudioWrite volume:" + m2056b);
            m2065a(bArr, m2056b);
        }
    }

    /* renamed from: a */
    public int m2062a(byte[] bArr, int i, int i2) {
        onRecordBuffer(bArr, i, i2);
        return 0;
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (bArr == null || i2 <= 0 || bArr.length < i2 || i2 <= 0 || !isRunning()) {
            return;
        }
        if (!this.f3196b) {
            this.f3196b = true;
            this.f3207o.m2256a("rec_start");
        }
        int i3 = this.f3208p;
        if (i3 <= 0) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            sendMsg(obtainMessage(2, bArr2));
        } else {
            if (i3 >= i2) {
                this.f3208p = i3 - i2;
                return;
            }
            byte[] bArr3 = new byte[i2 - i3];
            System.arraycopy(bArr, i + i3, bArr3, 0, i2 - i3);
            sendMsg(obtainMessage(2, bArr3));
            this.f3208p = 0;
        }
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onError(SpeechError speechError) {
        exit(speechError);
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordStarted(boolean z) {
        this.f3207o.m2256a("rec_ready");
    }

    /* renamed from: i */
    public C3742u m2076i() {
        return this.f3207o;
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordReleased() {
        PcmRecorder pcmRecorder = this.f3203i;
        if (pcmRecorder == null || !(pcmRecorder instanceof C3681a)) {
            return;
        }
        m2067a(true);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getSessionID() {
        if (TextUtils.isEmpty(this.f3210r)) {
            this.f3210r = this.f3202h.m2058c();
        }
        return this.f3210r;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getClientID() {
        return this.f3202h.getClientID();
    }
}
