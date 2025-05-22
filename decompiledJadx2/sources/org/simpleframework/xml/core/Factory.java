package org.simpleframework.xml.core;

import java.lang.reflect.Modifier;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public abstract class Factory {
    protected Context context;
    protected Class override;
    protected Support support;
    protected Type type;

    /* JADX INFO: Access modifiers changed from: protected */
    public Factory(Context context, Type type) {
        this(context, type, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Factory(Context context, Type type, Class cls) {
        this.support = context.getSupport();
        this.override = cls;
        this.context = context;
        this.type = type;
    }

    public Class getType() {
        Class cls = this.override;
        return cls != null ? cls : this.type.getType();
    }

    public Object getInstance() throws Exception {
        Class type = getType();
        if (!isInstantiable(type)) {
            throw new InstantiationException("Type %s can not be instantiated", type);
        }
        return type.newInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Value getOverride(InputNode inputNode) throws Exception {
        Value conversion = getConversion(inputNode);
        if (conversion != null) {
            Position position = inputNode.getPosition();
            Class type = conversion.getType();
            if (!isCompatible(getType(), type)) {
                throw new InstantiationException("Incompatible %s for %s at %s", type, this.type, position);
            }
        }
        return conversion;
    }

    public boolean setOverride(Type type, Object obj, OutputNode outputNode) throws Exception {
        Class type2 = type.getType();
        if (type2.isPrimitive()) {
            type = getPrimitive(type, type2);
        }
        return this.context.setOverride(type, obj, outputNode);
    }

    private Type getPrimitive(Type type, Class cls) throws Exception {
        Support support = this.support;
        Class primitive = Support.getPrimitive(cls);
        return primitive != cls ? new OverrideType(type, primitive) : type;
    }

    public Value getConversion(InputNode inputNode) throws Exception {
        Value override = this.context.getOverride(this.type, inputNode);
        if (override != null && this.override != null) {
            if (!isCompatible(this.override, override.getType())) {
                return new OverrideValue(override, this.override);
            }
        }
        return override;
    }

    public static boolean isCompatible(Class cls, Class cls2) {
        if (cls.isArray()) {
            cls = cls.getComponentType();
        }
        return cls.isAssignableFrom(cls2);
    }

    public static boolean isInstantiable(Class cls) {
        if (Modifier.isAbstract(cls.getModifiers())) {
            return false;
        }
        return !Modifier.isInterface(r1);
    }
}
