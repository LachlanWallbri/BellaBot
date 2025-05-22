package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.clean_task.CleanAlertContract;
import com.pudutech.bumblebee.presenter.clean_task.CleanAlertPresenter;
import com.pudutech.bumblebee.presenter.clean_task.RgbdCleanContract;
import com.pudutech.bumblebee.presenter.clean_task.RgbdCleanPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: HealthCheckHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001b\u001a\u00020\u000eH\u0002J\u0006\u0010\u001c\u001a\u00020\u000eJ\u0018\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0006\u0010#\u001a\u00020\u000eJ\u0006\u0010$\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u0018\u0010\u0019¨\u0006%"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/HealthCheckHelper;", "Lcom/pudutech/bumblebee/presenter/clean_task/CleanAlertContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/clean_task/RgbdCleanContract$ViewInterface;", "()V", "TAG", "", "cleanAlertPresenter", "Lcom/pudutech/bumblebee/presenter/clean_task/CleanAlertPresenter;", "getCleanAlertPresenter", "()Lcom/pudutech/bumblebee/presenter/clean_task/CleanAlertPresenter;", "cleanAlertPresenter$delegate", "Lkotlin/Lazy;", "onHealthAlert", "Lkotlin/Function0;", "", "getOnHealthAlert", "()Lkotlin/jvm/functions/Function0;", "setOnHealthAlert", "(Lkotlin/jvm/functions/Function0;)V", "onRgbdAlert", "getOnRgbdAlert", "setOnRgbdAlert", "rgbdCleanPresenter", "Lcom/pudutech/bumblebee/presenter/clean_task/RgbdCleanPresenter;", "getRgbdCleanPresenter", "()Lcom/pudutech/bumblebee/presenter/clean_task/RgbdCleanPresenter;", "rgbdCleanPresenter$delegate", "bindPresenter", "clearHealthCheck", "showAlert", "needClean", "", "metersFromLastTime", "", "showRgbdAlert", "startCheckOnce", "unBindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class HealthCheckHelper implements CleanAlertContract.ViewInterface, RgbdCleanContract.ViewInterface {
    private Function0<Unit> onHealthAlert;
    private Function0<Unit> onRgbdAlert;
    private final String TAG = "HealthCheckHelper";

    /* renamed from: cleanAlertPresenter$delegate, reason: from kotlin metadata */
    private final Lazy cleanAlertPresenter = LazyKt.lazy(new Function0<CleanAlertPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.HealthCheckHelper$cleanAlertPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CleanAlertPresenter invoke() {
            CleanAlertPresenter cleanAlertPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(CleanAlertPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(CleanAlertPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                cleanAlertPresenter = new CleanAlertPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(CleanAlertPresenter.class).toString(), cleanAlertPresenter);
            } else {
                if (!(basePresenterInterface instanceof CleanAlertPresenter)) {
                    basePresenterInterface = null;
                }
                cleanAlertPresenter = (CleanAlertPresenter) basePresenterInterface;
            }
            if (cleanAlertPresenter == null) {
                Intrinsics.throwNpe();
            }
            return cleanAlertPresenter;
        }
    });

    /* renamed from: rgbdCleanPresenter$delegate, reason: from kotlin metadata */
    private final Lazy rgbdCleanPresenter = LazyKt.lazy(new Function0<RgbdCleanPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.HealthCheckHelper$rgbdCleanPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RgbdCleanPresenter invoke() {
            RgbdCleanPresenter rgbdCleanPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(RgbdCleanPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(RgbdCleanPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                rgbdCleanPresenter = new RgbdCleanPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(RgbdCleanPresenter.class).toString(), rgbdCleanPresenter);
            } else {
                if (!(basePresenterInterface instanceof RgbdCleanPresenter)) {
                    basePresenterInterface = null;
                }
                rgbdCleanPresenter = (RgbdCleanPresenter) basePresenterInterface;
            }
            if (rgbdCleanPresenter == null) {
                Intrinsics.throwNpe();
            }
            return rgbdCleanPresenter;
        }
    });

    private final CleanAlertPresenter getCleanAlertPresenter() {
        return (CleanAlertPresenter) this.cleanAlertPresenter.getValue();
    }

    private final RgbdCleanPresenter getRgbdCleanPresenter() {
        return (RgbdCleanPresenter) this.rgbdCleanPresenter.getValue();
    }

    public final Function0<Unit> getOnHealthAlert() {
        return this.onHealthAlert;
    }

    public final void setOnHealthAlert(Function0<Unit> function0) {
        this.onHealthAlert = function0;
    }

    public final Function0<Unit> getOnRgbdAlert() {
        return this.onRgbdAlert;
    }

    public final void setOnRgbdAlert(Function0<Unit> function0) {
        this.onRgbdAlert = function0;
    }

    public final void startCheckOnce() {
        bindPresenter();
        getCleanAlertPresenter().actionCheck();
        getRgbdCleanPresenter().actionCheck();
    }

    private final void bindPresenter() {
        HealthCheckHelper healthCheckHelper = this;
        getCleanAlertPresenter().replaceView(healthCheckHelper);
        getRgbdCleanPresenter().replaceView(healthCheckHelper);
    }

    public final void unBindPresenter() {
        HealthCheckHelper healthCheckHelper = this;
        getCleanAlertPresenter().removeView(healthCheckHelper);
        getRgbdCleanPresenter().removeView(healthCheckHelper);
    }

    public final void clearHealthCheck() {
        Pdlog.m3273d(this.TAG, "clearHealthCheck");
        getCleanAlertPresenter().actionClean(true);
    }

    @Override // com.pudutech.bumblebee.presenter.clean_task.CleanAlertContract.ViewInterface
    public void showAlert(boolean needClean, double metersFromLastTime) {
        Function0<Unit> function0;
        Pdlog.m3273d(this.TAG, "showAlert " + needClean + " , " + metersFromLastTime);
        if (!needClean || (function0 = this.onHealthAlert) == null) {
            return;
        }
        function0.invoke();
    }

    @Override // com.pudutech.bumblebee.presenter.clean_task.RgbdCleanContract.ViewInterface
    public void showRgbdAlert(boolean needClean) {
        Function0<Unit> function0;
        Pdlog.m3273d(this.TAG, "showRgbdAlert " + needClean);
        if (!needClean || (function0 = this.onRgbdAlert) == null) {
            return;
        }
        function0.invoke();
    }
}
