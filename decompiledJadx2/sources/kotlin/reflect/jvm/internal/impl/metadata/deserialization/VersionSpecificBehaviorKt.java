package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: versionSpecificBehavior.kt */
/* loaded from: classes2.dex */
public final class VersionSpecificBehaviorKt {
    public static final boolean isVersionRequirementTableWrittenCorrectly(BinaryVersion version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        return isKotlin1Dot4OrLater(version);
    }

    public static final boolean isKotlin1Dot4OrLater(BinaryVersion version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        return version.getMajor() == 1 && version.getMinor() >= 4;
    }
}
