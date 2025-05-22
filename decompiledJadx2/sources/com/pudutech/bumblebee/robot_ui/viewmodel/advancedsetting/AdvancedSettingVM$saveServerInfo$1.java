package com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting;

import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.pd_network.report.utils.GsonUtils;
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
/* compiled from: AdvancedSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.AdvancedSettingVM$saveServerInfo$1", m3970f = "AdvancedSettingVM.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class AdvancedSettingVM$saveServerInfo$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ServerInfoBean $serverInfo;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4992p$;
    final /* synthetic */ AdvancedSettingVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdvancedSettingVM$saveServerInfo$1(AdvancedSettingVM advancedSettingVM, ServerInfoBean serverInfoBean, Continuation continuation) {
        super(2, continuation);
        this.this$0 = advancedSettingVM;
        this.$serverInfo = serverInfoBean;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AdvancedSettingVM$saveServerInfo$1 advancedSettingVM$saveServerInfo$1 = new AdvancedSettingVM$saveServerInfo$1(this.this$0, this.$serverInfo, completion);
        advancedSettingVM$saveServerInfo$1.f4992p$ = (CoroutineScope) obj;
        return advancedSettingVM$saveServerInfo$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdvancedSettingVM$saveServerInfo$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4992p$;
        Pdlog.m3273d(this.this$0.TAG, "saveServerInfo: " + this.$serverInfo);
        Integer id = this.$serverInfo.getId();
        if (id != null && id.intValue() == 1) {
            Constans.INSTANCE.setServerInfo("");
        } else {
            Constans constans = Constans.INSTANCE;
            String json = GsonUtils.toJson(this.$serverInfo);
            Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtils.toJson(serverInfo)");
            constans.setServerInfo(json);
        }
        this.this$0._localserverListLV.postValue(this.$serverInfo);
        Tools.execCommand("am force-stop " + RobotContext.INSTANCE.getContext().getPackageName(), true);
        return Unit.INSTANCE;
    }
}
