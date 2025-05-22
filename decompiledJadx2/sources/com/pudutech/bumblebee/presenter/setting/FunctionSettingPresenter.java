package com.pudutech.bumblebee.presenter.setting;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.mvp_base.BaseMultiViewPresenter;
import com.pudutech.bumblebee.presenter.setting.FunctionSettingContract;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: FunctionSettingPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0014\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000f0\u0013H\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000fH\u0016J\u0016\u0010\u0019\u001a\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015H\u0016R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@VX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseMultiViewPresenter;", "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$PresenterInterface;", "()V", "<set-?>", "", "TAG", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "currentState", "", "checkFunctionState", "", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$FunctionType;", "loadFunctionState", "", "loadOrderedFunctions", "", "updateFunctionState", "", "state", "updateOrderedFunctions", "list", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FunctionSettingPresenter extends BaseMultiViewPresenter<FunctionSettingContract.ViewInterface> implements FunctionSettingContract.PresenterInterface {
    private String TAG = "FunctionSettingPresenter";
    private int currentState;

    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseMultiViewPresenter
    protected String getTAG() {
        return this.TAG;
    }

    public void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.TAG = str;
    }

    @Override // com.pudutech.bumblebee.presenter.setting.FunctionSettingContract.PresenterInterface
    public Map<FunctionSettingContract.FunctionType, Boolean> loadFunctionState() {
        this.currentState = Constant.INSTANCE.getFunctionSettingState();
        Pdlog.m3273d(getTAG(), "loadFunctionState currentState = " + this.currentState);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(FunctionSettingContract.FunctionType.DELIVER_FUNCTION, Boolean.valueOf((this.currentState & FunctionSettingContract.FunctionType.DELIVER_FUNCTION.getCode()) == FunctionSettingContract.FunctionType.DELIVER_FUNCTION.getCode()));
        linkedHashMap.put(FunctionSettingContract.FunctionType.CRUISE_FUNCTION, Boolean.valueOf((this.currentState & FunctionSettingContract.FunctionType.CRUISE_FUNCTION.getCode()) == FunctionSettingContract.FunctionType.CRUISE_FUNCTION.getCode()));
        linkedHashMap.put(FunctionSettingContract.FunctionType.DIRECT_DELIVER_FUNCTION, Boolean.valueOf((this.currentState & FunctionSettingContract.FunctionType.DIRECT_DELIVER_FUNCTION.getCode()) == FunctionSettingContract.FunctionType.DIRECT_DELIVER_FUNCTION.getCode()));
        linkedHashMap.put(FunctionSettingContract.FunctionType.GREETER_FUNCTION, Boolean.valueOf((this.currentState & FunctionSettingContract.FunctionType.GREETER_FUNCTION.getCode()) == FunctionSettingContract.FunctionType.GREETER_FUNCTION.getCode()));
        linkedHashMap.put(FunctionSettingContract.FunctionType.SPECIAL_FUNCTION, Boolean.valueOf((this.currentState & FunctionSettingContract.FunctionType.SPECIAL_FUNCTION.getCode()) == FunctionSettingContract.FunctionType.SPECIAL_FUNCTION.getCode()));
        linkedHashMap.put(FunctionSettingContract.FunctionType.RETURN_FUNCTION, Boolean.valueOf((this.currentState & FunctionSettingContract.FunctionType.RETURN_FUNCTION.getCode()) == FunctionSettingContract.FunctionType.RETURN_FUNCTION.getCode()));
        linkedHashMap.put(FunctionSettingContract.FunctionType.BIRTHDAY_FUNCTION, Boolean.valueOf((this.currentState & FunctionSettingContract.FunctionType.BIRTHDAY_FUNCTION.getCode()) == FunctionSettingContract.FunctionType.BIRTHDAY_FUNCTION.getCode()));
        linkedHashMap.put(FunctionSettingContract.FunctionType.MUSIC_FUNCTION, Boolean.valueOf((this.currentState & FunctionSettingContract.FunctionType.MUSIC_FUNCTION.getCode()) == FunctionSettingContract.FunctionType.MUSIC_FUNCTION.getCode()));
        linkedHashMap.put(FunctionSettingContract.FunctionType.RECYCLE_FUNCTION, Boolean.valueOf((this.currentState & FunctionSettingContract.FunctionType.RECYCLE_FUNCTION.getCode()) == FunctionSettingContract.FunctionType.RECYCLE_FUNCTION.getCode()));
        return linkedHashMap;
    }

    @Override // com.pudutech.bumblebee.presenter.setting.FunctionSettingContract.PresenterInterface
    public void updateFunctionState(final FunctionSettingContract.FunctionType value, boolean state) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        Pdlog.m3273d(getTAG(), "updateFunctionState value:" + value + ",state:" + state);
        List mutableList = CollectionsKt.toMutableList((Collection) Constant.INSTANCE.getFunctionSettingOrdered());
        if (state) {
            this.currentState |= value.getCode();
            mutableList.add(value);
        } else {
            this.currentState &= ~value.getCode();
            mutableList.removeIf(new Predicate<FunctionSettingContract.FunctionType>() { // from class: com.pudutech.bumblebee.presenter.setting.FunctionSettingPresenter$updateFunctionState$1
                @Override // java.util.function.Predicate
                public final boolean test(FunctionSettingContract.FunctionType it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it == FunctionSettingContract.FunctionType.this;
                }
            });
        }
        Pdlog.m3273d(getTAG(), "updateFunctionState value:" + value + ",state:" + state + ",currentState:" + this.currentState);
        Constant.INSTANCE.setFunctionSettingState(this.currentState);
        Constant.INSTANCE.setFunctionSettingOrdered(CollectionsKt.distinct(CollectionsKt.toList(mutableList)));
    }

    @Override // com.pudutech.bumblebee.presenter.setting.FunctionSettingContract.PresenterInterface
    public boolean checkFunctionState(FunctionSettingContract.FunctionType value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        return (Constant.INSTANCE.getFunctionSettingState() & value.getCode()) == value.getCode();
    }

    @Override // com.pudutech.bumblebee.presenter.setting.FunctionSettingContract.PresenterInterface
    public void updateOrderedFunctions(List<? extends FunctionSettingContract.FunctionType> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Constant.INSTANCE.setFunctionSettingOrdered(list);
    }

    @Override // com.pudutech.bumblebee.presenter.setting.FunctionSettingContract.PresenterInterface
    public List<FunctionSettingContract.FunctionType> loadOrderedFunctions() {
        return Constant.INSTANCE.getFunctionSettingOrdered();
    }
}
