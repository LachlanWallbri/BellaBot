package io.netty.resolver.dns;

import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogLevel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class TraceDnsQueryLifeCycleObserverFactory implements DnsQueryLifecycleObserverFactory {
    private final InternalLogLevel level;
    private final InternalLogger logger;
    private static final InternalLogger DEFAULT_LOGGER = InternalLoggerFactory.getInstance((Class<?>) TraceDnsQueryLifeCycleObserverFactory.class);
    private static final InternalLogLevel DEFAULT_LEVEL = InternalLogLevel.DEBUG;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TraceDnsQueryLifeCycleObserverFactory() {
        this(DEFAULT_LOGGER, DEFAULT_LEVEL);
    }

    TraceDnsQueryLifeCycleObserverFactory(InternalLogger internalLogger, InternalLogLevel internalLogLevel) {
        this.logger = (InternalLogger) ObjectUtil.checkNotNull(internalLogger, "logger");
        this.level = (InternalLogLevel) ObjectUtil.checkNotNull(internalLogLevel, "level");
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserverFactory
    public DnsQueryLifecycleObserver newDnsQueryLifecycleObserver(DnsQuestion dnsQuestion) {
        return new TraceDnsQueryLifecycleObserver(dnsQuestion, this.logger, this.level);
    }
}
