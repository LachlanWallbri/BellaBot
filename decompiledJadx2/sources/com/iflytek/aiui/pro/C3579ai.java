package com.iflytek.aiui.pro;

import android.util.Log;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.aiui.data.audio.recorder.PcmRecorder;
import com.iflytek.aiui.error.AIUIError;
import com.iflytek.aiui.pro.AbstractC3577ag;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ai */
/* loaded from: classes.dex */
public class C3579ai extends AbstractC3577ag implements PcmRecorder.PcmRecordListener {

    /* renamed from: d */
    private int f2309d;

    /* renamed from: e */
    private PcmRecorder f2310e;

    public C3579ai(AbstractC3577ag.a aVar) {
        super(aVar);
        this.f2309d = 16000;
    }

    /* renamed from: e */
    private void m991e() {
        this.f2309d = C3582al.m1022a(InternalConstant.SUB_IAT, "sample_rate", 16000);
        this.f2310e = new PcmRecorder(this.f2309d, 40);
    }

    @Override // com.iflytek.aiui.pro.AbstractC3577ag
    /* renamed from: a */
    public int mo980a() {
        String str;
        if (m983d()) {
            str = "SingleAudioCaptor was already started.";
        } else {
            m991e();
            PcmRecorder pcmRecorder = this.f2310e;
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
        C3589as.m1059a("SystemAudioCaptor", str);
        return 0;
    }

    @Override // com.iflytek.aiui.pro.AbstractC3577ag
    /* renamed from: b */
    public void mo981b() {
        PcmRecorder pcmRecorder;
        if (m983d() && (pcmRecorder = this.f2310e) != null) {
            pcmRecorder.stopRecord(true);
        }
    }

    @Override // com.iflytek.aiui.pro.AbstractC3577ag
    /* renamed from: c */
    public void mo982c() {
        mo981b();
        if (this.f2310e != null) {
            this.f2310e = null;
            this.f2296b = true;
            if (this.f2297c != null) {
                this.f2297c.mo860c();
            }
        }
        C3589as.m1059a("SystemAudioCaptor", "captor released.");
    }

    @Override // com.iflytek.aiui.data.audio.recorder.PcmRecorder.PcmRecordListener
    public void onError(AIUIError aIUIError) {
        this.f2295a = false;
        if (this.f2297c != null) {
            this.f2297c.mo857a(aIUIError.getErrorCode(), aIUIError.getDes());
        }
        Log.e("SystemAudioCaptor", "error occurred, error=" + aIUIError.getErrorCode());
    }

    @Override // com.iflytek.aiui.data.audio.recorder.PcmRecorder.PcmRecordListener
    public void onRecordBuffer(byte[] bArr, int i, int i2) {
        if (this.f2297c != null) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            this.f2297c.mo858a(bArr2, i2, null);
        }
    }

    @Override // com.iflytek.aiui.data.audio.recorder.PcmRecorder.PcmRecordListener
    public void onRecordReleased() {
        this.f2295a = false;
        if (this.f2297c != null) {
            this.f2297c.mo859b();
        }
        C3589as.m1059a("SystemAudioCaptor", "captor stopped.");
    }

    @Override // com.iflytek.aiui.data.audio.recorder.PcmRecorder.PcmRecordListener
    public void onRecordStarted(boolean z) {
        if (z) {
            this.f2295a = true;
            if (this.f2297c != null) {
                this.f2297c.mo856a();
            }
            C3589as.m1059a("SystemAudioCaptor", "captor start success.");
        }
    }
}
