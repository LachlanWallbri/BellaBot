package com.amazonaws.mobile.auth.userpools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.amazonaws.mobile.auth.core.signin.p046ui.BackgroundDrawable;
import com.amazonaws.mobile.auth.core.signin.p046ui.DisplayUtils;
import com.amazonaws.mobile.auth.core.signin.p046ui.SplitBackgroundDrawable;

/* loaded from: classes.dex */
public class SignUpConfirmView extends LinearLayout {
    private static final String LOG_TAG = SignUpConfirmView.class.getSimpleName();
    private int backgroundColor;
    private BackgroundDrawable backgroundDrawable;
    private Button confirmButton;
    private EditText confirmCodeEditText;
    private FormView confirmForm;
    private String fontFamily;
    private boolean fullScreenBackgroundColor;
    private SplitBackgroundDrawable splitBackgroundDrawable;
    private Typeface typeFace;
    private EditText userNameEditText;

    public SignUpConfirmView(Context context) {
        this(context, null);
    }

    public SignUpConfirmView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignUpConfirmView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(1);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1190R.styleable.SignUpConfirmView);
            obtainStyledAttributes.getInt(C1190R.styleable.SignUpConfirmView_signUpConfirmViewBackgroundColor, -12303292);
            obtainStyledAttributes.recycle();
        }
        this.fontFamily = CognitoUserPoolsSignInProvider.getFontFamily();
        this.typeFace = Typeface.create(this.fontFamily, 0);
        this.fullScreenBackgroundColor = CognitoUserPoolsSignInProvider.isBackgroundColorFullScreen();
        this.backgroundColor = CognitoUserPoolsSignInProvider.getBackgroundColor();
        if (this.fullScreenBackgroundColor) {
            this.backgroundDrawable = new BackgroundDrawable(this.backgroundColor);
        } else {
            this.splitBackgroundDrawable = new SplitBackgroundDrawable(0, this.backgroundColor);
        }
    }

    private void setupFontFamily() {
        if (this.typeFace != null) {
            Log.d(LOG_TAG, "Setup font in SignUpConfirmView: " + this.fontFamily);
            this.userNameEditText.setTypeface(this.typeFace);
            this.confirmCodeEditText.setTypeface(this.typeFace);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.confirmForm = (FormView) findViewById(C1190R.id.signup_confirm_form);
        this.userNameEditText = this.confirmForm.addFormField(getContext(), 97, getContext().getString(C1190R.string.username_text));
        this.confirmCodeEditText = this.confirmForm.addFormField(getContext(), 2, getContext().getString(C1190R.string.sign_up_confirm_code));
        setupConfirmButtonColor();
        setupFontFamily();
    }

    private void setupConfirmButtonColor() {
        this.confirmButton = (Button) findViewById(C1190R.id.confirm_account_button);
        this.confirmButton.setBackgroundDrawable(DisplayUtils.getRoundedRectangleBackground(UserPoolFormConstants.FORM_BUTTON_CORNER_RADIUS, UserPoolFormConstants.FORM_BUTTON_COLOR));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.confirmButton.getLayoutParams();
        layoutParams.setMargins(this.confirmForm.getFormShadowMargin(), layoutParams.topMargin, this.confirmForm.getFormShadowMargin(), layoutParams.bottomMargin);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min((int) (View.MeasureSpec.getSize(i) * 0.85d), UserPoolFormConstants.MAX_FORM_WIDTH_IN_PIXELS), Integer.MIN_VALUE), i2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setupBackground();
    }

    private void setupBackground() {
        if (!this.fullScreenBackgroundColor) {
            this.splitBackgroundDrawable.setSplitPointDistanceFromTop(this.confirmForm.getTop() + (this.confirmForm.getMeasuredHeight() / 2));
            ((ViewGroup) getParent()).setBackgroundDrawable(this.splitBackgroundDrawable);
        } else {
            ((ViewGroup) getParent()).setBackgroundDrawable(this.backgroundDrawable);
        }
    }

    public EditText getUserNameEditText() {
        return this.userNameEditText;
    }

    public EditText getConfirmCodeEditText() {
        return this.confirmCodeEditText;
    }
}
