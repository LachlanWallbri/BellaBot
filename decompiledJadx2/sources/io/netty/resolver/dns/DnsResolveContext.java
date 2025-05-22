package io.netty.resolver.dns;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.dns.DefaultDnsQuestion;
import io.netty.handler.codec.dns.DefaultDnsRecordDecoder;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRawRecord;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.handler.codec.dns.DnsResponseCode;
import io.netty.handler.codec.dns.DnsSection;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.ThrowableUtil;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class DnsResolveContext<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final DnsRecord[] additionals;
    private int allowedQueries;
    private final int dnsClass;
    private final DnsRecordType[] expectedTypes;
    private List<T> finalResult;
    private final String hostname;
    private final int maxAllowedQueries;
    private final DnsServerAddressStream nameServerAddrs;
    final DnsNameResolver parent;
    private final Set<Future<AddressedEnvelope<DnsResponse, InetSocketAddress>>> queriesInProgress = Collections.newSetFromMap(new IdentityHashMap());
    private boolean triedCNAME;
    private static final FutureListener<AddressedEnvelope<DnsResponse, InetSocketAddress>> RELEASE_RESPONSE = new FutureListener<AddressedEnvelope<DnsResponse, InetSocketAddress>>() { // from class: io.netty.resolver.dns.DnsResolveContext.1
        @Override // io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> future) {
            if (future.isSuccess()) {
                future.getNow().release();
            }
        }
    };
    private static final RuntimeException NXDOMAIN_QUERY_FAILED_EXCEPTION = (RuntimeException) ThrowableUtil.unknownStackTrace(new RuntimeException("No answer found and NXDOMAIN response code returned"), DnsResolveContext.class, "onResponse(..)");
    private static final RuntimeException CNAME_NOT_FOUND_QUERY_FAILED_EXCEPTION = (RuntimeException) ThrowableUtil.unknownStackTrace(new RuntimeException("No matching CNAME record found"), DnsResolveContext.class, "onResponseCNAME(..)");
    private static final RuntimeException NO_MATCHING_RECORD_QUERY_FAILED_EXCEPTION = (RuntimeException) ThrowableUtil.unknownStackTrace(new RuntimeException("No matching record type found"), DnsResolveContext.class, "onResponseAorAAAA(..)");
    private static final RuntimeException UNRECOGNIZED_TYPE_QUERY_FAILED_EXCEPTION = (RuntimeException) ThrowableUtil.unknownStackTrace(new RuntimeException("Response type was unrecognized"), DnsResolveContext.class, "onResponse(..)");
    private static final RuntimeException NAME_SERVERS_EXHAUSTED_EXCEPTION = (RuntimeException) ThrowableUtil.unknownStackTrace(new RuntimeException("No name servers returned an answer"), DnsResolveContext.class, "tryToFinishResolve(..)");

    abstract void cache(String str, DnsRecord[] dnsRecordArr, DnsRecord dnsRecord, T t);

    abstract void cache(String str, DnsRecord[] dnsRecordArr, UnknownHostException unknownHostException);

    abstract boolean containsExpectedResult(List<T> list);

    abstract T convertRecord(DnsRecord dnsRecord, String str, DnsRecord[] dnsRecordArr, EventLoop eventLoop);

    abstract DnsResolveContext<T> newResolverContext(DnsNameResolver dnsNameResolver, String str, int i, DnsRecordType[] dnsRecordTypeArr, DnsRecord[] dnsRecordArr, DnsServerAddressStream dnsServerAddressStream);

    /* JADX INFO: Access modifiers changed from: package-private */
    public DnsResolveContext(DnsNameResolver dnsNameResolver, String str, int i, DnsRecordType[] dnsRecordTypeArr, DnsRecord[] dnsRecordArr, DnsServerAddressStream dnsServerAddressStream) {
        this.parent = dnsNameResolver;
        this.hostname = str;
        this.dnsClass = i;
        this.expectedTypes = dnsRecordTypeArr;
        this.additionals = dnsRecordArr;
        this.nameServerAddrs = (DnsServerAddressStream) ObjectUtil.checkNotNull(dnsServerAddressStream, "nameServerAddrs");
        int maxQueriesPerResolve = dnsNameResolver.maxQueriesPerResolve();
        this.maxAllowedQueries = maxQueriesPerResolve;
        this.allowedQueries = maxQueriesPerResolve;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    static final class DnsResolveContextException extends RuntimeException {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        private DnsResolveContextException(String str) {
            super(str);
        }

        private DnsResolveContextException(String str, boolean z) {
            super(str, null, false, true);
        }

        static DnsResolveContextException newStatic(String str) {
            if (PlatformDependent.javaVersion() >= 7) {
                return new DnsResolveContextException(str, true);
            }
            return new DnsResolveContextException(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resolve(final Promise<List<T>> promise) {
        String str;
        final String[] searchDomains = this.parent.searchDomains();
        if (searchDomains.length == 0 || this.parent.ndots() == 0 || StringUtil.endsWith(this.hostname, FilenameUtils.EXTENSION_SEPARATOR)) {
            internalResolve(promise);
            return;
        }
        final boolean hasNDots = hasNDots();
        if (hasNDots) {
            str = this.hostname;
        } else {
            str = this.hostname + FilenameUtils.EXTENSION_SEPARATOR + searchDomains[0];
        }
        final int i = !hasNDots ? 1 : 0;
        doSearchDomainQuery(str, new FutureListener<List<T>>() { // from class: io.netty.resolver.dns.DnsResolveContext.2
            private int searchDomainIdx;

            {
                this.searchDomainIdx = i;
            }

            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<List<T>> future) throws Exception {
                Throwable cause = future.cause();
                if (cause == null) {
                    promise.trySuccess(future.getNow());
                    return;
                }
                if (DnsNameResolver.isTransportOrTimeoutError(cause)) {
                    promise.tryFailure(new SearchDomainUnknownHostException(cause, DnsResolveContext.this.hostname));
                    return;
                }
                if (this.searchDomainIdx < searchDomains.length) {
                    DnsResolveContext dnsResolveContext = DnsResolveContext.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append(DnsResolveContext.this.hostname);
                    sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                    String[] strArr = searchDomains;
                    int i2 = this.searchDomainIdx;
                    this.searchDomainIdx = i2 + 1;
                    sb.append(strArr[i2]);
                    dnsResolveContext.doSearchDomainQuery(sb.toString(), this);
                    return;
                }
                if (!hasNDots) {
                    DnsResolveContext.this.internalResolve(promise);
                } else {
                    promise.tryFailure(new SearchDomainUnknownHostException(cause, DnsResolveContext.this.hostname));
                }
            }
        });
    }

    private boolean hasNDots() {
        int i = 0;
        for (int length = this.hostname.length() - 1; length >= 0; length--) {
            if (this.hostname.charAt(length) == '.' && (i = i + 1) >= this.parent.ndots()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private static final class SearchDomainUnknownHostException extends UnknownHostException {
        private static final long serialVersionUID = -8573510133644997085L;

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }

        SearchDomainUnknownHostException(Throwable th, String str) {
            super("Search domain query failed. Original hostname: '" + str + "' " + th.getMessage());
            setStackTrace(th.getStackTrace());
            initCause(th.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSearchDomainQuery(String str, FutureListener<List<T>> futureListener) {
        DnsResolveContext<T> newResolverContext = newResolverContext(this.parent, str, this.dnsClass, this.expectedTypes, this.additionals, this.nameServerAddrs);
        Promise<List<T>> newPromise = this.parent.executor().newPromise();
        newResolverContext.internalResolve(newPromise);
        newPromise.addListener((GenericFutureListener<? extends Future<? super List<T>>>) futureListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalResolve(Promise<List<T>> promise) {
        DnsServerAddressStream nameServers = getNameServers(this.hostname);
        int length = this.expectedTypes.length - 1;
        for (int i = 0; i < length; i++) {
            if (!query(this.hostname, this.expectedTypes[i], nameServers.duplicate(), promise, (Throwable) null)) {
                return;
            }
        }
        query(this.hostname, this.expectedTypes[length], nameServers, promise, (Throwable) null);
    }

    private void addNameServerToCache(AuthoritativeNameServer authoritativeNameServer, InetAddress inetAddress, long j) {
        if (authoritativeNameServer.isRootServer()) {
            return;
        }
        this.parent.authoritativeDnsServerCache().cache(authoritativeNameServer.domainName(), this.additionals, inetAddress, j, this.parent.f8571ch.eventLoop());
    }

    private DnsServerAddressStream getNameServersFromCache(String str) {
        int length = str.length();
        if (length == 0) {
            return null;
        }
        if (str.charAt(length - 1) != '.') {
            str = str + ".";
        }
        int indexOf = str.indexOf(46);
        if (indexOf == str.length() - 1) {
            return null;
        }
        while (true) {
            str = str.substring(indexOf + 1);
            indexOf = str.indexOf(46);
            if (indexOf <= 0 || indexOf == str.length() - 1) {
                break;
            }
            List<? extends DnsCacheEntry> list = this.parent.authoritativeDnsServerCache().get(str, this.additionals);
            if (list != null && !list.isEmpty()) {
                return DnsServerAddresses.sequential(new DnsCacheIterable(list)).stream();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class DnsCacheIterable implements Iterable<InetSocketAddress> {
        private final List<? extends DnsCacheEntry> entries;

        DnsCacheIterable(List<? extends DnsCacheEntry> list) {
            this.entries = list;
        }

        @Override // java.lang.Iterable
        public Iterator<InetSocketAddress> iterator() {
            return new Iterator<InetSocketAddress>() { // from class: io.netty.resolver.dns.DnsResolveContext.DnsCacheIterable.1
                Iterator<? extends DnsCacheEntry> entryIterator;

                {
                    this.entryIterator = DnsCacheIterable.this.entries.iterator();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.entryIterator.hasNext();
                }

                @Override // java.util.Iterator
                public InetSocketAddress next() {
                    InetAddress address = this.entryIterator.next().address();
                    return new InetSocketAddress(address, DnsResolveContext.this.parent.dnsRedirectPort(address));
                }

                @Override // java.util.Iterator
                public void remove() {
                    this.entryIterator.remove();
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void query(DnsServerAddressStream dnsServerAddressStream, int i, DnsQuestion dnsQuestion, Promise<List<T>> promise, Throwable th) {
        query(dnsServerAddressStream, i, dnsQuestion, this.parent.dnsQueryLifecycleObserverFactory().newDnsQueryLifecycleObserver(dnsQuestion), promise, th);
    }

    private void query(final DnsServerAddressStream dnsServerAddressStream, final int i, final DnsQuestion dnsQuestion, final DnsQueryLifecycleObserver dnsQueryLifecycleObserver, final Promise<List<T>> promise, Throwable th) {
        if (i >= dnsServerAddressStream.size() || this.allowedQueries == 0 || promise.isCancelled()) {
            tryToFinishResolve(dnsServerAddressStream, i, dnsQuestion, dnsQueryLifecycleObserver, promise, th);
            return;
        }
        this.allowedQueries--;
        InetSocketAddress next = dnsServerAddressStream.next();
        ChannelPromise newPromise = this.parent.f8571ch.newPromise();
        DnsNameResolver dnsNameResolver = this.parent;
        Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> query0 = dnsNameResolver.query0(next, dnsQuestion, this.additionals, newPromise, dnsNameResolver.f8571ch.eventLoop().newPromise());
        this.queriesInProgress.add(query0);
        dnsQueryLifecycleObserver.queryWritten(next, newPromise);
        query0.addListener(new FutureListener<AddressedEnvelope<DnsResponse, InetSocketAddress>>() { // from class: io.netty.resolver.dns.DnsResolveContext.3
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> future) {
                DnsResolveContext.this.queriesInProgress.remove(future);
                if (promise.isDone() || future.isCancelled()) {
                    dnsQueryLifecycleObserver.queryCancelled(DnsResolveContext.this.allowedQueries);
                    AddressedEnvelope<DnsResponse, InetSocketAddress> now = future.getNow();
                    if (now != null) {
                        now.release();
                        return;
                    }
                    return;
                }
                Throwable cause = future.cause();
                try {
                    if (cause == null) {
                        DnsResolveContext.this.onResponse(dnsServerAddressStream, i, dnsQuestion, future.getNow(), dnsQueryLifecycleObserver, promise);
                    } else {
                        dnsQueryLifecycleObserver.queryFailed(cause);
                        DnsResolveContext.this.query(dnsServerAddressStream, i + 1, dnsQuestion, promise, cause);
                    }
                } finally {
                    DnsResolveContext.this.tryToFinishResolve(dnsServerAddressStream, i, dnsQuestion, NoopDnsQueryLifecycleObserver.INSTANCE, promise, cause);
                }
            }
        });
    }

    void onResponse(DnsServerAddressStream dnsServerAddressStream, int i, DnsQuestion dnsQuestion, AddressedEnvelope<DnsResponse, InetSocketAddress> addressedEnvelope, DnsQueryLifecycleObserver dnsQueryLifecycleObserver, Promise<List<T>> promise) {
        try {
            DnsResponseCode code = addressedEnvelope.content().code();
            if (code != DnsResponseCode.NOERROR) {
                if (code != DnsResponseCode.NXDOMAIN) {
                    query(dnsServerAddressStream, i + 1, dnsQuestion, dnsQueryLifecycleObserver.queryNoAnswer(code), promise, null);
                } else {
                    dnsQueryLifecycleObserver.queryFailed(NXDOMAIN_QUERY_FAILED_EXCEPTION);
                }
                return;
            }
            if (handleRedirect(dnsQuestion, addressedEnvelope, dnsQueryLifecycleObserver, promise)) {
                return;
            }
            DnsRecordType type = dnsQuestion.type();
            if (type == DnsRecordType.CNAME) {
                onResponseCNAME(dnsQuestion, addressedEnvelope, dnsQueryLifecycleObserver, promise);
                return;
            }
            for (DnsRecordType dnsRecordType : this.expectedTypes) {
                if (type == dnsRecordType) {
                    onExpectedResponse(dnsQuestion, addressedEnvelope, dnsQueryLifecycleObserver, promise);
                    return;
                }
            }
            dnsQueryLifecycleObserver.queryFailed(UNRECOGNIZED_TYPE_QUERY_FAILED_EXCEPTION);
        } finally {
            ReferenceCountUtil.safeRelease(addressedEnvelope);
        }
    }

    private boolean handleRedirect(DnsQuestion dnsQuestion, AddressedEnvelope<DnsResponse, InetSocketAddress> addressedEnvelope, DnsQueryLifecycleObserver dnsQueryLifecycleObserver, Promise<List<T>> promise) {
        AuthoritativeNameServerList extractAuthoritativeNameServers;
        String name;
        AuthoritativeNameServer remove;
        InetAddress decodeAddress;
        DnsResponse content = addressedEnvelope.content();
        if (content.count(DnsSection.ANSWER) == 0 && (extractAuthoritativeNameServers = extractAuthoritativeNameServers(dnsQuestion.name(), content)) != null) {
            ArrayList arrayList = new ArrayList(extractAuthoritativeNameServers.size());
            int count = content.count(DnsSection.ADDITIONAL);
            for (int i = 0; i < count; i++) {
                DnsRecord recordAt = content.recordAt(DnsSection.ADDITIONAL, i);
                if ((recordAt.type() != DnsRecordType.f8484A || this.parent.supportsARecords()) && ((recordAt.type() != DnsRecordType.AAAA || this.parent.supportsAAAARecords()) && (remove = extractAuthoritativeNameServers.remove((name = recordAt.name()))) != null && (decodeAddress = DnsAddressDecoder.decodeAddress(recordAt, name, this.parent.isDecodeIdn())) != null)) {
                    arrayList.add(new InetSocketAddress(decodeAddress, this.parent.dnsRedirectPort(decodeAddress)));
                    addNameServerToCache(remove, decodeAddress, recordAt.timeToLive());
                }
            }
            if (!arrayList.isEmpty()) {
                query(this.parent.uncachedRedirectDnsServerStream(arrayList), 0, dnsQuestion, dnsQueryLifecycleObserver.queryRedirected(Collections.unmodifiableList(arrayList)), promise, null);
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: io.netty.resolver.dns.DnsResolveContext$4 */
    /* loaded from: classes8.dex */
    class C74014 implements AuthoritativeDnsServerCache {
        final /* synthetic */ AuthoritativeDnsServerCache val$authoritativeDnsServerCache;

        @Override // io.netty.resolver.dns.AuthoritativeDnsServerCache
        public DnsServerAddressStream get(String str) {
            return null;
        }

        C74014(AuthoritativeDnsServerCache authoritativeDnsServerCache) {
            this.val$authoritativeDnsServerCache = authoritativeDnsServerCache;
        }

        @Override // io.netty.resolver.dns.AuthoritativeDnsServerCache
        public void cache(String str, InetSocketAddress inetSocketAddress, long j, EventLoop eventLoop) {
            this.val$authoritativeDnsServerCache.cache(str, inetSocketAddress, j, eventLoop);
        }

        @Override // io.netty.resolver.dns.AuthoritativeDnsServerCache
        public void clear() {
            this.val$authoritativeDnsServerCache.clear();
        }

        @Override // io.netty.resolver.dns.AuthoritativeDnsServerCache
        public boolean clear(String str) {
            return this.val$authoritativeDnsServerCache.clear(str);
        }
    }

    private static AuthoritativeNameServerList extractAuthoritativeNameServers(String str, DnsResponse dnsResponse) {
        int count = dnsResponse.count(DnsSection.AUTHORITY);
        if (count == 0) {
            return null;
        }
        AuthoritativeNameServerList authoritativeNameServerList = new AuthoritativeNameServerList(str);
        for (int i = 0; i < count; i++) {
            authoritativeNameServerList.add(dnsResponse.recordAt(DnsSection.AUTHORITY, i));
        }
        return authoritativeNameServerList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x004e, code lost:
    
        if (r10.equals(r9) == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0050, code lost:
    
        r9 = r3.get(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x005a, code lost:
    
        if (r10.equals(r9) == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005d, code lost:
    
        if (r9 != null) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005f, code lost:
    
        if (r9 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0062, code lost:
    
        r9 = convertRecord(r8, r15.hostname, r15.additionals, r15.parent.f8571ch.eventLoop());
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0072, code lost:
    
        if (r9 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0077, code lost:
    
        if (r15.finalResult != null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0079, code lost:
    
        r15.finalResult = new java.util.ArrayList(8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0082, code lost:
    
        r15.finalResult.add(r9);
        cache(r15.hostname, r15.additionals, r8, r9);
        r7 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void onExpectedResponse(DnsQuestion dnsQuestion, AddressedEnvelope<DnsResponse, InetSocketAddress> addressedEnvelope, DnsQueryLifecycleObserver dnsQueryLifecycleObserver, Promise<List<T>> promise) {
        boolean z;
        DnsResponse content = addressedEnvelope.content();
        Map<String, String> buildAliasMap = buildAliasMap(content);
        int count = content.count(DnsSection.ANSWER);
        boolean z2 = false;
        for (int i = 0; i < count; i++) {
            DnsRecord recordAt = content.recordAt(DnsSection.ANSWER, i);
            DnsRecordType type = recordAt.type();
            DnsRecordType[] dnsRecordTypeArr = this.expectedTypes;
            int length = dnsRecordTypeArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = false;
                    break;
                } else {
                    if (type == dnsRecordTypeArr[i2]) {
                        z = true;
                        break;
                    }
                    i2++;
                }
            }
            if (z) {
                String lowerCase = dnsQuestion.name().toLowerCase(Locale.US);
                String lowerCase2 = recordAt.name().toLowerCase(Locale.US);
            }
        }
        if (z2) {
            dnsQueryLifecycleObserver.querySucceed();
        } else if (buildAliasMap.isEmpty()) {
            dnsQueryLifecycleObserver.queryFailed(NO_MATCHING_RECORD_QUERY_FAILED_EXCEPTION);
        } else {
            onResponseCNAME(dnsQuestion, buildAliasMap, dnsQueryLifecycleObserver, promise);
        }
    }

    private void onResponseCNAME(DnsQuestion dnsQuestion, AddressedEnvelope<DnsResponse, InetSocketAddress> addressedEnvelope, DnsQueryLifecycleObserver dnsQueryLifecycleObserver, Promise<List<T>> promise) {
        onResponseCNAME(dnsQuestion, buildAliasMap(addressedEnvelope.content()), dnsQueryLifecycleObserver, promise);
    }

    private void onResponseCNAME(DnsQuestion dnsQuestion, Map<String, String> map, DnsQueryLifecycleObserver dnsQueryLifecycleObserver, Promise<List<T>> promise) {
        String remove;
        String lowerCase = dnsQuestion.name().toLowerCase(Locale.US);
        boolean z = false;
        while (!map.isEmpty() && (remove = map.remove(lowerCase)) != null) {
            z = true;
            lowerCase = remove;
        }
        if (z) {
            followCname(dnsQuestion, lowerCase, dnsQueryLifecycleObserver, promise);
        } else {
            dnsQueryLifecycleObserver.queryFailed(CNAME_NOT_FOUND_QUERY_FAILED_EXCEPTION);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private static final class DnsAddressStreamList extends AbstractList<InetSocketAddress> {
        private List<InetSocketAddress> addresses;
        private final DnsServerAddressStream duplicate;

        DnsAddressStreamList(DnsServerAddressStream dnsServerAddressStream) {
            this.duplicate = dnsServerAddressStream.duplicate();
        }

        @Override // java.util.AbstractList, java.util.List
        public InetSocketAddress get(int i) {
            if (this.addresses == null) {
                DnsServerAddressStream duplicate = this.duplicate.duplicate();
                this.addresses = new ArrayList(size());
                for (int i2 = 0; i2 < duplicate.size(); i2++) {
                    this.addresses.add(duplicate.next());
                }
            }
            return this.addresses.get(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.duplicate.size();
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<InetSocketAddress> iterator() {
            return new Iterator<InetSocketAddress>() { // from class: io.netty.resolver.dns.DnsResolveContext.DnsAddressStreamList.1

                /* renamed from: i */
                private int f8573i;
                private final DnsServerAddressStream stream;

                {
                    this.stream = DnsAddressStreamList.this.duplicate.duplicate();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.f8573i < this.stream.size();
                }

                @Override // java.util.Iterator
                public InetSocketAddress next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    this.f8573i++;
                    return this.stream.next();
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private static Map<String, String> buildAliasMap(DnsResponse dnsResponse) {
        String decodeDomainName;
        int count = dnsResponse.count(DnsSection.ANSWER);
        HashMap hashMap = null;
        for (int i = 0; i < count; i++) {
            DnsRecord recordAt = dnsResponse.recordAt(DnsSection.ANSWER, i);
            if (recordAt.type() == DnsRecordType.CNAME && (recordAt instanceof DnsRawRecord) && (decodeDomainName = decodeDomainName(((ByteBufHolder) recordAt).content())) != null) {
                if (hashMap == null) {
                    hashMap = new HashMap(Math.min(8, count));
                }
                hashMap.put(recordAt.name().toLowerCase(Locale.US), decodeDomainName.toLowerCase(Locale.US));
            }
        }
        return hashMap != null ? hashMap : Collections.emptyMap();
    }

    void tryToFinishResolve(DnsServerAddressStream dnsServerAddressStream, int i, DnsQuestion dnsQuestion, DnsQueryLifecycleObserver dnsQueryLifecycleObserver, Promise<List<T>> promise, Throwable th) {
        if (!this.queriesInProgress.isEmpty()) {
            dnsQueryLifecycleObserver.queryCancelled(this.allowedQueries);
            List<T> list = this.finalResult;
            if (list == null || !containsExpectedResult(list)) {
                return;
            }
            finishResolve(promise, th);
            return;
        }
        if (this.finalResult == null) {
            if (i < dnsServerAddressStream.size()) {
                if (dnsQueryLifecycleObserver == NoopDnsQueryLifecycleObserver.INSTANCE) {
                    query(dnsServerAddressStream, 1 + i, dnsQuestion, promise, th);
                    return;
                } else {
                    query(dnsServerAddressStream, 1 + i, dnsQuestion, dnsQueryLifecycleObserver, promise, th);
                    return;
                }
            }
            dnsQueryLifecycleObserver.queryFailed(NAME_SERVERS_EXHAUSTED_EXCEPTION);
            if (th == null && !this.triedCNAME) {
                this.triedCNAME = true;
                query(this.hostname, DnsRecordType.CNAME, getNameServers(this.hostname), promise, (Throwable) null);
                return;
            }
        } else {
            dnsQueryLifecycleObserver.queryCancelled(this.allowedQueries);
        }
        finishResolve(promise, th);
    }

    private void finishResolve(Promise<List<T>> promise, Throwable th) {
        if (!this.queriesInProgress.isEmpty()) {
            Iterator<Future<AddressedEnvelope<DnsResponse, InetSocketAddress>>> it = this.queriesInProgress.iterator();
            while (it.hasNext()) {
                Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> next = it.next();
                it.remove();
                if (!next.cancel(false)) {
                    next.addListener(RELEASE_RESPONSE);
                }
            }
        }
        List<T> list = this.finalResult;
        if (list != null) {
            DnsNameResolver.trySuccess(promise, list);
            return;
        }
        int i = this.maxAllowedQueries - this.allowedQueries;
        StringBuilder sb = new StringBuilder(64);
        sb.append("failed to resolve '");
        sb.append(this.hostname);
        sb.append('\'');
        if (i > 1) {
            if (i < this.maxAllowedQueries) {
                sb.append(" after ");
                sb.append(i);
                sb.append(" queries ");
            } else {
                sb.append(". Exceeded max queries per resolve ");
                sb.append(this.maxAllowedQueries);
                sb.append(' ');
            }
        }
        UnknownHostException unknownHostException = new UnknownHostException(sb.toString());
        if (th == null) {
            cache(this.hostname, this.additionals, unknownHostException);
        } else {
            unknownHostException.initCause(th);
        }
        promise.tryFailure(unknownHostException);
    }

    static String decodeDomainName(ByteBuf byteBuf) {
        byteBuf.markReaderIndex();
        try {
            return DefaultDnsRecordDecoder.decodeName(byteBuf);
        } catch (CorruptedFrameException unused) {
            return null;
        } finally {
            byteBuf.resetReaderIndex();
        }
    }

    private DnsServerAddressStream getNameServers(String str) {
        DnsServerAddressStream nameServersFromCache = getNameServersFromCache(str);
        return nameServersFromCache == null ? this.nameServerAddrs.duplicate() : nameServersFromCache;
    }

    private void followCname(DnsQuestion dnsQuestion, String str, DnsQueryLifecycleObserver dnsQueryLifecycleObserver, Promise<List<T>> promise) {
        DnsServerAddressStream nameServers = getNameServers(str);
        try {
            DnsQuestion newQuestion = newQuestion(str, dnsQuestion.type());
            query(nameServers, 0, newQuestion, dnsQueryLifecycleObserver.queryCNAMEd(newQuestion), promise, null);
        } catch (Throwable th) {
            dnsQueryLifecycleObserver.queryFailed(th);
            PlatformDependent.throwException(th);
        }
    }

    private boolean query(String str, DnsRecordType dnsRecordType, DnsServerAddressStream dnsServerAddressStream, Promise<List<T>> promise, Throwable th) {
        DnsQuestion newQuestion = newQuestion(str, dnsRecordType);
        if (newQuestion == null) {
            return false;
        }
        query(dnsServerAddressStream, 0, newQuestion, promise, th);
        return true;
    }

    private DnsQuestion newQuestion(String str, DnsRecordType dnsRecordType) {
        try {
            return new DefaultDnsQuestion(str, dnsRecordType, this.dnsClass);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class AuthoritativeNameServerList {
        private int count;
        private AuthoritativeNameServer head;
        private final String questionName;

        AuthoritativeNameServerList(String str) {
            this.questionName = str.toLowerCase(Locale.US);
        }

        void add(DnsRecord dnsRecord) {
            String decodeDomainName;
            if (dnsRecord.type() == DnsRecordType.f8488NS && (dnsRecord instanceof DnsRawRecord) && this.questionName.length() >= dnsRecord.name().length()) {
                String lowerCase = dnsRecord.name().toLowerCase(Locale.US);
                int i = 0;
                int length = lowerCase.length() - 1;
                int length2 = this.questionName.length() - 1;
                while (length >= 0) {
                    char charAt = lowerCase.charAt(length);
                    if (this.questionName.charAt(length2) != charAt) {
                        return;
                    }
                    if (charAt == '.') {
                        i++;
                    }
                    length--;
                    length2--;
                }
                AuthoritativeNameServer authoritativeNameServer = this.head;
                if ((authoritativeNameServer == null || authoritativeNameServer.dots <= i) && (decodeDomainName = DnsResolveContext.decodeDomainName(((ByteBufHolder) dnsRecord).content())) != null) {
                    AuthoritativeNameServer authoritativeNameServer2 = this.head;
                    if (authoritativeNameServer2 == null || authoritativeNameServer2.dots < i) {
                        this.count = 1;
                        this.head = new AuthoritativeNameServer(i, lowerCase, decodeDomainName);
                    } else if (this.head.dots == i) {
                        AuthoritativeNameServer authoritativeNameServer3 = this.head;
                        while (authoritativeNameServer3.next != null) {
                            authoritativeNameServer3 = authoritativeNameServer3.next;
                        }
                        authoritativeNameServer3.next = new AuthoritativeNameServer(i, lowerCase, decodeDomainName);
                        this.count++;
                    }
                }
            }
        }

        AuthoritativeNameServer remove(String str) {
            for (AuthoritativeNameServer authoritativeNameServer = this.head; authoritativeNameServer != null; authoritativeNameServer = authoritativeNameServer.next) {
                if (!authoritativeNameServer.removed && authoritativeNameServer.nsName.equalsIgnoreCase(str)) {
                    authoritativeNameServer.removed = true;
                    return authoritativeNameServer;
                }
            }
            return null;
        }

        int size() {
            return this.count;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class AuthoritativeNameServer {
        final String domainName;
        final int dots;
        AuthoritativeNameServer next;
        final String nsName;
        boolean removed;

        AuthoritativeNameServer(int i, String str, String str2) {
            this.dots = i;
            this.nsName = str2;
            this.domainName = str;
        }

        boolean isRootServer() {
            return this.dots == 1;
        }

        String domainName() {
            return this.domainName;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private final class CombinedDnsServerAddressStream implements DnsServerAddressStream {
        private final DnsServerAddressStream originalStream;
        private final InetSocketAddress replaced;
        private Iterator<InetAddress> resolved;
        private final List<InetAddress> resolvedAddresses;

        CombinedDnsServerAddressStream(InetSocketAddress inetSocketAddress, List<InetAddress> list, DnsServerAddressStream dnsServerAddressStream) {
            this.replaced = inetSocketAddress;
            this.resolvedAddresses = list;
            this.originalStream = dnsServerAddressStream;
            this.resolved = list.iterator();
        }

        @Override // io.netty.resolver.dns.DnsServerAddressStream
        public InetSocketAddress next() {
            if (this.resolved.hasNext()) {
                return nextResolved0();
            }
            InetSocketAddress next = this.originalStream.next();
            if (!next.equals(this.replaced)) {
                return next;
            }
            this.resolved = this.resolvedAddresses.iterator();
            return nextResolved0();
        }

        private InetSocketAddress nextResolved0() {
            return DnsResolveContext.this.parent.newRedirectServerAddress(this.resolved.next());
        }

        @Override // io.netty.resolver.dns.DnsServerAddressStream
        public int size() {
            return (this.originalStream.size() + this.resolvedAddresses.size()) - 1;
        }

        @Override // io.netty.resolver.dns.DnsServerAddressStream
        public DnsServerAddressStream duplicate() {
            return new CombinedDnsServerAddressStream(this.replaced, this.resolvedAddresses, this.originalStream.duplicate());
        }
    }
}
