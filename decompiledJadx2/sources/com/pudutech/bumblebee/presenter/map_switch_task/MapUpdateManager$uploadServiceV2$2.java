package com.pudutech.bumblebee.presenter.map_switch_task;

import com.pudutech.bumblebee.business.oss.UpdateErrorSealed;
import com.pudutech.disinfect.baselib.network.req.RobotMapReqV2;
import com.pudutech.disinfect.baselib.network.response.MapUploadResponse;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$uploadServiceV2$2", m3970f = "MapUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class MapUpdateManager$uploadServiceV2$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RobotMapReqV2 $req;
    final /* synthetic */ Ref.ObjectRef $uploadMap;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4684p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdateManager$uploadServiceV2$2(Ref.ObjectRef objectRef, RobotMapReqV2 robotMapReqV2, Continuation continuation) {
        super(2, continuation);
        this.$uploadMap = objectRef;
        this.$req = robotMapReqV2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapUpdateManager$uploadServiceV2$2 mapUpdateManager$uploadServiceV2$2 = new MapUpdateManager$uploadServiceV2$2(this.$uploadMap, this.$req, completion);
        mapUpdateManager$uploadServiceV2$2.f4684p$ = (CoroutineScope) obj;
        return mapUpdateManager$uploadServiceV2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapUpdateManager$uploadServiceV2$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4684p$;
            MapUploadResponse mapUploadResponse = (MapUploadResponse) this.$uploadMap.element;
            if ((mapUploadResponse != null ? Boxing.boxInt(mapUploadResponse.getCode()) : null).intValue() == 0) {
                Function2<String, String, Unit> onSuccessListener = MapUpdateManager.INSTANCE.getOnSuccessListener();
                if (onSuccessListener != null) {
                    return onSuccessListener.invoke(this.$req.getName(), "TYPE_UPLOAD");
                }
                return null;
            }
            Function3<String, UpdateErrorSealed, String, Unit> onErrorListener = MapUpdateManager.INSTANCE.getOnErrorListener();
            if (onErrorListener != null) {
                return onErrorListener.invoke(this.$req.getName(), UpdateErrorSealed.CLOUD_SERVER_ERROR, "TYPE_UPLOAD");
            }
            return null;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
