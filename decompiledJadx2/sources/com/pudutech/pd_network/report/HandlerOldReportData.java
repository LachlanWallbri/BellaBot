package com.pudutech.pd_network.report;

import android.content.Context;
import android.util.Log;
import com.fasterxml.jackson.core.JsonFactory;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.report.bean.OldReportBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* compiled from: HandlerOldReportData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/pd_network/report/HandlerOldReportData;", "", "()V", JsonFactory.FORMAT_NAME_JSON, "Lokhttp3/MediaType;", "TAG", "", "kotlin.jvm.PlatformType", "client", "Lokhttp3/OkHttpClient;", "historyCacheFilePath", "historyFilePath", "scope", "Lkotlinx/coroutines/CoroutineScope;", "handler", "", "context", "Landroid/content/Context;", "handlerOldVersionData", "report", "", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/pd_network/report/bean/OldReportBean;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class HandlerOldReportData {
    public static final HandlerOldReportData INSTANCE;
    private static final MediaType JSON;
    private static final String TAG;
    private static OkHttpClient client = null;
    private static final String historyCacheFilePath = "/sdcard/history_path_cache.json";
    private static final String historyFilePath = "/sdcard/history_path.json";
    private static final CoroutineScope scope;

    static {
        HandlerOldReportData handlerOldReportData = new HandlerOldReportData();
        INSTANCE = handlerOldReportData;
        TAG = handlerOldReportData.getClass().getSimpleName();
        scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        JSON = MediaType.Companion.parse("application/json; charset=utf-8");
    }

    private HandlerOldReportData() {
    }

    public final void handler(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            handlerOldVersionData(context);
        } catch (Exception e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
            netWorkLog.mo3279e(TAG2, "handlerOldVersionData.error " + e);
        }
    }

    private final void handlerOldVersionData(Context context) {
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new HandlerOldReportData$handlerOldVersionData$1(context, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean report(OldReportBean content) {
        Call newCall;
        Log.e(TAG, "report : " + content);
        Response response = null;
        try {
            Request build = new Request.Builder().url(content.getUrl()).post(RequestBody.Companion.create(JSON, content.getData())).build();
            OkHttpClient okHttpClient = client;
            if (okHttpClient != null && (newCall = okHttpClient.newCall(build)) != null) {
                response = newCall.execute();
            }
            Log.e(TAG, "report - response : " + response);
            return (response != null ? response.code() : 501) < 500;
        } catch (Exception e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
            netWorkLog.mo3279e(TAG2, "report exception: " + e);
            return false;
        }
    }
}
