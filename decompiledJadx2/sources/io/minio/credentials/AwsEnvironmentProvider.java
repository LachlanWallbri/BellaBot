package io.minio.credentials;

import com.amazonaws.SDKGlobalConfiguration;

/* loaded from: classes7.dex */
public class AwsEnvironmentProvider extends EnvironmentProvider {
    private final String getAccessKey() {
        String property = getProperty(SDKGlobalConfiguration.ACCESS_KEY_ENV_VAR);
        return property != null ? property : getProperty(SDKGlobalConfiguration.ALTERNATE_ACCESS_KEY_ENV_VAR);
    }

    private final String getSecretKey() {
        String property = getProperty(SDKGlobalConfiguration.ALTERNATE_SECRET_KEY_ENV_VAR);
        return property != null ? property : getProperty(SDKGlobalConfiguration.SECRET_KEY_ENV_VAR);
    }

    @Override // io.minio.credentials.Provider
    public Credentials fetch() {
        return new Credentials(getAccessKey(), getSecretKey(), getProperty(SDKGlobalConfiguration.AWS_SESSION_TOKEN_ENV_VAR), null);
    }
}
