package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.network.response.TableBean;
import com.pudutech.disinfect.baselib.network.response.TableGroupBean;
import com.pudutech.peanut.robot_ui.config.Constans;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RowNumberVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\f¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/RowNumberVM;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "mGroupData", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/pudutech/disinfect/baselib/network/response/TableGroupBean;", "getMGroupData", "()Landroidx/lifecycle/MutableLiveData;", "setMGroupData", "(Landroidx/lifecycle/MutableLiveData;)V", "mTabeData", "Lcom/pudutech/disinfect/baselib/network/response/TableBean;", "getMTabeData", "setMTabeData", "getGroupTable", "", "mShopId", "", "getTableNumber", "mCode", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RowNumberVM extends BaseViewModel {
    private final String TAG = "PromotionsViewModel";
    private MutableLiveData<TableBean> mTabeData = new MutableLiveData<>();
    private MutableLiveData<List<TableGroupBean>> mGroupData = new MutableLiveData<>();

    public final MutableLiveData<TableBean> getMTabeData() {
        return this.mTabeData;
    }

    public final void setMTabeData(MutableLiveData<TableBean> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.mTabeData = mutableLiveData;
    }

    public final MutableLiveData<List<TableGroupBean>> getMGroupData() {
        return this.mGroupData;
    }

    public final void setMGroupData(MutableLiveData<List<TableGroupBean>> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.mGroupData = mutableLiveData;
    }

    public final void getGroupTable(int mShopId) {
        String shopGroup = Constans.INSTANCE.getShopGroup();
        Gson gson = new Gson();
        if (shopGroup.length() > 0) {
            Object fromJson = gson.fromJson(shopGroup, new TypeToken<List<? extends TableGroupBean>>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.RowNumberVM$getGroupTable$mGroupBean$1
            }.getType());
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "mJson.fromJson(\n        …>() {}.type\n            )");
            List<TableGroupBean> list = (List) fromJson;
            if (list != null) {
                this.mGroupData.postValue(list);
            }
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RowNumberVM$getGroupTable$2(this, mShopId, null), 3, null);
    }

    public final void getTableNumber(int mShopId, String mCode) {
        Intrinsics.checkParameterIsNotNull(mCode, "mCode");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RowNumberVM$getTableNumber$1(this, mShopId, mCode, null), 3, null);
    }
}
