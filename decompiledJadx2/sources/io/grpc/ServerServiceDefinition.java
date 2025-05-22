package io.grpc;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ServerServiceDefinition {
    private final Map<String, ServerMethodDefinition<?, ?>> methods;
    private final ServiceDescriptor serviceDescriptor;

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public static Builder builder(ServiceDescriptor serviceDescriptor) {
        return new Builder(serviceDescriptor);
    }

    private ServerServiceDefinition(ServiceDescriptor serviceDescriptor, Map<String, ServerMethodDefinition<?, ?>> map) {
        this.serviceDescriptor = (ServiceDescriptor) Preconditions.checkNotNull(serviceDescriptor, "serviceDescriptor");
        this.methods = Collections.unmodifiableMap(new HashMap(map));
    }

    public ServiceDescriptor getServiceDescriptor() {
        return this.serviceDescriptor;
    }

    public Collection<ServerMethodDefinition<?, ?>> getMethods() {
        return this.methods.values();
    }

    public ServerMethodDefinition<?, ?> getMethod(String str) {
        return this.methods.get(str);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {
        private final Map<String, ServerMethodDefinition<?, ?>> methods;
        private final ServiceDescriptor serviceDescriptor;
        private final String serviceName;

        private Builder(String str) {
            this.methods = new HashMap();
            this.serviceName = (String) Preconditions.checkNotNull(str, "serviceName");
            this.serviceDescriptor = null;
        }

        private Builder(ServiceDescriptor serviceDescriptor) {
            this.methods = new HashMap();
            this.serviceDescriptor = (ServiceDescriptor) Preconditions.checkNotNull(serviceDescriptor, "serviceDescriptor");
            this.serviceName = serviceDescriptor.getName();
        }

        public <ReqT, RespT> Builder addMethod(MethodDescriptor<ReqT, RespT> methodDescriptor, ServerCallHandler<ReqT, RespT> serverCallHandler) {
            return addMethod(ServerMethodDefinition.create((MethodDescriptor) Preconditions.checkNotNull(methodDescriptor, "method must not be null"), (ServerCallHandler) Preconditions.checkNotNull(serverCallHandler, "handler must not be null")));
        }

        public <ReqT, RespT> Builder addMethod(ServerMethodDefinition<ReqT, RespT> serverMethodDefinition) {
            MethodDescriptor<ReqT, RespT> methodDescriptor = serverMethodDefinition.getMethodDescriptor();
            Preconditions.checkArgument(this.serviceName.equals(methodDescriptor.getServiceName()), "Method name should be prefixed with service name and separated with '/'. Expected service name: '%s'. Actual fully qualifed method name: '%s'.", this.serviceName, methodDescriptor.getFullMethodName());
            String fullMethodName = methodDescriptor.getFullMethodName();
            Preconditions.checkState(!this.methods.containsKey(fullMethodName), "Method by same name already registered: %s", fullMethodName);
            this.methods.put(fullMethodName, serverMethodDefinition);
            return this;
        }

        public ServerServiceDefinition build() {
            ServiceDescriptor serviceDescriptor = this.serviceDescriptor;
            if (serviceDescriptor == null) {
                ArrayList arrayList = new ArrayList(this.methods.size());
                Iterator<ServerMethodDefinition<?, ?>> it = this.methods.values().iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getMethodDescriptor());
                }
                serviceDescriptor = new ServiceDescriptor(this.serviceName, arrayList);
            }
            HashMap hashMap = new HashMap(this.methods);
            for (MethodDescriptor<?, ?> methodDescriptor : serviceDescriptor.getMethods()) {
                ServerMethodDefinition serverMethodDefinition = (ServerMethodDefinition) hashMap.remove(methodDescriptor.getFullMethodName());
                if (serverMethodDefinition == null) {
                    throw new IllegalStateException("No method bound for descriptor entry " + methodDescriptor.getFullMethodName());
                }
                if (serverMethodDefinition.getMethodDescriptor() != methodDescriptor) {
                    throw new IllegalStateException("Bound method for " + methodDescriptor.getFullMethodName() + " not same instance as method in service descriptor");
                }
            }
            if (hashMap.size() > 0) {
                throw new IllegalStateException("No entry in descriptor matching bound method " + ((ServerMethodDefinition) hashMap.values().iterator().next()).getMethodDescriptor().getFullMethodName());
            }
            return new ServerServiceDefinition(serviceDescriptor, this.methods);
        }
    }
}
