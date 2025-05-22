package com.pudutech.robot.peripherals.disinfection;

import com.pudutech.base.Pdlog;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DisinfectRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$handleUvcLampDeviceData$2", m3970f = "DisinfectRobotPeripherals.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class DisinfectRobotPeripherals$handleUvcLampDeviceData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $bytes;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7335p$;
    final /* synthetic */ DisinfectRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisinfectRobotPeripherals$handleUvcLampDeviceData$2(DisinfectRobotPeripherals disinfectRobotPeripherals, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = disinfectRobotPeripherals;
        this.$bytes = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DisinfectRobotPeripherals$handleUvcLampDeviceData$2 disinfectRobotPeripherals$handleUvcLampDeviceData$2 = new DisinfectRobotPeripherals$handleUvcLampDeviceData$2(this.this$0, this.$bytes, completion);
        disinfectRobotPeripherals$handleUvcLampDeviceData$2.f7335p$ = (CoroutineScope) obj;
        return disinfectRobotPeripherals$handleUvcLampDeviceData$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DisinfectRobotPeripherals$handleUvcLampDeviceData$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Function2 function2;
        OpenState openState;
        CopyOnWriteArrayList copyOnWriteArrayList;
        CopyOnWriteArrayList copyOnWriteArrayList2;
        CopyOnWriteArrayList copyOnWriteArrayList3;
        CopyOnWriteArrayList<Function1> copyOnWriteArrayList4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7335p$;
        function2 = this.this$0.parseUvcLampDeviceError;
        List list = (List) function2.invoke(Boxing.boxInt(this.$bytes[5]), Boxing.boxInt(this.$bytes[4]));
        if (list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("current uvc error is ");
            sb.append(list);
            sb.append("  onUvcDeviceErrorListener list is  ");
            copyOnWriteArrayList2 = this.this$0.onUvcDeviceErrorListener;
            sb.append(copyOnWriteArrayList2);
            sb.append("  onUvcDevicePlateOpenStateListener is ");
            copyOnWriteArrayList3 = this.this$0.onUvcDevicePlateOpenStateListener;
            sb.append(copyOnWriteArrayList3);
            sb.append(' ');
            Pdlog.m3273d("DisinfectRobotPeripherals", sb.toString());
            copyOnWriteArrayList4 = this.this$0.onUvcDeviceErrorListener;
            if (copyOnWriteArrayList4 != null) {
                for (Function1 function1 : copyOnWriteArrayList4) {
                    Object[] array = list.toArray(new UvcLampDeviceError[0]);
                    if (array != null) {
                        function1.invoke(array);
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
            }
        }
        int i = this.$bytes[3] & 3;
        if (i == 0) {
            openState = OpenState.CLOSED;
        } else if (i == 1) {
            openState = OpenState.OPENING;
        } else if (i == 2) {
            openState = OpenState.OPENED;
        } else if (i == 3) {
            openState = OpenState.CLOSING;
        } else {
            openState = OpenState.CLOSED;
        }
        copyOnWriteArrayList = this.this$0.onUvcDevicePlateOpenStateListener;
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((Function1) it.next()).invoke(openState);
            }
        }
        return Unit.INSTANCE;
    }
}
