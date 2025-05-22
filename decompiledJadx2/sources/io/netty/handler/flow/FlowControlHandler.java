package io.netty.handler.flow;

import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Recycler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class FlowControlHandler extends ChannelDuplexHandler {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) FlowControlHandler.class);
    private ChannelConfig config;
    private RecyclableArrayDeque queue;
    private final boolean releaseMessages;
    private boolean shouldConsume;

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    public FlowControlHandler() {
        this(true);
    }

    public FlowControlHandler(boolean z) {
        this.releaseMessages = z;
    }

    boolean isQueueEmpty() {
        return this.queue.isEmpty();
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

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.config = channelHandlerContext.channel().config();
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        destroy();
        channelHandlerContext.fireChannelInactive();
    }

    @Override // io.netty.channel.ChannelDuplexHandler, io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (dequeue(channelHandlerContext, 1) == 0) {
            this.shouldConsume = true;
            channelHandlerContext.read();
        }
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (this.queue == null) {
            this.queue = RecyclableArrayDeque.newInstance();
        }
        this.queue.offer(obj);
        boolean z = this.shouldConsume;
        this.shouldConsume = false;
        dequeue(channelHandlerContext, z ? 1 : 0);
    }

    private int dequeue(ChannelHandlerContext channelHandlerContext, int i) {
        int i2 = 0;
        if (this.queue == null) {
            return 0;
        }
        while (true) {
            if (i2 >= i && !this.config.isAutoRead()) {
                break;
            }
            Object poll = this.queue.poll();
            if (poll == null) {
                break;
            }
            i2++;
            channelHandlerContext.fireChannelRead(poll);
        }
        if (this.queue.isEmpty() && i2 > 0) {
            channelHandlerContext.fireChannelReadComplete();
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class RecyclableArrayDeque extends ArrayDeque<Object> {
        private static final int DEFAULT_NUM_ELEMENTS = 2;
        private static final Recycler<RecyclableArrayDeque> RECYCLER = new Recycler<RecyclableArrayDeque>() { // from class: io.netty.handler.flow.FlowControlHandler.RecyclableArrayDeque.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.netty.util.Recycler
            public RecyclableArrayDeque newObject(Recycler.Handle<RecyclableArrayDeque> handle) {
                return new RecyclableArrayDeque(2, handle);
            }
        };
        private static final long serialVersionUID = 0;
        private final Recycler.Handle<RecyclableArrayDeque> handle;

        public static RecyclableArrayDeque newInstance() {
            return RECYCLER.get();
        }

        private RecyclableArrayDeque(int i, Recycler.Handle<RecyclableArrayDeque> handle) {
            super(i);
            this.handle = handle;
        }

        public void recycle() {
            clear();
            this.handle.recycle(this);
        }
    }
}
