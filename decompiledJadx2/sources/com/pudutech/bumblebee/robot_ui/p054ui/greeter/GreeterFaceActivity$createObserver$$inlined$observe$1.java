package com.pudutech.bumblebee.robot_ui.p054ui.greeter;

import android.app.Activity;
import android.content.Intent;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Observer;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.advertise.AdBannerFragment;
import com.pudutech.bumblebee.robot_ui.advertise.AdSceneConfig;
import com.pudutech.bumblebee.robot_ui.advertise.AdVideoFragment;
import com.pudutech.bumblebee.robot_ui.advertise.AdVm;
import com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment;
import com.pudutech.bumblebee.robot_ui.advertise.AdverComBean;
import com.pudutech.bumblebee.robot_ui.advertise.AdverConfig;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LiveData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "t", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Object;)V", "androidx/lifecycle/LiveDataKt$observe$wrappedObserver$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GreeterFaceActivity$createObserver$$inlined$observe$1<T> implements Observer<T> {
    final /* synthetic */ GreeterFaceActivity this$0;

    public GreeterFaceActivity$createObserver$$inlined$observe$1(GreeterFaceActivity greeterFaceActivity) {
        this.this$0 = greeterFaceActivity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.lifecycle.Observer
    public final void onChanged(T t) {
        final AdverBaseFragment adverBaseFragment;
        AdVm mAdVm;
        final AdverComBean adverComBean = (AdverComBean) t;
        this.this$0.mAdverType = adverComBean.getType();
        if (adverComBean.getType() == 0) {
            this.this$0.mAdverBaseFragment = new AdBannerFragment();
        } else {
            this.this$0.mAdverBaseFragment = new AdVideoFragment();
        }
        adverBaseFragment = this.this$0.mAdverBaseFragment;
        if (adverBaseFragment != null) {
            adverBaseFragment.setOnOutsideTask(new Function1<AdverConfig.TaskType, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$createObserver$$inlined$observe$1$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AdverConfig.TaskType taskType) {
                    invoke2(taskType);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AdverConfig.TaskType it) {
                    Activity mActivity;
                    Activity mActivity2;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intent intent = (Intent) null;
                    if (Intrinsics.areEqual(it, AdverConfig.TaskType.TaskContinue.INSTANCE)) {
                        mActivity2 = this.this$0.getMActivity();
                        intent = new Intent(mActivity2, (Class<?>) GreeterMenuActivity.class);
                    } else if (Intrinsics.areEqual(it, AdverConfig.TaskType.TaskFinish.INSTANCE)) {
                        mActivity = this.this$0.getMActivity();
                        intent = new Intent(mActivity, (Class<?>) GotoWelcomeAreaActivity.class);
                    }
                    if (intent != null) {
                        this.this$0.jumpAndFinish(intent);
                    }
                    Pdlog.m3273d(AdverBaseFragment.this.getTAG(), "onOutsideTask() " + it);
                }
            });
            adverBaseFragment.updateAdverContent(adverComBean.getAdverBaseBean());
            adverBaseFragment.showOutsideAdScene(AdSceneConfig.SOLICITING_PASSENGERS_MODE);
            this.this$0.showAdver();
            this.this$0.replaceFragment(adverBaseFragment, C4188R.id.adver_contain);
            if (Constans.INSTANCE.isLockedMachine()) {
                mAdVm = this.this$0.getMAdVm();
                mAdVm.controlAdPlay(false);
            }
            Pdlog.m3273d(adverBaseFragment.getTAG(), "createObserver() showAdver adverComBean =" + adverComBean);
        }
    }
}
