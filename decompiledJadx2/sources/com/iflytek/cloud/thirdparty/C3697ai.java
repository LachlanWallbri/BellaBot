package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.IdentityListener;
import com.iflytek.cloud.IdentityResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.FuncAdapter;
import com.iflytek.cloud.msc.util.log.DebugLog;

/* renamed from: com.iflytek.cloud.thirdparty.ai */
/* loaded from: classes3.dex */
public class C3697ai extends AbstractC3744w {

    /* renamed from: e */
    private boolean f2997e;

    public C3697ai(Context context) {
        super(context);
        this.f2997e = false;
    }

    /* renamed from: b */
    public void m1871b(String str) {
        synchronized (this.f3435b) {
            if (this.f3436c != null) {
                ((HandlerC3739r) this.f3436c).m2253f().m2256a(str);
            }
        }
    }

    /* renamed from: a */
    public int m1868a(IdentityListener identityListener) {
        int i;
        synchronized (this.f3435b) {
            i = 0;
            try {
                this.f2997e = this.mSessionParams.m1825a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                if (this.f3436c != null && this.f3436c.isRunning()) {
                    this.f3436c.cancel(this.mSessionParams.m1825a(SpeechConstant.MFV_INTERRUPT_ERROR, false));
                }
                this.f3436c = new HandlerC3739r(this.f3434a, this.mSessionParams, m2259a("mfv"));
                FuncAdapter.Lock(this.f3434a, Boolean.valueOf(this.f2997e), null);
                ((HandlerC3739r) this.f3436c).m2243a(new a(identityListener));
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

    /* renamed from: a */
    public int m1870a(String str, String str2, byte[] bArr, int i, int i2) {
        synchronized (this.f3435b) {
            if (this.f3436c == null) {
                DebugLog.LogE("writeAudio error, no active session.");
                return 21004;
            }
            if (i2 < 0) {
                DebugLog.LogE("writeAudio error, length < 0.");
                return 10109;
            }
            if (bArr != null && bArr.length < i2 + i) {
                DebugLog.LogE("writeAudio error, buffer length < offset + length.");
                return 10109;
            }
            ((HandlerC3739r) this.f3436c).m2244a(str, str2, bArr, i, i2);
            return 0;
        }
    }

    /* renamed from: c */
    public void m1872c(String str) {
        synchronized (this.f3435b) {
            if (this.f3436c != null) {
                ((HandlerC3739r) this.f3436c).m2247a(str, true);
            }
        }
    }

    /* renamed from: a */
    public int m1869a(String str, String str2, String str3, IdentityListener identityListener) {
        setParameter("sst", str2);
        int m1868a = m1868a(identityListener);
        if (m1868a != 0) {
            return m1868a;
        }
        int m1870a = m1870a(str, str3, null, 0, 0);
        m1872c(str);
        return m1870a;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    public void cancel(boolean z) {
        FuncAdapter.UnLock(this.f3434a, Boolean.valueOf(this.f2997e), null);
        super.cancel(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.ai$a */
    /* loaded from: classes3.dex */
    public final class a implements IdentityListener {

        /* renamed from: b */
        private IdentityListener f2999b;

        /* renamed from: c */
        private boolean f3000c = false;

        /* renamed from: d */
        private Handler f3001d = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.ai.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (a.this.f2999b == null) {
                    return;
                }
                int i = message.what;
                if (i == 0) {
                    a.this.f2999b.onError((SpeechError) message.obj);
                } else if (i != 1 && i != 2 && i != 3) {
                    if (i == 4) {
                        a.this.f2999b.onResult((IdentityResult) message.obj, message.arg1 == 1);
                        if (!a.this.f3000c) {
                            C3697ai.this.m1871b("ui_frs");
                            a.this.f3000c = true;
                        }
                        if (1 == message.arg1) {
                            C3697ai.this.m1871b("ui_lrs");
                        }
                    } else if (i == 6) {
                        Message message2 = (Message) message.obj;
                        a.this.f2999b.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                    }
                }
                super.handleMessage(message);
            }
        };

        public a(IdentityListener identityListener) {
            this.f2999b = null;
            this.f2999b = identityListener;
        }

        @Override // com.iflytek.cloud.IdentityListener
        public void onResult(IdentityResult identityResult, boolean z) {
            if (z) {
                m1877a();
            }
            this.f3001d.sendMessage(this.f3001d.obtainMessage(4, !z ? 0 : 1, 0, identityResult));
        }

        @Override // com.iflytek.cloud.IdentityListener
        public void onError(SpeechError speechError) {
            m1877a();
            this.f3001d.sendMessage(this.f3001d.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.IdentityListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            this.f3001d.sendMessage(this.f3001d.obtainMessage(6, 0, 0, message));
        }

        /* renamed from: a */
        protected void m1877a() {
            ((HandlerC3739r) C3697ai.this.f3436c).m2241a().saveToFile();
            FuncAdapter.UnLock(C3697ai.this.f3434a, Boolean.valueOf(C3697ai.this.f2997e), null);
        }
    }

    /* renamed from: e */
    public boolean m1873e() {
        return mo1797d();
    }
}
