package com.pudutech.freeinstall_wrapper;

import com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface;
import com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.TopoTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TopoMappingManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\nJ\u0015\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u000fJ\u0006\u0010\u0010\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/freeinstall_wrapper/TopoMappingManager;", "", "()V", "TAG", "", "mapServiceSdk", "Lcom/pudutech/mirsdk/aidl/mapify/MappingFunctionInterface;", "createTopomap", "", "destination", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "getTracks", "Lcom/pudutech/mirsdk/mircore/coreparcel/TopoTrack;", "init", "init$module_freeinstall_mirsdk_wrapper_release", "saveTopomap", "module_freeinstall_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TopoMappingManager {
    private final String TAG = "TopoMappingManager";
    private MappingFunctionInterface mapServiceSdk;

    public final void createTopomap(List<Destination> destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
    }

    public final void init$module_freeinstall_mirsdk_wrapper_release(MappingFunctionInterface mapServiceSdk) {
        Intrinsics.checkParameterIsNotNull(mapServiceSdk, "mapServiceSdk");
        this.mapServiceSdk = mapServiceSdk;
    }

    public final List<TopoTrack> getTracks() {
        TopoMappingInteface topoMappingInteface;
        MappingFunctionInterface mappingFunctionInterface = this.mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return null;
        }
        return topoMappingInteface.getTracks();
    }

    public final void saveTopomap() {
        TopoMappingInteface topoMappingInteface;
        MappingFunctionInterface mappingFunctionInterface = this.mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return;
        }
        topoMappingInteface.saveTopomap();
    }
}
