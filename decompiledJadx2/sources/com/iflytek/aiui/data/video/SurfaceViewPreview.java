package com.iflytek.aiui.data.video;

import android.content.Context;
import android.os.Build;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes4.dex */
class SurfaceViewPreview extends PreviewImpl {
    public SurfaceView mSurfaceView;

    /* renamed from: com.iflytek.aiui.data.video.SurfaceViewPreview$a */
    /* loaded from: classes4.dex */
    class SurfaceHolderCallbackC3551a implements SurfaceHolder.Callback {
        SurfaceHolderCallbackC3551a() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            SurfaceViewPreview.this.setSize(i2, i3);
            if (Build.VERSION.SDK_INT < 18 || !SurfaceViewPreview.this.mSurfaceView.isInLayout()) {
                SurfaceViewPreview.this.dispatchSurfaceChanged();
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            SurfaceViewPreview.this.setSize(0, 0);
        }
    }

    public SurfaceViewPreview(Context context, ViewGroup viewGroup) {
        SurfaceView surfaceView = new SurfaceView(context);
        this.mSurfaceView = surfaceView;
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        if (viewGroup != null) {
            viewGroup.addView(this.mSurfaceView);
        }
        SurfaceHolder holder = this.mSurfaceView.getHolder();
        holder.setType(3);
        holder.addCallback(new SurfaceHolderCallbackC3551a());
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public Class getOutputClass() {
        return SurfaceHolder.class;
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public Surface getSurface() {
        return getSurfaceHolder().getSurface();
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public SurfaceHolder getSurfaceHolder() {
        return this.mSurfaceView.getHolder();
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public View getView() {
        return this.mSurfaceView;
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public boolean isReady() {
        return (getWidth() == 0 || getHeight() == 0) ? false : true;
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public void setDisplayOrientation(int i) {
    }
}
