package org.jboss.netty.handler.codec.embedder;

import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.commons.io.FilenameUtils;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineException;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.DefaultChannelPipeline;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public abstract class AbstractCodecEmbedder<E> implements CodecEmbedder<E> {
    private final Channel channel;
    private final ChannelPipeline pipeline;
    final Queue<Object> productQueue;
    private final AbstractCodecEmbedder<E>.EmbeddedChannelSink sink;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractCodecEmbedder(ChannelHandler... channelHandlerArr) {
        this.sink = new EmbeddedChannelSink();
        this.productQueue = new LinkedList();
        this.pipeline = new EmbeddedChannelPipeline();
        configurePipeline(channelHandlerArr);
        this.channel = new EmbeddedChannel(this.pipeline, this.sink);
        fireInitialEvents();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractCodecEmbedder(ChannelBufferFactory channelBufferFactory, ChannelHandler... channelHandlerArr) {
        this(channelHandlerArr);
        getChannel().getConfig().setBufferFactory(channelBufferFactory);
    }

    private void fireInitialEvents() {
        Channels.fireChannelOpen(this.channel);
        Channel channel = this.channel;
        Channels.fireChannelBound(channel, channel.getLocalAddress());
        Channel channel2 = this.channel;
        Channels.fireChannelConnected(channel2, channel2.getRemoteAddress());
    }

    private void configurePipeline(ChannelHandler... channelHandlerArr) {
        if (channelHandlerArr == null) {
            throw new NullPointerException("handlers");
        }
        if (channelHandlerArr.length == 0) {
            throw new IllegalArgumentException("handlers should contain at least one " + ChannelHandler.class.getSimpleName() + FilenameUtils.EXTENSION_SEPARATOR);
        }
        for (int i = 0; i < channelHandlerArr.length; i++) {
            if (channelHandlerArr[i] == null) {
                throw new NullPointerException("handlers[" + i + "]");
            }
            this.pipeline.addLast(String.valueOf(i), channelHandlerArr[i]);
        }
        this.pipeline.addLast("SINK", this.sink);
    }

    @Override // org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public boolean finish() {
        Channels.close(this.channel);
        Channels.fireChannelDisconnected(this.channel);
        Channels.fireChannelUnbound(this.channel);
        Channels.fireChannelClosed(this.channel);
        return !this.productQueue.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Channel getChannel() {
        return this.channel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isEmpty() {
        return this.productQueue.isEmpty();
    }

    @Override // org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public final E poll() {
        return (E) this.productQueue.poll();
    }

    @Override // org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public final E peek() {
        return (E) this.productQueue.peek();
    }

    @Override // org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public final Object[] pollAll() {
        int size = size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            E poll = poll();
            if (poll == null) {
                throw new ConcurrentModificationException();
            }
            objArr[i] = poll;
        }
        return objArr;
    }

    @Override // org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public final <T> T[] pollAll(T[] tArr) {
        if (tArr == null) {
            throw new NullPointerException("a");
        }
        int size = size();
        if (tArr.length < size) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        }
        int i = 0;
        while (true) {
            E poll = poll();
            if (poll == null) {
                break;
            }
            tArr[i] = poll;
            i++;
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    @Override // org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public final int size() {
        return this.productQueue.size();
    }

    @Override // org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public ChannelPipeline getPipeline() {
        return this.pipeline;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class EmbeddedChannelSink implements ChannelSink, ChannelUpstreamHandler {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        EmbeddedChannelSink() {
        }

        @Override // org.jboss.netty.channel.ChannelUpstreamHandler
        public void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) {
            handleEvent(channelEvent);
        }

        @Override // org.jboss.netty.channel.ChannelSink
        public void eventSunk(ChannelPipeline channelPipeline, ChannelEvent channelEvent) {
            handleEvent(channelEvent);
        }

        private void handleEvent(ChannelEvent channelEvent) {
            if (channelEvent instanceof MessageEvent) {
                AbstractCodecEmbedder.this.productQueue.offer(((MessageEvent) channelEvent).getMessage());
            } else if (channelEvent instanceof ExceptionEvent) {
                throw new CodecEmbedderException(((ExceptionEvent) channelEvent).getCause());
            }
        }

        @Override // org.jboss.netty.channel.ChannelSink
        public void exceptionCaught(ChannelPipeline channelPipeline, ChannelEvent channelEvent, ChannelPipelineException channelPipelineException) throws Exception {
            Throwable cause = channelPipelineException.getCause();
            if (cause == null) {
                cause = channelPipelineException;
            }
            throw new CodecEmbedderException(cause);
        }

        @Override // org.jboss.netty.channel.ChannelSink
        public ChannelFuture execute(ChannelPipeline channelPipeline, Runnable runnable) {
            try {
                runnable.run();
                return Channels.succeededFuture(channelPipeline.getChannel());
            } catch (Throwable th) {
                return Channels.failedFuture(channelPipeline.getChannel(), th);
            }
        }
    }

    /* loaded from: classes7.dex */
    private static final class EmbeddedChannelPipeline extends DefaultChannelPipeline {
        EmbeddedChannelPipeline() {
        }

        @Override // org.jboss.netty.channel.DefaultChannelPipeline
        protected void notifyHandlerException(ChannelEvent channelEvent, Throwable th) {
            while ((th instanceof ChannelPipelineException) && th.getCause() != null) {
                th = th.getCause();
            }
            if (th instanceof CodecEmbedderException) {
                throw ((CodecEmbedderException) th);
            }
            throw new CodecEmbedderException(th);
        }
    }
}
