package com.pudutech.bumblebee.robot_ui.p054ui.view;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleInfoPresenter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

/* compiled from: MyStatusBarLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleInfoPresenter;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MyStatusBarLayout$scheduleInfoPresenter$2 extends Lambda implements Function0<ScheduleInfoPresenter> {
    public static final MyStatusBarLayout$scheduleInfoPresenter$2 INSTANCE = new MyStatusBarLayout$scheduleInfoPresenter$2();

    MyStatusBarLayout$scheduleInfoPresenter$2() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ScheduleInfoPresenter invoke() {
        ScheduleInfoPresenter scheduleInfoPresenter;
        PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
        BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(ScheduleInfoPresenter.class).toString());
        Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(ScheduleInfoPresenter.class) + ' ' + basePresenterInterface);
        if (basePresenterInterface == null) {
            scheduleInfoPresenter = new ScheduleInfoPresenter();
            presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(ScheduleInfoPresenter.class).toString(), scheduleInfoPresenter);
        } else {
            if (!(basePresenterInterface instanceof ScheduleInfoPresenter)) {
                basePresenterInterface = null;
            }
            scheduleInfoPresenter = (ScheduleInfoPresenter) basePresenterInterface;
        }
        if (scheduleInfoPresenter == null) {
            Intrinsics.throwNpe();
        }
        return scheduleInfoPresenter;
    }
}
