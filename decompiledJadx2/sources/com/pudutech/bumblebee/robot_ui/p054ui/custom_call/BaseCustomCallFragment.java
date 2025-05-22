package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import android.content.Intent;
import android.view.View;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.ICustomCallBean;
import com.pudutech.bumblebee.robot_ui.base.BaseFragment;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: BaseCustomCallFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0007H\u0016J\b\u0010\u0018\u001a\u00020\u0007H\u0016J\u0010\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001bH&R.\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR*\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\u0007\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R(\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0007\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BaseCustomCallFragment;", "Lcom/pudutech/bumblebee/robot_ui/base/BaseFragment;", "()V", "onActionState", "Lkotlin/Function2;", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallState;", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallOperationType;", "", "getOnActionState", "()Lkotlin/jvm/functions/Function2;", "setOnActionState", "(Lkotlin/jvm/functions/Function2;)V", "onFinish", "Lkotlin/Function1;", "Landroid/content/Intent;", "getOnFinish", "()Lkotlin/jvm/functions/Function1;", "setOnFinish", "(Lkotlin/jvm/functions/Function1;)V", "showStateBar", "", "getShowStateBar", "setShowStateBar", "completeCustomCall", "release", "updateCustomCallContent", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class BaseCustomCallFragment extends BaseFragment {
    private HashMap _$_findViewCache;
    private Function2<? super CustomCallState, ? super CustomCallOperationType, Unit> onActionState;
    private Function1<? super Intent, Unit> onFinish;
    private Function1<? super Boolean, Unit> showStateBar;

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void release() {
    }

    public abstract void updateCustomCallContent(ICustomCallBean content);

    public final Function1<Intent, Unit> getOnFinish() {
        return this.onFinish;
    }

    public final void setOnFinish(Function1<? super Intent, Unit> function1) {
        this.onFinish = function1;
    }

    public final Function2<CustomCallState, CustomCallOperationType, Unit> getOnActionState() {
        return this.onActionState;
    }

    public final void setOnActionState(Function2<? super CustomCallState, ? super CustomCallOperationType, Unit> function2) {
        this.onActionState = function2;
    }

    public final Function1<Boolean, Unit> getShowStateBar() {
        return this.showStateBar;
    }

    public final void setShowStateBar(Function1<? super Boolean, Unit> function1) {
        this.showStateBar = function1;
    }

    public void completeCustomCall() {
        Function2<? super CustomCallState, ? super CustomCallOperationType, Unit> function2 = this.onActionState;
        if (function2 != null) {
            function2.invoke(CustomCallState.Complete, CustomCallOperationType.remote);
        }
    }
}
