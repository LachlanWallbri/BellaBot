package io.opencensus.tags;

import io.opencensus.tags.TagMetadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Tag {
    private static final TagMetadata METADATA_UNLIMITED_PROPAGATION = TagMetadata.create(TagMetadata.TagTtl.UNLIMITED_PROPAGATION);

    public abstract TagKey getKey();

    public abstract TagMetadata getTagMetadata();

    public abstract TagValue getValue();

    @Deprecated
    public static Tag create(TagKey tagKey, TagValue tagValue) {
        return create(tagKey, tagValue, METADATA_UNLIMITED_PROPAGATION);
    }

    public static Tag create(TagKey tagKey, TagValue tagValue, TagMetadata tagMetadata) {
        return new AutoValue_Tag(tagKey, tagValue, tagMetadata);
    }
}
