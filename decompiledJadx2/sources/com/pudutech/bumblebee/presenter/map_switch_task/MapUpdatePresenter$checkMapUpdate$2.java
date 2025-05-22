package com.pudutech.bumblebee.presenter.map_switch_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateContract;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdatePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter$checkMapUpdate$2", m3970f = "MapUpdatePresenter.kt", m3971i = {0, 0}, m3972l = {219}, m3973m = "invokeSuspend", m3974n = {"$this$withContext", "localMapList"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes4.dex */
public final class MapUpdatePresenter$checkMapUpdate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4686p$;
    final /* synthetic */ MapUpdatePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdatePresenter$checkMapUpdate$2(MapUpdatePresenter mapUpdatePresenter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapUpdatePresenter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapUpdatePresenter$checkMapUpdate$2 mapUpdatePresenter$checkMapUpdate$2 = new MapUpdatePresenter$checkMapUpdate$2(this.this$0, completion);
        mapUpdatePresenter$checkMapUpdate$2.f4686p$ = (CoroutineScope) obj;
        return mapUpdatePresenter$checkMapUpdate$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((MapUpdatePresenter$checkMapUpdate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List<MapInfo> list;
        boolean z;
        MapUpdateContract.UpdateState checkCurrentMapUpdateState;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4686p$;
            List<MapInfo> mapList = RobotMapManager.INSTANCE.getMapList();
            Pdlog.m3273d(this.this$0.getTAG(), "checkMapUpdate localMapList:" + mapList);
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            this.L$0 = coroutineScope;
            this.L$1 = mapList;
            this.label = 1;
            obj = mapUpdateManager.getMapList(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            list = mapList;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            list = (List) this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        List list2 = (List) obj;
        Pdlog.m3273d(this.this$0.getTAG(), "checkMapUpdate serverMapList:" + list2);
        if (list2 == null) {
            return Boxing.boxBoolean(false);
        }
        if (list != null) {
            Iterator<T> it = list.iterator();
            z = false;
            while (it.hasNext()) {
                checkCurrentMapUpdateState = this.this$0.checkCurrentMapUpdateState((MapInfo) it.next(), list2);
                Pdlog.m3273d(this.this$0.getTAG(), "checkMapUpdate state: " + checkCurrentMapUpdateState);
                if (checkCurrentMapUpdateState == MapUpdateContract.UpdateState.UPLOAD || checkCurrentMapUpdateState == MapUpdateContract.UpdateState.DOWNLOAD) {
                    z = true;
                }
            }
        } else {
            z = false;
        }
        return Boxing.boxBoolean(z);
    }
}
