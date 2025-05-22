package com.pudutech.disinfect.baselib.maintenance;

import com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener;
import com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RemoteMaintenanceInterfaceWrap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J \u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J\u0012\u0010\f\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0016J\u0012\u0010\u0011\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/maintenance/RemoteMaintenanceInterfaceWrap;", "Lcom/pudutech/remotemaintenance/aidl/RemoteMaintenanceInterface$Stub;", "()V", "init", "Lcom/pudutech/remotemaintenance/aidl/RemoteMaintenanceInterface;", "p0", "Lcom/pudutech/remotemaintenance/aidl/IRemoteMaintenanceListener;", "setCoordinateOrientation", "", "", "p1", "p2", "setMapPath", "", "setPower", "", "setSpeed", "setTask", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class RemoteMaintenanceInterfaceWrap extends RemoteMaintenanceInterface.Stub {
    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public void setCoordinateOrientation(double p0, double p1, double p2) {
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public void setMapPath(String p0) {
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public void setPower(int p0) {
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public void setSpeed(double p0) {
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public void setTask(String p0) {
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public RemoteMaintenanceInterface init(IRemoteMaintenanceListener p0) {
        return this;
    }
}
