package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.VerifierListener;
import com.iflytek.cloud.VerifierResult;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.record.C3681a;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.thirdparty.AbstractC3741t;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.iflytek.cloud.thirdparty.g */
/* loaded from: classes3.dex */
public class HandlerC3728g extends AbstractHandlerC3740s implements PcmRecorder.PcmRecordListener {

    /* renamed from: a */
    protected volatile VerifierListener f3222a;

    /* renamed from: b */
    protected long f3223b;

    /* renamed from: c */
    protected boolean f3224c;

    /* renamed from: d */
    protected C3729h f3225d;

    /* renamed from: e */
    protected PcmRecorder f3226e;

    /* renamed from: f */
    protected String f3227f;

    /* renamed from: g */
    protected String f3228g;

    /* renamed from: h */
    protected VerifierResult f3229h;

    /* renamed from: i */
    protected ConcurrentLinkedQueue<byte[]> f3230i;

    /* renamed from: j */
    protected int f3231j;

    /* renamed from: k */
    private long f3232k;

    /* renamed from: l */
    private int f3233l;

    /* renamed from: m */
    private String f3234m;

    /* renamed from: n */
    private boolean f3235n;

    public HandlerC3728g(Context context, C3692ad c3692ad, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.f3222a = null;
        this.f3223b = 0L;
        this.f3224c = true;
        this.f3225d = new C3729h();
        this.f3226e = null;
        this.f3227f = "train";
        this.f3228g = "";
        this.f3229h = null;
        this.f3230i = null;
        this.f3231j = 1;
        this.f3232k = 0L;
        this.f3233l = 0;
        this.f3234m = null;
        this.f3235n = false;
        this.f3230i = new ConcurrentLinkedQueue<>();
        setParams(c3692ad);
    }

    /* renamed from: a */
    public synchronized void m2087a(VerifierListener verifierListener) {
        DebugLog.LogD("Isv Msc startVerify in");
        this.f3222a = verifierListener;
        start();
        DebugLog.LogD("Isv Msc startVerify out");
    }

    /* renamed from: a */
    public synchronized boolean m2090a() {
        DebugLog.LogD("Isv Msc stopRecord in");
        if (getStatus() != AbstractHandlerC3740s.b.recording) {
            DebugLog.LogD("endVerify fail  status is :" + getStatus());
            return false;
        }
        if (!m2081g()) {
            m2085k();
        }
        sendMsg(3);
        DebugLog.LogD("Isv Msc stopRecord out");
        return true;
    }

    /* renamed from: g */
    private boolean m2081g() {
        return "train".equalsIgnoreCase(getParam().m1833e("sst"));
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void cancel(boolean z) {
        if (z && isRunning() && this.f3222a != null) {
            this.f3222a.onError(new SpeechError(20017));
        }
        m2085k();
        super.cancel(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
        super.onMsgProcess(message);
        int i = message.what;
        if (i == 0) {
            m2091b();
            return;
        }
        if (i == 1) {
            m2093c();
            return;
        }
        if (i == 2) {
            m2086a(message);
            return;
        }
        if (i == 3) {
            m2082h();
            return;
        }
        if (i == 4) {
            m2092b(message);
        } else {
            if (i == 7 || i != 9) {
                return;
            }
            m2094d();
        }
    }

    /* renamed from: b */
    protected void m2091b() throws Exception {
        DebugLog.LogD("isv msc msg start in");
        String m1833e = getParam().m1833e("engine_type");
        boolean m1825a = getParam().m1825a(SpeechConstant.NET_CHECK, true);
        if ("cloud".equals(m1833e) && m1825a) {
            NetworkUtil.checkNetwork(this.mContext);
        }
        int m1816a = getParam().m1816a("record_read_rate", 40);
        if (this.f3231j != -1 && isRunning()) {
            DebugLog.LogD("[isv]start  record");
            if (this.f3226e == null) {
                this.f3235n = getParam().m1825a(SpeechConstant.BLUETOOTH, this.f3235n);
                if (this.f3235n) {
                    startBluetooth();
                }
                this.f3226e = new PcmRecorder(getSampleRate(), m1816a, this.f3231j);
                this.f3226e.startRecording(this);
            }
        }
        if (getStatus() != AbstractHandlerC3740s.b.exiting && this.f3222a != null) {
            this.f3222a.onBeginOfSpeech();
        }
        this.f3223b = SystemClock.elapsedRealtime();
        removeMessages(9);
        sendMsg(9, AbstractHandlerC3740s.a.normal, false, this.mSpeechTimeOut);
        sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
        DebugLog.LogD("isv msc msg start out");
    }

    /* renamed from: c */
    protected void m2093c() throws Exception {
        if (this.f3225d.mClientID == null) {
            PerfLogger.appendInfo(PerfLogger.SDK_SESSION_BIGNE, null);
            this.f3225d.sessionBegin(this.mContext, this.f3228g, this);
        }
        setStatus(AbstractHandlerC3740s.b.recording);
    }

    /* renamed from: a */
    protected void m2086a(Message message) throws Exception {
        byte[] bArr = (byte[]) message.obj;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        this.f3230i.add(bArr);
        m2089a(bArr, true);
    }

    /* renamed from: h */
    private void m2082h() throws SpeechError, IOException, InterruptedException {
        DebugLog.LogD("record stop msg in");
        if (!m2081g()) {
            m2085k();
        }
        this.f3225d.m2100a();
        sendMsg(4);
        DebugLog.LogD("record stop msg out");
    }

    /* renamed from: b */
    void m2092b(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        if (!m2081g()) {
            m2085k();
        }
        m2083i();
        if (getStatus() == AbstractHandlerC3740s.b.waitresult) {
            sendMsg(4, AbstractHandlerC3740s.a.normal, false, 20);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onEnd(SpeechError speechError) {
        DebugLog.LogD("isv msc onEnd in");
        m2085k();
        getSessionID();
        PerfLogger.appendInfo(PerfLogger.SESSION_END_BEGIN, null);
        if (this.mUserCancel) {
            this.f3225d.sessionEnd("user abort");
        } else if (speechError != null) {
            this.f3225d.sessionEnd("error" + speechError.getErrorCode());
        } else {
            this.f3225d.sessionEnd("success");
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_END_END, null);
        super.onEnd(speechError);
        if (this.f3222a != null && !this.mUserCancel) {
            DebugLog.LogD("VerifyListener#onEnd");
            if (speechError != null) {
                Bundle bundle = new Bundle();
                bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, getSessionID());
                this.f3222a.onEvent(20001, 0, 0, bundle);
                this.f3222a.onError(speechError);
            }
        }
        this.f3222a = null;
        DebugLog.LogD("isv msc onEnd out");
    }

    /* renamed from: i */
    private void m2083i() throws SpeechError, UnsupportedEncodingException {
        int i = AnonymousClass1.f3236a[this.f3225d.m2105e().ordinal()];
        if (i == 1 || i != 2) {
            return;
        }
        m2084j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.cloud.thirdparty.g$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f3236a = new int[AbstractC3741t.a.values().length];

        static {
            try {
                f3236a[AbstractC3741t.a.noResult.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3236a[AbstractC3741t.a.hasResult.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* renamed from: d */
    public void m2094d() {
        if (AbstractHandlerC3740s.b.recording == getStatus()) {
            DebugLog.LogD("Isv Msc vadEndCall");
            m2090a();
            if (this.f3222a != null) {
                this.f3222a.onEndOfSpeech();
            }
        }
    }

    /* renamed from: j */
    private void m2084j() throws SpeechError, UnsupportedEncodingException {
        this.mStatusBegin = SystemClock.elapsedRealtime();
        this.f3229h = new VerifierResult(new String(this.f3225d.m2104d(), "utf-8"));
        if (this.f3222a != null) {
            Bundle bundle = new Bundle();
            bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, getSessionID());
            this.f3222a.onEvent(20001, 0, 0, bundle);
        }
        if (this.f3227f.equals("train") && this.f3229h.ret == 0 && this.f3229h.suc < this.f3229h.rgn) {
            if (this.f3222a != null) {
                PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
                this.f3222a.onResult(this.f3229h);
            }
            sendMsg(0);
            return;
        }
        if (this.f3222a != null) {
            PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
            this.f3222a.onResult(this.f3229h);
        }
        exit(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onParseParam() {
        this.mSpeechTimeOut = getParam().m1816a(SpeechConstant.KEY_SPEECH_TIMEOUT, this.mSpeechTimeOut);
        this.f3228g = getParam().m1833e(SpeechConstant.ISV_VID);
        this.f3231j = getParam().m1816a(SpeechConstant.AUDIO_SOURCE, 1);
        this.f3233l = (((getParam().m1816a("sample_rate", this.mSampleRate) / 1000) * 16) / 8) * getParam().m1816a(SpeechConstant.FILTER_AUDIO_TIME, 0);
        DebugLog.LogD("mSpeechTimeOut=" + this.mSpeechTimeOut);
        super.onParseParam();
    }

    /* renamed from: k */
    private void m2085k() {
        PcmRecorder pcmRecorder = this.f3226e;
        if (pcmRecorder != null) {
            pcmRecorder.stopRecord(getParam().m1825a("record_force_stop", false));
            this.f3226e = null;
            if (this.f3235n) {
                stopBluetooth();
            }
        }
    }

    /* renamed from: a */
    public void m2088a(byte[] bArr, int i) {
        if (isRunning()) {
            this.f3222a.onVolumeChanged(i, bArr);
        }
    }

    /* renamed from: a */
    protected void m2089a(byte[] bArr, boolean z) throws SpeechError {
        this.f3225d.m2101a(bArr, bArr.length);
        if (z) {
            if (this.f3225d.m2102b()) {
                m2094d();
            } else {
                m2088a(bArr, this.f3225d.m2103c());
            }
        }
    }

    /* renamed from: e */
    public ConcurrentLinkedQueue<byte[]> m2095e() {
        return this.f3230i;
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (AbstractHandlerC3740s.b.recording == getStatus() && i2 > 0) {
            int i3 = this.f3233l;
            if (i3 <= 0) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, i, bArr2, 0, i2);
                sendMsg(obtainMessage(2, bArr2));
            } else {
                if (i3 >= i2) {
                    this.f3233l = i3 - i2;
                    return;
                }
                byte[] bArr3 = new byte[i2 - i3];
                System.arraycopy(bArr, i + i3, bArr3, 0, i2 - i3);
                sendMsg(obtainMessage(2, bArr3));
                this.f3233l = 0;
            }
        }
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onError(SpeechError speechError) {
        exit(speechError);
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordStarted(boolean z) {
        DebugLog.LogD("time cost: onRecordStarted:" + (SystemClock.elapsedRealtime() - this.f3232k));
    }

    /* renamed from: f */
    public int m2096f() {
        return this.f3231j;
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordReleased() {
        PcmRecorder pcmRecorder = this.f3226e;
        if (pcmRecorder == null || !(pcmRecorder instanceof C3681a)) {
            return;
        }
        m2090a();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getClientID() {
        return this.f3225d.getClientID();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getSessionID() {
        if (TextUtils.isEmpty(this.f3234m)) {
            this.f3234m = this.f3225d.m2106f();
        }
        return this.f3234m;
    }
}
