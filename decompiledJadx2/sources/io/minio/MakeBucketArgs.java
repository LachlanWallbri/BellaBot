package io.minio;

import io.minio.BucketArgs;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class MakeBucketArgs extends BucketArgs {
    private boolean objectLock;

    public boolean objectLock() {
        return this.objectLock;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, MakeBucketArgs> {
        public Builder objectLock(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$MakeBucketArgs$Builder$RJgdYI0g1Y6Y3-BWGYAGXuoetyU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((MakeBucketArgs) obj).objectLock = z;
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
        return (obj instanceof MakeBucketArgs) && super.equals(obj) && this.objectLock == ((MakeBucketArgs) obj).objectLock;
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), Boolean.valueOf(this.objectLock));
    }
}
