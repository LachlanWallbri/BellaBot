package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.general_task.GeneralPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: TouchEventDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/TouchEventDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "generalPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/GeneralPresenter;", "getGeneralPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/GeneralPresenter;", "generalPresenter$delegate", "Lkotlin/Lazy;", "dispatchTouchEvent", "", "ev", "Landroid/view/MotionEvent;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public class TouchEventDialog extends Dialog {

    /* renamed from: generalPresenter$delegate, reason: from kotlin metadata */
    private final Lazy generalPresenter;

    private final GeneralPresenter getGeneralPresenter() {
        return (GeneralPresenter) this.generalPresenter.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchEventDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.generalPresenter = LazyKt.lazy(new Function0<GeneralPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.TouchEventDialog$generalPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GeneralPresenter invoke() {
                GeneralPresenter generalPresenter;
                PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(GeneralPresenter.class).toString());
                Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(GeneralPresenter.class) + ' ' + basePresenterInterface);
                if (basePresenterInterface == null) {
                    generalPresenter = new GeneralPresenter();
                    presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(GeneralPresenter.class).toString(), generalPresenter);
                } else {
                    if (!(basePresenterInterface instanceof GeneralPresenter)) {
                        basePresenterInterface = null;
                    }
                    generalPresenter = (GeneralPresenter) basePresenterInterface;
                }
                if (generalPresenter == null) {
                    Intrinsics.throwNpe();
                }
                return generalPresenter;
            }
        });
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
        return super.dispatchTouchEvent(ev);
    }
}
