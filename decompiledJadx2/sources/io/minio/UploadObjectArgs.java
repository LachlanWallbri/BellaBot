package io.minio;

import io.minio.PutObjectBaseArgs;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class UploadObjectArgs extends PutObjectBaseArgs {
    private String filename;

    public String filename() {
        return this.filename;
    }

    @Override // io.minio.PutObjectBaseArgs
    public String contentType() throws IOException {
        String contentType = super.contentType();
        if (contentType != null) {
            return contentType;
        }
        String probeContentType = Files.probeContentType(Paths.get(this.filename, new String[0]));
        return (probeContentType == null || probeContentType.isEmpty()) ? "application/octet-stream" : probeContentType;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends PutObjectBaseArgs.Builder<Builder, UploadObjectArgs> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.ObjectArgs.Builder
        public void validate(UploadObjectArgs uploadObjectArgs) {
            super.validate((Builder) uploadObjectArgs);
            validateFilename(uploadObjectArgs.filename);
        }

        private void validateFilename(String str) {
            validateNotEmptyString(str, "filename");
            if (Files.isRegularFile(Paths.get(str, new String[0]), new LinkOption[0])) {
                return;
            }
            throw new IllegalArgumentException(str + " not a regular file");
        }

        public Builder filename(final String str, long j) throws IOException {
            validateFilename(str);
            final long size = Files.size(Paths.get(str, new String[0]));
            long[] partInfo = getPartInfo(size, j);
            final long j2 = partInfo[0];
            final int i = (int) partInfo[1];
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$UploadObjectArgs$Builder$WEBh5KLWe-xluFmTgJMgj_wGT5Q
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((UploadObjectArgs) obj).filename = str;
                }
            });
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$UploadObjectArgs$Builder$dp8K2CuqKdVcYOpj5eXQJWvu_zg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((UploadObjectArgs) obj).objectSize = size;
                }
            });
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$UploadObjectArgs$Builder$I_jcz1m-TrnzwXwCzM6SvWdMCm8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((UploadObjectArgs) obj).partSize = j2;
                }
            });
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$UploadObjectArgs$Builder$2myS-pWOsnPZcx6VpmUswgpeaAA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((UploadObjectArgs) obj).partCount = i;
                }
            });
            return this;
        }

        public Builder filename(String str) throws IOException {
            return filename(str, 0L);
        }

        public Builder contentType(final String str) {
            validateNotEmptyString(str, "content type");
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$UploadObjectArgs$Builder$WJZ4UJ8_BS5tV0DxmMsguG0o358
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((UploadObjectArgs) obj).contentType = str;
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
        if ((obj instanceof UploadObjectArgs) && super.equals(obj)) {
            return Objects.equals(this.filename, ((UploadObjectArgs) obj).filename);
        }
        return false;
    }

    @Override // io.minio.PutObjectBaseArgs, io.minio.ObjectWriteArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.filename);
    }
}
