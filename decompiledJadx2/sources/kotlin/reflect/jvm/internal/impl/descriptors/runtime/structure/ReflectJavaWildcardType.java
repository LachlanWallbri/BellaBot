package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;

/* compiled from: ReflectJavaWildcardType.kt */
/* loaded from: classes8.dex */
public final class ReflectJavaWildcardType extends ReflectJavaType implements JavaWildcardType {
    private final WildcardType reflectType;

    public ReflectJavaWildcardType(WildcardType reflectType) {
        Intrinsics.checkParameterIsNotNull(reflectType, "reflectType");
        this.reflectType = reflectType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    public WildcardType getReflectType() {
        return this.reflectType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType
    public ReflectJavaType getBound() {
        Type[] upperBounds = getReflectType().getUpperBounds();
        Type[] lowerBounds = getReflectType().getLowerBounds();
        if (upperBounds.length > 1 || lowerBounds.length > 1) {
            throw new UnsupportedOperationException("Wildcard types with many bounds are not yet supported: " + getReflectType());
        }
        if (lowerBounds.length == 1) {
            ReflectJavaType.Factory factory = ReflectJavaType.Factory;
            Intrinsics.checkExpressionValueIsNotNull(lowerBounds, "lowerBounds");
            Object single = ArraysKt.single(lowerBounds);
            Intrinsics.checkExpressionValueIsNotNull(single, "lowerBounds.single()");
            return factory.create((Type) single);
        }
        if (upperBounds.length != 1) {
            return null;
        }
        Intrinsics.checkExpressionValueIsNotNull(upperBounds, "upperBounds");
        Type ub = (Type) ArraysKt.single(upperBounds);
        if (!(!Intrinsics.areEqual(ub, Object.class))) {
            return null;
        }
        ReflectJavaType.Factory factory2 = ReflectJavaType.Factory;
        Intrinsics.checkExpressionValueIsNotNull(ub, "ub");
        return factory2.create(ub);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType
    public boolean isExtends() {
        Intrinsics.checkExpressionValueIsNotNull(getReflectType().getUpperBounds(), "reflectType.upperBounds");
        return !Intrinsics.areEqual((Type) ArraysKt.firstOrNull(r0), Object.class);
    }
}
