package com.pudutech.bumblebee.robot;

import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.Channel;
import com.pudutech.bumblebee.robot.remote_device.LoRaChannelManager2;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, m3961d2 = {"com/pudutech/bumblebee/robot/RobotService$iBinder$1$switchLoRaChannel$1", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$OnSetupChannelListener;", "onFailed", "", "errMsg", "", "resetFailedType", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaChannelManager2$ResetFailedType;", "onSetting", "onSucceed", "channel", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Channel;", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RobotService$iBinder$1$switchLoRaChannel$1 implements LoRaChannelManager2.OnSetupChannelListener {
    final /* synthetic */ int $channelId;
    final /* synthetic */ RobotService$iBinder$1 this$0;

    RobotService$iBinder$1$switchLoRaChannel$1(RobotService$iBinder$1 robotService$iBinder$1, int i) {
        this.this$0 = robotService$iBinder$1;
        this.$channelId = i;
    }

    @Override // com.pudutech.bumblebee.robot.remote_device.LoRaChannelManager2.OnSetupChannelListener
    public void onSetting() {
        ThreadSafeListener threadSafeListener;
        threadSafeListener = this.this$0.recycleRobotListener;
        threadSafeListener.notify(new Function2<IRecycleRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$switchLoRaChannel$1$onSetting$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IRecycleRobotListener iRecycleRobotListener, String str) {
                invoke2(iRecycleRobotListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IRecycleRobotListener it, String name) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                it.onLoRaChannelSwitchStatus(RobotService$iBinder$1$switchLoRaChannel$1.this.$channelId, 0);
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot.remote_device.LoRaChannelManager2.OnSetupChannelListener
    public void onSucceed(Channel channel) {
        ThreadSafeListener threadSafeListener;
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        threadSafeListener = this.this$0.recycleRobotListener;
        threadSafeListener.notify(new Function2<IRecycleRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$switchLoRaChannel$1$onSucceed$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IRecycleRobotListener iRecycleRobotListener, String str) {
                invoke2(iRecycleRobotListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IRecycleRobotListener it, String name) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                it.onLoRaChannelSwitchStatus(RobotService$iBinder$1$switchLoRaChannel$1.this.$channelId, 1);
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot.remote_device.LoRaChannelManager2.OnSetupChannelListener
    public void onFailed(String errMsg, LoRaChannelManager2.ResetFailedType resetFailedType) {
        ThreadSafeListener threadSafeListener;
        threadSafeListener = this.this$0.recycleRobotListener;
        threadSafeListener.notify(new Function2<IRecycleRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$switchLoRaChannel$1$onFailed$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IRecycleRobotListener iRecycleRobotListener, String str) {
                invoke2(iRecycleRobotListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IRecycleRobotListener it, String name) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                it.onLoRaChannelSwitchStatus(RobotService$iBinder$1$switchLoRaChannel$1.this.$channelId, -1);
            }
        });
    }
}
