package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: resolvers.kt */
/* loaded from: classes2.dex */
public final class LazyJavaTypeParameterResolver implements TypeParameterResolver {

    /* renamed from: c */
    private final LazyJavaResolverContext f8760c;
    private final DeclarationDescriptor containingDeclaration;
    private final MemoizedFunctionToNullable<JavaTypeParameter, LazyJavaTypeParameterDescriptor> resolve;
    private final Map<JavaTypeParameter, Integer> typeParameters;
    private final int typeParametersIndexOffset;

    public LazyJavaTypeParameterResolver(LazyJavaResolverContext c, DeclarationDescriptor containingDeclaration, JavaTypeParameterListOwner typeParameterOwner, int i) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(typeParameterOwner, "typeParameterOwner");
        this.f8760c = c;
        this.containingDeclaration = containingDeclaration;
        this.typeParametersIndexOffset = i;
        this.typeParameters = CollectionsKt.mapToIndex(typeParameterOwner.getTypeParameters());
        this.resolve = this.f8760c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<JavaTypeParameter, LazyJavaTypeParameterDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaTypeParameterResolver$resolve$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LazyJavaTypeParameterDescriptor invoke(JavaTypeParameter typeParameter) {
                Map map;
                LazyJavaResolverContext lazyJavaResolverContext;
                int i2;
                DeclarationDescriptor declarationDescriptor;
                Intrinsics.checkParameterIsNotNull(typeParameter, "typeParameter");
                map = LazyJavaTypeParameterResolver.this.typeParameters;
                Integer num = (Integer) map.get(typeParameter);
                if (num == null) {
                    return null;
                }
                int intValue = num.intValue();
                lazyJavaResolverContext = LazyJavaTypeParameterResolver.this.f8760c;
                LazyJavaResolverContext child = ContextKt.child(lazyJavaResolverContext, LazyJavaTypeParameterResolver.this);
                i2 = LazyJavaTypeParameterResolver.this.typeParametersIndexOffset;
                int i3 = i2 + intValue;
                declarationDescriptor = LazyJavaTypeParameterResolver.this.containingDeclaration;
                return new LazyJavaTypeParameterDescriptor(child, typeParameter, i3, declarationDescriptor);
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver
    public TypeParameterDescriptor resolveTypeParameter(JavaTypeParameter javaTypeParameter) {
        Intrinsics.checkParameterIsNotNull(javaTypeParameter, "javaTypeParameter");
        LazyJavaTypeParameterDescriptor invoke = this.resolve.invoke(javaTypeParameter);
        return invoke != null ? invoke : this.f8760c.getTypeParameterResolver().resolveTypeParameter(javaTypeParameter);
    }
}
