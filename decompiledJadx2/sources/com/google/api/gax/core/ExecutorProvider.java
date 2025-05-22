package com.google.api.gax.core;

import java.util.concurrent.ScheduledExecutorService;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ExecutorProvider {
    ScheduledExecutorService getExecutor();

    boolean shouldAutoClose();
}
