package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.setting.FunctionSettingPresenter;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

/* compiled from: HomeSettingDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingPresenter;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
final class HomeSettingDialog$functionSettingPresenter$2 extends Lambda implements Function0<FunctionSettingPresenter> {
    public static final HomeSettingDialog$functionSettingPresenter$2 INSTANCE = new HomeSettingDialog$functionSettingPresenter$2();

    HomeSettingDialog$functionSettingPresenter$2() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final FunctionSettingPresenter invoke() {
        FunctionSettingPresenter functionSettingPresenter;
        PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
        BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(FunctionSettingPresenter.class).toString());
        Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(FunctionSettingPresenter.class) + ' ' + basePresenterInterface);
        if (basePresenterInterface == null) {
            functionSettingPresenter = new FunctionSettingPresenter();
            presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(FunctionSettingPresenter.class).toString(), functionSettingPresenter);
        } else {
            if (!(basePresenterInterface instanceof FunctionSettingPresenter)) {
                basePresenterInterface = null;
            }
            functionSettingPresenter = (FunctionSettingPresenter) basePresenterInterface;
        }
        if (functionSettingPresenter != null) {
            return functionSettingPresenter;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.setting.FunctionSettingPresenter");
    }
}
