package com.pudutech.opengl_draw.geometry;

/* loaded from: classes5.dex */
public class LazyFrameTransform {
    private FrameTransform frameTransform;
    private final Object mutex = new Object();

    LazyFrameTransform(FrameTransform frameTransform) {
        this.frameTransform = frameTransform;
    }

    public FrameTransform get() {
        synchronized (this.mutex) {
            if (this.frameTransform != null) {
                return this.frameTransform;
            }
            return this.frameTransform;
        }
    }
}
