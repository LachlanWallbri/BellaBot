package me.jessyan.retrofiturlmanager.parser;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.HttpUrl;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface UrlParser {
    void init(RetrofitUrlManager retrofitUrlManager);

    HttpUrl parseUrl(HttpUrl httpUrl, HttpUrl httpUrl2);
}
