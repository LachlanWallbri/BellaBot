package com.pudutech.mirsdk.hardware;

import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.cameralib.CameraLib;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: MarkerCameraImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u001f\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010\u00062\b\u0010!\u001a\u0004\u0018\u00010\u0012H\u0016J\u001c\u0010\"\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010\u00062\b\u0010!\u001a\u0004\u0018\u00010\u001aH\u0016J\u001c\u0010#\u001a\u00020\u000f2\b\u0010$\u001a\u0004\u0018\u00010\u00062\b\u0010%\u001a\u0004\u0018\u00010\u0012H\u0016J\u001c\u0010&\u001a\u00020\u000f2\b\u0010$\u001a\u0004\u0018\u00010\u00062\b\u0010%\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010'\u001a\u00020\u000fH\u0016J\b\u0010(\u001a\u00020\u000fH\u0016J\b\u0010)\u001a\u00020\rH\u0016J\u0006\u0010*\u001a\u00020\u0006J\u0006\u0010+\u001a\u00020\u0006J\u0006\u0010,\u001a\u00020\u0006J\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u000200J\b\u00101\u001a\u00020\u000fH\u0016J\b\u00102\u001a\u00020\u000fH\u0016J\u0012\u00103\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u00104\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u00105\u001a\u00020\u000f2\b\u0010$\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u00106\u001a\u00020\u000f2\b\u0010$\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u00107\u001a\u00020.2\u0006\u00108\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R8\u0010\n\u001a,\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R8\u0010\u0017\u001a,\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/CameraImpl;", "Lcom/pudutech/mirsdk/hardware/CameraInterface$Stub;", "machine", "Lcom/pudutech/mirsdk/hardware/MachineInfoProcess;", "(Lcom/pudutech/mirsdk/hardware/MachineInfoProcess;)V", "TAG", "", "closeMarkerJob", "Lkotlinx/coroutines/Job;", "closeMonocularJob", "frameDistributor", "Lkotlin/Function6;", "Landroid/os/ParcelFileDescriptor;", "", "", "", "listeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData;", "getMachine", "()Lcom/pudutech/mirsdk/hardware/MachineInfoProcess;", "monocularCameraOpened", "Ljava/util/concurrent/atomic/AtomicBoolean;", "monocularFrameDistributor", "monocularListeners", "monocularStatelisteners", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraState;", "openMarkerJob", "openMonocularJob", "opened", "statelisteners", "addMarkerCameraListener", "name", "listener", "addMarkerCameraStateListener", "addMonocularCameraListener", "p0", "p1", "addMonocularCameraStateListener", "closeMarkerCamera", "closeMonocularCamera", "getCameraExposure", "getCameraGit", "getDevVid", "getLastRunningErrorMsg", "isOpened", "", "open", "Lcom/pudutech/mirsdk/hardware/cameralib/CameraLib$Result;", "openMarkerCamera", "openMonocularCamera", "removeMarkerCameraListener", "removeMarkerCameraStateListener", "removeMonocularCameraListener", "removeMonocularCameraStateListener", "setCameraExposure", "exposure", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CameraImpl extends CameraInterface.Stub {
    private final String TAG;
    private Job closeMarkerJob;
    private Job closeMonocularJob;
    private final Function6<ParcelFileDescriptor, Integer, Integer, Integer, Integer, Long, Unit> frameDistributor;
    private final ThreadSafeListener<IMarkerCameraData> listeners;
    private final MachineInfoProcess machine;
    private AtomicBoolean monocularCameraOpened;
    private final Function6<ParcelFileDescriptor, Integer, Integer, Integer, Integer, Long, Unit> monocularFrameDistributor;
    private final ThreadSafeListener<IMarkerCameraData> monocularListeners;
    private final ThreadSafeListener<IMarkerCameraState> monocularStatelisteners;
    private Job openMarkerJob;
    private Job openMonocularJob;
    private AtomicBoolean opened;
    private final ThreadSafeListener<IMarkerCameraState> statelisteners;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;

        static {
            int[] iArr = new int[MachineModel.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MachineModel.Ninetales.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineModel.Firefox.ordinal()] = 2;
            int[] iArr2 = new int[MachineModel.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[MachineModel.Ninetales.ordinal()] = 1;
            $EnumSwitchMapping$1[MachineModel.Firefox.ordinal()] = 2;
            int[] iArr3 = new int[MachineModel.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[MachineModel.Ninetales.ordinal()] = 1;
            $EnumSwitchMapping$2[MachineModel.Firefox.ordinal()] = 2;
            int[] iArr4 = new int[MachineModel.values().length];
            $EnumSwitchMapping$3 = iArr4;
            iArr4[MachineModel.Ninetales.ordinal()] = 1;
            $EnumSwitchMapping$3[MachineModel.Firefox.ordinal()] = 2;
            int[] iArr5 = new int[MachineModel.values().length];
            $EnumSwitchMapping$4 = iArr5;
            iArr5[MachineModel.Ninetales.ordinal()] = 1;
            $EnumSwitchMapping$4[MachineModel.Firefox.ordinal()] = 2;
            int[] iArr6 = new int[MachineInfo.MonocularType.values().length];
            $EnumSwitchMapping$5 = iArr6;
            iArr6[MachineInfo.MonocularType.XinYouKang.ordinal()] = 1;
            $EnumSwitchMapping$5[MachineInfo.MonocularType.YuXiang.ordinal()] = 2;
        }
    }

    public CameraImpl(MachineInfoProcess machine) {
        Intrinsics.checkParameterIsNotNull(machine, "machine");
        this.machine = machine;
        this.TAG = "camera";
        this.opened = new AtomicBoolean(false);
        this.monocularCameraOpened = new AtomicBoolean(false);
        this.statelisteners = new ThreadSafeListener<>();
        this.listeners = new ThreadSafeListener<>();
        this.monocularStatelisteners = new ThreadSafeListener<>();
        this.monocularListeners = new ThreadSafeListener<>();
        this.frameDistributor = new Function6<ParcelFileDescriptor, Integer, Integer, Integer, Integer, Long, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$frameDistributor$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(6);
            }

            @Override // kotlin.jvm.functions.Function6
            public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3, Integer num4, Long l) {
                invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue(), num4.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final ParcelFileDescriptor parcelFileDescriptor, final int i, final int i2, final int i3, final int i4, final long j) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                threadSafeListener = CameraImpl.this.listeners;
                threadSafeListener.notify(new Function2<IMarkerCameraData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$frameDistributor$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraData iMarkerCameraData, String str) {
                        invoke2(iMarkerCameraData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IMarkerCameraData it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onFrame(parcelFileDescriptor, i, i2, i3, i4, j);
                    }
                });
            }
        };
        this.monocularFrameDistributor = new Function6<ParcelFileDescriptor, Integer, Integer, Integer, Integer, Long, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$monocularFrameDistributor$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(6);
            }

            @Override // kotlin.jvm.functions.Function6
            public /* bridge */ /* synthetic */ Unit invoke(ParcelFileDescriptor parcelFileDescriptor, Integer num, Integer num2, Integer num3, Integer num4, Long l) {
                invoke(parcelFileDescriptor, num.intValue(), num2.intValue(), num3.intValue(), num4.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final ParcelFileDescriptor parcelFileDescriptor, final int i, final int i2, final int i3, final int i4, final long j) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                threadSafeListener = CameraImpl.this.monocularListeners;
                threadSafeListener.notify(new Function2<IMarkerCameraData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$monocularFrameDistributor$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraData iMarkerCameraData, String str) {
                        invoke2(iMarkerCameraData, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IMarkerCameraData it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onFrame(parcelFileDescriptor, i, i2, i3, i4, j);
                    }
                });
            }
        };
        this.listeners.add("tick", new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.hardware.CameraImpl.1
            @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
            public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
                CameraImpl.this.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$1$onFrame$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                        invoke2(iMarkerCameraState, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IMarkerCameraState it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onCameraFrameTick();
                    }
                });
            }
        });
        this.monocularListeners.add("tick", new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.hardware.CameraImpl.2
            @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
            public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
                CameraImpl.this.monocularStatelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$2$onFrame$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                        invoke2(iMarkerCameraState, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IMarkerCameraState it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onCameraFrameTick();
                    }
                });
            }
        });
    }

    public final MachineInfoProcess getMachine() {
        return this.machine;
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void addMarkerCameraListener(String name, IMarkerCameraData listener) {
        if (name == null || listener == null) {
            return;
        }
        this.listeners.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void removeMarkerCameraListener(String name) {
        if (name != null) {
            this.listeners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void addMarkerCameraStateListener(String name, IMarkerCameraState listener) {
        if (name == null || listener == null) {
            return;
        }
        this.statelisteners.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void removeMarkerCameraStateListener(String name) {
        if (name != null) {
            this.statelisteners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void openMarkerCamera() {
        Job launch$default;
        int i = WhenMappings.$EnumSwitchMapping$0[this.machine.getMachineInfo().getProductMachineType().getModel().ordinal()];
        if (i == 1 || i == 2) {
            this.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$openMarkerCamera$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                    invoke2(iMarkerCameraState, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraState it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpened(true);
                }
            });
            this.opened.set(true);
            return;
        }
        Job job = this.openMarkerJob;
        if (job != null && job.isActive()) {
            Pdlog.m3276v(this.TAG, "camera marker open job is running");
            return;
        }
        Job job2 = this.closeMarkerJob;
        if (job2 != null && job2.isActive()) {
            Pdlog.m3276v(this.TAG, "camera marker close job is running");
        } else if (!this.opened.get()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new CameraImpl$openMarkerCamera$3(this, null), 3, null);
            this.openMarkerJob = launch$default;
        } else {
            this.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$openMarkerCamera$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                    invoke2(iMarkerCameraState, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraState it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpened(true);
                }
            });
        }
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void closeMarkerCamera() {
        Job launch$default;
        int i = WhenMappings.$EnumSwitchMapping$1[this.machine.getMachineInfo().getProductMachineType().getModel().ordinal()];
        if (i == 1 || i == 2) {
            this.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$closeMarkerCamera$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                    invoke2(iMarkerCameraState, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraState it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpened(false);
                }
            });
            this.opened.set(false);
            return;
        }
        Job job = this.openMarkerJob;
        if (job != null && job.isActive()) {
            Pdlog.m3276v(this.TAG, "camera marker open job is running");
            return;
        }
        Job job2 = this.closeMarkerJob;
        if (job2 != null && job2.isActive()) {
            Pdlog.m3276v(this.TAG, "camera marker close job is running");
        } else if (this.opened.get()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new CameraImpl$closeMarkerCamera$3(this, null), 3, null);
            this.closeMarkerJob = launch$default;
        } else {
            this.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$closeMarkerCamera$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                    invoke2(iMarkerCameraState, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraState it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpened(false);
                }
            });
        }
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public boolean setCameraExposure(int exposure) {
        int i = WhenMappings.$EnumSwitchMapping$2[this.machine.getMachineInfo().getProductMachineType().getModel().ordinal()];
        if (i == 1 || i == 2) {
            return true;
        }
        return CameraLib.INSTANCE.setExposure(exposure);
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public int getCameraExposure() {
        int i = WhenMappings.$EnumSwitchMapping$3[this.machine.getMachineInfo().getProductMachineType().getModel().ordinal()];
        if (i == 1 || i == 2) {
            return 0;
        }
        return CameraLib.INSTANCE.getExposure();
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void addMonocularCameraListener(String p0, IMarkerCameraData p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        this.monocularListeners.add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void removeMonocularCameraListener(String p0) {
        if (p0 == null) {
            return;
        }
        this.monocularListeners.remove(p0);
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void addMonocularCameraStateListener(String p0, IMarkerCameraState p1) {
        if (p0 == null || p1 == null) {
            return;
        }
        this.monocularStatelisteners.add(p0, p1);
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void removeMonocularCameraStateListener(String p0) {
        if (p0 == null) {
            return;
        }
        this.monocularStatelisteners.remove(p0);
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void openMonocularCamera() {
        Job launch$default;
        Job job = this.openMonocularJob;
        if (job != null && job.isActive()) {
            Pdlog.m3276v(this.TAG, "camera monocular open job is running");
            return;
        }
        Job job2 = this.closeMonocularJob;
        if (job2 != null && job2.isActive()) {
            Pdlog.m3276v(this.TAG, "camera monocular close job is running");
        } else {
            if (!this.monocularCameraOpened.get()) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new CameraImpl$openMonocularCamera$2(this, null), 3, null);
                this.openMonocularJob = launch$default;
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CameraImpl$openMonocularCamera$3(this, null), 3, null);
                return;
            }
            this.monocularStatelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$openMonocularCamera$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                    invoke2(iMarkerCameraState, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraState it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpened(true);
                }
            });
        }
    }

    @Override // com.pudutech.mirsdk.hardware.CameraInterface
    public void closeMonocularCamera() {
        Job launch$default;
        Job job = this.openMonocularJob;
        if (job != null && job.isActive()) {
            Pdlog.m3276v(this.TAG, "camera monocular open job is running");
            return;
        }
        Job job2 = this.closeMonocularJob;
        if (job2 != null && job2.isActive()) {
            Pdlog.m3276v(this.TAG, "camera monocular close job is running");
        } else {
            if (this.monocularCameraOpened.get()) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new CameraImpl$closeMonocularCamera$2(this, null), 3, null);
                this.closeMonocularJob = launch$default;
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CameraImpl$closeMonocularCamera$3(this, null), 3, null);
                return;
            }
            this.monocularStatelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$closeMonocularCamera$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                    invoke2(iMarkerCameraState, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraState it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpened(false);
                }
            });
        }
    }

    public final String getLastRunningErrorMsg() {
        return CameraLib.INSTANCE.getLastRunningError();
    }

    public final CameraLib.Result open() {
        CameraLib.Result result;
        int i = WhenMappings.$EnumSwitchMapping$4[this.machine.getMachineInfo().getProductMachineType().getModel().ordinal()];
        if (i == 1 || i == 2) {
            result = new CameraLib.Result(true, "");
        } else {
            result = CameraLib.INSTANCE.openMarkerCamera(this.frameDistributor);
        }
        if (result.isSuccess()) {
            this.opened.set(true);
            this.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$open$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                    invoke2(iMarkerCameraState, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraState it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpened(true);
                }
            });
        } else {
            this.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$open$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                    invoke2(iMarkerCameraState, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraState it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpened(false);
                }
            });
        }
        if (this.machine.getMachineInfo().getMonocularDeviceType() != MachineInfo.MonocularType.NoDevice && result.isSuccess()) {
            result = CameraLib.INSTANCE.openMonocularCamera(this.monocularFrameDistributor, getDevVid());
            if (result.isSuccess()) {
                this.monocularCameraOpened.set(true);
                this.monocularStatelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$open$3
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                        invoke2(iMarkerCameraState, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IMarkerCameraState it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onOpened(true);
                    }
                });
            } else {
                this.monocularStatelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$open$4
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                        invoke2(iMarkerCameraState, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IMarkerCameraState it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onOpened(false);
                    }
                });
            }
        }
        return result;
    }

    public final boolean isOpened() {
        if (this.opened.get()) {
            if (this.machine.getMachineInfo().getMonocularDeviceType() != MachineInfo.MonocularType.NoDevice ? this.monocularCameraOpened.get() : true) {
                return true;
            }
        }
        return false;
    }

    public final String getCameraGit() {
        return CameraLib.INSTANCE.getGitHash();
    }

    public final String getDevVid() {
        return WhenMappings.$EnumSwitchMapping$5[this.machine.getMachineInfo().getMonocularDeviceType().ordinal()] != 1 ? "1bcf0b09" : "0bda3035";
    }
}
