package com.pudutech.robot.peripherals.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.robot.peripherals.config.LightBeltAnimation;
import com.pudutech.robot.peripherals.config.LightBeltAnimationColor;
import com.pudutech.robot.peripherals.config.LightBeltAnimationFrame;
import com.pudutech.robot.peripherals.config.LightBeltAnimationType;
import com.pudutech.robot.peripherals.config.LightBeltType;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: LightBeltsPlayHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\bÀ\u0002\u0018\u00002\u00020\u0001:\u00018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J5\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001e0\u001d\"\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\u0017H\u0000¢\u0006\u0004\b\"\u0010#J'\u0010$\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001e0\u001d\"\u00020\u001e2\u0006\u0010%\u001a\u00020&¢\u0006\u0002\u0010'J\u0018\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\"\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010/J\"\u00100\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b1\u0010/J\"\u00102\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b3\u0010/J#\u00104\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001e0\u001d\"\u00020\u001eH\u0000¢\u0006\u0004\b5\u00106J\u0010\u00107\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R-\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012j\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00069"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/common/LightBeltsPlayHelper;", "", "()V", "TAG", "", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "Lkotlin/Lazy;", "hardwareInterface", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "getHardwareInterface", "()Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "setHardwareInterface", "(Lcom/pudutech/mirsdk/hardware/HardwareInterface;)V", "lightMap", "Ljava/util/HashMap;", "Lkotlin/UByte;", "Lcom/pudutech/robot/peripherals/common/PlayLightAnimation;", "Lkotlin/collections/HashMap;", "protocolsType", "Lcom/pudutech/robot/peripherals/common/LightBeltsPlayHelper$ProtocolsType;", "stopLightColor", "Lcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;", "play", "", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", "ani", "Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;", "pt", "play$module_robot_peripherals_release", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;Lcom/pudutech/robot/peripherals/common/LightBeltsPlayHelper$ProtocolsType;)V", "playBreathing", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/common/BreathingLightColor;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/common/BreathingLightColor;)V", "playLight", "i", "", "sendLightCan", "id", C3898x.f4339h, "sendLightCan-wO8YFcA", "(BLcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;)V", "sendLightCan2", "sendLightCan2-wO8YFcA", "sendLightCanByProtocolsType", "sendLightCanByProtocolsType-wO8YFcA", "stop", "stop$module_robot_peripherals_release", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;)V", "stopPlay", "ProtocolsType", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LightBeltsPlayHelper {
    private static HardwareInterface hardwareInterface;
    public static final LightBeltsPlayHelper INSTANCE = new LightBeltsPlayHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static ProtocolsType protocolsType = ProtocolsType.One;
    private static final HashMap<UByte, PlayLightAnimation> lightMap = new HashMap<>();
    private static final LightBeltAnimationFrame stopLightColor = new LightBeltAnimationFrame(new LightBeltAnimationColor(0, 0, 0, false, 15, null), new LightBeltAnimationColor(0, 0, 0, false, 15, null), 0, 4, null);

    /* renamed from: handler$delegate, reason: from kotlin metadata */
    private static final Lazy handler = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<Handler>() { // from class: com.pudutech.robot.peripherals.common.LightBeltsPlayHelper$handler$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            HandlerThread handlerThread = new HandlerThread("Light_Thread");
            handlerThread.start();
            return new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.pudutech.robot.peripherals.common.LightBeltsPlayHelper$handler$2.1
                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    if (!(message.obj instanceof PlayLightAnimation)) {
                        return true;
                    }
                    LightBeltsPlayHelper lightBeltsPlayHelper = LightBeltsPlayHelper.INSTANCE;
                    int i = message.what;
                    Object obj = message.obj;
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.common.PlayLightAnimation");
                    }
                    lightBeltsPlayHelper.playLight(i, (PlayLightAnimation) obj);
                    return true;
                }
            });
        }
    });

    /* compiled from: LightBeltsPlayHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/common/LightBeltsPlayHelper$ProtocolsType;", "", "(Ljava/lang/String;I)V", "One", "Tow", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum ProtocolsType {
        One,
        Tow
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ProtocolsType.values().length];

        static {
            $EnumSwitchMapping$0[ProtocolsType.One.ordinal()] = 1;
            $EnumSwitchMapping$0[ProtocolsType.Tow.ordinal()] = 2;
        }
    }

    private final Handler getHandler() {
        return (Handler) handler.getValue();
    }

    private LightBeltsPlayHelper() {
    }

    public final HardwareInterface getHardwareInterface() {
        return hardwareInterface;
    }

    public final void setHardwareInterface(HardwareInterface hardwareInterface2) {
        hardwareInterface = hardwareInterface2;
    }

    public static /* synthetic */ void play$module_robot_peripherals_release$default(LightBeltsPlayHelper lightBeltsPlayHelper, LightBeltType[] lightBeltTypeArr, LightBeltAnimation lightBeltAnimation, ProtocolsType protocolsType2, int i, Object obj) {
        if ((i & 4) != 0) {
            protocolsType2 = ProtocolsType.One;
        }
        lightBeltsPlayHelper.play$module_robot_peripherals_release(lightBeltTypeArr, lightBeltAnimation, protocolsType2);
    }

    public final void play$module_robot_peripherals_release(final LightBeltType[] lightBelts, final LightBeltAnimation ani, ProtocolsType pt) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(ani, "ani");
        Intrinsics.checkParameterIsNotNull(pt, "pt");
        protocolsType = pt;
        Pdlog.m3273d(TAG, "play : lightBelts = " + lightBelts + "; ani = " + ani + "; pt = " + pt);
        getHandler().post(new Runnable() { // from class: com.pudutech.robot.peripherals.common.LightBeltsPlayHelper$play$1
            @Override // java.lang.Runnable
            public final void run() {
                HashMap hashMap;
                HashMap hashMap2;
                for (LightBeltType lightBeltType : lightBelts) {
                    LightBeltsPlayHelper lightBeltsPlayHelper = LightBeltsPlayHelper.INSTANCE;
                    hashMap = LightBeltsPlayHelper.lightMap;
                    PlayLightAnimation playLightAnimation = (PlayLightAnimation) hashMap.get(UByte.m4522boximpl(lightBeltType.getValue()));
                    PlayLightAnimation playLightAnimation2 = new PlayLightAnimation(null, null, 3, null);
                    if (ani.getType() == LightBeltAnimationType.MODE) {
                        playLightAnimation2.setMode(LightBeltAnimation.copy$default(ani, null, null, 3, null));
                        playLightAnimation2.setTemp(playLightAnimation != null ? playLightAnimation.getTemp() : null);
                    } else {
                        playLightAnimation2.setMode(playLightAnimation != null ? playLightAnimation.getMode() : null);
                        playLightAnimation2.setTemp(LightBeltAnimation.copy$default(ani, null, null, 3, null));
                    }
                    LightBeltsPlayHelper lightBeltsPlayHelper2 = LightBeltsPlayHelper.INSTANCE;
                    hashMap2 = LightBeltsPlayHelper.lightMap;
                    hashMap2.put(UByte.m4522boximpl(lightBeltType.getValue()), playLightAnimation2);
                    LightBeltsPlayHelper.INSTANCE.playLight(lightBeltType.getValue() & 255, playLightAnimation2);
                }
            }
        });
    }

    public final void stop$module_robot_peripherals_release(final LightBeltType... lightBelts) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Pdlog.m3273d(TAG, "stop : lightBelts = " + lightBelts + "; ");
        getHandler().post(new Runnable() { // from class: com.pudutech.robot.peripherals.common.LightBeltsPlayHelper$stop$1
            @Override // java.lang.Runnable
            public final void run() {
                HashMap hashMap;
                LightBeltAnimationFrame lightBeltAnimationFrame;
                for (LightBeltType lightBeltType : lightBelts) {
                    LightBeltsPlayHelper.INSTANCE.stopPlay(lightBeltType.getValue() & 255);
                    LightBeltsPlayHelper lightBeltsPlayHelper = LightBeltsPlayHelper.INSTANCE;
                    hashMap = LightBeltsPlayHelper.lightMap;
                    hashMap.remove(UByte.m4522boximpl(lightBeltType.getValue()));
                    LightBeltsPlayHelper lightBeltsPlayHelper2 = LightBeltsPlayHelper.INSTANCE;
                    byte value = lightBeltType.getValue();
                    LightBeltsPlayHelper lightBeltsPlayHelper3 = LightBeltsPlayHelper.INSTANCE;
                    lightBeltAnimationFrame = LightBeltsPlayHelper.stopLightColor;
                    lightBeltsPlayHelper2.m4480sendLightCanByProtocolsTypewO8YFcA(value, lightBeltAnimationFrame);
                }
            }
        });
    }

    public final void playBreathing(final LightBeltType[] lightBelts, final BreathingLightColor color) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(color, "color");
        Pdlog.m3273d(TAG, "playBreathing : lightBelts = " + lightBelts + "; color = " + color + "; ");
        getHandler().post(new Runnable() { // from class: com.pudutech.robot.peripherals.common.LightBeltsPlayHelper$playBreathing$1
            @Override // java.lang.Runnable
            public final void run() {
                HashMap hashMap;
                for (LightBeltType lightBeltType : lightBelts) {
                    LightBeltsPlayHelper.INSTANCE.stopPlay(lightBeltType.getValue() & 255);
                    LightBeltsPlayHelper lightBeltsPlayHelper = LightBeltsPlayHelper.INSTANCE;
                    hashMap = LightBeltsPlayHelper.lightMap;
                    hashMap.remove(UByte.m4522boximpl(lightBeltType.getValue()));
                    HardwareInterface hardwareInterface2 = LightBeltsPlayHelper.INSTANCE.getHardwareInterface();
                    if (hardwareInterface2 != null) {
                        byte[] bArr = {-112, lightBeltType.getValue(), UByte.m4528constructorimpl((byte) color.getColorId()), 3, -1, 7, -48};
                        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
                        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                        hardwareInterface2.sendCAN(copyOf);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playLight(int i, PlayLightAnimation ani) {
        LightBeltAnimationFrame nextFrames;
        stopPlay(i);
        LightBeltAnimation temp = ani.getTemp();
        if (temp != null) {
            LightBeltAnimationFrame nextFrames2 = temp.getNextFrames();
            if (nextFrames2 == null) {
                ani.setTemp((LightBeltAnimation) null);
            } else {
                Message message = new Message();
                message.what = i;
                message.obj = ani;
                INSTANCE.m4480sendLightCanByProtocolsTypewO8YFcA(UByte.m4528constructorimpl((byte) i), nextFrames2);
                if (nextFrames2.getDuration() > 0) {
                    INSTANCE.getHandler().sendMessageDelayed(message, nextFrames2.getDuration());
                    return;
                }
                return;
            }
        }
        LightBeltAnimation mode = ani.getMode();
        if (mode == null || (nextFrames = mode.getNextFrames()) == null) {
            return;
        }
        Message message2 = new Message();
        message2.what = i;
        message2.obj = ani;
        INSTANCE.m4480sendLightCanByProtocolsTypewO8YFcA(UByte.m4528constructorimpl((byte) i), nextFrames);
        if (nextFrames.getDuration() <= 0) {
            return;
        }
        INSTANCE.getHandler().sendMessageDelayed(message2, nextFrames.getDuration());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendLightCanByProtocolsType-wO8YFcA, reason: not valid java name */
    public final void m4480sendLightCanByProtocolsTypewO8YFcA(byte id, LightBeltAnimationFrame f) {
        int i = WhenMappings.$EnumSwitchMapping$0[protocolsType.ordinal()];
        if (i == 1) {
            m4478sendLightCanwO8YFcA(id, f);
        } else {
            if (i != 2) {
                return;
            }
            m4479sendLightCan2wO8YFcA(id, f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopPlay(int i) {
        getHandler().removeMessages(i);
    }

    /* renamed from: sendLightCan-wO8YFcA, reason: not valid java name */
    private final void m4478sendLightCanwO8YFcA(byte id, LightBeltAnimationFrame f) {
        LightBeltAnimationColor head = f.getHead();
        HardwareInterface hardwareInterface2 = hardwareInterface;
        if (hardwareInterface2 != null) {
            byte[] bArr = {-112, id, 0, 5, UByte.m4528constructorimpl((byte) head.getR()), UByte.m4528constructorimpl((byte) head.getG()), UByte.m4528constructorimpl((byte) head.getB())};
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
            hardwareInterface2.sendCAN(copyOf);
        }
    }

    /* renamed from: sendLightCan2-wO8YFcA, reason: not valid java name */
    private final void m4479sendLightCan2wO8YFcA(byte id, LightBeltAnimationFrame f) {
        int duration = ((id & 255) << 4) & (f.getDuration() <= 1600 ? f.getDuration() : 1600) & DimensionsKt.HDPI;
        HardwareInterface hardwareInterface2 = hardwareInterface;
        if (hardwareInterface2 != null) {
            byte[] bArr = {-96, UByte.m4528constructorimpl((byte) duration), UByte.m4528constructorimpl((byte) f.getHead().getR()), UByte.m4528constructorimpl((byte) f.getHead().getG()), UByte.m4528constructorimpl((byte) f.getHead().getB()), UByte.m4528constructorimpl((byte) f.getEnd().getR()), UByte.m4528constructorimpl((byte) f.getEnd().getG()), UByte.m4528constructorimpl((byte) f.getEnd().getB())};
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
            hardwareInterface2.sendCAN(copyOf);
        }
    }
}
