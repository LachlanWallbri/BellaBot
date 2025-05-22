package org.apache.commons.io.input;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class Tailer implements Runnable {
    private static final int DEFAULT_BUFSIZE = 4096;
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    private static final int DEFAULT_DELAY_MILLIS = 1000;
    private static final String RAF_MODE = "r";
    private final Charset cset;
    private final long delayMillis;
    private final boolean end;
    private final File file;
    private final byte[] inbuf;
    private final TailerListener listener;
    private final boolean reOpen;
    private volatile boolean run;

    public Tailer(File file, TailerListener tailerListener) {
        this(file, tailerListener, 1000L);
    }

    public Tailer(File file, TailerListener tailerListener, long j) {
        this(file, tailerListener, j, false);
    }

    public Tailer(File file, TailerListener tailerListener, long j, boolean z) {
        this(file, tailerListener, j, z, 4096);
    }

    public Tailer(File file, TailerListener tailerListener, long j, boolean z, boolean z2) {
        this(file, tailerListener, j, z, z2, 4096);
    }

    public Tailer(File file, TailerListener tailerListener, long j, boolean z, int i) {
        this(file, tailerListener, j, z, false, i);
    }

    public Tailer(File file, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        this(file, DEFAULT_CHARSET, tailerListener, j, z, z2, i);
    }

    public Tailer(File file, Charset charset, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        this.run = true;
        this.file = file;
        this.delayMillis = j;
        this.end = z;
        this.inbuf = new byte[i];
        this.listener = tailerListener;
        tailerListener.init(this);
        this.reOpen = z2;
        this.cset = charset;
    }

    public static Tailer create(File file, TailerListener tailerListener, long j, boolean z, int i) {
        return create(file, tailerListener, j, z, false, i);
    }

    public static Tailer create(File file, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        return create(file, DEFAULT_CHARSET, tailerListener, j, z, z2, i);
    }

    public static Tailer create(File file, Charset charset, TailerListener tailerListener, long j, boolean z, boolean z2, int i) {
        Tailer tailer = new Tailer(file, charset, tailerListener, j, z, z2, i);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        return tailer;
    }

    public static Tailer create(File file, TailerListener tailerListener, long j, boolean z) {
        return create(file, tailerListener, j, z, 4096);
    }

    public static Tailer create(File file, TailerListener tailerListener, long j, boolean z, boolean z2) {
        return create(file, tailerListener, j, z, z2, 4096);
    }

    public static Tailer create(File file, TailerListener tailerListener, long j) {
        return create(file, tailerListener, j, false);
    }

    public static Tailer create(File file, TailerListener tailerListener) {
        return create(file, tailerListener, 1000L, false);
    }

    public File getFile() {
        return this.file;
    }

    protected boolean getRun() {
        return this.run;
    }

    public long getDelay() {
        return this.delayMillis;
    }

    @Override // java.lang.Runnable
    public void run() {
        RandomAccessFile randomAccessFile;
        long readLines;
        long lastModified;
        RandomAccessFile randomAccessFile2 = null;
        long j = 0;
        long j2 = 0;
        while (getRun() && randomAccessFile2 == null) {
            try {
                try {
                    try {
                        randomAccessFile2 = new RandomAccessFile(this.file, "r");
                    } catch (FileNotFoundException unused) {
                        this.listener.fileNotFound();
                    }
                    if (randomAccessFile2 == null) {
                        Thread.sleep(this.delayMillis);
                    } else {
                        j2 = this.end ? this.file.length() : 0L;
                        j = this.file.lastModified();
                        randomAccessFile2.seek(j2);
                    }
                } catch (InterruptedException e) {
                    e = e;
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
        }
        while (getRun()) {
            boolean isFileNewer = FileUtils.isFileNewer(this.file, j);
            long length = this.file.length();
            if (length < j2) {
                this.listener.fileRotated();
                try {
                    randomAccessFile = new RandomAccessFile(this.file, "r");
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile = randomAccessFile2;
                }
                try {
                    try {
                        readLines(randomAccessFile2);
                    } catch (IOException e3) {
                        this.listener.handle(e3);
                    }
                    if (randomAccessFile2 != null) {
                        try {
                            try {
                                randomAccessFile2.close();
                            } catch (InterruptedException e4) {
                                e = e4;
                                randomAccessFile2 = randomAccessFile;
                                Thread.currentThread().interrupt();
                                this.listener.handle(e);
                                if (randomAccessFile2 != null) {
                                    try {
                                        randomAccessFile2.close();
                                    } catch (IOException e5) {
                                        e = e5;
                                        this.listener.handle(e);
                                        stop();
                                    }
                                }
                                stop();
                            } catch (Exception e6) {
                                e = e6;
                                randomAccessFile2 = randomAccessFile;
                                this.listener.handle(e);
                                if (randomAccessFile2 != null) {
                                    try {
                                        randomAccessFile2.close();
                                    } catch (IOException e7) {
                                        e = e7;
                                        this.listener.handle(e);
                                        stop();
                                    }
                                }
                                stop();
                            } catch (Throwable th3) {
                                th = th3;
                                randomAccessFile2 = randomAccessFile;
                                if (randomAccessFile2 != null) {
                                    try {
                                        randomAccessFile2.close();
                                    } catch (IOException e8) {
                                        this.listener.handle(e8);
                                    }
                                }
                                stop();
                                throw th;
                            }
                        } catch (FileNotFoundException unused2) {
                            j2 = 0;
                            randomAccessFile2 = randomAccessFile;
                            this.listener.fileNotFound();
                            Thread.sleep(this.delayMillis);
                        }
                    }
                    j2 = 0;
                    randomAccessFile2 = randomAccessFile;
                } catch (Throwable th4) {
                    th = th4;
                    try {
                        throw th;
                    } catch (Throwable th5) {
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (Throwable th6) {
                                try {
                                    th.addSuppressed(th6);
                                } catch (FileNotFoundException unused3) {
                                    randomAccessFile2 = randomAccessFile;
                                    this.listener.fileNotFound();
                                    Thread.sleep(this.delayMillis);
                                }
                            }
                        }
                        throw th5;
                        break;
                    }
                }
            } else {
                if (length > j2) {
                    readLines = readLines(randomAccessFile2);
                    lastModified = this.file.lastModified();
                } else {
                    if (isFileNewer) {
                        randomAccessFile2.seek(0L);
                        readLines = readLines(randomAccessFile2);
                        lastModified = this.file.lastModified();
                    }
                    if (this.reOpen && randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    Thread.sleep(this.delayMillis);
                    if (getRun() && this.reOpen) {
                        randomAccessFile = new RandomAccessFile(this.file, "r");
                        randomAccessFile.seek(j2);
                        randomAccessFile2 = randomAccessFile;
                    }
                }
                long j3 = readLines;
                j = lastModified;
                j2 = j3;
                if (this.reOpen) {
                    randomAccessFile2.close();
                }
                Thread.sleep(this.delayMillis);
                if (getRun()) {
                    randomAccessFile = new RandomAccessFile(this.file, "r");
                    randomAccessFile.seek(j2);
                    randomAccessFile2 = randomAccessFile;
                }
            }
        }
        if (randomAccessFile2 != null) {
            try {
                randomAccessFile2.close();
            } catch (IOException e9) {
                e = e9;
                this.listener.handle(e);
                stop();
            }
        }
        stop();
    }

    public void stop() {
        this.run = false;
    }

    private long readLines(RandomAccessFile randomAccessFile) throws IOException {
        int read;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(64);
        try {
            long filePointer = randomAccessFile.getFilePointer();
            long j = filePointer;
            boolean z = false;
            while (getRun() && (read = randomAccessFile.read(this.inbuf)) != -1) {
                for (int i = 0; i < read; i++) {
                    byte b = this.inbuf[i];
                    if (b == 10) {
                        this.listener.handle(new String(byteArrayOutputStream.toByteArray(), this.cset));
                        byteArrayOutputStream.reset();
                        filePointer = i + j + 1;
                        z = false;
                    } else if (b != 13) {
                        if (z) {
                            this.listener.handle(new String(byteArrayOutputStream.toByteArray(), this.cset));
                            byteArrayOutputStream.reset();
                            filePointer = i + j + 1;
                            z = false;
                        }
                        byteArrayOutputStream.write(b);
                    } else {
                        if (z) {
                            byteArrayOutputStream.write(13);
                        }
                        z = true;
                    }
                }
                j = randomAccessFile.getFilePointer();
            }
            randomAccessFile.seek(filePointer);
            if (this.listener instanceof TailerListenerAdapter) {
                ((TailerListenerAdapter) this.listener).endOfFileReached();
            }
            byteArrayOutputStream.close();
            return filePointer;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }
}
