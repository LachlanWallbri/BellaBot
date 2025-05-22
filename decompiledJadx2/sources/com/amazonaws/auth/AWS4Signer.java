package com.amazonaws.auth;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.p048s3.Headers;
import com.amazonaws.util.AwsHostNameUtils;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.HttpUtils;
import com.amazonaws.util.StringUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/* loaded from: classes.dex */
public class AWS4Signer extends AbstractAWSSigner implements ServiceAwareSigner, RegionAwareSigner, Presigner {
    protected static final String ALGORITHM = "AWS4-HMAC-SHA256";
    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final long MAX_EXPIRATION_TIME_IN_SECONDS = 604800;
    private static final long MILLISEC = 1000;
    protected static final String TERMINATOR = "aws4_request";
    private static final String TIME_PATTERN = "yyyyMMdd'T'HHmmss'Z'";
    protected static final Log log = LogFactory.getLog((Class<?>) AWS4Signer.class);
    protected boolean doubleUrlEncode;
    protected Date overriddenDate;
    protected String regionName;
    protected String serviceName;

    protected void processRequestPayload(Request<?> request, HeaderSigningResult headerSigningResult) {
    }

    public AWS4Signer() {
        this(true);
    }

    public AWS4Signer(boolean z) {
        this.doubleUrlEncode = z;
    }

    @Override // com.amazonaws.auth.Signer
    public void sign(Request<?> request, AWSCredentials aWSCredentials) {
        if (aWSCredentials instanceof AnonymousAWSCredentials) {
            return;
        }
        AWSCredentials sanitizeCredentials = sanitizeCredentials(aWSCredentials);
        if (sanitizeCredentials instanceof AWSSessionCredentials) {
            addSessionCredentials(request, (AWSSessionCredentials) sanitizeCredentials);
        }
        addHostHeader(request);
        long dateFromRequest = getDateFromRequest(request);
        String dateStamp = getDateStamp(dateFromRequest);
        String scope = getScope(request, dateStamp);
        String calculateContentHash = calculateContentHash(request);
        String timeStamp = getTimeStamp(dateFromRequest);
        request.addHeader("X-Amz-Date", timeStamp);
        if (request.getHeaders().get("x-amz-content-sha256") != null && "required".equals(request.getHeaders().get("x-amz-content-sha256"))) {
            request.addHeader("x-amz-content-sha256", calculateContentHash);
        }
        String str = sanitizeCredentials.getAWSAccessKeyId() + "/" + scope;
        HeaderSigningResult computeSignature = computeSignature(request, dateStamp, timeStamp, ALGORITHM, calculateContentHash, sanitizeCredentials);
        request.addHeader("Authorization", "AWS4-HMAC-SHA256 " + ("Credential=" + str) + ", " + ("SignedHeaders=" + getSignedHeadersString(request)) + ", " + ("Signature=" + BinaryUtils.toHex(computeSignature.getSignature())));
        processRequestPayload(request, computeSignature);
    }

    @Override // com.amazonaws.auth.ServiceAwareSigner
    public void setServiceName(String str) {
        this.serviceName = str;
    }

    @Override // com.amazonaws.auth.RegionAwareSigner
    public void setRegionName(String str) {
        this.regionName = str;
    }

    @Override // com.amazonaws.auth.AbstractAWSSigner
    protected void addSessionCredentials(Request<?> request, AWSSessionCredentials aWSSessionCredentials) {
        request.addHeader(Headers.SECURITY_TOKEN, aWSSessionCredentials.getSessionToken());
    }

    protected String extractRegionName(URI uri) {
        String str = this.regionName;
        return str != null ? str : AwsHostNameUtils.parseRegionName(uri.getHost(), this.serviceName);
    }

    protected String extractServiceName(URI uri) {
        String str = this.serviceName;
        return str != null ? str : AwsHostNameUtils.parseServiceName(uri);
    }

    void overrideDate(Date date) {
        this.overriddenDate = date;
    }

    protected String getCanonicalizedHeaderString(Request<?> request) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.addAll(request.getHeaders().keySet());
        Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (String str : arrayList) {
            if (needsSign(str)) {
                String replaceAll = StringUtils.lowerCase(str).replaceAll("\\s+", " ");
                String str2 = request.getHeaders().get(str);
                sb.append(replaceAll);
                sb.append(":");
                if (str2 != null) {
                    sb.append(str2.replaceAll("\\s+", " "));
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    protected String getSignedHeadersString(Request<?> request) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.addAll(request.getHeaders().keySet());
        Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (String str : arrayList) {
            if (needsSign(str)) {
                if (sb.length() > 0) {
                    sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                }
                sb.append(StringUtils.lowerCase(str));
            }
        }
        return sb.toString();
    }

    protected String getCanonicalRequest(Request<?> request, String str) {
        String appendUri;
        if (request.getEncodedUriResourcePath() != null) {
            appendUri = HttpUtils.appendUriEncoded(request.getEndpoint().getPath(), request.getEncodedUriResourcePath());
        } else {
            appendUri = HttpUtils.appendUri(request.getEndpoint().getPath(), request.getResourcePath());
        }
        String str2 = request.getHttpMethod().toString() + "\n" + getCanonicalizedResourcePath(appendUri, this.doubleUrlEncode) + "\n" + getCanonicalizedQueryString(request) + "\n" + getCanonicalizedHeaderString(request) + "\n" + getSignedHeadersString(request) + "\n" + str;
        log.debug("AWS4 Canonical Request: '\"" + str2 + "\"");
        return str2;
    }

    protected String getStringToSign(String str, String str2, String str3, String str4) {
        String str5 = str + "\n" + str2 + "\n" + str3 + "\n" + BinaryUtils.toHex(hash(str4));
        log.debug("AWS4 String to Sign: '\"" + str5 + "\"");
        return str5;
    }

    protected final HeaderSigningResult computeSignature(Request<?> request, String str, String str2, String str3, String str4, AWSCredentials aWSCredentials) {
        String extractRegionName = extractRegionName(request.getEndpoint());
        String extractServiceName = extractServiceName(request.getEndpoint());
        String str5 = str + "/" + extractRegionName + "/" + extractServiceName + "/" + TERMINATOR;
        String stringToSign = getStringToSign(str3, str2, str5, getCanonicalRequest(request, str4));
        byte[] sign = sign(TERMINATOR, sign(extractServiceName, sign(extractRegionName, sign(str, ("AWS4" + aWSCredentials.getAWSSecretKey()).getBytes(StringUtils.UTF8), SigningAlgorithm.HmacSHA256), SigningAlgorithm.HmacSHA256), SigningAlgorithm.HmacSHA256), SigningAlgorithm.HmacSHA256);
        return new HeaderSigningResult(str2, str5, sign, sign(stringToSign.getBytes(StringUtils.UTF8), sign, SigningAlgorithm.HmacSHA256));
    }

    protected final String getTimeStamp(long j) {
        return DateUtils.format("yyyyMMdd'T'HHmmss'Z'", new Date(j));
    }

    protected final String getDateStamp(long j) {
        return DateUtils.format(DATE_PATTERN, new Date(j));
    }

    protected final long getDateFromRequest(Request<?> request) {
        Date signatureDate = getSignatureDate(getTimeOffset(request));
        Date date = this.overriddenDate;
        if (date != null) {
            signatureDate = date;
        }
        return signatureDate.getTime();
    }

    protected void addHostHeader(Request<?> request) {
        String host = request.getEndpoint().getHost();
        if (HttpUtils.isUsingNonDefaultPort(request.getEndpoint())) {
            host = host + ":" + request.getEndpoint().getPort();
        }
        request.addHeader("Host", host);
    }

    protected String getScope(Request<?> request, String str) {
        return str + "/" + extractRegionName(request.getEndpoint()) + "/" + extractServiceName(request.getEndpoint()) + "/" + TERMINATOR;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String calculateContentHash(Request<?> request) {
        InputStream binaryRequestPayloadStream = getBinaryRequestPayloadStream(request);
        binaryRequestPayloadStream.mark(-1);
        String hex = BinaryUtils.toHex(hash(binaryRequestPayloadStream));
        try {
            binaryRequestPayloadStream.reset();
            return hex;
        } catch (IOException e) {
            throw new AmazonClientException("Unable to reset stream after calculating AWS4 signature", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class HeaderSigningResult {
        private final String dateTime;
        private final byte[] kSigning;
        private final String scope;
        private final byte[] signature;

        public HeaderSigningResult(String str, String str2, byte[] bArr, byte[] bArr2) {
            this.dateTime = str;
            this.scope = str2;
            this.kSigning = bArr;
            this.signature = bArr2;
        }

        public String getDateTime() {
            return this.dateTime;
        }

        public String getScope() {
            return this.scope;
        }

        public byte[] getKSigning() {
            byte[] bArr = this.kSigning;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }

        public byte[] getSignature() {
            byte[] bArr = this.signature;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
    }

    @Override // com.amazonaws.auth.Presigner
    public void presignRequest(Request<?> request, AWSCredentials aWSCredentials, Date date) {
        if (aWSCredentials instanceof AnonymousAWSCredentials) {
            return;
        }
        long time = date != null ? (date.getTime() - System.currentTimeMillis()) / 1000 : 604800L;
        if (time > MAX_EXPIRATION_TIME_IN_SECONDS) {
            throw new AmazonClientException("Requests that are pre-signed by SigV4 algorithm are valid for at most 7 days. The expiration date set on the current request [" + getTimeStamp(date.getTime()) + "] has exceeded this limit.");
        }
        addHostHeader(request);
        AWSCredentials sanitizeCredentials = sanitizeCredentials(aWSCredentials);
        if (sanitizeCredentials instanceof AWSSessionCredentials) {
            request.addParameter("X-Amz-Security-Token", ((AWSSessionCredentials) sanitizeCredentials).getSessionToken());
        }
        long dateFromRequest = getDateFromRequest(request);
        String dateStamp = getDateStamp(dateFromRequest);
        String str = sanitizeCredentials.getAWSAccessKeyId() + "/" + getScope(request, dateStamp);
        String timeStamp = getTimeStamp(dateFromRequest);
        request.addParameter("X-Amz-Algorithm", ALGORITHM);
        request.addParameter("X-Amz-Date", timeStamp);
        request.addParameter("X-Amz-SignedHeaders", getSignedHeadersString(request));
        request.addParameter("X-Amz-Expires", Long.toString(time));
        request.addParameter("X-Amz-Credential", str);
        request.addParameter("X-Amz-Signature", BinaryUtils.toHex(computeSignature(request, dateStamp, timeStamp, ALGORITHM, calculateContentHashPresign(request), sanitizeCredentials).getSignature()));
    }

    protected String calculateContentHashPresign(Request<?> request) {
        return calculateContentHash(request);
    }

    boolean needsSign(String str) {
        return TmpConstant.TYPE_VALUE_DATE.equalsIgnoreCase(str) || "Content-MD5".equalsIgnoreCase(str) || "host".equalsIgnoreCase(str) || str.startsWith("x-amz") || str.startsWith("X-Amz");
    }
}
