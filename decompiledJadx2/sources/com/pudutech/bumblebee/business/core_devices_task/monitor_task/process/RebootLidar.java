package com.pudutech.bumblebee.business.core_devices_task.monitor_task.process;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.SwitchListener;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.lidar.LidarController;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: RebootLidar.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/process/RebootLidar;", "", "()V", "TAG", "", "lisenter", "Lcom/pudutech/bumblebee/business/base/SwitchListener;", "tryIt", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "turnLidarPower", "b", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RebootLidar {
    private final String TAG = "RebootLidar";
    private SwitchListener lisenter;

    /* JADX WARN: Removed duplicated region for block: B:14:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00bc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00ba -> B:11:0x003f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object tryIt(Continuation<? super Boolean> continuation) {
        RebootLidar$tryIt$1 rebootLidar$tryIt$1;
        RebootLidar rebootLidar;
        int i;
        RebootLidar rebootLidar2;
        Object obj;
        RebootLidar$tryIt$1 rebootLidar$tryIt$12;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Boolean bool;
        if (continuation instanceof RebootLidar$tryIt$1) {
            rebootLidar$tryIt$1 = (RebootLidar$tryIt$1) continuation;
            if ((rebootLidar$tryIt$1.label & Integer.MIN_VALUE) != 0) {
                rebootLidar$tryIt$1.label -= Integer.MIN_VALUE;
                rebootLidar = this;
                Object obj2 = rebootLidar$tryIt$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = rebootLidar$tryIt$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    rebootLidar2 = rebootLidar;
                    obj = coroutine_suspended;
                    rebootLidar$tryIt$12 = rebootLidar$tryIt$1;
                    i2 = Integer.MAX_VALUE;
                    i3 = 0;
                    if (i3 >= i2) {
                    }
                } else if (i == 1) {
                    int i7 = rebootLidar$tryIt$1.I$2;
                    int i8 = rebootLidar$tryIt$1.I$1;
                    int i9 = rebootLidar$tryIt$1.I$0;
                    rebootLidar2 = (RebootLidar) rebootLidar$tryIt$1.L$0;
                    ResultKt.throwOnFailure(obj2);
                    i6 = i7;
                    i5 = i8;
                    i4 = i9;
                    Pdlog.m3275i(rebootLidar2.TAG, "turn power off done");
                    RebootLidar$tryIt$$inlined$repeat$lambda$2 rebootLidar$tryIt$$inlined$repeat$lambda$2 = new RebootLidar$tryIt$$inlined$repeat$lambda$2(null, rebootLidar2, rebootLidar$tryIt$1);
                    rebootLidar$tryIt$1.L$0 = rebootLidar2;
                    rebootLidar$tryIt$1.I$0 = i4;
                    rebootLidar$tryIt$1.I$1 = i5;
                    rebootLidar$tryIt$1.I$2 = i6;
                    rebootLidar$tryIt$1.label = 2;
                    obj2 = TimeoutKt.withTimeoutOrNull(10000L, rebootLidar$tryIt$$inlined$repeat$lambda$2, rebootLidar$tryIt$1);
                    if (obj2 == coroutine_suspended) {
                    }
                    Object obj3 = coroutine_suspended;
                    rebootLidar$tryIt$12 = rebootLidar$tryIt$1;
                    i2 = i5;
                    obj = obj3;
                    bool = (Boolean) obj2;
                    Pdlog.m3275i(rebootLidar2.TAG, "turn power on done. result=" + bool);
                    if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                    }
                } else if (i == 2) {
                    int i10 = rebootLidar$tryIt$1.I$2;
                    i5 = rebootLidar$tryIt$1.I$1;
                    i4 = rebootLidar$tryIt$1.I$0;
                    RebootLidar rebootLidar3 = (RebootLidar) rebootLidar$tryIt$1.L$0;
                    ResultKt.throwOnFailure(obj2);
                    rebootLidar2 = rebootLidar3;
                    Object obj32 = coroutine_suspended;
                    rebootLidar$tryIt$12 = rebootLidar$tryIt$1;
                    i2 = i5;
                    obj = obj32;
                    bool = (Boolean) obj2;
                    Pdlog.m3275i(rebootLidar2.TAG, "turn power on done. result=" + bool);
                    if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                        SwitchListener switchListener = rebootLidar2.lisenter;
                        if (switchListener != null) {
                            CoreDevices.INSTANCE.getLidar().removeListener(switchListener);
                        }
                        return Boxing.boxBoolean(true);
                    }
                    i3 = i4 + 1;
                    if (i3 >= i2) {
                        int intValue = Boxing.boxInt(i3).intValue();
                        RebootLidar$tryIt$$inlined$repeat$lambda$1 rebootLidar$tryIt$$inlined$repeat$lambda$1 = new RebootLidar$tryIt$$inlined$repeat$lambda$1(null, rebootLidar2, rebootLidar$tryIt$12);
                        rebootLidar$tryIt$12.L$0 = rebootLidar2;
                        rebootLidar$tryIt$12.I$0 = i3;
                        rebootLidar$tryIt$12.I$1 = i2;
                        rebootLidar$tryIt$12.I$2 = intValue;
                        rebootLidar$tryIt$12.label = 1;
                        if (TimeoutKt.withTimeoutOrNull(1000L, rebootLidar$tryIt$$inlined$repeat$lambda$1, rebootLidar$tryIt$12) == obj) {
                            return obj;
                        }
                        i4 = i3;
                        i6 = intValue;
                        Object obj4 = obj;
                        i5 = i2;
                        rebootLidar$tryIt$1 = rebootLidar$tryIt$12;
                        coroutine_suspended = obj4;
                        Pdlog.m3275i(rebootLidar2.TAG, "turn power off done");
                        RebootLidar$tryIt$$inlined$repeat$lambda$2 rebootLidar$tryIt$$inlined$repeat$lambda$22 = new RebootLidar$tryIt$$inlined$repeat$lambda$2(null, rebootLidar2, rebootLidar$tryIt$1);
                        rebootLidar$tryIt$1.L$0 = rebootLidar2;
                        rebootLidar$tryIt$1.I$0 = i4;
                        rebootLidar$tryIt$1.I$1 = i5;
                        rebootLidar$tryIt$1.I$2 = i6;
                        rebootLidar$tryIt$1.label = 2;
                        obj2 = TimeoutKt.withTimeoutOrNull(10000L, rebootLidar$tryIt$$inlined$repeat$lambda$22, rebootLidar$tryIt$1);
                        if (obj2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj322 = coroutine_suspended;
                        rebootLidar$tryIt$12 = rebootLidar$tryIt$1;
                        i2 = i5;
                        obj = obj322;
                        bool = (Boolean) obj2;
                        Pdlog.m3275i(rebootLidar2.TAG, "turn power on done. result=" + bool);
                        if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                        }
                    } else {
                        SwitchListener switchListener2 = rebootLidar2.lisenter;
                        if (switchListener2 != null) {
                            CoreDevices.INSTANCE.getLidar().removeListener(switchListener2);
                        }
                        return Boxing.boxBoolean(false);
                    }
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        rebootLidar = this;
        rebootLidar$tryIt$1 = new RebootLidar$tryIt$1(rebootLidar, continuation);
        Object obj22 = rebootLidar$tryIt$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = rebootLidar$tryIt$1.label;
        if (i != 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object turnLidarPower(final boolean z, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        Pdlog.m3275i(this.TAG, "turnLidarPower " + z);
        this.lisenter = new SwitchListener() { // from class: com.pudutech.bumblebee.business.core_devices_task.monitor_task.process.RebootLidar$turnLidarPower$$inlined$suspendCancellableCoroutine$lambda$1
            @Override // com.pudutech.bumblebee.business.base.BaseValueListener
            public void onValueSet(String describe, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkParameterIsNotNull(describe, "describe");
                Pdlog.m3273d(this.TAG, "onValueSet describe=" + describe + "  oldValue=" + oldValue + "  newValue=" + newValue);
                if (newValue != null) {
                    boolean booleanValue = newValue.booleanValue();
                    if (CancellableContinuation.this.isActive()) {
                        CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                        Boolean valueOf = Boolean.valueOf(booleanValue);
                        Result.Companion companion = Result.INSTANCE;
                        cancellableContinuation.resumeWith(Result.m4510constructorimpl(valueOf));
                    }
                }
            }
        };
        LidarController lidar = CoreDevices.INSTANCE.getLidar();
        SwitchListener switchListener = this.lisenter;
        if (switchListener == null) {
            Intrinsics.throwNpe();
        }
        lidar.addListener(switchListener);
        CoreDevices.INSTANCE.getLidar().turnOn(z);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
