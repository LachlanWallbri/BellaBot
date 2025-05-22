package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

/* loaded from: classes9.dex */
class InstanceFactory {
    private final Cache<Constructor> cache = new ConcurrentCache();

    public Instance getInstance(Value value) {
        return new ValueInstance(value);
    }

    public Instance getInstance(Class cls) {
        return new ClassInstance(cls);
    }

    protected Object getObject(Class cls) throws Exception {
        Constructor fetch = this.cache.fetch(cls);
        if (fetch == null) {
            fetch = cls.getDeclaredConstructor(new Class[0]);
            if (!fetch.isAccessible()) {
                fetch.setAccessible(true);
            }
            this.cache.cache(cls, fetch);
        }
        return fetch.newInstance(new Object[0]);
    }

    /* loaded from: classes9.dex */
    private class ValueInstance implements Instance {
        private final Class type;
        private final Value value;

        public ValueInstance(Value value) {
            this.type = value.getType();
            this.value = value;
        }

        @Override // org.simpleframework.xml.core.Instance
        public Object getInstance() throws Exception {
            if (this.value.isReference()) {
                return this.value.getValue();
            }
            Object object = InstanceFactory.this.getObject(this.type);
            Value value = this.value;
            if (value != null) {
                value.setValue(object);
            }
            return object;
        }

        @Override // org.simpleframework.xml.core.Instance
        public Object setInstance(Object obj) {
            Value value = this.value;
            if (value != null) {
                value.setValue(obj);
            }
            return obj;
        }

        @Override // org.simpleframework.xml.core.Instance
        public boolean isReference() {
            return this.value.isReference();
        }

        @Override // org.simpleframework.xml.core.Instance
        public Class getType() {
            return this.type;
        }
    }

    /* loaded from: classes9.dex */
    private class ClassInstance implements Instance {
        private Class type;
        private Object value;

        @Override // org.simpleframework.xml.core.Instance
        public boolean isReference() {
            return false;
        }

        public ClassInstance(Class cls) {
            this.type = cls;
        }

        @Override // org.simpleframework.xml.core.Instance
        public Object getInstance() throws Exception {
            if (this.value == null) {
                this.value = InstanceFactory.this.getObject(this.type);
            }
            return this.value;
        }

        @Override // org.simpleframework.xml.core.Instance
        public Object setInstance(Object obj) throws Exception {
            this.value = obj;
            return obj;
        }

        @Override // org.simpleframework.xml.core.Instance
        public Class getType() {
            return this.type;
        }
    }
}
