package io.minio.credentials;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import io.minio.Xml;
import io.minio.errors.XmlParserException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.ProviderException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;

/* loaded from: classes7.dex */
public abstract class AssumeRoleBaseProvider implements Provider {
    public static final int DEFAULT_DURATION_SECONDS = (int) TimeUnit.HOURS.toSeconds(1);
    private Credentials credentials;
    private final OkHttpClient httpClient;

    /* loaded from: classes7.dex */
    public interface Response {
        Credentials getCredentials();
    }

    protected abstract Request getRequest();

    protected abstract Class<? extends Response> getResponseClass();

    public AssumeRoleBaseProvider(OkHttpClient okHttpClient) {
        this.httpClient = okHttpClient == null ? new OkHttpClient().newBuilder().protocols(Arrays.asList(Protocol.HTTP_1_1)).build() : okHttpClient;
    }

    public AssumeRoleBaseProvider(OkHttpClient okHttpClient, SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) throws GeneralSecurityException, IOException {
        if (okHttpClient != null) {
            if (sSLSocketFactory != null || x509TrustManager != null) {
                throw new IllegalArgumentException("Either sslSocketFactory/trustManager or custom HTTP client must be provided");
            }
            this.httpClient = okHttpClient;
            return;
        }
        if (sSLSocketFactory == null || x509TrustManager == null) {
            throw new IllegalArgumentException("Both sslSocketFactory and trustManager must be provided");
        }
        this.httpClient = new OkHttpClient().newBuilder().protocols(Arrays.asList(Protocol.HTTP_1_1)).sslSocketFactory(sSLSocketFactory, x509TrustManager).build();
    }

    @Override // io.minio.credentials.Provider
    public synchronized Credentials fetch() {
        if (this.credentials != null && !this.credentials.isExpired()) {
            return this.credentials;
        }
        try {
            okhttp3.Response execute = this.httpClient.newCall(getRequest()).execute();
            try {
                if (!execute.isSuccessful()) {
                    throw new ProviderException("STS service failed with HTTP status code " + execute.code());
                }
                this.credentials = parseResponse(execute);
                Credentials credentials = this.credentials;
                if (execute != null) {
                    execute.close();
                }
                return credentials;
            } catch (Throwable th) {
                if (execute != null) {
                    try {
                        execute.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (XmlParserException | IOException e) {
            throw new ProviderException("Unable to parse STS response", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getValidDurationSeconds(Integer num) {
        if (num != null && num.intValue() > DEFAULT_DURATION_SECONDS) {
            return num.intValue();
        }
        return DEFAULT_DURATION_SECONDS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpUrl.Builder newUrlBuilder(HttpUrl httpUrl, String str, int i, String str2, String str3, String str4) {
        HttpUrl.Builder addQueryParameter = httpUrl.newBuilder().addQueryParameter(JsonDocumentFields.ACTION, str).addQueryParameter(JsonDocumentFields.VERSION, "2011-06-15");
        if (i > 0) {
            addQueryParameter.addQueryParameter("DurationSeconds", String.valueOf(i));
        }
        if (str2 != null) {
            addQueryParameter.addQueryParameter("Policy", str2);
        }
        if (str3 != null) {
            addQueryParameter.addQueryParameter("RoleArn", str3);
        }
        if (str4 != null) {
            addQueryParameter.addQueryParameter("RoleSessionName", str4);
        }
        return addQueryParameter;
    }

    protected Credentials parseResponse(okhttp3.Response response) throws XmlParserException, IOException {
        return ((Response) Xml.unmarshal(getResponseClass(), response.body().charStream())).getCredentials();
    }
}
