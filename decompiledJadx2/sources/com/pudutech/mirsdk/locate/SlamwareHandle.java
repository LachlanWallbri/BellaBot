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
/* compiled from: SlamwareHandle.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/locate/SlamwareHandle;", "Lcom/pudutech/mirsdk/locate/LocateHandle;", ConfigJson.SENSOR, "", "(I)V", "checkLocate", "", "locateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "machine", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SlamwareHandle extends LocateHandle {

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MachineModel.values().length];

        static {
            $EnumSwitchMapping$0[MachineModel.BellaBot.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineModel.Ninetales.ordinal()] = 2;
            $EnumSwitchMapping$0[MachineModel.RecycleDog.ordinal()] = 3;
        }
    }

    public SlamwareHandle(int i) {
        super(i);
    }

    @Override // com.pudutech.mirsdk.locate.LocateHandle
    public boolean checkLocate(LocateCase locateCase, ProductMachineType machine, int sensor) {
        Intrinsics.checkParameterIsNotNull(locateCase, "locateCase");
        Intrinsics.checkParameterIsNotNull(machine, "machine");
        int i = WhenMappings.$EnumSwitchMapping$0[machine.getModel().ordinal()];
        return (i == 1 || i == 2 || i == 3) && locateCase == LocateCase.Slamware;
    }
}
