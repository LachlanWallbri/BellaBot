package com.google.auth.oauth2;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.GenericJson;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.ExternalAccountCredentials;
import com.google.auth.oauth2.StsTokenExchangeRequest;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class AwsCredentials extends ExternalAccountCredentials {
    private final AwsCredentialSource awsCredentialSource;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class AwsCredentialSource extends ExternalAccountCredentials.CredentialSource {
        private final String regionUrl;
        private final String regionalCredentialVerificationUrl;
        private final String url;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AwsCredentialSource(Map<String, Object> map) {
            super(map);
            if (!map.containsKey("regional_cred_verification_url")) {
                throw new IllegalArgumentException("A regional_cred_verification_url representing the GetCallerIdentity action URL must be specified.");
            }
            Matcher matcher = Pattern.compile("(aws)([\\d]+)").matcher((String) map.get("environment_id"));
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid AWS environment ID.");
            }
            int parseInt = Integer.parseInt(matcher.group(2));
            if (parseInt != 1) {
                throw new IllegalArgumentException(String.format("AWS version %s is not supported in the current build.", Integer.valueOf(parseInt)));
            }
            this.regionUrl = (String) map.get("region_url");
            this.url = (String) map.get("url");
            this.regionalCredentialVerificationUrl = (String) map.get("regional_cred_verification_url");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AwsCredentials(HttpTransportFactory httpTransportFactory, String str, String str2, String str3, AwsCredentialSource awsCredentialSource, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Collection<String> collection, @Nullable EnvironmentProvider environmentProvider) {
        super(httpTransportFactory, str, str2, str3, awsCredentialSource, str4, str5, str6, str7, str8, collection, environmentProvider);
        this.awsCredentialSource = awsCredentialSource;
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public AccessToken refreshAccessToken() throws IOException {
        StsTokenExchangeRequest.Builder audience = StsTokenExchangeRequest.newBuilder(retrieveSubjectToken(), getSubjectTokenType()).setAudience(getAudience());
        Collection<String> scopes = getScopes();
        if (scopes != null && !scopes.isEmpty()) {
            audience.setScopes(new ArrayList(scopes));
        }
        return exchangeExternalCredentialForAccessToken(audience.build());
    }

    @Override // com.google.auth.oauth2.ExternalAccountCredentials
    public String retrieveSubjectToken() throws IOException {
        String awsRegion = getAwsRegion();
        AwsSecurityCredentials awsSecurityCredentials = getAwsSecurityCredentials();
        HashMap hashMap = new HashMap();
        hashMap.put("x-goog-cloud-target-resource", getAudience());
        return buildSubjectToken(AwsRequestSigner.newBuilder(awsSecurityCredentials, "POST", this.awsCredentialSource.regionalCredentialVerificationUrl.replace("{region}", awsRegion), awsRegion).setAdditionalHeaders(hashMap).build().sign());
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    public GoogleCredentials createScoped(Collection<String> collection) {
        return new AwsCredentials(this.transportFactory, getAudience(), getSubjectTokenType(), getTokenUrl(), this.awsCredentialSource, getTokenInfoUrl(), getServiceAccountImpersonationUrl(), getQuotaProjectId(), getClientId(), getClientSecret(), collection, getEnvironmentProvider());
    }

    private String retrieveResource(String str, String str2) throws IOException {
        try {
            return this.transportFactory.create().createRequestFactory().buildGetRequest(new GenericUrl(str)).execute().parseAsString();
        } catch (IOException e) {
            throw new IOException(String.format("Failed to retrieve AWS %s.", str2), e);
        }
    }

    private String buildSubjectToken(AwsRequestSignature awsRequestSignature) throws UnsupportedEncodingException {
        Map<String, String> canonicalHeaders = awsRequestSignature.getCanonicalHeaders();
        ArrayList arrayList = new ArrayList();
        for (String str : canonicalHeaders.keySet()) {
            arrayList.add(formatTokenHeaderForSts(str, canonicalHeaders.get(str)));
        }
        arrayList.add(formatTokenHeaderForSts("Authorization", awsRequestSignature.getAuthorizationHeader()));
        arrayList.add(formatTokenHeaderForSts("x-goog-cloud-target-resource", getAudience()));
        GenericJson genericJson = new GenericJson();
        genericJson.setFactory(OAuth2Utils.JSON_FACTORY);
        genericJson.put("headers", (Object) arrayList);
        genericJson.put("method", (Object) awsRequestSignature.getHttpMethod());
        genericJson.put("url", (Object) this.awsCredentialSource.regionalCredentialVerificationUrl.replace("{region}", awsRequestSignature.getRegion()));
        return URLEncoder.encode(genericJson.toString(), "UTF-8");
    }

    String getAwsRegion() throws IOException {
        String env = getEnvironmentProvider().getEnv("AWS_REGION");
        if (env != null) {
            return env;
        }
        String env2 = getEnvironmentProvider().getEnv("AWS_DEFAULT_REGION");
        if (env2 != null) {
            return env2;
        }
        if (this.awsCredentialSource.regionUrl != null && !this.awsCredentialSource.regionUrl.isEmpty()) {
            return retrieveResource(this.awsCredentialSource.regionUrl, OSSConfig.PARAM_REGION).substring(0, r0.length() - 1);
        }
        throw new IOException("Unable to determine the AWS region. The credential source does not contain the region URL.");
    }

    AwsSecurityCredentials getAwsSecurityCredentials() throws IOException {
        String env = getEnvironmentProvider().getEnv(SDKGlobalConfiguration.ACCESS_KEY_ENV_VAR);
        String env2 = getEnvironmentProvider().getEnv(SDKGlobalConfiguration.ALTERNATE_SECRET_KEY_ENV_VAR);
        String env3 = getEnvironmentProvider().getEnv("Token");
        if (env == null || env2 == null) {
            if (this.awsCredentialSource.url != null && !this.awsCredentialSource.url.isEmpty()) {
                GenericJson genericJson = (GenericJson) OAuth2Utils.JSON_FACTORY.createJsonParser(retrieveResource(this.awsCredentialSource.url + "/" + retrieveResource(this.awsCredentialSource.url, "IAM role"), "credentials")).parseAndClose(GenericJson.class);
                return new AwsSecurityCredentials((String) genericJson.get("AccessKeyId"), (String) genericJson.get("SecretAccessKey"), (String) genericJson.get("Token"));
            }
            throw new IOException("Unable to determine the AWS IAM role name. The credential source does not contain the url field.");
        }
        return new AwsSecurityCredentials(env, env2, env3);
    }

    String getEnv(String str) {
        return System.getenv(str);
    }

    private static GenericJson formatTokenHeaderForSts(String str, String str2) {
        GenericJson genericJson = new GenericJson();
        genericJson.setFactory(OAuth2Utils.JSON_FACTORY);
        genericJson.put(TransferTable.COLUMN_KEY, (Object) str);
        genericJson.put(ES6Iterator.VALUE_PROPERTY, (Object) str2);
        return genericJson;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(AwsCredentials awsCredentials) {
        return new Builder(awsCredentials);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder extends ExternalAccountCredentials.Builder {
        Builder() {
        }

        Builder(AwsCredentials awsCredentials) {
            super(awsCredentials);
        }

        @Override // com.google.auth.oauth2.ExternalAccountCredentials.Builder, com.google.auth.oauth2.GoogleCredentials.Builder, com.google.auth.oauth2.OAuth2Credentials.Builder
        public AwsCredentials build() {
            return new AwsCredentials(this.transportFactory, this.audience, this.subjectTokenType, this.tokenUrl, (AwsCredentialSource) this.credentialSource, this.tokenInfoUrl, this.serviceAccountImpersonationUrl, this.quotaProjectId, this.clientId, this.clientSecret, this.scopes, this.environmentProvider);
        }
    }
}
