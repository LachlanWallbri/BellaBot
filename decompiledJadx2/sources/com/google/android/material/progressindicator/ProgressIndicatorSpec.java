package com.google.android.material.progressindicator;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.C1653R;
import com.google.android.material.color.MaterialColors;

/* loaded from: classes.dex */
public final class ProgressIndicatorSpec {
    public int circularInset;
    public int circularRadius;
    public int growMode;
    public int[] indicatorColors;
    public int indicatorCornerRadius;
    public int indicatorType;
    public int indicatorWidth;
    public boolean inverse;
    public boolean linearSeamless;
    public int trackColor;

    public void loadFromAttributes(Context context, AttributeSet attributeSet, int i) {
        loadFromAttributes(context, attributeSet, i, ProgressIndicator.DEF_STYLE_RES);
    }

    public void loadFromAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1653R.styleable.ProgressIndicator, i, i2);
        this.indicatorType = obtainStyledAttributes.getInt(C1653R.styleable.ProgressIndicator_indicatorType, 0);
        this.indicatorWidth = getDimensionPixelSize(context, obtainStyledAttributes, C1653R.styleable.ProgressIndicator_indicatorWidth, C1653R.dimen.mtrl_progress_indicator_width);
        this.circularInset = getDimensionPixelSize(context, obtainStyledAttributes, C1653R.styleable.ProgressIndicator_circularInset, C1653R.dimen.mtrl_progress_circular_inset);
        this.circularRadius = getDimensionPixelSize(context, obtainStyledAttributes, C1653R.styleable.ProgressIndicator_circularRadius, C1653R.dimen.mtrl_progress_circular_radius);
        this.inverse = obtainStyledAttributes.getBoolean(C1653R.styleable.ProgressIndicator_inverse, false);
        this.growMode = obtainStyledAttributes.getInt(C1653R.styleable.ProgressIndicator_growMode, 0);
        loadIndicatorColors(context, obtainStyledAttributes);
        loadTrackColor(context, obtainStyledAttributes);
        this.linearSeamless = obtainStyledAttributes.getBoolean(C1653R.styleable.ProgressIndicator_linearSeamless, true) && this.indicatorType == 0 && this.indicatorColors.length >= 3;
        this.indicatorCornerRadius = Math.min(obtainStyledAttributes.getDimensionPixelSize(C1653R.styleable.ProgressIndicator_indicatorCornerRadius, 0), this.indicatorWidth / 2);
        obtainStyledAttributes.recycle();
        validate();
    }

    public void validate() {
        if (this.indicatorType == 1 && this.circularRadius < this.indicatorWidth / 2) {
            throw new IllegalArgumentException("The circularRadius cannot be less than half of the indicatorWidth.");
        }
        if (this.linearSeamless && this.indicatorCornerRadius > 0) {
            throw new IllegalArgumentException("Rounded corners are not supported in linear seamless mode.");
        }
    }

    private static int getDimensionPixelSize(Context context, TypedArray typedArray, int i, int i2) {
        return typedArray.getDimensionPixelSize(i, context.getResources().getDimensionPixelSize(i2));
    }

    private void loadIndicatorColors(Context context, TypedArray typedArray) {
        int color;
        if (typedArray.hasValue(C1653R.styleable.ProgressIndicator_indicatorColors)) {
            this.indicatorColors = context.getResources().getIntArray(typedArray.getResourceId(C1653R.styleable.ProgressIndicator_indicatorColors, -1));
            if (typedArray.hasValue(C1653R.styleable.ProgressIndicator_indicatorColor)) {
                throw new IllegalArgumentException("Attributes indicatorColors and indicatorColor cannot be used at the same time.");
            }
            if (this.indicatorColors.length == 0) {
                throw new IllegalArgumentException("indicatorColors cannot be empty when indicatorColor is not used.");
            }
            return;
        }
        int[] iArr = new int[1];
        if (typedArray.hasValue(C1653R.styleable.ProgressIndicator_indicatorColor)) {
            color = typedArray.getColor(C1653R.styleable.ProgressIndicator_indicatorColor, -1);
        } else {
            color = MaterialColors.getColor(context, C1653R.attr.colorPrimary, -1);
        }
        iArr[0] = color;
        this.indicatorColors = iArr;
    }

    private void loadTrackColor(Context context, TypedArray typedArray) {
        if (typedArray.hasValue(C1653R.styleable.ProgressIndicator_trackColor)) {
            this.trackColor = typedArray.getColor(C1653R.styleable.ProgressIndicator_trackColor, -1);
            return;
        }
        this.trackColor = this.indicatorColors[0];
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.disabledAlpha});
        float f = obtainStyledAttributes.getFloat(0, 0.2f);
        obtainStyledAttributes.recycle();
        this.trackColor = MaterialColors.compositeARGBWithAlpha(this.trackColor, (int) (f * 255.0f));
    }
}
