package com.pudutech.freeinstall_wrapper;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface;
import com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface;
import com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface;
import com.pudutech.mirsdk.aidl.serialize.CruiseTracks;
import com.pudutech.mirsdk.aidl.serialize.DockerResult;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* compiled from: MapingFuntionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0004J\u0015\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u0004¢\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bJ\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0011J\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0011J\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u0011J\u001e\u0010#\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bJ\u0006\u0010$\u001a\u00020\u0018J\u0015\u0010%\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u0004¢\u0006\u0002\u0010\u0016J\u0006\u0010&\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, m3961d2 = {"Lcom/pudutech/freeinstall_wrapper/MapingFuntionManager;", "", "()V", "TAG", "", "context", "Landroid/content/Context;", "isConnecting", "", "isOpen", "mapServiceSdk", "Lcom/pudutech/mirsdk/aidl/mapify/MappingFunctionInterface;", "mappingSdkConnection", "Lcom/pudutech/base/architecture/AIDLConnection;", "sdkWorker", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "analyMap", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "mapName", "checkMapRule", "filePath", "(Ljava/lang/String;)Ljava/lang/Boolean;", "connectService", "", "mappingServiceConnectionListener", "Lcom/pudutech/freeinstall_wrapper/MappingServiceConnectionListener;", "isMock", "defaultResetRobot", "getCruiseTracksList", "Lcom/pudutech/mirsdk/aidl/serialize/CruiseTracks;", "getDockerChargeList", "Lcom/pudutech/mirsdk/aidl/serialize/DockerResult;", "getMapListVersion", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "init", "initSubModule", "moveMapPath", "open", "module_freeinstall_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapingFuntionManager {
    private static Context context;
    private static boolean isConnecting;
    private static boolean isOpen;
    private static MappingFunctionInterface mapServiceSdk;
    private static AIDLConnection<MappingFunctionInterface> mappingSdkConnection;
    public static final MapingFuntionManager INSTANCE = new MapingFuntionManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final ExecutorCoroutineDispatcher sdkWorker = ThreadPoolDispatcherKt.newSingleThreadContext("SDKWorker");

    private MapingFuntionManager() {
    }

    public static final /* synthetic */ Context access$getContext$p(MapingFuntionManager mapingFuntionManager) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final void init(Context context2, MappingServiceConnectionListener mappingServiceConnectionListener, boolean isMock) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(mappingServiceConnectionListener, "mappingServiceConnectionListener");
        context = context2;
        connectService(mappingServiceConnectionListener, isMock);
    }

    public final void connectService(final MappingServiceConnectionListener mappingServiceConnectionListener, boolean isMock) {
        Intrinsics.checkParameterIsNotNull(mappingServiceConnectionListener, "mappingServiceConnectionListener");
        if (isConnecting) {
            Pdlog.m3273d(TAG, "connectService : isConnecting ");
            return;
        }
        if (mappingSdkConnection != null) {
            Pdlog.m3277w(TAG, "connectService mappingSdkConnection not null ??");
            AIDLConnection<MappingFunctionInterface> aIDLConnection = mappingSdkConnection;
            if (aIDLConnection != null) {
                Context context2 = context;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                aIDLConnection.disconnect(context2);
            }
            mappingSdkConnection = (AIDLConnection) null;
        }
        final MapingFuntionManager$connectService$2 mapingFuntionManager$connectService$2 = MapingFuntionManager$connectService$2.INSTANCE;
        final String str = Constant.mapingFuntionServer;
        mappingSdkConnection = new AIDLConnection<MappingFunctionInterface>(str, mapingFuntionManager$connectService$2) { // from class: com.pudutech.freeinstall_wrapper.MapingFuntionManager$connectService$1
            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                super.onServiceConnected(name, service);
                MappingServiceConnectionListener.this.onMappingServiceConnected();
                MapingFuntionManager.INSTANCE.initSubModule();
                MapingFuntionManager mapingFuntionManager = MapingFuntionManager.INSTANCE;
                MapingFuntionManager.isConnecting = false;
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                super.onBindingDied(name);
                MapingFuntionManager mapingFuntionManager = MapingFuntionManager.INSTANCE;
                MapingFuntionManager.isConnecting = false;
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName name) {
                super.onNullBinding(name);
                MapingFuntionManager mapingFuntionManager = MapingFuntionManager.INSTANCE;
                MapingFuntionManager.isConnecting = false;
            }

            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                super.onServiceDisconnected(name);
                MappingServiceConnectionListener.this.onMappingServiceDisconnected();
                MapingFuntionManager mapingFuntionManager = MapingFuntionManager.INSTANCE;
                MapingFuntionManager.isConnecting = false;
            }
        };
        isConnecting = true;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, sdkWorker, null, new MapingFuntionManager$connectService$3(null), 2, null);
    }

    public final void initSubModule() {
        AIDLConnection<MappingFunctionInterface> aIDLConnection = mappingSdkConnection;
        mapServiceSdk = aIDLConnection != null ? aIDLConnection.getInterface() : null;
    }

    public final List<Destination> analyMap(String mapName) {
        TopoMappingInteface topoMappingInteface;
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        String str = TAG;
        Object[] objArr = new Object[3];
        objArr[0] = String.valueOf(mapName);
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        objArr[1] = mappingFunctionInterface;
        objArr[2] = mappingFunctionInterface != null ? mappingFunctionInterface.getTopoMappingInteface() : null;
        Pdlog.m3273d(str, objArr);
        MappingFunctionInterface mappingFunctionInterface2 = mapServiceSdk;
        if (mappingFunctionInterface2 == null || (topoMappingInteface = mappingFunctionInterface2.getTopoMappingInteface()) == null) {
            return null;
        }
        return topoMappingInteface.analyMap(mapName);
    }

    public final List<DockerResult> getDockerChargeList() {
        TopoMappingInteface topoMappingInteface;
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface != null) {
            if ((mappingFunctionInterface != null ? mappingFunctionInterface.getTopoMappingInteface() : null) != null) {
                MappingFunctionInterface mappingFunctionInterface2 = mapServiceSdk;
                if (mappingFunctionInterface2 == null || (topoMappingInteface = mappingFunctionInterface2.getTopoMappingInteface()) == null) {
                    return null;
                }
                return topoMappingInteface.getDockerChargeList();
            }
        }
        Pdlog.m3273d(TAG, "getDockerChargeList mapServiceSdk topoMappingInteface is null");
        return null;
    }

    public final List<CruiseTracks> getCruiseTracksList() {
        TopoMappingInteface topoMappingInteface;
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface != null) {
            if ((mappingFunctionInterface != null ? mappingFunctionInterface.getTopoMappingInteface() : null) != null) {
                MappingFunctionInterface mappingFunctionInterface2 = mapServiceSdk;
                if (mappingFunctionInterface2 == null || (topoMappingInteface = mappingFunctionInterface2.getTopoMappingInteface()) == null) {
                    return null;
                }
                return topoMappingInteface.getCruiseTracksList();
            }
        }
        Pdlog.m3273d(TAG, "getCruiseTracksList mapServiceSdk topoMappingInteface is null");
        return null;
    }

    public final void open() {
        Pdlog.m3273d(TAG, "open");
        if (mapServiceSdk == null) {
            Pdlog.m3273d(TAG, "open mapServiceSdk null");
            return;
        }
        if (isOpen) {
            Pdlog.m3273d(TAG, "mapServiceSdk : open: isOpen");
            return;
        }
        isOpen = true;
        Pdlog.m3273d(TAG, "mapServiceSdk : open");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface != null) {
            mappingFunctionInterface.open();
        }
        LocateMappingManager locateMappingManager = LocateMappingManager.INSTANCE;
        MappingFunctionInterface mappingFunctionInterface2 = mapServiceSdk;
        if (mappingFunctionInterface2 == null) {
            Intrinsics.throwNpe();
        }
        locateMappingManager.init$module_freeinstall_mirsdk_wrapper_release(mappingFunctionInterface2);
    }

    public final void defaultResetRobot() {
        Pdlog.m3273d(TAG, "defaultResetRobot");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null) {
            Pdlog.m3273d(TAG, "defaultResetRobot mapServiceSdk null");
        } else if (mappingFunctionInterface != null) {
            mappingFunctionInterface.defaultResetRobot();
        }
    }

    public final List<MapInfo> getMapListVersion() {
        LocateMappingInterface locatMappingInterface;
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface != null) {
            if ((mappingFunctionInterface != null ? mappingFunctionInterface.getLocatMappingInterface() : null) != null) {
                try {
                    MappingFunctionInterface mappingFunctionInterface2 = mapServiceSdk;
                    if (mappingFunctionInterface2 == null || (locatMappingInterface = mappingFunctionInterface2.getLocatMappingInterface()) == null) {
                        return null;
                    }
                    return locatMappingInterface.getMapInfoList();
                } catch (Exception unused) {
                    Pdlog.m3273d(TAG, "getMapVersion exception");
                    return null;
                }
            }
        }
        Pdlog.m3273d(TAG, "getMapVersion mapServiceSdk null");
        return null;
    }

    public final Boolean moveMapPath(String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null) {
            Pdlog.m3273d(TAG, "getMapVersion mapServiceSdk null");
            return false;
        }
        if (mappingFunctionInterface != null) {
            return Boolean.valueOf(mappingFunctionInterface.saveMapFromPlatform(filePath));
        }
        return null;
    }

    public final Boolean checkMapRule(String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null) {
            Pdlog.m3273d(TAG, "checkMapRule mapServiceSdk null");
            return false;
        }
        if (mappingFunctionInterface != null) {
            return Boolean.valueOf(mappingFunctionInterface.checkLegalMap(filePath));
        }
        return null;
    }
}
