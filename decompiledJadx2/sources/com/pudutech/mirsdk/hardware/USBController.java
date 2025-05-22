package com.pudutech.mirsdk.hardware;

import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.easynodelib.EasyNode;
import com.pudutech.easynodelib.EasyNodeDataCatcher;
import com.pudutech.mirsdk.hardware.UsbControlInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: USBController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0006\u0010\r\u001a\u00020\u0007J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/USBController;", "Lcom/pudutech/mirsdk/hardware/UsbControlInterface$Stub;", "()V", "mUSBControlListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/IUsbControlListener;", "addUSBControlListener", "", "name", "", "listener", "getBacklight", "", "init", "removeUSBControlListener", "sendUsbData", "data", "", "setBacklight", "brightness", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class USBController extends UsbControlInterface.Stub {
    private ThreadSafeListener<IUsbControlListener> mUSBControlListener = new ThreadSafeListener<>();

    @Override // com.pudutech.mirsdk.hardware.UsbControlInterface
    public int getBacklight() {
        return 100;
    }

    public final void init() {
        EasyNode.INSTANCE.registerUSBCtrl(new EasyNodeDataCatcher() { // from class: com.pudutech.mirsdk.hardware.USBController$init$1
            @Override // com.pudutech.easynodelib.EasyNodeDataCatcher
            public void callback(final byte[] data) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(data, "data");
                threadSafeListener = USBController.this.mUSBControlListener;
                threadSafeListener.notify(new Function2<IUsbControlListener, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.USBController$init$1$callback$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IUsbControlListener iUsbControlListener, String str) {
                        invoke2(iUsbControlListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IUsbControlListener it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onUsbData(data);
                    }
                });
            }
        });
    }

    @Override // com.pudutech.mirsdk.hardware.UsbControlInterface
    public void addUSBControlListener(String name, IUsbControlListener listener) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mUSBControlListener.remove(name);
        this.mUSBControlListener.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.UsbControlInterface
    public void removeUSBControlListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.mUSBControlListener.remove(name);
    }

    @Override // com.pudutech.mirsdk.hardware.UsbControlInterface
    public int setBacklight(int brightness) {
        return EasyNode.INSTANCE.setBacklight(brightness);
    }

    @Override // com.pudutech.mirsdk.hardware.UsbControlInterface
    public int sendUsbData(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        return EasyNode.INSTANCE.sendTheUsbCtrlData(data);
    }
}
