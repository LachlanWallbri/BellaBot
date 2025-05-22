package com.pudutech.bumblebee.presenter.robot_open_task;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.map.DestinationType;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.bean.PageBody;
import com.pudutech.robot.opensdk.bean.resp.RespDestinationsBody;
import com.pudutech.robot.opensdk.interf.IBody;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RobotOpenManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$2", m3970f = "RobotOpenManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class RobotOpenManager$onOpenSdkEventListener$1$onEvent$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MsgContext $msgContext;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4708p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotOpenManager$onOpenSdkEventListener$1$onEvent$2(MsgContext msgContext, Continuation continuation) {
        super(2, continuation);
        this.$msgContext = msgContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotOpenManager$onOpenSdkEventListener$1$onEvent$2 robotOpenManager$onOpenSdkEventListener$1$onEvent$2 = new RobotOpenManager$onOpenSdkEventListener$1$onEvent$2(this.$msgContext, completion);
        robotOpenManager$onOpenSdkEventListener$1$onEvent$2.f4708p$ = (CoroutineScope) obj;
        return robotOpenManager$onOpenSdkEventListener$1$onEvent$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotOpenManager$onOpenSdkEventListener$1$onEvent$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Integer boxInt;
        Integer boxInt2;
        String str2;
        IBody reqData;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4708p$;
        PageBody pageBody = (PageBody) null;
        try {
            reqData = this.$msgContext.getReqData();
        } catch (Exception unused) {
        }
        if (reqData == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.PageBody");
        }
        pageBody = (PageBody) reqData;
        ArrayList<Destination> allDestination = RobotMapManager.INSTANCE.getAllDestination();
        ArrayList arrayList = new ArrayList();
        if (allDestination != null) {
            for (Destination destination : allDestination) {
                String name = destination.getName();
                DestinationType type = destination.getType();
                if (type == null || (str2 = type.getTypeName()) == null) {
                    str2 = "";
                }
                arrayList.add(new com.pudutech.robot.opensdk.bean.Destination(name, str2));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int i = 1;
        if (pageBody == null) {
            arrayList2.addAll(arrayList);
        } else {
            int pageIndex = (pageBody.getPageIndex() - 1) * pageBody.getPageSize();
            int pageIndex2 = pageBody.getPageIndex() * pageBody.getPageSize();
            try {
                if (pageIndex + 1 <= arrayList.size()) {
                    if (pageIndex2 > arrayList.size()) {
                        arrayList2.addAll(arrayList.subList(pageIndex, arrayList.size()));
                    } else if (pageIndex == 0 && pageIndex2 == 0) {
                        arrayList2.addAll(arrayList);
                    } else {
                        arrayList2.addAll(arrayList.subList(pageIndex, pageIndex2));
                    }
                }
            } catch (Exception e) {
                RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                str = RobotOpenManager.TAG;
                Pdlog.m3274e(str, "onEvent MSG_TYPE_REQUEST_DATA : " + Log.getStackTraceString(e));
            }
        }
        RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
        MsgContext msgContext = this.$msgContext;
        int size = arrayList.size();
        int intValue = (pageBody == null || (boxInt2 = Boxing.boxInt(pageBody.getPageSize())) == null) ? 1 : boxInt2.intValue();
        if (pageBody != null && (boxInt = Boxing.boxInt(pageBody.getPageIndex())) != null) {
            i = boxInt.intValue();
        }
        RobotOpenSdk.responseMsg$default(robotOpenSdk, msgContext, new RespDestinationsBody(arrayList2, size, intValue, i), null, 4, null);
        return Unit.INSTANCE;
    }
}
