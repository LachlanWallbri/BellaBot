package io.grpc;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.common.base.Preconditions;
import io.grpc.ServerBuilder;
import io.grpc.ServerStreamTracer;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ServerBuilder<T extends ServerBuilder<T>> {
    private T thisT() {
        return this;
    }

    public abstract T addService(BindableService bindableService);

    public abstract T addService(ServerServiceDefinition serverServiceDefinition);

    public abstract Server build();

    public abstract T compressorRegistry(@Nullable CompressorRegistry compressorRegistry);

    public abstract T decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry);

    public abstract T directExecutor();

    public abstract T executor(@Nullable Executor executor);

    public abstract T fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry);

    public abstract T useTransportSecurity(File file, File file2);

    public static ServerBuilder<?> forPort(int i) {
        return ServerProvider.provider().builderForPort(i);
    }

    public final T addServices(List<ServerServiceDefinition> list) {
        Preconditions.checkNotNull(list, TmpConstant.DEVICE_MODEL_SERVICES);
        Iterator<ServerServiceDefinition> it = list.iterator();
        while (it.hasNext()) {
            addService(it.next());
        }
        return thisT();
    }

    public T intercept(ServerInterceptor serverInterceptor) {
        throw new UnsupportedOperationException();
    }

    public T addTransportFilter(ServerTransportFilter serverTransportFilter) {
        throw new UnsupportedOperationException();
    }

    public T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        throw new UnsupportedOperationException();
    }

    public T useTransportSecurity(InputStream inputStream, InputStream inputStream2) {
        throw new UnsupportedOperationException();
    }

    public T handshakeTimeout(long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public T maxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "bytes must be >= 0");
        return thisT();
    }

    public T maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        return thisT();
    }

    public T setBinaryLog(BinaryLog binaryLog) {
        throw new UnsupportedOperationException();
    }
}
