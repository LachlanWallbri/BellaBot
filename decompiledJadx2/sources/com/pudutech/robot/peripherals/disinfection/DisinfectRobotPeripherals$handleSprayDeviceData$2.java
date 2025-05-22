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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DisinfectRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$handleSprayDeviceData$2", m3970f = "DisinfectRobotPeripherals.kt", m3971i = {0, 0}, m3972l = {872}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "error"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes6.dex */
public final class DisinfectRobotPeripherals$handleSprayDeviceData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $bytes;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7330p$;
    final /* synthetic */ DisinfectRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisinfectRobotPeripherals$handleSprayDeviceData$2(DisinfectRobotPeripherals disinfectRobotPeripherals, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = disinfectRobotPeripherals;
        this.$bytes = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DisinfectRobotPeripherals$handleSprayDeviceData$2 disinfectRobotPeripherals$handleSprayDeviceData$2 = new DisinfectRobotPeripherals$handleSprayDeviceData$2(this.this$0, this.$bytes, completion);
        disinfectRobotPeripherals$handleSprayDeviceData$2.f7330p$ = (CoroutineScope) obj;
        return disinfectRobotPeripherals$handleSprayDeviceData$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DisinfectRobotPeripherals$handleSprayDeviceData$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Function3 function3;
        CopyOnWriteArrayList<Function1> copyOnWriteArrayList;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7330p$;
            function3 = this.this$0.parseSprayDeviceError;
            List list = (List) function3.invoke(Boxing.boxInt(this.$bytes[6]), Boxing.boxInt(this.$bytes[5]), Boxing.boxInt(this.$bytes[4]));
            if (list.size() <= 0) {
                this.this$0.resetSprayDeviceError();
            } else {
                Pdlog.m3273d("DisinfectRobotPeripherals", "current spray error is " + list);
                copyOnWriteArrayList = this.this$0.onSprayDeviceErrorListeners;
                if (copyOnWriteArrayList != null) {
                    for (Function1 function1 : copyOnWriteArrayList) {
                        Object[] array = list.toArray(new SprayDeviceError[0]);
                        if (array != null) {
                            function1.invoke(array);
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    }
                }
            }
            boolean z = (this.$bytes[3] & 16) != 0;
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C57152 c57152 = new C57152(z, null);
            this.L$0 = coroutineScope;
            this.L$1 = list;
            this.label = 1;
            if (BuildersKt.withContext(main, c57152, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DisinfectRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$handleSprayDeviceData$2$2", m3970f = "DisinfectRobotPeripherals.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$handleSprayDeviceData$2$2 */
    /* loaded from: classes6.dex */
    public static final class C57152 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $d */
        final /* synthetic */ boolean f7331$d;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7332p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C57152(boolean z, Continuation continuation) {
            super(2, continuation);
            this.f7331$d = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57152 c57152 = new C57152(this.f7331$d, completion);
            c57152.f7332p$ = (CoroutineScope) obj;
            return c57152;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57152) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CopyOnWriteArrayList copyOnWriteArrayList;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f7332p$;
                copyOnWriteArrayList = DisinfectRobotPeripherals$handleSprayDeviceData$2.this.this$0.onSprayLiquidLevelStatusListeners;
                if (copyOnWriteArrayList == null) {
                    return null;
                }
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((Function1) it.next()).invoke(Boxing.boxBoolean(this.f7331$d));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
