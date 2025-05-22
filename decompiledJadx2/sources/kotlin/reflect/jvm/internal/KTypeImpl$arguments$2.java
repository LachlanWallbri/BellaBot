package kotlin.reflect.jvm.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.structure.ReflectClassUtilKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KTypeImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, m3961d2 = {"<anonymous>", "", "Lkotlin/reflect/KTypeProjection;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class KTypeImpl$arguments$2 extends Lambda implements Function0<List<? extends KTypeProjection>> {
    final /* synthetic */ KTypeImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KTypeImpl$arguments$2(KTypeImpl kTypeImpl) {
        super(0);
        this.this$0 = kTypeImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends KTypeProjection> invoke() {
        KTypeProjection invariant;
        List<TypeProjection> arguments = this.this$0.getType().getArguments();
        if (arguments.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<List<? extends Type>>() { // from class: kotlin.reflect.jvm.internal.KTypeImpl$arguments$2$parameterizedTypeArguments$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends Type> invoke() {
                return ReflectClassUtilKt.getParameterizedTypeArguments(KTypeImpl$arguments$2.this.this$0.getJavaType$kotlin_reflection());
            }
        });
        final KProperty kProperty = KTypeImpl.$$delegatedProperties[3];
        List<TypeProjection> list = arguments;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        final int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeProjection typeProjection = (TypeProjection) obj;
            if (typeProjection.isStarProjection()) {
                invariant = KTypeProjection.INSTANCE.getSTAR();
            } else {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "typeProjection.type");
                KTypeImpl kTypeImpl = new KTypeImpl(type, new Function0<Type>() { // from class: kotlin.reflect.jvm.internal.KTypeImpl$arguments$2$$special$$inlined$mapIndexed$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Type invoke() {
                        Type javaType$kotlin_reflection = this.this$0.getJavaType$kotlin_reflection();
                        if (javaType$kotlin_reflection instanceof Class) {
                            Class cls = (Class) javaType$kotlin_reflection;
                            Class componentType = cls.isArray() ? cls.getComponentType() : Object.class;
                            Intrinsics.checkExpressionValueIsNotNull(componentType, "if (javaType.isArray) ja…Type else Any::class.java");
                            return componentType;
                        }
                        if (javaType$kotlin_reflection instanceof GenericArrayType) {
                            if (i != 0) {
                                throw new KotlinReflectionInternalError("Array type has been queried for a non-0th argument: " + this.this$0);
                            }
                            Type genericComponentType = ((GenericArrayType) javaType$kotlin_reflection).getGenericComponentType();
                            Intrinsics.checkExpressionValueIsNotNull(genericComponentType, "javaType.genericComponentType");
                            return genericComponentType;
                        }
                        if (javaType$kotlin_reflection instanceof ParameterizedType) {
                            Type type2 = (Type) ((List) lazy.getValue()).get(i);
                            if (type2 instanceof WildcardType) {
                                WildcardType wildcardType = (WildcardType) type2;
                                Type[] lowerBounds = wildcardType.getLowerBounds();
                                Intrinsics.checkExpressionValueIsNotNull(lowerBounds, "argument.lowerBounds");
                                Type type3 = (Type) ArraysKt.firstOrNull(lowerBounds);
                                if (type3 != null) {
                                    type2 = type3;
                                } else {
                                    Type[] upperBounds = wildcardType.getUpperBounds();
                                    Intrinsics.checkExpressionValueIsNotNull(upperBounds, "argument.upperBounds");
                                    type2 = (Type) ArraysKt.first(upperBounds);
                                }
                            }
                            Intrinsics.checkExpressionValueIsNotNull(type2, "if (argument !is Wildcar…ument.upperBounds.first()");
                            return type2;
                        }
                        throw new KotlinReflectionInternalError("Non-generic type has been queried for arguments: " + this.this$0);
                    }
                });
                int i3 = KTypeImpl.WhenMappings.$EnumSwitchMapping$0[typeProjection.getProjectionKind().ordinal()];
                if (i3 == 1) {
                    invariant = KTypeProjection.INSTANCE.invariant(kTypeImpl);
                } else if (i3 == 2) {
                    invariant = KTypeProjection.INSTANCE.contravariant(kTypeImpl);
                } else {
                    if (i3 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    invariant = KTypeProjection.INSTANCE.covariant(kTypeImpl);
                }
            }
            arrayList.add(invariant);
            i = i2;
        }
        return arrayList;
    }
}
