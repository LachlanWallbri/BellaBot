package io.minio;

import io.minio.PutObjectBaseArgs;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class PutObjectArgs extends PutObjectBaseArgs {
    private BufferedInputStream stream;

    public BufferedInputStream stream() {
        return this.stream;
    }

    @Override // io.minio.PutObjectBaseArgs
    public String contentType() throws IOException {
        String contentType = super.contentType();
        return contentType != null ? contentType : "application/octet-stream";
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends PutObjectBaseArgs.Builder<Builder, PutObjectArgs> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.ObjectArgs.Builder
        public void validate(PutObjectArgs putObjectArgs) {
            super.validate((Builder) putObjectArgs);
            validateNotNull(putObjectArgs.stream, "stream");
        }

        public Builder stream(InputStream inputStream, long j, long j2) {
            BufferedInputStream bufferedInputStream;
            validateNotNull(inputStream, "stream");
            long[] partInfo = getPartInfo(j, j2);
            long j3 = partInfo[0];
            int i = (int) partInfo[1];
            if (inputStream instanceof BufferedInputStream) {
                bufferedInputStream = (BufferedInputStream) inputStream;
            } else {
                bufferedInputStream = new BufferedInputStream(inputStream);
            }
            return setStream(bufferedInputStream, j, j3, i);
        }

        private Builder setStream(final BufferedInputStream bufferedInputStream, final long j, final long j2, final int i) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$PutObjectArgs$Builder$7uJ4NFWakJ5FjzgAr8853cSZYZM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((PutObjectArgs) obj).stream = bufferedInputStream;
                }
            });
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$PutObjectArgs$Builder$jKsx4vDuPNU_KH8VjVSZLbui8wA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((PutObjectArgs) obj).objectSize = j;
                }
            });
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$PutObjectArgs$Builder$eUlyHhieT97o89Pb7hRW_fswKLo
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((PutObjectArgs) obj).partSize = j2;
                }
            });
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$PutObjectArgs$Builder$KPdZUHeCb0Ar5jvzmdO7ZeY0UXU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((PutObjectArgs) obj).partCount = i;
                }
            });
            return this;
        }

        public Builder contentType(final String str) {
            validateNotEmptyString(str, "content type");
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$PutObjectArgs$Builder$w2LrbiTRFn7vL_-RLON_7WBy0zU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((PutObjectArgs) obj).contentType = str;
                }
            });
            return this;
        }
    }

    @Override // io.minio.PutObjectBaseArgs, io.minio.ObjectWriteArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof PutObjectArgs) && super.equals(obj)) {
            return Objects.equals(this.stream, ((PutObjectArgs) obj).stream);
        }
        return false;
    }

    @Override // io.minio.PutObjectBaseArgs, io.minio.ObjectWriteArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.stream);
    }
}
