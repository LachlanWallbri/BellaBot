package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapDownloadViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapDownloadViewModel$getMapListData$1", m3970f = "MapDownloadViewModel.kt", m3971i = {0}, m3972l = {36}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class MapDownloadViewModel$getMapListData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5277p$;
    final /* synthetic */ MapDownloadViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapDownloadViewModel$getMapListData$1(MapDownloadViewModel mapDownloadViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapDownloadViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapDownloadViewModel$getMapListData$1 mapDownloadViewModel$getMapListData$1 = new MapDownloadViewModel$getMapListData$1(this.this$0, completion);
        mapDownloadViewModel$getMapListData$1.f5277p$ = (CoroutineScope) obj;
        return mapDownloadViewModel$getMapListData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapDownloadViewModel$getMapListData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5277p$;
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            Function1<List<RobotMapResp>, Unit> function1 = new Function1<List<RobotMapResp>, Unit>() { // from class: com.pudutech.freeinstall_ui.viewmodel.MapDownloadViewModel$getMapListData$1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<RobotMapResp> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<RobotMapResp> list) {
                    MapDownloadViewModel$getMapListData$1.this.this$0.getData().postValue(list);
                }
            };
            this.L$0 = coroutineScope;
            this.label = 1;
            if (mapUpdateManager.getMapList(function1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
