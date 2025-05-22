package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractServerStream;
import io.grpc.internal.ReadableBuffer;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class NettyServerStream extends AbstractServerStream {
    private static final Logger log = Logger.getLogger(NettyServerStream.class.getName());
    private final Attributes attributes;
    private final String authority;
    private final Sink sink;
    private final TransportState state;
    private final int streamId;
    private final TransportTracer transportTracer;
    private final WriteQueue writeQueue;

    public NettyServerStream(Channel channel, TransportState transportState, Attributes attributes, String str, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
        super(new NettyWritableBufferAllocator(channel.alloc()), statsTraceContext);
        this.sink = new Sink();
        this.state = (TransportState) Preconditions.checkNotNull(transportState, "transportState");
        this.writeQueue = transportState.handler.getWriteQueue();
        this.attributes = (Attributes) Preconditions.checkNotNull(attributes);
        this.authority = str;
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
        this.streamId = transportState().mo3906id();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractServerStream, io.grpc.internal.AbstractStream
    public TransportState transportState() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractServerStream
    public Sink abstractServerStreamSink() {
        return this.sink;
    }

    @Override // io.grpc.internal.AbstractServerStream, io.grpc.internal.ServerStream
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.grpc.internal.AbstractServerStream, io.grpc.internal.ServerStream
    public String getAuthority() {
        return this.authority;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class Sink implements AbstractServerStream.Sink {
        private Sink() {
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void writeHeaders(Metadata metadata) {
            PerfMark.startTask("NettyServerStream$Sink.writeHeaders");
            try {
                NettyServerStream.this.writeQueue.enqueue((WriteQueue.QueuedCommand) SendResponseHeadersCommand.createHeaders(NettyServerStream.this.transportState(), Utils.convertServerHeaders(metadata)), true);
            } finally {
                PerfMark.stopTask("NettyServerStream$Sink.writeHeaders");
            }
        }

        private void writeFrameInternal(WritableBuffer writableBuffer, boolean z, final int i) {
            Preconditions.checkArgument(i >= 0);
            if (writableBuffer == null) {
                NettyServerStream.this.writeQueue.scheduleFlush();
                return;
            }
            ByteBuf byteBuf = ((NettyWritableBuffer) writableBuffer).bytebuf().touch();
            final int readableBytes = byteBuf.readableBytes();
            NettyServerStream.this.onSendingBytes(readableBytes);
            NettyServerStream.this.writeQueue.enqueue(new SendGrpcFrameCommand(NettyServerStream.this.transportState(), byteBuf, false), z).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerStream.Sink.1
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    NettyServerStream.this.transportState().onSentBytes(readableBytes);
                    if (channelFuture.isSuccess()) {
                        NettyServerStream.this.transportTracer.reportMessageSent(i);
                    }
                }
            });
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void writeFrame(WritableBuffer writableBuffer, boolean z, int i) {
            PerfMark.startTask("NettyServerStream$Sink.writeFrame");
            try {
                writeFrameInternal(writableBuffer, z, i);
            } finally {
                PerfMark.stopTask("NettyServerStream$Sink.writeFrame");
            }
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void writeTrailers(Metadata metadata, boolean z, Status status) {
            PerfMark.startTask("NettyServerStream$Sink.writeTrailers");
            try {
                NettyServerStream.this.writeQueue.enqueue((WriteQueue.QueuedCommand) SendResponseHeadersCommand.createTrailers(NettyServerStream.this.transportState(), Utils.convertTrailers(metadata, z), status), true);
            } finally {
                PerfMark.stopTask("NettyServerStream$Sink.writeTrailers");
            }
        }

        @Override // io.grpc.internal.AbstractServerStream.Sink
        public void cancel(Status status) {
            PerfMark.startTask("NettyServerStream$Sink.cancel");
            try {
                NettyServerStream.this.writeQueue.enqueue((WriteQueue.QueuedCommand) new CancelServerStreamCommand(NettyServerStream.this.transportState(), status), true);
            } finally {
                PerfMark.startTask("NettyServerStream$Sink.cancel");
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class TransportState extends AbstractServerStream.TransportState implements StreamIdHolder {
        private final EventLoop eventLoop;
        private final NettyServerHandler handler;
        private final Http2Stream http2Stream;
        private final Tag tag;

        @Override // io.grpc.internal.AbstractServerStream.TransportState
        public /* bridge */ /* synthetic */ void complete() {
            super.complete();
        }

        @Override // io.grpc.internal.AbstractServerStream.TransportState, io.grpc.internal.MessageDeframer.Listener
        public /* bridge */ /* synthetic */ void deframerClosed(boolean z) {
            super.deframerClosed(z);
        }

        @Override // io.grpc.internal.AbstractServerStream.TransportState
        public /* bridge */ /* synthetic */ void inboundDataReceived(ReadableBuffer readableBuffer, boolean z) {
            super.inboundDataReceived(readableBuffer, z);
        }

        public TransportState(NettyServerHandler nettyServerHandler, EventLoop eventLoop, Http2Stream http2Stream, int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer, String str) {
            super(i, statsTraceContext, transportTracer);
            this.http2Stream = (Http2Stream) Preconditions.checkNotNull(http2Stream, "http2Stream");
            this.handler = (NettyServerHandler) Preconditions.checkNotNull(nettyServerHandler, "handler");
            this.eventLoop = eventLoop;
            this.tag = PerfMark.createTag(str, http2Stream.mo3916id());
        }

        @Override // io.grpc.internal.ApplicationThreadDeframerListener.TransportExecutor
        public void runOnTransportThread(final Runnable runnable) {
            if (this.eventLoop.inEventLoop()) {
                runnable.run();
            } else {
                final Link linkOut = PerfMark.linkOut();
                this.eventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerStream.TransportState.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PerfMark.startTask("NettyServerStream$TransportState.runOnTransportThread", TransportState.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            runnable.run();
                        } finally {
                            PerfMark.stopTask("NettyServerStream$TransportState.runOnTransportThread", TransportState.this.tag);
                        }
                    }
                });
            }
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void bytesRead(int i) {
            this.handler.returnProcessedBytes(this.http2Stream, i);
            this.handler.getWriteQueue().scheduleFlush();
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void deframeFailed(Throwable th) {
            NettyServerStream.log.log(Level.WARNING, "Exception processing message", th);
            Status fromThrowable = Status.fromThrowable(th);
            transportReportStatus(fromThrowable);
            this.handler.getWriteQueue().enqueue((WriteQueue.QueuedCommand) new CancelServerStreamCommand(this, fromThrowable), true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void inboundDataReceived(ByteBuf byteBuf, boolean z) {
            super.inboundDataReceived(new NettyReadableBuffer(byteBuf.retain()), z);
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.StreamIdHolder
        /* renamed from: id */
        public int mo3906id() {
            return this.http2Stream.mo3916id();
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.StreamIdHolder
        public Tag tag() {
            return this.tag;
        }
    }

    @Override // io.grpc.internal.ServerStream
    public int streamId() {
        return this.streamId;
    }
}
