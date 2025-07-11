package org.apache.commons.io.filefilter;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public abstract class AbstractFileFilter implements IOFileFilter {
    @Override // org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return accept(file.getParentFile(), file.getName());
    }

    @Override // org.apache.commons.io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return accept(new File(file, str));
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
