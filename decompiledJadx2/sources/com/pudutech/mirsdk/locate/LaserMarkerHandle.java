package com.pudutech.mirsdk.locate;

import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LaserMarkerHandle.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/locate/LaserMarkerHandle;", "Lcom/pudutech/mirsdk/locate/LocateHandle;", ConfigJson.SENSOR, "", "(I)V", "checkLocate", "", "locateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "machine", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LaserMarkerHandle extends LocateHandle {

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LocateCase.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$0[LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$0[LocateCase.Slamware.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[LocateCase.values().length];
            $EnumSwitchMapping$1[LocateCase.LaserMark.ordinal()] = 1;
            $EnumSwitchMapping$1[LocateCase.Marker.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[MachineModel.values().length];
            $EnumSwitchMapping$2[MachineModel.Peanut.ordinal()] = 1;
            $EnumSwitchMapping$2[MachineModel.RecycleDog.ordinal()] = 2;
            $EnumSwitchMapping$2[MachineModel.Phoenix.ordinal()] = 3;
            $EnumSwitchMapping$2[MachineModel.BellaBot.ordinal()] = 4;
            $EnumSwitchMapping$2[MachineModel.Hls.ordinal()] = 5;
        }
    }

    public LaserMarkerHandle(int i) {
        super(i);
    }

    @Override // com.pudutech.mirsdk.locate.LocateHandle
    public boolean checkLocate(LocateCase locateCase, ProductMachineType machine, int sensor) {
        int i;
        Intrinsics.checkParameterIsNotNull(locateCase, "locateCase");
        Intrinsics.checkParameterIsNotNull(machine, "machine");
        int i2 = WhenMappings.$EnumSwitchMapping$2[machine.getModel().ordinal()];
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            if (locateCase == LocateCase.LaserMark) {
                return true;
            }
        } else if (i2 == 4) {
            int i3 = WhenMappings.$EnumSwitchMapping$0[locateCase.ordinal()];
            if (i3 == 1 || i3 == 2 || i3 == 3) {
                return true;
            }
        } else if (i2 == 5 && ((i = WhenMappings.$EnumSwitchMapping$1[locateCase.ordinal()]) == 1 || i == 2)) {
            return true;
        }
        return false;
    }
}
