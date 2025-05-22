package com.amazonaws.services.p048s3;

import com.amazonaws.services.p048s3.internal.FileDeletionEvent;

/* loaded from: classes.dex */
public interface OnFileDelete {
    void onFileDelete(FileDeletionEvent fileDeletionEvent);
}
