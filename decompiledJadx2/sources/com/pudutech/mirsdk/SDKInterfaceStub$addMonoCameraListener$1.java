package com.pudutech.mirsdk;

import android.os.ParcelFileDescriptor;
import com.pudutech.mirsdk.aidl.IMarkerCameraListener;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/mirsdk/SDKInterfaceStub$addMonoCameraListener$1", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData$Stub;", "onFrame", "", "p0", "Landroid/os/ParcelFileDescriptor;", "p1", "", "p2", "p3", "p4", "p5", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SDKInterfaceStub$addMonoCameraListener$1 extends IMarkerCameraData.Stub {
    SDKInterfaceStub$addMonoCameraListener$1() {
    }

    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
    public void onFrame(final ParcelFileDescriptor p0, final int p1, final int p2, final int p3, final int p4, final long p5) {
        SDKInterfaceStub.access$getIMarkerCameraListener$p(SDKInterfaceStub.INSTANCE).notify(new Function2<IMarkerCameraListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$addMonoCameraListener$1$onFrame$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraListener iMarkerCameraListener, String str) {
                invoke2(iMarkerCameraListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IMarkerCameraListener it, String name) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                it.onFrame(p0, p1, p2, p3, p4, p5);
            }
        });
    }
}
