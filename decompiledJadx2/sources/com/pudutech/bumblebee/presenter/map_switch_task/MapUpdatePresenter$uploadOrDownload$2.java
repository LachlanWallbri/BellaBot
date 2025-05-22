package com.pudutech.bumblebee.presenter.map_switch_task;

import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdatePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$uploadOrDownload$2", m3970f = "MapUpdatePresenter.kt", m3971i = {0, 0, 1}, m3972l = {184, 186}, m3973m = "invokeSuspend", m3974n = {"$this$withContext", "downloadMap", "$this$withContext"}, m3975s = {"L$0", "L$1", "L$0"})
/* loaded from: classes4.dex */
public final class MapUpdatePresenter$uploadOrDownload$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4695p$;
    final /* synthetic */ MapUpdatePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdatePresenter$uploadOrDownload$2(MapUpdatePresenter mapUpdatePresenter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapUpdatePresenter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapUpdatePresenter$uploadOrDownload$2 mapUpdatePresenter$uploadOrDownload$2 = new MapUpdatePresenter$uploadOrDownload$2(this.this$0, completion);
        mapUpdatePresenter$uploadOrDownload$2.f4695p$ = (CoroutineScope) obj;
        return mapUpdatePresenter$uploadOrDownload$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapUpdatePresenter$uploadOrDownload$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List list;
        List list2;
        List list3;
        List list4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
            } else if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4695p$;
            list = this.this$0.downloadMapList;
            if (list.size() > 0) {
                list4 = this.this$0.downloadMapList;
                RobotMapResp robotMapResp = (RobotMapResp) list4.remove(0);
                this.this$0.currentDownloadMap = robotMapResp.getName();
                MapUpdatePresenter mapUpdatePresenter = this.this$0;
                this.L$0 = coroutineScope;
                this.L$1 = robotMapResp;
                this.label = 1;
                if (mapUpdatePresenter.downloadMap(robotMapResp, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                list2 = this.this$0.uploadMapList;
                if (list2.size() > 0) {
                    MapUpdatePresenter mapUpdatePresenter2 = this.this$0;
                    list3 = mapUpdatePresenter2.uploadMapList;
                    MapInfo mapInfo = (MapInfo) list3.remove(0);
                    this.L$0 = coroutineScope;
                    this.label = 2;
                    if (mapUpdatePresenter2.uploadMap(mapInfo, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }
}
