package com.google.android.material.progressindicator;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.C1653R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;

/* loaded from: classes2.dex */
public abstract class BaseProgressIndicatorSpec {
    public int hideAnimationBehavior;
    public int[] indicatorColors = new int[0];
    public int showAnimationBehavior;
    public int trackColor;
    public int trackCornerRadius;
    public int trackThickness;

    abstract void validateSpec();

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseProgressIndicatorSpec(Context context, AttributeSet attributeSet, int i, int i2) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(C1653R.dimen.mtrl_progress_track_thickness);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C1653R.styleable.BaseProgressIndicator, i, i2, new int[0]);
        this.trackThickness = MaterialResources.getDimensionPixelSize(context, obtainStyledAttributes, C1653R.styleable.BaseProgressIndicator_trackThickness, dimensionPixelSize);
        this.trackCornerRadius = Math.min(MaterialResources.getDimensionPixelSize(context, obtainStyledAttributes, C1653R.styleable.BaseProgressIndicator_trackCornerRadius, 0), this.trackThickness / 2);
        this.showAnimationBehavior = obtainStyledAttributes.getInt(C1653R.styleable.BaseProgressIndicator_showAnimationBehavior, 0);
        this.hideAnimationBehavior = obtainStyledAttributes.getInt(C1653R.styleable.BaseProgressIndicator_hideAnimationBehavior, 0);
        loadIndicatorColors(context, obtainStyledAttributes);
        loadTrackColor(context, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    private void loadIndicatorColors(Context context, TypedArray typedArray) {
        if (!typedArray.hasValue(C1653R.styleable.BaseProgressIndicator_indicatorColor)) {
            this.indicatorColors = new int[]{MaterialColors.getColor(context, C1653R.attr.colorPrimary, -1)};
        } else {
            if (typedArray.peekValue(C1653R.styleable.BaseProgressIndicator_indicatorColor).type != 1) {
                this.indicatorColors = new int[]{typedArray.getColor(C1653R.styleable.BaseProgressIndicator_indicatorColor, -1)};
                return;
            }
            this.indicatorColors = context.getResources().getIntArray(typedArray.getResourceId(C1653R.styleable.BaseProgressIndicator_indicatorColor, -1));
            if (this.indicatorColors.length == 0) {
                throw new IllegalArgumentException("indicatorColors cannot be empty when indicatorColor is not used.");
            }
        }
    }

    private void loadTrackColor(Context context, TypedArray typedArray) {
        if (typedArray.hasValue(C1653R.styleable.BaseProgressIndicator_trackColor)) {
            this.trackColor = typedArray.getColor(C1653R.styleable.BaseProgressIndicator_trackColor, -1);
            return;
        }
        this.trackColor = this.indicatorColors[0];
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.disabledAlpha});
        float f = obtainStyledAttributes.getFloat(0, 0.2f);
        obtainStyledAttributes.recycle();
        this.trackColor = MaterialColors.compositeARGBWithAlpha(this.trackColor, (int) (f * 255.0f));
    }

    public boolean isShowAnimationEnabled() {
        return this.showAnimationBehavior != 0;
    }

    public boolean isHideAnimationEnabled() {
        return this.hideAnimationBehavior != 0;
    }
}
