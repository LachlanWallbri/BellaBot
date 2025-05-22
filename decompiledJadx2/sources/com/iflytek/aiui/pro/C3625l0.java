package com.iflytek.aiui.pro;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.aiui.data.audio.recorder.PcmRecorder;
import com.iflytek.aiui.error.AIUIError;
import com.iflytek.aiui.pro.AbstractC3617h0;
import com.iflytek.speech.VoiceWakeuperAidl;

/* renamed from: com.iflytek.aiui.pro.l0 */
/* loaded from: classes4.dex */
public class C3625l0 extends AbstractC3617h0 implements PcmRecorder.PcmRecordListener {

    /* renamed from: a */
    private int f2575a;

    /* renamed from: b */
    private PcmRecorder f2576b;

    /* renamed from: c */
    private C3623k0 f2577c;

    /* renamed from: d */
    private Context f2578d;

    public C3625l0(Context context, AbstractC3617h0.a aVar) {
        super(aVar);
        this.f2575a = 16000;
        this.f2577c = new C3623k0();
        this.f2578d = context;
    }

    /* renamed from: a */
    private AudioDeviceInfo m1402a() {
        AudioDeviceInfo[] devices;
        AudioDeviceInfo audioDeviceInfo = null;
        try {
            AudioManager audioManager = (AudioManager) this.f2578d.getSystemService(InternalConstant.DTYPE_AUDIO);
            int m1473b = C3633p0.m1473b("recorder", AIUIConstant.KEY_SOUND_CARD, -1);
            if (Build.VERSION.SDK_INT >= 23 && m1473b != -1 && (devices = audioManager.getDevices(1)) != null) {
                for (AudioDeviceInfo audioDeviceInfo2 : devices) {
                    C3651y0.m1627i("SystemAudioCaptor", "getProductName" + ((Object) audioDeviceInfo2.getProductName()) + "  address" + audioDeviceInfo2.getAddress());
                    if (audioDeviceInfo2.getAddress().startsWith("card=" + m1473b + VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                        audioDeviceInfo = audioDeviceInfo2;
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return audioDeviceInfo;
    }

    /* renamed from: b */
    private void m1403b() {
        this.f2575a = C3633p0.m1473b(InternalConstant.SUB_IAT, "sample_rate", 16000);
        this.f2576b = new PcmRecorder(this.f2575a, 40, 1, m1402a());
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public int getSampleRate() {
        return this.f2575a;
    }

    @Override // com.iflytek.aiui.data.audio.recorder.PcmRecorder.PcmRecordListener
    public void onError(AIUIError aIUIError) {
        this.mIsStarted = false;
        AbstractC3617h0.a aVar = this.mCaptureListener;
        if (aVar != null) {
            aVar.mo861a(aIUIError.getErrorCode(), aIUIError.getDes());
        }
        Log.e("SystemAudioCaptor", "error occurred, error=" + aIUIError.getErrorCode());
    }

    @Override // com.iflytek.aiui.data.audio.recorder.PcmRecorder.PcmRecordListener
    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (this.mCaptureListener != null) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            this.mCaptureListener.mo866h(this.f2577c.m1391a(), bArr2, i2, null);
        }
    }

    @Override // com.iflytek.aiui.data.audio.recorder.PcmRecorder.PcmRecordListener
    public void onRecordReleased() {
        this.mIsStarted = false;
        AbstractC3617h0.a aVar = this.mCaptureListener;
        if (aVar != null) {
            aVar.mo864e();
        }
        C3651y0.m1620b("SystemAudioCaptor", "captor stopped.");
    }

    @Override // com.iflytek.aiui.data.audio.recorder.PcmRecorder.PcmRecordListener
    public void onRecordStarted(boolean z) {
        if (z) {
            this.mIsStarted = true;
            AbstractC3617h0.a aVar = this.mCaptureListener;
            if (aVar != null) {
                aVar.mo865g();
            }
            C3651y0.m1620b("SystemAudioCaptor", "captor start success.");
        }
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public void release() {
        stop();
        if (this.f2576b != null) {
            this.f2576b = null;
            this.mIsReleased = true;
            AbstractC3617h0.a aVar = this.mCaptureListener;
            if (aVar != null) {
                aVar.mo862b();
            }
        }
        C3651y0.m1620b("SystemAudioCaptor", "captor released.");
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public int start() {
        String str;
        if (isStarted()) {
            str = "SingleAudioCaptor was already started.";
        } else {
            m1403b();
            PcmRecorder pcmRecorder = this.f2576b;
            if (pcmRecorder != null) {
                try {
                    pcmRecorder.startRecording(this);
                } catch (AIUIError e) {
                    e.printStackTrace();
                    int errorCode = e.getErrorCode();
                    Log.e("SystemAudioCaptor", "captor start error, error=" + errorCode);
                    return errorCode;
                }
            }
            str = "captor start.";
        }
        C3651y0.m1620b("SystemAudioCaptor", str);
        return 0;
    }

    @Override // com.iflytek.aiui.pro.AbstractC3617h0
    public void stop() {
        PcmRecorder pcmRecorder;
        if (isStarted() && (pcmRecorder = this.f2576b) != null) {
            pcmRecorder.stopRecord(true);
        }
    }
}
