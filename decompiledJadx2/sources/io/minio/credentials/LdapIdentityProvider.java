package io.minio.credentials;

import io.minio.credentials.AssumeRoleBaseProvider;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/* loaded from: classes7.dex */
public class LdapIdentityProvider extends AssumeRoleBaseProvider {
    private static final RequestBody EMPTY_BODY = RequestBody.create(new byte[0], MediaType.parse("application/octet-stream"));
    private final Request request;

    public LdapIdentityProvider(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nullable Integer num, @Nullable String str4, @Nullable OkHttpClient okHttpClient) {
        super(okHttpClient);
        HttpUrl httpUrl = (HttpUrl) Objects.requireNonNull(HttpUrl.parse((String) Objects.requireNonNull(str, "STS endpoint cannot be empty")), "Invalid STS endpoint");
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("LDAP username must be provided");
        }
        Objects.requireNonNull(str3, "LDAP password must not be null");
        this.request = new Request.Builder().url(newUrlBuilder(httpUrl, "AssumeRoleWithLDAPIdentity", getValidDurationSeconds(num), str4, null, null).addQueryParameter("LDAPUsername", str2).addQueryParameter("LDAPPassword", str3).build()).method("POST", EMPTY_BODY).build();
    }

    public LdapIdentityProvider(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nullable OkHttpClient okHttpClient) {
        this(str, str2, str3, null, null, okHttpClient);
    }

    @Override // io.minio.credentials.AssumeRoleBaseProvider
    protected Request getRequest() {
        return this.request;
    }

    @Override // io.minio.credentials.AssumeRoleBaseProvider
    protected Class<? extends AssumeRoleBaseProvider.Response> getResponseClass() {
        return LdapIdentityResponse.class;
    }

    @Namespace(reference = "https://sts.amazonaws.com/doc/2011-06-15/")
    @Root(name = "AssumeRoleWithLDAPIdentityResponse", strict = false)
    /* loaded from: classes7.dex */
    public static class LdapIdentityResponse implements AssumeRoleBaseProvider.Response {

        @Element(name = "Credentials")
        @Path("AssumeRoleWithLDAPIdentityResult")
        private Credentials credentials;

        @Override // io.minio.credentials.AssumeRoleBaseProvider.Response
        public Credentials getCredentials() {
            return this.credentials;
        }
    }
}
