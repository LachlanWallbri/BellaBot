package io.netty.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class CoalescingBufferQueue extends AbstractCoalescingBufferQueue {
    private final Channel channel;

    public CoalescingBufferQueue(Channel channel) {
        this(channel, 4);
    }

    public CoalescingBufferQueue(Channel channel, int i) {
        this(channel, i, false);
    }

    public CoalescingBufferQueue(Channel channel, int i, boolean z) {
        super(z ? channel : null, i);
        this.channel = (Channel) ObjectUtil.checkNotNull(channel, "channel");
    }

    public ByteBuf remove(int i, ChannelPromise channelPromise) {
        return remove(this.channel.alloc(), i, channelPromise);
    }

    public void releaseAndFailAll(Throwable th) {
        releaseAndFailAll(this.channel, th);
    }

    @Override // io.netty.channel.AbstractCoalescingBufferQueue
    protected ByteBuf compose(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
        if (byteBuf instanceof CompositeByteBuf) {
            CompositeByteBuf compositeByteBuf = (CompositeByteBuf) byteBuf;
            compositeByteBuf.addComponent(true, byteBuf2);
            return compositeByteBuf;
        }
        return composeIntoComposite(byteBufAllocator, byteBuf, byteBuf2);
    }

    @Override // io.netty.channel.AbstractCoalescingBufferQueue
    protected ByteBuf removeEmptyValue() {
        return Unpooled.EMPTY_BUFFER;
    }
}
