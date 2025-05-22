package com.pudutech.peanut.robot_ui.p063ui.helper;

import android.app.Application;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.leaselib.UseType;
import com.pudutech.peanut.presenter.PresenterHolder;
import com.pudutech.peanut.presenter.lease_task.LeaseContract;
import com.pudutech.peanut.presenter.lease_task.LeasePresenter;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.UrlManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LeaseExpireDialog;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: LeaseHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010\u0007J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%H\u0016J\u000e\u0010&\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0007J\u000e\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020\u0019R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006+"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/LeaseHelper;", "Lcom/pudutech/peanut/presenter/lease_task/LeaseContract$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "bindAc", "Landroidx/appcompat/app/AppCompatActivity;", "isLeaseExpire", "", "()Z", "setLeaseExpire", "(Z)V", "leaseExpireDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/LeaseExpireDialog;", "leasePresenter", "Lcom/pudutech/peanut/presenter/lease_task/LeasePresenter;", "getLeasePresenter", "()Lcom/pudutech/peanut/presenter/lease_task/LeasePresenter;", "leasePresenter$delegate", "Lkotlin/Lazy;", "leftTimeTips", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "onLeaseExpireForceCloseCallback", "Lkotlin/Function0;", "", "getOnLeaseExpireForceCloseCallback", "()Lkotlin/jvm/functions/Function0;", "setOnLeaseExpireForceCloseCallback", "(Lkotlin/jvm/functions/Function0;)V", "bind", "activity", "getLeftData", "mss", "", "onLeaseStatusChange", "leaseStatus", "Lcom/pudutech/peanut/presenter/lease_task/LeaseContract$LeaseStatus;", "showExpireDialog", "startCheck", "application", "Landroid/app/Application;", "unbind", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LeaseHelper implements LeaseContract.ViewInterface {
    private AppCompatActivity bindAc;
    private boolean isLeaseExpire;
    private LeaseExpireDialog leaseExpireDialog;
    private ShowTipMsgDialog leftTimeTips;
    private Function0<Unit> onLeaseExpireForceCloseCallback;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: leasePresenter$delegate, reason: from kotlin metadata */
    private final Lazy leasePresenter = LazyKt.lazy(new Function0<LeasePresenter>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.LeaseHelper$leasePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LeasePresenter invoke() {
            LeasePresenter leasePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(LeasePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(LeasePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                leasePresenter = new LeasePresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(LeasePresenter.class).toString(), leasePresenter);
            } else {
                if (!(basePresenterInterface instanceof LeasePresenter)) {
                    basePresenterInterface = null;
                }
                leasePresenter = (LeasePresenter) basePresenterInterface;
            }
            if (leasePresenter != null) {
                return leasePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.presenter.lease_task.LeasePresenter");
        }
    });

    private final LeasePresenter getLeasePresenter() {
        return (LeasePresenter) this.leasePresenter.getValue();
    }

    /* renamed from: isLeaseExpire, reason: from getter */
    public final boolean getIsLeaseExpire() {
        return this.isLeaseExpire;
    }

    public final void setLeaseExpire(boolean z) {
        this.isLeaseExpire = z;
    }

    public final Function0<Unit> getOnLeaseExpireForceCloseCallback() {
        return this.onLeaseExpireForceCloseCallback;
    }

    public final void setOnLeaseExpireForceCloseCallback(Function0<Unit> function0) {
        this.onLeaseExpireForceCloseCallback = function0;
    }

    @Override // com.pudutech.peanut.presenter.lease_task.LeaseContract.ViewInterface
    public void onLeaseStatusChange(final LeaseContract.LeaseStatus leaseStatus) {
        Intrinsics.checkParameterIsNotNull(leaseStatus, "leaseStatus");
        Pdlog.m3273d(this.TAG, "onLeaseStatusChange " + leaseStatus);
        if ((leaseStatus.getType() == UseType.LEASE || leaseStatus.getType() == UseType.TRIAL) && leaseStatus.getLeftTime() <= 0) {
            this.isLeaseExpire = true;
            ShowTipMsgDialog showTipMsgDialog = this.leftTimeTips;
            if (showTipMsgDialog != null) {
                showTipMsgDialog.dismiss();
            }
            AppCompatActivity appCompatActivity = this.bindAc;
            if (appCompatActivity != null) {
                showExpireDialog(appCompatActivity);
                return;
            }
            return;
        }
        if (leaseStatus.getNeedTipsLeftTime()) {
            this.isLeaseExpire = false;
            LeaseExpireDialog leaseExpireDialog = this.leaseExpireDialog;
            if (leaseExpireDialog != null) {
                leaseExpireDialog.dismiss();
            }
            AppCompatActivity appCompatActivity2 = this.bindAc;
            if (appCompatActivity2 != null) {
                ShowTipMsgDialog showTipMsgDialog2 = this.leftTimeTips;
                if (showTipMsgDialog2 != null && showTipMsgDialog2.isShowing()) {
                    Pdlog.m3273d(this.TAG, "leftTimeTips is showing");
                    ShowTipMsgDialog showTipMsgDialog3 = this.leftTimeTips;
                    if (showTipMsgDialog3 != null) {
                        showTipMsgDialog3.dismiss();
                    }
                }
                this.leftTimeTips = new ShowTipMsgDialog(appCompatActivity2);
                ShowTipMsgDialog showTipMsgDialog4 = this.leftTimeTips;
                if (showTipMsgDialog4 == null) {
                    Intrinsics.throwNpe();
                }
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr15_2);
                Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr15_2)");
                Object[] objArr = {getLeftData(leaseStatus.getLeftTime())};
                String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                showTipMsgDialog4.showTipMsg(format);
                ShowTipMsgDialog showTipMsgDialog5 = this.leftTimeTips;
                if (showTipMsgDialog5 == null) {
                    Intrinsics.throwNpe();
                }
                showTipMsgDialog5.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.LeaseHelper$onLeaseStatusChange$$inlined$let$lambda$1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        LeaseHelper.this.leftTimeTips = (ShowTipMsgDialog) null;
                    }
                });
                ShowTipMsgDialog showTipMsgDialog6 = this.leftTimeTips;
                if (showTipMsgDialog6 == null) {
                    Intrinsics.throwNpe();
                }
                showTipMsgDialog6.show();
                getLeasePresenter().tagShowTips();
                return;
            }
            return;
        }
        this.isLeaseExpire = false;
        ShowTipMsgDialog showTipMsgDialog7 = this.leftTimeTips;
        if (showTipMsgDialog7 != null) {
            showTipMsgDialog7.dismiss();
        }
        LeaseExpireDialog leaseExpireDialog2 = this.leaseExpireDialog;
        if (leaseExpireDialog2 != null) {
            leaseExpireDialog2.dismiss();
        }
    }

    public final void showExpireDialog(AppCompatActivity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        ShowTipMsgDialog showTipMsgDialog = this.leftTimeTips;
        if (showTipMsgDialog != null) {
            showTipMsgDialog.dismiss();
        }
        LeaseExpireDialog leaseExpireDialog = this.leaseExpireDialog;
        if (leaseExpireDialog != null && leaseExpireDialog.isShowing()) {
            Pdlog.m3273d(this.TAG, "leaseExpireDialog is showing");
            return;
        }
        this.leaseExpireDialog = new LeaseExpireDialog(activity);
        LeaseExpireDialog leaseExpireDialog2 = this.leaseExpireDialog;
        if (leaseExpireDialog2 == null) {
            Intrinsics.throwNpe();
        }
        String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr15_3);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr15_3)");
        leaseExpireDialog2.showTipMsg(string);
        LeaseExpireDialog leaseExpireDialog3 = this.leaseExpireDialog;
        if (leaseExpireDialog3 == null) {
            Intrinsics.throwNpe();
        }
        leaseExpireDialog3.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.helper.LeaseHelper$showExpireDialog$1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                LeaseExpireDialog leaseExpireDialog4;
                leaseExpireDialog4 = LeaseHelper.this.leaseExpireDialog;
                if (leaseExpireDialog4 != null) {
                    leaseExpireDialog4.setOnForceClose((Function0) null);
                }
                LeaseHelper.this.leaseExpireDialog = (LeaseExpireDialog) null;
            }
        });
        LeaseExpireDialog leaseExpireDialog4 = this.leaseExpireDialog;
        if (leaseExpireDialog4 == null) {
            Intrinsics.throwNpe();
        }
        leaseExpireDialog4.setOnForceClose(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.LeaseHelper$showExpireDialog$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Function0<Unit> onLeaseExpireForceCloseCallback = LeaseHelper.this.getOnLeaseExpireForceCloseCallback();
                if (onLeaseExpireForceCloseCallback != null) {
                    onLeaseExpireForceCloseCallback.invoke();
                }
            }
        });
        LeaseExpireDialog leaseExpireDialog5 = this.leaseExpireDialog;
        if (leaseExpireDialog5 == null) {
            Intrinsics.throwNpe();
        }
        leaseExpireDialog5.show();
    }

    private final String getLeftData(long mss) {
        long j = 86400;
        long j2 = mss / j;
        long j3 = 3600;
        long j4 = (mss % j) / j3;
        long j5 = (mss % j3) / 60;
        if (j2 > 0) {
            return String.valueOf(j2) + RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr15_4);
        }
        if (j4 > 0) {
            return String.valueOf(j4) + RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr15_5);
        }
        if (j5 > 0) {
            return String.valueOf(j5) + RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr15_6);
        }
        return "1" + RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr15_6);
    }

    public final void bind(AppCompatActivity activity) {
        Pdlog.m3273d(this.TAG, "bind");
        getLeasePresenter().replaceView(this);
        this.bindAc = activity;
    }

    public final void startCheck(Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        getLeasePresenter().checkLease(application, UrlManager.INSTANCE.getIS_LEASE_SERVER_TEST());
    }

    public final void unbind() {
        Pdlog.m3273d(this.TAG, "unbind");
        getLeasePresenter().removeView(this);
        this.bindAc = (AppCompatActivity) null;
        ShowTipMsgDialog showTipMsgDialog = this.leftTimeTips;
        if (showTipMsgDialog != null) {
            showTipMsgDialog.dismiss();
        }
        LeaseExpireDialog leaseExpireDialog = this.leaseExpireDialog;
        if (leaseExpireDialog != null) {
            leaseExpireDialog.dismiss();
        }
    }
}
