package com.google.android.material.transition;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes2.dex
 */
/* loaded from: classes.dex */
class FitModeResult {
    final float currentEndHeight;
    final float currentEndWidth;
    final float currentStartHeight;
    final float currentStartWidth;
    final float endScale;
    final float startScale;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FitModeResult(float f, float f2, float f3, float f4, float f5, float f6) {
        this.startScale = f;
        this.endScale = f2;
        this.currentStartWidth = f3;
        this.currentStartHeight = f4;
        this.currentEndWidth = f5;
        this.currentEndHeight = f6;
    }
}
