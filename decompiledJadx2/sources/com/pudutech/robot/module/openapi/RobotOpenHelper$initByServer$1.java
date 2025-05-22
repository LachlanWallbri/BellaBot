package com.pudutech.robot.module.openapi;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.IotHostReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.IotRegionResp;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotOpenHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.module.openapi.RobotOpenHelper$initByServer$1", m3970f = "RobotOpenHelper.kt", m3971i = {0, 1, 1, 1}, m3972l = {214, 218}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "resp", "appId"}, m3975s = {"L$0", "L$0", "L$1", "L$2"})
/* loaded from: classes6.dex */
public final class RobotOpenHelper$initByServer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7194p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotOpenHelper$initByServer$1(String str, Continuation continuation) {
        super(2, continuation);
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotOpenHelper$initByServer$1 robotOpenHelper$initByServer$1 = new RobotOpenHelper$initByServer$1(this.$mac, completion);
        robotOpenHelper$initByServer$1.f7194p$ = (CoroutineScope) obj;
        return robotOpenHelper$initByServer$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotOpenHelper$initByServer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0086 A[Catch: Exception -> 0x00ea, TryCatch #0 {Exception -> 0x00ea, blocks: (B:7:0x001b, B:8:0x00e4, B:15:0x002c, B:16:0x0060, B:18:0x0068, B:20:0x0070, B:21:0x0076, B:23:0x007a, B:28:0x0086, B:30:0x008e, B:31:0x0091, B:33:0x0099, B:34:0x009c, B:36:0x00ae, B:38:0x00bf, B:39:0x00c2, B:42:0x00cd, B:46:0x00dc, B:47:0x00e3, B:51:0x0035), top: B:2:0x0009 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        CoroutineScope coroutineScope;
        String str2;
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Exception e) {
            if (e instanceof IOException) {
                RobotOpenHelper.INSTANCE.setNetworkCallback();
            }
            RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
            str = RobotOpenHelper.TAG;
            Pdlog.m3274e(str, Log.getStackTraceString(e));
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7194p$;
            RobotOpenHelper robotOpenHelper2 = RobotOpenHelper.INSTANCE;
            str2 = RobotOpenHelper.TAG;
            Pdlog.m3273d(str2, "start getAliyunIOTSecret");
            NetWorkApiManager.NormalApiService normal = NetWorkApiManager.INSTANCE.getNormal();
            IotHostReq iotHostReq = new IotHostReq(this.$mac);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = NetWorkApiManager.NormalApiService.DefaultImpls.getEdgeHost$default(normal, null, iotHostReq, this, 1, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    RobotOpenHelper.INSTANCE.unRegisterNetworkCallback();
                    RobotOpenHelper robotOpenHelper3 = RobotOpenHelper.INSTANCE;
                    RobotOpenHelper.isGetIotSecretReqing = false;
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        ApiResponse apiResponse = (ApiResponse) obj;
        if (apiResponse.getCode() == 0) {
            IotRegionResp iotRegionResp = (IotRegionResp) apiResponse.getData();
            String regionId = iotRegionResp != null ? iotRegionResp.getRegionId() : null;
            if (regionId != null && !StringsKt.isBlank(regionId)) {
                z = false;
                if (!z) {
                    RobotOpenHelper robotOpenHelper4 = RobotOpenHelper.INSTANCE;
                    Object data = apiResponse.getData();
                    if (data == null) {
                        Intrinsics.throwNpe();
                    }
                    String regionId2 = ((IotRegionResp) data).getRegionId();
                    if (regionId2 == null) {
                        Intrinsics.throwNpe();
                    }
                    robotOpenHelper4.setRegion(regionId2);
                    String replace$default = StringsKt.replace$default(this.$mac, ":", "", false, 4, (Object) null);
                    if (replace$default == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String lowerCase = replace$default.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                    RobotOpenHelper robotOpenHelper5 = RobotOpenHelper.INSTANCE;
                    Object data2 = apiResponse.getData();
                    if (data2 == null) {
                        Intrinsics.throwNpe();
                    }
                    String endpoint = ((IotRegionResp) data2).getEndpoint();
                    if (endpoint == null) {
                        endpoint = "";
                    }
                    this.L$0 = coroutineScope;
                    this.L$1 = apiResponse;
                    this.L$2 = lowerCase;
                    this.label = 2;
                    if (robotOpenHelper5.startRegisterFromServer(lowerCase, endpoint, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            z = true;
            if (!z) {
            }
        }
        RobotOpenHelper.INSTANCE.unRegisterNetworkCallback();
        RobotOpenHelper robotOpenHelper32 = RobotOpenHelper.INSTANCE;
        RobotOpenHelper.isGetIotSecretReqing = false;
        return Unit.INSTANCE;
    }
}
