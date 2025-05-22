package io.minio;

import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import io.minio.BaseArgs;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public abstract class BucketArgs extends BaseArgs {
    protected String bucketName;
    protected String region;

    public String bucket() {
        return this.bucketName;
    }

    public String region() {
        return this.region;
    }

    /* loaded from: classes7.dex */
    public static abstract class Builder<B extends Builder<B, A>, A extends BucketArgs> extends BaseArgs.Builder<B, A> {
        protected void validateBucketName(String str) {
            validateNotNull(str, "bucket name");
            if (str.length() < 3 || str.length() > 63) {
                throw new IllegalArgumentException(str + " : bucket name must be at least 3 and no more than 63 characters long");
            }
            if (str.contains("..")) {
                throw new IllegalArgumentException(str + " : bucket name cannot contain successive periods. For more information refer http://docs.aws.amazon.com/AmazonS3/latest/dev/BucketRestrictions.html");
            }
            if (str.matches("^[a-z0-9][a-z0-9\\.\\-]+[a-z0-9]$")) {
                return;
            }
            throw new IllegalArgumentException(str + " : bucket name does not follow Amazon S3 standards. For more information refer http://docs.aws.amazon.com/AmazonS3/latest/dev/BucketRestrictions.html");
        }

        private void validateRegion(String str) {
            validateNullOrNotEmptyString(str, OSSConfig.PARAM_REGION);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BaseArgs.Builder
        public void validate(A a) {
            validateBucketName(a.bucketName);
        }

        public B bucket(final String str) {
            validateBucketName(str);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$BucketArgs$Builder$zblwoQpFFM9E-G87YFs408xpKss
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((BucketArgs) obj).bucketName = str;
                }
            });
            return this;
        }

        public B region(final String str) {
            validateRegion(str);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$BucketArgs$Builder$_NU2ZyiQBhcWciJShs0azH5DJx8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((BucketArgs) obj).region = str;
                }
            });
            return this;
        }
    }

    @Override // io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BucketArgs) || !super.equals(obj)) {
            return false;
        }
        BucketArgs bucketArgs = (BucketArgs) obj;
        return Objects.equals(this.bucketName, bucketArgs.bucketName) && Objects.equals(this.region, bucketArgs.region);
    }

    @Override // io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.bucketName, this.region);
    }
}
