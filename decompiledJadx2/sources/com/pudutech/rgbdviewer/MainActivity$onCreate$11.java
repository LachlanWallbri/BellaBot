package com.pudutech.rgbdviewer;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.recycle.robot.p064ui.widget.CFullScreenConfirmDialogFragment;
import com.pudutech.rgbdlib.RGBDData;
import com.pudutech.rgbdlib.RobotType;
import com.pudutech.rgbdviewer.RGBDView;
import com.pudutech.widget.loading.CLoadingView;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "rgbdData", "Lcom/pudutech/rgbdlib/RGBDData;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class MainActivity$onCreate$11 extends Lambda implements Function1<RGBDData, Unit> {
    final /* synthetic */ Ref.ObjectRef $ctx;
    final /* synthetic */ Button $downRGBDCheckButton;
    final /* synthetic */ Button $jumpToRbotButton;
    final /* synthetic */ Button $saveButton;
    final /* synthetic */ Button $switchButton;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$onCreate$11(MainActivity mainActivity, Button button, Button button2, Button button3, Button button4, Ref.ObjectRef objectRef) {
        super(1);
        this.this$0 = mainActivity;
        this.$switchButton = button;
        this.$saveButton = button2;
        this.$jumpToRbotButton = button3;
        this.$downRGBDCheckButton = button4;
        this.$ctx = objectRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(RGBDData rGBDData) {
        invoke2(rGBDData);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(RGBDData rgbdData) {
        boolean z;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(rgbdData, "rgbdData");
        try {
            z = this.this$0.inited;
            if (z) {
                z2 = this.this$0.enable_auto_detect;
                if (z2 & (MainActivity.access$getLeftView$p(this.this$0).getConfigState() != RGBDView.ConfigState.NotConfigured) & (MainActivity.access$getRightView$p(this.this$0).getConfigState() != RGBDView.ConfigState.NotConfigured)) {
                    this.this$0.enable_auto_detect = false;
                    MainActivity.access$getRgbdSensor$p(this.this$0).nativeStopAutoDetect();
                    new Thread(new RunnableC56793()).start();
                }
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C56804(rgbdData, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C56815(rgbdData, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C56826(rgbdData, null), 3, null);
                return;
            }
            if (Intrinsics.areEqual(rgbdData.getPos(), "Unknow")) {
                this.this$0.dealUnknowPosData(rgbdData);
            } else {
                this.this$0.dealKnownPosData(rgbdData);
            }
            if ((!(MainActivity.access$getLeftView$p(this.this$0).getSerialNum().length() == 0)) & (!(MainActivity.access$getRightView$p(this.this$0).getSerialNum().length() == 0))) {
                this.this$0.inited = true;
                this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$11.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        String str;
                        String str2;
                        String str3;
                        String str4;
                        Button switchButton = MainActivity$onCreate$11.this.$switchButton;
                        Intrinsics.checkExpressionValueIsNotNull(switchButton, "switchButton");
                        switchButton.setVisibility(0);
                        Button saveButton = MainActivity$onCreate$11.this.$saveButton;
                        Intrinsics.checkExpressionValueIsNotNull(saveButton, "saveButton");
                        saveButton.setVisibility(0);
                        Button pic_change1 = (Button) MainActivity$onCreate$11.this.this$0._$_findCachedViewById(C5692R.id.pic_change1);
                        Intrinsics.checkExpressionValueIsNotNull(pic_change1, "pic_change1");
                        pic_change1.setVisibility(0);
                        if (HardwareManager.INSTANCE.getRgbdVersion() == 2) {
                            FrameLayout downCheckLayout = (FrameLayout) MainActivity$onCreate$11.this.this$0._$_findCachedViewById(C5692R.id.downCheckLayout);
                            Intrinsics.checkExpressionValueIsNotNull(downCheckLayout, "downCheckLayout");
                            downCheckLayout.setVisibility(0);
                        }
                        if (MainActivity$onCreate$11.this.this$0.getRobotType() == RobotType.Bellabot) {
                            StringBuilder sb = new StringBuilder();
                            str4 = MainActivity$onCreate$11.this.this$0.VERSION;
                            sb.append(str4);
                            sb.append(MainActivity$onCreate$11.this.this$0.getResources().getString(2131558434));
                            ((TextView) MainActivity$onCreate$11.this.this$0.findViewById(2131231096)).setText(sb.toString());
                            return;
                        }
                        if (MainActivity$onCreate$11.this.this$0.getRobotType() == RobotType.Hls) {
                            StringBuilder sb2 = new StringBuilder();
                            str3 = MainActivity$onCreate$11.this.this$0.VERSION;
                            sb2.append(str3);
                            sb2.append(MainActivity$onCreate$11.this.this$0.getResources().getString(2131558483));
                            ((TextView) MainActivity$onCreate$11.this.this$0.findViewById(2131231096)).setText(sb2.toString());
                            return;
                        }
                        if (MainActivity$onCreate$11.this.this$0.getRobotType() == RobotType.RecycleDog) {
                            StringBuilder sb3 = new StringBuilder();
                            str2 = MainActivity$onCreate$11.this.this$0.VERSION;
                            sb3.append(str2);
                            sb3.append(MainActivity$onCreate$11.this.this$0.getResources().getString(2131558449));
                            ((TextView) MainActivity$onCreate$11.this.this$0.findViewById(2131231096)).setText(sb3.toString());
                            return;
                        }
                        if (MainActivity$onCreate$11.this.this$0.getRobotType() == RobotType.Ninetales) {
                            StringBuilder sb4 = new StringBuilder();
                            str = MainActivity$onCreate$11.this.this$0.VERSION;
                            sb4.append(str);
                            sb4.append(MainActivity$onCreate$11.this.this$0.getResources().getString(2131558472));
                            ((TextView) MainActivity$onCreate$11.this.this$0.findViewById(2131231096)).setText(sb4.toString());
                        }
                    }
                });
            }
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$11.2
                @Override // java.lang.Runnable
                public final void run() {
                    Button jumpToRbotButton = MainActivity$onCreate$11.this.$jumpToRbotButton;
                    Intrinsics.checkExpressionValueIsNotNull(jumpToRbotButton, "jumpToRbotButton");
                    jumpToRbotButton.setVisibility(0);
                    ((CLoadingView) MainActivity$onCreate$11.this.this$0.findViewById(2131230921)).hide();
                    View findViewById = MainActivity$onCreate$11.this.this$0.findViewById(2131230942);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<TextView>(R.id.open_info)");
                    ((TextView) findViewById).setVisibility(4);
                }
            });
        } catch (Exception e) {
            Pdlog.m3273d("sss", "exception: " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.rgbdviewer.MainActivity$onCreate$11$3 */
    /* loaded from: classes.dex */
    public static final class RunnableC56793 implements Runnable {
        RunnableC56793() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (MainActivity.access$getLeftView$p(MainActivity$onCreate$11.this.this$0).getConfigState() == RGBDView.ConfigState.AutoDetect && MainActivity.access$getRightView$p(MainActivity$onCreate$11.this.this$0).getConfigState() == RGBDView.ConfigState.AutoDetect) {
                if (HardwareManager.INSTANCE.getRgbdVersion() == 2 && !MainActivity$onCreate$11.this.this$0.downRgbdConfigExist()) {
                    MainActivity$onCreate$11.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity.onCreate.11.3.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            MainActivity$onCreate$11.this.this$0.saveConfig(false);
                            MainActivity$onCreate$11.this.$downRGBDCheckButton.performClick();
                        }
                    });
                } else {
                    MainActivity$onCreate$11.this.this$0.runOnUiThread(new AnonymousClass2());
                }
            }
        }

        /* compiled from: MainActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
        /* renamed from: com.pudutech.rgbdviewer.MainActivity$onCreate$11$3$2, reason: invalid class name */
        /* loaded from: classes.dex */
        static final class AnonymousClass2 implements Runnable {
            AnonymousClass2() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                CFullScreenConfirmDialogFragment.Builder builder = new CFullScreenConfirmDialogFragment.Builder();
                String string = MainActivity$onCreate$11.this.this$0.getResources().getString(2131558430);
                Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.string.auto_detect)");
                CFullScreenConfirmDialogFragment.Builder tips = builder.setTips(string);
                String string2 = MainActivity$onCreate$11.this.this$0.getResources().getString(2131558457);
                Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(R.string.main_ok)");
                CFullScreenConfirmDialogFragment.Builder positiveButton = tips.setPositiveButton(string2, new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$11$3$2$dialogFragment$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MainActivity$onCreate$11.this.this$0.saveConfig(false);
                        MainActivity$onCreate$11.this.this$0.jump2Robot((MainActivity) MainActivity$onCreate$11.this.$ctx.element);
                    }
                });
                String string3 = MainActivity$onCreate$11.this.this$0.getResources().getString(2131558452);
                Intrinsics.checkExpressionValueIsNotNull(string3, "resources.getString(R.string.main_cancel)");
                positiveButton.setNegativeButton(string3, new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$11$3$2$dialogFragment$2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                    }
                }).setBackgroundColor(Color.parseColor("#30000000")).build().show(MainActivity$onCreate$11.this.this$0.getSupportFragmentManager(), MainActivity.class.getSimpleName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.rgbdviewer.MainActivity$onCreate$11$4", m3970f = "MainActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.rgbdviewer.MainActivity$onCreate$11$4 */
    /* loaded from: classes.dex */
    public static final class C56804 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ RGBDData $rgbdData;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7156p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C56804(RGBDData rGBDData, Continuation continuation) {
            super(2, continuation);
            this.$rgbdData = rGBDData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C56804 c56804 = new C56804(this.$rgbdData, completion);
            c56804.f7156p$ = (CoroutineScope) obj;
            return c56804;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C56804) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                synchronized (this.f7156p$) {
                    if (Intrinsics.areEqual(MainActivity.access$getLeftView$p(MainActivity$onCreate$11.this.this$0).getSerialNum(), this.$rgbdData.getSerialNum())) {
                        MainActivity$onCreate$11.this.this$0.showView(MainActivity.access$getLeftView$p(MainActivity$onCreate$11.this.this$0), this.$rgbdData);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.rgbdviewer.MainActivity$onCreate$11$5", m3970f = "MainActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.rgbdviewer.MainActivity$onCreate$11$5 */
    /* loaded from: classes.dex */
    public static final class C56815 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ RGBDData $rgbdData;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7157p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C56815(RGBDData rGBDData, Continuation continuation) {
            super(2, continuation);
            this.$rgbdData = rGBDData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C56815 c56815 = new C56815(this.$rgbdData, completion);
            c56815.f7157p$ = (CoroutineScope) obj;
            return c56815;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C56815) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                synchronized (this.f7157p$) {
                    if (Intrinsics.areEqual(MainActivity.access$getRightView$p(MainActivity$onCreate$11.this.this$0).getSerialNum(), this.$rgbdData.getSerialNum())) {
                        MainActivity$onCreate$11.this.this$0.showView(MainActivity.access$getRightView$p(MainActivity$onCreate$11.this.this$0), this.$rgbdData);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.rgbdviewer.MainActivity$onCreate$11$6", m3970f = "MainActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.rgbdviewer.MainActivity$onCreate$11$6 */
    /* loaded from: classes.dex */
    public static final class C56826 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ RGBDData $rgbdData;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7158p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C56826(RGBDData rGBDData, Continuation continuation) {
            super(2, continuation);
            this.$rgbdData = rGBDData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C56826 c56826 = new C56826(this.$rgbdData, completion);
            c56826.f7158p$ = (CoroutineScope) obj;
            return c56826;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C56826) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                synchronized (this.f7158p$) {
                    if (Intrinsics.areEqual(MainActivity.access$getCenterView$p(MainActivity$onCreate$11.this.this$0).getSerialNum(), this.$rgbdData.getSerialNum())) {
                        MainActivity$onCreate$11.this.this$0.showView(MainActivity.access$getCenterView$p(MainActivity$onCreate$11.this.this$0), this.$rgbdData);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
