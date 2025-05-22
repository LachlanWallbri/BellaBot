package io.minio.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/* loaded from: classes7.dex */
public class EventMetadata {

    @JsonProperty
    private BucketMetadata bucket;

    @JsonProperty
    private String configurationId;

    @JsonProperty
    private ObjectMetadata object;

    @JsonProperty
    private String s3SchemaVersion;

    public String bucketName() {
        BucketMetadata bucketMetadata = this.bucket;
        if (bucketMetadata == null) {
            return null;
        }
        return bucketMetadata.name();
    }

    public String bucketOwner() {
        BucketMetadata bucketMetadata = this.bucket;
        if (bucketMetadata == null) {
            return null;
        }
        return bucketMetadata.owner();
    }

    public String bucketArn() {
        BucketMetadata bucketMetadata = this.bucket;
        if (bucketMetadata == null) {
            return null;
        }
        return bucketMetadata.arn();
    }

    public String objectName() {
        ObjectMetadata objectMetadata = this.object;
        if (objectMetadata == null) {
            return null;
        }
        return objectMetadata.key();
    }

    public long objectSize() {
        ObjectMetadata objectMetadata = this.object;
        if (objectMetadata == null) {
            return -1L;
        }
        return objectMetadata.size();
    }

    public String etag() {
        ObjectMetadata objectMetadata = this.object;
        if (objectMetadata == null) {
            return null;
        }
        return objectMetadata.etag();
    }

    public String objectVersionId() {
        ObjectMetadata objectMetadata = this.object;
        if (objectMetadata == null) {
            return null;
        }
        return objectMetadata.versionId();
    }

    public String sequencer() {
        ObjectMetadata objectMetadata = this.object;
        if (objectMetadata == null) {
            return null;
        }
        return objectMetadata.sequencer();
    }

    public Map<String, String> userMetadata() {
        ObjectMetadata objectMetadata = this.object;
        if (objectMetadata == null) {
            return null;
        }
        return objectMetadata.userMetadata();
    }
}
