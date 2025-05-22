package com.pudutech.tts_sdk;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.disklru.DiskLruCacheManager;
import com.pudutech.tts_sdk.tts.GoogleTtsTask;
import com.pudutech.tts_sdk.tts.IflyTtsTask;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.pudutech.tts_sdk.tts.TtsInterface;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import com.pudutech.tts_sdk.utils.CMediaPlayer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PdTtsSdk.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u00010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2#\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\f\u0018\u00010\u0010J;\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00112#\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\f\u0018\u00010\u0010J$\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u001a\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019J5\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00042%\b\u0002\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f\u0018\u00010\u0010J\u0006\u0010\u001f\u001a\u00020\fJ\u000e\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\"JA\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\u0012\u0010)\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040*\"\u00020\u0004¢\u0006\u0002\u0010+J2\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010,\u001a\u00020-J\u0006\u0010.\u001a\u00020\fJ\u0006\u0010/\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, m3961d2 = {"Lcom/pudutech/tts_sdk/PdTtsSdk;", "", "()V", "DISK_CACHE_DIR", "", "DISK_CACHE_SIZE", "", "googleTtsTask", "Lcom/pudutech/tts_sdk/tts/GoogleTtsTask;", "iflyTtsTask", "Lcom/pudutech/tts_sdk/tts/IflyTtsTask;", "init", "", "context", "Landroid/content/Context;", "initCallback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "code", "product", "playAssetsMp3File", "assetName", "listener", "Lcom/pudutech/tts_sdk/utils/CMediaPlayer$OnPlayStateChangedListener;", "playTtsMp3File", "filePath", "playTtsPcmFile", "Lcom/pudutech/tts_sdk/utils/AudioTrackUtils$AudioPlayEvent;", "event", "release", "setTtsVolume", "float", "", "startTts", "text", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "engine", "Lcom/pudutech/tts_sdk/PdTtsSdk$TtsEngine;", "keyExtraParams", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/tts_sdk/tts/OnTtsListener;Lcom/pudutech/tts_sdk/PdTtsSdk$TtsEngine;[Ljava/lang/String;)V", "notNeedCache", "", "stopTtsMp3File", "stopTtsPcmFile", "TtsEngine", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class PdTtsSdk {
    private static final String DISK_CACHE_DIR = "tts";
    private static final long DISK_CACHE_SIZE = 52428800;
    public static final PdTtsSdk INSTANCE = new PdTtsSdk();
    private static final IflyTtsTask iflyTtsTask = new IflyTtsTask();
    private static final GoogleTtsTask googleTtsTask = new GoogleTtsTask();

    /* compiled from: PdTtsSdk.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/tts_sdk/PdTtsSdk$TtsEngine;", "", "(Ljava/lang/String;I)V", "Ifly", "Google", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public enum TtsEngine {
        Ifly,
        Google
    }

    private PdTtsSdk() {
    }

    public final void init(Context context, Function1<? super Integer, Unit> initCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        iflyTtsTask.init(context, initCallback);
        googleTtsTask.init(context, initCallback);
        DiskLruCacheManager.INSTANCE.init(context, "tts", DISK_CACHE_SIZE);
    }

    public final void init(Context context, int product, Function1<? super Integer, Unit> initCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (product == 0) {
            iflyTtsTask.init(context, initCallback);
        } else {
            googleTtsTask.init(context, initCallback);
        }
        DiskLruCacheManager.INSTANCE.init(context, "tts", DISK_CACHE_SIZE);
    }

    public final void setTtsVolume(float r2) {
        TtsConfig.INSTANCE.setPCM_PLAY_VOLUME(r2);
    }

    public static /* synthetic */ void startTts$default(PdTtsSdk pdTtsSdk, String str, String str2, OnTtsListener onTtsListener, TtsEngine ttsEngine, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            ttsEngine = TtsEngine.Ifly;
        }
        TtsEngine ttsEngine2 = ttsEngine;
        if ((i & 16) != 0) {
            z = false;
        }
        pdTtsSdk.startTts(str, str2, onTtsListener, ttsEngine2, z);
    }

    public final void startTts(String text, String filePath, OnTtsListener onTtsListener, TtsEngine engine, boolean notNeedCache) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(onTtsListener, "onTtsListener");
        Intrinsics.checkParameterIsNotNull(engine, "engine");
        if (StringsKt.isBlank(text)) {
            onTtsListener.onError(-10, "text isNullOrEmpty");
            return;
        }
        if (!notNeedCache) {
            String diskCache = DiskLruCacheManager.INSTANCE.getDiskCache(filePath, DiskLruCacheManager.INSTANCE.genDiskLruKey(filePath, text));
            Pdlog.m3273d("startTts", "diskCache:" + diskCache);
            if (diskCache.length() > 0) {
                onTtsListener.onComplete(diskCache);
                return;
            }
        }
        if (engine == TtsEngine.Ifly) {
            TtsInterface.DefaultImpls.genTtsFile$default(iflyTtsTask, text, filePath, onTtsListener, null, 8, null);
        } else {
            TtsInterface.DefaultImpls.genTtsFile$default(googleTtsTask, text, filePath, onTtsListener, null, 8, null);
        }
    }

    public static /* synthetic */ void startTts$default(PdTtsSdk pdTtsSdk, String str, String str2, OnTtsListener onTtsListener, TtsEngine ttsEngine, String[] strArr, int i, Object obj) {
        if ((i & 8) != 0) {
            ttsEngine = TtsEngine.Ifly;
        }
        pdTtsSdk.startTts(str, str2, onTtsListener, ttsEngine, strArr);
    }

    public final void release() {
        iflyTtsTask.release();
        googleTtsTask.release();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playTtsPcmFile$default(PdTtsSdk pdTtsSdk, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        pdTtsSdk.playTtsPcmFile(str, function1);
    }

    public final void playTtsPcmFile(String filePath, Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        AudioTrackUtils.INSTANCE.play(filePath, listener);
    }

    public static /* synthetic */ void playTtsMp3File$default(PdTtsSdk pdTtsSdk, String str, CMediaPlayer.OnPlayStateChangedListener onPlayStateChangedListener, int i, Object obj) {
        if ((i & 2) != 0) {
            onPlayStateChangedListener = (CMediaPlayer.OnPlayStateChangedListener) null;
        }
        pdTtsSdk.playTtsMp3File(str, onPlayStateChangedListener);
    }

    public final void playTtsMp3File(String filePath, CMediaPlayer.OnPlayStateChangedListener listener) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        CMediaPlayer.INSTANCE.getINSTANCE().play(filePath, listener);
    }

    public static /* synthetic */ void playAssetsMp3File$default(PdTtsSdk pdTtsSdk, Context context, String str, CMediaPlayer.OnPlayStateChangedListener onPlayStateChangedListener, int i, Object obj) {
        if ((i & 4) != 0) {
            onPlayStateChangedListener = (CMediaPlayer.OnPlayStateChangedListener) null;
        }
        pdTtsSdk.playAssetsMp3File(context, str, onPlayStateChangedListener);
    }

    public final void playAssetsMp3File(Context context, String assetName, CMediaPlayer.OnPlayStateChangedListener listener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        CMediaPlayer.INSTANCE.getINSTANCE().play(context, assetName, listener);
    }

    public final void stopTtsMp3File() {
        CMediaPlayer.INSTANCE.getINSTANCE().stop();
    }

    public final void stopTtsPcmFile() {
        AudioTrackUtils.INSTANCE.stop();
    }

    public final void startTts(String text, String filePath, OnTtsListener onTtsListener, TtsEngine engine, String... keyExtraParams) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(onTtsListener, "onTtsListener");
        Intrinsics.checkParameterIsNotNull(engine, "engine");
        Intrinsics.checkParameterIsNotNull(keyExtraParams, "keyExtraParams");
        String str = "";
        for (String str2 : keyExtraParams) {
            str = str + str2;
        }
        Pdlog.m3273d("startTts", "keyParams:" + str);
        String diskCache = DiskLruCacheManager.INSTANCE.getDiskCache(filePath, DiskLruCacheManager.INSTANCE.genDiskLruKey(filePath, text, str));
        Pdlog.m3273d("startTts", "diskCache:" + diskCache);
        if (diskCache.length() > 0) {
            onTtsListener.onComplete(diskCache);
        } else if (engine == TtsEngine.Ifly) {
            iflyTtsTask.genTtsFile(text, filePath, onTtsListener, str);
        } else {
            googleTtsTask.genTtsFile(text, filePath, onTtsListener, str);
        }
    }
}
