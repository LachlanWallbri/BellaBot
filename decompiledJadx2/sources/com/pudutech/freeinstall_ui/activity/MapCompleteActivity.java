package com.pudutech.freeinstall_ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.lifecycle.Observer;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.adapter.ChargeListItem;
import com.pudutech.freeinstall_ui.adapter.DoublePathListItem;
import com.pudutech.freeinstall_ui.adapter.TableListItem;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.base.KtxFreeInstallActivityManager;
import com.pudutech.freeinstall_ui.bean.VirtualItemBean;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.view.NodeProgressBar;
import com.pudutech.freeinstall_ui.viewmodel.MapCompleteViewModel;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.TopoTrack;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.bean.Line;
import com.pudutech.opengl_draw.bean.Point;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.geometry.Vector3;
import com.pudutech.opengl_draw.layer.DottedLineLayer;
import com.pudutech.opengl_draw.layer.DoubleLineLayer;
import com.pudutech.opengl_draw.layer.LinesLayer;
import com.pudutech.opengl_draw.layer.OccupancyGridLayer;
import com.pudutech.opengl_draw.layer.PointLayer;
import com.pudutech.opengl_draw.layer.PointsLayer;
import com.pudutech.opengl_draw.layer.ViewControlLayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: MapCompleteActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010>\u001a\u00020?H\u0002J\b\u0010@\u001a\u00020?H\u0002J\b\u0010A\u001a\u00020?H\u0016J\b\u0010B\u001a\u00020\u0013H\u0016J\b\u0010C\u001a\u00020?H\u0002J\b\u0010D\u001a\u00020?H\u0002J\b\u0010E\u001a\u00020?H\u0002J\u0012\u0010F\u001a\u00020?2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J\b\u0010I\u001a\u00020?H\u0002J\b\u0010J\u001a\u00020\u001eH\u0016J\b\u0010K\u001a\u00020?H\u0002J\b\u0010L\u001a\u00020?H\u0014J\u0018\u0010M\u001a\u00020?2\u000e\u0010N\u001a\n\u0012\u0004\u0012\u00020P\u0018\u00010OH\u0003J\u001e\u0010Q\u001a\u00020?2\u0014\u0010R\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020T0S\u0018\u00010OH\u0002J\u0018\u0010U\u001a\u00020?2\u000e\u0010V\u001a\n\u0012\u0004\u0012\u00020W\u0018\u00010SH\u0002J\b\u0010X\u001a\u00020?H\u0002J0\u0010Y\u001a\u00020?2\u000e\u0010Z\u001a\n\u0012\u0004\u0012\u00020[\u0018\u00010O2\u000e\u0010\\\u001a\n\u0012\u0004\u0012\u00020]\u0018\u00010S2\u0006\u0010^\u001a\u00020\u0005H\u0003J\b\u0010_\u001a\u00020?H\u0002J\b\u0010`\u001a\u00020?H\u0002J\u0018\u0010a\u001a\u00020?2\u000e\u0010b\u001a\n\u0012\u0004\u0012\u00020c\u0018\u00010SH\u0002J\u0010\u0010d\u001a\u00020?2\u0006\u0010e\u001a\u00020\u0005H\u0002J\b\u0010f\u001a\u00020?H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u00102\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010/\"\u0004\b4\u00101R\u001c\u00105\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010/\"\u0004\b7\u00101R\u001c\u00108\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010/\"\u0004\b:\u00101R\u001c\u0010;\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010/\"\u0004\b=\u00101¨\u0006g"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/MapCompleteActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/MapCompleteViewModel;", "()V", "TAG", "", "dottedLineLayer", "Lcom/pudutech/opengl_draw/layer/DottedLineLayer;", "getDottedLineLayer", "()Lcom/pudutech/opengl_draw/layer/DottedLineLayer;", "setDottedLineLayer", "(Lcom/pudutech/opengl_draw/layer/DottedLineLayer;)V", "doubleLineLayer", "Lcom/pudutech/opengl_draw/layer/DoubleLineLayer;", "getDoubleLineLayer", "()Lcom/pudutech/opengl_draw/layer/DoubleLineLayer;", "setDoubleLineLayer", "(Lcom/pudutech/opengl_draw/layer/DoubleLineLayer;)V", "isDataLoad", "", "linesLayer", "Lcom/pudutech/opengl_draw/layer/LinesLayer;", "getLinesLayer", "()Lcom/pudutech/opengl_draw/layer/LinesLayer;", "setLinesLayer", "(Lcom/pudutech/opengl_draw/layer/LinesLayer;)V", "linesLayerTop", "getLinesLayerTop", "setLinesLayerTop", "mapHeight", "", "mapLayer", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;", "getMapLayer", "()Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;", "setMapLayer", "(Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;)V", "mapWith", "pointLayer", "Lcom/pudutech/opengl_draw/layer/PointLayer;", "getPointLayer", "()Lcom/pudutech/opengl_draw/layer/PointLayer;", "setPointLayer", "(Lcom/pudutech/opengl_draw/layer/PointLayer;)V", "pointsLayerCharge", "Lcom/pudutech/opengl_draw/layer/PointsLayer;", "getPointsLayerCharge", "()Lcom/pudutech/opengl_draw/layer/PointsLayer;", "setPointsLayerCharge", "(Lcom/pudutech/opengl_draw/layer/PointsLayer;)V", "pointsLayerDoor", "getPointsLayerDoor", "setPointsLayerDoor", "pointsLayerMeal", "getPointsLayerMeal", "setPointsLayerMeal", "pointsLayerStation", "getPointsLayerStation", "setPointsLayerStation", "pointsLayerTable", "getPointsLayerTable", "setPointsLayerTable", "ToHome", "", "complete", "createObserver", "currentActivityIsDark", "initData", "initListener", "initMap", "initView", "saveInstanceState", "Landroid/os/Bundle;", "jump", "layoutId", "locate", "onDestroy", "setChargePoint", "chargeListItems", "", "Lcom/pudutech/freeinstall_ui/adapter/ChargeListItem;", "setCruisePath", "cruisePath", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setDoublePath", "doublePaths", "Lcom/pudutech/freeinstall_ui/adapter/DoublePathListItem;", "setFirstPoint", "setPointView", "listItem", "Lcom/pudutech/freeinstall_ui/adapter/TableListItem;", "exceptionPoint", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "pointType", "setTopMap", "setView", "setVirtual", "virtuals", "Lcom/pudutech/freeinstall_ui/bean/VirtualItemBean;", "showErrorHint", "s", "showUploadFailDialog", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapCompleteActivity extends BaseActivity<MapCompleteViewModel> {
    private final String TAG = "MapCompleteActivity";
    private HashMap _$_findViewCache;
    private DottedLineLayer dottedLineLayer;
    private DoubleLineLayer doubleLineLayer;
    private boolean isDataLoad;
    private LinesLayer linesLayer;
    private LinesLayer linesLayerTop;
    private int mapHeight;
    private OccupancyGridLayer mapLayer;
    private int mapWith;
    private PointLayer pointLayer;
    private PointsLayer pointsLayerCharge;
    private PointsLayer pointsLayerDoor;
    private PointsLayer pointsLayerMeal;
    private PointsLayer pointsLayerStation;
    private PointsLayer pointsLayerTable;

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

    public final OccupancyGridLayer getMapLayer() {
        return this.mapLayer;
    }

    public final void setMapLayer(OccupancyGridLayer occupancyGridLayer) {
        this.mapLayer = occupancyGridLayer;
    }

    public final PointsLayer getPointsLayerTable() {
        return this.pointsLayerTable;
    }

    public final void setPointsLayerTable(PointsLayer pointsLayer) {
        this.pointsLayerTable = pointsLayer;
    }

    public final PointsLayer getPointsLayerDoor() {
        return this.pointsLayerDoor;
    }

    public final void setPointsLayerDoor(PointsLayer pointsLayer) {
        this.pointsLayerDoor = pointsLayer;
    }

    public final PointsLayer getPointsLayerMeal() {
        return this.pointsLayerMeal;
    }

    public final void setPointsLayerMeal(PointsLayer pointsLayer) {
        this.pointsLayerMeal = pointsLayer;
    }

    public final PointsLayer getPointsLayerStation() {
        return this.pointsLayerStation;
    }

    public final void setPointsLayerStation(PointsLayer pointsLayer) {
        this.pointsLayerStation = pointsLayer;
    }

    public final PointsLayer getPointsLayerCharge() {
        return this.pointsLayerCharge;
    }

    public final void setPointsLayerCharge(PointsLayer pointsLayer) {
        this.pointsLayerCharge = pointsLayer;
    }

    public final LinesLayer getLinesLayer() {
        return this.linesLayer;
    }

    public final void setLinesLayer(LinesLayer linesLayer) {
        this.linesLayer = linesLayer;
    }

    public final LinesLayer getLinesLayerTop() {
        return this.linesLayerTop;
    }

    public final void setLinesLayerTop(LinesLayer linesLayer) {
        this.linesLayerTop = linesLayer;
    }

    public final DottedLineLayer getDottedLineLayer() {
        return this.dottedLineLayer;
    }

    public final void setDottedLineLayer(DottedLineLayer dottedLineLayer) {
        this.dottedLineLayer = dottedLineLayer;
    }

    public final DoubleLineLayer getDoubleLineLayer() {
        return this.doubleLineLayer;
    }

    public final void setDoubleLineLayer(DoubleLineLayer doubleLineLayer) {
        this.doubleLineLayer = doubleLineLayer;
    }

    public final PointLayer getPointLayer() {
        return this.pointLayer;
    }

    public final void setPointLayer(PointLayer pointLayer) {
        this.pointLayer = pointLayer;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5362R.layout.activity_map_complete;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        setView();
        initMap();
        initListener();
    }

    private final void setView() {
        ((NodeProgressBar) _$_findCachedViewById(C5362R.id.npb_step)).setNodeList(Utils.INSTANCE.getNodeData(5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initData() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MapCompleteActivity$initData$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDoublePath(List<DoublePathListItem> doublePaths) {
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("setDoublePath ");
        sb.append(doublePaths != null ? Integer.valueOf(doublePaths.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (doublePaths == null || doublePaths.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (DoublePathListItem doublePathListItem : doublePaths) {
            if (doublePathListItem.isSelect()) {
                Pdlog.m3273d(this.TAG, "setDoublePath select " + doublePathListItem.getTopoTrack().size());
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVirtual(List<VirtualItemBean> virtuals) {
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("setVirtual ");
        sb.append(virtuals != null ? Integer.valueOf(virtuals.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (virtuals == null || virtuals.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (VirtualItemBean virtualItemBean : virtuals) {
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
    public final void setTopMap() {
        List<TopoTrack> topPath = LocateMappingManager.INSTANCE.getTopPath();
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("setTopMap ");
        sb.append(topPath != null ? Integer.valueOf(topPath.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (topPath == null || topPath.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (TopoTrack topoTrack : topPath) {
            if (topoTrack.getStart_pose() != null && topoTrack.getEnd_pose() != null) {
                ArrayList arrayList2 = new ArrayList();
                Utils.Companion companion = Utils.INSTANCE;
                Vector3d start_pose = topoTrack.getStart_pose();
                if (start_pose == null) {
                    Intrinsics.throwNpe();
                }
                Vector3 vector3dToVector3 = companion.vector3dToVector3(start_pose);
                if (vector3dToVector3 == null) {
                    Intrinsics.throwNpe();
                }
                arrayList2.add(vector3dToVector3);
                Utils.Companion companion2 = Utils.INSTANCE;
                Vector3d end_pose = topoTrack.getEnd_pose();
                if (end_pose == null) {
                    Intrinsics.throwNpe();
                }
                Vector3 vector3dToVector32 = companion2.vector3dToVector3(end_pose);
                if (vector3dToVector32 == null) {
                    Intrinsics.throwNpe();
                }
                arrayList2.add(vector3dToVector32);
                Line line = new Line(arrayList2, "");
                line.setColor(Color.fromHexAndAlpha("FFFFFF", 1.0f));
                arrayList.add(line);
            }
        }
        Pdlog.m3273d(this.TAG, "topPath " + arrayList.size());
        LinesLayer linesLayer = this.linesLayerTop;
        if (linesLayer != null) {
            linesLayer.addAlll(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCruisePath(List<List<Vector3d>> cruisePath) {
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("setCruisePath ");
        sb.append(cruisePath != null ? Integer.valueOf(cruisePath.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (cruisePath == null || cruisePath.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (List<Vector3d> list : cruisePath) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                Vector3 vector3dToVector3 = Utils.INSTANCE.vector3dToVector3((Vector3d) it.next());
                if (vector3dToVector3 == null) {
                    Intrinsics.throwNpe();
                }
                arrayList2.add(vector3dToVector3);
            }
            if (arrayList2.size() != 0) {
                Line line = new Line(arrayList2, "");
                line.setColor(Color.fromHexAndAlpha("1CC33D", 1.0f));
                arrayList.add(line);
            }
        }
        Pdlog.m3273d(this.TAG, "cruisePath " + cruisePath.size());
        LinesLayer linesLayer = this.linesLayer;
        if (linesLayer != null) {
            linesLayer.addAlll(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setChargePoint(List<ChargeListItem> chargeListItems) {
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("setChargePoint ");
        sb.append(chargeListItems != null ? Integer.valueOf(chargeListItems.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (chargeListItems == null || chargeListItems.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (ChargeListItem chargeListItem : chargeListItems) {
            String name = chargeListItem.getDockerResult().getName();
            Transform dockerDetectResultToTransform = Utils.INSTANCE.dockerDetectResultToTransform(chargeListItem.getDockerResult().getDocker());
            Drawable drawable = getDrawable(C5362R.drawable.icon_charge_unselect);
            arrayList.add(new Point(name, dockerDetectResultToTransform, drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null));
        }
        PointsLayer pointsLayer = this.pointsLayerCharge;
        if (pointsLayer != null) {
            pointsLayer.addAll(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPointView(List<TableListItem> listItem, List<Destination> exceptionPoint, String pointType) {
        Object obj;
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("setPointView ");
        sb.append(listItem != null ? Integer.valueOf(listItem.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        String str2 = this.TAG;
        Object[] objArr2 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("setPointView exceptionPoint ");
        sb2.append(exceptionPoint != null ? Integer.valueOf(exceptionPoint.size()) : null);
        objArr2[0] = sb2.toString();
        Pdlog.m3273d(str2, objArr2);
        if (listItem == null || listItem.isEmpty()) {
            return;
        }
        if (exceptionPoint != null) {
            for (Destination destination : exceptionPoint) {
                if (Intrinsics.areEqual(destination.getMode(), pointType)) {
                    Iterator<T> it = listItem.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            obj = it.next();
                            if (Intrinsics.areEqual(((TableListItem) obj).getDestination().getName(), destination.getName())) {
                                break;
                            }
                        } else {
                            obj = null;
                            break;
                        }
                    }
                    TableListItem tableListItem = (TableListItem) obj;
                    if (tableListItem != null) {
                        listItem.remove(tableListItem);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = listItem.iterator();
        while (it2.hasNext()) {
            arrayList.add(((TableListItem) it2.next()).getDestination());
        }
        if (arrayList.isEmpty()) {
            return;
        }
        switch (pointType.hashCode()) {
            case -793201736:
                if (pointType.equals("parking")) {
                    Utils.Companion companion = Utils.INSTANCE;
                    PointsLayer pointsLayer = this.pointsLayerStation;
                    Drawable drawable = getDrawable(C5362R.drawable.icon_station_unselect);
                    companion.addAllPoint(pointsLayer, arrayList, drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null);
                    return;
                }
                return;
            case 110115790:
                if (pointType.equals(Constants.POINT_TYPE_TABLE)) {
                    Utils.Companion companion2 = Utils.INSTANCE;
                    PointsLayer pointsLayer2 = this.pointsLayerTable;
                    Drawable drawable2 = getDrawable(C5362R.drawable.icon_table_unselect);
                    companion2.addAllPoint(pointsLayer2, arrayList, drawable2 != null ? DrawableKt.toBitmap$default(drawable2, 0, 0, null, 7, null) : null);
                    return;
                }
                return;
            case 111581111:
                if (pointType.equals(Constants.POINT_TYPE_DOOR)) {
                    Utils.Companion companion3 = Utils.INSTANCE;
                    PointsLayer pointsLayer3 = this.pointsLayerDoor;
                    Drawable drawable3 = getDrawable(C5362R.drawable.icon_door_unselect);
                    companion3.addAllPoint(pointsLayer3, arrayList, drawable3 != null ? DrawableKt.toBitmap$default(drawable3, 0, 0, null, 7, null) : null);
                    return;
                }
                return;
            case 1710315603:
                if (pointType.equals("dining_outlet")) {
                    Utils.Companion companion4 = Utils.INSTANCE;
                    PointsLayer pointsLayer4 = this.pointsLayerMeal;
                    Drawable drawable4 = getDrawable(C5362R.drawable.icon_meal_unselect);
                    companion4.addAllPoint(pointsLayer4, arrayList, drawable4 != null ? DrawableKt.toBitmap$default(drawable4, 0, 0, null, 7, null) : null);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private final void initListener() {
        final TextView textView = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
        final long j = 800;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.MapCompleteActivity$initListener$$inlined$singleClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView) > j || (textView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView, currentTimeMillis);
                    z = this.isDataLoad;
                    if (z) {
                        MapCompleteActivity mapCompleteActivity = this;
                        String string = mapCompleteActivity.getString(C5362R.string.upload_map_now);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.upload_map_now)");
                        mapCompleteActivity.showLoadingDialog(string, false);
                        this.complete();
                    }
                }
            }
        });
        final ImageView imageView = (ImageView) _$_findCachedViewById(C5362R.id.iv_locate);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.MapCompleteActivity$initListener$$inlined$singleClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(imageView) > j || (imageView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(imageView, currentTimeMillis);
                    this.locate();
                }
            }
        });
        final TextView textView2 = (TextView) _$_findCachedViewById(C5362R.id.tv_edit_continue);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.MapCompleteActivity$initListener$$inlined$singleClick$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView2) > j || (textView2 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView2, currentTimeMillis);
                    z = this.isDataLoad;
                    if (z) {
                        AbnormalManager.INSTANCE.addHardWareListener();
                        Intent intent = new Intent(this, (Class<?>) SelectMapSettingActivity.class);
                        intent.putExtra("from", 6);
                        this.startActivity(intent);
                        this.finish();
                    }
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void jump() {
        ((MapCompleteViewModel) getMViewModel()).loadMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void complete() {
        SpDataUtils.INSTANCE.clear();
        startActivity(new Intent(this, (Class<?>) MapSelectUserActivity.class));
        finish();
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
        this.pointsLayerTable = new PointsLayer(PointsLayer.PointType.CIRCLE);
        this.pointsLayerDoor = new PointsLayer(PointsLayer.PointType.CIRCLE);
        this.pointsLayerMeal = new PointsLayer(PointsLayer.PointType.CIRCLE);
        this.pointsLayerStation = new PointsLayer(PointsLayer.PointType.CIRCLE);
        this.pointsLayerCharge = new PointsLayer(PointsLayer.PointType.CIRCLE);
        this.linesLayer = new LinesLayer();
        this.linesLayerTop = new LinesLayer();
        this.dottedLineLayer = new DottedLineLayer();
        this.doubleLineLayer = new DoubleLineLayer();
        this.pointLayer = new PointLayer();
        VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
        if (visualizationView != null) {
            visualizationView.onCreate(new CopyOnWriteArrayList<>(), CommonExtKt.getScreenWidth(this) * 2, CommonExtKt.getScreenHeight(this) * 2);
            visualizationView.addLayer(this.mapLayer);
            visualizationView.addLayer(this.linesLayerTop);
            visualizationView.addLayer(this.linesLayer);
            visualizationView.addLayer(this.dottedLineLayer);
            visualizationView.addLayer(this.doubleLineLayer);
            visualizationView.addLayer(this.pointLayer);
            visualizationView.addLayer(this.pointsLayerTable);
            visualizationView.addLayer(this.pointsLayerDoor);
            visualizationView.addLayer(this.pointsLayerMeal);
            visualizationView.addLayer(this.pointsLayerStation);
            visualizationView.addLayer(this.pointsLayerCharge);
            visualizationView.addLayer(viewControlLayer);
            visualizationView.onStart();
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void ToHome() {
        RobotMapManager.INSTANCE.reloadMap();
        SpDataUtils.INSTANCE.clear();
        AppContext.StartActivityListener listener = AppContext.INSTANCE.getListener();
        if (listener != null) {
            listener.startSayHiAnimationActivity(AppContext.INSTANCE.getContext());
        }
        KtxFreeInstallActivityManager.INSTANCE.finishAllActivity();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        MapCompleteActivity mapCompleteActivity = this;
        ((MapCompleteViewModel) getMViewModel()).getStaticMapLiveData().observe(mapCompleteActivity, new Observer<String>() { // from class: com.pudutech.freeinstall_ui.activity.MapCompleteActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String str) {
                Utils.INSTANCE.updateMap(MapCompleteActivity.this.getMapLayer(), str, new OccupancyGridLayer.OccupancyOneListener() { // from class: com.pudutech.freeinstall_ui.activity.MapCompleteActivity$createObserver$1.1
                    @Override // com.pudutech.opengl_draw.layer.OccupancyGridLayer.OccupancyOneListener
                    public final void onSuccess() {
                        String str2;
                        str2 = MapCompleteActivity.this.TAG;
                        Pdlog.m3273d(str2, "地图绘制成功");
                        MapCompleteActivity.this.initData();
                        MapCompleteActivity.this.setFirstPoint();
                        MapCompleteActivity.this.locate();
                    }
                }, new Function2<Integer, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapCompleteActivity$createObserver$1.2
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                        invoke(num.intValue(), num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i, int i2) {
                        MapCompleteActivity.this.mapWith = i;
                        MapCompleteActivity.this.mapHeight = i2;
                    }
                });
            }
        });
        ((MapCompleteViewModel) getMViewModel()).getStaticMap();
        ((MapCompleteViewModel) getMViewModel()).isReloadSuccess().observe(mapCompleteActivity, new Observer<String>() { // from class: com.pudutech.freeinstall_ui.activity.MapCompleteActivity$createObserver$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String str) {
                if (str != null) {
                    MapCompleteActivity.this.dismissLoadingDialog();
                    MapCompleteActivity.this.ToHome();
                    return;
                }
                MapCompleteActivity.this.dismissLoadingDialog();
                MapCompleteActivity mapCompleteActivity2 = MapCompleteActivity.this;
                String string = mapCompleteActivity2.getString(C5362R.string.invalid_map);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.invalid_map)");
                mapCompleteActivity2.showErrorHint(string);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showErrorHint(String s) {
        final CommonDialog commonDialog = new CommonDialog(this);
        String string = getString(C5362R.string.tips);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
        commonDialog.setTit(string);
        commonDialog.setMinContent(s);
        commonDialog.setClose(true);
        commonDialog.setCancelable(false);
        commonDialog.setCanceledOnTouchOutside(false);
        commonDialog.getBtLeft().setVisibility(8);
        commonDialog.getBtRight().setVisibility(8);
        commonDialog.setCloseClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapCompleteActivity$showErrorHint$1$1
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
        commonDialog.show();
    }

    private final void showUploadFailDialog() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MapCompleteActivity$showUploadFailDialog$1(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        MapUpdateManager.INSTANCE.release();
        PointsLayer pointsLayer = this.pointsLayerTable;
        if (pointsLayer != null) {
            pointsLayer.clear();
        }
        PointsLayer pointsLayer2 = this.pointsLayerDoor;
        if (pointsLayer2 != null) {
            pointsLayer2.clear();
        }
        PointsLayer pointsLayer3 = this.pointsLayerCharge;
        if (pointsLayer3 != null) {
            pointsLayer3.clear();
        }
        PointsLayer pointsLayer4 = this.pointsLayerMeal;
        if (pointsLayer4 != null) {
            pointsLayer4.clear();
        }
        PointsLayer pointsLayer5 = this.pointsLayerStation;
        if (pointsLayer5 != null) {
            pointsLayer5.clear();
        }
        PointsLayer pointsLayer6 = (PointsLayer) null;
        this.pointsLayerDoor = pointsLayer6;
        this.pointsLayerTable = pointsLayer6;
        this.linesLayer = (LinesLayer) null;
        this.mapLayer = (OccupancyGridLayer) null;
    }
}
