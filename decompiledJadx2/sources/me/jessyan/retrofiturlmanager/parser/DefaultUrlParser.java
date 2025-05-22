package me.jessyan.retrofiturlmanager.parser;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.HttpUrl;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public class DefaultUrlParser implements UrlParser {
    private volatile UrlParser mAdvancedUrlParser;
    private UrlParser mDomainUrlParser;
    private RetrofitUrlManager mRetrofitUrlManager;
    private volatile UrlParser mSuperUrlParser;

    @Override // me.jessyan.retrofiturlmanager.parser.UrlParser
    public void init(RetrofitUrlManager retrofitUrlManager) {
        this.mRetrofitUrlManager = retrofitUrlManager;
        DomainUrlParser domainUrlParser = new DomainUrlParser();
        this.mDomainUrlParser = domainUrlParser;
        domainUrlParser.init(retrofitUrlManager);
    }

    @Override // me.jessyan.retrofiturlmanager.parser.UrlParser
    public HttpUrl parseUrl(HttpUrl httpUrl, HttpUrl httpUrl2) {
        if (httpUrl == null) {
            return httpUrl2;
        }
        if (httpUrl2.toString().contains(RetrofitUrlManager.IDENTIFICATION_PATH_SIZE)) {
            if (this.mSuperUrlParser == null) {
                synchronized (this) {
                    if (this.mSuperUrlParser == null) {
                        this.mSuperUrlParser = new SuperUrlParser();
                        this.mSuperUrlParser.init(this.mRetrofitUrlManager);
                    }
                }
            }
            return this.mSuperUrlParser.parseUrl(httpUrl, httpUrl2);
        }
        if (this.mRetrofitUrlManager.isAdvancedModel()) {
            if (this.mAdvancedUrlParser == null) {
                synchronized (this) {
                    if (this.mAdvancedUrlParser == null) {
                        this.mAdvancedUrlParser = new AdvancedUrlParser();
                        this.mAdvancedUrlParser.init(this.mRetrofitUrlManager);
                    }
                }
            }
            return this.mAdvancedUrlParser.parseUrl(httpUrl, httpUrl2);
        }
        return this.mDomainUrlParser.parseUrl(httpUrl, httpUrl2);
    }
}
