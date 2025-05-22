package com.pudutech.robot.peripherals.activity;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.robot.peripherals.C5707R;
import com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NineTailsDebugActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NineTailsDebugActivity$initView$1 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ NineTailsDebugActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NineTailsDebugActivity$initView$1(NineTailsDebugActivity nineTailsDebugActivity) {
        super(1);
        this.this$0 = nineTailsDebugActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
        invoke(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(boolean z) {
        boolean z2;
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals;
        Pdlog.m3273d("NineTailsDebugActivity", "connect to hardware " + z);
        if (z) {
            ToastUtils.INSTANCE.showShortToast("Success: HardWare is Online");
            z2 = NineTailsDebugActivity.isOpenFirst;
            if (!z2) {
                iDisinfectRobotPeripherals = this.this$0.disinfectRobotPeripherals;
                if (iDisinfectRobotPeripherals != null) {
                    HardwareInterface hardWareInterface = ((DisinfectRobotPeripherals) iDisinfectRobotPeripherals).getHardWareInterface();
                    if (hardWareInterface != null) {
                        hardWareInterface.open();
                    }
                    NineTailsDebugActivity.isOpenFirst = true;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals");
                }
            }
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C57091(null), 3, null);
        } else {
            TextView tvHardwareVersion = (TextView) this.this$0._$_findCachedViewById(C5707R.id.tvHardwareVersion);
            Intrinsics.checkExpressionValueIsNotNull(tvHardwareVersion, "tvHardwareVersion");
            tvHardwareVersion.setText("");
            ToastUtils.INSTANCE.showShortToast("Fail: HardWare is Offline");
        }
        this.this$0.isConnectToHardWare = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: NineTailsDebugActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initView$1$1", m3970f = "NineTailsDebugActivity.kt", m3971i = {0, 1}, m3972l = {79, 80}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
    /* renamed from: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initView$1$1 */
    /* loaded from: classes6.dex */
    public static final class C57091 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7293p$;

        C57091(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57091 c57091 = new C57091(completion);
            c57091.f7293p$ = (CoroutineScope) obj;
            return c57091;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57091) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f7293p$;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
            this.L$0 = coroutineScope;
            this.label = 2;
            if (BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: NineTailsDebugActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initView$1$1$1", m3970f = "NineTailsDebugActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$initView$1$1$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;

            /* renamed from: p$ */
            private CoroutineScope f7294p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                anonymousClass1.f7294p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                String handlerHardVersion;
                String handlerHardVersion2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f7294p$;
                handlerHardVersion = NineTailsDebugActivity$initView$1.this.this$0.handlerHardVersion();
                if (handlerHardVersion.length() > 0) {
                    TextView tvHardwareVersion = (TextView) NineTailsDebugActivity$initView$1.this.this$0._$_findCachedViewById(C5707R.id.tvHardwareVersion);
                    Intrinsics.checkExpressionValueIsNotNull(tvHardwareVersion, "tvHardwareVersion");
                    StringBuilder sb = new StringBuilder();
                    sb.append("当前硬件版本:");
                    handlerHardVersion2 = NineTailsDebugActivity$initView$1.this.this$0.handlerHardVersion();
                    sb.append(handlerHardVersion2);
                    tvHardwareVersion.setText(sb.toString());
                } else {
                    TextView tvHardwareVersion2 = (TextView) NineTailsDebugActivity$initView$1.this.this$0._$_findCachedViewById(C5707R.id.tvHardwareVersion);
                    Intrinsics.checkExpressionValueIsNotNull(tvHardwareVersion2, "tvHardwareVersion");
                    tvHardwareVersion2.setText("");
                }
                return Unit.INSTANCE;
            }
        }
    }
}
