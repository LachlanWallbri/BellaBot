package com.pudutech.bumblebee.presenter.robot_open_task.listener;

import com.iflytek.aiui.AIUIConstant;
import com.pudutech.bumblebee.business.base.BaseListener;
import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: CustomCallListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/CustomCallListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onAction", "", "ation", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/CustomCallAction;", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/robot/opensdk/interf/IBody;", "callback", "Lkotlin/Function1;", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface CustomCallListener extends BaseListener {
    void onAction(CustomCallAction ation, IBody content, Function1<? super Boolean, Unit> callback);

    /* compiled from: CustomCallListener.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void onAction$default(CustomCallListener customCallListener, CustomCallAction customCallAction, IBody iBody, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onAction");
            }
            if ((i & 4) != 0) {
                function1 = (Function1) null;
            }
            customCallListener.onAction(customCallAction, iBody, function1);
        }
    }
}
