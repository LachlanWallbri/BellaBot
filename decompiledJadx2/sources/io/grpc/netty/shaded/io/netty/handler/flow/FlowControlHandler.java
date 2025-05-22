package io.grpc.netty.shaded.io.netty.handler.flow;

import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectPool;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class FlowControlHandler extends ChannelDuplexHandler {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) FlowControlHandler.class);
    private ChannelConfig config;
    private RecyclableArrayDeque queue;
    private final boolean releaseMessages;
    private boolean shouldConsume;

    public FlowControlHandler() {
        this(true);
    }

    public FlowControlHandler(boolean z) {
        this.releaseMessages = z;
    }

    boolean isQueueEmpty() {
        RecyclableArrayDeque recyclableArrayDeque = this.queue;
        return recyclableArrayDeque == null || recyclableArrayDeque.isEmpty();
    }

    private void destroy() {
        RecyclableArrayDeque recyclableArrayDeque = this.queue;
        if (recyclableArrayDeque != null) {
            if (!recyclableArrayDeque.isEmpty()) {
                logger.trace("Non-empty queue: {}", this.queue);
                if (this.releaseMessages) {
                    while (true) {
                        Object poll = this.queue.poll();
                        if (poll == null) {
                            break;
                        } else {
                            ReferenceCountUtil.safeRelease(poll);
                        }
                    }
                }
            }
            this.queue.recycle();
            this.queue = null;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.config = channelHandlerContext.channel().config();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.handlerRemoved(channelHandlerContext);
        if (!isQueueEmpty()) {
            dequeue(channelHandlerContext, this.queue.size());
        }
        destroy();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        destroy();
        channelHandlerContext.fireChannelInactive();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (dequeue(channelHandlerContext, 1) == 0) {
            this.shouldConsume = true;
            channelHandlerContext.read();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (this.queue == null) {
            this.queue = RecyclableArrayDeque.newInstance();
        }
        this.queue.offer(obj);
        boolean z = this.shouldConsume;
        this.shouldConsume = false;
        dequeue(channelHandlerContext, z ? 1 : 0);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (isQueueEmpty()) {
            channelHandlerContext.fireChannelReadComplete();
        }
    }

    private int dequeue(ChannelHandlerContext channelHandlerContext, int i) {
        Object poll;
        int i2 = 0;
        while (this.queue != null && ((i2 < i || this.config.isAutoRead()) && (poll = this.queue.poll()) != null)) {
            i2++;
            channelHandlerContext.fireChannelRead(poll);
        }
        RecyclableArrayDeque recyclableArrayDeque = this.queue;
        if (recyclableArrayDeque != null && recyclableArrayDeque.isEmpty()) {
            this.queue.recycle();
            this.queue = null;
            if (i2 > 0) {
                channelHandlerContext.fireChannelReadComplete();
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class RecyclableArrayDeque extends ArrayDeque<Object> {
        private static final int DEFAULT_NUM_ELEMENTS = 2;
        private static final ObjectPool<RecyclableArrayDeque> RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator<RecyclableArrayDeque>() { // from class: io.grpc.netty.shaded.io.netty.handler.flow.FlowControlHandler.RecyclableArrayDeque.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.grpc.netty.shaded.io.netty.util.internal.ObjectPool.ObjectCreator
            public RecyclableArrayDeque newObject(ObjectPool.Handle<RecyclableArrayDeque> handle) {
                return new RecyclableArrayDeque(2, handle);
            }
        });
        private static final long serialVersionUID = 0;
        private final ObjectPool.Handle<RecyclableArrayDeque> handle;

        public static RecyclableArrayDeque newInstance() {
            return RECYCLER.get();
        }

        private RecyclableArrayDeque(int i, ObjectPool.Handle<RecyclableArrayDeque> handle) {
            super(i);
            this.handle = handle;
        }

        public void recycle() {
            clear();
            this.handle.recycle(this);
        }
    }
}
