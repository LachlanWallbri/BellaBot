package io.minio;

import io.minio.BucketArgs;
import java.util.Objects;
import java.util.function.Consumer;
import okhttp3.HttpUrl;

/* loaded from: classes7.dex */
public abstract class ObjectArgs extends BucketArgs {
    protected String objectName;

    public String object() {
        return this.objectName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkSse(ServerSideEncryption serverSideEncryption, HttpUrl httpUrl) {
        if (serverSideEncryption == null || !serverSideEncryption.tlsRequired() || httpUrl.isHttps()) {
            return;
        }
        throw new IllegalArgumentException(serverSideEncryption + " operations must be performed over a secure connection.");
    }

    /* loaded from: classes7.dex */
    public static abstract class Builder<B extends Builder<B, A>, A extends ObjectArgs> extends BucketArgs.Builder<B, A> {
        protected void validateObjectName(String str) {
            validateNotEmptyString(str, "object name");
            for (String str2 : str.split("/")) {
                if (str2.equals(".") || str2.equals("..")) {
                    throw new IllegalArgumentException("object name with '.' or '..' path segment is not supported");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(A a) {
            super.validate((Builder<B, A>) a);
            validateObjectName(a.objectName);
        }

        public B object(final String str) {
            validateObjectName(str);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectArgs$Builder$Daxeb31A8P2-MFAgyzSo1LF30IM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectArgs) obj).objectName = str;
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
        if ((obj instanceof ObjectArgs) && super.equals(obj)) {
            return Objects.equals(this.objectName, ((ObjectArgs) obj).objectName);
        }
        return false;
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.objectName);
    }
}
