package io.minio.credentials;

import io.minio.credentials.AssumeRoleBaseProvider;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
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
public class CertificateIdentityProvider extends AssumeRoleBaseProvider {
    private static final RequestBody EMPTY_BODY = RequestBody.create(new byte[0], MediaType.parse("application/octet-stream"));
    private final Request request;

    public CertificateIdentityProvider(@Nonnull String str, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable X509TrustManager x509TrustManager, @Nullable Integer num, @Nullable OkHttpClient okHttpClient) throws GeneralSecurityException, IOException {
        super(okHttpClient, sSLSocketFactory, x509TrustManager);
        HttpUrl httpUrl = (HttpUrl) Objects.requireNonNull(HttpUrl.parse((String) Objects.requireNonNull(str, "STS endpoint cannot be empty")), "Invalid STS endpoint");
        if (!httpUrl.isHttps()) {
            throw new IllegalArgumentException("STS endpoint scheme must be HTTPS");
        }
        this.request = new Request.Builder().url(newUrlBuilder(httpUrl, "AssumeRoleWithCertificate", getValidDurationSeconds(num), null, null, null).build()).method("POST", EMPTY_BODY).build();
    }

    @Override // io.minio.credentials.AssumeRoleBaseProvider
    protected Request getRequest() {
        return this.request;
    }

    @Override // io.minio.credentials.AssumeRoleBaseProvider
    protected Class<? extends AssumeRoleBaseProvider.Response> getResponseClass() {
        return CertificateIdentityResponse.class;
    }

    @Namespace(reference = "https://sts.amazonaws.com/doc/2011-06-15/")
    @Root(name = "AssumeRoleWithCertificateResponse", strict = false)
    /* loaded from: classes7.dex */
    public static class CertificateIdentityResponse implements AssumeRoleBaseProvider.Response {

        @Element(name = "Credentials")
        @Path("AssumeRoleWithCertificateResult")
        private Credentials credentials;

        @Override // io.minio.credentials.AssumeRoleBaseProvider.Response
        public Credentials getCredentials() {
            return this.credentials;
        }
    }
}
