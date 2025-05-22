package com.pudutech.voiceinteraction.component;

import com.pudutech.voiceinteraction.component.cmd.WorkMode;
import com.pudutech.voiceinteraction.component.config.UserInfo;
import java.util.ArrayList;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceCommentConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR*\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0010j\b\u0012\u0004\u0012\u00020\n`\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR*\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0010j\b\u0012\u0004\u0012\u00020\n`\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001a\u0010\u001c\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000eR\u001a\u0010\u001f\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\f\"\u0004\b&\u0010\u000eR\u001a\u0010'\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\f\"\u0004\b)\u0010\u000eR\u001a\u0010*\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR\u001a\u0010-\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\f\"\u0004\b/\u0010\u000eR\u001a\u00100\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\f\"\u0004\b2\u0010\u000eR\u001a\u00103\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\f\"\u0004\b5\u0010\u000eR\u001a\u00106\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0006\"\u0004\b8\u0010\bR*\u00109\u001a\u0012\u0012\u0004\u0012\u00020\n0:j\b\u0012\u0004\u0012\u00020\n`;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R*\u0010@\u001a\u0012\u0012\u0004\u0012\u00020\n0:j\b\u0012\u0004\u0012\u00020\n`;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010=\"\u0004\bB\u0010?R\u001c\u0010C\u001a\u0004\u0018\u00010DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001a\u0010I\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0006\"\u0004\bK\u0010\bR\u001a\u0010L\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0006\"\u0004\bN\u0010\bR\u001a\u0010O\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010!\"\u0004\bQ\u0010#R\u001a\u0010R\u001a\u00020SX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010W¨\u0006X"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/VoiceCommentConfig;", "", "()V", "audioCard", "", "getAudioCard", "()I", "setAudioCard", "(I)V", "cnAceWakeupWorkAssetName", "", "getCnAceWakeupWorkAssetName", "()Ljava/lang/String;", "setCnAceWakeupWorkAssetName", "(Ljava/lang/String;)V", "cnWakeupList", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "getCnWakeupList", "()Ljava/util/HashSet;", "setCnWakeupList", "(Ljava/util/HashSet;)V", "enAceWakeupWorkAssetName", "getEnAceWakeupWorkAssetName", "setEnAceWakeupWorkAssetName", "enWakeupList", "getEnWakeupList", "setEnWakeupList", "googleDialogflowConfigKey", "getGoogleDialogflowConfigKey", "setGoogleDialogflowConfigKey", "isContinuous", "", "()Z", "setContinuous", "(Z)V", "mapName", "getMapName", "setMapName", "productType", "getProductType", "setProductType", "recodeType", "getRecodeType", "setRecodeType", "scene", "getScene", "setScene", "shopId", "getShopId", "setShopId", "tinymixCmd", "getTinymixCmd", "setTinymixCmd", "ttsVolume", "getTtsVolume", "setTtsVolume", "useLessWordList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getUseLessWordList", "()Ljava/util/ArrayList;", "setUseLessWordList", "(Ljava/util/ArrayList;)V", "useWordList", "getUseWordList", "setUseWordList", "userInfo", "Lcom/pudutech/voiceinteraction/component/config/UserInfo;", "getUserInfo", "()Lcom/pudutech/voiceinteraction/component/config/UserInfo;", "setUserInfo", "(Lcom/pudutech/voiceinteraction/component/config/UserInfo;)V", "vadBosTimeout", "getVadBosTimeout", "setVadBosTimeout", "vadEosTimeout", "getVadEosTimeout", "setVadEosTimeout", "wakeResIsCopyFinisd", "getWakeResIsCopyFinisd", "setWakeResIsCopyFinisd", "wokerMode", "Lcom/pudutech/voiceinteraction/component/cmd/WorkMode;", "getWokerMode", "()Lcom/pudutech/voiceinteraction/component/cmd/WorkMode;", "setWokerMode", "(Lcom/pudutech/voiceinteraction/component/cmd/WorkMode;)V", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class VoiceCommentConfig {
    private static boolean isContinuous;
    private static UserInfo userInfo;
    private static boolean wakeResIsCopyFinisd;
    public static final VoiceCommentConfig INSTANCE = new VoiceCommentConfig();
    private static int audioCard = 1;
    private static String scene = "main_box";
    private static int recodeType = 2;
    private static String cnAceWakeupWorkAssetName = "chinese_wakeup_words.bin";
    private static String enAceWakeupWorkAssetName = "english_wakeup_words.bin";
    private static String googleDialogflowConfigKey = "";
    private static int vadBosTimeout = 10000;
    private static int vadEosTimeout = 2000;
    private static int ttsVolume = 50;
    private static String tinymixCmd = "tinymix -D 1 3 25 25 25 25 25 25";
    private static String mapName = "";
    private static String productType = "";
    private static WorkMode wokerMode = WorkMode.Delivery;
    private static HashSet<String> cnWakeupList = new HashSet<>(CollectionsKt.arrayListOf("hey4-pu3-du4", "hi-pu3-du4"));
    private static HashSet<String> enWakeupList = new HashSet<>(CollectionsKt.arrayListOf("hey4-pu3-du4", "hi-pu3-du4"));
    private static ArrayList<String> useLessWordList = CollectionsKt.arrayListOf("");
    private static ArrayList<String> useWordList = CollectionsKt.arrayListOf("");
    private static String shopId = "";

    private VoiceCommentConfig() {
    }

    public final int getAudioCard() {
        return audioCard;
    }

    public final void setAudioCard(int i) {
        audioCard = i;
    }

    public final UserInfo getUserInfo() {
        return userInfo;
    }

    public final void setUserInfo(UserInfo userInfo2) {
        userInfo = userInfo2;
    }

    public final String getScene() {
        return scene;
    }

    public final void setScene(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        scene = str;
    }

    public final int getRecodeType() {
        return recodeType;
    }

    public final void setRecodeType(int i) {
        recodeType = i;
    }

    public final String getCnAceWakeupWorkAssetName() {
        return cnAceWakeupWorkAssetName;
    }

    public final void setCnAceWakeupWorkAssetName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        cnAceWakeupWorkAssetName = str;
    }

    public final String getEnAceWakeupWorkAssetName() {
        return enAceWakeupWorkAssetName;
    }

    public final void setEnAceWakeupWorkAssetName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        enAceWakeupWorkAssetName = str;
    }

    public final String getGoogleDialogflowConfigKey() {
        return googleDialogflowConfigKey;
    }

    public final void setGoogleDialogflowConfigKey(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        googleDialogflowConfigKey = str;
    }

    public final int getVadBosTimeout() {
        return vadBosTimeout;
    }

    public final void setVadBosTimeout(int i) {
        vadBosTimeout = i;
    }

    public final int getVadEosTimeout() {
        return vadEosTimeout;
    }

    public final void setVadEosTimeout(int i) {
        vadEosTimeout = i;
    }

    public final int getTtsVolume() {
        return ttsVolume;
    }

    public final void setTtsVolume(int i) {
        ttsVolume = i;
    }

    public final boolean isContinuous() {
        return isContinuous;
    }

    public final void setContinuous(boolean z) {
        isContinuous = z;
    }

    public final String getTinymixCmd() {
        return tinymixCmd;
    }

    public final void setTinymixCmd(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        tinymixCmd = str;
    }

    public final String getMapName() {
        return mapName;
    }

    public final void setMapName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        mapName = str;
    }

    public final String getProductType() {
        return productType;
    }

    public final void setProductType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        productType = str;
    }

    public final WorkMode getWokerMode() {
        return wokerMode;
    }

    public final void setWokerMode(WorkMode workMode) {
        Intrinsics.checkParameterIsNotNull(workMode, "<set-?>");
        wokerMode = workMode;
    }

    public final HashSet<String> getCnWakeupList() {
        return cnWakeupList;
    }

    public final void setCnWakeupList(HashSet<String> hashSet) {
        Intrinsics.checkParameterIsNotNull(hashSet, "<set-?>");
        cnWakeupList = hashSet;
    }

    public final HashSet<String> getEnWakeupList() {
        return enWakeupList;
    }

    public final void setEnWakeupList(HashSet<String> hashSet) {
        Intrinsics.checkParameterIsNotNull(hashSet, "<set-?>");
        enWakeupList = hashSet;
    }

    public final ArrayList<String> getUseLessWordList() {
        return useLessWordList;
    }

    public final void setUseLessWordList(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        useLessWordList = arrayList;
    }

    public final ArrayList<String> getUseWordList() {
        return useWordList;
    }

    public final void setUseWordList(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        useWordList = arrayList;
    }

    public final String getShopId() {
        return shopId;
    }

    public final void setShopId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        shopId = str;
    }

    public final boolean getWakeResIsCopyFinisd() {
        return wakeResIsCopyFinisd;
    }

    public final void setWakeResIsCopyFinisd(boolean z) {
        wakeResIsCopyFinisd = z;
    }
}
