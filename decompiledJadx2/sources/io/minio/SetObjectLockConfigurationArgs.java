package io.minio;

import io.minio.BucketArgs;
import io.minio.messages.ObjectLockConfiguration;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SetObjectLockConfigurationArgs extends BucketArgs {
    private ObjectLockConfiguration config;

    public ObjectLockConfiguration config() {
        return this.config;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, SetObjectLockConfigurationArgs> {
        private void validateConfig(ObjectLockConfiguration objectLockConfiguration) {
            validateNotNull(objectLockConfiguration, "object-lock configuration");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(SetObjectLockConfigurationArgs setObjectLockConfigurationArgs) {
            super.validate((Builder) setObjectLockConfigurationArgs);
            validateConfig(setObjectLockConfigurationArgs.config);
        }

        public Builder config(final ObjectLockConfiguration objectLockConfiguration) {
            validateConfig(objectLockConfiguration);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetObjectLockConfigurationArgs$Builder$FOUgUQe82G4zcVppD0DKfXMAzEE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetObjectLockConfigurationArgs) obj).config = ObjectLockConfiguration.this;
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
        if ((obj instanceof SetObjectLockConfigurationArgs) && super.equals(obj)) {
            return Objects.equals(this.config, ((SetObjectLockConfigurationArgs) obj).config);
        }
        return false;
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.config);
    }
}
