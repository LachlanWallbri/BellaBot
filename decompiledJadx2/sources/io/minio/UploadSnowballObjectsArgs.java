package io.minio;

import io.minio.ObjectWriteArgs;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class UploadSnowballObjectsArgs extends ObjectWriteArgs {
    private static final Random random = new Random(new SecureRandom().nextLong());
    private boolean compression;
    private Iterable<SnowballObject> objects;
    private String stagingFilename;

    public Iterable<SnowballObject> objects() {
        return this.objects;
    }

    public String stagingFilename() {
        return this.stagingFilename;
    }

    public boolean compression() {
        return this.compression;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectWriteArgs.Builder<Builder, UploadSnowballObjectsArgs> {
        private void validateObjects(Iterable<SnowballObject> iterable) {
            validateNotNull(iterable, "objects");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.ObjectArgs.Builder
        public void validate(UploadSnowballObjectsArgs uploadSnowballObjectsArgs) {
            uploadSnowballObjectsArgs.objectName = "snowball." + UploadSnowballObjectsArgs.random.nextLong() + ".tar";
            validateObjects(uploadSnowballObjectsArgs.objects);
            super.validate((Builder) uploadSnowballObjectsArgs);
        }

        public Builder objects(final Iterable<SnowballObject> iterable) {
            validateObjects(iterable);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$UploadSnowballObjectsArgs$Builder$l7cd8dBSe2Jpjca_PX-R70eAc2o
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((UploadSnowballObjectsArgs) obj).objects = iterable;
                }
            });
            return this;
        }

        public Builder stagingFilename(final String str) {
            if (str != null && str.isEmpty()) {
                throw new IllegalArgumentException("staging filename must not be empty");
            }
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$UploadSnowballObjectsArgs$Builder$vchdSMaz8NNDOHQfU7sRxxB8Exg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((UploadSnowballObjectsArgs) obj).stagingFilename = str;
                }
            });
            return this;
        }

        public Builder compression(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$UploadSnowballObjectsArgs$Builder$SQkay0Jn8roUA_Ib0jEb36VHyMc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((UploadSnowballObjectsArgs) obj).compression = z;
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
        if (!(obj instanceof UploadSnowballObjectsArgs) || !super.equals(obj)) {
            return false;
        }
        UploadSnowballObjectsArgs uploadSnowballObjectsArgs = (UploadSnowballObjectsArgs) obj;
        return Objects.equals(this.objects, uploadSnowballObjectsArgs.objects) && Objects.equals(this.stagingFilename, uploadSnowballObjectsArgs.stagingFilename) && this.compression == uploadSnowballObjectsArgs.compression;
    }

    @Override // io.minio.ObjectWriteArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.objects, this.stagingFilename, Boolean.valueOf(this.compression));
    }
}
