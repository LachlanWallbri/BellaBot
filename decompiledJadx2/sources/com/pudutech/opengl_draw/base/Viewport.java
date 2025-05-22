package com.pudutech.opengl_draw.base;

import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class Viewport {
    private final int height;
    private final int width;

    public Viewport(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public void apply(GL10 gl10) {
        gl10.glViewport(0, 0, this.width, this.height);
        gl10.glMatrixMode(5889);
        gl10.glLoadIdentity();
        gl10.glOrthof(this.width / 2.0f, (-r0) / 2.0f, (-r0) / 2.0f, this.height / 2.0f, -10000.0f, 10000.0f);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
