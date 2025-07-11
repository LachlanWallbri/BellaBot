package com.google.android.material.bottomappbar;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private float cradleVerticalOffset;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float f, float f2, float f3) {
        this.fabMargin = f;
        this.roundedCornerRadius = f2;
        setCradleVerticalOffset(f3);
        this.horizontalOffset = 0.0f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f2, float f3, ShapePath shapePath) {
        float f4 = this.fabDiameter;
        if (f4 == 0.0f) {
            shapePath.lineTo(f, 0.0f);
            return;
        }
        float f5 = ((this.fabMargin * 2.0f) + f4) / 2.0f;
        float f6 = f3 * this.roundedCornerRadius;
        float f7 = f2 + this.horizontalOffset;
        float f8 = (this.cradleVerticalOffset * f3) + ((1.0f - f3) * f5);
        if (f8 / f5 >= 1.0f) {
            shapePath.lineTo(f, 0.0f);
            return;
        }
        float f9 = f5 + f6;
        float f10 = f8 + f6;
        float sqrt = (float) Math.sqrt((f9 * f9) - (f10 * f10));
        float f11 = f7 - sqrt;
        float f12 = f7 + sqrt;
        float degrees = (float) Math.toDegrees(Math.atan(sqrt / f10));
        float f13 = 90.0f - degrees;
        shapePath.lineTo(f11, 0.0f);
        float f14 = f6 * 2.0f;
        shapePath.addArc(f11 - f6, 0.0f, f11 + f6, f14, 270.0f, degrees);
        shapePath.addArc(f7 - f5, (-f5) - f8, f7 + f5, f5 - f8, 180.0f - f13, (f13 * 2.0f) - 180.0f);
        shapePath.addArc(f12 - f6, 0.0f, f12 + f6, f14, 270.0f - degrees, degrees);
        shapePath.lineTo(f, 0.0f);
    }

    public float getFabDiameter() {
        return this.fabDiameter;
    }

    public void setFabDiameter(float f) {
        this.fabDiameter = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHorizontalOffset(float f) {
        this.horizontalOffset = f;
    }

    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCradleVerticalOffset(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.cradleVerticalOffset = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFabCradleMargin(float f) {
        this.fabMargin = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFabCradleRoundedCornerRadius(float f) {
        this.roundedCornerRadius = f;
    }
}
