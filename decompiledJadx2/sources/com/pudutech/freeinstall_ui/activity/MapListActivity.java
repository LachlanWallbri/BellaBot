package com.pudutech.freeinstall_ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.disinfect.baselib.util.QrCodeUtils;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.freeinstall_ui.adapter.MapEditSelectAdapter;
import com.pudutech.freeinstall_ui.adapter.MapEditSelectItem;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.freeinstall_ui.dialog.InputFreeInstallDialog;
import com.pudutech.freeinstall_ui.dialog.LoadingDialog;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.viewmodel.MapListViewModel;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import com.pudutech.module_robot_selfcheck.oss.UpdateErrorSealed;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: MapListActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\fH\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\u0010\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0016J\b\u0010\u001e\u001a\u00020\fH\u0016J\b\u0010\u001f\u001a\u00020\u0017H\u0016J\b\u0010 \u001a\u00020\u0017H\u0002J\b\u0010!\u001a\u00020\u0017H\u0002J\u0012\u0010\"\u001a\u00020\u00172\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u0017H\u0002J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0017H\u0016J\b\u0010)\u001a\u00020\u0017H\u0014J\b\u0010*\u001a\u00020\u0017H\u0014J\u0010\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020-H\u0003J\u0010\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\u0005H\u0016J\b\u00100\u001a\u00020\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/MapListActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/MapListViewModel;", "()V", "CHECK_EDIT_AUTH_TIME", "", "TAG", "adapter", "Lcom/pudutech/freeinstall_ui/adapter/MapEditSelectAdapter;", "downloadDialog", "Lcom/pudutech/freeinstall_ui/dialog/LoadingDialog;", "isEdit", "", "mapListView", "Landroid/view/View;", "psView", "pswQrCode", "Landroid/widget/ImageView;", "recyclerview_map_list", "Landroidx/recyclerview/widget/RecyclerView;", "updateDialog", "Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "changeView", "", "isPSPass", "checkEditAuth", "checkMapDownloadFinish", "checkSameName", "s", "createObserver", "currentActivityIsDark", "dismissLoading", "initMapListView", "initPSView", "initView", "saveInstanceState", "Landroid/os/Bundle;", "isAuthState", "layoutId", "", "onBackPressed", "onDestroy", "onResume", "showInputDialog", "item", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "showLoading", "message", "showUpdateDialog", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapListActivity extends BaseActivity<MapListViewModel> {
    private HashMap _$_findViewCache;
    private LoadingDialog downloadDialog;
    private boolean isEdit;
    private View mapListView;
    private View psView;
    private ImageView pswQrCode;
    private RecyclerView recyclerview_map_list;
    private CommonDialog updateDialog;
    private final String TAG = "MapListActivity";
    private final String CHECK_EDIT_AUTH_TIME = "checkEditAuth";
    private MapEditSelectAdapter adapter = new MapEditSelectAdapter();

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity
    public boolean currentActivityIsDark() {
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5362R.layout.activity_map_list;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        initPSView();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MapListActivity$initView$1(null), 3, null);
        ((TextView) _$_findCachedViewById(C5362R.id.tv_back)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                z = MapListActivity.this.isEdit;
                if (z) {
                    MapListActivity mapListActivity = MapListActivity.this;
                    String string = mapListActivity.getString(C5362R.string.tips);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
                    mapListActivity.showLoading(string);
                    MapUpdateManager.INSTANCE.reloadMap(RobotMapManager.INSTANCE.getDefaultPdmap(), new Function1<String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$initView$2.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str) {
                            invoke2(str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String str) {
                            MapListActivity.this.dismissLoading();
                            MapListActivity.this.finish();
                        }
                    });
                    return;
                }
                MapListActivity.this.finish();
            }
        });
        MapUpdateManager.INSTANCE.setOnSuccessListener(new Function2<String, String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url, String operateType) {
                Intrinsics.checkParameterIsNotNull(url, "url");
                Intrinsics.checkParameterIsNotNull(operateType, "operateType");
                if (Intrinsics.areEqual(operateType, "TYPE_UPLOAD")) {
                    Pdlog.m3274e(MapListActivity.this.getClass().getSimpleName(), url);
                    ((MapListViewModel) MapListActivity.this.getMViewModel()).uploadResult(true);
                    return;
                }
                List<RobotMapResp> needUpdateData = MapUpdateManager.INSTANCE.getNeedUpdateData();
                if (needUpdateData != null) {
                    Iterator<RobotMapResp> it = needUpdateData.iterator();
                    while (it.hasNext()) {
                        RobotMapResp next = it.next();
                        if (Intrinsics.areEqual(next.getUrl(), url)) {
                            if (!MapUpdateManager.INSTANCE.checkTempMapRule(next)) {
                                MapUpdateManager.INSTANCE.deleteFailureDownloadFile(next);
                                it.remove();
                            } else {
                                next.setState("1");
                            }
                        }
                    }
                    MapListActivity.this.checkMapDownloadFinish();
                }
            }
        });
        MapUpdateManager.INSTANCE.setOnErrorListener(new Function3<String, UpdateErrorSealed, String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(String str, UpdateErrorSealed updateErrorSealed, String str2) {
                invoke2(str, updateErrorSealed, str2);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url, UpdateErrorSealed errorMsg, String operateType) {
                Intrinsics.checkParameterIsNotNull(url, "url");
                Intrinsics.checkParameterIsNotNull(errorMsg, "errorMsg");
                Intrinsics.checkParameterIsNotNull(operateType, "operateType");
                if (Intrinsics.areEqual(operateType, "TYPE_UPLOAD")) {
                    Pdlog.m3274e(MapListActivity.this.getClass().getSimpleName(), url);
                    ((MapListViewModel) MapListActivity.this.getMViewModel()).uploadResult(false);
                    return;
                }
                List<RobotMapResp> needUpdateData = MapUpdateManager.INSTANCE.getNeedUpdateData();
                if (needUpdateData != null) {
                    Iterator<RobotMapResp> it = needUpdateData.iterator();
                    while (it.hasNext()) {
                        RobotMapResp next = it.next();
                        if (Intrinsics.areEqual(next.getUrl(), url)) {
                            it.remove();
                            next.setState("-1");
                            MapUpdateManager.INSTANCE.deleteFailureDownloadFile(next);
                        }
                    }
                    MapListActivity.this.checkMapDownloadFinish();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void checkMapDownloadFinish() {
        List<RobotMapResp> needUpdateData = MapUpdateManager.INSTANCE.getNeedUpdateData();
        List<RobotMapResp> list = needUpdateData;
        if (list == null || list.isEmpty()) {
            ((MapListViewModel) getMViewModel()).getMapList();
            dismissLoading();
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : needUpdateData) {
            if (((RobotMapResp) obj).getState() == null) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            MapUpdateManager.INSTANCE.moveMapFileAndDelTem(new Function1<Boolean, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$checkMapDownloadFinish$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    MapUpdateManager.INSTANCE.reloadMap(RobotMapManager.INSTANCE.getDefaultPdmap(), new Function1<String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$checkMapDownloadFinish$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str) {
                            invoke2(str);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String str) {
                            RobotMapManager.INSTANCE.reloadMap();
                            ((MapListViewModel) MapListActivity.this.getMViewModel()).getMapList();
                            MapListActivity.this.dismissLoading();
                        }
                    });
                }
            });
        }
    }

    private final void initPSView() {
        View inflate = ((ViewStub) findViewById(C5362R.id.view_stub_ps_check)).inflate();
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view_stub_ps_check.inflate()");
        this.psView = inflate;
        View view = this.psView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("psView");
        }
        View findViewById = view.findViewById(C5362R.id.iv_psw_qrcode);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "psView.findViewById<ImageView>(R.id.iv_psw_qrcode)");
        this.pswQrCode = (ImageView) findViewById;
        Bitmap createQRCodeBitmap = QrCodeUtils.createQRCodeBitmap(WifiUtil.INSTANCE.getMac(), 500, 500, "utf-8", "H", "2", ViewCompat.MEASURED_STATE_MASK, -1, BitmapFactory.decodeResource(getResources(), C5362R.drawable.icon_pudu_logo), 0.2f, null);
        ImageView imageView = this.pswQrCode;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pswQrCode");
        }
        imageView.setImageBitmap(createQRCodeBitmap);
        long currentTimeMillis = System.currentTimeMillis();
        long j = SpUtils.get((Context) this, this.CHECK_EDIT_AUTH_TIME, 0L);
        if (j == 0 || currentTimeMillis - j > 60000) {
            checkEditAuth();
        } else {
            isAuthState();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkEditAuth() {
        ((MapListViewModel) getMViewModel()).checkEditAuth(new Function1<Boolean, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$checkEditAuth$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                String str;
                String str2;
                if (z) {
                    str = MapListActivity.this.TAG;
                    Pdlog.m3273d(str, "checkEditAuth " + z);
                    MapListActivity mapListActivity = MapListActivity.this;
                    MapListActivity mapListActivity2 = mapListActivity;
                    str2 = mapListActivity.CHECK_EDIT_AUTH_TIME;
                    SpUtils.set(mapListActivity2, str2, System.currentTimeMillis());
                    MapListActivity.this.isAuthState();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void isAuthState() {
        initMapListView();
        String string = getString(C5362R.string.loading_map_wating);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.loading_map_wating)");
        BaseActivity.showLoadingDialog$default(this, string, false, 2, null);
        ((MapListViewModel) getMViewModel()).m4339isShouldUpdate();
        changeView(true);
    }

    private final void changeView(boolean isPSPass) {
        initMapListView();
        if (isPSPass) {
            TextView tv_back = (TextView) _$_findCachedViewById(C5362R.id.tv_back);
            Intrinsics.checkExpressionValueIsNotNull(tv_back, "tv_back");
            tv_back.setVisibility(0);
            View view = this.mapListView;
            if (view != null) {
                view.setVisibility(0);
            }
            View view2 = this.psView;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("psView");
            }
            view2.setVisibility(8);
            return;
        }
        View view3 = this.mapListView;
        if (view3 != null) {
            view3.setVisibility(8);
        }
        TextView textView = (TextView) _$_findCachedViewById(C5362R.id.tv_back);
        if (textView != null) {
            textView.setVisibility(8);
        }
        View view4 = this.psView;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("psView");
        }
        view4.setVisibility(0);
    }

    private final void initMapListView() {
        if (this.mapListView == null) {
            this.mapListView = ((ViewStub) findViewById(C5362R.id.view_stub_map_list)).inflate();
            View view = this.mapListView;
            if (view != null) {
                View findViewById = view.findViewById(C5362R.id.rlv_map_list);
                Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.rlv_map_list)");
                this.recyclerview_map_list = (RecyclerView) findViewById;
                RecyclerView recyclerView = this.recyclerview_map_list;
                if (recyclerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerview_map_list");
                }
                recyclerView.setAdapter(this.adapter);
                RecyclerView recyclerView2 = this.recyclerview_map_list;
                if (recyclerView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerview_map_list");
                }
                recyclerView2.setLayoutManager(new GridLayoutManager(this, 3));
                this.adapter.setOnItemEditClick(new Function1<MapInfo, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$initMapListView$$inlined$let$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MapInfo mapInfo) {
                        invoke2(mapInfo);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MapInfo it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Pdlog.m3273d(MapListActivity.this.getLocalClassName(), it);
                        MapListActivity.this.isEdit = true;
                        MapListActivity mapListActivity = MapListActivity.this;
                        String string = mapListActivity.getString(C5362R.string.map_decode);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.map_decode)");
                        mapListActivity.showLoading(string);
                        ((MapListViewModel) MapListActivity.this.getMViewModel()).jumpMap(it);
                    }
                });
                this.adapter.setOnItemAddClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$initMapListView$$inlined$let$lambda$2
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
                        MapListActivity.this.isEdit = true;
                        Intent intent = new Intent(MapListActivity.this, (Class<?>) CreateMapNoticeActivity.class);
                        intent.putExtra(Constants.TYPE_CREATE_MAP_ARGUMENTS, 1);
                        intent.putExtra("from_edit_map_arguments", true);
                        MapListActivity.this.startActivity(intent);
                        AbnormalManager.INSTANCE.addHardWareListener();
                    }
                });
            }
        }
    }

    private final void showInputDialog(final MapInfo item) {
        final InputFreeInstallDialog inputFreeInstallDialog = new InputFreeInstallDialog(this);
        String string = getString(C5362R.string.please_input_name);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.please_input_name)");
        inputFreeInstallDialog.setTitle(string);
        String string2 = getString(C5362R.string.sure);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.sure)");
        inputFreeInstallDialog.setBtnText(string2);
        inputFreeInstallDialog.setOnInputResult(new Function1<String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$showInputDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                boolean checkSameName;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (!(it.length() == 0)) {
                    checkSameName = MapListActivity.this.checkSameName(it);
                    if (checkSameName) {
                        ToastUtils toastUtils = ToastUtils.INSTANCE;
                        String string3 = MapListActivity.this.getString(C5362R.string.name_repeat);
                        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.name_repeat)");
                        toastUtils.showShortToast(string3);
                        return;
                    }
                    inputFreeInstallDialog.dismiss();
                    MapListActivity mapListActivity = MapListActivity.this;
                    String string4 = mapListActivity.getString(C5362R.string.map_create_in);
                    Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.map_create_in)");
                    mapListActivity.showLoading(string4);
                    ((MapListViewModel) MapListActivity.this.getMViewModel()).copyData(item, it);
                    return;
                }
                ToastUtils toastUtils2 = ToastUtils.INSTANCE;
                String string5 = MapListActivity.this.getString(C5362R.string.please_input_name);
                Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.please_input_name)");
                toastUtils2.showShortToast(string5);
            }
        });
        inputFreeInstallDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkSameName(String s) {
        List<MapEditSelectItem> data;
        MapEditSelectAdapter mapEditSelectAdapter = this.adapter;
        boolean z = false;
        if (mapEditSelectAdapter != null && (data = mapEditSelectAdapter.getData()) != null) {
            Iterator<T> it = data.iterator();
            while (it.hasNext()) {
                MapInfo mapInfo = ((MapEditSelectItem) it.next()).getMapInfo();
                if (mapInfo == null) {
                    Intrinsics.throwNpe();
                }
                if (Intrinsics.areEqual(mapInfo.getMapName(), s)) {
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void showLoading(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (this.downloadDialog == null) {
            this.downloadDialog = new LoadingDialog();
        }
        LoadingDialog loadingDialog = this.downloadDialog;
        if (loadingDialog != null) {
            loadingDialog.closeVisible(false);
            loadingDialog.setTitle(message);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            String localClassName = getLocalClassName();
            Intrinsics.checkExpressionValueIsNotNull(localClassName, "this@MapListActivity.localClassName");
            loadingDialog.showDialog(supportFragmentManager, localClassName);
        }
    }

    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void dismissLoading() {
        LoadingDialog loadingDialog = this.downloadDialog;
        if (loadingDialog != null) {
            loadingDialog.dismissDialog();
        }
        this.downloadDialog = (LoadingDialog) null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        MapListActivity mapListActivity = this;
        ((MapListViewModel) getMViewModel()).getData().observe(mapListActivity, new Observer<List<MapInfo>>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<MapInfo> t) {
                MapEditSelectAdapter mapEditSelectAdapter;
                MapListActivity.this.dismissLoadingDialog();
                ArrayList arrayList = new ArrayList();
                arrayList.add(new MapEditSelectItem(null, null));
                Intrinsics.checkExpressionValueIsNotNull(t, "t");
                for (MapInfo mapInfo : t) {
                    File file = new File(mapInfo.getMapRoot() + "/optemap.png");
                    if (!file.exists()) {
                        file = new File(mapInfo.getMapRoot() + "/imgmap.png");
                    }
                    arrayList.add(new MapEditSelectItem(mapInfo, file));
                }
                mapEditSelectAdapter = MapListActivity.this.adapter;
                mapEditSelectAdapter.setNewData(arrayList);
                MapListActivity.this.dismissLoading();
            }
        });
        ((MapListViewModel) getMViewModel()).isShouldUpdate().observe(mapListActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$createObserver$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean isShouldUpdate) {
                Intrinsics.checkExpressionValueIsNotNull(isShouldUpdate, "isShouldUpdate");
                if (isShouldUpdate.booleanValue()) {
                    MapListActivity.this.showUpdateDialog();
                } else {
                    ((MapListViewModel) MapListActivity.this.getMViewModel()).getMapList();
                }
            }
        });
        ((MapListViewModel) getMViewModel()).isEdit().observe(mapListActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$createObserver$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                MapListActivity.this.dismissLoading();
                Intent intent = new Intent(MapListActivity.this, (Class<?>) SelectMapSettingActivity.class);
                intent.putExtra("from", 3);
                MapListActivity.this.startActivity(intent);
            }
        });
        ((MapListViewModel) getMViewModel()).isReload().observe(mapListActivity, new Observer<RobotMapResp>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$createObserver$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(RobotMapResp robotMapResp) {
                LoadingDialog loadingDialog;
                if (robotMapResp == null) {
                    MapListActivity mapListActivity2 = MapListActivity.this;
                    String string = mapListActivity2.getString(C5362R.string.map_download_load_fail);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.map_download_load_fail)");
                    Toast makeText = Toast.makeText(mapListActivity2, string, 0);
                    makeText.show();
                    Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
                }
                ((MapListViewModel) MapListActivity.this.getMViewModel()).getMapList();
                loadingDialog = MapListActivity.this.downloadDialog;
                if (loadingDialog != null) {
                    loadingDialog.dismissDialog();
                }
            }
        });
        ((MapListViewModel) getMViewModel()).getToast().observe(mapListActivity, new Observer<String>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$createObserver$5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String it) {
                MapListActivity.this.dismissLoading();
                MapListActivity mapListActivity2 = MapListActivity.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Toast makeText = Toast.makeText(mapListActivity2, it, 0);
                makeText.show();
                Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
            }
        });
        ((MapListViewModel) getMViewModel()).isCopySuccess().observe(mapListActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$createObserver$6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean isCopySuccess) {
                MapListActivity.this.dismissLoading();
                Pdlog.m3273d("MapListActivity", isCopySuccess);
                Intrinsics.checkExpressionValueIsNotNull(isCopySuccess, "isCopySuccess");
                if (isCopySuccess.booleanValue()) {
                    MapListActivity mapListActivity2 = MapListActivity.this;
                    String string = mapListActivity2.getString(C5362R.string.operation_success);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.operation_success)");
                    Toast makeText = Toast.makeText(mapListActivity2, string, 0);
                    makeText.show();
                    Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
                    return;
                }
                MapListActivity mapListActivity3 = MapListActivity.this;
                String string2 = mapListActivity3.getString(C5362R.string.operation_fail);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.operation_fail)");
                Toast makeText2 = Toast.makeText(mapListActivity3, string2, 0);
                makeText2.show();
                Intrinsics.checkExpressionValueIsNotNull(makeText2, "Toast\n        .makeText(…         show()\n        }");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showUpdateDialog() {
        dismissLoading();
        CommonDialog.Builder builder = new CommonDialog.Builder(this);
        String string = getString(C5362R.string.tips);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
        CommonDialog.Builder title = builder.setTitle(string);
        String string2 = getString(C5362R.string.robot_map_need_update);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.robot_map_need_update)");
        CommonDialog.Builder minContent = title.setMinContent(string2);
        String string3 = getString(C5362R.string.to_edit);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.to_edit)");
        CommonDialog.Builder btLeft = minContent.setBtLeft(string3, CommonDialog.BtBg.BLUE_WIDTH, getColor(C5362R.color.color_0072FF));
        String string4 = getString(C5362R.string.download_map);
        Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.download_map)");
        this.updateDialog = btLeft.setBtRight(string4, CommonDialog.BtBg.BLUE, getColor(C5362R.color.white)).setClose(false).create();
        CommonDialog commonDialog = this.updateDialog;
        if (commonDialog != null) {
            commonDialog.show();
        }
        CommonDialog commonDialog2 = this.updateDialog;
        if (commonDialog2 != null) {
            commonDialog2.setBtLeftClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$showUpdateDialog$1
                /* JADX INFO: Access modifiers changed from: package-private */
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
                    CommonDialog commonDialog3;
                    commonDialog3 = MapListActivity.this.updateDialog;
                    if (commonDialog3 != null) {
                        commonDialog3.dismiss();
                    }
                    ((MapListViewModel) MapListActivity.this.getMViewModel()).getMapList();
                }
            });
        }
        CommonDialog commonDialog3 = this.updateDialog;
        if (commonDialog3 != null) {
            commonDialog3.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapListActivity$showUpdateDialog$2
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
                    CommonDialog commonDialog4;
                    MapUpdateManager.INSTANCE.downloadMap();
                    commonDialog4 = MapListActivity.this.updateDialog;
                    if (commonDialog4 != null) {
                        commonDialog4.dismiss();
                    }
                    MapListActivity mapListActivity = MapListActivity.this;
                    String string5 = mapListActivity.getString(C5362R.string.map_downloading);
                    Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.map_downloading)");
                    mapListActivity.showLoading(string5);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        AbnormalManager.INSTANCE.removeHardWareListener();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        MapUpdateManager.INSTANCE.release();
        this.updateDialog = (CommonDialog) null;
        LoadingDialog loadingDialog = this.downloadDialog;
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        this.downloadDialog = (LoadingDialog) null;
    }
}
