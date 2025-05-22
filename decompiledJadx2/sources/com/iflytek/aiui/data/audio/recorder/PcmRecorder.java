package com.iflytek.aiui.data.audio.recorder;

import android.media.AudioRecord;
import com.iflytek.aiui.error.AIUIError;
import com.iflytek.aiui.pro.C3589as;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class PcmRecorder extends Thread {
    private static final short DEFAULT_CHANNELS = 1;
    public static final int RATE16K = 16000;
    public static final int READ_INTERVAL40MS = 40;
    private static final int RECORD_BUFFER_TIMES_FOR_FRAME = 4;
    private final short DEFAULT_BIT_SAMPLES;
    private double checkDataSum;
    private double checkStandDev;
    private volatile boolean exit;
    private int mAudioSource;
    private byte[] mDataBuffer;
    private int mInterval;
    private PcmRecordListener mOutListener;
    private int mRate;
    private int mReadInterval;
    private AudioRecord mRecorder;
    private PcmRecordListener mStopListener;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public interface PcmRecordListener {
        void onError(AIUIError aIUIError);

        void onRecordBuffer(byte[] bArr, int i, int i2);

        void onRecordReleased();

        void onRecordStarted(boolean z);
    }

    public PcmRecorder(int i, int i2) {
        this(i, i2, 1);
    }

    public PcmRecorder(int i, int i2, int i3) {
        this.DEFAULT_BIT_SAMPLES = (short) 16;
        this.mDataBuffer = null;
        this.mRecorder = null;
        this.mOutListener = null;
        this.mStopListener = null;
        this.exit = false;
        this.checkDataSum = 0.0d;
        this.checkStandDev = 0.0d;
        this.mRate = 16000;
        this.mInterval = 40;
        this.mReadInterval = 40;
        this.mAudioSource = i3;
        this.mRate = i;
        this.mInterval = i2;
        if (i2 < 40 || i2 > 100) {
            this.mInterval = 40;
        }
        this.mReadInterval = 10;
    }

    private double checkAudio(byte[] bArr, int i) {
        double d = 0.0d;
        if (bArr == null || i <= 0) {
            return 0.0d;
        }
        double d2 = 0.0d;
        for (byte b : bArr) {
            d2 += b;
        }
        double length = d2 / bArr.length;
        for (byte b2 : bArr) {
            d += Math.pow(b2 - length, 2.0d);
        }
        return Math.sqrt(d / (bArr.length - 1));
    }

    private int readRecordData() throws AIUIError {
        PcmRecordListener pcmRecordListener;
        AudioRecord audioRecord = this.mRecorder;
        if (audioRecord == null || this.mOutListener == null) {
            return 0;
        }
        byte[] bArr = this.mDataBuffer;
        int read = audioRecord.read(bArr, 0, bArr.length);
        if (read > 0 && (pcmRecordListener = this.mOutListener) != null) {
            pcmRecordListener.onRecordBuffer(this.mDataBuffer, 0, read);
            return read;
        }
        if (read >= 0) {
            return read;
        }
        C3589as.m1062b("Record read data error: " + read);
        throw new AIUIError(20006);
    }

    private void release() {
        synchronized (this) {
            try {
                if (this.mRecorder != null) {
                    C3589as.m1058a("release record begin");
                    this.mRecorder.release();
                    this.mRecorder = null;
                    if (this.mStopListener != null) {
                        this.mStopListener.onRecordReleased();
                        this.mStopListener = null;
                    }
                    C3589as.m1058a("release record over");
                }
            } catch (Exception unused) {
            }
        }
    }

    protected void finalize() throws Throwable {
        release();
        super.finalize();
    }

    protected void initRecord(short s, int i, int i2) throws AIUIError {
        if (this.mRecorder != null) {
            release();
        }
        int i3 = (i2 * i) / 1000;
        int i4 = (((i3 * 4) * 16) * s) / 8;
        int i5 = s == 1 ? 2 : 3;
        int minBufferSize = AudioRecord.getMinBufferSize(i, i5, 2);
        if (i4 < minBufferSize) {
            i4 = minBufferSize;
        }
        this.mRecorder = new AudioRecord(this.mAudioSource, i, i5, 2, i4);
        this.mDataBuffer = new byte[((s * i3) * 16) / 8];
        C3589as.m1058a("\nSampleRate:" + i + "\nChannel:" + i5 + "\nFormat:2\nFramePeriod:" + i3 + "\nBufferSize:" + i4 + "\nMinBufferSize:" + minBufferSize + "\nActualBufferSize:" + this.mDataBuffer.length + "\n");
        if (this.mRecorder.getState() == 1) {
            return;
        }
        C3589as.m1058a("create AudioRecord error");
        throw new AIUIError(20006);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        boolean z;
        int i = 0;
        while (true) {
            try {
                z = true;
                if (this.exit) {
                    break;
                }
                try {
                    initRecord((short) 1, this.mRate, this.mInterval);
                    break;
                } catch (Exception unused) {
                    i++;
                    if (i >= 10) {
                        throw new AIUIError(20006);
                    }
                    sleep(40L);
                }
            } catch (Exception e) {
                C3589as.m1061a(e);
                PcmRecordListener pcmRecordListener = this.mOutListener;
                if (pcmRecordListener != null) {
                    pcmRecordListener.onError(new AIUIError(20006));
                }
            }
        }
        int i2 = 0;
        while (!this.exit) {
            try {
                this.mRecorder.startRecording();
                if (this.mRecorder.getRecordingState() != 3) {
                    throw new AIUIError(20006);
                    break;
                }
                break;
            } catch (Exception unused2) {
                i2++;
                if (i2 >= 10) {
                    throw new AIUIError(20006);
                }
                sleep(40L);
            }
        }
        if (this.mOutListener != null) {
            this.mOutListener.onRecordStarted(true);
        }
        long currentTimeMillis = System.currentTimeMillis();
        while (!this.exit) {
            int readRecordData = readRecordData();
            if (z) {
                this.checkDataSum += readRecordData;
                this.checkStandDev += checkAudio(this.mDataBuffer, this.mDataBuffer.length);
                if (System.currentTimeMillis() - currentTimeMillis >= 1000) {
                    if (this.checkDataSum == 0.0d || this.checkStandDev == 0.0d) {
                        C3589as.m1062b("cannot get record permission, get invalid audio data.");
                        throw new AIUIError(20006);
                    }
                    z = false;
                }
            }
            if (this.mDataBuffer.length > readRecordData) {
                C3589as.m1058a("current record read size is less than buffer size: " + readRecordData);
                sleep((long) this.mReadInterval);
            }
        }
        release();
    }

    public void startRecording(PcmRecordListener pcmRecordListener) throws AIUIError {
        this.mOutListener = pcmRecordListener;
        setPriority(10);
        start();
    }

    public void stopRecord(boolean z) {
        this.exit = true;
        if (this.mStopListener == null) {
            this.mStopListener = this.mOutListener;
        }
        this.mOutListener = null;
        if (z) {
            synchronized (this) {
                try {
                    C3589as.m1058a("stopRecord...release");
                    if (this.mRecorder != null) {
                        if (3 == this.mRecorder.getRecordingState() && 1 == this.mRecorder.getState()) {
                            C3589as.m1058a("stopRecord releaseRecording ing...");
                            this.mRecorder.release();
                            C3589as.m1058a("stopRecord releaseRecording end...");
                            this.mRecorder = null;
                        }
                        if (this.mStopListener != null) {
                            this.mStopListener.onRecordReleased();
                            this.mStopListener = null;
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }
        C3589as.m1058a("stop record");
    }
}
