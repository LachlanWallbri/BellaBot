package io.minio;

import io.minio.ObjectWriteArgs;
import java.util.Objects;
import java.util.function.Consumer;
import okhttp3.HttpUrl;

/* loaded from: classes7.dex */
public class CopyObjectArgs extends ObjectWriteArgs {
    private Directive metadataDirective;
    private CopySource source;
    private Directive taggingDirective;

    protected CopyObjectArgs() {
        this.source = null;
    }

    public CopyObjectArgs(ComposeObjectArgs composeObjectArgs) {
        this.source = null;
        this.extraHeaders = composeObjectArgs.extraHeaders;
        this.extraQueryParams = composeObjectArgs.extraQueryParams;
        this.bucketName = composeObjectArgs.bucketName;
        this.region = composeObjectArgs.region;
        this.objectName = composeObjectArgs.objectName;
        this.headers = composeObjectArgs.headers;
        this.userMetadata = composeObjectArgs.userMetadata;
        this.sse = composeObjectArgs.sse;
        this.tags = composeObjectArgs.tags;
        this.retention = composeObjectArgs.retention;
        this.legalHold = composeObjectArgs.legalHold;
        this.source = new CopySource(composeObjectArgs.sources().get(0));
    }

    public CopySource source() {
        return this.source;
    }

    public Directive metadataDirective() {
        return this.metadataDirective;
    }

    public Directive taggingDirective() {
        return this.taggingDirective;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // io.minio.ObjectWriteArgs
    public void validateSse(HttpUrl httpUrl) {
        super.validateSse(httpUrl);
        this.source.validateSsec(httpUrl);
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectWriteArgs.Builder<Builder, CopyObjectArgs> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.ObjectArgs.Builder
        public void validate(CopyObjectArgs copyObjectArgs) {
            super.validate((Builder) copyObjectArgs);
            validateNotNull(copyObjectArgs.source, "copy source");
            if (copyObjectArgs.source.offset() == null && copyObjectArgs.source.length() == null) {
                return;
            }
            if (copyObjectArgs.metadataDirective == null || copyObjectArgs.metadataDirective != Directive.COPY) {
                if (copyObjectArgs.taggingDirective != null && copyObjectArgs.taggingDirective == Directive.COPY) {
                    throw new IllegalArgumentException("COPY tagging directive is not applicable to source object with range");
                }
                return;
            }
            throw new IllegalArgumentException("COPY metadata directive is not applicable to source object with range");
        }

        public Builder source(final CopySource copySource) {
            validateNotNull(copySource, "copy source");
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$CopyObjectArgs$Builder$PKzzl9rC2viyNtCmuaWhe6XKMcM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((CopyObjectArgs) obj).source = CopySource.this;
                }
            });
            return this;
        }

        public Builder metadataDirective(final Directive directive) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$CopyObjectArgs$Builder$5jlYgJwed5TvgGgh1YkoAtw-4Y0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((CopyObjectArgs) obj).metadataDirective = Directive.this;
                }
            });
            return this;
        }

        public Builder taggingDirective(final Directive directive) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$CopyObjectArgs$Builder$WPLhe0v0dQFT2PlCVpGV7b5b6CY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((CopyObjectArgs) obj).taggingDirective = Directive.this;
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
        if (!(obj instanceof CopyObjectArgs) || !super.equals(obj)) {
            return false;
        }
        CopyObjectArgs copyObjectArgs = (CopyObjectArgs) obj;
        return Objects.equals(this.source, copyObjectArgs.source) && this.metadataDirective == copyObjectArgs.metadataDirective && this.taggingDirective == copyObjectArgs.taggingDirective;
    }

    @Override // io.minio.ObjectWriteArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.source, this.metadataDirective, this.taggingDirective);
    }
}
