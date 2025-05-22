package com.pudutech.bumblebee.presenter.robot_voices_task;

import androidx.core.app.NotificationCompat;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import java.util.List;
import kotlin.Metadata;

/* compiled from: RobotVoicesContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesContract;", "", "DownloadResult", "PresenterInterface", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface RobotVoicesContract {

    /* compiled from: RobotVoicesContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesContract$DownloadResult;", "", "(Ljava/lang/String;I)V", "SUCCESS", "FAIL", "DOWNLOADING", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum DownloadResult {
        SUCCESS,
        FAIL,
        DOWNLOADING
    }

    /* compiled from: RobotVoicesContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H&J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H&J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H&J\u0012\u0010\u000e\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004H&J\b\u0010\u000f\u001a\u00020\nH&R\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "allPackages", "", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "getAllPackages", "()Ljava/util/List;", "setAllPackages", "(Ljava/util/List;)V", "cancelDownload", "", "theOne", RequestParameters.SUBRESOURCE_DELETE, "download", "select", "syncList", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void cancelDownload(VoicePackageInfo theOne);

        void delete(VoicePackageInfo theOne);

        void download(VoicePackageInfo theOne);

        List<VoicePackageInfo> getAllPackages();

        void select(VoicePackageInfo theOne);

        void setAllPackages(List<VoicePackageInfo> list);

        void syncList();
    }

    /* compiled from: RobotVoicesContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&J\u0016\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0011H&¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "showCouldSyncResult", "", "success", "", NotificationCompat.CATEGORY_MESSAGE, "", "code", "", "showDownloadProgress", SpeechUtility.TAG_RESOURCE_RESULT, "Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesContract$DownloadResult;", "info", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "showListSync", "all", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void showCouldSyncResult(boolean success, String msg, int code);

        void showDownloadProgress(DownloadResult result, VoicePackageInfo info);

        void showListSync(List<VoicePackageInfo> all);
    }
}
