package com.pudutech.updateService;

import com.pudutech.lib_update.base.net.HttpResult;
import com.pudutech.lib_update.module.model.SwitchVersionResult;
import com.pudutech.lib_update.module.model.VerionResult;
import io.reactivex.Observable;
import kotlin.Metadata;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/* compiled from: ApiService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001Jx\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\u000b\u001a\u00020\t2\b\b\u0001\u0010\f\u001a\u00020\t2\b\b\u0001\u0010\r\u001a\u00020\t2\b\b\u0001\u0010\u000e\u001a\u00020\t2\b\b\u0001\u0010\u000f\u001a\u00020\t2\b\b\u0001\u0010\u0010\u001a\u00020\t2\b\b\u0001\u0010\u0011\u001a\u00020\tH'JZ\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\u000b\u001a\u00020\t2\b\b\u0001\u0010\f\u001a\u00020\t2\b\b\u0001\u0010\u0011\u001a\u00020\t2\b\b\u0001\u0010\u0013\u001a\u00020\tH'JP\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\u000b\u001a\u00020\t2\b\b\u0001\u0010\f\u001a\u00020\t2\b\b\u0001\u0010\u0011\u001a\u00020\tH'JF\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\u000b\u001a\u00020\t2\b\b\u0001\u0010\f\u001a\u00020\tH'¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/updateService/ApiService;", "", "checkSystemUpdate", "Lio/reactivex/Observable;", "Lcom/pudutech/lib_update/base/net/HttpResult;", "Lcom/pudutech/lib_update/module/model/VerionResult;", "hostType", "", "version_name", "", "version_code", "mac", "product_name", "request_ver_name", "request_ver_code", "app_version_name", "sys_build_id", "channel_name", "checkUpdate", "language", "switchVersion", "Lcom/pudutech/lib_update/module/model/SwitchVersionResult;", "switchVersion2", "lib_update_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface ApiService {
    @GET("api/v1/client/update")
    Observable<HttpResult<VerionResult>> checkSystemUpdate(@Header("url_type") int hostType, @Query("version_name") String version_name, @Query("version_code") String version_code, @Query("mac") String mac, @Query("product_name") String product_name, @Query("request_ver_name") String request_ver_name, @Query("request_ver_code") String request_ver_code, @Query("app_version") String app_version_name, @Query("sys_build_id") String sys_build_id, @Query("channel_name") String channel_name);

    @GET("api/v1/client/update")
    Observable<HttpResult<VerionResult>> checkUpdate(@Header("url_type") int hostType, @Query("version_name") String version_name, @Query("version_code") String version_code, @Query("mac") String mac, @Query("product_name") String product_name, @Query("channel_name") String channel_name, @Query("language") String language);

    @GET("api/v1/client/switch")
    Observable<HttpResult<SwitchVersionResult>> switchVersion(@Header("url_type") int hostType, @Query("version_name") String version_name, @Query("version_code") String version_code, @Query("mac") String mac, @Query("product_name") String product_name, @Query("channel_name") String channel_name);

    @GET("api/v1/client/switch")
    Observable<HttpResult<SwitchVersionResult>> switchVersion2(@Header("url_type") int hostType, @Query("version_name") String version_name, @Query("version_code") String version_code, @Query("mac") String mac, @Query("product_name") String product_name);
}
