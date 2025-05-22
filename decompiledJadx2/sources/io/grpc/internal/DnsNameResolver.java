package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.ProxiedSocketAddress;
import io.grpc.ProxyDetector;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.SharedResourceHolder;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DnsNameResolver extends NameResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long DEFAULT_NETWORK_CACHE_TTL_SECONDS = 30;
    static final String NETWORKADDRESS_CACHE_TTL_PROPERTY = "networkaddress.cache.ttl";
    private static final String SERVICE_CONFIG_NAME_PREFIX = "_grpc_config.";
    static final String SERVICE_CONFIG_PREFIX = "grpc_config=";
    private static String localHostname;
    private final String authority;
    private final long cacheTtlNanos;
    private Executor executor;
    private final SharedResourceHolder.Resource<Executor> executorResource;
    private final String host;
    private NameResolver.Listener2 listener;
    private final int port;
    final ProxyDetector proxyDetector;
    protected boolean resolved;
    private boolean resolving;
    private final NameResolver.ServiceConfigParser serviceConfigParser;
    private boolean shutdown;
    private final Stopwatch stopwatch;
    private final SynchronizationContext syncContext;
    private final boolean usingExecutorResource;
    private static final Logger logger = Logger.getLogger(DnsNameResolver.class.getName());
    private static final String SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY = "clientLanguage";
    private static final String SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY = "percentage";
    private static final String SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY = "clientHostname";
    private static final String SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY = "serviceConfig";
    private static final Set<String> SERVICE_CONFIG_CHOICE_KEYS = Collections.unmodifiableSet(new HashSet(Arrays.asList(SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY, SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY, SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY)));
    private static final String JNDI_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi", "true");
    private static final String JNDI_LOCALHOST_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi_localhost", "false");
    private static final String JNDI_TXT_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_service_config", "false");
    static boolean enableJndi = Boolean.parseBoolean(JNDI_PROPERTY);
    static boolean enableJndiLocalhost = Boolean.parseBoolean(JNDI_LOCALHOST_PROPERTY);
    protected static boolean enableTxt = Boolean.parseBoolean(JNDI_TXT_PROPERTY);
    private static final ResourceResolverFactory resourceResolverFactory = getResourceResolverFactory(DnsNameResolver.class.getClassLoader());
    private final Random random = new Random();
    protected volatile AddressResolver addressResolver = JdkAddressResolver.INSTANCE;
    private final AtomicReference<ResourceResolver> resourceResolver = new AtomicReference<>();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface AddressResolver {
        List<InetAddress> resolveAddress(String str) throws Exception;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface ResourceResolver {
        List<SrvRecord> resolveSrv(String str) throws Exception;

        List<String> resolveTxt(String str) throws Exception;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface ResourceResolverFactory {
        @Nullable
        ResourceResolver newResourceResolver();

        @Nullable
        Throwable unavailabilityCause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DnsNameResolver(@Nullable String str, String str2, NameResolver.Args args, SharedResourceHolder.Resource<Executor> resource, Stopwatch stopwatch, boolean z) {
        Preconditions.checkNotNull(args, "args");
        this.executorResource = resource;
        URI create = URI.create("//" + ((String) Preconditions.checkNotNull(str2, "name")));
        Preconditions.checkArgument(create.getHost() != null, "Invalid DNS name: %s", str2);
        this.authority = (String) Preconditions.checkNotNull(create.getAuthority(), "nameUri (%s) doesn't have an authority", create);
        this.host = create.getHost();
        if (create.getPort() == -1) {
            this.port = args.getDefaultPort();
        } else {
            this.port = create.getPort();
        }
        this.proxyDetector = (ProxyDetector) Preconditions.checkNotNull(args.getProxyDetector(), "proxyDetector");
        this.cacheTtlNanos = getNetworkAddressCacheTtlNanos(z);
        this.stopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, "stopwatch");
        this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(args.getSynchronizationContext(), "syncContext");
        this.executor = args.getOffloadExecutor();
        this.usingExecutorResource = this.executor == null;
        this.serviceConfigParser = (NameResolver.ServiceConfigParser) Preconditions.checkNotNull(args.getServiceConfigParser(), "serviceConfigParser");
    }

    @Override // io.grpc.NameResolver
    public String getServiceAuthority() {
        return this.authority;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getHost() {
        return this.host;
    }

    @Override // io.grpc.NameResolver
    public void start(NameResolver.Listener2 listener2) {
        Preconditions.checkState(this.listener == null, "already started");
        if (this.usingExecutorResource) {
            this.executor = (Executor) SharedResourceHolder.get(this.executorResource);
        }
        this.listener = (NameResolver.Listener2) Preconditions.checkNotNull(listener2, "listener");
        resolve();
    }

    @Override // io.grpc.NameResolver
    public void refresh() {
        Preconditions.checkState(this.listener != null, "not started");
        resolve();
    }

    private List<EquivalentAddressGroup> resolveAddresses() {
        Exception e = null;
        try {
            try {
                List<InetAddress> resolveAddress = this.addressResolver.resolveAddress(this.host);
                ArrayList arrayList = new ArrayList(resolveAddress.size());
                Iterator<InetAddress> it = resolveAddress.iterator();
                while (it.hasNext()) {
                    arrayList.add(new EquivalentAddressGroup(new InetSocketAddress(it.next(), this.port)));
                }
                return Collections.unmodifiableList(arrayList);
            } catch (Exception e2) {
                e = e2;
                Throwables.throwIfUnchecked(e);
                throw new RuntimeException(e);
            }
        } catch (Throwable th) {
            if (e != null) {
                logger.log(Level.FINE, "Address resolution failure", (Throwable) e);
            }
            throw th;
        }
    }

    @Nullable
    private NameResolver.ConfigOrError resolveServiceConfig() {
        List<String> emptyList = Collections.emptyList();
        ResourceResolver resourceResolver = getResourceResolver();
        if (resourceResolver != null) {
            try {
                emptyList = resourceResolver.resolveTxt(SERVICE_CONFIG_NAME_PREFIX + this.host);
            } catch (Exception e) {
                logger.log(Level.FINE, "ServiceConfig resolution failure", (Throwable) e);
            }
        }
        if (!emptyList.isEmpty()) {
            NameResolver.ConfigOrError parseServiceConfig = parseServiceConfig(emptyList, this.random, getLocalHostname());
            if (parseServiceConfig == null) {
                return null;
            }
            if (parseServiceConfig.getError() != null) {
                return NameResolver.ConfigOrError.fromError(parseServiceConfig.getError());
            }
            return this.serviceConfigParser.parseServiceConfig((Map) parseServiceConfig.getConfig());
        }
        logger.log(Level.FINE, "No TXT records found for {0}", new Object[]{this.host});
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public EquivalentAddressGroup detectProxy() throws IOException {
        ProxiedSocketAddress proxyFor = this.proxyDetector.proxyFor(InetSocketAddress.createUnresolved(this.host, this.port));
        if (proxyFor != null) {
            return new EquivalentAddressGroup(proxyFor);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public InternalResolutionResult doResolve(boolean z) {
        InternalResolutionResult internalResolutionResult = new InternalResolutionResult();
        try {
            internalResolutionResult.addresses = resolveAddresses();
        } catch (Exception e) {
            if (!z) {
                internalResolutionResult.error = Status.UNAVAILABLE.withDescription("Unable to resolve host " + this.host).withCause(e);
                return internalResolutionResult;
            }
        }
        if (enableTxt) {
            internalResolutionResult.config = resolveServiceConfig();
        }
        return internalResolutionResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class Resolve implements Runnable {
        private final NameResolver.Listener2 savedListener;

        Resolve(NameResolver.Listener2 listener2) {
            this.savedListener = (NameResolver.Listener2) Preconditions.checkNotNull(listener2, "savedListener");
        }

        @Override // java.lang.Runnable
        public void run() {
            SynchronizationContext synchronizationContext;
            Runnable runnable;
            if (DnsNameResolver.logger.isLoggable(Level.FINER)) {
                DnsNameResolver.logger.finer("Attempting DNS resolution of " + DnsNameResolver.this.host);
            }
            InternalResolutionResult internalResolutionResult = null;
            try {
                try {
                    EquivalentAddressGroup detectProxy = DnsNameResolver.this.detectProxy();
                    NameResolver.ResolutionResult.Builder newBuilder = NameResolver.ResolutionResult.newBuilder();
                    if (detectProxy != null) {
                        if (DnsNameResolver.logger.isLoggable(Level.FINER)) {
                            DnsNameResolver.logger.finer("Using proxy address " + detectProxy);
                        }
                        newBuilder.setAddresses(Collections.singletonList(detectProxy));
                    } else {
                        internalResolutionResult = DnsNameResolver.this.doResolve(false);
                        if (internalResolutionResult.error != null) {
                            this.savedListener.onError(internalResolutionResult.error);
                            return;
                        }
                        if (internalResolutionResult.addresses != null) {
                            newBuilder.setAddresses(internalResolutionResult.addresses);
                        }
                        if (internalResolutionResult.config != null) {
                            newBuilder.setServiceConfig(internalResolutionResult.config);
                        }
                        if (internalResolutionResult.attributes != null) {
                            newBuilder.setAttributes(internalResolutionResult.attributes);
                        }
                    }
                    this.savedListener.onResult(newBuilder.build());
                    r1 = internalResolutionResult != null && internalResolutionResult.error == null;
                    synchronizationContext = DnsNameResolver.this.syncContext;
                    runnable = new Runnable() { // from class: io.grpc.internal.DnsNameResolver.Resolve.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (r2) {
                                DnsNameResolver.this.resolved = true;
                                if (DnsNameResolver.this.cacheTtlNanos > 0) {
                                    DnsNameResolver.this.stopwatch.reset().start();
                                }
                            }
                            DnsNameResolver.this.resolving = false;
                        }
                    };
                } catch (IOException e) {
                    this.savedListener.onError(Status.UNAVAILABLE.withDescription("Unable to resolve host " + DnsNameResolver.this.host).withCause(e));
                    r1 = 0 != 0 && null.error == null;
                    synchronizationContext = DnsNameResolver.this.syncContext;
                    runnable = new Runnable() { // from class: io.grpc.internal.DnsNameResolver.Resolve.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (r2) {
                                DnsNameResolver.this.resolved = true;
                                if (DnsNameResolver.this.cacheTtlNanos > 0) {
                                    DnsNameResolver.this.stopwatch.reset().start();
                                }
                            }
                            DnsNameResolver.this.resolving = false;
                        }
                    };
                }
                synchronizationContext.execute(runnable);
            } finally {
                r1 = 0 != 0 && null.error == null;
                DnsNameResolver.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.DnsNameResolver.Resolve.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (r2) {
                            DnsNameResolver.this.resolved = true;
                            if (DnsNameResolver.this.cacheTtlNanos > 0) {
                                DnsNameResolver.this.stopwatch.reset().start();
                            }
                        }
                        DnsNameResolver.this.resolving = false;
                    }
                });
            }
        }
    }

    @Nullable
    static NameResolver.ConfigOrError parseServiceConfig(List<String> list, Random random, String str) {
        try {
            Iterator<Map<String, ?>> it = parseTxtResults(list).iterator();
            Map<String, ?> map = null;
            while (it.hasNext()) {
                try {
                    map = maybeChooseServiceConfig(it.next(), random, str);
                    if (map != null) {
                        break;
                    }
                } catch (RuntimeException e) {
                    return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to pick service config choice").withCause(e));
                }
            }
            if (map == null) {
                return null;
            }
            return NameResolver.ConfigOrError.fromConfig(map);
        } catch (IOException | RuntimeException e2) {
            return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse TXT records").withCause(e2));
        }
    }

    private void resolve() {
        if (this.resolving || this.shutdown || !cacheRefreshRequired()) {
            return;
        }
        this.resolving = true;
        this.executor.execute(new Resolve(this.listener));
    }

    private boolean cacheRefreshRequired() {
        if (this.resolved) {
            long j = this.cacheTtlNanos;
            if (j != 0 && (j <= 0 || this.stopwatch.elapsed(TimeUnit.NANOSECONDS) <= this.cacheTtlNanos)) {
                return false;
            }
        }
        return true;
    }

    @Override // io.grpc.NameResolver
    public void shutdown() {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        Executor executor = this.executor;
        if (executor == null || !this.usingExecutorResource) {
            return;
        }
        this.executor = (Executor) SharedResourceHolder.release(this.executorResource, executor);
    }

    final int getPort() {
        return this.port;
    }

    static List<Map<String, ?>> parseTxtResults(List<String> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.startsWith(SERVICE_CONFIG_PREFIX)) {
                logger.log(Level.FINE, "Ignoring non service config {0}", new Object[]{str});
            } else {
                Object parse = JsonParser.parse(str.substring(12));
                if (!(parse instanceof List)) {
                    throw new ClassCastException("wrong type " + parse);
                }
                arrayList.addAll(JsonUtil.checkObjectList((List) parse));
            }
        }
        return arrayList;
    }

    @Nullable
    private static final Double getPercentageFromChoice(Map<String, ?> map) {
        return JsonUtil.getNumber(map, SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY);
    }

    @Nullable
    private static final List<String> getClientLanguagesFromChoice(Map<String, ?> map) {
        return JsonUtil.getListOfStrings(map, SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY);
    }

    @Nullable
    private static final List<String> getHostnamesFromChoice(Map<String, ?> map) {
        return JsonUtil.getListOfStrings(map, SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY);
    }

    private static long getNetworkAddressCacheTtlNanos(boolean z) {
        if (z) {
            return 0L;
        }
        String property = System.getProperty(NETWORKADDRESS_CACHE_TTL_PROPERTY);
        long j = DEFAULT_NETWORK_CACHE_TTL_SECONDS;
        if (property != null) {
            try {
                j = Long.parseLong(property);
            } catch (NumberFormatException unused) {
                logger.log(Level.WARNING, "Property({0}) valid is not valid number format({1}), fall back to default({2})", new Object[]{NETWORKADDRESS_CACHE_TTL_PROPERTY, property, Long.valueOf(DEFAULT_NETWORK_CACHE_TTL_SECONDS)});
            }
        }
        return j > 0 ? TimeUnit.SECONDS.toNanos(j) : j;
    }

    @Nullable
    static Map<String, ?> maybeChooseServiceConfig(Map<String, ?> map, Random random, String str) {
        boolean z;
        boolean z2;
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            Verify.verify(SERVICE_CONFIG_CHOICE_KEYS.contains(entry.getKey()), "Bad key: %s", entry);
        }
        List<String> clientLanguagesFromChoice = getClientLanguagesFromChoice(map);
        if (clientLanguagesFromChoice != null && !clientLanguagesFromChoice.isEmpty()) {
            Iterator<String> it = clientLanguagesFromChoice.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                if ("java".equalsIgnoreCase(it.next())) {
                    z2 = true;
                    break;
                }
            }
            if (!z2) {
                return null;
            }
        }
        Double percentageFromChoice = getPercentageFromChoice(map);
        if (percentageFromChoice != null) {
            int intValue = percentageFromChoice.intValue();
            Verify.verify(intValue >= 0 && intValue <= 100, "Bad percentage: %s", percentageFromChoice);
            if (random.nextInt(100) >= intValue) {
                return null;
            }
        }
        List<String> hostnamesFromChoice = getHostnamesFromChoice(map);
        if (hostnamesFromChoice != null && !hostnamesFromChoice.isEmpty()) {
            Iterator<String> it2 = hostnamesFromChoice.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                if (it2.next().equals(str)) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return null;
            }
        }
        Map<String, ?> object = JsonUtil.getObject(map, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY);
        if (object != null) {
            return object;
        }
        throw new VerifyException(String.format("key '%s' missing in '%s'", map, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class InternalResolutionResult {
        private List<EquivalentAddressGroup> addresses;
        public Attributes attributes;
        private NameResolver.ConfigOrError config;
        private Status error;

        private InternalResolutionResult() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class SrvRecord {
        public final String host;
        public final int port;

        public SrvRecord(String str, int i) {
            this.host = str;
            this.port = i;
        }

        public int hashCode() {
            return Objects.hashCode(this.host, Integer.valueOf(this.port));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SrvRecord srvRecord = (SrvRecord) obj;
            return this.port == srvRecord.port && this.host.equals(srvRecord.host);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("host", this.host).add("port", this.port).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAddressResolver(AddressResolver addressResolver) {
        this.addressResolver = addressResolver;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setResourceResolver(ResourceResolver resourceResolver) {
        this.resourceResolver.set(resourceResolver);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private enum JdkAddressResolver implements AddressResolver {
        INSTANCE;

        @Override // io.grpc.internal.DnsNameResolver.AddressResolver
        public List<InetAddress> resolveAddress(String str) throws UnknownHostException {
            return Collections.unmodifiableList(Arrays.asList(InetAddress.getAllByName(str)));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public ResourceResolver getResourceResolver() {
        ResourceResolverFactory resourceResolverFactory2;
        if (!shouldUseJndi(enableJndi, enableJndiLocalhost, this.host)) {
            return null;
        }
        ResourceResolver resourceResolver = this.resourceResolver.get();
        return (resourceResolver != null || (resourceResolverFactory2 = resourceResolverFactory) == null) ? resourceResolver : resourceResolverFactory2.newResourceResolver();
    }

    @Nullable
    static ResourceResolverFactory getResourceResolverFactory(ClassLoader classLoader) {
        try {
            try {
                try {
                    ResourceResolverFactory resourceResolverFactory2 = (ResourceResolverFactory) Class.forName("io.grpc.internal.JndiResourceResolverFactory", true, classLoader).asSubclass(ResourceResolverFactory.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (resourceResolverFactory2.unavailabilityCause() == null) {
                        return resourceResolverFactory2;
                    }
                    logger.log(Level.FINE, "JndiResourceResolverFactory not available, skipping.", resourceResolverFactory2.unavailabilityCause());
                    return null;
                } catch (Exception e) {
                    logger.log(Level.FINE, "Can't construct JndiResourceResolverFactory, skipping.", (Throwable) e);
                    return null;
                }
            } catch (Exception e2) {
                logger.log(Level.FINE, "Can't find JndiResourceResolverFactory ctor, skipping.", (Throwable) e2);
                return null;
            }
        } catch (ClassCastException e3) {
            logger.log(Level.FINE, "Unable to cast JndiResourceResolverFactory, skipping.", (Throwable) e3);
            return null;
        } catch (ClassNotFoundException e4) {
            logger.log(Level.FINE, "Unable to find JndiResourceResolverFactory, skipping.", (Throwable) e4);
            return null;
        }
    }

    private static String getLocalHostname() {
        if (localHostname == null) {
            try {
                localHostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return localHostname;
    }

    protected static boolean shouldUseJndi(boolean z, boolean z2, String str) {
        if (!z) {
            return false;
        }
        if ("localhost".equalsIgnoreCase(str)) {
            return z2;
        }
        if (str.contains(":")) {
            return false;
        }
        boolean z3 = true;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != '.') {
                z3 &= charAt >= '0' && charAt <= '9';
            }
        }
        return true ^ z3;
    }
}
