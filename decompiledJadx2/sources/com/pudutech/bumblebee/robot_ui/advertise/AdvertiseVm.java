package com.pudutech.bumblebee.robot_ui.advertise;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.robot_ui.util.video.VideoPreloadManager;
import com.pudutech.bumblebee.robot_ui.viewmodel.BaseFVM;
import com.pudutech.bumblebee.robot_ui.viewmodel.VMExtKt;
import com.pudutech.bumblebee.robot_ui.viewmodel.ViewModelLaunchBuild;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.ad.AdverListReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.ad.AdInfo;
import com.pudutech.disinfect.baselib.network.response.ad.AdverListResp;
import com.pudutech.disinfect.baselib.network.response.ad.Material;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.disinfect.baselib.util.LocalStoringUtil;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* compiled from: AdvertiseVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0014H\u0002J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020$H\u0016J\u001a\u0010&\u001a\u00020$2\u0006\u0010 \u001a\u00020!2\b\b\u0002\u0010'\u001a\u00020\u000fH\u0002J\u000e\u0010(\u001a\u00020$2\u0006\u0010 \u001a\u00020!J\u0018\u0010)\u001a\u00020$2\u0006\u0010 \u001a\u00020!2\b\b\u0002\u0010'\u001a\u00020\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR \u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001a¨\u0006*"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdvertiseVm;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BaseFVM;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;)V", "TAG", "", "_adverData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverComBean;", "_adverDataLd", "Landroidx/lifecycle/LiveData;", "get_adverDataLd", "()Landroidx/lifecycle/LiveData;", "<set-?>", "", "isCruiseAdver", "()Z", "isDoorWelcomeAdver", "mAdverListResp", "Lcom/pudutech/disinfect/baselib/network/response/ad/AdverListResp;", "mJob", "Lkotlinx/coroutines/Job;", "mVideoPreloadManager", "Lcom/pudutech/bumblebee/robot_ui/util/video/VideoPreloadManager;", "getMVideoPreloadManager", "()Lcom/pudutech/bumblebee/robot_ui/util/video/VideoPreloadManager;", "mVideoPreloadManager$delegate", "Lkotlin/Lazy;", "getAdverListResp", "getAdverType", "", "adSceneConfig", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdSceneConfig;", "getIsExitAdver", "isExistAds", "", "onCleared", "parseAdver", "isPreloadVideo", "reqCacheAdList", "reqNetAdList", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AdvertiseVm extends BaseFVM {
    private final String TAG;
    private final MutableLiveData<AdverComBean> _adverData;
    private final LiveData<AdverComBean> _adverDataLd;
    private boolean isCruiseAdver;
    private boolean isDoorWelcomeAdver;
    private AdverListResp mAdverListResp;
    private Job mJob;

    /* renamed from: mVideoPreloadManager$delegate, reason: from kotlin metadata */
    private final Lazy mVideoPreloadManager;

    private final VideoPreloadManager getMVideoPreloadManager() {
        return (VideoPreloadManager) this.mVideoPreloadManager.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdvertiseVm(CoroutineScope scope) {
        super(scope);
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        this.TAG = "AdvertiseVm";
        this._adverData = new MutableLiveData<>();
        this._adverDataLd = VMExtKt.toLiveData(this._adverData);
        this.mVideoPreloadManager = LazyKt.lazy(new Function0<VideoPreloadManager>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdvertiseVm$mVideoPreloadManager$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoPreloadManager invoke() {
                return new VideoPreloadManager();
            }
        });
    }

    public final LiveData<AdverComBean> get_adverDataLd() {
        return this._adverDataLd;
    }

    public static /* synthetic */ void reqNetAdList$default(AdvertiseVm advertiseVm, AdSceneConfig adSceneConfig, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        advertiseVm.reqNetAdList(adSceneConfig, z);
    }

    public final void reqNetAdList(final AdSceneConfig adSceneConfig, final boolean isPreloadVideo) {
        Intrinsics.checkParameterIsNotNull(adSceneConfig, "adSceneConfig");
        this.mJob = VMExtKt.launchIO(this, new Function1<ViewModelLaunchBuild, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdvertiseVm$reqNetAdList$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ViewModelLaunchBuild viewModelLaunchBuild) {
                invoke2(viewModelLaunchBuild);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: AdvertiseVm.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.advertise.AdvertiseVm$reqNetAdList$1$1", m3970f = "AdvertiseVm.kt", m3971i = {0, 0, 0}, m3972l = {40}, m3973m = "invokeSuspend", m3974n = {"$this$job", "cloudApi", "shopId"}, m3975s = {"L$0", "L$1", "I$0"})
            /* renamed from: com.pudutech.bumblebee.robot_ui.advertise.AdvertiseVm$reqNetAdList$1$1 */
            /* loaded from: classes2.dex */
            public static final class C41901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int I$0;
                Object L$0;
                Object L$1;
                Object L$2;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4836p$;

                C41901(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C41901 c41901 = new C41901(completion);
                    c41901.f4836p$ = (CoroutineScope) obj;
                    return c41901;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C41901) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    AdvertiseVm advertiseVm;
                    AdverListResp adverListResp;
                    String str;
                    AdverListResp adverListResp2;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f4836p$;
                        NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
                        int shopId = Constant.INSTANCE.getShopId();
                        AdvertiseVm advertiseVm2 = AdvertiseVm.this;
                        AdverListReq adverListReq = new AdverListReq(null, shopId, 0, 5, null);
                        this.L$0 = coroutineScope;
                        this.L$1 = cloudApi;
                        this.I$0 = shopId;
                        this.L$2 = advertiseVm2;
                        this.label = 1;
                        obj = cloudApi.getAdverList(adverListReq, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        advertiseVm = advertiseVm2;
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        advertiseVm = (AdvertiseVm) this.L$2;
                        int i2 = this.I$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    advertiseVm.mAdverListResp = (AdverListResp) ((ApiResponse) obj).getData();
                    adverListResp = AdvertiseVm.this.mAdverListResp;
                    if (adverListResp != null) {
                        LocalStoringUtil.INSTANCE.put(AdverConfig.ADVER_DATA_LOCAL, GsonSingleton.INSTANCE.getINSTANCE().toJson(adverListResp));
                    }
                    AdvertiseVm.this.isExistAds();
                    AdvertiseVm.this.parseAdver(adSceneConfig, isPreloadVideo);
                    str = AdvertiseVm.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("reqNetAdList() mAdverListResp =");
                    adverListResp2 = AdvertiseVm.this.mAdverListResp;
                    sb.append(adverListResp2);
                    Pdlog.m3273d(str, sb.toString());
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ViewModelLaunchBuild receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.job(new C41901(null));
                receiver.onError(new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdvertiseVm$reqNetAdList$1.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable it) {
                        AdverListResp adverListResp;
                        String str;
                        AdverListResp adverListResp2;
                        String str2;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        adverListResp = AdvertiseVm.this.getAdverListResp();
                        AdvertiseVm.this.mAdverListResp = adverListResp;
                        AdvertiseVm.this.isExistAds();
                        AdvertiseVm.this.parseAdver(adSceneConfig, isPreloadVideo);
                        str = AdvertiseVm.this.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("reqNetAdList fail local = ");
                        adverListResp2 = AdvertiseVm.this.mAdverListResp;
                        sb.append(adverListResp2);
                        Pdlog.m3273d(str, sb.toString());
                        str2 = AdvertiseVm.this.TAG;
                        it.printStackTrace();
                        Pdlog.m3274e(str2, "reqNetAdList fail", Unit.INSTANCE);
                    }
                });
            }
        });
    }

    public final AdverListResp getAdverListResp() {
        return (AdverListResp) GsonSingleton.INSTANCE.getINSTANCE().fromJson(LocalStoringUtil.INSTANCE.get(AdverConfig.ADVER_DATA_LOCAL, ""), AdverListResp.class);
    }

    public final void reqCacheAdList(AdSceneConfig adSceneConfig) {
        Intrinsics.checkParameterIsNotNull(adSceneConfig, "adSceneConfig");
        this.mAdverListResp = getAdverListResp();
        Pdlog.m3273d(this.TAG, "reqCacheAdList() mAdverListResp =" + this.mAdverListResp);
        parseAdver$default(this, adSceneConfig, false, 2, null);
    }

    static /* synthetic */ void parseAdver$default(AdvertiseVm advertiseVm, AdSceneConfig adSceneConfig, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        advertiseVm.parseAdver(adSceneConfig, z);
    }

    public final void parseAdver(AdSceneConfig adSceneConfig, boolean isPreloadVideo) {
        Pdlog.m3273d(this.TAG, "parseAdver() adSceneConfig =" + adSceneConfig);
        AdverListResp adverListResp = this.mAdverListResp;
        if (adverListResp != null) {
            ArrayList<AdInfo> ad = adverListResp.getAd();
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            if (ad != null) {
                for (AdInfo adInfo : ad) {
                    if (adInfo.getStart_time() <= currentTimeMillis && currentTimeMillis <= adInfo.getEnd_time()) {
                        ArrayList<String> scenes = adInfo.getScenes();
                        if (scenes != null && scenes.contains(adSceneConfig.getId())) {
                            for (Material material : adInfo.getAd_list()) {
                                if (material.getType() == 0) {
                                    AdverImageBean adverImageBean = new AdverImageBean(material.getUrl(), Integer.valueOf(adInfo.getSecond()), false, 4, null);
                                    if (arrayList.size() <= 20) {
                                        arrayList.add(adverImageBean);
                                    } else {
                                        Pdlog.m3273d(this.TAG, "parseAdver() picList.size >20");
                                    }
                                } else if (material.getType() == 1) {
                                    arrayList2.add(new AdVideoInfoBean(material.getUrl(), material.getMd5()));
                                }
                            }
                        }
                        if (isPreloadVideo) {
                            for (Material material2 : adInfo.getAd_list()) {
                                if (material2.getType() == 1) {
                                    arrayList3.add(material2.getUrl());
                                }
                            }
                            if (arrayList3.size() > 0) {
                                getMVideoPreloadManager().initVideoUlr(arrayList3);
                                getMVideoPreloadManager().load();
                                Pdlog.m3273d(this.TAG, "parseAdver() PreloadManager.size =" + arrayList3.size());
                            }
                        }
                    }
                }
            }
            Pdlog.m3273d(this.TAG, "parseAdver() end adverPicList =" + arrayList + " adverVideoList =" + arrayList2 + ' ');
            if (arrayList2.size() > 0) {
                this._adverData.postValue(new AdverComBean(new AdverVideoBean(arrayList2), 1));
            } else if (arrayList.size() > 0) {
                this._adverData.postValue(new AdverComBean(new AdverPicBean(arrayList), 0));
            }
        }
    }

    public final boolean isCruiseAdver() {
        return getIsExitAdver(AdSceneConfig.CRUISE_MODE);
    }

    public final boolean isDoorWelcomeAdver() {
        return getIsExitAdver(AdSceneConfig.SOLICITING_PASSENGERS_MODE);
    }

    public final boolean getIsExitAdver(AdSceneConfig adSceneConfig) {
        Intrinsics.checkParameterIsNotNull(adSceneConfig, "adSceneConfig");
        int adverType = getAdverType(adSceneConfig);
        Pdlog.m3273d(this.TAG, "getIsExitAdver() result = " + adverType);
        return adverType != -2;
    }

    public final int getAdverType(AdSceneConfig adSceneConfig) {
        Intrinsics.checkParameterIsNotNull(adSceneConfig, "adSceneConfig");
        if (Intrinsics.areEqual(adSceneConfig.getId(), AdSceneConfig.CRUISE_MODE.getId())) {
            if (!AdverConfig.INSTANCE.isCruiseAdverPic() && !AdverConfig.INSTANCE.isCruiseAdverVideo()) {
                return -2;
            }
            if (AdverConfig.INSTANCE.isCruiseAdverVideo()) {
                return 1;
            }
            if (AdverConfig.INSTANCE.isCruiseAdverPic()) {
                return 0;
            }
        } else {
            if (!Intrinsics.areEqual(adSceneConfig.getId(), AdSceneConfig.SOLICITING_PASSENGERS_MODE.getId()) || (!AdverConfig.INSTANCE.isDoorWelcomeAdverVideo() && !AdverConfig.INSTANCE.isDoorWelcomeAdverPic())) {
                return -2;
            }
            if (AdverConfig.INSTANCE.isDoorWelcomeAdverVideo()) {
                return 1;
            }
            if (AdverConfig.INSTANCE.isDoorWelcomeAdverPic()) {
                return 0;
            }
        }
        return -2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v14 */
    public final void isExistAds() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        ArrayList<String> scenes;
        AdverListResp adverListResp = this.mAdverListResp;
        if (adverListResp != null) {
            ArrayList<AdInfo> ad = adverListResp.getAd();
            long currentTimeMillis = System.currentTimeMillis();
            int i = 1;
            if (ad != null) {
                z = false;
                z2 = false;
                z3 = false;
                z4 = false;
                for (AdInfo adInfo : ad) {
                    if (adInfo.getStart_time() <= currentTimeMillis && currentTimeMillis <= adInfo.getEnd_time() && (scenes = adInfo.getScenes()) != null) {
                        if (scenes.contains(AdSceneConfig.SOLICITING_PASSENGERS_MODE.getId())) {
                            this.isDoorWelcomeAdver = i;
                            if (adInfo.mediaType() == 0) {
                                z = i;
                            } else if (adInfo.mediaType() == i) {
                                z2 = i;
                            }
                            String str = this.TAG;
                            Object[] objArr = new Object[i];
                            objArr[0] = "isExistAds() isDoorWelcomeAdver =" + isDoorWelcomeAdver() + "  pic =" + z + " video =" + z2;
                            Pdlog.m3273d(str, objArr);
                        }
                        if (scenes.contains(AdSceneConfig.CRUISE_MODE.getId())) {
                            this.isCruiseAdver = true;
                            if (adInfo.mediaType() == 0) {
                                z3 = true;
                            } else if (adInfo.mediaType() == 1) {
                                z4 = true;
                            }
                            Pdlog.m3273d(this.TAG, "isExistAds() isCruiseAdver =" + isCruiseAdver() + "  pic =" + z3 + " video=" + z4);
                        }
                    }
                    i = 1;
                }
            } else {
                z = false;
                z2 = false;
                z3 = false;
                z4 = false;
            }
            AdverConfig.INSTANCE.setCruiseAdverPic(z3);
            AdverConfig.INSTANCE.setCruiseAdverVideo(z4);
            AdverConfig.INSTANCE.setDoorWelcomeAdverPic(z);
            AdverConfig.INSTANCE.setDoorWelcomeAdverVideo(z2);
            Pdlog.m3273d(this.TAG, "isExistAds() isCruiseAdverPic =" + AdverConfig.INSTANCE.isCruiseAdverPic() + " isCruiseAdverVideo=" + AdverConfig.INSTANCE.isCruiseAdverVideo() + "isDoorWelcomeAdverPic =" + AdverConfig.INSTANCE.isDoorWelcomeAdverPic() + " isDoorWelcomeAdverVideo=" + AdverConfig.INSTANCE.isDoorWelcomeAdverVideo());
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.viewmodel.BaseFVM
    public void onCleared() {
        super.onCleared();
        Job job = this.mJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.mJob = (Job) null;
        CoroutineScopeKt.cancel$default(getScope(), null, 1, null);
        getMVideoPreloadManager().cancel();
    }
}
