package com.pudutech.freeinstall_ui.utils;

import com.pudutech.mirsdk.aidl.serialize.CameraType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StepDataHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/StepDataHelper;", "", "()V", "cameraType", "Lcom/pudutech/mirsdk/aidl/serialize/CameraType;", "getCameraType", "()Lcom/pudutech/mirsdk/aidl/serialize/CameraType;", "setCameraType", "(Lcom/pudutech/mirsdk/aidl/serialize/CameraType;)V", "isInitLocation", "", "()Z", "setInitLocation", "(Z)V", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class StepDataHelper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile StepDataHelper instance;
    private CameraType cameraType;
    private boolean isInitLocation;

    private StepDataHelper() {
        this.cameraType = CameraType.MARKER_CAMERA;
    }

    public /* synthetic */ StepDataHelper(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: StepDataHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/StepDataHelper$Companion;", "", "()V", "instance", "Lcom/pudutech/freeinstall_ui/utils/StepDataHelper;", "getInstance", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final StepDataHelper getInstance() {
            StepDataHelper stepDataHelper = StepDataHelper.instance;
            if (stepDataHelper == null) {
                synchronized (this) {
                    stepDataHelper = StepDataHelper.instance;
                    if (stepDataHelper == null) {
                        stepDataHelper = new StepDataHelper(null);
                        StepDataHelper.instance = stepDataHelper;
                    }
                }
            }
            return stepDataHelper;
        }
    }

    /* renamed from: isInitLocation, reason: from getter */
    public final boolean getIsInitLocation() {
        return this.isInitLocation;
    }

    public final void setInitLocation(boolean z) {
        this.isInitLocation = z;
    }

    public final CameraType getCameraType() {
        return this.cameraType;
    }

    public final void setCameraType(CameraType cameraType) {
        Intrinsics.checkParameterIsNotNull(cameraType, "<set-?>");
        this.cameraType = cameraType;
    }
}
