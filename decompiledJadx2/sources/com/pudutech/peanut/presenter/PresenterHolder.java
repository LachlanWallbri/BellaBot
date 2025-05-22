package com.pudutech.peanut.presenter;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: PresenterHolder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\r\u001a\u0004\u0018\u0001H\u000e\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\tH\u0086\b¢\u0006\u0002\u0010\u000fJ,\u0010\u0010\u001a\u0004\u0018\u0001H\u000e\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\t2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\u0012H\u0086\b¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\u0004\u0018\u0001H\u000e\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\tH\u0086\b¢\u0006\u0002\u0010\u000fJ,\u0010\u0015\u001a\u0004\u0018\u0001H\u000e\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\t2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\u0012H\u0086\b¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R-\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\bj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t`\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/PresenterHolder;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "box", "Ljava/util/HashMap;", "Lcom/pudutech/kotlinmvp/mvp_base/BasePresenterInterface;", "Lkotlin/collections/HashMap;", "getBox", "()Ljava/util/HashMap;", "find", ExifInterface.GPS_DIRECTION_TRUE, "()Lcom/pudutech/kotlinmvp/mvp_base/BasePresenterInterface;", "findOrCreateIt", "defaultConstructor", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Lcom/pudutech/kotlinmvp/mvp_base/BasePresenterInterface;", "pop", "popOrCreateIt", "put", "", "presenter", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PresenterHolder {
    public static final PresenterHolder INSTANCE = new PresenterHolder();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final HashMap<String, BasePresenterInterface> box = new HashMap<>();

    private PresenterHolder() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final HashMap<String, BasePresenterInterface> getBox() {
        return box;
    }

    public final void put(BasePresenterInterface presenter) {
        Pdlog.m3273d(TAG, "put " + presenter);
        if (presenter == null) {
            Pdlog.m3274e(TAG, "put null not accepted");
        } else {
            box.put(JvmClassMappingKt.getKotlinClass(presenter.getClass()).toString(), presenter);
        }
    }

    public final /* synthetic */ <T extends BasePresenterInterface> T pop() {
        HashMap<String, BasePresenterInterface> box2 = getBox();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BasePresenterInterface basePresenterInterface = box2.get(Reflection.getOrCreateKotlinClass(BasePresenterInterface.class).toString());
        HashMap<String, BasePresenterInterface> box3 = getBox();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        box3.remove(Reflection.getOrCreateKotlinClass(BasePresenterInterface.class).toString());
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) basePresenterInterface;
    }

    public final /* synthetic */ <T extends BasePresenterInterface> T find() {
        HashMap<String, BasePresenterInterface> box2 = getBox();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BasePresenterInterface basePresenterInterface = box2.get(Reflection.getOrCreateKotlinClass(BasePresenterInterface.class).toString());
        Pdlog.m3273d(getTAG(), "find " + basePresenterInterface);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) basePresenterInterface;
    }

    public final /* synthetic */ <T extends BasePresenterInterface> T popOrCreateIt(Function0<? extends T> defaultConstructor) {
        Intrinsics.checkParameterIsNotNull(defaultConstructor, "defaultConstructor");
        HashMap<String, BasePresenterInterface> box2 = getBox();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BasePresenterInterface basePresenterInterface = box2.get(Reflection.getOrCreateKotlinClass(BasePresenterInterface.class).toString());
        String tag = getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("popOrCreateIt ");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        sb.append(Reflection.getOrCreateKotlinClass(BasePresenterInterface.class));
        sb.append(' ');
        sb.append(basePresenterInterface);
        Pdlog.m3273d(tag, sb.toString());
        if (basePresenterInterface != null) {
            HashMap<String, BasePresenterInterface> box3 = getBox();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            box3.remove(Reflection.getOrCreateKotlinClass(BasePresenterInterface.class).toString());
            Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) basePresenterInterface;
        }
        return defaultConstructor.invoke();
    }

    public final /* synthetic */ <T extends BasePresenterInterface> T findOrCreateIt(Function0<? extends T> defaultConstructor) {
        Intrinsics.checkParameterIsNotNull(defaultConstructor, "defaultConstructor");
        HashMap<String, BasePresenterInterface> box2 = getBox();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BasePresenterInterface basePresenterInterface = box2.get(Reflection.getOrCreateKotlinClass(BasePresenterInterface.class).toString());
        String tag = getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("findOrCreateIt ");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        sb.append(Reflection.getOrCreateKotlinClass(BasePresenterInterface.class));
        sb.append(' ');
        sb.append(basePresenterInterface);
        Pdlog.m3273d(tag, sb.toString());
        if (basePresenterInterface != null) {
            Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) basePresenterInterface;
        }
        T invoke = defaultConstructor.invoke();
        HashMap<String, BasePresenterInterface> box3 = getBox();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        String obj = Reflection.getOrCreateKotlinClass(BasePresenterInterface.class).toString();
        if (invoke == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface");
        }
        box3.put(obj, invoke);
        return invoke;
    }
}
