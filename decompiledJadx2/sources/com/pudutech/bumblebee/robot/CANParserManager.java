package com.pudutech.bumblebee.robot;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener;
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.PowerOffEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;
import com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager;
import com.pudutech.bumblebee.robot.pallet.PalletProtocol;
import com.pudutech.bumblebee.robot.protocol_utils.UByteArrayUtilsKt;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CANParser.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0002\u0010\nJ\u001a\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u0015H\u0002ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J \u0010$\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0004\b%\u0010&JP\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u000f26\u0010)\u001a2\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u0010ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u0018\u0010,\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0004\b-\u0010.R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u008d\u0001\u0010\r\u001a~\u0012\u0004\u0012\u00020\u000f\u00124\u00122\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u00100\u000ej>\u0012\u0004\u0012\u00020\u000f\u00124\u00122\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u0010`\u0018X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R!\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u001aj\b\u0012\u0004\u0012\u00020\u000f`\u001bX\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\u00020\u0015ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/CANParserManager;", "", "hardware", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "robotListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/bumblebee/robot/aidl/IRobotListener;", "deliveryListener", "Lcom/pudutech/bumblebee/robot/aidl/IDeliveryRobotListener;", "(Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/base/architecture/ThreadSafeListener;Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "TAG", "", "canParsers", "Ljava/util/HashMap;", "Lkotlin/UByte;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "id", "Lkotlin/UByteArray;", "byteArray", "", "Lkotlin/collections/HashMap;", "checkValidBlackList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "filterIDs", "getFilterIDs", "()[B", "[B", "checkValid", "", "checkValid-GBYM_sE", "([B)Z", "parse", "parse-eUNIVaU", "(I[B)V", "regCanParser", "protocol", "parser", "regCanParser-eLRuwBU", "(BLkotlin/jvm/functions/Function2;)V", "unRegCanParser", "unRegCanParser-7apg3OU", "(B)V", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CANParserManager {
    private final String TAG;
    private final HashMap<UByte, Function2<Integer, UByteArray, Unit>> canParsers;
    private final ArrayList<UByte> checkValidBlackList;
    private final byte[] filterIDs;

    public CANParserManager(final AIDLConnection<HardwareInterface> hardware, final ThreadSafeListener<IRobotListener> robotListener, final ThreadSafeListener<IDeliveryRobotListener> deliveryListener) {
        Intrinsics.checkParameterIsNotNull(hardware, "hardware");
        Intrinsics.checkParameterIsNotNull(robotListener, "robotListener");
        Intrinsics.checkParameterIsNotNull(deliveryListener, "deliveryListener");
        this.TAG = "CAN";
        this.canParsers = new HashMap<>();
        this.checkValidBlackList = CollectionsKt.arrayListOf(UByte.m4522boximpl((byte) 96), UByte.m4522boximpl((byte) 97), UByte.m4522boximpl((byte) 98), UByte.m4522boximpl((byte) 99), UByte.m4522boximpl((byte) 100), UByte.m4522boximpl((byte) 101));
        this.filterIDs = new byte[]{-108, -111, ClassDefinitionUtils.OPS_areturn, Constans.CAN_REV_SPRAY_SYS_RESULT, Constans.CAN_REV_DIS_MODE_SWITCH, Constans.CAN_REV_UV_SYS_RESULT, Constans.CAN_REV_UV_SET_RESULT};
        this.canParsers.put(UByte.m4522boximpl((byte) -108), new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.bumblebee.robot.CANParserManager.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, final byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                final TouchPlace m4317fromValue7apg3OU = TouchPlace.INSTANCE.m4317fromValue7apg3OU(UByteArray.m4577getimpl(bytes, 1));
                ThreadSafeListener.this.notify(new Function2<IRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.CANParserManager.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        it.onTouch(TouchPlace.this, TouchState.INSTANCE.m4318fromValue7apg3OU(UByteArray.m4577getimpl(bytes, 2)));
                    }
                });
            }
        });
        this.canParsers.put(UByte.m4522boximpl((byte) -111), new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.bumblebee.robot.CANParserManager.2
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                if (PalletProtocol.INSTANCE.m4330parseGBYM_sE(bytes)) {
                    ThreadSafeListener.this.notify(new Function2<IDeliveryRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.CANParserManager.2.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IDeliveryRobotListener iDeliveryRobotListener, String str) {
                            invoke2(iDeliveryRobotListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IDeliveryRobotListener it, String str) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            it.onPalletState(PalletProtocol.INSTANCE.getPallets());
                        }
                    });
                }
            }
        });
        this.canParsers.put(UByte.m4522boximpl(ClassDefinitionUtils.OPS_areturn), new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.bumblebee.robot.CANParserManager.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, final byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                Pdlog.m3273d(CANParserManager.this.TAG, "PowerOffEvent receive can " + UByteArray.m4583toStringimpl(bytes) + " getInterface()=" + ((HardwareInterface) hardware.getInterface()));
                robotListener.notify(new Function2<IRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.CANParserManager.3.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        it.onPowerOffEvent(PowerOffEvent.INSTANCE.m4316fromValue7apg3OU(UByteArray.m4577getimpl(bytes, 2)));
                    }
                });
                HardwareInterface hardwareInterface = (HardwareInterface) hardware.getInterface();
                if (hardwareInterface != null) {
                    byte[] copyOf = Arrays.copyOf(new byte[]{ClassDefinitionUtils.OPS_areturn, 2, 0, 0, 0, 0, 0}, 7);
                    Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                    hardwareInterface.sendCAN(copyOf);
                }
            }
        });
        this.canParsers.put(UByte.m4522boximpl(Constans.CAN_REV_SPRAY_SYS_RESULT), new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.bumblebee.robot.CANParserManager.4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                DisinfectionModuleManager.INSTANCE.m4321onDataReceiveGBYM_sE(bytes);
            }
        });
        this.canParsers.put(UByte.m4522boximpl(Constans.CAN_REV_DIS_MODE_SWITCH), new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.bumblebee.robot.CANParserManager.5
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                DisinfectionModuleManager.INSTANCE.m4321onDataReceiveGBYM_sE(bytes);
            }
        });
        this.canParsers.put(UByte.m4522boximpl(Constans.CAN_REV_UV_SYS_RESULT), new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.bumblebee.robot.CANParserManager.6
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                DisinfectionModuleManager.INSTANCE.m4321onDataReceiveGBYM_sE(bytes);
            }
        });
        this.canParsers.put(UByte.m4522boximpl(Constans.CAN_REV_UV_SET_RESULT), new Function2<Integer, UByteArray, Unit>() { // from class: com.pudutech.bumblebee.robot.CANParserManager.7
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
                invoke(num.intValue(), uByteArray.getStorage());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, byte[] bytes) {
                Intrinsics.checkParameterIsNotNull(bytes, "bytes");
                DisinfectionModuleManager.INSTANCE.m4321onDataReceiveGBYM_sE(bytes);
            }
        });
    }

    /* renamed from: regCanParser-eLRuwBU, reason: not valid java name */
    public final void m4298regCanParsereLRuwBU(byte protocol, Function2<? super Integer, ? super UByteArray, Unit> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        this.canParsers.put(UByte.m4522boximpl(protocol), parser);
    }

    /* renamed from: unRegCanParser-7apg3OU, reason: not valid java name */
    public final void m4299unRegCanParser7apg3OU(byte protocol) {
        this.canParsers.remove(UByte.m4522boximpl(protocol));
    }

    public final byte[] getFilterIDs() {
        return this.filterIDs;
    }

    /* renamed from: parse-eUNIVaU, reason: not valid java name */
    public final void m4297parseeUNIVaU(int id, byte[] byteArray) {
        Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
        if (UByteArray.m4578getSizeimpl(byteArray) != 8) {
            Pdlog.m3277w(this.TAG, "parse can data size error:" + UByteArray.m4578getSizeimpl(byteArray));
            return;
        }
        if (this.checkValidBlackList.contains(UByte.m4522boximpl(UByteArray.m4577getimpl(byteArray, 0))) || m4296checkValidGBYM_sE(byteArray)) {
            Pdlog.m3276v(this.TAG, "parse can:" + UByteArrayUtilsKt.m4334toHexStringGBYM_sE(byteArray));
            Function2<Integer, UByteArray, Unit> function2 = this.canParsers.get(UByte.m4522boximpl(UByteArray.m4577getimpl(byteArray, 0)));
            if (function2 != null) {
                function2.invoke(Integer.valueOf(id), UByteArray.m4570boximpl(byteArray));
            }
        }
    }

    /* renamed from: checkValid-GBYM_sE, reason: not valid java name */
    private final boolean m4296checkValidGBYM_sE(byte[] byteArray) {
        byte m4295canChecksumGBYM_sE = CANParserKt.m4295canChecksumGBYM_sE(byteArray);
        if (m4295canChecksumGBYM_sE == UByteArray.m4577getimpl(byteArray, 7)) {
            return true;
        }
        Pdlog.m3277w(this.TAG, "can invalid data0=" + UByte.m4563toStringimpl(UByteArray.m4577getimpl(byteArray, 0)) + " sum=" + UByte.m4563toStringimpl(m4295canChecksumGBYM_sE) + "!= data7=" + UByte.m4563toStringimpl(UByteArray.m4577getimpl(byteArray, 7)));
        return false;
    }
}
