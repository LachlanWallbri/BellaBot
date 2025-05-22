package com.pudutech.mirsdk.activity;

import android.content.Context;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.function.C4946R;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$1", m3970f = "MirSDKActivity.kt", m3971i = {0}, m3972l = {832}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class MirSDKActivity$connectHardware$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5610p$;
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSDKActivity$connectHardware$1(MirSDKActivity mirSDKActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSDKActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSDKActivity$connectHardware$1 mirSDKActivity$connectHardware$1 = new MirSDKActivity$connectHardware$1(this.this$0, completion);
        mirSDKActivity$connectHardware$1.f5610p$ = (CoroutineScope) obj;
        return mirSDKActivity$connectHardware$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSDKActivity$connectHardware$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5610p$;
            HardWareServiceConnection hardWareServiceConnection = HardWareServiceConnection.INSTANCE;
            Context applicationContext = this.this$0.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = AIDLConnection.connect$default(hardWareServiceConnection, applicationContext, null, this, 2, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "HardWareServiceConnection success");
            HardWareServiceConnection.INSTANCE.setOnSensorMagneticResult(new Function1<Integer, Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: MirSDKActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$1$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$1$1$1, reason: invalid class name */
                /* loaded from: classes5.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ int $it;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f5611p$;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(int i, Continuation continuation) {
                        super(2, continuation);
                        this.$it = i;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$it, completion);
                        anonymousClass1.f5611p$ = (CoroutineScope) obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        String str;
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5611p$;
                        str = MirSDKActivity$connectHardware$1.this.this$0.TAG;
                        Pdlog.m3273d(str, "sensor magetic num = " + this.$it);
                        int i = this.$it;
                        if (i == 0) {
                            ((TextView) MirSDKActivity$connectHardware$1.this.this$0._$_findCachedViewById(C4946R.id.tx_magnetic)).setText(MirSDKActivity$connectHardware$1.this.this$0.getString(C4946R.string.no_magnetic_tip));
                        } else if (i == 1) {
                            ((TextView) MirSDKActivity$connectHardware$1.this.this$0._$_findCachedViewById(C4946R.id.tx_magnetic)).setText(MirSDKActivity$connectHardware$1.this.this$0.getString(C4946R.string.left_magnetic_tip));
                        } else if (i == 2) {
                            ((TextView) MirSDKActivity$connectHardware$1.this.this$0._$_findCachedViewById(C4946R.id.tx_magnetic)).setText(MirSDKActivity$connectHardware$1.this.this$0.getString(C4946R.string.right_magnetic_tip));
                        } else if (i == 3) {
                            ((TextView) MirSDKActivity$connectHardware$1.this.this$0._$_findCachedViewById(C4946R.id.tx_magnetic)).setText(MirSDKActivity$connectHardware$1.this.this$0.getString(C4946R.string.left_right_magnetic_tip));
                            MirSDKActivity$connectHardware$1.this.this$0.supportMagneticFunction = true;
                        }
                        return Unit.INSTANCE;
                    }
                }

                public final void invoke(int i2) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(i2, null), 2, null);
                }
            });
            HardWareServiceConnection.INSTANCE.setOnGeomagneticResult(new Function4<String, Integer, Integer, Integer, Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$1.2
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(String str2, Integer num, Integer num2, Integer num3) {
                    invoke(str2, num.intValue(), num2.intValue(), num3.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: MirSDKActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$1$2$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$connectHardware$1$2$1, reason: invalid class name */
                /* loaded from: classes5.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ String $direction;

                    /* renamed from: $x */
                    final /* synthetic */ int f5612$x;

                    /* renamed from: $y */
                    final /* synthetic */ int f5613$y;

                    /* renamed from: $z */
                    final /* synthetic */ int f5614$z;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f5615p$;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(String str, int i, int i2, int i3, Continuation continuation) {
                        super(2, continuation);
                        this.$direction = str;
                        this.f5612$x = i;
                        this.f5613$y = i2;
                        this.f5614$z = i3;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$direction, this.f5612$x, this.f5613$y, this.f5614$z, completion);
                        anonymousClass1.f5615p$ = (CoroutineScope) obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        String str;
                        int i;
                        int i2;
                        int i3;
                        int i4;
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5615p$;
                        str = MirSDKActivity$connectHardware$1.this.this$0.TAG;
                        Pdlog.m3273d(str, "left view is " + this.$direction + " x=" + this.f5612$x + " y = " + this.f5613$y + " z=" + this.f5614$z);
                        if ("left".equals(this.$direction)) {
                            TextView geomagnetic_left = (TextView) MirSDKActivity$connectHardware$1.this.this$0._$_findCachedViewById(C4946R.id.geomagnetic_left);
                            Intrinsics.checkExpressionValueIsNotNull(geomagnetic_left, "geomagnetic_left");
                            geomagnetic_left.setText(this.$direction + ":x=" + this.f5612$x + " y=" + this.f5613$y + " z=" + this.f5614$z);
                            int i5 = this.f5612$x;
                            if ((i5 <= this.f5613$y || i5 <= this.f5614$z) && (i5 = this.f5613$y) <= (i3 = this.f5614$z)) {
                                i5 = i3;
                            }
                            i4 = MirSDKActivity$connectHardware$1.this.this$0.leftMageticMax;
                            if (i4 < i5) {
                                MirSDKActivity$connectHardware$1.this.this$0.leftMageticMax = i5;
                            }
                        }
                        if ("right".equals(this.$direction)) {
                            TextView geomagnetic_right = (TextView) MirSDKActivity$connectHardware$1.this.this$0._$_findCachedViewById(C4946R.id.geomagnetic_right);
                            Intrinsics.checkExpressionValueIsNotNull(geomagnetic_right, "geomagnetic_right");
                            geomagnetic_right.setText(this.$direction + ":x=" + this.f5612$x + " y=" + this.f5613$y + " z=" + this.f5614$z);
                            int i6 = this.f5612$x;
                            if ((i6 <= this.f5613$y || i6 <= this.f5614$z) && (i6 = this.f5613$y) <= (i = this.f5614$z)) {
                                i6 = i;
                            }
                            i2 = MirSDKActivity$connectHardware$1.this.this$0.rightMageticMax;
                            if (i2 < i6) {
                                MirSDKActivity$connectHardware$1.this.this$0.rightMageticMax = i6;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }

                public final void invoke(String direction, int i2, int i3, int i4) {
                    Intrinsics.checkParameterIsNotNull(direction, "direction");
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(direction, i2, i3, i4, null), 2, null);
                }
            });
            HardWareServiceConnection.INSTANCE.addHardwareListener();
            HardwareInterface hardwareInterface = HardWareServiceConnection.INSTANCE.getInterface();
            if (hardwareInterface != null) {
                hardwareInterface.getMagneticSensor();
            }
        }
        return Unit.INSTANCE;
    }
}
