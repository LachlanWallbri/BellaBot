package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.EvaluatorListener;
import com.iflytek.cloud.EvaluatorResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.msc.util.DataUtil;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.record.C3681a;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.thirdparty.AbstractC3741t;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.iflytek.cloud.thirdparty.b */
/* loaded from: classes3.dex */
public class HandlerC3715b extends AbstractHandlerC3740s implements PcmRecorder.PcmRecordListener {

    /* renamed from: a */
    public static int f3138a;

    /* renamed from: b */
    public static int f3139b;

    /* renamed from: l */
    private static Boolean f3140l = false;

    /* renamed from: c */
    long f3141c;

    /* renamed from: d */
    protected int f3142d;

    /* renamed from: e */
    protected C3688a f3143e;

    /* renamed from: f */
    protected PcmRecorder f3144f;

    /* renamed from: g */
    protected C3742u f3145g;

    /* renamed from: h */
    protected String f3146h;

    /* renamed from: i */
    protected byte[] f3147i;

    /* renamed from: j */
    protected String f3148j;

    /* renamed from: k */
    protected String f3149k;

    /* renamed from: m */
    private volatile EvaluatorListener f3150m;

    /* renamed from: n */
    private ConcurrentLinkedQueue<byte[]> f3151n;

    /* renamed from: o */
    private ConcurrentLinkedQueue<byte[]> f3152o;

    /* renamed from: p */
    private ArrayList<String> f3153p;

    /* renamed from: q */
    private boolean f3154q;

    /* renamed from: r */
    private AbstractC3741t.a f3155r;

    /* renamed from: s */
    private String f3156s;

    /* renamed from: t */
    private boolean f3157t;

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordStarted(boolean z) {
    }

    public HandlerC3715b(Context context, C3692ad c3692ad, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.f3150m = null;
        this.f3141c = 0L;
        this.f3142d = 1;
        this.f3143e = new C3688a();
        this.f3144f = null;
        this.f3145g = new C3742u();
        this.f3146h = null;
        this.f3147i = null;
        this.f3148j = null;
        this.f3149k = null;
        this.f3151n = null;
        this.f3152o = null;
        this.f3153p = null;
        this.f3154q = false;
        this.f3155r = AbstractC3741t.a.noResult;
        this.f3156s = null;
        this.f3157t = false;
        this.f3152o = new ConcurrentLinkedQueue<>();
        this.f3151n = new ConcurrentLinkedQueue<>();
        this.f3153p = new ArrayList<>();
        this.f3154q = false;
        setParams(c3692ad);
    }

    /* renamed from: a */
    public synchronized void m2013a(String str, String str2, EvaluatorListener evaluatorListener) {
        f3140l = false;
        this.f3148j = str;
        this.f3146h = str2;
        this.f3149k = getParam().m1833e(SpeechConstant.ISE_USER_MODEL_ID);
        this.f3150m = evaluatorListener;
        DebugLog.LogD("[ise]startListening called 01");
        start();
    }

    /* renamed from: a */
    public synchronized void m2014a(byte[] bArr, String str, EvaluatorListener evaluatorListener) {
        f3140l = true;
        this.f3147i = bArr;
        this.f3146h = str;
        this.f3149k = getParam().m1833e(SpeechConstant.ISE_USER_MODEL_ID);
        this.f3150m = evaluatorListener;
        DebugLog.LogD("[ise]startListening called 02");
        start();
    }

    /* renamed from: a */
    public synchronized boolean m2015a(boolean z) {
        if (getStatus() != AbstractHandlerC3740s.b.recording) {
            DebugLog.LogD("stopRecognize fail  status is :" + getStatus());
            return false;
        }
        if (this.f3144f != null) {
            this.f3144f.stopRecord(getParam().m1825a("record_force_stop", false));
        }
        this.f3154q = z;
        sendMsg(3);
        return true;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void cancel(boolean z) {
        if (z && isRunning() && this.f3150m != null) {
            this.f3150m.onError(new SpeechError(20017));
        }
        m2010g();
        super.cancel(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
        super.onMsgProcess(message);
        int i = message.what;
        if (i == 0) {
            m2011a();
            return;
        }
        if (i == 1) {
            m2016b();
            return;
        }
        if (i == 2) {
            m2012a(message);
            return;
        }
        if (i == 3) {
            m2007d();
            return;
        }
        if (i == 4) {
            m2017b(message);
        } else {
            if (i == 7 || i != 9) {
                return;
            }
            DebugLog.LogD("--->on timeout vad");
            m2009f();
        }
    }

    /* renamed from: a */
    protected void m2011a() throws Exception {
        DebugLog.LogD("--->onStart: in");
        if (getParam().m1825a(SpeechConstant.NET_CHECK, true)) {
            NetworkUtil.checkNetwork(this.mContext);
        }
        int m1816a = getParam().m1816a("record_read_rate", 40);
        this.f3142d = getParam().m1816a(SpeechConstant.AUDIO_SOURCE, 1);
        if (this.f3142d != -1 && isRunning()) {
            DebugLog.LogD("[ise]start  record");
            if (this.f3142d == -2) {
                this.f3144f = new C3681a(getSampleRate(), m1816a, this.f3142d, getParam().m1833e(SpeechConstant.ISE_SOURCE_PATH));
            } else {
                this.f3157t = getParam().m1825a(SpeechConstant.BLUETOOTH, this.f3157t);
                if (this.f3157t) {
                    startBluetooth();
                }
                this.f3144f = new PcmRecorder(getSampleRate(), m1816a, this.f3142d);
            }
            this.f3144f.startRecording(this);
        }
        if (getStatus() != AbstractHandlerC3740s.b.exiting && this.f3150m != null) {
            this.f3150m.onBeginOfSpeech();
        }
        removeMessages(9);
        if (-1 != this.mSpeechTimeOut) {
            sendMsg(9, AbstractHandlerC3740s.a.normal, false, this.mSpeechTimeOut);
        }
        sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
        DebugLog.LogD("--->onStart: out");
    }

    /* renamed from: b */
    protected void m2016b() throws Exception {
        byte[] bytes;
        if (this.f3143e.mClientID == null) {
            PerfLogger.appendInfo(PerfLogger.SDK_SESSION_BIGNE, null);
            this.f3143e.sessionBegin(this.mContext, this.f3149k, this);
        }
        if (f3140l.booleanValue()) {
            if ("1".equals(getParam().m1833e(SpeechConstant.TEXT_BOM))) {
                bytes = DataUtil.getUTF8Bom(this.f3147i);
            } else {
                bytes = this.f3147i;
            }
        } else if ("1".equals(getParam().m1833e(SpeechConstant.TEXT_BOM))) {
            bytes = DataUtil.getUTF8Bom(this.f3148j);
        } else {
            bytes = this.f3148j.getBytes("gb2312");
        }
        this.f3143e.m1763a(bytes, TextUtils.isEmpty(this.f3146h) ? null : this.f3146h.getBytes("gb2312"));
        setStatus(AbstractHandlerC3740s.b.recording);
        sendMsg(4, AbstractHandlerC3740s.a.normal, false, 20);
    }

    /* renamed from: a */
    protected void m2012a(Message message) throws Exception {
        DebugLog.LogD("proc_Msg_Record_Data");
        byte[] bArr = (byte[]) message.obj;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        if (!TextUtils.isEmpty(getParam().m1833e(SpeechConstant.ISE_AUDIO_PATH))) {
            this.f3151n.add(bArr);
        }
        m2005a(bArr, true);
    }

    /* renamed from: d */
    private void m2007d() throws SpeechError, IOException, InterruptedException {
        DebugLog.LogD("--->onStoped: in");
        if (!isRunning()) {
            m2010g();
        }
        this.f3143e.m1761a();
        updateTimeoutMsg();
        DebugLog.LogD("--->onStoped: out");
    }

    /* renamed from: b */
    void m2017b(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        m2008e();
        if (AbstractC3741t.a.noResult == this.f3155r) {
            sendMsg(4, AbstractHandlerC3740s.a.normal, false, 20);
        } else if (AbstractC3741t.a.hasResult == this.f3155r) {
            sendMsg(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onEnd(SpeechError speechError) {
        DebugLog.LogD("--->onEnd: in");
        m2010g();
        getSessionID();
        PerfLogger.appendInfo(PerfLogger.SESSION_END_BEGIN, null);
        if (this.mUserCancel) {
            this.f3143e.sessionEnd("user abort");
        } else if (speechError != null) {
            this.f3143e.sessionEnd("error" + speechError.getErrorCode());
        } else {
            this.f3143e.sessionEnd("success");
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_END_END, null);
        super.onEnd(speechError);
        if (this.f3150m != null && !this.mUserCancel) {
            DebugLog.LogD("VerifyListener#onEnd");
            if (speechError != null) {
                Bundle bundle = new Bundle();
                bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, getSessionID());
                this.f3150m.onEvent(20001, 0, 0, bundle);
                this.f3150m.onError(speechError);
            }
        }
        this.f3150m = null;
        DebugLog.LogD("--->onEnd: out");
    }

    /* renamed from: e */
    private void m2008e() throws SpeechError, UnsupportedEncodingException {
        AbstractC3741t.a m1767e = this.f3143e.m1767e();
        this.f3155r = m1767e;
        int i = AnonymousClass1.f3158a[m1767e.ordinal()];
        if (i != 1) {
            if (i == 2) {
                m2006c(false);
            } else {
                if (i != 3) {
                    return;
                }
                m2006c(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.cloud.thirdparty.b$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f3158a = new int[AbstractC3741t.a.values().length];

        static {
            try {
                f3158a[AbstractC3741t.a.noResult.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3158a[AbstractC3741t.a.hasResult.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3158a[AbstractC3741t.a.resultOver.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: f */
    private void m2009f() {
        if (AbstractHandlerC3740s.b.recording == getStatus()) {
            DebugLog.LogD("Ise Msc vadEndCall");
            m2015a(false);
            if (this.f3150m != null) {
                this.f3150m.onEndOfSpeech();
            }
        }
    }

    /* renamed from: c */
    private void m2006c(boolean z) throws SpeechError, UnsupportedEncodingException {
        this.mStatusBegin = SystemClock.elapsedRealtime();
        if (this.f3143e.m1766d() != null && this.f3143e.m1766d().length > 0) {
            this.f3153p.add(new String(this.f3143e.m1766d(), "utf-8"));
        }
        m2018b(z);
    }

    /* renamed from: b */
    public void m2018b(boolean z) throws SpeechError, UnsupportedEncodingException {
        DebugLog.LogD("msc result time:" + System.currentTimeMillis());
        String m1827b = getParam().m1827b("rse", "gb2312");
        System.out.println("notifyEngineResult encoding=" + m1827b);
        EvaluatorResult evaluatorResult = new EvaluatorResult(new String(this.f3143e.m1766d(), m1827b));
        if (this.f3150m != null) {
            Bundle bundle = new Bundle();
            bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, getSessionID());
            this.f3150m.onEvent(20001, 0, 0, bundle);
            PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
            this.f3150m.onResult(evaluatorResult, z);
        }
        if (z) {
            exit(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onParseParam() {
        this.mSpeechTimeOut = getParam().m1816a(SpeechConstant.KEY_SPEECH_TIMEOUT, -1);
        DebugLog.LogD("mSpeechTimeOut=" + this.mSpeechTimeOut);
        if ("utf-8".equals(getParam().m1833e(SpeechConstant.TEXT_ENCODING)) && Locale.CHINA.toString().equalsIgnoreCase(getParam().m1833e("language"))) {
            getParam().m1823a(SpeechConstant.TEXT_BOM, "1", false);
        } else {
            getParam().m1823a(SpeechConstant.TEXT_BOM, "0", false);
        }
        super.onParseParam();
    }

    /* renamed from: g */
    private void m2010g() {
        PcmRecorder pcmRecorder = this.f3144f;
        if (pcmRecorder != null) {
            pcmRecorder.stopRecord(getParam().m1825a("record_force_stop", false));
            this.f3144f = null;
            if (this.f3157t) {
                stopBluetooth();
            }
        }
    }

    /* renamed from: a */
    private void m2005a(byte[] bArr, boolean z) throws SpeechError {
        this.f3143e.m1762a(bArr, bArr.length);
        if (z) {
            if (this.f3143e.m1764b() == 3) {
                m2009f();
            } else {
                m2004a(bArr, this.f3143e.m1765c());
            }
        }
    }

    /* renamed from: c */
    public ConcurrentLinkedQueue<byte[]> m2019c() {
        while (true) {
            byte[] poll = this.f3152o.poll();
            if (poll != null) {
                this.f3151n.add(poll);
            } else {
                return this.f3151n;
            }
        }
    }

    /* renamed from: a */
    private void m2004a(byte[] bArr, int i) {
        if (this.f3150m == null || !isRunning()) {
            return;
        }
        this.f3150m.onVolumeChanged(i, bArr);
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (AbstractHandlerC3740s.b.recording != getStatus()) {
            DebugLog.LogE("onRecordBuffer statuts not recording");
        } else if (i2 > 0) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            sendMsg(obtainMessage(2, bArr2));
        }
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onError(SpeechError speechError) {
        exit(speechError);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getClientID() {
        return this.f3143e.getClientID();
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordReleased() {
        PcmRecorder pcmRecorder = this.f3144f;
        if (pcmRecorder == null || !(pcmRecorder instanceof C3681a)) {
            return;
        }
        m2015a(true);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getSessionID() {
        if (TextUtils.isEmpty(this.f3156s)) {
            this.f3156s = this.f3143e.m1768f();
        }
        return this.f3156s;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getTextEncoding() {
        return getParam().m1827b(SpeechConstant.TEXT_ENCODING, "gb2312");
    }
}
