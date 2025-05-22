package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ClassLiteralValue.kt */
/* loaded from: classes2.dex */
public final class ClassLiteralValue {
    private final int arrayNestedness;
    private final ClassId classId;

    public final ClassId component1() {
        return this.classId;
    }

    public final int component2() {
        return this.arrayNestedness;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof ClassLiteralValue) {
                ClassLiteralValue classLiteralValue = (ClassLiteralValue) obj;
                if (Intrinsics.areEqual(this.classId, classLiteralValue.classId)) {
                    if (this.arrayNestedness == classLiteralValue.arrayNestedness) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        ClassId classId = this.classId;
        return ((classId != null ? classId.hashCode() : 0) * 31) + this.arrayNestedness;
    }

    public ClassLiteralValue(ClassId classId, int i) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        this.classId = classId;
        this.arrayNestedness = i;
    }

    public final int getArrayNestedness() {
        return this.arrayNestedness;
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.arrayNestedness;
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("kotlin/Array<");
        }
        sb.append(this.classId);
        int i3 = this.arrayNestedness;
        for (int i4 = 0; i4 < i3; i4++) {
            sb.append(">");
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
