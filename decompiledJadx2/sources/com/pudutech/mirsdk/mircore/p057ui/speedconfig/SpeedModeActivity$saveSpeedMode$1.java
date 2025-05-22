package com.pudutech.mirsdk.mircore.p057ui.speedconfig;

import android.widget.TextView;
import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.MirCoreScopeKt;
import com.pudutech.mirsdk.mircore.module.speedlevel.PlannerParamUtils;
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
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SpeedModeActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "code", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SpeedModeActivity$saveSpeedMode$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ SpeedModeActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpeedModeActivity$saveSpeedMode$1(SpeedModeActivity speedModeActivity) {
        super(1);
        this.this$0 = speedModeActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
        invoke(num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: SpeedModeActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.speedconfig.SpeedModeActivity$saveSpeedMode$1$1", m3970f = "SpeedModeActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.speedconfig.SpeedModeActivity$saveSpeedMode$1$1 */
    /* loaded from: classes6.dex */
    public static final class C52721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6291p$;

        C52721(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52721 c52721 = new C52721(completion);
            c52721.f6291p$ = (CoroutineScope) obj;
            return c52721;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52721) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            EditTextAdapter editTextAdapter;
            String str;
            String str2;
            String str3;
            String str4;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6291p$;
            editTextAdapter = SpeedModeActivity$saveSpeedMode$1.this.this$0.editTextAdapter;
            String saveSpeedMode$mircore_packRelease = editTextAdapter.saveSpeedMode$mircore_packRelease();
            str = SpeedModeActivity.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("set speed mode=");
            sb.append(saveSpeedMode$mircore_packRelease);
            sb.append(" successful=");
            sb.append(true);
            sb.append(" for mode ");
            str2 = SpeedModeActivity$saveSpeedMode$1.this.this$0.nowModeName;
            sb.append(str2);
            Pdlog.m3275i(str, sb.toString());
            BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new AnonymousClass1(saveSpeedMode$mircore_packRelease, null), 3, null);
            StringBuilder sb2 = new StringBuilder();
            str3 = SpeedModeActivity.SHOW_INFO;
            sb2.append(str3);
            sb2.append(" ");
            sb2.append("巡航\u3000");
            sb2.append(PlannerParamUtils.INSTANCE.getCruise_Mode());
            sb2.append("  直达 ");
            sb2.append(PlannerParamUtils.INSTANCE.getP2P_Mode());
            String sb3 = sb2.toString();
            TextView textView = (TextView) SpeedModeActivity$saveSpeedMode$1.this.this$0._$_findCachedViewById(C5224R.id.tvSpeedMode);
            if (textView == null) {
                Intrinsics.throwNpe();
            }
            textView.setText(sb3);
            Toast.makeText(SpeedModeActivity$saveSpeedMode$1.this.this$0, "success", 0).show();
            SpeedModeActivity speedModeActivity = SpeedModeActivity$saveSpeedMode$1.this.this$0;
            str4 = SpeedModeActivity$saveSpeedMode$1.this.this$0.nowModeName;
            speedModeActivity.selectedMode(str4);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* compiled from: SpeedModeActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.speedconfig.SpeedModeActivity$saveSpeedMode$1$1$1", m3970f = "SpeedModeActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.mirsdk.mircore.ui.speedconfig.SpeedModeActivity$saveSpeedMode$1$1$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ String $speedParam;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f6292p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(String str, Continuation continuation) {
                super(2, continuation);
                this.$speedParam = str;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$speedParam, completion);
                anonymousClass1.f6292p$ = (CoroutineScope) obj;
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
                CoroutineScope coroutineScope = this.f6292p$;
                PlannerParamUtils plannerParamUtils = PlannerParamUtils.INSTANCE;
                str = SpeedModeActivity$saveSpeedMode$1.this.this$0.nowModeName;
                plannerParamUtils.updateSpecifiedMode(str, this.$speedParam);
                return Unit.INSTANCE;
            }
        }
    }

    public final void invoke(int i) {
        if (i != 0) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), Dispatchers.getMain(), null, new C52721(null), 2, null);
    }
}
