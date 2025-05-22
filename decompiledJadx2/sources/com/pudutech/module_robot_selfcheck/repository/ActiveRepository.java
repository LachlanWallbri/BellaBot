package com.pudutech.module_robot_selfcheck.repository;

import androidx.core.view.PointerIconCompat;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.disinfect.baselib.base.BaseRepository;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.RobotActiveCodeData;
import com.pudutech.disinfect.baselib.network.req.RobotActiveCodeReq;
import com.pudutech.disinfect.baselib.network.req.RobotActiveStatusReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.RobotActiveStatusResp;
import com.pudutech.disinfect.baselib.util.MMKVConfig;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import com.pudutech.disinfect.baselib.util.NetStatusUtil;
import com.pudutech.module_robot_selfcheck.RobotInitProcessor;
import com.pudutech.module_robot_selfcheck.data.ActiveStatusWrapInfo;
import com.pudutech.module_robot_selfcheck.enums.ActiveStatus;
import com.pudutech.module_robot_selfcheck.enums.InactiveType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActiveRepository.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u0006J\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\"\u0010\f\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\r\u001a\u00020\u000eJ\"\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\n2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u0006J\u0006\u0010\u0011\u001a\u00020\u0012J5\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\t0\t2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/repository/ActiveRepository;", "Lcom/pudutech/disinfect/baselib/base/BaseRepository;", "()V", "checkLocalActiveStatus", "", SpeechUtility.TAG_RESOURCE_RESULT, "Lkotlin/Function1;", "Lcom/pudutech/module_robot_selfcheck/data/ActiveStatusWrapInfo;", "getActiveStatusByNetwork", "Lcom/pudutech/disinfect/baselib/network/response/ApiResponse;", "Lcom/pudutech/disinfect/baselib/network/response/RobotActiveStatusResp;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleActiveStatusByCode", "code", "", "handleActiveStatusResponse", "resp", "isLocalActive", "", "robotActiveByCode", "", "name", "", "pid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ActiveRepository extends BaseRepository {
    public final Object getActiveStatusByNetwork(Continuation<? super ApiResponse<RobotActiveStatusResp>> continuation) {
        return NetWorkApiManager.CloudApiService.DefaultImpls.getRobotActiveStatus$default(NetWorkApiManager.INSTANCE.getCloudApi(), new RobotActiveStatusReq(), null, continuation, 2, null);
    }

    public final Object robotActiveByCode(String str, String str2, String str3, Continuation<? super ApiResponse<ApiResponse<Object>>> continuation) {
        RobotActiveCodeReq robotActiveCodeReq = new RobotActiveCodeReq();
        robotActiveCodeReq.setData(new RobotActiveCodeData(str, str2, str3));
        return NetWorkApiManager.CloudApiService.DefaultImpls.getRobotActive$default(NetWorkApiManager.INSTANCE.getCloudApi(), robotActiveCodeReq, null, continuation, 2, null);
    }

    public final void handleActiveStatusResponse(RobotActiveStatusResp resp, Function1<? super ActiveStatusWrapInfo, Unit> result) {
        ActiveStatus activeStatus;
        Intrinsics.checkParameterIsNotNull(resp, "resp");
        Intrinsics.checkParameterIsNotNull(result, "result");
        InactiveType inactiveType = (InactiveType) null;
        String robot_type = resp.getRobot_type();
        int hashCode = robot_type.hashCode();
        if (hashCode == -1422950650) {
            if (robot_type.equals("active")) {
                MMKVManager.INSTANCE.getINSTANCE().set(MMKVConfig.KEY_IS_ACTIVE, (Object) true);
                activeStatus = ActiveStatus.Active;
            }
            inactiveType = InactiveType.ServerInactive;
            activeStatus = ActiveStatus.Inactive;
            MMKVManager.INSTANCE.getINSTANCE().set(MMKVConfig.KEY_IS_ACTIVE, (Object) false);
        } else {
            if (hashCode == -1422446064 && robot_type.equals("testing")) {
                if (resp.getOver_time() <= 0) {
                    MMKVManager.INSTANCE.getINSTANCE().set(MMKVConfig.KEY_IS_ACTIVE, (Object) false);
                    inactiveType = InactiveType.TestingTimeout;
                    activeStatus = ActiveStatus.Inactive;
                } else {
                    activeStatus = ActiveStatus.Active;
                }
            }
            inactiveType = InactiveType.ServerInactive;
            activeStatus = ActiveStatus.Inactive;
            MMKVManager.INSTANCE.getINSTANCE().set(MMKVConfig.KEY_IS_ACTIVE, (Object) false);
        }
        result.invoke(new ActiveStatusWrapInfo(activeStatus, inactiveType));
    }

    public final boolean isLocalActive() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(MMKVConfig.KEY_IS_ACTIVE);
    }

    public final void checkLocalActiveStatus(Function1<? super ActiveStatusWrapInfo, Unit> result) {
        ActiveStatusWrapInfo activeStatusWrapInfo;
        Intrinsics.checkParameterIsNotNull(result, "result");
        if (MMKVManager.INSTANCE.getINSTANCE().getBoolean(MMKVConfig.KEY_IS_ACTIVE)) {
            result.invoke(new ActiveStatusWrapInfo(ActiveStatus.Active, null, 2, null));
            return;
        }
        if (!NetStatusUtil.isConnected(RobotInitProcessor.INSTANCE.getINSTANCE().getContext$module_robot_selfcheck_release())) {
            activeStatusWrapInfo = new ActiveStatusWrapInfo(ActiveStatus.Inactive, InactiveType.NetworkUnconnected);
        } else {
            activeStatusWrapInfo = new ActiveStatusWrapInfo(ActiveStatus.Inactive, InactiveType.RequestFailed);
        }
        result.invoke(activeStatusWrapInfo);
    }

    public final void handleActiveStatusByCode(Function1<? super ActiveStatusWrapInfo, Unit> result, int code) {
        Intrinsics.checkParameterIsNotNull(result, "result");
        if (code == 0) {
            MMKVManager.INSTANCE.getINSTANCE().set(MMKVConfig.KEY_IS_ACTIVE, (Object) true);
            result.invoke(new ActiveStatusWrapInfo(ActiveStatus.Active, null, 2, null));
            return;
        }
        switch (code) {
            case 1001:
                result.invoke(new ActiveStatusWrapInfo(ActiveStatus.Inactive, InactiveType.ParamMissing));
                return;
            case 1002:
                result.invoke(new ActiveStatusWrapInfo(ActiveStatus.Inactive, InactiveType.CodeNotFound));
                return;
            case 1003:
                result.invoke(new ActiveStatusWrapInfo(ActiveStatus.Inactive, InactiveType.CodeHasUse));
                return;
            case 1004:
                result.invoke(new ActiveStatusWrapInfo(ActiveStatus.Inactive, InactiveType.StatusUnableActive));
                return;
            case 1005:
                result.invoke(new ActiveStatusWrapInfo(ActiveStatus.Inactive, InactiveType.ExceptionRecord));
                return;
            case PointerIconCompat.TYPE_CELL /* 1006 */:
                result.invoke(new ActiveStatusWrapInfo(ActiveStatus.Inactive, InactiveType.NeedFrozen));
                return;
            default:
                result.invoke(new ActiveStatusWrapInfo(ActiveStatus.Inactive, InactiveType.NetworkUnconnected));
                return;
        }
    }
}
