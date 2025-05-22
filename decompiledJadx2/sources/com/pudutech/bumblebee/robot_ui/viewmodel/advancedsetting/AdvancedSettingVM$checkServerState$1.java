package com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting;

import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.RobotActiveStatusReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.RobotActiveStatusResp;
import com.pudutech.pd_network.PdNetworkManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdvancedSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/disinfect/baselib/network/response/ApiResponse;", "Lcom/pudutech/disinfect/baselib/network/response/RobotActiveStatusResp;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.AdvancedSettingVM$checkServerState$1", m3970f = "AdvancedSettingVM.kt", m3971i = {0}, m3972l = {84, 84}, m3973m = "invokeSuspend", m3974n = {"$this$apply"}, m3975s = {"L$1"})
/* loaded from: classes4.dex */
final class AdvancedSettingVM$checkServerState$1 extends SuspendLambda implements Function1<Continuation<? super ApiResponse<RobotActiveStatusResp>>, Object> {
    final /* synthetic */ String $host;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdvancedSettingVM$checkServerState$1(String str, Continuation continuation) {
        super(1, continuation);
        this.$host = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        return new AdvancedSettingVM$checkServerState$1(this.$host, completion);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super ApiResponse<RobotActiveStatusResp>> continuation) {
        return ((AdvancedSettingVM$checkServerState$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        RobotActiveStatusReq robotActiveStatusReq;
        RobotActiveStatusReq robotActiveStatusReq2;
        NetWorkApiManager.CloudApiService cloudApiService;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
            str = this.$host + "/api/common/robot_life/active/v2";
            robotActiveStatusReq = new RobotActiveStatusReq();
            PdNetworkManager pdNetworkManager = PdNetworkManager.f10310INSTANCE;
            this.L$0 = robotActiveStatusReq;
            this.L$1 = robotActiveStatusReq;
            this.L$2 = robotActiveStatusReq;
            this.L$3 = cloudApi;
            this.L$4 = str;
            this.label = 1;
            Object hardID = pdNetworkManager.hardID(this);
            if (hardID == coroutine_suspended) {
                return coroutine_suspended;
            }
            robotActiveStatusReq2 = robotActiveStatusReq;
            cloudApiService = cloudApi;
            obj = hardID;
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str = (String) this.L$4;
            cloudApiService = (NetWorkApiManager.CloudApiService) this.L$3;
            robotActiveStatusReq = (RobotActiveStatusReq) this.L$2;
            robotActiveStatusReq2 = (RobotActiveStatusReq) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        robotActiveStatusReq.setHard_id((String) obj);
        this.label = 2;
        obj = cloudApiService.checkRobotActiveStatusFromServer(str, robotActiveStatusReq2, this);
        return obj == coroutine_suspended ? coroutine_suspended : obj;
    }
}
