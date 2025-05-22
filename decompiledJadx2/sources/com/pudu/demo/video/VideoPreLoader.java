package com.pudu.demo.video;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.pudutech.base.Pdlog;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: VideoPreLoader.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0002J\u0006\u0010\u0016\u001a\u00020\u0014J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\b\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudu/demo/video/VideoPreLoader;", "", "context", "Landroid/content/Context;", "originalUrls", "", "", "(Landroid/content/Context;Ljava/util/List;)V", "TAG", "getTAG", "()Ljava/lang/String;", "isCancel", "", "mContext", "mList", "mScrop", "Lkotlinx/coroutines/CoroutineScope;", "mVideoProxyHelper", "Lcom/pudu/demo/video/VideoProxyHelper;", "realDownLoad", "", "proxyUrl", "reqPreLoader", "setCancel", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VideoPreLoader {
    private final String TAG;
    private boolean isCancel;
    private Context mContext;
    private List<String> mList;
    private CoroutineScope mScrop;
    private VideoProxyHelper mVideoProxyHelper;

    public final String getTAG() {
        return this.TAG;
    }

    public VideoPreLoader(Context context, List<String> list) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "VideoPreLoader";
        this.mScrop = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        if (context instanceof Activity) {
            Context applicationContext = ((Activity) context).getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
            this.mContext = applicationContext;
        } else {
            this.mContext = context;
        }
        this.mVideoProxyHelper = new VideoProxyHelper(context);
        this.mList = list;
    }

    public final void setCancel(boolean isCancel) {
        this.isCancel = isCancel;
        new Handler().postDelayed(new Runnable() { // from class: com.pudu.demo.video.VideoPreLoader$setCancel$1
            @Override // java.lang.Runnable
            public final void run() {
                VideoProxyHelper videoProxyHelper;
                CoroutineScope coroutineScope;
                videoProxyHelper = VideoPreLoader.this.mVideoProxyHelper;
                videoProxyHelper.onDestory();
                coroutineScope = VideoPreLoader.this.mScrop;
                CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
            }
        }, 10L);
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [T, java.lang.String] */
    public final void reqPreLoader() {
        List<String> list = this.mList;
        if (list != null) {
            if (list.size() <= 0) {
                Pdlog.m3273d(this.TAG, "reqPreLoader--mList没有数据");
                return;
            }
            for (String str : list) {
                if (CacheUtils.INSTANCE.isCacheFile(this.mContext, str)) {
                    Pdlog.m3273d(this.TAG, "reqPreLoader--已经加载");
                } else {
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = VideoProxyHelper.getProxyUrl$default(this.mVideoProxyHelper, str, false, null, 4, null);
                    BuildersKt__Builders_commonKt.launch$default(this.mScrop, null, null, new VideoPreLoader$reqPreLoader$$inlined$let$lambda$1(objectRef, null, this), 3, null);
                }
            }
            return;
        }
        Pdlog.m3273d(this.TAG, "reqPreLoader--mList is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void realDownLoad(String proxyUrl) {
        Throwable th;
        HttpURLConnection httpURLConnection;
        int read;
        if (TextUtils.isEmpty(proxyUrl)) {
            return;
        }
        HttpURLConnection httpURLConnection2 = (HttpURLConnection) null;
        try {
            try {
                URLConnection openConnection = new URL(proxyUrl).openConnection();
                if (openConnection == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
                }
                httpURLConnection = (HttpURLConnection) openConnection;
                try {
                    httpURLConnection.connect();
                    long contentLength = httpURLConnection.getContentLength();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    byte[] bArr = new byte[2048];
                    int i = 0;
                    do {
                        read = inputStream.read(bArr);
                        i += read;
                        Pdlog.m3273d(this.TAG, "reqPreLoader--正在加载：" + i);
                        if (!this.isCancel) {
                            if (i >= contentLength) {
                                break;
                            }
                        } else {
                            Log.d(this.TAG, "realDownLoad--isCancel--取消缓存--url--" + proxyUrl);
                            Pdlog.m3273d(this.TAG, "realDownLoad--isCancel--取消缓存--url--" + proxyUrl);
                            break;
                        }
                    } while (read != -1);
                    Log.d(this.TAG, "realDownLoad--loadEnd--取消缓存--url--" + proxyUrl);
                    Pdlog.m3273d(this.TAG, "realDownLoad--loadEnd--取消缓存--url--" + proxyUrl);
                    inputStream.close();
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.getInputStream().close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        httpURLConnection.disconnect();
                    }
                } catch (Exception e2) {
                    e = e2;
                    httpURLConnection2 = httpURLConnection;
                    String message = e.getMessage();
                    if (message == null) {
                        Intrinsics.throwNpe();
                    }
                    Log.e("VideoPreLoader", message);
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.getInputStream().close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        httpURLConnection2.disconnect();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (httpURLConnection == null) {
                        throw th;
                    }
                    try {
                        httpURLConnection.getInputStream().close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    httpURLConnection.disconnect();
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = httpURLConnection2;
        }
    }
}
