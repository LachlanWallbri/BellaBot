package io.minio;

import io.minio.BucketArgs;
import io.minio.messages.VersioningConfiguration;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SetBucketVersioningArgs extends BucketArgs {
    private VersioningConfiguration config;

    public VersioningConfiguration config() {
        return this.config;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, SetBucketVersioningArgs> {
        private void validateConfig(VersioningConfiguration versioningConfiguration) {
            validateNotNull(versioningConfiguration, "versioning configuration");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(SetBucketVersioningArgs setBucketVersioningArgs) {
            super.validate((Builder) setBucketVersioningArgs);
            validateConfig(setBucketVersioningArgs.config);
        }

        public Builder config(final VersioningConfiguration versioningConfiguration) {
            validateConfig(versioningConfiguration);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetBucketVersioningArgs$Builder$8bQzuOc5cjdClJjBXhvPRA0dxuo
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetBucketVersioningArgs) obj).config = VersioningConfiguration.this;
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
        if ((obj instanceof SetBucketVersioningArgs) && super.equals(obj)) {
            return Objects.equals(this.config, ((SetBucketVersioningArgs) obj).config);
        }
        return false;
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.config);
    }
}
