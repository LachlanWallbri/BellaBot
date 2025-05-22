package com.pudutech.remotemaintenance;

import com.pudutech.remotemaintenance.interf.IoTInterface;

/* loaded from: classes.dex */
public class ReportTask extends Thread {
    private int count;
    private int currentCount;
    private boolean isActive = false;
    private IoTInterface mIoTInterface;

    public ReportTask(IoTInterface ioTInterface) {
        this.mIoTInterface = ioTInterface;
    }

    public void startReport(int i) {
        this.isActive = true;
        this.count = i;
        this.currentCount = 0;
        synchronized (this) {
            notify();
        }
    }

    public void stopReport() {
        this.isActive = false;
        this.count = 0;
        this.currentCount = 0;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int i;
        while (true) {
            if (this.isActive && (i = this.currentCount) < this.count) {
                this.currentCount = i + 1;
                System.out.println("running, currentCount=" + this.currentCount);
                IoTInterface ioTInterface = this.mIoTInterface;
                ioTInterface.sendMsg(ioTInterface.getReportMsg());
                try {
                    sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                synchronized (this) {
                    try {
                        System.out.println("wait");
                        wait();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }
}
