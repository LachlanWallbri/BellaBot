package io.perfmark;

import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public class Impl {
    private static final long NO_LINK_ID = Long.MIN_VALUE;
    static final long NO_TAG_ID = Long.MIN_VALUE;
    static final String NO_TAG_NAME = "";
    static final Tag NO_TAG = new Tag("", Long.MIN_VALUE);
    static final Link NO_LINK = new Link(Long.MIN_VALUE);

    /* JADX INFO: Access modifiers changed from: protected */
    public void attachTag(Tag tag) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void attachTag(String str, long j) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void attachTag(String str, long j, long j2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> void attachTag(String str, T t, StringFunction<? super T> stringFunction) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void attachTag(String str, String str2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void event(String str) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void event(String str, Tag tag) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void event(String str, String str2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void linkIn(Link link) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setEnabled(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> void startTask(T t, StringFunction<? super T> stringFunction) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startTask(String str) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startTask(String str, Tag tag) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startTask(String str, String str2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stopTask() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stopTask(String str) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stopTask(String str, Tag tag) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stopTask(String str, String str2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Impl(Tag tag) {
        if (tag != NO_TAG) {
            throw new AssertionError("nope");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Link linkOut() {
        return NO_LINK;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Tag createTag(@Nullable String str, long j) {
        return NO_TAG;
    }

    @Nullable
    protected static String unpackTagName(Tag tag) {
        return tag.tagName;
    }

    protected static long unpackTagId(Tag tag) {
        return tag.tagId;
    }

    protected static long unpackLinkId(Link link) {
        return link.linkId;
    }

    protected static Tag packTag(@Nullable String str, long j) {
        return new Tag(str, j);
    }

    protected static Link packLink(long j) {
        return new Link(j);
    }
}
