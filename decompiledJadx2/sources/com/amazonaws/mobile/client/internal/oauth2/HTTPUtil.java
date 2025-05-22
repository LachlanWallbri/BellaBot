package com.amazonaws.mobile.client.internal.oauth2;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.amazonaws.mobileconnectors.cognitoauth.exceptions.AuthClientException;
import com.amazonaws.mobileconnectors.cognitoauth.exceptions.AuthServiceException;
import com.amazonaws.util.StringUtils;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OAuth2Client.java */
/* loaded from: classes.dex */
class HTTPUtil {
    HTTPUtil() {
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x012b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String httpPost(URL url, Map<String, String> map, Map<String, String> map2, String str) throws Exception {
        BufferedReader bufferedReader;
        InputStream errorStream;
        if (url == null || map2 == null || map2.size() < 1) {
            throw new AuthClientException("Invalid http request parameters");
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        DataOutputStream dataOutputStream = null;
        try {
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoOutput(true);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpsURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
            }
            httpsURLConnection.addRequestProperty("x-amz-user-agent", str != null ? str : AWSMobileClient.DEFAULT_USER_AGENT);
            if (str == null) {
                str = httpsURLConnection.getRequestProperty("User-Agent") + " " + AWSMobileClient.DEFAULT_USER_AGENT;
            }
            httpsURLConnection.setRequestProperty("User-Agent", str);
            StringBuilder sb = new StringBuilder();
            int size = map2.size();
            for (Map.Entry<String, String> entry2 : map2.entrySet()) {
                sb.append(URLEncoder.encode(entry2.getKey(), "UTF-8"));
                sb.append('=');
                sb.append(URLEncoder.encode(entry2.getValue(), "UTF-8"));
                int i = size - 1;
                if (size > 1) {
                    sb.append(Typography.amp);
                }
                size = i;
            }
            String sb2 = sb.toString();
            DataOutputStream dataOutputStream2 = new DataOutputStream(httpsURLConnection.getOutputStream());
            try {
                dataOutputStream2.writeBytes(sb2);
                dataOutputStream2.flush();
                httpsURLConnection.getHeaderFields();
                int responseCode = httpsURLConnection.getResponseCode();
                if (responseCode >= 200 && responseCode < 500) {
                    if (responseCode < 400) {
                        errorStream = httpsURLConnection.getInputStream();
                    } else {
                        errorStream = httpsURLConnection.getErrorStream();
                    }
                    bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                    try {
                        StringBuilder sb3 = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                sb3.append(readLine);
                            } else {
                                String sb4 = sb3.toString();
                                dataOutputStream2.close();
                                bufferedReader.close();
                                return sb4;
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        dataOutputStream = dataOutputStream2;
                        try {
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        dataOutputStream = dataOutputStream2;
                        if (dataOutputStream != null) {
                        }
                        if (bufferedReader != null) {
                        }
                        throw th;
                    }
                } else {
                    throw new AuthServiceException(httpsURLConnection.getResponseMessage());
                }
            } catch (Exception e2) {
                e = e2;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Exception e3) {
            e = e3;
            bufferedReader = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
    }

    public static OAuth2Tokens parseHttpResponse(String str) throws JSONException {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Invalid (null) response from Amazon Cognito Auth endpoint");
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString(OAuth2Constants.TokenResponseFields.ERROR.toString(), null);
        String optString2 = jSONObject.optString(OAuth2Constants.TokenResponseFields.ERROR_DESCRIPTION.toString(), null);
        String optString3 = jSONObject.optString(OAuth2Constants.TokenResponseFields.ERROR_URI.toString(), null);
        if (optString != null) {
            throw new OAuth2Exception("Failed to exchange code for tokens", optString, optString2, optString3);
        }
        String optString4 = jSONObject.optString(OAuth2Constants.TokenResponseFields.EXPIRES_IN.toString());
        return new OAuth2Tokens(jSONObject.getString(OAuth2Constants.TokenResponseFields.ACCESS_TOKEN.toString()), jSONObject.optString(OAuth2Constants.TokenResponseFields.ID_TOKEN.toString(), null), jSONObject.optString(OAuth2Constants.TokenResponseFields.REFRESH_TOKEN.toString(), null), jSONObject.getString(OAuth2Constants.TokenResponseFields.TOKEN_TYPE.toString()), !StringUtils.isBlank(optString4) ? Long.valueOf(Long.parseLong(optString4)) : null, Long.valueOf(System.currentTimeMillis() / 1000), jSONObject.optString(OAuth2Constants.SCOPES, null));
    }
}
