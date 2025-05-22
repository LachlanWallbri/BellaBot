package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.content.Context;
import android.text.TextUtils;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.bean.TtsVoiceData;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectSpeakerAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.roomdata.TtsDataBaseManager;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.tts_sdk.TtsConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: TtsVoiceFlHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ&\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0018\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0018\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0018\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u001e\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010 \u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0018\u0010!\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0018\u0010\"\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0018\u0010#\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0018\u0010$\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0018\u0010%\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0018\u0010&\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0006\u0010'\u001a\u00020\u0004J\u0018\u0010(\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020+0*j\b\u0012\u0004\u0012\u00020+`,J\u000e\u0010-\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010.\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000fJ&\u0010/\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u00100\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u00101\u001a\u0002022\u0006\u0010\u0010\u001a\u00020\u0004J\u000e\u00103\u001a\u00020\r2\u0006\u00104\u001a\u00020\u0004J\u0006\u00105\u001a\u00020\rJ\u0016\u00106\u001a\u00020\r2\u0006\u00107\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u00068"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceFlHelper;", "", "()V", "FLIE_MARK", "", "TAG", "kotlin.jvm.PlatformType", "speakerMap", "", "", "getSpeakerMap", "()Ljava/util/Map;", "addConfigItem", "", "ttsVoiceData", "Lcom/pudutech/bumblebee/robot_ui/bean/TtsVoiceData;", "filePath", "md5", "text", "ttsVoiceType", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "deleteVoice", "configData", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "getBirthDayFilePath", "context", "Landroid/content/Context;", "name", "getCruiseFilePath", "getCruiseStayFilePath", "getDeliverFilePath", "getFilePath", "getGreeterFilePath", "getGreeterGuideFilePath", "getGuideArrivalFilePath", "getPalletDeliverFilePath", "getRecyclePointArriveFilePath", "getRecycleTableArriveFilePath", "getRecycleTableLeaveFilePath", "getSelectedSpeaker", "getSpcialModeArrivePath", "getSpeakerList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectSpeakerAdapter$SpeakerData;", "Lkotlin/collections/ArrayList;", "getTempPlayFilePath", "getTtsConfigData", "getTtsVoiceData", "getTtsVoiceList", "isGoogleTts", "", "setGoogleTtsSpeaker", "speaker", "setGoogleTtsVoiceName", "updataVoice", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TtsVoiceFlHelper {
    public static final String FLIE_MARK = ".mp3";
    public static final TtsVoiceFlHelper INSTANCE;
    private static final String TAG;
    private static final Map<String, List<String>> speakerMap;

    static {
        TtsVoiceFlHelper ttsVoiceFlHelper = new TtsVoiceFlHelper();
        INSTANCE = ttsVoiceFlHelper;
        TAG = ttsVoiceFlHelper.getClass().getSimpleName();
        speakerMap = MapsKt.mapOf(TuplesKt.m3968to("ja-JP", CollectionsKt.listOf("ja-JP-Wavenet-B")), TuplesKt.m3968to("ko-KR", CollectionsKt.listOf("ko-KR-Wavenet-A")), TuplesKt.m3968to("es-ES", CollectionsKt.listOf("es-ES-Standard-A")), TuplesKt.m3968to("es-US", CollectionsKt.listOf("es-ES-Standard-A")), TuplesKt.m3968to("fr-FR", CollectionsKt.listOf("fr-FR-Wavenet-A")), TuplesKt.m3968to("it-IT", CollectionsKt.listOf("it-IT-Wavenet-A")), TuplesKt.m3968to("ru-RU", CollectionsKt.listOf((Object[]) new String[]{"ru-RU-Standard-E", "ru-RU-Wavenet-B"})), TuplesKt.m3968to("de-DE", CollectionsKt.listOf("de-DE-Wavenet-A")), TuplesKt.m3968to("en-US", CollectionsKt.listOf((Object[]) new String[]{"en-US-Wavenet-H", "en-AU-Wavenet-C"})), TuplesKt.m3968to("th-TH", CollectionsKt.listOf("th-TH-Standard-A")), TuplesKt.m3968to("pt-PT", CollectionsKt.listOf("pt-PT-Wavenet-A")), TuplesKt.m3968to("pt-BR", CollectionsKt.listOf("pt-BR-Wavenet-C")), TuplesKt.m3968to("nl-NL", CollectionsKt.listOf("nl-NL-Wavenet-A")), TuplesKt.m3968to("pl-PL", CollectionsKt.listOf("pl-PL-Wavenet-A")), TuplesKt.m3968to("tr-TR", CollectionsKt.listOf("tr-TR-Wavenet-A")), TuplesKt.m3968to("in-ID", CollectionsKt.listOf("id-ID-Standard-A")), TuplesKt.m3968to("id-ID", CollectionsKt.listOf("id-ID-Wavenet-A")), TuplesKt.m3968to("sv-SE", CollectionsKt.listOf("sv-SE-Wavenet-A")), TuplesKt.m3968to("zh-HK", CollectionsKt.listOf("yue-HK-Standard-A")), TuplesKt.m3968to("yue-HK", CollectionsKt.listOf("yue-HK-Wavenet-A")), TuplesKt.m3968to("cs-CZ", CollectionsKt.listOf("cs-CZ-Wavenet-A")), TuplesKt.m3968to("ar-AR", CollectionsKt.listOf("ar-XA-Standard-A")), TuplesKt.m3968to("hu-HU", CollectionsKt.listOf("hu-HU-Wavenet-A")), TuplesKt.m3968to("bg-BG", CollectionsKt.listOf("bg-bg-Standard-A")), TuplesKt.m3968to("vi-VN", CollectionsKt.listOf("vi-VN-Standard-A")), TuplesKt.m3968to("sk-SK", CollectionsKt.listOf("sk-SK-Wavenet-A")), TuplesKt.m3968to("ro-RO", CollectionsKt.listOf("ro-RO-Wavenet-A")), TuplesKt.m3968to("sr-RS", CollectionsKt.listOf("sr-rs-Standard-A")), TuplesKt.m3968to("ms-MY", CollectionsKt.listOf("ms-MY-Standard-A")));
    }

    private TtsVoiceFlHelper() {
    }

    public final Map<String, List<String>> getSpeakerMap() {
        return speakerMap;
    }

    public final List<TtsVoiceData> getTtsVoiceList(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        String localeStr$default = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
        List<TtsVoiceData> ttsList = TtsDataBaseManager.INSTANCE.getInstance().getMTtsVoiceDao().getTtsList(localeStr$default, ttsVoiceType.name());
        Pdlog.m3273d(TAG, "getTtsVoiceList-" + localeStr$default + "--" + ttsVoiceType.name());
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("getTtsVoiceList的数据：");
        sb.append(ttsList);
        Pdlog.m3273d(str, sb.toString());
        return ttsList;
    }

    public final void updataVoice(TtsVoiceHelper.TtsConfigData item, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Pdlog.m3273d(TAG, "updataVoice---TtsConfigData：" + item + "---TtsVoiceType：" + ttsVoiceType.name());
        try {
            switch (ttsVoiceType) {
                case DELIVER_TYPE:
                case CRUISE_STAY_TYPE:
                case BIRTHDAY_TYPE:
                    List<TtsVoiceData> ttsVoiceList = getTtsVoiceList(ttsVoiceType);
                    if (ttsVoiceList != null) {
                        for (TtsVoiceData ttsVoiceData : ttsVoiceList) {
                            ttsVoiceData.setSelect(StringsKt.equals$default(ttsVoiceData.getMd5(), item.getMd5(), false, 2, null));
                        }
                        TtsDataBaseManager.INSTANCE.getInstance().getMTtsVoiceDao().updataTts(ttsVoiceList);
                        Pdlog.m3273d(TAG, "updataVoice---只能单选：---TtsVoiceType：" + ttsVoiceType.name() + "---list:" + ttsVoiceList);
                        return;
                    }
                    return;
                case CRUISE_TYPE:
                case GREETER_TYPE:
                case GREETER_GUIDE_TYPE:
                case SPECIAL_MODE_ARRIVE:
                case RECYCLE_POINT_ARRIVE:
                case RECYCLE_TABLE_ARRIVE:
                case RECYCLE_TABLE_LEAVE:
                    TtsVoiceData ttsMd5Data = TtsDataBaseManager.INSTANCE.getInstance().getMTtsVoiceDao().getTtsMd5Data(item.getMd5(), ttsVoiceType.name());
                    if (ttsMd5Data != null) {
                        ttsMd5Data.setSelect(item.isSelect());
                        TtsDataBaseManager.INSTANCE.getInstance().getMTtsVoiceDao().updataTts(ttsMd5Data);
                        Pdlog.m3273d(TAG, "updataVoice---可以多选：---TtsVoiceType：" + ttsVoiceType.name() + "---list:" + ttsMd5Data);
                        return;
                    }
                    return;
                case PALLET_DELIVER_TYPE:
                    item.setSelect(!item.isSelect());
                    Pdlog.m3273d(TAG, "updataVoice---可不选---TtsVoiceType：" + ttsVoiceType.name() + "---item:" + item);
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "updataVoice--Exception:" + e);
        }
    }

    public final void deleteVoice(TtsVoiceHelper.TtsConfigData configData, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(configData, "configData");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        try {
            TtsDataBaseManager.INSTANCE.getInstance().getMTtsVoiceDao().deleteTtsByMd5(configData.getMd5());
            Pdlog.m3273d(TAG, "deleteVoice:" + configData);
            List<TtsVoiceData> ttsVoiceList = getTtsVoiceList(ttsVoiceType);
            FileUtils.delete(configData.getPath());
            if (ttsVoiceList == null || ttsVoiceList.size() <= 0) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : ttsVoiceList) {
                if (((TtsVoiceData) obj).getIsSelect()) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.isEmpty()) {
                ttsVoiceList.get(0).setSelect(true);
            }
            TtsDataBaseManager.INSTANCE.getInstance().getMTtsVoiceDao().updataTts(ttsVoiceList);
            Pdlog.m3273d(TAG, "deleteVoice--updataTts:" + ttsVoiceList);
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "deleteVoice--Exception:" + e);
        }
    }

    public final void addConfigItem(String filePath, String md5, String text, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        TtsVoiceData ttsVoiceData = new TtsVoiceData();
        ttsVoiceData.setName(text);
        ttsVoiceData.setMd5(md5);
        ttsVoiceData.setLocale(LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null));
        ttsVoiceData.setFliePath(filePath);
        ttsVoiceData.setTtsType(ttsVoiceType.name());
        TtsDataBaseManager.INSTANCE.getInstance().getMTtsVoiceDao().addTts(ttsVoiceData);
    }

    public final TtsVoiceHelper.TtsConfigData getTtsConfigData(TtsVoiceData ttsVoiceData) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceData, "ttsVoiceData");
        String fliePath = ttsVoiceData.getFliePath();
        String str = fliePath != null ? fliePath : "";
        String name = ttsVoiceData.getName();
        String str2 = name != null ? name : "";
        String md5 = ttsVoiceData.getMd5();
        return new TtsVoiceHelper.TtsConfigData(str, md5 != null ? md5 : "", str2, ttsVoiceData.getIsSelect(), false, 16, null);
    }

    public final TtsVoiceData getTtsVoiceData(String filePath, String md5, String text, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        TtsVoiceData ttsVoiceData = new TtsVoiceData();
        ttsVoiceData.setName(text);
        ttsVoiceData.setMd5(md5);
        ttsVoiceData.setLocale(LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null));
        ttsVoiceData.setFliePath(filePath);
        ttsVoiceData.setTtsType(ttsVoiceType.name());
        return ttsVoiceData;
    }

    public final void addConfigItem(TtsVoiceData ttsVoiceData) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceData, "ttsVoiceData");
        try {
            String md5 = ttsVoiceData.getMd5();
            String ttsType = ttsVoiceData.getTtsType();
            if (ttsType == null) {
                ttsType = "";
            }
            if (TtsDataBaseManager.INSTANCE.getInstance().getMTtsVoiceDao().getTtsMd5Data(md5, ttsType) != null) {
                TtsDataBaseManager.INSTANCE.getInstance().getMTtsVoiceDao().updataTts(ttsVoiceData);
                Pdlog.m3273d(TAG, "addFlTtsVoice:updataTts:" + ttsVoiceData);
            } else {
                TtsDataBaseManager.INSTANCE.getInstance().getMTtsVoiceDao().addTts(ttsVoiceData);
                Pdlog.m3273d(TAG, "addFlTtsVoice:addConfigItem:" + ttsVoiceData);
            }
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "addConfigItem--Exception:" + e);
        }
    }

    public final String getFilePath(Context context, String name, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        String cruiseFilePath;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        switch (ttsVoiceType) {
            case CRUISE_TYPE:
                cruiseFilePath = getCruiseFilePath(context, name);
                break;
            case DELIVER_TYPE:
                cruiseFilePath = getDeliverFilePath(context, name);
                break;
            case GREETER_TYPE:
                cruiseFilePath = getGreeterFilePath(context, name);
                break;
            case GREETER_GUIDE_TYPE:
                cruiseFilePath = getGreeterGuideFilePath(context, name);
                break;
            case SPECIAL_MODE_ARRIVE:
                cruiseFilePath = getSpcialModeArrivePath(context, name);
                break;
            case CRUISE_STAY_TYPE:
                cruiseFilePath = getCruiseStayFilePath(context, name);
                break;
            case BIRTHDAY_TYPE:
                cruiseFilePath = getBirthDayFilePath(context, name);
                break;
            case GUIDE_ARRIVAL_TYPE:
                cruiseFilePath = getGuideArrivalFilePath(context, name);
                break;
            case PALLET_DELIVER_TYPE:
                cruiseFilePath = getDeliverFilePath(context, name);
                break;
            case RECYCLE_POINT_ARRIVE:
                cruiseFilePath = getRecyclePointArriveFilePath(context, name);
                break;
            case RECYCLE_TABLE_ARRIVE:
                cruiseFilePath = getRecycleTableArriveFilePath(context, name);
                break;
            case RECYCLE_TABLE_LEAVE:
                cruiseFilePath = getRecycleTableLeaveFilePath(context, name);
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        File fileByPath = com.pudutech.tts_sdk.utils.FileUtils.getFileByPath(cruiseFilePath);
        Intrinsics.checkExpressionValueIsNotNull(fileByPath, "fileByPath");
        File parentFile = fileByPath.getParentFile();
        Pdlog.m3273d(TAG, "getFilePath--pathFile:" + cruiseFilePath);
        com.pudutech.tts_sdk.utils.FileUtils.createOrExistsDir(parentFile);
        return cruiseFilePath;
    }

    public final boolean isGoogleTts(String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        return StringsKt.endsWith$default(filePath, ".mp3", false, 2, (Object) null);
    }

    private final String getCruiseFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getCruiseFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_cruise/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getDeliverFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getDeliverFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_deliver/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getPalletDeliverFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getPalletDeliverFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_pallet_deliver/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getGreeterFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getGreeterFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_greeter/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getGreeterGuideFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getGreeterGuideFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_greeter_guide/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getGuideArrivalFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getGuideArrivalFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_guide_arrival/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getSpcialModeArrivePath(Context context, String name) {
        Pdlog.m3274e(TAG, "SpcialModeArrivePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_spcial_mode_arrive/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getCruiseStayFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getDeliverFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_cruise_stay/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getBirthDayFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getDeliverFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_birthday/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getRecycleTableArriveFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getRecycleTableArriveFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_recycle_table_arrive/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getRecycleTableLeaveFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getRecycleTableLeaveFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_recycle_table_leave/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    private final String getRecyclePointArriveFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getRecycleTableLeaveFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/fl_tts_recycle_point_leave/");
        sb.append(name);
        sb.append(".mp3");
        return sb.toString();
    }

    public final String getTempPlayFilePath(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        StringBuilder sb = new StringBuilder();
        File cacheDir = context.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir, "context.cacheDir");
        sb.append(cacheDir.getAbsolutePath());
        sb.append("/tts/fl_temp_play.mp3");
        String sb2 = sb.toString();
        File fileByPath = com.pudutech.tts_sdk.utils.FileUtils.getFileByPath(sb2);
        Intrinsics.checkExpressionValueIsNotNull(fileByPath, "fileByPath");
        File parentFile = fileByPath.getParentFile();
        String str = TAG;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("getTempPlayFilePath--parentFile:");
        Intrinsics.checkExpressionValueIsNotNull(parentFile, "parentFile");
        sb3.append(parentFile.getName());
        Pdlog.m3273d(str, sb3.toString());
        com.pudutech.tts_sdk.utils.FileUtils.createOrExistsDir(parentFile);
        return sb2;
    }

    public final void setGoogleTtsVoiceName() {
        String selectedSpeaker = getSelectedSpeaker();
        String localeStr$default = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
        Pdlog.m3277w(TAG, "setGoogleTtsVoiceName======localeStr:" + localeStr$default);
        int hashCode = localeStr$default.hashCode();
        if (hashCode != 93023053) {
            if (hashCode == 100292291) {
                if (localeStr$default.equals("in-ID")) {
                    localeStr$default = "id-ID";
                }
            } else if (hashCode == 115813378 && localeStr$default.equals("zh-HK")) {
                localeStr$default = "yue-HK";
            }
        } else if (localeStr$default.equals("ar-AR")) {
            localeStr$default = "ar-XA";
        }
        if (TextUtils.isEmpty(selectedSpeaker)) {
            Pdlog.m3277w(TAG, "setGoogleTtsVoiceName 没有找到对应的VoiceName");
            return;
        }
        TtsConfig.INSTANCE.setGoogleTtsVoiceLanguage(localeStr$default);
        TtsConfig.INSTANCE.setGoogleTtsVoiceName(selectedSpeaker);
        Pdlog.m3277w(TAG, "setGoogleTtsVoiceName成功--googleTtsVoiceLanguage：" + TtsConfig.INSTANCE.getGoogleTtsVoiceLanguage() + "====googleTtsVoiceName：" + TtsConfig.INSTANCE.getGoogleTtsVoiceName() + '}');
    }

    public final void setGoogleTtsSpeaker(String speaker) {
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        TtsConfig.INSTANCE.setGoogleTtsVoiceLanguage(LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null));
        TtsConfig.INSTANCE.setGoogleTtsVoiceName(speaker);
        Pdlog.m3277w(TAG, "setGoogleTtsSpeaker--googleTtsVoiceLanguage：" + TtsConfig.INSTANCE.getGoogleTtsVoiceLanguage() + "====googleTtsVoiceName：" + TtsConfig.INSTANCE.getGoogleTtsVoiceName() + '}');
    }

    public final String getSelectedSpeaker() {
        String str;
        List<String> list = speakerMap.get(LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null));
        if (list == null || (str = list.get(0)) == null) {
            str = "";
        }
        return SpUtils.get(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.INSTANCE.getSpeakerKey(), str);
    }

    public final ArrayList<SelectSpeakerAdapter.SpeakerData> getSpeakerList() {
        List<String> list = speakerMap.get(LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null));
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        String selectedSpeaker = getSelectedSpeaker();
        List<String> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        int i = 0;
        for (Object obj : list2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            String string = i == 0 ? RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_198) : RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_195) + i;
            Intrinsics.checkExpressionValueIsNotNull(string, "if (index == 0) RobotCon…\n                }$index\"");
            arrayList.add(new SelectSpeakerAdapter.SpeakerData(str, string, Intrinsics.areEqual(selectedSpeaker, str), false));
            i = i2;
        }
        return new ArrayList<>(arrayList);
    }
}
