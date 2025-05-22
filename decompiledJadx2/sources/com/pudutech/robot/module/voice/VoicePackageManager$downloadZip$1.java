package com.pudutech.robot.module.voice;

import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.response.VoicePackCloud;
import com.pudutech.light_network.download.DownLoadObserver;
import com.pudutech.light_network.download.DownloadInfo;
import com.pudutech.robot.module.voice.VoicePackageManager;
import com.pudutech.robot.module.voice.pkg.VoicePackageHelper;
import com.pudutech.robot.module.voice.pkg.VoicePackageInfo;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.apache.commons.io.FilenameUtils;

/* compiled from: VoicePackageManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/robot/module/voice/VoicePackageManager$downloadZip$1", "Lcom/pudutech/light_network/download/DownLoadObserver;", "onComplete", "", "onError", C3898x.f4338g, "", "onNext", "downloadInfo", "Lcom/pudutech/light_network/download/DownloadInfo;", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoicePackageManager$downloadZip$1 extends DownLoadObserver {
    final /* synthetic */ Function1 $cb;
    final /* synthetic */ VoicePackageInfo $packInfo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoicePackageManager$downloadZip$1(VoicePackageInfo voicePackageInfo, Function1 function1) {
        this.$packInfo = voicePackageInfo;
        this.$cb = function1;
    }

    @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
    public void onComplete() {
        String str;
        HashMap hashMap;
        VoicePackageHelper voicePackageHelper;
        VoicePackageInfo voicePackageInfo;
        VoicePackageHelper voicePackageHelper2;
        VoicePackageHelper voicePackageHelper3;
        VoicePackageHelper voicePackageHelper4;
        VoicePackageHelper voicePackageHelper5;
        List<VoicePackageInfo> list;
        super.onComplete();
        DownloadInfo downloadInfo = this.downloadInfo;
        Intrinsics.checkExpressionValueIsNotNull(downloadInfo, "downloadInfo");
        String fileName = downloadInfo.getFileName();
        VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
        str = VoicePackageManager.TAG;
        Pdlog.m3275i(str, "download complete. " + this.$packInfo.getId() + ' ' + fileName + FilenameUtils.EXTENSION_SEPARATOR);
        this.$packInfo.setDownloadProgress$module_robot_voice_release(0);
        this.$packInfo.setDownloading$module_robot_voice_release(false);
        VoicePackageManager.DownloadResult downloadResult = VoicePackageManager.DownloadResult.FAIL;
        VoicePackageManager voicePackageManager2 = VoicePackageManager.INSTANCE;
        hashMap = VoicePackageManager.cloudLib;
        VoicePackCloud it = (VoicePackCloud) hashMap.get(Long.valueOf(this.$packInfo.getId()));
        if (it != null) {
            VoicePackageManager voicePackageManager3 = VoicePackageManager.INSTANCE;
            voicePackageHelper3 = VoicePackageManager.packageHelper;
            Intrinsics.checkExpressionValueIsNotNull(fileName, "fileName");
            String md5 = it.getMd5();
            if (md5 == null) {
                md5 = "";
            }
            if (voicePackageHelper3.checkMD5Same(fileName, md5)) {
                VoicePackageManager voicePackageManager4 = VoicePackageManager.INSTANCE;
                voicePackageHelper4 = VoicePackageManager.packageHelper;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (voicePackageHelper4.moveDownloadsToPackage(fileName, it)) {
                    this.$packInfo.setExist$module_robot_voice_release(true);
                    this.$packInfo.setNewVersionAvailable$module_robot_voice_release(false);
                    VoicePackageInfo voicePackageInfo2 = this.$packInfo;
                    Long version_code = it.getVersion_code();
                    voicePackageInfo2.setVersion_code$module_robot_voice_release(version_code != null ? version_code.longValue() : 0L);
                    VoicePackageManager voicePackageManager5 = VoicePackageManager.INSTANCE;
                    voicePackageHelper5 = VoicePackageManager.packageHelper;
                    VoicePackageManager voicePackageManager6 = VoicePackageManager.INSTANCE;
                    list = VoicePackageManager.allPackages;
                    voicePackageHelper5.syncRecordToSDCard(list);
                    downloadResult = VoicePackageManager.DownloadResult.SUCCESS;
                }
            }
        }
        String valueOf = String.valueOf(this.$packInfo.getId());
        VoicePackageManager voicePackageManager7 = VoicePackageManager.INSTANCE;
        voicePackageHelper = VoicePackageManager.packageHelper;
        if (Intrinsics.areEqual(valueOf, voicePackageHelper.getSelectedIDRecord())) {
            new VoiceDataSourceHelper().loadCustomVoiceToMemory$module_robot_voice_release();
        }
        if (downloadResult == VoicePackageManager.DownloadResult.SUCCESS) {
            VoicePackageManager voicePackageManager8 = VoicePackageManager.INSTANCE;
            voicePackageInfo = VoicePackageManager.selectedOne;
            if (Intrinsics.areEqual(voicePackageInfo, this.$packInfo)) {
                this.$packInfo.setSelected$module_robot_voice_release(true);
                VoicePackageManager voicePackageManager9 = VoicePackageManager.INSTANCE;
                voicePackageHelper2 = VoicePackageManager.packageHelper;
                voicePackageHelper2.setSelectedIDRecord(String.valueOf(this.$packInfo.getId()));
                new VoiceDataSourceHelper().loadCustomVoiceToMemory$module_robot_voice_release();
            }
        }
        VoicePackageManager.INSTANCE.notifyDownload(new VoicePackageManager.DownloadVoice(downloadResult, this.$packInfo));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new VoicePackageManager$downloadZip$1$onComplete$2(this, null), 2, null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
    public void onNext(DownloadInfo downloadInfo) {
        String str;
        Intrinsics.checkParameterIsNotNull(downloadInfo, "downloadInfo");
        super.onNext(downloadInfo);
        int progress = (int) ((downloadInfo.getProgress() * 100) / downloadInfo.getTotal());
        if (this.$packInfo.getDownloadProgress() != progress) {
            VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
            str = VoicePackageManager.TAG;
            Pdlog.m3275i(str, "download " + downloadInfo.getFileName() + ' ' + progress);
        }
        if (progress == 100) {
            progress = 99;
        }
        this.$packInfo.setDownloadProgress$module_robot_voice_release(progress);
        VoicePackageManager.INSTANCE.notifyDownload(new VoicePackageManager.DownloadVoice(VoicePackageManager.DownloadResult.DOWNLOADING, this.$packInfo));
    }

    @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
    public void onError(Throwable e) {
        String str;
        Intrinsics.checkParameterIsNotNull(e, "e");
        super.onError(e);
        VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
        str = VoicePackageManager.TAG;
        Pdlog.m3274e(str, "download " + this.$packInfo.getName() + " error. " + e);
        this.$packInfo.setDownloadProgress$module_robot_voice_release(0);
        this.$packInfo.setDownloading$module_robot_voice_release(false);
        VoicePackageManager.INSTANCE.notifyDownload(new VoicePackageManager.DownloadVoice(VoicePackageManager.DownloadResult.FAIL, this.$packInfo));
    }
}
