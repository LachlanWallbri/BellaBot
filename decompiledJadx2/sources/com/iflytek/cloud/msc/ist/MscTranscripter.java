package com.iflytek.cloud.msc.ist;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.TranscripterListener;
import com.iflytek.cloud.TranscripterResult;
import com.iflytek.cloud.msc.ist.AudioAccessor;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.thirdparty.AbstractC3741t;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import com.iflytek.cloud.thirdparty.C3692ad;
import com.iflytek.cloud.thirdparty.C3693ae;
import com.iflytek.cloud.thirdparty.C3742u;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class MscTranscripter extends AbstractHandlerC3740s implements PcmRecorder.PcmRecordListener {
    protected static final int MSG_CUSTOM_MIN = 32789;
    private static final int MSG_WAIT_RESULT = 32790;
    private static final int NET_PERF_MSG_DELAY = 500;
    private static final int STATUS_HASRESULT = 0;
    private static final int STATUS_LASTRESULT = 5;
    private static final int STATUS_NORESULT = 2;
    private static final int WAIT_RESULT_PERIOD = 500;
    public static int mDownflow;
    public static int mUpflow;
    private final String KEY_TOTAL_AUDIO;
    private final int SESSION_RETRY_PERIOD;
    private AudioAccessor mAccesser;
    protected int mAudioSource;
    private byte[] mBuffer;
    private SpeechError mError;
    private String mFilePath;
    protected boolean mFirstDataArrival;
    protected boolean mFirstDataWrite;
    protected boolean mFirstRecordAudio;
    private boolean mIsFirstSession;
    private boolean mIsProcRecordDataOn;
    private boolean mIsRecording;
    private boolean mIsRestarting;
    private int mLastMscOffset;
    private int mLastServerOffset;
    protected volatile TranscripterListener mListener;
    private int mMaxSessionTry;
    protected C3742u mPerflog;
    protected PcmRecorder mRecorder;
    private int mResultSecondLeft;
    protected ArrayList<String> mResults;
    protected IstSession mSession;
    private String mSessionID;
    private int mSessionRetryCount;
    private int mSyncID;
    private boolean mUseBluetooth;
    protected boolean mUstopRecord;

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordReleased() {
    }

    public MscTranscripter(Context context, C3692ad c3692ad, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.mListener = null;
        this.mFirstRecordAudio = false;
        this.mFirstDataWrite = false;
        this.mFirstDataArrival = false;
        this.mUstopRecord = false;
        this.mAudioSource = 1;
        this.mSession = new IstSession();
        this.mRecorder = null;
        this.mResults = null;
        this.mBuffer = null;
        this.mLastMscOffset = 0;
        this.mLastServerOffset = 0;
        this.mResultSecondLeft = -1;
        this.mSessionID = null;
        this.mSyncID = 0;
        this.mFilePath = null;
        this.mAccesser = null;
        this.KEY_TOTAL_AUDIO = "total";
        this.mMaxSessionTry = 1;
        this.SESSION_RETRY_PERIOD = 500;
        this.mIsProcRecordDataOn = false;
        this.mIsRecording = false;
        this.mError = null;
        this.mIsFirstSession = true;
        this.mIsRestarting = false;
        this.mUseBluetooth = false;
        this.mPerflog = new C3742u();
        this.mSessionRetryCount = 0;
        this.mResults = new ArrayList<>();
        this.mUstopRecord = false;
        setParams(c3692ad);
    }

    public int getAudioSource() {
        return this.mAudioSource;
    }

    public void callbackVolume(byte[] bArr, int i) {
        if (isRunning()) {
            this.mListener.onVolumeChanged(i, bArr);
        }
    }

    public synchronized void startTranscripting(TranscripterListener transcripterListener) {
        this.mListener = transcripterListener;
        DebugLog.LogD("startTranscripting called");
        start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void start() {
        this.mPerflog.m2255a(getParam());
        super.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
        super.onMsgProcess(message);
        if (message.what == 0 || isRunning()) {
            int i = message.what;
            if (i == 0) {
                proc_Msg_Start();
                return;
            }
            if (i == 1) {
                proc_Msg_Session_Begin();
                return;
            }
            if (i == 2) {
                proc_Msg_Record_Data(message);
                return;
            }
            if (i == 3) {
                proc_Msg_Record_Stoped();
                return;
            }
            if (i == 4) {
                proc_Msg_Result(message);
                return;
            }
            if (i == 7) {
                proc_Msg_Netperf();
            } else if (i == 22) {
                requestResult();
            } else {
                if (i != MSG_WAIT_RESULT) {
                    return;
                }
                proc_Msg_Wait_Result();
            }
        }
    }

    protected void proc_Msg_Start() throws Exception {
        DebugLog.LogD("start connecting");
        if ("cloud".equals(getParam().m1833e("engine_type")) && (-1 == getAudioSource() || -2 == getAudioSource())) {
            NetworkUtil.checkNetwork(this.mContext);
        }
        int m1816a = getParam().m1816a("record_read_rate", 40);
        int i = this.mAudioSource;
        if (-1 == i) {
            this.mAccesser = AudioAccessor.createBufferAccessor();
        } else if (-2 == i) {
            this.mAccesser = AudioAccessor.createReadOnlyAccessor(this.mFilePath);
            getParam().m1823a("sample_rate", this.mAccesser.getAudioInfo(AudioAccessor.AudioKeys.RATE), false);
        } else {
            this.mAccesser = AudioAccessor.createWriteReadAccessor(this.mFilePath, this.mSampleRate);
            if (this.mListener != null) {
                Bundle bundle = new Bundle();
                bundle.putString(SpeechConstant.IST_AUDIO_PATH, this.mAccesser.getFilePath());
                this.mListener.onEvent(SpeechEvent.EVENT_IST_AUDIO_FILE, 0, 0, bundle);
            }
        }
        this.mBuffer = new byte[this.mAccesser.getBufferLength()];
        if (this.mAudioSource >= 0 && isRunning()) {
            DebugLog.LogD("start  record");
            this.mUseBluetooth = getParam().m1825a(SpeechConstant.BLUETOOTH, this.mUseBluetooth);
            if (this.mUseBluetooth) {
                startBluetooth();
            }
            this.mRecorder = new PcmRecorder(getSampleRate(), m1816a, this.mAudioSource);
            this.mPerflog.m2256a("rec_open");
            this.mRecorder.startRecording(this);
            if (this.mListener != null) {
                this.mListener.onBeginOfSpeech();
            }
        }
        this.mPerflog.m2256a("app_ssb");
        sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
        this.mIsRecording = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:
    
        if (isRecordMode() != false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0083, code lost:
    
        sendMsg(1, com.iflytek.cloud.thirdparty.AbstractHandlerC3740s.a.max, false, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007d, code lost:
    
        sendMsg(1, com.iflytek.cloud.thirdparty.AbstractHandlerC3740s.a.normal, false, 500);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x007b, code lost:
    
        if (isRecordMode() != false) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void proc_Msg_Session_Begin() throws Exception {
        int i;
        int sessionBegin;
        if (isSessionOn()) {
            return;
        }
        assertSessionTimes();
        try {
            try {
                PerfLogger.appendInfo(PerfLogger.SDK_SESSION_BIGNE, null);
                sessionBegin = this.mSession.sessionBegin(this.mContext, null, this);
                this.mSessionRetryCount++;
            } catch (SpeechError e) {
                e.printStackTrace();
                i = e.getErrorCode();
                try {
                    this.mError = e;
                    this.mSessionRetryCount++;
                    if (i == 0 && this.mSession.mClientID != null) {
                        this.mIsRestarting = false;
                        if (!this.mIsFirstSession) {
                            return;
                        }
                        this.mIsFirstSession = false;
                        if (this.mListener == null) {
                            return;
                        }
                    } else if (!isRunning()) {
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    this.mSessionRetryCount++;
                    if (i != 0) {
                    }
                    if (isRunning()) {
                    }
                    throw th;
                }
            }
            if (sessionBegin == 0 && this.mSession.mClientID != null) {
                this.mIsRestarting = false;
                if (this.mIsFirstSession) {
                    this.mIsFirstSession = false;
                    if (this.mListener == null) {
                        return;
                    }
                    this.mListener.onEvent(SpeechEvent.EVENT_SESSION_BEGIN, 0, 0, null);
                    return;
                }
                return;
            }
            if (!isRunning()) {
            }
        } catch (Throwable th2) {
            th = th2;
            i = 0;
            this.mSessionRetryCount++;
            if (i != 0 && this.mSession.mClientID != null) {
                this.mIsRestarting = false;
                if (this.mIsFirstSession) {
                    this.mIsFirstSession = false;
                    if (this.mListener != null) {
                        this.mListener.onEvent(SpeechEvent.EVENT_SESSION_BEGIN, 0, 0, null);
                    }
                }
            } else if (isRunning()) {
                if (isRecordMode()) {
                    sendMsg(1, AbstractHandlerC3740s.a.normal, false, 500);
                } else {
                    sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
                }
            }
            throw th;
        }
    }

    protected void proc_Msg_Record_Data(Message message) throws Exception {
        try {
            int audioData = getAudioData();
            try {
                try {
                    if (isSessionOn()) {
                        if (this.mBuffer != null && audioData > 0) {
                            uploadData(this.mBuffer, audioData, false);
                            this.mLastMscOffset += audioData;
                            DebugLog.LogD("LastMscOffset: " + this.mLastMscOffset);
                        }
                        updateUploadBytes();
                        updateSessionID();
                        updateSyncID();
                        updateCatchLeft();
                        sendMsg(obtainMessage(22, null), AbstractHandlerC3740s.a.normal, false, 100);
                    }
                } catch (SpeechError e) {
                    this.mError = e;
                    if (10114 == e.getErrorCode()) {
                        restartSession();
                        if (!hasMoreData()) {
                            if (!isSessionOn()) {
                                return;
                            }
                        }
                    } else {
                        throw e;
                    }
                }
                if (!hasMoreData()) {
                    if (!isSessionOn()) {
                        return;
                    }
                    stopWriteAutio();
                    return;
                }
                sendMsg(obtainMessage(2, null));
            } catch (Throwable th) {
                if (hasMoreData()) {
                    sendMsg(obtainMessage(2, null));
                } else if (isSessionOn()) {
                    stopWriteAutio();
                }
                throw th;
            }
        } catch (IOException e2) {
            DebugLog.LogE(e2);
            throw new SpeechError(10105, "Read file Error" + e2.getLocalizedMessage());
        }
    }

    public synchronized boolean stopTranscripting(boolean z) {
        DebugLog.LogD("stopTranscript, current status is :" + getStatus() + " usercancel : " + z);
        this.mPerflog.m2256a("app_stop");
        releaseRecord();
        this.mUstopRecord = z;
        sendMsg(3);
        return true;
    }

    private void proc_Msg_Record_Stoped() throws SpeechError, IOException, InterruptedException {
        DebugLog.LogD("recording stop");
        releaseRecord();
        this.mPerflog.m2256a("app_lau");
        if (this.mListener != null) {
            this.mListener.onEndOfSpeech();
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void cancel(boolean z) {
        if (z && isRunning() && this.mListener != null) {
            this.mListener.onError(new SpeechError(20017));
        }
        releaseRecord();
        if (getStatus() == AbstractHandlerC3740s.b.recording) {
            this.mUstopRecord = true;
        }
        super.cancel(z);
    }

    public void proc_Msg_Netperf() {
        if (isRunning()) {
            int intValue = this.mSession.getIntValue(AbstractHandlerC3740s.TAG_NETPERF);
            if (this.mListener != null) {
                this.mListener.onEvent(10001, intValue, 0, null);
            }
            sendMsg(7, AbstractHandlerC3740s.a.normal, false, 500);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onEnd(SpeechError speechError) {
        DebugLog.LogD("onSessionEnd");
        releaseRecord();
        mUpflow = this.mSession.getIntValue(AbstractHandlerC3740s.TAG_UPFLOW);
        mDownflow = this.mSession.getIntValue(AbstractHandlerC3740s.TAG_DOWNFLOW);
        getSessionID();
        updateUploadBytes();
        updateSessionID();
        updateSyncID();
        if (this.mListener != null) {
            Bundle bundle = new Bundle();
            bundle.putString("sid", this.mSessionID);
            bundle.putInt(SpeechConstant.IST_SYNC_ID, this.mSyncID);
            bundle.putInt(SpeechConstant.IST_AUDIO_UPLOADED, this.mLastServerOffset);
            bundle.putInt("total", (int) getDataLength());
            bundle.putString(SpeechConstant.IST_AUDIO_PATH, getAudioPath());
            bundle.putSerializable("error", speechError);
            this.mListener.onEvent(SpeechEvent.EVENT_SESSION_END, 0, 0, bundle);
        }
        try {
            closeAudioFile();
        } catch (Exception e) {
            if (speechError == null) {
                speechError = new SpeechError(e);
            }
        }
        if (this.mResults.size() <= 0 && speechError == null && getParam().m1825a(SpeechConstant.ASR_NOMATCH_ERROR, true)) {
            speechError = new SpeechError(10118);
        }
        if (speechError != null) {
            this.mPerflog.m2257a("app_ret", speechError.getErrorCode(), false);
        } else {
            this.mPerflog.m2257a("app_ret", 0L, false);
        }
        this.mPerflog.m2258a("rec_ustop", this.mUstopRecord ? "1" : "0", false);
        this.mSession.setParam("sessinfo", this.mPerflog.m2254a());
        PerfLogger.appendInfo(PerfLogger.SESSION_END_BEGIN, null);
        if (this.mUserCancel) {
            this.mSession.sessionEnd("user abort");
        } else if (speechError != null) {
            this.mSession.sessionEnd("error" + speechError.getErrorCode());
        } else {
            this.mSession.sessionEnd("success");
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_END_END, null);
        super.onEnd(speechError);
        if (this.mListener != null) {
            if (this.mUserCancel) {
                DebugLog.LogD("TranscripterListener#onCancel");
                return;
            }
            DebugLog.LogD("TranscripterListener#onEnd");
            if (speechError != null) {
                this.mListener.onError(speechError);
            }
        }
    }

    private void notifyResult(boolean z, byte[] bArr) throws SpeechError, UnsupportedEncodingException {
        String str;
        this.mStatusBegin = SystemClock.elapsedRealtime();
        if (bArr != null && bArr.length > 0) {
            str = new String(bArr, "utf-8");
        } else {
            str = this.mResults.size() <= 0 ? null : "";
        }
        getSessionID();
        this.mResults.add(str);
        if (this.mListener != null && isRunning()) {
            TranscripterResult transcripterResult = new TranscripterResult(str);
            PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
            this.mListener.onResult(transcripterResult, z);
        }
        DebugLog.LogD("msc result time:" + System.currentTimeMillis());
        if (z) {
            exit(null);
        }
    }

    public TranscripterResult getTranscriptResult() throws SpeechError {
        try {
            if (this.mSession.getResultData() != null) {
                return new TranscripterResult(new String(this.mSession.getResultData(), "utf-8"));
            }
            return null;
        } catch (UnsupportedEncodingException e) {
            DebugLog.LogE(e);
            throw new SpeechError(20004);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onParseParam() {
        this.mAudioSource = getParam().m1816a(SpeechConstant.AUDIO_SOURCE, 1);
        this.mMaxSessionTry = getParam().m1816a(SpeechConstant.IST_SESSION_TRY, this.mMaxSessionTry);
        this.mLastServerOffset = getParam().m1816a(SpeechConstant.IST_AUDIO_UPLOADED, 0);
        this.mLastMscOffset = this.mLastServerOffset;
        this.mSessionID = getParam().m1827b("sid", (String) null);
        this.mFilePath = new String(getParam().m1827b(SpeechConstant.IST_AUDIO_PATH, C3693ae.f2977b));
        this.mSyncID = getParam().m1816a(SpeechConstant.IST_SYNC_ID, 0);
        super.onParseParam();
    }

    private void releaseRecord() {
        PcmRecorder pcmRecorder = this.mRecorder;
        if (pcmRecorder != null) {
            pcmRecorder.stopRecord(getParam().m1825a("record_force_stop", false));
            this.mRecorder = null;
            this.mPerflog.m2256a("rec_close");
            try {
                if (this.mAccesser != null) {
                    this.mAccesser.flush();
                }
                this.mSessionRetryCount = 0;
                if (this.mUseBluetooth) {
                    stopBluetooth();
                }
            } catch (Exception e) {
                exit(new SpeechError(e));
                return;
            }
        }
        this.mIsRecording = false;
    }

    void proc_Msg_Result(Message message) throws SpeechError, InterruptedException, UnsupportedEncodingException {
        int i = message.arg1;
        byte[] bArr = (byte[]) message.obj;
        if (i == 0) {
            if (!this.mFirstDataArrival) {
                this.mFirstDataArrival = true;
                this.mPerflog.m2256a("app_frs");
            }
            notifyResult(false, bArr);
            return;
        }
        if (i == 2 || i != 5) {
            return;
        }
        if (!this.mFirstDataArrival) {
            this.mFirstDataArrival = true;
            this.mPerflog.m2256a("app_frs");
        }
        this.mPerflog.m2256a("app_lrs");
        notifyResult(true, bArr);
    }

    protected void uploadData(byte[] bArr, int i, boolean z) throws SpeechError {
        if (!this.mFirstDataWrite) {
            this.mFirstDataWrite = true;
            this.mPerflog.m2256a("app_fau");
        }
        this.mSession.pushAudioData(bArr, i);
        if (z) {
            int audioVolume = this.mSession.getAudioVolume();
            DebugLog.LogI("QISRAudioWrite volume:" + audioVolume);
            callbackVolume(bArr, audioVolume);
        }
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (bArr == null || i2 <= 0 || bArr.length < i2 || i2 <= 0 || !isRunning()) {
            return;
        }
        if (!this.mFirstRecordAudio) {
            this.mFirstRecordAudio = true;
            this.mPerflog.m2256a("rec_start");
        }
        try {
            if (this.mAccesser == null) {
                if (-1 == this.mAudioSource) {
                    this.mAccesser = AudioAccessor.createBufferAccessor();
                } else if (-2 == this.mAudioSource) {
                    this.mAccesser = AudioAccessor.createReadOnlyAccessor(this.mFilePath);
                    getParam().m1823a("sample_rate", this.mAccesser.getAudioInfo(AudioAccessor.AudioKeys.RATE), false);
                }
            }
            this.mAccesser.putAudio(bArr, i2);
            updateCatchLeft();
            if (this.mIsProcRecordDataOn) {
                return;
            }
            this.mIsProcRecordDataOn = true;
            sendMsg(obtainMessage(2, null));
        } catch (Exception e) {
            DebugLog.LogE(e);
            exit(new SpeechError(e));
        }
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onError(SpeechError speechError) {
        exit(speechError);
    }

    @Override // com.iflytek.cloud.record.PcmRecorder.PcmRecordListener
    public void onRecordStarted(boolean z) {
        this.mPerflog.m2256a("rec_ready");
        this.mIsRecording = z;
    }

    public C3742u getPerfLog() {
        return this.mPerflog;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getSessionID() {
        if (TextUtils.isEmpty(this.mSessionID)) {
            this.mSessionID = this.mSession.getSessionID();
        }
        return this.mSessionID;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getClientID() {
        return this.mSession.getClientID();
    }

    private boolean isRecordMode() {
        int i = this.mAudioSource;
        return (-1 == i || -2 == i) ? false : true;
    }

    private void assertSessionTimes() throws SpeechError {
        if ((!isRecordMode() || (isRecordMode() && !isRecording())) && getMaxSessionTry() != -1 && getMaxSessionTry() <= this.mSessionRetryCount) {
            DebugLog.LogE("proc_Msg_Session_Begin max session try:" + getMaxSessionTry());
            throw new SpeechError(this.mError);
        }
    }

    private boolean isRecording() {
        DebugLog.LogE("isRecording:" + this.mIsRecording);
        return this.mIsRecording;
    }

    private boolean hasMoreData() {
        DebugLog.LogE("hasMoreData >>> isRecording ? " + isRecording() + " && datalength == " + getDataLength());
        return isRecording() || getDataLength() > ((long) this.mLastMscOffset);
    }

    private synchronized void restartSession() throws SpeechError {
        if (this.mIsRestarting) {
            return;
        }
        assertSessionTimes();
        updateUploadBytes();
        updateSessionID();
        updateSyncID();
        this.mSession.sessionEnd("restart");
        this.mLastMscOffset = this.mLastServerOffset;
        this.mSession.mClientID = null;
        if (this.mSessionID != null) {
            getParam().m1822a("sid", this.mSessionID);
            getParam().m1823a(SpeechConstant.IST_AUDIO_UPLOADED, Integer.toString(this.mLastServerOffset), true);
            getParam().m1823a(SpeechConstant.IST_SYNC_ID, Integer.toString(this.mSyncID), true);
        }
        sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
        this.mIsRestarting = true;
    }

    private int getMaxSessionTry() {
        return this.mMaxSessionTry;
    }

    private long getDataLength() {
        if (this.mAccesser != null) {
            DebugLog.LogD("getDataLength len=" + this.mAccesser.getDataLength());
            return this.mAccesser.getDataLength();
        }
        DebugLog.LogE("getDataLength file accesser is null.");
        return -1L;
    }

    private void closeAudioFile() throws IOException {
        AudioAccessor audioAccessor = this.mAccesser;
        if (audioAccessor != null) {
            audioAccessor.close();
            this.mAccesser = null;
        }
    }

    private int getAudioData() throws SpeechError, IOException {
        AudioAccessor audioAccessor = this.mAccesser;
        if (audioAccessor != null) {
            return audioAccessor.getAudio(this.mBuffer);
        }
        return 0;
    }

    private boolean isSessionOn() {
        return this.mSession.mClientID != null;
    }

    public int getUploadBytes() {
        return this.mLastServerOffset;
    }

    private void updateSessionID() {
        if (this.mSessionID == null) {
            this.mSessionID = getSessionID();
            if (this.mSessionID == null || this.mListener == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, this.mSessionID);
            this.mListener.onEvent(20001, 0, 0, bundle);
        }
    }

    private void updateUploadBytes() {
        int intValue = this.mSession.getIntValue(SpeechConstant.IST_AUDIO_UPLOADED, this.mLastServerOffset);
        DebugLog.LogS("updateUploadBytes", "uploadLen=" + intValue);
        if (intValue != this.mLastServerOffset) {
            this.mLastServerOffset = intValue;
            if (this.mListener != null) {
                int dataLength = -1 == getAudioSource() ? (int) getDataLength() : -1;
                boolean z = !isRecording() && getDataLength() == ((long) this.mLastServerOffset);
                Bundle bundle = new Bundle();
                bundle.putBoolean(SpeechEvent.KEY_EVENT_IST_UPLOAD_COMPLETE, z);
                this.mListener.onEvent(SpeechEvent.EVENT_IST_UPLOAD_BYTES, intValue, dataLength, bundle);
            }
        }
    }

    private void updateCatchLeft() {
        if (isRecordMode() || this.mListener == null || this.mAccesser == null) {
            return;
        }
        this.mListener.onEvent(SpeechEvent.EVENT_IST_CACHE_LEFT, this.mAccesser.getCacheLeft(), 0, null);
    }

    public int getSyncID() {
        return this.mSyncID;
    }

    private void updateSyncID() {
        int intValue = this.mSession.getIntValue(SpeechConstant.IST_SYNC_ID, this.mSyncID);
        DebugLog.LogS("updateSyncID", "syncid=" + intValue);
        if (intValue != this.mSyncID) {
            this.mSyncID = intValue;
            if (this.mListener != null) {
                this.mListener.onEvent(SpeechEvent.EVENT_IST_SYNC_ID, intValue, 0, null);
            }
        }
    }

    public String getAudioPath() {
        AudioAccessor audioAccessor = this.mAccesser;
        if (audioAccessor != null) {
            return audioAccessor.getFilePath();
        }
        return null;
    }

    private void stopWriteAutio() throws SpeechError {
        this.mSession.pushEndFlag();
        sendMsg(obtainMessage(MSG_WAIT_RESULT, null));
    }

    protected void proc_Msg_Wait_Result() throws Exception {
        updateUploadBytes();
        updateSessionID();
        updateSyncID();
        int intValue = this.mSession.getIntValue(C3693ae.f2976a, -1);
        DebugLog.LogD("onWaiting msc waitTime=" + intValue);
        if (intValue >= 0 && intValue != this.mResultSecondLeft) {
            this.mStatusBegin = System.currentTimeMillis();
            this.mResultSecondLeft = intValue;
            if (this.mListener != null) {
                this.mListener.onEvent(SpeechEvent.EVENT_IST_RESULT_TIME, intValue / 1000, 0, null);
            }
        }
        AbstractC3741t.a aVar = AbstractC3741t.a.noResult;
        try {
            aVar = requestResult();
        } catch (SpeechError e) {
            if (10114 == e.getErrorCode()) {
                restartSession();
            } else {
                throw e;
            }
        }
        if (this.mResultSecondLeft >= 0 || AbstractC3741t.a.noResult != aVar) {
            timeOutCheck(this.mStatusBegin, this.mResultSecondLeft + this.mNetTimeOut);
        }
        sendMsg(MSG_WAIT_RESULT, AbstractHandlerC3740s.a.max, false, 500);
    }

    protected AbstractC3741t.a requestResult() throws SpeechError, UnsupportedEncodingException {
        AbstractC3741t.a status = this.mSession.getStatus();
        byte[] bytes = (AbstractC3741t.a.noResult == status || this.mSession.getResultData() == null || this.mSession.getResultData().length <= 0) ? null : new String(this.mSession.getResultData(), "gb2312").getBytes("utf-8");
        DebugLog.LogD("result:" + bytes);
        Message obtainMessage = obtainMessage(4, status.ordinal(), 0, bytes);
        if (hasMessages(4)) {
            sendMsg(obtainMessage, AbstractHandlerC3740s.a.normal, false, 0);
        } else {
            sendMsg(obtainMessage, AbstractHandlerC3740s.a.max, false, 0);
        }
        return status;
    }
}
