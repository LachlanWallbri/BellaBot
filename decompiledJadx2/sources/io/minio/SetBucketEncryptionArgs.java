package io.minio;

import io.minio.BucketArgs;
import io.minio.messages.SseConfiguration;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SetBucketEncryptionArgs extends BucketArgs {
    private SseConfiguration config;

    public SseConfiguration config() {
        return this.config;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, SetBucketEncryptionArgs> {
        private void validateConfig(SseConfiguration sseConfiguration) {
            validateNotNull(sseConfiguration, "encryption configuration");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(SetBucketEncryptionArgs setBucketEncryptionArgs) {
            super.validate((Builder) setBucketEncryptionArgs);
            validateConfig(setBucketEncryptionArgs.config);
        }

        public Builder config(final SseConfiguration sseConfiguration) {
            validateConfig(sseConfiguration);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetBucketEncryptionArgs$Builder$iBV3SqACYYSyIGOJ42FB9UMVE8M
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetBucketEncryptionArgs) obj).config = SseConfiguration.this;
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
        if ((obj instanceof SetBucketEncryptionArgs) && super.equals(obj)) {
            return Objects.equals(this.config, ((SetBucketEncryptionArgs) obj).config);
        }
        return false;
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.config);
    }
}
