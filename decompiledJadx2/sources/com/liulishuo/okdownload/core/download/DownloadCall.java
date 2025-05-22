package com.liulishuo.okdownload.core.download;

import android.os.SystemClock;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.NamedRunnable;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.download.DownloadCache;
import com.liulishuo.okdownload.core.file.ProcessFileStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class DownloadCall extends NamedRunnable implements Comparable<DownloadCall> {
    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkDownload Block", false));
    static final int MAX_COUNT_RETRY_FOR_PRECONDITION_FAILED = 1;
    private static final String TAG = "DownloadCall";
    public final boolean asyncExecuted;
    final ArrayList<DownloadChain> blockChainList;
    volatile DownloadCache cache;
    volatile boolean canceled;
    volatile Thread currentThread;
    volatile boolean finishing;
    private final DownloadStore store;
    public final DownloadTask task;

    @Override // com.liulishuo.okdownload.core.NamedRunnable
    protected void interrupted(InterruptedException interruptedException) {
    }

    private DownloadCall(DownloadTask downloadTask, boolean z, DownloadStore downloadStore) {
        this(downloadTask, z, new ArrayList(), downloadStore);
    }

    DownloadCall(DownloadTask downloadTask, boolean z, ArrayList<DownloadChain> arrayList, DownloadStore downloadStore) {
        super("download call: " + downloadTask.getId());
        this.task = downloadTask;
        this.asyncExecuted = z;
        this.blockChainList = arrayList;
        this.store = downloadStore;
    }

    public static DownloadCall create(DownloadTask downloadTask, boolean z, DownloadStore downloadStore) {
        return new DownloadCall(downloadTask, z, downloadStore);
    }

    public boolean cancel() {
        synchronized (this) {
            if (this.canceled) {
                return false;
            }
            if (this.finishing) {
                return false;
            }
            this.canceled = true;
            long uptimeMillis = SystemClock.uptimeMillis();
            OkDownload.with().downloadDispatcher().flyingCanceled(this);
            DownloadCache downloadCache = this.cache;
            if (downloadCache != null) {
                downloadCache.setUserCanceled();
            }
            Object[] array = this.blockChainList.toArray();
            if (array != null && array.length != 0) {
                for (Object obj : array) {
                    if (obj instanceof DownloadChain) {
                        ((DownloadChain) obj).cancel();
                    }
                }
            } else if (this.currentThread != null) {
                Util.m2347d(TAG, "interrupt thread with cancel operation because of chains are not running " + this.task.getId());
                this.currentThread.interrupt();
            }
            if (downloadCache != null) {
                downloadCache.getOutputStream().cancelAsync();
            }
            Util.m2347d(TAG, "cancel task " + this.task.getId() + " consume: " + (SystemClock.uptimeMillis() - uptimeMillis) + "ms");
            return true;
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public boolean isFinishing() {
        return this.finishing;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0149 A[LOOP:0: B:2:0x0013->B:32:0x0149, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x015e A[EDGE_INSN: B:33:0x015e->B:34:0x015e BREAK  A[LOOP:0: B:2:0x0013->B:32:0x0149], SYNTHETIC] */
    @Override // com.liulishuo.okdownload.core.NamedRunnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void execute() throws InterruptedException {
        EndCause endCause;
        int i;
        boolean z;
        this.currentThread = Thread.currentThread();
        OkDownload with = OkDownload.with();
        ProcessFileStrategy processFileStrategy = with.processFileStrategy();
        inspectTaskStart();
        int i2 = 0;
        while (true) {
            if (this.task.getUrl().length() <= 0) {
                this.cache = new DownloadCache.PreError(new IOException("unexpected url: " + this.task.getUrl()));
                break;
            }
            if (this.canceled) {
                break;
            }
            try {
                BreakpointInfo breakpointInfo = this.store.get(this.task.getId());
                if (breakpointInfo == null) {
                    breakpointInfo = this.store.createAndInsert(this.task);
                }
                setInfoToTask(breakpointInfo);
                if (this.canceled) {
                    break;
                }
                DownloadCache createCache = createCache(breakpointInfo);
                this.cache = createCache;
                BreakpointRemoteCheck createRemoteCheck = createRemoteCheck(breakpointInfo);
                try {
                    createRemoteCheck.check();
                    createCache.setRedirectLocation(this.task.getRedirectLocation());
                    processFileStrategy.getFileLock().waitForRelease(this.task.getFile().getAbsolutePath());
                    OkDownload.with().downloadStrategy().inspectAnotherSameInfo(this.task, breakpointInfo, createRemoteCheck.getInstanceLength());
                    try {
                        if (createRemoteCheck.isResumable()) {
                            BreakpointLocalCheck createLocalCheck = createLocalCheck(breakpointInfo, createRemoteCheck.getInstanceLength());
                            createLocalCheck.check();
                            if (createLocalCheck.isDirty()) {
                                Util.m2347d(TAG, "breakpoint invalid: download from beginning because of local check is dirty " + this.task.getId() + " " + createLocalCheck);
                                processFileStrategy.discardProcess(this.task);
                                assembleBlockAndCallbackFromBeginning(breakpointInfo, createRemoteCheck, createLocalCheck.getCauseOrThrow());
                            } else {
                                with.callbackDispatcher().dispatch().downloadFromBreakpoint(this.task, breakpointInfo);
                            }
                        } else {
                            Util.m2347d(TAG, "breakpoint invalid: download from beginning because of remote check not resumable " + this.task.getId() + " " + createRemoteCheck);
                            processFileStrategy.discardProcess(this.task);
                            assembleBlockAndCallbackFromBeginning(breakpointInfo, createRemoteCheck, createRemoteCheck.getCauseOrThrow());
                        }
                        start(createCache, breakpointInfo);
                        if (this.canceled) {
                            break;
                        }
                        if (createCache.isPreconditionFailed()) {
                            i = i2 + 1;
                            if (i2 < 1) {
                                this.store.remove(this.task.getId());
                                z = true;
                                if (z) {
                                    break;
                                } else {
                                    i2 = i;
                                }
                            } else {
                                i2 = i;
                            }
                        }
                        i = i2;
                        z = false;
                        if (z) {
                        }
                    } catch (IOException e) {
                        createCache.setUnknownError(e);
                    }
                } catch (IOException e2) {
                    createCache.catchException(e2);
                }
            } catch (IOException e3) {
                this.cache = new DownloadCache.PreError(e3);
            }
        }
        this.finishing = true;
        this.blockChainList.clear();
        DownloadCache downloadCache = this.cache;
        if (this.canceled || downloadCache == null) {
            return;
        }
        IOException iOException = null;
        if (downloadCache.isServerCanceled() || downloadCache.isUnknownError() || downloadCache.isPreconditionFailed()) {
            endCause = EndCause.ERROR;
            iOException = downloadCache.getRealCause();
        } else if (downloadCache.isFileBusyAfterRun()) {
            endCause = EndCause.FILE_BUSY;
        } else if (downloadCache.isPreAllocateFailed()) {
            endCause = EndCause.PRE_ALLOCATE_FAILED;
            iOException = downloadCache.getRealCause();
        } else {
            endCause = EndCause.COMPLETED;
        }
        inspectTaskEnd(downloadCache, endCause, iOException);
    }

    private void inspectTaskStart() {
        this.store.onTaskStart(this.task.getId());
        OkDownload.with().callbackDispatcher().dispatch().taskStart(this.task);
    }

    private void inspectTaskEnd(DownloadCache downloadCache, EndCause endCause, Exception exc) {
        if (endCause == EndCause.CANCELED) {
            throw new IllegalAccessError("can't recognize cancelled on here");
        }
        synchronized (this) {
            if (this.canceled) {
                return;
            }
            this.finishing = true;
            this.store.onTaskEnd(this.task.getId(), endCause, exc);
            if (endCause == EndCause.COMPLETED) {
                this.store.markFileClear(this.task.getId());
                OkDownload.with().processFileStrategy().completeProcessStream(downloadCache.getOutputStream(), this.task);
            }
            OkDownload.with().callbackDispatcher().dispatch().taskEnd(this.task, endCause, exc);
        }
    }

    DownloadCache createCache(BreakpointInfo breakpointInfo) {
        return new DownloadCache(OkDownload.with().processFileStrategy().createProcessStream(this.task, breakpointInfo, this.store));
    }

    int getPriority() {
        return this.task.getPriority();
    }

    void start(DownloadCache downloadCache, BreakpointInfo breakpointInfo) throws InterruptedException {
        int blockCount = breakpointInfo.getBlockCount();
        ArrayList arrayList = new ArrayList(breakpointInfo.getBlockCount());
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < blockCount; i++) {
            BlockInfo block = breakpointInfo.getBlock(i);
            if (!Util.isCorrectFull(block.getCurrentOffset(), block.getContentLength())) {
                Util.resetBlockIfDirty(block);
                DownloadChain createChain = DownloadChain.createChain(i, this.task, breakpointInfo, downloadCache, this.store);
                arrayList.add(createChain);
                arrayList2.add(Integer.valueOf(createChain.getBlockIndex()));
            }
        }
        if (this.canceled) {
            return;
        }
        downloadCache.getOutputStream().setRequireStreamBlocks(arrayList2);
        startBlocks(arrayList);
    }

    @Override // com.liulishuo.okdownload.core.NamedRunnable
    protected void finished() {
        OkDownload.with().downloadDispatcher().finish(this);
        Util.m2347d(TAG, "call is finished " + this.task.getId());
    }

    void startBlocks(List<DownloadChain> list) throws InterruptedException {
        ArrayList arrayList = new ArrayList(list.size());
        try {
            Iterator<DownloadChain> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(submitChain(it.next()));
            }
            this.blockChainList.addAll(list);
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                Future future = (Future) it2.next();
                if (!future.isDone()) {
                    try {
                        future.get();
                    } catch (CancellationException | ExecutionException unused) {
                    }
                }
            }
        } finally {
        }
    }

    BreakpointLocalCheck createLocalCheck(BreakpointInfo breakpointInfo, long j) {
        return new BreakpointLocalCheck(this.task, breakpointInfo, j);
    }

    BreakpointRemoteCheck createRemoteCheck(BreakpointInfo breakpointInfo) {
        return new BreakpointRemoteCheck(this.task, breakpointInfo);
    }

    void setInfoToTask(BreakpointInfo breakpointInfo) {
        DownloadTask.TaskHideWrapper.setBreakpointInfo(this.task, breakpointInfo);
    }

    void assembleBlockAndCallbackFromBeginning(BreakpointInfo breakpointInfo, BreakpointRemoteCheck breakpointRemoteCheck, ResumeFailedCause resumeFailedCause) {
        Util.assembleBlock(this.task, breakpointInfo, breakpointRemoteCheck.getInstanceLength(), breakpointRemoteCheck.isAcceptRange());
        OkDownload.with().callbackDispatcher().dispatch().downloadFromBeginning(this.task, breakpointInfo, resumeFailedCause);
    }

    Future<?> submitChain(DownloadChain downloadChain) {
        return EXECUTOR.submit(downloadChain);
    }

    public boolean equalsTask(DownloadTask downloadTask) {
        return this.task.equals(downloadTask);
    }

    public File getFile() {
        return this.task.getFile();
    }

    @Override // java.lang.Comparable
    public int compareTo(DownloadCall downloadCall) {
        return downloadCall.getPriority() - getPriority();
    }
}
