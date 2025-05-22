package com.pudutech.peanut.robot_ui.p063ui.helper;

import android.content.Intent;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveSortType;
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.presenter.performance.MovePerformance;
import com.pudutech.peanut.robot_ui.manager.TableTaskManager;
import com.pudutech.peanut.robot_ui.p063ui.DeliverActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BeeperCallHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0013J\u0006\u0010\u0019\u001a\u00020\fJ\u0006\u0010\u001a\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/BeeperCallHelper;", "", "()V", "TAG", "", "activity", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "onCancelCallListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", TypedValues.Attributes.S_TARGET, "", "getOnCancelCallListener", "()Lkotlin/jvm/functions/Function1;", "setOnCancelCallListener", "(Lkotlin/jvm/functions/Function1;)V", "bind", "receiver", "", "resetCountdown", "onCallTask", "onCancelTask", "receiverCallTask", "force", "stopReceiverCallTask", "unbind", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BeeperCallHelper {
    private final String TAG = "BeeperCallHelper";
    private MyBaseActivity activity;
    private Function1<? super String, Unit> onCancelCallListener;

    public final Function1<String, Unit> getOnCancelCallListener() {
        return this.onCancelCallListener;
    }

    public final void setOnCancelCallListener(Function1<? super String, Unit> function1) {
        this.onCancelCallListener = function1;
    }

    public final void onCallTask(String r20) {
        Intrinsics.checkParameterIsNotNull(r20, "target");
        Pdlog.m3273d(this.TAG, "onCallTask : target = " + r20 + "; ");
        MyBaseActivity myBaseActivity = this.activity;
        if (myBaseActivity == null || myBaseActivity.isFinishing() || myBaseActivity.isDestroyed()) {
            return;
        }
        DeliveryModel deliveryModel = new DeliveryModel(r20, null, null, null, null, 28, null);
        ArrayList arrayList = new ArrayList();
        TrayModel trayModel = new TrayModel();
        trayModel.getAllDestinations().add(deliveryModel);
        arrayList.add(trayModel);
        TableTaskManager.setAllTask$default(TableTaskManager.INSTANCE, arrayList, MoveSortType.AUTO, MovePerformance.NORMAL, false, false, 16, null);
        Intent intent = new Intent(myBaseActivity, (Class<?>) DeliverActivity.class);
        intent.putExtra("DELIVER_MODE", 4);
        myBaseActivity.jumpAndFinish(intent);
    }

    public final void onCancelTask(String r5) {
        Intrinsics.checkParameterIsNotNull(r5, "target");
        Pdlog.m3273d(this.TAG, "onCancelTask : target = " + r5 + "; ");
        Function1<? super String, Unit> function1 = this.onCancelCallListener;
        if (function1 != null) {
            function1.invoke(r5);
        }
    }

    public final void receiverCallTask(boolean force) {
        Pdlog.m3273d(this.TAG, "receiverCallTask : force = " + force + "; ");
    }

    public final void stopReceiverCallTask() {
        Pdlog.m3273d(this.TAG, "stopReceiverCallTask ");
    }

    public static /* synthetic */ void bind$default(BeeperCallHelper beeperCallHelper, MyBaseActivity myBaseActivity, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        beeperCallHelper.bind(myBaseActivity, z, z2);
    }

    public final void bind(MyBaseActivity activity, boolean receiver, boolean resetCountdown) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Pdlog.m3273d(this.TAG, "bind : activity = " + activity + "; receiver = " + receiver + "; resetCountdown = " + resetCountdown + "; ");
        this.activity = activity;
    }

    public final void unbind() {
        Pdlog.m3273d(this.TAG, "unbind ");
        this.activity = (MyBaseActivity) null;
        this.onCancelCallListener = (Function1) null;
    }
}
