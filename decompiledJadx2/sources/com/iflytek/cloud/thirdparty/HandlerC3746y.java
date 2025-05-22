package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import java.io.IOException;

/* renamed from: com.iflytek.cloud.thirdparty.y */
/* loaded from: classes3.dex */
public class HandlerC3746y extends AbstractHandlerC3740s {

    /* renamed from: a */
    private SpeechListener f3444a;

    /* renamed from: b */
    private C3745x f3445b;

    /* renamed from: c */
    private String f3446c;

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getClientID() {
        return null;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getSessionID() {
        return null;
    }

    public HandlerC3746y(Context context, C3692ad c3692ad, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.f3444a = null;
        this.f3445b = new C3745x();
        this.f3446c = null;
        setParams(c3692ad);
    }

    public HandlerC3746y(Context context, C3692ad c3692ad) {
        super(context);
        this.f3444a = null;
        this.f3445b = new C3745x();
        this.f3446c = null;
        setParams(c3692ad);
    }

    /* renamed from: a */
    public void m2269a(SpeechListener speechListener) {
        this.f3444a = speechListener;
        sendMsg(11);
    }

    /* renamed from: a */
    public void m2270a(SpeechListener speechListener, String str) {
        setStatus(AbstractHandlerC3740s.b.start);
        this.f3446c = "sch";
        this.f3444a = speechListener;
        sendMsg(obtainMessage(12, str));
    }

    /* renamed from: a */
    public void m2271a(SpeechListener speechListener, String str, byte[] bArr) {
        this.f3446c = "uup";
        this.f3444a = speechListener;
        sendMsg(obtainMessage(10, new a(bArr, str)));
    }

    /* renamed from: a */
    public SpeechError m2268a(String str, String str2) {
        SpeechError e;
        StringBuilder sb;
        this.f3446c = "auth";
        try {
            C3745x.m2263a(this.mContext, str, str2, this);
            return null;
        } catch (SpeechError e2) {
            e = e2;
            DebugLog.LogE(e);
            sb = new StringBuilder();
            sb.append(getTag());
            sb.append(" occur Error = ");
            sb.append(e.toString());
            DebugLog.LogD(sb.toString());
            return e;
        } catch (IOException e3) {
            DebugLog.LogE(e3);
            e = new SpeechError(20010);
            sb = new StringBuilder();
            sb.append(getTag());
            sb.append(" occur Error = ");
            sb.append(e.toString());
            DebugLog.LogD(sb.toString());
            return e;
        } catch (Exception e4) {
            DebugLog.LogE(e4);
            e = new SpeechError(21003);
            sb = new StringBuilder();
            sb.append(getTag());
            sb.append(" occur Error = ");
            sb.append(e.toString());
            DebugLog.LogD(sb.toString());
            return e;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
        byte[] m2267a;
        super.onMsgProcess(message);
        if (SpeechUtility.getUtility() == null) {
            DebugLog.LogE("MscCommon process while utility is null!");
            exit(new SpeechError(20015));
            return;
        }
        switch (message.what) {
            case 10:
                a aVar = (a) message.obj;
                if (aVar.m2272a() == null || aVar.m2272a().length <= 0) {
                    throw new SpeechError(20009);
                }
                m2267a = this.f3445b.m2267a(this.mContext, aVar.m2273b(), aVar.m2272a(), this);
                break;
                break;
            case 11:
                m2267a = this.f3445b.m2265a(this.mContext, this);
                break;
            case 12:
                String str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    throw new SpeechError(20009);
                }
                setStatus(AbstractHandlerC3740s.b.waitresult);
                m2267a = this.f3445b.m2266a(this.mContext, this, str);
                break;
            default:
                m2267a = null;
                break;
        }
        if (m2267a == null) {
            throw new SpeechError(20004);
        }
        if (this.f3444a != null && !this.mUserCancel) {
            this.f3444a.onBufferReceived(m2267a);
        }
        exit(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onEnd(SpeechError speechError) {
        super.onEnd(speechError);
        if (this.f3444a == null || this.mUserCancel) {
            return;
        }
        this.f3444a.onCompleted(speechError);
    }

    /* renamed from: com.iflytek.cloud.thirdparty.y$a */
    /* loaded from: classes3.dex */
    private class a {

        /* renamed from: b */
        private byte[] f3448b;

        /* renamed from: c */
        private String f3449c;

        public a(byte[] bArr, String str) {
            this.f3448b = null;
            this.f3449c = "";
            this.f3448b = bArr;
            this.f3449c = str;
        }

        /* renamed from: a */
        public byte[] m2272a() {
            return this.f3448b;
        }

        /* renamed from: b */
        public String m2273b() {
            return this.f3449c;
        }
    }
}
