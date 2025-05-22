package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.freeinstall_ui.adapter.AddVirtualAdapter;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.bean.VirtualItemBean;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.freeinstall_ui.utils.CommonDialogUtils;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.view.MaxHeightRecyclerView;
import com.pudutech.freeinstall_ui.viewmodel.AddVirtualViewModel;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.bean.Line;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.geometry.Vector3;
import com.pudutech.opengl_draw.layer.DottedLineLayer;
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
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: AddVirtualActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u007f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0019\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\tH\u0016J\b\u0010$\u001a\u00020\"H\u0002J\b\u0010%\u001a\u00020\"H\u0003J\b\u0010&\u001a\u00020\"H\u0003J\u0012\u0010'\u001a\u00020\"2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020\u0010H\u0016J\b\u0010+\u001a\u00020\"H\u0002J\b\u0010,\u001a\u00020\"H\u0014J\u0010\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020\u0005H\u0002J\b\u0010/\u001a\u00020\"H\u0002J\b\u00100\u001a\u00020\"H\u0002J\b\u00101\u001a\u00020\"H\u0003J\u0018\u00102\u001a\u00020\"2\u000e\u00103\u001a\n\u0012\u0004\u0012\u000205\u0018\u000104H\u0002J\b\u00106\u001a\u00020\"H\u0002J\b\u00107\u001a\u00020\"H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/AddVirtualActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/AddVirtualViewModel;", "()V", "TAG", "", "dottedLineLayer", "Lcom/pudutech/opengl_draw/layer/DottedLineLayer;", "isDataLoad", "", "isEdit", "linesLayerTop", "Lcom/pudutech/opengl_draw/layer/LinesLayer;", "mAdapter", "Lcom/pudutech/freeinstall_ui/adapter/AddVirtualAdapter;", "mapHeight", "", "mapLayer", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;", "mapWith", "pointLayer", "Lcom/pudutech/opengl_draw/layer/PointLayer;", "robotLayer", "Lcom/pudutech/opengl_draw/layer/RobotLayer;", "robotPoseListener", "com/pudutech/freeinstall_ui/activity/AddVirtualActivity$robotPoseListener$1", "Lcom/pudutech/freeinstall_ui/activity/AddVirtualActivity$robotPoseListener$1;", "tableList", "", "Lcom/pudutech/freeinstall_ui/bean/VirtualItemBean;", "vector3d", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "virtualItemBean", "createObserver", "", "currentActivityIsDark", "initListener", "initMap", "initRecycleView", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "locate", "onDestroy", "removeLine", "name", "setFirstPoint", "setLineVisible", "setRestoreData", "setTopMap", "it", "", "Lcom/pudutech/opengl_draw/bean/Line;", "setView", "showTipsDialog", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AddVirtualActivity extends BaseActivity<AddVirtualViewModel> {
    private HashMap _$_findViewCache;
    private DottedLineLayer dottedLineLayer;
    private boolean isDataLoad;
    private boolean isEdit;
    private LinesLayer linesLayerTop;
    private AddVirtualAdapter mAdapter;
    private int mapHeight;
    private OccupancyGridLayer mapLayer;
    private int mapWith;
    private PointLayer pointLayer;
    private RobotLayer robotLayer;
    private Vector3d vector3d;
    private VirtualItemBean virtualItemBean;
    private final String TAG = "AddVirtualActivity";
    private final List<VirtualItemBean> tableList = new ArrayList();
    private final AddVirtualActivity$robotPoseListener$1 robotPoseListener = new RobotMoveManager.RobotPoseListener() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$robotPoseListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotPoseListener
        public void onPose(double x, double y, double yaw) {
            String str;
            RobotLayer robotLayer;
            Vector3d vector3d;
            str = AddVirtualActivity.this.TAG;
            Pdlog.m3273d(str, "x--" + x + "  y--" + y + "  yaw--" + yaw);
            AddVirtualActivity.this.vector3d = new Vector3d(x, y, yaw);
            Utils.Companion companion = Utils.INSTANCE;
            robotLayer = AddVirtualActivity.this.robotLayer;
            vector3d = AddVirtualActivity.this.vector3d;
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
        return C5362R.layout.activity_add_virtual;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        initMap();
        initListener();
        initRecycleView();
    }

    private final void initListener() {
        final TextView textView = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
        final long j = 800;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$initListener$$inlined$singleClick$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                List<VirtualItemBean> list;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView) > j || (textView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView, currentTimeMillis);
                    z = this.isDataLoad;
                    if (z) {
                        BaseActivity.showLoadingDialog$default(this, null, false, 3, null);
                        AddVirtualViewModel addVirtualViewModel = (AddVirtualViewModel) this.getMViewModel();
                        list = this.tableList;
                        addVirtualViewModel.saveVirtualWall(list);
                    }
                }
            }
        });
        final ImageView imageView = (ImageView) _$_findCachedViewById(C5362R.id.iv_locate);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$initListener$$inlined$singleClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(imageView) > j || (imageView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(imageView, currentTimeMillis);
                    this.locate();
                }
            }
        });
        final TextView textView2 = (TextView) _$_findCachedViewById(C5362R.id.tv_add);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$initListener$$inlined$singleClick$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DottedLineLayer dottedLineLayer;
                DottedLineLayer dottedLineLayer2;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView2) > j || (textView2 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView2, currentTimeMillis);
                    this.setView();
                    dottedLineLayer = this.dottedLineLayer;
                    if (dottedLineLayer != null) {
                        dottedLineLayer2 = this.dottedLineLayer;
                        if ((dottedLineLayer2 != null ? Boolean.valueOf(dottedLineLayer2.isHavFocus()) : null) == null) {
                            Intrinsics.throwNpe();
                        }
                        dottedLineLayer.setHavFocus(!r0.booleanValue());
                    }
                }
            }
        });
        final ImageView imageView2 = (ImageView) _$_findCachedViewById(C5362R.id.iv_min);
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$initListener$$inlined$singleClick$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                List list;
                List list2;
                List list3;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(imageView2) > j || (imageView2 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(imageView2, currentTimeMillis);
                    list = this.tableList;
                    if (list.size() == 0) {
                        return;
                    }
                    MaxHeightRecyclerView rv_virtual_list = (MaxHeightRecyclerView) this._$_findCachedViewById(C5362R.id.rv_virtual_list);
                    Intrinsics.checkExpressionValueIsNotNull(rv_virtual_list, "rv_virtual_list");
                    if (rv_virtual_list.getVisibility() == 0) {
                        MaxHeightRecyclerView rv_virtual_list2 = (MaxHeightRecyclerView) this._$_findCachedViewById(C5362R.id.rv_virtual_list);
                        Intrinsics.checkExpressionValueIsNotNull(rv_virtual_list2, "rv_virtual_list");
                        rv_virtual_list2.setVisibility(8);
                        View view_line = this._$_findCachedViewById(C5362R.id.view_line);
                        Intrinsics.checkExpressionValueIsNotNull(view_line, "view_line");
                        view_line.setVisibility(8);
                        TextView tv_min_table = (TextView) this._$_findCachedViewById(C5362R.id.tv_min_table);
                        Intrinsics.checkExpressionValueIsNotNull(tv_min_table, "tv_min_table");
                        tv_min_table.setVisibility(0);
                        TextView tv_min_table2 = (TextView) this._$_findCachedViewById(C5362R.id.tv_min_table);
                        Intrinsics.checkExpressionValueIsNotNull(tv_min_table2, "tv_min_table");
                        list2 = this.tableList;
                        list3 = this.tableList;
                        tv_min_table2.setText(((VirtualItemBean) list2.get(list3.size() - 1)).getName());
                        return;
                    }
                    MaxHeightRecyclerView rv_virtual_list3 = (MaxHeightRecyclerView) this._$_findCachedViewById(C5362R.id.rv_virtual_list);
                    Intrinsics.checkExpressionValueIsNotNull(rv_virtual_list3, "rv_virtual_list");
                    rv_virtual_list3.setVisibility(0);
                    View view_line2 = this._$_findCachedViewById(C5362R.id.view_line);
                    Intrinsics.checkExpressionValueIsNotNull(view_line2, "view_line");
                    view_line2.setVisibility(0);
                    TextView tv_min_table3 = (TextView) this._$_findCachedViewById(C5362R.id.tv_min_table);
                    Intrinsics.checkExpressionValueIsNotNull(tv_min_table3, "tv_min_table");
                    tv_min_table3.setVisibility(8);
                }
            }
        });
    }

    private final void initRecycleView() {
        MaxHeightRecyclerView rv_virtual_list = (MaxHeightRecyclerView) _$_findCachedViewById(C5362R.id.rv_virtual_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_virtual_list, "rv_virtual_list");
        rv_virtual_list.setLayoutManager(new LinearLayoutManager(this));
        final AddVirtualAdapter addVirtualAdapter = new AddVirtualAdapter();
        addVirtualAdapter.setNewData(this.tableList);
        setLineVisible();
        addVirtualAdapter.setOnDeleteClickListener(new Function2<VirtualItemBean, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$initRecycleView$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(VirtualItemBean virtualItemBean, Integer num) {
                invoke(virtualItemBean, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(VirtualItemBean item, int i) {
                List<VirtualItemBean> list;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                for (VirtualItemBean virtualItemBean : list) {
                    virtualItemBean.setDelete(Intrinsics.areEqual(virtualItemBean, item));
                }
                AddVirtualAdapter.this.notifyDataSetChanged();
            }
        });
        addVirtualAdapter.setOnDeleteSureClickListener(new Function2<VirtualItemBean, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$initRecycleView$$inlined$apply$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(VirtualItemBean virtualItemBean, Integer num) {
                invoke(virtualItemBean, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(VirtualItemBean item, int i) {
                List list;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                list.remove(i);
                AddVirtualAdapter.this.notifyDataSetChanged();
                this.removeLine(item.getName());
                this.setLineVisible();
            }
        });
        this.mAdapter = addVirtualAdapter;
        MaxHeightRecyclerView rv_virtual_list2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5362R.id.rv_virtual_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_virtual_list2, "rv_virtual_list");
        rv_virtual_list2.setAdapter(this.mAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeLine(String name) {
        DottedLineLayer dottedLineLayer = this.dottedLineLayer;
        if (dottedLineLayer != null) {
            dottedLineLayer.remove(name);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLineVisible() {
        if (this.tableList.size() > 0) {
            RelativeLayout rl_virtual_list = (RelativeLayout) _$_findCachedViewById(C5362R.id.rl_virtual_list);
            Intrinsics.checkExpressionValueIsNotNull(rl_virtual_list, "rl_virtual_list");
            rl_virtual_list.setVisibility(0);
            View view_line = _$_findCachedViewById(C5362R.id.view_line);
            Intrinsics.checkExpressionValueIsNotNull(view_line, "view_line");
            view_line.setVisibility(0);
            return;
        }
        View view_line2 = _$_findCachedViewById(C5362R.id.view_line);
        Intrinsics.checkExpressionValueIsNotNull(view_line2, "view_line");
        view_line2.setVisibility(8);
        RelativeLayout rl_virtual_list2 = (RelativeLayout) _$_findCachedViewById(C5362R.id.rl_virtual_list);
        Intrinsics.checkExpressionValueIsNotNull(rl_virtual_list2, "rl_virtual_list");
        rl_virtual_list2.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void locate() {
        VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
        Intrinsics.checkExpressionValueIsNotNull(visualizationView, "visualizationView");
        visualizationView.getCamera().fullPreview(Utils.INSTANCE.getScreenScale(this.mapWith, this.mapHeight));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setView() {
        this.isEdit = !this.isEdit;
        if (this.isEdit) {
            TextView tv_add = (TextView) _$_findCachedViewById(C5362R.id.tv_add);
            Intrinsics.checkExpressionValueIsNotNull(tv_add, "tv_add");
            Sdk27PropertiesKt.setBackgroundResource(tv_add, C5362R.drawable.rectangle_btn_red_8);
            TextView tv_add2 = (TextView) _$_findCachedViewById(C5362R.id.tv_add);
            Intrinsics.checkExpressionValueIsNotNull(tv_add2, "tv_add");
            tv_add2.setText(getString(C5362R.string.stop));
            TextView tv_next_step = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
            Intrinsics.checkExpressionValueIsNotNull(tv_next_step, "tv_next_step");
            tv_next_step.setVisibility(8);
            return;
        }
        TextView tv_add3 = (TextView) _$_findCachedViewById(C5362R.id.tv_add);
        Intrinsics.checkExpressionValueIsNotNull(tv_add3, "tv_add");
        tv_add3.setText(getString(C5362R.string.add));
        TextView tv_add4 = (TextView) _$_findCachedViewById(C5362R.id.tv_add);
        Intrinsics.checkExpressionValueIsNotNull(tv_add4, "tv_add");
        Sdk27PropertiesKt.setBackgroundResource(tv_add4, C5362R.drawable.rectangle_btn_1cc33d_8);
        TextView tv_next_step2 = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
        Intrinsics.checkExpressionValueIsNotNull(tv_next_step2, "tv_next_step");
        tv_next_step2.setVisibility(0);
    }

    private final void initMap() {
        ViewControlLayer viewControlLayer = new ViewControlLayer(this, (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView));
        this.mapLayer = new OccupancyGridLayer();
        this.robotLayer = new RobotLayer();
        this.linesLayerTop = new LinesLayer();
        this.dottedLineLayer = new DottedLineLayer();
        this.pointLayer = new PointLayer();
        VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
        if (visualizationView != null) {
            visualizationView.onCreate(new CopyOnWriteArrayList<>(), CommonExtKt.getScreenWidth(this) * 2, CommonExtKt.getScreenHeight(this) * 2);
            visualizationView.addLayer(this.mapLayer);
            visualizationView.addLayer(this.linesLayerTop);
            visualizationView.addLayer(this.dottedLineLayer);
            visualizationView.addLayer(this.pointLayer);
            visualizationView.addLayer(this.robotLayer);
            visualizationView.addLayer(viewControlLayer);
            visualizationView.onStart();
        }
        DottedLineLayer dottedLineLayer = this.dottedLineLayer;
        if (dottedLineLayer != null) {
            dottedLineLayer.setDefName(getString(C5362R.string.title_virtual));
        }
        DottedLineLayer dottedLineLayer2 = this.dottedLineLayer;
        if (dottedLineLayer2 != null) {
            dottedLineLayer2.setDottedLineLayerListener(new DottedLineLayer.DottedLineLayerListener() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$initMap$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.pudutech.opengl_draw.layer.DottedLineLayer.DottedLineLayerListener
                public void dottedLineBack(Vector3 startVector3, Vector3 endVector3, String name) {
                    String str;
                    AddVirtualActivity.this.setView();
                    if (startVector3 == null || endVector3 == null) {
                        return;
                    }
                    str = AddVirtualActivity.this.TAG;
                    Pdlog.m3273d(str, "dottedLineBack " + startVector3.getX() + ' ' + endVector3.getX());
                    ArrayList arrayList = new ArrayList();
                    Vector3d vector3ToVector3d = Utils.INSTANCE.vector3ToVector3d(startVector3);
                    if (vector3ToVector3d == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList.add(vector3ToVector3d);
                    Vector3d vector3ToVector3d2 = Utils.INSTANCE.vector3ToVector3d(endVector3);
                    if (vector3ToVector3d2 == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList.add(vector3ToVector3d2);
                    AddVirtualActivity addVirtualActivity = AddVirtualActivity.this;
                    if (name == null) {
                        name = "";
                    }
                    addVirtualActivity.virtualItemBean = new VirtualItemBean(arrayList, name, false, 4, null);
                    ((AddVirtualViewModel) AddVirtualActivity.this.getMViewModel()).checkVirtualWall(arrayList);
                }

                @Override // com.pudutech.opengl_draw.layer.DottedLineLayer.DottedLineLayerListener
                public void deleteLineName(String name) {
                    List list;
                    Object obj;
                    List list2;
                    list = AddVirtualActivity.this.tableList;
                    Iterator it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        } else {
                            obj = it.next();
                            if (Intrinsics.areEqual(((VirtualItemBean) obj).getName(), name)) {
                                break;
                            }
                        }
                    }
                    VirtualItemBean virtualItemBean = (VirtualItemBean) obj;
                    list2 = AddVirtualActivity.this.tableList;
                    List list3 = list2;
                    if (list3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
                    }
                    TypeIntrinsics.asMutableCollection(list3).remove(virtualItemBean);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFirstPoint() {
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
        AddVirtualActivity addVirtualActivity = this;
        ((AddVirtualViewModel) getMViewModel()).getStaticMapLiveData().observe(addVirtualActivity, new AddVirtualActivity$createObserver$1(this));
        ((AddVirtualViewModel) getMViewModel()).getStaticMap();
        RobotMoveManager.INSTANCE.addRobotPoseListener(this.robotPoseListener);
        ((AddVirtualViewModel) getMViewModel()).getCheckVirtualLiveData().observe(addVirtualActivity, (Observer) new Observer<boolean[]>() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$createObserver$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(boolean[] zArr) {
                VirtualItemBean virtualItemBean;
                List list;
                VirtualItemBean virtualItemBean2;
                AddVirtualAdapter addVirtualAdapter;
                DottedLineLayer dottedLineLayer;
                VirtualItemBean virtualItemBean3;
                if (zArr != null) {
                    if (zArr.length == 0) {
                        return;
                    }
                    if (!zArr[0]) {
                        AddVirtualActivity.this.showTipsDialog();
                        dottedLineLayer = AddVirtualActivity.this.dottedLineLayer;
                        if (dottedLineLayer != null) {
                            virtualItemBean3 = AddVirtualActivity.this.virtualItemBean;
                            dottedLineLayer.remove(virtualItemBean3 != null ? virtualItemBean3.getName() : null);
                            return;
                        }
                        return;
                    }
                    virtualItemBean = AddVirtualActivity.this.virtualItemBean;
                    if (virtualItemBean == null) {
                        return;
                    }
                    list = AddVirtualActivity.this.tableList;
                    virtualItemBean2 = AddVirtualActivity.this.virtualItemBean;
                    if (virtualItemBean2 == null) {
                        Intrinsics.throwNpe();
                    }
                    list.add(virtualItemBean2);
                    addVirtualAdapter = AddVirtualActivity.this.mAdapter;
                    if (addVirtualAdapter != null) {
                        addVirtualAdapter.notifyDataSetChanged();
                    }
                    AddVirtualActivity.this.setLineVisible();
                }
            }
        });
        ((AddVirtualViewModel) getMViewModel()).getTopoMapLiveData().observe(addVirtualActivity, new Observer<List<? extends Line>>() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$createObserver$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends Line> list) {
                AddVirtualActivity.this.setTopMap(list);
            }
        });
        ((AddVirtualViewModel) getMViewModel()).getDoublePathLiveData().observe(addVirtualActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$createObserver$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean it) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    AddVirtualActivity.this.dismissLoadingDialog();
                    AddVirtualActivity addVirtualActivity2 = AddVirtualActivity.this;
                    addVirtualActivity2.startActivity(new Intent(addVirtualActivity2, (Class<?>) AddDoublePathActivity.class));
                    AddVirtualActivity.this.finish();
                    return;
                }
                ((AddVirtualViewModel) AddVirtualActivity.this.getMViewModel()).setDoublePath(new ArrayList());
            }
        });
        ((AddVirtualViewModel) getMViewModel()).getAddPathLiveData().observe(addVirtualActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.AddVirtualActivity$createObserver$5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                AddVirtualActivity.this.dismissLoadingDialog();
                AbnormalManager.INSTANCE.removeLocateStatusListener();
                AbnormalManager.INSTANCE.removeHardWareListener();
                AddVirtualActivity addVirtualActivity2 = AddVirtualActivity.this;
                addVirtualActivity2.startActivity(new Intent(addVirtualActivity2, (Class<?>) MapCompleteActivity.class));
                AddVirtualActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRestoreData() {
        setFirstPoint();
        List<VirtualItemBean> virtual = SpDataUtils.INSTANCE.getVirtual();
        this.isDataLoad = true;
        if (virtual == null || virtual.isEmpty()) {
            return;
        }
        this.tableList.clear();
        this.tableList.addAll(virtual);
        AddVirtualAdapter addVirtualAdapter = this.mAdapter;
        if (addVirtualAdapter != null) {
            addVirtualAdapter.notifyDataSetChanged();
        }
        setLineVisible();
        ArrayList arrayList = new ArrayList();
        for (VirtualItemBean virtualItemBean : this.tableList) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it = virtualItemBean.getVector3ds().iterator();
            while (it.hasNext()) {
                Vector3 vector3dToVector3 = Utils.INSTANCE.vector3dToVector3((Vector3d) it.next());
                if (vector3dToVector3 != null) {
                    arrayList2.add(vector3dToVector3);
                }
            }
            if (!arrayList2.isEmpty()) {
                arrayList.add(new Line(arrayList2, ""));
            }
        }
        DottedLineLayer dottedLineLayer = this.dottedLineLayer;
        if (dottedLineLayer != null) {
            dottedLineLayer.addAll(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTopMap(List<? extends Line> it) {
        LinesLayer linesLayer;
        if (it == null || !(!it.isEmpty()) || (linesLayer = this.linesLayerTop) == null) {
            return;
        }
        linesLayer.addAlll(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTipsDialog() {
        String string = getString(C5362R.string.virtual_in_path);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.virtual_in_path)");
        CommonDialogUtils.Companion.showSingleCommonDialog$default(CommonDialogUtils.INSTANCE, this, null, string, null, null, 26, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        RobotMoveManager.INSTANCE.removeRobotPoseListener(this.robotPoseListener);
        this.mapLayer = (OccupancyGridLayer) null;
        this.robotLayer = (RobotLayer) null;
        this.dottedLineLayer = (DottedLineLayer) null;
    }
}
