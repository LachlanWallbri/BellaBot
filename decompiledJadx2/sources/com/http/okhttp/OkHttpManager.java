package com.http.okhttp;

import com.http.helper.HttpCallback;
import com.http.helper.HttpFailCode;
import com.http.helper.HttpUtils;
import com.http.helper.OkHttpInterceptor;
import com.http.utils.LogUtils;
import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class OkHttpManager implements IHttpClient {
    private static final String DEFAULT_ENCODEING = "UTF-8";
    private static final String TAG = "OkHttpManager";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");
    private static final MediaType MEDIA_TYPE_NORAML_FORM = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    private static OkHttpClient defaultOkHttpClient = null;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes.dex */
    private static class SingletonHolder {
        private static final OkHttpManager INSTANCE = new OkHttpManager();

        private SingletonHolder() {
        }
    }

    public static OkHttpManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private OkHttpManager() {
        defaultOkHttpClient = createOkHttpClient();
    }

    public OkHttpClient getOkHttpClient() {
        return defaultOkHttpClient;
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new OkHttpInterceptor.RetryInterceptor(3));
        builder.addInterceptor(new OkHttpInterceptor.ExceptionInterceptor());
        builder.addInterceptor(new OkHttpInterceptor.HttpHeaderInterceptor());
        return builder.build();
    }

    @Override // com.http.okhttp.IHttpClient
    public String getSync(String str, Map<String, String> map) throws IOException {
        if (HttpUtils.isEmpty(str)) {
            throw new IllegalArgumentException("url=null");
        }
        LogUtils.print(TAG, " getSync url=" + str + ",headers=" + map);
        Request.Builder builder = new Request.Builder();
        builder.url(str);
        if (map != null && map.size() > 0) {
            builder.headers(Headers.m3987of(map));
        }
        Response execute = getOkHttpClient().newCall(builder.build()).execute();
        if (!execute.isSuccessful()) {
            throw new IOException(execute.body().string(), new HttpFailCode(execute.code()));
        }
        String string = execute.body().string();
        LogUtils.print(TAG, "getSync result=" + string);
        return string;
    }

    @Override // com.http.okhttp.IHttpClient
    public void getAsync(final String str, Map<String, String> map, final HttpCallback<IOException, String> httpCallback) throws IOException {
        if (HttpUtils.isEmpty(str)) {
            throw new IllegalArgumentException("url=null");
        }
        LogUtils.print(TAG, "getAsync url=" + str + ",headers=" + map);
        Request.Builder builder = new Request.Builder();
        builder.url(str);
        if (map != null && map.size() > 0) {
            builder.headers(Headers.m3987of(map));
        }
        getOkHttpClient().newCall(builder.build()).enqueue(new Callback() { // from class: com.http.okhttp.OkHttpManager.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                LogUtils.print(OkHttpManager.TAG, "getAsync onFailure e=" + iOException);
                HttpCallback httpCallback2 = httpCallback;
                if (httpCallback2 != null) {
                    httpCallback2.onFail(call.request().url().toString(), iOException);
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.print(OkHttpManager.TAG, "getAsync onResponse call=" + call + ",response=" + response);
                try {
                } catch (IOException e) {
                    LogUtils.print(OkHttpManager.TAG, "getAsync onResponse e=" + e);
                }
                if (response == null) {
                    LogUtils.print(OkHttpManager.TAG, "getAsync response=null.");
                    if (httpCallback != null) {
                        httpCallback.onFail(str, new IOException("getAsync Reponse Null", new HttpFailCode(-1)));
                        return;
                    }
                    return;
                }
                if (!response.isSuccessful()) {
                    LogUtils.print(OkHttpManager.TAG, "getAsync response is fail.");
                    if (httpCallback != null) {
                        httpCallback.onFail(str, new IOException(response.body().string(), new HttpFailCode(response.code())));
                        return;
                    }
                    return;
                }
                if (response != null && response.body() != null) {
                    String string = response.body().string();
                    LogUtils.print(OkHttpManager.TAG, "getAsync result=" + string);
                    if (httpCallback != null) {
                        httpCallback.onSuccess(str, string);
                        return;
                    }
                    return;
                }
                LogUtils.print(OkHttpManager.TAG, "getAsync onResponse error data.");
                HttpCallback httpCallback2 = httpCallback;
                if (httpCallback2 != null) {
                    httpCallback2.onFail(call.request().url().toString(), new IOException("ResponseDataError"));
                }
            }
        });
    }

    @Override // com.http.okhttp.IHttpClient
    public String postSync(String str, Map<String, String> map, String str2) throws IOException {
        if (HttpUtils.isEmpty(str)) {
            throw new IllegalArgumentException("url=null");
        }
        LogUtils.print("postSync url=" + str + ", headersMap=" + map + ", postData=" + str2);
        Request.Builder builder = new Request.Builder();
        builder.url(str);
        MediaType mediaType = MEDIA_TYPE_NORAML_FORM;
        if (map != null && map.size() > 0) {
            builder.headers(Headers.m3987of(map));
            if (map.get("Content-Type") != null && map.get("Content-Type").contains("application/json")) {
                mediaType = JSON;
            } else if (map.get("Content-Type") != null && map.get("Content-Type").contains("application/octet-stream")) {
                mediaType = MEDIA_TYPE_STREAM;
            }
        }
        if (str2 != null) {
            builder.post(RequestBody.create(mediaType, str2));
        }
        Response execute = getOkHttpClient().newCall(builder.build()).execute();
        if (!execute.isSuccessful()) {
            throw new IOException(execute.body().string(), new HttpFailCode(execute.code()));
        }
        String string = execute.body().string();
        LogUtils.print(TAG, "postSync result=" + string);
        return string;
    }

    @Override // com.http.okhttp.IHttpClient
    public void postAsync(final String str, Map<String, String> map, String str2, final HttpCallback<IOException, String> httpCallback) throws IOException {
        if (HttpUtils.isEmpty(str)) {
            throw new IllegalArgumentException("url=null");
        }
        LogUtils.print("postAsync url=" + str + ", headersMap=" + map + ", postData=" + str2);
        Request.Builder builder = new Request.Builder();
        builder.url(str);
        MediaType mediaType = MEDIA_TYPE_NORAML_FORM;
        if (map != null && map.size() > 0) {
            builder.headers(Headers.m3987of(map));
            if (map.get("Content-Type") != null && map.get("Content-Type").contains("application/json")) {
                mediaType = JSON;
            } else if (map.get("Content-Type") != null && map.get("Content-Type").contains("application/octet-stream")) {
                mediaType = MEDIA_TYPE_STREAM;
            }
        }
        if (str2 != null) {
            builder.post(RequestBody.create(mediaType, str2));
        }
        getOkHttpClient().newCall(builder.build()).enqueue(new Callback() { // from class: com.http.okhttp.OkHttpManager.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                LogUtils.print(OkHttpManager.TAG, "postAsync onFailure e=" + iOException);
                HttpCallback httpCallback2 = httpCallback;
                if (httpCallback2 != null) {
                    httpCallback2.onFail(call.request().url().toString(), iOException);
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.print(OkHttpManager.TAG, "postAsync onResponse call=" + call + ",response=" + response);
                try {
                } catch (IOException e) {
                    LogUtils.print(OkHttpManager.TAG, "postAsync onResponse e=" + e);
                }
                if (response == null) {
                    LogUtils.print(OkHttpManager.TAG, "postAsync response=null.");
                    if (httpCallback != null) {
                        httpCallback.onFail(str, new IOException("postAsync Reponse Null", new HttpFailCode(-1)));
                        return;
                    }
                    return;
                }
                if (!response.isSuccessful()) {
                    LogUtils.print(OkHttpManager.TAG, "postAsync response is fail.");
                    if (httpCallback != null) {
                        httpCallback.onFail(str, new IOException(response.body().string(), new HttpFailCode(response.code())));
                        return;
                    }
                    return;
                }
                if (response.body() != null) {
                    String string = response.body().string();
                    LogUtils.print(OkHttpManager.TAG, "postAsync url=" + call.request().url().toString());
                    LogUtils.print(OkHttpManager.TAG, "postAsync result=" + string);
                    if (httpCallback != null) {
                        httpCallback.onSuccess(str, string);
                        return;
                    }
                    return;
                }
                LogUtils.print(OkHttpManager.TAG, "postAsync onResponse error data.");
                HttpCallback httpCallback2 = httpCallback;
                if (httpCallback2 != null) {
                    httpCallback2.onFail(call.request().url().toString(), new IOException("ResponseDataError", new HttpFailCode(-1)));
                }
            }
        });
    }
}
