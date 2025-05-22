package com.pudutech.lib_update.base.net;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.light_network.HttpsServiceType;
import com.pudutech.light_network.RetrofitManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import retrofit2.Retrofit;

/* compiled from: HttpManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0011\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001f\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u000e¢\u0006\u0002\u0010\u000fR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/lib_update/base/net/HttpManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mRetrofit", "Lretrofit2/Retrofit;", "getMRetrofit", "()Lretrofit2/Retrofit;", "setMRetrofit", "(Lretrofit2/Retrofit;)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "service", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "Companion", "lib_update_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class HttpManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static HttpManager mManger;
    private Retrofit mRetrofit;

    private HttpManager(Context context) {
        if (context != null) {
            this.mRetrofit = RetrofitManager.getInstance(context, HttpsServiceType.Upgrade, null, new AddMoreHostWork(), true);
        } else {
            this.mRetrofit = RetrofitManager.getInstance(context, null, new AddMoreHostWork(), true);
        }
    }

    public /* synthetic */ HttpManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Retrofit getMRetrofit() {
        return this.mRetrofit;
    }

    public final void setMRetrofit(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    /* compiled from: HttpManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/lib_update/base/net/HttpManager$Companion;", "", "()V", "mManger", "Lcom/pudutech/lib_update/base/net/HttpManager;", "getMManger", "()Lcom/pudutech/lib_update/base/net/HttpManager;", "setMManger", "(Lcom/pudutech/lib_update/base/net/HttpManager;)V", "getInstance", "context", "Landroid/content/Context;", "lib_update_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HttpManager getMManger() {
            return HttpManager.mManger;
        }

        public final void setMManger(HttpManager httpManager) {
            HttpManager.mManger = httpManager;
        }

        public final HttpManager getInstance(Context context) {
            Companion companion = this;
            if (companion.getMManger() == null) {
                synchronized (Reflection.getOrCreateKotlinClass(HttpManager.class)) {
                    if (HttpManager.INSTANCE.getMManger() == null) {
                        HttpManager.INSTANCE.setMManger(new HttpManager(context, null));
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            HttpManager mManger = companion.getMManger();
            if (mManger == null) {
                Intrinsics.throwNpe();
            }
            return mManger;
        }
    }

    public final <T> T create(Class<T> service) {
        Intrinsics.checkParameterIsNotNull(service, "service");
        Retrofit retrofit = this.mRetrofit;
        T t = retrofit != null ? (T) retrofit.create(service) : null;
        if (t == null) {
            Intrinsics.throwNpe();
        }
        return t;
    }
}
