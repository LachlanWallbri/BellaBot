package io.minio.credentials;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.messages.ResponseDate;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.ProviderException;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;
import org.apache.http.HttpHost;

/* loaded from: classes7.dex */
public class IamAwsProvider extends EnvironmentProvider {
    private Credentials credentials;
    private final HttpUrl customEndpoint;
    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;

    public IamAwsProvider(@Nullable String str, @Nullable OkHttpClient okHttpClient) {
        this.customEndpoint = str != null ? (HttpUrl) Objects.requireNonNull(HttpUrl.parse(str), "Invalid custom endpoint") : null;
        this.httpClient = okHttpClient == null ? new OkHttpClient().newBuilder().protocols(Arrays.asList(Protocol.HTTP_1_1)).build() : okHttpClient;
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    private void checkLoopbackHost(HttpUrl httpUrl) {
        try {
            for (InetAddress inetAddress : InetAddress.getAllByName(httpUrl.host())) {
                if (!inetAddress.isLoopbackAddress()) {
                    throw new ProviderException(httpUrl.host() + " is not loopback only host");
                }
            }
        } catch (UnknownHostException unused) {
            throw new ProviderException("Host in " + httpUrl + " is not loopback address");
        }
    }

    private Credentials fetchCredentials(final String str) {
        String str2;
        HttpUrl httpUrl = this.customEndpoint;
        if (httpUrl == null) {
            String property = getProperty("AWS_REGION");
            if (property == null) {
                str2 = "https://sts.amazonaws.com";
            } else {
                str2 = "https://sts." + property + ".amazonaws.com";
            }
            httpUrl = HttpUrl.parse(str2);
        }
        this.credentials = new WebIdentityProvider(new Supplier() { // from class: io.minio.credentials.-$$Lambda$IamAwsProvider$2eLrL2Dq5gh4kkI3uDlZbSNrdcQ
            @Override // java.util.function.Supplier
            public final Object get() {
                return IamAwsProvider.lambda$fetchCredentials$0(str);
            }
        }, httpUrl.toString(), null, null, getProperty("AWS_ROLE_ARN"), getProperty("AWS_ROLE_SESSION_NAME"), this.httpClient).fetch();
        return this.credentials;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Jwt lambda$fetchCredentials$0(String str) {
        try {
            return new Jwt(new String(Files.readAllBytes(Paths.get(str, new String[0])), StandardCharsets.UTF_8), 0);
        } catch (IOException e) {
            throw new ProviderException("Error in reading file " + str, e);
        }
    }

    private Credentials fetchCredentials(HttpUrl httpUrl, String str, String str2) {
        Request.Builder method = new Request.Builder().url(httpUrl).method("GET", null);
        if (str2 != null && !str2.isEmpty()) {
            method.header(str, str2);
        }
        try {
            Response execute = this.httpClient.newCall(method.build()).execute();
            try {
                if (!execute.isSuccessful()) {
                    throw new ProviderException(httpUrl + " failed with HTTP status code " + execute.code());
                }
                EcsCredentials ecsCredentials = (EcsCredentials) this.mapper.readValue(execute.body().charStream(), EcsCredentials.class);
                if (ecsCredentials.code() != null && !ecsCredentials.code().equals("Success")) {
                    throw new ProviderException(httpUrl + " failed with code " + ecsCredentials.code() + " and message " + ecsCredentials.message());
                }
                Credentials credentials = ecsCredentials.toCredentials();
                if (execute != null) {
                    execute.close();
                }
                return credentials;
            } finally {
            }
        } catch (IOException e) {
            throw new ProviderException("Unable to parse response", e);
        }
    }

    private String fetchImdsToken() {
        HttpUrl build;
        HttpUrl httpUrl = this.customEndpoint;
        if (httpUrl == null) {
            build = HttpUrl.parse("http://169.254.169.254/latest/api/token");
        } else {
            build = new HttpUrl.Builder().scheme(httpUrl.scheme()).host(httpUrl.host()).addPathSegments("latest/api/token").build();
        }
        try {
            Response execute = this.httpClient.newCall(new Request.Builder().url(build).method("PUT", RequestBody.create(new byte[0], (MediaType) null)).header("X-aws-ec2-metadata-token-ttl-seconds", "21600").build()).execute();
            try {
                String string = execute.isSuccessful() ? execute.body().string() : "";
                if (execute != null) {
                    execute.close();
                }
                return string;
            } finally {
            }
        } catch (IOException unused) {
            return "";
        }
    }

    private String getIamRoleName(HttpUrl httpUrl, String str) {
        Request.Builder method = new Request.Builder().url(httpUrl).method("GET", null);
        if (str != null && !str.isEmpty()) {
            method.header("X-aws-ec2-metadata-token", str);
        }
        try {
            Response execute = this.httpClient.newCall(method.build()).execute();
            try {
                if (!execute.isSuccessful()) {
                    throw new ProviderException(httpUrl + " failed with HTTP status code " + execute.code());
                }
                String[] split = execute.body().string().split("\\R");
                if (execute != null) {
                    execute.close();
                }
                if (split.length == 0) {
                    throw new ProviderException("No IAM roles attached to EC2 service " + httpUrl);
                }
                return split[0];
            } finally {
            }
        } catch (IOException e) {
            throw new ProviderException("Unable to parse response", e);
        }
    }

    private HttpUrl getIamRoleNamedUrl(String str) {
        HttpUrl build;
        HttpUrl httpUrl = this.customEndpoint;
        if (httpUrl == null) {
            build = HttpUrl.parse("http://169.254.169.254/latest/meta-data/iam/security-credentials/");
        } else {
            build = new HttpUrl.Builder().scheme(httpUrl.scheme()).host(httpUrl.host()).addPathSegments("latest/meta-data/iam/security-credentials/").build();
        }
        return build.newBuilder().addPathSegment(getIamRoleName(build, str)).build();
    }

    @Override // io.minio.credentials.Provider
    public synchronized Credentials fetch() {
        if (this.credentials != null && !this.credentials.isExpired()) {
            return this.credentials;
        }
        HttpUrl httpUrl = this.customEndpoint;
        String property = getProperty("AWS_WEB_IDENTITY_TOKEN_FILE");
        if (property != null) {
            this.credentials = fetchCredentials(property);
            return this.credentials;
        }
        String str = "Authorization";
        String property2 = getProperty("AWS_CONTAINER_AUTHORIZATION_TOKEN");
        if (getProperty("AWS_CONTAINER_CREDENTIALS_RELATIVE_URI") != null) {
            if (httpUrl == null) {
                httpUrl = new HttpUrl.Builder().scheme(HttpHost.DEFAULT_SCHEME_NAME).host("169.254.170.2").addPathSegments(getProperty("AWS_CONTAINER_CREDENTIALS_RELATIVE_URI")).build();
            }
        } else if (getProperty("AWS_CONTAINER_CREDENTIALS_FULL_URI") != null) {
            if (httpUrl == null) {
                httpUrl = HttpUrl.parse(getProperty("AWS_CONTAINER_CREDENTIALS_FULL_URI"));
            }
            checkLoopbackHost(httpUrl);
        } else {
            property2 = fetchImdsToken();
            str = "X-aws-ec2-metadata-token";
            httpUrl = getIamRoleNamedUrl(property2);
        }
        this.credentials = fetchCredentials(httpUrl, str, property2);
        return this.credentials;
    }

    /* loaded from: classes7.dex */
    public static class EcsCredentials {

        @JsonProperty("AccessKeyID")
        private String accessKey;

        @JsonProperty(AttributeLayout.ATTRIBUTE_CODE)
        private String code;

        @JsonProperty("Expiration")
        private ResponseDate expiration;

        @JsonProperty("Message")
        private String message;

        @JsonProperty("SecretAccessKey")
        private String secretKey;

        @JsonProperty("Token")
        private String sessionToken;

        public String code() {
            return this.code;
        }

        public String message() {
            return this.message;
        }

        public Credentials toCredentials() {
            return new Credentials(this.accessKey, this.secretKey, this.sessionToken, this.expiration);
        }
    }
}
