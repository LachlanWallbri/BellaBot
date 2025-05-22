package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractLazyTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: LazyJavaTypeParameterDescriptor.kt */
/* loaded from: classes2.dex */
public final class LazyJavaTypeParameterDescriptor extends AbstractLazyTypeParameterDescriptor {
    private final LazyJavaAnnotations annotations;

    /* renamed from: c */
    private final LazyJavaResolverContext f8770c;
    private final JavaTypeParameter javaTypeParameter;

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    /* renamed from: reportSupertypeLoopError */
    protected void mo5463reportSupertypeLoopError(KotlinType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaTypeParameterDescriptor(LazyJavaResolverContext c, JavaTypeParameter javaTypeParameter, int i, DeclarationDescriptor containingDeclaration) {
        super(c.getStorageManager(), containingDeclaration, javaTypeParameter.getName(), Variance.INVARIANT, false, i, SourceElement.NO_SOURCE, c.getComponents().getSupertypeLoopChecker());
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(javaTypeParameter, "javaTypeParameter");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        this.f8770c = c;
        this.javaTypeParameter = javaTypeParameter;
        this.annotations = new LazyJavaAnnotations(this.f8770c, this.javaTypeParameter);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public LazyJavaAnnotations getAnnotations() {
        return this.annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    protected List<KotlinType> resolveUpperBounds() {
        Collection<JavaClassifierType> upperBounds = this.javaTypeParameter.getUpperBounds();
        if (upperBounds.isEmpty()) {
            SimpleType anyType = this.f8770c.getModule().getBuiltIns().getAnyType();
            Intrinsics.checkExpressionValueIsNotNull(anyType, "c.module.builtIns.anyType");
            SimpleType nullableAnyType = this.f8770c.getModule().getBuiltIns().getNullableAnyType();
            Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "c.module.builtIns.nullableAnyType");
            return CollectionsKt.listOf(KotlinTypeFactory.flexibleType(anyType, nullableAnyType));
        }
        Collection<JavaClassifierType> collection = upperBounds;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(this.f8770c.getTypeResolver().transformJavaType((JavaClassifierType) it.next(), JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, this, 1, null)));
        }
        return arrayList;
    }
}
