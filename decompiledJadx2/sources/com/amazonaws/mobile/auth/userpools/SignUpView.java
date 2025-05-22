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
import android.widget.TextView;
import com.amazonaws.mobile.auth.core.signin.p046ui.BackgroundDrawable;
import com.amazonaws.mobile.auth.core.signin.p046ui.DisplayUtils;
import com.amazonaws.mobile.auth.core.signin.p046ui.SplitBackgroundDrawable;

/* loaded from: classes.dex */
public class SignUpView extends LinearLayout {
    private static final String LOG_TAG = SignUpView.class.getSimpleName();
    private int backgroundColor;
    private BackgroundDrawable backgroundDrawable;
    private EditText emailEditText;
    private String fontFamily;
    private boolean fullScreenBackgroundColor;
    private EditText givenNameEditText;
    private EditText passwordEditText;
    private EditText phoneEditText;
    private Button signUpButton;
    private FormView signUpForm;
    private TextView signUpMessage;
    private SplitBackgroundDrawable splitBackgroundDrawable;
    private Typeface typeFace;
    private EditText userNameEditText;

    public SignUpView(Context context) {
        this(context, null);
    }

    public SignUpView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignUpView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(1);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1190R.styleable.SignUpView);
            obtainStyledAttributes.getInt(C1190R.styleable.SignUpView_signUpViewBackgroundColor, -12303292);
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
            Log.d(LOG_TAG, "Setup font in SignUpView: " + this.fontFamily);
            this.userNameEditText.setTypeface(this.typeFace);
            this.passwordEditText.setTypeface(this.typeFace);
            this.givenNameEditText.setTypeface(this.typeFace);
            this.emailEditText.setTypeface(this.typeFace);
            this.phoneEditText.setTypeface(this.typeFace);
            this.signUpMessage.setTypeface(this.typeFace);
            this.signUpButton.setTypeface(this.typeFace);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.signUpForm = (FormView) findViewById(C1190R.id.signup_form);
        this.userNameEditText = this.signUpForm.addFormField(getContext(), 97, getContext().getString(C1190R.string.username_text));
        this.passwordEditText = this.signUpForm.addFormField(getContext(), 129, getContext().getString(C1190R.string.sign_in_password));
        this.givenNameEditText = this.signUpForm.addFormField(getContext(), 97, getContext().getString(C1190R.string.given_name_text));
        this.emailEditText = this.signUpForm.addFormField(getContext(), 33, getContext().getString(C1190R.string.email_address_text));
        this.phoneEditText = this.signUpForm.addFormField(getContext(), 3, getContext().getString(C1190R.string.phone_number_text));
        this.signUpMessage = (TextView) findViewById(C1190R.id.signup_message);
        this.signUpButton = (Button) findViewById(C1190R.id.signup_button);
        setupSignUpButtonBackground();
        setupFontFamily();
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

    private void setupSignUpButtonBackground() {
        this.signUpButton.setBackgroundDrawable(DisplayUtils.getRoundedRectangleBackground(UserPoolFormConstants.FORM_BUTTON_CORNER_RADIUS, UserPoolFormConstants.FORM_BUTTON_COLOR));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.signUpButton.getLayoutParams();
        layoutParams.setMargins(this.signUpForm.getFormShadowMargin(), layoutParams.topMargin, this.signUpForm.getFormShadowMargin(), layoutParams.bottomMargin);
    }

    private void setupBackground() {
        if (!this.fullScreenBackgroundColor) {
            this.splitBackgroundDrawable.setSplitPointDistanceFromTop(this.signUpForm.getTop() + (this.signUpForm.getMeasuredHeight() / 2));
            ((ViewGroup) getParent()).setBackgroundDrawable(this.splitBackgroundDrawable);
        } else {
            ((ViewGroup) getParent()).setBackgroundDrawable(this.backgroundDrawable);
        }
    }

    public String getUserName() {
        return this.userNameEditText.getText().toString();
    }

    public String getPassword() {
        return this.passwordEditText.getText().toString();
    }

    public void setPassword(String str) {
        this.passwordEditText.setText(str);
    }

    public String getGivenName() {
        return this.givenNameEditText.getText().toString();
    }

    public String getEmail() {
        return this.emailEditText.getText().toString();
    }

    public String getPhone() {
        return this.phoneEditText.getText().toString();
    }
}
