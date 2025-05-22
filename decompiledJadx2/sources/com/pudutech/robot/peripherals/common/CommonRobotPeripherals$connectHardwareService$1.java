package com.pudutech.robot.peripherals.common;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CommonRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.common.CommonRobotPeripherals$connectHardwareService$1", m3970f = "CommonRobotPeripherals.kt", m3971i = {0, 1, 1, 2, 2}, m3972l = {164, 169, 177}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "succeed", "$this$launch", "succeed"}, m3975s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes6.dex */
public final class CommonRobotPeripherals$connectHardwareService$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7297p$;
    final /* synthetic */ CommonRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommonRobotPeripherals$connectHardwareService$1(CommonRobotPeripherals commonRobotPeripherals, Continuation continuation) {
        super(2, continuation);
        this.this$0 = commonRobotPeripherals;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CommonRobotPeripherals$connectHardwareService$1 commonRobotPeripherals$connectHardwareService$1 = new CommonRobotPeripherals$connectHardwareService$1(this.this$0, completion);
        commonRobotPeripherals$connectHardwareService$1.f7297p$ = (CoroutineScope) obj;
        return commonRobotPeripherals$connectHardwareService$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CommonRobotPeripherals$connectHardwareService$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f2  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Ref.BooleanRef booleanRef;
        AIDLConnection aIDLConnection;
        CoroutineScope coroutineScope;
        Ref.BooleanRef booleanRef2;
        Ref.BooleanRef booleanRef3;
        CoroutineScope coroutineScope2;
        AIDLConnection aIDLConnection2;
        AIDLConnection aIDLConnection3;
        byte[] allReceiveCmds;
        CommonRobotPeripherals$onCANDataReceivedListener$1 commonRobotPeripherals$onCANDataReceivedListener$1;
        int i;
        int i2;
        int i3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope3 = this.f7297p$;
            booleanRef = new Ref.BooleanRef();
            aIDLConnection = this.this$0.hardwareService;
            Context context = this.this$0.getContext();
            this.L$0 = coroutineScope3;
            this.L$1 = booleanRef;
            this.L$2 = booleanRef;
            this.label = 1;
            Object connect$default = AIDLConnection.connect$default(aIDLConnection, context, null, this, 2, null);
            if (connect$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope3;
            obj = connect$default;
            booleanRef2 = booleanRef;
        } else {
            if (i4 != 1) {
                if (i4 != 2) {
                    if (i4 == 3) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                booleanRef3 = (Ref.BooleanRef) this.L$1;
                coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (booleanRef3.element) {
                    i = this.this$0.reConnectCount;
                    i2 = this.this$0.MAX_CONNECT_COUNT;
                    if (i < i2) {
                        CommonRobotPeripherals commonRobotPeripherals = this.this$0;
                        i3 = commonRobotPeripherals.reConnectCount;
                        commonRobotPeripherals.reConnectCount = i3 + 1;
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        C57122 c57122 = new C57122(null);
                        this.L$0 = coroutineScope2;
                        this.L$1 = booleanRef3;
                        this.label = 3;
                        if (BuildersKt.withContext(main, c57122, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                this.this$0.isHardwareServiceConnected = true;
                aIDLConnection2 = this.this$0.hardwareService;
                HardwareInterface hardwareInterface = (HardwareInterface) aIDLConnection2.getInterface();
                if (hardwareInterface != null) {
                    String simpleName = this.this$0.getClass().getSimpleName();
                    allReceiveCmds = this.this$0.getAllReceiveCmds();
                    byte[] copyOf = Arrays.copyOf(allReceiveCmds, allReceiveCmds.length);
                    Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                    commonRobotPeripherals$onCANDataReceivedListener$1 = this.this$0.onCANDataReceivedListener;
                    hardwareInterface.addCANDataListener(simpleName, copyOf, commonRobotPeripherals$onCANDataReceivedListener$1);
                }
                LightBeltsPlayHelper lightBeltsPlayHelper = LightBeltsPlayHelper.INSTANCE;
                aIDLConnection3 = this.this$0.hardwareService;
                lightBeltsPlayHelper.setHardwareInterface((HardwareInterface) aIDLConnection3.getInterface());
                return Unit.INSTANCE;
            }
            booleanRef = (Ref.BooleanRef) this.L$2;
            booleanRef2 = (Ref.BooleanRef) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        booleanRef.element = ((Boolean) obj).booleanValue();
        Pdlog.m3273d(this.this$0.getClass().getSimpleName(), "connectHardwareService() succeed = " + booleanRef2.element);
        MainCoroutineDispatcher main2 = Dispatchers.getMain();
        C57111 c57111 = new C57111(booleanRef2, null);
        this.L$0 = coroutineScope;
        this.L$1 = booleanRef2;
        this.label = 2;
        if (BuildersKt.withContext(main2, c57111, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        booleanRef3 = booleanRef2;
        coroutineScope2 = coroutineScope;
        if (booleanRef3.element) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CommonRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.robot.peripherals.common.CommonRobotPeripherals$connectHardwareService$1$1", m3970f = "CommonRobotPeripherals.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.robot.peripherals.common.CommonRobotPeripherals$connectHardwareService$1$1 */
    /* loaded from: classes6.dex */
    public static final class C57111 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $succeed;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7298p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C57111(Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$succeed = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57111 c57111 = new C57111(this.$succeed, completion);
            c57111.f7298p$ = (CoroutineScope) obj;
            return c57111;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57111) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x002e, code lost:
        
            if (r2 == (r0 - 1)) goto L10;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Function1 function1;
            Function1 function12;
            int i;
            int i2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7298p$;
            function1 = CommonRobotPeripherals$connectHardwareService$1.this.this$0.hardwareConnectListener;
            if (function1 != null) {
                if (!this.$succeed.element) {
                    i = CommonRobotPeripherals$connectHardwareService$1.this.this$0.reConnectCount;
                    i2 = CommonRobotPeripherals$connectHardwareService$1.this.this$0.MAX_CONNECT_COUNT;
                }
                function12 = CommonRobotPeripherals$connectHardwareService$1.this.this$0.hardwareConnectListener;
                if (function12 != null) {
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CommonRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.robot.peripherals.common.CommonRobotPeripherals$connectHardwareService$1$2", m3970f = "CommonRobotPeripherals.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.robot.peripherals.common.CommonRobotPeripherals$connectHardwareService$1$2 */
    /* loaded from: classes6.dex */
    public static final class C57122 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7299p$;

        C57122(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57122 c57122 = new C57122(completion);
            c57122.f7299p$ = (CoroutineScope) obj;
            return c57122;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57122) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7299p$;
            CommonRobotPeripherals$connectHardwareService$1.this.this$0.connectHardwareService();
            return Unit.INSTANCE;
        }
    }
}
