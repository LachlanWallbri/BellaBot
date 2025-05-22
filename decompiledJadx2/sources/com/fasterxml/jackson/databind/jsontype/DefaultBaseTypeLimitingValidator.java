package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import java.io.Closeable;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class DefaultBaseTypeLimitingValidator extends PolymorphicTypeValidator implements Serializable {
    private static final long serialVersionUID = 1;

    protected boolean isSafeSubType(MapperConfig<?> mapperConfig, JavaType javaType, JavaType javaType2) {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator
    public PolymorphicTypeValidator.Validity validateBaseType(MapperConfig<?> mapperConfig, JavaType javaType) {
        if (isUnsafeBaseType(mapperConfig, javaType)) {
            return PolymorphicTypeValidator.Validity.DENIED;
        }
        return PolymorphicTypeValidator.Validity.INDETERMINATE;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator
    public PolymorphicTypeValidator.Validity validateSubClassName(MapperConfig<?> mapperConfig, JavaType javaType, String str) {
        return PolymorphicTypeValidator.Validity.INDETERMINATE;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator
    public PolymorphicTypeValidator.Validity validateSubType(MapperConfig<?> mapperConfig, JavaType javaType, JavaType javaType2) {
        return isSafeSubType(mapperConfig, javaType, javaType2) ? PolymorphicTypeValidator.Validity.ALLOWED : PolymorphicTypeValidator.Validity.DENIED;
    }

    protected boolean isUnsafeBaseType(MapperConfig<?> mapperConfig, JavaType javaType) {
        return UnsafeBaseTypes.instance.isUnsafeBaseType(javaType.getRawClass());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class UnsafeBaseTypes {
        public static final UnsafeBaseTypes instance = new UnsafeBaseTypes();
        private final Set<String> UNSAFE = new HashSet();

        private UnsafeBaseTypes() {
            this.UNSAFE.add(Object.class.getName());
            this.UNSAFE.add(Closeable.class.getName());
            this.UNSAFE.add(Serializable.class.getName());
            this.UNSAFE.add(AutoCloseable.class.getName());
            this.UNSAFE.add(Cloneable.class.getName());
            this.UNSAFE.add("java.util.logging.Handler");
            this.UNSAFE.add("javax.naming.Referenceable");
            this.UNSAFE.add("javax.sql.DataSource");
        }

        public boolean isUnsafeBaseType(Class<?> cls) {
            return this.UNSAFE.contains(cls.getName());
        }
    }
}
