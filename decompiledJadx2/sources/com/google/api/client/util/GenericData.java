package com.google.api.client.util;

import com.google.api.client.util.DataMap;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class GenericData extends AbstractMap<String, Object> implements Cloneable {
    final ClassInfo classInfo;
    Map<String, Object> unknownFields;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum Flags {
        IGNORE_CASE
    }

    public GenericData() {
        this(EnumSet.noneOf(Flags.class));
    }

    public GenericData(EnumSet<Flags> enumSet) {
        this.unknownFields = ArrayMap.create();
        this.classInfo = ClassInfo.m573of(getClass(), enumSet.contains(Flags.IGNORE_CASE));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (!(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(str);
        if (fieldInfo != null) {
            return fieldInfo.getValue(this);
        }
        if (this.classInfo.getIgnoreCase()) {
            str = str.toLowerCase(Locale.US);
        }
        return this.unknownFields.get(str);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(String str, Object obj) {
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(str);
        if (fieldInfo != null) {
            Object value = fieldInfo.getValue(this);
            fieldInfo.setValue(this, obj);
            return value;
        }
        if (this.classInfo.getIgnoreCase()) {
            str = str.toLowerCase(Locale.US);
        }
        return this.unknownFields.put(str, obj);
    }

    public GenericData set(String str, Object obj) {
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(str);
        if (fieldInfo != null) {
            fieldInfo.setValue(this, obj);
        } else {
            if (this.classInfo.getIgnoreCase()) {
                str = str.toLowerCase(Locale.US);
            }
            this.unknownFields.put(str, obj);
        }
        return this;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends String, ?> map) {
        for (Map.Entry<? extends String, ?> entry : map.entrySet()) {
            set(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (!(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        if (this.classInfo.getFieldInfo(str) != null) {
            throw new UnsupportedOperationException();
        }
        if (this.classInfo.getIgnoreCase()) {
            str = str.toLowerCase(Locale.US);
        }
        return this.unknownFields.remove(str);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return new EntrySet();
    }

    @Override // java.util.AbstractMap
    public GenericData clone() {
        try {
            GenericData genericData = (GenericData) super.clone();
            Data.deepCopy(this, genericData);
            genericData.unknownFields = (Map) Data.clone(this.unknownFields);
            return genericData;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    public final Map<String, Object> getUnknownKeys() {
        return this.unknownFields;
    }

    public final void setUnknownKeys(Map<String, Object> map) {
        this.unknownFields = map;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof GenericData)) {
            return false;
        }
        GenericData genericData = (GenericData) obj;
        return super.equals(genericData) && java.util.Objects.equals(this.classInfo, genericData.classInfo);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        return java.util.Objects.hash(Integer.valueOf(super.hashCode()), this.classInfo);
    }

    @Override // java.util.AbstractMap
    public String toString() {
        return "GenericData{classInfo=" + this.classInfo.names + ", " + super.toString() + "}";
    }

    public final ClassInfo getClassInfo() {
        return this.classInfo;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    final class EntrySet extends AbstractSet<Map.Entry<String, Object>> {
        private final DataMap.EntrySet dataEntrySet;

        EntrySet() {
            this.dataEntrySet = new DataMap(GenericData.this, GenericData.this.classInfo.getIgnoreCase()).entrySet();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<String, Object>> iterator() {
            return new EntryIterator(this.dataEntrySet);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return GenericData.this.unknownFields.size() + this.dataEntrySet.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            GenericData.this.unknownFields.clear();
            this.dataEntrySet.clear();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    final class EntryIterator implements Iterator<Map.Entry<String, Object>> {
        private final Iterator<Map.Entry<String, Object>> fieldIterator;
        private boolean startedUnknown;
        private final Iterator<Map.Entry<String, Object>> unknownIterator;

        EntryIterator(DataMap.EntrySet entrySet) {
            this.fieldIterator = entrySet.iterator();
            this.unknownIterator = GenericData.this.unknownFields.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.fieldIterator.hasNext() || this.unknownIterator.hasNext();
        }

        @Override // java.util.Iterator
        public Map.Entry<String, Object> next() {
            if (!this.startedUnknown) {
                if (this.fieldIterator.hasNext()) {
                    return this.fieldIterator.next();
                }
                this.startedUnknown = true;
            }
            return this.unknownIterator.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.startedUnknown) {
                this.unknownIterator.remove();
            }
            this.fieldIterator.remove();
        }
    }
}
