package com.pudutech.bumblebee.presenter.setting;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.setting.FunctionSettingContract;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: FunctionUpdateHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/setting/ZeroToOneMigration;", "Lcom/pudutech/bumblebee/presenter/setting/IMigration;", "()V", "TAG", "", "update", "", "newVersion", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ZeroToOneMigration extends IMigration {
    private final String TAG;

    public ZeroToOneMigration() {
        super(0, 1);
        this.TAG = "ZeroToOneMigration";
    }

    @Override // com.pudutech.bumblebee.presenter.setting.IMigration
    public void update(int newVersion) {
        Pdlog.m3273d(this.TAG, "update newVersion:" + newVersion);
        int functionSettingState = Constant.INSTANCE.getFunctionSettingState();
        Pdlog.m3273d(this.TAG, "update begin currentState: " + functionSettingState);
        int code = functionSettingState | FunctionSettingContract.FunctionType.RECYCLE_FUNCTION.getCode();
        Constant.INSTANCE.setFunctionSettingState(code);
        Pdlog.m3273d(this.TAG, "update after RECYCLE_FUNCTION currentState: " + code);
        int i = code & (~FunctionSettingContract.FunctionType.MUSIC_FUNCTION.getCode());
        Constant.INSTANCE.setFunctionSettingState(i);
        Pdlog.m3273d(this.TAG, "update after MUSIC_FUNCTION currentState: " + i);
        List<? extends FunctionSettingContract.FunctionType> mutableList = CollectionsKt.toMutableList((Collection) Constant.INSTANCE.getFunctionSettingOrdered());
        if (mutableList.contains(FunctionSettingContract.FunctionType.MUSIC_FUNCTION)) {
            mutableList.remove(FunctionSettingContract.FunctionType.MUSIC_FUNCTION);
        }
        if (!mutableList.contains(FunctionSettingContract.FunctionType.RECYCLE_FUNCTION)) {
            mutableList.add(FunctionSettingContract.FunctionType.RECYCLE_FUNCTION);
        }
        Pdlog.m3273d(this.TAG, "update getFunctionSettingOrdered:" + mutableList);
        Constant.INSTANCE.setFunctionSettingOrdered(mutableList);
    }
}
