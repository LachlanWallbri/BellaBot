package com.iflytek.aiui.vad.sdk;

/* loaded from: classes4.dex */
public abstract class Vad {
    protected long mHandle = 0;
    protected VadListener mListener;

    /* loaded from: classes4.dex */
    public interface VadListener {
        void onAuthSuccess();

        void onError(int i, String str);

        void onResult(int i, byte[] bArr, int i2, int i3);
    }

    public abstract void destroy();

    public abstract int detect(byte[] bArr, int i);

    public abstract String getVersion();

    public abstract int requestAuth();

    public abstract int reset();

    public abstract int setParameter(String str, String str2);

    protected void setHandle(long j) {
        this.mHandle = j;
    }

    protected void onAuthSuccessCb() {
        VadListener vadListener = this.mListener;
        if (vadListener != null) {
            vadListener.onAuthSuccess();
        }
    }

    protected void onErrorCb(int i, String str) {
        VadListener vadListener = this.mListener;
        if (vadListener != null) {
            vadListener.onError(i, str);
        }
    }

    protected void onResultCb(int i, byte[] bArr, int i2, int i3) {
        VadListener vadListener = this.mListener;
        if (vadListener != null) {
            vadListener.onResult(i, bArr, i2, i3);
        }
    }
}
