package io.minio;

import io.minio.ObjectVersionArgs;
import io.minio.messages.Tags;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SetObjectTagsArgs extends ObjectVersionArgs {
    private Tags tags;

    public Tags tags() {
        return this.tags;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectVersionArgs.Builder<Builder, SetObjectTagsArgs> {
        private void validateTags(Tags tags) {
            validateNotNull(tags, "tags");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.ObjectArgs.Builder
        public void validate(SetObjectTagsArgs setObjectTagsArgs) {
            super.validate((Builder) setObjectTagsArgs);
            validateTags(setObjectTagsArgs.tags);
        }

        public Builder tags(final Map<String, String> map) {
            validateNotNull(map, "map for tags");
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetObjectTagsArgs$Builder$vn8dcPn00OpGOMm64TZFhkcj5uM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetObjectTagsArgs) obj).tags = Tags.newObjectTags(map);
                }
            });
            return this;
        }

        public Builder tags(final Tags tags) {
            validateTags(tags);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetObjectTagsArgs$Builder$k3hQ90holka0kYXsTKWGIkStRtw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetObjectTagsArgs) obj).tags = Tags.this;
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
        if ((obj instanceof SetObjectTagsArgs) && super.equals(obj)) {
            return Objects.equals(this.tags, ((SetObjectTagsArgs) obj).tags);
        }
        return false;
    }

    @Override // io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.tags);
    }
}
