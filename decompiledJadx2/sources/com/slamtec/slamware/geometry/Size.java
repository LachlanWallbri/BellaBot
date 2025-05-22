package com.slamtec.slamware.geometry;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class Size {
    private int height;
    private int width;

    public Size() {
        this.width = 0;
        this.height = 0;
    }

    public Size(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public Size(Size size) {
        this.width = size.width;
        this.height = size.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }
}
