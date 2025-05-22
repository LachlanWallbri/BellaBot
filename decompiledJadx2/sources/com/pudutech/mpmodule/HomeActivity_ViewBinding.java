package com.pudutech.mpmodule;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class HomeActivity_ViewBinding implements Unbinder {
    private HomeActivity target;
    private View view7f0b007a;
    private View view7f0b007c;
    private View view7f0b007d;

    public HomeActivity_ViewBinding(HomeActivity homeActivity) {
        this(homeActivity, homeActivity.getWindow().getDecorView());
    }

    public HomeActivity_ViewBinding(final HomeActivity homeActivity, View view) {
        this.target = homeActivity;
        homeActivity.mMainLayout = (ViewGroup) Utils.findRequiredViewAsType(view, C5441R.id.layout_main, "field 'mMainLayout'", ViewGroup.class);
        homeActivity.mMusicSwtichBtn = (Switch) Utils.findRequiredViewAsType(view, C5441R.id.sb_music, "field 'mMusicSwtichBtn'", Switch.class);
        View findRequiredView = Utils.findRequiredView(view, C5441R.id.cv_list_edit, "method 'onClickListener'");
        this.view7f0b007c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.HomeActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClickListener(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, C5441R.id.cv_music_import, "method 'onClickListener'");
        this.view7f0b007d = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.HomeActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClickListener(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, C5441R.id.cv_delete_music, "method 'onClickListener'");
        this.view7f0b007a = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.HomeActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClickListener(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        HomeActivity homeActivity = this.target;
        if (homeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeActivity.mMainLayout = null;
        homeActivity.mMusicSwtichBtn = null;
        this.view7f0b007c.setOnClickListener(null);
        this.view7f0b007c = null;
        this.view7f0b007d.setOnClickListener(null);
        this.view7f0b007d = null;
        this.view7f0b007a.setOnClickListener(null);
        this.view7f0b007a = null;
    }
}
