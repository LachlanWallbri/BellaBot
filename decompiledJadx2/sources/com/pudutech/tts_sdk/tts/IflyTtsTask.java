package com.pudutech.tts_sdk.tts;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.pudutech.base.Pdlog;
import com.pudutech.tts_sdk.TtsConfig;
import com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IflyTtsTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J5\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152#\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\r\u0018\u00010\u0017H\u0016J\u0010\u0010\b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\tH\u0002J\u0010\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010 \u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/tts_sdk/tts/IflyTtsTask;", "Lcom/pudutech/tts_sdk/tts/TtsInterface;", "()V", "APPID", "", "ASSETS_CONFIG_PATH", "executor", "Ljava/util/concurrent/Executor;", "initSuccess", "", "mainHandler", "Landroid/os/Handler;", "genTtsFile", "", "text", "filePath", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "extraParam", "init", "context", "Landroid/content/Context;", "initCallback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "code", MqttServiceConstants.CONNECT_ACTION, "isOnlyTTs", "readAsset", "", "release", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IflyTtsTask implements TtsInterface {
    private final String APPID;
    private final String ASSETS_CONFIG_PATH;
    private final Executor executor;
    private boolean initSuccess;
    private final Handler mainHandler;

    public IflyTtsTask() {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        Intrinsics.checkExpressionValueIsNotNull(newCachedThreadPool, "Executors.newCachedThreadPool()");
        this.executor = newCachedThreadPool;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.ASSETS_CONFIG_PATH = IFlyVoiceInteractionKit.ASSETS_CONFIG_PATH;
        this.APPID = "8247e8d9";
    }

    @Override // com.pudutech.tts_sdk.tts.TtsInterface
    public void init(Context context, final Function1<? super Integer, Unit> initCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        IFlyVoiceInteractionKit.INSTANCE.getINSTANCE().initOnlyTTS(context, new Function1<Integer, Unit>() { // from class: com.pudutech.tts_sdk.tts.IflyTtsTask$init$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                Pdlog.m3273d("IflyTtsTask", "IflyTtsTask initOnlyTTS  " + i);
                Function1 function1 = Function1.this;
                if (function1 != null) {
                }
            }
        });
    }

    @Override // com.pudutech.tts_sdk.tts.TtsInterface
    public void genTtsFile(String text, String filePath, OnTtsListener onTtsListener, String extraParam) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(onTtsListener, "onTtsListener");
        Intrinsics.checkParameterIsNotNull(extraParam, "extraParam");
        this.initSuccess = IFlyVoiceInteractionKit.INSTANCE.getINSTANCE().getServerConnectState(null);
        if (this.initSuccess) {
            Pdlog.m3273d(TtsConfig.TAG, "genTtsFile " + text + " , " + filePath);
            String cachePath = TtsConfig.INSTANCE.getCachePath();
            StringBuilder sb = new StringBuilder();
            sb.append(cachePath);
            sb.append("uri_tts." + TtsConfig.INSTANCE.getAUDIO_FORMAT());
            sb.toString();
            IFlyVoiceInteractionKit.INSTANCE.getINSTANCE().startTts(text, true, filePath, new IflyTtsTask$genTtsFile$1(this, onTtsListener, text, extraParam));
            return;
        }
        onTtsListener.onError(-1, "is not init or init failed");
    }

    private final void initSuccess(boolean connect) {
        this.initSuccess = connect;
    }

    @Override // com.pudutech.tts_sdk.tts.TtsInterface
    public void release() {
        Pdlog.m3273d(TtsConfig.TAG, "release");
        this.initSuccess = false;
    }

    private final boolean isOnlyTTs(Context context) {
        byte[] readAsset = readAsset(this.ASSETS_CONFIG_PATH, context);
        if (readAsset == null) {
            Intrinsics.throwNpe();
        }
        return Intrinsics.areEqual(new JSONObject(new String(readAsset, Charsets.UTF_8)).optJSONObject("login").get("appid").toString(), this.APPID);
    }

    private final byte[] readAsset(String filePath, Context context) {
        InputStream inputStream = (InputStream) null;
        try {
            try {
                inputStream = context.getResources().getAssets().open(filePath);
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                if (inputStream != null) {
                    inputStream.close();
                }
                return bArr;
            } catch (IOException e) {
                e.printStackTrace();
                if (inputStream != null) {
                    inputStream.close();
                }
                return null;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }
}
