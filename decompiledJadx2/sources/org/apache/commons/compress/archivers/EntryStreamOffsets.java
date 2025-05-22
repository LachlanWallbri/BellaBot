package org.apache.commons.compress.archivers;

/* loaded from: classes7.dex */
public interface EntryStreamOffsets {
    public static final long OFFSET_UNKNOWN = -1;

    long getDataOffset();

    boolean isStreamContiguous();
}
