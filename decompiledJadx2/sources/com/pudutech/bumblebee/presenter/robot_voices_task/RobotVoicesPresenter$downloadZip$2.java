package com.pudutech.bumblebee.presenter.robot_voices_task;

import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayerHelper;
import com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract;
import com.pudutech.disinfect.baselib.network.response.VoicePackCloud;
import com.pudutech.light_network.download.DownLoadObserver;
import com.pudutech.light_network.download.DownloadInfo;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.apache.commons.io.FilenameUtils;

/* compiled from: RobotVoicesPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesPresenter$downloadZip$2", "Lcom/pudutech/light_network/download/DownLoadObserver;", "onComplete", "", "onError", C3898x.f4338g, "", "onNext", "downloadInfo", "Lcom/pudutech/light_network/download/DownloadInfo;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotVoicesPresenter$downloadZip$2 extends DownLoadObserver {
    final /* synthetic */ VoicePackageInfo $packInfo;
    final /* synthetic */ RobotVoicesPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RobotVoicesPresenter$downloadZip$2(RobotVoicesPresenter robotVoicesPresenter, VoicePackageInfo voicePackageInfo) {
        this.this$0 = robotVoicesPresenter;
        this.$packInfo = voicePackageInfo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v24, types: [T, com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract$DownloadResult] */
    /* JADX WARN: Type inference failed for: r3v1, types: [T, com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract$DownloadResult] */
    @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
    public void onComplete() {
        HashMap hashMap;
        VoicePackageHelper voicePackageHelper;
        VoicePackageInfo voicePackageInfo;
        VoicePackageHelper voicePackageHelper2;
        VoicePackageHelper voicePackageHelper3;
        VoicePackageHelper voicePackageHelper4;
        VoicePackageHelper voicePackageHelper5;
        super.onComplete();
        if (this.downloadInfo == null) {
            onError(new Throwable("downloadInfo is null"));
            return;
        }
        DownloadInfo downloadInfo = this.downloadInfo;
        Intrinsics.checkExpressionValueIsNotNull(downloadInfo, "downloadInfo");
        String fileName = downloadInfo.getFileName();
        Pdlog.m3275i(this.this$0.getTAG(), "download complete. " + this.$packInfo.getId() + ' ' + fileName + FilenameUtils.EXTENSION_SEPARATOR);
        this.$packInfo.setDownloadProgress(0);
        this.$packInfo.setDownloading(false);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = RobotVoicesContract.DownloadResult.FAIL;
        hashMap = this.this$0.cloudLib;
        VoicePackCloud it = (VoicePackCloud) hashMap.get(Long.valueOf(this.$packInfo.getId()));
        if (it != null) {
            voicePackageHelper3 = this.this$0.packageHelper;
            Intrinsics.checkExpressionValueIsNotNull(fileName, "fileName");
            if (voicePackageHelper3.checkMD5Same(fileName, it.getMd5())) {
                voicePackageHelper4 = this.this$0.packageHelper;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (voicePackageHelper4.moveDownloadsToPackage(fileName, it)) {
                    this.$packInfo.setExist(true);
                    this.$packInfo.setNewVersionAvailable(false);
                    this.$packInfo.setVersion_code(it.getVersion_code());
                    voicePackageHelper5 = this.this$0.packageHelper;
                    voicePackageHelper5.syncRecordToSDCard(this.this$0.getAllPackages());
                    objectRef.element = RobotVoicesContract.DownloadResult.SUCCESS;
                }
            }
        }
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$downloadZip$2$onComplete$2
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

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                RobotVoicesContract.ViewInterface theView;
                theView = RobotVoicesPresenter$downloadZip$2.this.this$0.getTheView();
                if (theView != null) {
                    theView.showDownloadProgress((RobotVoicesContract.DownloadResult) objectRef.element, RobotVoicesPresenter$downloadZip$2.this.$packInfo);
                }
            }
        });
        String valueOf = String.valueOf(this.$packInfo.getId());
        voicePackageHelper = this.this$0.packageHelper;
        if (Intrinsics.areEqual(valueOf, voicePackageHelper.getSelectedIDRecord())) {
            new VoicePlayerHelper().loadCustomVoiceToMemory();
        }
        if (((RobotVoicesContract.DownloadResult) objectRef.element) == RobotVoicesContract.DownloadResult.SUCCESS) {
            voicePackageInfo = this.this$0.selectedOne;
            if (Intrinsics.areEqual(voicePackageInfo, this.$packInfo)) {
                this.$packInfo.setSelected(true);
                voicePackageHelper2 = this.this$0.packageHelper;
                voicePackageHelper2.setSelectedIDRecord(String.valueOf(this.$packInfo.getId()));
                new VoicePlayerHelper().loadCustomVoiceToMemory();
                this.this$0.showListOnUI();
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
    public void onNext(DownloadInfo downloadInfo) {
        Intrinsics.checkParameterIsNotNull(downloadInfo, "downloadInfo");
        super.onNext(downloadInfo);
        int progress = (int) ((downloadInfo.getProgress() * 100) / downloadInfo.getTotal());
        if (this.$packInfo.getDownloadProgress() != progress) {
            Pdlog.m3275i(this.this$0.getTAG(), "download " + downloadInfo.getFileName() + ' ' + progress);
        }
        if (progress == 100) {
            progress = 99;
        }
        this.$packInfo.setDownloadProgress(progress);
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$downloadZip$2$onNext$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                RobotVoicesContract.ViewInterface theView;
                theView = RobotVoicesPresenter$downloadZip$2.this.this$0.getTheView();
                if (theView != null) {
                    theView.showDownloadProgress(RobotVoicesContract.DownloadResult.DOWNLOADING, RobotVoicesPresenter$downloadZip$2.this.$packInfo);
                }
            }
        });
    }

    @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
    public void onError(Throwable e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        super.onError(e);
        Pdlog.m3274e(this.this$0.getTAG(), "download " + this.$packInfo.getName() + " error. " + e);
        this.$packInfo.setDownloadProgress(0);
        this.$packInfo.setDownloading(false);
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$downloadZip$2$onError$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                RobotVoicesContract.ViewInterface theView;
                theView = RobotVoicesPresenter$downloadZip$2.this.this$0.getTheView();
                if (theView != null) {
                    theView.showDownloadProgress(RobotVoicesContract.DownloadResult.FAIL, RobotVoicesPresenter$downloadZip$2.this.$packInfo);
                }
            }
        });
    }
}
