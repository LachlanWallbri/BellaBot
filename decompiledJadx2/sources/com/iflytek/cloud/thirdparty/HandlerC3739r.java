package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.IdentityListener;
import com.iflytek.cloud.IdentityResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.msc.util.DataLogger;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import com.iflytek.msc.MSC;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.iflytek.cloud.thirdparty.r */
/* loaded from: classes3.dex */
public class HandlerC3739r extends AbstractHandlerC3740s {

    /* renamed from: a */
    protected volatile IdentityListener f3397a;

    /* renamed from: b */
    protected boolean f3398b;

    /* renamed from: c */
    protected boolean f3399c;

    /* renamed from: d */
    protected boolean f3400d;

    /* renamed from: e */
    protected C3738q f3401e;

    /* renamed from: h */
    protected String f3402h;

    /* renamed from: i */
    protected C3742u f3403i;

    /* renamed from: j */
    long f3404j;

    /* renamed from: l */
    private boolean f3405l;

    /* renamed from: m */
    private DataLogger f3406m;

    /* renamed from: n */
    private HashMap<String, C3692ad> f3407n;

    /* renamed from: o */
    private HashMap<String, StringBuffer> f3408o;

    /* renamed from: p */
    private HashMap<String, Boolean> f3409p;

    /* renamed from: q */
    private String f3410q;

    /* renamed from: r */
    private int f3411r;

    /* renamed from: k */
    private static final String f3396k = HandlerC3739r.class.getSimpleName();

    /* renamed from: f */
    public static int f3394f = 0;

    /* renamed from: g */
    public static int f3395g = 0;

    public HandlerC3739r(Context context, C3692ad c3692ad, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.f3405l = true;
        this.f3397a = null;
        this.f3398b = false;
        this.f3399c = false;
        this.f3400d = false;
        this.f3401e = new C3738q();
        this.f3402h = null;
        this.f3403i = new C3742u();
        this.f3407n = null;
        this.f3408o = null;
        this.f3410q = null;
        this.f3411r = 0;
        this.f3404j = 0L;
        this.f3406m = new DataLogger();
        this.f3400d = false;
        this.f3407n = new HashMap<>();
        this.f3408o = new HashMap<>();
        this.f3409p = new HashMap<>();
        setParams(c3692ad);
    }

    /* renamed from: a */
    public DataLogger m2241a() {
        return this.f3406m;
    }

    /* renamed from: a */
    public void m2246a(byte[] bArr, int i) {
        if (this.f3397a == null || !isRunning()) {
            return;
        }
        this.f3397a.onEvent(SpeechEvent.EVENT_VOLUME, i, 0, null);
    }

    /* renamed from: a */
    public synchronized void m2243a(IdentityListener identityListener) {
        this.f3397a = identityListener;
        DebugLog.LogD("startWorking called");
        start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void start() {
        this.f3403i.m2255a(getParam());
        super.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
        super.onMsgProcess(message);
        int i = message.what;
        if (i == 0) {
            m2248b();
            return;
        }
        if (i == 1) {
            m2250c();
            return;
        }
        if (i == 2) {
            m2242a(message);
            return;
        }
        if (i == 3) {
            m2240c(message);
            return;
        }
        if (i == 4) {
            m2249b(message);
        } else if (i == 7) {
            m2251d();
        } else {
            if (i != 9) {
                return;
            }
            m2252e();
        }
    }

    /* renamed from: b */
    protected void m2248b() throws Exception {
        DebugLog.LogD("[mfv]start connecting");
        sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
    }

    /* renamed from: c */
    protected void m2250c() throws Exception {
        if (getParam().m1825a(SpeechConstant.NET_CHECK, true)) {
            NetworkUtil.checkNetwork(this.mContext);
        }
        PerfLogger.appendInfo(PerfLogger.SDK_SESSION_BIGNE, null);
        this.f3403i.m2256a("app_ssb");
        int sessionBegin = this.f3401e.sessionBegin(this.mContext, null, this);
        if (sessionBegin == 0 && this.f3401e.mClientID != null) {
            if (isRunning()) {
                MSC.QMFVRegisterNotify(this.f3401e.mClientID, "rsltCb", "stusCb", "errCb", this);
                setStatus(AbstractHandlerC3740s.b.recording);
                if (getParam().m1825a(SpeechConstant.ASR_NET_PERF, false)) {
                    sendMsg(7, AbstractHandlerC3740s.a.max, false, 0);
                    return;
                }
                return;
            }
            return;
        }
        this.f3411r++;
        if (this.f3411r > 40) {
            throw new SpeechError(sessionBegin);
        }
        if (isRunning()) {
            Thread.sleep(15L);
            sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
        }
    }

    /* renamed from: a */
    protected void m2242a(Message message) throws Exception {
        HashMap hashMap = (HashMap) message.obj;
        byte[] bArr = (byte[]) hashMap.get("data");
        String str = (String) hashMap.get("ssub");
        C3692ad c3692ad = this.f3407n.get(str);
        StringBuffer stringBuffer = this.f3408o.get(str);
        String m1833e = c3692ad.m1833e(SpeechConstant.MFV_DATA_PATH);
        if (!TextUtils.isEmpty(m1833e) && bArr != null) {
            this.f3406m.addData(m1833e, bArr);
        }
        Boolean bool = this.f3409p.get(str);
        if (bool == null) {
            bool = true;
        }
        if (SpeechConstant.ENG_IVP.equals(str) && this.f3405l) {
            m2245a(stringBuffer, bArr, bool.booleanValue(), true);
        } else {
            m2245a(stringBuffer, bArr, bool.booleanValue(), false);
        }
        if (bool.booleanValue()) {
            this.f3409p.put(str, false);
        }
    }

    /* renamed from: a */
    public synchronized boolean m2247a(String str, boolean z) {
        DebugLog.LogD("stopRecognize, current status is :" + getStatus() + " usercancel : " + z);
        this.f3403i.m2256a("app_stop");
        this.f3400d = z;
        sendMsg(obtainMessage(3, str));
        return true;
    }

    /* renamed from: c */
    private void m2240c(Message message) throws SpeechError, IOException, InterruptedException {
        DebugLog.LogD("recording stop");
        this.f3403i.m2256a("app_lau");
        this.f3409p.put((String) message.obj, true);
        this.f3401e.m2233a((String) message.obj);
        updateTimeoutMsg();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void cancel(boolean z) {
        if (z && isRunning() && this.f3397a != null) {
            DebugLog.LogD("cancel");
            this.f3397a.onError(new SpeechError(20017));
        }
        if (getStatus() == AbstractHandlerC3740s.b.recording) {
            this.f3400d = true;
        }
        super.cancel(z);
    }

    /* renamed from: d */
    public void m2251d() {
        if (isRunning()) {
            int m2236b = this.f3401e.m2236b(AbstractHandlerC3740s.TAG_NETPERF);
            if (this.f3397a != null) {
                this.f3397a.onEvent(10001, m2236b, 0, null);
            }
            sendMsg(7, AbstractHandlerC3740s.a.normal, false, 100);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onEnd(SpeechError speechError) {
        DebugLog.LogD("onSessionEnd");
        f3394f = this.f3401e.m2236b(AbstractHandlerC3740s.TAG_UPFLOW);
        f3395g = this.f3401e.m2236b(AbstractHandlerC3740s.TAG_DOWNFLOW);
        getSessionID();
        if (this.f3402h == null && speechError == null && getParam().m1825a(SpeechConstant.ASR_NOMATCH_ERROR, true)) {
            speechError = new SpeechError(10118);
        }
        if (speechError != null) {
            this.f3403i.m2257a("app_ret", speechError.getErrorCode(), false);
        } else {
            this.f3403i.m2257a("app_ret", 0L, false);
        }
        this.f3403i.m2258a("rec_ustop", this.f3400d ? "1" : "0", false);
        this.f3401e.m2235a("sessinfo", this.f3403i.m2254a());
        PerfLogger.appendInfo(PerfLogger.SESSION_END_BEGIN, null);
        if (this.mUserCancel) {
            this.f3401e.sessionEnd("user abort");
        } else if (speechError != null) {
            this.f3401e.sessionEnd("error" + speechError.getErrorCode());
        } else {
            this.f3401e.sessionEnd("success");
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_END_END, null);
        super.onEnd(speechError);
        if (this.f3397a != null) {
            if (!this.mUserCancel && speechError != null) {
                this.f3397a.onError(speechError);
            }
            this.f3397a.onEvent(SpeechEvent.EVENT_SESSION_END, 0, 0, null);
        }
        this.f3397a = null;
    }

    /* renamed from: e */
    public void m2252e() {
        if (AbstractHandlerC3740s.b.recording == getStatus()) {
            DebugLog.LogD("mfv msc vadEndCall");
            if (this.f3397a != null) {
                this.f3397a.onEvent(SpeechEvent.EVENT_VAD_EOS, 0, 0, null);
            }
            m2247a(SpeechConstant.ENG_IVP, false);
        }
    }

    /* renamed from: a */
    private void m2239a(boolean z, byte[] bArr) throws SpeechError, UnsupportedEncodingException {
        this.mStatusBegin = SystemClock.elapsedRealtime();
        if (bArr != null && bArr.length > 0) {
            String replace = new String(bArr, "utf-8").replace("\"return\"", "\"ret\"");
            this.f3402h = replace;
            if (this.f3397a != null && isRunning()) {
                Bundle bundle = new Bundle();
                bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, getSessionID());
                this.f3397a.onEvent(20001, 0, 0, bundle);
                IdentityResult identityResult = new IdentityResult(replace);
                PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
                this.f3397a.onResult(identityResult, z);
            }
            DebugLog.LogD("msc result time:" + System.currentTimeMillis());
            if (z) {
                exit(null);
                return;
            }
            return;
        }
        throw new SpeechError(10118);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onParseParam() {
        this.mSpeechTimeOut = getParam().m1816a(SpeechConstant.KEY_SPEECH_TIMEOUT, this.mSpeechTimeOut);
        DebugLog.LogD("mSpeechTimeOut=" + this.mSpeechTimeOut);
        super.onParseParam();
    }

    void stusCb(char[] cArr, int i, int i2, byte[] bArr, int i3) {
        DebugLog.LogD(f3396k, "stusCb:" + i2 + ",type:" + i);
        if (i == 0 && 3 == i2) {
            m2252e();
        }
        if (1 == i) {
            String[] split = getParam().m1827b(SpeechConstant.MFV_SCENES, "").split("\\|");
            if (split == null || split.length >= 2) {
                try {
                    String str = new String(bArr, "utf-8");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("sub", str);
                    jSONObject.put("sret", i2);
                    jSONObject.put("ret", i2);
                    jSONObject.put("sst", this.f3407n.get(str).m1833e("sst"));
                    if (this.f3397a != null) {
                        this.f3397a.onResult(new IdentityResult(jSONObject.toString()), true);
                    }
                } catch (UnsupportedEncodingException e) {
                    DebugLog.LogE(e);
                } catch (JSONException e2) {
                    DebugLog.LogE(e2);
                }
            }
        }
    }

    void rsltCb(char[] cArr, byte[] bArr, int i, int i2) {
        if (bArr != null) {
            DebugLog.LogD(f3396k, "rsltCb:" + i2 + "result:" + new String(bArr));
        } else {
            DebugLog.LogI(f3396k, "rsltCb:" + i2 + "result:null");
        }
        Message obtainMessage = obtainMessage(4, i2, 0, bArr);
        if (hasMessages(4)) {
            sendMsg(obtainMessage, AbstractHandlerC3740s.a.normal, false, 0);
        } else {
            sendMsg(obtainMessage, AbstractHandlerC3740s.a.max, false, 0);
        }
    }

    void errCb(char[] cArr, int i, byte[] bArr) {
        DebugLog.LogD(f3396k, "clientSessionID:" + new String(cArr) + "errorcode:" + i);
        Bundle bundle = new Bundle();
        bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, getSessionID());
        this.f3397a.onEvent(20001, 0, 0, bundle);
        exit(new SpeechError(i));
    }

    /* renamed from: b */
    void m2249b(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        int i = message.arg1;
        byte[] bArr = (byte[]) message.obj;
        if (i == 0) {
            if (!this.f3399c) {
                this.f3399c = true;
                this.f3403i.m2256a("app_frs");
            }
            m2239a(false, bArr);
            return;
        }
        if (i == 2 || i != 5) {
            return;
        }
        if (!this.f3399c) {
            this.f3399c = true;
            this.f3403i.m2256a("app_frs");
        }
        this.f3403i.m2256a("app_lrs");
        m2239a(true, bArr);
    }

    /* renamed from: a */
    protected void m2245a(StringBuffer stringBuffer, byte[] bArr, boolean z, boolean z2) throws SpeechError {
        this.f3401e.m2234a(stringBuffer, bArr, bArr == null ? 0 : bArr.length, z);
        if (z2) {
            int m2232a = this.f3401e.m2232a();
            DebugLog.LogI("QISRAudioWrite volume:" + m2232a);
            m2246a(bArr, m2232a);
        }
    }

    /* renamed from: a */
    public void m2244a(String str, String str2, byte[] bArr, int i, int i2) {
        if (!this.f3407n.containsKey(str)) {
            C3692ad c3692ad = new C3692ad();
            c3692ad.m1821a(str2);
            c3692ad.m1823a("sst", getParam().m1833e("sst"), false);
            c3692ad.m1823a("mver", "2.0", false);
            c3692ad.m1822a("ssub", str);
            this.f3405l = c3692ad.m1825a("vad_enable", true);
            this.f3407n.put(str, c3692ad);
            this.f3408o.put(str, new StringBuffer(c3692ad.toString()));
        }
        if (isRunning()) {
            if (!this.f3398b) {
                this.f3398b = true;
                this.f3403i.m2256a("rec_start");
            }
            HashMap hashMap = new HashMap();
            hashMap.put("ssub", str);
            byte[] bArr2 = null;
            if (bArr != null) {
                bArr2 = new byte[i2];
                System.arraycopy(bArr, i, bArr2, 0, i2);
            }
            hashMap.put("data", bArr2);
            sendMsg(obtainMessage(2, hashMap));
        }
    }

    /* renamed from: f */
    public C3742u m2253f() {
        return this.f3403i;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getSessionID() {
        if (TextUtils.isEmpty(this.f3410q)) {
            this.f3410q = this.f3401e.m2237b();
        }
        return this.f3410q;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getClientID() {
        return this.f3401e.getClientID();
    }
}
