package com.pudutech.bumblebee.business.core_devices_task.emergency;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.business.core_devices_task.IDeviceListenerDispatcher;
import com.pudutech.mirsdk.aidl.DeviceInterface;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: EmergencyTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/emergency/EmergencyTask;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/core_devices_task/emergency/EmergencyListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/mirsdk/aidl/DeviceInterface;", "controller", "getController$module_bumblebee_business_robotRelease", "()Lcom/pudutech/mirsdk/aidl/DeviceInterface;", "setController$module_bumblebee_business_robotRelease", "(Lcom/pudutech/mirsdk/aidl/DeviceInterface;)V", "deviceListener", "Lcom/pudutech/bumblebee/business/core_devices_task/IDeviceListenerDispatcher;", "getDeviceListener", "()Lcom/pudutech/bumblebee/business/core_devices_task/IDeviceListenerDispatcher;", "deviceListener$delegate", "Lkotlin/Lazy;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class EmergencyTask extends BaseMultiListenerImpl<EmergencyListener> {
    private DeviceInterface controller;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: deviceListener$delegate, reason: from kotlin metadata */
    private final Lazy deviceListener = LazyKt.lazy(new Function0<IDeviceListenerDispatcher>() { // from class: com.pudutech.bumblebee.business.core_devices_task.emergency.EmergencyTask$deviceListener$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IDeviceListenerDispatcher invoke() {
            IDeviceListenerDispatcher iDeviceListenerDispatcher = new IDeviceListenerDispatcher();
            iDeviceListenerDispatcher.setOnEmergencyKeyPressed(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.emergency.EmergencyTask$deviceListener$2.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(final boolean z) {
                    String str;
                    ListenerList listeners;
                    str = EmergencyTask.this.TAG;
                    Pdlog.m3275i(str, "onCollision " + z);
                    listeners = EmergencyTask.this.getListeners();
                    listeners.forEach(new Function1<EmergencyListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.emergency.EmergencyTask.deviceListener.2.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(EmergencyListener emergencyListener) {
                            invoke2(emergencyListener);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(EmergencyListener it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            it.onEmergencyKeyPressed(z);
                        }
                    });
                }
            });
            return iDeviceListenerDispatcher;
        }
    });

    private final IDeviceListenerDispatcher getDeviceListener() {
        return (IDeviceListenerDispatcher) this.deviceListener.getValue();
    }

    /* renamed from: getController$module_bumblebee_business_robotRelease, reason: from getter */
    public final DeviceInterface getController() {
        return this.controller;
    }

    public final void setController$module_bumblebee_business_robotRelease(DeviceInterface deviceInterface) {
        DeviceInterface deviceInterface2 = this.controller;
        if (deviceInterface2 != null) {
            deviceInterface2.removeListener(this.TAG);
        }
        this.controller = deviceInterface;
        DeviceInterface deviceInterface3 = this.controller;
        if (deviceInterface3 != null) {
            deviceInterface3.addListener(this.TAG, getDeviceListener());
        }
    }
}
