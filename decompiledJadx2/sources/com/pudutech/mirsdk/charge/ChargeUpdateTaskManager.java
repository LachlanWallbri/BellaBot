package com.pudutech.mirsdk.charge;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.BluetoothChargeInterface;
import com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener;
import com.pudutech.mirsdk.aidl.serialize.BluetoothChargeUpdateErrorType;
import com.pudutech.mirsdk.charge.ChargeUpdateTask;
import com.pudutech.mirsdk.config.SDKConfig;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: ChargeUpdateTaskManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000;\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r*\u0001\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007H\u0016J\u0006\u0010\u0014\u001a\u00020\u0004J\u0006\u0010\u0015\u001a\u00020\u0011J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0004H\u0016J\u0012\u0010\u0018\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0004H\u0002J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\tH\u0016J\u0010\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0004H\u0016J\u000e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/mirsdk/charge/ChargeUpdateTaskManager;", "Lcom/pudutech/mirsdk/aidl/BluetoothChargeInterface$Stub;", "()V", "TAG", "", "chargeUpdateListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/BluetoothChargeUpdateListener;", "isAutoUpdate", "", "updateChargeListener", "com/pudutech/mirsdk/charge/ChargeUpdateTaskManager$updateChargeListener$1", "Lcom/pudutech/mirsdk/charge/ChargeUpdateTaskManager$updateChargeListener$1;", "updateTaskHashMap", "", "Lcom/pudutech/mirsdk/charge/ChargeUpdateTask;", "addBluetoothChargeUpdateListener", "", "name", "listener", "getTargetVersion", "init", "isUpdating", "mac", "removeBluetoothChargeUpdateListener", "removeTask", "setAutoUpdate", "p0", "startUpdateTask", "stopUpdateTask", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeUpdateTaskManager extends BluetoothChargeInterface.Stub {
    public static final ChargeUpdateTaskManager INSTANCE = new ChargeUpdateTaskManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final ThreadSafeListener<BluetoothChargeUpdateListener> chargeUpdateListeners = new ThreadSafeListener<>();
    private static final Map<String, ChargeUpdateTask> updateTaskHashMap = new LinkedHashMap();
    private static boolean isAutoUpdate = true;
    private static final ChargeUpdateTaskManager$updateChargeListener$1 updateChargeListener = new ChargeUpdateTask.onChargePileUpdateListener() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1
        @Override // com.pudutech.mirsdk.charge.ChargeUpdateTask.onChargePileUpdateListener
        public void onConnectingDevice(final String mac) {
            ThreadSafeListener threadSafeListener;
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            ChargeUpdateTaskManager chargeUpdateTaskManager = ChargeUpdateTaskManager.INSTANCE;
            threadSafeListener = ChargeUpdateTaskManager.chargeUpdateListeners;
            threadSafeListener.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1$onConnectingDevice$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                    invoke2(bluetoothChargeUpdateListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onConnectingDevice(mac);
                }
            });
        }

        @Override // com.pudutech.mirsdk.charge.ChargeUpdateTask.onChargePileUpdateListener
        public void onCheckingVersionBefore(final String mac) {
            ThreadSafeListener threadSafeListener;
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            ChargeUpdateTaskManager chargeUpdateTaskManager = ChargeUpdateTaskManager.INSTANCE;
            threadSafeListener = ChargeUpdateTaskManager.chargeUpdateListeners;
            threadSafeListener.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1$onCheckingVersionBefore$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                    invoke2(bluetoothChargeUpdateListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onCheckingVersionBefore(mac);
                }
            });
        }

        @Override // com.pudutech.mirsdk.charge.ChargeUpdateTask.onChargePileUpdateListener
        public void onRequestToIAPMode(final String mac) {
            ThreadSafeListener threadSafeListener;
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            ChargeUpdateTaskManager chargeUpdateTaskManager = ChargeUpdateTaskManager.INSTANCE;
            threadSafeListener = ChargeUpdateTaskManager.chargeUpdateListeners;
            threadSafeListener.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1$onRequestToIAPMode$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                    invoke2(bluetoothChargeUpdateListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onRequestToIAPMode(mac);
                }
            });
        }

        @Override // com.pudutech.mirsdk.charge.ChargeUpdateTask.onChargePileUpdateListener
        public void onSendingPack(final String mac, final int total, final int progress) {
            ThreadSafeListener threadSafeListener;
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            ChargeUpdateTaskManager chargeUpdateTaskManager = ChargeUpdateTaskManager.INSTANCE;
            threadSafeListener = ChargeUpdateTaskManager.chargeUpdateListeners;
            threadSafeListener.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1$onSendingPack$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                    invoke2(bluetoothChargeUpdateListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onSendData(mac, total, progress);
                }
            });
        }

        @Override // com.pudutech.mirsdk.charge.ChargeUpdateTask.onChargePileUpdateListener
        public void onRequestToAPPMode(final String mac) {
            ThreadSafeListener threadSafeListener;
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            ChargeUpdateTaskManager chargeUpdateTaskManager = ChargeUpdateTaskManager.INSTANCE;
            threadSafeListener = ChargeUpdateTaskManager.chargeUpdateListeners;
            threadSafeListener.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1$onRequestToAPPMode$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                    invoke2(bluetoothChargeUpdateListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onRequestToAPPMode(mac);
                }
            });
        }

        @Override // com.pudutech.mirsdk.charge.ChargeUpdateTask.onChargePileUpdateListener
        public void onUpdateSuccess(final String mac, final String targetVersion) {
            ThreadSafeListener threadSafeListener;
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            Intrinsics.checkParameterIsNotNull(targetVersion, "targetVersion");
            ChargeUpdateTaskManager.INSTANCE.removeTask(mac);
            ChargeUpdateTaskManager chargeUpdateTaskManager = ChargeUpdateTaskManager.INSTANCE;
            threadSafeListener = ChargeUpdateTaskManager.chargeUpdateListeners;
            threadSafeListener.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1$onUpdateSuccess$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                    invoke2(bluetoothChargeUpdateListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onUpdateSuccess(mac, targetVersion);
                }
            });
        }

        @Override // com.pudutech.mirsdk.charge.ChargeUpdateTask.onChargePileUpdateListener
        public void onError(final String mac, int error, final String describe) {
            ThreadSafeListener threadSafeListener;
            ThreadSafeListener threadSafeListener2;
            ThreadSafeListener threadSafeListener3;
            ThreadSafeListener threadSafeListener4;
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            Intrinsics.checkParameterIsNotNull(describe, "describe");
            if (error == 1) {
                ChargeUpdateTaskManager.INSTANCE.removeTask(mac);
                ChargeUpdateTaskManager chargeUpdateTaskManager = ChargeUpdateTaskManager.INSTANCE;
                threadSafeListener = ChargeUpdateTaskManager.chargeUpdateListeners;
                threadSafeListener.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1$onError$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                        invoke2(bluetoothChargeUpdateListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onUpdateError(mac, BluetoothChargeUpdateErrorType.ConnectFail, describe);
                    }
                });
                return;
            }
            if (error == 2) {
                ChargeUpdateTaskManager.INSTANCE.removeTask(mac);
                ChargeUpdateTaskManager chargeUpdateTaskManager2 = ChargeUpdateTaskManager.INSTANCE;
                threadSafeListener2 = ChargeUpdateTaskManager.chargeUpdateListeners;
                threadSafeListener2.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1$onError$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                        invoke2(bluetoothChargeUpdateListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onUpdateError(mac, BluetoothChargeUpdateErrorType.SendDataFail, describe);
                    }
                });
                return;
            }
            if (error == 3) {
                ChargeUpdateTaskManager.INSTANCE.removeTask(mac);
                ChargeUpdateTaskManager chargeUpdateTaskManager3 = ChargeUpdateTaskManager.INSTANCE;
                threadSafeListener3 = ChargeUpdateTaskManager.chargeUpdateListeners;
                threadSafeListener3.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1$onError$4
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                        invoke2(bluetoothChargeUpdateListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onUpdateError(mac, BluetoothChargeUpdateErrorType.VersionCheckFail, describe);
                    }
                });
                return;
            }
            if (error != 5) {
                return;
            }
            ChargeUpdateTaskManager.INSTANCE.removeTask(mac);
            ChargeUpdateTaskManager chargeUpdateTaskManager4 = ChargeUpdateTaskManager.INSTANCE;
            threadSafeListener4 = ChargeUpdateTaskManager.chargeUpdateListeners;
            threadSafeListener4.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$updateChargeListener$1$onError$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                    invoke2(bluetoothChargeUpdateListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onUpdateError(mac, BluetoothChargeUpdateErrorType.NoReply, describe);
                }
            });
        }
    };

    private ChargeUpdateTaskManager() {
    }

    public final void init() {
        isAutoUpdate = SDKConfig.INSTANCE.getPreferences().getBoolean("auto_update_charge", true);
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeInterface
    public void startUpdateTask(final String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        if (mac.length() == 0) {
            Pdlog.m3273d(TAG, "startUpdateTask mac is empty");
            return;
        }
        if (!updateTaskHashMap.containsKey(mac)) {
            updateTaskHashMap.put(mac, new ChargeUpdateTask(mac));
        }
        ChargeUpdateTask chargeUpdateTask = updateTaskHashMap.get(mac);
        if (chargeUpdateTask != null) {
            chargeUpdateTask.setChargePileUpdateListener(updateChargeListener);
        }
        final String targetVersion = getTargetVersion();
        chargeUpdateListeners.notify(new Function2<BluetoothChargeUpdateListener, String, Unit>() { // from class: com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$startUpdateTask$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(BluetoothChargeUpdateListener bluetoothChargeUpdateListener, String str) {
                invoke2(bluetoothChargeUpdateListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BluetoothChargeUpdateListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.onStartUpdate(mac, targetVersion);
            }
        });
        ChargeUpdateTask chargeUpdateTask2 = updateTaskHashMap.get(mac);
        if (chargeUpdateTask2 != null) {
            chargeUpdateTask2.startUpdateMcu();
        }
    }

    public final void stopUpdateTask(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        if (updateTaskHashMap.containsKey(mac)) {
            removeTask(mac);
            ChargeUpdateTask chargeUpdateTask = updateTaskHashMap.get(mac);
            if (chargeUpdateTask != null) {
                chargeUpdateTask.stopTask();
            }
        }
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeInterface
    public boolean isUpdating(String mac) {
        ChargeUpdateTask chargeUpdateTask;
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        return updateTaskHashMap.containsKey(mac) && ((chargeUpdateTask = updateTaskHashMap.get(mac)) == null || chargeUpdateTask.getCurUpdateStep() != -1);
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeInterface
    public void setAutoUpdate(boolean p0) {
        isAutoUpdate = p0;
        SDKConfig.INSTANCE.getPreferences().edit().putBoolean("auto_update_charge", p0).apply();
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeInterface
    public boolean isAutoUpdate() {
        return isAutoUpdate;
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeInterface
    public void addBluetoothChargeUpdateListener(String name, BluetoothChargeUpdateListener listener) {
        String str = name;
        if ((str == null || str.length() == 0) || listener == null) {
            return;
        }
        Pdlog.m3273d(TAG, "addBluetoothChargeUpdateListener " + name);
        chargeUpdateListeners.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeInterface
    public void removeBluetoothChargeUpdateListener(String name) {
        Pdlog.m3273d(TAG, "removeBluetoothChargeUpdateListener " + name);
        if (name != null) {
            chargeUpdateListeners.remove(name);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeTask(String mac) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChargeUpdateTaskManager$removeTask$1(mac, null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b8 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getTargetVersion() {
        String[] list;
        File file = new File(ChargeUpdateTask.TEST_PATH);
        if (file.exists() && file.isDirectory()) {
            String[] list2 = file.list();
            Intrinsics.checkExpressionValueIsNotNull(list2, "testFile.list()");
            if (!(list2.length == 0)) {
                list = file.list();
                if (list != null) {
                    return "";
                }
                for (String fileName : list) {
                    Intrinsics.checkExpressionValueIsNotNull(fileName, "fileName");
                    if (StringsKt.startsWith$default(fileName, "PDCharge-", false, 2, (Object) null)) {
                        List split$default = StringsKt.split$default((CharSequence) fileName, new String[]{".", "-"}, false, 0, 6, (Object) null);
                        if (split$default.size() == 3) {
                            List split$default2 = StringsKt.split$default((CharSequence) split$default.get(1), new String[]{"_"}, false, 0, 6, (Object) null);
                            if (split$default2.size() == 3) {
                                return ((String) split$default2.get(0)) + FilenameUtils.EXTENSION_SEPARATOR + ((String) split$default2.get(1)) + FilenameUtils.EXTENSION_SEPARATOR + ((String) split$default2.get(2));
                            }
                        } else {
                            continue;
                        }
                    }
                }
                return "";
            }
        }
        list = SDKConfig.INSTANCE.getProcessContext().getAssets().list("bluetooth_charge");
        if (list != null) {
        }
    }
}
