package androidx.lifecycle;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CoroutineLiveData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0080@"}, m3961d2 = {"emitSource", "", ExifInterface.GPS_DIRECTION_TRUE, MapElement.Source.SOURCE, "Landroidx/lifecycle/LiveData;", "continuation", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/DisposableHandle;"}, m3962k = 3, m3963mv = {1, 1, 15})
@DebugMetadata(m3969c = "androidx.lifecycle.CoroutineLiveData", m3970f = "CoroutineLiveData.kt", m3971i = {0, 0, 1, 1}, m3972l = {227, 228}, m3973m = "emitSource$lifecycle_livedata_ktx_release", m3974n = {"this", MapElement.Source.SOURCE, "this", MapElement.Source.SOURCE}, m3975s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes.dex */
public final class CoroutineLiveData$emitSource$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CoroutineLiveData this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineLiveData$emitSource$1(CoroutineLiveData coroutineLiveData, Continuation continuation) {
        super(continuation);
        this.this$0 = coroutineLiveData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emitSource$lifecycle_livedata_ktx_release(null, this);
    }
}
