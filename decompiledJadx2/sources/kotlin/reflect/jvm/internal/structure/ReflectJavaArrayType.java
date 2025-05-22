package kotlin.reflect.jvm.internal.structure;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.structure.ReflectJavaType;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ReflectJavaArrayType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0001X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lkotlin/reflect/jvm/internal/structure/ReflectJavaArrayType;", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaType;", "Lkotlin/reflect/jvm/internal/impl/load/java/structure/JavaArrayType;", "reflectType", "Ljava/lang/reflect/Type;", "(Ljava/lang/reflect/Type;)V", "componentType", "getComponentType", "()Lkotlin/reflect/jvm/internal/structure/ReflectJavaType;", "getReflectType", "()Ljava/lang/reflect/Type;", "descriptors.runtime"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class ReflectJavaArrayType extends ReflectJavaType implements JavaArrayType {
    private final ReflectJavaType componentType;
    private final Type reflectType;

    public ReflectJavaArrayType(Type reflectType) {
        ReflectJavaType create;
        Intrinsics.checkParameterIsNotNull(reflectType, "reflectType");
        this.reflectType = reflectType;
        Type reflectType2 = getReflectType();
        if (!(reflectType2 instanceof GenericArrayType)) {
            if (reflectType2 instanceof Class) {
                Class cls = (Class) reflectType2;
                if (cls.isArray()) {
                    ReflectJavaType.Companion companion = ReflectJavaType.INSTANCE;
                    Class<?> componentType = cls.getComponentType();
                    Intrinsics.checkExpressionValueIsNotNull(componentType, "getComponentType()");
                    create = companion.create(componentType);
                }
            }
            throw new IllegalArgumentException("Not an array type (" + getReflectType().getClass() + "): " + getReflectType());
        }
        ReflectJavaType.Companion companion2 = ReflectJavaType.INSTANCE;
        Type genericComponentType = ((GenericArrayType) reflectType2).getGenericComponentType();
        Intrinsics.checkExpressionValueIsNotNull(genericComponentType, "genericComponentType");
        create = companion2.create(genericComponentType);
        this.componentType = create;
    }

    @Override // kotlin.reflect.jvm.internal.structure.ReflectJavaType
    protected Type getReflectType() {
        return this.reflectType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType
    public ReflectJavaType getComponentType() {
        return this.componentType;
    }
}
