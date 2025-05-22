package com.iflytek.aiui.data.video;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;

/* loaded from: classes4.dex */
public abstract class PreviewImpl {
    private Callback mCallback;
    private int mHeight;
    private int mWidth;

    /* loaded from: classes4.dex */
    public interface Callback {
        void onSurfaceChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dispatchSurfaceChanged() {
        this.mCallback.onSurfaceChanged();
    }

    public int getHeight() {
        return this.mHeight;
    }

    public abstract Class getOutputClass();

    public abstract Surface getSurface();

    public SurfaceHolder getSurfaceHolder() {
        return null;
    }

    public Object getSurfaceTexture() {
        return null;
    }

    public abstract View getView();

    public int getWidth() {
        return this.mWidth;
    }

    public abstract boolean isReady();

    public void setBufferSize(int i, int i2) {
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public abstract void setDisplayOrientation(int i);

    public void setSize(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }
}
