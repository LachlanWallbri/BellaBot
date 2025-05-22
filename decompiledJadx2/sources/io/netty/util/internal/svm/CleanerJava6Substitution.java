package io.netty.util.internal.svm;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.TargetClass;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@TargetClass(className = "io.netty.util.internal.CleanerJava6")
/* loaded from: classes8.dex */
final class CleanerJava6Substitution {

    @RecomputeFieldValue(declClassName = "java.nio.DirectByteBuffer", kind = RecomputeFieldValue.Kind.FieldOffset, name = "cleaner")
    @Alias
    private static long CLEANER_FIELD_OFFSET;

    private CleanerJava6Substitution() {
    }
}
