package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KotlinTypeFactory.kt */
/* loaded from: classes2.dex */
public final class KotlinTypeFactory {
    public static final KotlinTypeFactory INSTANCE = new KotlinTypeFactory();

    private KotlinTypeFactory() {
    }

    private final MemberScope computeMemberScope(TypeConstructor typeConstructor, List<? extends TypeProjection> list) {
        ClassifierDescriptor mo5460getDeclarationDescriptor = typeConstructor.mo5460getDeclarationDescriptor();
        if (mo5460getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return mo5460getDeclarationDescriptor.getDefaultType().getMemberScope();
        }
        if (mo5460getDeclarationDescriptor instanceof ClassDescriptor) {
            if (list.isEmpty()) {
                return ((ClassDescriptor) mo5460getDeclarationDescriptor).getDefaultType().getMemberScope();
            }
            MemberScope memberScope = ((ClassDescriptor) mo5460getDeclarationDescriptor).getMemberScope(TypeConstructorSubstitution.Companion.create(typeConstructor, list));
            Intrinsics.checkExpressionValueIsNotNull(memberScope, "descriptor.getMemberScop…(constructor, arguments))");
            return memberScope;
        }
        if (mo5460getDeclarationDescriptor instanceof TypeAliasDescriptor) {
            MemberScope createErrorScope = ErrorUtils.createErrorScope("Scope for abbreviation: " + ((TypeAliasDescriptor) mo5460getDeclarationDescriptor).getName(), true);
            Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…{descriptor.name}\", true)");
            return createErrorScope;
        }
        throw new IllegalStateException("Unsupported classifier: " + mo5460getDeclarationDescriptor + " for constructor: " + typeConstructor);
    }

    @JvmStatic
    public static final SimpleType simpleType(Annotations annotations, TypeConstructor constructor, List<? extends TypeProjection> arguments, boolean z) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        if (annotations.isEmpty() && arguments.isEmpty() && !z && constructor.mo5460getDeclarationDescriptor() != null) {
            ClassifierDescriptor mo5460getDeclarationDescriptor = constructor.mo5460getDeclarationDescriptor();
            if (mo5460getDeclarationDescriptor == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(mo5460getDeclarationDescriptor, "constructor.declarationDescriptor!!");
            SimpleType defaultType = mo5460getDeclarationDescriptor.getDefaultType();
            Intrinsics.checkExpressionValueIsNotNull(defaultType, "constructor.declarationDescriptor!!.defaultType");
            return defaultType;
        }
        return simpleTypeWithNonTrivialMemberScope(annotations, constructor, arguments, z, INSTANCE.computeMemberScope(constructor, arguments));
    }

    @JvmStatic
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(Annotations annotations, TypeConstructor constructor, List<? extends TypeProjection> arguments, boolean z, MemberScope memberScope) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        Intrinsics.checkParameterIsNotNull(memberScope, "memberScope");
        SimpleTypeImpl simpleTypeImpl = new SimpleTypeImpl(constructor, arguments, z, memberScope);
        if (annotations.isEmpty()) {
            return simpleTypeImpl;
        }
        return new AnnotatedSimpleType(simpleTypeImpl, annotations);
    }

    @JvmStatic
    public static final SimpleType simpleNotNullType(Annotations annotations, ClassDescriptor descriptor, List<? extends TypeProjection> arguments) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        TypeConstructor typeConstructor = descriptor.getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "descriptor.typeConstructor");
        return simpleType(annotations, typeConstructor, arguments, false);
    }

    @JvmStatic
    public static final UnwrappedType flexibleType(SimpleType lowerBound, SimpleType upperBound) {
        Intrinsics.checkParameterIsNotNull(lowerBound, "lowerBound");
        Intrinsics.checkParameterIsNotNull(upperBound, "upperBound");
        return Intrinsics.areEqual(lowerBound, upperBound) ? lowerBound : new FlexibleTypeImpl(lowerBound, upperBound);
    }

    @JvmStatic
    public static final SimpleType integerLiteralType(Annotations annotations, IntegerLiteralTypeConstructor constructor, boolean z) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        List emptyList = CollectionsKt.emptyList();
        MemberScope createErrorScope = ErrorUtils.createErrorScope("Scope for integer literal type", true);
        Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…eger literal type\", true)");
        return simpleTypeWithNonTrivialMemberScope(annotations, constructor, emptyList, z, createErrorScope);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: KotlinTypeFactory.kt */
    /* loaded from: classes8.dex */
    public static final class ExpandedTypeOrRefinedConstructor {
        private final SimpleType expandedType;
        private final TypeConstructor refinedConstructor;

        public ExpandedTypeOrRefinedConstructor(SimpleType simpleType, TypeConstructor typeConstructor) {
            this.expandedType = simpleType;
            this.refinedConstructor = typeConstructor;
        }

        public final SimpleType getExpandedType() {
            return this.expandedType;
        }

        public final TypeConstructor getRefinedConstructor() {
            return this.refinedConstructor;
        }
    }
}
