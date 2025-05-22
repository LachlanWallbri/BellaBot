package com.pudutech.rgbdlib.activity;

import android.os.ParcelFileDescriptor;
import android.view.View;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.rgbdlib.RGBDDataCatcher;
import com.pudutech.rgbdlib.RGBDSensor;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: StressTestingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class StressTestingActivity$onCreate$2 implements View.OnClickListener {
    final /* synthetic */ StressTestingActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StressTestingActivity$onCreate$2(StressTestingActivity stressTestingActivity) {
        this.this$0 = stressTestingActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Job job;
        Job job2;
        AtomicInteger atomicInteger;
        AtomicInteger atomicInteger2;
        AtomicInteger atomicInteger3;
        AtomicInteger atomicInteger4;
        AtomicBoolean atomicBoolean;
        Job launch$default;
        Job job3;
        job = this.this$0.switchCheckingJob;
        if (job != null && job.isActive()) {
            job3 = this.this$0.switchCheckingJob;
            if (job3 != null) {
                Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
            }
            this.this$0.removeSwitchCheck();
        }
        job2 = this.this$0.fpsCheckingJob;
        if (job2 == null || !job2.isActive()) {
            RGBDSensor rgbdSensor = StressTestingActivity.INSTANCE.getRgbdSensor();
            if (rgbdSensor != null) {
                rgbdSensor.openRGBD();
            }
            this.this$0.removeFPSCheck();
            atomicInteger = this.this$0.leftFrame;
            atomicInteger.set(0);
            this.this$0.totalLeftFrame = 0;
            atomicInteger2 = this.this$0.rightFrame;
            atomicInteger2.set(0);
            this.this$0.totalRightFrame = 0;
            atomicInteger3 = this.this$0.centerFrame;
            atomicInteger3.set(0);
            this.this$0.totalCenterFrame = 0;
            atomicInteger4 = this.this$0.downFrame;
            atomicInteger4.set(0);
            this.this$0.totalDownFrame = 0;
            atomicBoolean = this.this$0.startFPSCheck;
            atomicBoolean.set(false);
            this.this$0.totalIndex = 0;
            StressTestingActivity stressTestingActivity = this.this$0;
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C56671(null), 3, null);
            stressTestingActivity.fpsCheckingJob = launch$default;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* compiled from: StressTestingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.rgbdlib.activity.StressTestingActivity$onCreate$2$1", m3970f = "StressTestingActivity.kt", m3971i = {0}, m3972l = {113}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.rgbdlib.activity.StressTestingActivity$onCreate$2$1 */
    /* loaded from: classes6.dex */
    static final class C56671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7150p$;

        C56671(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C56671 c56671 = new C56671(completion);
            c56671.f7150p$ = (CoroutineScope) obj;
            return c56671;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C56671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x00e5  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x008f  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x0099 -> B:5:0x009c). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            AtomicBoolean atomicBoolean;
            CoroutineScope coroutineScope;
            C56671 c56671;
            ThreadSafeListener downRGBDListeners;
            ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
            ThreadSafeListener<RGBDDataCatcher> rightRGBDListeners;
            ThreadSafeListener<RGBDDataCatcher> leftRGBDListeners;
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i6 = this.label;
            if (i6 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = this.f7150p$;
                RGBDSensor rgbdSensor = StressTestingActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor != null && (leftRGBDListeners = rgbdSensor.getLeftRGBDListeners()) != null) {
                    leftRGBDListeners.add("fps_check", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.StressTestingActivity.onCreate.2.1.1
                        @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                        public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                            AtomicBoolean atomicBoolean2;
                            AtomicInteger atomicInteger;
                            Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                            if (parcelFileDescriptor.getFd() != -1) {
                                atomicBoolean2 = StressTestingActivity$onCreate$2.this.this$0.startFPSCheck;
                                if (atomicBoolean2.get()) {
                                    atomicInteger = StressTestingActivity$onCreate$2.this.this$0.leftFrame;
                                    atomicInteger.incrementAndGet();
                                }
                            }
                        }
                    });
                }
                RGBDSensor rgbdSensor2 = StressTestingActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor2 != null && (rightRGBDListeners = rgbdSensor2.getRightRGBDListeners()) != null) {
                    rightRGBDListeners.add("fps_check", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.StressTestingActivity.onCreate.2.1.2
                        @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                        public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                            AtomicBoolean atomicBoolean2;
                            AtomicInteger atomicInteger;
                            Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                            if (parcelFileDescriptor.getFd() != -1) {
                                atomicBoolean2 = StressTestingActivity$onCreate$2.this.this$0.startFPSCheck;
                                if (atomicBoolean2.get()) {
                                    atomicInteger = StressTestingActivity$onCreate$2.this.this$0.rightFrame;
                                    atomicInteger.incrementAndGet();
                                }
                            }
                        }
                    });
                }
                RGBDSensor rgbdSensor3 = StressTestingActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor3 != null && (centernRGBDListeners = rgbdSensor3.getCenternRGBDListeners()) != null) {
                    centernRGBDListeners.add("fps_check", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.StressTestingActivity.onCreate.2.1.3
                        @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                        public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                            AtomicBoolean atomicBoolean2;
                            AtomicInteger atomicInteger;
                            Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                            if (parcelFileDescriptor.getFd() != -1) {
                                atomicBoolean2 = StressTestingActivity$onCreate$2.this.this$0.startFPSCheck;
                                if (atomicBoolean2.get()) {
                                    atomicInteger = StressTestingActivity$onCreate$2.this.this$0.centerFrame;
                                    atomicInteger.incrementAndGet();
                                }
                            }
                        }
                    });
                }
                RGBDSensor rgbdSensor4 = StressTestingActivity.INSTANCE.getRgbdSensor();
                if (rgbdSensor4 != null && (downRGBDListeners = rgbdSensor4.getDownRGBDListeners()) != null) {
                    downRGBDListeners.add("fps_check", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.activity.StressTestingActivity.onCreate.2.1.4
                        @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                        public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                            AtomicBoolean atomicBoolean2;
                            AtomicInteger atomicInteger;
                            Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                            if (parcelFileDescriptor.getFd() != -1) {
                                atomicBoolean2 = StressTestingActivity$onCreate$2.this.this$0.startFPSCheck;
                                if (atomicBoolean2.get()) {
                                    atomicInteger = StressTestingActivity$onCreate$2.this.this$0.downFrame;
                                    atomicInteger.incrementAndGet();
                                }
                            }
                        }
                    });
                }
                atomicBoolean = StressTestingActivity$onCreate$2.this.this$0.startFPSCheck;
                atomicBoolean.set(true);
                coroutineScope = coroutineScope2;
                c56671 = this;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            } else if (i6 == 1) {
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c56671 = this;
                StressTestingActivity stressTestingActivity = StressTestingActivity$onCreate$2.this.this$0;
                i = stressTestingActivity.totalIndex;
                stressTestingActivity.totalIndex = i + 1;
                StressTestingActivity stressTestingActivity2 = StressTestingActivity$onCreate$2.this.this$0;
                i2 = StressTestingActivity$onCreate$2.this.this$0.totalIndex;
                stressTestingActivity2.getLeftFPS(i2);
                StressTestingActivity stressTestingActivity3 = StressTestingActivity$onCreate$2.this.this$0;
                i3 = StressTestingActivity$onCreate$2.this.this$0.totalIndex;
                stressTestingActivity3.getRightFPS(i3);
                StressTestingActivity stressTestingActivity4 = StressTestingActivity$onCreate$2.this.this$0;
                i4 = StressTestingActivity$onCreate$2.this.this$0.totalIndex;
                stressTestingActivity4.getCenterFPS(i4);
                StressTestingActivity stressTestingActivity5 = StressTestingActivity$onCreate$2.this.this$0;
                i5 = StressTestingActivity$onCreate$2.this.this$0.totalIndex;
                stressTestingActivity5.getDownFPS(i5);
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    c56671.L$0 = coroutineScope;
                    c56671.label = 1;
                    if (DelayKt.delay(1000L, c56671) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    StressTestingActivity stressTestingActivity6 = StressTestingActivity$onCreate$2.this.this$0;
                    i = stressTestingActivity6.totalIndex;
                    stressTestingActivity6.totalIndex = i + 1;
                    StressTestingActivity stressTestingActivity22 = StressTestingActivity$onCreate$2.this.this$0;
                    i2 = StressTestingActivity$onCreate$2.this.this$0.totalIndex;
                    stressTestingActivity22.getLeftFPS(i2);
                    StressTestingActivity stressTestingActivity32 = StressTestingActivity$onCreate$2.this.this$0;
                    i3 = StressTestingActivity$onCreate$2.this.this$0.totalIndex;
                    stressTestingActivity32.getRightFPS(i3);
                    StressTestingActivity stressTestingActivity42 = StressTestingActivity$onCreate$2.this.this$0;
                    i4 = StressTestingActivity$onCreate$2.this.this$0.totalIndex;
                    stressTestingActivity42.getCenterFPS(i4);
                    StressTestingActivity stressTestingActivity52 = StressTestingActivity$onCreate$2.this.this$0;
                    i5 = StressTestingActivity$onCreate$2.this.this$0.totalIndex;
                    stressTestingActivity52.getDownFPS(i5);
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        return Unit.INSTANCE;
                    }
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
