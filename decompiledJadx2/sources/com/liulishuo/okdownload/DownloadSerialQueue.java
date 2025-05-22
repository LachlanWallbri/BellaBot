package com.liulishuo.okdownload;

import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener2;
import com.liulishuo.okdownload.core.listener.DownloadListenerBunch;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class DownloadSerialQueue extends DownloadListener2 implements Runnable {
    static final int ID_INVALID = 0;
    private static final Executor SERIAL_EXECUTOR = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkDownload DynamicSerial", false));
    private static final String TAG = "DownloadSerialQueue";
    DownloadListenerBunch listenerBunch;
    volatile boolean looping;
    volatile boolean paused;
    volatile DownloadTask runningTask;
    volatile boolean shutedDown;
    private final ArrayList<DownloadTask> taskList;

    public DownloadSerialQueue() {
        this(null);
    }

    DownloadSerialQueue(DownloadListener downloadListener, ArrayList<DownloadTask> arrayList) {
        this.shutedDown = false;
        this.looping = false;
        this.paused = false;
        this.listenerBunch = new DownloadListenerBunch.Builder().append(this).append(downloadListener).build();
        this.taskList = arrayList;
    }

    public DownloadSerialQueue(DownloadListener downloadListener) {
        this(downloadListener, new ArrayList());
    }

    public void setListener(DownloadListener downloadListener) {
        this.listenerBunch = new DownloadListenerBunch.Builder().append(this).append(downloadListener).build();
    }

    public synchronized void enqueue(DownloadTask downloadTask) {
        this.taskList.add(downloadTask);
        Collections.sort(this.taskList);
        if (!this.paused && !this.looping) {
            this.looping = true;
            startNewLooper();
        }
    }

    public synchronized void pause() {
        if (this.paused) {
            Util.m2350w(TAG, "require pause this queue(remain " + this.taskList.size() + "), butit has already been paused");
            return;
        }
        this.paused = true;
        if (this.runningTask != null) {
            this.runningTask.cancel();
            this.taskList.add(0, this.runningTask);
            this.runningTask = null;
        }
    }

    public synchronized void resume() {
        if (!this.paused) {
            Util.m2350w(TAG, "require resume this queue(remain " + this.taskList.size() + "), but it is still running");
            return;
        }
        this.paused = false;
        if (!this.taskList.isEmpty() && !this.looping) {
            this.looping = true;
            startNewLooper();
        }
    }

    public int getWorkingTaskId() {
        if (this.runningTask != null) {
            return this.runningTask.getId();
        }
        return 0;
    }

    public int getWaitingTaskCount() {
        return this.taskList.size();
    }

    public synchronized DownloadTask[] shutdown() {
        DownloadTask[] downloadTaskArr;
        this.shutedDown = true;
        if (this.runningTask != null) {
            this.runningTask.cancel();
        }
        downloadTaskArr = new DownloadTask[this.taskList.size()];
        this.taskList.toArray(downloadTaskArr);
        this.taskList.clear();
        return downloadTaskArr;
    }

    @Override // java.lang.Runnable
    public void run() {
        DownloadTask remove;
        while (!this.shutedDown) {
            synchronized (this) {
                if (!this.taskList.isEmpty() && !this.paused) {
                    remove = this.taskList.remove(0);
                }
                this.runningTask = null;
                this.looping = false;
                return;
            }
            remove.execute(this.listenerBunch);
        }
    }

    void startNewLooper() {
        SERIAL_EXECUTOR.execute(this);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void taskStart(DownloadTask downloadTask) {
        this.runningTask = downloadTask;
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public synchronized void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc) {
        if (endCause != EndCause.CANCELED && downloadTask == this.runningTask) {
            this.runningTask = null;
        }
    }
}
