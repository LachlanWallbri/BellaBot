package com.pudutech.bumblebee.business.robotsdk;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.business.ims.IMSKit;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletInstallTask;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTask;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.DeviceStatusChangedListener;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.MarkScanListener;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.MsgReceivedListener;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.PalletStateListener;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.TouchListener;
import com.pudutech.bumblebee.business.utils.ShutDownHelper;
import com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener;
import com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener;
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.FaultLevel;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.bumblebee.robot.aidl.serialize.PowerOffEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: RobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000y\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t*\u0003\u000b\u001a<\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010I\u001a\u00020/2\u0006\u0010J\u001a\u00020\u001eJ\b\u0010K\u001a\u00020/H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u0016\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0013\u001a\u0004\b!\u0010\u0017R!\u0010#\u001a\b\u0012\u0004\u0012\u00020 0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u0013\u001a\u0004\b$\u0010\u0011R!\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u0013\u001a\u0004\b(\u0010\u0011R!\u0010*\u001a\b\u0012\u0004\u0012\u00020'0\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u0013\u001a\u0004\b+\u0010\u0017R\"\u0010-\u001a\n\u0012\u0004\u0012\u00020/\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R!\u00104\u001a\b\u0012\u0004\u0012\u0002050\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b7\u0010\u0013\u001a\u0004\b6\u0010\u0011R!\u00108\u001a\b\u0012\u0004\u0012\u0002050\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b:\u0010\u0013\u001a\u0004\b9\u0010\u0017R\u0010\u0010;\u001a\u00020<X\u0082\u0004¢\u0006\u0004\n\u0002\u0010=R\u001a\u0010>\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u0006\"\u0004\b@\u0010AR!\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\u0013\u001a\u0004\bD\u0010\u0011R!\u0010F\u001a\b\u0012\u0004\u0012\u00020C0\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bH\u0010\u0013\u001a\u0004\bG\u0010\u0017¨\u0006L"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/RobotPeripherals;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "connection", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "deliveryListener", "com/pudutech/bumblebee/business/robotsdk/RobotPeripherals$deliveryListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/RobotPeripherals$deliveryListener$1;", "deviceStatusChangedListenerList", "Lcom/pudutech/bumblebee/business/base/ListenerList;", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/DeviceStatusChangedListener;", "getDeviceStatusChangedListenerList", "()Lcom/pudutech/bumblebee/business/base/ListenerList;", "deviceStatusChangedListenerList$delegate", "Lkotlin/Lazy;", "deviceStatusChangedListeners", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "getDeviceStatusChangedListeners", "()Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "deviceStatusChangedListeners$delegate", "listener", "com/pudutech/bumblebee/business/robotsdk/RobotPeripherals$listener$1", "Lcom/pudutech/bumblebee/business/robotsdk/RobotPeripherals$listener$1;", "mAppContext", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "markScanListener", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/MarkScanListener;", "getMarkScanListener", "markScanListener$delegate", "markScanListenerList", "getMarkScanListenerList", "markScanListenerList$delegate", "msgReceivedListenerList", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/MsgReceivedListener;", "getMsgReceivedListenerList", "msgReceivedListenerList$delegate", "msgReceivedListeners", "getMsgReceivedListeners", "msgReceivedListeners$delegate", "onLEDInitDone", "Lkotlin/Function0;", "", "getOnLEDInitDone", "()Lkotlin/jvm/functions/Function0;", "setOnLEDInitDone", "(Lkotlin/jvm/functions/Function0;)V", "palletListenerList", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/PalletStateListener;", "getPalletListenerList", "palletListenerList$delegate", "palletStateListeners", "getPalletStateListeners", "palletStateListeners$delegate", "recycleRobotListener", "com/pudutech/bumblebee/business/robotsdk/RobotPeripherals$recycleRobotListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/RobotPeripherals$recycleRobotListener$1;", "servicePath", "getServicePath", "setServicePath", "(Ljava/lang/String;)V", "touchListenerList", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/TouchListener;", "getTouchListenerList", "touchListenerList$delegate", "touchListeners", "getTouchListeners", "touchListeners$delegate", MqttServiceConstants.CONNECT_ACTION, "appContext", "init", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotPeripherals {
    private static AIDLConnection<RobotInterface> connection;
    private static WeakReference<Context> mAppContext;
    private static Function0<Unit> onLEDInitDone;
    public static final RobotPeripherals INSTANCE = new RobotPeripherals();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String servicePath = "com.pudutech.bumblebee.robot.RobotService";

    /* renamed from: deviceStatusChangedListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy deviceStatusChangedListenerList = LazyKt.lazy(new Function0<ListenerList<DeviceStatusChangedListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$deviceStatusChangedListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<DeviceStatusChangedListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: deviceStatusChangedListeners$delegate, reason: from kotlin metadata */
    private static final Lazy deviceStatusChangedListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<DeviceStatusChangedListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$deviceStatusChangedListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<DeviceStatusChangedListener> invoke() {
            ListenerList deviceStatusChangedListenerList2;
            deviceStatusChangedListenerList2 = RobotPeripherals.INSTANCE.getDeviceStatusChangedListenerList();
            return new BaseMultiListenerImpl<>(deviceStatusChangedListenerList2);
        }
    });

    /* renamed from: msgReceivedListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy msgReceivedListenerList = LazyKt.lazy(new Function0<ListenerList<MsgReceivedListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$msgReceivedListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<MsgReceivedListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: msgReceivedListeners$delegate, reason: from kotlin metadata */
    private static final Lazy msgReceivedListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<MsgReceivedListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$msgReceivedListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<MsgReceivedListener> invoke() {
            ListenerList msgReceivedListenerList2;
            msgReceivedListenerList2 = RobotPeripherals.INSTANCE.getMsgReceivedListenerList();
            return new BaseMultiListenerImpl<>(msgReceivedListenerList2);
        }
    });

    /* renamed from: palletListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy palletListenerList = LazyKt.lazy(new Function0<ListenerList<PalletStateListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$palletListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<PalletStateListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: palletStateListeners$delegate, reason: from kotlin metadata */
    private static final Lazy palletStateListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<PalletStateListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$palletStateListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<PalletStateListener> invoke() {
            ListenerList palletListenerList2;
            palletListenerList2 = RobotPeripherals.INSTANCE.getPalletListenerList();
            return new BaseMultiListenerImpl<>(palletListenerList2);
        }
    });

    /* renamed from: touchListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy touchListenerList = LazyKt.lazy(new Function0<ListenerList<TouchListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$touchListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<TouchListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: touchListeners$delegate, reason: from kotlin metadata */
    private static final Lazy touchListeners = LazyKt.lazy(new Function0<BaseMultiListenerImpl<TouchListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$touchListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<TouchListener> invoke() {
            ListenerList touchListenerList2;
            touchListenerList2 = RobotPeripherals.INSTANCE.getTouchListenerList();
            return new BaseMultiListenerImpl<>(touchListenerList2);
        }
    });

    /* renamed from: markScanListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy markScanListenerList = LazyKt.lazy(new Function0<ListenerList<MarkScanListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$markScanListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<MarkScanListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: markScanListener$delegate, reason: from kotlin metadata */
    private static final Lazy markScanListener = LazyKt.lazy(new Function0<BaseMultiListenerImpl<MarkScanListener>>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$markScanListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<MarkScanListener> invoke() {
            ListenerList markScanListenerList2;
            markScanListenerList2 = RobotPeripherals.INSTANCE.getMarkScanListenerList();
            return new BaseMultiListenerImpl<>(markScanListenerList2);
        }
    });
    private static final RobotPeripherals$listener$1 listener = new IRobotListener.Stub() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$listener$1
        @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
        public void onFault(FaultLevel p0, String p1) {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
        public void onPowerOffEvent(PowerOffEvent p0) {
            WeakReference weakReference;
            Context it;
            Pdlog.m3274e(RobotPeripherals.INSTANCE.getTAG(), "onPowerOffEvent event=" + p0);
            RobotPeripherals robotPeripherals = RobotPeripherals.INSTANCE;
            weakReference = RobotPeripherals.mAppContext;
            if (weakReference != null && (it = (Context) weakReference.get()) != null) {
                ShutDownHelper shutDownHelper = ShutDownHelper.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (shutDownHelper.doCaseByBroadcast(it)) {
                    return;
                }
                ShutDownHelper.INSTANCE.doCaseBySimulateInputEvent(it);
                return;
            }
            ShutDownHelper.INSTANCE.doCaseByReflect();
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
        public void onDeviceStatusChange(final PeripheralDevice device, final PeripheralDeviceStatus status, final String description) {
            ListenerList deviceStatusChangedListenerList2;
            WeakReference weakReference;
            WeakReference weakReference2;
            Context it;
            if (device == PeripheralDevice.LORA) {
                if (status == PeripheralDeviceStatus.NORMAL) {
                    String tag = RobotPeripherals.INSTANCE.getTAG();
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("init imskit, context = ");
                    RobotPeripherals robotPeripherals = RobotPeripherals.INSTANCE;
                    weakReference = RobotPeripherals.mAppContext;
                    sb.append(weakReference != null ? (Context) weakReference.get() : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(tag, objArr);
                    RobotPeripherals robotPeripherals2 = RobotPeripherals.INSTANCE;
                    weakReference2 = RobotPeripherals.mAppContext;
                    if (weakReference2 != null && (it = (Context) weakReference2.get()) != null) {
                        Pdlog.m3273d(RobotPeripherals.INSTANCE.getTAG(), "init imskit begin");
                        IMSKit companion = IMSKit.INSTANCE.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        companion.init(it);
                        Pdlog.m3273d(RobotPeripherals.INSTANCE.getTAG(), "init imskit end");
                    }
                } else if (status != PeripheralDeviceStatus.DEVICE_DISCONNECT) {
                    PeripheralDeviceStatus peripheralDeviceStatus = PeripheralDeviceStatus.FAULT;
                }
                deviceStatusChangedListenerList2 = RobotPeripherals.INSTANCE.getDeviceStatusChangedListenerList();
                deviceStatusChangedListenerList2.forEach(new Function1<DeviceStatusChangedListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$listener$1$onDeviceStatusChange$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DeviceStatusChangedListener deviceStatusChangedListener) {
                        invoke2(deviceStatusChangedListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DeviceStatusChangedListener it2) {
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        it2.onDeviceStatusChange(PeripheralDevice.this, status, description);
                    }
                });
            }
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
        public void onTouch(final TouchPlace p0, final TouchState p1) {
            ListenerList touchListenerList2;
            if (p0 == null || p1 == null) {
                return;
            }
            touchListenerList2 = RobotPeripherals.INSTANCE.getTouchListenerList();
            touchListenerList2.forEach(new Function1<TouchListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$listener$1$onTouch$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TouchListener touchListener) {
                    invoke2(touchListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TouchListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onTouch(TouchPlace.this, p1);
                }
            });
        }
    };
    private static final RobotPeripherals$deliveryListener$1 deliveryListener = new IDeliveryRobotListener.Stub() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$deliveryListener$1
        @Override // com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener
        public void onQRScanMsg(final String p0) {
            ListenerList markScanListenerList2;
            boolean z = true;
            Pdlog.m3273d(RobotPeripherals.INSTANCE.getTAG(), "onQRScanMsg " + p0);
            String str = p0;
            if (str != null && !StringsKt.isBlank(str)) {
                z = false;
            }
            if (z) {
                return;
            }
            markScanListenerList2 = RobotPeripherals.INSTANCE.getMarkScanListenerList();
            markScanListenerList2.forEach(new Function1<MarkScanListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$deliveryListener$1$onQRScanMsg$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MarkScanListener markScanListener2) {
                    invoke2(markScanListener2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MarkScanListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onMarkScan(p0);
                }
            });
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener
        public void onPalletState(final List<Pallet> p0) {
            ListenerList palletListenerList2;
            palletListenerList2 = RobotPeripherals.INSTANCE.getPalletListenerList();
            palletListenerList2.forEach(new Function1<PalletStateListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$deliveryListener$1$onPalletState$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PalletStateListener palletStateListener) {
                    invoke2(palletStateListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PalletStateListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onPalletsResponse(p0);
                }
            });
        }
    };
    private static final RobotPeripherals$recycleRobotListener$1 recycleRobotListener = new IRecycleRobotListener.Stub() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$recycleRobotListener$1
        public void onLoRaChannelSwitchStatus(int p0, int p1) {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
        public void onNFCSignDetected(String p0) {
            Intrinsics.checkParameterIsNotNull(p0, "p0");
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
        public void onRemoteDeviceResponseChecking(int p0) {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
        public void onRemoteDeviceMsg(final byte[] p0) {
            ListenerList msgReceivedListenerList2;
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            String tag = RobotPeripherals.INSTANCE.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("onRemoteDeviceMsg: ");
            String arrays = Arrays.toString(p0);
            Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
            sb.append(arrays);
            Pdlog.m3273d(tag, sb.toString());
            msgReceivedListenerList2 = RobotPeripherals.INSTANCE.getMsgReceivedListenerList();
            msgReceivedListenerList2.forEach(new Function1<MsgReceivedListener, Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$recycleRobotListener$1$onRemoteDeviceMsg$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MsgReceivedListener msgReceivedListener) {
                    invoke2(msgReceivedListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MsgReceivedListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onMsgReceived(p0);
                }
            });
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<DeviceStatusChangedListener> getDeviceStatusChangedListenerList() {
        return (ListenerList) deviceStatusChangedListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<MarkScanListener> getMarkScanListenerList() {
        return (ListenerList) markScanListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<MsgReceivedListener> getMsgReceivedListenerList() {
        return (ListenerList) msgReceivedListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<PalletStateListener> getPalletListenerList() {
        return (ListenerList) palletListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<TouchListener> getTouchListenerList() {
        return (ListenerList) touchListenerList.getValue();
    }

    public final BaseMultiListenerImpl<DeviceStatusChangedListener> getDeviceStatusChangedListeners() {
        return (BaseMultiListenerImpl) deviceStatusChangedListeners.getValue();
    }

    public final BaseMultiListenerImpl<MarkScanListener> getMarkScanListener() {
        return (BaseMultiListenerImpl) markScanListener.getValue();
    }

    public final BaseMultiListenerImpl<MsgReceivedListener> getMsgReceivedListeners() {
        return (BaseMultiListenerImpl) msgReceivedListeners.getValue();
    }

    public final BaseMultiListenerImpl<PalletStateListener> getPalletStateListeners() {
        return (BaseMultiListenerImpl) palletStateListeners.getValue();
    }

    public final BaseMultiListenerImpl<TouchListener> getTouchListeners() {
        return (BaseMultiListenerImpl) touchListeners.getValue();
    }

    private RobotPeripherals() {
    }

    public static final /* synthetic */ AIDLConnection access$getConnection$p(RobotPeripherals robotPeripherals) {
        AIDLConnection<RobotInterface> aIDLConnection = connection;
        if (aIDLConnection == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connection");
        }
        return aIDLConnection;
    }

    public final String getTAG() {
        return TAG;
    }

    public final String getServicePath() {
        return servicePath;
    }

    public final void setServicePath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        servicePath = str;
    }

    public final void connect(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        mAppContext = new WeakReference<>(appContext);
        final String str = servicePath;
        final RobotPeripherals$connect$2 robotPeripherals$connect$2 = RobotPeripherals$connect$2.INSTANCE;
        connection = new AIDLConnection<RobotInterface>(str, robotPeripherals$connect$2) { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$connect$1
            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                super.onServiceConnected(name, service);
                Pdlog.m3275i("RobotDevicesConnection", "onConnected name=" + name + "  " + service);
                RobotPeripherals.INSTANCE.init();
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                super.onBindingDied(name);
                Pdlog.m3277w(RobotPeripherals.INSTANCE.getTAG(), "onBindingDied name=" + name);
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName name) {
                super.onNullBinding(name);
                Pdlog.m3277w(RobotPeripherals.INSTANCE.getTAG(), "onNullBinding name=" + name);
            }

            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                super.onServiceDisconnected(name);
                Pdlog.m3277w(RobotPeripherals.INSTANCE.getTAG(), "onServiceDisconnected name=" + name);
            }
        };
        ExecutorCoroutineDispatcher newSingleThreadContext = ThreadPoolDispatcherKt.newSingleThreadContext("RobotPeripheral");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, newSingleThreadContext, null, new RobotPeripherals$connect$3(appContext, newSingleThreadContext, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void init() {
        Context it;
        AIDLConnection<RobotInterface> aIDLConnection = connection;
        if (aIDLConnection == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connection");
        }
        RobotInterface robotInterface = aIDLConnection.getInterface();
        if (robotInterface != null) {
            robotInterface.open();
        }
        AIDLConnection<RobotInterface> aIDLConnection2 = connection;
        if (aIDLConnection2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connection");
        }
        RobotInterface robotInterface2 = aIDLConnection2.getInterface();
        if (robotInterface2 != null) {
            robotInterface2.addListener("peripherals", listener);
        }
        AIDLConnection<RobotInterface> aIDLConnection3 = connection;
        if (aIDLConnection3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connection");
        }
        RobotInterface robotInterface3 = aIDLConnection3.getInterface();
        if (robotInterface3 != null) {
            robotInterface3.addDeliveryRobotListener("peripherals", deliveryListener);
        }
        AIDLConnection<RobotInterface> aIDLConnection4 = connection;
        if (aIDLConnection4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connection");
        }
        final RobotInterface robotInterface4 = aIDLConnection4.getInterface();
        if (robotInterface4 != null) {
            Pdlog.m3275i(TAG, "init. robotInterface=" + robotInterface4);
            Peripherals.INSTANCE.getPallet().setController$module_bumblebee_business_robotRelease(robotInterface4);
            Peripherals.INSTANCE.getLedScreen().setController$module_bumblebee_business_robotRelease(robotInterface4);
            Peripherals.INSTANCE.getLora().setRobot$module_bumblebee_business_robotRelease(robotInterface4);
            Peripherals.INSTANCE.getIms().init(robotInterface4, INSTANCE);
            robotInterface4.addRecycleRobotListener("recycleRobotListener", recycleRobotListener);
        } else {
            robotInterface4 = null;
        }
        BaseMultiListenerImpl<TouchListener> touchListeners2 = getTouchListeners();
        touchListeners2.addListener(Peripherals.INSTANCE.getTouchSensor());
        touchListeners2.addListener(Peripherals.INSTANCE.getFunctionButton());
        if (robotInterface4 != null) {
            Peripherals.INSTANCE.getLedControllers().init(robotInterface4, Peripherals.INSTANCE.getThread());
            Function0<Unit> function0 = onLEDInitDone;
            if (function0 != null) {
                function0.invoke();
            }
        }
        if (robotInterface4 == null) {
            Pdlog.m3274e(TAG, "robot interface is null");
            Unit unit = Unit.INSTANCE;
        }
        BaseMultiListenerImpl<PalletStateListener> palletStateListeners2 = getPalletStateListeners();
        palletStateListeners2.addListener(Peripherals.INSTANCE.getPallet());
        palletStateListeners2.addListener(Peripherals.INSTANCE.getPalletInstallTask());
        getMarkScanListener().addListener(Peripherals.INSTANCE.getQrScanTask());
        WeakReference<Context> weakReference = mAppContext;
        if (weakReference == null || (it = weakReference.get()) == null) {
            return;
        }
        PalletTask.Companion companion = PalletTask.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        companion.initConfig(it);
        PalletInstallTask.INSTANCE.initConfig(it, new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.robotsdk.RobotPeripherals$init$$inlined$let$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                RobotInterface robotInterface5 = RobotInterface.this;
                if (robotInterface5 != null) {
                    robotInterface5.requestPallets();
                }
            }
        });
    }

    public final Function0<Unit> getOnLEDInitDone() {
        return onLEDInitDone;
    }

    public final void setOnLEDInitDone(Function0<Unit> function0) {
        onLEDInitDone = function0;
    }
}
