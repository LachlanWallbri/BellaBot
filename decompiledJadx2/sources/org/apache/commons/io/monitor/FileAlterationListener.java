package org.apache.commons.io.monitor;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public interface FileAlterationListener {
    void onDirectoryChange(File file);

    void onDirectoryCreate(File file);

    void onDirectoryDelete(File file);

    void onFileChange(File file);

    void onFileCreate(File file);

    void onFileDelete(File file);

    void onStart(FileAlterationObserver fileAlterationObserver);

    void onStop(FileAlterationObserver fileAlterationObserver);
}
