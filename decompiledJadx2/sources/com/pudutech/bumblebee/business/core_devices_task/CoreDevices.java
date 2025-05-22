package com.pudutech.bumblebee.business.core_devices_task;

import com.pudutech.bumblebee.business.core_devices_task.battery.Battery;
import com.pudutech.bumblebee.business.core_devices_task.camera.IRLEDController;
import com.pudutech.bumblebee.business.core_devices_task.camera.MarkerCameraController;
import com.pudutech.bumblebee.business.core_devices_task.emergency.EmergencyTask;
import com.pudutech.bumblebee.business.core_devices_task.lidar.LidarController;
import com.pudutech.bumblebee.business.core_devices_task.mileage_task.MileageTask;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorTask;
import com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionTask;
import com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveTask;
import com.pudutech.bumblebee.business.core_devices_task.rgbd.RgbdController;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: CoreDevices.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\b\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00198FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\b\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\b\u001a\u0004\b\u001f\u0010 R\u001b\u0010\"\u001a\u00020#8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\b\u001a\u0004\b$\u0010%R\u001b\u0010'\u001a\u00020(8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\b\u001a\u0004\b)\u0010*R(\u0010.\u001a\u0004\u0018\u00010-2\b\u0010,\u001a\u0004\u0018\u00010-@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001b\u00103\u001a\u0002048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b7\u0010\b\u001a\u0004\b5\u00106R\u001b\u00108\u001a\u0002098FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\b\u001a\u0004\b:\u0010;¨\u0006="}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/CoreDevices;", "", "()V", "battery", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/Battery;", "getBattery", "()Lcom/pudutech/bumblebee/business/core_devices_task/battery/Battery;", "battery$delegate", "Lkotlin/Lazy;", "emergencyTask", "Lcom/pudutech/bumblebee/business/core_devices_task/emergency/EmergencyTask;", "getEmergencyTask", "()Lcom/pudutech/bumblebee/business/core_devices_task/emergency/EmergencyTask;", "emergencyTask$delegate", "irled", "Lcom/pudutech/bumblebee/business/core_devices_task/camera/IRLEDController;", "getIrled", "()Lcom/pudutech/bumblebee/business/core_devices_task/camera/IRLEDController;", "irled$delegate", "lidar", "Lcom/pudutech/bumblebee/business/core_devices_task/lidar/LidarController;", "getLidar", "()Lcom/pudutech/bumblebee/business/core_devices_task/lidar/LidarController;", "lidar$delegate", "markerCamera", "Lcom/pudutech/bumblebee/business/core_devices_task/camera/MarkerCameraController;", "getMarkerCamera", "()Lcom/pudutech/bumblebee/business/core_devices_task/camera/MarkerCameraController;", "markerCamera$delegate", "mileageTask", "Lcom/pudutech/bumblebee/business/core_devices_task/mileage_task/MileageTask;", "getMileageTask", "()Lcom/pudutech/bumblebee/business/core_devices_task/mileage_task/MileageTask;", "mileageTask$delegate", "monitorTask", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorTask;", "getMonitorTask", "()Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorTask;", "monitorTask$delegate", "motionTask", "Lcom/pudutech/bumblebee/business/core_devices_task/motion_task/MotionTask;", "getMotionTask", "()Lcom/pudutech/bumblebee/business/core_devices_task/motion_task/MotionTask;", "motionTask$delegate", "<set-?>", "Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "moveAction", "getMoveAction", "()Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;", "setMoveAction$module_bumblebee_business_robotRelease", "(Lcom/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator;)V", "powerSaveTask", "Lcom/pudutech/bumblebee/business/core_devices_task/power_save/PowerSaveTask;", "getPowerSaveTask", "()Lcom/pudutech/bumblebee/business/core_devices_task/power_save/PowerSaveTask;", "powerSaveTask$delegate", "rgbd", "Lcom/pudutech/bumblebee/business/core_devices_task/rgbd/RgbdController;", "getRgbd", "()Lcom/pudutech/bumblebee/business/core_devices_task/rgbd/RgbdController;", "rgbd$delegate", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CoreDevices {
    private static RobotMoveInterfaceDecorator moveAction;
    public static final CoreDevices INSTANCE = new CoreDevices();

    /* renamed from: battery$delegate, reason: from kotlin metadata */
    private static final Lazy battery = LazyKt.lazy(new Function0<Battery>() { // from class: com.pudutech.bumblebee.business.core_devices_task.CoreDevices$battery$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Battery invoke() {
            return new Battery();
        }
    });

    /* renamed from: mileageTask$delegate, reason: from kotlin metadata */
    private static final Lazy mileageTask = LazyKt.lazy(new Function0<MileageTask>() { // from class: com.pudutech.bumblebee.business.core_devices_task.CoreDevices$mileageTask$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MileageTask invoke() {
            return new MileageTask();
        }
    });

    /* renamed from: motionTask$delegate, reason: from kotlin metadata */
    private static final Lazy motionTask = LazyKt.lazy(new Function0<MotionTask>() { // from class: com.pudutech.bumblebee.business.core_devices_task.CoreDevices$motionTask$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MotionTask invoke() {
            return new MotionTask();
        }
    });

    /* renamed from: monitorTask$delegate, reason: from kotlin metadata */
    private static final Lazy monitorTask = LazyKt.lazy(new Function0<MonitorTask>() { // from class: com.pudutech.bumblebee.business.core_devices_task.CoreDevices$monitorTask$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MonitorTask invoke() {
            return new MonitorTask();
        }
    });

    /* renamed from: irled$delegate, reason: from kotlin metadata */
    private static final Lazy irled = LazyKt.lazy(new Function0<IRLEDController>() { // from class: com.pudutech.bumblebee.business.core_devices_task.CoreDevices$irled$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IRLEDController invoke() {
            return new IRLEDController();
        }
    });

    /* renamed from: lidar$delegate, reason: from kotlin metadata */
    private static final Lazy lidar = LazyKt.lazy(new Function0<LidarController>() { // from class: com.pudutech.bumblebee.business.core_devices_task.CoreDevices$lidar$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LidarController invoke() {
            return new LidarController();
        }
    });

    /* renamed from: rgbd$delegate, reason: from kotlin metadata */
    private static final Lazy rgbd = LazyKt.lazy(new Function0<RgbdController>() { // from class: com.pudutech.bumblebee.business.core_devices_task.CoreDevices$rgbd$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RgbdController invoke() {
            return new RgbdController();
        }
    });

    /* renamed from: markerCamera$delegate, reason: from kotlin metadata */
    private static final Lazy markerCamera = LazyKt.lazy(new Function0<MarkerCameraController>() { // from class: com.pudutech.bumblebee.business.core_devices_task.CoreDevices$markerCamera$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MarkerCameraController invoke() {
            return new MarkerCameraController();
        }
    });

    /* renamed from: emergencyTask$delegate, reason: from kotlin metadata */
    private static final Lazy emergencyTask = LazyKt.lazy(new Function0<EmergencyTask>() { // from class: com.pudutech.bumblebee.business.core_devices_task.CoreDevices$emergencyTask$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final EmergencyTask invoke() {
            return new EmergencyTask();
        }
    });

    /* renamed from: powerSaveTask$delegate, reason: from kotlin metadata */
    private static final Lazy powerSaveTask = LazyKt.lazy(new Function0<PowerSaveTask>() { // from class: com.pudutech.bumblebee.business.core_devices_task.CoreDevices$powerSaveTask$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PowerSaveTask invoke() {
            return new PowerSaveTask();
        }
    });

    public final Battery getBattery() {
        return (Battery) battery.getValue();
    }

    public final EmergencyTask getEmergencyTask() {
        return (EmergencyTask) emergencyTask.getValue();
    }

    public final IRLEDController getIrled() {
        return (IRLEDController) irled.getValue();
    }

    public final LidarController getLidar() {
        return (LidarController) lidar.getValue();
    }

    public final MarkerCameraController getMarkerCamera() {
        return (MarkerCameraController) markerCamera.getValue();
    }

    public final MileageTask getMileageTask() {
        return (MileageTask) mileageTask.getValue();
    }

    public final MonitorTask getMonitorTask() {
        return (MonitorTask) monitorTask.getValue();
    }

    public final MotionTask getMotionTask() {
        return (MotionTask) motionTask.getValue();
    }

    public final PowerSaveTask getPowerSaveTask() {
        return (PowerSaveTask) powerSaveTask.getValue();
    }

    public final RgbdController getRgbd() {
        return (RgbdController) rgbd.getValue();
    }

    private CoreDevices() {
    }

    public final RobotMoveInterfaceDecorator getMoveAction() {
        return moveAction;
    }

    public final void setMoveAction$module_bumblebee_business_robotRelease(RobotMoveInterfaceDecorator robotMoveInterfaceDecorator) {
        moveAction = robotMoveInterfaceDecorator;
    }
}
