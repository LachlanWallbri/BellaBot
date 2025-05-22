package com.pudutech.pd_network.storage;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.PdHost;
import com.pudutech.pd_network.bean.StorageConfig;
import com.pudutech.pd_network.bean.StorageRequest;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* compiled from: api.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/pd_network/storage/Api;", "", "getStorageConfig", "Lcom/pudutech/pd_network/bean/BaseResponse;", "Lcom/pudutech/pd_network/bean/StorageConfig;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/pd_network/bean/StorageRequest;", "(Lcom/pudutech/pd_network/bean/StorageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface Api {
    @Headers({PdHost.ROBOT_BIZ})
    @POST(ApiKt.GET_STORAGE_CONFIG)
    Object getStorageConfig(@Body StorageRequest storageRequest, Continuation<? super BaseResponse<StorageConfig>> continuation);
}
