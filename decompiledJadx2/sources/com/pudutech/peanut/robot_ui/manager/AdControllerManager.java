package com.pudutech.peanut.robot_ui.manager;

import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.RespApkVersionData;
import com.pudutech.lib_update.module.model.VerionResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AdControllerManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0007\u001a\u00020\b2#\u0010\t\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\b0\nJ)\u0010\u000f\u001a\u00020\b2!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nJ\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\bJ\b\u0010\u0016\u001a\u00020\bH\u0002J9\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/AdControllerManager;", "", "()V", "TAG", "", "hasSendMac", "", "checkApkVersion", "", "cb", "Lkotlin/Function1;", "Lcom/pudutech/lib_update/module/model/VerionResult;", "Lkotlin/ParameterName;", "name", "v", "checkState", "b", "getAdAppVersion", "Lcom/pudutech/disinfect/baselib/network/response/ApiResponse;", "Lcom/pudutech/disinfect/baselib/network/response/RespApkVersionData;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "init", "sendMacIfNeed", "startUpdate", "url", "md5", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AdControllerManager {
    private static volatile boolean hasSendMac;
    public static final AdControllerManager INSTANCE = new AdControllerManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private AdControllerManager() {
    }

    public final void init() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AdControllerManager$init$1(null), 3, null);
        sendMacIfNeed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMacIfNeed() {
        if (hasSendMac) {
            return;
        }
        Pdlog.m3273d(TAG, "sendMacIfNeed start");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AdControllerManager$sendMacIfNeed$1(null), 3, null);
    }

    public final void checkState(Function1<? super Boolean, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        Pdlog.m3273d(TAG, "checkState ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AdControllerManager$checkState$1(cb, null), 3, null);
    }

    public final Object getAdAppVersion(Continuation<? super ApiResponse<RespApkVersionData>> continuation) {
        return NetWorkApiManager.INSTANCE.getAdControl().getApkVersion(continuation);
    }

    public final void checkApkVersion(Function1<? super VerionResult, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AdControllerManager$checkApkVersion$1(cb, null), 3, null);
    }

    public final void startUpdate(String url, String md5, Function1<? super Boolean, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        Pdlog.m3273d(TAG, "startUpdate : url = " + url + "; md5 = " + md5 + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AdControllerManager$startUpdate$1(url, md5, cb, null), 3, null);
    }
}
