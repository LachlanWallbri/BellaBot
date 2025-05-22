package com.warkiz.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Indicator {
    private ArrowView mArrowView;
    private Context mContext;
    private int mGap;
    private int mIndicatorColor;
    private View mIndicatorCustomTopContentView;
    private View mIndicatorCustomView;
    private PopupWindow mIndicatorPopW;
    private int mIndicatorTextColor;
    private float mIndicatorTextSize;
    private int mIndicatorType;
    private View mIndicatorView;
    private TextView mProgressTextView;
    private IndicatorSeekBar mSeekBar;
    private LinearLayout mTopContentView;
    private int[] mLocation = new int[2];
    private final int mWindowWidth = getWindowWidth();

    public Indicator(Context context, IndicatorSeekBar indicatorSeekBar, int i, int i2, int i3, int i4, View view, View view2) {
        this.mContext = context;
        this.mSeekBar = indicatorSeekBar;
        this.mIndicatorColor = i;
        this.mIndicatorType = i2;
        this.mIndicatorCustomView = view;
        this.mIndicatorCustomTopContentView = view2;
        this.mIndicatorTextSize = i3;
        this.mIndicatorTextColor = i4;
        this.mGap = SizeUtils.dp2px(this.mContext, 2.0f);
        initIndicator();
    }

    private void initIndicator() {
        View findViewById;
        int i = this.mIndicatorType;
        if (i == 4) {
            View view = this.mIndicatorCustomView;
            if (view != null) {
                this.mIndicatorView = view;
                int identifier = this.mContext.getResources().getIdentifier("isb_progress", "id", this.mContext.getApplicationContext().getPackageName());
                if (identifier <= 0 || (findViewById = this.mIndicatorView.findViewById(identifier)) == null) {
                    return;
                }
                if (findViewById instanceof TextView) {
                    this.mProgressTextView = (TextView) findViewById;
                    this.mProgressTextView.setText(this.mSeekBar.getIndicatorTextString());
                    this.mProgressTextView.setTextSize(SizeUtils.px2sp(this.mContext, this.mIndicatorTextSize));
                    this.mProgressTextView.setTextColor(this.mIndicatorTextColor);
                    return;
                }
                throw new ClassCastException("the view identified by isb_progress in indicator custom layout can not be cast to TextView");
            }
            throw new IllegalArgumentException("the attr：indicator_custom_layout must be set while you set the indicator type to CUSTOM.");
        }
        if (i == 1) {
            this.mIndicatorView = new CircleBubbleView(this.mContext, this.mIndicatorTextSize, this.mIndicatorTextColor, this.mIndicatorColor, "1000");
            ((CircleBubbleView) this.mIndicatorView).setProgress(this.mSeekBar.getIndicatorTextString());
            return;
        }
        this.mIndicatorView = View.inflate(this.mContext, C5949R.layout.isb_indicator, null);
        this.mTopContentView = (LinearLayout) this.mIndicatorView.findViewById(C5949R.id.indicator_container);
        this.mArrowView = (ArrowView) this.mIndicatorView.findViewById(C5949R.id.indicator_arrow);
        this.mArrowView.setColor(this.mIndicatorColor);
        this.mProgressTextView = (TextView) this.mIndicatorView.findViewById(C5949R.id.isb_progress);
        this.mProgressTextView.setText(this.mSeekBar.getIndicatorTextString());
        this.mProgressTextView.setTextSize(SizeUtils.px2sp(this.mContext, this.mIndicatorTextSize));
        this.mProgressTextView.setTextColor(this.mIndicatorTextColor);
        if (Build.VERSION.SDK_INT >= 16) {
            this.mTopContentView.setBackground(getGradientDrawable());
        } else {
            this.mTopContentView.setBackgroundDrawable(getGradientDrawable());
        }
        if (this.mIndicatorCustomTopContentView != null) {
            int identifier2 = this.mContext.getResources().getIdentifier("isb_progress", "id", this.mContext.getApplicationContext().getPackageName());
            View view2 = this.mIndicatorCustomTopContentView;
            if (identifier2 > 0) {
                View findViewById2 = view2.findViewById(identifier2);
                if (findViewById2 != null && (findViewById2 instanceof TextView)) {
                    setTopContentView(view2, (TextView) findViewById2);
                    return;
                } else {
                    setTopContentView(view2);
                    return;
                }
            }
            setTopContentView(view2);
        }
    }

    private GradientDrawable getGradientDrawable() {
        GradientDrawable gradientDrawable;
        if (this.mIndicatorType == 2) {
            gradientDrawable = (GradientDrawable) this.mContext.getResources().getDrawable(C5949R.drawable.isb_indicator_rounded_corners);
        } else {
            gradientDrawable = (GradientDrawable) this.mContext.getResources().getDrawable(C5949R.drawable.isb_indicator_square_corners);
        }
        gradientDrawable.setColor(this.mIndicatorColor);
        return gradientDrawable;
    }

    private int getWindowWidth() {
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        if (windowManager != null) {
            return windowManager.getDefaultDisplay().getWidth();
        }
        return 0;
    }

    private int getIndicatorScreenX() {
        this.mSeekBar.getLocationOnScreen(this.mLocation);
        return this.mLocation[0];
    }

    private void adjustArrow(float f) {
        int i = this.mIndicatorType;
        if (i == 4 || i == 1) {
            return;
        }
        if (getIndicatorScreenX() + f < this.mIndicatorPopW.getContentView().getMeasuredWidth() / 2) {
            setMargin(this.mArrowView, -((int) (((this.mIndicatorPopW.getContentView().getMeasuredWidth() / 2) - r0) - f)), -1, -1, -1);
        } else if ((this.mWindowWidth - r0) - f < this.mIndicatorPopW.getContentView().getMeasuredWidth() / 2) {
            setMargin(this.mArrowView, (int) ((this.mIndicatorPopW.getContentView().getMeasuredWidth() / 2) - ((this.mWindowWidth - r0) - f)), -1, -1, -1);
        } else {
            setMargin(this.mArrowView, 0, 0, 0, 0);
        }
    }

    private void setMargin(View view, int i, int i2, int i3, int i4) {
        if (view != null && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (i == -1) {
                i = marginLayoutParams.leftMargin;
            }
            if (i2 == -1) {
                i2 = marginLayoutParams.topMargin;
            }
            if (i3 == -1) {
                i3 = marginLayoutParams.rightMargin;
            }
            if (i4 == -1) {
                i4 = marginLayoutParams.bottomMargin;
            }
            marginLayoutParams.setMargins(i, i2, i3, i4);
            view.requestLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void iniPop() {
        View view;
        if (this.mIndicatorPopW != null || this.mIndicatorType == 0 || (view = this.mIndicatorView) == null) {
            return;
        }
        view.measure(0, 0);
        this.mIndicatorPopW = new PopupWindow(this.mIndicatorView, -2, -2, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getInsideContentView() {
        return this.mIndicatorView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProgressTextView(String str) {
        View view = this.mIndicatorView;
        if (view instanceof CircleBubbleView) {
            ((CircleBubbleView) view).setProgress(str);
            return;
        }
        TextView textView = this.mProgressTextView;
        if (textView != null) {
            textView.setText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateIndicatorLocation(int i) {
        setMargin(this.mIndicatorView, i, -1, -1, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateArrowViewLocation(int i) {
        setMargin(this.mArrowView, i, -1, -1, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void update(float f) {
        if (this.mSeekBar.isEnabled() && this.mSeekBar.getVisibility() == 0) {
            refreshProgressText();
            PopupWindow popupWindow = this.mIndicatorPopW;
            if (popupWindow != null) {
                popupWindow.getContentView().measure(0, 0);
                this.mIndicatorPopW.update(this.mSeekBar, (int) (f - (r2.getContentView().getMeasuredWidth() / 2)), -(((this.mSeekBar.getMeasuredHeight() + this.mIndicatorPopW.getContentView().getMeasuredHeight()) - this.mSeekBar.getPaddingTop()) + this.mGap), -1, -1);
                adjustArrow(f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void show(float f) {
        if (this.mSeekBar.isEnabled() && this.mSeekBar.getVisibility() == 0) {
            refreshProgressText();
            PopupWindow popupWindow = this.mIndicatorPopW;
            if (popupWindow != null) {
                popupWindow.getContentView().measure(0, 0);
                this.mIndicatorPopW.showAsDropDown(this.mSeekBar, (int) (f - (r0.getContentView().getMeasuredWidth() / 2.0f)), -(((this.mSeekBar.getMeasuredHeight() + this.mIndicatorPopW.getContentView().getMeasuredHeight()) - this.mSeekBar.getPaddingTop()) + this.mGap));
                adjustArrow(f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void refreshProgressText() {
        String indicatorTextString = this.mSeekBar.getIndicatorTextString();
        View view = this.mIndicatorView;
        if (view instanceof CircleBubbleView) {
            ((CircleBubbleView) view).setProgress(indicatorTextString);
            return;
        }
        TextView textView = this.mProgressTextView;
        if (textView != null) {
            textView.setText(indicatorTextString);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hide() {
        PopupWindow popupWindow = this.mIndicatorPopW;
        if (popupWindow == null) {
            return;
        }
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isShowing() {
        PopupWindow popupWindow = this.mIndicatorPopW;
        return popupWindow != null && popupWindow.isShowing();
    }

    public View getContentView() {
        return this.mIndicatorView;
    }

    public void setContentView(View view) {
        this.mIndicatorType = 4;
        this.mIndicatorCustomView = view;
        initIndicator();
    }

    public void setContentView(View view, TextView textView) {
        this.mProgressTextView = textView;
        this.mIndicatorType = 4;
        this.mIndicatorCustomView = view;
        initIndicator();
    }

    public View getTopContentView() {
        return this.mTopContentView;
    }

    public void setTopContentView(View view) {
        setTopContentView(view, null);
    }

    public void setTopContentView(View view, TextView textView) {
        this.mProgressTextView = textView;
        this.mTopContentView.removeAllViews();
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(getGradientDrawable());
        } else {
            view.setBackgroundDrawable(getGradientDrawable());
        }
        this.mTopContentView.addView(view);
    }
}
