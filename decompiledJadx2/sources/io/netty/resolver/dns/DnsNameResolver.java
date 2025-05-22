package io.netty.resolver.dns;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.handler.codec.dns.DatagramDnsQueryEncoder;
import io.netty.handler.codec.dns.DatagramDnsResponse;
import io.netty.handler.codec.dns.DatagramDnsResponseDecoder;
import io.netty.handler.codec.dns.DefaultDnsRawRecord;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRawRecord;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.handler.codec.dns.TcpDnsResponseDecoder;
import io.netty.resolver.HostsFileEntriesResolver;
import io.netty.resolver.InetNameResolver;
import io.netty.resolver.ResolvedAddressTypes;
import io.netty.util.NetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.IDN;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DnsNameResolver extends InetNameResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final DatagramDnsResponseDecoder DECODER;
    private static final int DEFAULT_NDOTS;
    static final ResolvedAddressTypes DEFAULT_RESOLVE_ADDRESS_TYPES;
    static final String[] DEFAULT_SEARCH_DOMAINS;
    private static final DatagramDnsQueryEncoder ENCODER;
    private static final String LOCALHOST = "localhost";
    private static final InetAddress LOCALHOST_ADDRESS;
    private final DnsCache authoritativeDnsServerCache;

    /* renamed from: ch */
    final DatagramChannel f8571ch;
    final Future<Channel> channelFuture;
    private final boolean decodeIdn;
    private final DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory;
    private final DnsServerAddressStreamProvider dnsServerAddressStreamProvider;
    private final HostsFileEntriesResolver hostsFileEntriesResolver;
    private final int maxPayloadSize;
    private final int maxQueriesPerResolve;
    private final FastThreadLocal<DnsServerAddressStream> nameServerAddrStream;
    private final int ndots;
    private final boolean optResourceEnabled;
    private final InternetProtocolFamily preferredAddressType;
    final DnsQueryContextManager queryContextManager;
    private final long queryTimeoutMillis;
    private final boolean recursionDesired;
    private final DnsCache resolveCache;
    private final DnsRecordType[] resolveRecordTypes;
    private final ResolvedAddressTypes resolvedAddressTypes;
    private final InternetProtocolFamily[] resolvedInternetProtocolFamilies;
    private final String[] searchDomains;
    private final boolean supportsAAAARecords;
    private final boolean supportsARecords;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DnsNameResolver.class);
    private static final DnsRecord[] EMPTY_ADDITIONALS = new DnsRecord[0];
    private static final DnsRecordType[] IPV4_ONLY_RESOLVED_RECORD_TYPES = {DnsRecordType.f8484A};
    private static final InternetProtocolFamily[] IPV4_ONLY_RESOLVED_PROTOCOL_FAMILIES = {InternetProtocolFamily.IPv4};
    private static final DnsRecordType[] IPV4_PREFERRED_RESOLVED_RECORD_TYPES = {DnsRecordType.f8484A, DnsRecordType.AAAA};
    private static final InternetProtocolFamily[] IPV4_PREFERRED_RESOLVED_PROTOCOL_FAMILIES = {InternetProtocolFamily.IPv4, InternetProtocolFamily.IPv6};
    private static final DnsRecordType[] IPV6_ONLY_RESOLVED_RECORD_TYPES = {DnsRecordType.AAAA};
    private static final InternetProtocolFamily[] IPV6_ONLY_RESOLVED_PROTOCOL_FAMILIES = {InternetProtocolFamily.IPv6};
    private static final DnsRecordType[] IPV6_PREFERRED_RESOLVED_RECORD_TYPES = {DnsRecordType.AAAA, DnsRecordType.f8484A};
    private static final InternetProtocolFamily[] IPV6_PREFERRED_RESOLVED_PROTOCOL_FAMILIES = {InternetProtocolFamily.IPv6, InternetProtocolFamily.IPv4};

    /* JADX WARN: Multi-variable type inference failed */
    private static Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> cast(Promise<?> promise) {
        return promise;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int dnsRedirectPort(InetAddress inetAddress) {
        return 53;
    }

    static {
        String[] strArr;
        int i = 1;
        if (NetUtil.isIpV4StackPreferred()) {
            DEFAULT_RESOLVE_ADDRESS_TYPES = ResolvedAddressTypes.IPV4_ONLY;
            LOCALHOST_ADDRESS = NetUtil.LOCALHOST4;
        } else if (NetUtil.isIpV6AddressesPreferred()) {
            DEFAULT_RESOLVE_ADDRESS_TYPES = ResolvedAddressTypes.IPV6_PREFERRED;
            LOCALHOST_ADDRESS = NetUtil.LOCALHOST6;
        } else {
            DEFAULT_RESOLVE_ADDRESS_TYPES = ResolvedAddressTypes.IPV4_PREFERRED;
            LOCALHOST_ADDRESS = NetUtil.LOCALHOST4;
        }
        try {
            Class<?> cls = Class.forName("sun.net.dns.ResolverConfiguration");
            List list = (List) cls.getMethod("searchlist", new Class[0]).invoke(cls.getMethod("open", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            strArr = (String[]) list.toArray(new String[list.size()]);
        } catch (Exception unused) {
            strArr = EmptyArrays.EMPTY_STRINGS;
        }
        DEFAULT_SEARCH_DOMAINS = strArr;
        try {
            i = UnixResolverDnsServerAddressStreamProvider.parseEtcResolverFirstNdots();
        } catch (Exception unused2) {
        }
        DEFAULT_NDOTS = i;
        DECODER = new DatagramDnsResponseDecoder();
        ENCODER = new DatagramDnsQueryEncoder();
    }

    public DnsNameResolver(EventLoop eventLoop, ChannelFactory<? extends DatagramChannel> channelFactory, final DnsCache dnsCache, DnsCache dnsCache2, DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory, long j, ResolvedAddressTypes resolvedAddressTypes, boolean z, int i, boolean z2, int i2, boolean z3, HostsFileEntriesResolver hostsFileEntriesResolver, DnsServerAddressStreamProvider dnsServerAddressStreamProvider, String[] strArr, int i3, boolean z4) {
        super(eventLoop);
        DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory2;
        this.queryContextManager = new DnsQueryContextManager();
        this.nameServerAddrStream = new FastThreadLocal<DnsServerAddressStream>() { // from class: io.netty.resolver.dns.DnsNameResolver.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.netty.util.concurrent.FastThreadLocal
            public DnsServerAddressStream initialValue() throws Exception {
                return DnsNameResolver.this.dnsServerAddressStreamProvider.nameServerAddressStream("");
            }
        };
        this.queryTimeoutMillis = ObjectUtil.checkPositive(j, "queryTimeoutMillis");
        this.resolvedAddressTypes = resolvedAddressTypes != null ? resolvedAddressTypes : DEFAULT_RESOLVE_ADDRESS_TYPES;
        this.recursionDesired = z;
        this.maxQueriesPerResolve = ObjectUtil.checkPositive(i, "maxQueriesPerResolve");
        this.maxPayloadSize = ObjectUtil.checkPositive(i2, "maxPayloadSize");
        this.optResourceEnabled = z3;
        this.hostsFileEntriesResolver = (HostsFileEntriesResolver) ObjectUtil.checkNotNull(hostsFileEntriesResolver, "hostsFileEntriesResolver");
        this.dnsServerAddressStreamProvider = (DnsServerAddressStreamProvider) ObjectUtil.checkNotNull(dnsServerAddressStreamProvider, "dnsServerAddressStreamProvider");
        this.resolveCache = (DnsCache) ObjectUtil.checkNotNull(dnsCache, "resolveCache");
        this.authoritativeDnsServerCache = (DnsCache) ObjectUtil.checkNotNull(dnsCache2, "authoritativeDnsServerCache");
        if (z2) {
            dnsQueryLifecycleObserverFactory2 = dnsQueryLifecycleObserverFactory instanceof NoopDnsQueryLifecycleObserverFactory ? new TraceDnsQueryLifeCycleObserverFactory() : new BiDnsQueryLifecycleObserverFactory(new TraceDnsQueryLifeCycleObserverFactory(), dnsQueryLifecycleObserverFactory);
        } else {
            dnsQueryLifecycleObserverFactory2 = (DnsQueryLifecycleObserverFactory) ObjectUtil.checkNotNull(dnsQueryLifecycleObserverFactory, "dnsQueryLifecycleObserverFactory");
        }
        this.dnsQueryLifecycleObserverFactory = dnsQueryLifecycleObserverFactory2;
        this.searchDomains = strArr != null ? (String[]) strArr.clone() : DEFAULT_SEARCH_DOMAINS;
        this.ndots = i3 >= 0 ? i3 : DEFAULT_NDOTS;
        this.decodeIdn = z4;
        int i4 = C73885.$SwitchMap$io$netty$resolver$ResolvedAddressTypes[this.resolvedAddressTypes.ordinal()];
        if (i4 == 1) {
            this.supportsAAAARecords = false;
            this.supportsARecords = true;
            this.resolveRecordTypes = IPV4_ONLY_RESOLVED_RECORD_TYPES;
            this.resolvedInternetProtocolFamilies = IPV4_ONLY_RESOLVED_PROTOCOL_FAMILIES;
            this.preferredAddressType = InternetProtocolFamily.IPv4;
        } else if (i4 == 2) {
            this.supportsAAAARecords = true;
            this.supportsARecords = true;
            this.resolveRecordTypes = IPV4_PREFERRED_RESOLVED_RECORD_TYPES;
            this.resolvedInternetProtocolFamilies = IPV4_PREFERRED_RESOLVED_PROTOCOL_FAMILIES;
            this.preferredAddressType = InternetProtocolFamily.IPv4;
        } else if (i4 == 3) {
            this.supportsAAAARecords = true;
            this.supportsARecords = false;
            this.resolveRecordTypes = IPV6_ONLY_RESOLVED_RECORD_TYPES;
            this.resolvedInternetProtocolFamilies = IPV6_ONLY_RESOLVED_PROTOCOL_FAMILIES;
            this.preferredAddressType = InternetProtocolFamily.IPv6;
        } else if (i4 == 4) {
            this.supportsAAAARecords = true;
            this.supportsARecords = true;
            this.resolveRecordTypes = IPV6_PREFERRED_RESOLVED_RECORD_TYPES;
            this.resolvedInternetProtocolFamilies = IPV6_PREFERRED_RESOLVED_PROTOCOL_FAMILIES;
            this.preferredAddressType = InternetProtocolFamily.IPv6;
        } else {
            throw new IllegalArgumentException("Unknown ResolvedAddressTypes " + resolvedAddressTypes);
        }
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(executor());
        bootstrap.channelFactory((ChannelFactory) channelFactory);
        bootstrap.option(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION, true);
        final DnsResponseHandler dnsResponseHandler = new DnsResponseHandler(executor().newPromise());
        bootstrap.handler(new ChannelInitializer<DatagramChannel>() { // from class: io.netty.resolver.dns.DnsNameResolver.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.channel.ChannelInitializer
            public void initChannel(DatagramChannel datagramChannel) throws Exception {
                datagramChannel.pipeline().addLast(DnsNameResolver.DECODER, DnsNameResolver.ENCODER, dnsResponseHandler);
            }
        });
        this.channelFuture = dnsResponseHandler.channelActivePromise;
        DatagramChannel datagramChannel = (DatagramChannel) bootstrap.register().channel();
        this.f8571ch = datagramChannel;
        datagramChannel.config().setRecvByteBufAllocator((RecvByteBufAllocator) new FixedRecvByteBufAllocator(i2));
        this.f8571ch.closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.resolver.dns.DnsNameResolver.3
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                dnsCache.clear();
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.resolver.dns.DnsNameResolver$5 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C73885 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$resolver$ResolvedAddressTypes;

        static {
            int[] iArr = new int[ResolvedAddressTypes.values().length];
            $SwitchMap$io$netty$resolver$ResolvedAddressTypes = iArr;
            try {
                iArr[ResolvedAddressTypes.IPV4_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV4_PREFERRED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV6_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV6_PREFERRED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory() {
        return this.dnsQueryLifecycleObserverFactory;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DnsServerAddressStream uncachedRedirectDnsServerStream(List<InetSocketAddress> list) {
        return DnsServerAddresses.sequential(list).stream();
    }

    public DnsCache resolveCache() {
        return this.resolveCache;
    }

    public DnsCache authoritativeDnsServerCache() {
        return this.authoritativeDnsServerCache;
    }

    public long queryTimeoutMillis() {
        return this.queryTimeoutMillis;
    }

    public ResolvedAddressTypes resolvedAddressTypes() {
        return this.resolvedAddressTypes;
    }

    InternetProtocolFamily[] resolvedInternetProtocolFamiliesUnsafe() {
        return this.resolvedInternetProtocolFamilies;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String[] searchDomains() {
        return this.searchDomains;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int ndots() {
        return this.ndots;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean supportsAAAARecords() {
        return this.supportsAAAARecords;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean supportsARecords() {
        return this.supportsARecords;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final InternetProtocolFamily preferredAddressType() {
        return this.preferredAddressType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final DnsRecordType[] resolveRecordTypes() {
        return this.resolveRecordTypes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isDecodeIdn() {
        return this.decodeIdn;
    }

    public boolean isRecursionDesired() {
        return this.recursionDesired;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: io.netty.resolver.dns.DnsNameResolver$7 */
    /* loaded from: classes8.dex */
    static /* synthetic */ class C73907 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$resolver$ResolvedAddressTypes = new int[ResolvedAddressTypes.values().length];

        static {
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV4_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV4_PREFERRED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV6_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV6_PREFERRED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public int maxQueriesPerResolve() {
        return this.maxQueriesPerResolve;
    }

    public int maxPayloadSize() {
        return this.maxPayloadSize;
    }

    public boolean isOptResourceEnabled() {
        return this.optResourceEnabled;
    }

    public HostsFileEntriesResolver hostsFileEntriesResolver() {
        return this.hostsFileEntriesResolver;
    }

    @Override // io.netty.resolver.SimpleNameResolver, io.netty.resolver.NameResolver, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f8571ch.isOpen()) {
            this.f8571ch.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.resolver.SimpleNameResolver
    public EventLoop executor() {
        return (EventLoop) super.executor();
    }

    private InetAddress resolveHostsFileEntry(String str) {
        HostsFileEntriesResolver hostsFileEntriesResolver = this.hostsFileEntriesResolver;
        if (hostsFileEntriesResolver == null) {
            return null;
        }
        InetAddress address = hostsFileEntriesResolver.address(str, this.resolvedAddressTypes);
        return (address == null && PlatformDependent.isWindows() && LOCALHOST.equalsIgnoreCase(str)) ? LOCALHOST_ADDRESS : address;
    }

    public final Future<InetAddress> resolve(String str, Iterable<DnsRecord> iterable) {
        return resolve(str, iterable, executor().newPromise());
    }

    public final Future<InetAddress> resolve(String str, Iterable<DnsRecord> iterable, Promise<InetAddress> promise) {
        ObjectUtil.checkNotNull(promise, "promise");
        try {
            doResolve(str, toArray(iterable, true), promise, this.resolveCache);
            return promise;
        } catch (Exception e) {
            return promise.setFailure(e);
        }
    }

    public final Future<List<InetAddress>> resolveAll(String str, Iterable<DnsRecord> iterable) {
        return resolveAll(str, iterable, executor().newPromise());
    }

    public final Future<List<InetAddress>> resolveAll(String str, Iterable<DnsRecord> iterable, Promise<List<InetAddress>> promise) {
        ObjectUtil.checkNotNull(promise, "promise");
        try {
            doResolveAll(str, toArray(iterable, true), promise, this.resolveCache);
            return promise;
        } catch (Exception e) {
            return promise.setFailure(e);
        }
    }

    @Override // io.netty.resolver.SimpleNameResolver
    protected void doResolve(String str, Promise<InetAddress> promise) throws Exception {
        doResolve(str, EMPTY_ADDITIONALS, promise, this.resolveCache);
    }

    public final Future<List<DnsRecord>> resolveAll(DnsQuestion dnsQuestion) {
        return resolveAll(dnsQuestion, EMPTY_ADDITIONALS, executor().newPromise());
    }

    public final Future<List<DnsRecord>> resolveAll(DnsQuestion dnsQuestion, Iterable<DnsRecord> iterable) {
        return resolveAll(dnsQuestion, iterable, executor().newPromise());
    }

    public final Future<List<DnsRecord>> resolveAll(DnsQuestion dnsQuestion, Iterable<DnsRecord> iterable, Promise<List<DnsRecord>> promise) {
        return resolveAll(dnsQuestion, toArray(iterable, true), promise);
    }

    private Future<List<DnsRecord>> resolveAll(DnsQuestion dnsQuestion, DnsRecord[] dnsRecordArr, Promise<List<DnsRecord>> promise) {
        InetAddress resolveHostsFileEntry;
        ByteBuf wrappedBuffer;
        ByteBuf byteBuf;
        ObjectUtil.checkNotNull(dnsQuestion, "question");
        ObjectUtil.checkNotNull(promise, "promise");
        DnsRecordType type = dnsQuestion.type();
        String name = dnsQuestion.name();
        if ((type == DnsRecordType.f8484A || type == DnsRecordType.AAAA) && (resolveHostsFileEntry = resolveHostsFileEntry(name)) != null) {
            if (resolveHostsFileEntry instanceof Inet4Address) {
                if (type == DnsRecordType.f8484A) {
                    wrappedBuffer = Unpooled.wrappedBuffer(resolveHostsFileEntry.getAddress());
                    byteBuf = wrappedBuffer;
                }
                byteBuf = null;
            } else {
                if ((resolveHostsFileEntry instanceof Inet6Address) && type == DnsRecordType.AAAA) {
                    wrappedBuffer = Unpooled.wrappedBuffer(resolveHostsFileEntry.getAddress());
                    byteBuf = wrappedBuffer;
                }
                byteBuf = null;
            }
            if (byteBuf != null) {
                trySuccess(promise, Collections.singletonList(new DefaultDnsRawRecord(name, type, 86400L, byteBuf)));
                return promise;
            }
        }
        new DnsRecordResolveContext(this, dnsQuestion, dnsRecordArr, this.dnsServerAddressStreamProvider.nameServerAddressStream(name)).resolve(promise);
        return promise;
    }

    private static DnsRecord[] toArray(Iterable<DnsRecord> iterable, boolean z) {
        ObjectUtil.checkNotNull(iterable, "additionals");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            Iterator<DnsRecord> it = iterable.iterator();
            while (it.hasNext()) {
                validateAdditional(it.next(), z);
            }
            return (DnsRecord[]) collection.toArray(new DnsRecord[collection.size()]);
        }
        Iterator<DnsRecord> it2 = iterable.iterator();
        if (!it2.hasNext()) {
            return EMPTY_ADDITIONALS;
        }
        ArrayList arrayList = new ArrayList();
        do {
            DnsRecord next = it2.next();
            validateAdditional(next, z);
            arrayList.add(next);
        } while (it2.hasNext());
        return (DnsRecord[]) arrayList.toArray(new DnsRecord[arrayList.size()]);
    }

    private static void validateAdditional(DnsRecord dnsRecord, boolean z) {
        ObjectUtil.checkNotNull(dnsRecord, "record");
        if (z && (dnsRecord instanceof DnsRawRecord)) {
            throw new IllegalArgumentException("DnsRawRecord implementations not allowed: " + dnsRecord);
        }
    }

    private InetAddress loopbackAddress() {
        return preferredAddressType().localhost();
    }

    protected void doResolve(String str, DnsRecord[] dnsRecordArr, Promise<InetAddress> promise, DnsCache dnsCache) throws Exception {
        if (str == null || str.isEmpty()) {
            promise.setSuccess(loopbackAddress());
            return;
        }
        byte[] createByteArrayFromIpAddressString = NetUtil.createByteArrayFromIpAddressString(str);
        if (createByteArrayFromIpAddressString != null) {
            promise.setSuccess(InetAddress.getByAddress(createByteArrayFromIpAddressString));
            return;
        }
        String hostname = hostname(str);
        InetAddress resolveHostsFileEntry = resolveHostsFileEntry(hostname);
        if (resolveHostsFileEntry != null) {
            promise.setSuccess(resolveHostsFileEntry);
        } else {
            if (doResolveCached(hostname, dnsRecordArr, promise, dnsCache)) {
                return;
            }
            doResolveUncached(hostname, dnsRecordArr, promise, dnsCache);
        }
    }

    private boolean doResolveCached(String str, DnsRecord[] dnsRecordArr, Promise<InetAddress> promise, DnsCache dnsCache) {
        List<? extends DnsCacheEntry> list = dnsCache.get(str, dnsRecordArr);
        if (list == null || list.isEmpty()) {
            return false;
        }
        Throwable cause = list.get(0).cause();
        if (cause == null) {
            int size = list.size();
            for (InternetProtocolFamily internetProtocolFamily : this.resolvedInternetProtocolFamilies) {
                for (int i = 0; i < size; i++) {
                    DnsCacheEntry dnsCacheEntry = list.get(i);
                    if (internetProtocolFamily.addressType().isInstance(dnsCacheEntry.address())) {
                        trySuccess(promise, dnsCacheEntry.address());
                        return true;
                    }
                }
            }
            return false;
        }
        tryFailure(promise, cause);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void trySuccess(Promise<T> promise, T t) {
        if (promise.trySuccess(t)) {
            return;
        }
        logger.warn("Failed to notify success ({}) to a promise: {}", t, promise);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void tryFailure(Promise<?> promise, Throwable th) {
        if (promise.tryFailure(th)) {
            return;
        }
        logger.warn("Failed to notify failure to a promise: {}", promise, th);
    }

    private void doResolveUncached(String str, DnsRecord[] dnsRecordArr, final Promise<InetAddress> promise, DnsCache dnsCache) {
        Promise<List<InetAddress>> newPromise = executor().newPromise();
        doResolveAllUncached(str, dnsRecordArr, newPromise, dnsCache);
        newPromise.addListener((GenericFutureListener<? extends Future<? super List<InetAddress>>>) new FutureListener<List<InetAddress>>() { // from class: io.netty.resolver.dns.DnsNameResolver.4
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<List<InetAddress>> future) {
                if (!future.isSuccess()) {
                    DnsNameResolver.tryFailure(promise, future.cause());
                } else {
                    DnsNameResolver.trySuccess(promise, future.getNow().get(0));
                }
            }
        });
    }

    @Override // io.netty.resolver.SimpleNameResolver
    protected void doResolveAll(String str, Promise<List<InetAddress>> promise) throws Exception {
        doResolveAll(str, EMPTY_ADDITIONALS, promise, this.resolveCache);
    }

    protected void doResolveAll(String str, DnsRecord[] dnsRecordArr, Promise<List<InetAddress>> promise, DnsCache dnsCache) throws Exception {
        if (str == null || str.isEmpty()) {
            promise.setSuccess(Collections.singletonList(loopbackAddress()));
            return;
        }
        byte[] createByteArrayFromIpAddressString = NetUtil.createByteArrayFromIpAddressString(str);
        if (createByteArrayFromIpAddressString != null) {
            promise.setSuccess(Collections.singletonList(InetAddress.getByAddress(createByteArrayFromIpAddressString)));
            return;
        }
        String hostname = hostname(str);
        InetAddress resolveHostsFileEntry = resolveHostsFileEntry(hostname);
        if (resolveHostsFileEntry != null) {
            promise.setSuccess(Collections.singletonList(resolveHostsFileEntry));
        } else {
            if (doResolveAllCached(hostname, dnsRecordArr, promise, dnsCache)) {
                return;
            }
            doResolveAllUncached(hostname, dnsRecordArr, promise, dnsCache);
        }
    }

    private boolean doResolveAllCached(String str, DnsRecord[] dnsRecordArr, Promise<List<InetAddress>> promise, DnsCache dnsCache) {
        List<? extends DnsCacheEntry> list = dnsCache.get(str, dnsRecordArr);
        if (list == null || list.isEmpty()) {
            return false;
        }
        Throwable cause = list.get(0).cause();
        if (cause == null) {
            ArrayList arrayList = null;
            int size = list.size();
            for (InternetProtocolFamily internetProtocolFamily : this.resolvedInternetProtocolFamilies) {
                for (int i = 0; i < size; i++) {
                    DnsCacheEntry dnsCacheEntry = list.get(i);
                    if (internetProtocolFamily.addressType().isInstance(dnsCacheEntry.address())) {
                        if (arrayList == null) {
                            arrayList = new ArrayList(size);
                        }
                        arrayList.add(dnsCacheEntry.address());
                    }
                }
            }
            if (arrayList == null) {
                return false;
            }
            trySuccess(promise, arrayList);
            return true;
        }
        tryFailure(promise, cause);
        return true;
    }

    private void doResolveAllUncached(String str, DnsRecord[] dnsRecordArr, Promise<List<InetAddress>> promise, DnsCache dnsCache) {
        new DnsAddressResolveContext(this, str, dnsRecordArr, this.dnsServerAddressStreamProvider.nameServerAddressStream(str), dnsCache).resolve(promise);
    }

    private static String hostname(String str) {
        String ascii = IDN.toASCII(str);
        if (!StringUtil.endsWith(str, FilenameUtils.EXTENSION_SEPARATOR) || StringUtil.endsWith(ascii, FilenameUtils.EXTENSION_SEPARATOR)) {
            return ascii;
        }
        return ascii + ".";
    }

    public Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> query(DnsQuestion dnsQuestion) {
        return query(nextNameServerAddress(), dnsQuestion);
    }

    public Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> query(DnsQuestion dnsQuestion, Iterable<DnsRecord> iterable) {
        return query(nextNameServerAddress(), dnsQuestion, iterable);
    }

    public Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> query(DnsQuestion dnsQuestion, Promise<AddressedEnvelope<? extends DnsResponse, InetSocketAddress>> promise) {
        return query(nextNameServerAddress(), dnsQuestion, Collections.emptyList(), promise);
    }

    private InetSocketAddress nextNameServerAddress() {
        return this.nameServerAddrStream.get().next();
    }

    public Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> query(InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion) {
        return query0(inetSocketAddress, dnsQuestion, EMPTY_ADDITIONALS, this.f8571ch.eventLoop().newPromise());
    }

    public Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> query(InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion, Iterable<DnsRecord> iterable) {
        return query0(inetSocketAddress, dnsQuestion, toArray(iterable, false), this.f8571ch.eventLoop().newPromise());
    }

    public Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> query(InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion, Promise<AddressedEnvelope<? extends DnsResponse, InetSocketAddress>> promise) {
        return query0(inetSocketAddress, dnsQuestion, EMPTY_ADDITIONALS, promise);
    }

    public Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> query(InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion, Iterable<DnsRecord> iterable, Promise<AddressedEnvelope<? extends DnsResponse, InetSocketAddress>> promise) {
        return query0(inetSocketAddress, dnsQuestion, toArray(iterable, false), promise);
    }

    public static boolean isTransportOrTimeoutError(Throwable th) {
        return th != null && (th.getCause() instanceof DnsNameResolverException);
    }

    public static boolean isTimeoutError(Throwable th) {
        return th != null && (th.getCause() instanceof DnsNameResolverTimeoutException);
    }

    final Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> query0(InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion, DnsRecord[] dnsRecordArr, Promise<AddressedEnvelope<? extends DnsResponse, InetSocketAddress>> promise) {
        return query0(inetSocketAddress, dnsQuestion, dnsRecordArr, this.f8571ch.newPromise(), promise);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> query0(InetSocketAddress inetSocketAddress, DnsQuestion dnsQuestion, DnsRecord[] dnsRecordArr, ChannelPromise channelPromise, Promise<AddressedEnvelope<? extends DnsResponse, InetSocketAddress>> promise) {
        Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> cast = cast((Promise) ObjectUtil.checkNotNull(promise, "promise"));
        try {
            new DnsQueryContext(this, inetSocketAddress, dnsQuestion, dnsRecordArr, cast).query(channelPromise);
            return cast;
        } catch (Exception e) {
            return cast.setFailure(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public final class DnsResponseHandler extends ChannelInboundHandlerAdapter {
        private final Promise<Channel> channelActivePromise;

        DnsResponseHandler(Promise<Channel> promise) {
            this.channelActivePromise = promise;
        }

        @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
        public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
            try {
                DatagramDnsResponse datagramDnsResponse = (DatagramDnsResponse) obj;
                int id = datagramDnsResponse.mo3938id();
                if (DnsNameResolver.logger.isDebugEnabled()) {
                    DnsNameResolver.logger.debug("{} RECEIVED: [{}: {}], {}", DnsNameResolver.this.f8571ch, Integer.valueOf(id), datagramDnsResponse.sender(), datagramDnsResponse);
                }
                DnsQueryContext dnsQueryContext = DnsNameResolver.this.queryContextManager.get(datagramDnsResponse.sender(), id);
                if (dnsQueryContext == null) {
                    DnsNameResolver.logger.warn("{} Received a DNS response with an unknown ID: {}", DnsNameResolver.this.f8571ch, Integer.valueOf(id));
                } else {
                    dnsQueryContext.finish(datagramDnsResponse);
                }
            } finally {
                ReferenceCountUtil.safeRelease(obj);
            }
        }

        @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
        public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
            super.channelActive(channelHandlerContext);
            this.channelActivePromise.setSuccess(channelHandlerContext.channel());
        }

        @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
            DnsNameResolver.logger.warn("{} Unexpected exception: ", DnsNameResolver.this.f8571ch, th);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes5.dex
         */
        /* renamed from: io.netty.resolver.dns.DnsNameResolver$DnsResponseHandler$1 */
        /* loaded from: classes8.dex */
        class C73911 extends ChannelInitializer<Channel> {
            final /* synthetic */ DnsQueryContext val$qCtx;
            final /* synthetic */ int val$queryId;
            final /* synthetic */ DatagramDnsResponse val$res;

            C73911(DatagramDnsResponse datagramDnsResponse, DnsQueryContext dnsQueryContext, int i) {
                this.val$res = datagramDnsResponse;
                this.val$qCtx = dnsQueryContext;
                this.val$queryId = i;
            }

            @Override // io.netty.channel.ChannelInitializer
            protected void initChannel(Channel channel) {
                channel.pipeline().addLast(DnsNameResolver.access$800());
                channel.pipeline().addLast(new TcpDnsResponseDecoder());
                channel.pipeline().addLast(new ChannelInboundHandlerAdapter() { // from class: io.netty.resolver.dns.DnsNameResolver.DnsResponseHandler.1.1
                    private boolean finish;

                    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
                    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
                        try {
                            Channel channel2 = channelHandlerContext.channel();
                            DnsResponse dnsResponse = (DnsResponse) obj;
                            int id = dnsResponse.mo3938id();
                            if (DnsNameResolver.access$000().isDebugEnabled()) {
                                DnsNameResolver.access$000().debug("{} RECEIVED: TCP [{}: {}], {}", channel2, Integer.valueOf(id), channel2.remoteAddress(), dnsResponse);
                            }
                            DnsQueryContext dnsQueryContext = DnsNameResolver.this.queryContextManager.get(C73911.this.val$res.sender(), id);
                            if (dnsQueryContext == null) {
                                DnsNameResolver.access$000().warn("{} Received a DNS response with an unknown ID: {}", channel2, Integer.valueOf(id));
                                C73911.this.val$qCtx.finish(C73911.this.val$res);
                            } else {
                                C73911.this.val$res.release();
                                dnsQueryContext.finish(new AddressedEnvelopeAdapter((InetSocketAddress) channelHandlerContext.channel().remoteAddress(), (InetSocketAddress) channelHandlerContext.channel().localAddress(), dnsResponse));
                                this.finish = true;
                            }
                        } finally {
                            ReferenceCountUtil.release(obj);
                        }
                    }

                    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
                    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
                        if (this.finish) {
                            return;
                        }
                        if (DnsNameResolver.access$000().isDebugEnabled()) {
                            DnsNameResolver.access$000().debug("{} Error during processing response: TCP [{}: {}]", channelHandlerContext.channel(), Integer.valueOf(C73911.this.val$queryId), channelHandlerContext.channel().remoteAddress(), th);
                        }
                        C73911.this.val$qCtx.finish(C73911.this.val$res);
                    }
                });
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes5.dex
         */
        /* renamed from: io.netty.resolver.dns.DnsNameResolver$DnsResponseHandler$2 */
        /* loaded from: classes8.dex */
        class C73922 implements ChannelFutureListener {
            final /* synthetic */ DnsQueryContext val$qCtx;
            final /* synthetic */ int val$queryId;
            final /* synthetic */ DatagramDnsResponse val$res;

            C73922(DnsQueryContext dnsQueryContext, DatagramDnsResponse datagramDnsResponse, int i) {
                this.val$qCtx = dnsQueryContext;
                this.val$res = datagramDnsResponse;
                this.val$queryId = i;
            }

            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) {
                if (channelFuture.isSuccess()) {
                    final Channel channel = channelFuture.channel();
                    Promise newPromise = channel.eventLoop().newPromise();
                    new TcpDnsQueryContext(DnsNameResolver.this, channel, (InetSocketAddress) channel.remoteAddress(), this.val$qCtx.question(), DnsNameResolver.access$900(), newPromise).query(true, channelFuture.channel().newPromise());
                    newPromise.addListener((GenericFutureListener) new FutureListener<AddressedEnvelope<DnsResponse, InetSocketAddress>>() { // from class: io.netty.resolver.dns.DnsNameResolver.DnsResponseHandler.2.1
                        @Override // io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> future) {
                            channel.close();
                            if (future.isSuccess()) {
                                C73922.this.val$qCtx.finish(future.getNow());
                            } else {
                                C73922.this.val$qCtx.finish(C73922.this.val$res);
                            }
                        }
                    });
                    return;
                }
                if (DnsNameResolver.access$000().isDebugEnabled()) {
                    DnsNameResolver.access$000().debug("{} Unable to fallback to TCP [{}]", Integer.valueOf(this.val$queryId), channelFuture.cause());
                }
                this.val$qCtx.finish(this.val$res);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: io.netty.resolver.dns.DnsNameResolver$6 */
    /* loaded from: classes8.dex */
    class RunnableC73896 implements Runnable {
        final /* synthetic */ DnsRecord[] val$additionals;
        final /* synthetic */ boolean val$completeEarlyIfPossible;
        final /* synthetic */ String val$hostname;
        final /* synthetic */ Promise val$promise;
        final /* synthetic */ DnsCache val$resolveCache;

        RunnableC73896(String str, DnsRecord[] dnsRecordArr, Promise promise, DnsCache dnsCache, boolean z) {
            this.val$hostname = str;
            this.val$additionals = dnsRecordArr;
            this.val$promise = promise;
            this.val$resolveCache = dnsCache;
            this.val$completeEarlyIfPossible = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            DnsNameResolver.access$600(DnsNameResolver.this, this.val$hostname, this.val$additionals, this.val$promise, this.val$resolveCache, this.val$completeEarlyIfPossible);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private final class AddressedEnvelopeAdapter implements AddressedEnvelope<DnsResponse, InetSocketAddress> {
        private final InetSocketAddress recipient;
        private final DnsResponse response;
        private final InetSocketAddress sender;

        AddressedEnvelopeAdapter(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, DnsResponse dnsResponse) {
            this.sender = inetSocketAddress;
            this.recipient = inetSocketAddress2;
            this.response = dnsResponse;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.netty.channel.AddressedEnvelope
        public DnsResponse content() {
            return this.response;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.netty.channel.AddressedEnvelope
        public InetSocketAddress sender() {
            return this.sender;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.netty.channel.AddressedEnvelope
        public InetSocketAddress recipient() {
            return this.recipient;
        }

        @Override // io.netty.util.ReferenceCounted
        public AddressedEnvelope<DnsResponse, InetSocketAddress> retain() {
            this.response.retain();
            return this;
        }

        @Override // io.netty.util.ReferenceCounted
        public AddressedEnvelope<DnsResponse, InetSocketAddress> retain(int i) {
            this.response.retain(i);
            return this;
        }

        @Override // io.netty.util.ReferenceCounted
        public AddressedEnvelope<DnsResponse, InetSocketAddress> touch() {
            this.response.touch();
            return this;
        }

        @Override // io.netty.util.ReferenceCounted
        public AddressedEnvelope<DnsResponse, InetSocketAddress> touch(Object obj) {
            this.response.touch(obj);
            return this;
        }

        @Override // io.netty.util.ReferenceCounted
        public int refCnt() {
            return this.response.refCnt();
        }

        @Override // io.netty.util.ReferenceCounted
        public boolean release() {
            return this.response.release();
        }

        @Override // io.netty.util.ReferenceCounted
        public boolean release(int i) {
            return this.response.release(i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AddressedEnvelope)) {
                return false;
            }
            AddressedEnvelope addressedEnvelope = (AddressedEnvelope) obj;
            if (sender() == null) {
                if (addressedEnvelope.sender() != null) {
                    return false;
                }
            } else if (!sender().equals(addressedEnvelope.sender())) {
                return false;
            }
            if (recipient() == null) {
                if (addressedEnvelope.recipient() != null) {
                    return false;
                }
            } else if (!recipient().equals(addressedEnvelope.recipient())) {
                return false;
            }
            return this.response.equals(obj);
        }

        public int hashCode() {
            int hashCode = this.response.hashCode();
            if (sender() != null) {
                hashCode = (hashCode * 31) + sender().hashCode();
            }
            return recipient() != null ? (hashCode * 31) + recipient().hashCode() : hashCode;
        }
    }
}
