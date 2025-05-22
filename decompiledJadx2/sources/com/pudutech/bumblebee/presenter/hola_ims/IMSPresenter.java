package com.pudutech.bumblebee.presenter.hola_ims;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.IMSKit;
import com.pudutech.bumblebee.business.ims.config.CallPoint;
import com.pudutech.bumblebee.business.ims.config.IMSConfig;
import com.pudutech.bumblebee.business.ims.config.MsgType;
import com.pudutech.bumblebee.business.ims.lora.Channel;
import com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import com.pudutech.bumblebee.presenter.hola_ims.IMSContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.utils.FileUtil;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.map.DestinationType;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IMSPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0087\u0001\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016J\u007f\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016Jm\u0010\u0018\u001a\u00020\n2%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016J\b\u0010\u0019\u001a\u00020\nH\u0016J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016Jm\u0010\u001d\u001a\u00020\n2%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016J\b\u0010\u001e\u001a\u00020\nH\u0016Ju\u0010\u001f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016J\u0018\u0010 \u001a\u0004\u0018\u00010\u00112\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$Jm\u0010%\u001a\u00020\n2%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016J4\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\f2\u0010\u0010(\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010)2\u0010\u0010*\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010)H\u0016J\u001c\u0010+\u001a\u0016\u0012\u0004\u0012\u00020-\u0018\u00010,j\n\u0012\u0004\u0012\u00020-\u0018\u0001`.H\u0016J\u0010\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u000201H\u0016J\n\u00102\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u00103\u001a\u00020\fH\u0016Jm\u00104\u001a\u00020\n2%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016Jm\u00105\u001a\u00020\n2%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016J\u0010\u00106\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0006H\u0016Jm\u00107\u001a\u00020\n2%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016J\u0010\u00108\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J}\u00109\u001a\u00020\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016Jm\u0010:\u001a\u00020\n2%\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00102<\u0010\u0015\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0016H\u0016J\u0010\u0010;\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\u0012\u0010<\u001a\u00020\n2\b\u0010=\u001a\u0004\u0018\u00010\u0006H\u0016J\u001c\u0010>\u001a\u00020\n2\b\u0010?\u001a\u0004\u0018\u00010@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\u001c\u0010C\u001a\u00020\n2\b\u0010?\u001a\u0004\u0018\u00010@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016Js\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020\u00062\u0016\u0010F\u001a\u0012\u0012\u0004\u0012\u00020-0,j\b\u0012\u0004\u0012\u00020-`.2\u0010\u0010G\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010)2\u0010\u0010H\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010)2%\u0010I\u001a!\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(J\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0010H\u0016Js\u0010K\u001a\u00020\n2\u0006\u0010E\u001a\u00020\u00062\u0016\u0010F\u001a\u0012\u0012\u0004\u0012\u00020-0,j\b\u0012\u0004\u0012\u00020-`.2\u0010\u0010L\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010)2\u0010\u0010M\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010)2%\u0010N\u001a!\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(J\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0010H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006O"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/hola_ims/IMSPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/hola_ims/IMSContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/hola_ims/IMSContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "addBeeperBroadcastControl", "", "isOpen", "", "msgId", "centralControlCode", "onSentSucceed", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "Lkotlin/ParameterName;", "name", NotificationCompat.CATEGORY_MESSAGE, "onSentFailed", "Lkotlin/Function2;", "reason", "addDevice", "cancelSyncMap", "changeReportInterval", "delayTime", "", "checkMapSyncStatus", "checkTaskPartitionTableSyncStatus", "controlCentralControlBroadcast", "createMsg", "task", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Task;", "callPoint", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$CallPoint;", "deleteDevice", "enableTaskPartition", "isEnable", "onSucceed", "Lkotlin/Function0;", "onFailed", "getCallPointList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/ims/config/CallPoint;", "Lkotlin/collections/ArrayList;", "getMac", "context", "Landroid/content/Context;", "getMapVersionMD5", "isReportingRobotStatus", "queryHasUnallocatedTask", "queryMapVersion", "removeMsg", "reportCurrentTime", "reportRobotStatus", "reportTaskStatus", "resetCentralControl", "sendWatchMsg", "setCentralControlMac", "mac", "setupChannel", "chl", "Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "listener", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$OnSetupChannelListener;", "setupLocalChannel", "syncMap", "version", "callPointList", "onSyncMapSucceed", "onSyncMapFailed", "onSyncMapPercent", "percent", "syncTaskPartitionTable", "onSyncTaskPartitionTableSucceed", "onSyncTaskPartitionTableFailed", "onSyncTaskPartitionTablePercent", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IMSPresenter extends BaseOneViewPresenter<IMSContract.ViewInterface> implements IMSContract.PresenterInterface {
    private final String TAG = "IMSPresenter";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public String getMac(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return IMSKit.INSTANCE.getInstance().getMac(context);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void controlCentralControlBroadcast(boolean isOpen, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        IMSKit.INSTANCE.getInstance().controlCentralControlBroadcast(isOpen, onSentSucceed, onSentFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void setCentralControlMac(String mac) {
        IMSKit.INSTANCE.getInstance().setCentralControlMac(mac);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void queryMapVersion(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        IMSKit.INSTANCE.getInstance().queryMapVersion(onSentSucceed, onSentFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public String getMapVersionMD5() {
        String currentMapAtlasPath = RobotMapManager.INSTANCE.getCurrentMapAtlasPath();
        String str = currentMapAtlasPath;
        if (str == null || str.length() == 0) {
            Pdlog.m3277w(getTAG(), "getMapVersionMD5() failed, mapFilePath is null or empty");
            return null;
        }
        File file = new File(currentMapAtlasPath);
        if (!file.exists() || !file.isFile()) {
            Pdlog.m3277w(getTAG(), "getMapVersionMD5() failed, mapFile is not exists or mapFile is not file.");
            return null;
        }
        return FileUtil.INSTANCE.getFileMD5(file);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public ArrayList<CallPoint> getCallPointList() {
        String str;
        ArrayList<CallPoint> arrayList = new ArrayList<>();
        Pdlog.m3273d(getTAG(), "getCallPointList");
        for (Destination destination : RobotMapManager.INSTANCE.getAllDestination()) {
            DestinationType type = destination.getType();
            if (type == null || (str = type.getTypeName()) == null) {
                str = "";
            }
            arrayList.add(new CallPoint(str, destination.getName()));
        }
        return arrayList;
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void syncMap(String version, ArrayList<CallPoint> callPointList, Function0<Unit> onSyncMapSucceed, Function0<Unit> onSyncMapFailed, Function1<? super Integer, Unit> onSyncMapPercent) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(callPointList, "callPointList");
        IMSKit.INSTANCE.getInstance().syncMap(version, callPointList, onSyncMapSucceed, onSyncMapFailed, onSyncMapPercent);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void syncTaskPartitionTable(String version, ArrayList<CallPoint> callPointList, Function0<Unit> onSyncTaskPartitionTableSucceed, Function0<Unit> onSyncTaskPartitionTableFailed, Function1<? super Integer, Unit> onSyncTaskPartitionTablePercent) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(callPointList, "callPointList");
        IMSKit.INSTANCE.getInstance().syncTaskPartitionTable(version, callPointList, onSyncTaskPartitionTableSucceed, onSyncTaskPartitionTableFailed, onSyncTaskPartitionTablePercent);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void enableTaskPartition(boolean isEnable, Function0<Unit> onSucceed, Function0<Unit> onFailed) {
        IMSKit.INSTANCE.getInstance().enableTaskPartition(isEnable, onSucceed, onFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void checkTaskPartitionTableSyncStatus() {
        IMSKit.checkTaskPartitionTableSyncStatus$default(IMSKit.INSTANCE.getInstance(), null, null, 3, null);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void cancelSyncMap() {
        IMSKit.INSTANCE.getInstance().cancelSyncMap();
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void reportRobotStatus(boolean isOpen) {
        IMSKit.INSTANCE.getInstance().reportRobotStatus(isOpen);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void changeReportInterval(int delayTime) {
        IMSKit.INSTANCE.getInstance().changeReportInterval(delayTime);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public boolean isReportingRobotStatus() {
        return IMSKit.INSTANCE.getInstance().isReportingRobotStatus();
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void reportTaskStatus(MessageProtobuf.Task task, MessageProtobuf.CallPoint callPoint, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(callPoint, "callPoint");
        IMSKit.INSTANCE.getInstance().reportTaskStatus(task, callPoint, onSentSucceed, onSentFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void resetCentralControl(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        IMSKit.INSTANCE.getInstance().resetCentralControl(onSentSucceed, onSentFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void queryHasUnallocatedTask(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        IMSKit.INSTANCE.getInstance().queryHasUnallocatedTask(onSentSucceed, onSentFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void addBeeperBroadcastControl(boolean isOpen, String centralControlCode, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        IMSKit.INSTANCE.getInstance().addBeeperBroadcastControl(isOpen, centralControlCode, onSentSucceed, onSentFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void addBeeperBroadcastControl(boolean isOpen, String msgId, String centralControlCode, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        IMSKit.INSTANCE.getInstance().addBeeperBroadcastControl(isOpen, msgId, centralControlCode, onSentSucceed, onSentFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void checkMapSyncStatus(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        IMSKit.INSTANCE.getInstance().checkMapSyncStatus(onSentSucceed, onSentFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void addDevice(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        IMSKit.INSTANCE.getInstance().addDevice(onSentSucceed, onSentFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void removeMsg(String msgId) {
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        IMSKit.INSTANCE.getInstance().removeMsg(msgId);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void deleteDevice(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        IMSKit.INSTANCE.getInstance().deleteDevice(onSentSucceed, onSentFailed);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void setupChannel(Channel chl, LoRaChannelManager2.OnSetupChannelListener listener) {
        IMSKit.INSTANCE.getInstance().setupChannel(chl, listener);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void setupLocalChannel(Channel chl, LoRaChannelManager2.OnSetupChannelListener listener) {
        IMSKit.INSTANCE.getInstance().setupLocalChannel(chl, listener);
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void sendWatchMsg(MessageProtobuf.Msg msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        try {
            byte[] byteArray = msg.toByteArray();
            int length = byteArray.length;
            ByteBuffer allocate = ByteBuffer.allocate(IMSConfig.FRAME_HEAD.length + 1 + length);
            allocate.put(IMSConfig.FRAME_HEAD);
            allocate.put((byte) (length & 255));
            allocate.put(byteArray);
            Peripherals.INSTANCE.getIms().sendMsg(allocate.array());
            Pdlog.m3273d("IMSPresenter", "sendWatchMsg() msg = " + msg + ", length = " + allocate.array().length + ", channel = " + LoRaChannelManager2.INSTANCE.getINSTANCE().getCurrentChannel() + ", data = " + Arrays.toString(allocate.array()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.pudutech.bumblebee.presenter.hola_ims.IMSContract.PresenterInterface
    public void reportCurrentTime(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        IMSKit.INSTANCE.getInstance().reportCurrentTime(onSentSucceed, onSentFailed);
    }

    public final MessageProtobuf.Msg createMsg(MessageProtobuf.Task task, MessageProtobuf.CallPoint callPoint) {
        MessageProtobuf.Msg.Builder task2;
        MessageProtobuf.Msg.Builder callPoint2;
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(callPoint, "callPoint");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = IMSKit.INSTANCE.getInstance().generateCommonMsgBuilder(MsgType.SyncTaskState);
        if (generateCommonMsgBuilder == null || (task2 = generateCommonMsgBuilder.setTask(task)) == null || (callPoint2 = task2.setCallPoint(callPoint)) == null) {
            return null;
        }
        return callPoint2.build();
    }
}
