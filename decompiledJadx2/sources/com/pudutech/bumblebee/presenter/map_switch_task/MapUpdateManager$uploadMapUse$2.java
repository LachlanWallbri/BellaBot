package com.pudutech.bumblebee.presenter.map_switch_task;

import com.google.logging.type.LogSeverity;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.ReqRobotMac;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$uploadMapUse$2", m3970f = "MapUpdateManager.kt", m3971i = {0}, m3972l = {LogSeverity.EMERGENCY_VALUE}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class MapUpdateManager$uploadMapUse$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $name;
    final /* synthetic */ Function1 $result;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4683p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapUpdateManager$uploadMapUse$2(String str, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$name = str;
        this.$result = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MapUpdateManager$uploadMapUse$2 mapUpdateManager$uploadMapUse$2 = new MapUpdateManager$uploadMapUse$2(this.$name, this.$result, completion);
        mapUpdateManager$uploadMapUse$2.f4683p$ = (CoroutineScope) obj;
        return mapUpdateManager$uploadMapUse$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MapUpdateManager$uploadMapUse$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0075 A[Catch: Exception -> 0x00a6, TryCatch #0 {Exception -> 0x00a6, blocks: (B:6:0x0011, B:7:0x0051, B:8:0x0055, B:10:0x0075, B:11:0x007f, B:13:0x0085, B:17:0x009a, B:19:0x009e, B:20:0x00a2, B:25:0x0022, B:27:0x002a, B:30:0x0037), top: B:2:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0085 A[Catch: Exception -> 0x00a6, TryCatch #0 {Exception -> 0x00a6, blocks: (B:6:0x0011, B:7:0x0051, B:8:0x0055, B:10:0x0075, B:11:0x007f, B:13:0x0085, B:17:0x009a, B:19:0x009e, B:20:0x00a2, B:25:0x0022, B:27:0x002a, B:30:0x0037), top: B:2:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x009a A[Catch: Exception -> 0x00a6, TryCatch #0 {Exception -> 0x00a6, blocks: (B:6:0x0011, B:7:0x0051, B:8:0x0055, B:10:0x0075, B:11:0x007f, B:13:0x0085, B:17:0x009a, B:19:0x009e, B:20:0x00a2, B:25:0x0022, B:27:0x002a, B:30:0x0037), top: B:2:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007e  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        ApiResponse apiResponse;
        String str2;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Exception e) {
            MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
            str = MapUpdateManager.TAG;
            Pdlog.m3273d(str, "uploadMapUse=Error:" + e.getMessage());
            this.$result.invoke(e.getMessage());
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4683p$;
            NetWorkApiManager.MapManagerService map = NetWorkApiManager.INSTANCE.getMap();
            if (map != null) {
                String mac = WifiUtil.INSTANCE.getMac();
                if (mac == null) {
                    mac = "";
                }
                ReqRobotMac reqRobotMac = new ReqRobotMac(mac, CollectionsKt.arrayListOf(this.$name));
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = map.switchMap(reqRobotMac, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                apiResponse = null;
                MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
                str2 = MapUpdateManager.TAG;
                Pdlog.m3273d(str2, "uploadMapUse=" + apiResponse);
                if ((apiResponse == null ? Boxing.boxInt(apiResponse.getCode()) : null).intValue() != 0) {
                    MapUpdateManager mapUpdateManager3 = MapUpdateManager.INSTANCE;
                    str3 = MapUpdateManager.TAG;
                    Pdlog.m3273d(str3, "uploadMapUse=上报成功");
                    this.$result.invoke(null);
                } else {
                    this.$result.invoke(apiResponse != null ? apiResponse.getMsg() : null);
                }
                return Unit.INSTANCE;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        apiResponse = (ApiResponse) obj;
        MapUpdateManager mapUpdateManager22 = MapUpdateManager.INSTANCE;
        str2 = MapUpdateManager.TAG;
        Pdlog.m3273d(str2, "uploadMapUse=" + apiResponse);
        if ((apiResponse == null ? Boxing.boxInt(apiResponse.getCode()) : null).intValue() != 0) {
        }
        return Unit.INSTANCE;
    }
}
