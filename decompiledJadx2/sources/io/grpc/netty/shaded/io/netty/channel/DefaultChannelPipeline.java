package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakDetector;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup;
import io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.WeakHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultChannelPipeline implements ChannelPipeline {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Channel channel;
    private Map<EventExecutorGroup, EventExecutor> childExecutors;
    private volatile MessageSizeEstimator.Handle estimatorHandle;
    private PendingHandlerCallback pendingHandlerCallbackHead;
    private boolean registered;
    private final ChannelFuture succeededFuture;
    private final VoidChannelPromise voidPromise;
    static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultChannelPipeline.class);
    private static final String HEAD_NAME = generateName0(HeadContext.class);
    private static final String TAIL_NAME = generateName0(TailContext.class);
    private static final FastThreadLocal<Map<Class<?>, String>> nameCaches = new FastThreadLocal<Map<Class<?>, String>>() { // from class: io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
        public Map<Class<?>, String> initialValue() {
            return new WeakHashMap();
        }
    };
    private static final AtomicReferenceFieldUpdater<DefaultChannelPipeline, MessageSizeEstimator.Handle> ESTIMATOR = AtomicReferenceFieldUpdater.newUpdater(DefaultChannelPipeline.class, MessageSizeEstimator.Handle.class, "estimatorHandle");
    private final boolean touch = ResourceLeakDetector.isEnabled();
    private boolean firstRegistration = true;
    final AbstractChannelHandlerContext tail = new TailContext(this);
    final AbstractChannelHandlerContext head = new HeadContext(this);

    protected void onUnhandledChannelWritabilityChanged() {
    }

    protected void onUnhandledInboundChannelActive() {
    }

    protected void onUnhandledInboundChannelInactive() {
    }

    protected void onUnhandledInboundChannelReadComplete() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DefaultChannelPipeline(Channel channel) {
        this.channel = (Channel) ObjectUtil.checkNotNull(channel, "channel");
        this.succeededFuture = new SucceededChannelFuture(channel, null);
        this.voidPromise = new VoidChannelPromise(channel, true);
        AbstractChannelHandlerContext abstractChannelHandlerContext = this.head;
        AbstractChannelHandlerContext abstractChannelHandlerContext2 = this.tail;
        abstractChannelHandlerContext.next = abstractChannelHandlerContext2;
        abstractChannelHandlerContext2.prev = abstractChannelHandlerContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final MessageSizeEstimator.Handle estimatorHandle() {
        MessageSizeEstimator.Handle handle = this.estimatorHandle;
        if (handle != null) {
            return handle;
        }
        MessageSizeEstimator.Handle newHandle = this.channel.config().getMessageSizeEstimator().newHandle();
        return !ESTIMATOR.compareAndSet(this, null, newHandle) ? this.estimatorHandle : newHandle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object touch(Object obj, AbstractChannelHandlerContext abstractChannelHandlerContext) {
        return this.touch ? ReferenceCountUtil.touch(obj, abstractChannelHandlerContext) : obj;
    }

    private AbstractChannelHandlerContext newContext(EventExecutorGroup eventExecutorGroup, String str, ChannelHandler channelHandler) {
        return new DefaultChannelHandlerContext(this, childExecutor(eventExecutorGroup), str, channelHandler);
    }

    private EventExecutor childExecutor(EventExecutorGroup eventExecutorGroup) {
        if (eventExecutorGroup == null) {
            return null;
        }
        Boolean bool = (Boolean) this.channel.config().getOption(ChannelOption.SINGLE_EVENTEXECUTOR_PER_GROUP);
        if (bool != null && !bool.booleanValue()) {
            return eventExecutorGroup.next();
        }
        Map map = this.childExecutors;
        if (map == null) {
            map = new IdentityHashMap(4);
            this.childExecutors = map;
        }
        EventExecutor eventExecutor = (EventExecutor) map.get(eventExecutorGroup);
        if (eventExecutor != null) {
            return eventExecutor;
        }
        EventExecutor next = eventExecutorGroup.next();
        map.put(eventExecutorGroup, next);
        return next;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final Channel channel() {
        return this.channel;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addFirst(String str, ChannelHandler channelHandler) {
        return addFirst(null, str, channelHandler);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addFirst(EventExecutorGroup eventExecutorGroup, String str, ChannelHandler channelHandler) {
        synchronized (this) {
            checkMultiplicity(channelHandler);
            AbstractChannelHandlerContext newContext = newContext(eventExecutorGroup, filterName(str, channelHandler), channelHandler);
            addFirst0(newContext);
            if (!this.registered) {
                newContext.setAddPending();
                callHandlerCallbackLater(newContext, true);
                return this;
            }
            EventExecutor executor = newContext.executor();
            if (!executor.inEventLoop()) {
                callHandlerAddedInEventLoop(newContext, executor);
                return this;
            }
            callHandlerAdded0(newContext);
            return this;
        }
    }

    private void addFirst0(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        AbstractChannelHandlerContext abstractChannelHandlerContext2 = this.head.next;
        AbstractChannelHandlerContext abstractChannelHandlerContext3 = this.head;
        abstractChannelHandlerContext.prev = abstractChannelHandlerContext3;
        abstractChannelHandlerContext.next = abstractChannelHandlerContext2;
        abstractChannelHandlerContext3.next = abstractChannelHandlerContext;
        abstractChannelHandlerContext2.prev = abstractChannelHandlerContext;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addLast(String str, ChannelHandler channelHandler) {
        return addLast(null, str, channelHandler);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addLast(EventExecutorGroup eventExecutorGroup, String str, ChannelHandler channelHandler) {
        synchronized (this) {
            checkMultiplicity(channelHandler);
            AbstractChannelHandlerContext newContext = newContext(eventExecutorGroup, filterName(str, channelHandler), channelHandler);
            addLast0(newContext);
            if (!this.registered) {
                newContext.setAddPending();
                callHandlerCallbackLater(newContext, true);
                return this;
            }
            EventExecutor executor = newContext.executor();
            if (!executor.inEventLoop()) {
                callHandlerAddedInEventLoop(newContext, executor);
                return this;
            }
            callHandlerAdded0(newContext);
            return this;
        }
    }

    private void addLast0(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        AbstractChannelHandlerContext abstractChannelHandlerContext2 = this.tail.prev;
        abstractChannelHandlerContext.prev = abstractChannelHandlerContext2;
        AbstractChannelHandlerContext abstractChannelHandlerContext3 = this.tail;
        abstractChannelHandlerContext.next = abstractChannelHandlerContext3;
        abstractChannelHandlerContext2.next = abstractChannelHandlerContext;
        abstractChannelHandlerContext3.prev = abstractChannelHandlerContext;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addBefore(String str, String str2, ChannelHandler channelHandler) {
        return addBefore(null, str, str2, channelHandler);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addBefore(EventExecutorGroup eventExecutorGroup, String str, String str2, ChannelHandler channelHandler) {
        synchronized (this) {
            checkMultiplicity(channelHandler);
            String filterName = filterName(str2, channelHandler);
            AbstractChannelHandlerContext contextOrDie = getContextOrDie(str);
            AbstractChannelHandlerContext newContext = newContext(eventExecutorGroup, filterName, channelHandler);
            addBefore0(contextOrDie, newContext);
            if (!this.registered) {
                newContext.setAddPending();
                callHandlerCallbackLater(newContext, true);
                return this;
            }
            EventExecutor executor = newContext.executor();
            if (!executor.inEventLoop()) {
                callHandlerAddedInEventLoop(newContext, executor);
                return this;
            }
            callHandlerAdded0(newContext);
            return this;
        }
    }

    private static void addBefore0(AbstractChannelHandlerContext abstractChannelHandlerContext, AbstractChannelHandlerContext abstractChannelHandlerContext2) {
        abstractChannelHandlerContext2.prev = abstractChannelHandlerContext.prev;
        abstractChannelHandlerContext2.next = abstractChannelHandlerContext;
        abstractChannelHandlerContext.prev.next = abstractChannelHandlerContext2;
        abstractChannelHandlerContext.prev = abstractChannelHandlerContext2;
    }

    private String filterName(String str, ChannelHandler channelHandler) {
        if (str == null) {
            return generateName(channelHandler);
        }
        checkDuplicateName(str);
        return str;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addAfter(String str, String str2, ChannelHandler channelHandler) {
        return addAfter(null, str, str2, channelHandler);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addAfter(EventExecutorGroup eventExecutorGroup, String str, String str2, ChannelHandler channelHandler) {
        synchronized (this) {
            checkMultiplicity(channelHandler);
            String filterName = filterName(str2, channelHandler);
            AbstractChannelHandlerContext contextOrDie = getContextOrDie(str);
            AbstractChannelHandlerContext newContext = newContext(eventExecutorGroup, filterName, channelHandler);
            addAfter0(contextOrDie, newContext);
            if (!this.registered) {
                newContext.setAddPending();
                callHandlerCallbackLater(newContext, true);
                return this;
            }
            EventExecutor executor = newContext.executor();
            if (!executor.inEventLoop()) {
                callHandlerAddedInEventLoop(newContext, executor);
                return this;
            }
            callHandlerAdded0(newContext);
            return this;
        }
    }

    private static void addAfter0(AbstractChannelHandlerContext abstractChannelHandlerContext, AbstractChannelHandlerContext abstractChannelHandlerContext2) {
        abstractChannelHandlerContext2.prev = abstractChannelHandlerContext;
        abstractChannelHandlerContext2.next = abstractChannelHandlerContext.next;
        abstractChannelHandlerContext.next.prev = abstractChannelHandlerContext2;
        abstractChannelHandlerContext.next = abstractChannelHandlerContext2;
    }

    public final ChannelPipeline addFirst(ChannelHandler channelHandler) {
        return addFirst((String) null, channelHandler);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addFirst(ChannelHandler... channelHandlerArr) {
        return addFirst((EventExecutorGroup) null, channelHandlerArr);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addFirst(EventExecutorGroup eventExecutorGroup, ChannelHandler... channelHandlerArr) {
        ObjectUtil.checkNotNull(channelHandlerArr, "handlers");
        if (channelHandlerArr.length != 0 && channelHandlerArr[0] != null) {
            int i = 1;
            while (i < channelHandlerArr.length && channelHandlerArr[i] != null) {
                i++;
            }
            for (int i2 = i - 1; i2 >= 0; i2--) {
                addFirst(eventExecutorGroup, null, channelHandlerArr[i2]);
            }
        }
        return this;
    }

    public final ChannelPipeline addLast(ChannelHandler channelHandler) {
        return addLast((String) null, channelHandler);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addLast(ChannelHandler... channelHandlerArr) {
        return addLast((EventExecutorGroup) null, channelHandlerArr);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline addLast(EventExecutorGroup eventExecutorGroup, ChannelHandler... channelHandlerArr) {
        ObjectUtil.checkNotNull(channelHandlerArr, "handlers");
        for (ChannelHandler channelHandler : channelHandlerArr) {
            if (channelHandler == null) {
                break;
            }
            addLast(eventExecutorGroup, null, channelHandler);
        }
        return this;
    }

    private String generateName(ChannelHandler channelHandler) {
        Map<Class<?>, String> map = nameCaches.get();
        Class<?> cls = channelHandler.getClass();
        String str = map.get(cls);
        if (str == null) {
            str = generateName0(cls);
            map.put(cls, str);
        }
        if (context0(str) != null) {
            int i = 1;
            String substring = str.substring(0, str.length() - 1);
            while (true) {
                str = substring + i;
                if (context0(str) == null) {
                    break;
                }
                i++;
            }
        }
        return str;
    }

    private static String generateName0(Class<?> cls) {
        return StringUtil.simpleClassName(cls) + "#0";
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline remove(ChannelHandler channelHandler) {
        remove(getContextOrDie(channelHandler));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandler remove(String str) {
        return remove(getContextOrDie(str)).handler();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final <T extends ChannelHandler> T remove(Class<T> cls) {
        return (T) remove(getContextOrDie((Class<? extends ChannelHandler>) cls)).handler();
    }

    public final <T extends ChannelHandler> T removeIfExists(String str) {
        return (T) removeIfExists(context(str));
    }

    public final <T extends ChannelHandler> T removeIfExists(Class<T> cls) {
        return (T) removeIfExists(context((Class<? extends ChannelHandler>) cls));
    }

    public final <T extends ChannelHandler> T removeIfExists(ChannelHandler channelHandler) {
        return (T) removeIfExists(context(channelHandler));
    }

    private <T extends ChannelHandler> T removeIfExists(ChannelHandlerContext channelHandlerContext) {
        if (channelHandlerContext == null) {
            return null;
        }
        return (T) remove((AbstractChannelHandlerContext) channelHandlerContext).handler();
    }

    private AbstractChannelHandlerContext remove(final AbstractChannelHandlerContext abstractChannelHandlerContext) {
        synchronized (this) {
            atomicRemoveFromHandlerList(abstractChannelHandlerContext);
            if (!this.registered) {
                callHandlerCallbackLater(abstractChannelHandlerContext, false);
                return abstractChannelHandlerContext;
            }
            EventExecutor executor = abstractChannelHandlerContext.executor();
            if (!executor.inEventLoop()) {
                executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultChannelPipeline.this.callHandlerRemoved0(abstractChannelHandlerContext);
                    }
                });
                return abstractChannelHandlerContext;
            }
            callHandlerRemoved0(abstractChannelHandlerContext);
            return abstractChannelHandlerContext;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void atomicRemoveFromHandlerList(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        AbstractChannelHandlerContext abstractChannelHandlerContext2 = abstractChannelHandlerContext.prev;
        AbstractChannelHandlerContext abstractChannelHandlerContext3 = abstractChannelHandlerContext.next;
        abstractChannelHandlerContext2.next = abstractChannelHandlerContext3;
        abstractChannelHandlerContext3.prev = abstractChannelHandlerContext2;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandler removeFirst() {
        if (this.head.next == this.tail) {
            throw new NoSuchElementException();
        }
        return remove(this.head.next).handler();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandler removeLast() {
        AbstractChannelHandlerContext abstractChannelHandlerContext = this.head.next;
        AbstractChannelHandlerContext abstractChannelHandlerContext2 = this.tail;
        if (abstractChannelHandlerContext == abstractChannelHandlerContext2) {
            throw new NoSuchElementException();
        }
        return remove(abstractChannelHandlerContext2.prev).handler();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelPipeline replace(ChannelHandler channelHandler, String str, ChannelHandler channelHandler2) {
        replace(getContextOrDie(channelHandler), str, channelHandler2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandler replace(String str, String str2, ChannelHandler channelHandler) {
        return replace(getContextOrDie(str), str2, channelHandler);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final <T extends ChannelHandler> T replace(Class<T> cls, String str, ChannelHandler channelHandler) {
        return (T) replace(getContextOrDie((Class<? extends ChannelHandler>) cls), str, channelHandler);
    }

    private ChannelHandler replace(final AbstractChannelHandlerContext abstractChannelHandlerContext, String str, ChannelHandler channelHandler) {
        synchronized (this) {
            checkMultiplicity(channelHandler);
            if (str == null) {
                str = generateName(channelHandler);
            } else if (!abstractChannelHandlerContext.name().equals(str)) {
                checkDuplicateName(str);
            }
            final AbstractChannelHandlerContext newContext = newContext(abstractChannelHandlerContext.executor, str, channelHandler);
            replace0(abstractChannelHandlerContext, newContext);
            if (!this.registered) {
                callHandlerCallbackLater(newContext, true);
                callHandlerCallbackLater(abstractChannelHandlerContext, false);
                return abstractChannelHandlerContext.handler();
            }
            EventExecutor executor = abstractChannelHandlerContext.executor();
            if (!executor.inEventLoop()) {
                executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline.3
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultChannelPipeline.this.callHandlerAdded0(newContext);
                        DefaultChannelPipeline.this.callHandlerRemoved0(abstractChannelHandlerContext);
                    }
                });
                return abstractChannelHandlerContext.handler();
            }
            callHandlerAdded0(newContext);
            callHandlerRemoved0(abstractChannelHandlerContext);
            return abstractChannelHandlerContext.handler();
        }
    }

    private static void replace0(AbstractChannelHandlerContext abstractChannelHandlerContext, AbstractChannelHandlerContext abstractChannelHandlerContext2) {
        AbstractChannelHandlerContext abstractChannelHandlerContext3 = abstractChannelHandlerContext.prev;
        AbstractChannelHandlerContext abstractChannelHandlerContext4 = abstractChannelHandlerContext.next;
        abstractChannelHandlerContext2.prev = abstractChannelHandlerContext3;
        abstractChannelHandlerContext2.next = abstractChannelHandlerContext4;
        abstractChannelHandlerContext3.next = abstractChannelHandlerContext2;
        abstractChannelHandlerContext4.prev = abstractChannelHandlerContext2;
        abstractChannelHandlerContext.prev = abstractChannelHandlerContext2;
        abstractChannelHandlerContext.next = abstractChannelHandlerContext2;
    }

    private static void checkMultiplicity(ChannelHandler channelHandler) {
        if (channelHandler instanceof ChannelHandlerAdapter) {
            ChannelHandlerAdapter channelHandlerAdapter = (ChannelHandlerAdapter) channelHandler;
            if (!channelHandlerAdapter.isSharable() && channelHandlerAdapter.added) {
                throw new ChannelPipelineException(channelHandlerAdapter.getClass().getName() + " is not a @Sharable handler, so can't be added or removed multiple times.");
            }
            channelHandlerAdapter.added = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callHandlerAdded0(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        try {
            abstractChannelHandlerContext.callHandlerAdded();
        } catch (Throwable th) {
            boolean z = false;
            try {
                atomicRemoveFromHandlerList(abstractChannelHandlerContext);
                abstractChannelHandlerContext.callHandlerRemoved();
                z = true;
            } catch (Throwable th2) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Failed to remove a handler: " + abstractChannelHandlerContext.name(), th2);
                }
            }
            if (z) {
                fireExceptionCaught((Throwable) new ChannelPipelineException(abstractChannelHandlerContext.handler().getClass().getName() + ".handlerAdded() has thrown an exception; removed.", th));
                return;
            }
            fireExceptionCaught((Throwable) new ChannelPipelineException(abstractChannelHandlerContext.handler().getClass().getName() + ".handlerAdded() has thrown an exception; also failed to remove.", th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callHandlerRemoved0(AbstractChannelHandlerContext abstractChannelHandlerContext) {
        try {
            abstractChannelHandlerContext.callHandlerRemoved();
        } catch (Throwable th) {
            fireExceptionCaught((Throwable) new ChannelPipelineException(abstractChannelHandlerContext.handler().getClass().getName() + ".handlerRemoved() has thrown an exception.", th));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void invokeHandlerAddedIfNeeded() {
        if (this.firstRegistration) {
            this.firstRegistration = false;
            callHandlerAddedForAllHandlers();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandler first() {
        ChannelHandlerContext firstContext = firstContext();
        if (firstContext == null) {
            return null;
        }
        return firstContext.handler();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandlerContext firstContext() {
        if (this.head.next == this.tail) {
            return null;
        }
        return this.head.next;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandler last() {
        AbstractChannelHandlerContext abstractChannelHandlerContext = this.tail.prev;
        if (abstractChannelHandlerContext == this.head) {
            return null;
        }
        return abstractChannelHandlerContext.handler();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandlerContext lastContext() {
        AbstractChannelHandlerContext abstractChannelHandlerContext = this.tail.prev;
        if (abstractChannelHandlerContext == this.head) {
            return null;
        }
        return abstractChannelHandlerContext;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandler get(String str) {
        ChannelHandlerContext context = context(str);
        if (context == null) {
            return null;
        }
        return context.handler();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final <T extends ChannelHandler> T get(Class<T> cls) {
        ChannelHandlerContext context = context((Class<? extends ChannelHandler>) cls);
        if (context == null) {
            return null;
        }
        return (T) context.handler();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandlerContext context(String str) {
        return context0((String) ObjectUtil.checkNotNull(str, "name"));
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandlerContext context(ChannelHandler channelHandler) {
        ObjectUtil.checkNotNull(channelHandler, "handler");
        for (AbstractChannelHandlerContext abstractChannelHandlerContext = this.head.next; abstractChannelHandlerContext != null; abstractChannelHandlerContext = abstractChannelHandlerContext.next) {
            if (abstractChannelHandlerContext.handler() == channelHandler) {
                return abstractChannelHandlerContext;
            }
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final ChannelHandlerContext context(Class<? extends ChannelHandler> cls) {
        ObjectUtil.checkNotNull(cls, "handlerType");
        for (AbstractChannelHandlerContext abstractChannelHandlerContext = this.head.next; abstractChannelHandlerContext != null; abstractChannelHandlerContext = abstractChannelHandlerContext.next) {
            if (cls.isAssignableFrom(abstractChannelHandlerContext.handler().getClass())) {
                return abstractChannelHandlerContext;
            }
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final List<String> names() {
        ArrayList arrayList = new ArrayList();
        for (AbstractChannelHandlerContext abstractChannelHandlerContext = this.head.next; abstractChannelHandlerContext != null; abstractChannelHandlerContext = abstractChannelHandlerContext.next) {
            arrayList.add(abstractChannelHandlerContext.name());
        }
        return arrayList;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPipeline
    public final Map<String, ChannelHandler> toMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (AbstractChannelHandlerContext abstractChannelHandlerContext = this.head.next; abstractChannelHandlerContext != this.tail; abstractChannelHandlerContext = abstractChannelHandlerContext.next) {
            linkedHashMap.put(abstractChannelHandlerContext.name(), abstractChannelHandlerContext.handler());
        }
        return linkedHashMap;
    }

    @Override // java.lang.Iterable
    public final Iterator<Map.Entry<String, ChannelHandler>> iterator() {
        return toMap().entrySet().iterator();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.simpleClassName(this));
        sb.append('{');
        AbstractChannelHandlerContext abstractChannelHandlerContext = this.head.next;
        while (abstractChannelHandlerContext != this.tail) {
            sb.append('(');
            sb.append(abstractChannelHandlerContext.name());
            sb.append(" = ");
            sb.append(abstractChannelHandlerContext.handler().getClass().getName());
            sb.append(')');
            abstractChannelHandlerContext = abstractChannelHandlerContext.next;
            if (abstractChannelHandlerContext == this.tail) {
                break;
            }
            sb.append(", ");
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
    public final ChannelPipeline fireChannelRegistered() {
        AbstractChannelHandlerContext.invokeChannelRegistered(this.head);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
    public final ChannelPipeline fireChannelUnregistered() {
        AbstractChannelHandlerContext.invokeChannelUnregistered(this.head);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void destroy() {
        destroyUp(this.head.next, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyUp(final AbstractChannelHandlerContext abstractChannelHandlerContext, boolean z) {
        Thread currentThread = Thread.currentThread();
        AbstractChannelHandlerContext abstractChannelHandlerContext2 = this.tail;
        while (abstractChannelHandlerContext != abstractChannelHandlerContext2) {
            EventExecutor executor = abstractChannelHandlerContext.executor();
            if (!z && !executor.inEventLoop(currentThread)) {
                executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline.4
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultChannelPipeline.this.destroyUp(abstractChannelHandlerContext, true);
                    }
                });
                return;
            } else {
                abstractChannelHandlerContext = abstractChannelHandlerContext.next;
                z = false;
            }
        }
        destroyDown(currentThread, abstractChannelHandlerContext2.prev, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyDown(Thread thread, final AbstractChannelHandlerContext abstractChannelHandlerContext, boolean z) {
        AbstractChannelHandlerContext abstractChannelHandlerContext2 = this.head;
        while (abstractChannelHandlerContext != abstractChannelHandlerContext2) {
            EventExecutor executor = abstractChannelHandlerContext.executor();
            if (z || executor.inEventLoop(thread)) {
                atomicRemoveFromHandlerList(abstractChannelHandlerContext);
                callHandlerRemoved0(abstractChannelHandlerContext);
                abstractChannelHandlerContext = abstractChannelHandlerContext.prev;
                z = false;
            } else {
                executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline.5
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultChannelPipeline.this.destroyDown(Thread.currentThread(), abstractChannelHandlerContext, true);
                    }
                });
                return;
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
    public final ChannelPipeline fireChannelActive() {
        AbstractChannelHandlerContext.invokeChannelActive(this.head);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
    public final ChannelPipeline fireChannelInactive() {
        AbstractChannelHandlerContext.invokeChannelInactive(this.head);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
    public final ChannelPipeline fireExceptionCaught(Throwable th) {
        AbstractChannelHandlerContext.invokeExceptionCaught(this.head, th);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
    public final ChannelPipeline fireUserEventTriggered(Object obj) {
        AbstractChannelHandlerContext.invokeUserEventTriggered(this.head, obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
    public final ChannelPipeline fireChannelRead(Object obj) {
        AbstractChannelHandlerContext.invokeChannelRead(this.head, obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
    public final ChannelPipeline fireChannelReadComplete() {
        AbstractChannelHandlerContext.invokeChannelReadComplete(this.head);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundInvoker
    public final ChannelPipeline fireChannelWritabilityChanged() {
        AbstractChannelHandlerContext.invokeChannelWritabilityChanged(this.head);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture bind(SocketAddress socketAddress) {
        return this.tail.bind(socketAddress);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture connect(SocketAddress socketAddress) {
        return this.tail.connect(socketAddress);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
        return this.tail.connect(socketAddress, socketAddress2);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture disconnect() {
        return this.tail.disconnect();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture close() {
        return this.tail.close();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture deregister() {
        return this.tail.deregister();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelPipeline flush() {
        this.tail.flush();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return this.tail.bind(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return this.tail.connect(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
        return this.tail.connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture disconnect(ChannelPromise channelPromise) {
        return this.tail.disconnect(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture close(ChannelPromise channelPromise) {
        return this.tail.close(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture deregister(ChannelPromise channelPromise) {
        return this.tail.deregister(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelPipeline read() {
        this.tail.read();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture write(Object obj) {
        return this.tail.write(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture write(Object obj, ChannelPromise channelPromise) {
        return this.tail.write(obj, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture writeAndFlush(Object obj, ChannelPromise channelPromise) {
        return this.tail.writeAndFlush(obj, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture writeAndFlush(Object obj) {
        return this.tail.writeAndFlush(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelPromise newPromise() {
        return new DefaultChannelPromise(this.channel);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelProgressivePromise newProgressivePromise() {
        return new DefaultChannelProgressivePromise(this.channel);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture newSucceededFuture() {
        return this.succeededFuture;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture newFailedFuture(Throwable th) {
        return new FailedChannelFuture(this.channel, null, th);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelPromise voidPromise() {
        return this.voidPromise;
    }

    private void checkDuplicateName(String str) {
        if (context0(str) == null) {
            return;
        }
        throw new IllegalArgumentException("Duplicate handler name: " + str);
    }

    private AbstractChannelHandlerContext context0(String str) {
        for (AbstractChannelHandlerContext abstractChannelHandlerContext = this.head.next; abstractChannelHandlerContext != this.tail; abstractChannelHandlerContext = abstractChannelHandlerContext.next) {
            if (abstractChannelHandlerContext.name().equals(str)) {
                return abstractChannelHandlerContext;
            }
        }
        return null;
    }

    private AbstractChannelHandlerContext getContextOrDie(String str) {
        AbstractChannelHandlerContext abstractChannelHandlerContext = (AbstractChannelHandlerContext) context(str);
        if (abstractChannelHandlerContext != null) {
            return abstractChannelHandlerContext;
        }
        throw new NoSuchElementException(str);
    }

    private AbstractChannelHandlerContext getContextOrDie(ChannelHandler channelHandler) {
        AbstractChannelHandlerContext abstractChannelHandlerContext = (AbstractChannelHandlerContext) context(channelHandler);
        if (abstractChannelHandlerContext != null) {
            return abstractChannelHandlerContext;
        }
        throw new NoSuchElementException(channelHandler.getClass().getName());
    }

    private AbstractChannelHandlerContext getContextOrDie(Class<? extends ChannelHandler> cls) {
        AbstractChannelHandlerContext abstractChannelHandlerContext = (AbstractChannelHandlerContext) context(cls);
        if (abstractChannelHandlerContext != null) {
            return abstractChannelHandlerContext;
        }
        throw new NoSuchElementException(cls.getName());
    }

    private void callHandlerAddedForAllHandlers() {
        PendingHandlerCallback pendingHandlerCallback;
        synchronized (this) {
            this.registered = true;
            this.pendingHandlerCallbackHead = null;
        }
        for (pendingHandlerCallback = this.pendingHandlerCallbackHead; pendingHandlerCallback != null; pendingHandlerCallback = pendingHandlerCallback.next) {
            pendingHandlerCallback.execute();
        }
    }

    private void callHandlerCallbackLater(AbstractChannelHandlerContext abstractChannelHandlerContext, boolean z) {
        PendingHandlerCallback pendingHandlerAddedTask = z ? new PendingHandlerAddedTask(abstractChannelHandlerContext) : new PendingHandlerRemovedTask(abstractChannelHandlerContext);
        PendingHandlerCallback pendingHandlerCallback = this.pendingHandlerCallbackHead;
        if (pendingHandlerCallback == null) {
            this.pendingHandlerCallbackHead = pendingHandlerAddedTask;
            return;
        }
        while (pendingHandlerCallback.next != null) {
            pendingHandlerCallback = pendingHandlerCallback.next;
        }
        pendingHandlerCallback.next = pendingHandlerAddedTask;
    }

    private void callHandlerAddedInEventLoop(final AbstractChannelHandlerContext abstractChannelHandlerContext, EventExecutor eventExecutor) {
        abstractChannelHandlerContext.setAddPending();
        eventExecutor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline.6
            @Override // java.lang.Runnable
            public void run() {
                DefaultChannelPipeline.this.callHandlerAdded0(abstractChannelHandlerContext);
            }
        });
    }

    protected void onUnhandledInboundException(Throwable th) {
        try {
            logger.warn("An exceptionCaught() event was fired, and it reached at the tail of the pipeline. It usually means the last handler in the pipeline did not handle the exception.", th);
        } finally {
            ReferenceCountUtil.release(th);
        }
    }

    protected void onUnhandledInboundMessage(Object obj) {
        try {
            logger.debug("Discarded inbound message {} that reached at the tail of the pipeline. Please check your pipeline configuration.", obj);
        } finally {
            ReferenceCountUtil.release(obj);
        }
    }

    protected void onUnhandledInboundMessage(ChannelHandlerContext channelHandlerContext, Object obj) {
        onUnhandledInboundMessage(obj);
        if (logger.isDebugEnabled()) {
            logger.debug("Discarded message pipeline : {}. Channel : {}.", channelHandlerContext.pipeline().names(), channelHandlerContext.channel());
        }
    }

    protected void onUnhandledInboundUserEventTriggered(Object obj) {
        ReferenceCountUtil.release(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void incrementPendingOutboundBytes(long j) {
        ChannelOutboundBuffer outboundBuffer = this.channel.unsafe().outboundBuffer();
        if (outboundBuffer != null) {
            outboundBuffer.incrementPendingOutboundBytes(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void decrementPendingOutboundBytes(long j) {
        ChannelOutboundBuffer outboundBuffer = this.channel.unsafe().outboundBuffer();
        if (outboundBuffer != null) {
            outboundBuffer.decrementPendingOutboundBytes(j);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    final class TailContext extends AbstractChannelHandlerContext implements ChannelInboundHandler {
        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelRegistered(ChannelHandlerContext channelHandlerContext) {
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelUnregistered(ChannelHandlerContext channelHandlerContext) {
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext
        public ChannelHandler handler() {
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler
        public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler
        public void handlerRemoved(ChannelHandlerContext channelHandlerContext) {
        }

        TailContext(DefaultChannelPipeline defaultChannelPipeline) {
            super(defaultChannelPipeline, null, DefaultChannelPipeline.TAIL_NAME, TailContext.class);
            setAddComplete();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelActive(ChannelHandlerContext channelHandlerContext) {
            DefaultChannelPipeline.this.onUnhandledInboundChannelActive();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelInactive(ChannelHandlerContext channelHandlerContext) {
            DefaultChannelPipeline.this.onUnhandledInboundChannelInactive();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) {
            DefaultChannelPipeline.this.onUnhandledChannelWritabilityChanged();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) {
            DefaultChannelPipeline.this.onUnhandledInboundUserEventTriggered(obj);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
            DefaultChannelPipeline.this.onUnhandledInboundException(th);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
            DefaultChannelPipeline.this.onUnhandledInboundMessage(channelHandlerContext, obj);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelReadComplete(ChannelHandlerContext channelHandlerContext) {
            DefaultChannelPipeline.this.onUnhandledInboundChannelReadComplete();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    final class HeadContext extends AbstractChannelHandlerContext implements ChannelOutboundHandler, ChannelInboundHandler {
        private final Channel.Unsafe unsafe;

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext
        public ChannelHandler handler() {
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler
        public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler
        public void handlerRemoved(ChannelHandlerContext channelHandlerContext) {
        }

        HeadContext(DefaultChannelPipeline defaultChannelPipeline) {
            super(defaultChannelPipeline, null, DefaultChannelPipeline.HEAD_NAME, HeadContext.class);
            this.unsafe = defaultChannelPipeline.channel().unsafe();
            setAddComplete();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) {
            this.unsafe.bind(socketAddress, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            this.unsafe.connect(socketAddress, socketAddress2, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
            this.unsafe.disconnect(channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
            this.unsafe.close(channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
            this.unsafe.deregister(channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void read(ChannelHandlerContext channelHandlerContext) {
            this.unsafe.beginRead();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) {
            this.unsafe.write(obj, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void flush(ChannelHandlerContext channelHandlerContext) {
            this.unsafe.flush();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
            channelHandlerContext.fireExceptionCaught(th);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelRegistered(ChannelHandlerContext channelHandlerContext) {
            DefaultChannelPipeline.this.invokeHandlerAddedIfNeeded();
            channelHandlerContext.fireChannelRegistered();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelUnregistered(ChannelHandlerContext channelHandlerContext) {
            channelHandlerContext.fireChannelUnregistered();
            if (DefaultChannelPipeline.this.channel.isOpen()) {
                return;
            }
            DefaultChannelPipeline.this.destroy();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelActive(ChannelHandlerContext channelHandlerContext) {
            channelHandlerContext.fireChannelActive();
            readIfIsAutoRead();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelInactive(ChannelHandlerContext channelHandlerContext) {
            channelHandlerContext.fireChannelInactive();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
            channelHandlerContext.fireChannelRead(obj);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelReadComplete(ChannelHandlerContext channelHandlerContext) {
            channelHandlerContext.fireChannelReadComplete();
            readIfIsAutoRead();
        }

        private void readIfIsAutoRead() {
            if (DefaultChannelPipeline.this.channel.config().isAutoRead()) {
                DefaultChannelPipeline.this.channel.read();
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) {
            channelHandlerContext.fireUserEventTriggered(obj);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) {
            channelHandlerContext.fireChannelWritabilityChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class PendingHandlerCallback implements Runnable {
        final AbstractChannelHandlerContext ctx;
        PendingHandlerCallback next;

        abstract void execute();

        PendingHandlerCallback(AbstractChannelHandlerContext abstractChannelHandlerContext) {
            this.ctx = abstractChannelHandlerContext;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class PendingHandlerAddedTask extends PendingHandlerCallback {
        PendingHandlerAddedTask(AbstractChannelHandlerContext abstractChannelHandlerContext) {
            super(abstractChannelHandlerContext);
        }

        @Override // java.lang.Runnable
        public void run() {
            DefaultChannelPipeline.this.callHandlerAdded0(this.ctx);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline.PendingHandlerCallback
        void execute() {
            EventExecutor executor = this.ctx.executor();
            if (executor.inEventLoop()) {
                DefaultChannelPipeline.this.callHandlerAdded0(this.ctx);
                return;
            }
            try {
                executor.execute(this);
            } catch (RejectedExecutionException e) {
                if (DefaultChannelPipeline.logger.isWarnEnabled()) {
                    DefaultChannelPipeline.logger.warn("Can't invoke handlerAdded() as the EventExecutor {} rejected it, removing handler {}.", executor, this.ctx.name(), e);
                }
                DefaultChannelPipeline.this.atomicRemoveFromHandlerList(this.ctx);
                this.ctx.setRemoved();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class PendingHandlerRemovedTask extends PendingHandlerCallback {
        PendingHandlerRemovedTask(AbstractChannelHandlerContext abstractChannelHandlerContext) {
            super(abstractChannelHandlerContext);
        }

        @Override // java.lang.Runnable
        public void run() {
            DefaultChannelPipeline.this.callHandlerRemoved0(this.ctx);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline.PendingHandlerCallback
        void execute() {
            EventExecutor executor = this.ctx.executor();
            if (executor.inEventLoop()) {
                DefaultChannelPipeline.this.callHandlerRemoved0(this.ctx);
                return;
            }
            try {
                executor.execute(this);
            } catch (RejectedExecutionException e) {
                if (DefaultChannelPipeline.logger.isWarnEnabled()) {
                    DefaultChannelPipeline.logger.warn("Can't invoke handlerRemoved() as the EventExecutor {} rejected it, removing handler {}.", executor, this.ctx.name(), e);
                }
                this.ctx.setRemoved();
            }
        }
    }
}
