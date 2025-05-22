package kotlin.reflect.jvm.internal.impl.platform;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: platformUtil.kt */
/* loaded from: classes8.dex */
public final class PlatformUtilKt {
    public static final String getPresentableDescription(TargetPlatform presentableDescription) {
        Intrinsics.checkParameterIsNotNull(presentableDescription, "$this$presentableDescription");
        return CollectionsKt.joinToString$default(presentableDescription.getComponentPlatforms(), "/", null, null, 0, null, null, 62, null);
    }
}
