package com.pudutech.mirsdk.dance;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.dance.MoveHelper;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusInfo;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MoveHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.dance.MoveHelper$startJob$2", m3970f = "MoveHelper.kt", m3971i = {0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 8, 8, 8, 8}, m3972l = {53, 89, 99, 102, 124, 161, 171, 180, 195}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "watchResult", "$this$launch", "watchResult", "$this$launch", "watchResult", "$this$launch", "watchResult", "$this$launch", "navigating", "lostPositionState", "watchResult", "$this$launch", "navigating", "lostPositionState", "watchResult", "$this$launch", "watchResult", "$this$launch", "navigating", "lostPositionState", "watchResult"}, m3975s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "I$0", "I$1", "L$1", "L$0", "I$0", "I$1", "L$1", "L$0", "L$1", "L$0", "I$0", "I$1", "L$1"})
/* loaded from: classes4.dex */
public final class MoveHelper$startJob$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5783p$;
    final /* synthetic */ MoveHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveHelper$startJob$2(MoveHelper moveHelper, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveHelper$startJob$2 moveHelper$startJob$2 = new MoveHelper$startJob$2(this.this$0, completion);
        moveHelper$startJob$2.f5783p$ = (CoroutineScope) obj;
        return moveHelper$startJob$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveHelper$startJob$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0010. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:56:0x01e8. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x043b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0305 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0218  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:78:0x0303 -> B:25:0x0105). Please report as a decompilation issue!!! */
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
        Function0 function0;
        Object obj2;
        CoroutineScope coroutineScope2;
        int i;
        int i2;
        Pair pair;
        MoveHelper$startJob$2 moveHelper$startJob$2;
        CoroutineScope coroutineScope3;
        Function2 function23;
        Function2 function24;
        Function2 function25;
        String str4;
        LocalizationStatus localizationStatus2;
        Function2 function26;
        LocalizationStatus localizationStatus3;
        LocalizationStatus localizationStatus4;
        String str5;
        String str6;
        AIDLConnection aIDLConnection5;
        String str7;
        LocalizationStatus localizationStatus5;
        LocalizationStatus localizationStatus6;
        String str8;
        Function0 function02;
        String str9;
        LocalizationStatus localizationStatus7;
        int i3;
        String str10;
        LocalizationStatus localizationStatus8;
        LocalizationStatus localizationStatus9;
        Function0 function03;
        Function2 function27;
        LocalizationStatus localizationStatus10;
        LocalizationStatus localizationStatus11;
        LocalizationStatusLevel status_level;
        LocalizationInterface localizer3;
        String str11;
        String str12;
        LocalizationStatus localizationStatus12;
        Function2 function28;
        LocalizationStatus localizationStatus13;
        LocalizationStatus localizationStatus14;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5783p$;
                str = this.this$0.TAG;
                Pdlog.m3273d(str, "startJob enter async");
                MoveHelper moveHelper = this.this$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                checkAndClearWheelError = moveHelper.checkAndClearWheelError(this);
                if (checkAndClearWheelError == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (((Boolean) checkAndClearWheelError).booleanValue()) {
                    return Unit.INSTANCE;
                }
                aIDLConnection = this.this$0.coreService;
                MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
                if (mirCoreInterface == null || (localizer2 = mirCoreInterface.getLocalizer()) == null || !localizer2.isLocalizationFinishInitialization()) {
                    str2 = this.this$0.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("localizer not init finish localizer: ");
                    aIDLConnection2 = this.this$0.coreService;
                    MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection2.getInterface();
                    sb.append(mirCoreInterface2 != null ? mirCoreInterface2.getLocalizer() : null);
                    sb.append(" init state: ");
                    aIDLConnection3 = this.this$0.coreService;
                    MirCoreInterface mirCoreInterface3 = (MirCoreInterface) aIDLConnection3.getInterface();
                    sb.append((mirCoreInterface3 == null || (localizer = mirCoreInterface3.getLocalizer()) == null || (localizationStatus = localizer.getLocalizationStatus()) == null) ? null : localizationStatus.getStatus_level());
                    objArr[0] = sb.toString();
                    Pdlog.m3277w(str2, objArr);
                    function2 = this.this$0._onStateChange;
                    function2.invoke(RobotState.Error, "{\"error_type\":\"PoseNotInit\",\"level\":\"Error\",\"detail\":\"Localization Fault, can not continue task\"}");
                    return Unit.INSTANCE;
                }
                aIDLConnection4 = this.this$0.coreService;
                MirCoreInterface mirCoreInterface4 = (MirCoreInterface) aIDLConnection4.getInterface();
                if (mirCoreInterface4 == null || !mirCoreInterface4.hasCoreReady()) {
                    str3 = this.this$0.TAG;
                    Pdlog.m3277w(str3, "core not ready to run task");
                    function22 = this.this$0._onStateChange;
                    function22.invoke(RobotState.Error, "{\"error_type\":\"CoreNotReady\",\"level\":\"Error\", \"detail\":\"Core not ready, please check whether costmap updated by laser, or schedule or topomap\"}");
                    return Unit.INSTANCE;
                }
                function0 = this.this$0.actionStart;
                function0.invoke();
                obj2 = coroutine_suspended;
                coroutineScope2 = coroutineScope;
                i = 0;
                i2 = 1;
                moveHelper$startJob$2 = this;
                while (CoroutineScopeKt.isActive(coroutineScope2)) {
                    str5 = moveHelper$startJob$2.this$0.TAG;
                    Pdlog.m3273d(str5, "startJob looping, check watch dog");
                    pair = moveHelper$startJob$2.this$0.watchDogCheck();
                    str6 = moveHelper$startJob$2.this$0.TAG;
                    Pdlog.m3273d(str6, "watch result " + ((SolicitService.WatchLevel) pair.getFirst()).name());
                    int i4 = MoveHelper.WhenMappings.$EnumSwitchMapping$0[((SolicitService.WatchLevel) pair.getFirst()).ordinal()];
                    if (i4 == 1) {
                        MoveHelper moveHelper2 = moveHelper$startJob$2.this$0;
                        moveHelper$startJob$2.L$0 = coroutineScope2;
                        moveHelper$startJob$2.L$1 = pair;
                        moveHelper$startJob$2.label = 2;
                        if (moveHelper2.stopAndWaitBrake(moveHelper$startJob$2) == obj2) {
                            return obj2;
                        }
                        function23 = moveHelper$startJob$2.this$0._onStateChange;
                        function23.invoke(RobotState.Error, pair.getSecond());
                        str11 = moveHelper$startJob$2.this$0.TAG;
                        Pdlog.m3273d(str11, "navigation quit");
                        return Unit.INSTANCE;
                    }
                    if (i4 == 2) {
                        MoveHelper moveHelper3 = moveHelper$startJob$2.this$0;
                        moveHelper$startJob$2.L$0 = coroutineScope2;
                        moveHelper$startJob$2.L$1 = pair;
                        moveHelper$startJob$2.label = 3;
                        if (moveHelper3.stopAndWaitBrake(moveHelper$startJob$2) == obj2) {
                            return obj2;
                        }
                        coroutineScope3 = coroutineScope2;
                        function24 = moveHelper$startJob$2.this$0._onStateChange;
                        function24.invoke(RobotState.Error, pair.getSecond());
                        moveHelper$startJob$2.L$0 = coroutineScope3;
                        moveHelper$startJob$2.L$1 = pair;
                        moveHelper$startJob$2.label = 4;
                        if (DelayKt.delay(100L, moveHelper$startJob$2) == obj2) {
                            return obj2;
                        }
                        str11 = moveHelper$startJob$2.this$0.TAG;
                        Pdlog.m3273d(str11, "navigation quit");
                        return Unit.INSTANCE;
                    }
                    MoveHelper moveHelper4 = moveHelper$startJob$2.this$0;
                    aIDLConnection5 = moveHelper4.coreService;
                    MirCoreInterface mirCoreInterface5 = (MirCoreInterface) aIDLConnection5.getInterface();
                    moveHelper4.localizationStatus = (mirCoreInterface5 == null || (localizer3 = mirCoreInterface5.getLocalizer()) == null) ? null : localizer3.getLocalizationStatus();
                    str7 = moveHelper$startJob$2.this$0.TAG;
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("localization status ");
                    localizationStatus5 = moveHelper$startJob$2.this$0.localizationStatus;
                    sb2.append((localizationStatus5 == null || (status_level = localizationStatus5.getStatus_level()) == null) ? null : status_level.name());
                    objArr2[0] = sb2.toString();
                    Pdlog.m3273d(str7, objArr2);
                    localizationStatus6 = moveHelper$startJob$2.this$0.localizationStatus;
                    LocalizationStatusLevel status_level2 = localizationStatus6 != null ? localizationStatus6.getStatus_level() : null;
                    if (status_level2 != null) {
                        int i5 = MoveHelper.WhenMappings.$EnumSwitchMapping$3[status_level2.ordinal()];
                        if (i5 != 1) {
                            if (i5 == 2) {
                                localizationStatus7 = moveHelper$startJob$2.this$0.localizationStatus;
                                LocalizationStatusInfo status_info = localizationStatus7 != null ? localizationStatus7.getStatus_info() : null;
                                if (status_info != null && ((i3 = MoveHelper.WhenMappings.$EnumSwitchMapping$1[status_info.ordinal()]) == 1 || i3 == 2 || i3 == 3)) {
                                    if (i2 == 0) {
                                        function03 = moveHelper$startJob$2.this$0.actionStart;
                                        function03.invoke();
                                        function27 = moveHelper$startJob$2.this$0._onStateChange;
                                        RobotState robotState = RobotState.Error;
                                        StringBuilder sb3 = new StringBuilder();
                                        sb3.append("{\"error_type\":\"LostLocalization\",\"level\":\"Warn\",\"detail\":\"");
                                        localizationStatus10 = moveHelper$startJob$2.this$0.localizationStatus;
                                        sb3.append(localizationStatus10 != null ? localizationStatus10.getStatus_info() : null);
                                        sb3.append("\"}");
                                        function27.invoke(robotState, sb3.toString());
                                        i2 = 1;
                                    }
                                    str10 = moveHelper$startJob$2.this$0.TAG;
                                    Object[] objArr3 = new Object[1];
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append("{\"error_type\":\"LostLocalization\",\"level\":\"Warn\",\"detail\":\"");
                                    localizationStatus8 = moveHelper$startJob$2.this$0.localizationStatus;
                                    sb4.append(localizationStatus8 != null ? localizationStatus8.getStatus_info() : null);
                                    sb4.append(" : ");
                                    localizationStatus9 = moveHelper$startJob$2.this$0.localizationStatus;
                                    sb4.append(localizationStatus9 != null ? localizationStatus9.getStatus_description() : null);
                                    sb4.append("\"}");
                                    objArr3[0] = sb4.toString();
                                    Pdlog.m3273d(str10, objArr3);
                                }
                            } else if (i5 == 3) {
                                localizationStatus11 = moveHelper$startJob$2.this$0.localizationStatus;
                                LocalizationStatusInfo status_info2 = localizationStatus11 != null ? localizationStatus11.getStatus_info() : null;
                                if (status_info2 != null) {
                                    switch (status_info2) {
                                        case NoParam:
                                        case NoInit:
                                        case ParamError:
                                        case MapError:
                                        case NoMarker:
                                        case MarkerError:
                                            if (i2 != 0) {
                                                MoveHelper moveHelper5 = moveHelper$startJob$2.this$0;
                                                moveHelper$startJob$2.L$0 = coroutineScope2;
                                                moveHelper$startJob$2.I$0 = 0;
                                                moveHelper$startJob$2.I$1 = 1;
                                                moveHelper$startJob$2.L$1 = pair;
                                                moveHelper$startJob$2.label = 6;
                                                if (moveHelper5.stopAndWaitBrake(moveHelper$startJob$2) == obj2) {
                                                    return obj2;
                                                }
                                                i2 = 0;
                                                i = 1;
                                                function26 = moveHelper$startJob$2.this$0._onStateChange;
                                                RobotState robotState2 = RobotState.Error;
                                                StringBuilder sb5 = new StringBuilder();
                                                sb5.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                                                localizationStatus3 = moveHelper$startJob$2.this$0.localizationStatus;
                                                sb5.append(localizationStatus3 == null ? localizationStatus3.getStatus_info() : null);
                                                sb5.append("\",\"description\":\"");
                                                localizationStatus4 = moveHelper$startJob$2.this$0.localizationStatus;
                                                sb5.append(localizationStatus4 == null ? localizationStatus4.getStatus_description() : null);
                                                sb5.append("\"}");
                                                function26.invoke(robotState2, sb5.toString());
                                            }
                                            str4 = moveHelper$startJob$2.this$0.TAG;
                                            Object[] objArr4 = new Object[1];
                                            StringBuilder sb6 = new StringBuilder();
                                            sb6.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                                            localizationStatus2 = moveHelper$startJob$2.this$0.localizationStatus;
                                            sb6.append(localizationStatus2 == null ? localizationStatus2.getStatus_info() : null);
                                            sb6.append("\"}");
                                            objArr4[0] = sb6.toString();
                                            Pdlog.m3273d(str4, objArr4);
                                            moveHelper$startJob$2.L$0 = coroutineScope2;
                                            moveHelper$startJob$2.I$0 = i2;
                                            moveHelper$startJob$2.I$1 = i;
                                            moveHelper$startJob$2.L$1 = pair;
                                            moveHelper$startJob$2.label = 7;
                                            if (DelayKt.delay(100L, moveHelper$startJob$2) == obj2) {
                                                return obj2;
                                            }
                                            while (CoroutineScopeKt.isActive(coroutineScope2)) {
                                            }
                                        case LaserLocateLose:
                                            if (i2 != 0) {
                                                MoveHelper moveHelper6 = moveHelper$startJob$2.this$0;
                                                moveHelper$startJob$2.L$0 = coroutineScope2;
                                                moveHelper$startJob$2.L$1 = pair;
                                                moveHelper$startJob$2.label = 8;
                                                if (moveHelper6.stopAndWaitBrake(moveHelper$startJob$2) == obj2) {
                                                    return obj2;
                                                }
                                                function28 = moveHelper$startJob$2.this$0._onStateChange;
                                                RobotState robotState3 = RobotState.Error;
                                                StringBuilder sb7 = new StringBuilder();
                                                sb7.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                                                localizationStatus13 = moveHelper$startJob$2.this$0.localizationStatus;
                                                sb7.append(localizationStatus13 == null ? localizationStatus13.getStatus_info() : null);
                                                sb7.append("\",\"description\":\"");
                                                localizationStatus14 = moveHelper$startJob$2.this$0.localizationStatus;
                                                sb7.append(localizationStatus14 == null ? localizationStatus14.getStatus_description() : null);
                                                sb7.append("\"}");
                                                function28.invoke(robotState3, sb7.toString());
                                            }
                                            str12 = moveHelper$startJob$2.this$0.TAG;
                                            Object[] objArr5 = new Object[1];
                                            StringBuilder sb8 = new StringBuilder();
                                            sb8.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                                            localizationStatus12 = moveHelper$startJob$2.this$0.localizationStatus;
                                            sb8.append(localizationStatus12 == null ? localizationStatus12.getStatus_info() : null);
                                            sb8.append("\"}");
                                            objArr5[0] = sb8.toString();
                                            Pdlog.m3273d(str12, objArr5);
                                            str11 = moveHelper$startJob$2.this$0.TAG;
                                            Pdlog.m3273d(str11, "navigation quit");
                                            return Unit.INSTANCE;
                                    }
                                }
                            }
                        } else if (i2 == 0 && i == 0) {
                            function02 = moveHelper$startJob$2.this$0.actionStart;
                            function02.invoke();
                            str9 = moveHelper$startJob$2.this$0.TAG;
                            Pdlog.m3273d(str9, "status1 ,normal");
                            i = 0;
                            i2 = 1;
                        } else if (i2 == 0 && i != 0) {
                            str8 = moveHelper$startJob$2.this$0.TAG;
                            Pdlog.m3273d(str8, "status lost lostRetrived");
                            MoveHelper moveHelper7 = moveHelper$startJob$2.this$0;
                            moveHelper$startJob$2.L$0 = coroutineScope2;
                            moveHelper$startJob$2.L$1 = pair;
                            moveHelper$startJob$2.label = 5;
                            if (moveHelper7.stopAndWaitBrake(moveHelper$startJob$2) == obj2) {
                                return obj2;
                            }
                            function25 = moveHelper$startJob$2.this$0._onStateChange;
                            function25.invoke(RobotState.Pause, "");
                            str11 = moveHelper$startJob$2.this$0.TAG;
                            Pdlog.m3273d(str11, "navigation quit");
                            return Unit.INSTANCE;
                        }
                    }
                    moveHelper$startJob$2.L$0 = coroutineScope2;
                    moveHelper$startJob$2.I$0 = i2;
                    moveHelper$startJob$2.I$1 = i;
                    moveHelper$startJob$2.L$1 = pair;
                    moveHelper$startJob$2.label = 9;
                    if (DelayKt.delay(100L, moveHelper$startJob$2) == obj2) {
                        return obj2;
                    }
                }
                str11 = moveHelper$startJob$2.this$0.TAG;
                Pdlog.m3273d(str11, "navigation quit");
                return Unit.INSTANCE;
            case 1:
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                checkAndClearWheelError = obj;
                if (((Boolean) checkAndClearWheelError).booleanValue()) {
                }
                break;
            case 2:
                Pair pair2 = (Pair) this.L$1;
                ResultKt.throwOnFailure(obj);
                pair = pair2;
                moveHelper$startJob$2 = this;
                function23 = moveHelper$startJob$2.this$0._onStateChange;
                function23.invoke(RobotState.Error, pair.getSecond());
                str11 = moveHelper$startJob$2.this$0.TAG;
                Pdlog.m3273d(str11, "navigation quit");
                return Unit.INSTANCE;
            case 3:
                Pair pair3 = (Pair) this.L$1;
                coroutineScope3 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                pair = pair3;
                moveHelper$startJob$2 = this;
                function24 = moveHelper$startJob$2.this$0._onStateChange;
                function24.invoke(RobotState.Error, pair.getSecond());
                moveHelper$startJob$2.L$0 = coroutineScope3;
                moveHelper$startJob$2.L$1 = pair;
                moveHelper$startJob$2.label = 4;
                if (DelayKt.delay(100L, moveHelper$startJob$2) == obj2) {
                }
                str11 = moveHelper$startJob$2.this$0.TAG;
                Pdlog.m3273d(str11, "navigation quit");
                return Unit.INSTANCE;
            case 4:
                ResultKt.throwOnFailure(obj);
                moveHelper$startJob$2 = this;
                str11 = moveHelper$startJob$2.this$0.TAG;
                Pdlog.m3273d(str11, "navigation quit");
                return Unit.INSTANCE;
            case 5:
                ResultKt.throwOnFailure(obj);
                moveHelper$startJob$2 = this;
                function25 = moveHelper$startJob$2.this$0._onStateChange;
                function25.invoke(RobotState.Pause, "");
                str11 = moveHelper$startJob$2.this$0.TAG;
                Pdlog.m3273d(str11, "navigation quit");
                return Unit.INSTANCE;
            case 6:
                Pair pair4 = (Pair) this.L$1;
                int i6 = this.I$1;
                int i7 = this.I$0;
                CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                pair = pair4;
                i2 = i7;
                coroutineScope2 = coroutineScope4;
                i = i6;
                obj2 = coroutine_suspended;
                moveHelper$startJob$2 = this;
                function26 = moveHelper$startJob$2.this$0._onStateChange;
                RobotState robotState22 = RobotState.Error;
                StringBuilder sb52 = new StringBuilder();
                sb52.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                localizationStatus3 = moveHelper$startJob$2.this$0.localizationStatus;
                sb52.append(localizationStatus3 == null ? localizationStatus3.getStatus_info() : null);
                sb52.append("\",\"description\":\"");
                localizationStatus4 = moveHelper$startJob$2.this$0.localizationStatus;
                sb52.append(localizationStatus4 == null ? localizationStatus4.getStatus_description() : null);
                sb52.append("\"}");
                function26.invoke(robotState22, sb52.toString());
                str4 = moveHelper$startJob$2.this$0.TAG;
                Object[] objArr42 = new Object[1];
                StringBuilder sb62 = new StringBuilder();
                sb62.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                localizationStatus2 = moveHelper$startJob$2.this$0.localizationStatus;
                sb62.append(localizationStatus2 == null ? localizationStatus2.getStatus_info() : null);
                sb62.append("\"}");
                objArr42[0] = sb62.toString();
                Pdlog.m3273d(str4, objArr42);
                moveHelper$startJob$2.L$0 = coroutineScope2;
                moveHelper$startJob$2.I$0 = i2;
                moveHelper$startJob$2.I$1 = i;
                moveHelper$startJob$2.L$1 = pair;
                moveHelper$startJob$2.label = 7;
                if (DelayKt.delay(100L, moveHelper$startJob$2) == obj2) {
                }
                while (CoroutineScopeKt.isActive(coroutineScope2)) {
                }
                str11 = moveHelper$startJob$2.this$0.TAG;
                Pdlog.m3273d(str11, "navigation quit");
                return Unit.INSTANCE;
            case 7:
            case 9:
                int i8 = this.I$1;
                int i9 = this.I$0;
                coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                i = i8;
                i2 = i9;
                obj2 = coroutine_suspended;
                moveHelper$startJob$2 = this;
                while (CoroutineScopeKt.isActive(coroutineScope2)) {
                }
                str11 = moveHelper$startJob$2.this$0.TAG;
                Pdlog.m3273d(str11, "navigation quit");
                return Unit.INSTANCE;
            case 8:
                ResultKt.throwOnFailure(obj);
                moveHelper$startJob$2 = this;
                function28 = moveHelper$startJob$2.this$0._onStateChange;
                RobotState robotState32 = RobotState.Error;
                StringBuilder sb72 = new StringBuilder();
                sb72.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                localizationStatus13 = moveHelper$startJob$2.this$0.localizationStatus;
                sb72.append(localizationStatus13 == null ? localizationStatus13.getStatus_info() : null);
                sb72.append("\",\"description\":\"");
                localizationStatus14 = moveHelper$startJob$2.this$0.localizationStatus;
                sb72.append(localizationStatus14 == null ? localizationStatus14.getStatus_description() : null);
                sb72.append("\"}");
                function28.invoke(robotState32, sb72.toString());
                str12 = moveHelper$startJob$2.this$0.TAG;
                Object[] objArr52 = new Object[1];
                StringBuilder sb82 = new StringBuilder();
                sb82.append("{\"error_type\":\"LostLocalization\",\"level\":\"Error\",\"detail\":\"");
                localizationStatus12 = moveHelper$startJob$2.this$0.localizationStatus;
                sb82.append(localizationStatus12 == null ? localizationStatus12.getStatus_info() : null);
                sb82.append("\"}");
                objArr52[0] = sb82.toString();
                Pdlog.m3273d(str12, objArr52);
                str11 = moveHelper$startJob$2.this$0.TAG;
                Pdlog.m3273d(str11, "navigation quit");
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
