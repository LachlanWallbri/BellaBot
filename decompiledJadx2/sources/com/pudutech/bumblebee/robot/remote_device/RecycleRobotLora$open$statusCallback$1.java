package com.pudutech.bumblebee.robot.remote_device;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.serialport.library.ISerialPortOpenStatusCallback;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RecycleRobotLora.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/bumblebee/robot/remote_device/RecycleRobotLora$open$statusCallback$1", "Lcom/pudutech/serialport/library/ISerialPortOpenStatusCallback;", "callbackOpenStatus", "", "opened", "", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RecycleRobotLora$open$statusCallback$1 implements ISerialPortOpenStatusCallback {
    final /* synthetic */ Function1 $statueListener;
    final /* synthetic */ RecycleRobotLora this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecycleRobotLora$open$statusCallback$1(RecycleRobotLora recycleRobotLora, Function1 function1) {
        this.this$0 = recycleRobotLora;
        this.$statueListener = function1;
    }

    @Override // com.pudutech.serialport.library.ISerialPortOpenStatusCallback
    public void callbackOpenStatus(boolean opened) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "callbackOpenStatus opened=" + opened);
        this.this$0.status = opened ? PeripheralDeviceStatus.NORMAL : PeripheralDeviceStatus.DEVICE_DISCONNECT;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RecycleRobotLora$open$statusCallback$1$callbackOpenStatus$1(this, opened, null), 3, null);
    }
}
