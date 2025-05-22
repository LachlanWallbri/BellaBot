package com.pudutech.mqtt.component.client.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class ExecutorServiceFactory {
    private ExecutorService bossPool;
    private ExecutorService workPool;

    public synchronized void initBossLoopGroup() {
        initBossLoopGroup(1);
    }

    public synchronized void initBossLoopGroup(int i) {
        destroyBossLoopGroup();
        this.bossPool = Executors.newFixedThreadPool(i);
    }

    public synchronized void initWorkLoopGroup() {
        initWorkLoopGroup(1);
    }

    public synchronized void initWorkLoopGroup(int i) {
        destroyWorkLoopGroup();
        this.workPool = Executors.newFixedThreadPool(i);
    }

    public void execBossTask(Runnable runnable) {
        if (this.bossPool == null) {
            initBossLoopGroup();
        }
        this.bossPool.execute(runnable);
    }

    public void execWorkTask(Runnable runnable) {
        if (this.workPool == null) {
            initWorkLoopGroup();
        }
        this.workPool.execute(runnable);
    }

    public synchronized void destroyBossLoopGroup() {
        if (this.bossPool != null) {
            try {
                this.bossPool.shutdownNow();
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public synchronized void destroyWorkLoopGroup() {
        if (this.workPool != null) {
            try {
                this.workPool.shutdownNow();
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public synchronized void destroy() {
        destroyBossLoopGroup();
        destroyWorkLoopGroup();
    }
}
