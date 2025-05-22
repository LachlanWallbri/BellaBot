package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.network.response.FeaturedBean;
import com.pudutech.disinfect.baselib.network.response.PromotionsBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: PromotionsViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR&\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\f¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/PromotionsViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "mFeaturedDada", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/pudutech/disinfect/baselib/network/response/FeaturedBean;", "getMFeaturedDada", "()Landroidx/lifecycle/MutableLiveData;", "setMFeaturedDada", "(Landroidx/lifecycle/MutableLiveData;)V", "mPromotionsDada", "Lcom/pudutech/disinfect/baselib/network/response/PromotionsBean;", "getMPromotionsDada", "setMPromotionsDada", "getFeaturedData", "", "page", "", "getPromotionsData", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PromotionsViewModel extends BaseViewModel {
    private final String TAG = "PromotionsViewModel";
    private MutableLiveData<List<PromotionsBean>> mPromotionsDada = new MutableLiveData<>();
    private MutableLiveData<List<FeaturedBean>> mFeaturedDada = new MutableLiveData<>();

    public final MutableLiveData<List<PromotionsBean>> getMPromotionsDada() {
        return this.mPromotionsDada;
    }

    public final void setMPromotionsDada(MutableLiveData<List<PromotionsBean>> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.mPromotionsDada = mutableLiveData;
    }

    public final MutableLiveData<List<FeaturedBean>> getMFeaturedDada() {
        return this.mFeaturedDada;
    }

    public final void setMFeaturedDada(MutableLiveData<List<FeaturedBean>> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.mFeaturedDada = mutableLiveData;
    }

    public final void getPromotionsData(int page) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new PromotionsViewModel$getPromotionsData$1(this, page, null), 3, null);
    }

    public final void getFeaturedData(int page) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new PromotionsViewModel$getFeaturedData$1(this, page, null), 3, null);
    }
}
