package com.google.api.core;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ApiService {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @BetaApi
    /* loaded from: classes2.dex */
    public static abstract class Listener {
        public void failed(State state, Throwable th) {
        }

        public void running() {
        }

        public void starting() {
        }

        public void stopping(State state) {
        }

        public void terminated(State state) {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @BetaApi
    /* loaded from: classes2.dex */
    public enum State {
        FAILED,
        NEW,
        RUNNING,
        STARTING,
        STOPPING,
        TERMINATED
    }

    void addListener(Listener listener, Executor executor);

    void awaitRunning();

    void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException;

    void awaitTerminated();

    void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException;

    Throwable failureCause();

    boolean isRunning();

    ApiService startAsync();

    State state();

    ApiService stopAsync();
}
