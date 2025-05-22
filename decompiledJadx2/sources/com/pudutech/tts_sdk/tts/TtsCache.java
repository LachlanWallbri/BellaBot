package com.pudutech.tts_sdk.tts;

import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.tts_sdk.TtsConfig;
import com.pudutech.tts_sdk.utils.EncryptUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: TtsCache.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/tts_sdk/tts/TtsCache;", "", "()V", "SUFFIX", "", "checkCache", "", "text", "genCacheKey", "getCacheFilePath", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class TtsCache {
    private final String SUFFIX = TtsVoiceHelper.FLIE_MARK;

    private final String genCacheKey(String text) {
        return TtsConfig.CACHE_VERSION + EncryptUtils.encryptMD5ToString(text);
    }

    public final String getCacheFilePath(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        return genCacheKey(text) + this.SUFFIX;
    }

    public final boolean checkCache(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        return new File(getCacheFilePath(text)).exists();
    }
}
