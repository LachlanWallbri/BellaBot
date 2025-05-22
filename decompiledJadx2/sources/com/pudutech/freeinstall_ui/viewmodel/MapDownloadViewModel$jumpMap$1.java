package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
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

/* compiled from: MapDownloadViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.MapDownloadViewModel$jumpMap$1", m3970f = "MapDownloadViewModel.kt", m3971i = {0, 0}, m3972l = {169}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "analyMap"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes2.dex */
final class MapDownloadViewModel$jumpMap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RobotMapResp $item;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5278p$;
    final /* synthetic */ MapDownloadViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapDownloadViewModel$jumpMap$1(MapDownloadViewModel mapDownloadViewModel, RobotMapResp robotMapResp, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mapDownloadViewModel;
        this.$item = robotMapResp;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapDownloadViewModel$jumpMap$1 mapDownloadViewModel$jumpMap$1 = new MapDownloadViewModel$jumpMap$1(this.this$0, this.$item, completion);
        mapDownloadViewModel$jumpMap$1.f5278p$ = (CoroutineScope) obj;
        return mapDownloadViewModel$jumpMap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapDownloadViewModel$jumpMap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5278p$;
            List<Destination> analyMap = MapingFuntionManager.INSTANCE.analyMap(MapUpdateManager.INSTANCE.getMapFile(this.$item.getName()));
            Pdlog.m3274e("JUMP", this.$item);
            List<Destination> list = analyMap;
            if (list == null || list.isEmpty()) {
                this.this$0.getToast().postValue("无法获取该地图相应信息");
            } else {
                Pdlog.m3274e("localClassName", analyMap);
                MapDownloadViewModel mapDownloadViewModel = this.this$0;
                RobotMapResp robotMapResp = this.$item;
                this.L$0 = coroutineScope;
                this.L$1 = analyMap;
                this.label = 1;
                if (mapDownloadViewModel.setMapData(analyMap, robotMapResp, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
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
