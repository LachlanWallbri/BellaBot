package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class BeanSerializerBuilder {
    private static final BeanPropertyWriter[] NO_PROPERTIES = new BeanPropertyWriter[0];
    protected AnyGetterWriter _anyGetter;
    protected final BeanDescription _beanDesc;
    protected SerializationConfig _config;
    protected Object _filterId;
    protected BeanPropertyWriter[] _filteredProperties;
    protected ObjectIdWriter _objectIdWriter;
    protected List<BeanPropertyWriter> _properties;
    protected AnnotatedMember _typeId;

    public BeanSerializerBuilder(BeanDescription beanDescription) {
        this._properties = Collections.emptyList();
        this._beanDesc = beanDescription;
    }

    protected BeanSerializerBuilder(BeanSerializerBuilder beanSerializerBuilder) {
        this._properties = Collections.emptyList();
        this._beanDesc = beanSerializerBuilder._beanDesc;
        this._properties = beanSerializerBuilder._properties;
        this._filteredProperties = beanSerializerBuilder._filteredProperties;
        this._anyGetter = beanSerializerBuilder._anyGetter;
        this._filterId = beanSerializerBuilder._filterId;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setConfig(SerializationConfig serializationConfig) {
        this._config = serializationConfig;
    }

    public void setProperties(List<BeanPropertyWriter> list) {
        this._properties = list;
    }

    public void setFilteredProperties(BeanPropertyWriter[] beanPropertyWriterArr) {
        if (beanPropertyWriterArr != null && beanPropertyWriterArr.length != this._properties.size()) {
            throw new IllegalArgumentException(String.format("Trying to set %d filtered properties; must match length of non-filtered `properties` (%d)", Integer.valueOf(beanPropertyWriterArr.length), Integer.valueOf(this._properties.size())));
        }
        this._filteredProperties = beanPropertyWriterArr;
    }

    public void setAnyGetter(AnyGetterWriter anyGetterWriter) {
        this._anyGetter = anyGetterWriter;
    }

    public void setFilterId(Object obj) {
        this._filterId = obj;
    }

    public void setTypeId(AnnotatedMember annotatedMember) {
        if (this._typeId != null) {
            throw new IllegalArgumentException("Multiple type ids specified with " + this._typeId + " and " + annotatedMember);
        }
        this._typeId = annotatedMember;
    }

    public void setObjectIdWriter(ObjectIdWriter objectIdWriter) {
        this._objectIdWriter = objectIdWriter;
    }

    public AnnotatedClass getClassInfo() {
        return this._beanDesc.getClassInfo();
    }

    public BeanDescription getBeanDescription() {
        return this._beanDesc;
    }

    public List<BeanPropertyWriter> getProperties() {
        return this._properties;
    }

    public boolean hasProperties() {
        List<BeanPropertyWriter> list = this._properties;
        return list != null && list.size() > 0;
    }

    public BeanPropertyWriter[] getFilteredProperties() {
        return this._filteredProperties;
    }

    public AnyGetterWriter getAnyGetter() {
        return this._anyGetter;
    }

    public Object getFilterId() {
        return this._filterId;
    }

    public AnnotatedMember getTypeId() {
        return this._typeId;
    }

    public ObjectIdWriter getObjectIdWriter() {
        return this._objectIdWriter;
    }

    public JsonSerializer<?> build() {
        BeanPropertyWriter[] beanPropertyWriterArr;
        if (this._typeId != null && this._config.isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            this._typeId.fixAccess(this._config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        AnyGetterWriter anyGetterWriter = this._anyGetter;
        if (anyGetterWriter != null) {
            anyGetterWriter.fixAccess(this._config);
        }
        List<BeanPropertyWriter> list = this._properties;
        if (list == null || list.isEmpty()) {
            if (this._anyGetter == null && this._objectIdWriter == null) {
                return null;
            }
            beanPropertyWriterArr = NO_PROPERTIES;
        } else {
            List<BeanPropertyWriter> list2 = this._properties;
            beanPropertyWriterArr = (BeanPropertyWriter[]) list2.toArray(new BeanPropertyWriter[list2.size()]);
            if (this._config.isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                for (BeanPropertyWriter beanPropertyWriter : beanPropertyWriterArr) {
                    beanPropertyWriter.fixAccess(this._config);
                }
            }
        }
        BeanPropertyWriter[] beanPropertyWriterArr2 = this._filteredProperties;
        if (beanPropertyWriterArr2 != null && beanPropertyWriterArr2.length != this._properties.size()) {
            throw new IllegalStateException(String.format("Mismatch between `properties` size (%d), `filteredProperties` (%s): should have as many (or `null` for latter)", Integer.valueOf(this._properties.size()), Integer.valueOf(this._filteredProperties.length)));
        }
        return new BeanSerializer(this._beanDesc.getType(), this, beanPropertyWriterArr, this._filteredProperties);
    }

    public BeanSerializer createDummy() {
        return BeanSerializer.createDummy(this._beanDesc.getType(), this);
    }
}
