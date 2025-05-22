package com.amazonaws.mobile.auth.userpools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.amazonaws.mobile.auth.core.signin.SignInManager;
import com.amazonaws.mobile.auth.core.signin.p046ui.DisplayUtils;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;

/* loaded from: classes.dex */
public class UserPoolSignInView extends LinearLayout {
    private static final int DEFAULT_BACKGROUND_COLOR = -12303292;
    private static final String LOG_TAG = UserPoolSignInView.class.getSimpleName();
    private int backgroundColor;
    private FormView credentialsFormView;
    private String fontFamily;
    private TextView forgotPasswordTextView;
    private boolean isBackgroundColorFullScreenEnabled;
    private boolean isInitialized;
    private EditText passwordEditText;
    private Button signInButton;
    private TextView signUpTextView;
    private EditText userNameEditText;

    public UserPoolSignInView(Context context) {
        this(context, null);
    }

    public UserPoolSignInView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UserPoolSignInView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(1);
        setGravity(17);
        setId(C1190R.id.user_pool_sign_in_view_id);
        setupCredentialsForm(context);
        setupSignInButton(context);
        setupLayoutForSignUpAndForgotPassword(context);
        Activity activity = (Activity) context;
        setupFontFamily(activity);
        setupBackgroundColor(activity);
        setupBackgroundColorFullScreen(activity);
    }

    private void initializeIfNecessary() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;
        if (isInEditMode()) {
            return;
        }
        try {
            SignInManager.getInstance().initializeSignInButton(CognitoUserPoolsSignInProvider.class, this.signInButton);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Cannot initialize the SignInButton. Please check if IdentityManager : startUpAuth and setUpToAuthenticate are invoked", e);
        }
    }

    private void setupCredentialsForm(Context context) {
        this.credentialsFormView = new FormView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.userNameEditText = this.credentialsFormView.addFormField(context, 33, context.getString(C1190R.string.sign_in_username));
        this.passwordEditText = this.credentialsFormView.addFormField(context, 129, context.getString(C1190R.string.sign_in_password));
        addView(this.credentialsFormView, layoutParams);
    }

    private void setupSignInButton(Context context) {
        this.signInButton = new Button(context);
        this.signInButton.setTextColor(-1);
        this.signInButton.setText(context.getString(C1190R.string.sign_in_button_text));
        this.signInButton.setAllCaps(false);
        this.signInButton.setBackgroundDrawable(DisplayUtils.getRoundedRectangleBackground(UserPoolFormConstants.FORM_BUTTON_CORNER_RADIUS, UserPoolFormConstants.FORM_BUTTON_COLOR));
        Resources resources = getResources();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, resources.getDimensionPixelSize(C1190R.dimen.sign_in_button_height));
        layoutParams.setMargins(this.credentialsFormView.getFormShadowMargin(), resources.getDimensionPixelSize(C1190R.dimen.user_pools_sign_in_button_margin_top_bottom) + this.credentialsFormView.getFormShadowMargin(), this.credentialsFormView.getFormShadowMargin(), 0);
        addView(this.signInButton, layoutParams);
    }

    private void setupLayoutForSignUpAndForgotPassword(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(this.credentialsFormView.getFormShadowMargin(), DisplayUtils.m502dp(10), this.credentialsFormView.getFormShadowMargin(), 0);
        layoutParams.gravity = 1;
        this.signUpTextView = new TextView(context);
        this.signUpTextView.setText(C1190R.string.sign_in_new_account);
        this.signUpTextView.setTextAppearance(context, android.R.style.TextAppearance.Small);
        this.signUpTextView.setGravity(GravityCompat.START);
        this.signUpTextView.setTextColor(UserPoolFormConstants.FORM_BUTTON_COLOR);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.weight = 1.0f;
        linearLayout.addView(this.signUpTextView, layoutParams2);
        this.forgotPasswordTextView = new TextView(context);
        this.forgotPasswordTextView.setText(C1190R.string.sign_in_forgot_password);
        this.forgotPasswordTextView.setTextAppearance(context, android.R.style.TextAppearance.Small);
        this.forgotPasswordTextView.setGravity(GravityCompat.END);
        this.forgotPasswordTextView.setTextColor(UserPoolFormConstants.FORM_BUTTON_COLOR);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.weight = 1.0f;
        linearLayout.addView(this.forgotPasswordTextView, layoutParams3);
        addView(linearLayout, layoutParams);
    }

    private void setupBackgroundColor(Activity activity) {
        this.backgroundColor = activity.getIntent().getIntExtra(CognitoUserPoolsSignInProvider.AttributeKeys.BACKGROUND_COLOR, DEFAULT_BACKGROUND_COLOR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getFontFamily() {
        return this.fontFamily;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBackgroundColorFullScreen() {
        return this.isBackgroundColorFullScreenEnabled;
    }

    private void setupFontFamily(Activity activity) {
        this.fontFamily = activity.getIntent().getStringExtra(CognitoUserPoolsSignInProvider.AttributeKeys.FONT_FAMILY);
        String str = this.fontFamily;
        if (str != null) {
            Typeface create = Typeface.create(str, 0);
            Log.d(LOG_TAG, "Setup font in UserPoolSignInView: " + this.fontFamily);
            this.signUpTextView.setTypeface(create);
            this.forgotPasswordTextView.setTypeface(create);
            this.signInButton.setTypeface(create);
            this.userNameEditText.setTypeface(create);
            this.passwordEditText.setTypeface(create);
        }
    }

    private void setupBackgroundColorFullScreen(Activity activity) {
        this.isBackgroundColorFullScreenEnabled = activity.getIntent().getBooleanExtra(CognitoUserPoolsSignInProvider.AttributeKeys.FULL_SCREEN_BACKGROUND_COLOR, false);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min((int) (View.MeasureSpec.getSize(i) * 0.85d), UserPoolFormConstants.MAX_FORM_WIDTH_IN_PIXELS), Integer.MIN_VALUE), i2);
        initializeIfNecessary();
    }

    public TextView getSignUpTextView() {
        return this.signUpTextView;
    }

    public TextView getForgotPasswordTextView() {
        return this.forgotPasswordTextView;
    }

    public String getEnteredUserName() {
        return this.userNameEditText.getText().toString();
    }

    public String getEnteredPassword() {
        return this.passwordEditText.getText().toString();
    }

    public FormView getCredentialsFormView() {
        return this.credentialsFormView;
    }
}
