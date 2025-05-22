package com.google.api.pathtemplate;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class TemplatedResourceName implements Map<String, String> {
    private static volatile Resolver resourceNameResolver = new Resolver() { // from class: com.google.api.pathtemplate.TemplatedResourceName.1
        @Override // com.google.api.pathtemplate.TemplatedResourceName.Resolver
        public <T> T resolve(Class<T> cls, TemplatedResourceName templatedResourceName, String str) {
            throw new IllegalStateException("No resource name resolver is registered in ResourceName class.");
        }
    };
    private final String endpoint;
    private volatile String stringRepr;
    private final PathTemplate template;
    private final ImmutableMap<String, String> values;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface Resolver {
        <T> T resolve(Class<T> cls, TemplatedResourceName templatedResourceName, @Nullable String str);
    }

    public static void registerResourceNameResolver(Resolver resolver) {
        resourceNameResolver = resolver;
    }

    public static TemplatedResourceName create(PathTemplate pathTemplate, String str) {
        Map<String, String> match = pathTemplate.match(str);
        if (match == null) {
            throw new ValidationException("path '%s' does not match template '%s'", str, pathTemplate);
        }
        return new TemplatedResourceName(pathTemplate, match, null);
    }

    public static TemplatedResourceName create(PathTemplate pathTemplate, Map<String, String> map) {
        if (!map.keySet().containsAll(pathTemplate.vars())) {
            LinkedHashSet newLinkedHashSet = Sets.newLinkedHashSet(pathTemplate.vars());
            newLinkedHashSet.removeAll(map.keySet());
            throw new ValidationException("unbound variables: %s", newLinkedHashSet);
        }
        return new TemplatedResourceName(pathTemplate, map, null);
    }

    @Nullable
    public static TemplatedResourceName createFromFullName(PathTemplate pathTemplate, String str) {
        Map<String, String> matchFromFullName = pathTemplate.matchFromFullName(str);
        if (matchFromFullName == null) {
            return null;
        }
        return new TemplatedResourceName(pathTemplate, matchFromFullName, null);
    }

    private TemplatedResourceName(PathTemplate pathTemplate, Map<String, String> map, String str) {
        this.template = pathTemplate;
        this.values = ImmutableMap.copyOf((Map) map);
        this.endpoint = str;
    }

    public String toString() {
        if (this.stringRepr == null) {
            this.stringRepr = this.template.instantiate(this.values);
        }
        return this.stringRepr;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (!(obj instanceof TemplatedResourceName)) {
            return false;
        }
        TemplatedResourceName templatedResourceName = (TemplatedResourceName) obj;
        return Objects.equals(this.template, templatedResourceName.template) && Objects.equals(this.endpoint, templatedResourceName.endpoint) && Objects.equals(this.values, templatedResourceName.values);
    }

    @Override // java.util.Map
    public int hashCode() {
        return Objects.hash(this.template, this.endpoint, this.values);
    }

    public PathTemplate template() {
        return this.template;
    }

    public boolean hasEndpoint() {
        return this.endpoint != null;
    }

    @Nullable
    public String endpoint() {
        return this.endpoint;
    }

    public TemplatedResourceName withEndpoint(String str) {
        return new TemplatedResourceName(this.template, this.values, (String) Preconditions.checkNotNull(str));
    }

    public TemplatedResourceName parentName() {
        return new TemplatedResourceName(this.template.parentTemplate(), this.values, this.endpoint);
    }

    public boolean startsWith(TemplatedResourceName templatedResourceName) {
        return toString().startsWith(templatedResourceName.toString());
    }

    public <T> T resolve(Class<T> cls, @Nullable String str) {
        Preconditions.checkArgument(hasEndpoint(), "Resource name must have an endpoint.");
        return (T) resourceNameResolver.resolve(cls, this, str);
    }

    @Override // java.util.Map
    public int size() {
        return this.values.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.values.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.values.containsValue(obj);
    }

    @Override // java.util.Map
    public String get(Object obj) {
        return this.values.get(obj);
    }

    @Override // java.util.Map
    @Deprecated
    public String put(String str, String str2) {
        return this.values.put(str, str2);
    }

    @Override // java.util.Map
    @Deprecated
    public String remove(Object obj) {
        return this.values.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    public void putAll(Map<? extends String, ? extends String> map) {
        this.values.putAll(map);
    }

    @Override // java.util.Map
    @Deprecated
    public void clear() {
        this.values.clear();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.values.keySet();
    }

    @Override // java.util.Map
    public Collection<String> values() {
        return this.values.values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, String>> entrySet() {
        return this.values.entrySet();
    }
}
