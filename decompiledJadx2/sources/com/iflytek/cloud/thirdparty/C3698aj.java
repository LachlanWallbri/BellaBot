package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.DataDownloader;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.VerifierListener;
import com.iflytek.cloud.VerifierResult;
import com.iflytek.cloud.msc.util.FileUtil;
import com.iflytek.cloud.msc.util.FuncAdapter;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.HandlerC3727f;
import java.security.SecureRandom;

/* renamed from: com.iflytek.cloud.thirdparty.aj */
/* loaded from: classes3.dex */
public class C3698aj extends AbstractC3744w {

    /* renamed from: e */
    private boolean f3003e;

    public C3698aj(Context context) {
        super(context);
        this.f3003e = false;
    }

    /* renamed from: a */
    public int m1885a(VerifierListener verifierListener) {
        int i;
        synchronized (this.f3435b) {
            i = 0;
            try {
                this.f3003e = this.mSessionParams.m1825a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                if (this.f3436c != null && this.f3436c.isRunning()) {
                    this.f3436c.cancel(this.mSessionParams.m1825a(SpeechConstant.ISV_INTERRUPT_ERROR, false));
                }
                this.f3436c = new HandlerC3728g(this.f3434a, this.mSessionParams, m2259a("verify"));
                FuncAdapter.Lock(this.f3434a, Boolean.valueOf(this.f3003e), null);
                ((HandlerC3728g) this.f3436c).m2087a(new a(verifierListener));
            } catch (SpeechError e) {
                i = e.getErrorCode();
                DebugLog.LogE(e);
            } catch (Throwable th) {
                i = 20999;
                DebugLog.LogE(th);
            }
        }
        return i;
    }

    /* renamed from: e */
    public void m1890e() {
        synchronized (this.f3435b) {
            if (this.f3436c != null) {
                ((HandlerC3728g) this.f3436c).m2090a();
            }
        }
    }

    /* renamed from: a */
    public void m1889a(SpeechListener speechListener) {
        DataDownloader dataDownloader = new DataDownloader(this.f3434a);
        dataDownloader.setParameter(this.mSessionParams);
        dataDownloader.downloadData(speechListener);
    }

    /* renamed from: a */
    public String m1888a(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        SecureRandom secureRandom = new SecureRandom();
        String str = "023456789".charAt(secureRandom.nextInt(9)) + "";
        stringBuffer.append(str);
        int i2 = 0;
        while (i2 < i - 1) {
            String str2 = str;
            while (true) {
                for (Boolean bool = false; !bool.booleanValue(); bool = true) {
                    str2 = "023456789".charAt(secureRandom.nextInt(9)) + "";
                    if (stringBuffer.indexOf(str2) >= 0) {
                        break;
                    }
                    if (Integer.parseInt(stringBuffer.charAt(stringBuffer.length() - 1) + "") * Integer.parseInt(str2) == 10) {
                        break;
                    }
                }
            }
            stringBuffer.append(str2);
            i2++;
            str = str2;
        }
        return stringBuffer.toString();
    }

    /* renamed from: com.iflytek.cloud.thirdparty.aj$a */
    /* loaded from: classes3.dex */
    private final class a implements VerifierListener {

        /* renamed from: b */
        private VerifierListener f3005b;

        /* renamed from: c */
        private Handler f3006c = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.aj.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (a.this.f3005b == null) {
                    return;
                }
                int i = message.what;
                if (i == 0) {
                    a.this.f3005b.onError((SpeechError) message.obj);
                } else if (i == 1) {
                    a.this.f3005b.onVolumeChanged(message.arg1, (byte[]) message.obj);
                } else if (i == 2) {
                    a.this.f3005b.onBeginOfSpeech();
                } else if (i == 3) {
                    a.this.f3005b.onEndOfSpeech();
                } else if (i == 4) {
                    a.this.f3005b.onResult((VerifierResult) message.obj);
                } else if (i == 5) {
                    Message message2 = (Message) message.obj;
                    a.this.f3005b.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                }
                super.handleMessage(message);
            }
        };

        public a(VerifierListener verifierListener) {
            this.f3005b = null;
            this.f3005b = verifierListener;
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onVolumeChanged(int i, byte[] bArr) {
            this.f3006c.sendMessage(this.f3006c.obtainMessage(1, i, 0, bArr));
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onEndOfSpeech() {
            this.f3006c.sendMessage(this.f3006c.obtainMessage(3, 0, 0, null));
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onBeginOfSpeech() {
            this.f3006c.sendMessage(this.f3006c.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onResult(VerifierResult verifierResult) {
            m1893a();
            this.f3006c.sendMessage(this.f3006c.obtainMessage(4, verifierResult));
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onError(SpeechError speechError) {
            m1893a();
            this.f3006c.sendMessage(this.f3006c.obtainMessage(0, speechError));
        }

        /* renamed from: a */
        protected void m1893a() {
            String m1833e = C3698aj.this.f3436c.getParam().m1833e(SpeechConstant.ISV_AUDIO_PATH);
            if (!TextUtils.isEmpty(m1833e) && FileUtil.saveFile(((HandlerC3728g) C3698aj.this.f3436c).m2095e(), m1833e)) {
                FileUtil.formatPcm(C3698aj.this.f3436c.getParam().m1827b(SpeechConstant.AUDIO_FORMAT, (String) null), m1833e, C3698aj.this.f3436c.getParam().m1816a("sample_rate", C3698aj.this.f3436c.mSampleRate));
            }
            FuncAdapter.UnLock(C3698aj.this.f3434a, Boolean.valueOf(C3698aj.this.f3003e), null);
        }

        @Override // com.iflytek.cloud.VerifierListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = i2;
            obtain.arg2 = i3;
            obtain.obj = bundle;
            Message.obtain(this.f3006c, 5, obtain).sendToTarget();
        }
    }

    /* renamed from: a */
    public int m1887a(byte[] bArr, int i, int i2) {
        synchronized (this.f3435b) {
            if (this.f3436c == null) {
                DebugLog.LogD("writeAudio error, no active session.");
                return 21004;
            }
            if (bArr != null && bArr.length > 0) {
                if (bArr.length < i2 + i) {
                    DebugLog.LogD("writeAudio error,buffer length < length.");
                    return 10109;
                }
                if (((HandlerC3728g) this.f3436c).m2096f() != -1) {
                    return 10106;
                }
                ((HandlerC3728g) this.f3436c).onRecordBuffer(bArr, i, i2);
                return 0;
            }
            DebugLog.LogD("writeAudio error,buffer is null.");
            return 10109;
        }
    }

    /* renamed from: f */
    public boolean m1891f() {
        return mo1797d();
    }

    /* renamed from: a */
    public int m1886a(String str, String str2, SpeechListener speechListener) {
        int i;
        synchronized (this.f3435b) {
            try {
                this.mSessionParams.m1822a("cmd", str);
                this.mSessionParams.m1822a(SpeechConstant.AUTH_ID, str2);
                new HandlerC3727f(this.f3434a, m2259a("manager")).m2079a(this.mSessionParams, new HandlerC3727f.a(speechListener));
                i = 0;
            } catch (SpeechError e) {
                i = e.getErrorCode();
                DebugLog.LogE(e);
            } catch (Throwable th) {
                i = 20999;
                DebugLog.LogE(th);
            }
        }
        return i;
    }
}
