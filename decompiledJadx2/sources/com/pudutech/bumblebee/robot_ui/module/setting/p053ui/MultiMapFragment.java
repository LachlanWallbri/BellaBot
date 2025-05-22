package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.oss.UpdateErrorSealed;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract;
import com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter;
import com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateContract;
import com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager;
import com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowDownloadingDialog;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.MapSelectAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.MapSelectItem;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectPositionAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectPositionItem;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SyncLocalMapAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.ConfirmTipDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.MapSwitchTipDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.TransparentLoadDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MapView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MaxHeightRecyclerView;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.RecyclerViewClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.mirsdkwrap.lib.map.MapLine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: MultiMapFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020*H\u0002J\b\u0010,\u001a\u00020*H\u0002J\b\u0010-\u001a\u00020*H\u0002J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020*2\u0006\u00100\u001a\u000201H\u0016J&\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010:H\u0016J\b\u0010;\u001a\u00020*H\u0016J\b\u0010<\u001a\u00020*H\u0002J\b\u0010=\u001a\u00020*H\u0002J\u001a\u0010>\u001a\u00020*2\u0006\u0010?\u001a\u0002042\b\u00109\u001a\u0004\u0018\u00010:H\u0016J(\u0010@\u001a\u00020*2\u0006\u0010A\u001a\u00020B2\u0016\u0010C\u001a\u0012\u0012\u0004\u0012\u00020\u00060Dj\b\u0012\u0004\u0012\u00020\u0006`EH\u0016J@\u0010F\u001a\u00020*2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020H2\u0006\u0010K\u001a\u00020H2\u0016\u0010L\u001a\u0012\u0012\u0004\u0012\u00020M0Dj\b\u0012\u0004\u0012\u00020M`EH\u0016J\u001a\u0010N\u001a\u00020*2\u0006\u0010A\u001a\u00020B2\b\u0010O\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010P\u001a\u00020*H\u0002J\u000e\u0010Q\u001a\u00020*2\u0006\u0010R\u001a\u00020\u0006J\u0010\u0010S\u001a\u00020*2\u0006\u0010T\u001a\u00020UH\u0002J.\u0010V\u001a\u00020*2\b\u0010O\u001a\u0004\u0018\u00010\u00062\b\u0010W\u001a\u0004\u0018\u00010X2\b\u0010Y\u001a\u0004\u0018\u00010\u0006H\u0016ø\u0001\u0000¢\u0006\u0002\bZJ\u001a\u0010[\u001a\u00020*2\u0006\u0010\\\u001a\u00020]2\b\u0010^\u001a\u0004\u0018\u00010_H\u0016J\u0010\u0010`\u001a\u00020*2\u0006\u0010R\u001a\u00020HH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0010\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010!\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/MultiMapFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdateContract$ViewInterface;", "()V", "TAG", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "errorDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/ConfirmTipDialog;", "localMapAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SyncLocalMapAdapter;", "getLocalMapAdapter", "()Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SyncLocalMapAdapter;", "localMapAdapter$delegate", "Lkotlin/Lazy;", "mLoadDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/TransparentLoadDialog;", "mapAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/MapSelectAdapter;", "mapStatusJob", "Lkotlinx/coroutines/Job;", "mapSwitchPresenter", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter;", "getMapSwitchPresenter", "()Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter;", "mapSwitchPresenter$delegate", "mapUpdatePresenter", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdatePresenter;", "getMapUpdatePresenter", "()Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdatePresenter;", "mapUpdatePresenter$delegate", "networkJob", "outSiteAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectPositionAdapter;", "progressDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowDownloadingDialog;", "successDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "usherAdapter", "dismissLoad", "", "dismissProgressDialog", "initData", "initView", "isNetworkAvailable", "", "context", "Landroid/content/Context;", "onAttach", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onError", "onSuccess", "onViewCreated", "view", "showAll", "type", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$Type;", "names", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "showBackGround", "maxX", "", "minX", "maxY", "minY", "model", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "showChosen", "name", "showLoad", "showProgress", "progress", "showSelectMapDialog", "mapItem", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/MapSelectItem;", "showSwitchError", "code", "Lkotlin/UByte;", "description", "showSwitchError-olJ16Uo", "updateFinish", "state", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapUpdateContract$UpdateState;", "error", "Lcom/pudutech/bumblebee/business/oss/UpdateErrorSealed;", "updateProgress", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MultiMapFragment extends Fragment implements MapSwitchContract.ViewInterface, MapUpdateContract.ViewInterface {
    private HashMap _$_findViewCache;
    private CoroutineScope coroutineScope;
    private ConfirmTipDialog errorDialog;
    private TransparentLoadDialog mLoadDialog;
    private MapSelectAdapter mapAdapter;
    private Job mapStatusJob;
    private Job networkJob;
    private SelectPositionAdapter outSiteAdapter;
    private ShowDownloadingDialog progressDialog;
    private ShowTipMsgDialog successDialog;
    private SelectPositionAdapter usherAdapter;
    private final String TAG = "MultiMapFragment";

    /* renamed from: mapSwitchPresenter$delegate, reason: from kotlin metadata */
    private final Lazy mapSwitchPresenter = LazyKt.lazy(new Function0<MapSwitchPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$mapSwitchPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MapSwitchPresenter invoke() {
            MapSwitchPresenter mapSwitchPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(MapSwitchPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(MapSwitchPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                mapSwitchPresenter = new MapSwitchPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(MapSwitchPresenter.class).toString(), mapSwitchPresenter);
            } else {
                if (!(basePresenterInterface instanceof MapSwitchPresenter)) {
                    basePresenterInterface = null;
                }
                mapSwitchPresenter = (MapSwitchPresenter) basePresenterInterface;
            }
            if (mapSwitchPresenter != null) {
                return mapSwitchPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter");
        }
    });

    /* renamed from: mapUpdatePresenter$delegate, reason: from kotlin metadata */
    private final Lazy mapUpdatePresenter = LazyKt.lazy(new Function0<MapUpdatePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$mapUpdatePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MapUpdatePresenter invoke() {
            MapUpdatePresenter mapUpdatePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(MapUpdatePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(MapUpdatePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                mapUpdatePresenter = new MapUpdatePresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(MapUpdatePresenter.class).toString(), mapUpdatePresenter);
            } else {
                if (!(basePresenterInterface instanceof MapUpdatePresenter)) {
                    basePresenterInterface = null;
                }
                mapUpdatePresenter = (MapUpdatePresenter) basePresenterInterface;
            }
            if (mapUpdatePresenter != null) {
                return mapUpdatePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter");
        }
    });

    /* renamed from: localMapAdapter$delegate, reason: from kotlin metadata */
    private final Lazy localMapAdapter = LazyKt.lazy(new Function0<SyncLocalMapAdapter>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$localMapAdapter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SyncLocalMapAdapter invoke() {
            return new SyncLocalMapAdapter();
        }
    });

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MapSwitchContract.Type.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[MapSwitchContract.Type.MAP.ordinal()] = 1;
            $EnumSwitchMapping$0[MapSwitchContract.Type.DINING_LET_OUT.ordinal()] = 2;
            $EnumSwitchMapping$0[MapSwitchContract.Type.USHER.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[MapSwitchContract.Type.values().length];
            $EnumSwitchMapping$1[MapSwitchContract.Type.MAP.ordinal()] = 1;
            $EnumSwitchMapping$1[MapSwitchContract.Type.DINING_LET_OUT.ordinal()] = 2;
            $EnumSwitchMapping$1[MapSwitchContract.Type.USHER.ordinal()] = 3;
        }
    }

    private final SyncLocalMapAdapter getLocalMapAdapter() {
        return (SyncLocalMapAdapter) this.localMapAdapter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MapSwitchPresenter getMapSwitchPresenter() {
        return (MapSwitchPresenter) this.mapSwitchPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MapUpdatePresenter getMapUpdatePresenter() {
        return (MapUpdatePresenter) this.mapUpdatePresenter.getValue();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ MapSelectAdapter access$getMapAdapter$p(MultiMapFragment multiMapFragment) {
        MapSelectAdapter mapSelectAdapter = multiMapFragment.mapAdapter;
        if (mapSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
        }
        return mapSelectAdapter;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_multi_map, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoad() {
        if (this.mLoadDialog == null && getContext() != null) {
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            this.mLoadDialog = new TransparentLoadDialog(context);
        }
        TransparentLoadDialog transparentLoadDialog = this.mLoadDialog;
        if (transparentLoadDialog == null || transparentLoadDialog.isShowing()) {
            return;
        }
        transparentLoadDialog.show();
    }

    private final void dismissLoad() {
        TransparentLoadDialog transparentLoadDialog = this.mLoadDialog;
        if (transparentLoadDialog == null || !transparentLoadDialog.isShowing()) {
            return;
        }
        transparentLoadDialog.dismiss();
    }

    private final void initData() {
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new MultiMapFragment$initData$1(this, null), 3, null);
        }
    }

    private final boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) ? false : true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        MultiMapFragment multiMapFragment = this;
        getMapSwitchPresenter().replaceView(multiMapFragment);
        getMapSwitchPresenter().loadMap();
        getMapUpdatePresenter().replaceView(multiMapFragment);
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        MapUpdateManager.INSTANCE.release();
        Job job = this.mapStatusJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Job job2 = this.networkJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        MultiMapFragment multiMapFragment = this;
        getMapSwitchPresenter().removeView(multiMapFragment);
        getMapUpdatePresenter().removeView(multiMapFragment);
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.coroutineScope = (CoroutineScope) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSelectMapDialog(final MapSelectItem mapItem) {
        Pdlog.m3273d(this.TAG, "showSelectMapDialog : mapName = " + mapItem.getMapName() + "; ");
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        final MapSwitchTipDialog mapSwitchTipDialog = new MapSwitchTipDialog(context);
        mapSwitchTipDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$showSelectMapDialog$1
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
                MapSwitchTipDialog.this.dismiss();
            }
        });
        mapSwitchTipDialog.setOnBtn2Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$showSelectMapDialog$2
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
                MapSwitchPresenter mapSwitchPresenter;
                MultiMapFragment.this.showLoad();
                mapSwitchPresenter = MultiMapFragment.this.getMapSwitchPresenter();
                mapSwitchPresenter.actionChoose(MapSwitchContract.Type.MAP, mapItem.getMapName());
                List<MapSelectItem> data = MultiMapFragment.access$getMapAdapter$p(MultiMapFragment.this).getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "mapAdapter.data");
                for (MapSelectItem mapSelectItem : data) {
                    mapSelectItem.setSelect(Intrinsics.areEqual(mapSelectItem.getMapName(), mapItem.getMapName()));
                }
                MultiMapFragment.access$getMapAdapter$p(MultiMapFragment.this).notifyDataSetChanged();
                mapSwitchTipDialog.dismiss();
            }
        });
        mapSwitchTipDialog.show();
    }

    private final void initView() {
        LinearLayout update_ll = (LinearLayout) _$_findCachedViewById(C4188R.id.update_ll);
        Intrinsics.checkExpressionValueIsNotNull(update_ll, "update_ll");
        update_ll.setVisibility(Constans.INSTANCE.isMapSyncSwitch() ? 0 : 8);
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        this.mapAdapter = new MapSelectAdapter(context);
        RecyclerView map_recycler_view = (RecyclerView) _$_findCachedViewById(C4188R.id.map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(map_recycler_view, "map_recycler_view");
        map_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView map_recycler_view2 = (RecyclerView) _$_findCachedViewById(C4188R.id.map_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(map_recycler_view2, "map_recycler_view");
        MapSelectAdapter mapSelectAdapter = this.mapAdapter;
        if (mapSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
        }
        map_recycler_view2.setAdapter(mapSelectAdapter);
        MaxHeightRecyclerView maxHeightRecyclerView = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.rv_local_map);
        maxHeightRecyclerView.setLayoutManager(new LinearLayoutManager(maxHeightRecyclerView.getContext()));
        maxHeightRecyclerView.setHasFixedSize(true);
        maxHeightRecyclerView.setAdapter(getLocalMapAdapter());
        MapSelectAdapter mapSelectAdapter2 = this.mapAdapter;
        if (mapSelectAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
        }
        mapSelectAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                List<?> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i = 0;
                for (Object obj : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        MapSelectItem mapSelectItem = (MapSelectItem) obj;
                        if (i == position && !mapSelectItem.isSelect()) {
                            MultiMapFragment.this.showSelectMapDialog(mapSelectItem);
                        }
                        i = i2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.MapSelectItem");
                    }
                }
            }
        });
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
        this.outSiteAdapter = new SelectPositionAdapter(context2);
        MaxHeightRecyclerView out_food_position_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.out_food_position_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(out_food_position_recycler_view, "out_food_position_recycler_view");
        out_food_position_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView out_food_position_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.out_food_position_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(out_food_position_recycler_view2, "out_food_position_recycler_view");
        SelectPositionAdapter selectPositionAdapter = this.outSiteAdapter;
        if (selectPositionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
        }
        out_food_position_recycler_view2.setAdapter(selectPositionAdapter);
        SelectPositionAdapter selectPositionAdapter2 = this.outSiteAdapter;
        if (selectPositionAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
        }
        selectPositionAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                MapSwitchPresenter mapSwitchPresenter;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                List<?> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i = 0;
                for (Object obj : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        SelectPositionItem selectPositionItem = (SelectPositionItem) obj;
                        selectPositionItem.setSelect(i == position);
                        if (selectPositionItem.isSelect()) {
                            mapSwitchPresenter = MultiMapFragment.this.getMapSwitchPresenter();
                            mapSwitchPresenter.actionChoose(MapSwitchContract.Type.DINING_LET_OUT, selectPositionItem.getId());
                        }
                        i = i2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.SelectPositionItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        ((MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.out_food_position_recycler_view)).addOnItemTouchListener(new RecyclerViewClickListener(getContext(), new RecyclerViewClickListener.OnItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$initView$4
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.RecyclerViewClickListener.OnItemClickListener
            public void onItemDownClick(View view, int position) {
                if (view != null) {
                    Context context3 = MultiMapFragment.this.getContext();
                    if (context3 == null) {
                        Intrinsics.throwNpe();
                    }
                    view.setBackgroundColor(context3.getColor(C4188R.color.click_color_press));
                }
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.RecyclerViewClickListener.OnItemClickListener
            public void onItemUpClick(View view, int position) {
                if (view != null) {
                    view.setBackgroundColor(-1);
                }
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.RecyclerViewClickListener.OnItemClickListener
            public void onItemMoveClick(View view, int position) {
                if (view != null) {
                    view.setBackgroundColor(-1);
                }
            }
        }));
        Context context3 = getContext();
        if (context3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context3, "context!!");
        this.usherAdapter = new SelectPositionAdapter(context3);
        MaxHeightRecyclerView usher_position_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.usher_position_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(usher_position_recycler_view, "usher_position_recycler_view");
        usher_position_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView usher_position_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.usher_position_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(usher_position_recycler_view2, "usher_position_recycler_view");
        SelectPositionAdapter selectPositionAdapter3 = this.usherAdapter;
        if (selectPositionAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        usher_position_recycler_view2.setAdapter(selectPositionAdapter3);
        SelectPositionAdapter selectPositionAdapter4 = this.usherAdapter;
        if (selectPositionAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        selectPositionAdapter4.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$initView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                MapSwitchPresenter mapSwitchPresenter;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                List<?> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i = 0;
                for (Object obj : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        SelectPositionItem selectPositionItem = (SelectPositionItem) obj;
                        selectPositionItem.setSelect(i == position);
                        if (selectPositionItem.isSelect()) {
                            mapSwitchPresenter = MultiMapFragment.this.getMapSwitchPresenter();
                            mapSwitchPresenter.actionChoose(MapSwitchContract.Type.USHER, selectPositionItem.getId());
                        }
                        i = i2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.SelectPositionItem");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        ((FrameLayout) _$_findCachedViewById(C4188R.id.update_fl)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$initView$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CoroutineScope coroutineScope;
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ((TextView) MultiMapFragment.this._$_findCachedViewById(C4188R.id.update_tv)).performClick();
                MultiMapFragment.this.showProgress("0");
                coroutineScope = MultiMapFragment.this.coroutineScope;
                if (coroutineScope != null) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C42511(null), 3, null);
                }
                str = MultiMapFragment.this.TAG;
                Pdlog.m3273d(str, "update_fl showProgress(0)");
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MultiMapFragment.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$initView$6$1", m3970f = "MultiMapFragment.kt", m3971i = {0}, m3972l = {228}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$initView$6$1 */
            /* loaded from: classes3.dex */
            public static final class C42511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4883p$;

                C42511(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C42511 c42511 = new C42511(completion);
                    c42511.f4883p$ = (CoroutineScope) obj;
                    return c42511;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C42511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    MapUpdatePresenter mapUpdatePresenter;
                    CoroutineScope coroutineScope;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope2 = this.f4883p$;
                        mapUpdatePresenter = MultiMapFragment.this.getMapUpdatePresenter();
                        coroutineScope = MultiMapFragment.this.coroutineScope;
                        this.L$0 = coroutineScope2;
                        this.label = 1;
                        if (mapUpdatePresenter.updateAll(coroutineScope, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }
        }, 3, null));
        FrameLayout update_fl = (FrameLayout) _$_findCachedViewById(C4188R.id.update_fl);
        Intrinsics.checkExpressionValueIsNotNull(update_fl, "update_fl");
        update_fl.setClickable(false);
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    public void showAll(MapSwitchContract.Type type, ArrayList<String> names) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(names, "names");
        Pdlog.m3273d(this.TAG, "showAll " + type + " , " + names);
        int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            ArrayList arrayList = new ArrayList();
            boolean z7 = false;
            for (String str : names) {
                if (Intrinsics.areEqual(getMapSwitchPresenter().getMapChosen(), str)) {
                    getMapSwitchPresenter().actionChoose(MapSwitchContract.Type.MAP, str);
                    z2 = true;
                    z = true;
                } else {
                    z = z7;
                    z2 = false;
                }
                arrayList.add(new MapSelectItem(str, z2));
                z7 = z;
            }
            if (!z7 && names.size() > 0) {
                MapSwitchPresenter mapSwitchPresenter = getMapSwitchPresenter();
                MapSwitchContract.Type type2 = MapSwitchContract.Type.MAP;
                String str2 = names.get(0);
                Intrinsics.checkExpressionValueIsNotNull(str2, "names[0]");
                mapSwitchPresenter.actionChoose(type2, str2);
            }
            MapSelectAdapter mapSelectAdapter = this.mapAdapter;
            if (mapSelectAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
            }
            mapSelectAdapter.setNewData(arrayList);
            return;
        }
        if (i == 2) {
            ArrayList arrayList2 = new ArrayList();
            boolean z8 = false;
            for (String str3 : names) {
                if (Intrinsics.areEqual(str3, getMapSwitchPresenter().getDiningOutLetChosen())) {
                    z4 = true;
                    z3 = true;
                } else {
                    z3 = z8;
                    z4 = false;
                }
                arrayList2.add(new SelectPositionItem(str3, z4));
                z8 = z3;
            }
            if (!z8 && names.size() > 0) {
                MapSwitchPresenter mapSwitchPresenter2 = getMapSwitchPresenter();
                MapSwitchContract.Type type3 = MapSwitchContract.Type.DINING_LET_OUT;
                String str4 = names.get(0);
                Intrinsics.checkExpressionValueIsNotNull(str4, "names[0]");
                mapSwitchPresenter2.actionChoose(type3, str4);
                Pdlog.m3273d(this.TAG, "showAll not has select , set new Default select");
            }
            SelectPositionAdapter selectPositionAdapter = this.outSiteAdapter;
            if (selectPositionAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
            }
            selectPositionAdapter.setNewData(arrayList2);
            return;
        }
        if (i != 3) {
            return;
        }
        if (!names.isEmpty()) {
            TextView usher_position_tv = (TextView) _$_findCachedViewById(C4188R.id.usher_position_tv);
            Intrinsics.checkExpressionValueIsNotNull(usher_position_tv, "usher_position_tv");
            usher_position_tv.setVisibility(0);
        }
        ArrayList arrayList3 = new ArrayList();
        boolean z9 = false;
        for (String str5 : names) {
            if (Intrinsics.areEqual(str5, getMapSwitchPresenter().getUsherChosen())) {
                z6 = true;
                z5 = true;
            } else {
                z5 = z9;
                z6 = false;
            }
            arrayList3.add(new SelectPositionItem(str5, z6));
            z9 = z5;
        }
        if (!z9 && names.size() > 0) {
            MapSwitchPresenter mapSwitchPresenter3 = getMapSwitchPresenter();
            MapSwitchContract.Type type4 = MapSwitchContract.Type.USHER;
            String str6 = names.get(0);
            Intrinsics.checkExpressionValueIsNotNull(str6, "names[0]");
            mapSwitchPresenter3.actionChoose(type4, str6);
            Pdlog.m3273d(this.TAG, "showAll not has select , set new Default select");
        }
        SelectPositionAdapter selectPositionAdapter2 = this.usherAdapter;
        if (selectPositionAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        selectPositionAdapter2.setNewData(arrayList3);
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    public void showBackGround(int maxX, int minX, int maxY, int minY, ArrayList<MapLine> model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showBackGround " + model);
        ((MapView) _$_findCachedViewById(C4188R.id.map_iv)).setMapData(maxX, maxY, minX, minY, model);
        dismissLoad();
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    public void showChosen(MapSwitchContract.Type type, String name) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Pdlog.m3273d(this.TAG, "showChosen " + type + ',' + name + ' ');
        int i = WhenMappings.$EnumSwitchMapping$1[type.ordinal()];
        if (i == 1) {
            MapSelectAdapter mapSelectAdapter = this.mapAdapter;
            if (mapSelectAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
            }
            List<MapSelectItem> data = mapSelectAdapter.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "mapAdapter.data");
            for (MapSelectItem mapSelectItem : data) {
                mapSelectItem.setSelect(Intrinsics.areEqual(mapSelectItem.getMapName(), name));
            }
            MapSelectAdapter mapSelectAdapter2 = this.mapAdapter;
            if (mapSelectAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mapAdapter");
            }
            mapSelectAdapter2.notifyDataSetChanged();
            return;
        }
        if (i == 2) {
            SelectPositionAdapter selectPositionAdapter = this.outSiteAdapter;
            if (selectPositionAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
            }
            List<SelectPositionItem> data2 = selectPositionAdapter.getData();
            Intrinsics.checkExpressionValueIsNotNull(data2, "outSiteAdapter.data");
            for (SelectPositionItem selectPositionItem : data2) {
                selectPositionItem.setSelect(Intrinsics.areEqual(selectPositionItem.getId(), name));
            }
            SelectPositionAdapter selectPositionAdapter2 = this.outSiteAdapter;
            if (selectPositionAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("outSiteAdapter");
            }
            selectPositionAdapter2.notifyDataSetChanged();
            return;
        }
        if (i != 3) {
            return;
        }
        SelectPositionAdapter selectPositionAdapter3 = this.usherAdapter;
        if (selectPositionAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        List<SelectPositionItem> data3 = selectPositionAdapter3.getData();
        Intrinsics.checkExpressionValueIsNotNull(data3, "usherAdapter.data");
        for (SelectPositionItem selectPositionItem2 : data3) {
            selectPositionItem2.setSelect(Intrinsics.areEqual(selectPositionItem2.getId(), name));
        }
        SelectPositionAdapter selectPositionAdapter4 = this.usherAdapter;
        if (selectPositionAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usherAdapter");
        }
        selectPositionAdapter4.notifyDataSetChanged();
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    /* renamed from: showSwitchError-olJ16Uo */
    public void mo4294showSwitchErrorolJ16Uo(String name, UByte code, String description) {
        Pdlog.m3274e(this.TAG, "showSwitchError " + name + ',' + description);
        dismissLoad();
        Context context = RobotContext.INSTANCE.getContext();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(C4188R.string.pdStr11_14);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr11_14)");
        Object[] objArr = new Object[2];
        if (name == null) {
            name = "";
        }
        objArr[0] = name;
        if (description == null) {
            description = "";
        }
        objArr[1] = description;
        String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        ToastUtils.show(context, format, new Object[0]);
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateContract.ViewInterface
    public void updateFinish(MapUpdateContract.UpdateState state, UpdateErrorSealed error) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        if (state == MapUpdateContract.UpdateState.DOWNLOAD) {
            getMapSwitchPresenter().loadMap();
        }
        if (error == UpdateErrorSealed.NOT_ERROR) {
            FrameLayout update_fl = (FrameLayout) _$_findCachedViewById(C4188R.id.update_fl);
            Intrinsics.checkExpressionValueIsNotNull(update_fl, "update_fl");
            update_fl.setClickable(false);
            ((TextView) _$_findCachedViewById(C4188R.id.update_tv)).setText(C4188R.string.map_update_latest);
            onSuccess();
            return;
        }
        FrameLayout update_fl2 = (FrameLayout) _$_findCachedViewById(C4188R.id.update_fl);
        Intrinsics.checkExpressionValueIsNotNull(update_fl2, "update_fl");
        update_fl2.setClickable(true);
        ((TextView) _$_findCachedViewById(C4188R.id.update_tv)).setText(C4188R.string.map_update_sync);
        onError();
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateContract.ViewInterface
    public void updateProgress(int progress) {
        showProgress(String.valueOf(progress));
    }

    private final void onSuccess() {
        Pdlog.m3273d(this.TAG, "onSuccess()");
        requireActivity().runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$onSuccess$$inlined$runOnUiThread$1
            /* JADX WARN: Code restructure failed: missing block: B:8:0x0045, code lost:
            
                r0 = r4.this$0.successDialog;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void run() {
                ShowTipMsgDialog showTipMsgDialog;
                ShowTipMsgDialog showTipMsgDialog2;
                ShowTipMsgDialog showTipMsgDialog3;
                MultiMapFragment.this.dismissProgressDialog();
                showTipMsgDialog = MultiMapFragment.this.successDialog;
                if (showTipMsgDialog == null) {
                    MultiMapFragment multiMapFragment = MultiMapFragment.this;
                    Context requireContext = multiMapFragment.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
                    ShowTipMsgDialog showTipMsgDialog4 = new ShowTipMsgDialog(requireContext);
                    String string = MultiMapFragment.this.getString(C4188R.string.map_synced);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.map_synced)");
                    showTipMsgDialog4.showTipMsg(string);
                    showTipMsgDialog4.setCanCancel(true);
                    multiMapFragment.successDialog = showTipMsgDialog4;
                    Unit unit = Unit.INSTANCE;
                }
                showTipMsgDialog2 = MultiMapFragment.this.successDialog;
                if (showTipMsgDialog2 == null || showTipMsgDialog2.isShowing() || showTipMsgDialog3 == null) {
                    return;
                }
                showTipMsgDialog3.show();
            }
        });
    }

    private final void onError() {
        Pdlog.m3273d(this.TAG, "onError download or update map failed");
        requireActivity().runOnUiThread(new MultiMapFragment$onError$$inlined$runOnUiThread$1(this));
    }

    public final void showProgress(final String progress) {
        Intrinsics.checkParameterIsNotNull(progress, "progress");
        requireActivity().runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$showProgress$$inlined$runOnUiThread$1
            /* JADX WARN: Code restructure failed: missing block: B:11:0x0046, code lost:
            
                r0 = r4.this$0.errorDialog;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void run() {
                ShowDownloadingDialog showDownloadingDialog;
                ShowTipMsgDialog showTipMsgDialog;
                ConfirmTipDialog confirmTipDialog;
                ShowDownloadingDialog showDownloadingDialog2;
                showDownloadingDialog = MultiMapFragment.this.progressDialog;
                if (showDownloadingDialog == null) {
                    MultiMapFragment multiMapFragment = MultiMapFragment.this;
                    Context requireContext = multiMapFragment.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
                    ShowDownloadingDialog showDownloadingDialog3 = new ShowDownloadingDialog(requireContext);
                    showDownloadingDialog3.showProgress("0");
                    showDownloadingDialog3.setCanceledOnTouchOutside(false);
                    String string = MultiMapFragment.this.getString(C4188R.string.pdStr10_65);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr10_65)");
                    showDownloadingDialog3.setTitle(string);
                    multiMapFragment.progressDialog = showDownloadingDialog3;
                    Unit unit = Unit.INSTANCE;
                }
                showTipMsgDialog = MultiMapFragment.this.successDialog;
                if ((showTipMsgDialog == null || !showTipMsgDialog.isShowing()) && (confirmTipDialog == null || !confirmTipDialog.isShowing())) {
                    showDownloadingDialog2 = MultiMapFragment.this.progressDialog;
                    if (showDownloadingDialog2 != null) {
                        if (!showDownloadingDialog2.isShowing()) {
                            showDownloadingDialog2.showProgress(progress);
                            showDownloadingDialog2.show();
                            return;
                        } else {
                            showDownloadingDialog2.showProgress(progress);
                            return;
                        }
                    }
                    return;
                }
                MultiMapFragment.this.dismissProgressDialog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dismissProgressDialog() {
        requireActivity().runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$dismissProgressDialog$$inlined$runOnUiThread$1
            @Override // java.lang.Runnable
            public final void run() {
                ShowDownloadingDialog showDownloadingDialog;
                showDownloadingDialog = MultiMapFragment.this.progressDialog;
                if (showDownloadingDialog == null || !showDownloadingDialog.isShowing()) {
                    return;
                }
                showDownloadingDialog.dismisscheckUpdateDownloadingDialog();
            }
        });
    }
}
