package com.pudutech.cloud.report;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.core.JsonFactory;
import com.google.gson.Gson;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.light_network.HttpsServiceType;
import com.pudutech.light_network.OKHttpClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: ReportClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001:\u0004\u001b\u001c\u001d\u001eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006J\u0016\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/cloud/report/ReportClient;", "", "()V", JsonFactory.FORMAT_NAME_JSON, "Lokhttp3/MediaType;", "TAG", "", "client", "Lokhttp3/OkHttpClient;", "gson", "Lcom/google/gson/Gson;", "has_history", "", "historyFilePath", "intentsDequeue", "Ljava/util/concurrent/LinkedBlockingDeque;", "Lcom/pudutech/cloud/report/ReportClient$ReportContent;", "reportThread", "Lcom/pudutech/cloud/report/ReportClient$ReportIntentThread;", "init", "", "context", "Landroid/content/Context;", ReportClient.TAG, "url", NotificationCompat.CATEGORY_MESSAGE, "reportOrThrown", "HistoryBody", "HistoryContent", "ReportContent", "ReportIntentThread", "cloudreport_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ReportClient {
    private static OkHttpClient client = null;
    private static ReportIntentThread reportThread;
    public static final ReportClient INSTANCE = new ReportClient();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String historyFilePath = historyFilePath;
    private static final String historyFilePath = historyFilePath;
    private static final LinkedBlockingDeque<ReportContent> intentsDequeue = new LinkedBlockingDeque<>();
    private static boolean has_history = true;
    private static final Gson gson = new Gson();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private ReportClient() {
    }

    public static final /* synthetic */ OkHttpClient access$getClient$p(ReportClient reportClient) {
        OkHttpClient okHttpClient = client;
        if (okHttpClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
        }
        return okHttpClient;
    }

    public static final /* synthetic */ Gson access$getGson$p(ReportClient reportClient) {
        return gson;
    }

    public static final /* synthetic */ boolean access$getHas_history$p(ReportClient reportClient) {
        return has_history;
    }

    public static final /* synthetic */ String access$getHistoryFilePath$p(ReportClient reportClient) {
        return historyFilePath;
    }

    public static final /* synthetic */ LinkedBlockingDeque access$getIntentsDequeue$p(ReportClient reportClient) {
        return intentsDequeue;
    }

    public static final /* synthetic */ MediaType access$getJSON$p(ReportClient reportClient) {
        return JSON;
    }

    public static final /* synthetic */ ReportIntentThread access$getReportThread$p(ReportClient reportClient) {
        ReportIntentThread reportIntentThread = reportThread;
        if (reportIntentThread == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reportThread");
        }
        return reportIntentThread;
    }

    public static final /* synthetic */ String access$getTAG$p(ReportClient reportClient) {
        return TAG;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (client == null && reportThread == null) {
            Pdlog.m3273d(TAG, "start init report client");
            OkHttpClient instance4Https = OKHttpClient.getInstance4Https(context, HttpsServiceType.Cloud, null, null, false);
            Intrinsics.checkExpressionValueIsNotNull(instance4Https, "OKHttpClient.getInstance…Cloud, null, null, false)");
            client = instance4Https;
            reportThread = new ReportIntentThread();
            ReportIntentThread reportIntentThread = reportThread;
            if (reportIntentThread == null) {
                Intrinsics.throwUninitializedPropertyAccessException("reportThread");
            }
            reportIntentThread.setName("CloudReportIntent");
            ReportIntentThread reportIntentThread2 = reportThread;
            if (reportIntentThread2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("reportThread");
            }
            reportIntentThread2.start();
        }
    }

    public final void report(String url, String msg) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (client == null) {
            return;
        }
        intentsDequeue.putFirst(new ReportContent(msg, url, true));
    }

    public final void reportOrThrown(String url, String msg) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (client == null) {
            return;
        }
        intentsDequeue.putFirst(new ReportContent(msg, url, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReportClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/cloud/report/ReportClient$ReportContent;", "", "data", "", "url", "save_to_history", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getData", "()Ljava/lang/String;", "setData", "(Ljava/lang/String;)V", "getSave_to_history", "()Z", "setSave_to_history", "(Z)V", "getUrl", "setUrl", "cloudreport_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class ReportContent {
        private String data;
        private boolean save_to_history;
        private String url;

        public ReportContent() {
            this(null, null, false, 7, null);
        }

        public ReportContent(String data, String url, boolean z) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            Intrinsics.checkParameterIsNotNull(url, "url");
            this.data = data;
            this.url = url;
            this.save_to_history = z;
        }

        public final String getData() {
            return this.data;
        }

        public final void setData(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.data = str;
        }

        public final String getUrl() {
            return this.url;
        }

        public final void setUrl(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.url = str;
        }

        public /* synthetic */ ReportContent(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? false : z);
        }

        public final boolean getSave_to_history() {
            return this.save_to_history;
        }

        public final void setSave_to_history(boolean z) {
            this.save_to_history = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReportClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/cloud/report/ReportClient$HistoryContent;", "", "time", "", "url", "", "data", "(ILjava/lang/String;Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "setData", "(Ljava/lang/String;)V", "getTime", "()I", "setTime", "(I)V", "getUrl", "setUrl", "cloudreport_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class HistoryContent {
        private String data;
        private int time;
        private String url;

        public HistoryContent() {
            this(0, null, null, 7, null);
        }

        public HistoryContent(int i, String url, String data) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intrinsics.checkParameterIsNotNull(data, "data");
            this.time = i;
            this.url = url;
            this.data = data;
        }

        public final int getTime() {
            return this.time;
        }

        public final void setTime(int i) {
            this.time = i;
        }

        public final String getUrl() {
            return this.url;
        }

        public final void setUrl(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.url = str;
        }

        public /* synthetic */ HistoryContent(int i, String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2);
        }

        public final String getData() {
            return this.data;
        }

        public final void setData(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.data = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReportClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\u0002\u0010\bR.\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/cloud/report/ReportClient$HistoryBody;", "", "size", "", "history", "Ljava/util/ArrayList;", "Lcom/pudutech/cloud/report/ReportClient$HistoryContent;", "Lkotlin/collections/ArrayList;", "(ILjava/util/ArrayList;)V", "getHistory", "()Ljava/util/ArrayList;", "setHistory", "(Ljava/util/ArrayList;)V", "getSize", "()I", "setSize", "(I)V", "cloudreport_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class HistoryBody {
        private ArrayList<HistoryContent> history;
        private int size;

        public HistoryBody() {
            this(0, null, 3, 0 == true ? 1 : 0);
        }

        public HistoryBody(int i, ArrayList<HistoryContent> arrayList) {
            this.size = i;
            this.history = arrayList;
        }

        public final int getSize() {
            return this.size;
        }

        public final void setSize(int i) {
            this.size = i;
        }

        public /* synthetic */ HistoryBody(int i, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? (ArrayList) null : arrayList);
        }

        public final ArrayList<HistoryContent> getHistory() {
            return this.history;
        }

        public final void setHistory(ArrayList<HistoryContent> arrayList) {
            this.history = arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReportClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u0004H\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/cloud/report/ReportClient$ReportIntentThread;", "Ljava/lang/Thread;", "()V", "reportHistory", "", "reportToCloud", "", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/cloud/report/ReportClient$ReportContent;", "run", "saveEmptyFile", "saveOneContentToFile", "saveToHistory", "ReportResultObj", "cloudreport_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class ReportIntentThread extends Thread {

        /* compiled from: ReportClient.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/cloud/report/ReportClient$ReportIntentThread$ReportResultObj;", "", "code", "", NotificationCompat.CATEGORY_MESSAGE, "", "data", "Lcom/google/gson/Gson;", "(Lcom/pudutech/cloud/report/ReportClient$ReportIntentThread;ILjava/lang/String;Lcom/google/gson/Gson;)V", "getCode", "()I", "setCode", "(I)V", "getData", "()Lcom/google/gson/Gson;", "setData", "(Lcom/google/gson/Gson;)V", "getMsg", "()Ljava/lang/String;", "setMsg", "(Ljava/lang/String;)V", "cloudreport_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes3.dex */
        public final class ReportResultObj {
            private int code;
            private Gson data;
            private String msg;
            final /* synthetic */ ReportIntentThread this$0;

            public ReportResultObj(ReportIntentThread reportIntentThread, int i, String msg, Gson data) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                Intrinsics.checkParameterIsNotNull(data, "data");
                this.this$0 = reportIntentThread;
                this.code = i;
                this.msg = msg;
                this.data = data;
            }

            public /* synthetic */ ReportResultObj(ReportIntentThread reportIntentThread, int i, String str, Gson gson, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this(reportIntentThread, (i2 & 1) != 0 ? -1 : i, str, gson);
            }

            public final int getCode() {
                return this.code;
            }

            public final Gson getData() {
                return this.data;
            }

            public final String getMsg() {
                return this.msg;
            }

            public final void setCode(int i) {
                this.code = i;
            }

            public final void setData(Gson gson) {
                Intrinsics.checkParameterIsNotNull(gson, "<set-?>");
                this.data = gson;
            }

            public final void setMsg(String str) {
                Intrinsics.checkParameterIsNotNull(str, "<set-?>");
                this.msg = str;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                ReportContent content = (ReportContent) ReportClient.access$getIntentsDequeue$p(ReportClient.INSTANCE).takeLast();
                Intrinsics.checkExpressionValueIsNotNull(content, "content");
                if (reportToCloud(content)) {
                    if (ReportClient.access$getHas_history$p(ReportClient.INSTANCE)) {
                        reportHistory();
                    }
                } else if (content.getSave_to_history()) {
                    saveToHistory(content);
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x00c9 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:13:0x00ca  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00b4  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x00ab  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final boolean reportToCloud(ReportContent content) {
            ResponseBody body;
            String string;
            ReportResultObj reportResultObj;
            try {
                try {
                    Response execute = ReportClient.access$getClient$p(ReportClient.INSTANCE).newCall(new Request.Builder().url(content.getUrl()).post(RequestBody.create(ReportClient.access$getJSON$p(ReportClient.INSTANCE), content.getData())).build()).execute();
                    if (execute != null) {
                        try {
                            body = execute.body();
                        } catch (Exception e) {
                            String access$getTAG$p = ReportClient.access$getTAG$p(ReportClient.INSTANCE);
                            StringBuilder sb = new StringBuilder();
                            sb.append("report failed: ");
                            StackTraceElement[] stackTrace = e.getStackTrace();
                            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                            sb.append(ArraysKt.contentDeepToString(stackTrace));
                            Pdlog.m3277w(access$getTAG$p, sb.toString());
                        }
                        if (body != null) {
                            string = body.string();
                            String access$getTAG$p2 = ReportClient.access$getTAG$p(ReportClient.INSTANCE);
                            Object[] objArr = new Object[1];
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("report content ");
                            sb2.append(content.getData());
                            sb2.append(" \n\t\tto ");
                            sb2.append(content.getUrl());
                            sb2.append(" to history? ");
                            sb2.append(content.getSave_to_history());
                            sb2.append(" \n\t\tresult ");
                            sb2.append(execute == null ? Boolean.valueOf(execute.isSuccessful()) : null);
                            sb2.append(" \n\t\t\t ");
                            sb2.append(string);
                            objArr[0] = sb2.toString();
                            Pdlog.m3273d(access$getTAG$p2, objArr);
                            if (execute != null) {
                                return false;
                            }
                            ReportResultObj reportResultObj2 = (ReportResultObj) null;
                            try {
                                reportResultObj = (ReportResultObj) ReportClient.access$getGson$p(ReportClient.INSTANCE).fromJson(string, ReportResultObj.class);
                            } catch (Exception unused) {
                                reportResultObj = reportResultObj2;
                            }
                            return execute.isSuccessful() && reportResultObj != null && (reportResultObj.getCode() == 0 || reportResultObj.getCode() == -1);
                        }
                    }
                    string = null;
                    String access$getTAG$p22 = ReportClient.access$getTAG$p(ReportClient.INSTANCE);
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb22 = new StringBuilder();
                    sb22.append("report content ");
                    sb22.append(content.getData());
                    sb22.append(" \n\t\tto ");
                    sb22.append(content.getUrl());
                    sb22.append(" to history? ");
                    sb22.append(content.getSave_to_history());
                    sb22.append(" \n\t\tresult ");
                    sb22.append(execute == null ? Boolean.valueOf(execute.isSuccessful()) : null);
                    sb22.append(" \n\t\t\t ");
                    sb22.append(string);
                    objArr2[0] = sb22.toString();
                    Pdlog.m3273d(access$getTAG$p22, objArr2);
                    if (execute != null) {
                    }
                } catch (Exception e2) {
                    Pdlog.m3274e(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "report exception: " + e2);
                    Pdlog.m3273d(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "report content " + content.getData() + " \n\t\tto " + content.getUrl() + " to history? " + content.getSave_to_history() + " \n\t\tresult " + ((Object) null) + " \n\t\t\t " + ((String) null));
                    return false;
                }
            } catch (Throwable unused2) {
                Pdlog.m3273d(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "report content " + content.getData() + " \n\t\tto " + content.getUrl() + " to history? " + content.getSave_to_history() + " \n\t\tresult " + ((Object) null) + " \n\t\t\t " + ((String) null));
                return false;
            }
        }

        private final void saveToHistory(ReportContent content) {
            File file = new File(ReportClient.access$getHistoryFilePath$p(ReportClient.INSTANCE));
            if (!file.exists()) {
                saveOneContentToFile(content);
                ReportClient reportClient = ReportClient.INSTANCE;
                ReportClient.has_history = true;
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            new HistoryBody(0, null, 3, 0 == true ? 1 : 0);
            try {
                Object fromJson = ReportClient.access$getGson$p(ReportClient.INSTANCE).fromJson((Reader) bufferedReader, (Class<Object>) HistoryBody.class);
                Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(reader, HistoryBody::class.java)");
                HistoryBody historyBody = (HistoryBody) fromJson;
                fileInputStream.close();
                if (historyBody.getSize() == 0) {
                    saveOneContentToFile(content);
                    return;
                }
                int size = historyBody.getSize();
                ArrayList<HistoryContent> history = historyBody.getHistory();
                if (history == null) {
                    Intrinsics.throwNpe();
                }
                if (size != history.size()) {
                    ArrayList<HistoryContent> history2 = historyBody.getHistory();
                    if (history2 == null) {
                        Intrinsics.throwNpe();
                    }
                    historyBody.setSize(history2.size());
                }
                ArrayList<HistoryContent> history3 = historyBody.getHistory();
                if (history3 == null) {
                    Intrinsics.throwNpe();
                }
                int time = history3.get(historyBody.getSize() - 1).getTime();
                if (time >= 500000) {
                    ArrayList<HistoryContent> history4 = historyBody.getHistory();
                    if (history4 == null) {
                        Intrinsics.throwNpe();
                    }
                    Iterator<HistoryContent> it = history4.iterator();
                    Intrinsics.checkExpressionValueIsNotNull(it, "history.history!!.iterator()");
                    while (it.hasNext()) {
                        HistoryContent next = it.next();
                        Intrinsics.checkExpressionValueIsNotNull(next, "iterator.next()");
                        HistoryContent historyContent = next;
                        if (time - historyContent.getTime() > 50000) {
                            it.remove();
                        } else {
                            historyContent.setTime(historyContent.getTime() + 1);
                            historyContent.getTime();
                        }
                    }
                }
                ArrayList<HistoryContent> history5 = historyBody.getHistory();
                if (history5 == null) {
                    Intrinsics.throwNpe();
                }
                historyBody.setSize(history5.size() + 1);
                ArrayList<HistoryContent> history6 = historyBody.getHistory();
                if (history6 == null) {
                    Intrinsics.throwNpe();
                }
                ArrayList<HistoryContent> history7 = historyBody.getHistory();
                if (history7 == null) {
                    Intrinsics.throwNpe();
                }
                history6.add(new HistoryContent(history7.get(historyBody.getSize() - 2).getTime() + 1, content.getUrl(), content.getData()));
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    String json = ReportClient.access$getGson$p(ReportClient.INSTANCE).toJson(historyBody);
                    Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(history)");
                    fileOutputStream.write(StringsKt.encodeToByteArray(json));
                    fileOutputStream.close();
                } catch (Exception e) {
                    Pdlog.m3274e(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "save least history fail: " + e);
                    fileOutputStream.close();
                    saveOneContentToFile(content);
                }
            } catch (Exception e2) {
                Pdlog.m3274e(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "read history file error: " + e2);
                fileInputStream.close();
                saveOneContentToFile(content);
            }
        }

        private final void reportHistory() {
            FileInputStream fileInputStream;
            File file = new File(ReportClient.access$getHistoryFilePath$p(ReportClient.INSTANCE));
            int i = 0;
            if (!file.exists()) {
                saveEmptyFile();
                ReportClient reportClient = ReportClient.INSTANCE;
                ReportClient.has_history = false;
                return;
            }
            new HistoryBody(i, null, 3, 0 == true ? 1 : 0);
            FileInputStream fileInputStream2 = (FileInputStream) null;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    Object fromJson = ReportClient.access$getGson$p(ReportClient.INSTANCE).fromJson((Reader) new BufferedReader(new InputStreamReader(fileInputStream)), (Class<Object>) HistoryBody.class);
                    Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(reader, HistoryBody::class.java)");
                    HistoryBody historyBody = (HistoryBody) fromJson;
                    fileInputStream.close();
                    if (historyBody.getSize() == 0) {
                        ReportClient reportClient2 = ReportClient.INSTANCE;
                        ReportClient.has_history = false;
                        return;
                    }
                    Pdlog.m3273d(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "current history size " + historyBody.getSize());
                    ReportContent reportContent = new ReportContent(null, null, false, 7, null);
                    ArrayList<HistoryContent> history = historyBody.getHistory();
                    if (history == null) {
                        Intrinsics.throwNpe();
                    }
                    Iterator<HistoryContent> it = history.iterator();
                    Intrinsics.checkExpressionValueIsNotNull(it, "history.history!!.iterator()");
                    while (it.hasNext()) {
                        String access$getTAG$p = ReportClient.access$getTAG$p(ReportClient.INSTANCE);
                        Object[] objArr = new Object[1];
                        StringBuilder sb = new StringBuilder();
                        sb.append("history report, current least size ");
                        ArrayList<HistoryContent> history2 = historyBody.getHistory();
                        if (history2 == null) {
                            Intrinsics.throwNpe();
                        }
                        sb.append(history2.size());
                        objArr[0] = sb.toString();
                        Pdlog.m3273d(access$getTAG$p, objArr);
                        HistoryContent next = it.next();
                        Intrinsics.checkExpressionValueIsNotNull(next, "iterator.next()");
                        HistoryContent historyContent = next;
                        reportContent.setUrl(historyContent.getUrl());
                        reportContent.setData(historyContent.getData());
                        reportContent.setSave_to_history(true);
                        if (!reportToCloud(reportContent)) {
                            break;
                        } else {
                            it.remove();
                        }
                    }
                    ArrayList<HistoryContent> history3 = historyBody.getHistory();
                    if (history3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (history3.size() == 0) {
                        saveEmptyFile();
                        ReportClient reportClient3 = ReportClient.INSTANCE;
                        ReportClient.has_history = false;
                        return;
                    }
                    String access$getTAG$p2 = ReportClient.access$getTAG$p(ReportClient.INSTANCE);
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("least history size ");
                    ArrayList<HistoryContent> history4 = historyBody.getHistory();
                    if (history4 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb2.append(history4.size());
                    objArr2[0] = sb2.toString();
                    Pdlog.m3273d(access$getTAG$p2, objArr2);
                    ArrayList<HistoryContent> history5 = historyBody.getHistory();
                    if (history5 == null) {
                        Intrinsics.throwNpe();
                    }
                    historyBody.setSize(history5.size());
                    ArrayList<HistoryContent> history6 = historyBody.getHistory();
                    if (history6 == null) {
                        Intrinsics.throwNpe();
                    }
                    Iterator<HistoryContent> it2 = history6.iterator();
                    Intrinsics.checkExpressionValueIsNotNull(it2, "history.history!!.iterator()");
                    int i2 = 0;
                    while (it2.hasNext()) {
                        HistoryContent next2 = it2.next();
                        Intrinsics.checkExpressionValueIsNotNull(next2, "iterator.next()");
                        next2.setTime(i2);
                        i2++;
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        String json = ReportClient.access$getGson$p(ReportClient.INSTANCE).toJson(historyBody);
                        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(history)");
                        fileOutputStream.write(StringsKt.encodeToByteArray(json));
                        fileOutputStream.close();
                    } catch (Exception e) {
                        Pdlog.m3274e(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "save least history fail: " + e);
                        fileOutputStream.close();
                        saveEmptyFile();
                    }
                } catch (Exception e2) {
                    e = e2;
                    Pdlog.m3274e(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "read history file error: " + e);
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    saveEmptyFile();
                }
            } catch (Exception e3) {
                e = e3;
                fileInputStream = fileInputStream2;
            }
        }

        private final void saveOneContentToFile(ReportContent content) {
            Pdlog.m3273d(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "save only current info");
            File file = new File(ReportClient.access$getHistoryFilePath$p(ReportClient.INSTANCE));
            HistoryBody historyBody = new HistoryBody(0, null, 3, 0 == true ? 1 : 0);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            historyBody.setSize(1);
            historyBody.setHistory(new ArrayList<>(0));
            ArrayList<HistoryContent> history = historyBody.getHistory();
            if (history != null) {
                history.add(new HistoryContent(0, content.getUrl(), content.getData()));
            }
            try {
                try {
                    String json = ReportClient.access$getGson$p(ReportClient.INSTANCE).toJson(historyBody);
                    Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(body)");
                    fileOutputStream.write(StringsKt.encodeToByteArray(json));
                    fileOutputStream.close();
                    ReportClient reportClient = ReportClient.INSTANCE;
                    ReportClient.has_history = true;
                } catch (Exception e) {
                    Pdlog.m3274e(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "save file exception: " + e);
                }
            } finally {
                fileOutputStream.close();
            }
        }

        private final void saveEmptyFile() {
            File file = new File(ReportClient.access$getHistoryFilePath$p(ReportClient.INSTANCE));
            HistoryBody historyBody = new HistoryBody(0, null, 3, 0 == true ? 1 : 0);
            FileOutputStream fileOutputStream = (FileOutputStream) null;
            historyBody.setSize(0);
            historyBody.setHistory((ArrayList) null);
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        String json = ReportClient.access$getGson$p(ReportClient.INSTANCE).toJson(historyBody);
                        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(body)");
                        fileOutputStream2.write(StringsKt.encodeToByteArray(json));
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        Pdlog.m3274e(ReportClient.access$getTAG$p(ReportClient.INSTANCE), "save file exception: " + e);
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }
}
