package org.yaml.snakeyaml.introspector;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.yaml.snakeyaml.error.YAMLException;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class PropertyUtils {
    private final Map<Class<?>, Map<String, Property>> propertiesCache = new HashMap();
    private final Map<Class<?>, Set<Property>> readableProperties = new HashMap();
    private BeanAccess beanAccess = BeanAccess.DEFAULT;
    private boolean allowReadOnlyProperties = false;
    private boolean skipMissingProperties = false;

    protected Map<String, Property> getPropertiesMap(Class<?> cls, BeanAccess beanAccess) {
        if (this.propertiesCache.containsKey(cls)) {
            return this.propertiesCache.get(cls);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            for (Field field : cls2.getDeclaredFields()) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers) && !linkedHashMap.containsKey(field.getName())) {
                    linkedHashMap.put(field.getName(), new FieldProperty(field));
                }
            }
        }
        this.propertiesCache.put(cls, linkedHashMap);
        return linkedHashMap;
    }

    public Set<Property> getProperties(Class<? extends Object> cls) {
        return getProperties(cls, this.beanAccess);
    }

    public Set<Property> getProperties(Class<? extends Object> cls, BeanAccess beanAccess) {
        if (this.readableProperties.containsKey(cls)) {
            return this.readableProperties.get(cls);
        }
        Set<Property> createPropertySet = createPropertySet(cls, beanAccess);
        this.readableProperties.put(cls, createPropertySet);
        return createPropertySet;
    }

    protected Set<Property> createPropertySet(Class<? extends Object> cls, BeanAccess beanAccess) {
        TreeSet treeSet = new TreeSet();
        for (Property property : getPropertiesMap(cls, beanAccess).values()) {
            if (property.isReadable() && (this.allowReadOnlyProperties || property.isWritable())) {
                treeSet.add(property);
            }
        }
        return treeSet;
    }

    public Property getProperty(Class<? extends Object> cls, String str) {
        return getProperty(cls, str, this.beanAccess);
    }

    public Property getProperty(Class<? extends Object> cls, String str, BeanAccess beanAccess) {
        Property property = getPropertiesMap(cls, beanAccess).get(str);
        if (property == null && this.skipMissingProperties) {
            property = new MissingProperty(str);
        }
        if (property != null && property.isWritable()) {
            return property;
        }
        throw new YAMLException("Unable to find property '" + str + "' on class: " + cls.getName());
    }

    public void setBeanAccess(BeanAccess beanAccess) {
        if (this.beanAccess != beanAccess) {
            this.beanAccess = beanAccess;
            this.propertiesCache.clear();
            this.readableProperties.clear();
        }
    }

    public void setAllowReadOnlyProperties(boolean z) {
        if (this.allowReadOnlyProperties != z) {
            this.allowReadOnlyProperties = z;
            this.readableProperties.clear();
        }
    }

    public void setSkipMissingProperties(boolean z) {
        if (this.skipMissingProperties != z) {
            this.skipMissingProperties = z;
            this.readableProperties.clear();
        }
    }
}
