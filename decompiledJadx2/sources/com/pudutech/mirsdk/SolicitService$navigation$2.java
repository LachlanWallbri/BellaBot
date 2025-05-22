package com.pudutech.mirsdk;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusInfo;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SolicitService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SolicitService$navigation$2", m3970f = "SolicitService.kt", m3971i = {0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3}, m3972l = {456, 509, 587, TypedValues.Motion.TYPE_QUANTIZE_MOTIONSTEPS}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "navigating", "lostPositionState", "watchResult", "$this$launch", "navigating", "lostPositionState", "watchResult", "$this$launch", "navigating", "lostPositionState", "watchResult"}, m3975s = {"L$0", "L$0", "I$0", "I$1", "L$1", "L$0", "I$0", "I$1", "L$1", "L$0", "I$0", "I$1", "L$1"})
/* loaded from: classes4.dex */
public final class SolicitService$navigation$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5592p$;
    final /* synthetic */ SolicitService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SolicitService$navigation$2(SolicitService solicitService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = solicitService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SolicitService$navigation$2 solicitService$navigation$2 = new SolicitService$navigation$2(this.this$0, completion);
        solicitService$navigation$2.f5592p$ = (CoroutineScope) obj;
        return solicitService$navigation$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SolicitService$navigation$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:53:0x01ee. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0454 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x04b5 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:119:0x0452 -> B:8:0x002a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x0300 -> B:9:0x0301). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        Object checkAndClearWheelError;
        AIDLConnection aIDLConnection;
        String str2;
        AIDLConnection aIDLConnection2;
        AIDLConnection aIDLConnection3;
        Function2 function2;
        LocalizationInterface localizer;
        LocalizationStatus localizationStatus;
        LocalizationInterface localizer2;
        AIDLConnection aIDLConnection4;
        String str3;
        Function2 function22;
        AIDLConnection aIDLConnection5;
        AIDLConnection aIDLConnection6;
        Object obj2;
        CoroutineScope coroutineScope2;
        int i;
        int i2;
        SolicitService$navigation$2 solicitService$navigation$2;
        SolicitService$personDetectListener$1 solicitService$personDetectListener$1;
        int i3;
        int i4;
        String str4;
        String str5;
        Pair watchDogCheck;
        String str6;
        Function2 function23;
        AIDLConnection aIDLConnection7;
        String str7;
        LocalizationStatus localizationStatus2;
        LocalizationStatus localizationStatus3;
        String str8;
        Function2 function24;
        AIDLConnection aIDLConnection8;
        AIDLConnection aIDLConnection9;
        String str9;
        SolicitService$personDetectListener$1 solicitService$personDetectListener$12;
        LocalizationStatus localizationStatus4;
        int i5;
        String str10;
        LocalizationStatus localizationStatus5;
        LocalizationStatus localizationStatus6;
        AIDLConnection aIDLConnection10;
        AIDLConnection aIDLConnection11;
        Function2 function25;
        LocalizationStatus localizationStatus7;
        SolicitService$personDetectListener$1 solicitService$personDetectListener$13;
        LocalizationStatus localizationStatus8;
        String str11;
        LocalizationStatus localizationStatus9;
        Function2 function26;
        LocalizationStatus localizationStatus10;
        LocalizationStatus localizationStatus11;
        String str12;
        LocalizationStatus localizationStatus12;
        Function2 function27;
        LocalizationStatus localizationStatus13;
        LocalizationStatus localizationStatus14;
        LocalizationStatusLevel status_level;
        LocalizationInterface localizer3;
        Function2 function28;
        RobotHardware robotHardware;
        SolicitService.Companion unused;
        SolicitService.Companion unused2;
        SolicitService.Companion unused3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i6 = this.label;
        int i7 = 2;
        if (i6 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5592p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "navigation enter async");
            SolicitService solicitService = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            checkAndClearWheelError = solicitService.checkAndClearWheelError(this);
            if (checkAndClearWheelError == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i6 != 1) {
                if (i6 == 2) {
                    int i8 = this.I$1;
                    int i9 = this.I$0;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj2 = coroutine_suspended;
                    solicitService$navigation$2 = this;
                    i = i8;
                    i2 = i9;
                    do {
                        if (CoroutineScopeKt.isActive(coroutineScope2)) {
                        }
                        str4 = solicitService$navigation$2.this$0.TAG;
                        Pdlog.m3273d(str4, "navigation quit");
                        return Unit.INSTANCE;
                    } while (DelayKt.delay(100L, solicitService$navigation$2) != obj2);
                    return obj2;
                }
                if (i6 == 3) {
                    i4 = this.I$1;
                    i3 = this.I$0;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj2 = coroutine_suspended;
                    solicitService$navigation$2 = this;
                    int i10 = i3;
                    i = i4;
                    i2 = i10;
                    i7 = 2;
                    do {
                        if (CoroutineScopeKt.isActive(coroutineScope2)) {
                        }
                        str4 = solicitService$navigation$2.this$0.TAG;
                        Pdlog.m3273d(str4, "navigation quit");
                        return Unit.INSTANCE;
                    } while (DelayKt.delay(100L, solicitService$navigation$2) != obj2);
                    return obj2;
                }
                if (i6 != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i11 = this.I$1;
                int i12 = this.I$0;
                coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                solicitService$navigation$2 = this;
                int i13 = i12;
                i = i11;
                i2 = i13;
                i7 = 2;
                do {
                    if (CoroutineScopeKt.isActive(coroutineScope2)) {
                        str5 = solicitService$navigation$2.this$0.TAG;
                        Pdlog.m3273d(str5, "navigation looping, check watch dog");
                        watchDogCheck = solicitService$navigation$2.this$0.watchDogCheck();
                        str6 = solicitService$navigation$2.this$0.TAG;
                        Pdlog.m3273d(str6, "watch result " + ((SolicitService.WatchLevel) watchDogCheck.getFirst()).name());
                        int i14 = SolicitService.WhenMappings.$EnumSwitchMapping$1[((SolicitService.WatchLevel) watchDogCheck.getFirst()).ordinal()];
                        if (i14 == 1) {
                            if (i2 != 0) {
                                solicitService$navigation$2.this$0.stop();
                            }
                            solicitService$navigation$2.this$0.brakeUntilStop();
                            function23 = solicitService$navigation$2.this$0._onStateChange;
                            function23.invoke(RobotState.Error, watchDogCheck.getSecond());
                        } else if (i14 == i7) {
                            if (i2 != 0) {
                                solicitService$navigation$2.this$0.stop();
                                i2 = 0;
                            }
                            function28 = solicitService$navigation$2.this$0._onStateChange;
                            function28.invoke(RobotState.Error, watchDogCheck.getSecond());
                            robotHardware = solicitService$navigation$2.this$0.robotHardware;
                            HardwareInterface hardwareInterface = robotHardware.getInterface();
                            if (hardwareInterface != null) {
                                hardwareInterface.controlWheel(0.0d, 0.0d, true);
                            }
                            solicitService$navigation$2.L$0 = coroutineScope2;
                            solicitService$navigation$2.I$0 = i2;
                            solicitService$navigation$2.I$1 = i;
                            solicitService$navigation$2.L$1 = watchDogCheck;
                            i7 = 2;
                            solicitService$navigation$2.label = 2;
                        } else {
                            SolicitService solicitService2 = solicitService$navigation$2.this$0;
                            aIDLConnection7 = solicitService2.coreService;
                            MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection7.getInterface();
                            solicitService2.localizationStatus = (mirCoreInterface == null || (localizer3 = mirCoreInterface.getLocalizer()) == null) ? null : localizer3.getLocalizationStatus();
                            str7 = solicitService$navigation$2.this$0.TAG;
                            Object[] objArr = new Object[1];
                            StringBuilder sb = new StringBuilder();
                            sb.append("localization status ");
                            localizationStatus2 = solicitService$navigation$2.this$0.localizationStatus;
                            sb.append((localizationStatus2 == null || (status_level = localizationStatus2.getStatus_level()) == null) ? null : status_level.name());
                            objArr[0] = sb.toString();
                            Pdlog.m3273d(str7, objArr);
                            localizationStatus3 = solicitService$navigation$2.this$0.localizationStatus;
                            LocalizationStatusLevel status_level2 = localizationStatus3 != null ? localizationStatus3.getStatus_level() : null;
                            if (status_level2 != null) {
                                int i15 = SolicitService.WhenMappings.$EnumSwitchMapping$4[status_level2.ordinal()];
                                if (i15 != 1) {
                                    if (i15 != i7) {
                                        if (i15 == 3) {
                                            localizationStatus8 = solicitService$navigation$2.this$0.localizationStatus;
                                            LocalizationStatusInfo status_info = localizationStatus8 != null ? localizationStatus8.getStatus_info() : null;
                                            if (status_info != null) {
                                                switch (status_info) {
                                                    case NoParam:
                                                    case NoInit:
                                                    case ParamError:
                                                    case MapError:
                                                    case NoMarker:
                                                    case MarkerError:
                                                        if (i2 != 0) {
                                                            solicitService$navigation$2.this$0.stop();
                                                            solicitService$navigation$2.this$0.brakeUntilStop();
                                                            function26 = solicitService$navigation$2.this$0._onStateChange;
                                                            RobotState robotState = RobotState.Error;
                                                            StringBuilder sb2 = new StringBuilder();
                                                            sb2.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                                                            localizationStatus10 = solicitService$navigation$2.this$0.localizationStatus;
                                                            sb2.append(localizationStatus10 != null ? localizationStatus10.getStatus_info() : null);
                                                            sb2.append("\",\"description\":\"");
                                                            localizationStatus11 = solicitService$navigation$2.this$0.localizationStatus;
                                                            sb2.append(localizationStatus11 != null ? localizationStatus11.getStatus_description() : null);
                                                            sb2.append("\"}");
                                                            function26.invoke(robotState, sb2.toString());
                                                            i3 = 0;
                                                            i4 = 1;
                                                        } else {
                                                            int i16 = i;
                                                            i3 = i2;
                                                            i4 = i16;
                                                        }
                                                        str11 = solicitService$navigation$2.this$0.TAG;
                                                        Object[] objArr2 = new Object[1];
                                                        StringBuilder sb3 = new StringBuilder();
                                                        sb3.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                                                        localizationStatus9 = solicitService$navigation$2.this$0.localizationStatus;
                                                        sb3.append(localizationStatus9 != null ? localizationStatus9.getStatus_info() : null);
                                                        sb3.append("\"}");
                                                        objArr2[0] = sb3.toString();
                                                        Pdlog.m3273d(str11, objArr2);
                                                        solicitService$navigation$2.L$0 = coroutineScope2;
                                                        solicitService$navigation$2.I$0 = i3;
                                                        solicitService$navigation$2.I$1 = i4;
                                                        solicitService$navigation$2.L$1 = watchDogCheck;
                                                        solicitService$navigation$2.label = 3;
                                                        if (DelayKt.delay(100L, solicitService$navigation$2) == obj2) {
                                                            return obj2;
                                                        }
                                                        int i102 = i3;
                                                        i = i4;
                                                        i2 = i102;
                                                        i7 = 2;
                                                        if (CoroutineScopeKt.isActive(coroutineScope2)) {
                                                            break;
                                                        }
                                                        break;
                                                    case LaserLocateLose:
                                                        if (i2 != 0) {
                                                            solicitService$navigation$2.this$0.stop();
                                                            solicitService$navigation$2.this$0.brakeUntilStop();
                                                            function27 = solicitService$navigation$2.this$0._onStateChange;
                                                            RobotState robotState2 = RobotState.Error;
                                                            StringBuilder sb4 = new StringBuilder();
                                                            sb4.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                                                            localizationStatus13 = solicitService$navigation$2.this$0.localizationStatus;
                                                            sb4.append(localizationStatus13 != null ? localizationStatus13.getStatus_info() : null);
                                                            sb4.append("\",\"description\":\"");
                                                            localizationStatus14 = solicitService$navigation$2.this$0.localizationStatus;
                                                            sb4.append(localizationStatus14 != null ? localizationStatus14.getStatus_description() : null);
                                                            sb4.append("\"}");
                                                            function27.invoke(robotState2, sb4.toString());
                                                        }
                                                        str12 = solicitService$navigation$2.this$0.TAG;
                                                        Object[] objArr3 = new Object[1];
                                                        StringBuilder sb5 = new StringBuilder();
                                                        sb5.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                                                        localizationStatus12 = solicitService$navigation$2.this$0.localizationStatus;
                                                        sb5.append(localizationStatus12 != null ? localizationStatus12.getStatus_info() : null);
                                                        sb5.append("\"}");
                                                        objArr3[0] = sb5.toString();
                                                        Pdlog.m3273d(str12, objArr3);
                                                        break;
                                                }
                                            }
                                        }
                                    } else {
                                        localizationStatus4 = solicitService$navigation$2.this$0.localizationStatus;
                                        LocalizationStatusInfo status_info2 = localizationStatus4 != null ? localizationStatus4.getStatus_info() : null;
                                        if (status_info2 != null && ((i5 = SolicitService.WhenMappings.$EnumSwitchMapping$2[status_info2.ordinal()]) == 1 || i5 == 2 || i5 == 3)) {
                                            if (i2 == 0) {
                                                SolicitService solicitService3 = solicitService$navigation$2.this$0;
                                                unused = SolicitService.INSTANCE;
                                                solicitService3.solicitState = (byte) 1;
                                                aIDLConnection10 = solicitService$navigation$2.this$0.coreService;
                                                MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection10.getInterface();
                                                if (mirCoreInterface2 != null) {
                                                    solicitService$personDetectListener$13 = solicitService$navigation$2.this$0.personDetectListener;
                                                    mirCoreInterface2.addPersonListener("personDetect", solicitService$personDetectListener$13);
                                                }
                                                aIDLConnection11 = solicitService$navigation$2.this$0.coreService;
                                                MirCoreInterface mirCoreInterface3 = (MirCoreInterface) aIDLConnection11.getInterface();
                                                if (mirCoreInterface3 != null) {
                                                    mirCoreInterface3.enablePersonDetect(true);
                                                }
                                                function25 = solicitService$navigation$2.this$0._onStateChange;
                                                RobotState robotState3 = RobotState.Error;
                                                StringBuilder sb6 = new StringBuilder();
                                                sb6.append("{\"error_type\":\"LostLocalization\",\"level\":\"Warn\",\"detail\":\"");
                                                localizationStatus7 = solicitService$navigation$2.this$0.localizationStatus;
                                                sb6.append(localizationStatus7 != null ? localizationStatus7.getStatus_info() : null);
                                                sb6.append("\"}");
                                                function25.invoke(robotState3, sb6.toString());
                                                i2 = 1;
                                            }
                                            str10 = solicitService$navigation$2.this$0.TAG;
                                            Object[] objArr4 = new Object[1];
                                            StringBuilder sb7 = new StringBuilder();
                                            sb7.append("{\"error_type\":\"LostLocalization\",\"level\":\"Warn\",\"detail\":\"");
                                            localizationStatus5 = solicitService$navigation$2.this$0.localizationStatus;
                                            sb7.append(localizationStatus5 != null ? localizationStatus5.getStatus_info() : null);
                                            sb7.append(" : ");
                                            localizationStatus6 = solicitService$navigation$2.this$0.localizationStatus;
                                            sb7.append(localizationStatus6 != null ? localizationStatus6.getStatus_description() : null);
                                            sb7.append("\"}");
                                            objArr4[0] = sb7.toString();
                                            Pdlog.m3273d(str10, objArr4);
                                        }
                                    }
                                } else if (i2 == 0 && i == 0) {
                                    SolicitService solicitService4 = solicitService$navigation$2.this$0;
                                    unused2 = SolicitService.INSTANCE;
                                    solicitService4.solicitState = (byte) 1;
                                    aIDLConnection8 = solicitService$navigation$2.this$0.coreService;
                                    MirCoreInterface mirCoreInterface4 = (MirCoreInterface) aIDLConnection8.getInterface();
                                    if (mirCoreInterface4 != null) {
                                        solicitService$personDetectListener$12 = solicitService$navigation$2.this$0.personDetectListener;
                                        mirCoreInterface4.addPersonListener("personDetect", solicitService$personDetectListener$12);
                                    }
                                    aIDLConnection9 = solicitService$navigation$2.this$0.coreService;
                                    MirCoreInterface mirCoreInterface5 = (MirCoreInterface) aIDLConnection9.getInterface();
                                    if (mirCoreInterface5 != null) {
                                        mirCoreInterface5.enablePersonDetect(true);
                                    }
                                    str9 = solicitService$navigation$2.this$0.TAG;
                                    Pdlog.m3273d(str9, "status1 ,normal");
                                    i11 = 0;
                                    i12 = 1;
                                } else if (i2 == 0 && i != 0) {
                                    str8 = solicitService$navigation$2.this$0.TAG;
                                    Pdlog.m3273d(str8, "status lost lostRetrived");
                                    solicitService$navigation$2.this$0.stop();
                                    function24 = solicitService$navigation$2.this$0._onStateChange;
                                    function24.invoke(RobotState.Pause, "");
                                }
                                solicitService$navigation$2.L$0 = coroutineScope2;
                                solicitService$navigation$2.I$0 = i12;
                                solicitService$navigation$2.I$1 = i11;
                                solicitService$navigation$2.L$1 = watchDogCheck;
                                solicitService$navigation$2.label = 4;
                                if (DelayKt.delay(100L, solicitService$navigation$2) == obj2) {
                                    return obj2;
                                }
                                int i132 = i12;
                                i = i11;
                                i2 = i132;
                                i7 = 2;
                                if (CoroutineScopeKt.isActive(coroutineScope2)) {
                                }
                            }
                            int i17 = i;
                            i12 = i2;
                            i11 = i17;
                            solicitService$navigation$2.L$0 = coroutineScope2;
                            solicitService$navigation$2.I$0 = i12;
                            solicitService$navigation$2.I$1 = i11;
                            solicitService$navigation$2.L$1 = watchDogCheck;
                            solicitService$navigation$2.label = 4;
                            if (DelayKt.delay(100L, solicitService$navigation$2) == obj2) {
                            }
                            int i1322 = i12;
                            i = i11;
                            i2 = i1322;
                            i7 = 2;
                            if (CoroutineScopeKt.isActive(coroutineScope2)) {
                            }
                        }
                    }
                    str4 = solicitService$navigation$2.this$0.TAG;
                    Pdlog.m3273d(str4, "navigation quit");
                    return Unit.INSTANCE;
                } while (DelayKt.delay(100L, solicitService$navigation$2) != obj2);
                return obj2;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            checkAndClearWheelError = obj;
        }
        if (!((Boolean) checkAndClearWheelError).booleanValue()) {
            return Unit.INSTANCE;
        }
        aIDLConnection = this.this$0.coreService;
        MirCoreInterface mirCoreInterface6 = (MirCoreInterface) aIDLConnection.getInterface();
        if (mirCoreInterface6 == null || (localizer2 = mirCoreInterface6.getLocalizer()) == null || !localizer2.isLocalizationFinishInitialization()) {
            str2 = this.this$0.TAG;
            Object[] objArr5 = new Object[1];
            StringBuilder sb8 = new StringBuilder();
            sb8.append("localizer not init finish localizer: ");
            aIDLConnection2 = this.this$0.coreService;
            MirCoreInterface mirCoreInterface7 = (MirCoreInterface) aIDLConnection2.getInterface();
            sb8.append(mirCoreInterface7 != null ? mirCoreInterface7.getLocalizer() : null);
            sb8.append(" init state: ");
            aIDLConnection3 = this.this$0.coreService;
            MirCoreInterface mirCoreInterface8 = (MirCoreInterface) aIDLConnection3.getInterface();
            sb8.append((mirCoreInterface8 == null || (localizer = mirCoreInterface8.getLocalizer()) == null || (localizationStatus = localizer.getLocalizationStatus()) == null) ? null : localizationStatus.getStatus_level());
            objArr5[0] = sb8.toString();
            Pdlog.m3277w(str2, objArr5);
            function2 = this.this$0._onStateChange;
            function2.invoke(RobotState.Error, "{\"error_type\":\"PoseNotInit\",\"level\":\"Error\",\"detail\":\"Localization Fault, can not continue task\"}");
            return Unit.INSTANCE;
        }
        aIDLConnection4 = this.this$0.coreService;
        MirCoreInterface mirCoreInterface9 = (MirCoreInterface) aIDLConnection4.getInterface();
        if (mirCoreInterface9 == null || !mirCoreInterface9.hasCoreReady()) {
            str3 = this.this$0.TAG;
            Pdlog.m3277w(str3, "core not ready to run task");
            function22 = this.this$0._onStateChange;
            function22.invoke(RobotState.Error, "{\"error_type\":\"CoreNotReady\",\"level\":\"Error\", \"detail\":\"Core not ready, please check whether costmap updated by laser, or schedule or topomap\"}");
            return Unit.INSTANCE;
        }
        SolicitService solicitService5 = this.this$0;
        unused3 = SolicitService.INSTANCE;
        solicitService5.solicitState = (byte) 1;
        aIDLConnection5 = this.this$0.coreService;
        MirCoreInterface mirCoreInterface10 = (MirCoreInterface) aIDLConnection5.getInterface();
        if (mirCoreInterface10 != null) {
            solicitService$personDetectListener$1 = this.this$0.personDetectListener;
            mirCoreInterface10.addPersonListener("personDetect", solicitService$personDetectListener$1);
        }
        aIDLConnection6 = this.this$0.coreService;
        MirCoreInterface mirCoreInterface11 = (MirCoreInterface) aIDLConnection6.getInterface();
        if (mirCoreInterface11 != null) {
            mirCoreInterface11.enablePersonDetect(true);
        }
        obj2 = coroutine_suspended;
        coroutineScope2 = coroutineScope;
        i = 0;
        i2 = 1;
        solicitService$navigation$2 = this;
        do {
            if (CoroutineScopeKt.isActive(coroutineScope2)) {
            }
            str4 = solicitService$navigation$2.this$0.TAG;
            Pdlog.m3273d(str4, "navigation quit");
            return Unit.INSTANCE;
        } while (DelayKt.delay(100L, solicitService$navigation$2) != obj2);
        return obj2;
    }
}
