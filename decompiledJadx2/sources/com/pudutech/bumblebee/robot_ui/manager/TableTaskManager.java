package com.pudutech.bumblebee.robot_ui.manager;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.robot.module.report.track2.TaskDestination;
import com.pudutech.robot.module.report.track2.TaskInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TableTaskManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0004J\u0006\u0010\u001f\u001a\u00020 J\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0018j\b\u0012\u0004\u0012\u00020\u0019`\u001aJ\u0006\u0010\"\u001a\u00020\u0016J\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$J8\u0010&\u001a\u00020\u001c2\u0016\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0018j\b\u0012\u0004\u0012\u00020\u0019`\u001a2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010(\u001a\u00020\fJ.\u0010)\u001a\u00020\u001c2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00190$2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010(\u001a\u00020\fJ\u0006\u0010*\u001a\u00020\u001cR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0018j\b\u0012\u0004\u0012\u00020\u0019`\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/TableTaskManager;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "currentTask", "getCurrentTask", "()Ljava/lang/String;", "setCurrentTask", "(Ljava/lang/String;)V", "hasTask", "", "getHasTask", "()Z", "movePerformance", "Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "getMovePerformance", "()Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "setMovePerformance", "(Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;)V", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "tableTaskList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "clearAll", "", "finishTask", "id", "getAllDestinations", "", "getAllTask", "getSortType", "getTaskInfo", "", "Lcom/pudutech/robot/module/report/track2/TaskInfo;", "setAllTask", "list", "saveHistory", "setRecycleTasks", "setReverse", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TableTaskManager {
    public static final TableTaskManager INSTANCE;
    private static final String TAG;
    private static String currentTask;
    private static MovePerformance movePerformance;
    private static SortType sortType;
    private static final ArrayList<TrayModel> tableTaskList;

    static {
        TableTaskManager tableTaskManager = new TableTaskManager();
        INSTANCE = tableTaskManager;
        TAG = tableTaskManager.getClass().getSimpleName();
        tableTaskList = new ArrayList<>();
        sortType = Constans.INSTANCE.getSortType();
        movePerformance = MovePerformance.NORMAL;
    }

    private TableTaskManager() {
    }

    public final MovePerformance getMovePerformance() {
        return movePerformance;
    }

    public final void setMovePerformance(MovePerformance movePerformance2) {
        Intrinsics.checkParameterIsNotNull(movePerformance2, "<set-?>");
        movePerformance = movePerformance2;
    }

    public final boolean getHasTask() {
        ArrayList<TrayModel> arrayList = tableTaskList;
        if ((arrayList instanceof Collection) && arrayList.isEmpty()) {
            return false;
        }
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((TrayModel) it.next()).getAllDestinations().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public final String getCurrentTask() {
        return currentTask;
    }

    public final void setCurrentTask(String str) {
        currentTask = str;
    }

    public static /* synthetic */ void setAllTask$default(TableTaskManager tableTaskManager, ArrayList arrayList, SortType sortType2, MovePerformance movePerformance2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        tableTaskManager.setAllTask(arrayList, sortType2, movePerformance2, z);
    }

    public final void setAllTask(ArrayList<TrayModel> list, SortType sortType2, MovePerformance movePerformance2, boolean saveHistory) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(sortType2, "sortType");
        Intrinsics.checkParameterIsNotNull(movePerformance2, "movePerformance");
        Pdlog.m3273d(TAG, "setAllTask = " + list);
        tableTaskList.clear();
        tableTaskList.addAll(list);
        if (saveHistory) {
            Constans.INSTANCE.saveLastDeliveryTask(list);
        }
        movePerformance = movePerformance2;
        sortType = sortType2;
    }

    public static /* synthetic */ void setRecycleTasks$default(TableTaskManager tableTaskManager, List list, SortType sortType2, MovePerformance movePerformance2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        tableTaskManager.setRecycleTasks(list, sortType2, movePerformance2, z);
    }

    public final void setRecycleTasks(List<TrayModel> list, SortType sortType2, MovePerformance movePerformance2, boolean saveHistory) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(sortType2, "sortType");
        Intrinsics.checkParameterIsNotNull(movePerformance2, "movePerformance");
        Pdlog.m3273d(TAG, "setRecycleTasks = " + list);
        tableTaskList.clear();
        tableTaskList.addAll(list);
        if (saveHistory) {
            Constans.INSTANCE.saveHistoryRecycleTask(list);
        }
        movePerformance = movePerformance2;
        sortType = sortType2;
    }

    public final ArrayList<TrayModel> getAllTask() {
        ArrayList<TrayModel> arrayList = new ArrayList<>();
        arrayList.addAll(tableTaskList);
        return arrayList;
    }

    public final void setReverse() {
        Iterator<T> it = tableTaskList.iterator();
        while (it.hasNext()) {
            CollectionsKt.reverse(((TrayModel) it.next()).getAllDestinations());
        }
    }

    public final SortType getSortType() {
        return sortType;
    }

    public final void finishTask(final String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        int i = 0;
        for (Object obj : tableTaskList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((TrayModel) obj).getAllDestinations().removeIf(new Predicate<DeliveryModel>() { // from class: com.pudutech.bumblebee.robot_ui.manager.TableTaskManager$finishTask$$inlined$forEachIndexed$lambda$1
                @Override // java.util.function.Predicate
                public final boolean test(DeliveryModel it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return Intrinsics.areEqual(it.getDestination(), id);
                }
            });
            i = i2;
        }
    }

    public final void clearAll() {
        tableTaskList.clear();
        currentTask = (String) null;
        movePerformance = MovePerformance.NORMAL;
    }

    public final int getAllDestinations() {
        Iterator<T> it = tableTaskList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((TrayModel) it.next()).getAllDestinations().size();
        }
        return i;
    }

    public final List<TaskInfo> getTaskInfo() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : tableTaskList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ArrayList arrayList2 = new ArrayList();
            TaskInfo taskInfo = new TaskInfo(i2, arrayList2);
            for (DeliveryModel deliveryModel : ((TrayModel) obj).getAllDestinations()) {
                deliveryModel.getDestination();
                arrayList2.add(new TaskDestination(deliveryModel.getDestination(), String.valueOf(deliveryModel.getFoodInfo())));
            }
            if (!arrayList2.isEmpty()) {
                arrayList.add(taskInfo);
            }
            i = i2;
        }
        return arrayList;
    }
}
