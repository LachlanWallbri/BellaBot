package org.yaml.snakeyaml.introspector;

import java.lang.reflect.Field;
import org.yaml.snakeyaml.error.YAMLException;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class FieldProperty extends GenericProperty {
    private final Field field;

    public FieldProperty(Field field) {
        super(field.getName(), field.getType(), field.getGenericType());
        this.field = field;
        field.setAccessible(true);
    }

    @Override // org.yaml.snakeyaml.introspector.Property
    public void set(Object obj, Object obj2) throws Exception {
        this.field.set(obj, obj2);
    }

    @Override // org.yaml.snakeyaml.introspector.Property
    public Object get(Object obj) {
        try {
            return this.field.get(obj);
        } catch (Exception e) {
            throw new YAMLException("Unable to access field " + this.field.getName() + " on object " + obj + " : " + e);
        }
    }
}
