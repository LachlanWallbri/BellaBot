package com.pudutech.bumblebee.robot_ui.advertise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.advertise.AdverConfig;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* compiled from: AdBannerFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0012\u0010\u0017\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001f\u001a\u00020\u0015H\u0002J\b\u0010 \u001a\u00020\u0015H\u0002J\u0010\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdBannerFragment;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseFragment;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdVm;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "loadJob", "Lkotlinx/coroutines/Job;", "mAdverBannerView", "Lcom/youth/banner/Banner;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverImageBean;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBannerAdapter;", "getMAdverBannerView", "()Lcom/youth/banner/Banner;", "setMAdverBannerView", "(Lcom/youth/banner/Banner;)V", "mAdverImageBean", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverPicBean;", "createObserver", "", "initData", "initView", "savedInstanceState", "Landroid/os/Bundle;", "layoutId", "", "onViewCreated", "view", "Landroid/view/View;", "playPause", "playResume", "updateAdverContent", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseBean;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AdBannerFragment extends AdverBaseFragment<AdVm> {
    private final String TAG = "AdBannerFragment";
    private HashMap _$_findViewCache;
    private Job loadJob;
    private Banner<AdverImageBean, AdverBannerAdapter> mAdverBannerView;
    private AdverPicBean mAdverImageBean;

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment, com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment, com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
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

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment, com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment
    public String getTAG() {
        return this.TAG;
    }

    public final Banner<AdverImageBean, AdverBannerAdapter> getMAdverBannerView() {
        return this.mAdverBannerView;
    }

    public final void setMAdverBannerView(Banner<AdverImageBean, AdverBannerAdapter> banner) {
        this.mAdverBannerView = banner;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public int layoutId() {
        return C4188R.layout.fragment_adver_banner;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void initView(Bundle savedInstanceState) {
        Banner<AdverImageBean, AdverBannerAdapter> banner = this.mAdverBannerView;
        if (banner != null) {
            FragmentActivity activity = getActivity();
            banner.setIndicator(new CircleIndicator(activity != null ? activity.getApplicationContext() : null));
        }
        Banner<AdverImageBean, AdverBannerAdapter> banner2 = this.mAdverBannerView;
        if (banner2 != null) {
            banner2.addBannerLifecycleObserver(getViewLifecycleOwner());
        }
        initComListener();
        Pdlog.m3273d(getTAG(), "initview()");
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ViewPager2 viewPager2;
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.mAdverBannerView = (Banner) view.findViewById(C4188R.id.adver_banner_view);
        Banner<AdverImageBean, AdverBannerAdapter> banner = this.mAdverBannerView;
        if (banner != null && (viewPager2 = banner.getViewPager2()) != null) {
            viewPager2.setOffscreenPageLimit(3);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void createObserver() {
        ((AdVm) getMViewModel()).getControlAdPlay().observe(this, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdBannerFragment$createObserver$$inlined$observe$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                if (((Boolean) t).booleanValue()) {
                    AdBannerFragment.this.playResume();
                } else {
                    AdBannerFragment.this.playPause();
                }
            }
        });
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void initData() {
        Banner<AdverImageBean, AdverBannerAdapter> banner;
        super.initData();
        AdverPicBean adverPicBean = this.mAdverImageBean;
        if (adverPicBean != null) {
            List<AdverImageBean> urls = adverPicBean.getUrls();
            Integer switchTime = urls.get(0).getSwitchTime();
            if (switchTime != null) {
                int intValue = switchTime.intValue();
                if (intValue > 0 && (banner = this.mAdverBannerView) != null) {
                    banner.setLoopTime(intValue * 1000);
                }
                Pdlog.m3273d(getTAG(), "initData() switchTime =" + intValue);
            }
            if (urls.size() == 1) {
                Banner<AdverImageBean, AdverBannerAdapter> banner2 = this.mAdverBannerView;
                if (banner2 != null) {
                    banner2.isAutoLoop(false);
                }
                Banner<AdverImageBean, AdverBannerAdapter> banner3 = this.mAdverBannerView;
                if (banner3 != null) {
                    banner3.setIndicatorWidth(0, 0);
                }
                Banner<AdverImageBean, AdverBannerAdapter> banner4 = this.mAdverBannerView;
                if (banner4 != null) {
                    banner4.setIndicatorHeight(0);
                }
            }
            Activity mActivity = getMActivity();
            if (mActivity != null) {
                AdverBannerAdapter adverBannerAdapter = new AdverBannerAdapter(urls, mActivity);
                Banner<AdverImageBean, AdverBannerAdapter> banner5 = this.mAdverBannerView;
                if (banner5 != null) {
                    banner5.setAdapter(adverBannerAdapter);
                }
            }
            Pdlog.m3273d(getTAG(), "urls() success =" + urls.size());
        }
        Banner<AdverImageBean, AdverBannerAdapter> banner6 = this.mAdverBannerView;
        if (banner6 != null) {
            banner6.setOnBannerListener(new OnBannerListener<AdverImageBean>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdBannerFragment$initData$2
                @Override // com.youth.banner.listener.OnBannerListener
                public final void OnBannerClick(AdverImageBean adverImageBean, int i) {
                    Function1<AdverConfig.TaskType, Unit> onOutsideTask = AdBannerFragment.this.getOnOutsideTask();
                    if (onOutsideTask != null) {
                        onOutsideTask.invoke(AdverConfig.TaskType.TaskScreen.INSTANCE);
                    }
                    if (!Intrinsics.areEqual(AdBannerFragment.this.getMSceneConfig() != null ? r3.getId() : null, AdSceneConfig.SOLICITING_PASSENGERS_MODE.getId())) {
                        LinearLayout adver_right_root = (LinearLayout) AdBannerFragment.this._$_findCachedViewById(C4188R.id.adver_right_root);
                        Intrinsics.checkExpressionValueIsNotNull(adver_right_root, "adver_right_root");
                        ViewExtKt.isShowView(adver_right_root);
                    }
                    Pdlog.m3273d(AdBannerFragment.this.getTAG(), "setOnBannerListener() TaskScreen");
                }
            });
        }
        Banner<AdverImageBean, AdverBannerAdapter> banner7 = this.mAdverBannerView;
        if (banner7 != null) {
            banner7.addOnPageChangeListener(new AdBannerFragment$initData$3(this));
        }
        AdSceneConfig mSceneConfig = getMSceneConfig();
        if (mSceneConfig != null) {
            showOutsideAdScene(mSceneConfig);
        }
        Pdlog.m3273d(getTAG(), "initData() mAdverImageBean =" + this.mAdverImageBean);
    }

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment
    public void updateAdverContent(AdverBaseBean content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        if (content instanceof AdverPicBean) {
            this.mAdverImageBean = (AdverPicBean) content;
        }
        Pdlog.m3273d(getTAG(), "updateAdverContent() mAdverImageBean =" + this.mAdverImageBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playPause() {
        Banner<AdverImageBean, AdverBannerAdapter> banner = this.mAdverBannerView;
        if (banner != null) {
            banner.isAutoLoop(false);
        }
        Banner<AdverImageBean, AdverBannerAdapter> banner2 = this.mAdverBannerView;
        if (banner2 != null) {
            banner2.stop();
        }
        Pdlog.m3273d(getTAG(), "playPause()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playResume() {
        Banner<AdverImageBean, AdverBannerAdapter> banner = this.mAdverBannerView;
        if (banner != null) {
            banner.isAutoLoop(true);
        }
        Banner<AdverImageBean, AdverBannerAdapter> banner2 = this.mAdverBannerView;
        if (banner2 != null) {
            banner2.start();
        }
        Pdlog.m3273d(getTAG(), "playResume()");
    }
}
