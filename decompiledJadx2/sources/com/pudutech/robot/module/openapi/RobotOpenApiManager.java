package com.pudutech.robot.module.openapi;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.module.openapi.remoteapi.BaseRemoteApiProvider;
import com.pudutech.robot.module.openapi.remoteapi.DisinfectionActionProvider;
import com.pudutech.robot.module.openapi.remoteapi.DisinfectionWorkingStatusProvider;
import com.pudutech.robot.module.openapi.remoteapi.RobotActionCommandProvider;
import com.pudutech.robot.module.openapi.remoteapi.RobotCallProvider;
import com.pudutech.robot.module.openapi.remoteapi.RobotCancelCallProvider;
import com.pudutech.robot.module.openapi.remoteapi.RobotDeliveryTaskProvider;
import com.pudutech.robot.module.openapi.remoteapi.RobotDeliveryTrayOrdersProvider;
import com.pudutech.robot.module.openapi.remoteapi.RobotGetDestinationsProvider;
import com.pudutech.robot.module.openapi.remoteapi.RobotStatusProvider;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.RemoteConnectState;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import com.pudutech.robot.opensdk.bean.ActionCommand;
import com.pudutech.robot.opensdk.bean.ActionCommandBody;
import com.pudutech.robot.opensdk.bean.DeliveryTaskBody;
import com.pudutech.robot.opensdk.bean.Destination;
import com.pudutech.robot.opensdk.bean.PageBody;
import com.pudutech.robot.opensdk.bean.RobotDeliveryTrayOrderBody;
import com.pudutech.robot.opensdk.bean.disnfection.DisActionBody;
import com.pudutech.robot.opensdk.bean.disnfection.DisActionCommand;
import com.pudutech.robot.opensdk.bean.disnfection.DisStatus;
import com.pudutech.robot.opensdk.bean.disnfection.DisWorkingStatusBody;
import com.pudutech.robot.opensdk.bean.resp.RespResultBody;
import com.pudutech.robot.opensdk.bean.resp.RespRobotStateBody;
import com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack;
import com.pudutech.robot.opensdk.interf.IOnOpenSdkEventListener;
import com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RobotOpenApiManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000g\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\rJ\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018J!\u0010\u0019\u001a\u0016\u0012\u0004\u0012\u00020\u001a\u0018\u00010\fj\n\u0012\u0004\u0012\u00020\u001a\u0018\u0001`\u000eH\u0000¢\u0006\u0002\b\u001bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0004H\u0002J\u000e\u0010\"\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010$\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\rJ)\u0010%\u001a\u00020\u0011\"\n\b\u0000\u0010&\u0018\u0001*\u00020\r2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u00020\u001f0(H\u0082\bJ\u000e\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tRJ\u0010\n\u001a>\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e0\u000bj\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, m3961d2 = {"Lcom/pudutech/robot/module/openapi/RobotOpenApiManager;", "", "()V", "TAG", "", "context", "Landroid/content/Context;", "onOpenSdkEventListener", "com/pudutech/robot/module/openapi/RobotOpenApiManager$onOpenSdkEventListener$1", "Lcom/pudutech/robot/module/openapi/RobotOpenApiManager$onOpenSdkEventListener$1;", "remoteApiProvidersMap", "Ljava/util/HashMap;", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/openapi/remoteapi/BaseRemoteApiProvider;", "Lkotlin/collections/ArrayList;", "Lkotlin/collections/HashMap;", "addConnectListener", "", "l", "Lcom/pudutech/robot/opensdk/interf/IRemoteConnectStateListener;", "addRemoteApiProviders", "p", "genBind", "callBack", "Lcom/pudutech/robot/opensdk/interf/IGenBindCodeCallBack;", "getAllRobotDes", "Lcom/pudutech/robot/opensdk/bean/Destination;", "getAllRobotDes$module_robot_open_api_release", "getConnectStatus", "Lcom/pudutech/robot/opensdk/RemoteConnectState;", "getSwitch", "", "hasProvider", TransferTable.COLUMN_KEY, "init", "removeConnectListener", "removeRemoteApiProviders", "resp", ExifInterface.GPS_DIRECTION_TRUE, NotificationCompat.CATEGORY_CALL, "Lkotlin/Function1;", "setSwitch", "sw", "module_robot_open_api_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotOpenApiManager {
    private static Context context;
    public static final RobotOpenApiManager INSTANCE = new RobotOpenApiManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final HashMap<String, ArrayList<BaseRemoteApiProvider>> remoteApiProvidersMap = new HashMap<>();
    private static final RobotOpenApiManager$onOpenSdkEventListener$1 onOpenSdkEventListener = new IOnOpenSdkEventListener() { // from class: com.pudutech.robot.module.openapi.RobotOpenApiManager$onOpenSdkEventListener$1
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v102 */
        /* JADX WARN: Type inference failed for: r2v103 */
        /* JADX WARN: Type inference failed for: r2v104 */
        /* JADX WARN: Type inference failed for: r2v74 */
        /* JADX WARN: Type inference failed for: r2v75 */
        /* JADX WARN: Type inference failed for: r2v76 */
        /* JADX WARN: Type inference failed for: r2v91 */
        /* JADX WARN: Type inference failed for: r2v92 */
        /* JADX WARN: Type inference failed for: r2v93 */
        @Override // com.pudutech.robot.opensdk.interf.IOnOpenSdkEventListener
        public void onEvent(MsgContext<?> msgContext) {
            String str;
            String str2;
            PageBody pageBody;
            Object reqData;
            String str3;
            ?? r2;
            String str4;
            boolean hasProvider;
            ?? r22;
            String str5;
            ?? r23;
            Intrinsics.checkParameterIsNotNull(msgContext, "msgContext");
            RobotOpenApiManager robotOpenApiManager = RobotOpenApiManager.INSTANCE;
            str = RobotOpenApiManager.TAG;
            boolean z = false;
            Pdlog.m3273d(str, "onEvent : msgContext = " + msgContext + "; ");
            String msgType = msgContext.getMsgType();
            if (!Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_QUERY_STATE())) {
                if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_DIS_STATUS())) {
                    RobotOpenApiManager robotOpenApiManager2 = RobotOpenApiManager.INSTANCE;
                    String name = DisinfectionWorkingStatusProvider.class.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "DisinfectionWorkingStatusProvider::class.java.name");
                    hasProvider = robotOpenApiManager2.hasProvider(name);
                    if (!hasProvider) {
                        RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new DisWorkingStatusBody(null, DisStatus.Idle.name(), null, 4, null), null, 4, null);
                        return;
                    }
                    ArrayList arrayList = (ArrayList) RobotOpenApiManager.access$getRemoteApiProvidersMap$p(RobotOpenApiManager.INSTANCE).get(DisinfectionWorkingStatusProvider.class.getName());
                    if (arrayList != null) {
                        Iterator it = arrayList.iterator();
                        Intrinsics.checkExpressionValueIsNotNull(it, "it.iterator()");
                        while (it.hasNext()) {
                            Object next = it.next();
                            if (next == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.DisinfectionWorkingStatusProvider");
                            }
                            DisinfectionWorkingStatusProvider disinfectionWorkingStatusProvider = (DisinfectionWorkingStatusProvider) next;
                            if (disinfectionWorkingStatusProvider != null) {
                                DisWorkingStatusBody disWorkingStatusState = disinfectionWorkingStatusProvider.getDisWorkingStatusState();
                                if (disWorkingStatusState != null) {
                                    RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, disWorkingStatusState, null, 4, null);
                                    r22 = true;
                                } else {
                                    r22 = false;
                                }
                                if (r22 != false) {
                                    break;
                                }
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.DisinfectionWorkingStatusProvider");
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                        return;
                    }
                    return;
                }
                ActionCommand actionCommand = null;
                DisActionCommand disActionCommand = null;
                if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_DIS_ACTION())) {
                    Object reqData2 = msgContext.getReqData();
                    if (reqData2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.disnfection.DisActionBody");
                    }
                    DisActionBody disActionBody = (DisActionBody) reqData2;
                    if (Intrinsics.areEqual(disActionBody.getAction(), DisActionCommand.Pause.getId())) {
                        disActionCommand = DisActionCommand.Pause;
                    } else if (Intrinsics.areEqual(disActionBody.getAction(), DisActionCommand.Resume.getId())) {
                        disActionCommand = DisActionCommand.Resume;
                    }
                    if (disActionCommand == null) {
                        RobotOpenApiManager robotOpenApiManager3 = RobotOpenApiManager.INSTANCE;
                        str4 = RobotOpenApiManager.TAG;
                        Pdlog.m3274e(str4, "onEvent : MSG_TYPE_DIS_ACTION , failed " + disActionBody);
                        return;
                    }
                    RobotOpenApiManager robotOpenApiManager4 = RobotOpenApiManager.INSTANCE;
                    str3 = RobotOpenApiManager.TAG;
                    Pdlog.m3273d(str3, "onEvent : MSG_TYPE_DIS_ACTION = " + msgContext + "; ");
                    ArrayList arrayList2 = (ArrayList) RobotOpenApiManager.access$getRemoteApiProvidersMap$p(RobotOpenApiManager.INSTANCE).get(DisinfectionActionProvider.class.getName());
                    if (arrayList2 != null) {
                        Iterator it2 = arrayList2.iterator();
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it.iterator()");
                        while (it2.hasNext()) {
                            Object next2 = it2.next();
                            if (next2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.DisinfectionActionProvider");
                            }
                            DisinfectionActionProvider disinfectionActionProvider = (DisinfectionActionProvider) next2;
                            if (disinfectionActionProvider != null) {
                                RespResultBody doAction = disinfectionActionProvider.doAction(disActionCommand);
                                if (doAction != null) {
                                    RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, doAction, null, 4, null);
                                    r2 = true;
                                } else {
                                    r2 = false;
                                }
                                if (r2 != false) {
                                    break;
                                }
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.DisinfectionActionProvider");
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        return;
                    }
                    return;
                }
                if (!Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CALL())) {
                    if (!Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CANCEL_CALL())) {
                        if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_DELIVERY_TASK())) {
                            Object reqData3 = msgContext.getReqData();
                            if (reqData3 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.DeliveryTaskBody");
                            }
                            DeliveryTaskBody deliveryTaskBody = (DeliveryTaskBody) reqData3;
                            ArrayList arrayList3 = (ArrayList) RobotOpenApiManager.access$getRemoteApiProvidersMap$p(RobotOpenApiManager.INSTANCE).get(RobotDeliveryTaskProvider.class.getName());
                            if (arrayList3 != null) {
                                Iterator it3 = arrayList3.iterator();
                                Intrinsics.checkExpressionValueIsNotNull(it3, "it.iterator()");
                                if (it3.hasNext()) {
                                    Object next3 = it3.next();
                                    if (next3 != null) {
                                        RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, ((RobotDeliveryTaskProvider) next3).setDeliveryTask(deliveryTaskBody), null, 4, null);
                                    } else {
                                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotDeliveryTaskProvider");
                                    }
                                }
                                Unit unit3 = Unit.INSTANCE;
                                return;
                            }
                            return;
                        }
                        if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_ACTION_COMMAND())) {
                            Object reqData4 = msgContext.getReqData();
                            if (reqData4 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.ActionCommandBody");
                            }
                            String action = ((ActionCommandBody) reqData4).getAction();
                            if (Intrinsics.areEqual(action, ActionCommand.START.getId())) {
                                actionCommand = ActionCommand.START;
                            } else if (Intrinsics.areEqual(action, ActionCommand.COMPLETE.getId())) {
                                actionCommand = ActionCommand.COMPLETE;
                            } else if (Intrinsics.areEqual(action, ActionCommand.CANCEL_ALL_DELIVERY.getId())) {
                                actionCommand = ActionCommand.CANCEL_ALL_DELIVERY;
                            }
                            if (actionCommand != null) {
                                ArrayList arrayList4 = (ArrayList) RobotOpenApiManager.access$getRemoteApiProvidersMap$p(RobotOpenApiManager.INSTANCE).get(RobotActionCommandProvider.class.getName());
                                if (arrayList4 != null) {
                                    Iterator it4 = arrayList4.iterator();
                                    Intrinsics.checkExpressionValueIsNotNull(it4, "it.iterator()");
                                    while (it4.hasNext()) {
                                        Object next4 = it4.next();
                                        if (next4 != null) {
                                            if (((RobotActionCommandProvider) next4).onCommand(actionCommand).getSuccess()) {
                                                z = true;
                                            }
                                        } else {
                                            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotActionCommandProvider");
                                        }
                                    }
                                    Unit unit4 = Unit.INSTANCE;
                                }
                                RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(z, null, null, 6, null), null, 4, null);
                                Unit unit5 = Unit.INSTANCE;
                                return;
                            }
                            return;
                        }
                        if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_REQUEST_DATA())) {
                            try {
                                reqData = msgContext.getReqData();
                            } catch (Exception unused) {
                                pageBody = (PageBody) null;
                            }
                            if (reqData == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.PageBody");
                            }
                            pageBody = (PageBody) reqData;
                            ArrayList arrayList5 = (ArrayList) RobotOpenApiManager.access$getRemoteApiProvidersMap$p(RobotOpenApiManager.INSTANCE).get(RobotGetDestinationsProvider.class.getName());
                            if (arrayList5 != null) {
                                Iterator it5 = arrayList5.iterator();
                                Intrinsics.checkExpressionValueIsNotNull(it5, "it.iterator()");
                                if (it5.hasNext()) {
                                    Object next5 = it5.next();
                                    if (next5 != null) {
                                        RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, ((RobotGetDestinationsProvider) next5).getDestinations(pageBody), null, 4, null);
                                    } else {
                                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotGetDestinationsProvider");
                                    }
                                }
                                Unit unit6 = Unit.INSTANCE;
                                return;
                            }
                            return;
                        }
                        if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_DELEVERY_TRAY_ORDER())) {
                            RobotDeliveryTrayOrderBody robotDeliveryTrayOrderBody = (RobotDeliveryTrayOrderBody) msgContext.getReqData();
                            if (robotDeliveryTrayOrderBody == null || robotDeliveryTrayOrderBody.getOrders() == null) {
                                return;
                            }
                            ArrayList arrayList6 = (ArrayList) RobotOpenApiManager.access$getRemoteApiProvidersMap$p(RobotOpenApiManager.INSTANCE).get(RobotDeliveryTrayOrdersProvider.class.getName());
                            if (arrayList6 != null) {
                                Iterator it6 = arrayList6.iterator();
                                Intrinsics.checkExpressionValueIsNotNull(it6, "it.iterator()");
                                if (it6.hasNext()) {
                                    Object next6 = it6.next();
                                    if (next6 == null) {
                                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotDeliveryTrayOrdersProvider");
                                    }
                                    RobotDeliveryTrayOrdersProvider robotDeliveryTrayOrdersProvider = (RobotDeliveryTrayOrdersProvider) next6;
                                    Object reqData5 = msgContext.getReqData();
                                    if (reqData5 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    z = robotDeliveryTrayOrdersProvider.setDeliveryTrayOrders((RobotDeliveryTrayOrderBody) reqData5).getSuccess();
                                }
                                Unit unit7 = Unit.INSTANCE;
                            }
                            RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(z, null, null, 6, null), null, 4, null);
                            Unit unit8 = Unit.INSTANCE;
                            return;
                        }
                        if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_GET_ROBOT_CURRENT_MAP())) {
                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RobotOpenApiManager$onOpenSdkEventListener$1$onEvent$10(msgContext, null), 2, null);
                            return;
                        }
                        RobotOpenApiManager robotOpenApiManager5 = RobotOpenApiManager.INSTANCE;
                        str2 = RobotOpenApiManager.TAG;
                        Pdlog.m3274e(str2, "onEvent : not adapter");
                        return;
                    }
                    ArrayList arrayList7 = (ArrayList) RobotOpenApiManager.access$getRemoteApiProvidersMap$p(RobotOpenApiManager.INSTANCE).get(RobotCancelCallProvider.class.getName());
                    if (arrayList7 != null) {
                        Iterator it7 = arrayList7.iterator();
                        Intrinsics.checkExpressionValueIsNotNull(it7, "it.iterator()");
                        if (it7.hasNext()) {
                            Object next7 = it7.next();
                            if (next7 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotCancelCallProvider");
                            }
                            RobotCancelCallProvider robotCancelCallProvider = (RobotCancelCallProvider) next7;
                            if (robotCancelCallProvider == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotCancelCallProvider");
                            }
                            RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, robotCancelCallProvider.cancelCallTask(msgContext), null, 4, null);
                        }
                        Unit unit9 = Unit.INSTANCE;
                        return;
                    }
                    return;
                }
                ArrayList arrayList8 = (ArrayList) RobotOpenApiManager.access$getRemoteApiProvidersMap$p(RobotOpenApiManager.INSTANCE).get(RobotCallProvider.class.getName());
                if (arrayList8 != null) {
                    Iterator it8 = arrayList8.iterator();
                    Intrinsics.checkExpressionValueIsNotNull(it8, "it.iterator()");
                    if (it8.hasNext()) {
                        Object next8 = it8.next();
                        if (next8 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotCallProvider");
                        }
                        RobotCallProvider robotCallProvider = (RobotCallProvider) next8;
                        if (robotCallProvider == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotCallProvider");
                        }
                        RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, robotCallProvider.addCallTask(msgContext), null, 4, null);
                    }
                    Unit unit10 = Unit.INSTANCE;
                    return;
                }
                return;
            }
            ArrayList arrayList9 = (ArrayList) RobotOpenApiManager.access$getRemoteApiProvidersMap$p(RobotOpenApiManager.INSTANCE).get(RobotStatusProvider.class.getName());
            if (arrayList9 != null) {
                Iterator it9 = arrayList9.iterator();
                Intrinsics.checkExpressionValueIsNotNull(it9, "it.iterator()");
                while (it9.hasNext()) {
                    Object next9 = it9.next();
                    if (next9 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotStatusProvider");
                    }
                    RobotStatusProvider robotStatusProvider = (RobotStatusProvider) next9;
                    if (robotStatusProvider != null) {
                        RespRobotStateBody robotState = robotStatusProvider.getRobotState();
                        RobotOpenApiManager robotOpenApiManager6 = RobotOpenApiManager.INSTANCE;
                        str5 = RobotOpenApiManager.TAG;
                        Pdlog.m3273d(str5, robotState);
                        if (robotState != null) {
                            RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, robotState, null, 4, null);
                            r23 = true;
                        } else {
                            r23 = false;
                        }
                        if (r23 != false) {
                            break;
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotStatusProvider");
                    }
                }
                Unit unit11 = Unit.INSTANCE;
            }
        }
    };

    private RobotOpenApiManager() {
    }

    public static final /* synthetic */ HashMap access$getRemoteApiProvidersMap$p(RobotOpenApiManager robotOpenApiManager) {
        return remoteApiProvidersMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final /* synthetic */ <T extends BaseRemoteApiProvider> void resp(Function1<? super T, Boolean> call) {
        HashMap access$getRemoteApiProvidersMap$p = access$getRemoteApiProvidersMap$p(this);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        ArrayList arrayList = (ArrayList) access$getRemoteApiProvidersMap$p.get(BaseRemoteApiProvider.class.getName());
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            Intrinsics.checkExpressionValueIsNotNull(it, "it.iterator()");
            while (it.hasNext()) {
                Object next = it.next();
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
                if (call.invoke((BaseRemoteApiProvider) next).booleanValue()) {
                    return;
                }
            }
        }
    }

    public final ArrayList<Destination> getAllRobotDes$module_robot_open_api_release() {
        ArrayList<BaseRemoteApiProvider> arrayList = remoteApiProvidersMap.get(RobotGetDestinationsProvider.class.getName());
        BaseRemoteApiProvider baseRemoteApiProvider = arrayList != null ? (BaseRemoteApiProvider) CollectionsKt.firstOrNull((List) arrayList) : null;
        if (baseRemoteApiProvider instanceof RobotGetDestinationsProvider) {
            return ((RobotGetDestinationsProvider) baseRemoteApiProvider).getDestinations(null).getDestinations();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasProvider(String key) {
        if (remoteApiProvidersMap.containsKey(key)) {
            ArrayList<BaseRemoteApiProvider> arrayList = remoteApiProvidersMap.get(key);
            if (!(arrayList == null || arrayList.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    public final void init(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Pdlog.m3273d(TAG, "init " + context2);
        context = context2;
        RobotOpenHelper.INSTANCE.init$module_robot_open_api_release(context2, onOpenSdkEventListener);
    }

    public final synchronized void addRemoteApiProviders(BaseRemoteApiProvider p) {
        Intrinsics.checkParameterIsNotNull(p, "p");
        Class<?> cls = p.getClass();
        if (cls == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<com.pudutech.robot.module.openapi.remoteapi.BaseRemoteApiProvider>");
        }
        Class<?> cls2 = cls.getInterfaces()[0];
        Intrinsics.checkExpressionValueIsNotNull(cls2, "(p.javaClass as Class).interfaces[0]");
        String name = cls2.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "(p.javaClass as Class).interfaces[0].name");
        if (remoteApiProvidersMap.containsKey(name)) {
            ArrayList<BaseRemoteApiProvider> arrayList = remoteApiProvidersMap.get(name);
            if (arrayList == null) {
                Intrinsics.throwNpe();
            }
            if (!arrayList.contains(p)) {
                arrayList.add(p);
            }
        } else {
            remoteApiProvidersMap.put(name, CollectionsKt.arrayListOf(p));
        }
    }

    public final synchronized void removeRemoteApiProviders(BaseRemoteApiProvider p) {
        Intrinsics.checkParameterIsNotNull(p, "p");
        Class<?> cls = p.getClass();
        if (cls == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<com.pudutech.robot.module.openapi.remoteapi.BaseRemoteApiProvider>");
        }
        Class<?> cls2 = cls.getInterfaces()[0];
        Intrinsics.checkExpressionValueIsNotNull(cls2, "(p.javaClass as Class).interfaces[0]");
        String name = cls2.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "(p.javaClass as Class).interfaces[0].name");
        ArrayList<BaseRemoteApiProvider> arrayList = remoteApiProvidersMap.get(name);
        if (arrayList != null) {
            arrayList.remove(p);
        }
    }

    public final boolean getSwitch() {
        return RobotOpenHelper.INSTANCE.isOpen$module_robot_open_api_release();
    }

    public final void addConnectListener(IRemoteConnectStateListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "addConnectListener : l = " + l + "; ");
        RobotOpenSdk.INSTANCE.addConnectStateListener(l);
    }

    public final void removeConnectListener(IRemoteConnectStateListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "removeConnectListener : l = " + l + "; ");
        RobotOpenSdk.INSTANCE.removeConnectStateListener(l);
    }

    public final RemoteConnectState getConnectStatus() {
        return RobotOpenSdk.INSTANCE.getRemoteConnectState();
    }

    public final void setSwitch(boolean sw) {
        Pdlog.m3273d(TAG, "setSwitch : sw = " + sw + "; ");
        if (sw) {
            RobotOpenHelper.INSTANCE.open$module_robot_open_api_release();
        } else {
            RobotOpenHelper.INSTANCE.close$module_robot_open_api_release();
        }
    }

    public final void genBind(IGenBindCodeCallBack callBack) {
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        if (RobotOpenSdk.INSTANCE.getRemoteConnectState() != RemoteConnectState.CONNECTED) {
            Pdlog.m3274e(TAG, "genBind : " + RobotOpenSdk.INSTANCE + ".remoteConnectState");
            callBack.onFailed(new IOException("disconnected"));
            return;
        }
        RobotOpenSdk.INSTANCE.startBind(callBack);
    }
}
