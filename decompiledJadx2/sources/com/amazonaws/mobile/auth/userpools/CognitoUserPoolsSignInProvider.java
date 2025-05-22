package com.amazonaws.mobile.auth.userpools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.amazonaws.mobile.auth.core.internal.util.ViewHelper;
import com.amazonaws.mobile.auth.core.signin.SignInProvider;
import com.amazonaws.mobile.auth.core.signin.SignInProviderResultHandler;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ForgotPasswordContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.NewPasswordContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.VerificationHandler;
import com.amazonaws.services.cognitoidentityprovider.model.InvalidParameterException;
import com.amazonaws.services.cognitoidentityprovider.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidentityprovider.model.UserNotConfirmedException;
import com.amazonaws.services.cognitoidentityprovider.model.UserNotFoundException;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes.dex */
public class CognitoUserPoolsSignInProvider implements SignInProvider {
    private static final String LOG_TAG = CognitoUserPoolsSignInProvider.class.getSimpleName();
    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int REQUEST_CODE_START = 10608;
    private static final String USERPOOLS_EXCEPTION_PREFIX = "(Service";
    private static int backgroundColor;
    private static String fontFamily;
    private static boolean isBackgroundColorFullScreenEnabled;
    private Activity activity;
    private AWSConfiguration awsConfiguration;
    private String cognitoLoginKey;
    private CognitoUserPool cognitoUserPool;
    private CognitoUserSession cognitoUserSession;
    private Context context;
    private ForgotPasswordContinuation forgotPasswordContinuation;
    private NewPasswordContinuation mForceChangePasswordContinuation;
    private MultiFactorAuthenticationContinuation multiFactorAuthenticationContinuation;
    private String password;
    private SignInProviderResultHandler resultsHandler;
    private String signUpConfirmationDestination;
    private String username;
    private String verificationCode;
    private ForgotPasswordHandler forgotPasswordHandler = new ForgotPasswordHandler() { // from class: com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider.1
        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler
        public void onSuccess() {
            Log.d(CognitoUserPoolsSignInProvider.LOG_TAG, "Password change succeeded.");
            ViewHelper.showDialog(CognitoUserPoolsSignInProvider.this.activity, CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.title_activity_forgot_password), CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.password_change_success));
            CognitoUserPoolsSignInProvider.this.cognitoUserPool.getUser(CognitoUserPoolsSignInProvider.this.username).getSessionInBackground(CognitoUserPoolsSignInProvider.this.authenticationHandler);
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler
        public void getResetCode(ForgotPasswordContinuation forgotPasswordContinuation) {
            CognitoUserPoolsSignInProvider.this.forgotPasswordContinuation = forgotPasswordContinuation;
            CognitoUserPoolsSignInProvider.this.activity.startActivityForResult(new Intent(CognitoUserPoolsSignInProvider.this.context, (Class<?>) ForgotPasswordActivity.class), RequestCodes.FORGOT_PASSWORD_REQUEST_CODE.value);
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.ForgotPasswordHandler
        public void onFailure(Exception exc) {
            String errorMessageFromException;
            Log.e(CognitoUserPoolsSignInProvider.LOG_TAG, "Password change failed.", exc);
            if (exc instanceof InvalidParameterException) {
                errorMessageFromException = CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.password_change_no_verification_failed);
            } else {
                errorMessageFromException = CognitoUserPoolsSignInProvider.getErrorMessageFromException(exc);
            }
            ViewHelper.showDialog(CognitoUserPoolsSignInProvider.this.activity, CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.title_activity_forgot_password), CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.password_change_failed) + " " + errorMessageFromException);
        }
    };
    private GenericHandler signUpConfirmationHandler = new GenericHandler() { // from class: com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider.2
        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
        public void onSuccess() {
            Log.i(CognitoUserPoolsSignInProvider.LOG_TAG, "Confirmed.");
            ViewHelper.showDialog(CognitoUserPoolsSignInProvider.this.activity, CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.title_activity_sign_up_confirm), CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.sign_up_confirm_success));
            CognitoUserPoolsSignInProvider.this.cognitoUserPool.getUser(CognitoUserPoolsSignInProvider.this.username).getSessionInBackground(CognitoUserPoolsSignInProvider.this.authenticationHandler);
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
        public void onFailure(Exception exc) {
            Log.e(CognitoUserPoolsSignInProvider.LOG_TAG, "Failed to confirm user.", exc);
            ViewHelper.showDialog(CognitoUserPoolsSignInProvider.this.activity, CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.title_activity_sign_up_confirm), CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.sign_up_confirm_failed) + " " + CognitoUserPoolsSignInProvider.getErrorMessageFromException(exc));
        }
    };
    private AuthenticationHandler authenticationHandler = new AuthenticationHandler() { // from class: com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider.4
        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
        public void onSuccess(CognitoUserSession cognitoUserSession, CognitoDevice cognitoDevice) {
            Log.i(CognitoUserPoolsSignInProvider.LOG_TAG, "Logged in. " + cognitoUserSession.getIdToken());
            CognitoUserPoolsSignInProvider.this.cognitoUserSession = cognitoUserSession;
            if (CognitoUserPoolsSignInProvider.this.resultsHandler != null) {
                CognitoUserPoolsSignInProvider.this.resultsHandler.onSuccess(CognitoUserPoolsSignInProvider.this);
            }
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
        public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String str) {
            if (CognitoUserPoolsSignInProvider.this.username == null || CognitoUserPoolsSignInProvider.this.password == null) {
                return;
            }
            authenticationContinuation.setAuthenticationDetails(new AuthenticationDetails(CognitoUserPoolsSignInProvider.this.username, CognitoUserPoolsSignInProvider.this.password, (Map<String, String>) null));
            authenticationContinuation.continueTask();
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
        public void getMFACode(MultiFactorAuthenticationContinuation multiFactorAuthenticationContinuation) {
            CognitoUserPoolsSignInProvider.this.multiFactorAuthenticationContinuation = multiFactorAuthenticationContinuation;
            CognitoUserPoolsSignInProvider.this.activity.startActivityForResult(new Intent(CognitoUserPoolsSignInProvider.this.context, (Class<?>) MFAActivity.class), RequestCodes.MFA_REQUEST_CODE.value);
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
        public void authenticationChallenge(ChallengeContinuation challengeContinuation) {
            if (challengeContinuation instanceof NewPasswordContinuation) {
                CognitoUserPoolsSignInProvider.this.mForceChangePasswordContinuation = (NewPasswordContinuation) challengeContinuation;
                CognitoUserPoolsSignInProvider.this.activity.startActivityForResult(new Intent(CognitoUserPoolsSignInProvider.this.context, (Class<?>) ForceChangePasswordActivity.class), RequestCodes.FORCE_CHANGE_PASSWORD_REQUEST_CODE.value);
                return;
            }
            throw new UnsupportedOperationException("Not supported in this sample.");
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
        public void onFailure(Exception exc) {
            String string;
            Log.e(CognitoUserPoolsSignInProvider.LOG_TAG, "Failed to login.", exc);
            if (exc instanceof UserNotConfirmedException) {
                CognitoUserPoolsSignInProvider.this.resendConfirmationCode();
                return;
            }
            if (exc instanceof UserNotFoundException) {
                string = CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.user_does_not_exist);
            } else {
                string = exc instanceof NotAuthorizedException ? CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.incorrect_username_or_password) : CognitoUserPoolsSignInProvider.getErrorMessageFromException(exc);
            }
            if (CognitoUserPoolsSignInProvider.this.resultsHandler != null) {
                ViewHelper.showDialog(CognitoUserPoolsSignInProvider.this.activity, CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.title_activity_sign_in), CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.login_failed) + " " + string);
                CognitoUserPoolsSignInProvider.this.resultsHandler.onError(CognitoUserPoolsSignInProvider.this, exc);
            }
        }
    };

    /* loaded from: classes.dex */
    public static final class AttributeKeys {
        public static final String BACKGROUND_COLOR = "signInBackgroundColor";
        public static final String CONFIRMATION_DESTINATION = "destination";
        public static final String EMAIL_ADDRESS = "email";
        public static final String FONT_FAMILY = "fontFamily";
        public static final String FULL_SCREEN_BACKGROUND_COLOR = "fullScreenBackgroundColor";
        public static final String GIVEN_NAME = "given_name";
        public static final String IS_SIGN_UP_CONFIRMED = "isSignUpConfirmed";
        public static final String PASSWORD = "password";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String USERNAME = "username";
        public static final String VERIFICATION_CODE = "verification_code";
    }

    @Override // com.amazonaws.mobile.auth.core.IdentityProvider
    public String getDisplayName() {
        return "Amazon Cognito Your User Pools";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum RequestCodes {
        FORGOT_PASSWORD_REQUEST_CODE(10650),
        SIGN_UP_REQUEST_CODE(10651),
        MFA_REQUEST_CODE(10652),
        VERIFICATION_REQUEST_CODE(10653),
        FORCE_CHANGE_PASSWORD_REQUEST_CODE(10654);

        public final int value;

        RequestCodes(int i) {
            this.value = i;
        }

        static RequestCodes valueOf(int i) {
            Log.e(CognitoUserPoolsSignInProvider.LOG_TAG, "valueOf: " + i, new RuntimeException(""));
            for (RequestCodes requestCodes : values()) {
                Log.e(CognitoUserPoolsSignInProvider.LOG_TAG, "valueOf: compare " + requestCodes.value);
                if (i == requestCodes.value) {
                    return requestCodes;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVerificationActivity() {
        Intent intent = new Intent(this.context, (Class<?>) SignUpConfirmActivity.class);
        intent.putExtra(AttributeKeys.USERNAME, this.username);
        intent.putExtra("destination", this.signUpConfirmationDestination);
        this.activity.startActivityForResult(intent, RequestCodes.VERIFICATION_REQUEST_CODE.value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resendConfirmationCode() {
        this.cognitoUserPool.getUser(this.username).resendConfirmationCodeInBackground(new VerificationHandler() { // from class: com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider.3
            @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.VerificationHandler
            public void onSuccess(CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                CognitoUserPoolsSignInProvider.this.startVerificationActivity();
            }

            @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.VerificationHandler
            public void onFailure(Exception exc) {
                if (CognitoUserPoolsSignInProvider.this.resultsHandler != null) {
                    ViewHelper.showDialog(CognitoUserPoolsSignInProvider.this.activity, CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.title_activity_sign_in), CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.login_failed) + "\nUser was not verified and resending confirmation code failed.\n" + CognitoUserPoolsSignInProvider.getErrorMessageFromException(exc));
                    CognitoUserPoolsSignInProvider.this.resultsHandler.onError(CognitoUserPoolsSignInProvider.this, exc);
                }
            }
        });
    }

    @Override // com.amazonaws.mobile.auth.core.IdentityProvider
    public void initialize(Context context, AWSConfiguration aWSConfiguration) {
        this.context = context;
        this.awsConfiguration = aWSConfiguration;
        Log.d(LOG_TAG, "Initializing Cognito User Pools");
        this.cognitoUserPool = new CognitoUserPool(context, aWSConfiguration);
        this.cognitoLoginKey = "cognito-idp." + getValueFromConfig("Region") + ".amazonaws.com/" + this.cognitoUserPool.getUserPoolId();
    }

    protected String getValueFromConfig(String str) {
        try {
            return this.awsConfiguration.optJsonObject("CognitoUserPool").getString(str);
        } catch (JSONException e) {
            throw new IllegalArgumentException("Cannot find the CognitoUserPool " + str + " from the AWSConfiguration file.", e);
        }
    }

    @Override // com.amazonaws.mobile.auth.core.signin.SignInProvider
    public boolean isRequestCodeOurs(int i) {
        return RequestCodes.valueOf(i) != null;
    }

    @Override // com.amazonaws.mobile.auth.core.signin.SignInProvider
    public void handleActivityResult(int i, int i2, Intent intent) {
        RequestCodes valueOf = RequestCodes.valueOf(i);
        if (-1 != i2 || valueOf == null) {
            return;
        }
        int i3 = C11878.f1141xf6453f4d[valueOf.ordinal()];
        if (i3 == 1) {
            this.password = intent.getStringExtra(AttributeKeys.PASSWORD);
            this.verificationCode = intent.getStringExtra(AttributeKeys.VERIFICATION_CODE);
            if (this.password.length() < 6) {
                Activity activity = this.activity;
                ViewHelper.showDialog(activity, activity.getString(C1190R.string.title_activity_forgot_password), this.activity.getString(C1190R.string.password_change_failed) + " " + this.activity.getString(C1190R.string.password_length_validation_failed));
                return;
            }
            Log.d(LOG_TAG, "verificationCode = " + this.verificationCode);
            this.forgotPasswordContinuation.setPassword(this.password);
            this.forgotPasswordContinuation.setVerificationCode(this.verificationCode);
            this.forgotPasswordContinuation.continueTask();
            return;
        }
        if (i3 == 2) {
            this.username = intent.getStringExtra(AttributeKeys.USERNAME);
            this.password = intent.getStringExtra(AttributeKeys.PASSWORD);
            boolean booleanExtra = intent.getBooleanExtra(AttributeKeys.IS_SIGN_UP_CONFIRMED, true);
            this.signUpConfirmationDestination = intent.getStringExtra("destination");
            Log.d(LOG_TAG, "sign up result username = " + this.username);
            if (booleanExtra) {
                Log.d(LOG_TAG, "Signed up. User ID = " + this.username);
                Activity activity2 = this.activity;
                ViewHelper.showDialog(activity2, activity2.getString(C1190R.string.title_activity_sign_up), this.activity.getString(C1190R.string.sign_up_success) + " " + this.username);
                signInUser();
                return;
            }
            Log.w(LOG_TAG, "Additional confirmation for sign up.");
            startVerificationActivity();
            return;
        }
        if (i3 == 3) {
            this.verificationCode = intent.getStringExtra(AttributeKeys.VERIFICATION_CODE);
            if (this.verificationCode.length() < 1) {
                Activity activity3 = this.activity;
                ViewHelper.showDialog(activity3, activity3.getString(C1190R.string.title_activity_mfa), this.activity.getString(C1190R.string.mfa_failed) + " " + this.activity.getString(C1190R.string.mfa_code_empty));
                return;
            }
            Log.d(LOG_TAG, "verificationCode = " + this.verificationCode);
            this.multiFactorAuthenticationContinuation.setMfaCode(this.verificationCode);
            this.multiFactorAuthenticationContinuation.continueTask();
            return;
        }
        if (i3 != 4) {
            if (i3 == 5) {
                Log.d(LOG_TAG, "handleActivityResult: FORCE_CHANGE_PASSWORD_REQUEST_CODE");
                this.password = intent.getStringExtra(AttributeKeys.PASSWORD);
                this.mForceChangePasswordContinuation.setPassword(this.password);
                this.mForceChangePasswordContinuation.continueTask();
                return;
            }
            Log.e(LOG_TAG, "Unknown Request Code sent.");
            return;
        }
        Log.d(LOG_TAG, "handleActivityResult: VERIFICATION_REQUEST_CODE");
        this.username = intent.getStringExtra(AttributeKeys.USERNAME);
        this.verificationCode = intent.getStringExtra(AttributeKeys.VERIFICATION_CODE);
        if (this.username.length() < 1) {
            Activity activity4 = this.activity;
            ViewHelper.showDialog(activity4, activity4.getString(C1190R.string.title_activity_sign_up_confirm), this.activity.getString(C1190R.string.sign_up_confirm_title) + " " + this.activity.getString(C1190R.string.sign_up_username_missing));
            return;
        }
        if (this.verificationCode.length() < 1) {
            Activity activity5 = this.activity;
            ViewHelper.showDialog(activity5, activity5.getString(C1190R.string.title_activity_sign_up_confirm), this.activity.getString(C1190R.string.sign_up_confirm_title) + " " + this.activity.getString(C1190R.string.sign_up_confirm_code_missing));
            return;
        }
        Log.d(LOG_TAG, "username = " + this.username);
        Log.d(LOG_TAG, "verificationCode = " + this.verificationCode);
        this.cognitoUserPool.getUser(this.username).confirmSignUpInBackground(this.verificationCode, true, this.signUpConfirmationHandler);
    }

    /* renamed from: com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider$8 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C11878 {

        /* renamed from: $SwitchMap$com$amazonaws$mobile$auth$userpools$CognitoUserPoolsSignInProvider$RequestCodes */
        static final /* synthetic */ int[] f1141xf6453f4d = new int[RequestCodes.values().length];

        static {
            try {
                f1141xf6453f4d[RequestCodes.FORGOT_PASSWORD_REQUEST_CODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1141xf6453f4d[RequestCodes.SIGN_UP_REQUEST_CODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1141xf6453f4d[RequestCodes.MFA_REQUEST_CODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1141xf6453f4d[RequestCodes.VERIFICATION_REQUEST_CODE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1141xf6453f4d[RequestCodes.FORCE_CHANGE_PASSWORD_REQUEST_CODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    protected void signInUser() {
        this.cognitoUserPool.getUser(this.username).getSessionInBackground(this.authenticationHandler);
    }

    @Override // com.amazonaws.mobile.auth.core.signin.SignInProvider
    public View.OnClickListener initializeSignInButton(Activity activity, View view, SignInProviderResultHandler signInProviderResultHandler) {
        this.activity = activity;
        this.resultsHandler = signInProviderResultHandler;
        final UserPoolSignInView userPoolSignInView = (UserPoolSignInView) this.activity.findViewById(C1190R.id.user_pool_sign_in_view_id);
        backgroundColor = userPoolSignInView.getBackgroundColor();
        fontFamily = userPoolSignInView.getFontFamily();
        isBackgroundColorFullScreenEnabled = userPoolSignInView.isBackgroundColorFullScreen();
        userPoolSignInView.getSignUpTextView().setOnClickListener(new View.OnClickListener() { // from class: com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SignUpActivity.startActivity(CognitoUserPoolsSignInProvider.this.activity, RequestCodes.SIGN_UP_REQUEST_CODE.value);
            }
        });
        userPoolSignInView.getForgotPasswordTextView().setOnClickListener(new View.OnClickListener() { // from class: com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                CognitoUserPoolsSignInProvider.this.username = userPoolSignInView.getEnteredUserName();
                if (CognitoUserPoolsSignInProvider.this.username.length() < 1) {
                    Log.w(CognitoUserPoolsSignInProvider.LOG_TAG, "Missing username.");
                    ViewHelper.showDialog(CognitoUserPoolsSignInProvider.this.activity, CognitoUserPoolsSignInProvider.this.activity.getString(C1190R.string.title_activity_sign_in), "Missing username.");
                } else {
                    CognitoUserPoolsSignInProvider.this.cognitoUserPool.getUser(CognitoUserPoolsSignInProvider.this.username).forgotPasswordInBackground(CognitoUserPoolsSignInProvider.this.forgotPasswordHandler);
                }
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                CognitoUserPoolsSignInProvider.this.username = userPoolSignInView.getEnteredUserName();
                CognitoUserPoolsSignInProvider.this.password = userPoolSignInView.getEnteredPassword();
                CognitoUserPoolsSignInProvider.this.signInUser();
            }
        };
        view.setOnClickListener(onClickListener);
        return onClickListener;
    }

    @Override // com.amazonaws.mobile.auth.core.IdentityProvider
    public String getCognitoLoginKey() {
        return this.cognitoLoginKey;
    }

    /* loaded from: classes.dex */
    private static class RefreshSessionAuthenticationHandler implements AuthenticationHandler {
        private CognitoUserSession userSession;

        private RefreshSessionAuthenticationHandler() {
            this.userSession = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CognitoUserSession getUserSession() {
            return this.userSession;
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
        public void onSuccess(CognitoUserSession cognitoUserSession, CognitoDevice cognitoDevice) {
            this.userSession = cognitoUserSession;
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
        public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String str) {
            Log.d(CognitoUserPoolsSignInProvider.LOG_TAG, "Can't refresh the session silently, due to authentication details needed.");
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
        public void getMFACode(MultiFactorAuthenticationContinuation multiFactorAuthenticationContinuation) {
            Log.wtf(CognitoUserPoolsSignInProvider.LOG_TAG, "Refresh flow can not trigger request for MFA code.");
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
        public void authenticationChallenge(ChallengeContinuation challengeContinuation) {
            Log.wtf(CognitoUserPoolsSignInProvider.LOG_TAG, "Refresh flow can not trigger request for authentication challenge.");
        }

        @Override // com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
        public void onFailure(Exception exc) {
            Log.e(CognitoUserPoolsSignInProvider.LOG_TAG, "Can't refresh session.", exc);
        }
    }

    @Override // com.amazonaws.mobile.auth.core.IdentityProvider
    public boolean refreshUserSignInState() {
        CognitoUserSession cognitoUserSession = this.cognitoUserSession;
        if (cognitoUserSession != null && cognitoUserSession.isValid()) {
            return true;
        }
        RefreshSessionAuthenticationHandler refreshSessionAuthenticationHandler = new RefreshSessionAuthenticationHandler();
        this.cognitoUserPool.getCurrentUser().getSession(refreshSessionAuthenticationHandler);
        if (refreshSessionAuthenticationHandler.getUserSession() != null) {
            this.cognitoUserSession = refreshSessionAuthenticationHandler.getUserSession();
            Log.i(LOG_TAG, "refreshUserSignInState: Signed in with Cognito.");
            return true;
        }
        Log.i(LOG_TAG, "refreshUserSignInState: Not signed in with Cognito.");
        this.cognitoUserSession = null;
        return false;
    }

    @Override // com.amazonaws.mobile.auth.core.IdentityProvider
    public String getToken() {
        CognitoUserSession cognitoUserSession = this.cognitoUserSession;
        if (cognitoUserSession == null) {
            return null;
        }
        return cognitoUserSession.getIdToken().getJWTToken();
    }

    @Override // com.amazonaws.mobile.auth.core.IdentityProvider
    public String refreshToken() {
        CognitoUserSession cognitoUserSession = this.cognitoUserSession;
        if (cognitoUserSession != null && !cognitoUserSession.isValid()) {
            RefreshSessionAuthenticationHandler refreshSessionAuthenticationHandler = new RefreshSessionAuthenticationHandler();
            this.cognitoUserPool.getCurrentUser().getSession(refreshSessionAuthenticationHandler);
            if (refreshSessionAuthenticationHandler.getUserSession() != null) {
                this.cognitoUserSession = refreshSessionAuthenticationHandler.getUserSession();
            } else {
                Log.e(LOG_TAG, "Could not refresh the Cognito User Pool Token.");
            }
        }
        return getToken();
    }

    @Override // com.amazonaws.mobile.auth.core.IdentityProvider
    public void signOut() {
        CognitoUserPool cognitoUserPool = this.cognitoUserPool;
        if (cognitoUserPool == null || cognitoUserPool.getCurrentUser() == null) {
            return;
        }
        this.cognitoUserPool.getCurrentUser().signOut();
        this.cognitoUserSession = null;
        this.username = null;
        this.password = null;
    }

    public CognitoUserPool getCognitoUserPool() {
        return this.cognitoUserPool;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getErrorMessageFromException(Exception exc) {
        String localizedMessage = exc.getLocalizedMessage();
        if (localizedMessage == null) {
            return exc.getMessage();
        }
        int indexOf = localizedMessage.indexOf(USERPOOLS_EXCEPTION_PREFIX);
        return indexOf == -1 ? localizedMessage : localizedMessage.substring(0, indexOf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isBackgroundColorFullScreen() {
        return isBackgroundColorFullScreenEnabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getBackgroundColor() {
        return backgroundColor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getFontFamily() {
        return fontFamily;
    }
}
