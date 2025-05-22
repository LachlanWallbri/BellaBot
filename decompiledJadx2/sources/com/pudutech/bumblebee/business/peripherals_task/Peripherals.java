package com.pudutech.bumblebee.business.peripherals_task;

import android.os.HandlerThread;
import com.pudutech.bumblebee.business.peripherals_task.ims.IMSTask;
import com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task.LCDScreen;
import com.pudutech.bumblebee.business.peripherals_task.led_screen_task.LEDScreenTask;
import com.pudutech.bumblebee.business.peripherals_task.led_task.LEDTask;
import com.pudutech.bumblebee.business.peripherals_task.lora_notice_task.LoRaNoticeTask;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletInstallTask;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTask;
import com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanTask;
import com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonTask;
import com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorTask;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: Peripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\b\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00198FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\b\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\b\u001a\u0004\b\u001f\u0010 R\u001b\u0010\"\u001a\u00020#8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\b\u001a\u0004\b$\u0010%R\u001b\u0010'\u001a\u00020(8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\b\u001a\u0004\b)\u0010*R\u001b\u0010,\u001a\u00020-8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\b\u001a\u0004\b.\u0010/R\u0011\u00101\u001a\u000202¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u001b\u00105\u001a\u0002068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\b\u001a\u0004\b7\u00108¨\u0006:"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/Peripherals;", "", "()V", "functionButton", "Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/FunctionButtonTask;", "getFunctionButton", "()Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/FunctionButtonTask;", "functionButton$delegate", "Lkotlin/Lazy;", "ims", "Lcom/pudutech/bumblebee/business/peripherals_task/ims/IMSTask;", "getIms", "()Lcom/pudutech/bumblebee/business/peripherals_task/ims/IMSTask;", "ims$delegate", "lcd", "Lcom/pudutech/bumblebee/business/peripherals_task/lcd_screed_task/LCDScreen;", "getLcd", "()Lcom/pudutech/bumblebee/business/peripherals_task/lcd_screed_task/LCDScreen;", "lcd$delegate", "ledControllers", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDTask;", "getLedControllers", "()Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDTask;", "ledControllers$delegate", "ledScreen", "Lcom/pudutech/bumblebee/business/peripherals_task/led_screen_task/LEDScreenTask;", "getLedScreen", "()Lcom/pudutech/bumblebee/business/peripherals_task/led_screen_task/LEDScreenTask;", "ledScreen$delegate", "lora", "Lcom/pudutech/bumblebee/business/peripherals_task/lora_notice_task/LoRaNoticeTask;", "getLora", "()Lcom/pudutech/bumblebee/business/peripherals_task/lora_notice_task/LoRaNoticeTask;", "lora$delegate", "pallet", "Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletTask;", "getPallet", "()Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletTask;", "pallet$delegate", "palletInstallTask", "Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletInstallTask;", "getPalletInstallTask", "()Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletInstallTask;", "palletInstallTask$delegate", "qrScanTask", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanTask;", "getQrScanTask", "()Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanTask;", "qrScanTask$delegate", "thread", "Landroid/os/HandlerThread;", "getThread", "()Landroid/os/HandlerThread;", "touchSensor", "Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/TouchSensorTask;", "getTouchSensor", "()Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/TouchSensorTask;", "touchSensor$delegate", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Peripherals {
    public static final Peripherals INSTANCE = new Peripherals();
    private static final HandlerThread thread = new HandlerThread("Peripherals");

    /* renamed from: touchSensor$delegate, reason: from kotlin metadata */
    private static final Lazy touchSensor = LazyKt.lazy(new Function0<TouchSensorTask>() { // from class: com.pudutech.bumblebee.business.peripherals_task.Peripherals$touchSensor$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TouchSensorTask invoke() {
            return new TouchSensorTask();
        }
    });

    /* renamed from: ledScreen$delegate, reason: from kotlin metadata */
    private static final Lazy ledScreen = LazyKt.lazy(new Function0<LEDScreenTask>() { // from class: com.pudutech.bumblebee.business.peripherals_task.Peripherals$ledScreen$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LEDScreenTask invoke() {
            return new LEDScreenTask();
        }
    });

    /* renamed from: ledControllers$delegate, reason: from kotlin metadata */
    private static final Lazy ledControllers = LazyKt.lazy(new Function0<LEDTask>() { // from class: com.pudutech.bumblebee.business.peripherals_task.Peripherals$ledControllers$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LEDTask invoke() {
            return new LEDTask();
        }
    });

    /* renamed from: functionButton$delegate, reason: from kotlin metadata */
    private static final Lazy functionButton = LazyKt.lazy(new Function0<FunctionButtonTask>() { // from class: com.pudutech.bumblebee.business.peripherals_task.Peripherals$functionButton$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FunctionButtonTask invoke() {
            return new FunctionButtonTask();
        }
    });

    /* renamed from: pallet$delegate, reason: from kotlin metadata */
    private static final Lazy pallet = LazyKt.lazy(new Function0<PalletTask>() { // from class: com.pudutech.bumblebee.business.peripherals_task.Peripherals$pallet$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PalletTask invoke() {
            return new PalletTask();
        }
    });

    /* renamed from: palletInstallTask$delegate, reason: from kotlin metadata */
    private static final Lazy palletInstallTask = LazyKt.lazy(new Function0<PalletInstallTask>() { // from class: com.pudutech.bumblebee.business.peripherals_task.Peripherals$palletInstallTask$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PalletInstallTask invoke() {
            return new PalletInstallTask();
        }
    });

    /* renamed from: lcd$delegate, reason: from kotlin metadata */
    private static final Lazy lcd = LazyKt.lazy(new Function0<LCDScreen>() { // from class: com.pudutech.bumblebee.business.peripherals_task.Peripherals$lcd$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LCDScreen invoke() {
            return new LCDScreen();
        }
    });

    /* renamed from: lora$delegate, reason: from kotlin metadata */
    private static final Lazy lora = LazyKt.lazy(new Function0<LoRaNoticeTask>() { // from class: com.pudutech.bumblebee.business.peripherals_task.Peripherals$lora$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LoRaNoticeTask invoke() {
            return new LoRaNoticeTask();
        }
    });

    /* renamed from: ims$delegate, reason: from kotlin metadata */
    private static final Lazy ims = LazyKt.lazy(new Function0<IMSTask>() { // from class: com.pudutech.bumblebee.business.peripherals_task.Peripherals$ims$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IMSTask invoke() {
            return new IMSTask();
        }
    });

    /* renamed from: qrScanTask$delegate, reason: from kotlin metadata */
    private static final Lazy qrScanTask = LazyKt.lazy(new Function0<QrScanTask>() { // from class: com.pudutech.bumblebee.business.peripherals_task.Peripherals$qrScanTask$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final QrScanTask invoke() {
            return new QrScanTask();
        }
    });

    public final FunctionButtonTask getFunctionButton() {
        return (FunctionButtonTask) functionButton.getValue();
    }

    public final IMSTask getIms() {
        return (IMSTask) ims.getValue();
    }

    public final LCDScreen getLcd() {
        return (LCDScreen) lcd.getValue();
    }

    public final LEDTask getLedControllers() {
        return (LEDTask) ledControllers.getValue();
    }

    public final LEDScreenTask getLedScreen() {
        return (LEDScreenTask) ledScreen.getValue();
    }

    public final LoRaNoticeTask getLora() {
        return (LoRaNoticeTask) lora.getValue();
    }

    public final PalletTask getPallet() {
        return (PalletTask) pallet.getValue();
    }

    public final PalletInstallTask getPalletInstallTask() {
        return (PalletInstallTask) palletInstallTask.getValue();
    }

    public final QrScanTask getQrScanTask() {
        return (QrScanTask) qrScanTask.getValue();
    }

    public final TouchSensorTask getTouchSensor() {
        return (TouchSensorTask) touchSensor.getValue();
    }

    private Peripherals() {
    }

    public final HandlerThread getThread() {
        return thread;
    }
}
