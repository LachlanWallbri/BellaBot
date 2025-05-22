package io.minio;

import io.minio.BucketArgs;
import io.minio.messages.LifecycleConfiguration;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SetBucketLifecycleArgs extends BucketArgs {
    private LifecycleConfiguration config;

    public LifecycleConfiguration config() {
        return this.config;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, SetBucketLifecycleArgs> {
        private void validateConfig(LifecycleConfiguration lifecycleConfiguration) {
            validateNotNull(lifecycleConfiguration, "lifecycle configuration");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(SetBucketLifecycleArgs setBucketLifecycleArgs) {
            super.validate((Builder) setBucketLifecycleArgs);
            validateConfig(setBucketLifecycleArgs.config);
        }

        public Builder config(final LifecycleConfiguration lifecycleConfiguration) {
            validateConfig(lifecycleConfiguration);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetBucketLifecycleArgs$Builder$6hmA2erMj8U0bIwtShP7BUcJE1I
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetBucketLifecycleArgs) obj).config = LifecycleConfiguration.this;
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
        if ((obj instanceof SetBucketLifecycleArgs) && super.equals(obj)) {
            return Objects.equals(this.config, ((SetBucketLifecycleArgs) obj).config);
        }
        return false;
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.config);
    }
}
