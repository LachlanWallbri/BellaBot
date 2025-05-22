package io.minio;

import io.minio.messages.LegalHold;
import io.minio.messages.ResponseDate;
import io.minio.messages.RetentionMode;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import okhttp3.Headers;

/* loaded from: classes7.dex */
public class StatObjectResponse extends GenericResponse {
    private boolean deleteMarker;
    private String etag;
    private ZonedDateTime lastModified;
    private LegalHold legalHold;
    private RetentionMode retentionMode;
    private ZonedDateTime retentionRetainUntilDate;
    private long size;
    private Map<String, String> userMetadata;

    public StatObjectResponse(Headers headers, String str, String str2, String str3) {
        super(headers, str, str2, str3);
        String str4 = headers.get("ETag");
        this.etag = str4 != null ? str4.replaceAll("\"", "") : "";
        String str5 = headers.get("Content-Length");
        this.size = str5 != null ? Long.parseLong(str5) : -1L;
        this.lastModified = ZonedDateTime.parse(headers.get("Last-Modified"), Time.HTTP_HEADER_DATE_FORMAT);
        String str6 = headers.get("x-amz-object-lock-mode");
        this.retentionMode = str6 != null ? RetentionMode.valueOf(str6) : null;
        String str7 = headers.get("x-amz-object-lock-retain-until-date");
        this.retentionRetainUntilDate = str7 != null ? ResponseDate.fromString(str7).zonedDateTime() : null;
        this.legalHold = new LegalHold("ON".equals(headers.get("x-amz-object-lock-legal-hold")));
        this.deleteMarker = Boolean.parseBoolean(headers.get("x-amz-delete-marker"));
        HashMap hashMap = new HashMap();
        for (String str8 : headers.names()) {
            if (str8.toLowerCase(Locale.US).startsWith(com.amazonaws.services.p048s3.Headers.S3_USER_METADATA_PREFIX)) {
                hashMap.put(str8.toLowerCase(Locale.US).substring(11, str8.length()), headers.get(str8));
            }
        }
        this.userMetadata = Collections.unmodifiableMap(hashMap);
    }

    public String etag() {
        return this.etag;
    }

    public long size() {
        return this.size;
    }

    public ZonedDateTime lastModified() {
        return this.lastModified;
    }

    public RetentionMode retentionMode() {
        return this.retentionMode;
    }

    public ZonedDateTime retentionRetainUntilDate() {
        return this.retentionRetainUntilDate;
    }

    public LegalHold legalHold() {
        return this.legalHold;
    }

    public boolean deleteMarker() {
        return this.deleteMarker;
    }

    public String versionId() {
        return headers().get(com.amazonaws.services.p048s3.Headers.S3_VERSION_ID);
    }

    public String contentType() {
        return headers().get("Content-Type");
    }

    public Map<String, String> userMetadata() {
        return this.userMetadata;
    }

    public String toString() {
        return "ObjectStat{bucket=" + bucket() + ", object=" + object() + ", last-modified=" + this.lastModified + ", size=" + this.size + "}";
    }
}
