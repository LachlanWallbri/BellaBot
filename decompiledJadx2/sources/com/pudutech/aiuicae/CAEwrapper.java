package com.pudutech.aiuicae;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.alsa.AlsaRecorder;
import com.iflytek.iflyos.cae.CAE;
import com.iflytek.iflyos.cae.ICAEListener;
import com.pudutech.aiuicae.AIUIWrapper;
import com.pudutech.aiuicae.util.PcmFileUtil;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class CAEwrapper {
    private static String PARAM_AUTH_SN_DEF = "dc95607b-b1c5-4a1f-94ad-2ec6756fdb36";
    private static final int mPcmChannel = 8;
    private static final int mPcmDevice = 0;
    private static final int mPcmFormat = 0;
    private static final int mPcmPeriodCount = 8;
    private static final int mPcmPeriodSize = 1536;
    private static final int mPcmSampleRate = 16000;
    private static final String sTag = "CAEWrapper";
    private AlsaRecorder mAlsaRecorder;
    Context mContext;
    PcmFileUtil mRaChennel_0;
    PcmFileUtil mRaChennel_1;
    PcmFileUtil mRaChennel_2;
    PcmFileUtil mRaChennel_3;
    PcmFileUtil mRaChennel_4;
    PcmFileUtil mRaChennel_5;
    PcmFileUtil mRaChennel_6;
    PcmFileUtil mRaChennel_7;
    PcmFileUtil mRawFileUtil;
    PcmFileUtil mWakeFileUtil;
    private OnStateChangedListener onChangedListener;
    private int mPcmCard = 1;
    JSONObject mSyn = new JSONObject();
    private boolean mWakeup = false;
    private boolean mRecording = false;
    private final int CMD_ID = 100;
    private boolean isAIUI = false;
    private boolean ismWakeupFile = false;
    String mWakeAudioDir = "/sdcard/CAEWakeAudio/";
    String mRawAudioDir = "/sdcard/CAERawAudio/";
    String mChannelAudioDir = "/sdcard/CAEChannelAudio/";
    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.aiuicae.CAEwrapper.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 100) {
                if (CAEwrapper.this.mAlsaRecorder.isRecording()) {
                    CAEwrapper.this.handler.sendEmptyMessageDelayed(100, 10L);
                    return false;
                }
                if (CAEwrapper.this.mRecording) {
                    CAEwrapper.this.mAlsaRecorder.startRecording(CAEwrapper.this.mPcmListener);
                }
            }
            return true;
        }
    });
    private int mState = 2;
    String mResPath = "/sdcard/ivw60/res.bin";
    AIUIWrapper mAUUIWrapper = new AIUIWrapper();
    ICAEListener mCAEListener = new ICAEListener() { // from class: com.pudutech.aiuicae.CAEwrapper.2
        @Override // com.iflytek.iflyos.cae.ICAEListener
        public void onIvwAudioCallback(byte[] bArr, int i) {
        }

        @Override // com.iflytek.iflyos.cae.ICAEListener
        public void onWakeup(int i, int i2) {
            Pdlog.m3275i(CAEwrapper.sTag, "Wake up: " + i + " beam" + i2);
            CAEwrapper.this.mWakeFileUtil.createChannelPcmFile("wakeup");
            CAEwrapper.this.ismWakeupFile = true;
            Pdlog.m3275i("test", "reset" + CAEwrapper.this.mState);
            CAEwrapper.this.resetCAE();
            CAEwrapper.this.mWakeup = true;
            CAE.CAESetRealBeam(i2);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("angle", i);
                jSONObject.put("beam", i2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CAEwrapper.this.updateWakeUp(jSONObject.toString());
        }

        @Override // com.iflytek.iflyos.cae.ICAEListener
        public void onAudioCallback(byte[] bArr, int i) {
            CAEwrapper.this.mWakeFileUtil.write(bArr);
            if (CAEwrapper.this.mWakeup && CAEwrapper.this.isAIUI) {
                Pdlog.m3275i(CAEwrapper.sTag, "start recording");
                CAEwrapper.this.mAUUIWrapper.startRecordAudio(bArr);
            }
        }
    };
    AlsaRecorder.PcmListener mPcmListener = new AlsaRecorder.PcmListener() { // from class: com.pudutech.aiuicae.CAEwrapper.3
        @Override // com.iflytek.alsa.AlsaRecorder.PcmListener
        public void onPcmData(byte[] bArr, int i) {
            byte[] bArr2 = new byte[bArr.length / 8];
            CAEwrapper.this.mRaChennel_0.write(CAEwrapper.detachChannel(bArr, 0));
            CAEwrapper.this.mRaChennel_1.write(CAEwrapper.detachChannel(bArr, 1));
            CAEwrapper.this.mRaChennel_2.write(CAEwrapper.detachChannel(bArr, 2));
            CAEwrapper.this.mRaChennel_3.write(CAEwrapper.detachChannel(bArr, 3));
            CAEwrapper.this.mRaChennel_4.write(CAEwrapper.detachChannel(bArr, 4));
            CAEwrapper.this.mRaChennel_5.write(CAEwrapper.detachChannel(bArr, 5));
            CAEwrapper.this.mRaChennel_6.write(CAEwrapper.detachChannel(bArr, 6));
            CAEwrapper.this.mRaChennel_7.write(CAEwrapper.detachChannel(bArr, 7));
            byte[] addMICChannel = CAEwrapper.this.addMICChannel(bArr);
            synchronized (CAEwrapper.this.mSyn) {
                if (CAE.isLibLoaded()) {
                    CAE.CAEAudioWrite(addMICChannel, addMICChannel.length);
                    CAEwrapper.this.mRawFileUtil.write(bArr);
                }
            }
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes.dex */
    public interface OnStateChangedListener {
        void onResultChanged(String str);

        void onStateChanged(int i);

        void onTextChanged(String str);

        void onVadChanged(int i);

        void onWakeChanged(String str);
    }

    public boolean initCAEEngine(Context context, boolean z, boolean z2, int i) {
        if (z) {
            Pdlog.m3275i(sTag, "CAEEngine init...");
            if (i == 1) {
                try {
                    if (!new File("/dev/snd/pcmC1D0c").exists()) {
                        Pdlog.m3274e(sTag, "can't find pcm device");
                        return false;
                    }
                    Tools.execCommand("chmod 777 /dev/snd/pcmC1D0c", true);
                    Tools.execCommand("tinymix -D 1 3 25 25 25 25 25 25", true);
                } catch (Exception e) {
                    Pdlog.m3272a(sTag, "initCAEEngine failed, reason:" + e.getMessage());
                }
            } else if (i == 2) {
                try {
                    if (!new File("/dev/snd/pcmC2D0c").exists()) {
                        Pdlog.m3274e(sTag, "can't find pcm device");
                        return false;
                    }
                    Tools.execCommand("chmod 777 /dev/snd/pcmC2D0c", true);
                    Tools.execCommand("tinymix -D 1 3 25 25 25 25 25 25", true);
                } catch (Exception e2) {
                    Pdlog.m3272a(sTag, "initCAEEngine failed, reason:" + e2.getMessage());
                }
            } else {
                Pdlog.m3274e(sTag, "can't find pcm device");
                return false;
            }
            this.mPcmCard = i;
            copyAssetstoSDcard(context, "cfg/res.bin", "/sdcard/ivw60", "res.bin");
        }
        this.mRawFileUtil = new PcmFileUtil(this.mRawAudioDir);
        this.mWakeFileUtil = new PcmFileUtil(this.mWakeAudioDir);
        this.mRaChennel_1 = new PcmFileUtil(this.mChannelAudioDir);
        this.mRaChennel_2 = new PcmFileUtil(this.mChannelAudioDir);
        this.mRaChennel_3 = new PcmFileUtil(this.mChannelAudioDir);
        this.mRaChennel_4 = new PcmFileUtil(this.mChannelAudioDir);
        this.mRaChennel_5 = new PcmFileUtil(this.mChannelAudioDir);
        this.mRaChennel_6 = new PcmFileUtil(this.mChannelAudioDir);
        this.mRaChennel_7 = new PcmFileUtil(this.mChannelAudioDir);
        this.mRaChennel_0 = new PcmFileUtil(this.mChannelAudioDir);
        this.mWakeup = false;
        this.mContext = context;
        CAE.loadLib();
        if (CAE.CAENew(this.mResPath, this.mCAEListener) != 0) {
            Pdlog.m3274e(sTag, "CAE init fail");
            return false;
        }
        if (CAE.CAEAuth(PARAM_AUTH_SN_DEF) == 0) {
            Pdlog.m3273d(sTag, "鉴权成功");
            AlsaRecorder createInstance = AlsaRecorder.createInstance(this.mPcmCard, 0, 8, 16000, mPcmPeriodSize, 8, 0);
            this.mAlsaRecorder = createInstance;
            AIUIWrapper aIUIWrapper = this.mAUUIWrapper;
            if (aIUIWrapper == null || createInstance == null) {
                Pdlog.m3274e(sTag, "AIUI init fail");
                return false;
            }
            if (z2) {
                this.isAIUI = true;
                aIUIWrapper.initAIUIAgent(context);
                this.mAUUIWrapper.setOnStateChangedListener(new AIUIWrapper.OnStateChangedListener() { // from class: com.pudutech.aiuicae.CAEwrapper.4
                    @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
                    public void onStateChanged(int i2) {
                        CAEwrapper.this.updateState(i2);
                        CAEwrapper.this.mState = i2;
                        AIUIWrapper aIUIWrapper2 = CAEwrapper.this.mAUUIWrapper;
                        if (i2 != 4) {
                            AIUIWrapper aIUIWrapper3 = CAEwrapper.this.mAUUIWrapper;
                            if (i2 != 5) {
                                AIUIWrapper aIUIWrapper4 = CAEwrapper.this.mAUUIWrapper;
                                if (i2 != 20001) {
                                    AIUIWrapper aIUIWrapper5 = CAEwrapper.this.mAUUIWrapper;
                                    if (i2 != 10120) {
                                        return;
                                    }
                                }
                            }
                        }
                        CAEwrapper.this.mWakeup = false;
                        Pdlog.m3275i(CAEwrapper.sTag, "STOP RECOGNIZE");
                    }

                    @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
                    public void onTextChanged(String str) {
                        CAEwrapper.this.updateText(str);
                        Pdlog.m3275i(CAEwrapper.sTag, "Text Change:" + str);
                    }

                    @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
                    public void onResultChange(String str) {
                        CAEwrapper.this.updateResult(str);
                        Pdlog.m3275i(CAEwrapper.sTag, "Result Change:" + str);
                    }

                    @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
                    public void onVadChange(int i2) {
                        CAEwrapper.this.updateVad(i2);
                        Pdlog.m3275i(CAEwrapper.sTag, "Vol Change:" + i2);
                    }
                });
                Pdlog.m3275i(sTag, "AIUIinit success");
            }
            Pdlog.m3275i(sTag, "CAEinit success");
            return true;
        }
        Pdlog.m3274e(sTag, "鉴权失败");
        return false;
    }

    public void startRecording() {
        if (this.mAlsaRecorder == null || this.mRecording) {
            return;
        }
        this.mRawFileUtil.createPcmFile();
        this.mRaChennel_1.createChannelPcmFile("channel_1");
        this.mRaChennel_2.createChannelPcmFile("channel_2");
        this.mRaChennel_3.createChannelPcmFile("channel_3");
        this.mRaChennel_4.createChannelPcmFile("channel_4");
        this.mRaChennel_5.createChannelPcmFile("channel_5");
        this.mRaChennel_6.createChannelPcmFile("channel_6");
        this.mRaChennel_7.createChannelPcmFile("channel_7");
        this.mRaChennel_0.createChannelPcmFile("channel_0");
        this.mAlsaRecorder.startRecording(this.mPcmListener);
        this.mRecording = true;
    }

    public void stopRecording() {
        AlsaRecorder alsaRecorder = this.mAlsaRecorder;
        if (alsaRecorder != null) {
            this.mRecording = false;
            alsaRecorder.stopRecording();
            this.mRaChennel_0.closeWriteFile();
            convertPcm2Wav("/sdcard/CAEChannelAudio/channel_0.pcm", "/sdcard/CAEChannelAudio/channel_0.wav", 16000, 1, 16);
            this.mRaChennel_1.closeWriteFile();
            convertPcm2Wav("/sdcard/CAEChannelAudio/channel_1.pcm", "/sdcard/CAEChannelAudio/channel_1.wav", 16000, 1, 16);
            this.mRaChennel_2.closeWriteFile();
            convertPcm2Wav("/sdcard/CAEChannelAudio/channel_2.pcm", "/sdcard/CAEChannelAudio/channel_2.wav", 16000, 1, 16);
            this.mRaChennel_3.closeWriteFile();
            convertPcm2Wav("/sdcard/CAEChannelAudio/channel_3.pcm", "/sdcard/CAEChannelAudio/channel_3.wav", 16000, 1, 16);
            this.mRaChennel_4.closeWriteFile();
            convertPcm2Wav("/sdcard/CAEChannelAudio/channel_4.pcm", "/sdcard/CAEChannelAudio/channel_4.wav", 16000, 1, 16);
            this.mRaChennel_5.closeWriteFile();
            convertPcm2Wav("/sdcard/CAEChannelAudio/channel_5.pcm", "/sdcard/CAEChannelAudio/channel_5.wav", 16000, 1, 16);
            this.mRaChennel_6.closeWriteFile();
            convertPcm2Wav("/sdcard/CAEChannelAudio/channel_6.pcm", "/sdcard/CAEChannelAudio/channel_6.wav", 16000, 1, 16);
            this.mRaChennel_7.closeWriteFile();
            convertPcm2Wav("/sdcard/CAEChannelAudio/channel_7.pcm", "/sdcard/CAEChannelAudio/channel_7.wav", 16000, 1, 16);
            this.handler.removeMessages(100);
            this.handler.sendEmptyMessageDelayed(100, 10L);
            if (this.ismWakeupFile) {
                this.mWakeFileUtil.closeWriteFile();
                convertPcm2Wav("/sdcard/CAEWakeAudio/wakeup.pcm", "/sdcard/CAEWakeAudio/wakeup.wav", 16000, 1, 16);
                this.ismWakeupFile = false;
            }
            this.mRawFileUtil.closeWriteFile();
        }
    }

    public void setRealBeam(int i) {
        synchronized (this.mSyn) {
            if (CAE.isLibLoaded()) {
                CAE.CAESetRealBeam(i);
            }
        }
    }

    public void resetCAE() {
        synchronized (this.mSyn) {
            if (this.mAUUIWrapper != null && this.isAIUI) {
                this.mWakeup = false;
                this.mAUUIWrapper.releaseAIUIAgent();
                Pdlog.m3275i(sTag, "RESET CAE");
                this.mAUUIWrapper.initAIUIAgent(this.mContext);
                this.mAUUIWrapper.setOnStateChangedListener(new AIUIWrapper.OnStateChangedListener() { // from class: com.pudutech.aiuicae.CAEwrapper.5
                    @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
                    public void onStateChanged(int i) {
                        CAEwrapper.this.updateState(i);
                        CAEwrapper.this.mState = i;
                        AIUIWrapper aIUIWrapper = CAEwrapper.this.mAUUIWrapper;
                        if (i != 4) {
                            AIUIWrapper aIUIWrapper2 = CAEwrapper.this.mAUUIWrapper;
                            if (i != 5) {
                                return;
                            }
                        }
                        CAEwrapper.this.mWakeup = false;
                        Pdlog.m3275i(CAEwrapper.sTag, "STOP RECOGNIZE");
                    }

                    @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
                    public void onTextChanged(String str) {
                        CAEwrapper.this.updateText(str);
                        Pdlog.m3275i(CAEwrapper.sTag, "Text Change:" + str);
                    }

                    @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
                    public void onResultChange(String str) {
                        CAEwrapper.this.updateResult(str);
                        Pdlog.m3275i(CAEwrapper.sTag, "Result Change:" + str);
                    }

                    @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
                    public void onVadChange(int i) {
                        CAEwrapper.this.updateVad(i);
                        Pdlog.m3275i(CAEwrapper.sTag, "Vol Change:" + i);
                    }
                });
            }
        }
    }

    /* renamed from: com.pudutech.aiuicae.CAEwrapper$6 */
    /* loaded from: classes3.dex */
    class C39826 implements AIUIWrapper.OnStateChangedListener {
        C39826() {
        }

        @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
        public void onStateChanged(int i) {
            CAEwrapper.access$1500(CAEwrapper.this, i);
            CAEwrapper.access$802(CAEwrapper.this, i);
            AIUIWrapper aIUIWrapper = CAEwrapper.this.mAUUIWrapper;
            if (i != 4) {
                AIUIWrapper aIUIWrapper2 = CAEwrapper.this.mAUUIWrapper;
                if (i != 5) {
                    return;
                }
            }
            CAEwrapper.access$402(CAEwrapper.this, false);
            Pdlog.m3275i(CAEwrapper.sTag, "STOP RECOGNIZE");
        }

        public void onTextChanged(String str, boolean z) {
            CAEwrapper.access$1600(CAEwrapper.this, str, z);
            Pdlog.m3275i(CAEwrapper.sTag, "Text Change:" + str);
        }

        @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
        public void onResultChange(String str) {
            CAEwrapper.access$1700(CAEwrapper.this, str);
            Pdlog.m3275i(CAEwrapper.sTag, "Result Change:" + str);
        }

        @Override // com.pudutech.aiuicae.AIUIWrapper.OnStateChangedListener
        public void onVadChange(int i) {
            CAEwrapper.access$1800(CAEwrapper.this, i);
            Pdlog.m3275i(CAEwrapper.sTag, "Vol Change:" + i);
        }

        public void onCmdChange(String str) {
            CAEwrapper.access$1900(CAEwrapper.this, str);
            Pdlog.m3275i(CAEwrapper.sTag, "cmd Change:" + str);
        }
    }

    public void releaseCAE() {
        synchronized (this.mSyn) {
            stopRecording();
            if (this.mAUUIWrapper != null) {
                this.mAUUIWrapper.releaseAIUIAgent();
            }
            if (CAE.isLibLoaded()) {
                CAE.CAEDestory();
            }
            if (this.mAlsaRecorder != null) {
                this.mAlsaRecorder.destroy();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateState(int i) {
        OnStateChangedListener onStateChangedListener = this.onChangedListener;
        if (onStateChangedListener != null) {
            onStateChangedListener.onStateChanged(i);
        }
        Pdlog.m3275i(sTag, "UPDATE STATE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateText(String str) {
        OnStateChangedListener onStateChangedListener = this.onChangedListener;
        if (onStateChangedListener != null) {
            onStateChangedListener.onTextChanged(str);
        }
        Pdlog.m3275i(sTag, "UPDATE TEXT");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateResult(String str) {
        OnStateChangedListener onStateChangedListener = this.onChangedListener;
        if (onStateChangedListener != null) {
            onStateChangedListener.onResultChanged(str);
        }
        Pdlog.m3275i(sTag, "UPDATE RESULT");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWakeUp(String str) {
        OnStateChangedListener onStateChangedListener = this.onChangedListener;
        if (onStateChangedListener != null) {
            onStateChangedListener.onWakeChanged(str);
        }
        Pdlog.m3275i(sTag, "UPDATE WAKEUP INFO");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVad(int i) {
        OnStateChangedListener onStateChangedListener = this.onChangedListener;
        if (onStateChangedListener != null) {
            onStateChangedListener.onVadChanged(i);
        }
        Pdlog.m3275i(sTag, "UPDATE VAD");
    }

    public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.onChangedListener = onStateChangedListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] detachChannel(byte[] bArr, int i) {
        byte[] bArr2 = null;
        if (bArr != null && i >= 0 && i <= 7) {
            if (bArr.length <= 0) {
                return null;
            }
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            bArr2 = new byte[copyOf.length / 8];
            int i2 = 0;
            for (int i3 = 0; i3 < copyOf.length; i3 += 16) {
                int i4 = i2 + 1;
                int i5 = (i * 2) + i3;
                bArr2[i2] = copyOf[i5];
                i2 = i4 + 1;
                bArr2[i4] = copyOf[i5 + 1];
            }
        }
        return bArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0063 -> B:14:0x0066). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void convertPcm2Wav(String str, String str2, int i, int i2, int i3) {
        FileOutputStream fileOutputStream;
        long j;
        FileInputStream fileInputStream;
        byte[] bArr = new byte[1024];
        int i4 = i * i2 * i3;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                j = i4 / 8;
                fileInputStream = new FileInputStream(str);
                try {
                    fileOutputStream = new FileOutputStream(str2);
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                }
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
            try {
                long size = fileInputStream.getChannel().size();
                writeWaveFileHeader(fileOutputStream, size, 36 + size, i, i2, j);
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
                fileInputStream.close();
                fileOutputStream.close();
            } catch (Exception e4) {
                e = e4;
                fileInputStream2 = fileInputStream;
                try {
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    if (fileOutputStream == null) {
                        try {
                            fileOutputStream.close();
                            throw th;
                        } catch (IOException e7) {
                            e7.printStackTrace();
                            throw th;
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                }
                if (fileOutputStream == null) {
                }
            }
        } catch (IOException e8) {
            e8.printStackTrace();
        }
    }

    private static void writeWaveFileHeader(FileOutputStream fileOutputStream, long j, long j2, int i, int i2, long j3) throws IOException {
        fileOutputStream.write(new byte[]{82, PrinterUtils.BarCode.CODE128, 70, 70, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, (byte) i2, 0, (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255), (byte) (j3 & 255), (byte) ((j3 >> 8) & 255), (byte) ((j3 >> 16) & 255), (byte) ((j3 >> 24) & 255), (byte) ((i2 * 16) / 8), 0, 16, 0, 100, 97, 116, 97, (byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255)}, 0, 44);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] addMICChannel(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length * 2];
        int i = 0;
        while (i < length / 2) {
            for (int i2 = 1; i2 < 9; i2++) {
                int i3 = i * 4;
                bArr2[i3] = 0;
                bArr2[i3 + 1] = (byte) i2;
                int i4 = i * 2;
                bArr2[i3 + 2] = bArr[i4];
                bArr2[i3 + 3] = bArr[i4 + 1];
                i++;
            }
        }
        return bArr2;
    }

    public static void copyAssetstoSDcard(Context context, String str, String str2, String str3) {
        String str4 = str2 + "/" + str3;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            InputStream open = context.getResources().getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str4);
            byte[] bArr = new byte[7168];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    open.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
