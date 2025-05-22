package com.iflytek.aiui.pro;

import android.os.Bundle;
import com.iflytek.aiui.AIUIMessage;

/* renamed from: com.iflytek.aiui.pro.h0 */
/* loaded from: classes4.dex */
public abstract class AbstractC3617h0 {
    public static final int FAIL = -1;
    public static final int SUCCESS = 0;
    protected a mCaptureListener;
    protected boolean mIsReleased;
    protected boolean mIsStarted;

    /* renamed from: com.iflytek.aiui.pro.h0$a */
    /* loaded from: classes4.dex */
    public interface a {
        /* renamed from: a */
        void mo861a(int i, String str);

        /* renamed from: b */
        void mo862b();

        /* renamed from: d */
        void mo863d(byte[] bArr, int i, Bundle bundle);

        /* renamed from: e */
        void mo864e();

        /* renamed from: g */
        void mo865g();

        /* renamed from: h */
        void mo866h(int i, byte[] bArr, int i2, Bundle bundle);
    }

    public AbstractC3617h0(a aVar) {
        this.mCaptureListener = aVar;
    }

    public abstract int getSampleRate();

    public boolean isReleased() {
        return this.mIsReleased;
    }

    public boolean isStarted() {
        return this.mIsStarted;
    }

    public abstract void release();

    public abstract int start();

    public abstract void stop();

    public void syncImage(AIUIMessage aIUIMessage) {
    }
}
