package io.minio;

import io.minio.ObjectWriteArgs;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public abstract class PutObjectBaseArgs extends ObjectWriteArgs {
    protected String contentType;
    protected long objectSize;
    protected int partCount;
    protected long partSize;
    protected boolean preloadData;

    public long objectSize() {
        return this.objectSize;
    }

    public long partSize() {
        return this.partSize;
    }

    public int partCount() {
        return this.partCount;
    }

    public String contentType() throws IOException {
        String str = this.contentType;
        if (str != null) {
            return str;
        }
        if (headers().containsKey("Content-Type")) {
            return headers().get("Content-Type").iterator().next();
        }
        return null;
    }

    public boolean preloadData() {
        return this.preloadData;
    }

    /* loaded from: classes7.dex */
    public static abstract class Builder<B extends Builder<B, A>, A extends PutObjectBaseArgs> extends ObjectWriteArgs.Builder<B, A> {
        private void validateSizes(long j, long j2) {
            if (j2 > 0) {
                if (j2 < 5242880) {
                    throw new IllegalArgumentException("part size " + j2 + " is not supported; minimum allowed 5MiB");
                }
                if (j2 > 5368709120L) {
                    throw new IllegalArgumentException("part size " + j2 + " is not supported; maximum allowed 5GiB");
                }
            }
            if (j < 0) {
                if (j2 <= 0) {
                    throw new IllegalArgumentException("valid part size must be provided when object size is unknown");
                }
            } else {
                if (j <= ObjectWriteArgs.MAX_OBJECT_SIZE) {
                    return;
                }
                throw new IllegalArgumentException("object size " + j + " is not supported; maximum allowed 5TiB");
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public long[] getPartInfo(long j, long j2) {
            validateSizes(j, j2);
            if (j < 0) {
                return new long[]{j2, -1};
            }
            if (j2 <= 0) {
                j2 = (long) (Math.ceil(Math.ceil(j / 10000.0d) / 5242880.0d) * 5242880.0d);
            }
            if (j2 > j) {
                j2 = j;
            }
            long ceil = j2 > 0 ? (long) Math.ceil(j / j2) : 1L;
            if (ceil <= 10000) {
                return new long[]{j2, ceil};
            }
            throw new IllegalArgumentException("object size " + j + " and part size " + j2 + " make more than 10000parts for upload");
        }

        @Deprecated
        public B preloadData(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$PutObjectBaseArgs$Builder$Uo4-jGRjKRPr6nJIB_fMsacEZ9Y
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((PutObjectBaseArgs) obj).preloadData = z;
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
        if (!(obj instanceof PutObjectBaseArgs) || !super.equals(obj)) {
            return false;
        }
        PutObjectBaseArgs putObjectBaseArgs = (PutObjectBaseArgs) obj;
        return this.objectSize == putObjectBaseArgs.objectSize && this.partSize == putObjectBaseArgs.partSize && this.partCount == putObjectBaseArgs.partCount && Objects.equals(this.contentType, putObjectBaseArgs.contentType) && this.preloadData == putObjectBaseArgs.preloadData;
    }

    @Override // io.minio.ObjectWriteArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), Long.valueOf(this.objectSize), Long.valueOf(this.partSize), Integer.valueOf(this.partCount), this.contentType, Boolean.valueOf(this.preloadData));
    }
}
