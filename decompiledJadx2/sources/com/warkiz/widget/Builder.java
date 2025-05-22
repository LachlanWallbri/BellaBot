package com.warkiz.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Builder {
    final Context context;
    int indicatorTextSize;
    boolean showTickText;
    int thumbSize;
    int tickMarksSize;
    int tickTextsSize;
    int trackBackgroundSize;
    int trackProgressSize;
    float max = 100.0f;
    float min = 0.0f;
    float progress = 0.0f;
    boolean progressValueFloat = false;
    boolean seekSmoothly = false;
    boolean r2l = false;
    boolean userSeekable = true;
    boolean onlyThumbDraggable = false;
    boolean clearPadding = false;
    int showIndicatorType = 2;
    int indicatorColor = Color.parseColor("#FF4081");
    int indicatorTextColor = Color.parseColor("#FFFFFF");
    View indicatorContentView = null;
    View indicatorTopContentView = null;
    int trackBackgroundColor = Color.parseColor("#D7D7D7");
    int trackProgressColor = Color.parseColor("#FF4081");
    boolean trackRoundedCorners = false;
    int thumbTextColor = Color.parseColor("#FF4081");
    boolean showThumbText = false;
    int thumbColor = Color.parseColor("#FF4081");
    ColorStateList thumbColorStateList = null;
    Drawable thumbDrawable = null;
    int tickTextsColor = Color.parseColor("#FF4081");
    String[] tickTextsCustomArray = null;
    Typeface tickTextsTypeFace = Typeface.DEFAULT;
    ColorStateList tickTextsColorStateList = null;
    int tickCount = 0;
    int showTickMarksType = 0;
    int tickMarksColor = Color.parseColor("#FF4081");
    Drawable tickMarksDrawable = null;
    boolean tickMarksEndsHide = false;
    boolean tickMarksSweptHide = false;
    ColorStateList tickMarksColorStateList = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Builder(Context context) {
        this.indicatorTextSize = 0;
        this.trackBackgroundSize = 0;
        this.trackProgressSize = 0;
        this.thumbSize = 0;
        this.tickTextsSize = 0;
        this.tickMarksSize = 0;
        this.context = context;
        this.indicatorTextSize = SizeUtils.sp2px(context, 14.0f);
        this.trackBackgroundSize = SizeUtils.dp2px(context, 2.0f);
        this.trackProgressSize = SizeUtils.dp2px(context, 2.0f);
        this.tickMarksSize = SizeUtils.dp2px(context, 10.0f);
        this.tickTextsSize = SizeUtils.sp2px(context, 13.0f);
        this.thumbSize = SizeUtils.dp2px(context, 14.0f);
    }

    public IndicatorSeekBar build() {
        return new IndicatorSeekBar(this);
    }

    public Builder max(float f) {
        this.max = f;
        return this;
    }

    public Builder min(float f) {
        this.min = f;
        return this;
    }

    public Builder progress(float f) {
        this.progress = f;
        return this;
    }

    public Builder progressValueFloat(boolean z) {
        this.progressValueFloat = z;
        return this;
    }

    public Builder seekSmoothly(boolean z) {
        this.seekSmoothly = z;
        return this;
    }

    public Builder r2l(boolean z) {
        this.r2l = z;
        return this;
    }

    public Builder clearPadding(boolean z) {
        this.clearPadding = z;
        return this;
    }

    public Builder userSeekable(boolean z) {
        this.userSeekable = z;
        return this;
    }

    public Builder onlyThumbDraggable(boolean z) {
        this.onlyThumbDraggable = z;
        return this;
    }

    public Builder showIndicatorType(int i) {
        this.showIndicatorType = i;
        return this;
    }

    public Builder indicatorColor(int i) {
        this.indicatorColor = i;
        return this;
    }

    public Builder indicatorTextColor(int i) {
        this.indicatorTextColor = i;
        return this;
    }

    public Builder indicatorTextSize(int i) {
        this.indicatorTextSize = SizeUtils.sp2px(this.context, i);
        return this;
    }

    public Builder indicatorContentView(View view) {
        this.indicatorContentView = view;
        return this;
    }

    public Builder indicatorContentViewLayoutId(int i) {
        this.indicatorContentView = View.inflate(this.context, i, null);
        return this;
    }

    public Builder indicatorTopContentView(View view) {
        this.indicatorTopContentView = view;
        return this;
    }

    public Builder indicatorTopContentViewLayoutId(int i) {
        this.indicatorTopContentView = View.inflate(this.context, i, null);
        return this;
    }

    public Builder trackBackgroundSize(int i) {
        this.trackBackgroundSize = SizeUtils.dp2px(this.context, i);
        return this;
    }

    public Builder trackBackgroundColor(int i) {
        this.trackBackgroundColor = i;
        return this;
    }

    public Builder trackProgressSize(int i) {
        this.trackProgressSize = SizeUtils.dp2px(this.context, i);
        return this;
    }

    public Builder trackProgressColor(int i) {
        this.trackProgressColor = i;
        return this;
    }

    public Builder trackRoundedCorners(boolean z) {
        this.trackRoundedCorners = z;
        return this;
    }

    public Builder thumbTextColor(int i) {
        this.thumbTextColor = i;
        return this;
    }

    public Builder showThumbText(boolean z) {
        this.showThumbText = z;
        return this;
    }

    public Builder thumbColor(int i) {
        this.thumbColor = i;
        return this;
    }

    public Builder thumbColorStateList(ColorStateList colorStateList) {
        this.thumbColorStateList = colorStateList;
        return this;
    }

    public Builder thumbSize(int i) {
        this.thumbSize = SizeUtils.dp2px(this.context, i);
        return this;
    }

    public Builder thumbDrawable(Drawable drawable) {
        this.thumbDrawable = drawable;
        return this;
    }

    public Builder thumbDrawable(StateListDrawable stateListDrawable) {
        this.thumbDrawable = stateListDrawable;
        return this;
    }

    public Builder showTickTexts(boolean z) {
        this.showTickText = z;
        return this;
    }

    public Builder tickTextsColor(int i) {
        this.tickTextsColor = i;
        return this;
    }

    public Builder tickTextsColorStateList(ColorStateList colorStateList) {
        this.tickTextsColorStateList = colorStateList;
        return this;
    }

    public Builder tickTextsSize(int i) {
        this.tickTextsSize = SizeUtils.sp2px(this.context, i);
        return this;
    }

    public Builder tickTextsArray(String[] strArr) {
        this.tickTextsCustomArray = strArr;
        return this;
    }

    public Builder tickTextsArray(int i) {
        this.tickTextsCustomArray = this.context.getResources().getStringArray(i);
        return this;
    }

    public Builder tickTextsTypeFace(Typeface typeface) {
        this.tickTextsTypeFace = typeface;
        return this;
    }

    public Builder tickCount(int i) {
        this.tickCount = i;
        return this;
    }

    public Builder showTickMarksType(int i) {
        this.showTickMarksType = i;
        return this;
    }

    public Builder tickMarksColor(int i) {
        this.tickMarksColor = i;
        return this;
    }

    public Builder tickMarksColor(ColorStateList colorStateList) {
        this.tickMarksColorStateList = colorStateList;
        return this;
    }

    public Builder tickMarksSize(int i) {
        this.tickMarksSize = SizeUtils.dp2px(this.context, i);
        return this;
    }

    public Builder tickMarksDrawable(Drawable drawable) {
        this.tickMarksDrawable = drawable;
        return this;
    }

    public Builder tickMarksDrawable(StateListDrawable stateListDrawable) {
        this.tickMarksDrawable = stateListDrawable;
        return this;
    }

    public Builder tickMarksEndsHide(boolean z) {
        this.tickMarksEndsHide = z;
        return this;
    }

    public Builder tickMarksSweptHide(boolean z) {
        this.tickMarksSweptHide = z;
        return this;
    }
}
