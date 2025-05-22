package com.pudutech.mirsdk.charge;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.ChargingPileInfo;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.bluetooth.BluetoothBleListener;
import com.pudutech.mirsdk.bluetooth.BluetoothBleScanStateListener;
import com.pudutech.mirsdk.charge.ChargeFinder;
import com.pudutech.mirsdk.charge.ChargeMessageParser;
import com.pudutech.mirsdk.map.Atlas;
import com.pudutech.mirsdk.map.elements.ChargingPile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* compiled from: ChargeFinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000w\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0012*\u0003\u001d #\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001EB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u00103\u001a\u000204J\u0006\u00105\u001a\u000204J\u0010\u00106\u001a\u0002042\u0006\u00107\u001a\u00020\u0004H\u0002J\u0010\u00108\u001a\u0002042\u0006\u00107\u001a\u00020\u0004H\u0002J\u0010\u00109\u001a\u0002042\u0006\u00107\u001a\u00020\u0004H\u0002J\u000e\u0010:\u001a\u0002042\u0006\u0010;\u001a\u00020\tJ\u0010\u0010<\u001a\u0002042\u0006\u00107\u001a\u00020\u0004H\u0002J\b\u0010=\u001a\u000204H\u0002J\u0006\u0010>\u001a\u000204J\u000e\u0010?\u001a\u0002042\u0006\u0010%\u001a\u00020&J\u000e\u0010@\u001a\u0002042\u0006\u0010,\u001a\u00020-J\u0006\u0010A\u001a\u000204J\u0010\u0010B\u001a\u0002042\u0006\u00107\u001a\u00020\u0004H\u0002J\b\u0010C\u001a\u000204H\u0002J\b\u0010D\u001a\u000204H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R\u0010\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0004\n\u0002\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010.\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102¨\u0006F"}, m3961d2 = {"Lcom/pudutech/mirsdk/charge/ChargeFinder;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "callback", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleListener;", "checkData", "", "checkPileJob", "Lkotlinx/coroutines/Job;", "checkPileList", "", "Lcom/pudutech/mirsdk/map/elements/ChargingPile;", "checkPileSucJob", "connectJob", "filterList", "<set-?>", "Lcom/pudutech/mirsdk/charge/ChargeFinder$FoundChargeStep;", "foundStep", "getFoundStep", "()Lcom/pudutech/mirsdk/charge/ChargeFinder$FoundChargeStep;", "isRecCheckMessage", "", "isRecCheckSucMessage", "isRecStatusReply", "isRequestBluetooth", "mScanCallback", "com/pudutech/mirsdk/charge/ChargeFinder$mScanCallback$1", "Lcom/pudutech/mirsdk/charge/ChargeFinder$mScanCallback$1;", "mScanStateListener", "com/pudutech/mirsdk/charge/ChargeFinder$mScanStateListener$1", "Lcom/pudutech/mirsdk/charge/ChargeFinder$mScanStateListener$1;", "messageParserListener", "com/pudutech/mirsdk/charge/ChargeFinder$messageParserListener$1", "Lcom/pudutech/mirsdk/charge/ChargeFinder$messageParserListener$1;", "moveAction", "Lcom/pudutech/mirsdk/MoveAction;", "nearDevice", "parser", "Lcom/pudutech/mirsdk/charge/ChargeMessageParser;", "reConnectCount", "", "sdkInterface", "Lcom/pudutech/mirsdk/aidl/SDKInterface;", "stateReqJob", "getStateReqJob", "()Lkotlinx/coroutines/Job;", "setStateReqJob", "(Lkotlinx/coroutines/Job;)V", "cancelConnectDevice", "", "disconnectChargePile", "doBluetoothChargeState", "mac", "doCheckPileTask", "doStartChargeWhenNoTask", "foundAndConnectChargePile", "data", "initCallBack", "reloadLocalizationIfNeed", "reset", "setMoveAction", "setSdkInterface", "setToChargingState", "startConnectChargePile", "startConnectChargePileWhenNoTask", "startScanNearDevice", "FoundChargeStep", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeFinder {
    private static BluetoothBleListener callback;
    private static byte[] checkData;
    private static Job checkPileJob;
    private static Job checkPileSucJob;
    private static Job connectJob;
    private static volatile boolean isRecCheckMessage;
    private static volatile boolean isRecCheckSucMessage;
    private static volatile boolean isRecStatusReply;
    private static volatile boolean isRequestBluetooth;
    private static MoveAction moveAction;
    private static ChargingPile nearDevice;
    private static ChargeMessageParser parser;
    private static int reConnectCount;
    private static SDKInterface sdkInterface;
    private static Job stateReqJob;
    public static final ChargeFinder INSTANCE = new ChargeFinder();
    private static final String TAG = ChargeFinder.class.getSimpleName();
    private static FoundChargeStep foundStep = FoundChargeStep.Idle;
    private static Map<String, ChargingPile> checkPileList = new LinkedHashMap();
    private static Map<String, ChargingPile> filterList = new LinkedHashMap();
    private static final ChargeFinder$mScanCallback$1 mScanCallback = new ScanCallback() { // from class: com.pudutech.mirsdk.charge.ChargeFinder$mScanCallback$1
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int callbackType, ScanResult result) {
            Map map;
            Map map2;
            Map map3;
            if (ChargeFinder.INSTANCE.getFoundStep() != ChargeFinder.FoundChargeStep.FoundNearDevice || result == null) {
                return;
            }
            ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
            map = ChargeFinder.checkPileList;
            BluetoothDevice device = result.getDevice();
            Intrinsics.checkExpressionValueIsNotNull(device, "device");
            if (map.containsKey(device.getAddress())) {
                ChargeFinder chargeFinder2 = ChargeFinder.INSTANCE;
                map2 = ChargeFinder.filterList;
                BluetoothDevice device2 = result.getDevice();
                Intrinsics.checkExpressionValueIsNotNull(device2, "device");
                if (map2.containsKey(device2.getAddress())) {
                    return;
                }
                ChargeFinder chargeFinder3 = ChargeFinder.INSTANCE;
                ChargeFinder chargeFinder4 = ChargeFinder.INSTANCE;
                map3 = ChargeFinder.checkPileList;
                BluetoothDevice device3 = result.getDevice();
                Intrinsics.checkExpressionValueIsNotNull(device3, "device");
                ChargeFinder.nearDevice = (ChargingPile) map3.get(device3.getAddress());
                ChargeFinder.INSTANCE.startConnectChargePileWhenNoTask();
                BluetoothBleHelper.scanBleDevice$default(BluetoothBleHelper.INSTANCE, false, 0L, null, 6, null);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int errorCode) {
            String str;
            ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
            str = ChargeFinder.TAG;
            Pdlog.m3273d(str, "onScanFailed");
        }
    };
    private static final ChargeFinder$mScanStateListener$1 mScanStateListener = new BluetoothBleScanStateListener() { // from class: com.pudutech.mirsdk.charge.ChargeFinder$mScanStateListener$1
        @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleScanStateListener
        public void onScanState(boolean isScanning) {
            ChargingPile chargingPile;
            String str;
            MoveAction moveAction2;
            if (isScanning) {
                return;
            }
            ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
            chargingPile = ChargeFinder.nearDevice;
            if (chargingPile != null || ChargeFinder.INSTANCE.getFoundStep() == ChargeFinder.FoundChargeStep.Idle) {
                return;
            }
            ChargeFinder chargeFinder2 = ChargeFinder.INSTANCE;
            str = ChargeFinder.TAG;
            Pdlog.m3273d(str, "No found near charge pile");
            ChargeFinder chargeFinder3 = ChargeFinder.INSTANCE;
            ChargeFinder.foundStep = ChargeFinder.FoundChargeStep.Idle;
            ChargeFinder chargeFinder4 = ChargeFinder.INSTANCE;
            moveAction2 = ChargeFinder.moveAction;
            if (moveAction2 != null) {
                moveAction2.notifyBTChargePileArrivedState(false);
            }
            ChargeFinder.INSTANCE.disconnectChargePile();
        }
    };
    private static final ChargeFinder$messageParserListener$1 messageParserListener = new ChargeMessageParser.OnChargeMessageParserListener() { // from class: com.pudutech.mirsdk.charge.ChargeFinder$messageParserListener$1
        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeStartReply(boolean isSuccess) {
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeTimeout() {
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onCmdFail() {
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onRebootReply() {
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onVersion(String version) {
            Intrinsics.checkParameterIsNotNull(version, "version");
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeState(int errorCode, int busyState, int inVol, int outVol, int current, int temp) {
            ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
            ChargeFinder.isRecStatusReply = true;
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeStopReply(boolean isSuccess) {
            MoveAction moveAction2;
            ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
            moveAction2 = ChargeFinder.moveAction;
            if (moveAction2 != null) {
                moveAction2.notifyBTChargePileArrivedState(false);
            }
            ChargeFinder.INSTANCE.disconnectChargePile();
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeArrived() {
            String str;
            ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
            str = ChargeFinder.TAG;
            Pdlog.m3273d(str, "onChargeArrived");
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onChargeLeave() {
            String str;
            MoveAction moveAction2;
            ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
            str = ChargeFinder.TAG;
            Pdlog.m3273d(str, "onChargeLeave");
            ChargeFinder chargeFinder2 = ChargeFinder.INSTANCE;
            ChargeFinder.foundStep = ChargeFinder.FoundChargeStep.Idle;
            ChargeFinder chargeFinder3 = ChargeFinder.INSTANCE;
            moveAction2 = ChargeFinder.moveAction;
            if (moveAction2 != null) {
                moveAction2.notifyBTChargePileArrivedState(false);
            }
            ChargeFinder.INSTANCE.disconnectChargePile();
        }

        @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
        public void onCheckPileSuccess() {
            BluetoothBleListener bluetoothBleListener;
            MoveAction moveAction2;
            String mac;
            if (ChargeFinder.INSTANCE.getFoundStep() == ChargeFinder.FoundChargeStep.StartCharge) {
                ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
                ChargeFinder.isRecCheckSucMessage = true;
                BuildersKt__BuildersKt.runBlocking$default(null, new ChargeFinder$messageParserListener$1$onCheckPileSuccess$1(null), 1, null);
                ChargeFinder chargeFinder2 = ChargeFinder.INSTANCE;
                ChargeFinder.foundStep = ChargeFinder.FoundChargeStep.Charging;
                ChargeFinder chargeFinder3 = ChargeFinder.INSTANCE;
                bluetoothBleListener = ChargeFinder.callback;
                if (bluetoothBleListener != null && (mac = bluetoothBleListener.getMac()) != null) {
                    ChargeFinder.INSTANCE.doBluetoothChargeState(mac);
                }
                ChargeFinder chargeFinder4 = ChargeFinder.INSTANCE;
                moveAction2 = ChargeFinder.moveAction;
                if (moveAction2 != null) {
                    moveAction2.notifyBTChargePileArrivedState(true);
                }
                ChargeFinder.INSTANCE.reloadLocalizationIfNeed();
            }
        }
    };

    /* compiled from: ChargeFinder.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdk/charge/ChargeFinder$FoundChargeStep;", "", "(Ljava/lang/String;I)V", "Idle", "FoundNearDevice", "ConnectDevice", "CheckDevice", "StartCharge", "Charging", "DoChargingTask", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum FoundChargeStep {
        Idle,
        FoundNearDevice,
        ConnectDevice,
        CheckDevice,
        StartCharge,
        Charging,
        DoChargingTask
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FoundChargeStep.values().length];

        static {
            $EnumSwitchMapping$0[FoundChargeStep.Idle.ordinal()] = 1;
            $EnumSwitchMapping$0[FoundChargeStep.CheckDevice.ordinal()] = 2;
        }
    }

    private ChargeFinder() {
    }

    public final FoundChargeStep getFoundStep() {
        return foundStep;
    }

    public final void setMoveAction(MoveAction moveAction2) {
        Intrinsics.checkParameterIsNotNull(moveAction2, "moveAction");
        moveAction = moveAction2;
    }

    public final void setSdkInterface(SDKInterface sdkInterface2) {
        Intrinsics.checkParameterIsNotNull(sdkInterface2, "sdkInterface");
        sdkInterface = sdkInterface2;
    }

    public final void foundAndConnectChargePile(byte[] data) {
        List<ChargingPile> chargingPilesList;
        String mac;
        Intrinsics.checkParameterIsNotNull(data, "data");
        Pdlog.m3273d(TAG, "foundAndConnectChargePile cur foundStep " + foundStep);
        if (foundStep == FoundChargeStep.Charging || foundStep == FoundChargeStep.DoChargingTask) {
            Pdlog.m3273d(TAG, "current status is " + foundStep + " not need found again");
            cancelConnectDevice();
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[foundStep.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            isRecCheckMessage = true;
            BuildersKt__BuildersKt.runBlocking$default(null, new ChargeFinder$foundAndConnectChargePile$2(null), 1, null);
            BluetoothBleListener bluetoothBleListener = callback;
            if (bluetoothBleListener == null || (mac = bluetoothBleListener.getMac()) == null) {
                return;
            }
            INSTANCE.doStartChargeWhenNoTask(mac);
            return;
        }
        nearDevice = (ChargingPile) null;
        checkPileList.clear();
        filterList.clear();
        checkData = data;
        isRecCheckSucMessage = false;
        isRecCheckMessage = false;
        MoveAction moveAction2 = moveAction;
        if (moveAction2 != null) {
            moveAction2.notifyBTChargePileArrivedState(true);
        }
        MoveAction moveAction3 = moveAction;
        if (moveAction3 != null && (chargingPilesList = moveAction3.getChargingPilesList()) != null) {
            for (ChargingPile chargingPile : chargingPilesList) {
                if (chargingPile.getMac().length() > 0) {
                    List split$default = StringsKt.split$default((CharSequence) chargingPile.getMac(), new String[]{":"}, false, 0, 6, (Object) null);
                    byte parseInt = (byte) (((byte) Integer.parseInt((String) split$default.get(CollectionsKt.getLastIndex(split$default)), 16)) & ((byte) 3));
                    Pdlog.m3273d(TAG, "foundAndConnectChargePile macByte:" + ((int) parseInt) + " checkByte:" + ((int) data[2]));
                    if (parseInt == data[2]) {
                        Pdlog.m3273d(TAG, "foundAndConnectChargePile add check device:" + chargingPile.getId() + " group:" + chargingPile.getGroup());
                        checkPileList.put(chargingPile.getMac(), chargingPile);
                    }
                }
            }
        }
        if (checkPileList.isEmpty()) {
            Pdlog.m3273d(TAG, "check pile list is empty");
        } else {
            startScanNearDevice();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startScanNearDevice() {
        Pdlog.m3273d(TAG, "startScanNearDevice");
        foundStep = FoundChargeStep.FoundNearDevice;
        BluetoothBleHelper.INSTANCE.addBleScanCallback(mScanCallback);
        BluetoothBleHelper.INSTANCE.addBluetoothScanStateListener(mScanStateListener);
        ArrayList arrayList = new ArrayList();
        ScanFilter build = new ScanFilter.Builder().setDeviceName("PDCharger").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "ScanFilter.Builder().set…Name(\"PDCharger\").build()");
        arrayList.add(build);
        ScanFilter build2 = new ScanFilter.Builder().setDeviceName("PdInstall").build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "ScanFilter.Builder().set…Name(\"PdInstall\").build()");
        arrayList.add(build2);
        BluetoothBleHelper.INSTANCE.scanBleDevice(true, 5000L, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startConnectChargePileWhenNoTask() {
        String mac;
        Pdlog.m3273d(TAG, "startConnectChargePileWhenNoTask");
        ChargingPile chargingPile = nearDevice;
        if (chargingPile != null) {
            foundStep = FoundChargeStep.ConnectDevice;
            BluetoothBleHelper.INSTANCE.removeBleScanCallBack(mScanCallback);
            BluetoothBleHelper.INSTANCE.removeBluetoothScanStateListener(mScanStateListener);
            BluetoothBleListener bluetoothBleListener = callback;
            if (bluetoothBleListener != null && (mac = bluetoothBleListener.getMac()) != null && (!Intrinsics.areEqual(mac, chargingPile.getMac()))) {
                if (mac.length() > 0) {
                    Pdlog.m3273d(TAG, "disconnectChargePile " + mac);
                    INSTANCE.disconnectChargePile();
                }
            }
            reConnectCount = 0;
            INSTANCE.startConnectChargePile(chargingPile.getMac());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doCheckPileTask(String mac) {
        Job launch$default;
        Pdlog.m3273d(TAG, "doCheckPileTask " + mac);
        BuildersKt__BuildersKt.runBlocking$default(null, new ChargeFinder$doCheckPileTask$1(null), 1, null);
        foundStep = FoundChargeStep.CheckDevice;
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChargeFinder$doCheckPileTask$2(mac, null), 3, null);
        checkPileJob = launch$default;
    }

    private final void doStartChargeWhenNoTask(String mac) {
        Job launch$default;
        Pdlog.m3273d(TAG, "doStartChargeWhenNoTask " + mac);
        BuildersKt__BuildersKt.runBlocking$default(null, new ChargeFinder$doStartChargeWhenNoTask$1(null), 1, null);
        foundStep = FoundChargeStep.StartCharge;
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChargeFinder$doStartChargeWhenNoTask$2(mac, null), 3, null);
        checkPileSucJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadLocalizationIfNeed() {
        List<ChargingPileInfo> chargingPileInfos;
        String str;
        Object obj;
        Atlas atlas;
        MoveAction moveAction2 = moveAction;
        if (moveAction2 == null || (chargingPileInfos = moveAction2.getChargingPileInfos()) == null) {
            return;
        }
        Iterator<T> it = chargingPileInfos.iterator();
        while (true) {
            str = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            String mac = ((ChargingPileInfo) obj).getMac();
            BluetoothBleListener bluetoothBleListener = callback;
            if (Intrinsics.areEqual(mac, bluetoothBleListener != null ? bluetoothBleListener.getMac() : null)) {
                break;
            }
        }
        ChargingPileInfo chargingPileInfo = (ChargingPileInfo) obj;
        if (chargingPileInfo != null) {
            SDKInterface sDKInterface = sdkInterface;
            if (sDKInterface != null && sDKInterface.isRelocalizationSuccess()) {
                MoveAction moveAction3 = moveAction;
                if (moveAction3 != null && (atlas = moveAction3.getAtlas()) != null) {
                    str = atlas.getDefaultFloor();
                }
                if (!(!Intrinsics.areEqual(str, chargingPileInfo.getFloor()))) {
                    return;
                }
            }
            MoveAction moveAction4 = moveAction;
            if (moveAction4 != null) {
                moveAction4.reloadLocalizationByChargingPile(chargingPileInfo);
            }
        }
    }

    public final Job getStateReqJob() {
        return stateReqJob;
    }

    public final void setStateReqJob(Job job) {
        stateReqJob = job;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doBluetoothChargeState(String mac) {
        Job launch$default;
        Pdlog.m3273d(TAG, "doBluetoothChargeState " + mac);
        BuildersKt__BuildersKt.runBlocking$default(null, new ChargeFinder$doBluetoothChargeState$1(null), 1, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChargeFinder$doBluetoothChargeState$2(mac, null), 3, null);
        stateReqJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initCallBack(final String mac) {
        if (!Intrinsics.areEqual(callback != null ? r0.getMac() : null, mac)) {
            Pdlog.m3273d(TAG, "initCallBack mac:" + mac);
            ChargeMessageParser chargeMessageParser = parser;
            if (chargeMessageParser != null) {
                chargeMessageParser.setMessageParserListener((ChargeMessageParser.OnChargeMessageParserListener) null);
            }
            ChargeMessageParser chargeMessageParser2 = new ChargeMessageParser(mac);
            chargeMessageParser2.setMessageParserListener(messageParserListener);
            parser = chargeMessageParser2;
            callback = new BluetoothBleListener(mac) { // from class: com.pudutech.mirsdk.charge.ChargeFinder$initCallBack$2
                @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
                public void onDataSendState(byte[] data, boolean isSuccess) {
                    Intrinsics.checkParameterIsNotNull(data, "data");
                }

                @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
                public void onConnectStateChange(int state) {
                    String str;
                    boolean z;
                    if (state == 0) {
                        ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
                        str = ChargeFinder.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("onConnectStateChange DISCONNECTED isRequestBluetooth:");
                        ChargeFinder chargeFinder2 = ChargeFinder.INSTANCE;
                        z = ChargeFinder.isRequestBluetooth;
                        sb.append(z);
                        Pdlog.m3273d(str, sb.toString());
                        if (ChargeFinder.INSTANCE.getFoundStep() == ChargeFinder.FoundChargeStep.ConnectDevice) {
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChargeFinder$initCallBack$2$onConnectStateChange$1(null), 3, null);
                        }
                    }
                }

                @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
                public void onServicesDiscovered(int status) {
                    if (status == 0 && ChargeFinder.INSTANCE.getFoundStep() == ChargeFinder.FoundChargeStep.ConnectDevice) {
                        ChargeFinder.INSTANCE.doCheckPileTask(getMac());
                    }
                }

                @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
                public void onDataRead(byte[] data) {
                    ChargeMessageParser chargeMessageParser3;
                    ChargeMessageParser chargeMessageParser4;
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    String mac2 = getMac();
                    ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
                    chargeMessageParser3 = ChargeFinder.parser;
                    if (Intrinsics.areEqual(mac2, chargeMessageParser3 != null ? chargeMessageParser3.getMac() : null)) {
                        ChargeFinder chargeFinder2 = ChargeFinder.INSTANCE;
                        chargeMessageParser4 = ChargeFinder.parser;
                        if (chargeMessageParser4 != null) {
                            chargeMessageParser4.parseMessage(data);
                        }
                    }
                }
            };
        }
        BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
        BluetoothBleListener bluetoothBleListener = callback;
        if (bluetoothBleListener == null) {
            Intrinsics.throwNpe();
        }
        bluetoothBleHelper.addBluetoothBleListeners(bluetoothBleListener);
    }

    private final void startConnectChargePile(String mac) {
        Job launch$default;
        if (isRequestBluetooth) {
            Pdlog.m3273d(TAG, "startConnectChargePile is in request");
            return;
        }
        isRequestBluetooth = true;
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChargeFinder$startConnectChargePile$1(mac, null), 3, null);
        connectJob = launch$default;
    }

    public final void disconnectChargePile() {
        Pdlog.m3273d(TAG, "disconnectChargePile");
        isRecStatusReply = false;
        isRequestBluetooth = false;
        BluetoothBleHelper.INSTANCE.removeBleScanCallBack(mScanCallback);
        BluetoothBleHelper.INSTANCE.removeBluetoothScanStateListener(mScanStateListener);
        cancelConnectDevice();
        BluetoothBleListener bluetoothBleListener = callback;
        if (bluetoothBleListener != null) {
            BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(bluetoothBleListener);
            if (!ChargeUpdateTaskManager.INSTANCE.isUpdating(bluetoothBleListener.getMac())) {
                BluetoothBleHelper.INSTANCE.disconnectDevice(bluetoothBleListener.getMac());
            }
        }
        callback = (BluetoothBleListener) null;
    }

    public final void cancelConnectDevice() {
        BuildersKt__BuildersKt.runBlocking$default(null, new ChargeFinder$cancelConnectDevice$1(null), 1, null);
    }

    public final void reset() {
        Pdlog.m3273d(TAG, "reset idle");
        foundStep = FoundChargeStep.Idle;
        disconnectChargePile();
    }

    public final void setToChargingState() {
        Pdlog.m3273d(TAG, "set To Charging State");
        foundStep = FoundChargeStep.Charging;
        cancelConnectDevice();
    }
}
