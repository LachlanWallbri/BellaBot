package io.minio;

import io.minio.ObjectWriteArgs;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import okhttp3.HttpUrl;

/* loaded from: classes7.dex */
public class ComposeObjectArgs extends ObjectWriteArgs {
    List<ComposeSource> sources;

    protected ComposeObjectArgs() {
    }

    public ComposeObjectArgs(CopyObjectArgs copyObjectArgs) {
        this.extraHeaders = copyObjectArgs.extraHeaders;
        this.extraQueryParams = copyObjectArgs.extraQueryParams;
        this.bucketName = copyObjectArgs.bucketName;
        this.region = copyObjectArgs.region;
        this.objectName = copyObjectArgs.objectName;
        this.headers = copyObjectArgs.headers;
        this.userMetadata = copyObjectArgs.userMetadata;
        this.sse = copyObjectArgs.sse;
        this.tags = copyObjectArgs.tags;
        this.retention = copyObjectArgs.retention;
        this.legalHold = copyObjectArgs.legalHold;
        this.sources = new LinkedList();
        this.sources.add(new ComposeSource(copyObjectArgs.source()));
    }

    public List<ComposeSource> sources() {
        return this.sources;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // io.minio.ObjectWriteArgs
    public void validateSse(HttpUrl httpUrl) {
        super.validateSse(httpUrl);
        Iterator<ComposeSource> it = this.sources.iterator();
        while (it.hasNext()) {
            it.next().validateSsec(httpUrl);
        }
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectWriteArgs.Builder<Builder, ComposeObjectArgs> {
        private void validateSources(List<ComposeSource> list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("compose sources cannot be empty");
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.ObjectArgs.Builder
        public void validate(ComposeObjectArgs composeObjectArgs) {
            super.validate((Builder) composeObjectArgs);
            validateSources(composeObjectArgs.sources);
        }

        public Builder sources(final List<ComposeSource> list) {
            validateSources(list);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ComposeObjectArgs$Builder$pxCoJHaHCSC1zzA9juqAydseBVg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ComposeObjectArgs) obj).sources = list;
                }
            });
            return this;
        }
    }

    @Override // io.minio.ObjectWriteArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ComposeObjectArgs) && super.equals(obj)) {
            return Objects.equals(this.sources, ((ComposeObjectArgs) obj).sources);
        }
        return false;
    }

    @Override // io.minio.ObjectWriteArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.sources);
    }
}
