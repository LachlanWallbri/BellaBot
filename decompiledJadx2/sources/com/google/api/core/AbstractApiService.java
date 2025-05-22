package com.google.api.core;

import com.google.api.core.ApiService;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.Service;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class AbstractApiService implements ApiService {
    private static final ImmutableMap<Service.State, ApiService.State> GUAVA_TO_API_SERVICE_STATE = ImmutableMap.builder().put(Service.State.FAILED, ApiService.State.FAILED).put(Service.State.NEW, ApiService.State.NEW).put(Service.State.RUNNING, ApiService.State.RUNNING).put(Service.State.STARTING, ApiService.State.STARTING).put(Service.State.STOPPING, ApiService.State.STOPPING).put(Service.State.TERMINATED, ApiService.State.TERMINATED).build();
    private final InnerService impl = new InnerService();

    protected abstract void doStart();

    protected abstract void doStop();

    protected AbstractApiService() {
    }

    @Override // com.google.api.core.ApiService
    public void addListener(final ApiService.Listener listener, Executor executor) {
        this.impl.addListener(new Service.Listener() { // from class: com.google.api.core.AbstractApiService.1
            @Override // com.google.common.util.concurrent.Service.Listener
            public void failed(Service.State state, Throwable th) {
                listener.failed((ApiService.State) AbstractApiService.GUAVA_TO_API_SERVICE_STATE.get(state), th);
            }

            @Override // com.google.common.util.concurrent.Service.Listener
            public void running() {
                listener.running();
            }

            @Override // com.google.common.util.concurrent.Service.Listener
            public void starting() {
                listener.starting();
            }

            @Override // com.google.common.util.concurrent.Service.Listener
            public void stopping(Service.State state) {
                listener.stopping((ApiService.State) AbstractApiService.GUAVA_TO_API_SERVICE_STATE.get(state));
            }

            @Override // com.google.common.util.concurrent.Service.Listener
            public void terminated(Service.State state) {
                listener.terminated((ApiService.State) AbstractApiService.GUAVA_TO_API_SERVICE_STATE.get(state));
            }
        }, executor);
    }

    @Override // com.google.api.core.ApiService
    public void awaitRunning() {
        this.impl.awaitRunning();
    }

    @Override // com.google.api.core.ApiService
    public void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        this.impl.awaitRunning(j, timeUnit);
    }

    @Override // com.google.api.core.ApiService
    public void awaitTerminated() {
        this.impl.awaitTerminated();
    }

    @Override // com.google.api.core.ApiService
    public void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        this.impl.awaitTerminated(j, timeUnit);
    }

    @Override // com.google.api.core.ApiService
    public Throwable failureCause() {
        return this.impl.failureCause();
    }

    @Override // com.google.api.core.ApiService
    public boolean isRunning() {
        return this.impl.isRunning();
    }

    @Override // com.google.api.core.ApiService
    public ApiService startAsync() {
        this.impl.startAsync();
        return this;
    }

    @Override // com.google.api.core.ApiService
    public ApiService.State state() {
        return GUAVA_TO_API_SERVICE_STATE.get(this.impl.state());
    }

    @Override // com.google.api.core.ApiService
    public ApiService stopAsync() {
        this.impl.stopAsync();
        return this;
    }

    protected void notifyStarted() {
        this.impl.innerNotifyStarted();
    }

    protected void notifyStopped() {
        this.impl.innerNotifyStopped();
    }

    protected void notifyFailed(Throwable th) {
        this.impl.innerNotifyFailed(th);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class InnerService extends AbstractService {
        private InnerService() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected void doStart() {
            AbstractApiService.this.doStart();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected void doStop() {
            AbstractApiService.this.doStop();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void innerNotifyStarted() {
            notifyStarted();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void innerNotifyStopped() {
            notifyStopped();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void innerNotifyFailed(Throwable th) {
            notifyFailed(th);
        }
    }
}
