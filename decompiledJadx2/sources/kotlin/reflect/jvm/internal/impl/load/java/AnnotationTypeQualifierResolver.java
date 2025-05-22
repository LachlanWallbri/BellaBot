package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: AnnotationTypeQualifierResolver.kt */
/* loaded from: classes2.dex */
public final class AnnotationTypeQualifierResolver {
    private final boolean disabled;
    private final Jsr305State jsr305State;
    private final MemoizedFunctionToNullable<ClassDescriptor, AnnotationDescriptor> resolvedNicknames;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: AnnotationTypeQualifierResolver.kt */
    /* loaded from: classes2.dex */
    public enum QualifierApplicabilityType {
        METHOD_RETURN_TYPE,
        VALUE_PARAMETER,
        FIELD,
        TYPE_USE
    }

    public AnnotationTypeQualifierResolver(StorageManager storageManager, Jsr305State jsr305State) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(jsr305State, "jsr305State");
        this.jsr305State = jsr305State;
        this.resolvedNicknames = storageManager.createMemoizedFunctionWithNullableValues(new AnnotationTypeQualifierResolver$resolvedNicknames$1(this));
        this.disabled = this.jsr305State.getDisabled();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: AnnotationTypeQualifierResolver.kt */
    /* loaded from: classes2.dex */
    public static final class TypeQualifierWithApplicability {
        private final int applicability;
        private final AnnotationDescriptor typeQualifier;

        public TypeQualifierWithApplicability(AnnotationDescriptor typeQualifier, int i) {
            Intrinsics.checkParameterIsNotNull(typeQualifier, "typeQualifier");
            this.typeQualifier = typeQualifier;
            this.applicability = i;
        }

        public final AnnotationDescriptor component1() {
            return this.typeQualifier;
        }

        public final List<QualifierApplicabilityType> component2() {
            QualifierApplicabilityType[] values = QualifierApplicabilityType.values();
            ArrayList arrayList = new ArrayList();
            for (QualifierApplicabilityType qualifierApplicabilityType : values) {
                if (isApplicableTo(qualifierApplicabilityType)) {
                    arrayList.add(qualifierApplicabilityType);
                }
            }
            return arrayList;
        }

        private final boolean isApplicableTo(QualifierApplicabilityType qualifierApplicabilityType) {
            return isApplicableConsideringMask(QualifierApplicabilityType.TYPE_USE) || isApplicableConsideringMask(qualifierApplicabilityType);
        }

        private final boolean isApplicableConsideringMask(QualifierApplicabilityType qualifierApplicabilityType) {
            return ((1 << qualifierApplicabilityType.ordinal()) & this.applicability) != 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AnnotationDescriptor computeTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (!classDescriptor.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_NICKNAME_FQNAME())) {
            return null;
        }
        Iterator<AnnotationDescriptor> it = classDescriptor.getAnnotations().iterator();
        while (it.hasNext()) {
            AnnotationDescriptor resolveTypeQualifierAnnotation = resolveTypeQualifierAnnotation(it.next());
            if (resolveTypeQualifierAnnotation != null) {
                return resolveTypeQualifierAnnotation;
            }
        }
        return null;
    }

    private final AnnotationDescriptor resolveTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
            return null;
        }
        return this.resolvedNicknames.invoke(classDescriptor);
    }

    public final AnnotationDescriptor resolveTypeQualifierAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        boolean isAnnotatedWithTypeQualifier;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled() || (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) == null) {
            return null;
        }
        isAnnotatedWithTypeQualifier = AnnotationTypeQualifierResolverKt.isAnnotatedWithTypeQualifier(annotationClass);
        return isAnnotatedWithTypeQualifier ? annotationDescriptor : resolveTypeQualifierNickname(annotationClass);
    }

    public final NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability = AnnotationTypeQualifierResolverKt.getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS().get(annotationDescriptor.getFqName());
        if (nullabilityQualifierWithApplicability == null) {
            return (NullabilityQualifierWithApplicability) null;
        }
        NullabilityQualifierWithMigrationStatus component1 = nullabilityQualifierWithApplicability.component1();
        Collection<QualifierApplicabilityType> component2 = nullabilityQualifierWithApplicability.component2();
        ReportLevel resolveJsr305AnnotationState = resolveJsr305AnnotationState(annotationDescriptor);
        if (!(resolveJsr305AnnotationState != ReportLevel.IGNORE)) {
            resolveJsr305AnnotationState = null;
        }
        if (resolveJsr305AnnotationState != null) {
            return new NullabilityQualifierWithApplicability(NullabilityQualifierWithMigrationStatus.copy$default(component1, null, resolveJsr305AnnotationState.isWarning(), 1, null), component2);
        }
        return null;
    }

    public final TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        AnnotationDescriptor annotationDescriptor2;
        List<QualifierApplicabilityType> emptyList;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (!this.jsr305State.getDisabled() && (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) != null) {
            if (!annotationClass.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_DEFAULT_FQNAME())) {
                annotationClass = null;
            }
            if (annotationClass != null) {
                ClassDescriptor annotationClass2 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
                if (annotationClass2 == null) {
                    Intrinsics.throwNpe();
                }
                AnnotationDescriptor mo5455findAnnotation = annotationClass2.getAnnotations().mo5455findAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_DEFAULT_FQNAME());
                if (mo5455findAnnotation == null) {
                    Intrinsics.throwNpe();
                }
                Map<Name, ConstantValue<?>> allValueArguments = mo5455findAnnotation.getAllValueArguments();
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<Name, ConstantValue<?>> entry : allValueArguments.entrySet()) {
                    Name key = entry.getKey();
                    ConstantValue<?> value = entry.getValue();
                    if (Intrinsics.areEqual(key, JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                        emptyList = mapConstantToQualifierApplicabilityTypes(value);
                    } else {
                        emptyList = CollectionsKt.emptyList();
                    }
                    CollectionsKt.addAll(arrayList, emptyList);
                }
                Iterator it = arrayList.iterator();
                int i = 0;
                while (it.hasNext()) {
                    i |= 1 << ((QualifierApplicabilityType) it.next()).ordinal();
                }
                Iterator<AnnotationDescriptor> it2 = annotationClass.getAnnotations().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        annotationDescriptor2 = null;
                        break;
                    }
                    annotationDescriptor2 = it2.next();
                    if (resolveTypeQualifierAnnotation(annotationDescriptor2) != null) {
                        break;
                    }
                }
                AnnotationDescriptor annotationDescriptor3 = annotationDescriptor2;
                if (annotationDescriptor3 != null) {
                    return new TypeQualifierWithApplicability(annotationDescriptor3, i);
                }
            }
        }
        return null;
    }

    public final ReportLevel resolveJsr305AnnotationState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        ReportLevel resolveJsr305CustomState = resolveJsr305CustomState(annotationDescriptor);
        return resolveJsr305CustomState != null ? resolveJsr305CustomState : this.jsr305State.getGlobal();
    }

    public final ReportLevel resolveJsr305CustomState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        Map<String, ReportLevel> user = this.jsr305State.getUser();
        FqName fqName = annotationDescriptor.getFqName();
        ReportLevel reportLevel = user.get(fqName != null ? fqName.asString() : null);
        if (reportLevel != null) {
            return reportLevel;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass != null) {
            return migrationAnnotationStatus(annotationClass);
        }
        return null;
    }

    private final ReportLevel migrationAnnotationStatus(ClassDescriptor classDescriptor) {
        AnnotationDescriptor mo5455findAnnotation = classDescriptor.getAnnotations().mo5455findAnnotation(AnnotationTypeQualifierResolverKt.getMIGRATION_ANNOTATION_FQNAME());
        ConstantValue<?> firstArgument = mo5455findAnnotation != null ? DescriptorUtilsKt.firstArgument(mo5455findAnnotation) : null;
        if (!(firstArgument instanceof EnumValue)) {
            firstArgument = null;
        }
        EnumValue enumValue = (EnumValue) firstArgument;
        if (enumValue == null) {
            return null;
        }
        ReportLevel migration = this.jsr305State.getMigration();
        if (migration != null) {
            return migration;
        }
        String asString = enumValue.getEnumEntryName().asString();
        int hashCode = asString.hashCode();
        if (hashCode == -2137067054) {
            if (asString.equals("IGNORE")) {
                return ReportLevel.IGNORE;
            }
            return null;
        }
        if (hashCode == -1838656823) {
            if (asString.equals("STRICT")) {
                return ReportLevel.STRICT;
            }
            return null;
        }
        if (hashCode == 2656902 && asString.equals("WARN")) {
            return ReportLevel.WARN;
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final List<QualifierApplicabilityType> mapConstantToQualifierApplicabilityTypes(ConstantValue<?> constantValue) {
        QualifierApplicabilityType qualifierApplicabilityType;
        if (constantValue instanceof ArrayValue) {
            List<? extends ConstantValue<?>> value = ((ArrayValue) constantValue).getValue();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = value.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, mapConstantToQualifierApplicabilityTypes((ConstantValue) it.next()));
            }
            return arrayList;
        }
        if (!(constantValue instanceof EnumValue)) {
            return CollectionsKt.emptyList();
        }
        String identifier = ((EnumValue) constantValue).getEnumEntryName().getIdentifier();
        switch (identifier.hashCode()) {
            case -2024225567:
                if (identifier.equals("METHOD")) {
                    qualifierApplicabilityType = QualifierApplicabilityType.METHOD_RETURN_TYPE;
                    break;
                }
                qualifierApplicabilityType = null;
                break;
            case 66889946:
                if (identifier.equals("FIELD")) {
                    qualifierApplicabilityType = QualifierApplicabilityType.FIELD;
                    break;
                }
                qualifierApplicabilityType = null;
                break;
            case 107598562:
                if (identifier.equals("TYPE_USE")) {
                    qualifierApplicabilityType = QualifierApplicabilityType.TYPE_USE;
                    break;
                }
                qualifierApplicabilityType = null;
                break;
            case 446088073:
                if (identifier.equals("PARAMETER")) {
                    qualifierApplicabilityType = QualifierApplicabilityType.VALUE_PARAMETER;
                    break;
                }
                qualifierApplicabilityType = null;
                break;
            default:
                qualifierApplicabilityType = null;
                break;
        }
        return CollectionsKt.listOfNotNull(qualifierApplicabilityType);
    }

    public final boolean getDisabled() {
        return this.disabled;
    }
}
