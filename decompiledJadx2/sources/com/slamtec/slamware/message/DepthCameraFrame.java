package com.slamtec.slamware.message;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class DepthCameraFrame {
    private float minValidDistance = 0.0f;
    private float maxValidDistance = 0.0f;
    private float minFovPitch = 0.0f;
    private float maxFovPitch = 0.0f;
    private float minFovYaw = 0.0f;
    private float maxFovYaw = 0.0f;
    private int cols = 0;
    private int rows = 0;
    private ArrayList<Float> data = null;

    public float getMinValidDistance() {
        return this.minValidDistance;
    }

    public void setMinValidDistance(float f) {
        this.minValidDistance = f;
    }

    public float getMaxValidDistance() {
        return this.maxValidDistance;
    }

    public void setMaxValidDistance(float f) {
        this.maxValidDistance = f;
    }

    public float getMinFovPitch() {
        return this.minFovPitch;
    }

    public void setMinFovPitch(float f) {
        this.minFovPitch = f;
    }

    public float getMaxFovPitch() {
        return this.maxFovPitch;
    }

    public void setMaxFovPitch(float f) {
        this.maxFovPitch = f;
    }

    public float getMinFovYaw() {
        return this.minFovYaw;
    }

    public void setMinFovYaw(float f) {
        this.minFovYaw = f;
    }

    public float getMaxFovYaw() {
        return this.maxFovYaw;
    }

    public void setMaxFovYaw(float f) {
        this.maxFovYaw = f;
    }

    public int getCols() {
        return this.cols;
    }

    public void setCols(int i) {
        this.cols = i;
    }

    public int getRows() {
        return this.rows;
    }

    public void setRows(int i) {
        this.rows = i;
    }

    public ArrayList<Float> getData() {
        return this.data;
    }

    public void setData(ArrayList<Float> arrayList) {
        this.data = arrayList;
    }
}
