package com.pudutech.bumblebee.presenter.delivery_task;

import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliverSelectVoiceContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSelectVoiceContract;", "", "()V", "PresenterInterface", "SelectVoiceInfo", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliverSelectVoiceContract {

    /* compiled from: DeliverSelectVoiceContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSelectVoiceContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "actionComplete", "", "select", "", "actionTransform", "data", "", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void actionComplete(long select);

        void actionTransform(List<VoicePackageInfo> data);
    }

    /* compiled from: DeliverSelectVoiceContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001e\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSelectVoiceContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "complete", "", "voicePkgInfo", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "showList", "data", "", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSelectVoiceContract$SelectVoiceInfo;", "selectId", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void complete(VoicePackageInfo voicePkgInfo);

        void showList(List<SelectVoiceInfo> data, long selectId);
    }

    /* compiled from: DeliverSelectVoiceContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSelectVoiceContract$SelectVoiceInfo;", "", "voiceName", "", "checkState", "", "id", "", "(Ljava/lang/String;ZJ)V", "getCheckState", "()Z", "setCheckState", "(Z)V", "getId", "()J", "setId", "(J)V", "getVoiceName", "()Ljava/lang/String;", "setVoiceName", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class SelectVoiceInfo {
        private boolean checkState;
        private long id;
        private String voiceName;

        public static /* synthetic */ SelectVoiceInfo copy$default(SelectVoiceInfo selectVoiceInfo, String str, boolean z, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = selectVoiceInfo.voiceName;
            }
            if ((i & 2) != 0) {
                z = selectVoiceInfo.checkState;
            }
            if ((i & 4) != 0) {
                j = selectVoiceInfo.id;
            }
            return selectVoiceInfo.copy(str, z, j);
        }

        /* renamed from: component1, reason: from getter */
        public final String getVoiceName() {
            return this.voiceName;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getCheckState() {
            return this.checkState;
        }

        /* renamed from: component3, reason: from getter */
        public final long getId() {
            return this.id;
        }

        public final SelectVoiceInfo copy(String voiceName, boolean checkState, long id) {
            Intrinsics.checkParameterIsNotNull(voiceName, "voiceName");
            return new SelectVoiceInfo(voiceName, checkState, id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SelectVoiceInfo)) {
                return false;
            }
            SelectVoiceInfo selectVoiceInfo = (SelectVoiceInfo) other;
            return Intrinsics.areEqual(this.voiceName, selectVoiceInfo.voiceName) && this.checkState == selectVoiceInfo.checkState && this.id == selectVoiceInfo.id;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            String str = this.voiceName;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            boolean z = this.checkState;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode + i) * 31;
            long j = this.id;
            return i2 + ((int) (j ^ (j >>> 32)));
        }

        public String toString() {
            return "SelectVoiceInfo(voiceName=" + this.voiceName + ", checkState=" + this.checkState + ", id=" + this.id + ")";
        }

        public SelectVoiceInfo(String voiceName, boolean z, long j) {
            Intrinsics.checkParameterIsNotNull(voiceName, "voiceName");
            this.voiceName = voiceName;
            this.checkState = z;
            this.id = j;
        }

        public final String getVoiceName() {
            return this.voiceName;
        }

        public final void setVoiceName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.voiceName = str;
        }

        public final boolean getCheckState() {
            return this.checkState;
        }

        public final void setCheckState(boolean z) {
            this.checkState = z;
        }

        public final long getId() {
            return this.id;
        }

        public final void setId(long j) {
            this.id = j;
        }
    }
}
