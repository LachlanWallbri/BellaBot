package com.iflytek.alsa;

import android.util.Log;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.alsa.jni.AlsaJni;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class AlsaRecorder {
    private static AlsaRecorder instance;
    private boolean mIsRecording;
    private PcmListener mPcmListener;
    private PcmReadThread mPcmReadThread;
    private static String TAG = AlsaRecorder.class.getSimpleName();
    private static int mCard = 2;
    private static int mDevice = 0;
    private static int mChannels = 2;
    private static int mSampleRate = InternalConstant.RATE96K;
    private static int mPeriodSize = 1536;
    private static int mPeriodCount = 8;
    private static int mPcmFormat = 1;
    private static int pcmHandle = 0;
    private int mBufferSize = -1;
    private Object mSyn = new Object();

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public interface PcmListener {
        void onPcmData(byte[] bArr, int i);
    }

    public static String getVersion() {
        return "1.2";
    }

    private AlsaRecorder(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        mCard = i;
        mDevice = i2;
        mChannels = i3;
        mSampleRate = i4;
        mPeriodSize = i5;
        mPeriodCount = i6;
        mPcmFormat = i7;
    }

    public static AlsaRecorder createInstance(int i) {
        return createInstance(i, mDevice, mChannels, mSampleRate, mPeriodSize, mPeriodCount, mPcmFormat);
    }

    public static AlsaRecorder createInstance(int i, int i2) {
        return createInstance(i, mDevice, mChannels, i2, mPeriodSize, mPeriodCount, mPcmFormat);
    }

    public static AlsaRecorder createInstance(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        AlsaJni.loadLib();
        if (instance == null) {
            instance = new AlsaRecorder(i, i2, i3, i4, i5, i6, i7);
        }
        return instance;
    }

    public static AlsaRecorder getInstance() {
        return instance;
    }

    public void setBufferSize(int i) {
        this.mBufferSize = i;
    }

    public int startRecording(PcmListener pcmListener) {
        if (instance == null) {
            Log.e(TAG, "startRecording | AlsaRecorder instance is null.");
            return -1;
        }
        if (this.mIsRecording) {
            Log.e(TAG, "startRecording | be repeatedly called.");
            return -1;
        }
        this.mPcmListener = pcmListener;
        Thread thread = new Thread() { // from class: com.iflytek.alsa.AlsaRecorder.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                AlsaJni.pcmOpen(AlsaRecorder.mCard, AlsaRecorder.mDevice, AlsaRecorder.mChannels, AlsaRecorder.mSampleRate, AlsaRecorder.mPeriodSize, AlsaRecorder.mPeriodCount, AlsaRecorder.mPcmFormat, AlsaRecorder.instance);
                synchronized (AlsaRecorder.this.mSyn) {
                    AlsaRecorder.this.mSyn.notify();
                }
            }
        };
        synchronized (this.mSyn) {
            try {
                thread.start();
                this.mSyn.wait(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int i = pcmHandle;
        if (i != 0) {
            if (-1 == this.mBufferSize) {
                this.mBufferSize = AlsaJni.pcm_buffer_size(i) / 2;
            }
            int i2 = this.mBufferSize;
            if (AlsaJni.pcm_start_record(i2, i2 * 8) == 0) {
                this.mIsRecording = true;
            }
            if (this.mPcmReadThread != null) {
                return 0;
            }
            PcmReadThread pcmReadThread = new PcmReadThread();
            this.mPcmReadThread = pcmReadThread;
            pcmReadThread.start();
            return 0;
        }
        thread.interrupt();
        Log.e("AlsaRecorder", "startRecording | open pcm device failed.");
        return -1;
    }

    public boolean isRecording() {
        return this.mIsRecording;
    }

    public void setLogShow(boolean z) {
        AlsaJni.setJniLog(z);
    }

    public void stopRecording() {
        if (instance == null) {
            Log.e(TAG, "stopRecording | AlsaRecorder instance is null.");
            return;
        }
        PcmReadThread pcmReadThread = this.mPcmReadThread;
        if (pcmReadThread != null) {
            pcmReadThread.stopRun();
            this.mPcmReadThread = null;
            synchronized (this.mSyn) {
                try {
                    this.mSyn.wait(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCardDevId() {
        return mCard;
    }

    public int getSampleRate() {
        return mSampleRate;
    }

    public int getPeriodSize() {
        return mPeriodSize;
    }

    public int getBufferSize() {
        return this.mBufferSize;
    }

    public PcmListener getListener() {
        return this.mPcmListener;
    }

    public void destroy() {
        stopRecording();
        instance = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public class PcmReadThread extends Thread {
        private boolean mStop;

        public PcmReadThread() {
            super("AlsaRecorder-PcmReadThread");
            this.mStop = false;
        }

        public void stopRun() {
            interrupt();
            this.mStop = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            while (!this.mStop) {
                int i = AlsaRecorder.this.mBufferSize;
                byte[] bArr = new byte[i];
                int pcm_read = AlsaJni.pcm_read(bArr, i);
                if (AlsaRecorder.this.mPcmListener != null && pcm_read != 0) {
                    AlsaRecorder.this.mPcmListener.onPcmData(bArr, i);
                }
            }
            AlsaJni.pcm_close(AlsaRecorder.pcmHandle);
            AlsaRecorder.this.mIsRecording = false;
            AlsaRecorder.pcmHandle = 0;
            synchronized (AlsaRecorder.this.mSyn) {
                AlsaRecorder.this.mSyn.notify();
            }
        }
    }
}
