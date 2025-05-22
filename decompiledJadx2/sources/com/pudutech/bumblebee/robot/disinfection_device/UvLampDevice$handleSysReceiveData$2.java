package com.pudutech.bumblebee.robot.disinfection_device;

import com.pudutech.bumblebee.robot.aidl.serialize.OpenState;
import com.pudutech.bumblebee.robot.aidl.serialize.UvLampDeviceError;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.UByteArray;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: UvLampDevice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.disinfection_device.UvLampDevice$handleSysReceiveData$2", m3970f = "UvLampDevice.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class UvLampDevice$handleSysReceiveData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $bytes;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4775p$;
    final /* synthetic */ UvLampDevice this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UvLampDevice$handleSysReceiveData$2(UvLampDevice uvLampDevice, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uvLampDevice;
        this.$bytes = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UvLampDevice$handleSysReceiveData$2 uvLampDevice$handleSysReceiveData$2 = new UvLampDevice$handleSysReceiveData$2(this.this$0, this.$bytes, completion);
        uvLampDevice$handleSysReceiveData$2.f4775p$ = (CoroutineScope) obj;
        return uvLampDevice$handleSysReceiveData$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UvLampDevice$handleSysReceiveData$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Function2 function2;
        OpenState openState;
        Function1<UvLampDeviceError[], Unit> onUvDeviceErrorListener$Robot_release;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        function2 = this.this$0.parseError;
        List list = (List) function2.invoke(Boxing.boxInt(UByteArray.m4577getimpl(this.$bytes, 5) & 255), Boxing.boxInt(UByteArray.m4577getimpl(this.$bytes, 4) & 255));
        if (list.size() > 0 && (onUvDeviceErrorListener$Robot_release = this.this$0.getOnUvDeviceErrorListener$Robot_release()) != 0) {
            Object[] array = list.toArray(new UvLampDeviceError[0]);
            if (array != null) {
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        int m4577getimpl = UByteArray.m4577getimpl(this.$bytes, 3) & 255 & 3;
        if (m4577getimpl == 0) {
            openState = OpenState.CLOSED;
        } else if (m4577getimpl == 1) {
            openState = OpenState.OPENING;
        } else if (m4577getimpl == 2) {
            openState = OpenState.OPENED;
        } else if (m4577getimpl == 3) {
            openState = OpenState.CLOSING;
        } else {
            openState = OpenState.CLOSED;
        }
        Function1<OpenState, Unit> onUvDevicePlateOpenStateListener$Robot_release = this.this$0.getOnUvDevicePlateOpenStateListener$Robot_release();
        if (onUvDevicePlateOpenStateListener$Robot_release != null) {
            onUvDevicePlateOpenStateListener$Robot_release.invoke(openState);
        }
        return Unit.INSTANCE;
    }
}
