package io.minio;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import io.minio.BucketArgs;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class ListenBucketNotificationArgs extends BucketArgs {
    private String[] events = null;
    private String prefix;
    private String suffix;

    public String prefix() {
        return this.prefix;
    }

    public String suffix() {
        return this.suffix;
    }

    public String[] events() {
        String[] strArr = this.events;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, ListenBucketNotificationArgs> {
        private void validateEvents(String[] strArr) {
            validateNotNull(strArr, TmpConstant.DEVICE_MODEL_EVENTS);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(ListenBucketNotificationArgs listenBucketNotificationArgs) {
            if (listenBucketNotificationArgs.bucketName != null) {
                super.validate((Builder) listenBucketNotificationArgs);
            }
            validateEvents(listenBucketNotificationArgs.events);
        }

        public Builder prefix(final String str) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListenBucketNotificationArgs$Builder$8LQVOReDkUrF11UNwjINnHkmFE0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListenBucketNotificationArgs) obj).prefix = str;
                }
            });
            return this;
        }

        public Builder suffix(final String str) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListenBucketNotificationArgs$Builder$DFNZZ4odojoHyP2yHACVs4s_mtI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListenBucketNotificationArgs) obj).suffix = str;
                }
            });
            return this;
        }

        public Builder events(String[] strArr) {
            validateEvents(strArr);
            final String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListenBucketNotificationArgs$Builder$7QGPM9JjpH-EqNt4cgIL3LKRHZE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListenBucketNotificationArgs) obj).events = strArr2;
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
        if (!(obj instanceof ListenBucketNotificationArgs) || !super.equals(obj)) {
            return false;
        }
        ListenBucketNotificationArgs listenBucketNotificationArgs = (ListenBucketNotificationArgs) obj;
        return Objects.equals(this.prefix, listenBucketNotificationArgs.prefix) && Objects.equals(this.suffix, listenBucketNotificationArgs.suffix) && Arrays.equals(this.events, listenBucketNotificationArgs.events);
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.prefix, this.suffix, this.events);
    }
}
