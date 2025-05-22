package com.pudutech.mirsdk.service;

import android.content.Context;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.mapify.HardwareExceptListener;
import com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface;
import com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface;
import com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl;
import com.pudutech.mirsdk.impl.TopoMappingIntefaceImpl;
import com.pudutech.mirsdk.map.MapConfig;
import com.pudutech.mirsdk.map.MapDecode;
import com.pudutech.mirsdk.map.elements.Source;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
import com.pudutech.mirsdk.mircore.MirMappingCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitStep;
import com.pudutech.mirsdk.mirhardware.MappingHardware;
import com.pudutech.schedulerlib.utils.CommandUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: MapifyService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\fH\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0016J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0016\u0010 \u001a\u00020\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0002J\u0016\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u001fJ\u0016\u0010'\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u001fJ\b\u0010(\u001a\u00020\u0016H\u0016J\u0006\u0010)\u001a\u00020\u0016J\u0006\u0010*\u001a\u00020\u0016J\b\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020.H\u0016J\u000e\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u00020\u0006J\"\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u00020\u0004H\u0002J\u0010\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u0004H\u0002J&\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020<2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0002J\b\u0010=\u001a\u00020\u0016H\u0016J\u0010\u0010>\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004H\u0016J\b\u0010?\u001a\u00020\u0016H\u0016J\u0012\u0010@\u001a\u00020\u001b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000¨\u0006A"}, m3961d2 = {"Lcom/pudutech/mirsdk/service/MapifyInterfaceStub;", "Lcom/pudutech/mirsdk/aidl/mapify/MappingFunctionInterface$Stub;", "()V", "TAG", "", "context", "Landroid/content/Context;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirMappingCoreInterface;", "hardwareExceptListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/mapify/HardwareExceptListener;", "locateMappingInterface", "Lcom/pudutech/mirsdk/impl/LocateMappingInterfaceImpl;", "mapExtractPath", "mapRootPath", "mappingHardware", "Lcom/pudutech/mirsdk/mirhardware/MappingHardware;", "topoMappingInteface", "Lcom/pudutech/mirsdk/impl/TopoMappingIntefaceImpl;", "addHardWareListener", "", "p0", "p1", "addMappingSensorListener", "checkLegalMap", "", "pdmap", "checkMapPackNotLegal", "file", "Ljava/io/File;", "checkNoRedundancyDestination", "destinationTable", "", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "copyDirToDir", "srcFile", "destFile", "copyfile", "defaultResetRobot", MqttServiceConstants.DISCONNECT_ACTION, "disconnectSubService", "getLocatMappingInterface", "Lcom/pudutech/mirsdk/aidl/mapify/LocateMappingInterface;", "getTopoMappingInteface", "Lcom/pudutech/mirsdk/aidl/mapify/TopoMappingInteface;", "init", "_context", "initStepNotify", "step", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "stepState", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "description", "isBase64", "str", "loadDestinations", "floor", MapElement.Key.MAP, "Lcom/pudutech/mirsdk/map/MapDecode;", "open", "removeHardwareListener", "removeMappingSensorListener", "saveMapFromPlatform", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapifyInterfaceStub extends MappingFunctionInterface.Stub {
    private static Context context = null;
    private static LocateMappingInterfaceImpl locateMappingInterface = null;
    private static TopoMappingIntefaceImpl topoMappingInteface;
    public static final MapifyInterfaceStub INSTANCE = new MapifyInterfaceStub();
    private static final String mapRootPath = "/sdcard/pudu/map";
    private static final String mapExtractPath = mapRootPath + "/extract";
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final AIDLConnection<MirMappingCoreInterface> coreService = new AIDLConnection<>("com.pudutech.mirsdk.mircore.MirMappingCoreService", MapifyInterfaceStub$coreService$1.INSTANCE, null, 4, null);
    private static ThreadSafeListener<HardwareExceptListener> hardwareExceptListener = new ThreadSafeListener<>();
    private static final MappingHardware mappingHardware = new MappingHardware();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MappingCoreInitStep.values().length];

        static {
            $EnumSwitchMapping$0[MappingCoreInitStep.Finish.ordinal()] = 1;
        }
    }

    private final void initStepNotify(InitStep step, StepState stepState, String description) {
    }

    private MapifyInterfaceStub() {
    }

    public final void init(Context _context) {
        Intrinsics.checkParameterIsNotNull(_context, "_context");
        context = _context;
        File file = new File(MapFilePathConfig.STATIC_MAP_DEPTH);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            Pdlog.m3273d(TAG, "staticMapstest  MapServiceConnection init exists");
        }
        File file2 = new File("/sdcard/pudu/static_map/map");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        locateMappingInterface = new LocateMappingInterfaceImpl(coreService, mappingHardware, _context);
        topoMappingInteface = new TopoMappingIntefaceImpl(coreService);
        Pdlog.m3273d(TAG, "MapifyService  MapServiceConnection init ");
        mappingHardware.getErrorEvent().plusAssign(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.service.MapifyInterfaceStub$init$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final String errors) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(errors, "errors");
                MapifyInterfaceStub mapifyInterfaceStub = MapifyInterfaceStub.INSTANCE;
                threadSafeListener = MapifyInterfaceStub.hardwareExceptListener;
                threadSafeListener.notify(new Function2<HardwareExceptListener, String, Unit>() { // from class: com.pudutech.mirsdk.service.MapifyInterfaceStub$init$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(HardwareExceptListener hardwareExceptListener2, String str) {
                        invoke2(hardwareExceptListener2, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(HardwareExceptListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onReceiveHardWareInfo(errors);
                    }
                });
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
    public void addMappingSensorListener() {
        Pdlog.m3273d(TAG, "sensor addMappingSensorListener");
        MirMappingCoreInterface mirMappingCoreInterface = coreService.getInterface();
        if (mirMappingCoreInterface != null) {
            mirMappingCoreInterface.addMappingSensorListener();
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
    public void removeMappingSensorListener() {
        Pdlog.m3273d(TAG, "sensor removeMappingSensorListener");
        MirMappingCoreInterface mirMappingCoreInterface = coreService.getInterface();
        if (mirMappingCoreInterface != null) {
            mirMappingCoreInterface.removeMappingSensorListener();
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState] */
    /* JADX WARN: Type inference failed for: r3v1, types: [T, com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitStep] */
    @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
    public void open() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = MappingCoreInitState.Running;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = (MappingCoreInitStep) 0;
        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = "";
        disconnectSubService();
        CommandUtils.INSTANCE.executeCommand(InvokeServiceData.CALL_TYPE_SYNC);
        CommandUtils.INSTANCE.executeCommand("echo 1 > /proc/sys/vm/drop_caches");
        CommandUtils.INSTANCE.executeCommand("echo 2 > /proc/sys/vm/drop_caches");
        CommandUtils.INSTANCE.executeCommand("echo 3 > /proc/sys/vm/drop_caches");
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new MapifyInterfaceStub$open$1(objectRef, objectRef3, objectRef2, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
    public void addHardWareListener(String p0, HardwareExceptListener p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        hardwareExceptListener.add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
    public boolean checkLegalMap(String pdmap) {
        File file = new File(pdmap);
        Pdlog.m3273d(TAG, "path is " + file.getAbsolutePath() + "  name is " + file.getName());
        String name = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
        isBase64(StringsKt.removeSuffix(name, (CharSequence) ".pdmap"));
        return checkMapPackNotLegal(file);
    }

    private final boolean isBase64(String str) {
        return Pattern.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$", str);
    }

    private final boolean checkNoRedundancyDestination(List<Destination> destinationTable) {
        HashSet hashSet = new HashSet();
        for (Destination destination : destinationTable) {
            if (hashSet.contains(destination.getName())) {
                Pdlog.m3277w(TAG, "destination " + destination.getName() + " has existed in destination list 168");
                return false;
            }
            hashSet.add(destination.getName());
        }
        return true;
    }

    private final void loadDestinations(String floor, MapDecode map, List<Destination> destinationTable) {
        Iterator<Source> it = map.getSource().iterator();
        while (it.hasNext()) {
            Source next = it.next();
            Pdlog.m3273d(TAG, "chk source id " + next.getId());
            destinationTable.add(new Destination(next.getId(), floor, next.getPosition()[0], next.getPosition()[1], next.getDir(), next.getDoubleDir(), next.getMode(), next.getGroup()));
        }
        Pdlog.m3273d(TAG, "destinationTable size:" + destinationTable.size() + " 193");
    }

    private final boolean checkMapPackNotLegal(File file) {
        ZipInputStream zipInputStream;
        boolean isEmpty;
        List list;
        List list2;
        String str;
        String str2 = "path is ";
        String str3 = "ATLAS_DATA";
        ZipInputStream zipInputStream2 = (ZipInputStream) null;
        int i = 1;
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(file.getAbsolutePath()));
                try {
                    Pdlog.m3273d(TAG, "path is " + file.getAbsolutePath() + "  1 name is " + file.getName());
                    List mutableListOf = CollectionsKt.mutableListOf("optemap.yaml", "optemap.pgm", "ATLAS_DATA");
                    List mutableListOf2 = CollectionsKt.mutableListOf(MapFilePathConfig.LOCATE_MAP_DATA_NAME, "ATLAS_DATA");
                    List mutableListOf3 = CollectionsKt.mutableListOf("optemap.yaml", "optemap.pgm", "ATLAS_DATA");
                    List mutableListOf4 = CollectionsKt.mutableListOf(MapFilePathConfig.SLAM_MAP_DATA_NAME);
                    int i2 = 0;
                    int i3 = 0;
                    while (true) {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        String str4 = TAG;
                        Object[] objArr = new Object[i];
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        String str5 = str2;
                        sb.append(file.getAbsolutePath());
                        sb.append(" 2 name is ");
                        sb.append(file.getName());
                        objArr[0] = sb.toString();
                        Pdlog.m3273d(str4, objArr);
                        if (nextEntry == null) {
                            if (i2 == 0) {
                                Pdlog.m3273d(TAG, "filenumber is Empty " + i2);
                                zipInputStream.close();
                                return false;
                            }
                            if (i3 == 0 || i3 == 1) {
                                String str6 = TAG;
                                Object[] objArr2 = new Object[1];
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("markerFileNeed.isNotEmpty() ");
                                sb2.append(!mutableListOf2.isEmpty());
                                objArr2[0] = sb2.toString();
                                Pdlog.m3273d(str6, objArr2);
                                isEmpty = mutableListOf2.isEmpty();
                            } else if (i3 == 2) {
                                String str7 = TAG;
                                Object[] objArr3 = new Object[1];
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("markerFileNeed.isNotEmpty() ");
                                sb3.append(!mutableListOf.isEmpty());
                                objArr3[0] = sb3.toString();
                                Pdlog.m3273d(str7, objArr3);
                                isEmpty = mutableListOf.isEmpty();
                            } else if (i3 == 3) {
                                String str8 = TAG;
                                Object[] objArr4 = new Object[1];
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("markerFileNeed.isNotEmpty() ");
                                sb4.append(!mutableListOf3.isEmpty());
                                objArr4[0] = sb4.toString();
                                Pdlog.m3273d(str8, objArr4);
                                isEmpty = mutableListOf3.isEmpty();
                            } else if (i3 != 4) {
                                Pdlog.m3277w(TAG, "error sensor " + i3);
                                isEmpty = true;
                            } else {
                                isEmpty = mutableListOf4.isEmpty();
                            }
                            Pdlog.m3273d(TAG, "fileListIsNotEmpty is " + isEmpty);
                            zipInputStream.close();
                            return isEmpty;
                        }
                        i2++;
                        Pdlog.m3273d(TAG, "file number is " + i2);
                        if (mutableListOf.contains(nextEntry.getName())) {
                            mutableListOf.remove(nextEntry.getName());
                        }
                        if (mutableListOf2.contains(nextEntry.getName())) {
                            mutableListOf2.remove(nextEntry.getName());
                        }
                        if (mutableListOf3.contains(nextEntry.getName())) {
                            mutableListOf3.remove(nextEntry.getName());
                        }
                        if (mutableListOf4.contains(nextEntry.getName())) {
                            mutableListOf4.remove(nextEntry.getName());
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
                            byte[] tmp = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                            i3 = ((MapConfig) new Gson().fromJson(new String(tmp, 0, tmp.length, Charsets.UTF_8), MapConfig.class)).getSensor();
                            str2 = str5;
                        } else {
                            if (Intrinsics.areEqual(nextEntry.getName(), str3)) {
                                ArrayList arrayList = new ArrayList();
                                String str9 = TAG;
                                StringBuilder sb5 = new StringBuilder();
                                sb5.append("destinationTable size is ");
                                list = mutableListOf3;
                                sb5.append(arrayList.size());
                                sb5.append(" 253 ");
                                Pdlog.m3273d(str9, sb5.toString());
                                MapDecode mapDecode = new MapDecode();
                                byte[] bArr2 = new byte[1024];
                                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                while (true) {
                                    int read2 = zipInputStream.read(bArr2);
                                    if (read2 <= 0) {
                                        break;
                                    }
                                    List list3 = mutableListOf4;
                                    String str10 = str3;
                                    byteArrayOutputStream2.write(bArr2, 0, read2);
                                    mutableListOf4 = list3;
                                    str3 = str10;
                                }
                                byte[] tmp2 = byteArrayOutputStream2.toByteArray();
                                StringBuilder sb6 = new StringBuilder();
                                sb6.append("");
                                Intrinsics.checkExpressionValueIsNotNull(tmp2, "tmp");
                                list2 = mutableListOf4;
                                str = str3;
                                sb6.append(new String(tmp2, 0, tmp2.length, Charsets.UTF_8));
                                mapDecode.decode(sb6.toString());
                                loadDestinations("", mapDecode, arrayList);
                                Pdlog.m3273d(TAG, "destinationTable size is " + arrayList.size() + " 268");
                                if (!checkNoRedundancyDestination(arrayList)) {
                                    zipInputStream.close();
                                    return false;
                                }
                            } else {
                                list = mutableListOf3;
                                list2 = mutableListOf4;
                                str = str3;
                            }
                            zipInputStream.closeEntry();
                            str2 = str5;
                            mutableListOf4 = list2;
                            mutableListOf3 = list;
                            str3 = str;
                        }
                        i = 1;
                    }
                } catch (Exception e) {
                    e = e;
                    zipInputStream2 = zipInputStream;
                    Pdlog.m3274e(TAG, "check map legal failed: " + e.getMessage());
                    if (zipInputStream2 != null) {
                        zipInputStream2.close();
                    }
                    return false;
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

    static /* synthetic */ void initStepNotify$default(MapifyInterfaceStub mapifyInterfaceStub, InitStep initStep, StepState stepState, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = "";
        }
        mapifyInterfaceStub.initStepNotify(initStep, stepState, str);
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
    public TopoMappingInteface getTopoMappingInteface() {
        TopoMappingIntefaceImpl topoMappingIntefaceImpl = topoMappingInteface;
        if (topoMappingIntefaceImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topoMappingInteface");
        }
        return topoMappingIntefaceImpl;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
    public boolean saveMapFromPlatform(String p0) {
        return copyDirToDir(new File(p0), new File("/sdcard/pudu/map"));
    }

    public final boolean copyDirToDir(File srcFile, File destFile) {
        Intrinsics.checkParameterIsNotNull(srcFile, "srcFile");
        Intrinsics.checkParameterIsNotNull(destFile, "destFile");
        for (File f : srcFile.listFiles()) {
            String absolutePath = destFile.getAbsolutePath();
            Intrinsics.checkExpressionValueIsNotNull(f, "f");
            File file = new File(absolutePath, f.getName());
            if (f.isFile()) {
                System.out.println((Object) (f.getAbsolutePath() + "-->" + file.getAbsolutePath()));
                copyfile(f, file);
            } else {
                if (!file.exists() && !file.mkdir()) {
                    return true;
                }
                copyDirToDir(f, file);
            }
        }
        return true;
    }

    public final void copyfile(File srcFile, File destFile) {
        Intrinsics.checkParameterIsNotNull(srcFile, "srcFile");
        Intrinsics.checkParameterIsNotNull(destFile, "destFile");
        FileInputStream fileInputStream = new FileInputStream(srcFile);
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                bufferedOutputStream.write(bArr, 0, read);
            } else {
                fileInputStream.close();
                fileOutputStream.close();
                return;
            }
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
    public LocateMappingInterface getLocatMappingInterface() {
        LocateMappingInterfaceImpl locateMappingInterfaceImpl = locateMappingInterface;
        if (locateMappingInterfaceImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateMappingInterface");
        }
        return locateMappingInterfaceImpl;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
    public void removeHardwareListener(String p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        hardwareExceptListener.remove(p0);
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
    public void defaultResetRobot() {
        File file = new File(mapRootPath);
        if (file.isDirectory()) {
            FilesKt.deleteRecursively(file);
        }
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(mapExtractPath);
        if (file2.exists()) {
            File[] listFiles = file2.listFiles();
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "extractFilesPath.listFiles()");
            if (!(listFiles.length == 0)) {
                if (file2.isDirectory()) {
                    FilesKt.deleteRecursively(file2);
                } else {
                    file2.delete();
                }
            }
        }
        CamerConfigHelper.overrideCameraconfigFile(context);
        Pdlog.m3273d(TAG, "delete the history map");
    }

    public final void disconnectSubService() {
        try {
            if (coreService.getInterface() != null) {
                AIDLConnection<MirMappingCoreInterface> aIDLConnection = coreService;
                Context context2 = context;
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                aIDLConnection.disconnect(context2);
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "disconnect MirMappingCoreService service exception:" + Log.getStackTraceString(e));
        }
        try {
            if (mappingHardware.getInterface() != null) {
                MappingHardware mappingHardware2 = mappingHardware;
                Context context3 = context;
                if (context3 == null) {
                    Intrinsics.throwNpe();
                }
                mappingHardware2.disconnect(context3);
            }
        } catch (Exception e2) {
            Pdlog.m3274e(TAG, "disconnect hardware service exception:" + Log.getStackTraceString(e2));
        }
    }

    public final void disconnect() {
        disconnectSubService();
        context = (Context) null;
    }
}
