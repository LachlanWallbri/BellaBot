package io.grpc.netty.shaded.io.netty.bootstrap;

import io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.resolver.AddressResolver;
import io.grpc.netty.shaded.io.netty.resolver.AddressResolverGroup;
import io.grpc.netty.shaded.io.netty.resolver.DefaultAddressResolverGroup;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Bootstrap extends AbstractBootstrap<Bootstrap, Channel> {
    private final BootstrapConfig config;
    private volatile SocketAddress remoteAddress;
    private volatile AddressResolverGroup<SocketAddress> resolver;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Bootstrap.class);
    private static final AddressResolverGroup<?> DEFAULT_RESOLVER = DefaultAddressResolverGroup.INSTANCE;

    public Bootstrap() {
        this.config = new BootstrapConfig(this);
        this.resolver = DEFAULT_RESOLVER;
    }

    private Bootstrap(Bootstrap bootstrap) {
        super(bootstrap);
        this.config = new BootstrapConfig(this);
        this.resolver = DEFAULT_RESOLVER;
        this.resolver = bootstrap.resolver;
        this.remoteAddress = bootstrap.remoteAddress;
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
    
        r1 = r1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Bootstrap resolver(AddressResolverGroup<?> addressResolverGroup) {
        AddressResolverGroup addressResolverGroup2;
        if (addressResolverGroup == null) {
            addressResolverGroup2 = DEFAULT_RESOLVER;
        }
        this.resolver = addressResolverGroup2;
        return this;
    }

    public Bootstrap remoteAddress(SocketAddress socketAddress) {
        this.remoteAddress = socketAddress;
        return this;
    }

    public Bootstrap remoteAddress(String str, int i) {
        this.remoteAddress = InetSocketAddress.createUnresolved(str, i);
        return this;
    }

    public Bootstrap remoteAddress(InetAddress inetAddress, int i) {
        this.remoteAddress = new InetSocketAddress(inetAddress, i);
        return this;
    }

    public ChannelFuture connect() {
        validate();
        SocketAddress socketAddress = this.remoteAddress;
        if (socketAddress == null) {
            throw new IllegalStateException("remoteAddress not set");
        }
        return doResolveAndConnect(socketAddress, this.config.localAddress());
    }

    public ChannelFuture connect(String str, int i) {
        return connect(InetSocketAddress.createUnresolved(str, i));
    }

    public ChannelFuture connect(InetAddress inetAddress, int i) {
        return connect(new InetSocketAddress(inetAddress, i));
    }

    public ChannelFuture connect(SocketAddress socketAddress) {
        ObjectUtil.checkNotNull(socketAddress, "remoteAddress");
        validate();
        return doResolveAndConnect(socketAddress, this.config.localAddress());
    }

    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
        ObjectUtil.checkNotNull(socketAddress, "remoteAddress");
        validate();
        return doResolveAndConnect(socketAddress, socketAddress2);
    }

    private ChannelFuture doResolveAndConnect(final SocketAddress socketAddress, final SocketAddress socketAddress2) {
        ChannelFuture initAndRegister = initAndRegister();
        final Channel channel = initAndRegister.channel();
        if (initAndRegister.isDone()) {
            return !initAndRegister.isSuccess() ? initAndRegister : doResolveAndConnect0(channel, socketAddress, socketAddress2, channel.newPromise());
        }
        final AbstractBootstrap.PendingRegistrationPromise pendingRegistrationPromise = new AbstractBootstrap.PendingRegistrationPromise(channel);
        initAndRegister.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.bootstrap.Bootstrap.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                Throwable cause = channelFuture.cause();
                if (cause != null) {
                    pendingRegistrationPromise.setFailure(cause);
                } else {
                    pendingRegistrationPromise.registered();
                    Bootstrap.this.doResolveAndConnect0(channel, socketAddress, socketAddress2, pendingRegistrationPromise);
                }
            }
        });
        return pendingRegistrationPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ChannelFuture doResolveAndConnect0(final Channel channel, SocketAddress socketAddress, final SocketAddress socketAddress2, final ChannelPromise channelPromise) {
        AddressResolver<SocketAddress> resolver;
        try {
            try {
                resolver = this.resolver.getResolver(channel.eventLoop());
            } catch (Throwable th) {
                channel.close();
                return channelPromise.setFailure(th);
            }
        } catch (Throwable th2) {
            channelPromise.tryFailure(th2);
        }
        if (resolver.isSupported(socketAddress) && !resolver.isResolved(socketAddress)) {
            Future<SocketAddress> resolve = resolver.resolve(socketAddress);
            if (resolve.isDone()) {
                Throwable cause = resolve.cause();
                if (cause != null) {
                    channel.close();
                    channelPromise.setFailure(cause);
                } else {
                    doConnect(resolve.getNow(), socketAddress2, channelPromise);
                }
                return channelPromise;
            }
            resolve.addListener(new FutureListener<SocketAddress>() { // from class: io.grpc.netty.shaded.io.netty.bootstrap.Bootstrap.2
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<SocketAddress> future) throws Exception {
                    if (future.cause() == null) {
                        Bootstrap.doConnect(future.getNow(), socketAddress2, channelPromise);
                    } else {
                        channel.close();
                        channelPromise.setFailure(future.cause());
                    }
                }
            });
            return channelPromise;
        }
        doConnect(socketAddress, socketAddress2, channelPromise);
        return channelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doConnect(final SocketAddress socketAddress, final SocketAddress socketAddress2, final ChannelPromise channelPromise) {
        final Channel channel = channelPromise.channel();
        channel.eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.bootstrap.Bootstrap.3
            @Override // java.lang.Runnable
            public void run() {
                SocketAddress socketAddress3 = socketAddress2;
                if (socketAddress3 == null) {
                    channel.connect(socketAddress, channelPromise);
                } else {
                    channel.connect(socketAddress, socketAddress3, channelPromise);
                }
                channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        });
    }

    @Override // io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap
    void init(Channel channel) {
        channel.pipeline().addLast(this.config.handler());
        setChannelOptions(channel, newOptionsArray(), logger);
        setAttributes(channel, (Map.Entry[]) attrs0().entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY));
    }

    @Override // io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap
    public Bootstrap validate() {
        super.validate();
        if (this.config.handler() != null) {
            return this;
        }
        throw new IllegalStateException("handler not set");
    }

    @Override // io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap
    /* renamed from: clone, reason: avoid collision after fix types in other method */
    public Bootstrap mo4504clone() {
        return new Bootstrap(this);
    }

    public Bootstrap clone(EventLoopGroup eventLoopGroup) {
        Bootstrap bootstrap = new Bootstrap(this);
        bootstrap.group = eventLoopGroup;
        return bootstrap;
    }

    @Override // io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap
    public final AbstractBootstrapConfig<Bootstrap, Channel> config() {
        return this.config;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SocketAddress remoteAddress() {
        return this.remoteAddress;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final AddressResolverGroup<?> resolver() {
        return this.resolver;
    }
}
