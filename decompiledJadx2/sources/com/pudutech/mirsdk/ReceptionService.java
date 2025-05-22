package com.pudutech.mirsdk;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.aidl.IReceptionService;
import com.pudutech.mirsdk.aidl.ISolicitListener;
import com.pudutech.mirsdk.aidl.IWelcomeListener;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* compiled from: ReceptionService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00126\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\f¢\u0006\u0002\u0010\u0014J\u001c\u0010\u001a\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u001c\u0010\u001d\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010 \u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u0011H\u0016J\u001e\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0013H\u0016J\b\u0010)\u001a\u00020\u0013H\u0016J\b\u0010*\u001a\u00020\u0013H\u0016J\u001c\u0010+\u001a\u00020\u00132\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u00020.\u0012\u0006\u0012\u0004\u0018\u00010\u00110-J\u000e\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u0011J\u001e\u00101\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082D¢\u0006\u0002\n\u0000R>\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, m3961d2 = {"Lcom/pudutech/mirsdk/ReceptionService;", "Lcom/pudutech/mirsdk/aidl/IReceptionService$Stub;", "hardware", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "robotStatus", "Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "watchDog", "Lcom/pudutech/mirsdk/WatchDog;", "_onStateChange", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "state", "", TmpConstant.SERVICE_DESC, "", "(Lcom/pudutech/mirsdk/mirhardware/RobotHardware;Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/mirsdk/mirhardware/RobotStatus;Lcom/pudutech/mirsdk/WatchDog;Lkotlin/jvm/functions/Function2;)V", "TAG", "solicitService", "Lcom/pudutech/mirsdk/SolicitService;", "welcomeService", "Lcom/pudutech/mirsdk/WelcomeService;", "addSolicitListener", "l", "Lcom/pudutech/mirsdk/aidl/ISolicitListener;", "addWelcomeListener", "Lcom/pudutech/mirsdk/aidl/IWelcomeListener;", "removeSolicitListener", "removeWelcomeListener", "setStartRobotPos", "x", "", "y", CompressorStreamFactory.f8930Z, "startSolicit", "", "startWelcome", "stopSolicit", "stopWelcome", "suspendWarningWelfunction", "warning", "Lkotlin/Pair;", "", "triggerError", "error", "updateRobotPos", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReceptionService extends IReceptionService.Stub {
    private final String TAG;
    private final Function2<RobotState, String, Unit> _onStateChange;
    private final AIDLConnection<MirCoreInterface> coreService;
    private final RobotHardware hardware;
    private final RobotStatus robotStatus;
    private final SolicitService solicitService;
    private final WatchDog watchDog;
    private final WelcomeService welcomeService;

    /* JADX WARN: Multi-variable type inference failed */
    public ReceptionService(RobotHardware hardware, AIDLConnection<MirCoreInterface> coreService, RobotStatus robotStatus, WatchDog watchDog, Function2<? super RobotState, ? super String, Unit> _onStateChange) {
        Intrinsics.checkParameterIsNotNull(hardware, "hardware");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        Intrinsics.checkParameterIsNotNull(robotStatus, "robotStatus");
        Intrinsics.checkParameterIsNotNull(watchDog, "watchDog");
        Intrinsics.checkParameterIsNotNull(_onStateChange, "_onStateChange");
        this.hardware = hardware;
        this.coreService = coreService;
        this.robotStatus = robotStatus;
        this.watchDog = watchDog;
        this._onStateChange = _onStateChange;
        this.TAG = "ReceptionService";
        this.solicitService = new SolicitService(this.hardware, this.coreService, this.robotStatus, this.watchDog, this._onStateChange);
        this.welcomeService = new WelcomeService(this.coreService);
    }

    @Override // com.pudutech.mirsdk.aidl.IReceptionService
    public void addSolicitListener(String name, ISolicitListener l) {
        String str = name;
        if ((str == null || str.length() == 0) || l == null) {
            Pdlog.m3273d(this.TAG, "addSolicitListener failure name=" + name);
            return;
        }
        this.solicitService.addSolicitListener(name, l);
    }

    @Override // com.pudutech.mirsdk.aidl.IReceptionService
    public void removeSolicitListener(String name) {
        String str = name;
        if (str == null || str.length() == 0) {
            Pdlog.m3273d(this.TAG, "removeSolicitListener failure name=" + name);
            return;
        }
        this.solicitService.removeSolicitListener(name);
    }

    @Override // com.pudutech.mirsdk.aidl.IReceptionService
    public int startSolicit() {
        return this.solicitService.startSolicit();
    }

    @Override // com.pudutech.mirsdk.aidl.IReceptionService
    public void stopSolicit() {
        Pdlog.m3273d(this.TAG, "stopSolicit-----");
        this.solicitService.stopSolicit();
    }

    public final void updateRobotPos(double x, double y, double z) {
        this.solicitService.updateRobotPos(x, y, z);
    }

    public final void setStartRobotPos(double x, double y, double z) {
        this.solicitService.setStartPose(new Vector3d(x, y, z));
    }

    @Override // com.pudutech.mirsdk.aidl.IReceptionService
    public void addWelcomeListener(String name, IWelcomeListener l) {
        String str = name;
        if ((str == null || str.length() == 0) || l == null) {
            Pdlog.m3273d(this.TAG, "addWelcomeListener failure name=" + name);
            return;
        }
        this.welcomeService.addWelcomeListener(name, l);
    }

    @Override // com.pudutech.mirsdk.aidl.IReceptionService
    public void removeWelcomeListener(String name) {
        String str = name;
        if (str == null || str.length() == 0) {
            Pdlog.m3273d(this.TAG, "removeWelcomeListener failure name=" + name);
            return;
        }
        this.welcomeService.removeWelcomeListener(name);
    }

    @Override // com.pudutech.mirsdk.aidl.IReceptionService
    public void startWelcome() {
        Pdlog.m3273d(this.TAG, "startWelcome");
        this.welcomeService.startWelcome();
    }

    @Override // com.pudutech.mirsdk.aidl.IReceptionService
    public void stopWelcome() {
        Pdlog.m3273d(this.TAG, "stopWelcome");
        this.welcomeService.stopWelcome();
    }

    public final void triggerError(String error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        this.solicitService.triggerError(error);
    }

    public final void suspendWarningWelfunction(Pair<Boolean, String> warning) {
        Intrinsics.checkParameterIsNotNull(warning, "warning");
        this.solicitService.suspendWarningWelfunction(warning);
    }
}
