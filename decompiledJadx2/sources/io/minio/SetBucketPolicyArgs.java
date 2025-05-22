package io.minio;

import io.minio.BucketArgs;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SetBucketPolicyArgs extends BucketArgs {
    private String config;

    public String config() {
        return this.config;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, SetBucketPolicyArgs> {
        private void validateConfig(String str) {
            validateNotNull(str, "policy configuration");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(SetBucketPolicyArgs setBucketPolicyArgs) {
            super.validate((Builder) setBucketPolicyArgs);
            validateConfig(setBucketPolicyArgs.config);
        }

        public Builder config(final String str) {
            validateConfig(str);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetBucketPolicyArgs$Builder$9qjMUvo7D4TGEAhn04X_XN-sklc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetBucketPolicyArgs) obj).config = str;
                }
            });
            return this;
        }
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof SetBucketPolicyArgs) && super.equals(obj)) {
            return Objects.equals(this.config, ((SetBucketPolicyArgs) obj).config);
        }
        return false;
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.config);
    }
}
