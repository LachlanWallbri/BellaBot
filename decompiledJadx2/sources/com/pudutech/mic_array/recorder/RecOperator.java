package com.pudutech.mic_array.recorder;

import com.iflytek.alsa.AlsaRecorder;
import com.pudutech.mic_array.util.LogUtils;
import com.pudutech.mic_array.util.RootShell;

/* loaded from: classes5.dex */
public class RecOperator {
    private static final String TAG = RecOperator.class.getSimpleName();
    private static final int mPcmChannel = 8;
    private static final int mPcmFormat = 0;
    private static final int mPcmPeriodCount = 4;
    private static final int mPcmPeriodSize = 3072;
    private static final int mPcmSampleRate = 16000;
    private int Max = 111111;
    AlsaRecorder.PcmListener mAlsaPcmListener = new AlsaRecorder.PcmListener() { // from class: com.pudutech.mic_array.recorder.RecOperator.1
        @Override // com.iflytek.alsa.AlsaRecorder.PcmListener
        public void onPcmData(byte[] bArr, int i) {
            RecOperator.this.mPcmListener.onPcmData(bArr);
        }
    };
    private AlsaRecorder mAlsaRecorder;
    protected RecordListener mPcmListener;

    public AlsaRecorder getAlsaRecorder() {
        return this.mAlsaRecorder;
    }

    public void initRec(RecordListener recordListener, int i, int i2, String str) {
        RootShell.execRootCmdSilent("setenforce 0");
        RootShell.execRootCmdSilent("chmod 777 /dev/snd/pcmC" + i + "D" + i2 + "c");
        RootShell.execRootCmdSilent(str);
        this.mPcmListener = recordListener;
        this.mAlsaRecorder = AlsaRecorder.createInstance(i, i2, 8, 16000, 3072, 4, 0);
        this.mAlsaRecorder.setLogShow(false);
    }

    public void alsaDestory() {
        this.mAlsaRecorder.destroy();
    }

    public int startRecord() {
        AlsaRecorder alsaRecorder = this.mAlsaRecorder;
        if (alsaRecorder != null) {
            int startRecording = alsaRecorder.startRecording(this.mAlsaPcmListener);
            if (startRecording == 0) {
                LogUtils.m3294i(TAG, "start recording sucess...");
            } else {
                LogUtils.m3294i(TAG, "start recording fail...");
            }
            return startRecording;
        }
        LogUtils.m3290d(TAG, "AlsaRecorder is null ...");
        return this.Max;
    }

    public void stopRecord() {
        this.mAlsaRecorder.stopRecording();
    }
}
