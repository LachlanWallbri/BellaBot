package com.amazonaws.mobile.auth.userpools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;

/* loaded from: classes.dex */
public class SignUpConfirmActivity extends Activity {
    private static final String LOG_TAG = SignUpConfirmActivity.class.getSimpleName();
    private SignUpConfirmView signUpConfirmView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1190R.layout.activity_sign_up_confirm);
        String stringExtra = getIntent().getStringExtra(CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME);
        String stringExtra2 = getIntent().getStringExtra("destination");
        this.signUpConfirmView = (SignUpConfirmView) findViewById(C1190R.id.signup_confirm_view);
        this.signUpConfirmView.getUserNameEditText().setText(stringExtra);
        this.signUpConfirmView.getConfirmCodeEditText().requestFocus();
        ((TextView) findViewById(C1190R.id.confirm_account_message1)).setText(getString(C1190R.string.sign_up_confirm_code_sent) + "\n" + stringExtra2);
        ((InputMethodManager) getSystemService("input_method")).toggleSoftInput(2, 1);
    }

    public void confirmAccount(View view) {
        String obj = this.signUpConfirmView.getUserNameEditText().getText().toString();
        String obj2 = this.signUpConfirmView.getConfirmCodeEditText().getText().toString();
        Log.d(LOG_TAG, "username = " + obj);
        Log.d(LOG_TAG, "verificationCode = " + obj2);
        Intent intent = new Intent();
        intent.putExtra(CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, obj);
        intent.putExtra(CognitoUserPoolsSignInProvider.AttributeKeys.VERIFICATION_CODE, obj2);
        setResult(-1, intent);
        finish();
    }
}
