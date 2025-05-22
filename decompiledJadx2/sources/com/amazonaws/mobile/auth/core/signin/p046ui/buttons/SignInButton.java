package com.amazonaws.mobile.auth.core.signin.p046ui.buttons;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazonaws.mobile.auth.core.C1175R;
import com.amazonaws.mobile.auth.core.signin.p046ui.DisplayUtils;

/* loaded from: classes.dex */
public class SignInButton extends LinearLayout {
    private static final int BORDER_COLOR = -16777216;
    private static final float MIN_TEXT_SIZE_SP = 8.0f;
    private final SignInButtonAttributes attributes;
    protected Bitmap bitmap;
    protected ImageView imageView;
    protected boolean isSmallStyle;
    protected TextView textView;
    private static final int IMAGE_LEFT_MARGIN = DisplayUtils.m502dp(8);
    private static final int IMAGE_RIGHT_MARGIN = DisplayUtils.m502dp(8);
    private static final int TEXT_LEFT_MARGIN = DisplayUtils.m502dp(2);
    private static final int TEXT_RIGHT_MARGIN = DisplayUtils.m502dp(8);
    private static final float MAX_TEXT_SIZE_PX = DisplayUtils.m502dp(50);

    public SignInButton(Context context, AttributeSet attributeSet, int i, SignInButtonAttributes signInButtonAttributes) {
        super(context, attributeSet, i);
        this.isSmallStyle = false;
        this.attributes = signInButtonAttributes;
        setFocusable(true);
        setClickable(true);
        setOrientation(0);
        setGravity(16);
        setBackgroundDrawable(getBackgroundStatesDrawable());
        this.imageView = new ImageView(context);
        this.bitmap = BitmapFactory.decodeResource(getResources(), this.attributes.getImageIconResourceId());
        this.imageView.setImageDrawable(new BitmapDrawable(getResources(), this.bitmap));
        this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.imageView.setAdjustViewBounds(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(IMAGE_LEFT_MARGIN, 0, IMAGE_RIGHT_MARGIN, 0);
        layoutParams.weight = 0.0f;
        addView(this.imageView, layoutParams);
        this.textView = new TextView(context);
        this.textView.setTextColor(this.attributes.getTextColor());
        String str = null;
        this.textView.setTypeface(null, 1);
        this.textView.setSingleLine(true);
        this.textView.setGravity(16);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1175R.styleable.SignInButton);
            if (obtainStyledAttributes.getInt(C1175R.styleable.SignInButton_button_style, 0) > 0) {
                this.isSmallStyle = true;
            }
            str = obtainStyledAttributes.getString(C1175R.styleable.SignInButton_text);
            obtainStyledAttributes.recycle();
        }
        if (str != null) {
            this.textView.setText(str);
        } else {
            this.textView.setText(this.attributes.getDefaultTextResourceId());
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams2.setMargins(DisplayUtils.m502dp(TEXT_LEFT_MARGIN), 0, DisplayUtils.m502dp(TEXT_RIGHT_MARGIN), 0);
        layoutParams2.weight = 1.0f;
        addView(this.textView, layoutParams2);
        updateStyle();
        invalidate();
    }

    private Drawable getButtonBackground(int i) {
        ShapeDrawable roundedRectangleBackground = DisplayUtils.getRoundedRectangleBackground(this.attributes.getCornerRadius(), i);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{this.attributes.getTopShadowColor(), this.attributes.getTopShadowColor()});
        gradientDrawable.setCornerRadius(DisplayUtils.m502dp(r0));
        GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{this.attributes.getBottomShadowColor(), this.attributes.getBottomShadowColor()});
        gradientDrawable2.setCornerRadius(DisplayUtils.m502dp(r0));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, gradientDrawable2, roundedRectangleBackground});
        layerDrawable.setLayerInset(0, 0, 0, 0, 0);
        layerDrawable.setLayerInset(1, this.attributes.getTopShadowThickness(), this.attributes.getTopShadowThickness(), 0, 0);
        layerDrawable.setLayerInset(2, this.attributes.getTopShadowThickness(), this.attributes.getTopShadowThickness(), this.attributes.getBottomShadowThickness(), this.attributes.getBottomShadowThickness());
        return layerDrawable;
    }

    private Drawable getBackgroundStatesDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, getButtonBackground(this.attributes.getBackgroundColorPressed()));
        stateListDrawable.addState(new int[0], getButtonBackground(this.attributes.getBackgroundColor()));
        return stateListDrawable;
    }

    private void updateStyle() {
        if (this.isSmallStyle) {
            this.textView.setVisibility(8);
            setGravity(17);
        } else {
            this.textView.setVisibility(0);
            setGravity(16);
        }
    }

    public void setSmallStyle(boolean z) {
        this.isSmallStyle = z;
        updateStyle();
    }

    public void setButtonText(String str) {
        this.textView.setText(str);
        resizeButtonText();
    }

    public void setButtonText(int i) {
        this.textView.setText(i);
        resizeButtonText();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ViewGroup.LayoutParams layoutParams = this.imageView.getLayoutParams();
        int measuredHeight = (int) (getMeasuredHeight() * 0.72d);
        if (measuredHeight > this.bitmap.getHeight()) {
            measuredHeight = this.bitmap.getHeight();
        }
        layoutParams.height = measuredHeight;
        layoutParams.width = measuredHeight;
    }

    private boolean doesTextViewFit(float f, RectF rectF) {
        String charSequence;
        TextPaint textPaint = new TextPaint(this.textView.getPaint());
        textPaint.setTextSize(f);
        TransformationMethod transformationMethod = this.textView.getTransformationMethod();
        if (transformationMethod == null) {
            charSequence = this.textView.getText().toString();
        } else {
            charSequence = transformationMethod.getTransformation(this.textView.getText(), this.textView).toString();
        }
        return rectF.contains(new RectF(0.0f, 0.0f, textPaint.measureText(charSequence), textPaint.getFontSpacing()));
    }

    private float findBestSize(float f, float f2, RectF rectF) {
        float f3 = f;
        while (f <= f2) {
            float f4 = (f + f2) / 2.0f;
            if (doesTextViewFit(f4, rectF)) {
                f = f4 + 0.5f;
                f3 = f4;
            } else {
                f2 = f4 - 0.5f;
            }
        }
        return f3;
    }

    private void resizeButtonText() {
        if (getMeasuredWidth() == 0 || this.isSmallStyle) {
            return;
        }
        float applyDimension = TypedValue.applyDimension(2, MIN_TEXT_SIZE_SP, getResources().getDisplayMetrics());
        RectF rectF = new RectF();
        rectF.right = (this.textView.getMeasuredWidth() - this.textView.getCompoundPaddingLeft()) - this.textView.getCompoundPaddingRight();
        rectF.bottom = (this.textView.getMeasuredHeight() - this.textView.getCompoundPaddingBottom()) - this.textView.getCompoundPaddingTop();
        this.textView.setTextSize(0, findBestSize(applyDimension, MAX_TEXT_SIZE_PX, rectF));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        resizeButtonText();
    }
}
