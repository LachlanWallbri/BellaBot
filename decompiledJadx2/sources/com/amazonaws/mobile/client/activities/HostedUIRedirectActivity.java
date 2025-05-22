package com.amazonaws.mobile.client.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.cognitoauth.activities.CustomTabsManagerActivity;

/* loaded from: classes.dex */
public final class HostedUIRedirectActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        startActivity(CustomTabsManagerActivity.createResponseHandlingIntent(this, getIntent().getData()));
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        Log.d("AuthClient", "Handling auth redirect response");
        AWSMobileClient.getInstance().handleAuthResponse(getIntent());
        finish();
    }
}
