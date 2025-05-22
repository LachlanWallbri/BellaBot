package com.pudutech.bumblebee.presenter.robot_voices_task;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayerHelper;
import com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract;
import com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt;
import com.pudutech.disinfect.baselib.network.req.VoicePackInfoReq;
import com.pudutech.disinfect.baselib.network.response.VoicePackCloud;
import com.pudutech.disinfect.baselib.network.response.VoicePackCloudResponse;
import com.pudutech.disinfect.baselib.state.AppException;
import com.pudutech.light_network.download.DownloadManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: RobotVoicesPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000bH\u0002J\u0010\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u000bH\u0016J\u0010\u0010!\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u000bH\u0016J\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u000bH\u0016J\u0018\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u000bH\u0002J\u0010\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u0002H\u0016J\u0010\u0010(\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u0002H\u0016J\u0012\u0010)\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010*\u001a\u00020\u001dH\u0002J\b\u0010+\u001a\u00020\u001dH\u0003J\b\u0010,\u001a\u00020\u001dH\u0016J\u0006\u0010-\u001a\u00020\u001dR\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "allPackages", "", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "getAllPackages", "()Ljava/util/List;", "setAllPackages", "(Ljava/util/List;)V", "cloudLib", "Ljava/util/HashMap;", "", "Lcom/pudutech/disinfect/baselib/network/response/VoicePackCloud;", "Lkotlin/collections/HashMap;", "mCurJob", "Lkotlinx/coroutines/Job;", "mScope", "Lkotlinx/coroutines/CoroutineScope;", "packageHelper", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageHelper;", "selectedOne", "addToAll", "", "pack", "cancelDownload", "theOne", RequestParameters.SUBRESOURCE_DELETE, "download", "downloadZip", "url", "packInfo", "removeView", "view", "replaceView", "select", "showListOnUI", "syncCloud", "syncList", "syncLoadLocalVoice", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotVoicesPresenter extends BaseOneViewPresenter<RobotVoicesContract.ViewInterface> implements RobotVoicesContract.PresenterInterface {
    private Job mCurJob;
    private CoroutineScope mScope;
    private VoicePackageInfo selectedOne;
    private final String TAG = "RobotVoicesPresenter";
    private List<VoicePackageInfo> allPackages = new ArrayList();
    private VoicePackageHelper packageHelper = new VoicePackageHelper();
    private final HashMap<Long, VoicePackCloud> cloudLib = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract.PresenterInterface
    public List<VoicePackageInfo> getAllPackages() {
        return this.allPackages;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract.PresenterInterface
    public void setAllPackages(List<VoicePackageInfo> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.allPackages = list;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract.PresenterInterface
    public void syncList() {
        Pdlog.m3275i(getTAG(), "syncList");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$syncList$1
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
                VoicePackageHelper voicePackageHelper;
                VoicePackageHelper voicePackageHelper2;
                VoicePackageHelper voicePackageHelper3;
                VoicePackageHelper voicePackageHelper4;
                voicePackageHelper = RobotVoicesPresenter.this.packageHelper;
                voicePackageHelper.cleanDownloads();
                RobotVoicesPresenter.this.getAllPackages().clear();
                List<VoicePackageInfo> allPackages = RobotVoicesPresenter.this.getAllPackages();
                voicePackageHelper2 = RobotVoicesPresenter.this.packageHelper;
                allPackages.add(voicePackageHelper2.defaultPackage());
                voicePackageHelper3 = RobotVoicesPresenter.this.packageHelper;
                voicePackageHelper3.loadRecord();
                voicePackageHelper4 = RobotVoicesPresenter.this.packageHelper;
                RobotVoicesPresenter.this.getAllPackages().addAll(voicePackageHelper4.getDownloadList());
                RobotVoicesPresenter.this.showListOnUI();
                RobotVoicesPresenter.this.syncCloud();
            }
        });
    }

    public final void syncLoadLocalVoice() {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$syncLoadLocalVoice$1
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
                VoicePackageHelper voicePackageHelper;
                VoicePackageHelper voicePackageHelper2;
                VoicePackageHelper voicePackageHelper3;
                RobotVoicesPresenter.this.getAllPackages().clear();
                List<VoicePackageInfo> allPackages = RobotVoicesPresenter.this.getAllPackages();
                voicePackageHelper = RobotVoicesPresenter.this.packageHelper;
                allPackages.add(voicePackageHelper.defaultPackage());
                voicePackageHelper2 = RobotVoicesPresenter.this.packageHelper;
                voicePackageHelper2.loadRecord();
                voicePackageHelper3 = RobotVoicesPresenter.this.packageHelper;
                RobotVoicesPresenter.this.getAllPackages().addAll(voicePackageHelper3.getDownloadList());
                RobotVoicesPresenter.this.showListOnUI();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.pudutech.disinfect.baselib.network.req.VoicePackInfoReq] */
    public final void syncCloud() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new VoicePackInfoReq();
        CoroutineScope coroutineScope = this.mScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mScope");
        }
        this.mCurJob = BaseViewModelExtKt.requestNetworkData$default(coroutineScope, new RobotVoicesPresenter$syncCloud$1(objectRef, null), new Function1<VoicePackCloudResponse, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$syncCloud$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VoicePackCloudResponse voicePackCloudResponse) {
                invoke2(voicePackCloudResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VoicePackCloudResponse voicePackCloudResponse) {
                VoicePackageHelper voicePackageHelper;
                HashMap hashMap;
                Intrinsics.checkParameterIsNotNull(voicePackCloudResponse, "voicePackCloudResponse");
                String tag = RobotVoicesPresenter.this.getTAG();
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("pack size=");
                ArrayList<VoicePackCloud> list = voicePackCloudResponse.getList();
                sb.append((list != null ? Integer.valueOf(list.size()) : null).intValue());
                objArr[0] = sb.toString();
                Pdlog.m3275i(tag, objArr);
                ArrayList<VoicePackCloud> list2 = voicePackCloudResponse.getList();
                if (list2 != null) {
                    for (VoicePackCloud voicePackCloud : list2) {
                        Pdlog.m3275i(RobotVoicesPresenter.this.getTAG(), "id=" + voicePackCloud.getId() + " name=" + voicePackCloud.getName() + " version=" + voicePackCloud.getVersion_code());
                        hashMap = RobotVoicesPresenter.this.cloudLib;
                        hashMap.put(Long.valueOf(voicePackCloud.getId()), voicePackCloud);
                        VoicePackageInfo voicePackageInfo = new VoicePackageInfo();
                        voicePackageInfo.setName(voicePackCloud.getName());
                        voicePackageInfo.setId(voicePackCloud.getId());
                        voicePackageInfo.setVersion_code(voicePackCloud.getVersion_code());
                        RobotVoicesPresenter.this.addToAll(voicePackageInfo);
                    }
                }
                voicePackageHelper = RobotVoicesPresenter.this.packageHelper;
                voicePackageHelper.syncRecordToSDCard(RobotVoicesPresenter.this.getAllPackages());
                RobotVoicesPresenter.this.showListOnUI();
            }
        }, new Function1<AppException, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$syncCloud$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                invoke2(appException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final AppException it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                RobotVoicesPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$syncCloud$3.1
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
                        RobotVoicesContract.ViewInterface theView;
                        theView = RobotVoicesPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showCouldSyncResult(false, String.valueOf(it), -1);
                        }
                    }
                });
            }
        }, null, 16, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addToAll(VoicePackageInfo pack) {
        Pdlog.m3275i(getTAG(), "add to all. " + pack.getName());
        for (VoicePackageInfo voicePackageInfo : getAllPackages()) {
            if (voicePackageInfo.getId() == pack.getId()) {
                Pdlog.m3275i(getTAG(), "exist. id=" + pack.getId());
                if (!Intrinsics.areEqual(voicePackageInfo.getName(), pack.getName())) {
                    Pdlog.m3275i(getTAG(), "rename " + voicePackageInfo.getName() + " to " + pack.getName());
                    voicePackageInfo.setName(pack.getName());
                    if (voicePackageInfo.getSelected()) {
                        this.packageHelper.setSelectedIDRecord(String.valueOf(voicePackageInfo.getId()));
                    }
                }
                if (voicePackageInfo.getVersion_code() < pack.getVersion_code()) {
                    Pdlog.m3275i(getTAG(), "new version available. old=" + voicePackageInfo.getVersion_code() + " new=" + pack.getVersion_code());
                    voicePackageInfo.setNewVersionAvailable(true);
                    return;
                }
                return;
            }
        }
        getAllPackages().add(pack);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract.PresenterInterface
    public void select(final VoicePackageInfo theOne) {
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append(theOne != null ? theOne.getName() : null);
        sb.append(' ');
        sb.append(theOne != null ? Boolean.valueOf(theOne.getIsExist()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3275i(tag, objArr);
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$select$1
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
                VoicePackageHelper voicePackageHelper;
                VoicePackageHelper voicePackageHelper2;
                VoicePackageHelper voicePackageHelper3;
                Iterator<T> it = RobotVoicesPresenter.this.getAllPackages().iterator();
                while (it.hasNext()) {
                    ((VoicePackageInfo) it.next()).setSelected(false);
                }
                RobotVoicesPresenter.this.selectedOne = theOne;
                VoicePackageInfo voicePackageInfo = theOne;
                if (voicePackageInfo == null || voicePackageInfo.getId() == VoicePackageHelper.INSTANCE.getDefaultID()) {
                    voicePackageHelper = RobotVoicesPresenter.this.packageHelper;
                    voicePackageHelper.setSelectedIDRecord((String) null);
                    voicePackageHelper2 = RobotVoicesPresenter.this.packageHelper;
                    voicePackageHelper2.defaultPackage().setSelected(true);
                } else if (RobotVoicesPresenter.this.getAllPackages().contains(theOne)) {
                    if (theOne.getIsExist()) {
                        theOne.setSelected(true);
                        if (new VoicePlayerHelper().loadCustomVoiceToMemory(String.valueOf(theOne.getId()))) {
                            voicePackageHelper3 = RobotVoicesPresenter.this.packageHelper;
                            voicePackageHelper3.setSelectedIDRecord(String.valueOf(theOne.getId()));
                        }
                    } else {
                        RobotVoicesPresenter.this.download(theOne);
                        return;
                    }
                }
                RobotVoicesPresenter.this.showListOnUI();
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract.PresenterInterface
    public void download(final VoicePackageInfo theOne) {
        Intrinsics.checkParameterIsNotNull(theOne, "theOne");
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("download ");
        sb.append(theOne.getName());
        sb.append(" url:");
        VoicePackCloud voicePackCloud = this.cloudLib.get(Long.valueOf(theOne.getId()));
        sb.append(voicePackCloud != null ? voicePackCloud.getUrl() : null);
        objArr[0] = sb.toString();
        Pdlog.m3275i(tag, objArr);
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$download$1
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
                HashMap hashMap;
                hashMap = RobotVoicesPresenter.this.cloudLib;
                VoicePackCloud voicePackCloud2 = (VoicePackCloud) hashMap.get(Long.valueOf(theOne.getId()));
                String url = voicePackCloud2 != null ? voicePackCloud2.getUrl() : null;
                if (url != null) {
                    RobotVoicesPresenter.this.downloadZip(url, theOne);
                } else {
                    RobotVoicesPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$download$1.1
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
                            theView = RobotVoicesPresenter.this.getTheView();
                            if (theView != null) {
                                theView.showDownloadProgress(RobotVoicesContract.DownloadResult.FAIL, theOne);
                            }
                        }
                    });
                }
                RobotVoicesPresenter.this.showListOnUI();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downloadZip(String url, final VoicePackageInfo packInfo) {
        packInfo.setDownloading(true);
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$downloadZip$1
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
                RobotVoicesContract.ViewInterface theView;
                theView = RobotVoicesPresenter.this.getTheView();
                if (theView != null) {
                    theView.showDownloadProgress(RobotVoicesContract.DownloadResult.DOWNLOADING, packInfo);
                }
            }
        });
        DownloadManager.getInstance().download(VoicePackageHelper.INSTANCE.getDownloadPath(), url, new RobotVoicesPresenter$downloadZip$2(this, packInfo));
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract.PresenterInterface
    public void cancelDownload(final VoicePackageInfo theOne) {
        Intrinsics.checkParameterIsNotNull(theOne, "theOne");
        Pdlog.m3275i(getTAG(), "cancel " + theOne.getName());
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$cancelDownload$1
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
                HashMap hashMap;
                hashMap = RobotVoicesPresenter.this.cloudLib;
                VoicePackCloud voicePackCloud = (VoicePackCloud) hashMap.get(Long.valueOf(theOne.getId()));
                if (voicePackCloud != null) {
                    DownloadManager.getInstance().cancel(voicePackCloud.getUrl());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract.PresenterInterface
    public void delete(final VoicePackageInfo theOne) {
        Intrinsics.checkParameterIsNotNull(theOne, "theOne");
        Pdlog.m3275i(getTAG(), "delete " + theOne.getId());
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$delete$1
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
                VoicePackageHelper voicePackageHelper;
                VoicePackageHelper voicePackageHelper2;
                VoicePackageHelper voicePackageHelper3;
                VoicePackageHelper voicePackageHelper4;
                voicePackageHelper = RobotVoicesPresenter.this.packageHelper;
                voicePackageHelper.deletePackFile(String.valueOf(theOne.getId()));
                theOne.setExist(false);
                if (theOne.getSelected()) {
                    voicePackageHelper3 = RobotVoicesPresenter.this.packageHelper;
                    voicePackageHelper3.setSelectedIDRecord((String) null);
                    voicePackageHelper4 = RobotVoicesPresenter.this.packageHelper;
                    voicePackageHelper4.defaultPackage().setSelected(true);
                    theOne.setSelected(false);
                }
                theOne.setNewVersionAvailable(false);
                voicePackageHelper2 = RobotVoicesPresenter.this.packageHelper;
                voicePackageHelper2.syncRecordToSDCard(RobotVoicesPresenter.this.getAllPackages());
                RobotVoicesPresenter.this.showListOnUI();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showListOnUI() {
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(getAllPackages());
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter$showListOnUI$1
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
                RobotVoicesContract.ViewInterface theView;
                theView = RobotVoicesPresenter.this.getTheView();
                if (theView != null) {
                    theView.showListSync(arrayList);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void replaceView(RobotVoicesContract.ViewInterface view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.replaceView((RobotVoicesPresenter) view);
        this.mScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    }

    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void removeView(RobotVoicesContract.ViewInterface view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.removeView((RobotVoicesPresenter) view);
        Job job = this.mCurJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        CoroutineScope coroutineScope = this.mScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mScope");
        }
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
    }
}
