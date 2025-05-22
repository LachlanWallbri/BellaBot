package io.grpc.netty.shaded.io.netty.util.internal.svm;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.TargetClass;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@TargetClass(className = "io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent")
/* loaded from: classes7.dex */
final class PlatformDependentSubstitution {

    @RecomputeFieldValue(declClass = byte[].class, kind = RecomputeFieldValue.Kind.ArrayBaseOffset)
    @Alias
    private static long BYTE_ARRAY_BASE_OFFSET;

    private PlatformDependentSubstitution() {
    }
}
