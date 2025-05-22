package io.minio;

import io.minio.ObjectConditionalReadArgs;

/* loaded from: classes7.dex */
public class StatObjectArgs extends ObjectConditionalReadArgs {

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectConditionalReadArgs.Builder<Builder, StatObjectArgs> {
    }

    protected StatObjectArgs() {
    }

    public StatObjectArgs(ObjectReadArgs objectReadArgs) {
        this.extraHeaders = objectReadArgs.extraHeaders;
        this.extraQueryParams = objectReadArgs.extraQueryParams;
        this.bucketName = objectReadArgs.bucketName;
        this.region = objectReadArgs.region;
        this.objectName = objectReadArgs.objectName;
        this.versionId = objectReadArgs.versionId;
        this.ssec = objectReadArgs.ssec;
    }

    public static Builder builder() {
        return new Builder();
    }
}
