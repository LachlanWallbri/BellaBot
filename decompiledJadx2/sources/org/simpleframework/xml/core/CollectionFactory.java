package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;

/* loaded from: classes9.dex */
class CollectionFactory extends Factory {
    public CollectionFactory(Context context, Type type) {
        super(context, type);
    }

    @Override // org.simpleframework.xml.core.Factory
    public Object getInstance() throws Exception {
        Class type = getType();
        Class conversion = !isInstantiable(type) ? getConversion(type) : type;
        if (!isCollection(conversion)) {
            throw new InstantiationException("Invalid collection %s for %s", type, this.type);
        }
        return conversion.newInstance();
    }

    public Instance getInstance(InputNode inputNode) throws Exception {
        Value override = getOverride(inputNode);
        Class type = getType();
        if (override != null) {
            return getInstance(override);
        }
        if (!isInstantiable(type)) {
            type = getConversion(type);
        }
        if (!isCollection(type)) {
            throw new InstantiationException("Invalid collection %s for %s", type, this.type);
        }
        return this.context.getInstance(type);
    }

    public Instance getInstance(Value value) throws Exception {
        Class type = value.getType();
        if (!isInstantiable(type)) {
            type = getConversion(type);
        }
        if (!isCollection(type)) {
            throw new InstantiationException("Invalid collection %s for %s", type, this.type);
        }
        return new ConversionInstance(this.context, value, type);
    }

    public Class getConversion(Class cls) throws Exception {
        if (cls.isAssignableFrom(ArrayList.class)) {
            return ArrayList.class;
        }
        if (cls.isAssignableFrom(HashSet.class)) {
            return HashSet.class;
        }
        if (cls.isAssignableFrom(TreeSet.class)) {
            return TreeSet.class;
        }
        throw new InstantiationException("Cannot instantiate %s for %s", cls, this.type);
    }

    private boolean isCollection(Class cls) {
        return Collection.class.isAssignableFrom(cls);
    }
}
