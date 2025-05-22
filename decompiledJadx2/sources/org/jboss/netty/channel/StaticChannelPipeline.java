package org.jboss.netty.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.internal.ConversionUtil;

@Deprecated
/* loaded from: classes7.dex */
public class StaticChannelPipeline implements ChannelPipeline {
    static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) StaticChannelPipeline.class);
    private volatile Channel channel;
    private final StaticChannelHandlerContext[] contexts;
    private final int lastIndex;
    private final Map<String, StaticChannelHandlerContext> name2ctx = new HashMap(4);
    private volatile ChannelSink sink;

    public StaticChannelPipeline(ChannelHandler... channelHandlerArr) {
        if (channelHandlerArr == null) {
            throw new NullPointerException("handlers");
        }
        if (channelHandlerArr.length == 0) {
            throw new IllegalArgumentException("no handlers specified");
        }
        StaticChannelHandlerContext[] staticChannelHandlerContextArr = new StaticChannelHandlerContext[channelHandlerArr.length];
        int i = 0;
        while (i < staticChannelHandlerContextArr.length && channelHandlerArr[i] != null) {
            i++;
        }
        if (i == staticChannelHandlerContextArr.length) {
            this.contexts = staticChannelHandlerContextArr;
            this.lastIndex = staticChannelHandlerContextArr.length - 1;
        } else {
            staticChannelHandlerContextArr = new StaticChannelHandlerContext[i];
            this.contexts = staticChannelHandlerContextArr;
            this.lastIndex = i - 1;
        }
        for (int i2 = 0; i2 < i; i2++) {
            ChannelHandler channelHandler = channelHandlerArr[i2];
            String conversionUtil = ConversionUtil.toString(i2);
            StaticChannelHandlerContext staticChannelHandlerContext = new StaticChannelHandlerContext(i2, conversionUtil, channelHandler);
            staticChannelHandlerContextArr[i2] = staticChannelHandlerContext;
            this.name2ctx.put(conversionUtil, staticChannelHandlerContext);
        }
        for (StaticChannelHandlerContext staticChannelHandlerContext2 : staticChannelHandlerContextArr) {
            callBeforeAdd(staticChannelHandlerContext2);
            callAfterAdd(staticChannelHandlerContext2);
        }
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelFuture execute(Runnable runnable) {
        return getSink().execute(this, runnable);
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public Channel getChannel() {
        return this.channel;
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelSink getSink() {
        ChannelSink channelSink = this.sink;
        return channelSink == null ? DefaultChannelPipeline.discardingSink : channelSink;
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public void attach(Channel channel, ChannelSink channelSink) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (channelSink == null) {
            throw new NullPointerException("sink");
        }
        if (this.channel != null || this.sink != null) {
            throw new IllegalStateException("attached already");
        }
        this.channel = channel;
        this.sink = channelSink;
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public boolean isAttached() {
        return this.sink != null;
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public void addFirst(String str, ChannelHandler channelHandler) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public void addLast(String str, ChannelHandler channelHandler) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public void addBefore(String str, String str2, ChannelHandler channelHandler) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public void addAfter(String str, String str2, ChannelHandler channelHandler) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public void remove(ChannelHandler channelHandler) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelHandler remove(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public <T extends ChannelHandler> T remove(Class<T> cls) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelHandler removeFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelHandler removeLast() {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public void replace(ChannelHandler channelHandler, String str, ChannelHandler channelHandler2) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelHandler replace(String str, String str2, ChannelHandler channelHandler) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public <T extends ChannelHandler> T replace(Class<T> cls, String str, ChannelHandler channelHandler) {
        throw new UnsupportedOperationException();
    }

    private static void callBeforeAdd(ChannelHandlerContext channelHandlerContext) {
        if (channelHandlerContext.getHandler() instanceof LifeCycleAwareChannelHandler) {
            LifeCycleAwareChannelHandler lifeCycleAwareChannelHandler = (LifeCycleAwareChannelHandler) channelHandlerContext.getHandler();
            try {
                lifeCycleAwareChannelHandler.beforeAdd(channelHandlerContext);
            } catch (Throwable th) {
                throw new ChannelHandlerLifeCycleException(lifeCycleAwareChannelHandler.getClass().getName() + ".beforeAdd() has thrown an exception; not adding.", th);
            }
        }
    }

    private static void callAfterAdd(ChannelHandlerContext channelHandlerContext) {
        if (channelHandlerContext.getHandler() instanceof LifeCycleAwareChannelHandler) {
            LifeCycleAwareChannelHandler lifeCycleAwareChannelHandler = (LifeCycleAwareChannelHandler) channelHandlerContext.getHandler();
            try {
                lifeCycleAwareChannelHandler.afterAdd(channelHandlerContext);
            } catch (Throwable th) {
                boolean z = false;
                try {
                    callBeforeRemove(channelHandlerContext);
                    callAfterRemove(channelHandlerContext);
                    z = true;
                } catch (Throwable th2) {
                    logger.warn("Failed to remove a handler: " + channelHandlerContext.getName(), th2);
                }
                if (z) {
                    throw new ChannelHandlerLifeCycleException(lifeCycleAwareChannelHandler.getClass().getName() + ".afterAdd() has thrown an exception; removed.", th);
                }
                throw new ChannelHandlerLifeCycleException(lifeCycleAwareChannelHandler.getClass().getName() + ".afterAdd() has thrown an exception; also failed to remove.", th);
            }
        }
    }

    private static void callBeforeRemove(ChannelHandlerContext channelHandlerContext) {
        if (channelHandlerContext.getHandler() instanceof LifeCycleAwareChannelHandler) {
            LifeCycleAwareChannelHandler lifeCycleAwareChannelHandler = (LifeCycleAwareChannelHandler) channelHandlerContext.getHandler();
            try {
                lifeCycleAwareChannelHandler.beforeRemove(channelHandlerContext);
            } catch (Throwable th) {
                throw new ChannelHandlerLifeCycleException(lifeCycleAwareChannelHandler.getClass().getName() + ".beforeRemove() has thrown an exception; not removing.", th);
            }
        }
    }

    private static void callAfterRemove(ChannelHandlerContext channelHandlerContext) {
        if (channelHandlerContext.getHandler() instanceof LifeCycleAwareChannelHandler) {
            LifeCycleAwareChannelHandler lifeCycleAwareChannelHandler = (LifeCycleAwareChannelHandler) channelHandlerContext.getHandler();
            try {
                lifeCycleAwareChannelHandler.afterRemove(channelHandlerContext);
            } catch (Throwable th) {
                throw new ChannelHandlerLifeCycleException(lifeCycleAwareChannelHandler.getClass().getName() + ".afterRemove() has thrown an exception.", th);
            }
        }
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelHandler getFirst() {
        return this.contexts[0].getHandler();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelHandler getLast() {
        return this.contexts[r0.length - 1].getHandler();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelHandler get(String str) {
        StaticChannelHandlerContext staticChannelHandlerContext = this.name2ctx.get(str);
        if (staticChannelHandlerContext == null) {
            return null;
        }
        return staticChannelHandlerContext.getHandler();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public <T extends ChannelHandler> T get(Class<T> cls) {
        ChannelHandlerContext context = getContext((Class<? extends ChannelHandler>) cls);
        if (context == null) {
            return null;
        }
        return (T) context.getHandler();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelHandlerContext getContext(String str) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        return this.name2ctx.get(str);
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelHandlerContext getContext(ChannelHandler channelHandler) {
        if (channelHandler == null) {
            throw new NullPointerException("handler");
        }
        for (StaticChannelHandlerContext staticChannelHandlerContext : this.contexts) {
            if (staticChannelHandlerContext.getHandler() == channelHandler) {
                return staticChannelHandlerContext;
            }
        }
        return null;
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public ChannelHandlerContext getContext(Class<? extends ChannelHandler> cls) {
        if (cls == null) {
            throw new NullPointerException("handlerType");
        }
        for (StaticChannelHandlerContext staticChannelHandlerContext : this.contexts) {
            if (cls.isAssignableFrom(staticChannelHandlerContext.getHandler().getClass())) {
                return staticChannelHandlerContext;
            }
        }
        return null;
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public List<String> getNames() {
        ArrayList arrayList = new ArrayList();
        for (StaticChannelHandlerContext staticChannelHandlerContext : this.contexts) {
            arrayList.add(staticChannelHandlerContext.getName());
        }
        return arrayList;
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public Map<String, ChannelHandler> toMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (StaticChannelHandlerContext staticChannelHandlerContext : this.contexts) {
            linkedHashMap.put(staticChannelHandlerContext.getName(), staticChannelHandlerContext.getHandler());
        }
        return linkedHashMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('{');
        for (StaticChannelHandlerContext staticChannelHandlerContext : this.contexts) {
            sb.append('(');
            sb.append(staticChannelHandlerContext.getName());
            sb.append(" = ");
            sb.append(staticChannelHandlerContext.getHandler().getClass().getName());
            sb.append(')');
            sb.append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "}");
        return sb.toString();
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public void sendUpstream(ChannelEvent channelEvent) {
        StaticChannelHandlerContext actualUpstreamContext = getActualUpstreamContext(0);
        if (actualUpstreamContext == null) {
            logger.warn("The pipeline contains no upstream handlers; discarding: " + channelEvent);
            return;
        }
        sendUpstream(actualUpstreamContext, channelEvent);
    }

    void sendUpstream(StaticChannelHandlerContext staticChannelHandlerContext, ChannelEvent channelEvent) {
        try {
            ((ChannelUpstreamHandler) staticChannelHandlerContext.getHandler()).handleUpstream(staticChannelHandlerContext, channelEvent);
        } catch (Throwable th) {
            notifyHandlerException(channelEvent, th);
        }
    }

    @Override // org.jboss.netty.channel.ChannelPipeline
    public void sendDownstream(ChannelEvent channelEvent) {
        StaticChannelHandlerContext actualDownstreamContext = getActualDownstreamContext(this.lastIndex);
        if (actualDownstreamContext == null) {
            try {
                getSink().eventSunk(this, channelEvent);
                return;
            } catch (Throwable th) {
                notifyHandlerException(channelEvent, th);
                return;
            }
        }
        sendDownstream(actualDownstreamContext, channelEvent);
    }

    void sendDownstream(StaticChannelHandlerContext staticChannelHandlerContext, ChannelEvent channelEvent) {
        if (channelEvent instanceof UpstreamMessageEvent) {
            throw new IllegalArgumentException("cannot send an upstream event to downstream");
        }
        try {
            ((ChannelDownstreamHandler) staticChannelHandlerContext.getHandler()).handleDownstream(staticChannelHandlerContext, channelEvent);
        } catch (Throwable th) {
            channelEvent.getFuture().setFailure(th);
            notifyHandlerException(channelEvent, th);
        }
    }

    StaticChannelHandlerContext getActualUpstreamContext(int i) {
        while (true) {
            StaticChannelHandlerContext[] staticChannelHandlerContextArr = this.contexts;
            if (i >= staticChannelHandlerContextArr.length) {
                return null;
            }
            StaticChannelHandlerContext staticChannelHandlerContext = staticChannelHandlerContextArr[i];
            if (staticChannelHandlerContext.canHandleUpstream()) {
                return staticChannelHandlerContext;
            }
            i++;
        }
    }

    StaticChannelHandlerContext getActualDownstreamContext(int i) {
        while (i >= 0) {
            StaticChannelHandlerContext staticChannelHandlerContext = this.contexts[i];
            if (staticChannelHandlerContext.canHandleDownstream()) {
                return staticChannelHandlerContext;
            }
            i--;
        }
        return null;
    }

    protected void notifyHandlerException(ChannelEvent channelEvent, Throwable th) {
        ChannelPipelineException channelPipelineException;
        if (channelEvent instanceof ExceptionEvent) {
            logger.warn("An exception was thrown by a user handler while handling an exception event (" + channelEvent + ")", th);
            return;
        }
        if (th instanceof ChannelPipelineException) {
            channelPipelineException = (ChannelPipelineException) th;
        } else {
            channelPipelineException = new ChannelPipelineException(th);
        }
        try {
            this.sink.exceptionCaught(this, channelEvent, channelPipelineException);
        } catch (Exception e) {
            logger.warn("An exception was thrown by an exception handler.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class StaticChannelHandlerContext implements ChannelHandlerContext {
        private volatile Object attachment;
        private final boolean canHandleDownstream;
        private final boolean canHandleUpstream;
        private final ChannelHandler handler;
        private final int index;
        private final String name;

        StaticChannelHandlerContext(int i, String str, ChannelHandler channelHandler) {
            if (str == null) {
                throw new NullPointerException("name");
            }
            if (channelHandler == null) {
                throw new NullPointerException("handler");
            }
            this.canHandleUpstream = channelHandler instanceof ChannelUpstreamHandler;
            this.canHandleDownstream = channelHandler instanceof ChannelDownstreamHandler;
            if (!this.canHandleUpstream && !this.canHandleDownstream) {
                throw new IllegalArgumentException("handler must be either " + ChannelUpstreamHandler.class.getName() + " or " + ChannelDownstreamHandler.class.getName() + FilenameUtils.EXTENSION_SEPARATOR);
            }
            this.index = i;
            this.name = str;
            this.handler = channelHandler;
        }

        @Override // org.jboss.netty.channel.ChannelHandlerContext
        public Channel getChannel() {
            return getPipeline().getChannel();
        }

        @Override // org.jboss.netty.channel.ChannelHandlerContext
        public ChannelPipeline getPipeline() {
            return StaticChannelPipeline.this;
        }

        @Override // org.jboss.netty.channel.ChannelHandlerContext
        public boolean canHandleDownstream() {
            return this.canHandleDownstream;
        }

        @Override // org.jboss.netty.channel.ChannelHandlerContext
        public boolean canHandleUpstream() {
            return this.canHandleUpstream;
        }

        @Override // org.jboss.netty.channel.ChannelHandlerContext
        public ChannelHandler getHandler() {
            return this.handler;
        }

        @Override // org.jboss.netty.channel.ChannelHandlerContext
        public String getName() {
            return this.name;
        }

        @Override // org.jboss.netty.channel.ChannelHandlerContext
        public Object getAttachment() {
            return this.attachment;
        }

        @Override // org.jboss.netty.channel.ChannelHandlerContext
        public void setAttachment(Object obj) {
            this.attachment = obj;
        }

        @Override // org.jboss.netty.channel.ChannelHandlerContext
        public void sendDownstream(ChannelEvent channelEvent) {
            StaticChannelHandlerContext actualDownstreamContext = StaticChannelPipeline.this.getActualDownstreamContext(this.index - 1);
            if (actualDownstreamContext == null) {
                try {
                    StaticChannelPipeline.this.getSink().eventSunk(StaticChannelPipeline.this, channelEvent);
                    return;
                } catch (Throwable th) {
                    StaticChannelPipeline.this.notifyHandlerException(channelEvent, th);
                    return;
                }
            }
            StaticChannelPipeline.this.sendDownstream(actualDownstreamContext, channelEvent);
        }

        @Override // org.jboss.netty.channel.ChannelHandlerContext
        public void sendUpstream(ChannelEvent channelEvent) {
            StaticChannelHandlerContext actualUpstreamContext = StaticChannelPipeline.this.getActualUpstreamContext(this.index + 1);
            if (actualUpstreamContext != null) {
                StaticChannelPipeline.this.sendUpstream(actualUpstreamContext, channelEvent);
            }
        }
    }
}
