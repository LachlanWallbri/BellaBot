package io.minio;

import com.amazonaws.regions.ServiceAbbreviations;
import com.amazonaws.services.p048s3.Headers;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.io.BaseEncoding;
import com.iflytek.speech.VoiceWakeuperAidl;
import io.grpc.internal.GrpcUtil;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.HttpUrl;
import okhttp3.Request;

/* loaded from: classes7.dex */
public class Signer {
    private static final Set<String> IGNORED_HEADERS = ImmutableSet.m679of(GrpcUtil.CONTENT_ACCEPT_ENCODING, "authorization", "user-agent");
    private static final Set<String> PRESIGN_IGNORED_HEADERS = ImmutableSet.m682of(GrpcUtil.CONTENT_ACCEPT_ENCODING, "authorization", "user-agent", "content-md5", "x-amz-content-sha256", Headers.S3_ALTERNATE_DATE, Headers.SECURITY_TOKEN);
    private String accessKey;
    private String authorization;
    private Map<String, String> canonicalHeaders;
    private String canonicalQueryString;
    private String canonicalRequest;
    private String canonicalRequestHash;
    private String contentSha256;
    private ZonedDateTime date;
    private String prevSignature;
    private String region;
    private Request request;
    private String scope;
    private String secretKey;
    private String signature;
    private String signedHeaders;
    private byte[] signingKey;
    private String stringToSign;
    private HttpUrl url;

    private Signer(Request request, String str, ZonedDateTime zonedDateTime, String str2, String str3, String str4, String str5) {
        this.request = request;
        this.contentSha256 = str;
        this.date = zonedDateTime;
        this.region = str2;
        this.accessKey = str3;
        this.secretKey = str4;
        this.prevSignature = str5;
    }

    private void setScope(String str) {
        this.scope = this.date.format(Time.SIGNER_DATE_FORMAT) + "/" + this.region + "/" + str + "/aws4_request";
    }

    private void setCanonicalHeaders(Set<String> set) {
        this.canonicalHeaders = new TreeMap();
        okhttp3.Headers headers = this.request.headers();
        for (String str : headers.names()) {
            String lowerCase = str.toLowerCase(Locale.US);
            if (!set.contains(lowerCase)) {
                this.canonicalHeaders.put(lowerCase, (String) headers.values(str).stream().map(new Function() { // from class: io.minio.-$$Lambda$Signer$TCSOXJPvl1pcAFfp6SSNfnz-saA
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        String replaceAll;
                        replaceAll = ((String) obj).replaceAll("( +)", " ");
                        return replaceAll;
                    }
                }).collect(Collectors.joining(",")));
            }
        }
        this.signedHeaders = Joiner.m609on(VoiceWakeuperAidl.PARAMS_SEPARATE).join(this.canonicalHeaders.keySet());
    }

    private void setCanonicalQueryString() {
        String encodedQuery = this.url.encodedQuery();
        if (encodedQuery == null) {
            this.canonicalQueryString = "";
            return;
        }
        Multimap build = MultimapBuilder.treeKeys().arrayListValues().build();
        for (String str : encodedQuery.split("&")) {
            String[] split = str.split("=");
            if (split.length > 1) {
                build.put(split[0], split[1]);
            } else {
                build.put(split[0], "");
            }
        }
        this.canonicalQueryString = Joiner.m609on("&").withKeyValueSeparator("=").join(build.entries());
    }

    private void setCanonicalRequest() throws NoSuchAlgorithmException {
        setCanonicalHeaders(IGNORED_HEADERS);
        this.url = this.request.url();
        setCanonicalQueryString();
        this.canonicalRequest = this.request.method() + "\n" + this.url.encodedPath() + "\n" + this.canonicalQueryString + "\n" + Joiner.m609on("\n").withKeyValueSeparator(":").join(this.canonicalHeaders) + "\n\n" + this.signedHeaders + "\n" + this.contentSha256;
        this.canonicalRequestHash = Digest.sha256Hash(this.canonicalRequest);
    }

    private void setStringToSign() {
        this.stringToSign = "AWS4-HMAC-SHA256\n" + this.date.format(Time.AMZ_DATE_FORMAT) + "\n" + this.scope + "\n" + this.canonicalRequestHash;
    }

    private void setChunkStringToSign() throws NoSuchAlgorithmException {
        this.stringToSign = "AWS4-HMAC-SHA256-PAYLOAD\n" + this.date.format(Time.AMZ_DATE_FORMAT) + "\n" + this.scope + "\n" + this.prevSignature + "\n" + Digest.sha256Hash("") + "\n" + this.contentSha256;
    }

    private void setSigningKey(String str) throws NoSuchAlgorithmException, InvalidKeyException {
        this.signingKey = sumHmac(sumHmac(sumHmac(sumHmac(("AWS4" + this.secretKey).getBytes(StandardCharsets.UTF_8), this.date.format(Time.SIGNER_DATE_FORMAT).getBytes(StandardCharsets.UTF_8)), this.region.getBytes(StandardCharsets.UTF_8)), str.getBytes(StandardCharsets.UTF_8)), "aws4_request".getBytes(StandardCharsets.UTF_8));
    }

    private void setSignature() throws NoSuchAlgorithmException, InvalidKeyException {
        this.signature = BaseEncoding.base16().encode(sumHmac(this.signingKey, this.stringToSign.getBytes(StandardCharsets.UTF_8))).toLowerCase(Locale.US);
    }

    private void setAuthorization() {
        this.authorization = "AWS4-HMAC-SHA256 Credential=" + this.accessKey + "/" + this.scope + ", SignedHeaders=" + this.signedHeaders + ", Signature=" + this.signature;
    }

    public static String getChunkSignature(String str, ZonedDateTime zonedDateTime, String str2, String str3, String str4) throws NoSuchAlgorithmException, InvalidKeyException {
        Signer signer = new Signer(null, str, zonedDateTime, str2, null, str3, str4);
        signer.setScope("s3");
        signer.setChunkStringToSign();
        signer.setSigningKey("s3");
        signer.setSignature();
        return signer.signature;
    }

    private static Request signV4(String str, Request request, String str2, String str3, String str4, String str5) throws NoSuchAlgorithmException, InvalidKeyException {
        Signer signer = new Signer(request, str5, ZonedDateTime.parse(request.header(Headers.S3_ALTERNATE_DATE), Time.AMZ_DATE_FORMAT), str2, str3, str4, null);
        signer.setScope(str);
        signer.setCanonicalRequest();
        signer.setStringToSign();
        signer.setSigningKey(str);
        signer.setSignature();
        signer.setAuthorization();
        return request.newBuilder().header("Authorization", signer.authorization).build();
    }

    public static Request signV4S3(Request request, String str, String str2, String str3, String str4) throws NoSuchAlgorithmException, InvalidKeyException {
        return signV4("s3", request, str, str2, str3, str4);
    }

    public static Request signV4Sts(Request request, String str, String str2, String str3, String str4) throws NoSuchAlgorithmException, InvalidKeyException {
        return signV4(ServiceAbbreviations.STS, request, str, str2, str3, str4);
    }

    private void setPresignCanonicalRequest(int i) throws NoSuchAlgorithmException {
        setCanonicalHeaders(PRESIGN_IGNORED_HEADERS);
        HttpUrl.Builder newBuilder = this.request.url().newBuilder();
        newBuilder.addEncodedQueryParameter(S3Escaper.encode("X-Amz-Algorithm"), S3Escaper.encode("AWS4-HMAC-SHA256"));
        newBuilder.addEncodedQueryParameter(S3Escaper.encode("X-Amz-Credential"), S3Escaper.encode(this.accessKey + "/" + this.scope));
        newBuilder.addEncodedQueryParameter(S3Escaper.encode("X-Amz-Date"), S3Escaper.encode(this.date.format(Time.AMZ_DATE_FORMAT)));
        newBuilder.addEncodedQueryParameter(S3Escaper.encode("X-Amz-Expires"), S3Escaper.encode(Integer.toString(i)));
        newBuilder.addEncodedQueryParameter(S3Escaper.encode("X-Amz-SignedHeaders"), S3Escaper.encode(this.signedHeaders));
        this.url = newBuilder.build();
        setCanonicalQueryString();
        this.canonicalRequest = this.request.method() + "\n" + this.url.encodedPath() + "\n" + this.canonicalQueryString + "\n" + Joiner.m609on("\n").withKeyValueSeparator(":").join(this.canonicalHeaders) + "\n\n" + this.signedHeaders + "\n" + this.contentSha256;
        this.canonicalRequestHash = Digest.sha256Hash(this.canonicalRequest);
    }

    public static HttpUrl presignV4(Request request, String str, String str2, String str3, int i) throws NoSuchAlgorithmException, InvalidKeyException {
        Signer signer = new Signer(request, "UNSIGNED-PAYLOAD", ZonedDateTime.parse(request.header(Headers.S3_ALTERNATE_DATE), Time.AMZ_DATE_FORMAT), str, str2, str3, null);
        signer.setScope("s3");
        signer.setPresignCanonicalRequest(i);
        signer.setStringToSign();
        signer.setSigningKey("s3");
        signer.setSignature();
        return signer.url.newBuilder().addEncodedQueryParameter(S3Escaper.encode("X-Amz-Signature"), S3Escaper.encode(signer.signature)).build();
    }

    public static String credential(String str, ZonedDateTime zonedDateTime, String str2) {
        return str + "/" + zonedDateTime.format(Time.SIGNER_DATE_FORMAT) + "/" + str2 + "/s3/aws4_request";
    }

    public static String postPresignV4(String str, String str2, ZonedDateTime zonedDateTime, String str3) throws NoSuchAlgorithmException, InvalidKeyException {
        Signer signer = new Signer(null, null, zonedDateTime, str3, null, str2, null);
        signer.stringToSign = str;
        signer.setSigningKey("s3");
        signer.setSignature();
        return signer.signature;
    }

    public static byte[] sumHmac(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(bArr, "HmacSHA256"));
        mac.update(bArr2);
        return mac.doFinal();
    }
}
