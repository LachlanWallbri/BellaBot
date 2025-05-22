package com.iflytek.aiui.data.video;

import com.iflytek.aiui.pro.AbstractC3617h0;
import com.iflytek.aiui.pro.C3633p0;
import java.util.Set;
import java.util.SortedSet;

/* renamed from: com.iflytek.aiui.data.video.b */
/* loaded from: classes4.dex */
abstract class AbstractC3554b extends AbstractC3617h0 {
    protected static final C3553a DEFAULT_ASPECT_RATIO = C3553a.m817d(16, 9);
    protected static final int FACING_BACK = 0;
    protected static final int FACING_FRONT = 1;
    protected static final int FLASH_AUTO = 3;
    protected static final int FLASH_OFF = 0;
    protected static final int FLASH_ON = 1;
    protected static final int FLASH_RED_EYE = 4;
    protected static final int FLASH_TORCH = 2;
    protected static final int LANDSCAPE_270 = 270;
    protected static final int LANDSCAPE_90 = 90;
    protected static final int MAX_PX = 921600;
    protected C3553a mAspectRatio;
    protected float mCameraScale;
    protected PreviewImpl mPreview;
    protected int maxImagePixel;

    /* renamed from: com.iflytek.aiui.data.video.b$a */
    /* loaded from: classes4.dex */
    interface a extends AbstractC3617h0.a {
        /* renamed from: c */
        void m824c();

        /* renamed from: f */
        void m825f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC3554b(PreviewImpl previewImpl, AbstractC3617h0.a aVar) {
        super(aVar);
        this.mPreview = previewImpl;
        this.maxImagePixel = C3633p0.m1473b("recorder", "cam_max_px", MAX_PX);
        this.mAspectRatio = C3553a.m818e(C3633p0.m1477f("recorder", "cam_aspect_ratio", DEFAULT_ASPECT_RATIO.toString()));
        this.mCameraScale = C3633p0.m1473b("recorder", "cam_zoom", 0) / 100.0f;
        setFacing(C3633p0.m1473b("recorder", "cam_facing", 1));
    }

    protected C3557e chooseImageReaderSize(SortedSet<C3557e> sortedSet) {
        C3557e first = sortedSet.first();
        for (C3557e c3557e : sortedSet) {
            if (c3557e.m830b() * c3557e.m831c() <= this.maxImagePixel) {
                first = c3557e;
            }
        }
        return first;
    }

    abstract C3553a getAspectRatio();

    abstract boolean getAutoFocus();

    abstract int getDisplayOrientation();

    abstract int getFacing();

    abstract int getFlash();

    abstract C3557e getPreviewSize();

    abstract Set<C3553a> getSupportedAspectRatios();

    abstract boolean isCameraOpened();

    abstract boolean setAspectRatio(C3553a c3553a);

    abstract void setAutoFocus(boolean z);

    abstract void setDisplayOrientation(int i);

    abstract void setFacing(int i);

    abstract void setFlash(int i);

    abstract void takePicture();
}
