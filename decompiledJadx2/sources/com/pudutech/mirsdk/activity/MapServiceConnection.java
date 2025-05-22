package com.pudutech.mirsdk.activity;

import android.content.Context;
import android.os.IBinder;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_wrapper.Constant;
import com.pudutech.mirsdk.aidl.mapify.LocalizationListener;
import com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface;
import com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface;
import com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface;
import com.pudutech.mirsdk.aidl.serialize.DockerResult;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: MapServiceConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000?\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t*\u0001\n\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0010\u001a\u00020\rJ\u0006\u0010\u0011\u001a\u00020\rJ\u0006\u0010\u0012\u001a\u00020\rJ\r\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\r\u0010\u0016\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\u0006\u0010\u0017\u001a\u00020\rJ\u0006\u0010\u0018\u001a\u00020\rJ\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\rJ\u0006\u0010\u001d\u001a\u00020\rJ\r\u0010\u001e\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\u0006\u0010\u001f\u001a\u00020\rJ\u0006\u0010 \u001a\u00020\rJ\u0006\u0010!\u001a\u00020\rJ\u0006\u0010\"\u001a\u00020\rJ\u0006\u0010#\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006$"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/MapServiceConnection;", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/aidl/mapify/MappingFunctionInterface;", "()V", "TAG", "", "destinatiosn", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "locatlistener", "com/pudutech/mirsdk/activity/MapServiceConnection$locatlistener$1", "Lcom/pudutech/mirsdk/activity/MapServiceConnection$locatlistener$1;", "addChargeDocker", "", "addMapSensorListener", "addReinitmodulesListener", "addTable", "addlisttener", "addusher", "checkFinshflag", "", "()Ljava/lang/Boolean;", "checkflag", "createCruise", "deletemap", "disconnectConnection", "context", "Landroid/content/Context;", "finishMapping", "init", "optimezedMapFlag", "reinitModules", "removeMapSensorListener", "saveTopoMap", "saveoptimezedMapflag", "startMapping", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapServiceConnection extends AIDLConnection<MappingFunctionInterface> {
    public static final MapServiceConnection INSTANCE = new MapServiceConnection();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static List<Destination> destinatiosn = new ArrayList();
    private static final MapServiceConnection$locatlistener$1 locatlistener = new LocalizationListener.Stub() { // from class: com.pudutech.mirsdk.activity.MapServiceConnection$locatlistener$1
        @Override // com.pudutech.mirsdk.aidl.mapify.LocalizationListener
        public void updateRobotPosition(Vector3d p0) {
        }
    };

    public final void addReinitmodulesListener() {
    }

    public final void deletemap() {
    }

    public final void saveoptimezedMapflag() {
    }

    /* compiled from: MapServiceConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012,\u0010\u0003\u001a( \u0002*\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/mirsdk/aidl/mapify/MappingFunctionInterface;", "kotlin.jvm.PlatformType", "p1", "Landroid/os/IBinder;", "Lkotlin/ParameterName;", "name", "p0", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.activity.MapServiceConnection$1 */
    /* loaded from: classes4.dex */
    static final /* synthetic */ class C48161 extends FunctionReference implements Function1<IBinder, MappingFunctionInterface> {
        public static final C48161 INSTANCE = new C48161();

        C48161() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "asInterface";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(MappingFunctionInterface.Stub.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "asInterface(Landroid/os/IBinder;)Lcom/pudutech/mirsdk/aidl/mapify/MappingFunctionInterface;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final MappingFunctionInterface invoke(IBinder iBinder) {
            return MappingFunctionInterface.Stub.asInterface(iBinder);
        }
    }

    private MapServiceConnection() {
        super(Constant.mapingFuntionServer, C48161.INSTANCE, null, 4, null);
    }

    public final void init() {
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface != null) {
            mappingFunctionInterface.open();
        }
    }

    public final void addMapSensorListener() {
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface != null) {
            mappingFunctionInterface.addMappingSensorListener();
        }
    }

    public final void removeMapSensorListener() {
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface != null) {
            mappingFunctionInterface.removeMappingSensorListener();
        }
    }

    public final void addlisttener() {
        LocateMappingInterface locatMappingInterface;
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.addLocalizationListener("name", locatlistener);
    }

    public final Boolean checkflag() {
        LocateMappingInterface locatMappingInterface;
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return Boolean.valueOf(locatMappingInterface.checkBeginMappingMarkerVisible());
    }

    public final Boolean checkFinshflag() {
        LocateMappingInterface locatMappingInterface;
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return Boolean.valueOf(locatMappingInterface.checkFinishMappingMarkerVisible());
    }

    public final void disconnectConnection(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.disconnect(context);
    }

    public final void startMapping() {
        LocateMappingInterface locatMappingInterface;
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.startMapping();
    }

    public final void finishMapping() {
        LocateMappingInterface locatMappingInterface;
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.finishMapping();
    }

    public final Boolean optimezedMapFlag() {
        LocateMappingInterface locatMappingInterface;
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return Boolean.valueOf(locatMappingInterface.checkMappingOpt());
    }

    public final void reinitModules() {
        LocateMappingInterface locatMappingInterface;
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.reInitModules();
    }

    public final void addTable() {
        Destination destination = new Destination();
        destination.setName("testTable1");
        destination.setVector(SDKServiceConnection.INSTANCE.getVectorPose());
        destination.setMode(Constants.POINT_TYPE_TABLE);
        destinatiosn.add(destination);
    }

    public final void addusher() {
        Destination destination = new Destination();
        destination.setName("testUsher1");
        destination.setVector(SDKServiceConnection.INSTANCE.getVectorPose());
        destination.setMode(Constants.POINT_TYPE_DOOR);
        destinatiosn.add(destination);
    }

    public final void createCruise() {
        TopoMappingInteface topoMappingInteface;
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return;
        }
        topoMappingInteface.createCruise(SDKServiceConnection.INSTANCE.getVector3dList(), "name");
    }

    public final void addChargeDocker() {
        new DockerResult().setName("testCharge");
    }

    public final void saveTopoMap() {
        TopoMappingInteface topoMappingInteface;
        MappingFunctionInterface mappingFunctionInterface = INSTANCE.getInterface();
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return;
        }
        topoMappingInteface.saveTopomap();
    }
}
