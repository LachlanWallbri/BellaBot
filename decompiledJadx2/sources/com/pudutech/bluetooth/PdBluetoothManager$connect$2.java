package com.pudutech.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.log.ProxyLog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PdBluetoothManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bluetooth.PdBluetoothManager$connect$2", m3970f = "PdBluetoothManager.kt", m3971i = {0, 1, 2, 2, 3, 3}, m3972l = {77, 80, 84, 92}, m3973m = "invokeSuspend", m3974n = {"$this$async", "$this$async", "$this$async", UtilityConfig.KEY_DEVICE_INFO, "$this$async", UtilityConfig.KEY_DEVICE_INFO}, m3975s = {"L$0", "L$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes4.dex */
public final class PdBluetoothManager$connect$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $fromMac;
    final /* synthetic */ String $mac;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4501p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PdBluetoothManager$connect$2(String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.$fromMac = str;
        this.$mac = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PdBluetoothManager$connect$2 pdBluetoothManager$connect$2 = new PdBluetoothManager$connect$2(this.$fromMac, this.$mac, completion);
        pdBluetoothManager$connect$2.f4501p$ = (CoroutineScope) obj;
        return pdBluetoothManager$connect$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PdBluetoothManager$connect$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:1|(1:(1:(1:(1:(3:7|8|9)(2:11|12))(8:13|14|15|16|17|(1:19)|8|9))(9:24|25|26|27|(1:29)|17|(0)|8|9))(1:32))(2:36|(2:38|(1:40))(2:41|42))|33|(1:35)|25|26|27|(0)|17|(0)|8|9|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0142, code lost:
    
        r3 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x01a3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0141 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Deferred deferred;
        Deferred deferred2;
        Deferred deferred3;
        BluetoothDevice bluetoothDevice;
        Deferred deferred4;
        PdBluetoothManager pdBluetoothManager;
        Deferred deferred5;
        PdBluetoothManager pdBluetoothManager2;
        Deferred deferred6;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4501p$;
            ProxyLog proxyLog = ProxyLog.INSTANCE;
            String TAG = PdBluetoothManager.INSTANCE.getTAG();
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            StringBuilder sb = new StringBuilder();
            sb.append("connect.start > ");
            sb.append(this.$fromMac);
            sb.append(' ');
            PdBluetoothManager pdBluetoothManager3 = PdBluetoothManager.INSTANCE;
            deferred = PdBluetoothManager.connectJob;
            sb.append(deferred);
            proxyLog.mo3285i(TAG, sb.toString());
            if (!BluetoothAdapter.checkBluetoothAddress(this.$mac)) {
                ProxyLog proxyLog2 = ProxyLog.INSTANCE;
                String TAG2 = PdBluetoothManager.INSTANCE.getTAG();
                Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                proxyLog2.mo3285i(TAG2, "connect > checkBluetoothAddress " + this.$mac);
                throw new IllegalArgumentException("error mac " + this.$mac);
            }
            ProxyLog proxyLog3 = ProxyLog.INSTANCE;
            String TAG3 = PdBluetoothManager.INSTANCE.getTAG();
            Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("connect.openBluetooth > ");
            sb2.append(this.$fromMac);
            sb2.append(' ');
            PdBluetoothManager pdBluetoothManager4 = PdBluetoothManager.INSTANCE;
            deferred2 = PdBluetoothManager.connectJob;
            sb2.append(deferred2);
            proxyLog3.mo3285i(TAG3, sb2.toString());
            PdBluetoothManager pdBluetoothManager5 = PdBluetoothManager.INSTANCE;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (pdBluetoothManager5.openBluetooth(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    bluetoothDevice = (BluetoothDevice) obj;
                    ProxyLog proxyLog4 = ProxyLog.INSTANCE;
                    String TAG4 = PdBluetoothManager.INSTANCE.getTAG();
                    Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("connect.bondDevice > ");
                    sb3.append(this.$fromMac);
                    sb3.append(' ');
                    PdBluetoothManager pdBluetoothManager6 = PdBluetoothManager.INSTANCE;
                    deferred4 = PdBluetoothManager.connectJob;
                    sb3.append(deferred4);
                    proxyLog4.mo3285i(TAG4, sb3.toString());
                    pdBluetoothManager = PdBluetoothManager.INSTANCE;
                    this.L$0 = coroutineScope;
                    this.L$1 = bluetoothDevice;
                    this.label = 3;
                    if (pdBluetoothManager.bondDevice(bluetoothDevice, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ProxyLog proxyLog5 = ProxyLog.INSTANCE;
                    String TAG5 = PdBluetoothManager.INSTANCE.getTAG();
                    Intrinsics.checkExpressionValueIsNotNull(TAG5, "TAG");
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("connect.realConnect > ");
                    sb4.append(this.$fromMac);
                    sb4.append(' ');
                    PdBluetoothManager pdBluetoothManager7 = PdBluetoothManager.INSTANCE;
                    deferred5 = PdBluetoothManager.connectJob;
                    sb4.append(deferred5);
                    proxyLog5.mo3285i(TAG5, sb4.toString());
                    pdBluetoothManager2 = PdBluetoothManager.INSTANCE;
                    this.L$0 = coroutineScope;
                    this.L$1 = bluetoothDevice;
                    this.label = 4;
                    if (pdBluetoothManager2.realConnect(true, bluetoothDevice, this) == coroutine_suspended) {
                    }
                    ProxyLog proxyLog6 = ProxyLog.INSTANCE;
                    String TAG6 = PdBluetoothManager.INSTANCE.getTAG();
                    Intrinsics.checkExpressionValueIsNotNull(TAG6, "TAG");
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("connect connect success > ");
                    sb5.append(this.$fromMac);
                    sb5.append(' ');
                    PdBluetoothManager pdBluetoothManager8 = PdBluetoothManager.INSTANCE;
                    deferred6 = PdBluetoothManager.connectJob;
                    sb5.append(deferred6);
                    proxyLog6.mo3285i(TAG6, sb5.toString());
                    return Unit.INSTANCE;
                }
                if (i != 3) {
                    if (i == 4) {
                        ResultKt.throwOnFailure(obj);
                        ProxyLog proxyLog62 = ProxyLog.INSTANCE;
                        String TAG62 = PdBluetoothManager.INSTANCE.getTAG();
                        Intrinsics.checkExpressionValueIsNotNull(TAG62, "TAG");
                        StringBuilder sb52 = new StringBuilder();
                        sb52.append("connect connect success > ");
                        sb52.append(this.$fromMac);
                        sb52.append(' ');
                        PdBluetoothManager pdBluetoothManager82 = PdBluetoothManager.INSTANCE;
                        deferred6 = PdBluetoothManager.connectJob;
                        sb52.append(deferred6);
                        proxyLog62.mo3285i(TAG62, sb52.toString());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                BluetoothDevice bluetoothDevice2 = (BluetoothDevice) this.L$1;
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    bluetoothDevice = bluetoothDevice2;
                    coroutineScope = coroutineScope2;
                } catch (Exception e) {
                    e = e;
                    bluetoothDevice = bluetoothDevice2;
                    coroutineScope = coroutineScope2;
                    ProxyLog proxyLog7 = ProxyLog.INSTANCE;
                    String TAG7 = PdBluetoothManager.INSTANCE.getTAG();
                    Intrinsics.checkExpressionValueIsNotNull(TAG7, "TAG");
                    proxyLog7.mo3285i(TAG7, "connect.bondDevice.error > " + Log.getStackTraceString(e));
                    ProxyLog proxyLog52 = ProxyLog.INSTANCE;
                    String TAG52 = PdBluetoothManager.INSTANCE.getTAG();
                    Intrinsics.checkExpressionValueIsNotNull(TAG52, "TAG");
                    StringBuilder sb42 = new StringBuilder();
                    sb42.append("connect.realConnect > ");
                    sb42.append(this.$fromMac);
                    sb42.append(' ');
                    PdBluetoothManager pdBluetoothManager72 = PdBluetoothManager.INSTANCE;
                    deferred5 = PdBluetoothManager.connectJob;
                    sb42.append(deferred5);
                    proxyLog52.mo3285i(TAG52, sb42.toString());
                    pdBluetoothManager2 = PdBluetoothManager.INSTANCE;
                    this.L$0 = coroutineScope;
                    this.L$1 = bluetoothDevice;
                    this.label = 4;
                    if (pdBluetoothManager2.realConnect(true, bluetoothDevice, this) == coroutine_suspended) {
                    }
                    ProxyLog proxyLog622 = ProxyLog.INSTANCE;
                    String TAG622 = PdBluetoothManager.INSTANCE.getTAG();
                    Intrinsics.checkExpressionValueIsNotNull(TAG622, "TAG");
                    StringBuilder sb522 = new StringBuilder();
                    sb522.append("connect connect success > ");
                    sb522.append(this.$fromMac);
                    sb522.append(' ');
                    PdBluetoothManager pdBluetoothManager822 = PdBluetoothManager.INSTANCE;
                    deferred6 = PdBluetoothManager.connectJob;
                    sb522.append(deferred6);
                    proxyLog622.mo3285i(TAG622, sb522.toString());
                    return Unit.INSTANCE;
                }
                ProxyLog proxyLog522 = ProxyLog.INSTANCE;
                String TAG522 = PdBluetoothManager.INSTANCE.getTAG();
                Intrinsics.checkExpressionValueIsNotNull(TAG522, "TAG");
                StringBuilder sb422 = new StringBuilder();
                sb422.append("connect.realConnect > ");
                sb422.append(this.$fromMac);
                sb422.append(' ');
                PdBluetoothManager pdBluetoothManager722 = PdBluetoothManager.INSTANCE;
                deferred5 = PdBluetoothManager.connectJob;
                sb422.append(deferred5);
                proxyLog522.mo3285i(TAG522, sb422.toString());
                pdBluetoothManager2 = PdBluetoothManager.INSTANCE;
                this.L$0 = coroutineScope;
                this.L$1 = bluetoothDevice;
                this.label = 4;
                if (pdBluetoothManager2.realConnect(true, bluetoothDevice, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                ProxyLog proxyLog6222 = ProxyLog.INSTANCE;
                String TAG6222 = PdBluetoothManager.INSTANCE.getTAG();
                Intrinsics.checkExpressionValueIsNotNull(TAG6222, "TAG");
                StringBuilder sb5222 = new StringBuilder();
                sb5222.append("connect connect success > ");
                sb5222.append(this.$fromMac);
                sb5222.append(' ');
                PdBluetoothManager pdBluetoothManager8222 = PdBluetoothManager.INSTANCE;
                deferred6 = PdBluetoothManager.connectJob;
                sb5222.append(deferred6);
                proxyLog6222.mo3285i(TAG6222, sb5222.toString());
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        ProxyLog proxyLog8 = ProxyLog.INSTANCE;
        String TAG8 = PdBluetoothManager.INSTANCE.getTAG();
        Intrinsics.checkExpressionValueIsNotNull(TAG8, "TAG");
        StringBuilder sb6 = new StringBuilder();
        sb6.append("connect.findDevice > ");
        sb6.append(this.$fromMac);
        sb6.append(' ');
        PdBluetoothManager pdBluetoothManager9 = PdBluetoothManager.INSTANCE;
        deferred3 = PdBluetoothManager.connectJob;
        sb6.append(deferred3);
        proxyLog8.mo3285i(TAG8, sb6.toString());
        PdBluetoothManager pdBluetoothManager10 = PdBluetoothManager.INSTANCE;
        String str = this.$mac;
        this.L$0 = coroutineScope;
        this.label = 2;
        obj = pdBluetoothManager10.findDevice(str, this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        bluetoothDevice = (BluetoothDevice) obj;
        ProxyLog proxyLog42 = ProxyLog.INSTANCE;
        String TAG42 = PdBluetoothManager.INSTANCE.getTAG();
        Intrinsics.checkExpressionValueIsNotNull(TAG42, "TAG");
        StringBuilder sb32 = new StringBuilder();
        sb32.append("connect.bondDevice > ");
        sb32.append(this.$fromMac);
        sb32.append(' ');
        PdBluetoothManager pdBluetoothManager62 = PdBluetoothManager.INSTANCE;
        deferred4 = PdBluetoothManager.connectJob;
        sb32.append(deferred4);
        proxyLog42.mo3285i(TAG42, sb32.toString());
        pdBluetoothManager = PdBluetoothManager.INSTANCE;
        this.L$0 = coroutineScope;
        this.L$1 = bluetoothDevice;
        this.label = 3;
        if (pdBluetoothManager.bondDevice(bluetoothDevice, this) == coroutine_suspended) {
        }
        ProxyLog proxyLog5222 = ProxyLog.INSTANCE;
        String TAG5222 = PdBluetoothManager.INSTANCE.getTAG();
        Intrinsics.checkExpressionValueIsNotNull(TAG5222, "TAG");
        StringBuilder sb4222 = new StringBuilder();
        sb4222.append("connect.realConnect > ");
        sb4222.append(this.$fromMac);
        sb4222.append(' ');
        PdBluetoothManager pdBluetoothManager7222 = PdBluetoothManager.INSTANCE;
        deferred5 = PdBluetoothManager.connectJob;
        sb4222.append(deferred5);
        proxyLog5222.mo3285i(TAG5222, sb4222.toString());
        pdBluetoothManager2 = PdBluetoothManager.INSTANCE;
        this.L$0 = coroutineScope;
        this.L$1 = bluetoothDevice;
        this.label = 4;
        if (pdBluetoothManager2.realConnect(true, bluetoothDevice, this) == coroutine_suspended) {
        }
        ProxyLog proxyLog62222 = ProxyLog.INSTANCE;
        String TAG62222 = PdBluetoothManager.INSTANCE.getTAG();
        Intrinsics.checkExpressionValueIsNotNull(TAG62222, "TAG");
        StringBuilder sb52222 = new StringBuilder();
        sb52222.append("connect connect success > ");
        sb52222.append(this.$fromMac);
        sb52222.append(' ');
        PdBluetoothManager pdBluetoothManager82222 = PdBluetoothManager.INSTANCE;
        deferred6 = PdBluetoothManager.connectJob;
        sb52222.append(deferred6);
        proxyLog62222.mo3285i(TAG62222, sb52222.toString());
        return Unit.INSTANCE;
    }
}
