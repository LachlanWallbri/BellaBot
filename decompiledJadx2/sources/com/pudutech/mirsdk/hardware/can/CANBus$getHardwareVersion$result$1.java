package com.pudutech.mirsdk.hardware.can;

import android.os.Build;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.HardwareBoard;
import com.pudutech.mirsdk.hardware.serialize.HardwareVersion;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
 */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$getHardwareVersion$result$1", m3970f = "CANBus.kt", m3971i = {0}, m3972l = {506}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class CANBus$getHardwareVersion$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6026p$;
    final /* synthetic */ CANBus this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CANBus$getHardwareVersion$result$1(CANBus cANBus, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cANBus;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CANBus$getHardwareVersion$result$1 cANBus$getHardwareVersion$result$1 = new CANBus$getHardwareVersion$result$1(this.this$0, completion);
        cANBus$getHardwareVersion$result$1.f6026p$ = (CoroutineScope) obj;
        return cANBus$getHardwareVersion$result$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((CANBus$getHardwareVersion$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0048 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x003e -> B:5:0x0041). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CANBus$getHardwareVersion$result$1 cANBus$getHardwareVersion$result$1;
        int i;
        HardwareBoard[] hardwareBoardArr;
        HardwareBoard[] hardwareBoardArr2;
        HardwareBoard[] hardwareBoardArr3;
        String str;
        HardwareBoard[] hardwareBoardArr4;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6026p$;
            cANBus$getHardwareVersion$result$1 = this;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i2 == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            cANBus$getHardwareVersion$result$1 = this;
            synchronized (cANBus$getHardwareVersion$result$1.this$0.getHardwareVersions()) {
                i = 0;
                for (HardwareVersion hardwareVersion : cANBus$getHardwareVersion$result$1.this$0.getHardwareVersions()) {
                    if (Build.VERSION.SDK_INT > 22) {
                        hardwareBoardArr3 = cANBus$getHardwareVersion$result$1.this$0.hardwareBoards;
                        if (ArraysKt.contains(hardwareBoardArr3, hardwareVersion.getBoard())) {
                            i++;
                        } else {
                            str = cANBus$getHardwareVersion$result$1.this$0.TAG;
                            Pdlog.m3277w(str, "unknow hardware id:" + hardwareVersion.getBoard());
                        }
                    } else {
                        hardwareBoardArr4 = cANBus$getHardwareVersion$result$1.this$0.markerBoards;
                        if (ArraysKt.contains(hardwareBoardArr4, hardwareVersion.getBoard())) {
                            i++;
                        } else {
                            str2 = cANBus$getHardwareVersion$result$1.this$0.TAG;
                            Pdlog.m3277w(str2, "unknow hardware id:" + hardwareVersion.getBoard());
                        }
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
            if (Build.VERSION.SDK_INT > 22) {
                hardwareBoardArr2 = cANBus$getHardwareVersion$result$1.this$0.hardwareBoards;
                if (i == hardwareBoardArr2.length) {
                    return Boxing.boxBoolean(true);
                }
            } else {
                hardwareBoardArr = cANBus$getHardwareVersion$result$1.this$0.markerBoards;
                if (i == hardwareBoardArr.length) {
                    return Boxing.boxBoolean(true);
                }
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                cANBus$getHardwareVersion$result$1.this$0.m4425sendGBYM_sE(new byte[]{0, 19, 0, 0, 0, 0, 0});
                cANBus$getHardwareVersion$result$1.L$0 = coroutineScope;
                cANBus$getHardwareVersion$result$1.label = 1;
                if (DelayKt.delay(50L, cANBus$getHardwareVersion$result$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                synchronized (cANBus$getHardwareVersion$result$1.this$0.getHardwareVersions()) {
                }
            } else {
                return Boxing.boxBoolean(true);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
