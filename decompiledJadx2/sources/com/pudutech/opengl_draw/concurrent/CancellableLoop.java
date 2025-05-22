package com.pudutech.opengl_draw.concurrent;

import androidx.core.util.Preconditions;

/* loaded from: classes5.dex */
public abstract class CancellableLoop implements Runnable {
    private Thread thread;
    private boolean ranOnce = false;
    private final Object mutex = new Object();

    protected void handleInterruptedException(InterruptedException interruptedException) {
    }

    protected abstract void loop() throws InterruptedException;

    protected void setup() {
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.mutex) {
            Preconditions.checkState(!this.ranOnce, "CancellableLoops cannot be restarted.");
            this.ranOnce = true;
            this.thread = Thread.currentThread();
        }
        try {
            try {
                setup();
                while (!this.thread.isInterrupted()) {
                    loop();
                }
            } catch (InterruptedException e) {
                handleInterruptedException(e);
            }
        } finally {
            this.thread = null;
        }
    }

    public void cancel() {
        Thread thread = this.thread;
        if (thread != null) {
            thread.interrupt();
        }
    }

    public boolean isRunning() {
        Thread thread = this.thread;
        return (thread == null || thread.isInterrupted()) ? false : true;
    }
}
