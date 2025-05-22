package com.pudutech.robot.module.openapi;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.module.openapi.remoteapi.RobotCallProvider;
import com.pudutech.robot.module.openapi.remoteapi.RobotCancelCallProvider;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.bean.CallBody;
import com.pudutech.robot.opensdk.bean.Destination;
import com.pudutech.robot.opensdk.bean.pub.GoState;
import com.pudutech.robot.opensdk.bean.pub.PubRobotGoStateData;
import com.pudutech.robot.opensdk.bean.resp.ErrorCode;
import com.pudutech.robot.opensdk.bean.resp.RespResultBody;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RobotOpenCallManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\"#B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nJ\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u0016\u0010\u0014\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0003J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0016\u001a\u00020\u0012J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0004J\b\u0010\u0019\u001a\u00020\u000fH\u0002J\u000e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0004J\u0010\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u000e\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0004J\u001e\u0010\u001d\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u000e\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nJ\u0010\u0010!\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/robot/module/openapi/RobotOpenCallManager;", "", "()V", "TAG", "", "allCallTaskList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/robot/opensdk/MsgContext;", "Lcom/pudutech/robot/opensdk/bean/CallBody;", "callListeners", "Lcom/pudutech/robot/module/openapi/RobotOpenCallManager$IOnCallNotify;", "currentCallTasks", "getAllCallTarget", "Lcom/pudutech/robot/module/openapi/RobotOpenCallManager$IGetAllCallTarget;", "addCallListener", "", "iOnCallNotify", "addCallTask", "", NotificationCompat.CATEGORY_MESSAGE, "cancelCallTask", "getCallTarget", "hasCallTask", "notifyArrived", TypedValues.Attributes.S_TARGET, "notifyCall", "notifyCancel", "notifyCancelToUi", "notifyMoving", "notifyState", "goState", "Lcom/pudutech/robot/opensdk/bean/pub/GoState;", "removeCallListener", "setRunningCallTask", "IGetAllCallTarget", "IOnCallNotify", "module_robot_open_api_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotOpenCallManager {
    private static IGetAllCallTarget getAllCallTarget;
    public static final RobotOpenCallManager INSTANCE = new RobotOpenCallManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final CopyOnWriteArrayList<MsgContext<CallBody>> allCallTaskList = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<MsgContext<CallBody>> currentCallTasks = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<IOnCallNotify> callListeners = new CopyOnWriteArrayList<>();

    /* compiled from: RobotOpenCallManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/robot/module/openapi/RobotOpenCallManager$IGetAllCallTarget;", "", "getAllCallTarget", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "module_robot_open_api_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface IGetAllCallTarget {
        ArrayList<String> getAllCallTarget();
    }

    /* compiled from: RobotOpenCallManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/module/openapi/RobotOpenCallManager$IOnCallNotify;", "", "onCallReceive", "", "onCancel", TypedValues.Attributes.S_TARGET, "", "module_robot_open_api_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface IOnCallNotify {
        void onCallReceive();

        void onCancel(String target);
    }

    static {
        RobotOpenApiManager.INSTANCE.addRemoteApiProviders(new RobotCallProvider() { // from class: com.pudutech.robot.module.openapi.RobotOpenCallManager.1
            @Override // com.pudutech.robot.module.openapi.remoteapi.RobotCallProvider
            public RespResultBody addCallTask(MsgContext<CallBody> msg) {
                Destination destination;
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                ArrayList<Destination> allRobotDes$module_robot_open_api_release = RobotOpenApiManager.INSTANCE.getAllRobotDes$module_robot_open_api_release();
                if (allRobotDes$module_robot_open_api_release != null) {
                    for (Destination destination2 : allRobotDes$module_robot_open_api_release) {
                        CallBody reqData = msg.getReqData();
                        if (Intrinsics.areEqual((reqData == null || (destination = reqData.getDestination()) == null) ? null : destination.getName(), destination2.getName())) {
                            if (RobotOpenCallManager.INSTANCE.addCallTask(msg)) {
                                RobotOpenCallManager.INSTANCE.notifyCall();
                                return new RespResultBody(true, null, null, 6, null);
                            }
                            return new RespResultBody(false, Integer.valueOf(ErrorCode.getCALL_FAILED_TARGET_REPEAT()), null, 4, null);
                        }
                    }
                }
                return new RespResultBody(false, Integer.valueOf(ErrorCode.getCALL_FAILED_NO_TARGET()), null, 4, null);
            }
        });
        RobotOpenApiManager.INSTANCE.addRemoteApiProviders(new RobotCancelCallProvider() { // from class: com.pudutech.robot.module.openapi.RobotOpenCallManager.2
            @Override // com.pudutech.robot.module.openapi.remoteapi.RobotCancelCallProvider
            public RespResultBody cancelCallTask(MsgContext<CallBody> msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                RobotOpenCallManager.INSTANCE.cancelCallTask(msg);
                return new RespResultBody(true, null, null, 6, null);
            }
        });
    }

    private RobotOpenCallManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyCall() {
        Pdlog.m3273d(TAG, "notifyCall ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new RobotOpenCallManager$notifyCall$1(null), 2, null);
    }

    private final void notifyCancelToUi(String target) {
        Pdlog.m3273d(TAG, "notifyCancel : target = " + target + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new RobotOpenCallManager$notifyCancelToUi$1(target, null), 2, null);
    }

    public final boolean hasCallTask() {
        return !allCallTaskList.isEmpty();
    }

    public final String getCallTarget() {
        CallBody reqData;
        Destination destination;
        if (!(!allCallTaskList.isEmpty()) || (reqData = allCallTaskList.get(0).getReqData()) == null || (destination = reqData.getDestination()) == null) {
            return null;
        }
        return destination.getName();
    }

    public final void addCallListener(IOnCallNotify iOnCallNotify) {
        Intrinsics.checkParameterIsNotNull(iOnCallNotify, "iOnCallNotify");
        if (callListeners.contains(iOnCallNotify)) {
            return;
        }
        callListeners.add(iOnCallNotify);
    }

    public final void removeCallListener(IOnCallNotify iOnCallNotify) {
        Intrinsics.checkParameterIsNotNull(iOnCallNotify, "iOnCallNotify");
        callListeners.remove(iOnCallNotify);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean addCallTask(MsgContext<CallBody> msg) {
        String str;
        Destination destination;
        Destination destination2;
        MsgContext msgContext;
        String name;
        Destination destination3;
        Destination destination4;
        Pdlog.m3273d(TAG, "addCallTask : msg = " + msg + "; currentCallTasks = " + currentCallTasks);
        Iterator<T> it = currentCallTasks.iterator();
        do {
            str = null;
            if (it.hasNext()) {
                msgContext = (MsgContext) it.next();
                CallBody callBody = (CallBody) msgContext.getReqData();
                name = (callBody == null || (destination4 = callBody.getDestination()) == null) ? null : destination4.getName();
                CallBody reqData = msg.getReqData();
                if (reqData != null && (destination3 = reqData.getDestination()) != null) {
                    str = destination3.getName();
                }
            } else {
                Iterator<T> it2 = allCallTaskList.iterator();
                while (it2.hasNext()) {
                    MsgContext msgContext2 = (MsgContext) it2.next();
                    CallBody callBody2 = (CallBody) msgContext2.getReqData();
                    String name2 = (callBody2 == null || (destination2 = callBody2.getDestination()) == null) ? null : destination2.getName();
                    CallBody reqData2 = msg.getReqData();
                    if (Intrinsics.areEqual(name2, (reqData2 == null || (destination = reqData2.getDestination()) == null) ? null : destination.getName())) {
                        return Intrinsics.areEqual(msgContext2.getTarget(), msg.getTarget());
                    }
                }
                allCallTaskList.add(msg);
                return true;
            }
        } while (!Intrinsics.areEqual(name, str));
        if (Intrinsics.areEqual(msgContext.getTarget(), msg.getTarget())) {
            return true;
        }
        Pdlog.m3273d(TAG, "addCallTask has same destination");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0067, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r7, r8 != null ? r8.getDestination() : null) != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x011a, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r9, r10 != null ? r10.getDestination() : null) != false) goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0035 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x012f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void cancelCallTask(final MsgContext<CallBody> msg) {
        boolean z;
        boolean z2;
        Pdlog.m3273d(TAG, "cancelCallTask : msg = " + msg + "; currentCallTasks = " + currentCallTasks);
        CopyOnWriteArrayList<MsgContext<CallBody>> copyOnWriteArrayList = currentCallTasks;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = copyOnWriteArrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            MsgContext msgContext = (MsgContext) next;
            if (msg.getReqData() != null) {
                CallBody callBody = (CallBody) msgContext.getReqData();
                Destination destination = callBody != null ? callBody.getDestination() : null;
                CallBody reqData = msg.getReqData();
            }
            if (Intrinsics.areEqual(msgContext.getTarget(), msg.getTarget())) {
                z2 = true;
                if (!z2) {
                    arrayList.add(next);
                }
            }
            z2 = false;
            if (!z2) {
            }
        }
        ArrayList<MsgContext<CallBody>> arrayList2 = arrayList;
        if (!arrayList2.isEmpty()) {
            Pdlog.m3273d(TAG, "cancelCallTask : cTask = " + arrayList2 + "; ");
            for (MsgContext<CallBody> it2 : arrayList2) {
                RobotOpenCallManager robotOpenCallManager = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                robotOpenCallManager.notifyState(it2, GoState.Cancel);
                currentCallTasks.remove(it2);
            }
            notifyCancelToUi(msg.getTarget());
        } else {
            CopyOnWriteArrayList<MsgContext<CallBody>> copyOnWriteArrayList2 = allCallTaskList;
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : copyOnWriteArrayList2) {
                MsgContext msgContext2 = (MsgContext) obj;
                if (msg.getReqData() != null) {
                    CallBody callBody2 = (CallBody) msgContext2.getReqData();
                    Destination destination2 = callBody2 != null ? callBody2.getDestination() : null;
                    CallBody reqData2 = msg.getReqData();
                }
                if (Intrinsics.areEqual(msgContext2.getTarget(), msg.getTarget())) {
                    z = true;
                    if (!z) {
                        arrayList3.add(obj);
                    }
                }
                z = false;
                if (!z) {
                }
            }
            ArrayList<MsgContext<CallBody>> arrayList4 = arrayList3;
            if (!arrayList4.isEmpty()) {
                Pdlog.m3273d(TAG, "cancelCallTask : allTask = " + arrayList4 + "; ");
                for (MsgContext<CallBody> it3 : arrayList4) {
                    RobotOpenCallManager robotOpenCallManager2 = INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                    robotOpenCallManager2.notifyState(it3, GoState.Cancel);
                }
                notifyCancelToUi(msg.getTarget());
            }
        }
        allCallTaskList.removeIf(new Predicate<MsgContext<CallBody>>() { // from class: com.pudutech.robot.module.openapi.RobotOpenCallManager$cancelCallTask$3
            @Override // java.util.function.Predicate
            public final boolean test(MsgContext<CallBody> msgContext3) {
                if (Intrinsics.areEqual(msgContext3.getTarget(), MsgContext.this.getTarget())) {
                    if (MsgContext.this.getReqData() != null) {
                        CallBody reqData3 = msgContext3.getReqData();
                        Destination destination3 = reqData3 != null ? reqData3.getDestination() : null;
                        CallBody callBody3 = (CallBody) MsgContext.this.getReqData();
                        if (Intrinsics.areEqual(destination3, callBody3 != null ? callBody3.getDestination() : null)) {
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public final void notifyMoving(String target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        setRunningCallTask(target);
        Pdlog.m3273d(TAG, "notifyMoving : target = " + target + "; currentCallTasks = " + currentCallTasks.size());
        for (MsgContext<CallBody> it : currentCallTasks) {
            RobotOpenCallManager robotOpenCallManager = INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            robotOpenCallManager.notifyState(it, GoState.Arriving);
        }
    }

    public final void notifyArrived(String target) {
        Destination destination;
        Intrinsics.checkParameterIsNotNull(target, "target");
        Pdlog.m3273d(TAG, "notifyArrived : target = " + target + "; currentCallTasks = " + currentCallTasks.size());
        for (MsgContext<CallBody> it : currentCallTasks) {
            CallBody reqData = it.getReqData();
            if (!Intrinsics.areEqual(target, (reqData == null || (destination = reqData.getDestination()) == null) ? null : destination.getName())) {
                Pdlog.m3274e(TAG, "notifyArrived : target not same ???? ");
            }
            Pdlog.m3273d(TAG, "notifyArrived ");
            RobotOpenCallManager robotOpenCallManager = INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            robotOpenCallManager.notifyState(it, GoState.Arrived);
        }
        currentCallTasks.clear();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0029 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ae A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void notifyCancel(String target) {
        Destination destination;
        Destination destination2;
        boolean z;
        Destination destination3;
        boolean z2;
        Destination destination4;
        Intrinsics.checkParameterIsNotNull(target, "target");
        Pdlog.m3274e(TAG, target, allCallTaskList, currentCallTasks);
        CopyOnWriteArrayList<MsgContext<CallBody>> copyOnWriteArrayList = allCallTaskList;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = copyOnWriteArrayList.iterator();
        while (true) {
            String str = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            MsgContext msgContext = (MsgContext) next;
            if (msgContext.getReqData() != null) {
                CallBody callBody = (CallBody) msgContext.getReqData();
                if (callBody != null && (destination4 = callBody.getDestination()) != null) {
                    str = destination4.getName();
                }
                if (Intrinsics.areEqual(str, target)) {
                    z2 = true;
                    if (!z2) {
                        arrayList.add(next);
                    }
                }
            }
            z2 = false;
            if (!z2) {
            }
        }
        ArrayList<MsgContext<CallBody>> arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            CopyOnWriteArrayList<MsgContext<CallBody>> copyOnWriteArrayList2 = currentCallTasks;
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : copyOnWriteArrayList2) {
                MsgContext msgContext2 = (MsgContext) obj;
                if (msgContext2.getReqData() != null) {
                    CallBody callBody2 = (CallBody) msgContext2.getReqData();
                    if (Intrinsics.areEqual((callBody2 == null || (destination3 = callBody2.getDestination()) == null) ? null : destination3.getName(), target)) {
                        z = true;
                        if (!z) {
                            arrayList3.add(obj);
                        }
                    }
                }
                z = false;
                if (!z) {
                }
            }
            ArrayList<MsgContext<CallBody>> arrayList4 = arrayList3;
            if (arrayList4.isEmpty()) {
                return;
            }
            for (MsgContext<CallBody> it2 : arrayList4) {
                CallBody reqData = it2.getReqData();
                if (!Intrinsics.areEqual(target, (reqData == null || (destination2 = reqData.getDestination()) == null) ? null : destination2.getName())) {
                    Pdlog.m3274e(TAG, "notifyCancel : target not same ???? ");
                }
                RobotOpenCallManager robotOpenCallManager = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                robotOpenCallManager.cancelCallTask(it2);
            }
            return;
        }
        for (MsgContext<CallBody> it3 : arrayList2) {
            CallBody reqData2 = it3.getReqData();
            if (!Intrinsics.areEqual(target, (reqData2 == null || (destination = reqData2.getDestination()) == null) ? null : destination.getName())) {
                Pdlog.m3274e(TAG, "notifyCancel : target not same ???? ");
            }
            RobotOpenCallManager robotOpenCallManager2 = INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            robotOpenCallManager2.cancelCallTask(it3);
        }
    }

    private final void setRunningCallTask(String target) {
        Destination destination;
        if (!currentCallTasks.isEmpty()) {
            Pdlog.m3274e(TAG, "setRunningCallTask : currentCallTask is not empty = " + currentCallTasks + " !!");
        }
        CopyOnWriteArrayList<MsgContext<CallBody>> copyOnWriteArrayList = allCallTaskList;
        ArrayList arrayList = new ArrayList();
        for (Object obj : copyOnWriteArrayList) {
            CallBody callBody = (CallBody) ((MsgContext) obj).getReqData();
            if (Intrinsics.areEqual((callBody == null || (destination = callBody.getDestination()) == null) ? null : destination.getName(), target)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        currentCallTasks.addAll(arrayList2);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            allCallTaskList.remove((MsgContext) it.next());
        }
    }

    private final void notifyState(MsgContext<CallBody> msg, GoState goState) {
        RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
        String target = msg.getTarget();
        String role = msg.getRole();
        String state = goState.getState();
        CallBody reqData = msg.getReqData();
        Destination destination = reqData != null ? reqData.getDestination() : null;
        if (destination == null) {
            Intrinsics.throwNpe();
        }
        robotOpenSdk.publishMsg(target, new PubRobotGoStateData(role, state, destination), new ICallback() { // from class: com.pudutech.robot.module.openapi.RobotOpenCallManager$notifyState$1
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                String str;
                Intrinsics.checkParameterIsNotNull(e, "e");
                RobotOpenCallManager robotOpenCallManager = RobotOpenCallManager.INSTANCE;
                str = RobotOpenCallManager.TAG;
                Pdlog.m3274e(str, "cancelCallTask onFailed : " + Log.getStackTraceString(e));
            }

            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
                String str;
                RobotOpenCallManager robotOpenCallManager = RobotOpenCallManager.INSTANCE;
                str = RobotOpenCallManager.TAG;
                Pdlog.m3274e(str, "cancelCallTask onSuccess");
            }
        });
    }
}
