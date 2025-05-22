package com.liulishuo.okdownload.core.dispatcher;

import android.os.SystemClock;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.IdentifiedTask;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.download.DownloadCall;
import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class DownloadDispatcher {
    private static final String TAG = "DownloadDispatcher";
    private volatile ExecutorService executorService;
    private final List<DownloadCall> finishingCalls;
    private final AtomicInteger flyingCanceledAsyncCallCount;
    int maxParallelRunningCount;
    private final List<DownloadCall> readyAsyncCalls;
    private final List<DownloadCall> runningAsyncCalls;
    private final List<DownloadCall> runningSyncCalls;
    private final AtomicInteger skipProceedCallCount;
    private DownloadStore store;

    public DownloadDispatcher() {
        this(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList());
    }

    DownloadDispatcher(List<DownloadCall> list, List<DownloadCall> list2, List<DownloadCall> list3, List<DownloadCall> list4) {
        this.maxParallelRunningCount = 5;
        this.flyingCanceledAsyncCallCount = new AtomicInteger();
        this.skipProceedCallCount = new AtomicInteger();
        this.readyAsyncCalls = list;
        this.runningAsyncCalls = list2;
        this.runningSyncCalls = list3;
        this.finishingCalls = list4;
    }

    public void setDownloadStore(DownloadStore downloadStore) {
        this.store = downloadStore;
    }

    synchronized ExecutorService getExecutorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkDownload Download", false));
        }
        return this.executorService;
    }

    public void enqueue(DownloadTask[] downloadTaskArr) {
        this.skipProceedCallCount.incrementAndGet();
        enqueueLocked(downloadTaskArr);
        this.skipProceedCallCount.decrementAndGet();
    }

    public void enqueue(DownloadTask downloadTask) {
        this.skipProceedCallCount.incrementAndGet();
        enqueueLocked(downloadTask);
        this.skipProceedCallCount.decrementAndGet();
    }

    private synchronized void enqueueLocked(DownloadTask[] downloadTaskArr) {
        long uptimeMillis = SystemClock.uptimeMillis();
        Util.m2347d(TAG, "start enqueueLocked for bunch task: " + downloadTaskArr.length);
        ArrayList<DownloadTask> arrayList = new ArrayList();
        Collections.addAll(arrayList, downloadTaskArr);
        if (arrayList.size() > 1) {
            Collections.sort(arrayList);
        }
        int size = this.readyAsyncCalls.size();
        try {
            OkDownload.with().downloadStrategy().inspectNetworkAvailable();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (DownloadTask downloadTask : arrayList) {
                if (!inspectCompleted(downloadTask, arrayList2) && !inspectForConflict(downloadTask, arrayList3, arrayList4)) {
                    enqueueIgnorePriority(downloadTask);
                }
            }
            OkDownload.with().callbackDispatcher().endTasks(arrayList2, arrayList3, arrayList4);
        } catch (UnknownHostException e) {
            OkDownload.with().callbackDispatcher().endTasksWithError(new ArrayList(arrayList), e);
        }
        if (size != this.readyAsyncCalls.size()) {
            Collections.sort(this.readyAsyncCalls);
        }
        Util.m2347d(TAG, "end enqueueLocked for bunch task: " + downloadTaskArr.length + " consume " + (SystemClock.uptimeMillis() - uptimeMillis) + "ms");
    }

    private synchronized void enqueueLocked(DownloadTask downloadTask) {
        Util.m2347d(TAG, "enqueueLocked for single task: " + downloadTask);
        if (inspectCompleted(downloadTask)) {
            return;
        }
        if (inspectForConflict(downloadTask)) {
            return;
        }
        int size = this.readyAsyncCalls.size();
        enqueueIgnorePriority(downloadTask);
        if (size != this.readyAsyncCalls.size()) {
            Collections.sort(this.readyAsyncCalls);
        }
    }

    private synchronized void enqueueIgnorePriority(DownloadTask downloadTask) {
        DownloadCall create = DownloadCall.create(downloadTask, true, this.store);
        if (runningAsyncSize() < this.maxParallelRunningCount) {
            this.runningAsyncCalls.add(create);
            getExecutorService().execute(create);
        } else {
            this.readyAsyncCalls.add(create);
        }
    }

    public void execute(DownloadTask downloadTask) {
        Util.m2347d(TAG, "execute: " + downloadTask);
        synchronized (this) {
            if (inspectCompleted(downloadTask)) {
                return;
            }
            if (inspectForConflict(downloadTask)) {
                return;
            }
            DownloadCall create = DownloadCall.create(downloadTask, false, this.store);
            this.runningSyncCalls.add(create);
            syncRunCall(create);
        }
    }

    public void cancelAll() {
        this.skipProceedCallCount.incrementAndGet();
        ArrayList arrayList = new ArrayList();
        Iterator<DownloadCall> it = this.readyAsyncCalls.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().task);
        }
        Iterator<DownloadCall> it2 = this.runningAsyncCalls.iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().task);
        }
        Iterator<DownloadCall> it3 = this.runningSyncCalls.iterator();
        while (it3.hasNext()) {
            arrayList.add(it3.next().task);
        }
        if (!arrayList.isEmpty()) {
            cancelLocked((IdentifiedTask[]) arrayList.toArray(new DownloadTask[arrayList.size()]));
        }
        this.skipProceedCallCount.decrementAndGet();
    }

    public void cancel(IdentifiedTask[] identifiedTaskArr) {
        this.skipProceedCallCount.incrementAndGet();
        cancelLocked(identifiedTaskArr);
        this.skipProceedCallCount.decrementAndGet();
        processCalls();
    }

    public boolean cancel(IdentifiedTask identifiedTask) {
        this.skipProceedCallCount.incrementAndGet();
        boolean cancelLocked = cancelLocked(identifiedTask);
        this.skipProceedCallCount.decrementAndGet();
        processCalls();
        return cancelLocked;
    }

    public boolean cancel(int i) {
        this.skipProceedCallCount.incrementAndGet();
        boolean cancelLocked = cancelLocked(DownloadTask.mockTaskForCompare(i));
        this.skipProceedCallCount.decrementAndGet();
        processCalls();
        return cancelLocked;
    }

    private synchronized void cancelLocked(IdentifiedTask[] identifiedTaskArr) {
        long uptimeMillis = SystemClock.uptimeMillis();
        Util.m2347d(TAG, "start cancel bunch task manually: " + identifiedTaskArr.length);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            for (IdentifiedTask identifiedTask : identifiedTaskArr) {
                filterCanceledCalls(identifiedTask, arrayList, arrayList2);
            }
        } finally {
            handleCanceledCalls(arrayList, arrayList2);
            Util.m2347d(TAG, "finish cancel bunch task manually: " + identifiedTaskArr.length + " consume " + (SystemClock.uptimeMillis() - uptimeMillis) + "ms");
        }
    }

    synchronized boolean cancelLocked(IdentifiedTask identifiedTask) {
        ArrayList arrayList;
        ArrayList arrayList2;
        Util.m2347d(TAG, "cancel manually: " + identifiedTask.getId());
        arrayList = new ArrayList();
        arrayList2 = new ArrayList();
        try {
            filterCanceledCalls(identifiedTask, arrayList, arrayList2);
            handleCanceledCalls(arrayList, arrayList2);
        } catch (Throwable th) {
            handleCanceledCalls(arrayList, arrayList2);
            throw th;
        }
        return arrayList.size() > 0 || arrayList2.size() > 0;
    }

    private synchronized void filterCanceledCalls(IdentifiedTask identifiedTask, List<DownloadCall> list, List<DownloadCall> list2) {
        Iterator<DownloadCall> it = this.readyAsyncCalls.iterator();
        while (it.hasNext()) {
            DownloadCall next = it.next();
            if (next.task == identifiedTask || next.task.getId() == identifiedTask.getId()) {
                if (!next.isCanceled() && !next.isFinishing()) {
                    it.remove();
                    list.add(next);
                    return;
                }
                return;
            }
        }
        for (DownloadCall downloadCall : this.runningAsyncCalls) {
            if (downloadCall.task == identifiedTask || downloadCall.task.getId() == identifiedTask.getId()) {
                list.add(downloadCall);
                list2.add(downloadCall);
                return;
            }
        }
        for (DownloadCall downloadCall2 : this.runningSyncCalls) {
            if (downloadCall2.task == identifiedTask || downloadCall2.task.getId() == identifiedTask.getId()) {
                list.add(downloadCall2);
                list2.add(downloadCall2);
                return;
            }
        }
    }

    private synchronized void handleCanceledCalls(List<DownloadCall> list, List<DownloadCall> list2) {
        Util.m2347d(TAG, "handle cancel calls, cancel calls: " + list2.size());
        if (!list2.isEmpty()) {
            for (DownloadCall downloadCall : list2) {
                if (!downloadCall.cancel()) {
                    list.remove(downloadCall);
                }
            }
        }
        Util.m2347d(TAG, "handle cancel calls, callback cancel event: " + list.size());
        if (!list.isEmpty()) {
            if (list.size() <= 1) {
                OkDownload.with().callbackDispatcher().dispatch().taskEnd(list.get(0).task, EndCause.CANCELED, null);
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator<DownloadCall> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().task);
                }
                OkDownload.with().callbackDispatcher().endTasksWithCanceled(arrayList);
            }
        }
    }

    public synchronized DownloadTask findSameTask(DownloadTask downloadTask) {
        Util.m2347d(TAG, "findSameTask: " + downloadTask.getId());
        for (DownloadCall downloadCall : this.readyAsyncCalls) {
            if (!downloadCall.isCanceled() && downloadCall.equalsTask(downloadTask)) {
                return downloadCall.task;
            }
        }
        for (DownloadCall downloadCall2 : this.runningAsyncCalls) {
            if (!downloadCall2.isCanceled() && downloadCall2.equalsTask(downloadTask)) {
                return downloadCall2.task;
            }
        }
        for (DownloadCall downloadCall3 : this.runningSyncCalls) {
            if (!downloadCall3.isCanceled() && downloadCall3.equalsTask(downloadTask)) {
                return downloadCall3.task;
            }
        }
        return null;
    }

    public synchronized boolean isRunning(DownloadTask downloadTask) {
        Util.m2347d(TAG, "isRunning: " + downloadTask.getId());
        for (DownloadCall downloadCall : this.runningSyncCalls) {
            if (!downloadCall.isCanceled() && downloadCall.equalsTask(downloadTask)) {
                return true;
            }
        }
        for (DownloadCall downloadCall2 : this.runningAsyncCalls) {
            if (!downloadCall2.isCanceled() && downloadCall2.equalsTask(downloadTask)) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean isPending(DownloadTask downloadTask) {
        Util.m2347d(TAG, "isPending: " + downloadTask.getId());
        for (DownloadCall downloadCall : this.readyAsyncCalls) {
            if (!downloadCall.isCanceled() && downloadCall.equalsTask(downloadTask)) {
                return true;
            }
        }
        return false;
    }

    void syncRunCall(DownloadCall downloadCall) {
        downloadCall.run();
    }

    public synchronized void flyingCanceled(DownloadCall downloadCall) {
        Util.m2347d(TAG, "flying canceled: " + downloadCall.task.getId());
        if (downloadCall.asyncExecuted) {
            this.flyingCanceledAsyncCallCount.incrementAndGet();
        }
    }

    public synchronized void finish(DownloadCall downloadCall) {
        List<DownloadCall> list;
        boolean z = downloadCall.asyncExecuted;
        if (this.finishingCalls.contains(downloadCall)) {
            list = this.finishingCalls;
        } else if (z) {
            list = this.runningAsyncCalls;
        } else {
            list = this.runningSyncCalls;
        }
        if (!list.remove(downloadCall)) {
            throw new AssertionError("Call wasn't in-flight!");
        }
        if (z && downloadCall.isCanceled()) {
            this.flyingCanceledAsyncCallCount.decrementAndGet();
        }
        if (z) {
            processCalls();
        }
    }

    public synchronized boolean isFileConflictAfterRun(DownloadTask downloadTask) {
        File file;
        File file2;
        Util.m2347d(TAG, "is file conflict after run: " + downloadTask.getId());
        File file3 = downloadTask.getFile();
        if (file3 == null) {
            return false;
        }
        for (DownloadCall downloadCall : this.runningSyncCalls) {
            if (!downloadCall.isCanceled() && downloadCall.task != downloadTask && (file2 = downloadCall.task.getFile()) != null && file3.equals(file2)) {
                return true;
            }
        }
        for (DownloadCall downloadCall2 : this.runningAsyncCalls) {
            if (!downloadCall2.isCanceled() && downloadCall2.task != downloadTask && (file = downloadCall2.task.getFile()) != null && file3.equals(file)) {
                return true;
            }
        }
        return false;
    }

    private boolean inspectForConflict(DownloadTask downloadTask) {
        return inspectForConflict(downloadTask, null, null);
    }

    private boolean inspectForConflict(DownloadTask downloadTask, Collection<DownloadTask> collection, Collection<DownloadTask> collection2) {
        return inspectForConflict(downloadTask, this.readyAsyncCalls, collection, collection2) || inspectForConflict(downloadTask, this.runningAsyncCalls, collection, collection2) || inspectForConflict(downloadTask, this.runningSyncCalls, collection, collection2);
    }

    boolean inspectCompleted(DownloadTask downloadTask) {
        return inspectCompleted(downloadTask, null);
    }

    boolean inspectCompleted(DownloadTask downloadTask, Collection<DownloadTask> collection) {
        if (!downloadTask.isPassIfAlreadyCompleted() || !StatusUtil.isCompleted(downloadTask)) {
            return false;
        }
        if (downloadTask.getFilename() == null && !OkDownload.with().downloadStrategy().validFilenameFromStore(downloadTask)) {
            return false;
        }
        OkDownload.with().downloadStrategy().validInfoOnCompleted(downloadTask, this.store);
        if (collection != null) {
            collection.add(downloadTask);
            return true;
        }
        OkDownload.with().callbackDispatcher().dispatch().taskEnd(downloadTask, EndCause.COMPLETED, null);
        return true;
    }

    boolean inspectForConflict(DownloadTask downloadTask, Collection<DownloadCall> collection, Collection<DownloadTask> collection2, Collection<DownloadTask> collection3) {
        CallbackDispatcher callbackDispatcher = OkDownload.with().callbackDispatcher();
        Iterator<DownloadCall> it = collection.iterator();
        while (it.hasNext()) {
            DownloadCall next = it.next();
            if (!next.isCanceled()) {
                if (next.equalsTask(downloadTask)) {
                    if (!next.isFinishing()) {
                        if (collection2 != null) {
                            collection2.add(downloadTask);
                        } else {
                            callbackDispatcher.dispatch().taskEnd(downloadTask, EndCause.SAME_TASK_BUSY, null);
                        }
                        return true;
                    }
                    Util.m2347d(TAG, "task: " + downloadTask.getId() + " is finishing, move it to finishing list");
                    this.finishingCalls.add(next);
                    it.remove();
                    return false;
                }
                File file = next.getFile();
                File file2 = downloadTask.getFile();
                if (file != null && file2 != null && file.equals(file2)) {
                    if (collection3 != null) {
                        collection3.add(downloadTask);
                    } else {
                        callbackDispatcher.dispatch().taskEnd(downloadTask, EndCause.FILE_BUSY, null);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private synchronized void processCalls() {
        if (this.skipProceedCallCount.get() > 0) {
            return;
        }
        if (runningAsyncSize() >= this.maxParallelRunningCount) {
            return;
        }
        if (this.readyAsyncCalls.isEmpty()) {
            return;
        }
        Iterator<DownloadCall> it = this.readyAsyncCalls.iterator();
        while (it.hasNext()) {
            DownloadCall next = it.next();
            it.remove();
            DownloadTask downloadTask = next.task;
            if (isFileConflictAfterRun(downloadTask)) {
                OkDownload.with().callbackDispatcher().dispatch().taskEnd(downloadTask, EndCause.FILE_BUSY, null);
            } else {
                this.runningAsyncCalls.add(next);
                getExecutorService().execute(next);
                if (runningAsyncSize() >= this.maxParallelRunningCount) {
                    return;
                }
            }
        }
    }

    private int runningAsyncSize() {
        return this.runningAsyncCalls.size() - this.flyingCanceledAsyncCallCount.get();
    }

    public static void setMaxParallelRunningCount(int i) {
        DownloadDispatcher downloadDispatcher = OkDownload.with().downloadDispatcher();
        if (downloadDispatcher.getClass() != DownloadDispatcher.class) {
            throw new IllegalStateException("The current dispatcher is " + downloadDispatcher + " not DownloadDispatcher exactly!");
        }
        downloadDispatcher.maxParallelRunningCount = Math.max(1, i);
    }
}
