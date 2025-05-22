package com.pudutech.mirsdk.mircore.mapareadetection;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: MapAreaDetection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b3\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005J\"\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005J(\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00070\nJ4\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u000b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000bH\u0082 ¢\u0006\u0002\u0010\u001fJ\"\u0010 \u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070\u0005J(\u0010!\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\nJ(\u0010\"\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00070\nJ(\u0010#\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\nJ!\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0082 J\u0019\u0010'\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u000bH\u0082 J\u0016\u0010(\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u000bJ!\u0010)\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0082 J&\u0010*\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u000bJ!\u0010,\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0082 J\t\u0010-\u001a\u00020\u0007H\u0086 J\t\u0010.\u001a\u00020\u0007H\u0082 J\t\u0010/\u001a\u00020\u0007H\u0082 J\t\u00100\u001a\u00020\u0007H\u0082 J\t\u00101\u001a\u00020\u0007H\u0082 J\t\u00102\u001a\u00020\u0007H\u0082 J\t\u00103\u001a\u00020\u0007H\u0082 J\t\u00104\u001a\u00020\u0007H\u0082 J\u0011\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u0014H\u0082 J?\u00107\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u000b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u00108\u001a\u0004\u0018\u00010\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u00109J\u000e\u0010:\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010;\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010<\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010=\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010>\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010?\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010@\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010A\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u0014J&\u0010B\u001a\u00020\u00072\u0006\u0010C\u001a\u00020\u000b2\u0006\u0010D\u001a\u00020\u000b2\u0006\u0010E\u001a\u00020\u000f2\u0006\u0010F\u001a\u00020\u000fJ)\u0010G\u001a\u00020\u00072\u0006\u0010C\u001a\u00020\u000b2\u0006\u0010D\u001a\u00020\u000b2\u0006\u0010E\u001a\u00020\u000f2\u0006\u0010F\u001a\u00020\u000fH\u0082 J\t\u0010H\u001a\u00020\u0007H\u0082 J\t\u0010I\u001a\u00020\u0007H\u0082 J\t\u0010J\u001a\u00020\u0007H\u0082 J\t\u0010K\u001a\u00020\u0007H\u0082 J\t\u0010L\u001a\u00020\u0007H\u0082 J\t\u0010M\u001a\u00020\u0007H\u0082 J\t\u0010N\u001a\u00020\u0007H\u0082 J\u000e\u0010O\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u0006J\u000e\u0010Q\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u0006J\u0016\u0010R\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010S\u001a\u00020\u00072\u0006\u0010T\u001a\u00020\rJ\u0016\u0010U\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u000fJ\u0016\u0010V\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u000bJ\u0011\u0010W\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&H\u0086 J\u0016\u0010X\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u000fR \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\t\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00070\n0\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u000e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\n0\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0010\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00070\n0\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0011\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\n0\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Y"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mapareadetection/MapAreaDetection;", "", "()V", "dangerousZoneListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function1;", "", "", "elevatorZoneListener", "exemptZoneListener", "Lkotlin/Function2;", "", "mapAreaInfoAddressListener", "", "noDetourListener", "", "rgbdFunLimitListener", "speedLimitListener", "addDangerousZoneListener", "name", "", "callback", "addElevatorZoneListener", "addExemptZoneListener", "addMapAreaData", "type", "vertices", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "maxSpeed", ES6Iterator.VALUE_PROPERTY, "(I[Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;DI)V", "addMapAreaInfoAddresListeners", "addNoDetourListener", "addRGBDFunLimitListener", "addSpeedLimitListener", "getDoubleProperty", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getInside", "getInsideStatus", "getIntProperty", "getProperty", "dataType", "getStringProperty", "initialize", "installDangerousZoneListener", "installElevatorZoneListener", "installExemptZoneListener", "installMapAreaInfoAddressListener", "installNoDetourListener", "installRGBDFunLimitListener", "installSpeedLimitListener", "loadMap", "path", "receiveAreaData", "id", "(I[Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;)V", "removeDangerousZoneListener", "removeElevatorZoneListener", "removeExemptZoneListener", "removeMapAreaInfoListener", "removeNoDetourListener", "removeRGBDFunLimitListener", "removeSpeedLimitListener", "setMap", "setMapConfig", "imageWidth", "imageHeight", "originX", "originY", "setMapInfo", "uninstallDangerousZoneListener", "uninstallElevatorListener", "uninstallExemptZoneListener", "uninstallMapAreInfoAddressListener", "uninstallNoDetourListener", "uninstallRGBDFunLimitListener", "uninstallSpeedLimitListener", "updateDangerousZoneInfo", "inside", "updateElevatorZoneInfo", "updateExemptZoneInfo", "updateMapAreaInfoAddress", "address", "updateNoDetourInfo", "updateRGBDFunLimitInfo", "updateRobotPose", "updateSpeedLimitInfo", "MapAreaDetection_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MapAreaDetection {
    public static final MapAreaDetection INSTANCE = new MapAreaDetection();
    private static ThreadSafeListener<Function1<Boolean, Unit>> dangerousZoneListener;
    private static ThreadSafeListener<Function1<Boolean, Unit>> elevatorZoneListener;
    private static ThreadSafeListener<Function2<Boolean, Integer, Unit>> exemptZoneListener;
    private static ThreadSafeListener<Function1<Long, Unit>> mapAreaInfoAddressListener;
    private static ThreadSafeListener<Function2<Boolean, Double, Unit>> noDetourListener;
    private static ThreadSafeListener<Function2<Boolean, Integer, Unit>> rgbdFunLimitListener;
    private static ThreadSafeListener<Function2<Boolean, Double, Unit>> speedLimitListener;

    private final native void addMapAreaData(int type, Vector2d[] vertices, double maxSpeed, int value);

    private final native double getDoubleProperty(Vector3d pose, int type, String name);

    private final native boolean getInside(Vector3d pose, int type);

    private final native int getIntProperty(Vector3d pose, int type, String name);

    private final native String getStringProperty(Vector3d pose, int type, String name);

    private final native void installDangerousZoneListener();

    private final native void installElevatorZoneListener();

    private final native void installExemptZoneListener();

    private final native void installMapAreaInfoAddressListener();

    private final native void installNoDetourListener();

    private final native void installRGBDFunLimitListener();

    private final native void installSpeedLimitListener();

    private final native boolean loadMap(String path);

    private final native void setMapInfo(int imageWidth, int imageHeight, double originX, double originY);

    private final native void uninstallDangerousZoneListener();

    private final native void uninstallElevatorListener();

    private final native void uninstallExemptZoneListener();

    private final native void uninstallMapAreInfoAddressListener();

    private final native void uninstallNoDetourListener();

    private final native void uninstallRGBDFunLimitListener();

    private final native void uninstallSpeedLimitListener();

    public final native void initialize();

    public final native void updateRobotPose(Vector3d pose);

    static {
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "load library: map_area_detection");
        System.loadLibrary("map_area_detection");
        mapAreaInfoAddressListener = new ThreadSafeListener<>();
        speedLimitListener = new ThreadSafeListener<>();
        noDetourListener = new ThreadSafeListener<>();
        rgbdFunLimitListener = new ThreadSafeListener<>();
        elevatorZoneListener = new ThreadSafeListener<>();
        exemptZoneListener = new ThreadSafeListener<>();
        dangerousZoneListener = new ThreadSafeListener<>();
    }

    private MapAreaDetection() {
    }

    public final boolean setMap(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "setmap, path: " + path);
        return loadMap(path);
    }

    public final void setMapConfig(int imageWidth, int imageHeight, double originX, double originY) {
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "setMapConfig, height: " + imageHeight + " width:" + imageWidth + ", originX:" + originX + ", originY:" + originY);
        setMapInfo(imageWidth, imageHeight, originX, originY);
    }

    public final void receiveAreaData(int type, Vector2d[] vertices, String id, Double maxSpeed, Integer value) {
        Intrinsics.checkParameterIsNotNull(vertices, "vertices");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "receiveAreaData, type: " + type + ", maxSpeed: " + maxSpeed + ", value: " + value);
        addMapAreaData(type, vertices, maxSpeed != null ? maxSpeed.doubleValue() : 0.0d, value != null ? value.intValue() : 0);
    }

    public final void addMapAreaInfoAddresListeners(String name, Function1<? super Long, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "addMapAreaInfoAddresListeners, name: " + name);
        if (mapAreaInfoAddressListener.counts() == 0) {
            installMapAreaInfoAddressListener();
        }
        mapAreaInfoAddressListener.add(name, callback);
    }

    public final void removeMapAreaInfoListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "removeMapAreaInfoListener, name: " + name);
        mapAreaInfoAddressListener.remove(name);
        if (mapAreaInfoAddressListener.counts() == 0) {
            uninstallMapAreInfoAddressListener();
        }
    }

    public final void addSpeedLimitListener(String name, Function2<? super Boolean, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "addSpeedLimitListener, name: " + name);
        if (speedLimitListener.counts() == 0) {
            installSpeedLimitListener();
        }
        speedLimitListener.add(name, callback);
    }

    public final void removeSpeedLimitListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "removeSpeedLimitListener, name: " + name);
        speedLimitListener.remove(name);
        if (speedLimitListener.counts() == 0) {
            uninstallSpeedLimitListener();
        }
    }

    public final void addNoDetourListener(String name, Function2<? super Boolean, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "addNoDetourListener, name: " + name);
        if (noDetourListener.counts() == 0) {
            installNoDetourListener();
        }
        noDetourListener.add(name, callback);
    }

    public final void removeNoDetourListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "removeNoDetourListener, name: " + name);
        noDetourListener.remove(name);
        if (noDetourListener.counts() == 0) {
            uninstallNoDetourListener();
        }
    }

    public final void addRGBDFunLimitListener(String name, Function2<? super Boolean, ? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "addNoDetourListener, name: " + name);
        if (rgbdFunLimitListener.counts() == 0) {
            installRGBDFunLimitListener();
        }
        rgbdFunLimitListener.add(name, callback);
    }

    public final void removeRGBDFunLimitListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "removeRGBDFunLimitListener, name: " + name);
        rgbdFunLimitListener.remove(name);
        if (rgbdFunLimitListener.counts() == 0) {
            uninstallRGBDFunLimitListener();
        }
    }

    public final void addElevatorZoneListener(String name, Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "addElevatorZoneListener, name: " + name);
        if (elevatorZoneListener.counts() == 0) {
            installElevatorZoneListener();
        }
        elevatorZoneListener.add(name, callback);
    }

    public final void removeElevatorZoneListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "removeElevatorZoneListener, name: " + name);
        elevatorZoneListener.remove(name);
        if (elevatorZoneListener.counts() == 0) {
            uninstallElevatorListener();
        }
    }

    public final void addExemptZoneListener(String name, Function2<? super Boolean, ? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "addExemptZoneListener, name: " + name);
        if (exemptZoneListener.counts() == 0) {
            installExemptZoneListener();
        }
        exemptZoneListener.add(name, callback);
    }

    public final void removeExemptZoneListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "removeExemptZoneListener, name: " + name);
        exemptZoneListener.remove(name);
        if (exemptZoneListener.counts() == 0) {
            uninstallExemptZoneListener();
        }
    }

    public final void addDangerousZoneListener(String name, Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "addDangerousZoneListener, name: " + name);
        if (dangerousZoneListener.counts() == 0) {
            installDangerousZoneListener();
        }
        dangerousZoneListener.add(name, callback);
    }

    public final void removeDangerousZoneListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "removeDangerousZoneListener, name: " + name);
        dangerousZoneListener.remove(name);
        if (dangerousZoneListener.counts() == 0) {
            uninstallDangerousZoneListener();
        }
    }

    public final boolean getInsideStatus(Vector3d pose, int type) {
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "getInsideStatus, pose: " + pose.getX() + ' ' + pose.getY() + ' ' + pose.getZ() + " , type: " + type);
        return getInside(pose, type);
    }

    public final Object getProperty(Vector3d pose, int type, String name, int dataType) {
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "getProperty, pose: " + pose.getX() + ' ' + pose.getY() + ' ' + pose.getZ() + " , type: " + type + " , name: " + name + ", dataType: " + dataType);
        if (dataType == 1) {
            return getStringProperty(pose, type, name);
        }
        if (dataType == 2) {
            return Double.valueOf(getDoubleProperty(pose, type, name));
        }
        if (dataType == 3) {
            return Integer.valueOf(getIntProperty(pose, type, name));
        }
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "unknown data type: " + dataType);
        return 0;
    }

    public final void updateMapAreaInfoAddress(final long address) {
        Pdlog.m3273d(MapAreaDetectionKt.getTAG(), "updateMapAreaInfoAddress");
        mapAreaInfoAddressListener.notify(new Function2<Function1<? super Long, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mapareadetection.MapAreaDetection$updateMapAreaInfoAddress$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Long, ? extends Unit> function1, String str) {
                invoke2((Function1<? super Long, Unit>) function1, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Long, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Long.valueOf(address));
            }
        });
    }

    public final void updateSpeedLimitInfo(final boolean inside, final double maxSpeed) {
        speedLimitListener.notify(new Function2<Function2<? super Boolean, ? super Double, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mapareadetection.MapAreaDetection$updateSpeedLimitInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Boolean, ? super Double, ? extends Unit> function2, String str) {
                invoke2((Function2<? super Boolean, ? super Double, Unit>) function2, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super Boolean, ? super Double, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Boolean.valueOf(inside), Double.valueOf(maxSpeed));
            }
        });
    }

    public final void updateNoDetourInfo(final boolean inside, final double maxSpeed) {
        noDetourListener.notify(new Function2<Function2<? super Boolean, ? super Double, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mapareadetection.MapAreaDetection$updateNoDetourInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Boolean, ? super Double, ? extends Unit> function2, String str) {
                invoke2((Function2<? super Boolean, ? super Double, Unit>) function2, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super Boolean, ? super Double, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Boolean.valueOf(inside), Double.valueOf(maxSpeed));
            }
        });
    }

    public final void updateRGBDFunLimitInfo(final boolean inside, final int value) {
        rgbdFunLimitListener.notify(new Function2<Function2<? super Boolean, ? super Integer, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mapareadetection.MapAreaDetection$updateRGBDFunLimitInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Boolean, ? super Integer, ? extends Unit> function2, String str) {
                invoke2((Function2<? super Boolean, ? super Integer, Unit>) function2, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super Boolean, ? super Integer, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Boolean.valueOf(inside), Integer.valueOf(value));
            }
        });
    }

    public final void updateElevatorZoneInfo(final boolean inside) {
        elevatorZoneListener.notify(new Function2<Function1<? super Boolean, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mapareadetection.MapAreaDetection$updateElevatorZoneInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Boolean, ? extends Unit> function1, String str) {
                invoke2((Function1<? super Boolean, Unit>) function1, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Boolean, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Boolean.valueOf(inside));
            }
        });
    }

    public final void updateExemptZoneInfo(final boolean inside, final int value) {
        exemptZoneListener.notify(new Function2<Function2<? super Boolean, ? super Integer, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mapareadetection.MapAreaDetection$updateExemptZoneInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Boolean, ? super Integer, ? extends Unit> function2, String str) {
                invoke2((Function2<? super Boolean, ? super Integer, Unit>) function2, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super Boolean, ? super Integer, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Boolean.valueOf(inside), Integer.valueOf(value));
            }
        });
    }

    public final void updateDangerousZoneInfo(final boolean inside) {
        dangerousZoneListener.notify(new Function2<Function1<? super Boolean, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mapareadetection.MapAreaDetection$updateDangerousZoneInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Boolean, ? extends Unit> function1, String str) {
                invoke2((Function1<? super Boolean, Unit>) function1, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Boolean, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Boolean.valueOf(inside));
            }
        });
    }
}
