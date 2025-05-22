package com.pudutech.peanut.robot_ui.manager;

import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.SystemClock;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdVoicePlayManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001!B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0004J\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u001f\u001a\u00020\u001aJ\u0006\u0010 \u001a\u00020\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0015j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/AdVoicePlayManager;", "", "()V", "TAG", "", "audioEncoding", "", "audioRecord", "Landroid/media/AudioRecord;", "audioTrack", "Landroid/media/AudioTrack;", "channelConfiguration", "frequency", "isInitV", "", "isRecording", "playBufSize", "playThread", "Lcom/pudutech/peanut/robot_ui/manager/AdVoicePlayManager$RecordPlayThread;", "recBufSize", "voiceTag", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "vol", "", "init", "", "lowVolumeMode", "mActivityName", "normalVolumeMode", "setVolume", "startPlay", "stopPlay", "RecordPlayThread", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AdVoicePlayManager {
    private static AudioRecord audioRecord = null;
    private static AudioTrack audioTrack = null;
    private static volatile boolean isInitV;
    private static int playBufSize;
    private static RecordPlayThread playThread;
    private static int recBufSize;
    public static final AdVoicePlayManager INSTANCE = new AdVoicePlayManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final int frequency = frequency;
    private static final int frequency = frequency;
    private static final int channelConfiguration = 3;
    private static final int audioEncoding = 2;
    private static final HashMap<String, String> voiceTag = new HashMap<>();
    private static volatile boolean isRecording = true;
    private static float vol = 1.0f;

    private AdVoicePlayManager() {
    }

    public static final /* synthetic */ AudioRecord access$getAudioRecord$p(AdVoicePlayManager adVoicePlayManager) {
        return audioRecord;
    }

    public static final /* synthetic */ AudioTrack access$getAudioTrack$p(AdVoicePlayManager adVoicePlayManager) {
        return audioTrack;
    }

    public static final /* synthetic */ int access$getPlayBufSize$p(AdVoicePlayManager adVoicePlayManager) {
        return playBufSize;
    }

    public static final /* synthetic */ String access$getTAG$p(AdVoicePlayManager adVoicePlayManager) {
        return TAG;
    }

    public static final /* synthetic */ float access$getVol$p(AdVoicePlayManager adVoicePlayManager) {
        return vol;
    }

    public static final /* synthetic */ boolean access$isInitV$p(AdVoicePlayManager adVoicePlayManager) {
        return isInitV;
    }

    public static final /* synthetic */ boolean access$isRecording$p(AdVoicePlayManager adVoicePlayManager) {
        return isRecording;
    }

    public final void init() {
        Pdlog.m3273d(TAG, "init ");
        recBufSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding);
        playBufSize = AudioTrack.getMinBufferSize(frequency, channelConfiguration, audioEncoding);
        audioRecord = new AudioRecord(5, frequency, channelConfiguration, audioEncoding, recBufSize);
        audioTrack = new AudioTrack(3, frequency, channelConfiguration, audioEncoding, playBufSize, 1);
    }

    public final synchronized void startPlay() {
        Pdlog.m3273d(TAG, "startPlay ");
        isRecording = true;
        if (playThread != null) {
            RecordPlayThread recordPlayThread = playThread;
            if (recordPlayThread == null) {
                Intrinsics.throwNpe();
            }
            recordPlayThread.interrupt();
        }
        playThread = new RecordPlayThread();
        RecordPlayThread recordPlayThread2 = playThread;
        if (recordPlayThread2 != null) {
            recordPlayThread2.start();
        }
    }

    public final synchronized void stopPlay() {
        Pdlog.m3273d(TAG, "stopPlay ");
        isRecording = false;
        RecordPlayThread recordPlayThread = playThread;
        if (recordPlayThread != null) {
            recordPlayThread.interrupt();
        }
        playThread = (RecordPlayThread) null;
    }

    public final void setVolume(float vol2) {
        AudioTrack audioTrack2;
        Pdlog.m3273d(TAG, "setVolume : vol = " + vol2 + "; ");
        if (isInitV && (audioTrack2 = audioTrack) != null) {
            audioTrack2.setVolume(vol2);
        }
        vol = vol2;
    }

    public final void lowVolumeMode(String mActivityName) {
        Intrinsics.checkParameterIsNotNull(mActivityName, "mActivityName");
        voiceTag.put(mActivityName, mActivityName);
        Pdlog.m3273d(TAG, "lowVolumeMode " + mActivityName);
        float f = vol;
        if (f > 0.2d) {
            f = 0.2f;
        }
        AudioTrack audioTrack2 = audioTrack;
        if (audioTrack2 != null) {
            audioTrack2.setVolume(f);
        }
    }

    public final void normalVolumeMode(String mActivityName) {
        AudioTrack audioTrack2;
        Intrinsics.checkParameterIsNotNull(mActivityName, "mActivityName");
        String str = voiceTag.get(mActivityName);
        if (!(str == null || str.length() == 0)) {
            voiceTag.remove(mActivityName);
        }
        Pdlog.m3273d(TAG, "normalVolumeMode " + mActivityName);
        if (voiceTag.size() != 0 || (audioTrack2 = audioTrack) == null) {
            return;
        }
        audioTrack2.setVolume(vol);
    }

    /* compiled from: AdVoicePlayManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/AdVoicePlayManager$RecordPlayThread;", "Ljava/lang/Thread;", "()V", "isSetV", "", "startRecord", "", "run", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    private static final class RecordPlayThread extends Thread {
        private boolean isSetV;
        private long startRecord;

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                byte[] bArr = new byte[AdVoicePlayManager.access$getPlayBufSize$p(AdVoicePlayManager.INSTANCE)];
                AudioTrack access$getAudioTrack$p = AdVoicePlayManager.access$getAudioTrack$p(AdVoicePlayManager.INSTANCE);
                if (access$getAudioTrack$p != null) {
                    access$getAudioTrack$p.setVolume(0.0f);
                }
                this.startRecord = SystemClock.elapsedRealtime();
                AudioRecord access$getAudioRecord$p = AdVoicePlayManager.access$getAudioRecord$p(AdVoicePlayManager.INSTANCE);
                if (access$getAudioRecord$p != null) {
                    access$getAudioRecord$p.startRecording();
                }
                AudioTrack access$getAudioTrack$p2 = AdVoicePlayManager.access$getAudioTrack$p(AdVoicePlayManager.INSTANCE);
                if (access$getAudioTrack$p2 != null) {
                    access$getAudioTrack$p2.play();
                }
                Pdlog.m3273d(AdVoicePlayManager.access$getTAG$p(AdVoicePlayManager.INSTANCE), "run start");
                while (AdVoicePlayManager.access$isRecording$p(AdVoicePlayManager.INSTANCE) && AdVoicePlayManager.access$getAudioRecord$p(AdVoicePlayManager.INSTANCE) != null && !isInterrupted()) {
                    AudioRecord access$getAudioRecord$p2 = AdVoicePlayManager.access$getAudioRecord$p(AdVoicePlayManager.INSTANCE);
                    if (access$getAudioRecord$p2 == null) {
                        Intrinsics.throwNpe();
                    }
                    int read = access$getAudioRecord$p2.read(bArr, 0, AdVoicePlayManager.access$getPlayBufSize$p(AdVoicePlayManager.INSTANCE));
                    byte[] bArr2 = new byte[read];
                    System.arraycopy(bArr, 0, bArr2, 0, read);
                    AudioTrack access$getAudioTrack$p3 = AdVoicePlayManager.access$getAudioTrack$p(AdVoicePlayManager.INSTANCE);
                    if (access$getAudioTrack$p3 != null) {
                        access$getAudioTrack$p3.write(bArr2, 0, bArr2.length);
                    }
                    if (!this.isSetV) {
                        Tools.execCommand("chmod 777 /dev/snd/pcmC0D0c", true);
                        Tools.execCommand("chmod 777 /dev/snd/controlC0", true);
                        Tools.execCommand("chmod 777 /dev/snd/pcmC0D0p", true);
                        Tools.execCommand("amix \"IN2 Boost\" 0", false);
                        Tools.execCommand("amix \"ADC Capture Volume\" 50 50", false);
                        this.isSetV = true;
                    }
                    if (!AdVoicePlayManager.access$isInitV$p(AdVoicePlayManager.INSTANCE) && SystemClock.elapsedRealtime() - this.startRecord > 3000) {
                        AudioTrack access$getAudioTrack$p4 = AdVoicePlayManager.access$getAudioTrack$p(AdVoicePlayManager.INSTANCE);
                        if (access$getAudioTrack$p4 != null) {
                            access$getAudioTrack$p4.setVolume(AdVoicePlayManager.access$getVol$p(AdVoicePlayManager.INSTANCE));
                        }
                        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
                        AdVoicePlayManager.isInitV = true;
                    }
                }
                Pdlog.m3273d(AdVoicePlayManager.access$getTAG$p(AdVoicePlayManager.INSTANCE), " RecordPlayThread : run stop");
                AudioTrack access$getAudioTrack$p5 = AdVoicePlayManager.access$getAudioTrack$p(AdVoicePlayManager.INSTANCE);
                if (access$getAudioTrack$p5 != null) {
                    access$getAudioTrack$p5.stop();
                }
                AudioRecord access$getAudioRecord$p3 = AdVoicePlayManager.access$getAudioRecord$p(AdVoicePlayManager.INSTANCE);
                if (access$getAudioRecord$p3 != null) {
                    access$getAudioRecord$p3.stop();
                }
            } catch (Throwable th) {
                Pdlog.m3274e(AdVoicePlayManager.access$getTAG$p(AdVoicePlayManager.INSTANCE), " RecordPlayThread : " + Log.getStackTraceString(th));
            }
        }
    }
}
