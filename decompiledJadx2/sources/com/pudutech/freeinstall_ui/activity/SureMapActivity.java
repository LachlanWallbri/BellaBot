package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.view.NodeProgressBar;
import com.pudutech.freeinstall_ui.viewmodel.SureMapViewModel;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SureMapActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\tH\u0002J\u0012\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0016J\b\u0010\u0014\u001a\u00020\tH\u0014J\b\u0010\u0015\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/SureMapActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/SureMapViewModel;", "()V", "createMapType", "", "singleClickListener", "Landroid/view/View$OnClickListener;", "createObserver", "", "currentActivityIsDark", "", "initIntent", "intent", "Landroid/content/Intent;", "initListener", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "onDestroy", "setView", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SureMapActivity extends BaseActivity<SureMapViewModel> {
    public static final String TAG = "SureMapActivity";
    private HashMap _$_findViewCache;
    private int createMapType = 1;
    private final View.OnClickListener singleClickListener = new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.SureMapActivity$singleClickListener$1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            int i;
            if (view == null || view.getId() != C5362R.id.tv_complete) {
                return;
            }
            Intent intent = new Intent(SureMapActivity.this, (Class<?>) NewMapActivity.class);
            i = SureMapActivity.this.createMapType;
            intent.putExtra(Constants.TYPE_CREATE_MAP_ARGUMENTS, i);
            SureMapActivity.this.startActivity(intent);
            SureMapActivity.this.finish();
        }
    };

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
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

    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity
    public boolean currentActivityIsDark() {
        return true;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5362R.layout.activity_sure_map;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        initIntent(intent);
        setView();
        initListener();
    }

    private final void setView() {
        ((NodeProgressBar) _$_findCachedViewById(C5362R.id.npb_step)).setNodeList(Utils.INSTANCE.getNodeData(1));
    }

    private final void initListener() {
        ExtandsKt.singleClick$default((TextView) _$_findCachedViewById(C5362R.id.tv_complete), this.singleClickListener, 0L, 2, (Object) null);
    }

    private final void initIntent(Intent intent) {
        this.createMapType = intent.getIntExtra(Constants.TYPE_CREATE_MAP_ARGUMENTS, 1);
        Pdlog.m3273d(TAG, "createMapType" + this.createMapType);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(TAG, "onDestroy");
    }
}
