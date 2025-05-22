package com.pudutech.robot.peripherals.interf;

import android.content.Context;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.robot.peripherals.config.LightBeltAnimation;
import com.pudutech.robot.peripherals.config.LightBeltType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: IRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H&J\u0012\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J)\u0010\r\u001a\u00020\u00032\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f\"\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H&¢\u0006\u0002\u0010\u0013J)\u0010\u0014\u001a\u00020\u00032\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f\"\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H'¢\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u001aH&J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0006H&J\b\u0010\u001f\u001a\u00020\u0003H&J\b\u0010 \u001a\u00020\u0003H&J\b\u0010!\u001a\u00020\u0003H&J!\u0010\"\u001a\u00020\u00032\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f\"\u00020\u0010H&¢\u0006\u0002\u0010#¨\u0006$"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/interf/IRobotPeripherals;", "", "addHardWareConnectListener", "", "l", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isConnect", "addShutdownEventListener", "shutdownEventListener", "Lcom/pudutech/robot/peripherals/interf/IShutdownEventListener;", "controlLight", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", "ani", "Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;)V", "controlLightBelt", "assetFileName", "", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Ljava/lang/String;)V", "debugOpen", TransferTable.COLUMN_KEY, "Lcom/pudutech/mirsdk/hardware/IHardware;", "init", "context", "Landroid/content/Context;", "isMock", "release", "removeHardWareConnectListener", "shutdown", "stopLightBelt", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;)V", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IRobotPeripherals {
    void addHardWareConnectListener(Function1<? super Boolean, Unit> l);

    void addShutdownEventListener(IShutdownEventListener shutdownEventListener);

    void controlLight(LightBeltType[] lightBelts, LightBeltAnimation ani);

    void controlLightBelt(LightBeltType[] lightBelts, String assetFileName);

    void debugOpen(String key, IHardware l);

    void init(Context context, boolean isMock);

    void release();

    void removeHardWareConnectListener();

    void shutdown();

    void stopLightBelt(LightBeltType... lightBelts);
}
