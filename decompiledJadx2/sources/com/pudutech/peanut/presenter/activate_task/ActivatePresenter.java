package com.pudutech.peanut.presenter.activate_task;

import android.content.Context;
import android.util.Log;
import androidx.core.view.PointerIconCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.peanut.presenter.activate_task.ActivateContract;
import com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.peanut.presenter.net.ServerApiManager;
import com.pudutech.peanut.presenter.net.req.RobotActiveReq;
import com.pudutech.peanut.presenter.net.req.RobotActiveStatusReq;
import com.pudutech.peanut.presenter.net.resp.RobotActiveResp;
import com.pudutech.peanut.presenter.net.resp.RobotActiveStatusResp;
import com.pudutech.peanut.presenter.utils.WifiUtil;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.UnknownHostException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: ActivatePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\u0006J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0013H\u0002J\u001c\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u000eH\u0014J\b\u0010 \u001a\u00020\u000eH\u0014J\u000e\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/activate_task/ActivatePresenter;", "Lcom/pudutech/peanut/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "disposableGetStatus", "Lio/reactivex/disposables/Disposable;", "isFactoryRobotActiveKey", "robotActiveKey", "checkActiveStatus", "", "context", "Landroid/content/Context;", "getPID", "isActiveLocal", "", "localActive", "code", "notifyLocalStatus", "isNetError", "notifyUi", "status", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$RobotActiveStatus;", "info", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$ActiveRobotInfo;", "notifyUiStatus", "Lcom/pudutech/peanut/presenter/net/resp/RobotActiveStatusResp;", "onViewAttach", "onViewRemoved", "robotActiveReq", "requestData", "Lcom/pudutech/peanut/presenter/net/req/RobotActiveReq;", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ActivatePresenter extends BaseOneViewPresenter<ActivateContract.ViewInterface> implements ActivateContract.PresenterInterface {
    private volatile Disposable disposableGetStatus;
    private final String TAG = "ActivatePresenter";
    private final String robotActiveKey = "robot_server_ac_key";
    private final String isFactoryRobotActiveKey = "isFactoryrobot_server_ac_key";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        Disposable disposable = this.disposableGetStatus;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposableGetStatus = (Disposable) null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v11, types: [T, java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v14, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getPID() {
        FileReader fileReader;
        String str;
        BufferedReader bufferedReader;
        File file = new File("/sdcard/pudu/config/pid");
        if (!file.exists()) {
            return "";
        }
        FileReader fileReader2 = (FileReader) null;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (BufferedReader) 0;
        try {
            try {
                fileReader = new FileReader(file);
            } catch (Throwable th) {
                th = th;
                fileReader = fileReader2;
            }
        } catch (Exception e) {
            e = e;
            str = "";
        }
        try {
            try {
                objectRef.element = new BufferedReader(fileReader);
                try {
                    final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (String) 0;
                    str = "";
                    while (new Function0<String>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$getPID$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        /* JADX WARN: Type inference failed for: r1v3, types: [T, java.lang.String] */
                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            Ref.ObjectRef.this.element = ((BufferedReader) objectRef.element).readLine();
                            return (String) Ref.ObjectRef.this.element;
                        }
                    }.invoke() != null) {
                        try {
                            str = Intrinsics.stringPlus(str, (String) objectRef2.element);
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                e.printStackTrace();
                                bufferedReader = (BufferedReader) objectRef.element;
                                if (bufferedReader != null) {
                                }
                                fileReader.close();
                            } catch (Exception e3) {
                                e = e3;
                                fileReader2 = fileReader;
                                e.printStackTrace();
                                BufferedReader bufferedReader2 = (BufferedReader) objectRef.element;
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                if (fileReader2 != null) {
                                    fileReader2.close();
                                }
                                Pdlog.m3273d(getTAG(), "读取出来的文件内容是：" + str);
                                if (str != null) {
                                }
                            }
                            Pdlog.m3273d(getTAG(), "读取出来的文件内容是：" + str);
                            if (str != null) {
                            }
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    str = "";
                }
                bufferedReader = (BufferedReader) objectRef.element;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                fileReader.close();
            } catch (Exception e5) {
                e = e5;
                str = "";
            }
            Pdlog.m3273d(getTAG(), "读取出来的文件内容是：" + str);
            return str != null ? str : "";
        } catch (Throwable th2) {
            th = th2;
            BufferedReader bufferedReader3 = (BufferedReader) objectRef.element;
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
    }

    public final void robotActiveReq(RobotActiveReq requestData) {
        Intrinsics.checkParameterIsNotNull(requestData, "requestData");
        ServerApiManager.ServerApi.DefaultImpls.getRobotActive$default(ServerApiManager.INSTANCE.getServerApi(), null, requestData, 1, null).observeOn(Schedulers.m3958io()).subscribeOn(Schedulers.m3958io()).subscribe(new Consumer<ServerApiManager.HttpResult<RobotActiveResp>>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$robotActiveReq$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(ServerApiManager.HttpResult<RobotActiveResp> httpResult) {
                final ActivateContract.ActiveRobotInfo activeRobotInfo = new ActivateContract.ActiveRobotInfo(ActivateContract.InactiveType.REQUEST_ERROR);
                int code = httpResult.getCode();
                if (code != -1) {
                    if (code == 0) {
                        ActivatePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$robotActiveReq$1.1
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
                                ActivateContract.ViewInterface theView;
                                theView = ActivatePresenter.this.getTheView();
                                if (theView != null) {
                                    ActivateContract.ViewInterface.DefaultImpls.showActiveStatus$default(theView, ActivateContract.RobotActiveStatus.ACTIVE, null, 2, null);
                                }
                            }
                        });
                        return;
                    }
                    switch (code) {
                        case 1001:
                            activeRobotInfo.setInactiveType(ActivateContract.InactiveType.PARAM_IS_MISSING);
                            break;
                        case 1002:
                            activeRobotInfo.setInactiveType(ActivateContract.InactiveType.CODE_NOT_FOUND);
                            break;
                        case 1003:
                            activeRobotInfo.setInactiveType(ActivateContract.InactiveType.CODE_HAS_USE);
                            break;
                        case 1004:
                            activeRobotInfo.setInactiveType(ActivateContract.InactiveType.STATUS_UNABLE_ACTIVE);
                            break;
                        case 1005:
                            activeRobotInfo.setInactiveType(ActivateContract.InactiveType.EXCEPTION_RECORD);
                            break;
                        case PointerIconCompat.TYPE_CELL /* 1006 */:
                            activeRobotInfo.setInactiveType(ActivateContract.InactiveType.NEED_FROZEN);
                            break;
                    }
                }
                ActivatePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$robotActiveReq$1.2
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
                        ActivateContract.ViewInterface theView;
                        theView = ActivatePresenter.this.getTheView();
                        if (theView != null) {
                            theView.showActiveStatus(ActivateContract.RobotActiveStatus.INACTIVE, activeRobotInfo);
                        }
                    }
                });
            }
        }, new Consumer<Throwable>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$robotActiveReq$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Pdlog.m3274e("re", "onError");
                ActivatePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$robotActiveReq$2.1
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
                        ActivateContract.ViewInterface theView;
                        theView = ActivatePresenter.this.getTheView();
                        if (theView != null) {
                            theView.showActiveStatus(ActivateContract.RobotActiveStatus.INACTIVE, null);
                        }
                    }
                });
            }
        });
    }

    @Override // com.pudutech.peanut.presenter.activate_task.ActivateContract.PresenterInterface
    public void checkActiveStatus(final Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(getTAG(), "checkActiveStatus ");
        if (this.disposableGetStatus != null) {
            Disposable disposable = this.disposableGetStatus;
            if (disposable == null) {
                Intrinsics.throwNpe();
            }
            if (!disposable.isDisposed()) {
                Pdlog.m3273d(getTAG(), "checkActiveStatus , is requesting ");
                return;
            }
        }
        this.disposableGetStatus = ServerApiManager.ServerApi.DefaultImpls.getRobotActiveStatus$default(ServerApiManager.INSTANCE.getServerApi(), null, new RobotActiveStatusReq(), 1, null).observeOn(Schedulers.m3958io()).subscribeOn(Schedulers.m3958io()).subscribe(new Consumer<ServerApiManager.HttpResult<RobotActiveStatusResp>>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$checkActiveStatus$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(ServerApiManager.HttpResult<RobotActiveStatusResp> httpResult) {
                boolean isActiveLocal;
                Pdlog.m3273d(ActivatePresenter.this.getTAG(), "checkActiveStatus " + httpResult);
                Thread.sleep(500L);
                if (httpResult.getData() == null) {
                    isActiveLocal = ActivatePresenter.this.isActiveLocal(context);
                    if (isActiveLocal) {
                        ActivatePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$checkActiveStatus$1.1
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
                                ActivateContract.ViewInterface theView;
                                theView = ActivatePresenter.this.getTheView();
                                if (theView != null) {
                                    ActivateContract.ViewInterface.DefaultImpls.showActiveStatus$default(theView, ActivateContract.RobotActiveStatus.ACTIVE, null, 2, null);
                                }
                            }
                        });
                        return;
                    } else {
                        ActivatePresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$checkActiveStatus$1.2
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
                                ActivateContract.ViewInterface theView;
                                theView = ActivatePresenter.this.getTheView();
                                if (theView != null) {
                                    theView.showActiveStatus(ActivateContract.RobotActiveStatus.INACTIVE, new ActivateContract.ActiveRobotInfo(ActivateContract.InactiveType.REQUEST_ERROR));
                                }
                            }
                        });
                        return;
                    }
                }
                ActivatePresenter.this.notifyUiStatus(context, httpResult.getData());
            }
        }, new Consumer<Throwable>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$checkActiveStatus$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Pdlog.m3274e(ActivatePresenter.this.getTAG(), "checkActiveStatus from server : " + th + " ," + Log.getStackTraceString(th));
                ActivatePresenter.this.notifyLocalStatus(context, th instanceof UnknownHostException);
            }
        });
    }

    @Override // com.pudutech.peanut.presenter.activate_task.ActivateContract.PresenterInterface
    public void localActive(String code) {
        Intrinsics.checkParameterIsNotNull(code, "code");
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$localActive$1
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
                ActivateContract.ViewInterface theView;
                theView = ActivatePresenter.this.getTheView();
                if (theView != null) {
                    theView.showLocalActiveResult(false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyUiStatus(Context context, RobotActiveStatusResp status) {
        if (Intrinsics.areEqual(status.getRobot_type(), "active")) {
            SpUtils.set(context, this.robotActiveKey, true);
            SpUtils.set(context, this.isFactoryRobotActiveKey, false);
            notifyUi$default(this, ActivateContract.RobotActiveStatus.ACTIVE, null, 2, null);
        } else {
            if (Intrinsics.areEqual(status.getRobot_type(), "testing")) {
                if (status.getOver_time() <= 0) {
                    SpUtils.set(context, this.isFactoryRobotActiveKey, false);
                    SpUtils.set(context, this.robotActiveKey, false);
                    notifyUi(ActivateContract.RobotActiveStatus.INACTIVE, new ActivateContract.ActiveRobotInfo(ActivateContract.InactiveType.TESTING_TIMEOUT));
                    return;
                } else {
                    SpUtils.set(context, this.isFactoryRobotActiveKey, true);
                    notifyUi$default(this, ActivateContract.RobotActiveStatus.ACTIVE, null, 2, null);
                    return;
                }
            }
            SpUtils.set(context, this.isFactoryRobotActiveKey, false);
            SpUtils.set(context, this.robotActiveKey, false);
            notifyUi(ActivateContract.RobotActiveStatus.INACTIVE, new ActivateContract.ActiveRobotInfo(ActivateContract.InactiveType.SERVER_STATUS));
        }
    }

    static /* synthetic */ void notifyUi$default(ActivatePresenter activatePresenter, ActivateContract.RobotActiveStatus robotActiveStatus, ActivateContract.ActiveRobotInfo activeRobotInfo, int i, Object obj) {
        if ((i & 2) != 0) {
            activeRobotInfo = (ActivateContract.ActiveRobotInfo) null;
        }
        activatePresenter.notifyUi(robotActiveStatus, activeRobotInfo);
    }

    private final void notifyUi(final ActivateContract.RobotActiveStatus status, final ActivateContract.ActiveRobotInfo info) {
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.activate_task.ActivatePresenter$notifyUi$1
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
                ActivateContract.ViewInterface theView;
                theView = ActivatePresenter.this.getTheView();
                if (theView != null) {
                    theView.showActiveStatus(status, info);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyLocalStatus(Context context, boolean isNetError) {
        if (isActiveLocal(context)) {
            Pdlog.m3273d(getTAG(), "notifyLocalStatus : isActiveLocal");
            notifyUi$default(this, ActivateContract.RobotActiveStatus.ACTIVE, null, 2, null);
        } else if (!WifiUtil.INSTANCE.isNetworkAvailable(context) || isNetError) {
            Pdlog.m3273d(getTAG(), "notifyLocalStatus : isNetworkAvailable is false");
            notifyUi(ActivateContract.RobotActiveStatus.INACTIVE, new ActivateContract.ActiveRobotInfo(ActivateContract.InactiveType.WIFI_NOT_CONNECT));
        } else {
            Pdlog.m3273d(getTAG(), "notifyLocalStatus : isNetworkAvailable is true");
            notifyUi(ActivateContract.RobotActiveStatus.INACTIVE, new ActivateContract.ActiveRobotInfo(ActivateContract.InactiveType.REQUEST_ERROR));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isActiveLocal(Context context) {
        return SpUtils.get(context, this.robotActiveKey, false);
    }
}
