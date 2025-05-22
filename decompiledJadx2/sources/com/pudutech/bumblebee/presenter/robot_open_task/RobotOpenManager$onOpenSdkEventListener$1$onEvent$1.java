package com.pudutech.bumblebee.presenter.robot_open_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallFromType;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.BeeperAction;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.BeeperTaskListener;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.bean.CallBody;
import com.pudutech.robot.opensdk.bean.Destination;
import com.pudutech.robot.opensdk.bean.resp.ErrorCode;
import com.pudutech.robot.opensdk.bean.resp.RespResultBody;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RobotOpenManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$1", m3970f = "RobotOpenManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class RobotOpenManager$onOpenSdkEventListener$1$onEvent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MsgContext $msgContext;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4707p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotOpenManager$onOpenSdkEventListener$1$onEvent$1(MsgContext msgContext, Continuation continuation) {
        super(2, continuation);
        this.$msgContext = msgContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotOpenManager$onOpenSdkEventListener$1$onEvent$1 robotOpenManager$onOpenSdkEventListener$1$onEvent$1 = new RobotOpenManager$onOpenSdkEventListener$1$onEvent$1(this.$msgContext, completion);
        robotOpenManager$onOpenSdkEventListener$1$onEvent$1.f4707p$ = (CoroutineScope) obj;
        return robotOpenManager$onOpenSdkEventListener$1$onEvent$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotOpenManager$onOpenSdkEventListener$1$onEvent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Destination destination;
        String name;
        String str;
        boolean addCallTask;
        ListenerList beeperListenerList;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4707p$;
            MsgContext msgContext = this.$msgContext;
            if (msgContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.MsgContext<com.pudutech.robot.opensdk.bean.CallBody>");
            }
            CallBody callBody = (CallBody) msgContext.getReqData();
            if (callBody != null && (destination = callBody.getDestination()) != null && (name = destination.getName()) != null) {
                if (RobotMapManager.INSTANCE.checkDestinationExist(name)) {
                    RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                    MsgContext msgContext2 = this.$msgContext;
                    if (msgContext2 != null) {
                        addCallTask = robotOpenManager.addCallTask(msgContext2);
                        if (addCallTask) {
                            final String canRunCallTask$module_bumblebee_presenter_robotRelease = RobotOpenManager.INSTANCE.getCanRunCallTask$module_bumblebee_presenter_robotRelease();
                            String str2 = canRunCallTask$module_bumblebee_presenter_robotRelease;
                            if (!(str2 == null || str2.length() == 0)) {
                                beeperListenerList = RobotOpenManager.INSTANCE.getBeeperListenerList();
                                beeperListenerList.forEach(new Function1<BeeperTaskListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$1$1$1
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(BeeperTaskListener beeperTaskListener) {
                                        invoke2(beeperTaskListener);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(BeeperTaskListener it) {
                                        Intrinsics.checkParameterIsNotNull(it, "it");
                                        it.onTask(BeeperAction.CALL, canRunCallTask$module_bumblebee_presenter_robotRelease, CallFromType.OPEN_API);
                                    }
                                });
                            }
                            RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, this.$msgContext, new RespResultBody(true, null, null, 6, null), null, 4, null);
                        } else {
                            RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, this.$msgContext, new RespResultBody(false, Boxing.boxInt(ErrorCode.getCALL_FAILED_TARGET_REPEAT()), null, 4, null), null, 4, null);
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.MsgContext<com.pudutech.robot.opensdk.bean.CallBody>");
                    }
                } else {
                    RobotOpenManager robotOpenManager2 = RobotOpenManager.INSTANCE;
                    str = RobotOpenManager.TAG;
                    Pdlog.m3274e(str, "onEvent : EVENT_CALL , do not has table");
                    RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, this.$msgContext, new RespResultBody(false, Boxing.boxInt(ErrorCode.getCALL_FAILED_NO_TARGET()), null, 4, null), null, 4, null);
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
