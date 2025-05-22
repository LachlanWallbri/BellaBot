package com.google.api.gax.core;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class BackgroundResourceAggregation implements BackgroundResource {
    private final List<BackgroundResource> resources;

    public BackgroundResourceAggregation(List<BackgroundResource> list) {
        this.resources = list;
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdown() {
        Iterator<BackgroundResource> it = this.resources.iterator();
        while (it.hasNext()) {
            it.next().shutdown();
        }
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isShutdown() {
        Iterator<BackgroundResource> it = this.resources.iterator();
        while (it.hasNext()) {
            if (!it.next().isShutdown()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isTerminated() {
        Iterator<BackgroundResource> it = this.resources.iterator();
        while (it.hasNext()) {
            if (!it.next().isTerminated()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdownNow() {
        Iterator<BackgroundResource> it = this.resources.iterator();
        while (it.hasNext()) {
            it.next().shutdownNow();
        }
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        Iterator<BackgroundResource> it = this.resources.iterator();
        while (it.hasNext()) {
            if (!it.next().awaitTermination(j, timeUnit)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.AutoCloseable
    public final void close() throws Exception {
        shutdown();
    }
}
