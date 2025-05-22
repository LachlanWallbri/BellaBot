package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
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
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapDownloadViewModel$reloadMap$1", m3970f = "MapDownloadViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class MapDownloadViewModel$reloadMap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RobotMapResp $robotMapResp;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5279p$;
    final /* synthetic */ MapDownloadViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapDownloadViewModel$reloadMap$1(MapDownloadViewModel mapDownloadViewModel, RobotMapResp robotMapResp, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapDownloadViewModel;
        this.$robotMapResp = robotMapResp;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapDownloadViewModel$reloadMap$1 mapDownloadViewModel$reloadMap$1 = new MapDownloadViewModel$reloadMap$1(this.this$0, this.$robotMapResp, completion);
        mapDownloadViewModel$reloadMap$1.f5279p$ = (CoroutineScope) obj;
        return mapDownloadViewModel$reloadMap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapDownloadViewModel$reloadMap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5279p$;
        MapUpdateManager.INSTANCE.reloadMap(this.$robotMapResp.getName(), new Function1<String, Unit>() { // from class: com.pudutech.freeinstall_ui.viewmodel.MapDownloadViewModel$reloadMap$1.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                MapDownloadViewModel$reloadMap$1.this.this$0.isReload().postValue(str);
            }
        });
        return Unit.INSTANCE;
    }
}
