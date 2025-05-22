package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.io.IOException;
import java.util.HashSet;

/* renamed from: com.iflytek.cloud.thirdparty.s */
/* loaded from: classes3.dex */
public abstract class AbstractHandlerC3740s extends Handler {
    protected static final int MSG_CLEAR = 20;
    protected static final int MSG_DOWNLOAD = 11;
    protected static final int MSG_END = 21;
    protected static final int MSG_ISV_REQUEST = 13;
    protected static final int MSG_NET_PERF = 7;
    protected static final int MSG_NET_TIMEOUT = 8;
    protected static final int MSG_RECORD_AUDIO = 2;
    protected static final int MSG_RECORD_STOPD = 3;
    protected static final int MSG_REQUEST_RESULT = 22;
    protected static final int MSG_RESULT = 4;
    protected static final int MSG_SEARCH = 12;
    protected static final int MSG_SESSION_BEGIN = 1;
    protected static final int MSG_SPEECH_TIMEOUT = 9;
    protected static final int MSG_START = 0;
    protected static final int MSG_TTS_AUDIO = 5;
    protected static final int MSG_UPLOAD = 10;
    public static final String TAG_DOWNFLOW = "downflow";
    public static final String TAG_LOGIN_ID = "loginid";
    public static final String TAG_NETPERF = "netperf";
    public static final String TAG_UPFLOW = "upflow";
    protected static final HashSet<AbstractHandlerC3740s> sInstances = new HashSet<>();
    protected Context mContext;
    protected int mNetTimeOut;
    private C3692ad mParam;
    public int mSampleRate;
    protected int mSpeechTimeOut;
    private volatile b mStatus;
    protected long mStatusBegin;
    private HandlerThread mThread;
    protected volatile boolean mUserCancel;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: com.iflytek.cloud.thirdparty.s$a */
    /* loaded from: classes3.dex */
    public enum a {
        max,
        normal
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: com.iflytek.cloud.thirdparty.s$b */
    /* loaded from: classes3.dex */
    public enum b {
        init,
        start,
        recording,
        waitresult,
        exiting,
        exited
    }

    public abstract String getClientID();

    public abstract String getSessionID();

    public boolean isLongInput() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
    }

    public AbstractHandlerC3740s(Context context, HandlerThread handlerThread) {
        super(handlerThread.getLooper());
        this.mSpeechTimeOut = 60000;
        this.mSampleRate = 16000;
        this.mContext = null;
        this.mParam = new C3692ad();
        this.mUserCancel = false;
        this.mStatus = b.init;
        this.mStatusBegin = 0L;
        this.mNetTimeOut = 20000;
        this.mThread = handlerThread;
        this.mContext = context;
        this.mUserCancel = false;
        sInstances.add(this);
    }

    public AbstractHandlerC3740s(Context context) {
        super(context.getMainLooper());
        this.mSpeechTimeOut = 60000;
        this.mSampleRate = 16000;
        this.mContext = null;
        this.mParam = new C3692ad();
        this.mUserCancel = false;
        this.mStatus = b.init;
        this.mStatusBegin = 0L;
        this.mNetTimeOut = 20000;
        this.mContext = context;
        this.mUserCancel = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateTimeoutMsg() {
        removeMessages(8);
        sendMsg(8, a.normal, false, this.mNetTimeOut);
    }

    private void destroyThread() {
        Looper mainLooper;
        HandlerThread handlerThread = this.mThread;
        if (handlerThread != null && handlerThread.isAlive()) {
            clearAllMsg();
            Context context = this.mContext;
            Thread thread = (context == null || (mainLooper = context.getMainLooper()) == null) ? null : mainLooper.getThread();
            if (this.mContext == null || !this.mThread.equals(thread)) {
                this.mThread.quit();
                DebugLog.LogD("quit current Msc Handler thread");
            }
            this.mThread = null;
        }
        sInstances.remove(this);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        SpeechError e;
        StringBuilder sb;
        if (message.what == 21) {
            onEnd((SpeechError) message.obj);
            destroyThread();
            return;
        }
        try {
            try {
                try {
                    if (message.what == 8) {
                        throw new SpeechError(20002);
                    }
                    if (SpeechUtility.getUtility() == null && 1 == message.what) {
                        DebugLog.LogE("SDK is not init while session begin");
                        throw new SpeechError(20015);
                    }
                    onMsgProcess(message);
                } catch (SpeechError e2) {
                    e = e2;
                    DebugLog.LogE(e);
                    sb = new StringBuilder();
                    sb.append(getTag());
                    sb.append(" occur Error = ");
                    sb.append(e.toString());
                    DebugLog.LogD(sb.toString());
                    exit(e);
                } catch (UnsatisfiedLinkError e3) {
                    DebugLog.LogE(e3);
                    e = new SpeechError(20021);
                    sb = new StringBuilder();
                    sb.append(getTag());
                    sb.append(" occur Error = ");
                    sb.append(e.toString());
                    DebugLog.LogD(sb.toString());
                    exit(e);
                }
            } catch (IOException e4) {
                DebugLog.LogE(e4);
                e = new SpeechError(20010);
                sb = new StringBuilder();
                sb.append(getTag());
                sb.append(" occur Error = ");
                sb.append(e.toString());
                DebugLog.LogD(sb.toString());
                exit(e);
            } catch (Throwable th) {
                DebugLog.LogE(th);
                e = new SpeechError(20999);
                sb = new StringBuilder();
                sb.append(getTag());
                sb.append(" occur Error = ");
                sb.append(e.toString());
                DebugLog.LogD(sb.toString());
                exit(e);
            }
        } catch (Exception e5) {
            DebugLog.LogE(e5);
            SpeechError speechError = new SpeechError(e5);
            DebugLog.LogD(getTag() + " occur Error = " + speechError.toString());
            exit(speechError);
        }
    }

    public String getParamEncoding() {
        return this.mParam.m1827b("pte", "utf-8");
    }

    public String getTextEncoding() {
        return this.mParam.m1827b(SpeechConstant.TEXT_ENCODING, "utf-8");
    }

    public String getResultEncoding() {
        return this.mParam.m1827b("rse", "utf-8");
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public void cancel(boolean z) {
        this.mUserCancel = true;
        clearAllMsg();
        exit(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void exit(SpeechError speechError) {
        if (speechError != null) {
            clearAllMsg();
        }
        sendMsg(obtainMessage(21, speechError));
    }

    public static boolean isEmpty() {
        return sInstances.isEmpty();
    }

    public boolean isRunning() {
        return (this.mStatus == b.exited || this.mStatus == b.exiting || this.mStatus == b.init) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void setStatus(b bVar) {
        DebugLog.LogD("curStatus=" + this.mStatus + ",setStatus=" + bVar);
        if (this.mStatus == b.exited) {
            return;
        }
        if (this.mStatus != b.exiting || bVar == b.exited) {
            DebugLog.LogD("setStatus success=" + bVar);
            this.mStatus = bVar;
            this.mStatusBegin = SystemClock.elapsedRealtime();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized b getStatus() {
        return this.mStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setParams(C3692ad c3692ad) {
        this.mParam = c3692ad.clone();
        onParseParam();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onParseParam() {
        this.mNetTimeOut = this.mParam.m1816a("timeout", this.mNetTimeOut);
        this.mSampleRate = this.mParam.m1816a("sample_rate", this.mSampleRate);
    }

    public C3692ad getParam() {
        return this.mParam;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void start() {
        sendMsg(0, a.max, false, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendMsg(int i) {
        sendMsg(obtainMessage(i), a.normal, false, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendMsg(Message message) {
        sendMsg(message, a.normal, false, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendMsg(int i, a aVar, boolean z, int i2) {
        sendMsg(obtainMessage(i), aVar, z, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendMsg(Message message, a aVar, boolean z, int i) {
        if (getStatus() == b.exited || getStatus() == b.exiting) {
            DebugLog.LogD("send msg failed while status is " + getStatus());
            return;
        }
        int i2 = message.what;
        if (i2 == 0) {
            setStatus(b.start);
        } else if (i2 == 3) {
            setStatus(b.waitresult);
        } else if (i2 == 21) {
            setStatus(b.exiting);
        }
        if (z) {
            removeMessages(message.what);
        }
        if (aVar == a.max && i <= 0) {
            sendMessageAtFrontOfQueue(message);
        } else {
            sendMessageDelayed(message, i);
        }
    }

    protected void clearAllMsg() {
        DebugLog.LogD("clear all message");
        for (int i = 0; i < 20; i++) {
            removeMessages(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onEnd(SpeechError speechError) {
        setStatus(b.exited);
        clearAllMsg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getTag() {
        return getClass().toString();
    }

    public static void timeOutCheck(long j, int i) throws SpeechError {
        if (SystemClock.elapsedRealtime() - j > i) {
            throw new SpeechError(20002);
        }
    }

    public void startBluetooth() throws SecurityException {
        DebugLog.LogD("startBluetooth enter");
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService(InternalConstant.DTYPE_AUDIO);
        audioManager.setBluetoothScoOn(true);
        audioManager.startBluetoothSco();
    }

    public void stopBluetooth() {
        DebugLog.LogD("stopBluetooth enter");
        try {
            AudioManager audioManager = (AudioManager) this.mContext.getSystemService(InternalConstant.DTYPE_AUDIO);
            audioManager.setBluetoothScoOn(false);
            audioManager.stopBluetoothSco();
        } catch (SecurityException e) {
            DebugLog.LogE(e);
        } catch (Throwable th) {
            DebugLog.LogE(th);
        }
    }
}
