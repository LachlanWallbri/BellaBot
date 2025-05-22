package com.felhr.usbserial;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
abstract class AbstractWorkerThread extends Thread {
    boolean firstTime = true;
    volatile boolean keep = true;
    private volatile Thread workingThread;

    abstract void doRun();

    void stopThread() {
        this.keep = false;
        if (this.workingThread != null) {
            this.workingThread.interrupt();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        if (this.keep) {
            this.workingThread = Thread.currentThread();
            while (this.keep && !this.workingThread.isInterrupted()) {
                doRun();
            }
            Log.i("SerialUSB_Lidar", "------>>>>" + Thread.currentThread().getName() + ", " + Thread.currentThread().getId() + " end!!! ");
        }
    }
}
