package org.apache.commons.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public abstract class DirectoryWalker<T> {
    private final int depthLimit;
    private final FileFilter filter;

    protected File[] filterDirectoryContents(File file, int i, File[] fileArr) throws IOException {
        return fileArr;
    }

    protected boolean handleDirectory(File file, int i, Collection<T> collection) throws IOException {
        return true;
    }

    protected void handleDirectoryEnd(File file, int i, Collection<T> collection) throws IOException {
    }

    protected void handleDirectoryStart(File file, int i, Collection<T> collection) throws IOException {
    }

    protected void handleEnd(Collection<T> collection) throws IOException {
    }

    protected void handleFile(File file, int i, Collection<T> collection) throws IOException {
    }

    protected boolean handleIsCancelled(File file, int i, Collection<T> collection) throws IOException {
        return false;
    }

    protected void handleRestricted(File file, int i, Collection<T> collection) throws IOException {
    }

    protected void handleStart(File file, Collection<T> collection) throws IOException {
    }

    protected DirectoryWalker() {
        this(null, -1);
    }

    protected DirectoryWalker(FileFilter fileFilter, int i) {
        this.filter = fileFilter;
        this.depthLimit = i;
    }

    protected DirectoryWalker(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2, int i) {
        if (iOFileFilter == null && iOFileFilter2 == null) {
            this.filter = null;
        } else {
            this.filter = FileFilterUtils.m4000or(FileFilterUtils.makeDirectoryOnly(iOFileFilter == null ? TrueFileFilter.TRUE : iOFileFilter), FileFilterUtils.makeFileOnly(iOFileFilter2 == null ? TrueFileFilter.TRUE : iOFileFilter2));
        }
        this.depthLimit = i;
    }

    protected final void walk(File file, Collection<T> collection) throws IOException {
        if (file == null) {
            throw new NullPointerException("Start Directory is null");
        }
        try {
            handleStart(file, collection);
            walk(file, 0, collection);
            handleEnd(collection);
        } catch (CancelException e) {
            handleCancelled(file, collection, e);
        }
    }

    private void walk(File file, int i, Collection<T> collection) throws IOException {
        checkIfCancelled(file, i, collection);
        if (handleDirectory(file, i, collection)) {
            handleDirectoryStart(file, i, collection);
            int i2 = i + 1;
            int i3 = this.depthLimit;
            if (i3 < 0 || i2 <= i3) {
                checkIfCancelled(file, i, collection);
                FileFilter fileFilter = this.filter;
                File[] filterDirectoryContents = filterDirectoryContents(file, i, fileFilter == null ? file.listFiles() : file.listFiles(fileFilter));
                if (filterDirectoryContents == null) {
                    handleRestricted(file, i2, collection);
                } else {
                    for (File file2 : filterDirectoryContents) {
                        if (file2.isDirectory()) {
                            walk(file2, i2, collection);
                        } else {
                            checkIfCancelled(file2, i2, collection);
                            handleFile(file2, i2, collection);
                            checkIfCancelled(file2, i2, collection);
                        }
                    }
                }
            }
            handleDirectoryEnd(file, i, collection);
        }
        checkIfCancelled(file, i, collection);
    }

    protected final void checkIfCancelled(File file, int i, Collection<T> collection) throws IOException {
        if (handleIsCancelled(file, i, collection)) {
            throw new CancelException(file, i);
        }
    }

    protected void handleCancelled(File file, Collection<T> collection, CancelException cancelException) throws IOException {
        throw cancelException;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* loaded from: classes2.dex */
    public static class CancelException extends IOException {
        private static final long serialVersionUID = 1347339620135041008L;
        private final int depth;
        private final File file;

        public CancelException(File file, int i) {
            this("Operation Cancelled", file, i);
        }

        public CancelException(String str, File file, int i) {
            super(str);
            this.file = file;
            this.depth = i;
        }

        public File getFile() {
            return this.file;
        }

        public int getDepth() {
            return this.depth;
        }
    }
}
