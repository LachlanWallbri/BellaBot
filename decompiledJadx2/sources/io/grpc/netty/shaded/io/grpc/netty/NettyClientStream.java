package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.InternalKnownTransport;
import io.grpc.InternalMethodDescriptor;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.AbstractClientStream;
import io.grpc.internal.Http2ClientStreamTransportState;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import javax.annotation.Nullable;
import org.jboss.netty.handler.codec.spdy.SpdyHeaders;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class NettyClientStream extends AbstractClientStream {
    private static final InternalMethodDescriptor methodDescriptorAccessor;
    private AsciiString authority;
    private final MethodDescriptor<?, ?> method;
    private final AsciiString scheme;
    private final Sink sink;
    private final TransportState state;
    private final AsciiString userAgent;
    private final WriteQueue writeQueue;

    static {
        methodDescriptorAccessor = new InternalMethodDescriptor(NettyClientTransport.class.getName().contains("grpc.netty.shaded") ? InternalKnownTransport.NETTY_SHADED : InternalKnownTransport.NETTY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyClientStream(TransportState transportState, MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, Channel channel, AsciiString asciiString, AsciiString asciiString2, AsciiString asciiString3, StatsTraceContext statsTraceContext, TransportTracer transportTracer, CallOptions callOptions, boolean z) {
        super(new NettyWritableBufferAllocator(channel.alloc()), statsTraceContext, transportTracer, metadata, callOptions, z && methodDescriptor.isSafe());
        this.sink = new Sink();
        this.state = (TransportState) Preconditions.checkNotNull(transportState, "transportState");
        this.writeQueue = transportState.handler.getWriteQueue();
        this.method = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor, "method");
        this.authority = (AsciiString) Preconditions.checkNotNull(asciiString, "authority");
        this.scheme = (AsciiString) Preconditions.checkNotNull(asciiString2, SpdyHeaders.Spdy2HttpNames.SCHEME);
        this.userAgent = asciiString3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractClientStream, io.grpc.internal.AbstractStream
    public TransportState transportState() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractClientStream
    public Sink abstractClientStreamSink() {
        return this.sink;
    }

    @Override // io.grpc.internal.ClientStream
    public void setAuthority(String str) {
        this.authority = AsciiString.m3920of((CharSequence) Preconditions.checkNotNull(str, "authority"));
    }

    @Override // io.grpc.internal.ClientStream
    public Attributes getAttributes() {
        return this.state.handler.getAttributes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class Sink implements AbstractClientStream.Sink {
        private Sink() {
        }

        @Override // io.grpc.internal.AbstractClientStream.Sink
        public void writeHeaders(Metadata metadata, byte[] bArr) {
            PerfMark.startTask("NettyClientStream$Sink.writeHeaders");
            try {
                writeHeadersInternal(metadata, bArr);
            } finally {
                PerfMark.stopTask("NettyClientStream$Sink.writeHeaders");
            }
        }

        private void writeHeadersInternal(Metadata metadata, byte[] bArr) {
            AsciiString asciiString;
            AsciiString asciiString2;
            AsciiString asciiString3 = (AsciiString) NettyClientStream.methodDescriptorAccessor.geRawMethodName(NettyClientStream.this.method);
            if (asciiString3 == null) {
                asciiString3 = new AsciiString("/" + NettyClientStream.this.method.getFullMethodName());
                NettyClientStream.methodDescriptorAccessor.setRawMethodName(NettyClientStream.this.method, asciiString3);
            }
            boolean z = true;
            boolean z2 = bArr != null;
            if (z2) {
                AsciiString asciiString4 = new AsciiString(((Object) asciiString3) + "?" + BaseEncoding.base64().encode(bArr));
                asciiString = Utils.HTTP_GET_METHOD;
                asciiString2 = asciiString4;
            } else {
                asciiString = Utils.HTTP_METHOD;
                asciiString2 = asciiString3;
            }
            Http2Headers convertClientHeaders = Utils.convertClientHeaders(metadata, NettyClientStream.this.scheme, asciiString2, NettyClientStream.this.authority, asciiString, NettyClientStream.this.userAgent);
            ChannelFutureListener channelFutureListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientStream.Sink.1
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        return;
                    }
                    Status shutdownStatus = NettyClientStream.this.transportState().handler.getLifecycleManager().getShutdownStatus();
                    if (shutdownStatus == null) {
                        shutdownStatus = NettyClientStream.this.transportState().statusFromFailedFuture(channelFuture);
                    }
                    NettyClientStream.this.transportState().transportReportStatus(shutdownStatus, true, new Metadata());
                }
            };
            WriteQueue writeQueue = NettyClientStream.this.writeQueue;
            CreateStreamCommand createStreamCommand = new CreateStreamCommand(convertClientHeaders, NettyClientStream.this.transportState(), NettyClientStream.this.shouldBeCountedForInUse(), z2);
            if (NettyClientStream.this.method.getType().clientSendsOneMessage() && !z2) {
                z = false;
            }
            writeQueue.enqueue(createStreamCommand, z).addListener((GenericFutureListener<? extends Future<? super Void>>) channelFutureListener);
        }

        private void writeFrameInternal(WritableBuffer writableBuffer, boolean z, boolean z2, final int i) {
            Preconditions.checkArgument(i >= 0);
            ByteBuf byteBuf = writableBuffer == null ? Unpooled.EMPTY_BUFFER : ((NettyWritableBuffer) writableBuffer).bytebuf().touch();
            final int readableBytes = byteBuf.readableBytes();
            if (readableBytes > 0) {
                NettyClientStream.this.onSendingBytes(readableBytes);
                NettyClientStream.this.writeQueue.enqueue(new SendGrpcFrameCommand(NettyClientStream.this.transportState(), byteBuf, z), z2).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyClientStream.Sink.2
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (!channelFuture.isSuccess() || NettyClientStream.this.transportState().http2Stream() == null) {
                            return;
                        }
                        NettyClientStream.this.transportState().onSentBytes(readableBytes);
                        NettyClientStream.this.getTransportTracer().reportMessageSent(i);
                    }
                });
            } else {
                NettyClientStream.this.writeQueue.enqueue(new SendGrpcFrameCommand(NettyClientStream.this.transportState(), byteBuf, z), z2);
            }
        }

        @Override // io.grpc.internal.AbstractClientStream.Sink
        public void writeFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i) {
            PerfMark.startTask("NettyClientStream$Sink.writeFrame");
            try {
                writeFrameInternal(writableBuffer, z, z2, i);
            } finally {
                PerfMark.stopTask("NettyClientStream$Sink.writeFrame");
            }
        }

        @Override // io.grpc.internal.AbstractClientStream.Sink
        public void cancel(Status status) {
            PerfMark.startTask("NettyClientStream$Sink.cancel");
            try {
                NettyClientStream.this.writeQueue.enqueue((WriteQueue.QueuedCommand) new CancelClientStreamCommand(NettyClientStream.this.transportState(), status), true);
            } finally {
                PerfMark.stopTask("NettyClientStream$Sink.cancel");
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class TransportState extends Http2ClientStreamTransportState implements StreamIdHolder {
        private static final int NON_EXISTENT_ID = -1;
        private final EventLoop eventLoop;
        private final NettyClientHandler handler;
        private Http2Stream http2Stream;

        /* renamed from: id */
        private int f8326id;
        private final String methodName;
        private Tag tag;

        protected abstract Status statusFromFailedFuture(ChannelFuture channelFuture);

        /* JADX INFO: Access modifiers changed from: protected */
        public TransportState(NettyClientHandler nettyClientHandler, EventLoop eventLoop, int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer, String str) {
            super(i, statsTraceContext, transportTracer);
            this.methodName = (String) Preconditions.checkNotNull(str, "methodName");
            this.handler = (NettyClientHandler) Preconditions.checkNotNull(nettyClientHandler, "handler");
            this.eventLoop = (EventLoop) Preconditions.checkNotNull(eventLoop, "eventLoop");
            this.tag = PerfMark.createTag(str);
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.StreamIdHolder
        /* renamed from: id */
        public int mo3906id() {
            return this.f8326id;
        }

        public void setId(int i) {
            Preconditions.checkArgument(i > 0, "id must be positive %s", i);
            Preconditions.checkState(this.f8326id == 0, "id has been previously set: %s", this.f8326id);
            this.f8326id = i;
            this.tag = PerfMark.createTag(this.methodName, i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void setNonExistent() {
            Preconditions.checkState(this.f8326id == 0, "Id has been previously set: %s", this.f8326id);
            this.f8326id = -1;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isNonExistent() {
            return this.f8326id == -1;
        }

        public void setHttp2Stream(Http2Stream http2Stream) {
            Preconditions.checkNotNull(http2Stream, "http2Stream");
            Preconditions.checkState(this.http2Stream == null, "Can only set http2Stream once");
            this.http2Stream = http2Stream;
            onStreamAllocated();
            getTransportTracer().reportLocalStreamStarted();
        }

        @Nullable
        public Http2Stream http2Stream() {
            return this.http2Stream;
        }

        @Override // io.grpc.internal.Http2ClientStreamTransportState
        protected void http2ProcessingFailed(Status status, boolean z, Metadata metadata) {
            transportReportStatus(status, z, metadata);
            this.handler.getWriteQueue().enqueue((WriteQueue.QueuedCommand) new CancelClientStreamCommand(this, status), true);
        }

        @Override // io.grpc.internal.ApplicationThreadDeframerListener.TransportExecutor
        public void runOnTransportThread(Runnable runnable) {
            if (this.eventLoop.inEventLoop()) {
                runnable.run();
            } else {
                this.eventLoop.execute(runnable);
            }
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void bytesRead(int i) {
            this.handler.returnProcessedBytes(this.http2Stream, i);
            this.handler.getWriteQueue().scheduleFlush();
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void deframeFailed(Throwable th) {
            http2ProcessingFailed(Status.fromThrowable(th), true, new Metadata());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void transportHeadersReceived(Http2Headers http2Headers, boolean z) {
            if (z) {
                if (!isOutboundClosed()) {
                    this.handler.getWriteQueue().enqueue((WriteQueue.QueuedCommand) new CancelClientStreamCommand(this, null), true);
                }
                transportTrailersReceived(Utils.convertTrailers(http2Headers));
                return;
            }
            transportHeadersReceived(Utils.convertHeaders(http2Headers));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void transportDataReceived(ByteBuf byteBuf, boolean z) {
            transportDataReceived(new NettyReadableBuffer(byteBuf.retain()), z);
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.StreamIdHolder
        public final Tag tag() {
            return this.tag;
        }
    }
}
