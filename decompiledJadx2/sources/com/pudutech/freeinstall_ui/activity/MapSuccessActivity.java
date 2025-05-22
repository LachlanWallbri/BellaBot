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
import com.pudutech.freeinstall_ui.base.BaseActivity;
import com.pudutech.freeinstall_ui.base.KtxFreeInstallActivityManager;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.freeinstall_ui.utils.CommonDialogUtils;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.freeinstall_ui.utils.SpDataUtils;
import com.pudutech.freeinstall_ui.utils.Utils;
import com.pudutech.freeinstall_ui.viewmodel.MapSuccessViewModel;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.layer.OccupancyGridLayer;
import com.pudutech.opengl_draw.layer.PointLayer;
import com.pudutech.opengl_draw.layer.ViewControlLayer;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* compiled from: MapSuccessActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0002J\u0012\u0010\u0018\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0007H\u0016J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0014J\b\u0010\u001e\u001a\u00020\u0013H\u0002J\b\u0010\u001f\u001a\u00020\u0013H\u0002J\b\u0010 \u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006!"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/activity/MapSuccessActivity;", "Lcom/pudutech/freeinstall_ui/base/BaseActivity;", "Lcom/pudutech/freeinstall_ui/viewmodel/MapSuccessViewModel;", "()V", "TAG", "", "mapHeight", "", "mapLayer", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;", "mapWith", "pointLayer", "Lcom/pudutech/opengl_draw/layer/PointLayer;", "type", "getType", "()I", "setType", "(I)V", "createObserver", "", "currentActivityIsDark", "", "initListener", "initMap", "initView", "saveInstanceState", "Landroid/os/Bundle;", "layoutId", "locate", "onDestroy", "setFirstPoint", "showAgainMapDialog", "showExpandMapDialog", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapSuccessActivity extends BaseActivity<MapSuccessViewModel> {
    private HashMap _$_findViewCache;
    private int mapHeight;
    private OccupancyGridLayer mapLayer;
    private int mapWith;
    private PointLayer pointLayer;
    private final String TAG = "MapSuccessActivity";
    private int type = 1;

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

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C5362R.layout.activity_map_success;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        initMap();
        initListener();
    }

    private final void initListener() {
        final TextView textView = (TextView) _$_findCachedViewById(C5362R.id.tv_add);
        final long j = 800;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$initListener$$inlined$singleClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView) > j || (textView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView, currentTimeMillis);
                    this.setType(1);
                    this.showAgainMapDialog();
                }
            }
        });
        final TextView textView2 = (TextView) _$_findCachedViewById(C5362R.id.tv_add_expand);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$initListener$$inlined$singleClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView2) > j || (textView2 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView2, currentTimeMillis);
                    this.setType(2);
                    this.showExpandMapDialog();
                }
            }
        });
        final ImageView imageView = (ImageView) _$_findCachedViewById(C5362R.id.iv_locate);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$initListener$$inlined$singleClick$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(imageView) > j || (imageView instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(imageView, currentTimeMillis);
                    this.locate();
                }
            }
        });
        final TextView textView3 = (TextView) _$_findCachedViewById(C5362R.id.tv_next_step);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$initListener$$inlined$singleClick$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView3) > j || (textView3 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView3, currentTimeMillis);
                    MapSuccessActivity mapSuccessActivity = this;
                    mapSuccessActivity.startActivity(new Intent(mapSuccessActivity, (Class<?>) MapSelectUserActivity.class));
                    KtxFreeInstallActivityManager.INSTANCE.finishAllActivity();
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
        this.pointLayer = new PointLayer();
        VisualizationView visualizationView = (VisualizationView) _$_findCachedViewById(C5362R.id.visualizationView);
        if (visualizationView != null) {
            visualizationView.onCreate(new CopyOnWriteArrayList<>(), CommonExtKt.getScreenWidth(this) * 2, CommonExtKt.getScreenHeight(this) * 2);
            visualizationView.addLayer(this.mapLayer);
            visualizationView.addLayer(this.pointLayer);
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        ((MapSuccessViewModel) getMViewModel()).getStaticMapLiveData().observe(this, new Observer<String>() { // from class: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$createObserver$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String str) {
                OccupancyGridLayer occupancyGridLayer;
                Utils.Companion companion = Utils.INSTANCE;
                occupancyGridLayer = MapSuccessActivity.this.mapLayer;
                companion.updateMap(occupancyGridLayer, str, new OccupancyGridLayer.OccupancyOneListener() { // from class: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$createObserver$1.1
                    @Override // com.pudutech.opengl_draw.layer.OccupancyGridLayer.OccupancyOneListener
                    public final void onSuccess() {
                        String str2;
                        str2 = MapSuccessActivity.this.TAG;
                        Pdlog.m3273d(str2, "地图绘制成功");
                        MapSuccessActivity.this.locate();
                        MapSuccessActivity.this.setFirstPoint();
                    }
                }, new Function2<Integer, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$createObserver$1.2
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                        invoke(num.intValue(), num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i, int i2) {
                        MapSuccessActivity.this.mapWith = i;
                        MapSuccessActivity.this.mapHeight = i2;
                    }
                });
            }
        });
        ((MapSuccessViewModel) getMViewModel()).getStaticMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showExpandMapDialog() {
        String string = getString(C5362R.string.sure_expand_map);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.sure_expand_map)");
        String string2 = getString(C5362R.string.cancel);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.cancel)");
        String string3 = getString(C5362R.string.confirm_free);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.confirm_free)");
        CommonDialogUtils.Companion.showDoubleCommonDialog$default(CommonDialogUtils.INSTANCE, this, null, string, string2, string3, false, null, new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$showExpandMapDialog$1
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
                if (SpDataUtils.INSTANCE.getCameraType() == CameraType.FACE_CAMERA) {
                    RobotMapManager.INSTANCE.selectCameraScheme(CameraType.FACE_CAMERA);
                }
                MapSuccessActivity mapSuccessActivity = MapSuccessActivity.this;
                mapSuccessActivity.startActivity(new Intent(mapSuccessActivity, (Class<?>) ExpandMapActivity.class));
            }
        }, 98, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAgainMapDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder(this);
        String string = getString(C5362R.string.tips);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
        CommonDialog.Builder title = builder.setTitle(string);
        String string2 = getString(C5362R.string.sure_again_build_map);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.sure_again_build_map)");
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
        create.setBtLeftClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$showAgainMapDialog$1$1
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
        create.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$showAgainMapDialog$$inlined$let$lambda$1
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

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: MapSuccessActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/freeinstall_ui/activity/MapSuccessActivity$showAgainMapDialog$1$2$1"}, m3962k = 3, m3963mv = {1, 1, 16})
            /* renamed from: com.pudutech.freeinstall_ui.activity.MapSuccessActivity$showAgainMapDialog$$inlined$let$lambda$1$1 */
            /* loaded from: classes3.dex */
            public static final class C45681 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5230p$;

                C45681(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C45681 c45681 = new C45681(completion);
                    c45681.f5230p$ = (CoroutineScope) obj;
                    return c45681;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C45681) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f5230p$;
                    LocateMappingManager locateMappingManager = LocateMappingManager.INSTANCE;
                    String mapName = SpDataUtils.INSTANCE.getMapName();
                    if (mapName == null) {
                        mapName = "";
                    }
                    locateMappingManager.deleteSavedMap(mapName);
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CommonDialog.this.dismiss();
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C45681(null), 3, null);
                MapSuccessActivity mapSuccessActivity = this;
                mapSuccessActivity.startActivity(new Intent(mapSuccessActivity, (Class<?>) CreateMapNoticeActivity.class).putExtra(Constants.TYPE_CREATE_MAP_ARGUMENTS, 3));
            }
        });
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.freeinstall_ui.base.BaseActivity, com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        this.mapLayer = (OccupancyGridLayer) null;
        this.pointLayer = (PointLayer) null;
    }
}
