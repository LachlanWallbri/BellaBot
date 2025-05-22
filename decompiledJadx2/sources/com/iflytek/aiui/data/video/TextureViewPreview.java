package com.iflytek.aiui.data.video;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes4.dex */
class TextureViewPreview extends PreviewImpl {
    private int mDisplayOrientation;
    private final TextureView mTextureView;

    /* renamed from: com.iflytek.aiui.data.video.TextureViewPreview$a */
    /* loaded from: classes4.dex */
    class TextureViewSurfaceTextureListenerC3552a implements TextureView.SurfaceTextureListener {
        TextureViewSurfaceTextureListenerC3552a() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            TextureViewPreview.this.setSize(i, i2);
            TextureViewPreview.this.configureTransform();
            TextureViewPreview.this.dispatchSurfaceChanged();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            TextureViewPreview.this.setSize(0, 0);
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            TextureViewPreview.this.setSize(i, i2);
            TextureViewPreview.this.configureTransform();
            TextureViewPreview.this.dispatchSurfaceChanged();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextureViewPreview(Context context, ViewGroup viewGroup) {
        TextureView textureView = new TextureView(context);
        this.mTextureView = textureView;
        textureView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        if (viewGroup != null) {
            viewGroup.addView(textureView);
        }
        textureView.setSurfaceTextureListener(new TextureViewSurfaceTextureListenerC3552a());
    }

    void configureTransform() {
        Matrix matrix = new Matrix();
        int i = this.mDisplayOrientation;
        if (i % 180 == 90) {
            float width = getWidth();
            float height = getHeight();
            matrix.setPolyToPoly(new float[]{0.0f, 0.0f, width, 0.0f, 0.0f, height, width, height}, 0, this.mDisplayOrientation == 90 ? new float[]{0.0f, height, 0.0f, 0.0f, width, height, width, 0.0f} : new float[]{width, 0.0f, width, height, 0.0f, 0.0f, 0.0f, height}, 0, 4);
        } else if (i == 180) {
            matrix.postRotate(180.0f, getWidth() / 2, getHeight() / 2);
        }
        this.mTextureView.setTransform(matrix);
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public Class getOutputClass() {
        return SurfaceTexture.class;
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public Surface getSurface() {
        return new Surface(this.mTextureView.getSurfaceTexture());
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public SurfaceTexture getSurfaceTexture() {
        return this.mTextureView.getSurfaceTexture();
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public View getView() {
        return this.mTextureView;
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public boolean isReady() {
        return this.mTextureView.getSurfaceTexture() != null;
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public void setBufferSize(int i, int i2) {
        this.mTextureView.getSurfaceTexture().setDefaultBufferSize(i, i2);
    }

    @Override // com.iflytek.aiui.data.video.PreviewImpl
    public void setDisplayOrientation(int i) {
        this.mDisplayOrientation = i;
        configureTransform();
    }
}
