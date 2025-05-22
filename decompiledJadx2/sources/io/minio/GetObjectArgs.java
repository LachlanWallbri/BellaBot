package io.minio;

import io.minio.ObjectConditionalReadArgs;

/* loaded from: classes7.dex */
public class GetObjectArgs extends ObjectConditionalReadArgs {

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectConditionalReadArgs.Builder<Builder, GetObjectArgs> {
    }

    protected GetObjectArgs() {
    }

    public GetObjectArgs(DownloadObjectArgs downloadObjectArgs) {
        this.extraHeaders = downloadObjectArgs.extraHeaders;
        this.extraQueryParams = downloadObjectArgs.extraQueryParams;
        this.bucketName = downloadObjectArgs.bucketName;
        this.region = downloadObjectArgs.region;
        this.objectName = downloadObjectArgs.objectName;
        this.versionId = downloadObjectArgs.versionId;
        this.ssec = downloadObjectArgs.ssec;
    }

    public static Builder builder() {
        return new Builder();
    }
}
