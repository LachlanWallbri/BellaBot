package io.minio;

import io.minio.ObjectVersionArgs;
import java.util.Objects;
import java.util.function.Consumer;
import okhttp3.HttpUrl;

/* loaded from: classes7.dex */
public abstract class ObjectReadArgs extends ObjectVersionArgs {
    protected ServerSideEncryptionCustomerKey ssec;

    public ServerSideEncryptionCustomerKey ssec() {
        return this.ssec;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateSsec(HttpUrl httpUrl) {
        checkSse(this.ssec, httpUrl);
    }

    /* loaded from: classes7.dex */
    public static abstract class Builder<B extends Builder<B, A>, A extends ObjectReadArgs> extends ObjectVersionArgs.Builder<B, A> {
        public B ssec(final ServerSideEncryptionCustomerKey serverSideEncryptionCustomerKey) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectReadArgs$Builder$-nsKJZQjksWvAjAw5tDKKbWXK8k
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectReadArgs) obj).ssec = ServerSideEncryptionCustomerKey.this;
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
        if ((obj instanceof ObjectReadArgs) && super.equals(obj)) {
            return Objects.equals(this.ssec, ((ObjectReadArgs) obj).ssec);
        }
        return false;
    }

    @Override // io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.ssec);
    }
}
