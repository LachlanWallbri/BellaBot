package com.google.auth.oauth2;

import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.google.auth.ServiceAccountSigner;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class AppEngineCredentials extends GoogleCredentials implements ServiceAccountSigner {
    static final String APP_IDENTITY_SERVICE_CLASS = "com.google.appengine.api.appidentity.AppIdentityService";
    static final String APP_IDENTITY_SERVICE_FACTORY_CLASS = "com.google.appengine.api.appidentity.AppIdentityServiceFactory";
    private static final String GET_ACCESS_TOKEN_METHOD = "getAccessToken";
    static final String GET_ACCESS_TOKEN_RESULT_CLASS = "com.google.appengine.api.appidentity.AppIdentityService$GetAccessTokenResult";
    private static final String GET_ACCESS_TOKEN_RESULT_METHOD = "getAccessToken";
    private static final String GET_APP_IDENTITY_SERVICE_METHOD = "getAppIdentityService";
    private static final String GET_EXPIRATION_TIME_METHOD = "getExpirationTime";
    private static final String GET_SERVICE_ACCOUNT_NAME_METHOD = "getServiceAccountName";
    private static final String GET_SIGNATURE_METHOD = "getSignature";
    static final String SIGNING_RESULT_CLASS = "com.google.appengine.api.appidentity.AppIdentityService$SigningResult";
    private static final String SIGN_FOR_APP_METHOD = "signForApp";
    private static final long serialVersionUID = -493219027336622194L;
    private transient String account;
    private transient Object appIdentityService;
    private transient Method getAccessToken;
    private transient Method getAccessTokenResult;
    private transient Method getExpirationTime;
    private transient Method getSignature;
    private final Collection<String> scopes;
    private final boolean scopesRequired;
    private transient Method signForApp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppEngineCredentials(Collection<String> collection, Collection<String> collection2) throws IOException {
        if (collection == null || collection.isEmpty()) {
            this.scopes = collection2 == null ? ImmutableList.m634of() : ImmutableList.copyOf((Collection) collection2);
        } else {
            this.scopes = ImmutableList.copyOf((Collection) collection);
        }
        this.scopesRequired = this.scopes.isEmpty();
        init();
    }

    AppEngineCredentials(Collection<String> collection, Collection<String> collection2, AppEngineCredentials appEngineCredentials) {
        this.appIdentityService = appEngineCredentials.appIdentityService;
        this.getAccessToken = appEngineCredentials.getAccessToken;
        this.getAccessTokenResult = appEngineCredentials.getAccessTokenResult;
        this.getExpirationTime = appEngineCredentials.getExpirationTime;
        if (collection == null || collection.isEmpty()) {
            this.scopes = collection2 == null ? ImmutableSet.m676of() : ImmutableList.copyOf((Collection) collection2);
        } else {
            this.scopes = ImmutableList.copyOf((Collection) collection);
        }
        this.scopesRequired = this.scopes.isEmpty();
    }

    private void init() throws IOException {
        try {
            this.appIdentityService = forName(APP_IDENTITY_SERVICE_FACTORY_CLASS).getMethod(GET_APP_IDENTITY_SERVICE_METHOD, new Class[0]).invoke(null, new Object[0]);
            Class<?> forName = forName(APP_IDENTITY_SERVICE_CLASS);
            Class<?> forName2 = forName(GET_ACCESS_TOKEN_RESULT_CLASS);
            this.getAccessTokenResult = forName.getMethod("getAccessToken", Iterable.class);
            this.getAccessToken = forName2.getMethod("getAccessToken", new Class[0]);
            this.getExpirationTime = forName2.getMethod(GET_EXPIRATION_TIME_METHOD, new Class[0]);
            this.account = (String) forName.getMethod(GET_SERVICE_ACCOUNT_NAME_METHOD, new Class[0]).invoke(this.appIdentityService, new Object[0]);
            this.signForApp = forName.getMethod(SIGN_FOR_APP_METHOD, byte[].class);
            this.getSignature = forName(SIGNING_RESULT_CLASS).getMethod(GET_SIGNATURE_METHOD, new Class[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IOException("Application Default Credentials failed to create the Google App Engine service account credentials. Check that the App Engine SDK is deployed.", e);
        }
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public AccessToken refreshAccessToken() throws IOException {
        if (createScopedRequired()) {
            throw new IOException("AppEngineCredentials requires createScoped call before use.");
        }
        try {
            Object invoke = this.getAccessTokenResult.invoke(this.appIdentityService, this.scopes);
            return new AccessToken((String) this.getAccessToken.invoke(invoke, new Object[0]), (Date) this.getExpirationTime.invoke(invoke, new Object[0]));
        } catch (Exception e) {
            throw new IOException("Could not get the access token.", e);
        }
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public boolean createScopedRequired() {
        return this.scopesRequired;
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public GoogleCredentials createScoped(Collection<String> collection) {
        return new AppEngineCredentials(collection, null, this);
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public GoogleCredentials createScoped(Collection<String> collection, Collection<String> collection2) {
        return new AppEngineCredentials(collection, collection2, this);
    }

    @Override // com.google.auth.ServiceAccountSigner
    public String getAccount() {
        return this.account;
    }

    @Override // com.google.auth.ServiceAccountSigner
    public byte[] sign(byte[] bArr) {
        try {
            return (byte[]) this.getSignature.invoke(this.signForApp.invoke(this.appIdentityService, bArr), new Object[0]);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ServiceAccountSigner.SigningException("Failed to sign the provided bytes", e);
        }
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public int hashCode() {
        return Objects.hash(this.scopes, Boolean.valueOf(this.scopesRequired));
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public String toString() {
        return MoreObjects.toStringHelper(this).add(OAuth2Constants.SCOPES, this.scopes).add("scopesRequired", this.scopesRequired).toString();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public boolean equals(Object obj) {
        if (!(obj instanceof AppEngineCredentials)) {
            return false;
        }
        AppEngineCredentials appEngineCredentials = (AppEngineCredentials) obj;
        return this.scopesRequired == appEngineCredentials.scopesRequired && Objects.equals(this.scopes, appEngineCredentials.scopes);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init();
    }

    Class<?> forName(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }
}
