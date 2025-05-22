package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.app.Activity;
import android.content.Intent;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallContract;
import com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallPresenter;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallFromType;
import com.pudutech.bumblebee.robot_ui.manager.LoRaManager;
import com.pudutech.bumblebee.robot_ui.manager.RobotStatusManager;
import com.pudutech.bumblebee.robot_ui.manager.TableTaskManager;
import com.pudutech.bumblebee.robot_ui.p054ui.DeliverActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.FinishInter;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: BeeperCallHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0019J\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0019J\u0006\u0010!\u001a\u00020\u0012J\u0006\u0010\"\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR7\u0010\r\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006#"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperCallContract$ViewInterface;", "()V", "TAG", "", "activity", "Landroid/app/Activity;", "beeperCallPresenter", "Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperCallPresenter;", "getBeeperCallPresenter", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperCallPresenter;", "beeperCallPresenter$delegate", "Lkotlin/Lazy;", "onCancelCallListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", TypedValues.Attributes.S_TARGET, "", "getOnCancelCallListener", "()Lkotlin/jvm/functions/Function1;", "setOnCancelCallListener", "(Lkotlin/jvm/functions/Function1;)V", "bind", "receiver", "", "resetCountdown", "onCallTask", "type", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallFromType;", "onCancelTask", "receiverCallTask", "force", "stopReceiverCallTask", "unbind", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BeeperCallHelper implements BeeperCallContract.ViewInterface {
    private Activity activity;
    private Function1<? super String, Unit> onCancelCallListener;
    private final String TAG = "BeeperCallHelper";

    /* renamed from: beeperCallPresenter$delegate, reason: from kotlin metadata */
    private final Lazy beeperCallPresenter = LazyKt.lazy(new Function0<BeeperCallPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.BeeperCallHelper$beeperCallPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BeeperCallPresenter invoke() {
            BeeperCallPresenter beeperCallPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(BeeperCallPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(BeeperCallPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                beeperCallPresenter = new BeeperCallPresenter();
            } else {
                presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(BeeperCallPresenter.class).toString());
                if (!(basePresenterInterface instanceof BeeperCallPresenter)) {
                    basePresenterInterface = null;
                }
                beeperCallPresenter = (BeeperCallPresenter) basePresenterInterface;
            }
            if (beeperCallPresenter == null) {
                Intrinsics.throwNpe();
            }
            return beeperCallPresenter;
        }
    });

    private final BeeperCallPresenter getBeeperCallPresenter() {
        return (BeeperCallPresenter) this.beeperCallPresenter.getValue();
    }

    public final Function1<String, Unit> getOnCancelCallListener() {
        return this.onCancelCallListener;
    }

    public final void setOnCancelCallListener(Function1<? super String, Unit> function1) {
        this.onCancelCallListener = function1;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallContract.ViewInterface
    public void onCallTask(String r12, CallFromType type) {
        Intrinsics.checkParameterIsNotNull(r12, "target");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Pdlog.m3273d(this.TAG, "onCallTask : target = " + r12 + "; ");
        Activity activity = this.activity;
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        DeliveryModel deliveryModel = new DeliveryModel(r12, null, null, null, 12, null);
        ArrayList<TrayModel> arrayList = new ArrayList<>();
        TrayModel trayModel = new TrayModel();
        trayModel.getAllDestinations().add(deliveryModel);
        arrayList.add(trayModel);
        TableTaskManager.INSTANCE.setAllTask(arrayList, SortType.AUTO, MovePerformance.NORMAL, false);
        Intent intent = new Intent(activity, (Class<?>) DeliverActivity.class);
        intent.putExtra("DELIVER_MODE", 4);
        intent.putExtra(DeliverActivity.DELIVER_FROM, type.getType());
        boolean z = activity instanceof FinishInter;
        Object obj = activity;
        if (!z) {
            obj = null;
        }
        FinishInter finishInter = (FinishInter) obj;
        if (finishInter != null) {
            finishInter.jumpAndFinish(intent);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallContract.ViewInterface
    public void onCancelTask(String r5) {
        Intrinsics.checkParameterIsNotNull(r5, "target");
        Pdlog.m3273d(this.TAG, "onCancelTask : target = " + r5 + "; ");
        Function1<? super String, Unit> function1 = this.onCancelCallListener;
        if (function1 != null) {
            function1.invoke(r5);
        }
    }

    public final void receiverCallTask(boolean force) {
        Pdlog.m3273d(this.TAG, "receiverCallTask : force = " + force + "; ");
        if (force) {
            getBeeperCallPresenter().resetCallCountdown();
        }
        RobotStatusManager.INSTANCE.isBusyState(false);
        getBeeperCallPresenter().startReceiveCallTask();
    }

    public final void stopReceiverCallTask() {
        Pdlog.m3273d(this.TAG, "stopReceiverCallTask ");
        RobotStatusManager.INSTANCE.isBusyState(true);
        getBeeperCallPresenter().stopReceiveCallTask();
    }

    public static /* synthetic */ void bind$default(BeeperCallHelper beeperCallHelper, Activity activity, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        beeperCallHelper.bind(activity, z, z2);
    }

    public final void bind(Activity activity, boolean receiver, boolean resetCountdown) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Pdlog.m3273d(this.TAG, "bind : activity = " + activity + "; receiver = " + receiver + "; resetCountdown = " + resetCountdown + "; ");
        this.activity = activity;
        getBeeperCallPresenter().replaceView(this);
        if (resetCountdown) {
            getBeeperCallPresenter().resetCallCountdown();
        }
        if (receiver) {
            getBeeperCallPresenter().startReceiveCallTask();
        } else {
            getBeeperCallPresenter().stopReceiveCallTask();
        }
        LoRaManager.INSTANCE.setCallback(this);
    }

    public final void unbind() {
        Pdlog.m3273d(this.TAG, "unbind ");
        getBeeperCallPresenter().removeView(this);
        this.activity = (Activity) null;
        this.onCancelCallListener = (Function1) null;
        LoRaManager.INSTANCE.setCallback(null);
    }
}
