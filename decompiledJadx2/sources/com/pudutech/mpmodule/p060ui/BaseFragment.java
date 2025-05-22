package com.pudutech.mpmodule.p060ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.pudutech.mpmodule.NavigationBarUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public abstract class BaseFragment extends Fragment {
    boolean hasInit = false;
    boolean hasRelease = false;
    protected int mLayoutResId = -1;
    protected View mLayoutView = null;
    private Unbinder unbinder;

    protected void initData() {
    }

    protected void initWidget() {
    }

    protected void onRelease() {
    }

    protected void requestData() {
    }

    protected void setListeners() {
    }

    protected abstract void setRootView();

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.hasInit = false;
        this.hasRelease = false;
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        if (!this.hasInit) {
            this.hasInit = true;
            setRootView();
            if (this.mLayoutView == null && -1 != (i = this.mLayoutResId)) {
                this.mLayoutView = layoutInflater.inflate(i, viewGroup, false);
            }
            this.unbinder = ButterKnife.bind(this, this.mLayoutView);
            initData();
            initWidget();
            setListeners();
            requestData();
        } else if (this.mLayoutView.getParent() != null) {
            ((ViewGroup) this.mLayoutView.getParent()).removeView(this.mLayoutView);
        }
        return this.mLayoutView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Window window = getActivity().getWindow();
        if (window != null) {
            NavigationBarUtil.focusNotAle(window);
            NavigationBarUtil.hideNavigationBar(window);
            NavigationBarUtil.clearFocusNotAle(window);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.unbinder.unbind();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (!this.hasRelease) {
            onRelease();
            this.hasRelease = true;
        }
        super.onDestroy();
    }

    public void setPageView(int i) {
        this.mLayoutResId = i;
    }

    public void setPageView(View view) {
        this.mLayoutView = view;
    }
}
