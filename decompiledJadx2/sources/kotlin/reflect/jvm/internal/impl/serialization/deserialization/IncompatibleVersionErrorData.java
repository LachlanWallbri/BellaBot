package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: IncompatibleVersionErrorData.kt */
/* loaded from: classes2.dex */
public final class IncompatibleVersionErrorData<T extends BinaryVersion> {
    private final T actualVersion;
    private final ClassId classId;
    private final T expectedVersion;
    private final String filePath;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IncompatibleVersionErrorData)) {
            return false;
        }
        IncompatibleVersionErrorData incompatibleVersionErrorData = (IncompatibleVersionErrorData) obj;
        return Intrinsics.areEqual(this.actualVersion, incompatibleVersionErrorData.actualVersion) && Intrinsics.areEqual(this.expectedVersion, incompatibleVersionErrorData.expectedVersion) && Intrinsics.areEqual(this.filePath, incompatibleVersionErrorData.filePath) && Intrinsics.areEqual(this.classId, incompatibleVersionErrorData.classId);
    }

    public int hashCode() {
        T t = this.actualVersion;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        T t2 = this.expectedVersion;
        int hashCode2 = (hashCode + (t2 != null ? t2.hashCode() : 0)) * 31;
        String str = this.filePath;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        ClassId classId = this.classId;
        return hashCode3 + (classId != null ? classId.hashCode() : 0);
    }

    public String toString() {
        return "IncompatibleVersionErrorData(actualVersion=" + this.actualVersion + ", expectedVersion=" + this.expectedVersion + ", filePath=" + this.filePath + ", classId=" + this.classId + ")";
    }

    public IncompatibleVersionErrorData(T actualVersion, T expectedVersion, String filePath, ClassId classId) {
        Intrinsics.checkParameterIsNotNull(actualVersion, "actualVersion");
        Intrinsics.checkParameterIsNotNull(expectedVersion, "expectedVersion");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        this.actualVersion = actualVersion;
        this.expectedVersion = expectedVersion;
        this.filePath = filePath;
        this.classId = classId;
    }
}
