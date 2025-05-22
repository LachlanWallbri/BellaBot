package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.content.Context;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.presenter.robot_voices.TtsVoiceProperty;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.disinfect.baselib.network.response.TtsVoiceInfo;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import com.pudutech.disklru.DiskLruCacheManager;
import com.pudutech.disklru.utils.Utils;
import com.pudutech.tts_sdk.PdTtsSdk;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.pudutech.tts_sdk.utils.FileUtils;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TtsVoiceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004JP\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u000428\u0010\u0014\u001a4\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0015J\u0014\u0010\u001b\u001a\u00020\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dJ\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010#\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!2\u0006\u0010$\u001a\u00020\u0016H\u0002J\u001e\u0010#\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0016J\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dJ\u0006\u0010&\u001a\u00020\u0004J\u0018\u0010'\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!2\u0006\u0010$\u001a\u00020\u0016H\u0002J\u0010\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010\u0012\u001a\u00020\u0004J(\u0010*\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u00162\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010-J&\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u00042\u0016\b\u0002\u00100\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u000101J\u0006\u00102\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u00063"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceManager;", "", "()V", "KEY_MERCHANT_VERSION", "", "TAG", "lock", ES6Iterator.VALUE_PROPERTY, "merchantTtsVersion", "getMerchantTtsVersion", "()Ljava/lang/String;", "setMerchantTtsVersion", "(Ljava/lang/String;)V", "copyTempTtsToMerchantTts", "srcPath", "destFileName", "createMerchantTts", "", "text", TransferTable.COLUMN_KEY, "onComplete", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "isSuccess", "path", "deleteOldMerchantTts", "existsList", "", "Lcom/pudutech/disinfect/baselib/network/response/TtsVoiceInfo;", "getCommonVoiceFilePath", "context", "Landroid/content/Context;", "getFlCommonVoiceFilePath", "getMerchantTtsFilePath", "isIfly", "getMerchantTtsList", "getSpeaker", "getTempMerchantTtsFilePath", "getTtsVoiceProperty", "Lcom/pudutech/bumblebee/presenter/robot_voices/TtsVoiceProperty;", "playTtsVoice", "isPlayFile", "playFinish", "Lkotlin/Function0;", "resetMerchantTts", "speaker", "callComplete", "Lkotlin/Function1;", "resetMerchantTtsAllData", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TtsVoiceManager {
    private static final String KEY_MERCHANT_VERSION = "key_merchant_version";
    private static final String TAG = "TtsVoiceManager";
    public static final TtsVoiceManager INSTANCE = new TtsVoiceManager();
    private static final Object lock = new Object();

    private TtsVoiceManager() {
    }

    public final void resetMerchantTtsAllData() {
        VoicePackageHelper.INSTANCE.setSelectMerchantTts(false);
        VoicePackageHelper.INSTANCE.setMerchantTtsData((String) null);
    }

    public final String getSpeaker() {
        String selectedSpeaker = TtsVoiceFlHelper.INSTANCE.getSelectedSpeaker();
        if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
            selectedSpeaker = TtsVoiceHelper.INSTANCE.getSelectedSpeaker();
        }
        Pdlog.m3273d(TAG, "getSpeaker() speaker=" + selectedSpeaker);
        return selectedSpeaker;
    }

    public final TtsVoiceProperty getTtsVoiceProperty(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        boolean z = LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw();
        String localeStr$default = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
        String speaker = getSpeaker();
        String flCommonVoiceFilePath = getFlCommonVoiceFilePath(BaseApplication.INSTANCE.getInstance());
        if (z) {
            flCommonVoiceFilePath = getCommonVoiceFilePath(BaseApplication.INSTANCE.getInstance());
        }
        String diskCache = DiskLruCacheManager.INSTANCE.getDiskCache(flCommonVoiceFilePath, DiskLruCacheManager.INSTANCE.genDiskLruKey(flCommonVoiceFilePath, text, localeStr$default, speaker));
        Pdlog.m3273d(TAG, "isExistTtsVoice() diskCache = " + diskCache);
        String str = diskCache;
        if (str == null || str.length() == 0) {
            return null;
        }
        return new TtsVoiceProperty(0L, diskCache);
    }

    public static /* synthetic */ void playTtsVoice$default(TtsVoiceManager ttsVoiceManager, String str, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        ttsVoiceManager.playTtsVoice(str, z, function0);
    }

    public final void playTtsVoice(String text, boolean isPlayFile, Function0<Unit> playFinish) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        boolean z = LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw();
        String localeStr$default = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
        PdTtsSdk.TtsEngine ttsEngine = PdTtsSdk.TtsEngine.Google;
        String speaker = getSpeaker();
        String flCommonVoiceFilePath = getFlCommonVoiceFilePath(BaseApplication.INSTANCE.getInstance());
        if (z) {
            ttsEngine = PdTtsSdk.TtsEngine.Ifly;
            flCommonVoiceFilePath = getCommonVoiceFilePath(BaseApplication.INSTANCE.getInstance());
        } else {
            TtsVoiceFlHelper.INSTANCE.setGoogleTtsVoiceName();
        }
        Pdlog.m3273d(TAG, "playTtsVoice() text =" + text + " isZh =" + z + " localeStr =" + localeStr$default + " ttsEngine =" + ttsEngine + " filePath =" + flCommonVoiceFilePath + " speaker =" + speaker);
        PdTtsSdk.INSTANCE.startTts(text, flCommonVoiceFilePath, new TtsVoiceManager$playTtsVoice$1(isPlayFile, playFinish), ttsEngine, localeStr$default, speaker);
    }

    private final String getCommonVoiceFilePath(Context context) {
        StringBuilder sb = new StringBuilder();
        File cacheDir = context.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir, "context.cacheDir");
        sb.append(cacheDir.getAbsolutePath());
        sb.append("/tts/common/voice.pcm");
        return sb.toString();
    }

    private final String getFlCommonVoiceFilePath(Context context) {
        StringBuilder sb = new StringBuilder();
        File cacheDir = context.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir, "context.cacheDir");
        sb.append(cacheDir.getAbsolutePath());
        sb.append("/fl_tts/common/voice.mp3");
        return sb.toString();
    }

    public final List<TtsVoiceInfo> getMerchantTtsList() {
        List<TtsVoiceInfo> merchantTts = VoicePackageHelper.INSTANCE.getMerchantTts();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("getMerchantTtsList() result.size =");
        sb.append(merchantTts != null ? Integer.valueOf(merchantTts.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
        return merchantTts;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void resetMerchantTts$default(TtsVoiceManager ttsVoiceManager, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        ttsVoiceManager.resetMerchantTts(str, function1);
    }

    /* JADX WARN: Type inference failed for: r12v1, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r1v5, types: [T, kotlin.jvm.functions.Function0] */
    public final void resetMerchantTts(final String speaker, final Function1<? super Boolean, Unit> callComplete) {
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        final List<TtsVoiceInfo> merchantTtsList = getMerchantTtsList();
        List<TtsVoiceInfo> list = merchantTtsList;
        if (list == null || list.isEmpty()) {
            Pdlog.m3273d(TAG, "resetMerchantTts() merchantTtsList is null");
            if (callComplete != null) {
                callComplete.invoke(true);
                return;
            }
            return;
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        final int size = merchantTtsList.size();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (Function0) 0;
        objectRef.element = (Function0) new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceManager$resetMerchantTts$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String localeStr$default = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
                final TtsVoiceInfo ttsVoiceInfo = (TtsVoiceInfo) merchantTtsList.get(intRef.element);
                final String md5 = MD5Util.toMD5(((ttsVoiceInfo.getId() + localeStr$default) + ttsVoiceInfo.getText()) + speaker);
                TtsVoiceManager ttsVoiceManager = TtsVoiceManager.INSTANCE;
                String text = ttsVoiceInfo.getText();
                Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
                ttsVoiceManager.createMerchantTts(text, md5, new Function2<Boolean, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceManager$resetMerchantTts$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                        invoke(bool.booleanValue(), str);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z, String pathFile) {
                        Object obj;
                        Intrinsics.checkParameterIsNotNull(pathFile, "pathFile");
                        TtsVoiceManager ttsVoiceManager2 = TtsVoiceManager.INSTANCE;
                        obj = TtsVoiceManager.lock;
                        synchronized (obj) {
                            intRef.element++;
                            ttsVoiceInfo.setMd5(md5);
                            if (z) {
                                ttsVoiceInfo.setProduced(true);
                                TtsVoiceManager ttsVoiceManager3 = TtsVoiceManager.INSTANCE;
                                String md52 = md5;
                                Intrinsics.checkExpressionValueIsNotNull(md52, "md5");
                                String copyTempTtsToMerchantTts = ttsVoiceManager3.copyTempTtsToMerchantTts(pathFile, md52);
                                if (copyTempTtsToMerchantTts != null) {
                                    ttsVoiceInfo.setPath(copyTempTtsToMerchantTts);
                                    if (intRef.element >= size) {
                                        String json = GsonSingleton.INSTANCE.getINSTANCE().toJson(merchantTtsList);
                                        VoicePackageHelper.INSTANCE.setMerchantTtsData(json);
                                        Pdlog.m3273d("TtsVoiceManager", "resetMerchantTts() toJson =" + json);
                                        TtsVoiceManager.INSTANCE.deleteOldMerchantTts(merchantTtsList);
                                        Function1 function1 = callComplete;
                                        if (function1 != null) {
                                        }
                                    } else {
                                        Function0 function0 = (Function0) objectRef.element;
                                        if (function0 != null) {
                                        }
                                        Pdlog.m3273d("TtsVoiceManager", "resetMerchantTts() invoke curNum =" + intRef.element);
                                        Unit unit = Unit.INSTANCE;
                                    }
                                    return;
                                }
                                TtsVoiceManager ttsVoiceManager4 = TtsVoiceManager.INSTANCE;
                                List<TtsVoiceInfo> merchantTtsList2 = TtsVoiceManager.INSTANCE.getMerchantTtsList();
                                if (merchantTtsList2 == null) {
                                    merchantTtsList2 = CollectionsKt.emptyList();
                                }
                                ttsVoiceManager4.deleteOldMerchantTts(merchantTtsList2);
                                Function1 function12 = callComplete;
                                if (function12 != null) {
                                }
                                return;
                            }
                            TtsVoiceManager ttsVoiceManager5 = TtsVoiceManager.INSTANCE;
                            List<TtsVoiceInfo> merchantTtsList3 = TtsVoiceManager.INSTANCE.getMerchantTtsList();
                            if (merchantTtsList3 == null) {
                                merchantTtsList3 = CollectionsKt.emptyList();
                            }
                            ttsVoiceManager5.deleteOldMerchantTts(merchantTtsList3);
                            Function1 function13 = callComplete;
                            if (function13 != null) {
                            }
                        }
                    }
                });
            }
        };
        Function0 function0 = (Function0) objectRef.element;
        if (function0 != null) {
        }
        Pdlog.m3273d(TAG, "resetMerchantTts() merchantTtsList =" + merchantTtsList);
    }

    public final String copyTempTtsToMerchantTts(String srcPath, String destFileName) {
        Intrinsics.checkParameterIsNotNull(srcPath, "srcPath");
        Intrinsics.checkParameterIsNotNull(destFileName, "destFileName");
        String merchantTtsFilePath = getMerchantTtsFilePath(BaseApplication.INSTANCE.getInstance(), destFileName, LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw());
        if (FileUtils.copyFile(srcPath, merchantTtsFilePath)) {
            return merchantTtsFilePath;
        }
        return null;
    }

    public final void deleteOldMerchantTts(List<TtsVoiceInfo> existsList) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(existsList, "existsList");
        File file = new File(getMerchantTtsFilePath(BaseApplication.INSTANCE.getInstance(), LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()));
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "ttsStoreFile.listFiles()");
            for (File file2 : listFiles) {
                StringBuilder sb = new StringBuilder();
                sb.append("deleteOldMerchantTts file.path:");
                Intrinsics.checkExpressionValueIsNotNull(file2, "file");
                sb.append(file2.getPath());
                sb.append(' ');
                Pdlog.m3273d(TAG, sb.toString());
                Iterator<T> it = existsList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        obj = it.next();
                        if (file2.getPath().equals(((TtsVoiceInfo) obj).getPath())) {
                            break;
                        }
                    } else {
                        obj = null;
                        break;
                    }
                }
                if (obj == null) {
                    Pdlog.m3273d(TAG, "deleteOldMerchantTts result: " + FileUtils.delete(file2) + " , file.path: " + file2.getPath());
                }
            }
        }
    }

    public final void createMerchantTts(String text, String key, final Function2<? super Boolean, ? super String, Unit> onComplete) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(key, "key");
        boolean z = LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw();
        String localeStr$default = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
        PdTtsSdk.TtsEngine ttsEngine = PdTtsSdk.TtsEngine.Google;
        String tempMerchantTtsFilePath = getTempMerchantTtsFilePath(BaseApplication.INSTANCE.getInstance(), z);
        if (z) {
            ttsEngine = PdTtsSdk.TtsEngine.Ifly;
        } else {
            TtsVoiceFlHelper.INSTANCE.setGoogleTtsVoiceName();
        }
        PdTtsSdk.TtsEngine ttsEngine2 = ttsEngine;
        Pdlog.m3273d(TAG, "playTtsVoice() isZh =" + z + " localeStr =" + localeStr$default + " ttsEngine =" + ttsEngine2 + " filePath =" + tempMerchantTtsFilePath + " text = " + text + " key = " + key);
        PdTtsSdk.INSTANCE.startTts(text, tempMerchantTtsFilePath, new OnTtsListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceManager$createMerchantTts$1
            @Override // com.pudutech.tts_sdk.tts.OnTtsListener
            public void onComplete(String filePath) {
                Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                Function2 function2 = Function2.this;
                if (function2 != null) {
                }
                Pdlog.m3273d("TtsVoiceManager", "createMerchantTts() onComplete =" + filePath);
            }

            @Override // com.pudutech.tts_sdk.tts.OnTtsListener
            public void onError(int code, String msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                Function2 function2 = Function2.this;
                if (function2 != null) {
                }
                Pdlog.m3273d("TtsVoiceManager", "createMerchantTts() onError =" + code + "--" + msg);
            }

            @Override // com.pudutech.tts_sdk.tts.OnTtsListener
            public void onProgress(int proses) {
                Pdlog.m3273d("TtsVoiceManager", "createMerchantTts() onProgress =" + proses);
            }
        }, ttsEngine2, key);
    }

    public final String getMerchantTtsFilePath(Context context, String name, boolean isIfly) {
        String str;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(TAG, "getMerchantTtsFilePath");
        StringBuilder sb = new StringBuilder();
        sb.append(getMerchantTtsFilePath(context, isIfly));
        if (isIfly) {
            str = name + TtsVoiceHelper.FLIE_MARK;
        } else {
            str = name + ".mp3";
        }
        sb.append(str);
        return sb.toString();
    }

    private final String getMerchantTtsFilePath(Context context, boolean isIfly) {
        if (isIfly) {
            return Utils.INSTANCE.getDiskCacheDir(context) + "/merchant/tts/";
        }
        return Utils.INSTANCE.getDiskCacheDir(context) + "/merchant/fl_tts/";
    }

    private final String getTempMerchantTtsFilePath(Context context, boolean isIfly) {
        StringBuilder sb = new StringBuilder();
        sb.append(Utils.INSTANCE.getDiskCacheDir(context));
        sb.append("/temptts/temp_voice");
        sb.append(isIfly ? TtsVoiceHelper.FLIE_MARK : ".mp3");
        return sb.toString();
    }

    public final void setMerchantTtsVersion(String str) {
        MMKVManager.INSTANCE.getINSTANCE().set(LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null) + KEY_MERCHANT_VERSION, str);
    }

    public final String getMerchantTtsVersion() {
        return MMKVManager.INSTANCE.getINSTANCE().getString(LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null) + KEY_MERCHANT_VERSION, "");
    }
}
