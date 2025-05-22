package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ServerProvider;
import io.grpc.ServiceProviders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ServerRegistry {
    private static ServerRegistry instance;
    private static final Logger logger = Logger.getLogger(ServerRegistry.class.getName());
    private final LinkedHashSet<ServerProvider> allProviders = new LinkedHashSet<>();
    private List<ServerProvider> effectiveProviders = Collections.emptyList();

    public synchronized void register(ServerProvider serverProvider) {
        addProvider(serverProvider);
        refreshProviders();
    }

    private synchronized void addProvider(ServerProvider serverProvider) {
        Preconditions.checkArgument(serverProvider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(serverProvider);
    }

    public synchronized void deregister(ServerProvider serverProvider) {
        this.allProviders.remove(serverProvider);
        refreshProviders();
    }

    private synchronized void refreshProviders() {
        ArrayList arrayList = new ArrayList(this.allProviders);
        Collections.sort(arrayList, Collections.reverseOrder(new Comparator<ServerProvider>() { // from class: io.grpc.ServerRegistry.1
            @Override // java.util.Comparator
            public int compare(ServerProvider serverProvider, ServerProvider serverProvider2) {
                return serverProvider.priority() - serverProvider2.priority();
            }
        }));
        this.effectiveProviders = Collections.unmodifiableList(arrayList);
    }

    public static synchronized ServerRegistry getDefaultRegistry() {
        ServerRegistry serverRegistry;
        synchronized (ServerRegistry.class) {
            if (instance == null) {
                List<ServerProvider> loadAll = ServiceProviders.loadAll(ServerProvider.class, Collections.emptyList(), ServerProvider.class.getClassLoader(), new ServerPriorityAccessor());
                instance = new ServerRegistry();
                for (ServerProvider serverProvider : loadAll) {
                    logger.fine("Service loader found " + serverProvider);
                    if (serverProvider.isAvailable()) {
                        instance.addProvider(serverProvider);
                    }
                }
                instance.refreshProviders();
            }
            serverRegistry = instance;
        }
        return serverRegistry;
    }

    synchronized List<ServerProvider> providers() {
        return this.effectiveProviders;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServerProvider provider() {
        List<ServerProvider> providers = providers();
        if (providers.isEmpty()) {
            return null;
        }
        return providers.get(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServerBuilder<?> newServerBuilderForPort(int i, ServerCredentials serverCredentials) {
        if (providers().isEmpty()) {
            throw new ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty or grpc-netty-shaded artifact");
        }
        StringBuilder sb = new StringBuilder();
        for (ServerProvider serverProvider : providers()) {
            ServerProvider.NewServerBuilderResult newServerBuilderForPort = serverProvider.newServerBuilderForPort(i, serverCredentials);
            if (newServerBuilderForPort.getServerBuilder() != null) {
                return newServerBuilderForPort.getServerBuilder();
            }
            sb.append("; ");
            sb.append(serverProvider.getClass().getName());
            sb.append(": ");
            sb.append(newServerBuilderForPort.getError());
        }
        throw new ProviderNotFoundException(sb.substring(2));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class ServerPriorityAccessor implements ServiceProviders.PriorityAccessor<ServerProvider> {
        private ServerPriorityAccessor() {
        }

        @Override // io.grpc.ServiceProviders.PriorityAccessor
        public boolean isAvailable(ServerProvider serverProvider) {
            return serverProvider.isAvailable();
        }

        @Override // io.grpc.ServiceProviders.PriorityAccessor
        public int getPriority(ServerProvider serverProvider) {
            return serverProvider.priority();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String str) {
            super(str);
        }
    }
}
