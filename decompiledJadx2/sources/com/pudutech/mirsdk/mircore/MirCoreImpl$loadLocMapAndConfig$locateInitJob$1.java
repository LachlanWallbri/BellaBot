package com.pudutech.mirsdk.mircore;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.mircore.MirCoreImpl;
import com.pudutech.mirsdk.mircore.PuduLocateInterface;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import com.pudutech.mirsdk.mircore.module.HardwareLinker;
import com.pudutech.mirsdk.mircore.module.LocalizationStub;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.MirCoreImpl$loadLocMapAndConfig$locateInitJob$1", m3970f = "MirCoreImpl.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class MirCoreImpl$loadLocMapAndConfig$locateInitJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ InitServiceListener $initListener;
    final /* synthetic */ List $initModuleList;
    final /* synthetic */ Ref.ObjectRef $locateBytes;
    final /* synthetic */ Ref.ObjectRef $locateCase;
    final /* synthetic */ Ref.ObjectRef $optemapConfigBytes;
    final /* synthetic */ Ref.ObjectRef $optemapMapBytes;
    final /* synthetic */ Ref.ObjectRef $stcmMapBytes;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6167p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MirCoreImpl$loadLocMapAndConfig$locateInitJob$1(Ref.ObjectRef objectRef, InitServiceListener initServiceListener, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3, Ref.ObjectRef objectRef4, Ref.ObjectRef objectRef5, List list, Continuation continuation) {
        super(2, continuation);
        this.$locateCase = objectRef;
        this.$initListener = initServiceListener;
        this.$locateBytes = objectRef2;
        this.$optemapConfigBytes = objectRef3;
        this.$optemapMapBytes = objectRef4;
        this.$stcmMapBytes = objectRef5;
        this.$initModuleList = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirCoreImpl$loadLocMapAndConfig$locateInitJob$1 mirCoreImpl$loadLocMapAndConfig$locateInitJob$1 = new MirCoreImpl$loadLocMapAndConfig$locateInitJob$1(this.$locateCase, this.$initListener, this.$locateBytes, this.$optemapConfigBytes, this.$optemapMapBytes, this.$stcmMapBytes, this.$initModuleList, completion);
        mirCoreImpl$loadLocMapAndConfig$locateInitJob$1.f6167p$ = (CoroutineScope) obj;
        return mirCoreImpl$loadLocMapAndConfig$locateInitJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirCoreImpl$loadLocMapAndConfig$locateInitJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MachineModel machineModel;
        boolean loadMarkerLocateConfig;
        boolean loadMarkerLocateMap;
        String str;
        MachineModel machineModel2;
        boolean loadLaserLocateConfig;
        boolean loadLocateMap;
        String str2;
        MachineModel machineModel3;
        boolean loadLaserLocateConfig2;
        boolean loadMarkerLocateConfig2;
        boolean loadLocateMap2;
        int i;
        String str3;
        String str4;
        String str5;
        String str6;
        MachineModel machineModel4;
        boolean loadLocateMap3;
        String str7;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6167p$;
        int i2 = MirCoreImpl.WhenMappings.$EnumSwitchMapping$2[((PuduLocateInterface.LocateCase) this.$locateCase.element).ordinal()];
        if (i2 == 1) {
            LocalizationStub localization = MirCoreImpl.INSTANCE.getLocalization();
            Context context = MirCoreService.INSTANCE.getContext().get();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "MirCoreService.context.get()!!");
            PuduLocateInterface.LocateCase locateCase = (PuduLocateInterface.LocateCase) this.$locateCase.element;
            MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
            machineModel = MirCoreImpl.machineType;
            localization.switchLocateCase(context, locateCase, machineModel);
            loadMarkerLocateConfig = MirCoreImpl.INSTANCE.loadMarkerLocateConfig((PuduLocateInterface.LocateCase) this.$locateCase.element);
            if (!loadMarkerLocateConfig) {
                MirCoreImpl mirCoreImpl2 = MirCoreImpl.INSTANCE;
                str = MirCoreImpl.TAG;
                Pdlog.m3277w(str, "load locate config files failed");
                InitServiceListener initServiceListener = this.$initListener;
                if (initServiceListener != null) {
                    initServiceListener.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "Localization config failed");
                }
                return Unit.INSTANCE;
            }
            if (((byte[]) this.$locateBytes.element) != null) {
                loadMarkerLocateMap = MirCoreImpl.INSTANCE.loadMarkerLocateMap((byte[]) this.$locateBytes.element, this.$initListener);
                if (!loadMarkerLocateMap) {
                    return Unit.INSTANCE;
                }
            } else {
                InitServiceListener initServiceListener2 = this.$initListener;
                if (initServiceListener2 != null) {
                    initServiceListener2.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "not find locate map data in package");
                }
                return Unit.INSTANCE;
            }
        } else if (i2 == 2) {
            LocalizationStub localization2 = MirCoreImpl.INSTANCE.getLocalization();
            Context context2 = MirCoreService.INSTANCE.getContext().get();
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context2, "MirCoreService.context.get()!!");
            PuduLocateInterface.LocateCase locateCase2 = (PuduLocateInterface.LocateCase) this.$locateCase.element;
            MirCoreImpl mirCoreImpl3 = MirCoreImpl.INSTANCE;
            machineModel2 = MirCoreImpl.machineType;
            localization2.switchLocateCase(context2, locateCase2, machineModel2);
            loadLaserLocateConfig = MirCoreImpl.INSTANCE.loadLaserLocateConfig();
            if (!loadLaserLocateConfig) {
                MirCoreImpl mirCoreImpl4 = MirCoreImpl.INSTANCE;
                str2 = MirCoreImpl.TAG;
                Pdlog.m3277w(str2, "load locate config files failed");
                InitServiceListener initServiceListener3 = this.$initListener;
                if (initServiceListener3 != null) {
                    initServiceListener3.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "Localization config failed");
                }
                return Unit.INSTANCE;
            }
            if (((byte[]) this.$optemapConfigBytes.element) != null && ((byte[]) this.$optemapMapBytes.element) != null) {
                loadLocateMap = MirCoreImpl.INSTANCE.loadLocateMap((PuduLocateInterface.LocateCase) this.$locateCase.element, (byte[]) this.$optemapMapBytes.element, (byte[]) this.$optemapConfigBytes.element, this.$initListener);
                if (!loadLocateMap) {
                    return Unit.INSTANCE;
                }
            } else {
                InitServiceListener initServiceListener4 = this.$initListener;
                if (initServiceListener4 != null) {
                    initServiceListener4.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "not find map pgm or yaml in map package");
                }
                return Unit.INSTANCE;
            }
        } else if (i2 == 3) {
            LocalizationStub localization3 = MirCoreImpl.INSTANCE.getLocalization();
            Context context3 = MirCoreService.INSTANCE.getContext().get();
            if (context3 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context3, "MirCoreService.context.get()!!");
            PuduLocateInterface.LocateCase locateCase3 = (PuduLocateInterface.LocateCase) this.$locateCase.element;
            MirCoreImpl mirCoreImpl5 = MirCoreImpl.INSTANCE;
            machineModel3 = MirCoreImpl.machineType;
            localization3.switchLocateCase(context3, locateCase3, machineModel3);
            loadLaserLocateConfig2 = MirCoreImpl.INSTANCE.loadLaserLocateConfig();
            if (!loadLaserLocateConfig2) {
                MirCoreImpl mirCoreImpl6 = MirCoreImpl.INSTANCE;
                str6 = MirCoreImpl.TAG;
                Pdlog.m3277w(str6, "load locate config files failed");
                InitServiceListener initServiceListener5 = this.$initListener;
                if (initServiceListener5 != null) {
                    initServiceListener5.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "Localization config failed");
                }
                return Unit.INSTANCE;
            }
            if (((byte[]) this.$optemapConfigBytes.element) != null && ((byte[]) this.$optemapMapBytes.element) != null) {
                loadMarkerLocateConfig2 = MirCoreImpl.INSTANCE.loadMarkerLocateConfig((PuduLocateInterface.LocateCase) this.$locateCase.element);
                if (!loadMarkerLocateConfig2) {
                    MirCoreImpl mirCoreImpl7 = MirCoreImpl.INSTANCE;
                    str5 = MirCoreImpl.TAG;
                    Pdlog.m3277w(str5, "load marker config files failed");
                    InitServiceListener initServiceListener6 = this.$initListener;
                    if (initServiceListener6 != null) {
                        initServiceListener6.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "Localization config failed");
                    }
                    return Unit.INSTANCE;
                }
                if (((byte[]) this.$locateBytes.element) == null || MirCoreImpl.INSTANCE.getLocalization().setLocateMap((byte[]) this.$locateBytes.element)) {
                    loadLocateMap2 = MirCoreImpl.INSTANCE.loadLocateMap((PuduLocateInterface.LocateCase) this.$locateCase.element, (byte[]) this.$optemapMapBytes.element, (byte[]) this.$optemapConfigBytes.element, this.$initListener);
                    if (!loadLocateMap2) {
                        return Unit.INSTANCE;
                    }
                    MirCoreImpl mirCoreImpl8 = MirCoreImpl.INSTANCE;
                    i = MirCoreImpl.locateCameraType;
                    if (i == 0) {
                        MirCoreImpl mirCoreImpl9 = MirCoreImpl.INSTANCE;
                        str4 = MirCoreImpl.TAG;
                        Pdlog.m3273d(str4, "registerMarkerFrameListener");
                        MirCoreImpl.INSTANCE.getLocalization().registerMarkerFrameListener(new Function2<String, IMarkerCameraData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$loadLocMapAndConfig$locateInitJob$1.1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(String str8, IMarkerCameraData iMarkerCameraData) {
                                invoke2(str8, iMarkerCameraData);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String name, IMarkerCameraData marker) {
                                HardwareLinker hardwareLinker;
                                CameraInterface camera;
                                Intrinsics.checkParameterIsNotNull(name, "name");
                                Intrinsics.checkParameterIsNotNull(marker, "marker");
                                MirCoreImpl mirCoreImpl10 = MirCoreImpl.INSTANCE;
                                hardwareLinker = MirCoreImpl.hardwareLinker;
                                HardwareInterface hardwareService = hardwareLinker.getHardwareService();
                                if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                                    return;
                                }
                                camera.addMarkerCameraListener(name, marker);
                            }
                        });
                    } else {
                        MirCoreImpl mirCoreImpl10 = MirCoreImpl.INSTANCE;
                        str3 = MirCoreImpl.TAG;
                        Pdlog.m3273d(str3, "registerMonocularCameraListener");
                        MirCoreImpl.INSTANCE.getLocalization().registerMonocularCameraListener(new Function2<String, IMarkerCameraData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirCoreImpl$loadLocMapAndConfig$locateInitJob$1.2
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(String str8, IMarkerCameraData iMarkerCameraData) {
                                invoke2(str8, iMarkerCameraData);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(String name, IMarkerCameraData camera) {
                                HardwareLinker hardwareLinker;
                                CameraInterface camera2;
                                Intrinsics.checkParameterIsNotNull(name, "name");
                                Intrinsics.checkParameterIsNotNull(camera, "camera");
                                MirCoreImpl mirCoreImpl11 = MirCoreImpl.INSTANCE;
                                hardwareLinker = MirCoreImpl.hardwareLinker;
                                HardwareInterface hardwareService = hardwareLinker.getHardwareService();
                                if (hardwareService == null || (camera2 = hardwareService.getCamera()) == null) {
                                    return;
                                }
                                camera2.addMonocularCameraListener(name, camera);
                            }
                        });
                    }
                } else {
                    InitServiceListener initServiceListener7 = this.$initListener;
                    if (initServiceListener7 != null) {
                        initServiceListener7.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "Localization marker map load failed");
                    }
                    return Unit.INSTANCE;
                }
            } else {
                InitServiceListener initServiceListener8 = this.$initListener;
                if (initServiceListener8 != null) {
                    initServiceListener8.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "not find map pgm or yaml in map package");
                }
                return Unit.INSTANCE;
            }
        } else if (i2 == 4) {
            LocalizationStub localization4 = MirCoreImpl.INSTANCE.getLocalization();
            Context context4 = MirCoreService.INSTANCE.getContext().get();
            if (context4 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context4, "MirCoreService.context.get()!!");
            PuduLocateInterface.LocateCase locateCase4 = (PuduLocateInterface.LocateCase) this.$locateCase.element;
            MirCoreImpl mirCoreImpl11 = MirCoreImpl.INSTANCE;
            machineModel4 = MirCoreImpl.machineType;
            localization4.switchLocateCase(context4, locateCase4, machineModel4);
            if (((byte[]) this.$stcmMapBytes.element) != null) {
                loadLocateMap3 = MirCoreImpl.INSTANCE.loadLocateMap((PuduLocateInterface.LocateCase) this.$locateCase.element, (byte[]) this.$stcmMapBytes.element, null, this.$initListener);
                if (!loadLocateMap3) {
                    return Unit.INSTANCE;
                }
            } else {
                InitServiceListener initServiceListener9 = this.$initListener;
                if (initServiceListener9 != null) {
                    initServiceListener9.initCoreServiceState(CoreInitStep.Localization, CoreInitState.Fail, "not find slamware map in map package");
                }
                return Unit.INSTANCE;
            }
        } else {
            MirCoreImpl mirCoreImpl12 = MirCoreImpl.INSTANCE;
            str7 = MirCoreImpl.TAG;
            Pdlog.m3277w(str7, "unknow locate case " + ((PuduLocateInterface.LocateCase) this.$locateCase.element) + " in config.json");
            return Unit.INSTANCE;
        }
        this.$initModuleList.remove(MirCoreImpl.CoreInitModules.Locate);
        return Unit.INSTANCE;
    }
}
