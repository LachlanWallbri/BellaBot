package com.amazonaws.services.p048s3.internal;

import com.amazonaws.services.p048s3.OnFileDelete;
import java.io.File;

/* loaded from: classes.dex */
public class PartCreationEvent {
    private final OnFileDelete fileDeleteObserver;
    private final boolean isLastPart;
    private final File part;
    private final int partNumber;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PartCreationEvent(File file, int i, boolean z, OnFileDelete onFileDelete) {
        if (file == null) {
            throw new IllegalArgumentException("part must not be specified");
        }
        this.part = file;
        this.partNumber = i;
        this.isLastPart = z;
        this.fileDeleteObserver = onFileDelete;
    }

    public File getPart() {
        return this.part;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public boolean isLastPart() {
        return this.isLastPart;
    }

    public OnFileDelete getFileDeleteObserver() {
        return this.fileDeleteObserver;
    }
}
