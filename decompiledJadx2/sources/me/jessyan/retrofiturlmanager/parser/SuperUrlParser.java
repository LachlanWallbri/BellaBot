package me.jessyan.retrofiturlmanager.parser;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.retrofiturlmanager.cache.Cache;
import me.jessyan.retrofiturlmanager.cache.LruCache;
import okhttp3.HttpUrl;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public class SuperUrlParser implements UrlParser {
    private Cache<String, String> mCache;
    private RetrofitUrlManager mRetrofitUrlManager;

    @Override // me.jessyan.retrofiturlmanager.parser.UrlParser
    public void init(RetrofitUrlManager retrofitUrlManager) {
        this.mRetrofitUrlManager = retrofitUrlManager;
        this.mCache = new LruCache(100);
    }

    @Override // me.jessyan.retrofiturlmanager.parser.UrlParser
    public HttpUrl parseUrl(HttpUrl httpUrl, HttpUrl httpUrl2) {
        if (httpUrl == null) {
            return httpUrl2;
        }
        HttpUrl.Builder newBuilder = httpUrl2.newBuilder();
        int resolvePathSize = resolvePathSize(httpUrl2, newBuilder);
        if (TextUtils.isEmpty(this.mCache.get(getKey(httpUrl, httpUrl2, resolvePathSize)))) {
            for (int i = 0; i < httpUrl2.pathSize(); i++) {
                newBuilder.removePathSegment(0);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(httpUrl.encodedPathSegments());
            if (httpUrl2.pathSize() > resolvePathSize) {
                List<String> encodedPathSegments = httpUrl2.encodedPathSegments();
                for (int i2 = resolvePathSize; i2 < encodedPathSegments.size(); i2++) {
                    arrayList.add(encodedPathSegments.get(i2));
                }
            } else if (httpUrl2.pathSize() < resolvePathSize) {
                throw new IllegalArgumentException(String.format("Your final path is %s, the pathSize = %d, but the #baseurl_path_size = %d, #baseurl_path_size must be less than or equal to pathSize of the final path", httpUrl2.scheme() + "://" + httpUrl2.host() + httpUrl2.encodedPath(), Integer.valueOf(httpUrl2.pathSize()), Integer.valueOf(resolvePathSize)));
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                newBuilder.addEncodedPathSegment((String) it.next());
            }
        } else {
            newBuilder.encodedPath(this.mCache.get(getKey(httpUrl, httpUrl2, resolvePathSize)));
        }
        HttpUrl build = newBuilder.scheme(httpUrl.scheme()).host(httpUrl.host()).port(httpUrl.port()).build();
        if (TextUtils.isEmpty(this.mCache.get(getKey(httpUrl, httpUrl2, resolvePathSize)))) {
            this.mCache.put(getKey(httpUrl, httpUrl2, resolvePathSize), build.encodedPath());
        }
        return build;
    }

    private String getKey(HttpUrl httpUrl, HttpUrl httpUrl2, int i) {
        return httpUrl.encodedPath() + httpUrl2.encodedPath() + i;
    }

    private int resolvePathSize(HttpUrl httpUrl, HttpUrl.Builder builder) {
        String fragment = httpUrl.fragment();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        if (fragment.indexOf(MqttTopic.MULTI_LEVEL_WILDCARD) == -1) {
            String[] split = fragment.split("=");
            if (split.length > 1) {
                i = Integer.parseInt(split[1]);
            }
        } else if (fragment.indexOf(RetrofitUrlManager.IDENTIFICATION_PATH_SIZE) == -1) {
            int indexOf = fragment.indexOf(MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append(fragment.substring(indexOf + 1, fragment.length()));
            String[] split2 = fragment.substring(0, indexOf).split("=");
            if (split2.length > 1) {
                i = Integer.parseInt(split2[1]);
            }
        } else {
            String[] split3 = fragment.split(RetrofitUrlManager.IDENTIFICATION_PATH_SIZE);
            stringBuffer.append(split3[0]);
            if (split3.length > 1) {
                int indexOf2 = split3[1].indexOf(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (indexOf2 != -1) {
                    stringBuffer.append(split3[1].substring(indexOf2, split3[1].length()));
                    String substring = split3[1].substring(0, indexOf2);
                    if (!TextUtils.isEmpty(substring)) {
                        i = Integer.parseInt(substring);
                    }
                } else {
                    i = Integer.parseInt(split3[1]);
                }
            }
        }
        if (TextUtils.isEmpty(stringBuffer.toString())) {
            builder.fragment(null);
        } else {
            builder.fragment(stringBuffer.toString());
        }
        return i;
    }
}
