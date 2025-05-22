package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.disinfect.baselib.ext.util.StringExtKt;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.freeinstall_ui.adapter.AddCruiseAdapter;
import com.pudutech.freeinstall_ui.adapter.CruiseListItem;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.bean.CruiseDestination;
import com.pudutech.freeinstall_ui.dialog.InputFreeInstallDialog;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.view.MaxHeightRecyclerView;
import com.pudutech.freeinstall_ui.viewmodel.AddCruisePathViewModel;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.bean.Line;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.geometry.Vector3;
import com.pudutech.opengl_draw.layer.LinesLayer;
import com.pudutech.opengl_draw.layer.OccupancyGridLayer;
import com.pudutech.opengl_draw.layer.PointLayer;
import com.pudutech.opengl_draw.layer.RobotLayer;
import com.pudutech.opengl_draw.layer.ViewControlLayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: AddCruisePathActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0087\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r*\u0001\u0016\u0018\u0000 F2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001FB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001eH\u0002J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u0005H\u0016J\u0010\u0010'\u001a\u00020%2\u0006\u0010\"\u001a\u00020#H\u0002J\u0018\u0010(\u001a\u00020%2\u000e\u0010)\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*H\u0002J\u0010\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020+H\u0002J\b\u0010.\u001a\u00020%H\u0002J\b\u0010/\u001a\u00020%H\u0002J\b\u00100\u001a\u00020%H\u0002J\u0012\u00101\u001a\u00020%2\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u00020\fH\u0016J\b\u00105\u001a\u00020%H\u0002J\b\u00106\u001a\u00020%H\u0014J\u0018\u00107\u001a\u00020%2\u0006\u00108\u001a\u00020\u001c2\u0006\u00109\u001a\u00020:H\u0002J\u0018\u0010;\u001a\u00020%2\u0006\u00108\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010<\u001a\u00020%H\u0002J\b\u0010=\u001a\u00020%H\u0002J\b\u0010>\u001a\u00020%H\u0002J\u0010\u0010?\u001a\u00020%2\u0006\u0010@\u001a\u00020\u001eH\u0002J\b\u0010A\u001a\u00020%H\u0003J!\u0010B\u001a\u00020%2\b\u00108\u001a\u0004\u0018\u00010\u001c2\b\u0010C\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010DJ\b\u0010E\u001a\u00020%H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006G"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/AddCruisePathActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/AddCruisePathViewModel;", "()V", "isDataLoad", "", "isFirstReLast", "lineLayer", "Lcom/pudutech/opengl_draw/layer/LinesLayer;", "mAdapter", "Lcom/pudutech/freeinstall_ui/adapter/AddCruiseAdapter;", "mapHeight", "", "mapLayer", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;", "mapWith", "pageType", "pointLayer", "Lcom/pudutech/opengl_draw/layer/PointLayer;", "robotLayer", "Lcom/pudutech/opengl_draw/layer/RobotLayer;", "robotPoseListener", "com/pudutech/freeinstall_ui/activity/AddCruisePathActivity$robotPoseListener$1", "Lcom/pudutech/freeinstall_ui/activity/AddCruisePathActivity$robotPoseListener$1;", "singleClickListener", "Landroid/view/View$OnClickListener;", "tableList", "", "Lcom/pudutech/freeinstall_ui/adapter/CruiseListItem;", "vector3dList", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "calculateDistance", "vector3d", "checkNameRepeat", "name", "", "createObserver", "", "currentActivityIsDark", "deletePath", "drawAllPath", "cruiseDestinations", "", "Lcom/pudutech/freeinstall_ui/bean/CruiseDestination;", TypedValues.Position.S_DRAWPATH, "cruiseDestination", "initListener", "initMap", "initRecycleView", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "locate", "onDestroy", "reColor", "item", TypedValues.Custom.S_COLOR, "Lcom/pudutech/opengl_draw/base/Color;", "renamePoint", "setFirstPoint", "setLineVisble", "setRestoreData", "setUpdateRobotPosition", "p0", "setView", "showInputDialog", "pos", "(Lcom/pudutech/freeinstall_ui/adapter/CruiseListItem;Ljava/lang/Integer;)V", "updateRealLine", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AddCruisePathActivity extends BaseActivity<AddCruisePathViewModel> {
    private static final String TAG = "AddCruisePathActivity";
    public static final int TYPE_PATH_RECORDING = 2;
    public static final int TYPE_PATH_SHOW = 1;
    private HashMap _$_findViewCache;
    private boolean isDataLoad;
    private LinesLayer lineLayer;
    private AddCruiseAdapter mAdapter;
    private int mapHeight;
    private OccupancyGridLayer mapLayer;
    private int mapWith;
    private PointLayer pointLayer;
    private RobotLayer robotLayer;
    private final List<CruiseListItem> tableList = new ArrayList();
    private final List<Vector3d> vector3dList = new ArrayList();
    private int pageType = 1;
    private boolean isFirstReLast = true;
    private final View.OnClickListener singleClickListener = new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddCruisePathActivity$singleClickListener$1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            List list;
            List list2;
            int i;
            boolean z;
            List<CruiseListItem> list3;
            if (view != null) {
                int id = view.getId();
                if (id == C5362R.id.tv_next_step) {
                    z = AddCruisePathActivity.this.isDataLoad;
                    if (z) {
                        AddCruisePathViewModel addCruisePathViewModel = (AddCruisePathViewModel) AddCruisePathActivity.this.getMViewModel();
                        list3 = AddCruisePathActivity.this.tableList;
                        addCruisePathViewModel.addPath(list3);
                        return;
                    }
                    return;
                }
                if (id == C5362R.id.tv_add) {
                    i = AddCruisePathActivity.this.pageType;
                    if (i == 1) {
                        AddCruisePathActivity.this.pageType = 2;
                        AddCruisePathActivity.this.setView();
                        return;
                    } else {
                        AddCruisePathActivity.this.pageType = 1;
                        AddCruisePathActivity.this.showInputDialog(null, null);
                        return;
                    }
                }
                if (id == C5362R.id.iv_min) {
                    MaxHeightRecyclerView rv_table_list = (MaxHeightRecyclerView) AddCruisePathActivity.this._$_findCachedViewById(C5362R.id.rv_table_list);
                    Intrinsics.checkExpressionValueIsNotNull(rv_table_list, "rv_table_list");
                    if (rv_table_list.getVisibility() == 0) {
                        MaxHeightRecyclerView rv_table_list2 = (MaxHeightRecyclerView) AddCruisePathActivity.this._$_findCachedViewById(C5362R.id.rv_table_list);
                        Intrinsics.checkExpressionValueIsNotNull(rv_table_list2, "rv_table_list");
                        rv_table_list2.setVisibility(8);
                        View view_line = AddCruisePathActivity.this._$_findCachedViewById(C5362R.id.view_line);
                        Intrinsics.checkExpressionValueIsNotNull(view_line, "view_line");
                        view_line.setVisibility(8);
                        TextView tv_min_table = (TextView) AddCruisePathActivity.this._$_findCachedViewById(C5362R.id.tv_min_table);
                        Intrinsics.checkExpressionValueIsNotNull(tv_min_table, "tv_min_table");
                        tv_min_table.setVisibility(0);
                        TextView tv_min_table2 = (TextView) AddCruisePathActivity.this._$_findCachedViewById(C5362R.id.tv_min_table);
                        Intrinsics.checkExpressionValueIsNotNull(tv_min_table2, "tv_min_table");
                        list = AddCruisePathActivity.this.tableList;
                        list2 = AddCruisePathActivity.this.tableList;
                        tv_min_table2.setText(((CruiseListItem) list.get(list2.size() - 1)).getDestination().getId());
                        return;
                    }
                    MaxHeightRecyclerView rv_table_list3 = (MaxHeightRecyclerView) AddCruisePathActivity.this._$_findCachedViewById(C5362R.id.rv_table_list);
                    Intrinsics.checkExpressionValueIsNotNull(rv_table_list3, "rv_table_list");
                    rv_table_list3.setVisibility(0);
                    View view_line2 = AddCruisePathActivity.this._$_findCachedViewById(C5362R.id.view_line);
                    Intrinsics.checkExpressionValueIsNotNull(view_line2, "view_line");
                    view_line2.setVisibility(0);
                    TextView tv_min_table3 = (TextView) AddCruisePathActivity.this._$_findCachedViewById(C5362R.id.tv_min_table);
                    Intrinsics.checkExpressionValueIsNotNull(tv_min_table3, "tv_min_table");
                    tv_min_table3.setVisibility(8);
                    return;
                }
                if (id == C5362R.id.tv_back) {
                    Utils.INSTANCE.showBackDialog(AddCruisePathActivity.this);
                } else if (id == C5362R.id.iv_locate) {
                    AddCruisePathActivity.this.locate();
                }
            }
        }
    };
    private final AddCruisePathActivity$robotPoseListener$1 robotPoseListener = new RobotMoveManager.RobotPoseListener() { // from class: com.pudutech.freeinstall_ui.activity.AddCruisePathActivity$robotPoseListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotPoseListener
        public void onPose(double x, double y, double yaw) {
            RobotLayer robotLayer;
            Pdlog.m3273d("AddCruisePathActivity", "x--" + x + "  y--" + y + "  yaw--" + yaw);
            Vector3d vector3d = new Vector3d(x, y, yaw);
            AddCruisePathActivity.this.setUpdateRobotPosition(vector3d);
            Utils.Companion companion = Utils.INSTANCE;
            robotLayer = AddCruisePathActivity.this.robotLayer;
            companion.updateRobotPosition(robotLayer, vector3d);
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
        return C5362R.layout.activity_add_cruise_path;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        initMap();
        initListener();
        initRecycleView();
    }

    private final void initRecycleView() {
        MaxHeightRecyclerView rv_table_list = (MaxHeightRecyclerView) _$_findCachedViewById(C5362R.id.rv_table_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_table_list, "rv_table_list");
        rv_table_list.setLayoutManager(new LinearLayoutManager(this));
        final AddCruiseAdapter addCruiseAdapter = new AddCruiseAdapter();
        addCruiseAdapter.setNewData(this.tableList);
        setLineVisble();
        addCruiseAdapter.setOnItemClickListener(new Function2<CruiseListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddCruisePathActivity$initRecycleView$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CruiseListItem cruiseListItem, Integer num) {
                invoke(cruiseListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CruiseListItem item, int i) {
                List<CruiseListItem> list;
                Intrinsics.checkParameterIsNotNull(item, "item");
                Pdlog.m3273d("AddCruisePathActivity", "onItemClickListener " + i + "  " + item.getDestination().getId());
                list = this.tableList;
                for (CruiseListItem cruiseListItem : list) {
                    cruiseListItem.setSelect(Intrinsics.areEqual(cruiseListItem, item));
                    cruiseListItem.setDelete(false);
                    if (Intrinsics.areEqual(cruiseListItem, item)) {
                        AddCruisePathActivity addCruisePathActivity = this;
                        Color fromHexAndAlpha = Color.fromHexAndAlpha("FF962D", 1.0f);
                        Intrinsics.checkExpressionValueIsNotNull(fromHexAndAlpha, "Color.fromHexAndAlpha(\"FF962D\", 1f)");
                        addCruisePathActivity.reColor(cruiseListItem, fromHexAndAlpha);
                    } else {
                        AddCruisePathActivity addCruisePathActivity2 = this;
                        Color fromHexAndAlpha2 = Color.fromHexAndAlpha("FFFFFF", 1.0f);
                        Intrinsics.checkExpressionValueIsNotNull(fromHexAndAlpha2, "Color.fromHexAndAlpha(\"FFFFFF\", 1f)");
                        addCruisePathActivity2.reColor(cruiseListItem, fromHexAndAlpha2);
                    }
                }
                AddCruiseAdapter.this.notifyDataSetChanged();
            }
        });
        addCruiseAdapter.setOnRenameClickListener(new Function2<CruiseListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddCruisePathActivity$initRecycleView$$inlined$apply$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CruiseListItem cruiseListItem, Integer num) {
                invoke(cruiseListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CruiseListItem item, int i) {
                Intrinsics.checkParameterIsNotNull(item, "item");
                AddCruisePathActivity.this.showInputDialog(item, Integer.valueOf(i));
            }
        });
        addCruiseAdapter.setOnDeleteClickListener(new Function2<CruiseListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddCruisePathActivity$initRecycleView$$inlined$apply$lambda$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CruiseListItem cruiseListItem, Integer num) {
                invoke(cruiseListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CruiseListItem item, int i) {
                List<CruiseListItem> list;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                for (CruiseListItem cruiseListItem : list) {
                    cruiseListItem.setDelete(Intrinsics.areEqual(cruiseListItem, item));
                }
                AddCruiseAdapter.this.notifyDataSetChanged();
            }
        });
        addCruiseAdapter.setOnDeleteSureClickListener(new Function2<CruiseListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddCruisePathActivity$initRecycleView$$inlined$apply$lambda$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CruiseListItem cruiseListItem, Integer num) {
                invoke(cruiseListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CruiseListItem item, int i) {
                List list;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                list.remove(i);
                AddCruiseAdapter.this.notifyDataSetChanged();
                this.deletePath(item.getDestination().getId());
                this.setLineVisble();
            }
        });
        this.mAdapter = addCruiseAdapter;
        MaxHeightRecyclerView rv_table_list2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5362R.id.rv_table_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_table_list2, "rv_table_list");
        rv_table_list2.setAdapter(this.mAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLineVisble() {
        if (this.tableList.size() > 0) {
            RelativeLayout rl_table_list = (RelativeLayout) _$_findCachedViewById(C5362R.id.rl_table_list);
            Intrinsics.checkExpressionValueIsNotNull(rl_table_list, "rl_table_list");
            rl_table_list.setVisibility(0);
            View view_line = _$_findCachedViewById(C5362R.id.view_line);
            Intrinsics.checkExpressionValueIsNotNull(view_line, "view_line");
            view_line.setVisibility(0);
            return;
        }
        View view_line2 = _$_findCachedViewById(C5362R.id.view_line);
        Intrinsics.checkExpressionValueIsNotNull(view_line2, "view_line");
        view_line2.setVisibility(8);
        RelativeLayout rl_table_list2 = (RelativeLayout) _$_findCachedViewById(C5362R.id.rl_table_list);
        Intrinsics.checkExpressionValueIsNotNull(rl_table_list2, "rl_table_list");
        rl_table_list2.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showInputDialog(final CruiseListItem item, final Integer pos) {
        final InputFreeInstallDialog inputFreeInstallDialog = new InputFreeInstallDialog(this);
        String string = getString(C5362R.string.please_input_name);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.please_input_name)");
        inputFreeInstallDialog.setTitle(string);
        String string2 = getString(C5362R.string.sure);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.sure)");
        inputFreeInstallDialog.setBtnText(string2);
        inputFreeInstallDialog.setOnInputResult(new Function1<String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddCruisePathActivity$showInputDialog$1
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

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                boolean checkNameRepeat;
                List list;
                AddCruiseAdapter addCruiseAdapter;
                List list2;
                List list3;
                List list4;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (!(it.length() == 0)) {
                    checkNameRepeat = AddCruisePathActivity.this.checkNameRepeat(it);
                    if (checkNameRepeat) {
                        ToastUtils toastUtils = ToastUtils.INSTANCE;
                        String string3 = AddCruisePathActivity.this.getString(C5362R.string.name_repeat);
                        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.name_repeat)");
                        toastUtils.showShortToast(string3);
                        return;
                    }
                    inputFreeInstallDialog.dismiss();
                    if (pos == null) {
                        ArrayList arrayList = new ArrayList();
                        list2 = AddCruisePathActivity.this.vector3dList;
                        arrayList.addAll(list2);
                        CruiseDestination cruiseDestination = new CruiseDestination(it, arrayList);
                        list3 = AddCruisePathActivity.this.tableList;
                        list3.add(new CruiseListItem(cruiseDestination, false, false, 4, null));
                        AddCruisePathActivity.this.setView();
                        AddCruisePathActivity.this.drawPath(cruiseDestination);
                        list4 = AddCruisePathActivity.this.vector3dList;
                        list4.clear();
                    } else {
                        AddCruisePathActivity addCruisePathActivity = AddCruisePathActivity.this;
                        CruiseListItem cruiseListItem = item;
                        if (cruiseListItem == null) {
                            Intrinsics.throwNpe();
                        }
                        addCruisePathActivity.renamePoint(cruiseListItem, it);
                        list = AddCruisePathActivity.this.tableList;
                        ((CruiseListItem) list.get(pos.intValue())).getDestination().setId(it);
                    }
                    addCruiseAdapter = AddCruisePathActivity.this.mAdapter;
                    if (addCruiseAdapter != null) {
                        addCruiseAdapter.notifyDataSetChanged();
                    }
                    AddCruisePathActivity.this.setLineVisble();
                    return;
                }
                ToastUtils toastUtils2 = ToastUtils.INSTANCE;
                String string4 = AddCruisePathActivity.this.getString(C5362R.string.please_input_name);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.please_input_name)");
                toastUtils2.showShortToast(string4);
            }
        });
        inputFreeInstallDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void drawPath(CruiseDestination cruiseDestination) {
        LinesLayer linesLayer;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = cruiseDestination.getVector3s().iterator();
        while (it.hasNext()) {
            Vector3 vector3dToVector3 = Utils.INSTANCE.vector3dToVector3((Vector3d) it.next());
            if (vector3dToVector3 == null) {
                Intrinsics.throwNpe();
            }
            arrayList.add(vector3dToVector3);
        }
        if (arrayList.isEmpty() || (linesLayer = this.lineLayer) == null) {
            return;
        }
        linesLayer.add(new Line(arrayList, cruiseDestination.getId()));
    }

    private final void drawAllPath(List<CruiseDestination> cruiseDestinations) {
        if (cruiseDestinations == null || cruiseDestinations.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (CruiseDestination cruiseDestination : cruiseDestinations) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it = cruiseDestination.getVector3s().iterator();
            while (it.hasNext()) {
                Vector3 vector3dToVector3 = Utils.INSTANCE.vector3dToVector3((Vector3d) it.next());
                if (vector3dToVector3 == null) {
                    Intrinsics.throwNpe();
                }
                arrayList2.add(vector3dToVector3);
            }
            if (arrayList2.size() != 0) {
                arrayList.add(new Line(arrayList2, cruiseDestination.getId()));
            }
        }
        LinesLayer linesLayer = this.lineLayer;
        if (linesLayer != null) {
            linesLayer.addAlll(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deletePath(String name) {
        LinesLayer linesLayer = this.lineLayer;
        if (linesLayer != null) {
            linesLayer.remove(name);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkNameRepeat(String name) {
        Object obj;
        Iterator<T> it = this.tableList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(name, ((CruiseListItem) obj).getDestination().getId())) {
                break;
            }
        }
        return ((CruiseListItem) obj) != null;
    }

    private final void initListener() {
        ExtandsKt.singleClick$default((TextView) _$_findCachedViewById(C5362R.id.tv_next_step), this.singleClickListener, 0L, 2, (Object) null);
        ExtandsKt.singleClick$default((TextView) _$_findCachedViewById(C5362R.id.tv_add), this.singleClickListener, 0L, 2, (Object) null);
        ExtandsKt.singleClick$default((ImageView) _$_findCachedViewById(C5362R.id.iv_min), this.singleClickListener, 0L, 2, (Object) null);
        ExtandsKt.singleClick$default((TextView) _$_findCachedViewById(C5362R.id.tv_back), this.singleClickListener, 0L, 2, (Object) null);
        ExtandsKt.singleClick$default((ImageView) _$_findCachedViewById(C5362R.id.iv_locate), this.singleClickListener, 0L, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void locate() {
        VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
        Intrinsics.checkExpressionValueIsNotNull(visualizationView, "visualizationView");
        visualizationView.getCamera().fullPreview(Utils.INSTANCE.getScreenScale(this.mapWith, this.mapHeight));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUpdateRobotPosition(Vector3d p0) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddCruisePathActivity$setUpdateRobotPosition$1(this, p0, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRealLine() {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.vector3dList.iterator();
        while (it.hasNext()) {
            Vector3 vector3dToVector3 = Utils.INSTANCE.vector3dToVector3((Vector3d) it.next());
            if (vector3dToVector3 == null) {
                Intrinsics.throwNpe();
            }
            arrayList.add(vector3dToVector3);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        if (this.isFirstReLast) {
            this.isFirstReLast = false;
            LinesLayer linesLayer = this.lineLayer;
            if (linesLayer != null) {
                linesLayer.add(new Line(arrayList, ""));
                return;
            }
            return;
        }
        LinesLayer linesLayer2 = this.lineLayer;
        if (linesLayer2 != null) {
            linesLayer2.reLast(new Line(arrayList, ""));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean calculateDistance(Vector3d vector3d) {
        if (this.vector3dList.size() == 0) {
            return true;
        }
        List<Vector3d> list = this.vector3dList;
        return Utils.INSTANCE.calculateDistance(vector3d, list.get(list.size() - 1), 10L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reColor(CruiseListItem item, Color color) {
        LinesLayer linesLayer = this.lineLayer;
        if (linesLayer != null) {
            linesLayer.reColor(item.getDestination().getId(), color);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renamePoint(CruiseListItem item, String name) {
        LinesLayer linesLayer = this.lineLayer;
        if (linesLayer != null) {
            linesLayer.reName(item.getDestination().getId(), name);
        }
    }

    private final void initMap() {
        ViewControlLayer viewControlLayer = new ViewControlLayer(this, (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView));
        this.mapLayer = new OccupancyGridLayer();
        this.robotLayer = new RobotLayer();
        this.lineLayer = new LinesLayer();
        this.pointLayer = new PointLayer();
        VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
        if (visualizationView != null) {
            visualizationView.onCreate(new CopyOnWriteArrayList<>(), CommonExtKt.getScreenWidth(this) * 2, CommonExtKt.getScreenHeight(this) * 2);
            visualizationView.addLayer(this.mapLayer);
            visualizationView.addLayer(this.lineLayer);
            visualizationView.addLayer(this.pointLayer);
            visualizationView.addLayer(this.robotLayer);
            visualizationView.addLayer(viewControlLayer);
            visualizationView.onStart();
        }
    }

    private final void setFirstPoint() {
        Pdlog.m3273d(TAG, "setFirstPoint");
        Destination mapStartPoint = SpDataUtils.INSTANCE.getMapStartPoint();
        if (mapStartPoint != null) {
            Pdlog.m3273d(TAG, "setFirstPoint --- mapStartPoint " + mapStartPoint.getName());
            PointLayer pointLayer = this.pointLayer;
            if (pointLayer != null) {
                Transform destinationToTransform = Utils.INSTANCE.destinationToTransform(mapStartPoint);
                Drawable drawable = getDrawable(C5362R.drawable.icon_start_point);
                pointLayer.update(destinationToTransform, drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null, mapStartPoint.getName());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        AddCruisePathActivity addCruisePathActivity = this;
        ((AddCruisePathViewModel) getMViewModel()).getAddPathLiveData().observe(addCruisePathActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.AddCruisePathActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                AddCruisePathActivity addCruisePathActivity2 = AddCruisePathActivity.this;
                addCruisePathActivity2.startActivity(new Intent(addCruisePathActivity2, (Class<?>) SelectMapSettingActivity.class).putExtra("from", 4));
            }
        });
        ((AddCruisePathViewModel) getMViewModel()).getStaticMapLiveData().observe(addCruisePathActivity, new AddCruisePathActivity$createObserver$2(this));
        ((AddCruisePathViewModel) getMViewModel()).getStaticMap();
        RobotMoveManager.INSTANCE.addRobotPoseListener(this.robotPoseListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRestoreData() {
        setFirstPoint();
        List<CruiseListItem> addCruisePath = SpDataUtils.INSTANCE.getAddCruisePath();
        this.isDataLoad = true;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("setRestoreData ");
        sb.append(addCruisePath != null ? Integer.valueOf(addCruisePath.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
        if (addCruisePath == null || addCruisePath.isEmpty()) {
            return;
        }
        this.tableList.addAll(addCruisePath);
        AddCruiseAdapter addCruiseAdapter = this.mAdapter;
        if (addCruiseAdapter != null) {
            addCruiseAdapter.notifyDataSetChanged();
        }
        setLineVisble();
        ArrayList arrayList = new ArrayList();
        for (CruiseListItem cruiseListItem : addCruisePath) {
            Pdlog.m3273d(TAG, "setRestoreData " + cruiseListItem.getDestination().getId() + "---" + cruiseListItem.getDestination().getVector3s().size());
            Pdlog.json(TAG, StringExtKt.toJson(cruiseListItem.getDestination().getVector3s()));
            arrayList.add(cruiseListItem.getDestination());
        }
        drawAllPath(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setView() {
        int i = this.pageType;
        if (i == 1) {
            TextView tv_title = (TextView) _$_findCachedViewById(C5362R.id.tv_title);
            Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
            tv_title.setText(getString(C5362R.string.push_to_cruise));
            RelativeLayout rl_table_list = (RelativeLayout) _$_findCachedViewById(C5362R.id.rl_table_list);
            Intrinsics.checkExpressionValueIsNotNull(rl_table_list, "rl_table_list");
            rl_table_list.setVisibility(0);
            TextView tv_back = (TextView) _$_findCachedViewById(C5362R.id.tv_back);
            Intrinsics.checkExpressionValueIsNotNull(tv_back, "tv_back");
            tv_back.setVisibility(0);
            TextView tv_next_step = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
            Intrinsics.checkExpressionValueIsNotNull(tv_next_step, "tv_next_step");
            tv_next_step.setVisibility(0);
            TextView tv_add = (TextView) _$_findCachedViewById(C5362R.id.tv_add);
            Intrinsics.checkExpressionValueIsNotNull(tv_add, "tv_add");
            tv_add.setText(getString(C5362R.string.start_recording));
            ((TextView) _$_findCachedViewById(C5362R.id.tv_add)).setCompoundDrawables(null, null, null, null);
            RelativeLayout rl_add = (RelativeLayout) _$_findCachedViewById(C5362R.id.rl_add);
            Intrinsics.checkExpressionValueIsNotNull(rl_add, "rl_add");
            Sdk27PropertiesKt.setBackgroundResource(rl_add, C5362R.drawable.rectangle_btn_1cc33d_8);
            return;
        }
        if (i != 2) {
            return;
        }
        TextView tv_title2 = (TextView) _$_findCachedViewById(C5362R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_title2, "tv_title");
        tv_title2.setText(getString(C5362R.string.not_out_map));
        RelativeLayout rl_table_list2 = (RelativeLayout) _$_findCachedViewById(C5362R.id.rl_table_list);
        Intrinsics.checkExpressionValueIsNotNull(rl_table_list2, "rl_table_list");
        rl_table_list2.setVisibility(8);
        TextView tv_back2 = (TextView) _$_findCachedViewById(C5362R.id.tv_back);
        Intrinsics.checkExpressionValueIsNotNull(tv_back2, "tv_back");
        tv_back2.setVisibility(8);
        TextView tv_next_step2 = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
        Intrinsics.checkExpressionValueIsNotNull(tv_next_step2, "tv_next_step");
        tv_next_step2.setVisibility(8);
        TextView tv_add2 = (TextView) _$_findCachedViewById(C5362R.id.tv_add);
        Intrinsics.checkExpressionValueIsNotNull(tv_add2, "tv_add");
        tv_add2.setText(getString(C5362R.string.stop_recording));
        Drawable drawable = getDrawable(C5362R.drawable.icon_stop_record);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        ((TextView) _$_findCachedViewById(C5362R.id.tv_add)).setCompoundDrawables(drawable, null, null, null);
        RelativeLayout rl_add2 = (RelativeLayout) _$_findCachedViewById(C5362R.id.rl_add);
        Intrinsics.checkExpressionValueIsNotNull(rl_add2, "rl_add");
        Sdk27PropertiesKt.setBackgroundResource(rl_add2, C5362R.drawable.rectangle_btn_red_8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        RobotMoveManager.INSTANCE.removeRobotPoseListener(this.robotPoseListener);
        this.mapLayer = (OccupancyGridLayer) null;
        this.robotLayer = (RobotLayer) null;
        this.lineLayer = (LinesLayer) null;
    }
}
