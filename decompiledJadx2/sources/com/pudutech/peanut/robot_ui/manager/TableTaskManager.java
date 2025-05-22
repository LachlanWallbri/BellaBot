package com.pudutech.peanut.robot_ui.manager;

import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveSortType;
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.presenter.performance.MovePerformance;
import com.pudutech.peanut.robot_ui.bean.TaskModel;
import com.pudutech.peanut.robot_ui.config.Constans;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TableTaskManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\"J\u0006\u0010$\u001a\u00020\"J\u000e\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0004J\u001a\u0010'\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\tJ\u0016\u0010(\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tJ\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020 0\u0007j\b\u0012\u0004\u0012\u00020 `\tJ\u0006\u0010*\u001a\u00020\u001eJ\u0006\u0010+\u001a\u00020\u0010J\b\u0010,\u001a\u00020\"H\u0002J\u001e\u0010-\u001a\u00020\"2\u0016\u0010.\u001a\u0012\u0012\u0004\u0012\u00020 0\u0007j\b\u0012\u0004\u0012\u00020 `\tJB\u0010/\u001a\u00020\"2\u0016\u0010.\u001a\u0012\u0012\u0004\u0012\u00020 0\u0007j\b\u0012\u0004\u0012\u00020 `\t2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u00100\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u0010J\u000e\u00101\u001a\u00020\"2\u0006\u00102\u001a\u00020\u0004J\u000e\u00103\u001a\u00020\"2\u0006\u00102\u001a\u00020\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0012\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020 0\u0007j\b\u0012\u0004\u0012\u00020 `\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/TableTaskManager;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "allTask", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/robot_ui/bean/TaskModel;", "Lkotlin/collections/ArrayList;", "currentTask", "getCurrentTask", "()Ljava/lang/String;", "setCurrentTask", "(Ljava/lang/String;)V", "hasTask", "", "getHasTask", "()Z", "historyTaskDialog", "isBirthDay", "setBirthDay", "(Z)V", "movePerformance", "Lcom/pudutech/peanut/presenter/performance/MovePerformance;", "getMovePerformance", "()Lcom/pudutech/peanut/presenter/performance/MovePerformance;", "setMovePerformance", "(Lcom/pudutech/peanut/presenter/performance/MovePerformance;)V", "sortType", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveSortType;", "tableTaskList", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "cancelAllTask", "", "clearAll", "clearAllReturnTask", "finishTask", "id", "getAllHistoryReturnTask", "getAllReturnTask", "getAllTask", "getSortType", "haveReturnTask", "saveHistoryTask", "setAllReturnTask", "list", "setAllTask", "saveHistory", "setFinishTask", AIUIConstant.KEY_CONTENT, "setReturnTask", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TableTaskManager {
    public static final TableTaskManager INSTANCE;
    private static final String TAG;
    private static final ArrayList<TaskModel> allTask;
    private static String currentTask;
    private static final ArrayList<TaskModel> historyTaskDialog;
    private static boolean isBirthDay;
    private static MovePerformance movePerformance;
    private static MoveSortType sortType;
    private static final ArrayList<TrayModel> tableTaskList;

    static {
        TableTaskManager tableTaskManager = new TableTaskManager();
        INSTANCE = tableTaskManager;
        TAG = tableTaskManager.getClass().getSimpleName();
        tableTaskList = new ArrayList<>();
        allTask = new ArrayList<>();
        historyTaskDialog = new ArrayList<>();
        sortType = MoveSortType.AUTO;
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

    public final boolean isBirthDay() {
        return isBirthDay;
    }

    public final void setBirthDay(boolean z) {
        isBirthDay = z;
    }

    public static /* synthetic */ void setAllTask$default(TableTaskManager tableTaskManager, ArrayList arrayList, MoveSortType moveSortType, MovePerformance movePerformance2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        boolean z3 = z;
        if ((i & 16) != 0) {
            z2 = false;
        }
        tableTaskManager.setAllTask(arrayList, moveSortType, movePerformance2, z3, z2);
    }

    public final void setAllTask(ArrayList<TrayModel> list, MoveSortType sortType2, MovePerformance movePerformance2, boolean saveHistory, boolean isBirthDay2) {
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
        isBirthDay = isBirthDay2;
    }

    public final void setAllReturnTask(ArrayList<TrayModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3273d(TAG, "setReturnTask = " + list);
        allTask.clear();
        for (TrayModel trayModel : list) {
            String destination = trayModel.getAllDestinations().size() > 0 ? trayModel.getAllDestinations().get(0).getDestination() : "";
            allTask.add(new TaskModel(destination.length() == 0 ? -1 : 0, destination, trayModel));
        }
        saveHistoryTask();
    }

    public final boolean haveReturnTask() {
        ArrayList<TaskModel> arrayList = allTask;
        boolean z = false;
        if (arrayList != null) {
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                if (((TaskModel) it.next()).isReturn() == 1) {
                    z = true;
                }
            }
        }
        return z;
    }

    private final void saveHistoryTask() {
        Pdlog.m3273d(TAG, "saveHistoryTask = " + allTask);
        if (isBirthDay) {
            return;
        }
        Constans.INSTANCE.saveLastReturnTask(allTask);
    }

    public final void clearAllReturnTask() {
        allTask.clear();
    }

    public final ArrayList<TrayModel> getAllTask() {
        ArrayList<TrayModel> arrayList = new ArrayList<>();
        Iterator<T> it = tableTaskList.iterator();
        while (it.hasNext()) {
            arrayList.add(((TrayModel) it.next()).copy());
        }
        return arrayList;
    }

    public final ArrayList<TaskModel> getAllReturnTask() {
        ArrayList<TaskModel> arrayList = new ArrayList<>();
        arrayList.addAll(allTask);
        return arrayList;
    }

    public final ArrayList<TaskModel> getAllHistoryReturnTask() {
        return Constans.INSTANCE.getLastReturnTask();
    }

    public final void setReturnTask(String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        ArrayList<TaskModel> arrayList = allTask;
        if (arrayList != null) {
            for (TaskModel taskModel : arrayList) {
                if ((taskModel.getContent().length() > 0) && Intrinsics.areEqual(taskModel.getContent(), content)) {
                    taskModel.setReturn(1);
                }
            }
        }
    }

    public final void setFinishTask(String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        ArrayList<TaskModel> arrayList = allTask;
        if (arrayList != null) {
            for (TaskModel taskModel : arrayList) {
                if ((taskModel.getContent().length() > 0) && Intrinsics.areEqual(taskModel.getContent(), content) && taskModel.isReturn() != 1) {
                    taskModel.setReturn(5);
                }
            }
        }
        saveHistoryTask();
    }

    public final void cancelAllTask() {
        ArrayList<TaskModel> arrayList = allTask;
        if (arrayList != null) {
            for (TaskModel taskModel : arrayList) {
                if ((taskModel.getContent().length() > 0) && taskModel.isReturn() != 5 && taskModel.isReturn() != 1) {
                    taskModel.setReturn(3);
                }
            }
        }
        saveHistoryTask();
    }

    public final MoveSortType getSortType() {
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
            ((TrayModel) obj).getAllDestinations().removeIf(new Predicate<DeliveryModel>() { // from class: com.pudutech.peanut.robot_ui.manager.TableTaskManager$finishTask$$inlined$forEachIndexed$lambda$1
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
}
