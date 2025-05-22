package com.pudutech.peanut.robot_ui;

import com.pudutech.freeinstall_ui.utils.BusinessFunction;
import com.pudutech.freeinstall_ui.utils.CameraSupportType;
import com.pudutech.freeinstall_ui.utils.ConfigDataHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotContext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.RobotContext$initFreeInstallData$1", m3970f = "RobotContext.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class RobotContext$initFreeInstallData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6975p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RobotContext$initFreeInstallData$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotContext$initFreeInstallData$1 robotContext$initFreeInstallData$1 = new RobotContext$initFreeInstallData$1(completion);
        robotContext$initFreeInstallData$1.f6975p$ = (CoroutineScope) obj;
        return robotContext$initFreeInstallData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotContext$initFreeInstallData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6975p$;
        ConfigDataHelper.INSTANCE.setCameraSupportType(CameraSupportType.CAMERA_ALL);
        ConfigDataHelper.INSTANCE.setFunctionList(CollectionsKt.arrayListOf(BusinessFunction.FUNCTION_ASSURES, BusinessFunction.FUNCTION_MEALS, BusinessFunction.FUNCTION_RECEPTIONIST, BusinessFunction.FUNCTION_CRUISE, BusinessFunction.FUNCTION_CHARGE));
        return Unit.INSTANCE;
    }
}
