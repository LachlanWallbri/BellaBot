package io.minio;

import io.minio.BucketArgs;
import io.minio.messages.Tags;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SetBucketTagsArgs extends BucketArgs {
    private Tags tags;

    public Tags tags() {
        return this.tags;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, SetBucketTagsArgs> {
        private void validateTags(Tags tags) {
            validateNotNull(tags, "tags");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(SetBucketTagsArgs setBucketTagsArgs) {
            super.validate((Builder) setBucketTagsArgs);
            validateTags(setBucketTagsArgs.tags);
        }

        public Builder tags(final Map<String, String> map) {
            validateNotNull(map, "map for tags");
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetBucketTagsArgs$Builder$V75vG7sNF_KnUTxhFfzgV49Uq4c
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetBucketTagsArgs) obj).tags = Tags.newBucketTags(map);
                }
            });
            return this;
        }

        public Builder tags(final Tags tags) {
            validateTags(tags);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetBucketTagsArgs$Builder$ilHTuHg57wWcIRu4Y1l9QQubQLs
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetBucketTagsArgs) obj).tags = Tags.this;
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
        if ((obj instanceof SetBucketTagsArgs) && super.equals(obj)) {
            return Objects.equals(this.tags, ((SetBucketTagsArgs) obj).tags);
        }
        return false;
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.tags);
    }
}
