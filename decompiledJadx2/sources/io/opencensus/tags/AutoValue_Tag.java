package io.opencensus.tags;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_Tag extends Tag {
    private final TagKey key;
    private final TagMetadata tagMetadata;
    private final TagValue value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Tag(TagKey tagKey, TagValue tagValue, TagMetadata tagMetadata) {
        if (tagKey == null) {
            throw new NullPointerException("Null key");
        }
        this.key = tagKey;
        if (tagValue == null) {
            throw new NullPointerException("Null value");
        }
        this.value = tagValue;
        if (tagMetadata == null) {
            throw new NullPointerException("Null tagMetadata");
        }
        this.tagMetadata = tagMetadata;
    }

    @Override // io.opencensus.tags.Tag
    public TagKey getKey() {
        return this.key;
    }

    @Override // io.opencensus.tags.Tag
    public TagValue getValue() {
        return this.value;
    }

    @Override // io.opencensus.tags.Tag
    public TagMetadata getTagMetadata() {
        return this.tagMetadata;
    }

    public String toString() {
        return "Tag{key=" + this.key + ", value=" + this.value + ", tagMetadata=" + this.tagMetadata + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) obj;
        return this.key.equals(tag.getKey()) && this.value.equals(tag.getValue()) && this.tagMetadata.equals(tag.getTagMetadata());
    }

    public int hashCode() {
        return ((((this.key.hashCode() ^ 1000003) * 1000003) ^ this.value.hashCode()) * 1000003) ^ this.tagMetadata.hashCode();
    }
}
