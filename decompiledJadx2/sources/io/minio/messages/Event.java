package io.minio.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.Map;

/* loaded from: classes7.dex */
public class Event {

    @JsonProperty
    private String awsRegion;

    @JsonProperty
    private EventType eventName;

    @JsonProperty
    private String eventSource;

    @JsonProperty
    private ResponseDate eventTime;

    @JsonProperty
    private String eventVersion;

    @JsonProperty
    private Map<String, String> requestParameters;

    @JsonProperty
    private Map<String, String> responseElements;

    /* renamed from: s3 */
    @JsonProperty
    private EventMetadata f8438s3;

    @JsonProperty
    private Source source;

    @JsonProperty
    private Identity userIdentity;

    public String region() {
        return this.awsRegion;
    }

    public ZonedDateTime eventTime() {
        return this.eventTime.zonedDateTime();
    }

    public EventType eventType() {
        return this.eventName;
    }

    public String userId() {
        Identity identity = this.userIdentity;
        if (identity == null) {
            return null;
        }
        return identity.principalId();
    }

    public Map<String, String> requestParameters() {
        return this.requestParameters;
    }

    public Map<String, String> responseElements() {
        return this.responseElements;
    }

    public String bucketName() {
        EventMetadata eventMetadata = this.f8438s3;
        if (eventMetadata == null) {
            return null;
        }
        return eventMetadata.bucketName();
    }

    public String bucketOwner() {
        EventMetadata eventMetadata = this.f8438s3;
        if (eventMetadata == null) {
            return null;
        }
        return eventMetadata.bucketOwner();
    }

    public String bucketArn() {
        EventMetadata eventMetadata = this.f8438s3;
        if (eventMetadata == null) {
            return null;
        }
        return eventMetadata.bucketArn();
    }

    public String objectName() {
        EventMetadata eventMetadata = this.f8438s3;
        if (eventMetadata == null) {
            return null;
        }
        return eventMetadata.objectName();
    }

    public long objectSize() {
        EventMetadata eventMetadata = this.f8438s3;
        if (eventMetadata == null) {
            return -1L;
        }
        return eventMetadata.objectSize();
    }

    public String etag() {
        EventMetadata eventMetadata = this.f8438s3;
        if (eventMetadata == null) {
            return null;
        }
        return eventMetadata.etag();
    }

    public String objectVersionId() {
        EventMetadata eventMetadata = this.f8438s3;
        if (eventMetadata == null) {
            return null;
        }
        return eventMetadata.objectVersionId();
    }

    public String sequencer() {
        EventMetadata eventMetadata = this.f8438s3;
        if (eventMetadata == null) {
            return null;
        }
        return eventMetadata.sequencer();
    }

    public Map<String, String> userMetadata() {
        EventMetadata eventMetadata = this.f8438s3;
        if (eventMetadata == null) {
            return null;
        }
        return eventMetadata.userMetadata();
    }

    public String host() {
        Source source = this.source;
        if (source == null) {
            return null;
        }
        return source.host();
    }

    public String port() {
        Source source = this.source;
        if (source == null) {
            return null;
        }
        return source.port();
    }

    public String userAgent() {
        Source source = this.source;
        if (source == null) {
            return null;
        }
        return source.userAgent();
    }
}
