package com.pudutech.bumblebee.presenter.lease_task;

import android.app.Application;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import com.pudutech.leaselib.UseType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LeaseContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/lease_task/LeaseContract;", "", "LeaseStatus", "PresenterInterface", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface LeaseContract {

    /* compiled from: LeaseContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/lease_task/LeaseContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "onLeaseStatusChange", "", "leaseStatus", "Lcom/pudutech/bumblebee/presenter/lease_task/LeaseContract$LeaseStatus;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void onLeaseStatusChange(LeaseStatus leaseStatus);
    }

    /* compiled from: LeaseContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/lease_task/LeaseContract$LeaseStatus;", "", "type", "Lcom/pudutech/leaselib/UseType;", AUserTrack.UTKEY_END_TIME, "", "leftTime", "needTipsLeftTime", "", "(Lcom/pudutech/leaselib/UseType;JJZ)V", "getEndTime", "()J", "getLeftTime", "getNeedTipsLeftTime", "()Z", "getType", "()Lcom/pudutech/leaselib/UseType;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class LeaseStatus {
        private final long endTime;
        private final long leftTime;
        private final boolean needTipsLeftTime;
        private final UseType type;

        public static /* synthetic */ LeaseStatus copy$default(LeaseStatus leaseStatus, UseType useType, long j, long j2, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                useType = leaseStatus.type;
            }
            if ((i & 2) != 0) {
                j = leaseStatus.endTime;
            }
            long j3 = j;
            if ((i & 4) != 0) {
                j2 = leaseStatus.leftTime;
            }
            long j4 = j2;
            if ((i & 8) != 0) {
                z = leaseStatus.needTipsLeftTime;
            }
            return leaseStatus.copy(useType, j3, j4, z);
        }

        /* renamed from: component1, reason: from getter */
        public final UseType getType() {
            return this.type;
        }

        /* renamed from: component2, reason: from getter */
        public final long getEndTime() {
            return this.endTime;
        }

        /* renamed from: component3, reason: from getter */
        public final long getLeftTime() {
            return this.leftTime;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getNeedTipsLeftTime() {
            return this.needTipsLeftTime;
        }

        public final LeaseStatus copy(UseType type, long endTime, long leftTime, boolean needTipsLeftTime) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            return new LeaseStatus(type, endTime, leftTime, needTipsLeftTime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LeaseStatus)) {
                return false;
            }
            LeaseStatus leaseStatus = (LeaseStatus) other;
            return Intrinsics.areEqual(this.type, leaseStatus.type) && this.endTime == leaseStatus.endTime && this.leftTime == leaseStatus.leftTime && this.needTipsLeftTime == leaseStatus.needTipsLeftTime;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            UseType useType = this.type;
            int hashCode = useType != null ? useType.hashCode() : 0;
            long j = this.endTime;
            int i = ((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31;
            long j2 = this.leftTime;
            int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            boolean z = this.needTipsLeftTime;
            int i3 = z;
            if (z != 0) {
                i3 = 1;
            }
            return i2 + i3;
        }

        public String toString() {
            return "LeaseStatus(type=" + this.type + ", endTime=" + this.endTime + ", leftTime=" + this.leftTime + ", needTipsLeftTime=" + this.needTipsLeftTime + ")";
        }

        public LeaseStatus(UseType type, long j, long j2, boolean z) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.type = type;
            this.endTime = j;
            this.leftTime = j2;
            this.needTipsLeftTime = z;
        }

        public final UseType getType() {
            return this.type;
        }

        public final long getEndTime() {
            return this.endTime;
        }

        public final long getLeftTime() {
            return this.leftTime;
        }

        public final boolean getNeedTipsLeftTime() {
            return this.needTipsLeftTime;
        }
    }

    /* compiled from: LeaseContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/lease_task/LeaseContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "checkLease", "", "context", "Landroid/app/Application;", "isTest", "", "tagShowTips", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void checkLease(Application context, boolean isTest);

        void tagShowTips();

        /* compiled from: LeaseContract.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ void checkLease$default(PresenterInterface presenterInterface, Application application, boolean z, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkLease");
                }
                if ((i & 2) != 0) {
                    z = false;
                }
                presenterInterface.checkLease(application, z);
            }
        }
    }
}
