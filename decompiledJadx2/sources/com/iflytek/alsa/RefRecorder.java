package com.iflytek.alsa;

import android.util.Log;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.alsa.jni.RefJni;

/* loaded from: classes4.dex */
public class RefRecorder {
    private static RefRecorder instance;
    private boolean mIsRecording;
    private PcmListener mPcmListener;
    private PcmReadThread mPcmReadThread;
    private static String TAG = RefRecorder.class.getSimpleName();
    private static int mCard = 2;
    private static int mDevice = 0;
    private static int mChannels = 2;
    private static int mSampleRate = InternalConstant.RATE96K;
    private static int mPeriodSize = 1536;
    private static int mPeriodCount = 8;
    private static int mPcmFormat = 1;
    private static int mBufferSize = 8192;
    private static int ret = 0;
    private long pcmHandle = 0;
    private Object mSyn = new Object();

    /* loaded from: classes4.dex */
    public interface PcmListener {
        void onPcmData(byte[] bArr, int i);
    }

    public static String getVersion() {
        return "1.2";
    }

    public RefRecorder(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        RefJni.loadLib();
        mCard = i;
        mDevice = i2;
        mChannels = i3;
        mSampleRate = i4;
        mPeriodSize = i5;
        mPeriodCount = i6;
        mPcmFormat = i7;
        mBufferSize = i8;
    }

    public static RefRecorder createInstance(int i) {
        return createInstance(i, mDevice, mChannels, mSampleRate, mPeriodSize, mPeriodCount, mPcmFormat);
    }

    public static RefRecorder createInstance(int i, int i2) {
        return createInstance(i, mDevice, mChannels, i2, mPeriodSize, mPeriodCount, mPcmFormat);
    }

    public static RefRecorder createInstance(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (instance == null) {
            instance = new RefRecorder(i, i2, i3, i4, i5, i6, i7, mBufferSize);
        }
        return instance;
    }

    public static RefRecorder createInstance(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (instance == null) {
            instance = new RefRecorder(i, i2, i3, i4, i5, i6, i7, i8);
        }
        return instance;
    }

    public static RefRecorder getInstance() {
        return instance;
    }

    public void setBufferSize(int i) {
        mBufferSize = i;
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
        Thread thread = new Thread() { // from class: com.iflytek.alsa.RefRecorder.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                int unused = RefRecorder.ret = RefJni.pcmOpen(RefRecorder.mCard, RefRecorder.mDevice, RefRecorder.mChannels, RefRecorder.mSampleRate, RefRecorder.mPeriodSize, RefRecorder.mPeriodCount, RefRecorder.mPcmFormat, RefRecorder.this);
                synchronized (RefRecorder.this.mSyn) {
                    RefRecorder.this.mSyn.notify();
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
        if (ret < 0) {
            Log.e(TAG, "open Devices Failed --> ret" + ret);
            return ret;
        }
        long j = this.pcmHandle;
        if (0 != j) {
            if (-1 == mBufferSize) {
                mBufferSize = RefJni.pcm_buffer_size(j) / 2;
            }
            int i = mBufferSize;
            if (RefJni.pcm_start_record(i, i * 8) == 0) {
                this.mIsRecording = true;
            }
            if (this.mPcmReadThread != null) {
                return 0;
            }
            this.mPcmReadThread = new PcmReadThread();
            this.mPcmReadThread.start();
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
        RefJni.setJniLog(z);
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
        return mBufferSize;
    }

    public PcmListener getListener() {
        return this.mPcmListener;
    }

    public void destroy() {
        stopRecording();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
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
                byte[] bArr = new byte[RefRecorder.mBufferSize];
                int pcm_read = RefJni.pcm_read(bArr, bArr.length);
                if (RefRecorder.this.mPcmListener != null && pcm_read != 0) {
                    RefRecorder.this.mPcmListener.onPcmData(bArr, bArr.length);
                }
            }
            RefJni.pcm_close(RefRecorder.this.pcmHandle);
            RefRecorder.this.mIsRecording = false;
            RefRecorder.this.pcmHandle = 0L;
            synchronized (RefRecorder.this.mSyn) {
                RefRecorder.this.mSyn.notify();
            }
        }
    }
}
