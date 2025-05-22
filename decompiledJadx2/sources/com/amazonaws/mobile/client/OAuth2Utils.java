package com.amazonaws.mobile.client;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsCallback;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Client;
import com.amazonaws.mobileconnectors.cognitoauth.util.Pkce;
import com.pudutech.robot.module.report.track2.TrackKey;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* compiled from: AWSMobileClient.java */
/* loaded from: classes.dex */
class OAuth2Utils {
    private CustomTabsCallback customTabsCallback = new CustomTabsCallback();
    private final Context mContext;
    private CustomTabsClient mCustomTabsClient;
    private CustomTabsServiceConnection mCustomTabsServiceConnection;
    private CustomTabsSession mCustomTabsSession;
    private String mError;
    private String mErrorDescription;
    private final Uri mSignInRedirectUri;
    private String mState;

    Uri exchangeCode(String str) {
        return null;
    }

    OAuth2Utils(Context context, Uri uri) {
        this.mContext = context;
        this.mSignInRedirectUri = uri;
    }

    void preWarm() {
        this.mCustomTabsServiceConnection = new CustomTabsServiceConnection() { // from class: com.amazonaws.mobile.client.OAuth2Utils.1
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                OAuth2Utils.this.mCustomTabsClient = customTabsClient;
                OAuth2Utils.this.mCustomTabsClient.warmup(0L);
                OAuth2Utils oAuth2Utils = OAuth2Utils.this;
                oAuth2Utils.mCustomTabsSession = oAuth2Utils.mCustomTabsClient.newSession(OAuth2Utils.this.customTabsCallback);
            }

            public void onServiceDisconnected(ComponentName componentName) {
                OAuth2Utils.this.mCustomTabsClient = null;
            }
        };
        CustomTabsClient.bindCustomTabsService(this.mContext, OAuth2Client.CUSTOM_TABS_PACKAGE_NAME, this.mCustomTabsServiceConnection);
    }

    void authorize(String str, String str2, Map<String, String> map) {
        this.mState = Pkce.generateRandom();
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        if (!map.containsKey("code")) {
            buildUpon.appendQueryParameter("response_type", "code");
        }
        if (!map.containsKey("client_id")) {
            if (str2 != null) {
                buildUpon.appendQueryParameter("client_id", str2);
            } else {
                throw new IllegalArgumentException("Client id must be specified for an authorization request.");
            }
        }
        buildUpon.appendQueryParameter("state", this.mState);
        navigate(buildUpon.build());
    }

    void navigate(Uri uri) {
        CustomTabsIntent build = new CustomTabsIntent.Builder(this.mCustomTabsSession).build();
        build.intent.setPackage(OAuth2Client.CUSTOM_TABS_PACKAGE_NAME);
        build.intent.addFlags(1073741824);
        build.intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        build.launchUrl(this.mContext, uri);
    }

    boolean parse(Uri uri) {
        if (uri.getScheme().equals(this.mSignInRedirectUri.getScheme()) && uri.getAuthority().equals(this.mSignInRedirectUri.getAuthority()) && uri.getPath().equals(this.mSignInRedirectUri.getPath()) && uri.getQueryParameterNames().containsAll(this.mSignInRedirectUri.getQueryParameterNames())) {
            uri.getQueryParameter("code");
            if (!this.mState.equals(uri.getQueryParameter("state"))) {
                return false;
            }
            this.mError = uri.getQueryParameter("error");
            this.mErrorDescription = uri.getQueryParameter(TrackKey.ERROR_DESCRIPTION);
            if (this.mError != null) {
                return true;
            }
        }
        return false;
    }
}
