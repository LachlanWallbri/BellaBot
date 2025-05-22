package io.minio;

import io.minio.ObjectArgs;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public abstract class ObjectVersionArgs extends ObjectArgs {
    protected String versionId;

    public String versionId() {
        return this.versionId;
    }

    /* loaded from: classes7.dex */
    public static abstract class Builder<B extends Builder<B, A>, A extends ObjectVersionArgs> extends ObjectArgs.Builder<B, A> {
        public B versionId(final String str) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectVersionArgs$Builder$6aTSIGqqS-FBnwSsyH021lPcSvY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectVersionArgs) obj).versionId = str;
                }
            });
            return this;
        }
    }

    @Override // io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ObjectVersionArgs) && super.equals(obj)) {
            return Objects.equals(this.versionId, ((ObjectVersionArgs) obj).versionId);
        }
        return false;
    }

    @Override // io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.versionId);
    }
}
