package com.pudutech.mirsdk.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.mapify.LocalizationListener;
import com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface;
import com.pudutech.mirsdk.aidl.mapify.MapingModuleListener;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.aidl.serialize.MapStepType;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl;
import com.pudutech.mirsdk.map.MapConfig;
import com.pudutech.mirsdk.map.StringBase64Kt;
import com.pudutech.mirsdk.mapify.bean.Config;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
import com.pudutech.mirsdk.mapify.util.FileUtils;
import com.pudutech.mirsdk.mapify.util.ZipUtils;
import com.pudutech.mirsdk.mircore.InitMappingServiceListener;
import com.pudutech.mirsdk.mircore.MirMappingCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitStep;
import com.pudutech.mirsdk.mirhardware.MappingHardware;
import com.pudutech.schedulerlib.utils.CommandUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: LocateMappingInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0091\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f*\u0001\u001a\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001c\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\r2\b\u0010/\u001a\u0004\u0018\u00010\u001eH\u0016J\u0018\u00100\u001a\u00020-2\u0006\u00101\u001a\u00020\r2\u0006\u00102\u001a\u00020$H\u0016J\b\u00103\u001a\u00020-H\u0016J\b\u00104\u001a\u00020\u000fH\u0016J\b\u00105\u001a\u00020\u000fH\u0016J\b\u00106\u001a\u00020\u000fH\u0016J\u0010\u00107\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000bH\u0016J\b\u00109\u001a\u00020-H\u0002J>\u0010:\u001a\u00020-2\u0006\u0010;\u001a\u00020\r2\u0006\u0010.\u001a\u00020\r2\u0006\u0010<\u001a\u00020\u000f2\b\b\u0002\u0010=\u001a\u00020\u000b2\b\b\u0002\u0010>\u001a\u00020\u000b2\b\b\u0002\u0010?\u001a\u00020@H\u0002J\u0012\u0010A\u001a\u00020-2\b\u0010B\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010C\u001a\u00020-H\u0016J\u000e\u0010D\u001a\b\u0012\u0004\u0012\u00020F0EH\u0016J\u0012\u0010G\u001a\u0004\u0018\u00010\u00182\u0006\u0010B\u001a\u00020\rH\u0016J\u0012\u0010H\u001a\u0004\u0018\u00010F2\b\u0010B\u001a\u0004\u0018\u00010\rJ\n\u0010I\u001a\u0004\u0018\u00010JH\u0016J\n\u0010K\u001a\u0004\u0018\u00010JH\u0016J\b\u0010L\u001a\u00020\rH\u0016J\b\u0010M\u001a\u00020-H\u0016J\b\u0010N\u001a\u00020-H\u0016J\b\u0010O\u001a\u00020-H\u0016J\u0012\u0010P\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010Q\u001a\u00020-2\u0006\u00101\u001a\u00020\rH\u0016J \u0010R\u001a\u00020-2\u0006\u0010B\u001a\u00020\r2\u0006\u0010S\u001a\u00020\r2\u0006\u0010T\u001a\u00020\u0018H\u0002J\u001a\u0010U\u001a\u00020-2\b\u0010B\u001a\u0004\u0018\u00010\r2\u0006\u0010T\u001a\u00020\u0018H\u0016J\b\u0010V\u001a\u00020-H\u0002J\b\u0010W\u001a\u00020-H\u0016J\u0018\u0010X\u001a\u00020-2\u0006\u0010B\u001a\u00020\r2\u0006\u0010=\u001a\u00020\u000bH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001bR \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Y"}, m3961d2 = {"Lcom/pudutech/mirsdk/impl/LocateMappingInterfaceImpl;", "Lcom/pudutech/mirsdk/aidl/mapify/LocateMappingInterface$Stub;", "mapcoreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirMappingCoreInterface;", "mappingHardware", "Lcom/pudutech/mirsdk/mirhardware/MappingHardware;", "mContext", "Landroid/content/Context;", "(Lcom/pudutech/base/architecture/AIDLConnection;Lcom/pudutech/mirsdk/mirhardware/MappingHardware;Landroid/content/Context;)V", "MEMORY_LIMIT", "", "TAG", "", "enableAutoExposure", "", "extendFlag", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "setGson", "(Lcom/google/gson/Gson;)V", "initDestination", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "initMappingServiceListener", "com/pudutech/mirsdk/impl/LocateMappingInterfaceImpl$initMappingServiceListener$1", "Lcom/pudutech/mirsdk/impl/LocateMappingInterfaceImpl$initMappingServiceListener$1;", "localizationListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/mapify/LocalizationListener;", "getLocalizationListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "setLocalizationListener", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "mapingModuleListener", "Lcom/pudutech/mirsdk/aidl/mapify/MapingModuleListener;", "getMapingModuleListener", "setMapingModuleListener", "objectMapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "sendFlag", "unzipMap", "Ljava/io/File;", "addLocalizationListener", "", "name", "listener", "addMapingModuleListener", "p0", "p1", "cancelMapping", "checkBeginMappingMarkerVisible", "checkFinishMappingMarkerVisible", "checkMappingOpt", "checkMemoryLimit", "size", "clearCache", "createMapConfig", "dirPath", "newMap", "version", ConfigJson.SENSOR, "cameraType", "Lcom/pudutech/mirsdk/aidl/serialize/CameraType;", "deleteSavedMap", "mapName", "finishMapping", "getMapInfoList", "", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "getMapInitPoint", "getMapVersion", "getOptimizedMap", "Landroid/os/ParcelFileDescriptor;", "getRealMapData", "getTempMapRoot", "initExtendDrawing", "initExtendFlag", "reInitModules", "removeLocalizationListener", "removeMapingModuleListener", "saveMap", "temMapdataRoot", "initPoint", "saveOptimizedMap", "sendRobotPose", "startMapping", "updateMapVersion", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LocateMappingInterfaceImpl extends LocateMappingInterface.Stub {
    private final int MEMORY_LIMIT;
    private final String TAG;
    private boolean enableAutoExposure;
    private boolean extendFlag;
    private Gson gson;
    private Destination initDestination;
    private LocateMappingInterfaceImpl$initMappingServiceListener$1 initMappingServiceListener;
    private ThreadSafeListener<LocalizationListener> localizationListener;
    private final Context mContext;
    private final AIDLConnection<MirMappingCoreInterface> mapcoreService;
    private ThreadSafeListener<MapingModuleListener> mapingModuleListener;
    private final MappingHardware mappingHardware;
    private final ObjectMapper objectMapper;
    private boolean sendFlag;
    private File unzipMap;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MappingCoreInitStep.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[MappingCoreInitStep.Finish.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[MappingCoreInitStep.values().length];
            $EnumSwitchMapping$1[MappingCoreInitStep.Finish.ordinal()] = 1;
            $EnumSwitchMapping$2 = new int[LocateCase.values().length];
            $EnumSwitchMapping$2[LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$2[LocateCase.Laser.ordinal()] = 2;
            $EnumSwitchMapping$2[LocateCase.LaserMark.ordinal()] = 3;
        }
    }

    /* JADX WARN: Type inference failed for: r2v8, types: [com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl$initMappingServiceListener$1] */
    public LocateMappingInterfaceImpl(AIDLConnection<MirMappingCoreInterface> mapcoreService, MappingHardware mappingHardware, Context mContext) {
        Intrinsics.checkParameterIsNotNull(mapcoreService, "mapcoreService");
        Intrinsics.checkParameterIsNotNull(mappingHardware, "mappingHardware");
        Intrinsics.checkParameterIsNotNull(mContext, "mContext");
        this.mapcoreService = mapcoreService;
        this.mappingHardware = mappingHardware;
        this.mContext = mContext;
        this.TAG = "LocateMappingInterface";
        this.localizationListener = new ThreadSafeListener<>();
        this.mapingModuleListener = new ThreadSafeListener<>();
        this.gson = new Gson();
        this.MEMORY_LIMIT = 2;
        this.enableAutoExposure = true;
        this.objectMapper = new ObjectMapper();
        this.initMappingServiceListener = new InitMappingServiceListener.Stub() { // from class: com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl$initMappingServiceListener$1
            @Override // com.pudutech.mirsdk.mircore.InitMappingServiceListener
            public void initMappingCoreServiceState(MappingCoreInitStep p0, MappingCoreInitState p1, String p2) {
                if (p0 != null && LocateMappingInterfaceImpl.WhenMappings.$EnumSwitchMapping$1[p0.ordinal()] == 1) {
                    if (p1 == MappingCoreInitState.Success) {
                        LocateMappingInterfaceImpl.this.getMapingModuleListener().notify(new Function2<MapingModuleListener, String, Unit>() { // from class: com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl$initMappingServiceListener$1$initMappingCoreServiceState$1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(MapingModuleListener mapingModuleListener, String str) {
                                invoke2(mapingModuleListener, str);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(MapingModuleListener it, String name) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(name, "name");
                                it.mapingInitStepResult(MapStepType.InitExtendDrawing, MappingCoreInitState.Success);
                            }
                        });
                    }
                    Pdlog.m3276v("MappingCoreInitState", "MappingCoreInitState.Success");
                } else if (p1 == MappingCoreInitState.Fail) {
                    LocateMappingInterfaceImpl.this.getMapingModuleListener().notify(new Function2<MapingModuleListener, String, Unit>() { // from class: com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl$initMappingServiceListener$1$initMappingCoreServiceState$2
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(MapingModuleListener mapingModuleListener, String str) {
                            invoke2(mapingModuleListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MapingModuleListener it, String name) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(name, "name");
                            it.mapingInitStepResult(MapStepType.InitExtendDrawing, MappingCoreInitState.Fail);
                        }
                    });
                    Pdlog.m3276v("MappingCoreInitState", "MappingCoreInitState.Fail");
                }
            }
        };
    }

    public final ThreadSafeListener<LocalizationListener> getLocalizationListener() {
        return this.localizationListener;
    }

    public final void setLocalizationListener(ThreadSafeListener<LocalizationListener> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        this.localizationListener = threadSafeListener;
    }

    public final ThreadSafeListener<MapingModuleListener> getMapingModuleListener() {
        return this.mapingModuleListener;
    }

    public final void setMapingModuleListener(ThreadSafeListener<MapingModuleListener> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        this.mapingModuleListener = threadSafeListener;
    }

    public final Gson getGson() {
        return this.gson;
    }

    public final void setGson(Gson gson) {
        Intrinsics.checkParameterIsNotNull(gson, "<set-?>");
        this.gson = gson;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public boolean checkMappingOpt() {
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        Boolean valueOf = mirMappingCoreInterface != null ? Boolean.valueOf(mirMappingCoreInterface.getMappingOptStatus()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        return valueOf.booleanValue();
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public boolean checkBeginMappingMarkerVisible() {
        Pdlog.m3273d(this.TAG, "checkBeginMappingMarkerVisible  begin");
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        boolean checkBeginMappingMarkerVisible = mirMappingCoreInterface != null ? mirMappingCoreInterface.checkBeginMappingMarkerVisible() : false;
        Pdlog.m3273d(this.TAG, "mappingHardware.getInterface()? start bool= " + checkBeginMappingMarkerVisible);
        return checkBeginMappingMarkerVisible;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void startMapping() {
        clearCache();
        Pdlog.m3273d(this.TAG, " mapcoreService.getInterface()?.startMapping()");
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        if (mirMappingCoreInterface != null) {
            mirMappingCoreInterface.startMapping();
        }
    }

    private final void clearCache() {
        CommandUtils.INSTANCE.executeCommand(InvokeServiceData.CALL_TYPE_SYNC);
        CommandUtils.INSTANCE.executeCommand("echo 1 > /proc/sys/vm/drop_caches");
        CommandUtils.INSTANCE.executeCommand("echo 2 > /proc/sys/vm/drop_caches");
        CommandUtils.INSTANCE.executeCommand("echo 3 > /proc/sys/vm/drop_caches");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void sendRobotPose() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        objectRef.element = mirMappingCoreInterface != null ? mirMappingCoreInterface.getRobotPose() : 0;
        this.localizationListener.notify(new Function2<LocalizationListener, String, Unit>() { // from class: com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl$sendRobotPose$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(LocalizationListener localizationListener, String str) {
                invoke2(localizationListener, str);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LocalizationListener it, String name) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                it.updateRobotPosition((Vector3d) Ref.ObjectRef.this.element);
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void finishMapping() {
        clearCache();
        Pdlog.m3273d(this.TAG, "mapcoreService.getInterface()?.finishMapping()");
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        if (mirMappingCoreInterface != null) {
            mirMappingCoreInterface.finishMapping();
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void cancelMapping() {
        Pdlog.m3273d(this.TAG, "cancelMapping has called");
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        if (mirMappingCoreInterface != null) {
            mirMappingCoreInterface.cancelMapping();
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void addLocalizationListener(String name, LocalizationListener listener) {
        Pdlog.m3273d("addLocalizationListener", "added robotpose LocalizationListener");
        if (name == null || listener == null) {
            return;
        }
        this.localizationListener.add(name, listener);
        this.sendFlag = true;
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new LocateMappingInterfaceImpl$addLocalizationListener$1(this, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void removeLocalizationListener(String name) {
        Pdlog.m3273d("addLocalizationListener", "added robotpose LocalizationListener " + name);
        if (name != null) {
            this.localizationListener.remove(name);
            this.sendFlag = false;
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public boolean checkMemoryLimit(int size) {
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        if (mirMappingCoreInterface == null) {
            Intrinsics.throwNpe();
        }
        boolean checkMapLimit = mirMappingCoreInterface.checkMapLimit(size);
        Pdlog.m3273d(this.TAG, "check memory Limit " + checkMapLimit + " size " + size);
        return checkMapLimit;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void reInitModules() {
        Pdlog.m3273d(this.TAG, "coreService.reInitModules CoreInitState.Success Start");
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        if (mirMappingCoreInterface != null) {
            mirMappingCoreInterface.reInitModules(new InitMappingServiceListener.Stub() { // from class: com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl$reInitModules$1
                @Override // com.pudutech.mirsdk.mircore.InitMappingServiceListener
                public void initMappingCoreServiceState(MappingCoreInitStep p0, MappingCoreInitState p1, String p2) {
                    String str;
                    String str2;
                    String str3;
                    if (p0 != null && LocateMappingInterfaceImpl.WhenMappings.$EnumSwitchMapping$0[p0.ordinal()] == 1) {
                        str2 = LocateMappingInterfaceImpl.this.TAG;
                        Pdlog.m3273d(str2, "coreService.reInitModules CoreInitState.Success state is " + p1);
                        if (p1 == MappingCoreInitState.Success) {
                            LocateMappingInterfaceImpl.this.getMapingModuleListener().notify(new Function2<MapingModuleListener, String, Unit>() { // from class: com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl$reInitModules$1$initMappingCoreServiceState$1
                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(MapingModuleListener mapingModuleListener, String str4) {
                                    invoke2(mapingModuleListener, str4);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(MapingModuleListener it, String name) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    Intrinsics.checkParameterIsNotNull(name, "name");
                                    it.mapingInitStepResult(MapStepType.ReInitModules, MappingCoreInitState.Success);
                                }
                            });
                            str3 = LocateMappingInterfaceImpl.this.TAG;
                            Pdlog.m3273d(str3, "coreService.reInitModules CoreInitState.Success state is");
                            return;
                        }
                        return;
                    }
                    if (p1 == MappingCoreInitState.Fail) {
                        LocateMappingInterfaceImpl.this.getMapingModuleListener().notify(new Function2<MapingModuleListener, String, Unit>() { // from class: com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl$reInitModules$1$initMappingCoreServiceState$2
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(MapingModuleListener mapingModuleListener, String str4) {
                                invoke2(mapingModuleListener, str4);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(MapingModuleListener it, String name) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(name, "name");
                                it.mapingInitStepResult(MapStepType.ReInitModules, MappingCoreInitState.Fail);
                            }
                        });
                        str = LocateMappingInterfaceImpl.this.TAG;
                        Pdlog.m3273d(str, "coreService.reInitModules CoreInitState.Fail state is");
                    }
                }
            });
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public boolean checkFinishMappingMarkerVisible() {
        Pdlog.m3273d(this.TAG, "checkFinishMappingMarkerVisible  and type is " + CamerConfigHelper.cameraType);
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        boolean checkFinishMappingMarkerVisible = mirMappingCoreInterface != null ? mirMappingCoreInterface.checkFinishMappingMarkerVisible() : false;
        Pdlog.m3273d(this.TAG, "mappingHardware.getInterface()? finish bool= " + checkFinishMappingMarkerVisible);
        return checkFinishMappingMarkerVisible;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public String getTempMapRoot() {
        String string = SDKConfig.INSTANCE.getPreferences().getString("tempMaproot", MapFilePathConfig.TMP_MAP_LOCATE_PATH);
        Intrinsics.checkExpressionValueIsNotNull(string, "SDKConfig.preferences.ge…nfig.TMP_MAP_LOCATE_PATH)");
        return string;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public ParcelFileDescriptor getOptimizedMap() {
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        if (mirMappingCoreInterface != null) {
            return mirMappingCoreInterface.getFinalMapDataUI();
        }
        return null;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public ParcelFileDescriptor getRealMapData() {
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        if (mirMappingCoreInterface != null) {
            return mirMappingCoreInterface.getMapDataUI();
        }
        return null;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void removeMapingModuleListener(String p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        this.mapingModuleListener.remove(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void saveOptimizedMap(String mapName, Destination initPoint) {
        Intrinsics.checkParameterIsNotNull(initPoint, "initPoint");
        MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
        String loclizationMap = mirMappingCoreInterface != null ? mirMappingCoreInterface.getLoclizationMap() : null;
        SharedPreferences.Editor edit = SDKConfig.INSTANCE.getPreferences().edit();
        edit.putString("tempMaproot", loclizationMap);
        edit.apply();
        Pdlog.m3273d("tempcreateroot", "the temp dir is    is " + loclizationMap);
        MapFilePathConfig mapFilePathConfig = MapFilePathConfig.INSTANCE;
        if (mapName == null) {
            Intrinsics.throwNpe();
        }
        mapFilePathConfig.setCREATE_MAPNAME(mapName);
        if (loclizationMap != null) {
            saveMap(mapName, loclizationMap, initPoint);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public Destination getMapInitPoint(String mapName) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        File file = new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, StringBase64Kt.encodeMapName(mapName) + ".pdmap");
        ZipUtils.unzipFile(file, new File(file.getParent(), FileUtils.getFileNameNoExtension(file.getName())));
        File file2 = new File((MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH + "/") + StringBase64Kt.encodeMapName(mapName), MapFilePathConfig.INIT_POINT_JSON);
        if (file2.exists()) {
            this.initDestination = (Destination) this.gson.fromJson(FilesKt.readText$default(file2, null, 1, null), Destination.class);
            Pdlog.m3273d(this.TAG, "getMapInitPoint in init point");
        }
        return this.initDestination;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void initExtendFlag() {
        this.extendFlag = false;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void deleteSavedMap(String mapName) {
        File file = new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, Intrinsics.stringPlus(mapName != null ? StringBase64Kt.encodeMapName(mapName) : null, ".pdmap"));
        if (file.exists()) {
            file.delete();
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void initExtendDrawing() {
        File file = new File(getTempMapRoot(), MapFilePathConfig.LOCATE_MAP_DATA_NAME);
        File file2 = new File(getTempMapRoot(), "optemap.pgm");
        File file3 = new File(getTempMapRoot(), "optemap.yaml");
        this.extendFlag = true;
        Pdlog.m3273d("initExtendDrawing", "MapConfig.LocateCase is " + MapFilePathConfig.INSTANCE.getLocateCase());
        if (this.mapcoreService.getInterface() != null) {
            Pdlog.m3273d("initExtendDrawing", "mapcoreService.getInterface() not null ");
        }
        int i = WhenMappings.$EnumSwitchMapping$2[MapFilePathConfig.INSTANCE.getLocateCase().ordinal()];
        if (i == 1) {
            Pdlog.m3273d("initExtendDrawing", "extending scheme is LocateCase.Marker ");
            MirMappingCoreInterface mirMappingCoreInterface = this.mapcoreService.getInterface();
            if (mirMappingCoreInterface != null) {
                mirMappingCoreInterface.initExtendMapping(null, null, FilesKt.readBytes(file), this.initMappingServiceListener);
                return;
            }
            return;
        }
        if (i == 2) {
            Pdlog.m3273d("initExtendDrawing", "extending scheme is LocateCase.Laser ");
            MirMappingCoreInterface mirMappingCoreInterface2 = this.mapcoreService.getInterface();
            if (mirMappingCoreInterface2 != null) {
                mirMappingCoreInterface2.initExtendMapping(FilesKt.readBytes(file2), FilesKt.readBytes(file3), null, this.initMappingServiceListener);
                return;
            }
            return;
        }
        if (i != 3) {
            return;
        }
        Pdlog.m3273d("initExtendDrawing", "extending scheme is LaserMark");
        MirMappingCoreInterface mirMappingCoreInterface3 = this.mapcoreService.getInterface();
        if (mirMappingCoreInterface3 != null) {
            mirMappingCoreInterface3.initExtendMapping(FilesKt.readBytes(file2), FilesKt.readBytes(file3), FilesKt.readBytes(file), this.initMappingServiceListener);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void addMapingModuleListener(String p0, MapingModuleListener p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        Pdlog.m3273d(this.TAG, "mapingModuleListener have added");
        this.mapingModuleListener.add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public List<MapInfo> getMapInfoList() {
        ArrayList arrayList = new ArrayList();
        File[] mapFiles = new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH).listFiles(new FilenameFilter() { // from class: com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl$getMapInfoList$mapFiles$1
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return str != null && StringsKt.contains$default((CharSequence) str, (CharSequence) "pdmap", false, 2, (Object) null);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(mapFiles, "mapFiles");
        if (mapFiles.length == 0) {
            Pdlog.m3273d(this.TAG, "mapFiles.isEmpty()");
            return arrayList;
        }
        for (File file : mapFiles) {
            StringBuilder sb = new StringBuilder();
            sb.append("name is ");
            Intrinsics.checkExpressionValueIsNotNull(file, "file");
            sb.append(file.getAbsolutePath());
            Pdlog.m3273d("pdmaplist", sb.toString());
            MapInfo mapVersion = getMapVersion(file.getAbsolutePath());
            if (mapVersion != null) {
                arrayList.add(mapVersion);
                Pdlog.m3273d("pdmaplist", "name is " + mapVersion.getMapName());
            }
        }
        Pdlog.m3273d("pdmaplist", "mapInfoList  size is " + arrayList.size());
        return arrayList;
    }

    public final MapInfo getMapVersion(String mapName) {
        ZipInputStream zipInputStream;
        File file = new File(mapName);
        Pdlog.m3273d("pdmaplist", "name is " + file.getName());
        ZipInputStream zipInputStream2 = (ZipInputStream) null;
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(file.getAbsolutePath()));
                try {
                    MapInfo mapInfo = (MapInfo) null;
                    Pdlog.m3273d("pdmaplist", "name is 2");
                    while (true) {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        Pdlog.m3273d("pdmaplist", "name is 3");
                        if (nextEntry == null) {
                            break;
                        }
                        if (Intrinsics.areEqual("config.json", nextEntry.getName())) {
                            byte[] bArr = new byte[1024];
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            while (true) {
                                int read = zipInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            Pdlog.m3273d("pdmaplist", "name is 4");
                            byte[] tmp = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                            MapConfig mapConfig = (MapConfig) new Gson().fromJson(new String(tmp, 0, tmp.length, Charsets.UTF_8), MapConfig.class);
                            String fileNameNoExtension = FileUtils.getFileNameNoExtension(file.getName());
                            Intrinsics.checkExpressionValueIsNotNull(fileNameNoExtension, "FileUtils.getFileNameNoExtension(file.name)");
                            String decodeMapName = StringBase64Kt.decodeMapName(fileNameNoExtension);
                            String str = (MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH + "/") + StringBase64Kt.encodeMapName(decodeMapName);
                            int map_version = mapConfig.getMap_version();
                            byte[] fileMD5 = FileUtils.getFileMD5(file);
                            Intrinsics.checkExpressionValueIsNotNull(fileMD5, "FileUtils.getFileMD5(file)");
                            MapInfo mapInfo2 = new MapInfo(map_version, fileMD5, decodeMapName, str);
                            this.unzipMap = new File(file.getParent(), FileUtils.getFileNameNoExtension(file.getName()));
                            File file2 = this.unzipMap;
                            if (file2 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (file2.exists()) {
                                FileUtils.delete(this.unzipMap);
                            }
                            Pdlog.m3273d("pdmaplist", "name is 5");
                            ZipUtils.unzipFile(file, new File(file.getParent(), FileUtils.getFileNameNoExtension(file.getName())));
                            mapInfo = mapInfo2;
                        } else {
                            zipInputStream.closeEntry();
                        }
                    }
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("zips close mapinfo is ");
                    sb.append(mapInfo != null ? mapInfo.getMapName() : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d("pdmaplist", objArr);
                    zipInputStream.close();
                    return mapInfo;
                } catch (Exception e) {
                    e = e;
                    zipInputStream2 = zipInputStream;
                    Pdlog.m3274e(this.TAG, "check map legal failed: " + e.getMessage());
                    if (zipInputStream2 != null) {
                        zipInputStream2.close();
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    if (zipInputStream != null) {
                        zipInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                zipInputStream = zipInputStream2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
    public void updateMapVersion(String mapName, int version) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Pdlog.m3273d("enDir", "init enDir " + mapName + "  name is " + version);
        String encodeMapName = StringBase64Kt.encodeMapName(mapName);
        File file = new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, encodeMapName);
        File file2 = new File((MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH + "/") + encodeMapName, "config.json");
        if (!file2.exists()) {
            Pdlog.m3273d("enDir", "init enDir " + file.getAbsolutePath() + "  name is " + mapName);
            return;
        }
        CamerConfigHelper.setConfigJsonParams(file2.getAbsolutePath(), version, mapName);
        File[] listFiles = file.listFiles();
        ZipUtils.zipFiles(listFiles != null ? ArraysKt.toList(listFiles) : null, new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, encodeMapName + ".pdmap"));
        CommandUtils.INSTANCE.executeCommand(InvokeServiceData.CALL_TYPE_SYNC);
        Pdlog.m3273d("enDir", "init enDir " + file2.getAbsolutePath() + " and file dir name is " + file.getName());
    }

    private final void saveMap(String mapName, String temMapdataRoot, Destination initPoint) {
        File file = new File(temMapdataRoot, MapFilePathConfig.LOCATE_MAP_DATA_NAME);
        File file2 = new File(temMapdataRoot, "optemap.png");
        File file3 = new File(temMapdataRoot, "optemap.pgm");
        File file4 = new File(temMapdataRoot, "optemap.yaml");
        File file5 = new File(temMapdataRoot, MapFilePathConfig.MERGE_MAP);
        try {
            String encodeMapName = StringBase64Kt.encodeMapName(mapName);
            File file6 = new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, encodeMapName);
            if (file6.exists() && !this.extendFlag) {
                FileUtils.deleteDir(file6);
                file6.mkdir();
            }
            if (!file6.exists()) {
                file6.mkdir();
            }
            FileUtils.copyFile(file, new File(file6, MapFilePathConfig.LOCATE_MAP_DATA_NAME));
            FileUtils.copyFile(file2, new File(file6, "optemap.png"));
            FileUtils.copyFile(file3, new File(file6, "optemap.pgm"));
            FileUtils.copyFile(file4, new File(file6, "optemap.yaml"));
            FileUtils.copyFile(file5, new File(file6, MapFilePathConfig.MERGE_MAP));
            String json = new Gson().toJson(initPoint);
            Pdlog.m3273d(this.TAG, "mapInitPoint " + json);
            org.apache.commons.io.FileUtils.writeStringToFile(new File(file6, MapFilePathConfig.INIT_POINT_JSON), json, Charsets.UTF_8);
            CamerConfigHelper.createScheduleJson(this.mContext, encodeMapName);
            String absolutePath = file6.getAbsolutePath();
            Intrinsics.checkExpressionValueIsNotNull(absolutePath, "enDir.absolutePath");
            CameraType cameraType = CamerConfigHelper.cameraType;
            Intrinsics.checkExpressionValueIsNotNull(cameraType, "CamerConfigHelper.cameraType");
            createMapConfig$default(this, absolutePath, mapName, true, 0, 3, cameraType, 8, null);
            File[] listFiles = file6.listFiles();
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "enDir.listFiles()");
            ZipUtils.zipFiles(ArraysKt.toList(listFiles), new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, encodeMapName + ".pdmap"));
            CommandUtils.INSTANCE.executeCommand(InvokeServiceData.CALL_TYPE_SYNC);
            this.extendFlag = false;
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    static /* synthetic */ void createMapConfig$default(LocateMappingInterfaceImpl locateMappingInterfaceImpl, String str, String str2, boolean z, int i, int i2, CameraType cameraType, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i = 1;
        }
        int i4 = i;
        if ((i3 & 16) != 0) {
            i2 = 3;
        }
        int i5 = i2;
        if ((i3 & 32) != 0) {
            cameraType = CameraType.MARKER_CAMERA;
        }
        locateMappingInterfaceImpl.createMapConfig(str, str2, z, i4, i5, cameraType);
    }

    private final void createMapConfig(String dirPath, String name, boolean newMap, int version, int sensor, CameraType cameraType) {
        ObjectNode tryReadAsJSON = CamerConfigHelper.tryReadAsJSON();
        int asInt = tryReadAsJSON.path(ConfigJson.IMAGE_WIDTH).asInt();
        int asInt2 = tryReadAsJSON.path(ConfigJson.IMAGE_HEIGHT).asInt();
        int asInt3 = tryReadAsJSON.path(ConfigJson.IMAGE_FPS).asInt();
        int asInt4 = tryReadAsJSON.path("exposure_time").asInt();
        Pdlog.m3273d(this.TAG, "create map config with autoexp status " + this.enableAutoExposure + " with new map " + newMap);
        Config config = new Config(version, name, asInt, asInt2, asInt3, asInt4, newMap, false, newMap ? this.enableAutoExposure : false, sensor, cameraType == CameraType.MARKER_CAMERA ? 0 : 1);
        Pdlog.m3273d(this.TAG, "createMapConfig " + config);
        File file = new File(dirPath, "config.json");
        String writeValueAsString = this.objectMapper.writeValueAsString(config);
        Intrinsics.checkExpressionValueIsNotNull(writeValueAsString, "objectMapper.writeValueAsString(mapConfig)");
        FilesKt.writeText$default(file, writeValueAsString, null, 2, null);
    }
}
