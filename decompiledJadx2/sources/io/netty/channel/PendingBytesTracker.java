package io.netty.channel;

import io.netty.channel.MessageSizeEstimator;
import io.netty.util.internal.ObjectUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class PendingBytesTracker implements MessageSizeEstimator.Handle {
    private final MessageSizeEstimator.Handle estimatorHandle;

    public abstract void decrementPendingOutboundBytes(long j);

    public abstract void incrementPendingOutboundBytes(long j);

    private PendingBytesTracker(MessageSizeEstimator.Handle handle) {
        this.estimatorHandle = (MessageSizeEstimator.Handle) ObjectUtil.checkNotNull(handle, "estimatorHandle");
    }

    @Override // io.netty.channel.MessageSizeEstimator.Handle
    public final int size(Object obj) {
        return this.estimatorHandle.size(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PendingBytesTracker newTracker(Channel channel) {
        if (channel.pipeline() instanceof DefaultChannelPipeline) {
            return new DefaultChannelPipelinePendingBytesTracker((DefaultChannelPipeline) channel.pipeline());
        }
        ChannelOutboundBuffer outboundBuffer = channel.unsafe().outboundBuffer();
        MessageSizeEstimator.Handle newHandle = channel.config().getMessageSizeEstimator().newHandle();
        return outboundBuffer == null ? new NoopPendingBytesTracker(newHandle) : new ChannelOutboundBufferPendingBytesTracker(outboundBuffer, newHandle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class DefaultChannelPipelinePendingBytesTracker extends PendingBytesTracker {
        private final DefaultChannelPipeline pipeline;

        DefaultChannelPipelinePendingBytesTracker(DefaultChannelPipeline defaultChannelPipeline) {
            super(defaultChannelPipeline.estimatorHandle());
            this.pipeline = defaultChannelPipeline;
        }

        @Override // io.netty.channel.PendingBytesTracker
        public void incrementPendingOutboundBytes(long j) {
            this.pipeline.incrementPendingOutboundBytes(j);
        }

        @Override // io.netty.channel.PendingBytesTracker
        public void decrementPendingOutboundBytes(long j) {
            this.pipeline.decrementPendingOutboundBytes(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class ChannelOutboundBufferPendingBytesTracker extends PendingBytesTracker {
        private final ChannelOutboundBuffer buffer;

        ChannelOutboundBufferPendingBytesTracker(ChannelOutboundBuffer channelOutboundBuffer, MessageSizeEstimator.Handle handle) {
            super(handle);
            this.buffer = channelOutboundBuffer;
        }

        @Override // io.netty.channel.PendingBytesTracker
        public void incrementPendingOutboundBytes(long j) {
            this.buffer.incrementPendingOutboundBytes(j);
        }

        @Override // io.netty.channel.PendingBytesTracker
        public void decrementPendingOutboundBytes(long j) {
            this.buffer.decrementPendingOutboundBytes(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class NoopPendingBytesTracker extends PendingBytesTracker {
        @Override // io.netty.channel.PendingBytesTracker
        public void decrementPendingOutboundBytes(long j) {
        }

        @Override // io.netty.channel.PendingBytesTracker
        public void incrementPendingOutboundBytes(long j) {
        }

        NoopPendingBytesTracker(MessageSizeEstimator.Handle handle) {
            super(handle);
        }
    }
}
