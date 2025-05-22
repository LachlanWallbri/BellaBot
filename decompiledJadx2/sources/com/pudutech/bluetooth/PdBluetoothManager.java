package com.pudutech.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.log.ProxyLog;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.TimeoutKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: PdBluetoothManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0083@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020!H\u0003J\u0019\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0019\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u0004H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010$J\u000e\u0010'\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020!J\u0011\u0010(\u001a\u00020\u0012H\u0083@ø\u0001\u0000¢\u0006\u0002\u0010)J\u0019\u0010*\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u0004H\u0083@ø\u0001\u0000¢\u0006\u0002\u0010$J'\u0010+\u001a\u00020\u001c2\u0006\u0010\u000e\u001a\u00020\u000f2\u0017\u0010,\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u001c0-¢\u0006\u0002\b/J\b\u00100\u001a\u00020!H\u0003J\u0011\u00101\u001a\u00020\u001cH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010)J!\u00102\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u001d\u001a\u00020\u001eH\u0083@ø\u0001\u0000¢\u0006\u0002\u00103J\b\u00104\u001a\u00020\u001cH\u0007R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n \u0005*\u0004\u0018\u00010\u001a0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00065"}, m3961d2 = {"Lcom/pudutech/bluetooth/PdBluetoothManager;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "adapter", "Landroid/bluetooth/BluetoothAdapter;", "bondJob", "Lkotlinx/coroutines/Job;", "connectJob", "Lkotlinx/coroutines/Deferred;", "context", "Landroid/content/Context;", "disConnectJob", "mBluetoothA2dp", "Landroid/bluetooth/BluetoothA2dp;", "mBluetoothManager", "Landroid/bluetooth/BluetoothManager;", "receiver", "Lcom/pudutech/bluetooth/BluetoothStateReceiver;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "uuid", "Ljava/util/UUID;", "bondDevice", "", UtilityConfig.KEY_DEVICE_INFO, "Landroid/bluetooth/BluetoothDevice;", "(Landroid/bluetooth/BluetoothDevice;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "check", "", MqttServiceConstants.CONNECT_ACTION, "fromMac", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disConnect", "mac", "enable", "fetchBluetoothA2dp", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findDevice", "init", "config", "Lkotlin/Function1;", "Lcom/pudutech/bluetooth/PdBluetoothConfig;", "Lkotlin/ExtensionFunctionType;", "isSystemApp", "openBluetooth", "realConnect", "(ZLandroid/bluetooth/BluetoothDevice;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "release", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PdBluetoothManager {
    public static final PdBluetoothManager INSTANCE;
    private static final String TAG;
    private static BluetoothAdapter adapter;
    private static Job bondJob;
    private static Deferred<?> connectJob;
    private static Context context;
    private static Deferred<?> disConnectJob;
    private static BluetoothA2dp mBluetoothA2dp;
    private static BluetoothManager mBluetoothManager;
    private static BluetoothStateReceiver receiver;
    private static final CoroutineScope scope;
    private static final UUID uuid;

    static {
        PdBluetoothManager pdBluetoothManager = new PdBluetoothManager();
        INSTANCE = pdBluetoothManager;
        TAG = pdBluetoothManager.getClass().getSimpleName();
        uuid = UUID.fromString("0001101-0000-1000-8000-00805F9B34FB");
        scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
    }

    private PdBluetoothManager() {
    }

    public static final /* synthetic */ BluetoothAdapter access$getAdapter$p(PdBluetoothManager pdBluetoothManager) {
        return adapter;
    }

    public static final /* synthetic */ Context access$getContext$p(PdBluetoothManager pdBluetoothManager) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final String getTAG() {
        return TAG;
    }

    public final void init(Context context2, Function1<? super PdBluetoothConfig, Unit> config) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Context applicationContext = context2.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        context = applicationContext;
        Context context3 = context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        Object systemService = context3.getSystemService((Class<Object>) BluetoothManager.class);
        Intrinsics.checkExpressionValueIsNotNull(systemService, "this.context.getSystemSe…toothManager::class.java)");
        mBluetoothManager = (BluetoothManager) systemService;
        BluetoothManager bluetoothManager = mBluetoothManager;
        if (bluetoothManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBluetoothManager");
        }
        adapter = bluetoothManager.getAdapter();
        receiver = new BluetoothStateReceiver();
        Context context4 = context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        BluetoothStateReceiver bluetoothStateReceiver = receiver;
        BluetoothStateReceiver bluetoothStateReceiver2 = bluetoothStateReceiver;
        if (bluetoothStateReceiver == null) {
            Intrinsics.throwNpe();
        }
        context4.registerReceiver(bluetoothStateReceiver2, bluetoothStateReceiver.getFilter());
    }

    public final void release() {
        BluetoothAdapter bluetoothAdapter = adapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.disable();
        }
        adapter = (BluetoothAdapter) null;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        context2.unregisterReceiver(receiver);
        receiver = (BluetoothStateReceiver) null;
    }

    public final void enable(boolean enable) {
        ProxyLog proxyLog = ProxyLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        proxyLog.mo3285i(TAG2, "enable > ");
        if (enable) {
            BluetoothAdapter bluetoothAdapter = adapter;
            if (bluetoothAdapter != null) {
                bluetoothAdapter.enable();
                return;
            }
            return;
        }
        BluetoothAdapter bluetoothAdapter2 = adapter;
        if (bluetoothAdapter2 != null) {
            bluetoothAdapter2.disable();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object connect(String str, Continuation<? super Unit> continuation) {
        PdBluetoothManager$connect$1 pdBluetoothManager$connect$1;
        int i;
        Deferred<?> async$default;
        try {
            if (continuation instanceof PdBluetoothManager$connect$1) {
                pdBluetoothManager$connect$1 = (PdBluetoothManager$connect$1) continuation;
                if ((pdBluetoothManager$connect$1.label & Integer.MIN_VALUE) != 0) {
                    pdBluetoothManager$connect$1.label -= Integer.MIN_VALUE;
                    Object obj = pdBluetoothManager$connect$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = pdBluetoothManager$connect$1.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        ProxyLog proxyLog = ProxyLog.INSTANCE;
                        String TAG2 = TAG;
                        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                        proxyLog.mo3285i(TAG2, "connect > " + str);
                        if (!check()) {
                            return Unit.INSTANCE;
                        }
                        if (str != null) {
                            String upperCase = str.toUpperCase();
                            Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
                            Deferred<?> deferred = connectJob;
                            if (deferred != null) {
                                Job.DefaultImpls.cancel$default((Job) deferred, (CancellationException) null, 1, (Object) null);
                            }
                            Deferred<?> deferred2 = disConnectJob;
                            if (deferred2 != null) {
                                Job.DefaultImpls.cancel$default((Job) deferred2, (CancellationException) null, 1, (Object) null);
                            }
                            async$default = BuildersKt__Builders_commonKt.async$default(scope, null, null, new PdBluetoothManager$connect$2(str, upperCase, null), 3, null);
                            connectJob = async$default;
                            ProxyLog proxyLog2 = ProxyLog.INSTANCE;
                            String TAG3 = TAG;
                            Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                            proxyLog2.mo3285i(TAG3, "connect.new job > " + connectJob);
                            Deferred<?> deferred3 = connectJob;
                            if (deferred3 != null) {
                                pdBluetoothManager$connect$1.L$0 = this;
                                pdBluetoothManager$connect$1.L$1 = str;
                                pdBluetoothManager$connect$1.L$2 = upperCase;
                                pdBluetoothManager$connect$1.label = 1;
                                if (deferred3.await(pdBluetoothManager$connect$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
            if (i != 0) {
            }
            return Unit.INSTANCE;
        } catch (Exception e) {
            ProxyLog proxyLog3 = ProxyLog.INSTANCE;
            String TAG4 = TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
            StringBuilder sb = new StringBuilder();
            sb.append("connect.error > ");
            sb.append(connectJob);
            sb.append(" \n ");
            Exception exc = e;
            sb.append(Log.getStackTraceString(exc));
            sb.append("  ");
            proxyLog3.mo3285i(TAG4, sb.toString());
            e.printStackTrace();
            throw exc;
        }
        pdBluetoothManager$connect$1 = new PdBluetoothManager$connect$1(this, continuation);
        Object obj2 = pdBluetoothManager$connect$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pdBluetoothManager$connect$1.label;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x00f8, code lost:
    
        if (r4 == null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00bf, code lost:
    
        r2 = (android.bluetooth.BluetoothDevice) r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c1, code lost:
    
        if (r2 == null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c3, code lost:
    
        r13 = com.pudutech.bluetooth.PdBluetoothManager.connectJob;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c5, code lost:
    
        if (r13 == null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c7, code lost:
    
        kotlinx.coroutines.Job.DefaultImpls.cancel$default((kotlinx.coroutines.Job) r13, (java.util.concurrent.CancellationException) null, 1, (java.lang.Object) null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ca, code lost:
    
        r13 = com.pudutech.bluetooth.PdBluetoothManager.disConnectJob;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00cc, code lost:
    
        if (r13 == null) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ce, code lost:
    
        kotlinx.coroutines.Job.DefaultImpls.cancel$default((kotlinx.coroutines.Job) r13, (java.util.concurrent.CancellationException) null, 1, (java.lang.Object) null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00d1, code lost:
    
        r13 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(com.pudutech.bluetooth.PdBluetoothManager.scope, null, null, new com.pudutech.bluetooth.PdBluetoothManager$disConnect$3$1(r2, null), 3, null);
        com.pudutech.bluetooth.PdBluetoothManager.disConnectJob = r13;
        r13 = com.pudutech.bluetooth.PdBluetoothManager.disConnectJob;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e7, code lost:
    
        if (r13 == null) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00e9, code lost:
    
        r0.L$0 = r11;
        r0.L$1 = r12;
        r0.L$2 = r2;
        r0.label = 1;
        r13 = r13.await(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f5, code lost:
    
        if (r13 != r1) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00f7, code lost:
    
        return r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object disConnect(String str, Continuation<? super Unit> continuation) {
        PdBluetoothManager$disConnect$1 pdBluetoothManager$disConnect$1;
        int i;
        Set<BluetoothDevice> bondedDevices;
        Object obj;
        if (continuation instanceof PdBluetoothManager$disConnect$1) {
            pdBluetoothManager$disConnect$1 = (PdBluetoothManager$disConnect$1) continuation;
            if ((pdBluetoothManager$disConnect$1.label & Integer.MIN_VALUE) != 0) {
                pdBluetoothManager$disConnect$1.label -= Integer.MIN_VALUE;
                Object obj2 = pdBluetoothManager$disConnect$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pdBluetoothManager$disConnect$1.label;
                Object obj3 = null;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    ProxyLog proxyLog = ProxyLog.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                    proxyLog.mo3285i(TAG2, "disConnect > " + str);
                    if (!check()) {
                        return Unit.INSTANCE;
                    }
                    BluetoothAdapter bluetoothAdapter = adapter;
                    if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
                        return Unit.INSTANCE;
                    }
                    BluetoothAdapter bluetoothAdapter2 = adapter;
                    if (bluetoothAdapter2 != null && (bondedDevices = bluetoothAdapter2.getBondedDevices()) != null) {
                        Iterator<T> it = bondedDevices.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it.next();
                            BluetoothDevice it2 = (BluetoothDevice) obj;
                            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                            String address = it2.getAddress();
                            if (str == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String upperCase = str.toUpperCase();
                            Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
                            if (Boxing.boxBoolean(Intrinsics.areEqual(address, upperCase)).booleanValue()) {
                                break;
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj2);
                obj3 = obj2;
            }
        }
        pdBluetoothManager$disConnect$1 = new PdBluetoothManager$disConnect$1(this, continuation);
        Object obj22 = pdBluetoothManager$disConnect$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pdBluetoothManager$disConnect$1.label;
        Object obj32 = null;
        if (i != 0) {
        }
        obj32 = obj22;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object openBluetooth(Continuation<? super Unit> continuation) {
        PdBluetoothManager$openBluetooth$1 pdBluetoothManager$openBluetooth$1;
        Object obj;
        int i;
        if (continuation instanceof PdBluetoothManager$openBluetooth$1) {
            pdBluetoothManager$openBluetooth$1 = (PdBluetoothManager$openBluetooth$1) continuation;
            if ((pdBluetoothManager$openBluetooth$1.label & Integer.MIN_VALUE) != 0) {
                pdBluetoothManager$openBluetooth$1.label -= Integer.MIN_VALUE;
                obj = pdBluetoothManager$openBluetooth$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pdBluetoothManager$openBluetooth$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    ProxyLog proxyLog = ProxyLog.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                    proxyLog.mo3285i(TAG2, "openBluetooth > ");
                    BluetoothAdapter bluetoothAdapter = adapter;
                    if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                        return Unit.INSTANCE;
                    }
                    BluetoothAdapter bluetoothAdapter2 = adapter;
                    if (bluetoothAdapter2 == null || !ExtKt.enableBluetooth(bluetoothAdapter2, isSystemApp())) {
                        throw new IOException("蓝牙打开失败");
                    }
                    PdBluetoothManager$openBluetooth$2 pdBluetoothManager$openBluetooth$2 = new PdBluetoothManager$openBluetooth$2(null);
                    pdBluetoothManager$openBluetooth$1.L$0 = this;
                    pdBluetoothManager$openBluetooth$1.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(5000L, pdBluetoothManager$openBluetooth$2, pdBluetoothManager$openBluetooth$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                if (((BluetoothState) obj) != null) {
                    throw new IOException("蓝牙打开失败");
                }
                return Unit.INSTANCE;
            }
        }
        pdBluetoothManager$openBluetooth$1 = new PdBluetoothManager$openBluetooth$1(this, continuation);
        obj = pdBluetoothManager$openBluetooth$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pdBluetoothManager$openBluetooth$1.label;
        if (i != 0) {
        }
        if (((BluetoothState) obj) != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object bondDevice(BluetoothDevice bluetoothDevice, Continuation<? super Unit> continuation) {
        PdBluetoothManager$bondDevice$1 pdBluetoothManager$bondDevice$1;
        Object obj;
        int i;
        if (continuation instanceof PdBluetoothManager$bondDevice$1) {
            pdBluetoothManager$bondDevice$1 = (PdBluetoothManager$bondDevice$1) continuation;
            if ((pdBluetoothManager$bondDevice$1.label & Integer.MIN_VALUE) != 0) {
                pdBluetoothManager$bondDevice$1.label -= Integer.MIN_VALUE;
                obj = pdBluetoothManager$bondDevice$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pdBluetoothManager$bondDevice$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    ProxyLog proxyLog = ProxyLog.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                    proxyLog.mo3285i(TAG2, "bondDevice > ");
                    if (bluetoothDevice.getBondState() == 12) {
                        return Unit.INSTANCE;
                    }
                    if (!bluetoothDevice.createBond()) {
                        throw new IOException("设备绑定失败");
                    }
                    if (bluetoothDevice.getBondState() == 12) {
                        return Unit.INSTANCE;
                    }
                    PdBluetoothManager$bondDevice$2 pdBluetoothManager$bondDevice$2 = new PdBluetoothManager$bondDevice$2(bluetoothDevice, null);
                    pdBluetoothManager$bondDevice$1.L$0 = this;
                    pdBluetoothManager$bondDevice$1.L$1 = bluetoothDevice;
                    pdBluetoothManager$bondDevice$1.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(30000L, pdBluetoothManager$bondDevice$2, pdBluetoothManager$bondDevice$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                if (((BluetoothDevice) obj) != null) {
                    throw new IOException("设备绑定失败");
                }
                return Unit.INSTANCE;
            }
        }
        pdBluetoothManager$bondDevice$1 = new PdBluetoothManager$bondDevice$1(this, continuation);
        obj = pdBluetoothManager$bondDevice$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pdBluetoothManager$bondDevice$1.label;
        if (i != 0) {
        }
        if (((BluetoothDevice) obj) != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00ea A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object findDevice(String str, Continuation<? super BluetoothDevice> continuation) {
        PdBluetoothManager$findDevice$1 pdBluetoothManager$findDevice$1;
        int i;
        Set<BluetoothDevice> bondedDevices;
        Object obj;
        BluetoothDevice bluetoothDevice;
        BluetoothAdapter bluetoothAdapter;
        if (continuation instanceof PdBluetoothManager$findDevice$1) {
            pdBluetoothManager$findDevice$1 = (PdBluetoothManager$findDevice$1) continuation;
            if ((pdBluetoothManager$findDevice$1.label & Integer.MIN_VALUE) != 0) {
                pdBluetoothManager$findDevice$1.label -= Integer.MIN_VALUE;
                Object obj2 = pdBluetoothManager$findDevice$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pdBluetoothManager$findDevice$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    ProxyLog proxyLog = ProxyLog.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                    proxyLog.mo3285i(TAG2, "findDevice > ");
                    BluetoothAdapter bluetoothAdapter2 = adapter;
                    if (bluetoothAdapter2 != null && (bondedDevices = bluetoothAdapter2.getBondedDevices()) != null) {
                        Iterator<T> it = bondedDevices.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it.next();
                            BluetoothDevice it2 = (BluetoothDevice) obj;
                            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                            if (Boxing.boxBoolean(Intrinsics.areEqual(it2.getAddress(), str)).booleanValue()) {
                                break;
                            }
                        }
                        BluetoothDevice bluetoothDevice2 = (BluetoothDevice) obj;
                        if (bluetoothDevice2 != null) {
                            ProxyLog proxyLog2 = ProxyLog.INSTANCE;
                            String TAG3 = TAG;
                            Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                            proxyLog2.mo3285i(TAG3, "findDevice resume bondedDevices> ");
                            return bluetoothDevice2;
                        }
                    }
                    BluetoothAdapter bluetoothAdapter3 = adapter;
                    if (bluetoothAdapter3 != null) {
                        Boxing.boxBoolean(bluetoothAdapter3.cancelDiscovery());
                    }
                    BluetoothAdapter bluetoothAdapter4 = adapter;
                    if (bluetoothAdapter4 != null) {
                        Boxing.boxBoolean(bluetoothAdapter4.startDiscovery());
                    }
                    PdBluetoothManager$findDevice$device$1 pdBluetoothManager$findDevice$device$1 = new PdBluetoothManager$findDevice$device$1(str, null);
                    pdBluetoothManager$findDevice$1.L$0 = this;
                    pdBluetoothManager$findDevice$1.L$1 = str;
                    pdBluetoothManager$findDevice$1.label = 1;
                    obj2 = TimeoutKt.withTimeoutOrNull(15000L, pdBluetoothManager$findDevice$device$1, pdBluetoothManager$findDevice$1);
                    if (obj2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj2);
                }
                bluetoothDevice = (BluetoothDevice) obj2;
                ProxyLog proxyLog3 = ProxyLog.INSTANCE;
                String TAG4 = TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
                proxyLog3.mo3285i(TAG4, "findDevice.collect > " + bluetoothDevice);
                bluetoothAdapter = adapter;
                if (bluetoothAdapter != null) {
                    Boxing.boxBoolean(bluetoothAdapter.cancelDiscovery());
                }
                if (bluetoothDevice == null) {
                    return bluetoothDevice;
                }
                throw new DeviceNotFondException("找不到设备:\n1.设备是否开机、蓝牙是否打开\n2.蓝牙设备是否已经和其他设备连接");
            }
        }
        pdBluetoothManager$findDevice$1 = new PdBluetoothManager$findDevice$1(this, continuation);
        Object obj22 = pdBluetoothManager$findDevice$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pdBluetoothManager$findDevice$1.label;
        if (i != 0) {
        }
        bluetoothDevice = (BluetoothDevice) obj22;
        ProxyLog proxyLog32 = ProxyLog.INSTANCE;
        String TAG42 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG42, "TAG");
        proxyLog32.mo3285i(TAG42, "findDevice.collect > " + bluetoothDevice);
        bluetoothAdapter = adapter;
        if (bluetoothAdapter != null) {
        }
        if (bluetoothDevice == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object realConnect(boolean z, BluetoothDevice bluetoothDevice, Continuation<? super Unit> continuation) {
        PdBluetoothManager$realConnect$1 pdBluetoothManager$realConnect$1;
        int i;
        PdBluetoothManager pdBluetoothManager;
        boolean z2;
        BluetoothDevice bluetoothDevice2;
        BluetoothA2dp bluetoothA2dp;
        Object obj;
        BluetoothA2dp bluetoothA2dp2;
        boolean z3;
        DeviceWithConnectState deviceWithConnectState;
        if (continuation instanceof PdBluetoothManager$realConnect$1) {
            pdBluetoothManager$realConnect$1 = (PdBluetoothManager$realConnect$1) continuation;
            if ((pdBluetoothManager$realConnect$1.label & Integer.MIN_VALUE) != 0) {
                pdBluetoothManager$realConnect$1.label -= Integer.MIN_VALUE;
                Object obj2 = pdBluetoothManager$realConnect$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pdBluetoothManager$realConnect$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    PdBluetoothManager$realConnect$bluetoothA2dp$1 pdBluetoothManager$realConnect$bluetoothA2dp$1 = new PdBluetoothManager$realConnect$bluetoothA2dp$1(null);
                    pdBluetoothManager$realConnect$1.L$0 = this;
                    pdBluetoothManager$realConnect$1.Z$0 = z;
                    pdBluetoothManager$realConnect$1.L$1 = bluetoothDevice;
                    pdBluetoothManager$realConnect$1.label = 1;
                    obj2 = TimeoutKt.withTimeoutOrNull(5000L, pdBluetoothManager$realConnect$bluetoothA2dp$1, pdBluetoothManager$realConnect$1);
                    if (obj2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pdBluetoothManager = this;
                    z2 = z;
                    bluetoothDevice2 = bluetoothDevice;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        bluetoothA2dp2 = (BluetoothA2dp) pdBluetoothManager$realConnect$1.L$2;
                        bluetoothDevice2 = (BluetoothDevice) pdBluetoothManager$realConnect$1.L$1;
                        z3 = pdBluetoothManager$realConnect$1.Z$0;
                        ResultKt.throwOnFailure(obj2);
                        deviceWithConnectState = (DeviceWithConnectState) obj2;
                        if (deviceWithConnectState == null) {
                            ProxyLog proxyLog = ProxyLog.INSTANCE;
                            String TAG2 = TAG;
                            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                            proxyLog.mo3285i(TAG2, "deviceConnectStateChangeFlow.collect > " + deviceWithConnectState);
                            if (deviceWithConnectState != null) {
                                if (Intrinsics.areEqual(deviceWithConnectState.getDevice(), bluetoothDevice2)) {
                                    ProxyLog proxyLog2 = ProxyLog.INSTANCE;
                                    String TAG3 = TAG;
                                    Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                                    proxyLog2.mo3285i(TAG3, "deviceConnectStateChangeFlow.collect >  scc.realConnect success");
                                } else if (z3) {
                                    ExtKt.connectDevice(bluetoothA2dp2, bluetoothDevice2);
                                } else {
                                    ExtKt.disconnectDevice(bluetoothA2dp2, bluetoothDevice2);
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IOException("连接设备失败：1.设备是否开机、蓝牙是否打开\n2.蓝牙设备是否已经和其他设备连接");
                    }
                    bluetoothDevice2 = (BluetoothDevice) pdBluetoothManager$realConnect$1.L$1;
                    z2 = pdBluetoothManager$realConnect$1.Z$0;
                    pdBluetoothManager = (PdBluetoothManager) pdBluetoothManager$realConnect$1.L$0;
                    ResultKt.throwOnFailure(obj2);
                }
                bluetoothA2dp = (BluetoothA2dp) obj2;
                if (bluetoothA2dp != null) {
                    throw new IllegalArgumentException("获取蓝牙代理工具类失败");
                }
                List<BluetoothDevice> connectedDevices = bluetoothA2dp.getConnectedDevices();
                Intrinsics.checkExpressionValueIsNotNull(connectedDevices, "bluetoothA2dp.connectedDevices");
                Iterator<T> it = connectedDevices.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    BluetoothDevice it2 = (BluetoothDevice) obj;
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    if (Boxing.boxBoolean(Intrinsics.areEqual(it2.getAddress(), bluetoothDevice2.getAddress())).booleanValue()) {
                        break;
                    }
                }
                boolean z4 = obj != null;
                if (z4 == z2) {
                    ProxyLog proxyLog3 = ProxyLog.INSTANCE;
                    String TAG4 = TAG;
                    Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
                    proxyLog3.mo3285i(TAG4, "onServiceConnected > targetConnect:" + z4 + " crtConnect:" + z2);
                    return Unit.INSTANCE;
                }
                if (z2) {
                    ExtKt.connectDevice(bluetoothA2dp, bluetoothDevice2);
                } else {
                    ExtKt.disconnectDevice(bluetoothA2dp, bluetoothDevice2);
                }
                ProxyLog proxyLog4 = ProxyLog.INSTANCE;
                String TAG5 = TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG5, "TAG");
                proxyLog4.mo3285i(TAG5, "onServiceConnected > device:" + bluetoothDevice2);
                PdBluetoothManager$realConnect$2 pdBluetoothManager$realConnect$2 = new PdBluetoothManager$realConnect$2(bluetoothDevice2, z2, null);
                pdBluetoothManager$realConnect$1.L$0 = pdBluetoothManager;
                pdBluetoothManager$realConnect$1.Z$0 = z2;
                pdBluetoothManager$realConnect$1.L$1 = bluetoothDevice2;
                pdBluetoothManager$realConnect$1.L$2 = bluetoothA2dp;
                pdBluetoothManager$realConnect$1.label = 2;
                Object withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(5000L, pdBluetoothManager$realConnect$2, pdBluetoothManager$realConnect$1);
                if (withTimeoutOrNull == coroutine_suspended) {
                    return coroutine_suspended;
                }
                bluetoothA2dp2 = bluetoothA2dp;
                obj2 = withTimeoutOrNull;
                z3 = z2;
                deviceWithConnectState = (DeviceWithConnectState) obj2;
                if (deviceWithConnectState == null) {
                }
            }
        }
        pdBluetoothManager$realConnect$1 = new PdBluetoothManager$realConnect$1(this, continuation);
        Object obj22 = pdBluetoothManager$realConnect$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pdBluetoothManager$realConnect$1.label;
        if (i != 0) {
        }
        bluetoothA2dp = (BluetoothA2dp) obj22;
        if (bluetoothA2dp != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object fetchBluetoothA2dp(Continuation<? super BluetoothA2dp> continuation) {
        BluetoothA2dp bluetoothA2dp = mBluetoothA2dp;
        if (bluetoothA2dp != null) {
            if (bluetoothA2dp == null) {
                Intrinsics.throwNpe();
            }
            return bluetoothA2dp;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        BluetoothProfile.ServiceListener serviceListener = new BluetoothProfile.ServiceListener() { // from class: com.pudutech.bluetooth.PdBluetoothManager$fetchBluetoothA2dp$2$profileListener$1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int profile, BluetoothProfile proxy) {
                BluetoothA2dp bluetoothA2dp2;
                Intrinsics.checkParameterIsNotNull(proxy, "proxy");
                ProxyLog proxyLog = ProxyLog.INSTANCE;
                String TAG2 = PdBluetoothManager.INSTANCE.getTAG();
                Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                proxyLog.mo3285i(TAG2, "onServiceConnected > profile:" + profile + " proxy:" + proxy);
                PdBluetoothManager pdBluetoothManager = PdBluetoothManager.INSTANCE;
                PdBluetoothManager.mBluetoothA2dp = (BluetoothA2dp) proxy;
                try {
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    PdBluetoothManager pdBluetoothManager2 = PdBluetoothManager.INSTANCE;
                    bluetoothA2dp2 = PdBluetoothManager.mBluetoothA2dp;
                    if (bluetoothA2dp2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m4510constructorimpl(bluetoothA2dp2));
                } catch (Exception unused) {
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int profile) {
                ProxyLog proxyLog = ProxyLog.INSTANCE;
                String TAG2 = PdBluetoothManager.INSTANCE.getTAG();
                Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                proxyLog.mo3285i(TAG2, "onServiceDisconnected > profile:" + profile + ' ');
                PdBluetoothManager pdBluetoothManager = PdBluetoothManager.INSTANCE;
                PdBluetoothManager.mBluetoothA2dp = (BluetoothA2dp) null;
            }
        };
        BluetoothAdapter access$getAdapter$p = access$getAdapter$p(INSTANCE);
        if (access$getAdapter$p != null) {
            Boxing.boxBoolean(access$getAdapter$p.getProfileProxy(access$getContext$p(INSTANCE), serviceListener, 2));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    private final boolean isSystemApp() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        PackageManager packageManager = context2.getPackageManager();
        if (packageManager != null) {
            try {
                Context context3 = context;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context3.getPackageName(), 0);
                if (applicationInfo != null) {
                    if ((applicationInfo.flags & 1) > 0) {
                        return true;
                    }
                }
                return false;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private final boolean check() {
        if (!(context != null)) {
            PdBluetoothManagerKt.throwOrLog("没有调用init方法");
            return false;
        }
        if (adapter == null) {
            PdBluetoothManagerKt.throwOrLog("该设备不支持蓝牙");
            return false;
        }
        if (receiver != null) {
            return true;
        }
        PdBluetoothManagerKt.throwOrLog("未注册广播");
        return false;
    }
}
