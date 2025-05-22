package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.robot.module.voice.VoicePackageManager;
import com.pudutech.robot.module.voice.pkg.VoicePackageInfo;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoicePkgWm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007*\u0001\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012J\b\u0010\u001a\u001a\u00020\u0016H\u0014J\u0010\u0010\u001b\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0012J\u0006\u0010\u001c\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR'\u0010\u0010\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u00130\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\t¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/VoicePkgWm;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "downloadInfo", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/robot/module/voice/VoicePackageManager$DownloadVoice;", "getDownloadInfo", "()Landroidx/lifecycle/MutableLiveData;", "onDownloadListener", "com/pudutech/peanut/robot_ui/viewmodel/VoicePkgWm$onDownloadListener$1", "Lcom/pudutech/peanut/robot_ui/viewmodel/VoicePkgWm$onDownloadListener$1;", "updateResult", "Lcom/pudutech/robot/module/voice/VoicePackageManager$UpdateResult;", "getUpdateResult", "voicePkgList", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/voice/pkg/VoicePackageInfo;", "Lkotlin/collections/ArrayList;", "getVoicePkgList", "cancelDownload", "", "voice", "deletePkg", "downloadPkg", "onCleared", "switchPkgIfNeedDownload", "updatePkgList", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class VoicePkgWm extends BaseViewModel {
    private final String TAG = "VoicePkgWm";
    private final MutableLiveData<ArrayList<VoicePackageInfo>> voicePkgList = new MutableLiveData<>();
    private final MutableLiveData<VoicePackageManager.DownloadVoice> downloadInfo = new MutableLiveData<>();
    private final MutableLiveData<VoicePackageManager.UpdateResult> updateResult = new MutableLiveData<>();
    private final VoicePkgWm$onDownloadListener$1 onDownloadListener = new VoicePackageManager.OnDownloadListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.VoicePkgWm$onDownloadListener$1
        @Override // com.pudutech.robot.module.voice.VoicePackageManager.OnDownloadListener
        public void onLoad(VoicePackageManager.DownloadVoice v) {
            Intrinsics.checkParameterIsNotNull(v, "v");
            Pdlog.m3273d(VoicePkgWm.this.TAG, "onLoad : v = " + v + "; ");
            VoicePkgWm.this.getDownloadInfo().postValue(v);
        }
    };

    /* JADX WARN: Type inference failed for: r0v4, types: [com.pudutech.peanut.robot_ui.viewmodel.VoicePkgWm$onDownloadListener$1] */
    public VoicePkgWm() {
        VoicePackageManager.INSTANCE.getLocalVoicePkgList(new Function1<ArrayList<VoicePackageInfo>, Unit>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.VoicePkgWm.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayList<VoicePackageInfo> arrayList) {
                invoke2(arrayList);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ArrayList<VoicePackageInfo> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Pdlog.m3273d(VoicePkgWm.this.TAG, "getLocalVoicePkgList " + it + ' ');
                VoicePkgWm.this.getVoicePkgList().postValue(it);
            }
        });
        VoicePackageManager.INSTANCE.addDownloadListener(this.onDownloadListener);
    }

    public final MutableLiveData<ArrayList<VoicePackageInfo>> getVoicePkgList() {
        return this.voicePkgList;
    }

    public final MutableLiveData<VoicePackageManager.DownloadVoice> getDownloadInfo() {
        return this.downloadInfo;
    }

    public final MutableLiveData<VoicePackageManager.UpdateResult> getUpdateResult() {
        return this.updateResult;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        VoicePackageManager.INSTANCE.removeDownloadListener(this.onDownloadListener);
    }

    public final void updatePkgList() {
        Pdlog.m3273d(this.TAG, "updatePkgList ");
        VoicePackageManager.INSTANCE.updateVoicePkgList(new Function1<VoicePackageManager.UpdateResult, Unit>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.VoicePkgWm$updatePkgList$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VoicePackageManager.UpdateResult updateResult) {
                invoke2(updateResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VoicePackageManager.UpdateResult it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Pdlog.m3273d(VoicePkgWm.this.TAG, "updatePkgList " + it.getSuccess());
                if (it.getSuccess()) {
                    VoicePkgWm.this.getVoicePkgList().postValue(it.getL());
                }
                VoicePkgWm.this.getUpdateResult().postValue(it);
            }
        });
    }

    public final void switchPkgIfNeedDownload(VoicePackageInfo voice) {
        Pdlog.m3273d(this.TAG, "switchPkgIfNeedDownload : voice = " + voice + "; ");
        VoicePackageManager.INSTANCE.selectPkg(voice, new Function1<ArrayList<VoicePackageInfo>, Unit>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.VoicePkgWm$switchPkgIfNeedDownload$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayList<VoicePackageInfo> arrayList) {
                invoke2(arrayList);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ArrayList<VoicePackageInfo> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                VoicePkgWm.this.getVoicePkgList().postValue(it);
            }
        });
    }

    public final void downloadPkg(VoicePackageInfo voice) {
        Intrinsics.checkParameterIsNotNull(voice, "voice");
        Pdlog.m3273d(this.TAG, "downloadPkg : voice = " + voice + "; ");
        VoicePackageManager.INSTANCE.download(voice, new Function1<ArrayList<VoicePackageInfo>, Unit>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.VoicePkgWm$downloadPkg$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayList<VoicePackageInfo> arrayList) {
                invoke2(arrayList);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ArrayList<VoicePackageInfo> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                VoicePkgWm.this.getVoicePkgList().postValue(it);
            }
        });
    }

    public final void deletePkg(VoicePackageInfo voice) {
        Intrinsics.checkParameterIsNotNull(voice, "voice");
        Pdlog.m3273d(this.TAG, "deletePkg : voice = " + voice + "; ");
        VoicePackageManager.INSTANCE.delete(voice, new Function1<ArrayList<VoicePackageInfo>, Unit>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.VoicePkgWm$deletePkg$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayList<VoicePackageInfo> arrayList) {
                invoke2(arrayList);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ArrayList<VoicePackageInfo> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                VoicePkgWm.this.getVoicePkgList().postValue(it);
            }
        });
    }

    public final void cancelDownload(VoicePackageInfo voice) {
        Intrinsics.checkParameterIsNotNull(voice, "voice");
        Pdlog.m3273d(this.TAG, "cancelDownload : voice = " + voice + "; ");
        VoicePackageManager.INSTANCE.cancelDownload(voice);
    }
}
