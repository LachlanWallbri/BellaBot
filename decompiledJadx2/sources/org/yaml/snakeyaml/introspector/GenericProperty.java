package org.yaml.snakeyaml.introspector;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class GenericProperty extends Property {
    private Class<?>[] actualClasses;
    private boolean actualClassesChecked;
    private Type genType;

    public GenericProperty(String str, Class<?> cls, Type type) {
        super(str, cls);
        this.genType = type;
        this.actualClassesChecked = type == null;
    }

    @Override // org.yaml.snakeyaml.introspector.Property
    public Class<?>[] getActualTypeArguments() {
        if (!this.actualClassesChecked) {
            Type type = this.genType;
            if (type instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                if (actualTypeArguments.length > 0) {
                    this.actualClasses = new Class[actualTypeArguments.length];
                    int i = 0;
                    while (true) {
                        if (i >= actualTypeArguments.length) {
                            break;
                        }
                        if (actualTypeArguments[i] instanceof Class) {
                            this.actualClasses[i] = (Class) actualTypeArguments[i];
                        } else if (actualTypeArguments[i] instanceof ParameterizedType) {
                            this.actualClasses[i] = (Class) ((ParameterizedType) actualTypeArguments[i]).getRawType();
                        } else if (actualTypeArguments[i] instanceof GenericArrayType) {
                            Type genericComponentType = ((GenericArrayType) actualTypeArguments[i]).getGenericComponentType();
                            if (genericComponentType instanceof Class) {
                                this.actualClasses[i] = Array.newInstance((Class<?>) genericComponentType, 0).getClass();
                            } else {
                                this.actualClasses = null;
                                break;
                            }
                        } else {
                            this.actualClasses = null;
                            break;
                        }
                        i++;
                    }
                }
            } else if (type instanceof GenericArrayType) {
                Type genericComponentType2 = ((GenericArrayType) type).getGenericComponentType();
                if (genericComponentType2 instanceof Class) {
                    this.actualClasses = new Class[]{(Class) genericComponentType2};
                }
            } else if ((type instanceof Class) && ((Class) type).isArray()) {
                this.actualClasses = new Class[1];
                this.actualClasses[0] = getType().getComponentType();
            }
            this.actualClassesChecked = true;
        }
        return this.actualClasses;
    }
}
