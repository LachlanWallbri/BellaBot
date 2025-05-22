package com.pudutech.mirsdk.mircore;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.aidl.CliffInfoListener;
import com.pudutech.mirsdk.mircore.aidl.ReflectorInfoListener;
import com.pudutech.mirsdk.mircore.coreparcel.AIDLGitHash;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import com.pudutech.mirsdk.mircore.mirnavigation.Navigation;
import com.pudutech.mirsdk.mircore.mirperception.Perception;
import com.pudutech.mirsdk.mircore.mirschedulemaster.ScheduleMaster;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirCoreBinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0016J\u001c\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u0011H\u0016J\u001c\u0010\u0012\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u0013H\u0016J\u001c\u0010\u0014\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u0015H\u0016J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0004H\u0016J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\n\u0010 \u001a\u0004\u0018\u00010!H\u0016J\"\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020#H\u0016J\n\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020\u001aH\u0016J0\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00042\u000e\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001002\u0006\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u00020\u001aH\u0016J(\u00104\u001a\n\u0012\u0004\u0012\u000205\u0018\u0001002\u000e\u00106\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001002\u0006\u00107\u001a\u00020\u001aH\u0016J2\u00108\u001a\u00020\f2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u00042\u000e\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001002\u0006\u00101\u001a\u000209H\u0016J\u0012\u0010:\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010;\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010<\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010=\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010>\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u001aH\u0016J\u0010\u0010?\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u001aH\u0016J\u0010\u0010@\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u001aH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/MirCoreBinder;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface$Stub;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "coreJob", "Lkotlinx/coroutines/Job;", "acrossFloorPathSegment", "Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegments;", "destination", "addCliffDistanceListener", "", "p0", "p1", "Lcom/pudutech/mirsdk/mircore/aidl/CliffInfoListener;", "addDockerEstimateTransformListener", "Lcom/pudutech/mirsdk/mircore/DockerEstimateTransformListener;", "addFaceDetectListener", "Lcom/pudutech/mirsdk/mircore/FaceDetectListener;", "addReflectorDistanceListener", "Lcom/pudutech/mirsdk/mircore/aidl/ReflectorInfoListener;", "currentFloorPathSegment", "detectChargeDocker", "Lcom/pudutech/mirsdk/mircore/coreparcel/DockerDetectResult;", "enableFaceDetect", "", "getAreaDetect", "Lcom/pudutech/mirsdk/mircore/AreaDetectInterface;", "getGitHash", "getLocalizer", "Lcom/pudutech/mirsdk/mircore/LocalizationInterface;", "getNavigator", "Lcom/pudutech/mirsdk/mircore/NavigationInterface;", "getPlanPathWeight", "", "starter", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "end_pose_x", "end_pose_y", "getScheduler", "Lcom/pudutech/mirsdk/mircore/ScheduleInterface;", "hasCoreReady", "initModules", "defFloorIndex", "", "pdmap", "floors_map", "", "listener", "Lcom/pudutech/mirsdk/mircore/InitServiceListener;", "isArriveCirclePath", "orderDestinationWithRange", "Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "destinations", "order", "reloadPdmap", "Lcom/pudutech/mirsdk/mircore/ReloadMapResultListener;", "removeCliffDistanceListener", "removeDockerEstimateTransformListener", "removeFaceDetectListener", "removeReflectorDistanceListener", "setDropDetSwitch", "setRefletorSwitch", "switchAutoExposure", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MirCoreBinder extends MirCoreInterface.Stub {
    public static final MirCoreBinder INSTANCE;
    private static final String TAG;
    private static Job coreJob;

    static {
        MirCoreBinder mirCoreBinder = new MirCoreBinder();
        INSTANCE = mirCoreBinder;
        TAG = mirCoreBinder.getClass().getSimpleName();
    }

    private MirCoreBinder() {
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public NavigationInterface getNavigator() {
        return MirCoreImpl.INSTANCE.getNavigationer();
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public LocalizationInterface getLocalizer() {
        return MirCoreImpl.INSTANCE.getLocalization();
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public ScheduleInterface getScheduler() {
        return MirCoreImpl.INSTANCE.getScheduleStub();
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public AreaDetectInterface getAreaDetect() {
        return MirCoreImpl.INSTANCE.getAreadetectStub();
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public synchronized void initModules(int defFloorIndex, String pdmap, List<String> floors_map, InitServiceListener listener) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(pdmap, "pdmap");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (coreJob != null) {
            return;
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirCoreBinder$initModules$1(defFloorIndex, pdmap, floors_map, listener, null), 3, null);
        coreJob = launch$default;
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public synchronized void reloadPdmap(int defFloorIndex, String pdmap, List<String> floors_map, ReloadMapResultListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirCoreBinder$reloadPdmap$1(defFloorIndex, pdmap, floors_map, listener, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public double getPlanPathWeight(Vector3d starter, double end_pose_x, double end_pose_y) {
        return MirCoreImpl.INSTANCE.pathplanForTask(starter, end_pose_x, end_pose_y);
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public PathSegments currentFloorPathSegment(String destination) {
        if (destination != null) {
            Pdlog.m3275i(TAG, "task is valid, call currentFloorPathSegment");
            return ScheduleMaster.INSTANCE.currentFloorPathSegment(destination);
        }
        Pdlog.m3275i(TAG, "task is invalid, can not call currentFloorPathSegment");
        return null;
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public PathSegments acrossFloorPathSegment(String destination) {
        if (destination != null) {
            Pdlog.m3275i(TAG, "task is valid, call acrossFloorPathSegment");
            return ScheduleMaster.INSTANCE.acrossFloorPathSegment(destination);
        }
        Pdlog.m3275i(TAG, "task is invalid, can not call acrossFloorPathSegment");
        return null;
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public List<DestinationWithAccRange> orderDestinationWithRange(List<String> destinations, boolean order) {
        if (destinations != null) {
            Pdlog.m3275i(TAG, "task is valid, call orderDestinationWithRange");
            return CollectionsKt.toMutableList((Collection) ScheduleMaster.INSTANCE.orderDestinationWithRange(destinations, order));
        }
        Pdlog.m3275i(TAG, "task is invalid, can not call orderDestinationWithRange");
        return null;
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public boolean hasCoreReady() {
        return Perception.INSTANCE.isModuleInited() && ScheduleMaster.INSTANCE.isModuleReady();
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public String getGitHash() {
        String str = "{\"core\":{\"git\":\"commit: 4f43cdb, auth: “Chengsi Shang”<“shangchengsi@pudutech.com”>, time: “Wed May 17 15:53:38 2023 +0800”\",\"relays\":[" + AIDLGitHash.INSTANCE.getGitHash() + "," + Perception.INSTANCE.getGitHash() + "," + ScheduleMaster.INSTANCE.getGitHash() + "," + Navigation.INSTANCE.getGitHash() + "," + MirCoreImpl.INSTANCE.getLocalization().getVersionInfo() + ",]}}";
        Intrinsics.checkExpressionValueIsNotNull(str, "string.toString()");
        return str;
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public boolean isArriveCirclePath() {
        return ScheduleMaster.INSTANCE.isArriveCirclePath();
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void switchAutoExposure(boolean p0) {
        MirCoreImpl.INSTANCE.getLocalization().switchAutoExposure(p0);
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void addFaceDetectListener(String p0, FaceDetectListener p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        MirCoreImpl.INSTANCE.getFaceDetectListener$mircore_packRelease().add(p0, p1);
        Perception.INSTANCE.addVisionListener(p0, new Function5<Integer, Double, Double, Double, Double, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreBinder$addFaceDetectListener$1
            @Override // kotlin.jvm.functions.Function5
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Double d, Double d2, Double d3, Double d4) {
                invoke(num.intValue(), d.doubleValue(), d2.doubleValue(), d3.doubleValue(), d4.doubleValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final int i, final double d, final double d2, final double d3, double d4) {
                MirCoreImpl.INSTANCE.getFaceDetectListener$mircore_packRelease().notify(new Function2<FaceDetectListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreBinder$addFaceDetectListener$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(FaceDetectListener faceDetectListener, String str) {
                        invoke2(faceDetectListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FaceDetectListener it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onDetection(i, d, d2, d3);
                    }
                });
            }
        });
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void removeFaceDetectListener(String p0) {
        if (p0 == null) {
            return;
        }
        Perception.INSTANCE.removeVisionListener(p0);
        MirCoreImpl.INSTANCE.getFaceDetectListener$mircore_packRelease().remove(p0);
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void enableFaceDetect(boolean p0) {
        Perception.INSTANCE.enableVision(p0);
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public DockerDetectResult detectChargeDocker() {
        return MirCoreImpl.INSTANCE.detectChargeDocker();
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void addDockerEstimateTransformListener(String p0, DockerEstimateTransformListener p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        MirCoreImpl.INSTANCE.getDockerEstimateTransformListener$mircore_packRelease().add(p0, p1);
        Perception.INSTANCE.addAutoDockListener(p0, new Function4<Boolean, Double, Double, Double, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreBinder$addDockerEstimateTransformListener$1
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Double d, Double d2, Double d3) {
                invoke(bool.booleanValue(), d.doubleValue(), d2.doubleValue(), d3.doubleValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z, final double d, final double d2, final double d3) {
                MirCoreImpl.INSTANCE.getDockerEstimateTransformListener$mircore_packRelease().notify(new Function2<DockerEstimateTransformListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreBinder$addDockerEstimateTransformListener$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(DockerEstimateTransformListener dockerEstimateTransformListener, String str) {
                        invoke2(dockerEstimateTransformListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DockerEstimateTransformListener it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.callDockerEstimateTransform(z, d, d2, d3);
                    }
                });
            }
        });
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void removeDockerEstimateTransformListener(String p0) {
        if (p0 == null) {
            return;
        }
        Perception.INSTANCE.removeAutoDockListener(p0);
        MirCoreImpl.INSTANCE.getDockerEstimateTransformListener$mircore_packRelease().remove(p0);
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void setDropDetSwitch(boolean p0) {
        Perception.INSTANCE.setDropDetSwitch(p0);
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void addCliffDistanceListener(String p0, CliffInfoListener p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        Pdlog.m3273d(TAG, "addCliffDistanceListener " + p0);
        MirCoreImpl.INSTANCE.getCliffDisListener$mircore_packRelease().add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void removeCliffDistanceListener(String p0) {
        if (p0 == null) {
            return;
        }
        MirCoreImpl.INSTANCE.getCliffDisListener$mircore_packRelease().remove(p0);
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void addReflectorDistanceListener(String p0, ReflectorInfoListener p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        Pdlog.m3273d(TAG, "addReflectorDistanceListener " + p0);
        MirCoreImpl.INSTANCE.getReflectorDisListener$mircore_packRelease().add(p0, p1);
        Perception.INSTANCE.addReflectorDetectListener(p0, new Function3<Double, Integer, Integer, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreBinder$addReflectorDistanceListener$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Double d, Integer num, Integer num2) {
                invoke(d.doubleValue(), num.intValue(), num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(double d, int i, int i2) {
                MirCoreImpl.INSTANCE.processReflectorResult(d, i, i2);
            }
        });
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void removeReflectorDistanceListener(String p0) {
        if (p0 == null) {
            return;
        }
        Perception.INSTANCE.removeReflectorDetectListener(p0);
        MirCoreImpl.INSTANCE.getReflectorDisListener$mircore_packRelease().remove(p0);
    }

    @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
    public void setRefletorSwitch(boolean p0) {
        MirCoreImpl.INSTANCE.loadReflectorParam();
    }
}
