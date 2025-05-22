package io.grpc.internal;

import io.grpc.HandlerRegistry;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class InternalHandlerRegistry extends HandlerRegistry {
    private final Map<String, ServerMethodDefinition<?, ?>> methods;
    private final List<ServerServiceDefinition> services;

    private InternalHandlerRegistry(List<ServerServiceDefinition> list, Map<String, ServerMethodDefinition<?, ?>> map) {
        this.services = list;
        this.methods = map;
    }

    @Override // io.grpc.HandlerRegistry
    public List<ServerServiceDefinition> getServices() {
        return this.services;
    }

    @Override // io.grpc.HandlerRegistry
    @Nullable
    public ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2) {
        return this.methods.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {
        private final HashMap<String, ServerServiceDefinition> services = new LinkedHashMap();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder addService(ServerServiceDefinition serverServiceDefinition) {
            this.services.put(serverServiceDefinition.getServiceDescriptor().getName(), serverServiceDefinition);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public InternalHandlerRegistry build() {
            HashMap hashMap = new HashMap();
            Iterator<ServerServiceDefinition> it = this.services.values().iterator();
            while (it.hasNext()) {
                for (ServerMethodDefinition<?, ?> serverMethodDefinition : it.next().getMethods()) {
                    hashMap.put(serverMethodDefinition.getMethodDescriptor().getFullMethodName(), serverMethodDefinition);
                }
            }
            return new InternalHandlerRegistry(Collections.unmodifiableList(new ArrayList(this.services.values())), Collections.unmodifiableMap(hashMap));
        }
    }
}
