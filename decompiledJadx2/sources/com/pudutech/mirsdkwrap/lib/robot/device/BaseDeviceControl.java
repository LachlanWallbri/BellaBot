package com.pudutech.mirsdkwrap.lib.robot.device;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import kotlinx.coroutines.TimeoutKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseDeviceControl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b \u0018\u0000 (2\u00020\u0001:\u0001(B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001d\u001a\u00020\u0017J\u0011\u0010\u001e\u001a\u00020\u0017H¤@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0006\u0010 \u001a\u00020\u0017J\u0011\u0010!\u001a\u00020\u0017H¤@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\b\u0010\"\u001a\u00020\u0017H\u0014J\u0013\u0010#\u001a\u00020\nH\u0080@ø\u0001\u0000¢\u0006\u0004\b$\u0010\u001fJ\b\u0010%\u001a\u00020\u0017H\u0002J\u0019\u0010&\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010'R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\nX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR+\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\nX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\r\"\u0004\b\u001c\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/device/BaseDeviceControl;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "checkJob", "Lkotlinx/coroutines/Job;", ES6Iterator.VALUE_PROPERTY, "", "currentDeviceSwitch", "getCurrentDeviceSwitch", "()Z", "setCurrentDeviceSwitch", "(Z)V", "isCheckOpenResult", "setCheckOpenResult", "onRestartStateChangeListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "b", "", "restartCount", "", "setSwitch", "getSetSwitch$module_robot_mirsdk_wrapper_release", "setSetSwitch$module_robot_mirsdk_wrapper_release", "close", "closeDevice", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "open", "openDevice", "resetDevice", "restart", "restart$module_robot_mirsdk_wrapper_release", "switch", "switchSync", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class BaseDeviceControl {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ExecutorCoroutineDispatcher deviceControlWork = ThreadPoolDispatcherKt.newSingleThreadContext("deviceControlWork");
    private Job checkJob;
    private boolean currentDeviceSwitch;
    private Function1<? super Boolean, Unit> onRestartStateChangeListener;
    private int restartCount;
    private boolean setSwitch;
    private final String TAG = "BaseDeviceControl";
    private boolean isCheckOpenResult = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object closeDevice(Continuation<? super Unit> continuation);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object openDevice(Continuation<? super Unit> continuation);

    public String getTAG() {
        return this.TAG;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: BaseDeviceControl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/device/BaseDeviceControl$Companion;", "", "()V", "deviceControlWork", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "deviceControlWork$annotations", "getDeviceControlWork", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        protected static /* synthetic */ void deviceControlWork$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        protected final ExecutorCoroutineDispatcher getDeviceControlWork() {
            return BaseDeviceControl.deviceControlWork;
        }
    }

    /* renamed from: isCheckOpenResult, reason: from getter */
    protected boolean getIsCheckOpenResult() {
        return this.isCheckOpenResult;
    }

    protected void setCheckOpenResult(boolean z) {
        this.isCheckOpenResult = z;
    }

    public final synchronized boolean getCurrentDeviceSwitch() {
        return this.currentDeviceSwitch;
    }

    public final synchronized void setCurrentDeviceSwitch(boolean z) {
        this.currentDeviceSwitch = z;
        if (this.onRestartStateChangeListener != null) {
            Pdlog.m3273d(getTAG(), "currentDeviceSwitch set " + z + " and onRestartStateChangeListener is not null " + this.onRestartStateChangeListener);
            Function1<? super Boolean, Unit> function1 = this.onRestartStateChangeListener;
            if (function1 != null) {
                function1.invoke(Boolean.valueOf(z));
            }
        } else if (z != getSetSwitch() && getIsCheckOpenResult()) {
            Pdlog.m3273d(getTAG(), "currentDeviceSwitch set " + z + ", not same with setSwitch = " + getSetSwitch());
            m4469switch();
        }
    }

    /* renamed from: getSetSwitch$module_robot_mirsdk_wrapper_release, reason: from getter */
    public boolean getSetSwitch() {
        return this.setSwitch;
    }

    public void setSetSwitch$module_robot_mirsdk_wrapper_release(boolean z) {
        this.setSwitch = z;
    }

    public final synchronized void open() {
        setSetSwitch$module_robot_mirsdk_wrapper_release(true);
        Pdlog.m3273d(getTAG(), "open, currentDeviceSwitch = " + this.currentDeviceSwitch);
        if (this.currentDeviceSwitch) {
            return;
        }
        m4469switch();
    }

    public final synchronized void close() {
        setSetSwitch$module_robot_mirsdk_wrapper_release(false);
        Pdlog.m3273d(getTAG(), "close, currentDeviceSwitch = " + this.currentDeviceSwitch);
        if (this.currentDeviceSwitch) {
            m4469switch();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: switch, reason: not valid java name */
    public final void m4469switch() {
        Job launch$default;
        Pdlog.m3273d(getTAG(), "switch " + getSetSwitch() + " , " + this.currentDeviceSwitch);
        if (this.checkJob == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, deviceControlWork, null, new BaseDeviceControl$switch$1(this, null), 2, null);
            this.checkJob = launch$default;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, deviceControlWork, null, new BaseDeviceControl$switch$2(this, null), 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0113 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0111 -> B:11:0x0041). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object restart$module_robot_mirsdk_wrapper_release(Continuation<? super Boolean> continuation) {
        BaseDeviceControl$restart$1 baseDeviceControl$restart$1;
        int i;
        BaseDeviceControl baseDeviceControl;
        Object obj;
        BaseDeviceControl$restart$1 baseDeviceControl$restart$12;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        Boolean bool;
        if (continuation instanceof BaseDeviceControl$restart$1) {
            baseDeviceControl$restart$1 = (BaseDeviceControl$restart$1) continuation;
            if ((baseDeviceControl$restart$1.label & Integer.MIN_VALUE) != 0) {
                baseDeviceControl$restart$1.label -= Integer.MIN_VALUE;
                Object obj2 = baseDeviceControl$restart$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = baseDeviceControl$restart$1.label;
                boolean z = false;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    this.restartCount = 0;
                    Pdlog.m3273d(getTAG(), "restart setSwitch:" + getSetSwitch() + " , currentDeviceSwitch:" + this.currentDeviceSwitch + " , restartCount:" + this.restartCount);
                    baseDeviceControl = this;
                    obj = coroutine_suspended;
                    baseDeviceControl$restart$12 = baseDeviceControl$restart$1;
                    i2 = 10;
                    i3 = 0;
                    if (i3 >= i2) {
                    }
                } else if (i == 1) {
                    int i8 = baseDeviceControl$restart$1.I$2;
                    int i9 = baseDeviceControl$restart$1.I$1;
                    int i10 = baseDeviceControl$restart$1.I$0;
                    baseDeviceControl = (BaseDeviceControl) baseDeviceControl$restart$1.L$0;
                    ResultKt.throwOnFailure(obj2);
                    i7 = i8;
                    i4 = 1;
                    i6 = i9;
                    i5 = i10;
                    if (baseDeviceControl.restartCount > 0) {
                    }
                    String tag = baseDeviceControl.getTAG();
                    Object[] objArr = new Object[i4];
                    objArr[0] = "close finish ";
                    Pdlog.m3273d(tag, objArr);
                    long j = 10000 + (baseDeviceControl.restartCount * 10000);
                    baseDeviceControl = baseDeviceControl;
                    BaseDeviceControl$restart$$inlined$repeat$lambda$2 baseDeviceControl$restart$$inlined$repeat$lambda$2 = new BaseDeviceControl$restart$$inlined$repeat$lambda$2(null, baseDeviceControl, baseDeviceControl$restart$1);
                    baseDeviceControl$restart$1.L$0 = baseDeviceControl;
                    baseDeviceControl$restart$1.I$0 = i5;
                    baseDeviceControl$restart$1.I$1 = i6;
                    baseDeviceControl$restart$1.I$2 = i7;
                    baseDeviceControl$restart$1.label = 2;
                    obj2 = TimeoutKt.withTimeoutOrNull(j, baseDeviceControl$restart$$inlined$repeat$lambda$2, baseDeviceControl$restart$1);
                    if (obj2 == coroutine_suspended) {
                    }
                    Object obj3 = coroutine_suspended;
                    baseDeviceControl$restart$12 = baseDeviceControl$restart$1;
                    i2 = i6;
                    obj = obj3;
                    bool = (Boolean) obj2;
                    Pdlog.m3273d(baseDeviceControl.getTAG(), "restart result = " + bool);
                    if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                    }
                } else if (i == 2) {
                    int i11 = baseDeviceControl$restart$1.I$2;
                    i6 = baseDeviceControl$restart$1.I$1;
                    i5 = baseDeviceControl$restart$1.I$0;
                    BaseDeviceControl baseDeviceControl2 = (BaseDeviceControl) baseDeviceControl$restart$1.L$0;
                    ResultKt.throwOnFailure(obj2);
                    baseDeviceControl = baseDeviceControl2;
                    Object obj32 = coroutine_suspended;
                    baseDeviceControl$restart$12 = baseDeviceControl$restart$1;
                    i2 = i6;
                    obj = obj32;
                    bool = (Boolean) obj2;
                    Pdlog.m3273d(baseDeviceControl.getTAG(), "restart result = " + bool);
                    if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                        baseDeviceControl.restartCount = 0;
                        return Boxing.boxBoolean(true);
                    }
                    baseDeviceControl.restartCount++;
                    if (baseDeviceControl.restartCount > 2) {
                        baseDeviceControl.restartCount = 2;
                    }
                    i3 = i5 + 1;
                    z = false;
                    if (i3 >= i2) {
                        int intValue = Boxing.boxInt(i3).intValue();
                        long j2 = 10000 + (baseDeviceControl.restartCount * 10000);
                        BaseDeviceControl$restart$$inlined$repeat$lambda$1 baseDeviceControl$restart$$inlined$repeat$lambda$1 = new BaseDeviceControl$restart$$inlined$repeat$lambda$1(null, baseDeviceControl, baseDeviceControl$restart$12);
                        baseDeviceControl$restart$12.L$0 = baseDeviceControl;
                        baseDeviceControl$restart$12.I$0 = i3;
                        baseDeviceControl$restart$12.I$1 = i2;
                        baseDeviceControl$restart$12.I$2 = intValue;
                        i4 = 1;
                        baseDeviceControl$restart$12.label = 1;
                        if (TimeoutKt.withTimeoutOrNull(j2, baseDeviceControl$restart$$inlined$repeat$lambda$1, baseDeviceControl$restart$12) == obj) {
                            return obj;
                        }
                        i5 = i3;
                        i7 = intValue;
                        Object obj4 = obj;
                        i6 = i2;
                        baseDeviceControl$restart$1 = baseDeviceControl$restart$12;
                        coroutine_suspended = obj4;
                        if (baseDeviceControl.restartCount > 0) {
                            baseDeviceControl.resetDevice();
                        }
                        String tag2 = baseDeviceControl.getTAG();
                        Object[] objArr2 = new Object[i4];
                        objArr2[0] = "close finish ";
                        Pdlog.m3273d(tag2, objArr2);
                        long j3 = 10000 + (baseDeviceControl.restartCount * 10000);
                        baseDeviceControl = baseDeviceControl;
                        BaseDeviceControl$restart$$inlined$repeat$lambda$2 baseDeviceControl$restart$$inlined$repeat$lambda$22 = new BaseDeviceControl$restart$$inlined$repeat$lambda$2(null, baseDeviceControl, baseDeviceControl$restart$1);
                        baseDeviceControl$restart$1.L$0 = baseDeviceControl;
                        baseDeviceControl$restart$1.I$0 = i5;
                        baseDeviceControl$restart$1.I$1 = i6;
                        baseDeviceControl$restart$1.I$2 = i7;
                        baseDeviceControl$restart$1.label = 2;
                        obj2 = TimeoutKt.withTimeoutOrNull(j3, baseDeviceControl$restart$$inlined$repeat$lambda$22, baseDeviceControl$restart$1);
                        if (obj2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj322 = coroutine_suspended;
                        baseDeviceControl$restart$12 = baseDeviceControl$restart$1;
                        i2 = i6;
                        obj = obj322;
                        bool = (Boolean) obj2;
                        Pdlog.m3273d(baseDeviceControl.getTAG(), "restart result = " + bool);
                        if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                        }
                    } else {
                        return Boxing.boxBoolean(z);
                    }
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        baseDeviceControl$restart$1 = new BaseDeviceControl$restart$1(this, continuation);
        Object obj22 = baseDeviceControl$restart$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = baseDeviceControl$restart$1.label;
        boolean z2 = false;
        if (i != 0) {
        }
    }

    protected void resetDevice() {
        Pdlog.m3273d(getTAG(), "resetDevice not impl");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object switchSync(final boolean z, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        if (getCurrentDeviceSwitch() == z && getSetSwitch() == z) {
            Boolean boxBoolean = Boxing.boxBoolean(true);
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationImpl2.resumeWith(Result.m4510constructorimpl(boxBoolean));
        }
        this.onRestartStateChangeListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.device.BaseDeviceControl$switchSync$$inlined$suspendCancellableCoroutine$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z2) {
                Pdlog.m3273d(this.getTAG(), "switchSync , device = " + z2 + " , control = " + z);
                this.onRestartStateChangeListener = (Function1) null;
                if (CancellableContinuation.this.isActive()) {
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    Boolean valueOf = Boolean.valueOf(z == z2);
                    Result.Companion companion2 = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m4510constructorimpl(valueOf));
                }
            }
        };
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, INSTANCE.getDeviceControlWork(), null, new C5361x7e94ef27(null, this, z), 2, null);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
