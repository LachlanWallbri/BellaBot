package org.apache.commons.io;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
@Deprecated
/* loaded from: classes2.dex */
public class FileCleaner {
    static final FileCleaningTracker theInstance = new FileCleaningTracker();

    @Deprecated
    public static void track(File file, Object obj) {
        theInstance.track(file, obj);
    }

    @Deprecated
    public static void track(File file, Object obj, FileDeleteStrategy fileDeleteStrategy) {
        theInstance.track(file, obj, fileDeleteStrategy);
    }

    @Deprecated
    public static void track(String str, Object obj) {
        theInstance.track(str, obj);
    }

    @Deprecated
    public static void track(String str, Object obj, FileDeleteStrategy fileDeleteStrategy) {
        theInstance.track(str, obj, fileDeleteStrategy);
    }

    @Deprecated
    public static int getTrackCount() {
        return theInstance.getTrackCount();
    }

    @Deprecated
    public static synchronized void exitWhenFinished() {
        synchronized (FileCleaner.class) {
            theInstance.exitWhenFinished();
        }
    }

    public static FileCleaningTracker getInstance() {
        return theInstance;
    }
}
