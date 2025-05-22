package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;

/* compiled from: TypeAliasExpansionReportStrategy.kt */
/* loaded from: classes8.dex */
public interface TypeAliasExpansionReportStrategy {
    void boundsViolationInSubstitution(KotlinType kotlinType, KotlinType kotlinType2, KotlinType kotlinType3, TypeParameterDescriptor typeParameterDescriptor);

    void conflictingProjection(TypeAliasDescriptor typeAliasDescriptor, TypeParameterDescriptor typeParameterDescriptor, KotlinType kotlinType);

    void recursiveTypeAlias(TypeAliasDescriptor typeAliasDescriptor);

    void repeatedAnnotation(AnnotationDescriptor annotationDescriptor);

    /* compiled from: TypeAliasExpansionReportStrategy.kt */
    /* loaded from: classes8.dex */
    public static final class DO_NOTHING implements TypeAliasExpansionReportStrategy {
        public static final DO_NOTHING INSTANCE = new DO_NOTHING();

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void boundsViolationInSubstitution(KotlinType bound, KotlinType unsubstitutedArgument, KotlinType argument, TypeParameterDescriptor typeParameter) {
            Intrinsics.checkParameterIsNotNull(bound, "bound");
            Intrinsics.checkParameterIsNotNull(unsubstitutedArgument, "unsubstitutedArgument");
            Intrinsics.checkParameterIsNotNull(argument, "argument");
            Intrinsics.checkParameterIsNotNull(typeParameter, "typeParameter");
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void conflictingProjection(TypeAliasDescriptor typeAlias, TypeParameterDescriptor typeParameterDescriptor, KotlinType substitutedArgument) {
            Intrinsics.checkParameterIsNotNull(typeAlias, "typeAlias");
            Intrinsics.checkParameterIsNotNull(substitutedArgument, "substitutedArgument");
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void recursiveTypeAlias(TypeAliasDescriptor typeAlias) {
            Intrinsics.checkParameterIsNotNull(typeAlias, "typeAlias");
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy
        public void repeatedAnnotation(AnnotationDescriptor annotation) {
            Intrinsics.checkParameterIsNotNull(annotation, "annotation");
        }

        private DO_NOTHING() {
        }
    }
}
