package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public interface DrawingDelegate {
    void adjustCanvas(Canvas canvas, ProgressIndicatorSpec progressIndicatorSpec, float f);

    void fillTrackWithColor(Canvas canvas, Paint paint, int i, float f, float f2, float f3, float f4);

    int getPreferredHeight(ProgressIndicatorSpec progressIndicatorSpec);

    int getPreferredWidth(ProgressIndicatorSpec progressIndicatorSpec);
}
