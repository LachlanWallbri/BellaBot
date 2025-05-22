package com.pudutech.bumblebee.presenter.general_task;

import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: PalletContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/general_task/PalletContract;", "", "PalletModel", "PresenterInterface", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface PalletContract {

    /* compiled from: PalletContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\"\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/general_task/PalletContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "checkSupportManualConfig", "", "getCheckSupportManualConfig", "()Z", "pallets", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/general_task/PalletContract$PalletModel;", "Lkotlin/collections/ArrayList;", "getPallets", "()Ljava/util/ArrayList;", "visibleNum", "", "getVisibleNum", "()I", "setPalletNum", "", "num", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        boolean getCheckSupportManualConfig();

        ArrayList<PalletModel> getPallets();

        int getVisibleNum();

        void setPalletNum(int num);
    }

    /* compiled from: PalletContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/general_task/PalletContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "showPallets", "", "visibleNum", "", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/general_task/PalletContract$PalletModel;", "Lkotlin/collections/ArrayList;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showPallets(int visibleNum, ArrayList<PalletModel> list);
    }

    /* compiled from: PalletContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/general_task/PalletContract$PalletModel;", "", "id", "", "isVisible", "", "(IZ)V", "getId", "()I", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class PalletModel {
        private final int id;
        private final boolean isVisible;

        public static /* synthetic */ PalletModel copy$default(PalletModel palletModel, int i, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = palletModel.id;
            }
            if ((i2 & 2) != 0) {
                z = palletModel.isVisible;
            }
            return palletModel.copy(i, z);
        }

        /* renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getIsVisible() {
            return this.isVisible;
        }

        public final PalletModel copy(int id, boolean isVisible) {
            return new PalletModel(id, isVisible);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PalletModel)) {
                return false;
            }
            PalletModel palletModel = (PalletModel) other;
            return this.id == palletModel.id && this.isVisible == palletModel.isVisible;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int i = this.id * 31;
            boolean z = this.isVisible;
            int i2 = z;
            if (z != 0) {
                i2 = 1;
            }
            return i + i2;
        }

        public String toString() {
            return "PalletModel(id=" + this.id + ", isVisible=" + this.isVisible + ")";
        }

        public PalletModel(int i, boolean z) {
            this.id = i;
            this.isVisible = z;
        }

        public final int getId() {
            return this.id;
        }

        public final boolean isVisible() {
            return this.isVisible;
        }
    }
}
