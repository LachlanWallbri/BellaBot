package com.pudutech.mirsdk.activity;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.freeinstall_wrapper.Constant;
import com.pudutech.mirsdk.activity.SDKServiceConnection;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.RobotAction;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.aidl.serialize.SwitchMapResult;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKServiceConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0085\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001;\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001EB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u000e\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020?J\u0006\u0010@\u001a\u00020\u0013J\u001c\u0010A\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010B2\b\u0010C\u001a\u0004\u0018\u00010DH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bRN\u0010\f\u001a6\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017RN\u0010\u0018\u001a6\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017Ra\u0010\u001b\u001aI\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$RL\u0010%\u001a4\u0012\u0013\u0012\u00110&¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b('\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u0013\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0015\"\u0004\b)\u0010\u0017RL\u0010*\u001a4\u0012\u0013\u0012\u00110+¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0013\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0015\"\u0004\b.\u0010\u0017R\u0011\u0010/\u001a\u000200¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R7\u00103\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(5\u0012\u0004\u0012\u00020\u0013\u0018\u000104X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0010\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0004\n\u0002\u0010<¨\u0006F"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/SDKServiceConnection;", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/aidl/SDKInterface;", "()V", "TAG", "", "chargeStateStore", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "getChargeStateStore", "()Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "setChargeStateStore", "(Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;)V", "onAddChargePointResult", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", SpeechUtility.TAG_RESOURCE_RESULT, TmpConstant.SERVICE_DESC, "", "getOnAddChargePointResult", "()Lkotlin/jvm/functions/Function2;", "setOnAddChargePointResult", "(Lkotlin/jvm/functions/Function2;)V", "onAddRelocatePointResult", "getOnAddRelocatePointResult", "setOnAddRelocatePointResult", "onInitStepResult", "Lkotlin/Function3;", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "state", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "stepState", "getOnInitStepResult", "()Lkotlin/jvm/functions/Function3;", "setOnInitStepResult", "(Lkotlin/jvm/functions/Function3;)V", "onStateChange", "Lcom/pudutech/mirsdk/activity/SDKServiceConnection$DataType;", "type", "getOnStateChange", "setOnStateChange", "onSwitchMapResult", "Lcom/pudutech/mirsdk/aidl/serialize/SwitchMapResult;", "map_name", "getOnSwitchMapResult", "setOnSwitchMapResult", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getPose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "securityCallback", "Lkotlin/Function1;", "flag", "getSecurityCallback", "()Lkotlin/jvm/functions/Function1;", "setSecurityCallback", "(Lkotlin/jvm/functions/Function1;)V", "stateListener", "com/pudutech/mirsdk/activity/SDKServiceConnection$stateListener$1", "Lcom/pudutech/mirsdk/activity/SDKServiceConnection$stateListener$1;", "disconnectConnection", "context", "Landroid/content/Context;", "init", "onServiceConnected", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "DataType", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SDKServiceConnection extends AIDLConnection<SDKInterface> {
    private static Function2<? super Boolean, ? super String, Unit> onAddChargePointResult;
    private static Function2<? super Boolean, ? super String, Unit> onAddRelocatePointResult;
    private static Function3<? super InitStep, ? super StepState, ? super String, Unit> onInitStepResult;
    private static Function2<? super DataType, ? super String, Unit> onStateChange;
    private static Function2<? super SwitchMapResult, ? super String, Unit> onSwitchMapResult;
    private static Function1<? super Boolean, Unit> securityCallback;
    public static final SDKServiceConnection INSTANCE = new SDKServiceConnection();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final Vector3d pose = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private static ChargeState chargeStateStore = ChargeState.Idle;
    private static final SDKServiceConnection$stateListener$1 stateListener = new ISDKListener.Stub() { // from class: com.pudutech.mirsdk.activity.SDKServiceConnection$stateListener$1
        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onAction(RobotAction action, String description) {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3275i(str, "onAction action:" + action + ' ' + description);
            Function2<SDKServiceConnection.DataType, String, Unit> onStateChange2 = SDKServiceConnection.INSTANCE.getOnStateChange();
            if (onStateChange2 != null) {
                onStateChange2.invoke(SDKServiceConnection.DataType.State, "state: onAction " + action + ' ' + description);
            }
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onInitStep(InitStep step, StepState state, String description) {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3275i(str, "onInitStep action:" + step + ' ' + state + ' ' + description);
            Function2<SDKServiceConnection.DataType, String, Unit> onStateChange2 = SDKServiceConnection.INSTANCE.getOnStateChange();
            if (onStateChange2 != null) {
                onStateChange2.invoke(SDKServiceConnection.DataType.State, "state:onInitStep " + step + ' ' + state + ' ' + description);
            }
            Function3<InitStep, StepState, String, Unit> onInitStepResult2 = SDKServiceConnection.INSTANCE.getOnInitStepResult();
            if (onInitStepResult2 != null) {
                if (step == null) {
                    Intrinsics.throwNpe();
                }
                if (state == null) {
                    Intrinsics.throwNpe();
                }
                if (description == null) {
                    description = "";
                }
                onInitStepResult2.invoke(step, state, description);
            }
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onSwitchPdmapResult(SwitchMapResult result, String map_name) {
            String str;
            Intrinsics.checkParameterIsNotNull(result, "result");
            Intrinsics.checkParameterIsNotNull(map_name, "map_name");
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3273d(str, "switch pdmap result " + result.name());
            Function2<SwitchMapResult, String, Unit> onSwitchMapResult2 = SDKServiceConnection.INSTANCE.getOnSwitchMapResult();
            if (onSwitchMapResult2 != null) {
                onSwitchMapResult2.invoke(result, map_name);
            }
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onStateChange(RobotState state, String description) {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3275i(str, "onStateChange action:" + state + ' ' + description);
            Function2<SDKServiceConnection.DataType, String, Unit> onStateChange2 = SDKServiceConnection.INSTANCE.getOnStateChange();
            if (onStateChange2 != null) {
                onStateChange2.invoke(SDKServiceConnection.DataType.State, "state:onStateChange " + state + ' ' + description);
            }
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onPose(double x, double y, double angular) {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3276v(str, "onPose :" + x + ',' + y + ',' + angular);
            SDKServiceConnection.INSTANCE.getPose().setX(x);
            SDKServiceConnection.INSTANCE.getPose().setY(y);
            SDKServiceConnection.INSTANCE.getPose().setZ(angular);
            Function2<SDKServiceConnection.DataType, String, Unit> onStateChange2 = SDKServiceConnection.INSTANCE.getOnStateChange();
            if (onStateChange2 != null) {
                onStateChange2.invoke(SDKServiceConnection.DataType.Pose, "pose: " + CommonKt.format(x, 3) + ", " + CommonKt.format(y, 3) + " orientation:" + CommonKt.format(angular, 3));
            }
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onBattery(int percent, ChargeState chargeState) {
            Function2<SDKServiceConnection.DataType, String, Unit> onStateChange2 = SDKServiceConnection.INSTANCE.getOnStateChange();
            if (onStateChange2 != null) {
                onStateChange2.invoke(SDKServiceConnection.DataType.Battery, percent + "% " + chargeState);
            }
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            if (chargeState == null) {
                chargeState = ChargeState.Idle;
            }
            sDKServiceConnection.setChargeStateStore(chargeState);
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onSpeed(double line, double angular) {
            Function2<SDKServiceConnection.DataType, String, Unit> onStateChange2 = SDKServiceConnection.INSTANCE.getOnStateChange();
            if (onStateChange2 != null) {
                onStateChange2.invoke(SDKServiceConnection.DataType.Speed, "speed line:" + CommonKt.format(line, 3) + " angular:" + CommonKt.format(angular, 3));
            }
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onSchedulerCount(int p0) {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3273d(str, "scheduler count " + p0);
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onGoalCruisePath() {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3273d(str, "move on goal cruise path");
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onFaceDetectResult(int p0, double p1, double p2, double p3) {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3273d(str, "face detect " + p0 + ", " + p1 + ", " + p2 + ", " + p3);
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onAddRelocationPointResult(boolean p0, String p1) {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3273d(str, "add relocation point result " + p0 + ", " + p1);
            Function2<Boolean, String, Unit> onAddRelocatePointResult2 = SDKServiceConnection.INSTANCE.getOnAddRelocatePointResult();
            if (onAddRelocatePointResult2 != null) {
                onAddRelocatePointResult2.invoke(Boolean.valueOf(p0), p1);
            }
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onAddChargePointResult(boolean p0, String p1) {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3273d(str, "add charge point  result " + p0 + ", " + p1);
            Function2<Boolean, String, Unit> onAddChargePointResult2 = SDKServiceConnection.INSTANCE.getOnAddChargePointResult();
            if (onAddChargePointResult2 != null) {
                onAddChargePointResult2.invoke(Boolean.valueOf(p0), p1);
            }
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onBatteryFloorLevelLimitResult(int p0, int p1) {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3273d(str, "add battery floor level limit result " + p0 + ", " + p1);
            Function2<SDKServiceConnection.DataType, String, Unit> onStateChange2 = SDKServiceConnection.INSTANCE.getOnStateChange();
            if (onStateChange2 != null) {
                onStateChange2.invoke(SDKServiceConnection.DataType.BatteryLevel, "level: " + p1 + '%');
            }
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void securityFeedback(boolean p0) {
            Function1<Boolean, Unit> securityCallback2 = SDKServiceConnection.INSTANCE.getSecurityCallback();
            if (securityCallback2 != null) {
                securityCallback2.invoke(Boolean.valueOf(p0));
            }
        }

        @Override // com.pudutech.mirsdk.aidl.ISDKListener
        public void onStuckReplan() {
            String str;
            SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
            str = SDKServiceConnection.TAG;
            Pdlog.m3273d(str, "onStuckReplan");
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: SDKServiceConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/SDKServiceConnection$DataType;", "", "(Ljava/lang/String;I)V", "State", "Pose", "Battery", "Speed", "BatteryLevel", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum DataType {
        State,
        Pose,
        Battery,
        Speed,
        BatteryLevel
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: SDKServiceConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012,\u0010\u0003\u001a( \u0002*\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/mirsdk/aidl/SDKInterface;", "kotlin.jvm.PlatformType", "p1", "Landroid/os/IBinder;", "Lkotlin/ParameterName;", "name", "p0", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.activity.SDKServiceConnection$1 */
    /* loaded from: classes5.dex */
    static final /* synthetic */ class C48791 extends FunctionReference implements Function1<IBinder, SDKInterface> {
        public static final C48791 INSTANCE = new C48791();

        C48791() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "asInterface";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(SDKInterface.Stub.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "asInterface(Landroid/os/IBinder;)Lcom/pudutech/mirsdk/aidl/SDKInterface;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final SDKInterface invoke(IBinder iBinder) {
            return SDKInterface.Stub.asInterface(iBinder);
        }
    }

    private SDKServiceConnection() {
        super(Constant.mirsdkServer, C48791.INSTANCE, null, 4, null);
    }

    public final Function2<DataType, String, Unit> getOnStateChange() {
        return onStateChange;
    }

    public final void setOnStateChange(Function2<? super DataType, ? super String, Unit> function2) {
        onStateChange = function2;
    }

    public final Function3<InitStep, StepState, String, Unit> getOnInitStepResult() {
        return onInitStepResult;
    }

    public final void setOnInitStepResult(Function3<? super InitStep, ? super StepState, ? super String, Unit> function3) {
        onInitStepResult = function3;
    }

    public final Function2<SwitchMapResult, String, Unit> getOnSwitchMapResult() {
        return onSwitchMapResult;
    }

    public final void setOnSwitchMapResult(Function2<? super SwitchMapResult, ? super String, Unit> function2) {
        onSwitchMapResult = function2;
    }

    public final Function2<Boolean, String, Unit> getOnAddRelocatePointResult() {
        return onAddRelocatePointResult;
    }

    public final void setOnAddRelocatePointResult(Function2<? super Boolean, ? super String, Unit> function2) {
        onAddRelocatePointResult = function2;
    }

    public final Function2<Boolean, String, Unit> getOnAddChargePointResult() {
        return onAddChargePointResult;
    }

    public final void setOnAddChargePointResult(Function2<? super Boolean, ? super String, Unit> function2) {
        onAddChargePointResult = function2;
    }

    public final Vector3d getPose() {
        return pose;
    }

    public final ChargeState getChargeStateStore() {
        return chargeStateStore;
    }

    public final void setChargeStateStore(ChargeState chargeState) {
        Intrinsics.checkParameterIsNotNull(chargeState, "<set-?>");
        chargeStateStore = chargeState;
    }

    public final Function1<Boolean, Unit> getSecurityCallback() {
        return securityCallback;
    }

    public final void setSecurityCallback(Function1<? super Boolean, Unit> function1) {
        securityCallback = function1;
    }

    @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        super.onServiceConnected(name, service);
        SDKInterface sDKInterface = getInterface();
        if (sDKInterface != null) {
            sDKInterface.addLisener("function_app", stateListener);
        }
    }

    public final void disconnectConnection(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        SDKInterface sDKInterface = getInterface();
        if (sDKInterface != null) {
            sDKInterface.removeListener("function_app");
        }
        super.disconnect(context);
    }

    public final void init() {
        Pdlog.m3275i(TAG, "sdkInterface initing");
        SDKInterface sDKInterface = getInterface();
        if (sDKInterface != null) {
            sDKInterface.init(LocateCase.Slamware);
        }
        Pdlog.m3275i(TAG, "sdkInterface  finish");
    }
}
