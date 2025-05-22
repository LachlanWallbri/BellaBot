package com.pudutech.bumblebee.presenter.robot_open_task;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.battery.Battery;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.pdmqtt.config.MosquittoMqttConfig;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
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
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotOpenHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$connectionLocalServer$1", m3970f = "RobotOpenHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class RobotOpenHelper$connectionLocalServer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4705p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RobotOpenHelper$connectionLocalServer$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotOpenHelper$connectionLocalServer$1 robotOpenHelper$connectionLocalServer$1 = new RobotOpenHelper$connectionLocalServer$1(completion);
        robotOpenHelper$connectionLocalServer$1.f4705p$ = (CoroutineScope) obj;
        return robotOpenHelper$connectionLocalServer$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotOpenHelper$connectionLocalServer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0045, code lost:
    
        if (r2 != null) goto L15;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String replace$default;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4705p$;
        RobotOpenSdk.INSTANCE.setOnEventListener(RobotOpenHelper.access$getOnOpenSdkEventListener$p(RobotOpenHelper.INSTANCE));
        RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
        Context access$getContext$p = RobotOpenHelper.access$getContext$p(RobotOpenHelper.INSTANCE);
        RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
        str = RobotOpenHelper.IOT_PRODUCTKEY;
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac != null && (replace$default = StringsKt.replace$default(mac, ":", "", false, 4, (Object) null)) != null) {
            if (replace$default == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            str2 = replace$default.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.String).toLowerCase()");
        }
        str2 = "020000000000";
        robotOpenSdk.connectMosquitto(access$getContext$p, new MosquittoMqttConfig(str, str2, RobotOpenHelper.INSTANCE.getLocalHost(), "8443"), new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$connectionLocalServer$1.1
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
                String str3;
                RobotOpenHelper$systemBatteryListener$1 robotOpenHelper$systemBatteryListener$1;
                RobotOpenHelper robotOpenHelper2 = RobotOpenHelper.INSTANCE;
                str3 = RobotOpenHelper.TAG;
                Pdlog.m3273d(str3, "startInit onSuccess");
                RobotOpenHelper.INSTANCE.chechIsOpen();
                if (RobotOpenHelper.INSTANCE.isOpen()) {
                    Battery battery = CoreDevices.INSTANCE.getBattery();
                    RobotOpenHelper robotOpenHelper3 = RobotOpenHelper.INSTANCE;
                    robotOpenHelper$systemBatteryListener$1 = RobotOpenHelper.systemBatteryListener;
                    battery.addListener(robotOpenHelper$systemBatteryListener$1);
                    RobotOpenHelper.INSTANCE.getRobotMoveStateNotify().init();
                    RobotOpenHelper.INSTANCE.getRobotPoseNotify().init();
                }
            }

            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                String str3;
                Intrinsics.checkParameterIsNotNull(e, "e");
                RobotOpenHelper robotOpenHelper2 = RobotOpenHelper.INSTANCE;
                str3 = RobotOpenHelper.TAG;
                Pdlog.m3273d(str3, "startInit onFailed : e = " + e + "; ");
                RobotOpenHelper.INSTANCE.chechIsOpen();
            }
        });
        return Unit.INSTANCE;
    }
}
