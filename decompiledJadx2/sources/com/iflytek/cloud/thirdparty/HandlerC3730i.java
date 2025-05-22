package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.Setting;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.record.PcmRecorder;
import com.iflytek.cloud.thirdparty.AbstractC3734m;
import com.iflytek.cloud.thirdparty.AbstractC3741t;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import com.iflytek.cloud.thirdparty.C3693ae;
import com.iflytek.cloud.thirdparty.HandlerC3732k;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.iflytek.cloud.thirdparty.i */
/* loaded from: classes3.dex */
public class HandlerC3730i extends HandlerC3732k implements AbstractC3734m.a, AbstractC3734m.b {

    /* renamed from: A */
    private final String f3241A;

    /* renamed from: B */
    private final String f3242B;

    /* renamed from: C */
    private final String f3243C;

    /* renamed from: D */
    private final String f3244D;

    /* renamed from: E */
    private int f3245E;

    /* renamed from: F */
    private AbstractC3734m f3246F;

    /* renamed from: G */
    private final int f3247G;

    /* renamed from: w */
    private final String f3248w;

    /* renamed from: x */
    private final int f3249x;

    /* renamed from: y */
    private final int f3250y;

    /* renamed from: z */
    private final String f3251z;

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m.b
    /* renamed from: a */
    public void mo2118a(byte[] bArr, int i, int i2, Object obj) {
    }

    public HandlerC3730i(Context context, C3692ad c3692ad, HandlerThread handlerThread) {
        super(context, c3692ad, handlerThread);
        this.f3248w = "ivw_caller";
        this.f3249x = 22;
        this.f3250y = 23;
        this.f3251z = "sid";
        this.f3241A = NotificationCompat.CATEGORY_MESSAGE;
        this.f3242B = "arg1";
        this.f3243C = "arg2";
        this.f3244D = SpeechUtility.TAG_RESOURCE_RESULT;
        this.f3245E = 0;
        this.f3246F = null;
        this.f3247G = 0;
        this.f3245E = getParam().m1816a(SpeechConstant.IVW_CHANNEL_NUM, 0);
        getParam().m1823a("ivw_caller", "1", false);
        this.f3246F = AbstractC3734m.m2146a();
        if (this.f3246F == null) {
            this.f3246F = AbstractC3734m.m2147a(getParam().m1831d("aimic_init_param"));
        }
        AbstractC3734m abstractC3734m = this.f3246F;
        if (abstractC3734m != null) {
            abstractC3734m.mo2153a((AbstractC3734m.b) this);
            this.f3246F.mo2152a((AbstractC3734m.a) this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.HandlerC3732k, com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
        super.onMsgProcess(message);
        int i = message.what;
        if (i == 22) {
            m2109c(message);
        } else {
            if (i != 23) {
                return;
            }
            m2110f();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: c */
    private void m2109c(Message message) throws SpeechError, Throwable {
        SpeechError speechError;
        HandlerC3732k.a aVar;
        DebugLog.LogD("proc_Wakeup_Angle enter");
        if (this.f3256b != null) {
            Bundle peekData = message.peekData();
            int i = peekData.getInt(NotificationCompat.CATEGORY_MESSAGE);
            try {
                aVar = HandlerC3732k.a.values()[i];
            } catch (Throwable unused) {
                DebugLog.LogE("warn: unmatched ivw message: " + i);
                aVar = HandlerC3732k.a.undefined_0;
            }
            int i2 = peekData.getInt("arg1");
            switch (aVar) {
                case IVW_MSG_WAKEUP:
                    m2117a(peekData);
                    speechError = null;
                    break;
                case IVW_MSG_ERROR:
                    speechError = new SpeechError(i2);
                    break;
                case IVW_MSG_ISR_RESULT:
                    sendMsg(obtainMessage(4, i2, 1, peekData.getByteArray(SpeechUtility.TAG_RESOURCE_RESULT)), hasMessages(4) ? AbstractHandlerC3740s.a.normal : AbstractHandlerC3740s.a.max, false, 0);
                    speechError = null;
                    break;
                case IVW_MSG_ISR_EPS:
                    if (C3693ae.a.VAD_EOS.ordinal() == i2) {
                        m2134d();
                    }
                    speechError = null;
                    break;
                case IVW_MSG_VOLUME:
                    if (this.f3256b != null) {
                        this.f3256b.onVolumeChanged(i2);
                    }
                    speechError = null;
                    break;
                case IVW_MSG_ENROLL:
                    sendMsg(obtainMessage(4, 0, 2, peekData.getByteArray(SpeechUtility.TAG_RESOURCE_RESULT)), AbstractHandlerC3740s.a.max, false, 0);
                    speechError = null;
                    break;
                case IVW_MSG_RESET:
                    DebugLog.LogD("proc_Wakeup_Msg reset msg");
                    speechError = null;
                    break;
                default:
                    speechError = null;
                    break;
            }
        } else {
            DebugLog.LogE("proc_Wakeup_Angle error: listener is null");
            speechError = new SpeechError(20999);
        }
        if (speechError == null) {
            return;
        }
        DebugLog.LogE("wakeup msg error: " + speechError.getErrorCode());
        throw speechError;
    }

    @Override // com.iflytek.cloud.thirdparty.HandlerC3732k
    /* renamed from: a */
    protected void mo2114a() throws SpeechError, Throwable {
        m2111g();
        setStatus(AbstractHandlerC3740s.b.recording);
        if (this.f3256b != null) {
            this.f3256b.onEvent(SpeechEvent.EVENT_SESSION_BEGIN, 0, 0, null);
        }
        sendMsg(23, AbstractHandlerC3740s.a.normal, true, 0);
    }

    @Override // com.iflytek.cloud.thirdparty.HandlerC3732k
    /* renamed from: b */
    protected void mo2119b() throws Exception {
        DebugLog.LogD("start connecting");
        this.f3262h = false;
        int m1816a = getParam().m1816a("record_read_rate", 40);
        if (-3 == this.f3258d) {
            if (this.f3246F == null) {
                DebugLog.LogE("create AIMIC failed!");
                throw new SpeechError(21003);
            }
        } else if (this.f3258d != -1 && isRunning()) {
            DebugLog.LogD("start  record");
            if (this.f3260f == null) {
                this.f3260f = new PcmRecorder(getSampleRate(), m1816a, this.f3258d);
                this.f3260f.startRecording(this);
            }
        }
        sendMsg(1, AbstractHandlerC3740s.a.max, false, 0);
    }

    /* renamed from: f */
    private void m2110f() throws Throwable {
        if (isRunning()) {
            m2112h();
            if (getStatus() == AbstractHandlerC3740s.b.exiting || this.f3256b == null) {
                return;
            }
            this.f3256b.onBeginOfSpeech();
            return;
        }
        DebugLog.LogD("It's not running while start aimic listening!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.HandlerC3732k, com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onEnd(SpeechError speechError) {
        DebugLog.LogD("Aimic Wakeuper onEnd enter");
        super.onEnd(m2107a(speechError));
    }

    @Override // com.iflytek.cloud.thirdparty.HandlerC3732k
    /* renamed from: a */
    public int mo2113a(byte[] bArr, int i, int i2) {
        AbstractC3734m abstractC3734m = this.f3246F;
        if (abstractC3734m != null) {
            return abstractC3734m.mo2151a(bArr, i, i2);
        }
        return 22001;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m.b
    /* renamed from: a */
    public void mo2116a(int i, int i2, int i3, byte[] bArr, int i4, byte[] bArr2, int i5, byte[] bArr3, int i6) {
        try {
            Bundle bundle = new Bundle();
            byte[] bArr4 = null;
            if (HandlerC3732k.a.IVW_MSG_WAKEUP.ordinal() == i) {
                bArr4 = new byte[i6];
                System.arraycopy(bArr3, 0, bArr4, 0, bArr4.length);
                DebugLog.LogD("onWakeupMsg audio length:" + bArr4.length);
            }
            bundle.putByteArray("ivw_audio", bArr4);
            if (bArr != null) {
                byte[] bArr5 = new byte[i4];
                System.arraycopy(bArr, 0, bArr5, 0, bArr5.length);
                bundle.putByteArray("sid", bArr5);
            }
            if (bArr2 != null) {
                byte[] bArr6 = new byte[i5];
                System.arraycopy(bArr2, 0, bArr6, 0, bArr6.length);
                bundle.putByteArray(SpeechUtility.TAG_RESOURCE_RESULT, bArr6);
            }
            bundle.putInt(NotificationCompat.CATEGORY_MESSAGE, i);
            bundle.putInt("arg1", i2);
            bundle.putInt("arg2", i3);
            Message obtainMessage = obtainMessage(22);
            obtainMessage.setData(bundle);
            sendMsg(obtainMessage, hasMessages(22) ? AbstractHandlerC3740s.a.normal : AbstractHandlerC3740s.a.max, false, 0);
        } catch (Throwable th) {
            DebugLog.LogE(th);
            System.gc();
            m2108b(new SpeechError(20999));
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m.a, com.iflytek.cloud.thirdparty.AbstractC3734m.b
    /* renamed from: a */
    public void mo2115a(int i) {
        onError(new SpeechError(i));
    }

    /* renamed from: g */
    private void m2111g() throws SpeechError, Throwable {
        if (this.f3245E <= 0) {
            DebugLog.LogE("Channel number " + this.f3245E + " is less than 1 !");
            throw new SpeechError(20012);
        }
        AbstractC3734m abstractC3734m = this.f3246F;
        if (abstractC3734m == null) {
            DebugLog.LogE("create AIMIC failed!");
            throw new SpeechError(21003);
        }
        abstractC3734m.mo2154a(Setting.getShowLog(), Setting.getLogLevel().ordinal());
        int mo2159c = this.f3246F.mo2159c();
        HashMap<String, String> m1830c = getParam().m1830c();
        DebugLog.LogD("AIMicBegin param count: " + m1830c.size());
        int i = 0;
        Iterator<Map.Entry<String, String>> it = m1830c.entrySet().iterator();
        while (it.hasNext() && mo2159c == 0) {
            Map.Entry<String, String> next = it.next();
            if (next.getKey().startsWith("aimic_ssb_")) {
                mo2159c = this.f3246F.mo2150a(next.getKey().substring(10), next.getValue());
                it.remove();
            }
            i++;
        }
        DebugLog.LogD("AIMicBegin for count: " + i);
        if (mo2159c == 0) {
            mo2159c = this.f3246F.mo2150a("ivw_thread_num", Integer.toString(this.f3245E));
        }
        if (mo2159c == 0) {
            mo2159c = this.f3246F.mo2150a("ivw_ssb", C3694af.m1845b(this.mContext, this.f3255a, this));
        }
        if (mo2159c == 0) {
            return;
        }
        DebugLog.LogE("aimic init error: " + mo2159c);
        throw new SpeechError(mo2159c);
    }

    /* renamed from: a */
    private SpeechError m2107a(SpeechError speechError) {
        int i;
        DebugLog.LogD("AIMicEnd enter");
        AbstractC3734m abstractC3734m = this.f3246F;
        if (abstractC3734m != null) {
            abstractC3734m.mo2158b((AbstractC3734m.b) this);
            this.f3246F.mo2157b((AbstractC3734m.a) this);
            this.f3246F.mo2161e();
            UnsatisfiedLinkError e = null;
            try {
                String str = "success";
                if (this.mUserCancel) {
                    str = "user abort";
                } else if (speechError != null) {
                    str = "error" + speechError.getErrorCode();
                }
                i = this.f3246F.mo2150a("ivw_sse", str);
            } catch (UnsatisfiedLinkError e2) {
                e = e2;
                i = 20021;
            } catch (Throwable th) {
                e = th;
                i = 20999;
            }
            if (speechError == null && (e != null || i != 0)) {
                DebugLog.LogE("AIMicEnd error!");
                speechError = new SpeechError(e, i);
            }
        }
        DebugLog.LogD("AIMicEnd leave");
        return speechError;
    }

    /* renamed from: a */
    protected void m2117a(Bundle bundle) throws SpeechError, Throwable {
        this.f3262h = true;
        AbstractC3741t.a aVar = AbstractC3741t.a.resultOver;
        if (this.f3263i || AIUIConstant.INTERACT_MODE_ONESHOT.equals(this.f3255a)) {
            aVar = AbstractC3741t.a.hasResult;
        }
        Message obtainMessage = obtainMessage(4, aVar.ordinal(), 0, bundle.getByteArray(SpeechUtility.TAG_RESOURCE_RESULT));
        obtainMessage.setData(bundle);
        sendMsg(obtainMessage, hasMessages(4) ? AbstractHandlerC3740s.a.normal : AbstractHandlerC3740s.a.max, false, 0);
    }

    /* renamed from: b */
    private void m2108b(SpeechError speechError) {
        exit(speechError);
    }

    /* renamed from: h */
    private void m2112h() throws SpeechError {
        String m1831d = getParam().m1831d("alsa_card");
        int mo2150a = !TextUtils.isEmpty(m1831d) ? this.f3246F.mo2150a("alsa_card", m1831d) : 0;
        String m1831d2 = getParam().m1831d("alsa_rate");
        if (mo2150a == 0 && !TextUtils.isEmpty(m1831d2)) {
            mo2150a = this.f3246F.mo2150a("alsa_rate", m1831d2);
        }
        String m1831d3 = getParam().m1831d("alsa_save");
        if (mo2150a == 0 && !TextUtils.isEmpty(m1831d3)) {
            mo2150a = this.f3246F.mo2150a("alsa_save", m1831d3);
        }
        if (mo2150a == 0) {
            mo2150a = this.f3246F.mo2150a(SpeechConstant.AUDIO_SOURCE, Integer.toString(-3 != this.f3258d ? -1 : -3));
        }
        if (mo2150a == 0) {
            mo2150a = this.f3246F.mo2160d();
        }
        if (mo2150a == 0) {
            return;
        }
        DebugLog.LogE("start aimic listening failed: " + mo2150a);
        throw new SpeechError(mo2150a);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3734m.a
    /* renamed from: b */
    public void mo2120b(byte[] bArr, int i, int i2, Object obj) {
        try {
            Bundle bundle = new Bundle();
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            bundle.putByteArray("data", bArr2);
            this.f3256b.onEvent(21003, 0, 0, bundle);
        } catch (Throwable th) {
            DebugLog.LogE(th);
            onError(new SpeechError(th, 20999));
        }
    }
}
