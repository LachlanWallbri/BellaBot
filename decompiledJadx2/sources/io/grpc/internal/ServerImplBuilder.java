package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.BinaryLog;
import io.grpc.BindableService;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.InternalChannelz;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerStreamTracer;
import io.grpc.ServerTransportFilter;
import io.grpc.internal.CallTracer;
import io.grpc.internal.InternalHandlerRegistry;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ServerImplBuilder extends ServerBuilder<ServerImplBuilder> {

    @Nullable
    BinaryLog binlog;
    private final ClientTransportServersBuilder clientTransportServersBuilder;
    private static final Logger log = Logger.getLogger(ServerImplBuilder.class.getName());
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final HandlerRegistry DEFAULT_FALLBACK_REGISTRY = new DefaultFallbackRegistry();
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final long DEFAULT_HANDSHAKE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(120);
    final InternalHandlerRegistry.Builder registryBuilder = new InternalHandlerRegistry.Builder();
    final List<ServerTransportFilter> transportFilters = new ArrayList();
    final List<ServerInterceptor> interceptors = new ArrayList();
    private final List<ServerStreamTracer.Factory> streamTracerFactories = new ArrayList();
    HandlerRegistry fallbackRegistry = DEFAULT_FALLBACK_REGISTRY;
    ObjectPool<? extends Executor> executorPool = DEFAULT_EXECUTOR_POOL;
    DecompressorRegistry decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
    CompressorRegistry compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
    long handshakeTimeoutMillis = DEFAULT_HANDSHAKE_TIMEOUT_MILLIS;
    Deadline.Ticker ticker = Deadline.getSystemTicker();
    private boolean statsEnabled = true;
    private boolean recordStartedRpcs = true;
    private boolean recordFinishedRpcs = true;
    private boolean recordRealTimeMetrics = false;
    private boolean tracingEnabled = true;
    InternalChannelz channelz = InternalChannelz.instance();
    CallTracer.Factory callTracerFactory = CallTracer.getDefaultFactory();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface ClientTransportServersBuilder {
        InternalServer buildClientTransportServers(List<? extends ServerStreamTracer.Factory> list);
    }

    public static ServerBuilder<?> forPort(int i) {
        throw new UnsupportedOperationException("ClientTransportServersBuilder is required, use a constructor");
    }

    public ServerImplBuilder(ClientTransportServersBuilder clientTransportServersBuilder) {
        this.clientTransportServersBuilder = (ClientTransportServersBuilder) Preconditions.checkNotNull(clientTransportServersBuilder, "clientTransportServersBuilder");
    }

    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder directExecutor() {
        return executor(MoreExecutors.directExecutor());
    }

    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder executor(@Nullable Executor executor) {
        this.executorPool = executor != null ? new FixedObjectPool<>(executor) : DEFAULT_EXECUTOR_POOL;
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder addService(ServerServiceDefinition serverServiceDefinition) {
        this.registryBuilder.addService((ServerServiceDefinition) Preconditions.checkNotNull(serverServiceDefinition, "service"));
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder addService(BindableService bindableService) {
        return addService(((BindableService) Preconditions.checkNotNull(bindableService, "bindableService")).bindService());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder addTransportFilter(ServerTransportFilter serverTransportFilter) {
        this.transportFilters.add(Preconditions.checkNotNull(serverTransportFilter, "filter"));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder intercept(ServerInterceptor serverInterceptor) {
        this.interceptors.add(Preconditions.checkNotNull(serverInterceptor, "interceptor"));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        this.streamTracerFactories.add(Preconditions.checkNotNull(factory, "factory"));
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry) {
        if (handlerRegistry == null) {
            handlerRegistry = DEFAULT_FALLBACK_REGISTRY;
        }
        this.fallbackRegistry = handlerRegistry;
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry) {
        if (decompressorRegistry == null) {
            decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
        }
        this.decompressorRegistry = decompressorRegistry;
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder compressorRegistry(@Nullable CompressorRegistry compressorRegistry) {
        if (compressorRegistry == null) {
            compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
        }
        this.compressorRegistry = compressorRegistry;
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder handshakeTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "handshake timeout is %s, but must be positive", j);
        this.handshakeTimeoutMillis = ((TimeUnit) Preconditions.checkNotNull(timeUnit, "unit")).toMillis(j);
        return this;
    }

    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder setBinaryLog(@Nullable BinaryLog binaryLog) {
        this.binlog = binaryLog;
        return this;
    }

    public void setStatsEnabled(boolean z) {
        this.statsEnabled = z;
    }

    public void setStatsRecordStartedRpcs(boolean z) {
        this.recordStartedRpcs = z;
    }

    public void setStatsRecordFinishedRpcs(boolean z) {
        this.recordFinishedRpcs = z;
    }

    public void setStatsRecordRealTimeMetrics(boolean z) {
        this.recordRealTimeMetrics = z;
    }

    public void setTracingEnabled(boolean z) {
        this.tracingEnabled = z;
    }

    public void setDeadlineTicker(Deadline.Ticker ticker) {
        this.ticker = (Deadline.Ticker) Preconditions.checkNotNull(ticker, "ticker");
    }

    @Override // io.grpc.ServerBuilder
    public Server build() {
        return new ServerImpl(this, this.clientTransportServersBuilder.buildClientTransportServers(getTracerFactories()), Context.ROOT);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    List<? extends ServerStreamTracer.Factory> getTracerFactories() {
        ServerStreamTracer.Factory factory;
        ServerStreamTracer.Factory factory2;
        ArrayList arrayList = new ArrayList();
        if (this.statsEnabled) {
            try {
                factory = (ServerStreamTracer.Factory) Class.forName("io.grpc.census.InternalCensusStatsAccessor").getDeclaredMethod("getServerStreamTracerFactory", Boolean.TYPE, Boolean.TYPE, Boolean.TYPE).invoke(null, Boolean.valueOf(this.recordStartedRpcs), Boolean.valueOf(this.recordFinishedRpcs), Boolean.valueOf(this.recordRealTimeMetrics));
            } catch (ClassNotFoundException e) {
                log.log(Level.FINE, "Unable to apply census stats", (Throwable) e);
                factory = null;
                if (factory != null) {
                }
                if (this.tracingEnabled) {
                }
                arrayList.addAll(this.streamTracerFactories);
                arrayList.trimToSize();
                return Collections.unmodifiableList(arrayList);
            } catch (IllegalAccessException e2) {
                log.log(Level.FINE, "Unable to apply census stats", (Throwable) e2);
                factory = null;
                if (factory != null) {
                }
                if (this.tracingEnabled) {
                }
                arrayList.addAll(this.streamTracerFactories);
                arrayList.trimToSize();
                return Collections.unmodifiableList(arrayList);
            } catch (NoSuchMethodException e3) {
                log.log(Level.FINE, "Unable to apply census stats", (Throwable) e3);
                factory = null;
                if (factory != null) {
                }
                if (this.tracingEnabled) {
                }
                arrayList.addAll(this.streamTracerFactories);
                arrayList.trimToSize();
                return Collections.unmodifiableList(arrayList);
            } catch (InvocationTargetException e4) {
                log.log(Level.FINE, "Unable to apply census stats", (Throwable) e4);
                factory = null;
                if (factory != null) {
                }
                if (this.tracingEnabled) {
                }
                arrayList.addAll(this.streamTracerFactories);
                arrayList.trimToSize();
                return Collections.unmodifiableList(arrayList);
            }
            if (factory != null) {
                arrayList.add(factory);
            }
        }
        if (this.tracingEnabled) {
            try {
                factory2 = (ServerStreamTracer.Factory) Class.forName("io.grpc.census.InternalCensusTracingAccessor").getDeclaredMethod("getServerStreamTracerFactory", new Class[0]).invoke(null, new Object[0]);
            } catch (ClassNotFoundException e5) {
                log.log(Level.FINE, "Unable to apply census stats", (Throwable) e5);
                factory2 = null;
                if (factory2 != null) {
                }
                arrayList.addAll(this.streamTracerFactories);
                arrayList.trimToSize();
                return Collections.unmodifiableList(arrayList);
            } catch (IllegalAccessException e6) {
                log.log(Level.FINE, "Unable to apply census stats", (Throwable) e6);
                factory2 = null;
                if (factory2 != null) {
                }
                arrayList.addAll(this.streamTracerFactories);
                arrayList.trimToSize();
                return Collections.unmodifiableList(arrayList);
            } catch (NoSuchMethodException e7) {
                log.log(Level.FINE, "Unable to apply census stats", (Throwable) e7);
                factory2 = null;
                if (factory2 != null) {
                }
                arrayList.addAll(this.streamTracerFactories);
                arrayList.trimToSize();
                return Collections.unmodifiableList(arrayList);
            } catch (InvocationTargetException e8) {
                log.log(Level.FINE, "Unable to apply census stats", (Throwable) e8);
                factory2 = null;
                if (factory2 != null) {
                }
                arrayList.addAll(this.streamTracerFactories);
                arrayList.trimToSize();
                return Collections.unmodifiableList(arrayList);
            }
            if (factory2 != null) {
                arrayList.add(factory2);
            }
        }
        arrayList.addAll(this.streamTracerFactories);
        arrayList.trimToSize();
        return Collections.unmodifiableList(arrayList);
    }

    public InternalChannelz getChannelz() {
        return this.channelz;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class DefaultFallbackRegistry extends HandlerRegistry {
        @Override // io.grpc.HandlerRegistry
        @Nullable
        public ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2) {
            return null;
        }

        private DefaultFallbackRegistry() {
        }

        @Override // io.grpc.HandlerRegistry
        public List<ServerServiceDefinition> getServices() {
            return Collections.emptyList();
        }
    }

    public ObjectPool<? extends Executor> getExecutorPool() {
        return this.executorPool;
    }

    @Override // io.grpc.ServerBuilder
    public ServerImplBuilder useTransportSecurity(File file, File file2) {
        throw new UnsupportedOperationException("TLS not supported in ServerImplBuilder");
    }
}
