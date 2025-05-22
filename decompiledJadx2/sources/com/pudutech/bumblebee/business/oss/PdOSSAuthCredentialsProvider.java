package com.pudutech.bumblebee.business.oss;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* loaded from: classes4.dex */
class PdOSSAuthCredentialsProvider extends OSSFederationCredentialProvider {
    private String mAuthServerUrl;
    private AuthDecoder mDecoder;

    /* loaded from: classes4.dex */
    public interface AuthDecoder {
        String decode(String str);
    }

    public PdOSSAuthCredentialsProvider(String str) {
        this.mAuthServerUrl = str;
    }

    public void setAuthServerUrl(String str) {
        this.mAuthServerUrl = str;
    }

    public void setDecoder(AuthDecoder authDecoder) {
        this.mDecoder = authDecoder;
    }

    @Override // com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider, com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
    public OSSFederationToken getFederationToken() throws ClientException {
        try {
            URL url = new URL(this.mAuthServerUrl);
            Pdlog.m3274e("DDD", this.mAuthServerUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestMethod("POST");
            String readStreamAsString = IOUtils.readStreamAsString(httpURLConnection.getInputStream(), "utf-8");
            if (this.mDecoder != null) {
                readStreamAsString = this.mDecoder.decode(readStreamAsString);
            }
            JSONObject jSONObject = new JSONObject(readStreamAsString);
            int i = jSONObject.getInt("code");
            Pdlog.m3274e("CODE", Integer.valueOf(i));
            if (i == 0) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                String string = jSONObject2.getString(OSSConfig.PARAM_ACCESS_KEY_ID);
                String string2 = jSONObject2.getString(OSSConfig.PARAM_ACCESS_KEY_SECRET);
                String string3 = jSONObject2.getString("stsToken");
                String string4 = jSONObject2.getString("expireTime");
                OSSFederationToken oSSFederationToken = new OSSFederationToken(string, string2, string3, getTime(string4));
                Pdlog.m3274e("TOKEN", oSSFederationToken, string4);
                return oSSFederationToken;
            }
            Pdlog.m3274e("decodeFailure", jSONObject);
            return null;
        } catch (Exception e) {
            throw new ClientException(e);
        }
    }

    private String getTime(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (StringsKt.toLongOrNull(str) != null) {
            currentTimeMillis += Long.parseLong(str) * 1000;
        }
        String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date(currentTimeMillis));
        Intrinsics.checkExpressionValueIsNotNull(format, "simpleDateFormat.format(data)");
        return format;
    }
}
