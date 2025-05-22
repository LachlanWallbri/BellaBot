package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.freeinstall_ui.adapter.AddDoublePathAdapter;
import com.pudutech.freeinstall_ui.adapter.DoublePathListItem;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.dialog.IdentityFailDialog;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.view.MaxHeightRecyclerView;
import com.pudutech.freeinstall_ui.viewmodel.AddDoublePathViewModel;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.bean.Line;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.layer.DoubleLineLayer;
import com.pudutech.opengl_draw.layer.LinesLayer;
import com.pudutech.opengl_draw.layer.OccupancyGridLayer;
import com.pudutech.opengl_draw.layer.PointLayer;
import com.pudutech.opengl_draw.layer.ViewControlLayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddDoublePathActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0018\u001a\u00020\u00192\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0019H\u0016J\b\u0010\u001d\u001a\u00020\tH\u0016J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\b\u0010\u001f\u001a\u00020\u0019H\u0003J\b\u0010 \u001a\u00020\u0019H\u0002J\u0012\u0010!\u001a\u00020\u00192\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u000fH\u0016J\b\u0010%\u001a\u00020\u0019H\u0002J\b\u0010&\u001a\u00020\u0019H\u0014J\u0018\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020\u0019H\u0002J\u0018\u0010,\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\tH\u0002J\u0018\u0010.\u001a\u00020\u00192\u000e\u0010/\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010\u001bH\u0002J\b\u00101\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/AddDoublePathActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/AddDoublePathViewModel;", "()V", "TAG", "", "doubleLineLayer", "Lcom/pudutech/opengl_draw/layer/DoubleLineLayer;", "isDataLoad", "", "linesLayerTop", "Lcom/pudutech/opengl_draw/layer/LinesLayer;", "mAdapter", "Lcom/pudutech/freeinstall_ui/adapter/AddDoublePathAdapter;", "mapHeight", "", "mapLayer", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;", "mapWith", "pointLayer", "Lcom/pudutech/opengl_draw/layer/PointLayer;", "tableList", "", "Lcom/pudutech/freeinstall_ui/adapter/DoublePathListItem;", "addAllLines", "", "listItems", "", "createObserver", "currentActivityIsDark", "initListener", "initMap", "initRecycleView", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "locate", "onDestroy", "reColor", "name", TypedValues.Custom.S_COLOR, "Lcom/pudutech/opengl_draw/base/Color;", "setFirstPoint", "setSelect", "b", "setTopMap", "it", "Lcom/pudutech/opengl_draw/bean/Line;", "showIdentityFailDialog", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AddDoublePathActivity extends BaseActivity<AddDoublePathViewModel> {
    private HashMap _$_findViewCache;
    private DoubleLineLayer doubleLineLayer;
    private boolean isDataLoad;
    private LinesLayer linesLayerTop;
    private AddDoublePathAdapter mAdapter;
    private int mapHeight;
    private OccupancyGridLayer mapLayer;
    private int mapWith;
    private PointLayer pointLayer;
    private final String TAG = "AddDoublePathActivity";
    private final List<DoublePathListItem> tableList = new ArrayList();

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
        return C5362R.layout.activity_add_double_path;
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
        final AddDoublePathAdapter addDoublePathAdapter = new AddDoublePathAdapter();
        addDoublePathAdapter.setNewData(this.tableList);
        addDoublePathAdapter.setOnItemClickListener(new Function2<DoublePathListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$initRecycleView$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(DoublePathListItem doublePathListItem, Integer num) {
                invoke(doublePathListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(DoublePathListItem item, int i) {
                List list;
                List list2;
                List list3;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                DoublePathListItem doublePathListItem = (DoublePathListItem) list.get(i);
                list2 = this.tableList;
                doublePathListItem.setSelect(!((DoublePathListItem) list2.get(i)).isSelect());
                AddDoublePathAdapter.this.notifyDataSetChanged();
                list3 = this.tableList;
                if (((DoublePathListItem) list3.get(i)).isSelect()) {
                    AddDoublePathActivity addDoublePathActivity = this;
                    String name = item.getName();
                    Color fromHexAndAlpha = Color.fromHexAndAlpha("1CC33D", 1.0f);
                    Intrinsics.checkExpressionValueIsNotNull(fromHexAndAlpha, "Color.fromHexAndAlpha(\"1CC33D\", 1f)");
                    addDoublePathActivity.reColor(name, fromHexAndAlpha);
                    this.setSelect(item.getName(), true);
                    return;
                }
                AddDoublePathActivity addDoublePathActivity2 = this;
                String name2 = item.getName();
                Color fromHexAndAlpha2 = Color.fromHexAndAlpha("FF962D", 1.0f);
                Intrinsics.checkExpressionValueIsNotNull(fromHexAndAlpha2, "Color.fromHexAndAlpha(\"FF962D\", 1f)");
                addDoublePathActivity2.reColor(name2, fromHexAndAlpha2);
                this.setSelect(item.getName(), false);
            }
        });
        addDoublePathAdapter.notifyDataSetChanged();
        this.mAdapter = addDoublePathAdapter;
        MaxHeightRecyclerView rv_table_list2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5362R.id.rv_table_list);
        Intrinsics.checkExpressionValueIsNotNull(rv_table_list2, "rv_table_list");
        rv_table_list2.setAdapter(this.mAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reColor(String name, Color color) {
        DoubleLineLayer doubleLineLayer = this.doubleLineLayer;
        if (doubleLineLayer != null) {
            doubleLineLayer.setColor(name, color);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSelect(String name, boolean b) {
        DoubleLineLayer doubleLineLayer = this.doubleLineLayer;
        if (doubleLineLayer != null) {
            doubleLineLayer.setSelect(name, b);
        }
    }

    private final void initListener() {
        final TextView textView = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
        final long j = 800;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$initListener$$inlined$singleClick$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                List<DoublePathListItem> list;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView) > j || (textView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView, currentTimeMillis);
                    z = this.isDataLoad;
                    if (z) {
                        BaseActivity.showLoadingDialog$default(this, null, false, 3, null);
                        AddDoublePathViewModel addDoublePathViewModel = (AddDoublePathViewModel) this.getMViewModel();
                        list = this.tableList;
                        addDoublePathViewModel.setDoublePath(list);
                    }
                }
            }
        });
        final ImageView imageView = (ImageView) _$_findCachedViewById(C5362R.id.iv_locate);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$initListener$$inlined$singleClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(imageView) > j || (imageView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(imageView, currentTimeMillis);
                    this.locate();
                }
            }
        });
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
        this.linesLayerTop = new LinesLayer();
        this.doubleLineLayer = new DoubleLineLayer();
        this.pointLayer = new PointLayer();
        VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
        if (visualizationView != null) {
            visualizationView.onCreate(new CopyOnWriteArrayList<>(), CommonExtKt.getScreenWidth(this) * 2, CommonExtKt.getScreenHeight(this) * 2);
            visualizationView.addLayer(this.mapLayer);
            visualizationView.addLayer(this.linesLayerTop);
            visualizationView.addLayer(this.doubleLineLayer);
            visualizationView.addLayer(this.pointLayer);
            visualizationView.addLayer(viewControlLayer);
            visualizationView.onStart();
        }
        DoubleLineLayer doubleLineLayer = this.doubleLineLayer;
        if (doubleLineLayer != null) {
            Drawable drawable = getDrawable(C5362R.drawable.icon_muti_select);
            doubleLineLayer.setBitmap(drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null);
        }
        DoubleLineLayer doubleLineLayer2 = this.doubleLineLayer;
        if (doubleLineLayer2 != null) {
            doubleLineLayer2.setDoubleLineListener(new DoubleLineLayer.DoubleLineListener() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$initMap$2
                @Override // com.pudutech.opengl_draw.layer.DoubleLineLayer.DoubleLineListener
                public final void selectLineNameBack(String name, boolean z) {
                    List<DoublePathListItem> list;
                    AddDoublePathAdapter addDoublePathAdapter;
                    list = AddDoublePathActivity.this.tableList;
                    for (DoublePathListItem doublePathListItem : list) {
                        if (TextUtils.equals(name, doublePathListItem.getName())) {
                            doublePathListItem.setSelect(z);
                        }
                    }
                    addDoublePathAdapter = AddDoublePathActivity.this.mAdapter;
                    if (addDoublePathAdapter != null) {
                        addDoublePathAdapter.notifyDataSetChanged();
                    }
                    if (z) {
                        AddDoublePathActivity addDoublePathActivity = AddDoublePathActivity.this;
                        Intrinsics.checkExpressionValueIsNotNull(name, "name");
                        Color fromHexAndAlpha = Color.fromHexAndAlpha("1CC33D", 1.0f);
                        Intrinsics.checkExpressionValueIsNotNull(fromHexAndAlpha, "Color.fromHexAndAlpha(\"1CC33D\", 1f)");
                        addDoublePathActivity.reColor(name, fromHexAndAlpha);
                        return;
                    }
                    AddDoublePathActivity addDoublePathActivity2 = AddDoublePathActivity.this;
                    Intrinsics.checkExpressionValueIsNotNull(name, "name");
                    Color fromHexAndAlpha2 = Color.fromHexAndAlpha("FF962D", 1.0f);
                    Intrinsics.checkExpressionValueIsNotNull(fromHexAndAlpha2, "Color.fromHexAndAlpha(\"FF962D\", 1f)");
                    addDoublePathActivity2.reColor(name, fromHexAndAlpha2);
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
        AddDoublePathActivity addDoublePathActivity = this;
        ((AddDoublePathViewModel) getMViewModel()).getStaticMapLiveData().observe(addDoublePathActivity, new Observer<String>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String str) {
                OccupancyGridLayer occupancyGridLayer;
                Utils.Companion companion = Utils.INSTANCE;
                occupancyGridLayer = AddDoublePathActivity.this.mapLayer;
                companion.updateMap(occupancyGridLayer, str, new OccupancyGridLayer.OccupancyOneListener() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$createObserver$1.1
                    @Override // com.pudutech.opengl_draw.layer.OccupancyGridLayer.OccupancyOneListener
                    public final void onSuccess() {
                        String str2;
                        str2 = AddDoublePathActivity.this.TAG;
                        Pdlog.m3273d(str2, "地图绘制成功");
                        AddDoublePathActivity.this.locate();
                        AddDoublePathActivity.this.setFirstPoint();
                        ((AddDoublePathViewModel) AddDoublePathActivity.this.getMViewModel()).getTopoMap();
                        ((AddDoublePathViewModel) AddDoublePathActivity.this.getMViewModel()).getDoublePath();
                    }
                }, new Function2<Integer, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$createObserver$1.2
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                        invoke(num.intValue(), num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i, int i2) {
                        AddDoublePathActivity.this.mapWith = i;
                        AddDoublePathActivity.this.mapHeight = i2;
                    }
                });
            }
        });
        ((AddDoublePathViewModel) getMViewModel()).getStaticMap();
        ((AddDoublePathViewModel) getMViewModel()).getDataLiveData().observe(addDoublePathActivity, new Observer<List<? extends DoublePathListItem>>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$createObserver$2
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends DoublePathListItem> list) {
                onChanged2((List<DoublePathListItem>) list);
            }

            /* renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<DoublePathListItem> list) {
                List list2;
                List list3;
                AddDoublePathAdapter addDoublePathAdapter;
                list2 = AddDoublePathActivity.this.tableList;
                list2.clear();
                list3 = AddDoublePathActivity.this.tableList;
                list3.addAll(list != null ? list : new ArrayList());
                addDoublePathAdapter = AddDoublePathActivity.this.mAdapter;
                if (addDoublePathAdapter != null) {
                    addDoublePathAdapter.notifyDataSetChanged();
                }
                AddDoublePathActivity.this.addAllLines(list);
                AddDoublePathActivity.this.isDataLoad = true;
            }
        });
        ((AddDoublePathViewModel) getMViewModel()).getTopoMapLiveData().observe(addDoublePathActivity, new Observer<List<? extends Line>>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$createObserver$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends Line> list) {
                AddDoublePathActivity.this.setTopMap(list);
            }
        });
        ((AddDoublePathViewModel) getMViewModel()).getAddPathLiveData().observe(addDoublePathActivity, new Observer<Boolean>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$createObserver$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                AddDoublePathActivity.this.dismissLoadingDialog();
                AbnormalManager.INSTANCE.removeLocateStatusListener();
                AbnormalManager.INSTANCE.removeHardWareListener();
                AddDoublePathActivity addDoublePathActivity2 = AddDoublePathActivity.this;
                addDoublePathActivity2.startActivity(new Intent(addDoublePathActivity2, (Class<?>) MapCompleteActivity.class));
                AddDoublePathActivity.this.finish();
            }
        });
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
    public final void addAllLines(List<DoublePathListItem> listItems) {
        if (listItems == null || listItems.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (DoublePathListItem doublePathListItem : listItems) {
            if (doublePathListItem.getTopoTrack().size() != 0) {
                Pdlog.m3273d(this.TAG, "vector3List " + doublePathListItem.getTopoTrack() + "  topo_id " + doublePathListItem.getName());
                Line line = new Line(doublePathListItem.getTopoTrack(), doublePathListItem.getName());
                line.setColor(Color.fromHexAndAlpha("FF962D", 1.0f));
                arrayList.add(line);
            }
        }
        DoubleLineLayer doubleLineLayer = this.doubleLineLayer;
        if (doubleLineLayer != null) {
            doubleLineLayer.addAll(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        this.mapLayer = (OccupancyGridLayer) null;
    }

    private final void showIdentityFailDialog() {
        final IdentityFailDialog identityFailDialog = new IdentityFailDialog();
        identityFailDialog.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddDoublePathActivity$showIdentityFailDialog$identityFailDialog$1$1
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
                IdentityFailDialog.this.dismiss();
            }
        });
        identityFailDialog.showDialog(getSupportFragmentManager(), "identity_fail");
        identityFailDialog.setBitmapResource(Integer.valueOf(C5362R.drawable.icon_double));
        identityFailDialog.setContent(getString(C5362R.string.set_double_content));
        identityFailDialog.setButton(getString(C5362R.string.double_button));
    }
}
