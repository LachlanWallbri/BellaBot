package com.iflytek.cloud;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3704ap;
import com.iflytek.msc.MSC;
import com.iflytek.speech.TextUnderstanderAidl;

/* loaded from: classes3.dex */
public class TextUnderstander extends AbstractC3743v {

    /* renamed from: c */
    private static TextUnderstander f2825c;

    /* renamed from: a */
    private C3704ap f2826a;

    /* renamed from: b */
    private TextUnderstanderAidl f2827b;

    /* renamed from: e */
    private InitListener f2829e;

    /* renamed from: d */
    private C3672a f2828d = null;

    /* renamed from: f */
    private Handler f2830f = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.TextUnderstander.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (TextUnderstander.this.f2829e == null) {
                return;
            }
            TextUnderstander.this.f2829e.onInit(0);
        }
    };

    public static synchronized TextUnderstander createTextUnderstander(Context context, InitListener initListener) {
        TextUnderstander textUnderstander;
        synchronized (TextUnderstander.class) {
            synchronized (sSync) {
                if (f2825c == null && SpeechUtility.getUtility() != null) {
                    f2825c = new TextUnderstander(context, initListener);
                }
            }
            textUnderstander = f2825c;
        }
        return textUnderstander;
    }

    protected TextUnderstander(Context context, InitListener initListener) {
        this.f2826a = null;
        this.f2827b = null;
        this.f2829e = null;
        this.f2829e = initListener;
        if (MSC.isLoaded()) {
            this.f2826a = new C3704ap(context);
        }
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.m1667a() && utility.getEngineMode() != AbstractC3743v.a.MSC) {
            this.f2827b = new TextUnderstanderAidl(context.getApplicationContext(), initListener);
        } else if (initListener != null) {
            Message.obtain(this.f2830f, 0, 0, 0, null).sendToTarget();
        }
    }

    public static TextUnderstander getTextUnderstander() {
        return f2825c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m1671a(Context context) {
        TextUnderstanderAidl textUnderstanderAidl;
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.m1667a() && utility.getEngineMode() != AbstractC3743v.a.MSC) {
            TextUnderstanderAidl textUnderstanderAidl2 = this.f2827b;
            if (textUnderstanderAidl2 != null && !textUnderstanderAidl2.isAvailable()) {
                this.f2827b.destory();
                this.f2827b = null;
            }
            this.f2827b = new TextUnderstanderAidl(context.getApplicationContext(), this.f2829e);
            return;
        }
        if (this.f2829e == null || (textUnderstanderAidl = this.f2827b) == null) {
            return;
        }
        textUnderstanderAidl.destory();
        this.f2827b = null;
    }

    public int understandText(String str, TextUnderstanderListener textUnderstanderListener) {
        DebugLog.LogD("start engine mode = " + getStartMode(SpeechConstant.ENG_NLU, this.f2827b).toString());
        C3704ap c3704ap = this.f2826a;
        if (c3704ap == null) {
            return 21001;
        }
        c3704ap.setParameter(this.mSessionParams);
        return this.f2826a.m1944a(str, textUnderstanderListener);
    }

    public boolean isUnderstanding() {
        C3704ap c3704ap = this.f2826a;
        if (c3704ap != null && c3704ap.m1945e()) {
            return true;
        }
        TextUnderstanderAidl textUnderstanderAidl = this.f2827b;
        return textUnderstanderAidl != null && textUnderstanderAidl.isUnderstanding();
    }

    public void cancel() {
        C3704ap c3704ap = this.f2826a;
        if (c3704ap != null) {
            c3704ap.cancel(false);
            return;
        }
        TextUnderstanderAidl textUnderstanderAidl = this.f2827b;
        if (textUnderstanderAidl == null) {
            DebugLog.LogE("TextUnderstander cancel failed, is not running");
        } else {
            textUnderstanderAidl.cancel(this.f2828d.f2832a);
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public String getParameter(String str) {
        return super.getParameter(str);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        TextUnderstanderAidl textUnderstanderAidl = this.f2827b;
        if (textUnderstanderAidl != null) {
            textUnderstanderAidl.destory();
        }
        C3704ap c3704ap = this.f2826a;
        boolean destroy = c3704ap != null ? c3704ap.destroy() : true;
        if (destroy && (destroy = super.destroy())) {
            this.f2827b = null;
            synchronized (sSync) {
                f2825c = null;
            }
        }
        return destroy;
    }

    /* renamed from: com.iflytek.cloud.TextUnderstander$a */
    /* loaded from: classes3.dex */
    private final class C3672a implements TextUnderstanderListener {

        /* renamed from: a */
        private com.iflytek.speech.TextUnderstanderListener f2832a;

        /* renamed from: b */
        private Handler f2833b;

        @Override // com.iflytek.cloud.TextUnderstanderListener
        public void onResult(UnderstanderResult understanderResult) {
            this.f2833b.sendMessage(this.f2833b.obtainMessage(4, understanderResult));
        }

        @Override // com.iflytek.cloud.TextUnderstanderListener
        public void onError(SpeechError speechError) {
            this.f2833b.sendMessage(this.f2833b.obtainMessage(0, speechError));
        }
    }
}
