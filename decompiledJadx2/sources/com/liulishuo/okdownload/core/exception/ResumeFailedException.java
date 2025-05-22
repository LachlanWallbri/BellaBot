package com.liulishuo.okdownload.core.exception;

import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import java.io.IOException;

/* loaded from: classes4.dex */
public class ResumeFailedException extends IOException {
    private final ResumeFailedCause resumeFailedCause;

    public ResumeFailedException(ResumeFailedCause resumeFailedCause) {
        super("Resume failed because of " + resumeFailedCause);
        this.resumeFailedCause = resumeFailedCause;
    }

    public ResumeFailedCause getResumeFailedCause() {
        return this.resumeFailedCause;
    }
}
