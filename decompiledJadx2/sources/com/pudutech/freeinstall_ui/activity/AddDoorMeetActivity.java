package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.freeinstall_ui.adapter.AddTableAdapter;
import com.pudutech.freeinstall_ui.adapter.TableListItem;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.dialog.InputFreeInstallDialog;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.view.MaxHeightRecyclerView;
import com.pudutech.freeinstall_ui.viewmodel.AddDoorMeetViewModel;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.layer.OccupancyGridLayer;
import com.pudutech.opengl_draw.layer.PointLayer;
import com.pudutech.opengl_draw.layer.PointsLayer;
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
import org.mozilla.javascript.ES6Iterator;

/* compiled from: AddDoorMeetActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000w\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f*\u0001\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0007H\u0016J\u0010\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020 H\u0002J\b\u0010&\u001a\u00020 H\u0002J\b\u0010'\u001a\u00020 H\u0003J\u0012\u0010(\u001a\u00020 2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020\u000bH\u0016J\b\u0010,\u001a\u00020 H\u0002J\b\u0010-\u001a\u00020 H\u0002J\b\u0010.\u001a\u00020 H\u0014J\b\u0010/\u001a\u00020 H\u0002J\b\u00100\u001a\u00020 H\u0002J\b\u00101\u001a\u00020 H\u0003J!\u00102\u001a\u00020 2\b\u00103\u001a\u0004\u0018\u00010\u001c2\b\u00104\u001a\u0004\u0018\u00010\u000bH\u0003¢\u0006\u0002\u00105R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/AddDoorMeetActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/AddDoorMeetViewModel;", "()V", "TAG", "", "isDataLoad", "", "mAdapter", "Lcom/pudutech/freeinstall_ui/adapter/AddTableAdapter;", "mapHeight", "", "mapLayer", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;", "mapWith", "pointLayer", "Lcom/pudutech/opengl_draw/layer/PointLayer;", "pointsLayer", "Lcom/pudutech/opengl_draw/layer/PointsLayer;", "robotLayer", "Lcom/pudutech/opengl_draw/layer/RobotLayer;", "robotPoseListener", "com/pudutech/freeinstall_ui/activity/AddDoorMeetActivity$robotPoseListener$1", "Lcom/pudutech/freeinstall_ui/activity/AddDoorMeetActivity$robotPoseListener$1;", "singleClickListener", "Landroid/view/View$OnClickListener;", "tableList", "", "Lcom/pudutech/freeinstall_ui/adapter/TableListItem;", "vector3d", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "createObserver", "", "currentActivityIsDark", "deletePoint", "destination", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "initListener", "initMap", "initRecycleView", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "locate", ES6Iterator.NEXT_METHOD, "onDestroy", "setFirstPoint", "setLineVisble", "setRestoreData", "showInputDialog", "item", "pos", "(Lcom/pudutech/freeinstall_ui/adapter/TableListItem;Ljava/lang/Integer;)V", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AddDoorMeetActivity extends BaseActivity<AddDoorMeetViewModel> {
    private HashMap _$_findViewCache;
    private boolean isDataLoad;
    private AddTableAdapter mAdapter;
    private int mapHeight;
    private OccupancyGridLayer mapLayer;
    private int mapWith;
    private PointLayer pointLayer;
    private PointsLayer pointsLayer;
    private RobotLayer robotLayer;
    private Vector3d vector3d;
    private final String TAG = "AddDoorMeetActivity";
    private final List<TableListItem> tableList = new ArrayList();
    private final View.OnClickListener singleClickListener = new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddDoorMeetActivity$singleClickListener$1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            List list;
            List list2;
            Vector3d vector3d;
            boolean z;
            if (view != null) {
                int id = view.getId();
                if (id == C5362R.id.tv_next_step) {
                    z = AddDoorMeetActivity.this.isDataLoad;
                    if (z) {
                        AddDoorMeetActivity.this.next();
                        return;
                    }
                    return;
                }
                if (id == C5362R.id.tv_add) {
                    vector3d = AddDoorMeetActivity.this.vector3d;
                    if (vector3d != null) {
                        AddDoorMeetActivity.this.showInputDialog(null, null);
                        return;
                    }
                    ToastUtils toastUtils = ToastUtils.INSTANCE;
                    String string = AddDoorMeetActivity.this.getString(C5362R.string.get_no_position);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.get_no_position)");
                    toastUtils.showShortToast(string);
                    return;
                }
                if (id == C5362R.id.iv_min) {
                    MaxHeightRecyclerView rv_table_list = (MaxHeightRecyclerView) AddDoorMeetActivity.this._$_findCachedViewById(C5362R.id.rv_table_list);
                    Intrinsics.checkExpressionValueIsNotNull(rv_table_list, "rv_table_list");
                    if (rv_table_list.getVisibility() == 0) {
                        MaxHeightRecyclerView rv_table_list2 = (MaxHeightRecyclerView) AddDoorMeetActivity.this._$_findCachedViewById(C5362R.id.rv_table_list);
                        Intrinsics.checkExpressionValueIsNotNull(rv_table_list2, "rv_table_list");
                        rv_table_list2.setVisibility(8);
                        View view_line = AddDoorMeetActivity.this._$_findCachedViewById(C5362R.id.view_line);
                        Intrinsics.checkExpressionValueIsNotNull(view_line, "view_line");
                        view_line.setVisibility(8);
                        TextView tv_min_table = (TextView) AddDoorMeetActivity.this._$_findCachedViewById(C5362R.id.tv_min_table);
                        Intrinsics.checkExpressionValueIsNotNull(tv_min_table, "tv_min_table");
                        tv_min_table.setVisibility(0);
                        TextView tv_min_table2 = (TextView) AddDoorMeetActivity.this._$_findCachedViewById(C5362R.id.tv_min_table);
                        Intrinsics.checkExpressionValueIsNotNull(tv_min_table2, "tv_min_table");
                        list = AddDoorMeetActivity.this.tableList;
                        list2 = AddDoorMeetActivity.this.tableList;
                        tv_min_table2.setText(((TableListItem) list.get(list2.size() - 1)).getDestination().getName());
                        return;
                    }
                    MaxHeightRecyclerView rv_table_list3 = (MaxHeightRecyclerView) AddDoorMeetActivity.this._$_findCachedViewById(C5362R.id.rv_table_list);
                    Intrinsics.checkExpressionValueIsNotNull(rv_table_list3, "rv_table_list");
                    rv_table_list3.setVisibility(0);
                    View view_line2 = AddDoorMeetActivity.this._$_findCachedViewById(C5362R.id.view_line);
                    Intrinsics.checkExpressionValueIsNotNull(view_line2, "view_line");
                    view_line2.setVisibility(0);
                    TextView tv_min_table3 = (TextView) AddDoorMeetActivity.this._$_findCachedViewById(C5362R.id.tv_min_table);
                    Intrinsics.checkExpressionValueIsNotNull(tv_min_table3, "tv_min_table");
                    tv_min_table3.setVisibility(8);
                    return;
                }
                if (id == C5362R.id.tv_back) {
                    Utils.INSTANCE.showBackDialog(AddDoorMeetActivity.this);
                } else if (id == C5362R.id.iv_locate) {
                    AddDoorMeetActivity.this.locate();
                }
            }
        }
    };
    private final AddDoorMeetActivity$robotPoseListener$1 robotPoseListener = new RobotMoveManager.RobotPoseListener() { // from class: com.pudutech.freeinstall_ui.activity.AddDoorMeetActivity$robotPoseListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotPoseListener
        public void onPose(double x, double y, double yaw) {
            String str;
            RobotLayer robotLayer;
            Vector3d vector3d;
            str = AddDoorMeetActivity.this.TAG;
            Pdlog.m3273d(str, "x--" + x + "  y--" + y + "  yaw--" + yaw);
            AddDoorMeetActivity.this.vector3d = new Vector3d(x, y, yaw);
            Utils.Companion companion = Utils.INSTANCE;
            robotLayer = AddDoorMeetActivity.this.robotLayer;
            vector3d = AddDoorMeetActivity.this.vector3d;
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
        return C5362R.layout.activity_add_door_meet;
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
        final AddTableAdapter addTableAdapter = new AddTableAdapter();
        addTableAdapter.setNewData(this.tableList);
        setLineVisble();
        addTableAdapter.setOnItemClickListener(new Function2<TableListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoorMeetActivity$initRecycleView$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TableListItem tableListItem, Integer num) {
                invoke(tableListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(TableListItem item, int i) {
                List<TableListItem> list;
                PointsLayer pointsLayer;
                PointsLayer pointsLayer2;
                PointsLayer pointsLayer3;
                PointsLayer pointsLayer4;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                for (TableListItem tableListItem : list) {
                    tableListItem.setSelect(Intrinsics.areEqual(tableListItem, item));
                    tableListItem.setDelete(false);
                    if (Intrinsics.areEqual(tableListItem, item)) {
                        Utils.Companion companion = Utils.INSTANCE;
                        pointsLayer = this.pointsLayer;
                        String name = tableListItem.getDestination().getName();
                        Color fromHexAndAlpha = Color.fromHexAndAlpha("FF962D", 1.0f);
                        Intrinsics.checkExpressionValueIsNotNull(fromHexAndAlpha, "Color.fromHexAndAlpha(\"FF962D\", 1f)");
                        companion.reColor(pointsLayer, name, fromHexAndAlpha);
                        Utils.Companion companion2 = Utils.INSTANCE;
                        pointsLayer2 = this.pointsLayer;
                        String name2 = tableListItem.getDestination().getName();
                        Drawable drawable = this.getDrawable(C5362R.drawable.icon_door_select);
                        companion2.reBitmapPoint(pointsLayer2, name2, drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null);
                    } else {
                        Utils.Companion companion3 = Utils.INSTANCE;
                        pointsLayer3 = this.pointsLayer;
                        String name3 = tableListItem.getDestination().getName();
                        Color fromHexAndAlpha2 = Color.fromHexAndAlpha("FFFFFF", 1.0f);
                        Intrinsics.checkExpressionValueIsNotNull(fromHexAndAlpha2, "Color.fromHexAndAlpha(\"FFFFFF\", 1f)");
                        companion3.reColor(pointsLayer3, name3, fromHexAndAlpha2);
                        Utils.Companion companion4 = Utils.INSTANCE;
                        pointsLayer4 = this.pointsLayer;
                        String name4 = tableListItem.getDestination().getName();
                        Drawable drawable2 = this.getDrawable(C5362R.drawable.icon_door_unselect);
                        companion4.reBitmapPoint(pointsLayer4, name4, drawable2 != null ? DrawableKt.toBitmap$default(drawable2, 0, 0, null, 7, null) : null);
                    }
                }
                AddTableAdapter.this.notifyDataSetChanged();
            }
        });
        addTableAdapter.setOnRenameClickListener(new Function2<TableListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoorMeetActivity$initRecycleView$$inlined$apply$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TableListItem tableListItem, Integer num) {
                invoke(tableListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(TableListItem item, int i) {
                Intrinsics.checkParameterIsNotNull(item, "item");
                AddDoorMeetActivity.this.showInputDialog(item, Integer.valueOf(i));
            }
        });
        addTableAdapter.setOnDeleteClickListener(new Function2<TableListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoorMeetActivity$initRecycleView$$inlined$apply$lambda$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TableListItem tableListItem, Integer num) {
                invoke(tableListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(TableListItem item, int i) {
                List<TableListItem> list;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                for (TableListItem tableListItem : list) {
                    tableListItem.setDelete(Intrinsics.areEqual(tableListItem, item));
                }
                AddTableAdapter.this.notifyDataSetChanged();
            }
        });
        addTableAdapter.setOnDeleteSureClickListener(new Function2<TableListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoorMeetActivity$initRecycleView$$inlined$apply$lambda$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TableListItem tableListItem, Integer num) {
                invoke(tableListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(TableListItem item, int i) {
                List list;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                list.remove(i);
                AddTableAdapter.this.notifyDataSetChanged();
                this.deletePoint(item.getDestination());
                this.setLineVisble();
            }
        });
        this.mAdapter = addTableAdapter;
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
        RelativeLayout rl_table_list2 = (RelativeLayout) _$_findCachedViewById(C5362R.id.rl_table_list);
        Intrinsics.checkExpressionValueIsNotNull(rl_table_list2, "rl_table_list");
        rl_table_list2.setVisibility(8);
        View view_line2 = _$_findCachedViewById(C5362R.id.view_line);
        Intrinsics.checkExpressionValueIsNotNull(view_line2, "view_line");
        view_line2.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showInputDialog(final TableListItem item, final Integer pos) {
        final InputFreeInstallDialog inputFreeInstallDialog = new InputFreeInstallDialog(this);
        String string = getString(C5362R.string.please_input_name);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.please_input_name)");
        inputFreeInstallDialog.setTitle(string);
        String string2 = getString(C5362R.string.sure);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.sure)");
        inputFreeInstallDialog.setBtnText(string2);
        inputFreeInstallDialog.setOnInputResult(new Function1<String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoorMeetActivity$showInputDialog$1
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
                List<TableListItem> list;
                PointsLayer pointsLayer;
                List list2;
                AddTableAdapter addTableAdapter;
                Vector3d vector3d;
                List list3;
                PointsLayer pointsLayer2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it.length() == 0) {
                    ToastUtils toastUtils = ToastUtils.INSTANCE;
                    String string3 = AddDoorMeetActivity.this.getString(C5362R.string.please_input_name);
                    Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.please_input_name)");
                    toastUtils.showShortToast(string3);
                    return;
                }
                Utils.Companion companion = Utils.INSTANCE;
                list = AddDoorMeetActivity.this.tableList;
                if (companion.checkPointNameRepeat(it, Constants.POINT_TYPE_DOOR, list)) {
                    ToastUtils toastUtils2 = ToastUtils.INSTANCE;
                    String string4 = AddDoorMeetActivity.this.getString(C5362R.string.name_repeat);
                    Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.name_repeat)");
                    toastUtils2.showShortToast(string4);
                    return;
                }
                inputFreeInstallDialog.dismiss();
                if (pos == null) {
                    Destination destination = new Destination();
                    destination.setName(it);
                    vector3d = AddDoorMeetActivity.this.vector3d;
                    if (vector3d == null) {
                        Intrinsics.throwNpe();
                    }
                    destination.setVector(vector3d);
                    destination.setMode(Constants.POINT_TYPE_DOOR);
                    list3 = AddDoorMeetActivity.this.tableList;
                    list3.add(new TableListItem(destination, false, false, 4, null));
                    Utils.Companion companion2 = Utils.INSTANCE;
                    pointsLayer2 = AddDoorMeetActivity.this.pointsLayer;
                    Drawable drawable = AddDoorMeetActivity.this.getDrawable(C5362R.drawable.icon_door_unselect);
                    companion2.addPoint(pointsLayer2, destination, drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null);
                } else {
                    Utils.Companion companion3 = Utils.INSTANCE;
                    pointsLayer = AddDoorMeetActivity.this.pointsLayer;
                    TableListItem tableListItem = item;
                    if (tableListItem == null) {
                        Intrinsics.throwNpe();
                    }
                    companion3.renamePoint(pointsLayer, tableListItem.getDestination().getName(), it);
                    list2 = AddDoorMeetActivity.this.tableList;
                    ((TableListItem) list2.get(pos.intValue())).getDestination().setName(it);
                }
                addTableAdapter = AddDoorMeetActivity.this.mAdapter;
                if (addTableAdapter != null) {
                    addTableAdapter.notifyDataSetChanged();
                }
                AddDoorMeetActivity.this.setLineVisble();
            }
        });
        inputFreeInstallDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deletePoint(Destination destination) {
        PointsLayer pointsLayer = this.pointsLayer;
        if (pointsLayer != null) {
            pointsLayer.remove(destination.getName());
        }
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

    private final void initMap() {
        ViewControlLayer viewControlLayer = new ViewControlLayer(this, (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView));
        this.mapLayer = new OccupancyGridLayer();
        this.robotLayer = new RobotLayer();
        this.pointsLayer = new PointsLayer(PointsLayer.PointType.SQUARE);
        this.pointLayer = new PointLayer();
        VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
        if (visualizationView != null) {
            visualizationView.onCreate(new CopyOnWriteArrayList<>(), CommonExtKt.getScreenWidth(this) * 2, CommonExtKt.getScreenHeight(this) * 2);
            visualizationView.addLayer(this.mapLayer);
            visualizationView.addLayer(this.pointLayer);
            visualizationView.addLayer(this.pointsLayer);
            visualizationView.addLayer(this.robotLayer);
            visualizationView.addLayer(viewControlLayer);
            visualizationView.onStart();
        }
    }

    private final void setFirstPoint() {
        Pdlog.m3273d(this.TAG, "setFirstPoint");
        Destination mapStartPoint = SpDataUtils.INSTANCE.getMapStartPoint();
        if (mapStartPoint != null) {
            Pdlog.m3273d(this.TAG, "setFirstPoint --- mapStartPoint " + mapStartPoint.getName());
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
        ((AddDoorMeetViewModel) getMViewModel()).getStaticMapLiveData().observe(this, new AddDoorMeetActivity$createObserver$1(this));
        ((AddDoorMeetViewModel) getMViewModel()).getStaticMap();
        RobotMoveManager.INSTANCE.addRobotPoseListener(this.robotPoseListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRestoreData() {
        setFirstPoint();
        List<TableListItem> addDoorPoint = SpDataUtils.INSTANCE.getAddDoorPoint();
        this.isDataLoad = true;
        if (addDoorPoint == null || addDoorPoint.isEmpty()) {
            return;
        }
        this.tableList.addAll(addDoorPoint);
        AddTableAdapter addTableAdapter = this.mAdapter;
        if (addTableAdapter != null) {
            addTableAdapter.notifyDataSetChanged();
        }
        setLineVisble();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = addDoorPoint.iterator();
        while (it.hasNext()) {
            arrayList.add(((TableListItem) it.next()).getDestination());
        }
        Utils.Companion companion = Utils.INSTANCE;
        PointsLayer pointsLayer = this.pointsLayer;
        Drawable drawable = getDrawable(C5362R.drawable.icon_door_unselect);
        companion.addAllPoint(pointsLayer, arrayList, drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void next() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddDoorMeetActivity$next$1(this, null), 3, null);
        startActivity(new Intent(this, (Class<?>) SelectMapSettingActivity.class).putExtra("from", 4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        RobotMoveManager.INSTANCE.removeRobotPoseListener(this.robotPoseListener);
        PointsLayer pointsLayer = this.pointsLayer;
        if (pointsLayer != null) {
            pointsLayer.clear();
        }
        this.mapLayer = (OccupancyGridLayer) null;
        this.robotLayer = (RobotLayer) null;
        this.pointsLayer = (PointsLayer) null;
    }
}
