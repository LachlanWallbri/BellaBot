package io.grpc.alts;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioSocketChannel;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class HandshakerServiceChannel {
    static final SharedResourceHolder.Resource<Channel> SHARED_HANDSHAKER_CHANNEL = new ChannelResource("metadata.google.internal.:8080");

    HandshakerServiceChannel() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SharedResourceHolder.Resource<Channel> getHandshakerChannelForTesting(String str) {
        return new ChannelResource(str);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class ChannelResource implements SharedResourceHolder.Resource<Channel> {
        private final String target;

        public String toString() {
            return "grpc-alts-handshaker-service-channel";
        }

        public ChannelResource(String str) {
            this.target = str;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public Channel create() {
            NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("handshaker pool", true));
            return new EventLoopHoldingChannel(((NettyChannelBuilder) NettyChannelBuilder.forTarget(this.target).channelType(NioSocketChannel.class).directExecutor()).eventLoopGroup(nioEventLoopGroup).usePlaintext().build(), nioEventLoopGroup);
        }

        @Override // io.grpc.internal.SharedResourceHolder.Resource
        public void close(Channel channel) {
            ((EventLoopHoldingChannel) channel).close();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static abstract class ForwardingChannel extends Channel {
        protected abstract Channel delegate();

        private ForwardingChannel() {
        }

        @Override // io.grpc.Channel
        public String authority() {
            return delegate().authority();
        }

        @Override // io.grpc.Channel
        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            return delegate().newCall(methodDescriptor, callOptions);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class EventLoopHoldingChannel extends ForwardingChannel {
        private final ManagedChannel delegate;
        private final EventLoopGroup eventLoopGroup;

        public EventLoopHoldingChannel(ManagedChannel managedChannel, EventLoopGroup eventLoopGroup) {
            super();
            this.delegate = managedChannel;
            this.eventLoopGroup = eventLoopGroup;
        }

        @Override // io.grpc.alts.HandshakerServiceChannel.ForwardingChannel
        protected Channel delegate() {
            return this.delegate;
        }

        public void close() {
            boolean z;
            this.delegate.shutdownNow();
            try {
                z = this.delegate.awaitTermination(2L, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
                z = false;
            }
            this.eventLoopGroup.shutdownGracefully(z ? 0L : 1L, 10L, TimeUnit.SECONDS);
        }
    }
}
