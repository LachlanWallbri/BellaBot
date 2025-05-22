package com.pudutech.mic_array.mic;

import android.content.Context;
import com.iflytek.iflyos.cae.CAE;
import com.pudutech.base.Pdlog;
import com.pudutech.getusbdev.lib.UsbDeviceHelper;
import com.pudutech.getusbdev.lib.UsbDeviceType;
import com.pudutech.mic_array.cae.CaeOperator;
import com.pudutech.mic_array.cae.OnCaeOperatorlistener;
import com.pudutech.mic_array.recorder.RecOperator;
import com.pudutech.mic_array.recorder.RecordListener;
import com.pudutech.mic_array.util.RecordAudioUtil;
import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class MicArray {
    private static final String TAG = MicArray.class.getSimpleName();
    private static boolean isRecording = false;
    private static int mPcmCard;
    private static int mPcmDevice;
    private static String mPid;
    private static String mVid;
    private IAudioListener mAudioListener;
    private CaeOperator mCaeOperator;
    private Thread mHandleAudioThread;
    private RecOperator mRecOperator;
    private String mTinyMix;
    private UsbDeviceHelper mUsbDevice;
    private IWakeupListener mWakeupListener;
    private int ret = 0;
    private final int max = 111111;
    private String result = null;
    private boolean flag = false;

    /* renamed from: sr */
    private int f5514sr = 0;
    private String vtnFilename = "/sdcard/vtn/cae/resources/config/vtn.ini";
    private OnCaeOperatorlistener onCaeOperatorlistener = new OnCaeOperatorlistener() { // from class: com.pudutech.mic_array.mic.MicArray.1
        @Override // com.pudutech.mic_array.cae.OnCaeOperatorlistener
        public void onAudio(byte[] bArr, int i) {
            if (bArr == null || MicArray.this.mWakeupListener == null) {
                return;
            }
            MicArray.this.mWakeupListener.onWakeMessage(bArr);
        }

        @Override // com.pudutech.mic_array.cae.OnCaeOperatorlistener
        public void onWakeup(int i, int i2, String str, int i3) {
            Pdlog.m3273d(MicArray.TAG, "唤醒成功,angle:" + i + " beam:" + i2 + " keyword：" + str + "score:" + i3);
            if (MicArray.this.mWakeupListener != null) {
                MicArray.this.mWakeupListener.onWakeSuccess(i, i2, str, i3);
            }
        }
    };
    private RecordListener onRecordListener = new RecordListener() { // from class: com.pudutech.mic_array.mic.MicArray.2
        @Override // com.pudutech.mic_array.recorder.RecordListener
        public void onPcmData(byte[] bArr) {
            if (MicArray.this.mAudioListener != null) {
                MicArray.this.mAudioListener.onRawData(bArr);
            }
            byte[] adapeter1C = RecordAudioUtil.adapeter1C(bArr);
            if (MicArray.this.mAudioListener != null) {
                MicArray.this.mAudioListener.onSingleData(adapeter1C);
            }
            MicArray.this.mCaeOperator.writeAudioTest(bArr);
        }
    };

    public boolean isFreeWake() {
        return false;
    }

    public MicArray(Context context) {
        CaeOperator.portingFile(context);
    }

    public int initSDK(String str, String str2, String str3, String str4) {
        return initSDK(str, str2, str3, str4, false);
    }

    public int initSDK(String str, String str2, String str3, String str4, Boolean bool) {
        Pdlog.m3275i(TAG, "Initialization started...");
        mPid = str;
        mVid = str2;
        this.mTinyMix = str3;
        this.mUsbDevice = UsbDeviceHelper.INSTANCE;
        if (this.result == null) {
            this.result = this.mUsbDevice.getUsbDevice(str, str2, UsbDeviceType.PCM);
        }
        Pdlog.m3273d("UsbLib", "result=" + this.result);
        String str5 = this.result;
        if (str5 == null) {
            return -1;
        }
        char[] charArray = str5.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 'C') {
                mPcmCard = charArray[i + 1] - '0';
            } else if (charArray[i] == 'D') {
                mPcmDevice = charArray[i + 1] - '0';
            }
        }
        if (mPcmCard != 0 || bool.booleanValue()) {
            if (!this.flag) {
                int initCaeEngine = initCaeEngine(str4);
                initAlsa(mPcmCard, mPcmDevice, this.mTinyMix);
                if (initCaeEngine == 0) {
                    Pdlog.m3275i(TAG, "Initialization succeeded...");
                    this.flag = true;
                    return 0;
                }
                Pdlog.m3275i(TAG, "initialization failed... ");
                Pdlog.m3275i(TAG, "CAE initialization failed...error message:" + initCaeEngine);
                return initCaeEngine;
            }
            Pdlog.m3275i(TAG, "Initialized...");
            return -2;
        }
        Pdlog.m3275i(TAG, "initialization failed... Device not found");
        return -3;
    }

    private int initCaeEngine(String str) {
        this.mCaeOperator = new CaeOperator();
        this.ret = this.mCaeOperator.initCAE(this.onCaeOperatorlistener, str);
        int i = this.ret;
        if (i == 0) {
            return 0;
        }
        return i;
    }

    private void initAlsa(int i, int i2, String str) {
        this.mRecOperator = new RecOperator();
        this.mRecOperator.initRec(this.onRecordListener, i, i2, str);
    }

    public void reInitAlsa(String str) {
        this.mTinyMix = str;
        this.mRecOperator = new RecOperator();
        this.mRecOperator.initRec(this.onRecordListener, mPcmCard, mPcmDevice, this.mTinyMix);
    }

    public int replaceWakeup(String str) {
        Pdlog.m3275i(TAG, "The change of wake-up words began...");
        if (str == null) {
            return -1;
        }
        if (new VoiceWakeup().replaceWakeup(this.vtnFilename, str)) {
            Pdlog.m3275i(TAG, "Wake up word replacement succeeded... ");
            return 0;
        }
        Pdlog.m3275i(TAG, "Wake up etymology file does not exist... ");
        return -2;
    }

    public int startRecord(IWakeupListener iWakeupListener, IAudioListener iAudioListener) {
        RecOperator recOperator;
        if (this.flag) {
            Pdlog.m3275i(TAG, "The recording began... ");
            if (isRecording || (recOperator = this.mRecOperator) == null) {
                return -4;
            }
            this.mWakeupListener = iWakeupListener;
            this.mAudioListener = iAudioListener;
            this.ret = recOperator.startRecord();
            int i = this.ret;
            if (i == 0) {
                isRecording = true;
                this.flag = false;
                this.f5514sr = 1;
                Pdlog.m3275i(TAG, "Open recording successfully... ");
                return 0;
            }
            if (111111 == i) {
                Pdlog.m3275i(TAG, "Alsa library is empty... ");
                return -1;
            }
            Pdlog.m3275i(TAG, "Failed to start recording...");
            return -2;
        }
        if (this.f5514sr == 0) {
            Pdlog.m3275i(TAG, "Uninitialized...");
            return -3;
        }
        Pdlog.m3275i(TAG, "Recording turned on... ");
        return -4;
    }

    public int startWakeup(int i) {
        CaeOperator caeOperator = this.mCaeOperator;
        if (caeOperator == null) {
            return -1;
        }
        return caeOperator.setRealBeam(i);
    }

    public boolean stopRecord() {
        if (!this.flag && this.f5514sr == 1) {
            if (isRecording && this.mRecOperator != null) {
                Pdlog.m3275i(TAG, "Stop recording and start... ");
                Thread thread = (Thread) getValue(this.mRecOperator.getAlsaRecorder(), "mPcmReadThread");
                this.mCaeOperator.stop();
                this.mRecOperator.stopRecord();
                isRecording = false;
                this.flag = true;
                this.f5514sr = 0;
                int i = 0;
                while (i < 50) {
                    boolean isInterrupted = (thread == null || !thread.isAlive()) ? true : thread.isInterrupted();
                    Pdlog.m3275i(TAG, "end=" + isInterrupted);
                    if (isInterrupted) {
                        Pdlog.m3275i(TAG, "Recording stopped successfully! ");
                        this.flag = true;
                        Thread thread2 = this.mHandleAudioThread;
                        if (thread2 != null) {
                            thread2.interrupt();
                        }
                        i = 50;
                    }
                    i++;
                }
            }
            return true;
        }
        Pdlog.m3275i(TAG, "Recording not started... ");
        return false;
    }

    private Object getValue(Object obj, String str) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            try {
                return declaredField.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void unInit() {
        if (this.mCaeOperator == null) {
            return;
        }
        CAE.CAEDestory();
        this.mRecOperator.alsaDestory();
    }

    public int wakeUpEnable(boolean z) {
        CaeOperator caeOperator = this.mCaeOperator;
        if (caeOperator == null) {
            Pdlog.m3274e(TAG, "mCaeOperator is null, cannot operate  wakeupEnable!");
            return -1;
        }
        return caeOperator.wakeUpEnable(z);
    }
}
