package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.TranscripterListener;
import com.iflytek.cloud.TranscripterResult;
import com.iflytek.cloud.msc.ist.MscTranscripter;
import com.iflytek.cloud.msc.util.FuncAdapter;
import com.iflytek.cloud.msc.util.log.DebugLog;

/* renamed from: com.iflytek.cloud.thirdparty.an */
/* loaded from: classes3.dex */
public class C3702an extends AbstractC3744w {

    /* renamed from: e */
    private boolean f3030e;

    public C3702an(Context context) {
        super(context);
        this.f3030e = false;
    }

    /* renamed from: b */
    public void m1927b(String str) {
        synchronized (this.f3435b) {
            if (this.f3436c != null) {
                ((MscTranscripter) this.f3436c).getPerfLog().m2256a(str);
            }
        }
    }

    /* renamed from: a */
    public int m1925a(TranscripterListener transcripterListener) {
        int i;
        synchronized (this.f3435b) {
            try {
                this.f3030e = this.mSessionParams.m1825a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                this.f3436c = new MscTranscripter(this.f3434a, this.mSessionParams, m2259a("ist"));
                FuncAdapter.Lock(this.f3434a, Boolean.valueOf(this.f3030e), null);
                ((MscTranscripter) this.f3436c).startTranscripting(new a(transcripterListener));
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

    /* renamed from: a */
    public boolean m1926a(byte[] bArr, int i, int i2) {
        synchronized (this.f3435b) {
            if (this.f3436c == null) {
                DebugLog.LogD("writeAudio error, no active session.");
                return false;
            }
            if (bArr != null && bArr.length > 0) {
                if (bArr.length < i2 + i) {
                    DebugLog.LogD("writeAudio error,buffer length < length.");
                    return false;
                }
                if (((MscTranscripter) this.f3436c).getAudioSource() != -1) {
                    return false;
                }
                ((MscTranscripter) this.f3436c).onRecordBuffer(bArr, i, i2);
                return true;
            }
            DebugLog.LogD("writeAudio error,buffer is null.");
            return false;
        }
    }

    /* renamed from: e */
    public void m1928e() {
        synchronized (this.f3435b) {
            if (this.f3436c != null) {
                ((MscTranscripter) this.f3436c).stopTranscripting(true);
            }
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    public void cancel(boolean z) {
        FuncAdapter.UnLock(this.f3434a, Boolean.valueOf(this.f3030e), null);
        super.cancel(z);
    }

    /* renamed from: com.iflytek.cloud.thirdparty.an$a */
    /* loaded from: classes3.dex */
    private final class a implements TranscripterListener {

        /* renamed from: b */
        private TranscripterListener f3032b;

        /* renamed from: c */
        private boolean f3033c = false;

        /* renamed from: d */
        private Handler f3034d = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.an.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (a.this.f3032b == null) {
                    return;
                }
                int i = message.what;
                if (i == 0) {
                    a.this.f3032b.onError((SpeechError) message.obj);
                } else if (i == 1) {
                    a.this.f3032b.onVolumeChanged(message.arg1, (byte[]) message.obj);
                } else if (i == 2) {
                    a.this.f3032b.onBeginOfSpeech();
                } else if (i == 3) {
                    a.this.f3032b.onEndOfSpeech();
                } else if (i == 4) {
                    a.this.f3032b.onResult((TranscripterResult) message.obj, message.arg1 == 1);
                    if (!a.this.f3033c) {
                        C3702an.this.m1927b("ui_frs");
                        a.this.f3033c = true;
                    }
                    if (1 == message.arg1) {
                        C3702an.this.m1927b("ui_lrs");
                    }
                } else if (i == 6) {
                    Message message2 = (Message) message.obj;
                    a.this.f3032b.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                }
                super.handleMessage(message);
            }
        };

        public a(TranscripterListener transcripterListener) {
            this.f3032b = null;
            this.f3032b = transcripterListener;
        }

        @Override // com.iflytek.cloud.TranscripterListener
        public void onVolumeChanged(int i, byte[] bArr) {
            this.f3034d.sendMessage(this.f3034d.obtainMessage(1, i, 0, bArr));
        }

        @Override // com.iflytek.cloud.TranscripterListener
        public void onEndOfSpeech() {
            this.f3034d.sendMessage(this.f3034d.obtainMessage(3, 0, 0, null));
        }

        @Override // com.iflytek.cloud.TranscripterListener
        public void onResult(TranscripterResult transcripterResult, boolean z) {
            if (z) {
                m1936a();
            }
            this.f3034d.sendMessage(this.f3034d.obtainMessage(4, !z ? 0 : 1, 0, transcripterResult));
        }

        @Override // com.iflytek.cloud.TranscripterListener
        public void onBeginOfSpeech() {
            DebugLog.LogD("onBeginOfSpeech");
            this.f3034d.sendMessage(this.f3034d.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.TranscripterListener
        public void onError(SpeechError speechError) {
            m1936a();
            this.f3034d.sendMessage(this.f3034d.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.TranscripterListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            this.f3034d.sendMessage(this.f3034d.obtainMessage(6, 0, 0, message));
        }

        /* renamed from: a */
        protected void m1936a() {
            FuncAdapter.UnLock(C3702an.this.f3434a, Boolean.valueOf(C3702an.this.f3030e), null);
        }
    }

    /* renamed from: f */
    public boolean m1929f() {
        return mo1797d();
    }

    /* renamed from: g */
    public String m1930g() {
        if (this.f3436c != null) {
            return this.f3436c.getSessionID();
        }
        return null;
    }

    /* renamed from: h */
    public int m1931h() {
        if (this.f3436c != null) {
            return ((MscTranscripter) this.f3436c).getUploadBytes();
        }
        return 0;
    }

    /* renamed from: i */
    public String m1932i() {
        if (this.f3436c != null) {
            return ((MscTranscripter) this.f3436c).getAudioPath();
        }
        return null;
    }
}
