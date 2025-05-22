package com.pudutech.mirsdk.hardware;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0082@"}, m3961d2 = {"doCheckSensor", "", "sensorList", "", "Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$SelfCheckSensor;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12}, m3972l = {291, 292, 293, 294, 304, 306, 308, 314, 315, TypedValues.Attributes.TYPE_PATH_ROTATE, TypedValues.Attributes.TYPE_EASING, TypedValues.Attributes.TYPE_PIVOT_TARGET, 319}, m3973m = "doCheckSensor", m3974n = {"this", "sensorList", "selfCheckInfo", "this", "sensorList", "selfCheckInfo", "imuJob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "magicSensorjob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "magicSensorjob", "rgbdJob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "magicSensorjob", "rgbdJob", "cameraJob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "magicSensorjob", "rgbdJob", "cameraJob", "lidarJob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "magicSensorjob", "rgbdJob", "cameraJob", "lidarJob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "magicSensorjob", "rgbdJob", "cameraJob", "lidarJob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "magicSensorjob", "rgbdJob", "cameraJob", "lidarJob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "magicSensorjob", "rgbdJob", "cameraJob", "lidarJob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "magicSensorjob", "rgbdJob", "cameraJob", "lidarJob", "this", "sensorList", "selfCheckInfo", "imuJob", "encoderJob", "magicSensorjob", "rgbdJob", "cameraJob", "lidarJob"}, m3975s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"})
/* loaded from: classes5.dex */
final class HardwareInterfaceStub$doCheckSensor$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HardwareInterfaceStub this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HardwareInterfaceStub$doCheckSensor$1(HardwareInterfaceStub hardwareInterfaceStub, Continuation continuation) {
        super(continuation);
        this.this$0 = hardwareInterfaceStub;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doCheckSensor(null, this);
    }
}
