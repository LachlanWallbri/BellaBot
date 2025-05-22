package kotlin.coroutines.jvm.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  BellFree.apk:DebugProbesKt.bin
  PuduBumblebeeApp.apk:DebugProbesKt.bin
  classes.dex
  classes2.dex
  classes2.dex
  classes7.dex
  classes8.dex
  mapify.apk:DebugProbesKt.bin
 */
/* compiled from: DebugProbes.kt */
@Metadata(m3963mv = {1, 1, 16}, m3959bv = {1, 0, 3}, m3962k = 2, m3960d1 = {"��\u0012\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\"\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H��\u001a\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H��\u001a\u0014\u0010\u0007\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H��¨\u0006\b"}, m3961d2 = {"probeCoroutineCreated", "Lkotlin/coroutines/Continuation;", ExifInterface.GPS_DIRECTION_TRUE, "completion", "probeCoroutineResumed", "", TypedValues.Attributes.S_FRAME, "probeCoroutineSuspended", "integration-testing_debugAgentTest"})
/* loaded from: 出厂测试.apk:DebugProbesKt.bin */
public final class DebugProbesKt {
    @NotNull
    public static final <T> Continuation<T> probeCoroutineCreated(@NotNull Continuation<? super T> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        return DebugProbesImpl.INSTANCE.probeCoroutineCreated$kotlinx_coroutines_core(completion);
    }

    public static final void probeCoroutineResumed(@NotNull Continuation<?> frame) {
        Intrinsics.checkParameterIsNotNull(frame, "frame");
        DebugProbesImpl.INSTANCE.probeCoroutineResumed$kotlinx_coroutines_core(frame);
    }

    public static final void probeCoroutineSuspended(@NotNull Continuation<?> frame) {
        Intrinsics.checkParameterIsNotNull(frame, "frame");
        DebugProbesImpl.INSTANCE.probeCoroutineSuspended$kotlinx_coroutines_core(frame);
    }
}
