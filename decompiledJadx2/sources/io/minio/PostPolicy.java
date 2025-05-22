package io.minio;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.amazonaws.services.p048s3.Headers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import io.minio.credentials.Credentials;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/* loaded from: classes7.dex */
public class PostPolicy {
    private static final String ALGORITHM = "AWS4-HMAC-SHA256";

    /* renamed from: EQ */
    private static final String f8436EQ = "eq";
    private static final String STARTS_WITH = "starts-with";
    private String bucketName;
    private Map<String, Map<String, String>> conditions;
    private ZonedDateTime expiration;
    private Long lowerLimit = null;
    private Long upperLimit = null;
    private static final List<String> RESERVED_ELEMENTS = Arrays.asList(OSSConfig.PARAM_BUCKET, "x-amz-algorithm", "x-amz-credential", Headers.S3_ALTERNATE_DATE, "policy", "x-amz-signature");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public PostPolicy(@Nonnull String str, @Nonnull ZonedDateTime zonedDateTime) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("bucket name cannot be empty");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(f8436EQ, new LinkedHashMap());
        linkedHashMap.put(STARTS_WITH, new LinkedHashMap());
        this.bucketName = str;
        this.expiration = zonedDateTime;
        this.conditions = linkedHashMap;
    }

    private String trimDollar(String str) {
        return str.startsWith("$") ? str.substring(1, str.length()) : str;
    }

    public void addEqualsCondition(@Nonnull String str, @Nonnull String str2) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("condition element cannot be empty");
        }
        String trimDollar = trimDollar(str);
        if ("success_action_redirect".equals(trimDollar) || "redirect".equals(trimDollar) || "content-length-range".equals(trimDollar)) {
            throw new IllegalArgumentException(trimDollar + " is unsupported for equals condition");
        }
        if (RESERVED_ELEMENTS.contains(trimDollar)) {
            throw new IllegalArgumentException(trimDollar + " cannot be set");
        }
        this.conditions.get(f8436EQ).put(trimDollar, str2);
    }

    public void removeEqualsCondition(@Nonnull String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("condition element cannot be empty");
        }
        this.conditions.get(f8436EQ).remove(trimDollar(str));
    }

    public void addStartsWithCondition(@Nonnull String str, @Nonnull String str2) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("condition element cannot be empty");
        }
        String trimDollar = trimDollar(str);
        if ("success_action_status".equals(trimDollar) || "content-length-range".equals(trimDollar) || (trimDollar.startsWith(Headers.AMAZON_PREFIX) && !trimDollar.startsWith(Headers.S3_USER_METADATA_PREFIX))) {
            throw new IllegalArgumentException(trimDollar + " is unsupported for starts-with condition");
        }
        if (RESERVED_ELEMENTS.contains(trimDollar)) {
            throw new IllegalArgumentException(trimDollar + " cannot be set");
        }
        this.conditions.get(STARTS_WITH).put(trimDollar, str2);
    }

    public void removeStartsWithCondition(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("condition element cannot be empty");
        }
        this.conditions.get(STARTS_WITH).remove(trimDollar(str));
    }

    public void addContentLengthRangeCondition(long j, long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("lower limit cannot be negative number");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("upper limit cannot be negative number");
        }
        if (j > j2) {
            throw new IllegalArgumentException("lower limit cannot be greater than upper limit");
        }
        this.lowerLimit = Long.valueOf(j);
        this.upperLimit = Long.valueOf(j2);
    }

    public void addContentLengthRangeCondition(int i, int i2) {
        addContentLengthRangeCondition(i, i2);
    }

    public void removeContentLengthRangeCondition() {
        this.lowerLimit = null;
        this.upperLimit = null;
    }

    public Map<String, String> formData(@Nonnull Credentials credentials, @Nonnull String str) throws NoSuchAlgorithmException, InvalidKeyException {
        Long l;
        if (credentials == null) {
            throw new IllegalArgumentException("credentials cannot be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("region cannot be empty");
        }
        if (!this.conditions.get(f8436EQ).containsKey(TransferTable.COLUMN_KEY) && !this.conditions.get(STARTS_WITH).containsKey(TransferTable.COLUMN_KEY)) {
            throw new IllegalArgumentException("key condition must be set");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("expiration", this.expiration.format(Time.EXPIRATION_DATE_FORMAT));
        LinkedList linkedList = new LinkedList();
        linkedList.add(Arrays.asList(f8436EQ, "$bucket", this.bucketName));
        for (Map.Entry<String, Map<String, String>> entry : this.conditions.entrySet()) {
            for (Map.Entry<String, String> entry2 : entry.getValue().entrySet()) {
                linkedList.add(Arrays.asList(entry.getKey(), "$" + entry2.getKey(), entry2.getValue()));
            }
        }
        Long l2 = this.lowerLimit;
        if (l2 != null && (l = this.upperLimit) != null) {
            linkedList.add(Arrays.asList("content-length-range", l2, l));
        }
        ZonedDateTime now = ZonedDateTime.now(Time.UTC);
        String credential = Signer.credential(credentials.accessKey(), now, str);
        String format = now.format(Time.AMZ_DATE_FORMAT);
        linkedList.add(Arrays.asList(f8436EQ, "$x-amz-algorithm", ALGORITHM));
        linkedList.add(Arrays.asList(f8436EQ, "$x-amz-credential", credential));
        if (credentials.sessionToken() != null) {
            linkedList.add(Arrays.asList(f8436EQ, "$x-amz-security-token", credentials.sessionToken()));
        }
        linkedList.add(Arrays.asList(f8436EQ, "$x-amz-date", format));
        hashMap.put("conditions", linkedList);
        try {
            String encodeToString = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(hashMap).getBytes(StandardCharsets.UTF_8));
            String postPresignV4 = Signer.postPresignV4(encodeToString, credentials.secretKey(), now, str);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("x-amz-algorithm", ALGORITHM);
            hashMap2.put("x-amz-credential", credential);
            if (credentials.sessionToken() != null) {
                hashMap2.put(Headers.SECURITY_TOKEN, credentials.sessionToken());
            }
            hashMap2.put(Headers.S3_ALTERNATE_DATE, format);
            hashMap2.put("policy", encodeToString);
            hashMap2.put("x-amz-signature", postPresignV4);
            return hashMap2;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String bucket() {
        return this.bucketName;
    }
}
