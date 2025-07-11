package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: JavaTypeResolver.kt */
/* loaded from: classes2.dex */
public final class JavaTypeAttributes {
    private final JavaTypeFlexibility flexibility;
    private final TypeUsage howThisTypeIsUsed;
    private final boolean isForAnnotationParameter;
    private final TypeParameterDescriptor upperBoundOfTypeParameter;

    public static /* synthetic */ JavaTypeAttributes copy$default(JavaTypeAttributes javaTypeAttributes, TypeUsage typeUsage, JavaTypeFlexibility javaTypeFlexibility, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, Object obj) {
        if ((i & 1) != 0) {
            typeUsage = javaTypeAttributes.howThisTypeIsUsed;
        }
        if ((i & 2) != 0) {
            javaTypeFlexibility = javaTypeAttributes.flexibility;
        }
        if ((i & 4) != 0) {
            z = javaTypeAttributes.isForAnnotationParameter;
        }
        if ((i & 8) != 0) {
            typeParameterDescriptor = javaTypeAttributes.upperBoundOfTypeParameter;
        }
        return javaTypeAttributes.copy(typeUsage, javaTypeFlexibility, z, typeParameterDescriptor);
    }

    public final JavaTypeAttributes copy(TypeUsage howThisTypeIsUsed, JavaTypeFlexibility flexibility, boolean z, TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(howThisTypeIsUsed, "howThisTypeIsUsed");
        Intrinsics.checkParameterIsNotNull(flexibility, "flexibility");
        return new JavaTypeAttributes(howThisTypeIsUsed, flexibility, z, typeParameterDescriptor);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof JavaTypeAttributes) {
                JavaTypeAttributes javaTypeAttributes = (JavaTypeAttributes) obj;
                if (Intrinsics.areEqual(this.howThisTypeIsUsed, javaTypeAttributes.howThisTypeIsUsed) && Intrinsics.areEqual(this.flexibility, javaTypeAttributes.flexibility)) {
                    if (!(this.isForAnnotationParameter == javaTypeAttributes.isForAnnotationParameter) || !Intrinsics.areEqual(this.upperBoundOfTypeParameter, javaTypeAttributes.upperBoundOfTypeParameter)) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        TypeUsage typeUsage = this.howThisTypeIsUsed;
        int hashCode = (typeUsage != null ? typeUsage.hashCode() : 0) * 31;
        JavaTypeFlexibility javaTypeFlexibility = this.flexibility;
        int hashCode2 = (hashCode + (javaTypeFlexibility != null ? javaTypeFlexibility.hashCode() : 0)) * 31;
        boolean z = this.isForAnnotationParameter;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode2 + i) * 31;
        TypeParameterDescriptor typeParameterDescriptor = this.upperBoundOfTypeParameter;
        return i2 + (typeParameterDescriptor != null ? typeParameterDescriptor.hashCode() : 0);
    }

    public String toString() {
        return "JavaTypeAttributes(howThisTypeIsUsed=" + this.howThisTypeIsUsed + ", flexibility=" + this.flexibility + ", isForAnnotationParameter=" + this.isForAnnotationParameter + ", upperBoundOfTypeParameter=" + this.upperBoundOfTypeParameter + ")";
    }

    public JavaTypeAttributes(TypeUsage howThisTypeIsUsed, JavaTypeFlexibility flexibility, boolean z, TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(howThisTypeIsUsed, "howThisTypeIsUsed");
        Intrinsics.checkParameterIsNotNull(flexibility, "flexibility");
        this.howThisTypeIsUsed = howThisTypeIsUsed;
        this.flexibility = flexibility;
        this.isForAnnotationParameter = z;
        this.upperBoundOfTypeParameter = typeParameterDescriptor;
    }

    public final TypeUsage getHowThisTypeIsUsed() {
        return this.howThisTypeIsUsed;
    }

    public /* synthetic */ JavaTypeAttributes(TypeUsage typeUsage, JavaTypeFlexibility javaTypeFlexibility, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeUsage, (i & 2) != 0 ? JavaTypeFlexibility.INFLEXIBLE : javaTypeFlexibility, (i & 4) != 0 ? false : z, (i & 8) != 0 ? (TypeParameterDescriptor) null : typeParameterDescriptor);
    }

    public final JavaTypeFlexibility getFlexibility() {
        return this.flexibility;
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    public final TypeParameterDescriptor getUpperBoundOfTypeParameter() {
        return this.upperBoundOfTypeParameter;
    }

    public final JavaTypeAttributes withFlexibility(JavaTypeFlexibility flexibility) {
        Intrinsics.checkParameterIsNotNull(flexibility, "flexibility");
        return copy$default(this, null, flexibility, false, null, 13, null);
    }
}
