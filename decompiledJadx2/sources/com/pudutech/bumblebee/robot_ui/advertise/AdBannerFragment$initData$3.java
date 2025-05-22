package com.pudutech.bumblebee.robot_ui.advertise;

import androidx.lifecycle.LifecycleOwnerKt;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.base.Pdlog;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnPageChangeListener;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* compiled from: AdBannerFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\f"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/advertise/AdBannerFragment$initData$3", "Lcom/youth/banner/listener/OnPageChangeListener;", "onPageScrollStateChanged", "", "state", "", "onPageScrolled", RequestParameters.POSITION, "positionOffset", "", "positionOffsetPixels", "onPageSelected", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AdBannerFragment$initData$3 implements OnPageChangeListener {
    final /* synthetic */ AdBannerFragment this$0;

    @Override // com.youth.banner.listener.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
    }

    @Override // com.youth.banner.listener.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdBannerFragment$initData$3(AdBannerFragment adBannerFragment) {
        this.this$0 = adBannerFragment;
    }

    @Override // com.youth.banner.listener.OnPageChangeListener
    public void onPageSelected(int position) {
        AdverPicBean adverPicBean;
        List<AdverImageBean> urls;
        AdverImageBean adverImageBean;
        Job job;
        Job launch$default;
        adverPicBean = this.this$0.mAdverImageBean;
        if (adverPicBean == null || (urls = adverPicBean.getUrls()) == null || (adverImageBean = urls.get(position)) == null) {
            return;
        }
        Pdlog.m3273d(this.this$0.getTAG(), "onPageSelected: " + adverImageBean);
        if (adverImageBean.getSwitchTime() != null) {
            long intValue = r1.intValue() * 1000;
            Banner<AdverImageBean, AdverBannerAdapter> mAdverBannerView = this.this$0.getMAdverBannerView();
            if (mAdverBannerView != null) {
                mAdverBannerView.setLoopTime(intValue);
            }
            this.this$0.playResume();
            Pdlog.m3273d(this.this$0.getTAG(), "onPageSelected() position =" + position + " loopTime =" + intValue);
        }
        if (adverImageBean.getLoadCompleted()) {
            return;
        }
        job = this.this$0.loadJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        AdBannerFragment adBannerFragment = this.this$0;
        launch$default = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(adBannerFragment), null, null, new C4189x70c338a2(null, this, position), 3, null);
        adBannerFragment.loadJob = launch$default;
    }
}
