package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ServiceDescriptor {
    private final Collection<MethodDescriptor<?, ?>> methods;
    private final String name;
    private final Object schemaDescriptor;

    public ServiceDescriptor(String str, MethodDescriptor<?, ?>... methodDescriptorArr) {
        this(str, Arrays.asList(methodDescriptorArr));
    }

    public ServiceDescriptor(String str, Collection<MethodDescriptor<?, ?>> collection) {
        this(newBuilder(str).addAllMethods((Collection) Preconditions.checkNotNull(collection, "methods")));
    }

    private ServiceDescriptor(Builder builder) {
        this.name = builder.name;
        validateMethodNames(this.name, builder.methods);
        this.methods = Collections.unmodifiableList(new ArrayList(builder.methods));
        this.schemaDescriptor = builder.schemaDescriptor;
    }

    public String getName() {
        return this.name;
    }

    public Collection<MethodDescriptor<?, ?>> getMethods() {
        return this.methods;
    }

    @Nullable
    public Object getSchemaDescriptor() {
        return this.schemaDescriptor;
    }

    static void validateMethodNames(String str, Collection<MethodDescriptor<?, ?>> collection) {
        HashSet hashSet = new HashSet(collection.size());
        for (MethodDescriptor<?, ?> methodDescriptor : collection) {
            Preconditions.checkNotNull(methodDescriptor, "method");
            String serviceName = methodDescriptor.getServiceName();
            Preconditions.checkArgument(str.equals(serviceName), "service names %s != %s", serviceName, str);
            Preconditions.checkArgument(hashSet.add(methodDescriptor.getFullMethodName()), "duplicate name %s", methodDescriptor.getFullMethodName());
        }
    }

    public static Builder newBuilder(String str) {
        return new Builder(str);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {
        private List<MethodDescriptor<?, ?>> methods;
        private String name;
        private Object schemaDescriptor;

        private Builder(String str) {
            this.methods = new ArrayList();
            setName(str);
        }

        public Builder setName(String str) {
            this.name = (String) Preconditions.checkNotNull(str, "name");
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder addMethod(MethodDescriptor<?, ?> methodDescriptor) {
            this.methods.add(Preconditions.checkNotNull(methodDescriptor, "method"));
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder addAllMethods(Collection<MethodDescriptor<?, ?>> collection) {
            this.methods.addAll(collection);
            return this;
        }

        public Builder setSchemaDescriptor(@Nullable Object obj) {
            this.schemaDescriptor = obj;
            return this;
        }

        public ServiceDescriptor build() {
            return new ServiceDescriptor(this);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.name).add("schemaDescriptor", this.schemaDescriptor).add("methods", this.methods).omitNullValues().toString();
    }
}
