package org.yaml.snakeyaml.introspector;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class Property implements Comparable<Property> {
    private final String name;
    private final Class<?> type;

    public abstract Object get(Object obj);

    public abstract Class<?>[] getActualTypeArguments();

    public boolean isReadable() {
        return true;
    }

    public boolean isWritable() {
        return true;
    }

    public abstract void set(Object obj, Object obj2) throws Exception;

    public Property(String str, Class<?> cls) {
        this.name = str;
        this.type = cls;
    }

    public Class<?> getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return getName() + " of " + getType();
    }

    @Override // java.lang.Comparable
    public int compareTo(Property property) {
        return this.name.compareTo(property.name);
    }

    public int hashCode() {
        return this.name.hashCode() + this.type.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Property)) {
            return false;
        }
        Property property = (Property) obj;
        return this.name.equals(property.getName()) && this.type.equals(property.getType());
    }
}
