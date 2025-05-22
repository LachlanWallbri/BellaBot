package com.pudutech.bumblebee.robot_ui.module.escape;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EscapeModule.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\f\r\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u0002H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\nH\u0086\b¢\u0006\u0002\u0010\u000bR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "presenter", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IViewDelegate;", "()Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IViewDelegate;", "IInteractor", "IView", "IViewDelegate", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class EscapeModule {
    public static final EscapeModule INSTANCE;
    private static final String TAG;

    /* compiled from: EscapeModule.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IInteractor;", "", "checkEscape", "", "checkMapHasNew", "checkNetwork", "uploadMap", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public interface IInteractor {
        void checkEscape();

        void checkMapHasNew();

        void checkNetwork();

        void uploadMap();
    }

    /* compiled from: EscapeModule.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IView;", "", "checkEnd", "", "checkNetworkNext", "showCheckNewMapView", "showDisNetworkView", "showLockView", "showSyncMapView", "showSyncedMapView", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public interface IView {
        void checkEnd();

        void checkNetworkNext();

        void showCheckNewMapView();

        void showDisNetworkView();

        void showLockView();

        void showSyncMapView();

        void showSyncedMapView();
    }

    /* compiled from: EscapeModule.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/EscapeModule$IViewDelegate;", "", "didCheckEscape", "", "hasEscape", "", "didCheckMapHasNew", "hasNew", "didCheckMapVersion", "needUpload", "didCheckNetwork", "available", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public interface IViewDelegate {
        void didCheckEscape(boolean hasEscape);

        void didCheckMapHasNew(boolean hasNew);

        void didCheckMapVersion(boolean needUpload);

        void didCheckNetwork(boolean available);
    }

    static {
        EscapeModule escapeModule = new EscapeModule();
        INSTANCE = escapeModule;
        TAG = escapeModule.getClass().getSimpleName();
    }

    private EscapeModule() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final /* synthetic */ <T extends IViewDelegate> T presenter() {
        EscapeView escapeView = new EscapeView();
        EscapeInteractor escapeInteractor = new EscapeInteractor(new EscapeService(new EscapeStorage(), RobotContext.INSTANCE.getLocationManager()));
        EscapePresenter escapePresenter = new EscapePresenter();
        escapePresenter.setView(escapeView);
        escapePresenter.setDelegate(escapeInteractor);
        escapeInteractor.setDelegate(escapePresenter);
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return escapePresenter;
    }
}
