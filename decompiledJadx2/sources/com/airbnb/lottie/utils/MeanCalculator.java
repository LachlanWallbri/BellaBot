package com.airbnb.lottie.utils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MeanCalculator {

    /* renamed from: n */
    private int f294n;
    private float sum;

    public void add(float f) {
        this.sum += f;
        this.f294n++;
        int i = this.f294n;
        if (i == Integer.MAX_VALUE) {
            this.sum /= 2.0f;
            this.f294n = i / 2;
        }
    }

    public float getMean() {
        int i = this.f294n;
        if (i == 0) {
            return 0.0f;
        }
        return this.sum / i;
    }
}
