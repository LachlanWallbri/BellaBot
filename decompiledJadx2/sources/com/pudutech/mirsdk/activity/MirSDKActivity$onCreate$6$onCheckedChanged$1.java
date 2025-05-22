package com.pudutech.mirsdk.activity;

import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
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
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MirSDKActivity$onCreate$6$onCheckedChanged$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ boolean $arg1;
    final /* synthetic */ MirSDKActivity$onCreate$6 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MirSDKActivity$onCreate$6$onCheckedChanged$1(MirSDKActivity$onCreate$6 mirSDKActivity$onCreate$6, boolean z) {
        super(0);
        this.this$0 = mirSDKActivity$onCreate$6;
        this.$arg1 = z;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        String str;
        str = this.this$0.this$0.TAG;
        Pdlog.m3273d(str, "enableDropDet set boolean is " + this.$arg1);
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface != null) {
            sDKInterface.enableDropDet(this.$arg1);
        }
        CamerConfigHelper.setDetDropSwitch(this.$arg1);
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48711(null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$6$onCheckedChanged$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$6$onCheckedChanged$1$1 */
    /* loaded from: classes4.dex */
    public static final class C48711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5683p$;

        C48711(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48711 c48711 = new C48711(completion);
            c48711.f5683p$ = (CoroutineScope) obj;
            return c48711;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5683p$;
            Toast.makeText(MirSDKActivity$onCreate$6$onCheckedChanged$1.this.this$0.this$0, "RGBD防跌落已关闭", 0).show();
            return Unit.INSTANCE;
        }
    }
}
