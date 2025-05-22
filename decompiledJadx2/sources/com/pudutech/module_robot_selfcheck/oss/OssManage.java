package com.pudutech.module_robot_selfcheck.oss;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.module_robot_selfcheck.RobotInitProcessor;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Response;

/* compiled from: OssManage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0007J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0006\u0010\n\u001a\u00020\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/oss/OssManage;", "", "()V", "singleInstance", "Lcom/alibaba/sdk/android/oss/OSSClient;", "getInstance", "getOSSAuthCredentialsProvider", "Lcom/alibaba/sdk/android/oss/common/auth/OSSCredentialProvider;", "getOSSConfig", "Lcom/alibaba/sdk/android/oss/ClientConfiguration;", "getOSSCredentialsProvider", "getTime", "", "time", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class OssManage {
    private OSSClient singleInstance;

    public final OSSClient getInstance() {
        if (this.singleInstance == null) {
            synchronized (OssManage.class) {
                this.singleInstance = new OSSClient(RobotInitProcessor.INSTANCE.getINSTANCE().getContext$module_robot_selfcheck_release(), Config.INSTANCE.getOSS_ENDPOINT(), getOSSAuthCredentialsProvider(), getOSSConfig());
                Unit unit = Unit.INSTANCE;
            }
        }
        OSSClient oSSClient = this.singleInstance;
        if (oSSClient == null) {
            Intrinsics.throwNpe();
        }
        return oSSClient;
    }

    private final ClientConfiguration getOSSConfig() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(15000);
        clientConfiguration.setSocketTimeout(15000);
        clientConfiguration.setMaxConcurrentRequest(3);
        clientConfiguration.setMaxErrorRetry(0);
        return clientConfiguration;
    }

    public final OSSCredentialProvider getOSSCredentialsProvider() {
        return new PdOSSAuthCredentialsProvider(Config.INSTANCE.getSTS_SERVER_URL());
    }

    public final OSSCredentialProvider getOSSAuthCredentialsProvider() {
        return new OSSFederationCredentialProvider() { // from class: com.pudutech.module_robot_selfcheck.oss.OssManage$getOSSAuthCredentialsProvider$1
            @Override // com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider, com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
            public OSSFederationToken getFederationToken() {
                String time;
                OSSFederationToken oSSFederationToken = (OSSFederationToken) null;
                try {
                    Response execute = NetWorkApiManager.INSTANCE.getMap().getToken().execute();
                    ResponseBody responseBody = (ResponseBody) execute.body();
                    Pdlog.m3274e("OssManage", execute);
                    Pdlog.m3274e("OssManage", responseBody);
                    if (responseBody != null) {
                        String string = responseBody.string();
                        JSONObject jSONObject = new JSONObject(string);
                        Pdlog.m3274e("OssManage", string);
                        if (jSONObject.getInt("code") == 0) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                            String string2 = jSONObject2.getString("expireTime");
                            Intrinsics.checkExpressionValueIsNotNull(string2, "data.getString(\"expireTime\")");
                            String string3 = jSONObject2.getString(OSSConfig.PARAM_ACCESS_KEY_ID);
                            Intrinsics.checkExpressionValueIsNotNull(string3, "data.getString(\"accessKeyId\")");
                            String string4 = jSONObject2.getString(OSSConfig.PARAM_ACCESS_KEY_SECRET);
                            Intrinsics.checkExpressionValueIsNotNull(string4, "data.getString(\"accessKeySecret\")");
                            String string5 = jSONObject2.getString("stsToken");
                            Intrinsics.checkExpressionValueIsNotNull(string5, "data.getString(\"stsToken\")");
                            time = OssManage.this.getTime(string2);
                            OSSFederationToken oSSFederationToken2 = new OSSFederationToken(string3, string4, string5, time);
                            try {
                                String string6 = jSONObject2.getString(OSSConfig.PARAM_REGION);
                                Intrinsics.checkExpressionValueIsNotNull(string6, "data.getString(\"region\")");
                                String string7 = jSONObject2.getString(OSSConfig.PARAM_BUCKET);
                                Intrinsics.checkExpressionValueIsNotNull(string7, "data.getString(\"bucket\")");
                                Config.INSTANCE.setREGION(string6);
                                Config.INSTANCE.setOSS_ENDPOINT("http://" + string6 + ".aliyuncs.com");
                                Config.INSTANCE.setBUCKET_NAME(string7);
                                oSSFederationToken = oSSFederationToken2;
                            } catch (Exception e) {
                                e = e;
                                oSSFederationToken = oSSFederationToken2;
                                e.printStackTrace();
                                e.printStackTrace();
                                Pdlog.m3274e("OssManage", Unit.INSTANCE);
                                Pdlog.m3274e("OssManage", e.getMessage());
                                Pdlog.m3274e("OssManage", oSSFederationToken);
                                return oSSFederationToken;
                            }
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                }
                Pdlog.m3274e("OssManage", oSSFederationToken);
                return oSSFederationToken;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getTime(String time) {
        long currentTimeMillis = StringsKt.toLongOrNull(time) == null ? System.currentTimeMillis() : System.currentTimeMillis() + (Long.parseLong(time) * 1000);
        Pdlog.m3274e("time", Long.valueOf(currentTimeMillis));
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = simpleDateFormat.format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "simpleDateFormat.format(data)");
        return format;
    }
}
