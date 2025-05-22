package com.pudutech.factory_test.single_test;

import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.LidarTestActivity;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.lidar.base.LidarErrorType;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ILidarState;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.serialize.LidarStopReason;
import java.util.LinkedHashMap;
import java.util.Map;
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
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LidarTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.LidarTestActivity$autoTest$1", m3970f = "LidarTestActivity.kt", m3971i = {0, 1, 1, 1, 1}, m3972l = {281, 319}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "frameCnt", "frameNeedBeCnt", "errorsMap"}, m3975s = {"L$0", "L$0", "L$1", "I$0", "L$2"})
/* loaded from: classes.dex */
public final class LidarTestActivity$autoTest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $lidarVersion;
    final /* synthetic */ Ref.IntRef $waitTime_s;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5185p$;
    final /* synthetic */ LidarTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LidarTestActivity$autoTest$1(LidarTestActivity lidarTestActivity, Ref.IntRef intRef, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lidarTestActivity;
        this.$waitTime_s = intRef;
        this.$lidarVersion = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LidarTestActivity$autoTest$1 lidarTestActivity$autoTest$1 = new LidarTestActivity$autoTest$1(this.this$0, this.$waitTime_s, this.$lidarVersion, completion);
        lidarTestActivity$autoTest$1.f5185p$ = (CoroutineScope) obj;
        return lidarTestActivity$autoTest$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LidarTestActivity$autoTest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        final Ref.IntRef intRef;
        final LinkedHashMap linkedHashMap;
        final int i;
        LidarInterface lidarInterface;
        LidarInterface lidarInterface2;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5185p$;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                if (i2 == 2) {
                    linkedHashMap = (LinkedHashMap) this.L$2;
                    i = this.I$0;
                    intRef = (Ref.IntRef) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$autoTest$1.2
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public final void run() {
                            int i3;
                            int i4;
                            Integer num = (Integer) LidarTestActivity$autoTest$1.this.$lidarVersion.element;
                            int value = LidarVersion.getValue(LidarVersion.LTME_02A);
                            boolean z = true;
                            if (num != null && num.intValue() == value) {
                                i4 = LidarTestActivity$autoTest$1.this.this$0.checkLidarAvg;
                                if (i4 <= 90) {
                                    z = false;
                                }
                            }
                            if (linkedHashMap.isEmpty() && intRef.element > i && z) {
                                LidarTestActivity$autoTest$1.this.this$0.setStep(LidarTestActivity.Step.CHECKING);
                                LidarTestActivity$autoTest$1.this.this$0.setCases();
                                LidarTestActivity$autoTest$1.this.this$0.FSM();
                                return;
                            }
                            if (intRef.element < i) {
                                linkedHashMap.put("读取不到数据", "可能是接线故障或者 machineInfo 配置错误。 frameCnt=" + intRef.element);
                            }
                            if (!z) {
                                LinkedHashMap linkedHashMap2 = linkedHashMap;
                                StringBuilder sb = new StringBuilder();
                                sb.append("雷达平均点数过少。 checkLidarAvg=");
                                i3 = LidarTestActivity$autoTest$1.this.this$0.checkLidarAvg;
                                sb.append(i3);
                                linkedHashMap2.put("异常帧", sb.toString());
                            }
                            StringBuilder sb2 = new StringBuilder();
                            for (Map.Entry entry : linkedHashMap.entrySet()) {
                                sb2.append((String) entry.getKey());
                                sb2.append("\n");
                                sb2.append((String) entry.getValue());
                                sb2.append("\n");
                            }
                            LidarTestActivity$autoTest$1.this.this$0.getMTestItem().setFailDescription(sb2.toString());
                            LidarTestActivity$autoTest$1.this.this$0.setStep(LidarTestActivity.Step.FAIL);
                            LidarTestActivity$autoTest$1.this.this$0.FSM();
                        }
                    });
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = 0;
        final int i3 = 30;
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface != null && (lidarInterface2 = hdInterface.getLidarInterface()) != null) {
            str = this.this$0.TAG;
            lidarInterface2.addStateListener(str, new ILidarState.Stub() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$autoTest$1.1
                @Override // com.pudutech.mirsdk.hardware.ILidarState
                public void onStartScan(boolean p0, String p1) {
                }

                @Override // com.pudutech.mirsdk.hardware.ILidarState
                public void onStopScan(LidarStopReason p0) {
                }

                @Override // com.pudutech.mirsdk.hardware.ILidarState
                public void onFrameTick() {
                    Ref.IntRef.this.element++;
                }

                @Override // com.pudutech.mirsdk.hardware.ILidarState
                public void onError(String p0) {
                    if (p0 == null || Ref.IntRef.this.element <= i3) {
                        return;
                    }
                    String str2 = p0;
                    String str3 = "读取不到数据，可能是接线故障或者雷达故障";
                    if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) LidarErrorType.OPEN_FAIL.toString(), false, 2, (Object) null)) {
                        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) LidarErrorType.DISCONNECTED.toString(), false, 2, (Object) null)) {
                            str3 = "";
                        } else if (StringsKt.contains$default((CharSequence) str2, (CharSequence) LidarErrorType.ILLEGAL_FRAME.toString(), false, 2, (Object) null)) {
                            str3 = StringsKt.contains$default((CharSequence) str2, (CharSequence) "inner", false, 2, (Object) null) ? "异常帧，扫描不到挡鼠板" : "异常帧，雷达数据全为零";
                        } else if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) LidarErrorType.READ_NOTHING.toString(), false, 2, (Object) null)) {
                            str3 = "未知错误";
                        }
                    }
                    linkedHashMap2.put(str3, String.valueOf(p0));
                }
            });
        }
        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
        if (hdInterface2 != null && (lidarInterface = hdInterface2.getLidarInterface()) != null) {
            lidarInterface.open();
        }
        long j = this.$waitTime_s.element * 1000;
        this.L$0 = coroutineScope;
        this.L$1 = intRef2;
        this.I$0 = 30;
        this.L$2 = linkedHashMap2;
        this.label = 2;
        if (DelayKt.delay(j, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        intRef = intRef2;
        linkedHashMap = linkedHashMap2;
        i = 30;
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$autoTest$1.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public final void run() {
                int i32;
                int i4;
                Integer num = (Integer) LidarTestActivity$autoTest$1.this.$lidarVersion.element;
                int value = LidarVersion.getValue(LidarVersion.LTME_02A);
                boolean z = true;
                if (num != null && num.intValue() == value) {
                    i4 = LidarTestActivity$autoTest$1.this.this$0.checkLidarAvg;
                    if (i4 <= 90) {
                        z = false;
                    }
                }
                if (linkedHashMap.isEmpty() && intRef.element > i && z) {
                    LidarTestActivity$autoTest$1.this.this$0.setStep(LidarTestActivity.Step.CHECKING);
                    LidarTestActivity$autoTest$1.this.this$0.setCases();
                    LidarTestActivity$autoTest$1.this.this$0.FSM();
                    return;
                }
                if (intRef.element < i) {
                    linkedHashMap.put("读取不到数据", "可能是接线故障或者 machineInfo 配置错误。 frameCnt=" + intRef.element);
                }
                if (!z) {
                    LinkedHashMap linkedHashMap22 = linkedHashMap;
                    StringBuilder sb = new StringBuilder();
                    sb.append("雷达平均点数过少。 checkLidarAvg=");
                    i32 = LidarTestActivity$autoTest$1.this.this$0.checkLidarAvg;
                    sb.append(i32);
                    linkedHashMap22.put("异常帧", sb.toString());
                }
                StringBuilder sb2 = new StringBuilder();
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    sb2.append((String) entry.getKey());
                    sb2.append("\n");
                    sb2.append((String) entry.getValue());
                    sb2.append("\n");
                }
                LidarTestActivity$autoTest$1.this.this$0.getMTestItem().setFailDescription(sb2.toString());
                LidarTestActivity$autoTest$1.this.this$0.setStep(LidarTestActivity.Step.FAIL);
                LidarTestActivity$autoTest$1.this.this$0.FSM();
            }
        });
        return Unit.INSTANCE;
    }
}
