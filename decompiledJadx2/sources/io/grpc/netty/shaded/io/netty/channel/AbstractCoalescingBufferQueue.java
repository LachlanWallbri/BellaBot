package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractCoalescingBufferQueue {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractCoalescingBufferQueue.class);
    private final ArrayDeque<Object> bufAndListenerPairs;
    private int readableBytes;
    private final PendingBytesTracker tracker;

    protected abstract ByteBuf compose(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2);

    protected ByteBuf composeFirst(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf) {
        return byteBuf;
    }

    protected abstract ByteBuf removeEmptyValue();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractCoalescingBufferQueue(Channel channel, int i) {
        this.bufAndListenerPairs = new ArrayDeque<>(i);
        this.tracker = channel == null ? null : PendingBytesTracker.newTracker(channel);
    }

    public final void addFirst(ByteBuf byteBuf, ChannelPromise channelPromise) {
        addFirst(byteBuf, toChannelFutureListener(channelPromise));
    }

    private void addFirst(ByteBuf byteBuf, ChannelFutureListener channelFutureListener) {
        if (channelFutureListener != null) {
            this.bufAndListenerPairs.addFirst(channelFutureListener);
        }
        this.bufAndListenerPairs.addFirst(byteBuf);
        incrementReadableBytes(byteBuf.readableBytes());
    }

    public final void add(ByteBuf byteBuf) {
        add(byteBuf, (ChannelFutureListener) null);
    }

    public final void add(ByteBuf byteBuf, ChannelPromise channelPromise) {
        add(byteBuf, toChannelFutureListener(channelPromise));
    }

    public final void add(ByteBuf byteBuf, ChannelFutureListener channelFutureListener) {
        this.bufAndListenerPairs.add(byteBuf);
        if (channelFutureListener != null) {
            this.bufAndListenerPairs.add(channelFutureListener);
        }
        incrementReadableBytes(byteBuf.readableBytes());
    }

    public final ByteBuf removeFirst(ChannelPromise channelPromise) {
        Object poll = this.bufAndListenerPairs.poll();
        if (poll == null) {
            return null;
        }
        ByteBuf byteBuf = (ByteBuf) poll;
        decrementReadableBytes(byteBuf.readableBytes());
        Object peek = this.bufAndListenerPairs.peek();
        if (peek instanceof ChannelFutureListener) {
            channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) peek);
            this.bufAndListenerPairs.poll();
        }
        return byteBuf;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x003b, code lost:
    
        r5.bufAndListenerPairs.addFirst(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0040, code lost:
    
        if (r1 <= 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0042, code lost:
    
        r0 = r3.readRetainedSlice(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0046, code lost:
    
        if (r2 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0048, code lost:
    
        r6 = composeFirst(r6, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0051, code lost:
    
        r2 = r6;
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x004d, code lost:
    
        r6 = compose(r6, r2, r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final ByteBuf remove(ByteBufAllocator byteBufAllocator, int i, ChannelPromise channelPromise) {
        ObjectUtil.checkPositiveOrZero(i, "bytes");
        ObjectUtil.checkNotNull(channelPromise, "aggregatePromise");
        if (this.bufAndListenerPairs.isEmpty()) {
            return removeEmptyValue();
        }
        int min = Math.min(i, this.readableBytes);
        ByteBuf byteBuf = null;
        int i2 = min;
        ByteBuf byteBuf2 = null;
        while (true) {
            try {
                Object poll = this.bufAndListenerPairs.poll();
                if (poll == null) {
                    break;
                }
                if (poll instanceof ChannelFutureListener) {
                    channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) poll);
                } else {
                    ByteBuf byteBuf3 = (ByteBuf) poll;
                    try {
                        if (byteBuf3.readableBytes() > i2) {
                            break;
                        }
                        i2 -= byteBuf3.readableBytes();
                        if (byteBuf2 == null) {
                            byteBuf2 = composeFirst(byteBufAllocator, byteBuf3);
                        } else {
                            byteBuf2 = compose(byteBufAllocator, byteBuf2, byteBuf3);
                        }
                    } catch (Throwable th) {
                        th = th;
                        byteBuf = byteBuf3;
                        ReferenceCountUtil.safeRelease(byteBuf);
                        ReferenceCountUtil.safeRelease(byteBuf2);
                        channelPromise.setFailure(th);
                        PlatformDependent.throwException(th);
                        decrementReadableBytes(min - i2);
                        return byteBuf2;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        decrementReadableBytes(min - i2);
        return byteBuf2;
    }

    public final int readableBytes() {
        return this.readableBytes;
    }

    public final boolean isEmpty() {
        return this.bufAndListenerPairs.isEmpty();
    }

    public final void releaseAndFailAll(ChannelOutboundInvoker channelOutboundInvoker, Throwable th) {
        releaseAndCompleteAll(channelOutboundInvoker.newFailedFuture(th));
    }

    public final void copyTo(AbstractCoalescingBufferQueue abstractCoalescingBufferQueue) {
        abstractCoalescingBufferQueue.bufAndListenerPairs.addAll(this.bufAndListenerPairs);
        abstractCoalescingBufferQueue.incrementReadableBytes(this.readableBytes);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0065 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0063 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void writeAndRemoveAll(ChannelHandlerContext channelHandlerContext) {
        ByteBuf byteBuf;
        ByteBuf byteBuf2 = null;
        Throwable th = null;
        while (true) {
            Object poll = this.bufAndListenerPairs.poll();
            if (poll == null) {
                if (byteBuf2 == null) {
                    break;
                }
                decrementReadableBytes(byteBuf2.readableBytes());
                channelHandlerContext.write(byteBuf2, channelHandlerContext.voidPromise());
                break;
            }
            try {
                if (poll instanceof ByteBuf) {
                    if (byteBuf2 != null) {
                        decrementReadableBytes(byteBuf2.readableBytes());
                        channelHandlerContext.write(byteBuf2, channelHandlerContext.voidPromise());
                    }
                    byteBuf = (ByteBuf) poll;
                } else {
                    if (poll instanceof ChannelPromise) {
                        decrementReadableBytes(byteBuf2.readableBytes());
                        channelHandlerContext.write(byteBuf2, (ChannelPromise) poll);
                    } else {
                        decrementReadableBytes(byteBuf2.readableBytes());
                        channelHandlerContext.write(byteBuf2).addListener((GenericFutureListener<? extends Future<? super Void>>) poll);
                    }
                    byteBuf = null;
                }
                byteBuf2 = byteBuf;
            } catch (Throwable th2) {
                if (th != null) {
                }
            }
            if (th != null) {
                th = th2;
            } else {
                logger.info("Throwable being suppressed because Throwable {} is already pending", th, th2);
            }
        }
        if (th != null) {
            throw new IllegalStateException(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ByteBuf composeIntoComposite(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
        CompositeByteBuf compositeBuffer = byteBufAllocator.compositeBuffer(size() + 2);
        try {
            compositeBuffer.addComponent(true, byteBuf);
            compositeBuffer.addComponent(true, byteBuf2);
        } catch (Throwable th) {
            compositeBuffer.release();
            ReferenceCountUtil.safeRelease(byteBuf2);
            PlatformDependent.throwException(th);
        }
        return compositeBuffer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ByteBuf copyAndCompose(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
        ByteBuf ioBuffer = byteBufAllocator.ioBuffer(byteBuf.readableBytes() + byteBuf2.readableBytes());
        try {
            ioBuffer.writeBytes(byteBuf).writeBytes(byteBuf2);
        } catch (Throwable th) {
            ioBuffer.release();
            ReferenceCountUtil.safeRelease(byteBuf2);
            PlatformDependent.throwException(th);
        }
        byteBuf.release();
        byteBuf2.release();
        return ioBuffer;
    }

    protected final int size() {
        return this.bufAndListenerPairs.size();
    }

    private void releaseAndCompleteAll(ChannelFuture channelFuture) {
        Throwable th = null;
        while (true) {
            Object poll = this.bufAndListenerPairs.poll();
            if (poll == null) {
                break;
            }
            try {
                if (poll instanceof ByteBuf) {
                    ByteBuf byteBuf = (ByteBuf) poll;
                    decrementReadableBytes(byteBuf.readableBytes());
                    ReferenceCountUtil.safeRelease(byteBuf);
                } else {
                    ((ChannelFutureListener) poll).operationComplete(channelFuture);
                }
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    logger.info("Throwable being suppressed because Throwable {} is already pending", th, th2);
                }
            }
        }
        if (th != null) {
            throw new IllegalStateException(th);
        }
    }

    private void incrementReadableBytes(int i) {
        int i2 = this.readableBytes;
        int i3 = i2 + i;
        if (i3 < i2) {
            throw new IllegalStateException("buffer queue length overflow: " + this.readableBytes + " + " + i);
        }
        this.readableBytes = i3;
        PendingBytesTracker pendingBytesTracker = this.tracker;
        if (pendingBytesTracker != null) {
            pendingBytesTracker.incrementPendingOutboundBytes(i);
        }
    }

    private void decrementReadableBytes(int i) {
        this.readableBytes -= i;
        PendingBytesTracker pendingBytesTracker = this.tracker;
        if (pendingBytesTracker != null) {
            pendingBytesTracker.decrementPendingOutboundBytes(i);
        }
    }

    private static ChannelFutureListener toChannelFutureListener(ChannelPromise channelPromise) {
        if (channelPromise.isVoid()) {
            return null;
        }
        return new DelegatingChannelPromiseNotifier(channelPromise);
    }
}
