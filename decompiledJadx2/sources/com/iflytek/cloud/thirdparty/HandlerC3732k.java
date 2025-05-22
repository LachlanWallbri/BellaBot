package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.WakeuperResult;
import com.iflytek.cloud.msc.util.Config;
import com.iflytek.cloud.msc.util.FileUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import com.iflytek.msc.MSC;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONObject;

/* renamed from: com.iflytek.cloud.thirdparty.k */
/* loaded from: classes3.dex */
public class HandlerC3732k extends AbstractHandlerC3740s implements PcmRecorder.PcmRecordListener {

    /* renamed from: a */
    public String f3255a;

    /* renamed from: b */
    protected volatile WakeuperListener f3256b;

    /* renamed from: c */
    protected boolean f3257c;

    /* renamed from: d */
    protected int f3258d;

    /* renamed from: e */
    protected C3731j f3259e;

    /* renamed from: f */
    protected PcmRecorder f3260f;

    /* renamed from: g */
    protected ArrayList<String> f3261g;

    /* renamed from: h */
    public boolean f3262h;

    /* renamed from: i */
    public boolean f3263i;

    /* renamed from: j */
    protected final ConcurrentLinkedQueue<byte[]> f3264j;

    /* renamed from: k */
    protected final int f3265k;

    /* renamed from: l */
    protected String f3266l;

    /* renamed from: m */
    protected int f3267m;

    /* renamed from: n */
    protected int f3268n;

    /* renamed from: o */
    protected boolean f3269o;

    /* renamed from: p */
    protected final int f3270p;

    /* renamed from: q */
    protected int f3271q;

    /* renamed from: r */
    protected long f3272r;

    /* renamed from: s */
    protected long f3273s;

    /* renamed from: t */
    protected long f3274t;

    /* renamed from: u */
    protected final int f3275u;

    /* renamed from: v */
    protected int f3276v;

    /* renamed from: w */
    private boolean f3277w;

    /* renamed from: x */
    private int f3278x;

    /* renamed from: com.iflytek.cloud.thirdparty.k$a */
    /* loaded from: classes3.dex */
    public enum a {
        undefined_0,
        IVW_MSG_WAKEUP,
        IVW_MSG_ERROR,
        IVW_MSG_ISR_RESULT,
        IVW_MSG_ISR_EPS,
        IVW_MSG_VOLUME,
        IVW_MSG_ENROLL,
        IVW_MSG_RESET
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getSessionID() {
        return null;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public boolean isLongInput() {
        return false;
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordReleased() {
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordStarted(boolean z) {
    }

    public HandlerC3732k(Context context, C3692ad c3692ad, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.f3256b = null;
        this.f3257c = false;
        this.f3258d = 1;
        this.f3259e = new C3731j();
        this.f3260f = null;
        this.f3261g = null;
        this.f3262h = false;
        this.f3263i = false;
        this.f3264j = new ConcurrentLinkedQueue<>();
        this.f3265k = 60000;
        this.f3266l = null;
        this.f3267m = 0;
        this.f3268n = 0;
        this.f3269o = false;
        this.f3270p = 2000;
        this.f3271q = 0;
        this.f3272r = 0L;
        this.f3273s = 0L;
        this.f3274t = 0L;
        this.f3275u = 100;
        this.f3276v = 0;
        this.f3277w = false;
        this.f3278x = 0;
        this.f3257c = false;
        setParams(c3692ad);
        this.f3261g = new ArrayList<>();
    }

    /* renamed from: c */
    public int m2133c() {
        return this.f3258d;
    }

    /* renamed from: a */
    public synchronized void m2129a(WakeuperListener wakeuperListener) {
        this.f3256b = wakeuperListener;
        DebugLog.LogD("[ivw]startListening called");
        start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
        super.onMsgProcess(message);
        int i = message.what;
        if (i == 0) {
            mo2119b();
            return;
        }
        if (i == 1) {
            mo2114a();
            return;
        }
        if (i == 2) {
            m2128a(message);
        } else if (i == 3) {
            m2126f();
        } else {
            if (i != 4) {
                return;
            }
            m2132b(message);
        }
    }

    /* renamed from: b */
    protected void mo2119b() throws Exception {
        DebugLog.LogD("[ivw]start connecting");
        this.f3262h = false;
        int m1816a = getParam().m1816a("record_read_rate", 40);
        this.f3269o = getParam().m1825a(SpeechConstant.NOTIFY_RECORD_DATA, this.f3269o);
        if (this.f3258d != -1 && isRunning()) {
            this.f3266l = getParam().m1833e(SpeechConstant.IVW_AUDIO_PATH);
            if (!TextUtils.isEmpty(this.f3266l)) {
                this.f3267m = ((getSampleRate() * 60000) / 1000) * 2;
            }
            DebugLog.LogD("[ivw]start  record");
            if (this.f3260f == null) {
                this.f3277w = getParam().m1825a(SpeechConstant.BLUETOOTH, this.f3277w);
                if (this.f3277w) {
                    startBluetooth();
                }
                this.f3260f = new PcmRecorder(getSampleRate(), m1816a, this.f3258d);
                this.f3260f.startRecording(this);
            }
        } else {
            this.f3267m = 0;
        }
        this.f3271q = ((getSampleRate() * 2000) / 1000) * 2;
        DebugLog.LogD("max saved buf byte: " + this.f3267m + ", max auw buf byte: " + this.f3271q);
        if (getStatus() != AbstractHandlerC3740s.b.exiting && this.f3256b != null) {
            this.f3256b.onBeginOfSpeech();
        }
        sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
    }

    /* renamed from: a */
    protected void mo2114a() throws SpeechError, Throwable {
        if (this.f3259e.mClientID == null) {
            PerfLogger.appendInfo(PerfLogger.SDK_SESSION_BIGNE, null);
            int sessionBegin = this.f3259e.sessionBegin(this.mContext, this.f3255a, this);
            if (sessionBegin == 0 && this.f3259e.mClientID != null) {
                if (isRunning()) {
                    MSC.QIVWRegisterNotify(this.f3259e.mClientID, "MsgProcCallBack", this);
                    setStatus(AbstractHandlerC3740s.b.recording);
                    return;
                }
                return;
            }
            if (sessionBegin != 0) {
                this.f3278x++;
                if (this.f3278x > 40) {
                    throw new SpeechError(sessionBegin);
                }
                if (isRunning()) {
                    Thread.sleep(15L);
                    sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
                    return;
                }
                return;
            }
            DebugLog.LogE("current csid: " + this.f3259e.mSessionID);
            throw new SpeechError(20999);
        }
    }

    /* renamed from: a */
    protected void m2128a(Message message) throws Throwable {
        byte[] bArr = (byte[]) message.obj;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        m2130a(bArr, true);
        this.f3274t += bArr.length;
        long j = this.f3272r;
        if (j < this.f3273s) {
            this.f3272r = j + bArr.length;
        }
        this.f3276v++;
        if (this.f3273s - this.f3272r >= this.f3271q) {
            DebugLog.LogW("cur rec buf: " + this.f3273s + ", cur sync auw size: " + this.f3272r + ", cur writen size: " + this.f3274t + ", diff match max buf size: " + this.f3271q + ", cur bufs in msg will be ignored!");
            this.f3272r = this.f3273s;
            removeMessages(2);
            System.gc();
        } else if (100 <= this.f3276v) {
            this.f3276v = 0;
            DebugLog.LogD("cur rec buf: " + this.f3273s + ", cur sync auw size: " + this.f3272r + ", cur writen size: " + this.f3274t);
        }
        if (this.f3267m > 0) {
            while (this.f3267m < this.f3268n) {
                byte[] poll = this.f3264j.poll();
                this.f3268n -= poll != null ? poll.length : 0;
            }
            this.f3264j.add(bArr);
            this.f3268n += bArr.length;
        }
        if (this.f3269o) {
            Bundle bundle = new Bundle();
            bundle.putByteArray("data", bArr);
            this.f3256b.onEvent(21003, 0, 0, bundle);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    int MsgProcCallBack(char[] cArr, int i, int i2, int i3, byte[] bArr) {
        Message obtainMessage;
        DebugLog.LogD("cur rec buf: " + this.f3273s + ", cur sync auw size: " + this.f3272r + ", cur writen size: " + this.f3274t);
        if (bArr != null) {
            DebugLog.LogD("MscWakeuper", "msg:" + i + "param1:" + i2 + "param2:" + i3 + "result:" + new String(bArr));
        } else {
            DebugLog.LogD("MscWakeuper", "msg:" + i + "param1:" + i2 + "param2:" + i3 + "result:null");
        }
        a aVar = null;
        try {
            aVar = a.values()[i];
        } catch (Throwable th) {
            DebugLog.LogE("unmatched ivw message!");
            DebugLog.LogE(th);
        }
        if (aVar != null) {
            switch (aVar) {
                case IVW_MSG_WAKEUP:
                    this.f3262h = true;
                    if (this.f3263i || this.f3255a.equals(AIUIConstant.INTERACT_MODE_ONESHOT)) {
                        obtainMessage = obtainMessage(4, 0, 0, bArr);
                    } else {
                        obtainMessage = obtainMessage(4, 5, 0, bArr);
                    }
                    if (hasMessages(4)) {
                        sendMsg(obtainMessage, AbstractHandlerC3740s.a.normal, false, 0);
                        break;
                    } else {
                        sendMsg(obtainMessage, AbstractHandlerC3740s.a.max, false, 0);
                        break;
                    }
                    break;
                case IVW_MSG_ERROR:
                    onError(new SpeechError(i2));
                    break;
                case IVW_MSG_ISR_RESULT:
                    Message obtainMessage2 = obtainMessage(4, i2, 1, bArr);
                    if (hasMessages(4)) {
                        sendMsg(obtainMessage2, AbstractHandlerC3740s.a.normal, false, 0);
                        break;
                    } else {
                        sendMsg(obtainMessage2, AbstractHandlerC3740s.a.max, false, 0);
                        break;
                    }
                case IVW_MSG_ISR_EPS:
                    if (i2 == 3) {
                        m2134d();
                        break;
                    }
                    break;
                case IVW_MSG_VOLUME:
                    try {
                        if (this.f3256b != null) {
                            this.f3256b.onVolumeChanged(i2);
                            break;
                        }
                    } catch (Exception unused) {
                        break;
                    }
                    break;
                case IVW_MSG_ENROLL:
                    sendMsg(obtainMessage(4, 0, 2, bArr), AbstractHandlerC3740s.a.max, false, 0);
                    break;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public synchronized boolean m2131a(boolean z) {
        DebugLog.LogD("stopListening, current status is :" + getStatus() + " usercancel : " + z);
        if (this.f3255a.equals("enroll")) {
            this.f3257c = z;
            sendMsg(3);
        } else if (this.f3255a.equals(AIUIConstant.INTERACT_MODE_ONESHOT) && this.f3262h) {
            m2127g();
            this.f3257c = z;
            sendMsg(3);
        } else {
            cancel(false);
        }
        return true;
    }

    /* renamed from: f */
    private void m2126f() throws SpeechError, IOException, InterruptedException {
        DebugLog.LogD("recording stop");
        if (!this.f3255a.equals("enroll")) {
            m2127g();
        }
        this.f3259e.m2122a();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void cancel(boolean z) {
        if (z && isRunning() && this.f3256b != null) {
            this.f3256b.onError(new SpeechError(20017));
        }
        DebugLog.LogD("cancel");
        m2127g();
        if (getStatus() == AbstractHandlerC3740s.b.recording) {
            this.f3257c = true;
        }
        super.cancel(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onEnd(SpeechError speechError) {
        DebugLog.LogD("onSessionEnd");
        m2127g();
        if (!TextUtils.isEmpty(this.f3266l)) {
            if (FileUtil.saveFile(this.f3264j, this.f3266l)) {
                FileUtil.formatPcm(getParam().m1827b(SpeechConstant.AUDIO_FORMAT, (String) null), this.f3266l, getSampleRate());
                DebugLog.LogD("save ivw audio succeed: " + this.f3266l);
            } else {
                DebugLog.LogE("save ivw audio failed: " + this.f3266l);
            }
        }
        if (this.f3255a.equals(AIUIConstant.INTERACT_MODE_ONESHOT) && this.f3262h && this.f3261g.size() <= 0 && speechError == null && getParam().m1825a(SpeechConstant.ASR_NOMATCH_ERROR, true)) {
            speechError = new SpeechError(10118);
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_END_BEGIN, null);
        if (this.mUserCancel) {
            this.f3259e.sessionEnd("user abort");
        } else if (speechError != null) {
            this.f3259e.sessionEnd("error" + speechError.getErrorCode());
        } else {
            this.f3259e.sessionEnd("success");
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_END_END, null);
        super.onEnd(speechError);
        if (this.f3256b != null) {
            if (this.mUserCancel) {
                DebugLog.LogD("WakeuperListener#onCancel");
            } else {
                DebugLog.LogD("WakeuperListener#onEnd");
                if (speechError != null) {
                    this.f3256b.onError(speechError);
                }
            }
        }
        this.f3256b = null;
    }

    /* renamed from: a */
    private void m2125a(boolean z, byte[] bArr, int i, Bundle bundle) throws SpeechError, UnsupportedEncodingException {
        String str;
        boolean z2 = false;
        if (i == 1) {
            if (bArr != null && bArr.length > 0) {
                str = new String(bArr, getResultEncoding());
            } else {
                if (this.f3261g.size() <= 0) {
                    String m1833e = getParam().m1833e(SpeechConstant.LOCAL_GRAMMAR);
                    if (!TextUtils.isEmpty(m1833e) && !"sms.irf".equals(m1833e)) {
                        throw new SpeechError(20005);
                    }
                    throw new SpeechError(10118);
                }
                str = "";
            }
            this.f3261g.add(str);
            if (this.f3256b != null && isRunning()) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable(SpeechEvent.KEY_EVENT_IVW_RESULT, new RecognizerResult(str));
                this.f3256b.onEvent(22001, z ? 1 : 0, 0, bundle2);
            }
            DebugLog.LogD("msc result time:" + System.currentTimeMillis());
        } else if (i == 0) {
            if (bArr != null && bArr.length > 0) {
                String str2 = new String(bArr, "utf-8");
                if (this.f3256b != null && isRunning()) {
                    WakeuperResult wakeuperResult = new WakeuperResult(str2, bundle != null ? bundle.getByteArray("ivw_audio") : null);
                    PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
                    this.f3256b.onResult(wakeuperResult);
                }
            } else {
                throw new SpeechError(10118);
            }
        } else if (i == 2) {
            if (bArr != null && bArr.length > 0) {
                String str3 = new String(bArr, "utf-8");
                if (this.f3255a.equals("enroll")) {
                    if (this.f3256b != null) {
                        this.f3256b.onResult(new WakeuperResult(str3));
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(str3);
                        if (jSONObject.getInt("suc_times") != jSONObject.getInt("total_times")) {
                            sendMsg(0);
                        } else {
                            z2 = true;
                        }
                    } catch (Exception unused) {
                        throw new SpeechError(10118);
                    }
                }
                if (this.f3256b != null && isRunning()) {
                    this.f3256b.onResult(new WakeuperResult(str3));
                    if (z2) {
                        exit(null);
                    }
                }
            } else {
                throw new SpeechError(10118);
            }
        }
        if (z) {
            exit(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onParseParam() {
        this.f3255a = getParam().m1827b("sst", "wakeup");
        this.f3263i = getParam().m1825a(SpeechConstant.KEEP_ALIVE, false);
        this.f3258d = getParam().m1816a(SpeechConstant.AUDIO_SOURCE, 1);
        int i = Config.getConfig(this.mContext).getInt("ivw_netval", 20);
        getParam().m1823a("ivw_netval", i + "", false);
        super.onParseParam();
    }

    /* renamed from: g */
    private void m2127g() {
        PcmRecorder pcmRecorder = this.f3260f;
        if (pcmRecorder != null) {
            pcmRecorder.stopRecord(getParam().m1825a("record_force_stop", false));
            this.f3260f = null;
            if (this.f3277w) {
                stopBluetooth();
            }
        }
    }

    /* renamed from: b */
    void m2132b(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        int i = message.arg1;
        byte[] bArr = (byte[]) message.obj;
        if (i == 0) {
            m2125a(false, bArr, message.arg2, message.getData());
        } else {
            if (i == 2) {
                throw new SpeechError(20010);
            }
            if (i != 5) {
                return;
            }
            m2125a(true, bArr, message.arg2, message.getData());
        }
    }

    /* renamed from: a */
    protected void m2130a(byte[] bArr, boolean z) throws SpeechError {
        this.f3259e.m2123a(bArr, bArr.length);
    }

    /* renamed from: a */
    public int mo2113a(byte[] bArr, int i, int i2) {
        onRecordBuffer(bArr, i, i2);
        return 0;
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (bArr.length < i2 || bArr == null || i2 <= 0 || i2 <= 0 || !isRunning()) {
            return;
        }
        this.f3273s += i2;
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        if (this.f3256b != null) {
            this.f3256b.onVolumeChanged((int) m2124a(i2, bArr2));
        }
        sendMsg(obtainMessage(2, bArr2));
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onError(SpeechError speechError) {
        exit(speechError);
    }

    /* renamed from: d */
    public void m2134d() {
        if (AbstractHandlerC3740s.b.recording == getStatus()) {
            DebugLog.LogD("ivw msc vadEndCall");
            m2131a(false);
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getClientID() {
        return this.f3259e.getClientID();
    }

    /* renamed from: e */
    public WakeuperListener m2135e() {
        return this.f3256b;
    }

    /* renamed from: a */
    private double m2124a(int i, byte[] bArr) {
        long j = 0;
        for (byte b : bArr) {
            j += b * b;
        }
        double log10 = Math.log10(j / i) * 10.0d;
        return log10 > 20.0d ? log10 - 20.0d : log10;
    }
}
