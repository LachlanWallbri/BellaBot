package com.pudutech.mirsdk;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import kotlin.Metadata;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$moveToPosition$2", m3970f = "MoveAction.kt", m3971i = {0, 1, 1, 1, 2, 2, 2}, m3972l = {1220, 1260, 1264}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "navigator", "localizer", "$this$launch", "navigator", "localizer"}, m3975s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
public final class MoveAction$moveToPosition$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MoveTaskMode $moveTaskMode;
    final /* synthetic */ Function0 $task;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5542p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$moveToPosition$2(MoveAction moveAction, Function0 function0, MoveTaskMode moveTaskMode, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
        this.$task = function0;
        this.$moveTaskMode = moveTaskMode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$moveToPosition$2 moveAction$moveToPosition$2 = new MoveAction$moveToPosition$2(this.this$0, this.$task, this.$moveTaskMode, completion);
        moveAction$moveToPosition$2.f5542p$ = (CoroutineScope) obj;
        return moveAction$moveToPosition$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$moveToPosition$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x01bc A[Catch: Exception -> 0x0209, TryCatch #0 {Exception -> 0x0209, blocks: (B:8:0x0021, B:15:0x003a, B:16:0x01b4, B:18:0x01bc, B:20:0x01bf, B:22:0x01c5, B:24:0x01c8, B:28:0x0043, B:30:0x008a, B:32:0x0090, B:34:0x0093, B:36:0x00a0, B:38:0x00a3, B:40:0x00b1, B:41:0x00b7, B:43:0x00c5, B:44:0x00c9, B:46:0x00cf, B:50:0x00d6, B:52:0x00eb, B:54:0x00ee, B:56:0x00fc, B:58:0x0108, B:60:0x010e, B:62:0x0111, B:65:0x011f, B:67:0x012d, B:69:0x0133, B:70:0x0166, B:73:0x018e, B:78:0x0137, B:80:0x0145, B:82:0x014b, B:83:0x014f, B:85:0x015d, B:87:0x0163, B:88:0x01d9, B:91:0x004d), top: B:2:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x01bf A[Catch: Exception -> 0x0209, TryCatch #0 {Exception -> 0x0209, blocks: (B:8:0x0021, B:15:0x003a, B:16:0x01b4, B:18:0x01bc, B:20:0x01bf, B:22:0x01c5, B:24:0x01c8, B:28:0x0043, B:30:0x008a, B:32:0x0090, B:34:0x0093, B:36:0x00a0, B:38:0x00a3, B:40:0x00b1, B:41:0x00b7, B:43:0x00c5, B:44:0x00c9, B:46:0x00cf, B:50:0x00d6, B:52:0x00eb, B:54:0x00ee, B:56:0x00fc, B:58:0x0108, B:60:0x010e, B:62:0x0111, B:65:0x011f, B:67:0x012d, B:69:0x0133, B:70:0x0166, B:73:0x018e, B:78:0x0137, B:80:0x0145, B:82:0x014b, B:83:0x014f, B:85:0x015d, B:87:0x0163, B:88:0x01d9, B:91:0x004d), top: B:2:0x000d }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        CoroutineScope coroutineScope;
        String str3;
        AIDLConnection aIDLConnection;
        NavigationInterface navigator;
        AIDLConnection aIDLConnection2;
        String str4;
        String str5;
        AIDLConnection aIDLConnection3;
        NavigationInterface navigator2;
        String str6;
        RobotHardware robotHardware;
        CoroutineScope coroutineScope2;
        LocalizationInterface localizationInterface;
        AIDLConnection aIDLConnection4;
        NavigationInterface navigator3;
        AIDLConnection aIDLConnection5;
        NavigationInterface navigator4;
        String str7 = "";
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Exception e) {
            this.this$0.onStateChange(SDKRobotState.Error, "{\"error_type\":\"InternalError\",\"level\":\"Error\",\"detail\":\"exception:" + e.getMessage() + "\"}");
            str = this.this$0.TAG;
            Pdlog.m3277w(str, "moveToPosition exception:", Log.getStackTraceString(e));
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5542p$;
            str3 = this.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            sb.append(currentThread.getName());
            sb.append("]enter moveToPosition coroutines");
            Pdlog.m3273d(str3, sb.toString());
            MoveAction moveAction = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (MoveAction.stopAndWaitBrake$default(moveAction, false, this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        str2 = this.this$0.TAG;
                        Pdlog.m3273d(str2, "moveToPosition end");
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                localizationInterface = (LocalizationInterface) this.L$2;
                navigator = (NavigationInterface) this.L$1;
                coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                    return Unit.INSTANCE;
                }
                MoveAction moveAction2 = this.this$0;
                this.L$0 = coroutineScope2;
                this.L$1 = navigator;
                this.L$2 = localizationInterface;
                this.label = 3;
                if (moveAction2.navigation(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str2 = this.this$0.TAG;
                Pdlog.m3273d(str2, "moveToPosition end");
                return Unit.INSTANCE;
            }
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope3;
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            this.this$0.onStateChange(SDKRobotState.Moving, "");
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                aIDLConnection = this.this$0.coreService;
                MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
                navigator = mirCoreInterface != null ? mirCoreInterface.getNavigator() : null;
                aIDLConnection2 = this.this$0.coreService;
                MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection2.getInterface();
                LocalizationInterface localizer = mirCoreInterface2 != null ? mirCoreInterface2.getLocalizer() : null;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
                if (navigator == null || localizer == null) {
                    this.this$0.onStateChange(SDKRobotState.Error, "{\"error_type\":\"InternalError\",\"level\":\"Error\",\"detail\":\"core service null\"}");
                    str4 = this.this$0.TAG;
                    Pdlog.m3277w(str4, "coreService error, navigator " + navigator + " localizer:" + localizer);
                    str2 = this.this$0.TAG;
                    Pdlog.m3273d(str2, "moveToPosition end");
                    return Unit.INSTANCE;
                }
                str5 = this.this$0.TAG;
                Pdlog.m3273d(str5, "planing path");
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
                if (!((Boolean) this.$task.invoke()).booleanValue()) {
                    this.this$0.onStateChange(SDKRobotState.Error, "{\"error_type\":\"CanNotReach\",\"level\":\"Error\"}");
                    return Unit.INSTANCE;
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    int i2 = MoveAction.WhenMappings.$EnumSwitchMapping$3[this.$moveTaskMode.ordinal()];
                    if (i2 == 1) {
                        aIDLConnection3 = this.this$0.coreService;
                        MirCoreInterface mirCoreInterface3 = (MirCoreInterface) aIDLConnection3.getInterface();
                        if (mirCoreInterface3 != null && (navigator2 = mirCoreInterface3.getNavigator()) != null) {
                            navigator2.updateSteadyFlag(true);
                        }
                    } else if (i2 != 2) {
                        aIDLConnection5 = this.this$0.coreService;
                        MirCoreInterface mirCoreInterface4 = (MirCoreInterface) aIDLConnection5.getInterface();
                        if (mirCoreInterface4 != null && (navigator4 = mirCoreInterface4.getNavigator()) != null) {
                            navigator4.updateSteadyFlag(false);
                        }
                    } else {
                        aIDLConnection4 = this.this$0.coreService;
                        MirCoreInterface mirCoreInterface5 = (MirCoreInterface) aIDLConnection4.getInterface();
                        if (mirCoreInterface5 != null && (navigator3 = mirCoreInterface5.getNavigator()) != null) {
                            navigator3.updateSteadyFlag(false);
                        }
                    }
                    this.this$0.onStateChange(SDKRobotState.Moving, "");
                    str6 = this.this$0.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("plan path finish, wheel ");
                    robotHardware = this.this$0.robotHardware;
                    if (!robotHardware.getWheelInError()) {
                        str7 = "not";
                    }
                    sb2.append(str7);
                    sb2.append(" in error");
                    objArr[0] = sb2.toString();
                    Pdlog.m3273d(str6, objArr);
                    MoveAction moveAction3 = this.this$0;
                    this.L$0 = coroutineScope;
                    this.L$1 = navigator;
                    this.L$2 = localizer;
                    this.label = 2;
                    Object checkAndClearWheelError = moveAction3.checkAndClearWheelError(this);
                    if (checkAndClearWheelError == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    LocalizationInterface localizationInterface2 = localizer;
                    coroutineScope2 = coroutineScope;
                    obj = checkAndClearWheelError;
                    localizationInterface = localizationInterface2;
                    if (((Boolean) obj).booleanValue()) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else {
                return Unit.INSTANCE;
            }
        } else {
            return Unit.INSTANCE;
        }
    }
}
