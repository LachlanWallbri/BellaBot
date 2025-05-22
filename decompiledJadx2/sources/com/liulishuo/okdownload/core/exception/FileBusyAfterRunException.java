package com.liulishuo.okdownload.core.exception;

import java.io.IOException;

/* loaded from: classes4.dex */
public class FileBusyAfterRunException extends IOException {
    public static final FileBusyAfterRunException SIGNAL = new FileBusyAfterRunException() { // from class: com.liulishuo.okdownload.core.exception.FileBusyAfterRunException.1
    };

    private FileBusyAfterRunException() {
        super("File busy after run");
    }
}
