package com.amazonaws.mobile.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.auth.core.SignInStateChangeListener;
import com.amazonaws.mobile.auth.core.StartupAuthResultHandler;
import com.amazonaws.mobile.auth.core.signin.SignInManager;
import com.amazonaws.mobile.auth.core.signin.SignInProvider;
import com.amazonaws.mobile.auth.facebook.FacebookButton;
import com.amazonaws.mobile.auth.facebook.FacebookSignInProvider;
import com.amazonaws.mobile.auth.google.GoogleButton;
import com.amazonaws.mobile.auth.google.GoogleSignInProvider;
import com.amazonaws.mobile.auth.ui.AuthUIConfiguration;
import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobile.client.internal.InternalCallback;
import com.amazonaws.mobile.client.internal.ReturningRunnable;
import com.amazonaws.mobile.client.internal.oauth2.AuthorizeResponse;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Client;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Tokens;
import com.amazonaws.mobile.client.results.ForgotPasswordResult;
import com.amazonaws.mobile.client.results.ForgotPasswordState;
import com.amazonaws.mobile.client.results.SignInResult;
import com.amazonaws.mobile.client.results.SignInState;
import com.amazonaws.mobile.client.results.SignUpResult;
import com.amazonaws.mobile.client.results.Tokens;
import com.amazonaws.mobile.client.results.UserCodeDeliveryDetails;
import com.amazonaws.mobile.config.AWSConfigurable;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.amazonaws.mobileconnectors.cognitoauth.AuthUserSession;
import com.amazonaws.mobileconnectors.cognitoauth.handlers.AuthHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.CognitoIdentityProviderContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ForgotPasswordContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.NewPasswordContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.UpdateAttributesHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.VerificationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoJWTParser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoPinpointSharedContext;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoServiceConstants;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidentityprovider.AmazonCognitoIdentityProvider;
import com.amazonaws.services.cognitoidentityprovider.AmazonCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidentityprovider.model.GlobalSignOutRequest;
import com.amazonaws.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AWSMobileClient implements AWSCredentialsProvider {
    static final String AUTH_KEY = "Auth";
    public static final String CHALLENGE_RESPONSE_NEW_PASSWORD_KEY = "NEW_PASSWORD";
    public static final String CHALLENGE_RESPONSE_USER_ATTRIBUTES_PREFIX_KEY = "userAttributes.";
    private static final String COGNITO_USERPOOL_CUSTOM_ENDPOINT = "Endpoint";
    private static final String CUSTOM_ROLE_ARN_KEY = "customRoleArn";
    public static final String DEFAULT_USER_AGENT = "AWSMobileClient";
    private static final String FACEBOOK = "FacebookSignIn";
    static final String FEDERATION_ENABLED_KEY = "isFederationEnabled";
    private static final String GOOGLE = "GoogleSignIn";
    private static final String GOOGLE_WEBAPP_CONFIG_KEY = "ClientId-WebApp";
    public static final String HOSTED_UI_KEY = "hostedUI";
    static final String IDENTITY_ID_KEY = "cognitoIdentityId";
    static final String PROVIDER_KEY = "provider";
    static final String SHARED_PREFERENCES_KEY = "com.amazonaws.mobile.client";
    static final String SIGN_IN_MODE = "signInMode";
    static final String TOKEN_KEY = "token";
    private static final String USER_POOLS = "CognitoUserPool";
    AWSConfiguration awsConfiguration;
    private AWSCredentialsProvider awsCredentialsProvider;
    private AWSStartupHandler awsStartupHandler;
    private final LinkedHashMap<Class<? extends AWSConfigurable>, AWSConfigurable> clientMap;
    CognitoCachingCredentialsProvider cognitoIdentity;
    private Object federateWithCognitoIdentityLockObject;
    private Callback<ForgotPasswordResult> forgotPasswordCallback;
    private ForgotPasswordContinuation forgotPasswordContinuation;
    Auth hostedUI;
    private Object initLockObject;
    List<UserStateListener> listeners;
    CognitoUserSession mCognitoUserSession;
    Context mContext;
    DeviceOperations mDeviceOperations;
    Map<String, String> mFederatedLoginsMap;
    private boolean mIsLegacyMode;
    boolean mIsPersistenceEnabled = true;
    OAuth2Client mOAuth2Client;
    private volatile CountDownLatch mSignedOutWaitLatch;
    KeyValueStore mStore;
    String mUserPoolPoolId;
    private Lock mWaitForSignInLock;
    AWSMobileClientCognitoIdentityProvider provider;
    private Object showSignInLockObject;
    private volatile CountDownLatch showSignInWaitLatch;
    private Callback<SignInResult> signInCallback;
    private ChallengeContinuation signInChallengeContinuation;
    private MultiFactorAuthenticationContinuation signInMfaContinuation;
    private SignInProviderConfig[] signInProviderConfig;
    private SignInState signInState;
    private CognitoUser signUpUser;
    private StartupAuthResultHandler startupAuthResultHandler;
    String userAgentOverride;
    private UserStateDetails userStateDetails;
    CognitoUserPool userpool;
    AmazonCognitoIdentityProvider userpoolLL;
    String userpoolsLoginKey;
    private static final String TAG = AWSMobileClient.class.getSimpleName();
    private static volatile AWSMobileClient singleton = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum SignInMode {
        SIGN_IN("0"),
        FEDERATED_SIGN_IN("1"),
        HOSTED_UI("2"),
        OAUTH2("3"),
        UNKNOWN("-1");

        String encode;

        SignInMode(String str) {
            this.encode = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.encode;
        }

        static SignInMode fromString(String str) {
            if ("0".equals(str)) {
                return SIGN_IN;
            }
            if ("1".equals(str)) {
                return FEDERATED_SIGN_IN;
            }
            if ("2".equals(str)) {
                return HOSTED_UI;
            }
            if ("3".equals(str)) {
                return OAUTH2;
            }
            return UNKNOWN;
        }
    }

    private AWSMobileClient() {
        if (singleton != null) {
            throw new AssertionError();
        }
        this.clientMap = new LinkedHashMap<>();
        this.userpoolsLoginKey = "";
        this.mWaitForSignInLock = new ReentrantLock();
        this.mFederatedLoginsMap = new HashMap();
        this.listeners = new ArrayList();
        this.showSignInLockObject = new Object();
        this.federateWithCognitoIdentityLockObject = new Object();
        this.showSignInWaitLatch = new CountDownLatch(1);
        this.initLockObject = new Object();
        this.mStore = new DummyStore();
    }

    public static synchronized AWSMobileClient getInstance() {
        AWSMobileClient aWSMobileClient;
        synchronized (AWSMobileClient.class) {
            if (singleton == null) {
                singleton = new AWSMobileClient();
            }
            aWSMobileClient = singleton;
        }
        return aWSMobileClient;
    }

    static synchronized AWSMobileClient getInstance(boolean z) {
        AWSMobileClient aWSMobileClient;
        synchronized (AWSMobileClient.class) {
            if (z) {
                singleton = null;
            }
            aWSMobileClient = new AWSMobileClient();
        }
        return aWSMobileClient;
    }

    void setUserPool(CognitoUserPool cognitoUserPool) {
        this.userpool = cognitoUserPool;
    }

    public AWSConfiguration getConfiguration() {
        return this.awsConfiguration;
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    public AWSCredentials getCredentials() {
        if (isLegacyMode()) {
            return IdentityManager.getDefaultIdentityManager().getCredentialsProvider().getCredentials();
        }
        if (this.cognitoIdentity == null) {
            throw new AmazonClientException("Cognito Identity not configured");
        }
        try {
            if (waitForSignIn()) {
                Log.d(TAG, "getCredentials: Validated user is signed-in");
            }
            AWSSessionCredentials credentials = this.cognitoIdentity.getCredentials();
            this.mStore.set(IDENTITY_ID_KEY, this.cognitoIdentity.getIdentityId());
            return credentials;
        } catch (NotAuthorizedException e) {
            Log.w(TAG, "getCredentials: Failed to getCredentials from Cognito Identity", e);
            throw new AmazonClientException("Failed to get credentials from Cognito Identity", e);
        } catch (Exception e2) {
            throw new AmazonClientException("Failed to get credentials from Cognito Identity", e2);
        }
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
        if (isLegacyMode()) {
            IdentityManager.getDefaultIdentityManager().getCredentialsProvider().refresh();
            return;
        }
        CognitoCachingCredentialsProvider cognitoCachingCredentialsProvider = this.cognitoIdentity;
        if (cognitoCachingCredentialsProvider == null) {
            throw new AmazonClientException("Cognito Identity not configured");
        }
        cognitoCachingCredentialsProvider.refresh();
        this.mStore.set(IDENTITY_ID_KEY, this.cognitoIdentity.getIdentityId());
    }

    public AWSCredentials getAWSCredentials() throws Exception {
        return _getAWSCredentials().await();
    }

    public void getAWSCredentials(Callback<AWSCredentials> callback) {
        _getAWSCredentials().async(callback);
    }

    private ReturningRunnable<AWSCredentials> _getAWSCredentials() {
        return new ReturningRunnable<AWSCredentials>() { // from class: com.amazonaws.mobile.client.AWSMobileClient.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazonaws.mobile.client.internal.ReturningRunnable
            public AWSCredentials run() {
                return AWSMobileClient.this.getCredentials();
            }
        };
    }

    public String getIdentityId() {
        if (isLegacyMode()) {
            return IdentityManager.getDefaultIdentityManager().getCachedUserID();
        }
        CognitoCachingCredentialsProvider cognitoCachingCredentialsProvider = this.cognitoIdentity;
        if (cognitoCachingCredentialsProvider == null) {
            throw new RuntimeException("Cognito Identity not configured");
        }
        String cachedIdentityId = cognitoCachingCredentialsProvider.getCachedIdentityId();
        return cachedIdentityId == null ? this.mStore.get(IDENTITY_ID_KEY) : cachedIdentityId;
    }

    boolean isLegacyMode() {
        return this.mIsLegacyMode;
    }

    public void initialize(Context context, Callback<UserStateDetails> callback) {
        Context applicationContext = context.getApplicationContext();
        initialize(applicationContext, new AWSConfiguration(applicationContext), callback);
    }

    public void initialize(Context context, AWSConfiguration aWSConfiguration, Callback<UserStateDetails> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_initialize(context, aWSConfiguration, internalCallback));
    }

    CountDownLatch getSignInUILatch() {
        return this.showSignInWaitLatch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazonaws.mobile.client.AWSMobileClient$2 */
    /* loaded from: classes.dex */
    public class RunnableC12032 implements Runnable {
        final /* synthetic */ AWSConfiguration val$awsConfiguration;
        final /* synthetic */ Callback val$callback;
        final /* synthetic */ Context val$context;

        RunnableC12032(Callback callback, AWSConfiguration aWSConfiguration, Context context) {
            this.val$callback = callback;
            this.val$awsConfiguration = aWSConfiguration;
            this.val$context = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (AWSMobileClient.this.initLockObject) {
                if (AWSMobileClient.this.awsConfiguration != null) {
                    this.val$callback.onResult(AWSMobileClient.this.getUserStateDetails(true));
                    return;
                }
                AWSMobileClient.this.mIsPersistenceEnabled = true;
                try {
                    if (this.val$awsConfiguration.optJsonObject(AWSMobileClient.AUTH_KEY) != null && this.val$awsConfiguration.optJsonObject(AWSMobileClient.AUTH_KEY).has("Persistence")) {
                        AWSMobileClient.this.mIsPersistenceEnabled = this.val$awsConfiguration.optJsonObject(AWSMobileClient.AUTH_KEY).getBoolean("Persistence");
                    }
                    AWSMobileClient.this.userAgentOverride = this.val$awsConfiguration.getUserAgentOverride();
                    AWSMobileClient.this.mContext = this.val$context.getApplicationContext();
                    AWSMobileClient.this.mStore = new AWSMobileClientStore(AWSMobileClient.this);
                    final IdentityManager identityManager = new IdentityManager(AWSMobileClient.this.mContext);
                    identityManager.enableFederation(false);
                    identityManager.setConfiguration(this.val$awsConfiguration);
                    identityManager.setPersistenceEnabled(AWSMobileClient.this.mIsPersistenceEnabled);
                    IdentityManager.setDefaultIdentityManager(identityManager);
                    AWSMobileClient.this.registerConfigSignInProviders(this.val$awsConfiguration);
                    identityManager.addSignInStateChangeListener(new SignInStateChangeListener() { // from class: com.amazonaws.mobile.client.AWSMobileClient.2.1
                        @Override // com.amazonaws.mobile.auth.core.SignInStateChangeListener
                        public void onUserSignedIn() {
                            Log.d(AWSMobileClient.TAG, "onUserSignedIn: Updating user state from drop-in UI");
                            AWSMobileClient.this.signInState = SignInState.DONE;
                            com.amazonaws.mobile.auth.core.IdentityProvider currentIdentityProvider = identityManager.getCurrentIdentityProvider();
                            String token = currentIdentityProvider.getToken();
                            AWSMobileClient.this.federatedSignInWithoutAssigningState(currentIdentityProvider.getCognitoLoginKey(), token, new Callback<UserStateDetails>() { // from class: com.amazonaws.mobile.client.AWSMobileClient.2.1.1
                                @Override // com.amazonaws.mobile.client.Callback
                                public void onResult(UserStateDetails userStateDetails) {
                                    Log.d(AWSMobileClient.TAG, "onResult: showSignIn federated");
                                    AWSMobileClient.this.setUserState(AWSMobileClient.this.getUserStateDetails(false));
                                    AWSMobileClient.this.getSignInUILatch().countDown();
                                }

                                @Override // com.amazonaws.mobile.client.Callback
                                public void onError(Exception exc) {
                                    Log.w(AWSMobileClient.TAG, "onError: User sign-in had errors from drop-in UI", exc);
                                    AWSMobileClient.this.setUserState(AWSMobileClient.this.getUserStateDetails(false));
                                    AWSMobileClient.this.getSignInUILatch().countDown();
                                }
                            });
                        }

                        @Override // com.amazonaws.mobile.auth.core.SignInStateChangeListener
                        public void onUserSignedOut() {
                            Log.d(AWSMobileClient.TAG, "onUserSignedOut: Updating user state from drop-in UI");
                            AWSMobileClient.this.setUserState(AWSMobileClient.this.getUserStateDetails(false));
                            AWSMobileClient.this.showSignInWaitLatch.countDown();
                        }
                    });
                    if (this.val$awsConfiguration.optJsonObject("CredentialsProvider") != null && this.val$awsConfiguration.optJsonObject("CredentialsProvider").optJSONObject("CognitoIdentity") != null) {
                        try {
                            JSONObject jSONObject = this.val$awsConfiguration.optJsonObject("CredentialsProvider").getJSONObject("CognitoIdentity").getJSONObject(this.val$awsConfiguration.getConfiguration());
                            String string = jSONObject.getString("PoolId");
                            String string2 = jSONObject.getString("Region");
                            ClientConfiguration clientConfiguration = new ClientConfiguration();
                            clientConfiguration.setUserAgent("AWSMobileClient " + this.val$awsConfiguration.getUserAgent());
                            if (AWSMobileClient.this.userAgentOverride != null) {
                                clientConfiguration.setUserAgentOverride(AWSMobileClient.this.userAgentOverride);
                            }
                            AmazonCognitoIdentityClient amazonCognitoIdentityClient = new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration);
                            amazonCognitoIdentityClient.setRegion(Region.getRegion(string2));
                            AWSMobileClient.this.provider = new AWSMobileClientCognitoIdentityProvider(null, string, amazonCognitoIdentityClient);
                            AWSMobileClient.this.cognitoIdentity = new CognitoCachingCredentialsProvider(AWSMobileClient.this.mContext, AWSMobileClient.this.provider, Regions.fromName(string2));
                            AWSMobileClient.this.cognitoIdentity.setPersistenceEnabled(AWSMobileClient.this.mIsPersistenceEnabled);
                            if (AWSMobileClient.this.userAgentOverride != null) {
                                AWSMobileClient.this.cognitoIdentity.setUserAgentOverride(AWSMobileClient.this.userAgentOverride);
                            }
                        } catch (Exception e) {
                            this.val$callback.onError(new RuntimeException("Failed to initialize Cognito Identity; please check your awsconfiguration.json", e));
                            return;
                        }
                    }
                    JSONObject optJsonObject = this.val$awsConfiguration.optJsonObject(AWSMobileClient.USER_POOLS);
                    if (optJsonObject != null) {
                        try {
                            AWSMobileClient.this.mUserPoolPoolId = optJsonObject.getString("PoolId");
                            String string3 = optJsonObject.getString("AppClientId");
                            String optString = optJsonObject.optString("AppClientSecret");
                            String pinpointEndpoint = CognitoPinpointSharedContext.getPinpointEndpoint(this.val$context, optJsonObject.optString("PinpointAppId"));
                            String optString2 = optJsonObject.optString(AWSMobileClient.COGNITO_USERPOOL_CUSTOM_ENDPOINT);
                            ClientConfiguration clientConfiguration2 = new ClientConfiguration();
                            clientConfiguration2.setUserAgent("AWSMobileClient " + this.val$awsConfiguration.getUserAgent());
                            if (AWSMobileClient.this.userAgentOverride != null) {
                                clientConfiguration2.setUserAgentOverride(AWSMobileClient.this.userAgentOverride);
                            }
                            AWSMobileClient.this.userpoolLL = new AmazonCognitoIdentityProviderClient(new AnonymousAWSCredentials(), clientConfiguration2);
                            AWSMobileClient.this.userpoolLL.setRegion(Region.getRegion(Regions.fromName(optJsonObject.getString("Region"))));
                            AWSMobileClient.this.userpoolsLoginKey = String.format("cognito-idp.%s.amazonaws.com/%s", optJsonObject.getString("Region"), optJsonObject.getString("PoolId"));
                            AWSMobileClient.this.userpool = new CognitoUserPool(AWSMobileClient.this.mContext, AWSMobileClient.this.mUserPoolPoolId, string3, optString, AWSMobileClient.this.userpoolLL, pinpointEndpoint, optString2);
                            AWSMobileClient.this.userpool.setPersistenceEnabled(AWSMobileClient.this.mIsPersistenceEnabled);
                            AWSMobileClient.this.mDeviceOperations = new DeviceOperations(AWSMobileClient.this, AWSMobileClient.this.userpoolLL);
                        } catch (Exception e2) {
                            this.val$callback.onError(new RuntimeException("Failed to initialize Cognito Userpool; please check your awsconfiguration.json", e2));
                            return;
                        }
                    }
                    JSONObject hostedUIJSON = AWSMobileClient.this.getHostedUIJSON(this.val$awsConfiguration);
                    if (hostedUIJSON != null) {
                        try {
                            if (hostedUIJSON.has("TokenURI")) {
                                Log.d(AWSMobileClient.TAG, "initialize: OAuth2 client detected");
                                AWSMobileClient.this.mOAuth2Client = new OAuth2Client(AWSMobileClient.this.mContext, AWSMobileClient.this);
                                AWSMobileClient.this.mOAuth2Client.setPersistenceEnabled(AWSMobileClient.this.mIsPersistenceEnabled);
                                AWSMobileClient.this.mOAuth2Client.setUserAgentOverride(AWSMobileClient.this.userAgentOverride);
                            } else {
                                AWSMobileClient.this._initializeHostedUI(hostedUIJSON);
                            }
                        } catch (Exception e3) {
                            this.val$callback.onError(new RuntimeException("Failed to initialize OAuth, please check your awsconfiguration.json", e3));
                        }
                    }
                    if (AWSMobileClient.this.cognitoIdentity == null && AWSMobileClient.this.userpool == null) {
                        this.val$callback.onError(new RuntimeException("Neither Cognito Identity or Cognito UserPool was used. At least one must be present to use AWSMobileClient."));
                        return;
                    }
                    AWSMobileClient.this.awsConfiguration = this.val$awsConfiguration;
                    UserStateDetails userStateDetails = AWSMobileClient.this.getUserStateDetails(true);
                    this.val$callback.onResult(userStateDetails);
                    AWSMobileClient.this.setUserState(userStateDetails);
                } catch (Exception e4) {
                    this.val$callback.onError(new RuntimeException("Failed to initialize AWSMobileClient; please check your awsconfiguration.json", e4));
                }
            }
        }
    }

    protected Runnable _initialize(Context context, AWSConfiguration aWSConfiguration, Callback<UserStateDetails> callback) {
        return new RunnableC12032(callback, aWSConfiguration, context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _initializeHostedUI(JSONObject jSONObject) throws JSONException {
        Log.d(TAG, "initialize: Cognito HostedUI client detected");
        JSONArray jSONArray = jSONObject.getJSONArray("Scopes");
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.getString(i));
        }
        if (this.mUserPoolPoolId == null) {
            throw new IllegalStateException("User pool Id must be available through user pool setting");
        }
        this.hostedUI = getHostedUI(jSONObject).setPersistenceEnabled(this.mIsPersistenceEnabled).setAuthHandler(new AuthHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.3
            public void onFailure(Exception exc) {
            }

            public void onSignout() {
            }

            public void onSuccess(AuthUserSession authUserSession) {
            }
        }).build();
    }

    JSONObject getHostedUIJSONFromJSON() {
        return getHostedUIJSONFromJSON(this.awsConfiguration);
    }

    JSONObject getHostedUIJSONFromJSON(AWSConfiguration aWSConfiguration) {
        JSONObject optJsonObject = aWSConfiguration.optJsonObject(AUTH_KEY);
        if (optJsonObject == null || !optJsonObject.has("OAuth")) {
            return null;
        }
        try {
            return optJsonObject.getJSONObject("OAuth");
        } catch (Exception e) {
            Log.w(TAG, "getHostedUIJSONFromJSON: Failed to read config", e);
            return null;
        }
    }

    JSONObject getHostedUIJSON() {
        return getHostedUIJSON(this.awsConfiguration);
    }

    JSONObject getHostedUIJSON(AWSConfiguration aWSConfiguration) {
        JSONObject jSONObject;
        try {
            JSONObject hostedUIJSONFromJSON = getHostedUIJSONFromJSON(aWSConfiguration);
            if (hostedUIJSONFromJSON == null) {
                return null;
            }
            try {
                jSONObject = new JSONObject(this.mStore.get(HOSTED_UI_KEY));
            } catch (Exception e) {
                Log.w(TAG, "Failed to parse HostedUI settings from store. Defaulting to awsconfiguration.json", e);
                jSONObject = null;
            }
            if (jSONObject != null || hostedUIJSONFromJSON == null) {
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject(hostedUIJSONFromJSON.toString());
            this.mStore.set(HOSTED_UI_KEY, jSONObject2.toString());
            return jSONObject2;
        } catch (Exception e2) {
            Log.d(TAG, "getHostedUIJSON: Failed to read config", e2);
            return null;
        }
    }

    Auth.Builder getHostedUI(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("Scopes");
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.getString(i));
        }
        return new Auth.Builder().setApplicationContext(this.mContext).setUserPoolId(this.mUserPoolPoolId).setAppClientId(jSONObject.getString("AppClientId")).setAppClientSecret(jSONObject.optString("AppClientSecret", null)).setAppCognitoWebDomain(jSONObject.getString("WebDomain")).setSignInRedirect(jSONObject.getString("SignInRedirectURI")).setSignOutRedirect(jSONObject.getString("SignOutRedirectURI")).setScopes(hashSet).setAdvancedSecurityDataCollection(false).setIdentityProvider(jSONObject.optString("IdentityProvider")).setIdpIdentifier(jSONObject.optString("IdpIdentifier"));
    }

    public DeviceOperations getDeviceOperations() {
        DeviceOperations deviceOperations = this.mDeviceOperations;
        if (deviceOperations != null) {
            return deviceOperations;
        }
        throw new AmazonClientException("Please check if userpools is configured.");
    }

    public void releaseSignInWait() {
        if (this.mSignedOutWaitLatch != null) {
            this.mSignedOutWaitLatch.countDown();
        }
    }

    protected void setUserState(final UserStateDetails userStateDetails) {
        boolean z = !userStateDetails.equals(this.userStateDetails);
        this.userStateDetails = userStateDetails;
        if (z) {
            synchronized (this.listeners) {
                for (final UserStateListener userStateListener : this.listeners) {
                    new Thread(new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.4
                        @Override // java.lang.Runnable
                        public void run() {
                            userStateListener.onUserStateChanged(userStateDetails);
                        }
                    }).start();
                }
            }
        }
    }

    protected boolean isNetworkAvailable(Context context) {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") != 0) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    return true;
                }
            }
        } catch (Exception e) {
            Log.w(TAG, "Could not access network state", e);
        }
        return false;
    }

    boolean isUserpoolsSignedIn() {
        return this.userpoolsLoginKey.equals(this.mStore.get(PROVIDER_KEY));
    }

    public String getUsername() {
        try {
            if (this.userpoolsLoginKey.equals(this.mStore.get(PROVIDER_KEY))) {
                return this.userpool.getCurrentUser().getUserId();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public String getUserSub() {
        try {
            if (this.userpoolsLoginKey.equals(this.mStore.get(PROVIDER_KEY))) {
                return CognitoJWTParser.getClaim(this.mStore.get(TOKEN_KEY), "sub");
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public UserStateDetails currentUserState() {
        try {
            return _currentUserState().await();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve user state.", e);
        }
    }

    public void currentUserState(Callback<UserStateDetails> callback) {
        _currentUserState().async(callback);
    }

    ReturningRunnable<UserStateDetails> _currentUserState() {
        return new ReturningRunnable<UserStateDetails>() { // from class: com.amazonaws.mobile.client.AWSMobileClient.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazonaws.mobile.client.internal.ReturningRunnable
            public UserStateDetails run() throws Exception {
                return AWSMobileClient.this.getUserStateDetails(false);
            }
        };
    }

    public void addUserStateListener(UserStateListener userStateListener) {
        synchronized (this.listeners) {
            this.listeners.add(userStateListener);
        }
    }

    public boolean removeUserStateListener(UserStateListener userStateListener) {
        synchronized (this.listeners) {
            int indexOf = this.listeners.indexOf(userStateListener);
            if (indexOf == -1) {
                return false;
            }
            this.listeners.remove(indexOf);
            return true;
        }
    }

    String getLoginKey() {
        return this.userpoolsLoginKey;
    }

    public boolean isSignedIn() {
        int i = C121228.$SwitchMap$com$amazonaws$mobile$client$UserState[getUserStateDetails(true).getUserState().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return true;
        }
        if (i == 4 || i == 5) {
            return false;
        }
        throw new IllegalStateException("Unknown user state, please report this exception");
    }

    protected boolean waitForSignIn() {
        try {
            try {
                this.mWaitForSignInLock.lock();
                this.mSignedOutWaitLatch = new CountDownLatch(1);
                boolean z = false;
                UserStateDetails userStateDetails = getUserStateDetails(false);
                Log.d(TAG, "waitForSignIn: userState:" + userStateDetails.getUserState());
                int i = C121228.$SwitchMap$com$amazonaws$mobile$client$UserState[userStateDetails.getUserState().ordinal()];
                if (i == 1) {
                    setUserState(userStateDetails);
                    return true;
                }
                if (i == 2 || i == 3) {
                    if (userStateDetails.getException() != null && !isSignedOutRelatedException(userStateDetails.getException())) {
                        throw userStateDetails.getException();
                    }
                    setUserState(userStateDetails);
                    this.mSignedOutWaitLatch.await();
                    z = getUserStateDetails(false).getUserState().equals(UserState.SIGNED_IN);
                } else {
                    if (i != 4 && i != 5) {
                        return false;
                    }
                    setUserState(userStateDetails);
                }
                return z;
            } catch (Exception e) {
                throw new AmazonClientException("Operation requires a signed-in state", e);
            }
        } finally {
            this.mWaitForSignInLock.unlock();
        }
    }

    Map<String, String> getSignInDetailsMap() {
        return this.mStore.get(PROVIDER_KEY, TOKEN_KEY);
    }

    String _getCachedIdentityId() {
        return this.mStore.get(IDENTITY_ID_KEY);
    }

    protected UserStateDetails getUserStateDetails(boolean z) {
        Tokens tokens;
        UserStateDetails userStateDetails;
        Map<String, String> signInDetailsMap = getSignInDetailsMap();
        String str = signInDetailsMap.get(PROVIDER_KEY);
        String str2 = signInDetailsMap.get(TOKEN_KEY);
        String _getCachedIdentityId = _getCachedIdentityId();
        boolean isFederationEnabled = isFederationEnabled();
        Log.d(TAG, "Inspecting user state details");
        boolean z2 = (str == null || str2 == null) ? false : true;
        if (z || !isNetworkAvailable(this.mContext)) {
            if (z2) {
                return new UserStateDetails(UserState.SIGNED_IN, signInDetailsMap);
            }
            if (_getCachedIdentityId != null) {
                return new UserStateDetails(UserState.GUEST, signInDetailsMap);
            }
            return new UserStateDetails(UserState.SIGNED_OUT, null);
        }
        if (z2 && !this.userpoolsLoginKey.equals(str)) {
            if (isFederationEnabled) {
                try {
                    SignInProvider previouslySignedInProvider = SignInManager.getInstance(this.mContext).getPreviouslySignedInProvider();
                    if (previouslySignedInProvider != null && str.equals(previouslySignedInProvider.getCognitoLoginKey())) {
                        str2 = previouslySignedInProvider.getToken();
                        Log.i(TAG, "Token was refreshed using drop-in UI internal mechanism");
                    }
                    if (str2 == null) {
                        Log.i(TAG, "Token used for federation has become null");
                        return new UserStateDetails(UserState.SIGNED_OUT_FEDERATED_TOKENS_INVALID, signInDetailsMap);
                    }
                    if (hasFederatedToken(str, str2)) {
                        Log.d(TAG, "getUserStateDetails: token already federated just fetch credentials");
                        if (this.cognitoIdentity != null) {
                            this.cognitoIdentity.getCredentials();
                        }
                    } else {
                        federateWithCognitoIdentity(str, str2);
                    }
                } catch (Exception e) {
                    Log.w(TAG, "Failed to federate the tokens.", e);
                    UserState userState = UserState.SIGNED_IN;
                    if (isSignedOutRelatedException(e)) {
                        userState = UserState.SIGNED_OUT_FEDERATED_TOKENS_INVALID;
                    }
                    UserStateDetails userStateDetails2 = new UserStateDetails(userState, signInDetailsMap);
                    userStateDetails2.setException(e);
                    return userStateDetails2;
                }
            }
            return new UserStateDetails(UserState.SIGNED_IN, signInDetailsMap);
        }
        if (z2 && this.userpool != null) {
            try {
                try {
                    tokens = getTokens(false);
                } catch (Exception e2) {
                    e = e2;
                    tokens = null;
                }
            } catch (Throwable unused) {
                UserState userState2 = UserState.SIGNED_IN;
                if (isSignedOutRelatedException(null)) {
                    userState2 = UserState.SIGNED_OUT_USER_POOLS_TOKENS_INVALID;
                }
                userStateDetails = new UserStateDetails(userState2, signInDetailsMap);
            }
            try {
                String tokenString = tokens.getIdToken().getTokenString();
                signInDetailsMap.put(TOKEN_KEY, tokenString);
                if (isFederationEnabled) {
                    if (hasFederatedToken(str, tokenString)) {
                        try {
                            if (this.cognitoIdentity != null) {
                                this.cognitoIdentity.getCredentials();
                            }
                        } catch (Exception e3) {
                            Log.w(TAG, "Failed to get or refresh credentials from Cognito Identity", e3);
                        }
                    } else if (this.cognitoIdentity != null) {
                        federateWithCognitoIdentity(str, tokenString);
                    }
                }
                UserState userState3 = UserState.SIGNED_IN;
                if (isSignedOutRelatedException(null)) {
                    userState3 = UserState.SIGNED_OUT_USER_POOLS_TOKENS_INVALID;
                }
                userStateDetails = new UserStateDetails(userState3, signInDetailsMap);
                userStateDetails.setException(null);
                return userStateDetails;
            } catch (Exception e4) {
                e = e4;
                Log.w(TAG, tokens == null ? "Tokens are invalid, please sign-in again." : "Failed to federate the tokens", e);
                UserState userState4 = UserState.SIGNED_IN;
                if (isSignedOutRelatedException(e)) {
                    userState4 = UserState.SIGNED_OUT_USER_POOLS_TOKENS_INVALID;
                }
                UserStateDetails userStateDetails3 = new UserStateDetails(userState4, signInDetailsMap);
                userStateDetails3.setException(e);
                return userStateDetails3;
            }
        }
        if (this.cognitoIdentity == null) {
            return new UserStateDetails(UserState.SIGNED_OUT, signInDetailsMap);
        }
        if (_getCachedIdentityId != null) {
            return new UserStateDetails(UserState.GUEST, signInDetailsMap);
        }
        return new UserStateDetails(UserState.SIGNED_OUT, null);
    }

    boolean isSignedOutRelatedException(Exception exc) {
        if (exc == null) {
            return false;
        }
        if (exc instanceof NotAuthorizedException) {
            return true;
        }
        return "No cached session.".equals(exc.getMessage()) && exc.getCause() == null;
    }

    boolean isFederationEnabled() {
        String str = this.mStore.get(FEDERATION_ENABLED_KEY);
        if (str != null) {
            return str.equals("true");
        }
        return true;
    }

    SignInMode getSignInMode() {
        return SignInMode.fromString(this.mStore.get(SIGN_IN_MODE));
    }

    private boolean hasFederatedToken(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            return false;
        }
        boolean equals = str2.equals(this.mFederatedLoginsMap.get(str));
        Log.d(TAG, "hasFederatedToken: " + equals + " provider: " + str);
        return equals;
    }

    public void signIn(String str, String str2, Map<String, String> map, Callback<SignInResult> callback) {
        signIn(str, str2, map, Collections.emptyMap(), callback);
    }

    public void signIn(String str, String str2, Map<String, String> map, Map<String, String> map2, Callback<SignInResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_signIn(str, str2, map, map2, internalCallback));
    }

    public SignInResult signIn(String str, String str2, Map<String, String> map) throws Exception {
        return signIn(str, str2, map, Collections.emptyMap());
    }

    public SignInResult signIn(String str, String str2, Map<String, String> map, Map<String, String> map2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (SignInResult) internalCallback.await(_signIn(str, str2, map, map2, internalCallback));
    }

    private Runnable _signIn(final String str, final String str2, final Map<String, String> map, final Map<String, String> map2, final Callback<SignInResult> callback) {
        this.signInCallback = callback;
        this.signInState = null;
        this.mStore.set(SIGN_IN_MODE, SignInMode.SIGN_IN.toString());
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AWSMobileClient.this.userpool.getUser(str).getSession(map2, new AuthenticationHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.6.1
                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
                        public void onSuccess(CognitoUserSession cognitoUserSession, CognitoDevice cognitoDevice) {
                            AWSMobileClient aWSMobileClient;
                            UserStateDetails userStateDetails;
                            try {
                                AWSMobileClient.this.mCognitoUserSession = cognitoUserSession;
                                AWSMobileClient.this.signInState = SignInState.DONE;
                            } catch (Exception e) {
                                AWSMobileClient.this.signInCallback.onError(e);
                                AWSMobileClient.this.signInCallback = null;
                            }
                            try {
                                try {
                                    if (AWSMobileClient.this.isFederationEnabled()) {
                                        AWSMobileClient.this.federatedSignInWithoutAssigningState(AWSMobileClient.this.userpoolsLoginKey, AWSMobileClient.this.mCognitoUserSession.getIdToken().getJWTToken());
                                    }
                                    AWSMobileClient.this.releaseSignInWait();
                                    aWSMobileClient = AWSMobileClient.this;
                                    userStateDetails = new UserStateDetails(UserState.SIGNED_IN, AWSMobileClient.this.getSignInDetailsMap());
                                } catch (Exception e2) {
                                    Log.w(AWSMobileClient.TAG, "Failed to federate tokens during sign-in", e2);
                                    aWSMobileClient = AWSMobileClient.this;
                                    userStateDetails = new UserStateDetails(UserState.SIGNED_IN, AWSMobileClient.this.getSignInDetailsMap());
                                }
                                aWSMobileClient.setUserState(userStateDetails);
                                AWSMobileClient.this.signInCallback.onResult(SignInResult.DONE);
                            } catch (Throwable th) {
                                AWSMobileClient.this.setUserState(new UserStateDetails(UserState.SIGNED_IN, AWSMobileClient.this.getSignInDetailsMap()));
                                throw th;
                            }
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
                        public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String str3) {
                            String string;
                            Log.d(AWSMobileClient.TAG, "Sending password.");
                            HashMap hashMap = new HashMap();
                            boolean z = AWSMobileClient.this.awsConfiguration.optJsonObject(AWSMobileClient.AUTH_KEY) != null && AWSMobileClient.this.awsConfiguration.optJsonObject(AWSMobileClient.AUTH_KEY).has("authenticationFlowType");
                            if (z) {
                                try {
                                    string = AWSMobileClient.this.awsConfiguration.optJsonObject(AWSMobileClient.AUTH_KEY).getString("authenticationFlowType");
                                } catch (JSONException e) {
                                    Log.w(AWSMobileClient.TAG, "Exception while attempting to read authenticationFlowType from config.", e);
                                }
                            } else {
                                string = null;
                            }
                            if (z && CognitoServiceConstants.AUTH_TYPE_INIT_CUSTOM_AUTH.equals(string)) {
                                if (str2 != null) {
                                    authenticationContinuation.setAuthenticationDetails(new AuthenticationDetails(str, str2, hashMap, map));
                                } else {
                                    authenticationContinuation.setAuthenticationDetails(new AuthenticationDetails(str, hashMap, (Map<String, String>) map));
                                }
                            } else if (!z || !CognitoServiceConstants.AUTH_TYPE_INIT_USER_PASSWORD.equals(string)) {
                                Log.d(AWSMobileClient.TAG, "Using USER_SRP_AUTH for flow type.");
                                authenticationContinuation.setAuthenticationDetails(new AuthenticationDetails(str, str2, (Map<String, String>) map));
                            } else {
                                AuthenticationDetails authenticationDetails = new AuthenticationDetails(str, str2, (Map<String, String>) map);
                                authenticationDetails.setAuthenticationType(CognitoServiceConstants.CHLG_TYPE_USER_PASSWORD);
                                authenticationContinuation.setAuthenticationDetails(authenticationDetails);
                            }
                            authenticationContinuation.continueTask();
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
                        public void getMFACode(MultiFactorAuthenticationContinuation multiFactorAuthenticationContinuation) {
                            AWSMobileClient.this.signInMfaContinuation = multiFactorAuthenticationContinuation;
                            CognitoUserCodeDeliveryDetails parameters = multiFactorAuthenticationContinuation.getParameters();
                            AWSMobileClient.this.signInState = SignInState.SMS_MFA;
                            AWSMobileClient.this.signInCallback.onResult(new SignInResult(SignInState.SMS_MFA, new UserCodeDeliveryDetails(parameters.getDestination(), parameters.getDeliveryMedium(), parameters.getAttributeName())));
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
                        public void authenticationChallenge(ChallengeContinuation challengeContinuation) {
                            try {
                                AWSMobileClient.this.signInState = SignInState.valueOf(challengeContinuation.getChallengeName());
                                AWSMobileClient.this.signInChallengeContinuation = challengeContinuation;
                                AWSMobileClient.this.signInCallback.onResult(new SignInResult(AWSMobileClient.this.signInState, challengeContinuation.getParameters()));
                            } catch (IllegalArgumentException e) {
                                AWSMobileClient.this.signInCallback.onError(e);
                            }
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
                        public void onFailure(Exception exc) {
                            AWSMobileClient.this.signInCallback.onError(exc);
                        }
                    });
                } catch (Exception e) {
                    callback.onError(e);
                }
            }
        };
    }

    public void confirmSignIn(String str, Callback<SignInResult> callback) {
        confirmSignIn(str, Collections.emptyMap(), callback);
    }

    public void confirmSignIn(String str, Map<String, String> map, Callback<SignInResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_confirmSignIn(str, map, Collections.emptyMap(), internalCallback));
    }

    public void confirmSignIn(String str, Map<String, String> map, Map<String, String> map2, Callback<SignInResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_confirmSignIn(str, map, map2, internalCallback));
    }

    public SignInResult confirmSignIn(String str) throws Exception {
        return confirmSignIn(str, Collections.emptyMap());
    }

    public SignInResult confirmSignIn(String str, Map<String, String> map) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (SignInResult) internalCallback.await(_confirmSignIn(str, map, Collections.emptyMap(), internalCallback));
    }

    public SignInResult confirmSignIn(String str, Map<String, String> map, Map<String, String> map2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (SignInResult) internalCallback.await(_confirmSignIn(str, map, map2, internalCallback));
    }

    private Runnable _confirmSignIn(final String str, final Map<String, String> map, final Map<String, String> map2, final Callback<SignInResult> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.7
            @Override // java.lang.Runnable
            public void run() {
                CognitoIdentityProviderContinuation cognitoIdentityProviderContinuation;
                if (AWSMobileClient.this.signInState != null) {
                    int i = C121228.$SwitchMap$com$amazonaws$mobile$client$results$SignInState[AWSMobileClient.this.signInState.ordinal()];
                    if (i == 1) {
                        AWSMobileClient.this.signInMfaContinuation.setMfaCode(str);
                        AWSMobileClient.this.signInMfaContinuation.setClientMetaData(map);
                        cognitoIdentityProviderContinuation = AWSMobileClient.this.signInMfaContinuation;
                        AWSMobileClient.this.signInCallback = new InternalCallback(callback);
                    } else if (i != 2) {
                        if (i != 3) {
                            if (i == 4) {
                                callback.onError(new IllegalStateException("confirmSignIn called after signIn has succeeded"));
                                return;
                            } else {
                                callback.onError(new IllegalStateException("confirmSignIn called on unsupported operation, please file a feature request"));
                                return;
                            }
                        }
                        AWSMobileClient.this.signInChallengeContinuation.setChallengeResponse(CognitoServiceConstants.CHLG_RESP_ANSWER, str);
                        cognitoIdentityProviderContinuation = AWSMobileClient.this.signInChallengeContinuation;
                        AWSMobileClient.this.signInCallback = new InternalCallback(callback);
                        if (map != null) {
                            AWSMobileClient.this.signInChallengeContinuation.setClientMetaData(map);
                        }
                    } else {
                        ((NewPasswordContinuation) AWSMobileClient.this.signInChallengeContinuation).setPassword(str);
                        AWSMobileClient.this.signInChallengeContinuation.setClientMetaData(map);
                        for (String str2 : map2.keySet()) {
                            AWSMobileClient.this.signInChallengeContinuation.setChallengeResponse("userAttributes." + str2, (String) map2.get(str2));
                        }
                        cognitoIdentityProviderContinuation = AWSMobileClient.this.signInChallengeContinuation;
                        AWSMobileClient.this.signInCallback = new InternalCallback(callback);
                    }
                    if (cognitoIdentityProviderContinuation != null) {
                        cognitoIdentityProviderContinuation.continueTask();
                        return;
                    }
                    return;
                }
                callback.onError(new IllegalStateException("Cannot call confirmSignIn(String, Callback) without initiating sign-in. This call is used for SMS_MFA and NEW_PASSWORD_REQUIRED sign-in state."));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazonaws.mobile.client.AWSMobileClient$28 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C121228 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$mobile$client$UserState;
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$mobile$client$results$SignInState = new int[SignInState.values().length];

        static {
            try {
                $SwitchMap$com$amazonaws$mobile$client$results$SignInState[SignInState.SMS_MFA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazonaws$mobile$client$results$SignInState[SignInState.NEW_PASSWORD_REQUIRED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazonaws$mobile$client$results$SignInState[SignInState.CUSTOM_CHALLENGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazonaws$mobile$client$results$SignInState[SignInState.DONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$amazonaws$mobile$client$UserState = new int[UserState.values().length];
            try {
                $SwitchMap$com$amazonaws$mobile$client$UserState[UserState.SIGNED_IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazonaws$mobile$client$UserState[UserState.SIGNED_OUT_USER_POOLS_TOKENS_INVALID.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazonaws$mobile$client$UserState[UserState.SIGNED_OUT_FEDERATED_TOKENS_INVALID.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazonaws$mobile$client$UserState[UserState.GUEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazonaws$mobile$client$UserState[UserState.SIGNED_OUT.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public void confirmSignIn(Map<String, String> map, Map<String, String> map2, Callback<SignInResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_confirmSignIn(map, internalCallback, map2));
    }

    public SignInResult confirmSignIn(Map<String, String> map, Map<String, String> map2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (SignInResult) internalCallback.await(_confirmSignIn(map, internalCallback, map2));
    }

    public void confirmSignIn(Map<String, String> map, Callback<SignInResult> callback) {
        confirmSignIn(map, (Map<String, String>) null, callback);
    }

    public SignInResult confirmSignIn(Map<String, String> map) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (SignInResult) internalCallback.await(_confirmSignIn(map, internalCallback, null));
    }

    private Runnable _confirmSignIn(final Map<String, String> map, final Callback<SignInResult> callback, final Map<String, String> map2) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.8
            /* JADX WARN: Removed duplicated region for block: B:18:0x00af  */
            /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                ChallengeContinuation challengeContinuation;
                if (AWSMobileClient.this.signInState != null) {
                    int i = C121228.$SwitchMap$com$amazonaws$mobile$client$results$SignInState[AWSMobileClient.this.signInState.ordinal()];
                    if (i == 1) {
                        callback.onError(new IllegalStateException("Please use confirmSignIn(String, Callback) for SMS_MFA challenges"));
                    } else if (i != 2 && i != 3) {
                        if (i == 4) {
                            challengeContinuation = null;
                            callback.onError(new IllegalStateException("confirmSignIn called after signIn has succeeded"));
                            if (challengeContinuation == null) {
                                challengeContinuation.continueTask();
                                return;
                            }
                            return;
                        }
                        callback.onError(new IllegalStateException("confirmSignIn called on unsupported operation, please file a feature request"));
                        return;
                    }
                    for (String str : map.keySet()) {
                        AWSMobileClient.this.signInChallengeContinuation.setChallengeResponse(str, (String) map.get(str));
                    }
                    challengeContinuation = AWSMobileClient.this.signInChallengeContinuation;
                    AWSMobileClient.this.signInCallback = new InternalCallback(callback);
                    if (SignInState.CUSTOM_CHALLENGE.equals(AWSMobileClient.this.signInState) && map2 != null) {
                        AWSMobileClient.this.signInChallengeContinuation.setClientMetaData(map2);
                    }
                    if (challengeContinuation == null) {
                    }
                } else {
                    callback.onError(new IllegalStateException("Cannot call confirmSignIn(Map<String, String>, Callback) without initiating sign-in. This call is used for CUSTOM_CHALLENGE sign-in state."));
                }
            }
        };
    }

    public void signOut() {
        String str = null;
        this.mCognitoUserSession = null;
        CognitoUserPool cognitoUserPool = this.userpool;
        if (cognitoUserPool != null) {
            cognitoUserPool.getCurrentUser().signOut();
            this.userpool.getUser().signOut();
        }
        CognitoCachingCredentialsProvider cognitoCachingCredentialsProvider = this.cognitoIdentity;
        if (cognitoCachingCredentialsProvider != null) {
            cognitoCachingCredentialsProvider.clear();
        }
        if (IdentityManager.getDefaultIdentityManager() != null) {
            IdentityManager.getDefaultIdentityManager().signOut();
        }
        this.mFederatedLoginsMap.clear();
        this.mStore.clear();
        if (this.awsConfiguration.optJsonObject(AUTH_KEY) != null && this.awsConfiguration.optJsonObject(AUTH_KEY).has("OAuth")) {
            try {
                str = this.awsConfiguration.optJsonObject(AUTH_KEY).getJSONObject("OAuth").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Auth auth = this.hostedUI;
            if (auth != null) {
                auth.signOut(true);
            }
            OAuth2Client oAuth2Client = this.mOAuth2Client;
            if (oAuth2Client != null) {
                oAuth2Client.signOut();
            }
        }
        this.mStore.set(HOSTED_UI_KEY, str);
        setUserState(getUserStateDetails(false));
        releaseSignInWait();
    }

    public void signOut(SignOutOptions signOutOptions) throws Exception {
        _signOut(signOutOptions).await();
    }

    public void signOut(SignOutOptions signOutOptions, Callback<Void> callback) {
        _signOut(signOutOptions).async(callback);
    }

    private ReturningRunnable<Void> _signOut(final SignOutOptions signOutOptions) {
        return new ReturningRunnable<Void>() { // from class: com.amazonaws.mobile.client.AWSMobileClient.9
            @Override // com.amazonaws.mobile.client.internal.ReturningRunnable
            public Void run() throws Exception {
                if (signOutOptions.isSignOutGlobally()) {
                    GlobalSignOutRequest globalSignOutRequest = new GlobalSignOutRequest();
                    globalSignOutRequest.setAccessToken(AWSMobileClient.this.getTokens().getAccessToken().getTokenString());
                    AWSMobileClient.this.userpoolLL.globalSignOut(globalSignOutRequest);
                }
                if (signOutOptions.isInvalidateTokens()) {
                    if (AWSMobileClient.this.userpool != null) {
                        AWSMobileClient.this.userpool.getCurrentUser().revokeTokens();
                    }
                    if (AWSMobileClient.this.hostedUI != null) {
                        if (signOutOptions.getBrowserPackage() != null) {
                            AWSMobileClient.this.hostedUI.setBrowserPackage(signOutOptions.getBrowserPackage());
                        }
                        AWSMobileClient.this.hostedUI.signOut();
                    } else if (AWSMobileClient.this.mOAuth2Client != null) {
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        JSONObject hostedUIJSON = AWSMobileClient.this.getHostedUIJSON();
                        Uri.Builder buildUpon = Uri.parse(hostedUIJSON.getString("SignOutURI")).buildUpon();
                        if (AWSMobileClient.this.getHostedUIJSON().optString("SignOutRedirectURI", null) != null) {
                            buildUpon.appendQueryParameter("redirect_uri", AWSMobileClient.this.getHostedUIJSON().getString("SignOutRedirectURI"));
                        }
                        JSONObject jSONObject = hostedUIJSON.getJSONObject("SignOutQueryParameters");
                        if (jSONObject != null) {
                            Iterator<String> keys = jSONObject.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                buildUpon.appendQueryParameter(next, jSONObject.getString(next));
                            }
                        }
                        final Exception[] excArr = new Exception[1];
                        AWSMobileClient.this.mOAuth2Client.signOut(buildUpon.build(), new Callback<Void>() { // from class: com.amazonaws.mobile.client.AWSMobileClient.9.1
                            @Override // com.amazonaws.mobile.client.Callback
                            public void onResult(Void r1) {
                                countDownLatch.countDown();
                            }

                            @Override // com.amazonaws.mobile.client.Callback
                            public void onError(Exception exc) {
                                excArr[0] = exc;
                                countDownLatch.countDown();
                            }
                        });
                        countDownLatch.await();
                        if (excArr[0] != null) {
                            throw excArr[0];
                        }
                    }
                }
                AWSMobileClient.this.signOut();
                return null;
            }
        };
    }

    public void federatedSignIn(String str, String str2, Callback<UserStateDetails> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_federatedSignIn(str, str2, null, internalCallback, true));
    }

    public UserStateDetails federatedSignIn(String str, String str2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (UserStateDetails) internalCallback.await(_federatedSignIn(str, str2, null, internalCallback, true));
    }

    public void federatedSignIn(String str, String str2, FederatedSignInOptions federatedSignInOptions, Callback<UserStateDetails> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_federatedSignIn(str, str2, federatedSignInOptions, internalCallback, true));
    }

    public UserStateDetails federatedSignIn(String str, String str2, FederatedSignInOptions federatedSignInOptions) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (UserStateDetails) internalCallback.await(_federatedSignIn(str, str2, federatedSignInOptions, internalCallback, true));
    }

    protected void federatedSignInWithoutAssigningState(String str, String str2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        internalCallback.await(_federatedSignIn(str, str2, null, internalCallback, false));
    }

    protected void federatedSignInWithoutAssigningState(String str, String str2, Callback<UserStateDetails> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_federatedSignIn(str, str2, null, internalCallback, false));
    }

    private Runnable _federatedSignIn(final String str, final String str2, FederatedSignInOptions federatedSignInOptions, final Callback<UserStateDetails> callback, final boolean z) {
        final HashMap hashMap = new HashMap();
        this.mStore.set(SIGN_IN_MODE, SignInMode.FEDERATED_SIGN_IN.toString());
        try {
            hashMap.put(str, str2);
            Log.d(TAG, String.format("_federatedSignIn: Putting provider and token in store", new Object[0]));
            HashMap hashMap2 = new HashMap();
            hashMap2.put(PROVIDER_KEY, str);
            hashMap2.put(TOKEN_KEY, str2);
            hashMap2.put(FEDERATION_ENABLED_KEY, "true");
            if (IdentityProvider.DEVELOPER.equals(str)) {
                if (federatedSignInOptions == null) {
                    callback.onError(new Exception("Developer authenticated identities require theidentity id to be specified in FederatedSignInOptions"));
                }
                hashMap2.put(IDENTITY_ID_KEY, federatedSignInOptions.getCognitoIdentityId());
            }
            if (federatedSignInOptions != null && !StringUtils.isBlank(federatedSignInOptions.getCustomRoleARN())) {
                hashMap2.put(CUSTOM_ROLE_ARN_KEY, federatedSignInOptions.getCustomRoleARN());
            }
            this.mStore.set(hashMap2);
        } catch (Exception e) {
            callback.onError(e);
        }
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (AWSMobileClient.this.cognitoIdentity == null) {
                        callback.onError(new Exception("Federation is not enabled, please check if you have CognitoIdentity configured."));
                        return;
                    }
                    if (!str2.equals(AWSMobileClient.this.mFederatedLoginsMap.get(str))) {
                        AWSMobileClient.this.cognitoIdentity.clear();
                        AWSMobileClient.this.cognitoIdentity.setLogins(hashMap);
                    }
                    UserStateDetails userStateDetails = AWSMobileClient.this.getUserStateDetails(true);
                    AWSMobileClient.this.federateWithCognitoIdentity(str, str2);
                    callback.onResult(userStateDetails);
                    end(userStateDetails);
                } catch (Exception e2) {
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put(AWSMobileClient.PROVIDER_KEY, null);
                    hashMap3.put(AWSMobileClient.TOKEN_KEY, null);
                    hashMap3.put(AWSMobileClient.FEDERATION_ENABLED_KEY, null);
                    hashMap3.put(AWSMobileClient.IDENTITY_ID_KEY, null);
                    hashMap3.put(AWSMobileClient.CUSTOM_ROLE_ARN_KEY, null);
                    AWSMobileClient.this.mStore.set(hashMap3);
                    callback.onError(new RuntimeException("Error in federating the token.", e2));
                }
            }

            private void end(UserStateDetails userStateDetails) {
                if (z) {
                    AWSMobileClient.this.setUserState(userStateDetails);
                }
            }
        };
    }

    protected void federateWithCognitoIdentity(String str, String str2) {
        synchronized (this.federateWithCognitoIdentityLockObject) {
            if (!hasFederatedToken(str, str2)) {
                if (IdentityProvider.DEVELOPER.equals(str)) {
                    this.provider.setDeveloperAuthenticated(this.mStore.get(IDENTITY_ID_KEY), str2);
                } else {
                    this.provider.setNotDeveloperAuthenticated();
                }
                String str3 = this.mStore.get(CUSTOM_ROLE_ARN_KEY);
                if (!StringUtils.isBlank(str3)) {
                    this.cognitoIdentity.setCustomRoleArn(str3);
                }
                HashMap hashMap = new HashMap();
                hashMap.put(str, str2);
                this.cognitoIdentity.setLogins(hashMap);
                this.cognitoIdentity.refresh();
                this.mStore.set(IDENTITY_ID_KEY, this.cognitoIdentity.getIdentityId());
                this.mFederatedLoginsMap = this.cognitoIdentity.getLogins();
            }
        }
    }

    public Tokens getTokens() throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (Tokens) internalCallback.await(_getTokens(internalCallback, false));
    }

    public void getTokens(Callback<Tokens> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_getTokens(internalCallback, false));
    }

    protected Tokens getTokens(boolean z) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (Tokens) internalCallback.await(_getTokens(internalCallback, z));
    }

    private Runnable _getTokens(final Callback<Tokens> callback, final boolean z) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.11
            @Override // java.lang.Runnable
            public void run() {
                String str = AWSMobileClient.this.getSignInDetailsMap().get(AWSMobileClient.PROVIDER_KEY);
                if (str != null && !AWSMobileClient.this.userpoolsLoginKey.equals(str)) {
                    callback.onError(new Exception("getTokens does not support retrieving tokens for federated sign-in"));
                    return;
                }
                if (z && !AWSMobileClient.this.waitForSignIn()) {
                    callback.onError(new Exception("getTokens does not support retrieving tokens while signed-out"));
                    return;
                }
                if (!AWSMobileClient.this.isUserpoolsSignedIn()) {
                    callback.onError(new Exception("You must be signed-in with Cognito Userpools to be able to use getTokens"));
                }
                if (AWSMobileClient.this.getSignInMode().equals(SignInMode.HOSTED_UI)) {
                    AWSMobileClient.this._getHostedUITokens(callback);
                    return;
                }
                if (AWSMobileClient.this.getSignInMode().equals(SignInMode.OAUTH2)) {
                    callback.onError(new Exception("Tokens are not supported for OAuth2"));
                    return;
                }
                try {
                    AWSMobileClient.this.userpool.getCurrentUser().getSession(Collections.emptyMap(), new AuthenticationHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.11.1
                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
                        public void onSuccess(CognitoUserSession cognitoUserSession, CognitoDevice cognitoDevice) {
                            try {
                                AWSMobileClient.this.mCognitoUserSession = cognitoUserSession;
                                callback.onResult(new Tokens(cognitoUserSession.getAccessToken().getJWTToken(), cognitoUserSession.getIdToken().getJWTToken(), cognitoUserSession.getRefreshToken().getToken()));
                            } catch (Exception e) {
                                callback.onError(e);
                            }
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
                        public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String str2) {
                            signalTokensNotAvailable(null);
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
                        public void getMFACode(MultiFactorAuthenticationContinuation multiFactorAuthenticationContinuation) {
                            signalTokensNotAvailable(null);
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
                        public void authenticationChallenge(ChallengeContinuation challengeContinuation) {
                            signalTokensNotAvailable(null);
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
                        public void onFailure(Exception exc) {
                            signalTokensNotAvailable(exc);
                        }

                        private void signalTokensNotAvailable(Exception exc) {
                            Log.w(AWSMobileClient.TAG, "signalTokensNotAvailable");
                            callback.onError(new Exception("No cached session.", exc));
                        }
                    });
                } catch (Exception e) {
                    callback.onError(e);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _getHostedUITokens(final Callback<Tokens> callback) {
        this.hostedUI = this.hostedUI.getCurrentUser();
        this.hostedUI.setAuthHandler(new AuthHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.12
            public void onSuccess(AuthUserSession authUserSession) {
                callback.onResult(new Tokens(authUserSession.getAccessToken().getJWTToken(), authUserSession.getIdToken().getJWTToken(), authUserSession.getRefreshToken().getToken()));
            }

            public void onSignout() {
                callback.onError(new Exception("No cached session."));
            }

            public void onFailure(Exception exc) {
                callback.onError(new Exception("No cached session.", exc));
            }
        });
        this.hostedUI.getSessionWithoutWebUI();
    }

    public void signUp(String str, String str2, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Callback<SignUpResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_signUp(str, str2, map, map2, map3, internalCallback));
    }

    public SignUpResult signUp(String str, String str2, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (SignUpResult) internalCallback.await(_signUp(str, str2, map, map3, map2, internalCallback));
    }

    public void signUp(String str, String str2, Map<String, String> map, Map<String, String> map2, Callback<SignUpResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_signUp(str, str2, map, map2, Collections.emptyMap(), internalCallback));
    }

    public SignUpResult signUp(String str, String str2, Map<String, String> map, Map<String, String> map2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (SignUpResult) internalCallback.await(_signUp(str, str2, map, map2, Collections.emptyMap(), internalCallback));
    }

    private Runnable _signUp(final String str, final String str2, final Map<String, String> map, final Map<String, String> map2, final Map<String, String> map3, final Callback<SignUpResult> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.13
            @Override // java.lang.Runnable
            public void run() {
                CognitoUserAttributes cognitoUserAttributes = new CognitoUserAttributes();
                for (String str3 : map.keySet()) {
                    cognitoUserAttributes.addAttribute(str3, (String) map.get(str3));
                }
                AWSMobileClient.this.userpool.signUp(str, str2, cognitoUserAttributes, map2, map3, new SignUpHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.13.1
                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler
                    public void onSuccess(CognitoUser cognitoUser, com.amazonaws.services.cognitoidentityprovider.model.SignUpResult signUpResult) {
                        AWSMobileClient.this.signUpUser = cognitoUser;
                        if (signUpResult == null) {
                            callback.onError(new Exception("SignUpResult received is null"));
                        } else if (signUpResult.getCodeDeliveryDetails() == null) {
                            callback.onResult(new SignUpResult(signUpResult.getUserConfirmed().booleanValue(), null, signUpResult.getUserSub()));
                        } else {
                            callback.onResult(new SignUpResult(signUpResult.getUserConfirmed().booleanValue(), new UserCodeDeliveryDetails(signUpResult.getCodeDeliveryDetails().getDestination(), signUpResult.getCodeDeliveryDetails().getDeliveryMedium(), signUpResult.getCodeDeliveryDetails().getAttributeName()), signUpResult.getUserSub()));
                        }
                    }

                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler
                    public void onFailure(Exception exc) {
                        callback.onError(exc);
                    }
                });
            }
        };
    }

    public void confirmSignUp(String str, String str2, Map<String, String> map, Callback<SignUpResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_confirmSignUp(str, str2, map, internalCallback));
    }

    public SignUpResult confirmSignUp(String str, String str2, Map<String, String> map) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (SignUpResult) internalCallback.await(_confirmSignUp(str, str2, map, internalCallback));
    }

    public void confirmSignUp(String str, String str2, Callback<SignUpResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_confirmSignUp(str, str2, Collections.emptyMap(), internalCallback));
    }

    public SignUpResult confirmSignUp(String str, String str2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (SignUpResult) internalCallback.await(_confirmSignUp(str, str2, Collections.emptyMap(), internalCallback));
    }

    private Runnable _confirmSignUp(final String str, final String str2, final Map<String, String> map, final Callback<SignUpResult> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.14
            @Override // java.lang.Runnable
            public void run() {
                AWSMobileClient.this.userpool.getUser(str).confirmSignUp(str2, false, map, new GenericHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.14.1
                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
                    public void onSuccess() {
                        callback.onResult(new SignUpResult(true, null, null));
                        AWSMobileClient.this.signUpUser = null;
                    }

                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
                    public void onFailure(Exception exc) {
                        callback.onError(exc);
                    }
                });
            }
        };
    }

    public void resendSignUp(String str, Callback<SignUpResult> callback) {
        resendSignUp(str, Collections.emptyMap(), callback);
    }

    public void resendSignUp(String str, Map<String, String> map, Callback<SignUpResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_resendSignUp(str, map, internalCallback));
    }

    public SignUpResult resendSignUp(String str) throws Exception {
        return resendSignUp(str, Collections.emptyMap());
    }

    public SignUpResult resendSignUp(String str, Map<String, String> map) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (SignUpResult) internalCallback.await(_resendSignUp(str, map, internalCallback));
    }

    private Runnable _resendSignUp(final String str, final Map<String, String> map, final Callback<SignUpResult> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.15
            @Override // java.lang.Runnable
            public void run() {
                AWSMobileClient.this.userpool.getUser(str).resendConfirmationCodeInBackground(map, new VerificationHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.15.1
                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.VerificationHandler
                    public void onSuccess(CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                        callback.onResult(new SignUpResult(false, new UserCodeDeliveryDetails(cognitoUserCodeDeliveryDetails.getDestination(), cognitoUserCodeDeliveryDetails.getDeliveryMedium(), cognitoUserCodeDeliveryDetails.getAttributeName()), null));
                    }

                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.VerificationHandler
                    public void onFailure(Exception exc) {
                        callback.onError(exc);
                    }
                });
            }
        };
    }

    public void forgotPassword(String str, Map<String, String> map, Callback<ForgotPasswordResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_forgotPassword(str, map, internalCallback));
    }

    public ForgotPasswordResult forgotPassword(String str, Map<String, String> map) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (ForgotPasswordResult) internalCallback.await(_forgotPassword(str, map, internalCallback));
    }

    public void forgotPassword(String str, Callback<ForgotPasswordResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_forgotPassword(str, Collections.emptyMap(), internalCallback));
    }

    public ForgotPasswordResult forgotPassword(String str) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (ForgotPasswordResult) internalCallback.await(_forgotPassword(str, Collections.emptyMap(), internalCallback));
    }

    private Runnable _forgotPassword(final String str, final Map<String, String> map, final Callback<ForgotPasswordResult> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.16
            @Override // java.lang.Runnable
            public void run() {
                AWSMobileClient.this.forgotPasswordCallback = new InternalCallback(callback);
                AWSMobileClient.this.userpool.getUser(str).forgotPasswordInBackground(map, new ForgotPasswordHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.16.1
                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler
                    public void onSuccess() {
                        AWSMobileClient.this.forgotPasswordCallback.onResult(new ForgotPasswordResult(ForgotPasswordState.DONE));
                    }

                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler
                    public void getResetCode(ForgotPasswordContinuation forgotPasswordContinuation) {
                        AWSMobileClient.this.forgotPasswordContinuation = forgotPasswordContinuation;
                        ForgotPasswordResult forgotPasswordResult = new ForgotPasswordResult(ForgotPasswordState.CONFIRMATION_CODE);
                        CognitoUserCodeDeliveryDetails parameters = forgotPasswordContinuation.getParameters();
                        forgotPasswordResult.setParameters(new UserCodeDeliveryDetails(parameters.getDestination(), parameters.getDeliveryMedium(), parameters.getAttributeName()));
                        AWSMobileClient.this.forgotPasswordCallback.onResult(forgotPasswordResult);
                    }

                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler
                    public void onFailure(Exception exc) {
                        AWSMobileClient.this.forgotPasswordCallback.onError(exc);
                    }
                });
            }
        };
    }

    public void confirmForgotPassword(String str, String str2, Map<String, String> map, Callback<ForgotPasswordResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_confirmForgotPassword(str, str2, map, internalCallback));
    }

    public ForgotPasswordResult confirmForgotPassword(String str, Map<String, String> map, String str2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (ForgotPasswordResult) internalCallback.await(_confirmForgotPassword(str, str2, map, internalCallback));
    }

    @Deprecated
    public void confirmForgotPassword(String str, String str2, Callback<ForgotPasswordResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_confirmForgotPassword(str, str2, Collections.emptyMap(), internalCallback));
    }

    public void confirmForgotPassword(String str, String str2, String str3, final Callback<ForgotPasswordResult> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        this.forgotPasswordContinuation = new ForgotPasswordContinuation(this.userpool.getUser(str), null, true, new ForgotPasswordHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.17
            @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler
            public void onSuccess() {
                callback.onResult(new ForgotPasswordResult(ForgotPasswordState.DONE));
            }

            @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler
            public void getResetCode(ForgotPasswordContinuation forgotPasswordContinuation) {
                callback.onResult(new ForgotPasswordResult(ForgotPasswordState.CONFIRMATION_CODE));
            }

            @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler
            public void onFailure(Exception exc) {
                callback.onError(exc);
            }
        });
        internalCallback.async(_confirmForgotPassword(str2, str3, Collections.emptyMap(), internalCallback));
    }

    public ForgotPasswordResult confirmForgotPassword(String str, String str2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (ForgotPasswordResult) internalCallback.await(_confirmForgotPassword(str, str2, Collections.emptyMap(), internalCallback));
    }

    private Runnable _confirmForgotPassword(final String str, final String str2, final Map<String, String> map, final Callback<ForgotPasswordResult> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.18
            @Override // java.lang.Runnable
            public void run() {
                if (AWSMobileClient.this.forgotPasswordContinuation != null) {
                    AWSMobileClient.this.forgotPasswordContinuation.setPassword(str);
                    AWSMobileClient.this.forgotPasswordContinuation.setVerificationCode(str2);
                    AWSMobileClient.this.forgotPasswordContinuation.setClientMetadata(map);
                    AWSMobileClient.this.forgotPasswordCallback = new InternalCallback(callback);
                    AWSMobileClient.this.forgotPasswordContinuation.continueTask();
                    return;
                }
                callback.onError(new IllegalStateException("confirmForgotPassword called before initiating forgotPassword"));
            }
        };
    }

    public void changePassword(String str, String str2, Callback<Void> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_changePassword(str, str2, internalCallback));
    }

    public void changePassword(String str, String str2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        internalCallback.await(_changePassword(str, str2, internalCallback));
    }

    private Runnable _changePassword(final String str, final String str2, final Callback<Void> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.19
            @Override // java.lang.Runnable
            public void run() {
                AWSMobileClient.this.userpool.getCurrentUser().changePassword(str, str2, new GenericHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.19.1
                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
                    public void onSuccess() {
                        callback.onResult(null);
                    }

                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
                    public void onFailure(Exception exc) {
                        callback.onError(exc);
                    }
                });
            }
        };
    }

    public void getUserAttributes(Callback<Map<String, String>> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_getUserAttributes(internalCallback));
    }

    public Map<String, String> getUserAttributes() throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (Map) internalCallback.await(_getUserAttributes(internalCallback));
    }

    private Runnable _getUserAttributes(final Callback<Map<String, String>> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.20
            @Override // java.lang.Runnable
            public void run() {
                if (!AWSMobileClient.this.waitForSignIn()) {
                    callback.onError(new Exception("Operation requires a signed-in state"));
                } else {
                    AWSMobileClient.this.userpool.getCurrentUser().getDetails(new GetDetailsHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.20.1
                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler
                        public void onSuccess(CognitoUserDetails cognitoUserDetails) {
                            callback.onResult(cognitoUserDetails.getAttributes().getAttributes());
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler
                        public void onFailure(Exception exc) {
                            callback.onError(exc);
                        }
                    });
                }
            }
        };
    }

    public void updateUserAttributes(Map<String, String> map, Callback<List<UserCodeDeliveryDetails>> callback) {
        updateUserAttributes(map, Collections.emptyMap(), callback);
    }

    public void updateUserAttributes(Map<String, String> map, Map<String, String> map2, Callback<List<UserCodeDeliveryDetails>> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_updateUserAttributes(map, map2, internalCallback));
    }

    public List<UserCodeDeliveryDetails> updateUserAttributes(Map<String, String> map) throws Exception {
        return updateUserAttributes(map, Collections.emptyMap());
    }

    public List<UserCodeDeliveryDetails> updateUserAttributes(Map<String, String> map, Map<String, String> map2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (List) internalCallback.await(_updateUserAttributes(map, map2, internalCallback));
    }

    private Runnable _updateUserAttributes(final Map<String, String> map, final Map<String, String> map2, final Callback<List<UserCodeDeliveryDetails>> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.21
            @Override // java.lang.Runnable
            public void run() {
                if (!AWSMobileClient.this.waitForSignIn()) {
                    callback.onError(new Exception("Operation requires a signed-in state"));
                    return;
                }
                CognitoUserAttributes cognitoUserAttributes = new CognitoUserAttributes();
                Map map3 = map;
                if (map3 != null) {
                    for (String str : map3.keySet()) {
                        cognitoUserAttributes.addAttribute(str, (String) map.get(str));
                    }
                }
                AWSMobileClient.this.userpool.getCurrentUser().updateAttributes(cognitoUserAttributes, map2, new UpdateAttributesHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.21.1
                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.UpdateAttributesHandler
                    public void onSuccess(List<CognitoUserCodeDeliveryDetails> list) {
                        LinkedList linkedList = new LinkedList();
                        for (CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails : list) {
                            linkedList.add(new UserCodeDeliveryDetails(cognitoUserCodeDeliveryDetails.getDestination(), cognitoUserCodeDeliveryDetails.getDeliveryMedium(), cognitoUserCodeDeliveryDetails.getAttributeName()));
                        }
                        callback.onResult(linkedList);
                    }

                    @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.UpdateAttributesHandler
                    public void onFailure(Exception exc) {
                        callback.onError(exc);
                    }
                });
            }
        };
    }

    public void verifyUserAttribute(String str, Callback<UserCodeDeliveryDetails> callback) {
        verifyUserAttribute(str, Collections.emptyMap(), callback);
    }

    public void verifyUserAttribute(String str, Map<String, String> map, Callback<UserCodeDeliveryDetails> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_verifyUserAttribute(str, map, internalCallback));
    }

    public UserCodeDeliveryDetails verifyUserAttribute(String str) throws Exception {
        return verifyUserAttribute(str, Collections.emptyMap());
    }

    public UserCodeDeliveryDetails verifyUserAttribute(String str, Map<String, String> map) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (UserCodeDeliveryDetails) internalCallback.await(_verifyUserAttribute(str, map, internalCallback));
    }

    private Runnable _verifyUserAttribute(final String str, final Map<String, String> map, final Callback<UserCodeDeliveryDetails> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.22
            @Override // java.lang.Runnable
            public void run() {
                if (!AWSMobileClient.this.waitForSignIn()) {
                    callback.onError(new Exception("Operation requires a signed-in state"));
                } else {
                    AWSMobileClient.this.userpool.getCurrentUser().getAttributeVerificationCodeInBackground(map, str, new VerificationHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.22.1
                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.VerificationHandler
                        public void onSuccess(CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                            callback.onResult(new UserCodeDeliveryDetails(cognitoUserCodeDeliveryDetails.getDestination(), cognitoUserCodeDeliveryDetails.getDeliveryMedium(), cognitoUserCodeDeliveryDetails.getAttributeName()));
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.VerificationHandler
                        public void onFailure(Exception exc) {
                            callback.onError(exc);
                        }
                    });
                }
            }
        };
    }

    public void confirmUpdateUserAttribute(String str, String str2, Callback<Void> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_confirmUserAttribute(str, str2, internalCallback));
    }

    public void confirmUpdateUserAttribute(String str, String str2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        internalCallback.await(_confirmUserAttribute(str, str2, internalCallback));
    }

    public void confirmVerifyUserAttribute(String str, String str2, Callback<Void> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_confirmUserAttribute(str, str2, internalCallback));
    }

    public void confirmVerifyUserAttribute(String str, String str2) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        internalCallback.await(_confirmUserAttribute(str, str2, internalCallback));
    }

    private Runnable _confirmUserAttribute(final String str, final String str2, final Callback<Void> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.23
            @Override // java.lang.Runnable
            public void run() {
                if (!AWSMobileClient.this.waitForSignIn()) {
                    callback.onError(new Exception("Operation requires a signed-in state"));
                } else {
                    AWSMobileClient.this.userpool.getCurrentUser().verifyAttribute(str, str2, new GenericHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.23.1
                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
                        public void onSuccess() {
                            callback.onResult(null);
                        }

                        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
                        public void onFailure(Exception exc) {
                            callback.onError(exc);
                        }
                    });
                }
            }
        };
    }

    public boolean handleAuthResponse(Intent intent) {
        Auth auth = this.hostedUI;
        if (auth == null) {
            OAuth2Client oAuth2Client = this.mOAuth2Client;
            return (oAuth2Client == null || intent == null || !oAuth2Client.handleRedirect(intent.getData())) ? false : true;
        }
        if (intent != null) {
            auth.getTokens(intent.getData());
        } else {
            auth.handleFlowCancelled();
        }
        return true;
    }

    public void showSignIn(Activity activity, Callback<UserStateDetails> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_showSignIn(activity, SignInUIOptions.builder().build(), internalCallback));
    }

    public UserStateDetails showSignIn(Activity activity) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (UserStateDetails) internalCallback.await(_showSignIn(activity, SignInUIOptions.builder().build(), internalCallback));
    }

    public void showSignIn(Activity activity, SignInUIOptions signInUIOptions, Callback<UserStateDetails> callback) {
        InternalCallback internalCallback = new InternalCallback(callback);
        internalCallback.async(_showSignIn(activity, signInUIOptions, internalCallback));
    }

    public UserStateDetails showSignIn(Activity activity, SignInUIOptions signInUIOptions) throws Exception {
        InternalCallback internalCallback = new InternalCallback();
        return (UserStateDetails) internalCallback.await(_showSignIn(activity, signInUIOptions, internalCallback));
    }

    private Runnable _showSignIn(Activity activity, SignInUIOptions signInUIOptions, final Callback<UserStateDetails> callback) {
        if (signInUIOptions.getHostedUIOptions() != null) {
            JSONObject hostedUIJSON = getHostedUIJSON();
            if (hostedUIJSON == null) {
                return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.24
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onError(new Exception("showSignIn called with HostedUI options in awsconfiguration.json"));
                    }
                };
            }
            if (hostedUIJSON.optString("TokenURI", null) != null) {
                return _showSignInOAuth2UI(activity, signInUIOptions, callback);
            }
            return _showSignInHostedUI(activity, signInUIOptions, callback);
        }
        return _showSignInDropInUI(activity, signInUIOptions, callback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazonaws.mobile.client.AWSMobileClient$25 */
    /* loaded from: classes.dex */
    public class RunnableC120925 implements Runnable {
        final /* synthetic */ Callback val$callback;
        final /* synthetic */ SignInUIOptions val$signInUIOptions;

        RunnableC120925(SignInUIOptions signInUIOptions, Callback callback) {
            this.val$signInUIOptions = signInUIOptions;
            this.val$callback = callback;
        }

        @Override // java.lang.Runnable
        public void run() {
            HostedUIOptions hostedUIOptions = this.val$signInUIOptions.getHostedUIOptions();
            JSONObject hostedUIJSONFromJSON = AWSMobileClient.this.getHostedUIJSONFromJSON();
            if (hostedUIJSONFromJSON == null) {
                this.val$callback.onError(new Exception("Could not create OAuth configuration object"));
            }
            if (hostedUIOptions.getFederationEnabled() != null) {
                AWSMobileClient.this.mStore.set(AWSMobileClient.FEDERATION_ENABLED_KEY, hostedUIOptions.getFederationEnabled().booleanValue() ? "true" : "false");
            } else {
                AWSMobileClient.this.mStore.set(AWSMobileClient.FEDERATION_ENABLED_KEY, "true");
            }
            AWSMobileClient.this.mStore.set(AWSMobileClient.SIGN_IN_MODE, SignInMode.OAUTH2.toString());
            if (AWSMobileClient.this.isFederationEnabled() && hostedUIOptions.getFederationProviderName() == null) {
                throw new IllegalArgumentException("OAuth flow requires a federation provider name if federation is enabled.");
            }
            if (hostedUIOptions.getSignOutQueryParameters() != null) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    for (Map.Entry<String, String> entry : hostedUIOptions.getSignOutQueryParameters().entrySet()) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                    hostedUIJSONFromJSON.put("SignOutQueryParameters", jSONObject);
                } catch (JSONException e) {
                    this.val$callback.onError(new Exception("Failed to construct sign-out query parameters", e));
                    return;
                }
            }
            if (hostedUIOptions.getTokenQueryParameters() != null) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    for (Map.Entry<String, String> entry2 : hostedUIOptions.getTokenQueryParameters().entrySet()) {
                        jSONObject2.put(entry2.getKey(), entry2.getValue());
                    }
                    hostedUIJSONFromJSON.put("TokenQueryParameters", jSONObject2);
                } catch (JSONException e2) {
                    this.val$callback.onError(new Exception("Failed to construct token query parameters", e2));
                    return;
                }
            }
            AWSMobileClient.this.mStore.set(AWSMobileClient.HOSTED_UI_KEY, hostedUIJSONFromJSON.toString());
            try {
                Uri.Builder buildUpon = Uri.parse(hostedUIJSONFromJSON.getString("SignInURI")).buildUpon();
                if (hostedUIOptions.getSignInQueryParameters() != null) {
                    for (Map.Entry<String, String> entry3 : hostedUIOptions.getSignInQueryParameters().entrySet()) {
                        buildUpon.appendQueryParameter(entry3.getKey(), entry3.getValue());
                    }
                }
                buildUpon.appendQueryParameter("redirect_uri", hostedUIJSONFromJSON.getString("SignInRedirectURI"));
                buildUpon.appendQueryParameter(OAuth2Constants.SCOPES, hostedUIJSONFromJSON.getJSONArray("Scopes").join(" "));
                buildUpon.appendQueryParameter("client_id", hostedUIJSONFromJSON.getString("AppClientId"));
                HashMap hashMap = new HashMap();
                try {
                    Uri.Builder buildUpon2 = Uri.parse(hostedUIJSONFromJSON.getString("TokenURI")).buildUpon();
                    if (hostedUIOptions.getTokenQueryParameters() != null) {
                        for (Map.Entry<String, String> entry4 : hostedUIOptions.getTokenQueryParameters().entrySet()) {
                            buildUpon2.appendQueryParameter(entry4.getKey(), entry4.getValue());
                        }
                    }
                    hashMap.put("client_id", hostedUIJSONFromJSON.getString("AppClientId"));
                    hashMap.put("redirect_uri", hostedUIJSONFromJSON.getString("SignInRedirectURI"));
                    AWSMobileClient.this.mOAuth2Client.authorize(buildUpon.build(), new AnonymousClass1(buildUpon2.build(), hashMap, hostedUIOptions));
                } catch (Exception e3) {
                    throw new RuntimeException("Failed to construct tokens url for OAuth", e3);
                }
            } catch (Exception e4) {
                throw new RuntimeException("Failed to construct authorization url for OAuth", e4);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.amazonaws.mobile.client.AWSMobileClient$25$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public class AnonymousClass1 implements Callback<AuthorizeResponse> {
            final /* synthetic */ HostedUIOptions val$hostedUIOptions;
            final /* synthetic */ Map val$tokensBody;
            final /* synthetic */ Uri val$tokensUri;

            AnonymousClass1(Uri uri, Map map, HostedUIOptions hostedUIOptions) {
                this.val$tokensUri = uri;
                this.val$tokensBody = map;
                this.val$hostedUIOptions = hostedUIOptions;
            }

            @Override // com.amazonaws.mobile.client.Callback
            public void onResult(AuthorizeResponse authorizeResponse) {
                Log.i(AWSMobileClient.TAG, "onResult: OAuth2 callback occurred, exchanging code for token");
                AWSMobileClient.this.mOAuth2Client.requestTokens(this.val$tokensUri, new HashMap(), this.val$tokensBody, authorizeResponse.getCode(), new Callback<OAuth2Tokens>() { // from class: com.amazonaws.mobile.client.AWSMobileClient.25.1.1
                    @Override // com.amazonaws.mobile.client.Callback
                    public void onResult(OAuth2Tokens oAuth2Tokens) {
                        if (AWSMobileClient.this.isFederationEnabled()) {
                            AWSMobileClient.this.federatedSignInWithoutAssigningState(AnonymousClass1.this.val$hostedUIOptions.getFederationProviderName(), oAuth2Tokens.getIdToken(), new Callback<UserStateDetails>() { // from class: com.amazonaws.mobile.client.AWSMobileClient.25.1.1.1
                                @Override // com.amazonaws.mobile.client.Callback
                                public void onResult(UserStateDetails userStateDetails) {
                                    UserStateDetails userStateDetails2 = AWSMobileClient.this.getUserStateDetails(false);
                                    RunnableC120925.this.val$callback.onResult(userStateDetails2);
                                    AWSMobileClient.this.setUserState(userStateDetails2);
                                }

                                @Override // com.amazonaws.mobile.client.Callback
                                public void onError(Exception exc) {
                                    UserStateDetails userStateDetails = AWSMobileClient.this.getUserStateDetails(false);
                                    RunnableC120925.this.val$callback.onResult(userStateDetails);
                                    AWSMobileClient.this.setUserState(userStateDetails);
                                }
                            });
                            return;
                        }
                        UserStateDetails userStateDetails = AWSMobileClient.this.getUserStateDetails(false);
                        RunnableC120925.this.val$callback.onResult(userStateDetails);
                        AWSMobileClient.this.setUserState(userStateDetails);
                    }

                    @Override // com.amazonaws.mobile.client.Callback
                    public void onError(Exception exc) {
                        RunnableC120925.this.val$callback.onError(exc);
                    }
                });
            }

            @Override // com.amazonaws.mobile.client.Callback
            public void onError(Exception exc) {
                RunnableC120925.this.val$callback.onError(exc);
            }
        }
    }

    private Runnable _showSignInOAuth2UI(Activity activity, SignInUIOptions signInUIOptions, Callback<UserStateDetails> callback) {
        return new RunnableC120925(signInUIOptions, callback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazonaws.mobile.client.AWSMobileClient$26 */
    /* loaded from: classes.dex */
    public class RunnableC121026 implements Runnable {
        final /* synthetic */ Callback val$callback;
        final /* synthetic */ Activity val$callingActivity;
        final /* synthetic */ SignInUIOptions val$signInUIOptions;

        RunnableC121026(SignInUIOptions signInUIOptions, Callback callback, Activity activity) {
            this.val$signInUIOptions = signInUIOptions;
            this.val$callback = callback;
            this.val$callingActivity = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObject;
            HostedUIOptions hostedUIOptions = this.val$signInUIOptions.getHostedUIOptions();
            HashSet hashSet = null;
            try {
                jSONObject = new JSONObject(AWSMobileClient.this.getHostedUIJSONFromJSON().toString());
            } catch (JSONException e) {
                this.val$callback.onError(new Exception("Could not create OAuth configuration object", e));
                jSONObject = null;
            }
            if (hostedUIOptions.getFederationEnabled() != null) {
                AWSMobileClient.this.mStore.set(AWSMobileClient.FEDERATION_ENABLED_KEY, hostedUIOptions.getFederationEnabled().booleanValue() ? "true" : "false");
            } else {
                AWSMobileClient.this.mStore.set(AWSMobileClient.FEDERATION_ENABLED_KEY, "true");
            }
            if (hostedUIOptions.getSignOutQueryParameters() != null) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    for (Map.Entry<String, String> entry : hostedUIOptions.getSignOutQueryParameters().entrySet()) {
                        jSONObject2.put(entry.getKey(), entry.getValue());
                    }
                    jSONObject.put("SignOutQueryParameters", jSONObject2);
                } catch (JSONException e2) {
                    this.val$callback.onError(new Exception("Failed to construct sign-out query parameters", e2));
                    return;
                }
            }
            if (hostedUIOptions.getTokenQueryParameters() != null) {
                try {
                    JSONObject jSONObject3 = new JSONObject();
                    for (Map.Entry<String, String> entry2 : hostedUIOptions.getTokenQueryParameters().entrySet()) {
                        jSONObject3.put(entry2.getKey(), entry2.getValue());
                    }
                    jSONObject.put("TokenQueryParameters", jSONObject3);
                } catch (JSONException e3) {
                    this.val$callback.onError(new Exception("Failed to construct token query parameters", e3));
                    return;
                }
            }
            AWSMobileClient.this.mStore.set(AWSMobileClient.HOSTED_UI_KEY, jSONObject.toString());
            if (hostedUIOptions.getScopes() != null) {
                hashSet = new HashSet();
                Collections.addAll(hashSet, hostedUIOptions.getScopes());
            }
            String identityProvider = hostedUIOptions.getIdentityProvider();
            String idpIdentifier = hostedUIOptions.getIdpIdentifier();
            AWSMobileClient.this.mStore.set(AWSMobileClient.SIGN_IN_MODE, SignInMode.HOSTED_UI.toString());
            try {
                Auth.Builder hostedUI = AWSMobileClient.this.getHostedUI(jSONObject);
                hostedUI.setPersistenceEnabled(AWSMobileClient.this.mIsPersistenceEnabled).setAuthHandler(new AuthHandler() { // from class: com.amazonaws.mobile.client.AWSMobileClient.26.1
                    boolean hasSucceededOnce = false;

                    public void onSuccess(AuthUserSession authUserSession) {
                        Log.d(AWSMobileClient.TAG, "onSuccess: HostedUI signed-in");
                        this.hasSucceededOnce = true;
                        if (AWSMobileClient.this.isFederationEnabled()) {
                            AWSMobileClient.this.federatedSignInWithoutAssigningState(AWSMobileClient.this.userpoolsLoginKey, authUserSession.getIdToken().getJWTToken(), new Callback<UserStateDetails>() { // from class: com.amazonaws.mobile.client.AWSMobileClient.26.1.1
                                @Override // com.amazonaws.mobile.client.Callback
                                public void onResult(UserStateDetails userStateDetails) {
                                    Log.d(AWSMobileClient.TAG, "onResult: Federation from the Hosted UI succeeded");
                                }

                                @Override // com.amazonaws.mobile.client.Callback
                                public void onError(Exception exc) {
                                    Log.e(AWSMobileClient.TAG, "onError: Federation from the Hosted UI failed", exc);
                                }
                            });
                        }
                        new Thread(new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.26.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                UserStateDetails userStateDetails = AWSMobileClient.this.getUserStateDetails(false);
                                RunnableC121026.this.val$callback.onResult(userStateDetails);
                                AWSMobileClient.this.setUserState(userStateDetails);
                            }
                        }).start();
                    }

                    public void onSignout() {
                        Log.d(AWSMobileClient.TAG, "onSignout: HostedUI signed-out");
                    }

                    public void onFailure(final Exception exc) {
                        if (this.hasSucceededOnce) {
                            Log.d(AWSMobileClient.TAG, "onFailure: Ignoring failure because HostedUI has signaled success at least once.");
                        } else {
                            new Thread(new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.26.1.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    RunnableC121026.this.val$callback.onError(exc);
                                }
                            }).start();
                        }
                    }
                });
                if (hashSet != null) {
                    hostedUI.setScopes(hashSet);
                }
                if (identityProvider != null) {
                    hostedUI.setIdentityProvider(identityProvider);
                }
                if (idpIdentifier != null) {
                    hostedUI.setIdpIdentifier(idpIdentifier);
                }
                AWSMobileClient.this.hostedUI = hostedUI.build();
                if (this.val$signInUIOptions.getBrowserPackage() != null) {
                    AWSMobileClient.this.hostedUI.setBrowserPackage(this.val$signInUIOptions.getBrowserPackage());
                }
                AWSMobileClient.this.hostedUI.getSession(this.val$callingActivity);
            } catch (JSONException e4) {
                throw new RuntimeException("Failed to construct HostedUI from awsconfiguration.json", e4);
            }
        }
    }

    private Runnable _showSignInHostedUI(Activity activity, SignInUIOptions signInUIOptions, Callback<UserStateDetails> callback) {
        return new RunnableC121026(signInUIOptions, callback, activity);
    }

    private Runnable _showSignInDropInUI(final Activity activity, final SignInUIOptions signInUIOptions, final Callback<UserStateDetails> callback) {
        return new Runnable() { // from class: com.amazonaws.mobile.client.AWSMobileClient.27
            @Override // java.lang.Runnable
            public void run() {
                synchronized (AWSMobileClient.this.showSignInLockObject) {
                    if (UserState.SIGNED_IN.equals(AWSMobileClient.this.getUserStateDetails(false).getUserState())) {
                        callback.onError(new RuntimeException("Called showSignIn while user is already signed-in"));
                        return;
                    }
                    AuthUIConfiguration.Builder isBackgroundColorFullScreen = new AuthUIConfiguration.Builder().canCancel(signInUIOptions.canCancel()).isBackgroundColorFullScreen(false);
                    if (signInUIOptions.getLogo() != null) {
                        isBackgroundColorFullScreen.logoResId(signInUIOptions.getLogo().intValue());
                    }
                    if (signInUIOptions.getBackgroundColor() != null) {
                        isBackgroundColorFullScreen.backgroundColor(signInUIOptions.getBackgroundColor().intValue());
                    }
                    if (AWSMobileClient.this.isConfigurationKeyPresent(AWSMobileClient.USER_POOLS)) {
                        isBackgroundColorFullScreen.userPools(true);
                    }
                    if (AWSMobileClient.this.isConfigurationKeyPresent(AWSMobileClient.FACEBOOK)) {
                        isBackgroundColorFullScreen.signInButton(FacebookButton.class);
                    }
                    if (AWSMobileClient.this.isConfigurationKeyPresent(AWSMobileClient.GOOGLE)) {
                        isBackgroundColorFullScreen.signInButton(GoogleButton.class);
                    }
                    AWSMobileClient.this.getClient(AWSMobileClient.this.mContext, SignInUI.class).login(activity, signInUIOptions.nextActivity() == null ? activity.getClass() : signInUIOptions.nextActivity()).authUIConfiguration(isBackgroundColorFullScreen.build()).enableFederation(false).execute();
                    AWSMobileClient.this.showSignInWaitLatch = new CountDownLatch(1);
                    try {
                        AWSMobileClient.this.showSignInWaitLatch.await();
                        callback.onResult(AWSMobileClient.this.getUserStateDetails(false));
                        Log.d(AWSMobileClient.TAG, "run: showSignIn completed");
                    } catch (InterruptedException e) {
                        callback.onError(e);
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeWithBuilder(InitializeBuilder initializeBuilder) {
        if (initializeBuilder.getAwsConfiguration() != null) {
            this.awsConfiguration = initializeBuilder.getAwsConfiguration();
        }
        if (initializeBuilder.getSignInProviderConfig() != null) {
            this.signInProviderConfig = initializeBuilder.getSignInProviderConfig();
        }
        try {
            fetchCognitoIdentity(initializeBuilder.getContext(), this.startupAuthResultHandler);
        } catch (Exception unused) {
            Log.e(TAG, "Error in initializing the AWSMobileClient. Check if AWS Cloud Config `awsconfiguration.json` is present in the application.");
        }
    }

    public AWSConfigurable getClient(Context context, Class<? extends AWSConfigurable> cls) {
        Log.d(TAG, "Retrieving the client instance for class: " + cls);
        AWSConfigurable aWSConfigurable = this.clientMap.get(cls);
        if (aWSConfigurable != null) {
            return aWSConfigurable;
        }
        try {
            aWSConfigurable = cls.newInstance().initialize(context.getApplicationContext(), this.awsConfiguration);
            this.clientMap.put(cls, aWSConfigurable);
            Log.d(TAG, "Created the new client: " + aWSConfigurable.toString());
            return aWSConfigurable;
        } catch (Exception e) {
            Log.e(TAG, "Error occurred in creating and initializing client. Check the context and the clientClass passed in: " + cls, e);
            return aWSConfigurable;
        }
    }

    private void fetchCognitoIdentity(Context context, StartupAuthResultHandler startupAuthResultHandler) {
        try {
            Log.d(TAG, "Fetching the Cognito Identity.");
            IdentityManager.setDefaultIdentityManager(new IdentityManager(context, this.awsConfiguration));
            if (this.signInProviderConfig == null) {
                registerConfigSignInProviders(this.awsConfiguration);
            } else {
                registerUserSignInProvidersWithPermissions();
            }
            resumeSession((Activity) context, startupAuthResultHandler);
        } catch (Exception e) {
            Log.e(TAG, "Error occurred in fetching the Cognito Identity and resuming the auth session", e);
        }
    }

    private void registerUserSignInProvidersWithPermissions() {
        Log.d(TAG, "Using the SignInProviderConfig supplied by the user.");
        IdentityManager defaultIdentityManager = IdentityManager.getDefaultIdentityManager();
        for (SignInProviderConfig signInProviderConfig : this.signInProviderConfig) {
            defaultIdentityManager.addSignInProvider(signInProviderConfig.getSignInProviderClass());
            if (signInProviderConfig.getProviderPermissions() != null) {
                if (FacebookSignInProvider.class.isInstance(signInProviderConfig.getSignInProviderClass())) {
                    FacebookSignInProvider.setPermissions(signInProviderConfig.getProviderPermissions());
                }
                if (GoogleSignInProvider.class.isInstance(signInProviderConfig.getSignInProviderClass())) {
                    GoogleSignInProvider.setPermissions(signInProviderConfig.getProviderPermissions());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerConfigSignInProviders(AWSConfiguration aWSConfiguration) {
        Log.d(TAG, "Using the SignInProviderConfig from `awsconfiguration.json`.");
        IdentityManager defaultIdentityManager = IdentityManager.getDefaultIdentityManager();
        try {
            if (isConfigurationKeyPresent(USER_POOLS, aWSConfiguration)) {
                defaultIdentityManager.addSignInProvider(CognitoUserPoolsSignInProvider.class);
            }
            if (isConfigurationKeyPresent(FACEBOOK, aWSConfiguration)) {
                defaultIdentityManager.addSignInProvider(FacebookSignInProvider.class);
            }
            if (isConfigurationKeyPresent(GOOGLE, aWSConfiguration)) {
                defaultIdentityManager.addSignInProvider(GoogleSignInProvider.class);
            }
        } catch (NoClassDefFoundError unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isConfigurationKeyPresent(String str) {
        return isConfigurationKeyPresent(str, this.awsConfiguration);
    }

    private boolean isConfigurationKeyPresent(String str, AWSConfiguration aWSConfiguration) {
        try {
            JSONObject optJsonObject = aWSConfiguration.optJsonObject(str);
            if (!str.equals(GOOGLE)) {
                return optJsonObject != null;
            }
            if (optJsonObject != null) {
                return optJsonObject.getString(GOOGLE_WEBAPP_CONFIG_KEY) != null;
            }
            return false;
        } catch (Exception unused) {
            Log.d(TAG, str + " not found in `awsconfiguration.json`");
            return false;
        }
    }

    private void resumeSession(Activity activity, StartupAuthResultHandler startupAuthResultHandler) {
        IdentityManager.getDefaultIdentityManager().resumeSession(activity, startupAuthResultHandler);
    }

    @Deprecated
    /* loaded from: classes.dex */
    public class InitializeBuilder {
        private AWSConfiguration awsConfiguration;
        private Context context;
        private SignInProviderConfig[] signInProviderConfig;

        @Deprecated
        public InitializeBuilder() {
            this.context = null;
            this.awsConfiguration = null;
            this.signInProviderConfig = null;
        }

        @Deprecated
        public InitializeBuilder(Context context) {
            this.context = context;
            this.awsConfiguration = null;
            this.signInProviderConfig = null;
        }

        @Deprecated
        public InitializeBuilder awsConfiguration(AWSConfiguration aWSConfiguration) {
            this.awsConfiguration = aWSConfiguration;
            return this;
        }

        @Deprecated
        public InitializeBuilder signInProviders(SignInProviderConfig... signInProviderConfigArr) {
            this.signInProviderConfig = signInProviderConfigArr;
            return this;
        }

        @Deprecated
        public AWSConfiguration getAwsConfiguration() {
            return this.awsConfiguration;
        }

        @Deprecated
        public SignInProviderConfig[] getSignInProviderConfig() {
            return this.signInProviderConfig;
        }

        @Deprecated
        public Context getContext() {
            return this.context;
        }

        @Deprecated
        public void execute() {
            AWSMobileClient.this.initializeWithBuilder(this);
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public class SignInProviderConfig {

        @Deprecated
        private String[] providerPermissions;

        @Deprecated
        private Class<? extends SignInProvider> signInProvider;

        @Deprecated
        public SignInProviderConfig(Class<? extends SignInProvider> cls, String... strArr) {
            this.signInProvider = cls;
            this.providerPermissions = strArr;
        }

        @Deprecated
        public Class<? extends SignInProvider> getSignInProviderClass() {
            return this.signInProvider;
        }

        @Deprecated
        public String[] getProviderPermissions() {
            return this.providerPermissions;
        }
    }
}
