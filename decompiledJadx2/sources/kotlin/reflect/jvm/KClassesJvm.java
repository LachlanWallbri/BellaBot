package kotlin.reflect.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.KClassImpl;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KClassesJvm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"jvmName", "", "Lkotlin/reflect/KClass;", "getJvmName", "(Lkotlin/reflect/KClass;)Ljava/lang/String;", "kotlin-reflection"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class KClassesJvm {
    public static final String getJvmName(KClass<?> jvmName) {
        Intrinsics.checkParameterIsNotNull(jvmName, "$this$jvmName");
        String name = ((KClassImpl) jvmName).getJClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "(this as KClassImpl).jClass.name");
        return name;
    }
}
