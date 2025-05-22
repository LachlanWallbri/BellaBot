package com.danikula.videocache.file;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes.dex */
public abstract class LruDiskUsage implements DiskUsage {
    private static final Logger LOG = LoggerFactory.getLogger("LruDiskUsage");
    private final ExecutorService workerThread = Executors.newSingleThreadExecutor();

    protected abstract boolean accept(File file, long j, int i);

    @Override // com.danikula.videocache.file.DiskUsage
    public void touch(File file) throws IOException {
        this.workerThread.submit(new TouchCallable(file));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void touchInBackground(File file) throws IOException {
        Files.setLastModifiedNow(file);
        trim(Files.getLruListFiles(file.getParentFile()));
    }

    private void trim(List<File> list) {
        long countTotalSize = countTotalSize(list);
        int size = list.size();
        for (File file : list) {
            if (!accept(file, countTotalSize, size)) {
                long length = file.length();
                if (file.delete()) {
                    size--;
                    countTotalSize -= length;
                    LOG.info("Cache file " + file + " is deleted because it exceeds cache limit");
                } else {
                    LOG.error("Error deleting file " + file + " for trimming cache");
                }
            }
        }
    }

    private long countTotalSize(List<File> list) {
        Iterator<File> it = list.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += it.next().length();
        }
        return j;
    }

    /* loaded from: classes.dex */
    private class TouchCallable implements Callable<Void> {
        private final File file;

        public TouchCallable(File file) {
            this.file = file;
        }

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            LruDiskUsage.this.touchInBackground(this.file);
            return null;
        }
    }
}
