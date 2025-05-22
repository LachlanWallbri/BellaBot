package com.pudutech.mirsdk.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Process;
import android.view.View;
import com.pudutech.base.Tools;
import com.pudutech.bumblebee.BuildConfig;
import com.pudutech.mirsdk.FunctionScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$toolsWorkSpace$3 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$toolsWorkSpace$3(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$toolsWorkSpace$3$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$toolsWorkSpace$3$1 */
    /* loaded from: classes5.dex */
    public static final class C48771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5701p$;

        C48771(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48771 c48771 = new C48771(completion);
            c48771.f5701p$ = (CoroutineScope) obj;
            return c48771;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: MirSDKActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$toolsWorkSpace$3$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$toolsWorkSpace$3$1$1, reason: invalid class name */
        /* loaded from: classes4.dex */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;

            /* renamed from: p$ */
            private CoroutineScope f5702p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                anonymousClass1.f5702p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f5702p$;
                Intent launchIntentForPackage = MirSDKActivity$toolsWorkSpace$3.this.this$0.getPackageManager().getLaunchIntentForPackage("com.pudutech.factory_test");
                if (launchIntentForPackage != null) {
                    Object systemService = MirSDKActivity$toolsWorkSpace$3.this.this$0.getSystemService("activity");
                    if (systemService == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
                    }
                    for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
                        if (runningAppProcessInfo.processName.equals(BuildConfig.APPLICATION_ID)) {
                            Process.killProcess(runningAppProcessInfo.pid);
                        } else if (runningAppProcessInfo.processName.equals("com.pudutech.pdrobot")) {
                            Process.killProcess(runningAppProcessInfo.pid);
                        } else if (runningAppProcessInfo.processName.equals("com.pudutech.recycle.robot")) {
                            Process.killProcess(runningAppProcessInfo.pid);
                        } else if (runningAppProcessInfo.processName.equals("com.pudutech.robot.ninetails")) {
                            Process.killProcess(runningAppProcessInfo.pid);
                        } else if (runningAppProcessInfo.processName.equals("com.pudutech.robot.peanut")) {
                            Process.killProcess(runningAppProcessInfo.pid);
                        } else if (runningAppProcessInfo.processName.equals("com.pudutech.robot.firefox")) {
                            Process.killProcess(runningAppProcessInfo.pid);
                        }
                    }
                    MirSDKActivity$toolsWorkSpace$3.this.this$0.startActivity(launchIntentForPackage);
                    Process.killProcess(Process.myPid());
                    System.exit(0);
                }
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5701p$;
            Intent launchIntentForPackage = MirSDKActivity$toolsWorkSpace$3.this.this$0.getPackageManager().getLaunchIntentForPackage("com.pudutech.robot.peanut");
            if (launchIntentForPackage != null) {
                MirSDKActivity$toolsWorkSpace$3.this.this$0.startActivity(launchIntentForPackage);
                Object systemService = MirSDKActivity$toolsWorkSpace$3.this.this$0.getSystemService("activity");
                if (systemService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
                }
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
                    if (runningAppProcessInfo.processName.equals(BuildConfig.APPLICATION_ID)) {
                        Tools.execCommand("am force-stop " + runningAppProcessInfo.processName, true);
                    } else if (runningAppProcessInfo.processName.equals("com.pudutech.pdrobot")) {
                        Tools.execCommand("am force-stop " + runningAppProcessInfo.processName, true);
                    } else if (runningAppProcessInfo.processName.equals("com.pudutech.recycle.robot")) {
                        Tools.execCommand("am force-stop " + runningAppProcessInfo.processName, true);
                    } else if (runningAppProcessInfo.processName.equals("com.pudutech.robot.ninetails")) {
                        Tools.execCommand("am force-stop " + runningAppProcessInfo.processName, true);
                    } else if (runningAppProcessInfo.processName.equals("com.pudutech.robot.firefox")) {
                        Tools.execCommand("am force-stop " + runningAppProcessInfo.processName, true);
                    }
                }
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
            MirSDKActivity$toolsWorkSpace$3.this.this$0.startActivity(launchIntentForPackage);
            Process.killProcess(Process.myPid());
            System.exit(0);
            return Unit.INSTANCE;
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new C48771(null), 2, null);
    }
}
