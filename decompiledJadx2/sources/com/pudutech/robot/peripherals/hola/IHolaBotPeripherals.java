package com.pudutech.robot.peripherals.hola;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.loc.C3898x;
import com.pudutech.robot.peripherals.config.LightBeltAnimationFrame;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.interf.IRobotPeripherals;
import kotlin.Metadata;

/* compiled from: IHolaBotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\b\u0010\u000b\u001a\u00020\bH&J\b\u0010\f\u001a\u00020\bH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J$\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H&J\u0012\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH&J\b\u0010\u001b\u001a\u00020\bH&J)\u0010\u001c\u001a\u00020\b2\u0012\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0\u001e\"\u00020\u001f2\u0006\u0010 \u001a\u00020!H&¢\u0006\u0002\u0010\"J\u0012\u0010#\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\u0010\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020&H&J\u0010\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\u000eH&JT\u0010)\u001a\u00020\b2\u0012\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0\u001e\"\u00020\u001f2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002002\b\b\u0002\u00102\u001a\u000200H&ø\u0001\u0000¢\u0006\u0004\b3\u00104J2\u00105\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u001f2\u0006\u00106\u001a\u0002002\u0006\u00107\u001a\u0002002\u0006\u00108\u001a\u000200H&ø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u0010\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020\u0010H&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006="}, m3961d2 = {"Lcom/pudutech/robot/peripherals/hola/IHolaBotPeripherals;", "Lcom/pudutech/robot/peripherals/interf/IRobotPeripherals;", "isMute", "", "()Z", "setMute", "(Z)V", "addFunctionPanelListener", "", "listener", "Lcom/pudutech/robot/peripherals/hola/IFunctionPanelListener;", "closeLoRa", "flushLoRa", "getFace", "Lcom/pudutech/robot/peripherals/hola/ExpressionType;", "getLoRaType", "Lcom/pudutech/robot/peripherals/hola/LoRaType;", "initLoRa", "context", "Landroid/content/Context;", "loRaStatusCallback", "Lcom/pudutech/robot/peripherals/hola/ILoRaStatusCallback;", "loRaDataReceivedCallback", "Lcom/pudutech/robot/peripherals/hola/ILoRaDataReceivedCallback;", "initNFC", "nfcSwipeCardListener", "Lcom/pudutech/robot/peripherals/hola/INFCSwipeCardListener;", "openLoRa", "playBreathing", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", C3898x.f4339h, "Lcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;)V", "removeFunctionPanelListener", "sendData", "data", "", "setFace", "expression", "setLightMode", "led", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/hola/LightColor;", "lightMode", "Lcom/pudutech/robot/peripherals/hola/LightMode;", "params2", "Lkotlin/UByte;", "params1", "params0", "setLightMode-3UDGHhA", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/hola/LightColor;Lcom/pudutech/robot/peripherals/hola/LightMode;BBB)V", "setLightRGB", "red", "green", "blue", "setLightRGB-Fh2MPcY", "(Lcom/pudutech/robot/peripherals/config/LightBeltType;BBB)V", "setLoRaType", "loRaType", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IHolaBotPeripherals extends IRobotPeripherals {
    void addFunctionPanelListener(IFunctionPanelListener listener);

    void closeLoRa();

    void flushLoRa();

    ExpressionType getFace();

    LoRaType getLoRaType();

    void initLoRa(Context context, ILoRaStatusCallback loRaStatusCallback, ILoRaDataReceivedCallback loRaDataReceivedCallback);

    void initNFC(INFCSwipeCardListener nfcSwipeCardListener);

    boolean isMute();

    void openLoRa();

    void playBreathing(LightBeltType[] lightBelts, LightBeltAnimationFrame f);

    void removeFunctionPanelListener(IFunctionPanelListener listener);

    void sendData(byte[] data);

    void setFace(ExpressionType expression);

    /* renamed from: setLightMode-3UDGHhA */
    void mo4498setLightMode3UDGHhA(LightBeltType[] led, LightColor color, LightMode lightMode, byte params2, byte params1, byte params0);

    /* renamed from: setLightRGB-Fh2MPcY */
    void mo4499setLightRGBFh2MPcY(LightBeltType led, byte red, byte green, byte blue);

    void setLoRaType(LoRaType loRaType);

    void setMute(boolean z);

    /* compiled from: IHolaBotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        /* renamed from: setLightMode-3UDGHhA$default, reason: not valid java name */
        public static /* synthetic */ void m4500setLightMode3UDGHhA$default(IHolaBotPeripherals iHolaBotPeripherals, LightBeltType[] lightBeltTypeArr, LightColor lightColor, LightMode lightMode, byte b, byte b2, byte b3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setLightMode");
            }
            iHolaBotPeripherals.mo4498setLightMode3UDGHhA(lightBeltTypeArr, lightColor, lightMode, (i & 8) != 0 ? (byte) 0 : b, (i & 16) != 0 ? (byte) 0 : b2, (i & 32) != 0 ? (byte) 0 : b3);
        }
    }
}
