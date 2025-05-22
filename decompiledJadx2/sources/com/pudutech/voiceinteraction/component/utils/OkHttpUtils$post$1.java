package com.pudutech.voiceinteraction.component.utils;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loc.C3898x;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: OkHttpUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, m3961d2 = {"com/pudutech/voiceinteraction/component/utils/OkHttpUtils$post$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", C3898x.f4338g, "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class OkHttpUtils$post$1 implements Callback {
    final /* synthetic */ OkHttpUtils this$0;

    OkHttpUtils$post$1(OkHttpUtils okHttpUtils) {
        this.this$0 = okHttpUtils;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [kotlin.jvm.functions.Function1] */
    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkParameterIsNotNull(call, "call");
        Intrinsics.checkParameterIsNotNull(e, "e");
        ?? ttsError = this.this$0.getTtsError();
        if (ttsError == 0) {
            Intrinsics.throwNpe();
        }
        ttsError.invoke(e.toString());
        Log.d(this.this$0.getTAG(), String.valueOf(e.getMessage()));
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r3v7, types: [kotlin.jvm.functions.Function1] */
    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        Intrinsics.checkParameterIsNotNull(call, "call");
        Intrinsics.checkParameterIsNotNull(response, "response");
        ResponseBody body = response.body();
        String string = body != null ? body.string() : null;
        if (string != null) {
            Log.d(this.this$0.getTAG(), string);
        }
        try {
            String answer = ((OkHttpUtils.TestData) new Gson().fromJson(string, OkHttpUtils.TestData.class)).getData().getData().getAnswer();
            if (this.this$0.getTtsClick() != null) {
                ?? ttsClick = this.this$0.getTtsClick();
                if (ttsClick == 0) {
                    Intrinsics.throwNpe();
                }
                ttsClick.invoke(answer);
            }
        } catch (JsonSyntaxException e) {
            ?? ttsError = this.this$0.getTtsError();
            if (ttsError == 0) {
                Intrinsics.throwNpe();
            }
            ttsError.invoke(e.toString());
        }
    }
}
