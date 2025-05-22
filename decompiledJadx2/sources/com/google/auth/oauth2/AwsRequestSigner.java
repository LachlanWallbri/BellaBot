package com.google.auth.oauth2;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.services.p048s3.Headers;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.AwsRequestSignature;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.io.BaseEncoding;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class AwsRequestSigner {
    private static final String AWS_REQUEST_TYPE = "aws4_request";
    private static final String HASHING_ALGORITHM = "AWS4-HMAC-SHA256";
    private final Map<String, String> additionalHeaders;
    private final AwsSecurityCredentials awsSecurityCredentials;
    private final AwsDates dates;
    private final String httpMethod;
    private final String region;
    private final String requestPayload;
    private final URI uri;

    private AwsRequestSigner(AwsSecurityCredentials awsSecurityCredentials, String str, String str2, String str3, @Nullable String str4, @Nullable Map<String, String> map, @Nullable AwsDates awsDates) {
        this.awsSecurityCredentials = (AwsSecurityCredentials) Preconditions.checkNotNull(awsSecurityCredentials);
        this.httpMethod = (String) Preconditions.checkNotNull(str);
        this.uri = URI.create(str2).normalize();
        this.region = (String) Preconditions.checkNotNull(str3);
        this.requestPayload = str4 == null ? "" : str4;
        this.additionalHeaders = map != null ? new HashMap(map) : new HashMap();
        this.dates = awsDates == null ? AwsDates.generateXAmzDate() : awsDates;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AwsRequestSignature sign() {
        String next = Splitter.m618on(".").split(this.uri.getHost()).iterator().next();
        Map<String, String> canonicalHeaders = getCanonicalHeaders(this.dates.getOriginalDate());
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = canonicalHeaders.keySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toLowerCase(Locale.US));
        }
        Collections.sort(arrayList);
        String createCanonicalRequestHash = createCanonicalRequestHash(canonicalHeaders, arrayList);
        String str = this.dates.getFormattedDate() + "/" + this.region + "/" + next + "/" + AWS_REQUEST_TYPE;
        String calculateAwsV4Signature = calculateAwsV4Signature(next, this.awsSecurityCredentials.getSecretAccessKey(), this.dates.getFormattedDate(), this.region, createStringToSign(createCanonicalRequestHash, this.dates.getXAmzDate(), str));
        return new AwsRequestSignature.Builder().setSignature(calculateAwsV4Signature).setCanonicalHeaders(canonicalHeaders).setHttpMethod(this.httpMethod).setSecurityCredentials(this.awsSecurityCredentials).setCredentialScope(str).setUrl(this.uri.toString()).setDate(this.dates.getOriginalDate()).setRegion(this.region).setAuthorizationHeader(generateAuthorizationHeader(arrayList, this.awsSecurityCredentials.getAccessKeyId(), str, calculateAwsV4Signature)).build();
    }

    private String createCanonicalRequestHash(Map<String, String> map, List<String> list) {
        StringBuilder sb = new StringBuilder(this.httpMethod);
        sb.append("\n");
        sb.append(this.uri.getRawPath().isEmpty() ? "/" : this.uri.getRawPath());
        sb.append("\n");
        sb.append(this.uri.getRawQuery() != null ? this.uri.getRawQuery() : "");
        sb.append("\n");
        StringBuilder sb2 = new StringBuilder();
        for (String str : list) {
            sb2.append(str);
            sb2.append(":");
            sb2.append(map.get(str));
            sb2.append("\n");
        }
        sb.append((CharSequence) sb2);
        sb.append("\n");
        sb.append(Joiner.m608on(';').join(list));
        sb.append("\n");
        sb.append(getHexEncodedSha256Hash(this.requestPayload.getBytes(StandardCharsets.UTF_8)));
        return getHexEncodedSha256Hash(sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    private String createStringToSign(String str, String str2, String str3) {
        return "AWS4-HMAC-SHA256\n" + str2 + "\n" + str3 + "\n" + str;
    }

    private String calculateAwsV4Signature(String str, String str2, String str3, String str4, String str5) {
        return BaseEncoding.base16().lowerCase().encode(sign(sign(sign(sign(sign(("AWS4" + str2).getBytes(StandardCharsets.UTF_8), str3.getBytes(StandardCharsets.UTF_8)), str4.getBytes(StandardCharsets.UTF_8)), str.getBytes(StandardCharsets.UTF_8)), AWS_REQUEST_TYPE.getBytes(StandardCharsets.UTF_8)), str5.getBytes(StandardCharsets.UTF_8)));
    }

    private String generateAuthorizationHeader(List<String> list, String str, String str2, String str3) {
        return String.format("%s Credential=%s/%s, SignedHeaders=%s, Signature=%s", HASHING_ALGORITHM, str, str2, Joiner.m608on(';').join(list), str3);
    }

    private Map<String, String> getCanonicalHeaders(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("host", this.uri.getHost());
        if (!this.additionalHeaders.containsKey(TmpConstant.TYPE_VALUE_DATE)) {
            hashMap.put(Headers.S3_ALTERNATE_DATE, str);
        }
        if (this.awsSecurityCredentials.getToken() != null && !this.awsSecurityCredentials.getToken().isEmpty()) {
            hashMap.put(Headers.SECURITY_TOKEN, this.awsSecurityCredentials.getToken());
        }
        for (String str2 : this.additionalHeaders.keySet()) {
            hashMap.put(str2.toLowerCase(Locale.US), this.additionalHeaders.get(str2));
        }
        return hashMap;
    }

    private static byte[] sign(byte[] bArr, byte[] bArr2) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(bArr, "HmacSHA256"));
            return mac.doFinal(bArr2);
        } catch (InvalidKeyException e) {
            throw new ServiceAccountSigner.SigningException("Invalid key used when calculating the AWS V4 Signature", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("HmacSHA256 must be supported by the JVM.", e2);
        }
    }

    private static String getHexEncodedSha256Hash(byte[] bArr) {
        try {
            return BaseEncoding.base16().lowerCase().encode(MessageDigest.getInstance("SHA-256").digest(bArr));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to compute SHA-256 hash.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder newBuilder(AwsSecurityCredentials awsSecurityCredentials, String str, String str2, String str3) {
        return new Builder(awsSecurityCredentials, str, str2, str3);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class Builder {

        @Nullable
        private Map<String, String> additionalHeaders;
        private final AwsSecurityCredentials awsSecurityCredentials;

        @Nullable
        private AwsDates dates;
        private final String httpMethod;
        private final String region;

        @Nullable
        private String requestPayload;
        private final String url;

        private Builder(AwsSecurityCredentials awsSecurityCredentials, String str, String str2, String str3) {
            this.awsSecurityCredentials = awsSecurityCredentials;
            this.httpMethod = str;
            this.url = str2;
            this.region = str3;
        }

        Builder setRequestPayload(String str) {
            this.requestPayload = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setAdditionalHeaders(Map<String, String> map) {
            if (map.containsKey(TmpConstant.TYPE_VALUE_DATE) && map.containsKey(Headers.S3_ALTERNATE_DATE)) {
                throw new IllegalArgumentException("One of {date, x-amz-date} can be specified, not both.");
            }
            try {
                if (map.containsKey(TmpConstant.TYPE_VALUE_DATE)) {
                    this.dates = AwsDates.fromDateHeader(map.get(TmpConstant.TYPE_VALUE_DATE));
                }
                if (map.containsKey(Headers.S3_ALTERNATE_DATE)) {
                    this.dates = AwsDates.fromXAmzDate(map.get(Headers.S3_ALTERNATE_DATE));
                }
                this.additionalHeaders = map;
                return this;
            } catch (ParseException e) {
                throw new IllegalArgumentException("The provided date header value is invalid.", e);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public AwsRequestSigner build() {
            return new AwsRequestSigner(this.awsSecurityCredentials, this.httpMethod, this.url, this.region, this.requestPayload, this.additionalHeaders, this.dates);
        }
    }
}
