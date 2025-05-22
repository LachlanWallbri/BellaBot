package com.amazonaws.mobile.client.internal.oauth2;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsCallback;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.amazonaws.mobileconnectors.cognitoauth.util.Pkce;
import com.pudutech.robot.module.report.track2.TrackKey;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* loaded from: classes.dex */
public class OAuth2Client {
    public static final String CREATE_DATE = "createDate";
    public static final String CUSTOM_TABS_PACKAGE_NAME = "com.android.chrome";
    private static final long REFRESH_THRESHOLD = 60000;
    public static final String SHARED_PREFERENCES_KEY = "com.amazonaws.mobile.client.oauth2";
    public static final String SIGN_IN_REDIRECT_URI_KEY = "signInRedirectUri";
    public static final String SIGN_OUT_REDIRECT_URI_KEY = "signOutRedirectUri";
    public static final String TAG = OAuth2Client.class.getSimpleName();
    public static final String TOKEN_URI_KEY = "tokenUri";
    Callback<AuthorizeResponse> mAuthorizeCallback;
    private boolean mAuthorizeOrSignOutRedirectReceived;
    private String mClientId;
    final Context mContext;
    CustomTabsClient mCustomTabsClient;
    CustomTabsSession mCustomTabsSession;
    private String mError;
    private String mErrorDescription;
    private String mErrorUriString;
    final AWSMobileClient mMobileClient;
    private Callback<Void> mSignOutCallback;
    String mState;
    private String userAgentOverride;
    boolean mIsPersistenceEnabled = true;
    PKCEMode mPKCEMode = PKCEMode.S256;
    private final OAuth2ClientStore mStore = new OAuth2ClientStore(this);
    CustomTabsCallback mCustomTabsCallback = new CustomTabsCallback() { // from class: com.amazonaws.mobile.client.internal.oauth2.OAuth2Client.1
        public void onNavigationEvent(int i, Bundle bundle) {
            super.onNavigationEvent(i, bundle);
            if (i != 6 || OAuth2Client.this.mAuthorizeOrSignOutRedirectReceived) {
                return;
            }
            if (OAuth2Client.this.mSignOutCallback != null) {
                OAuth2Client.this.mSignOutCallback.onError(new Exception("User cancelled flow or flow interrupted."));
                OAuth2Client.this.mSignOutCallback = null;
            } else if (OAuth2Client.this.mAuthorizeCallback != null) {
                OAuth2Client.this.mAuthorizeCallback.onError(new Exception("User cancelled flow or flow interrupted."));
                OAuth2Client.this.mAuthorizeCallback = null;
            }
        }
    };
    final CustomTabsServiceConnection mCustomTabsServiceConnection = new CustomTabsServiceConnection() { // from class: com.amazonaws.mobile.client.internal.oauth2.OAuth2Client.2
        public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
            OAuth2Client oAuth2Client = OAuth2Client.this;
            oAuth2Client.mCustomTabsClient = customTabsClient;
            oAuth2Client.mCustomTabsClient.warmup(0L);
            OAuth2Client oAuth2Client2 = OAuth2Client.this;
            oAuth2Client2.mCustomTabsSession = oAuth2Client2.mCustomTabsClient.newSession(OAuth2Client.this.mCustomTabsCallback);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            OAuth2Client.this.mCustomTabsClient = null;
        }
    };

    public OAuth2Client(Context context, AWSMobileClient aWSMobileClient) {
        this.mMobileClient = aWSMobileClient;
        this.mContext = context;
        if (CustomTabsClient.bindCustomTabsService(this.mContext, CUSTOM_TABS_PACKAGE_NAME, this.mCustomTabsServiceConnection)) {
            return;
        }
        Log.d(TAG, "OAuth2Client: Failed to pre-warm custom tab, first page load may be slower");
    }

    public void setPersistenceEnabled(boolean z) {
        this.mIsPersistenceEnabled = z;
        this.mStore.setPersistenceEnabled(z);
    }

    public void setUserAgentOverride(String str) {
        this.userAgentOverride = str;
    }

    public void signOut(Uri uri, Callback<Void> callback) {
        this.mSignOutCallback = callback;
        String queryParameter = uri.getQueryParameter("redirect_uri");
        if (queryParameter == null) {
            throw new IllegalArgumentException("The sign-out URI must contain a redirect_uri");
        }
        this.mStore.set(SIGN_OUT_REDIRECT_URI_KEY, queryParameter);
        Uri.parse(queryParameter);
        open(uri);
    }

    public void signOut() {
        this.mStore.clear();
        this.mSignOutCallback = null;
        this.mAuthorizeCallback = null;
        this.mPKCEMode = PKCEMode.S256;
        this.mState = null;
        this.mClientId = null;
        this.mError = null;
        this.mErrorDescription = null;
        this.mErrorUriString = null;
    }

    /* loaded from: classes.dex */
    public enum PKCEMode {
        NONE(""),
        S256("S256");

        private String encode;

        PKCEMode(String str) {
            this.encode = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.encode;
        }

        public boolean equals(PKCEMode pKCEMode) {
            return pKCEMode.encode.equals(this.encode);
        }
    }

    public void setPKCEMode(PKCEMode pKCEMode) {
        this.mPKCEMode = pKCEMode;
    }

    public void authorize(Uri uri, Callback<AuthorizeResponse> callback) {
        this.mAuthorizeCallback = callback;
        try {
            Uri.Builder buildUpon = uri.buildUpon();
            int i = C12313.f1148xd9cabf7[this.mPKCEMode.ordinal()];
            if (i == 1) {
                String generateRandom = Pkce.generateRandom();
                String generateHash = Pkce.generateHash(generateRandom);
                this.mStore.set("proofKey", generateRandom);
                this.mStore.set("proofKeyHash", generateHash);
                buildUpon.appendQueryParameter("code_challenge_method", this.mPKCEMode.toString()).appendQueryParameter("code_challenge", generateHash).build();
            } else if (i != 2) {
                throw new IllegalArgumentException("Unsupported PKCE mode was chosen, please choose another");
            }
            Uri build = buildUpon.build();
            this.mClientId = build.getQueryParameter("client_id");
            if (this.mClientId == null) {
                throw new IllegalArgumentException("The authorize URI must contain a client_id");
            }
            String queryParameter = build.getQueryParameter("redirect_uri");
            if (queryParameter == null) {
                throw new IllegalArgumentException("The authorize URI must contain a redirect_uri");
            }
            this.mStore.set(SIGN_IN_REDIRECT_URI_KEY, queryParameter);
            Uri.parse(queryParameter);
            if (build.getQueryParameter("response_type") == null) {
                buildUpon.appendQueryParameter("response_type", "code").build();
            }
            this.mState = build.getQueryParameter("state");
            if (this.mState == null) {
                this.mState = Pkce.generateRandom();
                buildUpon.appendQueryParameter("state", this.mState).build();
            }
            this.mStore.set("state", this.mState);
            open(buildUpon.build());
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazonaws.mobile.client.internal.oauth2.OAuth2Client$3 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C12313 {

        /* renamed from: $SwitchMap$com$amazonaws$mobile$client$internal$oauth2$OAuth2Client$PKCEMode */
        static final /* synthetic */ int[] f1148xd9cabf7 = new int[PKCEMode.values().length];

        static {
            try {
                f1148xd9cabf7[PKCEMode.S256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1148xd9cabf7[PKCEMode.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public void open(Uri uri) {
        CustomTabsIntent build = new CustomTabsIntent.Builder(this.mCustomTabsSession).build();
        build.intent.setPackage(CUSTOM_TABS_PACKAGE_NAME);
        build.intent.addFlags(1073741824);
        build.intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        this.mAuthorizeOrSignOutRedirectReceived = false;
        build.launchUrl(this.mContext, uri);
    }

    public boolean handleRedirect(Uri uri) {
        if (uri == null) {
            return false;
        }
        String str = this.mStore.get(SIGN_IN_REDIRECT_URI_KEY);
        String str2 = this.mStore.get(SIGN_OUT_REDIRECT_URI_KEY);
        if (str != null) {
            Uri parse = Uri.parse(str);
            if (uri.getScheme().equals(parse.getScheme()) && uri.getAuthority().equals(parse.getAuthority()) && uri.getPath().equals(parse.getPath()) && uri.getQueryParameterNames().containsAll(parse.getQueryParameterNames())) {
                String queryParameter = uri.getQueryParameter("code");
                if (!this.mStore.get("state").equals(uri.getQueryParameter("state"))) {
                    return false;
                }
                this.mError = uri.getQueryParameter("error");
                this.mErrorDescription = uri.getQueryParameter(TrackKey.ERROR_DESCRIPTION);
                this.mErrorUriString = uri.getQueryParameter("error_uri");
                this.mAuthorizeOrSignOutRedirectReceived = true;
                String str3 = this.mError;
                if (str3 != null) {
                    Callback<AuthorizeResponse> callback = this.mAuthorizeCallback;
                    if (callback != null) {
                        callback.onError(new OAuth2Exception("Authorization call failed with response from authorization server", str3, this.mErrorDescription, this.mErrorUriString));
                        this.mAuthorizeCallback = null;
                    }
                    return true;
                }
                if (queryParameter == null) {
                    return false;
                }
                if (this.mAuthorizeCallback != null) {
                    AuthorizeResponse authorizeResponse = new AuthorizeResponse();
                    authorizeResponse.code = queryParameter;
                    authorizeResponse.responseUri = uri;
                    this.mAuthorizeCallback.onResult(authorizeResponse);
                    this.mAuthorizeCallback = null;
                }
                return true;
            }
        }
        if (str2 != null) {
            Uri parse2 = Uri.parse(str2);
            if (uri.getScheme().equals(parse2.getScheme()) && uri.getAuthority().equals(parse2.getAuthority()) && uri.getPath().equals(parse2.getPath()) && uri.getQueryParameterNames().containsAll(parse2.getQueryParameterNames())) {
                this.mAuthorizeOrSignOutRedirectReceived = true;
                Callback<Void> callback2 = this.mSignOutCallback;
                if (callback2 != null) {
                    callback2.onResult(null);
                    this.mSignOutCallback = null;
                }
                return true;
            }
        }
        return false;
    }

    public void requestTokens(Uri uri, Map<String, String> map, Map<String, String> map2, String str, Callback<OAuth2Tokens> callback) {
        String str2 = this.mStore.get("proofKey");
        if (str2 == null && !this.mPKCEMode.equals(PKCEMode.NONE)) {
            callback.onError(new Exception("Proof key could not be found from current session."));
        }
        try {
            if (map2.get("client_id") == null) {
                throw new IllegalArgumentException("The token exchange must contain a client_id");
            }
            if (map2.get("redirect_uri") == null) {
                throw new IllegalArgumentException("The token exchange must contain a redirect_uri");
            }
            if (map2.get("code") == null) {
                if (str == null) {
                    throw new IllegalArgumentException("The token exchange must contain a code");
                }
                map2.put("code", str);
            }
            if (map2.get("code_verifier") == null) {
                if (str2 == null) {
                    throw new IllegalStateException("The token exchange must contain a code verifier");
                }
                map2.put("code_verifier", str2);
            }
            if (map2.get(OAuth2Constants.GRANT_TYPE) == null) {
                map2.put(OAuth2Constants.GRANT_TYPE, OAuth2Constants.GrantTypes.AUTHORIZATION_CODE.toString());
            }
            this.mStore.set(TOKEN_URI_KEY, uri.toString());
            OAuth2Tokens parseHttpResponse = HTTPUtil.parseHttpResponse(HTTPUtil.httpPost(new URL(uri.toString()), map, map2, this.userAgentOverride));
            this.mStore.set(parseHttpResponse);
            callback.onResult(parseHttpResponse);
        } catch (Exception e) {
            callback.onError(new Exception("Failed to exchange code for tokens", e));
        }
    }

    public void refresh(Uri uri, Map<String, String> map, Map<String, String> map2, Callback<OAuth2Tokens> callback) {
        String str = this.mStore.get(OAuth2Constants.TokenResponseFields.REFRESH_TOKEN.toString());
        if (str == null) {
            callback.onError(new IllegalStateException("Refresh called without refresh token available"));
        }
        try {
            if (map2.get(OAuth2Constants.GRANT_TYPE) == null) {
                map2.put(OAuth2Constants.GRANT_TYPE, OAuth2Constants.GrantTypes.REFRESH_TOKEN.toString());
            }
            if (map2.get("refresh_token") == null) {
                if (str == null) {
                    throw new IllegalArgumentException("The refresh flow must contain a refresh_token");
                }
                map2.put("refresh_token", str);
            }
            OAuth2Tokens parseHttpResponse = HTTPUtil.parseHttpResponse(HTTPUtil.httpPost(new URL(uri.toString()), map, map2, this.userAgentOverride));
            this.mStore.set(parseHttpResponse);
            callback.onResult(parseHttpResponse);
        } catch (Exception e) {
            callback.onError(new Exception("Failed to refresh tokens with service", e));
        }
    }

    public void getTokens(Callback<OAuth2Tokens> callback) {
        String str;
        try {
            OAuth2Tokens tokens = this.mStore.getTokens();
            if (tokens.expiresIn != null && (tokens.createDate.longValue() + tokens.expiresIn.longValue()) - System.currentTimeMillis() < 60000) {
                if (tokens.refreshToken != null && (str = this.mStore.get(TOKEN_URI_KEY)) != null) {
                    refresh(Uri.parse(str), new HashMap(), new HashMap(), callback);
                } else {
                    callback.onError(new Exception("No cached tokens available, refresh not available."));
                }
            }
            callback.onResult(tokens);
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
