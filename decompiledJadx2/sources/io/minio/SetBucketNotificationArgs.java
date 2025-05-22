package io.minio;

import io.minio.BucketArgs;
import io.minio.messages.NotificationConfiguration;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SetBucketNotificationArgs extends BucketArgs {
    private NotificationConfiguration config;

    public NotificationConfiguration config() {
        return this.config;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, SetBucketNotificationArgs> {
        private void validateConfig(NotificationConfiguration notificationConfiguration) {
            validateNotNull(notificationConfiguration, "notification configuration");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(SetBucketNotificationArgs setBucketNotificationArgs) {
            super.validate((Builder) setBucketNotificationArgs);
            validateConfig(setBucketNotificationArgs.config);
        }

        public Builder config(final NotificationConfiguration notificationConfiguration) {
            validateConfig(notificationConfiguration);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetBucketNotificationArgs$Builder$GhcGqC3_8jdjTDB3NgaCEGuHTZc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetBucketNotificationArgs) obj).config = NotificationConfiguration.this;
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
        if ((obj instanceof SetBucketNotificationArgs) && super.equals(obj)) {
            return Objects.equals(this.config, ((SetBucketNotificationArgs) obj).config);
        }
        return false;
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.config);
    }
}
