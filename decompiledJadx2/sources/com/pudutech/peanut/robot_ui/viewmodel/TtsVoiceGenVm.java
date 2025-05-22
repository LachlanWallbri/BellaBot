package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.ttsvoices.TtsCustomPkManager;
import com.pudutech.peanut.robot_ui.ttsvoices.TtsPkManager;
import com.pudutech.peanut.robot_ui.ttsvoices.bean.TtsDownInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: TtsVoiceGenVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0014J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0005H\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0007¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/TtsVoiceGenVm;", "Landroidx/lifecycle/ViewModel;", "()V", "downInfo", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsDownInfo;", "getDownInfo", "()Landroidx/lifecycle/MutableLiveData;", "downInfoModel", "Lcom/pudutech/peanut/robot_ui/viewmodel/TtsVoiceGenVm$DownInfoModel;", "needDown", "", "getNeedDown", "check", "", "down", "downCustom", "getCustomNeedDownSize", "", "getNeedDownCount", "onCleared", "saveDownInfo", "it", "sendMessage", "currentDownInfo", "DownInfoModel", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TtsVoiceGenVm extends ViewModel {
    private DownInfoModel downInfoModel;
    private final MutableLiveData<Boolean> needDown = new MutableLiveData<>();
    private final MutableLiveData<TtsDownInfo> downInfo = new MutableLiveData<>();

    public static final /* synthetic */ DownInfoModel access$getDownInfoModel$p(TtsVoiceGenVm ttsVoiceGenVm) {
        DownInfoModel downInfoModel = ttsVoiceGenVm.downInfoModel;
        if (downInfoModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downInfoModel");
        }
        return downInfoModel;
    }

    public final MutableLiveData<Boolean> getNeedDown() {
        return this.needDown;
    }

    public final MutableLiveData<TtsDownInfo> getDownInfo() {
        return this.downInfo;
    }

    public final void check() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TtsVoiceGenVm$check$1(this, null), 3, null);
    }

    private final int getCustomNeedDownSize() {
        return TtsCustomPkManager.INSTANCE.getNeedDownSize();
    }

    private final int getNeedDownCount() {
        return TtsPkManager.INSTANCE.getNeedDownSize();
    }

    public final void down() {
        this.downInfoModel = new DownInfoModel(new TtsDownInfo(0, getNeedDownCount(), 0, 4, null), new TtsDownInfo(0, getCustomNeedDownSize(), 0, 4, null));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TtsVoiceGenVm$down$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downCustom() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TtsVoiceGenVm$downCustom$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMessage(TtsDownInfo currentDownInfo) {
        DownInfoModel downInfoModel = this.downInfoModel;
        if (downInfoModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downInfoModel");
        }
        TtsDownInfo downInfo = downInfoModel.getDownInfo();
        DownInfoModel downInfoModel2 = this.downInfoModel;
        if (downInfoModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downInfoModel");
        }
        TtsDownInfo customDownInfo = downInfoModel2.getCustomDownInfo();
        this.downInfo.postValue(new TtsDownInfo(downInfo.getLeft() + customDownInfo.getLeft(), downInfo.getAll() + customDownInfo.getAll(), currentDownInfo.getCode()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveDownInfo(TtsDownInfo downInfo, TtsDownInfo it) {
        downInfo.setLeft(it.getLeft());
        downInfo.setCode(it.getCode());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        Pdlog.m3274e("TtsVoiceGenVm", String.valueOf(this.needDown.getValue()));
        TtsPkManager.INSTANCE.cancel();
        TtsCustomPkManager.INSTANCE.cancel();
    }

    /* compiled from: TtsVoiceGenVm.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/TtsVoiceGenVm$DownInfoModel;", "", "downInfo", "Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsDownInfo;", "customDownInfo", "(Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsDownInfo;Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsDownInfo;)V", "getCustomDownInfo", "()Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsDownInfo;", "setCustomDownInfo", "(Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsDownInfo;)V", "getDownInfo", "setDownInfo", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class DownInfoModel {
        private TtsDownInfo customDownInfo;
        private TtsDownInfo downInfo;

        public static /* synthetic */ DownInfoModel copy$default(DownInfoModel downInfoModel, TtsDownInfo ttsDownInfo, TtsDownInfo ttsDownInfo2, int i, Object obj) {
            if ((i & 1) != 0) {
                ttsDownInfo = downInfoModel.downInfo;
            }
            if ((i & 2) != 0) {
                ttsDownInfo2 = downInfoModel.customDownInfo;
            }
            return downInfoModel.copy(ttsDownInfo, ttsDownInfo2);
        }

        /* renamed from: component1, reason: from getter */
        public final TtsDownInfo getDownInfo() {
            return this.downInfo;
        }

        /* renamed from: component2, reason: from getter */
        public final TtsDownInfo getCustomDownInfo() {
            return this.customDownInfo;
        }

        public final DownInfoModel copy(TtsDownInfo downInfo, TtsDownInfo customDownInfo) {
            Intrinsics.checkParameterIsNotNull(downInfo, "downInfo");
            Intrinsics.checkParameterIsNotNull(customDownInfo, "customDownInfo");
            return new DownInfoModel(downInfo, customDownInfo);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownInfoModel)) {
                return false;
            }
            DownInfoModel downInfoModel = (DownInfoModel) other;
            return Intrinsics.areEqual(this.downInfo, downInfoModel.downInfo) && Intrinsics.areEqual(this.customDownInfo, downInfoModel.customDownInfo);
        }

        public int hashCode() {
            TtsDownInfo ttsDownInfo = this.downInfo;
            int hashCode = (ttsDownInfo != null ? ttsDownInfo.hashCode() : 0) * 31;
            TtsDownInfo ttsDownInfo2 = this.customDownInfo;
            return hashCode + (ttsDownInfo2 != null ? ttsDownInfo2.hashCode() : 0);
        }

        public String toString() {
            return "DownInfoModel(downInfo=" + this.downInfo + ", customDownInfo=" + this.customDownInfo + ")";
        }

        public DownInfoModel(TtsDownInfo downInfo, TtsDownInfo customDownInfo) {
            Intrinsics.checkParameterIsNotNull(downInfo, "downInfo");
            Intrinsics.checkParameterIsNotNull(customDownInfo, "customDownInfo");
            this.downInfo = downInfo;
            this.customDownInfo = customDownInfo;
        }

        public final TtsDownInfo getCustomDownInfo() {
            return this.customDownInfo;
        }

        public final TtsDownInfo getDownInfo() {
            return this.downInfo;
        }

        public final void setCustomDownInfo(TtsDownInfo ttsDownInfo) {
            Intrinsics.checkParameterIsNotNull(ttsDownInfo, "<set-?>");
            this.customDownInfo = ttsDownInfo;
        }

        public final void setDownInfo(TtsDownInfo ttsDownInfo) {
            Intrinsics.checkParameterIsNotNull(ttsDownInfo, "<set-?>");
            this.downInfo = ttsDownInfo;
        }
    }
}
