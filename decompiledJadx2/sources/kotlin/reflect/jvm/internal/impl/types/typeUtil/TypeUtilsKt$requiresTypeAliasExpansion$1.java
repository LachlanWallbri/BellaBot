package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* compiled from: TypeUtils.kt */
/* loaded from: classes8.dex */
final class TypeUtilsKt$requiresTypeAliasExpansion$1 extends Lambda implements Function1<UnwrappedType, Boolean> {
    public static final TypeUtilsKt$requiresTypeAliasExpansion$1 INSTANCE = new TypeUtilsKt$requiresTypeAliasExpansion$1();

    TypeUtilsKt$requiresTypeAliasExpansion$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(UnwrappedType unwrappedType) {
        return Boolean.valueOf(invoke2(unwrappedType));
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final boolean invoke2(UnwrappedType it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        ClassifierDescriptor mo5460getDeclarationDescriptor = it.getConstructor().mo5460getDeclarationDescriptor();
        if (mo5460getDeclarationDescriptor != null) {
            return (mo5460getDeclarationDescriptor instanceof TypeAliasDescriptor) || (mo5460getDeclarationDescriptor instanceof TypeParameterDescriptor);
        }
        return false;
    }
}
