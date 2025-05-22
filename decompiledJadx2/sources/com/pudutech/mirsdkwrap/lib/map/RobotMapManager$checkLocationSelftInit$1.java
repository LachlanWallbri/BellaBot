package com.pudutech.mirsdkwrap.lib.map;

import com.aliyun.alink.dm.api.DMErrorCode;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.SDKInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotMapManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.map.RobotMapManager$checkLocationSelftInit$1", m3970f = "RobotMapManager.kt", m3971i = {0, 0, 1, 1, 2, 2}, m3972l = {DMErrorCode.ERROR_CMP_SEND_ERROR_CONNECT_NOT_CONNECTED, DMErrorCode.ERROR_CMP_REGISTER_CONNECT_IS_REGISTERING, 534}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "it", "$this$launch", "it", "$this$launch", "it"}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$2"})
/* loaded from: classes4.dex */
public final class RobotMapManager$checkLocationSelftInit$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;

    /* renamed from: $i */
    final /* synthetic */ long f6504$i;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6505p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RobotMapManager$checkLocationSelftInit$1(long j, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.f6504$i = j;
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotMapManager$checkLocationSelftInit$1 robotMapManager$checkLocationSelftInit$1 = new RobotMapManager$checkLocationSelftInit$1(this.f6504$i, this.$cb, completion);
        robotMapManager$checkLocationSelftInit$1.f6505p$ = (CoroutineScope) obj;
        return robotMapManager$checkLocationSelftInit$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotMapManager$checkLocationSelftInit$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00d0 -> B:7:0x00d3). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        CoroutineScope coroutineScope;
        int i2;
        RobotMapManager$checkLocationSelftInit$1 robotMapManager$checkLocationSelftInit$1;
        SDKInterface sDKInterface;
        String str;
        Boolean boxBoolean;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6505p$;
            i = (int) this.f6504$i;
            coroutineScope = coroutineScope2;
            i2 = 0;
            robotMapManager$checkLocationSelftInit$1 = this;
            if (i2 < i) {
            }
        } else {
            if (i3 == 1) {
                int i4 = this.I$0;
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            if (i3 == 2) {
                int i5 = this.I$0;
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            if (i3 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i6 = this.I$2;
            i = this.I$1;
            i2 = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            robotMapManager$checkLocationSelftInit$1 = this;
            i2++;
            if (i2 < i) {
                int intValue = Boxing.boxInt(i2).intValue();
                RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
                sDKInterface = RobotMapManager.mirSdk;
                if ((sDKInterface == null || (boxBoolean = Boxing.boxBoolean(sDKInterface.isLocalizationFinishInitialization())) == null) ? false : boxBoolean.booleanValue()) {
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    C5319xff77aff3 c5319xff77aff3 = new C5319xff77aff3(null, robotMapManager$checkLocationSelftInit$1);
                    robotMapManager$checkLocationSelftInit$1.L$0 = coroutineScope;
                    robotMapManager$checkLocationSelftInit$1.I$0 = intValue;
                    robotMapManager$checkLocationSelftInit$1.label = 1;
                    if (BuildersKt.withContext(main, c5319xff77aff3, robotMapManager$checkLocationSelftInit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                if (intValue == ((int) robotMapManager$checkLocationSelftInit$1.f6504$i) - 1) {
                    RobotMapManager robotMapManager2 = RobotMapManager.INSTANCE;
                    str = RobotMapManager.TAG;
                    Pdlog.m3273d(str, "checkLocationInit false");
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    C5320xff77aff4 c5320xff77aff4 = new C5320xff77aff4(null, robotMapManager$checkLocationSelftInit$1);
                    robotMapManager$checkLocationSelftInit$1.L$0 = coroutineScope;
                    robotMapManager$checkLocationSelftInit$1.I$0 = intValue;
                    robotMapManager$checkLocationSelftInit$1.label = 2;
                    if (BuildersKt.withContext(main2, c5320xff77aff4, robotMapManager$checkLocationSelftInit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                robotMapManager$checkLocationSelftInit$1.L$0 = coroutineScope;
                robotMapManager$checkLocationSelftInit$1.I$0 = i2;
                robotMapManager$checkLocationSelftInit$1.I$1 = i;
                robotMapManager$checkLocationSelftInit$1.I$2 = intValue;
                robotMapManager$checkLocationSelftInit$1.label = 3;
                if (DelayKt.delay(500L, robotMapManager$checkLocationSelftInit$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i2++;
                if (i2 < i) {
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
