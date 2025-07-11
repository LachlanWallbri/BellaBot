package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KotlinJvmBinarySourceElement.kt */
/* loaded from: classes2.dex */
public final class KotlinJvmBinarySourceElement implements DeserializedContainerSource {
    private final KotlinJvmBinaryClass binaryClass;
    private final IncompatibleVersionErrorData<JvmMetadataVersion> incompatibility;
    private final boolean isPreReleaseInvisible;

    public KotlinJvmBinarySourceElement(KotlinJvmBinaryClass binaryClass, IncompatibleVersionErrorData<JvmMetadataVersion> incompatibleVersionErrorData, boolean z) {
        Intrinsics.checkParameterIsNotNull(binaryClass, "binaryClass");
        this.binaryClass = binaryClass;
        this.incompatibility = incompatibleVersionErrorData;
        this.isPreReleaseInvisible = z;
    }

    public final KotlinJvmBinaryClass getBinaryClass() {
        return this.binaryClass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource
    public String getPresentableString() {
        return "Class '" + this.binaryClass.getClassId().asSingleFqName().asString() + '\'';
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
    public SourceFile getContainingFile() {
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        Intrinsics.checkExpressionValueIsNotNull(sourceFile, "SourceFile.NO_SOURCE_FILE");
        return sourceFile;
    }

    public String toString() {
        return getClass().getSimpleName() + ": " + this.binaryClass;
    }
}
