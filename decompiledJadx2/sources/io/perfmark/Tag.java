package io.perfmark;

import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class Tag {
    final long tagId;

    @Nullable
    final String tagName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Tag(@Nullable String str, long j) {
        this.tagName = str;
        this.tagId = j;
    }
}
