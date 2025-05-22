package io.minio;

import io.minio.BucketArgs;
import io.minio.messages.DeleteObject;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class RemoveObjectsArgs extends BucketArgs {
    private boolean bypassGovernanceMode;
    private Iterable<DeleteObject> objects = new LinkedList();

    public boolean bypassGovernanceMode() {
        return this.bypassGovernanceMode;
    }

    public Iterable<DeleteObject> objects() {
        return this.objects;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, RemoveObjectsArgs> {
        public Builder bypassGovernanceMode(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$RemoveObjectsArgs$Builder$GpESVnmH5cLXswGtAy4P9h84j2s
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((RemoveObjectsArgs) obj).bypassGovernanceMode = z;
                }
            });
            return this;
        }

        public Builder objects(final Iterable<DeleteObject> iterable) {
            validateNotNull(iterable, "objects");
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$RemoveObjectsArgs$Builder$SuYjV22VH3F9svNC4PXJAuNGBrA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((RemoveObjectsArgs) obj).objects = iterable;
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
        if (!(obj instanceof RemoveObjectsArgs) || !super.equals(obj)) {
            return false;
        }
        RemoveObjectsArgs removeObjectsArgs = (RemoveObjectsArgs) obj;
        return this.bypassGovernanceMode == removeObjectsArgs.bypassGovernanceMode && Objects.equals(this.objects, removeObjectsArgs.objects);
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), Boolean.valueOf(this.bypassGovernanceMode), this.objects);
    }
}
