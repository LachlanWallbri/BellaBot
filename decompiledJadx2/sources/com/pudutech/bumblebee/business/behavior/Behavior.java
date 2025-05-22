package com.pudutech.bumblebee.business.behavior;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.bumblebee.business.movementInterface.CruiseInterface;
import com.pudutech.bumblebee.business.movementInterface.DeliverInterface;
import com.pudutech.bumblebee.business.movementInterface.GoHomeInterface;
import com.pudutech.bumblebee.business.movementInterface.HangOutInterface;
import com.pudutech.bumblebee.business.movementInterface.IdleInterface;
import com.pudutech.bumblebee.business.movementInterface.RecycleInterface;
import com.pudutech.bumblebee.business.movementInterface.TempMoveInterface;
import com.pudutech.bumblebee.business.movementTask.CruiseTask;
import com.pudutech.bumblebee.business.movementTask.DeliverTask;
import com.pudutech.bumblebee.business.movementTask.GoHomeTask;
import com.pudutech.bumblebee.business.movementTask.HangoutTask;
import com.pudutech.bumblebee.business.movementTask.IdleTask;
import com.pudutech.bumblebee.business.movementTask.RecycleTask;
import com.pudutech.bumblebee.business.movementTask.TempTask;
import com.pudutech.bumblebee.business.robotsdk.MoveStateListener;
import com.pudutech.mirsdk.aidl.AccessDoorListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Behavior.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001?B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u00104\u001a\u0004\u0018\u0001H5\"\n\b\u0000\u00105\u0018\u0001*\u00020\u001bH\u0086\b¢\u0006\u0002\u0010\u001eJ\u0010\u00106\u001a\u00020\b2\b\u00107\u001a\u0004\u0018\u00010\u0015J\u000e\u00108\u001a\u0002002\u0006\u00109\u001a\u00020:J\u0014\u0010;\u001a\u0002002\f\u00109\u001a\b\u0012\u0004\u0012\u0002000<J\u0016\u0010;\u001a\u0002002\u0006\u0010=\u001a\u00020>2\u0006\u00109\u001a\u00020:R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000b\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u00020\u00108FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R(\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R*\u0010!\u001a\u0012\u0012\u0004\u0012\u00020#0\"j\b\u0012\u0004\u0012\u00020#`$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(RL\u0010)\u001a:\u0012\u0015\u0012\u0013\u0018\u00010+¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(/\u0012\u0004\u0012\u0002000*j\u0002`1X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103¨\u0006@"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/behavior/Behavior;", "", "()V", "TAG", "", "handler", "Landroid/os/Handler;", "<set-?>", "", "initDone", "getInitDone", "()Z", "isHaveLocked", "setHaveLocked", "(Z)V", "isNoCanCall", "", "()I", "setNoCanCall", "(I)V", "moveActionInterface", "Lcom/pudutech/mirsdk/aidl/MoveActionInterface;", "getMoveActionInterface$module_bumblebee_business_robotRelease", "()Lcom/pudutech/mirsdk/aidl/MoveActionInterface;", "setMoveActionInterface$module_bumblebee_business_robotRelease", "(Lcom/pudutech/mirsdk/aidl/MoveActionInterface;)V", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;", "movementTask", "getMovementTask", "()Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;", "setMovementTask", "(Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;)V", "onIdleListeners", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/behavior/Behavior$OnIdleListener;", "Lkotlin/collections/ArrayList;", "getOnIdleListeners", "()Ljava/util/ArrayList;", "setOnIdleListeners", "(Ljava/util/ArrayList;)V", "stateListener", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "p0", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "getStateListener$module_bumblebee_business_robotRelease", "()Lkotlin/jvm/functions/Function2;", "changeMovementTask", ExifInterface.GPS_DIRECTION_TRUE, "init", "moveAction", "removeTaskDelayOnTaskThread", "task", "Ljava/lang/Runnable;", "runOnTaskThread", "Lkotlin/Function0;", "delay_ms", "", "OnIdleListener", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Behavior {
    private static Handler handler;
    private static boolean initDone;
    private static boolean isHaveLocked;
    private static int isNoCanCall;
    private static MoveActionInterface moveActionInterface;
    private static BaseTaskInterface movementTask;
    private static ArrayList<OnIdleListener> onIdleListeners;
    private static final Function2<RobotState, String, Unit> stateListener;
    public static final Behavior INSTANCE = new Behavior();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* compiled from: Behavior.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/behavior/Behavior$OnIdleListener;", "", "onIdle", "", "boolean", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnIdleListener {
        void onIdle(boolean r1);
    }

    static {
        HandlerThread handlerThread = new HandlerThread("TaskThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
        movementTask = new IdleTask();
        onIdleListeners = new ArrayList<>();
        stateListener = new Function2<RobotState, String, Unit>() { // from class: com.pudutech.bumblebee.business.behavior.Behavior$stateListener$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
                invoke2(robotState, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public void invoke2(RobotState state, String description) {
                String replace$default;
                String str;
                if (state == RobotState.Error && description != null && (replace$default = StringsKt.replace$default(description, " ", "", false, 4, (Object) null)) != null) {
                    String str2 = replace$default;
                    if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) "\"level\":\"Error\"", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) str2, (CharSequence) "\"level\":\"Fatal\"", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) str2, (CharSequence) "\"level\":\"Event\"", false, 2, (Object) null)) {
                        Behavior behavior = Behavior.INSTANCE;
                        str = Behavior.TAG;
                        Pdlog.m3277w(str, "only level waring. so callback monitor only --description = " + description);
                        return;
                    }
                }
                BaseTaskInterface movementTask2 = Behavior.INSTANCE.getMovementTask();
                if (!(movementTask2 instanceof MoveStateListener)) {
                    movementTask2 = null;
                }
                MoveStateListener moveStateListener = (MoveStateListener) movementTask2;
                if (moveStateListener != null) {
                    moveStateListener.onStateChange(state, description);
                }
            }
        };
    }

    private Behavior() {
    }

    public final void setNoCanCall(int i) {
        isNoCanCall = i;
    }

    public final int isNoCanCall() {
        Pdlog.m3273d(TAG, "isNoCanCall:" + isNoCanCall);
        return isNoCanCall;
    }

    public final boolean isHaveLocked() {
        return isHaveLocked;
    }

    public final void setHaveLocked(boolean z) {
        isHaveLocked = z;
    }

    public final boolean getInitDone() {
        return initDone;
    }

    public final void runOnTaskThread(final Function0<Unit> task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Handler handler2 = handler;
        if (handler2 != null) {
            handler2.post(new Runnable() { // from class: com.pudutech.bumblebee.business.behavior.Behavior$sam$java_lang_Runnable$0
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    Intrinsics.checkExpressionValueIsNotNull(Function0.this.invoke(), "invoke(...)");
                }
            });
        }
    }

    public final void runOnTaskThread(long delay_ms, Runnable task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Handler handler2 = handler;
        if (handler2 != null) {
            handler2.postDelayed(task, delay_ms);
        }
    }

    public final void removeTaskDelayOnTaskThread(Runnable task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Handler handler2 = handler;
        if (handler2 != null) {
            handler2.removeCallbacks(task);
        }
    }

    public final MoveActionInterface getMoveActionInterface$module_bumblebee_business_robotRelease() {
        return moveActionInterface;
    }

    public final void setMoveActionInterface$module_bumblebee_business_robotRelease(MoveActionInterface moveActionInterface2) {
        moveActionInterface = moveActionInterface2;
    }

    public final boolean init(MoveActionInterface moveAction) {
        Pdlog.m3275i(TAG, "init when initDone=" + initDone);
        initDone = false;
        moveActionInterface = moveAction;
        MoveActionInterface moveActionInterface2 = moveActionInterface;
        if (moveActionInterface2 == null) {
            Pdlog.m3274e(TAG, "moveActionInterface=" + moveActionInterface);
            return false;
        }
        if (moveActionInterface2 != null) {
            moveActionInterface2.addAccessDoorStateListener(TAG, new AccessDoorListener.Stub() { // from class: com.pudutech.bumblebee.business.behavior.Behavior$init$1
                @Override // com.pudutech.mirsdk.aidl.AccessDoorListener
                public void informAccessDoorControlState(AccessDoorControlState p0, String p1) {
                    BaseTaskInterface movementTask2 = Behavior.INSTANCE.getMovementTask();
                    if (!(movementTask2 instanceof MoveStateListener)) {
                        movementTask2 = null;
                    }
                    MoveStateListener moveStateListener = (MoveStateListener) movementTask2;
                    if (moveStateListener != null) {
                        moveStateListener.onAccessDoorChange(p0, p1);
                    }
                }
            });
        }
        initDone = true;
        Pdlog.m3275i(TAG, "init done");
        return initDone;
    }

    public final BaseTaskInterface getMovementTask() {
        return movementTask;
    }

    public final void setMovementTask(BaseTaskInterface baseTaskInterface) {
        movementTask = baseTaskInterface;
        Iterator<T> it = onIdleListeners.iterator();
        while (it.hasNext()) {
            ((OnIdleListener) it.next()).onIdle(baseTaskInterface instanceof IdleTask);
        }
    }

    public final ArrayList<OnIdleListener> getOnIdleListeners() {
        return onIdleListeners;
    }

    public final void setOnIdleListeners(ArrayList<OnIdleListener> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        onIdleListeners = arrayList;
    }

    public final /* synthetic */ <T extends BaseTaskInterface> T changeMovementTask() {
        Intrinsics.needClassReification();
        TypeToken<T> typeToken = new TypeToken<T>() { // from class: com.pudutech.bumblebee.business.behavior.Behavior$changeMovementTask$obj$1
        };
        Object rawType = typeToken.getRawType();
        Pdlog.m3275i(TAG, "change task=" + rawType + ' ' + typeToken.getType() + ' ' + typeToken.getClass() + ' ' + typeToken);
        if (!getInitDone()) {
            Pdlog.m3274e(TAG, "not init yet");
            return null;
        }
        DelayResumeActive.INSTANCE.cancelTask();
        if (Intrinsics.areEqual(rawType, CruiseInterface.class)) {
            setMovementTask(new CruiseTask());
        } else if (Intrinsics.areEqual(rawType, DeliverInterface.class)) {
            setMovementTask(new DeliverTask());
        } else if (Intrinsics.areEqual(rawType, GoHomeInterface.class)) {
            setMovementTask(new GoHomeTask());
        } else if (Intrinsics.areEqual(rawType, HangOutInterface.class)) {
            setMovementTask(new HangoutTask());
        } else if (Intrinsics.areEqual(rawType, RecycleInterface.class)) {
            setMovementTask(new RecycleTask());
        } else if (Intrinsics.areEqual(rawType, TempMoveInterface.class)) {
            setMovementTask(new TempTask());
        } else if (Intrinsics.areEqual(rawType, IdleInterface.class)) {
            setMovementTask(new IdleTask());
        } else {
            Pdlog.m3274e(TAG, rawType + " not supported");
            return null;
        }
        Pdlog.m3275i(TAG, "change task done");
        BaseTaskInterface movementTask2 = getMovementTask();
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) movementTask2;
    }

    public final Function2<RobotState, String, Unit> getStateListener$module_bumblebee_business_robotRelease() {
        return stateListener;
    }
}
