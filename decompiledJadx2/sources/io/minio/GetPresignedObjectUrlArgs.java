package io.minio;

import io.minio.ObjectVersionArgs;
import io.minio.http.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class GetPresignedObjectUrlArgs extends ObjectVersionArgs {
    public static final int DEFAULT_EXPIRY_TIME = (int) TimeUnit.DAYS.toSeconds(7);
    private int expiry = DEFAULT_EXPIRY_TIME;
    private Method method;

    public Method method() {
        return this.method;
    }

    public int expiry() {
        return this.expiry;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectVersionArgs.Builder<Builder, GetPresignedObjectUrlArgs> {
        private void validateMethod(Method method) {
            validateNotNull(method, "method");
        }

        private void validateExpiry(int i) {
            if (i < 1 || i > GetPresignedObjectUrlArgs.DEFAULT_EXPIRY_TIME) {
                throw new IllegalArgumentException("expiry must be minimum 1 second to maximum " + TimeUnit.SECONDS.toDays(GetPresignedObjectUrlArgs.DEFAULT_EXPIRY_TIME) + " days");
            }
        }

        public Builder method(final Method method) {
            validateMethod(method);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$GetPresignedObjectUrlArgs$Builder$QeqR7sxxId9l85PcdB_bTyEYWmw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((GetPresignedObjectUrlArgs) obj).method = Method.this;
                }
            });
            return this;
        }

        public Builder expiry(final int i) {
            validateExpiry(i);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$GetPresignedObjectUrlArgs$Builder$SXM3926Fujh9UYJIJMEJloOghwQ
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((GetPresignedObjectUrlArgs) obj).expiry = i;
                }
            });
            return this;
        }

        public Builder expiry(int i, TimeUnit timeUnit) {
            return expiry((int) timeUnit.toSeconds(i));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.ObjectArgs.Builder
        public void validate(GetPresignedObjectUrlArgs getPresignedObjectUrlArgs) {
            super.validate((Builder) getPresignedObjectUrlArgs);
            validateMethod(getPresignedObjectUrlArgs.method);
        }
    }

    @Override // io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetPresignedObjectUrlArgs) || !super.equals(obj)) {
            return false;
        }
        GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = (GetPresignedObjectUrlArgs) obj;
        return this.expiry == getPresignedObjectUrlArgs.expiry && this.method == getPresignedObjectUrlArgs.method;
    }

    @Override // io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.method, Integer.valueOf(this.expiry));
    }
}
