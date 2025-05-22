package com.pudutech.opengl_draw.bean;

import android.graphics.Bitmap;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.geometry.Transform;

/* loaded from: classes5.dex */
public class Point {
    private Bitmap bitmap;
    private Color color;
    private String name;
    private Transform transform;

    public Point(String str, Transform transform) {
        this.name = str;
        this.transform = transform;
    }

    public Point(String str, Transform transform, Bitmap bitmap) {
        this.name = str;
        this.transform = transform;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Transform getTransform() {
        return this.transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
