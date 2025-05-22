package org.apache.commons.io;

import java.io.File;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class FileExistsException extends IOException {
    private static final long serialVersionUID = 1;

    public FileExistsException() {
    }

    public FileExistsException(String str) {
        super(str);
    }

    public FileExistsException(File file) {
        super("File " + file + " exists");
    }
}
