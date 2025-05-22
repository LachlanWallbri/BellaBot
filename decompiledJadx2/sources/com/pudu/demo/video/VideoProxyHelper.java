package com.pudu.demo.video;

import android.content.Context;
import com.danikula.videocache.CacheListener;
import com.danikula.videocache.HttpProxyCacheServer;
import com.pudutech.base.Pdlog;
import com.pudutech.leaselib.utils.EncryptUtils;
import com.pudutech.tts_sdk.utils.FileUtils;
import java.io.File;
import java.net.URI;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: VideoProxyHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u0004\u0018\u00010\bJ \u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0006J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0006J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\u0006J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m3961d2 = {"Lcom/pudu/demo/video/VideoProxyHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "proxy", "Lcom/danikula/videocache/HttpProxyCacheServer;", "getHttpProxyServer", "getProxyUrl", "url", "allowCachedFileUri", "", "md5", "isCached", "newProxy", "onDestory", "", "registerCacheListener", "cacheListener", "Lcom/danikula/videocache/CacheListener;", "unregisterCacheListener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VideoProxyHelper {
    private final String TAG;
    private HttpProxyCacheServer proxy;

    public VideoProxyHelper(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "VideoProxyHelper";
        this.proxy = newProxy(context);
    }

    private final HttpProxyCacheServer newProxy(Context context) {
        HttpProxyCacheServer build = new HttpProxyCacheServer.Builder(context).cacheDirectory(CacheUtils.INSTANCE.getVideoCacheDir(context)).diskUsage(new TotalCountOrSizeLruDiskUsage(CacheUtils.DEFAULT_CACHE_LENGTH, 20)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "HttpProxyCacheServer.Bui…   )\n            .build()");
        return build;
    }

    /* renamed from: getHttpProxyServer, reason: from getter */
    public final HttpProxyCacheServer getProxy() {
        return this.proxy;
    }

    public final boolean isCached(String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer == null) {
            return false;
        }
        if (httpProxyCacheServer == null) {
            Intrinsics.throwNpe();
        }
        return httpProxyCacheServer.isCached(url);
    }

    public static /* synthetic */ String getProxyUrl$default(VideoProxyHelper videoProxyHelper, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return videoProxyHelper.getProxyUrl(str, str2);
    }

    public final String getProxyUrl(String url, String md5) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        return getProxyUrl(url, true, md5);
    }

    public static /* synthetic */ String getProxyUrl$default(VideoProxyHelper videoProxyHelper, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "";
        }
        return videoProxyHelper.getProxyUrl(str, z, str2);
    }

    public final String getProxyUrl(String url, boolean allowCachedFileUri, String md5) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer == null) {
            return url;
        }
        String file = httpProxyCacheServer.getProxyUrl(url, allowCachedFileUri);
        if (md5.length() > 0) {
            Intrinsics.checkExpressionValueIsNotNull(file, "file");
            if (StringsKt.startsWith$default(file, "file:", false, 2, (Object) null)) {
                String encryptMD5File2String = EncryptUtils.encryptMD5File2String(new File(new URI(file)));
                Pdlog.m3273d(this.TAG, "getProxyUrl fileMd5:" + encryptMD5File2String + ", md5:" + md5 + ", url:" + url + ", file:" + file);
                if (!Intrinsics.areEqual(encryptMD5File2String, md5)) {
                    FileUtils.delete(new File(new URI(file)));
                    file = httpProxyCacheServer.getProxyUrl(url, false);
                }
            }
        }
        return file != null ? file : url;
    }

    public final void registerCacheListener(CacheListener cacheListener, String url) {
        Intrinsics.checkParameterIsNotNull(cacheListener, "cacheListener");
        Intrinsics.checkParameterIsNotNull(url, "url");
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            httpProxyCacheServer.registerCacheListener(cacheListener, url);
        }
    }

    public final void unregisterCacheListener(CacheListener cacheListener, String url) {
        Intrinsics.checkParameterIsNotNull(cacheListener, "cacheListener");
        Intrinsics.checkParameterIsNotNull(url, "url");
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            httpProxyCacheServer.unregisterCacheListener(cacheListener, url);
        }
    }

    public final void onDestory() {
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            httpProxyCacheServer.shutdown();
        }
        this.proxy = (HttpProxyCacheServer) null;
    }
}
