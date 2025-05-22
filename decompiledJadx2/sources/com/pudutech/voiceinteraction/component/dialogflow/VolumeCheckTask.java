package com.pudutech.voiceinteraction.component.dialogflow;

import android.util.Log;
import com.pudutech.voiceinteraction.component.VoiceCommentConfig;
import com.pudutech.voiceinteraction.component.log.LogProxy;
import com.pudutech.voiceinteraction.component.utils.VolumeUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class VolumeCheckTask extends Thread {
    private static final String TAG = VolumeCheckTask.class.getSimpleName();
    private volatile byte[] data;
    private OnLowVolumeCheckCallback mCallback;
    private volatile boolean isActive = false;
    private int deleyTime = 100;
    private int lastDataHashCode = -1;
    private volatile int totalRecordTime = 0;
    private volatile int unMuteCount = 0;
    private volatile int unMuteBeginTime = 0;
    private int maxCount = 1;
    private volatile int continuitiesVoiceCount = 0;
    private int maxContinuitiseVoice = 2;
    private int limitV = 5;

    public VolumeCheckTask(OnLowVolumeCheckCallback onLowVolumeCheckCallback) {
        this.mCallback = onLowVolumeCheckCallback;
    }

    public void active() {
        this.isActive = true;
        onEnd();
        synchronized (this) {
            notify();
        }
    }

    public void inactive() {
        this.isActive = false;
        onEnd();
    }

    public void updateData(byte[] bArr) {
        this.data = bArr;
    }

    private void onEnd() {
        this.totalRecordTime = 0;
        this.unMuteCount = 0;
        this.continuitiesVoiceCount = 0;
        this.unMuteBeginTime = 0;
        this.lastDataHashCode = -1;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            if (!this.isActive) {
                synchronized (this) {
                    onEnd();
                    try {
                        LogProxy.INSTANCE.m3307i(TAG, "isActive = false, wait.");
                        wait();
                    } catch (InterruptedException e) {
                        LogProxy.INSTANCE.m3306e(TAG, Log.getStackTraceString(e));
                    }
                }
            }
            if (this.totalRecordTime >= VoiceCommentConfig.INSTANCE.getVadBosTimeout()) {
                synchronized (this) {
                    if (this.mCallback != null) {
                        if (this.unMuteCount > this.maxCount) {
                            this.mCallback.finish();
                        } else {
                            this.mCallback.timeout();
                        }
                    }
                    LogProxy.INSTANCE.m3307i(TAG, "totalRecordTime = " + this.totalRecordTime + ", unMuteCount = " + this.unMuteCount + ", wait");
                    onEnd();
                    try {
                        wait();
                    } catch (InterruptedException e2) {
                        LogProxy.INSTANCE.m3306e(TAG, Log.getStackTraceString(e2));
                    }
                }
            }
            try {
                sleep(this.deleyTime);
                this.totalRecordTime += this.deleyTime;
            } catch (InterruptedException e3) {
                LogProxy.INSTANCE.m3306e(TAG, Log.getStackTraceString(e3));
            }
            if (this.data != null && this.data.length != 0 && this.data.hashCode() != this.lastDataHashCode) {
                this.lastDataHashCode = this.data.hashCode();
                int computeVolume = VolumeUtil.computeVolume(this.data, this.data.length);
                LogProxy.INSTANCE.m3305d(TAG, "volume = " + computeVolume + ", totalRecordTime = " + this.totalRecordTime + " , unMuteCount = " + this.unMuteCount + " , " + this.data);
                if (computeVolume > this.limitV) {
                    this.continuitiesVoiceCount++;
                    if (this.unMuteCount >= this.maxCount) {
                        this.unMuteBeginTime = 0;
                    }
                } else {
                    this.continuitiesVoiceCount = 0;
                }
                if (this.continuitiesVoiceCount >= this.maxContinuitiseVoice) {
                    this.unMuteCount++;
                    this.totalRecordTime = 0;
                    this.continuitiesVoiceCount = 0;
                    this.unMuteBeginTime = 0;
                }
                if (this.unMuteCount >= this.maxCount) {
                    OnLowVolumeCheckCallback onLowVolumeCheckCallback = this.mCallback;
                    if (onLowVolumeCheckCallback != null) {
                        onLowVolumeCheckCallback.onVolumeChange(computeVolume);
                    }
                    this.unMuteBeginTime += this.deleyTime;
                    if (this.unMuteBeginTime >= 500) {
                        synchronized (this) {
                            if (this.mCallback != null) {
                                this.mCallback.finish();
                            }
                            onEnd();
                            try {
                                LogProxy.INSTANCE.m3307i(TAG, "unMuteBeginTime = " + this.unMuteBeginTime + ", wait");
                                wait();
                            } catch (InterruptedException e4) {
                                e4.printStackTrace();
                            }
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
    }
}
