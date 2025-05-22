package com.pudutech.freeinstall_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
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

/* compiled from: MapListViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapListViewModel$isShouldUpdate$1", m3970f = "MapListViewModel.kt", m3971i = {0, 1, 1, 1}, m3972l = {117, 120}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "mapList", "localMapList"}, m3975s = {"L$0", "L$0", "L$1", "L$2"})
/* loaded from: classes2.dex */
final class MapListViewModel$isShouldUpdate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5287p$;
    final /* synthetic */ MapListViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapListViewModel$isShouldUpdate$1(MapListViewModel mapListViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapListViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapListViewModel$isShouldUpdate$1 mapListViewModel$isShouldUpdate$1 = new MapListViewModel$isShouldUpdate$1(this.this$0, completion);
        mapListViewModel$isShouldUpdate$1.f5287p$ = (CoroutineScope) obj;
        return mapListViewModel$isShouldUpdate$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapListViewModel$isShouldUpdate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        MutableLiveData mutableLiveData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5287p$;
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = mapUpdateManager.getMapList(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    mutableLiveData = (MutableLiveData) this.L$3;
                    ResultKt.throwOnFailure(obj);
                    mutableLiveData.postValue(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        List<RobotMapResp> list = (List) obj;
        List<MapInfo> localMapList = MapUpdateManager.INSTANCE.getLocalMapList();
        Pdlog.m3273d("javaClass", localMapList);
        MutableLiveData<Boolean> isShouldUpdate = this.this$0.isShouldUpdate();
        MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
        this.L$0 = coroutineScope;
        this.L$1 = list;
        this.L$2 = localMapList;
        this.L$3 = isShouldUpdate;
        this.label = 2;
        obj = mapUpdateManager2.isShouldUpdate(list, localMapList, this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        mutableLiveData = isShouldUpdate;
        mutableLiveData.postValue(obj);
        return Unit.INSTANCE;
    }
}
