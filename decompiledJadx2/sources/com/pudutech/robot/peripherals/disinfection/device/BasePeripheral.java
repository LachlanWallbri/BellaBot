package com.pudutech.robot.peripherals.disinfection.device;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.SolicitService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* compiled from: BasePeripheral.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0014\b&\u0018\u0000 %2\u00020\u0001:\u0001%B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0017\u001a\u00020\u000bJ\u0011\u0010\u0018\u001a\u00020\u000bH¤@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0006\u0010\u001a\u001a\u00020\u000bJ\u0011\u0010\u001b\u001a\u00020\u000bH¤@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\b\u0010\u001c\u001a\u00020\u000bH\u0016J\b\u0010\u001d\u001a\u00020\u000bH\u0002J\b\u0010\u001e\u001a\u00020\u000bH\u0002J\u0010\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0012H\u0002J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0012H\u0002J\b\u0010\"\u001a\u00020\u000bH\u0002J\u0015\u0010#\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u0004H\u0000¢\u0006\u0002\b$R\u0016\u0010\u0003\u001a\u00020\u00048BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\u0004\u0018\u0001`\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/device/BasePeripheral;", "", "()V", "currentDeviceSwitchStatus", "", "getCurrentDeviceSwitchStatus", "()Z", "handler", "Landroid/os/Handler;", "onSwitchFailedListener", "Lkotlin/Function0;", "", "Lcom/pudutech/robot/peripherals/disinfection/device/OnSwitchFailedListener;", "getOnSwitchFailedListener$module_robot_peripherals_release", "()Lkotlin/jvm/functions/Function0;", "setOnSwitchFailedListener$module_robot_peripherals_release", "(Lkotlin/jvm/functions/Function0;)V", "retryCount", "", "switchStatus", "getSwitchStatus", "setSwitchStatus", "(Z)V", "close", "closeDevice", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "open", "openDevice", "resetRetry", "retryClose", "retryOpen", "startCheck", "count", "stopRetry", "switch", "switchResult", "switchResult$module_robot_peripherals_release", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class BasePeripheral {
    public static final int RETRY_CLOSE = 10002;
    public static final int RETRY_OPEN = 10001;
    public static final String TAG = "BasePeripheral";
    private boolean currentDeviceSwitchStatus;
    private final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.robot.peripherals.disinfection.device.BasePeripheral$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 10001) {
                BasePeripheral.this.retryOpen();
                return true;
            }
            if (i != 10002) {
                return true;
            }
            BasePeripheral.this.retryClose();
            return true;
        }
    });
    private Function0<Unit> onSwitchFailedListener;
    private int retryCount;
    private boolean switchStatus;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ExecutorCoroutineDispatcher deviceControlWork = ThreadPoolDispatcherKt.newSingleThreadContext("BasePeripheralContext");

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object closeDevice(Continuation<? super Unit> continuation);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object openDevice(Continuation<? super Unit> continuation);

    /* compiled from: BasePeripheral.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0084\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/device/BasePeripheral$Companion;", "", "()V", "RETRY_CLOSE", "", "RETRY_OPEN", "TAG", "", "deviceControlWork", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "deviceControlWork$annotations", "getDeviceControlWork", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        protected static /* synthetic */ void deviceControlWork$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        protected final ExecutorCoroutineDispatcher getDeviceControlWork() {
            return BasePeripheral.deviceControlWork;
        }
    }

    public final Function0<Unit> getOnSwitchFailedListener$module_robot_peripherals_release() {
        return this.onSwitchFailedListener;
    }

    public final void setOnSwitchFailedListener$module_robot_peripherals_release(Function0<Unit> function0) {
        this.onSwitchFailedListener = function0;
    }

    private final synchronized boolean getCurrentDeviceSwitchStatus() {
        Pdlog.m3273d(TAG, "current device open status " + this.currentDeviceSwitchStatus);
        return this.currentDeviceSwitchStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean getSwitchStatus() {
        return this.switchStatus;
    }

    protected final void setSwitchStatus(boolean z) {
        this.switchStatus = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retryClose() {
        Pdlog.m3273d(TAG, "retryClose " + this.retryCount);
        int i = this.retryCount;
        if (i < 3) {
            this.retryCount = i + 1;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, deviceControlWork, null, new BasePeripheral$retryClose$1(this, null), 2, null);
            startCheck(RETRY_CLOSE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retryOpen() {
        Pdlog.m3273d(TAG, "retryOpen " + this.retryCount);
        int i = this.retryCount;
        if (i < 3) {
            this.retryCount = i + 1;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, deviceControlWork, null, new BasePeripheral$retryOpen$1(this, null), 2, null);
            startCheck(10001);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCheck(int count) {
        stopRetry(count);
        this.handler.sendEmptyMessageDelayed(count, SolicitService.CAMERA_OPEN_TIME_OUT);
    }

    private final void stopRetry(int count) {
        this.handler.removeMessages(count);
    }

    public final void switchResult$module_robot_peripherals_release(boolean r4) {
        Pdlog.m3273d(TAG, "switchResult " + r4);
        if (r4) {
            synchronized (this) {
                this.currentDeviceSwitchStatus = r4;
                Unit unit = Unit.INSTANCE;
            }
            resetRetry();
            return;
        }
        Function0<Unit> function0 = this.onSwitchFailedListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final synchronized void close() {
        this.switchStatus = false;
        Pdlog.m3273d(TAG, "close currentDeviceSwitch " + getCurrentDeviceSwitchStatus());
        m4487switch();
    }

    public final synchronized void open() {
        this.switchStatus = true;
        Pdlog.m3273d(TAG, "open currentDeviceSwitch " + getCurrentDeviceSwitchStatus());
        m4487switch();
    }

    /* renamed from: switch, reason: not valid java name */
    private final void m4487switch() {
        Pdlog.m3273d(TAG, "fake switch " + this.switchStatus + " real switch " + getCurrentDeviceSwitchStatus());
        resetRetry();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, deviceControlWork, null, new BasePeripheral$switch$1(this, null), 2, null);
    }

    public void resetRetry() {
        stopRetry(10001);
        stopRetry(RETRY_CLOSE);
        this.retryCount = 0;
    }
}
