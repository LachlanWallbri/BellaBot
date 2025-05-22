package com.pudutech.opengl_draw.layer;

/* loaded from: classes5.dex */
public interface CameraControlListener {
    void onDoubleTap(float f, float f2);

    void onMoveUP();

    void onRotate(float f, float f2, double d);

    void onTranslate(float f, float f2);

    void onZoom(float f, float f2, float f3);
}
