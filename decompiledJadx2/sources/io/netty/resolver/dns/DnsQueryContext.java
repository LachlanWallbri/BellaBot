package io.netty.resolver.dns;

import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.dns.AbstractDnsOptPseudoRrRecord;
import io.netty.handler.codec.dns.DatagramDnsQuery;
import io.netty.handler.codec.dns.DnsQuery;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.handler.codec.dns.DnsSection;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.ScheduledFuture;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class DnsQueryContext {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DnsQueryContext.class);
    private final DnsRecord[] additionals;

    /* renamed from: id */
    private final int f8572id;
    private final InetSocketAddress nameServerAddr;
    private final DnsRecord optResource;
    private final DnsNameResolver parent;
    private final Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> promise;
    private final DnsQuestion question;
    private final boolean recursionDesired;
    private volatile ScheduledFuture<?> timeoutFuture;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DnsQueryContext(DnsNameResolver dnsNameResolver, InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion, DnsRecord[] dnsRecordArr, Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> promise) {
        this.parent = (DnsNameResolver) ObjectUtil.checkNotNull(dnsNameResolver, "parent");
        this.nameServerAddr = (InetSocketAddress) ObjectUtil.checkNotNull(inetSocketAddress, "nameServerAddr");
        this.question = (DnsQuestion) ObjectUtil.checkNotNull(dnsQuestion, "question");
        this.additionals = (DnsRecord[]) ObjectUtil.checkNotNull(dnsRecordArr, "additionals");
        this.promise = (Promise) ObjectUtil.checkNotNull(promise, "promise");
        this.recursionDesired = dnsNameResolver.isRecursionDesired();
        this.f8572id = dnsNameResolver.queryContextManager.add(this);
        if (dnsNameResolver.isOptResourceEnabled()) {
            int i = 0;
            this.optResource = new AbstractDnsOptPseudoRrRecord(dnsNameResolver.maxPayloadSize(), i, i) { // from class: io.netty.resolver.dns.DnsQueryContext.1
            };
        } else {
            this.optResource = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InetSocketAddress nameServerAddr() {
        return this.nameServerAddr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DnsQuestion question() {
        return this.question;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void query(ChannelPromise channelPromise) {
        DnsQuestion question = question();
        InetSocketAddress nameServerAddr = nameServerAddr();
        DatagramDnsQuery datagramDnsQuery = new DatagramDnsQuery(null, nameServerAddr, this.f8572id);
        datagramDnsQuery.setRecursionDesired(this.recursionDesired);
        datagramDnsQuery.addRecord(DnsSection.QUESTION, (DnsRecord) question);
        for (DnsRecord dnsRecord : this.additionals) {
            datagramDnsQuery.addRecord(DnsSection.ADDITIONAL, dnsRecord);
        }
        if (this.optResource != null) {
            datagramDnsQuery.addRecord(DnsSection.ADDITIONAL, this.optResource);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("{} WRITE: [{}: {}], {}", this.parent.f8571ch, Integer.valueOf(this.f8572id), nameServerAddr, question);
        }
        sendQuery(datagramDnsQuery, channelPromise);
    }

    private void sendQuery(final DnsQuery dnsQuery, final ChannelPromise channelPromise) {
        if (this.parent.channelFuture.isDone()) {
            writeQuery(dnsQuery, channelPromise);
        } else {
            this.parent.channelFuture.addListener(new GenericFutureListener<Future<? super Channel>>() { // from class: io.netty.resolver.dns.DnsQueryContext.2
                @Override // io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<? super Channel> future) throws Exception {
                    if (future.isSuccess()) {
                        DnsQueryContext.this.writeQuery(dnsQuery, channelPromise);
                        return;
                    }
                    Throwable cause = future.cause();
                    DnsQueryContext.this.promise.tryFailure(cause);
                    channelPromise.setFailure(cause);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeQuery(DnsQuery dnsQuery, ChannelPromise channelPromise) {
        final ChannelFuture writeAndFlush = this.parent.f8571ch.writeAndFlush(dnsQuery, channelPromise);
        if (writeAndFlush.isDone()) {
            onQueryWriteCompletion(writeAndFlush);
        } else {
            writeAndFlush.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.resolver.dns.DnsQueryContext.3
                @Override // io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    DnsQueryContext.this.onQueryWriteCompletion(writeAndFlush);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onQueryWriteCompletion(ChannelFuture channelFuture) {
        if (!channelFuture.isSuccess()) {
            setFailure("failed to send a query", channelFuture.cause());
            return;
        }
        final long queryTimeoutMillis = this.parent.queryTimeoutMillis();
        if (queryTimeoutMillis > 0) {
            this.timeoutFuture = this.parent.f8571ch.eventLoop().schedule(new Runnable() { // from class: io.netty.resolver.dns.DnsQueryContext.4
                @Override // java.lang.Runnable
                public void run() {
                    if (DnsQueryContext.this.promise.isDone()) {
                        return;
                    }
                    DnsQueryContext.this.setFailure("query timed out after " + queryTimeoutMillis + " milliseconds", null);
                }
            }, queryTimeoutMillis, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void finish(AddressedEnvelope<? extends DnsResponse, InetSocketAddress> addressedEnvelope) {
        DnsResponse content = addressedEnvelope.content();
        if (content.count(DnsSection.QUESTION) != 1) {
            logger.warn("Received a DNS response with invalid number of questions: {}", addressedEnvelope);
        } else if (!question().equals(content.recordAt(DnsSection.QUESTION))) {
            logger.warn("Received a mismatching DNS response: {}", addressedEnvelope);
        } else {
            setSuccess(addressedEnvelope);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setSuccess(AddressedEnvelope<? extends DnsResponse, InetSocketAddress> addressedEnvelope) {
        this.parent.queryContextManager.remove(nameServerAddr(), this.f8572id);
        ScheduledFuture<?> scheduledFuture = this.timeoutFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> promise = this.promise;
        if (!promise.setUncancellable() || promise.trySuccess(addressedEnvelope.retain())) {
            return;
        }
        addressedEnvelope.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFailure(String str, Throwable th) {
        Throwable dnsNameResolverException;
        InetSocketAddress nameServerAddr = nameServerAddr();
        this.parent.queryContextManager.remove(nameServerAddr, this.f8572id);
        StringBuilder sb = new StringBuilder(str.length() + 64);
        sb.append('[');
        sb.append(nameServerAddr);
        sb.append("] ");
        sb.append(str);
        sb.append(" (no stack trace available)");
        if (th == null) {
            dnsNameResolverException = new DnsNameResolverTimeoutException(nameServerAddr, question(), sb.toString());
        } else {
            dnsNameResolverException = new DnsNameResolverException(nameServerAddr, question(), sb.toString(), th);
        }
        this.promise.tryFailure(dnsNameResolverException);
    }
}
