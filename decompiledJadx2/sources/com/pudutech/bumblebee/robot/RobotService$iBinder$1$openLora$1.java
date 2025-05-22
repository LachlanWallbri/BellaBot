package com.pudutech.bumblebee.robot;

import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: RobotService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "status", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDeviceStatus;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final class RobotService$iBinder$1$openLora$1 extends Lambda implements Function1<PeripheralDeviceStatus, Unit> {
    final /* synthetic */ RobotService$iBinder$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RobotService$iBinder$1$openLora$1(RobotService$iBinder$1 robotService$iBinder$1) {
        super(1);
        this.this$0 = robotService$iBinder$1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(PeripheralDeviceStatus peripheralDeviceStatus) {
        invoke2(peripheralDeviceStatus);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final PeripheralDeviceStatus status) {
        ThreadSafeListener threadSafeListener;
        Intrinsics.checkParameterIsNotNull(status, "status");
        threadSafeListener = this.this$0.commonListener;
        threadSafeListener.notify(new Function2<IRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$openLora$1.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IRobotListener iRobotListener, String str) {
                invoke2(iRobotListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IRobotListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.onDeviceStatusChange(PeripheralDevice.LORA, PeripheralDeviceStatus.this, "");
            }
        });
    }
}
