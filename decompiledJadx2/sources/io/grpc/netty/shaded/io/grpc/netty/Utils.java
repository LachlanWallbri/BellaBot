package io.grpc.netty.shaded.io.grpc.netty;

import com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import io.grpc.InternalChannelz;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.TransportFrameUtil;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2HeadersUtils;
import io.grpc.netty.shaded.io.grpc.netty.NettySocketSupport;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.PooledByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelFactory;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.ReflectiveChannelFactory;
import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioServerSocketChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioSocketChannel;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderException;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.NettyRuntime;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultThreadFactory;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.UnresolvedAddressException;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.net.ssl.SSLException;
import org.apache.http.HttpHost;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Utils {
    public static final SharedResourceHolder.Resource<EventLoopGroup> DEFAULT_BOSS_EVENT_LOOP_GROUP;
    public static final Class<? extends Channel> DEFAULT_CLIENT_CHANNEL_TYPE;
    public static final ChannelFactory<? extends ServerChannel> DEFAULT_SERVER_CHANNEL_FACTORY;
    public static final SharedResourceHolder.Resource<EventLoopGroup> DEFAULT_WORKER_EVENT_LOOP_GROUP;

    @Nullable
    private static final Constructor<? extends EventLoopGroup> EPOLL_EVENT_LOOP_GROUP_CONSTRUCTOR;
    private static final Logger logger = Logger.getLogger(Utils.class.getName());
    public static final AsciiString STATUS_OK = AsciiString.m3920of(ErrorCode.UNKNOWN_SUCCESS_CODE);
    public static final AsciiString HTTP_METHOD = AsciiString.m3920of("POST");
    public static final AsciiString HTTP_GET_METHOD = AsciiString.m3920of("GET");
    public static final AsciiString HTTPS = AsciiString.m3920of("https");
    public static final AsciiString HTTP = AsciiString.m3920of(HttpHost.DEFAULT_SCHEME_NAME);
    public static final AsciiString CONTENT_TYPE_HEADER = AsciiString.m3920of(GrpcUtil.CONTENT_TYPE_KEY.name());
    public static final AsciiString CONTENT_TYPE_GRPC = AsciiString.m3920of(GrpcUtil.CONTENT_TYPE_GRPC);
    public static final AsciiString TE_HEADER = AsciiString.m3920of(GrpcUtil.TE_HEADER.name());
    public static final AsciiString TE_TRAILERS = AsciiString.m3920of("trailers");
    public static final AsciiString USER_AGENT = AsciiString.m3920of(GrpcUtil.USER_AGENT_KEY.name());
    public static final SharedResourceHolder.Resource<EventLoopGroup> NIO_BOSS_EVENT_LOOP_GROUP = new DefaultEventLoopGroupResource(1, "grpc-nio-boss-ELG", EventLoopGroupType.NIO);
    public static final SharedResourceHolder.Resource<EventLoopGroup> NIO_WORKER_EVENT_LOOP_GROUP = new DefaultEventLoopGroupResource(0, "grpc-nio-worker-ELG", EventLoopGroupType.NIO);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum EventLoopGroupType {
        NIO,
        EPOLL
    }

    static {
        if (isEpollAvailable()) {
            DEFAULT_CLIENT_CHANNEL_TYPE = epollChannelType();
            DEFAULT_SERVER_CHANNEL_FACTORY = new ReflectiveChannelFactory(epollServerChannelType());
            EPOLL_EVENT_LOOP_GROUP_CONSTRUCTOR = epollEventLoopGroupConstructor();
            DEFAULT_BOSS_EVENT_LOOP_GROUP = new DefaultEventLoopGroupResource(1, "grpc-default-boss-ELG", EventLoopGroupType.EPOLL);
            DEFAULT_WORKER_EVENT_LOOP_GROUP = new DefaultEventLoopGroupResource(0, "grpc-default-worker-ELG", EventLoopGroupType.EPOLL);
            return;
        }
        logger.log(Level.FINE, "Epoll is not available, using Nio.", getEpollUnavailabilityCause());
        DEFAULT_SERVER_CHANNEL_FACTORY = nioServerChannelFactory();
        DEFAULT_CLIENT_CHANNEL_TYPE = NioSocketChannel.class;
        DEFAULT_BOSS_EVENT_LOOP_GROUP = NIO_BOSS_EVENT_LOOP_GROUP;
        DEFAULT_WORKER_EVENT_LOOP_GROUP = NIO_WORKER_EVENT_LOOP_GROUP;
        EPOLL_EVENT_LOOP_GROUP_CONSTRUCTOR = null;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class ByteBufAllocatorPreferDirectHolder {
        private static final ByteBufAllocator allocator = Utils.createByteBufAllocator(true);

        private ByteBufAllocatorPreferDirectHolder() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class ByteBufAllocatorPreferHeapHolder {
        private static final ByteBufAllocator allocator = Utils.createByteBufAllocator(false);

        private ByteBufAllocatorPreferHeapHolder() {
        }
    }

    public static ByteBufAllocator getByteBufAllocator(boolean z) {
        if (Boolean.parseBoolean(System.getProperty("io.grpc.netty.shaded.io.grpc.netty.useCustomAllocator", "true"))) {
            boolean defaultPreferDirect = PooledByteBufAllocator.defaultPreferDirect();
            logger.log(Level.FINE, String.format("Using custom allocator: forceHeapBuffer=%s, defaultPreferDirect=%s", Boolean.valueOf(z), Boolean.valueOf(defaultPreferDirect)));
            if (!z && defaultPreferDirect) {
                return ByteBufAllocatorPreferDirectHolder.allocator;
            }
            return ByteBufAllocatorPreferHeapHolder.allocator;
        }
        logger.log(Level.FINE, "Using default allocator");
        return ByteBufAllocator.DEFAULT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteBufAllocator createByteBufAllocator(boolean z) {
        int defaultMaxOrder;
        logger.log(Level.FINE, "Creating allocator, preferDirect=" + z);
        if (System.getProperty("io.grpc.netty.shaded.io.netty.allocator.maxOrder") == null) {
            defaultMaxOrder = 8;
            logger.log(Level.FINE, "Forcing maxOrder=8");
        } else {
            defaultMaxOrder = PooledByteBufAllocator.defaultMaxOrder();
            logger.log(Level.FINE, "Using default maxOrder=" + defaultMaxOrder);
        }
        return new PooledByteBufAllocator(z, PooledByteBufAllocator.defaultNumHeapArena(), z ? PooledByteBufAllocator.defaultNumDirectArena() : 0, PooledByteBufAllocator.defaultPageSize(), defaultMaxOrder, PooledByteBufAllocator.defaultSmallCacheSize(), PooledByteBufAllocator.defaultNormalCacheSize(), PooledByteBufAllocator.defaultUseCacheForAllThreads());
    }

    public static Metadata convertHeaders(Http2Headers http2Headers) {
        if (http2Headers instanceof GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders) {
            GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders grpcHttp2InboundHeaders = (GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders) http2Headers;
            return InternalMetadata.newMetadata(grpcHttp2InboundHeaders.numHeaders(), grpcHttp2InboundHeaders.namesAndValues());
        }
        return InternalMetadata.newMetadata(convertHeadersToArray(http2Headers));
    }

    @CheckReturnValue
    private static byte[][] convertHeadersToArray(Http2Headers http2Headers) {
        byte[][] bArr = new byte[http2Headers.size() * 2];
        int i = 0;
        for (Map.Entry<CharSequence, CharSequence> entry : http2Headers) {
            int i2 = i + 1;
            bArr[i] = bytes(entry.getKey());
            i = i2 + 1;
            bArr[i2] = bytes(entry.getValue());
        }
        return TransportFrameUtil.toRawSerializedHeaders(bArr);
    }

    private static byte[] bytes(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            AsciiString asciiString = (AsciiString) charSequence;
            return asciiString.isEntireArrayUsed() ? asciiString.array() : asciiString.toByteArray();
        }
        return charSequence.toString().getBytes(CharsetUtil.UTF_8);
    }

    public static Http2Headers convertClientHeaders(Metadata metadata, AsciiString asciiString, AsciiString asciiString2, AsciiString asciiString3, AsciiString asciiString4, AsciiString asciiString5) {
        Preconditions.checkNotNull(asciiString2, "defaultPath");
        Preconditions.checkNotNull(asciiString3, "authority");
        Preconditions.checkNotNull(asciiString4, "method");
        metadata.discardAll(GrpcUtil.CONTENT_TYPE_KEY);
        metadata.discardAll(GrpcUtil.TE_HEADER);
        metadata.discardAll(GrpcUtil.USER_AGENT_KEY);
        return GrpcHttp2OutboundHeaders.clientRequestHeaders(TransportFrameUtil.toHttp2Headers(metadata), asciiString3, asciiString2, asciiString4, asciiString, asciiString5);
    }

    public static Http2Headers convertServerHeaders(Metadata metadata) {
        metadata.discardAll(GrpcUtil.CONTENT_TYPE_KEY);
        metadata.discardAll(GrpcUtil.TE_HEADER);
        metadata.discardAll(GrpcUtil.USER_AGENT_KEY);
        return GrpcHttp2OutboundHeaders.serverResponseHeaders(TransportFrameUtil.toHttp2Headers(metadata));
    }

    public static Metadata convertTrailers(Http2Headers http2Headers) {
        if (http2Headers instanceof GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders) {
            GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders grpcHttp2InboundHeaders = (GrpcHttp2HeadersUtils.GrpcHttp2InboundHeaders) http2Headers;
            return InternalMetadata.newMetadata(grpcHttp2InboundHeaders.numHeaders(), grpcHttp2InboundHeaders.namesAndValues());
        }
        return InternalMetadata.newMetadata(convertHeadersToArray(http2Headers));
    }

    public static Http2Headers convertTrailers(Metadata metadata, boolean z) {
        if (!z) {
            return convertServerHeaders(metadata);
        }
        return GrpcHttp2OutboundHeaders.serverResponseTrailers(TransportFrameUtil.toHttp2Headers(metadata));
    }

    public static Status statusFromThrowable(Throwable th) {
        Status fromThrowable = Status.fromThrowable(th);
        if (fromThrowable.getCode() != Status.Code.UNKNOWN) {
            return fromThrowable;
        }
        if (th instanceof ClosedChannelException) {
            ClosedChannelException closedChannelException = new ClosedChannelException();
            closedChannelException.initCause(th);
            return Status.UNKNOWN.withDescription("channel closed").withCause(closedChannelException);
        }
        if ((th instanceof DecoderException) && (th.getCause() instanceof SSLException)) {
            return Status.UNAVAILABLE.withDescription("ssl exception").withCause(th);
        }
        if (th instanceof IOException) {
            return Status.UNAVAILABLE.withDescription("io exception").withCause(th);
        }
        if (th instanceof UnresolvedAddressException) {
            return Status.UNAVAILABLE.withDescription("unresolved address").withCause(th);
        }
        return th instanceof Http2Exception ? Status.INTERNAL.withDescription("http2 exception").withCause(th) : fromThrowable;
    }

    static boolean isEpollAvailable() {
        try {
            return ((Boolean) Class.forName("io.grpc.netty.shaded.io.netty.channel.epoll.Epoll").getDeclaredMethod("isAvailable", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Exception while checking Epoll availability", e);
        }
    }

    private static Throwable getEpollUnavailabilityCause() {
        try {
            return (Throwable) Class.forName("io.grpc.netty.shaded.io.netty.channel.epoll.Epoll").getDeclaredMethod("unavailabilityCause", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return e;
        }
    }

    private static Class<? extends Channel> epollChannelType() {
        try {
            return Class.forName("io.grpc.netty.shaded.io.netty.channel.epoll.EpollSocketChannel").asSubclass(Channel.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot load EpollSocketChannel", e);
        }
    }

    private static Constructor<? extends EventLoopGroup> epollEventLoopGroupConstructor() {
        try {
            return Class.forName("io.grpc.netty.shaded.io.netty.channel.epoll.EpollEventLoopGroup").asSubclass(EventLoopGroup.class).getConstructor(Integer.TYPE, ThreadFactory.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot load EpollEventLoopGroup", e);
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException("EpollEventLoopGroup constructor not found", e2);
        }
    }

    private static Class<? extends ServerChannel> epollServerChannelType() {
        try {
            return Class.forName("io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerSocketChannel").asSubclass(ServerChannel.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot load EpollServerSocketChannel", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EventLoopGroup createEpollEventLoopGroup(int i, ThreadFactory threadFactory) {
        Preconditions.checkState(EPOLL_EVENT_LOOP_GROUP_CONSTRUCTOR != null, "Epoll is not available");
        try {
            return EPOLL_EVENT_LOOP_GROUP_CONSTRUCTOR.newInstance(Integer.valueOf(i), threadFactory);
        } catch (Exception e) {
            throw new RuntimeException("Cannot create Epoll EventLoopGroup", e);
        }
    }

    private static ChannelFactory<ServerChannel> nioServerChannelFactory() {
        return new ChannelFactory<ServerChannel>() { // from class: io.grpc.netty.shaded.io.grpc.netty.Utils.1
            @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFactory, io.grpc.netty.shaded.io.netty.bootstrap.ChannelFactory
            public ServerChannel newChannel() {
                return new NioServerSocketChannel();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static ChannelOption<Integer> maybeGetTcpUserTimeoutOption() {
        return getEpollChannelOption("TCP_USER_TIMEOUT");
    }

    @Nullable
    private static <T> ChannelOption<T> getEpollChannelOption(String str) {
        if (!isEpollAvailable()) {
            return null;
        }
        try {
            return (ChannelOption) Class.forName("io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelOption").getField(str).get(null);
        } catch (Exception e) {
            throw new RuntimeException("ChannelOption(" + str + ") is not available", e);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class DefaultEventLoopGroupResource implements SharedResourceHolder.Resource<EventLoopGroup> {
        private final EventLoopGroupType eventLoopGroupType;
        private final String name;
        private final int numEventLoops;

        DefaultEventLoopGroupResource(int i, String str, EventLoopGroupType eventLoopGroupType) {
            this.name = str;
            if (i == 0 && System.getProperty("io.grpc.netty.shaded.io.netty.eventLoopThreads") == null) {
                this.numEventLoops = NettyRuntime.availableProcessors();
            } else {
                this.numEventLoops = i;
            }
            this.eventLoopGroupType = eventLoopGroupType;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public EventLoopGroup create() {
            DefaultThreadFactory defaultThreadFactory = new DefaultThreadFactory(this.name, true);
            int i = C62542.$SwitchMap$io$grpc$netty$Utils$EventLoopGroupType[this.eventLoopGroupType.ordinal()];
            if (i == 1) {
                return new NioEventLoopGroup(this.numEventLoops, defaultThreadFactory);
            }
            if (i == 2) {
                return Utils.createEpollEventLoopGroup(this.numEventLoops, defaultThreadFactory);
            }
            throw new AssertionError("Unknown/Unsupported EventLoopGroupType: " + this.eventLoopGroupType);
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public void close(EventLoopGroup eventLoopGroup) {
            eventLoopGroup.shutdownGracefully(0L, 0L, TimeUnit.SECONDS);
        }

        public String toString() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.grpc.netty.Utils$2 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C62542 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$netty$Utils$EventLoopGroupType = new int[EventLoopGroupType.values().length];

        static {
            try {
                $SwitchMap$io$grpc$netty$Utils$EventLoopGroupType[EventLoopGroupType.NIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$netty$Utils$EventLoopGroupType[EventLoopGroupType.EPOLL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InternalChannelz.SocketOptions getSocketOptions(Channel channel) {
        ChannelConfig config = channel.config();
        InternalChannelz.SocketOptions.Builder builder = new InternalChannelz.SocketOptions.Builder();
        Integer num = (Integer) config.getOption(ChannelOption.SO_LINGER);
        if (num != null) {
            builder.setSocketOptionLingerSeconds(num);
        }
        Integer num2 = (Integer) config.getOption(ChannelOption.SO_TIMEOUT);
        if (num2 != null) {
            builder.setSocketOptionTimeoutMillis(num2);
        }
        for (Map.Entry<ChannelOption<?>, Object> entry : config.getOptions().entrySet()) {
            ChannelOption<?> key = entry.getKey();
            if (!key.equals(ChannelOption.SO_LINGER) && !key.equals(ChannelOption.SO_TIMEOUT)) {
                builder.addOption(key.name(), String.valueOf(entry.getValue()));
            }
        }
        NettySocketSupport.NativeSocketOptions nativeSocketOptions = NettySocketSupport.getNativeSocketOptions(channel);
        if (nativeSocketOptions != null) {
            builder.setTcpInfo(nativeSocketOptions.tcpInfo);
            UnmodifiableIterator<Map.Entry<String, String>> it = nativeSocketOptions.otherInfo.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> next = it.next();
                builder.addOption(next.getKey(), next.getValue());
            }
        }
        return builder.build();
    }

    private Utils() {
    }
}
