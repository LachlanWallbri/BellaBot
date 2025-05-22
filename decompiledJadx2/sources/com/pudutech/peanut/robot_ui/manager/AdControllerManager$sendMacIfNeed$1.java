package com.pudutech.peanut.robot_ui.manager;

import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.ReqRobotMac;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.RespResultData;
import com.pudutech.peanut.presenter.utils.WifiUtil;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AdControllerManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.manager.AdControllerManager$sendMacIfNeed$1", m3970f = "AdControllerManager.kt", m3971i = {0, 0, 0, 0}, m3972l = {60}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "retry", "it", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes5.dex */
public final class AdControllerManager$sendMacIfNeed$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6995p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdControllerManager$sendMacIfNeed$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AdControllerManager$sendMacIfNeed$1 adControllerManager$sendMacIfNeed$1 = new AdControllerManager$sendMacIfNeed$1(completion);
        adControllerManager$sendMacIfNeed$1.f6995p$ = (CoroutineScope) obj;
        return adControllerManager$sendMacIfNeed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdControllerManager$sendMacIfNeed$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [kotlin.jvm.functions.Function0] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AdControllerManager$sendMacIfNeed$1$retry$1 adControllerManager$sendMacIfNeed$1$retry$1;
        String str;
        AdControllerManager$sendMacIfNeed$1$retry$1 adControllerManager$sendMacIfNeed$1$retry$12;
        String str2;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6995p$;
            adControllerManager$sendMacIfNeed$1$retry$1 = new Function0<Job>() { // from class: com.pudutech.peanut.robot_ui.manager.AdControllerManager$sendMacIfNeed$1$retry$1

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: AdControllerManager.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.manager.AdControllerManager$sendMacIfNeed$1$retry$1$1", m3970f = "AdControllerManager.kt", m3971i = {0}, m3972l = {49}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
                /* renamed from: com.pudutech.peanut.robot_ui.manager.AdControllerManager$sendMacIfNeed$1$retry$1$1 */
                /* loaded from: classes5.dex */
                public static final class C55171 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    Object L$0;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f6996p$;

                    C55171(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C55171 c55171 = new C55171(completion);
                        c55171.f6996p$ = (CoroutineScope) obj;
                        return c55171;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C55171) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.L$0 = this.f6996p$;
                            this.label = 1;
                            if (DelayKt.delay(10000L, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        AdControllerManager.INSTANCE.sendMacIfNeed();
                        return Unit.INSTANCE;
                    }
                }

                @Override // kotlin.jvm.functions.Function0
                public final Job invoke() {
                    Job launch$default;
                    launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C55171(null), 3, null);
                    return launch$default;
                }
            };
            try {
                if (WifiUtil.INSTANCE.getMac() == null) {
                    adControllerManager$sendMacIfNeed$1$retry$1.invoke();
                } else {
                    String mac = WifiUtil.INSTANCE.getMac();
                    if (mac != null) {
                        ReqRobotMac reqRobotMac = new ReqRobotMac(mac);
                        AdControllerManager adControllerManager = AdControllerManager.INSTANCE;
                        str = AdControllerManager.TAG;
                        Pdlog.m3273d(str, "sendMacIfNeed " + mac);
                        NetWorkApiManager.AdPlatformService adControl = NetWorkApiManager.INSTANCE.getAdControl();
                        this.L$0 = coroutineScope;
                        this.L$1 = adControllerManager$sendMacIfNeed$1$retry$1;
                        this.L$2 = mac;
                        this.L$3 = reqRobotMac;
                        this.label = 1;
                        obj = adControl.robotMac(reqRobotMac, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        adControllerManager$sendMacIfNeed$1$retry$12 = adControllerManager$sendMacIfNeed$1$retry$1;
                    }
                }
            } catch (Exception e) {
                e = e;
                AdControllerManager adControllerManager2 = AdControllerManager.INSTANCE;
                str2 = AdControllerManager.TAG;
                Pdlog.m3274e(str2, "sendMacIfNeed : " + Log.getStackTraceString(e));
                adControllerManager$sendMacIfNeed$1$retry$1.invoke();
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        if (i == 1) {
            ?? r0 = (Function0) this.L$1;
            try {
                ResultKt.throwOnFailure(obj);
                adControllerManager$sendMacIfNeed$1$retry$12 = r0;
            } catch (Exception e2) {
                e = e2;
                adControllerManager$sendMacIfNeed$1$retry$1 = r0;
                AdControllerManager adControllerManager22 = AdControllerManager.INSTANCE;
                str2 = AdControllerManager.TAG;
                Pdlog.m3274e(str2, "sendMacIfNeed : " + Log.getStackTraceString(e));
                adControllerManager$sendMacIfNeed$1$retry$1.invoke();
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ApiResponse apiResponse = (ApiResponse) obj;
        AdControllerManager adControllerManager3 = AdControllerManager.INSTANCE;
        str3 = AdControllerManager.TAG;
        Pdlog.m3273d(str3, "sendMacIfNeed " + apiResponse);
        if (((RespResultData) apiResponse.getData()).getSuccess()) {
            AdControllerManager adControllerManager4 = AdControllerManager.INSTANCE;
            AdControllerManager.hasSendMac = true;
        } else {
            adControllerManager$sendMacIfNeed$1$retry$12.invoke();
        }
        return Unit.INSTANCE;
    }
}
