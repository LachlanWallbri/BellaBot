package io.minio.credentials;

import io.minio.credentials.AssumeRoleBaseProvider;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/* loaded from: classes7.dex */
public class WebIdentityProvider extends WebIdentityClientGrantsProvider {
    public WebIdentityProvider(@Nonnull Supplier<Jwt> supplier, @Nonnull String str, @Nullable Integer num, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable OkHttpClient okHttpClient) {
        super(supplier, str, num, str2, str3, str4, okHttpClient);
    }

    @Override // io.minio.credentials.WebIdentityClientGrantsProvider
    protected HttpUrl.Builder newUrlBuilder(Jwt jwt) {
        String str;
        HttpUrl httpUrl = this.stsEndpoint;
        int durationSeconds = getDurationSeconds(jwt.expiry());
        String str2 = this.policy;
        String str3 = this.roleArn;
        if (this.roleArn != null && this.roleSessionName == null) {
            str = String.valueOf(System.currentTimeMillis());
        } else {
            str = this.roleSessionName;
        }
        return newUrlBuilder(httpUrl, "AssumeRoleWithWebIdentity", durationSeconds, str2, str3, str).addQueryParameter("WebIdentityToken", jwt.token());
    }

    @Override // io.minio.credentials.AssumeRoleBaseProvider
    protected Class<? extends AssumeRoleBaseProvider.Response> getResponseClass() {
        return WebIdentityResponse.class;
    }

    @Namespace(reference = "https://sts.amazonaws.com/doc/2011-06-15/")
    @Root(name = "AssumeRoleWithWebIdentityResponse", strict = false)
    /* loaded from: classes7.dex */
    public static class WebIdentityResponse implements AssumeRoleBaseProvider.Response {

        @Element(name = "Credentials")
        @Path("AssumeRoleWithWebIdentityResult")
        private Credentials credentials;

        @Override // io.minio.credentials.AssumeRoleBaseProvider.Response
        public Credentials getCredentials() {
            return this.credentials;
        }
    }
}
