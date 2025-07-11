package com.google.common.util.concurrent;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenerCallQueue;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import java.lang.ref.WeakReference;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class ServiceManager implements ServiceManagerBridge {
    private final ImmutableList<Service> services;
    private final ServiceManagerState state;
    private static final Logger logger = Logger.getLogger(ServiceManager.class.getName());
    private static final ListenerCallQueue.Event<Listener> HEALTHY_EVENT = new ListenerCallQueue.Event<Listener>() { // from class: com.google.common.util.concurrent.ServiceManager.1
        public String toString() {
            return "healthy()";
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Listener listener) {
            listener.healthy();
        }
    };
    private static final ListenerCallQueue.Event<Listener> STOPPED_EVENT = new ListenerCallQueue.Event<Listener>() { // from class: com.google.common.util.concurrent.ServiceManager.2
        public String toString() {
            return "stopped()";
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Listener listener) {
            listener.stopped();
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static abstract class Listener {
        public void failure(Service service) {
        }

        public void healthy() {
        }

        public void stopped() {
        }
    }

    public ServiceManager(Iterable<? extends Service> iterable) {
        ImmutableList<Service> copyOf = ImmutableList.copyOf(iterable);
        if (copyOf.isEmpty()) {
            logger.log(Level.WARNING, "ServiceManager configured with no services.  Is your application configured properly?", (Throwable) new EmptyServiceManagerWarning());
            copyOf = ImmutableList.m635of(new NoOpService());
        }
        this.state = new ServiceManagerState(copyOf);
        this.services = copyOf;
        WeakReference weakReference = new WeakReference(this.state);
        UnmodifiableIterator<Service> it = copyOf.iterator();
        while (it.hasNext()) {
            Service next = it.next();
            next.addListener(new ServiceListener(next, weakReference), MoreExecutors.directExecutor());
            Preconditions.checkArgument(next.state() == Service.State.NEW, "Can only manage NEW services, %s", next);
        }
        this.state.markReady();
    }

    public void addListener(Listener listener, Executor executor) {
        this.state.addListener(listener, executor);
    }

    public ServiceManager startAsync() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            Service next = it.next();
            Service.State state = next.state();
            Preconditions.checkState(state == Service.State.NEW, "Service %s is %s, cannot start it.", next, state);
        }
        UnmodifiableIterator<Service> it2 = this.services.iterator();
        while (it2.hasNext()) {
            Service next2 = it2.next();
            try {
                this.state.tryStartTiming(next2);
                next2.startAsync();
            } catch (IllegalStateException e) {
                Logger logger2 = logger;
                Level level = Level.WARNING;
                String valueOf = String.valueOf(next2);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
                sb.append("Unable to start Service ");
                sb.append(valueOf);
                logger2.log(level, sb.toString(), (Throwable) e);
            }
        }
        return this;
    }

    public void awaitHealthy() {
        this.state.awaitHealthy();
    }

    public void awaitHealthy(Duration duration) throws TimeoutException {
        awaitHealthy(Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    public void awaitHealthy(long j, TimeUnit timeUnit) throws TimeoutException {
        this.state.awaitHealthy(j, timeUnit);
    }

    public ServiceManager stopAsync() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            it.next().stopAsync();
        }
        return this;
    }

    public void awaitStopped() {
        this.state.awaitStopped();
    }

    public void awaitStopped(Duration duration) throws TimeoutException {
        awaitStopped(Internal.toNanosSaturated(duration), TimeUnit.NANOSECONDS);
    }

    public void awaitStopped(long j, TimeUnit timeUnit) throws TimeoutException {
        this.state.awaitStopped(j, timeUnit);
    }

    public boolean isHealthy() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            if (!it.next().isRunning()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.common.util.concurrent.ServiceManagerBridge
    public ImmutableSetMultimap<Service.State, Service> servicesByState() {
        return this.state.servicesByState();
    }

    public ImmutableMap<Service, Long> startupTimes() {
        return this.state.startupTimes();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Class<?>) ServiceManager.class).add(TmpConstant.DEVICE_MODEL_SERVICES, Collections2.filter(this.services, Predicates.not(Predicates.instanceOf(NoOpService.class)))).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static final class ServiceManagerState {
        final int numberOfServices;
        boolean ready;
        boolean transitioned;
        final Monitor monitor = new Monitor();
        final SetMultimap<Service.State, Service> servicesByState = MultimapBuilder.enumKeys(Service.State.class).linkedHashSetValues().build();
        final Multiset<Service.State> states = this.servicesByState.keys();
        final Map<Service, Stopwatch> startupTimers = Maps.newIdentityHashMap();
        final Monitor.Guard awaitHealthGuard = new AwaitHealthGuard();
        final Monitor.Guard stoppedGuard = new StoppedGuard();
        final ListenerCallQueue<Listener> listeners = new ListenerCallQueue<>();

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes3.dex */
        final class AwaitHealthGuard extends Monitor.Guard {
            AwaitHealthGuard() {
                super(ServiceManagerState.this.monitor);
            }

            @Override // com.google.common.util.concurrent.Monitor.Guard
            public boolean isSatisfied() {
                return ServiceManagerState.this.states.count(Service.State.RUNNING) == ServiceManagerState.this.numberOfServices || ServiceManagerState.this.states.contains(Service.State.STOPPING) || ServiceManagerState.this.states.contains(Service.State.TERMINATED) || ServiceManagerState.this.states.contains(Service.State.FAILED);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes3.dex */
        final class StoppedGuard extends Monitor.Guard {
            StoppedGuard() {
                super(ServiceManagerState.this.monitor);
            }

            @Override // com.google.common.util.concurrent.Monitor.Guard
            public boolean isSatisfied() {
                return ServiceManagerState.this.states.count(Service.State.TERMINATED) + ServiceManagerState.this.states.count(Service.State.FAILED) == ServiceManagerState.this.numberOfServices;
            }
        }

        ServiceManagerState(ImmutableCollection<Service> immutableCollection) {
            this.numberOfServices = immutableCollection.size();
            this.servicesByState.putAll(Service.State.NEW, immutableCollection);
        }

        void tryStartTiming(Service service) {
            this.monitor.enter();
            try {
                if (this.startupTimers.get(service) == null) {
                    this.startupTimers.put(service, Stopwatch.createStarted());
                }
            } finally {
                this.monitor.leave();
            }
        }

        void markReady() {
            this.monitor.enter();
            try {
                if (!this.transitioned) {
                    this.ready = true;
                    return;
                }
                ArrayList newArrayList = Lists.newArrayList();
                UnmodifiableIterator<Service> it = servicesByState().values().iterator();
                while (it.hasNext()) {
                    Service next = it.next();
                    if (next.state() != Service.State.NEW) {
                        newArrayList.add(next);
                    }
                }
                String valueOf = String.valueOf(newArrayList);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 89);
                sb.append("Services started transitioning asynchronously before the ServiceManager was constructed: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            } finally {
                this.monitor.leave();
            }
        }

        void addListener(Listener listener, Executor executor) {
            this.listeners.addListener(listener, executor);
        }

        void awaitHealthy() {
            this.monitor.enterWhenUninterruptibly(this.awaitHealthGuard);
            try {
                checkHealthy();
            } finally {
                this.monitor.leave();
            }
        }

        void awaitHealthy(long j, TimeUnit timeUnit) throws TimeoutException {
            this.monitor.enter();
            try {
                if (!this.monitor.waitForUninterruptibly(this.awaitHealthGuard, j, timeUnit)) {
                    String valueOf = String.valueOf(Multimaps.filterKeys((SetMultimap) this.servicesByState, Predicates.m611in(ImmutableSet.m678of(Service.State.NEW, Service.State.STARTING))));
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 93);
                    sb.append("Timeout waiting for the services to become healthy. The following services have not started: ");
                    sb.append(valueOf);
                    throw new TimeoutException(sb.toString());
                }
                checkHealthy();
            } finally {
                this.monitor.leave();
            }
        }

        void awaitStopped() {
            this.monitor.enterWhenUninterruptibly(this.stoppedGuard);
            this.monitor.leave();
        }

        void awaitStopped(long j, TimeUnit timeUnit) throws TimeoutException {
            this.monitor.enter();
            try {
                if (this.monitor.waitForUninterruptibly(this.stoppedGuard, j, timeUnit)) {
                    return;
                }
                String valueOf = String.valueOf(Multimaps.filterKeys((SetMultimap) this.servicesByState, Predicates.not(Predicates.m611in(EnumSet.of(Service.State.TERMINATED, Service.State.FAILED)))));
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 83);
                sb.append("Timeout waiting for the services to stop. The following services have not stopped: ");
                sb.append(valueOf);
                throw new TimeoutException(sb.toString());
            } finally {
                this.monitor.leave();
            }
        }

        ImmutableSetMultimap<Service.State, Service> servicesByState() {
            ImmutableSetMultimap.Builder builder = ImmutableSetMultimap.builder();
            this.monitor.enter();
            try {
                for (Map.Entry<Service.State, Service> entry : this.servicesByState.entries()) {
                    if (!(entry.getValue() instanceof NoOpService)) {
                        builder.put((Map.Entry) entry);
                    }
                }
                this.monitor.leave();
                return builder.build();
            } catch (Throwable th) {
                this.monitor.leave();
                throw th;
            }
        }

        ImmutableMap<Service, Long> startupTimes() {
            this.monitor.enter();
            try {
                ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(this.startupTimers.size());
                for (Map.Entry<Service, Stopwatch> entry : this.startupTimers.entrySet()) {
                    Service key = entry.getKey();
                    Stopwatch value = entry.getValue();
                    if (!value.isRunning() && !(key instanceof NoOpService)) {
                        newArrayListWithCapacity.add(Maps.immutableEntry(key, Long.valueOf(value.elapsed(TimeUnit.MILLISECONDS))));
                    }
                }
                this.monitor.leave();
                Collections.sort(newArrayListWithCapacity, Ordering.natural().onResultOf(new Function<Map.Entry<Service, Long>, Long>(this) { // from class: com.google.common.util.concurrent.ServiceManager.ServiceManagerState.1
                    @Override // com.google.common.base.Function, java.util.function.Function
                    public Long apply(Map.Entry<Service, Long> entry2) {
                        return entry2.getValue();
                    }
                }));
                return ImmutableMap.copyOf(newArrayListWithCapacity);
            } catch (Throwable th) {
                this.monitor.leave();
                throw th;
            }
        }

        void transitionService(Service service, Service.State state, Service.State state2) {
            Preconditions.checkNotNull(service);
            Preconditions.checkArgument(state != state2);
            this.monitor.enter();
            try {
                this.transitioned = true;
                if (this.ready) {
                    Preconditions.checkState(this.servicesByState.remove(state, service), "Service %s not at the expected location in the state map %s", service, state);
                    Preconditions.checkState(this.servicesByState.put(state2, service), "Service %s in the state map unexpectedly at %s", service, state2);
                    Stopwatch stopwatch = this.startupTimers.get(service);
                    if (stopwatch == null) {
                        stopwatch = Stopwatch.createStarted();
                        this.startupTimers.put(service, stopwatch);
                    }
                    if (state2.compareTo(Service.State.RUNNING) >= 0 && stopwatch.isRunning()) {
                        stopwatch.stop();
                        if (!(service instanceof NoOpService)) {
                            ServiceManager.logger.log(Level.FINE, "Started {0} in {1}.", new Object[]{service, stopwatch});
                        }
                    }
                    if (state2 == Service.State.FAILED) {
                        enqueueFailedEvent(service);
                    }
                    if (this.states.count(Service.State.RUNNING) == this.numberOfServices) {
                        enqueueHealthyEvent();
                    } else if (this.states.count(Service.State.TERMINATED) + this.states.count(Service.State.FAILED) == this.numberOfServices) {
                        enqueueStoppedEvent();
                    }
                }
            } finally {
                this.monitor.leave();
                dispatchListenerEvents();
            }
        }

        void enqueueStoppedEvent() {
            this.listeners.enqueue(ServiceManager.STOPPED_EVENT);
        }

        void enqueueHealthyEvent() {
            this.listeners.enqueue(ServiceManager.HEALTHY_EVENT);
        }

        void enqueueFailedEvent(final Service service) {
            this.listeners.enqueue(new ListenerCallQueue.Event<Listener>(this) { // from class: com.google.common.util.concurrent.ServiceManager.ServiceManagerState.2
                @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
                public void call(Listener listener) {
                    listener.failure(service);
                }

                public String toString() {
                    String valueOf = String.valueOf(service);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                    sb.append("failed({service=");
                    sb.append(valueOf);
                    sb.append("})");
                    return sb.toString();
                }
            });
        }

        void dispatchListenerEvents() {
            Preconditions.checkState(!this.monitor.isOccupiedByCurrentThread(), "It is incorrect to execute listeners with the monitor held.");
            this.listeners.dispatch();
        }

        void checkHealthy() {
            if (this.states.count(Service.State.RUNNING) != this.numberOfServices) {
                String valueOf = String.valueOf(Multimaps.filterKeys((SetMultimap) this.servicesByState, Predicates.not(Predicates.equalTo(Service.State.RUNNING))));
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 79);
                sb.append("Expected to be healthy after starting. The following services are not running: ");
                sb.append(valueOf);
                IllegalStateException illegalStateException = new IllegalStateException(sb.toString());
                Iterator<Service> it = this.servicesByState.get((SetMultimap<Service.State, Service>) Service.State.FAILED).iterator();
                while (it.hasNext()) {
                    illegalStateException.addSuppressed(new FailedService(it.next()));
                }
                throw illegalStateException;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    private static final class ServiceListener extends Service.Listener {
        final Service service;
        final WeakReference<ServiceManagerState> state;

        ServiceListener(Service service, WeakReference<ServiceManagerState> weakReference) {
            this.service = service;
            this.state = weakReference;
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void starting() {
            ServiceManagerState serviceManagerState = this.state.get();
            if (serviceManagerState != null) {
                serviceManagerState.transitionService(this.service, Service.State.NEW, Service.State.STARTING);
                if (this.service instanceof NoOpService) {
                    return;
                }
                ServiceManager.logger.log(Level.FINE, "Starting {0}.", this.service);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void running() {
            ServiceManagerState serviceManagerState = this.state.get();
            if (serviceManagerState != null) {
                serviceManagerState.transitionService(this.service, Service.State.STARTING, Service.State.RUNNING);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void stopping(Service.State state) {
            ServiceManagerState serviceManagerState = this.state.get();
            if (serviceManagerState != null) {
                serviceManagerState.transitionService(this.service, state, Service.State.STOPPING);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void terminated(Service.State state) {
            ServiceManagerState serviceManagerState = this.state.get();
            if (serviceManagerState != null) {
                if (!(this.service instanceof NoOpService)) {
                    ServiceManager.logger.log(Level.FINE, "Service {0} has terminated. Previous state was: {1}", new Object[]{this.service, state});
                }
                serviceManagerState.transitionService(this.service, state, Service.State.TERMINATED);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void failed(Service.State state, Throwable th) {
            ServiceManagerState serviceManagerState = this.state.get();
            if (serviceManagerState != null) {
                if ((!(this.service instanceof NoOpService)) & (state != Service.State.STARTING)) {
                    Logger logger = ServiceManager.logger;
                    Level level = Level.SEVERE;
                    String valueOf = String.valueOf(this.service);
                    String valueOf2 = String.valueOf(state);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 34 + String.valueOf(valueOf2).length());
                    sb.append("Service ");
                    sb.append(valueOf);
                    sb.append(" has failed in the ");
                    sb.append(valueOf2);
                    sb.append(" state.");
                    logger.log(level, sb.toString(), th);
                }
                serviceManagerState.transitionService(this.service, state, Service.State.FAILED);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static final class NoOpService extends AbstractService {
        private NoOpService() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected void doStart() {
            notifyStarted();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected void doStop() {
            notifyStopped();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    private static final class EmptyServiceManagerWarning extends Throwable {
        private EmptyServiceManagerWarning() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class FailedService extends Throwable {
        FailedService(Service service) {
            super(service.toString(), service.failureCause(), false, false);
        }
    }
}
