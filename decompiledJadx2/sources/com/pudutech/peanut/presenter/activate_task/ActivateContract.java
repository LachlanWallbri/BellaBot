package com.pudutech.peanut.presenter.activate_task;

import android.content.Context;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.peanut.presenter.mvp_base.BaseViewInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActivateContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/activate_task/ActivateContract;", "", "()V", "ActiveRobotInfo", "InactiveType", "PresenterInterface", "RobotActiveStatus", "ViewInterface", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ActivateContract {

    /* compiled from: ActivateContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$InactiveType;", "", "(Ljava/lang/String;I)V", "WIFI_NOT_CONNECT", "TESTING_TIMEOUT", "REQUEST_ERROR", "SERVER_STATUS", "STATUS_UNABLE_ACTIVE", "EXCEPTION_RECORD", "NEED_FROZEN", "PARAM_IS_MISSING", "CODE_NOT_FOUND", "CODE_HAS_USE", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum InactiveType {
        WIFI_NOT_CONNECT,
        TESTING_TIMEOUT,
        REQUEST_ERROR,
        SERVER_STATUS,
        STATUS_UNABLE_ACTIVE,
        EXCEPTION_RECORD,
        NEED_FROZEN,
        PARAM_IS_MISSING,
        CODE_NOT_FOUND,
        CODE_HAS_USE
    }

    /* compiled from: ActivateContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$PresenterInterface;", "Lcom/pudutech/kotlinmvp/mvp_base/BasePresenterInterface;", "checkActiveStatus", "", "context", "Landroid/content/Context;", "localActive", "code", "", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void checkActiveStatus(Context context);

        void localActive(String code);
    }

    /* compiled from: ActivateContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$RobotActiveStatus;", "", "(Ljava/lang/String;I)V", "ACTIVE", "INACTIVE", "TESTING", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum RobotActiveStatus {
        ACTIVE,
        INACTIVE,
        TESTING
    }

    /* compiled from: ActivateContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$ActiveRobotInfo;", "", "inactiveType", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$InactiveType;", "(Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$InactiveType;)V", "getInactiveType", "()Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$InactiveType;", "setInactiveType", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class ActiveRobotInfo {
        private InactiveType inactiveType;

        public static /* synthetic */ ActiveRobotInfo copy$default(ActiveRobotInfo activeRobotInfo, InactiveType inactiveType, int i, Object obj) {
            if ((i & 1) != 0) {
                inactiveType = activeRobotInfo.inactiveType;
            }
            return activeRobotInfo.copy(inactiveType);
        }

        /* renamed from: component1, reason: from getter */
        public final InactiveType getInactiveType() {
            return this.inactiveType;
        }

        public final ActiveRobotInfo copy(InactiveType inactiveType) {
            Intrinsics.checkParameterIsNotNull(inactiveType, "inactiveType");
            return new ActiveRobotInfo(inactiveType);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof ActiveRobotInfo) && Intrinsics.areEqual(this.inactiveType, ((ActiveRobotInfo) other).inactiveType);
            }
            return true;
        }

        public int hashCode() {
            InactiveType inactiveType = this.inactiveType;
            if (inactiveType != null) {
                return inactiveType.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "ActiveRobotInfo(inactiveType=" + this.inactiveType + ")";
        }

        public ActiveRobotInfo(InactiveType inactiveType) {
            Intrinsics.checkParameterIsNotNull(inactiveType, "inactiveType");
            this.inactiveType = inactiveType;
        }

        public final InactiveType getInactiveType() {
            return this.inactiveType;
        }

        public final void setInactiveType(InactiveType inactiveType) {
            Intrinsics.checkParameterIsNotNull(inactiveType, "<set-?>");
            this.inactiveType = inactiveType;
        }
    }

    /* compiled from: ActivateContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/mvp_base/BaseViewInterface;", "showActiveStatus", "", "status", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$RobotActiveStatus;", "info", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$ActiveRobotInfo;", "showLocalActiveResult", SpeechUtility.TAG_RESOURCE_RESULT, "", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showActiveStatus(RobotActiveStatus status, ActiveRobotInfo info);

        void showLocalActiveResult(boolean result);

        /* compiled from: ActivateContract.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ void showActiveStatus$default(ViewInterface viewInterface, RobotActiveStatus robotActiveStatus, ActiveRobotInfo activeRobotInfo, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showActiveStatus");
                }
                if ((i & 2) != 0) {
                    activeRobotInfo = (ActiveRobotInfo) null;
                }
                viewInterface.showActiveStatus(robotActiveStatus, activeRobotInfo);
            }
        }
    }
}
