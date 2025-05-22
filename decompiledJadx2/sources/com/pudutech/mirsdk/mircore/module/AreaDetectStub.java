package com.pudutech.mirsdk.mircore.module;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.AreaDetectInterface;
import com.pudutech.mirsdk.mircore.aidl.ElevatorZoneListener;
import com.pudutech.mirsdk.mircore.aidl.ExemptZoneListener;
import com.pudutech.mirsdk.mircore.aidl.NoDetourListener;
import com.pudutech.mirsdk.mircore.aidl.RGBDFunLimitListener;
import com.pudutech.mirsdk.mircore.aidl.SpeedLimitListener;
import com.pudutech.mirsdk.mircore.mapareadetection.MapAreaDetection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AreaDetectStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0016J\u001c\u0010\u0015\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\nH\u0016J\u001c\u0010\u0016\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\u0017\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010\u0018\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010H\u0016J\u001a\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0013\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0014\u001a\u00020\u001cH\u0016J:\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u001c2\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u00042\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001cH\u0016J\u0012\u0010$\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010%\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010&\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010'\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010(\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010)\u001a\u00020\u001a2\b\u0010*\u001a\u0004\u0018\u00010\u0004H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/module/AreaDetectStub;", "Lcom/pudutech/mirsdk/mircore/AreaDetectInterface$Stub;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "elevatorZoneListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/mircore/aidl/ElevatorZoneListener;", "exemptZoneListener", "Lcom/pudutech/mirsdk/mircore/aidl/ExemptZoneListener;", "noDetourListener", "Lcom/pudutech/mirsdk/mircore/aidl/NoDetourListener;", "rgbdFunLimitListener", "Lcom/pudutech/mirsdk/mircore/aidl/RGBDFunLimitListener;", "speedLimitListener", "Lcom/pudutech/mirsdk/mircore/aidl/SpeedLimitListener;", "addElevatorZoneListener", "", "p0", "p1", "addExemptZoneListener", "addNoDetourListener", "addRGBDFunLimitListener", "addSpeedLimitListener", "getInsideStatus", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "", "receiveAreaData", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "p2", "p3", "", "p4", "removeElevatorZoneListener", "removeExemptZoneListener", "removeNoDetourListener", "removeRGBDFunLimitListener", "removeSpeedLimitListener", "setMap", "mapPath", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AreaDetectStub extends AreaDetectInterface.Stub {
    private final String TAG = getClass().getSimpleName();
    private final ThreadSafeListener<SpeedLimitListener> speedLimitListener = new ThreadSafeListener<>();
    private final ThreadSafeListener<NoDetourListener> noDetourListener = new ThreadSafeListener<>();
    private final ThreadSafeListener<RGBDFunLimitListener> rgbdFunLimitListener = new ThreadSafeListener<>();
    private final ThreadSafeListener<ElevatorZoneListener> elevatorZoneListener = new ThreadSafeListener<>();
    private final ThreadSafeListener<ExemptZoneListener> exemptZoneListener = new ThreadSafeListener<>();

    public AreaDetectStub() {
        MapAreaDetection.INSTANCE.initialize();
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public boolean setMap(String mapPath) {
        Pdlog.m3273d(this.TAG, "setMap");
        if (mapPath != null) {
            return MapAreaDetection.INSTANCE.setMap(mapPath);
        }
        return false;
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void receiveAreaData(int p0, List<Vector2d> p1, String p2, double p3, int p4) {
        Pdlog.m3273d(this.TAG, "receiveAreaData");
        if (p1 != null && p2 != null) {
            MapAreaDetection mapAreaDetection = MapAreaDetection.INSTANCE;
            Object[] array = p1.toArray(new Vector2d[0]);
            if (array != null) {
                mapAreaDetection.receiveAreaData(p0, (Vector2d[]) array, p2, Double.valueOf(p3), Integer.valueOf(p4));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Pdlog.m3277w(this.TAG, "receiveAreaData null");
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void addExemptZoneListener(String p0, ExemptZoneListener p1) {
        Pdlog.m3273d(this.TAG, "addExemptZoneListener");
        if (p0 != null && p1 != null) {
            this.exemptZoneListener.add(p0, p1);
            MapAreaDetection.INSTANCE.addExemptZoneListener(p0, new Function2<Boolean, Integer, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.AreaDetectStub$addExemptZoneListener$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Integer num) {
                    invoke(bool.booleanValue(), num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(final boolean z, final int i) {
                    ThreadSafeListener threadSafeListener;
                    threadSafeListener = AreaDetectStub.this.exemptZoneListener;
                    threadSafeListener.notify(new Function2<ExemptZoneListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.AreaDetectStub$addExemptZoneListener$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(ExemptZoneListener exemptZoneListener, String str) {
                            invoke2(exemptZoneListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ExemptZoneListener it, String str) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            it.callback(z, i);
                        }
                    });
                }
            });
        } else {
            Pdlog.m3277w(this.TAG, "addExemptZoneListener null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void removeExemptZoneListener(String p0) {
        Pdlog.m3273d(this.TAG, "removeExemptZoneListener");
        if (p0 != null) {
            MapAreaDetection.INSTANCE.removeExemptZoneListener(p0);
            this.exemptZoneListener.remove(p0);
        } else {
            Pdlog.m3277w(this.TAG, "removeSpeedLimitListener null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void addSpeedLimitListener(String p0, SpeedLimitListener p1) {
        Pdlog.m3273d(this.TAG, "addSpeedLimitListener");
        if (p0 != null && p1 != null) {
            this.speedLimitListener.add(p0, p1);
            MapAreaDetection.INSTANCE.addSpeedLimitListener(p0, new Function2<Boolean, Double, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.AreaDetectStub$addSpeedLimitListener$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Double d) {
                    invoke(bool.booleanValue(), d.doubleValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(final boolean z, final double d) {
                    ThreadSafeListener threadSafeListener;
                    threadSafeListener = AreaDetectStub.this.speedLimitListener;
                    threadSafeListener.notify(new Function2<SpeedLimitListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.AreaDetectStub$addSpeedLimitListener$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(SpeedLimitListener speedLimitListener, String str) {
                            invoke2(speedLimitListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SpeedLimitListener it, String str) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            it.callback(z, d);
                        }
                    });
                }
            });
        } else {
            Pdlog.m3277w(this.TAG, "addSpeedLimitListener null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void removeSpeedLimitListener(String p0) {
        Pdlog.m3273d(this.TAG, "removeSpeedLimitListener");
        if (p0 != null) {
            MapAreaDetection.INSTANCE.removeSpeedLimitListener(p0);
            this.speedLimitListener.remove(p0);
        } else {
            Pdlog.m3277w(this.TAG, "removeSpeedLimitListener null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void addNoDetourListener(String p0, NoDetourListener p1) {
        Pdlog.m3273d(this.TAG, "addNoDetourListener");
        if (p0 != null && p1 != null) {
            this.noDetourListener.add(p0, p1);
            MapAreaDetection.INSTANCE.addNoDetourListener(p0, new Function2<Boolean, Double, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.AreaDetectStub$addNoDetourListener$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Double d) {
                    invoke(bool.booleanValue(), d.doubleValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(final boolean z, final double d) {
                    ThreadSafeListener threadSafeListener;
                    threadSafeListener = AreaDetectStub.this.noDetourListener;
                    threadSafeListener.notify(new Function2<NoDetourListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.AreaDetectStub$addNoDetourListener$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(NoDetourListener noDetourListener, String str) {
                            invoke2(noDetourListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(NoDetourListener it, String str) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            it.callback(z, d);
                        }
                    });
                }
            });
        } else {
            Pdlog.m3277w(this.TAG, "addNoDetourListener null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void removeNoDetourListener(String p0) {
        Pdlog.m3273d(this.TAG, "removeNoDetourListener");
        if (p0 != null) {
            MapAreaDetection.INSTANCE.removeNoDetourListener(p0);
            this.noDetourListener.remove(p0);
        } else {
            Pdlog.m3277w(this.TAG, "removeNoDetourListener null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void addRGBDFunLimitListener(String p0, RGBDFunLimitListener p1) {
        Pdlog.m3273d(this.TAG, "addRGBDFunLimitListener");
        if (p0 != null && p1 != null) {
            this.rgbdFunLimitListener.add(p0, p1);
            MapAreaDetection.INSTANCE.addRGBDFunLimitListener(p0, new Function2<Boolean, Integer, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.AreaDetectStub$addRGBDFunLimitListener$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Integer num) {
                    invoke(bool.booleanValue(), num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(final boolean z, final int i) {
                    ThreadSafeListener threadSafeListener;
                    threadSafeListener = AreaDetectStub.this.rgbdFunLimitListener;
                    threadSafeListener.notify(new Function2<RGBDFunLimitListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.AreaDetectStub$addRGBDFunLimitListener$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(RGBDFunLimitListener rGBDFunLimitListener, String str) {
                            invoke2(rGBDFunLimitListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(RGBDFunLimitListener it, String str) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            it.callback(z, i);
                        }
                    });
                }
            });
        } else {
            Pdlog.m3277w(this.TAG, "addRGBDFunLimitListener null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void removeRGBDFunLimitListener(String p0) {
        Pdlog.m3273d(this.TAG, "removeRGBDFunLimitListener");
        if (p0 != null) {
            MapAreaDetection.INSTANCE.removeNoDetourListener(p0);
            this.rgbdFunLimitListener.remove(p0);
        } else {
            Pdlog.m3277w(this.TAG, "removeRGBDFunLimitListener null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void addElevatorZoneListener(String p0, ElevatorZoneListener p1) {
        Pdlog.m3273d(this.TAG, "addElevatorZoneListener");
        if (p0 != null && p1 != null) {
            MapAreaDetection.INSTANCE.addElevatorZoneListener(p0, new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.AreaDetectStub$addElevatorZoneListener$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(final boolean z) {
                    ThreadSafeListener threadSafeListener;
                    threadSafeListener = AreaDetectStub.this.elevatorZoneListener;
                    threadSafeListener.notify(new Function2<ElevatorZoneListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.AreaDetectStub$addElevatorZoneListener$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(ElevatorZoneListener elevatorZoneListener, String str) {
                            invoke2(elevatorZoneListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ElevatorZoneListener it, String str) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            it.callback(z);
                        }
                    });
                }
            });
            this.elevatorZoneListener.add(p0, p1);
        } else {
            Pdlog.m3277w(this.TAG, "addElevatorZoneListener null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public void removeElevatorZoneListener(String p0) {
        Pdlog.m3273d(this.TAG, "removeElevatorZoneListener");
        if (p0 != null) {
            MapAreaDetection.INSTANCE.removeElevatorZoneListener(p0);
            this.elevatorZoneListener.remove(p0);
        } else {
            Pdlog.m3277w(this.TAG, "removeElevatorZoneListener");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
    public boolean getInsideStatus(Vector3d p0, int p1) {
        Pdlog.m3273d(this.TAG, "getInsideStatus");
        if (p0 != null) {
            return MapAreaDetection.INSTANCE.getInsideStatus(p0, p1);
        }
        Pdlog.m3277w(this.TAG, "getInsideStatus");
        return false;
    }
}
