package io.minio.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/* loaded from: classes7.dex */
public class ObjectMetadata {

    @JsonProperty
    private String eTag;

    @JsonProperty
    private String key;

    @JsonProperty
    private String sequencer;

    @JsonProperty
    private long size;

    @JsonProperty
    private Map<String, String> userMetadata;

    @JsonProperty
    private String versionId;

    public String key() {
        return this.key;
    }

    public long size() {
        return this.size;
    }

    public String etag() {
        return this.eTag;
    }

    public String versionId() {
        return this.versionId;
    }

    public String sequencer() {
        return this.sequencer;
    }

    public Map<String, String> userMetadata() {
        return this.userMetadata;
    }
}
