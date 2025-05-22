package com.pudutech.disinfect.baselib.network;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.google.api.client.json.Json;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.AliyunIotSecretReq;
import com.pudutech.disinfect.baselib.network.req.AliyunIotSecretResp;
import com.pudutech.disinfect.baselib.network.req.AuthorizationPackReq;
import com.pudutech.disinfect.baselib.network.req.CardItemReq;
import com.pudutech.disinfect.baselib.network.req.EditMapReq;
import com.pudutech.disinfect.baselib.network.req.EscapeReq;
import com.pudutech.disinfect.baselib.network.req.FeaturedReq;
import com.pudutech.disinfect.baselib.network.req.FileUpdateReq;
import com.pudutech.disinfect.baselib.network.req.InWorkContentReq;
import com.pudutech.disinfect.baselib.network.req.IotHostReq;
import com.pudutech.disinfect.baselib.network.req.MapInfoReq;
import com.pudutech.disinfect.baselib.network.req.MqtRegisterReq;
import com.pudutech.disinfect.baselib.network.req.PersonPortrait;
import com.pudutech.disinfect.baselib.network.req.PromotionsReq;
import com.pudutech.disinfect.baselib.network.req.ReqAdBrightnessCmd;
import com.pudutech.disinfect.baselib.network.req.ReqApkUrlUpdate;
import com.pudutech.disinfect.baselib.network.req.ReqCheckAuth;
import com.pudutech.disinfect.baselib.network.req.ReqGetMapList;
import com.pudutech.disinfect.baselib.network.req.ReqRobotCmd;
import com.pudutech.disinfect.baselib.network.req.ReqRobotDance;
import com.pudutech.disinfect.baselib.network.req.ReqRobotMac;
import com.pudutech.disinfect.baselib.network.req.ReqSetScreenLight;
import com.pudutech.disinfect.baselib.network.req.RobotActiveCodeReq;
import com.pudutech.disinfect.baselib.network.req.RobotActiveStatusReq;
import com.pudutech.disinfect.baselib.network.req.RobotLockedStatusReq;
import com.pudutech.disinfect.baselib.network.req.RobotMapReqV2;
import com.pudutech.disinfect.baselib.network.req.RobotPermissionReq;
import com.pudutech.disinfect.baselib.network.req.SolicitCustomerRate;
import com.pudutech.disinfect.baselib.network.req.SolicitReq;
import com.pudutech.disinfect.baselib.network.req.SolicitVoiceReq;
import com.pudutech.disinfect.baselib.network.req.SupportAbilityReq;
import com.pudutech.disinfect.baselib.network.req.SwitchEnvReq;
import com.pudutech.disinfect.baselib.network.req.TableNumberReq;
import com.pudutech.disinfect.baselib.network.req.TableReq;
import com.pudutech.disinfect.baselib.network.req.TtsInfoReq;
import com.pudutech.disinfect.baselib.network.req.UploadState;
import com.pudutech.disinfect.baselib.network.req.VoicePackInfoReq;
import com.pudutech.disinfect.baselib.network.req.ad.AdLaunchDown;
import com.pudutech.disinfect.baselib.network.req.ad.AdReqLanguageCmd;
import com.pudutech.disinfect.baselib.network.req.ad.AdReqNotifyDownload;
import com.pudutech.disinfect.baselib.network.req.ad.AdverListReq;
import com.pudutech.disinfect.baselib.network.response.AliyunIotSecretResp2;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.AuthorizationPackResp;
import com.pudutech.disinfect.baselib.network.response.CardBean;
import com.pudutech.disinfect.baselib.network.response.EscapeResBase;
import com.pudutech.disinfect.baselib.network.response.EscapeResData;
import com.pudutech.disinfect.baselib.network.response.FeatBean;
import com.pudutech.disinfect.baselib.network.response.FileUpdateResponse;
import com.pudutech.disinfect.baselib.network.response.G4CodeData;
import com.pudutech.disinfect.baselib.network.response.GatewayContainer;
import com.pudutech.disinfect.baselib.network.response.IotRegionResp;
import com.pudutech.disinfect.baselib.network.response.KeyBtnWithDestinationContainer;
import com.pudutech.disinfect.baselib.network.response.MapPermissionResponse;
import com.pudutech.disinfect.baselib.network.response.MapUploadResponse;
import com.pudutech.disinfect.baselib.network.response.ResAuth;
import com.pudutech.disinfect.baselib.network.response.ResPosterData;
import com.pudutech.disinfect.baselib.network.response.RespApkVersionData;
import com.pudutech.disinfect.baselib.network.response.RespResultData;
import com.pudutech.disinfect.baselib.network.response.RespStateData;
import com.pudutech.disinfect.baselib.network.response.RobotActiveStatusResp;
import com.pudutech.disinfect.baselib.network.response.RobotLockedStatusResp;
import com.pudutech.disinfect.baselib.network.response.RobotMapRespWrap;
import com.pudutech.disinfect.baselib.network.response.ShopBean;
import com.pudutech.disinfect.baselib.network.response.SwitchEnvResp;
import com.pudutech.disinfect.baselib.network.response.TableBean;
import com.pudutech.disinfect.baselib.network.response.TableGroupBean;
import com.pudutech.disinfect.baselib.network.response.TokenResp;
import com.pudutech.disinfect.baselib.network.response.TriggerBean;
import com.pudutech.disinfect.baselib.network.response.TtsInfoResp;
import com.pudutech.disinfect.baselib.network.response.TtsModelResp;
import com.pudutech.disinfect.baselib.network.response.VoiceBean;
import com.pudutech.disinfect.baselib.network.response.VoicePackCloudResponse;
import com.pudutech.disinfect.baselib.network.response.WorkContent;
import com.pudutech.disinfect.baselib.network.response.WorkPlan;
import com.pudutech.disinfect.baselib.network.response.ad.AdRespIotPushMsg;
import com.pudutech.disinfect.baselib.network.response.ad.AdverListResp;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.GatewayConstant;
import com.pudutech.pd_network.bean.PdHost;
import com.pudutech.pdmqtt.config.MqttRegisterInfo;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: NetWorkApiManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001:\u0005'()*+B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\"\u001a\u00020\u0004J\u0006\u0010#\u001a\u00020\nJ\u0006\u0010$\u001a\u00020\u000fJ\u0006\u0010%\u001a\u00020\u0019J\u0006\u0010&\u001a\u00020\u001eR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\b\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\b\u001a\u0004\b\u001f\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager;", "", "()V", "adControllerNet", "Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$AdPlatformService;", "getAdControllerNet", "()Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$AdPlatformService;", "adControllerNet$delegate", "Lkotlin/Lazy;", "cloudNet", "Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$CloudApiService;", "getCloudNet", "()Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$CloudApiService;", "cloudNet$delegate", "iotNet", "Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$IotApiService;", "getIotNet", "()Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$IotApiService;", "iotNet$delegate", "isTestServer", "", "()Z", "setTestServer", "(Z)V", "mapManagerNet", "Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$MapManagerService;", "getMapManagerNet", "()Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$MapManagerService;", "mapManagerNet$delegate", "normalNet", "Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$NormalApiService;", "getNormalNet", "()Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$NormalApiService;", "normalNet$delegate", "getAdControl", "getCloudApi", "getIotApi", "getMap", "getNormal", "AdPlatformService", "CloudApiService", "IotApiService", "MapManagerService", "NormalApiService", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NetWorkApiManager {
    private static boolean isTestServer;
    public static final NetWorkApiManager INSTANCE = new NetWorkApiManager();

    /* renamed from: cloudNet$delegate, reason: from kotlin metadata */
    private static final Lazy cloudNet = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<CloudApiService>() { // from class: com.pudutech.disinfect.baselib.network.NetWorkApiManager$cloudNet$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final NetWorkApiManager.CloudApiService invoke() {
            return (NetWorkApiManager.CloudApiService) PdNetworkManager.f10310INSTANCE.createService(NetWorkApiManager.CloudApiService.class);
        }
    });

    /* renamed from: iotNet$delegate, reason: from kotlin metadata */
    private static final Lazy iotNet = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<IotApiService>() { // from class: com.pudutech.disinfect.baselib.network.NetWorkApiManager$iotNet$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final NetWorkApiManager.IotApiService invoke() {
            return (NetWorkApiManager.IotApiService) PdNetworkManager.f10310INSTANCE.createService(NetWorkApiManager.IotApiService.class);
        }
    });

    /* renamed from: normalNet$delegate, reason: from kotlin metadata */
    private static final Lazy normalNet = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<NormalApiService>() { // from class: com.pudutech.disinfect.baselib.network.NetWorkApiManager$normalNet$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final NetWorkApiManager.NormalApiService invoke() {
            return (NetWorkApiManager.NormalApiService) PdNetworkManager.f10310INSTANCE.createService(NetWorkApiManager.NormalApiService.class);
        }
    });

    /* renamed from: adControllerNet$delegate, reason: from kotlin metadata */
    private static final Lazy adControllerNet = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<AdPlatformService>() { // from class: com.pudutech.disinfect.baselib.network.NetWorkApiManager$adControllerNet$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final NetWorkApiManager.AdPlatformService invoke() {
            return (NetWorkApiManager.AdPlatformService) PdNetworkManager.f10310INSTANCE.createService(NetWorkApiManager.AdPlatformService.class);
        }
    });

    /* renamed from: mapManagerNet$delegate, reason: from kotlin metadata */
    private static final Lazy mapManagerNet = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<MapManagerService>() { // from class: com.pudutech.disinfect.baselib.network.NetWorkApiManager$mapManagerNet$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final NetWorkApiManager.MapManagerService invoke() {
            return (NetWorkApiManager.MapManagerService) PdNetworkManager.f10310INSTANCE.createService(NetWorkApiManager.MapManagerService.class);
        }
    });

    private final AdPlatformService getAdControllerNet() {
        return (AdPlatformService) adControllerNet.getValue();
    }

    private final CloudApiService getCloudNet() {
        return (CloudApiService) cloudNet.getValue();
    }

    private final IotApiService getIotNet() {
        return (IotApiService) iotNet.getValue();
    }

    private final MapManagerService getMapManagerNet() {
        return (MapManagerService) mapManagerNet.getValue();
    }

    private final NormalApiService getNormalNet() {
        return (NormalApiService) normalNet.getValue();
    }

    private NetWorkApiManager() {
    }

    public final boolean isTestServer() {
        return isTestServer;
    }

    public final void setTestServer(boolean z) {
        isTestServer = z;
    }

    public final CloudApiService getCloudApi() {
        return getCloudNet();
    }

    public final IotApiService getIotApi() {
        return getIotNet();
    }

    public final NormalApiService getNormal() {
        return getNormalNet();
    }

    public final MapManagerService getMap() {
        return getMapManagerNet();
    }

    public final AdPlatformService getAdControl() {
        return getAdControllerNet();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: NetWorkApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ô\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u0081\u00012\u00020\u0001:\u0002\u0081\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J+\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\r\u001a\u00020\u000eH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0012H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J-\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0014\b\u0001\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0018H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J-\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00152\u0014\b\u0001\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0018H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J-\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00152\u0014\b\u0001\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0018H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\t2\b\b\u0001\u0010 \u001a\u00020!H§@ø\u0001\u0000¢\u0006\u0002\u0010\"J)\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0$0\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010%H§@ø\u0001\u0000¢\u0006\u0002\u0010&J#\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010)H§@ø\u0001\u0000¢\u0006\u0002\u0010*J!\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\t2\b\b\u0001\u0010\u0005\u001a\u00020-H§@ø\u0001\u0000¢\u0006\u0002\u0010.J#\u0010/\u001a\b\u0012\u0004\u0012\u0002000\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010%H§@ø\u0001\u0000¢\u0006\u0002\u0010&J#\u00101\u001a\b\u0012\u0004\u0012\u0002020\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u000103H§@ø\u0001\u0000¢\u0006\u0002\u00104J!\u00105\u001a\b\u0012\u0004\u0012\u0002060\t2\b\b\u0001\u0010\u0005\u001a\u000207H§@ø\u0001\u0000¢\u0006\u0002\u00108J#\u00109\u001a\b\u0012\u0004\u0012\u00020:0\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010;H§@ø\u0001\u0000¢\u0006\u0002\u0010<J#\u0010=\u001a\b\u0012\u0004\u0012\u0002020\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010>H§@ø\u0001\u0000¢\u0006\u0002\u0010?J'\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\t0\t2\b\b\u0001\u0010\u0005\u001a\u00020AH§@ø\u0001\u0000¢\u0006\u0002\u0010BJ!\u0010C\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0001\u0010\u0005\u001a\u00020\u000eH§@ø\u0001\u0000¢\u0006\u0002\u0010DJ!\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\t2\b\b\u0001\u0010\u0005\u001a\u00020GH§@ø\u0001\u0000¢\u0006\u0002\u0010HJ#\u0010I\u001a\b\u0012\u0004\u0012\u00020J0\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010KH§@ø\u0001\u0000¢\u0006\u0002\u0010LJ#\u0010M\u001a\b\u0012\u0004\u0012\u0002020\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010NH§@ø\u0001\u0000¢\u0006\u0002\u0010OJ)\u0010P\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0$0\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010RH§@ø\u0001\u0000¢\u0006\u0002\u0010SJ)\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020U0$0\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010VH§@ø\u0001\u0000¢\u0006\u0002\u0010WJ#\u0010X\u001a\b\u0012\u0004\u0012\u00020Y0\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010ZH§@ø\u0001\u0000¢\u0006\u0002\u0010[J)\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020]0$0\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010%H§@ø\u0001\u0000¢\u0006\u0002\u0010&J\u001b\u0010^\u001a\u00020_2\b\b\u0001\u0010\u0005\u001a\u00020`H§@ø\u0001\u0000¢\u0006\u0002\u0010aJ\u0011\u0010b\u001a\u00020cH§@ø\u0001\u0000¢\u0006\u0002\u0010dJ!\u0010e\u001a\b\u0012\u0004\u0012\u00020f0\t2\b\b\u0001\u0010\u0005\u001a\u00020gH§@ø\u0001\u0000¢\u0006\u0002\u0010hJ)\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0$0\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010%H§@ø\u0001\u0000¢\u0006\u0002\u0010&J!\u0010j\u001a\b\u0012\u0004\u0012\u00020k0\t2\b\b\u0001\u0010\r\u001a\u00020lH§@ø\u0001\u0000¢\u0006\u0002\u0010mJ!\u0010n\u001a\b\u0012\u0004\u0012\u00020o0\t2\b\b\u0001\u0010\r\u001a\u00020pH§@ø\u0001\u0000¢\u0006\u0002\u0010qJ#\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00110\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010sH§@ø\u0001\u0000¢\u0006\u0002\u0010tJ!\u0010u\u001a\b\u0012\u0004\u0012\u00020\u00110\t2\b\b\u0001\u0010\r\u001a\u00020vH§@ø\u0001\u0000¢\u0006\u0002\u0010wJ!\u0010x\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0001\u0010\u0005\u001a\u00020yH§@ø\u0001\u0000¢\u0006\u0002\u0010zJ#\u0010{\u001a\b\u0012\u0004\u0012\u00020\u00110\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010|H§@ø\u0001\u0000¢\u0006\u0002\u0010}J$\u0010~\u001a\b\u0012\u0004\u0012\u00020\u00110\t2\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u007fH§@ø\u0001\u0000¢\u0006\u0003\u0010\u0080\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0082\u0001"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$CloudApiService;", "", "checkHasEscape", "Lcom/pudutech/disinfect/baselib/network/response/EscapeResBase;", "Lcom/pudutech/disinfect/baselib/network/response/EscapeResData;", "reqData", "Lcom/pudutech/disinfect/baselib/network/req/EscapeReq;", "(Lcom/pudutech/disinfect/baselib/network/req/EscapeReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkRobotActiveStatusFromServer", "Lcom/pudutech/disinfect/baselib/network/response/ApiResponse;", "Lcom/pudutech/disinfect/baselib/network/response/RobotActiveStatusResp;", "url", "", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/disinfect/baselib/network/req/RobotActiveStatusReq;", "(Ljava/lang/String;Lcom/pudutech/disinfect/baselib/network/req/RobotActiveStatusReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "editMap", "Lcom/pudutech/disinfect/baselib/network/response/RespResultData;", "Lcom/pudutech/disinfect/baselib/network/req/EditMapReq;", "(Lcom/pudutech/disinfect/baselib/network/req/EditMapReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetch4GCode", "Lcom/pudutech/pd_network/bean/BaseResponse;", "Lcom/pudutech/disinfect/baselib/network/response/G4CodeData;", MapElement.Key.MAP, "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchGatewayKyBtnList", "Lcom/pudutech/disinfect/baselib/network/response/KeyBtnWithDestinationContainer;", "fetchGatewayList", "Lcom/pudutech/disinfect/baselib/network/response/GatewayContainer;", "getAdverList", "Lcom/pudutech/disinfect/baselib/network/response/ad/AdverListResp;", "reqAd", "Lcom/pudutech/disinfect/baselib/network/req/ad/AdverListReq;", "(Lcom/pudutech/disinfect/baselib/network/req/ad/AdverListReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllMapName", "", "Lcom/pudutech/disinfect/baselib/network/req/CardItemReq;", "(Lcom/pudutech/disinfect/baselib/network/req/CardItemReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWorkList", "Lcom/pudutech/disinfect/baselib/network/response/WorkPlan;", "Lcom/pudutech/disinfect/baselib/network/req/ReqGetMapList;", "(Lcom/pudutech/disinfect/baselib/network/req/ReqGetMapList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAuthorizationPack", "Lcom/pudutech/disinfect/baselib/network/response/AuthorizationPackResp;", "Lcom/pudutech/disinfect/baselib/network/req/AuthorizationPackReq;", "(Lcom/pudutech/disinfect/baselib/network/req/AuthorizationPackReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCardItemInfo", "Lcom/pudutech/disinfect/baselib/network/response/CardBean;", "getFeaturedData", "Lcom/pudutech/disinfect/baselib/network/response/FeatBean;", "Lcom/pudutech/disinfect/baselib/network/req/FeaturedReq;", "(Lcom/pudutech/disinfect/baselib/network/req/FeaturedReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFileUpdate", "Lcom/pudutech/disinfect/baselib/network/response/FileUpdateResponse;", "Lcom/pudutech/disinfect/baselib/network/req/FileUpdateReq;", "(Lcom/pudutech/disinfect/baselib/network/req/FileUpdateReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInWorkContent", "Lcom/pudutech/disinfect/baselib/network/response/WorkContent;", "Lcom/pudutech/disinfect/baselib/network/req/InWorkContentReq;", "(Lcom/pudutech/disinfect/baselib/network/req/InWorkContentReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPromotionsData", "Lcom/pudutech/disinfect/baselib/network/req/PromotionsReq;", "(Lcom/pudutech/disinfect/baselib/network/req/PromotionsReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRobotActiveCode", "Lcom/pudutech/disinfect/baselib/network/req/RobotActiveCodeReq;", "(Lcom/pudutech/disinfect/baselib/network/req/RobotActiveCodeReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRobotActiveStatus", "(Lcom/pudutech/disinfect/baselib/network/req/RobotActiveStatusReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRobotLockedStatus", "Lcom/pudutech/disinfect/baselib/network/response/RobotLockedStatusResp;", "Lcom/pudutech/disinfect/baselib/network/req/RobotLockedStatusReq;", "(Lcom/pudutech/disinfect/baselib/network/req/RobotLockedStatusReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getShopId", "Lcom/pudutech/disinfect/baselib/network/response/ShopBean;", "Lcom/pudutech/disinfect/baselib/network/req/IotHostReq;", "(Lcom/pudutech/disinfect/baselib/network/req/IotHostReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSolicitData", "Lcom/pudutech/disinfect/baselib/network/req/SolicitReq;", "(Lcom/pudutech/disinfect/baselib/network/req/SolicitReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSolicitInfo", "Lcom/pudutech/disinfect/baselib/network/response/VoiceBean;", "Lcom/pudutech/disinfect/baselib/network/req/SolicitVoiceReq;", "(Lcom/pudutech/disinfect/baselib/network/req/SolicitVoiceReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTableGroup", "Lcom/pudutech/disinfect/baselib/network/response/TableGroupBean;", "Lcom/pudutech/disinfect/baselib/network/req/TableReq;", "(Lcom/pudutech/disinfect/baselib/network/req/TableReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTableNumber", "Lcom/pudutech/disinfect/baselib/network/response/TableBean;", "Lcom/pudutech/disinfect/baselib/network/req/TableNumberReq;", "(Lcom/pudutech/disinfect/baselib/network/req/TableNumberReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTriggerInfo", "Lcom/pudutech/disinfect/baselib/network/response/TriggerBean;", "getTtsInfo", "Lcom/pudutech/disinfect/baselib/network/response/TtsInfoResp;", "Lcom/pudutech/disinfect/baselib/network/req/TtsInfoReq;", "(Lcom/pudutech/disinfect/baselib/network/req/TtsInfoReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTtsModel", "Lcom/pudutech/disinfect/baselib/network/response/TtsModelResp;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getVoicePackInfo", "Lcom/pudutech/disinfect/baselib/network/response/VoicePackCloudResponse;", "Lcom/pudutech/disinfect/baselib/network/req/VoicePackInfoReq;", "(Lcom/pudutech/disinfect/baselib/network/req/VoicePackInfoReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWakeUpinfo", "queryServerList", "Lcom/pudutech/disinfect/baselib/network/response/SwitchEnvResp;", "Lcom/pudutech/disinfect/baselib/network/req/SwitchEnvReq;", "(Lcom/pudutech/disinfect/baselib/network/req/SwitchEnvReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lcom/pudutech/pdmqtt/config/MqttRegisterInfo;", "Lcom/pudutech/disinfect/baselib/network/req/MqtRegisterReq;", "(Lcom/pudutech/disinfect/baselib/network/req/MqtRegisterReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setPersonPortrait", "Lcom/pudutech/disinfect/baselib/network/req/PersonPortrait;", "(Lcom/pudutech/disinfect/baselib/network/req/PersonPortrait;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSolicitCustomerRate", "Lcom/pudutech/disinfect/baselib/network/req/SolicitCustomerRate;", "(Lcom/pudutech/disinfect/baselib/network/req/SolicitCustomerRate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updataSupportAbility", "Lcom/pudutech/disinfect/baselib/network/req/SupportAbilityReq;", "(Lcom/pudutech/disinfect/baselib/network/req/SupportAbilityReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateRobotState", "Lcom/pudutech/disinfect/baselib/network/req/UploadState;", "(Lcom/pudutech/disinfect/baselib/network/req/UploadState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadMaps", "Lcom/pudutech/disinfect/baselib/network/req/MapInfoReq;", "(Lcom/pudutech/disinfect/baselib/network/req/MapInfoReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface CloudApiService {
        public static final String ACTIVE_STATE = "/api/common/robot_life/active/v2";

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final String FINDSHOP_ID_URL = "/api/common/shop/data/v1";
        public static final String TABLE_LIST_URL = "/api/v1/tableGroup/list";
        public static final String TABLE_URL = "/api/v1/queue/takeByGroup";

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/robot/avoid_collusion/v1")
        Object checkHasEscape(@Body EscapeReq escapeReq, Continuation<? super EscapeResBase<EscapeResData>> continuation);

        @POST
        Object checkRobotActiveStatusFromServer(@Url String str, @Body RobotActiveStatusReq robotActiveStatusReq, Continuation<? super ApiResponse<RobotActiveStatusResp>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/map/upload_map_table/v1")
        Object editMap(@Body EditMapReq editMapReq, Continuation<? super ApiResponse<RespResultData>> continuation);

        @Headers({PdHost.HK_CENTER_URL})
        @POST("/odm-hub-service/api/v1/secrete/build")
        Object fetch4GCode(@Body Map<String, String> map, Continuation<? super com.pudutech.pd_network.bean.BaseResponse<G4CodeData>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/odm/gateway/bind/call/get/v1")
        Object fetchGatewayKyBtnList(@Body Map<String, String> map, Continuation<? super com.pudutech.pd_network.bean.BaseResponse<KeyBtnWithDestinationContainer>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/odm/gateway/info/list/v1")
        Object fetchGatewayList(@Body Map<String, String> map, Continuation<? super com.pudutech.pd_network.bean.BaseResponse<GatewayContainer>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/business/ad/list/v1")
        Object getAdverList(@Body AdverListReq adverListReq, Continuation<? super ApiResponse<AdverListResp>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/map/map_names/v1")
        Object getAllMapName(@Body CardItemReq cardItemReq, Continuation<? super ApiResponse<List<String>>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/kettybot/timed_task/list/v1")
        Object getAllWorkList(@Body ReqGetMapList reqGetMapList, Continuation<? super ApiResponse<WorkPlan>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/software/func_upg/v2")
        Object getAuthorizationPack(@Body AuthorizationPackReq authorizationPackReq, Continuation<? super ApiResponse<AuthorizationPackResp>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/touch_screen/gongge/v1")
        Object getCardItemInfo(@Body CardItemReq cardItemReq, Continuation<? super ApiResponse<CardBean>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/kettybot/robot/dishesList/v1")
        Object getFeaturedData(@Body FeaturedReq featuredReq, Continuation<? super ApiResponse<FeatBean>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/file_upgrade/file/list/v1")
        Object getFileUpdate(@Body FileUpdateReq fileUpdateReq, Continuation<? super ApiResponse<FileUpdateResponse>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/voice/text/info/v1")
        Object getInWorkContent(@Body InWorkContentReq inWorkContentReq, Continuation<? super ApiResponse<WorkContent>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/kettybot/couponList/v1")
        Object getPromotionsData(@Body PromotionsReq promotionsReq, Continuation<? super ApiResponse<FeatBean>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/robot_life/active_code/v1")
        Object getRobotActiveCode(@Body RobotActiveCodeReq robotActiveCodeReq, Continuation<? super ApiResponse<ApiResponse<Object>>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/robot_life/active/v2")
        Object getRobotActiveStatus(@Body RobotActiveStatusReq robotActiveStatusReq, Continuation<? super ApiResponse<RobotActiveStatusResp>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/robot_life/lock_state/v2")
        Object getRobotLockedStatus(@Body RobotLockedStatusReq robotLockedStatusReq, Continuation<? super ApiResponse<RobotLockedStatusResp>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/shop/data/v1")
        Object getShopId(@Body IotHostReq iotHostReq, Continuation<? super ApiResponse<ShopBean>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/trigger/answer/v1")
        Object getSolicitData(@Body SolicitReq solicitReq, Continuation<? super ApiResponse<FeatBean>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/kettybot/solicit/v1")
        Object getSolicitInfo(@Body SolicitVoiceReq solicitVoiceReq, Continuation<? super ApiResponse<List<VoiceBean>>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/v1/tableGroup/list")
        Object getTableGroup(@Body TableReq tableReq, Continuation<? super ApiResponse<List<TableGroupBean>>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/v1/queue/takeByGroup")
        Object getTableNumber(@Body TableNumberReq tableNumberReq, Continuation<? super ApiResponse<TableBean>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/trigger/list/v2")
        Object getTriggerInfo(@Body CardItemReq cardItemReq, Continuation<? super ApiResponse<List<TriggerBean>>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/voice/text/list/v1")
        Object getTtsInfo(@Body TtsInfoReq ttsInfoReq, Continuation<? super TtsInfoResp> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/voice/config/v1")
        Object getTtsModel(Continuation<? super TtsModelResp> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/voice/custom/list/v1")
        Object getVoicePackInfo(@Body VoicePackInfoReq voicePackInfoReq, Continuation<? super ApiResponse<VoicePackCloudResponse>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/trigger/list/v1")
        Object getWakeUpinfo(@Body CardItemReq cardItemReq, Continuation<? super ApiResponse<List<String>>> continuation);

        @Headers({PdHost.HK_CENTER_URL})
        @POST("/api/common/env/query/v1")
        Object queryServerList(@Body SwitchEnvReq switchEnvReq, Continuation<? super ApiResponse<SwitchEnvResp>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/mqtt/robot/register/v1")
        Object register(@Body MqtRegisterReq mqtRegisterReq, Continuation<? super ApiResponse<MqttRegisterInfo>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/ad/portrait/report/v1")
        Object setPersonPortrait(@Body PersonPortrait personPortrait, Continuation<? super ApiResponse<RespResultData>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/ad/solicitation/report/vi")
        Object setSolicitCustomerRate(@Body SolicitCustomerRate solicitCustomerRate, Continuation<? super ApiResponse<RespResultData>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/abilities/update/v1")
        Object updataSupportAbility(@Body SupportAbilityReq supportAbilityReq, Continuation<? super ApiResponse<Object>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/dudu_life/robot/status")
        Object updateRobotState(@Body UploadState uploadState, Continuation<? super ApiResponse<RespResultData>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/map/upload_map_table_list/v1")
        Object uploadMaps(@Body MapInfoReq mapInfoReq, Continuation<? super ApiResponse<RespResultData>> continuation);

        /* JADX WARN: Classes with same name are omitted:
          classes3.dex
         */
        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\"\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$CloudApiService$Companion;", "", "()V", "ACTIVE_CODE", "", "ACTIVE_STATE", "API_ADVER_List", "AUTH_PACK", "CARD_ITEM_URL", "CARD_WAKE_URL", "COUPON_LIST_URL", "DISH_URL", "EDIT_MAP", "FETCH_4G_CODE", "FETCH_GATEWAY_KY_BTN_LIST", "FETCH_GATEWAY_LIST", "FILE_UPGRADE", "FINDSHOP_ID_URL", "GET_ALL_MAP", "GET_WORK_PLAN_LIST", "LOCKED_STATUS", "LOCK_URL_V2", "PERSON_PORTRAIT", "QUERY_SERVER_LIST", GatewayConstant.ROBOT_MQTT, "ROBOT_STATE", "SOLICITATION_REPORT", "SOLICIT_URL", "SOLICIT_VOICE_URL", "SUPPORT_ABILITY", "TABLE_LIST_URL", "TABLE_URL", "TRIGGER_URL", "TTS_VOICE_INFO", "TTS_VOICE_MODEL", "TTS_VOICE_MOVE_TO", "UPLOAD_MAP", "VOICE_CUSTOM", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final String ACTIVE_CODE = "/api/common/robot_life/active_code/v1";
            public static final String ACTIVE_STATE = "/api/common/robot_life/active/v2";
            private static final String API_ADVER_List = "/api/business/ad/list/v1";
            private static final String AUTH_PACK = "/api/common/software/func_upg/v2";
            private static final String CARD_ITEM_URL = "/api/common/touch_screen/gongge/v1";
            private static final String CARD_WAKE_URL = "/api/common/trigger/list/v1";
            private static final String COUPON_LIST_URL = "/api/kettybot/couponList/v1";
            private static final String DISH_URL = "/api/kettybot/robot/dishesList/v1";
            private static final String EDIT_MAP = "/api/common/map/upload_map_table/v1";
            private static final String FETCH_4G_CODE = "/odm-hub-service/api/v1/secrete/build";
            private static final String FETCH_GATEWAY_KY_BTN_LIST = "/api/odm/gateway/bind/call/get/v1";
            private static final String FETCH_GATEWAY_LIST = "/api/odm/gateway/info/list/v1";
            private static final String FILE_UPGRADE = "/api/common/file_upgrade/file/list/v1";
            public static final String FINDSHOP_ID_URL = "/api/common/shop/data/v1";
            private static final String GET_ALL_MAP = "/api/common/map/map_names/v1";
            private static final String GET_WORK_PLAN_LIST = "/api/kettybot/timed_task/list/v1";
            private static final String LOCKED_STATUS = "/api/common/robot_life/lock_state/v2";
            private static final String LOCK_URL_V2 = "/api/common/robot/avoid_collusion/v1";
            private static final String PERSON_PORTRAIT = "/api/ad/portrait/report/v1";
            private static final String QUERY_SERVER_LIST = "/api/common/env/query/v1";
            private static final String ROBOT_MQTT = "/api/mqtt/robot/register/v1";
            private static final String ROBOT_STATE = "/api/dudu_life/robot/status";
            private static final String SOLICITATION_REPORT = "/api/ad/solicitation/report/vi";
            private static final String SOLICIT_URL = "/api/common/trigger/answer/v1";
            private static final String SOLICIT_VOICE_URL = "/api/kettybot/solicit/v1";
            private static final String SUPPORT_ABILITY = "/api/common/abilities/update/v1";
            public static final String TABLE_LIST_URL = "/api/v1/tableGroup/list";
            public static final String TABLE_URL = "/api/v1/queue/takeByGroup";
            private static final String TRIGGER_URL = "/api/common/trigger/list/v2";
            private static final String TTS_VOICE_INFO = "/api/common/voice/text/list/v1";
            private static final String TTS_VOICE_MODEL = "/api/common/voice/config/v1";
            private static final String TTS_VOICE_MOVE_TO = "/api/common/voice/text/info/v1";
            private static final String UPLOAD_MAP = "/api/common/map/upload_map_table_list/v1";
            private static final String VOICE_CUSTOM = "/api/common/voice/custom/list/v1";

            private Companion() {
            }
        }

        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes3.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ Object getTableNumber$default(CloudApiService cloudApiService, TableNumberReq tableNumberReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTableNumber");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/v1/queue/takeByGroup" : "https://rmp.pudutech.com/api/v1/queue/takeByGroup";
                }
                return cloudApiService.getTableNumber(tableNumberReq, str, continuation);
            }

            public static /* synthetic */ Object getTableGroup$default(CloudApiService cloudApiService, TableReq tableReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTableGroup");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/v1/tableGroup/list" : "https://rmp.pudutech.com/api/v1/tableGroup/list";
                }
                return cloudApiService.getTableGroup(tableReq, str, continuation);
            }

            public static /* synthetic */ Object getShopId$default(CloudApiService cloudApiService, IotHostReq iotHostReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getShopId");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/business/shopData" : "https://rmp.pudutech.com/api/business/shopData";
                }
                return cloudApiService.getShopId(iotHostReq, str, continuation);
            }

            public static /* synthetic */ Object getPromotionsData$default(CloudApiService cloudApiService, PromotionsReq promotionsReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPromotionsData");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/business/coupon/list" : "https://rmp.pudutech.com/api/business/coupon/list";
                }
                return cloudApiService.getPromotionsData(promotionsReq, str, continuation);
            }

            public static /* synthetic */ Object setPersonPortrait$default(CloudApiService cloudApiService, PersonPortrait personPortrait, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setPersonPortrait");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/ad/portrait/report/v1" : "https://rmp.pudutech.com/api/ad/portrait/report/v1";
                }
                return cloudApiService.setPersonPortrait(personPortrait, str, continuation);
            }

            public static /* synthetic */ Object getFeaturedData$default(CloudApiService cloudApiService, FeaturedReq featuredReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFeaturedData");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/business/dishes/list" : "https://rmp.pudutech.com/api/business/dishes/list";
                }
                return cloudApiService.getFeaturedData(featuredReq, str, continuation);
            }

            public static /* synthetic */ Object getServerInfo$default(CloudApiService cloudApiService, TableNumberReq tableNumberReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getServerInfo");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/v1/queue/takeByGroup" : "https://queue-call.pudutech.com/api/v1/queue/takeByGroup";
                }
                return cloudApiService.getServerInfo(tableNumberReq, str, continuation);
            }

            public static /* synthetic */ Object getRobotActiveStatus$default(CloudApiService cloudApiService, RobotActiveStatusReq robotActiveStatusReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRobotActiveStatus");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/v5/report" : "https://rmp.pudutech.com/api/v5/report";
                }
                return cloudApiService.getRobotActiveStatus(robotActiveStatusReq, str, continuation);
            }

            public static /* synthetic */ Object getRobotActive$default(CloudApiService cloudApiService, RobotActiveCodeReq robotActiveCodeReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRobotActive");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/v5/report" : "https://rmp.pudutech.com/api/v5/report";
                }
                return cloudApiService.getRobotActive(robotActiveCodeReq, str, continuation);
            }

            public static /* synthetic */ Object getVoicePackInfo$default(CloudApiService cloudApiService, VoicePackInfoReq voicePackInfoReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getVoicePackInfo");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/v3/report" : "https://rmp.pudutech.com/api/v3/report";
                }
                return cloudApiService.getVoicePackInfo(voicePackInfoReq, str, continuation);
            }

            public static /* synthetic */ Object getFileUpdate$default(CloudApiService cloudApiService, FileUpdateReq fileUpdateReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFileUpdate");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://rmp-test.pudutech.com/api/v3/report" : "https://rmp.pudutech.com/api/v3/report";
                }
                return cloudApiService.getFileUpdate(fileUpdateReq, str, continuation);
            }
        }
    }

    /* compiled from: NetWorkApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ3\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0003\u0010\b\u001a\u00020\tH§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ-\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\r2\b\b\u0003\u0010\b\u001a\u00020\tH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$SolicitApiService;", "", "getTableGroup", "Lcom/pudutech/disinfect/baselib/network/response/ApiResponse;", "", "Lcom/pudutech/disinfect/baselib/network/response/TableGroupBean;", "reqData", "Lcom/pudutech/disinfect/baselib/network/req/TableReq;", "url", "", "(Lcom/pudutech/disinfect/baselib/network/req/TableReq;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTableNumber", "Lcom/pudutech/disinfect/baselib/network/response/TableBean;", "Lcom/pudutech/disinfect/baselib/network/req/TableNumberReq;", "(Lcom/pudutech/disinfect/baselib/network/req/TableNumberReq;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public interface SolicitApiService {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final String DEBUG_URL_SOLICIT = "https://business.pudutech.com";
        public static final String SOLICIT_SECRET_URL = "https://queue-call.pudutech.com";
        public static final String SOLICIT_SECRET_URL_TEST = "https://queue-call-test.pudutech.com";
        public static final String TABLE_LIST_URL = "/api/v1/tableGroup/list";
        public static final String TABLE_URL = "/api/v1/queue/takeByGroup";

        @POST
        Object getTableGroup(@Body TableReq tableReq, @Url String str, Continuation<? super ApiResponse<List<TableGroupBean>>> continuation);

        @POST
        Object getTableNumber(@Body TableNumberReq tableNumberReq, @Url String str, Continuation<? super ApiResponse<TableBean>> continuation);

        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$SolicitApiService$Companion;", "", "()V", "DEBUG_URL_SOLICIT", "", "SOLICIT_SECRET_URL", "SOLICIT_SECRET_URL_TEST", "TABLE_LIST_URL", "TABLE_URL", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes3.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final String DEBUG_URL_SOLICIT = "https://business.pudutech.com";
            public static final String SOLICIT_SECRET_URL = "https://queue-call.pudutech.com";
            public static final String SOLICIT_SECRET_URL_TEST = "https://queue-call-test.pudutech.com";
            public static final String TABLE_LIST_URL = "/api/v1/tableGroup/list";
            public static final String TABLE_URL = "/api/v1/queue/takeByGroup";

            private Companion() {
            }
        }

        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes3.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ Object getTableNumber$default(SolicitApiService solicitApiService, TableNumberReq tableNumberReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTableNumber");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://queue-call-test.pudutech.com/api/v1/queue/takeByGroup" : "https://queue-call.pudutech.com/api/v1/queue/takeByGroup";
                }
                return solicitApiService.getTableNumber(tableNumberReq, str, continuation);
            }

            public static /* synthetic */ Object getTableGroup$default(SolicitApiService solicitApiService, TableReq tableReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTableGroup");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "https://queue-call-test.pudutech.com/api/v1/tableGroup/list" : "https://queue-call.pudutech.com/api/v1/tableGroup/list";
                }
                return solicitApiService.getTableGroup(tableReq, str, continuation);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: NetWorkApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000 \t2\u00020\u0001:\u0001\tJ%\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007H§@ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$IotApiService;", "", "getAliyunIOTSecret", "Lcom/pudutech/disinfect/baselib/network/req/AliyunIotSecretResp;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/disinfect/baselib/network/req/AliyunIotSecretReq;", "url", "", "(Lcom/pudutech/disinfect/baselib/network/req/AliyunIotSecretReq;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface IotApiService {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final String IOT_SECRET_URL = "https://iot.api.pudutech.com/";
        public static final String IOT_SECRET_URL_TEST = "http://iot.api.pudutech.com:8010/";
        public static final String PATH = "iot/robot/register";

        @POST
        Object getAliyunIOTSecret(@Body AliyunIotSecretReq aliyunIotSecretReq, @Url String str, Continuation<? super AliyunIotSecretResp> continuation);

        /* JADX WARN: Classes with same name are omitted:
          classes3.dex
         */
        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$IotApiService$Companion;", "", "()V", "IOT_SECRET_URL", "", "IOT_SECRET_URL_TEST", "PATH", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final String IOT_SECRET_URL = "https://iot.api.pudutech.com/";
            public static final String IOT_SECRET_URL_TEST = "http://iot.api.pudutech.com:8010/";
            public static final String PATH = "iot/robot/register";

            private Companion() {
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes3.dex
         */
        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ Object getAliyunIOTSecret$default(IotApiService iotApiService, AliyunIotSecretReq aliyunIotSecretReq, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAliyunIOTSecret");
                }
                if ((i & 2) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "http://iot.api.pudutech.com:8010/iot/robot/register" : "https://iot.api.pudutech.com/iot/robot/register";
                }
                return iotApiService.getAliyunIOTSecret(aliyunIotSecretReq, str, continuation);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: NetWorkApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017J9\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ?\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u00052\b\b\u0003\u0010\u0010\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J+\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b2\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0014\u001a\u00020\u0015H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$NormalApiService;", "", "genToken", "Lcom/pudutech/disinfect/baselib/network/response/TokenResp;", "url", "", OAuth2Constants.GRANT_TYPE, "client_id", "client_secret", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAliyunIOTSecret", "Lcom/pudutech/disinfect/baselib/network/response/ApiResponse;", "Lcom/pudutech/disinfect/baselib/network/response/AliyunIotSecretResp2;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/disinfect/baselib/network/req/AliyunIotSecretReq;", "token", CMSAttributeTableGenerator.CONTENT_TYPE, "(Ljava/lang/String;Lcom/pudutech/disinfect/baselib/network/req/AliyunIotSecretReq;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEdgeHost", "Lcom/pudutech/disinfect/baselib/network/response/IotRegionResp;", "reqData", "Lcom/pudutech/disinfect/baselib/network/req/IotHostReq;", "(Ljava/lang/String;Lcom/pudutech/disinfect/baselib/network/req/IotHostReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface NormalApiService {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final String GET_EDGE_HOST_URL = "/api/v1/region/getRobotEndpoint";
        public static final String HUB_URL = "https://rop-hub.pudutech.com";
        public static final String HUB_URL_TEST = "http://rop-hub-test.pudutech.com";

        @FormUrlEncoded
        @POST
        Object genToken(@Url String str, @Field("grant_type") String str2, @Field("client_id") String str3, @Field("client_secret") String str4, Continuation<? super TokenResp> continuation);

        @POST
        Object getAliyunIOTSecret(@Url String str, @Body AliyunIotSecretReq aliyunIotSecretReq, @Header("Authorization") String str2, @Header("Content-Type") String str3, Continuation<? super ApiResponse<AliyunIotSecretResp2>> continuation);

        @POST
        Object getEdgeHost(@Url String str, @Body IotHostReq iotHostReq, Continuation<? super ApiResponse<IotRegionResp>> continuation);

        /* JADX WARN: Classes with same name are omitted:
          classes3.dex
         */
        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$NormalApiService$Companion;", "", "()V", "GET_EDGE_HOST_URL", "", "HUB_URL", "HUB_URL_TEST", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final String GET_EDGE_HOST_URL = "/api/v1/region/getRobotEndpoint";
            public static final String HUB_URL = "https://rop-hub.pudutech.com";
            public static final String HUB_URL_TEST = "http://rop-hub-test.pudutech.com";

            private Companion() {
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes3.dex
         */
        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ Object getEdgeHost$default(NormalApiService normalApiService, String str, IotHostReq iotHostReq, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getEdgeHost");
                }
                if ((i & 1) != 0) {
                    str = NetWorkApiManager.INSTANCE.isTestServer() ? "http://rop-hub-test.pudutech.com/api/v1/region/getRobotEndpoint" : "https://rop-hub.pudutech.com/api/v1/region/getRobotEndpoint";
                }
                return normalApiService.getEdgeHost(str, iotHostReq, continuation);
            }

            public static /* synthetic */ Object getAliyunIOTSecret$default(NormalApiService normalApiService, String str, AliyunIotSecretReq aliyunIotSecretReq, String str2, String str3, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAliyunIOTSecret");
                }
                if ((i & 8) != 0) {
                    str3 = Json.MEDIA_TYPE;
                }
                return normalApiService.getAliyunIOTSecret(str, aliyunIotSecretReq, str2, str3, continuation);
            }

            public static /* synthetic */ Object genToken$default(NormalApiService normalApiService, String str, String str2, String str3, String str4, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: genToken");
                }
                if ((i & 2) != 0) {
                    str2 = "client_credentials";
                }
                String str5 = str2;
                if ((i & 4) != 0) {
                    str3 = "thom";
                }
                String str6 = str3;
                if ((i & 8) != 0) {
                    str4 = "nightworld";
                }
                return normalApiService.genToken(str, str5, str6, str4, continuation);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: NetWorkApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 ,2\u00020\u0001:\u0001,J+\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ+\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u000b2\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\fJ!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ+\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00152\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J+\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00182\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J+\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u001b\u001a\u00020\u001c2\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ+\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u001f2\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010 J!\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00032\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ+\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020$2\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010%J+\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020'2\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010(J+\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020*2\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010+\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$AdPlatformService;", "", "adpAdLaunchDown", "Lcom/pudutech/disinfect/baselib/network/response/ApiResponse;", "Lcom/pudutech/disinfect/baselib/network/response/RespResultData;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/disinfect/baselib/network/req/ad/AdLaunchDown;", "url", "", "(Lcom/pudutech/disinfect/baselib/network/req/ad/AdLaunchDown;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "controlAdBrightness", "Lcom/pudutech/disinfect/baselib/network/req/ReqAdBrightnessCmd;", "(Lcom/pudutech/disinfect/baselib/network/req/ReqAdBrightnessCmd;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApkVersion", "Lcom/pudutech/disinfect/baselib/network/response/RespApkVersionData;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIotPushMsg", "Lcom/pudutech/disinfect/baselib/network/response/ad/AdRespIotPushMsg;", "getState", "Lcom/pudutech/disinfect/baselib/network/response/RespStateData;", "mediaCmd", "Lcom/pudutech/disinfect/baselib/network/req/ReqRobotCmd;", "(Lcom/pudutech/disinfect/baselib/network/req/ReqRobotCmd;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "notifyAdDownload", "Lcom/pudutech/disinfect/baselib/network/req/ad/AdReqNotifyDownload;", "(Lcom/pudutech/disinfect/baselib/network/req/ad/AdReqNotifyDownload;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postLanguage", "re", "Lcom/pudutech/disinfect/baselib/network/req/ad/AdReqLanguageCmd;", "(Lcom/pudutech/disinfect/baselib/network/req/ad/AdReqLanguageCmd;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reqRobotDance", "Lcom/pudutech/disinfect/baselib/network/req/ReqRobotDance;", "(Lcom/pudutech/disinfect/baselib/network/req/ReqRobotDance;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reqRobotPoster", "Lcom/pudutech/disinfect/baselib/network/response/ResPosterData;", "robotMac", "Lcom/pudutech/disinfect/baselib/network/req/ReqRobotMac;", "(Lcom/pudutech/disinfect/baselib/network/req/ReqRobotMac;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setScreenLight", "Lcom/pudutech/disinfect/baselib/network/req/ReqSetScreenLight;", "(Lcom/pudutech/disinfect/baselib/network/req/ReqSetScreenLight;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateApk", "Lcom/pudutech/disinfect/baselib/network/req/ReqApkUrlUpdate;", "(Lcom/pudutech/disinfect/baselib/network/req/ReqApkUrlUpdate;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface AdPlatformService {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @POST
        Object adpAdLaunchDown(@Body AdLaunchDown adLaunchDown, @Url String str, Continuation<? super ApiResponse<RespResultData>> continuation);

        @POST
        Object controlAdBrightness(@Body ReqAdBrightnessCmd reqAdBrightnessCmd, @Url String str, Continuation<? super ApiResponse<RespResultData>> continuation);

        @GET
        Object getApkVersion(@Url String str, Continuation<? super ApiResponse<RespApkVersionData>> continuation);

        @GET
        Object getIotPushMsg(@Url String str, Continuation<? super ApiResponse<AdRespIotPushMsg>> continuation);

        @GET
        Object getState(@Url String str, Continuation<? super ApiResponse<RespStateData>> continuation);

        @POST
        Object mediaCmd(@Body ReqRobotCmd reqRobotCmd, @Url String str, Continuation<? super ApiResponse<RespResultData>> continuation);

        @POST
        Object notifyAdDownload(@Body AdReqNotifyDownload adReqNotifyDownload, @Url String str, Continuation<? super ApiResponse<RespResultData>> continuation);

        @POST
        Object postLanguage(@Body AdReqLanguageCmd adReqLanguageCmd, @Url String str, Continuation<? super ApiResponse<RespResultData>> continuation);

        @POST
        Object reqRobotDance(@Body ReqRobotDance reqRobotDance, @Url String str, Continuation<? super ApiResponse<RespResultData>> continuation);

        @GET
        Object reqRobotPoster(@Url String str, Continuation<? super ApiResponse<ResPosterData>> continuation);

        @POST
        Object robotMac(@Body ReqRobotMac reqRobotMac, @Url String str, Continuation<? super ApiResponse<RespResultData>> continuation);

        @POST
        Object setScreenLight(@Body ReqSetScreenLight reqSetScreenLight, @Url String str, Continuation<? super ApiResponse<RespResultData>> continuation);

        @Headers({"CONNECT_TIMEOUT:120000", "READ_TIMEOUT:120000", "WRITE_TIMEOUT:120000"})
        @POST
        Object updateApk(@Body ReqApkUrlUpdate reqApkUrlUpdate, @Url String str, Continuation<? super ApiResponse<RespResultData>> continuation);

        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$AdPlatformService$Companion;", "", "()V", "host", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            public final String host() {
                return "http://10.10.11.4:16555";
            }

            private Companion() {
            }
        }

        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ Object getApkVersion$default(AdPlatformService adPlatformService, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getApkVersion");
                }
                if ((i & 1) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/apk/version";
                }
                return adPlatformService.getApkVersion(str, continuation);
            }

            public static /* synthetic */ Object updateApk$default(AdPlatformService adPlatformService, ReqApkUrlUpdate reqApkUrlUpdate, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateApk");
                }
                if ((i & 2) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/apk/update";
                }
                return adPlatformService.updateApk(reqApkUrlUpdate, str, continuation);
            }

            public static /* synthetic */ Object getState$default(AdPlatformService adPlatformService, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getState");
                }
                if ((i & 1) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/state";
                }
                return adPlatformService.getState(str, continuation);
            }

            public static /* synthetic */ Object setScreenLight$default(AdPlatformService adPlatformService, ReqSetScreenLight reqSetScreenLight, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setScreenLight");
                }
                if ((i & 2) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/screen/light";
                }
                return adPlatformService.setScreenLight(reqSetScreenLight, str, continuation);
            }

            public static /* synthetic */ Object reqRobotDance$default(AdPlatformService adPlatformService, ReqRobotDance reqRobotDance, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reqRobotDance");
                }
                if ((i & 2) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/robot/dance";
                }
                return adPlatformService.reqRobotDance(reqRobotDance, str, continuation);
            }

            public static /* synthetic */ Object robotMac$default(AdPlatformService adPlatformService, ReqRobotMac reqRobotMac, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: robotMac");
                }
                if ((i & 2) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/robot/mac";
                }
                return adPlatformService.robotMac(reqRobotMac, str, continuation);
            }

            public static /* synthetic */ Object reqRobotPoster$default(AdPlatformService adPlatformService, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reqRobotPoster");
                }
                if ((i & 1) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/recommend/poster";
                }
                return adPlatformService.reqRobotPoster(str, continuation);
            }

            public static /* synthetic */ Object mediaCmd$default(AdPlatformService adPlatformService, ReqRobotCmd reqRobotCmd, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: mediaCmd");
                }
                if ((i & 2) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "api/media/receive/cmd";
                }
                return adPlatformService.mediaCmd(reqRobotCmd, str, continuation);
            }

            public static /* synthetic */ Object controlAdBrightness$default(AdPlatformService adPlatformService, ReqAdBrightnessCmd reqAdBrightnessCmd, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: controlAdBrightness");
                }
                if ((i & 2) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/ad/brightness";
                }
                return adPlatformService.controlAdBrightness(reqAdBrightnessCmd, str, continuation);
            }

            public static /* synthetic */ Object postLanguage$default(AdPlatformService adPlatformService, AdReqLanguageCmd adReqLanguageCmd, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: postLanguage");
                }
                if ((i & 2) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/post/language";
                }
                return adPlatformService.postLanguage(adReqLanguageCmd, str, continuation);
            }

            public static /* synthetic */ Object notifyAdDownload$default(AdPlatformService adPlatformService, AdReqNotifyDownload adReqNotifyDownload, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: notifyAdDownload");
                }
                if ((i & 2) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/post/download/notify";
                }
                return adPlatformService.notifyAdDownload(adReqNotifyDownload, str, continuation);
            }

            public static /* synthetic */ Object getIotPushMsg$default(AdPlatformService adPlatformService, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getIotPushMsg");
                }
                if ((i & 1) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/get/iot/push";
                }
                return adPlatformService.getIotPushMsg(str, continuation);
            }

            public static /* synthetic */ Object adpAdLaunchDown$default(AdPlatformService adPlatformService, AdLaunchDown adLaunchDown, String str, Continuation continuation, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: adpAdLaunchDown");
                }
                if ((i & 2) != 0) {
                    str = AdPlatformService.INSTANCE.host() + "/api/post/adp/app/launcher/down";
                }
                return adPlatformService.adpAdLaunchDown(adLaunchDown, str, continuation);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: NetWorkApiManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0004\u001a\u00020\tH§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0001\u0010\u0004\u001a\u00020\u000eH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\b\b\u0001\u0010\u0004\u001a\u00020\u0012H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u001b\u0010\u0014\u001a\u00020\u00152\b\b\u0001\u0010\u0004\u001a\u00020\u0016H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$MapManagerService;", "", "checkAuth", "Lcom/pudutech/disinfect/baselib/network/response/ResAuth;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/disinfect/baselib/network/req/ReqCheckAuth;", "(Lcom/pudutech/disinfect/baselib/network/req/ReqCheckAuth;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkPermissionCode", "Lcom/pudutech/disinfect/baselib/network/response/MapPermissionResponse;", "Lcom/pudutech/disinfect/baselib/network/req/RobotPermissionReq;", "(Lcom/pudutech/disinfect/baselib/network/req/RobotPermissionReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMapList", "Lcom/pudutech/disinfect/baselib/network/response/ApiResponse;", "Lcom/pudutech/disinfect/baselib/network/response/RobotMapRespWrap;", "Lcom/pudutech/disinfect/baselib/network/req/ReqGetMapList;", "(Lcom/pudutech/disinfect/baselib/network/req/ReqGetMapList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "switchMap", "", "Lcom/pudutech/disinfect/baselib/network/req/ReqRobotMapUse;", "(Lcom/pudutech/disinfect/baselib/network/req/ReqRobotMapUse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadMap", "Lcom/pudutech/disinfect/baselib/network/response/MapUploadResponse;", "Lcom/pudutech/disinfect/baselib/network/req/RobotMapReqV2;", "(Lcom/pudutech/disinfect/baselib/network/req/RobotMapReqV2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface MapManagerService {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @Headers({PdHost.ROBOT_BIZ})
        @POST("api/kettybot/api/mac/get/v1")
        Object checkAuth(@Body ReqCheckAuth reqCheckAuth, Continuation<? super ResAuth> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/map/check_permssion/v1")
        Object checkPermissionCode(@Body RobotPermissionReq robotPermissionReq, Continuation<? super MapPermissionResponse> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/map/map_list/v1")
        Object getMapList(@Body ReqGetMapList reqGetMapList, Continuation<? super ApiResponse<RobotMapRespWrap>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/map/map_switch/v1")
        Object switchMap(@Body com.pudutech.disinfect.baselib.network.req.ReqRobotMac reqRobotMac, Continuation<? super ApiResponse<Boolean>> continuation);

        @Headers({PdHost.ROBOT_BIZ})
        @POST("/api/common/map/upload/v2")
        Object uploadMap(@Body RobotMapReqV2 robotMapReqV2, Continuation<? super MapUploadResponse> continuation);

        /* JADX WARN: Classes with same name are omitted:
          classes3.dex
         */
        /* compiled from: NetWorkApiManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$MapManagerService$Companion;", "", "()V", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }
        }
    }
}
