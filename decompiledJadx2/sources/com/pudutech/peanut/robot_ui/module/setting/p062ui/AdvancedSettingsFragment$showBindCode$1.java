package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.robot.opensdk.aliyun.bean.BindCodeData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.anko.DimensionsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AdvancedSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$showBindCode$1", m3970f = "AdvancedSettingsFragment.kt", m3971i = {0, 0}, m3972l = {DimensionsKt.XHDPI}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "generateQRCodeBitmap"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes5.dex */
public final class AdvancedSettingsFragment$showBindCode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BindCodeData $current;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7020p$;
    final /* synthetic */ AdvancedSettingsFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdvancedSettingsFragment$showBindCode$1(AdvancedSettingsFragment advancedSettingsFragment, BindCodeData bindCodeData, Continuation continuation) {
        super(2, continuation);
        this.this$0 = advancedSettingsFragment;
        this.$current = bindCodeData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AdvancedSettingsFragment$showBindCode$1 advancedSettingsFragment$showBindCode$1 = new AdvancedSettingsFragment$showBindCode$1(this.this$0, this.$current, completion);
        advancedSettingsFragment$showBindCode$1.f7020p$ = (CoroutineScope) obj;
        return advancedSettingsFragment$showBindCode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdvancedSettingsFragment$showBindCode$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, android.graphics.Bitmap] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ?? generateQRCodeBitmap;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7020p$;
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            generateQRCodeBitmap = this.this$0.generateQRCodeBitmap(this.$current.getCode());
            objectRef.element = generateQRCodeBitmap;
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C55311 c55311 = new C55311(objectRef, null);
            this.L$0 = coroutineScope;
            this.L$1 = objectRef;
            this.label = 1;
            if (BuildersKt.withContext(main, c55311, this) == coroutine_suspended) {
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
    /* compiled from: AdvancedSettingsFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$showBindCode$1$1", m3970f = "AdvancedSettingsFragment.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.peanut.robot_ui.module.setting.ui.AdvancedSettingsFragment$showBindCode$1$1 */
    /* loaded from: classes5.dex */
    public static final class C55311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Ref.ObjectRef $generateQRCodeBitmap;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7021p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C55311(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$generateQRCodeBitmap = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C55311 c55311 = new C55311(this.$generateQRCodeBitmap, completion);
            c55311.f7021p$ = (CoroutineScope) obj;
            return c55311;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C55311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Handler handler;
            Handler handler2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7021p$;
            RelativeLayout gen_qrcode_layout = (RelativeLayout) AdvancedSettingsFragment$showBindCode$1.this.this$0._$_findCachedViewById(C5508R.id.gen_qrcode_layout);
            Intrinsics.checkExpressionValueIsNotNull(gen_qrcode_layout, "gen_qrcode_layout");
            gen_qrcode_layout.setVisibility(0);
            TextView er_code_tv = (TextView) AdvancedSettingsFragment$showBindCode$1.this.this$0._$_findCachedViewById(C5508R.id.er_code_tv);
            Intrinsics.checkExpressionValueIsNotNull(er_code_tv, "er_code_tv");
            er_code_tv.setText(AdvancedSettingsFragment$showBindCode$1.this.$current.getCode());
            TextView er_code_tv2 = (TextView) AdvancedSettingsFragment$showBindCode$1.this.this$0._$_findCachedViewById(C5508R.id.er_code_tv);
            Intrinsics.checkExpressionValueIsNotNull(er_code_tv2, "er_code_tv");
            er_code_tv2.setVisibility(0);
            TextView gen_code_tv = (TextView) AdvancedSettingsFragment$showBindCode$1.this.this$0._$_findCachedViewById(C5508R.id.gen_code_tv);
            Intrinsics.checkExpressionValueIsNotNull(gen_code_tv, "gen_code_tv");
            gen_code_tv.setText(AdvancedSettingsFragment$showBindCode$1.this.this$0.getString(C5508R.string.pdStr7_142));
            ((ImageView) AdvancedSettingsFragment$showBindCode$1.this.this$0._$_findCachedViewById(C5508R.id.er_code_iv)).setImageBitmap((Bitmap) this.$generateQRCodeBitmap.element);
            ImageView er_code_iv = (ImageView) AdvancedSettingsFragment$showBindCode$1.this.this$0._$_findCachedViewById(C5508R.id.er_code_iv);
            Intrinsics.checkExpressionValueIsNotNull(er_code_iv, "er_code_iv");
            er_code_iv.setVisibility(0);
            handler = AdvancedSettingsFragment$showBindCode$1.this.this$0.mainHandler;
            handler.removeMessages(0);
            handler2 = AdvancedSettingsFragment$showBindCode$1.this.this$0.mainHandler;
            return Boxing.boxBoolean(handler2.sendEmptyMessageDelayed(0, 300000L));
        }
    }
}
