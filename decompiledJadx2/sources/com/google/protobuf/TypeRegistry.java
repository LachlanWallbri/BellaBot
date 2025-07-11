package com.google.protobuf;

import com.google.protobuf.Descriptors;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class TypeRegistry {
    private static final Logger logger = Logger.getLogger(TypeRegistry.class.getName());
    private final Map<String, Descriptors.Descriptor> types;

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    private static class EmptyTypeRegistryHolder {
        private static final TypeRegistry EMPTY = new TypeRegistry(Collections.emptyMap());

        private EmptyTypeRegistryHolder() {
        }
    }

    public static TypeRegistry getEmptyTypeRegistry() {
        return EmptyTypeRegistryHolder.EMPTY;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Descriptors.Descriptor find(String str) {
        return this.types.get(str);
    }

    public final Descriptors.Descriptor getDescriptorForTypeUrl(String str) throws InvalidProtocolBufferException {
        return find(getTypeName(str));
    }

    TypeRegistry(Map<String, Descriptors.Descriptor> map) {
        this.types = map;
    }

    private static String getTypeName(String str) throws InvalidProtocolBufferException {
        String[] split = str.split("/");
        if (split.length == 1) {
            throw new InvalidProtocolBufferException("Invalid type url found: " + str);
        }
        return split[split.length - 1];
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public static final class Builder {
        private final Set<String> files;
        private Map<String, Descriptors.Descriptor> types;

        private Builder() {
            this.files = new HashSet();
            this.types = new HashMap();
        }

        public Builder add(Descriptors.Descriptor descriptor) {
            if (this.types == null) {
                throw new IllegalStateException("A TypeRegistry.Builder can only be used once.");
            }
            addFile(descriptor.getFile());
            return this;
        }

        public Builder add(Iterable<Descriptors.Descriptor> iterable) {
            if (this.types == null) {
                throw new IllegalStateException("A TypeRegistry.Builder can only be used once.");
            }
            Iterator<Descriptors.Descriptor> it = iterable.iterator();
            while (it.hasNext()) {
                addFile(it.next().getFile());
            }
            return this;
        }

        public TypeRegistry build() {
            TypeRegistry typeRegistry = new TypeRegistry(this.types);
            this.types = null;
            return typeRegistry;
        }

        private void addFile(Descriptors.FileDescriptor fileDescriptor) {
            if (this.files.add(fileDescriptor.getFullName())) {
                Iterator<Descriptors.FileDescriptor> it = fileDescriptor.getDependencies().iterator();
                while (it.hasNext()) {
                    addFile(it.next());
                }
                Iterator<Descriptors.Descriptor> it2 = fileDescriptor.getMessageTypes().iterator();
                while (it2.hasNext()) {
                    addMessage(it2.next());
                }
            }
        }

        private void addMessage(Descriptors.Descriptor descriptor) {
            Iterator<Descriptors.Descriptor> it = descriptor.getNestedTypes().iterator();
            while (it.hasNext()) {
                addMessage(it.next());
            }
            if (this.types.containsKey(descriptor.getFullName())) {
                TypeRegistry.logger.warning("Type " + descriptor.getFullName() + " is added multiple times.");
                return;
            }
            this.types.put(descriptor.getFullName(), descriptor);
        }
    }
}
