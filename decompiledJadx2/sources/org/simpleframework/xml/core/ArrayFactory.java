package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Position;

/* loaded from: classes9.dex */
class ArrayFactory extends Factory {
    public ArrayFactory(Context context, Type type) {
        super(context, type);
    }

    @Override // org.simpleframework.xml.core.Factory
    public Object getInstance() throws Exception {
        Class componentType = getComponentType();
        if (componentType != null) {
            return Array.newInstance((Class<?>) componentType, 0);
        }
        return null;
    }

    public Instance getInstance(InputNode inputNode) throws Exception {
        Position position = inputNode.getPosition();
        Value override = getOverride(inputNode);
        if (override == null) {
            throw new ElementException("Array length required for %s at %s", this.type, position);
        }
        return getInstance(override, override.getType());
    }

    private Instance getInstance(Value value, Class cls) throws Exception {
        Class componentType = getComponentType();
        if (!componentType.isAssignableFrom(cls)) {
            throw new InstantiationException("Array of type %s cannot hold %s for %s", componentType, cls, this.type);
        }
        return new ArrayInstance(value);
    }

    private Class getComponentType() throws Exception {
        Class type = getType();
        if (!type.isArray()) {
            throw new InstantiationException("The %s not an array for %s", type, this.type);
        }
        return type.getComponentType();
    }
}
