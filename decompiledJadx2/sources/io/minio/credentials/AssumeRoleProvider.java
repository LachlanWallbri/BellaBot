package io.minio.credentials;

import com.amazonaws.services.p048s3.Headers;
import io.minio.Digest;
import io.minio.Signer;
import io.minio.Time;
import io.minio.credentials.AssumeRoleBaseProvider;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.ProviderException;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.HttpHost;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/* loaded from: classes7.dex */
public class AssumeRoleProvider extends AssumeRoleBaseProvider {
    private final String accessKey;
    private final String contentSha256;
    private final String region;
    private final Request request;
    private final String secretKey;

    public AssumeRoleProvider(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nullable Integer num, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable OkHttpClient okHttpClient) throws NoSuchAlgorithmException {
        super(okHttpClient);
        HttpUrl httpUrl = (HttpUrl) Objects.requireNonNull(HttpUrl.parse((String) Objects.requireNonNull(str, "STS endpoint cannot be empty")), "Invalid STS endpoint");
        String str9 = (String) Objects.requireNonNull(str2, "Access key must not be null");
        if (str9.isEmpty()) {
            throw new IllegalArgumentException("Access key must not be empty");
        }
        this.accessKey = str9;
        this.secretKey = (String) Objects.requireNonNull(str3, "Secret key must not be null");
        this.region = str5 != null ? str5 : "";
        if (str8 != null && (str8.length() < 2 || str8.length() > 1224)) {
            throw new IllegalArgumentException("Length of ExternalId must be in between 2 and 1224");
        }
        String str10 = httpUrl.host() + ":" + httpUrl.port();
        if ((httpUrl.scheme().equals(HttpHost.DEFAULT_SCHEME_NAME) && httpUrl.port() == 80) || (httpUrl.scheme().equals("https") && httpUrl.port() == 443)) {
            str10 = httpUrl.host();
        }
        String str11 = str10;
        HttpUrl.Builder newUrlBuilder = newUrlBuilder(httpUrl, "AssumeRole", getValidDurationSeconds(num), str4, str6, str7);
        if (str8 != null) {
            newUrlBuilder.addQueryParameter("ExternalId", str8);
        }
        String encodedQuery = newUrlBuilder.build().encodedQuery();
        this.contentSha256 = Digest.sha256Hash(encodedQuery);
        this.request = new Request.Builder().url(httpUrl).header("Host", str11).method("POST", RequestBody.create(encodedQuery, MediaType.parse("application/x-www-form-urlencoded"))).build();
    }

    @Override // io.minio.credentials.AssumeRoleBaseProvider
    protected Request getRequest() {
        try {
            return Signer.signV4Sts(this.request.newBuilder().header(Headers.S3_ALTERNATE_DATE, ZonedDateTime.now().format(Time.AMZ_DATE_FORMAT)).build(), this.region, this.accessKey, this.secretKey, this.contentSha256);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new ProviderException("Signature calculation failed", e);
        }
    }

    @Override // io.minio.credentials.AssumeRoleBaseProvider
    protected Class<? extends AssumeRoleBaseProvider.Response> getResponseClass() {
        return AssumeRoleResponse.class;
    }

    @Namespace(reference = "https://sts.amazonaws.com/doc/2011-06-15/")
    @Root(name = "AssumeRoleResponse", strict = false)
    /* loaded from: classes7.dex */
    public static class AssumeRoleResponse implements AssumeRoleBaseProvider.Response {

        @Element(name = "Credentials")
        @Path("AssumeRoleResult")
        private Credentials credentials;

        @Override // io.minio.credentials.AssumeRoleBaseProvider.Response
        public Credentials getCredentials() {
            return this.credentials;
        }
    }
}
