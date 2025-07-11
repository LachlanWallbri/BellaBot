package io.grpc.internal;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.InternalMetadata;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ProxiedSocketAddress;
import io.grpc.ProxyDetector;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.StreamListener;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class GrpcUtil {
    public static final String CONTENT_ACCEPT_ENCODING = "accept-encoding";
    public static final Metadata.Key<byte[]> CONTENT_ACCEPT_ENCODING_KEY;
    public static final String CONTENT_TYPE_GRPC = "application/grpc";
    public static final String DEFAULT_LB_POLICY = "pick_first";
    public static final int DEFAULT_MAX_HEADER_LIST_SIZE = 8192;
    public static final int DEFAULT_MAX_MESSAGE_SIZE = 4194304;
    public static final int DEFAULT_PORT_PLAINTEXT = 80;
    public static final int DEFAULT_PORT_SSL = 443;
    public static final String HTTP_METHOD = "POST";
    private static final String IMPLEMENTATION_VERSION = "1.39.0";
    public static final long KEEPALIVE_TIME_NANOS_DISABLED = Long.MAX_VALUE;
    public static final String MESSAGE_ACCEPT_ENCODING = "grpc-accept-encoding";
    public static final Metadata.Key<byte[]> MESSAGE_ACCEPT_ENCODING_KEY;
    public static final long SERVER_KEEPALIVE_TIME_NANOS_DISABLED = Long.MAX_VALUE;
    public static final String TE_TRAILERS = "trailers";
    private static final Logger log = Logger.getLogger(GrpcUtil.class.getName());
    public static final Charset US_ASCII = Charset.forName("US-ASCII");
    public static final String TIMEOUT = "grpc-timeout";
    public static final Metadata.Key<Long> TIMEOUT_KEY = Metadata.Key.m3901of(TIMEOUT, new TimeoutMarshaller());
    public static final String MESSAGE_ENCODING = "grpc-encoding";
    public static final Metadata.Key<String> MESSAGE_ENCODING_KEY = Metadata.Key.m3901of(MESSAGE_ENCODING, Metadata.ASCII_STRING_MARSHALLER);
    public static final String CONTENT_ENCODING = "content-encoding";
    public static final Metadata.Key<String> CONTENT_ENCODING_KEY = Metadata.Key.m3901of(CONTENT_ENCODING, Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> CONTENT_TYPE_KEY = Metadata.Key.m3901of("content-type", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> TE_HEADER = Metadata.Key.m3901of("te", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> USER_AGENT_KEY = Metadata.Key.m3901of("user-agent", Metadata.ASCII_STRING_MARSHALLER);
    public static final Splitter ACCEPT_ENCODING_SPLITTER = Splitter.m615on(',').trimResults();
    public static final long DEFAULT_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20);
    public static final long DEFAULT_SERVER_KEEPALIVE_TIME_NANOS = TimeUnit.HOURS.toNanos(2);
    public static final long DEFAULT_SERVER_KEEPALIVE_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(20);
    public static final ProxyDetector DEFAULT_PROXY_DETECTOR = new ProxyDetectorImpl();
    public static final ProxyDetector NOOP_PROXY_DETECTOR = new ProxyDetector() { // from class: io.grpc.internal.GrpcUtil.1
        @Override // io.grpc.ProxyDetector
        @Nullable
        public ProxiedSocketAddress proxyFor(SocketAddress socketAddress) {
            return null;
        }
    };
    public static final CallOptions.Key<Boolean> CALL_OPTIONS_RPC_OWNED_BY_BALANCER = CallOptions.Key.create("io.grpc.internal.CALL_OPTIONS_RPC_OWNED_BY_BALANCER");
    public static final SharedResourceHolder.Resource<Executor> SHARED_CHANNEL_EXECUTOR = new SharedResourceHolder.Resource<Executor>() { // from class: io.grpc.internal.GrpcUtil.2
        private static final String NAME = "grpc-default-executor";

        public String toString() {
            return NAME;
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public Executor create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-default-executor-%d", true));
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public void close(Executor executor) {
            ((ExecutorService) executor).shutdown();
        }
    };
    public static final SharedResourceHolder.Resource<ScheduledExecutorService> TIMER_SERVICE = new SharedResourceHolder.Resource<ScheduledExecutorService>() { // from class: io.grpc.internal.GrpcUtil.3
        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public ScheduledExecutorService create() {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, GrpcUtil.getThreadFactory("grpc-timer-%d", true));
            try {
                newScheduledThreadPool.getClass().getMethod("setRemoveOnCancelPolicy", Boolean.TYPE).invoke(newScheduledThreadPool, true);
            } catch (NoSuchMethodException unused) {
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
            return Executors.unconfigurableScheduledExecutorService(newScheduledThreadPool);
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public void close(ScheduledExecutorService scheduledExecutorService) {
            scheduledExecutorService.shutdown();
        }
    };
    public static final Supplier<Stopwatch> STOPWATCH_SUPPLIER = new Supplier<Stopwatch>() { // from class: io.grpc.internal.GrpcUtil.4
        @Override // com.google.common.base.Supplier, java.util.function.Supplier
        public Stopwatch get() {
            return Stopwatch.createUnstarted();
        }
    };

    static {
        MESSAGE_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf(MESSAGE_ACCEPT_ENCODING, new AcceptEncodingMarshaller());
        CONTENT_ACCEPT_ENCODING_KEY = InternalMetadata.keyOf(CONTENT_ACCEPT_ENCODING, new AcceptEncodingMarshaller());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class AcceptEncodingMarshaller implements InternalMetadata.TrustedAsciiMarshaller<byte[]> {
        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public byte[] parseAsciiString(byte[] bArr) {
            return bArr;
        }

        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public byte[] toAsciiString(byte[] bArr) {
            return bArr;
        }

        private AcceptEncodingMarshaller() {
        }
    }

    public static boolean shouldBeCountedForInUse(CallOptions callOptions) {
        return !Boolean.TRUE.equals(callOptions.getOption(CALL_OPTIONS_RPC_OWNED_BY_BALANCER));
    }

    public static Status httpStatusToGrpcStatus(int i) {
        return httpStatusToGrpcCode(i).toStatus().withDescription("HTTP status code " + i);
    }

    private static Status.Code httpStatusToGrpcCode(int i) {
        if (i >= 100 && i < 200) {
            return Status.Code.INTERNAL;
        }
        if (i != 400) {
            if (i == 401) {
                return Status.Code.UNAUTHENTICATED;
            }
            if (i == 403) {
                return Status.Code.PERMISSION_DENIED;
            }
            if (i == 404) {
                return Status.Code.UNIMPLEMENTED;
            }
            if (i != 429) {
                if (i != 431) {
                    switch (i) {
                        case 502:
                        case 503:
                        case 504:
                            break;
                        default:
                            return Status.Code.UNKNOWN;
                    }
                }
            }
            return Status.Code.UNAVAILABLE;
        }
        return Status.Code.INTERNAL;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum Http2Error {
        NO_ERROR(0, Status.UNAVAILABLE),
        PROTOCOL_ERROR(1, Status.INTERNAL),
        INTERNAL_ERROR(2, Status.INTERNAL),
        FLOW_CONTROL_ERROR(3, Status.INTERNAL),
        SETTINGS_TIMEOUT(4, Status.INTERNAL),
        STREAM_CLOSED(5, Status.INTERNAL),
        FRAME_SIZE_ERROR(6, Status.INTERNAL),
        REFUSED_STREAM(7, Status.UNAVAILABLE),
        CANCEL(8, Status.CANCELLED),
        COMPRESSION_ERROR(9, Status.INTERNAL),
        CONNECT_ERROR(10, Status.INTERNAL),
        ENHANCE_YOUR_CALM(11, Status.RESOURCE_EXHAUSTED.withDescription("Bandwidth exhausted")),
        INADEQUATE_SECURITY(12, Status.PERMISSION_DENIED.withDescription("Permission denied as protocol is not secure enough to call")),
        HTTP_1_1_REQUIRED(13, Status.UNKNOWN);

        private static final Http2Error[] codeMap = buildHttp2CodeMap();
        private final int code;
        private final Status status;

        private static Http2Error[] buildHttp2CodeMap() {
            Http2Error[] values = values();
            Http2Error[] http2ErrorArr = new Http2Error[((int) values[values.length - 1].code()) + 1];
            for (Http2Error http2Error : values) {
                http2ErrorArr[(int) http2Error.code()] = http2Error;
            }
            return http2ErrorArr;
        }

        Http2Error(int i, Status status) {
            this.code = i;
            String str = "HTTP/2 error code: " + name();
            if (status.getDescription() != null) {
                str = str + " (" + status.getDescription() + ")";
            }
            this.status = status.withDescription(str);
        }

        public long code() {
            return this.code;
        }

        public Status status() {
            return this.status;
        }

        public static Http2Error forCode(long j) {
            Http2Error[] http2ErrorArr = codeMap;
            if (j >= http2ErrorArr.length || j < 0) {
                return null;
            }
            return http2ErrorArr[(int) j];
        }

        public static Status statusForCode(long j) {
            Http2Error forCode = forCode(j);
            if (forCode == null) {
                return Status.fromCodeValue(INTERNAL_ERROR.status().getCode().value()).withDescription("Unrecognized HTTP/2 error code: " + j);
            }
            return forCode.status();
        }
    }

    public static boolean isGrpcContentType(String str) {
        if (str == null || 16 > str.length()) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        if (!lowerCase.startsWith(CONTENT_TYPE_GRPC)) {
            return false;
        }
        if (lowerCase.length() == 16) {
            return true;
        }
        char charAt = lowerCase.charAt(16);
        return charAt == '+' || charAt == ';';
    }

    public static String getGrpcUserAgent(String str, @Nullable String str2) {
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            sb.append(str2);
            sb.append(' ');
        }
        sb.append("grpc-java-");
        sb.append(str);
        sb.append('/');
        sb.append(IMPLEMENTATION_VERSION);
        return sb.toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class GrpcBuildVersion {
        private final String implementationVersion;
        private final String userAgent;

        private GrpcBuildVersion(String str, String str2) {
            this.userAgent = (String) Preconditions.checkNotNull(str, "userAgentName");
            this.implementationVersion = (String) Preconditions.checkNotNull(str2, "implementationVersion");
        }

        public String getUserAgent() {
            return this.userAgent;
        }

        public String getImplementationVersion() {
            return this.implementationVersion;
        }

        public String toString() {
            return this.userAgent + " " + this.implementationVersion;
        }
    }

    public static GrpcBuildVersion getGrpcBuildVersion() {
        return new GrpcBuildVersion("gRPC Java", IMPLEMENTATION_VERSION);
    }

    public static URI authorityToUri(String str) {
        Preconditions.checkNotNull(str, "authority");
        try {
            return new URI(null, str, null, null, null);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid authority: " + str, e);
        }
    }

    public static String checkAuthority(String str) {
        URI authorityToUri = authorityToUri(str);
        Preconditions.checkArgument(authorityToUri.getHost() != null, "No host in authority '%s'", str);
        Preconditions.checkArgument(authorityToUri.getUserInfo() == null, "Userinfo must not be present on authority: '%s'", str);
        return str;
    }

    public static String authorityFromHostAndPort(String str, int i) {
        try {
            return new URI(null, null, str, i, null, null, null).getAuthority();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid host or port: " + str + " " + i, e);
        }
    }

    public static ThreadFactory getThreadFactory(String str, boolean z) {
        return new ThreadFactoryBuilder().setDaemon(z).setNameFormat(str).build();
    }

    public static String getHost(InetSocketAddress inetSocketAddress) {
        try {
            return (String) InetSocketAddress.class.getMethod("getHostString", new Class[0]).invoke(inetSocketAddress, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return inetSocketAddress.getHostName();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static class TimeoutMarshaller implements Metadata.AsciiMarshaller<Long> {
        TimeoutMarshaller() {
        }

        @Override // io.grpc.Metadata.AsciiMarshaller
        public String toAsciiString(Long l) {
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            if (l.longValue() < 0) {
                throw new IllegalArgumentException("Timeout too small");
            }
            if (l.longValue() < 100000000) {
                return l + "n";
            }
            if (l.longValue() < 100000000000L) {
                return timeUnit.toMicros(l.longValue()) + "u";
            }
            if (l.longValue() < 100000000000000L) {
                return timeUnit.toMillis(l.longValue()) + "m";
            }
            if (l.longValue() < 100000000000000000L) {
                return timeUnit.toSeconds(l.longValue()) + ExifInterface.LATITUDE_SOUTH;
            }
            if (l.longValue() < 6000000000000000000L) {
                return timeUnit.toMinutes(l.longValue()) + "M";
            }
            return timeUnit.toHours(l.longValue()) + "H";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.Metadata.AsciiMarshaller
        public Long parseAsciiString(String str) {
            Preconditions.checkArgument(str.length() > 0, "empty timeout");
            Preconditions.checkArgument(str.length() <= 9, "bad timeout format");
            long parseLong = Long.parseLong(str.substring(0, str.length() - 1));
            char charAt = str.charAt(str.length() - 1);
            if (charAt == 'H') {
                return Long.valueOf(TimeUnit.HOURS.toNanos(parseLong));
            }
            if (charAt == 'M') {
                return Long.valueOf(TimeUnit.MINUTES.toNanos(parseLong));
            }
            if (charAt == 'S') {
                return Long.valueOf(TimeUnit.SECONDS.toNanos(parseLong));
            }
            if (charAt == 'u') {
                return Long.valueOf(TimeUnit.MICROSECONDS.toNanos(parseLong));
            }
            if (charAt == 'm') {
                return Long.valueOf(TimeUnit.MILLISECONDS.toNanos(parseLong));
            }
            if (charAt == 'n') {
                return Long.valueOf(parseLong);
            }
            throw new IllegalArgumentException(String.format("Invalid timeout unit: %s", Character.valueOf(charAt)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static ClientTransport getTransportFromPickResult(LoadBalancer.PickResult pickResult, boolean z) {
        LoadBalancer.Subchannel subchannel = pickResult.getSubchannel();
        final ClientTransport obtainActiveTransport = subchannel != null ? ((TransportProvider) subchannel.getInternalSubchannel()).obtainActiveTransport() : null;
        if (obtainActiveTransport != null) {
            final ClientStreamTracer.Factory streamTracerFactory = pickResult.getStreamTracerFactory();
            return streamTracerFactory == null ? obtainActiveTransport : new ClientTransport() { // from class: io.grpc.internal.GrpcUtil.5
                @Override // io.grpc.internal.ClientTransport
                public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
                    return ClientTransport.this.newStream(methodDescriptor, metadata, callOptions.withStreamTracerFactory(streamTracerFactory));
                }

                @Override // io.grpc.internal.ClientTransport
                public void ping(ClientTransport.PingCallback pingCallback, Executor executor) {
                    ClientTransport.this.ping(pingCallback, executor);
                }

                @Override // io.grpc.InternalWithLogId
                public InternalLogId getLogId() {
                    return ClientTransport.this.getLogId();
                }

                @Override // io.grpc.InternalInstrumented
                public ListenableFuture<InternalChannelz.SocketStats> getStats() {
                    return ClientTransport.this.getStats();
                }
            };
        }
        if (!pickResult.getStatus().isOk()) {
            if (pickResult.isDrop()) {
                return new FailingClientTransport(pickResult.getStatus(), ClientStreamListener.RpcProgress.DROPPED);
            }
            if (!z) {
                return new FailingClientTransport(pickResult.getStatus(), ClientStreamListener.RpcProgress.PROCESSED);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void closeQuietly(StreamListener.MessageProducer messageProducer) {
        while (true) {
            InputStream next = messageProducer.next();
            if (next == null) {
                return;
            } else {
                closeQuietly(next);
            }
        }
    }

    public static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            log.log(Level.WARNING, "exception caught in closeQuietly", (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> boolean iterableContains(Iterable<T> iterable, T t) {
        if (iterable instanceof Collection) {
            try {
                return ((Collection) iterable).contains(t);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        }
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            if (Objects.equal(it.next(), t)) {
                return true;
            }
        }
        return false;
    }

    private GrpcUtil() {
    }
}
