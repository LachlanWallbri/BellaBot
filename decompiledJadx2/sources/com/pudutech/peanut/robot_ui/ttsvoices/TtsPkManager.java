package com.pudutech.peanut.robot_ui.ttsvoices;

import android.util.Log;
import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.ttsvoices.bean.TtsDownInfo;
import com.pudutech.peanut.robot_ui.ttsvoices.bean.TtsVoiceItem;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.tts_sdk.TtsConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: TtsPkManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\u0011\u0010\u0019\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ)\u0010\u001b\u001a\u00020\u00172!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00170\u001dJ/\u0010!\u001a\u00020\u00172%\b\u0002\u0010\u001c\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001dH\u0002J\u0006\u0010\"\u001a\u00020#J\b\u0010$\u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u0004\u0018\u00010\u00042\u0006\u0010&\u001a\u00020'J\u0010\u0010(\u001a\u0004\u0018\u00010\u00042\u0006\u0010&\u001a\u00020'J\u0010\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000RJ\u0010\u0011\u001a>\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\n0\u0013j\b\u0012\u0004\u0012\u00020\n`\u00140\u0012j\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\n0\u0013j\b\u0012\u0004\u0012\u00020\n`\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ttsvoices/TtsPkManager;", "", "()V", "TAG", "", "cancelFlag", "", "isDowning", "needDownTts", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsVoiceItem;", "pkPath", "ttsDownInfo", "Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsDownInfo;", "ttsListData", "Lcom/pudutech/peanut/robot_ui/ttsvoices/TtsListData;", "ttsType", "voiceMap", "Ljava/util/HashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "Lkotlin/collections/HashMap;", "cancel", "", "checkAndDownload", "checkVoice", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downTtsVoice", "cb", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "info", "genVoiceIfNeed", "getNeedDownSize", "", "getPkPath", "getVoiceName", "item", "Lcom/pudutech/peanut/robot_ui/ttsvoices/VoiceItem;", "getVoicePath", "md5", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TtsPkManager {
    private static boolean cancelFlag = false;
    private static boolean isDowning = false;
    private static TtsDownInfo ttsDownInfo = null;
    public static final TtsPkManager INSTANCE = new TtsPkManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String pkPath = pkPath;
    private static final String pkPath = pkPath;
    private static final String ttsType = ".pcm";
    private static final TtsListData ttsListData = new TtsListData();
    private static final HashMap<String, ArrayList<TtsVoiceItem>> voiceMap = new HashMap<>();
    private static final CopyOnWriteArrayList<TtsVoiceItem> needDownTts = new CopyOnWriteArrayList<>();

    private TtsPkManager() {
    }

    public final void checkAndDownload() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TtsPkManager$checkAndDownload$1(null), 3, null);
    }

    public final int getNeedDownSize() {
        return needDownTts.size();
    }

    public final Object checkVoice(Continuation<? super Boolean> continuation) {
        String pkPath2 = getPkPath();
        Pdlog.m3273d(TAG, "checkAndDownload " + pkPath2);
        File file = new File(pkPath2);
        if (!file.exists()) {
            file.mkdirs();
        }
        needDownTts.clear();
        voiceMap.clear();
        for (TtsVoiceItem ttsVoiceItem : ttsListData.get()) {
            String md5 = MD5Util.toMD5(ttsVoiceItem.getValue());
            Intrinsics.checkExpressionValueIsNotNull(md5, "MD5Util.toMD5(it.value)");
            ttsVoiceItem.setMd5(md5);
            ttsVoiceItem.setPath(INSTANCE.getVoicePath(ttsVoiceItem.getMd5()));
            if (!new File(ttsVoiceItem.getPath()).exists()) {
                needDownTts.add(ttsVoiceItem);
            } else {
                if (voiceMap.get(ttsVoiceItem.getNoName()) == null) {
                    voiceMap.put(ttsVoiceItem.getNoName(), new ArrayList<>());
                }
                ArrayList<TtsVoiceItem> arrayList = voiceMap.get(ttsVoiceItem.getNoName());
                if (arrayList == null) {
                    Intrinsics.throwNpe();
                }
                arrayList.add(ttsVoiceItem);
            }
        }
        return Boxing.boxBoolean(needDownTts.isEmpty());
    }

    public final void downTtsVoice(Function1<? super TtsDownInfo, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        if (needDownTts.isEmpty()) {
            cb.invoke(new TtsDownInfo(0, 0, 0, 4, null));
        } else {
            cancelFlag = false;
            genVoiceIfNeed(cb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void genVoiceIfNeed$default(TtsPkManager ttsPkManager, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        ttsPkManager.genVoiceIfNeed(function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void genVoiceIfNeed(final Function1<? super TtsDownInfo, Unit> cb) {
        Pdlog.m3273d(TAG, "genVoiceIfNeed " + needDownTts.isEmpty());
        Pdlog.m3273d(TAG, "genCustomVoiceIfNeed cancal = " + cancelFlag);
        if (cancelFlag) {
            ttsDownInfo = (TtsDownInfo) null;
            return;
        }
        if (!needDownTts.isEmpty()) {
            if (ttsDownInfo == null) {
                ttsDownInfo = new TtsDownInfo(needDownTts.size(), needDownTts.size(), 0, 4, null);
            }
            Pdlog.m3273d(TAG, "checkAndDownload " + needDownTts);
            final TtsVoiceItem ttsVoiceItem = (TtsVoiceItem) CollectionsKt.firstOrNull((List) needDownTts);
            if (ttsVoiceItem != null) {
                TtsVoiceHelper.INSTANCE.genTts(ttsVoiceItem.getValue(), ttsVoiceItem.getPath(), new Function1<Throwable, Unit>() { // from class: com.pudutech.peanut.robot_ui.ttsvoices.TtsPkManager$genVoiceIfNeed$$inlined$let$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th) {
                        TtsDownInfo ttsDownInfo2;
                        TtsDownInfo ttsDownInfo3;
                        CopyOnWriteArrayList copyOnWriteArrayList;
                        TtsDownInfo ttsDownInfo4;
                        String str;
                        TtsDownInfo ttsDownInfo5;
                        CopyOnWriteArrayList copyOnWriteArrayList2;
                        TtsDownInfo ttsDownInfo6;
                        TtsDownInfo ttsDownInfo7;
                        CopyOnWriteArrayList copyOnWriteArrayList3;
                        HashMap hashMap;
                        HashMap hashMap2;
                        HashMap hashMap3;
                        TtsDownInfo ttsDownInfo8;
                        if (th == null) {
                            TtsPkManager ttsPkManager = TtsPkManager.INSTANCE;
                            copyOnWriteArrayList2 = TtsPkManager.needDownTts;
                            copyOnWriteArrayList2.remove(TtsVoiceItem.this);
                            TtsPkManager ttsPkManager2 = TtsPkManager.INSTANCE;
                            ttsDownInfo6 = TtsPkManager.ttsDownInfo;
                            if (ttsDownInfo6 == null) {
                                Intrinsics.throwNpe();
                            }
                            TtsPkManager ttsPkManager3 = TtsPkManager.INSTANCE;
                            ttsDownInfo7 = TtsPkManager.ttsDownInfo;
                            if (ttsDownInfo7 == null) {
                                Intrinsics.throwNpe();
                            }
                            int all = ttsDownInfo7.getAll();
                            TtsPkManager ttsPkManager4 = TtsPkManager.INSTANCE;
                            copyOnWriteArrayList3 = TtsPkManager.needDownTts;
                            ttsDownInfo6.setLeft(all - copyOnWriteArrayList3.size());
                            Function1 function1 = cb;
                            if (function1 != null) {
                                TtsPkManager ttsPkManager5 = TtsPkManager.INSTANCE;
                                ttsDownInfo8 = TtsPkManager.ttsDownInfo;
                                if (ttsDownInfo8 == null) {
                                    Intrinsics.throwNpe();
                                }
                            }
                            TtsPkManager ttsPkManager6 = TtsPkManager.INSTANCE;
                            hashMap = TtsPkManager.voiceMap;
                            if (hashMap.get(TtsVoiceItem.this.getNoName()) == null) {
                                TtsPkManager ttsPkManager7 = TtsPkManager.INSTANCE;
                                hashMap3 = TtsPkManager.voiceMap;
                                hashMap3.put(TtsVoiceItem.this.getNoName(), new ArrayList());
                            }
                            TtsPkManager ttsPkManager8 = TtsPkManager.INSTANCE;
                            hashMap2 = TtsPkManager.voiceMap;
                            Object obj = hashMap2.get(TtsVoiceItem.this.getNoName());
                            if (obj == null) {
                                Intrinsics.throwNpe();
                            }
                            ((ArrayList) obj).add(TtsVoiceItem.this);
                            TtsPkManager.INSTANCE.genVoiceIfNeed(cb);
                            return;
                        }
                        TtsPkManager ttsPkManager9 = TtsPkManager.INSTANCE;
                        ttsDownInfo2 = TtsPkManager.ttsDownInfo;
                        if (ttsDownInfo2 == null) {
                            Intrinsics.throwNpe();
                        }
                        TtsPkManager ttsPkManager10 = TtsPkManager.INSTANCE;
                        ttsDownInfo3 = TtsPkManager.ttsDownInfo;
                        if (ttsDownInfo3 == null) {
                            Intrinsics.throwNpe();
                        }
                        int all2 = ttsDownInfo3.getAll();
                        TtsPkManager ttsPkManager11 = TtsPkManager.INSTANCE;
                        copyOnWriteArrayList = TtsPkManager.needDownTts;
                        ttsDownInfo2.setLeft(all2 - copyOnWriteArrayList.size());
                        TtsPkManager ttsPkManager12 = TtsPkManager.INSTANCE;
                        ttsDownInfo4 = TtsPkManager.ttsDownInfo;
                        if (ttsDownInfo4 == null) {
                            Intrinsics.throwNpe();
                        }
                        ttsDownInfo4.setCode(1);
                        Function1 function12 = cb;
                        if (function12 != null) {
                            TtsPkManager ttsPkManager13 = TtsPkManager.INSTANCE;
                            ttsDownInfo5 = TtsPkManager.ttsDownInfo;
                            if (ttsDownInfo5 == null) {
                                Intrinsics.throwNpe();
                            }
                        }
                        TtsPkManager ttsPkManager14 = TtsPkManager.INSTANCE;
                        TtsPkManager.ttsDownInfo = (TtsDownInfo) null;
                        TtsPkManager ttsPkManager15 = TtsPkManager.INSTANCE;
                        str = TtsPkManager.TAG;
                        Pdlog.m3274e(str, "checkAndDownload : " + Log.getStackTraceString(th));
                    }
                });
                return;
            }
            return;
        }
        ttsDownInfo = (TtsDownInfo) null;
    }

    private final String getVoicePath(String md5) {
        return ((getPkPath() + File.separator) + md5) + ttsType;
    }

    private final String getPkPath() {
        String language = new LanguageUtils(RobotContext.INSTANCE.getContext()).getCurrent().getLocale().getLanguage();
        return ((pkPath + language) + File.separator) + TtsConfig.INSTANCE.getIFLY_SPEECH_VOICE_NAME();
    }

    public final String getVoicePath(VoiceItem item) {
        TtsVoiceItem ttsVoiceItem;
        Intrinsics.checkParameterIsNotNull(item, "item");
        ArrayList<TtsVoiceItem> arrayList = voiceMap.get(item.name());
        if (arrayList == null || (ttsVoiceItem = (TtsVoiceItem) CollectionsKt.firstOrNull((List) arrayList)) == null) {
            return null;
        }
        return ttsVoiceItem.getPath();
    }

    public final String getVoiceName(VoiceItem item) {
        TtsVoiceItem ttsVoiceItem;
        Intrinsics.checkParameterIsNotNull(item, "item");
        ArrayList<TtsVoiceItem> arrayList = voiceMap.get(item.name());
        if (arrayList == null || (ttsVoiceItem = (TtsVoiceItem) CollectionsKt.firstOrNull((List) arrayList)) == null) {
            return null;
        }
        return ttsVoiceItem.getValue();
    }

    public final void cancel() {
        cancelFlag = true;
        Pdlog.m3273d(TAG, "genCustomVoiceIfNeed setCancal = " + cancelFlag);
    }
}
