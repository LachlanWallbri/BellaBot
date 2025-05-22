package com.pudutech.bumblebee.robot.remote_device;

import android.content.Context;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RemoteDeviceInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J8\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\nH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\fH&¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/RemoteDeviceInterface;", "", "flush", "", "getDeviceStatus", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDeviceStatus;", "open", "context", "Landroid/content/Context;", "statueListener", "Lkotlin/Function1;", "dataListener", "", MqttServiceConstants.SEND_ACTION, "byteArray", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface RemoteDeviceInterface {
    void flush();

    PeripheralDeviceStatus getDeviceStatus();

    void open(Context context, Function1<? super PeripheralDeviceStatus, Unit> statueListener, Function1<? super byte[], Unit> dataListener);

    void send(byte[] byteArray);
}
