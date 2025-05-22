package com.pudutech.mirsdk.mircore.p057ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.MirMappingCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitStep;
import com.pudutech.mirsdk.mircore.tools.EncodeUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.io.FileTreeWalk;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: MappingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010 \u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ$\u0010!\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\"j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`#2\b\u0010$\u001a\u0004\u0018\u00010\u0004J\u0012\u0010%\u001a\u00020\u001c2\b\u0010&\u001a\u0004\u0018\u00010'H\u0014J\u000e\u0010(\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010)\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/MappingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "autoChargeJob", "Lkotlinx/coroutines/Job;", "chargeDist", "", "chargeX", "chargeY", "chargeZ", "cntTime", "", "getChargeSignal", "", "getRunTimes", "hardwareService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "mapDir", "mapFile", "mappingCoreService", "Lcom/pudutech/mirsdk/mircore/MirMappingCoreInterface;", "open_camera", "runTimes", "wheelError", "autoCharge", "", "v", "Landroid/view/View;", "autoChargePause", "autoChargeSignal", "getMapFiles", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", MapElement.Key.DIR, "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openCore", "reInit", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MappingActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private Job autoChargeJob;
    private double chargeX;
    private double chargeY;
    private double chargeZ;
    private boolean getChargeSignal;
    private boolean getRunTimes;
    private String mapFile;
    private boolean open_camera;
    private boolean wheelError;
    private final String TAG = "MappingActivity";
    private final String mapDir = "/sdcard/pudu/map/";
    private double chargeDist = 0.5d;
    private int runTimes = 1;
    private int cntTime = 1;
    private final AIDLConnection<MirMappingCoreInterface> mappingCoreService = new AIDLConnection<>("com.pudutech.mirsdk.mircore.MirMappingCoreService", MappingActivity$mappingCoreService$1.INSTANCE, null, 4, null);
    private final AIDLConnection<HardwareInterface> hardwareService = new AIDLConnection<>("com.pudutech.mirsdk.hardware.HardwareService", MappingActivity$hardwareService$1.INSTANCE, null, 4, null);

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MappingCoreInitState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[MappingCoreInitState.Success.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[MappingCoreInitState.values().length];
            $EnumSwitchMapping$1[MappingCoreInitState.Fail.ordinal()] = 1;
            $EnumSwitchMapping$2 = new int[MappingCoreInitStep.values().length];
            $EnumSwitchMapping$2[MappingCoreInitStep.Finish.ordinal()] = 1;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

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

    public final void reInit(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate MappingActivity");
        setContentView(C5224R.layout.activity_test);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MappingActivity$onCreate$1(this, null), 3, null);
    }

    public final void openCore(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MappingActivity$openCore$1(this, null), 3, null);
    }

    public final ArrayList<String> getMapFiles(String dir) {
        boolean exists = new File(dir).exists();
        if (!exists) {
            if (exists) {
                throw new NoWhenBranchMatchedException();
            }
            return null;
        }
        FileTreeWalk walk$default = FilesKt.walk$default(new File(dir), null, 1, null);
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator it = SequencesKt.filter(SequencesKt.filter(walk$default.maxDepth(1), new Function1<File, Boolean>() { // from class: com.pudutech.mirsdk.mircore.ui.MappingActivity$getMapFiles$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(File file) {
                return Boolean.valueOf(invoke2(file));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(File it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                return it2.isFile();
            }
        }), new Function1<File, Boolean>() { // from class: com.pudutech.mirsdk.mircore.ui.MappingActivity$getMapFiles$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(File file) {
                return Boolean.valueOf(invoke2(file));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(File it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                return Intrinsics.areEqual(FilesKt.getExtension(it2), "pdmap");
            }
        }).iterator();
        while (it.hasNext()) {
            String name = ((File) it.next()).getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
            arrayList.add(StringsKt.replace$default(name, ".pdmap", "", false, 4, (Object) null));
        }
        if (arrayList.size() == 0) {
            return null;
        }
        int i = 0;
        for (Object obj : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.set(i, EncodeUtils.INSTANCE.decodeMapName((String) obj));
            i = i2;
        }
        return arrayList;
    }

    public final void autoCharge(View v) {
        MirMappingCoreInterface mirMappingCoreInterface;
        HardwareInterface hardwareInterface;
        CameraInterface camera;
        MirMappingCoreInterface mirMappingCoreInterface2;
        Intrinsics.checkParameterIsNotNull(v, "v");
        this.open_camera = true;
        AIDLConnection<MirMappingCoreInterface> aIDLConnection = this.mappingCoreService;
        Boolean valueOf = (aIDLConnection == null || (mirMappingCoreInterface2 = aIDLConnection.getInterface()) == null) ? null : Boolean.valueOf(mirMappingCoreInterface2.checkBeginMappingMarkerVisible());
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        boolean booleanValue = valueOf.booleanValue();
        Pdlog.m3273d(this.TAG, "checkBeginMappingMarkerVisible : ", Boolean.valueOf(booleanValue));
        if (booleanValue) {
            TextView status_text = (TextView) _$_findCachedViewById(C5224R.id.status_text);
            Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
            status_text.setText("find begin marker");
            AIDLConnection<HardwareInterface> aIDLConnection2 = this.hardwareService;
            if (aIDLConnection2 != null && (hardwareInterface = aIDLConnection2.getInterface()) != null && (camera = hardwareInterface.getCamera()) != null) {
                camera.closeMonocularCamera();
            }
            this.open_camera = false;
            AIDLConnection<MirMappingCoreInterface> aIDLConnection3 = this.mappingCoreService;
            if (aIDLConnection3 != null && (mirMappingCoreInterface = aIDLConnection3.getInterface()) != null) {
                mirMappingCoreInterface.startMapping();
            }
        } else {
            TextView status_text2 = (TextView) _$_findCachedViewById(C5224R.id.status_text);
            Intrinsics.checkExpressionValueIsNotNull(status_text2, "status_text");
            status_text2.setText("can't find begin marker");
        }
        this.open_camera = false;
    }

    public final void autoChargePause(View v) {
        HardwareInterface hardwareInterface;
        CameraInterface camera;
        MirMappingCoreInterface mirMappingCoreInterface;
        HardwareInterface hardwareInterface2;
        CameraInterface camera2;
        MirMappingCoreInterface mirMappingCoreInterface2;
        HardwareInterface hardwareInterface3;
        CameraInterface camera3;
        Intrinsics.checkParameterIsNotNull(v, "v");
        AIDLConnection<HardwareInterface> aIDLConnection = this.hardwareService;
        if (aIDLConnection != null && (hardwareInterface3 = aIDLConnection.getInterface()) != null && (camera3 = hardwareInterface3.getCamera()) != null) {
            camera3.openMonocularCamera();
        }
        this.open_camera = true;
        AIDLConnection<MirMappingCoreInterface> aIDLConnection2 = this.mappingCoreService;
        Boolean valueOf = (aIDLConnection2 == null || (mirMappingCoreInterface2 = aIDLConnection2.getInterface()) == null) ? null : Boolean.valueOf(mirMappingCoreInterface2.checkFinishMappingMarkerVisible());
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        boolean booleanValue = valueOf.booleanValue();
        Pdlog.m3273d(this.TAG, "checkFinishMappingMarkerVisible : ", Boolean.valueOf(booleanValue));
        if (booleanValue) {
            TextView status_text = (TextView) _$_findCachedViewById(C5224R.id.status_text);
            Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
            status_text.setText("find finish marker");
            AIDLConnection<HardwareInterface> aIDLConnection3 = this.hardwareService;
            if (aIDLConnection3 != null && (hardwareInterface2 = aIDLConnection3.getInterface()) != null && (camera2 = hardwareInterface2.getCamera()) != null) {
                camera2.closeMonocularCamera();
            }
            this.open_camera = false;
            AIDLConnection<MirMappingCoreInterface> aIDLConnection4 = this.mappingCoreService;
            if (aIDLConnection4 != null && (mirMappingCoreInterface = aIDLConnection4.getInterface()) != null) {
                mirMappingCoreInterface.finishMapping();
            }
        } else {
            TextView status_text2 = (TextView) _$_findCachedViewById(C5224R.id.status_text);
            Intrinsics.checkExpressionValueIsNotNull(status_text2, "status_text");
            status_text2.setText("can't find finish marker");
        }
        AIDLConnection<HardwareInterface> aIDLConnection5 = this.hardwareService;
        if (aIDLConnection5 != null && (hardwareInterface = aIDLConnection5.getInterface()) != null && (camera = hardwareInterface.getCamera()) != null) {
            camera.closeMonocularCamera();
        }
        this.open_camera = false;
    }

    public final void autoChargeSignal(View v) {
        MirMappingCoreInterface mirMappingCoreInterface;
        MirMappingCoreInterface mirMappingCoreInterface2;
        Intrinsics.checkParameterIsNotNull(v, "v");
        AIDLConnection<MirMappingCoreInterface> aIDLConnection = this.mappingCoreService;
        String str = null;
        Boolean valueOf = (aIDLConnection == null || (mirMappingCoreInterface2 = aIDLConnection.getInterface()) == null) ? null : Boolean.valueOf(mirMappingCoreInterface2.getMappingOptStatus());
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        if (valueOf.booleanValue()) {
            AIDLConnection<MirMappingCoreInterface> aIDLConnection2 = this.mappingCoreService;
            if (aIDLConnection2 != null && (mirMappingCoreInterface = aIDLConnection2.getInterface()) != null) {
                str = mirMappingCoreInterface.getLoclizationMap();
            }
            TextView status_text = (TextView) _$_findCachedViewById(C5224R.id.status_text);
            Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
            status_text.setText(str);
            new ArrayList();
            return;
        }
        TextView status_text2 = (TextView) _$_findCachedViewById(C5224R.id.status_text);
        Intrinsics.checkExpressionValueIsNotNull(status_text2, "status_text");
        status_text2.setText("opting ....");
    }
}
