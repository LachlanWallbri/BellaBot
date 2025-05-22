package com.pudutech.lib_update.module.service;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.lib_update.base.net.ApiService;
import com.pudutech.lib_update.base.net.AppUpdateReq;
import com.pudutech.lib_update.base.net.SystemUpdateReq;
import com.pudutech.lib_update.config.UrlManager;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.model.SwitchVersionResult;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.BaseResponse;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: UpdateRequestManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J_\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J?\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u001f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001eR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/lib_update/module/service/UpdateRequestManager;", "", "()V", "api", "Lcom/pudutech/lib_update/base/net/ApiService;", "getApi", "()Lcom/pudutech/lib_update/base/net/ApiService;", "api$delegate", "Lkotlin/Lazy;", "checkSystemVersionUpdate", "Lcom/pudutech/pd_network/bean/BaseResponse;", "Lcom/pudutech/lib_update/module/model/VerionResult;", "version_name", "", "version_code", "mac", "product_name", "request_ver_name", "request_ver_code", "app_version_name", "sys_build_id", "channel_name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkVersionUpdate", "language", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "switchSoftWareVersion", "Lcom/pudutech/lib_update/module/model/SwitchVersionResult;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/lib_update/module/model/CheckUpdateRequestParams;", "(Lcom/pudutech/lib_update/module/model/CheckUpdateRequestParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class UpdateRequestManager {
    public static final UpdateRequestManager INSTANCE = new UpdateRequestManager();

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private static final Lazy api = LazyKt.lazy(new Function0<ApiService>() { // from class: com.pudutech.lib_update.module.service.UpdateRequestManager$api$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ApiService invoke() {
            return (ApiService) PdNetworkManager.f10310INSTANCE.createService(ApiService.class);
        }
    });

    private final ApiService getApi() {
        return (ApiService) api.getValue();
    }

    private UpdateRequestManager() {
    }

    public final Object checkVersionUpdate(String str, String str2, String str3, String str4, String str5, Continuation<? super BaseResponse<VerionResult>> continuation) {
        return getApi().checkUpdate(new AppUpdateReq(str, str2, str3, str4, UrlManager.getCHANNEL_NAME(), str5), continuation);
    }

    public final Object checkSystemVersionUpdate(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Continuation<? super BaseResponse<VerionResult>> continuation) {
        return getApi().checkSystemUpdate(new SystemUpdateReq(str, str2, str3, str4, str5, str6, str7, str8, str9), continuation);
    }

    public final Object switchSoftWareVersion(CheckUpdateRequestParams checkUpdateRequestParams, Continuation<? super BaseResponse<SwitchVersionResult>> continuation) {
        return getApi().switchVersion(checkUpdateRequestParams, continuation);
    }
}
