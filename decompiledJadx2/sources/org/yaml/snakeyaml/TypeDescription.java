package org.yaml.snakeyaml;

import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.nodes.Tag;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class TypeDescription {
    private Map<String, Class<? extends Object>> keyProperties;
    private Map<String, Class<? extends Object>> listProperties;
    private Tag tag;
    private final Class<? extends Object> type;
    private Map<String, Class<? extends Object>> valueProperties;

    public TypeDescription(Class<? extends Object> cls, Tag tag) {
        this.type = cls;
        this.tag = tag;
        this.listProperties = new HashMap();
        this.keyProperties = new HashMap();
        this.valueProperties = new HashMap();
    }

    public TypeDescription(Class<? extends Object> cls, String str) {
        this(cls, new Tag(str));
    }

    public TypeDescription(Class<? extends Object> cls) {
        this(cls, (Tag) null);
    }

    public Tag getTag() {
        return this.tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setTag(String str) {
        setTag(new Tag(str));
    }

    public Class<? extends Object> getType() {
        return this.type;
    }

    public void putListPropertyType(String str, Class<? extends Object> cls) {
        this.listProperties.put(str, cls);
    }

    public Class<? extends Object> getListPropertyType(String str) {
        return this.listProperties.get(str);
    }

    public void putMapPropertyType(String str, Class<? extends Object> cls, Class<? extends Object> cls2) {
        this.keyProperties.put(str, cls);
        this.valueProperties.put(str, cls2);
    }

    public Class<? extends Object> getMapKeyType(String str) {
        return this.keyProperties.get(str);
    }

    public Class<? extends Object> getMapValueType(String str) {
        return this.valueProperties.get(str);
    }

    public String toString() {
        return "TypeDescription for " + getType() + " (tag='" + getTag() + "')";
    }
}
