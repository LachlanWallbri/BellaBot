package io.minio;

import io.minio.ObjectConditionalReadArgs;

/* loaded from: classes7.dex */
public class CopySource extends ObjectConditionalReadArgs {

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectConditionalReadArgs.Builder<Builder, CopySource> {
    }

    protected CopySource() {
    }

    public CopySource(ObjectConditionalReadArgs objectConditionalReadArgs) {
        this.extraHeaders = objectConditionalReadArgs.extraHeaders;
        this.extraQueryParams = objectConditionalReadArgs.extraQueryParams;
        this.bucketName = objectConditionalReadArgs.bucketName;
        this.region = objectConditionalReadArgs.region;
        this.objectName = objectConditionalReadArgs.objectName;
        this.versionId = objectConditionalReadArgs.versionId;
        this.ssec = objectConditionalReadArgs.ssec;
        this.offset = objectConditionalReadArgs.offset;
        this.length = objectConditionalReadArgs.length;
        this.matchETag = objectConditionalReadArgs.matchETag;
        this.notMatchETag = objectConditionalReadArgs.notMatchETag;
        this.modifiedSince = objectConditionalReadArgs.modifiedSince;
        this.unmodifiedSince = objectConditionalReadArgs.unmodifiedSince;
    }

    public static Builder builder() {
        return new Builder();
    }
}
