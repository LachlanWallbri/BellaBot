package com.pudutech.disinfect.baselib.util;

import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.hgj.jetpackmvvm.util.ParameterizedTypeImpl;

/* compiled from: GsonSingleton.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007\b\u0012¢\u0006\u0002\u0010\u0002J-\u0010\u0005\u001a\u0004\u0018\u0001H\u0006\"\u0004\b\u0000\u0010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ,\u0010\f\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\r\"\u0004\b\u0000\u0010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\nJ\u0006\u0010\u000f\u001a\u00020\u0004J\u0010\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/GsonSingleton;", "", "()V", "mGson", "Lcom/google/gson/Gson;", "fromJson", ExifInterface.GPS_DIRECTION_TRUE, "s", "", "tClass", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "fromJsonArray", "", "clazz", "getGson", "toJson", "o", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GsonSingleton {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<GsonSingleton>() { // from class: com.pudutech.disinfect.baselib.util.GsonSingleton$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GsonSingleton invoke() {
            return new GsonSingleton(null);
        }
    });
    private Gson mGson;

    public /* synthetic */ GsonSingleton(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private GsonSingleton() {
        Gson create = new GsonBuilder().setExclusionStrategies(new IgnoreStrategy()).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "GsonBuilder().setExclusi…gnoreStrategy()).create()");
        this.mGson = create;
    }

    /* renamed from: getGson, reason: from getter */
    public final Gson getMGson() {
        return this.mGson;
    }

    /* compiled from: GsonSingleton.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/GsonSingleton$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/disinfect/baselib/util/GsonSingleton;", "getINSTANCE", "()Lcom/pudutech/disinfect/baselib/util/GsonSingleton;", "INSTANCE$delegate", "Lkotlin/Lazy;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        public final GsonSingleton getINSTANCE() {
            Lazy lazy = GsonSingleton.INSTANCE$delegate;
            Companion companion = GsonSingleton.INSTANCE;
            return (GsonSingleton) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final String toJson(Object o) {
        String json = this.mGson.toJson(o);
        Intrinsics.checkExpressionValueIsNotNull(json, "mGson.toJson(o)");
        return json;
    }

    public final <T> T fromJson(String s, Class<T> tClass) {
        return (T) this.mGson.fromJson(s, (Class) tClass);
    }

    public final <T> List<T> fromJsonArray(String s, Class<?> clazz) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        if (clazz == null) {
            Intrinsics.throwNpe();
        }
        return (List) this.mGson.fromJson(s, new ParameterizedTypeImpl(clazz));
    }
}
