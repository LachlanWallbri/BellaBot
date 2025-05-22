package com.pudutech.robot.module.voice;

import android.content.Context;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.response.VoicePackCloud;
import com.pudutech.light_network.download.DownloadManager;
import com.pudutech.robot.module.voice.pkg.VoicePackageHelper;
import com.pudutech.robot.module.voice.pkg.VoicePackageInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: VoicePackageManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u00041234B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0007H\u0002J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0007JA\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u000721\u0010\u001e\u001a-\u0012#\u0012!\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00170\u001fJC\u0010\"\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u000723\u0010\u001e\u001a/\u0012#\u0012!\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001fJM\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u000723\u0010\u001e\u001a/\u0012#\u0012!\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001fH\u0002J9\u0010&\u001a\u00020\u001721\u0010\u001e\u001a-\u0012#\u0012!\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00170\u001fJ\u000e\u0010'\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0002J\u000e\u0010+\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011JE\u0010,\u001a\u00020\u00172\b\u0010-\u001a\u0004\u0018\u00010\u000723\u0010\u001e\u001a/\u0012#\u0012!\u0012\u0004\u0012\u00020\u00070\u0010j\b\u0012\u0004\u0012\u00020\u0007`\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001fJ)\u0010.\u001a\u00020\u00172!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110/¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00170\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/VoicePackageManager;", "", "()V", "TAG", "", "allPackages", "", "Lcom/pudutech/robot/module/voice/pkg/VoicePackageInfo;", "cloudLib", "Ljava/util/HashMap;", "", "Lcom/pudutech/disinfect/baselib/network/response/VoicePackCloud;", "Lkotlin/collections/HashMap;", "context", "Landroid/content/Context;", "downloadListener", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/voice/VoicePackageManager$OnDownloadListener;", "Lkotlin/collections/ArrayList;", "packageHelper", "Lcom/pudutech/robot/module/voice/pkg/VoicePackageHelper;", "selectedOne", "addDownloadListener", "", "l", "addToAll", "pack", "cancelDownload", "theOne", RequestParameters.SUBRESOURCE_DELETE, "cb", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "download", "downloadZip", "url", "packInfo", "getLocalVoicePkgList", "init", "notifyDownload", "v", "Lcom/pudutech/robot/module/voice/VoicePackageManager$DownloadVoice;", "removeDownloadListener", "selectPkg", "voice", "updateVoicePkgList", "Lcom/pudutech/robot/module/voice/VoicePackageManager$UpdateResult;", "r", "DownloadResult", "DownloadVoice", "OnDownloadListener", "UpdateResult", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoicePackageManager {
    private static Context context;
    private static VoicePackageInfo selectedOne;
    public static final VoicePackageManager INSTANCE = new VoicePackageManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static VoicePackageHelper packageHelper = new VoicePackageHelper();
    private static final List<VoicePackageInfo> allPackages = new ArrayList();
    private static final HashMap<Long, VoicePackCloud> cloudLib = new HashMap<>();
    private static final ArrayList<OnDownloadListener> downloadListener = new ArrayList<>();

    /* compiled from: VoicePackageManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/VoicePackageManager$DownloadResult;", "", "(Ljava/lang/String;I)V", "SUCCESS", "FAIL", "DOWNLOADING", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum DownloadResult {
        SUCCESS,
        FAIL,
        DOWNLOADING
    }

    /* compiled from: VoicePackageManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/VoicePackageManager$OnDownloadListener;", "", "onLoad", "", "v", "Lcom/pudutech/robot/module/voice/VoicePackageManager$DownloadVoice;", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnDownloadListener {
        void onLoad(DownloadVoice v);
    }

    private VoicePackageManager() {
    }

    public final void init(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Pdlog.m3273d(TAG, "init : context = " + context2 + "; ");
        context = context2;
        VoicePackageHelper.INSTANCE.init(context2);
    }

    public final void getLocalVoicePkgList(Function1<? super ArrayList<VoicePackageInfo>, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoicePackageManager$getLocalVoicePkgList$1(cb, null), 3, null);
    }

    public final void updateVoicePkgList(Function1<? super UpdateResult, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoicePackageManager$updateVoicePkgList$1(cb, null), 3, null);
    }

    public final void selectPkg(VoicePackageInfo voice, Function1<? super ArrayList<VoicePackageInfo>, Unit> cb) {
        Pdlog.m3273d(TAG, "selectPkg ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoicePackageManager$selectPkg$1(voice, cb, null), 3, null);
    }

    public final void download(VoicePackageInfo theOne, Function1<? super ArrayList<VoicePackageInfo>, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(theOne, "theOne");
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("download ");
        sb.append(theOne.getName());
        sb.append(" url:");
        VoicePackCloud voicePackCloud = cloudLib.get(Long.valueOf(theOne.getId()));
        sb.append(voicePackCloud != null ? voicePackCloud.getUrl() : null);
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoicePackageManager$download$1(theOne, cb, null), 3, null);
    }

    public final void cancelDownload(VoicePackageInfo theOne) {
        Intrinsics.checkParameterIsNotNull(theOne, "theOne");
        Pdlog.m3275i(TAG, "cancel " + theOne.getName());
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoicePackageManager$cancelDownload$1(theOne, null), 3, null);
    }

    public final void delete(VoicePackageInfo theOne, Function1<? super ArrayList<VoicePackageInfo>, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(theOne, "theOne");
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        Pdlog.m3275i(TAG, "delete " + theOne.getId());
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new VoicePackageManager$delete$1(theOne, cb, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyDownload(DownloadVoice v) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new VoicePackageManager$notifyDownload$1(v, null), 2, null);
    }

    public final void addDownloadListener(OnDownloadListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (downloadListener.contains(l)) {
            return;
        }
        downloadListener.add(l);
    }

    public final void removeDownloadListener(OnDownloadListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        downloadListener.remove(l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downloadZip(String url, VoicePackageInfo packInfo, Function1<? super ArrayList<VoicePackageInfo>, Unit> cb) {
        packInfo.setDownloading$module_robot_voice_release(true);
        notifyDownload(new DownloadVoice(DownloadResult.DOWNLOADING, packInfo));
        DownloadManager.getInstance().download(VoicePackageHelper.INSTANCE.getDownloadPath(), url, new VoicePackageManager$downloadZip$1(packInfo, cb));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addToAll(VoicePackageInfo pack) {
        Pdlog.m3275i(TAG, "add to all. " + pack.getName());
        for (VoicePackageInfo voicePackageInfo : allPackages) {
            if (voicePackageInfo.getId() == pack.getId()) {
                Pdlog.m3275i(TAG, "exist. id=" + pack.getId());
                if (!Intrinsics.areEqual(voicePackageInfo.getName(), pack.getName())) {
                    Pdlog.m3275i(TAG, "rename " + voicePackageInfo.getName() + " to " + pack.getName());
                    voicePackageInfo.setName$module_robot_voice_release(pack.getName());
                    if (voicePackageInfo.getSelected()) {
                        packageHelper.setSelectedIDRecord(String.valueOf(voicePackageInfo.getId()));
                    }
                }
                if (voicePackageInfo.getVersion_code() < pack.getVersion_code()) {
                    Pdlog.m3275i(TAG, "new version available. old=" + voicePackageInfo.getVersion_code() + " new=" + pack.getVersion_code());
                    voicePackageInfo.setNewVersionAvailable$module_robot_voice_release(true);
                    return;
                }
                return;
            }
        }
        allPackages.add(pack);
    }

    /* compiled from: VoicePackageManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0003J1\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R%\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/VoicePackageManager$UpdateResult;", "", "success", "", "l", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/voice/pkg/VoicePackageInfo;", "Lkotlin/collections/ArrayList;", "(ZLjava/util/ArrayList;)V", "getL", "()Ljava/util/ArrayList;", "getSuccess", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class UpdateResult {
        private final ArrayList<VoicePackageInfo> l;
        private final boolean success;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ UpdateResult copy$default(UpdateResult updateResult, boolean z, ArrayList arrayList, int i, Object obj) {
            if ((i & 1) != 0) {
                z = updateResult.success;
            }
            if ((i & 2) != 0) {
                arrayList = updateResult.l;
            }
            return updateResult.copy(z, arrayList);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        public final ArrayList<VoicePackageInfo> component2() {
            return this.l;
        }

        public final UpdateResult copy(boolean success, ArrayList<VoicePackageInfo> l) {
            return new UpdateResult(success, l);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UpdateResult)) {
                return false;
            }
            UpdateResult updateResult = (UpdateResult) other;
            return this.success == updateResult.success && Intrinsics.areEqual(this.l, updateResult.l);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.success;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            ArrayList<VoicePackageInfo> arrayList = this.l;
            return i + (arrayList != null ? arrayList.hashCode() : 0);
        }

        public String toString() {
            return "UpdateResult(success=" + this.success + ", l=" + this.l + ")";
        }

        public UpdateResult(boolean z, ArrayList<VoicePackageInfo> arrayList) {
            this.success = z;
            this.l = arrayList;
        }

        public final ArrayList<VoicePackageInfo> getL() {
            return this.l;
        }

        public final boolean getSuccess() {
            return this.success;
        }
    }

    /* compiled from: VoicePackageManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/VoicePackageManager$DownloadVoice;", "", SpeechUtility.TAG_RESOURCE_RESULT, "Lcom/pudutech/robot/module/voice/VoicePackageManager$DownloadResult;", "info", "Lcom/pudutech/robot/module/voice/pkg/VoicePackageInfo;", "(Lcom/pudutech/robot/module/voice/VoicePackageManager$DownloadResult;Lcom/pudutech/robot/module/voice/pkg/VoicePackageInfo;)V", "getInfo", "()Lcom/pudutech/robot/module/voice/pkg/VoicePackageInfo;", "getResult", "()Lcom/pudutech/robot/module/voice/VoicePackageManager$DownloadResult;", "setResult", "(Lcom/pudutech/robot/module/voice/VoicePackageManager$DownloadResult;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class DownloadVoice {
        private final VoicePackageInfo info;
        private DownloadResult result;

        public static /* synthetic */ DownloadVoice copy$default(DownloadVoice downloadVoice, DownloadResult downloadResult, VoicePackageInfo voicePackageInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                downloadResult = downloadVoice.result;
            }
            if ((i & 2) != 0) {
                voicePackageInfo = downloadVoice.info;
            }
            return downloadVoice.copy(downloadResult, voicePackageInfo);
        }

        /* renamed from: component1, reason: from getter */
        public final DownloadResult getResult() {
            return this.result;
        }

        /* renamed from: component2, reason: from getter */
        public final VoicePackageInfo getInfo() {
            return this.info;
        }

        public final DownloadVoice copy(DownloadResult result, VoicePackageInfo info) {
            Intrinsics.checkParameterIsNotNull(result, "result");
            Intrinsics.checkParameterIsNotNull(info, "info");
            return new DownloadVoice(result, info);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadVoice)) {
                return false;
            }
            DownloadVoice downloadVoice = (DownloadVoice) other;
            return Intrinsics.areEqual(this.result, downloadVoice.result) && Intrinsics.areEqual(this.info, downloadVoice.info);
        }

        public int hashCode() {
            DownloadResult downloadResult = this.result;
            int hashCode = (downloadResult != null ? downloadResult.hashCode() : 0) * 31;
            VoicePackageInfo voicePackageInfo = this.info;
            return hashCode + (voicePackageInfo != null ? voicePackageInfo.hashCode() : 0);
        }

        public String toString() {
            return "DownloadVoice(result=" + this.result + ", info=" + this.info + ")";
        }

        public DownloadVoice(DownloadResult result, VoicePackageInfo info) {
            Intrinsics.checkParameterIsNotNull(result, "result");
            Intrinsics.checkParameterIsNotNull(info, "info");
            this.result = result;
            this.info = info;
        }

        public final VoicePackageInfo getInfo() {
            return this.info;
        }

        public final DownloadResult getResult() {
            return this.result;
        }

        public final void setResult(DownloadResult downloadResult) {
            Intrinsics.checkParameterIsNotNull(downloadResult, "<set-?>");
            this.result = downloadResult;
        }
    }
}
