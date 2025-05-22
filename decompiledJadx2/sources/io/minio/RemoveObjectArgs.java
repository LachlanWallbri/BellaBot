package io.minio;

import io.minio.ObjectVersionArgs;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class RemoveObjectArgs extends ObjectVersionArgs {
    private boolean bypassGovernanceMode;

    public boolean bypassGovernanceMode() {
        return this.bypassGovernanceMode;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectVersionArgs.Builder<Builder, RemoveObjectArgs> {
        public Builder bypassGovernanceMode(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$RemoveObjectArgs$Builder$Xq4F_oq2ZNyIJAhwr2s4g6CrE1w
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((RemoveObjectArgs) obj).bypassGovernanceMode = z;
                }
            });
            return this;
        }
    }

    @Override // io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RemoveObjectArgs) && super.equals(obj) && this.bypassGovernanceMode == ((RemoveObjectArgs) obj).bypassGovernanceMode;
    }

    @Override // io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), Boolean.valueOf(this.bypassGovernanceMode));
    }
}
