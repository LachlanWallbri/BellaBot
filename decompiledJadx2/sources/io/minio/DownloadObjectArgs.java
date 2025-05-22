package io.minio;

import io.minio.ObjectReadArgs;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class DownloadObjectArgs extends ObjectReadArgs {
    private String filename;

    public String filename() {
        return this.filename;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectReadArgs.Builder<Builder, DownloadObjectArgs> {
        public Builder filename(final String str) {
            validateFilename(str);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$DownloadObjectArgs$Builder$aIw1__0Jo1PlAnGMCOO0vvSyqzM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((DownloadObjectArgs) obj).filename = str;
                }
            });
            return this;
        }

        private void validateFilename(String str) {
            validateNotEmptyString(str, "filename");
            if (Files.exists(Paths.get(str, new String[0]), new LinkOption[0])) {
                throw new IllegalArgumentException("Destination file " + str + " already exists");
            }
        }
    }

    @Override // io.minio.ObjectReadArgs, io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof DownloadObjectArgs) && super.equals(obj)) {
            return Objects.equals(this.filename, ((DownloadObjectArgs) obj).filename);
        }
        return false;
    }

    @Override // io.minio.ObjectReadArgs, io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.filename);
    }
}
