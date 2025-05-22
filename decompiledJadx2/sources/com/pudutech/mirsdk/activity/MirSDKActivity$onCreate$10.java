package com.pudutech.mirsdk.activity;

import android.widget.CompoundButton;
import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.SDKInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$onCreate$10", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "onCheckedChanged", "", "arg0", "Landroid/widget/CompoundButton;", "arg1", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$onCreate$10 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$onCreate$10(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton arg0, final boolean arg1) {
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        Boolean valueOf = sDKInterface != null ? Boolean.valueOf(sDKInterface.getDropDetStatus()) : null;
        if (valueOf == null || Intrinsics.areEqual(Boolean.valueOf(arg1), valueOf)) {
            return;
        }
        if (arg1) {
            this.this$0.checkAuth("dbgmd", new Function0<Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$10$onCheckedChanged$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    String str;
                    str = MirSDKActivity$onCreate$10.this.this$0.TAG;
                    Pdlog.m3273d(str, "enableDropDet set boolean is " + arg1);
                    SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface2 != null) {
                        sDKInterface2.enableDropDet(arg1);
                    }
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48561(null), 2, null);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: MirSDKActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$10$onCheckedChanged$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$10$onCheckedChanged$1$1 */
                /* loaded from: classes5.dex */
                public static final class C48561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f5671p$;

                    C48561(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C48561 c48561 = new C48561(completion);
                        c48561.f5671p$ = (CoroutineScope) obj;
                        return c48561;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C48561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5671p$;
                        Toast.makeText(MirSDKActivity$onCreate$10.this.this$0, "RGBD防跌落已打开", 0).show();
                        return Unit.INSTANCE;
                    }
                }
            });
        } else {
            this.this$0.checkAuth("dbgmd", new Function0<Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$10$onCheckedChanged$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    String str;
                    str = MirSDKActivity$onCreate$10.this.this$0.TAG;
                    Pdlog.m3273d(str, "enableDropDet set boolean is " + arg1);
                    SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface2 != null) {
                        sDKInterface2.enableDropDet(arg1);
                    }
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48571(null), 2, null);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: MirSDKActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$10$onCheckedChanged$2$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$10$onCheckedChanged$2$1 */
                /* loaded from: classes5.dex */
                public static final class C48571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f5672p$;

                    C48571(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C48571 c48571 = new C48571(completion);
                        c48571.f5672p$ = (CoroutineScope) obj;
                        return c48571;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C48571) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5672p$;
                        Toast.makeText(MirSDKActivity$onCreate$10.this.this$0, "RGBD防跌落已关闭", 0).show();
                        return Unit.INSTANCE;
                    }
                }
            });
        }
    }
}
