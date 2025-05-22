package com.pudutech.rgbdviewer;

import android.graphics.Color;
import android.os.Process;
import android.view.View;
import android.widget.LinearLayout;
import com.pudutech.recycle.robot.p064ui.widget.CFullScreenConfirmDialogFragment;
import com.pudutech.rgbdlib.RobotType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.rgbdviewer.MainActivity$onCreate$12", m3970f = "MainActivity.kt", m3971i = {0}, m3972l = {443}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
final class MainActivity$onCreate$12 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $ctx;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7159p$;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$onCreate$12(MainActivity mainActivity, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mainActivity;
        this.$ctx = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MainActivity$onCreate$12 mainActivity$onCreate$12 = new MainActivity$onCreate$12(this.this$0, this.$ctx, completion);
        mainActivity$onCreate$12.f7159p$ = (CoroutineScope) obj;
        return mainActivity$onCreate$12;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivity$onCreate$12) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00e9, code lost:
    
        if ((r6.length == 0) != false) goto L34;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7159p$;
            MainActivity mainActivity = this.this$0;
            String string = mainActivity.getResources().getString(2131558447);
            Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.string.get_version)");
            mainActivity.setPromoteText(string);
            HardwareManager hardwareManager = HardwareManager.INSTANCE;
            MainActivity mainActivity2 = (MainActivity) this.$ctx.element;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = hardwareManager.connect(mainActivity2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (!((Boolean) obj).booleanValue()) {
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$12.1
                @Override // java.lang.Runnable
                public final void run() {
                    CFullScreenConfirmDialogFragment.Builder builder = new CFullScreenConfirmDialogFragment.Builder();
                    String string2 = MainActivity$onCreate$12.this.this$0.getResources().getString(2131558445);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(R.string.get_machine_fail)");
                    CFullScreenConfirmDialogFragment.Builder tips = builder.setTips(string2);
                    String string3 = MainActivity$onCreate$12.this.this$0.getResources().getString(2131558457);
                    Intrinsics.checkExpressionValueIsNotNull(string3, "resources.getString(R.string.main_ok)");
                    tips.setPositiveButton(string3, new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$12$1$dialogFragment$1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            Process.killProcess(Process.myPid());
                            System.exit(0);
                            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
                        }
                    }).setNegativeButton("", new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$12$1$dialogFragment$2
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                        }
                    }).setBackgroundColor(Color.parseColor("#30000000")).build().show(MainActivity$onCreate$12.this.this$0.getSupportFragmentManager(), MainActivity.class.getSimpleName());
                }
            });
            return Unit.INSTANCE;
        }
        if (HardwareManager.INSTANCE.currentRobotIsBellabot()) {
            this.this$0.setRobotType(RobotType.Bellabot);
        } else if (HardwareManager.INSTANCE.currentRobotIsRecycleDog()) {
            this.this$0.setRobotType(RobotType.RecycleDog);
            MainActivity mainActivity3 = this.this$0;
            RGBDView access$getRightView$p = MainActivity.access$getRightView$p(mainActivity3);
            MainActivity mainActivity4 = this.this$0;
            mainActivity4.rightView = MainActivity.access$getLeftView$p(mainActivity4);
            mainActivity3.leftView = access$getRightView$p;
        } else if (HardwareManager.INSTANCE.currentRobotIsNinetales()) {
            this.this$0.setRobotType(RobotType.Ninetales);
        }
        MainActivity mainActivity5 = this.this$0;
        String string2 = mainActivity5.getResources().getString(2131558474);
        Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(R.string.opening_camera)");
        mainActivity5.setPromoteText(string2);
        if (HardwareManager.INSTANCE.getRgbdVersion() != 2) {
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$12.3
                @Override // java.lang.Runnable
                public final void run() {
                    LinearLayout centerLayout = (LinearLayout) MainActivity$onCreate$12.this.this$0._$_findCachedViewById(C5692R.id.centerLayout);
                    Intrinsics.checkExpressionValueIsNotNull(centerLayout, "centerLayout");
                    centerLayout.setVisibility(8);
                }
            });
        }
        String[] start = MainActivity.access$getRgbdSensor$p(this.this$0).start(HardwareManager.INSTANCE.getRgbdVersion(), this.this$0.getRobotType());
        if (start != null) {
        }
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$12.4
            @Override // java.lang.Runnable
            public final void run() {
                CFullScreenConfirmDialogFragment.Builder builder = new CFullScreenConfirmDialogFragment.Builder();
                String string3 = MainActivity$onCreate$12.this.this$0.getResources().getString(2131558475);
                Intrinsics.checkExpressionValueIsNotNull(string3, "resources.getString(R.string.opening_camera_fail)");
                CFullScreenConfirmDialogFragment.Builder tips = builder.setTips(string3);
                String string4 = MainActivity$onCreate$12.this.this$0.getResources().getString(2131558457);
                Intrinsics.checkExpressionValueIsNotNull(string4, "resources.getString(R.string.main_ok)");
                tips.setPositiveButton(string4, new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$12$4$dialogFragment$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        Process.killProcess(Process.myPid());
                        System.exit(0);
                        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
                    }
                }).setNegativeButton("", new View.OnClickListener() { // from class: com.pudutech.rgbdviewer.MainActivity$onCreate$12$4$dialogFragment$2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                    }
                }).setBackgroundColor(Color.parseColor("#30000000")).build().show(MainActivity$onCreate$12.this.this$0.getSupportFragmentManager(), MainActivity.class.getSimpleName());
            }
        });
        return Unit.INSTANCE;
    }
}
