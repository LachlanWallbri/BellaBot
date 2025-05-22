package com.pudutech.pd_network.verify;

import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.GetSecretRes;
import com.pudutech.pd_network.bean.GetTokenRes;
import com.pudutech.pd_network.bean.InternalGetSecretReq;
import com.pudutech.pd_network.bean.InternalGetTokenReq;
import com.pudutech.pd_network.bean.PdHost;
import kotlin.Metadata;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* compiled from: Api.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J(\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\t\u001a\u00020\nH'J\u001e\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\rH'J(\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\r2\b\b\u0001\u0010\t\u001a\u00020\nH'¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/pd_network/verify/Api;", "", "getSecret", "Lretrofit2/Call;", "Lcom/pudutech/pd_network/bean/BaseResponse;", "Lcom/pudutech/pd_network/bean/GetSecretRes;", "reqData", "Lcom/pudutech/pd_network/bean/InternalGetSecretReq;", "getSecretInner", "url", "", "getToken", "Lcom/pudutech/pd_network/bean/GetTokenRes;", "Lcom/pudutech/pd_network/bean/InternalGetTokenReq;", "getTokenInner", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface Api {
    @Headers({PdHost.DEVICE_BIZ})
    @POST(ApiKt.DEVICE_GET_SECRET)
    Call<BaseResponse<GetSecretRes>> getSecret(@Body InternalGetSecretReq reqData);

    @POST
    Call<BaseResponse<GetSecretRes>> getSecretInner(@Body InternalGetSecretReq reqData, @Url String url);

    @Headers({PdHost.DEVICE_BIZ})
    @POST(ApiKt.DEVICE_REFRESH_TOKEN)
    Call<BaseResponse<GetTokenRes>> getToken(@Body InternalGetTokenReq reqData);

    @POST
    Call<BaseResponse<GetTokenRes>> getTokenInner(@Body InternalGetTokenReq reqData, @Url String url);
}
