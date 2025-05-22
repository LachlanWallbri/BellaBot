package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ServiceProviders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class LoadBalancerRegistry {
    private static LoadBalancerRegistry instance;
    private final LinkedHashSet<LoadBalancerProvider> allProviders = new LinkedHashSet<>();
    private final LinkedHashMap<String, LoadBalancerProvider> effectiveProviders = new LinkedHashMap<>();
    private static final Logger logger = Logger.getLogger(LoadBalancerRegistry.class.getName());
    private static final Iterable<Class<?>> HARDCODED_CLASSES = getHardCodedClasses();

    public synchronized void register(LoadBalancerProvider loadBalancerProvider) {
        addProvider(loadBalancerProvider);
        refreshProviderMap();
    }

    private synchronized void addProvider(LoadBalancerProvider loadBalancerProvider) {
        Preconditions.checkArgument(loadBalancerProvider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(loadBalancerProvider);
    }

    public synchronized void deregister(LoadBalancerProvider loadBalancerProvider) {
        this.allProviders.remove(loadBalancerProvider);
        refreshProviderMap();
    }

    private synchronized void refreshProviderMap() {
        this.effectiveProviders.clear();
        Iterator<LoadBalancerProvider> it = this.allProviders.iterator();
        while (it.hasNext()) {
            LoadBalancerProvider next = it.next();
            String policyName = next.getPolicyName();
            LoadBalancerProvider loadBalancerProvider = this.effectiveProviders.get(policyName);
            if (loadBalancerProvider == null || loadBalancerProvider.getPriority() < next.getPriority()) {
                this.effectiveProviders.put(policyName, next);
            }
        }
    }

    public static synchronized LoadBalancerRegistry getDefaultRegistry() {
        LoadBalancerRegistry loadBalancerRegistry;
        synchronized (LoadBalancerRegistry.class) {
            if (instance == null) {
                List<LoadBalancerProvider> loadAll = ServiceProviders.loadAll(LoadBalancerProvider.class, HARDCODED_CLASSES, LoadBalancerProvider.class.getClassLoader(), new LoadBalancerPriorityAccessor());
                instance = new LoadBalancerRegistry();
                for (LoadBalancerProvider loadBalancerProvider : loadAll) {
                    logger.fine("Service loader found " + loadBalancerProvider);
                    if (loadBalancerProvider.isAvailable()) {
                        instance.addProvider(loadBalancerProvider);
                    }
                }
                instance.refreshProviderMap();
            }
            loadBalancerRegistry = instance;
        }
        return loadBalancerRegistry;
    }

    @Nullable
    public synchronized LoadBalancerProvider getProvider(String str) {
        return this.effectiveProviders.get(Preconditions.checkNotNull(str, "policy"));
    }

    synchronized Map<String, LoadBalancerProvider> providers() {
        return new LinkedHashMap(this.effectiveProviders);
    }

    static List<Class<?>> getHardCodedClasses() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("io.grpc.internal.PickFirstLoadBalancerProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "Unable to find pick-first LoadBalancer", (Throwable) e);
        }
        try {
            arrayList.add(Class.forName("io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider"));
        } catch (ClassNotFoundException e2) {
            logger.log(Level.FINE, "Unable to find round-robin LoadBalancer", (Throwable) e2);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class LoadBalancerPriorityAccessor implements ServiceProviders.PriorityAccessor<LoadBalancerProvider> {
        LoadBalancerPriorityAccessor() {
        }

        @Override // io.grpc.ServiceProviders.PriorityAccessor
        public boolean isAvailable(LoadBalancerProvider loadBalancerProvider) {
            return loadBalancerProvider.isAvailable();
        }

        @Override // io.grpc.ServiceProviders.PriorityAccessor
        public int getPriority(LoadBalancerProvider loadBalancerProvider) {
            return loadBalancerProvider.getPriority();
        }
    }
}
