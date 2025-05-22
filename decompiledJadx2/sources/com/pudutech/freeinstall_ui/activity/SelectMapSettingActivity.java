package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.util.StringExtKt;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.disinfect.baselib.util.DensityUtil;
import com.pudutech.freeinstall_ui.adapter.BusinessSetAdapter;
import com.pudutech.freeinstall_ui.adapter.BusinessSetItem;
import com.pudutech.freeinstall_ui.adapter.FunctionSelectAdapter;
import com.pudutech.freeinstall_ui.adapter.FunctionSelectItem;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.freeinstall_ui.utils.CommonDialogUtils;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.view.GridDividerItemDecoration;
import com.pudutech.freeinstall_ui.view.NodeProgressBar;
import com.pudutech.freeinstall_ui.viewmodel.EditMapViewModel;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: SelectMapSettingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 ^2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001^B\u0005¢\u0006\u0002\u0010\u0003J\n\u0010>\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010?\u001a\u00020%H\u0002J\n\u0010@\u001a\u0004\u0018\u00010\u000bH\u0002J\b\u0010A\u001a\u00020BH\u0002J\b\u0010C\u001a\u00020BH\u0016J\b\u0010D\u001a\u00020%H\u0016J\b\u0010E\u001a\u00020BH\u0002J\b\u0010F\u001a\u00020BH\u0002J\b\u0010G\u001a\u00020BH\u0002J\b\u0010H\u001a\u00020BH\u0002J\u0012\u0010I\u001a\u00020B2\b\u0010J\u001a\u0004\u0018\u00010KH\u0016J\b\u0010L\u001a\u00020\u0011H\u0016J\b\u0010M\u001a\u00020BH\u0014J\u0012\u0010N\u001a\u00020B2\b\u0010O\u001a\u0004\u0018\u00010PH\u0014J\b\u0010Q\u001a\u00020BH\u0002J\b\u0010R\u001a\u00020BH\u0002J\b\u0010S\u001a\u00020BH\u0002J\b\u0010T\u001a\u00020BH\u0002J\b\u0010U\u001a\u00020BH\u0002J\b\u0010V\u001a\u00020BH\u0002J\b\u0010W\u001a\u00020BH\u0002J\b\u0010X\u001a\u00020BH\u0002J\b\u0010Y\u001a\u00020BH\u0002J\b\u0010Z\u001a\u00020BH\u0002J\b\u0010[\u001a\u00020BH\u0002J\b\u0010\\\u001a\u00020BH\u0002J\b\u0010]\u001a\u00020BH\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R*\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u0017j\b\u0012\u0004\u0012\u00020\u0018`\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010'\"\u0004\b,\u0010)R\u001a\u0010-\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010'\"\u0004\b.\u0010)R*\u0010/\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0017j\b\u0012\u0004\u0012\u00020\u000b`\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001b\"\u0004\b1\u0010\u001dR\u001a\u00102\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010'\"\u0004\b4\u0010)R\u001a\u00105\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0013\"\u0004\b7\u0010\u0015R\u001c\u00108\u001a\u0004\u0018\u000109X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=¨\u0006_"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/SelectMapSettingActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/EditMapViewModel;", "()V", "businessSetAdapter", "Lcom/pudutech/freeinstall_ui/adapter/BusinessSetAdapter;", "getBusinessSetAdapter", "()Lcom/pudutech/freeinstall_ui/adapter/BusinessSetAdapter;", "setBusinessSetAdapter", "(Lcom/pudutech/freeinstall_ui/adapter/BusinessSetAdapter;)V", "clickItem", "Lcom/pudutech/freeinstall_ui/adapter/BusinessSetItem;", "getClickItem", "()Lcom/pudutech/freeinstall_ui/adapter/BusinessSetItem;", "setClickItem", "(Lcom/pudutech/freeinstall_ui/adapter/BusinessSetItem;)V", "from", "", "getFrom", "()I", "setFrom", "(I)V", "functionItemList", "Ljava/util/ArrayList;", "Lcom/pudutech/freeinstall_ui/adapter/FunctionSelectItem;", "Lkotlin/collections/ArrayList;", "getFunctionItemList", "()Ljava/util/ArrayList;", "setFunctionItemList", "(Ljava/util/ArrayList;)V", "functionSelectAdapter", "Lcom/pudutech/freeinstall_ui/adapter/FunctionSelectAdapter;", "getFunctionSelectAdapter", "()Lcom/pudutech/freeinstall_ui/adapter/FunctionSelectAdapter;", "setFunctionSelectAdapter", "(Lcom/pudutech/freeinstall_ui/adapter/FunctionSelectAdapter;)V", "hasAdd", "", "getHasAdd", "()Z", "setHasAdd", "(Z)V", "hasExpand", "getHasExpand", "setHasExpand", "isClickFinish", "setClickFinish", "itemList", "getItemList", "setItemList", "locateSuccess", "getLocateSuccess", "setLocateSuccess", "pageType", "getPageType", "setPageType", "tipsDialog", "Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "getTipsDialog", "()Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "setTipsDialog", "(Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;)V", "checkFunctionSelect", "checkNeedExpand", "checkPointSet", "checkPosition", "", "createObserver", "currentActivityIsDark", "initIntent", "initListener", "initLocate", "initRecycleView", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "onDestroy", "onNewIntent", "intent", "Landroid/content/Intent;", "setBack", "setBtnEnable", "setComplete", "setExpandMap", "setFinish", "setNext", "setOut", "setView", "showDialog", "showMapInterruptDialog", "showTipsDialog", "startJump", "stopLocate", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelectMapSettingActivity extends BaseActivity<EditMapViewModel> {
    public static final String FROM_ARGUMENT = "from";
    public static final int FROM_COMPLETE_EDIT = 6;
    public static final int FROM_DOWNLOAD = 3;
    public static final int FROM_ELEMENT = 4;
    public static final int FROM_EXPAND_MAP = 5;
    public static final int FROM_NOMAL = 1;
    public static final int FROM_RESTORE = 2;
    public static final String LOCATE_LISTENER = "locate_listener_SelectMapSettingActivity";
    public static final int PAGE_BUSINESS = 2;
    public static final int PAGE_FUNCTION = 1;
    private static final String TAG = "SelectMapSettingActivity";
    private HashMap _$_findViewCache;
    private BusinessSetAdapter businessSetAdapter;
    private BusinessSetItem clickItem;
    private FunctionSelectAdapter functionSelectAdapter;
    private boolean hasAdd;
    private boolean hasExpand;
    private boolean isClickFinish;
    private boolean locateSuccess;
    private CommonDialog tipsDialog;
    private ArrayList<BusinessSetItem> itemList = new ArrayList<>();
    private ArrayList<FunctionSelectItem> functionItemList = new ArrayList<>();
    private int from = 1;
    private int pageType = 1;

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

    public final FunctionSelectAdapter getFunctionSelectAdapter() {
        return this.functionSelectAdapter;
    }

    public final void setFunctionSelectAdapter(FunctionSelectAdapter functionSelectAdapter) {
        this.functionSelectAdapter = functionSelectAdapter;
    }

    public final BusinessSetAdapter getBusinessSetAdapter() {
        return this.businessSetAdapter;
    }

    public final void setBusinessSetAdapter(BusinessSetAdapter businessSetAdapter) {
        this.businessSetAdapter = businessSetAdapter;
    }

    public final ArrayList<BusinessSetItem> getItemList() {
        return this.itemList;
    }

    public final void setItemList(ArrayList<BusinessSetItem> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.itemList = arrayList;
    }

    public final ArrayList<FunctionSelectItem> getFunctionItemList() {
        return this.functionItemList;
    }

    public final void setFunctionItemList(ArrayList<FunctionSelectItem> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.functionItemList = arrayList;
    }

    public final BusinessSetItem getClickItem() {
        return this.clickItem;
    }

    public final void setClickItem(BusinessSetItem businessSetItem) {
        this.clickItem = businessSetItem;
    }

    public final CommonDialog getTipsDialog() {
        return this.tipsDialog;
    }

    public final void setTipsDialog(CommonDialog commonDialog) {
        this.tipsDialog = commonDialog;
    }

    public final int getFrom() {
        return this.from;
    }

    public final void setFrom(int i) {
        this.from = i;
    }

    public final boolean getHasAdd() {
        return this.hasAdd;
    }

    public final void setHasAdd(boolean z) {
        this.hasAdd = z;
    }

    public final boolean getLocateSuccess() {
        return this.locateSuccess;
    }

    public final void setLocateSuccess(boolean z) {
        this.locateSuccess = z;
    }

    public final boolean getHasExpand() {
        return this.hasExpand;
    }

    public final void setHasExpand(boolean z) {
        this.hasExpand = z;
    }

    /* renamed from: isClickFinish, reason: from getter */
    public final boolean getIsClickFinish() {
        return this.isClickFinish;
    }

    public final void setClickFinish(boolean z) {
        this.isClickFinish = z;
    }

    public final int getPageType() {
        return this.pageType;
    }

    public final void setPageType(int i) {
        this.pageType = i;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5362R.layout.activity_select_map_setting;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        initRecycleView();
        initListener();
        initIntent();
        setView();
        ((EditMapViewModel) getMViewModel()).getFunctionItemData();
    }

    private final void setView() {
        ((NodeProgressBar) _$_findCachedViewById(C5362R.id.npb_step)).setNodeList(Utils.INSTANCE.getNodeData(2));
    }

    private final void initIntent() {
        this.from = getIntent().getIntExtra("from", 1);
        Pdlog.m3273d(TAG, "from" + this.from);
        int i = this.from;
        if (i == 1) {
            ToastUtils toastUtils = ToastUtils.INSTANCE;
            String string = getString(C5362R.string.complete_map_start_set);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.complete_map_start_set)");
            toastUtils.showShortToast(string);
            return;
        }
        if (i == 2) {
            AbnormalManager.INSTANCE.addHardWareListener();
            showMapInterruptDialog();
        } else {
            if (i != 3) {
                return;
            }
            AbnormalManager.INSTANCE.addHardWareListener();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ((EditMapViewModel) getMViewModel()).getBusinessItemData(this.functionItemList, this.from);
        Integer valueOf = intent != null ? Integer.valueOf(intent.getIntExtra("from", 1)) : null;
        Pdlog.m3273d(TAG, "onNewIntent " + valueOf + " -- functionItemList" + this.functionItemList.size());
        if ((valueOf != null && valueOf.intValue() == 5) || (valueOf != null && valueOf.intValue() == 6)) {
            this.hasAdd = false;
            EditMapViewModel editMapViewModel = (EditMapViewModel) getMViewModel();
            String mapName = SpDataUtils.INSTANCE.getMapName();
            if (mapName == null) {
                mapName = "";
            }
            editMapViewModel.initLocalization(mapName);
            return;
        }
        if (valueOf != null && valueOf.intValue() == 1) {
            this.hasExpand = true;
            this.locateSuccess = false;
            this.hasAdd = false;
            EditMapViewModel editMapViewModel2 = (EditMapViewModel) getMViewModel();
            String mapName2 = SpDataUtils.INSTANCE.getMapName();
            if (mapName2 == null) {
                mapName2 = "";
            }
            editMapViewModel2.initLocalization(mapName2);
        }
    }

    private final void initListener() {
        final TextView textView = (TextView) _$_findCachedViewById(C5362R.id.tv_back);
        final long j = 800;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$initListener$$inlined$singleClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView) > j || (textView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView, currentTimeMillis);
                    this.setBack();
                }
            }
        });
        final TextView textView2 = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$initListener$$inlined$singleClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView2) > j || (textView2 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView2, currentTimeMillis);
                    this.setNext();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void setNext() {
        Pdlog.m3273d(TAG, "tv_next_step--pageType" + this.pageType);
        if (this.pageType == 1) {
            if (checkFunctionSelect() == null) {
                ToastUtils toastUtils = ToastUtils.INSTANCE;
                String string = getString(C5362R.string.select_function_title);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.select_function_title)");
                toastUtils.showShortToast(string);
                return;
            }
            this.pageType = 2;
            RecyclerView rv_function_list = (RecyclerView) _$_findCachedViewById(C5362R.id.rv_function_list);
            Intrinsics.checkExpressionValueIsNotNull(rv_function_list, "rv_function_list");
            rv_function_list.setVisibility(8);
            RecyclerView rv_setting_list = (RecyclerView) _$_findCachedViewById(C5362R.id.rv_setting_list);
            Intrinsics.checkExpressionValueIsNotNull(rv_setting_list, "rv_setting_list");
            rv_setting_list.setVisibility(0);
            TextView tv_next_step = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
            Intrinsics.checkExpressionValueIsNotNull(tv_next_step, "tv_next_step");
            tv_next_step.setText(getString(C5362R.string.complete));
            ((EditMapViewModel) getMViewModel()).getBusinessItemData(this.functionItemList, this.from);
            return;
        }
        setComplete();
    }

    private final void setComplete() {
        if (checkPointSet() != null) {
            ToastUtils toastUtils = ToastUtils.INSTANCE;
            String string = getString(C5362R.string.please_complete_map_set);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.please_complete_map_set)");
            toastUtils.showShortToast(string);
            return;
        }
        if (checkNeedExpand()) {
            return;
        }
        this.isClickFinish = true;
        checkPosition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBack() {
        Pdlog.m3273d(TAG, "setBack--pageType" + this.pageType);
        if (this.pageType == 1) {
            setOut();
            return;
        }
        this.pageType = 1;
        RecyclerView rv_function_list = (RecyclerView) _$_findCachedViewById(C5362R.id.rv_function_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_function_list, "rv_function_list");
        rv_function_list.setVisibility(0);
        RecyclerView rv_setting_list = (RecyclerView) _$_findCachedViewById(C5362R.id.rv_setting_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_setting_list, "rv_setting_list");
        rv_setting_list.setVisibility(8);
        TextView tv_next_step = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
        Intrinsics.checkExpressionValueIsNotNull(tv_next_step, "tv_next_step");
        tv_next_step.setText(getString(C5362R.string.next_step));
        setBtnEnable();
    }

    private final void setOut() {
        Pdlog.m3273d(TAG, "setOut--from" + this.from);
        if (this.from == 3) {
            showDialog();
        } else {
            stopLocate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void setFinish() {
        ((EditMapViewModel) getMViewModel()).addPoint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startJump() {
        int i = this.from;
        if (i == 2 || i == 1) {
            startActivity(new Intent(this, (Class<?>) MapSuccessActivity.class));
        }
        finish();
    }

    private final void showDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder(this);
        String string = getString(C5362R.string.tips);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
        CommonDialog.Builder title = builder.setTitle(string);
        String string2 = getString(C5362R.string.if_gave_up_change);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.if_gave_up_change)");
        CommonDialog.Builder minContent = title.setMinContent(string2);
        String string3 = getString(C5362R.string.yes);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.yes)");
        CommonDialog.Builder btRight = minContent.setBtRight(string3);
        String string4 = getString(C5362R.string.f6652no);
        Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.no)");
        final CommonDialog create = btRight.setBtLeft(string4).setClose(false).create();
        String string5 = getString(C5362R.string.f6652no);
        Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.no)");
        create.setBtLeft(string5, CommonDialog.BtBg.RED, getColor(C5362R.color.white));
        create.setBtLeftClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$showDialog$1$1
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
        create.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$showDialog$$inlined$let$lambda$1
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
                CommonDialog.this.dismiss();
                SpDataUtils.INSTANCE.clear();
                AbnormalManager.INSTANCE.removeHardWareListener();
                AbnormalManager.INSTANCE.removeLocateStatusListener();
                this.stopLocate();
            }
        });
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void stopLocate() {
        BaseActivity.showLoadingDialog$default(this, null, false, 3, null);
        ((EditMapViewModel) getMViewModel()).reinitAlgoModules();
    }

    private final void initRecycleView() {
        final FunctionSelectAdapter functionSelectAdapter = new FunctionSelectAdapter();
        functionSelectAdapter.setNewData(this.functionItemList);
        functionSelectAdapter.setOnItemClickListener(new Function2<FunctionSelectItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$initRecycleView$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(FunctionSelectItem functionSelectItem, Integer num) {
                invoke(functionSelectItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(FunctionSelectItem item, int i) {
                Intrinsics.checkParameterIsNotNull(item, "item");
                if (item.getHasSetting()) {
                    return;
                }
                for (FunctionSelectItem functionSelectItem : this.getFunctionItemList()) {
                    if (Intrinsics.areEqual(functionSelectItem.getName(), item.getName())) {
                        functionSelectItem.setSelect(!functionSelectItem.isSelect());
                    }
                }
                FunctionSelectAdapter.this.notifyDataSetChanged();
                this.setBtnEnable();
            }
        });
        this.functionSelectAdapter = functionSelectAdapter;
        RecyclerView rv_function_list = (RecyclerView) _$_findCachedViewById(C5362R.id.rv_function_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_function_list, "rv_function_list");
        SelectMapSettingActivity selectMapSettingActivity = this;
        rv_function_list.setLayoutManager(new LinearLayoutManager(selectMapSettingActivity));
        Integer num = null;
        int i = 4;
        DefaultConstructorMarker defaultConstructorMarker = null;
        ((RecyclerView) _$_findCachedViewById(C5362R.id.rv_function_list)).addItemDecoration(new GridDividerItemDecoration(Integer.valueOf(DensityUtil.INSTANCE.dip2px(selectMapSettingActivity, 0.0f)), Integer.valueOf(DensityUtil.INSTANCE.dip2px(selectMapSettingActivity, 24.0f)), num, i, defaultConstructorMarker));
        RecyclerView rv_function_list2 = (RecyclerView) _$_findCachedViewById(C5362R.id.rv_function_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_function_list2, "rv_function_list");
        rv_function_list2.setAdapter(this.functionSelectAdapter);
        final BusinessSetAdapter businessSetAdapter = new BusinessSetAdapter();
        businessSetAdapter.setNewData(this.itemList);
        businessSetAdapter.setOnItemClickListener(new Function2<BusinessSetItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$initRecycleView$$inlined$apply$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(BusinessSetItem businessSetItem, Integer num2) {
                invoke(businessSetItem, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(BusinessSetItem item, int i2) {
                Intrinsics.checkParameterIsNotNull(item, "item");
                BusinessSetAdapter businessSetAdapter2 = BusinessSetAdapter.this;
                this.setClickItem(item);
                this.setClickFinish(false);
                this.checkPosition();
            }
        });
        this.businessSetAdapter = businessSetAdapter;
        RecyclerView rv_setting_list = (RecyclerView) _$_findCachedViewById(C5362R.id.rv_setting_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_setting_list, "rv_setting_list");
        rv_setting_list.setLayoutManager(new LinearLayoutManager(selectMapSettingActivity, 0, false));
        ((RecyclerView) _$_findCachedViewById(C5362R.id.rv_setting_list)).addItemDecoration(new GridDividerItemDecoration(Integer.valueOf(DensityUtil.INSTANCE.dip2px(selectMapSettingActivity, 24.0f)), Integer.valueOf(DensityUtil.INSTANCE.dip2px(selectMapSettingActivity, 0.0f)), num, i, defaultConstructorMarker));
        RecyclerView rv_setting_list2 = (RecyclerView) _$_findCachedViewById(C5362R.id.rv_setting_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_setting_list2, "rv_setting_list");
        rv_setting_list2.setAdapter(this.businessSetAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void checkPosition() {
        Pdlog.m3273d(TAG, "checkPosition " + this.locateSuccess);
        BusinessSetItem businessSetItem = this.clickItem;
        if ((businessSetItem == null || businessSetItem.getPointType() != 7) && checkNeedExpand()) {
            return;
        }
        if (this.locateSuccess) {
            BaseActivity.showLoadingDialog$default(this, null, false, 3, null);
            ((EditMapViewModel) getMViewModel()).getMapInitStatus();
        } else {
            ToastUtils toastUtils = ToastUtils.INSTANCE;
            String string = getString(C5362R.string.locate_not_finish);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.locate_not_finish)");
            toastUtils.showShortToast(string);
        }
    }

    private final boolean checkNeedExpand() {
        Pdlog.m3273d(TAG, "checkNeedExpand");
        if (this.from == 3 && !this.hasExpand) {
            LocateCase mapType = LocateMappingManager.INSTANCE.getMapType();
            Pdlog.m3273d(TAG, "checkNeedExpand " + mapType);
            if (mapType == LocateCase.Marker) {
                ToastUtils toastUtils = ToastUtils.INSTANCE;
                String string = getString(C5362R.string.please_extra_map);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.please_extra_map)");
                toastUtils.showShortToast(string);
                return true;
            }
        }
        return false;
    }

    private final void showMapInterruptDialog() {
        String string = getString(C5362R.string.map_interrupt_content);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.map_interrupt_content)");
        CommonDialogUtils.Companion.showSingleCommonDialog$default(CommonDialogUtils.INSTANCE, this, null, string, null, null, 26, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        SelectMapSettingActivity selectMapSettingActivity = this;
        ((EditMapViewModel) getMViewModel()).getFunctionData().observe(selectMapSettingActivity, new Observer<List<FunctionSelectItem>>() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<FunctionSelectItem> list) {
                SelectMapSettingActivity.this.getFunctionItemList().clear();
                SelectMapSettingActivity.this.getFunctionItemList().addAll(list);
                FunctionSelectAdapter functionSelectAdapter = SelectMapSettingActivity.this.getFunctionSelectAdapter();
                if (functionSelectAdapter != null) {
                    functionSelectAdapter.notifyDataSetChanged();
                }
                SelectMapSettingActivity.this.setBtnEnable();
            }
        });
        ((EditMapViewModel) getMViewModel()).getPointData().observe(selectMapSettingActivity, new Observer<List<BusinessSetItem>>() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$createObserver$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<BusinessSetItem> list) {
                SelectMapSettingActivity.this.getItemList().clear();
                SelectMapSettingActivity.this.getItemList().addAll(list);
                BusinessSetAdapter businessSetAdapter = SelectMapSettingActivity.this.getBusinessSetAdapter();
                if (businessSetAdapter != null) {
                    businessSetAdapter.notifyDataSetChanged();
                }
                SelectMapSettingActivity.this.setBtnEnable();
            }
        });
        ((EditMapViewModel) getMViewModel()).getMapStatusLiveData().observe(selectMapSettingActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$createObserver$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean it) {
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("mapStatusLiveData");
                sb.append(it);
                sb.append("-----pointType");
                BusinessSetItem clickItem = SelectMapSettingActivity.this.getClickItem();
                sb.append(clickItem != null ? Integer.valueOf(clickItem.getPointType()) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d("SelectMapSettingActivity", objArr);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    if (!SelectMapSettingActivity.this.getHasAdd()) {
                        AbnormalManager.INSTANCE.addLocateStatusListener();
                        SelectMapSettingActivity.this.setHasAdd(true);
                    }
                    if (SelectMapSettingActivity.this.getIsClickFinish()) {
                        SelectMapSettingActivity.this.setFinish();
                        return;
                    }
                    SelectMapSettingActivity.this.dismissLoadingDialog();
                    BusinessSetItem clickItem2 = SelectMapSettingActivity.this.getClickItem();
                    Integer valueOf = clickItem2 != null ? Integer.valueOf(clickItem2.getPointType()) : null;
                    if (valueOf != null && valueOf.intValue() == 3) {
                        SelectMapSettingActivity selectMapSettingActivity2 = SelectMapSettingActivity.this;
                        selectMapSettingActivity2.startActivity(new Intent(selectMapSettingActivity2, (Class<?>) SetBusinessNoticeActivity.class).putExtra(SetBusinessNoticeActivity.STEP_ARGUMENTS, 2));
                        return;
                    }
                    if (valueOf != null && valueOf.intValue() == 2) {
                        SelectMapSettingActivity selectMapSettingActivity3 = SelectMapSettingActivity.this;
                        selectMapSettingActivity3.startActivity(new Intent(selectMapSettingActivity3, (Class<?>) SetBusinessNoticeActivity.class).putExtra(SetBusinessNoticeActivity.STEP_ARGUMENTS, 3));
                        return;
                    }
                    if (valueOf != null && valueOf.intValue() == 4) {
                        SelectMapSettingActivity selectMapSettingActivity4 = SelectMapSettingActivity.this;
                        selectMapSettingActivity4.startActivity(new Intent(selectMapSettingActivity4, (Class<?>) SetBusinessNoticeActivity.class).putExtra(SetBusinessNoticeActivity.STEP_ARGUMENTS, 4));
                        return;
                    }
                    if (valueOf != null && valueOf.intValue() == 1) {
                        SelectMapSettingActivity selectMapSettingActivity5 = SelectMapSettingActivity.this;
                        selectMapSettingActivity5.startActivity(new Intent(selectMapSettingActivity5, (Class<?>) SetBusinessNoticeActivity.class).putExtra(SetBusinessNoticeActivity.STEP_ARGUMENTS, 5));
                        return;
                    }
                    if (valueOf != null && valueOf.intValue() == 5) {
                        SelectMapSettingActivity selectMapSettingActivity6 = SelectMapSettingActivity.this;
                        selectMapSettingActivity6.startActivity(new Intent(selectMapSettingActivity6, (Class<?>) SetBusinessNoticeActivity.class).putExtra(SetBusinessNoticeActivity.STEP_ARGUMENTS, 6));
                        return;
                    } else if (valueOf != null && valueOf.intValue() == 6) {
                        SelectMapSettingActivity selectMapSettingActivity7 = SelectMapSettingActivity.this;
                        selectMapSettingActivity7.startActivity(new Intent(selectMapSettingActivity7, (Class<?>) SetBusinessNoticeActivity.class).putExtra(SetBusinessNoticeActivity.STEP_ARGUMENTS, 7));
                        return;
                    } else {
                        if (valueOf != null && valueOf.intValue() == 7) {
                            SelectMapSettingActivity.this.setExpandMap();
                            return;
                        }
                        return;
                    }
                }
                SelectMapSettingActivity.this.dismissLoadingDialog();
                SelectMapSettingActivity.this.showTipsDialog();
            }
        });
        ((EditMapViewModel) getMViewModel()).getAddPathLiveData().observe(selectMapSettingActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$createObserver$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                SelectMapSettingActivity.this.dismissLoadingDialog();
                AnkoInternals.internalStartActivity(SelectMapSettingActivity.this, SetVirtualGuideActivity.class, new Pair[0]);
            }
        });
        ((EditMapViewModel) getMViewModel()).getTimeOutLiveData().observe(selectMapSettingActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$createObserver$5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                SelectMapSettingActivity.this.dismissLoadingDialog();
                SelectMapSettingActivity.this.startJump();
            }
        });
        MirSdkManager.INSTANCE.addLocateListener(LOCATE_LISTENER, new SelectMapSettingActivity$createObserver$6(this));
        if (this.from == 2) {
            ((EditMapViewModel) getMViewModel()).getMapData();
        }
        initLocate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void initLocate() {
        if (this.from != 4) {
            EditMapViewModel editMapViewModel = (EditMapViewModel) getMViewModel();
            String mapName = SpDataUtils.INSTANCE.getMapName();
            if (mapName == null) {
                mapName = "";
            }
            editMapViewModel.initLocalization(mapName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setExpandMap() {
        if (SpDataUtils.INSTANCE.getCameraType() == CameraType.FACE_CAMERA) {
            RobotMapManager.INSTANCE.selectCameraScheme(CameraType.FACE_CAMERA);
        }
        startActivity(new Intent(this, (Class<?>) ExpandMapActivity.class).putExtra("from_edit_map_arguments", true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTipsDialog() {
        CommonDialog commonDialog = this.tipsDialog;
        if (commonDialog == null || !commonDialog.isShowing()) {
            Pdlog.m3273d(TAG, "showTipsDialog");
            if (this.tipsDialog == null) {
                CommonDialog.Builder builder = new CommonDialog.Builder(this);
                String string = getString(C5362R.string.tips);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
                CommonDialog.Builder title = builder.setTitle(string);
                String string2 = getString(C5362R.string.not_position);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.not_position)");
                CommonDialog.Builder minContent = title.setMinContent(string2);
                String string3 = getString(C5362R.string.confirm_free);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.confirm_free)");
                this.tipsDialog = minContent.setBtRight(string3).setClose(false).create();
                final CommonDialog commonDialog2 = this.tipsDialog;
                if (commonDialog2 != null) {
                    commonDialog2.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity$showTipsDialog$1$1
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
            CommonDialog commonDialog3 = this.tipsDialog;
            if (commonDialog3 != null) {
                commonDialog3.show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBtnEnable() {
        if (this.pageType == 1) {
            if (checkFunctionSelect() == null) {
                ((TextView) _$_findCachedViewById(C5362R.id.tv_next_step)).setBackgroundResource(C5362R.drawable.rectangle_btn_gray_8);
                return;
            } else {
                ((TextView) _$_findCachedViewById(C5362R.id.tv_next_step)).setBackgroundResource(C5362R.drawable.rectangle_btn_blu_8);
                return;
            }
        }
        if (checkPointSet() == null) {
            ((TextView) _$_findCachedViewById(C5362R.id.tv_next_step)).setBackgroundResource(C5362R.drawable.rectangle_btn_blu_8);
        } else {
            ((TextView) _$_findCachedViewById(C5362R.id.tv_next_step)).setBackgroundResource(C5362R.drawable.rectangle_btn_gray_8);
        }
    }

    private final BusinessSetItem checkPointSet() {
        Object obj;
        Pdlog.json("SelectMapSettingActivity--checkPointSet", StringExtKt.toJson(this.itemList));
        Iterator<T> it = this.itemList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            BusinessSetItem businessSetItem = (BusinessSetItem) obj;
            if (businessSetItem.isSelect() && !businessSetItem.isSetting()) {
                break;
            }
        }
        return (BusinessSetItem) obj;
    }

    private final FunctionSelectItem checkFunctionSelect() {
        Object obj;
        Pdlog.json("SelectMapSettingActivity--checkFunctionSelect", StringExtKt.toJson(this.functionItemList));
        Iterator<T> it = this.functionItemList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((FunctionSelectItem) obj).isSelect()) {
                break;
            }
        }
        return (FunctionSelectItem) obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(TAG, "onDestroy");
        CommonDialog commonDialog = this.tipsDialog;
        if (commonDialog != null && commonDialog.isShowing()) {
            CommonDialog commonDialog2 = this.tipsDialog;
            if (commonDialog2 != null) {
                commonDialog2.dismiss();
            }
            this.tipsDialog = (CommonDialog) null;
        }
        MirSdkManager.INSTANCE.removeLocateListener(LOCATE_LISTENER);
    }
}
