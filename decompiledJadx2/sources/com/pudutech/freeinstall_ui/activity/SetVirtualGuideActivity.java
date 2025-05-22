package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.view.NodeProgressBar;
import com.pudutech.freeinstall_ui.viewmodel.SureMapViewModel;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: SetVirtualGuideActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0002J\b\u0010\u000f\u001a\u00020\u000bH\u0002J\u0012\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0014J\b\u0010\u0015\u001a\u00020\u000bH\u0002J\b\u0010\u0016\u001a\u00020\u000bH\u0002J\b\u0010\u0017\u001a\u00020\u000bH\u0002J\b\u0010\u0018\u001a\u00020\u000bH\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/SetVirtualGuideActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/SureMapViewModel;", "()V", "pageType", "", "getPageType", "()I", "setPageType", "(I)V", "createObserver", "", "currentActivityIsDark", "", "initIntent", "initListener", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "onDestroy", "setComplete", "setSkip", "setView", "toDoublePath", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SetVirtualGuideActivity extends BaseActivity<SureMapViewModel> {
    public static final String PAGE_TYPE_ARGUMENT = "PageTypeArgument";
    public static final String TAG = "SetVirtualGuideActivity";
    private HashMap _$_findViewCache;
    private int pageType = 3;

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

    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity
    public boolean currentActivityIsDark() {
        return true;
    }

    public final int getPageType() {
        return this.pageType;
    }

    public final void setPageType(int i) {
        this.pageType = i;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5362R.layout.activity_set_virtual_guide;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        initIntent();
        setView();
        initListener();
    }

    private final void initIntent() {
        this.pageType = getIntent().getIntExtra(PAGE_TYPE_ARGUMENT, 3);
    }

    private final void setView() {
        if (this.pageType == 3) {
            LottieAnimationView lav_guid = (LottieAnimationView) _$_findCachedViewById(C5362R.id.lav_guid);
            Intrinsics.checkExpressionValueIsNotNull(lav_guid, "lav_guid");
            lav_guid.setVisibility(0);
            LottieAnimationView lav_guid_double_path = (LottieAnimationView) _$_findCachedViewById(C5362R.id.lav_guid_double_path);
            Intrinsics.checkExpressionValueIsNotNull(lav_guid_double_path, "lav_guid_double_path");
            lav_guid_double_path.setVisibility(8);
            TextView tv_notice_title = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_title);
            Intrinsics.checkExpressionValueIsNotNull(tv_notice_title, "tv_notice_title");
            tv_notice_title.setText(getString(C5362R.string.set_virtual_wall));
            TextView tv_notice_content = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_content);
            Intrinsics.checkExpressionValueIsNotNull(tv_notice_content, "tv_notice_content");
            tv_notice_content.setText(Html.fromHtml(getString(C5362R.string.set_virtual_guide_content)));
            TextView tv_complete_two = (TextView) _$_findCachedViewById(C5362R.id.tv_complete_two);
            Intrinsics.checkExpressionValueIsNotNull(tv_complete_two, "tv_complete_two");
            tv_complete_two.setVisibility(4);
            TextView tv_complete = (TextView) _$_findCachedViewById(C5362R.id.tv_complete);
            Intrinsics.checkExpressionValueIsNotNull(tv_complete, "tv_complete");
            tv_complete.setVisibility(0);
            TextView tv_skip = (TextView) _$_findCachedViewById(C5362R.id.tv_skip);
            Intrinsics.checkExpressionValueIsNotNull(tv_skip, "tv_skip");
            tv_skip.setVisibility(0);
        } else {
            LottieAnimationView lav_guid_double_path2 = (LottieAnimationView) _$_findCachedViewById(C5362R.id.lav_guid_double_path);
            Intrinsics.checkExpressionValueIsNotNull(lav_guid_double_path2, "lav_guid_double_path");
            lav_guid_double_path2.setVisibility(0);
            LottieAnimationView lav_guid2 = (LottieAnimationView) _$_findCachedViewById(C5362R.id.lav_guid);
            Intrinsics.checkExpressionValueIsNotNull(lav_guid2, "lav_guid");
            lav_guid2.setVisibility(8);
            TextView tv_notice_title2 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_title);
            Intrinsics.checkExpressionValueIsNotNull(tv_notice_title2, "tv_notice_title");
            tv_notice_title2.setText(getString(C5362R.string.set_double_path));
            TextView tv_notice_content2 = (TextView) _$_findCachedViewById(C5362R.id.tv_notice_content);
            Intrinsics.checkExpressionValueIsNotNull(tv_notice_content2, "tv_notice_content");
            tv_notice_content2.setText(getString(C5362R.string.set_double_path_guide_content));
            TextView tv_complete_two2 = (TextView) _$_findCachedViewById(C5362R.id.tv_complete_two);
            Intrinsics.checkExpressionValueIsNotNull(tv_complete_two2, "tv_complete_two");
            tv_complete_two2.setVisibility(0);
            TextView tv_complete2 = (TextView) _$_findCachedViewById(C5362R.id.tv_complete);
            Intrinsics.checkExpressionValueIsNotNull(tv_complete2, "tv_complete");
            tv_complete2.setVisibility(8);
            TextView tv_skip2 = (TextView) _$_findCachedViewById(C5362R.id.tv_skip);
            Intrinsics.checkExpressionValueIsNotNull(tv_skip2, "tv_skip");
            tv_skip2.setVisibility(8);
        }
        ((NodeProgressBar) _$_findCachedViewById(C5362R.id.npb_step)).setNodeList(Utils.INSTANCE.getNodeData(this.pageType));
    }

    private final void initListener() {
        final TextView textView = (TextView) _$_findCachedViewById(C5362R.id.tv_complete);
        final long j = 800;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.SetVirtualGuideActivity$initListener$$inlined$singleClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView) > j || (textView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView, currentTimeMillis);
                    this.setComplete();
                }
            }
        });
        final TextView textView2 = (TextView) _$_findCachedViewById(C5362R.id.tv_skip);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.SetVirtualGuideActivity$initListener$$inlined$singleClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView2) > j || (textView2 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView2, currentTimeMillis);
                    this.setSkip();
                }
            }
        });
        final TextView textView3 = (TextView) _$_findCachedViewById(C5362R.id.tv_complete_two);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.SetVirtualGuideActivity$initListener$$inlined$singleClick$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView3) > j || (textView3 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView3, currentTimeMillis);
                    AnkoInternals.internalStartActivity(this, AddDoublePathActivity.class, new Pair[0]);
                    this.finish();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void setSkip() {
        Pdlog.m3273d(TAG, "tv_skip");
        BaseActivity.showLoadingDialog$default(this, null, false, 3, null);
        ((SureMapViewModel) getMViewModel()).getDoublePath();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setComplete() {
        Pdlog.m3273d(TAG, "tv_complete");
        AnkoInternals.internalStartActivity(this, AddVirtualActivity.class, new Pair[0]);
        finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        SetVirtualGuideActivity setVirtualGuideActivity = this;
        ((SureMapViewModel) getMViewModel()).getDoublePathLiveData().observe(setVirtualGuideActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.SetVirtualGuideActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    SetVirtualGuideActivity.this.dismissLoadingDialog();
                    SetVirtualGuideActivity.this.toDoublePath();
                } else {
                    ((SureMapViewModel) SetVirtualGuideActivity.this.getMViewModel()).setDoublePath(new ArrayList());
                }
            }
        });
        ((SureMapViewModel) getMViewModel()).getAddPathLiveData().observe(setVirtualGuideActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.SetVirtualGuideActivity$createObserver$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                SetVirtualGuideActivity.this.dismissLoadingDialog();
                AbnormalManager.INSTANCE.removeLocateStatusListener();
                AbnormalManager.INSTANCE.removeHardWareListener();
                SetVirtualGuideActivity setVirtualGuideActivity2 = SetVirtualGuideActivity.this;
                setVirtualGuideActivity2.startActivity(new Intent(setVirtualGuideActivity2, (Class<?>) MapCompleteActivity.class));
                SetVirtualGuideActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toDoublePath() {
        this.pageType = 4;
        setView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(TAG, "onDestroy");
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5362R.id.lav_guid);
        if (lottieAnimationView != null && lottieAnimationView.isAnimating()) {
            lottieAnimationView.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(C5362R.id.lav_guid_double_path);
        if (lottieAnimationView2 == null || !lottieAnimationView2.isAnimating()) {
            return;
        }
        lottieAnimationView2.cancelAnimation();
    }
}
