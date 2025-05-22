package com.pudutech.freeinstall_ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.adapter.MapDownloadAdapter;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.freeinstall_ui.dialog.LoadingDialog;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.viewmodel.MapDownloadViewModel;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import com.pudutech.module_robot_selfcheck.oss.UpdateErrorSealed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapDownloadActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005*\u0001\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u001cH\u0016J\b\u0010\"\u001a\u00020\u001cH\u0002J\b\u0010#\u001a\u00020\u001cH\u0002J\u0012\u0010$\u001a\u00020\u001c2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u0010'\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020 H\u0002J\b\u0010)\u001a\u00020\u001cH\u0002J\b\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020\u001cH\u0002J\b\u0010-\u001a\u00020\u001cH\u0002J\b\u0010.\u001a\u00020\u001cH\u0016J\b\u0010/\u001a\u00020\u001cH\u0014J\u0010\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u00020\u001cH\u0002J\u0010\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u000202H\u0016J\b\u00106\u001a\u00020\u001cH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011¨\u00067"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/MapDownloadActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/MapDownloadViewModel;", "()V", "adapter", "Lcom/pudutech/freeinstall_ui/adapter/MapDownloadAdapter;", "getAdapter", "()Lcom/pudutech/freeinstall_ui/adapter/MapDownloadAdapter;", "currentSelectMap", "Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;", "downloadDialog", "Lcom/pudutech/freeinstall_ui/dialog/LoadingDialog;", "emptyDialog", "Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "getEmptyDialog", "()Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "setEmptyDialog", "(Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;)V", "locateHintDialog", "getLocateHintDialog", "setLocateHintDialog", "switchMapResultListener", "com/pudutech/freeinstall_ui/activity/MapDownloadActivity$switchMapResultListener$1", "Lcom/pudutech/freeinstall_ui/activity/MapDownloadActivity$switchMapResultListener$1;", "tipsDialog", "getTipsDialog", "setTipsDialog", "changeView", "", "checkLocation", "createObserver", "currentActivityIsDark", "", "dismissLoading", "initDownload", "initServiceListener", "initView", "saveInstanceState", "Landroid/os/Bundle;", "isDataEmpty", "isEmpty", "jumpToHome", "layoutId", "", "notifyComplete", "onBack", "onBackPressed", "onDestroy", "showEmptyDialog", NotificationCompat.CATEGORY_MESSAGE, "", "showErrorDialog", "showLoading", "message", "showLocateHintDialog", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapDownloadActivity extends BaseActivity<MapDownloadViewModel> {
    private HashMap _$_findViewCache;
    private RobotMapResp currentSelectMap;
    private LoadingDialog downloadDialog;
    private CommonDialog emptyDialog;
    private CommonDialog locateHintDialog;
    private CommonDialog tipsDialog;
    private final MapDownloadAdapter adapter = new MapDownloadAdapter();
    private final MapDownloadActivity$switchMapResultListener$1 switchMapResultListener = new RobotMapManager.SwitchMapResultListener() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$switchMapResultListener$1
        @Override // com.pudutech.mirsdkwrap.lib.map.RobotMapManager.SwitchMapResultListener
        public void onResult(boolean b, String errorMsg) {
            Pdlog.m3273d("MapDownloadActivity", "switchMapResultListener onResult = " + b + ' ' + errorMsg);
            MapDownloadActivity.this.dismissLoading();
            if (b) {
                MapDownloadActivity.this.showLocateHintDialog();
                MapDownloadActivity.this.checkLocation();
            } else {
                MapDownloadActivity.this.showErrorDialog();
            }
        }
    };

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

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5362R.layout.activity_map_download;
    }

    public final MapDownloadAdapter getAdapter() {
        return this.adapter;
    }

    public final CommonDialog getTipsDialog() {
        return this.tipsDialog;
    }

    public final void setTipsDialog(CommonDialog commonDialog) {
        this.tipsDialog = commonDialog;
    }

    public final CommonDialog getEmptyDialog() {
        return this.emptyDialog;
    }

    public final void setEmptyDialog(CommonDialog commonDialog) {
        this.emptyDialog = commonDialog;
    }

    public final CommonDialog getLocateHintDialog() {
        return this.locateHintDialog;
    }

    public final void setLocateHintDialog(CommonDialog commonDialog) {
        this.locateHintDialog = commonDialog;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        RobotMapManager.INSTANCE.addSwitchMapResultListener(this.switchMapResultListener);
        initDownload();
        RecyclerView recyclerview_map_list = (RecyclerView) _$_findCachedViewById(C5362R.id.recyclerview_map_list);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview_map_list, "recyclerview_map_list");
        recyclerview_map_list.setAdapter(this.adapter);
        RecyclerView recyclerview_map_list2 = (RecyclerView) _$_findCachedViewById(C5362R.id.recyclerview_map_list);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview_map_list2, "recyclerview_map_list");
        recyclerview_map_list2.setLayoutManager(new LinearLayoutManager(this));
        ((MapDownloadViewModel) getMViewModel()).getMapListData();
        this.adapter.setOnErrorRetryListener(new Function3<String, Boolean, RobotMapResp, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool, RobotMapResp robotMapResp) {
                invoke(str, bool.booleanValue(), robotMapResp);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(String str, boolean z, RobotMapResp item) {
                Intrinsics.checkParameterIsNotNull(item, "item");
                if (str != null) {
                    if (z) {
                        MapDownloadActivity mapDownloadActivity = MapDownloadActivity.this;
                        String string = mapDownloadActivity.getString(C5362R.string.map_decode);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.map_decode)");
                        mapDownloadActivity.showLoading(string);
                        ((MapDownloadViewModel) MapDownloadActivity.this.getMViewModel()).jumpMap(item);
                        return;
                    }
                    if (Intrinsics.areEqual(item.getState(), "-1")) {
                        MapUpdateManager.INSTANCE.downloadOne(item);
                    }
                }
            }
        });
        this.adapter.setOnItemCheckListener(new Function2<String, Boolean, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String it1, boolean z) {
                Intrinsics.checkParameterIsNotNull(it1, "it1");
                if (z) {
                    List<RobotMapResp> data = MapDownloadActivity.this.getAdapter().getData();
                    if (data != null) {
                        for (RobotMapResp robotMapResp : data) {
                            robotMapResp.setSetting(Intrinsics.areEqual(robotMapResp.getName(), it1));
                        }
                    }
                    MapDownloadActivity.this.getAdapter().notifyDataSetChanged();
                    return;
                }
                List<RobotMapResp> data2 = MapDownloadActivity.this.getAdapter().getData();
                if (data2 != null) {
                    Iterator<T> it = data2.iterator();
                    while (it.hasNext()) {
                        ((RobotMapResp) it.next()).setSetting(false);
                    }
                }
            }
        });
        Button button = (Button) _$_findCachedViewById(C5362R.id.btn_start_download);
        final long j = 800;
        button.setOnClickListener(new MapDownloadActivity$initView$$inlined$singleClick$1(button, 800L, this));
        final TextView textView = (TextView) _$_findCachedViewById(C5362R.id.tv_back);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$initView$$inlined$singleClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView) > j || (textView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView, currentTimeMillis);
                    this.onBack();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpToHome() {
        AppContext.StartActivityListener listener = AppContext.INSTANCE.getListener();
        if (listener != null) {
            Context applicationContext = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            listener.startDeliActivity(applicationContext);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showErrorDialog() {
        if (this.tipsDialog == null) {
            CommonDialog.Builder builder = new CommonDialog.Builder(this);
            String string = getString(C5362R.string.tips);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
            CommonDialog.Builder title = builder.setTitle(string);
            String string2 = getString(C5362R.string.map_exception);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.map_exception)");
            CommonDialog.Builder minContent = title.setMinContent(string2);
            String string3 = getString(C5362R.string.confirm_free);
            Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.confirm_free)");
            this.tipsDialog = minContent.setBtRight(string3).setClose(false).create();
            final CommonDialog commonDialog = this.tipsDialog;
            if (commonDialog != null) {
                commonDialog.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$showErrorDialog$1$1
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
                        CommonDialog.this.dismiss();
                    }
                });
            }
        }
        CommonDialog commonDialog2 = this.tipsDialog;
        if (commonDialog2 != null) {
            commonDialog2.show();
        }
    }

    private final void initDownload() {
        initServiceListener();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        onBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBack() {
        finish();
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
        }
        LoadingDialog loadingDialog2 = this.downloadDialog;
        if (loadingDialog2 != null) {
            loadingDialog2.setTitle(message);
        }
        LoadingDialog loadingDialog3 = this.downloadDialog;
        if (loadingDialog3 != null) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            String localClassName = getLocalClassName();
            Intrinsics.checkExpressionValueIsNotNull(localClassName, "this.localClassName");
            loadingDialog3.showDialog(supportFragmentManager, localClassName);
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
        MapDownloadActivity mapDownloadActivity = this;
        ((MapDownloadViewModel) getMViewModel()).getData().observe(mapDownloadActivity, new Observer<List<RobotMapResp>>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<RobotMapResp> list) {
                List<RobotMapResp> list2 = list;
                boolean z = true;
                if (list2 == null || list2.isEmpty()) {
                    MapDownloadActivity.this.isDataEmpty(true);
                    return;
                }
                MapDownloadActivity.this.isDataEmpty(false);
                List<String> tempFileList = ((MapDownloadViewModel) MapDownloadActivity.this.getMViewModel()).getTempFileList(list);
                Pdlog.m3274e(MapDownloadActivity.this.getLocalClassName(), tempFileList);
                List<String> list3 = tempFileList;
                if (list3 != null && !list3.isEmpty()) {
                    z = false;
                }
                if (z) {
                    MapDownloadActivity.this.getAdapter().setShowCheck(false);
                } else if (tempFileList.size() == list.size()) {
                    List<RobotMapResp> list4 = list;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
                    Iterator<T> it = list4.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((RobotMapResp) it.next()).getName());
                    }
                    if (arrayList.containsAll(list3)) {
                        MapDownloadActivity mapDownloadActivity2 = MapDownloadActivity.this;
                        String string = mapDownloadActivity2.getString(C5362R.string.map_decode);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.map_decode)");
                        mapDownloadActivity2.showLoading(string);
                        MapUpdateManager.INSTANCE.moveMapFileAndDelTem(new Function1<Boolean, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$createObserver$1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z2) {
                                MapDownloadActivity.this.getAdapter().setShowCheck(true);
                                MapDownloadActivity.this.dismissLoading();
                            }
                        });
                    }
                } else {
                    MapDownloadActivity.this.getAdapter().setShowCheck(false);
                }
                MapDownloadActivity.this.changeView();
                MapDownloadActivity.this.getAdapter().setData(list);
                MapDownloadActivity.this.getAdapter().notifyDataSetChanged();
            }
        });
        ((MapDownloadViewModel) getMViewModel()).isEdit().observe(mapDownloadActivity, new MapDownloadActivity$createObserver$2(this));
        ((MapDownloadViewModel) getMViewModel()).isReload().observe(mapDownloadActivity, new Observer<String>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$createObserver$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String str) {
                if (str != null) {
                    RobotMapManager.INSTANCE.reloadMap(RobotMapManager.INSTANCE.getCurrentFloorName(), str);
                } else {
                    MapDownloadActivity.this.dismissLoading();
                    MapDownloadActivity.this.showErrorDialog();
                }
            }
        });
        ((MapDownloadViewModel) getMViewModel()).getToast().observe(mapDownloadActivity, new Observer<String>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$createObserver$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String it) {
                MapDownloadActivity mapDownloadActivity2 = MapDownloadActivity.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Toast makeText = Toast.makeText(mapDownloadActivity2, it, 0);
                makeText.show();
                Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkLocation() {
        RobotMapManager.INSTANCE.checkLocationSelftInit(new Function1<Boolean, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$checkLocation$1
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
                Pdlog.m3273d(MapDownloadActivity.this.getLocalClassName(), "getMapInitStatus " + z + ' ' + MapDownloadActivity.this.getLocateHintDialog());
                if (z) {
                    CommonDialog locateHintDialog = MapDownloadActivity.this.getLocateHintDialog();
                    if (locateHintDialog != null) {
                        locateHintDialog.dismiss();
                    }
                    MapDownloadActivity.this.jumpToHome();
                }
            }
        }, 15000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLocateHintDialog() {
        if (this.locateHintDialog == null) {
            CommonDialog.Builder builder = new CommonDialog.Builder(this);
            String string = getString(C5362R.string.tips);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
            CommonDialog.Builder title = builder.setTitle(string);
            String string2 = getString(C5362R.string.map_success_locate_hint);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.map_success_locate_hint)");
            this.locateHintDialog = title.setMinContent(string2).setClose(true).create();
            final CommonDialog commonDialog = this.locateHintDialog;
            if (commonDialog != null) {
                commonDialog.setCanceledOnTouchOutside(false);
                Button btLeft = commonDialog.getBtLeft();
                if (btLeft != null) {
                    btLeft.setVisibility(8);
                }
                Button btRight = commonDialog.getBtRight();
                if (btRight != null) {
                    btRight.setVisibility(8);
                }
                commonDialog.setCloseClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$showLocateHintDialog$1$1
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
                        CommonDialog.this.dismiss();
                    }
                });
            }
        }
        CommonDialog commonDialog2 = this.locateHintDialog;
        if (commonDialog2 != null) {
            commonDialog2.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void isDataEmpty(boolean isEmpty) {
        if (isEmpty) {
            RecyclerView recyclerview_map_list = (RecyclerView) _$_findCachedViewById(C5362R.id.recyclerview_map_list);
            Intrinsics.checkExpressionValueIsNotNull(recyclerview_map_list, "recyclerview_map_list");
            recyclerview_map_list.setVisibility(8);
            Button btn_start_download = (Button) _$_findCachedViewById(C5362R.id.btn_start_download);
            Intrinsics.checkExpressionValueIsNotNull(btn_start_download, "btn_start_download");
            btn_start_download.setText(getString(C5362R.string.start_create_map));
            TextView tv_map_empty = (TextView) _$_findCachedViewById(C5362R.id.tv_map_empty);
            Intrinsics.checkExpressionValueIsNotNull(tv_map_empty, "tv_map_empty");
            tv_map_empty.setVisibility(0);
            return;
        }
        RecyclerView recyclerview_map_list2 = (RecyclerView) _$_findCachedViewById(C5362R.id.recyclerview_map_list);
        Intrinsics.checkExpressionValueIsNotNull(recyclerview_map_list2, "recyclerview_map_list");
        recyclerview_map_list2.setVisibility(0);
        Button btn_start_download2 = (Button) _$_findCachedViewById(C5362R.id.btn_start_download);
        Intrinsics.checkExpressionValueIsNotNull(btn_start_download2, "btn_start_download");
        btn_start_download2.setText(getString(C5362R.string.start_download));
        TextView tv_map_empty2 = (TextView) _$_findCachedViewById(C5362R.id.tv_map_empty);
        Intrinsics.checkExpressionValueIsNotNull(tv_map_empty2, "tv_map_empty");
        tv_map_empty2.setVisibility(8);
    }

    private final void showEmptyDialog(String msg) {
        if (this.emptyDialog == null) {
            CommonDialog.Builder builder = new CommonDialog.Builder(this);
            String string = getString(C5362R.string.tips);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
            CommonDialog.Builder minContent = builder.setTitle(string).setMinContent(msg);
            String string2 = getString(C5362R.string.confirm_free);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.confirm_free)");
            this.emptyDialog = minContent.setBtRight(string2).setClose(false).create();
            final CommonDialog commonDialog = this.emptyDialog;
            if (commonDialog != null) {
                commonDialog.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$showEmptyDialog$1$1
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
                        CommonDialog.this.dismiss();
                    }
                });
            }
        }
        CommonDialog commonDialog2 = this.emptyDialog;
        if (commonDialog2 != null) {
            commonDialog2.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeView() {
        if (this.adapter.getIsShowCheck()) {
            TextView tv_map_download_title = (TextView) _$_findCachedViewById(C5362R.id.tv_map_download_title);
            Intrinsics.checkExpressionValueIsNotNull(tv_map_download_title, "tv_map_download_title");
            tv_map_download_title.setVisibility(0);
            Button btn_start_download = (Button) _$_findCachedViewById(C5362R.id.btn_start_download);
            Intrinsics.checkExpressionValueIsNotNull(btn_start_download, "btn_start_download");
            btn_start_download.setText(getString(C5362R.string.next_step));
            return;
        }
        TextView tv_map_download_title2 = (TextView) _$_findCachedViewById(C5362R.id.tv_map_download_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_map_download_title2, "tv_map_download_title");
        tv_map_download_title2.setVisibility(8);
        Button btn_start_download2 = (Button) _$_findCachedViewById(C5362R.id.btn_start_download);
        Intrinsics.checkExpressionValueIsNotNull(btn_start_download2, "btn_start_download");
        btn_start_download2.setText(getString(C5362R.string.start_download));
    }

    private final void initServiceListener() {
        MapUpdateManager.INSTANCE.setOnSuccessListener(new Function2<String, String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$initServiceListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final String path, String operateType) {
                Intrinsics.checkParameterIsNotNull(path, "path");
                Intrinsics.checkParameterIsNotNull(operateType, "operateType");
                ((RecyclerView) MapDownloadActivity.this._$_findCachedViewById(C5362R.id.recyclerview_map_list)).post(new Runnable() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$initServiceListener$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        List<RobotMapResp> data = MapDownloadActivity.this.getAdapter().getData();
                        if (data != null) {
                            Iterator<RobotMapResp> it = data.iterator();
                            while (it.hasNext()) {
                                RobotMapResp next = it.next();
                                if (Intrinsics.areEqual(next.getUrl(), path)) {
                                    if (!MapUpdateManager.INSTANCE.checkTempMapRule(next)) {
                                        MapUpdateManager.INSTANCE.deleteFailureDownloadFile(next);
                                        it.remove();
                                    } else {
                                        next.setState("1");
                                    }
                                }
                            }
                        }
                        MapDownloadActivity.this.getAdapter().notifyDataSetChanged();
                        MapDownloadActivity.this.notifyComplete();
                    }
                });
            }
        });
        MapUpdateManager mapUpdateManager = MapUpdateManager.INSTANCE;
        if (mapUpdateManager != null) {
            mapUpdateManager.setOnErrorListener(new Function3<String, UpdateErrorSealed, String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$initServiceListener$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(String str, UpdateErrorSealed updateErrorSealed, String str2) {
                    invoke2(str, updateErrorSealed, str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final String path, final UpdateErrorSealed errorMsg, String operateType) {
                    Intrinsics.checkParameterIsNotNull(path, "path");
                    Intrinsics.checkParameterIsNotNull(errorMsg, "errorMsg");
                    Intrinsics.checkParameterIsNotNull(operateType, "operateType");
                    ((RecyclerView) MapDownloadActivity.this._$_findCachedViewById(C5362R.id.recyclerview_map_list)).post(new Runnable() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$initServiceListener$2.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            List<RobotMapResp> data = MapDownloadActivity.this.getAdapter().getData();
                            if (data != null) {
                                Iterator<RobotMapResp> it = data.iterator();
                                while (it.hasNext()) {
                                    RobotMapResp next = it.next();
                                    if (Intrinsics.areEqual(next.getUrl(), path)) {
                                        it.remove();
                                        MapUpdateManager.INSTANCE.deleteFailureDownloadFile(next);
                                    }
                                }
                            }
                            MapDownloadActivity.this.getAdapter().notifyDataSetChanged();
                            MapDownloadActivity.this.notifyComplete();
                            Pdlog.m3274e("taskFailure", path, errorMsg);
                        }
                    });
                }
            });
        }
        MapUpdateManager mapUpdateManager2 = MapUpdateManager.INSTANCE;
        if (mapUpdateManager2 != null) {
            mapUpdateManager2.setUpdateProgressListener(new Function3<String, String, String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$initServiceListener$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, String str3) {
                    invoke2(str, str2, str3);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final String path, final String progress, String operateType) {
                    Intrinsics.checkParameterIsNotNull(path, "path");
                    Intrinsics.checkParameterIsNotNull(progress, "progress");
                    Intrinsics.checkParameterIsNotNull(operateType, "operateType");
                    ((RecyclerView) MapDownloadActivity.this._$_findCachedViewById(C5362R.id.recyclerview_map_list)).post(new Runnable() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$initServiceListener$3.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            List<RobotMapResp> data = MapDownloadActivity.this.getAdapter().getData();
                            if (data != null) {
                                for (RobotMapResp robotMapResp : data) {
                                    if (Intrinsics.areEqual(robotMapResp.getUrl(), path)) {
                                        robotMapResp.setState("0");
                                        robotMapResp.setProgress("正在下载  " + progress + '%');
                                    }
                                }
                            }
                            MapDownloadActivity.this.getAdapter().notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyComplete() {
        List<RobotMapResp> data = this.adapter.getData();
        if (data != null) {
            Pdlog.m3274e(getLocalClassName(), "notifyComplete", data);
            ArrayList arrayList = new ArrayList();
            for (Object obj : data) {
                RobotMapResp robotMapResp = (RobotMapResp) obj;
                if (Intrinsics.areEqual(robotMapResp.getState(), "0") || robotMapResp.getState() == null) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.isEmpty()) {
                Button btn_start_download = (Button) _$_findCachedViewById(C5362R.id.btn_start_download);
                Intrinsics.checkExpressionValueIsNotNull(btn_start_download, "btn_start_download");
                btn_start_download.setEnabled(true);
                List<RobotMapResp> errorData = MapUpdateManager.INSTANCE.getErrorData();
                if (!(errorData == null || errorData.isEmpty())) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(getString(C5362R.string.map_invalid_remove));
                    sb.append('\n');
                    List<RobotMapResp> errorData2 = MapUpdateManager.INSTANCE.getErrorData();
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(errorData2, 10));
                    Iterator<T> it = errorData2.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(((RobotMapResp) it.next()).getName());
                    }
                    sb.append(CollectionsKt.joinToString$default(arrayList2, ",", null, null, 0, null, null, 62, null));
                    showEmptyDialog(sb.toString());
                }
                List<RobotMapResp> list = data;
                if (list == null || list.isEmpty()) {
                    isDataEmpty(true);
                } else {
                    Pdlog.m3274e(getLocalClassName(), "notifyComplete");
                    String string = getString(C5362R.string.loading_map_wating);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.loading_map_wating)");
                    showLoading(string);
                    MapUpdateManager.INSTANCE.moveMapFileAndDelTem(new Function1<Boolean, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapDownloadActivity$notifyComplete$$inlined$let$lambda$1
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
                            MapDownloadActivity.this.dismissLoading();
                            MapDownloadActivity.this.getAdapter().setShowCheck(true);
                            MapDownloadActivity.this.getAdapter().notifyDataSetChanged();
                            MapDownloadActivity.this.changeView();
                        }
                    });
                }
            }
            Pdlog.m3274e("taskComplete", "taskComplete");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LoadingDialog loadingDialog = this.downloadDialog;
        if (loadingDialog != null) {
            loadingDialog.dismissDialog();
        }
        this.downloadDialog = (LoadingDialog) null;
        super.onDestroy();
        CommonDialog commonDialog = this.tipsDialog;
        if (commonDialog != null && commonDialog.isShowing()) {
            CommonDialog commonDialog2 = this.tipsDialog;
            if (commonDialog2 != null) {
                commonDialog2.dismiss();
            }
            this.tipsDialog = (CommonDialog) null;
        }
        CommonDialog commonDialog3 = this.locateHintDialog;
        if (commonDialog3 != null) {
            commonDialog3.dismiss();
        }
        CommonDialog commonDialog4 = (CommonDialog) null;
        this.locateHintDialog = commonDialog4;
        this.emptyDialog = commonDialog4;
        RobotMapManager.INSTANCE.removeSwitchMapResultListener(this.switchMapResultListener);
        MapUpdateManager.INSTANCE.release();
    }
}
