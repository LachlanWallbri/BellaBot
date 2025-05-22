package com.amazonaws.services.p048s3.internal;

import com.amazonaws.Request;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AbstractAWSSigner;
import com.amazonaws.auth.SigningAlgorithm;
import com.amazonaws.services.p048s3.Headers;
import java.util.Date;

/* loaded from: classes.dex */
public class S3QueryStringSigner extends AbstractAWSSigner {
    private static final Long TIME_TO_SECONDS = 1000L;
    private final Date expiration;
    private final String httpVerb;
    private final String resourcePath;

    public S3QueryStringSigner(String str, String str2, Date date) {
        this.httpVerb = str;
        this.resourcePath = str2;
        this.expiration = date;
        if (str2 == null) {
            throw new IllegalArgumentException("Parameter resourcePath is empty");
        }
    }

    @Override // com.amazonaws.auth.Signer
    public void sign(Request<?> request, AWSCredentials aWSCredentials) {
        AWSCredentials sanitizeCredentials = sanitizeCredentials(aWSCredentials);
        if (sanitizeCredentials instanceof AWSSessionCredentials) {
            addSessionCredentials(request, (AWSSessionCredentials) sanitizeCredentials);
        }
        String l = Long.toString(this.expiration.getTime() / TIME_TO_SECONDS.longValue());
        String signAndBase64Encode = super.signAndBase64Encode(RestUtils.makeS3CanonicalString(this.httpVerb, this.resourcePath, request, l), sanitizeCredentials.getAWSSecretKey(), SigningAlgorithm.HmacSHA1);
        request.addParameter("AWSAccessKeyId", sanitizeCredentials.getAWSAccessKeyId());
        request.addParameter("Expires", l);
        request.addParameter("Signature", signAndBase64Encode);
    }

    @Override // com.amazonaws.auth.AbstractAWSSigner
    protected void addSessionCredentials(Request<?> request, AWSSessionCredentials aWSSessionCredentials) {
        request.addParameter(Headers.SECURITY_TOKEN, aWSSessionCredentials.getSessionToken());
    }
}
