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
public class ClientGrantsProvider extends WebIdentityClientGrantsProvider {
    public ClientGrantsProvider(@Nonnull Supplier<Jwt> supplier, @Nonnull String str, @Nullable Integer num, @Nullable String str2, @Nullable OkHttpClient okHttpClient) {
        super(supplier, str, num, str2, null, null, okHttpClient);
    }

    @Override // io.minio.credentials.WebIdentityClientGrantsProvider
    protected HttpUrl.Builder newUrlBuilder(Jwt jwt) {
        return newUrlBuilder(this.stsEndpoint, "AssumeRoleWithClientGrants", getDurationSeconds(jwt.expiry()), this.policy, null, null).addQueryParameter("Token", jwt.token());
    }

    @Override // io.minio.credentials.AssumeRoleBaseProvider
    protected Class<? extends AssumeRoleBaseProvider.Response> getResponseClass() {
        return ClientGrantsResponse.class;
    }

    @Namespace(reference = "https://sts.amazonaws.com/doc/2011-06-15/")
    @Root(name = "AssumeRoleWithClientGrantsResponse", strict = false)
    /* loaded from: classes7.dex */
    public static class ClientGrantsResponse implements AssumeRoleBaseProvider.Response {

        @Element(name = "Credentials")
        @Path("AssumeRoleWithClientGrantsResult")
        private Credentials credentials;

        @Override // io.minio.credentials.AssumeRoleBaseProvider.Response
        public Credentials getCredentials() {
            return this.credentials;
        }
    }
}
