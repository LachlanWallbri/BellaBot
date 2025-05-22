package com.pudutech.module_robot_selfcheck.p058ui;

import android.os.Bundle;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.activity.BaseVmActivity;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.module_robot_selfcheck.C5365R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InitActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/InitActivity;", "Lcom/pudutech/disinfect/baselib/base/activity/BaseVmActivity;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "createObserver", "", "dismissLoading", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "", "showLoading", "message", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class InitActivity extends BaseVmActivity<BaseViewModel> {
    private HashMap _$_findViewCache;

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void dismissLoading() {
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void showLoading(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5365R.layout.activity_init;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        Pdlog.m3274e("initActivity", Long.valueOf(System.currentTimeMillis()));
    }
}
