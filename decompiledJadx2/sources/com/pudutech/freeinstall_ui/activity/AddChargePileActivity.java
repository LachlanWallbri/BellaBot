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
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.freeinstall_ui.adapter.AddChargePileAdapter;
import com.pudutech.freeinstall_ui.adapter.ChargeListItem;
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.dialog.IdentityFailDialog;
import com.pudutech.freeinstall_ui.dialog.InputFreeInstallDialog;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.view.MaxHeightRecyclerView;
import com.pudutech.freeinstall_ui.viewmodel.AddChargePileViewModel;
import com.pudutech.mirsdk.aidl.serialize.DockerResult;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.bean.Point;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AddChargePileActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000u\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f*\u0001\u0018\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u001f\u001a\u00020 2\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\"H\u0003J\u0012\u0010#\u001a\u00020 2\b\u0010$\u001a\u0004\u0018\u00010\u001cH\u0003J\u0010\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0005H\u0002J\b\u0010'\u001a\u00020 H\u0016J\b\u0010(\u001a\u00020\tH\u0016J\b\u0010)\u001a\u00020 H\u0002J\b\u0010*\u001a\u00020 H\u0002J\b\u0010+\u001a\u00020 H\u0002J\u0012\u0010,\u001a\u00020 2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020\rH\u0016J\b\u00100\u001a\u00020 H\u0002J\b\u00101\u001a\u00020 H\u0014J\u0010\u00102\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0005H\u0002J\b\u00103\u001a\u00020 H\u0002J\b\u00104\u001a\u00020 H\u0002J\b\u00105\u001a\u00020 H\u0002J\b\u00106\u001a\u00020 H\u0002J\b\u00107\u001a\u00020 H\u0002J\b\u00108\u001a\u00020 H\u0002J!\u00109\u001a\u00020 2\b\u0010:\u001a\u0004\u0018\u00010\u001c2\b\u0010;\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0002\u0010<R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/AddChargePileActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/AddChargePileViewModel;", "()V", "TAG", "", "detectResult", "Lcom/pudutech/mirsdk/mircore/coreparcel/DockerDetectResult;", "isDataLoad", "", "mAdapter", "Lcom/pudutech/freeinstall_ui/adapter/AddChargePileAdapter;", "mapHeight", "", "mapLayer", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;", "mapWith", "pointLayer", "Lcom/pudutech/opengl_draw/layer/PointLayer;", "pointsLayer", "Lcom/pudutech/opengl_draw/layer/PointsLayer;", "robotLayer", "Lcom/pudutech/opengl_draw/layer/RobotLayer;", "robotPoseListener", "com/pudutech/freeinstall_ui/activity/AddChargePileActivity$robotPoseListener$1", "Lcom/pudutech/freeinstall_ui/activity/AddChargePileActivity$robotPoseListener$1;", "tableList", "", "Lcom/pudutech/freeinstall_ui/adapter/ChargeListItem;", "vector3d", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "addAllPoint", "", "list", "", "addPoint", "chargeListItem", "checkNameRepeat", "name", "createObserver", "currentActivityIsDark", "initListener", "initMap", "initRecycleView", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "locate", "onDestroy", "removePoint", "setAdd", "setFinish", "setFirstPoint", "setLineVisble", "setRestoreData", "showIdentityFailDialog", "showInputDialog", "item", "pos", "(Lcom/pudutech/freeinstall_ui/adapter/ChargeListItem;Ljava/lang/Integer;)V", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AddChargePileActivity extends BaseActivity<AddChargePileViewModel> {
    private HashMap _$_findViewCache;
    private DockerDetectResult detectResult;
    private boolean isDataLoad;
    private AddChargePileAdapter mAdapter;
    private int mapHeight;
    private OccupancyGridLayer mapLayer;
    private int mapWith;
    private PointLayer pointLayer;
    private PointsLayer pointsLayer;
    private RobotLayer robotLayer;
    private Vector3d vector3d;
    private final String TAG = "AddChargePileActivity";
    private final List<ChargeListItem> tableList = new ArrayList();
    private final AddChargePileActivity$robotPoseListener$1 robotPoseListener = new RobotMoveManager.RobotPoseListener() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$robotPoseListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotPoseListener
        public void onPose(double x, double y, double yaw) {
            String str;
            RobotLayer robotLayer;
            Vector3d vector3d;
            str = AddChargePileActivity.this.TAG;
            Pdlog.m3273d(str, "x--" + x + "  y--" + y + "  yaw--" + yaw);
            AddChargePileActivity.this.vector3d = new Vector3d(x, y, yaw);
            Utils.Companion companion = Utils.INSTANCE;
            robotLayer = AddChargePileActivity.this.robotLayer;
            vector3d = AddChargePileActivity.this.vector3d;
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
        return C5362R.layout.activity_add_charge_pile;
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
        final AddChargePileAdapter addChargePileAdapter = new AddChargePileAdapter();
        addChargePileAdapter.setNewData(this.tableList);
        setLineVisble();
        addChargePileAdapter.setOnItemClickListener(new Function2<ChargeListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$initRecycleView$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ChargeListItem chargeListItem, Integer num) {
                invoke(chargeListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ChargeListItem item, int i) {
                List<ChargeListItem> list;
                PointsLayer pointsLayer;
                PointsLayer pointsLayer2;
                PointsLayer pointsLayer3;
                PointsLayer pointsLayer4;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                for (ChargeListItem chargeListItem : list) {
                    chargeListItem.setSelect(Intrinsics.areEqual(chargeListItem, item));
                    chargeListItem.setDelete(false);
                    if (Intrinsics.areEqual(chargeListItem, item)) {
                        Utils.Companion companion = Utils.INSTANCE;
                        pointsLayer = this.pointsLayer;
                        String name = chargeListItem.getDockerResult().getName();
                        Color fromHexAndAlpha = Color.fromHexAndAlpha("FF962D", 1.0f);
                        Intrinsics.checkExpressionValueIsNotNull(fromHexAndAlpha, "Color.fromHexAndAlpha(\"FF962D\", 1f)");
                        companion.reColor(pointsLayer, name, fromHexAndAlpha);
                        Utils.Companion companion2 = Utils.INSTANCE;
                        pointsLayer2 = this.pointsLayer;
                        String name2 = chargeListItem.getDockerResult().getName();
                        Drawable drawable = this.getDrawable(C5362R.drawable.icon_charge_select);
                        companion2.reBitmapPoint(pointsLayer2, name2, drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null);
                    } else {
                        Utils.Companion companion3 = Utils.INSTANCE;
                        pointsLayer3 = this.pointsLayer;
                        String name3 = chargeListItem.getDockerResult().getName();
                        Color fromHexAndAlpha2 = Color.fromHexAndAlpha("FFFFFF", 1.0f);
                        Intrinsics.checkExpressionValueIsNotNull(fromHexAndAlpha2, "Color.fromHexAndAlpha(\"FFFFFF\", 1f)");
                        companion3.reColor(pointsLayer3, name3, fromHexAndAlpha2);
                        Utils.Companion companion4 = Utils.INSTANCE;
                        pointsLayer4 = this.pointsLayer;
                        String name4 = chargeListItem.getDockerResult().getName();
                        Drawable drawable2 = this.getDrawable(C5362R.drawable.icon_charge_unselect);
                        companion4.reBitmapPoint(pointsLayer4, name4, drawable2 != null ? DrawableKt.toBitmap$default(drawable2, 0, 0, null, 7, null) : null);
                    }
                }
                AddChargePileAdapter.this.notifyDataSetChanged();
            }
        });
        addChargePileAdapter.setOnRenameClickListener(new Function2<ChargeListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$initRecycleView$$inlined$apply$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ChargeListItem chargeListItem, Integer num) {
                invoke(chargeListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ChargeListItem item, int i) {
                Intrinsics.checkParameterIsNotNull(item, "item");
                AddChargePileActivity.this.showInputDialog(item, Integer.valueOf(i));
            }
        });
        addChargePileAdapter.setOnDeleteClickListener(new Function2<ChargeListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$initRecycleView$$inlined$apply$lambda$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ChargeListItem chargeListItem, Integer num) {
                invoke(chargeListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ChargeListItem item, int i) {
                List<ChargeListItem> list;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                for (ChargeListItem chargeListItem : list) {
                    chargeListItem.setDelete(Intrinsics.areEqual(chargeListItem, item));
                }
                AddChargePileAdapter.this.notifyDataSetChanged();
            }
        });
        addChargePileAdapter.setOnDeleteSureClickListener(new Function2<ChargeListItem, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$initRecycleView$$inlined$apply$lambda$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ChargeListItem chargeListItem, Integer num) {
                invoke(chargeListItem, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ChargeListItem item, int i) {
                List list;
                Intrinsics.checkParameterIsNotNull(item, "item");
                list = this.tableList;
                list.remove(i);
                AddChargePileAdapter.this.notifyDataSetChanged();
                this.removePoint(item.getDockerResult().getName());
                this.setLineVisble();
            }
        });
        this.mAdapter = addChargePileAdapter;
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
    public final void showInputDialog(final ChargeListItem item, final Integer pos) {
        final InputFreeInstallDialog inputFreeInstallDialog = new InputFreeInstallDialog(this);
        String string = getString(C5362R.string.please_input_name);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.please_input_name)");
        inputFreeInstallDialog.setTitle(string);
        String string2 = getString(C5362R.string.sure);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.sure)");
        inputFreeInstallDialog.setBtnText(string2);
        inputFreeInstallDialog.setOnInputResult(new Function1<String, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$showInputDialog$1
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
                PointsLayer pointsLayer;
                List list;
                AddChargePileAdapter addChargePileAdapter;
                DockerDetectResult dockerDetectResult;
                List list2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (!(it.length() == 0)) {
                    checkNameRepeat = AddChargePileActivity.this.checkNameRepeat(it);
                    if (checkNameRepeat) {
                        ToastUtils toastUtils = ToastUtils.INSTANCE;
                        String string3 = AddChargePileActivity.this.getString(C5362R.string.name_repeat);
                        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.name_repeat)");
                        toastUtils.showShortToast(string3);
                        return;
                    }
                    inputFreeInstallDialog.dismiss();
                    if (pos == null) {
                        DockerResult dockerResult = new DockerResult();
                        dockerDetectResult = AddChargePileActivity.this.detectResult;
                        if (dockerDetectResult == null) {
                            dockerDetectResult = new DockerDetectResult();
                        }
                        dockerResult.setDocker(dockerDetectResult);
                        dockerResult.setName(it);
                        ChargeListItem chargeListItem = new ChargeListItem(dockerResult, false, false, 4, null);
                        list2 = AddChargePileActivity.this.tableList;
                        list2.add(chargeListItem);
                        AddChargePileActivity.this.addPoint(chargeListItem);
                    } else {
                        Utils.Companion companion = Utils.INSTANCE;
                        pointsLayer = AddChargePileActivity.this.pointsLayer;
                        ChargeListItem chargeListItem2 = item;
                        if (chargeListItem2 == null) {
                            Intrinsics.throwNpe();
                        }
                        companion.renamePoint(pointsLayer, chargeListItem2.getDockerResult().getName(), it);
                        list = AddChargePileActivity.this.tableList;
                        ((ChargeListItem) list.get(pos.intValue())).getDockerResult().setName(it);
                    }
                    addChargePileAdapter = AddChargePileActivity.this.mAdapter;
                    if (addChargePileAdapter != null) {
                        addChargePileAdapter.notifyDataSetChanged();
                    }
                    AddChargePileActivity.this.setLineVisble();
                    return;
                }
                ToastUtils toastUtils2 = ToastUtils.INSTANCE;
                String string4 = AddChargePileActivity.this.getString(C5362R.string.please_input_name);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.please_input_name)");
                toastUtils2.showShortToast(string4);
            }
        });
        inputFreeInstallDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addPoint(ChargeListItem chargeListItem) {
        PointsLayer pointsLayer;
        if ((chargeListItem != null ? chargeListItem.getDockerResult() : null) == null || (pointsLayer = this.pointsLayer) == null) {
            return;
        }
        String name = chargeListItem.getDockerResult().getName();
        Transform dockerDetectResultToTransform = Utils.INSTANCE.dockerDetectResultToTransform(chargeListItem.getDockerResult().getDocker());
        Drawable drawable = getDrawable(C5362R.drawable.icon_charge_unselect);
        pointsLayer.add(new Point(name, dockerDetectResultToTransform, drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null));
    }

    private final void addAllPoint(List<ChargeListItem> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (ChargeListItem chargeListItem : list) {
            String name = chargeListItem.getDockerResult().getName();
            Transform dockerDetectResultToTransform = Utils.INSTANCE.dockerDetectResultToTransform(chargeListItem.getDockerResult().getDocker());
            Drawable drawable = getDrawable(C5362R.drawable.icon_charge_unselect);
            arrayList.add(new Point(name, dockerDetectResultToTransform, drawable != null ? DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null) : null));
        }
        PointsLayer pointsLayer = this.pointsLayer;
        if (pointsLayer != null) {
            pointsLayer.addAll(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removePoint(String name) {
        PointsLayer pointsLayer = this.pointsLayer;
        if (pointsLayer != null) {
            pointsLayer.remove(name);
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
            if (Intrinsics.areEqual(name, ((ChargeListItem) obj).getDockerResult().getName())) {
                break;
            }
        }
        return ((ChargeListItem) obj) != null;
    }

    private final void initListener() {
        final TextView textView = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
        final long j = 800;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$initListener$$inlined$singleClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                boolean z;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView) > j || (textView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView, currentTimeMillis);
                    z = this.isDataLoad;
                    if (z) {
                        this.setFinish();
                    }
                }
            }
        });
        final TextView textView2 = (TextView) _$_findCachedViewById(C5362R.id.tv_add);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$initListener$$inlined$singleClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView2) > j || (textView2 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView2, currentTimeMillis);
                    this.setAdd();
                }
            }
        });
        final ImageView imageView = (ImageView) _$_findCachedViewById(C5362R.id.iv_min);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$initListener$$inlined$singleClick$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                List list;
                List list2;
                List list3;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(imageView) > j || (imageView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(imageView, currentTimeMillis);
                    list = this.tableList;
                    if (list.size() == 0) {
                        return;
                    }
                    MaxHeightRecyclerView rv_table_list = (MaxHeightRecyclerView) this._$_findCachedViewById(C5362R.id.rv_table_list);
                    Intrinsics.checkExpressionValueIsNotNull(rv_table_list, "rv_table_list");
                    if (rv_table_list.getVisibility() == 0) {
                        MaxHeightRecyclerView rv_table_list2 = (MaxHeightRecyclerView) this._$_findCachedViewById(C5362R.id.rv_table_list);
                        Intrinsics.checkExpressionValueIsNotNull(rv_table_list2, "rv_table_list");
                        rv_table_list2.setVisibility(8);
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
                        tv_min_table2.setText(((ChargeListItem) list2.get(list3.size() - 1)).getDockerResult().getName());
                        return;
                    }
                    MaxHeightRecyclerView rv_table_list3 = (MaxHeightRecyclerView) this._$_findCachedViewById(C5362R.id.rv_table_list);
                    Intrinsics.checkExpressionValueIsNotNull(rv_table_list3, "rv_table_list");
                    rv_table_list3.setVisibility(0);
                    View view_line2 = this._$_findCachedViewById(C5362R.id.view_line);
                    Intrinsics.checkExpressionValueIsNotNull(view_line2, "view_line");
                    view_line2.setVisibility(0);
                    TextView tv_min_table3 = (TextView) this._$_findCachedViewById(C5362R.id.tv_min_table);
                    Intrinsics.checkExpressionValueIsNotNull(tv_min_table3, "tv_min_table");
                    tv_min_table3.setVisibility(8);
                }
            }
        });
        final ImageView imageView2 = (ImageView) _$_findCachedViewById(C5362R.id.iv_locate);
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$initListener$$inlined$singleClick$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(imageView2) > j || (imageView2 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(imageView2, currentTimeMillis);
                    this.locate();
                }
            }
        });
        final TextView textView3 = (TextView) _$_findCachedViewById(C5362R.id.tv_back);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$initListener$$inlined$singleClick$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView3) > j || (textView3 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView3, currentTimeMillis);
                    Utils.INSTANCE.showBackDialog(this);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFinish() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AddChargePileActivity$setFinish$1(this, null), 3, null);
        startActivity(new Intent(this, (Class<?>) SelectMapSettingActivity.class).putExtra("from", 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void locate() {
        VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
        Intrinsics.checkExpressionValueIsNotNull(visualizationView, "visualizationView");
        visualizationView.getCamera().fullPreview(Utils.INSTANCE.getScreenScale(this.mapWith, this.mapHeight));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void setAdd() {
        if (this.vector3d == null) {
            ToastUtils toastUtils = ToastUtils.INSTANCE;
            String string = getString(C5362R.string.get_no_position);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.get_no_position)");
            toastUtils.showShortToast(string);
            return;
        }
        String string2 = getString(C5362R.string.identity_charge_pile);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.identity_charge_pile)");
        BaseActivity.showLoadingDialog$default(this, string2, false, 2, null);
        ((AddChargePileViewModel) getMViewModel()).identityChargePile();
    }

    private final void initMap() {
        ViewControlLayer viewControlLayer = new ViewControlLayer(this, (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView));
        this.mapLayer = new OccupancyGridLayer();
        this.robotLayer = new RobotLayer();
        this.pointsLayer = new PointsLayer(PointsLayer.PointType.CIRCLE);
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
        AddChargePileActivity addChargePileActivity = this;
        ((AddChargePileViewModel) getMViewModel()).getStaticMapLiveData().observe(addChargePileActivity, new AddChargePileActivity$createObserver$1(this));
        ((AddChargePileViewModel) getMViewModel()).getStaticMap();
        RobotMoveManager.INSTANCE.addRobotPoseListener(this.robotPoseListener);
        ((AddChargePileViewModel) getMViewModel()).getIdentityChargeLiveData().observe(addChargePileActivity, new Observer<DockerDetectResult>() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$createObserver$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(DockerDetectResult dockerDetectResult) {
                AddChargePileActivity.this.dismissLoadingDialog();
                AddChargePileActivity.this.detectResult = dockerDetectResult;
                if (dockerDetectResult == null || !dockerDetectResult.getDetect_sucess()) {
                    AddChargePileActivity.this.showIdentityFailDialog();
                } else {
                    AddChargePileActivity.this.showInputDialog(null, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRestoreData() {
        setFirstPoint();
        List<ChargeListItem> addChargePile = SpDataUtils.INSTANCE.getAddChargePile();
        this.isDataLoad = true;
        if (addChargePile == null || addChargePile.isEmpty()) {
            return;
        }
        this.tableList.addAll(addChargePile);
        AddChargePileAdapter addChargePileAdapter = this.mAdapter;
        if (addChargePileAdapter != null) {
            addChargePileAdapter.notifyDataSetChanged();
        }
        setLineVisble();
        addAllPoint(addChargePile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showIdentityFailDialog() {
        final IdentityFailDialog identityFailDialog = new IdentityFailDialog();
        identityFailDialog.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.AddChargePileActivity$showIdentityFailDialog$$inlined$apply$lambda$1
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

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                IdentityFailDialog.this.dismiss();
                AddChargePileActivity addChargePileActivity = this;
                String string = IdentityFailDialog.this.getString(C5362R.string.identity_charge_pile);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.identity_charge_pile)");
                BaseActivity.showLoadingDialog$default(addChargePileActivity, string, false, 2, null);
                ((AddChargePileViewModel) this.getMViewModel()).identityChargePile();
            }
        });
        identityFailDialog.showDialog(getSupportFragmentManager(), "identity_fail");
        identityFailDialog.setContent(getString(C5362R.string.set_charging_pile_fail_content));
        identityFailDialog.setButton(getString(C5362R.string.try_again));
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
        this.pointLayer = (PointLayer) null;
        this.pointsLayer = (PointsLayer) null;
    }
}
